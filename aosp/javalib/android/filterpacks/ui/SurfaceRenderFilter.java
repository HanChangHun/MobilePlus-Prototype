package android.filterpacks.ui;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.FilterSurfaceView;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GLEnvironment;
import android.filterfw.core.GLFrame;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.GenerateFinalPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;
import android.util.Log;
import android.view.SurfaceHolder;

public class SurfaceRenderFilter extends Filter implements SurfaceHolder.Callback {
  private static final String TAG = "SurfaceRenderFilter";
  
  private final int RENDERMODE_FILL_CROP = 2;
  
  private final int RENDERMODE_FIT = 1;
  
  private final int RENDERMODE_STRETCH = 0;
  
  private float mAspectRatio = 1.0F;
  
  private boolean mIsBound = false;
  
  private boolean mLogVerbose = Log.isLoggable("SurfaceRenderFilter", 2);
  
  private ShaderProgram mProgram;
  
  private int mRenderMode = 1;
  
  @GenerateFieldPort(hasDefault = true, name = "renderMode")
  private String mRenderModeString;
  
  private GLFrame mScreen;
  
  private int mScreenHeight;
  
  private int mScreenWidth;
  
  @GenerateFinalPort(name = "surfaceView")
  private FilterSurfaceView mSurfaceView;
  
  public SurfaceRenderFilter(String paramString) {
    super(paramString);
  }
  
  private void updateTargetRect() {
    int i = this.mScreenWidth;
    if (i > 0) {
      int j = this.mScreenHeight;
      if (j > 0) {
        ShaderProgram shaderProgram = this.mProgram;
        if (shaderProgram != null) {
          float f = i / j / this.mAspectRatio;
          i = this.mRenderMode;
          if (i != 0) {
            if (i != 1) {
              if (i == 2)
                if (f > 1.0F) {
                  shaderProgram.setTargetRect(0.0F, 0.5F - f * 0.5F, 1.0F, f);
                } else {
                  shaderProgram.setTargetRect(0.5F - 0.5F / f, 0.0F, 1.0F / f, 1.0F);
                }  
            } else if (f > 1.0F) {
              shaderProgram.setTargetRect(0.5F - 0.5F / f, 0.0F, 1.0F / f, 1.0F);
            } else {
              shaderProgram.setTargetRect(0.0F, 0.5F - f * 0.5F, 1.0F, f);
            } 
          } else {
            shaderProgram.setTargetRect(0.0F, 0.0F, 1.0F, 1.0F);
          } 
        } 
      } 
    } 
  }
  
  public void close(FilterContext paramFilterContext) {
    this.mSurfaceView.unbind();
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    updateTargetRect();
  }
  
  public void open(FilterContext paramFilterContext) {
    this.mSurfaceView.unbind();
    this.mSurfaceView.bindToListener(this, paramFilterContext.getGLEnvironment());
  }
  
  public void prepare(FilterContext paramFilterContext) {
    ShaderProgram shaderProgram = ShaderProgram.createIdentity(paramFilterContext);
    this.mProgram = shaderProgram;
    shaderProgram.setSourceRect(0.0F, 1.0F, 1.0F, -1.0F);
    this.mProgram.setClearsOutput(true);
    this.mProgram.setClearColor(0.0F, 0.0F, 0.0F);
    updateRenderMode();
    MutableFrameFormat mutableFrameFormat = ImageFormat.create(this.mSurfaceView.getWidth(), this.mSurfaceView.getHeight(), 3, 3);
    this.mScreen = (GLFrame)paramFilterContext.getFrameManager().newBoundFrame((FrameFormat)mutableFrameFormat, 101, 0L);
  }
  
  public void process(FilterContext paramFilterContext) {
    StringBuilder stringBuilder;
    if (!this.mIsBound) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(this);
      stringBuilder.append(": Ignoring frame as there is no surface to render to!");
      Log.w("SurfaceRenderFilter", stringBuilder.toString());
      return;
    } 
    if (this.mLogVerbose)
      Log.v("SurfaceRenderFilter", "Starting frame processing"); 
    GLEnvironment gLEnvironment = this.mSurfaceView.getGLEnv();
    if (gLEnvironment == stringBuilder.getGLEnvironment()) {
      Frame frame1;
      Frame frame2 = pullInput("frame");
      boolean bool = false;
      float f = frame2.getFormat().getWidth() / frame2.getFormat().getHeight();
      if (f != this.mAspectRatio) {
        if (this.mLogVerbose) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("New aspect ratio: ");
          stringBuilder1.append(f);
          stringBuilder1.append(", previously: ");
          stringBuilder1.append(this.mAspectRatio);
          Log.v("SurfaceRenderFilter", stringBuilder1.toString());
        } 
        this.mAspectRatio = f;
        updateTargetRect();
      } 
      if (this.mLogVerbose) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Got input format: ");
        stringBuilder1.append(frame2.getFormat());
        Log.v("SurfaceRenderFilter", stringBuilder1.toString());
      } 
      if (frame2.getFormat().getTarget() != 3) {
        frame1 = stringBuilder.getFrameManager().duplicateFrameToTarget(frame2, 3);
        bool = true;
      } else {
        frame1 = frame2;
      } 
      gLEnvironment.activateSurfaceWithId(this.mSurfaceView.getSurfaceId());
      this.mProgram.process(frame1, (Frame)this.mScreen);
      gLEnvironment.swapBuffers();
      if (bool)
        frame1.release(); 
      return;
    } 
    throw new RuntimeException("Surface created under different GLEnvironment!");
  }
  
  public void setupPorts() {
    if (this.mSurfaceView != null) {
      addMaskedInputPort("frame", (FrameFormat)ImageFormat.create(3));
      return;
    } 
    throw new RuntimeException("NULL SurfaceView passed to SurfaceRenderFilter");
  }
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mScreen : Landroid/filterfw/core/GLFrame;
    //   6: ifnull -> 36
    //   9: aload_0
    //   10: iload_3
    //   11: putfield mScreenWidth : I
    //   14: aload_0
    //   15: iload #4
    //   17: putfield mScreenHeight : I
    //   20: aload_0
    //   21: getfield mScreen : Landroid/filterfw/core/GLFrame;
    //   24: iconst_0
    //   25: iconst_0
    //   26: iload_3
    //   27: iload #4
    //   29: invokevirtual setViewport : (IIII)V
    //   32: aload_0
    //   33: invokespecial updateTargetRect : ()V
    //   36: aload_0
    //   37: monitorexit
    //   38: return
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   2	36	39	finally
  }
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield mIsBound : Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield mIsBound : Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public void tearDown(FilterContext paramFilterContext) {
    GLFrame gLFrame = this.mScreen;
    if (gLFrame != null)
      gLFrame.release(); 
  }
  
  public void updateRenderMode() {
    String str = this.mRenderModeString;
    if (str != null)
      if (str.equals("stretch")) {
        this.mRenderMode = 0;
      } else if (this.mRenderModeString.equals("fit")) {
        this.mRenderMode = 1;
      } else if (this.mRenderModeString.equals("fill_crop")) {
        this.mRenderMode = 2;
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown render mode '");
        stringBuilder.append(this.mRenderModeString);
        stringBuilder.append("'!");
        throw new RuntimeException(stringBuilder.toString());
      }  
    updateTargetRect();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/ui/SurfaceRenderFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.filterpacks.ui;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
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
import android.view.Surface;

public class SurfaceTargetFilter extends Filter {
  private static final String TAG = "SurfaceRenderFilter";
  
  private final int RENDERMODE_FILL_CROP = 2;
  
  private final int RENDERMODE_FIT = 1;
  
  private final int RENDERMODE_STRETCH = 0;
  
  private float mAspectRatio = 1.0F;
  
  private GLEnvironment mGlEnv;
  
  private boolean mLogVerbose = Log.isLoggable("SurfaceRenderFilter", 2);
  
  private ShaderProgram mProgram;
  
  private int mRenderMode = 1;
  
  @GenerateFieldPort(hasDefault = true, name = "renderMode")
  private String mRenderModeString;
  
  private GLFrame mScreen;
  
  @GenerateFieldPort(name = "oheight")
  private int mScreenHeight;
  
  @GenerateFieldPort(name = "owidth")
  private int mScreenWidth;
  
  @GenerateFinalPort(name = "surface")
  private Surface mSurface;
  
  private int mSurfaceId = -1;
  
  public SurfaceTargetFilter(String paramString) {
    super(paramString);
  }
  
  private void registerSurface() {
    int i = this.mGlEnv.registerSurface(this.mSurface);
    this.mSurfaceId = i;
    if (i >= 0)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not register Surface: ");
    stringBuilder.append(this.mSurface);
    throw new RuntimeException(stringBuilder.toString());
  }
  
  private void unregisterSurface() {
    int i = this.mSurfaceId;
    if (i > 0)
      this.mGlEnv.unregisterSurfaceId(i); 
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
    unregisterSurface();
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    this.mScreen.setViewport(0, 0, this.mScreenWidth, this.mScreenHeight);
    updateTargetRect();
  }
  
  public void open(FilterContext paramFilterContext) {
    registerSurface();
  }
  
  public void prepare(FilterContext paramFilterContext) {
    this.mGlEnv = paramFilterContext.getGLEnvironment();
    ShaderProgram shaderProgram = ShaderProgram.createIdentity(paramFilterContext);
    this.mProgram = shaderProgram;
    shaderProgram.setSourceRect(0.0F, 1.0F, 1.0F, -1.0F);
    this.mProgram.setClearsOutput(true);
    this.mProgram.setClearColor(0.0F, 0.0F, 0.0F);
    MutableFrameFormat mutableFrameFormat = ImageFormat.create(this.mScreenWidth, this.mScreenHeight, 3, 3);
    this.mScreen = (GLFrame)paramFilterContext.getFrameManager().newBoundFrame((FrameFormat)mutableFrameFormat, 101, 0L);
    updateRenderMode();
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame1;
    if (this.mLogVerbose)
      Log.v("SurfaceRenderFilter", "Starting frame processing"); 
    Frame frame2 = pullInput("frame");
    boolean bool = false;
    float f = frame2.getFormat().getWidth() / frame2.getFormat().getHeight();
    if (f != this.mAspectRatio) {
      if (this.mLogVerbose) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("New aspect ratio: ");
        stringBuilder.append(f);
        stringBuilder.append(", previously: ");
        stringBuilder.append(this.mAspectRatio);
        Log.v("SurfaceRenderFilter", stringBuilder.toString());
      } 
      this.mAspectRatio = f;
      updateTargetRect();
    } 
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Got input format: ");
      stringBuilder.append(frame2.getFormat());
      Log.v("SurfaceRenderFilter", stringBuilder.toString());
    } 
    if (frame2.getFormat().getTarget() != 3) {
      frame1 = paramFilterContext.getFrameManager().duplicateFrameToTarget(frame2, 3);
      bool = true;
    } else {
      frame1 = frame2;
    } 
    this.mGlEnv.activateSurfaceWithId(this.mSurfaceId);
    this.mProgram.process(frame1, (Frame)this.mScreen);
    this.mGlEnv.swapBuffers();
    if (bool)
      frame1.release(); 
  }
  
  public void setupPorts() {
    if (this.mSurface != null) {
      addMaskedInputPort("frame", (FrameFormat)ImageFormat.create(3));
      return;
    } 
    throw new RuntimeException("NULL Surface passed to SurfaceTargetFilter");
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


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/ui/SurfaceTargetFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
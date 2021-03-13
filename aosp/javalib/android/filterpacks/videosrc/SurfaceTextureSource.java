package android.filterpacks.videosrc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GLFrame;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.GenerateFinalPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;
import android.graphics.SurfaceTexture;
import android.opengl.Matrix;
import android.os.ConditionVariable;
import android.util.Log;

public class SurfaceTextureSource extends Filter {
  private static final String TAG = "SurfaceTextureSource";
  
  private static final boolean mLogVerbose;
  
  private static final float[] mSourceCoords = new float[] { 
      0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 
      0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F };
  
  @GenerateFieldPort(hasDefault = true, name = "closeOnTimeout")
  private boolean mCloseOnTimeout = false;
  
  private boolean mFirstFrame;
  
  private ShaderProgram mFrameExtractor;
  
  private float[] mFrameTransform = new float[16];
  
  @GenerateFieldPort(name = "height")
  private int mHeight;
  
  private float[] mMappedCoords = new float[16];
  
  private GLFrame mMediaFrame;
  
  private ConditionVariable mNewFrameAvailable = new ConditionVariable();
  
  private MutableFrameFormat mOutputFormat;
  
  private final String mRenderShader = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n";
  
  @GenerateFinalPort(name = "sourceListener")
  private SurfaceTextureSourceListener mSourceListener;
  
  private SurfaceTexture mSurfaceTexture;
  
  @GenerateFieldPort(hasDefault = true, name = "waitForNewFrame")
  private boolean mWaitForNewFrame = true;
  
  @GenerateFieldPort(hasDefault = true, name = "waitTimeout")
  private int mWaitTimeout = 1000;
  
  @GenerateFieldPort(name = "width")
  private int mWidth;
  
  private SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener = new SurfaceTexture.OnFrameAvailableListener() {
      public void onFrameAvailable(SurfaceTexture param1SurfaceTexture) {
        if (SurfaceTextureSource.mLogVerbose)
          Log.v("SurfaceTextureSource", "New frame from SurfaceTexture"); 
        SurfaceTextureSource.this.mNewFrameAvailable.open();
      }
    };
  
  static {
    mLogVerbose = Log.isLoggable("SurfaceTextureSource", 2);
  }
  
  public SurfaceTextureSource(String paramString) {
    super(paramString);
  }
  
  private void createFormats() {
    this.mOutputFormat = ImageFormat.create(this.mWidth, this.mHeight, 3, 3);
  }
  
  public void close(FilterContext paramFilterContext) {
    if (mLogVerbose)
      Log.v("SurfaceTextureSource", "SurfaceTextureSource closed"); 
    this.mSourceListener.onSurfaceTextureSourceReady(null);
    this.mSurfaceTexture.release();
    this.mSurfaceTexture = null;
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (paramString.equals("width") || paramString.equals("height"))
      this.mOutputFormat.setDimensions(this.mWidth, this.mHeight); 
  }
  
  public void open(FilterContext paramFilterContext) {
    if (mLogVerbose)
      Log.v("SurfaceTextureSource", "Opening SurfaceTextureSource"); 
    SurfaceTexture surfaceTexture = new SurfaceTexture(this.mMediaFrame.getTextureId());
    this.mSurfaceTexture = surfaceTexture;
    surfaceTexture.setOnFrameAvailableListener(this.onFrameAvailableListener);
    this.mSourceListener.onSurfaceTextureSourceReady(this.mSurfaceTexture);
    this.mFirstFrame = true;
  }
  
  protected void prepare(FilterContext paramFilterContext) {
    if (mLogVerbose)
      Log.v("SurfaceTextureSource", "Preparing SurfaceTextureSource"); 
    createFormats();
    this.mMediaFrame = (GLFrame)paramFilterContext.getFrameManager().newBoundFrame((FrameFormat)this.mOutputFormat, 104, 0L);
    this.mFrameExtractor = new ShaderProgram(paramFilterContext, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n");
  }
  
  public void process(FilterContext paramFilterContext) {
    if (mLogVerbose)
      Log.v("SurfaceTextureSource", "Processing new frame"); 
    if (this.mWaitForNewFrame || this.mFirstFrame) {
      int i = this.mWaitTimeout;
      if (i != 0) {
        if (!this.mNewFrameAvailable.block(i)) {
          if (this.mCloseOnTimeout) {
            if (mLogVerbose)
              Log.v("SurfaceTextureSource", "Timeout waiting for a new frame. Closing."); 
            closeOutputPort("video");
            return;
          } 
          throw new RuntimeException("Timeout waiting for new frame");
        } 
      } else {
        this.mNewFrameAvailable.block();
      } 
      this.mNewFrameAvailable.close();
      this.mFirstFrame = false;
    } 
    this.mSurfaceTexture.updateTexImage();
    this.mSurfaceTexture.getTransformMatrix(this.mFrameTransform);
    Matrix.multiplyMM(this.mMappedCoords, 0, this.mFrameTransform, 0, mSourceCoords, 0);
    ShaderProgram shaderProgram = this.mFrameExtractor;
    float[] arrayOfFloat = this.mMappedCoords;
    shaderProgram.setSourceRegion(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[4], arrayOfFloat[5], arrayOfFloat[8], arrayOfFloat[9], arrayOfFloat[12], arrayOfFloat[13]);
    Frame frame = paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mOutputFormat);
    this.mFrameExtractor.process((Frame)this.mMediaFrame, frame);
    frame.setTimestamp(this.mSurfaceTexture.getTimestamp());
    pushOutput("video", frame);
    frame.release();
  }
  
  public void setupPorts() {
    addOutputPort("video", (FrameFormat)ImageFormat.create(3, 3));
  }
  
  public void tearDown(FilterContext paramFilterContext) {
    GLFrame gLFrame = this.mMediaFrame;
    if (gLFrame != null)
      gLFrame.release(); 
  }
  
  public static interface SurfaceTextureSourceListener {
    void onSurfaceTextureSourceReady(SurfaceTexture param1SurfaceTexture);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videosrc/SurfaceTextureSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
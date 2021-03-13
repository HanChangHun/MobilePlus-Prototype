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
import android.hardware.Camera;
import android.opengl.Matrix;
import android.util.Log;
import java.io.IOException;
import java.util.List;

public class CameraSource extends Filter {
  private static final int NEWFRAME_TIMEOUT = 100;
  
  private static final int NEWFRAME_TIMEOUT_REPEAT = 10;
  
  private static final String TAG = "CameraSource";
  
  private static final String mFrameShader = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n";
  
  private static final float[] mSourceCoords = new float[] { 
      0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 
      0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F };
  
  private Camera mCamera;
  
  private GLFrame mCameraFrame;
  
  @GenerateFieldPort(hasDefault = true, name = "id")
  private int mCameraId = 0;
  
  private Camera.Parameters mCameraParameters;
  
  private float[] mCameraTransform = new float[16];
  
  @GenerateFieldPort(hasDefault = true, name = "framerate")
  private int mFps = 30;
  
  private ShaderProgram mFrameExtractor;
  
  @GenerateFieldPort(hasDefault = true, name = "height")
  private int mHeight = 240;
  
  private final boolean mLogVerbose = Log.isLoggable("CameraSource", 2);
  
  private float[] mMappedCoords = new float[16];
  
  private boolean mNewFrameAvailable;
  
  private MutableFrameFormat mOutputFormat;
  
  private SurfaceTexture mSurfaceTexture;
  
  @GenerateFinalPort(hasDefault = true, name = "waitForNewFrame")
  private boolean mWaitForNewFrame = true;
  
  @GenerateFieldPort(hasDefault = true, name = "width")
  private int mWidth = 320;
  
  private SurfaceTexture.OnFrameAvailableListener onCameraFrameAvailableListener = new SurfaceTexture.OnFrameAvailableListener() {
      public void onFrameAvailable(SurfaceTexture param1SurfaceTexture) {
        if (CameraSource.this.mLogVerbose)
          Log.v("CameraSource", "New frame from camera"); 
        synchronized (CameraSource.this) {
          CameraSource.access$102(CameraSource.this, true);
          CameraSource.this.notify();
          return;
        } 
      }
    };
  
  public CameraSource(String paramString) {
    super(paramString);
  }
  
  private void createFormats() {
    this.mOutputFormat = ImageFormat.create(this.mWidth, this.mHeight, 3, 3);
  }
  
  private int[] findClosestFpsRange(int paramInt, Camera.Parameters paramParameters) {
    List<int[]> list = paramParameters.getSupportedPreviewFpsRange();
    int[] arrayOfInt = list.get(0);
    for (int[] arrayOfInt2 : list) {
      int[] arrayOfInt1 = arrayOfInt;
      if (arrayOfInt2[0] < paramInt * 1000) {
        arrayOfInt1 = arrayOfInt;
        if (arrayOfInt2[1] > paramInt * 1000) {
          arrayOfInt1 = arrayOfInt;
          if (arrayOfInt2[0] > arrayOfInt[0]) {
            arrayOfInt1 = arrayOfInt;
            if (arrayOfInt2[1] < arrayOfInt[1])
              arrayOfInt1 = arrayOfInt2; 
          } 
        } 
      } 
      arrayOfInt = arrayOfInt1;
    } 
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requested fps: ");
      stringBuilder.append(paramInt);
      stringBuilder.append(".Closest frame rate range: [");
      stringBuilder.append(arrayOfInt[0] / 1000.0D);
      stringBuilder.append(",");
      stringBuilder.append(arrayOfInt[1] / 1000.0D);
      stringBuilder.append("]");
      Log.v("CameraSource", stringBuilder.toString());
    } 
    return arrayOfInt;
  }
  
  private int[] findClosestSize(int paramInt1, int paramInt2, Camera.Parameters paramParameters) {
    List list = paramParameters.getSupportedPreviewSizes();
    int i = -1;
    int j = -1;
    int k = ((Camera.Size)list.get(0)).width;
    int m = ((Camera.Size)list.get(0)).height;
    for (Camera.Size size : list) {
      int i1 = i;
      int i2 = j;
      if (size.width <= paramInt1) {
        i1 = i;
        i2 = j;
        if (size.height <= paramInt2) {
          i1 = i;
          i2 = j;
          if (size.width >= i) {
            i1 = i;
            i2 = j;
            if (size.height >= j) {
              i1 = size.width;
              i2 = size.height;
            } 
          } 
        } 
      } 
      int i3 = k;
      int i4 = m;
      if (size.width < k) {
        i3 = k;
        i4 = m;
        if (size.height < m) {
          i3 = size.width;
          i4 = size.height;
        } 
      } 
      i = i1;
      j = i2;
      k = i3;
      m = i4;
    } 
    int n = i;
    if (i == -1) {
      j = m;
      n = k;
    } 
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requested resolution: (");
      stringBuilder.append(paramInt1);
      stringBuilder.append(", ");
      stringBuilder.append(paramInt2);
      stringBuilder.append("). Closest match: (");
      stringBuilder.append(n);
      stringBuilder.append(", ");
      stringBuilder.append(j);
      stringBuilder.append(").");
      Log.v("CameraSource", stringBuilder.toString());
    } 
    return new int[] { n, j };
  }
  
  public void close(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("CameraSource", "Closing"); 
    this.mCamera.release();
    this.mCamera = null;
    this.mSurfaceTexture.release();
    this.mSurfaceTexture = null;
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (paramString.equals("framerate")) {
      getCameraParameters();
      int[] arrayOfInt = findClosestFpsRange(this.mFps, this.mCameraParameters);
      this.mCameraParameters.setPreviewFpsRange(arrayOfInt[0], arrayOfInt[1]);
      this.mCamera.setParameters(this.mCameraParameters);
    } 
  }
  
  public Camera.Parameters getCameraParameters() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore_1
    //   4: aload_0
    //   5: getfield mCameraParameters : Landroid/hardware/Camera$Parameters;
    //   8: ifnonnull -> 58
    //   11: aload_0
    //   12: getfield mCamera : Landroid/hardware/Camera;
    //   15: ifnonnull -> 31
    //   18: aload_0
    //   19: aload_0
    //   20: getfield mCameraId : I
    //   23: invokestatic open : (I)Landroid/hardware/Camera;
    //   26: putfield mCamera : Landroid/hardware/Camera;
    //   29: iconst_1
    //   30: istore_1
    //   31: aload_0
    //   32: aload_0
    //   33: getfield mCamera : Landroid/hardware/Camera;
    //   36: invokevirtual getParameters : ()Landroid/hardware/Camera$Parameters;
    //   39: putfield mCameraParameters : Landroid/hardware/Camera$Parameters;
    //   42: iload_1
    //   43: ifeq -> 58
    //   46: aload_0
    //   47: getfield mCamera : Landroid/hardware/Camera;
    //   50: invokevirtual release : ()V
    //   53: aload_0
    //   54: aconst_null
    //   55: putfield mCamera : Landroid/hardware/Camera;
    //   58: aload_0
    //   59: aload_0
    //   60: getfield mWidth : I
    //   63: aload_0
    //   64: getfield mHeight : I
    //   67: aload_0
    //   68: getfield mCameraParameters : Landroid/hardware/Camera$Parameters;
    //   71: invokespecial findClosestSize : (IILandroid/hardware/Camera$Parameters;)[I
    //   74: astore_2
    //   75: aload_2
    //   76: iconst_0
    //   77: iaload
    //   78: istore_1
    //   79: aload_0
    //   80: iload_1
    //   81: putfield mWidth : I
    //   84: aload_2
    //   85: iconst_1
    //   86: iaload
    //   87: istore_3
    //   88: aload_0
    //   89: iload_3
    //   90: putfield mHeight : I
    //   93: aload_0
    //   94: getfield mCameraParameters : Landroid/hardware/Camera$Parameters;
    //   97: iload_1
    //   98: iload_3
    //   99: invokevirtual setPreviewSize : (II)V
    //   102: aload_0
    //   103: aload_0
    //   104: getfield mFps : I
    //   107: aload_0
    //   108: getfield mCameraParameters : Landroid/hardware/Camera$Parameters;
    //   111: invokespecial findClosestFpsRange : (ILandroid/hardware/Camera$Parameters;)[I
    //   114: astore_2
    //   115: aload_0
    //   116: getfield mCameraParameters : Landroid/hardware/Camera$Parameters;
    //   119: aload_2
    //   120: iconst_0
    //   121: iaload
    //   122: aload_2
    //   123: iconst_1
    //   124: iaload
    //   125: invokevirtual setPreviewFpsRange : (II)V
    //   128: aload_0
    //   129: getfield mCameraParameters : Landroid/hardware/Camera$Parameters;
    //   132: astore_2
    //   133: aload_0
    //   134: monitorexit
    //   135: aload_2
    //   136: areturn
    //   137: astore_2
    //   138: aload_0
    //   139: monitorexit
    //   140: aload_2
    //   141: athrow
    // Exception table:
    //   from	to	target	type
    //   4	11	137	finally
    //   11	29	137	finally
    //   31	42	137	finally
    //   46	58	137	finally
    //   58	75	137	finally
    //   79	84	137	finally
    //   88	133	137	finally
  }
  
  public void open(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("CameraSource", "Opening"); 
    this.mCamera = Camera.open(this.mCameraId);
    getCameraParameters();
    this.mCamera.setParameters(this.mCameraParameters);
    createFormats();
    GLFrame gLFrame = (GLFrame)paramFilterContext.getFrameManager().newBoundFrame((FrameFormat)this.mOutputFormat, 104, 0L);
    this.mCameraFrame = gLFrame;
    SurfaceTexture surfaceTexture = new SurfaceTexture(gLFrame.getTextureId());
    this.mSurfaceTexture = surfaceTexture;
    try {
      this.mCamera.setPreviewTexture(surfaceTexture);
      this.mSurfaceTexture.setOnFrameAvailableListener(this.onCameraFrameAvailableListener);
      this.mNewFrameAvailable = false;
      this.mCamera.startPreview();
      return;
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not bind camera surface texture: ");
      stringBuilder.append(iOException.getMessage());
      stringBuilder.append("!");
      throw new RuntimeException(stringBuilder.toString());
    } 
  }
  
  public void prepare(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("CameraSource", "Preparing"); 
    this.mFrameExtractor = new ShaderProgram(paramFilterContext, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n");
  }
  
  public void process(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("CameraSource", "Processing new frame"); 
    if (this.mWaitForNewFrame) {
      while (!this.mNewFrameAvailable) {
        if (10 != 0) {
          try {
            wait(100L);
          } catch (InterruptedException interruptedException) {
            if (this.mLogVerbose)
              Log.v("CameraSource", "Interrupted while waiting for new frame"); 
          } 
          continue;
        } 
        throw new RuntimeException("Timeout waiting for new frame");
      } 
      this.mNewFrameAvailable = false;
      if (this.mLogVerbose)
        Log.v("CameraSource", "Got new frame"); 
    } 
    this.mSurfaceTexture.updateTexImage();
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Using frame extractor in thread: ");
      stringBuilder.append(Thread.currentThread());
      Log.v("CameraSource", stringBuilder.toString());
    } 
    this.mSurfaceTexture.getTransformMatrix(this.mCameraTransform);
    Matrix.multiplyMM(this.mMappedCoords, 0, this.mCameraTransform, 0, mSourceCoords, 0);
    ShaderProgram shaderProgram = this.mFrameExtractor;
    float[] arrayOfFloat = this.mMappedCoords;
    shaderProgram.setSourceRegion(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[4], arrayOfFloat[5], arrayOfFloat[8], arrayOfFloat[9], arrayOfFloat[12], arrayOfFloat[13]);
    Frame frame = paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mOutputFormat);
    this.mFrameExtractor.process((Frame)this.mCameraFrame, frame);
    long l = this.mSurfaceTexture.getTimestamp();
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Timestamp: ");
      stringBuilder.append(l / 1.0E9D);
      stringBuilder.append(" s");
      Log.v("CameraSource", stringBuilder.toString());
    } 
    frame.setTimestamp(l);
    pushOutput("video", frame);
    frame.release();
    if (this.mLogVerbose)
      Log.v("CameraSource", "Done processing new frame"); 
  }
  
  public void setCameraParameters(Camera.Parameters paramParameters) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: aload_0
    //   4: getfield mWidth : I
    //   7: aload_0
    //   8: getfield mHeight : I
    //   11: invokevirtual setPreviewSize : (II)V
    //   14: aload_0
    //   15: aload_1
    //   16: putfield mCameraParameters : Landroid/hardware/Camera$Parameters;
    //   19: aload_0
    //   20: invokevirtual isOpen : ()Z
    //   23: ifeq -> 37
    //   26: aload_0
    //   27: getfield mCamera : Landroid/hardware/Camera;
    //   30: aload_0
    //   31: getfield mCameraParameters : Landroid/hardware/Camera$Parameters;
    //   34: invokevirtual setParameters : (Landroid/hardware/Camera$Parameters;)V
    //   37: aload_0
    //   38: monitorexit
    //   39: return
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    // Exception table:
    //   from	to	target	type
    //   2	37	40	finally
  }
  
  public void setupPorts() {
    addOutputPort("video", (FrameFormat)ImageFormat.create(3, 3));
  }
  
  public void tearDown(FilterContext paramFilterContext) {
    GLFrame gLFrame = this.mCameraFrame;
    if (gLFrame != null)
      gLFrame.release(); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videosrc/CameraSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
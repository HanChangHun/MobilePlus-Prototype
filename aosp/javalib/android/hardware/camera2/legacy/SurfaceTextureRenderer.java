package android.hardware.camera2.legacy;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Environment;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class SurfaceTextureRenderer {
  private static final boolean DEBUG = false;
  
  private static final int EGL_COLOR_BITLENGTH = 8;
  
  private static final int EGL_RECORDABLE_ANDROID = 12610;
  
  private static final int FLIP_TYPE_BOTH = 3;
  
  private static final int FLIP_TYPE_HORIZONTAL = 1;
  
  private static final int FLIP_TYPE_NONE = 0;
  
  private static final int FLIP_TYPE_VERTICAL = 2;
  
  private static final int FLOAT_SIZE_BYTES = 4;
  
  private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
  
  private static final int GLES_VERSION = 2;
  
  private static final int GL_MATRIX_SIZE = 16;
  
  private static final String LEGACY_PERF_PROPERTY = "persist.camera.legacy_perf";
  
  private static final DateTimeFormatter LOG_NAME_TIME_FORMATTER;
  
  private static final int PBUFFER_PIXEL_BYTES = 4;
  
  private static final String TAG = SurfaceTextureRenderer.class.getSimpleName();
  
  private static final int TRIANGLE_VERTICES_DATA_POS_OFFSET = 0;
  
  private static final int TRIANGLE_VERTICES_DATA_STRIDE_BYTES = 20;
  
  private static final int TRIANGLE_VERTICES_DATA_UV_OFFSET = 3;
  
  private static final int VERTEX_POS_SIZE = 3;
  
  private static final String VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
  
  private static final int VERTEX_UV_SIZE = 2;
  
  private static final float[] sBothFlipTriangleVertices;
  
  private static final float[] sHorizontalFlipTriangleVertices;
  
  private static final float[] sRegularTriangleVertices;
  
  private static final float[] sVerticalFlipTriangleVertices;
  
  private FloatBuffer mBothFlipTriangleVertices;
  
  private EGLConfig mConfigs;
  
  private List<EGLSurfaceHolder> mConversionSurfaces = new ArrayList<>();
  
  private EGLContext mEGLContext = EGL14.EGL_NO_CONTEXT;
  
  private EGLDisplay mEGLDisplay = EGL14.EGL_NO_DISPLAY;
  
  private final int mFacing;
  
  private FloatBuffer mHorizontalFlipTriangleVertices;
  
  private float[] mMVPMatrix = new float[16];
  
  private ByteBuffer mPBufferPixels;
  
  private PerfMeasurement mPerfMeasurer = null;
  
  private int mProgram;
  
  private FloatBuffer mRegularTriangleVertices;
  
  private float[] mSTMatrix = new float[16];
  
  private volatile SurfaceTexture mSurfaceTexture;
  
  private List<EGLSurfaceHolder> mSurfaces = new ArrayList<>();
  
  private int mTextureID = 0;
  
  private FloatBuffer mVerticalFlipTriangleVertices;
  
  private int maPositionHandle;
  
  private int maTextureHandle;
  
  private int muMVPMatrixHandle;
  
  private int muSTMatrixHandle;
  
  static {
    LOG_NAME_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss", Locale.ROOT);
    sHorizontalFlipTriangleVertices = new float[] { 
        -1.0F, -1.0F, 0.0F, 1.0F, 0.0F, 1.0F, -1.0F, 0.0F, 0.0F, 0.0F, 
        -1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F };
    sVerticalFlipTriangleVertices = new float[] { 
        -1.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 
        -1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F };
    sBothFlipTriangleVertices = new float[] { 
        -1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, -1.0F, 0.0F, 0.0F, 1.0F, 
        -1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F };
    sRegularTriangleVertices = new float[] { 
        -1.0F, -1.0F, 0.0F, 0.0F, 0.0F, 1.0F, -1.0F, 0.0F, 1.0F, 0.0F, 
        -1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F };
  }
  
  public SurfaceTextureRenderer(int paramInt) {
    this.mFacing = paramInt;
    FloatBuffer floatBuffer = ByteBuffer.allocateDirect(sRegularTriangleVertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    this.mRegularTriangleVertices = floatBuffer;
    floatBuffer.put(sRegularTriangleVertices).position(0);
    floatBuffer = ByteBuffer.allocateDirect(sHorizontalFlipTriangleVertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    this.mHorizontalFlipTriangleVertices = floatBuffer;
    floatBuffer.put(sHorizontalFlipTriangleVertices).position(0);
    floatBuffer = ByteBuffer.allocateDirect(sVerticalFlipTriangleVertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    this.mVerticalFlipTriangleVertices = floatBuffer;
    floatBuffer.put(sVerticalFlipTriangleVertices).position(0);
    floatBuffer = ByteBuffer.allocateDirect(sBothFlipTriangleVertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    this.mBothFlipTriangleVertices = floatBuffer;
    floatBuffer.put(sBothFlipTriangleVertices).position(0);
    Matrix.setIdentityM(this.mSTMatrix, 0);
  }
  
  private void addGlTimestamp(long paramLong) {
    PerfMeasurement perfMeasurement = this.mPerfMeasurer;
    if (perfMeasurement == null)
      return; 
    perfMeasurement.addTimestamp(paramLong);
  }
  
  private void beginGlTiming() {
    PerfMeasurement perfMeasurement = this.mPerfMeasurer;
    if (perfMeasurement == null)
      return; 
    perfMeasurement.startTimer();
  }
  
  private void checkEglDrawError(String paramString) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    if (EGL14.eglGetError() != 12299) {
      int i = EGL14.eglGetError();
      if (i == 12288)
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(": EGL error: 0x");
      stringBuilder.append(Integer.toHexString(i));
      throw new IllegalStateException(stringBuilder.toString());
    } 
    throw new LegacyExceptionUtils.BufferQueueAbandonedException();
  }
  
  private void checkEglError(String paramString) {
    int i = EGL14.eglGetError();
    if (i == 12288)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(": EGL error: 0x");
    stringBuilder.append(Integer.toHexString(i));
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private void checkGlDrawError(String paramString) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    boolean bool1 = false;
    boolean bool2 = false;
    while (true) {
      int i = GLES20.glGetError();
      if (i != 0) {
        if (i == 1285) {
          bool1 = true;
          continue;
        } 
        bool2 = true;
        continue;
      } 
      if (!bool2) {
        if (!bool1)
          return; 
        throw new LegacyExceptionUtils.BufferQueueAbandonedException();
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(": GLES20 error: 0x");
      stringBuilder.append(Integer.toHexString(i));
      throw new IllegalStateException(stringBuilder.toString());
    } 
  }
  
  private void checkGlError(String paramString) {
    int i = GLES20.glGetError();
    if (i == 0)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(": GLES20 error: 0x");
    stringBuilder.append(Integer.toHexString(i));
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private void clearState() {
    this.mSurfaces.clear();
    for (EGLSurfaceHolder eGLSurfaceHolder : this.mConversionSurfaces) {
      try {
        LegacyCameraDevice.disconnectSurface(eGLSurfaceHolder.surface);
      } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
        Log.w(TAG, "Surface abandoned, skipping...", (Throwable)bufferQueueAbandonedException);
      } 
    } 
    this.mConversionSurfaces.clear();
    this.mPBufferPixels = null;
    if (this.mSurfaceTexture != null)
      this.mSurfaceTexture.release(); 
    this.mSurfaceTexture = null;
  }
  
  private void configureEGLContext() {
    EGLDisplay eGLDisplay = EGL14.eglGetDisplay(0);
    this.mEGLDisplay = eGLDisplay;
    if (eGLDisplay != EGL14.EGL_NO_DISPLAY) {
      int[] arrayOfInt = new int[2];
      if (EGL14.eglInitialize(this.mEGLDisplay, arrayOfInt, 0, arrayOfInt, 1)) {
        EGLConfig[] arrayOfEGLConfig = new EGLConfig[1];
        int[] arrayOfInt1 = new int[1];
        EGLDisplay eGLDisplay1 = this.mEGLDisplay;
        int i = arrayOfEGLConfig.length;
        EGL14.eglChooseConfig(eGLDisplay1, new int[] { 
              12324, 8, 12323, 8, 12322, 8, 12352, 4, 12610, 1, 
              12339, 5, 12344 }, 0, arrayOfEGLConfig, 0, i, arrayOfInt1, 0);
        checkEglError("eglCreateContext RGB888+recordable ES2");
        this.mConfigs = arrayOfEGLConfig[0];
        this.mEGLContext = EGL14.eglCreateContext(this.mEGLDisplay, arrayOfEGLConfig[0], EGL14.EGL_NO_CONTEXT, new int[] { 12440, 2, 12344 }, 0);
        checkEglError("eglCreateContext");
        if (this.mEGLContext != EGL14.EGL_NO_CONTEXT)
          return; 
        throw new IllegalStateException("No EGLContext could be made");
      } 
      throw new IllegalStateException("Cannot initialize EGL14");
    } 
    throw new IllegalStateException("No EGL14 display");
  }
  
  private void configureEGLOutputSurfaces(Collection<EGLSurfaceHolder> paramCollection) {
    if (paramCollection != null && paramCollection.size() != 0) {
      for (EGLSurfaceHolder eGLSurfaceHolder : paramCollection) {
        eGLSurfaceHolder.eglSurface = EGL14.eglCreateWindowSurface(this.mEGLDisplay, this.mConfigs, eGLSurfaceHolder.surface, new int[] { 12344 }, 0);
        checkEglError("eglCreateWindowSurface");
      } 
      return;
    } 
    throw new IllegalStateException("No Surfaces were provided to draw to");
  }
  
  private void configureEGLPbufferSurfaces(Collection<EGLSurfaceHolder> paramCollection) {
    if (paramCollection != null && paramCollection.size() != 0) {
      int i = 0;
      for (EGLSurfaceHolder eGLSurfaceHolder : paramCollection) {
        int j = eGLSurfaceHolder.width * eGLSurfaceHolder.height;
        if (j > i)
          i = j; 
        int k = eGLSurfaceHolder.width;
        j = eGLSurfaceHolder.height;
        eGLSurfaceHolder.eglSurface = EGL14.eglCreatePbufferSurface(this.mEGLDisplay, this.mConfigs, new int[] { 12375, k, 12374, j, 12344 }, 0);
        checkEglError("eglCreatePbufferSurface");
      } 
      this.mPBufferPixels = ByteBuffer.allocateDirect(i * 4).order(ByteOrder.nativeOrder());
      return;
    } 
    throw new IllegalStateException("No Surfaces were provided to draw to");
  }
  
  private int createProgram(String paramString1, String paramString2) {
    int i = loadShader(35633, paramString1);
    if (i == 0)
      return 0; 
    int j = loadShader(35632, paramString2);
    if (j == 0)
      return 0; 
    int k = GLES20.glCreateProgram();
    checkGlError("glCreateProgram");
    if (k == 0)
      Log.e(TAG, "Could not create program"); 
    GLES20.glAttachShader(k, i);
    checkGlError("glAttachShader");
    GLES20.glAttachShader(k, j);
    checkGlError("glAttachShader");
    GLES20.glLinkProgram(k);
    int[] arrayOfInt = new int[1];
    GLES20.glGetProgramiv(k, 35714, arrayOfInt, 0);
    if (arrayOfInt[0] == 1)
      return k; 
    Log.e(TAG, "Could not link program: ");
    Log.e(TAG, GLES20.glGetProgramInfoLog(k));
    GLES20.glDeleteProgram(k);
    throw new IllegalStateException("Could not link program");
  }
  
  private void drawFrame(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2, int paramInt3) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    checkGlError("onDrawFrame start");
    paramSurfaceTexture.getTransformMatrix(this.mSTMatrix);
    Matrix.setIdentityM(this.mMVPMatrix, 0);
    try {
      Size size = LegacyCameraDevice.getTextureSize(paramSurfaceTexture);
      float f1 = size.getWidth();
      float f2 = size.getHeight();
      if (f1 > 0.0F && f2 > 0.0F) {
        FloatBuffer floatBuffer;
        RectF rectF1 = new RectF(0.0F, 0.0F, f1, f2);
        RectF rectF2 = new RectF(0.0F, 0.0F, paramInt1, paramInt2);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(rectF2, rectF1, Matrix.ScaleToFit.CENTER);
        matrix.mapRect(rectF2);
        f2 = rectF1.width() / rectF2.width();
        f1 = rectF1.height() / rectF2.height();
        Matrix.scaleM(this.mMVPMatrix, 0, f2, f1, 1.0F);
        GLES20.glViewport(0, 0, paramInt1, paramInt2);
        GLES20.glUseProgram(this.mProgram);
        checkGlError("glUseProgram");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.mTextureID);
        if (paramInt3 != 1) {
          if (paramInt3 != 2) {
            if (paramInt3 != 3) {
              floatBuffer = this.mRegularTriangleVertices;
            } else {
              floatBuffer = this.mBothFlipTriangleVertices;
            } 
          } else {
            floatBuffer = this.mVerticalFlipTriangleVertices;
          } 
        } else {
          floatBuffer = this.mHorizontalFlipTriangleVertices;
        } 
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.maPositionHandle, 3, 5126, false, 20, floatBuffer);
        checkGlError("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(this.maPositionHandle);
        checkGlError("glEnableVertexAttribArray maPositionHandle");
        floatBuffer.position(3);
        GLES20.glVertexAttribPointer(this.maTextureHandle, 2, 5126, false, 20, floatBuffer);
        checkGlError("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(this.maTextureHandle);
        checkGlError("glEnableVertexAttribArray maTextureHandle");
        GLES20.glUniformMatrix4fv(this.muMVPMatrixHandle, 1, false, this.mMVPMatrix, 0);
        GLES20.glUniformMatrix4fv(this.muSTMatrixHandle, 1, false, this.mSTMatrix, 0);
        GLES20.glDrawArrays(5, 0, 4);
        checkGlDrawError("glDrawArrays");
        return;
      } 
      throw new IllegalStateException("Illegal intermediate texture with dimension of 0");
    } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
      throw new IllegalStateException("Surface abandoned, skipping drawFrame...", bufferQueueAbandonedException);
    } 
  }
  
  private void dumpGlTiming() {
    if (this.mPerfMeasurer == null)
      return; 
    File file = new File(Environment.getExternalStorageDirectory(), "CameraLegacy");
    if (!file.exists() && !file.mkdirs()) {
      Log.e(TAG, "Failed to create directory for data dump");
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder(file.getPath());
    stringBuilder.append(File.separator);
    stringBuilder.append("durations_");
    stringBuilder.append(formatTimestamp(System.currentTimeMillis()));
    stringBuilder.append("_S");
    for (EGLSurfaceHolder eGLSurfaceHolder : this.mSurfaces) {
      stringBuilder.append(String.format("_%d_%d", new Object[] { Integer.valueOf(eGLSurfaceHolder.width), Integer.valueOf(eGLSurfaceHolder.height) }));
    } 
    stringBuilder.append("_C");
    for (EGLSurfaceHolder eGLSurfaceHolder : this.mConversionSurfaces) {
      stringBuilder.append(String.format("_%d_%d", new Object[] { Integer.valueOf(eGLSurfaceHolder.width), Integer.valueOf(eGLSurfaceHolder.height) }));
    } 
    stringBuilder.append(".txt");
    this.mPerfMeasurer.dumpPerformanceData(stringBuilder.toString());
  }
  
  private void endGlTiming() {
    PerfMeasurement perfMeasurement = this.mPerfMeasurer;
    if (perfMeasurement == null)
      return; 
    perfMeasurement.stopTimer();
  }
  
  private static String formatTimestamp(long paramLong) {
    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(paramLong), ZoneId.systemDefault());
    return LOG_NAME_TIME_FORMATTER.format(localDateTime);
  }
  
  private int getTextureId() {
    return this.mTextureID;
  }
  
  private void initializeGLState() {
    int i = createProgram("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
    this.mProgram = i;
    if (i != 0) {
      this.maPositionHandle = GLES20.glGetAttribLocation(i, "aPosition");
      checkGlError("glGetAttribLocation aPosition");
      if (this.maPositionHandle != -1) {
        this.maTextureHandle = GLES20.glGetAttribLocation(this.mProgram, "aTextureCoord");
        checkGlError("glGetAttribLocation aTextureCoord");
        if (this.maTextureHandle != -1) {
          this.muMVPMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, "uMVPMatrix");
          checkGlError("glGetUniformLocation uMVPMatrix");
          if (this.muMVPMatrixHandle != -1) {
            this.muSTMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, "uSTMatrix");
            checkGlError("glGetUniformLocation uSTMatrix");
            if (this.muSTMatrixHandle != -1) {
              int[] arrayOfInt = new int[1];
              GLES20.glGenTextures(1, arrayOfInt, 0);
              i = arrayOfInt[0];
              this.mTextureID = i;
              GLES20.glBindTexture(36197, i);
              checkGlError("glBindTexture mTextureID");
              GLES20.glTexParameterf(36197, 10241, 9728.0F);
              GLES20.glTexParameterf(36197, 10240, 9729.0F);
              GLES20.glTexParameteri(36197, 10242, 33071);
              GLES20.glTexParameteri(36197, 10243, 33071);
              checkGlError("glTexParameter");
              return;
            } 
            throw new IllegalStateException("Could not get attrib location for uSTMatrix");
          } 
          throw new IllegalStateException("Could not get attrib location for uMVPMatrix");
        } 
        throw new IllegalStateException("Could not get attrib location for aTextureCoord");
      } 
      throw new IllegalStateException("Could not get attrib location for aPosition");
    } 
    throw new IllegalStateException("failed creating program");
  }
  
  private int loadShader(int paramInt, String paramString) {
    int i = GLES20.glCreateShader(paramInt);
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("glCreateShader type=");
    stringBuilder2.append(paramInt);
    checkGlError(stringBuilder2.toString());
    GLES20.glShaderSource(i, paramString);
    GLES20.glCompileShader(i);
    int[] arrayOfInt = new int[1];
    GLES20.glGetShaderiv(i, 35713, arrayOfInt, 0);
    if (arrayOfInt[0] != 0)
      return i; 
    String str1 = TAG;
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Could not compile shader ");
    stringBuilder2.append(paramInt);
    stringBuilder2.append(":");
    Log.e(str1, stringBuilder2.toString());
    String str2 = TAG;
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(" ");
    stringBuilder1.append(GLES20.glGetShaderInfoLog(i));
    Log.e(str2, stringBuilder1.toString());
    GLES20.glDeleteShader(i);
    stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Could not compile shader ");
    stringBuilder1.append(paramInt);
    throw new IllegalStateException(stringBuilder1.toString());
  }
  
  private void makeCurrent(EGLSurface paramEGLSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    EGL14.eglMakeCurrent(this.mEGLDisplay, paramEGLSurface, paramEGLSurface, this.mEGLContext);
    checkEglDrawError("makeCurrent");
  }
  
  private void releaseEGLContext() {
    if (this.mEGLDisplay != EGL14.EGL_NO_DISPLAY) {
      EGL14.eglMakeCurrent(this.mEGLDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
      dumpGlTiming();
      List<EGLSurfaceHolder> list = this.mSurfaces;
      if (list != null)
        for (EGLSurfaceHolder eGLSurfaceHolder : list) {
          if (eGLSurfaceHolder.eglSurface != null)
            EGL14.eglDestroySurface(this.mEGLDisplay, eGLSurfaceHolder.eglSurface); 
        }  
      list = this.mConversionSurfaces;
      if (list != null)
        for (EGLSurfaceHolder eGLSurfaceHolder : list) {
          if (eGLSurfaceHolder.eglSurface != null)
            EGL14.eglDestroySurface(this.mEGLDisplay, eGLSurfaceHolder.eglSurface); 
        }  
      EGL14.eglDestroyContext(this.mEGLDisplay, this.mEGLContext);
      EGL14.eglReleaseThread();
      EGL14.eglTerminate(this.mEGLDisplay);
    } 
    this.mConfigs = null;
    this.mEGLDisplay = EGL14.EGL_NO_DISPLAY;
    this.mEGLContext = EGL14.EGL_NO_CONTEXT;
    clearState();
  }
  
  private void setupGlTiming() {
    if (PerfMeasurement.isGlTimingSupported()) {
      Log.d(TAG, "Enabling GL performance measurement");
      this.mPerfMeasurer = new PerfMeasurement();
    } else {
      Log.d(TAG, "GL performance measurement not supported on this device");
      this.mPerfMeasurer = null;
    } 
  }
  
  private boolean swapBuffers(EGLSurface paramEGLSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    boolean bool = EGL14.eglSwapBuffers(this.mEGLDisplay, paramEGLSurface);
    int i = EGL14.eglGetError();
    if (i != 12288) {
      if (i != 12299 && i != 12301) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("swapBuffers: EGL error: 0x");
        stringBuilder.append(Integer.toHexString(i));
        throw new IllegalStateException(stringBuilder.toString());
      } 
      throw new LegacyExceptionUtils.BufferQueueAbandonedException();
    } 
    return bool;
  }
  
  public void cleanupEGLContext() {
    releaseEGLContext();
  }
  
  public void configureSurfaces(Collection<Pair<Surface, Size>> paramCollection) {
    releaseEGLContext();
    if (paramCollection == null || paramCollection.size() == 0) {
      Log.w(TAG, "No output surfaces configured for GL drawing.");
      return;
    } 
    for (Pair<Surface, Size> pair : paramCollection) {
      Surface surface = (Surface)pair.first;
      Size size = (Size)pair.second;
      try {
        EGLSurfaceHolder eGLSurfaceHolder = new EGLSurfaceHolder();
        this(this);
        eGLSurfaceHolder.surface = surface;
        eGLSurfaceHolder.width = size.getWidth();
        eGLSurfaceHolder.height = size.getHeight();
        if (LegacyCameraDevice.needsConversion(surface)) {
          this.mConversionSurfaces.add(eGLSurfaceHolder);
          LegacyCameraDevice.connectSurface(surface);
          continue;
        } 
        this.mSurfaces.add(eGLSurfaceHolder);
      } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
        Log.w(TAG, "Surface abandoned, skipping configuration... ", (Throwable)bufferQueueAbandonedException);
      } 
    } 
    configureEGLContext();
    if (this.mSurfaces.size() > 0)
      configureEGLOutputSurfaces(this.mSurfaces); 
    if (this.mConversionSurfaces.size() > 0)
      configureEGLPbufferSurfaces(this.mConversionSurfaces); 
    try {
      EGLSurface eGLSurface;
      if (this.mSurfaces.size() > 0) {
        eGLSurface = ((EGLSurfaceHolder)this.mSurfaces.get(0)).eglSurface;
      } else {
        eGLSurface = ((EGLSurfaceHolder)this.mConversionSurfaces.get(0)).eglSurface;
      } 
      makeCurrent(eGLSurface);
    } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
      Log.w(TAG, "Surface abandoned, skipping configuration... ", (Throwable)bufferQueueAbandonedException);
    } 
    initializeGLState();
    this.mSurfaceTexture = new SurfaceTexture(getTextureId());
    if (SystemProperties.getBoolean("persist.camera.legacy_perf", false))
      setupGlTiming(); 
  }
  
  public void drawIntoSurfaces(CaptureCollector paramCaptureCollector) {
    Collection<Surface> collection1;
    List<EGLSurfaceHolder> list = this.mSurfaces;
    if (list == null || list.size() == 0) {
      list = this.mConversionSurfaces;
      if (list == null || list.size() == 0)
        return; 
    } 
    boolean bool = paramCaptureCollector.hasPendingPreviewCaptures();
    checkGlError("before updateTexImage");
    if (bool)
      beginGlTiming(); 
    this.mSurfaceTexture.updateTexImage();
    long l = this.mSurfaceTexture.getTimestamp();
    Pair<RequestHolder, Long> pair = paramCaptureCollector.previewCaptured(l);
    if (pair == null) {
      if (bool)
        endGlTiming(); 
      return;
    } 
    RequestHolder requestHolder = (RequestHolder)pair.first;
    Collection<Surface> collection2 = requestHolder.getHolderTargets();
    if (bool)
      addGlTimestamp(l); 
    list = new ArrayList<>();
    try {
      collection2 = (Collection)LegacyCameraDevice.getSurfaceIds(collection2);
      collection1 = collection2;
    } catch (BufferQueueAbandonedException bufferQueueAbandonedException1) {
      Log.w(TAG, "Surface abandoned, dropping frame. ", (Throwable)bufferQueueAbandonedException1);
      requestHolder.setOutputAbandoned();
    } 
    for (EGLSurfaceHolder eGLSurfaceHolder : this.mSurfaces) {
      if (LegacyCameraDevice.containsSurfaceId(eGLSurfaceHolder.surface, (Collection)collection1))
        try {
          boolean bool1;
          LegacyCameraDevice.setSurfaceDimens(eGLSurfaceHolder.surface, eGLSurfaceHolder.width, eGLSurfaceHolder.height);
          makeCurrent(eGLSurfaceHolder.eglSurface);
          LegacyCameraDevice.setNextTimestamp(eGLSurfaceHolder.surface, ((Long)pair.second).longValue());
          SurfaceTexture surfaceTexture = this.mSurfaceTexture;
          int i = eGLSurfaceHolder.width;
          int j = eGLSurfaceHolder.height;
          if (this.mFacing == 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          drawFrame(surfaceTexture, i, j, bool1);
          swapBuffers(eGLSurfaceHolder.eglSurface);
        } catch (BufferQueueAbandonedException bufferQueueAbandonedException1) {
          Log.w(TAG, "Surface abandoned, dropping frame. ", (Throwable)bufferQueueAbandonedException1);
          requestHolder.setOutputAbandoned();
        }  
    } 
    for (EGLSurfaceHolder eGLSurfaceHolder : this.mConversionSurfaces) {
      if (LegacyCameraDevice.containsSurfaceId(eGLSurfaceHolder.surface, (Collection)collection1))
        try {
          int k;
          makeCurrent(eGLSurfaceHolder.eglSurface);
          SurfaceTexture surfaceTexture = this.mSurfaceTexture;
          int i = eGLSurfaceHolder.width;
          int j = eGLSurfaceHolder.height;
          if (this.mFacing == 0) {
            k = 3;
          } else {
            k = 2;
          } 
          drawFrame(surfaceTexture, i, j, k);
          this.mPBufferPixels.clear();
          GLES20.glReadPixels(0, 0, eGLSurfaceHolder.width, eGLSurfaceHolder.height, 6408, 5121, this.mPBufferPixels);
          checkGlError("glReadPixels");
          try {
            k = LegacyCameraDevice.detectSurfaceType(eGLSurfaceHolder.surface);
            LegacyCameraDevice.setSurfaceDimens(eGLSurfaceHolder.surface, eGLSurfaceHolder.width, eGLSurfaceHolder.height);
            LegacyCameraDevice.setNextTimestamp(eGLSurfaceHolder.surface, ((Long)pair.second).longValue());
            LegacyCameraDevice.produceFrame(eGLSurfaceHolder.surface, this.mPBufferPixels.array(), eGLSurfaceHolder.width, eGLSurfaceHolder.height, k);
          } catch (BufferQueueAbandonedException bufferQueueAbandonedException1) {
            Log.w(TAG, "Surface abandoned, dropping frame. ", (Throwable)bufferQueueAbandonedException1);
            requestHolder.setOutputAbandoned();
          } 
        } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
          throw new IllegalStateException("Surface abandoned, skipping drawFrame...", bufferQueueAbandonedException);
        }  
    } 
    bufferQueueAbandonedException.previewProduced();
    if (bool)
      endGlTiming(); 
  }
  
  public void flush() {
    Log.e(TAG, "Flush not yet implemented.");
  }
  
  public SurfaceTexture getSurfaceTexture() {
    return this.mSurfaceTexture;
  }
  
  private class EGLSurfaceHolder {
    EGLSurface eglSurface;
    
    int height;
    
    Surface surface;
    
    int width;
    
    private EGLSurfaceHolder() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/SurfaceTextureRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.filterfw.core;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import java.nio.ByteBuffer;

public class GLFrame extends Frame {
  public static final int EXISTING_FBO_BINDING = 101;
  
  public static final int EXISTING_TEXTURE_BINDING = 100;
  
  public static final int EXTERNAL_TEXTURE = 104;
  
  public static final int NEW_FBO_BINDING = 103;
  
  public static final int NEW_TEXTURE_BINDING = 102;
  
  private int glFrameId = -1;
  
  private GLEnvironment mGLEnvironment;
  
  private boolean mOwnsTexture = true;
  
  static {
    System.loadLibrary("filterfw");
  }
  
  GLFrame(FrameFormat paramFrameFormat, FrameManager paramFrameManager) {
    super(paramFrameFormat, paramFrameManager);
  }
  
  GLFrame(FrameFormat paramFrameFormat, FrameManager paramFrameManager, int paramInt, long paramLong) {
    super(paramFrameFormat, paramFrameManager, paramInt, paramLong);
  }
  
  private void assertGLEnvValid() {
    if (!this.mGLEnvironment.isContextActive()) {
      if (GLEnvironment.isAnyContextActive()) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Attempting to access ");
        stringBuilder1.append(this);
        stringBuilder1.append(" with foreign GL context active!");
        throw new RuntimeException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Attempting to access ");
      stringBuilder.append(this);
      stringBuilder.append(" with no GL context  active!");
      throw new RuntimeException(stringBuilder.toString());
    } 
  }
  
  private native boolean generateNativeMipMap();
  
  private native boolean getNativeBitmap(Bitmap paramBitmap);
  
  private native byte[] getNativeData();
  
  private native int getNativeFboId();
  
  private native float[] getNativeFloats();
  
  private native int[] getNativeInts();
  
  private native int getNativeTextureId();
  
  private void initNew(boolean paramBoolean) {
    if (paramBoolean) {
      if (!nativeAllocateExternal(this.mGLEnvironment))
        throw new RuntimeException("Could not allocate external GL frame!"); 
    } else if (!nativeAllocate(this.mGLEnvironment, getFormat().getWidth(), getFormat().getHeight())) {
      throw new RuntimeException("Could not allocate GL frame!");
    } 
  }
  
  private void initWithFbo(int paramInt) {
    int i = getFormat().getWidth();
    int j = getFormat().getHeight();
    if (nativeAllocateWithFbo(this.mGLEnvironment, paramInt, i, j))
      return; 
    throw new RuntimeException("Could not allocate FBO backed GL frame!");
  }
  
  private void initWithTexture(int paramInt) {
    int i = getFormat().getWidth();
    int j = getFormat().getHeight();
    if (nativeAllocateWithTexture(this.mGLEnvironment, paramInt, i, j)) {
      this.mOwnsTexture = false;
      markReadOnly();
      return;
    } 
    throw new RuntimeException("Could not allocate texture backed GL frame!");
  }
  
  private native boolean nativeAllocate(GLEnvironment paramGLEnvironment, int paramInt1, int paramInt2);
  
  private native boolean nativeAllocateExternal(GLEnvironment paramGLEnvironment);
  
  private native boolean nativeAllocateWithFbo(GLEnvironment paramGLEnvironment, int paramInt1, int paramInt2, int paramInt3);
  
  private native boolean nativeAllocateWithTexture(GLEnvironment paramGLEnvironment, int paramInt1, int paramInt2, int paramInt3);
  
  private native boolean nativeCopyFromGL(GLFrame paramGLFrame);
  
  private native boolean nativeCopyFromNative(NativeFrame paramNativeFrame);
  
  private native boolean nativeDeallocate();
  
  private native boolean nativeDetachTexFromFbo();
  
  private native boolean nativeFocus();
  
  private native boolean nativeReattachTexToFbo();
  
  private native boolean nativeResetParams();
  
  private native boolean setNativeBitmap(Bitmap paramBitmap, int paramInt);
  
  private native boolean setNativeData(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  private native boolean setNativeFloats(float[] paramArrayOffloat);
  
  private native boolean setNativeInts(int[] paramArrayOfint);
  
  private native boolean setNativeTextureParam(int paramInt1, int paramInt2);
  
  private native boolean setNativeViewport(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  void flushGPU(String paramString) {
    StopWatchMap stopWatchMap = GLFrameTimer.get();
    if (stopWatchMap.LOG_MFF_RUNNING_TIMES) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("glFinish ");
      stringBuilder.append(paramString);
      stopWatchMap.start(stringBuilder.toString());
      GLES20.glFinish();
      stringBuilder = new StringBuilder();
      stringBuilder.append("glFinish ");
      stringBuilder.append(paramString);
      stopWatchMap.stop(stringBuilder.toString());
    } 
  }
  
  public void focus() {
    if (nativeFocus())
      return; 
    throw new RuntimeException("Could not focus on GLFrame for drawing!");
  }
  
  public void generateMipMap() {
    assertFrameMutable();
    assertGLEnvValid();
    if (generateNativeMipMap())
      return; 
    throw new RuntimeException("Could not generate mip-map for GL frame!");
  }
  
  public Bitmap getBitmap() {
    assertGLEnvValid();
    flushGPU("getBitmap");
    Bitmap bitmap = Bitmap.createBitmap(getFormat().getWidth(), getFormat().getHeight(), Bitmap.Config.ARGB_8888);
    if (getNativeBitmap(bitmap))
      return bitmap; 
    throw new RuntimeException("Could not get bitmap data from GL frame!");
  }
  
  public ByteBuffer getData() {
    assertGLEnvValid();
    flushGPU("getData");
    return ByteBuffer.wrap(getNativeData());
  }
  
  public int getFboId() {
    return getNativeFboId();
  }
  
  public float[] getFloats() {
    assertGLEnvValid();
    flushGPU("getFloats");
    return getNativeFloats();
  }
  
  public GLEnvironment getGLEnvironment() {
    return this.mGLEnvironment;
  }
  
  public int[] getInts() {
    assertGLEnvValid();
    flushGPU("getInts");
    return getNativeInts();
  }
  
  public Object getObjectValue() {
    assertGLEnvValid();
    return ByteBuffer.wrap(getNativeData());
  }
  
  public int getTextureId() {
    return getNativeTextureId();
  }
  
  protected boolean hasNativeAllocation() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield glFrameId : I
    //   6: istore_1
    //   7: iload_1
    //   8: iconst_m1
    //   9: if_icmpeq -> 17
    //   12: iconst_1
    //   13: istore_2
    //   14: goto -> 19
    //   17: iconst_0
    //   18: istore_2
    //   19: aload_0
    //   20: monitorexit
    //   21: iload_2
    //   22: ireturn
    //   23: astore_3
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_3
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	23	finally
  }
  
  void init(GLEnvironment paramGLEnvironment) {
    FrameFormat frameFormat = getFormat();
    this.mGLEnvironment = paramGLEnvironment;
    if (frameFormat.getBytesPerSample() == 4) {
      if (frameFormat.getDimensionCount() == 2) {
        if (getFormat().getSize() >= 0) {
          int i = getBindingType();
          boolean bool = true;
          if (i == 0) {
            initNew(false);
          } else if (i == 104) {
            initNew(true);
            bool = false;
          } else if (i == 100) {
            initWithTexture((int)getBindingId());
          } else if (i == 101) {
            initWithFbo((int)getBindingId());
          } else if (i == 102) {
            initWithTexture((int)getBindingId());
          } else if (i == 103) {
            initWithFbo((int)getBindingId());
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Attempting to create GL frame with unknown binding type ");
            stringBuilder.append(i);
            stringBuilder.append("!");
            throw new RuntimeException(stringBuilder.toString());
          } 
          setReusable(bool);
          return;
        } 
        throw new IllegalArgumentException("Initializing GL frame with zero size!");
      } 
      throw new IllegalArgumentException("GL frames must be 2-dimensional!");
    } 
    throw new IllegalArgumentException("GL frames must have 4 bytes per sample!");
  }
  
  protected void onFrameFetch() {
    if (!this.mOwnsTexture)
      nativeReattachTexToFbo(); 
  }
  
  protected void onFrameStore() {
    if (!this.mOwnsTexture)
      nativeDetachTexFromFbo(); 
  }
  
  protected void releaseNativeAllocation() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial nativeDeallocate : ()Z
    //   6: pop
    //   7: aload_0
    //   8: iconst_m1
    //   9: putfield glFrameId : I
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	15	finally
  }
  
  protected void reset(FrameFormat paramFrameFormat) {
    if (nativeResetParams()) {
      super.reset(paramFrameFormat);
      return;
    } 
    throw new RuntimeException("Could not reset GLFrame texture parameters!");
  }
  
  public void setBitmap(Bitmap paramBitmap) {
    assertFrameMutable();
    assertGLEnvValid();
    if (getFormat().getWidth() == paramBitmap.getWidth() && getFormat().getHeight() == paramBitmap.getHeight()) {
      paramBitmap = convertBitmapToRGBA(paramBitmap);
      if (setNativeBitmap(paramBitmap, paramBitmap.getByteCount()))
        return; 
      throw new RuntimeException("Could not set GL frame bitmap data!");
    } 
    throw new RuntimeException("Bitmap dimensions do not match GL frame dimensions!");
  }
  
  public void setData(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2) {
    assertFrameMutable();
    assertGLEnvValid();
    byte[] arrayOfByte = paramByteBuffer.array();
    if (getFormat().getSize() == arrayOfByte.length) {
      if (setNativeData(arrayOfByte, paramInt1, paramInt2))
        return; 
      throw new RuntimeException("Could not set GL frame data!");
    } 
    throw new RuntimeException("Data size in setData does not match GL frame size!");
  }
  
  public void setDataFromFrame(Frame paramFrame) {
    assertGLEnvValid();
    if (getFormat().getSize() >= paramFrame.getFormat().getSize()) {
      if (paramFrame instanceof NativeFrame) {
        nativeCopyFromNative((NativeFrame)paramFrame);
      } else if (paramFrame instanceof GLFrame) {
        nativeCopyFromGL((GLFrame)paramFrame);
      } else if (paramFrame instanceof SimpleFrame) {
        setObjectValue(paramFrame.getObjectValue());
      } else {
        super.setDataFromFrame(paramFrame);
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Attempting to assign frame of size ");
    stringBuilder.append(paramFrame.getFormat().getSize());
    stringBuilder.append(" to smaller GL frame of size ");
    stringBuilder.append(getFormat().getSize());
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setFloats(float[] paramArrayOffloat) {
    assertFrameMutable();
    assertGLEnvValid();
    if (setNativeFloats(paramArrayOffloat))
      return; 
    throw new RuntimeException("Could not set int values for GL frame!");
  }
  
  public void setInts(int[] paramArrayOfint) {
    assertFrameMutable();
    assertGLEnvValid();
    if (setNativeInts(paramArrayOfint))
      return; 
    throw new RuntimeException("Could not set int values for GL frame!");
  }
  
  public void setTextureParameter(int paramInt1, int paramInt2) {
    assertFrameMutable();
    assertGLEnvValid();
    if (setNativeTextureParam(paramInt1, paramInt2))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not set texture value ");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" = ");
    stringBuilder.append(paramInt2);
    stringBuilder.append(" for GLFrame!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setViewport(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    assertFrameMutable();
    setNativeViewport(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setViewport(Rect paramRect) {
    assertFrameMutable();
    setNativeViewport(paramRect.left, paramRect.top, paramRect.right - paramRect.left, paramRect.bottom - paramRect.top);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("GLFrame id: ");
    stringBuilder.append(this.glFrameId);
    stringBuilder.append(" (");
    stringBuilder.append(getFormat());
    stringBuilder.append(") with texture ID ");
    stringBuilder.append(getTextureId());
    stringBuilder.append(", FBO ID ");
    stringBuilder.append(getFboId());
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/GLFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
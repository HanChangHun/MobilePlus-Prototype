package android.filterfw.core;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

public class NativeFrame extends Frame {
  private int nativeFrameId;
  
  static {
    System.loadLibrary("filterfw");
  }
  
  NativeFrame(FrameFormat paramFrameFormat, FrameManager paramFrameManager) {
    super(paramFrameFormat, paramFrameManager);
    boolean bool;
    this.nativeFrameId = -1;
    int i = paramFrameFormat.getSize();
    nativeAllocate(i);
    if (i != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    setReusable(bool);
  }
  
  private native boolean getNativeBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2);
  
  private native boolean getNativeBuffer(NativeBuffer paramNativeBuffer);
  
  private native int getNativeCapacity();
  
  private native byte[] getNativeData(int paramInt);
  
  private native float[] getNativeFloats(int paramInt);
  
  private native int[] getNativeInts(int paramInt);
  
  private native boolean nativeAllocate(int paramInt);
  
  private native boolean nativeCopyFromGL(GLFrame paramGLFrame);
  
  private native boolean nativeCopyFromNative(NativeFrame paramNativeFrame);
  
  private native boolean nativeDeallocate();
  
  private static native int nativeFloatSize();
  
  private static native int nativeIntSize();
  
  private native boolean setNativeBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2);
  
  private native boolean setNativeData(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  private native boolean setNativeFloats(float[] paramArrayOffloat);
  
  private native boolean setNativeInts(int[] paramArrayOfint);
  
  public Bitmap getBitmap() {
    if (getFormat().getNumberOfDimensions() == 2) {
      Bitmap bitmap = Bitmap.createBitmap(getFormat().getWidth(), getFormat().getHeight(), Bitmap.Config.ARGB_8888);
      if (getNativeBitmap(bitmap, bitmap.getByteCount(), getFormat().getBytesPerSample()))
        return bitmap; 
      throw new RuntimeException("Could not get bitmap data from native frame!");
    } 
    throw new RuntimeException("Attempting to get Bitmap for non 2-dimensional native frame!");
  }
  
  public int getCapacity() {
    return getNativeCapacity();
  }
  
  public ByteBuffer getData() {
    ByteBuffer byteBuffer;
    byte[] arrayOfByte = getNativeData(getFormat().getSize());
    if (arrayOfByte == null) {
      arrayOfByte = null;
    } else {
      byteBuffer = ByteBuffer.wrap(arrayOfByte);
    } 
    return byteBuffer;
  }
  
  public float[] getFloats() {
    return getNativeFloats(getFormat().getSize());
  }
  
  public int[] getInts() {
    return getNativeInts(getFormat().getSize());
  }
  
  public Object getObjectValue() {
    if (getFormat().getBaseType() != 8)
      return getData(); 
    Class<?> clazz = getFormat().getObjectClass();
    if (clazz != null) {
      if (NativeBuffer.class.isAssignableFrom(clazz))
        try {
          NativeBuffer nativeBuffer = (NativeBuffer)clazz.newInstance();
          if (getNativeBuffer(nativeBuffer)) {
            nativeBuffer.attachToFrame(this);
            return nativeBuffer;
          } 
          throw new RuntimeException("Could not get the native structured data for frame!");
        } catch (Exception exception) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Could not instantiate new structure instance of type '");
          stringBuilder.append(clazz);
          stringBuilder.append("'!");
          throw new RuntimeException(stringBuilder.toString());
        }  
      throw new RuntimeException("NativeFrame object class must be a subclass of NativeBuffer!");
    } 
    throw new RuntimeException("Attempting to get object data from frame that does not specify a structure object class!");
  }
  
  protected boolean hasNativeAllocation() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield nativeFrameId : I
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
  
  protected void releaseNativeAllocation() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial nativeDeallocate : ()Z
    //   6: pop
    //   7: aload_0
    //   8: iconst_m1
    //   9: putfield nativeFrameId : I
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
  
  public void setBitmap(Bitmap paramBitmap) {
    assertFrameMutable();
    if (getFormat().getNumberOfDimensions() == 2) {
      if (getFormat().getWidth() == paramBitmap.getWidth() && getFormat().getHeight() == paramBitmap.getHeight()) {
        paramBitmap = convertBitmapToRGBA(paramBitmap);
        if (setNativeBitmap(paramBitmap, paramBitmap.getByteCount(), getFormat().getBytesPerSample()))
          return; 
        throw new RuntimeException("Could not set native frame bitmap data!");
      } 
      throw new RuntimeException("Bitmap dimensions do not match native frame dimensions!");
    } 
    throw new RuntimeException("Attempting to set Bitmap for non 2-dimensional native frame!");
  }
  
  public void setData(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2) {
    StringBuilder stringBuilder1;
    assertFrameMutable();
    byte[] arrayOfByte = paramByteBuffer.array();
    if (paramInt2 + paramInt1 <= paramByteBuffer.limit()) {
      if (getFormat().getSize() == paramInt2) {
        if (setNativeData(arrayOfByte, paramInt1, paramInt2))
          return; 
        throw new RuntimeException("Could not set native frame data!");
      } 
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Data size in setData does not match native frame size: Frame size is ");
      stringBuilder1.append(getFormat().getSize());
      stringBuilder1.append(" bytes, but ");
      stringBuilder1.append(paramInt2);
      stringBuilder1.append(" bytes given!");
      throw new RuntimeException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Offset and length exceed buffer size in native setData: ");
    stringBuilder2.append(paramInt2 + paramInt1);
    stringBuilder2.append(" bytes given, but only ");
    stringBuilder2.append(stringBuilder1.limit());
    stringBuilder2.append(" bytes available!");
    throw new RuntimeException(stringBuilder2.toString());
  }
  
  public void setDataFromFrame(Frame paramFrame) {
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
    stringBuilder.append(" to smaller native frame of size ");
    stringBuilder.append(getFormat().getSize());
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setFloats(float[] paramArrayOffloat) {
    assertFrameMutable();
    if (paramArrayOffloat.length * nativeFloatSize() <= getFormat().getSize()) {
      if (setNativeFloats(paramArrayOffloat))
        return; 
      throw new RuntimeException("Could not set int values for native frame!");
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("NativeFrame cannot hold ");
    stringBuilder.append(paramArrayOffloat.length);
    stringBuilder.append(" floats. (Can only hold ");
    stringBuilder.append(getFormat().getSize() / nativeFloatSize());
    stringBuilder.append(" floats).");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setInts(int[] paramArrayOfint) {
    assertFrameMutable();
    if (paramArrayOfint.length * nativeIntSize() <= getFormat().getSize()) {
      if (setNativeInts(paramArrayOfint))
        return; 
      throw new RuntimeException("Could not set int values for native frame!");
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("NativeFrame cannot hold ");
    stringBuilder.append(paramArrayOfint.length);
    stringBuilder.append(" integers. (Can only hold ");
    stringBuilder.append(getFormat().getSize() / nativeIntSize());
    stringBuilder.append(" integers).");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("NativeFrame id: ");
    stringBuilder.append(this.nativeFrameId);
    stringBuilder.append(" (");
    stringBuilder.append(getFormat());
    stringBuilder.append(") of size ");
    stringBuilder.append(getCapacity());
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/NativeFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
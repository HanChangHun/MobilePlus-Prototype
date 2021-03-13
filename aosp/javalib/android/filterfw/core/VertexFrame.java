package android.filterfw.core;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

public class VertexFrame extends Frame {
  private int vertexFrameId = -1;
  
  static {
    System.loadLibrary("filterfw");
  }
  
  VertexFrame(FrameFormat paramFrameFormat, FrameManager paramFrameManager) {
    super(paramFrameFormat, paramFrameManager);
    if (getFormat().getSize() > 0) {
      if (nativeAllocate(getFormat().getSize()))
        return; 
      throw new RuntimeException("Could not allocate vertex frame!");
    } 
    throw new IllegalArgumentException("Initializing vertex frame with zero size!");
  }
  
  private native int getNativeVboId();
  
  private native boolean nativeAllocate(int paramInt);
  
  private native boolean nativeDeallocate();
  
  private native boolean setNativeData(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  private native boolean setNativeFloats(float[] paramArrayOffloat);
  
  private native boolean setNativeInts(int[] paramArrayOfint);
  
  public Bitmap getBitmap() {
    throw new RuntimeException("Vertex frames do not support reading data!");
  }
  
  public ByteBuffer getData() {
    throw new RuntimeException("Vertex frames do not support reading data!");
  }
  
  public float[] getFloats() {
    throw new RuntimeException("Vertex frames do not support reading data!");
  }
  
  public int[] getInts() {
    throw new RuntimeException("Vertex frames do not support reading data!");
  }
  
  public Object getObjectValue() {
    throw new RuntimeException("Vertex frames do not support reading data!");
  }
  
  public int getVboId() {
    return getNativeVboId();
  }
  
  protected boolean hasNativeAllocation() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield vertexFrameId : I
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
    //   9: putfield vertexFrameId : I
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
    throw new RuntimeException("Unsupported: Cannot set vertex frame bitmap value!");
  }
  
  public void setData(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2) {
    assertFrameMutable();
    byte[] arrayOfByte = paramByteBuffer.array();
    if (getFormat().getSize() == arrayOfByte.length) {
      if (setNativeData(arrayOfByte, paramInt1, paramInt2))
        return; 
      throw new RuntimeException("Could not set vertex frame data!");
    } 
    throw new RuntimeException("Data size in setData does not match vertex frame size!");
  }
  
  public void setDataFromFrame(Frame paramFrame) {
    super.setDataFromFrame(paramFrame);
  }
  
  public void setFloats(float[] paramArrayOffloat) {
    assertFrameMutable();
    if (setNativeFloats(paramArrayOffloat))
      return; 
    throw new RuntimeException("Could not set int values for vertex frame!");
  }
  
  public void setInts(int[] paramArrayOfint) {
    assertFrameMutable();
    if (setNativeInts(paramArrayOfint))
      return; 
    throw new RuntimeException("Could not set int values for vertex frame!");
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("VertexFrame (");
    stringBuilder.append(getFormat());
    stringBuilder.append(") with VBO ID ");
    stringBuilder.append(getVboId());
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/VertexFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
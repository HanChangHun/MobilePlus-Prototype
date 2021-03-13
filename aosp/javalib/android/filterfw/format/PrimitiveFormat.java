package android.filterfw.format;

import android.filterfw.core.MutableFrameFormat;

public class PrimitiveFormat {
  public static MutableFrameFormat createByteFormat(int paramInt) {
    return createFormat(2, paramInt);
  }
  
  public static MutableFrameFormat createByteFormat(int paramInt1, int paramInt2) {
    return createFormat(2, paramInt1, paramInt2);
  }
  
  public static MutableFrameFormat createDoubleFormat(int paramInt) {
    return createFormat(6, paramInt);
  }
  
  public static MutableFrameFormat createDoubleFormat(int paramInt1, int paramInt2) {
    return createFormat(6, paramInt1, paramInt2);
  }
  
  public static MutableFrameFormat createFloatFormat(int paramInt) {
    return createFormat(5, paramInt);
  }
  
  public static MutableFrameFormat createFloatFormat(int paramInt1, int paramInt2) {
    return createFormat(5, paramInt1, paramInt2);
  }
  
  private static MutableFrameFormat createFormat(int paramInt1, int paramInt2) {
    MutableFrameFormat mutableFrameFormat = new MutableFrameFormat(paramInt1, paramInt2);
    mutableFrameFormat.setDimensionCount(1);
    return mutableFrameFormat;
  }
  
  private static MutableFrameFormat createFormat(int paramInt1, int paramInt2, int paramInt3) {
    MutableFrameFormat mutableFrameFormat = new MutableFrameFormat(paramInt1, paramInt3);
    mutableFrameFormat.setDimensions(paramInt2);
    return mutableFrameFormat;
  }
  
  public static MutableFrameFormat createInt16Format(int paramInt) {
    return createFormat(3, paramInt);
  }
  
  public static MutableFrameFormat createInt16Format(int paramInt1, int paramInt2) {
    return createFormat(3, paramInt1, paramInt2);
  }
  
  public static MutableFrameFormat createInt32Format(int paramInt) {
    return createFormat(4, paramInt);
  }
  
  public static MutableFrameFormat createInt32Format(int paramInt1, int paramInt2) {
    return createFormat(4, paramInt1, paramInt2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/format/PrimitiveFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
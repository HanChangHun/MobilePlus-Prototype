package android.graphics.text;

import libcore.util.NativeAllocationRegistry;

public class Result {
  private static final int END_HYPHEN_MASK = 7;
  
  private static final int HYPHEN_MASK = 255;
  
  private static final int START_HYPHEN_BITS_SHIFT = 3;
  
  private static final int START_HYPHEN_MASK = 24;
  
  private static final int TAB_MASK = 536870912;
  
  private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(Result.class.getClassLoader(), LineBreaker.access$100());
  
  private final long mPtr;
  
  private Result(long paramLong) {
    this.mPtr = paramLong;
    sRegistry.registerNativeAllocation(this, paramLong);
  }
  
  public int getEndLineHyphenEdit(int paramInt) {
    return LineBreaker.access$700(this.mPtr, paramInt) & 0x7;
  }
  
  public float getLineAscent(int paramInt) {
    return LineBreaker.access$500(this.mPtr, paramInt);
  }
  
  public int getLineBreakOffset(int paramInt) {
    return LineBreaker.access$300(this.mPtr, paramInt);
  }
  
  public int getLineCount() {
    return LineBreaker.access$200(this.mPtr);
  }
  
  public float getLineDescent(int paramInt) {
    return LineBreaker.access$600(this.mPtr, paramInt);
  }
  
  public float getLineWidth(int paramInt) {
    return LineBreaker.access$400(this.mPtr, paramInt);
  }
  
  public int getStartLineHyphenEdit(int paramInt) {
    return (LineBreaker.access$700(this.mPtr, paramInt) & 0x18) >> 3;
  }
  
  public boolean hasLineTab(int paramInt) {
    boolean bool;
    if ((LineBreaker.access$700(this.mPtr, paramInt) & 0x20000000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/text/LineBreaker$Result.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
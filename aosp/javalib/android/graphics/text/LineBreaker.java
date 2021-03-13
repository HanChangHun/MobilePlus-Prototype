package android.graphics.text;

import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import libcore.util.NativeAllocationRegistry;

public class LineBreaker {
  public static final int BREAK_STRATEGY_BALANCED = 2;
  
  public static final int BREAK_STRATEGY_HIGH_QUALITY = 1;
  
  public static final int BREAK_STRATEGY_SIMPLE = 0;
  
  public static final int HYPHENATION_FREQUENCY_FULL = 2;
  
  public static final int HYPHENATION_FREQUENCY_NONE = 0;
  
  public static final int HYPHENATION_FREQUENCY_NORMAL = 1;
  
  public static final int JUSTIFICATION_MODE_INTER_WORD = 1;
  
  public static final int JUSTIFICATION_MODE_NONE = 0;
  
  private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(LineBreaker.class.getClassLoader(), nGetReleaseFunc());
  
  private final long mNativePtr;
  
  private LineBreaker(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfint) {
    boolean bool = true;
    if (paramInt3 != 1)
      bool = false; 
    long l = nInit(paramInt1, paramInt2, bool, paramArrayOfint);
    this.mNativePtr = l;
    sRegistry.registerNativeAllocation(this, l);
  }
  
  private static native long nComputeLineBreaks(long paramLong1, char[] paramArrayOfchar, long paramLong2, int paramInt1, float paramFloat1, int paramInt2, float paramFloat2, float[] paramArrayOffloat, float paramFloat3, int paramInt3);
  
  @CriticalNative
  private static native float nGetLineAscent(long paramLong, int paramInt);
  
  @CriticalNative
  private static native int nGetLineBreakOffset(long paramLong, int paramInt);
  
  @CriticalNative
  private static native int nGetLineCount(long paramLong);
  
  @CriticalNative
  private static native float nGetLineDescent(long paramLong, int paramInt);
  
  @CriticalNative
  private static native int nGetLineFlag(long paramLong, int paramInt);
  
  @CriticalNative
  private static native float nGetLineWidth(long paramLong, int paramInt);
  
  @CriticalNative
  private static native long nGetReleaseFunc();
  
  @CriticalNative
  private static native long nGetReleaseResultFunc();
  
  @FastNative
  private static native long nInit(int paramInt1, int paramInt2, boolean paramBoolean, int[] paramArrayOfint);
  
  public Result computeLineBreaks(MeasuredText paramMeasuredText, ParagraphConstraints paramParagraphConstraints, int paramInt) {
    return new Result(nComputeLineBreaks(this.mNativePtr, paramMeasuredText.getChars(), paramMeasuredText.getNativePtr(), (paramMeasuredText.getChars()).length, paramParagraphConstraints.mFirstWidth, paramParagraphConstraints.mFirstWidthLineCount, paramParagraphConstraints.mWidth, paramParagraphConstraints.mVariableTabStops, paramParagraphConstraints.mDefaultTabStop, paramInt));
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface BreakStrategy {}
  
  public static final class Builder {
    private int mBreakStrategy = 0;
    
    private int mHyphenationFrequency = 0;
    
    private int[] mIndents = null;
    
    private int mJustificationMode = 0;
    
    public LineBreaker build() {
      return new LineBreaker(this.mBreakStrategy, this.mHyphenationFrequency, this.mJustificationMode, this.mIndents);
    }
    
    public Builder setBreakStrategy(int param1Int) {
      this.mBreakStrategy = param1Int;
      return this;
    }
    
    public Builder setHyphenationFrequency(int param1Int) {
      this.mHyphenationFrequency = param1Int;
      return this;
    }
    
    public Builder setIndents(int[] param1ArrayOfint) {
      this.mIndents = param1ArrayOfint;
      return this;
    }
    
    public Builder setJustificationMode(int param1Int) {
      this.mJustificationMode = param1Int;
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface HyphenationFrequency {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface JustificationMode {}
  
  public static class ParagraphConstraints {
    private float mDefaultTabStop = 0.0F;
    
    private float mFirstWidth = 0.0F;
    
    private int mFirstWidthLineCount = 0;
    
    private float[] mVariableTabStops = null;
    
    private float mWidth = 0.0F;
    
    public float getDefaultTabStop() {
      return this.mDefaultTabStop;
    }
    
    public float getFirstWidth() {
      return this.mFirstWidth;
    }
    
    public int getFirstWidthLineCount() {
      return this.mFirstWidthLineCount;
    }
    
    public float[] getTabStops() {
      return this.mVariableTabStops;
    }
    
    public float getWidth() {
      return this.mWidth;
    }
    
    public void setIndent(float param1Float, int param1Int) {
      this.mFirstWidth = param1Float;
      this.mFirstWidthLineCount = param1Int;
    }
    
    public void setTabStops(float[] param1ArrayOffloat, float param1Float) {
      this.mVariableTabStops = param1ArrayOffloat;
      this.mDefaultTabStop = param1Float;
    }
    
    public void setWidth(float param1Float) {
      this.mWidth = param1Float;
    }
  }
  
  public static class Result {
    private static final int END_HYPHEN_MASK = 7;
    
    private static final int HYPHEN_MASK = 255;
    
    private static final int START_HYPHEN_BITS_SHIFT = 3;
    
    private static final int START_HYPHEN_MASK = 24;
    
    private static final int TAB_MASK = 536870912;
    
    private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(Result.class.getClassLoader(), LineBreaker.nGetReleaseResultFunc());
    
    private final long mPtr;
    
    private Result(long param1Long) {
      this.mPtr = param1Long;
      sRegistry.registerNativeAllocation(this, param1Long);
    }
    
    public int getEndLineHyphenEdit(int param1Int) {
      return LineBreaker.nGetLineFlag(this.mPtr, param1Int) & 0x7;
    }
    
    public float getLineAscent(int param1Int) {
      return LineBreaker.nGetLineAscent(this.mPtr, param1Int);
    }
    
    public int getLineBreakOffset(int param1Int) {
      return LineBreaker.nGetLineBreakOffset(this.mPtr, param1Int);
    }
    
    public int getLineCount() {
      return LineBreaker.nGetLineCount(this.mPtr);
    }
    
    public float getLineDescent(int param1Int) {
      return LineBreaker.nGetLineDescent(this.mPtr, param1Int);
    }
    
    public float getLineWidth(int param1Int) {
      return LineBreaker.nGetLineWidth(this.mPtr, param1Int);
    }
    
    public int getStartLineHyphenEdit(int param1Int) {
      return (LineBreaker.nGetLineFlag(this.mPtr, param1Int) & 0x18) >> 3;
    }
    
    public boolean hasLineTab(int param1Int) {
      boolean bool;
      if ((LineBreaker.nGetLineFlag(this.mPtr, param1Int) & 0x20000000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/text/LineBreaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
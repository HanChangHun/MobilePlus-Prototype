package android.graphics.text;

import android.graphics.Paint;
import com.android.internal.util.Preconditions;
import libcore.util.NativeAllocationRegistry;

public final class Builder {
  private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(MeasuredText.class.getClassLoader(), MeasuredText.access$000());
  
  private boolean mComputeHyphenation = false;
  
  private boolean mComputeLayout = true;
  
  private int mCurrentOffset = 0;
  
  private MeasuredText mHintMt = null;
  
  private long mNativePtr;
  
  private final char[] mText;
  
  public Builder(MeasuredText paramMeasuredText) {
    Preconditions.checkNotNull(paramMeasuredText);
    this.mText = MeasuredText.access$100(paramMeasuredText);
    this.mNativePtr = nInitBuilder();
    if (MeasuredText.access$200(paramMeasuredText)) {
      this.mComputeHyphenation = MeasuredText.access$300(paramMeasuredText);
      this.mComputeLayout = MeasuredText.access$200(paramMeasuredText);
      this.mHintMt = paramMeasuredText;
      return;
    } 
    throw new IllegalArgumentException("The input MeasuredText must not be created with setComputeLayout(false).");
  }
  
  public Builder(char[] paramArrayOfchar) {
    Preconditions.checkNotNull(paramArrayOfchar);
    this.mText = paramArrayOfchar;
    this.mNativePtr = nInitBuilder();
  }
  
  private void ensureNativePtrNoReuse() {
    if (this.mNativePtr != 0L)
      return; 
    throw new IllegalStateException("Builder can not be reused.");
  }
  
  private static native void nAddReplacementRun(long paramLong1, long paramLong2, int paramInt1, int paramInt2, float paramFloat);
  
  private static native void nAddStyleRun(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean);
  
  private static native long nBuildMeasuredText(long paramLong1, long paramLong2, char[] paramArrayOfchar, boolean paramBoolean1, boolean paramBoolean2);
  
  private static native void nFreeBuilder(long paramLong);
  
  private static native long nInitBuilder();
  
  public Builder appendReplacementRun(Paint paramPaint, int paramInt, float paramFloat) {
    boolean bool2;
    boolean bool1 = true;
    if (paramInt > 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2, "length can not be negative");
    paramInt = this.mCurrentOffset + paramInt;
    if (paramInt <= this.mText.length) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2, "Replacement exceeds the text length");
    nAddReplacementRun(this.mNativePtr, paramPaint.getNativeInstance(), this.mCurrentOffset, paramInt, paramFloat);
    this.mCurrentOffset = paramInt;
    return this;
  }
  
  public Builder appendStyleRun(Paint paramPaint, int paramInt, boolean paramBoolean) {
    boolean bool2;
    Preconditions.checkNotNull(paramPaint);
    boolean bool1 = true;
    if (paramInt > 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2, "length can not be negative");
    paramInt = this.mCurrentOffset + paramInt;
    if (paramInt <= this.mText.length) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2, "Style exceeds the text length");
    nAddStyleRun(this.mNativePtr, paramPaint.getNativeInstance(), this.mCurrentOffset, paramInt, paramBoolean);
    this.mCurrentOffset = paramInt;
    return this;
  }
  
  public MeasuredText build() {
    ensureNativePtrNoReuse();
    if (this.mCurrentOffset == this.mText.length) {
      MeasuredText measuredText = this.mHintMt;
      if (measuredText == null || MeasuredText.access$300(measuredText) == this.mComputeHyphenation)
        try {
          if (this.mHintMt == null) {
            l = 0L;
          } else {
            l = this.mHintMt.getNativePtr();
          } 
          long l = nBuildMeasuredText(this.mNativePtr, l, this.mText, this.mComputeHyphenation, this.mComputeLayout);
          measuredText = new MeasuredText();
          this(l, this.mText, this.mComputeHyphenation, this.mComputeLayout);
          sRegistry.registerNativeAllocation(measuredText, l);
          return measuredText;
        } finally {
          nFreeBuilder(this.mNativePtr);
          this.mNativePtr = 0L;
        }  
      throw new IllegalArgumentException("The hyphenation configuration is different from given hint MeasuredText");
    } 
    throw new IllegalStateException("Style info has not been provided for all text.");
  }
  
  public Builder setComputeHyphenation(boolean paramBoolean) {
    this.mComputeHyphenation = paramBoolean;
    return this;
  }
  
  public Builder setComputeLayout(boolean paramBoolean) {
    this.mComputeLayout = paramBoolean;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/text/MeasuredText$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
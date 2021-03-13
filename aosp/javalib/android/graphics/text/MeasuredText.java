package android.graphics.text;

import android.graphics.Paint;
import android.graphics.Rect;
import com.android.internal.util.Preconditions;
import dalvik.annotation.optimization.CriticalNative;
import libcore.util.NativeAllocationRegistry;

public class MeasuredText {
  private char[] mChars;
  
  private boolean mComputeHyphenation;
  
  private boolean mComputeLayout;
  
  private long mNativePtr;
  
  private MeasuredText(long paramLong, char[] paramArrayOfchar, boolean paramBoolean1, boolean paramBoolean2) {
    this.mNativePtr = paramLong;
    this.mChars = paramArrayOfchar;
    this.mComputeHyphenation = paramBoolean1;
    this.mComputeLayout = paramBoolean2;
  }
  
  private static native void nGetBounds(long paramLong, char[] paramArrayOfchar, int paramInt1, int paramInt2, Rect paramRect);
  
  @CriticalNative
  private static native float nGetCharWidthAt(long paramLong, int paramInt);
  
  @CriticalNative
  private static native int nGetMemoryUsage(long paramLong);
  
  @CriticalNative
  private static native long nGetReleaseFunc();
  
  @CriticalNative
  private static native float nGetWidth(long paramLong, int paramInt1, int paramInt2);
  
  public void getBounds(int paramInt1, int paramInt2, Rect paramRect) {
    boolean bool2;
    boolean bool1 = true;
    if (paramInt1 >= 0 && paramInt1 <= this.mChars.length) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("start(");
    stringBuilder.append(paramInt1);
    stringBuilder.append(") must be 0 <= start <= ");
    stringBuilder.append(this.mChars.length);
    Preconditions.checkArgument(bool2, stringBuilder.toString());
    if (paramInt2 >= 0 && paramInt2 <= this.mChars.length) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    stringBuilder = new StringBuilder();
    stringBuilder.append("end(");
    stringBuilder.append(paramInt2);
    stringBuilder.append(") must be 0 <= end <= ");
    stringBuilder.append(this.mChars.length);
    Preconditions.checkArgument(bool2, stringBuilder.toString());
    if (paramInt1 <= paramInt2) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    stringBuilder = new StringBuilder();
    stringBuilder.append("start(");
    stringBuilder.append(paramInt1);
    stringBuilder.append(") is larger than end(");
    stringBuilder.append(paramInt2);
    stringBuilder.append(")");
    Preconditions.checkArgument(bool2, stringBuilder.toString());
    Preconditions.checkNotNull(paramRect);
    nGetBounds(this.mNativePtr, this.mChars, paramInt1, paramInt2, paramRect);
  }
  
  public float getCharWidthAt(int paramInt) {
    boolean bool;
    if (paramInt >= 0 && paramInt < this.mChars.length) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("offset(");
    stringBuilder.append(paramInt);
    stringBuilder.append(") is larger than text length: ");
    stringBuilder.append(this.mChars.length);
    Preconditions.checkArgument(bool, stringBuilder.toString());
    return nGetCharWidthAt(this.mNativePtr, paramInt);
  }
  
  public char[] getChars() {
    return this.mChars;
  }
  
  public int getMemoryUsage() {
    return nGetMemoryUsage(this.mNativePtr);
  }
  
  public long getNativePtr() {
    return this.mNativePtr;
  }
  
  public float getWidth(int paramInt1, int paramInt2) {
    boolean bool2;
    boolean bool1 = true;
    if (paramInt1 >= 0 && paramInt1 <= this.mChars.length) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("start(");
    stringBuilder.append(paramInt1);
    stringBuilder.append(") must be 0 <= start <= ");
    stringBuilder.append(this.mChars.length);
    Preconditions.checkArgument(bool2, stringBuilder.toString());
    if (paramInt2 >= 0 && paramInt2 <= this.mChars.length) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    stringBuilder = new StringBuilder();
    stringBuilder.append("end(");
    stringBuilder.append(paramInt2);
    stringBuilder.append(") must be 0 <= end <= ");
    stringBuilder.append(this.mChars.length);
    Preconditions.checkArgument(bool2, stringBuilder.toString());
    if (paramInt1 <= paramInt2) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    stringBuilder = new StringBuilder();
    stringBuilder.append("start(");
    stringBuilder.append(paramInt1);
    stringBuilder.append(") is larger than end(");
    stringBuilder.append(paramInt2);
    stringBuilder.append(")");
    Preconditions.checkArgument(bool2, stringBuilder.toString());
    return nGetWidth(this.mNativePtr, paramInt1, paramInt2);
  }
  
  public static final class Builder {
    private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(MeasuredText.class.getClassLoader(), MeasuredText.nGetReleaseFunc());
    
    private boolean mComputeHyphenation = false;
    
    private boolean mComputeLayout = true;
    
    private int mCurrentOffset = 0;
    
    private MeasuredText mHintMt = null;
    
    private long mNativePtr;
    
    private final char[] mText;
    
    public Builder(MeasuredText param1MeasuredText) {
      Preconditions.checkNotNull(param1MeasuredText);
      this.mText = param1MeasuredText.mChars;
      this.mNativePtr = nInitBuilder();
      if (param1MeasuredText.mComputeLayout) {
        this.mComputeHyphenation = param1MeasuredText.mComputeHyphenation;
        this.mComputeLayout = param1MeasuredText.mComputeLayout;
        this.mHintMt = param1MeasuredText;
        return;
      } 
      throw new IllegalArgumentException("The input MeasuredText must not be created with setComputeLayout(false).");
    }
    
    public Builder(char[] param1ArrayOfchar) {
      Preconditions.checkNotNull(param1ArrayOfchar);
      this.mText = param1ArrayOfchar;
      this.mNativePtr = nInitBuilder();
    }
    
    private void ensureNativePtrNoReuse() {
      if (this.mNativePtr != 0L)
        return; 
      throw new IllegalStateException("Builder can not be reused.");
    }
    
    private static native void nAddReplacementRun(long param1Long1, long param1Long2, int param1Int1, int param1Int2, float param1Float);
    
    private static native void nAddStyleRun(long param1Long1, long param1Long2, int param1Int1, int param1Int2, boolean param1Boolean);
    
    private static native long nBuildMeasuredText(long param1Long1, long param1Long2, char[] param1ArrayOfchar, boolean param1Boolean1, boolean param1Boolean2);
    
    private static native void nFreeBuilder(long param1Long);
    
    private static native long nInitBuilder();
    
    public Builder appendReplacementRun(Paint param1Paint, int param1Int, float param1Float) {
      boolean bool2;
      boolean bool1 = true;
      if (param1Int > 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      Preconditions.checkArgument(bool2, "length can not be negative");
      param1Int = this.mCurrentOffset + param1Int;
      if (param1Int <= this.mText.length) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      Preconditions.checkArgument(bool2, "Replacement exceeds the text length");
      nAddReplacementRun(this.mNativePtr, param1Paint.getNativeInstance(), this.mCurrentOffset, param1Int, param1Float);
      this.mCurrentOffset = param1Int;
      return this;
    }
    
    public Builder appendStyleRun(Paint param1Paint, int param1Int, boolean param1Boolean) {
      boolean bool2;
      Preconditions.checkNotNull(param1Paint);
      boolean bool1 = true;
      if (param1Int > 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      Preconditions.checkArgument(bool2, "length can not be negative");
      param1Int = this.mCurrentOffset + param1Int;
      if (param1Int <= this.mText.length) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      Preconditions.checkArgument(bool2, "Style exceeds the text length");
      nAddStyleRun(this.mNativePtr, param1Paint.getNativeInstance(), this.mCurrentOffset, param1Int, param1Boolean);
      this.mCurrentOffset = param1Int;
      return this;
    }
    
    public MeasuredText build() {
      ensureNativePtrNoReuse();
      if (this.mCurrentOffset == this.mText.length) {
        MeasuredText measuredText = this.mHintMt;
        if (measuredText == null || measuredText.mComputeHyphenation == this.mComputeHyphenation)
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
    
    public Builder setComputeHyphenation(boolean param1Boolean) {
      this.mComputeHyphenation = param1Boolean;
      return this;
    }
    
    public Builder setComputeLayout(boolean param1Boolean) {
      this.mComputeLayout = param1Boolean;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/text/MeasuredText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
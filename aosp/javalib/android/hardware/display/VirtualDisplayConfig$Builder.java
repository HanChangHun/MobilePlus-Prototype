package android.hardware.display;

import android.annotation.IntRange;
import android.annotation.NonNull;
import android.view.Surface;
import com.android.internal.util.AnnotationValidations;

public final class Builder {
  private long mBuilderFieldsSet = 0L;
  
  private int mDensityDpi;
  
  private int mDisplayIdToMirror;
  
  private int mFlags;
  
  private int mHeight;
  
  private String mName;
  
  private Surface mSurface;
  
  private String mUniqueId;
  
  private int mWidth;
  
  public Builder(String paramString, int paramInt1, int paramInt2, int paramInt3) {
    this.mName = paramString;
    AnnotationValidations.validate(NonNull.class, null, paramString);
    this.mWidth = paramInt1;
    AnnotationValidations.validate(IntRange.class, null, paramInt1, "from", 1L);
    this.mHeight = paramInt2;
    AnnotationValidations.validate(IntRange.class, null, paramInt2, "from", 1L);
    this.mDensityDpi = paramInt3;
    AnnotationValidations.validate(IntRange.class, null, paramInt3, "from", 1L);
  }
  
  private void checkNotUsed() {
    if ((this.mBuilderFieldsSet & 0x100L) == 0L)
      return; 
    throw new IllegalStateException("This Builder should not be reused. Use a new Builder instance instead");
  }
  
  public VirtualDisplayConfig build() {
    checkNotUsed();
    long l = this.mBuilderFieldsSet | 0x100L;
    this.mBuilderFieldsSet = l;
    if ((l & 0x10L) == 0L)
      this.mFlags = 0; 
    if ((this.mBuilderFieldsSet & 0x20L) == 0L)
      this.mSurface = null; 
    if ((this.mBuilderFieldsSet & 0x40L) == 0L)
      this.mUniqueId = null; 
    if ((this.mBuilderFieldsSet & 0x80L) == 0L)
      this.mDisplayIdToMirror = 0; 
    return new VirtualDisplayConfig(this.mName, this.mWidth, this.mHeight, this.mDensityDpi, this.mFlags, this.mSurface, this.mUniqueId, this.mDisplayIdToMirror);
  }
  
  public Builder setDensityDpi(int paramInt) {
    checkNotUsed();
    this.mBuilderFieldsSet |= 0x8L;
    this.mDensityDpi = paramInt;
    return this;
  }
  
  public Builder setDisplayIdToMirror(int paramInt) {
    checkNotUsed();
    this.mBuilderFieldsSet |= 0x80L;
    this.mDisplayIdToMirror = paramInt;
    return this;
  }
  
  public Builder setFlags(int paramInt) {
    checkNotUsed();
    this.mBuilderFieldsSet |= 0x10L;
    this.mFlags = paramInt;
    return this;
  }
  
  public Builder setHeight(int paramInt) {
    checkNotUsed();
    this.mBuilderFieldsSet |= 0x4L;
    this.mHeight = paramInt;
    return this;
  }
  
  public Builder setName(String paramString) {
    checkNotUsed();
    this.mBuilderFieldsSet |= 0x1L;
    this.mName = paramString;
    return this;
  }
  
  public Builder setSurface(Surface paramSurface) {
    checkNotUsed();
    this.mBuilderFieldsSet |= 0x20L;
    this.mSurface = paramSurface;
    return this;
  }
  
  public Builder setUniqueId(String paramString) {
    checkNotUsed();
    this.mBuilderFieldsSet |= 0x40L;
    this.mUniqueId = paramString;
    return this;
  }
  
  public Builder setWidth(int paramInt) {
    checkNotUsed();
    this.mBuilderFieldsSet |= 0x2L;
    this.mWidth = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/VirtualDisplayConfig$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
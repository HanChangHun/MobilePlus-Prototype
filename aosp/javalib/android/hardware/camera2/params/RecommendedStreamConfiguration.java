package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;

public final class RecommendedStreamConfiguration extends StreamConfiguration {
  private final int mUsecaseBitmap;
  
  public RecommendedStreamConfiguration(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4) {
    super(paramInt1, paramInt2, paramInt3, paramBoolean);
    this.mUsecaseBitmap = paramInt4;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof RecommendedStreamConfiguration) {
      paramObject = paramObject;
      boolean bool1 = bool;
      if (this.mFormat == ((RecommendedStreamConfiguration)paramObject).mFormat) {
        bool1 = bool;
        if (this.mWidth == ((RecommendedStreamConfiguration)paramObject).mWidth) {
          bool1 = bool;
          if (this.mHeight == ((RecommendedStreamConfiguration)paramObject).mHeight) {
            bool1 = bool;
            if (this.mUsecaseBitmap == ((RecommendedStreamConfiguration)paramObject).mUsecaseBitmap) {
              bool1 = bool;
              if (this.mInput == ((RecommendedStreamConfiguration)paramObject).mInput)
                bool1 = true; 
            } 
          } 
        } 
      } 
      return bool1;
    } 
    return false;
  }
  
  public int getUsecaseBitmap() {
    return this.mUsecaseBitmap;
  }
  
  public int hashCode() {
    return HashCodeHelpers.hashCode(new int[] { this.mFormat, this.mWidth, this.mHeight, this.mInput, this.mUsecaseBitmap });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/RecommendedStreamConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
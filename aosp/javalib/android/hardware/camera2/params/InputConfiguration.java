package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;

public final class InputConfiguration {
  private final int mFormat;
  
  private final int mHeight;
  
  private final int mWidth;
  
  public InputConfiguration(int paramInt1, int paramInt2, int paramInt3) {
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    this.mFormat = paramInt3;
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof InputConfiguration))
      return false; 
    paramObject = paramObject;
    return (paramObject.getWidth() == this.mWidth && paramObject.getHeight() == this.mHeight && paramObject.getFormat() == this.mFormat);
  }
  
  public int getFormat() {
    return this.mFormat;
  }
  
  public int getHeight() {
    return this.mHeight;
  }
  
  public int getWidth() {
    return this.mWidth;
  }
  
  public int hashCode() {
    return HashCodeHelpers.hashCode(new int[] { this.mWidth, this.mHeight, this.mFormat });
  }
  
  public String toString() {
    return String.format("InputConfiguration(w:%d, h:%d, format:%d)", new Object[] { Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight), Integer.valueOf(this.mFormat) });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/InputConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
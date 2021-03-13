package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Size;
import com.android.internal.util.Preconditions;

public class StreamConfiguration {
  protected int mFormat;
  
  protected int mHeight;
  
  protected boolean mInput;
  
  protected int mWidth;
  
  public StreamConfiguration(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    this.mFormat = StreamConfigurationMap.checkArgumentFormatInternal(paramInt1);
    this.mWidth = Preconditions.checkArgumentPositive(paramInt2, "width must be positive");
    this.mHeight = Preconditions.checkArgumentPositive(paramInt3, "height must be positive");
    this.mInput = paramBoolean;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof StreamConfiguration) {
      paramObject = paramObject;
      boolean bool1 = bool;
      if (this.mFormat == ((StreamConfiguration)paramObject).mFormat) {
        bool1 = bool;
        if (this.mWidth == ((StreamConfiguration)paramObject).mWidth) {
          bool1 = bool;
          if (this.mHeight == ((StreamConfiguration)paramObject).mHeight) {
            bool1 = bool;
            if (this.mInput == ((StreamConfiguration)paramObject).mInput)
              bool1 = true; 
          } 
        } 
      } 
      return bool1;
    } 
    return false;
  }
  
  public final int getFormat() {
    return this.mFormat;
  }
  
  public int getHeight() {
    return this.mHeight;
  }
  
  public Size getSize() {
    return new Size(this.mWidth, this.mHeight);
  }
  
  public int getWidth() {
    return this.mWidth;
  }
  
  public int hashCode() {
    return HashCodeHelpers.hashCode(new int[] { this.mFormat, this.mWidth, this.mHeight, this.mInput });
  }
  
  public boolean isInput() {
    return this.mInput;
  }
  
  public boolean isOutput() {
    return this.mInput ^ true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/StreamConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
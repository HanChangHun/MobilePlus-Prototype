package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Size;
import com.android.internal.util.Preconditions;

public final class StreamConfigurationDuration {
  private final long mDurationNs;
  
  private final int mFormat;
  
  private final int mHeight;
  
  private final int mWidth;
  
  public StreamConfigurationDuration(int paramInt1, int paramInt2, int paramInt3, long paramLong) {
    this.mFormat = StreamConfigurationMap.checkArgumentFormatInternal(paramInt1);
    this.mWidth = Preconditions.checkArgumentPositive(paramInt2, "width must be positive");
    this.mHeight = Preconditions.checkArgumentPositive(paramInt3, "height must be positive");
    this.mDurationNs = Preconditions.checkArgumentNonnegative(paramLong, "durationNs must be non-negative");
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof StreamConfigurationDuration) {
      paramObject = paramObject;
      boolean bool1 = bool;
      if (this.mFormat == ((StreamConfigurationDuration)paramObject).mFormat) {
        bool1 = bool;
        if (this.mWidth == ((StreamConfigurationDuration)paramObject).mWidth) {
          bool1 = bool;
          if (this.mHeight == ((StreamConfigurationDuration)paramObject).mHeight) {
            bool1 = bool;
            if (this.mDurationNs == ((StreamConfigurationDuration)paramObject).mDurationNs)
              bool1 = true; 
          } 
        } 
      } 
      return bool1;
    } 
    return false;
  }
  
  public long getDuration() {
    return this.mDurationNs;
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
    int i = this.mFormat;
    int j = this.mWidth;
    int k = this.mHeight;
    long l = this.mDurationNs;
    return HashCodeHelpers.hashCode(new int[] { i, j, k, (int)l, (int)(l >>> 32L) });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/StreamConfigurationDuration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
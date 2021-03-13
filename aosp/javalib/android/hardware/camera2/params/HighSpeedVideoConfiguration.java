package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Range;
import android.util.Size;
import com.android.internal.util.Preconditions;

public final class HighSpeedVideoConfiguration {
  private static final int HIGH_SPEED_MAX_MINIMAL_FPS = 120;
  
  private final int mBatchSizeMax;
  
  private final int mFpsMax;
  
  private final int mFpsMin;
  
  private final Range<Integer> mFpsRange;
  
  private final int mHeight;
  
  private final Size mSize;
  
  private final int mWidth;
  
  public HighSpeedVideoConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt4 >= 120) {
      this.mFpsMax = paramInt4;
      this.mWidth = Preconditions.checkArgumentPositive(paramInt1, "width must be positive");
      this.mHeight = Preconditions.checkArgumentPositive(paramInt2, "height must be positive");
      this.mFpsMin = Preconditions.checkArgumentPositive(paramInt3, "fpsMin must be positive");
      this.mSize = new Size(this.mWidth, this.mHeight);
      this.mBatchSizeMax = Preconditions.checkArgumentPositive(paramInt5, "batchSizeMax must be positive");
      this.mFpsRange = new Range(Integer.valueOf(this.mFpsMin), Integer.valueOf(this.mFpsMax));
      return;
    } 
    throw new IllegalArgumentException("fpsMax must be at least 120");
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof HighSpeedVideoConfiguration) {
      paramObject = paramObject;
      boolean bool1 = bool;
      if (this.mWidth == ((HighSpeedVideoConfiguration)paramObject).mWidth) {
        bool1 = bool;
        if (this.mHeight == ((HighSpeedVideoConfiguration)paramObject).mHeight) {
          bool1 = bool;
          if (this.mFpsMin == ((HighSpeedVideoConfiguration)paramObject).mFpsMin) {
            bool1 = bool;
            if (this.mFpsMax == ((HighSpeedVideoConfiguration)paramObject).mFpsMax) {
              bool1 = bool;
              if (this.mBatchSizeMax == ((HighSpeedVideoConfiguration)paramObject).mBatchSizeMax)
                bool1 = true; 
            } 
          } 
        } 
      } 
      return bool1;
    } 
    return false;
  }
  
  public int getBatchSizeMax() {
    return this.mBatchSizeMax;
  }
  
  public int getFpsMax() {
    return this.mFpsMax;
  }
  
  public int getFpsMin() {
    return this.mFpsMin;
  }
  
  public Range<Integer> getFpsRange() {
    return this.mFpsRange;
  }
  
  public int getHeight() {
    return this.mHeight;
  }
  
  public Size getSize() {
    return this.mSize;
  }
  
  public int getWidth() {
    return this.mWidth;
  }
  
  public int hashCode() {
    return HashCodeHelpers.hashCode(new int[] { this.mWidth, this.mHeight, this.mFpsMin, this.mFpsMax });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/HighSpeedVideoConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
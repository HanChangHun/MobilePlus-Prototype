package android.accessibilityservice;

import android.graphics.ColorSpace;
import android.hardware.HardwareBuffer;
import com.android.internal.util.Preconditions;

public final class ScreenshotResult {
  private final ColorSpace mColorSpace;
  
  private final HardwareBuffer mHardwareBuffer;
  
  private final long mTimestamp;
  
  private ScreenshotResult(HardwareBuffer paramHardwareBuffer, ColorSpace paramColorSpace, long paramLong) {
    Preconditions.checkNotNull(paramHardwareBuffer, "hardwareBuffer cannot be null");
    Preconditions.checkNotNull(paramColorSpace, "colorSpace cannot be null");
    this.mHardwareBuffer = paramHardwareBuffer;
    this.mColorSpace = paramColorSpace;
    this.mTimestamp = paramLong;
  }
  
  public ColorSpace getColorSpace() {
    return this.mColorSpace;
  }
  
  public HardwareBuffer getHardwareBuffer() {
    return this.mHardwareBuffer;
  }
  
  public long getTimestamp() {
    return this.mTimestamp;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityService$ScreenshotResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
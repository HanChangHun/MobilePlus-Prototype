package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import com.android.internal.util.Preconditions;

public final class OisSample {
  private final long mTimestampNs;
  
  private final float mXShift;
  
  private final float mYShift;
  
  public OisSample(long paramLong, float paramFloat1, float paramFloat2) {
    this.mTimestampNs = paramLong;
    this.mXShift = Preconditions.checkArgumentFinite(paramFloat1, "xShift must be finite");
    this.mYShift = Preconditions.checkArgumentFinite(paramFloat2, "yShift must be finite");
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof OisSample) {
      paramObject = paramObject;
      boolean bool1 = bool;
      if (this.mTimestampNs == ((OisSample)paramObject).mTimestampNs) {
        bool1 = bool;
        if (this.mXShift == ((OisSample)paramObject).mXShift) {
          bool1 = bool;
          if (this.mYShift == ((OisSample)paramObject).mYShift)
            bool1 = true; 
        } 
      } 
      return bool1;
    } 
    return false;
  }
  
  public long getTimestamp() {
    return this.mTimestampNs;
  }
  
  public float getXshift() {
    return this.mXShift;
  }
  
  public float getYshift() {
    return this.mYShift;
  }
  
  public int hashCode() {
    int i = HashCodeHelpers.hashCode(new float[] { (float)this.mTimestampNs });
    return HashCodeHelpers.hashCode(new float[] { this.mXShift, this.mYShift, i });
  }
  
  public String toString() {
    return String.format("OisSample{timestamp:%d, shift_x:%f, shift_y:%f}", new Object[] { Long.valueOf(this.mTimestampNs), Float.valueOf(this.mXShift), Float.valueOf(this.mYShift) });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/OisSample.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
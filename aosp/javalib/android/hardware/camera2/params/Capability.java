package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Range;
import android.util.Size;
import com.android.internal.util.Preconditions;

public final class Capability {
  public static final int COUNT = 3;
  
  private final int mMaxStreamingHeight;
  
  private final int mMaxStreamingWidth;
  
  private final float mMaxZoomRatio;
  
  private final float mMinZoomRatio;
  
  private final int mMode;
  
  public Capability(int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2) {
    this.mMode = paramInt1;
    this.mMaxStreamingWidth = Preconditions.checkArgumentNonnegative(paramInt2, "maxStreamingWidth must be nonnegative");
    this.mMaxStreamingHeight = Preconditions.checkArgumentNonnegative(paramInt3, "maxStreamingHeight must be nonnegative");
    if (paramFloat1 <= paramFloat2) {
      this.mMinZoomRatio = Preconditions.checkArgumentPositive(paramFloat1, "minZoomRatio must be positive");
      this.mMaxZoomRatio = Preconditions.checkArgumentPositive(paramFloat2, "maxZoomRatio must be positive");
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("minZoomRatio ");
    stringBuilder.append(paramFloat1);
    stringBuilder.append(" is greater than maxZoomRatio ");
    stringBuilder.append(paramFloat2);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof Capability) {
      paramObject = paramObject;
      boolean bool1 = bool;
      if (this.mMode == ((Capability)paramObject).mMode) {
        bool1 = bool;
        if (this.mMaxStreamingWidth == ((Capability)paramObject).mMaxStreamingWidth) {
          bool1 = bool;
          if (this.mMaxStreamingHeight == ((Capability)paramObject).mMaxStreamingHeight) {
            bool1 = bool;
            if (this.mMinZoomRatio == ((Capability)paramObject).mMinZoomRatio) {
              bool1 = bool;
              if (this.mMaxZoomRatio == ((Capability)paramObject).mMaxZoomRatio)
                bool1 = true; 
            } 
          } 
        } 
      } 
      return bool1;
    } 
    return false;
  }
  
  public Size getMaxStreamingSize() {
    return new Size(this.mMaxStreamingWidth, this.mMaxStreamingHeight);
  }
  
  public int getMode() {
    return this.mMode;
  }
  
  public Range<Float> getZoomRatioRange() {
    return new Range(Float.valueOf(this.mMinZoomRatio), Float.valueOf(this.mMaxZoomRatio));
  }
  
  public int hashCode() {
    return HashCodeHelpers.hashCode(new float[] { this.mMode, this.mMaxStreamingWidth, this.mMaxStreamingHeight, this.mMinZoomRatio, this.mMaxZoomRatio });
  }
  
  public String toString() {
    return String.format("(mode:%d, maxStreamingSize:%d x %d, zoomRatio: %f-%f)", new Object[] { Integer.valueOf(this.mMode), Integer.valueOf(this.mMaxStreamingWidth), Integer.valueOf(this.mMaxStreamingHeight), Float.valueOf(this.mMinZoomRatio), Float.valueOf(this.mMaxZoomRatio) });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/Capability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
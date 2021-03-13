package android.hardware.camera2.legacy;

import android.graphics.Rect;

public class ZoomData {
  public final Rect previewCrop;
  
  public final Rect reportedCrop;
  
  public final float reportedZoomRatio;
  
  public final int zoomIndex;
  
  public ZoomData(int paramInt, Rect paramRect1, Rect paramRect2, float paramFloat) {
    this.zoomIndex = paramInt;
    this.previewCrop = paramRect1;
    this.reportedCrop = paramRect2;
    this.reportedZoomRatio = paramFloat;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/ParameterUtils$ZoomData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
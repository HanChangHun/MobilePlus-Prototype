package android.hardware.camera2.legacy;

import android.graphics.Rect;
import android.hardware.Camera;

public class MeteringData {
  public final Camera.Area meteringArea;
  
  public final Rect previewMetering;
  
  public final Rect reportedMetering;
  
  public MeteringData(Camera.Area paramArea, Rect paramRect1, Rect paramRect2) {
    this.meteringArea = paramArea;
    this.previewMetering = paramRect1;
    this.reportedMetering = paramRect2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/ParameterUtils$MeteringData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
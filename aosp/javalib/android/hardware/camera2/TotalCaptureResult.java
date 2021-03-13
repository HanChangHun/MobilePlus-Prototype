package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.impl.PhysicalCaptureResultInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TotalCaptureResult extends CaptureResult {
  private final List<CaptureResult> mPartialResults;
  
  private final HashMap<String, CaptureResult> mPhysicalCaptureResults;
  
  private final int mSessionId;
  
  public TotalCaptureResult(CameraMetadataNative paramCameraMetadataNative, int paramInt) {
    super(paramCameraMetadataNative, paramInt);
    this.mPartialResults = new ArrayList<>();
    this.mSessionId = -1;
    this.mPhysicalCaptureResults = new HashMap<>();
  }
  
  public TotalCaptureResult(CameraMetadataNative paramCameraMetadataNative, CaptureRequest paramCaptureRequest, CaptureResultExtras paramCaptureResultExtras, List<CaptureResult> paramList, int paramInt, PhysicalCaptureResultInfo[] paramArrayOfPhysicalCaptureResultInfo) {
    super(paramCameraMetadataNative, paramCaptureRequest, paramCaptureResultExtras);
    if (paramList == null) {
      this.mPartialResults = new ArrayList<>();
    } else {
      this.mPartialResults = paramList;
    } 
    this.mSessionId = paramInt;
    this.mPhysicalCaptureResults = new HashMap<>();
    int i = paramArrayOfPhysicalCaptureResultInfo.length;
    for (paramInt = 0; paramInt < i; paramInt++) {
      PhysicalCaptureResultInfo physicalCaptureResultInfo = paramArrayOfPhysicalCaptureResultInfo[paramInt];
      CaptureResult captureResult = new CaptureResult(physicalCaptureResultInfo.getCameraMetadata(), paramCaptureRequest, paramCaptureResultExtras);
      this.mPhysicalCaptureResults.put(physicalCaptureResultInfo.getCameraId(), captureResult);
    } 
  }
  
  public List<CaptureResult> getPartialResults() {
    return Collections.unmodifiableList(this.mPartialResults);
  }
  
  public Map<String, CaptureResult> getPhysicalCameraResults() {
    return Collections.unmodifiableMap(this.mPhysicalCaptureResults);
  }
  
  public int getSessionId() {
    return this.mSessionId;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/TotalCaptureResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
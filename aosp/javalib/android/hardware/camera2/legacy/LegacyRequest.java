package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.util.Size;
import com.android.internal.util.Preconditions;

public class LegacyRequest {
  public final CaptureRequest captureRequest;
  
  public final CameraCharacteristics characteristics;
  
  public final Camera.Parameters parameters;
  
  public final Size previewSize;
  
  public LegacyRequest(CameraCharacteristics paramCameraCharacteristics, CaptureRequest paramCaptureRequest, Size paramSize, Camera.Parameters paramParameters) {
    this.characteristics = (CameraCharacteristics)Preconditions.checkNotNull(paramCameraCharacteristics, "characteristics must not be null");
    this.captureRequest = (CaptureRequest)Preconditions.checkNotNull(paramCaptureRequest, "captureRequest must not be null");
    this.previewSize = (Size)Preconditions.checkNotNull(paramSize, "previewSize must not be null");
    Preconditions.checkNotNull(paramParameters, "parameters must not be null");
    this.parameters = Camera.getParametersCopy(paramParameters);
  }
  
  public void setParameters(Camera.Parameters paramParameters) {
    Preconditions.checkNotNull(paramParameters, "parameters must not be null");
    this.parameters.copyFrom(paramParameters);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
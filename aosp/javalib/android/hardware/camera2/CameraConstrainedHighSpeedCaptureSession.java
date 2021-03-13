package android.hardware.camera2;

import java.util.List;

public abstract class CameraConstrainedHighSpeedCaptureSession extends CameraCaptureSession {
  public abstract List<CaptureRequest> createHighSpeedRequestList(CaptureRequest paramCaptureRequest) throws CameraAccessException;
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraConstrainedHighSpeedCaptureSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
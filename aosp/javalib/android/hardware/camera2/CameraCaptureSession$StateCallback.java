package android.hardware.camera2;

import android.view.Surface;

public abstract class StateCallback {
  public void onActive(CameraCaptureSession paramCameraCaptureSession) {}
  
  public void onCaptureQueueEmpty(CameraCaptureSession paramCameraCaptureSession) {}
  
  public void onClosed(CameraCaptureSession paramCameraCaptureSession) {}
  
  public abstract void onConfigureFailed(CameraCaptureSession paramCameraCaptureSession);
  
  public abstract void onConfigured(CameraCaptureSession paramCameraCaptureSession);
  
  public void onReady(CameraCaptureSession paramCameraCaptureSession) {}
  
  public void onSurfacePrepared(CameraCaptureSession paramCameraCaptureSession, Surface paramSurface) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraCaptureSession$StateCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
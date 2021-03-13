package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.view.Surface;

class WrapperCallback extends CameraCaptureSession.StateCallback {
  private final CameraCaptureSession.StateCallback mCallback;
  
  public WrapperCallback(CameraCaptureSession.StateCallback paramStateCallback) {
    this.mCallback = paramStateCallback;
  }
  
  public void onActive(CameraCaptureSession paramCameraCaptureSession) {
    this.mCallback.onActive((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
  }
  
  public void onCaptureQueueEmpty(CameraCaptureSession paramCameraCaptureSession) {
    this.mCallback.onCaptureQueueEmpty((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
  }
  
  public void onClosed(CameraCaptureSession paramCameraCaptureSession) {
    this.mCallback.onClosed((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
  }
  
  public void onConfigureFailed(CameraCaptureSession paramCameraCaptureSession) {
    CameraConstrainedHighSpeedCaptureSessionImpl.access$000(CameraConstrainedHighSpeedCaptureSessionImpl.this).block();
    this.mCallback.onConfigureFailed((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
  }
  
  public void onConfigured(CameraCaptureSession paramCameraCaptureSession) {
    CameraConstrainedHighSpeedCaptureSessionImpl.access$000(CameraConstrainedHighSpeedCaptureSessionImpl.this).block();
    this.mCallback.onConfigured((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
  }
  
  public void onReady(CameraCaptureSession paramCameraCaptureSession) {
    this.mCallback.onReady((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
  }
  
  public void onSurfacePrepared(CameraCaptureSession paramCameraCaptureSession, Surface paramSurface) {
    this.mCallback.onSurfacePrepared((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this, paramSurface);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraConstrainedHighSpeedCaptureSessionImpl$WrapperCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
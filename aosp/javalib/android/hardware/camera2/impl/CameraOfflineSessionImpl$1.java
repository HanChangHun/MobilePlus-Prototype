package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;

class null implements Runnable {
  public void run() {
    if (!CameraOfflineSessionImpl.access$000(CameraOfflineSessionImpl.this))
      callback.onCaptureSequenceCompleted((CameraCaptureSession)CameraOfflineSessionImpl.this, requestId, requestLastFrameNumbers.getLastFrameNumber()); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraOfflineSessionImpl$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
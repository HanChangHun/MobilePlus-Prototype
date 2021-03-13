package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;

class null implements Runnable {
  public void run() {
    CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
    if (!CameraOfflineSessionImpl.access$000(this.this$1.this$0) && captureCallback != null)
      captureCallback.onCaptureFailed((CameraCaptureSession)this.this$1.this$0, request, failure); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks$7.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
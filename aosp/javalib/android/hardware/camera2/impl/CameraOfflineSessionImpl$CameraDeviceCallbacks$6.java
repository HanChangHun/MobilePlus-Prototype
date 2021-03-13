package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.view.Surface;

class null implements Runnable {
  public void run() {
    CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
    if (!CameraOfflineSessionImpl.access$000(this.this$1.this$0) && captureCallback != null)
      captureCallback.onCaptureBufferLost((CameraCaptureSession)this.this$1.this$0, request, surface, frameNumber); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks$6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
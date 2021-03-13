package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;

class null implements Runnable {
  public void run() {
    CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
    if (!CameraOfflineSessionImpl.access$000(this.this$1.this$0) && captureCallback != null)
      if (holder.hasBatchedOutputs()) {
        for (byte b = 0; b < holder.getRequestCount(); b++) {
          CaptureResult captureResult = new CaptureResult(new CameraMetadataNative(resultCopy), holder.getRequest(b), resultExtras);
          CaptureRequest captureRequest = holder.getRequest(b);
          captureCallback.onCaptureProgressed((CameraCaptureSession)this.this$1.this$0, captureRequest, captureResult);
        } 
      } else {
        captureCallback.onCaptureProgressed((CameraCaptureSession)this.this$1.this$0, request, resultAsCapture);
      }  
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
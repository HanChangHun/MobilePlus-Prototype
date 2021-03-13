package android.hardware.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;

class null implements Runnable {
  public void run() {
    if (!CameraDeviceImpl.access$400(this.this$1.this$0))
      if (holder.hasBatchedOutputs()) {
        for (byte b = 0; b < holder.getRequestCount(); b++) {
          CaptureResult captureResult = new CaptureResult(new CameraMetadataNative(resultCopy), holder.getRequest(b), resultExtras);
          holder.getCallback().onCaptureProgressed(this.this$1.this$0, holder.getRequest(b), captureResult);
        } 
      } else {
        holder.getCallback().onCaptureProgressed(this.this$1.this$0, request, resultAsCapture);
      }  
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
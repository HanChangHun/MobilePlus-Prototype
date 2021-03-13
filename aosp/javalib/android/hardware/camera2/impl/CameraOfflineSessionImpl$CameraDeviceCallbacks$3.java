package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.util.Range;

class null implements Runnable {
  public void run() {
    CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
    if (!CameraOfflineSessionImpl.access$000(this.this$1.this$0) && captureCallback != null) {
      int i = resultExtras.getSubsequenceId();
      CaptureRequest captureRequest = holder.getRequest(i);
      if (holder.hasBatchedOutputs()) {
        Range range = (Range)captureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
        for (byte b = 0; b < holder.getRequestCount(); b++) {
          CaptureRequest captureRequest1 = holder.getRequest(b);
          long l1 = timestamp;
          long l2 = (i - b) * 1000000000L / ((Integer)range.getUpper()).intValue();
          long l3 = frameNumber;
          long l4 = (i - b);
          captureCallback.onCaptureStarted((CameraCaptureSession)this.this$1.this$0, captureRequest1, l1 - l2, l3 - l4);
        } 
      } else {
        captureCallback.onCaptureStarted((CameraCaptureSession)this.this$1.this$0, holder.getRequest(resultExtras.getSubsequenceId()), timestamp, frameNumber);
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
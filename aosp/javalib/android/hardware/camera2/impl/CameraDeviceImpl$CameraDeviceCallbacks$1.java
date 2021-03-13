package android.hardware.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import android.util.Range;

class null implements Runnable {
  public void run() {
    if (!CameraDeviceImpl.access$400(this.this$1.this$0)) {
      int i = resultExtras.getSubsequenceId();
      CaptureRequest captureRequest = holder.getRequest(i);
      if (holder.hasBatchedOutputs()) {
        Range range = (Range)captureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
        for (byte b = 0; b < holder.getRequestCount(); b++)
          holder.getCallback().onCaptureStarted(this.this$1.this$0, holder.getRequest(b), timestamp - (i - b) * 1000000000L / ((Integer)range.getUpper()).intValue(), frameNumber - (i - b)); 
      } else {
        holder.getCallback().onCaptureStarted(this.this$1.this$0, holder.getRequest(resultExtras.getSubsequenceId()), timestamp, frameNumber);
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
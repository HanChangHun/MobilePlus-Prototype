package android.hardware.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Range;
import java.util.List;

class null implements Runnable {
  public void run() {
    if (!CameraDeviceImpl.access$400(this.this$1.this$0))
      if (holder.hasBatchedOutputs()) {
        for (byte b = 0; b < holder.getRequestCount(); b++) {
          resultCopy.set(CaptureResult.SENSOR_TIMESTAMP, Long.valueOf(sensorTimestamp - (subsequenceId - b) * 1000000000L / ((Integer)fpsRange.getUpper()).intValue()));
          TotalCaptureResult totalCaptureResult = new TotalCaptureResult(new CameraMetadataNative(resultCopy), holder.getRequest(b), resultExtras, partialResults, holder.getSessionId(), new PhysicalCaptureResultInfo[0]);
          holder.getCallback().onCaptureCompleted(this.this$1.this$0, holder.getRequest(b), totalCaptureResult);
        } 
      } else {
        holder.getCallback().onCaptureCompleted(this.this$1.this$0, request, resultAsCapture);
      }  
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
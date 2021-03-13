package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Range;
import java.util.List;

class null implements Runnable {
  public void run() {
    CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
    if (!CameraOfflineSessionImpl.access$000(this.this$1.this$0) && captureCallback != null)
      if (holder.hasBatchedOutputs()) {
        for (byte b = 0; b < holder.getRequestCount(); b++) {
          resultCopy.set(CaptureResult.SENSOR_TIMESTAMP, Long.valueOf(sensorTimestamp - (subsequenceId - b) * 1000000000L / ((Integer)fpsRange.getUpper()).intValue()));
          TotalCaptureResult totalCaptureResult = new TotalCaptureResult(new CameraMetadataNative(resultCopy), holder.getRequest(b), resultExtras, partialResults, holder.getSessionId(), new PhysicalCaptureResultInfo[0]);
          CaptureRequest captureRequest = holder.getRequest(b);
          captureCallback.onCaptureCompleted((CameraCaptureSession)this.this$1.this$0, captureRequest, totalCaptureResult);
        } 
      } else {
        captureCallback.onCaptureCompleted((CameraCaptureSession)this.this$1.this$0, request, resultAsCapture);
      }  
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
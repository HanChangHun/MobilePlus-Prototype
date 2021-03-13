package android.hardware.camera2.legacy;

import android.hardware.camera2.impl.CameraMetadataNative;

public interface CameraDeviceStateListener {
  void onBusy();
  
  void onCaptureResult(CameraMetadataNative paramCameraMetadataNative, RequestHolder paramRequestHolder);
  
  void onCaptureStarted(RequestHolder paramRequestHolder, long paramLong);
  
  void onConfiguring();
  
  void onError(int paramInt, Object paramObject, RequestHolder paramRequestHolder);
  
  void onIdle();
  
  void onRepeatingRequestError(long paramLong, int paramInt);
  
  void onRequestQueueEmpty();
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/CameraDeviceState$CameraDeviceStateListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
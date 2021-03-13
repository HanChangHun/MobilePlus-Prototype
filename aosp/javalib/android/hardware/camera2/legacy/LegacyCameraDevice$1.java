package android.hardware.camera2.legacy;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.os.RemoteException;

class null implements CameraDeviceState.CameraDeviceStateListener {
  public void onBusy() {
    LegacyCameraDevice.access$000(LegacyCameraDevice.this).close();
  }
  
  public void onCaptureResult(final CameraMetadataNative result, final RequestHolder holder) {
    final CaptureResultExtras extras = LegacyCameraDevice.access$400(LegacyCameraDevice.this, holder);
    LegacyCameraDevice.access$300(LegacyCameraDevice.this).post(new Runnable() {
          public void run() {
            try {
              LegacyCameraDevice.access$200(LegacyCameraDevice.this).onResultReceived(result, extras, new android.hardware.camera2.impl.PhysicalCaptureResultInfo[0]);
              return;
            } catch (RemoteException remoteException) {
              throw new IllegalStateException("Received remote exception during onCameraError callback: ", remoteException);
            } 
          }
        });
  }
  
  public void onCaptureStarted(final RequestHolder holder, final long timestamp) {
    final CaptureResultExtras extras = LegacyCameraDevice.access$400(LegacyCameraDevice.this, holder);
    LegacyCameraDevice.access$300(LegacyCameraDevice.this).post(new Runnable() {
          public void run() {
            try {
              LegacyCameraDevice.access$200(LegacyCameraDevice.this).onCaptureStarted(extras, timestamp);
              return;
            } catch (RemoteException remoteException) {
              throw new IllegalStateException("Received remote exception during onCameraError callback: ", remoteException);
            } 
          }
        });
  }
  
  public void onConfiguring() {}
  
  public void onError(final int errorCode, final Object extras, final RequestHolder holder) {
    if (errorCode == 0 || errorCode == 1 || errorCode == 2)
      LegacyCameraDevice.access$000(LegacyCameraDevice.this).open(); 
    extras = LegacyCameraDevice.access$100(LegacyCameraDevice.this, holder, errorCode, extras);
    LegacyCameraDevice.access$300(LegacyCameraDevice.this).post(new Runnable() {
          public void run() {
            try {
              LegacyCameraDevice.access$200(LegacyCameraDevice.this).onDeviceError(errorCode, extras);
              return;
            } catch (RemoteException remoteException) {
              throw new IllegalStateException("Received remote exception during onCameraError callback: ", remoteException);
            } 
          }
        });
  }
  
  public void onIdle() {
    LegacyCameraDevice.access$000(LegacyCameraDevice.this).open();
    LegacyCameraDevice.access$300(LegacyCameraDevice.this).post(new Runnable() {
          public void run() {
            try {
              LegacyCameraDevice.access$200(LegacyCameraDevice.this).onDeviceIdle();
              return;
            } catch (RemoteException remoteException) {
              throw new IllegalStateException("Received remote exception during onCameraIdle callback: ", remoteException);
            } 
          }
        });
  }
  
  public void onRepeatingRequestError(final long lastFrameNumber, final int repeatingRequestId) {
    LegacyCameraDevice.access$300(LegacyCameraDevice.this).post(new Runnable() {
          public void run() {
            try {
              LegacyCameraDevice.access$200(LegacyCameraDevice.this).onRepeatingRequestError(lastFrameNumber, repeatingRequestId);
              return;
            } catch (RemoteException remoteException) {
              throw new IllegalStateException("Received remote exception during onRepeatingRequestError callback: ", remoteException);
            } 
          }
        });
  }
  
  public void onRequestQueueEmpty() {
    LegacyCameraDevice.access$300(LegacyCameraDevice.this).post(new Runnable() {
          public void run() {
            try {
              LegacyCameraDevice.access$200(LegacyCameraDevice.this).onRequestQueueEmpty();
              return;
            } catch (RemoteException remoteException) {
              throw new IllegalStateException("Received remote exception during onRequestQueueEmpty callback: ", remoteException);
            } 
          }
        });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyCameraDevice$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
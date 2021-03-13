package android.hardware.camera2.legacy;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.os.RemoteException;

class null implements Runnable {
  public void run() {
    try {
      LegacyCameraDevice.access$200(this.this$1.this$0).onResultReceived(result, extras, new android.hardware.camera2.impl.PhysicalCaptureResultInfo[0]);
      return;
    } catch (RemoteException remoteException) {
      throw new IllegalStateException("Received remote exception during onCameraError callback: ", remoteException);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyCameraDevice$1$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
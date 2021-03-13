package android.hardware.camera2.legacy;

import android.hardware.camera2.impl.CaptureResultExtras;
import android.os.RemoteException;

class null implements Runnable {
  public void run() {
    try {
      LegacyCameraDevice.access$200(this.this$1.this$0).onCaptureStarted(extras, timestamp);
      return;
    } catch (RemoteException remoteException) {
      throw new IllegalStateException("Received remote exception during onCameraError callback: ", remoteException);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyCameraDevice$1$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
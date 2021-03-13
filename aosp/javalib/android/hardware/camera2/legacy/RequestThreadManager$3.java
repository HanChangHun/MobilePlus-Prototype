package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.os.SystemClock;

class null implements Camera.ShutterCallback {
  public void onShutter() {
    RequestThreadManager.access$200(RequestThreadManager.this).jpegCaptured(SystemClock.elapsedRealtimeNanos());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestThreadManager$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
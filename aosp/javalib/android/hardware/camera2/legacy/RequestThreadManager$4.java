package android.hardware.camera2.legacy;

import android.graphics.SurfaceTexture;

class null implements SurfaceTexture.OnFrameAvailableListener {
  public void onFrameAvailable(SurfaceTexture paramSurfaceTexture) {
    RequestThreadManager.access$500(RequestThreadManager.this).queueNewFrame();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestThreadManager$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
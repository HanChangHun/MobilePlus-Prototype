package android.filterpacks.videosrc;

import android.graphics.SurfaceTexture;
import android.util.Log;

class null implements SurfaceTexture.OnFrameAvailableListener {
  public void onFrameAvailable(SurfaceTexture paramSurfaceTexture) {
    if (CameraSource.access$000(CameraSource.this))
      Log.v("CameraSource", "New frame from camera"); 
    synchronized (CameraSource.this) {
      CameraSource.access$102(CameraSource.this, true);
      CameraSource.this.notify();
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videosrc/CameraSource$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
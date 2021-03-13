package android.filterpacks.videosrc;

import android.graphics.SurfaceTexture;
import android.util.Log;

class null implements SurfaceTexture.OnFrameAvailableListener {
  public void onFrameAvailable(SurfaceTexture paramSurfaceTexture) {
    if (MediaSource.access$000(MediaSource.this))
      Log.v("MediaSource", "New frame from media player"); 
    synchronized (MediaSource.this) {
      if (MediaSource.access$000(MediaSource.this))
        Log.v("MediaSource", "New frame: notify"); 
      MediaSource.access$802(MediaSource.this, true);
      MediaSource.this.notify();
      if (MediaSource.access$000(MediaSource.this))
        Log.v("MediaSource", "New frame: notify done"); 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videosrc/MediaSource$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
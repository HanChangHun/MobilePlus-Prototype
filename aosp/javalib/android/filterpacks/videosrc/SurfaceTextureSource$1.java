package android.filterpacks.videosrc;

import android.graphics.SurfaceTexture;
import android.util.Log;

class null implements SurfaceTexture.OnFrameAvailableListener {
  public void onFrameAvailable(SurfaceTexture paramSurfaceTexture) {
    if (SurfaceTextureSource.access$000())
      Log.v("SurfaceTextureSource", "New frame from SurfaceTexture"); 
    SurfaceTextureSource.access$100(SurfaceTextureSource.this).open();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videosrc/SurfaceTextureSource$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
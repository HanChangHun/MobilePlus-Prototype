package android.filterpacks.videosrc;

import android.media.MediaPlayer;
import android.util.Log;

class null implements MediaPlayer.OnVideoSizeChangedListener {
  public void onVideoSizeChanged(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2) {
    if (MediaSource.access$000(MediaSource.this)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("MediaPlayer sent dimensions: ");
      stringBuilder.append(paramInt1);
      stringBuilder.append(" x ");
      stringBuilder.append(paramInt2);
      Log.v("MediaSource", stringBuilder.toString());
    } 
    if (!MediaSource.access$100(MediaSource.this)) {
      if (MediaSource.access$200(MediaSource.this) == 0 || MediaSource.access$200(MediaSource.this) == 180) {
        MediaSource.access$300(MediaSource.this).setDimensions(paramInt1, paramInt2);
      } else {
        MediaSource.access$300(MediaSource.this).setDimensions(paramInt2, paramInt1);
      } 
      MediaSource.access$402(MediaSource.this, paramInt1);
      MediaSource.access$502(MediaSource.this, paramInt2);
    } else if (MediaSource.access$300(MediaSource.this).getWidth() != paramInt1 || MediaSource.access$300(MediaSource.this).getHeight() != paramInt2) {
      Log.e("MediaSource", "Multiple video size change events received!");
    } 
    synchronized (MediaSource.this) {
      MediaSource.access$102(MediaSource.this, true);
      MediaSource.this.notify();
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videosrc/MediaSource$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
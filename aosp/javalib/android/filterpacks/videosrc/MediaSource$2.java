package android.filterpacks.videosrc;

import android.media.MediaPlayer;
import android.util.Log;

class null implements MediaPlayer.OnPreparedListener {
  public void onPrepared(MediaPlayer paramMediaPlayer) {
    if (MediaSource.access$000(MediaSource.this))
      Log.v("MediaSource", "MediaPlayer is prepared"); 
    synchronized (MediaSource.this) {
      MediaSource.access$602(MediaSource.this, true);
      MediaSource.this.notify();
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videosrc/MediaSource$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
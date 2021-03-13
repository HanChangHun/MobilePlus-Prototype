package android.filterpacks.videosrc;

import android.media.MediaPlayer;
import android.util.Log;

class null implements MediaPlayer.OnCompletionListener {
  public void onCompletion(MediaPlayer paramMediaPlayer) {
    if (MediaSource.access$000(MediaSource.this))
      Log.v("MediaSource", "MediaPlayer has completed playback"); 
    synchronized (MediaSource.this) {
      MediaSource.access$702(MediaSource.this, true);
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videosrc/MediaSource$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
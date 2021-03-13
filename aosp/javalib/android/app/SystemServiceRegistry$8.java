package android.app;

import android.media.AudioManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<AudioManager> {
  public AudioManager createService(ContextImpl paramContextImpl) {
    return new AudioManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$8.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
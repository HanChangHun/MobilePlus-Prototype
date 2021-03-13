package android.app;

import android.media.session.MediaSessionManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<MediaSessionManager> {
  public MediaSessionManager createService(ContextImpl paramContextImpl) {
    return new MediaSessionManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$69.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
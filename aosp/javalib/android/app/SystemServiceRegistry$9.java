package android.app;

import android.media.MediaRouter;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<MediaRouter> {
  public MediaRouter createService(ContextImpl paramContextImpl) {
    return new MediaRouter(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$9.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
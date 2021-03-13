package android.app;

import android.media.projection.MediaProjectionManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<MediaProjectionManager> {
  public MediaProjectionManager createService(ContextImpl paramContextImpl) {
    return new MediaProjectionManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$82.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
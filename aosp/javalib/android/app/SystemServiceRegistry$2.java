package android.app;

import android.os.ServiceManager;
import android.view.accessibility.CaptioningManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<CaptioningManager> {
  public CaptioningManager createService(ContextImpl paramContextImpl) {
    return new CaptioningManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<StatusBarManager> {
  public StatusBarManager createService(ContextImpl paramContextImpl) {
    return new StatusBarManager(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$43.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
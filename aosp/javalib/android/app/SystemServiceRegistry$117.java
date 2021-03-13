package android.app;

import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<DreamManager> {
  public DreamManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new DreamManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$117.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
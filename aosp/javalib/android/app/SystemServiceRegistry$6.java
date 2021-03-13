package android.app;

import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<UriGrantsManager> {
  public UriGrantsManager createService(ContextImpl paramContextImpl) {
    return new UriGrantsManager(paramContextImpl.getOuterContext(), paramContextImpl.mMainThread.getHandler());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
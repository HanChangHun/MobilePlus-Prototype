package android.app;

import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<SearchManager> {
  public SearchManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new SearchManager(paramContextImpl.getOuterContext(), paramContextImpl.mMainThread.getHandler());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$40.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
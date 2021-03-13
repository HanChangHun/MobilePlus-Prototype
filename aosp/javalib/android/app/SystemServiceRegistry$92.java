package android.app;

import android.hardware.location.ContextHubManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<ContextHubManager> {
  public ContextHubManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new ContextHubManager(paramContextImpl.getOuterContext(), paramContextImpl.mMainThread.getHandler().getLooper());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$92.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
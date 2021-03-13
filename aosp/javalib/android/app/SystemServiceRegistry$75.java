package android.app;

import android.media.tv.ITvInputManager;
import android.media.tv.TvInputManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<TvInputManager> {
  public TvInputManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new TvInputManager(ITvInputManager.Stub.asInterface(ServiceManager.getServiceOrThrow("tv_input")), paramContextImpl.getUserId());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$75.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
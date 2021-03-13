package android.app;

import android.content.IRestrictionsManager;
import android.content.RestrictionsManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<RestrictionsManager> {
  public RestrictionsManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new RestrictionsManager(paramContextImpl, IRestrictionsManager.Stub.asInterface(ServiceManager.getServiceOrThrow("restrictions")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$65.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.net.nsd.INsdManager;
import android.net.nsd.NsdManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<NsdManager> {
  public NsdManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    INsdManager iNsdManager = INsdManager.Stub.asInterface(ServiceManager.getServiceOrThrow("servicediscovery"));
    return new NsdManager(paramContextImpl.getOuterContext(), iNsdManager);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$37.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
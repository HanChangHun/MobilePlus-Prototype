package android.app;

import android.net.EthernetManager;
import android.net.IEthernetManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<EthernetManager> {
  public EthernetManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IEthernetManager iEthernetManager = IEthernetManager.Stub.asInterface(ServiceManager.getServiceOrThrow("ethernet"));
    return new EthernetManager(paramContextImpl.getOuterContext(), iEthernetManager);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$58.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
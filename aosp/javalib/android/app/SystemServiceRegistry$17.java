package android.app;

import android.net.IIpSecService;
import android.net.IpSecManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<IpSecManager> {
  public IpSecManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new IpSecManager(paramContextImpl, IIpSecService.Stub.asInterface(ServiceManager.getService("ipsec")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$17.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
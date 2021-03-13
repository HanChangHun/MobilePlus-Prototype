package android.app;

import android.net.IConnectivityManager;
import android.net.VpnManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<VpnManager> {
  public VpnManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new VpnManager(paramContextImpl, IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$18.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
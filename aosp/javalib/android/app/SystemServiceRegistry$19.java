package android.app;

import android.net.ConnectivityDiagnosticsManager;
import android.net.IConnectivityManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<ConnectivityDiagnosticsManager> {
  public ConnectivityDiagnosticsManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new ConnectivityDiagnosticsManager(paramContextImpl, IConnectivityManager.Stub.asInterface(ServiceManager.getServiceOrThrow("connectivity")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$19.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.IConnectivityManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.StaticApplicationContextServiceFetcher<ConnectivityManager> {
  public ConnectivityManager createService(Context paramContext) throws ServiceManager.ServiceNotFoundException {
    return new ConnectivityManager(paramContext, IConnectivityManager.Stub.asInterface(ServiceManager.getServiceOrThrow("connectivity")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$14.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.content.Context;
import android.net.IConnectivityManager;
import android.net.ITestNetworkManager;
import android.net.TestNetworkManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.StaticApplicationContextServiceFetcher<TestNetworkManager> {
  public TestNetworkManager createService(Context paramContext) throws ServiceManager.ServiceNotFoundException {
    IBinder iBinder = ServiceManager.getServiceOrThrow("connectivity");
    IConnectivityManager iConnectivityManager = IConnectivityManager.Stub.asInterface(iBinder);
    try {
      IBinder iBinder1 = iConnectivityManager.startOrGetTestNetworkService();
      return new TestNetworkManager(ITestNetworkManager.Stub.asInterface(iBinder1));
    } catch (RemoteException remoteException) {
      throw new ServiceManager.ServiceNotFoundException("test_network");
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$20.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
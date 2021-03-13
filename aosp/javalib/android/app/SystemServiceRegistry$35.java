package android.app;

import android.net.INetworkPolicyManager;
import android.net.NetworkPolicyManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<NetworkPolicyManager> {
  public NetworkPolicyManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new NetworkPolicyManager(paramContextImpl, INetworkPolicyManager.Stub.asInterface(ServiceManager.getServiceOrThrow("netpolicy")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$35.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
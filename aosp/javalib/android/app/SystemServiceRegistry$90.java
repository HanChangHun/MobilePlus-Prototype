package android.app;

import android.net.NetworkWatchlistManager;
import android.os.ServiceManager;
import com.android.internal.net.INetworkWatchlistManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<NetworkWatchlistManager> {
  public NetworkWatchlistManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new NetworkWatchlistManager(paramContextImpl, INetworkWatchlistManager.Stub.asInterface(ServiceManager.getServiceOrThrow("network_watchlist")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$90.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
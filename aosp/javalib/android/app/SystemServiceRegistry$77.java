package android.app;

import android.net.NetworkScoreManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<NetworkScoreManager> {
  public NetworkScoreManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new NetworkScoreManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$77.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
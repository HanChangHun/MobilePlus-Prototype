package android.app;

import android.app.usage.NetworkStatsManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<NetworkStatsManager> {
  public NetworkStatsManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new NetworkStatsManager(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$79.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.net.wifi.nl80211.WifiNl80211Manager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<WifiNl80211Manager> {
  public WifiNl80211Manager createService(ContextImpl paramContextImpl) {
    return new WifiNl80211Manager(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$59.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
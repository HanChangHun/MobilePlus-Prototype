package android.app;

import android.os.ServiceManager;
import android.telephony.TelephonyRegistryManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<TelephonyRegistryManager> {
  public TelephonyRegistryManager createService(ContextImpl paramContextImpl) {
    return new TelephonyRegistryManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$48.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
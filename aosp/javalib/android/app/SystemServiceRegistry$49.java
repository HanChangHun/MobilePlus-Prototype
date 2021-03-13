package android.app;

import android.os.ServiceManager;
import android.telecom.TelecomManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<TelecomManager> {
  public TelecomManager createService(ContextImpl paramContextImpl) {
    return new TelecomManager(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$49.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
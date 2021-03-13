package android.app;

import android.os.ServiceManager;
import android.telephony.MmsManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<MmsManager> {
  public MmsManager createService(ContextImpl paramContextImpl) {
    return new MmsManager(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$50.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.nfc.NfcManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<NfcManager> {
  public NfcManager createService(ContextImpl paramContextImpl) {
    return new NfcManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$25.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
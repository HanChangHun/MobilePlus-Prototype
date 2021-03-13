package android.app;

import android.hardware.radio.RadioManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<RadioManager> {
  public RadioManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new RadioManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$85.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
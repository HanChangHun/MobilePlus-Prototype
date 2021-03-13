package android.app;

import android.hardware.SensorPrivacyManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<SensorPrivacyManager> {
  public SensorPrivacyManager createService(ContextImpl paramContextImpl) {
    return SensorPrivacyManager.getInstance(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$42.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
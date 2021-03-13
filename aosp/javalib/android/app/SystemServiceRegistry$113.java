package android.app;

import android.hardware.lights.LightsManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<LightsManager> {
  public LightsManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new LightsManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$113.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
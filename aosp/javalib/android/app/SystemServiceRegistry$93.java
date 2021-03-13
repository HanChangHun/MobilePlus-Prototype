package android.app;

import android.os.IncidentManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<IncidentManager> {
  public IncidentManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new IncidentManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$93.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
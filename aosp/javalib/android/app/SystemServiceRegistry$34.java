package android.app;

import android.location.ILocationManager;
import android.location.LocationManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<LocationManager> {
  public LocationManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new LocationManager(paramContextImpl, ILocationManager.Stub.asInterface(ServiceManager.getServiceOrThrow("location")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$34.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
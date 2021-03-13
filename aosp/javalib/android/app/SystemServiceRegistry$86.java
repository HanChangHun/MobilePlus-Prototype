package android.app;

import android.os.HardwarePropertiesManager;
import android.os.IBinder;
import android.os.IHardwarePropertiesManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<HardwarePropertiesManager> {
  public HardwarePropertiesManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IBinder iBinder = ServiceManager.getServiceOrThrow("hardware_properties");
    return new HardwarePropertiesManager(paramContextImpl, IHardwarePropertiesManager.Stub.asInterface(iBinder));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$86.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
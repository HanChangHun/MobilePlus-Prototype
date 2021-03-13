package android.app;

import android.app.admin.DevicePolicyManager;
import android.app.admin.IDevicePolicyManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<DevicePolicyManager> {
  public DevicePolicyManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new DevicePolicyManager(paramContextImpl, IDevicePolicyManager.Stub.asInterface(ServiceManager.getServiceOrThrow("device_policy")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$22.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
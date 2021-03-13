package android.app;

import android.os.ISystemUpdateManager;
import android.os.ServiceManager;
import android.os.SystemUpdateManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<SystemUpdateManager> {
  public SystemUpdateManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new SystemUpdateManager(ISystemUpdateManager.Stub.asInterface(ServiceManager.getServiceOrThrow("system_update")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$46.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
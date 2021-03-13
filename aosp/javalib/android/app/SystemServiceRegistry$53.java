package android.app;

import android.debug.AdbManager;
import android.debug.IAdbManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<AdbManager> {
  public AdbManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new AdbManager(paramContextImpl, IAdbManager.Stub.asInterface(ServiceManager.getServiceOrThrow("adb")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$53.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
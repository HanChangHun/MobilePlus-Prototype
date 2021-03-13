package android.app;

import android.os.ServiceManager;
import android.service.oemlock.IOemLockService;
import android.service.oemlock.OemLockManager;

class null extends SystemServiceRegistry.StaticServiceFetcher<OemLockManager> {
  public OemLockManager createService() throws ServiceManager.ServiceNotFoundException {
    IOemLockService iOemLockService = IOemLockService.Stub.asInterface(ServiceManager.getServiceOrThrow("oem_lock"));
    return (iOemLockService != null) ? new OemLockManager(iOemLockService) : null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$81.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
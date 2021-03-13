package android.app;

import android.content.integrity.AppIntegrityManager;
import android.content.integrity.IAppIntegrityManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<AppIntegrityManager> {
  public AppIntegrityManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new AppIntegrityManager(IAppIntegrityManager.Stub.asInterface(ServiceManager.getServiceOrThrow("app_integrity")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$116.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
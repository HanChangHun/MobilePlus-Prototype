package android.app;

import android.os.ServiceManager;
import android.os.image.DynamicSystemManager;
import android.os.image.IDynamicSystemService;

class null extends SystemServiceRegistry.CachedServiceFetcher<DynamicSystemManager> {
  public DynamicSystemManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new DynamicSystemManager(IDynamicSystemService.Stub.asInterface(ServiceManager.getServiceOrThrow("dynamic_system")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$110.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
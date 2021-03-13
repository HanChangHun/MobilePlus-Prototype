package android.app;

import android.os.ServiceManager;
import com.android.internal.app.IAppOpsService;

class null extends SystemServiceRegistry.CachedServiceFetcher<AppOpsManager> {
  public AppOpsManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new AppOpsManager(paramContextImpl, IAppOpsService.Stub.asInterface(ServiceManager.getServiceOrThrow("appops")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$62.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
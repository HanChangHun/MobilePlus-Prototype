package android.app;

import android.app.role.RoleControllerManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<RoleControllerManager> {
  public RoleControllerManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new RoleControllerManager(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$108.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
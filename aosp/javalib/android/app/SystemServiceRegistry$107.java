package android.app;

import android.app.role.RoleManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<RoleManager> {
  public RoleManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new RoleManager(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$107.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
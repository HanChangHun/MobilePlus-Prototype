package android.app;

import android.content.pm.IPackageManager;
import android.os.ServiceManager;
import android.permission.PermissionManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<PermissionManager> {
  public PermissionManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IPackageManager iPackageManager = AppGlobals.getPackageManager();
    return new PermissionManager(paramContextImpl.getOuterContext(), iPackageManager);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$105.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
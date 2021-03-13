package android.app;

import android.os.ServiceManager;
import android.permission.PermissionControllerManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<PermissionControllerManager> {
  public PermissionControllerManager createService(ContextImpl paramContextImpl) {
    return new PermissionControllerManager(paramContextImpl.getOuterContext(), paramContextImpl.getMainThreadHandler());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$106.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
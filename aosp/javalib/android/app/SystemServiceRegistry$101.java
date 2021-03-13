package android.app;

import android.content.pm.CrossProfileApps;
import android.content.pm.ICrossProfileApps;
import android.os.IBinder;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<CrossProfileApps> {
  public CrossProfileApps createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IBinder iBinder = ServiceManager.getServiceOrThrow("crossprofileapps");
    return new CrossProfileApps(paramContextImpl.getOuterContext(), ICrossProfileApps.Stub.asInterface(iBinder));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$101.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
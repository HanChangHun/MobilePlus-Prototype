package android.app;

import android.os.ServiceManager;
import android.os.health.SystemHealthManager;
import com.android.internal.app.IBatteryStats;

class null extends SystemServiceRegistry.CachedServiceFetcher<SystemHealthManager> {
  public SystemHealthManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new SystemHealthManager(IBatteryStats.Stub.asInterface(ServiceManager.getServiceOrThrow("batterystats")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$91.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
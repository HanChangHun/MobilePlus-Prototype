package android.app;

import android.app.usage.IUsageStatsManager;
import android.app.usage.UsageStatsManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<UsageStatsManager> {
  public UsageStatsManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IUsageStatsManager iUsageStatsManager = IUsageStatsManager.Stub.asInterface(ServiceManager.getServiceOrThrow("usagestats"));
    return new UsageStatsManager(paramContextImpl.getOuterContext(), iUsageStatsManager);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$78.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
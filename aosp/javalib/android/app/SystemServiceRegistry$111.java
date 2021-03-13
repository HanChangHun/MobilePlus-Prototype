package android.app;

import android.os.BatteryStatsManager;
import android.os.ServiceManager;
import com.android.internal.app.IBatteryStats;

class null extends SystemServiceRegistry.CachedServiceFetcher<BatteryStatsManager> {
  public BatteryStatsManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new BatteryStatsManager(IBatteryStats.Stub.asInterface(ServiceManager.getServiceOrThrow("batterystats")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$111.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
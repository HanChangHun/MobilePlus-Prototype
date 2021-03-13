package android.app;

import android.os.BatteryManager;
import android.os.IBatteryPropertiesRegistrar;
import android.os.ServiceManager;
import com.android.internal.app.IBatteryStats;

class null extends SystemServiceRegistry.CachedServiceFetcher<BatteryManager> {
  public BatteryManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IBatteryStats iBatteryStats = IBatteryStats.Stub.asInterface(ServiceManager.getServiceOrThrow("batterystats"));
    return new BatteryManager(paramContextImpl, iBatteryStats, IBatteryPropertiesRegistrar.Stub.asInterface(ServiceManager.getServiceOrThrow("batteryproperties")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$24.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
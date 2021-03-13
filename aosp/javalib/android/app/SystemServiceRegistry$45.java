package android.app;

import android.app.usage.IStorageStatsManager;
import android.app.usage.StorageStatsManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<StorageStatsManager> {
  public StorageStatsManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new StorageStatsManager(paramContextImpl, IStorageStatsManager.Stub.asInterface(ServiceManager.getServiceOrThrow("storagestats")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$45.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.ServiceManager;
import android.os.SystemConfigManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<SystemConfigManager> {
  public SystemConfigManager createService(ContextImpl paramContextImpl) {
    return new SystemConfigManager();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$47.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
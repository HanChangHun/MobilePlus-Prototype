package android.app;

import android.content.pm.LauncherApps;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<LauncherApps> {
  public LauncherApps createService(ContextImpl paramContextImpl) {
    return new LauncherApps(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
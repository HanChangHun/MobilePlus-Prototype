package android.app;

import android.content.pm.IShortcutService;
import android.content.pm.ShortcutManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<ShortcutManager> {
  public ShortcutManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new ShortcutManager(paramContextImpl, IShortcutService.Stub.asInterface(ServiceManager.getServiceOrThrow("shortcut")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$88.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
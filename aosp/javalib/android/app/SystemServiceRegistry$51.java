package android.app;

import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<UiModeManager> {
  public UiModeManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new UiModeManager(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$51.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
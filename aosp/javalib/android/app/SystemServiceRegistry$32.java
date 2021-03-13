package android.app;

import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<KeyguardManager> {
  public KeyguardManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new KeyguardManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$32.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
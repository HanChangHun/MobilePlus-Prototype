package android.app;

import android.os.ServiceManager;
import android.os.storage.StorageManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<StorageManager> {
  public StorageManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new StorageManager(paramContextImpl, paramContextImpl.mMainThread.getHandler().getLooper());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$44.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
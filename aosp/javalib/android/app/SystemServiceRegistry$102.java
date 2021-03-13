package android.app;

import android.app.slice.SliceManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<SliceManager> {
  public SliceManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new SliceManager(paramContextImpl.getOuterContext(), paramContextImpl.mMainThread.getHandler());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$102.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
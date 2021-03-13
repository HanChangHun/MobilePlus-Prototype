package android.app;

import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<ActivityManager> {
  public ActivityManager createService(ContextImpl paramContextImpl) {
    return new ActivityManager(paramContextImpl.getOuterContext(), paramContextImpl.mMainThread.getHandler());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
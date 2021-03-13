package android.app;

import android.content.ClipboardManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<ClipboardManager> {
  public ClipboardManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new ClipboardManager(paramContextImpl.getOuterContext(), paramContextImpl.mMainThread.getHandler());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$13.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
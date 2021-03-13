package android.app;

import android.os.ServiceManager;
import android.view.WindowManager;
import android.view.WindowManagerImpl;

class null extends SystemServiceRegistry.CachedServiceFetcher<WindowManager> {
  public WindowManager createService(ContextImpl paramContextImpl) {
    return (WindowManager)new WindowManagerImpl(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$60.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
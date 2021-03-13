package android.app;

import android.hardware.display.DisplayManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<DisplayManager> {
  public DisplayManager createService(ContextImpl paramContextImpl) {
    return new DisplayManager(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$28.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.hardware.display.ColorDisplayManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<ColorDisplayManager> {
  public ColorDisplayManager createService(ContextImpl paramContextImpl) {
    return new ColorDisplayManager();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$29.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.hardware.camera2.CameraManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<CameraManager> {
  public CameraManager createService(ContextImpl paramContextImpl) {
    return new CameraManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$63.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
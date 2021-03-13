package android.app;

import android.hardware.SensorManager;
import android.hardware.SystemSensorManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<SensorManager> {
  public SensorManager createService(ContextImpl paramContextImpl) {
    return (SensorManager)new SystemSensorManager(paramContextImpl.getOuterContext(), paramContextImpl.mMainThread.getHandler().getLooper());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$41.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
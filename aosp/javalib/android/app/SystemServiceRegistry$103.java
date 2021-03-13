package android.app;

import android.app.timedetector.TimeDetector;
import android.app.timedetector.TimeDetectorImpl;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<TimeDetector> {
  public TimeDetector createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return (TimeDetector)new TimeDetectorImpl();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$103.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
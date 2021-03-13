package android.app;

import android.app.timezonedetector.TimeZoneDetector;
import android.app.timezonedetector.TimeZoneDetectorImpl;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<TimeZoneDetector> {
  public TimeZoneDetector createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return (TimeZoneDetector)new TimeZoneDetectorImpl();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$104.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
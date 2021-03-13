package android.hardware.location;

import android.annotation.SystemApi;
import android.location.Location;

@SystemApi
public abstract class GeofenceHardwareMonitorCallback {
  @Deprecated
  public void onMonitoringSystemChange(int paramInt, boolean paramBoolean, Location paramLocation) {}
  
  public void onMonitoringSystemChange(GeofenceHardwareMonitorEvent paramGeofenceHardwareMonitorEvent) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardwareMonitorCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
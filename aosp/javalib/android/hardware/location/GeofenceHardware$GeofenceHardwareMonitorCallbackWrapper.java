package android.hardware.location;

import android.os.Build;
import java.lang.ref.WeakReference;

class GeofenceHardwareMonitorCallbackWrapper extends IGeofenceHardwareMonitorCallback.Stub {
  private WeakReference<GeofenceHardwareMonitorCallback> mCallback;
  
  GeofenceHardwareMonitorCallbackWrapper(GeofenceHardwareMonitorCallback paramGeofenceHardwareMonitorCallback) {
    this.mCallback = new WeakReference<>(paramGeofenceHardwareMonitorCallback);
  }
  
  public void onMonitoringSystemChange(GeofenceHardwareMonitorEvent paramGeofenceHardwareMonitorEvent) {
    boolean bool;
    GeofenceHardwareMonitorCallback geofenceHardwareMonitorCallback = this.mCallback.get();
    if (geofenceHardwareMonitorCallback == null)
      return; 
    int i = paramGeofenceHardwareMonitorEvent.getMonitoringType();
    if (paramGeofenceHardwareMonitorEvent.getMonitoringStatus() == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    geofenceHardwareMonitorCallback.onMonitoringSystemChange(i, bool, paramGeofenceHardwareMonitorEvent.getLocation());
    if (Build.VERSION.SDK_INT >= 21)
      geofenceHardwareMonitorCallback.onMonitoringSystemChange(paramGeofenceHardwareMonitorEvent); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardware$GeofenceHardwareMonitorCallbackWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
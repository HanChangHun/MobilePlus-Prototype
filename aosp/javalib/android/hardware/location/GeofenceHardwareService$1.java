package android.hardware.location;

import android.location.IFusedGeofenceHardware;
import android.location.IGpsGeofenceHardware;
import android.os.Binder;

class null extends IGeofenceHardware.Stub {
  public boolean addCircularFence(int paramInt, GeofenceHardwareRequestParcelable paramGeofenceHardwareRequestParcelable, IGeofenceHardwareCallback paramIGeofenceHardwareCallback) {
    GeofenceHardwareService.access$100(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
    GeofenceHardwareService.access$200(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), paramInt);
    return GeofenceHardwareService.access$000(GeofenceHardwareService.this).addCircularFence(paramInt, paramGeofenceHardwareRequestParcelable, paramIGeofenceHardwareCallback);
  }
  
  public int[] getMonitoringTypes() {
    GeofenceHardwareService.access$100(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
    return GeofenceHardwareService.access$000(GeofenceHardwareService.this).getMonitoringTypes();
  }
  
  public int getStatusOfMonitoringType(int paramInt) {
    GeofenceHardwareService.access$100(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
    return GeofenceHardwareService.access$000(GeofenceHardwareService.this).getStatusOfMonitoringType(paramInt);
  }
  
  public boolean pauseGeofence(int paramInt1, int paramInt2) {
    GeofenceHardwareService.access$100(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
    GeofenceHardwareService.access$200(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), paramInt2);
    return GeofenceHardwareService.access$000(GeofenceHardwareService.this).pauseGeofence(paramInt1, paramInt2);
  }
  
  public boolean registerForMonitorStateChangeCallback(int paramInt, IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback) {
    GeofenceHardwareService.access$100(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
    GeofenceHardwareService.access$200(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), paramInt);
    return GeofenceHardwareService.access$000(GeofenceHardwareService.this).registerForMonitorStateChangeCallback(paramInt, paramIGeofenceHardwareMonitorCallback);
  }
  
  public boolean removeGeofence(int paramInt1, int paramInt2) {
    GeofenceHardwareService.access$100(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
    GeofenceHardwareService.access$200(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), paramInt2);
    return GeofenceHardwareService.access$000(GeofenceHardwareService.this).removeGeofence(paramInt1, paramInt2);
  }
  
  public boolean resumeGeofence(int paramInt1, int paramInt2, int paramInt3) {
    GeofenceHardwareService.access$100(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
    GeofenceHardwareService.access$200(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), paramInt2);
    return GeofenceHardwareService.access$000(GeofenceHardwareService.this).resumeGeofence(paramInt1, paramInt2, paramInt3);
  }
  
  public void setFusedGeofenceHardware(IFusedGeofenceHardware paramIFusedGeofenceHardware) {
    GeofenceHardwareService.access$000(GeofenceHardwareService.this).setFusedGeofenceHardware(paramIFusedGeofenceHardware);
  }
  
  public void setGpsGeofenceHardware(IGpsGeofenceHardware paramIGpsGeofenceHardware) {
    GeofenceHardwareService.access$000(GeofenceHardwareService.this).setGpsHardwareGeofence(paramIGpsGeofenceHardware);
  }
  
  public boolean unregisterForMonitorStateChangeCallback(int paramInt, IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback) {
    GeofenceHardwareService.access$100(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
    GeofenceHardwareService.access$200(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), paramInt);
    return GeofenceHardwareService.access$000(GeofenceHardwareService.this).unregisterForMonitorStateChangeCallback(paramInt, paramIGeofenceHardwareMonitorCallback);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardwareService$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
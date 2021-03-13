package android.hardware.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.IFusedGeofenceHardware;
import android.location.IGpsGeofenceHardware;
import android.os.Binder;
import android.os.IBinder;

public class GeofenceHardwareService extends Service {
  private IBinder mBinder = (IBinder)new IGeofenceHardware.Stub() {
      public boolean addCircularFence(int param1Int, GeofenceHardwareRequestParcelable param1GeofenceHardwareRequestParcelable, IGeofenceHardwareCallback param1IGeofenceHardwareCallback) {
        GeofenceHardwareService.this.mContext.enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
        GeofenceHardwareService.this.checkPermission(Binder.getCallingPid(), Binder.getCallingUid(), param1Int);
        return GeofenceHardwareService.this.mGeofenceHardwareImpl.addCircularFence(param1Int, param1GeofenceHardwareRequestParcelable, param1IGeofenceHardwareCallback);
      }
      
      public int[] getMonitoringTypes() {
        GeofenceHardwareService.this.mContext.enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
        return GeofenceHardwareService.this.mGeofenceHardwareImpl.getMonitoringTypes();
      }
      
      public int getStatusOfMonitoringType(int param1Int) {
        GeofenceHardwareService.this.mContext.enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
        return GeofenceHardwareService.this.mGeofenceHardwareImpl.getStatusOfMonitoringType(param1Int);
      }
      
      public boolean pauseGeofence(int param1Int1, int param1Int2) {
        GeofenceHardwareService.this.mContext.enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
        GeofenceHardwareService.this.checkPermission(Binder.getCallingPid(), Binder.getCallingUid(), param1Int2);
        return GeofenceHardwareService.this.mGeofenceHardwareImpl.pauseGeofence(param1Int1, param1Int2);
      }
      
      public boolean registerForMonitorStateChangeCallback(int param1Int, IGeofenceHardwareMonitorCallback param1IGeofenceHardwareMonitorCallback) {
        GeofenceHardwareService.this.mContext.enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
        GeofenceHardwareService.this.checkPermission(Binder.getCallingPid(), Binder.getCallingUid(), param1Int);
        return GeofenceHardwareService.this.mGeofenceHardwareImpl.registerForMonitorStateChangeCallback(param1Int, param1IGeofenceHardwareMonitorCallback);
      }
      
      public boolean removeGeofence(int param1Int1, int param1Int2) {
        GeofenceHardwareService.this.mContext.enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
        GeofenceHardwareService.this.checkPermission(Binder.getCallingPid(), Binder.getCallingUid(), param1Int2);
        return GeofenceHardwareService.this.mGeofenceHardwareImpl.removeGeofence(param1Int1, param1Int2);
      }
      
      public boolean resumeGeofence(int param1Int1, int param1Int2, int param1Int3) {
        GeofenceHardwareService.this.mContext.enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
        GeofenceHardwareService.this.checkPermission(Binder.getCallingPid(), Binder.getCallingUid(), param1Int2);
        return GeofenceHardwareService.this.mGeofenceHardwareImpl.resumeGeofence(param1Int1, param1Int2, param1Int3);
      }
      
      public void setFusedGeofenceHardware(IFusedGeofenceHardware param1IFusedGeofenceHardware) {
        GeofenceHardwareService.this.mGeofenceHardwareImpl.setFusedGeofenceHardware(param1IFusedGeofenceHardware);
      }
      
      public void setGpsGeofenceHardware(IGpsGeofenceHardware param1IGpsGeofenceHardware) {
        GeofenceHardwareService.this.mGeofenceHardwareImpl.setGpsHardwareGeofence(param1IGpsGeofenceHardware);
      }
      
      public boolean unregisterForMonitorStateChangeCallback(int param1Int, IGeofenceHardwareMonitorCallback param1IGeofenceHardwareMonitorCallback) {
        GeofenceHardwareService.this.mContext.enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
        GeofenceHardwareService.this.checkPermission(Binder.getCallingPid(), Binder.getCallingUid(), param1Int);
        return GeofenceHardwareService.this.mGeofenceHardwareImpl.unregisterForMonitorStateChangeCallback(param1Int, param1IGeofenceHardwareMonitorCallback);
      }
    };
  
  private Context mContext;
  
  private GeofenceHardwareImpl mGeofenceHardwareImpl;
  
  private void checkPermission(int paramInt1, int paramInt2, int paramInt3) {
    if (this.mGeofenceHardwareImpl.getAllowedResolutionLevel(paramInt1, paramInt2) >= this.mGeofenceHardwareImpl.getMonitoringResolutionLevel(paramInt3))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Insufficient permissions to access hardware geofence for type: ");
    stringBuilder.append(paramInt3);
    throw new SecurityException(stringBuilder.toString());
  }
  
  public IBinder onBind(Intent paramIntent) {
    return this.mBinder;
  }
  
  public void onCreate() {
    this.mContext = (Context)this;
    this.mGeofenceHardwareImpl = GeofenceHardwareImpl.getInstance((Context)this);
  }
  
  public void onDestroy() {
    this.mGeofenceHardwareImpl = null;
  }
  
  public boolean onUnbind(Intent paramIntent) {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardwareService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
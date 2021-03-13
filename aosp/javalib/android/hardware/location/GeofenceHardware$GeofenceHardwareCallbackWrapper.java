package android.hardware.location;

import android.location.Location;
import java.lang.ref.WeakReference;

class GeofenceHardwareCallbackWrapper extends IGeofenceHardwareCallback.Stub {
  private WeakReference<GeofenceHardwareCallback> mCallback;
  
  GeofenceHardwareCallbackWrapper(GeofenceHardwareCallback paramGeofenceHardwareCallback) {
    this.mCallback = new WeakReference<>(paramGeofenceHardwareCallback);
  }
  
  public void onGeofenceAdd(int paramInt1, int paramInt2) {
    GeofenceHardwareCallback geofenceHardwareCallback = this.mCallback.get();
    if (geofenceHardwareCallback != null)
      geofenceHardwareCallback.onGeofenceAdd(paramInt1, paramInt2); 
  }
  
  public void onGeofencePause(int paramInt1, int paramInt2) {
    GeofenceHardwareCallback geofenceHardwareCallback = this.mCallback.get();
    if (geofenceHardwareCallback != null)
      geofenceHardwareCallback.onGeofencePause(paramInt1, paramInt2); 
  }
  
  public void onGeofenceRemove(int paramInt1, int paramInt2) {
    GeofenceHardwareCallback geofenceHardwareCallback = this.mCallback.get();
    if (geofenceHardwareCallback != null) {
      geofenceHardwareCallback.onGeofenceRemove(paramInt1, paramInt2);
      GeofenceHardware.access$000(GeofenceHardware.this, geofenceHardwareCallback);
    } 
  }
  
  public void onGeofenceResume(int paramInt1, int paramInt2) {
    GeofenceHardwareCallback geofenceHardwareCallback = this.mCallback.get();
    if (geofenceHardwareCallback != null)
      geofenceHardwareCallback.onGeofenceResume(paramInt1, paramInt2); 
  }
  
  public void onGeofenceTransition(int paramInt1, int paramInt2, Location paramLocation, long paramLong, int paramInt3) {
    GeofenceHardwareCallback geofenceHardwareCallback = this.mCallback.get();
    if (geofenceHardwareCallback != null)
      geofenceHardwareCallback.onGeofenceTransition(paramInt1, paramInt2, paramLocation, paramLong, paramInt3); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardware$GeofenceHardwareCallbackWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
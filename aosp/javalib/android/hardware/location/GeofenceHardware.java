package android.hardware.location;

import android.annotation.SystemApi;
import android.location.Location;
import android.os.Build;
import android.os.RemoteException;
import java.lang.ref.WeakReference;
import java.util.HashMap;

@SystemApi
public final class GeofenceHardware {
  public static final int GEOFENCE_ENTERED = 1;
  
  public static final int GEOFENCE_ERROR_ID_EXISTS = 2;
  
  public static final int GEOFENCE_ERROR_ID_UNKNOWN = 3;
  
  public static final int GEOFENCE_ERROR_INSUFFICIENT_MEMORY = 6;
  
  public static final int GEOFENCE_ERROR_INVALID_TRANSITION = 4;
  
  public static final int GEOFENCE_ERROR_TOO_MANY_GEOFENCES = 1;
  
  public static final int GEOFENCE_EXITED = 2;
  
  public static final int GEOFENCE_FAILURE = 5;
  
  public static final int GEOFENCE_SUCCESS = 0;
  
  public static final int GEOFENCE_UNCERTAIN = 4;
  
  public static final int MONITORING_TYPE_FUSED_HARDWARE = 1;
  
  public static final int MONITORING_TYPE_GPS_HARDWARE = 0;
  
  public static final int MONITOR_CURRENTLY_AVAILABLE = 0;
  
  public static final int MONITOR_CURRENTLY_UNAVAILABLE = 1;
  
  public static final int MONITOR_UNSUPPORTED = 2;
  
  static final int NUM_MONITORS = 2;
  
  public static final int SOURCE_TECHNOLOGY_BLUETOOTH = 16;
  
  public static final int SOURCE_TECHNOLOGY_CELL = 8;
  
  public static final int SOURCE_TECHNOLOGY_GNSS = 1;
  
  public static final int SOURCE_TECHNOLOGY_SENSORS = 4;
  
  public static final int SOURCE_TECHNOLOGY_WIFI = 2;
  
  private HashMap<GeofenceHardwareCallback, GeofenceHardwareCallbackWrapper> mCallbacks = new HashMap<>();
  
  private HashMap<GeofenceHardwareMonitorCallback, GeofenceHardwareMonitorCallbackWrapper> mMonitorCallbacks = new HashMap<>();
  
  private IGeofenceHardware mService;
  
  public GeofenceHardware(IGeofenceHardware paramIGeofenceHardware) {
    this.mService = paramIGeofenceHardware;
  }
  
  private GeofenceHardwareCallbackWrapper getCallbackWrapper(GeofenceHardwareCallback paramGeofenceHardwareCallback) {
    synchronized (this.mCallbacks) {
      GeofenceHardwareCallbackWrapper geofenceHardwareCallbackWrapper1 = this.mCallbacks.get(paramGeofenceHardwareCallback);
      GeofenceHardwareCallbackWrapper geofenceHardwareCallbackWrapper2 = geofenceHardwareCallbackWrapper1;
      if (geofenceHardwareCallbackWrapper1 == null) {
        geofenceHardwareCallbackWrapper2 = new GeofenceHardwareCallbackWrapper();
        this(this, paramGeofenceHardwareCallback);
        this.mCallbacks.put(paramGeofenceHardwareCallback, geofenceHardwareCallbackWrapper2);
      } 
      return geofenceHardwareCallbackWrapper2;
    } 
  }
  
  private GeofenceHardwareMonitorCallbackWrapper getMonitorCallbackWrapper(GeofenceHardwareMonitorCallback paramGeofenceHardwareMonitorCallback) {
    synchronized (this.mMonitorCallbacks) {
      GeofenceHardwareMonitorCallbackWrapper geofenceHardwareMonitorCallbackWrapper1 = this.mMonitorCallbacks.get(paramGeofenceHardwareMonitorCallback);
      GeofenceHardwareMonitorCallbackWrapper geofenceHardwareMonitorCallbackWrapper2 = geofenceHardwareMonitorCallbackWrapper1;
      if (geofenceHardwareMonitorCallbackWrapper1 == null) {
        geofenceHardwareMonitorCallbackWrapper2 = new GeofenceHardwareMonitorCallbackWrapper();
        this(this, paramGeofenceHardwareMonitorCallback);
        this.mMonitorCallbacks.put(paramGeofenceHardwareMonitorCallback, geofenceHardwareMonitorCallbackWrapper2);
      } 
      return geofenceHardwareMonitorCallbackWrapper2;
    } 
  }
  
  private void removeCallback(GeofenceHardwareCallback paramGeofenceHardwareCallback) {
    synchronized (this.mCallbacks) {
      this.mCallbacks.remove(paramGeofenceHardwareCallback);
      return;
    } 
  }
  
  private void removeMonitorCallback(GeofenceHardwareMonitorCallback paramGeofenceHardwareMonitorCallback) {
    synchronized (this.mMonitorCallbacks) {
      this.mMonitorCallbacks.remove(paramGeofenceHardwareMonitorCallback);
      return;
    } 
  }
  
  public boolean addGeofence(int paramInt1, int paramInt2, GeofenceHardwareRequest paramGeofenceHardwareRequest, GeofenceHardwareCallback paramGeofenceHardwareCallback) {
    try {
      if (paramGeofenceHardwareRequest.getType() == 0) {
        IGeofenceHardware iGeofenceHardware = this.mService;
        GeofenceHardwareRequestParcelable geofenceHardwareRequestParcelable = new GeofenceHardwareRequestParcelable();
        this(paramInt1, paramGeofenceHardwareRequest);
        return iGeofenceHardware.addCircularFence(paramInt2, geofenceHardwareRequestParcelable, getCallbackWrapper(paramGeofenceHardwareCallback));
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this("Geofence Request type not supported");
      throw illegalArgumentException;
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public int[] getMonitoringTypes() {
    try {
      return this.mService.getMonitoringTypes();
    } catch (RemoteException remoteException) {
      return new int[0];
    } 
  }
  
  public int getStatusOfMonitoringType(int paramInt) {
    try {
      return this.mService.getStatusOfMonitoringType(paramInt);
    } catch (RemoteException remoteException) {
      return 2;
    } 
  }
  
  public boolean pauseGeofence(int paramInt1, int paramInt2) {
    try {
      return this.mService.pauseGeofence(paramInt1, paramInt2);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean registerForMonitorStateChangeCallback(int paramInt, GeofenceHardwareMonitorCallback paramGeofenceHardwareMonitorCallback) {
    try {
      return this.mService.registerForMonitorStateChangeCallback(paramInt, getMonitorCallbackWrapper(paramGeofenceHardwareMonitorCallback));
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean removeGeofence(int paramInt1, int paramInt2) {
    try {
      return this.mService.removeGeofence(paramInt1, paramInt2);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean resumeGeofence(int paramInt1, int paramInt2, int paramInt3) {
    try {
      return this.mService.resumeGeofence(paramInt1, paramInt2, paramInt3);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean unregisterForMonitorStateChangeCallback(int paramInt, GeofenceHardwareMonitorCallback paramGeofenceHardwareMonitorCallback) {
    boolean bool = false;
    try {
      boolean bool1 = this.mService.unregisterForMonitorStateChangeCallback(paramInt, getMonitorCallbackWrapper(paramGeofenceHardwareMonitorCallback));
      if (bool1) {
        bool = bool1;
        removeMonitorCallback(paramGeofenceHardwareMonitorCallback);
      } 
      bool = bool1;
    } catch (RemoteException remoteException) {}
    return bool;
  }
  
  class GeofenceHardwareCallbackWrapper extends IGeofenceHardwareCallback.Stub {
    private WeakReference<GeofenceHardwareCallback> mCallback;
    
    GeofenceHardwareCallbackWrapper(GeofenceHardwareCallback param1GeofenceHardwareCallback) {
      this.mCallback = new WeakReference<>(param1GeofenceHardwareCallback);
    }
    
    public void onGeofenceAdd(int param1Int1, int param1Int2) {
      GeofenceHardwareCallback geofenceHardwareCallback = this.mCallback.get();
      if (geofenceHardwareCallback != null)
        geofenceHardwareCallback.onGeofenceAdd(param1Int1, param1Int2); 
    }
    
    public void onGeofencePause(int param1Int1, int param1Int2) {
      GeofenceHardwareCallback geofenceHardwareCallback = this.mCallback.get();
      if (geofenceHardwareCallback != null)
        geofenceHardwareCallback.onGeofencePause(param1Int1, param1Int2); 
    }
    
    public void onGeofenceRemove(int param1Int1, int param1Int2) {
      GeofenceHardwareCallback geofenceHardwareCallback = this.mCallback.get();
      if (geofenceHardwareCallback != null) {
        geofenceHardwareCallback.onGeofenceRemove(param1Int1, param1Int2);
        GeofenceHardware.this.removeCallback(geofenceHardwareCallback);
      } 
    }
    
    public void onGeofenceResume(int param1Int1, int param1Int2) {
      GeofenceHardwareCallback geofenceHardwareCallback = this.mCallback.get();
      if (geofenceHardwareCallback != null)
        geofenceHardwareCallback.onGeofenceResume(param1Int1, param1Int2); 
    }
    
    public void onGeofenceTransition(int param1Int1, int param1Int2, Location param1Location, long param1Long, int param1Int3) {
      GeofenceHardwareCallback geofenceHardwareCallback = this.mCallback.get();
      if (geofenceHardwareCallback != null)
        geofenceHardwareCallback.onGeofenceTransition(param1Int1, param1Int2, param1Location, param1Long, param1Int3); 
    }
  }
  
  class GeofenceHardwareMonitorCallbackWrapper extends IGeofenceHardwareMonitorCallback.Stub {
    private WeakReference<GeofenceHardwareMonitorCallback> mCallback;
    
    GeofenceHardwareMonitorCallbackWrapper(GeofenceHardwareMonitorCallback param1GeofenceHardwareMonitorCallback) {
      this.mCallback = new WeakReference<>(param1GeofenceHardwareMonitorCallback);
    }
    
    public void onMonitoringSystemChange(GeofenceHardwareMonitorEvent param1GeofenceHardwareMonitorEvent) {
      boolean bool;
      GeofenceHardwareMonitorCallback geofenceHardwareMonitorCallback = this.mCallback.get();
      if (geofenceHardwareMonitorCallback == null)
        return; 
      int i = param1GeofenceHardwareMonitorEvent.getMonitoringType();
      if (param1GeofenceHardwareMonitorEvent.getMonitoringStatus() == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      geofenceHardwareMonitorCallback.onMonitoringSystemChange(i, bool, param1GeofenceHardwareMonitorEvent.getLocation());
      if (Build.VERSION.SDK_INT >= 21)
        geofenceHardwareMonitorCallback.onMonitoringSystemChange(param1GeofenceHardwareMonitorEvent); 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardware.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
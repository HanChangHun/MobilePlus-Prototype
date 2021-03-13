package android.hardware.location;

import android.location.IFusedGeofenceHardware;
import android.location.IGpsGeofenceHardware;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IGeofenceHardware {
  public boolean addCircularFence(int paramInt, GeofenceHardwareRequestParcelable paramGeofenceHardwareRequestParcelable, IGeofenceHardwareCallback paramIGeofenceHardwareCallback) throws RemoteException {
    return false;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public int[] getMonitoringTypes() throws RemoteException {
    return null;
  }
  
  public int getStatusOfMonitoringType(int paramInt) throws RemoteException {
    return 0;
  }
  
  public boolean pauseGeofence(int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean registerForMonitorStateChangeCallback(int paramInt, IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback) throws RemoteException {
    return false;
  }
  
  public boolean removeGeofence(int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean resumeGeofence(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    return false;
  }
  
  public void setFusedGeofenceHardware(IFusedGeofenceHardware paramIFusedGeofenceHardware) throws RemoteException {}
  
  public void setGpsGeofenceHardware(IGpsGeofenceHardware paramIGpsGeofenceHardware) throws RemoteException {}
  
  public boolean unregisterForMonitorStateChangeCallback(int paramInt, IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardware$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
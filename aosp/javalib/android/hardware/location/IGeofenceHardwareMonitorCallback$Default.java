package android.hardware.location;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IGeofenceHardwareMonitorCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onMonitoringSystemChange(GeofenceHardwareMonitorEvent paramGeofenceHardwareMonitorEvent) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardwareMonitorCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
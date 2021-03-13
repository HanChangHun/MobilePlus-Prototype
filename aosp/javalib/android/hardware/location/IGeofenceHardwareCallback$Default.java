package android.hardware.location;

import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IGeofenceHardwareCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onGeofenceAdd(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onGeofencePause(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onGeofenceRemove(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onGeofenceResume(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onGeofenceTransition(int paramInt1, int paramInt2, Location paramLocation, long paramLong, int paramInt3) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardwareCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
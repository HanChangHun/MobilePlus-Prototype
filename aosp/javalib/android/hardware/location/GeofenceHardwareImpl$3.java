package android.hardware.location;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;

class null extends Handler {
  public void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 1) {
      GeofenceHardwareImpl.Reaper reaper;
      if (i != 2) {
        if (i == 3) {
          reaper = (GeofenceHardwareImpl.Reaper)paramMessage.obj;
          GeofenceHardwareImpl.access$200(GeofenceHardwareImpl.this).remove(reaper);
        } 
      } else {
        IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = (IGeofenceHardwareMonitorCallback)((Message)reaper).obj;
        i = ((Message)reaper).arg1;
        reaper = new GeofenceHardwareImpl.Reaper(GeofenceHardwareImpl.this, iGeofenceHardwareMonitorCallback, i);
        if (!GeofenceHardwareImpl.access$200(GeofenceHardwareImpl.this).contains(reaper)) {
          GeofenceHardwareImpl.access$200(GeofenceHardwareImpl.this).add(reaper);
          IBinder iBinder = iGeofenceHardwareMonitorCallback.asBinder();
          try {
            iBinder.linkToDeath(reaper, 0);
          } catch (RemoteException remoteException) {}
        } 
      } 
    } else {
      IGeofenceHardwareCallback iGeofenceHardwareCallback = (IGeofenceHardwareCallback)((Message)remoteException).obj;
      i = ((Message)remoteException).arg1;
      GeofenceHardwareImpl.Reaper reaper = new GeofenceHardwareImpl.Reaper(GeofenceHardwareImpl.this, iGeofenceHardwareCallback, i);
      if (!GeofenceHardwareImpl.access$200(GeofenceHardwareImpl.this).contains(reaper)) {
        GeofenceHardwareImpl.access$200(GeofenceHardwareImpl.this).add(reaper);
        IBinder iBinder = iGeofenceHardwareCallback.asBinder();
        try {
          iBinder.linkToDeath(reaper, 0);
        } catch (RemoteException remoteException1) {}
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardwareImpl$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
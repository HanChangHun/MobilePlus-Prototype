package android.hardware.location;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;

class null extends Handler {
  public void handleMessage(Message paramMessage) {
    ArrayList<IGeofenceHardwareMonitorCallback> arrayList;
    int i = paramMessage.what;
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i == 4) {
            IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = (IGeofenceHardwareMonitorCallback)paramMessage.obj;
            if (GeofenceHardwareImpl.access$500()) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Monitor callback reaped:");
              stringBuilder.append(iGeofenceHardwareMonitorCallback);
              Log.d("GeofenceHardwareImpl", stringBuilder.toString());
            } 
            arrayList = GeofenceHardwareImpl.access$1100(GeofenceHardwareImpl.this)[paramMessage.arg1];
            if (arrayList != null && arrayList.contains(iGeofenceHardwareMonitorCallback))
              arrayList.remove(iGeofenceHardwareMonitorCallback); 
          } 
        } else {
          i = ((Message)arrayList).arg1;
          IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = (IGeofenceHardwareMonitorCallback)((Message)arrayList).obj;
          arrayList = GeofenceHardwareImpl.access$1100(GeofenceHardwareImpl.this)[i];
          if (arrayList != null)
            arrayList.remove(iGeofenceHardwareMonitorCallback); 
        } 
      } else {
        i = ((Message)arrayList).arg1;
        IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = (IGeofenceHardwareMonitorCallback)((Message)arrayList).obj;
        ArrayList<IGeofenceHardwareMonitorCallback> arrayList1 = GeofenceHardwareImpl.access$1100(GeofenceHardwareImpl.this)[i];
        arrayList = arrayList1;
        if (arrayList1 == null) {
          arrayList = new ArrayList();
          GeofenceHardwareImpl.access$1100(GeofenceHardwareImpl.this)[i] = arrayList;
        } 
        if (!arrayList.contains(iGeofenceHardwareMonitorCallback))
          arrayList.add(iGeofenceHardwareMonitorCallback); 
      } 
    } else {
      GeofenceHardwareMonitorEvent geofenceHardwareMonitorEvent = (GeofenceHardwareMonitorEvent)((Message)arrayList).obj;
      ArrayList arrayList1 = GeofenceHardwareImpl.access$1100(GeofenceHardwareImpl.this)[geofenceHardwareMonitorEvent.getMonitoringType()];
      if (arrayList1 != null) {
        if (GeofenceHardwareImpl.access$500()) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("MonitoringSystemChangeCallback: ");
          stringBuilder.append(geofenceHardwareMonitorEvent);
          Log.d("GeofenceHardwareImpl", stringBuilder.toString());
        } 
        for (IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback : arrayList1) {
          try {
            iGeofenceHardwareMonitorCallback.onMonitoringSystemChange(geofenceHardwareMonitorEvent);
          } catch (RemoteException remoteException) {
            Log.d("GeofenceHardwareImpl", "Error reporting onMonitoringSystemChange.", (Throwable)remoteException);
          } 
        } 
      } 
      GeofenceHardwareImpl.access$100(GeofenceHardwareImpl.this);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardwareImpl$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
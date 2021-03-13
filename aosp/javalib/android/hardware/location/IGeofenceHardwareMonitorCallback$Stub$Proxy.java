package android.hardware.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IGeofenceHardwareMonitorCallback {
  public static IGeofenceHardwareMonitorCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.location.IGeofenceHardwareMonitorCallback";
  }
  
  public void onMonitoringSystemChange(GeofenceHardwareMonitorEvent paramGeofenceHardwareMonitorEvent) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareMonitorCallback");
      if (paramGeofenceHardwareMonitorEvent != null) {
        parcel.writeInt(1);
        paramGeofenceHardwareMonitorEvent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IGeofenceHardwareMonitorCallback.Stub.getDefaultImpl() != null) {
        IGeofenceHardwareMonitorCallback.Stub.getDefaultImpl().onMonitoringSystemChange(paramGeofenceHardwareMonitorEvent);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardwareMonitorCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
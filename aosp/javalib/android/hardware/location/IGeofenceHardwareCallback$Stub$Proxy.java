package android.hardware.location;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IGeofenceHardwareCallback {
  public static IGeofenceHardwareCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.location.IGeofenceHardwareCallback";
  }
  
  public void onGeofenceAdd(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(2, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
        IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceAdd(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onGeofencePause(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(4, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
        IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofencePause(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onGeofenceRemove(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(3, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
        IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceRemove(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onGeofenceResume(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(5, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
        IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceResume(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onGeofenceTransition(int paramInt1, int paramInt2, Location paramLocation, long paramLong, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
      try {
        parcel.writeInt(paramInt1);
        try {
          parcel.writeInt(paramInt2);
          if (paramLocation != null) {
            parcel.writeInt(1);
            paramLocation.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          try {
            parcel.writeLong(paramLong);
            try {
              parcel.writeInt(paramInt3);
              try {
                if (!this.mRemote.transact(1, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
                  IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceTransition(paramInt1, paramInt2, paramLocation, paramLong, paramInt3);
                  parcel.recycle();
                  return;
                } 
                parcel.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel.recycle();
    throw paramLocation;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardwareCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
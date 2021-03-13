package android.hardware.location;

import android.location.IFusedGeofenceHardware;
import android.location.IGpsGeofenceHardware;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IGeofenceHardware {
  public static IGeofenceHardware sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public boolean addCircularFence(int paramInt, GeofenceHardwareRequestParcelable paramGeofenceHardwareRequestParcelable, IGeofenceHardwareCallback paramIGeofenceHardwareCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
      parcel1.writeInt(paramInt);
      boolean bool = true;
      if (paramGeofenceHardwareRequestParcelable != null) {
        parcel1.writeInt(1);
        paramGeofenceHardwareRequestParcelable.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIGeofenceHardwareCallback != null) {
        iBinder = paramIGeofenceHardwareCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
        bool = IGeofenceHardware.Stub.getDefaultImpl().addCircularFence(paramInt, paramGeofenceHardwareRequestParcelable, paramIGeofenceHardwareCallback);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.location.IGeofenceHardware";
  }
  
  public int[] getMonitoringTypes() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null)
        return IGeofenceHardware.Stub.getDefaultImpl().getMonitoringTypes(); 
      parcel2.readException();
      return parcel2.createIntArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getStatusOfMonitoringType(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
        paramInt = IGeofenceHardware.Stub.getDefaultImpl().getStatusOfMonitoringType(paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean pauseGeofence(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(7, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
        bool = IGeofenceHardware.Stub.getDefaultImpl().pauseGeofence(paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean registerForMonitorStateChangeCallback(int paramInt, IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
      parcel1.writeInt(paramInt);
      if (paramIGeofenceHardwareMonitorCallback != null) {
        iBinder = paramIGeofenceHardwareMonitorCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(9, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
        bool = IGeofenceHardware.Stub.getDefaultImpl().registerForMonitorStateChangeCallback(paramInt, paramIGeofenceHardwareMonitorCallback);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean removeGeofence(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(6, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
        bool = IGeofenceHardware.Stub.getDefaultImpl().removeGeofence(paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean resumeGeofence(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(8, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
        bool = IGeofenceHardware.Stub.getDefaultImpl().resumeGeofence(paramInt1, paramInt2, paramInt3);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setFusedGeofenceHardware(IFusedGeofenceHardware paramIFusedGeofenceHardware) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
      if (paramIFusedGeofenceHardware != null) {
        iBinder = paramIFusedGeofenceHardware.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
        IGeofenceHardware.Stub.getDefaultImpl().setFusedGeofenceHardware(paramIFusedGeofenceHardware);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setGpsGeofenceHardware(IGpsGeofenceHardware paramIGpsGeofenceHardware) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
      if (paramIGpsGeofenceHardware != null) {
        iBinder = paramIGpsGeofenceHardware.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
        IGeofenceHardware.Stub.getDefaultImpl().setGpsGeofenceHardware(paramIGpsGeofenceHardware);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean unregisterForMonitorStateChangeCallback(int paramInt, IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
      parcel1.writeInt(paramInt);
      if (paramIGeofenceHardwareMonitorCallback != null) {
        iBinder = paramIGeofenceHardwareMonitorCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(10, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
        bool = IGeofenceHardware.Stub.getDefaultImpl().unregisterForMonitorStateChangeCallback(paramInt, paramIGeofenceHardwareMonitorCallback);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardware$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
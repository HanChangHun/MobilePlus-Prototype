package android.hardware.location;

import android.location.IFusedGeofenceHardware;
import android.location.IGpsGeofenceHardware;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IGeofenceHardware {
  private static final String DESCRIPTOR = "android.hardware.location.IGeofenceHardware";
  
  static final int TRANSACTION_addCircularFence = 5;
  
  static final int TRANSACTION_getMonitoringTypes = 3;
  
  static final int TRANSACTION_getStatusOfMonitoringType = 4;
  
  static final int TRANSACTION_pauseGeofence = 7;
  
  static final int TRANSACTION_registerForMonitorStateChangeCallback = 9;
  
  static final int TRANSACTION_removeGeofence = 6;
  
  static final int TRANSACTION_resumeGeofence = 8;
  
  static final int TRANSACTION_setFusedGeofenceHardware = 2;
  
  static final int TRANSACTION_setGpsGeofenceHardware = 1;
  
  static final int TRANSACTION_unregisterForMonitorStateChangeCallback = 10;
  
  public Stub() {
    attachInterface(this, "android.hardware.location.IGeofenceHardware");
  }
  
  public static IGeofenceHardware asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IGeofenceHardware");
    return (iInterface != null && iInterface instanceof IGeofenceHardware) ? (IGeofenceHardware)iInterface : new Proxy(paramIBinder);
  }
  
  public static IGeofenceHardware getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 10:
        return "unregisterForMonitorStateChangeCallback";
      case 9:
        return "registerForMonitorStateChangeCallback";
      case 8:
        return "resumeGeofence";
      case 7:
        return "pauseGeofence";
      case 6:
        return "removeGeofence";
      case 5:
        return "addCircularFence";
      case 4:
        return "getStatusOfMonitoringType";
      case 3:
        return "getMonitoringTypes";
      case 2:
        return "setFusedGeofenceHardware";
      case 1:
        break;
    } 
    return "setGpsGeofenceHardware";
  }
  
  public static boolean setDefaultImpl(IGeofenceHardware paramIGeofenceHardware) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIGeofenceHardware != null) {
        Proxy.sDefaultImpl = paramIGeofenceHardware;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1598968902) {
      boolean bool2;
      int j;
      boolean bool1;
      int i;
      int[] arrayOfInt;
      GeofenceHardwareRequestParcelable geofenceHardwareRequestParcelable;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 10:
          paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
          bool2 = unregisterForMonitorStateChangeCallback(paramParcel1.readInt(), IGeofenceHardwareMonitorCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 9:
          paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
          bool2 = registerForMonitorStateChangeCallback(paramParcel1.readInt(), IGeofenceHardwareMonitorCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 8:
          paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
          bool2 = resumeGeofence(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
          bool2 = pauseGeofence(paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 6:
          paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
          bool2 = removeGeofence(paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 5:
          paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
          j = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            geofenceHardwareRequestParcelable = (GeofenceHardwareRequestParcelable)GeofenceHardwareRequestParcelable.CREATOR.createFromParcel(paramParcel1);
          } else {
            geofenceHardwareRequestParcelable = null;
          } 
          bool1 = addCircularFence(j, geofenceHardwareRequestParcelable, IGeofenceHardwareCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
          i = getStatusOfMonitoringType(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 3:
          paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
          arrayOfInt = getMonitoringTypes();
          paramParcel2.writeNoException();
          paramParcel2.writeIntArray(arrayOfInt);
          return true;
        case 2:
          arrayOfInt.enforceInterface("android.hardware.location.IGeofenceHardware");
          setFusedGeofenceHardware(IFusedGeofenceHardware.Stub.asInterface(arrayOfInt.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      arrayOfInt.enforceInterface("android.hardware.location.IGeofenceHardware");
      setGpsGeofenceHardware(IGpsGeofenceHardware.Stub.asInterface(arrayOfInt.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.hardware.location.IGeofenceHardware");
    return true;
  }
  
  private static class Proxy implements IGeofenceHardware {
    public static IGeofenceHardware sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public boolean addCircularFence(int param2Int, GeofenceHardwareRequestParcelable param2GeofenceHardwareRequestParcelable, IGeofenceHardwareCallback param2IGeofenceHardwareCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param2Int);
        boolean bool = true;
        if (param2GeofenceHardwareRequestParcelable != null) {
          parcel1.writeInt(1);
          param2GeofenceHardwareRequestParcelable.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IGeofenceHardwareCallback != null) {
          iBinder = param2IGeofenceHardwareCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().addCircularFence(param2Int, param2GeofenceHardwareRequestParcelable, param2IGeofenceHardwareCallback);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
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
    
    public int getStatusOfMonitoringType(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          param2Int = IGeofenceHardware.Stub.getDefaultImpl().getStatusOfMonitoringType(param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean pauseGeofence(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().pauseGeofence(param2Int1, param2Int2);
          return bool;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        if (param2Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean registerForMonitorStateChangeCallback(int param2Int, IGeofenceHardwareMonitorCallback param2IGeofenceHardwareMonitorCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param2Int);
        if (param2IGeofenceHardwareMonitorCallback != null) {
          iBinder = param2IGeofenceHardwareMonitorCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(9, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().registerForMonitorStateChangeCallback(param2Int, param2IGeofenceHardwareMonitorCallback);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeGeofence(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().removeGeofence(param2Int1, param2Int2);
          return bool;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        if (param2Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean resumeGeofence(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().resumeGeofence(param2Int1, param2Int2, param2Int3);
          return bool;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        if (param2Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setFusedGeofenceHardware(IFusedGeofenceHardware param2IFusedGeofenceHardware) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        if (param2IFusedGeofenceHardware != null) {
          iBinder = param2IFusedGeofenceHardware.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          IGeofenceHardware.Stub.getDefaultImpl().setFusedGeofenceHardware(param2IFusedGeofenceHardware);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setGpsGeofenceHardware(IGpsGeofenceHardware param2IGpsGeofenceHardware) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        if (param2IGpsGeofenceHardware != null) {
          iBinder = param2IGpsGeofenceHardware.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          IGeofenceHardware.Stub.getDefaultImpl().setGpsGeofenceHardware(param2IGpsGeofenceHardware);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean unregisterForMonitorStateChangeCallback(int param2Int, IGeofenceHardwareMonitorCallback param2IGeofenceHardwareMonitorCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param2Int);
        if (param2IGeofenceHardwareMonitorCallback != null) {
          iBinder = param2IGeofenceHardwareMonitorCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(10, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().unregisterForMonitorStateChangeCallback(param2Int, param2IGeofenceHardwareMonitorCallback);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardware$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
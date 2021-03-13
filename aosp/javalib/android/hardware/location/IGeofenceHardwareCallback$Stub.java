package android.hardware.location;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IGeofenceHardwareCallback {
  private static final String DESCRIPTOR = "android.hardware.location.IGeofenceHardwareCallback";
  
  static final int TRANSACTION_onGeofenceAdd = 2;
  
  static final int TRANSACTION_onGeofencePause = 4;
  
  static final int TRANSACTION_onGeofenceRemove = 3;
  
  static final int TRANSACTION_onGeofenceResume = 5;
  
  static final int TRANSACTION_onGeofenceTransition = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.location.IGeofenceHardwareCallback");
  }
  
  public static IGeofenceHardwareCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IGeofenceHardwareCallback");
    return (iInterface != null && iInterface instanceof IGeofenceHardwareCallback) ? (IGeofenceHardwareCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IGeofenceHardwareCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "onGeofenceResume") : "onGeofencePause") : "onGeofenceRemove") : "onGeofenceAdd") : "onGeofenceTransition";
  }
  
  public static boolean setDefaultImpl(IGeofenceHardwareCallback paramIGeofenceHardwareCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIGeofenceHardwareCallback != null) {
        Proxy.sDefaultImpl = paramIGeofenceHardwareCallback;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.hardware.location.IGeofenceHardwareCallback");
              return true;
            } 
            paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
            onGeofenceResume(paramParcel1.readInt(), paramParcel1.readInt());
            return true;
          } 
          paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
          onGeofencePause(paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        } 
        paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
        onGeofenceRemove(paramParcel1.readInt(), paramParcel1.readInt());
        return true;
      } 
      paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
      onGeofenceAdd(paramParcel1.readInt(), paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
    paramInt1 = paramParcel1.readInt();
    paramInt2 = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      Location location = (Location)Location.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    onGeofenceTransition(paramInt1, paramInt2, (Location)paramParcel2, paramParcel1.readLong(), paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IGeofenceHardwareCallback {
    public static IGeofenceHardwareCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IGeofenceHardwareCallback";
    }
    
    public void onGeofenceAdd(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(2, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
          IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceAdd(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onGeofencePause(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(4, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
          IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofencePause(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onGeofenceRemove(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(3, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
          IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceRemove(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onGeofenceResume(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(5, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
          IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceResume(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onGeofenceTransition(int param2Int1, int param2Int2, Location param2Location, long param2Long, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
        try {
          parcel.writeInt(param2Int1);
          try {
            parcel.writeInt(param2Int2);
            if (param2Location != null) {
              parcel.writeInt(1);
              param2Location.writeToParcel(parcel, 0);
            } else {
              parcel.writeInt(0);
            } 
            try {
              parcel.writeLong(param2Long);
              try {
                parcel.writeInt(param2Int3);
                try {
                  if (!this.mRemote.transact(1, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
                    IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceTransition(param2Int1, param2Int2, param2Location, param2Long, param2Int3);
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
      throw param2Location;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardwareCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
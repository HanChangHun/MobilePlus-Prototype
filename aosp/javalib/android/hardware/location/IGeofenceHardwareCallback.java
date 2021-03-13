package android.hardware.location;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGeofenceHardwareCallback extends IInterface {
  void onGeofenceAdd(int paramInt1, int paramInt2) throws RemoteException;
  
  void onGeofencePause(int paramInt1, int paramInt2) throws RemoteException;
  
  void onGeofenceRemove(int paramInt1, int paramInt2) throws RemoteException;
  
  void onGeofenceResume(int paramInt1, int paramInt2) throws RemoteException;
  
  void onGeofenceTransition(int paramInt1, int paramInt2, Location paramLocation, long paramLong, int paramInt3) throws RemoteException;
  
  public static class Default implements IGeofenceHardwareCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onGeofenceAdd(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onGeofencePause(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onGeofenceRemove(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onGeofenceResume(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onGeofenceTransition(int param1Int1, int param1Int2, Location param1Location, long param1Long, int param1Int3) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IGeofenceHardwareCallback {
    private static final String DESCRIPTOR = "android.hardware.location.IGeofenceHardwareCallback";
    
    static final int TRANSACTION_onGeofenceAdd = 2;
    
    static final int TRANSACTION_onGeofencePause = 4;
    
    static final int TRANSACTION_onGeofenceRemove = 3;
    
    static final int TRANSACTION_onGeofenceResume = 5;
    
    static final int TRANSACTION_onGeofenceTransition = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IGeofenceHardwareCallback");
    }
    
    public static IGeofenceHardwareCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IGeofenceHardwareCallback");
      return (iInterface != null && iInterface instanceof IGeofenceHardwareCallback) ? (IGeofenceHardwareCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IGeofenceHardwareCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? ((param1Int != 5) ? null : "onGeofenceResume") : "onGeofencePause") : "onGeofenceRemove") : "onGeofenceAdd") : "onGeofenceTransition";
    }
    
    public static boolean setDefaultImpl(IGeofenceHardwareCallback param1IGeofenceHardwareCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IGeofenceHardwareCallback != null) {
          Proxy.sDefaultImpl = param1IGeofenceHardwareCallback;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                if (param1Int1 != 1598968902)
                  return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
                param1Parcel2.writeString("android.hardware.location.IGeofenceHardwareCallback");
                return true;
              } 
              param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
              onGeofenceResume(param1Parcel1.readInt(), param1Parcel1.readInt());
              return true;
            } 
            param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
            onGeofencePause(param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          } 
          param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
          onGeofenceRemove(param1Parcel1.readInt(), param1Parcel1.readInt());
          return true;
        } 
        param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
        onGeofenceAdd(param1Parcel1.readInt(), param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
      param1Int1 = param1Parcel1.readInt();
      param1Int2 = param1Parcel1.readInt();
      if (param1Parcel1.readInt() != 0) {
        Location location = (Location)Location.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      onGeofenceTransition(param1Int1, param1Int2, (Location)param1Parcel2, param1Parcel1.readLong(), param1Parcel1.readInt());
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
  
  private static class Proxy implements IGeofenceHardwareCallback {
    public static IGeofenceHardwareCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IGeofenceHardwareCallback";
    }
    
    public void onGeofenceAdd(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(2, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
          IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceAdd(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onGeofencePause(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(4, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
          IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofencePause(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onGeofenceRemove(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(3, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
          IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceRemove(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onGeofenceResume(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(5, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
          IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceResume(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onGeofenceTransition(int param1Int1, int param1Int2, Location param1Location, long param1Long, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
        try {
          parcel.writeInt(param1Int1);
          try {
            parcel.writeInt(param1Int2);
            if (param1Location != null) {
              parcel.writeInt(1);
              param1Location.writeToParcel(parcel, 0);
            } else {
              parcel.writeInt(0);
            } 
            try {
              parcel.writeLong(param1Long);
              try {
                parcel.writeInt(param1Int3);
                try {
                  if (!this.mRemote.transact(1, parcel, null, 1) && IGeofenceHardwareCallback.Stub.getDefaultImpl() != null) {
                    IGeofenceHardwareCallback.Stub.getDefaultImpl().onGeofenceTransition(param1Int1, param1Int2, param1Location, param1Long, param1Int3);
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
      throw param1Location;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardwareCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
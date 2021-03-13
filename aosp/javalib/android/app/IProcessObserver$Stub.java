package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IProcessObserver {
  private static final String DESCRIPTOR = "android.app.IProcessObserver";
  
  static final int TRANSACTION_onForegroundActivitiesChanged = 1;
  
  static final int TRANSACTION_onForegroundServicesChanged = 2;
  
  static final int TRANSACTION_onProcessDied = 3;
  
  public Stub() {
    attachInterface(this, "android.app.IProcessObserver");
  }
  
  public static IProcessObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IProcessObserver");
    return (iInterface != null && iInterface instanceof IProcessObserver) ? (IProcessObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IProcessObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "onProcessDied") : "onForegroundServicesChanged") : "onForegroundActivitiesChanged";
  }
  
  public static boolean setDefaultImpl(IProcessObserver paramIProcessObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIProcessObserver != null) {
        Proxy.sDefaultImpl = paramIProcessObserver;
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
    boolean bool;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.app.IProcessObserver");
          return true;
        } 
        paramParcel1.enforceInterface("android.app.IProcessObserver");
        onProcessDied(paramParcel1.readInt(), paramParcel1.readInt());
        return true;
      } 
      paramParcel1.enforceInterface("android.app.IProcessObserver");
      onForegroundServicesChanged(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IProcessObserver");
    paramInt1 = paramParcel1.readInt();
    paramInt2 = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onForegroundActivitiesChanged(paramInt1, paramInt2, bool);
    return true;
  }
  
  private static class Proxy implements IProcessObserver {
    public static IProcessObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IProcessObserver";
    }
    
    public void onForegroundActivitiesChanged(int param2Int1, int param2Int2, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IProcessObserver");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IProcessObserver.Stub.getDefaultImpl() != null) {
          IProcessObserver.Stub.getDefaultImpl().onForegroundActivitiesChanged(param2Int1, param2Int2, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onForegroundServicesChanged(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IProcessObserver");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(2, parcel, null, 1) && IProcessObserver.Stub.getDefaultImpl() != null) {
          IProcessObserver.Stub.getDefaultImpl().onForegroundServicesChanged(param2Int1, param2Int2, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onProcessDied(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IProcessObserver");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(3, parcel, null, 1) && IProcessObserver.Stub.getDefaultImpl() != null) {
          IProcessObserver.Stub.getDefaultImpl().onProcessDied(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IProcessObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
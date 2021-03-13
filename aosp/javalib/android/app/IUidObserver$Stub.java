package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IUidObserver {
  private static final String DESCRIPTOR = "android.app.IUidObserver";
  
  static final int TRANSACTION_onUidActive = 2;
  
  static final int TRANSACTION_onUidCachedChanged = 5;
  
  static final int TRANSACTION_onUidGone = 1;
  
  static final int TRANSACTION_onUidIdle = 3;
  
  static final int TRANSACTION_onUidStateChanged = 4;
  
  public Stub() {
    attachInterface(this, "android.app.IUidObserver");
  }
  
  public static IUidObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IUidObserver");
    return (iInterface != null && iInterface instanceof IUidObserver) ? (IUidObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IUidObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "onUidCachedChanged") : "onUidStateChanged") : "onUidIdle") : "onUidActive") : "onUidGone";
  }
  
  public static boolean setDefaultImpl(IUidObserver paramIUidObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIUidObserver != null) {
        Proxy.sDefaultImpl = paramIUidObserver;
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
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.app.IUidObserver");
              return true;
            } 
            paramParcel1.enforceInterface("android.app.IUidObserver");
            paramInt1 = paramParcel1.readInt();
            if (paramParcel1.readInt() != 0)
              bool3 = true; 
            onUidCachedChanged(paramInt1, bool3);
            return true;
          } 
          paramParcel1.enforceInterface("android.app.IUidObserver");
          onUidStateChanged(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readLong(), paramParcel1.readInt());
          return true;
        } 
        paramParcel1.enforceInterface("android.app.IUidObserver");
        paramInt1 = paramParcel1.readInt();
        bool3 = bool1;
        if (paramParcel1.readInt() != 0)
          bool3 = true; 
        onUidIdle(paramInt1, bool3);
        return true;
      } 
      paramParcel1.enforceInterface("android.app.IUidObserver");
      onUidActive(paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IUidObserver");
    paramInt1 = paramParcel1.readInt();
    bool3 = bool2;
    if (paramParcel1.readInt() != 0)
      bool3 = true; 
    onUidGone(paramInt1, bool3);
    return true;
  }
  
  private static class Proxy implements IUidObserver {
    public static IUidObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IUidObserver";
    }
    
    public void onUidActive(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IUidObserver");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
          IUidObserver.Stub.getDefaultImpl().onUidActive(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUidCachedChanged(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IUidObserver");
        parcel.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(5, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
          IUidObserver.Stub.getDefaultImpl().onUidCachedChanged(param2Int, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUidGone(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IUidObserver");
        parcel.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
          IUidObserver.Stub.getDefaultImpl().onUidGone(param2Int, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUidIdle(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IUidObserver");
        parcel.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(3, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
          IUidObserver.Stub.getDefaultImpl().onUidIdle(param2Int, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUidStateChanged(int param2Int1, int param2Int2, long param2Long, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IUidObserver");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(4, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
          IUidObserver.Stub.getDefaultImpl().onUidStateChanged(param2Int1, param2Int2, param2Long, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUidObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IUserSwitchObserver {
  private static final String DESCRIPTOR = "android.app.IUserSwitchObserver";
  
  static final int TRANSACTION_onForegroundProfileSwitch = 3;
  
  static final int TRANSACTION_onLockedBootComplete = 4;
  
  static final int TRANSACTION_onUserSwitchComplete = 2;
  
  static final int TRANSACTION_onUserSwitching = 1;
  
  public Stub() {
    attachInterface(this, "android.app.IUserSwitchObserver");
  }
  
  public static IUserSwitchObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IUserSwitchObserver");
    return (iInterface != null && iInterface instanceof IUserSwitchObserver) ? (IUserSwitchObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IUserSwitchObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? null : "onLockedBootComplete") : "onForegroundProfileSwitch") : "onUserSwitchComplete") : "onUserSwitching";
  }
  
  public static boolean setDefaultImpl(IUserSwitchObserver paramIUserSwitchObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIUserSwitchObserver != null) {
        Proxy.sDefaultImpl = paramIUserSwitchObserver;
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
            if (paramInt1 != 1598968902)
              return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
            paramParcel2.writeString("android.app.IUserSwitchObserver");
            return true;
          } 
          paramParcel1.enforceInterface("android.app.IUserSwitchObserver");
          onLockedBootComplete(paramParcel1.readInt());
          return true;
        } 
        paramParcel1.enforceInterface("android.app.IUserSwitchObserver");
        onForegroundProfileSwitch(paramParcel1.readInt());
        return true;
      } 
      paramParcel1.enforceInterface("android.app.IUserSwitchObserver");
      onUserSwitchComplete(paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IUserSwitchObserver");
    onUserSwitching(paramParcel1.readInt(), IRemoteCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
    return true;
  }
  
  private static class Proxy implements IUserSwitchObserver {
    public static IUserSwitchObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IUserSwitchObserver";
    }
    
    public void onForegroundProfileSwitch(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
          IUserSwitchObserver.Stub.getDefaultImpl().onForegroundProfileSwitch(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onLockedBootComplete(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(4, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
          IUserSwitchObserver.Stub.getDefaultImpl().onLockedBootComplete(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUserSwitchComplete(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
          IUserSwitchObserver.Stub.getDefaultImpl().onUserSwitchComplete(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUserSwitching(int param2Int, IRemoteCallback param2IRemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
        parcel.writeInt(param2Int);
        if (param2IRemoteCallback != null) {
          iBinder = param2IRemoteCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
          IUserSwitchObserver.Stub.getDefaultImpl().onUserSwitching(param2Int, param2IRemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUserSwitchObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
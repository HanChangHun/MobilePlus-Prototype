package android.app;

import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IUserSwitchObserver {
  public static IUserSwitchObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IUserSwitchObserver";
  }
  
  public void onForegroundProfileSwitch(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
        IUserSwitchObserver.Stub.getDefaultImpl().onForegroundProfileSwitch(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onLockedBootComplete(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
        IUserSwitchObserver.Stub.getDefaultImpl().onLockedBootComplete(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onUserSwitchComplete(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
        IUserSwitchObserver.Stub.getDefaultImpl().onUserSwitchComplete(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onUserSwitching(int paramInt, IRemoteCallback paramIRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
      parcel.writeInt(paramInt);
      if (paramIRemoteCallback != null) {
        iBinder = paramIRemoteCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
        IUserSwitchObserver.Stub.getDefaultImpl().onUserSwitching(paramInt, paramIRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUserSwitchObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
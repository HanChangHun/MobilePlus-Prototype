package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IUidObserver {
  public static IUidObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IUidObserver";
  }
  
  public void onUidActive(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IUidObserver");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
        IUidObserver.Stub.getDefaultImpl().onUidActive(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onUidCachedChanged(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.IUidObserver");
      parcel.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(5, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
        IUidObserver.Stub.getDefaultImpl().onUidCachedChanged(paramInt, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onUidGone(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.IUidObserver");
      parcel.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(1, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
        IUidObserver.Stub.getDefaultImpl().onUidGone(paramInt, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onUidIdle(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.IUidObserver");
      parcel.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(3, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
        IUidObserver.Stub.getDefaultImpl().onUidIdle(paramInt, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onUidStateChanged(int paramInt1, int paramInt2, long paramLong, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IUidObserver");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(4, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
        IUidObserver.Stub.getDefaultImpl().onUidStateChanged(paramInt1, paramInt2, paramLong, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUidObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
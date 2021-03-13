package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IProcessObserver {
  public static IProcessObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IProcessObserver";
  }
  
  public void onForegroundActivitiesChanged(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.IProcessObserver");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(1, parcel, null, 1) && IProcessObserver.Stub.getDefaultImpl() != null) {
        IProcessObserver.Stub.getDefaultImpl().onForegroundActivitiesChanged(paramInt1, paramInt2, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onForegroundServicesChanged(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IProcessObserver");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(2, parcel, null, 1) && IProcessObserver.Stub.getDefaultImpl() != null) {
        IProcessObserver.Stub.getDefaultImpl().onForegroundServicesChanged(paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onProcessDied(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IProcessObserver");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(3, parcel, null, 1) && IProcessObserver.Stub.getDefaultImpl() != null) {
        IProcessObserver.Stub.getDefaultImpl().onProcessDied(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IProcessObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
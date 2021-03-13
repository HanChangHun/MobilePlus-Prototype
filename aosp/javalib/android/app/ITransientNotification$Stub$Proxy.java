package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ITransientNotification {
  public static ITransientNotification sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.ITransientNotification";
  }
  
  public void hide() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITransientNotification");
      if (!this.mRemote.transact(2, parcel, null, 1) && ITransientNotification.Stub.getDefaultImpl() != null) {
        ITransientNotification.Stub.getDefaultImpl().hide();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void show(IBinder paramIBinder) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITransientNotification");
      parcel.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && ITransientNotification.Stub.getDefaultImpl() != null) {
        ITransientNotification.Stub.getDefaultImpl().show(paramIBinder);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ITransientNotification$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
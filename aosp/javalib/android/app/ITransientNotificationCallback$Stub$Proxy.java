package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ITransientNotificationCallback {
  public static ITransientNotificationCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.ITransientNotificationCallback";
  }
  
  public void onToastHidden() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITransientNotificationCallback");
      if (!this.mRemote.transact(2, parcel, null, 1) && ITransientNotificationCallback.Stub.getDefaultImpl() != null) {
        ITransientNotificationCallback.Stub.getDefaultImpl().onToastHidden();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onToastShown() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITransientNotificationCallback");
      if (!this.mRemote.transact(1, parcel, null, 1) && ITransientNotificationCallback.Stub.getDefaultImpl() != null) {
        ITransientNotificationCallback.Stub.getDefaultImpl().onToastShown();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ITransientNotificationCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
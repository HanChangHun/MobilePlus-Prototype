package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ISearchManagerCallback {
  public static ISearchManagerCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.ISearchManagerCallback";
  }
  
  public void onCancel() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ISearchManagerCallback");
      if (!this.mRemote.transact(2, parcel, null, 1) && ISearchManagerCallback.Stub.getDefaultImpl() != null) {
        ISearchManagerCallback.Stub.getDefaultImpl().onCancel();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onDismiss() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ISearchManagerCallback");
      if (!this.mRemote.transact(1, parcel, null, 1) && ISearchManagerCallback.Stub.getDefaultImpl() != null) {
        ISearchManagerCallback.Stub.getDefaultImpl().onDismiss();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ISearchManagerCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
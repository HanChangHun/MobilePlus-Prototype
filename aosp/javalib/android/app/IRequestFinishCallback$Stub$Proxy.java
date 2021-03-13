package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IRequestFinishCallback {
  public static IRequestFinishCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IRequestFinishCallback";
  }
  
  public void requestFinish() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IRequestFinishCallback");
      if (!this.mRemote.transact(1, parcel, null, 1) && IRequestFinishCallback.Stub.getDefaultImpl() != null) {
        IRequestFinishCallback.Stub.getDefaultImpl().requestFinish();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IRequestFinishCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
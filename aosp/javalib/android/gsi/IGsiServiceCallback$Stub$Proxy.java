package android.gsi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IGsiServiceCallback {
  public static IGsiServiceCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.gsi.IGsiServiceCallback";
  }
  
  public void onResult(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.gsi.IGsiServiceCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IGsiServiceCallback.Stub.getDefaultImpl() != null) {
        IGsiServiceCallback.Stub.getDefaultImpl().onResult(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IGsiServiceCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
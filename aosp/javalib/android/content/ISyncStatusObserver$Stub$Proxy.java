package android.content;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ISyncStatusObserver {
  public static ISyncStatusObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.ISyncStatusObserver";
  }
  
  public void onStatusChanged(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.ISyncStatusObserver");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && ISyncStatusObserver.Stub.getDefaultImpl() != null) {
        ISyncStatusObserver.Stub.getDefaultImpl().onStatusChanged(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncStatusObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
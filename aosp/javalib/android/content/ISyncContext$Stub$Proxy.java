package android.content;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ISyncContext {
  public static ISyncContext sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.ISyncContext";
  }
  
  public void onFinished(SyncResult paramSyncResult) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.ISyncContext");
      if (paramSyncResult != null) {
        parcel1.writeInt(1);
        paramSyncResult.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISyncContext.Stub.getDefaultImpl() != null) {
        ISyncContext.Stub.getDefaultImpl().onFinished(paramSyncResult);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sendHeartbeat() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.ISyncContext");
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISyncContext.Stub.getDefaultImpl() != null) {
        ISyncContext.Stub.getDefaultImpl().sendHeartbeat();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncContext$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
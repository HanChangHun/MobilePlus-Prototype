package android.app.backup;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ISelectBackupTransportCallback {
  public static ISelectBackupTransportCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.backup.ISelectBackupTransportCallback";
  }
  
  public void onFailure(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.ISelectBackupTransportCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && ISelectBackupTransportCallback.Stub.getDefaultImpl() != null) {
        ISelectBackupTransportCallback.Stub.getDefaultImpl().onFailure(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSuccess(String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.ISelectBackupTransportCallback");
      parcel.writeString(paramString);
      if (!this.mRemote.transact(1, parcel, null, 1) && ISelectBackupTransportCallback.Stub.getDefaultImpl() != null) {
        ISelectBackupTransportCallback.Stub.getDefaultImpl().onSuccess(paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/ISelectBackupTransportCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
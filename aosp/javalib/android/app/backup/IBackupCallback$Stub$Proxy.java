package android.app.backup;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBackupCallback {
  public static IBackupCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.backup.IBackupCallback";
  }
  
  public void operationComplete(long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IBackupCallback");
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(1, parcel, null, 1) && IBackupCallback.Stub.getDefaultImpl() != null) {
        IBackupCallback.Stub.getDefaultImpl().operationComplete(paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
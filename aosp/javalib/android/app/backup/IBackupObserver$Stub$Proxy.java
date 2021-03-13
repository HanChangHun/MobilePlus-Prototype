package android.app.backup;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBackupObserver {
  public static IBackupObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void backupFinished(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel, null, 1) && IBackupObserver.Stub.getDefaultImpl() != null) {
        IBackupObserver.Stub.getDefaultImpl().backupFinished(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.backup.IBackupObserver";
  }
  
  public void onResult(String paramString, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IBackupObserver.Stub.getDefaultImpl() != null) {
        IBackupObserver.Stub.getDefaultImpl().onResult(paramString, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onUpdate(String paramString, BackupProgress paramBackupProgress) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
      parcel.writeString(paramString);
      if (paramBackupProgress != null) {
        parcel.writeInt(1);
        paramBackupProgress.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IBackupObserver.Stub.getDefaultImpl() != null) {
        IBackupObserver.Stub.getDefaultImpl().onUpdate(paramString, paramBackupProgress);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
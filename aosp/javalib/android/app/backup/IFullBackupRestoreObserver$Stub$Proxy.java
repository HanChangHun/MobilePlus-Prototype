package android.app.backup;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IFullBackupRestoreObserver {
  public static IFullBackupRestoreObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.backup.IFullBackupRestoreObserver";
  }
  
  public void onBackupPackage(String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
      parcel.writeString(paramString);
      if (!this.mRemote.transact(2, parcel, null, 1) && IFullBackupRestoreObserver.Stub.getDefaultImpl() != null) {
        IFullBackupRestoreObserver.Stub.getDefaultImpl().onBackupPackage(paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onEndBackup() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
      if (!this.mRemote.transact(3, parcel, null, 1) && IFullBackupRestoreObserver.Stub.getDefaultImpl() != null) {
        IFullBackupRestoreObserver.Stub.getDefaultImpl().onEndBackup();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onEndRestore() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
      if (!this.mRemote.transact(6, parcel, null, 1) && IFullBackupRestoreObserver.Stub.getDefaultImpl() != null) {
        IFullBackupRestoreObserver.Stub.getDefaultImpl().onEndRestore();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onRestorePackage(String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
      parcel.writeString(paramString);
      if (!this.mRemote.transact(5, parcel, null, 1) && IFullBackupRestoreObserver.Stub.getDefaultImpl() != null) {
        IFullBackupRestoreObserver.Stub.getDefaultImpl().onRestorePackage(paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onStartBackup() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
      if (!this.mRemote.transact(1, parcel, null, 1) && IFullBackupRestoreObserver.Stub.getDefaultImpl() != null) {
        IFullBackupRestoreObserver.Stub.getDefaultImpl().onStartBackup();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onStartRestore() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
      if (!this.mRemote.transact(4, parcel, null, 1) && IFullBackupRestoreObserver.Stub.getDefaultImpl() != null) {
        IFullBackupRestoreObserver.Stub.getDefaultImpl().onStartRestore();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTimeout() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
      if (!this.mRemote.transact(7, parcel, null, 1) && IFullBackupRestoreObserver.Stub.getDefaultImpl() != null) {
        IFullBackupRestoreObserver.Stub.getDefaultImpl().onTimeout();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IFullBackupRestoreObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
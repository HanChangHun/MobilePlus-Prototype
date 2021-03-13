package android.app.backup;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IRestoreSession {
  public static IRestoreSession sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void endRestoreSession() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null) {
        IRestoreSession.Stub.getDefaultImpl().endRestoreSession();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getAvailableRestoreSets(IRestoreObserver paramIRestoreObserver, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
      IBinder iBinder1 = null;
      if (paramIRestoreObserver != null) {
        iBinder2 = paramIRestoreObserver.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      IBinder iBinder2 = iBinder1;
      if (paramIBackupManagerMonitor != null)
        iBinder2 = paramIBackupManagerMonitor.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
        return IRestoreSession.Stub.getDefaultImpl().getAvailableRestoreSets(paramIRestoreObserver, paramIBackupManagerMonitor); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.backup.IRestoreSession";
  }
  
  public int restoreAll(long paramLong, IRestoreObserver paramIRestoreObserver, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
      parcel1.writeLong(paramLong);
      IBinder iBinder1 = null;
      if (paramIRestoreObserver != null) {
        iBinder2 = paramIRestoreObserver.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      IBinder iBinder2 = iBinder1;
      if (paramIBackupManagerMonitor != null)
        iBinder2 = paramIBackupManagerMonitor.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
        return IRestoreSession.Stub.getDefaultImpl().restoreAll(paramLong, paramIRestoreObserver, paramIBackupManagerMonitor); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int restorePackage(String paramString, IRestoreObserver paramIRestoreObserver, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
      parcel1.writeString(paramString);
      IBinder iBinder1 = null;
      if (paramIRestoreObserver != null) {
        iBinder2 = paramIRestoreObserver.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      IBinder iBinder2 = iBinder1;
      if (paramIBackupManagerMonitor != null)
        iBinder2 = paramIBackupManagerMonitor.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
        return IRestoreSession.Stub.getDefaultImpl().restorePackage(paramString, paramIRestoreObserver, paramIBackupManagerMonitor); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int restorePackages(long paramLong, IRestoreObserver paramIRestoreObserver, String[] paramArrayOfString, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
      parcel1.writeLong(paramLong);
      IBinder iBinder1 = null;
      if (paramIRestoreObserver != null) {
        iBinder2 = paramIRestoreObserver.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      parcel1.writeStringArray(paramArrayOfString);
      IBinder iBinder2 = iBinder1;
      if (paramIBackupManagerMonitor != null)
        iBinder2 = paramIBackupManagerMonitor.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
        return IRestoreSession.Stub.getDefaultImpl().restorePackages(paramLong, paramIRestoreObserver, paramArrayOfString, paramIBackupManagerMonitor); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IRestoreSession$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
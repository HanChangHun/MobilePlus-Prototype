package android.app.backup;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

class Proxy implements IRestoreObserver {
  public static IRestoreObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.backup.IRestoreObserver";
  }
  
  public void onUpdate(int paramInt, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
      parcel.writeInt(paramInt);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(3, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
        IRestoreObserver.Stub.getDefaultImpl().onUpdate(paramInt, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void restoreFinished(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
        IRestoreObserver.Stub.getDefaultImpl().restoreFinished(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void restoreSetsAvailable(RestoreSet[] paramArrayOfRestoreSet) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
      parcel.writeTypedArray((Parcelable[])paramArrayOfRestoreSet, 0);
      if (!this.mRemote.transact(1, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
        IRestoreObserver.Stub.getDefaultImpl().restoreSetsAvailable(paramArrayOfRestoreSet);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void restoreStarting(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
        IRestoreObserver.Stub.getDefaultImpl().restoreStarting(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IRestoreObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBackupObserver {
  private static final String DESCRIPTOR = "android.app.backup.IBackupObserver";
  
  static final int TRANSACTION_backupFinished = 3;
  
  static final int TRANSACTION_onResult = 2;
  
  static final int TRANSACTION_onUpdate = 1;
  
  public Stub() {
    attachInterface(this, "android.app.backup.IBackupObserver");
  }
  
  public static IBackupObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.backup.IBackupObserver");
    return (iInterface != null && iInterface instanceof IBackupObserver) ? (IBackupObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBackupObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "backupFinished") : "onResult") : "onUpdate";
  }
  
  public static boolean setDefaultImpl(IBackupObserver paramIBackupObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBackupObserver != null) {
        Proxy.sDefaultImpl = paramIBackupObserver;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.app.backup.IBackupObserver");
          return true;
        } 
        paramParcel1.enforceInterface("android.app.backup.IBackupObserver");
        backupFinished(paramParcel1.readInt());
        return true;
      } 
      paramParcel1.enforceInterface("android.app.backup.IBackupObserver");
      onResult(paramParcel1.readString(), paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.app.backup.IBackupObserver");
    String str = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      BackupProgress backupProgress = (BackupProgress)BackupProgress.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onUpdate(str, (BackupProgress)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IBackupObserver {
    public static IBackupObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void backupFinished(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IBackupObserver.Stub.getDefaultImpl() != null) {
          IBackupObserver.Stub.getDefaultImpl().backupFinished(param2Int);
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
    
    public void onResult(String param2String, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IBackupObserver.Stub.getDefaultImpl() != null) {
          IBackupObserver.Stub.getDefaultImpl().onResult(param2String, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUpdate(String param2String, BackupProgress param2BackupProgress) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
        parcel.writeString(param2String);
        if (param2BackupProgress != null) {
          parcel.writeInt(1);
          param2BackupProgress.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IBackupObserver.Stub.getDefaultImpl() != null) {
          IBackupObserver.Stub.getDefaultImpl().onUpdate(param2String, param2BackupProgress);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
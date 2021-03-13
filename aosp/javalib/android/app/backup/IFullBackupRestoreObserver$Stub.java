package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IFullBackupRestoreObserver {
  private static final String DESCRIPTOR = "android.app.backup.IFullBackupRestoreObserver";
  
  static final int TRANSACTION_onBackupPackage = 2;
  
  static final int TRANSACTION_onEndBackup = 3;
  
  static final int TRANSACTION_onEndRestore = 6;
  
  static final int TRANSACTION_onRestorePackage = 5;
  
  static final int TRANSACTION_onStartBackup = 1;
  
  static final int TRANSACTION_onStartRestore = 4;
  
  static final int TRANSACTION_onTimeout = 7;
  
  public Stub() {
    attachInterface(this, "android.app.backup.IFullBackupRestoreObserver");
  }
  
  public static IFullBackupRestoreObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.backup.IFullBackupRestoreObserver");
    return (iInterface != null && iInterface instanceof IFullBackupRestoreObserver) ? (IFullBackupRestoreObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IFullBackupRestoreObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 7:
        return "onTimeout";
      case 6:
        return "onEndRestore";
      case 5:
        return "onRestorePackage";
      case 4:
        return "onStartRestore";
      case 3:
        return "onEndBackup";
      case 2:
        return "onBackupPackage";
      case 1:
        break;
    } 
    return "onStartBackup";
  }
  
  public static boolean setDefaultImpl(IFullBackupRestoreObserver paramIFullBackupRestoreObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIFullBackupRestoreObserver != null) {
        Proxy.sDefaultImpl = paramIFullBackupRestoreObserver;
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
    if (paramInt1 != 1598968902) {
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 7:
          paramParcel1.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
          onTimeout();
          return true;
        case 6:
          paramParcel1.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
          onEndRestore();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
          onRestorePackage(paramParcel1.readString());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
          onStartRestore();
          return true;
        case 3:
          paramParcel1.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
          onEndBackup();
          return true;
        case 2:
          paramParcel1.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
          onBackupPackage(paramParcel1.readString());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
      onStartBackup();
      return true;
    } 
    paramParcel2.writeString("android.app.backup.IFullBackupRestoreObserver");
    return true;
  }
  
  private static class Proxy implements IFullBackupRestoreObserver {
    public static IFullBackupRestoreObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.backup.IFullBackupRestoreObserver";
    }
    
    public void onBackupPackage(String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
        parcel.writeString(param2String);
        if (!this.mRemote.transact(2, parcel, null, 1) && IFullBackupRestoreObserver.Stub.getDefaultImpl() != null) {
          IFullBackupRestoreObserver.Stub.getDefaultImpl().onBackupPackage(param2String);
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
    
    public void onRestorePackage(String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
        parcel.writeString(param2String);
        if (!this.mRemote.transact(5, parcel, null, 1) && IFullBackupRestoreObserver.Stub.getDefaultImpl() != null) {
          IFullBackupRestoreObserver.Stub.getDefaultImpl().onRestorePackage(param2String);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IFullBackupRestoreObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
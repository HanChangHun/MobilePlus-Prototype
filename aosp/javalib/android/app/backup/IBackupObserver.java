package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBackupObserver extends IInterface {
  void backupFinished(int paramInt) throws RemoteException;
  
  void onResult(String paramString, int paramInt) throws RemoteException;
  
  void onUpdate(String paramString, BackupProgress paramBackupProgress) throws RemoteException;
  
  public static class Default implements IBackupObserver {
    public IBinder asBinder() {
      return null;
    }
    
    public void backupFinished(int param1Int) throws RemoteException {}
    
    public void onResult(String param1String, int param1Int) throws RemoteException {}
    
    public void onUpdate(String param1String, BackupProgress param1BackupProgress) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBackupObserver {
    private static final String DESCRIPTOR = "android.app.backup.IBackupObserver";
    
    static final int TRANSACTION_backupFinished = 3;
    
    static final int TRANSACTION_onResult = 2;
    
    static final int TRANSACTION_onUpdate = 1;
    
    public Stub() {
      attachInterface(this, "android.app.backup.IBackupObserver");
    }
    
    public static IBackupObserver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.backup.IBackupObserver");
      return (iInterface != null && iInterface instanceof IBackupObserver) ? (IBackupObserver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBackupObserver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? null : "backupFinished") : "onResult") : "onUpdate";
    }
    
    public static boolean setDefaultImpl(IBackupObserver param1IBackupObserver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBackupObserver != null) {
          Proxy.sDefaultImpl = param1IBackupObserver;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 1598968902)
              return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
            param1Parcel2.writeString("android.app.backup.IBackupObserver");
            return true;
          } 
          param1Parcel1.enforceInterface("android.app.backup.IBackupObserver");
          backupFinished(param1Parcel1.readInt());
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.backup.IBackupObserver");
        onResult(param1Parcel1.readString(), param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.backup.IBackupObserver");
      String str = param1Parcel1.readString();
      if (param1Parcel1.readInt() != 0) {
        BackupProgress backupProgress = (BackupProgress)BackupProgress.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onUpdate(str, (BackupProgress)param1Parcel1);
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
  
  private static class Proxy implements IBackupObserver {
    public static IBackupObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void backupFinished(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IBackupObserver.Stub.getDefaultImpl() != null) {
          IBackupObserver.Stub.getDefaultImpl().backupFinished(param1Int);
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
    
    public void onResult(String param1String, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IBackupObserver.Stub.getDefaultImpl() != null) {
          IBackupObserver.Stub.getDefaultImpl().onResult(param1String, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUpdate(String param1String, BackupProgress param1BackupProgress) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
        parcel.writeString(param1String);
        if (param1BackupProgress != null) {
          parcel.writeInt(1);
          param1BackupProgress.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IBackupObserver.Stub.getDefaultImpl() != null) {
          IBackupObserver.Stub.getDefaultImpl().onUpdate(param1String, param1BackupProgress);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
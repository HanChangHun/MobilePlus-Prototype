package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IRestoreSession {
  private static final String DESCRIPTOR = "android.app.backup.IRestoreSession";
  
  static final int TRANSACTION_endRestoreSession = 5;
  
  static final int TRANSACTION_getAvailableRestoreSets = 1;
  
  static final int TRANSACTION_restoreAll = 2;
  
  static final int TRANSACTION_restorePackage = 4;
  
  static final int TRANSACTION_restorePackages = 3;
  
  public Stub() {
    attachInterface(this, "android.app.backup.IRestoreSession");
  }
  
  public static IRestoreSession asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.backup.IRestoreSession");
    return (iInterface != null && iInterface instanceof IRestoreSession) ? (IRestoreSession)iInterface : new Proxy(paramIBinder);
  }
  
  public static IRestoreSession getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "endRestoreSession") : "restorePackage") : "restorePackages") : "restoreAll") : "getAvailableRestoreSets";
  }
  
  public static boolean setDefaultImpl(IRestoreSession paramIRestoreSession) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIRestoreSession != null) {
        Proxy.sDefaultImpl = paramIRestoreSession;
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
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.app.backup.IRestoreSession");
              return true;
            } 
            paramParcel1.enforceInterface("android.app.backup.IRestoreSession");
            endRestoreSession();
            paramParcel2.writeNoException();
            return true;
          } 
          paramParcel1.enforceInterface("android.app.backup.IRestoreSession");
          paramInt1 = restorePackage(paramParcel1.readString(), IRestoreObserver.Stub.asInterface(paramParcel1.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        } 
        paramParcel1.enforceInterface("android.app.backup.IRestoreSession");
        paramInt1 = restorePackages(paramParcel1.readLong(), IRestoreObserver.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.createStringArray(), IBackupManagerMonitor.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      } 
      paramParcel1.enforceInterface("android.app.backup.IRestoreSession");
      paramInt1 = restoreAll(paramParcel1.readLong(), IRestoreObserver.Stub.asInterface(paramParcel1.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      paramParcel2.writeInt(paramInt1);
      return true;
    } 
    paramParcel1.enforceInterface("android.app.backup.IRestoreSession");
    paramInt1 = getAvailableRestoreSets(IRestoreObserver.Stub.asInterface(paramParcel1.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(paramParcel1.readStrongBinder()));
    paramParcel2.writeNoException();
    paramParcel2.writeInt(paramInt1);
    return true;
  }
  
  private static class Proxy implements IRestoreSession {
    public static IRestoreSession sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
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
    
    public int getAvailableRestoreSets(IRestoreObserver param2IRestoreObserver, IBackupManagerMonitor param2IBackupManagerMonitor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
        IBinder iBinder1 = null;
        if (param2IRestoreObserver != null) {
          iBinder2 = param2IRestoreObserver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param2IBackupManagerMonitor != null)
          iBinder2 = param2IBackupManagerMonitor.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
          return IRestoreSession.Stub.getDefaultImpl().getAvailableRestoreSets(param2IRestoreObserver, param2IBackupManagerMonitor); 
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
    
    public int restoreAll(long param2Long, IRestoreObserver param2IRestoreObserver, IBackupManagerMonitor param2IBackupManagerMonitor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
        parcel1.writeLong(param2Long);
        IBinder iBinder1 = null;
        if (param2IRestoreObserver != null) {
          iBinder2 = param2IRestoreObserver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param2IBackupManagerMonitor != null)
          iBinder2 = param2IBackupManagerMonitor.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
          return IRestoreSession.Stub.getDefaultImpl().restoreAll(param2Long, param2IRestoreObserver, param2IBackupManagerMonitor); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int restorePackage(String param2String, IRestoreObserver param2IRestoreObserver, IBackupManagerMonitor param2IBackupManagerMonitor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
        parcel1.writeString(param2String);
        IBinder iBinder1 = null;
        if (param2IRestoreObserver != null) {
          iBinder2 = param2IRestoreObserver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param2IBackupManagerMonitor != null)
          iBinder2 = param2IBackupManagerMonitor.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
          return IRestoreSession.Stub.getDefaultImpl().restorePackage(param2String, param2IRestoreObserver, param2IBackupManagerMonitor); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int restorePackages(long param2Long, IRestoreObserver param2IRestoreObserver, String[] param2ArrayOfString, IBackupManagerMonitor param2IBackupManagerMonitor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
        parcel1.writeLong(param2Long);
        IBinder iBinder1 = null;
        if (param2IRestoreObserver != null) {
          iBinder2 = param2IRestoreObserver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        parcel1.writeStringArray(param2ArrayOfString);
        IBinder iBinder2 = iBinder1;
        if (param2IBackupManagerMonitor != null)
          iBinder2 = param2IBackupManagerMonitor.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
          return IRestoreSession.Stub.getDefaultImpl().restorePackages(param2Long, param2IRestoreObserver, param2ArrayOfString, param2IBackupManagerMonitor); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IRestoreSession$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRestoreSession extends IInterface {
  void endRestoreSession() throws RemoteException;
  
  int getAvailableRestoreSets(IRestoreObserver paramIRestoreObserver, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException;
  
  int restoreAll(long paramLong, IRestoreObserver paramIRestoreObserver, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException;
  
  int restorePackage(String paramString, IRestoreObserver paramIRestoreObserver, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException;
  
  int restorePackages(long paramLong, IRestoreObserver paramIRestoreObserver, String[] paramArrayOfString, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException;
  
  public static class Default implements IRestoreSession {
    public IBinder asBinder() {
      return null;
    }
    
    public void endRestoreSession() throws RemoteException {}
    
    public int getAvailableRestoreSets(IRestoreObserver param1IRestoreObserver, IBackupManagerMonitor param1IBackupManagerMonitor) throws RemoteException {
      return 0;
    }
    
    public int restoreAll(long param1Long, IRestoreObserver param1IRestoreObserver, IBackupManagerMonitor param1IBackupManagerMonitor) throws RemoteException {
      return 0;
    }
    
    public int restorePackage(String param1String, IRestoreObserver param1IRestoreObserver, IBackupManagerMonitor param1IBackupManagerMonitor) throws RemoteException {
      return 0;
    }
    
    public int restorePackages(long param1Long, IRestoreObserver param1IRestoreObserver, String[] param1ArrayOfString, IBackupManagerMonitor param1IBackupManagerMonitor) throws RemoteException {
      return 0;
    }
  }
  
  public static abstract class Stub extends Binder implements IRestoreSession {
    private static final String DESCRIPTOR = "android.app.backup.IRestoreSession";
    
    static final int TRANSACTION_endRestoreSession = 5;
    
    static final int TRANSACTION_getAvailableRestoreSets = 1;
    
    static final int TRANSACTION_restoreAll = 2;
    
    static final int TRANSACTION_restorePackage = 4;
    
    static final int TRANSACTION_restorePackages = 3;
    
    public Stub() {
      attachInterface(this, "android.app.backup.IRestoreSession");
    }
    
    public static IRestoreSession asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.backup.IRestoreSession");
      return (iInterface != null && iInterface instanceof IRestoreSession) ? (IRestoreSession)iInterface : new Proxy(param1IBinder);
    }
    
    public static IRestoreSession getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? ((param1Int != 5) ? null : "endRestoreSession") : "restorePackage") : "restorePackages") : "restoreAll") : "getAvailableRestoreSets";
    }
    
    public static boolean setDefaultImpl(IRestoreSession param1IRestoreSession) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IRestoreSession != null) {
          Proxy.sDefaultImpl = param1IRestoreSession;
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
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                if (param1Int1 != 1598968902)
                  return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
                param1Parcel2.writeString("android.app.backup.IRestoreSession");
                return true;
              } 
              param1Parcel1.enforceInterface("android.app.backup.IRestoreSession");
              endRestoreSession();
              param1Parcel2.writeNoException();
              return true;
            } 
            param1Parcel1.enforceInterface("android.app.backup.IRestoreSession");
            param1Int1 = restorePackage(param1Parcel1.readString(), IRestoreObserver.Stub.asInterface(param1Parcel1.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          } 
          param1Parcel1.enforceInterface("android.app.backup.IRestoreSession");
          param1Int1 = restorePackages(param1Parcel1.readLong(), IRestoreObserver.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.createStringArray(), IBackupManagerMonitor.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          param1Parcel2.writeInt(param1Int1);
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.backup.IRestoreSession");
        param1Int1 = restoreAll(param1Parcel1.readLong(), IRestoreObserver.Stub.asInterface(param1Parcel1.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(param1Parcel1.readStrongBinder()));
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(param1Int1);
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.backup.IRestoreSession");
      param1Int1 = getAvailableRestoreSets(IRestoreObserver.Stub.asInterface(param1Parcel1.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(param1Parcel1.readStrongBinder()));
      param1Parcel2.writeNoException();
      param1Parcel2.writeInt(param1Int1);
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
  
  private static class Proxy implements IRestoreSession {
    public static IRestoreSession sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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
    
    public int getAvailableRestoreSets(IRestoreObserver param1IRestoreObserver, IBackupManagerMonitor param1IBackupManagerMonitor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
        IBinder iBinder1 = null;
        if (param1IRestoreObserver != null) {
          iBinder2 = param1IRestoreObserver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param1IBackupManagerMonitor != null)
          iBinder2 = param1IBackupManagerMonitor.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
          return IRestoreSession.Stub.getDefaultImpl().getAvailableRestoreSets(param1IRestoreObserver, param1IBackupManagerMonitor); 
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
    
    public int restoreAll(long param1Long, IRestoreObserver param1IRestoreObserver, IBackupManagerMonitor param1IBackupManagerMonitor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
        parcel1.writeLong(param1Long);
        IBinder iBinder1 = null;
        if (param1IRestoreObserver != null) {
          iBinder2 = param1IRestoreObserver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param1IBackupManagerMonitor != null)
          iBinder2 = param1IBackupManagerMonitor.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
          return IRestoreSession.Stub.getDefaultImpl().restoreAll(param1Long, param1IRestoreObserver, param1IBackupManagerMonitor); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int restorePackage(String param1String, IRestoreObserver param1IRestoreObserver, IBackupManagerMonitor param1IBackupManagerMonitor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
        parcel1.writeString(param1String);
        IBinder iBinder1 = null;
        if (param1IRestoreObserver != null) {
          iBinder2 = param1IRestoreObserver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param1IBackupManagerMonitor != null)
          iBinder2 = param1IBackupManagerMonitor.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
          return IRestoreSession.Stub.getDefaultImpl().restorePackage(param1String, param1IRestoreObserver, param1IBackupManagerMonitor); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int restorePackages(long param1Long, IRestoreObserver param1IRestoreObserver, String[] param1ArrayOfString, IBackupManagerMonitor param1IBackupManagerMonitor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IRestoreSession");
        parcel1.writeLong(param1Long);
        IBinder iBinder1 = null;
        if (param1IRestoreObserver != null) {
          iBinder2 = param1IRestoreObserver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        parcel1.writeStringArray(param1ArrayOfString);
        IBinder iBinder2 = iBinder1;
        if (param1IBackupManagerMonitor != null)
          iBinder2 = param1IBackupManagerMonitor.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRestoreSession.Stub.getDefaultImpl() != null)
          return IRestoreSession.Stub.getDefaultImpl().restorePackages(param1Long, param1IRestoreObserver, param1ArrayOfString, param1IBackupManagerMonitor); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IRestoreSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
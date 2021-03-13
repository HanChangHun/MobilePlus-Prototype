package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public interface IRestoreObserver extends IInterface {
  void onUpdate(int paramInt, String paramString) throws RemoteException;
  
  void restoreFinished(int paramInt) throws RemoteException;
  
  void restoreSetsAvailable(RestoreSet[] paramArrayOfRestoreSet) throws RemoteException;
  
  void restoreStarting(int paramInt) throws RemoteException;
  
  public static class Default implements IRestoreObserver {
    public IBinder asBinder() {
      return null;
    }
    
    public void onUpdate(int param1Int, String param1String) throws RemoteException {}
    
    public void restoreFinished(int param1Int) throws RemoteException {}
    
    public void restoreSetsAvailable(RestoreSet[] param1ArrayOfRestoreSet) throws RemoteException {}
    
    public void restoreStarting(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IRestoreObserver {
    private static final String DESCRIPTOR = "android.app.backup.IRestoreObserver";
    
    static final int TRANSACTION_onUpdate = 3;
    
    static final int TRANSACTION_restoreFinished = 4;
    
    static final int TRANSACTION_restoreSetsAvailable = 1;
    
    static final int TRANSACTION_restoreStarting = 2;
    
    public Stub() {
      attachInterface(this, "android.app.backup.IRestoreObserver");
    }
    
    public static IRestoreObserver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.backup.IRestoreObserver");
      return (iInterface != null && iInterface instanceof IRestoreObserver) ? (IRestoreObserver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IRestoreObserver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? null : "restoreFinished") : "onUpdate") : "restoreStarting") : "restoreSetsAvailable";
    }
    
    public static boolean setDefaultImpl(IRestoreObserver param1IRestoreObserver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IRestoreObserver != null) {
          Proxy.sDefaultImpl = param1IRestoreObserver;
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
              if (param1Int1 != 1598968902)
                return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
              param1Parcel2.writeString("android.app.backup.IRestoreObserver");
              return true;
            } 
            param1Parcel1.enforceInterface("android.app.backup.IRestoreObserver");
            restoreFinished(param1Parcel1.readInt());
            return true;
          } 
          param1Parcel1.enforceInterface("android.app.backup.IRestoreObserver");
          onUpdate(param1Parcel1.readInt(), param1Parcel1.readString());
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.backup.IRestoreObserver");
        restoreStarting(param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.backup.IRestoreObserver");
      restoreSetsAvailable((RestoreSet[])param1Parcel1.createTypedArray(RestoreSet.CREATOR));
      return true;
    }
    
    private static class Proxy implements IRestoreObserver {
      public static IRestoreObserver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.backup.IRestoreObserver";
      }
      
      public void onUpdate(int param2Int, String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
          parcel.writeInt(param2Int);
          parcel.writeString(param2String);
          if (!this.mRemote.transact(3, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
            IRestoreObserver.Stub.getDefaultImpl().onUpdate(param2Int, param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void restoreFinished(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(4, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
            IRestoreObserver.Stub.getDefaultImpl().restoreFinished(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void restoreSetsAvailable(RestoreSet[] param2ArrayOfRestoreSet) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
          parcel.writeTypedArray((Parcelable[])param2ArrayOfRestoreSet, 0);
          if (!this.mRemote.transact(1, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
            IRestoreObserver.Stub.getDefaultImpl().restoreSetsAvailable(param2ArrayOfRestoreSet);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void restoreStarting(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
            IRestoreObserver.Stub.getDefaultImpl().restoreStarting(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IRestoreObserver {
    public static IRestoreObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.backup.IRestoreObserver";
    }
    
    public void onUpdate(int param1Int, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
        parcel.writeInt(param1Int);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(3, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
          IRestoreObserver.Stub.getDefaultImpl().onUpdate(param1Int, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void restoreFinished(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
          IRestoreObserver.Stub.getDefaultImpl().restoreFinished(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void restoreSetsAvailable(RestoreSet[] param1ArrayOfRestoreSet) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
        parcel.writeTypedArray((Parcelable[])param1ArrayOfRestoreSet, 0);
        if (!this.mRemote.transact(1, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
          IRestoreObserver.Stub.getDefaultImpl().restoreSetsAvailable(param1ArrayOfRestoreSet);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void restoreStarting(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
          IRestoreObserver.Stub.getDefaultImpl().restoreStarting(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IRestoreObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
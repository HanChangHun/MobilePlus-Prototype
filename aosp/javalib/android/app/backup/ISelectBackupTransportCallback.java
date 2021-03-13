package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISelectBackupTransportCallback extends IInterface {
  void onFailure(int paramInt) throws RemoteException;
  
  void onSuccess(String paramString) throws RemoteException;
  
  public static class Default implements ISelectBackupTransportCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onFailure(int param1Int) throws RemoteException {}
    
    public void onSuccess(String param1String) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ISelectBackupTransportCallback {
    private static final String DESCRIPTOR = "android.app.backup.ISelectBackupTransportCallback";
    
    static final int TRANSACTION_onFailure = 2;
    
    static final int TRANSACTION_onSuccess = 1;
    
    public Stub() {
      attachInterface(this, "android.app.backup.ISelectBackupTransportCallback");
    }
    
    public static ISelectBackupTransportCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.backup.ISelectBackupTransportCallback");
      return (iInterface != null && iInterface instanceof ISelectBackupTransportCallback) ? (ISelectBackupTransportCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISelectBackupTransportCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onFailure") : "onSuccess";
    }
    
    public static boolean setDefaultImpl(ISelectBackupTransportCallback param1ISelectBackupTransportCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISelectBackupTransportCallback != null) {
          Proxy.sDefaultImpl = param1ISelectBackupTransportCallback;
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
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.app.backup.ISelectBackupTransportCallback");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.backup.ISelectBackupTransportCallback");
        onFailure(param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.backup.ISelectBackupTransportCallback");
      onSuccess(param1Parcel1.readString());
      return true;
    }
    
    private static class Proxy implements ISelectBackupTransportCallback {
      public static ISelectBackupTransportCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.backup.ISelectBackupTransportCallback";
      }
      
      public void onFailure(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.backup.ISelectBackupTransportCallback");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel, null, 1) && ISelectBackupTransportCallback.Stub.getDefaultImpl() != null) {
            ISelectBackupTransportCallback.Stub.getDefaultImpl().onFailure(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onSuccess(String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.backup.ISelectBackupTransportCallback");
          parcel.writeString(param2String);
          if (!this.mRemote.transact(1, parcel, null, 1) && ISelectBackupTransportCallback.Stub.getDefaultImpl() != null) {
            ISelectBackupTransportCallback.Stub.getDefaultImpl().onSuccess(param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ISelectBackupTransportCallback {
    public static ISelectBackupTransportCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.backup.ISelectBackupTransportCallback";
    }
    
    public void onFailure(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.ISelectBackupTransportCallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && ISelectBackupTransportCallback.Stub.getDefaultImpl() != null) {
          ISelectBackupTransportCallback.Stub.getDefaultImpl().onFailure(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSuccess(String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.ISelectBackupTransportCallback");
        parcel.writeString(param1String);
        if (!this.mRemote.transact(1, parcel, null, 1) && ISelectBackupTransportCallback.Stub.getDefaultImpl() != null) {
          ISelectBackupTransportCallback.Stub.getDefaultImpl().onSuccess(param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/ISelectBackupTransportCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
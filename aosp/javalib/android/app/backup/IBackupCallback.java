package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBackupCallback extends IInterface {
  void operationComplete(long paramLong) throws RemoteException;
  
  public static class Default implements IBackupCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void operationComplete(long param1Long) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBackupCallback {
    private static final String DESCRIPTOR = "android.app.backup.IBackupCallback";
    
    static final int TRANSACTION_operationComplete = 1;
    
    public Stub() {
      attachInterface(this, "android.app.backup.IBackupCallback");
    }
    
    public static IBackupCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.backup.IBackupCallback");
      return (iInterface != null && iInterface instanceof IBackupCallback) ? (IBackupCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBackupCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "operationComplete";
    }
    
    public static boolean setDefaultImpl(IBackupCallback param1IBackupCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBackupCallback != null) {
          Proxy.sDefaultImpl = param1IBackupCallback;
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
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.app.backup.IBackupCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.backup.IBackupCallback");
      operationComplete(param1Parcel1.readLong());
      return true;
    }
    
    private static class Proxy implements IBackupCallback {
      public static IBackupCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.backup.IBackupCallback";
      }
      
      public void operationComplete(long param2Long) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.backup.IBackupCallback");
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(1, parcel, null, 1) && IBackupCallback.Stub.getDefaultImpl() != null) {
            IBackupCallback.Stub.getDefaultImpl().operationComplete(param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IBackupCallback {
    public static IBackupCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.backup.IBackupCallback";
    }
    
    public void operationComplete(long param1Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IBackupCallback");
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBackupCallback.Stub.getDefaultImpl() != null) {
          IBackupCallback.Stub.getDefaultImpl().operationComplete(param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
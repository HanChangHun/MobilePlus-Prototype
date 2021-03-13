package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBackupCallback {
  private static final String DESCRIPTOR = "android.app.backup.IBackupCallback";
  
  static final int TRANSACTION_operationComplete = 1;
  
  public Stub() {
    attachInterface(this, "android.app.backup.IBackupCallback");
  }
  
  public static IBackupCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.backup.IBackupCallback");
    return (iInterface != null && iInterface instanceof IBackupCallback) ? (IBackupCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBackupCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "operationComplete";
  }
  
  public static boolean setDefaultImpl(IBackupCallback paramIBackupCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBackupCallback != null) {
        Proxy.sDefaultImpl = paramIBackupCallback;
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
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.app.backup.IBackupCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.backup.IBackupCallback");
    operationComplete(paramParcel1.readLong());
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


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISelectBackupTransportCallback {
  private static final String DESCRIPTOR = "android.app.backup.ISelectBackupTransportCallback";
  
  static final int TRANSACTION_onFailure = 2;
  
  static final int TRANSACTION_onSuccess = 1;
  
  public Stub() {
    attachInterface(this, "android.app.backup.ISelectBackupTransportCallback");
  }
  
  public static ISelectBackupTransportCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.backup.ISelectBackupTransportCallback");
    return (iInterface != null && iInterface instanceof ISelectBackupTransportCallback) ? (ISelectBackupTransportCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISelectBackupTransportCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onFailure") : "onSuccess";
  }
  
  public static boolean setDefaultImpl(ISelectBackupTransportCallback paramISelectBackupTransportCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISelectBackupTransportCallback != null) {
        Proxy.sDefaultImpl = paramISelectBackupTransportCallback;
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
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.app.backup.ISelectBackupTransportCallback");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.backup.ISelectBackupTransportCallback");
      onFailure(paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.app.backup.ISelectBackupTransportCallback");
    onSuccess(paramParcel1.readString());
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


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/ISelectBackupTransportCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
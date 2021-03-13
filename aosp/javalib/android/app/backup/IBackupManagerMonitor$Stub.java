package android.app.backup;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBackupManagerMonitor {
  private static final String DESCRIPTOR = "android.app.backup.IBackupManagerMonitor";
  
  static final int TRANSACTION_onEvent = 1;
  
  public Stub() {
    attachInterface(this, "android.app.backup.IBackupManagerMonitor");
  }
  
  public static IBackupManagerMonitor asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.backup.IBackupManagerMonitor");
    return (iInterface != null && iInterface instanceof IBackupManagerMonitor) ? (IBackupManagerMonitor)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBackupManagerMonitor getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onEvent";
  }
  
  public static boolean setDefaultImpl(IBackupManagerMonitor paramIBackupManagerMonitor) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBackupManagerMonitor != null) {
        Proxy.sDefaultImpl = paramIBackupManagerMonitor;
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
      paramParcel2.writeString("android.app.backup.IBackupManagerMonitor");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.backup.IBackupManagerMonitor");
    if (paramParcel1.readInt() != 0) {
      Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onEvent((Bundle)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IBackupManagerMonitor {
    public static IBackupManagerMonitor sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.backup.IBackupManagerMonitor";
    }
    
    public void onEvent(Bundle param2Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IBackupManagerMonitor");
        if (param2Bundle != null) {
          parcel.writeInt(1);
          param2Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IBackupManagerMonitor.Stub.getDefaultImpl() != null) {
          IBackupManagerMonitor.Stub.getDefaultImpl().onEvent(param2Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupManagerMonitor$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
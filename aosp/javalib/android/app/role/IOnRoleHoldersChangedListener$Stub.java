package android.app.role;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IOnRoleHoldersChangedListener {
  private static final String DESCRIPTOR = "android.app.role.IOnRoleHoldersChangedListener";
  
  static final int TRANSACTION_onRoleHoldersChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.app.role.IOnRoleHoldersChangedListener");
  }
  
  public static IOnRoleHoldersChangedListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.role.IOnRoleHoldersChangedListener");
    return (iInterface != null && iInterface instanceof IOnRoleHoldersChangedListener) ? (IOnRoleHoldersChangedListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IOnRoleHoldersChangedListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onRoleHoldersChanged";
  }
  
  public static boolean setDefaultImpl(IOnRoleHoldersChangedListener paramIOnRoleHoldersChangedListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIOnRoleHoldersChangedListener != null) {
        Proxy.sDefaultImpl = paramIOnRoleHoldersChangedListener;
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
      paramParcel2.writeString("android.app.role.IOnRoleHoldersChangedListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.role.IOnRoleHoldersChangedListener");
    onRoleHoldersChanged(paramParcel1.readString(), paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IOnRoleHoldersChangedListener {
    public static IOnRoleHoldersChangedListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.role.IOnRoleHoldersChangedListener";
    }
    
    public void onRoleHoldersChanged(String param2String, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IOnRoleHoldersChangedListener");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IOnRoleHoldersChangedListener.Stub.getDefaultImpl() != null) {
          IOnRoleHoldersChangedListener.Stub.getDefaultImpl().onRoleHoldersChanged(param2String, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/IOnRoleHoldersChangedListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
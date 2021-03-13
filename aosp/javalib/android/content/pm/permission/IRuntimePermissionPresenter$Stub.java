package android.content.pm.permission;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IRuntimePermissionPresenter {
  private static final String DESCRIPTOR = "android.content.pm.permission.IRuntimePermissionPresenter";
  
  static final int TRANSACTION_getAppPermissions = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.permission.IRuntimePermissionPresenter");
  }
  
  public static IRuntimePermissionPresenter asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.permission.IRuntimePermissionPresenter");
    return (iInterface != null && iInterface instanceof IRuntimePermissionPresenter) ? (IRuntimePermissionPresenter)iInterface : new Proxy(paramIBinder);
  }
  
  public static IRuntimePermissionPresenter getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "getAppPermissions";
  }
  
  public static boolean setDefaultImpl(IRuntimePermissionPresenter paramIRuntimePermissionPresenter) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIRuntimePermissionPresenter != null) {
        Proxy.sDefaultImpl = paramIRuntimePermissionPresenter;
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
      paramParcel2.writeString("android.content.pm.permission.IRuntimePermissionPresenter");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.permission.IRuntimePermissionPresenter");
    String str = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    getAppPermissions(str, (RemoteCallback)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IRuntimePermissionPresenter {
    public static IRuntimePermissionPresenter sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void getAppPermissions(String param2String, RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.permission.IRuntimePermissionPresenter");
        parcel.writeString(param2String);
        if (param2RemoteCallback != null) {
          parcel.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IRuntimePermissionPresenter.Stub.getDefaultImpl() != null) {
          IRuntimePermissionPresenter.Stub.getDefaultImpl().getAppPermissions(param2String, param2RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.permission.IRuntimePermissionPresenter";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/permission/IRuntimePermissionPresenter$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm.permission;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;

public interface IRuntimePermissionPresenter extends IInterface {
  void getAppPermissions(String paramString, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  public static class Default implements IRuntimePermissionPresenter {
    public IBinder asBinder() {
      return null;
    }
    
    public void getAppPermissions(String param1String, RemoteCallback param1RemoteCallback) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IRuntimePermissionPresenter {
    private static final String DESCRIPTOR = "android.content.pm.permission.IRuntimePermissionPresenter";
    
    static final int TRANSACTION_getAppPermissions = 1;
    
    public Stub() {
      attachInterface(this, "android.content.pm.permission.IRuntimePermissionPresenter");
    }
    
    public static IRuntimePermissionPresenter asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.permission.IRuntimePermissionPresenter");
      return (iInterface != null && iInterface instanceof IRuntimePermissionPresenter) ? (IRuntimePermissionPresenter)iInterface : new Proxy(param1IBinder);
    }
    
    public static IRuntimePermissionPresenter getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "getAppPermissions";
    }
    
    public static boolean setDefaultImpl(IRuntimePermissionPresenter param1IRuntimePermissionPresenter) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IRuntimePermissionPresenter != null) {
          Proxy.sDefaultImpl = param1IRuntimePermissionPresenter;
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
        param1Parcel2.writeString("android.content.pm.permission.IRuntimePermissionPresenter");
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.permission.IRuntimePermissionPresenter");
      String str = param1Parcel1.readString();
      if (param1Parcel1.readInt() != 0) {
        RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      getAppPermissions(str, (RemoteCallback)param1Parcel1);
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
  
  private static class Proxy implements IRuntimePermissionPresenter {
    public static IRuntimePermissionPresenter sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void getAppPermissions(String param1String, RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.permission.IRuntimePermissionPresenter");
        parcel.writeString(param1String);
        if (param1RemoteCallback != null) {
          parcel.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IRuntimePermissionPresenter.Stub.getDefaultImpl() != null) {
          IRuntimePermissionPresenter.Stub.getDefaultImpl().getAppPermissions(param1String, param1RemoteCallback);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/permission/IRuntimePermissionPresenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
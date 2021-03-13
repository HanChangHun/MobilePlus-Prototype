package android.app.role;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IRoleController {
  private static final String DESCRIPTOR = "android.app.role.IRoleController";
  
  static final int TRANSACTION_grantDefaultRoles = 1;
  
  static final int TRANSACTION_isApplicationQualifiedForRole = 5;
  
  static final int TRANSACTION_isApplicationVisibleForRole = 6;
  
  static final int TRANSACTION_isRoleVisible = 7;
  
  static final int TRANSACTION_onAddRoleHolder = 2;
  
  static final int TRANSACTION_onClearRoleHolders = 4;
  
  static final int TRANSACTION_onRemoveRoleHolder = 3;
  
  public Stub() {
    attachInterface(this, "android.app.role.IRoleController");
  }
  
  public static IRoleController asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.role.IRoleController");
    return (iInterface != null && iInterface instanceof IRoleController) ? (IRoleController)iInterface : new Proxy(paramIBinder);
  }
  
  public static IRoleController getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 7:
        return "isRoleVisible";
      case 6:
        return "isApplicationVisibleForRole";
      case 5:
        return "isApplicationQualifiedForRole";
      case 4:
        return "onClearRoleHolders";
      case 3:
        return "onRemoveRoleHolder";
      case 2:
        return "onAddRoleHolder";
      case 1:
        break;
    } 
    return "grantDefaultRoles";
  }
  
  public static boolean setDefaultImpl(IRoleController paramIRoleController) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIRoleController != null) {
        Proxy.sDefaultImpl = paramIRoleController;
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
    String str;
    if (paramInt1 != 1598968902) {
      String str1;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 7:
          paramParcel1.enforceInterface("android.app.role.IRoleController");
          str = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          isRoleVisible(str, (RemoteCallback)paramParcel1);
          return true;
        case 6:
          paramParcel1.enforceInterface("android.app.role.IRoleController");
          str = paramParcel1.readString();
          str1 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          isApplicationVisibleForRole(str, str1, (RemoteCallback)paramParcel1);
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.role.IRoleController");
          str1 = paramParcel1.readString();
          str = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          isApplicationQualifiedForRole(str1, str, (RemoteCallback)paramParcel1);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.app.role.IRoleController");
          str = paramParcel1.readString();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onClearRoleHolders(str, paramInt1, (RemoteCallback)paramParcel1);
          return true;
        case 3:
          paramParcel1.enforceInterface("android.app.role.IRoleController");
          str = paramParcel1.readString();
          str1 = paramParcel1.readString();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onRemoveRoleHolder(str, str1, paramInt1, (RemoteCallback)paramParcel1);
          return true;
        case 2:
          paramParcel1.enforceInterface("android.app.role.IRoleController");
          str1 = paramParcel1.readString();
          str = paramParcel1.readString();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onAddRoleHolder(str1, str, paramInt1, (RemoteCallback)paramParcel1);
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.app.role.IRoleController");
      if (paramParcel1.readInt() != 0) {
        RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      grantDefaultRoles((RemoteCallback)paramParcel1);
      return true;
    } 
    str.writeString("android.app.role.IRoleController");
    return true;
  }
  
  private static class Proxy implements IRoleController {
    public static IRoleController sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.role.IRoleController";
    }
    
    public void grantDefaultRoles(RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        if (param2RemoteCallback != null) {
          parcel.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().grantDefaultRoles(param2RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void isApplicationQualifiedForRole(String param2String1, String param2String2, RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param2String1);
        parcel.writeString(param2String2);
        if (param2RemoteCallback != null) {
          parcel.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().isApplicationQualifiedForRole(param2String1, param2String2, param2RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void isApplicationVisibleForRole(String param2String1, String param2String2, RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param2String1);
        parcel.writeString(param2String2);
        if (param2RemoteCallback != null) {
          parcel.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().isApplicationVisibleForRole(param2String1, param2String2, param2RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void isRoleVisible(String param2String, RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param2String);
        if (param2RemoteCallback != null) {
          parcel.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().isRoleVisible(param2String, param2RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAddRoleHolder(String param2String1, String param2String2, int param2Int, RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param2String1);
        parcel.writeString(param2String2);
        parcel.writeInt(param2Int);
        if (param2RemoteCallback != null) {
          parcel.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().onAddRoleHolder(param2String1, param2String2, param2Int, param2RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onClearRoleHolders(String param2String, int param2Int, RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int);
        if (param2RemoteCallback != null) {
          parcel.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().onClearRoleHolders(param2String, param2Int, param2RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRemoveRoleHolder(String param2String1, String param2String2, int param2Int, RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param2String1);
        parcel.writeString(param2String2);
        parcel.writeInt(param2Int);
        if (param2RemoteCallback != null) {
          parcel.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().onRemoveRoleHolder(param2String1, param2String2, param2Int, param2RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/IRoleController$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
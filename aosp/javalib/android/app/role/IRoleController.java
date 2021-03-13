package android.app.role;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;

public interface IRoleController extends IInterface {
  void grantDefaultRoles(RemoteCallback paramRemoteCallback) throws RemoteException;
  
  void isApplicationQualifiedForRole(String paramString1, String paramString2, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  void isApplicationVisibleForRole(String paramString1, String paramString2, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  void isRoleVisible(String paramString, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  void onAddRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  void onClearRoleHolders(String paramString, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  void onRemoveRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  public static class Default implements IRoleController {
    public IBinder asBinder() {
      return null;
    }
    
    public void grantDefaultRoles(RemoteCallback param1RemoteCallback) throws RemoteException {}
    
    public void isApplicationQualifiedForRole(String param1String1, String param1String2, RemoteCallback param1RemoteCallback) throws RemoteException {}
    
    public void isApplicationVisibleForRole(String param1String1, String param1String2, RemoteCallback param1RemoteCallback) throws RemoteException {}
    
    public void isRoleVisible(String param1String, RemoteCallback param1RemoteCallback) throws RemoteException {}
    
    public void onAddRoleHolder(String param1String1, String param1String2, int param1Int, RemoteCallback param1RemoteCallback) throws RemoteException {}
    
    public void onClearRoleHolders(String param1String, int param1Int, RemoteCallback param1RemoteCallback) throws RemoteException {}
    
    public void onRemoveRoleHolder(String param1String1, String param1String2, int param1Int, RemoteCallback param1RemoteCallback) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IRoleController {
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
    
    public static IRoleController asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.role.IRoleController");
      return (iInterface != null && iInterface instanceof IRoleController) ? (IRoleController)iInterface : new Proxy(param1IBinder);
    }
    
    public static IRoleController getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IRoleController param1IRoleController) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IRoleController != null) {
          Proxy.sDefaultImpl = param1IRoleController;
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
      String str;
      if (param1Int1 != 1598968902) {
        String str1;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 7:
            param1Parcel1.enforceInterface("android.app.role.IRoleController");
            str = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            isRoleVisible(str, (RemoteCallback)param1Parcel1);
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.app.role.IRoleController");
            str = param1Parcel1.readString();
            str1 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            isApplicationVisibleForRole(str, str1, (RemoteCallback)param1Parcel1);
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.app.role.IRoleController");
            str1 = param1Parcel1.readString();
            str = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            isApplicationQualifiedForRole(str1, str, (RemoteCallback)param1Parcel1);
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.app.role.IRoleController");
            str = param1Parcel1.readString();
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onClearRoleHolders(str, param1Int1, (RemoteCallback)param1Parcel1);
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.app.role.IRoleController");
            str = param1Parcel1.readString();
            str1 = param1Parcel1.readString();
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onRemoveRoleHolder(str, str1, param1Int1, (RemoteCallback)param1Parcel1);
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.app.role.IRoleController");
            str1 = param1Parcel1.readString();
            str = param1Parcel1.readString();
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onAddRoleHolder(str1, str, param1Int1, (RemoteCallback)param1Parcel1);
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.app.role.IRoleController");
        if (param1Parcel1.readInt() != 0) {
          RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        grantDefaultRoles((RemoteCallback)param1Parcel1);
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
  
  private static class Proxy implements IRoleController {
    public static IRoleController sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.role.IRoleController";
    }
    
    public void grantDefaultRoles(RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        if (param1RemoteCallback != null) {
          parcel.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().grantDefaultRoles(param1RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void isApplicationQualifiedForRole(String param1String1, String param1String2, RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param1String1);
        parcel.writeString(param1String2);
        if (param1RemoteCallback != null) {
          parcel.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().isApplicationQualifiedForRole(param1String1, param1String2, param1RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void isApplicationVisibleForRole(String param1String1, String param1String2, RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param1String1);
        parcel.writeString(param1String2);
        if (param1RemoteCallback != null) {
          parcel.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().isApplicationVisibleForRole(param1String1, param1String2, param1RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void isRoleVisible(String param1String, RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param1String);
        if (param1RemoteCallback != null) {
          parcel.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().isRoleVisible(param1String, param1RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAddRoleHolder(String param1String1, String param1String2, int param1Int, RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param1String1);
        parcel.writeString(param1String2);
        parcel.writeInt(param1Int);
        if (param1RemoteCallback != null) {
          parcel.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().onAddRoleHolder(param1String1, param1String2, param1Int, param1RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onClearRoleHolders(String param1String, int param1Int, RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int);
        if (param1RemoteCallback != null) {
          parcel.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().onClearRoleHolders(param1String, param1Int, param1RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRemoveRoleHolder(String param1String1, String param1String2, int param1Int, RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IRoleController");
        parcel.writeString(param1String1);
        parcel.writeString(param1String2);
        parcel.writeInt(param1Int);
        if (param1RemoteCallback != null) {
          parcel.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
          IRoleController.Stub.getDefaultImpl().onRemoveRoleHolder(param1String1, param1String2, param1Int, param1RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/IRoleController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
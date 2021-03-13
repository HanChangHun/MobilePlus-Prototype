package android.app.role;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IRoleManager {
  private static final String DESCRIPTOR = "android.app.role.IRoleManager";
  
  static final int TRANSACTION_addOnRoleHoldersChangedListenerAsUser = 7;
  
  static final int TRANSACTION_addRoleHolderAsUser = 4;
  
  static final int TRANSACTION_addRoleHolderFromController = 10;
  
  static final int TRANSACTION_clearRoleHoldersAsUser = 6;
  
  static final int TRANSACTION_getDefaultSmsPackage = 13;
  
  static final int TRANSACTION_getHeldRolesFromController = 12;
  
  static final int TRANSACTION_getRoleHoldersAsUser = 3;
  
  static final int TRANSACTION_isRoleAvailable = 1;
  
  static final int TRANSACTION_isRoleHeld = 2;
  
  static final int TRANSACTION_removeOnRoleHoldersChangedListenerAsUser = 8;
  
  static final int TRANSACTION_removeRoleHolderAsUser = 5;
  
  static final int TRANSACTION_removeRoleHolderFromController = 11;
  
  static final int TRANSACTION_setRoleNamesFromController = 9;
  
  public Stub() {
    attachInterface(this, "android.app.role.IRoleManager");
  }
  
  public static IRoleManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.role.IRoleManager");
    return (iInterface != null && iInterface instanceof IRoleManager) ? (IRoleManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IRoleManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 13:
        return "getDefaultSmsPackage";
      case 12:
        return "getHeldRolesFromController";
      case 11:
        return "removeRoleHolderFromController";
      case 10:
        return "addRoleHolderFromController";
      case 9:
        return "setRoleNamesFromController";
      case 8:
        return "removeOnRoleHoldersChangedListenerAsUser";
      case 7:
        return "addOnRoleHoldersChangedListenerAsUser";
      case 6:
        return "clearRoleHoldersAsUser";
      case 5:
        return "removeRoleHolderAsUser";
      case 4:
        return "addRoleHolderAsUser";
      case 3:
        return "getRoleHoldersAsUser";
      case 2:
        return "isRoleHeld";
      case 1:
        break;
    } 
    return "isRoleAvailable";
  }
  
  public static boolean setDefaultImpl(IRoleManager paramIRoleManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIRoleManager != null) {
        Proxy.sDefaultImpl = paramIRoleManager;
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
    if (paramInt1 != 1598968902) {
      boolean bool2;
      int i;
      String str1;
      List<String> list;
      String str2;
      String str3;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 13:
          paramParcel1.enforceInterface("android.app.role.IRoleManager");
          str1 = getDefaultSmsPackage(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 12:
          str1.enforceInterface("android.app.role.IRoleManager");
          list = getHeldRolesFromController(str1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list);
          return true;
        case 11:
          list.enforceInterface("android.app.role.IRoleManager");
          bool2 = removeRoleHolderFromController(list.readString(), list.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 10:
          list.enforceInterface("android.app.role.IRoleManager");
          bool2 = addRoleHolderFromController(list.readString(), list.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 9:
          list.enforceInterface("android.app.role.IRoleManager");
          setRoleNamesFromController(list.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 8:
          list.enforceInterface("android.app.role.IRoleManager");
          removeOnRoleHoldersChangedListenerAsUser(IOnRoleHoldersChangedListener.Stub.asInterface(list.readStrongBinder()), list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 7:
          list.enforceInterface("android.app.role.IRoleManager");
          addOnRoleHoldersChangedListenerAsUser(IOnRoleHoldersChangedListener.Stub.asInterface(list.readStrongBinder()), list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 6:
          list.enforceInterface("android.app.role.IRoleManager");
          str2 = list.readString();
          i = list.readInt();
          paramInt2 = list.readInt();
          if (list.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          clearRoleHoldersAsUser(str2, i, paramInt2, (RemoteCallback)list);
          paramParcel2.writeNoException();
          return true;
        case 5:
          list.enforceInterface("android.app.role.IRoleManager");
          str3 = list.readString();
          str2 = list.readString();
          paramInt2 = list.readInt();
          i = list.readInt();
          if (list.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          removeRoleHolderAsUser(str3, str2, paramInt2, i, (RemoteCallback)list);
          paramParcel2.writeNoException();
          return true;
        case 4:
          list.enforceInterface("android.app.role.IRoleManager");
          str3 = list.readString();
          str2 = list.readString();
          i = list.readInt();
          paramInt2 = list.readInt();
          if (list.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          addRoleHolderAsUser(str3, str2, i, paramInt2, (RemoteCallback)list);
          paramParcel2.writeNoException();
          return true;
        case 3:
          list.enforceInterface("android.app.role.IRoleManager");
          list = getRoleHoldersAsUser(list.readString(), list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list);
          return true;
        case 2:
          list.enforceInterface("android.app.role.IRoleManager");
          bool1 = isRoleHeld(list.readString(), list.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 1:
          break;
      } 
      list.enforceInterface("android.app.role.IRoleManager");
      boolean bool1 = isRoleAvailable(list.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeInt(bool1);
      return true;
    } 
    paramParcel2.writeString("android.app.role.IRoleManager");
    return true;
  }
  
  private static class Proxy implements IRoleManager {
    public static IRoleManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void addOnRoleHoldersChangedListenerAsUser(IOnRoleHoldersChangedListener param2IOnRoleHoldersChangedListener, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        if (param2IOnRoleHoldersChangedListener != null) {
          iBinder = param2IOnRoleHoldersChangedListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
          IRoleManager.Stub.getDefaultImpl().addOnRoleHoldersChangedListenerAsUser(param2IOnRoleHoldersChangedListener, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addRoleHolderAsUser(String param2String1, String param2String2, int param2Int1, int param2Int2, RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (param2RemoteCallback != null) {
          parcel1.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
          IRoleManager.Stub.getDefaultImpl().addRoleHolderAsUser(param2String1, param2String2, param2Int1, param2Int2, param2RemoteCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean addRoleHolderFromController(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(10, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
          bool = IRoleManager.Stub.getDefaultImpl().addRoleHolderFromController(param2String1, param2String2);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void clearRoleHoldersAsUser(String param2String, int param2Int1, int param2Int2, RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (param2RemoteCallback != null) {
          parcel1.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
          IRoleManager.Stub.getDefaultImpl().clearRoleHoldersAsUser(param2String, param2Int1, param2Int2, param2RemoteCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getDefaultSmsPackage(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null)
          return IRoleManager.Stub.getDefaultImpl().getDefaultSmsPackage(param2Int); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getHeldRolesFromController(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null)
          return IRoleManager.Stub.getDefaultImpl().getHeldRolesFromController(param2String); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.role.IRoleManager";
    }
    
    public List<String> getRoleHoldersAsUser(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null)
          return IRoleManager.Stub.getDefaultImpl().getRoleHoldersAsUser(param2String, param2Int); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isRoleAvailable(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(1, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
          bool = IRoleManager.Stub.getDefaultImpl().isRoleAvailable(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isRoleHeld(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
          bool = IRoleManager.Stub.getDefaultImpl().isRoleHeld(param2String1, param2String2);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeOnRoleHoldersChangedListenerAsUser(IOnRoleHoldersChangedListener param2IOnRoleHoldersChangedListener, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        if (param2IOnRoleHoldersChangedListener != null) {
          iBinder = param2IOnRoleHoldersChangedListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
          IRoleManager.Stub.getDefaultImpl().removeOnRoleHoldersChangedListenerAsUser(param2IOnRoleHoldersChangedListener, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeRoleHolderAsUser(String param2String1, String param2String2, int param2Int1, int param2Int2, RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (param2RemoteCallback != null) {
          parcel1.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
          IRoleManager.Stub.getDefaultImpl().removeRoleHolderAsUser(param2String1, param2String2, param2Int1, param2Int2, param2RemoteCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeRoleHolderFromController(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(11, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
          bool = IRoleManager.Stub.getDefaultImpl().removeRoleHolderFromController(param2String1, param2String2);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setRoleNamesFromController(List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.role.IRoleManager");
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
          IRoleManager.Stub.getDefaultImpl().setRoleNamesFromController(param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/IRoleManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
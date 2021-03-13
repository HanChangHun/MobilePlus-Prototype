package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IUiModeManager {
  private static final String DESCRIPTOR = "android.app.IUiModeManager";
  
  static final int TRANSACTION_disableCarMode = 2;
  
  static final int TRANSACTION_disableCarModeByCallingPackage = 3;
  
  static final int TRANSACTION_enableCarMode = 1;
  
  static final int TRANSACTION_getCurrentModeType = 4;
  
  static final int TRANSACTION_getCustomNightModeEnd = 12;
  
  static final int TRANSACTION_getCustomNightModeStart = 10;
  
  static final int TRANSACTION_getNightMode = 6;
  
  static final int TRANSACTION_isNightModeLocked = 8;
  
  static final int TRANSACTION_isUiModeLocked = 7;
  
  static final int TRANSACTION_setCustomNightModeEnd = 13;
  
  static final int TRANSACTION_setCustomNightModeStart = 11;
  
  static final int TRANSACTION_setNightMode = 5;
  
  static final int TRANSACTION_setNightModeActivated = 9;
  
  public Stub() {
    attachInterface(this, "android.app.IUiModeManager");
  }
  
  public static IUiModeManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IUiModeManager");
    return (iInterface != null && iInterface instanceof IUiModeManager) ? (IUiModeManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IUiModeManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 13:
        return "setCustomNightModeEnd";
      case 12:
        return "getCustomNightModeEnd";
      case 11:
        return "setCustomNightModeStart";
      case 10:
        return "getCustomNightModeStart";
      case 9:
        return "setNightModeActivated";
      case 8:
        return "isNightModeLocked";
      case 7:
        return "isUiModeLocked";
      case 6:
        return "getNightMode";
      case 5:
        return "setNightMode";
      case 4:
        return "getCurrentModeType";
      case 3:
        return "disableCarModeByCallingPackage";
      case 2:
        return "disableCarMode";
      case 1:
        break;
    } 
    return "enableCarMode";
  }
  
  public static boolean setDefaultImpl(IUiModeManager paramIUiModeManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIUiModeManager != null) {
        Proxy.sDefaultImpl = paramIUiModeManager;
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
      boolean bool;
      int i;
      long l;
      boolean bool1;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 13:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          setCustomNightModeEnd(paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        case 12:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          l = getCustomNightModeEnd();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 11:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          setCustomNightModeStart(paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        case 10:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          l = getCustomNightModeStart();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 9:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          bool = setNightModeActivated(bool1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 8:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          bool = isNightModeLocked();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          bool = isUiModeLocked();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 6:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          i = getNightMode();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          setNightMode(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 4:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          i = getCurrentModeType();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 3:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          disableCarModeByCallingPackage(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 2:
          paramParcel1.enforceInterface("android.app.IUiModeManager");
          disableCarMode(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.app.IUiModeManager");
      enableCarMode(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.app.IUiModeManager");
    return true;
  }
  
  private static class Proxy implements IUiModeManager {
    public static IUiModeManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void disableCarMode(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
          IUiModeManager.Stub.getDefaultImpl().disableCarMode(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void disableCarModeByCallingPackage(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
          IUiModeManager.Stub.getDefaultImpl().disableCarModeByCallingPackage(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enableCarMode(int param2Int1, int param2Int2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
          IUiModeManager.Stub.getDefaultImpl().enableCarMode(param2Int1, param2Int2, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getCurrentModeType() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null)
          return IUiModeManager.Stub.getDefaultImpl().getCurrentModeType(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getCustomNightModeEnd() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null)
          return IUiModeManager.Stub.getDefaultImpl().getCustomNightModeEnd(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getCustomNightModeStart() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null)
          return IUiModeManager.Stub.getDefaultImpl().getCustomNightModeStart(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IUiModeManager";
    }
    
    public int getNightMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null)
          return IUiModeManager.Stub.getDefaultImpl().getNightMode(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isNightModeLocked() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
          bool = IUiModeManager.Stub.getDefaultImpl().isNightModeLocked();
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
    
    public boolean isUiModeLocked() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
          bool = IUiModeManager.Stub.getDefaultImpl().isUiModeLocked();
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
    
    public void setCustomNightModeEnd(long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
          IUiModeManager.Stub.getDefaultImpl().setCustomNightModeEnd(param2Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCustomNightModeStart(long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
          IUiModeManager.Stub.getDefaultImpl().setCustomNightModeStart(param2Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNightMode(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
          IUiModeManager.Stub.getDefaultImpl().setNightMode(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setNightModeActivated(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiModeManager");
        boolean bool = true;
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IUiModeManager.Stub.getDefaultImpl().setNightModeActivated(param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUiModeManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
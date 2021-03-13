package android.content.om;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

public abstract class Stub extends Binder implements IOverlayManager {
  private static final String DESCRIPTOR = "android.content.om.IOverlayManager";
  
  static final int TRANSACTION_getAllOverlays = 1;
  
  static final int TRANSACTION_getDefaultOverlayPackages = 10;
  
  static final int TRANSACTION_getOverlayInfo = 3;
  
  static final int TRANSACTION_getOverlayInfosForTarget = 2;
  
  static final int TRANSACTION_invalidateCachesForOverlay = 11;
  
  static final int TRANSACTION_setEnabled = 4;
  
  static final int TRANSACTION_setEnabledExclusive = 5;
  
  static final int TRANSACTION_setEnabledExclusiveInCategory = 6;
  
  static final int TRANSACTION_setHighestPriority = 8;
  
  static final int TRANSACTION_setLowestPriority = 9;
  
  static final int TRANSACTION_setPriority = 7;
  
  public Stub() {
    attachInterface(this, "android.content.om.IOverlayManager");
  }
  
  public static IOverlayManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.om.IOverlayManager");
    return (iInterface != null && iInterface instanceof IOverlayManager) ? (IOverlayManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IOverlayManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 11:
        return "invalidateCachesForOverlay";
      case 10:
        return "getDefaultOverlayPackages";
      case 9:
        return "setLowestPriority";
      case 8:
        return "setHighestPriority";
      case 7:
        return "setPriority";
      case 6:
        return "setEnabledExclusiveInCategory";
      case 5:
        return "setEnabledExclusive";
      case 4:
        return "setEnabled";
      case 3:
        return "getOverlayInfo";
      case 2:
        return "getOverlayInfosForTarget";
      case 1:
        break;
    } 
    return "getAllOverlays";
  }
  
  public static boolean setDefaultImpl(IOverlayManager paramIOverlayManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIOverlayManager != null) {
        Proxy.sDefaultImpl = paramIOverlayManager;
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
      String[] arrayOfString;
      OverlayInfo overlayInfo;
      List list;
      String str;
      boolean bool1 = false;
      boolean bool2 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 11:
          paramParcel1.enforceInterface("android.content.om.IOverlayManager");
          invalidateCachesForOverlay(paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 10:
          paramParcel1.enforceInterface("android.content.om.IOverlayManager");
          arrayOfString = getDefaultOverlayPackages();
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString);
          return true;
        case 9:
          arrayOfString.enforceInterface("android.content.om.IOverlayManager");
          bool = setLowestPriority(arrayOfString.readString(), arrayOfString.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 8:
          arrayOfString.enforceInterface("android.content.om.IOverlayManager");
          bool = setHighestPriority(arrayOfString.readString(), arrayOfString.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 7:
          arrayOfString.enforceInterface("android.content.om.IOverlayManager");
          bool = setPriority(arrayOfString.readString(), arrayOfString.readString(), arrayOfString.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 6:
          arrayOfString.enforceInterface("android.content.om.IOverlayManager");
          bool = setEnabledExclusiveInCategory(arrayOfString.readString(), arrayOfString.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 5:
          arrayOfString.enforceInterface("android.content.om.IOverlayManager");
          str = arrayOfString.readString();
          if (arrayOfString.readInt() != 0)
            bool2 = true; 
          bool = setEnabledExclusive(str, bool2, arrayOfString.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 4:
          arrayOfString.enforceInterface("android.content.om.IOverlayManager");
          str = arrayOfString.readString();
          bool2 = bool1;
          if (arrayOfString.readInt() != 0)
            bool2 = true; 
          bool = setEnabled(str, bool2, arrayOfString.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 3:
          arrayOfString.enforceInterface("android.content.om.IOverlayManager");
          overlayInfo = getOverlayInfo(arrayOfString.readString(), arrayOfString.readInt());
          paramParcel2.writeNoException();
          if (overlayInfo != null) {
            paramParcel2.writeInt(1);
            overlayInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 2:
          overlayInfo.enforceInterface("android.content.om.IOverlayManager");
          list = getOverlayInfosForTarget(overlayInfo.readString(), overlayInfo.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeList(list);
          return true;
        case 1:
          break;
      } 
      list.enforceInterface("android.content.om.IOverlayManager");
      Map map = getAllOverlays(list.readInt());
      paramParcel2.writeNoException();
      paramParcel2.writeMap(map);
      return true;
    } 
    paramParcel2.writeString("android.content.om.IOverlayManager");
    return true;
  }
  
  private static class Proxy implements IOverlayManager {
    public static IOverlayManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public Map getAllOverlays(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null)
          return IOverlayManager.Stub.getDefaultImpl().getAllOverlays(param2Int); 
        parcel2.readException();
        return parcel2.readHashMap(getClass().getClassLoader());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] getDefaultOverlayPackages() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null)
          return IOverlayManager.Stub.getDefaultImpl().getDefaultOverlayPackages(); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.om.IOverlayManager";
    }
    
    public OverlayInfo getOverlayInfo(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null)
          return IOverlayManager.Stub.getDefaultImpl().getOverlayInfo(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          OverlayInfo overlayInfo = (OverlayInfo)OverlayInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (OverlayInfo)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List getOverlayInfosForTarget(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null)
          return IOverlayManager.Stub.getDefaultImpl().getOverlayInfosForTarget(param2String, param2Int); 
        parcel2.readException();
        return parcel2.readArrayList(getClass().getClassLoader());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void invalidateCachesForOverlay(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          IOverlayManager.Stub.getDefaultImpl().invalidateCachesForOverlay(param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setEnabled(String param2String, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param2String);
        boolean bool1 = true;
        if (param2Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IOverlayManager.Stub.getDefaultImpl().setEnabled(param2String, param2Boolean, param2Int);
          return param2Boolean;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0) {
          param2Boolean = bool1;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setEnabledExclusive(String param2String, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param2String);
        boolean bool1 = true;
        if (param2Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IOverlayManager.Stub.getDefaultImpl().setEnabledExclusive(param2String, param2Boolean, param2Int);
          return param2Boolean;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0) {
          param2Boolean = bool1;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setEnabledExclusiveInCategory(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          bool = IOverlayManager.Stub.getDefaultImpl().setEnabledExclusiveInCategory(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setHighestPriority(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          bool = IOverlayManager.Stub.getDefaultImpl().setHighestPriority(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setLowestPriority(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(9, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          bool = IOverlayManager.Stub.getDefaultImpl().setLowestPriority(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPriority(String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          bool = IOverlayManager.Stub.getDefaultImpl().setPriority(param2String1, param2String2, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/om/IOverlayManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
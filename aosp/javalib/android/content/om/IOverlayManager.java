package android.content.om;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

public interface IOverlayManager extends IInterface {
  Map getAllOverlays(int paramInt) throws RemoteException;
  
  String[] getDefaultOverlayPackages() throws RemoteException;
  
  OverlayInfo getOverlayInfo(String paramString, int paramInt) throws RemoteException;
  
  List getOverlayInfosForTarget(String paramString, int paramInt) throws RemoteException;
  
  void invalidateCachesForOverlay(String paramString, int paramInt) throws RemoteException;
  
  boolean setEnabled(String paramString, boolean paramBoolean, int paramInt) throws RemoteException;
  
  boolean setEnabledExclusive(String paramString, boolean paramBoolean, int paramInt) throws RemoteException;
  
  boolean setEnabledExclusiveInCategory(String paramString, int paramInt) throws RemoteException;
  
  boolean setHighestPriority(String paramString, int paramInt) throws RemoteException;
  
  boolean setLowestPriority(String paramString, int paramInt) throws RemoteException;
  
  boolean setPriority(String paramString1, String paramString2, int paramInt) throws RemoteException;
  
  public static class Default implements IOverlayManager {
    public IBinder asBinder() {
      return null;
    }
    
    public Map getAllOverlays(int param1Int) throws RemoteException {
      return null;
    }
    
    public String[] getDefaultOverlayPackages() throws RemoteException {
      return null;
    }
    
    public OverlayInfo getOverlayInfo(String param1String, int param1Int) throws RemoteException {
      return null;
    }
    
    public List getOverlayInfosForTarget(String param1String, int param1Int) throws RemoteException {
      return null;
    }
    
    public void invalidateCachesForOverlay(String param1String, int param1Int) throws RemoteException {}
    
    public boolean setEnabled(String param1String, boolean param1Boolean, int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean setEnabledExclusive(String param1String, boolean param1Boolean, int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean setEnabledExclusiveInCategory(String param1String, int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean setHighestPriority(String param1String, int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean setLowestPriority(String param1String, int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean setPriority(String param1String1, String param1String2, int param1Int) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IOverlayManager {
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
    
    public static IOverlayManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.om.IOverlayManager");
      return (iInterface != null && iInterface instanceof IOverlayManager) ? (IOverlayManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IOverlayManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IOverlayManager param1IOverlayManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IOverlayManager != null) {
          Proxy.sDefaultImpl = param1IOverlayManager;
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
      if (param1Int1 != 1598968902) {
        boolean bool;
        String[] arrayOfString;
        OverlayInfo overlayInfo;
        List list;
        String str;
        boolean bool1 = false;
        boolean bool2 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 11:
            param1Parcel1.enforceInterface("android.content.om.IOverlayManager");
            invalidateCachesForOverlay(param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.content.om.IOverlayManager");
            arrayOfString = getDefaultOverlayPackages();
            param1Parcel2.writeNoException();
            param1Parcel2.writeStringArray(arrayOfString);
            return true;
          case 9:
            arrayOfString.enforceInterface("android.content.om.IOverlayManager");
            bool = setLowestPriority(arrayOfString.readString(), arrayOfString.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 8:
            arrayOfString.enforceInterface("android.content.om.IOverlayManager");
            bool = setHighestPriority(arrayOfString.readString(), arrayOfString.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 7:
            arrayOfString.enforceInterface("android.content.om.IOverlayManager");
            bool = setPriority(arrayOfString.readString(), arrayOfString.readString(), arrayOfString.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 6:
            arrayOfString.enforceInterface("android.content.om.IOverlayManager");
            bool = setEnabledExclusiveInCategory(arrayOfString.readString(), arrayOfString.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 5:
            arrayOfString.enforceInterface("android.content.om.IOverlayManager");
            str = arrayOfString.readString();
            if (arrayOfString.readInt() != 0)
              bool2 = true; 
            bool = setEnabledExclusive(str, bool2, arrayOfString.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 4:
            arrayOfString.enforceInterface("android.content.om.IOverlayManager");
            str = arrayOfString.readString();
            bool2 = bool1;
            if (arrayOfString.readInt() != 0)
              bool2 = true; 
            bool = setEnabled(str, bool2, arrayOfString.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 3:
            arrayOfString.enforceInterface("android.content.om.IOverlayManager");
            overlayInfo = getOverlayInfo(arrayOfString.readString(), arrayOfString.readInt());
            param1Parcel2.writeNoException();
            if (overlayInfo != null) {
              param1Parcel2.writeInt(1);
              overlayInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 2:
            overlayInfo.enforceInterface("android.content.om.IOverlayManager");
            list = getOverlayInfosForTarget(overlayInfo.readString(), overlayInfo.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeList(list);
            return true;
          case 1:
            break;
        } 
        list.enforceInterface("android.content.om.IOverlayManager");
        Map map = getAllOverlays(list.readInt());
        param1Parcel2.writeNoException();
        param1Parcel2.writeMap(map);
        return true;
      } 
      param1Parcel2.writeString("android.content.om.IOverlayManager");
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
  
  private static class Proxy implements IOverlayManager {
    public static IOverlayManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public Map getAllOverlays(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null)
          return IOverlayManager.Stub.getDefaultImpl().getAllOverlays(param1Int); 
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
    
    public OverlayInfo getOverlayInfo(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null)
          return IOverlayManager.Stub.getDefaultImpl().getOverlayInfo(param1String, param1Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          OverlayInfo overlayInfo = (OverlayInfo)OverlayInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (OverlayInfo)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List getOverlayInfosForTarget(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null)
          return IOverlayManager.Stub.getDefaultImpl().getOverlayInfosForTarget(param1String, param1Int); 
        parcel2.readException();
        return parcel2.readArrayList(getClass().getClassLoader());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void invalidateCachesForOverlay(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          IOverlayManager.Stub.getDefaultImpl().invalidateCachesForOverlay(param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setEnabled(String param1String, boolean param1Boolean, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param1String);
        boolean bool1 = true;
        if (param1Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          param1Boolean = IOverlayManager.Stub.getDefaultImpl().setEnabled(param1String, param1Boolean, param1Int);
          return param1Boolean;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0) {
          param1Boolean = bool1;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setEnabledExclusive(String param1String, boolean param1Boolean, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param1String);
        boolean bool1 = true;
        if (param1Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          param1Boolean = IOverlayManager.Stub.getDefaultImpl().setEnabledExclusive(param1String, param1Boolean, param1Int);
          return param1Boolean;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0) {
          param1Boolean = bool1;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setEnabledExclusiveInCategory(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          bool = IOverlayManager.Stub.getDefaultImpl().setEnabledExclusiveInCategory(param1String, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setHighestPriority(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          bool = IOverlayManager.Stub.getDefaultImpl().setHighestPriority(param1String, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setLowestPriority(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(9, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          bool = IOverlayManager.Stub.getDefaultImpl().setLowestPriority(param1String, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPriority(String param1String1, String param1String2, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
          bool = IOverlayManager.Stub.getDefaultImpl().setPriority(param1String1, param1String2, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/om/IOverlayManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
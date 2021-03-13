package android.app;

import android.content.ComponentName;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IWallpaperManager extends IInterface {
  void clearWallpaper(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  int getHeightHint(int paramInt) throws RemoteException;
  
  String getName() throws RemoteException;
  
  ParcelFileDescriptor getWallpaper(String paramString, IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, Bundle paramBundle, int paramInt2) throws RemoteException;
  
  WallpaperColors getWallpaperColors(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  int getWallpaperIdForUser(int paramInt1, int paramInt2) throws RemoteException;
  
  WallpaperInfo getWallpaperInfo(int paramInt) throws RemoteException;
  
  ParcelFileDescriptor getWallpaperWithFeature(String paramString1, String paramString2, IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, Bundle paramBundle, int paramInt2) throws RemoteException;
  
  int getWidthHint(int paramInt) throws RemoteException;
  
  boolean hasNamedWallpaper(String paramString) throws RemoteException;
  
  boolean isSetWallpaperAllowed(String paramString) throws RemoteException;
  
  boolean isWallpaperBackupEligible(int paramInt1, int paramInt2) throws RemoteException;
  
  boolean isWallpaperSupported(String paramString) throws RemoteException;
  
  void registerWallpaperColorsCallback(IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, int paramInt2) throws RemoteException;
  
  void setDimensionHints(int paramInt1, int paramInt2, String paramString, int paramInt3) throws RemoteException;
  
  void setDisplayPadding(Rect paramRect, String paramString, int paramInt) throws RemoteException;
  
  void setInAmbientMode(boolean paramBoolean, long paramLong) throws RemoteException;
  
  boolean setLockWallpaperCallback(IWallpaperManagerCallback paramIWallpaperManagerCallback) throws RemoteException;
  
  ParcelFileDescriptor setWallpaper(String paramString1, String paramString2, Rect paramRect, boolean paramBoolean, Bundle paramBundle, int paramInt1, IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt2) throws RemoteException;
  
  void setWallpaperComponent(ComponentName paramComponentName) throws RemoteException;
  
  void setWallpaperComponentChecked(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException;
  
  void settingsRestored() throws RemoteException;
  
  void unregisterWallpaperColorsCallback(IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IWallpaperManager {
    public IBinder asBinder() {
      return null;
    }
    
    public void clearWallpaper(String param1String, int param1Int1, int param1Int2) throws RemoteException {}
    
    public int getHeightHint(int param1Int) throws RemoteException {
      return 0;
    }
    
    public String getName() throws RemoteException {
      return null;
    }
    
    public ParcelFileDescriptor getWallpaper(String param1String, IWallpaperManagerCallback param1IWallpaperManagerCallback, int param1Int1, Bundle param1Bundle, int param1Int2) throws RemoteException {
      return null;
    }
    
    public WallpaperColors getWallpaperColors(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      return null;
    }
    
    public int getWallpaperIdForUser(int param1Int1, int param1Int2) throws RemoteException {
      return 0;
    }
    
    public WallpaperInfo getWallpaperInfo(int param1Int) throws RemoteException {
      return null;
    }
    
    public ParcelFileDescriptor getWallpaperWithFeature(String param1String1, String param1String2, IWallpaperManagerCallback param1IWallpaperManagerCallback, int param1Int1, Bundle param1Bundle, int param1Int2) throws RemoteException {
      return null;
    }
    
    public int getWidthHint(int param1Int) throws RemoteException {
      return 0;
    }
    
    public boolean hasNamedWallpaper(String param1String) throws RemoteException {
      return false;
    }
    
    public boolean isSetWallpaperAllowed(String param1String) throws RemoteException {
      return false;
    }
    
    public boolean isWallpaperBackupEligible(int param1Int1, int param1Int2) throws RemoteException {
      return false;
    }
    
    public boolean isWallpaperSupported(String param1String) throws RemoteException {
      return false;
    }
    
    public void registerWallpaperColorsCallback(IWallpaperManagerCallback param1IWallpaperManagerCallback, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void setDimensionHints(int param1Int1, int param1Int2, String param1String, int param1Int3) throws RemoteException {}
    
    public void setDisplayPadding(Rect param1Rect, String param1String, int param1Int) throws RemoteException {}
    
    public void setInAmbientMode(boolean param1Boolean, long param1Long) throws RemoteException {}
    
    public boolean setLockWallpaperCallback(IWallpaperManagerCallback param1IWallpaperManagerCallback) throws RemoteException {
      return false;
    }
    
    public ParcelFileDescriptor setWallpaper(String param1String1, String param1String2, Rect param1Rect, boolean param1Boolean, Bundle param1Bundle, int param1Int1, IWallpaperManagerCallback param1IWallpaperManagerCallback, int param1Int2) throws RemoteException {
      return null;
    }
    
    public void setWallpaperComponent(ComponentName param1ComponentName) throws RemoteException {}
    
    public void setWallpaperComponentChecked(ComponentName param1ComponentName, String param1String, int param1Int) throws RemoteException {}
    
    public void settingsRestored() throws RemoteException {}
    
    public void unregisterWallpaperColorsCallback(IWallpaperManagerCallback param1IWallpaperManagerCallback, int param1Int1, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IWallpaperManager {
    private static final String DESCRIPTOR = "android.app.IWallpaperManager";
    
    static final int TRANSACTION_clearWallpaper = 8;
    
    static final int TRANSACTION_getHeightHint = 12;
    
    static final int TRANSACTION_getName = 14;
    
    static final int TRANSACTION_getWallpaper = 4;
    
    static final int TRANSACTION_getWallpaperColors = 20;
    
    static final int TRANSACTION_getWallpaperIdForUser = 6;
    
    static final int TRANSACTION_getWallpaperInfo = 7;
    
    static final int TRANSACTION_getWallpaperWithFeature = 5;
    
    static final int TRANSACTION_getWidthHint = 11;
    
    static final int TRANSACTION_hasNamedWallpaper = 9;
    
    static final int TRANSACTION_isSetWallpaperAllowed = 17;
    
    static final int TRANSACTION_isWallpaperBackupEligible = 18;
    
    static final int TRANSACTION_isWallpaperSupported = 16;
    
    static final int TRANSACTION_registerWallpaperColorsCallback = 21;
    
    static final int TRANSACTION_setDimensionHints = 10;
    
    static final int TRANSACTION_setDisplayPadding = 13;
    
    static final int TRANSACTION_setInAmbientMode = 23;
    
    static final int TRANSACTION_setLockWallpaperCallback = 19;
    
    static final int TRANSACTION_setWallpaper = 1;
    
    static final int TRANSACTION_setWallpaperComponent = 3;
    
    static final int TRANSACTION_setWallpaperComponentChecked = 2;
    
    static final int TRANSACTION_settingsRestored = 15;
    
    static final int TRANSACTION_unregisterWallpaperColorsCallback = 22;
    
    public Stub() {
      attachInterface(this, "android.app.IWallpaperManager");
    }
    
    public static IWallpaperManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IWallpaperManager");
      return (iInterface != null && iInterface instanceof IWallpaperManager) ? (IWallpaperManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IWallpaperManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 23:
          return "setInAmbientMode";
        case 22:
          return "unregisterWallpaperColorsCallback";
        case 21:
          return "registerWallpaperColorsCallback";
        case 20:
          return "getWallpaperColors";
        case 19:
          return "setLockWallpaperCallback";
        case 18:
          return "isWallpaperBackupEligible";
        case 17:
          return "isSetWallpaperAllowed";
        case 16:
          return "isWallpaperSupported";
        case 15:
          return "settingsRestored";
        case 14:
          return "getName";
        case 13:
          return "setDisplayPadding";
        case 12:
          return "getHeightHint";
        case 11:
          return "getWidthHint";
        case 10:
          return "setDimensionHints";
        case 9:
          return "hasNamedWallpaper";
        case 8:
          return "clearWallpaper";
        case 7:
          return "getWallpaperInfo";
        case 6:
          return "getWallpaperIdForUser";
        case 5:
          return "getWallpaperWithFeature";
        case 4:
          return "getWallpaper";
        case 3:
          return "setWallpaperComponent";
        case 2:
          return "setWallpaperComponentChecked";
        case 1:
          break;
      } 
      return "setWallpaper";
    }
    
    public static boolean setDefaultImpl(IWallpaperManager param1IWallpaperManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IWallpaperManager != null) {
          Proxy.sDefaultImpl = param1IWallpaperManager;
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
        boolean bool2;
        int j;
        boolean bool1;
        int i;
        WallpaperColors wallpaperColors;
        String str1;
        WallpaperInfo wallpaperInfo;
        Rect rect;
        Bundle bundle1;
        String str3;
        IWallpaperManagerCallback iWallpaperManagerCallback1;
        IWallpaperManagerCallback iWallpaperManagerCallback2;
        boolean bool = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 23:
            param1Parcel1.enforceInterface("android.app.IWallpaperManager");
            if (param1Parcel1.readInt() != 0)
              bool = true; 
            setInAmbientMode(bool, param1Parcel1.readLong());
            return true;
          case 22:
            param1Parcel1.enforceInterface("android.app.IWallpaperManager");
            unregisterWallpaperColorsCallback(IWallpaperManagerCallback.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readInt(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 21:
            param1Parcel1.enforceInterface("android.app.IWallpaperManager");
            registerWallpaperColorsCallback(IWallpaperManagerCallback.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readInt(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 20:
            param1Parcel1.enforceInterface("android.app.IWallpaperManager");
            wallpaperColors = getWallpaperColors(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            if (wallpaperColors != null) {
              param1Parcel2.writeInt(1);
              wallpaperColors.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 19:
            wallpaperColors.enforceInterface("android.app.IWallpaperManager");
            bool2 = setLockWallpaperCallback(IWallpaperManagerCallback.Stub.asInterface(wallpaperColors.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 18:
            wallpaperColors.enforceInterface("android.app.IWallpaperManager");
            bool2 = isWallpaperBackupEligible(wallpaperColors.readInt(), wallpaperColors.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 17:
            wallpaperColors.enforceInterface("android.app.IWallpaperManager");
            bool2 = isSetWallpaperAllowed(wallpaperColors.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 16:
            wallpaperColors.enforceInterface("android.app.IWallpaperManager");
            bool2 = isWallpaperSupported(wallpaperColors.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 15:
            wallpaperColors.enforceInterface("android.app.IWallpaperManager");
            settingsRestored();
            param1Parcel2.writeNoException();
            return true;
          case 14:
            wallpaperColors.enforceInterface("android.app.IWallpaperManager");
            str1 = getName();
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str1);
            return true;
          case 13:
            str1.enforceInterface("android.app.IWallpaperManager");
            if (str1.readInt() != 0) {
              rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)str1);
            } else {
              rect = null;
            } 
            setDisplayPadding(rect, str1.readString(), str1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 12:
            str1.enforceInterface("android.app.IWallpaperManager");
            j = getHeightHint(str1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 11:
            str1.enforceInterface("android.app.IWallpaperManager");
            j = getWidthHint(str1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 10:
            str1.enforceInterface("android.app.IWallpaperManager");
            setDimensionHints(str1.readInt(), str1.readInt(), str1.readString(), str1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 9:
            str1.enforceInterface("android.app.IWallpaperManager");
            bool1 = hasNamedWallpaper(str1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 8:
            str1.enforceInterface("android.app.IWallpaperManager");
            clearWallpaper(str1.readString(), str1.readInt(), str1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 7:
            str1.enforceInterface("android.app.IWallpaperManager");
            wallpaperInfo = getWallpaperInfo(str1.readInt());
            param1Parcel2.writeNoException();
            if (wallpaperInfo != null) {
              param1Parcel2.writeInt(1);
              wallpaperInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 6:
            wallpaperInfo.enforceInterface("android.app.IWallpaperManager");
            i = getWallpaperIdForUser(wallpaperInfo.readInt(), wallpaperInfo.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 5:
            wallpaperInfo.enforceInterface("android.app.IWallpaperManager");
            str3 = wallpaperInfo.readString();
            str4 = wallpaperInfo.readString();
            iWallpaperManagerCallback2 = IWallpaperManagerCallback.Stub.asInterface(wallpaperInfo.readStrongBinder());
            i = wallpaperInfo.readInt();
            bundle1 = new Bundle();
            parcelFileDescriptor = getWallpaperWithFeature(str3, str4, iWallpaperManagerCallback2, i, bundle1, wallpaperInfo.readInt());
            param1Parcel2.writeNoException();
            if (parcelFileDescriptor != null) {
              param1Parcel2.writeInt(1);
              parcelFileDescriptor.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            param1Parcel2.writeInt(1);
            bundle1.writeToParcel(param1Parcel2, 1);
            return true;
          case 4:
            parcelFileDescriptor.enforceInterface("android.app.IWallpaperManager");
            str4 = parcelFileDescriptor.readString();
            iWallpaperManagerCallback1 = IWallpaperManagerCallback.Stub.asInterface(parcelFileDescriptor.readStrongBinder());
            i = parcelFileDescriptor.readInt();
            bundle1 = new Bundle();
            parcelFileDescriptor = getWallpaper(str4, iWallpaperManagerCallback1, i, bundle1, parcelFileDescriptor.readInt());
            param1Parcel2.writeNoException();
            if (parcelFileDescriptor != null) {
              param1Parcel2.writeInt(1);
              parcelFileDescriptor.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            param1Parcel2.writeInt(1);
            bundle1.writeToParcel(param1Parcel2, 1);
            return true;
          case 3:
            parcelFileDescriptor.enforceInterface("android.app.IWallpaperManager");
            if (parcelFileDescriptor.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
            } else {
              parcelFileDescriptor = null;
            } 
            setWallpaperComponent((ComponentName)parcelFileDescriptor);
            param1Parcel2.writeNoException();
            return true;
          case 2:
            parcelFileDescriptor.enforceInterface("android.app.IWallpaperManager");
            if (parcelFileDescriptor.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
            } else {
              bundle1 = null;
            } 
            setWallpaperComponentChecked((ComponentName)bundle1, parcelFileDescriptor.readString(), parcelFileDescriptor.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        parcelFileDescriptor.enforceInterface("android.app.IWallpaperManager");
        String str2 = parcelFileDescriptor.readString();
        String str4 = parcelFileDescriptor.readString();
        if (parcelFileDescriptor.readInt() != 0) {
          Rect rect1 = (Rect)Rect.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
        } else {
          bundle1 = null;
        } 
        if (parcelFileDescriptor.readInt() != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        Bundle bundle2 = new Bundle();
        ParcelFileDescriptor parcelFileDescriptor = setWallpaper(str2, str4, (Rect)bundle1, bool, bundle2, parcelFileDescriptor.readInt(), IWallpaperManagerCallback.Stub.asInterface(parcelFileDescriptor.readStrongBinder()), parcelFileDescriptor.readInt());
        param1Parcel2.writeNoException();
        if (parcelFileDescriptor != null) {
          param1Parcel2.writeInt(1);
          parcelFileDescriptor.writeToParcel(param1Parcel2, 1);
        } else {
          param1Parcel2.writeInt(0);
        } 
        param1Parcel2.writeInt(1);
        bundle2.writeToParcel(param1Parcel2, 1);
        return true;
      } 
      param1Parcel2.writeString("android.app.IWallpaperManager");
      return true;
    }
    
    private static class Proxy implements IWallpaperManager {
      public static IWallpaperManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void clearWallpaper(String param2String, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            IWallpaperManager.Stub.getDefaultImpl().clearWallpaper(param2String, param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getHeightHint(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            param2Int = IWallpaperManager.Stub.getDefaultImpl().getHeightHint(param2Int);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IWallpaperManager";
      }
      
      public String getName() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null)
            return IWallpaperManager.Stub.getDefaultImpl().getName(); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ParcelFileDescriptor getWallpaper(String param2String, IWallpaperManagerCallback param2IWallpaperManagerCallback, int param2Int1, Bundle param2Bundle, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeString(param2String);
          if (param2IWallpaperManagerCallback != null) {
            iBinder = param2IWallpaperManagerCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null)
            return IWallpaperManager.Stub.getDefaultImpl().getWallpaper(param2String, param2IWallpaperManagerCallback, param2Int1, param2Bundle, param2Int2); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          if (parcel2.readInt() != 0)
            param2Bundle.readFromParcel(parcel2); 
          return (ParcelFileDescriptor)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public WallpaperColors getWallpaperColors(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          WallpaperColors wallpaperColors;
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            wallpaperColors = IWallpaperManager.Stub.getDefaultImpl().getWallpaperColors(param2Int1, param2Int2, param2Int3);
            return wallpaperColors;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            wallpaperColors = (WallpaperColors)WallpaperColors.CREATOR.createFromParcel(parcel2);
          } else {
            wallpaperColors = null;
          } 
          return wallpaperColors;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getWallpaperIdForUser(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            param2Int1 = IWallpaperManager.Stub.getDefaultImpl().getWallpaperIdForUser(param2Int1, param2Int2);
            return param2Int1;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          return param2Int1;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public WallpaperInfo getWallpaperInfo(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          WallpaperInfo wallpaperInfo;
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            wallpaperInfo = IWallpaperManager.Stub.getDefaultImpl().getWallpaperInfo(param2Int);
            return wallpaperInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            wallpaperInfo = (WallpaperInfo)WallpaperInfo.CREATOR.createFromParcel(parcel2);
          } else {
            wallpaperInfo = null;
          } 
          return wallpaperInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ParcelFileDescriptor getWallpaperWithFeature(String param2String1, String param2String2, IWallpaperManagerCallback param2IWallpaperManagerCallback, int param2Int1, Bundle param2Bundle, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          try {
            parcel1.writeString(param2String1);
            try {
              IBinder iBinder;
              parcel1.writeString(param2String2);
              if (param2IWallpaperManagerCallback != null) {
                iBinder = param2IWallpaperManagerCallback.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              try {
                parcel1.writeInt(param2Int1);
                try {
                  parcel1.writeInt(param2Int2);
                  try {
                    if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
                      ParcelFileDescriptor parcelFileDescriptor = IWallpaperManager.Stub.getDefaultImpl().getWallpaperWithFeature(param2String1, param2String2, param2IWallpaperManagerCallback, param2Int1, param2Bundle, param2Int2);
                      parcel2.recycle();
                      parcel1.recycle();
                      return parcelFileDescriptor;
                    } 
                    parcel2.readException();
                    if (parcel2.readInt() != 0) {
                      ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
                    } else {
                      param2String1 = null;
                    } 
                    param2Int1 = parcel2.readInt();
                    if (param2Int1 != 0)
                      try {
                        param2Bundle.readFromParcel(parcel2);
                      } finally {} 
                    parcel2.recycle();
                    parcel1.recycle();
                    return (ParcelFileDescriptor)param2String1;
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2String1;
      }
      
      public int getWidthHint(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            param2Int = IWallpaperManager.Stub.getDefaultImpl().getWidthHint(param2Int);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean hasNamedWallpaper(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(9, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            bool = IWallpaperManager.Stub.getDefaultImpl().hasNamedWallpaper(param2String);
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
      
      public boolean isSetWallpaperAllowed(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(17, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            bool = IWallpaperManager.Stub.getDefaultImpl().isSetWallpaperAllowed(param2String);
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
      
      public boolean isWallpaperBackupEligible(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(18, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            bool = IWallpaperManager.Stub.getDefaultImpl().isWallpaperBackupEligible(param2Int1, param2Int2);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isWallpaperSupported(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(16, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            bool = IWallpaperManager.Stub.getDefaultImpl().isWallpaperSupported(param2String);
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
      
      public void registerWallpaperColorsCallback(IWallpaperManagerCallback param2IWallpaperManagerCallback, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          if (param2IWallpaperManagerCallback != null) {
            iBinder = param2IWallpaperManagerCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            IWallpaperManager.Stub.getDefaultImpl().registerWallpaperColorsCallback(param2IWallpaperManagerCallback, param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setDimensionHints(int param2Int1, int param2Int2, String param2String, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            IWallpaperManager.Stub.getDefaultImpl().setDimensionHints(param2Int1, param2Int2, param2String, param2Int3);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setDisplayPadding(Rect param2Rect, String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          if (param2Rect != null) {
            parcel1.writeInt(1);
            param2Rect.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            IWallpaperManager.Stub.getDefaultImpl().setDisplayPadding(param2Rect, param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setInAmbientMode(boolean param2Boolean, long param2Long) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.app.IWallpaperManager");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(23, parcel, null, 1) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            IWallpaperManager.Stub.getDefaultImpl().setInAmbientMode(param2Boolean, param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public boolean setLockWallpaperCallback(IWallpaperManagerCallback param2IWallpaperManagerCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          if (param2IWallpaperManagerCallback != null) {
            iBinder = param2IWallpaperManagerCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(19, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            bool = IWallpaperManager.Stub.getDefaultImpl().setLockWallpaperCallback(param2IWallpaperManagerCallback);
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
      
      public ParcelFileDescriptor setWallpaper(String param2String1, String param2String2, Rect param2Rect, boolean param2Boolean, Bundle param2Bundle, int param2Int1, IWallpaperManagerCallback param2IWallpaperManagerCallback, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          try {
            parcel1.writeString(param2String1);
            try {
              boolean bool;
              parcel1.writeString(param2String2);
              if (param2Rect != null) {
                parcel1.writeInt(1);
                param2Rect.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (param2Boolean) {
                bool = true;
              } else {
                bool = false;
              } 
              parcel1.writeInt(bool);
              try {
                IBinder iBinder;
                parcel1.writeInt(param2Int1);
                if (param2IWallpaperManagerCallback != null) {
                  iBinder = param2IWallpaperManagerCallback.asBinder();
                } else {
                  iBinder = null;
                } 
                parcel1.writeStrongBinder(iBinder);
                parcel1.writeInt(param2Int2);
                if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
                  ParcelFileDescriptor parcelFileDescriptor = IWallpaperManager.Stub.getDefaultImpl().setWallpaper(param2String1, param2String2, param2Rect, param2Boolean, param2Bundle, param2Int1, param2IWallpaperManagerCallback, param2Int2);
                  parcel2.recycle();
                  parcel1.recycle();
                  return parcelFileDescriptor;
                } 
                parcel2.readException();
                if (parcel2.readInt() != 0) {
                  ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
                } else {
                  param2String1 = null;
                } 
                param2Int1 = parcel2.readInt();
                if (param2Int1 != 0)
                  try {
                    param2Bundle.readFromParcel(parcel2);
                  } finally {} 
                parcel2.recycle();
                parcel1.recycle();
                return (ParcelFileDescriptor)param2String1;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2String1;
      }
      
      public void setWallpaperComponent(ComponentName param2ComponentName) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            IWallpaperManager.Stub.getDefaultImpl().setWallpaperComponent(param2ComponentName);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setWallpaperComponentChecked(ComponentName param2ComponentName, String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            IWallpaperManager.Stub.getDefaultImpl().setWallpaperComponentChecked(param2ComponentName, param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void settingsRestored() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            IWallpaperManager.Stub.getDefaultImpl().settingsRestored();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unregisterWallpaperColorsCallback(IWallpaperManagerCallback param2IWallpaperManagerCallback, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IWallpaperManager");
          if (param2IWallpaperManagerCallback != null) {
            iBinder = param2IWallpaperManagerCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
            IWallpaperManager.Stub.getDefaultImpl().unregisterWallpaperColorsCallback(param2IWallpaperManagerCallback, param2Int1, param2Int2);
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
  
  private static class Proxy implements IWallpaperManager {
    public static IWallpaperManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void clearWallpaper(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          IWallpaperManager.Stub.getDefaultImpl().clearWallpaper(param1String, param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getHeightHint(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          param1Int = IWallpaperManager.Stub.getDefaultImpl().getHeightHint(param1Int);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IWallpaperManager";
    }
    
    public String getName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null)
          return IWallpaperManager.Stub.getDefaultImpl().getName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor getWallpaper(String param1String, IWallpaperManagerCallback param1IWallpaperManagerCallback, int param1Int1, Bundle param1Bundle, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeString(param1String);
        if (param1IWallpaperManagerCallback != null) {
          iBinder = param1IWallpaperManagerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null)
          return IWallpaperManager.Stub.getDefaultImpl().getWallpaper(param1String, param1IWallpaperManagerCallback, param1Int1, param1Bundle, param1Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        if (parcel2.readInt() != 0)
          param1Bundle.readFromParcel(parcel2); 
        return (ParcelFileDescriptor)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public WallpaperColors getWallpaperColors(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        WallpaperColors wallpaperColors;
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          wallpaperColors = IWallpaperManager.Stub.getDefaultImpl().getWallpaperColors(param1Int1, param1Int2, param1Int3);
          return wallpaperColors;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          wallpaperColors = (WallpaperColors)WallpaperColors.CREATOR.createFromParcel(parcel2);
        } else {
          wallpaperColors = null;
        } 
        return wallpaperColors;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getWallpaperIdForUser(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          param1Int1 = IWallpaperManager.Stub.getDefaultImpl().getWallpaperIdForUser(param1Int1, param1Int2);
          return param1Int1;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        return param1Int1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public WallpaperInfo getWallpaperInfo(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        WallpaperInfo wallpaperInfo;
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          wallpaperInfo = IWallpaperManager.Stub.getDefaultImpl().getWallpaperInfo(param1Int);
          return wallpaperInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          wallpaperInfo = (WallpaperInfo)WallpaperInfo.CREATOR.createFromParcel(parcel2);
        } else {
          wallpaperInfo = null;
        } 
        return wallpaperInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor getWallpaperWithFeature(String param1String1, String param1String2, IWallpaperManagerCallback param1IWallpaperManagerCallback, int param1Int1, Bundle param1Bundle, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        try {
          parcel1.writeString(param1String1);
          try {
            IBinder iBinder;
            parcel1.writeString(param1String2);
            if (param1IWallpaperManagerCallback != null) {
              iBinder = param1IWallpaperManagerCallback.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            try {
              parcel1.writeInt(param1Int1);
              try {
                parcel1.writeInt(param1Int2);
                try {
                  if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
                    ParcelFileDescriptor parcelFileDescriptor = IWallpaperManager.Stub.getDefaultImpl().getWallpaperWithFeature(param1String1, param1String2, param1IWallpaperManagerCallback, param1Int1, param1Bundle, param1Int2);
                    parcel2.recycle();
                    parcel1.recycle();
                    return parcelFileDescriptor;
                  } 
                  parcel2.readException();
                  if (parcel2.readInt() != 0) {
                    ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
                  } else {
                    param1String1 = null;
                  } 
                  param1Int1 = parcel2.readInt();
                  if (param1Int1 != 0)
                    try {
                      param1Bundle.readFromParcel(parcel2);
                    } finally {} 
                  parcel2.recycle();
                  parcel1.recycle();
                  return (ParcelFileDescriptor)param1String1;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1String1;
    }
    
    public int getWidthHint(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          param1Int = IWallpaperManager.Stub.getDefaultImpl().getWidthHint(param1Int);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasNamedWallpaper(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(9, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          bool = IWallpaperManager.Stub.getDefaultImpl().hasNamedWallpaper(param1String);
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
    
    public boolean isSetWallpaperAllowed(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(17, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          bool = IWallpaperManager.Stub.getDefaultImpl().isSetWallpaperAllowed(param1String);
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
    
    public boolean isWallpaperBackupEligible(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(18, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          bool = IWallpaperManager.Stub.getDefaultImpl().isWallpaperBackupEligible(param1Int1, param1Int2);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isWallpaperSupported(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(16, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          bool = IWallpaperManager.Stub.getDefaultImpl().isWallpaperSupported(param1String);
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
    
    public void registerWallpaperColorsCallback(IWallpaperManagerCallback param1IWallpaperManagerCallback, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        if (param1IWallpaperManagerCallback != null) {
          iBinder = param1IWallpaperManagerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          IWallpaperManager.Stub.getDefaultImpl().registerWallpaperColorsCallback(param1IWallpaperManagerCallback, param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDimensionHints(int param1Int1, int param1Int2, String param1String, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          IWallpaperManager.Stub.getDefaultImpl().setDimensionHints(param1Int1, param1Int2, param1String, param1Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDisplayPadding(Rect param1Rect, String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        if (param1Rect != null) {
          parcel1.writeInt(1);
          param1Rect.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          IWallpaperManager.Stub.getDefaultImpl().setDisplayPadding(param1Rect, param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setInAmbientMode(boolean param1Boolean, long param1Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IWallpaperManager");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(23, parcel, null, 1) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          IWallpaperManager.Stub.getDefaultImpl().setInAmbientMode(param1Boolean, param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public boolean setLockWallpaperCallback(IWallpaperManagerCallback param1IWallpaperManagerCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        if (param1IWallpaperManagerCallback != null) {
          iBinder = param1IWallpaperManagerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(19, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          bool = IWallpaperManager.Stub.getDefaultImpl().setLockWallpaperCallback(param1IWallpaperManagerCallback);
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
    
    public ParcelFileDescriptor setWallpaper(String param1String1, String param1String2, Rect param1Rect, boolean param1Boolean, Bundle param1Bundle, int param1Int1, IWallpaperManagerCallback param1IWallpaperManagerCallback, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        try {
          parcel1.writeString(param1String1);
          try {
            boolean bool;
            parcel1.writeString(param1String2);
            if (param1Rect != null) {
              parcel1.writeInt(1);
              param1Rect.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            if (param1Boolean) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel1.writeInt(bool);
            try {
              IBinder iBinder;
              parcel1.writeInt(param1Int1);
              if (param1IWallpaperManagerCallback != null) {
                iBinder = param1IWallpaperManagerCallback.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              parcel1.writeInt(param1Int2);
              if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
                ParcelFileDescriptor parcelFileDescriptor = IWallpaperManager.Stub.getDefaultImpl().setWallpaper(param1String1, param1String2, param1Rect, param1Boolean, param1Bundle, param1Int1, param1IWallpaperManagerCallback, param1Int2);
                parcel2.recycle();
                parcel1.recycle();
                return parcelFileDescriptor;
              } 
              parcel2.readException();
              if (parcel2.readInt() != 0) {
                ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
              } else {
                param1String1 = null;
              } 
              param1Int1 = parcel2.readInt();
              if (param1Int1 != 0)
                try {
                  param1Bundle.readFromParcel(parcel2);
                } finally {} 
              parcel2.recycle();
              parcel1.recycle();
              return (ParcelFileDescriptor)param1String1;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1String1;
    }
    
    public void setWallpaperComponent(ComponentName param1ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          IWallpaperManager.Stub.getDefaultImpl().setWallpaperComponent(param1ComponentName);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setWallpaperComponentChecked(ComponentName param1ComponentName, String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          IWallpaperManager.Stub.getDefaultImpl().setWallpaperComponentChecked(param1ComponentName, param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void settingsRestored() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          IWallpaperManager.Stub.getDefaultImpl().settingsRestored();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterWallpaperColorsCallback(IWallpaperManagerCallback param1IWallpaperManagerCallback, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IWallpaperManager");
        if (param1IWallpaperManagerCallback != null) {
          iBinder = param1IWallpaperManagerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
          IWallpaperManager.Stub.getDefaultImpl().unregisterWallpaperColorsCallback(param1IWallpaperManagerCallback, param1Int1, param1Int2);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IWallpaperManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
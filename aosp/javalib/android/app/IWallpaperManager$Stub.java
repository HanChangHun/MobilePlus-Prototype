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

public abstract class Stub extends Binder implements IWallpaperManager {
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
  
  public static IWallpaperManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IWallpaperManager");
    return (iInterface != null && iInterface instanceof IWallpaperManager) ? (IWallpaperManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IWallpaperManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(IWallpaperManager paramIWallpaperManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIWallpaperManager != null) {
        Proxy.sDefaultImpl = paramIWallpaperManager;
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
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 23:
          paramParcel1.enforceInterface("android.app.IWallpaperManager");
          if (paramParcel1.readInt() != 0)
            bool = true; 
          setInAmbientMode(bool, paramParcel1.readLong());
          return true;
        case 22:
          paramParcel1.enforceInterface("android.app.IWallpaperManager");
          unregisterWallpaperColorsCallback(IWallpaperManagerCallback.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 21:
          paramParcel1.enforceInterface("android.app.IWallpaperManager");
          registerWallpaperColorsCallback(IWallpaperManagerCallback.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 20:
          paramParcel1.enforceInterface("android.app.IWallpaperManager");
          wallpaperColors = getWallpaperColors(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (wallpaperColors != null) {
            paramParcel2.writeInt(1);
            wallpaperColors.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 19:
          wallpaperColors.enforceInterface("android.app.IWallpaperManager");
          bool2 = setLockWallpaperCallback(IWallpaperManagerCallback.Stub.asInterface(wallpaperColors.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 18:
          wallpaperColors.enforceInterface("android.app.IWallpaperManager");
          bool2 = isWallpaperBackupEligible(wallpaperColors.readInt(), wallpaperColors.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 17:
          wallpaperColors.enforceInterface("android.app.IWallpaperManager");
          bool2 = isSetWallpaperAllowed(wallpaperColors.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 16:
          wallpaperColors.enforceInterface("android.app.IWallpaperManager");
          bool2 = isWallpaperSupported(wallpaperColors.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 15:
          wallpaperColors.enforceInterface("android.app.IWallpaperManager");
          settingsRestored();
          paramParcel2.writeNoException();
          return true;
        case 14:
          wallpaperColors.enforceInterface("android.app.IWallpaperManager");
          str1 = getName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 13:
          str1.enforceInterface("android.app.IWallpaperManager");
          if (str1.readInt() != 0) {
            rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)str1);
          } else {
            rect = null;
          } 
          setDisplayPadding(rect, str1.readString(), str1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 12:
          str1.enforceInterface("android.app.IWallpaperManager");
          j = getHeightHint(str1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 11:
          str1.enforceInterface("android.app.IWallpaperManager");
          j = getWidthHint(str1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 10:
          str1.enforceInterface("android.app.IWallpaperManager");
          setDimensionHints(str1.readInt(), str1.readInt(), str1.readString(), str1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 9:
          str1.enforceInterface("android.app.IWallpaperManager");
          bool1 = hasNamedWallpaper(str1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 8:
          str1.enforceInterface("android.app.IWallpaperManager");
          clearWallpaper(str1.readString(), str1.readInt(), str1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 7:
          str1.enforceInterface("android.app.IWallpaperManager");
          wallpaperInfo = getWallpaperInfo(str1.readInt());
          paramParcel2.writeNoException();
          if (wallpaperInfo != null) {
            paramParcel2.writeInt(1);
            wallpaperInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 6:
          wallpaperInfo.enforceInterface("android.app.IWallpaperManager");
          i = getWallpaperIdForUser(wallpaperInfo.readInt(), wallpaperInfo.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 5:
          wallpaperInfo.enforceInterface("android.app.IWallpaperManager");
          str3 = wallpaperInfo.readString();
          str4 = wallpaperInfo.readString();
          iWallpaperManagerCallback2 = IWallpaperManagerCallback.Stub.asInterface(wallpaperInfo.readStrongBinder());
          i = wallpaperInfo.readInt();
          bundle1 = new Bundle();
          parcelFileDescriptor = getWallpaperWithFeature(str3, str4, iWallpaperManagerCallback2, i, bundle1, wallpaperInfo.readInt());
          paramParcel2.writeNoException();
          if (parcelFileDescriptor != null) {
            paramParcel2.writeInt(1);
            parcelFileDescriptor.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          paramParcel2.writeInt(1);
          bundle1.writeToParcel(paramParcel2, 1);
          return true;
        case 4:
          parcelFileDescriptor.enforceInterface("android.app.IWallpaperManager");
          str4 = parcelFileDescriptor.readString();
          iWallpaperManagerCallback1 = IWallpaperManagerCallback.Stub.asInterface(parcelFileDescriptor.readStrongBinder());
          i = parcelFileDescriptor.readInt();
          bundle1 = new Bundle();
          parcelFileDescriptor = getWallpaper(str4, iWallpaperManagerCallback1, i, bundle1, parcelFileDescriptor.readInt());
          paramParcel2.writeNoException();
          if (parcelFileDescriptor != null) {
            paramParcel2.writeInt(1);
            parcelFileDescriptor.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          paramParcel2.writeInt(1);
          bundle1.writeToParcel(paramParcel2, 1);
          return true;
        case 3:
          parcelFileDescriptor.enforceInterface("android.app.IWallpaperManager");
          if (parcelFileDescriptor.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
          } else {
            parcelFileDescriptor = null;
          } 
          setWallpaperComponent((ComponentName)parcelFileDescriptor);
          paramParcel2.writeNoException();
          return true;
        case 2:
          parcelFileDescriptor.enforceInterface("android.app.IWallpaperManager");
          if (parcelFileDescriptor.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
          } else {
            bundle1 = null;
          } 
          setWallpaperComponentChecked((ComponentName)bundle1, parcelFileDescriptor.readString(), parcelFileDescriptor.readInt());
          paramParcel2.writeNoException();
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
      paramParcel2.writeNoException();
      if (parcelFileDescriptor != null) {
        paramParcel2.writeInt(1);
        parcelFileDescriptor.writeToParcel(paramParcel2, 1);
      } else {
        paramParcel2.writeInt(0);
      } 
      paramParcel2.writeInt(1);
      bundle2.writeToParcel(paramParcel2, 1);
      return true;
    } 
    paramParcel2.writeString("android.app.IWallpaperManager");
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IWallpaperManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
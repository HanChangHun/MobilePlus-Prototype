package android.app;

import android.content.ComponentName;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

class Proxy implements IWallpaperManager {
  public static IWallpaperManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void clearWallpaper(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        IWallpaperManager.Stub.getDefaultImpl().clearWallpaper(paramString, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getHeightHint(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        paramInt = IWallpaperManager.Stub.getDefaultImpl().getHeightHint(paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
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
  
  public ParcelFileDescriptor getWallpaper(String paramString, IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, Bundle paramBundle, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeString(paramString);
      if (paramIWallpaperManagerCallback != null) {
        iBinder = paramIWallpaperManagerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null)
        return IWallpaperManager.Stub.getDefaultImpl().getWallpaper(paramString, paramIWallpaperManagerCallback, paramInt1, paramBundle, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      if (parcel2.readInt() != 0)
        paramBundle.readFromParcel(parcel2); 
      return (ParcelFileDescriptor)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public WallpaperColors getWallpaperColors(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      WallpaperColors wallpaperColors;
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        wallpaperColors = IWallpaperManager.Stub.getDefaultImpl().getWallpaperColors(paramInt1, paramInt2, paramInt3);
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
  
  public int getWallpaperIdForUser(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        paramInt1 = IWallpaperManager.Stub.getDefaultImpl().getWallpaperIdForUser(paramInt1, paramInt2);
        return paramInt1;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      return paramInt1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public WallpaperInfo getWallpaperInfo(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      WallpaperInfo wallpaperInfo;
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        wallpaperInfo = IWallpaperManager.Stub.getDefaultImpl().getWallpaperInfo(paramInt);
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
  
  public ParcelFileDescriptor getWallpaperWithFeature(String paramString1, String paramString2, IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, Bundle paramBundle, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      try {
        parcel1.writeString(paramString1);
        try {
          IBinder iBinder;
          parcel1.writeString(paramString2);
          if (paramIWallpaperManagerCallback != null) {
            iBinder = paramIWallpaperManagerCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          try {
            parcel1.writeInt(paramInt1);
            try {
              parcel1.writeInt(paramInt2);
              try {
                if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
                  ParcelFileDescriptor parcelFileDescriptor = IWallpaperManager.Stub.getDefaultImpl().getWallpaperWithFeature(paramString1, paramString2, paramIWallpaperManagerCallback, paramInt1, paramBundle, paramInt2);
                  parcel2.recycle();
                  parcel1.recycle();
                  return parcelFileDescriptor;
                } 
                parcel2.readException();
                if (parcel2.readInt() != 0) {
                  ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
                } else {
                  paramString1 = null;
                } 
                paramInt1 = parcel2.readInt();
                if (paramInt1 != 0)
                  try {
                    paramBundle.readFromParcel(parcel2);
                  } finally {} 
                parcel2.recycle();
                parcel1.recycle();
                return (ParcelFileDescriptor)paramString1;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public int getWidthHint(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        paramInt = IWallpaperManager.Stub.getDefaultImpl().getWidthHint(paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasNamedWallpaper(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(9, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        bool = IWallpaperManager.Stub.getDefaultImpl().hasNamedWallpaper(paramString);
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
  
  public boolean isSetWallpaperAllowed(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(17, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        bool = IWallpaperManager.Stub.getDefaultImpl().isSetWallpaperAllowed(paramString);
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
  
  public boolean isWallpaperBackupEligible(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(18, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        bool = IWallpaperManager.Stub.getDefaultImpl().isWallpaperBackupEligible(paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isWallpaperSupported(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(16, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        bool = IWallpaperManager.Stub.getDefaultImpl().isWallpaperSupported(paramString);
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
  
  public void registerWallpaperColorsCallback(IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      if (paramIWallpaperManagerCallback != null) {
        iBinder = paramIWallpaperManagerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        IWallpaperManager.Stub.getDefaultImpl().registerWallpaperColorsCallback(paramIWallpaperManagerCallback, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setDimensionHints(int paramInt1, int paramInt2, String paramString, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        IWallpaperManager.Stub.getDefaultImpl().setDimensionHints(paramInt1, paramInt2, paramString, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setDisplayPadding(Rect paramRect, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      if (paramRect != null) {
        parcel1.writeInt(1);
        paramRect.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        IWallpaperManager.Stub.getDefaultImpl().setDisplayPadding(paramRect, paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setInAmbientMode(boolean paramBoolean, long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.IWallpaperManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(23, parcel, null, 1) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        IWallpaperManager.Stub.getDefaultImpl().setInAmbientMode(paramBoolean, paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public boolean setLockWallpaperCallback(IWallpaperManagerCallback paramIWallpaperManagerCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      if (paramIWallpaperManagerCallback != null) {
        iBinder = paramIWallpaperManagerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(19, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        bool = IWallpaperManager.Stub.getDefaultImpl().setLockWallpaperCallback(paramIWallpaperManagerCallback);
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
  
  public ParcelFileDescriptor setWallpaper(String paramString1, String paramString2, Rect paramRect, boolean paramBoolean, Bundle paramBundle, int paramInt1, IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      try {
        parcel1.writeString(paramString1);
        try {
          boolean bool;
          parcel1.writeString(paramString2);
          if (paramRect != null) {
            parcel1.writeInt(1);
            paramRect.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (paramBoolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          try {
            IBinder iBinder;
            parcel1.writeInt(paramInt1);
            if (paramIWallpaperManagerCallback != null) {
              iBinder = paramIWallpaperManagerCallback.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            parcel1.writeInt(paramInt2);
            if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
              ParcelFileDescriptor parcelFileDescriptor = IWallpaperManager.Stub.getDefaultImpl().setWallpaper(paramString1, paramString2, paramRect, paramBoolean, paramBundle, paramInt1, paramIWallpaperManagerCallback, paramInt2);
              parcel2.recycle();
              parcel1.recycle();
              return parcelFileDescriptor;
            } 
            parcel2.readException();
            if (parcel2.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
            } else {
              paramString1 = null;
            } 
            paramInt1 = parcel2.readInt();
            if (paramInt1 != 0)
              try {
                paramBundle.readFromParcel(parcel2);
              } finally {} 
            parcel2.recycle();
            parcel1.recycle();
            return (ParcelFileDescriptor)paramString1;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public void setWallpaperComponent(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        IWallpaperManager.Stub.getDefaultImpl().setWallpaperComponent(paramComponentName);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setWallpaperComponentChecked(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        IWallpaperManager.Stub.getDefaultImpl().setWallpaperComponentChecked(paramComponentName, paramString, paramInt);
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
  
  public void unregisterWallpaperColorsCallback(IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IWallpaperManager");
      if (paramIWallpaperManagerCallback != null) {
        iBinder = paramIWallpaperManagerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IWallpaperManager.Stub.getDefaultImpl() != null) {
        IWallpaperManager.Stub.getDefaultImpl().unregisterWallpaperColorsCallback(paramIWallpaperManagerCallback, paramInt1, paramInt2);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IWallpaperManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
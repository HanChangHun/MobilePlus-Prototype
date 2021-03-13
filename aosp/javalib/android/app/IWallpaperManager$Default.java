package android.app;

import android.content.ComponentName;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public class Default implements IWallpaperManager {
  public IBinder asBinder() {
    return null;
  }
  
  public void clearWallpaper(String paramString, int paramInt1, int paramInt2) throws RemoteException {}
  
  public int getHeightHint(int paramInt) throws RemoteException {
    return 0;
  }
  
  public String getName() throws RemoteException {
    return null;
  }
  
  public ParcelFileDescriptor getWallpaper(String paramString, IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, Bundle paramBundle, int paramInt2) throws RemoteException {
    return null;
  }
  
  public WallpaperColors getWallpaperColors(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    return null;
  }
  
  public int getWallpaperIdForUser(int paramInt1, int paramInt2) throws RemoteException {
    return 0;
  }
  
  public WallpaperInfo getWallpaperInfo(int paramInt) throws RemoteException {
    return null;
  }
  
  public ParcelFileDescriptor getWallpaperWithFeature(String paramString1, String paramString2, IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, Bundle paramBundle, int paramInt2) throws RemoteException {
    return null;
  }
  
  public int getWidthHint(int paramInt) throws RemoteException {
    return 0;
  }
  
  public boolean hasNamedWallpaper(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isSetWallpaperAllowed(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isWallpaperBackupEligible(int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean isWallpaperSupported(String paramString) throws RemoteException {
    return false;
  }
  
  public void registerWallpaperColorsCallback(IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void setDimensionHints(int paramInt1, int paramInt2, String paramString, int paramInt3) throws RemoteException {}
  
  public void setDisplayPadding(Rect paramRect, String paramString, int paramInt) throws RemoteException {}
  
  public void setInAmbientMode(boolean paramBoolean, long paramLong) throws RemoteException {}
  
  public boolean setLockWallpaperCallback(IWallpaperManagerCallback paramIWallpaperManagerCallback) throws RemoteException {
    return false;
  }
  
  public ParcelFileDescriptor setWallpaper(String paramString1, String paramString2, Rect paramRect, boolean paramBoolean, Bundle paramBundle, int paramInt1, IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt2) throws RemoteException {
    return null;
  }
  
  public void setWallpaperComponent(ComponentName paramComponentName) throws RemoteException {}
  
  public void setWallpaperComponentChecked(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {}
  
  public void settingsRestored() throws RemoteException {}
  
  public void unregisterWallpaperColorsCallback(IWallpaperManagerCallback paramIWallpaperManagerCallback, int paramInt1, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IWallpaperManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
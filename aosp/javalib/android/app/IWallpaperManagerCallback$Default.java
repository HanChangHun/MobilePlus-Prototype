package android.app;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IWallpaperManagerCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onWallpaperChanged() throws RemoteException {}
  
  public void onWallpaperColorsChanged(WallpaperColors paramWallpaperColors, int paramInt1, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IWallpaperManagerCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
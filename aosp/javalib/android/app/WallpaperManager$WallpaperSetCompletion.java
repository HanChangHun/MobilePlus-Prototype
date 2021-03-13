package android.app;

import android.os.RemoteException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class WallpaperSetCompletion extends IWallpaperManagerCallback.Stub {
  final CountDownLatch mLatch = new CountDownLatch(1);
  
  public void onWallpaperChanged() throws RemoteException {
    this.mLatch.countDown();
  }
  
  public void onWallpaperColorsChanged(WallpaperColors paramWallpaperColors, int paramInt1, int paramInt2) throws RemoteException {
    WallpaperManager.access$100().onWallpaperColorsChanged(paramWallpaperColors, paramInt1, paramInt2);
  }
  
  public void waitForCompletion() {
    try {
      this.mLatch.await(30L, TimeUnit.SECONDS);
    } catch (InterruptedException interruptedException) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WallpaperManager$WallpaperSetCompletion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
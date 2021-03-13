package android.content.pm;

import android.app.PackageDeleteObserver;
import android.os.RemoteException;

public class LegacyPackageDeleteObserver extends PackageDeleteObserver {
  private final IPackageDeleteObserver mLegacy;
  
  public LegacyPackageDeleteObserver(IPackageDeleteObserver paramIPackageDeleteObserver) {
    this.mLegacy = paramIPackageDeleteObserver;
  }
  
  public void onPackageDeleted(String paramString1, int paramInt, String paramString2) {
    IPackageDeleteObserver iPackageDeleteObserver = this.mLegacy;
    if (iPackageDeleteObserver == null)
      return; 
    try {
      iPackageDeleteObserver.packageDeleted(paramString1, paramInt);
    } catch (RemoteException remoteException) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageManager$LegacyPackageDeleteObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
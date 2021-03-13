package android.app;

import android.content.Intent;
import android.content.pm.IPackageDeleteObserver2;

class null extends IPackageDeleteObserver2.Stub {
  public void onPackageDeleted(String paramString1, int paramInt, String paramString2) {
    PackageDeleteObserver.this.onPackageDeleted(paramString1, paramInt, paramString2);
  }
  
  public void onUserActionRequired(Intent paramIntent) {
    PackageDeleteObserver.this.onUserActionRequired(paramIntent);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PackageDeleteObserver$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
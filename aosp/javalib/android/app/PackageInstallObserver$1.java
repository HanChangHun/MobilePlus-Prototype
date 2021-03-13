package android.app;

import android.content.Intent;
import android.content.pm.IPackageInstallObserver2;
import android.os.Bundle;

class null extends IPackageInstallObserver2.Stub {
  public void onPackageInstalled(String paramString1, int paramInt, String paramString2, Bundle paramBundle) {
    PackageInstallObserver.this.onPackageInstalled(paramString1, paramInt, paramString2, paramBundle);
  }
  
  public void onUserActionRequired(Intent paramIntent) {
    PackageInstallObserver.this.onUserActionRequired(paramIntent);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PackageInstallObserver$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
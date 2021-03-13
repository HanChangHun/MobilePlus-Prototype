package android.app;

import android.content.Intent;
import android.content.pm.IPackageDeleteObserver2;

public class PackageDeleteObserver {
  private final IPackageDeleteObserver2.Stub mBinder = new IPackageDeleteObserver2.Stub() {
      public void onPackageDeleted(String param1String1, int param1Int, String param1String2) {
        PackageDeleteObserver.this.onPackageDeleted(param1String1, param1Int, param1String2);
      }
      
      public void onUserActionRequired(Intent param1Intent) {
        PackageDeleteObserver.this.onUserActionRequired(param1Intent);
      }
    };
  
  public IPackageDeleteObserver2 getBinder() {
    return (IPackageDeleteObserver2)this.mBinder;
  }
  
  public void onPackageDeleted(String paramString1, int paramInt, String paramString2) {}
  
  public void onUserActionRequired(Intent paramIntent) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PackageDeleteObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
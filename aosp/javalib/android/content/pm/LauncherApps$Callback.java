package android.content.pm;

import android.os.Bundle;
import android.os.UserHandle;
import java.util.List;

public abstract class Callback {
  public abstract void onPackageAdded(String paramString, UserHandle paramUserHandle);
  
  public abstract void onPackageChanged(String paramString, UserHandle paramUserHandle);
  
  public abstract void onPackageRemoved(String paramString, UserHandle paramUserHandle);
  
  public abstract void onPackagesAvailable(String[] paramArrayOfString, UserHandle paramUserHandle, boolean paramBoolean);
  
  public void onPackagesSuspended(String[] paramArrayOfString, UserHandle paramUserHandle) {}
  
  @Deprecated
  public void onPackagesSuspended(String[] paramArrayOfString, UserHandle paramUserHandle, Bundle paramBundle) {
    onPackagesSuspended(paramArrayOfString, paramUserHandle);
  }
  
  public abstract void onPackagesUnavailable(String[] paramArrayOfString, UserHandle paramUserHandle, boolean paramBoolean);
  
  public void onPackagesUnsuspended(String[] paramArrayOfString, UserHandle paramUserHandle) {}
  
  public void onShortcutsChanged(String paramString, List<ShortcutInfo> paramList, UserHandle paramUserHandle) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps$Callback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
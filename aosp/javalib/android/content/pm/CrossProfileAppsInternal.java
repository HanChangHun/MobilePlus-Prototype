package android.content.pm;

import android.os.UserHandle;
import java.util.List;

public abstract class CrossProfileAppsInternal {
  public abstract List<UserHandle> getTargetUserProfiles(String paramString, int paramInt);
  
  public abstract boolean verifyPackageHasInteractAcrossProfilePermission(String paramString, int paramInt) throws PackageManager.NameNotFoundException;
  
  public abstract boolean verifyUidHasInteractAcrossProfilePermission(String paramString, int paramInt);
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/CrossProfileAppsInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm;

import android.app.PropertyInvalidatedCache;

class null extends PropertyInvalidatedCache<PackageManager.ApplicationInfoQuery, ApplicationInfo> {
  null(int paramInt, String paramString) {
    super(paramInt, paramString);
  }
  
  protected ApplicationInfo maybeCheckConsistency(PackageManager.ApplicationInfoQuery paramApplicationInfoQuery, ApplicationInfo paramApplicationInfo) {
    return paramApplicationInfo;
  }
  
  protected ApplicationInfo recompute(PackageManager.ApplicationInfoQuery paramApplicationInfoQuery) {
    return PackageManager.access$000(paramApplicationInfoQuery.packageName, paramApplicationInfoQuery.flags, paramApplicationInfoQuery.userId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
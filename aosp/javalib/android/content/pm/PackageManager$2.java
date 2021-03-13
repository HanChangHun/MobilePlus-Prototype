package android.content.pm;

import android.app.PropertyInvalidatedCache;

class null extends PropertyInvalidatedCache<PackageManager.PackageInfoQuery, PackageInfo> {
  null(int paramInt, String paramString) {
    super(paramInt, paramString);
  }
  
  protected PackageInfo maybeCheckConsistency(PackageManager.PackageInfoQuery paramPackageInfoQuery, PackageInfo paramPackageInfo) {
    return paramPackageInfo;
  }
  
  protected PackageInfo recompute(PackageManager.PackageInfoQuery paramPackageInfoQuery) {
    return PackageManager.access$100(paramPackageInfoQuery.packageName, paramPackageInfoQuery.flags, paramPackageInfoQuery.userId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
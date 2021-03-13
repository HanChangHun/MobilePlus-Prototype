package android.app;

import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.ResolveInfo;

final class ResourceName {
  final int iconId;
  
  final String packageName;
  
  ResourceName(ApplicationInfo paramApplicationInfo, int paramInt) {
    this(paramApplicationInfo.packageName, paramInt);
  }
  
  ResourceName(ComponentInfo paramComponentInfo, int paramInt) {
    this(paramComponentInfo.applicationInfo.packageName, paramInt);
  }
  
  ResourceName(ResolveInfo paramResolveInfo, int paramInt) {
    this(paramResolveInfo.activityInfo.applicationInfo.packageName, paramInt);
  }
  
  ResourceName(String paramString, int paramInt) {
    this.packageName = paramString;
    this.iconId = paramInt;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.iconId != ((ResourceName)paramObject).iconId)
      return false; 
    String str = this.packageName;
    if ((str != null) ? !str.equals(((ResourceName)paramObject).packageName) : (((ResourceName)paramObject).packageName != null))
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    return this.packageName.hashCode() * 31 + this.iconId;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{ResourceName ");
    stringBuilder.append(this.packageName);
    stringBuilder.append(" / ");
    stringBuilder.append(this.iconId);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationPackageManager$ResourceName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class ListItem {
  public String className;
  
  public Bundle extras;
  
  public Drawable icon;
  
  public CharSequence label;
  
  public String packageName;
  
  public ResolveInfo resolveInfo;
  
  public ListItem() {}
  
  ListItem(PackageManager paramPackageManager, ResolveInfo paramResolveInfo, LauncherActivity.IconResizer paramIconResizer) {
    ServiceInfo serviceInfo;
    this.resolveInfo = paramResolveInfo;
    this.label = paramResolveInfo.loadLabel(paramPackageManager);
    ActivityInfo activityInfo1 = paramResolveInfo.activityInfo;
    ActivityInfo activityInfo2 = activityInfo1;
    if (activityInfo1 == null)
      serviceInfo = paramResolveInfo.serviceInfo; 
    if (this.label == null && serviceInfo != null)
      this.label = paramResolveInfo.activityInfo.name; 
    if (paramIconResizer != null)
      this.icon = paramIconResizer.createIconThumbnail(paramResolveInfo.loadIcon(paramPackageManager)); 
    this.packageName = ((ComponentInfo)serviceInfo).applicationInfo.packageName;
    this.className = ((ComponentInfo)serviceInfo).name;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LauncherActivity$ListItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
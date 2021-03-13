package android.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;

public class LauncherActivityInfo {
  private static final String TAG = "LauncherActivityInfo";
  
  private ActivityInfo mActivityInfo;
  
  private ComponentName mComponentName;
  
  private final PackageManager mPm;
  
  private UserHandle mUser;
  
  LauncherActivityInfo(Context paramContext) {
    this.mPm = paramContext.getPackageManager();
  }
  
  LauncherActivityInfo(Context paramContext, ActivityInfo paramActivityInfo, UserHandle paramUserHandle) {
    this(paramContext);
    this.mActivityInfo = paramActivityInfo;
    this.mComponentName = new ComponentName(paramActivityInfo.packageName, paramActivityInfo.name);
    this.mUser = paramUserHandle;
  }
  
  public int getApplicationFlags() {
    return this.mActivityInfo.applicationInfo.flags;
  }
  
  public ApplicationInfo getApplicationInfo() {
    return this.mActivityInfo.applicationInfo;
  }
  
  public Drawable getBadgedIcon(int paramInt) {
    Drawable drawable = getIcon(paramInt);
    return this.mPm.getUserBadgedIcon(drawable, this.mUser);
  }
  
  public ComponentName getComponentName() {
    return this.mComponentName;
  }
  
  public long getFirstInstallTime() {
    try {
      return (this.mPm.getPackageInfo(this.mActivityInfo.packageName, 8192)).firstInstallTime;
    } catch (NameNotFoundException nameNotFoundException) {
      return 0L;
    } 
  }
  
  public Drawable getIcon(int paramInt) {
    int i = this.mActivityInfo.getIconResource();
    Drawable drawable1 = null;
    Drawable drawable2 = drawable1;
    if (paramInt != 0) {
      drawable2 = drawable1;
      if (i != 0)
        try {
          drawable2 = this.mPm.getResourcesForApplication(this.mActivityInfo.applicationInfo).getDrawableForDensity(i, paramInt);
        } catch (NameNotFoundException|android.content.res.Resources.NotFoundException nameNotFoundException) {
          drawable2 = drawable1;
        }  
    } 
    drawable1 = drawable2;
    if (drawable2 == null)
      drawable1 = this.mActivityInfo.loadIcon(this.mPm); 
    return drawable1;
  }
  
  public CharSequence getLabel() {
    return this.mActivityInfo.loadLabel(this.mPm);
  }
  
  public String getName() {
    return this.mActivityInfo.name;
  }
  
  public UserHandle getUser() {
    return this.mUser;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherActivityInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.content.pm.IPackageManager;
import android.permission.IPermissionManager;

public class AppGlobals {
  public static float getFloatCoreSetting(String paramString, float paramFloat) {
    ActivityThread activityThread = ActivityThread.currentActivityThread();
    return (activityThread != null) ? activityThread.getFloatCoreSetting(paramString, paramFloat) : paramFloat;
  }
  
  public static Application getInitialApplication() {
    return ActivityThread.currentApplication();
  }
  
  public static String getInitialPackage() {
    return ActivityThread.currentPackageName();
  }
  
  public static int getIntCoreSetting(String paramString, int paramInt) {
    ActivityThread activityThread = ActivityThread.currentActivityThread();
    return (activityThread != null) ? activityThread.getIntCoreSetting(paramString, paramInt) : paramInt;
  }
  
  public static IPackageManager getPackageManager() {
    return ActivityThread.getPackageManager();
  }
  
  public static IPermissionManager getPermissionManager() {
    return ActivityThread.getPermissionManager();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppGlobals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
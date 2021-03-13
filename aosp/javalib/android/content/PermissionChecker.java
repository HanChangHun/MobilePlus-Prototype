package android.content;

import android.app.AppOpsManager;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Binder;
import android.os.Process;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionChecker {
  public static final int PERMISSION_GRANTED = 0;
  
  public static final int PERMISSION_HARD_DENIED = -1;
  
  public static final int PERMISSION_SOFT_DENIED = -2;
  
  public static final int PID_UNKNOWN = -1;
  
  private static int checkAppOpPermission(Context paramContext, String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, String paramString4, boolean paramBoolean) {
    int i;
    String str = AppOpsManager.permissionToOp(paramString1);
    byte b = -1;
    if (str == null || paramString2 == null)
      return -1; 
    AppOpsManager appOpsManager = paramContext.<AppOpsManager>getSystemService(AppOpsManager.class);
    if (paramBoolean) {
      i = appOpsManager.noteProxyOpNoThrow(str, paramString2, paramInt2, paramString3, paramString4);
    } else {
      i = appOpsManager.unsafeCheckOpRawNoThrow(str, paramInt2, paramString2);
    } 
    if (i != 0)
      if (i != 3) {
        if (i != 4)
          return -1; 
      } else {
        if (paramContext.checkPermission(paramString1, paramInt1, paramInt2) == 0) {
          paramInt1 = 0;
        } else {
          paramInt1 = b;
        } 
        return paramInt1;
      }  
    return 0;
  }
  
  public static int checkCallingOrSelfPermissionForDataDelivery(Context paramContext, String paramString1, String paramString2, String paramString3) {
    String str;
    if (Binder.getCallingPid() == Process.myPid()) {
      str = paramContext.getPackageName();
    } else {
      str = null;
    } 
    if (Binder.getCallingPid() == Process.myPid())
      paramString2 = paramContext.getAttributionTag(); 
    return checkPermissionForDataDelivery(paramContext, paramString1, Binder.getCallingPid(), Binder.getCallingUid(), str, paramString2, paramString3);
  }
  
  public static int checkCallingOrSelfPermissionForPreflight(Context paramContext, String paramString) {
    String str;
    if (Binder.getCallingPid() == Process.myPid()) {
      str = paramContext.getPackageName();
    } else {
      str = null;
    } 
    return checkPermissionForPreflight(paramContext, paramString, Binder.getCallingPid(), Binder.getCallingUid(), str);
  }
  
  public static int checkCallingPermissionForDataDelivery(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4) {
    return (Binder.getCallingPid() == Process.myPid()) ? -1 : checkPermissionForDataDelivery(paramContext, paramString1, Binder.getCallingPid(), Binder.getCallingUid(), paramString2, paramString3, paramString4);
  }
  
  public static int checkCallingPermissionForPreflight(Context paramContext, String paramString1, String paramString2) {
    return (Binder.getCallingPid() == Process.myPid()) ? -1 : checkPermissionForPreflight(paramContext, paramString1, Binder.getCallingPid(), Binder.getCallingUid(), paramString2);
  }
  
  static int checkPermissionCommon(Context paramContext, String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, String paramString4, boolean paramBoolean) {
    try {
      PackageManager packageManager = paramContext.getPackageManager();
      try {
        PermissionInfo permissionInfo = packageManager.getPermissionInfo(paramString1, 0);
        if (paramString2 == null) {
          String[] arrayOfString = paramContext.getPackageManager().getPackagesForUid(paramInt2);
          if (arrayOfString != null && arrayOfString.length > 0)
            paramString2 = arrayOfString[0]; 
        } 
        return permissionInfo.isAppOp() ? checkAppOpPermission(paramContext, paramString1, paramInt1, paramInt2, paramString2, paramString3, paramString4, paramBoolean) : (permissionInfo.isRuntime() ? checkRuntimePermission(paramContext, paramString1, paramInt1, paramInt2, paramString2, paramString3, paramString4, paramBoolean) : paramContext.checkPermission(paramString1, paramInt1, paramInt2));
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    return -1;
  }
  
  public static int checkPermissionForDataDelivery(Context paramContext, String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, String paramString4) {
    return checkPermissionCommon(paramContext, paramString1, paramInt1, paramInt2, paramString2, paramString3, paramString4, true);
  }
  
  public static int checkPermissionForPreflight(Context paramContext, String paramString1, int paramInt1, int paramInt2, String paramString2) {
    return checkPermissionCommon(paramContext, paramString1, paramInt1, paramInt2, paramString2, null, null, false);
  }
  
  private static int checkRuntimePermission(Context paramContext, String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, String paramString4, boolean paramBoolean) {
    if (paramContext.checkPermission(paramString1, paramInt1, paramInt2) == -1)
      return -1; 
    paramString1 = AppOpsManager.permissionToOp(paramString1);
    if (paramString1 == null || paramString2 == null)
      return 0; 
    AppOpsManager appOpsManager = paramContext.<AppOpsManager>getSystemService(AppOpsManager.class);
    if (paramBoolean) {
      paramInt1 = appOpsManager.noteProxyOpNoThrow(paramString1, paramString2, paramInt2, paramString3, paramString4);
    } else {
      paramInt1 = appOpsManager.unsafeCheckOpRawNoThrow(paramString1, paramInt2, paramString2);
    } 
    return (paramInt1 != 0 && paramInt1 != 4) ? -2 : 0;
  }
  
  public static int checkSelfPermissionForDataDelivery(Context paramContext, String paramString1, String paramString2) {
    return checkPermissionForDataDelivery(paramContext, paramString1, Process.myPid(), Process.myUid(), paramContext.getPackageName(), paramContext.getAttributionTag(), paramString2);
  }
  
  public static int checkSelfPermissionForPreflight(Context paramContext, String paramString) {
    return checkPermissionForPreflight(paramContext, paramString, Process.myPid(), Process.myUid(), paramContext.getPackageName());
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PermissionResult {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/PermissionChecker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
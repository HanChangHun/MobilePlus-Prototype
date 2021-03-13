package android.content.pm;

import android.app.AppGlobals;
import android.content.Intent;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.ArraySet;
import java.util.ArrayList;
import java.util.List;

public class AppsQueryHelper {
  public static int GET_APPS_WITH_INTERACT_ACROSS_USERS_PERM;
  
  public static int GET_IMES;
  
  public static int GET_NON_LAUNCHABLE_APPS = 1;
  
  public static int GET_REQUIRED_FOR_SYSTEM_USER;
  
  private List<ApplicationInfo> mAllApps;
  
  private final IPackageManager mPackageManager;
  
  static {
    GET_APPS_WITH_INTERACT_ACROSS_USERS_PERM = 2;
    GET_IMES = 4;
    GET_REQUIRED_FOR_SYSTEM_USER = 8;
  }
  
  public AppsQueryHelper() {
    this(AppGlobals.getPackageManager());
  }
  
  public AppsQueryHelper(IPackageManager paramIPackageManager) {
    this.mPackageManager = paramIPackageManager;
  }
  
  protected List<ApplicationInfo> getAllApps(int paramInt) {
    try {
      return this.mPackageManager.getInstalledApplications(8704, paramInt).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  protected List<PackageInfo> getPackagesHoldingPermission(String paramString, int paramInt) {
    try {
      return this.mPackageManager.getPackagesHoldingPermissions(new String[] { paramString }, 0, paramInt).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<String> queryApps(int paramInt, boolean paramBoolean, UserHandle paramUserHandle) {
    ApplicationInfo applicationInfo;
    int k;
    int m;
    int i = GET_NON_LAUNCHABLE_APPS;
    int j = 0;
    if ((paramInt & i) > 0) {
      k = 1;
    } else {
      k = 0;
    } 
    if ((paramInt & GET_APPS_WITH_INTERACT_ACROSS_USERS_PERM) > 0) {
      m = 1;
    } else {
      m = 0;
    } 
    if ((paramInt & GET_IMES) > 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if ((paramInt & GET_REQUIRED_FOR_SYSTEM_USER) > 0)
      j = 1; 
    if (this.mAllApps == null)
      this.mAllApps = getAllApps(paramUserHandle.getIdentifier()); 
    ArrayList<String> arrayList = new ArrayList();
    if (paramInt == 0) {
      j = this.mAllApps.size();
      for (paramInt = 0; paramInt < j; paramInt++) {
        applicationInfo = this.mAllApps.get(paramInt);
        if (!paramBoolean || applicationInfo.isSystemApp())
          arrayList.add(applicationInfo.packageName); 
      } 
      return arrayList;
    } 
    if (k) {
      Intent intent = (new Intent("android.intent.action.MAIN")).addCategory("android.intent.category.LAUNCHER");
      List<ResolveInfo> list = queryIntentActivitiesAsUser(intent, applicationInfo.getIdentifier());
      ArraySet arraySet = new ArraySet();
      k = list.size();
      for (paramInt = 0; paramInt < k; paramInt++)
        arraySet.add(((ResolveInfo)list.get(paramInt)).activityInfo.packageName); 
      k = this.mAllApps.size();
      for (paramInt = 0; paramInt < k; paramInt++) {
        ApplicationInfo applicationInfo1 = this.mAllApps.get(paramInt);
        if (!paramBoolean || applicationInfo1.isSystemApp()) {
          String str = applicationInfo1.packageName;
          if (!arraySet.contains(str))
            arrayList.add(str); 
        } 
      } 
    } 
    if (m) {
      List<PackageInfo> list = getPackagesHoldingPermission("android.permission.INTERACT_ACROSS_USERS", applicationInfo.getIdentifier());
      m = list.size();
      for (paramInt = 0; paramInt < m; paramInt++) {
        PackageInfo packageInfo = list.get(paramInt);
        if ((!paramBoolean || packageInfo.applicationInfo.isSystemApp()) && !arrayList.contains(packageInfo.packageName))
          arrayList.add(packageInfo.packageName); 
      } 
    } 
    if (i != 0) {
      List<ResolveInfo> list = queryIntentServicesAsUser(new Intent("android.view.InputMethod"), applicationInfo.getIdentifier());
      i = list.size();
      for (paramInt = 0; paramInt < i; paramInt++) {
        ServiceInfo serviceInfo = ((ResolveInfo)list.get(paramInt)).serviceInfo;
        if ((!paramBoolean || serviceInfo.applicationInfo.isSystemApp()) && !arrayList.contains(serviceInfo.packageName))
          arrayList.add(serviceInfo.packageName); 
      } 
    } 
    if (j != 0) {
      j = this.mAllApps.size();
      for (paramInt = 0; paramInt < j; paramInt++) {
        applicationInfo = this.mAllApps.get(paramInt);
        if ((!paramBoolean || applicationInfo.isSystemApp()) && applicationInfo.isRequiredForSystemUser())
          arrayList.add(applicationInfo.packageName); 
      } 
    } 
    return arrayList;
  }
  
  protected List<ResolveInfo> queryIntentActivitiesAsUser(Intent paramIntent, int paramInt) {
    try {
      return this.mPackageManager.queryIntentActivities(paramIntent, null, 795136, paramInt).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  protected List<ResolveInfo> queryIntentServicesAsUser(Intent paramIntent, int paramInt) {
    try {
      return this.mPackageManager.queryIntentServices(paramIntent, null, 819328, paramInt).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/AppsQueryHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
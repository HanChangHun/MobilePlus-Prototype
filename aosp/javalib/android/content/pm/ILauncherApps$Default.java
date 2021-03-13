package android.content.pm;

import android.app.IApplicationThread;
import android.content.ComponentName;
import android.content.IntentSender;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;

public class Default implements ILauncherApps {
  public void addOnAppsChangedListener(String paramString, IOnAppsChangedListener paramIOnAppsChangedListener) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void cacheShortcuts(String paramString1, String paramString2, List<String> paramList, UserHandle paramUserHandle, int paramInt) throws RemoteException {}
  
  public ParceledListSlice getAllSessions(String paramString) throws RemoteException {
    return null;
  }
  
  public LauncherApps.AppUsageLimit getAppUsageLimit(String paramString1, String paramString2, UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public ApplicationInfo getApplicationInfo(String paramString1, String paramString2, int paramInt, UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getLauncherActivities(String paramString1, String paramString2, UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getShortcutConfigActivities(String paramString1, String paramString2, UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public IntentSender getShortcutConfigActivityIntent(String paramString, ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public ParcelFileDescriptor getShortcutIconFd(String paramString1, String paramString2, String paramString3, int paramInt) throws RemoteException {
    return null;
  }
  
  public int getShortcutIconResId(String paramString1, String paramString2, String paramString3, int paramInt) throws RemoteException {
    return 0;
  }
  
  public String getShortcutIconUri(String paramString1, String paramString2, String paramString3, int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getShortcuts(String paramString, ShortcutQueryWrapper paramShortcutQueryWrapper, UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public Bundle getSuspendedPackageLauncherExtras(String paramString, UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public boolean hasShortcutHostPermission(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isActivityEnabled(String paramString, ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    return false;
  }
  
  public boolean isPackageEnabled(String paramString1, String paramString2, UserHandle paramUserHandle) throws RemoteException {
    return false;
  }
  
  public void pinShortcuts(String paramString1, String paramString2, List<String> paramList, UserHandle paramUserHandle) throws RemoteException {}
  
  public void registerPackageInstallerCallback(String paramString, IPackageInstallerCallback paramIPackageInstallerCallback) throws RemoteException {}
  
  public void registerShortcutChangeCallback(String paramString, ShortcutQueryWrapper paramShortcutQueryWrapper, IShortcutChangeCallback paramIShortcutChangeCallback) throws RemoteException {}
  
  public void removeOnAppsChangedListener(IOnAppsChangedListener paramIOnAppsChangedListener) throws RemoteException {}
  
  public ActivityInfo resolveActivity(String paramString, ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public boolean shouldHideFromSuggestions(String paramString, UserHandle paramUserHandle) throws RemoteException {
    return false;
  }
  
  public void showAppDetailsAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, ComponentName paramComponentName, Rect paramRect, Bundle paramBundle, UserHandle paramUserHandle) throws RemoteException {}
  
  public void startActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, ComponentName paramComponentName, Rect paramRect, Bundle paramBundle, UserHandle paramUserHandle) throws RemoteException {}
  
  public void startSessionDetailsActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, PackageInstaller.SessionInfo paramSessionInfo, Rect paramRect, Bundle paramBundle, UserHandle paramUserHandle) throws RemoteException {}
  
  public boolean startShortcut(String paramString1, String paramString2, String paramString3, String paramString4, Rect paramRect, Bundle paramBundle, int paramInt) throws RemoteException {
    return false;
  }
  
  public void uncacheShortcuts(String paramString1, String paramString2, List<String> paramList, UserHandle paramUserHandle, int paramInt) throws RemoteException {}
  
  public void unregisterShortcutChangeCallback(String paramString, IShortcutChangeCallback paramIShortcutChangeCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ILauncherApps$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
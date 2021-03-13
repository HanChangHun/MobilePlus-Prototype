package android.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Singleton;
import java.util.Iterator;
import java.util.List;

public class ActivityTaskManager {
  public static final String EXTRA_IGNORE_TARGET_SECURITY = "android.app.extra.EXTRA_IGNORE_TARGET_SECURITY";
  
  public static final String EXTRA_OPTIONS = "android.app.extra.OPTIONS";
  
  public static final String EXTRA_PERMISSION_TOKEN = "android.app.extra.PERMISSION_TOKEN";
  
  private static final Singleton<IActivityTaskManager> IActivityTaskManagerSingleton;
  
  public static final int INVALID_STACK_ID = -1;
  
  public static final int INVALID_TASK_ID = -1;
  
  public static final int RESIZE_MODE_FORCED = 2;
  
  public static final int RESIZE_MODE_PRESERVE_WINDOW = 1;
  
  public static final int RESIZE_MODE_SYSTEM = 0;
  
  public static final int RESIZE_MODE_SYSTEM_SCREEN_ROTATION = 1;
  
  public static final int RESIZE_MODE_USER = 1;
  
  public static final int RESIZE_MODE_USER_FORCED = 3;
  
  public static final int SPLIT_SCREEN_CREATE_MODE_BOTTOM_OR_RIGHT = 1;
  
  public static final int SPLIT_SCREEN_CREATE_MODE_TOP_OR_LEFT = 0;
  
  private static int sMaxRecentTasks = -1;
  
  static {
    IActivityTaskManagerSingleton = new Singleton<IActivityTaskManager>() {
        protected IActivityTaskManager create() {
          return IActivityTaskManager.Stub.asInterface(ServiceManager.getService("activity_task"));
        }
      };
  }
  
  ActivityTaskManager(Context paramContext, Handler paramHandler) {}
  
  public static boolean currentUiModeSupportsErrorDialogs(Context paramContext) {
    return currentUiModeSupportsErrorDialogs(paramContext.getResources().getConfiguration());
  }
  
  public static boolean currentUiModeSupportsErrorDialogs(Configuration paramConfiguration) {
    boolean bool;
    int i = paramConfiguration.uiMode & 0xF;
    if (i != 3 && (i != 6 || !Build.IS_USER) && i != 4 && i != 7) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static int getDefaultAppRecentsLimitStatic() {
    return getMaxRecentTasksStatic() / 6;
  }
  
  public static int getMaxAppRecentsLimitStatic() {
    return getMaxRecentTasksStatic() / 2;
  }
  
  public static int getMaxRecentTasksStatic() {
    int i = sMaxRecentTasks;
    if (i < 0) {
      if (ActivityManager.isLowRamDeviceStatic()) {
        i = 36;
      } else {
        i = 48;
      } 
      sMaxRecentTasks = i;
      return i;
    } 
    return i;
  }
  
  public static IActivityTaskManager getService() {
    return (IActivityTaskManager)IActivityTaskManagerSingleton.get();
  }
  
  public static boolean supportsMultiWindow(Context paramContext) {
    boolean bool = paramContext.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    if ((!ActivityManager.isLowRamDeviceStatic() || bool) && Resources.getSystem().getBoolean(17891551)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean supportsSplitScreenMultiWindow(Context paramContext) {
    boolean bool;
    if (supportsMultiWindow(paramContext) && Resources.getSystem().getBoolean(17891553)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void clearLaunchParamsForPackages(List<String> paramList) {
    try {
      getService().clearLaunchParamsForPackages(paramList);
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String listAllStacks() {
    try {
      List<ActivityManager.StackInfo> list = getService().getAllStackInfos();
      StringBuilder stringBuilder = new StringBuilder();
      if (list != null) {
        Iterator<ActivityManager.StackInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
          stringBuilder.append(iterator.next());
          stringBuilder.append("\n");
        } 
      } 
      return stringBuilder.toString();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void moveTaskToStack(int paramInt1, int paramInt2, boolean paramBoolean) {
    try {
      getService().moveTaskToStack(paramInt1, paramInt2, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean moveTopActivityToPinnedStack(int paramInt, Rect paramRect) {
    try {
      return getService().moveTopActivityToPinnedStack(paramInt, paramRect);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeAllVisibleRecentTasks() {
    try {
      getService().removeAllVisibleRecentTasks();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeStacksInWindowingModes(int[] paramArrayOfint) throws SecurityException {
    try {
      getService().removeStacksInWindowingModes(paramArrayOfint);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeStacksWithActivityTypes(int[] paramArrayOfint) throws SecurityException {
    try {
      getService().removeStacksWithActivityTypes(paramArrayOfint);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void requestPictureInPictureMode(IBinder paramIBinder) {
    try {
      getService().requestPictureInPictureMode(paramIBinder);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void resizeDockedStack(Rect paramRect1, Rect paramRect2) {
    try {
      getService().resizeDockedStack(paramRect1, paramRect2, null, null, null);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void resizeTask(int paramInt, Rect paramRect) {
    try {
      getService().resizeTask(paramInt, paramRect, 0);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setDisplayToSingleTaskInstance(int paramInt) {
    try {
      getService().setDisplayToSingleTaskInstance(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean setTaskWindowingMode(int paramInt1, int paramInt2, boolean paramBoolean) throws SecurityException {
    try {
      return getService().setTaskWindowingMode(paramInt1, paramInt2, paramBoolean);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean setTaskWindowingModeSplitScreenPrimary(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, Rect paramRect, boolean paramBoolean3) throws SecurityException {
    try {
      return getService().setTaskWindowingModeSplitScreenPrimary(paramInt1, paramBoolean1);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void startSystemLockTaskMode(int paramInt) {
    try {
      getService().startSystemLockTaskMode(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void stopSystemLockTaskMode() {
    try {
      getService().stopSystemLockTaskMode();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityTaskManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
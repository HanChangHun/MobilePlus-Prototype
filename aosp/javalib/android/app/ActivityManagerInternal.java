package android.app;

import android.content.ComponentName;
import android.content.IIntentReceiver;
import android.content.IIntentSender;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ActivityPresentationInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.UserInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.TransactionTooLargeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ActivityManagerInternal {
  public static final int ALLOW_ALL_PROFILE_PERMISSIONS_IN_PROFILE = 3;
  
  public static final int ALLOW_FULL_ONLY = 2;
  
  public static final int ALLOW_NON_FULL = 0;
  
  public static final int ALLOW_NON_FULL_IN_PROFILE = 1;
  
  public abstract void addPendingTopUid(int paramInt1, int paramInt2);
  
  public abstract void broadcastCloseSystemDialogs(String paramString);
  
  public abstract void broadcastGlobalConfigurationChanged(int paramInt, boolean paramBoolean);
  
  public abstract int broadcastIntent(Intent paramIntent, IIntentReceiver paramIIntentReceiver, String[] paramArrayOfString, boolean paramBoolean, int paramInt, int[] paramArrayOfint);
  
  public abstract int broadcastIntentInPackage(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, Intent paramIntent, String paramString3, IIntentReceiver paramIIntentReceiver, int paramInt4, String paramString4, Bundle paramBundle1, String paramString5, Bundle paramBundle2, boolean paramBoolean1, boolean paramBoolean2, int paramInt5, boolean paramBoolean3);
  
  public abstract boolean canStartMoreUsers();
  
  public abstract String checkContentProviderAccess(String paramString, int paramInt);
  
  public abstract int checkContentProviderUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void cleanUpServices(int paramInt, ComponentName paramComponentName, Intent paramIntent);
  
  public abstract void clearPendingBackup(int paramInt);
  
  public abstract void clearPendingIntentAllowBgActivityStarts(IIntentSender paramIIntentSender, IBinder paramIBinder);
  
  public abstract void deletePendingTopUid(int paramInt);
  
  public abstract void disconnectActivityFromServices(Object paramObject);
  
  public abstract void enforceCallingPermission(String paramString1, String paramString2);
  
  public abstract void ensureBootCompleted();
  
  public abstract void ensureNotSpecialUser(int paramInt);
  
  public abstract void finishBooting();
  
  public abstract void finishUserSwitch(Object paramObject);
  
  public abstract ActivityInfo getActivityInfoForUser(ActivityInfo paramActivityInfo, int paramInt);
  
  public abstract ActivityPresentationInfo getActivityPresentationInfo(IBinder paramIBinder);
  
  public abstract int[] getCurrentProfileIds();
  
  public abstract UserInfo getCurrentUser();
  
  public abstract int getCurrentUserId();
  
  public abstract int getMaxRunningUsers();
  
  public abstract List<ProcessMemoryState> getMemoryStateForProcesses();
  
  public abstract Map<Integer, String> getProcessesWithPendingBindMounts(int paramInt);
  
  public abstract int getStorageMountMode(int paramInt1, int paramInt2);
  
  public abstract int getTaskIdForActivity(IBinder paramIBinder, boolean paramBoolean);
  
  public abstract int getUidProcessState(int paramInt);
  
  public abstract int handleIncomingUser(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, String paramString1, String paramString2);
  
  public abstract boolean hasRunningActivity(int paramInt, String paramString);
  
  public abstract boolean hasRunningForegroundService(int paramInt1, int paramInt2);
  
  public abstract boolean hasStartedUserState(int paramInt);
  
  public abstract long inputDispatchingTimedOut(int paramInt, boolean paramBoolean, String paramString);
  
  public abstract boolean inputDispatchingTimedOut(Object paramObject1, String paramString1, ApplicationInfo paramApplicationInfo, String paramString2, Object paramObject2, boolean paramBoolean, String paramString3);
  
  public abstract boolean isActivityStartsLoggingEnabled();
  
  public abstract boolean isAppBad(ApplicationInfo paramApplicationInfo);
  
  public abstract boolean isAppForeground(int paramInt);
  
  public abstract boolean isBackgroundActivityStartsEnabled();
  
  public abstract boolean isBooted();
  
  public abstract boolean isBooting();
  
  public abstract boolean isCurrentProfile(int paramInt);
  
  public abstract boolean isDeviceOwner(int paramInt);
  
  public abstract boolean isPendingTopUid(int paramInt);
  
  public abstract boolean isRuntimeRestarted();
  
  public abstract boolean isSystemReady();
  
  public abstract boolean isUidActive(int paramInt);
  
  public abstract boolean isUidCurrentlyInstrumented(int paramInt);
  
  public abstract boolean isUserRunning(int paramInt1, int paramInt2);
  
  public abstract void killAllBackgroundProcessesExcept(int paramInt1, int paramInt2);
  
  public abstract void killForegroundAppsForUser(int paramInt);
  
  public abstract void killProcess(String paramString1, int paramInt, String paramString2);
  
  public abstract void killProcessesForRemovedTask(ArrayList<Object> paramArrayList);
  
  public abstract void monitor();
  
  public abstract void notifyNetworkPolicyRulesUpdated(int paramInt, long paramLong);
  
  public abstract void onUserRemoved(int paramInt);
  
  public abstract void onWakefulnessChanged(int paramInt);
  
  public abstract void prepareForPossibleShutdown();
  
  public abstract void registerProcessObserver(IProcessObserver paramIProcessObserver);
  
  public abstract void reportCurKeyguardUsageEvent(boolean paramBoolean);
  
  public abstract void scheduleAppGcs();
  
  public abstract void sendForegroundProfileChanged(int paramInt);
  
  public abstract void setBooted(boolean paramBoolean);
  
  public abstract void setBooting(boolean paramBoolean);
  
  public abstract void setDebugFlagsForStartingActivity(ActivityInfo paramActivityInfo, int paramInt, ProfilerInfo paramProfilerInfo, Object paramObject);
  
  public abstract void setDeviceIdleWhitelist(int[] paramArrayOfint1, int[] paramArrayOfint2);
  
  public abstract void setDeviceOwnerUid(int paramInt);
  
  public abstract void setHasOverlayUi(int paramInt, boolean paramBoolean);
  
  public abstract void setPendingIntentAllowBgActivityStarts(IIntentSender paramIIntentSender, IBinder paramIBinder, int paramInt);
  
  public abstract void setPendingIntentWhitelistDuration(IIntentSender paramIIntentSender, IBinder paramIBinder, long paramLong);
  
  public abstract void setSwitchingFromSystemUserMessage(String paramString);
  
  public abstract void setSwitchingToSystemUserMessage(String paramString);
  
  public abstract boolean shouldConfirmCredentials(int paramInt);
  
  public abstract boolean startIsolatedProcess(String paramString1, String[] paramArrayOfString, String paramString2, String paramString3, int paramInt, Runnable paramRunnable);
  
  public abstract void startProcess(String paramString1, ApplicationInfo paramApplicationInfo, boolean paramBoolean1, boolean paramBoolean2, String paramString2, ComponentName paramComponentName);
  
  public abstract ComponentName startServiceInPackage(int paramInt1, Intent paramIntent, String paramString1, boolean paramBoolean1, String paramString2, String paramString3, int paramInt2, boolean paramBoolean2) throws TransactionTooLargeException;
  
  public abstract void tempWhitelistForPendingIntent(int paramInt1, int paramInt2, int paramInt3, long paramLong, String paramString);
  
  public abstract void trimApplications();
  
  public abstract void unregisterProcessObserver(IProcessObserver paramIProcessObserver);
  
  public abstract void updateActivityUsageStats(ComponentName paramComponentName1, int paramInt1, int paramInt2, IBinder paramIBinder, ComponentName paramComponentName2);
  
  public abstract void updateBatteryStats(ComponentName paramComponentName, int paramInt1, int paramInt2, boolean paramBoolean);
  
  public abstract void updateCpuStats();
  
  public abstract void updateDeviceIdleTempWhitelist(int[] paramArrayOfint, int paramInt, boolean paramBoolean);
  
  public abstract void updateForegroundTimeIfOnBattery(String paramString, int paramInt, long paramLong);
  
  public abstract void updateOomAdj();
  
  public abstract void updateOomLevelsForDisplay(int paramInt);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManagerInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.content.ComponentName;
import android.content.IIntentReceiver;
import android.content.IIntentSender;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.LocusId;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.ParceledListSlice;
import android.content.pm.UserInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.IProgressListener;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.WorkSource;
import android.view.IRecentsAnimationRunner;
import com.android.internal.os.IResultReceiver;
import java.util.List;

public class Default implements IActivityManager {
  public void addInstrumentationResults(IApplicationThread paramIApplicationThread, Bundle paramBundle) throws RemoteException {}
  
  public void addPackageDependency(String paramString) throws RemoteException {}
  
  public void appNotResponding(String paramString) throws RemoteException {}
  
  public void appNotRespondingViaProvider(IBinder paramIBinder) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void attachApplication(IApplicationThread paramIApplicationThread, long paramLong) throws RemoteException {}
  
  public void backgroundWhitelistUid(int paramInt) throws RemoteException {}
  
  public void backupAgentCreated(String paramString, IBinder paramIBinder, int paramInt) throws RemoteException {}
  
  public boolean bindBackupAgent(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public int bindIsolatedService(IApplicationThread paramIApplicationThread, IBinder paramIBinder, Intent paramIntent, String paramString1, IServiceConnection paramIServiceConnection, int paramInt1, String paramString2, String paramString3, int paramInt2) throws RemoteException {
    return 0;
  }
  
  public int bindService(IApplicationThread paramIApplicationThread, IBinder paramIBinder, Intent paramIntent, String paramString1, IServiceConnection paramIServiceConnection, int paramInt1, String paramString2, int paramInt2) throws RemoteException {
    return 0;
  }
  
  public void bootAnimationComplete() throws RemoteException {}
  
  public int broadcastIntent(IApplicationThread paramIApplicationThread, Intent paramIntent, String paramString1, IIntentReceiver paramIIntentReceiver, int paramInt1, String paramString2, Bundle paramBundle1, String[] paramArrayOfString, int paramInt2, Bundle paramBundle2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3) throws RemoteException {
    return 0;
  }
  
  public int broadcastIntentWithFeature(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IIntentReceiver paramIIntentReceiver, int paramInt1, String paramString3, Bundle paramBundle1, String[] paramArrayOfString, int paramInt2, Bundle paramBundle2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3) throws RemoteException {
    return 0;
  }
  
  public void cancelIntentSender(IIntentSender paramIIntentSender) throws RemoteException {}
  
  public void cancelRecentsAnimation(boolean paramBoolean) throws RemoteException {}
  
  public void cancelTaskWindowTransition(int paramInt) throws RemoteException {}
  
  public int checkPermission(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return 0;
  }
  
  public int checkPermissionWithToken(String paramString, int paramInt1, int paramInt2, IBinder paramIBinder) throws RemoteException {
    return 0;
  }
  
  public int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3, int paramInt4, IBinder paramIBinder) throws RemoteException {
    return 0;
  }
  
  public boolean clearApplicationUserData(String paramString, boolean paramBoolean, IPackageDataObserver paramIPackageDataObserver, int paramInt) throws RemoteException {
    return false;
  }
  
  public void closeSystemDialogs(String paramString) throws RemoteException {}
  
  public void crashApplication(int paramInt1, int paramInt2, String paramString1, int paramInt3, String paramString2, boolean paramBoolean) throws RemoteException {}
  
  public boolean dumpHeap(String paramString1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2, ParcelFileDescriptor paramParcelFileDescriptor, RemoteCallback paramRemoteCallback) throws RemoteException {
    return false;
  }
  
  public void dumpHeapFinished(String paramString) throws RemoteException {}
  
  public boolean enableAppFreezer(boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public void enterSafeMode() throws RemoteException {}
  
  public boolean finishActivity(IBinder paramIBinder, int paramInt1, Intent paramIntent, int paramInt2) throws RemoteException {
    return false;
  }
  
  public void finishHeavyWeightApp() throws RemoteException {}
  
  public void finishInstrumentation(IApplicationThread paramIApplicationThread, int paramInt, Bundle paramBundle) throws RemoteException {}
  
  public void finishReceiver(IBinder paramIBinder, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean, int paramInt2) throws RemoteException {}
  
  public void forceStopPackage(String paramString, int paramInt) throws RemoteException {}
  
  public List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException {
    return null;
  }
  
  public List<String> getBugreportWhitelistedPackages() throws RemoteException {
    return null;
  }
  
  public Configuration getConfiguration() throws RemoteException {
    return null;
  }
  
  public ContentProviderHolder getContentProvider(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, int paramInt, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public ContentProviderHolder getContentProviderExternal(String paramString1, int paramInt, IBinder paramIBinder, String paramString2) throws RemoteException {
    return null;
  }
  
  public UserInfo getCurrentUser() throws RemoteException {
    return null;
  }
  
  public ActivityManager.StackInfo getFocusedStackInfo() throws RemoteException {
    return null;
  }
  
  public int getForegroundServiceType(ComponentName paramComponentName, IBinder paramIBinder) throws RemoteException {
    return 0;
  }
  
  public ParceledListSlice<ApplicationExitInfo> getHistoricalProcessExitReasons(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    return null;
  }
  
  public Intent getIntentForIntentSender(IIntentSender paramIIntentSender) throws RemoteException {
    return null;
  }
  
  public IIntentSender getIntentSender(int paramInt1, String paramString1, IBinder paramIBinder, String paramString2, int paramInt2, Intent[] paramArrayOfIntent, String[] paramArrayOfString, int paramInt3, Bundle paramBundle, int paramInt4) throws RemoteException {
    return null;
  }
  
  public IIntentSender getIntentSenderWithFeature(int paramInt1, String paramString1, String paramString2, IBinder paramIBinder, String paramString3, int paramInt2, Intent[] paramArrayOfIntent, String[] paramArrayOfString, int paramInt3, Bundle paramBundle, int paramInt4) throws RemoteException {
    return null;
  }
  
  public String getLaunchedFromPackage(IBinder paramIBinder) throws RemoteException {
    return null;
  }
  
  public int getLaunchedFromUid(IBinder paramIBinder) throws RemoteException {
    return 0;
  }
  
  public ParcelFileDescriptor getLifeMonitor() throws RemoteException {
    return null;
  }
  
  public int getLockTaskModeState() throws RemoteException {
    return 0;
  }
  
  public void getMemoryInfo(ActivityManager.MemoryInfo paramMemoryInfo) throws RemoteException {}
  
  public int getMemoryTrimLevel() throws RemoteException {
    return 0;
  }
  
  public void getMyMemoryState(ActivityManager.RunningAppProcessInfo paramRunningAppProcessInfo) throws RemoteException {}
  
  public String getPackageForIntentSender(IIntentSender paramIIntentSender) throws RemoteException {
    return null;
  }
  
  public int getPackageProcessState(String paramString1, String paramString2) throws RemoteException {
    return 0;
  }
  
  public int getProcessLimit() throws RemoteException {
    return 0;
  }
  
  public Debug.MemoryInfo[] getProcessMemoryInfo(int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public long[] getProcessPss(int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public List<ActivityManager.ProcessErrorStateInfo> getProcessesInErrorState() throws RemoteException {
    return null;
  }
  
  public String getProviderMimeType(Uri paramUri, int paramInt) throws RemoteException {
    return null;
  }
  
  public void getProviderMimeTypeAsync(Uri paramUri, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public ParceledListSlice getRecentTasks(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    return null;
  }
  
  public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() throws RemoteException {
    return null;
  }
  
  public List<ApplicationInfo> getRunningExternalApplications() throws RemoteException {
    return null;
  }
  
  public PendingIntent getRunningServiceControlPanel(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public int[] getRunningUserIds() throws RemoteException {
    return null;
  }
  
  public List<ActivityManager.RunningServiceInfo> getServices(int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public String getTagForIntentSender(IIntentSender paramIIntentSender, String paramString) throws RemoteException {
    return null;
  }
  
  public Rect getTaskBounds(int paramInt) throws RemoteException {
    return null;
  }
  
  public int getTaskForActivity(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public ActivityManager.TaskSnapshot getTaskSnapshot(int paramInt, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public List<ActivityManager.RunningTaskInfo> getTasks(int paramInt) throws RemoteException {
    return null;
  }
  
  public int getUidForIntentSender(IIntentSender paramIIntentSender) throws RemoteException {
    return 0;
  }
  
  public int getUidProcessState(int paramInt, String paramString) throws RemoteException {
    return 0;
  }
  
  public void grantUriPermission(IApplicationThread paramIApplicationThread, String paramString, Uri paramUri, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void handleApplicationCrash(IBinder paramIBinder, ApplicationErrorReport.ParcelableCrashInfo paramParcelableCrashInfo) throws RemoteException {}
  
  public void handleApplicationStrictModeViolation(IBinder paramIBinder, int paramInt, StrictMode.ViolationInfo paramViolationInfo) throws RemoteException {}
  
  public boolean handleApplicationWtf(IBinder paramIBinder, String paramString, boolean paramBoolean, ApplicationErrorReport.ParcelableCrashInfo paramParcelableCrashInfo, int paramInt) throws RemoteException {
    return false;
  }
  
  public int handleIncomingUser(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2) throws RemoteException {
    return 0;
  }
  
  public void hang(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {}
  
  public boolean isAppFreezerSupported() throws RemoteException {
    return false;
  }
  
  public boolean isAppStartModeDisabled(int paramInt, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isBackgroundRestricted(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isInLockTaskMode() throws RemoteException {
    return false;
  }
  
  public boolean isIntentSenderABroadcast(IIntentSender paramIIntentSender) throws RemoteException {
    return false;
  }
  
  public boolean isIntentSenderAForegroundService(IIntentSender paramIIntentSender) throws RemoteException {
    return false;
  }
  
  public boolean isIntentSenderAnActivity(IIntentSender paramIIntentSender) throws RemoteException {
    return false;
  }
  
  public boolean isIntentSenderTargetedToPackage(IIntentSender paramIIntentSender) throws RemoteException {
    return false;
  }
  
  public boolean isTopActivityImmersive() throws RemoteException {
    return false;
  }
  
  public boolean isTopOfTask(IBinder paramIBinder) throws RemoteException {
    return false;
  }
  
  public boolean isUidActive(int paramInt, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isUserAMonkey() throws RemoteException {
    return false;
  }
  
  public boolean isUserRunning(int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean isVrModePackageEnabled(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public void killAllBackgroundProcesses() throws RemoteException {}
  
  public void killApplication(String paramString1, int paramInt1, int paramInt2, String paramString2) throws RemoteException {}
  
  public void killApplicationProcess(String paramString, int paramInt) throws RemoteException {}
  
  public void killBackgroundProcesses(String paramString, int paramInt) throws RemoteException {}
  
  public void killPackageDependents(String paramString, int paramInt) throws RemoteException {}
  
  public boolean killPids(int[] paramArrayOfint, String paramString, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean killProcessesBelowForeground(String paramString) throws RemoteException {
    return false;
  }
  
  public void killProcessesWhenImperceptible(int[] paramArrayOfint, String paramString) throws RemoteException {}
  
  public void killUid(int paramInt1, int paramInt2, String paramString) throws RemoteException {}
  
  public void killUidForPermissionChange(int paramInt1, int paramInt2, String paramString) throws RemoteException {}
  
  public boolean launchBugReportHandlerApp() throws RemoteException {
    return false;
  }
  
  public void makePackageIdle(String paramString, int paramInt) throws RemoteException {}
  
  public boolean moveActivityTaskToBack(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public void moveTaskToFront(IApplicationThread paramIApplicationThread, String paramString, int paramInt1, int paramInt2, Bundle paramBundle) throws RemoteException {}
  
  public void moveTaskToStack(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {}
  
  public boolean moveTopActivityToPinnedStack(int paramInt, Rect paramRect) throws RemoteException {
    return false;
  }
  
  public void noteAlarmFinish(IIntentSender paramIIntentSender, WorkSource paramWorkSource, int paramInt, String paramString) throws RemoteException {}
  
  public void noteAlarmStart(IIntentSender paramIIntentSender, WorkSource paramWorkSource, int paramInt, String paramString) throws RemoteException {}
  
  public void noteWakeupAlarm(IIntentSender paramIIntentSender, WorkSource paramWorkSource, int paramInt, String paramString1, String paramString2) throws RemoteException {}
  
  public void notifyCleartextNetwork(int paramInt, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void notifyLockedProfile(int paramInt) throws RemoteException {}
  
  public ParcelFileDescriptor openContentUri(String paramString) throws RemoteException {
    return null;
  }
  
  public IBinder peekService(Intent paramIntent, String paramString1, String paramString2) throws RemoteException {
    return null;
  }
  
  public void performIdleMaintenance() throws RemoteException {}
  
  public void positionTaskInStack(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public boolean profileControl(String paramString, int paramInt1, boolean paramBoolean, ProfilerInfo paramProfilerInfo, int paramInt2) throws RemoteException {
    return false;
  }
  
  public void publishContentProviders(IApplicationThread paramIApplicationThread, List<ContentProviderHolder> paramList) throws RemoteException {}
  
  public void publishService(IBinder paramIBinder1, Intent paramIntent, IBinder paramIBinder2) throws RemoteException {}
  
  public boolean refContentProvider(IBinder paramIBinder, int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public void registerIntentSenderCancelListener(IIntentSender paramIIntentSender, IResultReceiver paramIResultReceiver) throws RemoteException {}
  
  public void registerProcessObserver(IProcessObserver paramIProcessObserver) throws RemoteException {}
  
  public Intent registerReceiver(IApplicationThread paramIApplicationThread, String paramString1, IIntentReceiver paramIIntentReceiver, IntentFilter paramIntentFilter, String paramString2, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public Intent registerReceiverWithFeature(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, IIntentReceiver paramIIntentReceiver, IntentFilter paramIntentFilter, String paramString3, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public void registerTaskStackListener(ITaskStackListener paramITaskStackListener) throws RemoteException {}
  
  public void registerUidObserver(IUidObserver paramIUidObserver, int paramInt1, int paramInt2, String paramString) throws RemoteException {}
  
  public void registerUserSwitchObserver(IUserSwitchObserver paramIUserSwitchObserver, String paramString) throws RemoteException {}
  
  public void removeContentProvider(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {}
  
  public void removeContentProviderExternal(String paramString, IBinder paramIBinder) throws RemoteException {}
  
  public void removeContentProviderExternalAsUser(String paramString, IBinder paramIBinder, int paramInt) throws RemoteException {}
  
  public void removeStack(int paramInt) throws RemoteException {}
  
  public boolean removeTask(int paramInt) throws RemoteException {
    return false;
  }
  
  public void requestBugReport(int paramInt) throws RemoteException {}
  
  public void requestBugReportWithDescription(String paramString1, String paramString2, int paramInt) throws RemoteException {}
  
  public void requestFullBugReport() throws RemoteException {}
  
  public void requestInteractiveBugReport() throws RemoteException {}
  
  public void requestInteractiveBugReportWithDescription(String paramString1, String paramString2) throws RemoteException {}
  
  public void requestRemoteBugReport() throws RemoteException {}
  
  public void requestSystemServerHeapDump() throws RemoteException {}
  
  public void requestTelephonyBugReport(String paramString1, String paramString2) throws RemoteException {}
  
  public void requestWifiBugReport(String paramString1, String paramString2) throws RemoteException {}
  
  public void resizeTask(int paramInt1, Rect paramRect, int paramInt2) throws RemoteException {}
  
  public void restart() throws RemoteException {}
  
  public int restartUserInBackground(int paramInt) throws RemoteException {
    return 0;
  }
  
  public void resumeAppSwitches() throws RemoteException {}
  
  public void revokeUriPermission(IApplicationThread paramIApplicationThread, String paramString, Uri paramUri, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void scheduleApplicationInfoChanged(List<String> paramList, int paramInt) throws RemoteException {}
  
  public void sendIdleJobTrigger() throws RemoteException {}
  
  public int sendIntentSender(IIntentSender paramIIntentSender, IBinder paramIBinder, int paramInt, Intent paramIntent, String paramString1, IIntentReceiver paramIIntentReceiver, String paramString2, Bundle paramBundle) throws RemoteException {
    return 0;
  }
  
  public void serviceDoneExecuting(IBinder paramIBinder, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void setActivityController(IActivityController paramIActivityController, boolean paramBoolean) throws RemoteException {}
  
  public void setActivityLocusContext(ComponentName paramComponentName, LocusId paramLocusId, IBinder paramIBinder) throws RemoteException {}
  
  public void setAgentApp(String paramString1, String paramString2) throws RemoteException {}
  
  public void setAlwaysFinish(boolean paramBoolean) throws RemoteException {}
  
  public void setDebugApp(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {}
  
  public void setDumpHeapDebugLimit(String paramString1, int paramInt, long paramLong, String paramString2) throws RemoteException {}
  
  public void setFocusedStack(int paramInt) throws RemoteException {}
  
  public void setHasTopUi(boolean paramBoolean) throws RemoteException {}
  
  public void setPackageScreenCompatMode(String paramString, int paramInt) throws RemoteException {}
  
  public void setPersistentVrThread(int paramInt) throws RemoteException {}
  
  public void setProcessImportant(IBinder paramIBinder, int paramInt, boolean paramBoolean, String paramString) throws RemoteException {}
  
  public void setProcessLimit(int paramInt) throws RemoteException {}
  
  public boolean setProcessMemoryTrimLevel(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public void setProcessStateSummary(byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void setRenderThread(int paramInt) throws RemoteException {}
  
  public void setRequestedOrientation(IBinder paramIBinder, int paramInt) throws RemoteException {}
  
  public void setServiceForeground(ComponentName paramComponentName, IBinder paramIBinder, int paramInt1, Notification paramNotification, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void setTaskResizeable(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void setUserIsMonkey(boolean paramBoolean) throws RemoteException {}
  
  public void showBootMessage(CharSequence paramCharSequence, boolean paramBoolean) throws RemoteException {}
  
  public void showWaitingForDebugger(IApplicationThread paramIApplicationThread, boolean paramBoolean) throws RemoteException {}
  
  public boolean shutdown(int paramInt) throws RemoteException {
    return false;
  }
  
  public void signalPersistentProcesses(int paramInt) throws RemoteException {}
  
  public int startActivity(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IBinder paramIBinder, String paramString3, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle) throws RemoteException {
    return 0;
  }
  
  public int startActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IBinder paramIBinder, String paramString3, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException {
    return 0;
  }
  
  public int startActivityAsUserWithFeature(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException {
    return 0;
  }
  
  public int startActivityFromRecents(int paramInt, Bundle paramBundle) throws RemoteException {
    return 0;
  }
  
  public int startActivityWithFeature(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle) throws RemoteException {
    return 0;
  }
  
  public boolean startBinderTracking() throws RemoteException {
    return false;
  }
  
  public void startConfirmDeviceCredentialIntent(Intent paramIntent, Bundle paramBundle) throws RemoteException {}
  
  public void startDelegateShellPermissionIdentity(int paramInt, String[] paramArrayOfString) throws RemoteException {}
  
  public boolean startInstrumentation(ComponentName paramComponentName, String paramString1, int paramInt1, Bundle paramBundle, IInstrumentationWatcher paramIInstrumentationWatcher, IUiAutomationConnection paramIUiAutomationConnection, int paramInt2, String paramString2) throws RemoteException {
    return false;
  }
  
  public void startRecentsActivity(Intent paramIntent, IAssistDataReceiver paramIAssistDataReceiver, IRecentsAnimationRunner paramIRecentsAnimationRunner) throws RemoteException {}
  
  public ComponentName startService(IApplicationThread paramIApplicationThread, Intent paramIntent, String paramString1, boolean paramBoolean, String paramString2, String paramString3, int paramInt) throws RemoteException {
    return null;
  }
  
  public void startSystemLockTaskMode(int paramInt) throws RemoteException {}
  
  public boolean startUserInBackground(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean startUserInBackgroundWithListener(int paramInt, IProgressListener paramIProgressListener) throws RemoteException {
    return false;
  }
  
  public boolean startUserInForegroundWithListener(int paramInt, IProgressListener paramIProgressListener) throws RemoteException {
    return false;
  }
  
  public void stopAppSwitches() throws RemoteException {}
  
  public boolean stopBinderTrackingAndDump(ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {
    return false;
  }
  
  public void stopDelegateShellPermissionIdentity() throws RemoteException {}
  
  public int stopService(IApplicationThread paramIApplicationThread, Intent paramIntent, String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public boolean stopServiceToken(ComponentName paramComponentName, IBinder paramIBinder, int paramInt) throws RemoteException {
    return false;
  }
  
  public int stopUser(int paramInt, boolean paramBoolean, IStopUserCallback paramIStopUserCallback) throws RemoteException {
    return 0;
  }
  
  public int stopUserWithDelayedLocking(int paramInt, boolean paramBoolean, IStopUserCallback paramIStopUserCallback) throws RemoteException {
    return 0;
  }
  
  public void suppressResizeConfigChanges(boolean paramBoolean) throws RemoteException {}
  
  public boolean switchUser(int paramInt) throws RemoteException {
    return false;
  }
  
  public void unbindBackupAgent(ApplicationInfo paramApplicationInfo) throws RemoteException {}
  
  public void unbindFinished(IBinder paramIBinder, Intent paramIntent, boolean paramBoolean) throws RemoteException {}
  
  public boolean unbindService(IServiceConnection paramIServiceConnection) throws RemoteException {
    return false;
  }
  
  public void unbroadcastIntent(IApplicationThread paramIApplicationThread, Intent paramIntent, int paramInt) throws RemoteException {}
  
  public void unhandledBack() throws RemoteException {}
  
  public boolean unlockUser(int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, IProgressListener paramIProgressListener) throws RemoteException {
    return false;
  }
  
  public void unregisterIntentSenderCancelListener(IIntentSender paramIIntentSender, IResultReceiver paramIResultReceiver) throws RemoteException {}
  
  public void unregisterProcessObserver(IProcessObserver paramIProcessObserver) throws RemoteException {}
  
  public void unregisterReceiver(IIntentReceiver paramIIntentReceiver) throws RemoteException {}
  
  public void unregisterTaskStackListener(ITaskStackListener paramITaskStackListener) throws RemoteException {}
  
  public void unregisterUidObserver(IUidObserver paramIUidObserver) throws RemoteException {}
  
  public void unregisterUserSwitchObserver(IUserSwitchObserver paramIUserSwitchObserver) throws RemoteException {}
  
  public void unstableProviderDied(IBinder paramIBinder) throws RemoteException {}
  
  public boolean updateConfiguration(Configuration paramConfiguration) throws RemoteException {
    return false;
  }
  
  public void updateDeviceOwner(String paramString) throws RemoteException {}
  
  public void updateLockTaskPackages(int paramInt, String[] paramArrayOfString) throws RemoteException {}
  
  public boolean updateMccMncConfiguration(String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public void updatePersistentConfiguration(Configuration paramConfiguration) throws RemoteException {}
  
  public void updateServiceGroup(IServiceConnection paramIServiceConnection, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void waitForNetworkStateUpdate(long paramLong) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
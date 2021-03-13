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
import android.os.Binder;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IProgressListener;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.WorkSource;
import android.text.TextUtils;
import android.view.IRecentsAnimationRunner;
import com.android.internal.os.IResultReceiver;
import java.util.List;

public interface IActivityManager extends IInterface {
  void addInstrumentationResults(IApplicationThread paramIApplicationThread, Bundle paramBundle) throws RemoteException;
  
  void addPackageDependency(String paramString) throws RemoteException;
  
  void appNotResponding(String paramString) throws RemoteException;
  
  void appNotRespondingViaProvider(IBinder paramIBinder) throws RemoteException;
  
  void attachApplication(IApplicationThread paramIApplicationThread, long paramLong) throws RemoteException;
  
  void backgroundWhitelistUid(int paramInt) throws RemoteException;
  
  void backupAgentCreated(String paramString, IBinder paramIBinder, int paramInt) throws RemoteException;
  
  boolean bindBackupAgent(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  int bindIsolatedService(IApplicationThread paramIApplicationThread, IBinder paramIBinder, Intent paramIntent, String paramString1, IServiceConnection paramIServiceConnection, int paramInt1, String paramString2, String paramString3, int paramInt2) throws RemoteException;
  
  int bindService(IApplicationThread paramIApplicationThread, IBinder paramIBinder, Intent paramIntent, String paramString1, IServiceConnection paramIServiceConnection, int paramInt1, String paramString2, int paramInt2) throws RemoteException;
  
  void bootAnimationComplete() throws RemoteException;
  
  int broadcastIntent(IApplicationThread paramIApplicationThread, Intent paramIntent, String paramString1, IIntentReceiver paramIIntentReceiver, int paramInt1, String paramString2, Bundle paramBundle1, String[] paramArrayOfString, int paramInt2, Bundle paramBundle2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3) throws RemoteException;
  
  int broadcastIntentWithFeature(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IIntentReceiver paramIIntentReceiver, int paramInt1, String paramString3, Bundle paramBundle1, String[] paramArrayOfString, int paramInt2, Bundle paramBundle2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3) throws RemoteException;
  
  void cancelIntentSender(IIntentSender paramIIntentSender) throws RemoteException;
  
  void cancelRecentsAnimation(boolean paramBoolean) throws RemoteException;
  
  void cancelTaskWindowTransition(int paramInt) throws RemoteException;
  
  int checkPermission(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  int checkPermissionWithToken(String paramString, int paramInt1, int paramInt2, IBinder paramIBinder) throws RemoteException;
  
  int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3, int paramInt4, IBinder paramIBinder) throws RemoteException;
  
  boolean clearApplicationUserData(String paramString, boolean paramBoolean, IPackageDataObserver paramIPackageDataObserver, int paramInt) throws RemoteException;
  
  void closeSystemDialogs(String paramString) throws RemoteException;
  
  void crashApplication(int paramInt1, int paramInt2, String paramString1, int paramInt3, String paramString2, boolean paramBoolean) throws RemoteException;
  
  boolean dumpHeap(String paramString1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2, ParcelFileDescriptor paramParcelFileDescriptor, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  void dumpHeapFinished(String paramString) throws RemoteException;
  
  boolean enableAppFreezer(boolean paramBoolean) throws RemoteException;
  
  void enterSafeMode() throws RemoteException;
  
  boolean finishActivity(IBinder paramIBinder, int paramInt1, Intent paramIntent, int paramInt2) throws RemoteException;
  
  void finishHeavyWeightApp() throws RemoteException;
  
  void finishInstrumentation(IApplicationThread paramIApplicationThread, int paramInt, Bundle paramBundle) throws RemoteException;
  
  void finishReceiver(IBinder paramIBinder, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean, int paramInt2) throws RemoteException;
  
  void forceStopPackage(String paramString, int paramInt) throws RemoteException;
  
  List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException;
  
  List<String> getBugreportWhitelistedPackages() throws RemoteException;
  
  Configuration getConfiguration() throws RemoteException;
  
  ContentProviderHolder getContentProvider(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, int paramInt, boolean paramBoolean) throws RemoteException;
  
  ContentProviderHolder getContentProviderExternal(String paramString1, int paramInt, IBinder paramIBinder, String paramString2) throws RemoteException;
  
  UserInfo getCurrentUser() throws RemoteException;
  
  ActivityManager.StackInfo getFocusedStackInfo() throws RemoteException;
  
  int getForegroundServiceType(ComponentName paramComponentName, IBinder paramIBinder) throws RemoteException;
  
  ParceledListSlice<ApplicationExitInfo> getHistoricalProcessExitReasons(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  Intent getIntentForIntentSender(IIntentSender paramIIntentSender) throws RemoteException;
  
  IIntentSender getIntentSender(int paramInt1, String paramString1, IBinder paramIBinder, String paramString2, int paramInt2, Intent[] paramArrayOfIntent, String[] paramArrayOfString, int paramInt3, Bundle paramBundle, int paramInt4) throws RemoteException;
  
  IIntentSender getIntentSenderWithFeature(int paramInt1, String paramString1, String paramString2, IBinder paramIBinder, String paramString3, int paramInt2, Intent[] paramArrayOfIntent, String[] paramArrayOfString, int paramInt3, Bundle paramBundle, int paramInt4) throws RemoteException;
  
  String getLaunchedFromPackage(IBinder paramIBinder) throws RemoteException;
  
  int getLaunchedFromUid(IBinder paramIBinder) throws RemoteException;
  
  ParcelFileDescriptor getLifeMonitor() throws RemoteException;
  
  int getLockTaskModeState() throws RemoteException;
  
  void getMemoryInfo(ActivityManager.MemoryInfo paramMemoryInfo) throws RemoteException;
  
  int getMemoryTrimLevel() throws RemoteException;
  
  void getMyMemoryState(ActivityManager.RunningAppProcessInfo paramRunningAppProcessInfo) throws RemoteException;
  
  String getPackageForIntentSender(IIntentSender paramIIntentSender) throws RemoteException;
  
  int getPackageProcessState(String paramString1, String paramString2) throws RemoteException;
  
  int getProcessLimit() throws RemoteException;
  
  Debug.MemoryInfo[] getProcessMemoryInfo(int[] paramArrayOfint) throws RemoteException;
  
  long[] getProcessPss(int[] paramArrayOfint) throws RemoteException;
  
  List<ActivityManager.ProcessErrorStateInfo> getProcessesInErrorState() throws RemoteException;
  
  String getProviderMimeType(Uri paramUri, int paramInt) throws RemoteException;
  
  void getProviderMimeTypeAsync(Uri paramUri, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  ParceledListSlice getRecentTasks(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() throws RemoteException;
  
  List<ApplicationInfo> getRunningExternalApplications() throws RemoteException;
  
  PendingIntent getRunningServiceControlPanel(ComponentName paramComponentName) throws RemoteException;
  
  int[] getRunningUserIds() throws RemoteException;
  
  List<ActivityManager.RunningServiceInfo> getServices(int paramInt1, int paramInt2) throws RemoteException;
  
  String getTagForIntentSender(IIntentSender paramIIntentSender, String paramString) throws RemoteException;
  
  Rect getTaskBounds(int paramInt) throws RemoteException;
  
  int getTaskForActivity(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  ActivityManager.TaskSnapshot getTaskSnapshot(int paramInt, boolean paramBoolean) throws RemoteException;
  
  List<ActivityManager.RunningTaskInfo> getTasks(int paramInt) throws RemoteException;
  
  int getUidForIntentSender(IIntentSender paramIIntentSender) throws RemoteException;
  
  int getUidProcessState(int paramInt, String paramString) throws RemoteException;
  
  void grantUriPermission(IApplicationThread paramIApplicationThread, String paramString, Uri paramUri, int paramInt1, int paramInt2) throws RemoteException;
  
  void handleApplicationCrash(IBinder paramIBinder, ApplicationErrorReport.ParcelableCrashInfo paramParcelableCrashInfo) throws RemoteException;
  
  void handleApplicationStrictModeViolation(IBinder paramIBinder, int paramInt, StrictMode.ViolationInfo paramViolationInfo) throws RemoteException;
  
  boolean handleApplicationWtf(IBinder paramIBinder, String paramString, boolean paramBoolean, ApplicationErrorReport.ParcelableCrashInfo paramParcelableCrashInfo, int paramInt) throws RemoteException;
  
  int handleIncomingUser(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2) throws RemoteException;
  
  void hang(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  boolean isAppFreezerSupported() throws RemoteException;
  
  boolean isAppStartModeDisabled(int paramInt, String paramString) throws RemoteException;
  
  boolean isBackgroundRestricted(String paramString) throws RemoteException;
  
  boolean isInLockTaskMode() throws RemoteException;
  
  boolean isIntentSenderABroadcast(IIntentSender paramIIntentSender) throws RemoteException;
  
  boolean isIntentSenderAForegroundService(IIntentSender paramIIntentSender) throws RemoteException;
  
  boolean isIntentSenderAnActivity(IIntentSender paramIIntentSender) throws RemoteException;
  
  boolean isIntentSenderTargetedToPackage(IIntentSender paramIIntentSender) throws RemoteException;
  
  boolean isTopActivityImmersive() throws RemoteException;
  
  boolean isTopOfTask(IBinder paramIBinder) throws RemoteException;
  
  boolean isUidActive(int paramInt, String paramString) throws RemoteException;
  
  boolean isUserAMonkey() throws RemoteException;
  
  boolean isUserRunning(int paramInt1, int paramInt2) throws RemoteException;
  
  boolean isVrModePackageEnabled(ComponentName paramComponentName) throws RemoteException;
  
  void killAllBackgroundProcesses() throws RemoteException;
  
  void killApplication(String paramString1, int paramInt1, int paramInt2, String paramString2) throws RemoteException;
  
  void killApplicationProcess(String paramString, int paramInt) throws RemoteException;
  
  void killBackgroundProcesses(String paramString, int paramInt) throws RemoteException;
  
  void killPackageDependents(String paramString, int paramInt) throws RemoteException;
  
  boolean killPids(int[] paramArrayOfint, String paramString, boolean paramBoolean) throws RemoteException;
  
  boolean killProcessesBelowForeground(String paramString) throws RemoteException;
  
  void killProcessesWhenImperceptible(int[] paramArrayOfint, String paramString) throws RemoteException;
  
  void killUid(int paramInt1, int paramInt2, String paramString) throws RemoteException;
  
  void killUidForPermissionChange(int paramInt1, int paramInt2, String paramString) throws RemoteException;
  
  boolean launchBugReportHandlerApp() throws RemoteException;
  
  void makePackageIdle(String paramString, int paramInt) throws RemoteException;
  
  boolean moveActivityTaskToBack(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  void moveTaskToFront(IApplicationThread paramIApplicationThread, String paramString, int paramInt1, int paramInt2, Bundle paramBundle) throws RemoteException;
  
  void moveTaskToStack(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException;
  
  boolean moveTopActivityToPinnedStack(int paramInt, Rect paramRect) throws RemoteException;
  
  void noteAlarmFinish(IIntentSender paramIIntentSender, WorkSource paramWorkSource, int paramInt, String paramString) throws RemoteException;
  
  void noteAlarmStart(IIntentSender paramIIntentSender, WorkSource paramWorkSource, int paramInt, String paramString) throws RemoteException;
  
  void noteWakeupAlarm(IIntentSender paramIIntentSender, WorkSource paramWorkSource, int paramInt, String paramString1, String paramString2) throws RemoteException;
  
  void notifyCleartextNetwork(int paramInt, byte[] paramArrayOfbyte) throws RemoteException;
  
  void notifyLockedProfile(int paramInt) throws RemoteException;
  
  ParcelFileDescriptor openContentUri(String paramString) throws RemoteException;
  
  IBinder peekService(Intent paramIntent, String paramString1, String paramString2) throws RemoteException;
  
  void performIdleMaintenance() throws RemoteException;
  
  void positionTaskInStack(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  boolean profileControl(String paramString, int paramInt1, boolean paramBoolean, ProfilerInfo paramProfilerInfo, int paramInt2) throws RemoteException;
  
  void publishContentProviders(IApplicationThread paramIApplicationThread, List<ContentProviderHolder> paramList) throws RemoteException;
  
  void publishService(IBinder paramIBinder1, Intent paramIntent, IBinder paramIBinder2) throws RemoteException;
  
  boolean refContentProvider(IBinder paramIBinder, int paramInt1, int paramInt2) throws RemoteException;
  
  void registerIntentSenderCancelListener(IIntentSender paramIIntentSender, IResultReceiver paramIResultReceiver) throws RemoteException;
  
  void registerProcessObserver(IProcessObserver paramIProcessObserver) throws RemoteException;
  
  Intent registerReceiver(IApplicationThread paramIApplicationThread, String paramString1, IIntentReceiver paramIIntentReceiver, IntentFilter paramIntentFilter, String paramString2, int paramInt1, int paramInt2) throws RemoteException;
  
  Intent registerReceiverWithFeature(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, IIntentReceiver paramIIntentReceiver, IntentFilter paramIntentFilter, String paramString3, int paramInt1, int paramInt2) throws RemoteException;
  
  void registerTaskStackListener(ITaskStackListener paramITaskStackListener) throws RemoteException;
  
  void registerUidObserver(IUidObserver paramIUidObserver, int paramInt1, int paramInt2, String paramString) throws RemoteException;
  
  void registerUserSwitchObserver(IUserSwitchObserver paramIUserSwitchObserver, String paramString) throws RemoteException;
  
  void removeContentProvider(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  void removeContentProviderExternal(String paramString, IBinder paramIBinder) throws RemoteException;
  
  void removeContentProviderExternalAsUser(String paramString, IBinder paramIBinder, int paramInt) throws RemoteException;
  
  void removeStack(int paramInt) throws RemoteException;
  
  boolean removeTask(int paramInt) throws RemoteException;
  
  void requestBugReport(int paramInt) throws RemoteException;
  
  void requestBugReportWithDescription(String paramString1, String paramString2, int paramInt) throws RemoteException;
  
  void requestFullBugReport() throws RemoteException;
  
  void requestInteractiveBugReport() throws RemoteException;
  
  void requestInteractiveBugReportWithDescription(String paramString1, String paramString2) throws RemoteException;
  
  void requestRemoteBugReport() throws RemoteException;
  
  void requestSystemServerHeapDump() throws RemoteException;
  
  void requestTelephonyBugReport(String paramString1, String paramString2) throws RemoteException;
  
  void requestWifiBugReport(String paramString1, String paramString2) throws RemoteException;
  
  void resizeTask(int paramInt1, Rect paramRect, int paramInt2) throws RemoteException;
  
  void restart() throws RemoteException;
  
  int restartUserInBackground(int paramInt) throws RemoteException;
  
  void resumeAppSwitches() throws RemoteException;
  
  void revokeUriPermission(IApplicationThread paramIApplicationThread, String paramString, Uri paramUri, int paramInt1, int paramInt2) throws RemoteException;
  
  void scheduleApplicationInfoChanged(List<String> paramList, int paramInt) throws RemoteException;
  
  void sendIdleJobTrigger() throws RemoteException;
  
  int sendIntentSender(IIntentSender paramIIntentSender, IBinder paramIBinder, int paramInt, Intent paramIntent, String paramString1, IIntentReceiver paramIIntentReceiver, String paramString2, Bundle paramBundle) throws RemoteException;
  
  void serviceDoneExecuting(IBinder paramIBinder, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void setActivityController(IActivityController paramIActivityController, boolean paramBoolean) throws RemoteException;
  
  void setActivityLocusContext(ComponentName paramComponentName, LocusId paramLocusId, IBinder paramIBinder) throws RemoteException;
  
  void setAgentApp(String paramString1, String paramString2) throws RemoteException;
  
  void setAlwaysFinish(boolean paramBoolean) throws RemoteException;
  
  void setDebugApp(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException;
  
  void setDumpHeapDebugLimit(String paramString1, int paramInt, long paramLong, String paramString2) throws RemoteException;
  
  void setFocusedStack(int paramInt) throws RemoteException;
  
  void setHasTopUi(boolean paramBoolean) throws RemoteException;
  
  void setPackageScreenCompatMode(String paramString, int paramInt) throws RemoteException;
  
  void setPersistentVrThread(int paramInt) throws RemoteException;
  
  void setProcessImportant(IBinder paramIBinder, int paramInt, boolean paramBoolean, String paramString) throws RemoteException;
  
  void setProcessLimit(int paramInt) throws RemoteException;
  
  boolean setProcessMemoryTrimLevel(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  void setProcessStateSummary(byte[] paramArrayOfbyte) throws RemoteException;
  
  void setRenderThread(int paramInt) throws RemoteException;
  
  void setRequestedOrientation(IBinder paramIBinder, int paramInt) throws RemoteException;
  
  void setServiceForeground(ComponentName paramComponentName, IBinder paramIBinder, int paramInt1, Notification paramNotification, int paramInt2, int paramInt3) throws RemoteException;
  
  void setTaskResizeable(int paramInt1, int paramInt2) throws RemoteException;
  
  void setUserIsMonkey(boolean paramBoolean) throws RemoteException;
  
  void showBootMessage(CharSequence paramCharSequence, boolean paramBoolean) throws RemoteException;
  
  void showWaitingForDebugger(IApplicationThread paramIApplicationThread, boolean paramBoolean) throws RemoteException;
  
  boolean shutdown(int paramInt) throws RemoteException;
  
  void signalPersistentProcesses(int paramInt) throws RemoteException;
  
  int startActivity(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IBinder paramIBinder, String paramString3, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle) throws RemoteException;
  
  int startActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IBinder paramIBinder, String paramString3, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException;
  
  int startActivityAsUserWithFeature(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException;
  
  int startActivityFromRecents(int paramInt, Bundle paramBundle) throws RemoteException;
  
  int startActivityWithFeature(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle) throws RemoteException;
  
  boolean startBinderTracking() throws RemoteException;
  
  void startConfirmDeviceCredentialIntent(Intent paramIntent, Bundle paramBundle) throws RemoteException;
  
  void startDelegateShellPermissionIdentity(int paramInt, String[] paramArrayOfString) throws RemoteException;
  
  boolean startInstrumentation(ComponentName paramComponentName, String paramString1, int paramInt1, Bundle paramBundle, IInstrumentationWatcher paramIInstrumentationWatcher, IUiAutomationConnection paramIUiAutomationConnection, int paramInt2, String paramString2) throws RemoteException;
  
  void startRecentsActivity(Intent paramIntent, IAssistDataReceiver paramIAssistDataReceiver, IRecentsAnimationRunner paramIRecentsAnimationRunner) throws RemoteException;
  
  ComponentName startService(IApplicationThread paramIApplicationThread, Intent paramIntent, String paramString1, boolean paramBoolean, String paramString2, String paramString3, int paramInt) throws RemoteException;
  
  void startSystemLockTaskMode(int paramInt) throws RemoteException;
  
  boolean startUserInBackground(int paramInt) throws RemoteException;
  
  boolean startUserInBackgroundWithListener(int paramInt, IProgressListener paramIProgressListener) throws RemoteException;
  
  boolean startUserInForegroundWithListener(int paramInt, IProgressListener paramIProgressListener) throws RemoteException;
  
  void stopAppSwitches() throws RemoteException;
  
  boolean stopBinderTrackingAndDump(ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException;
  
  void stopDelegateShellPermissionIdentity() throws RemoteException;
  
  int stopService(IApplicationThread paramIApplicationThread, Intent paramIntent, String paramString, int paramInt) throws RemoteException;
  
  boolean stopServiceToken(ComponentName paramComponentName, IBinder paramIBinder, int paramInt) throws RemoteException;
  
  int stopUser(int paramInt, boolean paramBoolean, IStopUserCallback paramIStopUserCallback) throws RemoteException;
  
  int stopUserWithDelayedLocking(int paramInt, boolean paramBoolean, IStopUserCallback paramIStopUserCallback) throws RemoteException;
  
  void suppressResizeConfigChanges(boolean paramBoolean) throws RemoteException;
  
  boolean switchUser(int paramInt) throws RemoteException;
  
  void unbindBackupAgent(ApplicationInfo paramApplicationInfo) throws RemoteException;
  
  void unbindFinished(IBinder paramIBinder, Intent paramIntent, boolean paramBoolean) throws RemoteException;
  
  boolean unbindService(IServiceConnection paramIServiceConnection) throws RemoteException;
  
  void unbroadcastIntent(IApplicationThread paramIApplicationThread, Intent paramIntent, int paramInt) throws RemoteException;
  
  void unhandledBack() throws RemoteException;
  
  boolean unlockUser(int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, IProgressListener paramIProgressListener) throws RemoteException;
  
  void unregisterIntentSenderCancelListener(IIntentSender paramIIntentSender, IResultReceiver paramIResultReceiver) throws RemoteException;
  
  void unregisterProcessObserver(IProcessObserver paramIProcessObserver) throws RemoteException;
  
  void unregisterReceiver(IIntentReceiver paramIIntentReceiver) throws RemoteException;
  
  void unregisterTaskStackListener(ITaskStackListener paramITaskStackListener) throws RemoteException;
  
  void unregisterUidObserver(IUidObserver paramIUidObserver) throws RemoteException;
  
  void unregisterUserSwitchObserver(IUserSwitchObserver paramIUserSwitchObserver) throws RemoteException;
  
  void unstableProviderDied(IBinder paramIBinder) throws RemoteException;
  
  boolean updateConfiguration(Configuration paramConfiguration) throws RemoteException;
  
  void updateDeviceOwner(String paramString) throws RemoteException;
  
  void updateLockTaskPackages(int paramInt, String[] paramArrayOfString) throws RemoteException;
  
  boolean updateMccMncConfiguration(String paramString1, String paramString2) throws RemoteException;
  
  void updatePersistentConfiguration(Configuration paramConfiguration) throws RemoteException;
  
  void updateServiceGroup(IServiceConnection paramIServiceConnection, int paramInt1, int paramInt2) throws RemoteException;
  
  void waitForNetworkStateUpdate(long paramLong) throws RemoteException;
  
  public static class Default implements IActivityManager {
    public void addInstrumentationResults(IApplicationThread param1IApplicationThread, Bundle param1Bundle) throws RemoteException {}
    
    public void addPackageDependency(String param1String) throws RemoteException {}
    
    public void appNotResponding(String param1String) throws RemoteException {}
    
    public void appNotRespondingViaProvider(IBinder param1IBinder) throws RemoteException {}
    
    public IBinder asBinder() {
      return null;
    }
    
    public void attachApplication(IApplicationThread param1IApplicationThread, long param1Long) throws RemoteException {}
    
    public void backgroundWhitelistUid(int param1Int) throws RemoteException {}
    
    public void backupAgentCreated(String param1String, IBinder param1IBinder, int param1Int) throws RemoteException {}
    
    public boolean bindBackupAgent(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      return false;
    }
    
    public int bindIsolatedService(IApplicationThread param1IApplicationThread, IBinder param1IBinder, Intent param1Intent, String param1String1, IServiceConnection param1IServiceConnection, int param1Int1, String param1String2, String param1String3, int param1Int2) throws RemoteException {
      return 0;
    }
    
    public int bindService(IApplicationThread param1IApplicationThread, IBinder param1IBinder, Intent param1Intent, String param1String1, IServiceConnection param1IServiceConnection, int param1Int1, String param1String2, int param1Int2) throws RemoteException {
      return 0;
    }
    
    public void bootAnimationComplete() throws RemoteException {}
    
    public int broadcastIntent(IApplicationThread param1IApplicationThread, Intent param1Intent, String param1String1, IIntentReceiver param1IIntentReceiver, int param1Int1, String param1String2, Bundle param1Bundle1, String[] param1ArrayOfString, int param1Int2, Bundle param1Bundle2, boolean param1Boolean1, boolean param1Boolean2, int param1Int3) throws RemoteException {
      return 0;
    }
    
    public int broadcastIntentWithFeature(IApplicationThread param1IApplicationThread, String param1String1, Intent param1Intent, String param1String2, IIntentReceiver param1IIntentReceiver, int param1Int1, String param1String3, Bundle param1Bundle1, String[] param1ArrayOfString, int param1Int2, Bundle param1Bundle2, boolean param1Boolean1, boolean param1Boolean2, int param1Int3) throws RemoteException {
      return 0;
    }
    
    public void cancelIntentSender(IIntentSender param1IIntentSender) throws RemoteException {}
    
    public void cancelRecentsAnimation(boolean param1Boolean) throws RemoteException {}
    
    public void cancelTaskWindowTransition(int param1Int) throws RemoteException {}
    
    public int checkPermission(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      return 0;
    }
    
    public int checkPermissionWithToken(String param1String, int param1Int1, int param1Int2, IBinder param1IBinder) throws RemoteException {
      return 0;
    }
    
    public int checkUriPermission(Uri param1Uri, int param1Int1, int param1Int2, int param1Int3, int param1Int4, IBinder param1IBinder) throws RemoteException {
      return 0;
    }
    
    public boolean clearApplicationUserData(String param1String, boolean param1Boolean, IPackageDataObserver param1IPackageDataObserver, int param1Int) throws RemoteException {
      return false;
    }
    
    public void closeSystemDialogs(String param1String) throws RemoteException {}
    
    public void crashApplication(int param1Int1, int param1Int2, String param1String1, int param1Int3, String param1String2, boolean param1Boolean) throws RemoteException {}
    
    public boolean dumpHeap(String param1String1, int param1Int, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, String param1String2, ParcelFileDescriptor param1ParcelFileDescriptor, RemoteCallback param1RemoteCallback) throws RemoteException {
      return false;
    }
    
    public void dumpHeapFinished(String param1String) throws RemoteException {}
    
    public boolean enableAppFreezer(boolean param1Boolean) throws RemoteException {
      return false;
    }
    
    public void enterSafeMode() throws RemoteException {}
    
    public boolean finishActivity(IBinder param1IBinder, int param1Int1, Intent param1Intent, int param1Int2) throws RemoteException {
      return false;
    }
    
    public void finishHeavyWeightApp() throws RemoteException {}
    
    public void finishInstrumentation(IApplicationThread param1IApplicationThread, int param1Int, Bundle param1Bundle) throws RemoteException {}
    
    public void finishReceiver(IBinder param1IBinder, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean, int param1Int2) throws RemoteException {}
    
    public void forceStopPackage(String param1String, int param1Int) throws RemoteException {}
    
    public List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException {
      return null;
    }
    
    public List<String> getBugreportWhitelistedPackages() throws RemoteException {
      return null;
    }
    
    public Configuration getConfiguration() throws RemoteException {
      return null;
    }
    
    public ContentProviderHolder getContentProvider(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, int param1Int, boolean param1Boolean) throws RemoteException {
      return null;
    }
    
    public ContentProviderHolder getContentProviderExternal(String param1String1, int param1Int, IBinder param1IBinder, String param1String2) throws RemoteException {
      return null;
    }
    
    public UserInfo getCurrentUser() throws RemoteException {
      return null;
    }
    
    public ActivityManager.StackInfo getFocusedStackInfo() throws RemoteException {
      return null;
    }
    
    public int getForegroundServiceType(ComponentName param1ComponentName, IBinder param1IBinder) throws RemoteException {
      return 0;
    }
    
    public ParceledListSlice<ApplicationExitInfo> getHistoricalProcessExitReasons(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      return null;
    }
    
    public Intent getIntentForIntentSender(IIntentSender param1IIntentSender) throws RemoteException {
      return null;
    }
    
    public IIntentSender getIntentSender(int param1Int1, String param1String1, IBinder param1IBinder, String param1String2, int param1Int2, Intent[] param1ArrayOfIntent, String[] param1ArrayOfString, int param1Int3, Bundle param1Bundle, int param1Int4) throws RemoteException {
      return null;
    }
    
    public IIntentSender getIntentSenderWithFeature(int param1Int1, String param1String1, String param1String2, IBinder param1IBinder, String param1String3, int param1Int2, Intent[] param1ArrayOfIntent, String[] param1ArrayOfString, int param1Int3, Bundle param1Bundle, int param1Int4) throws RemoteException {
      return null;
    }
    
    public String getLaunchedFromPackage(IBinder param1IBinder) throws RemoteException {
      return null;
    }
    
    public int getLaunchedFromUid(IBinder param1IBinder) throws RemoteException {
      return 0;
    }
    
    public ParcelFileDescriptor getLifeMonitor() throws RemoteException {
      return null;
    }
    
    public int getLockTaskModeState() throws RemoteException {
      return 0;
    }
    
    public void getMemoryInfo(ActivityManager.MemoryInfo param1MemoryInfo) throws RemoteException {}
    
    public int getMemoryTrimLevel() throws RemoteException {
      return 0;
    }
    
    public void getMyMemoryState(ActivityManager.RunningAppProcessInfo param1RunningAppProcessInfo) throws RemoteException {}
    
    public String getPackageForIntentSender(IIntentSender param1IIntentSender) throws RemoteException {
      return null;
    }
    
    public int getPackageProcessState(String param1String1, String param1String2) throws RemoteException {
      return 0;
    }
    
    public int getProcessLimit() throws RemoteException {
      return 0;
    }
    
    public Debug.MemoryInfo[] getProcessMemoryInfo(int[] param1ArrayOfint) throws RemoteException {
      return null;
    }
    
    public long[] getProcessPss(int[] param1ArrayOfint) throws RemoteException {
      return null;
    }
    
    public List<ActivityManager.ProcessErrorStateInfo> getProcessesInErrorState() throws RemoteException {
      return null;
    }
    
    public String getProviderMimeType(Uri param1Uri, int param1Int) throws RemoteException {
      return null;
    }
    
    public void getProviderMimeTypeAsync(Uri param1Uri, int param1Int, RemoteCallback param1RemoteCallback) throws RemoteException {}
    
    public ParceledListSlice getRecentTasks(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      return null;
    }
    
    public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() throws RemoteException {
      return null;
    }
    
    public List<ApplicationInfo> getRunningExternalApplications() throws RemoteException {
      return null;
    }
    
    public PendingIntent getRunningServiceControlPanel(ComponentName param1ComponentName) throws RemoteException {
      return null;
    }
    
    public int[] getRunningUserIds() throws RemoteException {
      return null;
    }
    
    public List<ActivityManager.RunningServiceInfo> getServices(int param1Int1, int param1Int2) throws RemoteException {
      return null;
    }
    
    public String getTagForIntentSender(IIntentSender param1IIntentSender, String param1String) throws RemoteException {
      return null;
    }
    
    public Rect getTaskBounds(int param1Int) throws RemoteException {
      return null;
    }
    
    public int getTaskForActivity(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      return 0;
    }
    
    public ActivityManager.TaskSnapshot getTaskSnapshot(int param1Int, boolean param1Boolean) throws RemoteException {
      return null;
    }
    
    public List<ActivityManager.RunningTaskInfo> getTasks(int param1Int) throws RemoteException {
      return null;
    }
    
    public int getUidForIntentSender(IIntentSender param1IIntentSender) throws RemoteException {
      return 0;
    }
    
    public int getUidProcessState(int param1Int, String param1String) throws RemoteException {
      return 0;
    }
    
    public void grantUriPermission(IApplicationThread param1IApplicationThread, String param1String, Uri param1Uri, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void handleApplicationCrash(IBinder param1IBinder, ApplicationErrorReport.ParcelableCrashInfo param1ParcelableCrashInfo) throws RemoteException {}
    
    public void handleApplicationStrictModeViolation(IBinder param1IBinder, int param1Int, StrictMode.ViolationInfo param1ViolationInfo) throws RemoteException {}
    
    public boolean handleApplicationWtf(IBinder param1IBinder, String param1String, boolean param1Boolean, ApplicationErrorReport.ParcelableCrashInfo param1ParcelableCrashInfo, int param1Int) throws RemoteException {
      return false;
    }
    
    public int handleIncomingUser(int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, String param1String1, String param1String2) throws RemoteException {
      return 0;
    }
    
    public void hang(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {}
    
    public boolean isAppFreezerSupported() throws RemoteException {
      return false;
    }
    
    public boolean isAppStartModeDisabled(int param1Int, String param1String) throws RemoteException {
      return false;
    }
    
    public boolean isBackgroundRestricted(String param1String) throws RemoteException {
      return false;
    }
    
    public boolean isInLockTaskMode() throws RemoteException {
      return false;
    }
    
    public boolean isIntentSenderABroadcast(IIntentSender param1IIntentSender) throws RemoteException {
      return false;
    }
    
    public boolean isIntentSenderAForegroundService(IIntentSender param1IIntentSender) throws RemoteException {
      return false;
    }
    
    public boolean isIntentSenderAnActivity(IIntentSender param1IIntentSender) throws RemoteException {
      return false;
    }
    
    public boolean isIntentSenderTargetedToPackage(IIntentSender param1IIntentSender) throws RemoteException {
      return false;
    }
    
    public boolean isTopActivityImmersive() throws RemoteException {
      return false;
    }
    
    public boolean isTopOfTask(IBinder param1IBinder) throws RemoteException {
      return false;
    }
    
    public boolean isUidActive(int param1Int, String param1String) throws RemoteException {
      return false;
    }
    
    public boolean isUserAMonkey() throws RemoteException {
      return false;
    }
    
    public boolean isUserRunning(int param1Int1, int param1Int2) throws RemoteException {
      return false;
    }
    
    public boolean isVrModePackageEnabled(ComponentName param1ComponentName) throws RemoteException {
      return false;
    }
    
    public void killAllBackgroundProcesses() throws RemoteException {}
    
    public void killApplication(String param1String1, int param1Int1, int param1Int2, String param1String2) throws RemoteException {}
    
    public void killApplicationProcess(String param1String, int param1Int) throws RemoteException {}
    
    public void killBackgroundProcesses(String param1String, int param1Int) throws RemoteException {}
    
    public void killPackageDependents(String param1String, int param1Int) throws RemoteException {}
    
    public boolean killPids(int[] param1ArrayOfint, String param1String, boolean param1Boolean) throws RemoteException {
      return false;
    }
    
    public boolean killProcessesBelowForeground(String param1String) throws RemoteException {
      return false;
    }
    
    public void killProcessesWhenImperceptible(int[] param1ArrayOfint, String param1String) throws RemoteException {}
    
    public void killUid(int param1Int1, int param1Int2, String param1String) throws RemoteException {}
    
    public void killUidForPermissionChange(int param1Int1, int param1Int2, String param1String) throws RemoteException {}
    
    public boolean launchBugReportHandlerApp() throws RemoteException {
      return false;
    }
    
    public void makePackageIdle(String param1String, int param1Int) throws RemoteException {}
    
    public boolean moveActivityTaskToBack(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      return false;
    }
    
    public void moveTaskToFront(IApplicationThread param1IApplicationThread, String param1String, int param1Int1, int param1Int2, Bundle param1Bundle) throws RemoteException {}
    
    public void moveTaskToStack(int param1Int1, int param1Int2, boolean param1Boolean) throws RemoteException {}
    
    public boolean moveTopActivityToPinnedStack(int param1Int, Rect param1Rect) throws RemoteException {
      return false;
    }
    
    public void noteAlarmFinish(IIntentSender param1IIntentSender, WorkSource param1WorkSource, int param1Int, String param1String) throws RemoteException {}
    
    public void noteAlarmStart(IIntentSender param1IIntentSender, WorkSource param1WorkSource, int param1Int, String param1String) throws RemoteException {}
    
    public void noteWakeupAlarm(IIntentSender param1IIntentSender, WorkSource param1WorkSource, int param1Int, String param1String1, String param1String2) throws RemoteException {}
    
    public void notifyCleartextNetwork(int param1Int, byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void notifyLockedProfile(int param1Int) throws RemoteException {}
    
    public ParcelFileDescriptor openContentUri(String param1String) throws RemoteException {
      return null;
    }
    
    public IBinder peekService(Intent param1Intent, String param1String1, String param1String2) throws RemoteException {
      return null;
    }
    
    public void performIdleMaintenance() throws RemoteException {}
    
    public void positionTaskInStack(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public boolean profileControl(String param1String, int param1Int1, boolean param1Boolean, ProfilerInfo param1ProfilerInfo, int param1Int2) throws RemoteException {
      return false;
    }
    
    public void publishContentProviders(IApplicationThread param1IApplicationThread, List<ContentProviderHolder> param1List) throws RemoteException {}
    
    public void publishService(IBinder param1IBinder1, Intent param1Intent, IBinder param1IBinder2) throws RemoteException {}
    
    public boolean refContentProvider(IBinder param1IBinder, int param1Int1, int param1Int2) throws RemoteException {
      return false;
    }
    
    public void registerIntentSenderCancelListener(IIntentSender param1IIntentSender, IResultReceiver param1IResultReceiver) throws RemoteException {}
    
    public void registerProcessObserver(IProcessObserver param1IProcessObserver) throws RemoteException {}
    
    public Intent registerReceiver(IApplicationThread param1IApplicationThread, String param1String1, IIntentReceiver param1IIntentReceiver, IntentFilter param1IntentFilter, String param1String2, int param1Int1, int param1Int2) throws RemoteException {
      return null;
    }
    
    public Intent registerReceiverWithFeature(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, IIntentReceiver param1IIntentReceiver, IntentFilter param1IntentFilter, String param1String3, int param1Int1, int param1Int2) throws RemoteException {
      return null;
    }
    
    public void registerTaskStackListener(ITaskStackListener param1ITaskStackListener) throws RemoteException {}
    
    public void registerUidObserver(IUidObserver param1IUidObserver, int param1Int1, int param1Int2, String param1String) throws RemoteException {}
    
    public void registerUserSwitchObserver(IUserSwitchObserver param1IUserSwitchObserver, String param1String) throws RemoteException {}
    
    public void removeContentProvider(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {}
    
    public void removeContentProviderExternal(String param1String, IBinder param1IBinder) throws RemoteException {}
    
    public void removeContentProviderExternalAsUser(String param1String, IBinder param1IBinder, int param1Int) throws RemoteException {}
    
    public void removeStack(int param1Int) throws RemoteException {}
    
    public boolean removeTask(int param1Int) throws RemoteException {
      return false;
    }
    
    public void requestBugReport(int param1Int) throws RemoteException {}
    
    public void requestBugReportWithDescription(String param1String1, String param1String2, int param1Int) throws RemoteException {}
    
    public void requestFullBugReport() throws RemoteException {}
    
    public void requestInteractiveBugReport() throws RemoteException {}
    
    public void requestInteractiveBugReportWithDescription(String param1String1, String param1String2) throws RemoteException {}
    
    public void requestRemoteBugReport() throws RemoteException {}
    
    public void requestSystemServerHeapDump() throws RemoteException {}
    
    public void requestTelephonyBugReport(String param1String1, String param1String2) throws RemoteException {}
    
    public void requestWifiBugReport(String param1String1, String param1String2) throws RemoteException {}
    
    public void resizeTask(int param1Int1, Rect param1Rect, int param1Int2) throws RemoteException {}
    
    public void restart() throws RemoteException {}
    
    public int restartUserInBackground(int param1Int) throws RemoteException {
      return 0;
    }
    
    public void resumeAppSwitches() throws RemoteException {}
    
    public void revokeUriPermission(IApplicationThread param1IApplicationThread, String param1String, Uri param1Uri, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void scheduleApplicationInfoChanged(List<String> param1List, int param1Int) throws RemoteException {}
    
    public void sendIdleJobTrigger() throws RemoteException {}
    
    public int sendIntentSender(IIntentSender param1IIntentSender, IBinder param1IBinder, int param1Int, Intent param1Intent, String param1String1, IIntentReceiver param1IIntentReceiver, String param1String2, Bundle param1Bundle) throws RemoteException {
      return 0;
    }
    
    public void serviceDoneExecuting(IBinder param1IBinder, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void setActivityController(IActivityController param1IActivityController, boolean param1Boolean) throws RemoteException {}
    
    public void setActivityLocusContext(ComponentName param1ComponentName, LocusId param1LocusId, IBinder param1IBinder) throws RemoteException {}
    
    public void setAgentApp(String param1String1, String param1String2) throws RemoteException {}
    
    public void setAlwaysFinish(boolean param1Boolean) throws RemoteException {}
    
    public void setDebugApp(String param1String, boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {}
    
    public void setDumpHeapDebugLimit(String param1String1, int param1Int, long param1Long, String param1String2) throws RemoteException {}
    
    public void setFocusedStack(int param1Int) throws RemoteException {}
    
    public void setHasTopUi(boolean param1Boolean) throws RemoteException {}
    
    public void setPackageScreenCompatMode(String param1String, int param1Int) throws RemoteException {}
    
    public void setPersistentVrThread(int param1Int) throws RemoteException {}
    
    public void setProcessImportant(IBinder param1IBinder, int param1Int, boolean param1Boolean, String param1String) throws RemoteException {}
    
    public void setProcessLimit(int param1Int) throws RemoteException {}
    
    public boolean setProcessMemoryTrimLevel(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      return false;
    }
    
    public void setProcessStateSummary(byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void setRenderThread(int param1Int) throws RemoteException {}
    
    public void setRequestedOrientation(IBinder param1IBinder, int param1Int) throws RemoteException {}
    
    public void setServiceForeground(ComponentName param1ComponentName, IBinder param1IBinder, int param1Int1, Notification param1Notification, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void setTaskResizeable(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void setUserIsMonkey(boolean param1Boolean) throws RemoteException {}
    
    public void showBootMessage(CharSequence param1CharSequence, boolean param1Boolean) throws RemoteException {}
    
    public void showWaitingForDebugger(IApplicationThread param1IApplicationThread, boolean param1Boolean) throws RemoteException {}
    
    public boolean shutdown(int param1Int) throws RemoteException {
      return false;
    }
    
    public void signalPersistentProcesses(int param1Int) throws RemoteException {}
    
    public int startActivity(IApplicationThread param1IApplicationThread, String param1String1, Intent param1Intent, String param1String2, IBinder param1IBinder, String param1String3, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle) throws RemoteException {
      return 0;
    }
    
    public int startActivityAsUser(IApplicationThread param1IApplicationThread, String param1String1, Intent param1Intent, String param1String2, IBinder param1IBinder, String param1String3, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, int param1Int3) throws RemoteException {
      return 0;
    }
    
    public int startActivityAsUserWithFeature(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, int param1Int3) throws RemoteException {
      return 0;
    }
    
    public int startActivityFromRecents(int param1Int, Bundle param1Bundle) throws RemoteException {
      return 0;
    }
    
    public int startActivityWithFeature(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle) throws RemoteException {
      return 0;
    }
    
    public boolean startBinderTracking() throws RemoteException {
      return false;
    }
    
    public void startConfirmDeviceCredentialIntent(Intent param1Intent, Bundle param1Bundle) throws RemoteException {}
    
    public void startDelegateShellPermissionIdentity(int param1Int, String[] param1ArrayOfString) throws RemoteException {}
    
    public boolean startInstrumentation(ComponentName param1ComponentName, String param1String1, int param1Int1, Bundle param1Bundle, IInstrumentationWatcher param1IInstrumentationWatcher, IUiAutomationConnection param1IUiAutomationConnection, int param1Int2, String param1String2) throws RemoteException {
      return false;
    }
    
    public void startRecentsActivity(Intent param1Intent, IAssistDataReceiver param1IAssistDataReceiver, IRecentsAnimationRunner param1IRecentsAnimationRunner) throws RemoteException {}
    
    public ComponentName startService(IApplicationThread param1IApplicationThread, Intent param1Intent, String param1String1, boolean param1Boolean, String param1String2, String param1String3, int param1Int) throws RemoteException {
      return null;
    }
    
    public void startSystemLockTaskMode(int param1Int) throws RemoteException {}
    
    public boolean startUserInBackground(int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean startUserInBackgroundWithListener(int param1Int, IProgressListener param1IProgressListener) throws RemoteException {
      return false;
    }
    
    public boolean startUserInForegroundWithListener(int param1Int, IProgressListener param1IProgressListener) throws RemoteException {
      return false;
    }
    
    public void stopAppSwitches() throws RemoteException {}
    
    public boolean stopBinderTrackingAndDump(ParcelFileDescriptor param1ParcelFileDescriptor) throws RemoteException {
      return false;
    }
    
    public void stopDelegateShellPermissionIdentity() throws RemoteException {}
    
    public int stopService(IApplicationThread param1IApplicationThread, Intent param1Intent, String param1String, int param1Int) throws RemoteException {
      return 0;
    }
    
    public boolean stopServiceToken(ComponentName param1ComponentName, IBinder param1IBinder, int param1Int) throws RemoteException {
      return false;
    }
    
    public int stopUser(int param1Int, boolean param1Boolean, IStopUserCallback param1IStopUserCallback) throws RemoteException {
      return 0;
    }
    
    public int stopUserWithDelayedLocking(int param1Int, boolean param1Boolean, IStopUserCallback param1IStopUserCallback) throws RemoteException {
      return 0;
    }
    
    public void suppressResizeConfigChanges(boolean param1Boolean) throws RemoteException {}
    
    public boolean switchUser(int param1Int) throws RemoteException {
      return false;
    }
    
    public void unbindBackupAgent(ApplicationInfo param1ApplicationInfo) throws RemoteException {}
    
    public void unbindFinished(IBinder param1IBinder, Intent param1Intent, boolean param1Boolean) throws RemoteException {}
    
    public boolean unbindService(IServiceConnection param1IServiceConnection) throws RemoteException {
      return false;
    }
    
    public void unbroadcastIntent(IApplicationThread param1IApplicationThread, Intent param1Intent, int param1Int) throws RemoteException {}
    
    public void unhandledBack() throws RemoteException {}
    
    public boolean unlockUser(int param1Int, byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2, IProgressListener param1IProgressListener) throws RemoteException {
      return false;
    }
    
    public void unregisterIntentSenderCancelListener(IIntentSender param1IIntentSender, IResultReceiver param1IResultReceiver) throws RemoteException {}
    
    public void unregisterProcessObserver(IProcessObserver param1IProcessObserver) throws RemoteException {}
    
    public void unregisterReceiver(IIntentReceiver param1IIntentReceiver) throws RemoteException {}
    
    public void unregisterTaskStackListener(ITaskStackListener param1ITaskStackListener) throws RemoteException {}
    
    public void unregisterUidObserver(IUidObserver param1IUidObserver) throws RemoteException {}
    
    public void unregisterUserSwitchObserver(IUserSwitchObserver param1IUserSwitchObserver) throws RemoteException {}
    
    public void unstableProviderDied(IBinder param1IBinder) throws RemoteException {}
    
    public boolean updateConfiguration(Configuration param1Configuration) throws RemoteException {
      return false;
    }
    
    public void updateDeviceOwner(String param1String) throws RemoteException {}
    
    public void updateLockTaskPackages(int param1Int, String[] param1ArrayOfString) throws RemoteException {}
    
    public boolean updateMccMncConfiguration(String param1String1, String param1String2) throws RemoteException {
      return false;
    }
    
    public void updatePersistentConfiguration(Configuration param1Configuration) throws RemoteException {}
    
    public void updateServiceGroup(IServiceConnection param1IServiceConnection, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void waitForNetworkStateUpdate(long param1Long) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IActivityManager {
    private static final String DESCRIPTOR = "android.app.IActivityManager";
    
    static final int TRANSACTION_addInstrumentationResults = 37;
    
    static final int TRANSACTION_addPackageDependency = 86;
    
    static final int TRANSACTION_appNotResponding = 210;
    
    static final int TRANSACTION_appNotRespondingViaProvider = 153;
    
    static final int TRANSACTION_attachApplication = 18;
    
    static final int TRANSACTION_backgroundWhitelistUid = 204;
    
    static final int TRANSACTION_backupAgentCreated = 82;
    
    static final int TRANSACTION_bindBackupAgent = 81;
    
    static final int TRANSACTION_bindIsolatedService = 29;
    
    static final int TRANSACTION_bindService = 28;
    
    static final int TRANSACTION_bootAnimationComplete = 164;
    
    static final int TRANSACTION_broadcastIntent = 14;
    
    static final int TRANSACTION_broadcastIntentWithFeature = 15;
    
    static final int TRANSACTION_cancelIntentSender = 56;
    
    static final int TRANSACTION_cancelRecentsAnimation = 160;
    
    static final int TRANSACTION_cancelTaskWindowTransition = 199;
    
    static final int TRANSACTION_checkPermission = 45;
    
    static final int TRANSACTION_checkPermissionWithToken = 165;
    
    static final int TRANSACTION_checkUriPermission = 46;
    
    static final int TRANSACTION_clearApplicationUserData = 71;
    
    static final int TRANSACTION_closeSystemDialogs = 88;
    
    static final int TRANSACTION_crashApplication = 98;
    
    static final int TRANSACTION_dumpHeap = 101;
    
    static final int TRANSACTION_dumpHeapFinished = 173;
    
    static final int TRANSACTION_enableAppFreezer = 217;
    
    static final int TRANSACTION_enterSafeMode = 60;
    
    static final int TRANSACTION_finishActivity = 10;
    
    static final int TRANSACTION_finishHeavyWeightApp = 95;
    
    static final int TRANSACTION_finishInstrumentation = 38;
    
    static final int TRANSACTION_finishReceiver = 17;
    
    static final int TRANSACTION_forceStopPackage = 72;
    
    static final int TRANSACTION_getAllStackInfos = 147;
    
    static final int TRANSACTION_getBugreportWhitelistedPackages = 141;
    
    static final int TRANSACTION_getConfiguration = 39;
    
    static final int TRANSACTION_getContentProvider = 22;
    
    static final int TRANSACTION_getContentProviderExternal = 113;
    
    static final int TRANSACTION_getCurrentUser = 118;
    
    static final int TRANSACTION_getFocusedStackInfo = 150;
    
    static final int TRANSACTION_getForegroundServiceType = 67;
    
    static final int TRANSACTION_getHistoricalProcessExitReasons = 211;
    
    static final int TRANSACTION_getIntentForIntentSender = 142;
    
    static final int TRANSACTION_getIntentSender = 54;
    
    static final int TRANSACTION_getIntentSenderWithFeature = 55;
    
    static final int TRANSACTION_getLaunchedFromPackage = 143;
    
    static final int TRANSACTION_getLaunchedFromUid = 119;
    
    static final int TRANSACTION_getLifeMonitor = 208;
    
    static final int TRANSACTION_getLockTaskModeState = 171;
    
    static final int TRANSACTION_getMemoryInfo = 69;
    
    static final int TRANSACTION_getMemoryTrimLevel = 189;
    
    static final int TRANSACTION_getMyMemoryState = 116;
    
    static final int TRANSACTION_getPackageForIntentSender = 57;
    
    static final int TRANSACTION_getPackageProcessState = 177;
    
    static final int TRANSACTION_getProcessLimit = 44;
    
    static final int TRANSACTION_getProcessMemoryInfo = 89;
    
    static final int TRANSACTION_getProcessPss = 110;
    
    static final int TRANSACTION_getProcessesInErrorState = 70;
    
    static final int TRANSACTION_getProviderMimeType = 99;
    
    static final int TRANSACTION_getProviderMimeTypeAsync = 100;
    
    static final int TRANSACTION_getRecentTasks = 52;
    
    static final int TRANSACTION_getRunningAppProcesses = 75;
    
    static final int TRANSACTION_getRunningExternalApplications = 94;
    
    static final int TRANSACTION_getRunningServiceControlPanel = 25;
    
    static final int TRANSACTION_getRunningUserIds = 130;
    
    static final int TRANSACTION_getServices = 74;
    
    static final int TRANSACTION_getTagForIntentSender = 156;
    
    static final int TRANSACTION_getTaskBounds = 154;
    
    static final int TRANSACTION_getTaskForActivity = 21;
    
    static final int TRANSACTION_getTaskSnapshot = 200;
    
    static final int TRANSACTION_getTasks = 19;
    
    static final int TRANSACTION_getUidForIntentSender = 84;
    
    static final int TRANSACTION_getUidProcessState = 5;
    
    static final int TRANSACTION_grantUriPermission = 47;
    
    static final int TRANSACTION_handleApplicationCrash = 6;
    
    static final int TRANSACTION_handleApplicationStrictModeViolation = 96;
    
    static final int TRANSACTION_handleApplicationWtf = 91;
    
    static final int TRANSACTION_handleIncomingUser = 85;
    
    static final int TRANSACTION_hang = 146;
    
    static final int TRANSACTION_isAppFreezerSupported = 215;
    
    static final int TRANSACTION_isAppStartModeDisabled = 184;
    
    static final int TRANSACTION_isBackgroundRestricted = 195;
    
    static final int TRANSACTION_isInLockTaskMode = 158;
    
    static final int TRANSACTION_isIntentSenderABroadcast = 123;
    
    static final int TRANSACTION_isIntentSenderAForegroundService = 122;
    
    static final int TRANSACTION_isIntentSenderAnActivity = 121;
    
    static final int TRANSACTION_isIntentSenderTargetedToPackage = 108;
    
    static final int TRANSACTION_isTopActivityImmersive = 97;
    
    static final int TRANSACTION_isTopOfTask = 163;
    
    static final int TRANSACTION_isUidActive = 4;
    
    static final int TRANSACTION_isUserAMonkey = 93;
    
    static final int TRANSACTION_isUserRunning = 102;
    
    static final int TRANSACTION_isVrModePackageEnabled = 190;
    
    static final int TRANSACTION_killAllBackgroundProcesses = 112;
    
    static final int TRANSACTION_killApplication = 87;
    
    static final int TRANSACTION_killApplicationProcess = 90;
    
    static final int TRANSACTION_killBackgroundProcesses = 92;
    
    static final int TRANSACTION_killPackageDependents = 186;
    
    static final int TRANSACTION_killPids = 73;
    
    static final int TRANSACTION_killProcessesBelowForeground = 117;
    
    static final int TRANSACTION_killProcessesWhenImperceptible = 212;
    
    static final int TRANSACTION_killUid = 144;
    
    static final int TRANSACTION_killUidForPermissionChange = 216;
    
    static final int TRANSACTION_launchBugReportHandlerApp = 140;
    
    static final int TRANSACTION_makePackageIdle = 188;
    
    static final int TRANSACTION_moveActivityTaskToBack = 68;
    
    static final int TRANSACTION_moveTaskToFront = 20;
    
    static final int TRANSACTION_moveTaskToStack = 148;
    
    static final int TRANSACTION_moveTopActivityToPinnedStack = 183;
    
    static final int TRANSACTION_noteAlarmFinish = 176;
    
    static final int TRANSACTION_noteAlarmStart = 175;
    
    static final int TRANSACTION_noteWakeupAlarm = 61;
    
    static final int TRANSACTION_notifyCleartextNetwork = 168;
    
    static final int TRANSACTION_notifyLockedProfile = 191;
    
    static final int TRANSACTION_openContentUri = 1;
    
    static final int TRANSACTION_peekService = 76;
    
    static final int TRANSACTION_performIdleMaintenance = 152;
    
    static final int TRANSACTION_positionTaskInStack = 181;
    
    static final int TRANSACTION_profileControl = 77;
    
    static final int TRANSACTION_publishContentProviders = 23;
    
    static final int TRANSACTION_publishService = 32;
    
    static final int TRANSACTION_refContentProvider = 24;
    
    static final int TRANSACTION_registerIntentSenderCancelListener = 58;
    
    static final int TRANSACTION_registerProcessObserver = 106;
    
    static final int TRANSACTION_registerReceiver = 11;
    
    static final int TRANSACTION_registerReceiverWithFeature = 12;
    
    static final int TRANSACTION_registerTaskStackListener = 166;
    
    static final int TRANSACTION_registerUidObserver = 2;
    
    static final int TRANSACTION_registerUserSwitchObserver = 128;
    
    static final int TRANSACTION_removeContentProvider = 62;
    
    static final int TRANSACTION_removeContentProviderExternal = 114;
    
    static final int TRANSACTION_removeContentProviderExternalAsUser = 115;
    
    static final int TRANSACTION_removeStack = 187;
    
    static final int TRANSACTION_removeTask = 105;
    
    static final int TRANSACTION_requestBugReport = 132;
    
    static final int TRANSACTION_requestBugReportWithDescription = 133;
    
    static final int TRANSACTION_requestFullBugReport = 138;
    
    static final int TRANSACTION_requestInteractiveBugReport = 137;
    
    static final int TRANSACTION_requestInteractiveBugReportWithDescription = 136;
    
    static final int TRANSACTION_requestRemoteBugReport = 139;
    
    static final int TRANSACTION_requestSystemServerHeapDump = 131;
    
    static final int TRANSACTION_requestTelephonyBugReport = 134;
    
    static final int TRANSACTION_requestWifiBugReport = 135;
    
    static final int TRANSACTION_resizeTask = 170;
    
    static final int TRANSACTION_restart = 151;
    
    static final int TRANSACTION_restartUserInBackground = 198;
    
    static final int TRANSACTION_resumeAppSwitches = 80;
    
    static final int TRANSACTION_revokeUriPermission = 48;
    
    static final int TRANSACTION_scheduleApplicationInfoChanged = 201;
    
    static final int TRANSACTION_sendIdleJobTrigger = 193;
    
    static final int TRANSACTION_sendIntentSender = 194;
    
    static final int TRANSACTION_serviceDoneExecuting = 53;
    
    static final int TRANSACTION_setActivityController = 49;
    
    static final int TRANSACTION_setActivityLocusContext = 213;
    
    static final int TRANSACTION_setAgentApp = 34;
    
    static final int TRANSACTION_setAlwaysFinish = 35;
    
    static final int TRANSACTION_setDebugApp = 33;
    
    static final int TRANSACTION_setDumpHeapDebugLimit = 172;
    
    static final int TRANSACTION_setFocusedStack = 149;
    
    static final int TRANSACTION_setHasTopUi = 197;
    
    static final int TRANSACTION_setPackageScreenCompatMode = 103;
    
    static final int TRANSACTION_setPersistentVrThread = 202;
    
    static final int TRANSACTION_setProcessImportant = 65;
    
    static final int TRANSACTION_setProcessLimit = 43;
    
    static final int TRANSACTION_setProcessMemoryTrimLevel = 155;
    
    static final int TRANSACTION_setProcessStateSummary = 214;
    
    static final int TRANSACTION_setRenderThread = 196;
    
    static final int TRANSACTION_setRequestedOrientation = 63;
    
    static final int TRANSACTION_setServiceForeground = 66;
    
    static final int TRANSACTION_setTaskResizeable = 169;
    
    static final int TRANSACTION_setUserIsMonkey = 145;
    
    static final int TRANSACTION_showBootMessage = 111;
    
    static final int TRANSACTION_showWaitingForDebugger = 50;
    
    static final int TRANSACTION_shutdown = 78;
    
    static final int TRANSACTION_signalPersistentProcesses = 51;
    
    static final int TRANSACTION_startActivity = 7;
    
    static final int TRANSACTION_startActivityAsUser = 124;
    
    static final int TRANSACTION_startActivityAsUserWithFeature = 125;
    
    static final int TRANSACTION_startActivityFromRecents = 161;
    
    static final int TRANSACTION_startActivityWithFeature = 8;
    
    static final int TRANSACTION_startBinderTracking = 179;
    
    static final int TRANSACTION_startConfirmDeviceCredentialIntent = 192;
    
    static final int TRANSACTION_startDelegateShellPermissionIdentity = 206;
    
    static final int TRANSACTION_startInstrumentation = 36;
    
    static final int TRANSACTION_startRecentsActivity = 159;
    
    static final int TRANSACTION_startService = 26;
    
    static final int TRANSACTION_startSystemLockTaskMode = 162;
    
    static final int TRANSACTION_startUserInBackground = 157;
    
    static final int TRANSACTION_startUserInBackgroundWithListener = 205;
    
    static final int TRANSACTION_startUserInForegroundWithListener = 209;
    
    static final int TRANSACTION_stopAppSwitches = 79;
    
    static final int TRANSACTION_stopBinderTrackingAndDump = 180;
    
    static final int TRANSACTION_stopDelegateShellPermissionIdentity = 207;
    
    static final int TRANSACTION_stopService = 27;
    
    static final int TRANSACTION_stopServiceToken = 42;
    
    static final int TRANSACTION_stopUser = 126;
    
    static final int TRANSACTION_stopUserWithDelayedLocking = 127;
    
    static final int TRANSACTION_suppressResizeConfigChanges = 182;
    
    static final int TRANSACTION_switchUser = 104;
    
    static final int TRANSACTION_unbindBackupAgent = 83;
    
    static final int TRANSACTION_unbindFinished = 64;
    
    static final int TRANSACTION_unbindService = 31;
    
    static final int TRANSACTION_unbroadcastIntent = 16;
    
    static final int TRANSACTION_unhandledBack = 9;
    
    static final int TRANSACTION_unlockUser = 185;
    
    static final int TRANSACTION_unregisterIntentSenderCancelListener = 59;
    
    static final int TRANSACTION_unregisterProcessObserver = 107;
    
    static final int TRANSACTION_unregisterReceiver = 13;
    
    static final int TRANSACTION_unregisterTaskStackListener = 167;
    
    static final int TRANSACTION_unregisterUidObserver = 3;
    
    static final int TRANSACTION_unregisterUserSwitchObserver = 129;
    
    static final int TRANSACTION_unstableProviderDied = 120;
    
    static final int TRANSACTION_updateConfiguration = 40;
    
    static final int TRANSACTION_updateDeviceOwner = 178;
    
    static final int TRANSACTION_updateLockTaskPackages = 174;
    
    static final int TRANSACTION_updateMccMncConfiguration = 41;
    
    static final int TRANSACTION_updatePersistentConfiguration = 109;
    
    static final int TRANSACTION_updateServiceGroup = 30;
    
    static final int TRANSACTION_waitForNetworkStateUpdate = 203;
    
    public Stub() {
      attachInterface(this, "android.app.IActivityManager");
    }
    
    public static IActivityManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IActivityManager");
      return (iInterface != null && iInterface instanceof IActivityManager) ? (IActivityManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IActivityManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 217:
          return "enableAppFreezer";
        case 216:
          return "killUidForPermissionChange";
        case 215:
          return "isAppFreezerSupported";
        case 214:
          return "setProcessStateSummary";
        case 213:
          return "setActivityLocusContext";
        case 212:
          return "killProcessesWhenImperceptible";
        case 211:
          return "getHistoricalProcessExitReasons";
        case 210:
          return "appNotResponding";
        case 209:
          return "startUserInForegroundWithListener";
        case 208:
          return "getLifeMonitor";
        case 207:
          return "stopDelegateShellPermissionIdentity";
        case 206:
          return "startDelegateShellPermissionIdentity";
        case 205:
          return "startUserInBackgroundWithListener";
        case 204:
          return "backgroundWhitelistUid";
        case 203:
          return "waitForNetworkStateUpdate";
        case 202:
          return "setPersistentVrThread";
        case 201:
          return "scheduleApplicationInfoChanged";
        case 200:
          return "getTaskSnapshot";
        case 199:
          return "cancelTaskWindowTransition";
        case 198:
          return "restartUserInBackground";
        case 197:
          return "setHasTopUi";
        case 196:
          return "setRenderThread";
        case 195:
          return "isBackgroundRestricted";
        case 194:
          return "sendIntentSender";
        case 193:
          return "sendIdleJobTrigger";
        case 192:
          return "startConfirmDeviceCredentialIntent";
        case 191:
          return "notifyLockedProfile";
        case 190:
          return "isVrModePackageEnabled";
        case 189:
          return "getMemoryTrimLevel";
        case 188:
          return "makePackageIdle";
        case 187:
          return "removeStack";
        case 186:
          return "killPackageDependents";
        case 185:
          return "unlockUser";
        case 184:
          return "isAppStartModeDisabled";
        case 183:
          return "moveTopActivityToPinnedStack";
        case 182:
          return "suppressResizeConfigChanges";
        case 181:
          return "positionTaskInStack";
        case 180:
          return "stopBinderTrackingAndDump";
        case 179:
          return "startBinderTracking";
        case 178:
          return "updateDeviceOwner";
        case 177:
          return "getPackageProcessState";
        case 176:
          return "noteAlarmFinish";
        case 175:
          return "noteAlarmStart";
        case 174:
          return "updateLockTaskPackages";
        case 173:
          return "dumpHeapFinished";
        case 172:
          return "setDumpHeapDebugLimit";
        case 171:
          return "getLockTaskModeState";
        case 170:
          return "resizeTask";
        case 169:
          return "setTaskResizeable";
        case 168:
          return "notifyCleartextNetwork";
        case 167:
          return "unregisterTaskStackListener";
        case 166:
          return "registerTaskStackListener";
        case 165:
          return "checkPermissionWithToken";
        case 164:
          return "bootAnimationComplete";
        case 163:
          return "isTopOfTask";
        case 162:
          return "startSystemLockTaskMode";
        case 161:
          return "startActivityFromRecents";
        case 160:
          return "cancelRecentsAnimation";
        case 159:
          return "startRecentsActivity";
        case 158:
          return "isInLockTaskMode";
        case 157:
          return "startUserInBackground";
        case 156:
          return "getTagForIntentSender";
        case 155:
          return "setProcessMemoryTrimLevel";
        case 154:
          return "getTaskBounds";
        case 153:
          return "appNotRespondingViaProvider";
        case 152:
          return "performIdleMaintenance";
        case 151:
          return "restart";
        case 150:
          return "getFocusedStackInfo";
        case 149:
          return "setFocusedStack";
        case 148:
          return "moveTaskToStack";
        case 147:
          return "getAllStackInfos";
        case 146:
          return "hang";
        case 145:
          return "setUserIsMonkey";
        case 144:
          return "killUid";
        case 143:
          return "getLaunchedFromPackage";
        case 142:
          return "getIntentForIntentSender";
        case 141:
          return "getBugreportWhitelistedPackages";
        case 140:
          return "launchBugReportHandlerApp";
        case 139:
          return "requestRemoteBugReport";
        case 138:
          return "requestFullBugReport";
        case 137:
          return "requestInteractiveBugReport";
        case 136:
          return "requestInteractiveBugReportWithDescription";
        case 135:
          return "requestWifiBugReport";
        case 134:
          return "requestTelephonyBugReport";
        case 133:
          return "requestBugReportWithDescription";
        case 132:
          return "requestBugReport";
        case 131:
          return "requestSystemServerHeapDump";
        case 130:
          return "getRunningUserIds";
        case 129:
          return "unregisterUserSwitchObserver";
        case 128:
          return "registerUserSwitchObserver";
        case 127:
          return "stopUserWithDelayedLocking";
        case 126:
          return "stopUser";
        case 125:
          return "startActivityAsUserWithFeature";
        case 124:
          return "startActivityAsUser";
        case 123:
          return "isIntentSenderABroadcast";
        case 122:
          return "isIntentSenderAForegroundService";
        case 121:
          return "isIntentSenderAnActivity";
        case 120:
          return "unstableProviderDied";
        case 119:
          return "getLaunchedFromUid";
        case 118:
          return "getCurrentUser";
        case 117:
          return "killProcessesBelowForeground";
        case 116:
          return "getMyMemoryState";
        case 115:
          return "removeContentProviderExternalAsUser";
        case 114:
          return "removeContentProviderExternal";
        case 113:
          return "getContentProviderExternal";
        case 112:
          return "killAllBackgroundProcesses";
        case 111:
          return "showBootMessage";
        case 110:
          return "getProcessPss";
        case 109:
          return "updatePersistentConfiguration";
        case 108:
          return "isIntentSenderTargetedToPackage";
        case 107:
          return "unregisterProcessObserver";
        case 106:
          return "registerProcessObserver";
        case 105:
          return "removeTask";
        case 104:
          return "switchUser";
        case 103:
          return "setPackageScreenCompatMode";
        case 102:
          return "isUserRunning";
        case 101:
          return "dumpHeap";
        case 100:
          return "getProviderMimeTypeAsync";
        case 99:
          return "getProviderMimeType";
        case 98:
          return "crashApplication";
        case 97:
          return "isTopActivityImmersive";
        case 96:
          return "handleApplicationStrictModeViolation";
        case 95:
          return "finishHeavyWeightApp";
        case 94:
          return "getRunningExternalApplications";
        case 93:
          return "isUserAMonkey";
        case 92:
          return "killBackgroundProcesses";
        case 91:
          return "handleApplicationWtf";
        case 90:
          return "killApplicationProcess";
        case 89:
          return "getProcessMemoryInfo";
        case 88:
          return "closeSystemDialogs";
        case 87:
          return "killApplication";
        case 86:
          return "addPackageDependency";
        case 85:
          return "handleIncomingUser";
        case 84:
          return "getUidForIntentSender";
        case 83:
          return "unbindBackupAgent";
        case 82:
          return "backupAgentCreated";
        case 81:
          return "bindBackupAgent";
        case 80:
          return "resumeAppSwitches";
        case 79:
          return "stopAppSwitches";
        case 78:
          return "shutdown";
        case 77:
          return "profileControl";
        case 76:
          return "peekService";
        case 75:
          return "getRunningAppProcesses";
        case 74:
          return "getServices";
        case 73:
          return "killPids";
        case 72:
          return "forceStopPackage";
        case 71:
          return "clearApplicationUserData";
        case 70:
          return "getProcessesInErrorState";
        case 69:
          return "getMemoryInfo";
        case 68:
          return "moveActivityTaskToBack";
        case 67:
          return "getForegroundServiceType";
        case 66:
          return "setServiceForeground";
        case 65:
          return "setProcessImportant";
        case 64:
          return "unbindFinished";
        case 63:
          return "setRequestedOrientation";
        case 62:
          return "removeContentProvider";
        case 61:
          return "noteWakeupAlarm";
        case 60:
          return "enterSafeMode";
        case 59:
          return "unregisterIntentSenderCancelListener";
        case 58:
          return "registerIntentSenderCancelListener";
        case 57:
          return "getPackageForIntentSender";
        case 56:
          return "cancelIntentSender";
        case 55:
          return "getIntentSenderWithFeature";
        case 54:
          return "getIntentSender";
        case 53:
          return "serviceDoneExecuting";
        case 52:
          return "getRecentTasks";
        case 51:
          return "signalPersistentProcesses";
        case 50:
          return "showWaitingForDebugger";
        case 49:
          return "setActivityController";
        case 48:
          return "revokeUriPermission";
        case 47:
          return "grantUriPermission";
        case 46:
          return "checkUriPermission";
        case 45:
          return "checkPermission";
        case 44:
          return "getProcessLimit";
        case 43:
          return "setProcessLimit";
        case 42:
          return "stopServiceToken";
        case 41:
          return "updateMccMncConfiguration";
        case 40:
          return "updateConfiguration";
        case 39:
          return "getConfiguration";
        case 38:
          return "finishInstrumentation";
        case 37:
          return "addInstrumentationResults";
        case 36:
          return "startInstrumentation";
        case 35:
          return "setAlwaysFinish";
        case 34:
          return "setAgentApp";
        case 33:
          return "setDebugApp";
        case 32:
          return "publishService";
        case 31:
          return "unbindService";
        case 30:
          return "updateServiceGroup";
        case 29:
          return "bindIsolatedService";
        case 28:
          return "bindService";
        case 27:
          return "stopService";
        case 26:
          return "startService";
        case 25:
          return "getRunningServiceControlPanel";
        case 24:
          return "refContentProvider";
        case 23:
          return "publishContentProviders";
        case 22:
          return "getContentProvider";
        case 21:
          return "getTaskForActivity";
        case 20:
          return "moveTaskToFront";
        case 19:
          return "getTasks";
        case 18:
          return "attachApplication";
        case 17:
          return "finishReceiver";
        case 16:
          return "unbroadcastIntent";
        case 15:
          return "broadcastIntentWithFeature";
        case 14:
          return "broadcastIntent";
        case 13:
          return "unregisterReceiver";
        case 12:
          return "registerReceiverWithFeature";
        case 11:
          return "registerReceiver";
        case 10:
          return "finishActivity";
        case 9:
          return "unhandledBack";
        case 8:
          return "startActivityWithFeature";
        case 7:
          return "startActivity";
        case 6:
          return "handleApplicationCrash";
        case 5:
          return "getUidProcessState";
        case 4:
          return "isUidActive";
        case 3:
          return "unregisterUidObserver";
        case 2:
          return "registerUidObserver";
        case 1:
          break;
      } 
      return "openContentUri";
    }
    
    public static boolean setDefaultImpl(IActivityManager param1IActivityManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IActivityManager != null) {
          Proxy.sDefaultImpl = param1IActivityManager;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      IBinder iBinder;
      if (param1Int1 != 1598968902) {
        boolean bool20;
        int i14;
        boolean bool19;
        int i13;
        boolean bool18;
        int i12;
        boolean bool17;
        int i11;
        boolean bool16;
        int i10;
        boolean bool15;
        int i9;
        boolean bool14;
        int i8;
        boolean bool13;
        int i7;
        boolean bool12;
        int i6;
        boolean bool11;
        int i5;
        boolean bool10;
        int i4;
        boolean bool9;
        int i3;
        boolean bool8;
        int i2;
        boolean bool7;
        int i1;
        boolean bool6;
        int n;
        boolean bool5;
        int m;
        boolean bool4;
        int k;
        boolean bool3;
        int j;
        boolean bool2;
        int i;
        boolean bool1;
        ParceledListSlice<ApplicationExitInfo> parceledListSlice1;
        ParcelFileDescriptor parcelFileDescriptor2;
        ActivityManager.TaskSnapshot taskSnapshot;
        String str5;
        Rect rect;
        ActivityManager.StackInfo stackInfo;
        List<ActivityManager.StackInfo> list4;
        String str4;
        Intent intent2;
        List<String> list3;
        int[] arrayOfInt1;
        UserInfo userInfo;
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo;
        ContentProviderHolder contentProviderHolder2;
        long[] arrayOfLong;
        String str3;
        List<ApplicationInfo> list2;
        Debug.MemoryInfo[] arrayOfMemoryInfo;
        IBinder iBinder3;
        List<ActivityManager.RunningAppProcessInfo> list1;
        ActivityManager.MemoryInfo memoryInfo;
        String str2;
        IIntentSender iIntentSender1;
        IBinder iBinder2;
        String str1;
        IBinder iBinder1;
        ParceledListSlice parceledListSlice;
        Configuration configuration;
        ComponentName componentName1;
        PendingIntent pendingIntent;
        ContentProviderHolder contentProviderHolder1;
        List<ActivityManager.RunningTaskInfo> list;
        Intent intent1;
        String str8;
        IBinder iBinder5;
        String str7;
        IBinder iBinder4;
        String str6;
        IApplicationThread iApplicationThread1;
        IBinder iBinder10;
        IIntentSender iIntentSender4;
        String str11;
        int[] arrayOfInt2;
        IBinder iBinder9;
        IIntentSender iIntentSender3;
        String[] arrayOfString1;
        IApplicationThread iApplicationThread4;
        IBinder iBinder8;
        IApplicationThread iApplicationThread3;
        String str10;
        IBinder iBinder7;
        IApplicationThread iApplicationThread2;
        String str9;
        IIntentReceiver iIntentReceiver1;
        IBinder iBinder6;
        ComponentName componentName2;
        IBinder iBinder15;
        String str15;
        IBinder iBinder14;
        String str14;
        IBinder iBinder13;
        IIntentSender iIntentSender5;
        IApplicationThread iApplicationThread8;
        IActivityController iActivityController;
        IApplicationThread iApplicationThread7;
        String str13;
        IApplicationThread iApplicationThread6;
        IBinder iBinder12;
        IApplicationThread iApplicationThread5;
        String str12;
        IBinder iBinder11;
        String str18;
        Intent[] arrayOfIntent1;
        String str17;
        IApplicationThread iApplicationThread9;
        String str16;
        IIntentReceiver iIntentReceiver2;
        String str20;
        Intent[] arrayOfIntent2;
        String str19;
        String str23;
        IApplicationThread iApplicationThread11;
        String[] arrayOfString2;
        IBinder iBinder16;
        IApplicationThread iApplicationThread10;
        String str22;
        IIntentReceiver iIntentReceiver3;
        String str21;
        String str26;
        IBinder iBinder18;
        String str25;
        IIntentReceiver iIntentReceiver4;
        String str24;
        IBinder iBinder17;
        IBinder iBinder20;
        String str27;
        IIntentReceiver iIntentReceiver5;
        String[] arrayOfString3;
        IBinder iBinder19;
        String str28;
        String[] arrayOfString4;
        int i15;
        IIntentSender iIntentSender2 = null;
        LocusId locusId = null;
        boolean bool21 = false;
        boolean bool22 = false;
        boolean bool23 = false;
        boolean bool24 = false;
        boolean bool25 = false;
        boolean bool26 = false;
        boolean bool27 = false;
        boolean bool28 = false;
        boolean bool29 = false;
        boolean bool30 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 217:
            param1Parcel1.enforceInterface("android.app.IActivityManager");
            if (param1Parcel1.readInt() != 0)
              bool30 = true; 
            bool20 = enableAppFreezer(bool30);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool20);
            return true;
          case 216:
            param1Parcel1.enforceInterface("android.app.IActivityManager");
            killUidForPermissionChange(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readString());
            param1Parcel2.writeNoException();
            return true;
          case 215:
            param1Parcel1.enforceInterface("android.app.IActivityManager");
            bool20 = isAppFreezerSupported();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool20);
            return true;
          case 214:
            param1Parcel1.enforceInterface("android.app.IActivityManager");
            setProcessStateSummary(param1Parcel1.createByteArray());
            param1Parcel2.writeNoException();
            return true;
          case 213:
            param1Parcel1.enforceInterface("android.app.IActivityManager");
            if (param1Parcel1.readInt() != 0) {
              componentName2 = (ComponentName)ComponentName.CREATOR.createFromParcel(param1Parcel1);
            } else {
              componentName2 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              locusId = (LocusId)LocusId.CREATOR.createFromParcel(param1Parcel1);
            } else {
              locusId = null;
            } 
            setActivityLocusContext(componentName2, locusId, param1Parcel1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 212:
            param1Parcel1.enforceInterface("android.app.IActivityManager");
            killProcessesWhenImperceptible(param1Parcel1.createIntArray(), param1Parcel1.readString());
            param1Parcel2.writeNoException();
            return true;
          case 211:
            param1Parcel1.enforceInterface("android.app.IActivityManager");
            parceledListSlice1 = getHistoricalProcessExitReasons(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            if (parceledListSlice1 != null) {
              param1Parcel2.writeInt(1);
              parceledListSlice1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 210:
            parceledListSlice1.enforceInterface("android.app.IActivityManager");
            appNotResponding(parceledListSlice1.readString());
            param1Parcel2.writeNoException();
            return true;
          case 209:
            parceledListSlice1.enforceInterface("android.app.IActivityManager");
            bool20 = startUserInForegroundWithListener(parceledListSlice1.readInt(), IProgressListener.Stub.asInterface(parceledListSlice1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool20);
            return true;
          case 208:
            parceledListSlice1.enforceInterface("android.app.IActivityManager");
            parcelFileDescriptor2 = getLifeMonitor();
            param1Parcel2.writeNoException();
            if (parcelFileDescriptor2 != null) {
              param1Parcel2.writeInt(1);
              parcelFileDescriptor2.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 207:
            parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
            stopDelegateShellPermissionIdentity();
            param1Parcel2.writeNoException();
            return true;
          case 206:
            parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
            startDelegateShellPermissionIdentity(parcelFileDescriptor2.readInt(), parcelFileDescriptor2.createStringArray());
            param1Parcel2.writeNoException();
            return true;
          case 205:
            parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
            bool20 = startUserInBackgroundWithListener(parcelFileDescriptor2.readInt(), IProgressListener.Stub.asInterface(parcelFileDescriptor2.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool20);
            return true;
          case 204:
            parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
            backgroundWhitelistUid(parcelFileDescriptor2.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 203:
            parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
            waitForNetworkStateUpdate(parcelFileDescriptor2.readLong());
            param1Parcel2.writeNoException();
            return true;
          case 202:
            parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
            setPersistentVrThread(parcelFileDescriptor2.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 201:
            parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
            scheduleApplicationInfoChanged(parcelFileDescriptor2.createStringArrayList(), parcelFileDescriptor2.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 200:
            parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
            i14 = parcelFileDescriptor2.readInt();
            if (parcelFileDescriptor2.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            taskSnapshot = getTaskSnapshot(i14, bool30);
            param1Parcel2.writeNoException();
            if (taskSnapshot != null) {
              param1Parcel2.writeInt(1);
              taskSnapshot.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 199:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            cancelTaskWindowTransition(taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 198:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            i14 = restartUserInBackground(taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i14);
            return true;
          case 197:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            bool30 = bool21;
            if (taskSnapshot.readInt() != 0)
              bool30 = true; 
            setHasTopUi(bool30);
            param1Parcel2.writeNoException();
            return true;
          case 196:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            setRenderThread(taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 195:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            bool19 = isBackgroundRestricted(taskSnapshot.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool19);
            return true;
          case 194:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            iIntentSender2 = IIntentSender.Stub.asInterface(taskSnapshot.readStrongBinder());
            iBinder10 = taskSnapshot.readStrongBinder();
            i13 = taskSnapshot.readInt();
            if (taskSnapshot.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              componentName2 = null;
            } 
            str18 = taskSnapshot.readString();
            iIntentReceiver2 = IIntentReceiver.Stub.asInterface(taskSnapshot.readStrongBinder());
            str23 = taskSnapshot.readString();
            if (taskSnapshot.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              taskSnapshot = null;
            } 
            i13 = sendIntentSender(iIntentSender2, iBinder10, i13, (Intent)componentName2, str18, iIntentReceiver2, str23, (Bundle)taskSnapshot);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i13);
            return true;
          case 193:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            sendIdleJobTrigger();
            param1Parcel2.writeNoException();
            return true;
          case 192:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            if (taskSnapshot.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              componentName2 = null;
            } 
            if (taskSnapshot.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              taskSnapshot = null;
            } 
            startConfirmDeviceCredentialIntent((Intent)componentName2, (Bundle)taskSnapshot);
            param1Parcel2.writeNoException();
            return true;
          case 191:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            notifyLockedProfile(taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 190:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            if (taskSnapshot.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              taskSnapshot = null;
            } 
            bool18 = isVrModePackageEnabled((ComponentName)taskSnapshot);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool18);
            return true;
          case 189:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            i12 = getMemoryTrimLevel();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i12);
            return true;
          case 188:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            makePackageIdle(taskSnapshot.readString(), taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 187:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            removeStack(taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 186:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            killPackageDependents(taskSnapshot.readString(), taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 185:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            bool17 = unlockUser(taskSnapshot.readInt(), taskSnapshot.createByteArray(), taskSnapshot.createByteArray(), IProgressListener.Stub.asInterface(taskSnapshot.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool17);
            return true;
          case 184:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            bool17 = isAppStartModeDisabled(taskSnapshot.readInt(), taskSnapshot.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool17);
            return true;
          case 183:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            i11 = taskSnapshot.readInt();
            if (taskSnapshot.readInt() != 0) {
              Rect rect1 = (Rect)Rect.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              taskSnapshot = null;
            } 
            bool16 = moveTopActivityToPinnedStack(i11, (Rect)taskSnapshot);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool16);
            return true;
          case 182:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            bool30 = bool22;
            if (taskSnapshot.readInt() != 0)
              bool30 = true; 
            suppressResizeConfigChanges(bool30);
            param1Parcel2.writeNoException();
            return true;
          case 181:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            positionTaskInStack(taskSnapshot.readInt(), taskSnapshot.readInt(), taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 180:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            if (taskSnapshot.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              taskSnapshot = null;
            } 
            bool16 = stopBinderTrackingAndDump((ParcelFileDescriptor)taskSnapshot);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool16);
            return true;
          case 179:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            bool16 = startBinderTracking();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool16);
            return true;
          case 178:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            updateDeviceOwner(taskSnapshot.readString());
            param1Parcel2.writeNoException();
            return true;
          case 177:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            i10 = getPackageProcessState(taskSnapshot.readString(), taskSnapshot.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i10);
            return true;
          case 176:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            iIntentSender4 = IIntentSender.Stub.asInterface(taskSnapshot.readStrongBinder());
            if (taskSnapshot.readInt() != 0) {
              WorkSource workSource = (WorkSource)WorkSource.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              componentName2 = null;
            } 
            noteAlarmFinish(iIntentSender4, (WorkSource)componentName2, taskSnapshot.readInt(), taskSnapshot.readString());
            param1Parcel2.writeNoException();
            return true;
          case 175:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            iIntentSender4 = IIntentSender.Stub.asInterface(taskSnapshot.readStrongBinder());
            if (taskSnapshot.readInt() != 0) {
              WorkSource workSource = (WorkSource)WorkSource.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              componentName2 = null;
            } 
            noteAlarmStart(iIntentSender4, (WorkSource)componentName2, taskSnapshot.readInt(), taskSnapshot.readString());
            param1Parcel2.writeNoException();
            return true;
          case 174:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            updateLockTaskPackages(taskSnapshot.readInt(), taskSnapshot.createStringArray());
            param1Parcel2.writeNoException();
            return true;
          case 173:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            dumpHeapFinished(taskSnapshot.readString());
            param1Parcel2.writeNoException();
            return true;
          case 172:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            setDumpHeapDebugLimit(taskSnapshot.readString(), taskSnapshot.readInt(), taskSnapshot.readLong(), taskSnapshot.readString());
            param1Parcel2.writeNoException();
            return true;
          case 171:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            i10 = getLockTaskModeState();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i10);
            return true;
          case 170:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            i10 = taskSnapshot.readInt();
            if (taskSnapshot.readInt() != 0) {
              Rect rect1 = (Rect)Rect.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              componentName2 = null;
            } 
            resizeTask(i10, (Rect)componentName2, taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 169:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            setTaskResizeable(taskSnapshot.readInt(), taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 168:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            notifyCleartextNetwork(taskSnapshot.readInt(), taskSnapshot.createByteArray());
            param1Parcel2.writeNoException();
            return true;
          case 167:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            unregisterTaskStackListener(ITaskStackListener.Stub.asInterface(taskSnapshot.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 166:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            registerTaskStackListener(ITaskStackListener.Stub.asInterface(taskSnapshot.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 165:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            i10 = checkPermissionWithToken(taskSnapshot.readString(), taskSnapshot.readInt(), taskSnapshot.readInt(), taskSnapshot.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i10);
            return true;
          case 164:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            bootAnimationComplete();
            param1Parcel2.writeNoException();
            return true;
          case 163:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            bool15 = isTopOfTask(taskSnapshot.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool15);
            return true;
          case 162:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            startSystemLockTaskMode(taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 161:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            i9 = taskSnapshot.readInt();
            if (taskSnapshot.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              taskSnapshot = null;
            } 
            i9 = startActivityFromRecents(i9, (Bundle)taskSnapshot);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i9);
            return true;
          case 160:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            bool30 = bool23;
            if (taskSnapshot.readInt() != 0)
              bool30 = true; 
            cancelRecentsAnimation(bool30);
            param1Parcel2.writeNoException();
            return true;
          case 159:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            if (taskSnapshot.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              componentName2 = null;
            } 
            startRecentsActivity((Intent)componentName2, IAssistDataReceiver.Stub.asInterface(taskSnapshot.readStrongBinder()), IRecentsAnimationRunner.Stub.asInterface(taskSnapshot.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 158:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            bool14 = isInLockTaskMode();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool14);
            return true;
          case 157:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            bool14 = startUserInBackground(taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool14);
            return true;
          case 156:
            taskSnapshot.enforceInterface("android.app.IActivityManager");
            str5 = getTagForIntentSender(IIntentSender.Stub.asInterface(taskSnapshot.readStrongBinder()), taskSnapshot.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str5);
            return true;
          case 155:
            str5.enforceInterface("android.app.IActivityManager");
            bool14 = setProcessMemoryTrimLevel(str5.readString(), str5.readInt(), str5.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool14);
            return true;
          case 154:
            str5.enforceInterface("android.app.IActivityManager");
            rect = getTaskBounds(str5.readInt());
            param1Parcel2.writeNoException();
            if (rect != null) {
              param1Parcel2.writeInt(1);
              rect.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 153:
            rect.enforceInterface("android.app.IActivityManager");
            appNotRespondingViaProvider(rect.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 152:
            rect.enforceInterface("android.app.IActivityManager");
            performIdleMaintenance();
            param1Parcel2.writeNoException();
            return true;
          case 151:
            rect.enforceInterface("android.app.IActivityManager");
            restart();
            param1Parcel2.writeNoException();
            return true;
          case 150:
            rect.enforceInterface("android.app.IActivityManager");
            stackInfo = getFocusedStackInfo();
            param1Parcel2.writeNoException();
            if (stackInfo != null) {
              param1Parcel2.writeInt(1);
              stackInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 149:
            stackInfo.enforceInterface("android.app.IActivityManager");
            setFocusedStack(stackInfo.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 148:
            stackInfo.enforceInterface("android.app.IActivityManager");
            i8 = stackInfo.readInt();
            param1Int2 = stackInfo.readInt();
            bool30 = bool24;
            if (stackInfo.readInt() != 0)
              bool30 = true; 
            moveTaskToStack(i8, param1Int2, bool30);
            param1Parcel2.writeNoException();
            return true;
          case 147:
            stackInfo.enforceInterface("android.app.IActivityManager");
            list4 = getAllStackInfos();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list4);
            return true;
          case 146:
            list4.enforceInterface("android.app.IActivityManager");
            iBinder15 = list4.readStrongBinder();
            bool30 = bool25;
            if (list4.readInt() != 0)
              bool30 = true; 
            hang(iBinder15, bool30);
            param1Parcel2.writeNoException();
            return true;
          case 145:
            list4.enforceInterface("android.app.IActivityManager");
            bool30 = bool26;
            if (list4.readInt() != 0)
              bool30 = true; 
            setUserIsMonkey(bool30);
            param1Parcel2.writeNoException();
            return true;
          case 144:
            list4.enforceInterface("android.app.IActivityManager");
            killUid(list4.readInt(), list4.readInt(), list4.readString());
            param1Parcel2.writeNoException();
            return true;
          case 143:
            list4.enforceInterface("android.app.IActivityManager");
            str4 = getLaunchedFromPackage(list4.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str4);
            return true;
          case 142:
            str4.enforceInterface("android.app.IActivityManager");
            intent2 = getIntentForIntentSender(IIntentSender.Stub.asInterface(str4.readStrongBinder()));
            param1Parcel2.writeNoException();
            if (intent2 != null) {
              param1Parcel2.writeInt(1);
              intent2.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 141:
            intent2.enforceInterface("android.app.IActivityManager");
            list3 = getBugreportWhitelistedPackages();
            param1Parcel2.writeNoException();
            param1Parcel2.writeStringList(list3);
            return true;
          case 140:
            list3.enforceInterface("android.app.IActivityManager");
            bool13 = launchBugReportHandlerApp();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool13);
            return true;
          case 139:
            list3.enforceInterface("android.app.IActivityManager");
            requestRemoteBugReport();
            param1Parcel2.writeNoException();
            return true;
          case 138:
            list3.enforceInterface("android.app.IActivityManager");
            requestFullBugReport();
            param1Parcel2.writeNoException();
            return true;
          case 137:
            list3.enforceInterface("android.app.IActivityManager");
            requestInteractiveBugReport();
            param1Parcel2.writeNoException();
            return true;
          case 136:
            list3.enforceInterface("android.app.IActivityManager");
            requestInteractiveBugReportWithDescription(list3.readString(), list3.readString());
            param1Parcel2.writeNoException();
            return true;
          case 135:
            list3.enforceInterface("android.app.IActivityManager");
            requestWifiBugReport(list3.readString(), list3.readString());
            param1Parcel2.writeNoException();
            return true;
          case 134:
            list3.enforceInterface("android.app.IActivityManager");
            requestTelephonyBugReport(list3.readString(), list3.readString());
            param1Parcel2.writeNoException();
            return true;
          case 133:
            list3.enforceInterface("android.app.IActivityManager");
            requestBugReportWithDescription(list3.readString(), list3.readString(), list3.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 132:
            list3.enforceInterface("android.app.IActivityManager");
            requestBugReport(list3.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 131:
            list3.enforceInterface("android.app.IActivityManager");
            requestSystemServerHeapDump();
            param1Parcel2.writeNoException();
            return true;
          case 130:
            list3.enforceInterface("android.app.IActivityManager");
            arrayOfInt1 = getRunningUserIds();
            param1Parcel2.writeNoException();
            param1Parcel2.writeIntArray(arrayOfInt1);
            return true;
          case 129:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            unregisterUserSwitchObserver(IUserSwitchObserver.Stub.asInterface(arrayOfInt1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 128:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            registerUserSwitchObserver(IUserSwitchObserver.Stub.asInterface(arrayOfInt1.readStrongBinder()), arrayOfInt1.readString());
            param1Parcel2.writeNoException();
            return true;
          case 127:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            i7 = arrayOfInt1.readInt();
            bool30 = bool27;
            if (arrayOfInt1.readInt() != 0)
              bool30 = true; 
            i7 = stopUserWithDelayedLocking(i7, bool30, IStopUserCallback.Stub.asInterface(arrayOfInt1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i7);
            return true;
          case 126:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            i7 = arrayOfInt1.readInt();
            bool30 = bool28;
            if (arrayOfInt1.readInt() != 0)
              bool30 = true; 
            i7 = stopUser(i7, bool30, IStopUserCallback.Stub.asInterface(arrayOfInt1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i7);
            return true;
          case 125:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            iApplicationThread11 = IApplicationThread.Stub.asInterface(arrayOfInt1.readStrongBinder());
            str20 = arrayOfInt1.readString();
            str18 = arrayOfInt1.readString();
            if (arrayOfInt1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)arrayOfInt1);
            } else {
              iBinder15 = null;
            } 
            str26 = arrayOfInt1.readString();
            iBinder20 = arrayOfInt1.readStrongBinder();
            str28 = arrayOfInt1.readString();
            i7 = arrayOfInt1.readInt();
            param1Int2 = arrayOfInt1.readInt();
            if (arrayOfInt1.readInt() != 0) {
              ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel((Parcel)arrayOfInt1);
            } else {
              iIntentSender4 = null;
            } 
            if (arrayOfInt1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)arrayOfInt1);
            } else {
              iIntentSender2 = null;
            } 
            i7 = startActivityAsUserWithFeature(iApplicationThread11, str20, str18, (Intent)iBinder15, str26, iBinder20, str28, i7, param1Int2, (ProfilerInfo)iIntentSender4, (Bundle)iIntentSender2, arrayOfInt1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i7);
            return true;
          case 124:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            iApplicationThread11 = IApplicationThread.Stub.asInterface(arrayOfInt1.readStrongBinder());
            str18 = arrayOfInt1.readString();
            if (arrayOfInt1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)arrayOfInt1);
            } else {
              iBinder15 = null;
            } 
            str20 = arrayOfInt1.readString();
            iBinder20 = arrayOfInt1.readStrongBinder();
            str26 = arrayOfInt1.readString();
            param1Int2 = arrayOfInt1.readInt();
            i7 = arrayOfInt1.readInt();
            if (arrayOfInt1.readInt() != 0) {
              ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel((Parcel)arrayOfInt1);
            } else {
              iIntentSender4 = null;
            } 
            if (arrayOfInt1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)arrayOfInt1);
            } else {
              iIntentSender2 = null;
            } 
            i7 = startActivityAsUser(iApplicationThread11, str18, (Intent)iBinder15, str20, iBinder20, str26, param1Int2, i7, (ProfilerInfo)iIntentSender4, (Bundle)iIntentSender2, arrayOfInt1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i7);
            return true;
          case 123:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            bool12 = isIntentSenderABroadcast(IIntentSender.Stub.asInterface(arrayOfInt1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool12);
            return true;
          case 122:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            bool12 = isIntentSenderAForegroundService(IIntentSender.Stub.asInterface(arrayOfInt1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool12);
            return true;
          case 121:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            bool12 = isIntentSenderAnActivity(IIntentSender.Stub.asInterface(arrayOfInt1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool12);
            return true;
          case 120:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            unstableProviderDied(arrayOfInt1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 119:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            i6 = getLaunchedFromUid(arrayOfInt1.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i6);
            return true;
          case 118:
            arrayOfInt1.enforceInterface("android.app.IActivityManager");
            userInfo = getCurrentUser();
            param1Parcel2.writeNoException();
            if (userInfo != null) {
              param1Parcel2.writeInt(1);
              userInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 117:
            userInfo.enforceInterface("android.app.IActivityManager");
            bool11 = killProcessesBelowForeground(userInfo.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool11);
            return true;
          case 116:
            userInfo.enforceInterface("android.app.IActivityManager");
            runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            getMyMemoryState(runningAppProcessInfo);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(1);
            runningAppProcessInfo.writeToParcel(param1Parcel2, 1);
            return true;
          case 115:
            runningAppProcessInfo.enforceInterface("android.app.IActivityManager");
            removeContentProviderExternalAsUser(runningAppProcessInfo.readString(), runningAppProcessInfo.readStrongBinder(), runningAppProcessInfo.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 114:
            runningAppProcessInfo.enforceInterface("android.app.IActivityManager");
            removeContentProviderExternal(runningAppProcessInfo.readString(), runningAppProcessInfo.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 113:
            runningAppProcessInfo.enforceInterface("android.app.IActivityManager");
            contentProviderHolder2 = getContentProviderExternal(runningAppProcessInfo.readString(), runningAppProcessInfo.readInt(), runningAppProcessInfo.readStrongBinder(), runningAppProcessInfo.readString());
            param1Parcel2.writeNoException();
            if (contentProviderHolder2 != null) {
              param1Parcel2.writeInt(1);
              contentProviderHolder2.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 112:
            contentProviderHolder2.enforceInterface("android.app.IActivityManager");
            killAllBackgroundProcesses();
            param1Parcel2.writeNoException();
            return true;
          case 111:
            contentProviderHolder2.enforceInterface("android.app.IActivityManager");
            if (contentProviderHolder2.readInt() != 0) {
              CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)contentProviderHolder2);
            } else {
              iBinder15 = null;
            } 
            if (contentProviderHolder2.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            showBootMessage((CharSequence)iBinder15, bool30);
            param1Parcel2.writeNoException();
            return true;
          case 110:
            contentProviderHolder2.enforceInterface("android.app.IActivityManager");
            arrayOfLong = getProcessPss(contentProviderHolder2.createIntArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeLongArray(arrayOfLong);
            return true;
          case 109:
            arrayOfLong.enforceInterface("android.app.IActivityManager");
            if (arrayOfLong.readInt() != 0) {
              Configuration configuration1 = (Configuration)Configuration.CREATOR.createFromParcel((Parcel)arrayOfLong);
            } else {
              arrayOfLong = null;
            } 
            updatePersistentConfiguration((Configuration)arrayOfLong);
            param1Parcel2.writeNoException();
            return true;
          case 108:
            arrayOfLong.enforceInterface("android.app.IActivityManager");
            bool11 = isIntentSenderTargetedToPackage(IIntentSender.Stub.asInterface(arrayOfLong.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool11);
            return true;
          case 107:
            arrayOfLong.enforceInterface("android.app.IActivityManager");
            unregisterProcessObserver(IProcessObserver.Stub.asInterface(arrayOfLong.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 106:
            arrayOfLong.enforceInterface("android.app.IActivityManager");
            registerProcessObserver(IProcessObserver.Stub.asInterface(arrayOfLong.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 105:
            arrayOfLong.enforceInterface("android.app.IActivityManager");
            bool11 = removeTask(arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool11);
            return true;
          case 104:
            arrayOfLong.enforceInterface("android.app.IActivityManager");
            bool11 = switchUser(arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool11);
            return true;
          case 103:
            arrayOfLong.enforceInterface("android.app.IActivityManager");
            setPackageScreenCompatMode(arrayOfLong.readString(), arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 102:
            arrayOfLong.enforceInterface("android.app.IActivityManager");
            bool11 = isUserRunning(arrayOfLong.readInt(), arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool11);
            return true;
          case 101:
            arrayOfLong.enforceInterface("android.app.IActivityManager");
            str8 = arrayOfLong.readString();
            i5 = arrayOfLong.readInt();
            if (arrayOfLong.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            if (arrayOfLong.readInt() != 0) {
              bool29 = true;
            } else {
              bool29 = false;
            } 
            if (arrayOfLong.readInt() != 0) {
              bool21 = true;
            } else {
              bool21 = false;
            } 
            str11 = arrayOfLong.readString();
            if (arrayOfLong.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel((Parcel)arrayOfLong);
            } else {
              iBinder15 = null;
            } 
            if (arrayOfLong.readInt() != 0) {
              RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel((Parcel)arrayOfLong);
            } else {
              arrayOfLong = null;
            } 
            bool10 = dumpHeap(str8, i5, bool30, bool29, bool21, str11, (ParcelFileDescriptor)iBinder15, (RemoteCallback)arrayOfLong);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool10);
            return true;
          case 100:
            arrayOfLong.enforceInterface("android.app.IActivityManager");
            if (arrayOfLong.readInt() != 0) {
              Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)arrayOfLong);
            } else {
              param1Parcel2 = null;
            } 
            i4 = arrayOfLong.readInt();
            if (arrayOfLong.readInt() != 0) {
              RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel((Parcel)arrayOfLong);
            } else {
              arrayOfLong = null;
            } 
            getProviderMimeTypeAsync((Uri)param1Parcel2, i4, (RemoteCallback)arrayOfLong);
            return true;
          case 99:
            arrayOfLong.enforceInterface("android.app.IActivityManager");
            if (arrayOfLong.readInt() != 0) {
              Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)arrayOfLong);
            } else {
              iBinder15 = null;
            } 
            str3 = getProviderMimeType((Uri)iBinder15, arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str3);
            return true;
          case 98:
            str3.enforceInterface("android.app.IActivityManager");
            i15 = str3.readInt();
            i4 = str3.readInt();
            str15 = str3.readString();
            param1Int2 = str3.readInt();
            str11 = str3.readString();
            if (str3.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            crashApplication(i15, i4, str15, param1Int2, str11, bool30);
            param1Parcel2.writeNoException();
            return true;
          case 97:
            str3.enforceInterface("android.app.IActivityManager");
            bool9 = isTopActivityImmersive();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool9);
            return true;
          case 96:
            str3.enforceInterface("android.app.IActivityManager");
            iBinder14 = str3.readStrongBinder();
            i3 = str3.readInt();
            if (str3.readInt() != 0) {
              StrictMode.ViolationInfo violationInfo = (StrictMode.ViolationInfo)StrictMode.ViolationInfo.CREATOR.createFromParcel((Parcel)str3);
            } else {
              str3 = null;
            } 
            handleApplicationStrictModeViolation(iBinder14, i3, (StrictMode.ViolationInfo)str3);
            param1Parcel2.writeNoException();
            return true;
          case 95:
            str3.enforceInterface("android.app.IActivityManager");
            finishHeavyWeightApp();
            param1Parcel2.writeNoException();
            return true;
          case 94:
            str3.enforceInterface("android.app.IActivityManager");
            list2 = getRunningExternalApplications();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list2);
            return true;
          case 93:
            list2.enforceInterface("android.app.IActivityManager");
            bool8 = isUserAMonkey();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool8);
            return true;
          case 92:
            list2.enforceInterface("android.app.IActivityManager");
            killBackgroundProcesses(list2.readString(), list2.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 91:
            list2.enforceInterface("android.app.IActivityManager");
            iBinder5 = list2.readStrongBinder();
            str11 = list2.readString();
            if (list2.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            if (list2.readInt() != 0) {
              ApplicationErrorReport.ParcelableCrashInfo parcelableCrashInfo = (ApplicationErrorReport.ParcelableCrashInfo)ApplicationErrorReport.ParcelableCrashInfo.CREATOR.createFromParcel((Parcel)list2);
            } else {
              iBinder14 = null;
            } 
            bool8 = handleApplicationWtf(iBinder5, str11, bool30, (ApplicationErrorReport.ParcelableCrashInfo)iBinder14, list2.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool8);
            return true;
          case 90:
            list2.enforceInterface("android.app.IActivityManager");
            killApplicationProcess(list2.readString(), list2.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 89:
            list2.enforceInterface("android.app.IActivityManager");
            arrayOfMemoryInfo = getProcessMemoryInfo(list2.createIntArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedArray((Parcelable[])arrayOfMemoryInfo, 1);
            return true;
          case 88:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            closeSystemDialogs(arrayOfMemoryInfo.readString());
            param1Parcel2.writeNoException();
            return true;
          case 87:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            killApplication(arrayOfMemoryInfo.readString(), arrayOfMemoryInfo.readInt(), arrayOfMemoryInfo.readInt(), arrayOfMemoryInfo.readString());
            param1Parcel2.writeNoException();
            return true;
          case 86:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            addPackageDependency(arrayOfMemoryInfo.readString());
            param1Parcel2.writeNoException();
            return true;
          case 85:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            i2 = arrayOfMemoryInfo.readInt();
            param1Int2 = arrayOfMemoryInfo.readInt();
            i15 = arrayOfMemoryInfo.readInt();
            if (arrayOfMemoryInfo.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            if (arrayOfMemoryInfo.readInt() != 0) {
              bool29 = true;
            } else {
              bool29 = false;
            } 
            i2 = handleIncomingUser(i2, param1Int2, i15, bool30, bool29, arrayOfMemoryInfo.readString(), arrayOfMemoryInfo.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i2);
            return true;
          case 84:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            i2 = getUidForIntentSender(IIntentSender.Stub.asInterface(arrayOfMemoryInfo.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i2);
            return true;
          case 83:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            if (arrayOfMemoryInfo.readInt() != 0) {
              ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel((Parcel)arrayOfMemoryInfo);
            } else {
              arrayOfMemoryInfo = null;
            } 
            unbindBackupAgent((ApplicationInfo)arrayOfMemoryInfo);
            param1Parcel2.writeNoException();
            return true;
          case 82:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            backupAgentCreated(arrayOfMemoryInfo.readString(), arrayOfMemoryInfo.readStrongBinder(), arrayOfMemoryInfo.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 81:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            bool7 = bindBackupAgent(arrayOfMemoryInfo.readString(), arrayOfMemoryInfo.readInt(), arrayOfMemoryInfo.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool7);
            return true;
          case 80:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            resumeAppSwitches();
            param1Parcel2.writeNoException();
            return true;
          case 79:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            stopAppSwitches();
            param1Parcel2.writeNoException();
            return true;
          case 78:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            bool7 = shutdown(arrayOfMemoryInfo.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool7);
            return true;
          case 77:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            str11 = arrayOfMemoryInfo.readString();
            i1 = arrayOfMemoryInfo.readInt();
            if (arrayOfMemoryInfo.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            if (arrayOfMemoryInfo.readInt() != 0) {
              ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel((Parcel)arrayOfMemoryInfo);
            } else {
              iBinder14 = null;
            } 
            bool6 = profileControl(str11, i1, bool30, (ProfilerInfo)iBinder14, arrayOfMemoryInfo.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool6);
            return true;
          case 76:
            arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
            if (arrayOfMemoryInfo.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)arrayOfMemoryInfo);
            } else {
              iBinder14 = null;
            } 
            iBinder3 = peekService((Intent)iBinder14, arrayOfMemoryInfo.readString(), arrayOfMemoryInfo.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeStrongBinder(iBinder3);
            return true;
          case 75:
            iBinder3.enforceInterface("android.app.IActivityManager");
            list1 = getRunningAppProcesses();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list1);
            return true;
          case 74:
            list1.enforceInterface("android.app.IActivityManager");
            list1 = (List)getServices(list1.readInt(), list1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list1);
            return true;
          case 73:
            list1.enforceInterface("android.app.IActivityManager");
            arrayOfInt2 = list1.createIntArray();
            str14 = list1.readString();
            if (list1.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            bool6 = killPids(arrayOfInt2, str14, bool30);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool6);
            return true;
          case 72:
            list1.enforceInterface("android.app.IActivityManager");
            forceStopPackage(list1.readString(), list1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 71:
            list1.enforceInterface("android.app.IActivityManager");
            str14 = list1.readString();
            if (list1.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            bool6 = clearApplicationUserData(str14, bool30, IPackageDataObserver.Stub.asInterface(list1.readStrongBinder()), list1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool6);
            return true;
          case 70:
            list1.enforceInterface("android.app.IActivityManager");
            list1 = (List)getProcessesInErrorState();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list1);
            return true;
          case 69:
            list1.enforceInterface("android.app.IActivityManager");
            memoryInfo = new ActivityManager.MemoryInfo();
            getMemoryInfo(memoryInfo);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(1);
            memoryInfo.writeToParcel(param1Parcel2, 1);
            return true;
          case 68:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            iBinder13 = memoryInfo.readStrongBinder();
            if (memoryInfo.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            bool6 = moveActivityTaskToBack(iBinder13, bool30);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool6);
            return true;
          case 67:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            if (memoryInfo.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)memoryInfo);
            } else {
              iBinder13 = null;
            } 
            n = getForegroundServiceType((ComponentName)iBinder13, memoryInfo.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(n);
            return true;
          case 66:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            if (memoryInfo.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)memoryInfo);
            } else {
              iBinder13 = null;
            } 
            iBinder5 = memoryInfo.readStrongBinder();
            n = memoryInfo.readInt();
            if (memoryInfo.readInt() != 0) {
              Notification notification = (Notification)Notification.CREATOR.createFromParcel((Parcel)memoryInfo);
            } else {
              arrayOfInt2 = null;
            } 
            setServiceForeground((ComponentName)iBinder13, iBinder5, n, (Notification)arrayOfInt2, memoryInfo.readInt(), memoryInfo.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 65:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            iBinder13 = memoryInfo.readStrongBinder();
            n = memoryInfo.readInt();
            if (memoryInfo.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            setProcessImportant(iBinder13, n, bool30, memoryInfo.readString());
            param1Parcel2.writeNoException();
            return true;
          case 64:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            iBinder9 = memoryInfo.readStrongBinder();
            if (memoryInfo.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)memoryInfo);
            } else {
              iBinder13 = null;
            } 
            if (memoryInfo.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            unbindFinished(iBinder9, (Intent)iBinder13, bool30);
            param1Parcel2.writeNoException();
            return true;
          case 63:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            setRequestedOrientation(memoryInfo.readStrongBinder(), memoryInfo.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 62:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            iBinder = memoryInfo.readStrongBinder();
            if (memoryInfo.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            removeContentProvider(iBinder, bool30);
            return true;
          case 61:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            iIntentSender3 = IIntentSender.Stub.asInterface(memoryInfo.readStrongBinder());
            if (memoryInfo.readInt() != 0) {
              WorkSource workSource = (WorkSource)WorkSource.CREATOR.createFromParcel((Parcel)memoryInfo);
            } else {
              iBinder13 = null;
            } 
            noteWakeupAlarm(iIntentSender3, (WorkSource)iBinder13, memoryInfo.readInt(), memoryInfo.readString(), memoryInfo.readString());
            iBinder.writeNoException();
            return true;
          case 60:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            enterSafeMode();
            iBinder.writeNoException();
            return true;
          case 59:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            unregisterIntentSenderCancelListener(IIntentSender.Stub.asInterface(memoryInfo.readStrongBinder()), IResultReceiver.Stub.asInterface(memoryInfo.readStrongBinder()));
            iBinder.writeNoException();
            return true;
          case 58:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            registerIntentSenderCancelListener(IIntentSender.Stub.asInterface(memoryInfo.readStrongBinder()), IResultReceiver.Stub.asInterface(memoryInfo.readStrongBinder()));
            iBinder.writeNoException();
            return true;
          case 57:
            memoryInfo.enforceInterface("android.app.IActivityManager");
            str2 = getPackageForIntentSender(IIntentSender.Stub.asInterface(memoryInfo.readStrongBinder()));
            iBinder.writeNoException();
            iBinder.writeString(str2);
            return true;
          case 56:
            str2.enforceInterface("android.app.IActivityManager");
            cancelIntentSender(IIntentSender.Stub.asInterface(str2.readStrongBinder()));
            iBinder.writeNoException();
            return true;
          case 55:
            str2.enforceInterface("android.app.IActivityManager");
            i15 = str2.readInt();
            str27 = str2.readString();
            str7 = str2.readString();
            iBinder18 = str2.readStrongBinder();
            str20 = str2.readString();
            n = str2.readInt();
            arrayOfIntent1 = (Intent[])str2.createTypedArray(Intent.CREATOR);
            arrayOfString2 = str2.createStringArray();
            param1Int2 = str2.readInt();
            if (str2.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str2);
            } else {
              iBinder13 = null;
            } 
            iIntentSender5 = getIntentSenderWithFeature(i15, str27, str7, iBinder18, str20, n, arrayOfIntent1, arrayOfString2, param1Int2, (Bundle)iBinder13, str2.readInt());
            iBinder.writeNoException();
            iIntentSender1 = iIntentSender3;
            if (iIntentSender5 != null)
              iBinder2 = iIntentSender5.asBinder(); 
            iBinder.writeStrongBinder(iBinder2);
            return true;
          case 54:
            iBinder2.enforceInterface("android.app.IActivityManager");
            i15 = iBinder2.readInt();
            str25 = iBinder2.readString();
            iBinder16 = iBinder2.readStrongBinder();
            str17 = iBinder2.readString();
            param1Int2 = iBinder2.readInt();
            arrayOfIntent2 = (Intent[])iBinder2.createTypedArray(Intent.CREATOR);
            arrayOfString1 = iBinder2.createStringArray();
            n = iBinder2.readInt();
            if (iBinder2.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)iBinder2);
            } else {
              iIntentSender5 = null;
            } 
            iIntentSender5 = getIntentSender(i15, str25, iBinder16, str17, param1Int2, arrayOfIntent2, arrayOfString1, n, (Bundle)iIntentSender5, iBinder2.readInt());
            iBinder.writeNoException();
            str1 = str7;
            if (iIntentSender5 != null)
              iBinder1 = iIntentSender5.asBinder(); 
            iBinder.writeStrongBinder(iBinder1);
            return true;
          case 53:
            iBinder1.enforceInterface("android.app.IActivityManager");
            serviceDoneExecuting(iBinder1.readStrongBinder(), iBinder1.readInt(), iBinder1.readInt(), iBinder1.readInt());
            return true;
          case 52:
            iBinder1.enforceInterface("android.app.IActivityManager");
            parceledListSlice = getRecentTasks(iBinder1.readInt(), iBinder1.readInt(), iBinder1.readInt());
            iBinder.writeNoException();
            if (parceledListSlice != null) {
              iBinder.writeInt(1);
              parceledListSlice.writeToParcel((Parcel)iBinder, 1);
            } else {
              iBinder.writeInt(0);
            } 
            return true;
          case 51:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            signalPersistentProcesses(parceledListSlice.readInt());
            iBinder.writeNoException();
            return true;
          case 50:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            iApplicationThread8 = IApplicationThread.Stub.asInterface(parceledListSlice.readStrongBinder());
            if (parceledListSlice.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            showWaitingForDebugger(iApplicationThread8, bool30);
            iBinder.writeNoException();
            return true;
          case 49:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            iActivityController = IActivityController.Stub.asInterface(parceledListSlice.readStrongBinder());
            if (parceledListSlice.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            setActivityController(iActivityController, bool30);
            iBinder.writeNoException();
            return true;
          case 48:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            iApplicationThread4 = IApplicationThread.Stub.asInterface(parceledListSlice.readStrongBinder());
            str7 = parceledListSlice.readString();
            if (parceledListSlice.readInt() != 0) {
              Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)parceledListSlice);
            } else {
              iActivityController = null;
            } 
            revokeUriPermission(iApplicationThread4, str7, (Uri)iActivityController, parceledListSlice.readInt(), parceledListSlice.readInt());
            iBinder.writeNoException();
            return true;
          case 47:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            iApplicationThread4 = IApplicationThread.Stub.asInterface(parceledListSlice.readStrongBinder());
            str7 = parceledListSlice.readString();
            if (parceledListSlice.readInt() != 0) {
              Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)parceledListSlice);
            } else {
              iActivityController = null;
            } 
            grantUriPermission(iApplicationThread4, str7, (Uri)iActivityController, parceledListSlice.readInt(), parceledListSlice.readInt());
            iBinder.writeNoException();
            return true;
          case 46:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            if (parceledListSlice.readInt() != 0) {
              Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)parceledListSlice);
            } else {
              iActivityController = null;
            } 
            n = checkUriPermission((Uri)iActivityController, parceledListSlice.readInt(), parceledListSlice.readInt(), parceledListSlice.readInt(), parceledListSlice.readInt(), parceledListSlice.readStrongBinder());
            iBinder.writeNoException();
            iBinder.writeInt(n);
            return true;
          case 45:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            n = checkPermission(parceledListSlice.readString(), parceledListSlice.readInt(), parceledListSlice.readInt());
            iBinder.writeNoException();
            iBinder.writeInt(n);
            return true;
          case 44:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            n = getProcessLimit();
            iBinder.writeNoException();
            iBinder.writeInt(n);
            return true;
          case 43:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            setProcessLimit(parceledListSlice.readInt());
            iBinder.writeNoException();
            return true;
          case 42:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            if (parceledListSlice.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parceledListSlice);
            } else {
              iActivityController = null;
            } 
            bool5 = stopServiceToken((ComponentName)iActivityController, parceledListSlice.readStrongBinder(), parceledListSlice.readInt());
            iBinder.writeNoException();
            iBinder.writeInt(bool5);
            return true;
          case 41:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            bool5 = updateMccMncConfiguration(parceledListSlice.readString(), parceledListSlice.readString());
            iBinder.writeNoException();
            iBinder.writeInt(bool5);
            return true;
          case 40:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            if (parceledListSlice.readInt() != 0) {
              Configuration configuration1 = (Configuration)Configuration.CREATOR.createFromParcel((Parcel)parceledListSlice);
            } else {
              parceledListSlice = null;
            } 
            bool5 = updateConfiguration((Configuration)parceledListSlice);
            iBinder.writeNoException();
            iBinder.writeInt(bool5);
            return true;
          case 39:
            parceledListSlice.enforceInterface("android.app.IActivityManager");
            configuration = getConfiguration();
            iBinder.writeNoException();
            if (configuration != null) {
              iBinder.writeInt(1);
              configuration.writeToParcel((Parcel)iBinder, 1);
            } else {
              iBinder.writeInt(0);
            } 
            return true;
          case 38:
            configuration.enforceInterface("android.app.IActivityManager");
            iApplicationThread7 = IApplicationThread.Stub.asInterface(configuration.readStrongBinder());
            m = configuration.readInt();
            if (configuration.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)configuration);
            } else {
              configuration = null;
            } 
            finishInstrumentation(iApplicationThread7, m, (Bundle)configuration);
            iBinder.writeNoException();
            return true;
          case 37:
            configuration.enforceInterface("android.app.IActivityManager");
            iApplicationThread7 = IApplicationThread.Stub.asInterface(configuration.readStrongBinder());
            if (configuration.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)configuration);
            } else {
              configuration = null;
            } 
            addInstrumentationResults(iApplicationThread7, (Bundle)configuration);
            iBinder.writeNoException();
            return true;
          case 36:
            configuration.enforceInterface("android.app.IActivityManager");
            if (configuration.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)configuration);
            } else {
              iApplicationThread7 = null;
            } 
            str7 = configuration.readString();
            m = configuration.readInt();
            if (configuration.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)configuration);
            } else {
              iApplicationThread4 = null;
            } 
            bool4 = startInstrumentation((ComponentName)iApplicationThread7, str7, m, (Bundle)iApplicationThread4, IInstrumentationWatcher.Stub.asInterface(configuration.readStrongBinder()), IUiAutomationConnection.Stub.asInterface(configuration.readStrongBinder()), configuration.readInt(), configuration.readString());
            iBinder.writeNoException();
            iBinder.writeInt(bool4);
            return true;
          case 35:
            configuration.enforceInterface("android.app.IActivityManager");
            if (configuration.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            setAlwaysFinish(bool30);
            iBinder.writeNoException();
            return true;
          case 34:
            configuration.enforceInterface("android.app.IActivityManager");
            setAgentApp(configuration.readString(), configuration.readString());
            iBinder.writeNoException();
            return true;
          case 33:
            configuration.enforceInterface("android.app.IActivityManager");
            str13 = configuration.readString();
            if (configuration.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            if (configuration.readInt() != 0) {
              bool29 = true;
            } else {
              bool29 = false;
            } 
            setDebugApp(str13, bool30, bool29);
            iBinder.writeNoException();
            return true;
          case 32:
            configuration.enforceInterface("android.app.IActivityManager");
            iBinder8 = configuration.readStrongBinder();
            if (configuration.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)configuration);
            } else {
              str13 = null;
            } 
            publishService(iBinder8, (Intent)str13, configuration.readStrongBinder());
            iBinder.writeNoException();
            return true;
          case 31:
            configuration.enforceInterface("android.app.IActivityManager");
            bool4 = unbindService(IServiceConnection.Stub.asInterface(configuration.readStrongBinder()));
            iBinder.writeNoException();
            iBinder.writeInt(bool4);
            return true;
          case 30:
            configuration.enforceInterface("android.app.IActivityManager");
            updateServiceGroup(IServiceConnection.Stub.asInterface(configuration.readStrongBinder()), configuration.readInt(), configuration.readInt());
            iBinder.writeNoException();
            return true;
          case 29:
            configuration.enforceInterface("android.app.IActivityManager");
            iApplicationThread3 = IApplicationThread.Stub.asInterface(configuration.readStrongBinder());
            iBinder4 = configuration.readStrongBinder();
            if (configuration.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)configuration);
            } else {
              str13 = null;
            } 
            k = bindIsolatedService(iApplicationThread3, iBinder4, (Intent)str13, configuration.readString(), IServiceConnection.Stub.asInterface(configuration.readStrongBinder()), configuration.readInt(), configuration.readString(), configuration.readString(), configuration.readInt());
            iBinder.writeNoException();
            iBinder.writeInt(k);
            return true;
          case 28:
            configuration.enforceInterface("android.app.IActivityManager");
            iApplicationThread3 = IApplicationThread.Stub.asInterface(configuration.readStrongBinder());
            iBinder4 = configuration.readStrongBinder();
            if (configuration.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)configuration);
            } else {
              str13 = null;
            } 
            k = bindService(iApplicationThread3, iBinder4, (Intent)str13, configuration.readString(), IServiceConnection.Stub.asInterface(configuration.readStrongBinder()), configuration.readInt(), configuration.readString(), configuration.readInt());
            iBinder.writeNoException();
            iBinder.writeInt(k);
            return true;
          case 27:
            configuration.enforceInterface("android.app.IActivityManager");
            iApplicationThread3 = IApplicationThread.Stub.asInterface(configuration.readStrongBinder());
            if (configuration.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)configuration);
            } else {
              str13 = null;
            } 
            k = stopService(iApplicationThread3, (Intent)str13, configuration.readString(), configuration.readInt());
            iBinder.writeNoException();
            iBinder.writeInt(k);
            return true;
          case 26:
            configuration.enforceInterface("android.app.IActivityManager");
            iApplicationThread3 = IApplicationThread.Stub.asInterface(configuration.readStrongBinder());
            if (configuration.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)configuration);
            } else {
              str13 = null;
            } 
            str6 = configuration.readString();
            if (configuration.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            componentName1 = startService(iApplicationThread3, (Intent)str13, str6, bool30, configuration.readString(), configuration.readString(), configuration.readInt());
            iBinder.writeNoException();
            if (componentName1 != null) {
              iBinder.writeInt(1);
              componentName1.writeToParcel((Parcel)iBinder, 1);
            } else {
              iBinder.writeInt(0);
            } 
            return true;
          case 25:
            componentName1.enforceInterface("android.app.IActivityManager");
            if (componentName1.readInt() != 0) {
              componentName1 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName1);
            } else {
              componentName1 = null;
            } 
            pendingIntent = getRunningServiceControlPanel(componentName1);
            iBinder.writeNoException();
            if (pendingIntent != null) {
              iBinder.writeInt(1);
              pendingIntent.writeToParcel((Parcel)iBinder, 1);
            } else {
              iBinder.writeInt(0);
            } 
            return true;
          case 24:
            pendingIntent.enforceInterface("android.app.IActivityManager");
            bool3 = refContentProvider(pendingIntent.readStrongBinder(), pendingIntent.readInt(), pendingIntent.readInt());
            iBinder.writeNoException();
            iBinder.writeInt(bool3);
            return true;
          case 23:
            pendingIntent.enforceInterface("android.app.IActivityManager");
            publishContentProviders(IApplicationThread.Stub.asInterface(pendingIntent.readStrongBinder()), pendingIntent.createTypedArrayList(ContentProviderHolder.CREATOR));
            iBinder.writeNoException();
            return true;
          case 22:
            pendingIntent.enforceInterface("android.app.IActivityManager");
            iApplicationThread6 = IApplicationThread.Stub.asInterface(pendingIntent.readStrongBinder());
            str6 = pendingIntent.readString();
            str10 = pendingIntent.readString();
            j = pendingIntent.readInt();
            if (pendingIntent.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            contentProviderHolder1 = getContentProvider(iApplicationThread6, str6, str10, j, bool30);
            iBinder.writeNoException();
            if (contentProviderHolder1 != null) {
              iBinder.writeInt(1);
              contentProviderHolder1.writeToParcel((Parcel)iBinder, 1);
            } else {
              iBinder.writeInt(0);
            } 
            return true;
          case 21:
            contentProviderHolder1.enforceInterface("android.app.IActivityManager");
            iBinder12 = contentProviderHolder1.readStrongBinder();
            if (contentProviderHolder1.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            j = getTaskForActivity(iBinder12, bool30);
            iBinder.writeNoException();
            iBinder.writeInt(j);
            return true;
          case 20:
            contentProviderHolder1.enforceInterface("android.app.IActivityManager");
            iApplicationThread5 = IApplicationThread.Stub.asInterface(contentProviderHolder1.readStrongBinder());
            str10 = contentProviderHolder1.readString();
            j = contentProviderHolder1.readInt();
            param1Int2 = contentProviderHolder1.readInt();
            if (contentProviderHolder1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)contentProviderHolder1);
            } else {
              contentProviderHolder1 = null;
            } 
            moveTaskToFront(iApplicationThread5, str10, j, param1Int2, (Bundle)contentProviderHolder1);
            iBinder.writeNoException();
            return true;
          case 19:
            contentProviderHolder1.enforceInterface("android.app.IActivityManager");
            list = getTasks(contentProviderHolder1.readInt());
            iBinder.writeNoException();
            iBinder.writeTypedList(list);
            return true;
          case 18:
            list.enforceInterface("android.app.IActivityManager");
            attachApplication(IApplicationThread.Stub.asInterface(list.readStrongBinder()), list.readLong());
            iBinder.writeNoException();
            return true;
          case 17:
            list.enforceInterface("android.app.IActivityManager");
            iBinder7 = list.readStrongBinder();
            j = list.readInt();
            str12 = list.readString();
            if (list.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)list);
            } else {
              iBinder = null;
            } 
            if (list.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            finishReceiver(iBinder7, j, str12, (Bundle)iBinder, bool30, list.readInt());
            return true;
          case 16:
            list.enforceInterface("android.app.IActivityManager");
            iApplicationThread2 = IApplicationThread.Stub.asInterface(list.readStrongBinder());
            if (list.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)list);
            } else {
              str12 = null;
            } 
            unbroadcastIntent(iApplicationThread2, (Intent)str12, list.readInt());
            iBinder.writeNoException();
            return true;
          case 15:
            list.enforceInterface("android.app.IActivityManager");
            iApplicationThread10 = IApplicationThread.Stub.asInterface(list.readStrongBinder());
            str17 = list.readString();
            if (list.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)list);
            } else {
              str12 = null;
            } 
            str19 = list.readString();
            iIntentReceiver5 = IIntentReceiver.Stub.asInterface(list.readStrongBinder());
            j = list.readInt();
            str25 = list.readString();
            if (list.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)list);
            } else {
              iApplicationThread2 = null;
            } 
            arrayOfString4 = list.createStringArray();
            param1Int2 = list.readInt();
            if (list.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)list);
            } else {
              str6 = null;
            } 
            if (list.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            if (list.readInt() != 0)
              bool29 = true; 
            j = broadcastIntentWithFeature(iApplicationThread10, str17, (Intent)str12, str19, iIntentReceiver5, j, str25, (Bundle)iApplicationThread2, arrayOfString4, param1Int2, (Bundle)str6, bool30, bool29, list.readInt());
            iBinder.writeNoException();
            iBinder.writeInt(j);
            return true;
          case 14:
            list.enforceInterface("android.app.IActivityManager");
            iApplicationThread9 = IApplicationThread.Stub.asInterface(list.readStrongBinder());
            if (list.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)list);
            } else {
              str12 = null;
            } 
            str19 = list.readString();
            iIntentReceiver4 = IIntentReceiver.Stub.asInterface(list.readStrongBinder());
            param1Int2 = list.readInt();
            str22 = list.readString();
            if (list.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)list);
            } else {
              iApplicationThread2 = null;
            } 
            arrayOfString3 = list.createStringArray();
            j = list.readInt();
            if (list.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)list);
            } else {
              str6 = null;
            } 
            if (list.readInt() != 0) {
              bool30 = true;
            } else {
              bool30 = false;
            } 
            if (list.readInt() != 0) {
              bool29 = true;
            } else {
              bool29 = false;
            } 
            j = broadcastIntent(iApplicationThread9, (Intent)str12, str19, iIntentReceiver4, param1Int2, str22, (Bundle)iApplicationThread2, arrayOfString3, j, (Bundle)str6, bool30, bool29, list.readInt());
            iBinder.writeNoException();
            iBinder.writeInt(j);
            return true;
          case 13:
            list.enforceInterface("android.app.IActivityManager");
            unregisterReceiver(IIntentReceiver.Stub.asInterface(list.readStrongBinder()));
            iBinder.writeNoException();
            return true;
          case 12:
            list.enforceInterface("android.app.IActivityManager");
            iApplicationThread9 = IApplicationThread.Stub.asInterface(list.readStrongBinder());
            str9 = list.readString();
            str6 = list.readString();
            iIntentReceiver3 = IIntentReceiver.Stub.asInterface(list.readStrongBinder());
            if (list.readInt() != 0) {
              IntentFilter intentFilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel((Parcel)list);
            } else {
              str12 = null;
            } 
            intent1 = registerReceiverWithFeature(iApplicationThread9, str9, str6, iIntentReceiver3, (IntentFilter)str12, list.readString(), list.readInt(), list.readInt());
            iBinder.writeNoException();
            if (intent1 != null) {
              iBinder.writeInt(1);
              intent1.writeToParcel((Parcel)iBinder, 1);
            } else {
              iBinder.writeInt(0);
            } 
            return true;
          case 11:
            intent1.enforceInterface("android.app.IActivityManager");
            iApplicationThread1 = IApplicationThread.Stub.asInterface(intent1.readStrongBinder());
            str16 = intent1.readString();
            iIntentReceiver1 = IIntentReceiver.Stub.asInterface(intent1.readStrongBinder());
            if (intent1.readInt() != 0) {
              IntentFilter intentFilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel((Parcel)intent1);
            } else {
              str12 = null;
            } 
            intent1 = registerReceiver(iApplicationThread1, str16, iIntentReceiver1, (IntentFilter)str12, intent1.readString(), intent1.readInt(), intent1.readInt());
            iBinder.writeNoException();
            if (intent1 != null) {
              iBinder.writeInt(1);
              intent1.writeToParcel((Parcel)iBinder, 1);
            } else {
              iBinder.writeInt(0);
            } 
            return true;
          case 10:
            intent1.enforceInterface("android.app.IActivityManager");
            iBinder6 = intent1.readStrongBinder();
            j = intent1.readInt();
            if (intent1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)intent1);
            } else {
              str12 = null;
            } 
            bool2 = finishActivity(iBinder6, j, (Intent)str12, intent1.readInt());
            iBinder.writeNoException();
            iBinder.writeInt(bool2);
            return true;
          case 9:
            intent1.enforceInterface("android.app.IActivityManager");
            unhandledBack();
            iBinder.writeNoException();
            return true;
          case 8:
            intent1.enforceInterface("android.app.IActivityManager");
            iApplicationThread1 = IApplicationThread.Stub.asInterface(intent1.readStrongBinder());
            str21 = intent1.readString();
            str16 = intent1.readString();
            if (intent1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)intent1);
            } else {
              str12 = null;
            } 
            str24 = intent1.readString();
            iBinder19 = intent1.readStrongBinder();
            str19 = intent1.readString();
            param1Int2 = intent1.readInt();
            i = intent1.readInt();
            if (intent1.readInt() != 0) {
              ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel((Parcel)intent1);
            } else {
              iBinder6 = null;
            } 
            if (intent1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)intent1);
            } else {
              intent1 = null;
            } 
            i = startActivityWithFeature(iApplicationThread1, str21, str16, (Intent)str12, str24, iBinder19, str19, param1Int2, i, (ProfilerInfo)iBinder6, (Bundle)intent1);
            iBinder.writeNoException();
            iBinder.writeInt(i);
            return true;
          case 7:
            intent1.enforceInterface("android.app.IActivityManager");
            iApplicationThread1 = IApplicationThread.Stub.asInterface(intent1.readStrongBinder());
            str16 = intent1.readString();
            if (intent1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)intent1);
            } else {
              str12 = null;
            } 
            str19 = intent1.readString();
            iBinder17 = intent1.readStrongBinder();
            str21 = intent1.readString();
            i = intent1.readInt();
            param1Int2 = intent1.readInt();
            if (intent1.readInt() != 0) {
              ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel((Parcel)intent1);
            } else {
              iBinder6 = null;
            } 
            if (intent1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)intent1);
            } else {
              intent1 = null;
            } 
            i = startActivity(iApplicationThread1, str16, (Intent)str12, str19, iBinder17, str21, i, param1Int2, (ProfilerInfo)iBinder6, (Bundle)intent1);
            iBinder.writeNoException();
            iBinder.writeInt(i);
            return true;
          case 6:
            intent1.enforceInterface("android.app.IActivityManager");
            iBinder11 = intent1.readStrongBinder();
            if (intent1.readInt() != 0) {
              ApplicationErrorReport.ParcelableCrashInfo parcelableCrashInfo = (ApplicationErrorReport.ParcelableCrashInfo)ApplicationErrorReport.ParcelableCrashInfo.CREATOR.createFromParcel((Parcel)intent1);
            } else {
              intent1 = null;
            } 
            handleApplicationCrash(iBinder11, (ApplicationErrorReport.ParcelableCrashInfo)intent1);
            iBinder.writeNoException();
            return true;
          case 5:
            intent1.enforceInterface("android.app.IActivityManager");
            i = getUidProcessState(intent1.readInt(), intent1.readString());
            iBinder.writeNoException();
            iBinder.writeInt(i);
            return true;
          case 4:
            intent1.enforceInterface("android.app.IActivityManager");
            bool1 = isUidActive(intent1.readInt(), intent1.readString());
            iBinder.writeNoException();
            iBinder.writeInt(bool1);
            return true;
          case 3:
            intent1.enforceInterface("android.app.IActivityManager");
            unregisterUidObserver(IUidObserver.Stub.asInterface(intent1.readStrongBinder()));
            iBinder.writeNoException();
            return true;
          case 2:
            intent1.enforceInterface("android.app.IActivityManager");
            registerUidObserver(IUidObserver.Stub.asInterface(intent1.readStrongBinder()), intent1.readInt(), intent1.readInt(), intent1.readString());
            iBinder.writeNoException();
            return true;
          case 1:
            break;
        } 
        intent1.enforceInterface("android.app.IActivityManager");
        ParcelFileDescriptor parcelFileDescriptor1 = openContentUri(intent1.readString());
        iBinder.writeNoException();
        if (parcelFileDescriptor1 != null) {
          iBinder.writeInt(1);
          parcelFileDescriptor1.writeToParcel((Parcel)iBinder, 1);
        } else {
          iBinder.writeInt(0);
        } 
        return true;
      } 
      iBinder.writeString("android.app.IActivityManager");
      return true;
    }
    
    private static class Proxy implements IActivityManager {
      public static IActivityManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public void addInstrumentationResults(IApplicationThread param2IApplicationThread, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().addInstrumentationResults(param2IApplicationThread, param2Bundle);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void addPackageDependency(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(86, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().addPackageDependency(param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void appNotResponding(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(210, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().appNotResponding(param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void appNotRespondingViaProvider(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(153, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().appNotRespondingViaProvider(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void attachApplication(IApplicationThread param2IApplicationThread, long param2Long) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeLong(param2Long);
          if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().attachApplication(param2IApplicationThread, param2Long);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void backgroundWhitelistUid(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(204, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().backgroundWhitelistUid(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void backupAgentCreated(String param2String, IBinder param2IBinder, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(82, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().backupAgentCreated(param2String, param2IBinder, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean bindBackupAgent(String param2String, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(81, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().bindBackupAgent(param2String, param2Int1, param2Int2);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int bindIsolatedService(IApplicationThread param2IApplicationThread, IBinder param2IBinder, Intent param2Intent, String param2String1, IServiceConnection param2IServiceConnection, int param2Int1, String param2String2, String param2String3, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder2;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder1 = null;
          if (param2IApplicationThread != null) {
            iBinder2 = param2IApplicationThread.asBinder();
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          try {
            parcel1.writeStrongBinder(param2IBinder);
            if (param2Intent != null) {
              parcel1.writeInt(1);
              param2Intent.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeString(param2String1);
              iBinder2 = iBinder1;
              if (param2IServiceConnection != null)
                iBinder2 = param2IServiceConnection.asBinder(); 
              parcel1.writeStrongBinder(iBinder2);
              parcel1.writeInt(param2Int1);
              parcel1.writeString(param2String2);
              parcel1.writeString(param2String3);
              parcel1.writeInt(param2Int2);
              if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                param2Int1 = IActivityManager.Stub.getDefaultImpl().bindIsolatedService(param2IApplicationThread, param2IBinder, param2Intent, param2String1, param2IServiceConnection, param2Int1, param2String2, param2String3, param2Int2);
                parcel2.recycle();
                parcel1.recycle();
                return param2Int1;
              } 
              parcel2.readException();
              param2Int1 = parcel2.readInt();
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public int bindService(IApplicationThread param2IApplicationThread, IBinder param2IBinder, Intent param2Intent, String param2String1, IServiceConnection param2IServiceConnection, int param2Int1, String param2String2, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder2;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder1 = null;
          if (param2IApplicationThread != null) {
            iBinder2 = param2IApplicationThread.asBinder();
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          try {
            parcel1.writeStrongBinder(param2IBinder);
            if (param2Intent != null) {
              parcel1.writeInt(1);
              param2Intent.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeString(param2String1);
              iBinder2 = iBinder1;
              if (param2IServiceConnection != null)
                iBinder2 = param2IServiceConnection.asBinder(); 
              parcel1.writeStrongBinder(iBinder2);
              try {
                parcel1.writeInt(param2Int1);
                parcel1.writeString(param2String2);
                parcel1.writeInt(param2Int2);
                if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                  param2Int1 = IActivityManager.Stub.getDefaultImpl().bindService(param2IApplicationThread, param2IBinder, param2Intent, param2String1, param2IServiceConnection, param2Int1, param2String2, param2Int2);
                  parcel2.recycle();
                  parcel1.recycle();
                  return param2Int1;
                } 
                parcel2.readException();
                param2Int1 = parcel2.readInt();
                parcel2.recycle();
                parcel1.recycle();
                return param2Int1;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public void bootAnimationComplete() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(164, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().bootAnimationComplete();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int broadcastIntent(IApplicationThread param2IApplicationThread, Intent param2Intent, String param2String1, IIntentReceiver param2IIntentReceiver, int param2Int1, String param2String2, Bundle param2Bundle1, String[] param2ArrayOfString, int param2Int2, Bundle param2Bundle2, boolean param2Boolean1, boolean param2Boolean2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool2;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder1 = null;
          if (param2IApplicationThread != null) {
            try {
              iBinder2 = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          boolean bool1 = true;
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String1);
          IBinder iBinder2 = iBinder1;
          if (param2IIntentReceiver != null)
            iBinder2 = param2IIntentReceiver.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          parcel1.writeInt(param2Int1);
          parcel1.writeString(param2String2);
          if (param2Bundle1 != null) {
            parcel1.writeInt(1);
            param2Bundle1.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeStringArray(param2ArrayOfString);
          parcel1.writeInt(param2Int2);
          if (param2Bundle2 != null) {
            parcel1.writeInt(1);
            param2Bundle2.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Boolean1) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (param2Boolean2) {
            bool2 = bool1;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityManager.broadcastIntent(param2IApplicationThread, param2Intent, param2String1, param2IIntentReceiver, param2Int1, param2String2, param2Bundle1, param2ArrayOfString, param2Int2, param2Bundle2, param2Boolean1, param2Boolean2, param2Int3);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public int broadcastIntentWithFeature(IApplicationThread param2IApplicationThread, String param2String1, Intent param2Intent, String param2String2, IIntentReceiver param2IIntentReceiver, int param2Int1, String param2String3, Bundle param2Bundle1, String[] param2ArrayOfString, int param2Int2, Bundle param2Bundle2, boolean param2Boolean1, boolean param2Boolean2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool2;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder1 = null;
          if (param2IApplicationThread != null) {
            try {
              iBinder2 = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          parcel1.writeString(param2String1);
          boolean bool1 = true;
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String2);
          IBinder iBinder2 = iBinder1;
          if (param2IIntentReceiver != null)
            iBinder2 = param2IIntentReceiver.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          parcel1.writeInt(param2Int1);
          parcel1.writeString(param2String3);
          if (param2Bundle1 != null) {
            parcel1.writeInt(1);
            param2Bundle1.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeStringArray(param2ArrayOfString);
          parcel1.writeInt(param2Int2);
          if (param2Bundle2 != null) {
            parcel1.writeInt(1);
            param2Bundle2.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Boolean1) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (param2Boolean2) {
            bool2 = bool1;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityManager.broadcastIntentWithFeature(param2IApplicationThread, param2String1, param2Intent, param2String2, param2IIntentReceiver, param2Int1, param2String3, param2Bundle1, param2ArrayOfString, param2Int2, param2Bundle2, param2Boolean1, param2Boolean2, param2Int3);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public void cancelIntentSender(IIntentSender param2IIntentSender) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().cancelIntentSender(param2IIntentSender);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void cancelRecentsAnimation(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(160, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().cancelRecentsAnimation(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void cancelTaskWindowTransition(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(199, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().cancelTaskWindowTransition(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int checkPermission(String param2String, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Int1 = IActivityManager.Stub.getDefaultImpl().checkPermission(param2String, param2Int1, param2Int2);
            return param2Int1;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          return param2Int1;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int checkPermissionWithToken(String param2String, int param2Int1, int param2Int2, IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(165, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Int1 = IActivityManager.Stub.getDefaultImpl().checkPermissionWithToken(param2String, param2Int1, param2Int2, param2IBinder);
            return param2Int1;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          return param2Int1;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int checkUriPermission(Uri param2Uri, int param2Int1, int param2Int2, int param2Int3, int param2Int4, IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2Uri != null) {
            parcel1.writeInt(1);
            param2Uri.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeInt(param2Int2);
              try {
                parcel1.writeInt(param2Int3);
                try {
                  parcel1.writeInt(param2Int4);
                  try {
                    parcel1.writeStrongBinder(param2IBinder);
                    if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                      param2Int1 = IActivityManager.Stub.getDefaultImpl().checkUriPermission(param2Uri, param2Int1, param2Int2, param2Int3, param2Int4, param2IBinder);
                      parcel2.recycle();
                      parcel1.recycle();
                      return param2Int1;
                    } 
                    parcel2.readException();
                    param2Int1 = parcel2.readInt();
                    parcel2.recycle();
                    parcel1.recycle();
                    return param2Int1;
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2Uri;
      }
      
      public boolean clearApplicationUserData(String param2String, boolean param2Boolean, IPackageDataObserver param2IPackageDataObserver, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool2;
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          boolean bool1 = true;
          if (param2Boolean) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (param2IPackageDataObserver != null) {
            iBinder = param2IPackageDataObserver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(71, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Boolean = IActivityManager.Stub.getDefaultImpl().clearApplicationUserData(param2String, param2Boolean, param2IPackageDataObserver, param2Int);
            return param2Boolean;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0) {
            param2Boolean = bool1;
          } else {
            param2Boolean = false;
          } 
          return param2Boolean;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void closeSystemDialogs(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(88, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().closeSystemDialogs(param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void crashApplication(int param2Int1, int param2Int2, String param2String1, int param2Int3, String param2String2, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeInt(param2Int2);
              try {
                parcel1.writeString(param2String1);
                try {
                  parcel1.writeInt(param2Int3);
                  try {
                    boolean bool;
                    parcel1.writeString(param2String2);
                    if (param2Boolean) {
                      bool = true;
                    } else {
                      bool = false;
                    } 
                    parcel1.writeInt(bool);
                    try {
                      if (!this.mRemote.transact(98, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                        IActivityManager.Stub.getDefaultImpl().crashApplication(param2Int1, param2Int2, param2String1, param2Int3, param2String2, param2Boolean);
                        parcel2.recycle();
                        parcel1.recycle();
                        return;
                      } 
                      parcel2.readException();
                      parcel2.recycle();
                      parcel1.recycle();
                      return;
                    } finally {}
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2String1;
      }
      
      public boolean dumpHeap(String param2String1, int param2Int, boolean param2Boolean1, boolean param2Boolean2, boolean param2Boolean3, String param2String2, ParcelFileDescriptor param2ParcelFileDescriptor, RemoteCallback param2RemoteCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          try {
            parcel1.writeString(param2String1);
            try {
              boolean bool2;
              parcel1.writeInt(param2Int);
              boolean bool1 = true;
              if (param2Boolean1) {
                bool2 = true;
              } else {
                bool2 = false;
              } 
              parcel1.writeInt(bool2);
              if (param2Boolean2) {
                bool2 = true;
              } else {
                bool2 = false;
              } 
              parcel1.writeInt(bool2);
              if (param2Boolean3) {
                bool2 = true;
              } else {
                bool2 = false;
              } 
              parcel1.writeInt(bool2);
              parcel1.writeString(param2String2);
              if (param2ParcelFileDescriptor != null) {
                parcel1.writeInt(1);
                param2ParcelFileDescriptor.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (param2RemoteCallback != null) {
                parcel1.writeInt(1);
                param2RemoteCallback.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (!this.mRemote.transact(101, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                param2Boolean1 = IActivityManager.Stub.getDefaultImpl().dumpHeap(param2String1, param2Int, param2Boolean1, param2Boolean2, param2Boolean3, param2String2, param2ParcelFileDescriptor, param2RemoteCallback);
                parcel2.recycle();
                parcel1.recycle();
                return param2Boolean1;
              } 
              parcel2.readException();
              param2Int = parcel2.readInt();
              if (param2Int != 0) {
                param2Boolean1 = bool1;
              } else {
                param2Boolean1 = false;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return param2Boolean1;
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2String1;
      }
      
      public void dumpHeapFinished(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(173, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().dumpHeapFinished(param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean enableAppFreezer(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          boolean bool = true;
          if (param2Boolean) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          if (!this.mRemote.transact(217, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Boolean = IActivityManager.Stub.getDefaultImpl().enableAppFreezer(param2Boolean);
            return param2Boolean;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0) {
            param2Boolean = bool;
          } else {
            param2Boolean = false;
          } 
          return param2Boolean;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void enterSafeMode() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().enterSafeMode();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean finishActivity(IBinder param2IBinder, int param2Int1, Intent param2Intent, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeInt(param2Int1);
          boolean bool = true;
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().finishActivity(param2IBinder, param2Int1, param2Intent, param2Int2);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void finishHeavyWeightApp() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(95, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().finishHeavyWeightApp();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void finishInstrumentation(IApplicationThread param2IApplicationThread, int param2Int, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeInt(param2Int);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().finishInstrumentation(param2IApplicationThread, param2Int, param2Bundle);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void finishReceiver(IBinder param2IBinder, int param2Int1, String param2String, Bundle param2Bundle, boolean param2Boolean, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IActivityManager");
          try {
            parcel.writeStrongBinder(param2IBinder);
            try {
              parcel.writeInt(param2Int1);
              try {
                parcel.writeString(param2String);
                boolean bool = false;
                if (param2Bundle != null) {
                  parcel.writeInt(1);
                  param2Bundle.writeToParcel(parcel, 0);
                } else {
                  parcel.writeInt(0);
                } 
                if (param2Boolean)
                  bool = true; 
                parcel.writeInt(bool);
                try {
                  parcel.writeInt(param2Int2);
                  try {
                    if (!this.mRemote.transact(17, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
                      IActivityManager.Stub.getDefaultImpl().finishReceiver(param2IBinder, param2Int1, param2String, param2Bundle, param2Boolean, param2Int2);
                      parcel.recycle();
                      return;
                    } 
                    parcel.recycle();
                    return;
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel.recycle();
        throw param2IBinder;
      }
      
      public void forceStopPackage(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(72, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().forceStopPackage(param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(147, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getAllStackInfos(); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ActivityManager.StackInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<String> getBugreportWhitelistedPackages() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(141, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getBugreportWhitelistedPackages(); 
          parcel2.readException();
          return parcel2.createStringArrayList();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Configuration getConfiguration() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          Configuration configuration;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            configuration = IActivityManager.Stub.getDefaultImpl().getConfiguration();
            return configuration;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            configuration = (Configuration)Configuration.CREATOR.createFromParcel(parcel2);
          } else {
            configuration = null;
          } 
          return configuration;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ContentProviderHolder getContentProvider(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          parcel1.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getContentProvider(param2IApplicationThread, param2String1, param2String2, param2Int, param2Boolean); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ContentProviderHolder contentProviderHolder = (ContentProviderHolder)ContentProviderHolder.CREATOR.createFromParcel(parcel2);
          } else {
            param2IApplicationThread = null;
          } 
          return (ContentProviderHolder)param2IApplicationThread;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ContentProviderHolder getContentProviderExternal(String param2String1, int param2Int, IBinder param2IBinder, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String1);
          parcel1.writeInt(param2Int);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(113, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getContentProviderExternal(param2String1, param2Int, param2IBinder, param2String2); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ContentProviderHolder contentProviderHolder = (ContentProviderHolder)ContentProviderHolder.CREATOR.createFromParcel(parcel2);
          } else {
            param2String1 = null;
          } 
          return (ContentProviderHolder)param2String1;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public UserInfo getCurrentUser() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          UserInfo userInfo;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(118, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            userInfo = IActivityManager.Stub.getDefaultImpl().getCurrentUser();
            return userInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            userInfo = (UserInfo)UserInfo.CREATOR.createFromParcel(parcel2);
          } else {
            userInfo = null;
          } 
          return userInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ActivityManager.StackInfo getFocusedStackInfo() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ActivityManager.StackInfo stackInfo;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(150, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            stackInfo = IActivityManager.Stub.getDefaultImpl().getFocusedStackInfo();
            return stackInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            stackInfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel2);
          } else {
            stackInfo = null;
          } 
          return stackInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getForegroundServiceType(ComponentName param2ComponentName, IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getForegroundServiceType(param2ComponentName, param2IBinder); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ParceledListSlice<ApplicationExitInfo> getHistoricalProcessExitReasons(String param2String, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(211, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getHistoricalProcessExitReasons(param2String, param2Int1, param2Int2, param2Int3); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (ParceledListSlice<ApplicationExitInfo>)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Intent getIntentForIntentSender(IIntentSender param2IIntentSender) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(142, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getIntentForIntentSender(param2IIntentSender); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
          } else {
            param2IIntentSender = null;
          } 
          return (Intent)param2IIntentSender;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IIntentSender getIntentSender(int param2Int1, String param2String1, IBinder param2IBinder, String param2String2, int param2Int2, Intent[] param2ArrayOfIntent, String[] param2ArrayOfString, int param2Int3, Bundle param2Bundle, int param2Int4) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          try {
            parcel1.writeInt(param2Int1);
            parcel1.writeString(param2String1);
            parcel1.writeStrongBinder(param2IBinder);
            parcel1.writeString(param2String2);
            parcel1.writeInt(param2Int2);
            parcel1.writeTypedArray((Parcelable[])param2ArrayOfIntent, 0);
            parcel1.writeStringArray(param2ArrayOfString);
            parcel1.writeInt(param2Int3);
            if (param2Bundle != null) {
              parcel1.writeInt(1);
              param2Bundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            parcel1.writeInt(param2Int4);
            if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
              IIntentSender iIntentSender1 = IActivityManager.Stub.getDefaultImpl().getIntentSender(param2Int1, param2String1, param2IBinder, param2String2, param2Int2, param2ArrayOfIntent, param2ArrayOfString, param2Int3, param2Bundle, param2Int4);
              parcel2.recycle();
              parcel1.recycle();
              return iIntentSender1;
            } 
            parcel2.readException();
            IIntentSender iIntentSender = IIntentSender.Stub.asInterface(parcel2.readStrongBinder());
            parcel2.recycle();
            parcel1.recycle();
            return iIntentSender;
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2String1;
      }
      
      public IIntentSender getIntentSenderWithFeature(int param2Int1, String param2String1, String param2String2, IBinder param2IBinder, String param2String3, int param2Int2, Intent[] param2ArrayOfIntent, String[] param2ArrayOfString, int param2Int3, Bundle param2Bundle, int param2Int4) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String3);
          parcel1.writeInt(param2Int2);
          parcel1.writeTypedArray((Parcelable[])param2ArrayOfIntent, 0);
          parcel1.writeStringArray(param2ArrayOfString);
          parcel1.writeInt(param2Int3);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int4);
          if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getIntentSenderWithFeature(param2Int1, param2String1, param2String2, param2IBinder, param2String3, param2Int2, param2ArrayOfIntent, param2ArrayOfString, param2Int3, param2Bundle, param2Int4); 
          parcel2.readException();
          return IIntentSender.Stub.asInterface(parcel2.readStrongBinder());
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IActivityManager";
      }
      
      public String getLaunchedFromPackage(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(143, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getLaunchedFromPackage(param2IBinder); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getLaunchedFromUid(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(119, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getLaunchedFromUid(param2IBinder); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ParcelFileDescriptor getLifeMonitor() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ParcelFileDescriptor parcelFileDescriptor;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(208, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            parcelFileDescriptor = IActivityManager.Stub.getDefaultImpl().getLifeMonitor();
            return parcelFileDescriptor;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
          } else {
            parcelFileDescriptor = null;
          } 
          return parcelFileDescriptor;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getLockTaskModeState() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(171, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getLockTaskModeState(); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void getMemoryInfo(ActivityManager.MemoryInfo param2MemoryInfo) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().getMemoryInfo(param2MemoryInfo);
            return;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0)
            param2MemoryInfo.readFromParcel(parcel2); 
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getMemoryTrimLevel() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(189, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getMemoryTrimLevel(); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void getMyMemoryState(ActivityManager.RunningAppProcessInfo param2RunningAppProcessInfo) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(116, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().getMyMemoryState(param2RunningAppProcessInfo);
            return;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0)
            param2RunningAppProcessInfo.readFromParcel(parcel2); 
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getPackageForIntentSender(IIntentSender param2IIntentSender) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getPackageForIntentSender(param2IIntentSender); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getPackageProcessState(String param2String1, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(177, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getPackageProcessState(param2String1, param2String2); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getProcessLimit() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getProcessLimit(); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Debug.MemoryInfo[] getProcessMemoryInfo(int[] param2ArrayOfint) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(89, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getProcessMemoryInfo(param2ArrayOfint); 
          parcel2.readException();
          return (Debug.MemoryInfo[])parcel2.createTypedArray(Debug.MemoryInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public long[] getProcessPss(int[] param2ArrayOfint) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(110, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getProcessPss(param2ArrayOfint); 
          parcel2.readException();
          return parcel2.createLongArray();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<ActivityManager.ProcessErrorStateInfo> getProcessesInErrorState() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getProcessesInErrorState(); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ActivityManager.ProcessErrorStateInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getProviderMimeType(Uri param2Uri, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2Uri != null) {
            parcel1.writeInt(1);
            param2Uri.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(99, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getProviderMimeType(param2Uri, param2Int); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void getProviderMimeTypeAsync(Uri param2Uri, int param2Int, RemoteCallback param2RemoteCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IActivityManager");
          if (param2Uri != null) {
            parcel.writeInt(1);
            param2Uri.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int);
          if (param2RemoteCallback != null) {
            parcel.writeInt(1);
            param2RemoteCallback.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(100, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().getProviderMimeTypeAsync(param2Uri, param2Int, param2RemoteCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public ParceledListSlice getRecentTasks(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ParceledListSlice parceledListSlice;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            parceledListSlice = IActivityManager.Stub.getDefaultImpl().getRecentTasks(param2Int1, param2Int2, param2Int3);
            return parceledListSlice;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
          } else {
            parceledListSlice = null;
          } 
          return parceledListSlice;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getRunningAppProcesses(); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ActivityManager.RunningAppProcessInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<ApplicationInfo> getRunningExternalApplications() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(94, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getRunningExternalApplications(); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ApplicationInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public PendingIntent getRunningServiceControlPanel(ComponentName param2ComponentName) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getRunningServiceControlPanel(param2ComponentName); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel2);
          } else {
            param2ComponentName = null;
          } 
          return (PendingIntent)param2ComponentName;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int[] getRunningUserIds() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(130, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getRunningUserIds(); 
          parcel2.readException();
          return parcel2.createIntArray();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<ActivityManager.RunningServiceInfo> getServices(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getServices(param2Int1, param2Int2); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ActivityManager.RunningServiceInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getTagForIntentSender(IIntentSender param2IIntentSender, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(156, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getTagForIntentSender(param2IIntentSender, param2String); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Rect getTaskBounds(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          Rect rect;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(154, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            rect = IActivityManager.Stub.getDefaultImpl().getTaskBounds(param2Int);
            return rect;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            rect = (Rect)Rect.CREATOR.createFromParcel(parcel2);
          } else {
            rect = null;
          } 
          return rect;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getTaskForActivity(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            i = IActivityManager.Stub.getDefaultImpl().getTaskForActivity(param2IBinder, param2Boolean);
            return i;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          return i;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ActivityManager.TaskSnapshot getTaskSnapshot(int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          ActivityManager.TaskSnapshot taskSnapshot;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(200, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            taskSnapshot = IActivityManager.Stub.getDefaultImpl().getTaskSnapshot(param2Int, param2Boolean);
            return taskSnapshot;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            taskSnapshot = (ActivityManager.TaskSnapshot)ActivityManager.TaskSnapshot.CREATOR.createFromParcel(parcel2);
          } else {
            taskSnapshot = null;
          } 
          return taskSnapshot;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<ActivityManager.RunningTaskInfo> getTasks(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getTasks(param2Int); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getUidForIntentSender(IIntentSender param2IIntentSender) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(84, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().getUidForIntentSender(param2IIntentSender); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getUidProcessState(int param2Int, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Int = IActivityManager.Stub.getDefaultImpl().getUidProcessState(param2Int, param2String);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void grantUriPermission(IApplicationThread param2IApplicationThread, String param2String, Uri param2Uri, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String);
          if (param2Uri != null) {
            parcel1.writeInt(1);
            param2Uri.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().grantUriPermission(param2IApplicationThread, param2String, param2Uri, param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void handleApplicationCrash(IBinder param2IBinder, ApplicationErrorReport.ParcelableCrashInfo param2ParcelableCrashInfo) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2ParcelableCrashInfo != null) {
            parcel1.writeInt(1);
            param2ParcelableCrashInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().handleApplicationCrash(param2IBinder, param2ParcelableCrashInfo);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void handleApplicationStrictModeViolation(IBinder param2IBinder, int param2Int, StrictMode.ViolationInfo param2ViolationInfo) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeInt(param2Int);
          if (param2ViolationInfo != null) {
            parcel1.writeInt(1);
            param2ViolationInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(96, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().handleApplicationStrictModeViolation(param2IBinder, param2Int, param2ViolationInfo);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean handleApplicationWtf(IBinder param2IBinder, String param2String, boolean param2Boolean, ApplicationErrorReport.ParcelableCrashInfo param2ParcelableCrashInfo, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          try {
            parcel1.writeStrongBinder(param2IBinder);
            try {
              boolean bool2;
              parcel1.writeString(param2String);
              boolean bool1 = true;
              if (param2Boolean) {
                bool2 = true;
              } else {
                bool2 = false;
              } 
              parcel1.writeInt(bool2);
              if (param2ParcelableCrashInfo != null) {
                parcel1.writeInt(1);
                param2ParcelableCrashInfo.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeInt(param2Int);
                try {
                  if (!this.mRemote.transact(91, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                    param2Boolean = IActivityManager.Stub.getDefaultImpl().handleApplicationWtf(param2IBinder, param2String, param2Boolean, param2ParcelableCrashInfo, param2Int);
                    parcel2.recycle();
                    parcel1.recycle();
                    return param2Boolean;
                  } 
                  parcel2.readException();
                  param2Int = parcel2.readInt();
                  if (param2Int != 0) {
                    param2Boolean = bool1;
                  } else {
                    param2Boolean = false;
                  } 
                  parcel2.recycle();
                  parcel1.recycle();
                  return param2Boolean;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IBinder;
      }
      
      public int handleIncomingUser(int param2Int1, int param2Int2, int param2Int3, boolean param2Boolean1, boolean param2Boolean2, String param2String1, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeInt(param2Int2);
              try {
                boolean bool2;
                parcel1.writeInt(param2Int3);
                boolean bool1 = true;
                if (param2Boolean1) {
                  bool2 = true;
                } else {
                  bool2 = false;
                } 
                parcel1.writeInt(bool2);
                if (param2Boolean2) {
                  bool2 = bool1;
                } else {
                  bool2 = false;
                } 
                parcel1.writeInt(bool2);
                try {
                  parcel1.writeString(param2String1);
                  try {
                    parcel1.writeString(param2String2);
                    if (!this.mRemote.transact(85, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                      param2Int1 = IActivityManager.Stub.getDefaultImpl().handleIncomingUser(param2Int1, param2Int2, param2Int3, param2Boolean1, param2Boolean2, param2String1, param2String2);
                      parcel2.recycle();
                      parcel1.recycle();
                      return param2Int1;
                    } 
                    parcel2.readException();
                    param2Int1 = parcel2.readInt();
                    parcel2.recycle();
                    parcel1.recycle();
                    return param2Int1;
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2String1;
      }
      
      public void hang(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(146, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().hang(param2IBinder, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isAppFreezerSupported() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(215, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isAppFreezerSupported();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isAppStartModeDisabled(int param2Int, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(184, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isAppStartModeDisabled(param2Int, param2String);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isBackgroundRestricted(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(195, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isBackgroundRestricted(param2String);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isInLockTaskMode() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(158, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isInLockTaskMode();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isIntentSenderABroadcast(IIntentSender param2IIntentSender) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(123, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderABroadcast(param2IIntentSender);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isIntentSenderAForegroundService(IIntentSender param2IIntentSender) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(122, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderAForegroundService(param2IIntentSender);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isIntentSenderAnActivity(IIntentSender param2IIntentSender) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(121, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderAnActivity(param2IIntentSender);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isIntentSenderTargetedToPackage(IIntentSender param2IIntentSender) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(108, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderTargetedToPackage(param2IIntentSender);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isTopActivityImmersive() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(97, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isTopActivityImmersive();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isTopOfTask(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(163, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isTopOfTask(param2IBinder);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isUidActive(int param2Int, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(4, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isUidActive(param2Int, param2String);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isUserAMonkey() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(93, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isUserAMonkey();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isUserRunning(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(102, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isUserRunning(param2Int1, param2Int2);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isVrModePackageEnabled(ComponentName param2ComponentName) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          boolean bool = true;
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(190, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().isVrModePackageEnabled(param2ComponentName);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void killAllBackgroundProcesses() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(112, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().killAllBackgroundProcesses();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void killApplication(String param2String1, int param2Int1, int param2Int2, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String1);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(87, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().killApplication(param2String1, param2Int1, param2Int2, param2String2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void killApplicationProcess(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(90, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().killApplicationProcess(param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void killBackgroundProcesses(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(92, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().killBackgroundProcesses(param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void killPackageDependents(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(186, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().killPackageDependents(param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean killPids(int[] param2ArrayOfint, String param2String, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeIntArray(param2ArrayOfint);
          parcel1.writeString(param2String);
          boolean bool = true;
          if (param2Boolean) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Boolean = IActivityManager.Stub.getDefaultImpl().killPids(param2ArrayOfint, param2String, param2Boolean);
            return param2Boolean;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0) {
            param2Boolean = bool;
          } else {
            param2Boolean = false;
          } 
          return param2Boolean;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean killProcessesBelowForeground(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(117, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().killProcessesBelowForeground(param2String);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void killProcessesWhenImperceptible(int[] param2ArrayOfint, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeIntArray(param2ArrayOfint);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(212, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().killProcessesWhenImperceptible(param2ArrayOfint, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void killUid(int param2Int1, int param2Int2, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(144, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().killUid(param2Int1, param2Int2, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void killUidForPermissionChange(int param2Int1, int param2Int2, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(216, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().killUidForPermissionChange(param2Int1, param2Int2, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean launchBugReportHandlerApp() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(140, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().launchBugReportHandlerApp();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void makePackageIdle(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(188, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().makePackageIdle(param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean moveActivityTaskToBack(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          boolean bool = true;
          if (param2Boolean) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Boolean = IActivityManager.Stub.getDefaultImpl().moveActivityTaskToBack(param2IBinder, param2Boolean);
            return param2Boolean;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0) {
            param2Boolean = bool;
          } else {
            param2Boolean = false;
          } 
          return param2Boolean;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void moveTaskToFront(IApplicationThread param2IApplicationThread, String param2String, int param2Int1, int param2Int2, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().moveTaskToFront(param2IApplicationThread, param2String, param2Int1, param2Int2, param2Bundle);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void moveTaskToStack(int param2Int1, int param2Int2, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(148, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().moveTaskToStack(param2Int1, param2Int2, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean moveTopActivityToPinnedStack(int param2Int, Rect param2Rect) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          boolean bool = true;
          if (param2Rect != null) {
            parcel1.writeInt(1);
            param2Rect.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(183, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().moveTopActivityToPinnedStack(param2Int, param2Rect);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void noteAlarmFinish(IIntentSender param2IIntentSender, WorkSource param2WorkSource, int param2Int, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2WorkSource != null) {
            parcel1.writeInt(1);
            param2WorkSource.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(176, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().noteAlarmFinish(param2IIntentSender, param2WorkSource, param2Int, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void noteAlarmStart(IIntentSender param2IIntentSender, WorkSource param2WorkSource, int param2Int, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2WorkSource != null) {
            parcel1.writeInt(1);
            param2WorkSource.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(175, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().noteAlarmStart(param2IIntentSender, param2WorkSource, param2Int, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void noteWakeupAlarm(IIntentSender param2IIntentSender, WorkSource param2WorkSource, int param2Int, String param2String1, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentSender != null) {
            iBinder = param2IIntentSender.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2WorkSource != null) {
            parcel1.writeInt(1);
            param2WorkSource.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(61, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().noteWakeupAlarm(param2IIntentSender, param2WorkSource, param2Int, param2String1, param2String2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void notifyCleartextNetwork(int param2Int, byte[] param2ArrayOfbyte) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          parcel1.writeByteArray(param2ArrayOfbyte);
          if (!this.mRemote.transact(168, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().notifyCleartextNetwork(param2Int, param2ArrayOfbyte);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void notifyLockedProfile(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(191, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().notifyLockedProfile(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ParcelFileDescriptor openContentUri(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().openContentUri(param2String); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (ParcelFileDescriptor)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IBinder peekService(Intent param2Intent, String param2String1, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
            return IActivityManager.Stub.getDefaultImpl().peekService(param2Intent, param2String1, param2String2); 
          parcel2.readException();
          return parcel2.readStrongBinder();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void performIdleMaintenance() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(152, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().performIdleMaintenance();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void positionTaskInStack(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(181, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().positionTaskInStack(param2Int1, param2Int2, param2Int3);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean profileControl(String param2String, int param2Int1, boolean param2Boolean, ProfilerInfo param2ProfilerInfo, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          try {
            parcel1.writeString(param2String);
            try {
              boolean bool2;
              parcel1.writeInt(param2Int1);
              boolean bool1 = true;
              if (param2Boolean) {
                bool2 = true;
              } else {
                bool2 = false;
              } 
              parcel1.writeInt(bool2);
              if (param2ProfilerInfo != null) {
                parcel1.writeInt(1);
                param2ProfilerInfo.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeInt(param2Int2);
                try {
                  if (!this.mRemote.transact(77, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                    param2Boolean = IActivityManager.Stub.getDefaultImpl().profileControl(param2String, param2Int1, param2Boolean, param2ProfilerInfo, param2Int2);
                    parcel2.recycle();
                    parcel1.recycle();
                    return param2Boolean;
                  } 
                  parcel2.readException();
                  param2Int1 = parcel2.readInt();
                  if (param2Int1 != 0) {
                    param2Boolean = bool1;
                  } else {
                    param2Boolean = false;
                  } 
                  parcel2.recycle();
                  parcel1.recycle();
                  return param2Boolean;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2String;
      }
      
      public void publishContentProviders(IApplicationThread param2IApplicationThread, List<ContentProviderHolder> param2List) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeTypedList(param2List);
          if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().publishContentProviders(param2IApplicationThread, param2List);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void publishService(IBinder param2IBinder1, Intent param2Intent, IBinder param2IBinder2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder1);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeStrongBinder(param2IBinder2);
          if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().publishService(param2IBinder1, param2Intent, param2IBinder2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean refContentProvider(IBinder param2IBinder, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(24, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().refContentProvider(param2IBinder, param2Int1, param2Int2);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerIntentSenderCancelListener(IIntentSender param2IIntentSender, IResultReceiver param2IResultReceiver) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder1 = null;
          if (param2IIntentSender != null) {
            iBinder2 = param2IIntentSender.asBinder();
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          IBinder iBinder2 = iBinder1;
          if (param2IResultReceiver != null)
            iBinder2 = param2IResultReceiver.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          if (!this.mRemote.transact(58, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().registerIntentSenderCancelListener(param2IIntentSender, param2IResultReceiver);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerProcessObserver(IProcessObserver param2IProcessObserver) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IProcessObserver != null) {
            iBinder = param2IProcessObserver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(106, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().registerProcessObserver(param2IProcessObserver);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Intent registerReceiver(IApplicationThread param2IApplicationThread, String param2String1, IIntentReceiver param2IIntentReceiver, IntentFilter param2IntentFilter, String param2String2, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder2;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder1 = null;
          if (param2IApplicationThread != null) {
            iBinder2 = param2IApplicationThread.asBinder();
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          try {
            parcel1.writeString(param2String1);
            iBinder2 = iBinder1;
            if (param2IIntentReceiver != null)
              iBinder2 = param2IIntentReceiver.asBinder(); 
            parcel1.writeStrongBinder(iBinder2);
            if (param2IntentFilter != null) {
              parcel1.writeInt(1);
              param2IntentFilter.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeString(param2String2);
              try {
                parcel1.writeInt(param2Int1);
                try {
                  parcel1.writeInt(param2Int2);
                  if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                    Intent intent = IActivityManager.Stub.getDefaultImpl().registerReceiver(param2IApplicationThread, param2String1, param2IIntentReceiver, param2IntentFilter, param2String2, param2Int1, param2Int2);
                    parcel2.recycle();
                    parcel1.recycle();
                    return intent;
                  } 
                  parcel2.readException();
                  if (parcel2.readInt() != 0) {
                    Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
                  } else {
                    param2IApplicationThread = null;
                  } 
                  parcel2.recycle();
                  parcel1.recycle();
                  return (Intent)param2IApplicationThread;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public Intent registerReceiverWithFeature(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, IIntentReceiver param2IIntentReceiver, IntentFilter param2IntentFilter, String param2String3, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder2;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder1 = null;
          if (param2IApplicationThread != null) {
            iBinder2 = param2IApplicationThread.asBinder();
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          try {
            parcel1.writeString(param2String1);
            try {
              parcel1.writeString(param2String2);
              iBinder2 = iBinder1;
              if (param2IIntentReceiver != null)
                iBinder2 = param2IIntentReceiver.asBinder(); 
              parcel1.writeStrongBinder(iBinder2);
              if (param2IntentFilter != null) {
                parcel1.writeInt(1);
                param2IntentFilter.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeString(param2String3);
                parcel1.writeInt(param2Int1);
                parcel1.writeInt(param2Int2);
                if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                  Intent intent = IActivityManager.Stub.getDefaultImpl().registerReceiverWithFeature(param2IApplicationThread, param2String1, param2String2, param2IIntentReceiver, param2IntentFilter, param2String3, param2Int1, param2Int2);
                  parcel2.recycle();
                  parcel1.recycle();
                  return intent;
                } 
                parcel2.readException();
                if (parcel2.readInt() != 0) {
                  Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
                } else {
                  param2IApplicationThread = null;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return (Intent)param2IApplicationThread;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public void registerTaskStackListener(ITaskStackListener param2ITaskStackListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2ITaskStackListener != null) {
            iBinder = param2ITaskStackListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(166, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().registerTaskStackListener(param2ITaskStackListener);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerUidObserver(IUidObserver param2IUidObserver, int param2Int1, int param2Int2, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IUidObserver != null) {
            iBinder = param2IUidObserver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().registerUidObserver(param2IUidObserver, param2Int1, param2Int2, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerUserSwitchObserver(IUserSwitchObserver param2IUserSwitchObserver, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IUserSwitchObserver != null) {
            iBinder = param2IUserSwitchObserver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(128, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().registerUserSwitchObserver(param2IUserSwitchObserver, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void removeContentProvider(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.app.IActivityManager");
          parcel.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(62, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().removeContentProvider(param2IBinder, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void removeContentProviderExternal(String param2String, IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(114, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().removeContentProviderExternal(param2String, param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void removeContentProviderExternalAsUser(String param2String, IBinder param2IBinder, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(115, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().removeContentProviderExternalAsUser(param2String, param2IBinder, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void removeStack(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(187, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().removeStack(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean removeTask(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(105, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().removeTask(param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestBugReport(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(132, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().requestBugReport(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestBugReportWithDescription(String param2String1, String param2String2, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(133, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().requestBugReportWithDescription(param2String1, param2String2, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestFullBugReport() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(138, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().requestFullBugReport();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestInteractiveBugReport() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(137, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().requestInteractiveBugReport();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestInteractiveBugReportWithDescription(String param2String1, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(136, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().requestInteractiveBugReportWithDescription(param2String1, param2String2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestRemoteBugReport() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(139, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().requestRemoteBugReport();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestSystemServerHeapDump() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(131, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().requestSystemServerHeapDump();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestTelephonyBugReport(String param2String1, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(134, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().requestTelephonyBugReport(param2String1, param2String2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestWifiBugReport(String param2String1, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(135, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().requestWifiBugReport(param2String1, param2String2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void resizeTask(int param2Int1, Rect param2Rect, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int1);
          if (param2Rect != null) {
            parcel1.writeInt(1);
            param2Rect.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(170, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().resizeTask(param2Int1, param2Rect, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void restart() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(151, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().restart();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int restartUserInBackground(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(198, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Int = IActivityManager.Stub.getDefaultImpl().restartUserInBackground(param2Int);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void resumeAppSwitches() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(80, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().resumeAppSwitches();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void revokeUriPermission(IApplicationThread param2IApplicationThread, String param2String, Uri param2Uri, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String);
          if (param2Uri != null) {
            parcel1.writeInt(1);
            param2Uri.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().revokeUriPermission(param2IApplicationThread, param2String, param2Uri, param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void scheduleApplicationInfoChanged(List<String> param2List, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStringList(param2List);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(201, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().scheduleApplicationInfoChanged(param2List, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void sendIdleJobTrigger() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(193, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().sendIdleJobTrigger();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int sendIntentSender(IIntentSender param2IIntentSender, IBinder param2IBinder, int param2Int, Intent param2Intent, String param2String1, IIntentReceiver param2IIntentReceiver, String param2String2, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder2;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder1 = null;
          if (param2IIntentSender != null) {
            iBinder2 = param2IIntentSender.asBinder();
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          try {
            parcel1.writeStrongBinder(param2IBinder);
            try {
              parcel1.writeInt(param2Int);
              if (param2Intent != null) {
                parcel1.writeInt(1);
                param2Intent.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              parcel1.writeString(param2String1);
              iBinder2 = iBinder1;
              if (param2IIntentReceiver != null)
                iBinder2 = param2IIntentReceiver.asBinder(); 
              parcel1.writeStrongBinder(iBinder2);
              parcel1.writeString(param2String2);
              if (param2Bundle != null) {
                parcel1.writeInt(1);
                param2Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (!this.mRemote.transact(194, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                param2Int = IActivityManager.Stub.getDefaultImpl().sendIntentSender(param2IIntentSender, param2IBinder, param2Int, param2Intent, param2String1, param2IIntentReceiver, param2String2, param2Bundle);
                parcel2.recycle();
                parcel1.recycle();
                return param2Int;
              } 
              parcel2.readException();
              param2Int = parcel2.readInt();
              parcel2.recycle();
              parcel1.recycle();
              return param2Int;
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IIntentSender;
      }
      
      public void serviceDoneExecuting(IBinder param2IBinder, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IActivityManager");
          parcel.writeStrongBinder(param2IBinder);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeInt(param2Int3);
          if (!this.mRemote.transact(53, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().serviceDoneExecuting(param2IBinder, param2Int1, param2Int2, param2Int3);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void setActivityController(IActivityController param2IActivityController, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IActivityController != null) {
            iBinder = param2IActivityController.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setActivityController(param2IActivityController, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setActivityLocusContext(ComponentName param2ComponentName, LocusId param2LocusId, IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2LocusId != null) {
            parcel1.writeInt(1);
            param2LocusId.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(213, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setActivityLocusContext(param2ComponentName, param2LocusId, param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setAgentApp(String param2String1, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setAgentApp(param2String1, param2String2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setAlwaysFinish(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setAlwaysFinish(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setDebugApp(String param2String, boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool2;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          boolean bool1 = true;
          if (param2Boolean1) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (param2Boolean2) {
            bool2 = bool1;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setDebugApp(param2String, param2Boolean1, param2Boolean2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setDumpHeapDebugLimit(String param2String1, int param2Int, long param2Long, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String1);
          parcel1.writeInt(param2Int);
          parcel1.writeLong(param2Long);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(172, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setDumpHeapDebugLimit(param2String1, param2Int, param2Long, param2String2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setFocusedStack(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(149, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setFocusedStack(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setHasTopUi(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(197, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setHasTopUi(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setPackageScreenCompatMode(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(103, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setPackageScreenCompatMode(param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setPersistentVrThread(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(202, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setPersistentVrThread(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setProcessImportant(IBinder param2IBinder, int param2Int, boolean param2Boolean, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setProcessImportant(param2IBinder, param2Int, param2Boolean, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setProcessLimit(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setProcessLimit(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean setProcessMemoryTrimLevel(String param2String, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(155, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().setProcessMemoryTrimLevel(param2String, param2Int1, param2Int2);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setProcessStateSummary(byte[] param2ArrayOfbyte) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeByteArray(param2ArrayOfbyte);
          if (!this.mRemote.transact(214, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setProcessStateSummary(param2ArrayOfbyte);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setRenderThread(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(196, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setRenderThread(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setRequestedOrientation(IBinder param2IBinder, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(63, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setRequestedOrientation(param2IBinder, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setServiceForeground(ComponentName param2ComponentName, IBinder param2IBinder, int param2Int1, Notification param2Notification, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeStrongBinder(param2IBinder);
            try {
              parcel1.writeInt(param2Int1);
              if (param2Notification != null) {
                parcel1.writeInt(1);
                param2Notification.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeInt(param2Int2);
                try {
                  parcel1.writeInt(param2Int3);
                  if (!this.mRemote.transact(66, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                    IActivityManager.Stub.getDefaultImpl().setServiceForeground(param2ComponentName, param2IBinder, param2Int1, param2Notification, param2Int2, param2Int3);
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } 
                  parcel2.readException();
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2ComponentName;
      }
      
      public void setTaskResizeable(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(169, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setTaskResizeable(param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setUserIsMonkey(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(145, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().setUserIsMonkey(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void showBootMessage(CharSequence param2CharSequence, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          boolean bool = true;
          if (param2CharSequence != null) {
            parcel1.writeInt(1);
            TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!param2Boolean)
            bool = false; 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(111, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().showBootMessage(param2CharSequence, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void showWaitingForDebugger(IApplicationThread param2IApplicationThread, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(50, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().showWaitingForDebugger(param2IApplicationThread, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean shutdown(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(78, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().shutdown(param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void signalPersistentProcesses(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().signalPersistentProcesses(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int startActivity(IApplicationThread param2IApplicationThread, String param2String1, Intent param2Intent, String param2String2, IBinder param2IBinder, String param2String3, int param2Int1, int param2Int2, ProfilerInfo param2ProfilerInfo, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            try {
              iBinder = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String1);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String2);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String3);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2ProfilerInfo != null) {
            parcel1.writeInt(1);
            param2ProfilerInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityManager.startActivity(param2IApplicationThread, param2String1, param2Intent, param2String2, param2IBinder, param2String3, param2Int1, param2Int2, param2ProfilerInfo, param2Bundle);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public int startActivityAsUser(IApplicationThread param2IApplicationThread, String param2String1, Intent param2Intent, String param2String2, IBinder param2IBinder, String param2String3, int param2Int1, int param2Int2, ProfilerInfo param2ProfilerInfo, Bundle param2Bundle, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            try {
              iBinder = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String1);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String2);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String3);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2ProfilerInfo != null) {
            parcel1.writeInt(1);
            param2ProfilerInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(124, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityManager.startActivityAsUser(param2IApplicationThread, param2String1, param2Intent, param2String2, param2IBinder, param2String3, param2Int1, param2Int2, param2ProfilerInfo, param2Bundle, param2Int3);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public int startActivityAsUserWithFeature(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, Intent param2Intent, String param2String3, IBinder param2IBinder, String param2String4, int param2Int1, int param2Int2, ProfilerInfo param2ProfilerInfo, Bundle param2Bundle, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            try {
              iBinder = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String3);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String4);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2ProfilerInfo != null) {
            parcel1.writeInt(1);
            param2ProfilerInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(125, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityManager.startActivityAsUserWithFeature(param2IApplicationThread, param2String1, param2String2, param2Intent, param2String3, param2IBinder, param2String4, param2Int1, param2Int2, param2ProfilerInfo, param2Bundle, param2Int3);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public int startActivityFromRecents(int param2Int, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(161, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Int = IActivityManager.Stub.getDefaultImpl().startActivityFromRecents(param2Int, param2Bundle);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int startActivityWithFeature(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, Intent param2Intent, String param2String3, IBinder param2IBinder, String param2String4, int param2Int1, int param2Int2, ProfilerInfo param2ProfilerInfo, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            try {
              iBinder = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String3);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String4);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2ProfilerInfo != null) {
            parcel1.writeInt(1);
            param2ProfilerInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityManager.startActivityWithFeature(param2IApplicationThread, param2String1, param2String2, param2Intent, param2String3, param2IBinder, param2String4, param2Int1, param2Int2, param2ProfilerInfo, param2Bundle);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public boolean startBinderTracking() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(179, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().startBinderTracking();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void startConfirmDeviceCredentialIntent(Intent param2Intent, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(192, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().startConfirmDeviceCredentialIntent(param2Intent, param2Bundle);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void startDelegateShellPermissionIdentity(int param2Int, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          parcel1.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(206, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().startDelegateShellPermissionIdentity(param2Int, param2ArrayOfString);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean startInstrumentation(ComponentName param2ComponentName, String param2String1, int param2Int1, Bundle param2Bundle, IInstrumentationWatcher param2IInstrumentationWatcher, IUiAutomationConnection param2IUiAutomationConnection, int param2Int2, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          boolean bool = true;
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeString(param2String1);
            try {
              parcel1.writeInt(param2Int1);
              if (param2Bundle != null) {
                parcel1.writeInt(1);
                param2Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              IBinder iBinder1 = null;
              if (param2IInstrumentationWatcher != null) {
                iBinder2 = param2IInstrumentationWatcher.asBinder();
              } else {
                iBinder2 = null;
              } 
              parcel1.writeStrongBinder(iBinder2);
              IBinder iBinder2 = iBinder1;
              if (param2IUiAutomationConnection != null)
                iBinder2 = param2IUiAutomationConnection.asBinder(); 
              parcel1.writeStrongBinder(iBinder2);
              parcel1.writeInt(param2Int2);
              parcel1.writeString(param2String2);
              if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                bool = IActivityManager.Stub.getDefaultImpl().startInstrumentation(param2ComponentName, param2String1, param2Int1, param2Bundle, param2IInstrumentationWatcher, param2IUiAutomationConnection, param2Int2, param2String2);
                parcel2.recycle();
                parcel1.recycle();
                return bool;
              } 
              parcel2.readException();
              param2Int1 = parcel2.readInt();
              if (param2Int1 == 0)
                bool = false; 
              parcel2.recycle();
              parcel1.recycle();
              return bool;
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2ComponentName;
      }
      
      public void startRecentsActivity(Intent param2Intent, IAssistDataReceiver param2IAssistDataReceiver, IRecentsAnimationRunner param2IRecentsAnimationRunner) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          IBinder iBinder1 = null;
          if (param2IAssistDataReceiver != null) {
            iBinder2 = param2IAssistDataReceiver.asBinder();
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          IBinder iBinder2 = iBinder1;
          if (param2IRecentsAnimationRunner != null)
            iBinder2 = param2IRecentsAnimationRunner.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          if (!this.mRemote.transact(159, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().startRecentsActivity(param2Intent, param2IAssistDataReceiver, param2IRecentsAnimationRunner);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ComponentName startService(IApplicationThread param2IApplicationThread, Intent param2Intent, String param2String1, boolean param2Boolean, String param2String2, String param2String3, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          boolean bool = true;
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeString(param2String1);
            if (!param2Boolean)
              bool = false; 
            parcel1.writeInt(bool);
            try {
              parcel1.writeString(param2String2);
              try {
                parcel1.writeString(param2String3);
                try {
                  parcel1.writeInt(param2Int);
                  if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                    ComponentName componentName = IActivityManager.Stub.getDefaultImpl().startService(param2IApplicationThread, param2Intent, param2String1, param2Boolean, param2String2, param2String3, param2Int);
                    parcel2.recycle();
                    parcel1.recycle();
                    return componentName;
                  } 
                  parcel2.readException();
                  if (parcel2.readInt() != 0) {
                    ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
                  } else {
                    param2IApplicationThread = null;
                  } 
                  parcel2.recycle();
                  parcel1.recycle();
                  return (ComponentName)param2IApplicationThread;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public void startSystemLockTaskMode(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(162, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().startSystemLockTaskMode(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean startUserInBackground(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(157, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().startUserInBackground(param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean startUserInBackgroundWithListener(int param2Int, IProgressListener param2IProgressListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (param2IProgressListener != null) {
            iBinder = param2IProgressListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(205, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().startUserInBackgroundWithListener(param2Int, param2IProgressListener);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean startUserInForegroundWithListener(int param2Int, IProgressListener param2IProgressListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (param2IProgressListener != null) {
            iBinder = param2IProgressListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(209, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().startUserInForegroundWithListener(param2Int, param2IProgressListener);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void stopAppSwitches() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(79, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().stopAppSwitches();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean stopBinderTrackingAndDump(ParcelFileDescriptor param2ParcelFileDescriptor) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          boolean bool = true;
          if (param2ParcelFileDescriptor != null) {
            parcel1.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(180, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().stopBinderTrackingAndDump(param2ParcelFileDescriptor);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void stopDelegateShellPermissionIdentity() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(207, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().stopDelegateShellPermissionIdentity();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int stopService(IApplicationThread param2IApplicationThread, Intent param2Intent, String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Int = IActivityManager.Stub.getDefaultImpl().stopService(param2IApplicationThread, param2Intent, param2String, param2Int);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean stopServiceToken(ComponentName param2ComponentName, IBinder param2IBinder, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          boolean bool = true;
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().stopServiceToken(param2ComponentName, param2IBinder, param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int stopUser(int param2Int, boolean param2Boolean, IStopUserCallback param2IStopUserCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (param2IStopUserCallback != null) {
            iBinder = param2IStopUserCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(126, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Int = IActivityManager.Stub.getDefaultImpl().stopUser(param2Int, param2Boolean, param2IStopUserCallback);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int stopUserWithDelayedLocking(int param2Int, boolean param2Boolean, IStopUserCallback param2IStopUserCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (param2IStopUserCallback != null) {
            iBinder = param2IStopUserCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(127, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            param2Int = IActivityManager.Stub.getDefaultImpl().stopUserWithDelayedLocking(param2Int, param2Boolean, param2IStopUserCallback);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void suppressResizeConfigChanges(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(182, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().suppressResizeConfigChanges(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean switchUser(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(104, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().switchUser(param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unbindBackupAgent(ApplicationInfo param2ApplicationInfo) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2ApplicationInfo != null) {
            parcel1.writeInt(1);
            param2ApplicationInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(83, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().unbindBackupAgent(param2ApplicationInfo);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unbindFinished(IBinder param2IBinder, Intent param2Intent, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          boolean bool = true;
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!param2Boolean)
            bool = false; 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(64, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().unbindFinished(param2IBinder, param2Intent, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean unbindService(IServiceConnection param2IServiceConnection) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IServiceConnection != null) {
            iBinder = param2IServiceConnection.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(31, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().unbindService(param2IServiceConnection);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unbroadcastIntent(IApplicationThread param2IApplicationThread, Intent param2Intent, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().unbroadcastIntent(param2IApplicationThread, param2Intent, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unhandledBack() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().unhandledBack();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean unlockUser(int param2Int, byte[] param2ArrayOfbyte1, byte[] param2ArrayOfbyte2, IProgressListener param2IProgressListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          parcel1.writeByteArray(param2ArrayOfbyte1);
          parcel1.writeByteArray(param2ArrayOfbyte2);
          if (param2IProgressListener != null) {
            iBinder = param2IProgressListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(185, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().unlockUser(param2Int, param2ArrayOfbyte1, param2ArrayOfbyte2, param2IProgressListener);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unregisterIntentSenderCancelListener(IIntentSender param2IIntentSender, IResultReceiver param2IResultReceiver) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          IBinder iBinder1 = null;
          if (param2IIntentSender != null) {
            iBinder2 = param2IIntentSender.asBinder();
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          IBinder iBinder2 = iBinder1;
          if (param2IResultReceiver != null)
            iBinder2 = param2IResultReceiver.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().unregisterIntentSenderCancelListener(param2IIntentSender, param2IResultReceiver);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unregisterProcessObserver(IProcessObserver param2IProcessObserver) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IProcessObserver != null) {
            iBinder = param2IProcessObserver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(107, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().unregisterProcessObserver(param2IProcessObserver);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unregisterReceiver(IIntentReceiver param2IIntentReceiver) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IIntentReceiver != null) {
            iBinder = param2IIntentReceiver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().unregisterReceiver(param2IIntentReceiver);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unregisterTaskStackListener(ITaskStackListener param2ITaskStackListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2ITaskStackListener != null) {
            iBinder = param2ITaskStackListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(167, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().unregisterTaskStackListener(param2ITaskStackListener);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unregisterUidObserver(IUidObserver param2IUidObserver) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IUidObserver != null) {
            iBinder = param2IUidObserver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().unregisterUidObserver(param2IUidObserver);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unregisterUserSwitchObserver(IUserSwitchObserver param2IUserSwitchObserver) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IUserSwitchObserver != null) {
            iBinder = param2IUserSwitchObserver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(129, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().unregisterUserSwitchObserver(param2IUserSwitchObserver);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unstableProviderDied(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(120, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().unstableProviderDied(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean updateConfiguration(Configuration param2Configuration) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          boolean bool = true;
          if (param2Configuration != null) {
            parcel1.writeInt(1);
            param2Configuration.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().updateConfiguration(param2Configuration);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void updateDeviceOwner(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(178, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().updateDeviceOwner(param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void updateLockTaskPackages(int param2Int, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeInt(param2Int);
          parcel1.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(174, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().updateLockTaskPackages(param2Int, param2ArrayOfString);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean updateMccMncConfiguration(String param2String1, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(41, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().updateMccMncConfiguration(param2String1, param2String2);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void updatePersistentConfiguration(Configuration param2Configuration) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2Configuration != null) {
            parcel1.writeInt(1);
            param2Configuration.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(109, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().updatePersistentConfiguration(param2Configuration);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void updateServiceGroup(IServiceConnection param2IServiceConnection, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          if (param2IServiceConnection != null) {
            iBinder = param2IServiceConnection.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().updateServiceGroup(param2IServiceConnection, param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void waitForNetworkStateUpdate(long param2Long) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityManager");
          parcel1.writeLong(param2Long);
          if (!this.mRemote.transact(203, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IActivityManager.Stub.getDefaultImpl().waitForNetworkStateUpdate(param2Long);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IActivityManager {
    public static IActivityManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public void addInstrumentationResults(IApplicationThread param1IApplicationThread, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().addInstrumentationResults(param1IApplicationThread, param1Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addPackageDependency(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(86, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().addPackageDependency(param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void appNotResponding(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(210, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().appNotResponding(param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void appNotRespondingViaProvider(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(153, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().appNotRespondingViaProvider(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void attachApplication(IApplicationThread param1IApplicationThread, long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeLong(param1Long);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().attachApplication(param1IApplicationThread, param1Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void backgroundWhitelistUid(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(204, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().backgroundWhitelistUid(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void backupAgentCreated(String param1String, IBinder param1IBinder, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(82, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().backupAgentCreated(param1String, param1IBinder, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean bindBackupAgent(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(81, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().bindBackupAgent(param1String, param1Int1, param1Int2);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int bindIsolatedService(IApplicationThread param1IApplicationThread, IBinder param1IBinder, Intent param1Intent, String param1String1, IServiceConnection param1IServiceConnection, int param1Int1, String param1String2, String param1String3, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder2;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder1 = null;
        if (param1IApplicationThread != null) {
          iBinder2 = param1IApplicationThread.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        try {
          parcel1.writeStrongBinder(param1IBinder);
          if (param1Intent != null) {
            parcel1.writeInt(1);
            param1Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeString(param1String1);
            iBinder2 = iBinder1;
            if (param1IServiceConnection != null)
              iBinder2 = param1IServiceConnection.asBinder(); 
            parcel1.writeStrongBinder(iBinder2);
            parcel1.writeInt(param1Int1);
            parcel1.writeString(param1String2);
            parcel1.writeString(param1String3);
            parcel1.writeInt(param1Int2);
            if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
              param1Int1 = IActivityManager.Stub.getDefaultImpl().bindIsolatedService(param1IApplicationThread, param1IBinder, param1Intent, param1String1, param1IServiceConnection, param1Int1, param1String2, param1String3, param1Int2);
              parcel2.recycle();
              parcel1.recycle();
              return param1Int1;
            } 
            parcel2.readException();
            param1Int1 = parcel2.readInt();
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public int bindService(IApplicationThread param1IApplicationThread, IBinder param1IBinder, Intent param1Intent, String param1String1, IServiceConnection param1IServiceConnection, int param1Int1, String param1String2, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder2;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder1 = null;
        if (param1IApplicationThread != null) {
          iBinder2 = param1IApplicationThread.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        try {
          parcel1.writeStrongBinder(param1IBinder);
          if (param1Intent != null) {
            parcel1.writeInt(1);
            param1Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeString(param1String1);
            iBinder2 = iBinder1;
            if (param1IServiceConnection != null)
              iBinder2 = param1IServiceConnection.asBinder(); 
            parcel1.writeStrongBinder(iBinder2);
            try {
              parcel1.writeInt(param1Int1);
              parcel1.writeString(param1String2);
              parcel1.writeInt(param1Int2);
              if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                param1Int1 = IActivityManager.Stub.getDefaultImpl().bindService(param1IApplicationThread, param1IBinder, param1Intent, param1String1, param1IServiceConnection, param1Int1, param1String2, param1Int2);
                parcel2.recycle();
                parcel1.recycle();
                return param1Int1;
              } 
              parcel2.readException();
              param1Int1 = parcel2.readInt();
              parcel2.recycle();
              parcel1.recycle();
              return param1Int1;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public void bootAnimationComplete() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(164, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().bootAnimationComplete();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int broadcastIntent(IApplicationThread param1IApplicationThread, Intent param1Intent, String param1String1, IIntentReceiver param1IIntentReceiver, int param1Int1, String param1String2, Bundle param1Bundle1, String[] param1ArrayOfString, int param1Int2, Bundle param1Bundle2, boolean param1Boolean1, boolean param1Boolean2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder1 = null;
        if (param1IApplicationThread != null) {
          try {
            iBinder2 = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        boolean bool1 = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String1);
        IBinder iBinder2 = iBinder1;
        if (param1IIntentReceiver != null)
          iBinder2 = param1IIntentReceiver.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        parcel1.writeInt(param1Int1);
        parcel1.writeString(param1String2);
        if (param1Bundle1 != null) {
          parcel1.writeInt(1);
          param1Bundle1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStringArray(param1ArrayOfString);
        parcel1.writeInt(param1Int2);
        if (param1Bundle2 != null) {
          parcel1.writeInt(1);
          param1Bundle2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param1Boolean2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityManager.broadcastIntent(param1IApplicationThread, param1Intent, param1String1, param1IIntentReceiver, param1Int1, param1String2, param1Bundle1, param1ArrayOfString, param1Int2, param1Bundle2, param1Boolean1, param1Boolean2, param1Int3);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public int broadcastIntentWithFeature(IApplicationThread param1IApplicationThread, String param1String1, Intent param1Intent, String param1String2, IIntentReceiver param1IIntentReceiver, int param1Int1, String param1String3, Bundle param1Bundle1, String[] param1ArrayOfString, int param1Int2, Bundle param1Bundle2, boolean param1Boolean1, boolean param1Boolean2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder1 = null;
        if (param1IApplicationThread != null) {
          try {
            iBinder2 = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        parcel1.writeString(param1String1);
        boolean bool1 = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String2);
        IBinder iBinder2 = iBinder1;
        if (param1IIntentReceiver != null)
          iBinder2 = param1IIntentReceiver.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        parcel1.writeInt(param1Int1);
        parcel1.writeString(param1String3);
        if (param1Bundle1 != null) {
          parcel1.writeInt(1);
          param1Bundle1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStringArray(param1ArrayOfString);
        parcel1.writeInt(param1Int2);
        if (param1Bundle2 != null) {
          parcel1.writeInt(1);
          param1Bundle2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param1Boolean2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityManager.broadcastIntentWithFeature(param1IApplicationThread, param1String1, param1Intent, param1String2, param1IIntentReceiver, param1Int1, param1String3, param1Bundle1, param1ArrayOfString, param1Int2, param1Bundle2, param1Boolean1, param1Boolean2, param1Int3);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public void cancelIntentSender(IIntentSender param1IIntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().cancelIntentSender(param1IIntentSender);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelRecentsAnimation(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(160, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().cancelRecentsAnimation(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelTaskWindowTransition(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(199, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().cancelTaskWindowTransition(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int checkPermission(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Int1 = IActivityManager.Stub.getDefaultImpl().checkPermission(param1String, param1Int1, param1Int2);
          return param1Int1;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        return param1Int1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int checkPermissionWithToken(String param1String, int param1Int1, int param1Int2, IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(165, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Int1 = IActivityManager.Stub.getDefaultImpl().checkPermissionWithToken(param1String, param1Int1, param1Int2, param1IBinder);
          return param1Int1;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        return param1Int1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int checkUriPermission(Uri param1Uri, int param1Int1, int param1Int2, int param1Int3, int param1Int4, IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1Uri != null) {
          parcel1.writeInt(1);
          param1Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeInt(param1Int1);
          try {
            parcel1.writeInt(param1Int2);
            try {
              parcel1.writeInt(param1Int3);
              try {
                parcel1.writeInt(param1Int4);
                try {
                  parcel1.writeStrongBinder(param1IBinder);
                  if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                    param1Int1 = IActivityManager.Stub.getDefaultImpl().checkUriPermission(param1Uri, param1Int1, param1Int2, param1Int3, param1Int4, param1IBinder);
                    parcel2.recycle();
                    parcel1.recycle();
                    return param1Int1;
                  } 
                  parcel2.readException();
                  param1Int1 = parcel2.readInt();
                  parcel2.recycle();
                  parcel1.recycle();
                  return param1Int1;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1Uri;
    }
    
    public boolean clearApplicationUserData(String param1String, boolean param1Boolean, IPackageDataObserver param1IPackageDataObserver, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        boolean bool1 = true;
        if (param1Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param1IPackageDataObserver != null) {
          iBinder = param1IPackageDataObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(71, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Boolean = IActivityManager.Stub.getDefaultImpl().clearApplicationUserData(param1String, param1Boolean, param1IPackageDataObserver, param1Int);
          return param1Boolean;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0) {
          param1Boolean = bool1;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void closeSystemDialogs(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(88, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().closeSystemDialogs(param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void crashApplication(int param1Int1, int param1Int2, String param1String1, int param1Int3, String param1String2, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        try {
          parcel1.writeInt(param1Int1);
          try {
            parcel1.writeInt(param1Int2);
            try {
              parcel1.writeString(param1String1);
              try {
                parcel1.writeInt(param1Int3);
                try {
                  boolean bool;
                  parcel1.writeString(param1String2);
                  if (param1Boolean) {
                    bool = true;
                  } else {
                    bool = false;
                  } 
                  parcel1.writeInt(bool);
                  try {
                    if (!this.mRemote.transact(98, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                      IActivityManager.Stub.getDefaultImpl().crashApplication(param1Int1, param1Int2, param1String1, param1Int3, param1String2, param1Boolean);
                      parcel2.recycle();
                      parcel1.recycle();
                      return;
                    } 
                    parcel2.readException();
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1String1;
    }
    
    public boolean dumpHeap(String param1String1, int param1Int, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, String param1String2, ParcelFileDescriptor param1ParcelFileDescriptor, RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        try {
          parcel1.writeString(param1String1);
          try {
            boolean bool2;
            parcel1.writeInt(param1Int);
            boolean bool1 = true;
            if (param1Boolean1) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            parcel1.writeInt(bool2);
            if (param1Boolean2) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            parcel1.writeInt(bool2);
            if (param1Boolean3) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            parcel1.writeInt(bool2);
            parcel1.writeString(param1String2);
            if (param1ParcelFileDescriptor != null) {
              parcel1.writeInt(1);
              param1ParcelFileDescriptor.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            if (param1RemoteCallback != null) {
              parcel1.writeInt(1);
              param1RemoteCallback.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            if (!this.mRemote.transact(101, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
              param1Boolean1 = IActivityManager.Stub.getDefaultImpl().dumpHeap(param1String1, param1Int, param1Boolean1, param1Boolean2, param1Boolean3, param1String2, param1ParcelFileDescriptor, param1RemoteCallback);
              parcel2.recycle();
              parcel1.recycle();
              return param1Boolean1;
            } 
            parcel2.readException();
            param1Int = parcel2.readInt();
            if (param1Int != 0) {
              param1Boolean1 = bool1;
            } else {
              param1Boolean1 = false;
            } 
            parcel2.recycle();
            parcel1.recycle();
            return param1Boolean1;
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1String1;
    }
    
    public void dumpHeapFinished(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(173, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().dumpHeapFinished(param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean enableAppFreezer(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        boolean bool = true;
        if (param1Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(217, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Boolean = IActivityManager.Stub.getDefaultImpl().enableAppFreezer(param1Boolean);
          return param1Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param1Boolean = bool;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enterSafeMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().enterSafeMode();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean finishActivity(IBinder param1IBinder, int param1Int1, Intent param1Intent, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int1);
        boolean bool = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().finishActivity(param1IBinder, param1Int1, param1Intent, param1Int2);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void finishHeavyWeightApp() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(95, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().finishHeavyWeightApp();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void finishInstrumentation(IApplicationThread param1IApplicationThread, int param1Int, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().finishInstrumentation(param1IApplicationThread, param1Int, param1Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void finishReceiver(IBinder param1IBinder, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IActivityManager");
        try {
          parcel.writeStrongBinder(param1IBinder);
          try {
            parcel.writeInt(param1Int1);
            try {
              parcel.writeString(param1String);
              boolean bool = false;
              if (param1Bundle != null) {
                parcel.writeInt(1);
                param1Bundle.writeToParcel(parcel, 0);
              } else {
                parcel.writeInt(0);
              } 
              if (param1Boolean)
                bool = true; 
              parcel.writeInt(bool);
              try {
                parcel.writeInt(param1Int2);
                try {
                  if (!this.mRemote.transact(17, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
                    IActivityManager.Stub.getDefaultImpl().finishReceiver(param1IBinder, param1Int1, param1String, param1Bundle, param1Boolean, param1Int2);
                    parcel.recycle();
                    return;
                  } 
                  parcel.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param1IBinder;
    }
    
    public void forceStopPackage(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(72, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().forceStopPackage(param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(147, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getAllStackInfos(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ActivityManager.StackInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getBugreportWhitelistedPackages() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(141, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getBugreportWhitelistedPackages(); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Configuration getConfiguration() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Configuration configuration;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          configuration = IActivityManager.Stub.getDefaultImpl().getConfiguration();
          return configuration;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          configuration = (Configuration)Configuration.CREATOR.createFromParcel(parcel2);
        } else {
          configuration = null;
        } 
        return configuration;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ContentProviderHolder getContentProvider(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        parcel1.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getContentProvider(param1IApplicationThread, param1String1, param1String2, param1Int, param1Boolean); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ContentProviderHolder contentProviderHolder = (ContentProviderHolder)ContentProviderHolder.CREATOR.createFromParcel(parcel2);
        } else {
          param1IApplicationThread = null;
        } 
        return (ContentProviderHolder)param1IApplicationThread;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ContentProviderHolder getContentProviderExternal(String param1String1, int param1Int, IBinder param1IBinder, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String1);
        parcel1.writeInt(param1Int);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(113, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getContentProviderExternal(param1String1, param1Int, param1IBinder, param1String2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ContentProviderHolder contentProviderHolder = (ContentProviderHolder)ContentProviderHolder.CREATOR.createFromParcel(parcel2);
        } else {
          param1String1 = null;
        } 
        return (ContentProviderHolder)param1String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public UserInfo getCurrentUser() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        UserInfo userInfo;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(118, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          userInfo = IActivityManager.Stub.getDefaultImpl().getCurrentUser();
          return userInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          userInfo = (UserInfo)UserInfo.CREATOR.createFromParcel(parcel2);
        } else {
          userInfo = null;
        } 
        return userInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ActivityManager.StackInfo getFocusedStackInfo() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ActivityManager.StackInfo stackInfo;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(150, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          stackInfo = IActivityManager.Stub.getDefaultImpl().getFocusedStackInfo();
          return stackInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          stackInfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel2);
        } else {
          stackInfo = null;
        } 
        return stackInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getForegroundServiceType(ComponentName param1ComponentName, IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getForegroundServiceType(param1ComponentName, param1IBinder); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice<ApplicationExitInfo> getHistoricalProcessExitReasons(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(211, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getHistoricalProcessExitReasons(param1String, param1Int1, param1Int2, param1Int3); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (ParceledListSlice<ApplicationExitInfo>)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Intent getIntentForIntentSender(IIntentSender param1IIntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(142, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getIntentForIntentSender(param1IIntentSender); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
        } else {
          param1IIntentSender = null;
        } 
        return (Intent)param1IIntentSender;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IIntentSender getIntentSender(int param1Int1, String param1String1, IBinder param1IBinder, String param1String2, int param1Int2, Intent[] param1ArrayOfIntent, String[] param1ArrayOfString, int param1Int3, Bundle param1Bundle, int param1Int4) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        try {
          parcel1.writeInt(param1Int1);
          parcel1.writeString(param1String1);
          parcel1.writeStrongBinder(param1IBinder);
          parcel1.writeString(param1String2);
          parcel1.writeInt(param1Int2);
          parcel1.writeTypedArray((Parcelable[])param1ArrayOfIntent, 0);
          parcel1.writeStringArray(param1ArrayOfString);
          parcel1.writeInt(param1Int3);
          if (param1Bundle != null) {
            parcel1.writeInt(1);
            param1Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param1Int4);
          if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            IIntentSender iIntentSender1 = IActivityManager.Stub.getDefaultImpl().getIntentSender(param1Int1, param1String1, param1IBinder, param1String2, param1Int2, param1ArrayOfIntent, param1ArrayOfString, param1Int3, param1Bundle, param1Int4);
            parcel2.recycle();
            parcel1.recycle();
            return iIntentSender1;
          } 
          parcel2.readException();
          IIntentSender iIntentSender = IIntentSender.Stub.asInterface(parcel2.readStrongBinder());
          parcel2.recycle();
          parcel1.recycle();
          return iIntentSender;
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1String1;
    }
    
    public IIntentSender getIntentSenderWithFeature(int param1Int1, String param1String1, String param1String2, IBinder param1IBinder, String param1String3, int param1Int2, Intent[] param1ArrayOfIntent, String[] param1ArrayOfString, int param1Int3, Bundle param1Bundle, int param1Int4) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String3);
        parcel1.writeInt(param1Int2);
        parcel1.writeTypedArray((Parcelable[])param1ArrayOfIntent, 0);
        parcel1.writeStringArray(param1ArrayOfString);
        parcel1.writeInt(param1Int3);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int4);
        if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getIntentSenderWithFeature(param1Int1, param1String1, param1String2, param1IBinder, param1String3, param1Int2, param1ArrayOfIntent, param1ArrayOfString, param1Int3, param1Bundle, param1Int4); 
        parcel2.readException();
        return IIntentSender.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IActivityManager";
    }
    
    public String getLaunchedFromPackage(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(143, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getLaunchedFromPackage(param1IBinder); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getLaunchedFromUid(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(119, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getLaunchedFromUid(param1IBinder); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor getLifeMonitor() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParcelFileDescriptor parcelFileDescriptor;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(208, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          parcelFileDescriptor = IActivityManager.Stub.getDefaultImpl().getLifeMonitor();
          return parcelFileDescriptor;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          parcelFileDescriptor = null;
        } 
        return parcelFileDescriptor;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getLockTaskModeState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(171, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getLockTaskModeState(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void getMemoryInfo(ActivityManager.MemoryInfo param1MemoryInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().getMemoryInfo(param1MemoryInfo);
          return;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0)
          param1MemoryInfo.readFromParcel(parcel2); 
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getMemoryTrimLevel() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(189, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getMemoryTrimLevel(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void getMyMemoryState(ActivityManager.RunningAppProcessInfo param1RunningAppProcessInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(116, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().getMyMemoryState(param1RunningAppProcessInfo);
          return;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0)
          param1RunningAppProcessInfo.readFromParcel(parcel2); 
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getPackageForIntentSender(IIntentSender param1IIntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getPackageForIntentSender(param1IIntentSender); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPackageProcessState(String param1String1, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(177, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getPackageProcessState(param1String1, param1String2); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getProcessLimit() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getProcessLimit(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Debug.MemoryInfo[] getProcessMemoryInfo(int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(89, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getProcessMemoryInfo(param1ArrayOfint); 
        parcel2.readException();
        return (Debug.MemoryInfo[])parcel2.createTypedArray(Debug.MemoryInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long[] getProcessPss(int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(110, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getProcessPss(param1ArrayOfint); 
        parcel2.readException();
        return parcel2.createLongArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ActivityManager.ProcessErrorStateInfo> getProcessesInErrorState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getProcessesInErrorState(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ActivityManager.ProcessErrorStateInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getProviderMimeType(Uri param1Uri, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1Uri != null) {
          parcel1.writeInt(1);
          param1Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(99, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getProviderMimeType(param1Uri, param1Int); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void getProviderMimeTypeAsync(Uri param1Uri, int param1Int, RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IActivityManager");
        if (param1Uri != null) {
          parcel.writeInt(1);
          param1Uri.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (param1RemoteCallback != null) {
          parcel.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(100, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().getProviderMimeTypeAsync(param1Uri, param1Int, param1RemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public ParceledListSlice getRecentTasks(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          parceledListSlice = IActivityManager.Stub.getDefaultImpl().getRecentTasks(param1Int1, param1Int2, param1Int3);
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getRunningAppProcesses(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ActivityManager.RunningAppProcessInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ApplicationInfo> getRunningExternalApplications() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(94, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getRunningExternalApplications(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ApplicationInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public PendingIntent getRunningServiceControlPanel(ComponentName param1ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getRunningServiceControlPanel(param1ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel2);
        } else {
          param1ComponentName = null;
        } 
        return (PendingIntent)param1ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int[] getRunningUserIds() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(130, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getRunningUserIds(); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ActivityManager.RunningServiceInfo> getServices(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getServices(param1Int1, param1Int2); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ActivityManager.RunningServiceInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getTagForIntentSender(IIntentSender param1IIntentSender, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(156, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getTagForIntentSender(param1IIntentSender, param1String); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Rect getTaskBounds(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Rect rect;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(154, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          rect = IActivityManager.Stub.getDefaultImpl().getTaskBounds(param1Int);
          return rect;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          rect = (Rect)Rect.CREATOR.createFromParcel(parcel2);
        } else {
          rect = null;
        } 
        return rect;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getTaskForActivity(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          i = IActivityManager.Stub.getDefaultImpl().getTaskForActivity(param1IBinder, param1Boolean);
          return i;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        return i;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ActivityManager.TaskSnapshot getTaskSnapshot(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        ActivityManager.TaskSnapshot taskSnapshot;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(200, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          taskSnapshot = IActivityManager.Stub.getDefaultImpl().getTaskSnapshot(param1Int, param1Boolean);
          return taskSnapshot;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          taskSnapshot = (ActivityManager.TaskSnapshot)ActivityManager.TaskSnapshot.CREATOR.createFromParcel(parcel2);
        } else {
          taskSnapshot = null;
        } 
        return taskSnapshot;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ActivityManager.RunningTaskInfo> getTasks(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getTasks(param1Int); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getUidForIntentSender(IIntentSender param1IIntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(84, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().getUidForIntentSender(param1IIntentSender); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getUidProcessState(int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Int = IActivityManager.Stub.getDefaultImpl().getUidProcessState(param1Int, param1String);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void grantUriPermission(IApplicationThread param1IApplicationThread, String param1String, Uri param1Uri, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        if (param1Uri != null) {
          parcel1.writeInt(1);
          param1Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().grantUriPermission(param1IApplicationThread, param1String, param1Uri, param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void handleApplicationCrash(IBinder param1IBinder, ApplicationErrorReport.ParcelableCrashInfo param1ParcelableCrashInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1ParcelableCrashInfo != null) {
          parcel1.writeInt(1);
          param1ParcelableCrashInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().handleApplicationCrash(param1IBinder, param1ParcelableCrashInfo);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void handleApplicationStrictModeViolation(IBinder param1IBinder, int param1Int, StrictMode.ViolationInfo param1ViolationInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int);
        if (param1ViolationInfo != null) {
          parcel1.writeInt(1);
          param1ViolationInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(96, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().handleApplicationStrictModeViolation(param1IBinder, param1Int, param1ViolationInfo);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean handleApplicationWtf(IBinder param1IBinder, String param1String, boolean param1Boolean, ApplicationErrorReport.ParcelableCrashInfo param1ParcelableCrashInfo, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        try {
          parcel1.writeStrongBinder(param1IBinder);
          try {
            boolean bool2;
            parcel1.writeString(param1String);
            boolean bool1 = true;
            if (param1Boolean) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            parcel1.writeInt(bool2);
            if (param1ParcelableCrashInfo != null) {
              parcel1.writeInt(1);
              param1ParcelableCrashInfo.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeInt(param1Int);
              try {
                if (!this.mRemote.transact(91, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                  param1Boolean = IActivityManager.Stub.getDefaultImpl().handleApplicationWtf(param1IBinder, param1String, param1Boolean, param1ParcelableCrashInfo, param1Int);
                  parcel2.recycle();
                  parcel1.recycle();
                  return param1Boolean;
                } 
                parcel2.readException();
                param1Int = parcel2.readInt();
                if (param1Int != 0) {
                  param1Boolean = bool1;
                } else {
                  param1Boolean = false;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return param1Boolean;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IBinder;
    }
    
    public int handleIncomingUser(int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, String param1String1, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        try {
          parcel1.writeInt(param1Int1);
          try {
            parcel1.writeInt(param1Int2);
            try {
              boolean bool2;
              parcel1.writeInt(param1Int3);
              boolean bool1 = true;
              if (param1Boolean1) {
                bool2 = true;
              } else {
                bool2 = false;
              } 
              parcel1.writeInt(bool2);
              if (param1Boolean2) {
                bool2 = bool1;
              } else {
                bool2 = false;
              } 
              parcel1.writeInt(bool2);
              try {
                parcel1.writeString(param1String1);
                try {
                  parcel1.writeString(param1String2);
                  if (!this.mRemote.transact(85, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                    param1Int1 = IActivityManager.Stub.getDefaultImpl().handleIncomingUser(param1Int1, param1Int2, param1Int3, param1Boolean1, param1Boolean2, param1String1, param1String2);
                    parcel2.recycle();
                    parcel1.recycle();
                    return param1Int1;
                  } 
                  parcel2.readException();
                  param1Int1 = parcel2.readInt();
                  parcel2.recycle();
                  parcel1.recycle();
                  return param1Int1;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1String1;
    }
    
    public void hang(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(146, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().hang(param1IBinder, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAppFreezerSupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(215, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isAppFreezerSupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAppStartModeDisabled(int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(184, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isAppStartModeDisabled(param1Int, param1String);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isBackgroundRestricted(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(195, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isBackgroundRestricted(param1String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isInLockTaskMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(158, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isInLockTaskMode();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isIntentSenderABroadcast(IIntentSender param1IIntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(123, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderABroadcast(param1IIntentSender);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isIntentSenderAForegroundService(IIntentSender param1IIntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(122, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderAForegroundService(param1IIntentSender);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isIntentSenderAnActivity(IIntentSender param1IIntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(121, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderAnActivity(param1IIntentSender);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isIntentSenderTargetedToPackage(IIntentSender param1IIntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(108, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderTargetedToPackage(param1IIntentSender);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isTopActivityImmersive() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(97, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isTopActivityImmersive();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isTopOfTask(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(163, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isTopOfTask(param1IBinder);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isUidActive(int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(4, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isUidActive(param1Int, param1String);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isUserAMonkey() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(93, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isUserAMonkey();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isUserRunning(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(102, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isUserRunning(param1Int1, param1Int2);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isVrModePackageEnabled(ComponentName param1ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        boolean bool = true;
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(190, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().isVrModePackageEnabled(param1ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void killAllBackgroundProcesses() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(112, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().killAllBackgroundProcesses();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void killApplication(String param1String1, int param1Int1, int param1Int2, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String1);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(87, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().killApplication(param1String1, param1Int1, param1Int2, param1String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void killApplicationProcess(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(90, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().killApplicationProcess(param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void killBackgroundProcesses(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(92, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().killBackgroundProcesses(param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void killPackageDependents(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(186, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().killPackageDependents(param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean killPids(int[] param1ArrayOfint, String param1String, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeIntArray(param1ArrayOfint);
        parcel1.writeString(param1String);
        boolean bool = true;
        if (param1Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Boolean = IActivityManager.Stub.getDefaultImpl().killPids(param1ArrayOfint, param1String, param1Boolean);
          return param1Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param1Boolean = bool;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean killProcessesBelowForeground(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(117, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().killProcessesBelowForeground(param1String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void killProcessesWhenImperceptible(int[] param1ArrayOfint, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeIntArray(param1ArrayOfint);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(212, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().killProcessesWhenImperceptible(param1ArrayOfint, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void killUid(int param1Int1, int param1Int2, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(144, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().killUid(param1Int1, param1Int2, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void killUidForPermissionChange(int param1Int1, int param1Int2, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(216, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().killUidForPermissionChange(param1Int1, param1Int2, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean launchBugReportHandlerApp() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(140, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().launchBugReportHandlerApp();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void makePackageIdle(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(188, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().makePackageIdle(param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean moveActivityTaskToBack(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        boolean bool = true;
        if (param1Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Boolean = IActivityManager.Stub.getDefaultImpl().moveActivityTaskToBack(param1IBinder, param1Boolean);
          return param1Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param1Boolean = bool;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void moveTaskToFront(IApplicationThread param1IApplicationThread, String param1String, int param1Int1, int param1Int2, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().moveTaskToFront(param1IApplicationThread, param1String, param1Int1, param1Int2, param1Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void moveTaskToStack(int param1Int1, int param1Int2, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(148, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().moveTaskToStack(param1Int1, param1Int2, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean moveTopActivityToPinnedStack(int param1Int, Rect param1Rect) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        boolean bool = true;
        if (param1Rect != null) {
          parcel1.writeInt(1);
          param1Rect.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(183, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().moveTopActivityToPinnedStack(param1Int, param1Rect);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void noteAlarmFinish(IIntentSender param1IIntentSender, WorkSource param1WorkSource, int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1WorkSource != null) {
          parcel1.writeInt(1);
          param1WorkSource.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(176, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().noteAlarmFinish(param1IIntentSender, param1WorkSource, param1Int, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void noteAlarmStart(IIntentSender param1IIntentSender, WorkSource param1WorkSource, int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1WorkSource != null) {
          parcel1.writeInt(1);
          param1WorkSource.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(175, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().noteAlarmStart(param1IIntentSender, param1WorkSource, param1Int, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void noteWakeupAlarm(IIntentSender param1IIntentSender, WorkSource param1WorkSource, int param1Int, String param1String1, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentSender != null) {
          iBinder = param1IIntentSender.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1WorkSource != null) {
          parcel1.writeInt(1);
          param1WorkSource.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(61, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().noteWakeupAlarm(param1IIntentSender, param1WorkSource, param1Int, param1String1, param1String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyCleartextNetwork(int param1Int, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(168, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().notifyCleartextNetwork(param1Int, param1ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyLockedProfile(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(191, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().notifyLockedProfile(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor openContentUri(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().openContentUri(param1String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (ParcelFileDescriptor)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder peekService(Intent param1Intent, String param1String1, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
          return IActivityManager.Stub.getDefaultImpl().peekService(param1Intent, param1String1, param1String2); 
        parcel2.readException();
        return parcel2.readStrongBinder();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void performIdleMaintenance() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(152, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().performIdleMaintenance();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void positionTaskInStack(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(181, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().positionTaskInStack(param1Int1, param1Int2, param1Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean profileControl(String param1String, int param1Int1, boolean param1Boolean, ProfilerInfo param1ProfilerInfo, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        try {
          parcel1.writeString(param1String);
          try {
            boolean bool2;
            parcel1.writeInt(param1Int1);
            boolean bool1 = true;
            if (param1Boolean) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            parcel1.writeInt(bool2);
            if (param1ProfilerInfo != null) {
              parcel1.writeInt(1);
              param1ProfilerInfo.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeInt(param1Int2);
              try {
                if (!this.mRemote.transact(77, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                  param1Boolean = IActivityManager.Stub.getDefaultImpl().profileControl(param1String, param1Int1, param1Boolean, param1ProfilerInfo, param1Int2);
                  parcel2.recycle();
                  parcel1.recycle();
                  return param1Boolean;
                } 
                parcel2.readException();
                param1Int1 = parcel2.readInt();
                if (param1Int1 != 0) {
                  param1Boolean = bool1;
                } else {
                  param1Boolean = false;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return param1Boolean;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1String;
    }
    
    public void publishContentProviders(IApplicationThread param1IApplicationThread, List<ContentProviderHolder> param1List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeTypedList(param1List);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().publishContentProviders(param1IApplicationThread, param1List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void publishService(IBinder param1IBinder1, Intent param1Intent, IBinder param1IBinder2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder1);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStrongBinder(param1IBinder2);
        if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().publishService(param1IBinder1, param1Intent, param1IBinder2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean refContentProvider(IBinder param1IBinder, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(24, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().refContentProvider(param1IBinder, param1Int1, param1Int2);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerIntentSenderCancelListener(IIntentSender param1IIntentSender, IResultReceiver param1IResultReceiver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder1 = null;
        if (param1IIntentSender != null) {
          iBinder2 = param1IIntentSender.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param1IResultReceiver != null)
          iBinder2 = param1IResultReceiver.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(58, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().registerIntentSenderCancelListener(param1IIntentSender, param1IResultReceiver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerProcessObserver(IProcessObserver param1IProcessObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IProcessObserver != null) {
          iBinder = param1IProcessObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(106, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().registerProcessObserver(param1IProcessObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Intent registerReceiver(IApplicationThread param1IApplicationThread, String param1String1, IIntentReceiver param1IIntentReceiver, IntentFilter param1IntentFilter, String param1String2, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder2;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder1 = null;
        if (param1IApplicationThread != null) {
          iBinder2 = param1IApplicationThread.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        try {
          parcel1.writeString(param1String1);
          iBinder2 = iBinder1;
          if (param1IIntentReceiver != null)
            iBinder2 = param1IIntentReceiver.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          if (param1IntentFilter != null) {
            parcel1.writeInt(1);
            param1IntentFilter.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeString(param1String2);
            try {
              parcel1.writeInt(param1Int1);
              try {
                parcel1.writeInt(param1Int2);
                if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                  Intent intent = IActivityManager.Stub.getDefaultImpl().registerReceiver(param1IApplicationThread, param1String1, param1IIntentReceiver, param1IntentFilter, param1String2, param1Int1, param1Int2);
                  parcel2.recycle();
                  parcel1.recycle();
                  return intent;
                } 
                parcel2.readException();
                if (parcel2.readInt() != 0) {
                  Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
                } else {
                  param1IApplicationThread = null;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return (Intent)param1IApplicationThread;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public Intent registerReceiverWithFeature(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, IIntentReceiver param1IIntentReceiver, IntentFilter param1IntentFilter, String param1String3, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder2;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder1 = null;
        if (param1IApplicationThread != null) {
          iBinder2 = param1IApplicationThread.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        try {
          parcel1.writeString(param1String1);
          try {
            parcel1.writeString(param1String2);
            iBinder2 = iBinder1;
            if (param1IIntentReceiver != null)
              iBinder2 = param1IIntentReceiver.asBinder(); 
            parcel1.writeStrongBinder(iBinder2);
            if (param1IntentFilter != null) {
              parcel1.writeInt(1);
              param1IntentFilter.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeString(param1String3);
              parcel1.writeInt(param1Int1);
              parcel1.writeInt(param1Int2);
              if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                Intent intent = IActivityManager.Stub.getDefaultImpl().registerReceiverWithFeature(param1IApplicationThread, param1String1, param1String2, param1IIntentReceiver, param1IntentFilter, param1String3, param1Int1, param1Int2);
                parcel2.recycle();
                parcel1.recycle();
                return intent;
              } 
              parcel2.readException();
              if (parcel2.readInt() != 0) {
                Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
              } else {
                param1IApplicationThread = null;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return (Intent)param1IApplicationThread;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public void registerTaskStackListener(ITaskStackListener param1ITaskStackListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1ITaskStackListener != null) {
          iBinder = param1ITaskStackListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(166, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().registerTaskStackListener(param1ITaskStackListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerUidObserver(IUidObserver param1IUidObserver, int param1Int1, int param1Int2, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IUidObserver != null) {
          iBinder = param1IUidObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().registerUidObserver(param1IUidObserver, param1Int1, param1Int2, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerUserSwitchObserver(IUserSwitchObserver param1IUserSwitchObserver, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IUserSwitchObserver != null) {
          iBinder = param1IUserSwitchObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(128, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().registerUserSwitchObserver(param1IUserSwitchObserver, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeContentProvider(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IActivityManager");
        parcel.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(62, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().removeContentProvider(param1IBinder, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void removeContentProviderExternal(String param1String, IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(114, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().removeContentProviderExternal(param1String, param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeContentProviderExternalAsUser(String param1String, IBinder param1IBinder, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(115, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().removeContentProviderExternalAsUser(param1String, param1IBinder, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeStack(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(187, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().removeStack(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeTask(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(105, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().removeTask(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestBugReport(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(132, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().requestBugReport(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestBugReportWithDescription(String param1String1, String param1String2, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(133, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().requestBugReportWithDescription(param1String1, param1String2, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestFullBugReport() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(138, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().requestFullBugReport();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestInteractiveBugReport() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(137, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().requestInteractiveBugReport();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestInteractiveBugReportWithDescription(String param1String1, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(136, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().requestInteractiveBugReportWithDescription(param1String1, param1String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestRemoteBugReport() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(139, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().requestRemoteBugReport();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestSystemServerHeapDump() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(131, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().requestSystemServerHeapDump();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestTelephonyBugReport(String param1String1, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(134, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().requestTelephonyBugReport(param1String1, param1String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestWifiBugReport(String param1String1, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(135, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().requestWifiBugReport(param1String1, param1String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resizeTask(int param1Int1, Rect param1Rect, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int1);
        if (param1Rect != null) {
          parcel1.writeInt(1);
          param1Rect.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(170, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().resizeTask(param1Int1, param1Rect, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void restart() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(151, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().restart();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int restartUserInBackground(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(198, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Int = IActivityManager.Stub.getDefaultImpl().restartUserInBackground(param1Int);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resumeAppSwitches() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(80, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().resumeAppSwitches();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void revokeUriPermission(IApplicationThread param1IApplicationThread, String param1String, Uri param1Uri, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        if (param1Uri != null) {
          parcel1.writeInt(1);
          param1Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().revokeUriPermission(param1IApplicationThread, param1String, param1Uri, param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void scheduleApplicationInfoChanged(List<String> param1List, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStringList(param1List);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(201, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().scheduleApplicationInfoChanged(param1List, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendIdleJobTrigger() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(193, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().sendIdleJobTrigger();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int sendIntentSender(IIntentSender param1IIntentSender, IBinder param1IBinder, int param1Int, Intent param1Intent, String param1String1, IIntentReceiver param1IIntentReceiver, String param1String2, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder2;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder1 = null;
        if (param1IIntentSender != null) {
          iBinder2 = param1IIntentSender.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        try {
          parcel1.writeStrongBinder(param1IBinder);
          try {
            parcel1.writeInt(param1Int);
            if (param1Intent != null) {
              parcel1.writeInt(1);
              param1Intent.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            parcel1.writeString(param1String1);
            iBinder2 = iBinder1;
            if (param1IIntentReceiver != null)
              iBinder2 = param1IIntentReceiver.asBinder(); 
            parcel1.writeStrongBinder(iBinder2);
            parcel1.writeString(param1String2);
            if (param1Bundle != null) {
              parcel1.writeInt(1);
              param1Bundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            if (!this.mRemote.transact(194, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
              param1Int = IActivityManager.Stub.getDefaultImpl().sendIntentSender(param1IIntentSender, param1IBinder, param1Int, param1Intent, param1String1, param1IIntentReceiver, param1String2, param1Bundle);
              parcel2.recycle();
              parcel1.recycle();
              return param1Int;
            } 
            parcel2.readException();
            param1Int = parcel2.readInt();
            parcel2.recycle();
            parcel1.recycle();
            return param1Int;
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IIntentSender;
    }
    
    public void serviceDoneExecuting(IBinder param1IBinder, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IActivityManager");
        parcel.writeStrongBinder(param1IBinder);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(53, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().serviceDoneExecuting(param1IBinder, param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setActivityController(IActivityController param1IActivityController, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IActivityController != null) {
          iBinder = param1IActivityController.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setActivityController(param1IActivityController, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setActivityLocusContext(ComponentName param1ComponentName, LocusId param1LocusId, IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1LocusId != null) {
          parcel1.writeInt(1);
          param1LocusId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(213, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setActivityLocusContext(param1ComponentName, param1LocusId, param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAgentApp(String param1String1, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setAgentApp(param1String1, param1String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAlwaysFinish(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setAlwaysFinish(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDebugApp(String param1String, boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        boolean bool1 = true;
        if (param1Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param1Boolean2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setDebugApp(param1String, param1Boolean1, param1Boolean2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDumpHeapDebugLimit(String param1String1, int param1Int, long param1Long, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String1);
        parcel1.writeInt(param1Int);
        parcel1.writeLong(param1Long);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(172, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setDumpHeapDebugLimit(param1String1, param1Int, param1Long, param1String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setFocusedStack(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(149, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setFocusedStack(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setHasTopUi(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(197, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setHasTopUi(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPackageScreenCompatMode(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(103, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setPackageScreenCompatMode(param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPersistentVrThread(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(202, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setPersistentVrThread(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setProcessImportant(IBinder param1IBinder, int param1Int, boolean param1Boolean, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setProcessImportant(param1IBinder, param1Int, param1Boolean, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setProcessLimit(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setProcessLimit(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setProcessMemoryTrimLevel(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(155, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().setProcessMemoryTrimLevel(param1String, param1Int1, param1Int2);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setProcessStateSummary(byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(214, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setProcessStateSummary(param1ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setRenderThread(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(196, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setRenderThread(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setRequestedOrientation(IBinder param1IBinder, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(63, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setRequestedOrientation(param1IBinder, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setServiceForeground(ComponentName param1ComponentName, IBinder param1IBinder, int param1Int1, Notification param1Notification, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeStrongBinder(param1IBinder);
          try {
            parcel1.writeInt(param1Int1);
            if (param1Notification != null) {
              parcel1.writeInt(1);
              param1Notification.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeInt(param1Int2);
              try {
                parcel1.writeInt(param1Int3);
                if (!this.mRemote.transact(66, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                  IActivityManager.Stub.getDefaultImpl().setServiceForeground(param1ComponentName, param1IBinder, param1Int1, param1Notification, param1Int2, param1Int3);
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } 
                parcel2.readException();
                parcel2.recycle();
                parcel1.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1ComponentName;
    }
    
    public void setTaskResizeable(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(169, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setTaskResizeable(param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setUserIsMonkey(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(145, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().setUserIsMonkey(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void showBootMessage(CharSequence param1CharSequence, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        boolean bool = true;
        if (param1CharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(param1CharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param1Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(111, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().showBootMessage(param1CharSequence, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void showWaitingForDebugger(IApplicationThread param1IApplicationThread, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(50, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().showWaitingForDebugger(param1IApplicationThread, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean shutdown(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(78, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().shutdown(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void signalPersistentProcesses(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().signalPersistentProcesses(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int startActivity(IApplicationThread param1IApplicationThread, String param1String1, Intent param1Intent, String param1String2, IBinder param1IBinder, String param1String3, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          try {
            iBinder = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String1);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String2);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String3);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1ProfilerInfo != null) {
          parcel1.writeInt(1);
          param1ProfilerInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityManager.startActivity(param1IApplicationThread, param1String1, param1Intent, param1String2, param1IBinder, param1String3, param1Int1, param1Int2, param1ProfilerInfo, param1Bundle);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public int startActivityAsUser(IApplicationThread param1IApplicationThread, String param1String1, Intent param1Intent, String param1String2, IBinder param1IBinder, String param1String3, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          try {
            iBinder = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String1);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String2);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String3);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1ProfilerInfo != null) {
          parcel1.writeInt(1);
          param1ProfilerInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(124, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityManager.startActivityAsUser(param1IApplicationThread, param1String1, param1Intent, param1String2, param1IBinder, param1String3, param1Int1, param1Int2, param1ProfilerInfo, param1Bundle, param1Int3);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public int startActivityAsUserWithFeature(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          try {
            iBinder = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String3);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String4);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1ProfilerInfo != null) {
          parcel1.writeInt(1);
          param1ProfilerInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(125, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityManager.startActivityAsUserWithFeature(param1IApplicationThread, param1String1, param1String2, param1Intent, param1String3, param1IBinder, param1String4, param1Int1, param1Int2, param1ProfilerInfo, param1Bundle, param1Int3);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public int startActivityFromRecents(int param1Int, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(161, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Int = IActivityManager.Stub.getDefaultImpl().startActivityFromRecents(param1Int, param1Bundle);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int startActivityWithFeature(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          try {
            iBinder = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String3);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String4);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1ProfilerInfo != null) {
          parcel1.writeInt(1);
          param1ProfilerInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityManager.startActivityWithFeature(param1IApplicationThread, param1String1, param1String2, param1Intent, param1String3, param1IBinder, param1String4, param1Int1, param1Int2, param1ProfilerInfo, param1Bundle);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public boolean startBinderTracking() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(179, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().startBinderTracking();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startConfirmDeviceCredentialIntent(Intent param1Intent, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(192, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().startConfirmDeviceCredentialIntent(param1Intent, param1Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startDelegateShellPermissionIdentity(int param1Int, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        parcel1.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(206, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().startDelegateShellPermissionIdentity(param1Int, param1ArrayOfString);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean startInstrumentation(ComponentName param1ComponentName, String param1String1, int param1Int1, Bundle param1Bundle, IInstrumentationWatcher param1IInstrumentationWatcher, IUiAutomationConnection param1IUiAutomationConnection, int param1Int2, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        boolean bool = true;
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(param1String1);
          try {
            parcel1.writeInt(param1Int1);
            if (param1Bundle != null) {
              parcel1.writeInt(1);
              param1Bundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            IBinder iBinder1 = null;
            if (param1IInstrumentationWatcher != null) {
              iBinder2 = param1IInstrumentationWatcher.asBinder();
            } else {
              iBinder2 = null;
            } 
            parcel1.writeStrongBinder(iBinder2);
            IBinder iBinder2 = iBinder1;
            if (param1IUiAutomationConnection != null)
              iBinder2 = param1IUiAutomationConnection.asBinder(); 
            parcel1.writeStrongBinder(iBinder2);
            parcel1.writeInt(param1Int2);
            parcel1.writeString(param1String2);
            if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
              bool = IActivityManager.Stub.getDefaultImpl().startInstrumentation(param1ComponentName, param1String1, param1Int1, param1Bundle, param1IInstrumentationWatcher, param1IUiAutomationConnection, param1Int2, param1String2);
              parcel2.recycle();
              parcel1.recycle();
              return bool;
            } 
            parcel2.readException();
            param1Int1 = parcel2.readInt();
            if (param1Int1 == 0)
              bool = false; 
            parcel2.recycle();
            parcel1.recycle();
            return bool;
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1ComponentName;
    }
    
    public void startRecentsActivity(Intent param1Intent, IAssistDataReceiver param1IAssistDataReceiver, IRecentsAnimationRunner param1IRecentsAnimationRunner) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        IBinder iBinder1 = null;
        if (param1IAssistDataReceiver != null) {
          iBinder2 = param1IAssistDataReceiver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param1IRecentsAnimationRunner != null)
          iBinder2 = param1IRecentsAnimationRunner.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(159, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().startRecentsActivity(param1Intent, param1IAssistDataReceiver, param1IRecentsAnimationRunner);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName startService(IApplicationThread param1IApplicationThread, Intent param1Intent, String param1String1, boolean param1Boolean, String param1String2, String param1String3, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        boolean bool = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(param1String1);
          if (!param1Boolean)
            bool = false; 
          parcel1.writeInt(bool);
          try {
            parcel1.writeString(param1String2);
            try {
              parcel1.writeString(param1String3);
              try {
                parcel1.writeInt(param1Int);
                if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                  ComponentName componentName = IActivityManager.Stub.getDefaultImpl().startService(param1IApplicationThread, param1Intent, param1String1, param1Boolean, param1String2, param1String3, param1Int);
                  parcel2.recycle();
                  parcel1.recycle();
                  return componentName;
                } 
                parcel2.readException();
                if (parcel2.readInt() != 0) {
                  ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
                } else {
                  param1IApplicationThread = null;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return (ComponentName)param1IApplicationThread;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public void startSystemLockTaskMode(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(162, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().startSystemLockTaskMode(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean startUserInBackground(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(157, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().startUserInBackground(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean startUserInBackgroundWithListener(int param1Int, IProgressListener param1IProgressListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (param1IProgressListener != null) {
          iBinder = param1IProgressListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(205, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().startUserInBackgroundWithListener(param1Int, param1IProgressListener);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean startUserInForegroundWithListener(int param1Int, IProgressListener param1IProgressListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (param1IProgressListener != null) {
          iBinder = param1IProgressListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(209, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().startUserInForegroundWithListener(param1Int, param1IProgressListener);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopAppSwitches() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(79, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().stopAppSwitches();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean stopBinderTrackingAndDump(ParcelFileDescriptor param1ParcelFileDescriptor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        boolean bool = true;
        if (param1ParcelFileDescriptor != null) {
          parcel1.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(180, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().stopBinderTrackingAndDump(param1ParcelFileDescriptor);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopDelegateShellPermissionIdentity() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(207, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().stopDelegateShellPermissionIdentity();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int stopService(IApplicationThread param1IApplicationThread, Intent param1Intent, String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Int = IActivityManager.Stub.getDefaultImpl().stopService(param1IApplicationThread, param1Intent, param1String, param1Int);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean stopServiceToken(ComponentName param1ComponentName, IBinder param1IBinder, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        boolean bool = true;
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().stopServiceToken(param1ComponentName, param1IBinder, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int stopUser(int param1Int, boolean param1Boolean, IStopUserCallback param1IStopUserCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (param1IStopUserCallback != null) {
          iBinder = param1IStopUserCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(126, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Int = IActivityManager.Stub.getDefaultImpl().stopUser(param1Int, param1Boolean, param1IStopUserCallback);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int stopUserWithDelayedLocking(int param1Int, boolean param1Boolean, IStopUserCallback param1IStopUserCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (param1IStopUserCallback != null) {
          iBinder = param1IStopUserCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(127, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          param1Int = IActivityManager.Stub.getDefaultImpl().stopUserWithDelayedLocking(param1Int, param1Boolean, param1IStopUserCallback);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void suppressResizeConfigChanges(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(182, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().suppressResizeConfigChanges(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean switchUser(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(104, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().switchUser(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unbindBackupAgent(ApplicationInfo param1ApplicationInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1ApplicationInfo != null) {
          parcel1.writeInt(1);
          param1ApplicationInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(83, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().unbindBackupAgent(param1ApplicationInfo);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unbindFinished(IBinder param1IBinder, Intent param1Intent, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        boolean bool = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param1Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(64, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().unbindFinished(param1IBinder, param1Intent, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean unbindService(IServiceConnection param1IServiceConnection) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IServiceConnection != null) {
          iBinder = param1IServiceConnection.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(31, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().unbindService(param1IServiceConnection);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unbroadcastIntent(IApplicationThread param1IApplicationThread, Intent param1Intent, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().unbroadcastIntent(param1IApplicationThread, param1Intent, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unhandledBack() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().unhandledBack();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean unlockUser(int param1Int, byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2, IProgressListener param1IProgressListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        parcel1.writeByteArray(param1ArrayOfbyte1);
        parcel1.writeByteArray(param1ArrayOfbyte2);
        if (param1IProgressListener != null) {
          iBinder = param1IProgressListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(185, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().unlockUser(param1Int, param1ArrayOfbyte1, param1ArrayOfbyte2, param1IProgressListener);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterIntentSenderCancelListener(IIntentSender param1IIntentSender, IResultReceiver param1IResultReceiver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        IBinder iBinder1 = null;
        if (param1IIntentSender != null) {
          iBinder2 = param1IIntentSender.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param1IResultReceiver != null)
          iBinder2 = param1IResultReceiver.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().unregisterIntentSenderCancelListener(param1IIntentSender, param1IResultReceiver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterProcessObserver(IProcessObserver param1IProcessObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IProcessObserver != null) {
          iBinder = param1IProcessObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(107, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().unregisterProcessObserver(param1IProcessObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterReceiver(IIntentReceiver param1IIntentReceiver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IIntentReceiver != null) {
          iBinder = param1IIntentReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().unregisterReceiver(param1IIntentReceiver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterTaskStackListener(ITaskStackListener param1ITaskStackListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1ITaskStackListener != null) {
          iBinder = param1ITaskStackListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(167, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().unregisterTaskStackListener(param1ITaskStackListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterUidObserver(IUidObserver param1IUidObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IUidObserver != null) {
          iBinder = param1IUidObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().unregisterUidObserver(param1IUidObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterUserSwitchObserver(IUserSwitchObserver param1IUserSwitchObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IUserSwitchObserver != null) {
          iBinder = param1IUserSwitchObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(129, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().unregisterUserSwitchObserver(param1IUserSwitchObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unstableProviderDied(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(120, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().unstableProviderDied(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean updateConfiguration(Configuration param1Configuration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        boolean bool = true;
        if (param1Configuration != null) {
          parcel1.writeInt(1);
          param1Configuration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().updateConfiguration(param1Configuration);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateDeviceOwner(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(178, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().updateDeviceOwner(param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateLockTaskPackages(int param1Int, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeInt(param1Int);
        parcel1.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(174, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().updateLockTaskPackages(param1Int, param1ArrayOfString);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean updateMccMncConfiguration(String param1String1, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(41, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          bool = IActivityManager.Stub.getDefaultImpl().updateMccMncConfiguration(param1String1, param1String2);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updatePersistentConfiguration(Configuration param1Configuration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1Configuration != null) {
          parcel1.writeInt(1);
          param1Configuration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(109, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().updatePersistentConfiguration(param1Configuration);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateServiceGroup(IServiceConnection param1IServiceConnection, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        if (param1IServiceConnection != null) {
          iBinder = param1IServiceConnection.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().updateServiceGroup(param1IServiceConnection, param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void waitForNetworkStateUpdate(long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityManager");
        parcel1.writeLong(param1Long);
        if (!this.mRemote.transact(203, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IActivityManager.Stub.getDefaultImpl().waitForNetworkStateUpdate(param1Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
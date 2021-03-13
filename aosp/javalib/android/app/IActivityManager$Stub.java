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

public abstract class Stub extends Binder implements IActivityManager {
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
  
  public static IActivityManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IActivityManager");
    return (iInterface != null && iInterface instanceof IActivityManager) ? (IActivityManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IActivityManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(IActivityManager paramIActivityManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIActivityManager != null) {
        Proxy.sDefaultImpl = paramIActivityManager;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    IBinder iBinder;
    if (paramInt1 != 1598968902) {
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
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 217:
          paramParcel1.enforceInterface("android.app.IActivityManager");
          if (paramParcel1.readInt() != 0)
            bool30 = true; 
          bool20 = enableAppFreezer(bool30);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool20);
          return true;
        case 216:
          paramParcel1.enforceInterface("android.app.IActivityManager");
          killUidForPermissionChange(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 215:
          paramParcel1.enforceInterface("android.app.IActivityManager");
          bool20 = isAppFreezerSupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool20);
          return true;
        case 214:
          paramParcel1.enforceInterface("android.app.IActivityManager");
          setProcessStateSummary(paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 213:
          paramParcel1.enforceInterface("android.app.IActivityManager");
          if (paramParcel1.readInt() != 0) {
            componentName2 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          } else {
            componentName2 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            locusId = (LocusId)LocusId.CREATOR.createFromParcel(paramParcel1);
          } else {
            locusId = null;
          } 
          setActivityLocusContext(componentName2, locusId, paramParcel1.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        case 212:
          paramParcel1.enforceInterface("android.app.IActivityManager");
          killProcessesWhenImperceptible(paramParcel1.createIntArray(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 211:
          paramParcel1.enforceInterface("android.app.IActivityManager");
          parceledListSlice1 = getHistoricalProcessExitReasons(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice1 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 210:
          parceledListSlice1.enforceInterface("android.app.IActivityManager");
          appNotResponding(parceledListSlice1.readString());
          paramParcel2.writeNoException();
          return true;
        case 209:
          parceledListSlice1.enforceInterface("android.app.IActivityManager");
          bool20 = startUserInForegroundWithListener(parceledListSlice1.readInt(), IProgressListener.Stub.asInterface(parceledListSlice1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool20);
          return true;
        case 208:
          parceledListSlice1.enforceInterface("android.app.IActivityManager");
          parcelFileDescriptor2 = getLifeMonitor();
          paramParcel2.writeNoException();
          if (parcelFileDescriptor2 != null) {
            paramParcel2.writeInt(1);
            parcelFileDescriptor2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 207:
          parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
          stopDelegateShellPermissionIdentity();
          paramParcel2.writeNoException();
          return true;
        case 206:
          parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
          startDelegateShellPermissionIdentity(parcelFileDescriptor2.readInt(), parcelFileDescriptor2.createStringArray());
          paramParcel2.writeNoException();
          return true;
        case 205:
          parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
          bool20 = startUserInBackgroundWithListener(parcelFileDescriptor2.readInt(), IProgressListener.Stub.asInterface(parcelFileDescriptor2.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool20);
          return true;
        case 204:
          parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
          backgroundWhitelistUid(parcelFileDescriptor2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 203:
          parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
          waitForNetworkStateUpdate(parcelFileDescriptor2.readLong());
          paramParcel2.writeNoException();
          return true;
        case 202:
          parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
          setPersistentVrThread(parcelFileDescriptor2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 201:
          parcelFileDescriptor2.enforceInterface("android.app.IActivityManager");
          scheduleApplicationInfoChanged(parcelFileDescriptor2.createStringArrayList(), parcelFileDescriptor2.readInt());
          paramParcel2.writeNoException();
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
          paramParcel2.writeNoException();
          if (taskSnapshot != null) {
            paramParcel2.writeInt(1);
            taskSnapshot.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 199:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          cancelTaskWindowTransition(taskSnapshot.readInt());
          paramParcel2.writeNoException();
          return true;
        case 198:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          i14 = restartUserInBackground(taskSnapshot.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i14);
          return true;
        case 197:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          bool30 = bool21;
          if (taskSnapshot.readInt() != 0)
            bool30 = true; 
          setHasTopUi(bool30);
          paramParcel2.writeNoException();
          return true;
        case 196:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          setRenderThread(taskSnapshot.readInt());
          paramParcel2.writeNoException();
          return true;
        case 195:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          bool19 = isBackgroundRestricted(taskSnapshot.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool19);
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
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i13);
          return true;
        case 193:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          sendIdleJobTrigger();
          paramParcel2.writeNoException();
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
          paramParcel2.writeNoException();
          return true;
        case 191:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          notifyLockedProfile(taskSnapshot.readInt());
          paramParcel2.writeNoException();
          return true;
        case 190:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          if (taskSnapshot.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)taskSnapshot);
          } else {
            taskSnapshot = null;
          } 
          bool18 = isVrModePackageEnabled((ComponentName)taskSnapshot);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool18);
          return true;
        case 189:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          i12 = getMemoryTrimLevel();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i12);
          return true;
        case 188:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          makePackageIdle(taskSnapshot.readString(), taskSnapshot.readInt());
          paramParcel2.writeNoException();
          return true;
        case 187:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          removeStack(taskSnapshot.readInt());
          paramParcel2.writeNoException();
          return true;
        case 186:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          killPackageDependents(taskSnapshot.readString(), taskSnapshot.readInt());
          paramParcel2.writeNoException();
          return true;
        case 185:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          bool17 = unlockUser(taskSnapshot.readInt(), taskSnapshot.createByteArray(), taskSnapshot.createByteArray(), IProgressListener.Stub.asInterface(taskSnapshot.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 184:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          bool17 = isAppStartModeDisabled(taskSnapshot.readInt(), taskSnapshot.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
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
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool16);
          return true;
        case 182:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          bool30 = bool22;
          if (taskSnapshot.readInt() != 0)
            bool30 = true; 
          suppressResizeConfigChanges(bool30);
          paramParcel2.writeNoException();
          return true;
        case 181:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          positionTaskInStack(taskSnapshot.readInt(), taskSnapshot.readInt(), taskSnapshot.readInt());
          paramParcel2.writeNoException();
          return true;
        case 180:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          if (taskSnapshot.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel((Parcel)taskSnapshot);
          } else {
            taskSnapshot = null;
          } 
          bool16 = stopBinderTrackingAndDump((ParcelFileDescriptor)taskSnapshot);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool16);
          return true;
        case 179:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          bool16 = startBinderTracking();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool16);
          return true;
        case 178:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          updateDeviceOwner(taskSnapshot.readString());
          paramParcel2.writeNoException();
          return true;
        case 177:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          i10 = getPackageProcessState(taskSnapshot.readString(), taskSnapshot.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i10);
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
          paramParcel2.writeNoException();
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
          paramParcel2.writeNoException();
          return true;
        case 174:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          updateLockTaskPackages(taskSnapshot.readInt(), taskSnapshot.createStringArray());
          paramParcel2.writeNoException();
          return true;
        case 173:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          dumpHeapFinished(taskSnapshot.readString());
          paramParcel2.writeNoException();
          return true;
        case 172:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          setDumpHeapDebugLimit(taskSnapshot.readString(), taskSnapshot.readInt(), taskSnapshot.readLong(), taskSnapshot.readString());
          paramParcel2.writeNoException();
          return true;
        case 171:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          i10 = getLockTaskModeState();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i10);
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
          paramParcel2.writeNoException();
          return true;
        case 169:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          setTaskResizeable(taskSnapshot.readInt(), taskSnapshot.readInt());
          paramParcel2.writeNoException();
          return true;
        case 168:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          notifyCleartextNetwork(taskSnapshot.readInt(), taskSnapshot.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 167:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          unregisterTaskStackListener(ITaskStackListener.Stub.asInterface(taskSnapshot.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 166:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          registerTaskStackListener(ITaskStackListener.Stub.asInterface(taskSnapshot.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 165:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          i10 = checkPermissionWithToken(taskSnapshot.readString(), taskSnapshot.readInt(), taskSnapshot.readInt(), taskSnapshot.readStrongBinder());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i10);
          return true;
        case 164:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          bootAnimationComplete();
          paramParcel2.writeNoException();
          return true;
        case 163:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          bool15 = isTopOfTask(taskSnapshot.readStrongBinder());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool15);
          return true;
        case 162:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          startSystemLockTaskMode(taskSnapshot.readInt());
          paramParcel2.writeNoException();
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
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i9);
          return true;
        case 160:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          bool30 = bool23;
          if (taskSnapshot.readInt() != 0)
            bool30 = true; 
          cancelRecentsAnimation(bool30);
          paramParcel2.writeNoException();
          return true;
        case 159:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          if (taskSnapshot.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)taskSnapshot);
          } else {
            componentName2 = null;
          } 
          startRecentsActivity((Intent)componentName2, IAssistDataReceiver.Stub.asInterface(taskSnapshot.readStrongBinder()), IRecentsAnimationRunner.Stub.asInterface(taskSnapshot.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 158:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          bool14 = isInLockTaskMode();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 157:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          bool14 = startUserInBackground(taskSnapshot.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 156:
          taskSnapshot.enforceInterface("android.app.IActivityManager");
          str5 = getTagForIntentSender(IIntentSender.Stub.asInterface(taskSnapshot.readStrongBinder()), taskSnapshot.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str5);
          return true;
        case 155:
          str5.enforceInterface("android.app.IActivityManager");
          bool14 = setProcessMemoryTrimLevel(str5.readString(), str5.readInt(), str5.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 154:
          str5.enforceInterface("android.app.IActivityManager");
          rect = getTaskBounds(str5.readInt());
          paramParcel2.writeNoException();
          if (rect != null) {
            paramParcel2.writeInt(1);
            rect.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 153:
          rect.enforceInterface("android.app.IActivityManager");
          appNotRespondingViaProvider(rect.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        case 152:
          rect.enforceInterface("android.app.IActivityManager");
          performIdleMaintenance();
          paramParcel2.writeNoException();
          return true;
        case 151:
          rect.enforceInterface("android.app.IActivityManager");
          restart();
          paramParcel2.writeNoException();
          return true;
        case 150:
          rect.enforceInterface("android.app.IActivityManager");
          stackInfo = getFocusedStackInfo();
          paramParcel2.writeNoException();
          if (stackInfo != null) {
            paramParcel2.writeInt(1);
            stackInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 149:
          stackInfo.enforceInterface("android.app.IActivityManager");
          setFocusedStack(stackInfo.readInt());
          paramParcel2.writeNoException();
          return true;
        case 148:
          stackInfo.enforceInterface("android.app.IActivityManager");
          i8 = stackInfo.readInt();
          paramInt2 = stackInfo.readInt();
          bool30 = bool24;
          if (stackInfo.readInt() != 0)
            bool30 = true; 
          moveTaskToStack(i8, paramInt2, bool30);
          paramParcel2.writeNoException();
          return true;
        case 147:
          stackInfo.enforceInterface("android.app.IActivityManager");
          list4 = getAllStackInfos();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list4);
          return true;
        case 146:
          list4.enforceInterface("android.app.IActivityManager");
          iBinder15 = list4.readStrongBinder();
          bool30 = bool25;
          if (list4.readInt() != 0)
            bool30 = true; 
          hang(iBinder15, bool30);
          paramParcel2.writeNoException();
          return true;
        case 145:
          list4.enforceInterface("android.app.IActivityManager");
          bool30 = bool26;
          if (list4.readInt() != 0)
            bool30 = true; 
          setUserIsMonkey(bool30);
          paramParcel2.writeNoException();
          return true;
        case 144:
          list4.enforceInterface("android.app.IActivityManager");
          killUid(list4.readInt(), list4.readInt(), list4.readString());
          paramParcel2.writeNoException();
          return true;
        case 143:
          list4.enforceInterface("android.app.IActivityManager");
          str4 = getLaunchedFromPackage(list4.readStrongBinder());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str4);
          return true;
        case 142:
          str4.enforceInterface("android.app.IActivityManager");
          intent2 = getIntentForIntentSender(IIntentSender.Stub.asInterface(str4.readStrongBinder()));
          paramParcel2.writeNoException();
          if (intent2 != null) {
            paramParcel2.writeInt(1);
            intent2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 141:
          intent2.enforceInterface("android.app.IActivityManager");
          list3 = getBugreportWhitelistedPackages();
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list3);
          return true;
        case 140:
          list3.enforceInterface("android.app.IActivityManager");
          bool13 = launchBugReportHandlerApp();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool13);
          return true;
        case 139:
          list3.enforceInterface("android.app.IActivityManager");
          requestRemoteBugReport();
          paramParcel2.writeNoException();
          return true;
        case 138:
          list3.enforceInterface("android.app.IActivityManager");
          requestFullBugReport();
          paramParcel2.writeNoException();
          return true;
        case 137:
          list3.enforceInterface("android.app.IActivityManager");
          requestInteractiveBugReport();
          paramParcel2.writeNoException();
          return true;
        case 136:
          list3.enforceInterface("android.app.IActivityManager");
          requestInteractiveBugReportWithDescription(list3.readString(), list3.readString());
          paramParcel2.writeNoException();
          return true;
        case 135:
          list3.enforceInterface("android.app.IActivityManager");
          requestWifiBugReport(list3.readString(), list3.readString());
          paramParcel2.writeNoException();
          return true;
        case 134:
          list3.enforceInterface("android.app.IActivityManager");
          requestTelephonyBugReport(list3.readString(), list3.readString());
          paramParcel2.writeNoException();
          return true;
        case 133:
          list3.enforceInterface("android.app.IActivityManager");
          requestBugReportWithDescription(list3.readString(), list3.readString(), list3.readInt());
          paramParcel2.writeNoException();
          return true;
        case 132:
          list3.enforceInterface("android.app.IActivityManager");
          requestBugReport(list3.readInt());
          paramParcel2.writeNoException();
          return true;
        case 131:
          list3.enforceInterface("android.app.IActivityManager");
          requestSystemServerHeapDump();
          paramParcel2.writeNoException();
          return true;
        case 130:
          list3.enforceInterface("android.app.IActivityManager");
          arrayOfInt1 = getRunningUserIds();
          paramParcel2.writeNoException();
          paramParcel2.writeIntArray(arrayOfInt1);
          return true;
        case 129:
          arrayOfInt1.enforceInterface("android.app.IActivityManager");
          unregisterUserSwitchObserver(IUserSwitchObserver.Stub.asInterface(arrayOfInt1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 128:
          arrayOfInt1.enforceInterface("android.app.IActivityManager");
          registerUserSwitchObserver(IUserSwitchObserver.Stub.asInterface(arrayOfInt1.readStrongBinder()), arrayOfInt1.readString());
          paramParcel2.writeNoException();
          return true;
        case 127:
          arrayOfInt1.enforceInterface("android.app.IActivityManager");
          i7 = arrayOfInt1.readInt();
          bool30 = bool27;
          if (arrayOfInt1.readInt() != 0)
            bool30 = true; 
          i7 = stopUserWithDelayedLocking(i7, bool30, IStopUserCallback.Stub.asInterface(arrayOfInt1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i7);
          return true;
        case 126:
          arrayOfInt1.enforceInterface("android.app.IActivityManager");
          i7 = arrayOfInt1.readInt();
          bool30 = bool28;
          if (arrayOfInt1.readInt() != 0)
            bool30 = true; 
          i7 = stopUser(i7, bool30, IStopUserCallback.Stub.asInterface(arrayOfInt1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i7);
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
          paramInt2 = arrayOfInt1.readInt();
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
          i7 = startActivityAsUserWithFeature(iApplicationThread11, str20, str18, (Intent)iBinder15, str26, iBinder20, str28, i7, paramInt2, (ProfilerInfo)iIntentSender4, (Bundle)iIntentSender2, arrayOfInt1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i7);
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
          paramInt2 = arrayOfInt1.readInt();
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
          i7 = startActivityAsUser(iApplicationThread11, str18, (Intent)iBinder15, str20, iBinder20, str26, paramInt2, i7, (ProfilerInfo)iIntentSender4, (Bundle)iIntentSender2, arrayOfInt1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i7);
          return true;
        case 123:
          arrayOfInt1.enforceInterface("android.app.IActivityManager");
          bool12 = isIntentSenderABroadcast(IIntentSender.Stub.asInterface(arrayOfInt1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool12);
          return true;
        case 122:
          arrayOfInt1.enforceInterface("android.app.IActivityManager");
          bool12 = isIntentSenderAForegroundService(IIntentSender.Stub.asInterface(arrayOfInt1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool12);
          return true;
        case 121:
          arrayOfInt1.enforceInterface("android.app.IActivityManager");
          bool12 = isIntentSenderAnActivity(IIntentSender.Stub.asInterface(arrayOfInt1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool12);
          return true;
        case 120:
          arrayOfInt1.enforceInterface("android.app.IActivityManager");
          unstableProviderDied(arrayOfInt1.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        case 119:
          arrayOfInt1.enforceInterface("android.app.IActivityManager");
          i6 = getLaunchedFromUid(arrayOfInt1.readStrongBinder());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i6);
          return true;
        case 118:
          arrayOfInt1.enforceInterface("android.app.IActivityManager");
          userInfo = getCurrentUser();
          paramParcel2.writeNoException();
          if (userInfo != null) {
            paramParcel2.writeInt(1);
            userInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 117:
          userInfo.enforceInterface("android.app.IActivityManager");
          bool11 = killProcessesBelowForeground(userInfo.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 116:
          userInfo.enforceInterface("android.app.IActivityManager");
          runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
          getMyMemoryState(runningAppProcessInfo);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(1);
          runningAppProcessInfo.writeToParcel(paramParcel2, 1);
          return true;
        case 115:
          runningAppProcessInfo.enforceInterface("android.app.IActivityManager");
          removeContentProviderExternalAsUser(runningAppProcessInfo.readString(), runningAppProcessInfo.readStrongBinder(), runningAppProcessInfo.readInt());
          paramParcel2.writeNoException();
          return true;
        case 114:
          runningAppProcessInfo.enforceInterface("android.app.IActivityManager");
          removeContentProviderExternal(runningAppProcessInfo.readString(), runningAppProcessInfo.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        case 113:
          runningAppProcessInfo.enforceInterface("android.app.IActivityManager");
          contentProviderHolder2 = getContentProviderExternal(runningAppProcessInfo.readString(), runningAppProcessInfo.readInt(), runningAppProcessInfo.readStrongBinder(), runningAppProcessInfo.readString());
          paramParcel2.writeNoException();
          if (contentProviderHolder2 != null) {
            paramParcel2.writeInt(1);
            contentProviderHolder2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 112:
          contentProviderHolder2.enforceInterface("android.app.IActivityManager");
          killAllBackgroundProcesses();
          paramParcel2.writeNoException();
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
          paramParcel2.writeNoException();
          return true;
        case 110:
          contentProviderHolder2.enforceInterface("android.app.IActivityManager");
          arrayOfLong = getProcessPss(contentProviderHolder2.createIntArray());
          paramParcel2.writeNoException();
          paramParcel2.writeLongArray(arrayOfLong);
          return true;
        case 109:
          arrayOfLong.enforceInterface("android.app.IActivityManager");
          if (arrayOfLong.readInt() != 0) {
            Configuration configuration1 = (Configuration)Configuration.CREATOR.createFromParcel((Parcel)arrayOfLong);
          } else {
            arrayOfLong = null;
          } 
          updatePersistentConfiguration((Configuration)arrayOfLong);
          paramParcel2.writeNoException();
          return true;
        case 108:
          arrayOfLong.enforceInterface("android.app.IActivityManager");
          bool11 = isIntentSenderTargetedToPackage(IIntentSender.Stub.asInterface(arrayOfLong.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 107:
          arrayOfLong.enforceInterface("android.app.IActivityManager");
          unregisterProcessObserver(IProcessObserver.Stub.asInterface(arrayOfLong.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 106:
          arrayOfLong.enforceInterface("android.app.IActivityManager");
          registerProcessObserver(IProcessObserver.Stub.asInterface(arrayOfLong.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 105:
          arrayOfLong.enforceInterface("android.app.IActivityManager");
          bool11 = removeTask(arrayOfLong.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 104:
          arrayOfLong.enforceInterface("android.app.IActivityManager");
          bool11 = switchUser(arrayOfLong.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 103:
          arrayOfLong.enforceInterface("android.app.IActivityManager");
          setPackageScreenCompatMode(arrayOfLong.readString(), arrayOfLong.readInt());
          paramParcel2.writeNoException();
          return true;
        case 102:
          arrayOfLong.enforceInterface("android.app.IActivityManager");
          bool11 = isUserRunning(arrayOfLong.readInt(), arrayOfLong.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
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
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 100:
          arrayOfLong.enforceInterface("android.app.IActivityManager");
          if (arrayOfLong.readInt() != 0) {
            Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)arrayOfLong);
          } else {
            paramParcel2 = null;
          } 
          i4 = arrayOfLong.readInt();
          if (arrayOfLong.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel((Parcel)arrayOfLong);
          } else {
            arrayOfLong = null;
          } 
          getProviderMimeTypeAsync((Uri)paramParcel2, i4, (RemoteCallback)arrayOfLong);
          return true;
        case 99:
          arrayOfLong.enforceInterface("android.app.IActivityManager");
          if (arrayOfLong.readInt() != 0) {
            Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)arrayOfLong);
          } else {
            iBinder15 = null;
          } 
          str3 = getProviderMimeType((Uri)iBinder15, arrayOfLong.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str3);
          return true;
        case 98:
          str3.enforceInterface("android.app.IActivityManager");
          i15 = str3.readInt();
          i4 = str3.readInt();
          str15 = str3.readString();
          paramInt2 = str3.readInt();
          str11 = str3.readString();
          if (str3.readInt() != 0) {
            bool30 = true;
          } else {
            bool30 = false;
          } 
          crashApplication(i15, i4, str15, paramInt2, str11, bool30);
          paramParcel2.writeNoException();
          return true;
        case 97:
          str3.enforceInterface("android.app.IActivityManager");
          bool9 = isTopActivityImmersive();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
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
          paramParcel2.writeNoException();
          return true;
        case 95:
          str3.enforceInterface("android.app.IActivityManager");
          finishHeavyWeightApp();
          paramParcel2.writeNoException();
          return true;
        case 94:
          str3.enforceInterface("android.app.IActivityManager");
          list2 = getRunningExternalApplications();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list2);
          return true;
        case 93:
          list2.enforceInterface("android.app.IActivityManager");
          bool8 = isUserAMonkey();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 92:
          list2.enforceInterface("android.app.IActivityManager");
          killBackgroundProcesses(list2.readString(), list2.readInt());
          paramParcel2.writeNoException();
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
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 90:
          list2.enforceInterface("android.app.IActivityManager");
          killApplicationProcess(list2.readString(), list2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 89:
          list2.enforceInterface("android.app.IActivityManager");
          arrayOfMemoryInfo = getProcessMemoryInfo(list2.createIntArray());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfMemoryInfo, 1);
          return true;
        case 88:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          closeSystemDialogs(arrayOfMemoryInfo.readString());
          paramParcel2.writeNoException();
          return true;
        case 87:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          killApplication(arrayOfMemoryInfo.readString(), arrayOfMemoryInfo.readInt(), arrayOfMemoryInfo.readInt(), arrayOfMemoryInfo.readString());
          paramParcel2.writeNoException();
          return true;
        case 86:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          addPackageDependency(arrayOfMemoryInfo.readString());
          paramParcel2.writeNoException();
          return true;
        case 85:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          i2 = arrayOfMemoryInfo.readInt();
          paramInt2 = arrayOfMemoryInfo.readInt();
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
          i2 = handleIncomingUser(i2, paramInt2, i15, bool30, bool29, arrayOfMemoryInfo.readString(), arrayOfMemoryInfo.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i2);
          return true;
        case 84:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          i2 = getUidForIntentSender(IIntentSender.Stub.asInterface(arrayOfMemoryInfo.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i2);
          return true;
        case 83:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          if (arrayOfMemoryInfo.readInt() != 0) {
            ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel((Parcel)arrayOfMemoryInfo);
          } else {
            arrayOfMemoryInfo = null;
          } 
          unbindBackupAgent((ApplicationInfo)arrayOfMemoryInfo);
          paramParcel2.writeNoException();
          return true;
        case 82:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          backupAgentCreated(arrayOfMemoryInfo.readString(), arrayOfMemoryInfo.readStrongBinder(), arrayOfMemoryInfo.readInt());
          paramParcel2.writeNoException();
          return true;
        case 81:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          bool7 = bindBackupAgent(arrayOfMemoryInfo.readString(), arrayOfMemoryInfo.readInt(), arrayOfMemoryInfo.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
          return true;
        case 80:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          resumeAppSwitches();
          paramParcel2.writeNoException();
          return true;
        case 79:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          stopAppSwitches();
          paramParcel2.writeNoException();
          return true;
        case 78:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          bool7 = shutdown(arrayOfMemoryInfo.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
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
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool6);
          return true;
        case 76:
          arrayOfMemoryInfo.enforceInterface("android.app.IActivityManager");
          if (arrayOfMemoryInfo.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)arrayOfMemoryInfo);
          } else {
            iBinder14 = null;
          } 
          iBinder3 = peekService((Intent)iBinder14, arrayOfMemoryInfo.readString(), arrayOfMemoryInfo.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeStrongBinder(iBinder3);
          return true;
        case 75:
          iBinder3.enforceInterface("android.app.IActivityManager");
          list1 = getRunningAppProcesses();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list1);
          return true;
        case 74:
          list1.enforceInterface("android.app.IActivityManager");
          list1 = (List)getServices(list1.readInt(), list1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list1);
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
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool6);
          return true;
        case 72:
          list1.enforceInterface("android.app.IActivityManager");
          forceStopPackage(list1.readString(), list1.readInt());
          paramParcel2.writeNoException();
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
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool6);
          return true;
        case 70:
          list1.enforceInterface("android.app.IActivityManager");
          list1 = (List)getProcessesInErrorState();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list1);
          return true;
        case 69:
          list1.enforceInterface("android.app.IActivityManager");
          memoryInfo = new ActivityManager.MemoryInfo();
          getMemoryInfo(memoryInfo);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(1);
          memoryInfo.writeToParcel(paramParcel2, 1);
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
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool6);
          return true;
        case 67:
          memoryInfo.enforceInterface("android.app.IActivityManager");
          if (memoryInfo.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)memoryInfo);
          } else {
            iBinder13 = null;
          } 
          n = getForegroundServiceType((ComponentName)iBinder13, memoryInfo.readStrongBinder());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(n);
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
          paramParcel2.writeNoException();
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
          paramParcel2.writeNoException();
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
          paramParcel2.writeNoException();
          return true;
        case 63:
          memoryInfo.enforceInterface("android.app.IActivityManager");
          setRequestedOrientation(memoryInfo.readStrongBinder(), memoryInfo.readInt());
          paramParcel2.writeNoException();
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
          paramInt2 = str2.readInt();
          if (str2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str2);
          } else {
            iBinder13 = null;
          } 
          iIntentSender5 = getIntentSenderWithFeature(i15, str27, str7, iBinder18, str20, n, arrayOfIntent1, arrayOfString2, paramInt2, (Bundle)iBinder13, str2.readInt());
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
          paramInt2 = iBinder2.readInt();
          arrayOfIntent2 = (Intent[])iBinder2.createTypedArray(Intent.CREATOR);
          arrayOfString1 = iBinder2.createStringArray();
          n = iBinder2.readInt();
          if (iBinder2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)iBinder2);
          } else {
            iIntentSender5 = null;
          } 
          iIntentSender5 = getIntentSender(i15, str25, iBinder16, str17, paramInt2, arrayOfIntent2, arrayOfString1, n, (Bundle)iIntentSender5, iBinder2.readInt());
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
          paramInt2 = contentProviderHolder1.readInt();
          if (contentProviderHolder1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)contentProviderHolder1);
          } else {
            contentProviderHolder1 = null;
          } 
          moveTaskToFront(iApplicationThread5, str10, j, paramInt2, (Bundle)contentProviderHolder1);
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
          paramInt2 = list.readInt();
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
          j = broadcastIntentWithFeature(iApplicationThread10, str17, (Intent)str12, str19, iIntentReceiver5, j, str25, (Bundle)iApplicationThread2, arrayOfString4, paramInt2, (Bundle)str6, bool30, bool29, list.readInt());
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
          paramInt2 = list.readInt();
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
          j = broadcastIntent(iApplicationThread9, (Intent)str12, str19, iIntentReceiver4, paramInt2, str22, (Bundle)iApplicationThread2, arrayOfString3, j, (Bundle)str6, bool30, bool29, list.readInt());
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
          paramInt2 = intent1.readInt();
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
          i = startActivityWithFeature(iApplicationThread1, str21, str16, (Intent)str12, str24, iBinder19, str19, paramInt2, i, (ProfilerInfo)iBinder6, (Bundle)intent1);
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
          paramInt2 = intent1.readInt();
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
          i = startActivity(iApplicationThread1, str16, (Intent)str12, str19, iBinder17, str21, i, paramInt2, (ProfilerInfo)iBinder6, (Bundle)intent1);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
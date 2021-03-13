package android.app;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.IIntentSender;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.ParceledListSlice;
import android.content.pm.UserInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.GraphicBuffer;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Singleton;
import android.util.Size;
import android.window.WindowContainerToken;
import com.android.internal.app.LocalePicker;
import com.android.internal.os.RoSystemProperties;
import com.android.internal.os.TransferPipe;
import com.android.internal.util.FastPrintWriter;
import com.android.internal.util.MemInfoReader;
import com.android.internal.util.Preconditions;
import com.android.server.LocalServices;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public class ActivityManager {
  public static final String ACTION_REPORT_HEAP_LIMIT = "android.app.action.REPORT_HEAP_LIMIT";
  
  public static final int APP_START_MODE_DELAYED = 1;
  
  public static final int APP_START_MODE_DELAYED_RIGID = 2;
  
  public static final int APP_START_MODE_DISABLED = 3;
  
  public static final int APP_START_MODE_NORMAL = 0;
  
  public static final int ASSIST_CONTEXT_AUTOFILL = 2;
  
  public static final int ASSIST_CONTEXT_BASIC = 0;
  
  public static final int ASSIST_CONTEXT_FULL = 1;
  
  public static final int BROADCAST_FAILED_USER_STOPPED = -2;
  
  public static final int BROADCAST_STICKY_CANT_HAVE_PERMISSION = -1;
  
  public static final int BROADCAST_SUCCESS = 0;
  
  public static final int COMPAT_MODE_ALWAYS = -1;
  
  public static final int COMPAT_MODE_DISABLED = 0;
  
  public static final int COMPAT_MODE_ENABLED = 1;
  
  public static final int COMPAT_MODE_NEVER = -2;
  
  public static final int COMPAT_MODE_TOGGLE = 2;
  
  public static final int COMPAT_MODE_UNKNOWN = -3;
  
  private static final boolean DEVELOPMENT_FORCE_LOW_RAM;
  
  private static final int FIRST_START_FATAL_ERROR_CODE = -100;
  
  private static final int FIRST_START_NON_FATAL_ERROR_CODE = 100;
  
  private static final int FIRST_START_SUCCESS_CODE = 0;
  
  public static final int FLAG_AND_LOCKED = 2;
  
  public static final int FLAG_AND_UNLOCKED = 4;
  
  public static final int FLAG_AND_UNLOCKING_OR_UNLOCKED = 8;
  
  public static final int FLAG_OR_STOPPED = 1;
  
  private static final Singleton<IActivityManager> IActivityManagerSingleton;
  
  public static final int INSTR_FLAG_DISABLE_HIDDEN_API_CHECKS = 1;
  
  public static final int INSTR_FLAG_DISABLE_ISOLATED_STORAGE = 2;
  
  public static final int INSTR_FLAG_DISABLE_TEST_API_CHECKS = 4;
  
  public static final int INTENT_SENDER_ACTIVITY = 2;
  
  public static final int INTENT_SENDER_ACTIVITY_RESULT = 3;
  
  public static final int INTENT_SENDER_BROADCAST = 1;
  
  public static final int INTENT_SENDER_FOREGROUND_SERVICE = 5;
  
  public static final int INTENT_SENDER_SERVICE = 4;
  
  private static final int LAST_START_FATAL_ERROR_CODE = -1;
  
  private static final int LAST_START_NON_FATAL_ERROR_CODE = 199;
  
  private static final int LAST_START_SUCCESS_CODE = 99;
  
  public static final int LOCK_TASK_MODE_LOCKED = 1;
  
  public static final int LOCK_TASK_MODE_NONE = 0;
  
  public static final int LOCK_TASK_MODE_PINNED = 2;
  
  public static final int MAX_PROCESS_STATE = 20;
  
  public static final String META_HOME_ALTERNATE = "android.app.home.alternate";
  
  public static final int MIN_PROCESS_STATE = 0;
  
  public static final int MOVE_TASK_NO_USER_ACTION = 2;
  
  public static final int MOVE_TASK_WITH_HOME = 1;
  
  public static final int PROCESS_CAPABILITY_ALL = 7;
  
  public static final int PROCESS_CAPABILITY_ALL_EXPLICIT = 1;
  
  public static final int PROCESS_CAPABILITY_ALL_IMPLICIT = 6;
  
  public static final int PROCESS_CAPABILITY_FOREGROUND_CAMERA = 2;
  
  public static final int PROCESS_CAPABILITY_FOREGROUND_LOCATION = 1;
  
  public static final int PROCESS_CAPABILITY_FOREGROUND_MICROPHONE = 4;
  
  public static final int PROCESS_CAPABILITY_NONE = 0;
  
  public static final int PROCESS_STATE_BACKUP = 9;
  
  public static final int PROCESS_STATE_BOUND_FOREGROUND_SERVICE = 5;
  
  public static final int PROCESS_STATE_BOUND_TOP = 3;
  
  public static final int PROCESS_STATE_CACHED_ACTIVITY = 16;
  
  public static final int PROCESS_STATE_CACHED_ACTIVITY_CLIENT = 17;
  
  public static final int PROCESS_STATE_CACHED_EMPTY = 19;
  
  public static final int PROCESS_STATE_CACHED_RECENT = 18;
  
  public static final int PROCESS_STATE_FOREGROUND_SERVICE = 4;
  
  public static final int PROCESS_STATE_HEAVY_WEIGHT = 13;
  
  public static final int PROCESS_STATE_HOME = 14;
  
  public static final int PROCESS_STATE_IMPORTANT_BACKGROUND = 7;
  
  public static final int PROCESS_STATE_IMPORTANT_FOREGROUND = 6;
  
  public static final int PROCESS_STATE_LAST_ACTIVITY = 15;
  
  public static final int PROCESS_STATE_NONEXISTENT = 20;
  
  public static final int PROCESS_STATE_PERSISTENT = 0;
  
  public static final int PROCESS_STATE_PERSISTENT_UI = 1;
  
  public static final int PROCESS_STATE_RECEIVER = 11;
  
  public static final int PROCESS_STATE_SERVICE = 10;
  
  public static final int PROCESS_STATE_TOP = 2;
  
  public static final int PROCESS_STATE_TOP_SLEEPING = 12;
  
  public static final int PROCESS_STATE_TRANSIENT_BACKGROUND = 8;
  
  public static final int PROCESS_STATE_UNKNOWN = -1;
  
  public static final int RECENT_IGNORE_UNAVAILABLE = 2;
  
  public static final int RECENT_WITH_EXCLUDED = 1;
  
  public static final int START_ABORTED = 102;
  
  public static final int START_ASSISTANT_HIDDEN_SESSION = -90;
  
  public static final int START_ASSISTANT_NOT_ACTIVE_SESSION = -89;
  
  public static final int START_CANCELED = -96;
  
  public static final int START_CLASS_NOT_FOUND = -92;
  
  public static final int START_DELIVERED_TO_TOP = 3;
  
  public static final int START_FLAG_DEBUG = 2;
  
  public static final int START_FLAG_NATIVE_DEBUGGING = 8;
  
  public static final int START_FLAG_ONLY_IF_NEEDED = 1;
  
  public static final int START_FLAG_TRACK_ALLOCATION = 4;
  
  public static final int START_FORWARD_AND_REQUEST_CONFLICT = -93;
  
  public static final int START_INTENT_NOT_RESOLVED = -91;
  
  public static final int START_NOT_ACTIVITY = -95;
  
  public static final int START_NOT_CURRENT_USER_ACTIVITY = -98;
  
  public static final int START_NOT_VOICE_COMPATIBLE = -97;
  
  public static final int START_PERMISSION_DENIED = -94;
  
  public static final int START_RETURN_INTENT_TO_CALLER = 1;
  
  public static final int START_RETURN_LOCK_TASK_MODE_VIOLATION = 101;
  
  public static final int START_SUCCESS = 0;
  
  public static final int START_SWITCHES_CANCELED = 100;
  
  public static final int START_TASK_TO_FRONT = 2;
  
  public static final int START_VOICE_HIDDEN_SESSION = -100;
  
  public static final int START_VOICE_NOT_ACTIVE_SESSION = -99;
  
  private static String TAG = "ActivityManager";
  
  public static final int UID_OBSERVER_ACTIVE = 8;
  
  public static final int UID_OBSERVER_CACHED = 16;
  
  public static final int UID_OBSERVER_GONE = 2;
  
  public static final int UID_OBSERVER_IDLE = 4;
  
  public static final int UID_OBSERVER_PROCSTATE = 1;
  
  public static final int USER_OP_ERROR_IS_SYSTEM = -3;
  
  public static final int USER_OP_ERROR_RELATED_USERS_CANNOT_STOP = -4;
  
  public static final int USER_OP_IS_CURRENT = -2;
  
  public static final int USER_OP_SUCCESS = 0;
  
  public static final int USER_OP_UNKNOWN_USER = -1;
  
  private static volatile boolean sSystemReady = false;
  
  Point mAppTaskThumbnailSize;
  
  private final Context mContext;
  
  final ArrayMap<OnUidImportanceListener, UidObserver> mImportanceListeners = new ArrayMap();
  
  static {
    DEVELOPMENT_FORCE_LOW_RAM = SystemProperties.getBoolean("debug.force_low_ram", false);
    IActivityManagerSingleton = new Singleton<IActivityManager>() {
        protected IActivityManager create() {
          return IActivityManager.Stub.asInterface(ServiceManager.getService("activity"));
        }
      };
  }
  
  ActivityManager(Context paramContext, Handler paramHandler) {
    this.mContext = paramContext;
  }
  
  public static void broadcastStickyIntent(Intent paramIntent, int paramInt) {
    broadcastStickyIntent(paramIntent, -1, paramInt);
  }
  
  public static void broadcastStickyIntent(Intent paramIntent, int paramInt1, int paramInt2) {
    try {
      getService().broadcastIntentWithFeature(null, null, paramIntent, null, null, -1, null, null, null, paramInt1, null, false, true, paramInt2);
    } catch (RemoteException remoteException) {}
  }
  
  public static int checkComponentPermission(String paramString, int paramInt1, int paramInt2, boolean paramBoolean) {
    int i = UserHandle.getAppId(paramInt1);
    if (i == 0 || i == 1000)
      return 0; 
    if (UserHandle.isIsolated(paramInt1))
      return -1; 
    if (paramInt2 >= 0 && UserHandle.isSameApp(paramInt1, paramInt2))
      return 0; 
    if (!paramBoolean)
      return -1; 
    if (paramString == null)
      return 0; 
    try {
      return AppGlobals.getPackageManager().checkUidPermission(paramString, paramInt1);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static int checkUidPermission(String paramString, int paramInt) {
    try {
      return AppGlobals.getPackageManager().checkUidPermission(paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static void dumpPackageStateStatic(FileDescriptor paramFileDescriptor, String paramString) {
    FastPrintWriter fastPrintWriter = new FastPrintWriter(new FileOutputStream(paramFileDescriptor));
    dumpService((PrintWriter)fastPrintWriter, paramFileDescriptor, "package", new String[] { paramString });
    fastPrintWriter.println();
    dumpService((PrintWriter)fastPrintWriter, paramFileDescriptor, "activity", new String[] { "-a", "package", paramString });
    fastPrintWriter.println();
    dumpService((PrintWriter)fastPrintWriter, paramFileDescriptor, "meminfo", new String[] { "--local", "--package", paramString });
    fastPrintWriter.println();
    dumpService((PrintWriter)fastPrintWriter, paramFileDescriptor, "procstats", new String[] { paramString });
    fastPrintWriter.println();
    dumpService((PrintWriter)fastPrintWriter, paramFileDescriptor, "usagestats", new String[] { paramString });
    fastPrintWriter.println();
    dumpService((PrintWriter)fastPrintWriter, paramFileDescriptor, "batterystats", new String[] { paramString });
    fastPrintWriter.flush();
  }
  
  private static void dumpService(PrintWriter paramPrintWriter, FileDescriptor paramFileDescriptor, String paramString, String[] paramArrayOfString) {
    paramPrintWriter.print("DUMP OF SERVICE ");
    paramPrintWriter.print(paramString);
    paramPrintWriter.println(":");
    IBinder iBinder = ServiceManager.checkService(paramString);
    if (iBinder == null) {
      paramPrintWriter.println("  (Service not found)");
      paramPrintWriter.flush();
      return;
    } 
    paramPrintWriter.flush();
    if (iBinder instanceof android.os.Binder) {
      try {
        iBinder.dump(paramFileDescriptor, paramArrayOfString);
      } finally {
        paramFileDescriptor = null;
        paramPrintWriter.println("Failure dumping service:");
        paramFileDescriptor.printStackTrace(paramPrintWriter);
      } 
    } else {
      TransferPipe transferPipe;
      String str = null;
      paramString = str;
      try {
        paramPrintWriter.flush();
        paramString = str;
        TransferPipe transferPipe1 = new TransferPipe();
        paramString = str;
        this();
        transferPipe = transferPipe1;
        transferPipe1.setBufferPrefix("  ");
        transferPipe = transferPipe1;
      } finally {
        paramFileDescriptor = null;
        if (transferPipe != null)
          transferPipe.kill(); 
        paramPrintWriter.println("Failure dumping service:");
      } 
    } 
  }
  
  private void ensureAppTaskThumbnailSizeLocked() {
    if (this.mAppTaskThumbnailSize == null)
      try {
        this.mAppTaskThumbnailSize = getTaskService().getAppTaskThumbnailSize();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @SystemApi
  public static int getCurrentUser() {
    try {
      boolean bool;
      UserInfo userInfo = getService().getCurrentUser();
      if (userInfo != null) {
        bool = userInfo.id;
      } else {
        bool = false;
      } 
      return bool;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  static int getLauncherLargeIconSizeInner(Context paramContext) {
    Resources resources = paramContext.getResources();
    int i = resources.getDimensionPixelSize(17104896);
    if ((resources.getConfiguration()).smallestScreenWidthDp < 600)
      return i; 
    int j = (resources.getDisplayMetrics()).densityDpi;
    return (j != 120) ? ((j != 160) ? ((j != 213) ? ((j != 240) ? ((j != 320) ? ((j != 480) ? (int)(i * 1.5F + 0.5F) : (i * 320 * 2 / 480)) : (i * 480 / 320)) : (i * 320 / 240)) : (i * 320 / 240)) : (i * 240 / 160)) : (i * 160 / 120);
  }
  
  @Deprecated
  public static int getMaxNumPictureInPictureActions() {
    return 3;
  }
  
  @Deprecated
  public static int getMaxRecentTasksStatic() {
    return ActivityTaskManager.getMaxRecentTasksStatic();
  }
  
  public static void getMyMemoryState(RunningAppProcessInfo paramRunningAppProcessInfo) {
    try {
      getService().getMyMemoryState(paramRunningAppProcessInfo);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static IActivityManager getService() {
    return (IActivityManager)IActivityManagerSingleton.get();
  }
  
  private static IActivityTaskManager getTaskService() {
    return ActivityTaskManager.getService();
  }
  
  public static int handleIncomingUser(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2) {
    if (UserHandle.getUserId(paramInt2) == paramInt3)
      return paramInt3; 
    try {
      return getService().handleIncomingUser(paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2, paramString1, paramString2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static boolean isForegroundService(int paramInt) {
    boolean bool;
    if (paramInt == 4) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isHighEndGfx() {
    boolean bool;
    if (!isLowRamDeviceStatic() && !RoSystemProperties.CONFIG_AVOID_GFX_ACCEL && !Resources.getSystem().getBoolean(17891372)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isLowMemoryKillReportSupported() {
    return SystemProperties.getBoolean("persist.sys.lmk.reportkills", false);
  }
  
  public static boolean isLowRamDeviceStatic() {
    return (RoSystemProperties.CONFIG_LOW_RAM || (Build.IS_DEBUGGABLE && DEVELOPMENT_FORCE_LOW_RAM));
  }
  
  public static final boolean isProcStateBackground(int paramInt) {
    boolean bool;
    if (paramInt >= 8) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public static boolean isRunningInTestHarness() {
    return SystemProperties.getBoolean("ro.test_harness", false);
  }
  
  public static boolean isRunningInUserTestHarness() {
    return SystemProperties.getBoolean("persist.sys.test_harness", false);
  }
  
  public static boolean isSmallBatteryDevice() {
    return RoSystemProperties.CONFIG_SMALL_BATTERY;
  }
  
  public static final boolean isStartResultFatalError(int paramInt) {
    boolean bool;
    if (-100 <= paramInt && paramInt <= -1) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static final boolean isStartResultSuccessful(int paramInt) {
    boolean bool;
    if (paramInt >= 0 && paramInt <= 99) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isSystemReady() {
    if (!sSystemReady)
      if (ActivityThread.isSystem()) {
        sSystemReady = ((ActivityManagerInternal)LocalServices.getService(ActivityManagerInternal.class)).isSystemReady();
      } else {
        sSystemReady = true;
      }  
    return sSystemReady;
  }
  
  public static boolean isUserAMonkey() {
    try {
      return getService().isUserAMonkey();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static void logoutCurrentUser() {
    int i = getCurrentUser();
    if (i != 0)
      try {
        getService().switchUser(0);
        getService().stopUser(i, false, null);
      } catch (RemoteException remoteException) {
        remoteException.rethrowFromSystemServer();
      }  
  }
  
  public static void noteAlarmFinish(PendingIntent paramPendingIntent, WorkSource paramWorkSource, int paramInt, String paramString) {
    try {
      IActivityManager iActivityManager = getService();
      if (paramPendingIntent != null) {
        IIntentSender iIntentSender = paramPendingIntent.getTarget();
      } else {
        paramPendingIntent = null;
      } 
      iActivityManager.noteAlarmFinish((IIntentSender)paramPendingIntent, paramWorkSource, paramInt, paramString);
    } catch (RemoteException remoteException) {}
  }
  
  public static void noteAlarmStart(PendingIntent paramPendingIntent, WorkSource paramWorkSource, int paramInt, String paramString) {
    try {
      IActivityManager iActivityManager = getService();
      if (paramPendingIntent != null) {
        IIntentSender iIntentSender = paramPendingIntent.getTarget();
      } else {
        paramPendingIntent = null;
      } 
      iActivityManager.noteAlarmStart((IIntentSender)paramPendingIntent, paramWorkSource, paramInt, paramString);
    } catch (RemoteException remoteException) {}
  }
  
  public static void noteWakeupAlarm(PendingIntent paramPendingIntent, WorkSource paramWorkSource, int paramInt, String paramString1, String paramString2) {
    try {
      IActivityManager iActivityManager = getService();
      if (paramPendingIntent != null) {
        IIntentSender iIntentSender = paramPendingIntent.getTarget();
      } else {
        paramPendingIntent = null;
      } 
      iActivityManager.noteWakeupAlarm((IIntentSender)paramPendingIntent, paramWorkSource, paramInt, paramString1, paramString2);
    } catch (RemoteException remoteException) {}
  }
  
  public static void printCapabilitiesFull(PrintWriter paramPrintWriter, int paramInt) {
    printCapabilitiesSummary(paramPrintWriter, paramInt);
    paramInt &= 0xFFFFFFF8;
    if (paramInt != 0) {
      paramPrintWriter.print('+');
      paramPrintWriter.print(paramInt);
    } 
  }
  
  public static void printCapabilitiesSummary(PrintWriter paramPrintWriter, int paramInt) {
    byte b = 45;
    if ((paramInt & 0x1) != 0) {
      byte b1 = 76;
      i = b1;
    } else {
      byte b1 = 45;
      i = b1;
    } 
    paramPrintWriter.print(i);
    if ((paramInt & 0x2) != 0) {
      byte b1 = 67;
      i = b1;
    } else {
      byte b1 = 45;
      i = b1;
    } 
    paramPrintWriter.print(i);
    int i = b;
    if ((paramInt & 0x4) != 0) {
      paramInt = 77;
      i = paramInt;
    } 
    paramPrintWriter.print(i);
  }
  
  public static final int processStateAmToProto(int paramInt) {
    switch (paramInt) {
      default:
        return 998;
      case 20:
        return 1019;
      case 19:
        return 1018;
      case 18:
        return 1017;
      case 17:
        return 1016;
      case 16:
        return 1015;
      case 15:
        return 1014;
      case 14:
        return 1013;
      case 13:
        return 1012;
      case 12:
        return 1011;
      case 11:
        return 1010;
      case 10:
        return 1009;
      case 9:
        return 1008;
      case 8:
        return 1007;
      case 7:
        return 1006;
      case 6:
        return 1005;
      case 5:
        return 1004;
      case 4:
        return 1003;
      case 3:
        return 1020;
      case 2:
        return 1002;
      case 1:
        return 1001;
      case 0:
        return 1000;
      case -1:
        break;
    } 
    return 999;
  }
  
  public static void resumeAppSwitches() throws RemoteException {
    getService().resumeAppSwitches();
  }
  
  @SystemApi
  public static void setPersistentVrThread(int paramInt) {
    try {
      getService().setPersistentVrThread(paramInt);
    } catch (RemoteException remoteException) {}
  }
  
  public static void setVrThread(int paramInt) {
    try {
      getTaskService().setVrThread(paramInt);
    } catch (RemoteException remoteException) {}
  }
  
  public static int staticGetLargeMemoryClass() {
    String str = SystemProperties.get("dalvik.vm.heapsize", "16m");
    return Integer.parseInt(str.substring(0, str.length() - 1));
  }
  
  public static int staticGetMemoryClass() {
    String str = SystemProperties.get("dalvik.vm.heapgrowthlimit", "");
    return (str != null && !"".equals(str)) ? Integer.parseInt(str.substring(0, str.length() - 1)) : staticGetLargeMemoryClass();
  }
  
  public int addAppTask(Activity paramActivity, Intent paramIntent, TaskDescription paramTaskDescription, Bitmap paramBitmap) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureAppTaskThumbnailSizeLocked : ()V
    //   6: aload_0
    //   7: getfield mAppTaskThumbnailSize : Landroid/graphics/Point;
    //   10: astore #5
    //   12: aload_0
    //   13: monitorexit
    //   14: aload #4
    //   16: invokevirtual getWidth : ()I
    //   19: istore #6
    //   21: aload #4
    //   23: invokevirtual getHeight : ()I
    //   26: istore #7
    //   28: iload #6
    //   30: aload #5
    //   32: getfield x : I
    //   35: if_icmpne -> 52
    //   38: aload #4
    //   40: astore #8
    //   42: iload #7
    //   44: aload #5
    //   46: getfield y : I
    //   49: if_icmpeq -> 213
    //   52: aload #5
    //   54: getfield x : I
    //   57: aload #5
    //   59: getfield y : I
    //   62: aload #4
    //   64: invokevirtual getConfig : ()Landroid/graphics/Bitmap$Config;
    //   67: invokestatic createBitmap : (IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   70: astore #8
    //   72: fconst_0
    //   73: fstore #9
    //   75: aload #5
    //   77: getfield x : I
    //   80: iload #6
    //   82: imul
    //   83: aload #5
    //   85: getfield y : I
    //   88: iload #7
    //   90: imul
    //   91: if_icmple -> 128
    //   94: aload #5
    //   96: getfield x : I
    //   99: i2f
    //   100: iload #7
    //   102: i2f
    //   103: fdiv
    //   104: fstore #10
    //   106: aload #5
    //   108: getfield y : I
    //   111: i2f
    //   112: iload #6
    //   114: i2f
    //   115: fload #10
    //   117: fmul
    //   118: fsub
    //   119: ldc_w 0.5
    //   122: fmul
    //   123: fstore #9
    //   125: goto -> 153
    //   128: aload #5
    //   130: getfield y : I
    //   133: i2f
    //   134: iload #6
    //   136: i2f
    //   137: fdiv
    //   138: fstore #10
    //   140: aload #5
    //   142: getfield x : I
    //   145: i2f
    //   146: fstore #11
    //   148: iload #7
    //   150: i2f
    //   151: fstore #11
    //   153: new android/graphics/Matrix
    //   156: dup
    //   157: invokespecial <init> : ()V
    //   160: astore #5
    //   162: aload #5
    //   164: fload #10
    //   166: fload #10
    //   168: invokevirtual setScale : (FF)V
    //   171: aload #5
    //   173: ldc_w 0.5
    //   176: fload #9
    //   178: fadd
    //   179: f2i
    //   180: i2f
    //   181: fconst_0
    //   182: invokevirtual postTranslate : (FF)Z
    //   185: pop
    //   186: new android/graphics/Canvas
    //   189: dup
    //   190: aload #8
    //   192: invokespecial <init> : (Landroid/graphics/Bitmap;)V
    //   195: astore #12
    //   197: aload #12
    //   199: aload #4
    //   201: aload #5
    //   203: aconst_null
    //   204: invokevirtual drawBitmap : (Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
    //   207: aload #12
    //   209: aconst_null
    //   210: invokevirtual setBitmap : (Landroid/graphics/Bitmap;)V
    //   213: aload_3
    //   214: astore #4
    //   216: aload_3
    //   217: ifnonnull -> 229
    //   220: new android/app/ActivityManager$TaskDescription
    //   223: dup
    //   224: invokespecial <init> : ()V
    //   227: astore #4
    //   229: invokestatic getTaskService : ()Landroid/app/IActivityTaskManager;
    //   232: aload_1
    //   233: invokevirtual getActivityToken : ()Landroid/os/IBinder;
    //   236: aload_2
    //   237: aload #4
    //   239: aload #8
    //   241: invokeinterface addAppTask : (Landroid/os/IBinder;Landroid/content/Intent;Landroid/app/ActivityManager$TaskDescription;Landroid/graphics/Bitmap;)I
    //   246: istore #6
    //   248: iload #6
    //   250: ireturn
    //   251: astore_1
    //   252: aload_1
    //   253: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   256: athrow
    //   257: astore_1
    //   258: aload_0
    //   259: monitorexit
    //   260: aload_1
    //   261: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	257	finally
    //   229	248	251	android/os/RemoteException
    //   258	260	257	finally
  }
  
  @SystemApi
  public void addOnUidImportanceListener(OnUidImportanceListener paramOnUidImportanceListener, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mImportanceListeners : Landroid/util/ArrayMap;
    //   6: aload_1
    //   7: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   10: ifne -> 66
    //   13: new android/app/ActivityManager$UidObserver
    //   16: astore_3
    //   17: aload_3
    //   18: aload_1
    //   19: aload_0
    //   20: getfield mContext : Landroid/content/Context;
    //   23: invokespecial <init> : (Landroid/app/ActivityManager$OnUidImportanceListener;Landroid/content/Context;)V
    //   26: invokestatic getService : ()Landroid/app/IActivityManager;
    //   29: aload_3
    //   30: iconst_3
    //   31: iload_2
    //   32: invokestatic importanceToProcState : (I)I
    //   35: aload_0
    //   36: getfield mContext : Landroid/content/Context;
    //   39: invokevirtual getOpPackageName : ()Ljava/lang/String;
    //   42: invokeinterface registerUidObserver : (Landroid/app/IUidObserver;IILjava/lang/String;)V
    //   47: aload_0
    //   48: getfield mImportanceListeners : Landroid/util/ArrayMap;
    //   51: aload_1
    //   52: aload_3
    //   53: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   56: pop
    //   57: aload_0
    //   58: monitorexit
    //   59: return
    //   60: astore_1
    //   61: aload_1
    //   62: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   65: athrow
    //   66: new java/lang/IllegalArgumentException
    //   69: astore #4
    //   71: new java/lang/StringBuilder
    //   74: astore_3
    //   75: aload_3
    //   76: invokespecial <init> : ()V
    //   79: aload_3
    //   80: ldc_w 'Listener already registered: '
    //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: aload_3
    //   88: aload_1
    //   89: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   92: pop
    //   93: aload #4
    //   95: aload_3
    //   96: invokevirtual toString : ()Ljava/lang/String;
    //   99: invokespecial <init> : (Ljava/lang/String;)V
    //   102: aload #4
    //   104: athrow
    //   105: astore_1
    //   106: aload_0
    //   107: monitorexit
    //   108: aload_1
    //   109: athrow
    // Exception table:
    //   from	to	target	type
    //   2	26	105	finally
    //   26	47	60	android/os/RemoteException
    //   26	47	105	finally
    //   47	57	105	finally
    //   57	59	105	finally
    //   61	66	105	finally
    //   66	105	105	finally
    //   106	108	105	finally
  }
  
  public void alwaysShowUnsupportedCompileSdkWarning(ComponentName paramComponentName) {
    try {
      getTaskService().alwaysShowUnsupportedCompileSdkWarning(paramComponentName);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void appNotResponding(String paramString) {
    try {
      getService().appNotResponding(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean clearApplicationUserData() {
    return clearApplicationUserData(this.mContext.getPackageName(), null);
  }
  
  public boolean clearApplicationUserData(String paramString, IPackageDataObserver paramIPackageDataObserver) {
    try {
      return getService().clearApplicationUserData(paramString, false, paramIPackageDataObserver, this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public void clearGrantedUriPermissions(String paramString) {
    ((UriGrantsManager)this.mContext.getSystemService("uri_grants")).clearGrantedUriPermissions(paramString);
  }
  
  public void clearWatchHeapLimit() {
    try {
      getService().setDumpHeapDebugLimit(null, 0, 0L, null);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void dumpPackageState(FileDescriptor paramFileDescriptor, String paramString) {
    dumpPackageStateStatic(paramFileDescriptor, paramString);
  }
  
  @SystemApi
  public void forceStopPackage(String paramString) {
    forceStopPackageAsUser(paramString, this.mContext.getUserId());
  }
  
  public void forceStopPackageAsUser(String paramString, int paramInt) {
    try {
      getService().forceStopPackage(paramString, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Size getAppTaskThumbnailSize() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureAppTaskThumbnailSizeLocked : ()V
    //   6: new android/util/Size
    //   9: astore_1
    //   10: aload_1
    //   11: aload_0
    //   12: getfield mAppTaskThumbnailSize : Landroid/graphics/Point;
    //   15: getfield x : I
    //   18: aload_0
    //   19: getfield mAppTaskThumbnailSize : Landroid/graphics/Point;
    //   22: getfield y : I
    //   25: invokespecial <init> : (II)V
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: areturn
    //   32: astore_1
    //   33: aload_0
    //   34: monitorexit
    //   35: aload_1
    //   36: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	32	finally
    //   33	35	32	finally
  }
  
  public List<AppTask> getAppTasks() {
    ArrayList<AppTask> arrayList = new ArrayList();
    try {
      List<IBinder> list = getTaskService().getAppTasks(this.mContext.getPackageName());
      int i = list.size();
      for (byte b = 0; b < i; b++)
        arrayList.add(new AppTask(IAppTask.Stub.asInterface(list.get(b)))); 
      return arrayList;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<String> getBugreportWhitelistedPackages() {
    try {
      return getService().getBugreportWhitelistedPackages();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ConfigurationInfo getDeviceConfigurationInfo() {
    try {
      return getTaskService().getDeviceConfigurationInfo();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getFrontActivityScreenCompatMode() {
    try {
      return getTaskService().getFrontActivityScreenCompatMode();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public ParceledListSlice<GrantedUriPermission> getGrantedUriPermissions(String paramString) {
    return ((UriGrantsManager)this.mContext.getSystemService("uri_grants")).getGrantedUriPermissions(paramString);
  }
  
  public List<ApplicationExitInfo> getHistoricalProcessExitReasons(String paramString, int paramInt1, int paramInt2) {
    try {
      List<?> list;
      ParceledListSlice<ApplicationExitInfo> parceledListSlice = getService().getHistoricalProcessExitReasons(paramString, paramInt1, paramInt2, this.mContext.getUserId());
      if (parceledListSlice == null) {
        list = Collections.emptyList();
      } else {
        list = list.getList();
      } 
      return (List)list;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getLargeMemoryClass() {
    return staticGetLargeMemoryClass();
  }
  
  public int getLauncherLargeIconDensity() {
    Resources resources = this.mContext.getResources();
    int i = (resources.getDisplayMetrics()).densityDpi;
    return ((resources.getConfiguration()).smallestScreenWidthDp < 600) ? i : ((i != 120) ? ((i != 160) ? ((i != 213) ? ((i != 240) ? ((i != 320) ? ((i != 480) ? (int)(i * 1.5F + 0.5F) : 640) : 480) : 320) : 320) : 240) : 160);
  }
  
  public int getLauncherLargeIconSize() {
    return getLauncherLargeIconSizeInner(this.mContext);
  }
  
  public int getLockTaskModeState() {
    try {
      return getTaskService().getLockTaskModeState();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getMemoryClass() {
    return staticGetMemoryClass();
  }
  
  public void getMemoryInfo(MemoryInfo paramMemoryInfo) {
    try {
      getService().getMemoryInfo(paramMemoryInfo);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean getPackageAskScreenCompat(String paramString) {
    try {
      return getTaskService().getPackageAskScreenCompat(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public int getPackageImportance(String paramString) {
    try {
      return RunningAppProcessInfo.procStateToImportanceForClient(getService().getPackageProcessState(paramString, this.mContext.getOpPackageName()), this.mContext);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getPackageScreenCompatMode(String paramString) {
    try {
      return getTaskService().getPackageScreenCompatMode(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Debug.MemoryInfo[] getProcessMemoryInfo(int[] paramArrayOfint) {
    try {
      return getService().getProcessMemoryInfo(paramArrayOfint);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ProcessErrorStateInfo> getProcessesInErrorState() {
    try {
      return getService().getProcessesInErrorState();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public List<RecentTaskInfo> getRecentTasks(int paramInt1, int paramInt2) throws SecurityException {
    if (paramInt1 >= 0)
      try {
        return getTaskService().getRecentTasks(paramInt1, paramInt2, this.mContext.getUserId()).getList();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
    this("The requested number of tasks should be >= 0");
    throw illegalArgumentException;
  }
  
  public List<RunningAppProcessInfo> getRunningAppProcesses() {
    try {
      return getService().getRunningAppProcesses();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ApplicationInfo> getRunningExternalApplications() {
    try {
      return getService().getRunningExternalApplications();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public PendingIntent getRunningServiceControlPanel(ComponentName paramComponentName) throws SecurityException {
    try {
      return getService().getRunningServiceControlPanel(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public List<RunningServiceInfo> getRunningServices(int paramInt) throws SecurityException {
    try {
      return getService().getServices(paramInt, 0);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public List<RunningTaskInfo> getRunningTasks(int paramInt) throws SecurityException {
    try {
      return getTaskService().getTasks(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public Collection<Locale> getSupportedLocales() {
    ArrayList<Locale> arrayList = new ArrayList();
    String[] arrayOfString = LocalePicker.getSupportedLocales(this.mContext);
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++)
      arrayList.add(Locale.forLanguageTag(arrayOfString[b])); 
    return arrayList;
  }
  
  public long getTotalRam() {
    MemInfoReader memInfoReader = new MemInfoReader();
    memInfoReader.readMemInfo();
    return memInfoReader.getTotalSize();
  }
  
  @SystemApi
  public int getUidImportance(int paramInt) {
    try {
      return RunningAppProcessInfo.procStateToImportanceForClient(getService().getUidProcessState(paramInt, this.mContext.getOpPackageName()), this.mContext);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isActivityStartAllowedOnDisplay(Context paramContext, int paramInt, Intent paramIntent) {
    try {
      return getTaskService().isActivityStartAllowedOnDisplay(paramInt, paramIntent, paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver()), paramContext.getUserId());
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
      return false;
    } 
  }
  
  public boolean isBackgroundRestricted() {
    try {
      return getService().isBackgroundRestricted(this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public boolean isInLockTaskMode() {
    boolean bool;
    if (getLockTaskModeState() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isLowRamDevice() {
    return isLowRamDeviceStatic();
  }
  
  public boolean isProfileForeground(UserHandle paramUserHandle) {
    UserManager userManager = (UserManager)this.mContext.getSystemService(UserManager.class);
    if (userManager != null) {
      Iterator iterator = userManager.getProfiles(getCurrentUser()).iterator();
      while (iterator.hasNext()) {
        if (((UserInfo)iterator.next()).id == paramUserHandle.getIdentifier())
          return true; 
      } 
    } 
    return false;
  }
  
  public boolean isUserRunning(int paramInt) {
    try {
      return getService().isUserRunning(paramInt, 0);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isVrModePackageEnabled(ComponentName paramComponentName) {
    try {
      return getService().isVrModePackageEnabled(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void killBackgroundProcesses(String paramString) {
    try {
      getService().killBackgroundProcesses(paramString, this.mContext.getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void killProcessesWhenImperceptible(int[] paramArrayOfint, String paramString) {
    try {
      getService().killProcessesWhenImperceptible(paramArrayOfint, paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void killUid(int paramInt, String paramString) {
    try {
      getService().killUid(UserHandle.getAppId(paramInt), UserHandle.getUserId(paramInt), paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void moveTaskToFront(int paramInt1, int paramInt2) {
    moveTaskToFront(paramInt1, paramInt2, null);
  }
  
  public void moveTaskToFront(int paramInt1, int paramInt2, Bundle paramBundle) {
    try {
      ActivityThread.ApplicationThread applicationThread = ActivityThread.currentActivityThread().getApplicationThread();
      String str = this.mContext.getPackageName();
      getTaskService().moveTaskToFront(applicationThread, str, paramInt1, paramInt2, paramBundle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void registerHomeVisibilityObserver(HomeVisibilityObserver paramHomeVisibilityObserver) {
    Preconditions.checkNotNull(paramHomeVisibilityObserver);
    try {
      paramHomeVisibilityObserver.init(this.mContext, this);
      getService().registerProcessObserver(paramHomeVisibilityObserver.mObserver);
      paramHomeVisibilityObserver.onHomeVisibilityChanged(paramHomeVisibilityObserver.mIsHomeActivityVisible);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void removeOnUidImportanceListener(OnUidImportanceListener paramOnUidImportanceListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mImportanceListeners : Landroid/util/ArrayMap;
    //   6: aload_1
    //   7: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast android/app/ActivityManager$UidObserver
    //   13: astore_2
    //   14: aload_2
    //   15: ifnull -> 36
    //   18: invokestatic getService : ()Landroid/app/IActivityManager;
    //   21: aload_2
    //   22: invokeinterface unregisterUidObserver : (Landroid/app/IUidObserver;)V
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: astore_1
    //   31: aload_1
    //   32: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   35: athrow
    //   36: new java/lang/IllegalArgumentException
    //   39: astore_3
    //   40: new java/lang/StringBuilder
    //   43: astore_2
    //   44: aload_2
    //   45: invokespecial <init> : ()V
    //   48: aload_2
    //   49: ldc_w 'Listener not registered: '
    //   52: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: pop
    //   56: aload_2
    //   57: aload_1
    //   58: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: aload_3
    //   63: aload_2
    //   64: invokevirtual toString : ()Ljava/lang/String;
    //   67: invokespecial <init> : (Ljava/lang/String;)V
    //   70: aload_3
    //   71: athrow
    //   72: astore_1
    //   73: aload_0
    //   74: monitorexit
    //   75: aload_1
    //   76: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	72	finally
    //   18	27	30	android/os/RemoteException
    //   18	27	72	finally
    //   27	29	72	finally
    //   31	36	72	finally
    //   36	72	72	finally
    //   73	75	72	finally
  }
  
  @Deprecated
  public void restartPackage(String paramString) {
    killBackgroundProcesses(paramString);
  }
  
  public void scheduleApplicationInfoChanged(List<String> paramList, int paramInt) {
    try {
      getService().scheduleApplicationInfoChanged(paramList, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void setDeviceLocales(LocaleList paramLocaleList) {
    LocalePicker.updateLocales(paramLocaleList);
  }
  
  public void setFrontActivityScreenCompatMode(int paramInt) {
    try {
      getTaskService().setFrontActivityScreenCompatMode(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setPackageAskScreenCompat(String paramString, boolean paramBoolean) {
    try {
      getTaskService().setPackageAskScreenCompat(paramString, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setPackageScreenCompatMode(String paramString, int paramInt) {
    try {
      getTaskService().setPackageScreenCompatMode(paramString, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean setProcessMemoryTrimLevel(String paramString, int paramInt1, int paramInt2) {
    try {
      return getService().setProcessMemoryTrimLevel(paramString, paramInt1, paramInt2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setProcessStateSummary(byte[] paramArrayOfbyte) {
    try {
      getService().setProcessStateSummary(paramArrayOfbyte);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setWatchHeapLimit(long paramLong) {
    try {
      getService().setDumpHeapDebugLimit(null, 0, paramLong, this.mContext.getPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean switchUser(int paramInt) {
    try {
      return getService().switchUser(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public boolean switchUser(UserHandle paramUserHandle) {
    if (paramUserHandle != null)
      return switchUser(paramUserHandle.getIdentifier()); 
    throw new IllegalArgumentException("UserHandle cannot be null.");
  }
  
  public void unregisterHomeVisibilityObserver(HomeVisibilityObserver paramHomeVisibilityObserver) {
    Preconditions.checkNotNull(paramHomeVisibilityObserver);
    try {
      getService().unregisterProcessObserver(paramHomeVisibilityObserver.mObserver);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean updateMccMncConfiguration(String paramString1, String paramString2) {
    if (paramString1 != null && paramString2 != null)
      try {
        return getService().updateMccMncConfiguration(paramString1, paramString2);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("mcc or mnc cannot be null.");
  }
  
  public static class AppTask {
    private IAppTask mAppTaskImpl;
    
    public AppTask(IAppTask param1IAppTask) {
      this.mAppTaskImpl = param1IAppTask;
    }
    
    public void finishAndRemoveTask() {
      try {
        this.mAppTaskImpl.finishAndRemoveTask();
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public ActivityManager.RecentTaskInfo getTaskInfo() {
      try {
        return this.mAppTaskImpl.getTaskInfo();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void moveToFront() {
      try {
        ActivityThread.ApplicationThread applicationThread = ActivityThread.currentActivityThread().getApplicationThread();
        String str = ActivityThread.currentPackageName();
        this.mAppTaskImpl.moveToFront(applicationThread, str);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void setExcludeFromRecents(boolean param1Boolean) {
      try {
        this.mAppTaskImpl.setExcludeFromRecents(param1Boolean);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void startActivity(Context param1Context, Intent param1Intent, Bundle param1Bundle) {
      ActivityThread activityThread = ActivityThread.currentActivityThread();
      activityThread.getInstrumentation().execStartActivityFromAppTask(param1Context, (IBinder)activityThread.getApplicationThread(), this.mAppTaskImpl, param1Intent, param1Bundle);
    }
  }
  
  public static class MemoryInfo implements Parcelable {
    public static final Parcelable.Creator<MemoryInfo> CREATOR = new Parcelable.Creator<MemoryInfo>() {
        public ActivityManager.MemoryInfo createFromParcel(Parcel param2Parcel) {
          return new ActivityManager.MemoryInfo(param2Parcel);
        }
        
        public ActivityManager.MemoryInfo[] newArray(int param2Int) {
          return new ActivityManager.MemoryInfo[param2Int];
        }
      };
    
    public long availMem;
    
    public long foregroundAppThreshold;
    
    public long hiddenAppThreshold;
    
    public boolean lowMemory;
    
    public long secondaryServerThreshold;
    
    public long threshold;
    
    public long totalMem;
    
    public long visibleAppThreshold;
    
    public MemoryInfo() {}
    
    private MemoryInfo(Parcel param1Parcel) {
      readFromParcel(param1Parcel);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void readFromParcel(Parcel param1Parcel) {
      boolean bool;
      this.availMem = param1Parcel.readLong();
      this.totalMem = param1Parcel.readLong();
      this.threshold = param1Parcel.readLong();
      if (param1Parcel.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.lowMemory = bool;
      this.hiddenAppThreshold = param1Parcel.readLong();
      this.secondaryServerThreshold = param1Parcel.readLong();
      this.visibleAppThreshold = param1Parcel.readLong();
      this.foregroundAppThreshold = param1Parcel.readLong();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeLong(this.availMem);
      param1Parcel.writeLong(this.totalMem);
      param1Parcel.writeLong(this.threshold);
      param1Parcel.writeInt(this.lowMemory);
      param1Parcel.writeLong(this.hiddenAppThreshold);
      param1Parcel.writeLong(this.secondaryServerThreshold);
      param1Parcel.writeLong(this.visibleAppThreshold);
      param1Parcel.writeLong(this.foregroundAppThreshold);
    }
  }
  
  class null implements Parcelable.Creator<MemoryInfo> {
    public ActivityManager.MemoryInfo createFromParcel(Parcel param1Parcel) {
      return new ActivityManager.MemoryInfo(param1Parcel);
    }
    
    public ActivityManager.MemoryInfo[] newArray(int param1Int) {
      return new ActivityManager.MemoryInfo[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface MoveTaskFlags {}
  
  @SystemApi
  public static interface OnUidImportanceListener {
    void onUidImportance(int param1Int1, int param1Int2);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ProcessCapability {}
  
  public static class ProcessErrorStateInfo implements Parcelable {
    public static final int CRASHED = 1;
    
    public static final Parcelable.Creator<ProcessErrorStateInfo> CREATOR = new Parcelable.Creator<ProcessErrorStateInfo>() {
        public ActivityManager.ProcessErrorStateInfo createFromParcel(Parcel param2Parcel) {
          return new ActivityManager.ProcessErrorStateInfo(param2Parcel);
        }
        
        public ActivityManager.ProcessErrorStateInfo[] newArray(int param2Int) {
          return new ActivityManager.ProcessErrorStateInfo[param2Int];
        }
      };
    
    public static final int NOT_RESPONDING = 2;
    
    public static final int NO_ERROR = 0;
    
    public int condition;
    
    public byte[] crashData = null;
    
    public String longMsg;
    
    public int pid;
    
    public String processName;
    
    public String shortMsg;
    
    public String stackTrace;
    
    public String tag;
    
    public int uid;
    
    public ProcessErrorStateInfo() {}
    
    private ProcessErrorStateInfo(Parcel param1Parcel) {
      readFromParcel(param1Parcel);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void readFromParcel(Parcel param1Parcel) {
      this.condition = param1Parcel.readInt();
      this.processName = param1Parcel.readString();
      this.pid = param1Parcel.readInt();
      this.uid = param1Parcel.readInt();
      this.tag = param1Parcel.readString();
      this.shortMsg = param1Parcel.readString();
      this.longMsg = param1Parcel.readString();
      this.stackTrace = param1Parcel.readString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.condition);
      param1Parcel.writeString(this.processName);
      param1Parcel.writeInt(this.pid);
      param1Parcel.writeInt(this.uid);
      param1Parcel.writeString(this.tag);
      param1Parcel.writeString(this.shortMsg);
      param1Parcel.writeString(this.longMsg);
      param1Parcel.writeString(this.stackTrace);
    }
  }
  
  class null implements Parcelable.Creator<ProcessErrorStateInfo> {
    public ActivityManager.ProcessErrorStateInfo createFromParcel(Parcel param1Parcel) {
      return new ActivityManager.ProcessErrorStateInfo(param1Parcel);
    }
    
    public ActivityManager.ProcessErrorStateInfo[] newArray(int param1Int) {
      return new ActivityManager.ProcessErrorStateInfo[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ProcessState {}
  
  public static class RecentTaskInfo extends TaskInfo implements Parcelable {
    public static final Parcelable.Creator<RecentTaskInfo> CREATOR = new Parcelable.Creator<RecentTaskInfo>() {
        public ActivityManager.RecentTaskInfo createFromParcel(Parcel param2Parcel) {
          return new ActivityManager.RecentTaskInfo(param2Parcel);
        }
        
        public ActivityManager.RecentTaskInfo[] newArray(int param2Int) {
          return new ActivityManager.RecentTaskInfo[param2Int];
        }
      };
    
    @Deprecated
    public int affiliatedTaskId;
    
    @Deprecated
    public CharSequence description;
    
    @Deprecated
    public int id;
    
    @Deprecated
    public int persistentId;
    
    public RecentTaskInfo() {}
    
    private RecentTaskInfo(Parcel param1Parcel) {
      readFromParcel(param1Parcel);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void dump(PrintWriter param1PrintWriter, String param1String) {
      boolean bool2;
      String str = WindowConfiguration.activityTypeToString(this.configuration.windowConfiguration.getActivityType());
      param1String = WindowConfiguration.activityTypeToString(this.configuration.windowConfiguration.getActivityType());
      param1PrintWriter.println();
      param1PrintWriter.print("   ");
      param1PrintWriter.print(" id=");
      param1PrintWriter.print(this.persistentId);
      param1PrintWriter.print(" stackId=");
      param1PrintWriter.print(this.stackId);
      param1PrintWriter.print(" userId=");
      param1PrintWriter.print(this.userId);
      param1PrintWriter.print(" hasTask=");
      int i = this.id;
      boolean bool1 = true;
      if (i != -1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      param1PrintWriter.print(bool2);
      param1PrintWriter.print(" lastActiveTime=");
      param1PrintWriter.println(this.lastActiveTime);
      param1PrintWriter.print("   ");
      param1PrintWriter.print(" baseIntent=");
      param1PrintWriter.println(this.baseIntent);
      if (this.baseActivity != null) {
        param1PrintWriter.print("   ");
        param1PrintWriter.print(" baseActivity=");
        param1PrintWriter.println(this.baseActivity.toShortString());
      } 
      if (this.topActivity != null) {
        param1PrintWriter.print("   ");
        param1PrintWriter.print(" topActivity=");
        param1PrintWriter.println(this.topActivity.toShortString());
      } 
      if (this.origActivity != null) {
        param1PrintWriter.print("   ");
        param1PrintWriter.print(" origActivity=");
        param1PrintWriter.println(this.origActivity.toShortString());
      } 
      if (this.realActivity != null) {
        param1PrintWriter.print("   ");
        param1PrintWriter.print(" realActivity=");
        param1PrintWriter.println(this.realActivity.toShortString());
      } 
      param1PrintWriter.print("   ");
      param1PrintWriter.print(" isExcluded=");
      if ((this.baseIntent.getFlags() & 0x800000) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      param1PrintWriter.print(bool2);
      param1PrintWriter.print(" activityType=");
      param1PrintWriter.print(str);
      param1PrintWriter.print(" windowingMode=");
      param1PrintWriter.print(param1String);
      param1PrintWriter.print(" supportsSplitScreenMultiWindow=");
      param1PrintWriter.println(this.supportsSplitScreenMultiWindow);
      if (this.taskDescription != null) {
        param1PrintWriter.print("   ");
        ActivityManager.TaskDescription taskDescription = this.taskDescription;
        param1PrintWriter.print(" taskDescription {");
        param1PrintWriter.print(" colorBackground=#");
        param1PrintWriter.print(Integer.toHexString(taskDescription.getBackgroundColor()));
        param1PrintWriter.print(" colorPrimary=#");
        param1PrintWriter.print(Integer.toHexString(taskDescription.getPrimaryColor()));
        param1PrintWriter.print(" iconRes=");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(taskDescription.getIconResourcePackage());
        stringBuilder.append("/");
        stringBuilder.append(taskDescription.getIconResource());
        param1PrintWriter.print(stringBuilder.toString());
        param1PrintWriter.print(" iconBitmap=");
        bool2 = bool1;
        if (taskDescription.getIconFilename() == null)
          if (taskDescription.getInMemoryIcon() != null) {
            bool2 = bool1;
          } else {
            bool2 = false;
          }  
        param1PrintWriter.print(bool2);
        param1PrintWriter.print(" resizeMode=");
        param1PrintWriter.print(ActivityInfo.resizeModeToString(taskDescription.getResizeMode()));
        param1PrintWriter.print(" minWidth=");
        param1PrintWriter.print(taskDescription.getMinWidth());
        param1PrintWriter.print(" minHeight=");
        param1PrintWriter.print(taskDescription.getMinHeight());
        param1PrintWriter.println(" }");
      } 
    }
    
    public void readFromParcel(Parcel param1Parcel) {
      this.id = param1Parcel.readInt();
      this.persistentId = param1Parcel.readInt();
      super.readFromParcel(param1Parcel);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.id);
      param1Parcel.writeInt(this.persistentId);
      super.writeToParcel(param1Parcel, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<RecentTaskInfo> {
    public ActivityManager.RecentTaskInfo createFromParcel(Parcel param1Parcel) {
      return new ActivityManager.RecentTaskInfo(param1Parcel);
    }
    
    public ActivityManager.RecentTaskInfo[] newArray(int param1Int) {
      return new ActivityManager.RecentTaskInfo[param1Int];
    }
  }
  
  public static class RunningAppProcessInfo implements Parcelable {
    public static final Parcelable.Creator<RunningAppProcessInfo> CREATOR = new Parcelable.Creator<RunningAppProcessInfo>() {
        public ActivityManager.RunningAppProcessInfo createFromParcel(Parcel param2Parcel) {
          return new ActivityManager.RunningAppProcessInfo(param2Parcel);
        }
        
        public ActivityManager.RunningAppProcessInfo[] newArray(int param2Int) {
          return new ActivityManager.RunningAppProcessInfo[param2Int];
        }
      };
    
    public static final int FLAG_CANT_SAVE_STATE = 1;
    
    public static final int FLAG_HAS_ACTIVITIES = 4;
    
    public static final int FLAG_PERSISTENT = 2;
    
    public static final int IMPORTANCE_BACKGROUND = 400;
    
    public static final int IMPORTANCE_CACHED = 400;
    
    public static final int IMPORTANCE_CANT_SAVE_STATE = 350;
    
    public static final int IMPORTANCE_CANT_SAVE_STATE_PRE_26 = 170;
    
    @Deprecated
    public static final int IMPORTANCE_EMPTY = 500;
    
    public static final int IMPORTANCE_FOREGROUND = 100;
    
    public static final int IMPORTANCE_FOREGROUND_SERVICE = 125;
    
    public static final int IMPORTANCE_GONE = 1000;
    
    public static final int IMPORTANCE_PERCEPTIBLE = 230;
    
    public static final int IMPORTANCE_PERCEPTIBLE_PRE_26 = 130;
    
    public static final int IMPORTANCE_SERVICE = 300;
    
    public static final int IMPORTANCE_TOP_SLEEPING = 325;
    
    @Deprecated
    public static final int IMPORTANCE_TOP_SLEEPING_PRE_28 = 150;
    
    public static final int IMPORTANCE_VISIBLE = 200;
    
    public static final int REASON_PROVIDER_IN_USE = 1;
    
    public static final int REASON_SERVICE_IN_USE = 2;
    
    public static final int REASON_UNKNOWN = 0;
    
    public int flags;
    
    public int importance;
    
    public int importanceReasonCode;
    
    public ComponentName importanceReasonComponent;
    
    public int importanceReasonImportance;
    
    public int importanceReasonPid;
    
    public boolean isFocused;
    
    public long lastActivityTime;
    
    public int lastTrimLevel;
    
    public int lru;
    
    public int pid;
    
    public String[] pkgList;
    
    public String processName;
    
    public int processState;
    
    public int uid;
    
    public RunningAppProcessInfo() {
      this.importance = 100;
      this.importanceReasonCode = 0;
      this.processState = 6;
      this.isFocused = false;
      this.lastActivityTime = 0L;
    }
    
    private RunningAppProcessInfo(Parcel param1Parcel) {
      readFromParcel(param1Parcel);
    }
    
    public RunningAppProcessInfo(String param1String, int param1Int, String[] param1ArrayOfString) {
      this.processName = param1String;
      this.pid = param1Int;
      this.pkgList = param1ArrayOfString;
      this.isFocused = false;
      this.lastActivityTime = 0L;
    }
    
    public static int importanceToProcState(int param1Int) {
      return (param1Int == 1000) ? 20 : ((param1Int >= 400) ? 14 : ((param1Int >= 350) ? 13 : ((param1Int >= 325) ? 12 : ((param1Int >= 300) ? 10 : ((param1Int >= 230) ? 8 : ((param1Int >= 200) ? 6 : ((param1Int >= 150) ? 6 : ((param1Int >= 125) ? 4 : 2))))))));
    }
    
    public static int procStateToImportance(int param1Int) {
      return (param1Int == 20) ? 1000 : ((param1Int >= 14) ? 400 : ((param1Int == 13) ? 350 : ((param1Int >= 12) ? 325 : ((param1Int >= 10) ? 300 : ((param1Int >= 8) ? 230 : ((param1Int >= 6) ? 200 : ((param1Int >= 4) ? 125 : 100)))))));
    }
    
    public static int procStateToImportanceForClient(int param1Int, Context param1Context) {
      return procStateToImportanceForTargetSdk(param1Int, (param1Context.getApplicationInfo()).targetSdkVersion);
    }
    
    public static int procStateToImportanceForTargetSdk(int param1Int1, int param1Int2) {
      param1Int1 = procStateToImportance(param1Int1);
      if (param1Int2 < 26)
        if (param1Int1 != 230) {
          if (param1Int1 != 325) {
            if (param1Int1 == 350)
              return 170; 
          } else {
            return 150;
          } 
        } else {
          return 130;
        }  
      return param1Int1;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void readFromParcel(Parcel param1Parcel) {
      boolean bool;
      this.processName = param1Parcel.readString();
      this.pid = param1Parcel.readInt();
      this.uid = param1Parcel.readInt();
      this.pkgList = param1Parcel.readStringArray();
      this.flags = param1Parcel.readInt();
      this.lastTrimLevel = param1Parcel.readInt();
      this.importance = param1Parcel.readInt();
      this.lru = param1Parcel.readInt();
      this.importanceReasonCode = param1Parcel.readInt();
      this.importanceReasonPid = param1Parcel.readInt();
      this.importanceReasonComponent = ComponentName.readFromParcel(param1Parcel);
      this.importanceReasonImportance = param1Parcel.readInt();
      this.processState = param1Parcel.readInt();
      if (param1Parcel.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.isFocused = bool;
      this.lastActivityTime = param1Parcel.readLong();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeString(this.processName);
      param1Parcel.writeInt(this.pid);
      param1Parcel.writeInt(this.uid);
      param1Parcel.writeStringArray(this.pkgList);
      param1Parcel.writeInt(this.flags);
      param1Parcel.writeInt(this.lastTrimLevel);
      param1Parcel.writeInt(this.importance);
      param1Parcel.writeInt(this.lru);
      param1Parcel.writeInt(this.importanceReasonCode);
      param1Parcel.writeInt(this.importanceReasonPid);
      ComponentName.writeToParcel(this.importanceReasonComponent, param1Parcel);
      param1Parcel.writeInt(this.importanceReasonImportance);
      param1Parcel.writeInt(this.processState);
      param1Parcel.writeInt(this.isFocused);
      param1Parcel.writeLong(this.lastActivityTime);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface Importance {}
  }
  
  class null implements Parcelable.Creator<RunningAppProcessInfo> {
    public ActivityManager.RunningAppProcessInfo createFromParcel(Parcel param1Parcel) {
      return new ActivityManager.RunningAppProcessInfo(param1Parcel);
    }
    
    public ActivityManager.RunningAppProcessInfo[] newArray(int param1Int) {
      return new ActivityManager.RunningAppProcessInfo[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Importance {}
  
  public static class RunningServiceInfo implements Parcelable {
    public static final Parcelable.Creator<RunningServiceInfo> CREATOR = new Parcelable.Creator<RunningServiceInfo>() {
        public ActivityManager.RunningServiceInfo createFromParcel(Parcel param2Parcel) {
          return new ActivityManager.RunningServiceInfo(param2Parcel);
        }
        
        public ActivityManager.RunningServiceInfo[] newArray(int param2Int) {
          return new ActivityManager.RunningServiceInfo[param2Int];
        }
      };
    
    public static final int FLAG_FOREGROUND = 2;
    
    public static final int FLAG_PERSISTENT_PROCESS = 8;
    
    public static final int FLAG_STARTED = 1;
    
    public static final int FLAG_SYSTEM_PROCESS = 4;
    
    public long activeSince;
    
    public int clientCount;
    
    public int clientLabel;
    
    public String clientPackage;
    
    public int crashCount;
    
    public int flags;
    
    public boolean foreground;
    
    public long lastActivityTime;
    
    public int pid;
    
    public String process;
    
    public long restarting;
    
    public ComponentName service;
    
    public boolean started;
    
    public int uid;
    
    public RunningServiceInfo() {}
    
    private RunningServiceInfo(Parcel param1Parcel) {
      readFromParcel(param1Parcel);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void readFromParcel(Parcel param1Parcel) {
      boolean bool2;
      this.service = ComponentName.readFromParcel(param1Parcel);
      this.pid = param1Parcel.readInt();
      this.uid = param1Parcel.readInt();
      this.process = param1Parcel.readString();
      int i = param1Parcel.readInt();
      boolean bool1 = true;
      if (i != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.foreground = bool2;
      this.activeSince = param1Parcel.readLong();
      if (param1Parcel.readInt() != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.started = bool2;
      this.clientCount = param1Parcel.readInt();
      this.crashCount = param1Parcel.readInt();
      this.lastActivityTime = param1Parcel.readLong();
      this.restarting = param1Parcel.readLong();
      this.flags = param1Parcel.readInt();
      this.clientPackage = param1Parcel.readString();
      this.clientLabel = param1Parcel.readInt();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      ComponentName.writeToParcel(this.service, param1Parcel);
      param1Parcel.writeInt(this.pid);
      param1Parcel.writeInt(this.uid);
      param1Parcel.writeString(this.process);
      param1Parcel.writeInt(this.foreground);
      param1Parcel.writeLong(this.activeSince);
      param1Parcel.writeInt(this.started);
      param1Parcel.writeInt(this.clientCount);
      param1Parcel.writeInt(this.crashCount);
      param1Parcel.writeLong(this.lastActivityTime);
      param1Parcel.writeLong(this.restarting);
      param1Parcel.writeInt(this.flags);
      param1Parcel.writeString(this.clientPackage);
      param1Parcel.writeInt(this.clientLabel);
    }
  }
  
  class null implements Parcelable.Creator<RunningServiceInfo> {
    public ActivityManager.RunningServiceInfo createFromParcel(Parcel param1Parcel) {
      return new ActivityManager.RunningServiceInfo(param1Parcel);
    }
    
    public ActivityManager.RunningServiceInfo[] newArray(int param1Int) {
      return new ActivityManager.RunningServiceInfo[param1Int];
    }
  }
  
  public static class RunningTaskInfo extends TaskInfo implements Parcelable {
    public static final Parcelable.Creator<RunningTaskInfo> CREATOR = new Parcelable.Creator<RunningTaskInfo>() {
        public ActivityManager.RunningTaskInfo createFromParcel(Parcel param2Parcel) {
          return new ActivityManager.RunningTaskInfo(param2Parcel);
        }
        
        public ActivityManager.RunningTaskInfo[] newArray(int param2Int) {
          return new ActivityManager.RunningTaskInfo[param2Int];
        }
      };
    
    @Deprecated
    public CharSequence description;
    
    @Deprecated
    public int id;
    
    @Deprecated
    public int numRunning;
    
    @Deprecated
    public Bitmap thumbnail;
    
    public RunningTaskInfo() {}
    
    private RunningTaskInfo(Parcel param1Parcel) {
      readFromParcel(param1Parcel);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void readFromParcel(Parcel param1Parcel) {
      this.id = param1Parcel.readInt();
      super.readFromParcel(param1Parcel);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.id);
      super.writeToParcel(param1Parcel, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<RunningTaskInfo> {
    public ActivityManager.RunningTaskInfo createFromParcel(Parcel param1Parcel) {
      return new ActivityManager.RunningTaskInfo(param1Parcel);
    }
    
    public ActivityManager.RunningTaskInfo[] newArray(int param1Int) {
      return new ActivityManager.RunningTaskInfo[param1Int];
    }
  }
  
  public static class StackInfo implements Parcelable {
    public static final Parcelable.Creator<StackInfo> CREATOR = new Parcelable.Creator<StackInfo>() {
        public ActivityManager.StackInfo createFromParcel(Parcel param2Parcel) {
          return new ActivityManager.StackInfo(param2Parcel);
        }
        
        public ActivityManager.StackInfo[] newArray(int param2Int) {
          return new ActivityManager.StackInfo[param2Int];
        }
      };
    
    public Rect bounds = new Rect();
    
    public final Configuration configuration = new Configuration();
    
    public int displayId;
    
    @Deprecated
    public int position;
    
    public int stackId;
    
    public WindowContainerToken stackToken;
    
    public Rect[] taskBounds;
    
    public int[] taskIds;
    
    public String[] taskNames;
    
    public int[] taskUserIds;
    
    public ComponentName topActivity;
    
    public int userId;
    
    public boolean visible;
    
    public StackInfo() {}
    
    private StackInfo(Parcel param1Parcel) {
      readFromParcel(param1Parcel);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void readFromParcel(Parcel param1Parcel) {
      boolean bool;
      this.stackId = param1Parcel.readInt();
      this.bounds = new Rect(param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt());
      this.taskIds = param1Parcel.createIntArray();
      this.taskNames = param1Parcel.createStringArray();
      int i = param1Parcel.readInt();
      if (i > 0) {
        this.taskBounds = new Rect[i];
        for (byte b = 0; b < i; b++) {
          this.taskBounds[b] = new Rect();
          this.taskBounds[b].set(param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt());
        } 
      } else {
        this.taskBounds = null;
      } 
      this.taskUserIds = param1Parcel.createIntArray();
      this.displayId = param1Parcel.readInt();
      this.userId = param1Parcel.readInt();
      if (param1Parcel.readInt() > 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.visible = bool;
      this.position = param1Parcel.readInt();
      this.stackToken = (WindowContainerToken)WindowContainerToken.CREATOR.createFromParcel(param1Parcel);
      if (param1Parcel.readInt() > 0)
        this.topActivity = ComponentName.readFromParcel(param1Parcel); 
      this.configuration.readFromParcel(param1Parcel);
    }
    
    public String toString() {
      return toString("");
    }
    
    public String toString(String param1String) {
      StringBuilder stringBuilder1 = new StringBuilder(256);
      stringBuilder1.append(param1String);
      stringBuilder1.append("Stack id=");
      stringBuilder1.append(this.stackId);
      stringBuilder1.append(" bounds=");
      stringBuilder1.append(this.bounds.toShortString());
      stringBuilder1.append(" displayId=");
      stringBuilder1.append(this.displayId);
      stringBuilder1.append(" userId=");
      stringBuilder1.append(this.userId);
      stringBuilder1.append("\n");
      stringBuilder1.append(" configuration=");
      stringBuilder1.append(this.configuration);
      stringBuilder1.append("\n");
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(param1String);
      stringBuilder2.append("  ");
      param1String = stringBuilder2.toString();
      for (byte b = 0; b < this.taskIds.length; b++) {
        stringBuilder1.append(param1String);
        stringBuilder1.append("taskId=");
        stringBuilder1.append(this.taskIds[b]);
        stringBuilder1.append(": ");
        stringBuilder1.append(this.taskNames[b]);
        if (this.taskBounds != null) {
          stringBuilder1.append(" bounds=");
          stringBuilder1.append(this.taskBounds[b].toShortString());
        } 
        stringBuilder1.append(" userId=");
        stringBuilder1.append(this.taskUserIds[b]);
        stringBuilder1.append(" visible=");
        stringBuilder1.append(this.visible);
        if (this.topActivity != null) {
          stringBuilder1.append(" topActivity=");
          stringBuilder1.append(this.topActivity);
        } 
        stringBuilder1.append("\n");
      } 
      return stringBuilder1.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      int i;
      param1Parcel.writeInt(this.stackId);
      param1Parcel.writeInt(this.bounds.left);
      param1Parcel.writeInt(this.bounds.top);
      param1Parcel.writeInt(this.bounds.right);
      param1Parcel.writeInt(this.bounds.bottom);
      param1Parcel.writeIntArray(this.taskIds);
      param1Parcel.writeStringArray(this.taskNames);
      Rect[] arrayOfRect = this.taskBounds;
      if (arrayOfRect == null) {
        i = 0;
      } else {
        i = arrayOfRect.length;
      } 
      param1Parcel.writeInt(i);
      for (byte b = 0; b < i; b++) {
        param1Parcel.writeInt((this.taskBounds[b]).left);
        param1Parcel.writeInt((this.taskBounds[b]).top);
        param1Parcel.writeInt((this.taskBounds[b]).right);
        param1Parcel.writeInt((this.taskBounds[b]).bottom);
      } 
      param1Parcel.writeIntArray(this.taskUserIds);
      param1Parcel.writeInt(this.displayId);
      param1Parcel.writeInt(this.userId);
      param1Parcel.writeInt(this.visible);
      param1Parcel.writeInt(this.position);
      this.stackToken.writeToParcel(param1Parcel, 0);
      if (this.topActivity != null) {
        param1Parcel.writeInt(1);
        this.topActivity.writeToParcel(param1Parcel, 0);
      } else {
        param1Parcel.writeInt(0);
      } 
      this.configuration.writeToParcel(param1Parcel, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<StackInfo> {
    public ActivityManager.StackInfo createFromParcel(Parcel param1Parcel) {
      return new ActivityManager.StackInfo(param1Parcel);
    }
    
    public ActivityManager.StackInfo[] newArray(int param1Int) {
      return new ActivityManager.StackInfo[param1Int];
    }
  }
  
  public static class TaskDescription implements Parcelable {
    private static final String ATTR_TASKDESCRIPTIONCOLOR_BACKGROUND = "task_description_colorBackground";
    
    private static final String ATTR_TASKDESCRIPTIONCOLOR_PRIMARY = "task_description_color";
    
    private static final String ATTR_TASKDESCRIPTIONICON_FILENAME = "task_description_icon_filename";
    
    private static final String ATTR_TASKDESCRIPTIONICON_RESOURCE = "task_description_icon_resource";
    
    private static final String ATTR_TASKDESCRIPTIONICON_RESOURCE_PACKAGE = "task_description_icon_package";
    
    private static final String ATTR_TASKDESCRIPTIONLABEL = "task_description_label";
    
    public static final String ATTR_TASKDESCRIPTION_PREFIX = "task_description_";
    
    public static final Parcelable.Creator<TaskDescription> CREATOR = new Parcelable.Creator<TaskDescription>() {
        public ActivityManager.TaskDescription createFromParcel(Parcel param2Parcel) {
          return new ActivityManager.TaskDescription(param2Parcel);
        }
        
        public ActivityManager.TaskDescription[] newArray(int param2Int) {
          return new ActivityManager.TaskDescription[param2Int];
        }
      };
    
    private int mColorBackground;
    
    private int mColorPrimary;
    
    private boolean mEnsureNavigationBarContrastWhenTransparent;
    
    private boolean mEnsureStatusBarContrastWhenTransparent;
    
    private Icon mIcon;
    
    private String mIconFilename;
    
    private String mLabel;
    
    private int mMinHeight;
    
    private int mMinWidth;
    
    private int mNavigationBarColor;
    
    private int mResizeMode;
    
    private int mStatusBarColor;
    
    public TaskDescription() {
      this(null, null, 0, 0, 0, 0, false, false, 2, -1, -1);
    }
    
    public TaskDescription(TaskDescription param1TaskDescription) {
      copyFrom(param1TaskDescription);
    }
    
    private TaskDescription(Parcel param1Parcel) {
      readFromParcel(param1Parcel);
    }
    
    public TaskDescription(String param1String) {
      this(param1String, null, 0, 0, 0, 0, false, false, 2, -1, -1);
    }
    
    public TaskDescription(String param1String, int param1Int) {
      this(param1String, Icon.createWithResource(ActivityThread.currentPackageName(), param1Int), 0, 0, 0, 0, false, false, 2, -1, -1);
    }
    
    public TaskDescription(String param1String, int param1Int1, int param1Int2) {
      this(param1String, Icon.createWithResource(ActivityThread.currentPackageName(), param1Int1), param1Int2, 0, 0, 0, false, false, 2, -1, -1);
      if (param1Int2 == 0 || Color.alpha(param1Int2) == 255)
        return; 
      throw new RuntimeException("A TaskDescription's primary color should be opaque");
    }
    
    @Deprecated
    public TaskDescription(String param1String, Bitmap param1Bitmap) {
      this(param1String, (Icon)param1Bitmap, 0, 0, 0, 0, false, false, 2, -1, -1);
    }
    
    @Deprecated
    public TaskDescription(String param1String, Bitmap param1Bitmap, int param1Int) {
      this(param1String, (Icon)param1Bitmap, param1Int, 0, 0, 0, false, false, 2, -1, -1);
      if (param1Int == 0 || Color.alpha(param1Int) == 255)
        return; 
      throw new RuntimeException("A TaskDescription's primary color should be opaque");
    }
    
    public TaskDescription(String param1String, Icon param1Icon, int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean1, boolean param1Boolean2, int param1Int5, int param1Int6, int param1Int7) {
      this.mLabel = param1String;
      this.mIcon = param1Icon;
      this.mColorPrimary = param1Int1;
      this.mColorBackground = param1Int2;
      this.mStatusBarColor = param1Int3;
      this.mNavigationBarColor = param1Int4;
      this.mEnsureStatusBarContrastWhenTransparent = param1Boolean1;
      this.mEnsureNavigationBarContrastWhenTransparent = param1Boolean2;
      this.mResizeMode = param1Int5;
      this.mMinWidth = param1Int6;
      this.mMinHeight = param1Int7;
    }
    
    public static boolean equals(TaskDescription param1TaskDescription1, TaskDescription param1TaskDescription2) {
      return (param1TaskDescription1 == null && param1TaskDescription2 == null) ? true : ((param1TaskDescription1 != null && param1TaskDescription2 != null) ? param1TaskDescription1.equals(param1TaskDescription2) : false);
    }
    
    public static Bitmap loadTaskDescriptionIcon(String param1String, int param1Int) {
      if (param1String != null)
        try {
          return ActivityManager.getTaskService().getTaskDescriptionIcon(param1String, param1Int);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return null;
    }
    
    public void copyFrom(TaskDescription param1TaskDescription) {
      this.mLabel = param1TaskDescription.mLabel;
      this.mIcon = param1TaskDescription.mIcon;
      this.mIconFilename = param1TaskDescription.mIconFilename;
      this.mColorPrimary = param1TaskDescription.mColorPrimary;
      this.mColorBackground = param1TaskDescription.mColorBackground;
      this.mStatusBarColor = param1TaskDescription.mStatusBarColor;
      this.mNavigationBarColor = param1TaskDescription.mNavigationBarColor;
      this.mEnsureStatusBarContrastWhenTransparent = param1TaskDescription.mEnsureStatusBarContrastWhenTransparent;
      this.mEnsureNavigationBarContrastWhenTransparent = param1TaskDescription.mEnsureNavigationBarContrastWhenTransparent;
      this.mResizeMode = param1TaskDescription.mResizeMode;
      this.mMinWidth = param1TaskDescription.mMinWidth;
      this.mMinHeight = param1TaskDescription.mMinHeight;
    }
    
    public void copyFromPreserveHiddenFields(TaskDescription param1TaskDescription) {
      this.mLabel = param1TaskDescription.mLabel;
      this.mIcon = param1TaskDescription.mIcon;
      this.mIconFilename = param1TaskDescription.mIconFilename;
      this.mColorPrimary = param1TaskDescription.mColorPrimary;
      int i = param1TaskDescription.mColorBackground;
      if (i != 0)
        this.mColorBackground = i; 
      i = param1TaskDescription.mStatusBarColor;
      if (i != 0)
        this.mStatusBarColor = i; 
      i = param1TaskDescription.mNavigationBarColor;
      if (i != 0)
        this.mNavigationBarColor = i; 
      this.mEnsureStatusBarContrastWhenTransparent = param1TaskDescription.mEnsureStatusBarContrastWhenTransparent;
      this.mEnsureNavigationBarContrastWhenTransparent = param1TaskDescription.mEnsureNavigationBarContrastWhenTransparent;
      i = param1TaskDescription.mResizeMode;
      if (i != 2)
        this.mResizeMode = i; 
      i = param1TaskDescription.mMinWidth;
      if (i != -1)
        this.mMinWidth = i; 
      i = param1TaskDescription.mMinHeight;
      if (i != -1)
        this.mMinHeight = i; 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = param1Object instanceof TaskDescription;
      boolean bool1 = false;
      if (!bool)
        return false; 
      param1Object = param1Object;
      if (TextUtils.equals(this.mLabel, ((TaskDescription)param1Object).mLabel) && TextUtils.equals(this.mIconFilename, ((TaskDescription)param1Object).mIconFilename) && this.mIcon == ((TaskDescription)param1Object).mIcon && this.mColorPrimary == ((TaskDescription)param1Object).mColorPrimary && this.mColorBackground == ((TaskDescription)param1Object).mColorBackground && this.mStatusBarColor == ((TaskDescription)param1Object).mStatusBarColor && this.mNavigationBarColor == ((TaskDescription)param1Object).mNavigationBarColor && this.mEnsureStatusBarContrastWhenTransparent == ((TaskDescription)param1Object).mEnsureStatusBarContrastWhenTransparent && this.mEnsureNavigationBarContrastWhenTransparent == ((TaskDescription)param1Object).mEnsureNavigationBarContrastWhenTransparent && this.mResizeMode == ((TaskDescription)param1Object).mResizeMode && this.mMinWidth == ((TaskDescription)param1Object).mMinWidth && this.mMinHeight == ((TaskDescription)param1Object).mMinHeight)
        bool1 = true; 
      return bool1;
    }
    
    public int getBackgroundColor() {
      return this.mColorBackground;
    }
    
    public boolean getEnsureNavigationBarContrastWhenTransparent() {
      return this.mEnsureNavigationBarContrastWhenTransparent;
    }
    
    public boolean getEnsureStatusBarContrastWhenTransparent() {
      return this.mEnsureStatusBarContrastWhenTransparent;
    }
    
    @Deprecated
    public Bitmap getIcon() {
      Bitmap bitmap = getInMemoryIcon();
      return (bitmap != null) ? bitmap : loadTaskDescriptionIcon(this.mIconFilename, UserHandle.myUserId());
    }
    
    public String getIconFilename() {
      return this.mIconFilename;
    }
    
    public int getIconResource() {
      Icon icon = this.mIcon;
      return (icon != null && icon.getType() == 2) ? this.mIcon.getResId() : 0;
    }
    
    public String getIconResourcePackage() {
      Icon icon = this.mIcon;
      return (icon != null && icon.getType() == 2) ? this.mIcon.getResPackage() : "";
    }
    
    public Bitmap getInMemoryIcon() {
      Icon icon = this.mIcon;
      return (icon != null && icon.getType() == 1) ? this.mIcon.getBitmap() : null;
    }
    
    public String getLabel() {
      return this.mLabel;
    }
    
    public int getMinHeight() {
      return this.mMinHeight;
    }
    
    public int getMinWidth() {
      return this.mMinWidth;
    }
    
    public int getNavigationBarColor() {
      return this.mNavigationBarColor;
    }
    
    public int getPrimaryColor() {
      return this.mColorPrimary;
    }
    
    public Icon getRawIcon() {
      return this.mIcon;
    }
    
    public int getResizeMode() {
      return this.mResizeMode;
    }
    
    public int getStatusBarColor() {
      return this.mStatusBarColor;
    }
    
    public Icon loadIcon() {
      Icon icon = this.mIcon;
      if (icon != null)
        return icon; 
      Bitmap bitmap = loadTaskDescriptionIcon(this.mIconFilename, UserHandle.myUserId());
      return (bitmap != null) ? Icon.createWithBitmap(bitmap) : null;
    }
    
    public void readFromParcel(Parcel param1Parcel) {
      int i = param1Parcel.readInt();
      String str1 = null;
      if (i > 0) {
        str2 = param1Parcel.readString();
      } else {
        str2 = null;
      } 
      this.mLabel = str2;
      if (param1Parcel.readInt() > 0)
        this.mIcon = (Icon)Icon.CREATOR.createFromParcel(param1Parcel); 
      this.mColorPrimary = param1Parcel.readInt();
      this.mColorBackground = param1Parcel.readInt();
      this.mStatusBarColor = param1Parcel.readInt();
      this.mNavigationBarColor = param1Parcel.readInt();
      this.mEnsureStatusBarContrastWhenTransparent = param1Parcel.readBoolean();
      this.mEnsureNavigationBarContrastWhenTransparent = param1Parcel.readBoolean();
      this.mResizeMode = param1Parcel.readInt();
      this.mMinWidth = param1Parcel.readInt();
      this.mMinHeight = param1Parcel.readInt();
      String str2 = str1;
      if (param1Parcel.readInt() > 0)
        str2 = param1Parcel.readString(); 
      this.mIconFilename = str2;
    }
    
    public void restoreFromXml(XmlPullParser param1XmlPullParser) {
      String str2 = param1XmlPullParser.getAttributeValue(null, "task_description_label");
      if (str2 != null)
        setLabel(str2); 
      str2 = param1XmlPullParser.getAttributeValue(null, "task_description_color");
      if (str2 != null)
        setPrimaryColor((int)Long.parseLong(str2, 16)); 
      str2 = param1XmlPullParser.getAttributeValue(null, "task_description_colorBackground");
      if (str2 != null)
        setBackgroundColor((int)Long.parseLong(str2, 16)); 
      str2 = param1XmlPullParser.getAttributeValue(null, "task_description_icon_filename");
      if (str2 != null)
        setIconFilename(str2); 
      str2 = param1XmlPullParser.getAttributeValue(null, "task_description_icon_resource");
      String str1 = param1XmlPullParser.getAttributeValue(null, "task_description_icon_package");
      if (str2 != null && str1 != null)
        setIcon(Icon.createWithResource(str1, Integer.parseInt(str2, 10))); 
    }
    
    public void saveToXml(XmlSerializer param1XmlSerializer) throws IOException {
      String str = this.mLabel;
      if (str != null)
        param1XmlSerializer.attribute(null, "task_description_label", str); 
      int i = this.mColorPrimary;
      if (i != 0)
        param1XmlSerializer.attribute(null, "task_description_color", Integer.toHexString(i)); 
      i = this.mColorBackground;
      if (i != 0)
        param1XmlSerializer.attribute(null, "task_description_colorBackground", Integer.toHexString(i)); 
      str = this.mIconFilename;
      if (str != null)
        param1XmlSerializer.attribute(null, "task_description_icon_filename", str); 
      Icon icon = this.mIcon;
      if (icon != null && icon.getType() == 2) {
        param1XmlSerializer.attribute(null, "task_description_icon_resource", Integer.toString(this.mIcon.getResId()));
        param1XmlSerializer.attribute(null, "task_description_icon_package", this.mIcon.getResPackage());
      } 
    }
    
    public void setBackgroundColor(int param1Int) {
      if (param1Int == 0 || Color.alpha(param1Int) == 255) {
        this.mColorBackground = param1Int;
        return;
      } 
      throw new RuntimeException("A TaskDescription's background color should be opaque");
    }
    
    public void setEnsureNavigationBarContrastWhenTransparent(boolean param1Boolean) {
      this.mEnsureNavigationBarContrastWhenTransparent = param1Boolean;
    }
    
    public void setEnsureStatusBarContrastWhenTransparent(boolean param1Boolean) {
      this.mEnsureStatusBarContrastWhenTransparent = param1Boolean;
    }
    
    public void setIcon(Icon param1Icon) {
      this.mIcon = param1Icon;
    }
    
    public void setIconFilename(String param1String) {
      this.mIconFilename = param1String;
      if (param1String != null)
        this.mIcon = null; 
    }
    
    public void setLabel(String param1String) {
      this.mLabel = param1String;
    }
    
    public void setMinHeight(int param1Int) {
      this.mMinHeight = param1Int;
    }
    
    public void setMinWidth(int param1Int) {
      this.mMinWidth = param1Int;
    }
    
    public void setNavigationBarColor(int param1Int) {
      this.mNavigationBarColor = param1Int;
    }
    
    public void setPrimaryColor(int param1Int) {
      if (param1Int == 0 || Color.alpha(param1Int) == 255) {
        this.mColorPrimary = param1Int;
        return;
      } 
      throw new RuntimeException("A TaskDescription's primary color should be opaque");
    }
    
    public void setResizeMode(int param1Int) {
      this.mResizeMode = param1Int;
    }
    
    public void setStatusBarColor(int param1Int) {
      this.mStatusBarColor = param1Int;
    }
    
    public String toString() {
      String str2;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("TaskDescription Label: ");
      stringBuilder.append(this.mLabel);
      stringBuilder.append(" Icon: ");
      stringBuilder.append(this.mIcon);
      stringBuilder.append(" IconFilename: ");
      stringBuilder.append(this.mIconFilename);
      stringBuilder.append(" colorPrimary: ");
      stringBuilder.append(this.mColorPrimary);
      stringBuilder.append(" colorBackground: ");
      stringBuilder.append(this.mColorBackground);
      stringBuilder.append(" statusBarColor: ");
      stringBuilder.append(this.mStatusBarColor);
      boolean bool = this.mEnsureStatusBarContrastWhenTransparent;
      String str1 = " (contrast when transparent)";
      if (bool) {
        str2 = " (contrast when transparent)";
      } else {
        str2 = "";
      } 
      stringBuilder.append(str2);
      stringBuilder.append(" navigationBarColor: ");
      stringBuilder.append(this.mNavigationBarColor);
      if (this.mEnsureNavigationBarContrastWhenTransparent) {
        str2 = str1;
      } else {
        str2 = "";
      } 
      stringBuilder.append(str2);
      stringBuilder.append(" resizeMode: ");
      stringBuilder.append(ActivityInfo.resizeModeToString(this.mResizeMode));
      stringBuilder.append(" minWidth: ");
      stringBuilder.append(this.mMinWidth);
      stringBuilder.append(" minHeight: ");
      stringBuilder.append(this.mMinHeight);
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      if (this.mLabel == null) {
        param1Parcel.writeInt(0);
      } else {
        param1Parcel.writeInt(1);
        param1Parcel.writeString(this.mLabel);
      } 
      Bitmap bitmap = getInMemoryIcon();
      if (this.mIcon == null || (bitmap != null && bitmap.isRecycled())) {
        param1Parcel.writeInt(0);
      } else {
        param1Parcel.writeInt(1);
        this.mIcon.writeToParcel(param1Parcel, 0);
      } 
      param1Parcel.writeInt(this.mColorPrimary);
      param1Parcel.writeInt(this.mColorBackground);
      param1Parcel.writeInt(this.mStatusBarColor);
      param1Parcel.writeInt(this.mNavigationBarColor);
      param1Parcel.writeBoolean(this.mEnsureStatusBarContrastWhenTransparent);
      param1Parcel.writeBoolean(this.mEnsureNavigationBarContrastWhenTransparent);
      param1Parcel.writeInt(this.mResizeMode);
      param1Parcel.writeInt(this.mMinWidth);
      param1Parcel.writeInt(this.mMinHeight);
      if (this.mIconFilename == null) {
        param1Parcel.writeInt(0);
      } else {
        param1Parcel.writeInt(1);
        param1Parcel.writeString(this.mIconFilename);
      } 
    }
  }
  
  class null implements Parcelable.Creator<TaskDescription> {
    public ActivityManager.TaskDescription createFromParcel(Parcel param1Parcel) {
      return new ActivityManager.TaskDescription(param1Parcel);
    }
    
    public ActivityManager.TaskDescription[] newArray(int param1Int) {
      return new ActivityManager.TaskDescription[param1Int];
    }
  }
  
  public static class TaskSnapshot implements Parcelable {
    public static final Parcelable.Creator<TaskSnapshot> CREATOR = new Parcelable.Creator<TaskSnapshot>() {
        public ActivityManager.TaskSnapshot createFromParcel(Parcel param2Parcel) {
          return new ActivityManager.TaskSnapshot(param2Parcel);
        }
        
        public ActivityManager.TaskSnapshot[] newArray(int param2Int) {
          return new ActivityManager.TaskSnapshot[param2Int];
        }
      };
    
    private final ColorSpace mColorSpace;
    
    private final Rect mContentInsets;
    
    private final long mId;
    
    private final boolean mIsLowResolution;
    
    private final boolean mIsRealSnapshot;
    
    private final boolean mIsTranslucent;
    
    private final int mOrientation;
    
    private int mRotation;
    
    private final GraphicBuffer mSnapshot;
    
    private final int mSystemUiVisibility;
    
    private final Point mTaskSize;
    
    private final ComponentName mTopActivityComponent;
    
    private final int mWindowingMode;
    
    public TaskSnapshot(long param1Long, ComponentName param1ComponentName, GraphicBuffer param1GraphicBuffer, ColorSpace param1ColorSpace, int param1Int1, int param1Int2, Point param1Point, Rect param1Rect, boolean param1Boolean1, boolean param1Boolean2, int param1Int3, int param1Int4, boolean param1Boolean3) {
      ColorSpace colorSpace;
      this.mId = param1Long;
      this.mTopActivityComponent = param1ComponentName;
      this.mSnapshot = param1GraphicBuffer;
      if (param1ColorSpace.getId() < 0) {
        colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
      } else {
        colorSpace = param1ColorSpace;
      } 
      this.mColorSpace = colorSpace;
      this.mOrientation = param1Int1;
      this.mRotation = param1Int2;
      this.mTaskSize = new Point(param1Point);
      this.mContentInsets = new Rect(param1Rect);
      this.mIsLowResolution = param1Boolean1;
      this.mIsRealSnapshot = param1Boolean2;
      this.mWindowingMode = param1Int3;
      this.mSystemUiVisibility = param1Int4;
      this.mIsTranslucent = param1Boolean3;
    }
    
    private TaskSnapshot(Parcel param1Parcel) {
      ColorSpace colorSpace;
      this.mId = param1Parcel.readLong();
      this.mTopActivityComponent = ComponentName.readFromParcel(param1Parcel);
      this.mSnapshot = (GraphicBuffer)param1Parcel.readParcelable(null);
      int i = param1Parcel.readInt();
      if (i >= 0 && i < (ColorSpace.Named.values()).length) {
        colorSpace = ColorSpace.get(ColorSpace.Named.values()[i]);
      } else {
        colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
      } 
      this.mColorSpace = colorSpace;
      this.mOrientation = param1Parcel.readInt();
      this.mRotation = param1Parcel.readInt();
      this.mTaskSize = (Point)param1Parcel.readParcelable(null);
      this.mContentInsets = (Rect)param1Parcel.readParcelable(null);
      this.mIsLowResolution = param1Parcel.readBoolean();
      this.mIsRealSnapshot = param1Parcel.readBoolean();
      this.mWindowingMode = param1Parcel.readInt();
      this.mSystemUiVisibility = param1Parcel.readInt();
      this.mIsTranslucent = param1Parcel.readBoolean();
    }
    
    public int describeContents() {
      return 0;
    }
    
    public ColorSpace getColorSpace() {
      return this.mColorSpace;
    }
    
    public Rect getContentInsets() {
      return this.mContentInsets;
    }
    
    public long getId() {
      return this.mId;
    }
    
    public int getOrientation() {
      return this.mOrientation;
    }
    
    public int getRotation() {
      return this.mRotation;
    }
    
    public GraphicBuffer getSnapshot() {
      return this.mSnapshot;
    }
    
    public int getSystemUiVisibility() {
      return this.mSystemUiVisibility;
    }
    
    public Point getTaskSize() {
      return this.mTaskSize;
    }
    
    public ComponentName getTopActivityComponent() {
      return this.mTopActivityComponent;
    }
    
    public int getWindowingMode() {
      return this.mWindowingMode;
    }
    
    public boolean isLowResolution() {
      return this.mIsLowResolution;
    }
    
    public boolean isRealSnapshot() {
      return this.mIsRealSnapshot;
    }
    
    public boolean isTranslucent() {
      return this.mIsTranslucent;
    }
    
    public String toString() {
      boolean bool;
      GraphicBuffer graphicBuffer = this.mSnapshot;
      int i = 0;
      if (graphicBuffer != null) {
        bool = graphicBuffer.getWidth();
      } else {
        bool = false;
      } 
      graphicBuffer = this.mSnapshot;
      if (graphicBuffer != null)
        i = graphicBuffer.getHeight(); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("TaskSnapshot{ mId=");
      stringBuilder.append(this.mId);
      stringBuilder.append(" mTopActivityComponent=");
      stringBuilder.append(this.mTopActivityComponent.flattenToShortString());
      stringBuilder.append(" mSnapshot=");
      stringBuilder.append(this.mSnapshot);
      stringBuilder.append(" (");
      stringBuilder.append(bool);
      stringBuilder.append("x");
      stringBuilder.append(i);
      stringBuilder.append(") mColorSpace=");
      stringBuilder.append(this.mColorSpace.toString());
      stringBuilder.append(" mOrientation=");
      stringBuilder.append(this.mOrientation);
      stringBuilder.append(" mRotation=");
      stringBuilder.append(this.mRotation);
      stringBuilder.append(" mTaskSize=");
      stringBuilder.append(this.mTaskSize.toString());
      stringBuilder.append(" mContentInsets=");
      stringBuilder.append(this.mContentInsets.toShortString());
      stringBuilder.append(" mIsLowResolution=");
      stringBuilder.append(this.mIsLowResolution);
      stringBuilder.append(" mIsRealSnapshot=");
      stringBuilder.append(this.mIsRealSnapshot);
      stringBuilder.append(" mWindowingMode=");
      stringBuilder.append(this.mWindowingMode);
      stringBuilder.append(" mSystemUiVisibility=");
      stringBuilder.append(this.mSystemUiVisibility);
      stringBuilder.append(" mIsTranslucent=");
      stringBuilder.append(this.mIsTranslucent);
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeLong(this.mId);
      ComponentName.writeToParcel(this.mTopActivityComponent, param1Parcel);
      GraphicBuffer graphicBuffer = this.mSnapshot;
      if (graphicBuffer != null && !graphicBuffer.isDestroyed()) {
        graphicBuffer = this.mSnapshot;
      } else {
        graphicBuffer = null;
      } 
      param1Parcel.writeParcelable((Parcelable)graphicBuffer, 0);
      param1Parcel.writeInt(this.mColorSpace.getId());
      param1Parcel.writeInt(this.mOrientation);
      param1Parcel.writeInt(this.mRotation);
      param1Parcel.writeParcelable((Parcelable)this.mTaskSize, 0);
      param1Parcel.writeParcelable((Parcelable)this.mContentInsets, 0);
      param1Parcel.writeBoolean(this.mIsLowResolution);
      param1Parcel.writeBoolean(this.mIsRealSnapshot);
      param1Parcel.writeInt(this.mWindowingMode);
      param1Parcel.writeInt(this.mSystemUiVisibility);
      param1Parcel.writeBoolean(this.mIsTranslucent);
    }
    
    public static final class Builder {
      private ColorSpace mColorSpace;
      
      private Rect mContentInsets;
      
      private long mId;
      
      private boolean mIsRealSnapshot;
      
      private boolean mIsTranslucent;
      
      private int mOrientation;
      
      private int mPixelFormat;
      
      private int mRotation;
      
      private GraphicBuffer mSnapshot;
      
      private int mSystemUiVisibility;
      
      private Point mTaskSize;
      
      private ComponentName mTopActivity;
      
      private int mWindowingMode;
      
      public ActivityManager.TaskSnapshot build() {
        return new ActivityManager.TaskSnapshot(this.mId, this.mTopActivity, this.mSnapshot, this.mColorSpace, this.mOrientation, this.mRotation, this.mTaskSize, this.mContentInsets, false, this.mIsRealSnapshot, this.mWindowingMode, this.mSystemUiVisibility, this.mIsTranslucent);
      }
      
      public int getPixelFormat() {
        return this.mPixelFormat;
      }
      
      public Builder setColorSpace(ColorSpace param2ColorSpace) {
        this.mColorSpace = param2ColorSpace;
        return this;
      }
      
      public Builder setContentInsets(Rect param2Rect) {
        this.mContentInsets = param2Rect;
        return this;
      }
      
      public Builder setId(long param2Long) {
        this.mId = param2Long;
        return this;
      }
      
      public Builder setIsRealSnapshot(boolean param2Boolean) {
        this.mIsRealSnapshot = param2Boolean;
        return this;
      }
      
      public Builder setIsTranslucent(boolean param2Boolean) {
        this.mIsTranslucent = param2Boolean;
        return this;
      }
      
      public Builder setOrientation(int param2Int) {
        this.mOrientation = param2Int;
        return this;
      }
      
      public Builder setPixelFormat(int param2Int) {
        this.mPixelFormat = param2Int;
        return this;
      }
      
      public Builder setRotation(int param2Int) {
        this.mRotation = param2Int;
        return this;
      }
      
      public Builder setSnapshot(GraphicBuffer param2GraphicBuffer) {
        this.mSnapshot = param2GraphicBuffer;
        return this;
      }
      
      public Builder setSystemUiVisibility(int param2Int) {
        this.mSystemUiVisibility = param2Int;
        return this;
      }
      
      public Builder setTaskSize(Point param2Point) {
        this.mTaskSize = param2Point;
        return this;
      }
      
      public Builder setTopActivityComponent(ComponentName param2ComponentName) {
        this.mTopActivity = param2ComponentName;
        return this;
      }
      
      public Builder setWindowingMode(int param2Int) {
        this.mWindowingMode = param2Int;
        return this;
      }
    }
  }
  
  class null implements Parcelable.Creator<TaskSnapshot> {
    public ActivityManager.TaskSnapshot createFromParcel(Parcel param1Parcel) {
      return new ActivityManager.TaskSnapshot(param1Parcel);
    }
    
    public ActivityManager.TaskSnapshot[] newArray(int param1Int) {
      return new ActivityManager.TaskSnapshot[param1Int];
    }
  }
  
  public static final class Builder {
    private ColorSpace mColorSpace;
    
    private Rect mContentInsets;
    
    private long mId;
    
    private boolean mIsRealSnapshot;
    
    private boolean mIsTranslucent;
    
    private int mOrientation;
    
    private int mPixelFormat;
    
    private int mRotation;
    
    private GraphicBuffer mSnapshot;
    
    private int mSystemUiVisibility;
    
    private Point mTaskSize;
    
    private ComponentName mTopActivity;
    
    private int mWindowingMode;
    
    public ActivityManager.TaskSnapshot build() {
      return new ActivityManager.TaskSnapshot(this.mId, this.mTopActivity, this.mSnapshot, this.mColorSpace, this.mOrientation, this.mRotation, this.mTaskSize, this.mContentInsets, false, this.mIsRealSnapshot, this.mWindowingMode, this.mSystemUiVisibility, this.mIsTranslucent);
    }
    
    public int getPixelFormat() {
      return this.mPixelFormat;
    }
    
    public Builder setColorSpace(ColorSpace param1ColorSpace) {
      this.mColorSpace = param1ColorSpace;
      return this;
    }
    
    public Builder setContentInsets(Rect param1Rect) {
      this.mContentInsets = param1Rect;
      return this;
    }
    
    public Builder setId(long param1Long) {
      this.mId = param1Long;
      return this;
    }
    
    public Builder setIsRealSnapshot(boolean param1Boolean) {
      this.mIsRealSnapshot = param1Boolean;
      return this;
    }
    
    public Builder setIsTranslucent(boolean param1Boolean) {
      this.mIsTranslucent = param1Boolean;
      return this;
    }
    
    public Builder setOrientation(int param1Int) {
      this.mOrientation = param1Int;
      return this;
    }
    
    public Builder setPixelFormat(int param1Int) {
      this.mPixelFormat = param1Int;
      return this;
    }
    
    public Builder setRotation(int param1Int) {
      this.mRotation = param1Int;
      return this;
    }
    
    public Builder setSnapshot(GraphicBuffer param1GraphicBuffer) {
      this.mSnapshot = param1GraphicBuffer;
      return this;
    }
    
    public Builder setSystemUiVisibility(int param1Int) {
      this.mSystemUiVisibility = param1Int;
      return this;
    }
    
    public Builder setTaskSize(Point param1Point) {
      this.mTaskSize = param1Point;
      return this;
    }
    
    public Builder setTopActivityComponent(ComponentName param1ComponentName) {
      this.mTopActivity = param1ComponentName;
      return this;
    }
    
    public Builder setWindowingMode(int param1Int) {
      this.mWindowingMode = param1Int;
      return this;
    }
  }
  
  static final class UidObserver extends IUidObserver.Stub {
    final Context mContext;
    
    final ActivityManager.OnUidImportanceListener mListener;
    
    UidObserver(ActivityManager.OnUidImportanceListener param1OnUidImportanceListener, Context param1Context) {
      this.mListener = param1OnUidImportanceListener;
      this.mContext = param1Context;
    }
    
    public void onUidActive(int param1Int) {}
    
    public void onUidCachedChanged(int param1Int, boolean param1Boolean) {}
    
    public void onUidGone(int param1Int, boolean param1Boolean) {
      this.mListener.onUidImportance(param1Int, 1000);
    }
    
    public void onUidIdle(int param1Int, boolean param1Boolean) {}
    
    public void onUidStateChanged(int param1Int1, int param1Int2, long param1Long, int param1Int3) {
      this.mListener.onUidImportance(param1Int1, ActivityManager.RunningAppProcessInfo.procStateToImportanceForClient(param1Int2, this.mContext));
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
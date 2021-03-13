package android.app;

import android.app.assist.AssistStructure;
import android.app.backup.BackupAgent;
import android.app.servertransaction.ActivityLifecycleItem;
import android.app.servertransaction.ActivityRelaunchItem;
import android.app.servertransaction.ActivityResultItem;
import android.app.servertransaction.ClientTransaction;
import android.app.servertransaction.ClientTransactionItem;
import android.app.servertransaction.PauseActivityItem;
import android.app.servertransaction.PendingTransactionActions;
import android.app.servertransaction.ResumeActivityItem;
import android.app.servertransaction.TransactionExecutor;
import android.app.servertransaction.TransactionExecutorHelper;
import android.content.AutofillOptions;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.ContentCaptureOptions;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IContentProvider;
import android.content.IIntentReceiver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.InstrumentationInfo;
import android.content.pm.ParceledListSlice;
import android.content.pm.ProviderInfo;
import android.content.pm.ProviderInfoList;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.loader.ResourcesLoader;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDebug;
import android.ddm.DdmHandleAppName;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.HardwareRenderer;
import android.hardware.display.DisplayManagerGlobal;
import android.net.ConnectivityManager;
import android.net.Proxy;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Debug;
import android.os.Environment;
import android.os.FileUtils;
import android.os.GraphicsEnvironment;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.LocaleList;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.Process;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.StatsFrameworkInitializer;
import android.os.StatsServiceManager;
import android.os.StrictMode;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.TelephonyServiceManager;
import android.os.Trace;
import android.os.UserHandle;
import android.os.UserManager;
import android.permission.IPermissionManager;
import android.renderscript.RenderScriptCacheDir;
import android.security.NetworkSecurityPolicy;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import android.telephony.TelephonyFrameworkInitializer;
import android.util.AndroidRuntimeException;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.EventLog;
import android.util.Log;
import android.util.MergedConfiguration;
import android.util.Pair;
import android.util.PrintWriterPrinter;
import android.util.Printer;
import android.util.Slog;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.SuperNotCalledException;
import android.util.proto.ProtoOutputStream;
import android.view.Choreographer;
import android.view.ContextThemeWrapper;
import android.view.DisplayAdjustments;
import android.view.ThreadedRenderer;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.webkit.WebView;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.content.ReferrerIntent;
import com.android.internal.os.BinderInternal;
import com.android.internal.os.RuntimeInit;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.FastPrintWriter;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.HexConsumer;
import com.android.internal.util.function.QuintConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import com.android.internal.util.function.pooled.PooledRunnable;
import com.android.org.conscrypt.OpenSSLSocketImpl;
import com.android.org.conscrypt.TrustedCertificateStore;
import dalvik.system.CloseGuard;
import dalvik.system.VMDebug;
import dalvik.system.VMRuntime;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import libcore.io.ForwardingOs;
import libcore.io.IoUtils;
import libcore.io.Os;
import libcore.net.event.NetworkEventDispatcher;

public final class ActivityThread extends ClientTransactionHandler {
  private static final int ACTIVITY_THREAD_CHECKIN_VERSION = 4;
  
  private static final long CONTENT_PROVIDER_RETAIN_TIME = 1000L;
  
  private static final boolean DEBUG_BACKUP = false;
  
  public static final boolean DEBUG_BROADCAST = false;
  
  public static final boolean DEBUG_CONFIGURATION = false;
  
  public static final boolean DEBUG_MEMORY_TRIM = false;
  
  static final boolean DEBUG_MESSAGES = false;
  
  public static final boolean DEBUG_ORDER = false;
  
  private static final boolean DEBUG_PROVIDER = false;
  
  private static final boolean DEBUG_RESULTS = false;
  
  private static final boolean DEBUG_SERVICE = false;
  
  private static final String HEAP_COLUMN = "%13s %8s %8s %8s %8s %8s %8s %8s %8s";
  
  private static final String HEAP_FULL_COLUMN = "%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s";
  
  public static final long INVALID_PROC_STATE_SEQ = -1L;
  
  private static final long MIN_TIME_BETWEEN_GCS = 5000L;
  
  private static final String ONE_ALT_COUNT_COLUMN = "%21s %8s %21s %8d";
  
  private static final String ONE_COUNT_COLUMN = "%21s %8d";
  
  private static final long PENDING_TOP_PROCESS_STATE_TIMEOUT = 1000L;
  
  public static final String PROC_START_SEQ_IDENT = "seq=";
  
  private static final boolean REPORT_TO_ACTIVITY = true;
  
  public static final int SERVICE_DONE_EXECUTING_ANON = 0;
  
  public static final int SERVICE_DONE_EXECUTING_START = 1;
  
  public static final int SERVICE_DONE_EXECUTING_STOP = 2;
  
  private static final int SQLITE_MEM_RELEASED_EVENT_LOG_TAG = 75003;
  
  public static final String TAG = "ActivityThread";
  
  private static final String THREE_COUNT_COLUMNS = "%21s %8d %21s %8s %21s %8d";
  
  private static final Bitmap.Config THUMBNAIL_FORMAT = Bitmap.Config.RGB_565;
  
  private static final String TWO_COUNT_COLUMNS = "%21s %8d %21s %8d";
  
  private static final String TWO_COUNT_COLUMN_HEADER = "%21s %8s %21s %8s";
  
  private static final int VM_PROCESS_STATE_JANK_IMPERCEPTIBLE = 1;
  
  private static final int VM_PROCESS_STATE_JANK_PERCEPTIBLE = 0;
  
  static final boolean localLOGV = false;
  
  private static volatile ActivityThread sCurrentActivityThread;
  
  private static final ThreadLocal<Intent> sCurrentBroadcastIntent = new ThreadLocal<>();
  
  static volatile Handler sMainThreadHandler;
  
  static volatile IPackageManager sPackageManager;
  
  private static volatile IPermissionManager sPermissionManager;
  
  private ArrayList<Pair<IBinder, Consumer<DisplayAdjustments>>> mActiveRotationAdjustments;
  
  final ArrayMap<IBinder, ActivityClientRecord> mActivities;
  
  final Map<IBinder, ClientTransactionItem> mActivitiesToBeDestroyed;
  
  final ArrayList<Application> mAllApplications;
  
  final ApplicationThread mAppThread = new ApplicationThread();
  
  private final SparseArray<ArrayMap<String, BackupAgent>> mBackupAgentsByUser;
  
  AppBindData mBoundApplication;
  
  Configuration mCompatConfiguration;
  
  Configuration mConfiguration;
  
  Bundle mCoreSettings;
  
  int mCurDefaultDisplayDpi;
  
  boolean mDensityCompatMode;
  
  final Executor mExecutor;
  
  final GcIdler mGcIdler;
  
  boolean mGcIdlerScheduled;
  
  final ArrayMap<ProviderKey, Object> mGetProviderLocks;
  
  final H mH;
  
  boolean mHasImeComponent;
  
  boolean mHiddenApiWarningShown;
  
  Application mInitialApplication;
  
  Instrumentation mInstrumentation;
  
  String mInstrumentationAppDir;
  
  String mInstrumentationLibDir;
  
  String mInstrumentationPackageName;
  
  String[] mInstrumentationSplitAppDirs;
  
  String mInstrumentedAppDir;
  
  String mInstrumentedLibDir;
  
  String[] mInstrumentedSplitAppDirs;
  
  ArrayList<WeakReference<AssistStructure>> mLastAssistStructures;
  
  private int mLastProcessState;
  
  private final Map<IBinder, Integer> mLastReportedWindowingMode;
  
  private int mLastSessionId;
  
  final ArrayMap<IBinder, ProviderClientRecord> mLocalProviders;
  
  final ArrayMap<ComponentName, ProviderClientRecord> mLocalProvidersByName;
  
  final Looper mLooper = Looper.myLooper();
  
  private Configuration mMainThreadConfig;
  
  private long mNetworkBlockSeq = -1L;
  
  private final Object mNetworkPolicyLock = new Object();
  
  ActivityClientRecord mNewActivities;
  
  private final AtomicInteger mNumLaunchingActivities;
  
  int mNumVisibleActivities;
  
  final ArrayMap<Activity, ArrayList<OnActivityPausedListener>> mOnPauseListeners;
  
  final ArrayMap<String, WeakReference<LoadedApk>> mPackages;
  
  Configuration mPendingConfiguration;
  
  private int mPendingProcessState;
  
  Profiler mProfiler;
  
  final ArrayMap<ProviderKey, ProviderClientRecord> mProviderMap;
  
  final ArrayMap<IBinder, ProviderRefCount> mProviderRefCountMap;
  
  final PurgeIdler mPurgeIdler;
  
  boolean mPurgeIdlerScheduled;
  
  final ArrayList<ActivityClientRecord> mRelaunchingActivities;
  
  private Map<SafeCancellationTransport, CancellationSignal> mRemoteCancellations;
  
  final ArrayMap<String, WeakReference<LoadedApk>> mResourcePackages;
  
  private final ResourcesManager mResourcesManager;
  
  final ArrayMap<IBinder, Service> mServices;
  
  boolean mSomeActivitiesChanged;
  
  private ContextImpl mSystemContext;
  
  boolean mSystemThread;
  
  private ContextImpl mSystemUiContext;
  
  private final TransactionExecutor mTransactionExecutor;
  
  ActivityThread() {
    H h = new H();
    this.mH = h;
    this.mExecutor = (Executor)new HandlerExecutor(h);
    this.mActivities = new ArrayMap();
    this.mActivitiesToBeDestroyed = Collections.synchronizedMap((Map<IBinder, ClientTransactionItem>)new ArrayMap());
    this.mNewActivities = null;
    this.mNumVisibleActivities = 0;
    this.mNumLaunchingActivities = new AtomicInteger();
    this.mLastProcessState = -1;
    this.mPendingProcessState = -1;
    this.mLastAssistStructures = new ArrayList<>();
    this.mServices = new ArrayMap();
    this.mAllApplications = new ArrayList<>();
    this.mBackupAgentsByUser = new SparseArray();
    this.mInstrumentationPackageName = null;
    this.mInstrumentationAppDir = null;
    this.mInstrumentationSplitAppDirs = null;
    this.mInstrumentationLibDir = null;
    this.mInstrumentedAppDir = null;
    this.mInstrumentedSplitAppDirs = null;
    this.mInstrumentedLibDir = null;
    this.mSystemThread = false;
    this.mSomeActivitiesChanged = false;
    this.mHiddenApiWarningShown = false;
    this.mPackages = new ArrayMap();
    this.mResourcePackages = new ArrayMap();
    this.mRelaunchingActivities = new ArrayList<>();
    this.mPendingConfiguration = null;
    this.mTransactionExecutor = new TransactionExecutor(this);
    this.mLastReportedWindowingMode = Collections.synchronizedMap((Map<IBinder, Integer>)new ArrayMap());
    this.mProviderMap = new ArrayMap();
    this.mProviderRefCountMap = new ArrayMap();
    this.mLocalProviders = new ArrayMap();
    this.mLocalProvidersByName = new ArrayMap();
    this.mGetProviderLocks = new ArrayMap();
    this.mOnPauseListeners = new ArrayMap();
    this.mGcIdler = new GcIdler();
    this.mPurgeIdler = new PurgeIdler();
    this.mPurgeIdlerScheduled = false;
    this.mGcIdlerScheduled = false;
    this.mCoreSettings = null;
    this.mHasImeComponent = false;
    this.mMainThreadConfig = new Configuration();
    this.mResourcesManager = ResourcesManager.getInstance();
  }
  
  private void applyPendingProcessState() {
    synchronized (this.mAppThread) {
      if (this.mPendingProcessState == -1)
        return; 
      int i = this.mPendingProcessState;
      this.mPendingProcessState = -1;
      if (i == this.mLastProcessState)
        updateVmProcessState(i); 
      return;
    } 
  }
  
  private void attach(boolean paramBoolean, long paramLong) {
    sCurrentActivityThread = this;
    this.mSystemThread = paramBoolean;
    if (!paramBoolean) {
      DdmHandleAppName.setAppName("<pre-initialized>", UserHandle.myUserId());
      RuntimeInit.setApplicationObject(this.mAppThread.asBinder());
      IActivityManager iActivityManager = ActivityManager.getService();
      try {
        iActivityManager.attachApplication(this.mAppThread, paramLong);
        BinderInternal.addGcWatcher(new Runnable() {
              public void run() {
                if (!ActivityThread.this.mSomeActivitiesChanged)
                  return; 
                Runtime runtime = Runtime.getRuntime();
                long l = runtime.maxMemory();
                if (runtime.totalMemory() - runtime.freeMemory() > 3L * l / 4L) {
                  ActivityThread.this.mSomeActivitiesChanged = false;
                  try {
                    ActivityTaskManager.getService().releaseSomeActivities(ActivityThread.this.mAppThread);
                  } catch (RemoteException remoteException) {
                    throw remoteException.rethrowFromSystemServer();
                  } 
                } 
              }
            });
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } else {
      DdmHandleAppName.setAppName("system_process", UserHandle.myUserId());
      try {
        Instrumentation instrumentation = new Instrumentation();
        this();
        this.mInstrumentation = instrumentation;
        instrumentation.basicInit(this);
        Application application = (ContextImpl.createAppContext(this, (getSystemContext()).mPackageInfo)).mPackageInfo.makeApplication(true, null);
        this.mInitialApplication = application;
        application.onCreate();
        ViewRootImpl.addConfigCallback(new _$$Lambda$ActivityThread$b_FQqoRaVwOfXehXE89zWylx8TU(this));
        return;
      } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to instantiate Application():");
        stringBuilder.append(exception.toString());
        throw new RuntimeException(stringBuilder.toString(), exception);
      } 
    } 
    ViewRootImpl.addConfigCallback(new _$$Lambda$ActivityThread$b_FQqoRaVwOfXehXE89zWylx8TU(this));
  }
  
  private static boolean attemptAttachAgent(String paramString, ClassLoader paramClassLoader) {
    try {
      VMDebug.attachAgent(paramString, paramClassLoader);
      return true;
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Attaching agent with ");
      stringBuilder.append(paramClassLoader);
      stringBuilder.append(" failed: ");
      stringBuilder.append(paramString);
      Slog.e("ActivityThread", stringBuilder.toString());
      return false;
    } 
  }
  
  private void callActivityOnSaveInstanceState(ActivityClientRecord paramActivityClientRecord) {
    paramActivityClientRecord.state = new Bundle();
    paramActivityClientRecord.state.setAllowFds(false);
    if (paramActivityClientRecord.isPersistable()) {
      paramActivityClientRecord.persistentState = new PersistableBundle();
      this.mInstrumentation.callActivityOnSaveInstanceState(paramActivityClientRecord.activity, paramActivityClientRecord.state, paramActivityClientRecord.persistentState);
    } else {
      this.mInstrumentation.callActivityOnSaveInstanceState(paramActivityClientRecord.activity, paramActivityClientRecord.state);
    } 
  }
  
  private void callActivityOnStop(ActivityClientRecord paramActivityClientRecord, boolean paramBoolean, String paramString) {
    // Byte code:
    //   0: iload_2
    //   1: ifeq -> 34
    //   4: aload_1
    //   5: getfield activity : Landroid/app/Activity;
    //   8: getfield mFinished : Z
    //   11: ifne -> 34
    //   14: aload_1
    //   15: getfield state : Landroid/os/Bundle;
    //   18: ifnonnull -> 34
    //   21: aload_1
    //   22: invokestatic access$3700 : (Landroid/app/ActivityThread$ActivityClientRecord;)Z
    //   25: ifne -> 34
    //   28: iconst_1
    //   29: istore #4
    //   31: goto -> 37
    //   34: iconst_0
    //   35: istore #4
    //   37: aload_1
    //   38: invokestatic access$3800 : (Landroid/app/ActivityThread$ActivityClientRecord;)Z
    //   41: istore_2
    //   42: iload #4
    //   44: ifeq -> 56
    //   47: iload_2
    //   48: ifeq -> 56
    //   51: aload_0
    //   52: aload_1
    //   53: invokespecial callActivityOnSaveInstanceState : (Landroid/app/ActivityThread$ActivityClientRecord;)V
    //   56: aload_1
    //   57: getfield activity : Landroid/app/Activity;
    //   60: aload_1
    //   61: getfield mPreserveWindow : Z
    //   64: aload_3
    //   65: invokevirtual performStop : (ZLjava/lang/String;)V
    //   68: goto -> 89
    //   71: astore #5
    //   73: aload_0
    //   74: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   77: aload_1
    //   78: getfield activity : Landroid/app/Activity;
    //   81: aload #5
    //   83: invokevirtual onException : (Ljava/lang/Object;Ljava/lang/Throwable;)Z
    //   86: ifeq -> 109
    //   89: aload_1
    //   90: iconst_5
    //   91: invokevirtual setState : (I)V
    //   94: iload #4
    //   96: ifeq -> 108
    //   99: iload_2
    //   100: ifne -> 108
    //   103: aload_0
    //   104: aload_1
    //   105: invokespecial callActivityOnSaveInstanceState : (Landroid/app/ActivityThread$ActivityClientRecord;)V
    //   108: return
    //   109: new java/lang/StringBuilder
    //   112: dup
    //   113: invokespecial <init> : ()V
    //   116: astore_3
    //   117: aload_3
    //   118: ldc_w 'Unable to stop activity '
    //   121: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: pop
    //   125: aload_3
    //   126: aload_1
    //   127: getfield intent : Landroid/content/Intent;
    //   130: invokevirtual getComponent : ()Landroid/content/ComponentName;
    //   133: invokevirtual toShortString : ()Ljava/lang/String;
    //   136: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: aload_3
    //   141: ldc_w ': '
    //   144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: pop
    //   148: aload_3
    //   149: aload #5
    //   151: invokevirtual toString : ()Ljava/lang/String;
    //   154: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: pop
    //   158: new java/lang/RuntimeException
    //   161: dup
    //   162: aload_3
    //   163: invokevirtual toString : ()Ljava/lang/String;
    //   166: aload #5
    //   168: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   171: athrow
    //   172: astore_1
    //   173: aload_1
    //   174: athrow
    // Exception table:
    //   from	to	target	type
    //   56	68	172	android/util/SuperNotCalledException
    //   56	68	71	java/lang/Exception
  }
  
  private void checkAndBlockForNetworkAccess() {
    synchronized (this.mNetworkPolicyLock) {
      long l = this.mNetworkBlockSeq;
      if (l != -1L)
        try {
          ActivityManager.getService().waitForNetworkStateUpdate(this.mNetworkBlockSeq);
          this.mNetworkBlockSeq = -1L;
        } catch (RemoteException remoteException) {} 
      return;
    } 
  }
  
  static final void cleanUpPendingRemoveWindows(ActivityClientRecord paramActivityClientRecord, boolean paramBoolean) {
    if (paramActivityClientRecord.mPreserveWindow && !paramBoolean)
      return; 
    if (paramActivityClientRecord.mPendingRemoveWindow != null) {
      paramActivityClientRecord.mPendingRemoveWindowManager.removeViewImmediate(paramActivityClientRecord.mPendingRemoveWindow.getDecorView());
      IBinder iBinder = paramActivityClientRecord.mPendingRemoveWindow.getDecorView().getWindowToken();
      if (iBinder != null)
        WindowManagerGlobal.getInstance().closeAll(iBinder, paramActivityClientRecord.activity.getClass().getName(), "Activity"); 
    } 
    paramActivityClientRecord.mPendingRemoveWindow = null;
    paramActivityClientRecord.mPendingRemoveWindowManager = null;
  }
  
  private ContextImpl createBaseContextForActivity(ActivityClientRecord paramActivityClientRecord) {
    try {
      int i = ActivityTaskManager.getService().getDisplayId(paramActivityClientRecord.token);
      ContextImpl contextImpl1 = ContextImpl.createActivityContext(this, paramActivityClientRecord.packageInfo, paramActivityClientRecord.activityInfo, paramActivityClientRecord.token, i, paramActivityClientRecord.overrideConfig);
      if (paramActivityClientRecord.mPendingFixedRotationAdjustments != null) {
        ArrayList<Pair<IBinder, Consumer<DisplayAdjustments>>> arrayList = this.mActiveRotationAdjustments;
        if (arrayList != null && !arrayList.isEmpty()) {
          ResourcesManager resourcesManager = this.mResourcesManager;
          IBinder iBinder = paramActivityClientRecord.token;
          ArrayList<Pair<IBinder, Consumer<DisplayAdjustments>>> arrayList1 = this.mActiveRotationAdjustments;
          resourcesManager.overrideTokenDisplayAdjustments(iBinder, (Consumer<DisplayAdjustments>)((Pair)arrayList1.get(arrayList1.size() - 1)).second);
        } 
        paramActivityClientRecord.mPendingFixedRotationAdjustments = null;
      } 
      DisplayManagerGlobal displayManagerGlobal = DisplayManagerGlobal.getInstance();
      String str = SystemProperties.get("debug.second-display.pkg");
      ContextImpl contextImpl2 = contextImpl1;
      if (str != null) {
        contextImpl2 = contextImpl1;
        if (!str.isEmpty()) {
          contextImpl2 = contextImpl1;
          if (paramActivityClientRecord.packageInfo.mPackageName.contains(str)) {
            int[] arrayOfInt = displayManagerGlobal.getDisplayIds();
            int j = arrayOfInt.length;
            i = 0;
            while (true) {
              contextImpl2 = contextImpl1;
              if (i < j) {
                int k = arrayOfInt[i];
                if (k != 0) {
                  contextImpl2 = (ContextImpl)contextImpl1.createDisplayContext(displayManagerGlobal.getCompatibleDisplay(k, contextImpl1.getResources()));
                  break;
                } 
                i++;
                continue;
              } 
              break;
            } 
          } 
        } 
      } 
      return contextImpl2;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private static Configuration createNewConfigAndUpdateIfNotNull(Configuration paramConfiguration1, Configuration paramConfiguration2) {
    if (paramConfiguration2 == null)
      return paramConfiguration1; 
    paramConfiguration1 = new Configuration(paramConfiguration1);
    paramConfiguration1.updateFrom(paramConfiguration2);
    return paramConfiguration1;
  }
  
  private SafeCancellationTransport createSafeCancellationTransport(CancellationSignal paramCancellationSignal) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mRemoteCancellations : Ljava/util/Map;
    //   6: ifnonnull -> 22
    //   9: new android/util/ArrayMap
    //   12: astore_2
    //   13: aload_2
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: aload_2
    //   19: putfield mRemoteCancellations : Ljava/util/Map;
    //   22: new android/app/ActivityThread$SafeCancellationTransport
    //   25: astore_2
    //   26: aload_2
    //   27: aload_0
    //   28: aload_1
    //   29: invokespecial <init> : (Landroid/app/ActivityThread;Landroid/os/CancellationSignal;)V
    //   32: aload_0
    //   33: getfield mRemoteCancellations : Ljava/util/Map;
    //   36: aload_2
    //   37: aload_1
    //   38: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   43: pop
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_2
    //   47: areturn
    //   48: astore_1
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	48	finally
    //   22	46	48	finally
    //   49	51	48	finally
  }
  
  public static ActivityThread currentActivityThread() {
    return sCurrentActivityThread;
  }
  
  public static Application currentApplication() {
    ActivityThread activityThread = currentActivityThread();
    if (activityThread != null) {
      Application application = activityThread.mInitialApplication;
    } else {
      activityThread = null;
    } 
    return (Application)activityThread;
  }
  
  public static String currentOpPackageName() {
    ActivityThread activityThread = currentActivityThread();
    if (activityThread != null && activityThread.getApplication() != null) {
      String str = activityThread.getApplication().getOpPackageName();
    } else {
      activityThread = null;
    } 
    return (String)activityThread;
  }
  
  public static String currentPackageName() {
    null = currentActivityThread();
    if (null != null) {
      AppBindData appBindData = null.mBoundApplication;
      if (appBindData != null)
        return appBindData.appInfo.packageName; 
    } 
    return null;
  }
  
  public static String currentProcessName() {
    null = currentActivityThread();
    if (null != null) {
      AppBindData appBindData = null.mBoundApplication;
      if (appBindData != null)
        return appBindData.processName; 
    } 
    return null;
  }
  
  private void deliverNewIntents(ActivityClientRecord paramActivityClientRecord, List<ReferrerIntent> paramList) {
    int i = paramList.size();
    for (byte b = 0; b < i; b++) {
      ReferrerIntent referrerIntent = paramList.get(b);
      referrerIntent.setExtrasClassLoader(paramActivityClientRecord.activity.getClassLoader());
      referrerIntent.prepareToEnterProcess();
      paramActivityClientRecord.activity.mFragments.noteStateNotSaved();
      this.mInstrumentation.callActivityOnNewIntent(paramActivityClientRecord.activity, referrerIntent);
    } 
  }
  
  private void deliverResults(ActivityClientRecord paramActivityClientRecord, List<ResultInfo> paramList, String paramString) {
    int i = paramList.size();
    for (byte b = 0; b < i; b++) {
      ResultInfo resultInfo = paramList.get(b);
      try {
        if (resultInfo.mData != null) {
          resultInfo.mData.setExtrasClassLoader(paramActivityClientRecord.activity.getClassLoader());
          resultInfo.mData.prepareToEnterProcess();
        } 
        paramActivityClientRecord.activity.dispatchActivityResult(resultInfo.mResultWho, resultInfo.mRequestCode, resultInfo.mResultCode, resultInfo.mData, paramString);
      } catch (Exception exception) {
        if (!this.mInstrumentation.onException(paramActivityClientRecord.activity, exception)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Failure delivering result ");
          stringBuilder.append(resultInfo);
          stringBuilder.append(" to activity ");
          stringBuilder.append(paramActivityClientRecord.intent.getComponent().toShortString());
          stringBuilder.append(": ");
          stringBuilder.append(exception.toString());
          throw new RuntimeException(stringBuilder.toString(), exception);
        } 
      } 
    } 
  }
  
  public static void dumpMemInfoTable(ProtoOutputStream paramProtoOutputStream, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6) {
    // Byte code:
    //   0: aload_1
    //   1: astore #16
    //   3: iload_3
    //   4: ifne -> 906
    //   7: aload_0
    //   8: ldc2_w 1146756268035
    //   11: invokevirtual start : (J)J
    //   14: lstore #17
    //   16: aload_0
    //   17: ldc2_w 1146756268033
    //   20: ldc_w 'Native Heap'
    //   23: aload #16
    //   25: getfield nativePss : I
    //   28: aload #16
    //   30: getfield nativeSwappablePss : I
    //   33: aload #16
    //   35: getfield nativeSharedDirty : I
    //   38: aload #16
    //   40: getfield nativePrivateDirty : I
    //   43: aload #16
    //   45: getfield nativeSharedClean : I
    //   48: aload #16
    //   50: getfield nativePrivateClean : I
    //   53: aload #16
    //   55: getfield hasSwappedOutPss : Z
    //   58: aload #16
    //   60: getfield nativeSwappedOut : I
    //   63: aload #16
    //   65: getfield nativeSwappedOutPss : I
    //   68: aload #16
    //   70: getfield nativeRss : I
    //   73: invokestatic dumpMemoryInfo : (Landroid/util/proto/ProtoOutputStream;JLjava/lang/String;IIIIIIZIII)V
    //   76: aload_0
    //   77: ldc2_w 1120986464258
    //   80: lload #4
    //   82: invokevirtual write : (JJ)V
    //   85: aload_0
    //   86: ldc2_w 1120986464259
    //   89: lload #6
    //   91: invokevirtual write : (JJ)V
    //   94: aload_0
    //   95: ldc2_w 1120986464260
    //   98: lload #8
    //   100: invokevirtual write : (JJ)V
    //   103: aload_0
    //   104: lload #17
    //   106: invokevirtual end : (J)V
    //   109: aload_0
    //   110: ldc2_w 1146756268036
    //   113: invokevirtual start : (J)J
    //   116: lstore #17
    //   118: aload_1
    //   119: getfield dalvikPss : I
    //   122: istore #19
    //   124: aload_1
    //   125: getfield dalvikSwappablePss : I
    //   128: istore #20
    //   130: aload_1
    //   131: getfield dalvikSharedDirty : I
    //   134: istore #21
    //   136: aload_1
    //   137: getfield dalvikPrivateDirty : I
    //   140: istore #22
    //   142: aload_1
    //   143: getfield dalvikSharedClean : I
    //   146: istore #23
    //   148: aload_1
    //   149: getfield dalvikPrivateClean : I
    //   152: istore #24
    //   154: aload_1
    //   155: getfield hasSwappedOutPss : Z
    //   158: istore_3
    //   159: aload_1
    //   160: getfield dalvikSwappedOut : I
    //   163: istore #25
    //   165: aload_1
    //   166: getfield dalvikSwappedOutPss : I
    //   169: istore #26
    //   171: aload_1
    //   172: getfield dalvikRss : I
    //   175: istore #27
    //   177: aload_0
    //   178: astore #16
    //   180: aload_0
    //   181: ldc2_w 1146756268033
    //   184: ldc_w 'Dalvik Heap'
    //   187: iload #19
    //   189: iload #20
    //   191: iload #21
    //   193: iload #22
    //   195: iload #23
    //   197: iload #24
    //   199: iload_3
    //   200: iload #25
    //   202: iload #26
    //   204: iload #27
    //   206: invokestatic dumpMemoryInfo : (Landroid/util/proto/ProtoOutputStream;JLjava/lang/String;IIIIIIZIII)V
    //   209: aload #16
    //   211: ldc2_w 1120986464258
    //   214: lload #10
    //   216: invokevirtual write : (JJ)V
    //   219: aload #16
    //   221: ldc2_w 1120986464259
    //   224: lload #12
    //   226: invokevirtual write : (JJ)V
    //   229: aload #16
    //   231: ldc2_w 1120986464260
    //   234: lload #14
    //   236: invokevirtual write : (JJ)V
    //   239: aload #16
    //   241: lload #17
    //   243: invokevirtual end : (J)V
    //   246: aload_1
    //   247: astore #28
    //   249: aload #28
    //   251: getfield otherPss : I
    //   254: istore #29
    //   256: aload #28
    //   258: getfield otherSwappablePss : I
    //   261: istore #20
    //   263: aload #28
    //   265: getfield otherSharedDirty : I
    //   268: istore #27
    //   270: aload #28
    //   272: getfield otherPrivateDirty : I
    //   275: istore #24
    //   277: aload #28
    //   279: getfield otherSharedClean : I
    //   282: istore #25
    //   284: aload #28
    //   286: getfield otherPrivateClean : I
    //   289: istore #22
    //   291: aload #28
    //   293: getfield otherSwappedOut : I
    //   296: istore #21
    //   298: aload #28
    //   300: getfield otherSwappedOutPss : I
    //   303: istore #23
    //   305: aload #28
    //   307: getfield otherRss : I
    //   310: istore #26
    //   312: iconst_0
    //   313: istore #19
    //   315: iload #19
    //   317: bipush #17
    //   319: if_icmpge -> 567
    //   322: aload #28
    //   324: iload #19
    //   326: invokevirtual getOtherPss : (I)I
    //   329: istore #30
    //   331: aload #28
    //   333: iload #19
    //   335: invokevirtual getOtherSwappablePss : (I)I
    //   338: istore #31
    //   340: aload #28
    //   342: iload #19
    //   344: invokevirtual getOtherSharedDirty : (I)I
    //   347: istore #32
    //   349: aload #28
    //   351: iload #19
    //   353: invokevirtual getOtherPrivateDirty : (I)I
    //   356: istore #33
    //   358: aload #28
    //   360: iload #19
    //   362: invokevirtual getOtherSharedClean : (I)I
    //   365: istore #34
    //   367: aload #28
    //   369: iload #19
    //   371: invokevirtual getOtherPrivateClean : (I)I
    //   374: istore #35
    //   376: aload #28
    //   378: iload #19
    //   380: invokevirtual getOtherSwappedOut : (I)I
    //   383: istore #36
    //   385: aload #28
    //   387: iload #19
    //   389: invokevirtual getOtherSwappedOutPss : (I)I
    //   392: istore #37
    //   394: aload #28
    //   396: iload #19
    //   398: invokevirtual getOtherRss : (I)I
    //   401: istore #38
    //   403: iload #30
    //   405: ifne -> 463
    //   408: iload #32
    //   410: ifne -> 463
    //   413: iload #33
    //   415: ifne -> 463
    //   418: iload #34
    //   420: ifne -> 463
    //   423: iload #35
    //   425: ifne -> 463
    //   428: iload #38
    //   430: ifne -> 463
    //   433: aload #28
    //   435: getfield hasSwappedOutPss : Z
    //   438: ifeq -> 448
    //   441: iload #37
    //   443: istore #39
    //   445: goto -> 452
    //   448: iload #36
    //   450: istore #39
    //   452: iload #39
    //   454: ifeq -> 460
    //   457: goto -> 463
    //   460: goto -> 561
    //   463: aload_0
    //   464: ldc2_w 2246267895813
    //   467: iload #19
    //   469: invokestatic getOtherLabel : (I)Ljava/lang/String;
    //   472: iload #30
    //   474: iload #31
    //   476: iload #32
    //   478: iload #33
    //   480: iload #34
    //   482: iload #35
    //   484: aload #28
    //   486: getfield hasSwappedOutPss : Z
    //   489: iload #36
    //   491: iload #37
    //   493: iload #38
    //   495: invokestatic dumpMemoryInfo : (Landroid/util/proto/ProtoOutputStream;JLjava/lang/String;IIIIIIZIII)V
    //   498: iload #29
    //   500: iload #30
    //   502: isub
    //   503: istore #29
    //   505: iload #20
    //   507: iload #31
    //   509: isub
    //   510: istore #20
    //   512: iload #27
    //   514: iload #32
    //   516: isub
    //   517: istore #27
    //   519: iload #24
    //   521: iload #33
    //   523: isub
    //   524: istore #24
    //   526: iload #25
    //   528: iload #34
    //   530: isub
    //   531: istore #25
    //   533: iload #22
    //   535: iload #35
    //   537: isub
    //   538: istore #22
    //   540: iload #21
    //   542: iload #36
    //   544: isub
    //   545: istore #21
    //   547: iload #23
    //   549: iload #37
    //   551: isub
    //   552: istore #23
    //   554: iload #26
    //   556: iload #38
    //   558: isub
    //   559: istore #26
    //   561: iinc #19, 1
    //   564: goto -> 315
    //   567: aload_0
    //   568: ldc2_w 1146756268038
    //   571: ldc_w 'Unknown'
    //   574: iload #29
    //   576: iload #20
    //   578: iload #27
    //   580: iload #24
    //   582: iload #25
    //   584: iload #22
    //   586: aload #28
    //   588: getfield hasSwappedOutPss : Z
    //   591: iload #21
    //   593: iload #23
    //   595: iload #26
    //   597: invokestatic dumpMemoryInfo : (Landroid/util/proto/ProtoOutputStream;JLjava/lang/String;IIIIIIZIII)V
    //   600: aload #16
    //   602: ldc2_w 1146756268039
    //   605: invokevirtual start : (J)J
    //   608: lstore #17
    //   610: aload_0
    //   611: ldc2_w 1146756268033
    //   614: ldc_w 'TOTAL'
    //   617: aload_1
    //   618: invokevirtual getTotalPss : ()I
    //   621: aload_1
    //   622: invokevirtual getTotalSwappablePss : ()I
    //   625: aload_1
    //   626: invokevirtual getTotalSharedDirty : ()I
    //   629: aload_1
    //   630: invokevirtual getTotalPrivateDirty : ()I
    //   633: aload_1
    //   634: invokevirtual getTotalSharedClean : ()I
    //   637: aload_1
    //   638: invokevirtual getTotalPrivateClean : ()I
    //   641: aload #28
    //   643: getfield hasSwappedOutPss : Z
    //   646: aload_1
    //   647: invokevirtual getTotalSwappedOut : ()I
    //   650: aload_1
    //   651: invokevirtual getTotalSwappedOutPss : ()I
    //   654: aload_1
    //   655: invokevirtual getTotalRss : ()I
    //   658: invokestatic dumpMemoryInfo : (Landroid/util/proto/ProtoOutputStream;JLjava/lang/String;IIIIIIZIII)V
    //   661: aload #16
    //   663: ldc2_w 1120986464258
    //   666: lload #4
    //   668: lload #10
    //   670: ladd
    //   671: invokevirtual write : (JJ)V
    //   674: aload #16
    //   676: ldc2_w 1120986464259
    //   679: lload #6
    //   681: lload #12
    //   683: ladd
    //   684: invokevirtual write : (JJ)V
    //   687: aload #16
    //   689: ldc2_w 1120986464260
    //   692: lload #8
    //   694: lload #14
    //   696: ladd
    //   697: invokevirtual write : (JJ)V
    //   700: lload #17
    //   702: lstore #4
    //   704: aload #16
    //   706: lload #4
    //   708: invokevirtual end : (J)V
    //   711: iload_2
    //   712: ifeq -> 906
    //   715: bipush #17
    //   717: istore #22
    //   719: iload #22
    //   721: bipush #32
    //   723: if_icmpge -> 903
    //   726: aload #28
    //   728: iload #22
    //   730: invokevirtual getOtherPss : (I)I
    //   733: istore #29
    //   735: aload #28
    //   737: iload #22
    //   739: invokevirtual getOtherSwappablePss : (I)I
    //   742: istore #20
    //   744: aload #28
    //   746: iload #22
    //   748: invokevirtual getOtherSharedDirty : (I)I
    //   751: istore #19
    //   753: aload #28
    //   755: iload #22
    //   757: invokevirtual getOtherPrivateDirty : (I)I
    //   760: istore #21
    //   762: aload #28
    //   764: iload #22
    //   766: invokevirtual getOtherSharedClean : (I)I
    //   769: istore #27
    //   771: aload #28
    //   773: iload #22
    //   775: invokevirtual getOtherPrivateClean : (I)I
    //   778: istore #39
    //   780: aload #28
    //   782: iload #22
    //   784: invokevirtual getOtherSwappedOut : (I)I
    //   787: istore #24
    //   789: aload #28
    //   791: iload #22
    //   793: invokevirtual getOtherSwappedOutPss : (I)I
    //   796: istore #26
    //   798: aload #28
    //   800: iload #22
    //   802: invokevirtual getOtherRss : (I)I
    //   805: istore #23
    //   807: iload #29
    //   809: ifne -> 862
    //   812: iload #19
    //   814: ifne -> 862
    //   817: iload #21
    //   819: ifne -> 862
    //   822: iload #27
    //   824: ifne -> 862
    //   827: iload #39
    //   829: ifne -> 862
    //   832: aload #28
    //   834: getfield hasSwappedOutPss : Z
    //   837: ifeq -> 847
    //   840: iload #26
    //   842: istore #25
    //   844: goto -> 851
    //   847: iload #24
    //   849: istore #25
    //   851: iload #25
    //   853: ifeq -> 859
    //   856: goto -> 862
    //   859: goto -> 897
    //   862: aload_0
    //   863: ldc2_w 2246267895816
    //   866: iload #22
    //   868: invokestatic getOtherLabel : (I)Ljava/lang/String;
    //   871: iload #29
    //   873: iload #20
    //   875: iload #19
    //   877: iload #21
    //   879: iload #27
    //   881: iload #39
    //   883: aload #28
    //   885: getfield hasSwappedOutPss : Z
    //   888: iload #24
    //   890: iload #26
    //   892: iload #23
    //   894: invokestatic dumpMemoryInfo : (Landroid/util/proto/ProtoOutputStream;JLjava/lang/String;IIIIIIZIII)V
    //   897: iinc #22, 1
    //   900: goto -> 719
    //   903: goto -> 906
    //   906: aload_0
    //   907: ldc2_w 1146756268041
    //   910: invokevirtual start : (J)J
    //   913: lstore #4
    //   915: aload_0
    //   916: ldc2_w 1120986464257
    //   919: aload_1
    //   920: invokevirtual getSummaryJavaHeap : ()I
    //   923: invokevirtual write : (JI)V
    //   926: aload_0
    //   927: ldc2_w 1120986464258
    //   930: aload_1
    //   931: invokevirtual getSummaryNativeHeap : ()I
    //   934: invokevirtual write : (JI)V
    //   937: aload_0
    //   938: ldc2_w 1120986464259
    //   941: aload_1
    //   942: invokevirtual getSummaryCode : ()I
    //   945: invokevirtual write : (JI)V
    //   948: aload_0
    //   949: ldc2_w 1120986464260
    //   952: aload_1
    //   953: invokevirtual getSummaryStack : ()I
    //   956: invokevirtual write : (JI)V
    //   959: aload_0
    //   960: ldc2_w 1120986464261
    //   963: aload_1
    //   964: invokevirtual getSummaryGraphics : ()I
    //   967: invokevirtual write : (JI)V
    //   970: aload_0
    //   971: ldc2_w 1120986464262
    //   974: aload_1
    //   975: invokevirtual getSummaryPrivateOther : ()I
    //   978: invokevirtual write : (JI)V
    //   981: aload_0
    //   982: ldc2_w 1120986464263
    //   985: aload_1
    //   986: invokevirtual getSummarySystem : ()I
    //   989: invokevirtual write : (JI)V
    //   992: aload_1
    //   993: getfield hasSwappedOutPss : Z
    //   996: ifeq -> 1013
    //   999: aload_0
    //   1000: ldc2_w 1120986464264
    //   1003: aload_1
    //   1004: invokevirtual getSummaryTotalSwapPss : ()I
    //   1007: invokevirtual write : (JI)V
    //   1010: goto -> 1024
    //   1013: aload_0
    //   1014: ldc2_w 1120986464264
    //   1017: aload_1
    //   1018: invokevirtual getSummaryTotalSwap : ()I
    //   1021: invokevirtual write : (JI)V
    //   1024: aload_0
    //   1025: ldc2_w 1120986464266
    //   1028: aload_1
    //   1029: invokevirtual getSummaryJavaHeapRss : ()I
    //   1032: invokevirtual write : (JI)V
    //   1035: aload_0
    //   1036: ldc2_w 1120986464267
    //   1039: aload_1
    //   1040: invokevirtual getSummaryNativeHeapRss : ()I
    //   1043: invokevirtual write : (JI)V
    //   1046: aload_0
    //   1047: ldc2_w 1120986464268
    //   1050: aload_1
    //   1051: invokevirtual getSummaryCodeRss : ()I
    //   1054: invokevirtual write : (JI)V
    //   1057: aload_0
    //   1058: ldc2_w 1120986464269
    //   1061: aload_1
    //   1062: invokevirtual getSummaryStackRss : ()I
    //   1065: invokevirtual write : (JI)V
    //   1068: aload_0
    //   1069: ldc2_w 1120986464270
    //   1072: aload_1
    //   1073: invokevirtual getSummaryGraphicsRss : ()I
    //   1076: invokevirtual write : (JI)V
    //   1079: aload_0
    //   1080: ldc2_w 1120986464271
    //   1083: aload_1
    //   1084: invokevirtual getSummaryUnknownRss : ()I
    //   1087: invokevirtual write : (JI)V
    //   1090: aload_0
    //   1091: lload #4
    //   1093: invokevirtual end : (J)V
    //   1096: return
  }
  
  public static void dumpMemInfoTable(PrintWriter paramPrintWriter, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt, String paramString, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6) {
    // Byte code:
    //   0: iload_2
    //   1: ifeq -> 840
    //   4: aload_0
    //   5: iconst_4
    //   6: invokevirtual print : (I)V
    //   9: aload_0
    //   10: bipush #44
    //   12: invokevirtual print : (C)V
    //   15: aload_0
    //   16: iload #6
    //   18: invokevirtual print : (I)V
    //   21: aload_0
    //   22: bipush #44
    //   24: invokevirtual print : (C)V
    //   27: aload_0
    //   28: aload #7
    //   30: invokevirtual print : (Ljava/lang/String;)V
    //   33: aload_0
    //   34: bipush #44
    //   36: invokevirtual print : (C)V
    //   39: aload_0
    //   40: lload #8
    //   42: invokevirtual print : (J)V
    //   45: aload_0
    //   46: bipush #44
    //   48: invokevirtual print : (C)V
    //   51: aload_0
    //   52: lload #14
    //   54: invokevirtual print : (J)V
    //   57: aload_0
    //   58: bipush #44
    //   60: invokevirtual print : (C)V
    //   63: aload_0
    //   64: ldc_w 'N/A,'
    //   67: invokevirtual print : (Ljava/lang/String;)V
    //   70: aload_0
    //   71: lload #8
    //   73: lload #14
    //   75: ladd
    //   76: invokevirtual print : (J)V
    //   79: aload_0
    //   80: bipush #44
    //   82: invokevirtual print : (C)V
    //   85: aload_0
    //   86: lload #10
    //   88: invokevirtual print : (J)V
    //   91: aload_0
    //   92: bipush #44
    //   94: invokevirtual print : (C)V
    //   97: aload_0
    //   98: lload #16
    //   100: invokevirtual print : (J)V
    //   103: aload_0
    //   104: bipush #44
    //   106: invokevirtual print : (C)V
    //   109: aload_0
    //   110: ldc_w 'N/A,'
    //   113: invokevirtual print : (Ljava/lang/String;)V
    //   116: aload_0
    //   117: lload #10
    //   119: lload #16
    //   121: ladd
    //   122: invokevirtual print : (J)V
    //   125: aload_0
    //   126: bipush #44
    //   128: invokevirtual print : (C)V
    //   131: aload_0
    //   132: lload #12
    //   134: invokevirtual print : (J)V
    //   137: aload_0
    //   138: bipush #44
    //   140: invokevirtual print : (C)V
    //   143: aload_0
    //   144: lload #18
    //   146: invokevirtual print : (J)V
    //   149: aload_0
    //   150: bipush #44
    //   152: invokevirtual print : (C)V
    //   155: aload_0
    //   156: ldc_w 'N/A,'
    //   159: invokevirtual print : (Ljava/lang/String;)V
    //   162: aload_0
    //   163: lload #12
    //   165: lload #18
    //   167: ladd
    //   168: invokevirtual print : (J)V
    //   171: aload_0
    //   172: bipush #44
    //   174: invokevirtual print : (C)V
    //   177: aload_0
    //   178: aload_1
    //   179: getfield nativePss : I
    //   182: invokevirtual print : (I)V
    //   185: aload_0
    //   186: bipush #44
    //   188: invokevirtual print : (C)V
    //   191: aload_0
    //   192: aload_1
    //   193: getfield dalvikPss : I
    //   196: invokevirtual print : (I)V
    //   199: aload_0
    //   200: bipush #44
    //   202: invokevirtual print : (C)V
    //   205: aload_0
    //   206: aload_1
    //   207: getfield otherPss : I
    //   210: invokevirtual print : (I)V
    //   213: aload_0
    //   214: bipush #44
    //   216: invokevirtual print : (C)V
    //   219: aload_0
    //   220: aload_1
    //   221: invokevirtual getTotalPss : ()I
    //   224: invokevirtual print : (I)V
    //   227: aload_0
    //   228: bipush #44
    //   230: invokevirtual print : (C)V
    //   233: aload_0
    //   234: aload_1
    //   235: getfield nativeSwappablePss : I
    //   238: invokevirtual print : (I)V
    //   241: aload_0
    //   242: bipush #44
    //   244: invokevirtual print : (C)V
    //   247: aload_0
    //   248: aload_1
    //   249: getfield dalvikSwappablePss : I
    //   252: invokevirtual print : (I)V
    //   255: aload_0
    //   256: bipush #44
    //   258: invokevirtual print : (C)V
    //   261: aload_0
    //   262: aload_1
    //   263: getfield otherSwappablePss : I
    //   266: invokevirtual print : (I)V
    //   269: aload_0
    //   270: bipush #44
    //   272: invokevirtual print : (C)V
    //   275: aload_0
    //   276: aload_1
    //   277: invokevirtual getTotalSwappablePss : ()I
    //   280: invokevirtual print : (I)V
    //   283: aload_0
    //   284: bipush #44
    //   286: invokevirtual print : (C)V
    //   289: aload_0
    //   290: aload_1
    //   291: getfield nativeSharedDirty : I
    //   294: invokevirtual print : (I)V
    //   297: aload_0
    //   298: bipush #44
    //   300: invokevirtual print : (C)V
    //   303: aload_0
    //   304: aload_1
    //   305: getfield dalvikSharedDirty : I
    //   308: invokevirtual print : (I)V
    //   311: aload_0
    //   312: bipush #44
    //   314: invokevirtual print : (C)V
    //   317: aload_0
    //   318: aload_1
    //   319: getfield otherSharedDirty : I
    //   322: invokevirtual print : (I)V
    //   325: aload_0
    //   326: bipush #44
    //   328: invokevirtual print : (C)V
    //   331: aload_0
    //   332: aload_1
    //   333: invokevirtual getTotalSharedDirty : ()I
    //   336: invokevirtual print : (I)V
    //   339: aload_0
    //   340: bipush #44
    //   342: invokevirtual print : (C)V
    //   345: aload_0
    //   346: aload_1
    //   347: getfield nativeSharedClean : I
    //   350: invokevirtual print : (I)V
    //   353: aload_0
    //   354: bipush #44
    //   356: invokevirtual print : (C)V
    //   359: aload_0
    //   360: aload_1
    //   361: getfield dalvikSharedClean : I
    //   364: invokevirtual print : (I)V
    //   367: aload_0
    //   368: bipush #44
    //   370: invokevirtual print : (C)V
    //   373: aload_0
    //   374: aload_1
    //   375: getfield otherSharedClean : I
    //   378: invokevirtual print : (I)V
    //   381: aload_0
    //   382: bipush #44
    //   384: invokevirtual print : (C)V
    //   387: aload_0
    //   388: aload_1
    //   389: invokevirtual getTotalSharedClean : ()I
    //   392: invokevirtual print : (I)V
    //   395: aload_0
    //   396: bipush #44
    //   398: invokevirtual print : (C)V
    //   401: aload_0
    //   402: aload_1
    //   403: getfield nativePrivateDirty : I
    //   406: invokevirtual print : (I)V
    //   409: aload_0
    //   410: bipush #44
    //   412: invokevirtual print : (C)V
    //   415: aload_0
    //   416: aload_1
    //   417: getfield dalvikPrivateDirty : I
    //   420: invokevirtual print : (I)V
    //   423: aload_0
    //   424: bipush #44
    //   426: invokevirtual print : (C)V
    //   429: aload_0
    //   430: aload_1
    //   431: getfield otherPrivateDirty : I
    //   434: invokevirtual print : (I)V
    //   437: aload_0
    //   438: bipush #44
    //   440: invokevirtual print : (C)V
    //   443: aload_0
    //   444: aload_1
    //   445: invokevirtual getTotalPrivateDirty : ()I
    //   448: invokevirtual print : (I)V
    //   451: aload_0
    //   452: bipush #44
    //   454: invokevirtual print : (C)V
    //   457: aload_0
    //   458: aload_1
    //   459: getfield nativePrivateClean : I
    //   462: invokevirtual print : (I)V
    //   465: aload_0
    //   466: bipush #44
    //   468: invokevirtual print : (C)V
    //   471: aload_0
    //   472: aload_1
    //   473: getfield dalvikPrivateClean : I
    //   476: invokevirtual print : (I)V
    //   479: aload_0
    //   480: bipush #44
    //   482: invokevirtual print : (C)V
    //   485: aload_0
    //   486: aload_1
    //   487: getfield otherPrivateClean : I
    //   490: invokevirtual print : (I)V
    //   493: aload_0
    //   494: bipush #44
    //   496: invokevirtual print : (C)V
    //   499: aload_0
    //   500: aload_1
    //   501: invokevirtual getTotalPrivateClean : ()I
    //   504: invokevirtual print : (I)V
    //   507: aload_0
    //   508: bipush #44
    //   510: invokevirtual print : (C)V
    //   513: aload_0
    //   514: aload_1
    //   515: getfield nativeSwappedOut : I
    //   518: invokevirtual print : (I)V
    //   521: aload_0
    //   522: bipush #44
    //   524: invokevirtual print : (C)V
    //   527: aload_0
    //   528: aload_1
    //   529: getfield dalvikSwappedOut : I
    //   532: invokevirtual print : (I)V
    //   535: aload_0
    //   536: bipush #44
    //   538: invokevirtual print : (C)V
    //   541: aload_0
    //   542: aload_1
    //   543: getfield otherSwappedOut : I
    //   546: invokevirtual print : (I)V
    //   549: aload_0
    //   550: bipush #44
    //   552: invokevirtual print : (C)V
    //   555: aload_0
    //   556: aload_1
    //   557: invokevirtual getTotalSwappedOut : ()I
    //   560: invokevirtual print : (I)V
    //   563: aload_0
    //   564: bipush #44
    //   566: invokevirtual print : (C)V
    //   569: aload_1
    //   570: getfield hasSwappedOutPss : Z
    //   573: ifeq -> 635
    //   576: aload_0
    //   577: aload_1
    //   578: getfield nativeSwappedOutPss : I
    //   581: invokevirtual print : (I)V
    //   584: aload_0
    //   585: bipush #44
    //   587: invokevirtual print : (C)V
    //   590: aload_0
    //   591: aload_1
    //   592: getfield dalvikSwappedOutPss : I
    //   595: invokevirtual print : (I)V
    //   598: aload_0
    //   599: bipush #44
    //   601: invokevirtual print : (C)V
    //   604: aload_0
    //   605: aload_1
    //   606: getfield otherSwappedOutPss : I
    //   609: invokevirtual print : (I)V
    //   612: aload_0
    //   613: bipush #44
    //   615: invokevirtual print : (C)V
    //   618: aload_0
    //   619: aload_1
    //   620: invokevirtual getTotalSwappedOutPss : ()I
    //   623: invokevirtual print : (I)V
    //   626: aload_0
    //   627: bipush #44
    //   629: invokevirtual print : (C)V
    //   632: goto -> 663
    //   635: aload_0
    //   636: ldc_w 'N/A,'
    //   639: invokevirtual print : (Ljava/lang/String;)V
    //   642: aload_0
    //   643: ldc_w 'N/A,'
    //   646: invokevirtual print : (Ljava/lang/String;)V
    //   649: aload_0
    //   650: ldc_w 'N/A,'
    //   653: invokevirtual print : (Ljava/lang/String;)V
    //   656: aload_0
    //   657: ldc_w 'N/A,'
    //   660: invokevirtual print : (Ljava/lang/String;)V
    //   663: iconst_0
    //   664: istore #6
    //   666: iload #6
    //   668: bipush #17
    //   670: if_icmpge -> 839
    //   673: aload_0
    //   674: iload #6
    //   676: invokestatic getOtherLabel : (I)Ljava/lang/String;
    //   679: invokevirtual print : (Ljava/lang/String;)V
    //   682: aload_0
    //   683: bipush #44
    //   685: invokevirtual print : (C)V
    //   688: aload_0
    //   689: aload_1
    //   690: iload #6
    //   692: invokevirtual getOtherPss : (I)I
    //   695: invokevirtual print : (I)V
    //   698: aload_0
    //   699: bipush #44
    //   701: invokevirtual print : (C)V
    //   704: aload_0
    //   705: aload_1
    //   706: iload #6
    //   708: invokevirtual getOtherSwappablePss : (I)I
    //   711: invokevirtual print : (I)V
    //   714: aload_0
    //   715: bipush #44
    //   717: invokevirtual print : (C)V
    //   720: aload_0
    //   721: aload_1
    //   722: iload #6
    //   724: invokevirtual getOtherSharedDirty : (I)I
    //   727: invokevirtual print : (I)V
    //   730: aload_0
    //   731: bipush #44
    //   733: invokevirtual print : (C)V
    //   736: aload_0
    //   737: aload_1
    //   738: iload #6
    //   740: invokevirtual getOtherSharedClean : (I)I
    //   743: invokevirtual print : (I)V
    //   746: aload_0
    //   747: bipush #44
    //   749: invokevirtual print : (C)V
    //   752: aload_0
    //   753: aload_1
    //   754: iload #6
    //   756: invokevirtual getOtherPrivateDirty : (I)I
    //   759: invokevirtual print : (I)V
    //   762: aload_0
    //   763: bipush #44
    //   765: invokevirtual print : (C)V
    //   768: aload_0
    //   769: aload_1
    //   770: iload #6
    //   772: invokevirtual getOtherPrivateClean : (I)I
    //   775: invokevirtual print : (I)V
    //   778: aload_0
    //   779: bipush #44
    //   781: invokevirtual print : (C)V
    //   784: aload_0
    //   785: aload_1
    //   786: iload #6
    //   788: invokevirtual getOtherSwappedOut : (I)I
    //   791: invokevirtual print : (I)V
    //   794: aload_0
    //   795: bipush #44
    //   797: invokevirtual print : (C)V
    //   800: aload_1
    //   801: getfield hasSwappedOutPss : Z
    //   804: ifeq -> 826
    //   807: aload_0
    //   808: aload_1
    //   809: iload #6
    //   811: invokevirtual getOtherSwappedOutPss : (I)I
    //   814: invokevirtual print : (I)V
    //   817: aload_0
    //   818: bipush #44
    //   820: invokevirtual print : (C)V
    //   823: goto -> 833
    //   826: aload_0
    //   827: ldc_w 'N/A,'
    //   830: invokevirtual print : (Ljava/lang/String;)V
    //   833: iinc #6, 1
    //   836: goto -> 666
    //   839: return
    //   840: iload #5
    //   842: ifne -> 3461
    //   845: iload_3
    //   846: ifeq -> 1480
    //   849: aload_1
    //   850: getfield hasSwappedOutPss : Z
    //   853: ifeq -> 864
    //   856: ldc_w 'SwapPss'
    //   859: astore #7
    //   861: goto -> 869
    //   864: ldc_w 'Swap'
    //   867: astore #7
    //   869: aload_0
    //   870: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   872: bipush #12
    //   874: anewarray java/lang/Object
    //   877: dup
    //   878: iconst_0
    //   879: ldc_w ''
    //   882: aastore
    //   883: dup
    //   884: iconst_1
    //   885: ldc_w 'Pss'
    //   888: aastore
    //   889: dup
    //   890: iconst_2
    //   891: ldc_w 'Pss'
    //   894: aastore
    //   895: dup
    //   896: iconst_3
    //   897: ldc_w 'Shared'
    //   900: aastore
    //   901: dup
    //   902: iconst_4
    //   903: ldc_w 'Private'
    //   906: aastore
    //   907: dup
    //   908: iconst_5
    //   909: ldc_w 'Shared'
    //   912: aastore
    //   913: dup
    //   914: bipush #6
    //   916: ldc_w 'Private'
    //   919: aastore
    //   920: dup
    //   921: bipush #7
    //   923: aload #7
    //   925: aastore
    //   926: dup
    //   927: bipush #8
    //   929: ldc_w 'Rss'
    //   932: aastore
    //   933: dup
    //   934: bipush #9
    //   936: ldc_w 'Heap'
    //   939: aastore
    //   940: dup
    //   941: bipush #10
    //   943: ldc_w 'Heap'
    //   946: aastore
    //   947: dup
    //   948: bipush #11
    //   950: ldc_w 'Heap'
    //   953: aastore
    //   954: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   957: aload_0
    //   958: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   960: bipush #12
    //   962: anewarray java/lang/Object
    //   965: dup
    //   966: iconst_0
    //   967: ldc_w ''
    //   970: aastore
    //   971: dup
    //   972: iconst_1
    //   973: ldc_w 'Total'
    //   976: aastore
    //   977: dup
    //   978: iconst_2
    //   979: ldc_w 'Clean'
    //   982: aastore
    //   983: dup
    //   984: iconst_3
    //   985: ldc_w 'Dirty'
    //   988: aastore
    //   989: dup
    //   990: iconst_4
    //   991: ldc_w 'Dirty'
    //   994: aastore
    //   995: dup
    //   996: iconst_5
    //   997: ldc_w 'Clean'
    //   1000: aastore
    //   1001: dup
    //   1002: bipush #6
    //   1004: ldc_w 'Clean'
    //   1007: aastore
    //   1008: dup
    //   1009: bipush #7
    //   1011: ldc_w 'Dirty'
    //   1014: aastore
    //   1015: dup
    //   1016: bipush #8
    //   1018: ldc_w 'Total'
    //   1021: aastore
    //   1022: dup
    //   1023: bipush #9
    //   1025: ldc_w 'Size'
    //   1028: aastore
    //   1029: dup
    //   1030: bipush #10
    //   1032: ldc_w 'Alloc'
    //   1035: aastore
    //   1036: dup
    //   1037: bipush #11
    //   1039: ldc_w 'Free'
    //   1042: aastore
    //   1043: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   1046: aload_0
    //   1047: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   1049: bipush #12
    //   1051: anewarray java/lang/Object
    //   1054: dup
    //   1055: iconst_0
    //   1056: ldc_w ''
    //   1059: aastore
    //   1060: dup
    //   1061: iconst_1
    //   1062: ldc_w '------'
    //   1065: aastore
    //   1066: dup
    //   1067: iconst_2
    //   1068: ldc_w '------'
    //   1071: aastore
    //   1072: dup
    //   1073: iconst_3
    //   1074: ldc_w '------'
    //   1077: aastore
    //   1078: dup
    //   1079: iconst_4
    //   1080: ldc_w '------'
    //   1083: aastore
    //   1084: dup
    //   1085: iconst_5
    //   1086: ldc_w '------'
    //   1089: aastore
    //   1090: dup
    //   1091: bipush #6
    //   1093: ldc_w '------'
    //   1096: aastore
    //   1097: dup
    //   1098: bipush #7
    //   1100: ldc_w '------'
    //   1103: aastore
    //   1104: dup
    //   1105: bipush #8
    //   1107: ldc_w '------'
    //   1110: aastore
    //   1111: dup
    //   1112: bipush #9
    //   1114: ldc_w '------'
    //   1117: aastore
    //   1118: dup
    //   1119: bipush #10
    //   1121: ldc_w '------'
    //   1124: aastore
    //   1125: dup
    //   1126: bipush #11
    //   1128: ldc_w '------'
    //   1131: aastore
    //   1132: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   1135: aload_1
    //   1136: getfield nativePss : I
    //   1139: istore #20
    //   1141: aload_1
    //   1142: getfield nativeSwappablePss : I
    //   1145: istore #21
    //   1147: aload_1
    //   1148: getfield nativeSharedDirty : I
    //   1151: istore #22
    //   1153: aload_1
    //   1154: getfield nativePrivateDirty : I
    //   1157: istore #23
    //   1159: aload_1
    //   1160: getfield nativeSharedClean : I
    //   1163: istore #24
    //   1165: aload_1
    //   1166: getfield nativePrivateClean : I
    //   1169: istore #25
    //   1171: aload_1
    //   1172: getfield hasSwappedOutPss : Z
    //   1175: ifeq -> 1187
    //   1178: aload_1
    //   1179: getfield nativeSwappedOutPss : I
    //   1182: istore #6
    //   1184: goto -> 1193
    //   1187: aload_1
    //   1188: getfield nativeSwappedOut : I
    //   1191: istore #6
    //   1193: aload_0
    //   1194: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   1196: bipush #12
    //   1198: anewarray java/lang/Object
    //   1201: dup
    //   1202: iconst_0
    //   1203: ldc_w 'Native Heap'
    //   1206: aastore
    //   1207: dup
    //   1208: iconst_1
    //   1209: iload #20
    //   1211: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1214: aastore
    //   1215: dup
    //   1216: iconst_2
    //   1217: iload #21
    //   1219: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1222: aastore
    //   1223: dup
    //   1224: iconst_3
    //   1225: iload #22
    //   1227: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1230: aastore
    //   1231: dup
    //   1232: iconst_4
    //   1233: iload #23
    //   1235: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1238: aastore
    //   1239: dup
    //   1240: iconst_5
    //   1241: iload #24
    //   1243: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1246: aastore
    //   1247: dup
    //   1248: bipush #6
    //   1250: iload #25
    //   1252: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1255: aastore
    //   1256: dup
    //   1257: bipush #7
    //   1259: iload #6
    //   1261: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1264: aastore
    //   1265: dup
    //   1266: bipush #8
    //   1268: aload_1
    //   1269: getfield nativeRss : I
    //   1272: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1275: aastore
    //   1276: dup
    //   1277: bipush #9
    //   1279: lload #8
    //   1281: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1284: aastore
    //   1285: dup
    //   1286: bipush #10
    //   1288: lload #10
    //   1290: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1293: aastore
    //   1294: dup
    //   1295: bipush #11
    //   1297: lload #12
    //   1299: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1302: aastore
    //   1303: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   1306: aload_1
    //   1307: getfield dalvikPss : I
    //   1310: istore #21
    //   1312: aload_1
    //   1313: getfield dalvikSwappablePss : I
    //   1316: istore #22
    //   1318: aload_1
    //   1319: getfield dalvikSharedDirty : I
    //   1322: istore #24
    //   1324: aload_1
    //   1325: getfield dalvikPrivateDirty : I
    //   1328: istore #25
    //   1330: aload_1
    //   1331: getfield dalvikSharedClean : I
    //   1334: istore #23
    //   1336: aload_1
    //   1337: getfield dalvikPrivateClean : I
    //   1340: istore #20
    //   1342: aload_1
    //   1343: getfield hasSwappedOutPss : Z
    //   1346: ifeq -> 1358
    //   1349: aload_1
    //   1350: getfield dalvikSwappedOutPss : I
    //   1353: istore #6
    //   1355: goto -> 1364
    //   1358: aload_1
    //   1359: getfield dalvikSwappedOut : I
    //   1362: istore #6
    //   1364: aload_0
    //   1365: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   1367: bipush #12
    //   1369: anewarray java/lang/Object
    //   1372: dup
    //   1373: iconst_0
    //   1374: ldc_w 'Dalvik Heap'
    //   1377: aastore
    //   1378: dup
    //   1379: iconst_1
    //   1380: iload #21
    //   1382: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1385: aastore
    //   1386: dup
    //   1387: iconst_2
    //   1388: iload #22
    //   1390: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1393: aastore
    //   1394: dup
    //   1395: iconst_3
    //   1396: iload #24
    //   1398: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1401: aastore
    //   1402: dup
    //   1403: iconst_4
    //   1404: iload #25
    //   1406: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1409: aastore
    //   1410: dup
    //   1411: iconst_5
    //   1412: iload #23
    //   1414: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1417: aastore
    //   1418: dup
    //   1419: bipush #6
    //   1421: iload #20
    //   1423: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1426: aastore
    //   1427: dup
    //   1428: bipush #7
    //   1430: iload #6
    //   1432: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1435: aastore
    //   1436: dup
    //   1437: bipush #8
    //   1439: aload_1
    //   1440: getfield dalvikRss : I
    //   1443: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1446: aastore
    //   1447: dup
    //   1448: bipush #9
    //   1450: lload #14
    //   1452: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1455: aastore
    //   1456: dup
    //   1457: bipush #10
    //   1459: lload #16
    //   1461: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1464: aastore
    //   1465: dup
    //   1466: bipush #11
    //   1468: lload #18
    //   1470: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1473: aastore
    //   1474: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   1477: goto -> 1962
    //   1480: aload_1
    //   1481: getfield hasSwappedOutPss : Z
    //   1484: ifeq -> 1495
    //   1487: ldc_w 'SwapPss'
    //   1490: astore #7
    //   1492: goto -> 1500
    //   1495: ldc_w 'Swap'
    //   1498: astore #7
    //   1500: aload_0
    //   1501: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   1503: bipush #9
    //   1505: anewarray java/lang/Object
    //   1508: dup
    //   1509: iconst_0
    //   1510: ldc_w ''
    //   1513: aastore
    //   1514: dup
    //   1515: iconst_1
    //   1516: ldc_w 'Pss'
    //   1519: aastore
    //   1520: dup
    //   1521: iconst_2
    //   1522: ldc_w 'Private'
    //   1525: aastore
    //   1526: dup
    //   1527: iconst_3
    //   1528: ldc_w 'Private'
    //   1531: aastore
    //   1532: dup
    //   1533: iconst_4
    //   1534: aload #7
    //   1536: aastore
    //   1537: dup
    //   1538: iconst_5
    //   1539: ldc_w 'Rss'
    //   1542: aastore
    //   1543: dup
    //   1544: bipush #6
    //   1546: ldc_w 'Heap'
    //   1549: aastore
    //   1550: dup
    //   1551: bipush #7
    //   1553: ldc_w 'Heap'
    //   1556: aastore
    //   1557: dup
    //   1558: bipush #8
    //   1560: ldc_w 'Heap'
    //   1563: aastore
    //   1564: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   1567: aload_0
    //   1568: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   1570: bipush #9
    //   1572: anewarray java/lang/Object
    //   1575: dup
    //   1576: iconst_0
    //   1577: ldc_w ''
    //   1580: aastore
    //   1581: dup
    //   1582: iconst_1
    //   1583: ldc_w 'Total'
    //   1586: aastore
    //   1587: dup
    //   1588: iconst_2
    //   1589: ldc_w 'Dirty'
    //   1592: aastore
    //   1593: dup
    //   1594: iconst_3
    //   1595: ldc_w 'Clean'
    //   1598: aastore
    //   1599: dup
    //   1600: iconst_4
    //   1601: ldc_w 'Dirty'
    //   1604: aastore
    //   1605: dup
    //   1606: iconst_5
    //   1607: ldc_w 'Total'
    //   1610: aastore
    //   1611: dup
    //   1612: bipush #6
    //   1614: ldc_w 'Size'
    //   1617: aastore
    //   1618: dup
    //   1619: bipush #7
    //   1621: ldc_w 'Alloc'
    //   1624: aastore
    //   1625: dup
    //   1626: bipush #8
    //   1628: ldc_w 'Free'
    //   1631: aastore
    //   1632: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   1635: aload_0
    //   1636: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   1638: bipush #10
    //   1640: anewarray java/lang/Object
    //   1643: dup
    //   1644: iconst_0
    //   1645: ldc_w ''
    //   1648: aastore
    //   1649: dup
    //   1650: iconst_1
    //   1651: ldc_w '------'
    //   1654: aastore
    //   1655: dup
    //   1656: iconst_2
    //   1657: ldc_w '------'
    //   1660: aastore
    //   1661: dup
    //   1662: iconst_3
    //   1663: ldc_w '------'
    //   1666: aastore
    //   1667: dup
    //   1668: iconst_4
    //   1669: ldc_w '------'
    //   1672: aastore
    //   1673: dup
    //   1674: iconst_5
    //   1675: ldc_w '------'
    //   1678: aastore
    //   1679: dup
    //   1680: bipush #6
    //   1682: ldc_w '------'
    //   1685: aastore
    //   1686: dup
    //   1687: bipush #7
    //   1689: ldc_w '------'
    //   1692: aastore
    //   1693: dup
    //   1694: bipush #8
    //   1696: ldc_w '------'
    //   1699: aastore
    //   1700: dup
    //   1701: bipush #9
    //   1703: ldc_w '------'
    //   1706: aastore
    //   1707: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   1710: aload_1
    //   1711: getfield nativePss : I
    //   1714: istore #22
    //   1716: aload_1
    //   1717: getfield nativePrivateDirty : I
    //   1720: istore #21
    //   1722: aload_1
    //   1723: getfield nativePrivateClean : I
    //   1726: istore #25
    //   1728: aload_1
    //   1729: getfield hasSwappedOutPss : Z
    //   1732: ifeq -> 1744
    //   1735: aload_1
    //   1736: getfield nativeSwappedOutPss : I
    //   1739: istore #6
    //   1741: goto -> 1750
    //   1744: aload_1
    //   1745: getfield nativeSwappedOut : I
    //   1748: istore #6
    //   1750: aload_0
    //   1751: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   1753: bipush #9
    //   1755: anewarray java/lang/Object
    //   1758: dup
    //   1759: iconst_0
    //   1760: ldc_w 'Native Heap'
    //   1763: aastore
    //   1764: dup
    //   1765: iconst_1
    //   1766: iload #22
    //   1768: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1771: aastore
    //   1772: dup
    //   1773: iconst_2
    //   1774: iload #21
    //   1776: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1779: aastore
    //   1780: dup
    //   1781: iconst_3
    //   1782: iload #25
    //   1784: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1787: aastore
    //   1788: dup
    //   1789: iconst_4
    //   1790: iload #6
    //   1792: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1795: aastore
    //   1796: dup
    //   1797: iconst_5
    //   1798: aload_1
    //   1799: getfield nativeRss : I
    //   1802: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1805: aastore
    //   1806: dup
    //   1807: bipush #6
    //   1809: lload #8
    //   1811: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1814: aastore
    //   1815: dup
    //   1816: bipush #7
    //   1818: lload #10
    //   1820: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1823: aastore
    //   1824: dup
    //   1825: bipush #8
    //   1827: lload #12
    //   1829: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1832: aastore
    //   1833: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   1836: aload_1
    //   1837: getfield dalvikPss : I
    //   1840: istore #25
    //   1842: aload_1
    //   1843: getfield dalvikPrivateDirty : I
    //   1846: istore #21
    //   1848: aload_1
    //   1849: getfield dalvikPrivateClean : I
    //   1852: istore #22
    //   1854: aload_1
    //   1855: getfield hasSwappedOutPss : Z
    //   1858: ifeq -> 1870
    //   1861: aload_1
    //   1862: getfield dalvikSwappedOutPss : I
    //   1865: istore #6
    //   1867: goto -> 1876
    //   1870: aload_1
    //   1871: getfield dalvikSwappedOut : I
    //   1874: istore #6
    //   1876: aload_0
    //   1877: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   1879: bipush #9
    //   1881: anewarray java/lang/Object
    //   1884: dup
    //   1885: iconst_0
    //   1886: ldc_w 'Dalvik Heap'
    //   1889: aastore
    //   1890: dup
    //   1891: iconst_1
    //   1892: iload #25
    //   1894: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1897: aastore
    //   1898: dup
    //   1899: iconst_2
    //   1900: iload #21
    //   1902: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1905: aastore
    //   1906: dup
    //   1907: iconst_3
    //   1908: iload #22
    //   1910: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1913: aastore
    //   1914: dup
    //   1915: iconst_4
    //   1916: iload #6
    //   1918: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1921: aastore
    //   1922: dup
    //   1923: iconst_5
    //   1924: aload_1
    //   1925: getfield dalvikRss : I
    //   1928: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1931: aastore
    //   1932: dup
    //   1933: bipush #6
    //   1935: lload #14
    //   1937: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1940: aastore
    //   1941: dup
    //   1942: bipush #7
    //   1944: lload #16
    //   1946: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1949: aastore
    //   1950: dup
    //   1951: bipush #8
    //   1953: lload #18
    //   1955: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1958: aastore
    //   1959: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   1962: aload_1
    //   1963: getfield otherPss : I
    //   1966: istore #22
    //   1968: aload_1
    //   1969: getfield otherSwappablePss : I
    //   1972: istore #26
    //   1974: aload_1
    //   1975: getfield otherSharedDirty : I
    //   1978: istore #27
    //   1980: aload_1
    //   1981: getfield otherPrivateDirty : I
    //   1984: istore #28
    //   1986: aload_1
    //   1987: getfield otherSharedClean : I
    //   1990: istore #29
    //   1992: aload_1
    //   1993: getfield otherPrivateClean : I
    //   1996: istore #24
    //   1998: aload_1
    //   1999: getfield otherSwappedOut : I
    //   2002: istore #21
    //   2004: aload_1
    //   2005: getfield otherSwappedOutPss : I
    //   2008: istore #6
    //   2010: aload_1
    //   2011: getfield otherRss : I
    //   2014: istore #25
    //   2016: iconst_0
    //   2017: istore #30
    //   2019: iload #30
    //   2021: bipush #17
    //   2023: if_icmpge -> 2530
    //   2026: aload_1
    //   2027: iload #30
    //   2029: invokevirtual getOtherPss : (I)I
    //   2032: istore #31
    //   2034: aload_1
    //   2035: iload #30
    //   2037: invokevirtual getOtherSwappablePss : (I)I
    //   2040: istore #32
    //   2042: aload_1
    //   2043: iload #30
    //   2045: invokevirtual getOtherSharedDirty : (I)I
    //   2048: istore #33
    //   2050: aload_1
    //   2051: iload #30
    //   2053: invokevirtual getOtherPrivateDirty : (I)I
    //   2056: istore #34
    //   2058: aload_1
    //   2059: iload #30
    //   2061: invokevirtual getOtherSharedClean : (I)I
    //   2064: istore #35
    //   2066: aload_1
    //   2067: iload #30
    //   2069: invokevirtual getOtherPrivateClean : (I)I
    //   2072: istore #36
    //   2074: aload_1
    //   2075: iload #30
    //   2077: invokevirtual getOtherSwappedOut : (I)I
    //   2080: istore #20
    //   2082: aload_1
    //   2083: iload #30
    //   2085: invokevirtual getOtherSwappedOutPss : (I)I
    //   2088: istore #23
    //   2090: aload_1
    //   2091: iload #30
    //   2093: invokevirtual getOtherRss : (I)I
    //   2096: istore #37
    //   2098: iload #31
    //   2100: ifne -> 2187
    //   2103: iload #33
    //   2105: ifne -> 2187
    //   2108: iload #34
    //   2110: ifne -> 2187
    //   2113: iload #35
    //   2115: ifne -> 2187
    //   2118: iload #36
    //   2120: ifne -> 2187
    //   2123: iload #37
    //   2125: ifne -> 2187
    //   2128: aload_1
    //   2129: getfield hasSwappedOutPss : Z
    //   2132: ifeq -> 2142
    //   2135: iload #23
    //   2137: istore #38
    //   2139: goto -> 2146
    //   2142: iload #20
    //   2144: istore #38
    //   2146: iload #22
    //   2148: istore #39
    //   2150: iload #25
    //   2152: istore #40
    //   2154: iload #26
    //   2156: istore #41
    //   2158: iload #27
    //   2160: istore #42
    //   2162: iload #28
    //   2164: istore #43
    //   2166: iload #29
    //   2168: istore #44
    //   2170: iload #24
    //   2172: istore #45
    //   2174: iload #21
    //   2176: istore #46
    //   2178: iload #6
    //   2180: istore #47
    //   2182: iload #38
    //   2184: ifeq -> 2488
    //   2187: iload_3
    //   2188: ifeq -> 2323
    //   2191: iload #30
    //   2193: invokestatic getOtherLabel : (I)Ljava/lang/String;
    //   2196: astore #7
    //   2198: aload_1
    //   2199: getfield hasSwappedOutPss : Z
    //   2202: ifeq -> 2212
    //   2205: iload #23
    //   2207: istore #47
    //   2209: goto -> 2216
    //   2212: iload #20
    //   2214: istore #47
    //   2216: aload_0
    //   2217: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   2219: bipush #12
    //   2221: anewarray java/lang/Object
    //   2224: dup
    //   2225: iconst_0
    //   2226: aload #7
    //   2228: aastore
    //   2229: dup
    //   2230: iconst_1
    //   2231: iload #31
    //   2233: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2236: aastore
    //   2237: dup
    //   2238: iconst_2
    //   2239: iload #32
    //   2241: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2244: aastore
    //   2245: dup
    //   2246: iconst_3
    //   2247: iload #33
    //   2249: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2252: aastore
    //   2253: dup
    //   2254: iconst_4
    //   2255: iload #34
    //   2257: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2260: aastore
    //   2261: dup
    //   2262: iconst_5
    //   2263: iload #35
    //   2265: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2268: aastore
    //   2269: dup
    //   2270: bipush #6
    //   2272: iload #36
    //   2274: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2277: aastore
    //   2278: dup
    //   2279: bipush #7
    //   2281: iload #47
    //   2283: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2286: aastore
    //   2287: dup
    //   2288: bipush #8
    //   2290: iload #37
    //   2292: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2295: aastore
    //   2296: dup
    //   2297: bipush #9
    //   2299: ldc_w ''
    //   2302: aastore
    //   2303: dup
    //   2304: bipush #10
    //   2306: ldc_w ''
    //   2309: aastore
    //   2310: dup
    //   2311: bipush #11
    //   2313: ldc_w ''
    //   2316: aastore
    //   2317: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   2320: goto -> 2425
    //   2323: iload #30
    //   2325: invokestatic getOtherLabel : (I)Ljava/lang/String;
    //   2328: astore #7
    //   2330: aload_1
    //   2331: getfield hasSwappedOutPss : Z
    //   2334: ifeq -> 2344
    //   2337: iload #23
    //   2339: istore #47
    //   2341: goto -> 2348
    //   2344: iload #20
    //   2346: istore #47
    //   2348: aload_0
    //   2349: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   2351: bipush #9
    //   2353: anewarray java/lang/Object
    //   2356: dup
    //   2357: iconst_0
    //   2358: aload #7
    //   2360: aastore
    //   2361: dup
    //   2362: iconst_1
    //   2363: iload #31
    //   2365: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2368: aastore
    //   2369: dup
    //   2370: iconst_2
    //   2371: iload #34
    //   2373: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2376: aastore
    //   2377: dup
    //   2378: iconst_3
    //   2379: iload #36
    //   2381: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2384: aastore
    //   2385: dup
    //   2386: iconst_4
    //   2387: iload #47
    //   2389: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2392: aastore
    //   2393: dup
    //   2394: iconst_5
    //   2395: iload #37
    //   2397: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2400: aastore
    //   2401: dup
    //   2402: bipush #6
    //   2404: ldc_w ''
    //   2407: aastore
    //   2408: dup
    //   2409: bipush #7
    //   2411: ldc_w ''
    //   2414: aastore
    //   2415: dup
    //   2416: bipush #8
    //   2418: ldc_w ''
    //   2421: aastore
    //   2422: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   2425: iload #22
    //   2427: iload #31
    //   2429: isub
    //   2430: istore #39
    //   2432: iload #26
    //   2434: iload #32
    //   2436: isub
    //   2437: istore #41
    //   2439: iload #27
    //   2441: iload #33
    //   2443: isub
    //   2444: istore #42
    //   2446: iload #28
    //   2448: iload #34
    //   2450: isub
    //   2451: istore #43
    //   2453: iload #29
    //   2455: iload #35
    //   2457: isub
    //   2458: istore #44
    //   2460: iload #24
    //   2462: iload #36
    //   2464: isub
    //   2465: istore #45
    //   2467: iload #21
    //   2469: iload #20
    //   2471: isub
    //   2472: istore #46
    //   2474: iload #6
    //   2476: iload #23
    //   2478: isub
    //   2479: istore #47
    //   2481: iload #25
    //   2483: iload #37
    //   2485: isub
    //   2486: istore #40
    //   2488: iinc #30, 1
    //   2491: iload #39
    //   2493: istore #22
    //   2495: iload #40
    //   2497: istore #25
    //   2499: iload #41
    //   2501: istore #26
    //   2503: iload #42
    //   2505: istore #27
    //   2507: iload #43
    //   2509: istore #28
    //   2511: iload #44
    //   2513: istore #29
    //   2515: iload #45
    //   2517: istore #24
    //   2519: iload #46
    //   2521: istore #21
    //   2523: iload #47
    //   2525: istore #6
    //   2527: goto -> 2019
    //   2530: iload_3
    //   2531: ifeq -> 2836
    //   2534: aload_1
    //   2535: getfield hasSwappedOutPss : Z
    //   2538: ifeq -> 2544
    //   2541: goto -> 2548
    //   2544: iload #21
    //   2546: istore #6
    //   2548: aload_0
    //   2549: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   2551: bipush #12
    //   2553: anewarray java/lang/Object
    //   2556: dup
    //   2557: iconst_0
    //   2558: ldc_w 'Unknown'
    //   2561: aastore
    //   2562: dup
    //   2563: iconst_1
    //   2564: iload #22
    //   2566: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2569: aastore
    //   2570: dup
    //   2571: iconst_2
    //   2572: iload #26
    //   2574: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2577: aastore
    //   2578: dup
    //   2579: iconst_3
    //   2580: iload #27
    //   2582: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2585: aastore
    //   2586: dup
    //   2587: iconst_4
    //   2588: iload #28
    //   2590: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2593: aastore
    //   2594: dup
    //   2595: iconst_5
    //   2596: iload #29
    //   2598: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2601: aastore
    //   2602: dup
    //   2603: bipush #6
    //   2605: iload #24
    //   2607: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2610: aastore
    //   2611: dup
    //   2612: bipush #7
    //   2614: iload #6
    //   2616: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2619: aastore
    //   2620: dup
    //   2621: bipush #8
    //   2623: iload #25
    //   2625: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2628: aastore
    //   2629: dup
    //   2630: bipush #9
    //   2632: ldc_w ''
    //   2635: aastore
    //   2636: dup
    //   2637: bipush #10
    //   2639: ldc_w ''
    //   2642: aastore
    //   2643: dup
    //   2644: bipush #11
    //   2646: ldc_w ''
    //   2649: aastore
    //   2650: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   2653: aload_1
    //   2654: invokevirtual getTotalPss : ()I
    //   2657: istore #28
    //   2659: aload_1
    //   2660: invokevirtual getTotalSwappablePss : ()I
    //   2663: istore #29
    //   2665: aload_1
    //   2666: invokevirtual getTotalSharedDirty : ()I
    //   2669: istore #24
    //   2671: aload_1
    //   2672: invokevirtual getTotalPrivateDirty : ()I
    //   2675: istore #21
    //   2677: aload_1
    //   2678: invokevirtual getTotalSharedClean : ()I
    //   2681: istore #20
    //   2683: aload_1
    //   2684: invokevirtual getTotalPrivateClean : ()I
    //   2687: istore #23
    //   2689: aload_1
    //   2690: getfield hasSwappedOutPss : Z
    //   2693: ifeq -> 2705
    //   2696: aload_1
    //   2697: invokevirtual getTotalSwappedOutPss : ()I
    //   2700: istore #6
    //   2702: goto -> 2711
    //   2705: aload_1
    //   2706: invokevirtual getTotalSwappedOut : ()I
    //   2709: istore #6
    //   2711: aload_0
    //   2712: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   2714: bipush #12
    //   2716: anewarray java/lang/Object
    //   2719: dup
    //   2720: iconst_0
    //   2721: ldc_w 'TOTAL'
    //   2724: aastore
    //   2725: dup
    //   2726: iconst_1
    //   2727: iload #28
    //   2729: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2732: aastore
    //   2733: dup
    //   2734: iconst_2
    //   2735: iload #29
    //   2737: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2740: aastore
    //   2741: dup
    //   2742: iconst_3
    //   2743: iload #24
    //   2745: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2748: aastore
    //   2749: dup
    //   2750: iconst_4
    //   2751: iload #21
    //   2753: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2756: aastore
    //   2757: dup
    //   2758: iconst_5
    //   2759: iload #20
    //   2761: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2764: aastore
    //   2765: dup
    //   2766: bipush #6
    //   2768: iload #23
    //   2770: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2773: aastore
    //   2774: dup
    //   2775: bipush #7
    //   2777: iload #6
    //   2779: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2782: aastore
    //   2783: dup
    //   2784: bipush #8
    //   2786: aload_1
    //   2787: invokevirtual getTotalRss : ()I
    //   2790: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2793: aastore
    //   2794: dup
    //   2795: bipush #9
    //   2797: lload #8
    //   2799: lload #14
    //   2801: ladd
    //   2802: invokestatic valueOf : (J)Ljava/lang/Long;
    //   2805: aastore
    //   2806: dup
    //   2807: bipush #10
    //   2809: lload #10
    //   2811: lload #16
    //   2813: ladd
    //   2814: invokestatic valueOf : (J)Ljava/lang/Long;
    //   2817: aastore
    //   2818: dup
    //   2819: bipush #11
    //   2821: lload #12
    //   2823: lload #18
    //   2825: ladd
    //   2826: invokestatic valueOf : (J)Ljava/lang/Long;
    //   2829: aastore
    //   2830: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   2833: goto -> 3063
    //   2836: aload_1
    //   2837: getfield hasSwappedOutPss : Z
    //   2840: ifeq -> 2850
    //   2843: iload #6
    //   2845: istore #21
    //   2847: goto -> 2850
    //   2850: aload_0
    //   2851: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   2853: bipush #9
    //   2855: anewarray java/lang/Object
    //   2858: dup
    //   2859: iconst_0
    //   2860: ldc_w 'Unknown'
    //   2863: aastore
    //   2864: dup
    //   2865: iconst_1
    //   2866: iload #22
    //   2868: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2871: aastore
    //   2872: dup
    //   2873: iconst_2
    //   2874: iload #28
    //   2876: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2879: aastore
    //   2880: dup
    //   2881: iconst_3
    //   2882: iload #24
    //   2884: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2887: aastore
    //   2888: dup
    //   2889: iconst_4
    //   2890: iload #21
    //   2892: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2895: aastore
    //   2896: dup
    //   2897: iconst_5
    //   2898: iload #25
    //   2900: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2903: aastore
    //   2904: dup
    //   2905: bipush #6
    //   2907: ldc_w ''
    //   2910: aastore
    //   2911: dup
    //   2912: bipush #7
    //   2914: ldc_w ''
    //   2917: aastore
    //   2918: dup
    //   2919: bipush #8
    //   2921: ldc_w ''
    //   2924: aastore
    //   2925: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   2928: aload_1
    //   2929: invokevirtual getTotalPss : ()I
    //   2932: istore #23
    //   2934: aload_1
    //   2935: invokevirtual getTotalPrivateDirty : ()I
    //   2938: istore #21
    //   2940: aload_1
    //   2941: invokevirtual getTotalPrivateClean : ()I
    //   2944: istore #20
    //   2946: aload_1
    //   2947: getfield hasSwappedOutPss : Z
    //   2950: ifeq -> 2962
    //   2953: aload_1
    //   2954: invokevirtual getTotalSwappedOutPss : ()I
    //   2957: istore #6
    //   2959: goto -> 2968
    //   2962: aload_1
    //   2963: invokevirtual getTotalSwappedOut : ()I
    //   2966: istore #6
    //   2968: aload_0
    //   2969: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   2971: bipush #9
    //   2973: anewarray java/lang/Object
    //   2976: dup
    //   2977: iconst_0
    //   2978: ldc_w 'TOTAL'
    //   2981: aastore
    //   2982: dup
    //   2983: iconst_1
    //   2984: iload #23
    //   2986: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2989: aastore
    //   2990: dup
    //   2991: iconst_2
    //   2992: iload #21
    //   2994: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   2997: aastore
    //   2998: dup
    //   2999: iconst_3
    //   3000: iload #20
    //   3002: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3005: aastore
    //   3006: dup
    //   3007: iconst_4
    //   3008: iload #6
    //   3010: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3013: aastore
    //   3014: dup
    //   3015: iconst_5
    //   3016: aload_1
    //   3017: invokevirtual getTotalPss : ()I
    //   3020: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3023: aastore
    //   3024: dup
    //   3025: bipush #6
    //   3027: lload #8
    //   3029: lload #14
    //   3031: ladd
    //   3032: invokestatic valueOf : (J)Ljava/lang/Long;
    //   3035: aastore
    //   3036: dup
    //   3037: bipush #7
    //   3039: lload #10
    //   3041: lload #16
    //   3043: ladd
    //   3044: invokestatic valueOf : (J)Ljava/lang/Long;
    //   3047: aastore
    //   3048: dup
    //   3049: bipush #8
    //   3051: lload #12
    //   3053: lload #18
    //   3055: ladd
    //   3056: invokestatic valueOf : (J)Ljava/lang/Long;
    //   3059: aastore
    //   3060: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3063: iload #4
    //   3065: ifeq -> 3458
    //   3068: aload_0
    //   3069: ldc_w ' '
    //   3072: invokevirtual println : (Ljava/lang/String;)V
    //   3075: aload_0
    //   3076: ldc_w ' Dalvik Details'
    //   3079: invokevirtual println : (Ljava/lang/String;)V
    //   3082: bipush #17
    //   3084: istore #23
    //   3086: iload #23
    //   3088: bipush #32
    //   3090: if_icmpge -> 3455
    //   3093: aload_1
    //   3094: iload #23
    //   3096: invokevirtual getOtherPss : (I)I
    //   3099: istore #29
    //   3101: aload_1
    //   3102: iload #23
    //   3104: invokevirtual getOtherSwappablePss : (I)I
    //   3107: istore #27
    //   3109: aload_1
    //   3110: iload #23
    //   3112: invokevirtual getOtherSharedDirty : (I)I
    //   3115: istore #26
    //   3117: aload_1
    //   3118: iload #23
    //   3120: invokevirtual getOtherPrivateDirty : (I)I
    //   3123: istore #30
    //   3125: aload_1
    //   3126: iload #23
    //   3128: invokevirtual getOtherSharedClean : (I)I
    //   3131: istore #47
    //   3133: aload_1
    //   3134: iload #23
    //   3136: invokevirtual getOtherPrivateClean : (I)I
    //   3139: istore #24
    //   3141: aload_1
    //   3142: iload #23
    //   3144: invokevirtual getOtherSwappedOut : (I)I
    //   3147: istore #6
    //   3149: aload_1
    //   3150: iload #23
    //   3152: invokevirtual getOtherSwappedOutPss : (I)I
    //   3155: istore #21
    //   3157: aload_1
    //   3158: iload #23
    //   3160: invokevirtual getOtherRss : (I)I
    //   3163: istore #28
    //   3165: iload #29
    //   3167: ifne -> 3219
    //   3170: iload #26
    //   3172: ifne -> 3219
    //   3175: iload #30
    //   3177: ifne -> 3219
    //   3180: iload #47
    //   3182: ifne -> 3219
    //   3185: iload #24
    //   3187: ifne -> 3219
    //   3190: aload_1
    //   3191: getfield hasSwappedOutPss : Z
    //   3194: ifeq -> 3204
    //   3197: iload #21
    //   3199: istore #20
    //   3201: goto -> 3208
    //   3204: iload #6
    //   3206: istore #20
    //   3208: iload #20
    //   3210: ifeq -> 3216
    //   3213: goto -> 3219
    //   3216: goto -> 3449
    //   3219: iload_3
    //   3220: ifeq -> 3351
    //   3223: iload #23
    //   3225: invokestatic getOtherLabel : (I)Ljava/lang/String;
    //   3228: astore #7
    //   3230: aload_1
    //   3231: getfield hasSwappedOutPss : Z
    //   3234: ifeq -> 3244
    //   3237: iload #21
    //   3239: istore #6
    //   3241: goto -> 3244
    //   3244: aload_0
    //   3245: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   3247: bipush #12
    //   3249: anewarray java/lang/Object
    //   3252: dup
    //   3253: iconst_0
    //   3254: aload #7
    //   3256: aastore
    //   3257: dup
    //   3258: iconst_1
    //   3259: iload #29
    //   3261: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3264: aastore
    //   3265: dup
    //   3266: iconst_2
    //   3267: iload #27
    //   3269: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3272: aastore
    //   3273: dup
    //   3274: iconst_3
    //   3275: iload #26
    //   3277: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3280: aastore
    //   3281: dup
    //   3282: iconst_4
    //   3283: iload #30
    //   3285: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3288: aastore
    //   3289: dup
    //   3290: iconst_5
    //   3291: iload #47
    //   3293: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3296: aastore
    //   3297: dup
    //   3298: bipush #6
    //   3300: iload #24
    //   3302: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3305: aastore
    //   3306: dup
    //   3307: bipush #7
    //   3309: iload #6
    //   3311: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3314: aastore
    //   3315: dup
    //   3316: bipush #8
    //   3318: iload #28
    //   3320: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3323: aastore
    //   3324: dup
    //   3325: bipush #9
    //   3327: ldc_w ''
    //   3330: aastore
    //   3331: dup
    //   3332: bipush #10
    //   3334: ldc_w ''
    //   3337: aastore
    //   3338: dup
    //   3339: bipush #11
    //   3341: ldc_w ''
    //   3344: aastore
    //   3345: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3348: goto -> 3449
    //   3351: iload #23
    //   3353: invokestatic getOtherLabel : (I)Ljava/lang/String;
    //   3356: astore #7
    //   3358: aload_1
    //   3359: getfield hasSwappedOutPss : Z
    //   3362: ifeq -> 3372
    //   3365: iload #21
    //   3367: istore #6
    //   3369: goto -> 3372
    //   3372: aload_0
    //   3373: ldc '%13s %8s %8s %8s %8s %8s %8s %8s %8s'
    //   3375: bipush #9
    //   3377: anewarray java/lang/Object
    //   3380: dup
    //   3381: iconst_0
    //   3382: aload #7
    //   3384: aastore
    //   3385: dup
    //   3386: iconst_1
    //   3387: iload #29
    //   3389: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3392: aastore
    //   3393: dup
    //   3394: iconst_2
    //   3395: iload #30
    //   3397: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3400: aastore
    //   3401: dup
    //   3402: iconst_3
    //   3403: iload #24
    //   3405: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3408: aastore
    //   3409: dup
    //   3410: iconst_4
    //   3411: iload #6
    //   3413: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3416: aastore
    //   3417: dup
    //   3418: iconst_5
    //   3419: iload #28
    //   3421: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3424: aastore
    //   3425: dup
    //   3426: bipush #6
    //   3428: ldc_w ''
    //   3431: aastore
    //   3432: dup
    //   3433: bipush #7
    //   3435: ldc_w ''
    //   3438: aastore
    //   3439: dup
    //   3440: bipush #8
    //   3442: ldc_w ''
    //   3445: aastore
    //   3446: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3449: iinc #23, 1
    //   3452: goto -> 3086
    //   3455: goto -> 3461
    //   3458: goto -> 3461
    //   3461: aload_0
    //   3462: ldc_w ' '
    //   3465: invokevirtual println : (Ljava/lang/String;)V
    //   3468: aload_0
    //   3469: ldc_w ' App Summary'
    //   3472: invokevirtual println : (Ljava/lang/String;)V
    //   3475: aload_0
    //   3476: ldc '%21s %8s %21s %8s'
    //   3478: iconst_4
    //   3479: anewarray java/lang/Object
    //   3482: dup
    //   3483: iconst_0
    //   3484: ldc_w ''
    //   3487: aastore
    //   3488: dup
    //   3489: iconst_1
    //   3490: ldc_w 'Pss(KB)'
    //   3493: aastore
    //   3494: dup
    //   3495: iconst_2
    //   3496: ldc_w ''
    //   3499: aastore
    //   3500: dup
    //   3501: iconst_3
    //   3502: ldc_w 'Rss(KB)'
    //   3505: aastore
    //   3506: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3509: aload_0
    //   3510: ldc '%21s %8s %21s %8s'
    //   3512: iconst_4
    //   3513: anewarray java/lang/Object
    //   3516: dup
    //   3517: iconst_0
    //   3518: ldc_w ''
    //   3521: aastore
    //   3522: dup
    //   3523: iconst_1
    //   3524: ldc_w '------'
    //   3527: aastore
    //   3528: dup
    //   3529: iconst_2
    //   3530: ldc_w ''
    //   3533: aastore
    //   3534: dup
    //   3535: iconst_3
    //   3536: ldc_w '------'
    //   3539: aastore
    //   3540: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3543: aload_0
    //   3544: ldc '%21s %8d %21s %8d'
    //   3546: iconst_4
    //   3547: anewarray java/lang/Object
    //   3550: dup
    //   3551: iconst_0
    //   3552: ldc_w 'Java Heap:'
    //   3555: aastore
    //   3556: dup
    //   3557: iconst_1
    //   3558: aload_1
    //   3559: invokevirtual getSummaryJavaHeap : ()I
    //   3562: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3565: aastore
    //   3566: dup
    //   3567: iconst_2
    //   3568: ldc_w ''
    //   3571: aastore
    //   3572: dup
    //   3573: iconst_3
    //   3574: aload_1
    //   3575: invokevirtual getSummaryJavaHeapRss : ()I
    //   3578: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3581: aastore
    //   3582: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3585: aload_0
    //   3586: ldc '%21s %8d %21s %8d'
    //   3588: iconst_4
    //   3589: anewarray java/lang/Object
    //   3592: dup
    //   3593: iconst_0
    //   3594: ldc_w 'Native Heap:'
    //   3597: aastore
    //   3598: dup
    //   3599: iconst_1
    //   3600: aload_1
    //   3601: invokevirtual getSummaryNativeHeap : ()I
    //   3604: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3607: aastore
    //   3608: dup
    //   3609: iconst_2
    //   3610: ldc_w ''
    //   3613: aastore
    //   3614: dup
    //   3615: iconst_3
    //   3616: aload_1
    //   3617: invokevirtual getSummaryNativeHeapRss : ()I
    //   3620: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3623: aastore
    //   3624: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3627: aload_0
    //   3628: ldc '%21s %8d %21s %8d'
    //   3630: iconst_4
    //   3631: anewarray java/lang/Object
    //   3634: dup
    //   3635: iconst_0
    //   3636: ldc_w 'Code:'
    //   3639: aastore
    //   3640: dup
    //   3641: iconst_1
    //   3642: aload_1
    //   3643: invokevirtual getSummaryCode : ()I
    //   3646: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3649: aastore
    //   3650: dup
    //   3651: iconst_2
    //   3652: ldc_w ''
    //   3655: aastore
    //   3656: dup
    //   3657: iconst_3
    //   3658: aload_1
    //   3659: invokevirtual getSummaryCodeRss : ()I
    //   3662: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3665: aastore
    //   3666: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3669: aload_0
    //   3670: ldc '%21s %8d %21s %8d'
    //   3672: iconst_4
    //   3673: anewarray java/lang/Object
    //   3676: dup
    //   3677: iconst_0
    //   3678: ldc_w 'Stack:'
    //   3681: aastore
    //   3682: dup
    //   3683: iconst_1
    //   3684: aload_1
    //   3685: invokevirtual getSummaryStack : ()I
    //   3688: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3691: aastore
    //   3692: dup
    //   3693: iconst_2
    //   3694: ldc_w ''
    //   3697: aastore
    //   3698: dup
    //   3699: iconst_3
    //   3700: aload_1
    //   3701: invokevirtual getSummaryStackRss : ()I
    //   3704: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3707: aastore
    //   3708: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3711: aload_0
    //   3712: ldc '%21s %8d %21s %8d'
    //   3714: iconst_4
    //   3715: anewarray java/lang/Object
    //   3718: dup
    //   3719: iconst_0
    //   3720: ldc_w 'Graphics:'
    //   3723: aastore
    //   3724: dup
    //   3725: iconst_1
    //   3726: aload_1
    //   3727: invokevirtual getSummaryGraphics : ()I
    //   3730: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3733: aastore
    //   3734: dup
    //   3735: iconst_2
    //   3736: ldc_w ''
    //   3739: aastore
    //   3740: dup
    //   3741: iconst_3
    //   3742: aload_1
    //   3743: invokevirtual getSummaryGraphicsRss : ()I
    //   3746: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3749: aastore
    //   3750: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3753: aload_0
    //   3754: ldc '%21s %8d'
    //   3756: iconst_2
    //   3757: anewarray java/lang/Object
    //   3760: dup
    //   3761: iconst_0
    //   3762: ldc_w 'Private Other:'
    //   3765: aastore
    //   3766: dup
    //   3767: iconst_1
    //   3768: aload_1
    //   3769: invokevirtual getSummaryPrivateOther : ()I
    //   3772: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3775: aastore
    //   3776: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3779: aload_0
    //   3780: ldc '%21s %8d'
    //   3782: iconst_2
    //   3783: anewarray java/lang/Object
    //   3786: dup
    //   3787: iconst_0
    //   3788: ldc_w 'System:'
    //   3791: aastore
    //   3792: dup
    //   3793: iconst_1
    //   3794: aload_1
    //   3795: invokevirtual getSummarySystem : ()I
    //   3798: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3801: aastore
    //   3802: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3805: aload_0
    //   3806: ldc '%21s %8s %21s %8d'
    //   3808: iconst_4
    //   3809: anewarray java/lang/Object
    //   3812: dup
    //   3813: iconst_0
    //   3814: ldc_w 'Unknown:'
    //   3817: aastore
    //   3818: dup
    //   3819: iconst_1
    //   3820: ldc_w ''
    //   3823: aastore
    //   3824: dup
    //   3825: iconst_2
    //   3826: ldc_w ''
    //   3829: aastore
    //   3830: dup
    //   3831: iconst_3
    //   3832: aload_1
    //   3833: invokevirtual getSummaryUnknownRss : ()I
    //   3836: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3839: aastore
    //   3840: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3843: aload_0
    //   3844: ldc_w ' '
    //   3847: invokevirtual println : (Ljava/lang/String;)V
    //   3850: aload_1
    //   3851: getfield hasSwappedOutPss : Z
    //   3854: ifeq -> 3919
    //   3857: aload_0
    //   3858: ldc '%21s %8d %21s %8s %21s %8d'
    //   3860: bipush #6
    //   3862: anewarray java/lang/Object
    //   3865: dup
    //   3866: iconst_0
    //   3867: ldc_w 'TOTAL PSS:'
    //   3870: aastore
    //   3871: dup
    //   3872: iconst_1
    //   3873: aload_1
    //   3874: invokevirtual getSummaryTotalPss : ()I
    //   3877: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3880: aastore
    //   3881: dup
    //   3882: iconst_2
    //   3883: ldc_w 'TOTAL RSS:'
    //   3886: aastore
    //   3887: dup
    //   3888: iconst_3
    //   3889: aload_1
    //   3890: invokevirtual getTotalRss : ()I
    //   3893: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3896: aastore
    //   3897: dup
    //   3898: iconst_4
    //   3899: ldc_w 'TOTAL SWAP PSS:'
    //   3902: aastore
    //   3903: dup
    //   3904: iconst_5
    //   3905: aload_1
    //   3906: invokevirtual getSummaryTotalSwapPss : ()I
    //   3909: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3912: aastore
    //   3913: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3916: goto -> 3978
    //   3919: aload_0
    //   3920: ldc '%21s %8d %21s %8s %21s %8d'
    //   3922: bipush #6
    //   3924: anewarray java/lang/Object
    //   3927: dup
    //   3928: iconst_0
    //   3929: ldc_w 'TOTAL PSS:'
    //   3932: aastore
    //   3933: dup
    //   3934: iconst_1
    //   3935: aload_1
    //   3936: invokevirtual getSummaryTotalPss : ()I
    //   3939: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3942: aastore
    //   3943: dup
    //   3944: iconst_2
    //   3945: ldc_w 'TOTAL RSS:'
    //   3948: aastore
    //   3949: dup
    //   3950: iconst_3
    //   3951: aload_1
    //   3952: invokevirtual getTotalRss : ()I
    //   3955: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3958: aastore
    //   3959: dup
    //   3960: iconst_4
    //   3961: ldc_w 'TOTAL SWAP (KB):'
    //   3964: aastore
    //   3965: dup
    //   3966: iconst_5
    //   3967: aload_1
    //   3968: invokevirtual getSummaryTotalSwap : ()I
    //   3971: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   3974: aastore
    //   3975: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   3978: return
  }
  
  private static void dumpMemoryInfo(ProtoOutputStream paramProtoOutputStream, long paramLong, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, int paramInt7, int paramInt8, int paramInt9) {
    paramLong = paramProtoOutputStream.start(paramLong);
    paramProtoOutputStream.write(1138166333441L, paramString);
    paramProtoOutputStream.write(1120986464258L, paramInt1);
    paramProtoOutputStream.write(1120986464259L, paramInt2);
    paramProtoOutputStream.write(1120986464260L, paramInt3);
    paramProtoOutputStream.write(1120986464261L, paramInt4);
    paramProtoOutputStream.write(1120986464262L, paramInt5);
    paramProtoOutputStream.write(1120986464263L, paramInt6);
    if (paramBoolean) {
      paramProtoOutputStream.write(1120986464265L, paramInt8);
    } else {
      paramProtoOutputStream.write(1120986464264L, paramInt7);
    } 
    paramProtoOutputStream.write(1120986464266L, paramInt9);
    paramProtoOutputStream.end(paramLong);
  }
  
  static void freeTextLayoutCachesIfNeeded(int paramInt) {
    if (paramInt != 0) {
      if ((paramInt & 0x4) != 0) {
        paramInt = 1;
      } else {
        paramInt = 0;
      } 
      if (paramInt != 0)
        Canvas.freeTextLayoutCaches(); 
    } 
  }
  
  private ArrayMap<String, BackupAgent> getBackupAgentsForUser(int paramInt) {
    ArrayMap<String, BackupAgent> arrayMap1 = (ArrayMap)this.mBackupAgentsByUser.get(paramInt);
    ArrayMap<String, BackupAgent> arrayMap2 = arrayMap1;
    if (arrayMap1 == null) {
      arrayMap2 = new ArrayMap();
      this.mBackupAgentsByUser.put(paramInt, arrayMap2);
    } 
    return arrayMap2;
  }
  
  private Object getGetProviderLock(String paramString, int paramInt) {
    ProviderKey providerKey = new ProviderKey(paramString, paramInt);
    synchronized (this.mGetProviderLocks) {
      Object object2 = this.mGetProviderLocks.get(providerKey);
      Object object1 = object2;
      if (object2 == null) {
        object1 = providerKey;
        this.mGetProviderLocks.put(providerKey, object1);
      } 
      return object1;
    } 
  }
  
  private String getInstrumentationLibrary(ApplicationInfo paramApplicationInfo, InstrumentationInfo paramInstrumentationInfo) {
    if (paramApplicationInfo.primaryCpuAbi != null && paramApplicationInfo.secondaryCpuAbi != null && paramApplicationInfo.secondaryCpuAbi.equals(paramInstrumentationInfo.secondaryCpuAbi)) {
      String str1 = VMRuntime.getInstructionSet(paramApplicationInfo.secondaryCpuAbi);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ro.dalvik.vm.isa.");
      stringBuilder.append(str1);
      String str2 = SystemProperties.get(stringBuilder.toString());
      if (!str2.isEmpty())
        str1 = str2; 
      if (VMRuntime.getRuntime().vmInstructionSet().equals(str1))
        return paramInstrumentationInfo.secondaryNativeLibraryDir; 
    } 
    return paramInstrumentationInfo.nativeLibraryDir;
  }
  
  public static Intent getIntentBeingBroadcast() {
    return sCurrentBroadcastIntent.get();
  }
  
  private LoadedApk getPackageInfo(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo, ClassLoader paramClassLoader, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    // Byte code:
    //   0: invokestatic myUserId : ()I
    //   3: aload_1
    //   4: getfield uid : I
    //   7: invokestatic getUserId : (I)I
    //   10: if_icmpeq -> 19
    //   13: iconst_1
    //   14: istore #7
    //   16: goto -> 22
    //   19: iconst_0
    //   20: istore #7
    //   22: aload_0
    //   23: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   26: astore #8
    //   28: aload #8
    //   30: monitorenter
    //   31: iload #7
    //   33: ifeq -> 42
    //   36: aconst_null
    //   37: astore #9
    //   39: goto -> 82
    //   42: iload #5
    //   44: ifeq -> 66
    //   47: aload_0
    //   48: getfield mPackages : Landroid/util/ArrayMap;
    //   51: aload_1
    //   52: getfield packageName : Ljava/lang/String;
    //   55: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   58: checkcast java/lang/ref/WeakReference
    //   61: astore #9
    //   63: goto -> 82
    //   66: aload_0
    //   67: getfield mResourcePackages : Landroid/util/ArrayMap;
    //   70: aload_1
    //   71: getfield packageName : Ljava/lang/String;
    //   74: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   77: checkcast java/lang/ref/WeakReference
    //   80: astore #9
    //   82: aload #9
    //   84: ifnull -> 100
    //   87: aload #9
    //   89: invokevirtual get : ()Ljava/lang/Object;
    //   92: checkcast android/app/LoadedApk
    //   95: astore #9
    //   97: goto -> 103
    //   100: aconst_null
    //   101: astore #9
    //   103: aload #9
    //   105: ifnull -> 144
    //   108: aload #9
    //   110: aload_1
    //   111: invokestatic isLoadedApkResourceDirsUpToDate : (Landroid/app/LoadedApk;Landroid/content/pm/ApplicationInfo;)Z
    //   114: ifne -> 138
    //   117: new java/util/ArrayList
    //   120: astore_2
    //   121: aload_2
    //   122: invokespecial <init> : ()V
    //   125: aload_0
    //   126: aload_1
    //   127: aload_2
    //   128: invokestatic makePaths : (Landroid/app/ActivityThread;Landroid/content/pm/ApplicationInfo;Ljava/util/List;)V
    //   131: aload #9
    //   133: aload_1
    //   134: aload_2
    //   135: invokevirtual updateApplicationInfo : (Landroid/content/pm/ApplicationInfo;Ljava/util/List;)V
    //   138: aload #8
    //   140: monitorexit
    //   141: aload #9
    //   143: areturn
    //   144: new android/app/LoadedApk
    //   147: astore #9
    //   149: iload #5
    //   151: ifeq -> 169
    //   154: aload_1
    //   155: getfield flags : I
    //   158: iconst_4
    //   159: iand
    //   160: ifeq -> 169
    //   163: iconst_1
    //   164: istore #10
    //   166: goto -> 172
    //   169: iconst_0
    //   170: istore #10
    //   172: aload #9
    //   174: aload_0
    //   175: aload_1
    //   176: aload_2
    //   177: aload_3
    //   178: iload #4
    //   180: iload #10
    //   182: iload #6
    //   184: invokespecial <init> : (Landroid/app/ActivityThread;Landroid/content/pm/ApplicationInfo;Landroid/content/res/CompatibilityInfo;Ljava/lang/ClassLoader;ZZZ)V
    //   187: aload_0
    //   188: getfield mSystemThread : Z
    //   191: ifeq -> 223
    //   194: ldc_w 'android'
    //   197: aload_1
    //   198: getfield packageName : Ljava/lang/String;
    //   201: invokevirtual equals : (Ljava/lang/Object;)Z
    //   204: ifeq -> 223
    //   207: aload #9
    //   209: aload_1
    //   210: aload_0
    //   211: invokevirtual getSystemContext : ()Landroid/app/ContextImpl;
    //   214: getfield mPackageInfo : Landroid/app/LoadedApk;
    //   217: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   220: invokevirtual installSystemApplicationInfo : (Landroid/content/pm/ApplicationInfo;Ljava/lang/ClassLoader;)V
    //   223: iload #7
    //   225: ifeq -> 231
    //   228: goto -> 293
    //   231: iload #5
    //   233: ifeq -> 266
    //   236: aload_0
    //   237: getfield mPackages : Landroid/util/ArrayMap;
    //   240: astore_2
    //   241: aload_1
    //   242: getfield packageName : Ljava/lang/String;
    //   245: astore_3
    //   246: new java/lang/ref/WeakReference
    //   249: astore_1
    //   250: aload_1
    //   251: aload #9
    //   253: invokespecial <init> : (Ljava/lang/Object;)V
    //   256: aload_2
    //   257: aload_3
    //   258: aload_1
    //   259: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   262: pop
    //   263: goto -> 293
    //   266: aload_0
    //   267: getfield mResourcePackages : Landroid/util/ArrayMap;
    //   270: astore_2
    //   271: aload_1
    //   272: getfield packageName : Ljava/lang/String;
    //   275: astore_3
    //   276: new java/lang/ref/WeakReference
    //   279: astore_1
    //   280: aload_1
    //   281: aload #9
    //   283: invokespecial <init> : (Ljava/lang/Object;)V
    //   286: aload_2
    //   287: aload_3
    //   288: aload_1
    //   289: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   292: pop
    //   293: aload #8
    //   295: monitorexit
    //   296: aload #9
    //   298: areturn
    //   299: astore_1
    //   300: aload #8
    //   302: monitorexit
    //   303: aload_1
    //   304: athrow
    // Exception table:
    //   from	to	target	type
    //   47	63	299	finally
    //   66	82	299	finally
    //   87	97	299	finally
    //   108	138	299	finally
    //   138	141	299	finally
    //   144	149	299	finally
    //   154	163	299	finally
    //   172	207	299	finally
    //   207	223	299	finally
    //   236	263	299	finally
    //   266	293	299	finally
    //   293	296	299	finally
    //   300	303	299	finally
  }
  
  public static IPackageManager getPackageManager() {
    if (sPackageManager != null)
      return sPackageManager; 
    sPackageManager = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
    return sPackageManager;
  }
  
  public static IPermissionManager getPermissionManager() {
    if (sPermissionManager != null)
      return sPermissionManager; 
    sPermissionManager = IPermissionManager.Stub.asInterface(ServiceManager.getService("permissionmgr"));
    return sPermissionManager;
  }
  
  static void handleAttachAgent(String paramString, LoadedApk paramLoadedApk) {
    if (paramLoadedApk != null) {
      ClassLoader classLoader = paramLoadedApk.getClassLoader();
    } else {
      paramLoadedApk = null;
    } 
    if (attemptAttachAgent(paramString, (ClassLoader)paramLoadedApk))
      return; 
    if (paramLoadedApk != null)
      attemptAttachAgent(paramString, (ClassLoader)null); 
  }
  
  static void handleAttachStartupAgents(String paramString) {
    try {
      File file = new File();
      this(paramString);
      path = ContextImpl.getCodeCacheDirBeforeBind(file).toPath();
      if (!Files.exists(path, new java.nio.file.LinkOption[0]))
        return; 
      path = path.resolve("startup_agents");
      if (Files.exists(path, new java.nio.file.LinkOption[0]))
        for (Path path : Files.newDirectoryStream(path)) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(path.toAbsolutePath().toString());
          stringBuilder.append("=");
          stringBuilder.append(paramString);
          handleAttachAgent(stringBuilder.toString(), (LoadedApk)null);
        }  
    } catch (Exception exception) {}
  }
  
  private void handleBindApplication(AppBindData paramAppBindData) {
    // Byte code:
    //   0: invokestatic registerSensitiveThread : ()V
    //   3: ldc_w 'debug.allocTracker.stackDepth'
    //   6: invokestatic get : (Ljava/lang/String;)Ljava/lang/String;
    //   9: astore_2
    //   10: aload_2
    //   11: invokevirtual length : ()I
    //   14: ifeq -> 24
    //   17: aload_2
    //   18: invokestatic parseInt : (Ljava/lang/String;)I
    //   21: invokestatic setAllocTrackerStackDepth : (I)V
    //   24: aload_1
    //   25: getfield trackAllocation : Z
    //   28: ifeq -> 35
    //   31: iconst_1
    //   32: invokestatic enableRecentAllocations : (Z)V
    //   35: invokestatic elapsedRealtime : ()J
    //   38: invokestatic uptimeMillis : ()J
    //   41: invokestatic setStartTimes : (JJ)V
    //   44: aload_1
    //   45: getfield disabledCompatChanges : [J
    //   48: invokestatic install : ([J)V
    //   51: aload_0
    //   52: aload_1
    //   53: putfield mBoundApplication : Landroid/app/ActivityThread$AppBindData;
    //   56: aload_0
    //   57: new android/content/res/Configuration
    //   60: dup
    //   61: aload_1
    //   62: getfield config : Landroid/content/res/Configuration;
    //   65: invokespecial <init> : (Landroid/content/res/Configuration;)V
    //   68: putfield mConfiguration : Landroid/content/res/Configuration;
    //   71: aload_0
    //   72: new android/content/res/Configuration
    //   75: dup
    //   76: aload_1
    //   77: getfield config : Landroid/content/res/Configuration;
    //   80: invokespecial <init> : (Landroid/content/res/Configuration;)V
    //   83: putfield mCompatConfiguration : Landroid/content/res/Configuration;
    //   86: aload_0
    //   87: new android/app/ActivityThread$Profiler
    //   90: dup
    //   91: invokespecial <init> : ()V
    //   94: putfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   97: aload_1
    //   98: getfield initProfilerInfo : Landroid/app/ProfilerInfo;
    //   101: ifnull -> 195
    //   104: aload_0
    //   105: getfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   108: aload_1
    //   109: getfield initProfilerInfo : Landroid/app/ProfilerInfo;
    //   112: getfield profileFile : Ljava/lang/String;
    //   115: putfield profileFile : Ljava/lang/String;
    //   118: aload_0
    //   119: getfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   122: aload_1
    //   123: getfield initProfilerInfo : Landroid/app/ProfilerInfo;
    //   126: getfield profileFd : Landroid/os/ParcelFileDescriptor;
    //   129: putfield profileFd : Landroid/os/ParcelFileDescriptor;
    //   132: aload_0
    //   133: getfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   136: aload_1
    //   137: getfield initProfilerInfo : Landroid/app/ProfilerInfo;
    //   140: getfield samplingInterval : I
    //   143: putfield samplingInterval : I
    //   146: aload_0
    //   147: getfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   150: aload_1
    //   151: getfield initProfilerInfo : Landroid/app/ProfilerInfo;
    //   154: getfield autoStopProfiler : Z
    //   157: putfield autoStopProfiler : Z
    //   160: aload_0
    //   161: getfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   164: aload_1
    //   165: getfield initProfilerInfo : Landroid/app/ProfilerInfo;
    //   168: getfield streamingOutput : Z
    //   171: putfield streamingOutput : Z
    //   174: aload_1
    //   175: getfield initProfilerInfo : Landroid/app/ProfilerInfo;
    //   178: getfield attachAgentDuringBind : Z
    //   181: ifeq -> 195
    //   184: aload_1
    //   185: getfield initProfilerInfo : Landroid/app/ProfilerInfo;
    //   188: getfield agent : Ljava/lang/String;
    //   191: astore_2
    //   192: goto -> 197
    //   195: aconst_null
    //   196: astore_2
    //   197: aload_1
    //   198: getfield processName : Ljava/lang/String;
    //   201: invokestatic setArgV0 : (Ljava/lang/String;)V
    //   204: aload_1
    //   205: getfield processName : Ljava/lang/String;
    //   208: aload_1
    //   209: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   212: getfield packageName : Ljava/lang/String;
    //   215: invokestatic myUserId : ()I
    //   218: invokestatic setAppName : (Ljava/lang/String;Ljava/lang/String;I)V
    //   221: aload_1
    //   222: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   225: getfield packageName : Ljava/lang/String;
    //   228: invokestatic setProcessPackageName : (Ljava/lang/String;)V
    //   231: aload_1
    //   232: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   235: getfield dataDir : Ljava/lang/String;
    //   238: invokestatic setProcessDataDirectory : (Ljava/lang/String;)V
    //   241: aload_0
    //   242: getfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   245: getfield profileFd : Landroid/os/ParcelFileDescriptor;
    //   248: ifnull -> 258
    //   251: aload_0
    //   252: getfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   255: invokevirtual startProfiling : ()V
    //   258: aload_1
    //   259: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   262: getfield targetSdkVersion : I
    //   265: bipush #12
    //   267: if_icmpgt -> 276
    //   270: getstatic android/os/AsyncTask.THREAD_POOL_EXECUTOR : Ljava/util/concurrent/Executor;
    //   273: invokestatic setDefaultExecutor : (Ljava/util/concurrent/Executor;)V
    //   276: aload_1
    //   277: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   280: getfield targetSdkVersion : I
    //   283: bipush #29
    //   285: if_icmplt -> 293
    //   288: iconst_1
    //   289: istore_3
    //   290: goto -> 295
    //   293: iconst_0
    //   294: istore_3
    //   295: iload_3
    //   296: invokestatic setThrowExceptionForUpperArrayOutOfBounds : (Z)V
    //   299: aload_1
    //   300: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   303: getfield targetSdkVersion : I
    //   306: invokestatic updateCheckRecycle : (I)V
    //   309: aload_1
    //   310: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   313: getfield targetSdkVersion : I
    //   316: putstatic android/graphics/ImageDecoder.sApiLevel : I
    //   319: aconst_null
    //   320: invokestatic setDefault : (Ljava/util/TimeZone;)V
    //   323: aload_1
    //   324: getfield config : Landroid/content/res/Configuration;
    //   327: invokevirtual getLocales : ()Landroid/os/LocaleList;
    //   330: invokestatic setDefault : (Landroid/os/LocaleList;)V
    //   333: aload_0
    //   334: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   337: astore #4
    //   339: aload #4
    //   341: monitorenter
    //   342: aload_0
    //   343: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   346: aload_1
    //   347: getfield config : Landroid/content/res/Configuration;
    //   350: aload_1
    //   351: getfield compatInfo : Landroid/content/res/CompatibilityInfo;
    //   354: invokevirtual applyConfigurationToResourcesLocked : (Landroid/content/res/Configuration;Landroid/content/res/CompatibilityInfo;)Z
    //   357: pop
    //   358: aload_1
    //   359: getfield config : Landroid/content/res/Configuration;
    //   362: getfield densityDpi : I
    //   365: istore #5
    //   367: aload_0
    //   368: iload #5
    //   370: putfield mCurDefaultDisplayDpi : I
    //   373: aload_0
    //   374: iload #5
    //   376: invokevirtual applyCompatConfiguration : (I)Landroid/content/res/Configuration;
    //   379: pop
    //   380: aload #4
    //   382: monitorexit
    //   383: aload_1
    //   384: aload_0
    //   385: aload_1
    //   386: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   389: aload_1
    //   390: getfield compatInfo : Landroid/content/res/CompatibilityInfo;
    //   393: invokevirtual getPackageInfoNoCheck : (Landroid/content/pm/ApplicationInfo;Landroid/content/res/CompatibilityInfo;)Landroid/app/LoadedApk;
    //   396: putfield info : Landroid/app/LoadedApk;
    //   399: aload_2
    //   400: ifnull -> 411
    //   403: aload_2
    //   404: aload_1
    //   405: getfield info : Landroid/app/LoadedApk;
    //   408: invokestatic handleAttachAgent : (Ljava/lang/String;Landroid/app/LoadedApk;)V
    //   411: aload_1
    //   412: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   415: getfield flags : I
    //   418: sipush #8192
    //   421: iand
    //   422: ifne -> 436
    //   425: aload_0
    //   426: iconst_1
    //   427: putfield mDensityCompatMode : Z
    //   430: sipush #160
    //   433: invokestatic setDefaultDensity : (I)V
    //   436: aload_0
    //   437: invokespecial updateDefaultDensity : ()V
    //   440: aload_0
    //   441: getfield mCoreSettings : Landroid/os/Bundle;
    //   444: ldc_w 'time_12_24'
    //   447: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   450: astore_2
    //   451: aload_2
    //   452: ifnull -> 479
    //   455: ldc_w '24'
    //   458: aload_2
    //   459: invokevirtual equals : (Ljava/lang/Object;)Z
    //   462: ifeq -> 472
    //   465: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   468: astore_2
    //   469: goto -> 476
    //   472: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   475: astore_2
    //   476: goto -> 481
    //   479: aconst_null
    //   480: astore_2
    //   481: aload_2
    //   482: invokestatic set24HourTimePref : (Ljava/lang/Boolean;)V
    //   485: aload_0
    //   486: invokespecial updateDebugViewAttributeState : ()Z
    //   489: pop
    //   490: aload_1
    //   491: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   494: invokestatic initThreadDefaults : (Landroid/content/pm/ApplicationInfo;)V
    //   497: aload_1
    //   498: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   501: invokestatic initVmDefaults : (Landroid/content/pm/ApplicationInfo;)V
    //   504: aload_1
    //   505: getfield debugMode : I
    //   508: ifeq -> 661
    //   511: sipush #8100
    //   514: invokestatic changeDebugPort : (I)V
    //   517: aload_1
    //   518: getfield debugMode : I
    //   521: iconst_2
    //   522: if_icmpne -> 615
    //   525: new java/lang/StringBuilder
    //   528: dup
    //   529: invokespecial <init> : ()V
    //   532: astore_2
    //   533: aload_2
    //   534: ldc_w 'Application '
    //   537: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   540: pop
    //   541: aload_2
    //   542: aload_1
    //   543: getfield info : Landroid/app/LoadedApk;
    //   546: invokevirtual getPackageName : ()Ljava/lang/String;
    //   549: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   552: pop
    //   553: aload_2
    //   554: ldc_w ' is waiting for the debugger on port 8100...'
    //   557: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: pop
    //   561: ldc 'ActivityThread'
    //   563: aload_2
    //   564: invokevirtual toString : ()Ljava/lang/String;
    //   567: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   570: pop
    //   571: invokestatic getService : ()Landroid/app/IActivityManager;
    //   574: astore_2
    //   575: aload_2
    //   576: aload_0
    //   577: getfield mAppThread : Landroid/app/ActivityThread$ApplicationThread;
    //   580: iconst_1
    //   581: invokeinterface showWaitingForDebugger : (Landroid/app/IApplicationThread;Z)V
    //   586: invokestatic waitForDebugger : ()V
    //   589: aload_2
    //   590: aload_0
    //   591: getfield mAppThread : Landroid/app/ActivityThread$ApplicationThread;
    //   594: iconst_0
    //   595: invokeinterface showWaitingForDebugger : (Landroid/app/IApplicationThread;Z)V
    //   600: goto -> 661
    //   603: astore_1
    //   604: aload_1
    //   605: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   608: athrow
    //   609: astore_1
    //   610: aload_1
    //   611: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   614: athrow
    //   615: new java/lang/StringBuilder
    //   618: dup
    //   619: invokespecial <init> : ()V
    //   622: astore_2
    //   623: aload_2
    //   624: ldc_w 'Application '
    //   627: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   630: pop
    //   631: aload_2
    //   632: aload_1
    //   633: getfield info : Landroid/app/LoadedApk;
    //   636: invokevirtual getPackageName : ()Ljava/lang/String;
    //   639: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   642: pop
    //   643: aload_2
    //   644: ldc_w ' can be debugged on port 8100...'
    //   647: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   650: pop
    //   651: ldc 'ActivityThread'
    //   653: aload_2
    //   654: invokevirtual toString : ()Ljava/lang/String;
    //   657: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   660: pop
    //   661: aload_1
    //   662: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   665: invokevirtual isProfileableByShell : ()Z
    //   668: istore_3
    //   669: iload_3
    //   670: invokestatic setAppTracingAllowed : (Z)V
    //   673: iload_3
    //   674: ifne -> 683
    //   677: getstatic android/os/Build.IS_DEBUGGABLE : Z
    //   680: ifeq -> 693
    //   683: aload_1
    //   684: getfield enableBinderTracking : Z
    //   687: ifeq -> 693
    //   690: invokestatic enableTracing : ()V
    //   693: iload_3
    //   694: ifne -> 703
    //   697: getstatic android/os/Build.IS_DEBUGGABLE : Z
    //   700: ifeq -> 707
    //   703: aload_0
    //   704: invokespecial nInitZygoteChildHeapProfiling : ()V
    //   707: aload_1
    //   708: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   711: getfield flags : I
    //   714: iconst_2
    //   715: iand
    //   716: ifeq -> 725
    //   719: iconst_1
    //   720: istore #5
    //   722: goto -> 728
    //   725: iconst_0
    //   726: istore #5
    //   728: iload #5
    //   730: ifne -> 747
    //   733: getstatic android/os/Build.IS_DEBUGGABLE : Z
    //   736: ifeq -> 742
    //   739: goto -> 747
    //   742: iconst_0
    //   743: istore_3
    //   744: goto -> 749
    //   747: iconst_1
    //   748: istore_3
    //   749: iload_3
    //   750: invokestatic setDebuggingEnabled : (Z)V
    //   753: aload_1
    //   754: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   757: getfield packageName : Ljava/lang/String;
    //   760: invokestatic setPackageName : (Ljava/lang/String;)V
    //   763: ldc2_w 64
    //   766: ldc_w 'Setup proxies'
    //   769: invokestatic traceBegin : (JLjava/lang/String;)V
    //   772: ldc_w 'connectivity'
    //   775: invokestatic getService : (Ljava/lang/String;)Landroid/os/IBinder;
    //   778: astore_2
    //   779: aload_2
    //   780: ifnull -> 813
    //   783: aload_2
    //   784: invokestatic asInterface : (Landroid/os/IBinder;)Landroid/net/IConnectivityManager;
    //   787: astore_2
    //   788: aload_2
    //   789: aconst_null
    //   790: invokeinterface getProxyForNetwork : (Landroid/net/Network;)Landroid/net/ProxyInfo;
    //   795: invokestatic setHttpProxySystemProperty : (Landroid/net/ProxyInfo;)V
    //   798: goto -> 813
    //   801: astore_1
    //   802: ldc2_w 64
    //   805: invokestatic traceEnd : (J)V
    //   808: aload_1
    //   809: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   812: athrow
    //   813: ldc2_w 64
    //   816: invokestatic traceEnd : (J)V
    //   819: aload_1
    //   820: getfield instrumentationName : Landroid/content/ComponentName;
    //   823: ifnull -> 1135
    //   826: new android/app/ApplicationPackageManager
    //   829: astore_2
    //   830: aload_2
    //   831: aconst_null
    //   832: invokestatic getPackageManager : ()Landroid/content/pm/IPackageManager;
    //   835: invokestatic getPermissionManager : ()Landroid/permission/IPermissionManager;
    //   838: invokespecial <init> : (Landroid/app/ContextImpl;Landroid/content/pm/IPackageManager;Landroid/permission/IPermissionManager;)V
    //   841: aload_2
    //   842: aload_1
    //   843: getfield instrumentationName : Landroid/content/ComponentName;
    //   846: iconst_0
    //   847: invokevirtual getInstrumentationInfo : (Landroid/content/ComponentName;I)Landroid/content/pm/InstrumentationInfo;
    //   850: astore #4
    //   852: aload_1
    //   853: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   856: getfield primaryCpuAbi : Ljava/lang/String;
    //   859: aload #4
    //   861: getfield primaryCpuAbi : Ljava/lang/String;
    //   864: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   867: ifeq -> 888
    //   870: aload_1
    //   871: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   874: getfield secondaryCpuAbi : Ljava/lang/String;
    //   877: aload #4
    //   879: getfield secondaryCpuAbi : Ljava/lang/String;
    //   882: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   885: ifne -> 1020
    //   888: new java/lang/StringBuilder
    //   891: dup
    //   892: invokespecial <init> : ()V
    //   895: astore_2
    //   896: aload_2
    //   897: ldc_w 'Package uses different ABI(s) than its instrumentation: package['
    //   900: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   903: pop
    //   904: aload_2
    //   905: aload_1
    //   906: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   909: getfield packageName : Ljava/lang/String;
    //   912: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   915: pop
    //   916: aload_2
    //   917: ldc_w ']: '
    //   920: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   923: pop
    //   924: aload_2
    //   925: aload_1
    //   926: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   929: getfield primaryCpuAbi : Ljava/lang/String;
    //   932: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   935: pop
    //   936: aload_2
    //   937: ldc_w ', '
    //   940: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   943: pop
    //   944: aload_2
    //   945: aload_1
    //   946: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   949: getfield secondaryCpuAbi : Ljava/lang/String;
    //   952: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   955: pop
    //   956: aload_2
    //   957: ldc_w ' instrumentation['
    //   960: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   963: pop
    //   964: aload_2
    //   965: aload #4
    //   967: getfield packageName : Ljava/lang/String;
    //   970: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   973: pop
    //   974: aload_2
    //   975: ldc_w ']: '
    //   978: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   981: pop
    //   982: aload_2
    //   983: aload #4
    //   985: getfield primaryCpuAbi : Ljava/lang/String;
    //   988: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   991: pop
    //   992: aload_2
    //   993: ldc_w ', '
    //   996: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   999: pop
    //   1000: aload_2
    //   1001: aload #4
    //   1003: getfield secondaryCpuAbi : Ljava/lang/String;
    //   1006: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1009: pop
    //   1010: ldc 'ActivityThread'
    //   1012: aload_2
    //   1013: invokevirtual toString : ()Ljava/lang/String;
    //   1016: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   1019: pop
    //   1020: aload_0
    //   1021: aload #4
    //   1023: getfield packageName : Ljava/lang/String;
    //   1026: putfield mInstrumentationPackageName : Ljava/lang/String;
    //   1029: aload_0
    //   1030: aload #4
    //   1032: getfield sourceDir : Ljava/lang/String;
    //   1035: putfield mInstrumentationAppDir : Ljava/lang/String;
    //   1038: aload_0
    //   1039: aload #4
    //   1041: getfield splitSourceDirs : [Ljava/lang/String;
    //   1044: putfield mInstrumentationSplitAppDirs : [Ljava/lang/String;
    //   1047: aload_0
    //   1048: aload_0
    //   1049: aload_1
    //   1050: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   1053: aload #4
    //   1055: invokespecial getInstrumentationLibrary : (Landroid/content/pm/ApplicationInfo;Landroid/content/pm/InstrumentationInfo;)Ljava/lang/String;
    //   1058: putfield mInstrumentationLibDir : Ljava/lang/String;
    //   1061: aload_0
    //   1062: aload_1
    //   1063: getfield info : Landroid/app/LoadedApk;
    //   1066: invokevirtual getAppDir : ()Ljava/lang/String;
    //   1069: putfield mInstrumentedAppDir : Ljava/lang/String;
    //   1072: aload_0
    //   1073: aload_1
    //   1074: getfield info : Landroid/app/LoadedApk;
    //   1077: invokevirtual getSplitAppDirs : ()[Ljava/lang/String;
    //   1080: putfield mInstrumentedSplitAppDirs : [Ljava/lang/String;
    //   1083: aload_0
    //   1084: aload_1
    //   1085: getfield info : Landroid/app/LoadedApk;
    //   1088: invokevirtual getLibDir : ()Ljava/lang/String;
    //   1091: putfield mInstrumentedLibDir : Ljava/lang/String;
    //   1094: goto -> 1138
    //   1097: astore_2
    //   1098: new java/lang/StringBuilder
    //   1101: dup
    //   1102: invokespecial <init> : ()V
    //   1105: astore_2
    //   1106: aload_2
    //   1107: ldc_w 'Unable to find instrumentation info for: '
    //   1110: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1113: pop
    //   1114: aload_2
    //   1115: aload_1
    //   1116: getfield instrumentationName : Landroid/content/ComponentName;
    //   1119: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1122: pop
    //   1123: new java/lang/RuntimeException
    //   1126: dup
    //   1127: aload_2
    //   1128: invokevirtual toString : ()Ljava/lang/String;
    //   1131: invokespecial <init> : (Ljava/lang/String;)V
    //   1134: athrow
    //   1135: aconst_null
    //   1136: astore #4
    //   1138: aload_0
    //   1139: aload_1
    //   1140: getfield info : Landroid/app/LoadedApk;
    //   1143: invokestatic createAppContext : (Landroid/app/ActivityThread;Landroid/app/LoadedApk;)Landroid/app/ContextImpl;
    //   1146: astore #6
    //   1148: aload_0
    //   1149: aload #6
    //   1151: aload_0
    //   1152: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   1155: invokevirtual getConfiguration : ()Landroid/content/res/Configuration;
    //   1158: invokevirtual getLocales : ()Landroid/os/LocaleList;
    //   1161: invokespecial updateLocaleListFromAppContext : (Landroid/content/Context;Landroid/os/LocaleList;)V
    //   1164: invokestatic isIsolated : ()Z
    //   1167: ifne -> 1197
    //   1170: invokestatic allowThreadDiskWritesMask : ()I
    //   1173: istore #5
    //   1175: aload_0
    //   1176: aload #6
    //   1178: invokespecial setupGraphicsSupport : (Landroid/content/Context;)V
    //   1181: iload #5
    //   1183: invokestatic setThreadPolicyMask : (I)V
    //   1186: goto -> 1201
    //   1189: astore_1
    //   1190: iload #5
    //   1192: invokestatic setThreadPolicyMask : (I)V
    //   1195: aload_1
    //   1196: athrow
    //   1197: iconst_1
    //   1198: invokestatic setIsolatedProcess : (Z)V
    //   1201: ldc2_w 64
    //   1204: ldc_w 'NetworkSecurityConfigProvider.install'
    //   1207: invokestatic traceBegin : (JLjava/lang/String;)V
    //   1210: aload #6
    //   1212: invokestatic install : (Landroid/content/Context;)V
    //   1215: ldc2_w 64
    //   1218: invokestatic traceEnd : (J)V
    //   1221: aload #4
    //   1223: ifnull -> 1517
    //   1226: invokestatic getPackageManager : ()Landroid/content/pm/IPackageManager;
    //   1229: aload #4
    //   1231: getfield packageName : Ljava/lang/String;
    //   1234: iconst_0
    //   1235: invokestatic myUserId : ()I
    //   1238: invokeinterface getApplicationInfo : (Ljava/lang/String;II)Landroid/content/pm/ApplicationInfo;
    //   1243: astore_2
    //   1244: goto -> 1250
    //   1247: astore_2
    //   1248: aconst_null
    //   1249: astore_2
    //   1250: aload_2
    //   1251: ifnonnull -> 1265
    //   1254: new android/content/pm/ApplicationInfo
    //   1257: dup
    //   1258: invokespecial <init> : ()V
    //   1261: astore_2
    //   1262: goto -> 1265
    //   1265: aload #4
    //   1267: aload_2
    //   1268: invokevirtual copyTo : (Landroid/content/pm/ApplicationInfo;)V
    //   1271: aload_2
    //   1272: invokestatic myUserId : ()I
    //   1275: invokevirtual initForUser : (I)V
    //   1278: aload_1
    //   1279: getfield compatInfo : Landroid/content/res/CompatibilityInfo;
    //   1282: astore #7
    //   1284: aload #6
    //   1286: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   1289: astore #8
    //   1291: aload #6
    //   1293: astore #9
    //   1295: aload_0
    //   1296: aload_2
    //   1297: aload #7
    //   1299: aload #8
    //   1301: iconst_0
    //   1302: iconst_1
    //   1303: iconst_0
    //   1304: invokespecial getPackageInfo : (Landroid/content/pm/ApplicationInfo;Landroid/content/res/CompatibilityInfo;Ljava/lang/ClassLoader;ZZZ)Landroid/app/LoadedApk;
    //   1307: astore_2
    //   1308: aload_0
    //   1309: aload_2
    //   1310: aload #9
    //   1312: invokevirtual getOpPackageName : ()Ljava/lang/String;
    //   1315: invokestatic createAppContext : (Landroid/app/ActivityThread;Landroid/app/LoadedApk;Ljava/lang/String;)Landroid/app/ContextImpl;
    //   1318: astore_2
    //   1319: aload_0
    //   1320: aload_2
    //   1321: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   1324: aload_1
    //   1325: getfield instrumentationName : Landroid/content/ComponentName;
    //   1328: invokevirtual getClassName : ()Ljava/lang/String;
    //   1331: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   1334: invokevirtual newInstance : ()Ljava/lang/Object;
    //   1337: checkcast android/app/Instrumentation
    //   1340: putfield mInstrumentation : Landroid/app/Instrumentation;
    //   1343: new android/content/ComponentName
    //   1346: dup
    //   1347: aload #4
    //   1349: getfield packageName : Ljava/lang/String;
    //   1352: aload #4
    //   1354: getfield name : Ljava/lang/String;
    //   1357: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   1360: astore #8
    //   1362: aload_0
    //   1363: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   1366: aload_0
    //   1367: aload_2
    //   1368: aload #9
    //   1370: aload #8
    //   1372: aload_1
    //   1373: getfield instrumentationWatcher : Landroid/app/IInstrumentationWatcher;
    //   1376: aload_1
    //   1377: getfield instrumentationUiAutomationConnection : Landroid/app/IUiAutomationConnection;
    //   1380: invokevirtual init : (Landroid/app/ActivityThread;Landroid/content/Context;Landroid/content/Context;Landroid/content/ComponentName;Landroid/app/IInstrumentationWatcher;Landroid/app/IUiAutomationConnection;)V
    //   1383: aload_0
    //   1384: getfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   1387: getfield profileFile : Ljava/lang/String;
    //   1390: ifnull -> 1452
    //   1393: aload #4
    //   1395: getfield handleProfiling : Z
    //   1398: ifne -> 1452
    //   1401: aload_0
    //   1402: getfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   1405: getfield profileFd : Landroid/os/ParcelFileDescriptor;
    //   1408: ifnonnull -> 1452
    //   1411: aload_0
    //   1412: getfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   1415: iconst_1
    //   1416: putfield handlingProfiling : Z
    //   1419: new java/io/File
    //   1422: dup
    //   1423: aload_0
    //   1424: getfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   1427: getfield profileFile : Ljava/lang/String;
    //   1430: invokespecial <init> : (Ljava/lang/String;)V
    //   1433: astore_2
    //   1434: aload_2
    //   1435: invokevirtual getParentFile : ()Ljava/io/File;
    //   1438: invokevirtual mkdirs : ()Z
    //   1441: pop
    //   1442: aload_2
    //   1443: invokevirtual toString : ()Ljava/lang/String;
    //   1446: ldc_w 8388608
    //   1449: invokestatic startMethodTracing : (Ljava/lang/String;I)V
    //   1452: goto -> 1535
    //   1455: astore_2
    //   1456: new java/lang/StringBuilder
    //   1459: dup
    //   1460: invokespecial <init> : ()V
    //   1463: astore #4
    //   1465: aload #4
    //   1467: ldc_w 'Unable to instantiate instrumentation '
    //   1470: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1473: pop
    //   1474: aload #4
    //   1476: aload_1
    //   1477: getfield instrumentationName : Landroid/content/ComponentName;
    //   1480: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1483: pop
    //   1484: aload #4
    //   1486: ldc_w ': '
    //   1489: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1492: pop
    //   1493: aload #4
    //   1495: aload_2
    //   1496: invokevirtual toString : ()Ljava/lang/String;
    //   1499: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1502: pop
    //   1503: new java/lang/RuntimeException
    //   1506: dup
    //   1507: aload #4
    //   1509: invokevirtual toString : ()Ljava/lang/String;
    //   1512: aload_2
    //   1513: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1516: athrow
    //   1517: new android/app/Instrumentation
    //   1520: dup
    //   1521: invokespecial <init> : ()V
    //   1524: astore_2
    //   1525: aload_0
    //   1526: aload_2
    //   1527: putfield mInstrumentation : Landroid/app/Instrumentation;
    //   1530: aload_2
    //   1531: aload_0
    //   1532: invokevirtual basicInit : (Landroid/app/ActivityThread;)V
    //   1535: aload_1
    //   1536: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   1539: getfield flags : I
    //   1542: ldc_w 1048576
    //   1545: iand
    //   1546: ifeq -> 1558
    //   1549: invokestatic getRuntime : ()Ldalvik/system/VMRuntime;
    //   1552: invokevirtual clearGrowthLimit : ()V
    //   1555: goto -> 1564
    //   1558: invokestatic getRuntime : ()Ldalvik/system/VMRuntime;
    //   1561: invokevirtual clampGrowthLimit : ()V
    //   1564: invokestatic allowThreadDiskWrites : ()Landroid/os/StrictMode$ThreadPolicy;
    //   1567: astore_2
    //   1568: invokestatic getThreadPolicy : ()Landroid/os/StrictMode$ThreadPolicy;
    //   1571: astore #4
    //   1573: aload_1
    //   1574: getfield info : Landroid/app/LoadedApk;
    //   1577: aload_1
    //   1578: getfield restrictedBackupMode : Z
    //   1581: aconst_null
    //   1582: invokevirtual makeApplication : (ZLandroid/app/Instrumentation;)Landroid/app/Application;
    //   1585: astore #8
    //   1587: aload #8
    //   1589: aload_1
    //   1590: getfield autofillOptions : Landroid/content/AutofillOptions;
    //   1593: invokevirtual setAutofillOptions : (Landroid/content/AutofillOptions;)V
    //   1596: aload #8
    //   1598: aload_1
    //   1599: getfield contentCaptureOptions : Landroid/content/ContentCaptureOptions;
    //   1602: invokevirtual setContentCaptureOptions : (Landroid/content/ContentCaptureOptions;)V
    //   1605: aload_0
    //   1606: aload #8
    //   1608: putfield mInitialApplication : Landroid/app/Application;
    //   1611: aload_1
    //   1612: getfield restrictedBackupMode : Z
    //   1615: ifne -> 1638
    //   1618: aload_1
    //   1619: getfield providers : Ljava/util/List;
    //   1622: invokestatic isEmpty : (Ljava/util/Collection;)Z
    //   1625: ifne -> 1638
    //   1628: aload_0
    //   1629: aload #8
    //   1631: aload_1
    //   1632: getfield providers : Ljava/util/List;
    //   1635: invokespecial installContentProviders : (Landroid/content/Context;Ljava/util/List;)V
    //   1638: aload_0
    //   1639: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   1642: aload_1
    //   1643: getfield instrumentationArgs : Landroid/os/Bundle;
    //   1646: invokevirtual onCreate : (Landroid/os/Bundle;)V
    //   1649: aload_0
    //   1650: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   1653: aload #8
    //   1655: invokevirtual callApplicationOnCreate : (Landroid/app/Application;)V
    //   1658: goto -> 1679
    //   1661: astore #9
    //   1663: aload_0
    //   1664: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   1667: aload #8
    //   1669: aload #9
    //   1671: invokevirtual onException : (Ljava/lang/Object;Ljava/lang/Throwable;)Z
    //   1674: istore_3
    //   1675: iload_3
    //   1676: ifeq -> 1786
    //   1679: aload_1
    //   1680: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   1683: getfield targetSdkVersion : I
    //   1686: bipush #27
    //   1688: if_icmplt -> 1702
    //   1691: invokestatic getThreadPolicy : ()Landroid/os/StrictMode$ThreadPolicy;
    //   1694: aload #4
    //   1696: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1699: ifeq -> 1706
    //   1702: aload_2
    //   1703: invokestatic setThreadPolicy : (Landroid/os/StrictMode$ThreadPolicy;)V
    //   1706: aload #6
    //   1708: invokestatic setApplicationContextForResources : (Landroid/content/Context;)V
    //   1711: invokestatic isIsolated : ()Z
    //   1714: ifne -> 1785
    //   1717: invokestatic getPackageManager : ()Landroid/content/pm/IPackageManager;
    //   1720: aload_1
    //   1721: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   1724: getfield packageName : Ljava/lang/String;
    //   1727: sipush #128
    //   1730: invokestatic myUserId : ()I
    //   1733: invokeinterface getApplicationInfo : (Ljava/lang/String;II)Landroid/content/pm/ApplicationInfo;
    //   1738: astore_2
    //   1739: aload_2
    //   1740: getfield metaData : Landroid/os/Bundle;
    //   1743: ifnull -> 1776
    //   1746: aload_2
    //   1747: getfield metaData : Landroid/os/Bundle;
    //   1750: ldc_w 'preloaded_fonts'
    //   1753: iconst_0
    //   1754: invokevirtual getInt : (Ljava/lang/String;I)I
    //   1757: istore #5
    //   1759: iload #5
    //   1761: ifeq -> 1776
    //   1764: aload_1
    //   1765: getfield info : Landroid/app/LoadedApk;
    //   1768: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   1771: iload #5
    //   1773: invokevirtual preloadFonts : (I)V
    //   1776: goto -> 1785
    //   1779: astore_1
    //   1780: aload_1
    //   1781: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   1784: athrow
    //   1785: return
    //   1786: new java/lang/RuntimeException
    //   1789: astore #7
    //   1791: new java/lang/StringBuilder
    //   1794: astore #6
    //   1796: aload #6
    //   1798: invokespecial <init> : ()V
    //   1801: aload #6
    //   1803: ldc_w 'Unable to create application '
    //   1806: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1809: pop
    //   1810: aload #6
    //   1812: aload #8
    //   1814: invokevirtual getClass : ()Ljava/lang/Class;
    //   1817: invokevirtual getName : ()Ljava/lang/String;
    //   1820: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1823: pop
    //   1824: aload #6
    //   1826: ldc_w ': '
    //   1829: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1832: pop
    //   1833: aload #6
    //   1835: aload #9
    //   1837: invokevirtual toString : ()Ljava/lang/String;
    //   1840: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1843: pop
    //   1844: aload #7
    //   1846: aload #6
    //   1848: invokevirtual toString : ()Ljava/lang/String;
    //   1851: aload #9
    //   1853: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1856: aload #7
    //   1858: athrow
    //   1859: astore #6
    //   1861: new java/lang/RuntimeException
    //   1864: astore #8
    //   1866: new java/lang/StringBuilder
    //   1869: astore #9
    //   1871: aload #9
    //   1873: invokespecial <init> : ()V
    //   1876: aload #9
    //   1878: ldc_w 'Exception thrown in onCreate() of '
    //   1881: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1884: pop
    //   1885: aload #9
    //   1887: aload_1
    //   1888: getfield instrumentationName : Landroid/content/ComponentName;
    //   1891: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1894: pop
    //   1895: aload #9
    //   1897: ldc_w ': '
    //   1900: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1903: pop
    //   1904: aload #9
    //   1906: aload #6
    //   1908: invokevirtual toString : ()Ljava/lang/String;
    //   1911: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1914: pop
    //   1915: aload #8
    //   1917: aload #9
    //   1919: invokevirtual toString : ()Ljava/lang/String;
    //   1922: aload #6
    //   1924: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1927: aload #8
    //   1929: athrow
    //   1930: astore #6
    //   1932: aload_1
    //   1933: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   1936: getfield targetSdkVersion : I
    //   1939: bipush #27
    //   1941: if_icmplt -> 1955
    //   1944: invokestatic getThreadPolicy : ()Landroid/os/StrictMode$ThreadPolicy;
    //   1947: aload #4
    //   1949: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1952: ifeq -> 1959
    //   1955: aload_2
    //   1956: invokestatic setThreadPolicy : (Landroid/os/StrictMode$ThreadPolicy;)V
    //   1959: aload #6
    //   1961: athrow
    //   1962: astore_1
    //   1963: aload #4
    //   1965: monitorexit
    //   1966: aload_1
    //   1967: athrow
    // Exception table:
    //   from	to	target	type
    //   342	383	1962	finally
    //   575	586	609	android/os/RemoteException
    //   589	600	603	android/os/RemoteException
    //   788	798	801	android/os/RemoteException
    //   826	852	1097	android/content/pm/PackageManager$NameNotFoundException
    //   1175	1181	1189	finally
    //   1226	1244	1247	android/os/RemoteException
    //   1319	1343	1455	java/lang/Exception
    //   1573	1638	1930	finally
    //   1638	1649	1859	java/lang/Exception
    //   1638	1649	1930	finally
    //   1649	1658	1661	java/lang/Exception
    //   1649	1658	1930	finally
    //   1663	1675	1930	finally
    //   1717	1759	1779	android/os/RemoteException
    //   1764	1776	1779	android/os/RemoteException
    //   1786	1859	1930	finally
    //   1861	1930	1930	finally
    //   1963	1966	1962	finally
  }
  
  private void handleBindService(BindServiceData paramBindServiceData) {
    Service service = (Service)this.mServices.get(paramBindServiceData.token);
    if (service != null)
      try {
        paramBindServiceData.intent.setExtrasClassLoader(service.getClassLoader());
        paramBindServiceData.intent.prepareToEnterProcess();
        try {
          if (!paramBindServiceData.rebind) {
            IBinder iBinder = service.onBind(paramBindServiceData.intent);
            ActivityManager.getService().publishService(paramBindServiceData.token, paramBindServiceData.intent, iBinder);
          } else {
            service.onRebind(paramBindServiceData.intent);
            ActivityManager.getService().serviceDoneExecuting(paramBindServiceData.token, 0, 0, 0);
          } 
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      } catch (Exception exception) {
        if (!this.mInstrumentation.onException(service, exception)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unable to bind to service ");
          stringBuilder.append(service);
          stringBuilder.append(" with ");
          stringBuilder.append(paramBindServiceData.intent);
          stringBuilder.append(": ");
          stringBuilder.append(exception.toString());
          throw new RuntimeException(stringBuilder.toString(), exception);
        } 
      }  
  }
  
  private void handleConfigurationChanged(Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getSystemContext : ()Landroid/app/ContextImpl;
    //   4: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   7: astore_3
    //   8: aload_0
    //   9: invokevirtual getSystemUiContext : ()Landroid/app/ContextImpl;
    //   12: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   15: astore #4
    //   17: aload_0
    //   18: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   21: astore #5
    //   23: aload #5
    //   25: monitorenter
    //   26: aload_1
    //   27: astore #6
    //   29: aload_0
    //   30: getfield mPendingConfiguration : Landroid/content/res/Configuration;
    //   33: ifnull -> 74
    //   36: aload_1
    //   37: astore #6
    //   39: aload_0
    //   40: getfield mPendingConfiguration : Landroid/content/res/Configuration;
    //   43: aload_1
    //   44: invokevirtual isOtherSeqNewer : (Landroid/content/res/Configuration;)Z
    //   47: ifne -> 69
    //   50: aload_0
    //   51: getfield mPendingConfiguration : Landroid/content/res/Configuration;
    //   54: astore #6
    //   56: aload_0
    //   57: aload #6
    //   59: getfield densityDpi : I
    //   62: putfield mCurDefaultDisplayDpi : I
    //   65: aload_0
    //   66: invokespecial updateDefaultDensity : ()V
    //   69: aload_0
    //   70: aconst_null
    //   71: putfield mPendingConfiguration : Landroid/content/res/Configuration;
    //   74: aload #6
    //   76: ifnonnull -> 105
    //   79: getstatic android/os/Build.IS_DEBUGGABLE : Z
    //   82: ifeq -> 101
    //   85: aload_0
    //   86: getfield mHasImeComponent : Z
    //   89: ifeq -> 101
    //   92: ldc 'ActivityThread'
    //   94: ldc_w 'handleConfigurationChanged for IME app but config is null'
    //   97: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   100: pop
    //   101: aload #5
    //   103: monitorexit
    //   104: return
    //   105: aload_0
    //   106: getfield mConfiguration : Landroid/content/res/Configuration;
    //   109: ifnull -> 130
    //   112: aload_0
    //   113: getfield mConfiguration : Landroid/content/res/Configuration;
    //   116: aload #6
    //   118: invokevirtual diffPublicOnly : (Landroid/content/res/Configuration;)I
    //   121: ifne -> 130
    //   124: iconst_1
    //   125: istore #7
    //   127: goto -> 133
    //   130: iconst_0
    //   131: istore #7
    //   133: aload_0
    //   134: getfield mInitialApplication : Landroid/app/Application;
    //   137: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   140: astore_1
    //   141: aload_1
    //   142: invokevirtual hasOverrideDisplayAdjustments : ()Z
    //   145: ifeq -> 161
    //   148: aload_1
    //   149: invokevirtual getDisplayAdjustments : ()Landroid/view/DisplayAdjustments;
    //   152: invokevirtual getConfiguration : ()Landroid/content/res/Configuration;
    //   155: aload #6
    //   157: invokevirtual updateFrom : (Landroid/content/res/Configuration;)I
    //   160: pop
    //   161: aload_0
    //   162: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   165: aload #6
    //   167: aload_2
    //   168: aload_1
    //   169: invokevirtual getDisplayAdjustments : ()Landroid/view/DisplayAdjustments;
    //   172: invokevirtual applyConfigurationToResourcesLocked : (Landroid/content/res/Configuration;Landroid/content/res/CompatibilityInfo;Landroid/view/DisplayAdjustments;)Z
    //   175: pop
    //   176: aload_0
    //   177: aload_0
    //   178: getfield mInitialApplication : Landroid/app/Application;
    //   181: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   184: aload_0
    //   185: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   188: invokevirtual getConfiguration : ()Landroid/content/res/Configuration;
    //   191: invokevirtual getLocales : ()Landroid/os/LocaleList;
    //   194: invokespecial updateLocaleListFromAppContext : (Landroid/content/Context;Landroid/os/LocaleList;)V
    //   197: aload_0
    //   198: getfield mConfiguration : Landroid/content/res/Configuration;
    //   201: ifnonnull -> 217
    //   204: new android/content/res/Configuration
    //   207: astore_1
    //   208: aload_1
    //   209: invokespecial <init> : ()V
    //   212: aload_0
    //   213: aload_1
    //   214: putfield mConfiguration : Landroid/content/res/Configuration;
    //   217: aload_0
    //   218: getfield mConfiguration : Landroid/content/res/Configuration;
    //   221: aload #6
    //   223: invokevirtual isOtherSeqNewer : (Landroid/content/res/Configuration;)Z
    //   226: ifne -> 300
    //   229: aload_2
    //   230: ifnonnull -> 300
    //   233: getstatic android/os/Build.IS_DEBUGGABLE : Z
    //   236: ifeq -> 296
    //   239: aload_0
    //   240: getfield mHasImeComponent : Z
    //   243: ifeq -> 296
    //   246: new java/lang/StringBuilder
    //   249: astore_1
    //   250: aload_1
    //   251: invokespecial <init> : ()V
    //   254: aload_1
    //   255: ldc_w 'handleConfigurationChanged for IME app but config seq is obsolete , config='
    //   258: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: pop
    //   262: aload_1
    //   263: aload #6
    //   265: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   268: pop
    //   269: aload_1
    //   270: ldc_w ', mConfiguration='
    //   273: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: pop
    //   277: aload_1
    //   278: aload_0
    //   279: getfield mConfiguration : Landroid/content/res/Configuration;
    //   282: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   285: pop
    //   286: ldc 'ActivityThread'
    //   288: aload_1
    //   289: invokevirtual toString : ()Ljava/lang/String;
    //   292: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   295: pop
    //   296: aload #5
    //   298: monitorexit
    //   299: return
    //   300: aload_0
    //   301: getfield mConfiguration : Landroid/content/res/Configuration;
    //   304: aload #6
    //   306: invokevirtual updateFrom : (Landroid/content/res/Configuration;)I
    //   309: istore #8
    //   311: aload_0
    //   312: aload_0
    //   313: getfield mCurDefaultDisplayDpi : I
    //   316: invokevirtual applyCompatConfiguration : (I)Landroid/content/res/Configuration;
    //   319: astore_1
    //   320: aload_3
    //   321: invokevirtual getChangingConfigurations : ()I
    //   324: iload #8
    //   326: iand
    //   327: ifeq -> 334
    //   330: aload_3
    //   331: invokevirtual rebase : ()V
    //   334: aload #4
    //   336: invokevirtual getChangingConfigurations : ()I
    //   339: iload #8
    //   341: iand
    //   342: ifeq -> 350
    //   345: aload #4
    //   347: invokevirtual rebase : ()V
    //   350: aload #5
    //   352: monitorexit
    //   353: aload_0
    //   354: iconst_0
    //   355: aload_1
    //   356: invokevirtual collectComponentCallbacks : (ZLandroid/content/res/Configuration;)Ljava/util/ArrayList;
    //   359: astore_2
    //   360: iload #8
    //   362: invokestatic freeTextLayoutCachesIfNeeded : (I)V
    //   365: aload_2
    //   366: ifnull -> 525
    //   369: aload_2
    //   370: invokevirtual size : ()I
    //   373: istore #9
    //   375: iconst_0
    //   376: istore #10
    //   378: iload #10
    //   380: iload #9
    //   382: if_icmpge -> 525
    //   385: aload_2
    //   386: iload #10
    //   388: invokevirtual get : (I)Ljava/lang/Object;
    //   391: checkcast android/content/ComponentCallbacks2
    //   394: astore #6
    //   396: aload #6
    //   398: instanceof android/app/Activity
    //   401: ifeq -> 434
    //   404: aload #6
    //   406: checkcast android/app/Activity
    //   409: astore #6
    //   411: aload_0
    //   412: aload_0
    //   413: getfield mActivities : Landroid/util/ArrayMap;
    //   416: aload #6
    //   418: invokevirtual getActivityToken : ()Landroid/os/IBinder;
    //   421: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   424: checkcast android/app/ActivityThread$ActivityClientRecord
    //   427: aload_1
    //   428: invokespecial performConfigurationChangedForActivity : (Landroid/app/ActivityThread$ActivityClientRecord;Landroid/content/res/Configuration;)V
    //   431: goto -> 519
    //   434: iload #7
    //   436: ifne -> 449
    //   439: aload_0
    //   440: aload #6
    //   442: aload_1
    //   443: invokespecial performConfigurationChanged : (Landroid/content/ComponentCallbacks2;Landroid/content/res/Configuration;)V
    //   446: goto -> 519
    //   449: getstatic android/os/Build.IS_DEBUGGABLE : Z
    //   452: ifeq -> 519
    //   455: aload #6
    //   457: instanceof android/inputmethodservice/InputMethodService
    //   460: ifeq -> 519
    //   463: new java/lang/StringBuilder
    //   466: dup
    //   467: invokespecial <init> : ()V
    //   470: astore #6
    //   472: aload #6
    //   474: ldc_w 'performConfigurationChanged didn't callback to IME , configDiff='
    //   477: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   480: pop
    //   481: aload #6
    //   483: iload #8
    //   485: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   488: pop
    //   489: aload #6
    //   491: ldc_w ', mConfiguration='
    //   494: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   497: pop
    //   498: aload #6
    //   500: aload_0
    //   501: getfield mConfiguration : Landroid/content/res/Configuration;
    //   504: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   507: pop
    //   508: ldc 'ActivityThread'
    //   510: aload #6
    //   512: invokevirtual toString : ()Ljava/lang/String;
    //   515: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   518: pop
    //   519: iinc #10, 1
    //   522: goto -> 378
    //   525: return
    //   526: astore_1
    //   527: aload #5
    //   529: monitorexit
    //   530: aload_1
    //   531: athrow
    // Exception table:
    //   from	to	target	type
    //   29	36	526	finally
    //   39	69	526	finally
    //   69	74	526	finally
    //   79	101	526	finally
    //   101	104	526	finally
    //   105	124	526	finally
    //   133	161	526	finally
    //   161	217	526	finally
    //   217	229	526	finally
    //   233	296	526	finally
    //   296	299	526	finally
    //   300	334	526	finally
    //   334	350	526	finally
    //   350	353	526	finally
    //   527	530	526	finally
  }
  
  private void handleCreateBackupAgent(CreateBackupAgentData paramCreateBackupAgentData) {
    // Byte code:
    //   0: invokestatic getPackageManager : ()Landroid/content/pm/IPackageManager;
    //   3: aload_1
    //   4: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   7: getfield packageName : Ljava/lang/String;
    //   10: iconst_0
    //   11: invokestatic myUserId : ()I
    //   14: invokeinterface getPackageInfo : (Ljava/lang/String;II)Landroid/content/pm/PackageInfo;
    //   19: getfield applicationInfo : Landroid/content/pm/ApplicationInfo;
    //   22: getfield uid : I
    //   25: istore_2
    //   26: invokestatic myUid : ()I
    //   29: istore_3
    //   30: iload_2
    //   31: iload_3
    //   32: if_icmpeq -> 79
    //   35: new java/lang/StringBuilder
    //   38: astore #4
    //   40: aload #4
    //   42: invokespecial <init> : ()V
    //   45: aload #4
    //   47: ldc_w 'Asked to instantiate non-matching package '
    //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: aload #4
    //   56: aload_1
    //   57: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   60: getfield packageName : Ljava/lang/String;
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: ldc 'ActivityThread'
    //   69: aload #4
    //   71: invokevirtual toString : ()Ljava/lang/String;
    //   74: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   77: pop
    //   78: return
    //   79: aload_0
    //   80: invokevirtual unscheduleGcIdler : ()V
    //   83: aload_0
    //   84: aload_1
    //   85: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   88: aload_1
    //   89: getfield compatInfo : Landroid/content/res/CompatibilityInfo;
    //   92: invokevirtual getPackageInfoNoCheck : (Landroid/content/pm/ApplicationInfo;Landroid/content/res/CompatibilityInfo;)Landroid/app/LoadedApk;
    //   95: astore #5
    //   97: aload #5
    //   99: getfield mPackageName : Ljava/lang/String;
    //   102: astore #6
    //   104: aload #6
    //   106: ifnonnull -> 119
    //   109: ldc 'ActivityThread'
    //   111: ldc_w 'Asked to create backup agent for nonexistent package'
    //   114: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   117: pop
    //   118: return
    //   119: aload_1
    //   120: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   123: getfield backupAgentName : Ljava/lang/String;
    //   126: astore #4
    //   128: aload #4
    //   130: astore #7
    //   132: aload #4
    //   134: ifnonnull -> 162
    //   137: aload_1
    //   138: getfield backupMode : I
    //   141: iconst_1
    //   142: if_icmpeq -> 157
    //   145: aload #4
    //   147: astore #7
    //   149: aload_1
    //   150: getfield backupMode : I
    //   153: iconst_3
    //   154: if_icmpne -> 162
    //   157: ldc_w 'android.app.backup.FullBackupAgent'
    //   160: astore #7
    //   162: aconst_null
    //   163: astore #8
    //   165: aload_0
    //   166: aload_1
    //   167: getfield userId : I
    //   170: invokespecial getBackupAgentsForUser : (I)Landroid/util/ArrayMap;
    //   173: astore #9
    //   175: aload #9
    //   177: aload #6
    //   179: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   182: checkcast android/app/backup/BackupAgent
    //   185: astore #4
    //   187: aload #4
    //   189: ifnull -> 202
    //   192: aload #4
    //   194: invokevirtual onBind : ()Landroid/os/IBinder;
    //   197: astore #4
    //   199: goto -> 368
    //   202: aload #8
    //   204: astore #4
    //   206: aload #5
    //   208: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   211: aload #7
    //   213: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   216: invokevirtual newInstance : ()Ljava/lang/Object;
    //   219: checkcast android/app/backup/BackupAgent
    //   222: astore #10
    //   224: aload #8
    //   226: astore #4
    //   228: aload_0
    //   229: aload #5
    //   231: invokestatic createAppContext : (Landroid/app/ActivityThread;Landroid/app/LoadedApk;)Landroid/app/ContextImpl;
    //   234: astore #5
    //   236: aload #8
    //   238: astore #4
    //   240: aload #5
    //   242: aload #10
    //   244: invokevirtual setOuterContext : (Landroid/content/Context;)V
    //   247: aload #8
    //   249: astore #4
    //   251: aload #10
    //   253: aload #5
    //   255: invokevirtual attach : (Landroid/content/Context;)V
    //   258: aload #8
    //   260: astore #4
    //   262: aload #10
    //   264: aload_1
    //   265: getfield userId : I
    //   268: invokestatic of : (I)Landroid/os/UserHandle;
    //   271: invokevirtual onCreate : (Landroid/os/UserHandle;)V
    //   274: aload #8
    //   276: astore #4
    //   278: aload #10
    //   280: invokevirtual onBind : ()Landroid/os/IBinder;
    //   283: astore #8
    //   285: aload #8
    //   287: astore #4
    //   289: aload #9
    //   291: aload #6
    //   293: aload #10
    //   295: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   298: pop
    //   299: aload #8
    //   301: astore #4
    //   303: goto -> 368
    //   306: astore #8
    //   308: new java/lang/StringBuilder
    //   311: astore #9
    //   313: aload #9
    //   315: invokespecial <init> : ()V
    //   318: aload #9
    //   320: ldc_w 'Agent threw during creation: '
    //   323: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: pop
    //   327: aload #9
    //   329: aload #8
    //   331: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   334: pop
    //   335: ldc 'ActivityThread'
    //   337: aload #9
    //   339: invokevirtual toString : ()Ljava/lang/String;
    //   342: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   345: pop
    //   346: aload_1
    //   347: getfield backupMode : I
    //   350: iconst_2
    //   351: if_icmpeq -> 368
    //   354: aload_1
    //   355: getfield backupMode : I
    //   358: iconst_3
    //   359: if_icmpne -> 365
    //   362: goto -> 368
    //   365: aload #8
    //   367: athrow
    //   368: invokestatic getService : ()Landroid/app/IActivityManager;
    //   371: aload #6
    //   373: aload #4
    //   375: aload_1
    //   376: getfield userId : I
    //   379: invokeinterface backupAgentCreated : (Ljava/lang/String;Landroid/os/IBinder;I)V
    //   384: return
    //   385: astore_1
    //   386: aload_1
    //   387: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   390: athrow
    //   391: astore_1
    //   392: new java/lang/StringBuilder
    //   395: dup
    //   396: invokespecial <init> : ()V
    //   399: astore #4
    //   401: aload #4
    //   403: ldc_w 'Unable to create BackupAgent '
    //   406: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: pop
    //   410: aload #4
    //   412: aload #7
    //   414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: pop
    //   418: aload #4
    //   420: ldc_w ': '
    //   423: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: pop
    //   427: aload #4
    //   429: aload_1
    //   430: invokevirtual toString : ()Ljava/lang/String;
    //   433: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: pop
    //   437: new java/lang/RuntimeException
    //   440: dup
    //   441: aload #4
    //   443: invokevirtual toString : ()Ljava/lang/String;
    //   446: aload_1
    //   447: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   450: athrow
    //   451: astore_1
    //   452: aload_1
    //   453: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   456: athrow
    // Exception table:
    //   from	to	target	type
    //   0	30	451	android/os/RemoteException
    //   35	78	451	android/os/RemoteException
    //   165	187	391	java/lang/Exception
    //   192	199	391	java/lang/Exception
    //   206	224	306	java/lang/Exception
    //   228	236	306	java/lang/Exception
    //   240	247	306	java/lang/Exception
    //   251	258	306	java/lang/Exception
    //   262	274	306	java/lang/Exception
    //   278	285	306	java/lang/Exception
    //   289	299	306	java/lang/Exception
    //   308	362	391	java/lang/Exception
    //   365	368	391	java/lang/Exception
    //   368	384	385	android/os/RemoteException
    //   368	384	391	java/lang/Exception
    //   386	391	391	java/lang/Exception
  }
  
  private void handleCreateService(CreateServiceData paramCreateServiceData) {
    unscheduleGcIdler();
    LoadedApk loadedApk = getPackageInfoNoCheck(paramCreateServiceData.info.applicationInfo, paramCreateServiceData.compatInfo);
    Service service1 = null;
    Service service2 = service1;
    try {
      ContextImpl contextImpl = ContextImpl.createAppContext(this, loadedApk);
      service2 = service1;
      Application application = loadedApk.makeApplication(false, this.mInstrumentation);
      service2 = service1;
      ClassLoader classLoader = loadedApk.getClassLoader();
      service2 = service1;
      service1 = loadedApk.getAppFactory().instantiateService(classLoader, paramCreateServiceData.info.name, paramCreateServiceData.intent);
      service2 = service1;
      contextImpl.getResources().addLoaders((ResourcesLoader[])application.getResources().getLoaders().toArray((Object[])new ResourcesLoader[0]));
      service2 = service1;
      contextImpl.setOuterContext((Context)service1);
      service2 = service1;
      service1.attach(contextImpl, this, paramCreateServiceData.info.name, paramCreateServiceData.token, application, ActivityManager.getService());
      service2 = service1;
      service1.onCreate();
      service2 = service1;
      this.mServices.put(paramCreateServiceData.token, service1);
      service2 = service1;
      try {
        ActivityManager.getService().serviceDoneExecuting(paramCreateServiceData.token, 0, 0, 0);
      } catch (RemoteException remoteException) {
        service2 = service1;
        throw remoteException.rethrowFromSystemServer();
      } 
    } catch (Exception exception) {
      if (!this.mInstrumentation.onException(service2, exception)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to create service ");
        stringBuilder.append(paramCreateServiceData.info.name);
        stringBuilder.append(": ");
        stringBuilder.append(exception.toString());
        throw new RuntimeException(stringBuilder.toString(), exception);
      } 
    } 
  }
  
  private void handleDestroyBackupAgent(CreateBackupAgentData paramCreateBackupAgentData) {
    String str = (getPackageInfoNoCheck(paramCreateBackupAgentData.appInfo, paramCreateBackupAgentData.compatInfo)).mPackageName;
    ArrayMap<String, BackupAgent> arrayMap = getBackupAgentsForUser(paramCreateBackupAgentData.userId);
    BackupAgent backupAgent = (BackupAgent)arrayMap.get(str);
    if (backupAgent != null) {
      try {
        backupAgent.onDestroy();
      } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Exception thrown in onDestroy by backup agent of ");
        stringBuilder.append(paramCreateBackupAgentData.appInfo);
        Slog.w("ActivityThread", stringBuilder.toString());
        exception.printStackTrace();
      } 
      arrayMap.remove(str);
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Attempt to destroy unknown backup agent ");
      stringBuilder.append(paramCreateBackupAgentData);
      Slog.w("ActivityThread", stringBuilder.toString());
    } 
  }
  
  private void handleDumpActivity(DumpComponentInfo paramDumpComponentInfo) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.allowThreadDiskWrites();
    try {
      ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramDumpComponentInfo.token);
      if (activityClientRecord != null && activityClientRecord.activity != null) {
        FastPrintWriter fastPrintWriter = new FastPrintWriter();
        FileOutputStream fileOutputStream = new FileOutputStream();
        this(paramDumpComponentInfo.fd.getFileDescriptor());
        this(fileOutputStream);
        activityClientRecord.activity.dump(paramDumpComponentInfo.prefix, paramDumpComponentInfo.fd.getFileDescriptor(), (PrintWriter)fastPrintWriter, paramDumpComponentInfo.args);
        fastPrintWriter.flush();
      } 
      return;
    } finally {
      IoUtils.closeQuietly((AutoCloseable)paramDumpComponentInfo.fd);
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  static void handleDumpHeap(DumpHeapData paramDumpHeapData) {
    if (paramDumpHeapData.runGc) {
      System.gc();
      System.runFinalization();
      System.gc();
    } 
    try {
      ParcelFileDescriptor parcelFileDescriptor = paramDumpHeapData.fd;
      try {
        if (paramDumpHeapData.managed) {
          Debug.dumpHprofData(paramDumpHeapData.path, parcelFileDescriptor.getFileDescriptor());
        } else if (paramDumpHeapData.mallocInfo) {
          Debug.dumpNativeMallocInfo(parcelFileDescriptor.getFileDescriptor());
        } else {
          Debug.dumpNativeHeap(parcelFileDescriptor.getFileDescriptor());
        } 
      } finally {
        if (parcelFileDescriptor != null)
          try {
            parcelFileDescriptor.close();
          } finally {
            parcelFileDescriptor = null;
          }  
      } 
    } catch (IOException iOException) {
      if (paramDumpHeapData.managed) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Managed heap dump failed on path ");
        stringBuilder.append(paramDumpHeapData.path);
        stringBuilder.append(" -- can the process access this path?");
        Slog.w("ActivityThread", stringBuilder.toString(), iOException);
      } else {
        Slog.w("ActivityThread", "Failed to dump heap", iOException);
      } 
    } catch (RuntimeException runtimeException) {
      Slog.wtf("ActivityThread", "Heap dumper threw a runtime exception", runtimeException);
    } 
    try {
      ActivityManager.getService().dumpHeapFinished(paramDumpHeapData.path);
      if (paramDumpHeapData.finishCallback != null)
        paramDumpHeapData.finishCallback.sendResult(null); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private void handleDumpProvider(DumpComponentInfo paramDumpComponentInfo) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.allowThreadDiskWrites();
    try {
      ProviderClientRecord providerClientRecord = (ProviderClientRecord)this.mLocalProviders.get(paramDumpComponentInfo.token);
      if (providerClientRecord != null && providerClientRecord.mLocalProvider != null) {
        FastPrintWriter fastPrintWriter = new FastPrintWriter();
        FileOutputStream fileOutputStream = new FileOutputStream();
        this(paramDumpComponentInfo.fd.getFileDescriptor());
        this(fileOutputStream);
        providerClientRecord.mLocalProvider.dump(paramDumpComponentInfo.fd.getFileDescriptor(), (PrintWriter)fastPrintWriter, paramDumpComponentInfo.args);
        fastPrintWriter.flush();
      } 
      return;
    } finally {
      IoUtils.closeQuietly((AutoCloseable)paramDumpComponentInfo.fd);
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  private void handleDumpService(DumpComponentInfo paramDumpComponentInfo) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.allowThreadDiskWrites();
    try {
      Service service = (Service)this.mServices.get(paramDumpComponentInfo.token);
      if (service != null) {
        FastPrintWriter fastPrintWriter = new FastPrintWriter();
        FileOutputStream fileOutputStream = new FileOutputStream();
        this(paramDumpComponentInfo.fd.getFileDescriptor());
        this(fileOutputStream);
        service.dump(paramDumpComponentInfo.fd.getFileDescriptor(), (PrintWriter)fastPrintWriter, paramDumpComponentInfo.args);
        fastPrintWriter.flush();
      } 
      return;
    } finally {
      IoUtils.closeQuietly((AutoCloseable)paramDumpComponentInfo.fd);
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  private void handleEnterAnimationComplete(IBinder paramIBinder) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord != null)
      activityClientRecord.activity.dispatchEnterAnimationComplete(); 
  }
  
  private void handleLocalVoiceInteractionStarted(IBinder paramIBinder, IVoiceInteractor paramIVoiceInteractor) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord != null) {
      activityClientRecord.voiceInteractor = paramIVoiceInteractor;
      activityClientRecord.activity.setVoiceInteractor(paramIVoiceInteractor);
      if (paramIVoiceInteractor == null) {
        activityClientRecord.activity.onLocalVoiceInteractionStopped();
      } else {
        activityClientRecord.activity.onLocalVoiceInteractionStarted();
      } 
    } 
  }
  
  private void handlePerformDirectAction(IBinder paramIBinder, String paramString, Bundle paramBundle, CancellationSignal paramCancellationSignal, RemoteCallback paramRemoteCallback) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord != null) {
      Bundle bundle;
      int i = activityClientRecord.getLifecycleState();
      if (i < 2 || i >= 5) {
        paramRemoteCallback.sendResult(null);
        return;
      } 
      if (paramBundle != null) {
        bundle = paramBundle;
      } else {
        bundle = Bundle.EMPTY;
      } 
      Activity activity = activityClientRecord.activity;
      Objects.requireNonNull(paramRemoteCallback);
      activity.onPerformDirectAction(paramString, bundle, paramCancellationSignal, new _$$Lambda$ZsFzoG2loyqNOR2cNbo_thrNK5c(paramRemoteCallback));
    } else {
      paramRemoteCallback.sendResult(null);
    } 
  }
  
  private void handleReceiver(ReceiverData paramReceiverData) {
    unscheduleGcIdler();
    String str = paramReceiverData.intent.getComponent().getClassName();
    LoadedApk loadedApk = getPackageInfoNoCheck(paramReceiverData.info.applicationInfo, paramReceiverData.compatInfo);
    IActivityManager iActivityManager = ActivityManager.getService();
    try {
      RuntimeException runtimeException;
      ContextImpl contextImpl1 = (ContextImpl)loadedApk.makeApplication(false, this.mInstrumentation).getBaseContext();
      ContextImpl contextImpl2 = contextImpl1;
      if (paramReceiverData.info.splitName != null)
        contextImpl2 = (ContextImpl)contextImpl1.createContextForSplit(paramReceiverData.info.splitName); 
      ClassLoader classLoader = contextImpl2.getClassLoader();
      paramReceiverData.intent.setExtrasClassLoader(classLoader);
      paramReceiverData.intent.prepareToEnterProcess();
      paramReceiverData.setExtrasClassLoader(classLoader);
      BroadcastReceiver broadcastReceiver = loadedApk.getAppFactory().instantiateReceiver(classLoader, paramReceiverData.info.name, paramReceiverData.intent);
      try {
        sCurrentBroadcastIntent.set(paramReceiverData.intent);
        broadcastReceiver.setPendingResult(paramReceiverData);
        broadcastReceiver.onReceive(contextImpl2.getReceiverRestrictedContext(), paramReceiverData.intent);
      } catch (Exception exception) {
        paramReceiverData.sendFinished(iActivityManager);
        boolean bool = this.mInstrumentation.onException(broadcastReceiver, exception);
        if (!bool) {
          runtimeException = new RuntimeException();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Unable to start receiver ");
          stringBuilder.append(str);
          stringBuilder.append(": ");
          stringBuilder.append(exception.toString());
          this(stringBuilder.toString(), exception);
          throw runtimeException;
        } 
      } finally {}
      sCurrentBroadcastIntent.set(null);
      if (runtimeException.getPendingResult() != null)
        paramReceiverData.finish(); 
      return;
    } catch (Exception exception) {
      paramReceiverData.sendFinished(iActivityManager);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to instantiate receiver ");
      stringBuilder.append(str);
      stringBuilder.append(": ");
      stringBuilder.append(exception.toString());
      throw new RuntimeException(stringBuilder.toString(), exception);
    } 
  }
  
  private void handleRelaunchActivityInner(ActivityClientRecord paramActivityClientRecord, int paramInt, List<ResultInfo> paramList, List<ReferrerIntent> paramList1, PendingTransactionActions paramPendingTransactionActions, boolean paramBoolean, Configuration paramConfiguration, String paramString) {
    Intent intent = paramActivityClientRecord.activity.mIntent;
    if (!paramActivityClientRecord.paused)
      performPauseActivity(paramActivityClientRecord, false, paramString, (PendingTransactionActions)null); 
    if (!paramActivityClientRecord.stopped)
      callActivityOnStop(paramActivityClientRecord, true, paramString); 
    handleDestroyActivity(paramActivityClientRecord.token, false, paramInt, true, paramString);
    paramActivityClientRecord.activity = null;
    paramActivityClientRecord.window = null;
    paramActivityClientRecord.hideForNow = false;
    paramActivityClientRecord.nextIdle = null;
    if (paramList != null)
      if (paramActivityClientRecord.pendingResults == null) {
        paramActivityClientRecord.pendingResults = paramList;
      } else {
        paramActivityClientRecord.pendingResults.addAll(paramList);
      }  
    if (paramList1 != null)
      if (paramActivityClientRecord.pendingIntents == null) {
        paramActivityClientRecord.pendingIntents = paramList1;
      } else {
        paramActivityClientRecord.pendingIntents.addAll(paramList1);
      }  
    paramActivityClientRecord.startsNotResumed = paramBoolean;
    paramActivityClientRecord.overrideConfig = paramConfiguration;
    handleLaunchActivity(paramActivityClientRecord, paramPendingTransactionActions, intent);
  }
  
  private void handleRelaunchActivityLocally(IBinder paramIBinder) {
    Configuration configuration;
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord == null) {
      Log.w("ActivityThread", "Activity to relaunch no longer exists");
      return;
    } 
    int i = activityClientRecord.getLifecycleState();
    if (i < 3 || i > 5) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Activity state must be in [ON_RESUME..ON_STOP] in order to be relaunched,current state is ");
      stringBuilder.append(i);
      Log.w("ActivityThread", stringBuilder.toString());
      return;
    } 
    if (activityClientRecord.createdConfig != null) {
      configuration = activityClientRecord.createdConfig;
    } else {
      configuration = this.mConfiguration;
    } 
    ActivityRelaunchItem activityRelaunchItem = ActivityRelaunchItem.obtain(null, null, 0, new MergedConfiguration(configuration, activityClientRecord.overrideConfig), activityClientRecord.mPreserveWindow);
    ActivityLifecycleItem activityLifecycleItem = TransactionExecutorHelper.getLifecycleRequestForCurrentState(activityClientRecord);
    ClientTransaction clientTransaction = ClientTransaction.obtain(this.mAppThread, activityClientRecord.token);
    clientTransaction.addCallback((ClientTransactionItem)activityRelaunchItem);
    clientTransaction.setLifecycleStateRequest(activityLifecycleItem);
    executeTransaction(clientTransaction);
  }
  
  private void handleRequestDirectActions(IBinder paramIBinder, IVoiceInteractor paramIVoiceInteractor, CancellationSignal paramCancellationSignal, RemoteCallback paramRemoteCallback) {
    StringBuilder stringBuilder;
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("requestDirectActions(): no activity for ");
      stringBuilder.append(paramIBinder);
      Log.w("ActivityThread", stringBuilder.toString());
      paramRemoteCallback.sendResult(null);
      return;
    } 
    int i = activityClientRecord.getLifecycleState();
    if (i < 2 || i >= 5) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("requestDirectActions(");
      stringBuilder1.append(activityClientRecord);
      stringBuilder1.append("): wrong lifecycle: ");
      stringBuilder1.append(i);
      Log.w("ActivityThread", stringBuilder1.toString());
      paramRemoteCallback.sendResult(null);
      return;
    } 
    if (activityClientRecord.activity.mVoiceInteractor == null || activityClientRecord.activity.mVoiceInteractor.mInteractor.asBinder() != stringBuilder.asBinder()) {
      if (activityClientRecord.activity.mVoiceInteractor != null)
        activityClientRecord.activity.mVoiceInteractor.destroy(); 
      activityClientRecord.activity.mVoiceInteractor = new VoiceInteractor((IVoiceInteractor)stringBuilder, (Context)activityClientRecord.activity, activityClientRecord.activity, Looper.myLooper());
    } 
    activityClientRecord.activity.onGetDirectActions(paramCancellationSignal, new _$$Lambda$ActivityThread$PBg2BVvZuEBxl1ijbGf3X_LErcY(activityClientRecord, paramRemoteCallback));
  }
  
  private void handleRunIsolatedEntryPoint(String paramString, String[] paramArrayOfString) {
    try {
      Class.forName(paramString).getMethod("main", new Class[] { String[].class }).invoke(null, new Object[] { paramArrayOfString });
      System.exit(0);
      return;
    } catch (ReflectiveOperationException reflectiveOperationException) {
      throw new AndroidRuntimeException("runIsolatedEntryPoint failed", reflectiveOperationException);
    } 
  }
  
  private void handleServiceArgs(ServiceArgsData paramServiceArgsData) {
    Service service = (Service)this.mServices.get(paramServiceArgsData.token);
    if (service != null)
      try {
        char c;
        if (paramServiceArgsData.args != null) {
          paramServiceArgsData.args.setExtrasClassLoader(service.getClassLoader());
          paramServiceArgsData.args.prepareToEnterProcess();
        } 
        if (!paramServiceArgsData.taskRemoved) {
          c = service.onStartCommand(paramServiceArgsData.args, paramServiceArgsData.flags, paramServiceArgsData.startId);
        } else {
          service.onTaskRemoved(paramServiceArgsData.args);
          c = 'Ϩ';
        } 
        QueuedWork.waitToFinish();
        try {
          ActivityManager.getService().serviceDoneExecuting(paramServiceArgsData.token, 1, paramServiceArgsData.startId, c);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      } catch (Exception exception) {
        if (!this.mInstrumentation.onException(service, exception)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unable to start service ");
          stringBuilder.append(service);
          stringBuilder.append(" with ");
          stringBuilder.append(paramServiceArgsData.args);
          stringBuilder.append(": ");
          stringBuilder.append(exception.toString());
          throw new RuntimeException(stringBuilder.toString(), exception);
        } 
      }  
  }
  
  private void handleSetCoreSettings(Bundle paramBundle) {
    synchronized (this.mResourcesManager) {
      this.mCoreSettings = paramBundle;
      onCoreSettingsChange();
      return;
    } 
  }
  
  private void handleStartBinderTracking() {
    Binder.enableTracing();
  }
  
  private void handleStopBinderTrackingAndDump(ParcelFileDescriptor paramParcelFileDescriptor) {
    try {
      Binder.disableTracing();
      Binder.getTransactionTracker().writeTracesToFile(paramParcelFileDescriptor);
      return;
    } finally {
      IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
      Binder.getTransactionTracker().clearTraces();
    } 
  }
  
  private void handleStopService(IBinder paramIBinder) {
    StringBuilder stringBuilder;
    Service service = (Service)this.mServices.remove(paramIBinder);
    if (service != null) {
      try {
        service.onDestroy();
        service.detachAndCleanUp();
        Context context = service.getBaseContext();
        if (context instanceof ContextImpl) {
          String str = service.getClassName();
          ((ContextImpl)context).scheduleFinalCleanup(str, "Service");
        } 
        QueuedWork.waitToFinish();
        try {
          ActivityManager.getService().serviceDoneExecuting(paramIBinder, 2, 0, 0);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      } catch (Exception exception) {
        StringBuilder stringBuilder1;
        if (this.mInstrumentation.onException(service, exception)) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("handleStopService: exception for ");
          stringBuilder1.append(paramIBinder);
          Slog.i("ActivityThread", stringBuilder1.toString(), exception);
        } else {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unable to stop service ");
          stringBuilder.append(stringBuilder1);
          stringBuilder.append(": ");
          stringBuilder.append(exception.toString());
          throw new RuntimeException(stringBuilder.toString(), exception);
        } 
      } 
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("handleStopService: token=");
      stringBuilder1.append(stringBuilder);
      stringBuilder1.append(" not found.");
      Slog.i("ActivityThread", stringBuilder1.toString());
    } 
  }
  
  private void handleTrimMemory(int paramInt) {
    Trace.traceBegin(64L, "trimMemory");
    if (paramInt >= 80) {
      Iterator<PropertyInvalidatedCache> iterator = PropertyInvalidatedCache.getActiveCaches().iterator();
      while (iterator.hasNext())
        ((PropertyInvalidatedCache)iterator.next()).clear(); 
    } 
    ArrayList<ComponentCallbacks2> arrayList = collectComponentCallbacks(true, (Configuration)null);
    int i = arrayList.size();
    for (byte b = 0; b < i; b++)
      ((ComponentCallbacks2)arrayList.get(b)).onTrimMemory(paramInt); 
    WindowManagerGlobal.getInstance().trimMemory(paramInt);
    Trace.traceEnd(64L);
    if (SystemProperties.getInt("debug.am.run_gc_trim_level", 2147483647) <= paramInt) {
      unscheduleGcIdler();
      doGcIfNeeded("tm");
    } 
    if (SystemProperties.getInt("debug.am.run_mallopt_trim_level", 2147483647) <= paramInt) {
      unschedulePurgeIdler();
      purgePendingResources();
    } 
  }
  
  private void handleUnbindService(BindServiceData paramBindServiceData) {
    Service service = (Service)this.mServices.get(paramBindServiceData.token);
    if (service != null)
      try {
        paramBindServiceData.intent.setExtrasClassLoader(service.getClassLoader());
        paramBindServiceData.intent.prepareToEnterProcess();
        boolean bool = service.onUnbind(paramBindServiceData.intent);
        if (bool) {
          try {
            ActivityManager.getService().unbindFinished(paramBindServiceData.token, paramBindServiceData.intent, bool);
          } catch (RemoteException remoteException) {
            throw remoteException.rethrowFromSystemServer();
          } 
        } else {
          ActivityManager.getService().serviceDoneExecuting(paramBindServiceData.token, 0, 0, 0);
        } 
      } catch (Exception exception) {
        if (!this.mInstrumentation.onException(service, exception)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unable to unbind to service ");
          stringBuilder.append(service);
          stringBuilder.append(" with ");
          stringBuilder.append(paramBindServiceData.intent);
          stringBuilder.append(": ");
          stringBuilder.append(exception.toString());
          throw new RuntimeException(stringBuilder.toString(), exception);
        } 
      }  
  }
  
  private void handleUpdatePackageCompatibilityInfo(UpdateCompatibilityData paramUpdateCompatibilityData) {
    LoadedApk loadedApk = peekPackageInfo(paramUpdateCompatibilityData.pkg, false);
    if (loadedApk != null)
      loadedApk.setCompatibilityInfo(paramUpdateCompatibilityData.info); 
    loadedApk = peekPackageInfo(paramUpdateCompatibilityData.pkg, true);
    if (loadedApk != null)
      loadedApk.setCompatibilityInfo(paramUpdateCompatibilityData.info); 
    handleConfigurationChanged(this.mConfiguration, paramUpdateCompatibilityData.info);
    WindowManagerGlobal.getInstance().reportNewConfiguration(this.mConfiguration);
  }
  
  private void handleWindowingModeChangeIfNeeded(Activity paramActivity, Configuration paramConfiguration) {
    int i = paramConfiguration.windowConfiguration.getWindowingMode();
    IBinder iBinder = paramActivity.getActivityToken();
    int j = ((Integer)this.mLastReportedWindowingMode.getOrDefault(iBinder, Integer.valueOf(0))).intValue();
    if (j == i)
      return; 
    if (i == 2) {
      paramActivity.dispatchPictureInPictureModeChanged(true, paramConfiguration);
    } else if (j == 2) {
      paramActivity.dispatchPictureInPictureModeChanged(false, paramConfiguration);
    } 
    boolean bool1 = WindowConfiguration.inMultiWindowMode(j);
    boolean bool2 = WindowConfiguration.inMultiWindowMode(i);
    if (bool1 != bool2)
      paramActivity.dispatchMultiWindowModeChanged(bool2, paramConfiguration); 
    this.mLastReportedWindowingMode.put(iBinder, Integer.valueOf(i));
  }
  
  private final void incProviderRefLocked(ProviderRefCount paramProviderRefCount, boolean paramBoolean) {
    if (paramBoolean) {
      paramProviderRefCount.stableCount++;
      if (paramProviderRefCount.stableCount == 1) {
        boolean bool;
        if (paramProviderRefCount.removePending) {
          bool = true;
          paramProviderRefCount.removePending = false;
          this.mH.removeMessages(131, paramProviderRefCount);
        } else {
          bool = false;
        } 
        try {
          ActivityManager.getService().refContentProvider(paramProviderRefCount.holder.connection, 1, bool);
        } catch (RemoteException remoteException) {}
      } 
    } else {
      ((ProviderRefCount)remoteException).unstableCount++;
      if (((ProviderRefCount)remoteException).unstableCount == 1)
        if (((ProviderRefCount)remoteException).removePending) {
          ((ProviderRefCount)remoteException).removePending = false;
          this.mH.removeMessages(131, remoteException);
        } else {
          try {
            ActivityManager.getService().refContentProvider(((ProviderRefCount)remoteException).holder.connection, 0, 1);
          } catch (RemoteException remoteException1) {}
        }  
    } 
  }
  
  public static void initializeMainlineModules() {
    TelephonyFrameworkInitializer.setTelephonyServiceManager(new TelephonyServiceManager());
    StatsFrameworkInitializer.setStatsServiceManager(new StatsServiceManager());
  }
  
  private void installContentProviders(Context paramContext, List<ProviderInfo> paramList) {
    ArrayList<ContentProviderHolder> arrayList = new ArrayList();
    Iterator<ProviderInfo> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      ContentProviderHolder contentProviderHolder = installProvider(paramContext, (ContentProviderHolder)null, iterator.next(), false, true, true);
      if (contentProviderHolder != null) {
        contentProviderHolder.noReleaseNeeded = true;
        arrayList.add(contentProviderHolder);
      } 
    } 
    try {
      ActivityManager.getService().publishContentProviders(getApplicationThread(), arrayList);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private ContentProviderHolder installProvider(Context paramContext, ContentProviderHolder paramContentProviderHolder, ProviderInfo paramProviderInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    // Byte code:
    //   0: aload_2
    //   1: ifnull -> 25
    //   4: aload_2
    //   5: getfield provider : Landroid/content/IContentProvider;
    //   8: ifnonnull -> 14
    //   11: goto -> 25
    //   14: aload_2
    //   15: getfield provider : Landroid/content/IContentProvider;
    //   18: astore #7
    //   20: aconst_null
    //   21: astore_1
    //   22: goto -> 399
    //   25: iload #4
    //   27: ifeq -> 88
    //   30: new java/lang/StringBuilder
    //   33: dup
    //   34: invokespecial <init> : ()V
    //   37: astore #7
    //   39: aload #7
    //   41: ldc_w 'Loading provider '
    //   44: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: pop
    //   48: aload #7
    //   50: aload_3
    //   51: getfield authority : Ljava/lang/String;
    //   54: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: pop
    //   58: aload #7
    //   60: ldc_w ': '
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload #7
    //   69: aload_3
    //   70: getfield name : Ljava/lang/String;
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: ldc 'ActivityThread'
    //   79: aload #7
    //   81: invokevirtual toString : ()Ljava/lang/String;
    //   84: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   87: pop
    //   88: aconst_null
    //   89: astore #7
    //   91: aload_3
    //   92: getfield applicationInfo : Landroid/content/pm/ApplicationInfo;
    //   95: astore #8
    //   97: aload_1
    //   98: invokevirtual getPackageName : ()Ljava/lang/String;
    //   101: aload #8
    //   103: getfield packageName : Ljava/lang/String;
    //   106: invokevirtual equals : (Ljava/lang/Object;)Z
    //   109: ifeq -> 115
    //   112: goto -> 179
    //   115: aload_0
    //   116: getfield mInitialApplication : Landroid/app/Application;
    //   119: astore #9
    //   121: aload #9
    //   123: ifnull -> 150
    //   126: aload #9
    //   128: invokevirtual getPackageName : ()Ljava/lang/String;
    //   131: aload #8
    //   133: getfield packageName : Ljava/lang/String;
    //   136: invokevirtual equals : (Ljava/lang/Object;)Z
    //   139: ifeq -> 150
    //   142: aload_0
    //   143: getfield mInitialApplication : Landroid/app/Application;
    //   146: astore_1
    //   147: goto -> 179
    //   150: aload #8
    //   152: getfield packageName : Ljava/lang/String;
    //   155: astore #9
    //   157: aload_1
    //   158: aload #9
    //   160: iconst_1
    //   161: invokevirtual createPackageContext : (Ljava/lang/String;I)Landroid/content/Context;
    //   164: astore_1
    //   165: goto -> 179
    //   168: astore_1
    //   169: aload #7
    //   171: astore_1
    //   172: goto -> 179
    //   175: astore_1
    //   176: aload #7
    //   178: astore_1
    //   179: aload_1
    //   180: ifnonnull -> 238
    //   183: new java/lang/StringBuilder
    //   186: dup
    //   187: invokespecial <init> : ()V
    //   190: astore_1
    //   191: aload_1
    //   192: ldc_w 'Unable to get context for package '
    //   195: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: pop
    //   199: aload_1
    //   200: aload #8
    //   202: getfield packageName : Ljava/lang/String;
    //   205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: pop
    //   209: aload_1
    //   210: ldc_w ' while loading content provider '
    //   213: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: pop
    //   217: aload_1
    //   218: aload_3
    //   219: getfield name : Ljava/lang/String;
    //   222: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: pop
    //   226: ldc 'ActivityThread'
    //   228: aload_1
    //   229: invokevirtual toString : ()Ljava/lang/String;
    //   232: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   235: pop
    //   236: aconst_null
    //   237: areturn
    //   238: aload_1
    //   239: astore #7
    //   241: aload_3
    //   242: getfield splitName : Ljava/lang/String;
    //   245: ifnull -> 271
    //   248: aload_1
    //   249: aload_3
    //   250: getfield splitName : Ljava/lang/String;
    //   253: invokevirtual createContextForSplit : (Ljava/lang/String;)Landroid/content/Context;
    //   256: astore #7
    //   258: goto -> 271
    //   261: astore_1
    //   262: new java/lang/RuntimeException
    //   265: dup
    //   266: aload_1
    //   267: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   270: athrow
    //   271: aload #7
    //   273: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   276: astore #9
    //   278: aload_0
    //   279: aload #8
    //   281: getfield packageName : Ljava/lang/String;
    //   284: iconst_1
    //   285: invokevirtual peekPackageInfo : (Ljava/lang/String;Z)Landroid/app/LoadedApk;
    //   288: astore #8
    //   290: aload #8
    //   292: astore_1
    //   293: aload #8
    //   295: ifnonnull -> 306
    //   298: aload_0
    //   299: invokevirtual getSystemContext : ()Landroid/app/ContextImpl;
    //   302: getfield mPackageInfo : Landroid/app/LoadedApk;
    //   305: astore_1
    //   306: aload_1
    //   307: invokevirtual getAppFactory : ()Landroid/app/AppComponentFactory;
    //   310: aload #9
    //   312: aload_3
    //   313: getfield name : Ljava/lang/String;
    //   316: invokevirtual instantiateProvider : (Ljava/lang/ClassLoader;Ljava/lang/String;)Landroid/content/ContentProvider;
    //   319: astore_1
    //   320: aload_1
    //   321: invokevirtual getIContentProvider : ()Landroid/content/IContentProvider;
    //   324: astore #8
    //   326: aload #8
    //   328: ifnonnull -> 388
    //   331: new java/lang/StringBuilder
    //   334: astore_1
    //   335: aload_1
    //   336: invokespecial <init> : ()V
    //   339: aload_1
    //   340: ldc_w 'Failed to instantiate class '
    //   343: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: pop
    //   347: aload_1
    //   348: aload_3
    //   349: getfield name : Ljava/lang/String;
    //   352: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: pop
    //   356: aload_1
    //   357: ldc_w ' from sourceDir '
    //   360: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: pop
    //   364: aload_1
    //   365: aload_3
    //   366: getfield applicationInfo : Landroid/content/pm/ApplicationInfo;
    //   369: getfield sourceDir : Ljava/lang/String;
    //   372: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: pop
    //   376: ldc 'ActivityThread'
    //   378: aload_1
    //   379: invokevirtual toString : ()Ljava/lang/String;
    //   382: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   385: pop
    //   386: aconst_null
    //   387: areturn
    //   388: aload_1
    //   389: aload #7
    //   391: aload_3
    //   392: invokevirtual attachInfo : (Landroid/content/Context;Landroid/content/pm/ProviderInfo;)V
    //   395: aload #8
    //   397: astore #7
    //   399: aload_0
    //   400: getfield mProviderMap : Landroid/util/ArrayMap;
    //   403: astore #8
    //   405: aload #8
    //   407: monitorenter
    //   408: aload #7
    //   410: invokeinterface asBinder : ()Landroid/os/IBinder;
    //   415: astore #9
    //   417: aload_1
    //   418: ifnull -> 525
    //   421: new android/content/ComponentName
    //   424: astore #10
    //   426: aload #10
    //   428: aload_3
    //   429: getfield packageName : Ljava/lang/String;
    //   432: aload_3
    //   433: getfield name : Ljava/lang/String;
    //   436: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   439: aload_0
    //   440: getfield mLocalProvidersByName : Landroid/util/ArrayMap;
    //   443: aload #10
    //   445: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   448: checkcast android/app/ActivityThread$ProviderClientRecord
    //   451: astore_2
    //   452: aload_2
    //   453: ifnull -> 466
    //   456: aload_2
    //   457: getfield mProvider : Landroid/content/IContentProvider;
    //   460: astore_1
    //   461: aload_2
    //   462: astore_1
    //   463: goto -> 517
    //   466: new android/app/ContentProviderHolder
    //   469: dup
    //   470: aload_3
    //   471: invokespecial <init> : (Landroid/content/pm/ProviderInfo;)V
    //   474: astore_2
    //   475: aload_2
    //   476: aload #7
    //   478: putfield provider : Landroid/content/IContentProvider;
    //   481: aload_2
    //   482: iconst_1
    //   483: putfield noReleaseNeeded : Z
    //   486: aload_0
    //   487: aload #7
    //   489: aload_1
    //   490: aload_2
    //   491: invokespecial installProviderAuthoritiesLocked : (Landroid/content/IContentProvider;Landroid/content/ContentProvider;Landroid/app/ContentProviderHolder;)Landroid/app/ActivityThread$ProviderClientRecord;
    //   494: astore_1
    //   495: aload_0
    //   496: getfield mLocalProviders : Landroid/util/ArrayMap;
    //   499: aload #9
    //   501: aload_1
    //   502: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   505: pop
    //   506: aload_0
    //   507: getfield mLocalProvidersByName : Landroid/util/ArrayMap;
    //   510: aload #10
    //   512: aload_1
    //   513: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   516: pop
    //   517: aload_1
    //   518: getfield mHolder : Landroid/app/ContentProviderHolder;
    //   521: astore_1
    //   522: goto -> 660
    //   525: aload_0
    //   526: getfield mProviderRefCountMap : Landroid/util/ArrayMap;
    //   529: aload #9
    //   531: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   534: checkcast android/app/ActivityThread$ProviderRefCount
    //   537: astore_3
    //   538: aload_3
    //   539: ifnull -> 579
    //   542: aload_3
    //   543: astore_1
    //   544: iload #5
    //   546: ifne -> 655
    //   549: aload_0
    //   550: aload_3
    //   551: iload #6
    //   553: invokespecial incProviderRefLocked : (Landroid/app/ActivityThread$ProviderRefCount;Z)V
    //   556: invokestatic getService : ()Landroid/app/IActivityManager;
    //   559: aload_2
    //   560: getfield connection : Landroid/os/IBinder;
    //   563: iload #6
    //   565: invokeinterface removeContentProvider : (Landroid/os/IBinder;Z)V
    //   570: goto -> 574
    //   573: astore_1
    //   574: aload_3
    //   575: astore_1
    //   576: goto -> 655
    //   579: aload_0
    //   580: aload #7
    //   582: aload_1
    //   583: aload_2
    //   584: invokespecial installProviderAuthoritiesLocked : (Landroid/content/IContentProvider;Landroid/content/ContentProvider;Landroid/app/ContentProviderHolder;)Landroid/app/ActivityThread$ProviderClientRecord;
    //   587: astore_3
    //   588: iload #5
    //   590: ifeq -> 612
    //   593: new android/app/ActivityThread$ProviderRefCount
    //   596: astore_1
    //   597: aload_1
    //   598: aload_2
    //   599: aload_3
    //   600: sipush #1000
    //   603: sipush #1000
    //   606: invokespecial <init> : (Landroid/app/ContentProviderHolder;Landroid/app/ActivityThread$ProviderClientRecord;II)V
    //   609: goto -> 644
    //   612: iload #6
    //   614: ifeq -> 632
    //   617: new android/app/ActivityThread$ProviderRefCount
    //   620: dup
    //   621: aload_2
    //   622: aload_3
    //   623: iconst_1
    //   624: iconst_0
    //   625: invokespecial <init> : (Landroid/app/ContentProviderHolder;Landroid/app/ActivityThread$ProviderClientRecord;II)V
    //   628: astore_1
    //   629: goto -> 644
    //   632: new android/app/ActivityThread$ProviderRefCount
    //   635: dup
    //   636: aload_2
    //   637: aload_3
    //   638: iconst_0
    //   639: iconst_1
    //   640: invokespecial <init> : (Landroid/app/ContentProviderHolder;Landroid/app/ActivityThread$ProviderClientRecord;II)V
    //   643: astore_1
    //   644: aload_0
    //   645: getfield mProviderRefCountMap : Landroid/util/ArrayMap;
    //   648: aload #9
    //   650: aload_1
    //   651: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   654: pop
    //   655: aload_1
    //   656: getfield holder : Landroid/app/ContentProviderHolder;
    //   659: astore_1
    //   660: aload #8
    //   662: monitorexit
    //   663: aload_1
    //   664: areturn
    //   665: astore_1
    //   666: aload #8
    //   668: monitorexit
    //   669: aload_1
    //   670: athrow
    //   671: astore_1
    //   672: goto -> 666
    //   675: astore_1
    //   676: aload_0
    //   677: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   680: aconst_null
    //   681: aload_1
    //   682: invokevirtual onException : (Ljava/lang/Object;Ljava/lang/Throwable;)Z
    //   685: ifeq -> 690
    //   688: aconst_null
    //   689: areturn
    //   690: new java/lang/StringBuilder
    //   693: dup
    //   694: invokespecial <init> : ()V
    //   697: astore_2
    //   698: aload_2
    //   699: ldc_w 'Unable to get provider '
    //   702: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   705: pop
    //   706: aload_2
    //   707: aload_3
    //   708: getfield name : Ljava/lang/String;
    //   711: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   714: pop
    //   715: aload_2
    //   716: ldc_w ': '
    //   719: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   722: pop
    //   723: aload_2
    //   724: aload_1
    //   725: invokevirtual toString : ()Ljava/lang/String;
    //   728: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   731: pop
    //   732: new java/lang/RuntimeException
    //   735: dup
    //   736: aload_2
    //   737: invokevirtual toString : ()Ljava/lang/String;
    //   740: aload_1
    //   741: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   744: athrow
    // Exception table:
    //   from	to	target	type
    //   150	157	175	android/content/pm/PackageManager$NameNotFoundException
    //   157	165	168	android/content/pm/PackageManager$NameNotFoundException
    //   248	258	261	android/content/pm/PackageManager$NameNotFoundException
    //   271	290	675	java/lang/Exception
    //   298	306	675	java/lang/Exception
    //   306	326	675	java/lang/Exception
    //   331	386	675	java/lang/Exception
    //   388	395	675	java/lang/Exception
    //   408	417	665	finally
    //   421	452	665	finally
    //   456	461	665	finally
    //   466	475	665	finally
    //   475	517	671	finally
    //   517	522	671	finally
    //   525	538	665	finally
    //   549	556	665	finally
    //   556	570	573	android/os/RemoteException
    //   556	570	665	finally
    //   579	588	665	finally
    //   593	609	665	finally
    //   617	629	665	finally
    //   632	644	665	finally
    //   644	655	665	finally
    //   655	660	665	finally
    //   660	663	671	finally
    //   666	669	671	finally
  }
  
  private ProviderClientRecord installProviderAuthoritiesLocked(IContentProvider paramIContentProvider, ContentProvider paramContentProvider, ContentProviderHolder paramContentProviderHolder) {
    String[] arrayOfString = paramContentProviderHolder.info.authority.split(";");
    int i = UserHandle.getUserId(paramContentProviderHolder.info.applicationInfo.uid);
    byte b1 = 0;
    if (paramIContentProvider != null) {
      int k = arrayOfString.length;
      for (byte b = 0; b < k; b++) {
        switch (arrayOfString[b]) {
          case "com.android.contacts":
          case "call_log":
          case "call_log_shadow":
          case "com.android.blockednumber":
          case "com.android.calendar":
          case "downloads":
          case "telephony":
            Binder.allowBlocking(paramIContentProvider.asBinder());
            break;
        } 
      } 
    } 
    ProviderClientRecord providerClientRecord = new ProviderClientRecord(arrayOfString, paramIContentProvider, paramContentProvider, paramContentProviderHolder);
    int j = arrayOfString.length;
    for (byte b2 = b1; b2 < j; b2++) {
      StringBuilder stringBuilder;
      String str = arrayOfString[b2];
      ProviderKey providerKey = new ProviderKey(str, i);
      if ((ProviderClientRecord)this.mProviderMap.get(providerKey) != null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Content provider ");
        stringBuilder.append(providerClientRecord.mHolder.info.name);
        stringBuilder.append(" already published as ");
        stringBuilder.append(str);
        Slog.w("ActivityThread", stringBuilder.toString());
      } else {
        this.mProviderMap.put(stringBuilder, providerClientRecord);
      } 
    } 
    return providerClientRecord;
  }
  
  private static boolean isLoadedApkResourceDirsUpToDate(LoadedApk paramLoadedApk, ApplicationInfo paramApplicationInfo) {
    boolean bool;
    Resources resources = paramLoadedApk.mResources;
    String[] arrayOfString1 = ArrayUtils.defeatNullable(paramLoadedApk.getOverlayDirs());
    String[] arrayOfString2 = ArrayUtils.defeatNullable(paramApplicationInfo.resourceDirs);
    if ((resources == null || resources.getAssets().isUpToDate()) && arrayOfString1.length == arrayOfString2.length && ArrayUtils.containsAll((Object[])arrayOfString1, (Object[])arrayOfString2)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isSystem() {
    boolean bool;
    if (sCurrentActivityThread != null) {
      bool = sCurrentActivityThread.mSystemThread;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static void main(String[] paramArrayOfString) {
    Trace.traceBegin(64L, "ActivityThreadMain");
    AndroidOs.install();
    CloseGuard.setEnabled(false);
    Environment.initForCurrentUser();
    TrustedCertificateStore.setDefaultUserDirectory(Environment.getUserConfigDirectory(UserHandle.myUserId()));
    initializeMainlineModules();
    Process.setArgV0("<pre-initialized>");
    Looper.prepareMainLooper();
    long l1 = 0L;
    long l2 = l1;
    if (paramArrayOfString != null) {
      int i = paramArrayOfString.length - 1;
      while (true) {
        l2 = l1;
        if (i >= 0) {
          l2 = l1;
          if (paramArrayOfString[i] != null) {
            l2 = l1;
            if (paramArrayOfString[i].startsWith("seq="))
              l2 = Long.parseLong(paramArrayOfString[i].substring("seq=".length())); 
          } 
          i--;
          l1 = l2;
          continue;
        } 
        break;
      } 
    } 
    ActivityThread activityThread = new ActivityThread();
    activityThread.attach(false, l2);
    if (sMainThreadHandler == null)
      sMainThreadHandler = activityThread.getHandler(); 
    Trace.traceEnd(64L);
    Looper.loop();
    throw new RuntimeException("Main thread loop unexpectedly exited");
  }
  
  private native void nDumpGraphicsInfo(FileDescriptor paramFileDescriptor);
  
  private native void nInitZygoteChildHeapProfiling();
  
  private native void nPurgePendingResources();
  
  private void onCoreSettingsChange() {
    if (updateDebugViewAttributeState())
      relaunchAllActivities(false); 
  }
  
  private void overrideApplicationDisplayAdjustments(IBinder paramIBinder, Consumer<DisplayAdjustments> paramConsumer) {
    Consumer consumer;
    if (this.mActiveRotationAdjustments == null)
      this.mActiveRotationAdjustments = new ArrayList<>(2); 
    if (paramConsumer != null) {
      this.mActiveRotationAdjustments.add(Pair.create(paramIBinder, paramConsumer));
      consumer = paramConsumer;
    } else {
      this.mActiveRotationAdjustments.removeIf(new _$$Lambda$ActivityThread$IYWPi27NLY9j2bLQQGfir7XNZZs((IBinder)consumer));
      if (this.mActiveRotationAdjustments.isEmpty()) {
        consumer = null;
      } else {
        ArrayList<Pair<IBinder, Consumer<DisplayAdjustments>>> arrayList = this.mActiveRotationAdjustments;
        consumer = (Consumer)((Pair)arrayList.get(arrayList.size() - 1)).second;
      } 
    } 
    this.mInitialApplication.getResources().overrideDisplayAdjustments(consumer);
  }
  
  private Configuration performActivityConfigurationChanged(Activity paramActivity, Configuration paramConfiguration1, Configuration paramConfiguration2, int paramInt, boolean paramBoolean) {
    if (paramActivity != null) {
      IBinder iBinder = paramActivity.getActivityToken();
      if (iBinder != null) {
        StringBuilder stringBuilder;
        handleWindowingModeChangeIfNeeded(paramActivity, paramConfiguration1);
        boolean bool = false;
        if (paramActivity.mCurrentConfig == null) {
          bool = true;
        } else {
          int i = paramActivity.mCurrentConfig.diffPublicOnly(paramConfiguration1);
          if (i == 0 && !paramBoolean && this.mResourcesManager.isSameResourcesOverrideConfig(iBinder, paramConfiguration2))
            return null; 
          if ((paramActivity.mActivityInfo.getRealConfigChanged() & i) == 0)
            bool = true; 
        } 
        Configuration configuration = paramActivity.getOverrideConfiguration();
        paramConfiguration2 = createNewConfigAndUpdateIfNotNull(paramConfiguration2, configuration);
        this.mResourcesManager.updateResourcesForActivity(iBinder, paramConfiguration2, paramInt, paramBoolean);
        paramActivity.mConfigChangeFlags = 0;
        paramActivity.mCurrentConfig = new Configuration(paramConfiguration1);
        paramConfiguration1 = createNewConfigAndUpdateIfNotNull(paramConfiguration1, configuration);
        if (paramBoolean)
          paramActivity.dispatchMovedToDisplay(paramInt, paramConfiguration1); 
        if (bool) {
          paramActivity.mCalled = false;
          paramActivity.onConfigurationChanged(paramConfiguration1);
          if (!paramActivity.mCalled) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Activity ");
            stringBuilder.append(paramActivity.getLocalClassName());
            stringBuilder.append(" did not call through to super.onConfigurationChanged()");
            throw new SuperNotCalledException(stringBuilder.toString());
          } 
        } 
        return (Configuration)stringBuilder;
      } 
      throw new IllegalArgumentException("Activity token not set. Is the activity attached?");
    } 
    throw new IllegalArgumentException("No activity provided.");
  }
  
  private void performConfigurationChanged(ComponentCallbacks2 paramComponentCallbacks2, Configuration paramConfiguration) {
    Configuration configuration = null;
    if (paramComponentCallbacks2 instanceof ContextThemeWrapper)
      configuration = ((ContextThemeWrapper)paramComponentCallbacks2).getOverrideConfiguration(); 
    paramComponentCallbacks2.onConfigurationChanged(createNewConfigAndUpdateIfNotNull(paramConfiguration, configuration));
  }
  
  private Configuration performConfigurationChangedForActivity(ActivityClientRecord paramActivityClientRecord, Configuration paramConfiguration, int paramInt, boolean paramBoolean) {
    paramActivityClientRecord.tmpConfig.setTo(paramConfiguration);
    if (paramActivityClientRecord.overrideConfig != null)
      paramActivityClientRecord.tmpConfig.updateFrom(paramActivityClientRecord.overrideConfig); 
    paramConfiguration = performActivityConfigurationChanged(paramActivityClientRecord.activity, paramActivityClientRecord.tmpConfig, paramActivityClientRecord.overrideConfig, paramInt, paramBoolean);
    freeTextLayoutCachesIfNeeded(paramActivityClientRecord.activity.mCurrentConfig.diff(paramActivityClientRecord.tmpConfig));
    return paramConfiguration;
  }
  
  private void performConfigurationChangedForActivity(ActivityClientRecord paramActivityClientRecord, Configuration paramConfiguration) {
    performConfigurationChangedForActivity(paramActivityClientRecord, paramConfiguration, paramActivityClientRecord.activity.getDisplayId(), false);
  }
  
  private Activity performLaunchActivity(ActivityClientRecord paramActivityClientRecord, Intent paramIntent) {
    // Byte code:
    //   0: aload_1
    //   1: getfield activityInfo : Landroid/content/pm/ActivityInfo;
    //   4: astore_3
    //   5: aload_1
    //   6: getfield packageInfo : Landroid/app/LoadedApk;
    //   9: ifnonnull -> 29
    //   12: aload_1
    //   13: aload_0
    //   14: aload_3
    //   15: getfield applicationInfo : Landroid/content/pm/ApplicationInfo;
    //   18: aload_1
    //   19: getfield compatInfo : Landroid/content/res/CompatibilityInfo;
    //   22: iconst_1
    //   23: invokevirtual getPackageInfo : (Landroid/content/pm/ApplicationInfo;Landroid/content/res/CompatibilityInfo;I)Landroid/app/LoadedApk;
    //   26: putfield packageInfo : Landroid/app/LoadedApk;
    //   29: aload_1
    //   30: getfield intent : Landroid/content/Intent;
    //   33: invokevirtual getComponent : ()Landroid/content/ComponentName;
    //   36: astore #4
    //   38: aload #4
    //   40: astore_3
    //   41: aload #4
    //   43: ifnonnull -> 70
    //   46: aload_1
    //   47: getfield intent : Landroid/content/Intent;
    //   50: aload_0
    //   51: getfield mInitialApplication : Landroid/app/Application;
    //   54: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   57: invokevirtual resolveActivity : (Landroid/content/pm/PackageManager;)Landroid/content/ComponentName;
    //   60: astore_3
    //   61: aload_1
    //   62: getfield intent : Landroid/content/Intent;
    //   65: aload_3
    //   66: invokevirtual setComponent : (Landroid/content/ComponentName;)Landroid/content/Intent;
    //   69: pop
    //   70: aload_1
    //   71: getfield activityInfo : Landroid/content/pm/ActivityInfo;
    //   74: getfield targetActivity : Ljava/lang/String;
    //   77: ifnull -> 106
    //   80: new android/content/ComponentName
    //   83: dup
    //   84: aload_1
    //   85: getfield activityInfo : Landroid/content/pm/ActivityInfo;
    //   88: getfield packageName : Ljava/lang/String;
    //   91: aload_1
    //   92: getfield activityInfo : Landroid/content/pm/ActivityInfo;
    //   95: getfield targetActivity : Ljava/lang/String;
    //   98: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   101: astore #4
    //   103: goto -> 109
    //   106: aload_3
    //   107: astore #4
    //   109: aload_0
    //   110: aload_1
    //   111: invokespecial createBaseContextForActivity : (Landroid/app/ActivityThread$ActivityClientRecord;)Landroid/app/ContextImpl;
    //   114: astore #5
    //   116: aconst_null
    //   117: astore #6
    //   119: aload #6
    //   121: astore_3
    //   122: aload #5
    //   124: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   127: astore #7
    //   129: aload #6
    //   131: astore_3
    //   132: aload_0
    //   133: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   136: aload #7
    //   138: aload #4
    //   140: invokevirtual getClassName : ()Ljava/lang/String;
    //   143: aload_1
    //   144: getfield intent : Landroid/content/Intent;
    //   147: invokevirtual newActivity : (Ljava/lang/ClassLoader;Ljava/lang/String;Landroid/content/Intent;)Landroid/app/Activity;
    //   150: astore #6
    //   152: aload #6
    //   154: astore_3
    //   155: aload #6
    //   157: invokevirtual getClass : ()Ljava/lang/Class;
    //   160: invokestatic incrementExpectedActivityCount : (Ljava/lang/Class;)V
    //   163: aload #6
    //   165: astore_3
    //   166: aload_1
    //   167: getfield intent : Landroid/content/Intent;
    //   170: aload #7
    //   172: invokevirtual setExtrasClassLoader : (Ljava/lang/ClassLoader;)V
    //   175: aload #6
    //   177: astore_3
    //   178: aload_1
    //   179: getfield intent : Landroid/content/Intent;
    //   182: invokevirtual prepareToEnterProcess : ()V
    //   185: aload #6
    //   187: astore_3
    //   188: aload_1
    //   189: getfield state : Landroid/os/Bundle;
    //   192: ifnull -> 207
    //   195: aload #6
    //   197: astore_3
    //   198: aload_1
    //   199: getfield state : Landroid/os/Bundle;
    //   202: aload #7
    //   204: invokevirtual setClassLoader : (Ljava/lang/ClassLoader;)V
    //   207: aload #6
    //   209: astore_3
    //   210: goto -> 228
    //   213: astore #6
    //   215: aload_0
    //   216: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   219: aload_3
    //   220: aload #6
    //   222: invokevirtual onException : (Ljava/lang/Object;Ljava/lang/Throwable;)Z
    //   225: ifeq -> 869
    //   228: aload_1
    //   229: getfield packageInfo : Landroid/app/LoadedApk;
    //   232: astore #6
    //   234: aload #6
    //   236: iconst_0
    //   237: aload_0
    //   238: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   241: invokevirtual makeApplication : (ZLandroid/app/Instrumentation;)Landroid/app/Application;
    //   244: astore #8
    //   246: aload_3
    //   247: ifnull -> 745
    //   250: aload_1
    //   251: getfield activityInfo : Landroid/content/pm/ActivityInfo;
    //   254: aload #5
    //   256: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   259: invokevirtual loadLabel : (Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   262: astore #9
    //   264: new android/content/res/Configuration
    //   267: astore #7
    //   269: aload #7
    //   271: aload_0
    //   272: getfield mCompatConfiguration : Landroid/content/res/Configuration;
    //   275: invokespecial <init> : (Landroid/content/res/Configuration;)V
    //   278: aload_1
    //   279: getfield overrideConfig : Landroid/content/res/Configuration;
    //   282: astore #6
    //   284: aload #6
    //   286: ifnull -> 310
    //   289: aload #7
    //   291: aload_1
    //   292: getfield overrideConfig : Landroid/content/res/Configuration;
    //   295: invokevirtual updateFrom : (Landroid/content/res/Configuration;)I
    //   298: pop
    //   299: goto -> 310
    //   302: astore_1
    //   303: goto -> 799
    //   306: astore_1
    //   307: goto -> 867
    //   310: aload_1
    //   311: getfield mPendingRemoveWindow : Landroid/view/Window;
    //   314: astore #6
    //   316: aload #6
    //   318: ifnull -> 347
    //   321: aload_1
    //   322: getfield mPreserveWindow : Z
    //   325: ifeq -> 347
    //   328: aload_1
    //   329: getfield mPendingRemoveWindow : Landroid/view/Window;
    //   332: astore #6
    //   334: aload_1
    //   335: aconst_null
    //   336: putfield mPendingRemoveWindow : Landroid/view/Window;
    //   339: aload_1
    //   340: aconst_null
    //   341: putfield mPendingRemoveWindowManager : Landroid/view/WindowManager;
    //   344: goto -> 350
    //   347: aconst_null
    //   348: astore #6
    //   350: aload #5
    //   352: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   355: aload #8
    //   357: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   360: invokevirtual getLoaders : ()Ljava/util/List;
    //   363: iconst_0
    //   364: anewarray android/content/res/loader/ResourcesLoader
    //   367: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   372: checkcast [Landroid/content/res/loader/ResourcesLoader;
    //   375: invokevirtual addLoaders : ([Landroid/content/res/loader/ResourcesLoader;)V
    //   378: aload #5
    //   380: aload_3
    //   381: invokevirtual setOuterContext : (Landroid/content/Context;)V
    //   384: aload_0
    //   385: invokevirtual getInstrumentation : ()Landroid/app/Instrumentation;
    //   388: astore #10
    //   390: aload_1
    //   391: getfield token : Landroid/os/IBinder;
    //   394: astore #11
    //   396: aload_1
    //   397: getfield ident : I
    //   400: istore #12
    //   402: aload_1
    //   403: getfield intent : Landroid/content/Intent;
    //   406: astore #13
    //   408: aload_1
    //   409: getfield activityInfo : Landroid/content/pm/ActivityInfo;
    //   412: astore #14
    //   414: aload_1
    //   415: getfield parent : Landroid/app/Activity;
    //   418: astore #15
    //   420: aload_1
    //   421: getfield embeddedID : Ljava/lang/String;
    //   424: astore #16
    //   426: aload_1
    //   427: getfield lastNonConfigurationInstances : Landroid/app/Activity$NonConfigurationInstances;
    //   430: astore #17
    //   432: aload_1
    //   433: getfield referrer : Ljava/lang/String;
    //   436: astore #18
    //   438: aload_1
    //   439: getfield voiceInteractor : Lcom/android/internal/app/IVoiceInteractor;
    //   442: astore #19
    //   444: aload_1
    //   445: getfield configCallback : Landroid/view/ViewRootImpl$ActivityConfigCallback;
    //   448: astore #20
    //   450: aload_1
    //   451: getfield assistToken : Landroid/os/IBinder;
    //   454: astore #21
    //   456: aload_3
    //   457: aload #5
    //   459: aload_0
    //   460: aload #10
    //   462: aload #11
    //   464: iload #12
    //   466: aload #8
    //   468: aload #13
    //   470: aload #14
    //   472: aload #9
    //   474: aload #15
    //   476: aload #16
    //   478: aload #17
    //   480: aload #7
    //   482: aload #18
    //   484: aload #19
    //   486: aload #6
    //   488: aload #20
    //   490: aload #21
    //   492: invokevirtual attach : (Landroid/content/Context;Landroid/app/ActivityThread;Landroid/app/Instrumentation;Landroid/os/IBinder;ILandroid/app/Application;Landroid/content/Intent;Landroid/content/pm/ActivityInfo;Ljava/lang/CharSequence;Landroid/app/Activity;Ljava/lang/String;Landroid/app/Activity$NonConfigurationInstances;Landroid/content/res/Configuration;Ljava/lang/String;Lcom/android/internal/app/IVoiceInteractor;Landroid/view/Window;Landroid/view/ViewRootImpl$ActivityConfigCallback;Landroid/os/IBinder;)V
    //   495: aload_2
    //   496: ifnull -> 515
    //   499: aload_3
    //   500: aload_2
    //   501: putfield mIntent : Landroid/content/Intent;
    //   504: goto -> 515
    //   507: astore_1
    //   508: goto -> 799
    //   511: astore_1
    //   512: goto -> 791
    //   515: aload_3
    //   516: astore #6
    //   518: aload_1
    //   519: astore_2
    //   520: aload_2
    //   521: aconst_null
    //   522: putfield lastNonConfigurationInstances : Landroid/app/Activity$NonConfigurationInstances;
    //   525: aload_0
    //   526: invokespecial checkAndBlockForNetworkAccess : ()V
    //   529: aload #6
    //   531: iconst_0
    //   532: putfield mStartedActivity : Z
    //   535: aload_2
    //   536: getfield activityInfo : Landroid/content/pm/ActivityInfo;
    //   539: invokevirtual getThemeResource : ()I
    //   542: istore #12
    //   544: iload #12
    //   546: ifeq -> 556
    //   549: aload #6
    //   551: iload #12
    //   553: invokevirtual setTheme : (I)V
    //   556: aload #6
    //   558: iconst_0
    //   559: putfield mCalled : Z
    //   562: aload_1
    //   563: invokevirtual isPersistable : ()Z
    //   566: istore #22
    //   568: iload #22
    //   570: ifeq -> 593
    //   573: aload_0
    //   574: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   577: aload #6
    //   579: aload_2
    //   580: getfield state : Landroid/os/Bundle;
    //   583: aload_2
    //   584: getfield persistentState : Landroid/os/PersistableBundle;
    //   587: invokevirtual callActivityOnCreate : (Landroid/app/Activity;Landroid/os/Bundle;Landroid/os/PersistableBundle;)V
    //   590: goto -> 606
    //   593: aload_0
    //   594: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   597: aload #6
    //   599: aload_2
    //   600: getfield state : Landroid/os/Bundle;
    //   603: invokevirtual callActivityOnCreate : (Landroid/app/Activity;Landroid/os/Bundle;)V
    //   606: aload #6
    //   608: getfield mCalled : Z
    //   611: ifeq -> 649
    //   614: aload_2
    //   615: aload #6
    //   617: putfield activity : Landroid/app/Activity;
    //   620: aload_0
    //   621: getfield mLastReportedWindowingMode : Ljava/util/Map;
    //   624: aload #6
    //   626: invokevirtual getActivityToken : ()Landroid/os/IBinder;
    //   629: aload #7
    //   631: getfield windowConfiguration : Landroid/app/WindowConfiguration;
    //   634: invokevirtual getWindowingMode : ()I
    //   637: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   640: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   645: pop
    //   646: goto -> 745
    //   649: new android/util/SuperNotCalledException
    //   652: astore #6
    //   654: new java/lang/StringBuilder
    //   657: astore_1
    //   658: aload_1
    //   659: invokespecial <init> : ()V
    //   662: aload_1
    //   663: ldc_w 'Activity '
    //   666: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   669: pop
    //   670: aload_1
    //   671: aload_2
    //   672: getfield intent : Landroid/content/Intent;
    //   675: invokevirtual getComponent : ()Landroid/content/ComponentName;
    //   678: invokevirtual toShortString : ()Ljava/lang/String;
    //   681: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   684: pop
    //   685: aload_1
    //   686: ldc_w ' did not call through to super.onCreate()'
    //   689: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   692: pop
    //   693: aload #6
    //   695: aload_1
    //   696: invokevirtual toString : ()Ljava/lang/String;
    //   699: invokespecial <init> : (Ljava/lang/String;)V
    //   702: aload #6
    //   704: athrow
    //   705: astore_1
    //   706: goto -> 799
    //   709: astore_1
    //   710: goto -> 791
    //   713: astore_1
    //   714: goto -> 799
    //   717: astore_1
    //   718: goto -> 867
    //   721: astore_1
    //   722: goto -> 738
    //   725: astore_1
    //   726: goto -> 742
    //   729: astore_1
    //   730: goto -> 738
    //   733: astore_1
    //   734: goto -> 742
    //   737: astore_1
    //   738: goto -> 799
    //   741: astore_1
    //   742: goto -> 867
    //   745: aload_0
    //   746: astore_2
    //   747: aload_1
    //   748: iconst_1
    //   749: invokevirtual setState : (I)V
    //   752: aload_2
    //   753: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   756: astore #6
    //   758: aload #6
    //   760: monitorenter
    //   761: aload_2
    //   762: getfield mActivities : Landroid/util/ArrayMap;
    //   765: aload_1
    //   766: getfield token : Landroid/os/IBinder;
    //   769: aload_1
    //   770: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   773: pop
    //   774: aload #6
    //   776: monitorexit
    //   777: goto -> 811
    //   780: astore_1
    //   781: aload #6
    //   783: monitorexit
    //   784: aload_1
    //   785: athrow
    //   786: astore_1
    //   787: goto -> 799
    //   790: astore_1
    //   791: goto -> 867
    //   794: astore_1
    //   795: goto -> 867
    //   798: astore_1
    //   799: aload_0
    //   800: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   803: aload_3
    //   804: aload_1
    //   805: invokevirtual onException : (Ljava/lang/Object;Ljava/lang/Throwable;)Z
    //   808: ifeq -> 813
    //   811: aload_3
    //   812: areturn
    //   813: new java/lang/StringBuilder
    //   816: dup
    //   817: invokespecial <init> : ()V
    //   820: astore_2
    //   821: aload_2
    //   822: ldc_w 'Unable to start activity '
    //   825: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   828: pop
    //   829: aload_2
    //   830: aload #4
    //   832: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   835: pop
    //   836: aload_2
    //   837: ldc_w ': '
    //   840: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   843: pop
    //   844: aload_2
    //   845: aload_1
    //   846: invokevirtual toString : ()Ljava/lang/String;
    //   849: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   852: pop
    //   853: new java/lang/RuntimeException
    //   856: dup
    //   857: aload_2
    //   858: invokevirtual toString : ()Ljava/lang/String;
    //   861: aload_1
    //   862: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   865: athrow
    //   866: astore_1
    //   867: aload_1
    //   868: athrow
    //   869: new java/lang/StringBuilder
    //   872: dup
    //   873: invokespecial <init> : ()V
    //   876: astore_1
    //   877: aload_1
    //   878: ldc_w 'Unable to instantiate activity '
    //   881: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   884: pop
    //   885: aload_1
    //   886: aload #4
    //   888: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   891: pop
    //   892: aload_1
    //   893: ldc_w ': '
    //   896: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   899: pop
    //   900: aload_1
    //   901: aload #6
    //   903: invokevirtual toString : ()Ljava/lang/String;
    //   906: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   909: pop
    //   910: new java/lang/RuntimeException
    //   913: dup
    //   914: aload_1
    //   915: invokevirtual toString : ()Ljava/lang/String;
    //   918: aload #6
    //   920: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   923: athrow
    // Exception table:
    //   from	to	target	type
    //   122	129	213	java/lang/Exception
    //   132	152	213	java/lang/Exception
    //   155	163	213	java/lang/Exception
    //   166	175	213	java/lang/Exception
    //   178	185	213	java/lang/Exception
    //   188	195	213	java/lang/Exception
    //   198	207	213	java/lang/Exception
    //   228	234	866	android/util/SuperNotCalledException
    //   228	234	798	java/lang/Exception
    //   234	246	794	android/util/SuperNotCalledException
    //   234	246	798	java/lang/Exception
    //   250	284	794	android/util/SuperNotCalledException
    //   250	284	798	java/lang/Exception
    //   289	299	306	android/util/SuperNotCalledException
    //   289	299	302	java/lang/Exception
    //   310	316	794	android/util/SuperNotCalledException
    //   310	316	798	java/lang/Exception
    //   321	344	306	android/util/SuperNotCalledException
    //   321	344	302	java/lang/Exception
    //   350	414	794	android/util/SuperNotCalledException
    //   350	414	798	java/lang/Exception
    //   414	420	741	android/util/SuperNotCalledException
    //   414	420	737	java/lang/Exception
    //   420	426	733	android/util/SuperNotCalledException
    //   420	426	729	java/lang/Exception
    //   426	456	725	android/util/SuperNotCalledException
    //   426	456	721	java/lang/Exception
    //   456	495	717	android/util/SuperNotCalledException
    //   456	495	713	java/lang/Exception
    //   499	504	511	android/util/SuperNotCalledException
    //   499	504	507	java/lang/Exception
    //   520	544	709	android/util/SuperNotCalledException
    //   520	544	705	java/lang/Exception
    //   549	556	709	android/util/SuperNotCalledException
    //   549	556	705	java/lang/Exception
    //   556	568	709	android/util/SuperNotCalledException
    //   556	568	705	java/lang/Exception
    //   573	590	790	android/util/SuperNotCalledException
    //   573	590	786	java/lang/Exception
    //   593	606	790	android/util/SuperNotCalledException
    //   593	606	786	java/lang/Exception
    //   606	646	790	android/util/SuperNotCalledException
    //   606	646	786	java/lang/Exception
    //   649	705	790	android/util/SuperNotCalledException
    //   649	705	786	java/lang/Exception
    //   747	761	790	android/util/SuperNotCalledException
    //   747	761	786	java/lang/Exception
    //   761	777	780	finally
    //   781	784	780	finally
    //   784	786	790	android/util/SuperNotCalledException
    //   784	786	786	java/lang/Exception
  }
  
  private Bundle performPauseActivity(ActivityClientRecord paramActivityClientRecord, boolean paramBoolean, String paramString, PendingTransactionActions paramPendingTransactionActions) {
    ArrayMap<Activity, ArrayList<OnActivityPausedListener>> arrayMap;
    Bundle bundle;
    boolean bool = paramActivityClientRecord.paused;
    ArrayMap arrayMap1 = null;
    if (bool) {
      if (paramActivityClientRecord.activity.mFinished)
        return null; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Performing pause of activity that is not resumed: ");
      stringBuilder.append(paramActivityClientRecord.intent.getComponent().toShortString());
      RuntimeException runtimeException = new RuntimeException(stringBuilder.toString());
      Slog.e("ActivityThread", runtimeException.getMessage(), runtimeException);
    } 
    boolean bool1 = true;
    if (paramBoolean)
      paramActivityClientRecord.activity.mFinished = true; 
    paramBoolean = paramActivityClientRecord.activity.mFinished;
    int i = 0;
    if (paramBoolean || !paramActivityClientRecord.isPreHoneycomb())
      bool1 = false; 
    if (bool1)
      callActivityOnSaveInstanceState(paramActivityClientRecord); 
    performPauseActivityIfNeeded(paramActivityClientRecord, paramString);
    synchronized (this.mOnPauseListeners) {
      ArrayList<OnActivityPausedListener> arrayList = (ArrayList)this.mOnPauseListeners.remove(paramActivityClientRecord.activity);
      if (arrayList != null)
        i = arrayList.size(); 
      for (byte b = 0; b < i; b++)
        ((OnActivityPausedListener)arrayList.get(b)).onPaused(paramActivityClientRecord.activity); 
      if (paramPendingTransactionActions != null) {
        bundle = paramPendingTransactionActions.getOldState();
      } else {
        arrayMap = null;
      } 
      if (arrayMap != null && paramActivityClientRecord.isPreHoneycomb())
        paramActivityClientRecord.state = (Bundle)arrayMap; 
      arrayMap = arrayMap1;
      if (bool1)
        bundle = paramActivityClientRecord.state; 
      return bundle;
    } 
  }
  
  private void performPauseActivityIfNeeded(ActivityClientRecord paramActivityClientRecord, String paramString) {
    if (paramActivityClientRecord.paused)
      return; 
    reportTopResumedActivityChanged(paramActivityClientRecord, false, "pausing");
    try {
      paramActivityClientRecord.activity.mCalled = false;
      this.mInstrumentation.callActivityOnPause(paramActivityClientRecord.activity);
      if (!paramActivityClientRecord.activity.mCalled) {
        SuperNotCalledException superNotCalledException1 = new SuperNotCalledException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Activity ");
        stringBuilder.append(safeToComponentShortString(paramActivityClientRecord.intent));
        stringBuilder.append(" did not call through to super.onPause()");
        this(stringBuilder.toString());
        throw superNotCalledException1;
      } 
      paramActivityClientRecord.setState(4);
      return;
    } catch (SuperNotCalledException superNotCalledException) {
      throw superNotCalledException;
    } catch (Exception exception) {
      if (!this.mInstrumentation.onException(((ActivityClientRecord)superNotCalledException).activity, exception)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to pause activity ");
        stringBuilder.append(safeToComponentShortString(((ActivityClientRecord)superNotCalledException).intent));
        stringBuilder.append(": ");
        stringBuilder.append(exception.toString());
        throw new RuntimeException(stringBuilder.toString(), exception);
      } 
      superNotCalledException.setState(4);
      return;
    } 
  }
  
  private void performStopActivityInner(ActivityClientRecord paramActivityClientRecord, PendingTransactionActions.StopInfo paramStopInfo, boolean paramBoolean1, boolean paramBoolean2, String paramString) {
    if (paramActivityClientRecord != null) {
      StringBuilder stringBuilder;
      if (paramActivityClientRecord.stopped) {
        if (paramActivityClientRecord.activity.mFinished)
          return; 
        if (!paramBoolean2) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Performing stop of activity that is already stopped: ");
          stringBuilder1.append(paramActivityClientRecord.intent.getComponent().toShortString());
          RuntimeException runtimeException = new RuntimeException(stringBuilder1.toString());
          Slog.e("ActivityThread", runtimeException.getMessage(), runtimeException);
          Slog.e("ActivityThread", paramActivityClientRecord.getStateString());
        } 
      } 
      performPauseActivityIfNeeded(paramActivityClientRecord, paramString);
      if (paramStopInfo != null)
        try {
          paramStopInfo.setDescription(paramActivityClientRecord.activity.onCreateDescription());
        } catch (Exception exception) {
          if (!this.mInstrumentation.onException(paramActivityClientRecord.activity, exception)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to save state of activity ");
            stringBuilder.append(paramActivityClientRecord.intent.getComponent().toShortString());
            stringBuilder.append(": ");
            stringBuilder.append(exception.toString());
            throw new RuntimeException(stringBuilder.toString(), exception);
          } 
        }  
      callActivityOnStop(paramActivityClientRecord, paramBoolean1, (String)stringBuilder);
    } 
  }
  
  static void printRow(PrintWriter paramPrintWriter, String paramString, Object... paramVarArgs) {
    paramPrintWriter.println(String.format(paramString, paramVarArgs));
  }
  
  private void purgePendingResources() {
    Trace.traceBegin(64L, "purgePendingResources");
    nPurgePendingResources();
    Trace.traceEnd(64L);
  }
  
  private void relaunchAllActivities(boolean paramBoolean) {
    for (Map.Entry entry : this.mActivities.entrySet()) {
      ActivityClientRecord activityClientRecord = (ActivityClientRecord)entry.getValue();
      if (!activityClientRecord.activity.mFinished) {
        if (paramBoolean && activityClientRecord.window != null)
          activityClientRecord.mPreserveWindow = true; 
        scheduleRelaunchActivity((IBinder)entry.getKey());
      } 
    } 
  }
  
  private CancellationSignal removeSafeCancellationTransport(SafeCancellationTransport paramSafeCancellationTransport) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mRemoteCancellations : Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   12: checkcast android/os/CancellationSignal
    //   15: astore_1
    //   16: aload_0
    //   17: getfield mRemoteCancellations : Ljava/util/Map;
    //   20: invokeinterface isEmpty : ()Z
    //   25: ifeq -> 33
    //   28: aload_0
    //   29: aconst_null
    //   30: putfield mRemoteCancellations : Ljava/util/Map;
    //   33: aload_0
    //   34: monitorexit
    //   35: aload_1
    //   36: areturn
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   2	33	37	finally
    //   33	35	37	finally
    //   38	40	37	finally
  }
  
  private void reportSizeConfigurations(ActivityClientRecord paramActivityClientRecord) {
    if (this.mActivitiesToBeDestroyed.containsKey(paramActivityClientRecord.token))
      return; 
    Configuration[] arrayOfConfiguration = paramActivityClientRecord.activity.getResources().getSizeConfigurations();
    if (arrayOfConfiguration == null)
      return; 
    SparseIntArray sparseIntArray1 = new SparseIntArray();
    SparseIntArray sparseIntArray2 = new SparseIntArray();
    SparseIntArray sparseIntArray3 = new SparseIntArray();
    for (int i = arrayOfConfiguration.length - 1; i >= 0; i--) {
      Configuration configuration = arrayOfConfiguration[i];
      if (configuration.screenHeightDp != 0)
        sparseIntArray2.put(configuration.screenHeightDp, 0); 
      if (configuration.screenWidthDp != 0)
        sparseIntArray1.put(configuration.screenWidthDp, 0); 
      if (configuration.smallestScreenWidthDp != 0)
        sparseIntArray3.put(configuration.smallestScreenWidthDp, 0); 
    } 
    try {
      ActivityTaskManager.getService().reportSizeConfigurations(paramActivityClientRecord.token, sparseIntArray1.copyKeys(), sparseIntArray2.copyKeys(), sparseIntArray3.copyKeys());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private void reportTopResumedActivityChanged(ActivityClientRecord paramActivityClientRecord, boolean paramBoolean, String paramString) {
    if (paramActivityClientRecord.lastReportedTopResumedState != paramBoolean) {
      paramActivityClientRecord.lastReportedTopResumedState = paramBoolean;
      paramActivityClientRecord.activity.performTopResumedActivityChanged(paramBoolean, paramString);
    } 
  }
  
  private static String safeToComponentShortString(Intent paramIntent) {
    String str;
    ComponentName componentName = paramIntent.getComponent();
    if (componentName == null) {
      str = "[Unknown]";
    } else {
      str = str.toShortString();
    } 
    return str;
  }
  
  private void schedulePauseWithUserLeaveHintAndReturnToCurrentState(ActivityClientRecord paramActivityClientRecord) {
    int i = paramActivityClientRecord.getLifecycleState();
    if (i != 3 && i != 4)
      return; 
    if (i != 3) {
      if (i == 4) {
        scheduleResume(paramActivityClientRecord);
        schedulePauseWithUserLeavingHint(paramActivityClientRecord);
      } 
    } else {
      schedulePauseWithUserLeavingHint(paramActivityClientRecord);
      scheduleResume(paramActivityClientRecord);
    } 
  }
  
  private void schedulePauseWithUserLeavingHint(ActivityClientRecord paramActivityClientRecord) {
    ClientTransaction clientTransaction = ClientTransaction.obtain(this.mAppThread, paramActivityClientRecord.token);
    clientTransaction.setLifecycleStateRequest((ActivityLifecycleItem)PauseActivityItem.obtain(paramActivityClientRecord.activity.isFinishing(), true, paramActivityClientRecord.activity.mConfigChangeFlags, false));
    executeTransaction(clientTransaction);
  }
  
  private void scheduleResume(ActivityClientRecord paramActivityClientRecord) {
    ClientTransaction clientTransaction = ClientTransaction.obtain(this.mAppThread, paramActivityClientRecord.token);
    clientTransaction.setLifecycleStateRequest((ActivityLifecycleItem)ResumeActivityItem.obtain(false));
    executeTransaction(clientTransaction);
  }
  
  private void sendMessage(int paramInt1, Object paramObject, int paramInt2) {
    sendMessage(paramInt1, paramObject, paramInt2, 0, false);
  }
  
  private void sendMessage(int paramInt1, Object paramObject, int paramInt2, int paramInt3) {
    sendMessage(paramInt1, paramObject, paramInt2, paramInt3, false);
  }
  
  private void sendMessage(int paramInt1, Object paramObject, int paramInt2, int paramInt3, int paramInt4) {
    Message message = Message.obtain();
    message.what = paramInt1;
    SomeArgs someArgs = SomeArgs.obtain();
    someArgs.arg1 = paramObject;
    someArgs.argi1 = paramInt2;
    someArgs.argi2 = paramInt3;
    someArgs.argi3 = paramInt4;
    message.obj = someArgs;
    this.mH.sendMessage(message);
  }
  
  private void sendMessage(int paramInt1, Object paramObject, int paramInt2, int paramInt3, boolean paramBoolean) {
    Message message = Message.obtain();
    message.what = paramInt1;
    message.obj = paramObject;
    message.arg1 = paramInt2;
    message.arg2 = paramInt3;
    if (paramBoolean)
      message.setAsynchronous(true); 
    this.mH.sendMessage(message);
  }
  
  private void setupGraphicsSupport(Context paramContext) {
    Trace.traceBegin(64L, "setupGraphicsSupport");
    if (!"android".equals(paramContext.getPackageName())) {
      File file = paramContext.getCacheDir();
      if (file != null) {
        System.setProperty("java.io.tmpdir", file.getAbsolutePath());
      } else {
        Log.v("ActivityThread", "Unable to initialize \"java.io.tmpdir\" property due to missing cache directory");
      } 
      file = paramContext.createDeviceProtectedStorageContext().getCodeCacheDir();
      if (file != null) {
        try {
          int i = Process.myUid();
          if (getPackageManager().getPackagesForUid(i) != null) {
            HardwareRenderer.setupDiskCache(file);
            RenderScriptCacheDir.setupDiskCache(file);
          } 
        } catch (RemoteException remoteException) {
          Trace.traceEnd(64L);
          throw remoteException.rethrowFromSystemServer();
        } 
      } else {
        Log.w("ActivityThread", "Unable to use shader/script cache: missing code-cache directory");
      } 
    } 
    GraphicsEnvironment.getInstance().setup((Context)remoteException, this.mCoreSettings);
    Trace.traceEnd(64L);
  }
  
  public static ActivityThread systemMain() {
    if (!ActivityManager.isHighEndGfx()) {
      ThreadedRenderer.disable(true);
    } else {
      ThreadedRenderer.enableForegroundTrimming();
    } 
    ActivityThread activityThread = new ActivityThread();
    activityThread.attach(true, 0L);
    return activityThread;
  }
  
  private boolean updateDebugViewAttributeState() {
    String str;
    boolean bool = View.sDebugViewAttributes;
    View.sDebugViewAttributesApplicationPackage = this.mCoreSettings.getString("debug_view_attributes_application_package", "");
    AppBindData appBindData = this.mBoundApplication;
    if (appBindData != null && appBindData.appInfo != null) {
      str = this.mBoundApplication.appInfo.packageName;
    } else {
      str = "<unknown-app>";
    } 
    Bundle bundle = this.mCoreSettings;
    boolean bool1 = false;
    if (bundle.getInt("debug_view_attributes", 0) != 0 || View.sDebugViewAttributesApplicationPackage.equals(str)) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    View.sDebugViewAttributes = bool2;
    boolean bool2 = bool1;
    if (bool != View.sDebugViewAttributes)
      bool2 = true; 
    return bool2;
  }
  
  private void updateDefaultDensity() {
    int i = this.mCurDefaultDisplayDpi;
    if (!this.mDensityCompatMode && i != 0 && i != DisplayMetrics.DENSITY_DEVICE) {
      DisplayMetrics.DENSITY_DEVICE = i;
      Bitmap.setDefaultDensity(i);
    } 
  }
  
  public static void updateHttpProxy(Context paramContext) {
    Proxy.setHttpProxySystemProperty(ConnectivityManager.from(paramContext).getDefaultProxy());
  }
  
  private void updateLocaleListFromAppContext(Context paramContext, LocaleList paramLocaleList) {
    Locale locale = paramContext.getResources().getConfiguration().getLocales().get(0);
    int i = paramLocaleList.size();
    for (byte b = 0; b < i; b++) {
      if (locale.equals(paramLocaleList.get(b))) {
        LocaleList.setDefault(paramLocaleList, b);
        return;
      } 
    } 
    LocaleList.setDefault(new LocaleList(locale, paramLocaleList));
  }
  
  private void updateVisibility(ActivityClientRecord paramActivityClientRecord, boolean paramBoolean) {
    View view = paramActivityClientRecord.activity.mDecor;
    if (view != null)
      if (paramBoolean) {
        if (!paramActivityClientRecord.activity.mVisibleFromServer) {
          paramActivityClientRecord.activity.mVisibleFromServer = true;
          this.mNumVisibleActivities++;
          if (paramActivityClientRecord.activity.mVisibleFromClient)
            paramActivityClientRecord.activity.makeVisible(); 
        } 
        if (paramActivityClientRecord.newConfig != null) {
          performConfigurationChangedForActivity(paramActivityClientRecord, paramActivityClientRecord.newConfig);
          paramActivityClientRecord.newConfig = null;
        } 
      } else if (paramActivityClientRecord.activity.mVisibleFromServer) {
        paramActivityClientRecord.activity.mVisibleFromServer = false;
        this.mNumVisibleActivities--;
        view.setVisibility(4);
      }  
  }
  
  private void updateVmProcessState(int paramInt) {
    if (paramInt <= 6) {
      paramInt = 0;
    } else {
      paramInt = 1;
    } 
    VMRuntime.getRuntime().updateProcessState(paramInt);
  }
  
  public final IContentProvider acquireExistingProvider(Context paramContext, String paramString, int paramInt, boolean paramBoolean) {
    synchronized (this.mProviderMap) {
      StringBuilder stringBuilder;
      ProviderKey providerKey = new ProviderKey();
      this(paramString, paramInt);
      ProviderClientRecord providerClientRecord = (ProviderClientRecord)this.mProviderMap.get(providerKey);
      if (providerClientRecord == null)
        return null; 
      IContentProvider iContentProvider = providerClientRecord.mProvider;
      IBinder iBinder = iContentProvider.asBinder();
      if (!iBinder.isBinderAlive()) {
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Acquiring provider ");
        stringBuilder.append(paramString);
        stringBuilder.append(" for user ");
        stringBuilder.append(paramInt);
        stringBuilder.append(": existing object's process dead");
        Log.i("ActivityThread", stringBuilder.toString());
        handleUnstableProviderDiedLocked(iBinder, true);
        return null;
      } 
      ProviderRefCount providerRefCount = (ProviderRefCount)this.mProviderRefCountMap.get(iBinder);
      if (providerRefCount != null)
        incProviderRefLocked(providerRefCount, paramBoolean); 
      return (IContentProvider)stringBuilder;
    } 
  }
  
  public final IContentProvider acquireProvider(Context paramContext, String paramString, int paramInt, boolean paramBoolean) {
    IContentProvider iContentProvider = acquireExistingProvider(paramContext, paramString, paramInt, paramBoolean);
    if (iContentProvider != null)
      return iContentProvider; 
    try {
      synchronized (getGetProviderLock(paramString, paramInt)) {
        StringBuilder stringBuilder;
        ContentProviderHolder contentProviderHolder = ActivityManager.getService().getContentProvider(getApplicationThread(), paramContext.getOpPackageName(), paramString, paramInt, paramBoolean);
        if (contentProviderHolder == null) {
          if (UserManager.get(paramContext).isUserUnlocked(paramInt)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to find provider info for ");
            stringBuilder.append(paramString);
            Slog.e("ActivityThread", stringBuilder.toString());
          } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to find provider info for ");
            stringBuilder.append(paramString);
            stringBuilder.append(" (user not unlocked)");
            Slog.w("ActivityThread", stringBuilder.toString());
          } 
          return null;
        } 
        return (installProvider((Context)stringBuilder, contentProviderHolder, contentProviderHolder.info, true, contentProviderHolder.noReleaseNeeded, paramBoolean)).provider;
      } 
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  final void appNotRespondingViaProvider(IBinder paramIBinder) {
    synchronized (this.mProviderMap) {
      ProviderRefCount providerRefCount = (ProviderRefCount)this.mProviderRefCountMap.get(paramIBinder);
      if (providerRefCount != null)
        try {
          ActivityManager.getService().appNotRespondingViaProvider(providerRefCount.holder.connection);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return;
    } 
  }
  
  final Configuration applyCompatConfiguration(int paramInt) {
    Configuration configuration = this.mConfiguration;
    if (this.mCompatConfiguration == null)
      this.mCompatConfiguration = new Configuration(); 
    this.mCompatConfiguration.setTo(this.mConfiguration);
    if (this.mResourcesManager.applyCompatConfigurationLocked(paramInt, this.mCompatConfiguration))
      configuration = this.mCompatConfiguration; 
    return configuration;
  }
  
  Configuration applyConfigCompatMainThread(int paramInt, Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo) {
    if (paramConfiguration == null)
      return null; 
    Configuration configuration = paramConfiguration;
    if (!paramCompatibilityInfo.supportsScreen()) {
      this.mMainThreadConfig.setTo(paramConfiguration);
      configuration = this.mMainThreadConfig;
      paramCompatibilityInfo.applyToConfiguration(paramInt, configuration);
    } 
    return configuration;
  }
  
  public final void applyConfigurationToResources(Configuration paramConfiguration) {
    synchronized (this.mResourcesManager) {
      this.mResourcesManager.applyConfigurationToResourcesLocked(paramConfiguration, null);
      return;
    } 
  }
  
  ArrayList<ComponentCallbacks2> collectComponentCallbacks(boolean paramBoolean, Configuration paramConfiguration) {
    ArrayList<ComponentCallbacks2> arrayList = new ArrayList();
    synchronized (this.mResourcesManager) {
      int i = this.mAllApplications.size();
      byte b;
      for (b = 0; b < i; b++)
        arrayList.add(this.mAllApplications.get(b)); 
      i = this.mActivities.size();
      for (b = 0; b < i; b++) {
        ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.valueAt(b);
        Activity activity = activityClientRecord.activity;
        if (activity != null) {
          Configuration configuration = applyConfigCompatMainThread(this.mCurDefaultDisplayDpi, paramConfiguration, activityClientRecord.packageInfo.getCompatibilityInfo());
          if (!activityClientRecord.activity.mFinished && (paramBoolean || !activityClientRecord.paused)) {
            arrayList.add(activity);
          } else if (configuration != null) {
            activityClientRecord.newConfig = configuration;
          } 
        } 
      } 
      i = this.mServices.size();
      for (b = 0; b < i; b++) {
        ComponentCallbacks2 componentCallbacks2 = (ComponentCallbacks2)this.mServices.valueAt(b);
        if (componentCallbacks2 instanceof android.inputmethodservice.InputMethodService)
          this.mHasImeComponent = true; 
        arrayList.add(componentCallbacks2);
      } 
      synchronized (this.mProviderMap) {
        i = this.mLocalProviders.size();
        for (b = 0; b < i; b++)
          arrayList.add(((ProviderClientRecord)this.mLocalProviders.valueAt(b)).mLocalProvider); 
        return arrayList;
      } 
    } 
  }
  
  final void completeRemoveProvider(ProviderRefCount paramProviderRefCount) {
    synchronized (this.mProviderMap) {
      if (!paramProviderRefCount.removePending)
        return; 
      paramProviderRefCount.removePending = false;
      IBinder iBinder = paramProviderRefCount.holder.provider.asBinder();
      if ((ProviderRefCount)this.mProviderRefCountMap.get(iBinder) == paramProviderRefCount)
        this.mProviderRefCountMap.remove(iBinder); 
      for (int i = this.mProviderMap.size() - 1; i >= 0; i--) {
        if (((ProviderClientRecord)this.mProviderMap.valueAt(i)).mProvider.asBinder() == iBinder)
          this.mProviderMap.removeAt(i); 
      } 
      try {
        ActivityManager.getService().removeContentProvider(paramProviderRefCount.holder.connection, false);
      } catch (RemoteException remoteException) {}
      return;
    } 
  }
  
  public void countLaunchingActivities(int paramInt) {
    this.mNumLaunchingActivities.getAndAdd(paramInt);
  }
  
  public ContextImpl createSystemUiContext(int paramInt) {
    return ContextImpl.createSystemUiContext(getSystemUiContext(), paramInt);
  }
  
  void doGcIfNeeded() {
    doGcIfNeeded("bg");
  }
  
  void doGcIfNeeded(String paramString) {
    this.mGcIdlerScheduled = false;
    long l = SystemClock.uptimeMillis();
    if (BinderInternal.getLastGcTime() + 5000L < l)
      BinderInternal.forceGc(paramString); 
  }
  
  final void finishInstrumentation(int paramInt, Bundle paramBundle) {
    IActivityManager iActivityManager = ActivityManager.getService();
    if (this.mProfiler.profileFile != null && this.mProfiler.handlingProfiling && this.mProfiler.profileFd == null)
      Debug.stopMethodTracing(); 
    try {
      iActivityManager.finishInstrumentation(this.mAppThread, paramInt, paramBundle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Map<IBinder, ClientTransactionItem> getActivitiesToBeDestroyed() {
    return this.mActivitiesToBeDestroyed;
  }
  
  public final Activity getActivity(IBinder paramIBinder) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord != null) {
      Activity activity = activityClientRecord.activity;
    } else {
      activityClientRecord = null;
    } 
    return (Activity)activityClientRecord;
  }
  
  public ActivityClientRecord getActivityClient(IBinder paramIBinder) {
    return (ActivityClientRecord)this.mActivities.get(paramIBinder);
  }
  
  public Application getApplication() {
    return this.mInitialApplication;
  }
  
  public ApplicationThread getApplicationThread() {
    return this.mAppThread;
  }
  
  public Configuration getConfiguration() {
    return this.mConfiguration;
  }
  
  public Bundle getCoreSettings() {
    return this.mCoreSettings;
  }
  
  public Executor getExecutor() {
    return this.mExecutor;
  }
  
  float getFloatCoreSetting(String paramString, float paramFloat) {
    synchronized (this.mResourcesManager) {
      if (this.mCoreSettings != null) {
        paramFloat = this.mCoreSettings.getFloat(paramString, paramFloat);
        return paramFloat;
      } 
      return paramFloat;
    } 
  }
  
  final Handler getHandler() {
    return this.mH;
  }
  
  public Instrumentation getInstrumentation() {
    return this.mInstrumentation;
  }
  
  public int getIntCoreSetting(String paramString, int paramInt) {
    synchronized (this.mResourcesManager) {
      if (this.mCoreSettings != null) {
        paramInt = this.mCoreSettings.getInt(paramString, paramInt);
        return paramInt;
      } 
      return paramInt;
    } 
  }
  
  public Looper getLooper() {
    return this.mLooper;
  }
  
  public final LoadedApk getPackageInfo(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt) {
    String str1;
    String str2;
    boolean bool1;
    boolean bool2;
    boolean bool3;
    if ((paramInt & 0x1) != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (bool1 && paramApplicationInfo.uid != 0 && paramApplicationInfo.uid != 1000 && (this.mBoundApplication == null || !UserHandle.isSameApp(paramApplicationInfo.uid, this.mBoundApplication.appInfo.uid))) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (bool1 && (0x40000000 & paramInt) != 0) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    if ((paramInt & 0x3) == 1 && bool2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requesting code from ");
      stringBuilder.append(paramApplicationInfo.packageName);
      stringBuilder.append(" (with uid ");
      stringBuilder.append(paramApplicationInfo.uid);
      stringBuilder.append(")");
      str2 = stringBuilder.toString();
      str1 = str2;
      if (this.mBoundApplication != null) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str2);
        stringBuilder1.append(" to be run in process ");
        stringBuilder1.append(this.mBoundApplication.processName);
        stringBuilder1.append(" (with uid ");
        stringBuilder1.append(this.mBoundApplication.appInfo.uid);
        stringBuilder1.append(")");
        str1 = stringBuilder1.toString();
      } 
      throw new SecurityException(str1);
    } 
    return getPackageInfo((ApplicationInfo)str1, (CompatibilityInfo)str2, (ClassLoader)null, bool2, bool1, bool3);
  }
  
  public final LoadedApk getPackageInfo(String paramString, CompatibilityInfo paramCompatibilityInfo, int paramInt) {
    return getPackageInfo(paramString, paramCompatibilityInfo, paramInt, UserHandle.myUserId());
  }
  
  public final LoadedApk getPackageInfo(String paramString, CompatibilityInfo paramCompatibilityInfo, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: invokestatic myUserId : ()I
    //   3: iload #4
    //   5: if_icmpeq -> 14
    //   8: iconst_1
    //   9: istore #5
    //   11: goto -> 17
    //   14: iconst_0
    //   15: istore #5
    //   17: iload #4
    //   19: ifge -> 30
    //   22: invokestatic myUserId : ()I
    //   25: istore #4
    //   27: goto -> 30
    //   30: aload_1
    //   31: ldc_w 268436480
    //   34: iload #4
    //   36: invokestatic getApplicationInfoAsUserCached : (Ljava/lang/String;II)Landroid/content/pm/ApplicationInfo;
    //   39: astore #6
    //   41: aload_0
    //   42: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   45: astore #7
    //   47: aload #7
    //   49: monitorenter
    //   50: iload #5
    //   52: ifeq -> 61
    //   55: aconst_null
    //   56: astore #8
    //   58: goto -> 96
    //   61: iload_3
    //   62: iconst_1
    //   63: iand
    //   64: ifeq -> 83
    //   67: aload_0
    //   68: getfield mPackages : Landroid/util/ArrayMap;
    //   71: aload_1
    //   72: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   75: checkcast java/lang/ref/WeakReference
    //   78: astore #8
    //   80: goto -> 96
    //   83: aload_0
    //   84: getfield mResourcePackages : Landroid/util/ArrayMap;
    //   87: aload_1
    //   88: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   91: checkcast java/lang/ref/WeakReference
    //   94: astore #8
    //   96: aload #8
    //   98: ifnull -> 114
    //   101: aload #8
    //   103: invokevirtual get : ()Ljava/lang/Object;
    //   106: checkcast android/app/LoadedApk
    //   109: astore #8
    //   111: goto -> 117
    //   114: aconst_null
    //   115: astore #8
    //   117: aload #6
    //   119: ifnull -> 271
    //   122: aload #8
    //   124: ifnull -> 271
    //   127: aload #8
    //   129: aload #6
    //   131: invokestatic isLoadedApkResourceDirsUpToDate : (Landroid/app/LoadedApk;Landroid/content/pm/ApplicationInfo;)Z
    //   134: ifne -> 160
    //   137: new java/util/ArrayList
    //   140: astore_2
    //   141: aload_2
    //   142: invokespecial <init> : ()V
    //   145: aload_0
    //   146: aload #6
    //   148: aload_2
    //   149: invokestatic makePaths : (Landroid/app/ActivityThread;Landroid/content/pm/ApplicationInfo;Ljava/util/List;)V
    //   152: aload #8
    //   154: aload #6
    //   156: aload_2
    //   157: invokevirtual updateApplicationInfo : (Landroid/content/pm/ApplicationInfo;Ljava/util/List;)V
    //   160: aload #8
    //   162: invokevirtual isSecurityViolation : ()Z
    //   165: ifeq -> 265
    //   168: iload_3
    //   169: iconst_2
    //   170: iand
    //   171: ifeq -> 177
    //   174: goto -> 265
    //   177: new java/lang/SecurityException
    //   180: astore_2
    //   181: new java/lang/StringBuilder
    //   184: astore #8
    //   186: aload #8
    //   188: invokespecial <init> : ()V
    //   191: aload #8
    //   193: ldc_w 'Requesting code from '
    //   196: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: pop
    //   200: aload #8
    //   202: aload_1
    //   203: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: pop
    //   207: aload #8
    //   209: ldc_w ' to be run in process '
    //   212: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: pop
    //   216: aload #8
    //   218: aload_0
    //   219: getfield mBoundApplication : Landroid/app/ActivityThread$AppBindData;
    //   222: getfield processName : Ljava/lang/String;
    //   225: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: pop
    //   229: aload #8
    //   231: ldc_w '/'
    //   234: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: pop
    //   238: aload #8
    //   240: aload_0
    //   241: getfield mBoundApplication : Landroid/app/ActivityThread$AppBindData;
    //   244: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   247: getfield uid : I
    //   250: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   253: pop
    //   254: aload_2
    //   255: aload #8
    //   257: invokevirtual toString : ()Ljava/lang/String;
    //   260: invokespecial <init> : (Ljava/lang/String;)V
    //   263: aload_2
    //   264: athrow
    //   265: aload #7
    //   267: monitorexit
    //   268: aload #8
    //   270: areturn
    //   271: aload #7
    //   273: monitorexit
    //   274: aload #6
    //   276: ifnull -> 288
    //   279: aload_0
    //   280: aload #6
    //   282: aload_2
    //   283: iload_3
    //   284: invokevirtual getPackageInfo : (Landroid/content/pm/ApplicationInfo;Landroid/content/res/CompatibilityInfo;I)Landroid/app/LoadedApk;
    //   287: areturn
    //   288: aconst_null
    //   289: areturn
    //   290: astore_1
    //   291: aload #7
    //   293: monitorexit
    //   294: aload_1
    //   295: athrow
    // Exception table:
    //   from	to	target	type
    //   67	80	290	finally
    //   83	96	290	finally
    //   101	111	290	finally
    //   127	160	290	finally
    //   160	168	290	finally
    //   177	265	290	finally
    //   265	268	290	finally
    //   271	274	290	finally
    //   291	294	290	finally
  }
  
  public final LoadedApk getPackageInfoNoCheck(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo) {
    return getPackageInfo(paramApplicationInfo, paramCompatibilityInfo, (ClassLoader)null, false, true, false);
  }
  
  public String getProcessName() {
    return this.mBoundApplication.processName;
  }
  
  public String getProfileFilePath() {
    return this.mProfiler.profileFile;
  }
  
  public String getStringCoreSetting(String paramString1, String paramString2) {
    synchronized (this.mResourcesManager) {
      if (this.mCoreSettings != null) {
        paramString1 = this.mCoreSettings.getString(paramString1, paramString2);
        return paramString1;
      } 
      return paramString2;
    } 
  }
  
  public ContextImpl getSystemContext() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mSystemContext : Landroid/app/ContextImpl;
    //   6: ifnonnull -> 17
    //   9: aload_0
    //   10: aload_0
    //   11: invokestatic createSystemContext : (Landroid/app/ActivityThread;)Landroid/app/ContextImpl;
    //   14: putfield mSystemContext : Landroid/app/ContextImpl;
    //   17: aload_0
    //   18: getfield mSystemContext : Landroid/app/ContextImpl;
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: areturn
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	26	finally
    //   17	24	26	finally
    //   27	29	26	finally
  }
  
  public ContextImpl getSystemUiContext() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mSystemUiContext : Landroid/app/ContextImpl;
    //   6: ifnonnull -> 20
    //   9: aload_0
    //   10: aload_0
    //   11: invokevirtual getSystemContext : ()Landroid/app/ContextImpl;
    //   14: invokestatic createSystemUiContext : (Landroid/app/ContextImpl;)Landroid/app/ContextImpl;
    //   17: putfield mSystemUiContext : Landroid/app/ContextImpl;
    //   20: aload_0
    //   21: getfield mSystemUiContext : Landroid/app/ContextImpl;
    //   24: astore_1
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_1
    //   28: areturn
    //   29: astore_1
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_1
    //   33: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	29	finally
    //   20	27	29	finally
    //   30	32	29	finally
  }
  
  Resources getTopLevelResources(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt, LoadedApk paramLoadedApk) {
    return this.mResourcesManager.getResources(null, paramString, paramArrayOfString1, paramArrayOfString2, paramArrayOfString3, paramInt, null, paramLoadedApk.getCompatibilityInfo(), paramLoadedApk.getClassLoader(), null);
  }
  
  TransactionExecutor getTransactionExecutor() {
    return this.mTransactionExecutor;
  }
  
  public void handleActivityConfigurationChanged(IBinder paramIBinder, Configuration paramConfiguration, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mActivities : Landroid/util/ArrayMap;
    //   4: aload_1
    //   5: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   8: checkcast android/app/ActivityThread$ActivityClientRecord
    //   11: astore #4
    //   13: aload #4
    //   15: ifnull -> 206
    //   18: aload #4
    //   20: getfield activity : Landroid/app/Activity;
    //   23: ifnonnull -> 29
    //   26: goto -> 206
    //   29: iload_3
    //   30: iconst_m1
    //   31: if_icmpeq -> 52
    //   34: iload_3
    //   35: aload #4
    //   37: getfield activity : Landroid/app/Activity;
    //   40: invokevirtual getDisplayId : ()I
    //   43: if_icmpeq -> 52
    //   46: iconst_1
    //   47: istore #5
    //   49: goto -> 55
    //   52: iconst_0
    //   53: istore #5
    //   55: aload #4
    //   57: monitorenter
    //   58: aload_2
    //   59: aload #4
    //   61: invokestatic access$4000 : (Landroid/app/ActivityThread$ActivityClientRecord;)Landroid/content/res/Configuration;
    //   64: invokevirtual isOtherSeqNewer : (Landroid/content/res/Configuration;)Z
    //   67: ifeq -> 74
    //   70: aload #4
    //   72: monitorexit
    //   73: return
    //   74: aconst_null
    //   75: astore_1
    //   76: aload #4
    //   78: aconst_null
    //   79: invokestatic access$4002 : (Landroid/app/ActivityThread$ActivityClientRecord;Landroid/content/res/Configuration;)Landroid/content/res/Configuration;
    //   82: pop
    //   83: aload #4
    //   85: monitorexit
    //   86: aload #4
    //   88: getfield overrideConfig : Landroid/content/res/Configuration;
    //   91: ifnull -> 112
    //   94: aload #4
    //   96: getfield overrideConfig : Landroid/content/res/Configuration;
    //   99: aload_2
    //   100: invokevirtual isOtherSeqNewer : (Landroid/content/res/Configuration;)Z
    //   103: ifne -> 112
    //   106: iload #5
    //   108: ifne -> 112
    //   111: return
    //   112: aload #4
    //   114: aload_2
    //   115: putfield overrideConfig : Landroid/content/res/Configuration;
    //   118: aload #4
    //   120: getfield activity : Landroid/app/Activity;
    //   123: getfield mDecor : Landroid/view/View;
    //   126: ifnull -> 144
    //   129: aload #4
    //   131: getfield activity : Landroid/app/Activity;
    //   134: getfield mDecor : Landroid/view/View;
    //   137: invokevirtual getViewRootImpl : ()Landroid/view/ViewRootImpl;
    //   140: astore_1
    //   141: goto -> 144
    //   144: iload #5
    //   146: ifeq -> 175
    //   149: aload_0
    //   150: aload #4
    //   152: aload_0
    //   153: getfield mCompatConfiguration : Landroid/content/res/Configuration;
    //   156: iload_3
    //   157: iconst_1
    //   158: invokespecial performConfigurationChangedForActivity : (Landroid/app/ActivityThread$ActivityClientRecord;Landroid/content/res/Configuration;IZ)Landroid/content/res/Configuration;
    //   161: astore_2
    //   162: aload_1
    //   163: ifnull -> 172
    //   166: aload_1
    //   167: iload_3
    //   168: aload_2
    //   169: invokevirtual onMovedToDisplay : (ILandroid/content/res/Configuration;)V
    //   172: goto -> 185
    //   175: aload_0
    //   176: aload #4
    //   178: aload_0
    //   179: getfield mCompatConfiguration : Landroid/content/res/Configuration;
    //   182: invokespecial performConfigurationChangedForActivity : (Landroid/app/ActivityThread$ActivityClientRecord;Landroid/content/res/Configuration;)V
    //   185: aload_1
    //   186: ifnull -> 194
    //   189: aload_1
    //   190: iload_3
    //   191: invokevirtual updateConfiguration : (I)V
    //   194: aload_0
    //   195: iconst_1
    //   196: putfield mSomeActivitiesChanged : Z
    //   199: return
    //   200: astore_1
    //   201: aload #4
    //   203: monitorexit
    //   204: aload_1
    //   205: athrow
    //   206: return
    // Exception table:
    //   from	to	target	type
    //   58	73	200	finally
    //   76	86	200	finally
    //   201	204	200	finally
  }
  
  public void handleApplicationInfoChanged(ApplicationInfo paramApplicationInfo) {
    ResourcesManager resourcesManager;
    String[] arrayOfString;
    synchronized (this.mResourcesManager) {
      ResourcesManager resourcesManager1;
      Configuration configuration;
      WeakReference<LoadedApk> weakReference1 = (WeakReference)this.mPackages.get(paramApplicationInfo.packageName);
      if (weakReference1 != null) {
        LoadedApk loadedApk = weakReference1.get();
      } else {
        weakReference1 = null;
      } 
      WeakReference<LoadedApk> weakReference2 = (WeakReference)this.mResourcePackages.get(paramApplicationInfo.packageName);
      if (weakReference2 != null) {
        LoadedApk loadedApk = weakReference2.get();
      } else {
        weakReference2 = null;
      } 
      arrayOfString = new String[2];
      int i = 0;
      if (weakReference1 != null) {
        arrayOfString[0] = weakReference1.getResDir();
        ArrayList<String> arrayList = new ArrayList();
        LoadedApk.makePaths(this, weakReference1.getApplicationInfo(), arrayList);
        weakReference1.updateApplicationInfo(paramApplicationInfo, arrayList);
      } 
      if (weakReference2 != null) {
        arrayOfString[1] = weakReference2.getResDir();
        ArrayList<String> arrayList = new ArrayList();
        LoadedApk.makePaths(this, weakReference2.getApplicationInfo(), arrayList);
        weakReference2.updateApplicationInfo(paramApplicationInfo, arrayList);
      } 
      synchronized (this.mResourcesManager) {
        this.mResourcesManager.applyNewResourceDirsLocked(paramApplicationInfo, arrayOfString);
        ApplicationPackageManager.configurationChanged();
        configuration = new Configuration();
        Configuration configuration1 = this.mConfiguration;
        if (configuration1 != null)
          i = configuration1.assetsSeq; 
        configuration.assetsSeq = i + 1;
        handleConfigurationChanged(configuration, (CompatibilityInfo)null);
        relaunchAllActivities(true);
        return;
      } 
    } 
  }
  
  public void handleConfigurationChanged(Configuration paramConfiguration) {
    Trace.traceBegin(64L, "configChanged");
    this.mCurDefaultDisplayDpi = paramConfiguration.densityDpi;
    handleConfigurationChanged(paramConfiguration, (CompatibilityInfo)null);
    Trace.traceEnd(64L);
  }
  
  public void handleDestroyActivity(IBinder paramIBinder, boolean paramBoolean1, int paramInt, boolean paramBoolean2, String paramString) {
    ActivityClientRecord activityClientRecord = performDestroyActivity(paramIBinder, paramBoolean1, paramInt, paramBoolean2, paramString);
    if (activityClientRecord != null) {
      cleanUpPendingRemoveWindows(activityClientRecord, paramBoolean1);
      WindowManager windowManager = activityClientRecord.activity.getWindowManager();
      View view = activityClientRecord.activity.mDecor;
      if (view != null) {
        if (activityClientRecord.activity.mVisibleFromServer)
          this.mNumVisibleActivities--; 
        IBinder iBinder = view.getWindowToken();
        if (activityClientRecord.activity.mWindowAdded)
          if (activityClientRecord.mPreserveWindow) {
            activityClientRecord.mPendingRemoveWindow = activityClientRecord.window;
            activityClientRecord.mPendingRemoveWindowManager = windowManager;
            activityClientRecord.window.clearContentView();
          } else {
            windowManager.removeViewImmediate(view);
          }  
        if (iBinder != null && activityClientRecord.mPendingRemoveWindow == null) {
          WindowManagerGlobal.getInstance().closeAll(iBinder, activityClientRecord.activity.getClass().getName(), "Activity");
        } else if (activityClientRecord.mPendingRemoveWindow != null) {
          WindowManagerGlobal.getInstance().closeAllExceptView(paramIBinder, view, activityClientRecord.activity.getClass().getName(), "Activity");
        } 
        activityClientRecord.activity.mDecor = null;
      } 
      if (activityClientRecord.mPendingRemoveWindow == null)
        WindowManagerGlobal.getInstance().closeAll(paramIBinder, activityClientRecord.activity.getClass().getName(), "Activity"); 
      Context context = activityClientRecord.activity.getBaseContext();
      if (context instanceof ContextImpl)
        ((ContextImpl)context).scheduleFinalCleanup(activityClientRecord.activity.getClass().getName(), "Activity"); 
    } 
    if (paramBoolean1)
      try {
        ActivityTaskManager.getService().activityDestroyed(paramIBinder);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    this.mSomeActivitiesChanged = true;
  }
  
  final void handleDispatchPackageBroadcast(int paramInt, String[] paramArrayOfString) {
    boolean bool1 = false;
    boolean bool2 = false;
    int i = 0;
    if (paramInt != 0 && paramInt != 2) {
      if (paramInt != 3) {
        bool2 = bool1;
      } else if (paramArrayOfString == null) {
        bool2 = bool1;
      } else {
        ArrayList<String> arrayList = new ArrayList();
        synchronized (this.mResourcesManager) {
          i = paramArrayOfString.length;
          bool2 = false;
          i--;
          while (true) {
            if (i >= 0) {
              String str = paramArrayOfString[i];
              try {
                LoadedApk loadedApk;
                WeakReference<LoadedApk> weakReference = (WeakReference)this.mPackages.get(str);
                WeakReference weakReference1 = null;
                if (weakReference != null) {
                  loadedApk = weakReference.get();
                } else {
                  weakReference = null;
                } 
                if (weakReference != null) {
                  bool2 = true;
                } else {
                  WeakReference<LoadedApk> weakReference2 = (WeakReference)this.mResourcePackages.get(str);
                  weakReference = weakReference1;
                  if (weakReference2 != null)
                    loadedApk = weakReference2.get(); 
                  if (loadedApk != null)
                    bool2 = true; 
                } 
                if (loadedApk != null)
                  try {
                    arrayList.add(str);
                    try {
                      null = sPackageManager.getApplicationInfo(str, 1024, UserHandle.myUserId());
                      if (this.mActivities.size() > 0)
                        for (ActivityClientRecord activityClientRecord : this.mActivities.values()) {
                          if (activityClientRecord.activityInfo.applicationInfo.packageName.equals(str)) {
                            activityClientRecord.activityInfo.applicationInfo = null;
                            activityClientRecord.packageInfo = loadedApk;
                          } 
                        }  
                      str = loadedApk.getResDir();
                      ArrayList<String> arrayList1 = new ArrayList();
                      this();
                      LoadedApk.makePaths(this, loadedApk.getApplicationInfo(), arrayList1);
                      loadedApk.updateApplicationInfo(null, arrayList1);
                      synchronized (this.mResourcesManager) {
                        this.mResourcesManager.applyNewResourceDirsLocked(null, new String[] { str });
                      } 
                    } catch (RemoteException remoteException) {}
                  } finally {} 
                i--;
                continue;
              } finally {}
            } else {
              try {
                getPackageManager().notifyPackagesReplacedReceived(arrayList.<String>toArray(new String[0]));
              } catch (RemoteException remoteException) {}
              break;
            } 
            throw paramArrayOfString;
          } 
        } 
      } 
    } else {
      if (paramInt == 0)
        i = 1; 
      if (paramArrayOfString == null) {
        bool2 = bool1;
      } else {
        synchronized (this.mResourcesManager) {
          int j = paramArrayOfString.length - 1;
          while (j >= 0) {
            bool1 = bool2;
            if (!bool2) {
              WeakReference weakReference = (WeakReference)this.mPackages.get(paramArrayOfString[j]);
              if (weakReference != null && weakReference.get() != null) {
                bool1 = true;
              } else {
                weakReference = (WeakReference)this.mResourcePackages.get(paramArrayOfString[j]);
                bool1 = bool2;
                if (weakReference != null) {
                  bool1 = bool2;
                  if (weakReference.get() != null)
                    bool1 = true; 
                } 
              } 
            } 
            if (i != 0) {
              this.mPackages.remove(paramArrayOfString[j]);
              this.mResourcePackages.remove(paramArrayOfString[j]);
            } 
            j--;
            bool2 = bool1;
          } 
          ApplicationPackageManager.handlePackageBroadcast(paramInt, paramArrayOfString, bool2);
          return;
        } 
      } 
    } 
    ApplicationPackageManager.handlePackageBroadcast(paramInt, paramArrayOfString, bool2);
  }
  
  public void handleFixedRotationAdjustments(IBinder paramIBinder, DisplayAdjustments.FixedRotationAdjustments paramFixedRotationAdjustments) {
    if (paramFixedRotationAdjustments != null) {
      _$$Lambda$ActivityThread$56nvK2b3yO5_TfyfDxdjntgxFgI _$$Lambda$ActivityThread$56nvK2b3yO5_TfyfDxdjntgxFgI = new _$$Lambda$ActivityThread$56nvK2b3yO5_TfyfDxdjntgxFgI(paramFixedRotationAdjustments);
    } else {
      paramFixedRotationAdjustments = null;
    } 
    if (!this.mResourcesManager.overrideTokenDisplayAdjustments(paramIBinder, (Consumer<DisplayAdjustments>)paramFixedRotationAdjustments))
      return; 
    if (this.mActivities.get(paramIBinder) == null)
      return; 
    overrideApplicationDisplayAdjustments(paramIBinder, (Consumer<DisplayAdjustments>)paramFixedRotationAdjustments);
  }
  
  public void handleInstallProvider(ProviderInfo paramProviderInfo) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.allowThreadDiskWrites();
    try {
      installContentProviders((Context)this.mInitialApplication, Arrays.asList(new ProviderInfo[] { paramProviderInfo }));
      return;
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  public Activity handleLaunchActivity(ActivityClientRecord paramActivityClientRecord, PendingTransactionActions paramPendingTransactionActions, Intent paramIntent) {
    unscheduleGcIdler();
    this.mSomeActivitiesChanged = true;
    if (paramActivityClientRecord.profilerInfo != null) {
      this.mProfiler.setProfiler(paramActivityClientRecord.profilerInfo);
      this.mProfiler.startProfiling();
    } 
    if (paramActivityClientRecord.mPendingFixedRotationAdjustments != null)
      overrideApplicationDisplayAdjustments(paramActivityClientRecord.token, new _$$Lambda$ActivityThread$1pr9ATUEz5ruJUGCmPxyPg12Cp8(paramActivityClientRecord)); 
    handleConfigurationChanged((Configuration)null, (CompatibilityInfo)null);
    if (!ThreadedRenderer.sRendererDisabled && (paramActivityClientRecord.activityInfo.flags & 0x200) != 0)
      HardwareRenderer.preload(); 
    WindowManagerGlobal.initialize();
    GraphicsEnvironment.hintActivityLaunch();
    Activity activity = performLaunchActivity(paramActivityClientRecord, paramIntent);
    if (activity != null) {
      paramActivityClientRecord.createdConfig = new Configuration(this.mConfiguration);
      reportSizeConfigurations(paramActivityClientRecord);
      if (!paramActivityClientRecord.activity.mFinished && paramPendingTransactionActions != null) {
        paramPendingTransactionActions.setOldState(paramActivityClientRecord.state);
        paramPendingTransactionActions.setRestoreInstanceState(true);
        paramPendingTransactionActions.setCallOnPostCreate(true);
      } 
    } else {
      try {
        ActivityTaskManager.getService().finishActivity(paramActivityClientRecord.token, 0, null, 0);
        return activity;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
    return activity;
  }
  
  final void handleLowMemory() {
    ArrayList<ComponentCallbacks2> arrayList = collectComponentCallbacks(true, (Configuration)null);
    int i = arrayList.size();
    for (byte b = 0; b < i; b++)
      ((ComponentCallbacks2)arrayList.get(b)).onLowMemory(); 
    if (Process.myUid() != 1000)
      EventLog.writeEvent(75003, SQLiteDatabase.releaseMemory()); 
    Canvas.freeCaches();
    Canvas.freeTextLayoutCaches();
    BinderInternal.forceGc("mem");
  }
  
  public void handleNewIntent(IBinder paramIBinder, List<ReferrerIntent> paramList) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord == null)
      return; 
    checkAndBlockForNetworkAccess();
    deliverNewIntents(activityClientRecord, paramList);
  }
  
  public void handlePauseActivity(IBinder paramIBinder, boolean paramBoolean1, boolean paramBoolean2, int paramInt, PendingTransactionActions paramPendingTransactionActions, String paramString) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord != null) {
      if (paramBoolean2)
        performUserLeavingActivity(activityClientRecord); 
      Activity activity = activityClientRecord.activity;
      activity.mConfigChangeFlags |= paramInt;
      performPauseActivity(activityClientRecord, paramBoolean1, paramString, paramPendingTransactionActions);
      if (activityClientRecord.isPreHoneycomb())
        QueuedWork.waitToFinish(); 
      this.mSomeActivitiesChanged = true;
    } 
  }
  
  public void handlePictureInPictureRequested(IBinder paramIBinder) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord == null) {
      Log.w("ActivityThread", "Activity to request PIP to no longer exists");
      return;
    } 
    if (!activityClientRecord.activity.onPictureInPictureRequested())
      schedulePauseWithUserLeaveHintAndReturnToCurrentState(activityClientRecord); 
  }
  
  final void handleProfilerControl(boolean paramBoolean, ProfilerInfo paramProfilerInfo, int paramInt) {
    if (paramBoolean) {
      try {
        this.mProfiler.setProfiler(paramProfilerInfo);
        this.mProfiler.startProfiling();
        paramProfilerInfo.closeFd();
      } catch (RuntimeException runtimeException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Profiling failed on path ");
        stringBuilder.append(paramProfilerInfo.profileFile);
        stringBuilder.append(" -- can the process access this path?");
        Slog.w("ActivityThread", stringBuilder.toString());
        paramProfilerInfo.closeFd();
      } finally {
        Exception exception;
      } 
    } else {
      this.mProfiler.stopProfiling();
    } 
  }
  
  public void handleRelaunchActivity(ActivityClientRecord paramActivityClientRecord, PendingTransactionActions paramPendingTransactionActions) {
    unscheduleGcIdler();
    this.mSomeActivitiesChanged = true;
    ActivityClientRecord activityClientRecord = null;
    ResourcesManager resourcesManager = this.mResourcesManager;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{android/app/ResourcesManager}, name=null} */
    try {
      int i = this.mRelaunchingActivities.size();
      try {
        IBinder iBinder = paramActivityClientRecord.token;
        int j = 0;
        int k = 0;
        ActivityClientRecord activityClientRecord1 = null;
        while (true) {
          if (j < i) {
            try {
              paramActivityClientRecord = this.mRelaunchingActivities.get(j);
              IBinder iBinder1 = paramActivityClientRecord.token;
              int m = i;
              int n = j;
              int i1 = k;
              if (iBinder1 == iBinder)
                try {
                  i1 = paramActivityClientRecord.pendingConfigChanges;
                  try {
                    this.mRelaunchingActivities.remove(j);
                    n = j - 1;
                    m = i - 1;
                    activityClientRecord1 = paramActivityClientRecord;
                    i1 |= k;
                  } finally {}
                } finally {} 
              j = n + 1;
              i = m;
              k = i1;
              continue;
            } finally {}
          } else {
            Configuration configuration;
            if (activityClientRecord1 == null) {
              /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/app/ResourcesManager}, name=null} */
              return;
            } 
            paramActivityClientRecord = activityClientRecord;
            if (this.mPendingConfiguration != null) {
              configuration = this.mPendingConfiguration;
              this.mPendingConfiguration = null;
            } 
            /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/app/ResourcesManager}, name=null} */
            if (activityClientRecord1.createdConfig != null && (this.mConfiguration == null || (activityClientRecord1.createdConfig.isOtherSeqNewer(this.mConfiguration) && this.mConfiguration.diff(activityClientRecord1.createdConfig) != 0)) && (configuration == null || activityClientRecord1.createdConfig.isOtherSeqNewer(configuration)))
              configuration = activityClientRecord1.createdConfig; 
            if (configuration != null) {
              this.mCurDefaultDisplayDpi = configuration.densityDpi;
              updateDefaultDensity();
              handleConfigurationChanged(configuration, (CompatibilityInfo)null);
            } 
            activityClientRecord = (ActivityClientRecord)this.mActivities.get(activityClientRecord1.token);
            if (activityClientRecord == null)
              return; 
            Activity activity = activityClientRecord.activity;
            activity.mConfigChangeFlags |= k;
            activityClientRecord.mPreserveWindow = activityClientRecord1.mPreserveWindow;
            activityClientRecord.activity.mChangingConfigurations = true;
            try {
              if (activityClientRecord.mPreserveWindow)
                WindowManagerGlobal.getWindowSession().prepareToReplaceWindows(activityClientRecord.token, true); 
              j = ((Integer)this.mLastReportedWindowingMode.getOrDefault(activityClientRecord.activity.getActivityToken(), Integer.valueOf(0))).intValue();
              handleRelaunchActivityInner(activityClientRecord, k, activityClientRecord1.pendingResults, activityClientRecord1.pendingIntents, paramPendingTransactionActions, activityClientRecord1.startsNotResumed, activityClientRecord1.overrideConfig, "handleRelaunchActivity");
              this.mLastReportedWindowingMode.put(activityClientRecord.activity.getActivityToken(), Integer.valueOf(j));
              handleWindowingModeChangeIfNeeded(activityClientRecord.activity, activityClientRecord.activity.mCurrentConfig);
              if (paramPendingTransactionActions != null)
                paramPendingTransactionActions.setReportRelaunchToWindowManager(true); 
              return;
            } catch (RemoteException remoteException) {
              throw remoteException.rethrowFromSystemServer();
            } 
          } 
          while (true) {
            try {
              /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/app/ResourcesManager}, name=null} */
              throw remoteException;
            } finally {
              remoteException = null;
            } 
          } 
          break;
        } 
      } finally {}
    } finally {}
    while (true) {
      try {
        /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/app/ResourcesManager}, name=null} */
        throw paramActivityClientRecord;
      } finally {
        paramActivityClientRecord = null;
      } 
    } 
    break;
  }
  
  public void handleRequestAssistContextExtras(RequestAssistContextExtras paramRequestAssistContextExtras) {
    // Byte code:
    //   0: aload_1
    //   1: getfield requestType : I
    //   4: istore_2
    //   5: iconst_0
    //   6: istore_3
    //   7: iload_2
    //   8: iconst_2
    //   9: if_icmpne -> 18
    //   12: iconst_1
    //   13: istore #4
    //   15: goto -> 21
    //   18: iconst_0
    //   19: istore #4
    //   21: aload_0
    //   22: getfield mLastSessionId : I
    //   25: aload_1
    //   26: getfield sessionId : I
    //   29: if_icmpeq -> 98
    //   32: aload_0
    //   33: aload_1
    //   34: getfield sessionId : I
    //   37: putfield mLastSessionId : I
    //   40: aload_0
    //   41: getfield mLastAssistStructures : Ljava/util/ArrayList;
    //   44: invokevirtual size : ()I
    //   47: iconst_1
    //   48: isub
    //   49: istore_2
    //   50: iload_2
    //   51: iflt -> 98
    //   54: aload_0
    //   55: getfield mLastAssistStructures : Ljava/util/ArrayList;
    //   58: iload_2
    //   59: invokevirtual get : (I)Ljava/lang/Object;
    //   62: checkcast java/lang/ref/WeakReference
    //   65: invokevirtual get : ()Ljava/lang/Object;
    //   68: checkcast android/app/assist/AssistStructure
    //   71: astore #5
    //   73: aload #5
    //   75: ifnull -> 83
    //   78: aload #5
    //   80: invokevirtual clearSendChannel : ()V
    //   83: aload_0
    //   84: getfield mLastAssistStructures : Ljava/util/ArrayList;
    //   87: iload_2
    //   88: invokevirtual remove : (I)Ljava/lang/Object;
    //   91: pop
    //   92: iinc #2, -1
    //   95: goto -> 50
    //   98: new android/os/Bundle
    //   101: dup
    //   102: invokespecial <init> : ()V
    //   105: astore #6
    //   107: aconst_null
    //   108: astore #7
    //   110: iload #4
    //   112: ifeq -> 121
    //   115: aconst_null
    //   116: astore #8
    //   118: goto -> 130
    //   121: new android/app/assist/AssistContent
    //   124: dup
    //   125: invokespecial <init> : ()V
    //   128: astore #8
    //   130: invokestatic uptimeMillis : ()J
    //   133: lstore #9
    //   135: aload_0
    //   136: getfield mActivities : Landroid/util/ArrayMap;
    //   139: aload_1
    //   140: getfield activityToken : Landroid/os/IBinder;
    //   143: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   146: checkcast android/app/ActivityThread$ActivityClientRecord
    //   149: astore #11
    //   151: aconst_null
    //   152: astore #5
    //   154: aload #11
    //   156: ifnull -> 370
    //   159: iload #4
    //   161: ifne -> 202
    //   164: aload #11
    //   166: getfield activity : Landroid/app/Activity;
    //   169: invokevirtual getApplication : ()Landroid/app/Application;
    //   172: aload #11
    //   174: getfield activity : Landroid/app/Activity;
    //   177: aload #6
    //   179: invokevirtual dispatchOnProvideAssistData : (Landroid/app/Activity;Landroid/os/Bundle;)V
    //   182: aload #11
    //   184: getfield activity : Landroid/app/Activity;
    //   187: aload #6
    //   189: invokevirtual onProvideAssistData : (Landroid/os/Bundle;)V
    //   192: aload #11
    //   194: getfield activity : Landroid/app/Activity;
    //   197: invokevirtual onProvideReferrer : ()Landroid/net/Uri;
    //   200: astore #5
    //   202: aload_1
    //   203: getfield requestType : I
    //   206: iconst_1
    //   207: if_icmpeq -> 221
    //   210: iload #4
    //   212: ifeq -> 218
    //   215: goto -> 221
    //   218: goto -> 373
    //   221: new android/app/assist/AssistStructure
    //   224: dup
    //   225: aload #11
    //   227: getfield activity : Landroid/app/Activity;
    //   230: iload #4
    //   232: aload_1
    //   233: getfield flags : I
    //   236: invokespecial <init> : (Landroid/app/Activity;ZI)V
    //   239: astore #7
    //   241: aload #11
    //   243: getfield activity : Landroid/app/Activity;
    //   246: invokevirtual getIntent : ()Landroid/content/Intent;
    //   249: astore #12
    //   251: aload #11
    //   253: getfield window : Landroid/view/Window;
    //   256: ifnull -> 279
    //   259: iload_3
    //   260: istore_2
    //   261: aload #11
    //   263: getfield window : Landroid/view/Window;
    //   266: invokevirtual getAttributes : ()Landroid/view/WindowManager$LayoutParams;
    //   269: getfield flags : I
    //   272: sipush #8192
    //   275: iand
    //   276: ifne -> 281
    //   279: iconst_1
    //   280: istore_2
    //   281: aload #12
    //   283: ifnull -> 335
    //   286: iload_2
    //   287: ifeq -> 335
    //   290: iload #4
    //   292: ifne -> 352
    //   295: new android/content/Intent
    //   298: dup
    //   299: aload #12
    //   301: invokespecial <init> : (Landroid/content/Intent;)V
    //   304: astore #12
    //   306: aload #12
    //   308: aload #12
    //   310: invokevirtual getFlags : ()I
    //   313: bipush #-67
    //   315: iand
    //   316: invokevirtual setFlags : (I)Landroid/content/Intent;
    //   319: pop
    //   320: aload #12
    //   322: invokevirtual removeUnsafeExtras : ()V
    //   325: aload #8
    //   327: aload #12
    //   329: invokevirtual setDefaultIntent : (Landroid/content/Intent;)V
    //   332: goto -> 352
    //   335: iload #4
    //   337: ifne -> 352
    //   340: aload #8
    //   342: new android/content/Intent
    //   345: dup
    //   346: invokespecial <init> : ()V
    //   349: invokevirtual setDefaultIntent : (Landroid/content/Intent;)V
    //   352: iload #4
    //   354: ifne -> 367
    //   357: aload #11
    //   359: getfield activity : Landroid/app/Activity;
    //   362: aload #8
    //   364: invokevirtual onProvideAssistContent : (Landroid/app/assist/AssistContent;)V
    //   367: goto -> 373
    //   370: aconst_null
    //   371: astore #5
    //   373: aload #7
    //   375: ifnonnull -> 390
    //   378: new android/app/assist/AssistStructure
    //   381: dup
    //   382: invokespecial <init> : ()V
    //   385: astore #7
    //   387: goto -> 390
    //   390: aload #7
    //   392: lload #9
    //   394: invokevirtual setAcquisitionStartTime : (J)V
    //   397: aload #7
    //   399: invokestatic uptimeMillis : ()J
    //   402: invokevirtual setAcquisitionEndTime : (J)V
    //   405: aload_0
    //   406: getfield mLastAssistStructures : Ljava/util/ArrayList;
    //   409: new java/lang/ref/WeakReference
    //   412: dup
    //   413: aload #7
    //   415: invokespecial <init> : (Ljava/lang/Object;)V
    //   418: invokevirtual add : (Ljava/lang/Object;)Z
    //   421: pop
    //   422: invokestatic getService : ()Landroid/app/IActivityTaskManager;
    //   425: astore #11
    //   427: aload_1
    //   428: getfield requestToken : Landroid/os/IBinder;
    //   431: astore_1
    //   432: aload #11
    //   434: aload_1
    //   435: aload #6
    //   437: aload #7
    //   439: aload #8
    //   441: aload #5
    //   443: invokeinterface reportAssistContextExtras : (Landroid/os/IBinder;Landroid/os/Bundle;Landroid/app/assist/AssistStructure;Landroid/app/assist/AssistContent;Landroid/net/Uri;)V
    //   448: return
    //   449: astore_1
    //   450: goto -> 454
    //   453: astore_1
    //   454: aload_1
    //   455: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   458: athrow
    // Exception table:
    //   from	to	target	type
    //   427	432	453	android/os/RemoteException
    //   432	448	449	android/os/RemoteException
  }
  
  public void handleResumeActivity(IBinder paramIBinder, boolean paramBoolean1, boolean paramBoolean2, String paramString) {
    boolean bool;
    boolean bool1;
    unscheduleGcIdler();
    this.mSomeActivitiesChanged = true;
    ActivityClientRecord activityClientRecord = performResumeActivity(paramIBinder, paramBoolean1, paramString);
    if (activityClientRecord == null)
      return; 
    if (this.mActivitiesToBeDestroyed.containsKey(paramIBinder))
      return; 
    Activity activity = activityClientRecord.activity;
    if (paramBoolean2) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    int j = activity.mStartedActivity ^ true;
    int i = j;
    if (j == 0)
      try {
        bool = ActivityTaskManager.getService().willActivityBeVisible(activity.getActivityToken());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    if (activityClientRecord.window == null && !activity.mFinished && bool) {
      activityClientRecord.window = activityClientRecord.activity.getWindow();
      View view = activityClientRecord.window.getDecorView();
      view.setVisibility(4);
      WindowManager windowManager = activity.getWindowManager();
      WindowManager.LayoutParams layoutParams = activityClientRecord.window.getAttributes();
      activity.mDecor = view;
      layoutParams.type = 1;
      layoutParams.softInputMode |= bool1;
      if (activityClientRecord.mPreserveWindow) {
        activity.mWindowAdded = true;
        activityClientRecord.mPreserveWindow = false;
        ViewRootImpl viewRootImpl = view.getViewRootImpl();
        if (viewRootImpl != null)
          viewRootImpl.notifyChildRebuilt(); 
      } 
      if (activity.mVisibleFromClient)
        if (!activity.mWindowAdded) {
          activity.mWindowAdded = true;
          windowManager.addView(view, (ViewGroup.LayoutParams)layoutParams);
        } else {
          activity.onWindowAttributesChanged(layoutParams);
        }  
    } else if (!bool) {
      activityClientRecord.hideForNow = true;
    } 
    cleanUpPendingRemoveWindows(activityClientRecord, false);
    if (!activityClientRecord.activity.mFinished && bool && activityClientRecord.activity.mDecor != null && !activityClientRecord.hideForNow) {
      WindowManager.LayoutParams layoutParams;
      if (activityClientRecord.newConfig != null) {
        performConfigurationChangedForActivity(activityClientRecord, activityClientRecord.newConfig);
        activityClientRecord.newConfig = null;
      } 
      ViewRootImpl viewRootImpl = activityClientRecord.window.getDecorView().getViewRootImpl();
      if (viewRootImpl != null) {
        layoutParams = viewRootImpl.mWindowAttributes;
      } else {
        layoutParams = activityClientRecord.window.getAttributes();
      } 
      if ((0x100 & layoutParams.softInputMode) != bool1) {
        layoutParams.softInputMode = layoutParams.softInputMode & 0xFFFFFEFF | bool1;
        if (activityClientRecord.activity.mVisibleFromClient)
          activity.getWindowManager().updateViewLayout(activityClientRecord.window.getDecorView(), (ViewGroup.LayoutParams)layoutParams); 
      } 
      activityClientRecord.activity.mVisibleFromServer = true;
      this.mNumVisibleActivities++;
      if (activityClientRecord.activity.mVisibleFromClient)
        activityClientRecord.activity.makeVisible(); 
    } 
    activityClientRecord.nextIdle = this.mNewActivities;
    this.mNewActivities = activityClientRecord;
    Looper.myQueue().addIdleHandler(new Idler());
  }
  
  public void handleSendResult(IBinder paramIBinder, List<ResultInfo> paramList, String paramString) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord != null) {
      StringBuilder stringBuilder;
      int i = activityClientRecord.paused ^ true;
      if (!activityClientRecord.activity.mFinished && activityClientRecord.activity.mDecor != null && activityClientRecord.hideForNow && i != 0)
        updateVisibility(activityClientRecord, true); 
      if (i != 0)
        try {
          activityClientRecord.activity.mCalled = false;
          this.mInstrumentation.callActivityOnPause(activityClientRecord.activity);
          if (!activityClientRecord.activity.mCalled) {
            SuperNotCalledException superNotCalledException1 = new SuperNotCalledException();
            StringBuilder stringBuilder1 = new StringBuilder();
            this();
            stringBuilder1.append("Activity ");
            stringBuilder1.append(activityClientRecord.intent.getComponent().toShortString());
            stringBuilder1.append(" did not call through to super.onPause()");
            this(stringBuilder1.toString());
            throw superNotCalledException1;
          } 
        } catch (SuperNotCalledException superNotCalledException) {
          throw superNotCalledException;
        } catch (Exception exception) {
          if (!this.mInstrumentation.onException(((ActivityClientRecord)superNotCalledException).activity, exception)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to pause activity ");
            stringBuilder.append(((ActivityClientRecord)superNotCalledException).intent.getComponent().toShortString());
            stringBuilder.append(": ");
            stringBuilder.append(exception.toString());
            throw new RuntimeException(stringBuilder.toString(), exception);
          } 
        }  
      checkAndBlockForNetworkAccess();
      deliverResults((ActivityClientRecord)superNotCalledException, (List<ResultInfo>)stringBuilder, paramString);
      if (i != 0)
        ((ActivityClientRecord)superNotCalledException).activity.performResume(false, paramString); 
    } 
  }
  
  public void handleStartActivity(IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    Activity activity = activityClientRecord.activity;
    if (activityClientRecord.activity == null)
      return; 
    if (activityClientRecord.stopped) {
      if (activityClientRecord.activity.mFinished)
        return; 
      unscheduleGcIdler();
      activity.performStart("handleStartActivity");
      activityClientRecord.setState(2);
      if (paramPendingTransactionActions == null)
        return; 
      if (paramPendingTransactionActions.shouldRestoreInstanceState())
        if (activityClientRecord.isPersistable()) {
          if (activityClientRecord.state != null || activityClientRecord.persistentState != null)
            this.mInstrumentation.callActivityOnRestoreInstanceState(activity, activityClientRecord.state, activityClientRecord.persistentState); 
        } else if (activityClientRecord.state != null) {
          this.mInstrumentation.callActivityOnRestoreInstanceState(activity, activityClientRecord.state);
        }  
      if (paramPendingTransactionActions.shouldCallOnPostCreate()) {
        activity.mCalled = false;
        if (activityClientRecord.isPersistable()) {
          this.mInstrumentation.callActivityOnPostCreate(activity, activityClientRecord.state, activityClientRecord.persistentState);
        } else {
          this.mInstrumentation.callActivityOnPostCreate(activity, activityClientRecord.state);
        } 
        if (!activity.mCalled) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Activity ");
          stringBuilder.append(activityClientRecord.intent.getComponent().toShortString());
          stringBuilder.append(" did not call through to super.onPostCreate()");
          throw new SuperNotCalledException(stringBuilder.toString());
        } 
      } 
      updateVisibility(activityClientRecord, true);
      this.mSomeActivitiesChanged = true;
      return;
    } 
    throw new IllegalStateException("Can't start activity that is not stopped.");
  }
  
  public void handleStopActivity(IBinder paramIBinder, int paramInt, PendingTransactionActions paramPendingTransactionActions, boolean paramBoolean, String paramString) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    Activity activity = activityClientRecord.activity;
    activity.mConfigChangeFlags |= paramInt;
    PendingTransactionActions.StopInfo stopInfo = new PendingTransactionActions.StopInfo();
    performStopActivityInner(activityClientRecord, stopInfo, true, paramBoolean, paramString);
    updateVisibility(activityClientRecord, false);
    if (!activityClientRecord.isPreHoneycomb())
      QueuedWork.waitToFinish(); 
    stopInfo.setActivity(activityClientRecord);
    stopInfo.setState(activityClientRecord.state);
    stopInfo.setPersistentState(activityClientRecord.persistentState);
    paramPendingTransactionActions.setStopInfo(stopInfo);
    this.mSomeActivitiesChanged = true;
  }
  
  public void handleSystemApplicationInfoChanged(ApplicationInfo paramApplicationInfo) {
    Preconditions.checkState(this.mSystemThread, "Must only be called in the system process");
    handleApplicationInfoChanged(paramApplicationInfo);
  }
  
  public void handleTopResumedActivityChanged(IBinder paramIBinder, boolean paramBoolean, String paramString) {
    StringBuilder stringBuilder2;
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord == null || activityClientRecord.activity == null) {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Not found target activity to report position change for token: ");
      stringBuilder2.append(paramIBinder);
      Slog.w("ActivityThread", stringBuilder2.toString());
      return;
    } 
    if (((ActivityClientRecord)stringBuilder2).isTopResumedActivity != paramBoolean) {
      ((ActivityClientRecord)stringBuilder2).isTopResumedActivity = paramBoolean;
      if (stringBuilder2.getLifecycleState() == 3)
        reportTopResumedActivityChanged((ActivityClientRecord)stringBuilder2, paramBoolean, "topStateChangedWhenResumed"); 
      return;
    } 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Activity top position already set to onTop=");
    stringBuilder1.append(paramBoolean);
    throw new IllegalStateException(stringBuilder1.toString());
  }
  
  public void handleTranslucentConversionComplete(IBinder paramIBinder, boolean paramBoolean) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord != null)
      activityClientRecord.activity.onTranslucentConversionComplete(paramBoolean); 
  }
  
  final void handleUnstableProviderDied(IBinder paramIBinder, boolean paramBoolean) {
    synchronized (this.mProviderMap) {
      handleUnstableProviderDiedLocked(paramIBinder, paramBoolean);
      return;
    } 
  }
  
  final void handleUnstableProviderDiedLocked(IBinder paramIBinder, boolean paramBoolean) {
    ProviderRefCount providerRefCount = (ProviderRefCount)this.mProviderRefCountMap.get(paramIBinder);
    if (providerRefCount != null) {
      this.mProviderRefCountMap.remove(paramIBinder);
      for (int i = this.mProviderMap.size() - 1; i >= 0; i--) {
        ProviderClientRecord providerClientRecord = (ProviderClientRecord)this.mProviderMap.valueAt(i);
        if (providerClientRecord != null && providerClientRecord.mProvider.asBinder() == paramIBinder) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Removing dead content provider:");
          stringBuilder.append(providerClientRecord.mProvider.toString());
          Slog.i("ActivityThread", stringBuilder.toString());
          this.mProviderMap.removeAt(i);
        } 
      } 
      if (paramBoolean)
        try {
          ActivityManager.getService().unstableProviderDied(providerRefCount.holder.connection);
        } catch (RemoteException remoteException) {} 
    } 
  }
  
  public void installSystemApplicationInfo(ApplicationInfo paramApplicationInfo, ClassLoader paramClassLoader) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual getSystemContext : ()Landroid/app/ContextImpl;
    //   6: aload_1
    //   7: aload_2
    //   8: invokevirtual installSystemApplicationInfo : (Landroid/content/pm/ApplicationInfo;Ljava/lang/ClassLoader;)V
    //   11: aload_0
    //   12: invokevirtual getSystemUiContext : ()Landroid/app/ContextImpl;
    //   15: aload_1
    //   16: aload_2
    //   17: invokevirtual installSystemApplicationInfo : (Landroid/content/pm/ApplicationInfo;Ljava/lang/ClassLoader;)V
    //   20: new android/app/ActivityThread$Profiler
    //   23: astore_1
    //   24: aload_1
    //   25: invokespecial <init> : ()V
    //   28: aload_0
    //   29: aload_1
    //   30: putfield mProfiler : Landroid/app/ActivityThread$Profiler;
    //   33: aload_0
    //   34: monitorexit
    //   35: return
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    // Exception table:
    //   from	to	target	type
    //   2	35	36	finally
    //   37	39	36	finally
  }
  
  public final void installSystemProviders(List<ProviderInfo> paramList) {
    if (paramList != null)
      installContentProviders((Context)this.mInitialApplication, paramList); 
  }
  
  public boolean isProfiling() {
    boolean bool;
    Profiler profiler = this.mProfiler;
    if (profiler != null && profiler.profileFile != null && this.mProfiler.profileFd == null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void onNewActivityOptions(IBinder paramIBinder, ActivityOptions paramActivityOptions) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord != null)
      activityClientRecord.activity.onNewActivityOptions(paramActivityOptions); 
  }
  
  public final LoadedApk peekPackageInfo(String paramString, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: iload_2
    //   8: ifeq -> 26
    //   11: aload_0
    //   12: getfield mPackages : Landroid/util/ArrayMap;
    //   15: aload_1
    //   16: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   19: checkcast java/lang/ref/WeakReference
    //   22: astore_1
    //   23: goto -> 38
    //   26: aload_0
    //   27: getfield mResourcePackages : Landroid/util/ArrayMap;
    //   30: aload_1
    //   31: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   34: checkcast java/lang/ref/WeakReference
    //   37: astore_1
    //   38: aload_1
    //   39: ifnull -> 53
    //   42: aload_1
    //   43: invokevirtual get : ()Ljava/lang/Object;
    //   46: checkcast android/app/LoadedApk
    //   49: astore_1
    //   50: goto -> 55
    //   53: aconst_null
    //   54: astore_1
    //   55: aload_3
    //   56: monitorexit
    //   57: aload_1
    //   58: areturn
    //   59: astore_1
    //   60: aload_3
    //   61: monitorexit
    //   62: aload_1
    //   63: athrow
    // Exception table:
    //   from	to	target	type
    //   11	23	59	finally
    //   26	38	59	finally
    //   42	50	59	finally
    //   55	57	59	finally
    //   60	62	59	finally
  }
  
  ActivityClientRecord performDestroyActivity(IBinder paramIBinder, boolean paramBoolean1, int paramInt, boolean paramBoolean2, String paramString) {
    Class<?> clazz;
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    paramString = null;
    if (activityClientRecord != null) {
      clazz = activityClientRecord.activity.getClass();
      Activity activity = activityClientRecord.activity;
      activity.mConfigChangeFlags |= paramInt;
      if (paramBoolean1)
        activityClientRecord.activity.mFinished = true; 
      performPauseActivityIfNeeded(activityClientRecord, "destroy");
      if (!activityClientRecord.stopped)
        callActivityOnStop(activityClientRecord, false, "destroy"); 
      if (paramBoolean2)
        try {
          activityClientRecord.lastNonConfigurationInstances = activityClientRecord.activity.retainNonConfigurationInstances();
        } catch (Exception exception) {
          if (!this.mInstrumentation.onException(activityClientRecord.activity, exception)) {
            null = new StringBuilder();
            null.append("Unable to retain activity ");
            null.append(activityClientRecord.intent.getComponent().toShortString());
            null.append(": ");
            null.append(exception.toString());
            throw new RuntimeException(null.toString(), exception);
          } 
        }  
      try {
        activityClientRecord.activity.mCalled = false;
        this.mInstrumentation.callActivityOnDestroy(activityClientRecord.activity);
        if (activityClientRecord.activity.mCalled) {
          if (activityClientRecord.window != null)
            activityClientRecord.window.closeAllPanels(); 
        } else {
          SuperNotCalledException superNotCalledException = new SuperNotCalledException();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Activity ");
          stringBuilder.append(safeToComponentShortString(activityClientRecord.intent));
          stringBuilder.append(" did not call through to super.onDestroy()");
          this(stringBuilder.toString());
          throw superNotCalledException;
        } 
        activityClientRecord.setState(6);
        this.mLastReportedWindowingMode.remove(activityClientRecord.activity.getActivityToken());
      } catch (SuperNotCalledException superNotCalledException) {
        throw superNotCalledException;
      } catch (Exception exception) {
        if (!this.mInstrumentation.onException(activityClientRecord.activity, exception)) {
          null = new StringBuilder();
          null.append("Unable to destroy activity ");
          null.append(safeToComponentShortString(activityClientRecord.intent));
          null.append(": ");
          null.append(exception.toString());
          throw new RuntimeException(null.toString(), exception);
        } 
        activityClientRecord.setState(6);
        this.mLastReportedWindowingMode.remove(activityClientRecord.activity.getActivityToken());
      } 
    } 
    schedulePurgeIdler();
    synchronized (this.mResourcesManager) {
      this.mActivities.remove(null);
      StrictMode.decrementExpectedActivityCount(clazz);
      return activityClientRecord;
    } 
  }
  
  final Bundle performPauseActivity(IBinder paramIBinder, boolean paramBoolean, String paramString, PendingTransactionActions paramPendingTransactionActions) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord != null) {
      Bundle bundle = performPauseActivity(activityClientRecord, paramBoolean, paramString, paramPendingTransactionActions);
    } else {
      activityClientRecord = null;
    } 
    return (Bundle)activityClientRecord;
  }
  
  public void performRestartActivity(IBinder paramIBinder, boolean paramBoolean) {
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord.stopped) {
      activityClientRecord.activity.performRestart(paramBoolean, "performRestartActivity");
      if (paramBoolean)
        activityClientRecord.setState(2); 
    } 
  }
  
  public ActivityClientRecord performResumeActivity(IBinder paramIBinder, boolean paramBoolean, String paramString) {
    IllegalStateException illegalStateException;
    ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
    if (activityClientRecord == null || activityClientRecord.activity.mFinished)
      return null; 
    if (activityClientRecord.getLifecycleState() == 3) {
      if (!paramBoolean) {
        illegalStateException = new IllegalStateException("Trying to resume activity which is already resumed");
        Slog.e("ActivityThread", illegalStateException.getMessage(), illegalStateException);
        Slog.e("ActivityThread", activityClientRecord.getStateString());
      } 
      return null;
    } 
    if (paramBoolean) {
      activityClientRecord.hideForNow = false;
      activityClientRecord.activity.mStartedActivity = false;
    } 
    try {
      activityClientRecord.activity.onStateNotSaved();
      activityClientRecord.activity.mFragments.noteStateNotSaved();
      checkAndBlockForNetworkAccess();
      if (activityClientRecord.pendingIntents != null) {
        deliverNewIntents(activityClientRecord, activityClientRecord.pendingIntents);
        activityClientRecord.pendingIntents = null;
      } 
      if (activityClientRecord.pendingResults != null) {
        deliverResults(activityClientRecord, activityClientRecord.pendingResults, (String)illegalStateException);
        activityClientRecord.pendingResults = null;
      } 
      activityClientRecord.activity.performResume(activityClientRecord.startsNotResumed, (String)illegalStateException);
      activityClientRecord.state = null;
      activityClientRecord.persistentState = null;
      activityClientRecord.setState(3);
      reportTopResumedActivityChanged(activityClientRecord, activityClientRecord.isTopResumedActivity, "topWhenResuming");
    } catch (Exception exception) {
      if (!this.mInstrumentation.onException(activityClientRecord.activity, exception)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to resume activity ");
        stringBuilder.append(activityClientRecord.intent.getComponent().toShortString());
        stringBuilder.append(": ");
        stringBuilder.append(exception.toString());
        throw new RuntimeException(stringBuilder.toString(), exception);
      } 
    } 
    return activityClientRecord;
  }
  
  final void performStopActivity(IBinder paramIBinder, boolean paramBoolean, String paramString) {
    performStopActivityInner((ActivityClientRecord)this.mActivities.get(paramIBinder), (PendingTransactionActions.StopInfo)null, paramBoolean, false, paramString);
  }
  
  final void performUserLeavingActivity(ActivityClientRecord paramActivityClientRecord) {
    this.mInstrumentation.callActivityOnPictureInPictureRequested(paramActivityClientRecord.activity);
    this.mInstrumentation.callActivityOnUserLeaving(paramActivityClientRecord.activity);
  }
  
  public ActivityClientRecord prepareRelaunchActivity(IBinder paramIBinder, List<ResultInfo> paramList, List<ReferrerIntent> paramList1, int paramInt, MergedConfiguration paramMergedConfiguration, boolean paramBoolean) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #7
    //   3: iconst_0
    //   4: istore #8
    //   6: aload_0
    //   7: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   10: astore #9
    //   12: aload #9
    //   14: monitorenter
    //   15: iconst_0
    //   16: istore #10
    //   18: aload #7
    //   20: astore #11
    //   22: iload #10
    //   24: aload_0
    //   25: getfield mRelaunchingActivities : Ljava/util/ArrayList;
    //   28: invokevirtual size : ()I
    //   31: if_icmpge -> 148
    //   34: aload_0
    //   35: getfield mRelaunchingActivities : Ljava/util/ArrayList;
    //   38: iload #10
    //   40: invokevirtual get : (I)Ljava/lang/Object;
    //   43: checkcast android/app/ActivityThread$ActivityClientRecord
    //   46: astore #12
    //   48: aload #12
    //   50: getfield token : Landroid/os/IBinder;
    //   53: aload_1
    //   54: if_acmpne -> 142
    //   57: aload #12
    //   59: astore #7
    //   61: aload_2
    //   62: ifnull -> 94
    //   65: aload #12
    //   67: getfield pendingResults : Ljava/util/List;
    //   70: ifnull -> 88
    //   73: aload #12
    //   75: getfield pendingResults : Ljava/util/List;
    //   78: aload_2
    //   79: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   84: pop
    //   85: goto -> 94
    //   88: aload #12
    //   90: aload_2
    //   91: putfield pendingResults : Ljava/util/List;
    //   94: aload #7
    //   96: astore #11
    //   98: aload_3
    //   99: ifnull -> 148
    //   102: aload #12
    //   104: getfield pendingIntents : Ljava/util/List;
    //   107: ifnull -> 129
    //   110: aload #12
    //   112: getfield pendingIntents : Ljava/util/List;
    //   115: aload_3
    //   116: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   121: pop
    //   122: aload #7
    //   124: astore #11
    //   126: goto -> 148
    //   129: aload #12
    //   131: aload_3
    //   132: putfield pendingIntents : Ljava/util/List;
    //   135: aload #7
    //   137: astore #11
    //   139: goto -> 148
    //   142: iinc #10, 1
    //   145: goto -> 18
    //   148: aload #11
    //   150: astore #7
    //   152: iload #8
    //   154: istore #10
    //   156: aload #11
    //   158: ifnonnull -> 209
    //   161: new android/app/ActivityThread$ActivityClientRecord
    //   164: astore #7
    //   166: aload #7
    //   168: invokespecial <init> : ()V
    //   171: aload #7
    //   173: aload_1
    //   174: putfield token : Landroid/os/IBinder;
    //   177: aload #7
    //   179: aload_2
    //   180: putfield pendingResults : Ljava/util/List;
    //   183: aload #7
    //   185: aload_3
    //   186: putfield pendingIntents : Ljava/util/List;
    //   189: aload #7
    //   191: iload #6
    //   193: putfield mPreserveWindow : Z
    //   196: aload_0
    //   197: getfield mRelaunchingActivities : Ljava/util/ArrayList;
    //   200: aload #7
    //   202: invokevirtual add : (Ljava/lang/Object;)Z
    //   205: pop
    //   206: iconst_1
    //   207: istore #10
    //   209: aload #7
    //   211: aload #5
    //   213: invokevirtual getGlobalConfiguration : ()Landroid/content/res/Configuration;
    //   216: putfield createdConfig : Landroid/content/res/Configuration;
    //   219: aload #7
    //   221: aload #5
    //   223: invokevirtual getOverrideConfiguration : ()Landroid/content/res/Configuration;
    //   226: putfield overrideConfig : Landroid/content/res/Configuration;
    //   229: aload #7
    //   231: aload #7
    //   233: getfield pendingConfigChanges : I
    //   236: iload #4
    //   238: ior
    //   239: putfield pendingConfigChanges : I
    //   242: aload #9
    //   244: monitorexit
    //   245: iload #10
    //   247: ifeq -> 256
    //   250: aload #7
    //   252: astore_1
    //   253: goto -> 258
    //   256: aconst_null
    //   257: astore_1
    //   258: aload_1
    //   259: areturn
    //   260: astore_1
    //   261: aload #9
    //   263: monitorexit
    //   264: aload_1
    //   265: athrow
    // Exception table:
    //   from	to	target	type
    //   22	57	260	finally
    //   65	85	260	finally
    //   88	94	260	finally
    //   102	122	260	finally
    //   129	135	260	finally
    //   161	171	260	finally
    //   171	206	260	finally
    //   209	245	260	finally
    //   261	264	260	finally
  }
  
  public void registerOnActivityPausedListener(Activity paramActivity, OnActivityPausedListener paramOnActivityPausedListener) {
    synchronized (this.mOnPauseListeners) {
      ArrayList<OnActivityPausedListener> arrayList1 = (ArrayList)this.mOnPauseListeners.get(paramActivity);
      ArrayList<OnActivityPausedListener> arrayList2 = arrayList1;
      if (arrayList1 == null) {
        arrayList2 = new ArrayList();
        this();
        this.mOnPauseListeners.put(paramActivity, arrayList2);
      } 
      arrayList2.add(paramOnActivityPausedListener);
      return;
    } 
  }
  
  public final boolean releaseProvider(IContentProvider paramIContentProvider, boolean paramBoolean) {
    int i = 0;
    if (paramIContentProvider == null)
      return false; 
    null = paramIContentProvider.asBinder();
    synchronized (this.mProviderMap) {
      ProviderRefCount providerRefCount = (ProviderRefCount)this.mProviderRefCountMap.get(null);
      if (providerRefCount == null)
        return false; 
      int j = 0;
      if (paramBoolean) {
        if (providerRefCount.stableCount == 0)
          return false; 
        providerRefCount.stableCount--;
        if (providerRefCount.stableCount == 0) {
          j = providerRefCount.unstableCount;
          if (j == 0) {
            j = 1;
          } else {
            j = 0;
          } 
          try {
            IActivityManager iActivityManager = ActivityManager.getService();
            IBinder iBinder = providerRefCount.holder.connection;
            if (j != 0)
              i = 1; 
            iActivityManager.refContentProvider(iBinder, -1, i);
          } catch (RemoteException remoteException) {}
        } 
      } else {
        if (providerRefCount.unstableCount == 0)
          return false; 
        providerRefCount.unstableCount--;
        if (providerRefCount.unstableCount == 0) {
          j = providerRefCount.stableCount;
          if (j == 0) {
            j = 1;
          } else {
            j = 0;
          } 
          i = j;
          j = i;
          if (i == 0)
            try {
              ActivityManager.getService().refContentProvider(providerRefCount.holder.connection, 0, -1);
              j = i;
            } catch (RemoteException remoteException) {
              j = i;
            }  
        } 
      } 
      if (j != 0) {
        Message message;
        if (!providerRefCount.removePending) {
          providerRefCount.removePending = true;
          message = this.mH.obtainMessage(131, providerRefCount);
          this.mH.sendMessageDelayed(message, 1000L);
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Duplicate remove pending of provider ");
          stringBuilder.append(((ProviderRefCount)message).holder.info.name);
          Slog.w("ActivityThread", stringBuilder.toString());
        } 
      } 
      return true;
    } 
  }
  
  public void reportRelaunch(IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    try {
      ActivityTaskManager.getService().activityRelaunched(paramIBinder);
      ActivityClientRecord activityClientRecord = (ActivityClientRecord)this.mActivities.get(paramIBinder);
      if (paramPendingTransactionActions.shouldReportRelaunchToWindowManager() && activityClientRecord != null && activityClientRecord.window != null)
        activityClientRecord.window.reportActivityRelaunched(); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void reportStop(PendingTransactionActions paramPendingTransactionActions) {
    this.mH.post((Runnable)paramPendingTransactionActions.getStopInfo());
  }
  
  public final ActivityInfo resolveActivityInfo(Intent paramIntent) {
    ActivityInfo activityInfo = paramIntent.resolveActivityInfo(this.mInitialApplication.getPackageManager(), 1024);
    if (activityInfo == null)
      Instrumentation.checkStartActivityResult(-92, paramIntent); 
    return activityInfo;
  }
  
  final void scheduleContextCleanup(ContextImpl paramContextImpl, String paramString1, String paramString2) {
    ContextCleanupInfo contextCleanupInfo = new ContextCleanupInfo();
    contextCleanupInfo.context = paramContextImpl;
    contextCleanupInfo.who = paramString1;
    contextCleanupInfo.what = paramString2;
    sendMessage(119, contextCleanupInfo);
  }
  
  void scheduleGcIdler() {
    if (!this.mGcIdlerScheduled) {
      this.mGcIdlerScheduled = true;
      Looper.myQueue().addIdleHandler(this.mGcIdler);
    } 
    this.mH.removeMessages(120);
  }
  
  void schedulePurgeIdler() {
    if (!this.mPurgeIdlerScheduled) {
      this.mPurgeIdlerScheduled = true;
      Looper.myQueue().addIdleHandler(this.mPurgeIdler);
    } 
    this.mH.removeMessages(161);
  }
  
  void scheduleRelaunchActivity(IBinder paramIBinder) {
    this.mH.removeMessages(160, paramIBinder);
    sendMessage(160, paramIBinder);
  }
  
  public final void sendActivityResult(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, Intent paramIntent) {
    ArrayList<ResultInfo> arrayList = new ArrayList();
    arrayList.add(new ResultInfo(paramString, paramInt1, paramInt2, paramIntent));
    ClientTransaction clientTransaction = ClientTransaction.obtain(this.mAppThread, paramIBinder);
    clientTransaction.addCallback((ClientTransactionItem)ActivityResultItem.obtain(arrayList));
    try {
      this.mAppThread.scheduleTransaction(clientTransaction);
    } catch (RemoteException remoteException) {}
  }
  
  void sendMessage(int paramInt, Object paramObject) {
    sendMessage(paramInt, paramObject, 0, 0, false);
  }
  
  public final Activity startActivityNow(Activity paramActivity, String paramString, Intent paramIntent, ActivityInfo paramActivityInfo, IBinder paramIBinder1, Bundle paramBundle, Activity.NonConfigurationInstances paramNonConfigurationInstances, IBinder paramIBinder2) {
    ActivityClientRecord activityClientRecord = new ActivityClientRecord();
    activityClientRecord.token = paramIBinder1;
    activityClientRecord.assistToken = paramIBinder2;
    activityClientRecord.ident = 0;
    activityClientRecord.intent = paramIntent;
    activityClientRecord.state = paramBundle;
    activityClientRecord.parent = paramActivity;
    activityClientRecord.embeddedID = paramString;
    activityClientRecord.activityInfo = paramActivityInfo;
    activityClientRecord.lastNonConfigurationInstances = paramNonConfigurationInstances;
    return performLaunchActivity(activityClientRecord, (Intent)null);
  }
  
  public void stopProfiling() {
    Profiler profiler = this.mProfiler;
    if (profiler != null)
      profiler.stopProfiling(); 
  }
  
  public void unregisterOnActivityPausedListener(Activity paramActivity, OnActivityPausedListener paramOnActivityPausedListener) {
    synchronized (this.mOnPauseListeners) {
      ArrayList arrayList = (ArrayList)this.mOnPauseListeners.get(paramActivity);
      if (arrayList != null)
        arrayList.remove(paramOnActivityPausedListener); 
      return;
    } 
  }
  
  void unscheduleGcIdler() {
    if (this.mGcIdlerScheduled) {
      this.mGcIdlerScheduled = false;
      Looper.myQueue().removeIdleHandler(this.mGcIdler);
    } 
    this.mH.removeMessages(120);
  }
  
  void unschedulePurgeIdler() {
    if (this.mPurgeIdlerScheduled) {
      this.mPurgeIdlerScheduled = false;
      Looper.myQueue().removeIdleHandler(this.mPurgeIdler);
    } 
    this.mH.removeMessages(161);
  }
  
  public void updatePendingActivityConfiguration(IBinder paramIBinder, Configuration paramConfiguration) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mResourcesManager : Landroid/app/ResourcesManager;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield mActivities : Landroid/util/ArrayMap;
    //   11: aload_1
    //   12: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   15: checkcast android/app/ActivityThread$ActivityClientRecord
    //   18: astore_1
    //   19: aload_3
    //   20: monitorexit
    //   21: aload_1
    //   22: ifnonnull -> 26
    //   25: return
    //   26: aload_1
    //   27: monitorenter
    //   28: aload_1
    //   29: invokestatic access$4000 : (Landroid/app/ActivityThread$ActivityClientRecord;)Landroid/content/res/Configuration;
    //   32: ifnull -> 49
    //   35: aload_1
    //   36: invokestatic access$4000 : (Landroid/app/ActivityThread$ActivityClientRecord;)Landroid/content/res/Configuration;
    //   39: aload_2
    //   40: invokevirtual isOtherSeqNewer : (Landroid/content/res/Configuration;)Z
    //   43: ifne -> 49
    //   46: aload_1
    //   47: monitorexit
    //   48: return
    //   49: aload_1
    //   50: aload_2
    //   51: invokestatic access$4002 : (Landroid/app/ActivityThread$ActivityClientRecord;Landroid/content/res/Configuration;)Landroid/content/res/Configuration;
    //   54: pop
    //   55: aload_1
    //   56: monitorexit
    //   57: return
    //   58: astore_2
    //   59: aload_1
    //   60: monitorexit
    //   61: aload_2
    //   62: athrow
    //   63: astore_1
    //   64: aload_3
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   7	21	63	finally
    //   28	48	58	finally
    //   49	57	58	finally
    //   59	61	58	finally
    //   64	66	63	finally
  }
  
  public void updatePendingConfiguration(Configuration paramConfiguration) {
    synchronized (this.mResourcesManager) {
      if (this.mPendingConfiguration == null || this.mPendingConfiguration.isOtherSeqNewer(paramConfiguration))
        this.mPendingConfiguration = paramConfiguration; 
      return;
    } 
  }
  
  public void updateProcessState(int paramInt, boolean paramBoolean) {
    synchronized (this.mAppThread) {
      if (this.mLastProcessState == paramInt)
        return; 
      this.mLastProcessState = paramInt;
      if (paramInt == 2 && this.mNumLaunchingActivities.get() > 0) {
        this.mPendingProcessState = paramInt;
        H h = this.mH;
        _$$Lambda$ActivityThread$A4ykhsPb8qV3ffTqpQDklHSMDJ0 _$$Lambda$ActivityThread$A4ykhsPb8qV3ffTqpQDklHSMDJ0 = new _$$Lambda$ActivityThread$A4ykhsPb8qV3ffTqpQDklHSMDJ0();
        this(this);
        h.postDelayed(_$$Lambda$ActivityThread$A4ykhsPb8qV3ffTqpQDklHSMDJ0, 1000L);
      } else {
        this.mPendingProcessState = -1;
        updateVmProcessState(paramInt);
      } 
      return;
    } 
  }
  
  public static final class ActivityClientRecord {
    Activity activity;
    
    ActivityInfo activityInfo;
    
    public IBinder assistToken;
    
    CompatibilityInfo compatInfo;
    
    ViewRootImpl.ActivityConfigCallback configCallback;
    
    Configuration createdConfig;
    
    String embeddedID;
    
    boolean hideForNow;
    
    int ident;
    
    Intent intent;
    
    public final boolean isForward;
    
    boolean isTopResumedActivity;
    
    Activity.NonConfigurationInstances lastNonConfigurationInstances;
    
    boolean lastReportedTopResumedState;
    
    boolean mIsUserLeaving;
    
    private int mLifecycleState = 0;
    
    DisplayAdjustments.FixedRotationAdjustments mPendingFixedRotationAdjustments;
    
    private Configuration mPendingOverrideConfig;
    
    Window mPendingRemoveWindow;
    
    WindowManager mPendingRemoveWindowManager;
    
    boolean mPreserveWindow;
    
    Configuration newConfig;
    
    ActivityClientRecord nextIdle;
    
    Configuration overrideConfig;
    
    public LoadedApk packageInfo;
    
    Activity parent;
    
    boolean paused;
    
    int pendingConfigChanges;
    
    List<ReferrerIntent> pendingIntents;
    
    List<ResultInfo> pendingResults;
    
    PersistableBundle persistentState;
    
    ProfilerInfo profilerInfo;
    
    String referrer;
    
    boolean startsNotResumed;
    
    Bundle state;
    
    boolean stopped;
    
    private Configuration tmpConfig = new Configuration();
    
    public IBinder token;
    
    IVoiceInteractor voiceInteractor;
    
    Window window;
    
    public ActivityClientRecord() {
      this.isForward = false;
      init();
    }
    
    public ActivityClientRecord(IBinder param1IBinder1, Intent param1Intent, int param1Int, ActivityInfo param1ActivityInfo, Configuration param1Configuration, CompatibilityInfo param1CompatibilityInfo, String param1String, IVoiceInteractor param1IVoiceInteractor, Bundle param1Bundle, PersistableBundle param1PersistableBundle, List<ResultInfo> param1List, List<ReferrerIntent> param1List1, boolean param1Boolean, ProfilerInfo param1ProfilerInfo, ClientTransactionHandler param1ClientTransactionHandler, IBinder param1IBinder2, DisplayAdjustments.FixedRotationAdjustments param1FixedRotationAdjustments) {
      this.token = param1IBinder1;
      this.assistToken = param1IBinder2;
      this.ident = param1Int;
      this.intent = param1Intent;
      this.referrer = param1String;
      this.voiceInteractor = param1IVoiceInteractor;
      this.activityInfo = param1ActivityInfo;
      this.compatInfo = param1CompatibilityInfo;
      this.state = param1Bundle;
      this.persistentState = param1PersistableBundle;
      this.pendingResults = param1List;
      this.pendingIntents = param1List1;
      this.isForward = param1Boolean;
      this.profilerInfo = param1ProfilerInfo;
      this.overrideConfig = param1Configuration;
      this.packageInfo = param1ClientTransactionHandler.getPackageInfoNoCheck(param1ActivityInfo.applicationInfo, param1CompatibilityInfo);
      this.mPendingFixedRotationAdjustments = param1FixedRotationAdjustments;
      init();
    }
    
    private void init() {
      this.parent = null;
      this.embeddedID = null;
      this.paused = false;
      this.stopped = false;
      this.hideForNow = false;
      this.nextIdle = null;
      this.configCallback = new _$$Lambda$ActivityThread$ActivityClientRecord$HOrG1qglSjSUHSjKBn2rXtX0gGg(this);
    }
    
    private boolean isPreHoneycomb() {
      boolean bool;
      Activity activity = this.activity;
      if (activity != null && (activity.getApplicationInfo()).targetSdkVersion < 11) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private boolean isPreP() {
      boolean bool;
      Activity activity = this.activity;
      if (activity != null && (activity.getApplicationInfo()).targetSdkVersion < 28) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getLifecycleState() {
      return this.mLifecycleState;
    }
    
    public String getStateString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ActivityClientRecord{");
      stringBuilder.append("paused=");
      stringBuilder.append(this.paused);
      stringBuilder.append(", stopped=");
      stringBuilder.append(this.stopped);
      stringBuilder.append(", hideForNow=");
      stringBuilder.append(this.hideForNow);
      stringBuilder.append(", startsNotResumed=");
      stringBuilder.append(this.startsNotResumed);
      stringBuilder.append(", isForward=");
      stringBuilder.append(this.isForward);
      stringBuilder.append(", pendingConfigChanges=");
      stringBuilder.append(this.pendingConfigChanges);
      stringBuilder.append(", preserveWindow=");
      stringBuilder.append(this.mPreserveWindow);
      if (this.activity != null) {
        stringBuilder.append(", Activity{");
        stringBuilder.append("resumed=");
        stringBuilder.append(this.activity.mResumed);
        stringBuilder.append(", stopped=");
        stringBuilder.append(this.activity.mStopped);
        stringBuilder.append(", finished=");
        stringBuilder.append(this.activity.isFinishing());
        stringBuilder.append(", destroyed=");
        stringBuilder.append(this.activity.isDestroyed());
        stringBuilder.append(", startedActivity=");
        stringBuilder.append(this.activity.mStartedActivity);
        stringBuilder.append(", changingConfigurations=");
        stringBuilder.append(this.activity.mChangingConfigurations);
        stringBuilder.append("}");
      } 
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public boolean isPersistable() {
      boolean bool;
      if (this.activityInfo.persistableMode == 2) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isVisibleFromServer() {
      boolean bool;
      Activity activity = this.activity;
      if (activity != null && activity.mVisibleFromServer) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void setState(int param1Int) {
      this.mLifecycleState = param1Int;
      if (param1Int != 1) {
        if (param1Int != 2) {
          if (param1Int != 3) {
            if (param1Int != 4) {
              if (param1Int == 5) {
                this.paused = true;
                this.stopped = true;
              } 
            } else {
              this.paused = true;
              this.stopped = false;
            } 
          } else {
            this.paused = false;
            this.stopped = false;
          } 
        } else {
          this.paused = true;
          this.stopped = false;
        } 
      } else {
        this.paused = true;
        this.stopped = true;
      } 
    }
    
    public String toString() {
      String str;
      Intent intent = this.intent;
      if (intent != null) {
        ComponentName componentName = intent.getComponent();
      } else {
        intent = null;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ActivityRecord{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" token=");
      stringBuilder.append(this.token);
      stringBuilder.append(" ");
      if (intent == null) {
        str = "no component name";
      } else {
        str = str.toShortString();
      } 
      stringBuilder.append(str);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
  }
  
  private static class AndroidOs extends ForwardingOs {
    private AndroidOs(Os param1Os) {
      super(param1Os);
    }
    
    private void deleteDeprecatedDataPath(String param1String) throws ErrnoException {
      Uri uri = ContentResolver.translateDeprecatedDataPath(param1String);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Redirecting ");
      stringBuilder.append(param1String);
      stringBuilder.append(" to ");
      stringBuilder.append(uri);
      Log.v("ActivityThread", stringBuilder.toString());
      ContentResolver contentResolver = ActivityThread.currentActivityThread().getApplication().getContentResolver();
      try {
        if (contentResolver.delete(uri, null, null) != 0)
          return; 
        FileNotFoundException fileNotFoundException = new FileNotFoundException();
        this();
        throw fileNotFoundException;
      } catch (SecurityException securityException) {
        throw new ErrnoException(securityException.getMessage(), OsConstants.EACCES);
      } catch (FileNotFoundException fileNotFoundException) {
        throw new ErrnoException(fileNotFoundException.getMessage(), OsConstants.ENOENT);
      } 
    }
    
    public static void install() {
      Os os;
      if (!ContentResolver.DEPRECATE_DATA_COLUMNS)
        return; 
      do {
        os = Os.getDefault();
      } while (!Os.compareAndSetDefault(os, (Os)new AndroidOs(os)));
    }
    
    private FileDescriptor openDeprecatedDataPath(String param1String, int param1Int) throws ErrnoException {
      Uri uri = ContentResolver.translateDeprecatedDataPath(param1String);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Redirecting ");
      stringBuilder.append(param1String);
      stringBuilder.append(" to ");
      stringBuilder.append(uri);
      Log.v("ActivityThread", stringBuilder.toString());
      ContentResolver contentResolver = ActivityThread.currentActivityThread().getApplication().getContentResolver();
      try {
        FileDescriptor fileDescriptor = new FileDescriptor();
        this();
        fileDescriptor.setInt$(contentResolver.openFileDescriptor(uri, FileUtils.translateModePosixToString(param1Int)).detachFd());
        return fileDescriptor;
      } catch (SecurityException securityException) {
        throw new ErrnoException(securityException.getMessage(), OsConstants.EACCES);
      } catch (FileNotFoundException fileNotFoundException) {
        throw new ErrnoException(fileNotFoundException.getMessage(), OsConstants.ENOENT);
      } 
    }
    
    public boolean access(String param1String, int param1Int) throws ErrnoException {
      if (param1String != null && param1String.startsWith("/mnt/content/")) {
        IoUtils.closeQuietly(openDeprecatedDataPath(param1String, FileUtils.translateModeAccessToPosix(param1Int)));
        return true;
      } 
      return super.access(param1String, param1Int);
    }
    
    public FileDescriptor open(String param1String, int param1Int1, int param1Int2) throws ErrnoException {
      return (param1String != null && param1String.startsWith("/mnt/content/")) ? openDeprecatedDataPath(param1String, param1Int2) : super.open(param1String, param1Int1, param1Int2);
    }
    
    public void remove(String param1String) throws ErrnoException {
      if (param1String != null && param1String.startsWith("/mnt/content/")) {
        deleteDeprecatedDataPath(param1String);
      } else {
        super.remove(param1String);
      } 
    }
    
    public void rename(String param1String1, String param1String2) throws ErrnoException {
      try {
        super.rename(param1String1, param1String2);
      } catch (ErrnoException errnoException) {
        if (errnoException.errno == OsConstants.EXDEV && param1String1.startsWith("/storage/emulated") && param1String2.startsWith("/storage/emulated")) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Recovering failed rename ");
          stringBuilder.append(param1String1);
          stringBuilder.append(" to ");
          stringBuilder.append(param1String2);
          Log.v("ActivityThread", stringBuilder.toString());
          try {
            File file = new File();
            this(param1String1);
            Path path = file.toPath();
            file = new File();
            this(param1String2);
            Files.move(path, file.toPath(), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
            return;
          } catch (IOException iOException) {
            Log.e("ActivityThread", "Rename recovery failed ", (Throwable)errnoException);
            throw errnoException;
          } 
        } 
      } 
    }
    
    public StructStat stat(String param1String) throws ErrnoException {
      FileDescriptor fileDescriptor;
      if (param1String != null && param1String.startsWith("/mnt/content/")) {
        fileDescriptor = openDeprecatedDataPath(param1String, OsConstants.O_RDONLY);
        try {
          return Os.fstat(fileDescriptor);
        } finally {
          IoUtils.closeQuietly(fileDescriptor);
        } 
      } 
      return super.stat((String)fileDescriptor);
    }
    
    public void unlink(String param1String) throws ErrnoException {
      if (param1String != null && param1String.startsWith("/mnt/content/")) {
        deleteDeprecatedDataPath(param1String);
      } else {
        super.unlink(param1String);
      } 
    }
  }
  
  static final class AppBindData {
    ApplicationInfo appInfo;
    
    AutofillOptions autofillOptions;
    
    String buildSerial;
    
    CompatibilityInfo compatInfo;
    
    Configuration config;
    
    ContentCaptureOptions contentCaptureOptions;
    
    int debugMode;
    
    long[] disabledCompatChanges;
    
    boolean enableBinderTracking;
    
    LoadedApk info;
    
    ProfilerInfo initProfilerInfo;
    
    Bundle instrumentationArgs;
    
    ComponentName instrumentationName;
    
    IUiAutomationConnection instrumentationUiAutomationConnection;
    
    IInstrumentationWatcher instrumentationWatcher;
    
    boolean persistent;
    
    String processName;
    
    List<ProviderInfo> providers;
    
    boolean restrictedBackupMode;
    
    boolean trackAllocation;
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("AppBindData{appInfo=");
      stringBuilder.append(this.appInfo);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
  }
  
  private class ApplicationThread extends IApplicationThread.Stub {
    private static final String DB_INFO_FORMAT = "  %8s %8s %14s %14s  %s";
    
    private ApplicationThread() {}
    
    private void dumpDatabaseInfo(ParcelFileDescriptor param1ParcelFileDescriptor, String[] param1ArrayOfString, boolean param1Boolean) {
      FastPrintWriter fastPrintWriter = new FastPrintWriter(new FileOutputStream(param1ParcelFileDescriptor.getFileDescriptor()));
      SQLiteDebug.dump((Printer)new PrintWriterPrinter((PrintWriter)fastPrintWriter), param1ArrayOfString, param1Boolean);
      fastPrintWriter.flush();
    }
    
    private void dumpMemInfo(ProtoOutputStream param1ProtoOutputStream, Debug.MemoryInfo param1MemoryInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4) {
      String str2;
      long l1 = Debug.getNativeHeapSize() / 1024L;
      long l2 = Debug.getNativeHeapAllocatedSize() / 1024L;
      long l3 = Debug.getNativeHeapFreeSize() / 1024L;
      Runtime runtime = Runtime.getRuntime();
      runtime.gc();
      long l4 = runtime.totalMemory() / 1024L;
      long l5 = runtime.freeMemory() / 1024L;
      param1Boolean1 = false;
      long[] arrayOfLong = VMDebug.countInstancesOfClasses(new Class[] { ContextImpl.class, Activity.class, WebView.class, OpenSSLSocketImpl.class }, true);
      long l6 = arrayOfLong[0];
      long l7 = arrayOfLong[1];
      long l8 = arrayOfLong[2];
      long l9 = arrayOfLong[3];
      long l10 = ViewDebug.getViewInstanceCount();
      long l11 = ViewDebug.getViewRootImplCount();
      int i = AssetManager.getGlobalAssetCount();
      int j = AssetManager.getGlobalAssetManagerCount();
      int k = Debug.getBinderLocalObjectCount();
      int m = Debug.getBinderProxyObjectCount();
      int n = Debug.getBinderDeathObjectCount();
      long l12 = Parcel.getGlobalAllocSize();
      long l13 = Parcel.getGlobalAllocCount();
      SQLiteDebug.PagerStats pagerStats2 = SQLiteDebug.getDatabaseInfo();
      long l14 = param1ProtoOutputStream.start(1146756268033L);
      param1ProtoOutputStream.write(1120986464257L, Process.myPid());
      if (ActivityThread.this.mBoundApplication != null) {
        str2 = ActivityThread.this.mBoundApplication.processName;
      } else {
        str2 = "unknown";
      } 
      param1ProtoOutputStream.write(1138166333442L, str2);
      ActivityThread.dumpMemInfoTable(param1ProtoOutputStream, param1MemoryInfo, param1Boolean2, param1Boolean3, l1, l2, l3, l4, l4 - l5, l5);
      param1ProtoOutputStream.end(l14);
      l14 = param1ProtoOutputStream.start(1146756268034L);
      param1ProtoOutputStream.write(1120986464257L, l10);
      param1ProtoOutputStream.write(1120986464258L, l11);
      param1ProtoOutputStream.write(1120986464259L, l6);
      param1ProtoOutputStream.write(1120986464260L, l7);
      param1ProtoOutputStream.write(1120986464261L, i);
      param1ProtoOutputStream.write(1120986464262L, j);
      param1ProtoOutputStream.write(1120986464263L, k);
      param1ProtoOutputStream.write(1120986464264L, m);
      param1ProtoOutputStream.write(1112396529673L, l12 / 1024L);
      param1ProtoOutputStream.write(1120986464266L, l13);
      param1ProtoOutputStream.write(1120986464267L, n);
      param1ProtoOutputStream.write(1120986464268L, l9);
      param1ProtoOutputStream.write(1120986464269L, l8);
      param1ProtoOutputStream.end(l14);
      l8 = param1ProtoOutputStream.start(1146756268035L);
      SQLiteDebug.PagerStats pagerStats1 = pagerStats2;
      param1ProtoOutputStream.write(1120986464257L, pagerStats1.memoryUsed / 1024);
      param1ProtoOutputStream.write(1120986464258L, pagerStats1.pageCacheOverflow / 1024);
      param1ProtoOutputStream.write(1120986464259L, pagerStats1.largestMemAlloc / 1024);
      k = pagerStats1.dbStats.size();
      for (n = 0; n < k; n++) {
        SQLiteDebug.DbStats dbStats = pagerStats1.dbStats.get(n);
        l9 = param1ProtoOutputStream.start(2246267895812L);
        param1ProtoOutputStream.write(1138166333441L, dbStats.dbName);
        param1ProtoOutputStream.write(1120986464258L, dbStats.pageSize);
        param1ProtoOutputStream.write(1120986464259L, dbStats.dbSize);
        param1ProtoOutputStream.write(1120986464260L, dbStats.lookaside);
        param1ProtoOutputStream.write(1138166333445L, dbStats.cache);
        param1ProtoOutputStream.end(l9);
      } 
      param1ProtoOutputStream.end(l8);
      String str1 = AssetManager.getAssetAllocations();
      if (str1 != null)
        param1ProtoOutputStream.write(1138166333444L, str1); 
      if (param1Boolean4) {
        if (ActivityThread.this.mBoundApplication == null) {
          k = 0;
        } else {
          k = ActivityThread.this.mBoundApplication.appInfo.flags;
        } 
        if ((k & 0x2) != 0 || Build.IS_DEBUGGABLE)
          param1Boolean1 = true; 
        param1ProtoOutputStream.write(1138166333445L, Debug.getUnreachableMemory(100, param1Boolean1));
      } 
    }
    
    private void dumpMemInfo(PrintWriter param1PrintWriter, Debug.MemoryInfo param1MemoryInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, boolean param1Boolean5) {
      // Byte code:
      //   0: invokestatic getNativeHeapSize : ()J
      //   3: ldc2_w 1024
      //   6: ldiv
      //   7: lstore #8
      //   9: invokestatic getNativeHeapAllocatedSize : ()J
      //   12: ldc2_w 1024
      //   15: ldiv
      //   16: lstore #10
      //   18: invokestatic getNativeHeapFreeSize : ()J
      //   21: ldc2_w 1024
      //   24: ldiv
      //   25: lstore #12
      //   27: invokestatic getRuntime : ()Ljava/lang/Runtime;
      //   30: astore #14
      //   32: aload #14
      //   34: invokevirtual gc : ()V
      //   37: aload #14
      //   39: invokevirtual totalMemory : ()J
      //   42: ldc2_w 1024
      //   45: ldiv
      //   46: lstore #15
      //   48: aload #14
      //   50: invokevirtual freeMemory : ()J
      //   53: ldc2_w 1024
      //   56: ldiv
      //   57: lstore #17
      //   59: iconst_0
      //   60: istore #19
      //   62: iconst_4
      //   63: anewarray java/lang/Class
      //   66: dup
      //   67: iconst_0
      //   68: ldc android/app/ContextImpl
      //   70: aastore
      //   71: dup
      //   72: iconst_1
      //   73: ldc android/app/Activity
      //   75: aastore
      //   76: dup
      //   77: iconst_2
      //   78: ldc android/webkit/WebView
      //   80: aastore
      //   81: dup
      //   82: iconst_3
      //   83: ldc com/android/org/conscrypt/OpenSSLSocketImpl
      //   85: aastore
      //   86: iconst_1
      //   87: invokestatic countInstancesOfClasses : ([Ljava/lang/Class;Z)[J
      //   90: astore #14
      //   92: aload #14
      //   94: iconst_0
      //   95: laload
      //   96: lstore #20
      //   98: aload #14
      //   100: iconst_1
      //   101: laload
      //   102: lstore #22
      //   104: aload #14
      //   106: iconst_2
      //   107: laload
      //   108: lstore #24
      //   110: aload #14
      //   112: iconst_3
      //   113: laload
      //   114: lstore #26
      //   116: invokestatic getViewInstanceCount : ()J
      //   119: lstore #28
      //   121: invokestatic getViewRootImplCount : ()J
      //   124: lstore #30
      //   126: invokestatic getGlobalAssetCount : ()I
      //   129: istore #32
      //   131: invokestatic getGlobalAssetManagerCount : ()I
      //   134: istore #33
      //   136: invokestatic getBinderLocalObjectCount : ()I
      //   139: istore #34
      //   141: invokestatic getBinderProxyObjectCount : ()I
      //   144: istore #35
      //   146: invokestatic getBinderDeathObjectCount : ()I
      //   149: istore #36
      //   151: invokestatic getGlobalAllocSize : ()J
      //   154: lstore #37
      //   156: invokestatic getGlobalAllocCount : ()J
      //   159: lstore #39
      //   161: invokestatic getDatabaseInfo : ()Landroid/database/sqlite/SQLiteDebug$PagerStats;
      //   164: astore #41
      //   166: invokestatic myPid : ()I
      //   169: istore #42
      //   171: aload_0
      //   172: getfield this$0 : Landroid/app/ActivityThread;
      //   175: getfield mBoundApplication : Landroid/app/ActivityThread$AppBindData;
      //   178: ifnull -> 196
      //   181: aload_0
      //   182: getfield this$0 : Landroid/app/ActivityThread;
      //   185: getfield mBoundApplication : Landroid/app/ActivityThread$AppBindData;
      //   188: getfield processName : Ljava/lang/String;
      //   191: astore #14
      //   193: goto -> 200
      //   196: ldc 'unknown'
      //   198: astore #14
      //   200: aload_1
      //   201: aload_2
      //   202: iload_3
      //   203: iload #4
      //   205: iload #5
      //   207: iload #6
      //   209: iload #42
      //   211: aload #14
      //   213: lload #8
      //   215: lload #10
      //   217: lload #12
      //   219: lload #15
      //   221: lload #15
      //   223: lload #17
      //   225: lsub
      //   226: lload #17
      //   228: invokestatic dumpMemInfoTable : (Ljava/io/PrintWriter;Landroid/os/Debug$MemoryInfo;ZZZZILjava/lang/String;JJJJJJ)V
      //   231: iload_3
      //   232: ifeq -> 554
      //   235: aload_1
      //   236: lload #28
      //   238: invokevirtual print : (J)V
      //   241: aload_1
      //   242: bipush #44
      //   244: invokevirtual print : (C)V
      //   247: aload_1
      //   248: lload #30
      //   250: invokevirtual print : (J)V
      //   253: aload_1
      //   254: bipush #44
      //   256: invokevirtual print : (C)V
      //   259: aload_1
      //   260: lload #20
      //   262: invokevirtual print : (J)V
      //   265: aload_1
      //   266: bipush #44
      //   268: invokevirtual print : (C)V
      //   271: aload_1
      //   272: lload #22
      //   274: invokevirtual print : (J)V
      //   277: aload_1
      //   278: bipush #44
      //   280: invokevirtual print : (C)V
      //   283: aload_1
      //   284: iload #32
      //   286: invokevirtual print : (I)V
      //   289: aload_1
      //   290: bipush #44
      //   292: invokevirtual print : (C)V
      //   295: aload_1
      //   296: iload #33
      //   298: invokevirtual print : (I)V
      //   301: aload_1
      //   302: bipush #44
      //   304: invokevirtual print : (C)V
      //   307: aload_1
      //   308: iload #34
      //   310: invokevirtual print : (I)V
      //   313: aload_1
      //   314: bipush #44
      //   316: invokevirtual print : (C)V
      //   319: aload_1
      //   320: iload #35
      //   322: invokevirtual print : (I)V
      //   325: aload_1
      //   326: bipush #44
      //   328: invokevirtual print : (C)V
      //   331: aload_1
      //   332: iload #36
      //   334: invokevirtual print : (I)V
      //   337: aload_1
      //   338: bipush #44
      //   340: invokevirtual print : (C)V
      //   343: aload_1
      //   344: lload #26
      //   346: invokevirtual print : (J)V
      //   349: aload_1
      //   350: bipush #44
      //   352: invokevirtual print : (C)V
      //   355: aload #41
      //   357: astore_2
      //   358: aload_1
      //   359: aload_2
      //   360: getfield memoryUsed : I
      //   363: sipush #1024
      //   366: idiv
      //   367: invokevirtual print : (I)V
      //   370: aload_1
      //   371: bipush #44
      //   373: invokevirtual print : (C)V
      //   376: aload_1
      //   377: aload_2
      //   378: getfield memoryUsed : I
      //   381: sipush #1024
      //   384: idiv
      //   385: invokevirtual print : (I)V
      //   388: aload_1
      //   389: bipush #44
      //   391: invokevirtual print : (C)V
      //   394: aload_1
      //   395: aload_2
      //   396: getfield pageCacheOverflow : I
      //   399: sipush #1024
      //   402: idiv
      //   403: invokevirtual print : (I)V
      //   406: aload_1
      //   407: bipush #44
      //   409: invokevirtual print : (C)V
      //   412: aload_1
      //   413: aload_2
      //   414: getfield largestMemAlloc : I
      //   417: sipush #1024
      //   420: idiv
      //   421: invokevirtual print : (I)V
      //   424: iconst_0
      //   425: istore #35
      //   427: iload #35
      //   429: aload_2
      //   430: getfield dbStats : Ljava/util/ArrayList;
      //   433: invokevirtual size : ()I
      //   436: if_icmpge -> 549
      //   439: aload_2
      //   440: getfield dbStats : Ljava/util/ArrayList;
      //   443: iload #35
      //   445: invokevirtual get : (I)Ljava/lang/Object;
      //   448: checkcast android/database/sqlite/SQLiteDebug$DbStats
      //   451: astore #14
      //   453: aload_1
      //   454: bipush #44
      //   456: invokevirtual print : (C)V
      //   459: aload_1
      //   460: aload #14
      //   462: getfield dbName : Ljava/lang/String;
      //   465: invokevirtual print : (Ljava/lang/String;)V
      //   468: aload_1
      //   469: bipush #44
      //   471: invokevirtual print : (C)V
      //   474: aload_1
      //   475: aload #14
      //   477: getfield pageSize : J
      //   480: invokevirtual print : (J)V
      //   483: aload_1
      //   484: bipush #44
      //   486: invokevirtual print : (C)V
      //   489: aload_1
      //   490: aload #14
      //   492: getfield dbSize : J
      //   495: invokevirtual print : (J)V
      //   498: aload_1
      //   499: bipush #44
      //   501: invokevirtual print : (C)V
      //   504: aload_1
      //   505: aload #14
      //   507: getfield lookaside : I
      //   510: invokevirtual print : (I)V
      //   513: aload_1
      //   514: bipush #44
      //   516: invokevirtual print : (C)V
      //   519: aload_1
      //   520: aload #14
      //   522: getfield cache : Ljava/lang/String;
      //   525: invokevirtual print : (Ljava/lang/String;)V
      //   528: aload_1
      //   529: bipush #44
      //   531: invokevirtual print : (C)V
      //   534: aload_1
      //   535: aload #14
      //   537: getfield cache : Ljava/lang/String;
      //   540: invokevirtual print : (Ljava/lang/String;)V
      //   543: iinc #35, 1
      //   546: goto -> 427
      //   549: aload_1
      //   550: invokevirtual println : ()V
      //   553: return
      //   554: ldc_w ' '
      //   557: astore_2
      //   558: aload_1
      //   559: ldc_w ' '
      //   562: invokevirtual println : (Ljava/lang/String;)V
      //   565: aload_1
      //   566: ldc_w ' Objects'
      //   569: invokevirtual println : (Ljava/lang/String;)V
      //   572: aload_1
      //   573: ldc_w '%21s %8d %21s %8d'
      //   576: iconst_4
      //   577: anewarray java/lang/Object
      //   580: dup
      //   581: iconst_0
      //   582: ldc_w 'Views:'
      //   585: aastore
      //   586: dup
      //   587: iconst_1
      //   588: lload #28
      //   590: invokestatic valueOf : (J)Ljava/lang/Long;
      //   593: aastore
      //   594: dup
      //   595: iconst_2
      //   596: ldc_w 'ViewRootImpl:'
      //   599: aastore
      //   600: dup
      //   601: iconst_3
      //   602: lload #30
      //   604: invokestatic valueOf : (J)Ljava/lang/Long;
      //   607: aastore
      //   608: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
      //   611: aload_1
      //   612: ldc_w '%21s %8d %21s %8d'
      //   615: iconst_4
      //   616: anewarray java/lang/Object
      //   619: dup
      //   620: iconst_0
      //   621: ldc_w 'AppContexts:'
      //   624: aastore
      //   625: dup
      //   626: iconst_1
      //   627: lload #20
      //   629: invokestatic valueOf : (J)Ljava/lang/Long;
      //   632: aastore
      //   633: dup
      //   634: iconst_2
      //   635: ldc_w 'Activities:'
      //   638: aastore
      //   639: dup
      //   640: iconst_3
      //   641: lload #22
      //   643: invokestatic valueOf : (J)Ljava/lang/Long;
      //   646: aastore
      //   647: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
      //   650: aload_1
      //   651: ldc_w '%21s %8d %21s %8d'
      //   654: iconst_4
      //   655: anewarray java/lang/Object
      //   658: dup
      //   659: iconst_0
      //   660: ldc_w 'Assets:'
      //   663: aastore
      //   664: dup
      //   665: iconst_1
      //   666: iload #32
      //   668: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   671: aastore
      //   672: dup
      //   673: iconst_2
      //   674: ldc_w 'AssetManagers:'
      //   677: aastore
      //   678: dup
      //   679: iconst_3
      //   680: iload #33
      //   682: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   685: aastore
      //   686: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
      //   689: aload_1
      //   690: ldc_w '%21s %8d %21s %8d'
      //   693: iconst_4
      //   694: anewarray java/lang/Object
      //   697: dup
      //   698: iconst_0
      //   699: ldc_w 'Local Binders:'
      //   702: aastore
      //   703: dup
      //   704: iconst_1
      //   705: iload #34
      //   707: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   710: aastore
      //   711: dup
      //   712: iconst_2
      //   713: ldc_w 'Proxy Binders:'
      //   716: aastore
      //   717: dup
      //   718: iconst_3
      //   719: iload #35
      //   721: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   724: aastore
      //   725: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
      //   728: aload_1
      //   729: ldc_w '%21s %8d %21s %8d'
      //   732: iconst_4
      //   733: anewarray java/lang/Object
      //   736: dup
      //   737: iconst_0
      //   738: ldc_w 'Parcel memory:'
      //   741: aastore
      //   742: dup
      //   743: iconst_1
      //   744: lload #37
      //   746: ldc2_w 1024
      //   749: ldiv
      //   750: invokestatic valueOf : (J)Ljava/lang/Long;
      //   753: aastore
      //   754: dup
      //   755: iconst_2
      //   756: ldc_w 'Parcel count:'
      //   759: aastore
      //   760: dup
      //   761: iconst_3
      //   762: lload #39
      //   764: invokestatic valueOf : (J)Ljava/lang/Long;
      //   767: aastore
      //   768: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
      //   771: aload_1
      //   772: ldc_w '%21s %8d %21s %8d'
      //   775: iconst_4
      //   776: anewarray java/lang/Object
      //   779: dup
      //   780: iconst_0
      //   781: ldc_w 'Death Recipients:'
      //   784: aastore
      //   785: dup
      //   786: iconst_1
      //   787: iload #36
      //   789: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   792: aastore
      //   793: dup
      //   794: iconst_2
      //   795: ldc_w 'OpenSSL Sockets:'
      //   798: aastore
      //   799: dup
      //   800: iconst_3
      //   801: lload #26
      //   803: invokestatic valueOf : (J)Ljava/lang/Long;
      //   806: aastore
      //   807: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
      //   810: aload_1
      //   811: ldc_w '%21s %8d'
      //   814: iconst_2
      //   815: anewarray java/lang/Object
      //   818: dup
      //   819: iconst_0
      //   820: ldc_w 'WebViews:'
      //   823: aastore
      //   824: dup
      //   825: iconst_1
      //   826: lload #24
      //   828: invokestatic valueOf : (J)Ljava/lang/Long;
      //   831: aastore
      //   832: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
      //   835: aload_1
      //   836: ldc_w ' '
      //   839: invokevirtual println : (Ljava/lang/String;)V
      //   842: aload_1
      //   843: ldc_w ' SQL'
      //   846: invokevirtual println : (Ljava/lang/String;)V
      //   849: aload_1
      //   850: ldc_w '%21s %8d'
      //   853: iconst_2
      //   854: anewarray java/lang/Object
      //   857: dup
      //   858: iconst_0
      //   859: ldc_w 'MEMORY_USED:'
      //   862: aastore
      //   863: dup
      //   864: iconst_1
      //   865: aload #41
      //   867: getfield memoryUsed : I
      //   870: sipush #1024
      //   873: idiv
      //   874: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   877: aastore
      //   878: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
      //   881: aload_1
      //   882: ldc_w '%21s %8d %21s %8d'
      //   885: iconst_4
      //   886: anewarray java/lang/Object
      //   889: dup
      //   890: iconst_0
      //   891: ldc_w 'PAGECACHE_OVERFLOW:'
      //   894: aastore
      //   895: dup
      //   896: iconst_1
      //   897: aload #41
      //   899: getfield pageCacheOverflow : I
      //   902: sipush #1024
      //   905: idiv
      //   906: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   909: aastore
      //   910: dup
      //   911: iconst_2
      //   912: ldc_w 'MALLOC_SIZE:'
      //   915: aastore
      //   916: dup
      //   917: iconst_3
      //   918: aload #41
      //   920: getfield largestMemAlloc : I
      //   923: sipush #1024
      //   926: idiv
      //   927: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   930: aastore
      //   931: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
      //   934: aload_1
      //   935: ldc_w ' '
      //   938: invokevirtual println : (Ljava/lang/String;)V
      //   941: aload #41
      //   943: getfield dbStats : Ljava/util/ArrayList;
      //   946: invokevirtual size : ()I
      //   949: istore #35
      //   951: iload #35
      //   953: ifle -> 1154
      //   956: aload_1
      //   957: ldc_w ' DATABASES'
      //   960: invokevirtual println : (Ljava/lang/String;)V
      //   963: aload_1
      //   964: ldc '  %8s %8s %14s %14s  %s'
      //   966: iconst_5
      //   967: anewarray java/lang/Object
      //   970: dup
      //   971: iconst_0
      //   972: ldc_w 'pgsz'
      //   975: aastore
      //   976: dup
      //   977: iconst_1
      //   978: ldc_w 'dbsz'
      //   981: aastore
      //   982: dup
      //   983: iconst_2
      //   984: ldc_w 'Lookaside(b)'
      //   987: aastore
      //   988: dup
      //   989: iconst_3
      //   990: ldc_w 'cache'
      //   993: aastore
      //   994: dup
      //   995: iconst_4
      //   996: ldc_w 'Dbname'
      //   999: aastore
      //   1000: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
      //   1003: iconst_0
      //   1004: istore #36
      //   1006: iload #36
      //   1008: iload #35
      //   1010: if_icmpge -> 1151
      //   1013: aload #41
      //   1015: getfield dbStats : Ljava/util/ArrayList;
      //   1018: iload #36
      //   1020: invokevirtual get : (I)Ljava/lang/Object;
      //   1023: checkcast android/database/sqlite/SQLiteDebug$DbStats
      //   1026: astore #43
      //   1028: aload #43
      //   1030: getfield pageSize : J
      //   1033: lconst_0
      //   1034: lcmp
      //   1035: ifle -> 1051
      //   1038: aload #43
      //   1040: getfield pageSize : J
      //   1043: invokestatic valueOf : (J)Ljava/lang/String;
      //   1046: astore #14
      //   1048: goto -> 1054
      //   1051: aload_2
      //   1052: astore #14
      //   1054: aload #43
      //   1056: getfield dbSize : J
      //   1059: lconst_0
      //   1060: lcmp
      //   1061: ifle -> 1077
      //   1064: aload #43
      //   1066: getfield dbSize : J
      //   1069: invokestatic valueOf : (J)Ljava/lang/String;
      //   1072: astore #44
      //   1074: goto -> 1080
      //   1077: aload_2
      //   1078: astore #44
      //   1080: aload #43
      //   1082: getfield lookaside : I
      //   1085: ifle -> 1101
      //   1088: aload #43
      //   1090: getfield lookaside : I
      //   1093: invokestatic valueOf : (I)Ljava/lang/String;
      //   1096: astore #45
      //   1098: goto -> 1104
      //   1101: aload_2
      //   1102: astore #45
      //   1104: aload_1
      //   1105: ldc '  %8s %8s %14s %14s  %s'
      //   1107: iconst_5
      //   1108: anewarray java/lang/Object
      //   1111: dup
      //   1112: iconst_0
      //   1113: aload #14
      //   1115: aastore
      //   1116: dup
      //   1117: iconst_1
      //   1118: aload #44
      //   1120: aastore
      //   1121: dup
      //   1122: iconst_2
      //   1123: aload #45
      //   1125: aastore
      //   1126: dup
      //   1127: iconst_3
      //   1128: aload #43
      //   1130: getfield cache : Ljava/lang/String;
      //   1133: aastore
      //   1134: dup
      //   1135: iconst_4
      //   1136: aload #43
      //   1138: getfield dbName : Ljava/lang/String;
      //   1141: aastore
      //   1142: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
      //   1145: iinc #36, 1
      //   1148: goto -> 1006
      //   1151: goto -> 1158
      //   1154: ldc_w ' '
      //   1157: astore_2
      //   1158: invokestatic getAssetAllocations : ()Ljava/lang/String;
      //   1161: astore #14
      //   1163: aload #14
      //   1165: ifnull -> 1189
      //   1168: aload_1
      //   1169: aload_2
      //   1170: invokevirtual println : (Ljava/lang/String;)V
      //   1173: aload_1
      //   1174: ldc_w ' Asset Allocations'
      //   1177: invokevirtual println : (Ljava/lang/String;)V
      //   1180: aload_1
      //   1181: aload #14
      //   1183: invokevirtual print : (Ljava/lang/String;)V
      //   1186: goto -> 1189
      //   1189: iload #7
      //   1191: ifeq -> 1258
      //   1194: aload_0
      //   1195: getfield this$0 : Landroid/app/ActivityThread;
      //   1198: getfield mBoundApplication : Landroid/app/ActivityThread$AppBindData;
      //   1201: ifnull -> 1222
      //   1204: iconst_2
      //   1205: aload_0
      //   1206: getfield this$0 : Landroid/app/ActivityThread;
      //   1209: getfield mBoundApplication : Landroid/app/ActivityThread$AppBindData;
      //   1212: getfield appInfo : Landroid/content/pm/ApplicationInfo;
      //   1215: getfield flags : I
      //   1218: iand
      //   1219: ifne -> 1231
      //   1222: iload #19
      //   1224: istore_3
      //   1225: getstatic android/os/Build.IS_DEBUGGABLE : Z
      //   1228: ifeq -> 1233
      //   1231: iconst_1
      //   1232: istore_3
      //   1233: aload_1
      //   1234: aload_2
      //   1235: invokevirtual println : (Ljava/lang/String;)V
      //   1238: aload_1
      //   1239: ldc_w ' Unreachable memory'
      //   1242: invokevirtual println : (Ljava/lang/String;)V
      //   1245: aload_1
      //   1246: bipush #100
      //   1248: iload_3
      //   1249: invokestatic getUnreachableMemory : (IZ)Ljava/lang/String;
      //   1252: invokevirtual print : (Ljava/lang/String;)V
      //   1255: goto -> 1258
      //   1258: return
    }
    
    private File getDatabasesDir(Context param1Context) {
      return param1Context.getDatabasePath("a").getParentFile();
    }
    
    public void attachAgent(String param1String) {
      ActivityThread.this.sendMessage(155, param1String);
    }
    
    public void attachStartupAgents(String param1String) {
      ActivityThread.this.sendMessage(162, param1String);
    }
    
    public final void bindApplication(String param1String1, ApplicationInfo param1ApplicationInfo, ProviderInfoList param1ProviderInfoList, ComponentName param1ComponentName, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle1, IInstrumentationWatcher param1IInstrumentationWatcher, IUiAutomationConnection param1IUiAutomationConnection, int param1Int, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, Configuration param1Configuration, CompatibilityInfo param1CompatibilityInfo, Map param1Map, Bundle param1Bundle2, String param1String2, AutofillOptions param1AutofillOptions, ContentCaptureOptions param1ContentCaptureOptions, long[] param1ArrayOflong) {
      if (param1Map != null)
        ServiceManager.initServiceCache(param1Map); 
      setCoreSettings(param1Bundle2);
      ActivityThread.AppBindData appBindData = new ActivityThread.AppBindData();
      appBindData.processName = param1String1;
      appBindData.appInfo = param1ApplicationInfo;
      appBindData.providers = param1ProviderInfoList.getList();
      appBindData.instrumentationName = param1ComponentName;
      appBindData.instrumentationArgs = param1Bundle1;
      appBindData.instrumentationWatcher = param1IInstrumentationWatcher;
      appBindData.instrumentationUiAutomationConnection = param1IUiAutomationConnection;
      appBindData.debugMode = param1Int;
      appBindData.enableBinderTracking = param1Boolean1;
      appBindData.trackAllocation = param1Boolean2;
      appBindData.restrictedBackupMode = param1Boolean3;
      appBindData.persistent = param1Boolean4;
      appBindData.config = param1Configuration;
      appBindData.compatInfo = param1CompatibilityInfo;
      appBindData.initProfilerInfo = param1ProfilerInfo;
      appBindData.buildSerial = param1String2;
      appBindData.autofillOptions = param1AutofillOptions;
      appBindData.contentCaptureOptions = param1ContentCaptureOptions;
      appBindData.disabledCompatChanges = param1ArrayOflong;
      ActivityThread.this.sendMessage(110, appBindData);
    }
    
    public void clearDnsCache() {
      InetAddress.clearDnsCache();
      NetworkEventDispatcher.getInstance().onNetworkConfigurationChanged();
    }
    
    public void dispatchPackageBroadcast(int param1Int, String[] param1ArrayOfString) {
      ActivityThread.this.sendMessage(133, param1ArrayOfString, param1Int);
    }
    
    public void dumpActivity(ParcelFileDescriptor param1ParcelFileDescriptor, IBinder param1IBinder, String param1String, String[] param1ArrayOfString) {
      ActivityThread.DumpComponentInfo dumpComponentInfo = new ActivityThread.DumpComponentInfo();
      try {
        dumpComponentInfo.fd = param1ParcelFileDescriptor.dup();
        dumpComponentInfo.token = param1IBinder;
        dumpComponentInfo.prefix = param1String;
        dumpComponentInfo.args = param1ArrayOfString;
        ActivityThread.this.sendMessage(136, dumpComponentInfo, 0, 0, true);
      } catch (IOException iOException) {
        Slog.w("ActivityThread", "dumpActivity failed", iOException);
      } finally {}
      IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
    }
    
    public void dumpCacheInfo(ParcelFileDescriptor param1ParcelFileDescriptor, String[] param1ArrayOfString) {
      PropertyInvalidatedCache.dumpCacheInfo(param1ParcelFileDescriptor.getFileDescriptor(), param1ArrayOfString);
      IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
    }
    
    public void dumpDbInfo(ParcelFileDescriptor param1ParcelFileDescriptor, final String[] args) {
      if (ActivityThread.this.mSystemThread) {
        try {
          final ParcelFileDescriptor dup = param1ParcelFileDescriptor.dup();
          IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
          AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
                public void run() {
                  try {
                    ActivityThread.ApplicationThread.this.dumpDatabaseInfo(dup, args, true);
                    return;
                  } finally {
                    IoUtils.closeQuietly((AutoCloseable)dup);
                  } 
                }
              });
        } catch (IOException iOException) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Could not dup FD ");
          stringBuilder.append(param1ParcelFileDescriptor.getFileDescriptor().getInt$());
          Log.w("ActivityThread", stringBuilder.toString());
          IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
          return;
        } finally {}
      } else {
        dumpDatabaseInfo(param1ParcelFileDescriptor, args, false);
        IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
      } 
    }
    
    public void dumpGfxInfo(ParcelFileDescriptor param1ParcelFileDescriptor, String[] param1ArrayOfString) {
      ActivityThread.this.nDumpGraphicsInfo(param1ParcelFileDescriptor.getFileDescriptor());
      WindowManagerGlobal.getInstance().dumpGfxInfo(param1ParcelFileDescriptor.getFileDescriptor(), param1ArrayOfString);
      IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
    }
    
    public void dumpHeap(boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, String param1String, ParcelFileDescriptor param1ParcelFileDescriptor, RemoteCallback param1RemoteCallback) {
      ActivityThread.DumpHeapData dumpHeapData = new ActivityThread.DumpHeapData();
      dumpHeapData.managed = param1Boolean1;
      dumpHeapData.mallocInfo = param1Boolean2;
      dumpHeapData.runGc = param1Boolean3;
      dumpHeapData.path = param1String;
      try {
        dumpHeapData.fd = param1ParcelFileDescriptor.dup();
        IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
        dumpHeapData.finishCallback = param1RemoteCallback;
        ActivityThread.this.sendMessage(135, dumpHeapData, 0, 0, true);
        return;
      } catch (IOException iOException) {
        Slog.e("ActivityThread", "Failed to duplicate heap dump file descriptor", iOException);
        IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
        return;
      } finally {}
      IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
      throw param1String;
    }
    
    public void dumpMemInfo(ParcelFileDescriptor param1ParcelFileDescriptor, Debug.MemoryInfo param1MemoryInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, boolean param1Boolean5, String[] param1ArrayOfString) {
      FastPrintWriter fastPrintWriter = new FastPrintWriter(new FileOutputStream(param1ParcelFileDescriptor.getFileDescriptor()));
      try {
        dumpMemInfo((PrintWriter)fastPrintWriter, param1MemoryInfo, param1Boolean1, param1Boolean2, param1Boolean3, param1Boolean4, param1Boolean5);
        return;
      } finally {
        fastPrintWriter.flush();
        IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
      } 
    }
    
    public void dumpMemInfoProto(ParcelFileDescriptor param1ParcelFileDescriptor, Debug.MemoryInfo param1MemoryInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, String[] param1ArrayOfString) {
      ProtoOutputStream protoOutputStream = new ProtoOutputStream(param1ParcelFileDescriptor.getFileDescriptor());
      try {
        dumpMemInfo(protoOutputStream, param1MemoryInfo, param1Boolean1, param1Boolean2, param1Boolean3, param1Boolean4);
        return;
      } finally {
        protoOutputStream.flush();
        IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
      } 
    }
    
    public void dumpProvider(ParcelFileDescriptor param1ParcelFileDescriptor, IBinder param1IBinder, String[] param1ArrayOfString) {
      ActivityThread.DumpComponentInfo dumpComponentInfo = new ActivityThread.DumpComponentInfo();
      try {
        dumpComponentInfo.fd = param1ParcelFileDescriptor.dup();
        dumpComponentInfo.token = param1IBinder;
        dumpComponentInfo.args = param1ArrayOfString;
        ActivityThread.this.sendMessage(141, dumpComponentInfo, 0, 0, true);
      } catch (IOException iOException) {
        Slog.w("ActivityThread", "dumpProvider failed", iOException);
      } finally {}
      IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
    }
    
    public void dumpService(ParcelFileDescriptor param1ParcelFileDescriptor, IBinder param1IBinder, String[] param1ArrayOfString) {
      ActivityThread.DumpComponentInfo dumpComponentInfo = new ActivityThread.DumpComponentInfo();
      try {
        dumpComponentInfo.fd = param1ParcelFileDescriptor.dup();
        dumpComponentInfo.token = param1IBinder;
        dumpComponentInfo.args = param1ArrayOfString;
        ActivityThread.this.sendMessage(123, dumpComponentInfo, 0, 0, true);
      } catch (IOException iOException) {
        Slog.w("ActivityThread", "dumpService failed", iOException);
      } finally {}
      IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
    }
    
    public void handleTrustStorageUpdate() {
      NetworkSecurityPolicy.getInstance().handleTrustStorageUpdate();
    }
    
    public void notifyCleartextNetwork(byte[] param1ArrayOfbyte) {
      if (StrictMode.vmCleartextNetworkEnabled())
        StrictMode.onCleartextNetworkDetected(param1ArrayOfbyte); 
    }
    
    public void performDirectAction(IBinder param1IBinder, String param1String, Bundle param1Bundle, RemoteCallback param1RemoteCallback1, RemoteCallback param1RemoteCallback2) {
      CancellationSignal cancellationSignal = new CancellationSignal();
      if (param1RemoteCallback1 != null) {
        ActivityThread.SafeCancellationTransport safeCancellationTransport = ActivityThread.this.createSafeCancellationTransport(cancellationSignal);
        Bundle bundle = new Bundle();
        bundle.putBinder("key_cancellation_signal", safeCancellationTransport.asBinder());
        param1RemoteCallback1.sendResult(bundle);
      } 
      ActivityThread.this.mH.sendMessage(PooledLambda.obtainMessage((HexConsumer)_$$Lambda$ActivityThread$ApplicationThread$nBC_BR7B9W6ftKAxur3BC53SJYc.INSTANCE, ActivityThread.this, param1IBinder, param1String, param1Bundle, cancellationSignal, param1RemoteCallback2));
    }
    
    public void processInBackground() {
      ActivityThread.this.mH.removeMessages(120);
      ActivityThread.this.mH.sendMessage(ActivityThread.this.mH.obtainMessage(120));
    }
    
    public void profilerControl(boolean param1Boolean, ProfilerInfo param1ProfilerInfo, int param1Int) {
      ActivityThread.this.sendMessage(127, param1ProfilerInfo, param1Boolean, param1Int);
    }
    
    public void requestAssistContextExtras(IBinder param1IBinder1, IBinder param1IBinder2, int param1Int1, int param1Int2, int param1Int3) {
      ActivityThread.RequestAssistContextExtras requestAssistContextExtras = new ActivityThread.RequestAssistContextExtras();
      requestAssistContextExtras.activityToken = param1IBinder1;
      requestAssistContextExtras.requestToken = param1IBinder2;
      requestAssistContextExtras.requestType = param1Int1;
      requestAssistContextExtras.sessionId = param1Int2;
      requestAssistContextExtras.flags = param1Int3;
      ActivityThread.this.sendMessage(143, requestAssistContextExtras);
    }
    
    public void requestDirectActions(IBinder param1IBinder, IVoiceInteractor param1IVoiceInteractor, RemoteCallback param1RemoteCallback1, RemoteCallback param1RemoteCallback2) {
      CancellationSignal cancellationSignal = new CancellationSignal();
      if (param1RemoteCallback1 != null) {
        ActivityThread.SafeCancellationTransport safeCancellationTransport = ActivityThread.this.createSafeCancellationTransport(cancellationSignal);
        Bundle bundle = new Bundle();
        bundle.putBinder("key_cancellation_signal", safeCancellationTransport.asBinder());
        param1RemoteCallback1.sendResult(bundle);
      } 
      ActivityThread.this.mH.sendMessage(PooledLambda.obtainMessage((QuintConsumer)_$$Lambda$ActivityThread$ApplicationThread$uR_ee_5oPoxu4U_by7wU55jwtdU.INSTANCE, ActivityThread.this, param1IBinder, param1IVoiceInteractor, cancellationSignal, param1RemoteCallback2));
    }
    
    public final void runIsolatedEntryPoint(String param1String, String[] param1ArrayOfString) {
      SomeArgs someArgs = SomeArgs.obtain();
      someArgs.arg1 = param1String;
      someArgs.arg2 = param1ArrayOfString;
      ActivityThread.this.sendMessage(158, someArgs);
    }
    
    public void scheduleApplicationInfoChanged(ApplicationInfo param1ApplicationInfo) {
      ActivityThread.this.mH.removeMessages(156, param1ApplicationInfo);
      ActivityThread.this.sendMessage(156, param1ApplicationInfo);
    }
    
    public final void scheduleBindService(IBinder param1IBinder, Intent param1Intent, boolean param1Boolean, int param1Int) {
      ActivityThread.this.updateProcessState(param1Int, false);
      ActivityThread.BindServiceData bindServiceData = new ActivityThread.BindServiceData();
      bindServiceData.token = param1IBinder;
      bindServiceData.intent = param1Intent;
      bindServiceData.rebind = param1Boolean;
      ActivityThread.this.sendMessage(121, bindServiceData);
    }
    
    public void scheduleCrash(String param1String) {
      ActivityThread.this.sendMessage(134, param1String);
    }
    
    public final void scheduleCreateBackupAgent(ApplicationInfo param1ApplicationInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int1, int param1Int2) {
      ActivityThread.CreateBackupAgentData createBackupAgentData = new ActivityThread.CreateBackupAgentData();
      createBackupAgentData.appInfo = param1ApplicationInfo;
      createBackupAgentData.compatInfo = param1CompatibilityInfo;
      createBackupAgentData.backupMode = param1Int1;
      createBackupAgentData.userId = param1Int2;
      ActivityThread.this.sendMessage(128, createBackupAgentData);
    }
    
    public final void scheduleCreateService(IBinder param1IBinder, ServiceInfo param1ServiceInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int) {
      ActivityThread.this.updateProcessState(param1Int, false);
      ActivityThread.CreateServiceData createServiceData = new ActivityThread.CreateServiceData();
      createServiceData.token = param1IBinder;
      createServiceData.info = param1ServiceInfo;
      createServiceData.compatInfo = param1CompatibilityInfo;
      ActivityThread.this.sendMessage(114, createServiceData);
    }
    
    public final void scheduleDestroyBackupAgent(ApplicationInfo param1ApplicationInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int) {
      ActivityThread.CreateBackupAgentData createBackupAgentData = new ActivityThread.CreateBackupAgentData();
      createBackupAgentData.appInfo = param1ApplicationInfo;
      createBackupAgentData.compatInfo = param1CompatibilityInfo;
      createBackupAgentData.userId = param1Int;
      ActivityThread.this.sendMessage(129, createBackupAgentData);
    }
    
    public void scheduleEnterAnimationComplete(IBinder param1IBinder) {
      ActivityThread.this.sendMessage(149, param1IBinder);
    }
    
    public final void scheduleExit() {
      ActivityThread.this.sendMessage(111, (Object)null);
    }
    
    public void scheduleInstallProvider(ProviderInfo param1ProviderInfo) {
      ActivityThread.this.sendMessage(145, param1ProviderInfo);
    }
    
    public void scheduleLocalVoiceInteractionStarted(IBinder param1IBinder, IVoiceInteractor param1IVoiceInteractor) throws RemoteException {
      SomeArgs someArgs = SomeArgs.obtain();
      someArgs.arg1 = param1IBinder;
      someArgs.arg2 = param1IVoiceInteractor;
      ActivityThread.this.sendMessage(154, someArgs);
    }
    
    public void scheduleLowMemory() {
      ActivityThread.this.sendMessage(124, (Object)null);
    }
    
    public void scheduleOnNewActivityOptions(IBinder param1IBinder, Bundle param1Bundle) {
      ActivityThread.this.sendMessage(146, new Pair(param1IBinder, ActivityOptions.fromBundle(param1Bundle)));
    }
    
    public final void scheduleReceiver(Intent param1Intent, ActivityInfo param1ActivityInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean, int param1Int2, int param1Int3) {
      ActivityThread.this.updateProcessState(param1Int3, false);
      ActivityThread.ReceiverData receiverData = new ActivityThread.ReceiverData(param1Intent, param1Int1, param1String, param1Bundle, param1Boolean, false, ActivityThread.this.mAppThread.asBinder(), param1Int2);
      receiverData.info = param1ActivityInfo;
      receiverData.compatInfo = param1CompatibilityInfo;
      ActivityThread.this.sendMessage(113, receiverData);
    }
    
    public void scheduleRegisteredReceiver(IIntentReceiver param1IIntentReceiver, Intent param1Intent, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean1, boolean param1Boolean2, int param1Int2, int param1Int3) throws RemoteException {
      ActivityThread.this.updateProcessState(param1Int3, false);
      param1IIntentReceiver.performReceive(param1Intent, param1Int1, param1String, param1Bundle, param1Boolean1, param1Boolean2, param1Int2);
    }
    
    public final void scheduleServiceArgs(IBinder param1IBinder, ParceledListSlice param1ParceledListSlice) {
      List<ServiceStartArgs> list = param1ParceledListSlice.getList();
      for (byte b = 0; b < list.size(); b++) {
        ServiceStartArgs serviceStartArgs = list.get(b);
        ActivityThread.ServiceArgsData serviceArgsData = new ActivityThread.ServiceArgsData();
        serviceArgsData.token = param1IBinder;
        serviceArgsData.taskRemoved = serviceStartArgs.taskRemoved;
        serviceArgsData.startId = serviceStartArgs.startId;
        serviceArgsData.flags = serviceStartArgs.flags;
        serviceArgsData.args = serviceStartArgs.args;
        ActivityThread.this.sendMessage(115, serviceArgsData);
      } 
    }
    
    public final void scheduleStopService(IBinder param1IBinder) {
      ActivityThread.this.sendMessage(116, param1IBinder);
    }
    
    public final void scheduleSuicide() {
      ActivityThread.this.sendMessage(130, (Object)null);
    }
    
    public void scheduleTransaction(ClientTransaction param1ClientTransaction) throws RemoteException {
      ActivityThread.this.scheduleTransaction(param1ClientTransaction);
    }
    
    public void scheduleTranslucentConversionComplete(IBinder param1IBinder, boolean param1Boolean) {
      ActivityThread.this.sendMessage(144, param1IBinder, param1Boolean);
    }
    
    public void scheduleTrimMemory(int param1Int) {
      PooledRunnable pooledRunnable = PooledLambda.obtainRunnable((BiConsumer)_$$Lambda$ActivityThread$ApplicationThread$tUGFX7CUhzB4Pg5wFd5yeqOnu38.INSTANCE, ActivityThread.this, Integer.valueOf(param1Int)).recycleOnUse();
      Choreographer choreographer = Choreographer.getMainThreadInstance();
      if (choreographer != null) {
        choreographer.postCallback(4, (Runnable)pooledRunnable, null);
      } else {
        ActivityThread.this.mH.post((Runnable)pooledRunnable);
      } 
    }
    
    public final void scheduleUnbindService(IBinder param1IBinder, Intent param1Intent) {
      ActivityThread.BindServiceData bindServiceData = new ActivityThread.BindServiceData();
      bindServiceData.token = param1IBinder;
      bindServiceData.intent = param1Intent;
      ActivityThread.this.sendMessage(122, bindServiceData);
    }
    
    public void setCoreSettings(Bundle param1Bundle) {
      ActivityThread.this.sendMessage(138, param1Bundle);
    }
    
    public void setNetworkBlockSeq(long param1Long) {
      synchronized (ActivityThread.this.mNetworkPolicyLock) {
        ActivityThread.access$702(ActivityThread.this, param1Long);
        return;
      } 
    }
    
    public void setProcessState(int param1Int) {
      ActivityThread.this.updateProcessState(param1Int, true);
    }
    
    public void setSchedulingGroup(int param1Int) {
      try {
        Process.setProcessGroup(Process.myPid(), param1Int);
      } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed setting process group to ");
        stringBuilder.append(param1Int);
        Slog.w("ActivityThread", stringBuilder.toString(), exception);
      } 
    }
    
    public void startBinderTracking() {
      ActivityThread.this.sendMessage(150, (Object)null);
    }
    
    public void stopBinderTrackingAndDump(ParcelFileDescriptor param1ParcelFileDescriptor) {
      try {
        ActivityThread.this.sendMessage(151, param1ParcelFileDescriptor.dup());
      } catch (IOException iOException) {
      
      } finally {
        IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor);
      } 
    }
    
    public void unstableProviderDied(IBinder param1IBinder) {
      ActivityThread.this.sendMessage(142, param1IBinder);
    }
    
    public void updateHttpProxy() {
      ContextImpl contextImpl;
      if (ActivityThread.this.getApplication() != null) {
        Application application = ActivityThread.this.getApplication();
      } else {
        contextImpl = ActivityThread.this.getSystemContext();
      } 
      ActivityThread.updateHttpProxy(contextImpl);
    }
    
    public void updatePackageCompatibilityInfo(String param1String, CompatibilityInfo param1CompatibilityInfo) {
      ActivityThread.UpdateCompatibilityData updateCompatibilityData = new ActivityThread.UpdateCompatibilityData();
      updateCompatibilityData.pkg = param1String;
      updateCompatibilityData.info = param1CompatibilityInfo;
      ActivityThread.this.sendMessage(139, updateCompatibilityData);
    }
    
    public final void updateTimePrefs(int param1Int) {
      Boolean bool;
      if (param1Int == 0) {
        bool = Boolean.FALSE;
      } else if (param1Int == 1) {
        bool = Boolean.TRUE;
      } else {
        bool = null;
      } 
      DateFormat.set24HourTimePref(bool);
    }
    
    public void updateTimeZone() {
      TimeZone.setDefault(null);
    }
  }
  
  class null implements Runnable {
    public void run() {
      try {
        this.this$1.dumpDatabaseInfo(dup, args, true);
        return;
      } finally {
        IoUtils.closeQuietly((AutoCloseable)dup);
      } 
    }
  }
  
  static final class BindServiceData {
    Intent intent;
    
    boolean rebind;
    
    IBinder token;
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("BindServiceData{token=");
      stringBuilder.append(this.token);
      stringBuilder.append(" intent=");
      stringBuilder.append(this.intent);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
  }
  
  static final class ContextCleanupInfo {
    ContextImpl context;
    
    String what;
    
    String who;
  }
  
  static final class CreateBackupAgentData {
    ApplicationInfo appInfo;
    
    int backupMode;
    
    CompatibilityInfo compatInfo;
    
    int userId;
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("CreateBackupAgentData{appInfo=");
      stringBuilder.append(this.appInfo);
      stringBuilder.append(" backupAgent=");
      stringBuilder.append(this.appInfo.backupAgentName);
      stringBuilder.append(" mode=");
      stringBuilder.append(this.backupMode);
      stringBuilder.append(" userId=");
      stringBuilder.append(this.userId);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
  }
  
  static final class CreateServiceData {
    CompatibilityInfo compatInfo;
    
    ServiceInfo info;
    
    Intent intent;
    
    IBinder token;
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("CreateServiceData{token=");
      stringBuilder.append(this.token);
      stringBuilder.append(" className=");
      stringBuilder.append(this.info.name);
      stringBuilder.append(" packageName=");
      stringBuilder.append(this.info.packageName);
      stringBuilder.append(" intent=");
      stringBuilder.append(this.intent);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
  }
  
  static final class DumpComponentInfo {
    String[] args;
    
    ParcelFileDescriptor fd;
    
    String prefix;
    
    IBinder token;
  }
  
  static final class DumpHeapData {
    ParcelFileDescriptor fd;
    
    RemoteCallback finishCallback;
    
    public boolean mallocInfo;
    
    public boolean managed;
    
    String path;
    
    public boolean runGc;
  }
  
  final class GcIdler implements MessageQueue.IdleHandler {
    public final boolean queueIdle() {
      ActivityThread.this.doGcIfNeeded();
      ActivityThread.this.purgePendingResources();
      return false;
    }
  }
  
  class H extends Handler {
    public static final int APPLICATION_INFO_CHANGED = 156;
    
    public static final int ATTACH_AGENT = 155;
    
    public static final int ATTACH_STARTUP_AGENTS = 162;
    
    public static final int BIND_APPLICATION = 110;
    
    public static final int BIND_SERVICE = 121;
    
    public static final int CLEAN_UP_CONTEXT = 119;
    
    public static final int CONFIGURATION_CHANGED = 118;
    
    public static final int CREATE_BACKUP_AGENT = 128;
    
    public static final int CREATE_SERVICE = 114;
    
    public static final int DESTROY_BACKUP_AGENT = 129;
    
    public static final int DISPATCH_PACKAGE_BROADCAST = 133;
    
    public static final int DUMP_ACTIVITY = 136;
    
    public static final int DUMP_HEAP = 135;
    
    public static final int DUMP_PROVIDER = 141;
    
    public static final int DUMP_SERVICE = 123;
    
    public static final int ENTER_ANIMATION_COMPLETE = 149;
    
    public static final int EXECUTE_TRANSACTION = 159;
    
    public static final int EXIT_APPLICATION = 111;
    
    public static final int GC_WHEN_IDLE = 120;
    
    public static final int INSTALL_PROVIDER = 145;
    
    public static final int LOCAL_VOICE_INTERACTION_STARTED = 154;
    
    public static final int LOW_MEMORY = 124;
    
    public static final int ON_NEW_ACTIVITY_OPTIONS = 146;
    
    public static final int PROFILER_CONTROL = 127;
    
    public static final int PURGE_RESOURCES = 161;
    
    public static final int RECEIVER = 113;
    
    public static final int RELAUNCH_ACTIVITY = 160;
    
    public static final int REMOVE_PROVIDER = 131;
    
    public static final int REQUEST_ASSIST_CONTEXT_EXTRAS = 143;
    
    public static final int RUN_ISOLATED_ENTRY_POINT = 158;
    
    public static final int SCHEDULE_CRASH = 134;
    
    public static final int SERVICE_ARGS = 115;
    
    public static final int SET_CORE_SETTINGS = 138;
    
    public static final int SLEEPING = 137;
    
    public static final int START_BINDER_TRACKING = 150;
    
    public static final int STOP_BINDER_TRACKING_AND_DUMP = 151;
    
    public static final int STOP_SERVICE = 116;
    
    public static final int SUICIDE = 130;
    
    public static final int TRANSLUCENT_CONVERSION_COMPLETE = 144;
    
    public static final int UNBIND_SERVICE = 122;
    
    public static final int UNSTABLE_PROVIDER_DIED = 142;
    
    public static final int UPDATE_PACKAGE_COMPATIBILITY_INFO = 139;
    
    String codeToString(int param1Int) {
      return Integer.toString(param1Int);
    }
    
    public void handleMessage(Message param1Message) {
      ClientTransaction clientTransaction;
      Application application;
      Pair pair;
      IBinder iBinder;
      ActivityThread activityThread1;
      ActivityThread.ContextCleanupInfo contextCleanupInfo;
      ActivityThread.AppBindData appBindData;
      String str;
      ActivityThread activityThread2;
      int i = param1Message.what;
      boolean bool1 = true;
      boolean bool2 = true;
      switch (i) {
        case 162:
          ActivityThread.handleAttachStartupAgents((String)param1Message.obj);
          break;
        case 161:
          ActivityThread.this.schedulePurgeIdler();
          break;
        case 160:
          ActivityThread.this.handleRelaunchActivityLocally((IBinder)param1Message.obj);
          break;
        case 159:
          clientTransaction = (ClientTransaction)param1Message.obj;
          ActivityThread.this.mTransactionExecutor.execute(clientTransaction);
          if (ActivityThread.isSystem())
            clientTransaction.recycle(); 
          break;
        case 158:
          ActivityThread.this.handleRunIsolatedEntryPoint((String)((SomeArgs)param1Message.obj).arg1, (String[])((SomeArgs)param1Message.obj).arg2);
          break;
        case 156:
          ActivityThread.this.handleApplicationInfoChanged((ApplicationInfo)param1Message.obj);
          break;
        case 155:
          application = ActivityThread.this.getApplication();
          str = (String)param1Message.obj;
          if (application != null) {
            LoadedApk loadedApk = application.mLoadedApk;
          } else {
            application = null;
          } 
          ActivityThread.handleAttachAgent(str, (LoadedApk)application);
          break;
        case 154:
          ActivityThread.this.handleLocalVoiceInteractionStarted((IBinder)((SomeArgs)param1Message.obj).arg1, (IVoiceInteractor)((SomeArgs)param1Message.obj).arg2);
          break;
        case 151:
          ActivityThread.this.handleStopBinderTrackingAndDump((ParcelFileDescriptor)param1Message.obj);
          break;
        case 150:
          ActivityThread.this.handleStartBinderTracking();
          break;
        case 149:
          ActivityThread.this.handleEnterAnimationComplete((IBinder)param1Message.obj);
          break;
        case 146:
          pair = (Pair)param1Message.obj;
          ActivityThread.this.onNewActivityOptions((IBinder)pair.first, (ActivityOptions)pair.second);
          break;
        case 145:
          ActivityThread.this.handleInstallProvider((ProviderInfo)param1Message.obj);
          break;
        case 144:
          activityThread2 = ActivityThread.this;
          iBinder = (IBinder)param1Message.obj;
          if (param1Message.arg1 != 1)
            bool2 = false; 
          activityThread2.handleTranslucentConversionComplete(iBinder, bool2);
          break;
        case 143:
          ActivityThread.this.handleRequestAssistContextExtras((ActivityThread.RequestAssistContextExtras)param1Message.obj);
          break;
        case 142:
          ActivityThread.this.handleUnstableProviderDied((IBinder)param1Message.obj, false);
          break;
        case 141:
          ActivityThread.this.handleDumpProvider((ActivityThread.DumpComponentInfo)param1Message.obj);
          break;
        case 139:
          ActivityThread.this.handleUpdatePackageCompatibilityInfo((ActivityThread.UpdateCompatibilityData)param1Message.obj);
          break;
        case 138:
          Trace.traceBegin(64L, "setCoreSettings");
          ActivityThread.this.handleSetCoreSettings((Bundle)param1Message.obj);
          Trace.traceEnd(64L);
          break;
        case 136:
          ActivityThread.this.handleDumpActivity((ActivityThread.DumpComponentInfo)param1Message.obj);
          break;
        case 135:
          ActivityThread.handleDumpHeap((ActivityThread.DumpHeapData)param1Message.obj);
          break;
        case 134:
          throw new RemoteServiceException((String)param1Message.obj);
        case 133:
          Trace.traceBegin(64L, "broadcastPackage");
          ActivityThread.this.handleDispatchPackageBroadcast(param1Message.arg1, (String[])param1Message.obj);
          Trace.traceEnd(64L);
          break;
        case 131:
          Trace.traceBegin(64L, "providerRemove");
          ActivityThread.this.completeRemoveProvider((ActivityThread.ProviderRefCount)param1Message.obj);
          Trace.traceEnd(64L);
          break;
        case 130:
          Process.killProcess(Process.myPid());
          break;
        case 129:
          Trace.traceBegin(64L, "backupDestroyAgent");
          ActivityThread.this.handleDestroyBackupAgent((ActivityThread.CreateBackupAgentData)param1Message.obj);
          Trace.traceEnd(64L);
          break;
        case 128:
          Trace.traceBegin(64L, "backupCreateAgent");
          ActivityThread.this.handleCreateBackupAgent((ActivityThread.CreateBackupAgentData)param1Message.obj);
          Trace.traceEnd(64L);
          break;
        case 127:
          activityThread1 = ActivityThread.this;
          if (param1Message.arg1 != 0) {
            bool2 = bool1;
          } else {
            bool2 = false;
          } 
          activityThread1.handleProfilerControl(bool2, (ProfilerInfo)param1Message.obj, param1Message.arg2);
          break;
        case 124:
          Trace.traceBegin(64L, "lowMemory");
          ActivityThread.this.handleLowMemory();
          Trace.traceEnd(64L);
          break;
        case 123:
          ActivityThread.this.handleDumpService((ActivityThread.DumpComponentInfo)param1Message.obj);
          break;
        case 122:
          Trace.traceBegin(64L, "serviceUnbind");
          ActivityThread.this.handleUnbindService((ActivityThread.BindServiceData)param1Message.obj);
          ActivityThread.this.schedulePurgeIdler();
          Trace.traceEnd(64L);
          break;
        case 121:
          Trace.traceBegin(64L, "serviceBind");
          ActivityThread.this.handleBindService((ActivityThread.BindServiceData)param1Message.obj);
          Trace.traceEnd(64L);
          break;
        case 120:
          ActivityThread.this.scheduleGcIdler();
          break;
        case 119:
          contextCleanupInfo = (ActivityThread.ContextCleanupInfo)param1Message.obj;
          contextCleanupInfo.context.performFinalCleanup(contextCleanupInfo.who, contextCleanupInfo.what);
          break;
        case 118:
          ActivityThread.this.handleConfigurationChanged((Configuration)param1Message.obj);
          break;
        case 116:
          Trace.traceBegin(64L, "serviceStop");
          ActivityThread.this.handleStopService((IBinder)param1Message.obj);
          ActivityThread.this.schedulePurgeIdler();
          Trace.traceEnd(64L);
          break;
        case 115:
          if (Trace.isTagEnabled(64L)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("serviceStart: ");
            stringBuilder.append(String.valueOf(param1Message.obj));
            Trace.traceBegin(64L, stringBuilder.toString());
          } 
          ActivityThread.this.handleServiceArgs((ActivityThread.ServiceArgsData)param1Message.obj);
          Trace.traceEnd(64L);
          break;
        case 114:
          if (Trace.isTagEnabled(64L)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("serviceCreate: ");
            stringBuilder.append(String.valueOf(param1Message.obj));
            Trace.traceBegin(64L, stringBuilder.toString());
          } 
          ActivityThread.this.handleCreateService((ActivityThread.CreateServiceData)param1Message.obj);
          Trace.traceEnd(64L);
          break;
        case 113:
          Trace.traceBegin(64L, "broadcastReceiveComp");
          ActivityThread.this.handleReceiver((ActivityThread.ReceiverData)param1Message.obj);
          Trace.traceEnd(64L);
          break;
        case 111:
          if (ActivityThread.this.mInitialApplication != null)
            ActivityThread.this.mInitialApplication.onTerminate(); 
          Looper.myLooper().quit();
          break;
        case 110:
          Trace.traceBegin(64L, "bindApplication");
          appBindData = (ActivityThread.AppBindData)param1Message.obj;
          ActivityThread.this.handleBindApplication(appBindData);
          Trace.traceEnd(64L);
          break;
      } 
      Object object = param1Message.obj;
      if (object instanceof SomeArgs)
        ((SomeArgs)object).recycle(); 
    }
  }
  
  private class Idler implements MessageQueue.IdleHandler {
    private Idler() {}
    
    public final boolean queueIdle() {
      ActivityThread.ActivityClientRecord activityClientRecord = ActivityThread.this.mNewActivities;
      boolean bool1 = false;
      boolean bool2 = bool1;
      if (ActivityThread.this.mBoundApplication != null) {
        bool2 = bool1;
        if (ActivityThread.this.mProfiler.profileFd != null) {
          bool2 = bool1;
          if (ActivityThread.this.mProfiler.autoStopProfiler)
            bool2 = true; 
        } 
      } 
      if (activityClientRecord != null) {
        ActivityThread.ActivityClientRecord activityClientRecord1;
        ActivityThread.this.mNewActivities = null;
        IActivityTaskManager iActivityTaskManager = ActivityTaskManager.getService();
        do {
          if (activityClientRecord.activity != null && !activityClientRecord.activity.mFinished)
            try {
              iActivityTaskManager.activityIdle(activityClientRecord.token, activityClientRecord.createdConfig, bool2);
              activityClientRecord.createdConfig = null;
            } catch (RemoteException remoteException) {
              throw remoteException.rethrowFromSystemServer();
            }  
          activityClientRecord1 = ((ActivityThread.ActivityClientRecord)remoteException).nextIdle;
          ((ActivityThread.ActivityClientRecord)remoteException).nextIdle = null;
          ActivityThread.ActivityClientRecord activityClientRecord2 = activityClientRecord1;
        } while (activityClientRecord1 != null);
      } 
      if (bool2)
        ActivityThread.this.mProfiler.stopProfiling(); 
      ActivityThread.this.applyPendingProcessState();
      return false;
    }
  }
  
  static final class Profiler {
    boolean autoStopProfiler;
    
    boolean handlingProfiling;
    
    ParcelFileDescriptor profileFd;
    
    String profileFile;
    
    boolean profiling;
    
    int samplingInterval;
    
    boolean streamingOutput;
    
    public void setProfiler(ProfilerInfo param1ProfilerInfo) {
      ParcelFileDescriptor parcelFileDescriptor1 = param1ProfilerInfo.profileFd;
      if (this.profiling) {
        if (parcelFileDescriptor1 != null)
          try {
            parcelFileDescriptor1.close();
          } catch (IOException iOException) {} 
        return;
      } 
      ParcelFileDescriptor parcelFileDescriptor2 = this.profileFd;
      if (parcelFileDescriptor2 != null)
        try {
          parcelFileDescriptor2.close();
        } catch (IOException iOException1) {} 
      this.profileFile = ((ProfilerInfo)iOException).profileFile;
      this.profileFd = parcelFileDescriptor1;
      this.samplingInterval = ((ProfilerInfo)iOException).samplingInterval;
      this.autoStopProfiler = ((ProfilerInfo)iOException).autoStopProfiler;
      this.streamingOutput = ((ProfilerInfo)iOException).streamingOutput;
    }
    
    public void startProfiling() {
      if (this.profileFd == null || this.profiling)
        return; 
      try {
        boolean bool;
        int i = SystemProperties.getInt("debug.traceview-buffer-size-mb", 8);
        String str = this.profileFile;
        FileDescriptor fileDescriptor = this.profileFd.getFileDescriptor();
        if (this.samplingInterval != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        VMDebug.startMethodTracing(str, fileDescriptor, i * 1024 * 1024, 0, bool, this.samplingInterval, this.streamingOutput);
        this.profiling = true;
      } catch (RuntimeException runtimeException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Profiling failed on path ");
        stringBuilder.append(this.profileFile);
        Slog.w("ActivityThread", stringBuilder.toString(), runtimeException);
        try {
          this.profileFd.close();
          this.profileFd = null;
        } catch (IOException iOException) {
          Slog.w("ActivityThread", "Failure closing profile fd", iOException);
        } 
      } 
    }
    
    public void stopProfiling() {
      if (this.profiling) {
        this.profiling = false;
        Debug.stopMethodTracing();
        ParcelFileDescriptor parcelFileDescriptor = this.profileFd;
        if (parcelFileDescriptor != null)
          try {
            parcelFileDescriptor.close();
          } catch (IOException iOException) {} 
        this.profileFd = null;
        this.profileFile = null;
      } 
    }
  }
  
  final class ProviderClientRecord {
    final ContentProviderHolder mHolder;
    
    final ContentProvider mLocalProvider;
    
    final String[] mNames;
    
    final IContentProvider mProvider;
    
    ProviderClientRecord(String[] param1ArrayOfString, IContentProvider param1IContentProvider, ContentProvider param1ContentProvider, ContentProviderHolder param1ContentProviderHolder) {
      this.mNames = param1ArrayOfString;
      this.mProvider = param1IContentProvider;
      this.mLocalProvider = param1ContentProvider;
      this.mHolder = param1ContentProviderHolder;
    }
  }
  
  private static final class ProviderKey {
    final String authority;
    
    final int userId;
    
    public ProviderKey(String param1String, int param1Int) {
      this.authority = param1String;
      this.userId = param1Int;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = param1Object instanceof ProviderKey;
      boolean bool1 = false;
      if (bool) {
        param1Object = param1Object;
        bool = bool1;
        if (Objects.equals(this.authority, ((ProviderKey)param1Object).authority)) {
          bool = bool1;
          if (this.userId == ((ProviderKey)param1Object).userId)
            bool = true; 
        } 
        return bool;
      } 
      return false;
    }
    
    public int hashCode() {
      boolean bool;
      String str = this.authority;
      if (str != null) {
        bool = str.hashCode();
      } else {
        bool = false;
      } 
      return bool ^ this.userId;
    }
  }
  
  private static final class ProviderRefCount {
    public final ActivityThread.ProviderClientRecord client;
    
    public final ContentProviderHolder holder;
    
    public boolean removePending;
    
    public int stableCount;
    
    public int unstableCount;
    
    ProviderRefCount(ContentProviderHolder param1ContentProviderHolder, ActivityThread.ProviderClientRecord param1ProviderClientRecord, int param1Int1, int param1Int2) {
      this.holder = param1ContentProviderHolder;
      this.client = param1ProviderClientRecord;
      this.stableCount = param1Int1;
      this.unstableCount = param1Int2;
    }
  }
  
  final class PurgeIdler implements MessageQueue.IdleHandler {
    public boolean queueIdle() {
      ActivityThread.this.purgePendingResources();
      return false;
    }
  }
  
  static final class ReceiverData extends BroadcastReceiver.PendingResult {
    CompatibilityInfo compatInfo;
    
    ActivityInfo info;
    
    Intent intent;
    
    public ReceiverData(Intent param1Intent, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean1, boolean param1Boolean2, IBinder param1IBinder, int param1Int2) {
      super(param1Int1, param1String, param1Bundle, 0, param1Boolean1, param1Boolean2, param1IBinder, param1Int2, param1Intent.getFlags());
      this.intent = param1Intent;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ReceiverData{intent=");
      stringBuilder.append(this.intent);
      stringBuilder.append(" packageName=");
      stringBuilder.append(this.info.packageName);
      stringBuilder.append(" resultCode=");
      stringBuilder.append(getResultCode());
      stringBuilder.append(" resultData=");
      stringBuilder.append(getResultData());
      stringBuilder.append(" resultExtras=");
      stringBuilder.append(getResultExtras(false));
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
  }
  
  static final class RequestAssistContextExtras {
    IBinder activityToken;
    
    int flags;
    
    IBinder requestToken;
    
    int requestType;
    
    int sessionId;
  }
  
  private static final class SafeCancellationTransport extends ICancellationSignal.Stub {
    private final WeakReference<ActivityThread> mWeakActivityThread;
    
    SafeCancellationTransport(ActivityThread param1ActivityThread, CancellationSignal param1CancellationSignal) {
      this.mWeakActivityThread = new WeakReference<>(param1ActivityThread);
    }
    
    public void cancel() {
      ActivityThread activityThread = this.mWeakActivityThread.get();
      if (activityThread != null) {
        CancellationSignal cancellationSignal = activityThread.removeSafeCancellationTransport(this);
        if (cancellationSignal != null)
          cancellationSignal.cancel(); 
      } 
    }
  }
  
  static final class ServiceArgsData {
    Intent args;
    
    int flags;
    
    int startId;
    
    boolean taskRemoved;
    
    IBinder token;
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ServiceArgsData{token=");
      stringBuilder.append(this.token);
      stringBuilder.append(" startId=");
      stringBuilder.append(this.startId);
      stringBuilder.append(" args=");
      stringBuilder.append(this.args);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
  }
  
  static final class UpdateCompatibilityData {
    CompatibilityInfo info;
    
    String pkg;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
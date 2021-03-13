package android.app;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.IIntentReceiver;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.SharedLibraryInfo;
import android.content.pm.dex.ArtManager;
import android.content.pm.split.SplitDependencyLoader;
import android.content.res.AssetManager;
import android.content.res.CompatibilityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.GraphicsEnvironment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.sysprop.VndkProperties;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import android.util.SparseArray;
import android.view.DisplayAdjustments;
import com.android.internal.util.ArrayUtils;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.VMRuntime;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;

public final class LoadedApk {
  static final boolean DEBUG = false;
  
  private static final String PROPERTY_NAME_APPEND_NATIVE = "pi.append_native_lib_paths";
  
  static final String TAG = "LoadedApk";
  
  private final ActivityThread mActivityThread;
  
  private AppComponentFactory mAppComponentFactory;
  
  private String mAppDir;
  
  private Application mApplication;
  
  private ApplicationInfo mApplicationInfo;
  
  private final ClassLoader mBaseClassLoader;
  
  private ClassLoader mClassLoader;
  
  private File mCredentialProtectedDataDirFile;
  
  private String mDataDir;
  
  private File mDataDirFile;
  
  private ClassLoader mDefaultClassLoader;
  
  private File mDeviceProtectedDataDirFile;
  
  private final DisplayAdjustments mDisplayAdjustments = new DisplayAdjustments();
  
  private final boolean mIncludeCode;
  
  private String mLibDir;
  
  private String[] mOverlayDirs;
  
  final String mPackageName;
  
  private final ArrayMap<Context, ArrayMap<BroadcastReceiver, ReceiverDispatcher>> mReceivers = new ArrayMap();
  
  private final boolean mRegisterPackage;
  
  private String mResDir;
  
  Resources mResources;
  
  private final boolean mSecurityViolation;
  
  private final ArrayMap<Context, ArrayMap<ServiceConnection, ServiceDispatcher>> mServices = new ArrayMap();
  
  private String[] mSplitAppDirs;
  
  private String[] mSplitClassLoaderNames;
  
  private SplitDependencyLoaderImpl mSplitLoader;
  
  private String[] mSplitNames;
  
  private String[] mSplitResDirs;
  
  private final ArrayMap<Context, ArrayMap<ServiceConnection, ServiceDispatcher>> mUnboundServices = new ArrayMap();
  
  private final ArrayMap<Context, ArrayMap<BroadcastReceiver, ReceiverDispatcher>> mUnregisteredReceivers = new ArrayMap();
  
  LoadedApk(ActivityThread paramActivityThread) {
    this.mActivityThread = paramActivityThread;
    ApplicationInfo applicationInfo = new ApplicationInfo();
    this.mApplicationInfo = applicationInfo;
    applicationInfo.packageName = "android";
    this.mPackageName = "android";
    this.mAppDir = null;
    this.mResDir = null;
    this.mSplitAppDirs = null;
    this.mSplitResDirs = null;
    this.mSplitClassLoaderNames = null;
    this.mOverlayDirs = null;
    this.mDataDir = null;
    this.mDataDirFile = null;
    this.mDeviceProtectedDataDirFile = null;
    this.mCredentialProtectedDataDirFile = null;
    this.mLibDir = null;
    this.mBaseClassLoader = null;
    this.mSecurityViolation = false;
    this.mIncludeCode = true;
    this.mRegisterPackage = false;
    this.mResources = Resources.getSystem();
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    this.mDefaultClassLoader = classLoader;
    AppComponentFactory appComponentFactory = createAppFactory(this.mApplicationInfo, classLoader);
    this.mAppComponentFactory = appComponentFactory;
    this.mClassLoader = appComponentFactory.instantiateClassLoader(this.mDefaultClassLoader, new ApplicationInfo(this.mApplicationInfo));
  }
  
  public LoadedApk(ActivityThread paramActivityThread, ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo, ClassLoader paramClassLoader, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    this.mActivityThread = paramActivityThread;
    setApplicationInfo(paramApplicationInfo);
    this.mPackageName = paramApplicationInfo.packageName;
    this.mBaseClassLoader = paramClassLoader;
    this.mSecurityViolation = paramBoolean1;
    this.mIncludeCode = paramBoolean2;
    this.mRegisterPackage = paramBoolean3;
    this.mDisplayAdjustments.setCompatibilityInfo(paramCompatibilityInfo);
    this.mAppComponentFactory = createAppFactory(this.mApplicationInfo, this.mBaseClassLoader);
  }
  
  private static ApplicationInfo adjustNativeLibraryPaths(ApplicationInfo paramApplicationInfo) {
    if (paramApplicationInfo.primaryCpuAbi != null && paramApplicationInfo.secondaryCpuAbi != null) {
      String str1 = VMRuntime.getRuntime().vmInstructionSet();
      String str2 = VMRuntime.getInstructionSet(paramApplicationInfo.secondaryCpuAbi);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ro.dalvik.vm.isa.");
      stringBuilder.append(str2);
      String str3 = SystemProperties.get(stringBuilder.toString());
      if (!str3.isEmpty())
        str2 = str3; 
      if (str1.equals(str2)) {
        paramApplicationInfo = new ApplicationInfo(paramApplicationInfo);
        paramApplicationInfo.nativeLibraryDir = paramApplicationInfo.secondaryNativeLibraryDir;
        paramApplicationInfo.primaryCpuAbi = paramApplicationInfo.secondaryCpuAbi;
        return paramApplicationInfo;
      } 
    } 
    return paramApplicationInfo;
  }
  
  private StrictMode.ThreadPolicy allowThreadDiskReads() {
    return (this.mActivityThread == null) ? null : StrictMode.allowThreadDiskReads();
  }
  
  private static void appendApkLibPathIfNeeded(String paramString, ApplicationInfo paramApplicationInfo, List<String> paramList) {
    if (paramList != null && paramApplicationInfo.primaryCpuAbi != null && paramString.endsWith(".apk") && paramApplicationInfo.targetSdkVersion >= 26) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("!/lib/");
      stringBuilder.append(paramApplicationInfo.primaryCpuAbi);
      paramList.add(stringBuilder.toString());
    } 
  }
  
  private static void appendSharedLibrariesLibPathsIfNeeded(List<SharedLibraryInfo> paramList, ApplicationInfo paramApplicationInfo, Set<String> paramSet, List<String> paramList1) {
    if (paramList == null)
      return; 
    for (SharedLibraryInfo sharedLibraryInfo : paramList) {
      List<? extends String> list = sharedLibraryInfo.getAllCodePaths();
      paramSet.addAll(list);
      Iterator<? extends String> iterator = list.iterator();
      while (iterator.hasNext())
        appendApkLibPathIfNeeded(iterator.next(), paramApplicationInfo, paramList1); 
      appendSharedLibrariesLibPathsIfNeeded(sharedLibraryInfo.getDependencies(), paramApplicationInfo, paramSet, paramList1);
    } 
  }
  
  private boolean canAccessDataDir() {
    if (this.mActivityThread == null)
      return false; 
    if (Objects.equals(this.mPackageName, ActivityThread.currentPackageName()))
      return true; 
    StrictMode.ThreadPolicy threadPolicy = allowThreadDiskReads();
    try {
      File file = new File();
      this(this.mDataDir);
      return file.canExecute();
    } finally {
      setThreadPolicy(threadPolicy);
    } 
  }
  
  private AppComponentFactory createAppFactory(ApplicationInfo paramApplicationInfo, ClassLoader paramClassLoader) {
    if (this.mIncludeCode && paramApplicationInfo.appComponentFactory != null && paramClassLoader != null)
      try {
        return (AppComponentFactory)paramClassLoader.loadClass(paramApplicationInfo.appComponentFactory).newInstance();
      } catch (InstantiationException|IllegalAccessException|ClassNotFoundException instantiationException) {
        Slog.e("LoadedApk", "Unable to instantiate appComponentFactory", instantiationException);
      }  
    return AppComponentFactory.DEFAULT;
  }
  
  private void createOrUpdateClassLoaderLocked(List<String> paramList) {
    boolean bool1;
    String str1;
    String str3;
    boolean bool3;
    if (this.mPackageName.equals("android")) {
      if (this.mClassLoader != null)
        return; 
      ClassLoader classLoader = this.mBaseClassLoader;
      if (classLoader != null) {
        this.mDefaultClassLoader = classLoader;
      } else {
        this.mDefaultClassLoader = ClassLoader.getSystemClassLoader();
      } 
      AppComponentFactory appComponentFactory = createAppFactory(this.mApplicationInfo, this.mDefaultClassLoader);
      this.mAppComponentFactory = appComponentFactory;
      this.mClassLoader = appComponentFactory.instantiateClassLoader(this.mDefaultClassLoader, new ApplicationInfo(this.mApplicationInfo));
      return;
    } 
    if (this.mActivityThread != null && !Objects.equals(this.mPackageName, ActivityThread.currentPackageName()) && this.mIncludeCode)
      try {
        ActivityThread.getPackageManager().notifyPackageUse(this.mPackageName, 6);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    if (this.mRegisterPackage)
      try {
        ActivityManager.getService().addPackageDependency(this.mPackageName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    ArrayList<String> arrayList1 = new ArrayList(10);
    ArrayList<String> arrayList2 = new ArrayList(10);
    if (this.mApplicationInfo.isSystemApp() && !this.mApplicationInfo.isUpdatedSystemApp()) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    String str2 = System.getProperty("java.library.path");
    boolean bool = str2.contains("/vendor/lib");
    boolean bool2 = bool1;
    if (this.mApplicationInfo.getCodePath() != null) {
      bool2 = bool1;
      if (this.mApplicationInfo.isVendor()) {
        bool2 = bool1;
        if ((bool ^ true) != 0)
          bool2 = false; 
      } 
    } 
    if (this.mApplicationInfo.getCodePath() != null && this.mApplicationInfo.isProduct() && VndkProperties.product_vndk_version().isPresent())
      bool2 = false; 
    makePaths(this.mActivityThread, bool2, this.mApplicationInfo, arrayList1, arrayList2);
    if (canAccessDataDir()) {
      str3 = this.mDataDir;
    } else {
      str3 = "";
    } 
    if (bool2) {
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(str3);
      stringBuilder2.append(File.pathSeparator);
      stringBuilder2.append(Paths.get(getAppDir(), new String[0]).getParent().toString());
      String str = stringBuilder2.toString();
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str);
      stringBuilder1.append(File.pathSeparator);
      stringBuilder1.append(str2);
      str3 = stringBuilder1.toString();
    } 
    String str4 = TextUtils.join(File.pathSeparator, arrayList2);
    ActivityThread activityThread = this.mActivityThread;
    if (activityThread != null) {
      str1 = activityThread.getStringCoreSetting("gpu_debug_app", "");
      if (!str1.isEmpty() && this.mPackageName.equals(str1))
        try {
          ApplicationInfo applicationInfo = ActivityThread.getPackageManager().getApplicationInfo(this.mPackageName, 128, UserHandle.myUserId());
          String str6 = GraphicsEnvironment.getInstance().getDebugLayerPathsFromSettings(this.mActivityThread.getCoreSettings(), ActivityThread.getPackageManager(), this.mPackageName, applicationInfo);
          String str5 = str3;
          if (str6 != null) {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append(str3);
            stringBuilder.append(File.pathSeparator);
            stringBuilder.append(str6);
            str5 = stringBuilder.toString();
          } 
          str3 = str5;
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("RemoteException when fetching debug layer paths for: ");
          stringBuilder.append(this.mPackageName);
          Slog.e("ActivityThread", stringBuilder.toString());
        }  
    } 
    if (!this.mIncludeCode) {
      if (this.mDefaultClassLoader == null) {
        StrictMode.ThreadPolicy threadPolicy = allowThreadDiskReads();
        this.mDefaultClassLoader = ApplicationLoaders.getDefault().getClassLoader("", this.mApplicationInfo.targetSdkVersion, bool2, str4, str3, this.mBaseClassLoader, null);
        setThreadPolicy(threadPolicy);
        this.mAppComponentFactory = AppComponentFactory.DEFAULT;
      } 
      if (this.mClassLoader == null)
        this.mClassLoader = this.mAppComponentFactory.instantiateClassLoader(this.mDefaultClassLoader, new ApplicationInfo(this.mApplicationInfo)); 
      return;
    } 
    if (arrayList1.size() == 1) {
      str1 = arrayList1.get(0);
    } else {
      str1 = TextUtils.join(File.pathSeparator, arrayList1);
    } 
    if (this.mDefaultClassLoader == null) {
      StrictMode.ThreadPolicy threadPolicy = allowThreadDiskReads();
      List<ClassLoader> list = createSharedLibrariesLoaders(this.mApplicationInfo.sharedLibraryInfos, bool2, str4, str3);
      ClassLoader classLoader = ApplicationLoaders.getDefault().getClassLoaderWithSharedLibraries(str1, this.mApplicationInfo.targetSdkVersion, bool2, str4, str3, this.mBaseClassLoader, this.mApplicationInfo.classLoaderName, list);
      this.mDefaultClassLoader = classLoader;
      this.mAppComponentFactory = createAppFactory(this.mApplicationInfo, classLoader);
      setThreadPolicy(threadPolicy);
      bool3 = true;
    } else {
      bool3 = false;
    } 
    if (!arrayList2.isEmpty() && SystemProperties.getBoolean("pi.append_native_lib_paths", true)) {
      StrictMode.ThreadPolicy threadPolicy = allowThreadDiskReads();
      try {
        ApplicationLoaders.getDefault().addNative(this.mDefaultClassLoader, arrayList2);
      } finally {
        setThreadPolicy(threadPolicy);
      } 
    } 
    boolean bool4 = bool3;
    if (paramList != null) {
      bool4 = bool3;
      if (paramList.size() > 0) {
        String str = TextUtils.join(File.pathSeparator, paramList);
        ApplicationLoaders.getDefault().addPath(this.mDefaultClassLoader, str);
        bool4 = true;
      } 
    } 
    if (bool4 && !ActivityThread.isSystem() && this.mActivityThread != null)
      setupJitProfileSupport(); 
    if (this.mClassLoader == null)
      this.mClassLoader = this.mAppComponentFactory.instantiateClassLoader(this.mDefaultClassLoader, new ApplicationInfo(this.mApplicationInfo)); 
  }
  
  private List<ClassLoader> createSharedLibrariesLoaders(List<SharedLibraryInfo> paramList, boolean paramBoolean, String paramString1, String paramString2) {
    if (paramList == null)
      return null; 
    ArrayList<ClassLoader> arrayList = new ArrayList();
    Iterator<SharedLibraryInfo> iterator = paramList.iterator();
    while (iterator.hasNext())
      arrayList.add(createSharedLibraryLoader(iterator.next(), paramBoolean, paramString1, paramString2)); 
    return arrayList;
  }
  
  private static String[] getLibrariesFor(String paramString) {
    try {
      ApplicationInfo applicationInfo = ActivityThread.getPackageManager().getApplicationInfo(paramString, 1024, UserHandle.myUserId());
      return (applicationInfo == null) ? null : applicationInfo.sharedLibraryFiles;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private IServiceConnection getServiceDispatcherCommon(ServiceConnection paramServiceConnection, Context paramContext, Handler paramHandler, Executor paramExecutor, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mServices : Landroid/util/ArrayMap;
    //   4: astore #6
    //   6: aload #6
    //   8: monitorenter
    //   9: aconst_null
    //   10: astore #7
    //   12: aload_0
    //   13: getfield mServices : Landroid/util/ArrayMap;
    //   16: aload_2
    //   17: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   20: checkcast android/util/ArrayMap
    //   23: astore #8
    //   25: aload #8
    //   27: ifnull -> 41
    //   30: aload #8
    //   32: aload_1
    //   33: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   36: checkcast android/app/LoadedApk$ServiceDispatcher
    //   39: astore #7
    //   41: aload #7
    //   43: ifnonnull -> 122
    //   46: aload #4
    //   48: ifnull -> 68
    //   51: new android/app/LoadedApk$ServiceDispatcher
    //   54: astore_3
    //   55: aload_3
    //   56: aload_1
    //   57: aload_2
    //   58: aload #4
    //   60: iload #5
    //   62: invokespecial <init> : (Landroid/content/ServiceConnection;Landroid/content/Context;Ljava/util/concurrent/Executor;I)V
    //   65: goto -> 81
    //   68: new android/app/LoadedApk$ServiceDispatcher
    //   71: dup
    //   72: aload_1
    //   73: aload_2
    //   74: aload_3
    //   75: iload #5
    //   77: invokespecial <init> : (Landroid/content/ServiceConnection;Landroid/content/Context;Landroid/os/Handler;I)V
    //   80: astore_3
    //   81: aload #8
    //   83: astore #4
    //   85: aload #8
    //   87: ifnonnull -> 111
    //   90: new android/util/ArrayMap
    //   93: astore #4
    //   95: aload #4
    //   97: invokespecial <init> : ()V
    //   100: aload_0
    //   101: getfield mServices : Landroid/util/ArrayMap;
    //   104: aload_2
    //   105: aload #4
    //   107: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   110: pop
    //   111: aload #4
    //   113: aload_1
    //   114: aload_3
    //   115: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   118: pop
    //   119: goto -> 134
    //   122: aload #7
    //   124: aload_2
    //   125: aload_3
    //   126: aload #4
    //   128: invokevirtual validate : (Landroid/content/Context;Landroid/os/Handler;Ljava/util/concurrent/Executor;)V
    //   131: aload #7
    //   133: astore_3
    //   134: aload_3
    //   135: invokevirtual getIServiceConnection : ()Landroid/app/IServiceConnection;
    //   138: astore_1
    //   139: aload #6
    //   141: monitorexit
    //   142: aload_1
    //   143: areturn
    //   144: astore_1
    //   145: aload #6
    //   147: monitorexit
    //   148: aload_1
    //   149: athrow
    // Exception table:
    //   from	to	target	type
    //   12	25	144	finally
    //   30	41	144	finally
    //   51	65	144	finally
    //   68	81	144	finally
    //   90	100	144	finally
    //   100	111	144	finally
    //   111	119	144	finally
    //   122	131	144	finally
    //   134	142	144	finally
    //   145	148	144	finally
  }
  
  private void initializeJavaContextClassLoader() {
    ClassLoader classLoader;
    boolean bool2;
    boolean bool3;
    ActivityThread.getPackageManager();
    PackageInfo packageInfo = PackageManager.getPackageInfoAsUserCached(this.mPackageName, 268435456, UserHandle.myUserId());
    String str = packageInfo.sharedUserId;
    boolean bool1 = true;
    if (str != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (packageInfo.applicationInfo != null && !this.mPackageName.equals(packageInfo.applicationInfo.processName)) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    boolean bool4 = bool1;
    if (!bool2)
      if (bool3) {
        bool4 = bool1;
      } else {
        bool4 = false;
      }  
    if (bool4) {
      classLoader = new WarningContextClassLoader();
    } else {
      classLoader = this.mClassLoader;
    } 
    Thread.currentThread().setContextClassLoader(classLoader);
  }
  
  public static void makePaths(ActivityThread paramActivityThread, ApplicationInfo paramApplicationInfo, List<String> paramList) {
    makePaths(paramActivityThread, false, paramApplicationInfo, paramList, null);
  }
  
  public static void makePaths(ActivityThread paramActivityThread, boolean paramBoolean, ApplicationInfo paramApplicationInfo, List<String> paramList1, List<String> paramList2) {
    // Byte code:
    //   0: aload_2
    //   1: getfield sourceDir : Ljava/lang/String;
    //   4: astore #5
    //   6: aload_2
    //   7: getfield nativeLibraryDir : Ljava/lang/String;
    //   10: astore #6
    //   12: aload_3
    //   13: invokeinterface clear : ()V
    //   18: aload_3
    //   19: aload #5
    //   21: invokeinterface add : (Ljava/lang/Object;)Z
    //   26: pop
    //   27: aload_2
    //   28: getfield splitSourceDirs : [Ljava/lang/String;
    //   31: ifnull -> 50
    //   34: aload_2
    //   35: invokevirtual requestsIsolatedSplitLoading : ()Z
    //   38: ifne -> 50
    //   41: aload_3
    //   42: aload_2
    //   43: getfield splitSourceDirs : [Ljava/lang/String;
    //   46: invokestatic addAll : (Ljava/util/Collection;[Ljava/lang/Object;)Z
    //   49: pop
    //   50: aload #4
    //   52: ifnull -> 62
    //   55: aload #4
    //   57: invokeinterface clear : ()V
    //   62: aconst_null
    //   63: astore #7
    //   65: aload #7
    //   67: astore #8
    //   69: aload_0
    //   70: ifnull -> 267
    //   73: aload_0
    //   74: getfield mInstrumentationPackageName : Ljava/lang/String;
    //   77: astore #9
    //   79: aload_0
    //   80: getfield mInstrumentationAppDir : Ljava/lang/String;
    //   83: astore #10
    //   85: aload_0
    //   86: getfield mInstrumentationSplitAppDirs : [Ljava/lang/String;
    //   89: astore #11
    //   91: aload_0
    //   92: getfield mInstrumentationLibDir : Ljava/lang/String;
    //   95: astore #12
    //   97: aload_0
    //   98: getfield mInstrumentedAppDir : Ljava/lang/String;
    //   101: astore #13
    //   103: aload_0
    //   104: getfield mInstrumentedSplitAppDirs : [Ljava/lang/String;
    //   107: astore #14
    //   109: aload_0
    //   110: getfield mInstrumentedLibDir : Ljava/lang/String;
    //   113: astore_0
    //   114: aload #5
    //   116: aload #10
    //   118: invokevirtual equals : (Ljava/lang/Object;)Z
    //   121: ifne -> 138
    //   124: aload #7
    //   126: astore #8
    //   128: aload #5
    //   130: aload #13
    //   132: invokevirtual equals : (Ljava/lang/Object;)Z
    //   135: ifeq -> 267
    //   138: aload_3
    //   139: invokeinterface clear : ()V
    //   144: aload_3
    //   145: aload #10
    //   147: invokeinterface add : (Ljava/lang/Object;)Z
    //   152: pop
    //   153: aload #10
    //   155: aload #13
    //   157: invokevirtual equals : (Ljava/lang/Object;)Z
    //   160: ifne -> 172
    //   163: aload_3
    //   164: aload #13
    //   166: invokeinterface add : (Ljava/lang/Object;)Z
    //   171: pop
    //   172: aload_2
    //   173: invokevirtual requestsIsolatedSplitLoading : ()Z
    //   176: ifne -> 213
    //   179: aload #11
    //   181: ifnull -> 191
    //   184: aload_3
    //   185: aload #11
    //   187: invokestatic addAll : (Ljava/util/Collection;[Ljava/lang/Object;)Z
    //   190: pop
    //   191: aload #10
    //   193: aload #13
    //   195: invokevirtual equals : (Ljava/lang/Object;)Z
    //   198: ifne -> 213
    //   201: aload #14
    //   203: ifnull -> 213
    //   206: aload_3
    //   207: aload #14
    //   209: invokestatic addAll : (Ljava/util/Collection;[Ljava/lang/Object;)Z
    //   212: pop
    //   213: aload #4
    //   215: ifnull -> 246
    //   218: aload #4
    //   220: aload #12
    //   222: invokeinterface add : (Ljava/lang/Object;)Z
    //   227: pop
    //   228: aload #12
    //   230: aload_0
    //   231: invokevirtual equals : (Ljava/lang/Object;)Z
    //   234: ifne -> 246
    //   237: aload #4
    //   239: aload_0
    //   240: invokeinterface add : (Ljava/lang/Object;)Z
    //   245: pop
    //   246: aload #7
    //   248: astore #8
    //   250: aload #13
    //   252: aload #10
    //   254: invokevirtual equals : (Ljava/lang/Object;)Z
    //   257: ifne -> 267
    //   260: aload #9
    //   262: invokestatic getLibrariesFor : (Ljava/lang/String;)[Ljava/lang/String;
    //   265: astore #8
    //   267: aload #4
    //   269: ifnull -> 462
    //   272: aload #4
    //   274: invokeinterface isEmpty : ()Z
    //   279: ifeq -> 292
    //   282: aload #4
    //   284: aload #6
    //   286: invokeinterface add : (Ljava/lang/Object;)Z
    //   291: pop
    //   292: aload_2
    //   293: getfield primaryCpuAbi : Ljava/lang/String;
    //   296: ifnull -> 444
    //   299: aload_2
    //   300: getfield targetSdkVersion : I
    //   303: bipush #24
    //   305: if_icmpge -> 367
    //   308: new java/lang/StringBuilder
    //   311: dup
    //   312: invokespecial <init> : ()V
    //   315: astore #7
    //   317: aload #7
    //   319: ldc_w '/system/fake-libs'
    //   322: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   325: pop
    //   326: aload_2
    //   327: getfield primaryCpuAbi : Ljava/lang/String;
    //   330: invokestatic is64BitAbi : (Ljava/lang/String;)Z
    //   333: ifeq -> 343
    //   336: ldc_w '64'
    //   339: astore_0
    //   340: goto -> 347
    //   343: ldc_w ''
    //   346: astore_0
    //   347: aload #7
    //   349: aload_0
    //   350: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   353: pop
    //   354: aload #4
    //   356: aload #7
    //   358: invokevirtual toString : ()Ljava/lang/String;
    //   361: invokeinterface add : (Ljava/lang/Object;)Z
    //   366: pop
    //   367: aload_3
    //   368: invokeinterface iterator : ()Ljava/util/Iterator;
    //   373: astore #7
    //   375: aload #7
    //   377: invokeinterface hasNext : ()Z
    //   382: ifeq -> 444
    //   385: aload #7
    //   387: invokeinterface next : ()Ljava/lang/Object;
    //   392: checkcast java/lang/String
    //   395: astore #6
    //   397: new java/lang/StringBuilder
    //   400: dup
    //   401: invokespecial <init> : ()V
    //   404: astore_0
    //   405: aload_0
    //   406: aload #6
    //   408: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: pop
    //   412: aload_0
    //   413: ldc_w '!/lib/'
    //   416: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   419: pop
    //   420: aload_0
    //   421: aload_2
    //   422: getfield primaryCpuAbi : Ljava/lang/String;
    //   425: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   428: pop
    //   429: aload #4
    //   431: aload_0
    //   432: invokevirtual toString : ()Ljava/lang/String;
    //   435: invokeinterface add : (Ljava/lang/Object;)Z
    //   440: pop
    //   441: goto -> 375
    //   444: iload_1
    //   445: ifeq -> 462
    //   448: aload #4
    //   450: ldc_w 'java.library.path'
    //   453: invokestatic getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   456: invokeinterface add : (Ljava/lang/Object;)Z
    //   461: pop
    //   462: new java/util/LinkedHashSet
    //   465: dup
    //   466: invokespecial <init> : ()V
    //   469: astore_0
    //   470: aload_2
    //   471: getfield sharedLibraryInfos : Ljava/util/List;
    //   474: aload_2
    //   475: aload_0
    //   476: aload #4
    //   478: invokestatic appendSharedLibrariesLibPathsIfNeeded : (Ljava/util/List;Landroid/content/pm/ApplicationInfo;Ljava/util/Set;Ljava/util/List;)V
    //   481: aload_2
    //   482: getfield sharedLibraryFiles : [Ljava/lang/String;
    //   485: ifnull -> 583
    //   488: iconst_0
    //   489: istore #15
    //   491: aload_2
    //   492: getfield sharedLibraryFiles : [Ljava/lang/String;
    //   495: astore #7
    //   497: aload #7
    //   499: arraylength
    //   500: istore #16
    //   502: iconst_0
    //   503: istore #17
    //   505: iload #17
    //   507: iload #16
    //   509: if_icmpge -> 583
    //   512: aload #7
    //   514: iload #17
    //   516: aaload
    //   517: astore #6
    //   519: iload #15
    //   521: istore #18
    //   523: aload_0
    //   524: aload #6
    //   526: invokeinterface contains : (Ljava/lang/Object;)Z
    //   531: ifne -> 573
    //   534: iload #15
    //   536: istore #18
    //   538: aload_3
    //   539: aload #6
    //   541: invokeinterface contains : (Ljava/lang/Object;)Z
    //   546: ifne -> 573
    //   549: aload_3
    //   550: iload #15
    //   552: aload #6
    //   554: invokeinterface add : (ILjava/lang/Object;)V
    //   559: iload #15
    //   561: iconst_1
    //   562: iadd
    //   563: istore #18
    //   565: aload #6
    //   567: aload_2
    //   568: aload #4
    //   570: invokestatic appendApkLibPathIfNeeded : (Ljava/lang/String;Landroid/content/pm/ApplicationInfo;Ljava/util/List;)V
    //   573: iinc #17, 1
    //   576: iload #18
    //   578: istore #15
    //   580: goto -> 505
    //   583: aload #8
    //   585: ifnull -> 640
    //   588: aload #8
    //   590: arraylength
    //   591: istore #15
    //   593: iconst_0
    //   594: istore #17
    //   596: iload #17
    //   598: iload #15
    //   600: if_icmpge -> 640
    //   603: aload #8
    //   605: iload #17
    //   607: aaload
    //   608: astore_0
    //   609: aload_3
    //   610: aload_0
    //   611: invokeinterface contains : (Ljava/lang/Object;)Z
    //   616: ifne -> 634
    //   619: aload_3
    //   620: iconst_0
    //   621: aload_0
    //   622: invokeinterface add : (ILjava/lang/Object;)V
    //   627: aload_0
    //   628: aload_2
    //   629: aload #4
    //   631: invokestatic appendApkLibPathIfNeeded : (Ljava/lang/String;Landroid/content/pm/ApplicationInfo;Ljava/util/List;)V
    //   634: iinc #17, 1
    //   637: goto -> 596
    //   640: return
  }
  
  private void rewriteRValues(ClassLoader paramClassLoader, String paramString, int paramInt) {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(paramString);
      stringBuilder.append(".R");
      Class<?> clazz = paramClassLoader.loadClass(stringBuilder.toString());
      try {
        Throwable throwable;
        Method method = clazz.getMethod("onResourcesLoaded", new Class[] { int.class });
        try {
          method.invoke(null, new Object[] { Integer.valueOf(paramInt) });
          return;
        } catch (IllegalAccessException null) {
        
        } catch (InvocationTargetException invocationTargetException) {
          throwable = invocationTargetException.getCause();
        } 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to rewrite resource references for ");
        stringBuilder.append(paramString);
        throw new RuntimeException(stringBuilder.toString(), throwable);
      } catch (NoSuchMethodException noSuchMethodException) {
        return;
      } 
    } catch (ClassNotFoundException classNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("No resource references to update in package ");
      stringBuilder.append(paramString);
      Log.i("LoadedApk", stringBuilder.toString());
      return;
    } 
  }
  
  private void setApplicationInfo(ApplicationInfo paramApplicationInfo) {
    String str;
    String[] arrayOfString;
    int i = Process.myUid();
    ApplicationInfo applicationInfo = adjustNativeLibraryPaths(paramApplicationInfo);
    this.mApplicationInfo = applicationInfo;
    this.mAppDir = applicationInfo.sourceDir;
    if (applicationInfo.uid == i) {
      str = applicationInfo.sourceDir;
    } else {
      str = applicationInfo.publicSourceDir;
    } 
    this.mResDir = str;
    this.mOverlayDirs = applicationInfo.resourceDirs;
    this.mDataDir = applicationInfo.dataDir;
    this.mLibDir = applicationInfo.nativeLibraryDir;
    this.mDataDirFile = FileUtils.newFileOrNull(applicationInfo.dataDir);
    this.mDeviceProtectedDataDirFile = FileUtils.newFileOrNull(applicationInfo.deviceProtectedDataDir);
    this.mCredentialProtectedDataDirFile = FileUtils.newFileOrNull(applicationInfo.credentialProtectedDataDir);
    this.mSplitNames = applicationInfo.splitNames;
    this.mSplitAppDirs = applicationInfo.splitSourceDirs;
    if (applicationInfo.uid == i) {
      arrayOfString = applicationInfo.splitSourceDirs;
    } else {
      arrayOfString = applicationInfo.splitPublicSourceDirs;
    } 
    this.mSplitResDirs = arrayOfString;
    this.mSplitClassLoaderNames = applicationInfo.splitClassLoaderNames;
    if (applicationInfo.requestsIsolatedSplitLoading() && !ArrayUtils.isEmpty((Object[])this.mSplitNames))
      this.mSplitLoader = new SplitDependencyLoaderImpl(applicationInfo.splitDependencies); 
  }
  
  private void setThreadPolicy(StrictMode.ThreadPolicy paramThreadPolicy) {
    if (this.mActivityThread != null && paramThreadPolicy != null)
      StrictMode.setThreadPolicy(paramThreadPolicy); 
  }
  
  private void setupJitProfileSupport() {
    if (!SystemProperties.getBoolean("dalvik.vm.usejitprofiles", false))
      return; 
    BaseDexClassLoader.setReporter(DexLoadReporter.getInstance());
    if (this.mApplicationInfo.uid != Process.myUid())
      return; 
    ArrayList<String> arrayList = new ArrayList();
    if ((this.mApplicationInfo.flags & 0x4) != 0)
      arrayList.add(this.mApplicationInfo.sourceDir); 
    if (this.mApplicationInfo.splitSourceDirs != null)
      Collections.addAll(arrayList, this.mApplicationInfo.splitSourceDirs); 
    if (arrayList.isEmpty())
      return; 
    for (int i = arrayList.size() - 1; i >= 0; i--) {
      String str;
      if (i == 0) {
        str = null;
      } else {
        str = this.mApplicationInfo.splitNames[i - 1];
      } 
      VMRuntime.registerAppInfo(ArtManager.getCurrentProfilePath(this.mPackageName, UserHandle.myUserId(), str), new String[] { arrayList.get(i) });
    } 
    DexLoadReporter.getInstance().registerAppDataDir(this.mPackageName, this.mDataDir);
  }
  
  ClassLoader createSharedLibraryLoader(SharedLibraryInfo paramSharedLibraryInfo, boolean paramBoolean, String paramString1, String paramString2) {
    String str;
    List<String> list = paramSharedLibraryInfo.getAllCodePaths();
    List<ClassLoader> list1 = createSharedLibrariesLoaders(paramSharedLibraryInfo.getDependencies(), paramBoolean, paramString1, paramString2);
    if (list.size() == 1) {
      str = list.get(0);
    } else {
      str = TextUtils.join(File.pathSeparator, list);
    } 
    return ApplicationLoaders.getDefault().getSharedLibraryClassLoaderWithSharedLibraries(str, this.mApplicationInfo.targetSdkVersion, paramBoolean, paramString1, paramString2, null, null, list1);
  }
  
  public IIntentReceiver forgetReceiverDispatcher(Context paramContext, BroadcastReceiver paramBroadcastReceiver) {
    synchronized (this.mReceivers) {
      IIntentReceiver iIntentReceiver;
      StringBuilder stringBuilder1;
      ArrayMap arrayMap = (ArrayMap)this.mReceivers.get(paramContext);
      if (arrayMap != null) {
        ReceiverDispatcher receiverDispatcher = (ReceiverDispatcher)arrayMap.get(paramBroadcastReceiver);
        if (receiverDispatcher != null) {
          arrayMap.remove(paramBroadcastReceiver);
          if (arrayMap.size() == 0)
            this.mReceivers.remove(paramContext); 
          if (paramBroadcastReceiver.getDebugUnregister()) {
            ArrayMap arrayMap1 = (ArrayMap)this.mUnregisteredReceivers.get(paramContext);
            arrayMap = arrayMap1;
            if (arrayMap1 == null) {
              arrayMap = new ArrayMap();
              this();
              this.mUnregisteredReceivers.put(paramContext, arrayMap);
            } 
            IllegalArgumentException illegalArgumentException1 = new IllegalArgumentException();
            this("Originally unregistered here:");
            illegalArgumentException1.fillInStackTrace();
            receiverDispatcher.setUnregisterLocation(illegalArgumentException1);
            arrayMap.put(paramBroadcastReceiver, receiverDispatcher);
          } 
          receiverDispatcher.mForgotten = true;
          iIntentReceiver = receiverDispatcher.getIIntentReceiver();
          return iIntentReceiver;
        } 
      } 
      arrayMap = (ArrayMap)this.mUnregisteredReceivers.get(iIntentReceiver);
      if (arrayMap != null) {
        ReceiverDispatcher receiverDispatcher = (ReceiverDispatcher)arrayMap.get(paramBroadcastReceiver);
        if (receiverDispatcher != null) {
          RuntimeException runtimeException = receiverDispatcher.getUnregisterLocation();
          IllegalArgumentException illegalArgumentException1 = new IllegalArgumentException();
          stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("Unregistering Receiver ");
          stringBuilder1.append(paramBroadcastReceiver);
          stringBuilder1.append(" that was already unregistered");
          this(stringBuilder1.toString(), runtimeException);
          throw illegalArgumentException1;
        } 
      } 
      if (stringBuilder1 == null) {
        IllegalStateException illegalStateException = new IllegalStateException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Unbinding Receiver ");
        stringBuilder.append(paramBroadcastReceiver);
        stringBuilder.append(" from Context that is no longer in use: ");
        stringBuilder.append(stringBuilder1);
        this(stringBuilder.toString());
        throw illegalStateException;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      StringBuilder stringBuilder2 = new StringBuilder();
      this();
      stringBuilder2.append("Receiver not registered: ");
      stringBuilder2.append(paramBroadcastReceiver);
      this(stringBuilder2.toString());
      throw illegalArgumentException;
    } 
  }
  
  public final IServiceConnection forgetServiceDispatcher(Context paramContext, ServiceConnection paramServiceConnection) {
    synchronized (this.mServices) {
      IServiceConnection iServiceConnection;
      RuntimeException runtimeException;
      ArrayMap arrayMap = (ArrayMap)this.mServices.get(paramContext);
      if (arrayMap != null) {
        ServiceDispatcher serviceDispatcher = (ServiceDispatcher)arrayMap.get(paramServiceConnection);
        if (serviceDispatcher != null) {
          arrayMap.remove(paramServiceConnection);
          serviceDispatcher.doForget();
          if (arrayMap.size() == 0)
            this.mServices.remove(paramContext); 
          if ((serviceDispatcher.getFlags() & 0x2) != 0) {
            ArrayMap arrayMap1 = (ArrayMap)this.mUnboundServices.get(paramContext);
            arrayMap = arrayMap1;
            if (arrayMap1 == null) {
              arrayMap = new ArrayMap();
              this();
              this.mUnboundServices.put(paramContext, arrayMap);
            } 
            runtimeException = new IllegalArgumentException();
            this("Originally unbound here:");
            runtimeException.fillInStackTrace();
            serviceDispatcher.setUnbindLocation(runtimeException);
            arrayMap.put(paramServiceConnection, serviceDispatcher);
          } 
          iServiceConnection = serviceDispatcher.getIServiceConnection();
          return iServiceConnection;
        } 
      } 
      arrayMap = (ArrayMap)this.mUnboundServices.get(iServiceConnection);
      if (arrayMap != null) {
        ServiceDispatcher serviceDispatcher = (ServiceDispatcher)arrayMap.get(paramServiceConnection);
        if (serviceDispatcher != null) {
          runtimeException = serviceDispatcher.getUnbindLocation();
          IllegalArgumentException illegalArgumentException1 = new IllegalArgumentException();
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("Unbinding Service ");
          stringBuilder1.append(paramServiceConnection);
          stringBuilder1.append(" that was already unbound");
          this(stringBuilder1.toString(), runtimeException);
          throw illegalArgumentException1;
        } 
      } 
      if (runtimeException == null) {
        IllegalStateException illegalStateException = new IllegalStateException();
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("Unbinding Service ");
        stringBuilder1.append(paramServiceConnection);
        stringBuilder1.append(" from Context that is no longer in use: ");
        stringBuilder1.append(runtimeException);
        this(stringBuilder1.toString());
        throw illegalStateException;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Service not registered: ");
      stringBuilder.append(paramServiceConnection);
      this(stringBuilder.toString());
      throw illegalArgumentException;
    } 
  }
  
  public String getAppDir() {
    return this.mAppDir;
  }
  
  public AppComponentFactory getAppFactory() {
    return this.mAppComponentFactory;
  }
  
  Application getApplication() {
    return this.mApplication;
  }
  
  public ApplicationInfo getApplicationInfo() {
    return this.mApplicationInfo;
  }
  
  public AssetManager getAssets() {
    return getResources().getAssets();
  }
  
  public ClassLoader getClassLoader() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mClassLoader : Ljava/lang/ClassLoader;
    //   6: ifnonnull -> 14
    //   9: aload_0
    //   10: aconst_null
    //   11: invokespecial createOrUpdateClassLoaderLocked : (Ljava/util/List;)V
    //   14: aload_0
    //   15: getfield mClassLoader : Ljava/lang/ClassLoader;
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: areturn
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	23	finally
    //   14	21	23	finally
    //   24	26	23	finally
  }
  
  public CompatibilityInfo getCompatibilityInfo() {
    return this.mDisplayAdjustments.getCompatibilityInfo();
  }
  
  public File getCredentialProtectedDataDirFile() {
    return this.mCredentialProtectedDataDirFile;
  }
  
  public String getDataDir() {
    return this.mDataDir;
  }
  
  public File getDataDirFile() {
    return this.mDataDirFile;
  }
  
  public File getDeviceProtectedDataDirFile() {
    return this.mDeviceProtectedDataDirFile;
  }
  
  public String getLibDir() {
    return this.mLibDir;
  }
  
  public String[] getOverlayDirs() {
    return this.mOverlayDirs;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public IIntentReceiver getReceiverDispatcher(BroadcastReceiver paramBroadcastReceiver, Context paramContext, Handler paramHandler, Instrumentation paramInstrumentation, boolean paramBoolean) {
    ReceiverDispatcher receiverDispatcher1;
    ArrayMap<Context, ArrayMap<BroadcastReceiver, ReceiverDispatcher>> arrayMap = this.mReceivers;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{android/util/ArrayMap<[ObjectType{android/content/Context}, ObjectType{android/util/ArrayMap<[ObjectType{android/content/BroadcastReceiver}, InnerObjectType{ObjectType{android/app/LoadedApk}.Landroid/app/LoadedApk$ReceiverDispatcher;}]>}]>}, name=null} */
    ReceiverDispatcher receiverDispatcher2 = null;
    ArrayMap arrayMap1 = null;
    ReceiverDispatcher receiverDispatcher3 = receiverDispatcher2;
    if (paramBoolean)
      try {
        ArrayMap arrayMap2 = (ArrayMap)this.mReceivers.get(paramContext);
        receiverDispatcher3 = receiverDispatcher2;
        arrayMap1 = arrayMap2;
        if (arrayMap2 != null) {
          receiverDispatcher3 = (ReceiverDispatcher)arrayMap2.get(paramBroadcastReceiver);
          arrayMap1 = arrayMap2;
        } 
      } finally {} 
    if (receiverDispatcher3 == null) {
      receiverDispatcher3 = new ReceiverDispatcher();
      this(paramBroadcastReceiver, paramContext, paramHandler, paramInstrumentation, paramBoolean);
      receiverDispatcher1 = receiverDispatcher3;
      receiverDispatcher3 = receiverDispatcher1;
      if (paramBoolean) {
        ArrayMap arrayMap2 = arrayMap1;
        if (arrayMap1 == null) {
          arrayMap2 = new ArrayMap();
          this();
          this.mReceivers.put(paramContext, arrayMap2);
        } 
        arrayMap2.put(paramBroadcastReceiver, receiverDispatcher1);
        receiverDispatcher3 = receiverDispatcher1;
      } 
    } else {
      receiverDispatcher3.validate(paramContext, (Handler)receiverDispatcher1);
    } 
    receiverDispatcher3.mForgotten = false;
    IIntentReceiver iIntentReceiver = receiverDispatcher3.getIIntentReceiver();
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/util/ArrayMap<[ObjectType{android/content/Context}, ObjectType{android/util/ArrayMap<[ObjectType{android/content/BroadcastReceiver}, InnerObjectType{ObjectType{android/app/LoadedApk}.Landroid/app/LoadedApk$ReceiverDispatcher;}]>}]>}, name=null} */
    return iIntentReceiver;
  }
  
  public String getResDir() {
    return this.mResDir;
  }
  
  public Resources getResources() {
    if (this.mResources == null)
      try {
        String[] arrayOfString = getSplitPaths(null);
        this.mResources = ResourcesManager.getInstance().getResources(null, this.mResDir, arrayOfString, this.mOverlayDirs, this.mApplicationInfo.sharedLibraryFiles, 0, null, getCompatibilityInfo(), getClassLoader(), null);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        throw new AssertionError("null split not found");
      }  
    return this.mResources;
  }
  
  public final IServiceConnection getServiceDispatcher(ServiceConnection paramServiceConnection, Context paramContext, Handler paramHandler, int paramInt) {
    return getServiceDispatcherCommon(paramServiceConnection, paramContext, paramHandler, null, paramInt);
  }
  
  public final IServiceConnection getServiceDispatcher(ServiceConnection paramServiceConnection, Context paramContext, Executor paramExecutor, int paramInt) {
    return getServiceDispatcherCommon(paramServiceConnection, paramContext, null, paramExecutor, paramInt);
  }
  
  public String[] getSplitAppDirs() {
    return this.mSplitAppDirs;
  }
  
  ClassLoader getSplitClassLoader(String paramString) throws PackageManager.NameNotFoundException {
    SplitDependencyLoaderImpl splitDependencyLoaderImpl = this.mSplitLoader;
    return (splitDependencyLoaderImpl == null) ? this.mClassLoader : splitDependencyLoaderImpl.getClassLoaderForSplit(paramString);
  }
  
  String[] getSplitPaths(String paramString) throws PackageManager.NameNotFoundException {
    SplitDependencyLoaderImpl splitDependencyLoaderImpl = this.mSplitLoader;
    return (splitDependencyLoaderImpl == null) ? this.mSplitResDirs : splitDependencyLoaderImpl.getSplitPathsForSplit(paramString);
  }
  
  public String[] getSplitResDirs() {
    return this.mSplitResDirs;
  }
  
  public int getTargetSdkVersion() {
    return this.mApplicationInfo.targetSdkVersion;
  }
  
  void installSystemApplicationInfo(ApplicationInfo paramApplicationInfo, ClassLoader paramClassLoader) {
    this.mApplicationInfo = paramApplicationInfo;
    this.mDefaultClassLoader = paramClassLoader;
    AppComponentFactory appComponentFactory = createAppFactory(paramApplicationInfo, paramClassLoader);
    this.mAppComponentFactory = appComponentFactory;
    this.mClassLoader = appComponentFactory.instantiateClassLoader(this.mDefaultClassLoader, new ApplicationInfo(this.mApplicationInfo));
  }
  
  public boolean isSecurityViolation() {
    return this.mSecurityViolation;
  }
  
  public IServiceConnection lookupServiceDispatcher(ServiceConnection paramServiceConnection, Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mServices : Landroid/util/ArrayMap;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aconst_null
    //   8: astore #4
    //   10: aload_0
    //   11: getfield mServices : Landroid/util/ArrayMap;
    //   14: aload_2
    //   15: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   18: checkcast android/util/ArrayMap
    //   21: astore #5
    //   23: aload #4
    //   25: astore_2
    //   26: aload #5
    //   28: ifnull -> 41
    //   31: aload #5
    //   33: aload_1
    //   34: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   37: checkcast android/app/LoadedApk$ServiceDispatcher
    //   40: astore_2
    //   41: aload_2
    //   42: ifnull -> 53
    //   45: aload_2
    //   46: invokevirtual getIServiceConnection : ()Landroid/app/IServiceConnection;
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
    //   10	23	59	finally
    //   31	41	59	finally
    //   45	50	59	finally
    //   55	57	59	finally
    //   60	62	59	finally
  }
  
  public Application makeApplication(boolean paramBoolean, Instrumentation paramInstrumentation) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mApplication : Landroid/app/Application;
    //   4: astore_3
    //   5: aload_3
    //   6: ifnull -> 11
    //   9: aload_3
    //   10: areturn
    //   11: ldc2_w 64
    //   14: ldc_w 'makeApplication'
    //   17: invokestatic traceBegin : (JLjava/lang/String;)V
    //   20: aconst_null
    //   21: astore #4
    //   23: aload_0
    //   24: getfield mApplicationInfo : Landroid/content/pm/ApplicationInfo;
    //   27: getfield className : Ljava/lang/String;
    //   30: astore_3
    //   31: iload_1
    //   32: ifne -> 42
    //   35: aload_3
    //   36: astore #5
    //   38: aload_3
    //   39: ifnonnull -> 47
    //   42: ldc_w 'android.app.Application'
    //   45: astore #5
    //   47: aload #4
    //   49: astore_3
    //   50: aload_0
    //   51: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   54: astore #6
    //   56: aload #4
    //   58: astore_3
    //   59: aload_0
    //   60: getfield mPackageName : Ljava/lang/String;
    //   63: ldc 'android'
    //   65: invokevirtual equals : (Ljava/lang/Object;)Z
    //   68: ifne -> 99
    //   71: aload #4
    //   73: astore_3
    //   74: ldc2_w 64
    //   77: ldc_w 'initializeJavaContextClassLoader'
    //   80: invokestatic traceBegin : (JLjava/lang/String;)V
    //   83: aload #4
    //   85: astore_3
    //   86: aload_0
    //   87: invokespecial initializeJavaContextClassLoader : ()V
    //   90: aload #4
    //   92: astore_3
    //   93: ldc2_w 64
    //   96: invokestatic traceEnd : (J)V
    //   99: aload #4
    //   101: astore_3
    //   102: aload_0
    //   103: invokevirtual getAssets : ()Landroid/content/res/AssetManager;
    //   106: iconst_0
    //   107: iconst_0
    //   108: invokevirtual getAssignedPackageIdentifiers : (ZZ)Landroid/util/SparseArray;
    //   111: astore #7
    //   113: iconst_0
    //   114: istore #8
    //   116: aload #4
    //   118: astore_3
    //   119: aload #7
    //   121: invokevirtual size : ()I
    //   124: istore #9
    //   126: iload #8
    //   128: iload #9
    //   130: if_icmpge -> 188
    //   133: aload #4
    //   135: astore_3
    //   136: aload #7
    //   138: iload #8
    //   140: invokevirtual keyAt : (I)I
    //   143: istore #10
    //   145: iload #10
    //   147: iconst_1
    //   148: if_icmpeq -> 182
    //   151: iload #10
    //   153: bipush #127
    //   155: if_icmpne -> 161
    //   158: goto -> 182
    //   161: aload #4
    //   163: astore_3
    //   164: aload_0
    //   165: aload #6
    //   167: aload #7
    //   169: iload #8
    //   171: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   174: checkcast java/lang/String
    //   177: iload #10
    //   179: invokespecial rewriteRValues : (Ljava/lang/ClassLoader;Ljava/lang/String;I)V
    //   182: iinc #8, 1
    //   185: goto -> 126
    //   188: aload #4
    //   190: astore_3
    //   191: aload_0
    //   192: getfield mActivityThread : Landroid/app/ActivityThread;
    //   195: aload_0
    //   196: invokestatic createAppContext : (Landroid/app/ActivityThread;Landroid/app/LoadedApk;)Landroid/app/ContextImpl;
    //   199: astore #7
    //   201: aload #4
    //   203: astore_3
    //   204: aload #7
    //   206: invokestatic handleNewApplication : (Landroid/content/Context;)V
    //   209: aload #4
    //   211: astore_3
    //   212: aload_0
    //   213: getfield mActivityThread : Landroid/app/ActivityThread;
    //   216: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   219: aload #6
    //   221: aload #5
    //   223: aload #7
    //   225: invokevirtual newApplication : (Ljava/lang/ClassLoader;Ljava/lang/String;Landroid/content/Context;)Landroid/app/Application;
    //   228: astore #4
    //   230: aload #4
    //   232: astore_3
    //   233: aload #7
    //   235: aload #4
    //   237: invokevirtual setOuterContext : (Landroid/content/Context;)V
    //   240: aload #4
    //   242: astore_3
    //   243: goto -> 264
    //   246: astore #4
    //   248: aload_0
    //   249: getfield mActivityThread : Landroid/app/ActivityThread;
    //   252: getfield mInstrumentation : Landroid/app/Instrumentation;
    //   255: aload_3
    //   256: aload #4
    //   258: invokevirtual onException : (Ljava/lang/Object;Ljava/lang/Throwable;)Z
    //   261: ifeq -> 382
    //   264: aload_0
    //   265: getfield mActivityThread : Landroid/app/ActivityThread;
    //   268: getfield mAllApplications : Ljava/util/ArrayList;
    //   271: aload_3
    //   272: invokevirtual add : (Ljava/lang/Object;)Z
    //   275: pop
    //   276: aload_0
    //   277: aload_3
    //   278: putfield mApplication : Landroid/app/Application;
    //   281: aload_2
    //   282: ifnull -> 374
    //   285: aload_2
    //   286: aload_3
    //   287: invokevirtual callApplicationOnCreate : (Landroid/app/Application;)V
    //   290: goto -> 374
    //   293: astore #5
    //   295: aload_2
    //   296: aload_3
    //   297: aload #5
    //   299: invokevirtual onException : (Ljava/lang/Object;Ljava/lang/Throwable;)Z
    //   302: ifeq -> 308
    //   305: goto -> 374
    //   308: ldc2_w 64
    //   311: invokestatic traceEnd : (J)V
    //   314: new java/lang/StringBuilder
    //   317: dup
    //   318: invokespecial <init> : ()V
    //   321: astore_2
    //   322: aload_2
    //   323: ldc_w 'Unable to create application '
    //   326: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   329: pop
    //   330: aload_2
    //   331: aload_3
    //   332: invokevirtual getClass : ()Ljava/lang/Class;
    //   335: invokevirtual getName : ()Ljava/lang/String;
    //   338: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: pop
    //   342: aload_2
    //   343: ldc_w ': '
    //   346: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: pop
    //   350: aload_2
    //   351: aload #5
    //   353: invokevirtual toString : ()Ljava/lang/String;
    //   356: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   359: pop
    //   360: new java/lang/RuntimeException
    //   363: dup
    //   364: aload_2
    //   365: invokevirtual toString : ()Ljava/lang/String;
    //   368: aload #5
    //   370: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   373: athrow
    //   374: ldc2_w 64
    //   377: invokestatic traceEnd : (J)V
    //   380: aload_3
    //   381: areturn
    //   382: ldc2_w 64
    //   385: invokestatic traceEnd : (J)V
    //   388: new java/lang/StringBuilder
    //   391: dup
    //   392: invokespecial <init> : ()V
    //   395: astore_2
    //   396: aload_2
    //   397: ldc_w 'Unable to instantiate application '
    //   400: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: pop
    //   404: aload_2
    //   405: aload #5
    //   407: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: pop
    //   411: aload_2
    //   412: ldc_w ': '
    //   415: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   418: pop
    //   419: aload_2
    //   420: aload #4
    //   422: invokevirtual toString : ()Ljava/lang/String;
    //   425: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   428: pop
    //   429: new java/lang/RuntimeException
    //   432: dup
    //   433: aload_2
    //   434: invokevirtual toString : ()Ljava/lang/String;
    //   437: aload #4
    //   439: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   442: athrow
    // Exception table:
    //   from	to	target	type
    //   50	56	246	java/lang/Exception
    //   59	71	246	java/lang/Exception
    //   74	83	246	java/lang/Exception
    //   86	90	246	java/lang/Exception
    //   93	99	246	java/lang/Exception
    //   102	113	246	java/lang/Exception
    //   119	126	246	java/lang/Exception
    //   136	145	246	java/lang/Exception
    //   164	182	246	java/lang/Exception
    //   191	201	246	java/lang/Exception
    //   204	209	246	java/lang/Exception
    //   212	230	246	java/lang/Exception
    //   233	240	246	java/lang/Exception
    //   285	290	293	java/lang/Exception
  }
  
  public void removeContextRegistrations(Context paramContext, String paramString1, String paramString2) {
    boolean bool = StrictMode.vmRegistrationLeaksEnabled();
    synchronized (this.mReceivers) {
      ArrayMap arrayMap = (ArrayMap)this.mReceivers.remove(paramContext);
      if (arrayMap != null) {
        byte b = 0;
        while (b < arrayMap.size()) {
          ReceiverDispatcher receiverDispatcher = (ReceiverDispatcher)arrayMap.valueAt(b);
          IntentReceiverLeaked intentReceiverLeaked = new IntentReceiverLeaked();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(paramString2);
          stringBuilder.append(" ");
          stringBuilder.append(paramString1);
          stringBuilder.append(" has leaked IntentReceiver ");
          stringBuilder.append(receiverDispatcher.getIntentReceiver());
          stringBuilder.append(" that was originally registered here. Are you missing a call to unregisterReceiver()?");
          this(stringBuilder.toString());
          intentReceiverLeaked.setStackTrace(receiverDispatcher.getLocation().getStackTrace());
          Slog.e("ActivityThread", intentReceiverLeaked.getMessage(), (Throwable)intentReceiverLeaked);
          if (bool)
            StrictMode.onIntentReceiverLeaked((Throwable)intentReceiverLeaked); 
          try {
            ActivityManager.getService().unregisterReceiver(receiverDispatcher.getIIntentReceiver());
            b++;
          } catch (RemoteException null) {
            throw null.rethrowFromSystemServer();
          } 
        } 
      } 
      this.mUnregisteredReceivers.remove(null);
      synchronized (this.mServices) {
        arrayMap = (ArrayMap)this.mServices.remove(null);
        if (arrayMap != null) {
          byte b = 0;
          while (b < arrayMap.size()) {
            ServiceDispatcher serviceDispatcher = (ServiceDispatcher)arrayMap.valueAt(b);
            ServiceConnectionLeaked serviceConnectionLeaked = new ServiceConnectionLeaked();
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append(paramString2);
            stringBuilder.append(" ");
            stringBuilder.append(paramString1);
            stringBuilder.append(" has leaked ServiceConnection ");
            stringBuilder.append(serviceDispatcher.getServiceConnection());
            stringBuilder.append(" that was originally bound here");
            this(stringBuilder.toString());
            serviceConnectionLeaked.setStackTrace(serviceDispatcher.getLocation().getStackTrace());
            Slog.e("ActivityThread", serviceConnectionLeaked.getMessage(), (Throwable)serviceConnectionLeaked);
            if (bool)
              StrictMode.onServiceConnectionLeaked((Throwable)serviceConnectionLeaked); 
            try {
              ActivityManager.getService().unbindService(serviceDispatcher.getIServiceConnection());
              serviceDispatcher.doForget();
              b++;
            } catch (RemoteException remoteException) {
              throw remoteException.rethrowFromSystemServer();
            } 
          } 
        } 
        this.mUnboundServices.remove(remoteException);
        return;
      } 
    } 
  }
  
  public void setCompatibilityInfo(CompatibilityInfo paramCompatibilityInfo) {
    this.mDisplayAdjustments.setCompatibilityInfo(paramCompatibilityInfo);
  }
  
  public void updateApplicationInfo(ApplicationInfo paramApplicationInfo, List<String> paramList) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial setApplicationInfo : (Landroid/content/pm/ApplicationInfo;)V
    //   5: new java/util/ArrayList
    //   8: dup
    //   9: invokespecial <init> : ()V
    //   12: astore_3
    //   13: aload_0
    //   14: getfield mActivityThread : Landroid/app/ActivityThread;
    //   17: aload_1
    //   18: aload_3
    //   19: invokestatic makePaths : (Landroid/app/ActivityThread;Landroid/content/pm/ApplicationInfo;Ljava/util/List;)V
    //   22: new java/util/ArrayList
    //   25: dup
    //   26: aload_3
    //   27: invokeinterface size : ()I
    //   32: invokespecial <init> : (I)V
    //   35: astore #4
    //   37: aload_2
    //   38: ifnull -> 171
    //   41: aload_3
    //   42: invokeinterface iterator : ()Ljava/util/Iterator;
    //   47: astore #5
    //   49: aload #5
    //   51: invokeinterface hasNext : ()Z
    //   56: ifeq -> 168
    //   59: aload #5
    //   61: invokeinterface next : ()Ljava/lang/Object;
    //   66: checkcast java/lang/String
    //   69: astore #6
    //   71: aload #6
    //   73: aload #6
    //   75: getstatic java/io/File.separator : Ljava/lang/String;
    //   78: invokevirtual lastIndexOf : (Ljava/lang/String;)I
    //   81: invokevirtual substring : (I)Ljava/lang/String;
    //   84: astore #7
    //   86: iconst_0
    //   87: istore #8
    //   89: aload_2
    //   90: invokeinterface iterator : ()Ljava/util/Iterator;
    //   95: astore_3
    //   96: iload #8
    //   98: istore #9
    //   100: aload_3
    //   101: invokeinterface hasNext : ()Z
    //   106: ifeq -> 150
    //   109: aload_3
    //   110: invokeinterface next : ()Ljava/lang/Object;
    //   115: checkcast java/lang/String
    //   118: astore #10
    //   120: aload #7
    //   122: aload #10
    //   124: aload #10
    //   126: getstatic java/io/File.separator : Ljava/lang/String;
    //   129: invokevirtual lastIndexOf : (Ljava/lang/String;)I
    //   132: invokevirtual substring : (I)Ljava/lang/String;
    //   135: invokevirtual equals : (Ljava/lang/Object;)Z
    //   138: ifeq -> 147
    //   141: iconst_1
    //   142: istore #9
    //   144: goto -> 150
    //   147: goto -> 96
    //   150: iload #9
    //   152: ifne -> 165
    //   155: aload #4
    //   157: aload #6
    //   159: invokeinterface add : (Ljava/lang/Object;)Z
    //   164: pop
    //   165: goto -> 49
    //   168: goto -> 180
    //   171: aload #4
    //   173: aload_3
    //   174: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   179: pop
    //   180: aload_0
    //   181: monitorenter
    //   182: aload_0
    //   183: aload #4
    //   185: invokespecial createOrUpdateClassLoaderLocked : (Ljava/util/List;)V
    //   188: aload_0
    //   189: getfield mResources : Landroid/content/res/Resources;
    //   192: astore_2
    //   193: aload_2
    //   194: ifnull -> 305
    //   197: aload_0
    //   198: aconst_null
    //   199: invokevirtual getSplitPaths : (Ljava/lang/String;)[Ljava/lang/String;
    //   202: astore #4
    //   204: invokestatic getInstance : ()Landroid/app/ResourcesManager;
    //   207: astore_3
    //   208: aload_0
    //   209: getfield mResDir : Ljava/lang/String;
    //   212: astore #5
    //   214: aload_0
    //   215: getfield mOverlayDirs : [Ljava/lang/String;
    //   218: astore #7
    //   220: aload_0
    //   221: getfield mApplicationInfo : Landroid/content/pm/ApplicationInfo;
    //   224: getfield sharedLibraryFiles : [Ljava/lang/String;
    //   227: astore #11
    //   229: aload_0
    //   230: invokevirtual getCompatibilityInfo : ()Landroid/content/res/CompatibilityInfo;
    //   233: astore #10
    //   235: aload_0
    //   236: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   239: astore #6
    //   241: aload_0
    //   242: getfield mApplication : Landroid/app/Application;
    //   245: ifnonnull -> 253
    //   248: aconst_null
    //   249: astore_2
    //   250: goto -> 264
    //   253: aload_0
    //   254: getfield mApplication : Landroid/app/Application;
    //   257: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   260: invokevirtual getLoaders : ()Ljava/util/List;
    //   263: astore_2
    //   264: aload_0
    //   265: aload_3
    //   266: aconst_null
    //   267: aload #5
    //   269: aload #4
    //   271: aload #7
    //   273: aload #11
    //   275: iconst_0
    //   276: aconst_null
    //   277: aload #10
    //   279: aload #6
    //   281: aload_2
    //   282: invokevirtual getResources : (Landroid/os/IBinder;Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;ILandroid/content/res/Configuration;Landroid/content/res/CompatibilityInfo;Ljava/lang/ClassLoader;Ljava/util/List;)Landroid/content/res/Resources;
    //   285: putfield mResources : Landroid/content/res/Resources;
    //   288: goto -> 305
    //   291: astore_1
    //   292: new java/lang/AssertionError
    //   295: astore_1
    //   296: aload_1
    //   297: ldc_w 'null split not found'
    //   300: invokespecial <init> : (Ljava/lang/Object;)V
    //   303: aload_1
    //   304: athrow
    //   305: aload_0
    //   306: monitorexit
    //   307: aload_0
    //   308: aload_0
    //   309: aload_1
    //   310: aload_0
    //   311: getfield mDefaultClassLoader : Ljava/lang/ClassLoader;
    //   314: invokespecial createAppFactory : (Landroid/content/pm/ApplicationInfo;Ljava/lang/ClassLoader;)Landroid/app/AppComponentFactory;
    //   317: putfield mAppComponentFactory : Landroid/app/AppComponentFactory;
    //   320: return
    //   321: astore_1
    //   322: aload_0
    //   323: monitorexit
    //   324: aload_1
    //   325: athrow
    // Exception table:
    //   from	to	target	type
    //   182	193	321	finally
    //   197	204	291	android/content/pm/PackageManager$NameNotFoundException
    //   197	204	321	finally
    //   204	248	321	finally
    //   253	264	321	finally
    //   264	288	321	finally
    //   292	305	321	finally
    //   305	307	321	finally
    //   322	324	321	finally
  }
  
  static final class ReceiverDispatcher {
    final Handler mActivityThread;
    
    final Context mContext;
    
    boolean mForgotten;
    
    final IIntentReceiver.Stub mIIntentReceiver;
    
    final Instrumentation mInstrumentation;
    
    final IntentReceiverLeaked mLocation;
    
    final BroadcastReceiver mReceiver;
    
    final boolean mRegistered;
    
    RuntimeException mUnregisterLocation;
    
    ReceiverDispatcher(BroadcastReceiver param1BroadcastReceiver, Context param1Context, Handler param1Handler, Instrumentation param1Instrumentation, boolean param1Boolean) {
      if (param1Handler != null) {
        this.mIIntentReceiver = new InnerReceiver(this, param1Boolean ^ true);
        this.mReceiver = param1BroadcastReceiver;
        this.mContext = param1Context;
        this.mActivityThread = param1Handler;
        this.mInstrumentation = param1Instrumentation;
        this.mRegistered = param1Boolean;
        IntentReceiverLeaked intentReceiverLeaked = new IntentReceiverLeaked(null);
        this.mLocation = intentReceiverLeaked;
        intentReceiverLeaked.fillInStackTrace();
        return;
      } 
      throw new NullPointerException("Handler must not be null");
    }
    
    IIntentReceiver getIIntentReceiver() {
      return (IIntentReceiver)this.mIIntentReceiver;
    }
    
    BroadcastReceiver getIntentReceiver() {
      return this.mReceiver;
    }
    
    IntentReceiverLeaked getLocation() {
      return this.mLocation;
    }
    
    RuntimeException getUnregisterLocation() {
      return this.mUnregisterLocation;
    }
    
    public void performReceive(Intent param1Intent, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean1, boolean param1Boolean2, int param1Int2) {
      Args args = new Args(param1Intent, param1Int1, param1String, param1Bundle, param1Boolean1, param1Boolean2, param1Int2);
      if (param1Intent == null)
        Log.wtf("LoadedApk", "Null intent received"); 
      if ((param1Intent == null || !this.mActivityThread.post(args.getRunnable())) && this.mRegistered && param1Boolean1)
        args.sendFinished(ActivityManager.getService()); 
    }
    
    void setUnregisterLocation(RuntimeException param1RuntimeException) {
      this.mUnregisterLocation = param1RuntimeException;
    }
    
    void validate(Context param1Context, Handler param1Handler) {
      StringBuilder stringBuilder1;
      if (this.mContext == param1Context) {
        if (this.mActivityThread == param1Handler)
          return; 
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Receiver ");
        stringBuilder1.append(this.mReceiver);
        stringBuilder1.append(" registered with differing handler (was ");
        stringBuilder1.append(this.mActivityThread);
        stringBuilder1.append(" now ");
        stringBuilder1.append(param1Handler);
        stringBuilder1.append(")");
        throw new IllegalStateException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Receiver ");
      stringBuilder2.append(this.mReceiver);
      stringBuilder2.append(" registered with differing Context (was ");
      stringBuilder2.append(this.mContext);
      stringBuilder2.append(" now ");
      stringBuilder2.append(stringBuilder1);
      stringBuilder2.append(")");
      throw new IllegalStateException(stringBuilder2.toString());
    }
    
    final class Args extends BroadcastReceiver.PendingResult {
      private Intent mCurIntent;
      
      private boolean mDispatched;
      
      private final boolean mOrdered;
      
      private boolean mRunCalled;
      
      public Args(Intent param2Intent, int param2Int1, String param2String, Bundle param2Bundle, boolean param2Boolean1, boolean param2Boolean2, int param2Int2) {
        super(param2Int1, param2String, param2Bundle, b, param2Boolean1, param2Boolean2, LoadedApk.ReceiverDispatcher.this.mIIntentReceiver.asBinder(), param2Int2, param2Intent.getFlags());
        byte b;
        this.mCurIntent = param2Intent;
        this.mOrdered = param2Boolean1;
      }
      
      public final Runnable getRunnable() {
        return new _$$Lambda$LoadedApk$ReceiverDispatcher$Args$_BumDX2UKsnxLVrE6UJsJZkotuA(this);
      }
    }
    
    static final class InnerReceiver extends IIntentReceiver.Stub {
      final WeakReference<LoadedApk.ReceiverDispatcher> mDispatcher;
      
      final LoadedApk.ReceiverDispatcher mStrongRef;
      
      InnerReceiver(LoadedApk.ReceiverDispatcher param2ReceiverDispatcher, boolean param2Boolean) {
        this.mDispatcher = new WeakReference<>(param2ReceiverDispatcher);
        if (!param2Boolean)
          param2ReceiverDispatcher = null; 
        this.mStrongRef = param2ReceiverDispatcher;
      }
      
      public void performReceive(Intent param2Intent, int param2Int1, String param2String, Bundle param2Bundle, boolean param2Boolean1, boolean param2Boolean2, int param2Int2) {
        LoadedApk.ReceiverDispatcher receiverDispatcher;
        if (param2Intent == null) {
          Log.wtf("LoadedApk", "Null intent received");
          receiverDispatcher = null;
        } else {
          receiverDispatcher = this.mDispatcher.get();
        } 
        if (receiverDispatcher != null) {
          receiverDispatcher.performReceive(param2Intent, param2Int1, param2String, param2Bundle, param2Boolean1, param2Boolean2, param2Int2);
        } else {
          IActivityManager iActivityManager = ActivityManager.getService();
          if (param2Bundle != null)
            try {
              param2Bundle.setAllowFds(false);
              iActivityManager.finishReceiver((IBinder)this, param2Int1, param2String, param2Bundle, false, param2Intent.getFlags());
            } catch (RemoteException remoteException) {
              throw remoteException.rethrowFromSystemServer();
            }  
          iActivityManager.finishReceiver((IBinder)this, param2Int1, param2String, param2Bundle, false, remoteException.getFlags());
        } 
      }
    }
  }
  
  final class Args extends BroadcastReceiver.PendingResult {
    private Intent mCurIntent;
    
    private boolean mDispatched;
    
    private final boolean mOrdered;
    
    private boolean mRunCalled;
    
    public Args(Intent param1Intent, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean1, boolean param1Boolean2, int param1Int2) {
      super(param1Int1, param1String, param1Bundle, b, param1Boolean1, param1Boolean2, ((LoadedApk.ReceiverDispatcher)LoadedApk.this).mIIntentReceiver.asBinder(), param1Int2, param1Intent.getFlags());
      byte b;
      this.mCurIntent = param1Intent;
      this.mOrdered = param1Boolean1;
    }
    
    public final Runnable getRunnable() {
      return new _$$Lambda$LoadedApk$ReceiverDispatcher$Args$_BumDX2UKsnxLVrE6UJsJZkotuA(this);
    }
  }
  
  static final class InnerReceiver extends IIntentReceiver.Stub {
    final WeakReference<LoadedApk.ReceiverDispatcher> mDispatcher;
    
    final LoadedApk.ReceiverDispatcher mStrongRef;
    
    InnerReceiver(LoadedApk.ReceiverDispatcher param1ReceiverDispatcher, boolean param1Boolean) {
      this.mDispatcher = new WeakReference<>(param1ReceiverDispatcher);
      if (!param1Boolean)
        param1ReceiverDispatcher = null; 
      this.mStrongRef = param1ReceiverDispatcher;
    }
    
    public void performReceive(Intent param1Intent, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean1, boolean param1Boolean2, int param1Int2) {
      LoadedApk.ReceiverDispatcher receiverDispatcher;
      if (param1Intent == null) {
        Log.wtf("LoadedApk", "Null intent received");
        receiverDispatcher = null;
      } else {
        receiverDispatcher = this.mDispatcher.get();
      } 
      if (receiverDispatcher != null) {
        receiverDispatcher.performReceive(param1Intent, param1Int1, param1String, param1Bundle, param1Boolean1, param1Boolean2, param1Int2);
      } else {
        IActivityManager iActivityManager = ActivityManager.getService();
        if (param1Bundle != null)
          try {
            param1Bundle.setAllowFds(false);
            iActivityManager.finishReceiver((IBinder)this, param1Int1, param1String, param1Bundle, false, param1Intent.getFlags());
          } catch (RemoteException remoteException) {
            throw remoteException.rethrowFromSystemServer();
          }  
        iActivityManager.finishReceiver((IBinder)this, param1Int1, param1String, param1Bundle, false, remoteException.getFlags());
      } 
    }
  }
  
  static final class ServiceDispatcher {
    private final ArrayMap<ComponentName, ConnectionInfo> mActiveConnections = new ArrayMap();
    
    private final Executor mActivityExecutor;
    
    private final Handler mActivityThread;
    
    private final ServiceConnection mConnection;
    
    private final Context mContext;
    
    private final int mFlags;
    
    private boolean mForgotten;
    
    private final InnerConnection mIServiceConnection = new InnerConnection(this);
    
    private final ServiceConnectionLeaked mLocation;
    
    private RuntimeException mUnbindLocation;
    
    ServiceDispatcher(ServiceConnection param1ServiceConnection, Context param1Context, Handler param1Handler, int param1Int) {
      this.mConnection = param1ServiceConnection;
      this.mContext = param1Context;
      this.mActivityThread = param1Handler;
      this.mActivityExecutor = null;
      ServiceConnectionLeaked serviceConnectionLeaked = new ServiceConnectionLeaked(null);
      this.mLocation = serviceConnectionLeaked;
      serviceConnectionLeaked.fillInStackTrace();
      this.mFlags = param1Int;
    }
    
    ServiceDispatcher(ServiceConnection param1ServiceConnection, Context param1Context, Executor param1Executor, int param1Int) {
      this.mConnection = param1ServiceConnection;
      this.mContext = param1Context;
      this.mActivityThread = null;
      this.mActivityExecutor = param1Executor;
      ServiceConnectionLeaked serviceConnectionLeaked = new ServiceConnectionLeaked(null);
      this.mLocation = serviceConnectionLeaked;
      serviceConnectionLeaked.fillInStackTrace();
      this.mFlags = param1Int;
    }
    
    public void connected(ComponentName param1ComponentName, IBinder param1IBinder, boolean param1Boolean) {
      Executor executor = this.mActivityExecutor;
      if (executor != null) {
        executor.execute(new RunConnection(param1ComponentName, param1IBinder, 0, param1Boolean));
      } else {
        Handler handler = this.mActivityThread;
        if (handler != null) {
          handler.post(new RunConnection(param1ComponentName, param1IBinder, 0, param1Boolean));
        } else {
          doConnected(param1ComponentName, param1IBinder, param1Boolean);
        } 
      } 
    }
    
    public void death(ComponentName param1ComponentName, IBinder param1IBinder) {
      Executor executor = this.mActivityExecutor;
      if (executor != null) {
        executor.execute(new RunConnection(param1ComponentName, param1IBinder, 1, false));
      } else {
        Handler handler = this.mActivityThread;
        if (handler != null) {
          handler.post(new RunConnection(param1ComponentName, param1IBinder, 1, false));
        } else {
          doDeath(param1ComponentName, param1IBinder);
        } 
      } 
    }
    
    public void doConnected(ComponentName param1ComponentName, IBinder param1IBinder, boolean param1Boolean) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mForgotten : Z
      //   6: ifeq -> 12
      //   9: aload_0
      //   10: monitorexit
      //   11: return
      //   12: aload_0
      //   13: getfield mActiveConnections : Landroid/util/ArrayMap;
      //   16: aload_1
      //   17: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   20: checkcast android/app/LoadedApk$ServiceDispatcher$ConnectionInfo
      //   23: astore #4
      //   25: aload #4
      //   27: ifnull -> 42
      //   30: aload #4
      //   32: getfield binder : Landroid/os/IBinder;
      //   35: aload_2
      //   36: if_acmpne -> 42
      //   39: aload_0
      //   40: monitorexit
      //   41: return
      //   42: aload_2
      //   43: ifnull -> 122
      //   46: new android/app/LoadedApk$ServiceDispatcher$ConnectionInfo
      //   49: astore #5
      //   51: aload #5
      //   53: aconst_null
      //   54: invokespecial <init> : (Landroid/app/LoadedApk$1;)V
      //   57: aload #5
      //   59: aload_2
      //   60: putfield binder : Landroid/os/IBinder;
      //   63: new android/app/LoadedApk$ServiceDispatcher$DeathMonitor
      //   66: astore #6
      //   68: aload #6
      //   70: aload_0
      //   71: aload_1
      //   72: aload_2
      //   73: invokespecial <init> : (Landroid/app/LoadedApk$ServiceDispatcher;Landroid/content/ComponentName;Landroid/os/IBinder;)V
      //   76: aload #5
      //   78: aload #6
      //   80: putfield deathMonitor : Landroid/os/IBinder$DeathRecipient;
      //   83: aload_2
      //   84: aload #5
      //   86: getfield deathMonitor : Landroid/os/IBinder$DeathRecipient;
      //   89: iconst_0
      //   90: invokeinterface linkToDeath : (Landroid/os/IBinder$DeathRecipient;I)V
      //   95: aload_0
      //   96: getfield mActiveConnections : Landroid/util/ArrayMap;
      //   99: aload_1
      //   100: aload #5
      //   102: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   105: pop
      //   106: goto -> 131
      //   109: astore_2
      //   110: aload_0
      //   111: getfield mActiveConnections : Landroid/util/ArrayMap;
      //   114: aload_1
      //   115: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
      //   118: pop
      //   119: aload_0
      //   120: monitorexit
      //   121: return
      //   122: aload_0
      //   123: getfield mActiveConnections : Landroid/util/ArrayMap;
      //   126: aload_1
      //   127: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
      //   130: pop
      //   131: aload #4
      //   133: ifnull -> 153
      //   136: aload #4
      //   138: getfield binder : Landroid/os/IBinder;
      //   141: aload #4
      //   143: getfield deathMonitor : Landroid/os/IBinder$DeathRecipient;
      //   146: iconst_0
      //   147: invokeinterface unlinkToDeath : (Landroid/os/IBinder$DeathRecipient;I)Z
      //   152: pop
      //   153: aload_0
      //   154: monitorexit
      //   155: aload #4
      //   157: ifnull -> 170
      //   160: aload_0
      //   161: getfield mConnection : Landroid/content/ServiceConnection;
      //   164: aload_1
      //   165: invokeinterface onServiceDisconnected : (Landroid/content/ComponentName;)V
      //   170: iload_3
      //   171: ifeq -> 184
      //   174: aload_0
      //   175: getfield mConnection : Landroid/content/ServiceConnection;
      //   178: aload_1
      //   179: invokeinterface onBindingDied : (Landroid/content/ComponentName;)V
      //   184: aload_2
      //   185: ifnull -> 202
      //   188: aload_0
      //   189: getfield mConnection : Landroid/content/ServiceConnection;
      //   192: aload_1
      //   193: aload_2
      //   194: invokeinterface onServiceConnected : (Landroid/content/ComponentName;Landroid/os/IBinder;)V
      //   199: goto -> 212
      //   202: aload_0
      //   203: getfield mConnection : Landroid/content/ServiceConnection;
      //   206: aload_1
      //   207: invokeinterface onNullBinding : (Landroid/content/ComponentName;)V
      //   212: return
      //   213: astore_1
      //   214: aload_0
      //   215: monitorexit
      //   216: aload_1
      //   217: athrow
      // Exception table:
      //   from	to	target	type
      //   2	11	213	finally
      //   12	25	213	finally
      //   30	41	213	finally
      //   46	83	213	finally
      //   83	106	109	android/os/RemoteException
      //   83	106	213	finally
      //   110	121	213	finally
      //   122	131	213	finally
      //   136	153	213	finally
      //   153	155	213	finally
      //   214	216	213	finally
    }
    
    public void doDeath(ComponentName param1ComponentName, IBinder param1IBinder) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mActiveConnections : Landroid/util/ArrayMap;
      //   6: aload_1
      //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   10: checkcast android/app/LoadedApk$ServiceDispatcher$ConnectionInfo
      //   13: astore_3
      //   14: aload_3
      //   15: ifnull -> 66
      //   18: aload_3
      //   19: getfield binder : Landroid/os/IBinder;
      //   22: aload_2
      //   23: if_acmpeq -> 29
      //   26: goto -> 66
      //   29: aload_0
      //   30: getfield mActiveConnections : Landroid/util/ArrayMap;
      //   33: aload_1
      //   34: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
      //   37: pop
      //   38: aload_3
      //   39: getfield binder : Landroid/os/IBinder;
      //   42: aload_3
      //   43: getfield deathMonitor : Landroid/os/IBinder$DeathRecipient;
      //   46: iconst_0
      //   47: invokeinterface unlinkToDeath : (Landroid/os/IBinder$DeathRecipient;I)Z
      //   52: pop
      //   53: aload_0
      //   54: monitorexit
      //   55: aload_0
      //   56: getfield mConnection : Landroid/content/ServiceConnection;
      //   59: aload_1
      //   60: invokeinterface onServiceDisconnected : (Landroid/content/ComponentName;)V
      //   65: return
      //   66: aload_0
      //   67: monitorexit
      //   68: return
      //   69: astore_1
      //   70: aload_0
      //   71: monitorexit
      //   72: aload_1
      //   73: athrow
      // Exception table:
      //   from	to	target	type
      //   2	14	69	finally
      //   18	26	69	finally
      //   29	53	69	finally
      //   53	55	69	finally
      //   66	68	69	finally
      //   70	72	69	finally
    }
    
    void doForget() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: iconst_0
      //   3: istore_1
      //   4: iload_1
      //   5: aload_0
      //   6: getfield mActiveConnections : Landroid/util/ArrayMap;
      //   9: invokevirtual size : ()I
      //   12: if_icmpge -> 48
      //   15: aload_0
      //   16: getfield mActiveConnections : Landroid/util/ArrayMap;
      //   19: iload_1
      //   20: invokevirtual valueAt : (I)Ljava/lang/Object;
      //   23: checkcast android/app/LoadedApk$ServiceDispatcher$ConnectionInfo
      //   26: astore_2
      //   27: aload_2
      //   28: getfield binder : Landroid/os/IBinder;
      //   31: aload_2
      //   32: getfield deathMonitor : Landroid/os/IBinder$DeathRecipient;
      //   35: iconst_0
      //   36: invokeinterface unlinkToDeath : (Landroid/os/IBinder$DeathRecipient;I)Z
      //   41: pop
      //   42: iinc #1, 1
      //   45: goto -> 4
      //   48: aload_0
      //   49: getfield mActiveConnections : Landroid/util/ArrayMap;
      //   52: invokevirtual clear : ()V
      //   55: aload_0
      //   56: iconst_1
      //   57: putfield mForgotten : Z
      //   60: aload_0
      //   61: monitorexit
      //   62: return
      //   63: astore_2
      //   64: aload_0
      //   65: monitorexit
      //   66: aload_2
      //   67: athrow
      // Exception table:
      //   from	to	target	type
      //   4	42	63	finally
      //   48	62	63	finally
      //   64	66	63	finally
    }
    
    int getFlags() {
      return this.mFlags;
    }
    
    IServiceConnection getIServiceConnection() {
      return this.mIServiceConnection;
    }
    
    ServiceConnectionLeaked getLocation() {
      return this.mLocation;
    }
    
    ServiceConnection getServiceConnection() {
      return this.mConnection;
    }
    
    RuntimeException getUnbindLocation() {
      return this.mUnbindLocation;
    }
    
    void setUnbindLocation(RuntimeException param1RuntimeException) {
      this.mUnbindLocation = param1RuntimeException;
    }
    
    void validate(Context param1Context, Handler param1Handler, Executor param1Executor) {
      StringBuilder stringBuilder1;
      if (this.mContext == param1Context) {
        if (this.mActivityThread == param1Handler) {
          if (this.mActivityExecutor == param1Executor)
            return; 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("ServiceConnection ");
          stringBuilder.append(this.mConnection);
          stringBuilder.append(" registered with differing executor (was ");
          stringBuilder.append(this.mActivityExecutor);
          stringBuilder.append(" now ");
          stringBuilder.append(param1Executor);
          stringBuilder.append(")");
          throw new RuntimeException(stringBuilder.toString());
        } 
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("ServiceConnection ");
        stringBuilder1.append(this.mConnection);
        stringBuilder1.append(" registered with differing handler (was ");
        stringBuilder1.append(this.mActivityThread);
        stringBuilder1.append(" now ");
        stringBuilder1.append(param1Handler);
        stringBuilder1.append(")");
        throw new RuntimeException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("ServiceConnection ");
      stringBuilder2.append(this.mConnection);
      stringBuilder2.append(" registered with differing Context (was ");
      stringBuilder2.append(this.mContext);
      stringBuilder2.append(" now ");
      stringBuilder2.append(stringBuilder1);
      stringBuilder2.append(")");
      throw new RuntimeException(stringBuilder2.toString());
    }
    
    private static class ConnectionInfo {
      IBinder binder;
      
      IBinder.DeathRecipient deathMonitor;
      
      private ConnectionInfo() {}
    }
    
    private final class DeathMonitor implements IBinder.DeathRecipient {
      final ComponentName mName;
      
      final IBinder mService;
      
      DeathMonitor(ComponentName param2ComponentName, IBinder param2IBinder) {
        this.mName = param2ComponentName;
        this.mService = param2IBinder;
      }
      
      public void binderDied() {
        LoadedApk.ServiceDispatcher.this.death(this.mName, this.mService);
      }
    }
    
    private static class InnerConnection extends IServiceConnection.Stub {
      final WeakReference<LoadedApk.ServiceDispatcher> mDispatcher;
      
      InnerConnection(LoadedApk.ServiceDispatcher param2ServiceDispatcher) {
        this.mDispatcher = new WeakReference<>(param2ServiceDispatcher);
      }
      
      public void connected(ComponentName param2ComponentName, IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        LoadedApk.ServiceDispatcher serviceDispatcher = this.mDispatcher.get();
        if (serviceDispatcher != null)
          serviceDispatcher.connected(param2ComponentName, param2IBinder, param2Boolean); 
      }
    }
    
    private final class RunConnection implements Runnable {
      final int mCommand;
      
      final boolean mDead;
      
      final ComponentName mName;
      
      final IBinder mService;
      
      RunConnection(ComponentName param2ComponentName, IBinder param2IBinder, int param2Int, boolean param2Boolean) {
        this.mName = param2ComponentName;
        this.mService = param2IBinder;
        this.mCommand = param2Int;
        this.mDead = param2Boolean;
      }
      
      public void run() {
        int i = this.mCommand;
        if (i == 0) {
          LoadedApk.ServiceDispatcher.this.doConnected(this.mName, this.mService, this.mDead);
        } else if (i == 1) {
          LoadedApk.ServiceDispatcher.this.doDeath(this.mName, this.mService);
        } 
      }
    }
  }
  
  private static class ConnectionInfo {
    IBinder binder;
    
    IBinder.DeathRecipient deathMonitor;
    
    private ConnectionInfo() {}
  }
  
  private final class DeathMonitor implements IBinder.DeathRecipient {
    final ComponentName mName;
    
    final IBinder mService;
    
    DeathMonitor(ComponentName param1ComponentName, IBinder param1IBinder) {
      this.mName = param1ComponentName;
      this.mService = param1IBinder;
    }
    
    public void binderDied() {
      this.this$0.death(this.mName, this.mService);
    }
  }
  
  private static class InnerConnection extends IServiceConnection.Stub {
    final WeakReference<LoadedApk.ServiceDispatcher> mDispatcher;
    
    InnerConnection(LoadedApk.ServiceDispatcher param1ServiceDispatcher) {
      this.mDispatcher = new WeakReference<>(param1ServiceDispatcher);
    }
    
    public void connected(ComponentName param1ComponentName, IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      LoadedApk.ServiceDispatcher serviceDispatcher = this.mDispatcher.get();
      if (serviceDispatcher != null)
        serviceDispatcher.connected(param1ComponentName, param1IBinder, param1Boolean); 
    }
  }
  
  private final class RunConnection implements Runnable {
    final int mCommand;
    
    final boolean mDead;
    
    final ComponentName mName;
    
    final IBinder mService;
    
    RunConnection(ComponentName param1ComponentName, IBinder param1IBinder, int param1Int, boolean param1Boolean) {
      this.mName = param1ComponentName;
      this.mService = param1IBinder;
      this.mCommand = param1Int;
      this.mDead = param1Boolean;
    }
    
    public void run() {
      int i = this.mCommand;
      if (i == 0) {
        this.this$0.doConnected(this.mName, this.mService, this.mDead);
      } else if (i == 1) {
        this.this$0.doDeath(this.mName, this.mService);
      } 
    }
  }
  
  private class SplitDependencyLoaderImpl extends SplitDependencyLoader<PackageManager.NameNotFoundException> {
    private final ClassLoader[] mCachedClassLoaders = new ClassLoader[LoadedApk.this.mSplitNames.length + 1];
    
    private final String[][] mCachedResourcePaths = new String[LoadedApk.this.mSplitNames.length + 1][];
    
    SplitDependencyLoaderImpl(SparseArray<int[]> param1SparseArray) {
      super(param1SparseArray);
    }
    
    private int ensureSplitLoaded(String param1String) throws PackageManager.NameNotFoundException {
      int i = 0;
      if (param1String != null) {
        i = Arrays.binarySearch((Object[])LoadedApk.this.mSplitNames, param1String);
        if (i >= 0) {
          i++;
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Split name '");
          stringBuilder.append(param1String);
          stringBuilder.append("' is not installed");
          throw new PackageManager.NameNotFoundException(stringBuilder.toString());
        } 
      } 
      loadDependenciesForSplit(i);
      return i;
    }
    
    protected void constructSplit(int param1Int1, int[] param1ArrayOfint, int param1Int2) throws PackageManager.NameNotFoundException {
      ArrayList<String> arrayList = new ArrayList();
      int i = 0;
      if (param1Int1 == 0) {
        LoadedApk.this.createOrUpdateClassLoaderLocked(null);
        this.mCachedClassLoaders[0] = LoadedApk.this.mClassLoader;
        param1Int2 = param1ArrayOfint.length;
        for (param1Int1 = 0; param1Int1 < param1Int2; param1Int1++) {
          i = param1ArrayOfint[param1Int1];
          arrayList.add(LoadedApk.this.mSplitResDirs[i - 1]);
        } 
        this.mCachedResourcePaths[0] = arrayList.<String>toArray(new String[arrayList.size()]);
        return;
      } 
      ClassLoader[] arrayOfClassLoader = this.mCachedClassLoaders;
      ClassLoader classLoader = arrayOfClassLoader[param1Int2];
      arrayOfClassLoader[param1Int1] = ApplicationLoaders.getDefault().getClassLoader(LoadedApk.this.mSplitAppDirs[param1Int1 - 1], LoadedApk.this.getTargetSdkVersion(), false, null, null, classLoader, LoadedApk.this.mSplitClassLoaderNames[param1Int1 - 1]);
      Collections.addAll(arrayList, this.mCachedResourcePaths[param1Int2]);
      arrayList.add(LoadedApk.this.mSplitResDirs[param1Int1 - 1]);
      int j = param1ArrayOfint.length;
      for (param1Int2 = i; param1Int2 < j; param1Int2++) {
        i = param1ArrayOfint[param1Int2];
        arrayList.add(LoadedApk.this.mSplitResDirs[i - 1]);
      } 
      this.mCachedResourcePaths[param1Int1] = arrayList.<String>toArray(new String[arrayList.size()]);
    }
    
    ClassLoader getClassLoaderForSplit(String param1String) throws PackageManager.NameNotFoundException {
      return this.mCachedClassLoaders[ensureSplitLoaded(param1String)];
    }
    
    String[] getSplitPathsForSplit(String param1String) throws PackageManager.NameNotFoundException {
      return this.mCachedResourcePaths[ensureSplitLoaded(param1String)];
    }
    
    protected boolean isSplitCached(int param1Int) {
      boolean bool;
      if (this.mCachedClassLoaders[param1Int] != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
  }
  
  private static class WarningContextClassLoader extends ClassLoader {
    private static boolean warned = false;
    
    private WarningContextClassLoader() {}
    
    private void warn(String param1String) {
      if (warned)
        return; 
      warned = true;
      Thread.currentThread().setContextClassLoader(getParent());
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ClassLoader.");
      stringBuilder.append(param1String);
      stringBuilder.append(": The class loader returned by Thread.getContextClassLoader() may fail for processes that host multiple applications. You should explicitly specify a context class loader. For example: Thread.setContextClassLoader(getClass().getClassLoader());");
      Slog.w("ActivityThread", stringBuilder.toString());
    }
    
    public void clearAssertionStatus() {
      warn("clearAssertionStatus");
      getParent().clearAssertionStatus();
    }
    
    public URL getResource(String param1String) {
      warn("getResource");
      return getParent().getResource(param1String);
    }
    
    public InputStream getResourceAsStream(String param1String) {
      warn("getResourceAsStream");
      return getParent().getResourceAsStream(param1String);
    }
    
    public Enumeration<URL> getResources(String param1String) throws IOException {
      warn("getResources");
      return getParent().getResources(param1String);
    }
    
    public Class<?> loadClass(String param1String) throws ClassNotFoundException {
      warn("loadClass");
      return getParent().loadClass(param1String);
    }
    
    public void setClassAssertionStatus(String param1String, boolean param1Boolean) {
      warn("setClassAssertionStatus");
      getParent().setClassAssertionStatus(param1String, param1Boolean);
    }
    
    public void setDefaultAssertionStatus(boolean param1Boolean) {
      warn("setDefaultAssertionStatus");
      getParent().setDefaultAssertionStatus(param1Boolean);
    }
    
    public void setPackageAssertionStatus(String param1String, boolean param1Boolean) {
      warn("setPackageAssertionStatus");
      getParent().setPackageAssertionStatus(param1String, param1Boolean);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoadedApk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
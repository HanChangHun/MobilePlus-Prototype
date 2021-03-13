package android.app;

import android.content.AutofillOptions;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentCaptureOptions;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.IContentProvider;
import android.content.IIntentReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.CompatResources;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.loader.ResourcesLoader;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.FileUtils;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.Trace;
import android.os.UserHandle;
import android.permission.IPermissionManager;
import android.permission.PermissionManager;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import android.view.Display;
import android.view.DisplayAdjustments;
import android.view.autofill.AutofillManager;
import com.android.internal.util.Preconditions;
import dalvik.system.BlockGuard;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteOrder;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import libcore.io.Memory;

class ContextImpl extends Context {
  private static final boolean DEBUG = false;
  
  private static final String MY_TAG = "201521037";
  
  static final int STATE_INITIALIZING = 1;
  
  static final int STATE_NOT_FOUND = 3;
  
  static final int STATE_READY = 2;
  
  static final int STATE_UNINITIALIZED = 0;
  
  private static final String TAG = "ContextImpl";
  
  private static final String XATTR_INODE_CACHE = "user.inode_cache";
  
  private static final String XATTR_INODE_CODE_CACHE = "user.inode_code_cache";
  
  private static ArrayMap<String, ArrayMap<File, SharedPreferencesImpl>> sSharedPrefsCache;
  
  private final String mAttributionTag;
  
  private AutofillManager.AutofillClient mAutofillClient;
  
  private AutofillOptions mAutofillOptions;
  
  private final String mBasePackageName;
  
  private File mCacheDir;
  
  private ClassLoader mClassLoader;
  
  private File mCodeCacheDir;
  
  private ContentCaptureOptions mContentCaptureOptions;
  
  private final ApplicationContentResolver mContentResolver;
  
  private File mCratesDir;
  
  private File mDatabasesDir;
  
  private Display mDisplay;
  
  private File mFilesDir;
  
  private final int mFlags;
  
  private boolean mIsAssociatedWithDisplay;
  
  private boolean mIsSystemOrSystemUiContext;
  
  private boolean mIsUiContext;
  
  final ActivityThread mMainThread;
  
  private File mNoBackupFilesDir;
  
  private final String mOpPackageName;
  
  private Context mOuterContext;
  
  final LoadedApk mPackageInfo;
  
  private PackageManager mPackageManager;
  
  private File mPreferencesDir;
  
  private Context mReceiverRestrictedContext;
  
  private Resources mResources;
  
  private final ResourcesManager mResourcesManager;
  
  final Object[] mServiceCache;
  
  final int[] mServiceInitializationStateArray;
  
  private ArrayMap<String, File> mSharedPrefsPaths;
  
  private String mSplitName;
  
  private final Object mSync;
  
  private Resources.Theme mTheme;
  
  private int mThemeResource;
  
  private final IBinder mToken;
  
  private final UserHandle mUser;
  
  private ContextImpl(ContextImpl paramContextImpl, ActivityThread paramActivityThread, LoadedApk paramLoadedApk, String paramString1, String paramString2, IBinder paramIBinder, UserHandle paramUserHandle, int paramInt, ClassLoader paramClassLoader, String paramString3) {
    String str1;
    String str2;
    this.mThemeResource = 0;
    this.mTheme = null;
    this.mReceiverRestrictedContext = null;
    this.mSplitName = null;
    this.mAutofillClient = null;
    this.mContentCaptureOptions = null;
    this.mSync = new Object();
    Object[] arrayOfObject = SystemServiceRegistry.createServiceCache();
    this.mServiceCache = arrayOfObject;
    this.mServiceInitializationStateArray = new int[arrayOfObject.length];
    this.mOuterContext = this;
    int i = paramInt;
    if ((paramInt & 0x18) == 0) {
      File file = paramLoadedApk.getDataDirFile();
      if (Objects.equals(file, paramLoadedApk.getCredentialProtectedDataDirFile())) {
        i = paramInt | 0x10;
      } else {
        i = paramInt;
        if (Objects.equals(file, paramLoadedApk.getDeviceProtectedDataDirFile()))
          i = paramInt | 0x8; 
      } 
    } 
    this.mMainThread = paramActivityThread;
    this.mToken = paramIBinder;
    this.mFlags = i;
    UserHandle userHandle = paramUserHandle;
    if (paramUserHandle == null)
      userHandle = Process.myUserHandle(); 
    this.mUser = userHandle;
    this.mPackageInfo = paramLoadedApk;
    this.mSplitName = paramString2;
    this.mClassLoader = paramClassLoader;
    this.mResourcesManager = ResourcesManager.getInstance();
    if (paramContextImpl != null) {
      this.mBasePackageName = paramContextImpl.mBasePackageName;
      str2 = paramContextImpl.mOpPackageName;
      setResources(paramContextImpl.mResources);
      this.mDisplay = paramContextImpl.mDisplay;
      this.mIsAssociatedWithDisplay = paramContextImpl.mIsAssociatedWithDisplay;
      this.mIsSystemOrSystemUiContext = paramContextImpl.mIsSystemOrSystemUiContext;
      this.mIsUiContext = paramContextImpl.isSelfOrOuterUiContext();
      str1 = str2;
    } else {
      this.mBasePackageName = ((LoadedApk)str2).mPackageName;
      ApplicationInfo applicationInfo = str2.getApplicationInfo();
      if (applicationInfo.uid == 1000 && applicationInfo.uid != Process.myUid()) {
        str1 = ActivityThread.currentPackageName();
      } else {
        str1 = this.mBasePackageName;
      } 
    } 
    if (paramString3 == null)
      paramString3 = str1; 
    this.mOpPackageName = paramString3;
    this.mAttributionTag = paramString1;
    this.mContentResolver = new ApplicationContentResolver(this, paramActivityThread);
  }
  
  private boolean bindServiceCommon(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt, String paramString, Handler paramHandler, Executor paramExecutor, UserHandle paramUserHandle) {
    boolean bool;
    int i = Binder.getCallingUid();
    if (i == 10135 || i == 10136) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("UID: ");
      stringBuilder.append(i);
      stringBuilder.append(" ContextImpl: bindServiceCommon: 0: mPackageInfo: ");
      stringBuilder.append(this.mPackageInfo);
      stringBuilder.append(" getOuterContext(): ");
      stringBuilder.append(getOuterContext());
      Log.d("201521037", stringBuilder.toString());
    } 
    if (paramServiceConnection != null) {
      if (paramHandler == null || paramExecutor == null) {
        LoadedApk loadedApk = this.mPackageInfo;
        if (loadedApk != null) {
          IServiceConnection iServiceConnection1;
          IServiceConnection iServiceConnection2;
          if (paramExecutor != null) {
            iServiceConnection2 = loadedApk.getServiceDispatcher(paramServiceConnection, getOuterContext(), paramExecutor, paramInt);
            iServiceConnection1 = iServiceConnection2;
            if (bool) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("UID: ");
              stringBuilder.append(i);
              stringBuilder.append(" ContextImpl: bindServiceCommon: 1: executor!=null ");
              stringBuilder.append(iServiceConnection2);
              Log.d("201521037", stringBuilder.toString());
              iServiceConnection1 = iServiceConnection2;
            } 
          } else {
            iServiceConnection2 = loadedApk.getServiceDispatcher((ServiceConnection)iServiceConnection1, getOuterContext(), (Handler)iServiceConnection2, paramInt);
            iServiceConnection1 = iServiceConnection2;
            if (bool) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("UID: ");
              stringBuilder.append(i);
              stringBuilder.append(" ContextImpl: bindServiceCommon: 2: executor==null ");
              stringBuilder.append(iServiceConnection2);
              Log.d("201521037", stringBuilder.toString());
              iServiceConnection1 = iServiceConnection2;
            } 
          } 
          validateServiceIntent(paramIntent);
          if (bool)
            try {
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("UID: ");
              stringBuilder.append(i);
              stringBuilder.append(" ContextImpl: bindServiceCommon: 3");
              Log.d("201521037", stringBuilder.toString());
            } catch (RemoteException null) {} 
          try {
            IBinder iBinder = getActivityToken();
            if (iBinder == null && (paramInt & 0x1) == 0 && this.mPackageInfo != null) {
              int j = (this.mPackageInfo.getApplicationInfo()).targetSdkVersion;
              if (j < 14) {
                if (bool)
                  try {
                    StringBuilder stringBuilder = new StringBuilder();
                    this();
                    stringBuilder.append("UID: ");
                    stringBuilder.append(i);
                    stringBuilder.append(" ContextImpl: bindServiceCommon: 4: in if token == null");
                    Log.d("201521037", stringBuilder.toString());
                  } catch (RemoteException null) {} 
                paramInt |= 0x20;
              } 
            } 
            try {
              remoteException.prepareToLeaveProcess(this);
              IActivityManager iActivityManager = ActivityManager.getService();
              ActivityThread.ApplicationThread applicationThread = this.mMainThread.getApplicationThread();
              IBinder iBinder1 = getActivityToken();
              String str2 = remoteException.resolveTypeIfNeeded(getContentResolver());
              String str1 = getOpPackageName();
              int j = paramUserHandle.getIdentifier();
              try {
                paramInt = iActivityManager.bindIsolatedService(applicationThread, iBinder1, (Intent)remoteException, str2, iServiceConnection1, paramInt, paramString, str1, j);
                if (bool) {
                  StringBuilder stringBuilder1 = new StringBuilder();
                  this();
                  stringBuilder1.append("UID: ");
                  stringBuilder1.append(i);
                  stringBuilder1.append(" ContextImpl: bindServiceCommon: 5: res: ");
                  stringBuilder1.append(paramInt);
                  Log.d("201521037", stringBuilder1.toString());
                } 
                if (paramInt >= 0) {
                  boolean bool1;
                  if (paramInt != 0) {
                    bool1 = true;
                  } else {
                    bool1 = false;
                  } 
                  return bool1;
                } 
                SecurityException securityException = new SecurityException();
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("Not allowed to bind to service ");
                stringBuilder.append(remoteException);
                this(stringBuilder.toString());
                throw securityException;
              } catch (RemoteException remoteException1) {}
            } catch (RemoteException remoteException1) {}
          } catch (RemoteException remoteException) {}
          throw remoteException.rethrowFromSystemServer();
        } 
        throw new RuntimeException("Not supported in system context");
      } 
      throw new IllegalArgumentException("Handler and Executor both supplied");
    } 
    throw new IllegalArgumentException("connection is null");
  }
  
  private void checkMode(int paramInt) {
    if ((getApplicationInfo()).targetSdkVersion >= 24)
      if ((paramInt & 0x1) == 0) {
        if ((paramInt & 0x2) != 0)
          throw new SecurityException("MODE_WORLD_WRITEABLE no longer supported"); 
      } else {
        throw new SecurityException("MODE_WORLD_READABLE no longer supported");
      }  
  }
  
  static ContextImpl createActivityContext(ActivityThread paramActivityThread, LoadedApk paramLoadedApk, ActivityInfo paramActivityInfo, IBinder paramIBinder, int paramInt, Configuration paramConfiguration) {
    if (paramLoadedApk != null) {
      CompatibilityInfo compatibilityInfo;
      List<ResourcesLoader> list;
      String[] arrayOfString1 = paramLoadedApk.getSplitResDirs();
      ClassLoader classLoader = paramLoadedApk.getClassLoader();
      if (paramLoadedApk.getApplicationInfo().requestsIsolatedSplitLoading()) {
        Trace.traceBegin(8192L, "SplitDependencies");
        try {
          classLoader = paramLoadedApk.getSplitClassLoader(paramActivityInfo.splitName);
          arrayOfString1 = paramLoadedApk.getSplitPaths(paramActivityInfo.splitName);
          Trace.traceEnd(8192L);
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
          RuntimeException runtimeException = new RuntimeException();
          this((Throwable)nameNotFoundException);
          throw runtimeException;
        } finally {}
      } 
      ContextImpl contextImpl = new ContextImpl(null, paramActivityThread, (LoadedApk)nameNotFoundException, null, paramActivityInfo.splitName, paramIBinder, null, 0, classLoader, null);
      contextImpl.mIsUiContext = true;
      contextImpl.mIsAssociatedWithDisplay = true;
      contextImpl.mIsSystemOrSystemUiContext = isSystemOrSystemUI(contextImpl);
      if (paramInt == -1)
        paramInt = 0; 
      if (paramInt == 0) {
        compatibilityInfo = nameNotFoundException.getCompatibilityInfo();
      } else {
        compatibilityInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
      } 
      ResourcesManager resourcesManager = ResourcesManager.getInstance();
      String str = nameNotFoundException.getResDir();
      String[] arrayOfString2 = nameNotFoundException.getOverlayDirs();
      String[] arrayOfString3 = (nameNotFoundException.getApplicationInfo()).sharedLibraryFiles;
      if (nameNotFoundException.getApplication() == null) {
        nameNotFoundException = null;
      } else {
        list = nameNotFoundException.getApplication().getResources().getLoaders();
      } 
      contextImpl.setResources(resourcesManager.createBaseTokenResources(paramIBinder, str, arrayOfString1, arrayOfString2, arrayOfString3, paramInt, paramConfiguration, compatibilityInfo, classLoader, list));
      contextImpl.mDisplay = resourcesManager.getAdjustedDisplay(paramInt, contextImpl.getResources());
      return contextImpl;
    } 
    throw new IllegalArgumentException("packageInfo");
  }
  
  static ContextImpl createAppContext(ActivityThread paramActivityThread, LoadedApk paramLoadedApk) {
    return createAppContext(paramActivityThread, paramLoadedApk, null);
  }
  
  static ContextImpl createAppContext(ActivityThread paramActivityThread, LoadedApk paramLoadedApk, String paramString) {
    if (paramLoadedApk != null) {
      ContextImpl contextImpl = new ContextImpl(null, paramActivityThread, paramLoadedApk, null, null, null, null, 0, null, paramString);
      contextImpl.setResources(paramLoadedApk.getResources());
      contextImpl.mIsSystemOrSystemUiContext = isSystemOrSystemUI(contextImpl);
      return contextImpl;
    } 
    throw new IllegalArgumentException("packageInfo");
  }
  
  private static Resources createResources(IBinder paramIBinder, LoadedApk paramLoadedApk, String paramString, int paramInt, Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo, List<ResourcesLoader> paramList) {
    try {
      String[] arrayOfString = paramLoadedApk.getSplitPaths(paramString);
      ClassLoader classLoader = paramLoadedApk.getSplitClassLoader(paramString);
      return ResourcesManager.getInstance().getResources(paramIBinder, paramLoadedApk.getResDir(), arrayOfString, paramLoadedApk.getOverlayDirs(), (paramLoadedApk.getApplicationInfo()).sharedLibraryFiles, paramInt, paramConfiguration, paramCompatibilityInfo, classLoader, paramList);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      throw new RuntimeException(nameNotFoundException);
    } 
  }
  
  static ContextImpl createSystemContext(ActivityThread paramActivityThread) {
    LoadedApk loadedApk = new LoadedApk(paramActivityThread);
    ContextImpl contextImpl = new ContextImpl(null, paramActivityThread, loadedApk, null, null, null, null, 0, null, null);
    contextImpl.setResources(loadedApk.getResources());
    contextImpl.mResources.updateConfiguration(contextImpl.mResourcesManager.getConfiguration(), contextImpl.mResourcesManager.getDisplayMetrics());
    contextImpl.mIsSystemOrSystemUiContext = true;
    return contextImpl;
  }
  
  static ContextImpl createSystemUiContext(ContextImpl paramContextImpl) {
    return createSystemUiContext(paramContextImpl, 0);
  }
  
  static ContextImpl createSystemUiContext(ContextImpl paramContextImpl, int paramInt) {
    LoadedApk loadedApk = paramContextImpl.mPackageInfo;
    paramContextImpl = new ContextImpl(null, paramContextImpl.mMainThread, loadedApk, null, null, null, null, 0, null, null);
    paramContextImpl.setResources(createResources(null, loadedApk, null, paramInt, null, loadedApk.getCompatibilityInfo(), null));
    paramContextImpl.updateDisplay(paramInt);
    paramContextImpl.mIsSystemOrSystemUiContext = true;
    return paramContextImpl;
  }
  
  private void enforce(String paramString1, int paramInt1, boolean paramBoolean, int paramInt2, String paramString2) {
    if (paramInt1 != 0) {
      String str;
      StringBuilder stringBuilder = new StringBuilder();
      if (paramString2 != null) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString2);
        stringBuilder1.append(": ");
        paramString2 = stringBuilder1.toString();
      } else {
        paramString2 = "";
      } 
      stringBuilder.append(paramString2);
      if (paramBoolean) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Neither user ");
        stringBuilder1.append(paramInt2);
        stringBuilder1.append(" nor current process has ");
        str = stringBuilder1.toString();
      } else {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("uid ");
        stringBuilder1.append(paramInt2);
        stringBuilder1.append(" does not have ");
        str = stringBuilder1.toString();
      } 
      stringBuilder.append(str);
      stringBuilder.append(paramString1);
      stringBuilder.append(".");
      throw new SecurityException(stringBuilder.toString());
    } 
  }
  
  private void enforceForUri(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, Uri paramUri, String paramString) {
    if (paramInt2 != 0) {
      String str;
      StringBuilder stringBuilder = new StringBuilder();
      if (paramString != null) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString);
        stringBuilder1.append(": ");
        paramString = stringBuilder1.toString();
      } else {
        paramString = "";
      } 
      stringBuilder.append(paramString);
      if (paramBoolean) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Neither user ");
        stringBuilder1.append(paramInt3);
        stringBuilder1.append(" nor current process has ");
        str = stringBuilder1.toString();
      } else {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("User ");
        stringBuilder1.append(paramInt3);
        stringBuilder1.append(" does not have ");
        str = stringBuilder1.toString();
      } 
      stringBuilder.append(str);
      stringBuilder.append(uriModeFlagToString(paramInt1));
      stringBuilder.append(" permission on ");
      stringBuilder.append(paramUri);
      stringBuilder.append(".");
      throw new SecurityException(stringBuilder.toString());
    } 
  }
  
  private File[] ensureExternalDirsExistOrFilter(File[] paramArrayOfFile, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w android/os/storage/StorageManager
    //   4: invokevirtual getSystemService : (Ljava/lang/Class;)Ljava/lang/Object;
    //   7: checkcast android/os/storage/StorageManager
    //   10: astore_3
    //   11: aload_1
    //   12: arraylength
    //   13: anewarray java/io/File
    //   16: astore #4
    //   18: iconst_0
    //   19: istore #5
    //   21: iload #5
    //   23: aload_1
    //   24: arraylength
    //   25: if_icmpge -> 151
    //   28: aload_1
    //   29: iload #5
    //   31: aaload
    //   32: astore #6
    //   34: aload #6
    //   36: astore #7
    //   38: aload #6
    //   40: invokevirtual exists : ()Z
    //   43: ifne -> 138
    //   46: iload_2
    //   47: ifeq -> 58
    //   50: aload #6
    //   52: invokevirtual mkdirs : ()Z
    //   55: ifne -> 72
    //   58: aload #6
    //   60: invokevirtual exists : ()Z
    //   63: ifne -> 72
    //   66: aload_3
    //   67: aload #6
    //   69: invokevirtual mkdirs : (Ljava/io/File;)V
    //   72: aload #6
    //   74: astore #7
    //   76: goto -> 138
    //   79: astore #8
    //   81: new java/lang/StringBuilder
    //   84: dup
    //   85: invokespecial <init> : ()V
    //   88: astore #7
    //   90: aload #7
    //   92: ldc_w 'Failed to ensure '
    //   95: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: pop
    //   99: aload #7
    //   101: aload #6
    //   103: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   106: pop
    //   107: aload #7
    //   109: ldc_w ': '
    //   112: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: pop
    //   116: aload #7
    //   118: aload #8
    //   120: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   123: pop
    //   124: ldc 'ContextImpl'
    //   126: aload #7
    //   128: invokevirtual toString : ()Ljava/lang/String;
    //   131: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   134: pop
    //   135: aconst_null
    //   136: astore #7
    //   138: aload #4
    //   140: iload #5
    //   142: aload #7
    //   144: aastore
    //   145: iinc #5, 1
    //   148: goto -> 21
    //   151: aload #4
    //   153: areturn
    // Exception table:
    //   from	to	target	type
    //   50	58	79	java/lang/Exception
    //   58	72	79	java/lang/Exception
  }
  
  private static File ensurePrivateCacheDirExists(File paramFile, String paramString) {
    return ensurePrivateDirExists(paramFile, 1529, UserHandle.getCacheAppGid(Process.myUid()), paramString);
  }
  
  private static File ensurePrivateDirExists(File paramFile) {
    return ensurePrivateDirExists(paramFile, 505, -1, null);
  }
  
  private static File ensurePrivateDirExists(File paramFile, int paramInt1, int paramInt2, String paramString) {
    if (!paramFile.exists()) {
      String str = paramFile.getAbsolutePath();
      try {
        Os.mkdir(str, paramInt1);
        Os.chmod(str, paramInt1);
        if (paramInt2 != -1)
          Os.chown(str, -1, paramInt2); 
      } catch (ErrnoException errnoException) {
        if (errnoException.errno != OsConstants.EEXIST) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Failed to ensure ");
          stringBuilder.append(paramFile);
          stringBuilder.append(": ");
          stringBuilder.append(errnoException.getMessage());
          Log.w("ContextImpl", stringBuilder.toString());
        } 
      } 
      if (paramString != null)
        try {
          StructStat structStat = Os.stat(paramFile.getAbsolutePath());
          byte[] arrayOfByte = new byte[8];
          Memory.pokeLong(arrayOfByte, 0, structStat.st_ino, ByteOrder.nativeOrder());
          Os.setxattr(paramFile.getParentFile().getAbsolutePath(), paramString, arrayOfByte, 0);
        } catch (ErrnoException errnoException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Failed to update ");
          stringBuilder.append(paramString);
          stringBuilder.append(": ");
          stringBuilder.append(errnoException.getMessage());
          Log.w("ContextImpl", stringBuilder.toString());
        }  
    } 
    return paramFile;
  }
  
  static File getCodeCacheDirBeforeBind(File paramFile) {
    return new File(paramFile, "code_cache");
  }
  
  private File getDatabasesDir() {
    synchronized (this.mSync) {
      if (this.mDatabasesDir == null)
        if ("android".equals(getPackageName())) {
          File file = new File();
          this("/data/system");
          this.mDatabasesDir = file;
        } else {
          File file = new File();
          this(getDataDir(), "databases");
          this.mDatabasesDir = file;
        }  
      return ensurePrivateDirExists(this.mDatabasesDir);
    } 
  }
  
  static ContextImpl getImpl(Context paramContext) {
    while (paramContext instanceof ContextWrapper) {
      Context context = ((ContextWrapper)paramContext).getBaseContext();
      if (context != null)
        paramContext = context; 
    } 
    return (ContextImpl)paramContext;
  }
  
  private File getPreferencesDir() {
    synchronized (this.mSync) {
      if (this.mPreferencesDir == null) {
        File file = new File();
        this(getDataDir(), "shared_prefs");
        this.mPreferencesDir = file;
      } 
      return ensurePrivateDirExists(this.mPreferencesDir);
    } 
  }
  
  private ArrayMap<File, SharedPreferencesImpl> getSharedPreferencesCacheLocked() {
    if (sSharedPrefsCache == null)
      sSharedPrefsCache = new ArrayMap(); 
    String str = getPackageName();
    ArrayMap<File, SharedPreferencesImpl> arrayMap1 = (ArrayMap)sSharedPrefsCache.get(str);
    ArrayMap<File, SharedPreferencesImpl> arrayMap2 = arrayMap1;
    if (arrayMap1 == null) {
      arrayMap2 = new ArrayMap();
      sSharedPrefsCache.put(str, arrayMap2);
    } 
    return arrayMap2;
  }
  
  private WallpaperManager getWallpaperManager() {
    return (WallpaperManager)getSystemService(WallpaperManager.class);
  }
  
  private void initializeTheme() {
    if (this.mTheme == null)
      this.mTheme = this.mResources.newTheme(); 
    this.mTheme.applyStyle(this.mThemeResource, true);
  }
  
  private boolean isSelfOrOuterUiContext() {
    return (isUiContext() || (getOuterContext() != null && getOuterContext().isUiContext()));
  }
  
  private static boolean isSystemOrSystemUI(Context paramContext) {
    return (ActivityThread.isSystem() || paramContext.checkPermission("android.permission.STATUS_BAR_SERVICE", Binder.getCallingPid(), Binder.getCallingUid()) == 0);
  }
  
  private static boolean isUiComponent(String paramString) {
    return ("window".equals(paramString) || "layout_inflater".equals(paramString) || "wallpaper".equals(paramString));
  }
  
  private File makeFilename(File paramFile, String paramString) {
    if (paramString.indexOf(File.separatorChar) < 0) {
      paramFile = new File(paramFile, paramString);
      BlockGuard.getVmPolicy().onPathAccess(paramFile.getPath());
      return paramFile;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("File ");
    stringBuilder.append(paramString);
    stringBuilder.append(" contains a path separator");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static int moveFiles(File paramFile1, File paramFile2, final String prefix) {
    File[] arrayOfFile = FileUtils.listFilesOrEmpty(paramFile1, new FilenameFilter() {
          public boolean accept(File param1File, String param1String) {
            return param1String.startsWith(prefix);
          }
        });
    int i = 0;
    int j = arrayOfFile.length;
    for (byte b = 0; b < j; b++) {
      File file1 = arrayOfFile[b];
      File file2 = new File(paramFile2, file1.getName());
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Migrating ");
      stringBuilder.append(file1);
      stringBuilder.append(" to ");
      stringBuilder.append(file2);
      Log.d("ContextImpl", stringBuilder.toString());
      try {
        FileUtils.copyFileOrThrow(file1, file2);
        FileUtils.copyPermissions(file1, file2);
        if (file1.delete()) {
          int k = i;
          if (i != -1)
            k = i + 1; 
          i = k;
        } else {
          IOException iOException = new IOException();
          stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Failed to clean up ");
          stringBuilder.append(file1);
          this(stringBuilder.toString());
          throw iOException;
        } 
      } catch (IOException iOException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to migrate ");
        stringBuilder1.append(file1);
        stringBuilder1.append(": ");
        stringBuilder1.append(iOException);
        Log.w("ContextImpl", stringBuilder1.toString());
        i = -1;
      } 
    } 
    return i;
  }
  
  private Intent registerReceiverInternal(BroadcastReceiver paramBroadcastReceiver, int paramInt1, IntentFilter paramIntentFilter, String paramString, Handler paramHandler, Context paramContext, int paramInt2) {
    if (paramBroadcastReceiver != null) {
      IIntentReceiver iIntentReceiver;
      if (this.mPackageInfo != null && paramContext != null) {
        if (paramHandler == null)
          paramHandler = this.mMainThread.getHandler(); 
        iIntentReceiver = this.mPackageInfo.getReceiverDispatcher(paramBroadcastReceiver, paramContext, paramHandler, this.mMainThread.getInstrumentation(), true);
      } else {
        if (paramHandler == null)
          paramHandler = this.mMainThread.getHandler(); 
        iIntentReceiver = (new LoadedApk.ReceiverDispatcher((BroadcastReceiver)iIntentReceiver, paramContext, paramHandler, null, true)).getIIntentReceiver();
      } 
    } else {
      paramBroadcastReceiver = null;
    } 
    try {
      Intent intent = ActivityManager.getService().registerReceiverWithFeature(this.mMainThread.getApplicationThread(), this.mBasePackageName, getAttributionTag(), (IIntentReceiver)paramBroadcastReceiver, paramIntentFilter, paramString, paramInt1, paramInt2);
      if (intent != null) {
        intent.setExtrasClassLoader(getClassLoader());
        intent.prepareToEnterProcess();
      } 
      return intent;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private int resolveUserId(Uri paramUri) {
    return ContentProvider.getUserIdFromUri(paramUri, getUserId());
  }
  
  static void setFilePermissionsFromMode(String paramString, int paramInt1, int paramInt2) {
    int i = paramInt2 | 0x1B0;
    paramInt2 = i;
    if ((paramInt1 & 0x1) != 0)
      paramInt2 = i | 0x4; 
    i = paramInt2;
    if ((paramInt1 & 0x2) != 0)
      i = paramInt2 | 0x2; 
    FileUtils.setPermissions(paramString, i, -1, -1);
  }
  
  private ComponentName startServiceCommon(Intent paramIntent, boolean paramBoolean, UserHandle paramUserHandle) {
    try {
      validateServiceIntent(paramIntent);
      paramIntent.prepareToLeaveProcess(this);
      ComponentName componentName = ActivityManager.getService().startService(this.mMainThread.getApplicationThread(), paramIntent, paramIntent.resolveTypeIfNeeded(getContentResolver()), paramBoolean, getOpPackageName(), getAttributionTag(), paramUserHandle.getIdentifier());
      if (componentName != null) {
        paramBoolean = componentName.getPackageName().equals("!");
        if (!paramBoolean) {
          paramBoolean = componentName.getPackageName().equals("!!");
          if (!paramBoolean) {
            if (componentName.getPackageName().equals("?")) {
              IllegalStateException illegalStateException = new IllegalStateException();
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("Not allowed to start service ");
              stringBuilder.append(paramIntent);
              stringBuilder.append(": ");
              stringBuilder.append(componentName.getClassName());
              this(stringBuilder.toString());
              throw illegalStateException;
            } 
          } else {
            SecurityException securityException = new SecurityException();
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("Unable to start service ");
            stringBuilder.append(paramIntent);
            stringBuilder.append(": ");
            stringBuilder.append(componentName.getClassName());
            this(stringBuilder.toString());
            throw securityException;
          } 
        } else {
          SecurityException securityException = new SecurityException();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Not allowed to start service ");
          stringBuilder.append(paramIntent);
          stringBuilder.append(" without permission ");
          stringBuilder.append(componentName.getClassName());
          this(stringBuilder.toString());
          throw securityException;
        } 
      } 
      return componentName;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private boolean stopServiceCommon(Intent paramIntent, UserHandle paramUserHandle) {
    try {
      validateServiceIntent(paramIntent);
      paramIntent.prepareToLeaveProcess(this);
      int i = ActivityManager.getService().stopService(this.mMainThread.getApplicationThread(), paramIntent, paramIntent.resolveTypeIfNeeded(getContentResolver()), paramUserHandle.getIdentifier());
      if (i >= 0) {
        boolean bool;
        if (i != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      } 
      SecurityException securityException = new SecurityException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Not allowed to stop service ");
      stringBuilder.append(paramIntent);
      this(stringBuilder.toString());
      throw securityException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private String uriModeFlagToString(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    if ((paramInt & 0x1) != 0)
      stringBuilder.append("read and "); 
    if ((paramInt & 0x2) != 0)
      stringBuilder.append("write and "); 
    if ((paramInt & 0x40) != 0)
      stringBuilder.append("persistable and "); 
    if ((paramInt & 0x80) != 0)
      stringBuilder.append("prefix and "); 
    if (stringBuilder.length() > 5) {
      stringBuilder.setLength(stringBuilder.length() - 5);
      return stringBuilder.toString();
    } 
    stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown permission mode flags: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private void validateServiceIntent(Intent paramIntent) {
    boolean bool;
    int i = Binder.getCallingUid();
    if (i == 10135 || i == 10136) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("UID: ");
      stringBuilder.append(i);
      stringBuilder.append(" ContextImpl: validateServiceIntent: 0");
      Log.d("201521037", stringBuilder.toString());
    } 
    if (paramIntent.getComponent() == null && paramIntent.getPackage() == null) {
      if (bool) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("UID: ");
        stringBuilder1.append(i);
        stringBuilder1.append(" ContextImpl: validateServiceIntent: 1");
        Log.d("201521037", stringBuilder1.toString());
      } 
      if ((getApplicationInfo()).targetSdkVersion >= 21) {
        if (bool) {
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("UID: ");
          stringBuilder2.append(i);
          stringBuilder2.append(" ContextImpl: validateServiceIntent: 2");
          Log.d("201521037", stringBuilder2.toString());
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Service Intent must be explicit: ");
        stringBuilder1.append(paramIntent);
        throw new IllegalArgumentException(stringBuilder1.toString());
      } 
      if (bool) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("UID: ");
        stringBuilder1.append(i);
        stringBuilder1.append(" ContextImpl: validateServiceIntent: 3");
        Log.d("201521037", stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Implicit intents with startService are not safe: ");
      stringBuilder.append(paramIntent);
      stringBuilder.append(" ");
      stringBuilder.append(Debug.getCallers(2, 3));
      Log.w("ContextImpl", stringBuilder.toString());
    } 
  }
  
  private void warnIfCallingFromSystemProcess() {
    if (Process.myUid() == 1000) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Calling a method in the system process without a qualified user: ");
      stringBuilder.append(Debug.getCallers(5));
      Slog.w("ContextImpl", stringBuilder.toString());
    } 
  }
  
  public boolean bindIsolatedService(Intent paramIntent, int paramInt, String paramString, Executor paramExecutor, ServiceConnection paramServiceConnection) {
    warnIfCallingFromSystemProcess();
    if (paramString != null)
      return bindServiceCommon(paramIntent, paramServiceConnection, paramInt, paramString, null, paramExecutor, getUser()); 
    throw new NullPointerException("null instanceName");
  }
  
  public boolean bindService(Intent paramIntent, int paramInt, Executor paramExecutor, ServiceConnection paramServiceConnection) {
    warnIfCallingFromSystemProcess();
    return bindServiceCommon(paramIntent, paramServiceConnection, paramInt, null, null, paramExecutor, getUser());
  }
  
  public boolean bindService(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt) {
    boolean bool;
    int i = Binder.getCallingUid();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Hello: ");
    stringBuilder.append(i);
    Log.d("201521037", stringBuilder.toString());
    if (i == 10135 || i == 10136) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("UID: ");
      stringBuilder.append(i);
      stringBuilder.append(" ContextImpl: bindService");
      Log.d("201521037", stringBuilder.toString());
    } 
    warnIfCallingFromSystemProcess();
    return bindServiceCommon(paramIntent, paramServiceConnection, paramInt, null, this.mMainThread.getHandler(), null, getUser());
  }
  
  public boolean bindServiceAsUser(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt, Handler paramHandler, UserHandle paramUserHandle) {
    if (paramHandler != null)
      return bindServiceCommon(paramIntent, paramServiceConnection, paramInt, null, paramHandler, null, paramUserHandle); 
    throw new IllegalArgumentException("handler must not be null.");
  }
  
  public boolean bindServiceAsUser(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt, UserHandle paramUserHandle) {
    return bindServiceCommon(paramIntent, paramServiceConnection, paramInt, null, this.mMainThread.getHandler(), null, paramUserHandle);
  }
  
  public boolean canLoadUnsafeResources() {
    boolean bool = getPackageName().equals(getOpPackageName());
    boolean bool1 = true;
    if (bool)
      return true; 
    if ((this.mFlags & 0x2) == 0)
      bool1 = false; 
    return bool1;
  }
  
  public int checkCallingOrSelfPermission(String paramString) {
    if (paramString != null)
      return checkPermission(paramString, Binder.getCallingPid(), Binder.getCallingUid()); 
    throw new IllegalArgumentException("permission is null");
  }
  
  public int checkCallingOrSelfUriPermission(Uri paramUri, int paramInt) {
    return checkUriPermission(paramUri, Binder.getCallingPid(), Binder.getCallingUid(), paramInt);
  }
  
  public int checkCallingPermission(String paramString) {
    if (paramString != null) {
      int i = Binder.getCallingPid();
      return (i != Process.myPid()) ? checkPermission(paramString, i, Binder.getCallingUid()) : -1;
    } 
    throw new IllegalArgumentException("permission is null");
  }
  
  public int checkCallingUriPermission(Uri paramUri, int paramInt) {
    int i = Binder.getCallingPid();
    return (i != Process.myPid()) ? checkUriPermission(paramUri, i, Binder.getCallingUid(), paramInt) : -1;
  }
  
  public int checkPermission(String paramString, int paramInt1, int paramInt2) {
    if (paramString != null)
      return PermissionManager.checkPermission(paramString, paramInt1, paramInt2); 
    throw new IllegalArgumentException("permission is null");
  }
  
  public int checkPermission(String paramString, int paramInt1, int paramInt2, IBinder paramIBinder) {
    if (paramString != null)
      try {
        return ActivityManager.getService().checkPermissionWithToken(paramString, paramInt1, paramInt2, paramIBinder);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("permission is null");
  }
  
  public int checkSelfPermission(String paramString) {
    if (paramString != null)
      return checkPermission(paramString, Process.myPid(), Process.myUid()); 
    throw new IllegalArgumentException("permission is null");
  }
  
  public int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3) {
    try {
      return ActivityManager.getService().checkUriPermission(ContentProvider.getUriWithoutUserId(paramUri), paramInt1, paramInt2, paramInt3, resolveUserId(paramUri), null);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3, IBinder paramIBinder) {
    try {
      return ActivityManager.getService().checkUriPermission(ContentProvider.getUriWithoutUserId(paramUri), paramInt1, paramInt2, paramInt3, resolveUserId(paramUri), paramIBinder);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int checkUriPermission(Uri paramUri, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3) {
    if ((paramInt3 & 0x1) != 0 && (paramString1 == null || checkPermission(paramString1, paramInt1, paramInt2) == 0))
      return 0; 
    if ((paramInt3 & 0x2) != 0 && (paramString2 == null || checkPermission(paramString2, paramInt1, paramInt2) == 0))
      return 0; 
    if (paramUri != null) {
      paramInt1 = checkUriPermission(paramUri, paramInt1, paramInt2, paramInt3);
    } else {
      paramInt1 = -1;
    } 
    return paramInt1;
  }
  
  @Deprecated
  public void clearWallpaper() throws IOException {
    getWallpaperManager().clear();
  }
  
  public Context createApplicationContext(ApplicationInfo paramApplicationInfo, int paramInt) throws PackageManager.NameNotFoundException {
    LoadedApk loadedApk = this.mMainThread.getPackageInfo(paramApplicationInfo, this.mResources.getCompatibilityInfo(), paramInt | 0x40000000);
    if (loadedApk != null) {
      ContextImpl contextImpl = new ContextImpl(this, this.mMainThread, loadedApk, null, null, this.mToken, new UserHandle(UserHandle.getUserId(paramApplicationInfo.uid)), paramInt, null, null);
      paramInt = getDisplayId();
      contextImpl.setResources(createResources(this.mToken, loadedApk, null, paramInt, null, getDisplayAdjustments(paramInt).getCompatibilityInfo(), null));
      if (contextImpl.mResources != null)
        return contextImpl; 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Application package ");
    stringBuilder.append(paramApplicationInfo.packageName);
    stringBuilder.append(" not found");
    throw new PackageManager.NameNotFoundException(stringBuilder.toString());
  }
  
  public Context createAttributionContext(String paramString) {
    return new ContextImpl(this, this.mMainThread, this.mPackageInfo, paramString, this.mSplitName, this.mToken, this.mUser, this.mFlags, this.mClassLoader, null);
  }
  
  ContextImpl createBaseWindowContext(IBinder paramIBinder) {
    ContextImpl contextImpl = new ContextImpl(this, this.mMainThread, this.mPackageInfo, this.mAttributionTag, this.mSplitName, paramIBinder, this.mUser, this.mFlags, this.mClassLoader, null);
    contextImpl.mIsUiContext = true;
    contextImpl.mIsAssociatedWithDisplay = true;
    return contextImpl;
  }
  
  public Context createConfigurationContext(Configuration paramConfiguration) {
    if (paramConfiguration != null) {
      ContextImpl contextImpl = new ContextImpl(this, this.mMainThread, this.mPackageInfo, this.mAttributionTag, this.mSplitName, this.mToken, this.mUser, this.mFlags, this.mClassLoader, null);
      int i = getDisplayId();
      contextImpl.setResources(createResources(this.mToken, this.mPackageInfo, this.mSplitName, i, paramConfiguration, getDisplayAdjustments(i).getCompatibilityInfo(), this.mResources.getLoaders()));
      return contextImpl;
    } 
    throw new IllegalArgumentException("overrideConfiguration must not be null");
  }
  
  public Context createContextAsUser(UserHandle paramUserHandle, int paramInt) {
    try {
      return createPackageContextAsUser(getPackageName(), paramInt, paramUserHandle);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Own package not found: package=");
      stringBuilder.append(getPackageName());
      throw new IllegalStateException(stringBuilder.toString());
    } 
  }
  
  public Context createContextForSplit(String paramString) throws PackageManager.NameNotFoundException {
    if (!this.mPackageInfo.getApplicationInfo().requestsIsolatedSplitLoading())
      return this; 
    ClassLoader classLoader = this.mPackageInfo.getSplitClassLoader(paramString);
    String[] arrayOfString = this.mPackageInfo.getSplitPaths(paramString);
    ContextImpl contextImpl = new ContextImpl(this, this.mMainThread, this.mPackageInfo, this.mAttributionTag, paramString, this.mToken, this.mUser, this.mFlags, classLoader, null);
    int i = getDisplayId();
    contextImpl.setResources(ResourcesManager.getInstance().getResources(this.mToken, this.mPackageInfo.getResDir(), arrayOfString, this.mPackageInfo.getOverlayDirs(), (this.mPackageInfo.getApplicationInfo()).sharedLibraryFiles, i, null, this.mPackageInfo.getCompatibilityInfo(), classLoader, this.mResources.getLoaders()));
    return contextImpl;
  }
  
  public Context createCredentialProtectedStorageContext() {
    int i = this.mFlags;
    return new ContextImpl(this, this.mMainThread, this.mPackageInfo, this.mAttributionTag, this.mSplitName, this.mToken, this.mUser, i & 0xFFFFFFF7 | 0x10, this.mClassLoader, null);
  }
  
  public Context createDeviceProtectedStorageContext() {
    int i = this.mFlags;
    return new ContextImpl(this, this.mMainThread, this.mPackageInfo, this.mAttributionTag, this.mSplitName, this.mToken, this.mUser, i & 0xFFFFFFEF | 0x8, this.mClassLoader, null);
  }
  
  public Context createDisplayContext(Display paramDisplay) {
    if (paramDisplay != null) {
      ContextImpl contextImpl = new ContextImpl(this, this.mMainThread, this.mPackageInfo, this.mAttributionTag, this.mSplitName, this.mToken, this.mUser, this.mFlags, this.mClassLoader, null);
      int i = paramDisplay.getDisplayId();
      contextImpl.setResources(createResources(this.mToken, this.mPackageInfo, this.mSplitName, i, null, getDisplayAdjustments(i).getCompatibilityInfo(), this.mResources.getLoaders()));
      contextImpl.mDisplay = paramDisplay;
      contextImpl.mIsAssociatedWithDisplay = true;
      contextImpl.mIsUiContext = false;
      return contextImpl;
    } 
    throw new IllegalArgumentException("display must not be null");
  }
  
  public Context createPackageContext(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return createPackageContextAsUser(paramString, paramInt, this.mUser);
  }
  
  public Context createPackageContextAsUser(String paramString, int paramInt, UserHandle paramUserHandle) throws PackageManager.NameNotFoundException {
    if (paramString.equals("system") || paramString.equals("android"))
      return new ContextImpl(this, this.mMainThread, this.mPackageInfo, this.mAttributionTag, null, this.mToken, paramUserHandle, paramInt, null, null); 
    LoadedApk loadedApk = this.mMainThread.getPackageInfo(paramString, this.mResources.getCompatibilityInfo(), paramInt | 0x40000000, paramUserHandle.getIdentifier());
    if (loadedApk != null) {
      ContextImpl contextImpl = new ContextImpl(this, this.mMainThread, loadedApk, this.mAttributionTag, null, this.mToken, paramUserHandle, paramInt, null, null);
      paramInt = getDisplayId();
      contextImpl.setResources(createResources(this.mToken, loadedApk, null, paramInt, null, getDisplayAdjustments(paramInt).getCompatibilityInfo(), null));
      if (contextImpl.mResources != null)
        return contextImpl; 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Application package ");
    stringBuilder.append(paramString);
    stringBuilder.append(" not found");
    throw new PackageManager.NameNotFoundException(stringBuilder.toString());
  }
  
  public WindowContext createWindowContext(int paramInt, Bundle paramBundle) {
    if (getDisplay() != null)
      return new WindowContext(this, paramInt, paramBundle); 
    throw new UnsupportedOperationException("WindowContext can only be created from other visual contexts, such as Activity or one created with Context#createDisplayContext(Display)");
  }
  
  Resources createWindowContextResources() {
    CompatibilityInfo compatibilityInfo;
    String str = this.mPackageInfo.getResDir();
    String[] arrayOfString1 = this.mPackageInfo.getSplitResDirs();
    String[] arrayOfString2 = this.mPackageInfo.getOverlayDirs();
    String[] arrayOfString3 = (this.mPackageInfo.getApplicationInfo()).sharedLibraryFiles;
    int i = getDisplayId();
    if (i == 0) {
      compatibilityInfo = this.mPackageInfo.getCompatibilityInfo();
    } else {
      compatibilityInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
    } 
    List<ResourcesLoader> list = this.mResources.getLoaders();
    return this.mResourcesManager.createBaseTokenResources(this.mToken, str, arrayOfString1, arrayOfString2, arrayOfString3, i, null, compatibilityInfo, this.mClassLoader, list);
  }
  
  public String[] databaseList() {
    return FileUtils.listOrEmpty(getDatabasesDir());
  }
  
  public boolean deleteDatabase(String paramString) {
    try {
      return SQLiteDatabase.deleteDatabase(getDatabasePath(paramString));
    } catch (Exception exception) {
      return false;
    } 
  }
  
  public boolean deleteFile(String paramString) {
    return makeFilename(getFilesDir(), paramString).delete();
  }
  
  public boolean deleteSharedPreferences(String paramString) {
    // Byte code:
    //   0: ldc android/app/ContextImpl
    //   2: monitorenter
    //   3: aload_0
    //   4: aload_1
    //   5: invokevirtual getSharedPreferencesPath : (Ljava/lang/String;)Ljava/io/File;
    //   8: astore_2
    //   9: aload_2
    //   10: invokestatic makeBackupFile : (Ljava/io/File;)Ljava/io/File;
    //   13: astore_1
    //   14: aload_0
    //   15: invokespecial getSharedPreferencesCacheLocked : ()Landroid/util/ArrayMap;
    //   18: aload_2
    //   19: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   22: pop
    //   23: aload_2
    //   24: invokevirtual delete : ()Z
    //   27: pop
    //   28: aload_1
    //   29: invokevirtual delete : ()Z
    //   32: pop
    //   33: aload_2
    //   34: invokevirtual exists : ()Z
    //   37: ifne -> 52
    //   40: aload_1
    //   41: invokevirtual exists : ()Z
    //   44: ifne -> 52
    //   47: iconst_1
    //   48: istore_3
    //   49: goto -> 54
    //   52: iconst_0
    //   53: istore_3
    //   54: ldc android/app/ContextImpl
    //   56: monitorexit
    //   57: iload_3
    //   58: ireturn
    //   59: astore_1
    //   60: ldc android/app/ContextImpl
    //   62: monitorexit
    //   63: aload_1
    //   64: athrow
    // Exception table:
    //   from	to	target	type
    //   3	47	59	finally
    //   54	57	59	finally
    //   60	63	59	finally
  }
  
  public void enforceCallingOrSelfPermission(String paramString1, String paramString2) {
    enforce(paramString1, checkCallingOrSelfPermission(paramString1), true, Binder.getCallingUid(), paramString2);
  }
  
  public void enforceCallingOrSelfUriPermission(Uri paramUri, int paramInt, String paramString) {
    enforceForUri(paramInt, checkCallingOrSelfUriPermission(paramUri, paramInt), true, Binder.getCallingUid(), paramUri, paramString);
  }
  
  public void enforceCallingPermission(String paramString1, String paramString2) {
    enforce(paramString1, checkCallingPermission(paramString1), false, Binder.getCallingUid(), paramString2);
  }
  
  public void enforceCallingUriPermission(Uri paramUri, int paramInt, String paramString) {
    enforceForUri(paramInt, checkCallingUriPermission(paramUri, paramInt), false, Binder.getCallingUid(), paramUri, paramString);
  }
  
  public void enforcePermission(String paramString1, int paramInt1, int paramInt2, String paramString2) {
    enforce(paramString1, checkPermission(paramString1, paramInt1, paramInt2), false, paramInt2, paramString2);
  }
  
  public void enforceUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3, String paramString) {
    enforceForUri(paramInt3, checkUriPermission(paramUri, paramInt1, paramInt2, paramInt3), false, paramInt2, paramUri, paramString);
  }
  
  public void enforceUriPermission(Uri paramUri, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, String paramString3) {
    enforceForUri(paramInt3, checkUriPermission(paramUri, paramString1, paramString2, paramInt1, paramInt2, paramInt3), false, paramInt2, paramUri, paramString3);
  }
  
  public String[] fileList() {
    return FileUtils.listOrEmpty(getFilesDir());
  }
  
  public IBinder getActivityToken() {
    return this.mToken;
  }
  
  public Context getApplicationContext() {
    Application application;
    LoadedApk loadedApk = this.mPackageInfo;
    if (loadedApk != null) {
      application = loadedApk.getApplication();
    } else {
      application = this.mMainThread.getApplication();
    } 
    return (Context)application;
  }
  
  public ApplicationInfo getApplicationInfo() {
    LoadedApk loadedApk = this.mPackageInfo;
    if (loadedApk != null)
      return loadedApk.getApplicationInfo(); 
    throw new RuntimeException("Not supported in system context");
  }
  
  public AssetManager getAssets() {
    return getResources().getAssets();
  }
  
  public String getAttributionTag() {
    return this.mAttributionTag;
  }
  
  public AutofillManager.AutofillClient getAutofillClient() {
    return this.mAutofillClient;
  }
  
  public AutofillOptions getAutofillOptions() {
    return this.mAutofillOptions;
  }
  
  public String getBasePackageName() {
    String str = this.mBasePackageName;
    if (str == null)
      str = getPackageName(); 
    return str;
  }
  
  public File getCacheDir() {
    synchronized (this.mSync) {
      if (this.mCacheDir == null) {
        File file = new File();
        this(getDataDir(), "cache");
        this.mCacheDir = file;
      } 
      return ensurePrivateCacheDirExists(this.mCacheDir, "user.inode_cache");
    } 
  }
  
  public ClassLoader getClassLoader() {
    ClassLoader classLoader = this.mClassLoader;
    if (classLoader == null) {
      LoadedApk loadedApk = this.mPackageInfo;
      if (loadedApk != null) {
        ClassLoader classLoader1 = loadedApk.getClassLoader();
      } else {
        classLoader = ClassLoader.getSystemClassLoader();
      } 
    } 
    return classLoader;
  }
  
  public File getCodeCacheDir() {
    synchronized (this.mSync) {
      if (this.mCodeCacheDir == null)
        this.mCodeCacheDir = getCodeCacheDirBeforeBind(getDataDir()); 
      return ensurePrivateCacheDirExists(this.mCodeCacheDir, "user.inode_code_cache");
    } 
  }
  
  public ContentCaptureOptions getContentCaptureOptions() {
    return this.mContentCaptureOptions;
  }
  
  public ContentResolver getContentResolver() {
    return this.mContentResolver;
  }
  
  public File getCrateDir(String paramString) {
    Preconditions.checkArgument(FileUtils.isValidExtFilename(paramString), "invalidated crateId");
    null = getDataDir().toPath().resolve("crates");
    Path path = null.resolve(paramString).toAbsolutePath().normalize();
    synchronized (this.mSync) {
      if (this.mCratesDir == null)
        this.mCratesDir = null.toFile(); 
      ensurePrivateDirExists(this.mCratesDir);
      return ensurePrivateDirExists(path.toFile());
    } 
  }
  
  public File getDataDir() {
    if (this.mPackageInfo != null) {
      File file;
      if (isCredentialProtectedStorage()) {
        file = this.mPackageInfo.getCredentialProtectedDataDirFile();
      } else if (isDeviceProtectedStorage()) {
        file = this.mPackageInfo.getDeviceProtectedDataDirFile();
      } else {
        file = this.mPackageInfo.getDataDirFile();
      } 
      if (file != null) {
        if (!file.exists() && Process.myUid() == 1000) {
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("Data directory doesn't exist for package ");
          stringBuilder2.append(getPackageName());
          Log.wtf("ContextImpl", stringBuilder2.toString(), new Throwable());
        } 
        return file;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("No data directory found for package ");
      stringBuilder1.append(getPackageName());
      throw new RuntimeException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("No package details found for package ");
    stringBuilder.append(getPackageName());
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public File getDatabasePath(String paramString) {
    File file;
    if (paramString.charAt(0) == File.separatorChar) {
      File file1 = new File(paramString.substring(0, paramString.lastIndexOf(File.separatorChar)));
      file = new File(file1, paramString.substring(paramString.lastIndexOf(File.separatorChar)));
      if (!file1.isDirectory() && file1.mkdir())
        FileUtils.setPermissions(file1.getPath(), 505, -1, -1); 
    } else {
      file = makeFilename(getDatabasesDir(), (String)file);
    } 
    return file;
  }
  
  public File getDir(String paramString, int paramInt) {
    checkMode(paramInt);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("app_");
    stringBuilder.append(paramString);
    paramString = stringBuilder.toString();
    File file = makeFilename(getDataDir(), paramString);
    if (!file.exists()) {
      file.mkdir();
      setFilePermissionsFromMode(file.getPath(), paramInt, 505);
    } 
    return file;
  }
  
  public Display getDisplay() {
    if (this.mIsSystemOrSystemUiContext || this.mIsAssociatedWithDisplay || isSelfOrOuterUiContext())
      return getDisplayNoVerify(); 
    throw new UnsupportedOperationException("Tried to obtain display from a Context not associated with one. Only visual Contexts (such as Activity or one created with Context#createWindowContext) or ones created with Context#createDisplayContext are associated with displays. Other types of Contexts are typically related to background entities and may return an arbitrary display.");
  }
  
  public DisplayAdjustments getDisplayAdjustments(int paramInt) {
    return this.mResources.getDisplayAdjustments();
  }
  
  public int getDisplayId() {
    boolean bool;
    Display display = getDisplayNoVerify();
    if (display != null) {
      bool = display.getDisplayId();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Display getDisplayNoVerify() {
    Display display = this.mDisplay;
    return (display == null) ? this.mResourcesManager.getAdjustedDisplay(0, this.mResources) : display;
  }
  
  public File getExternalCacheDir() {
    File[] arrayOfFile = getExternalCacheDirs();
    if (arrayOfFile != null && arrayOfFile.length > 0) {
      File file = arrayOfFile[0];
    } else {
      arrayOfFile = null;
    } 
    return (File)arrayOfFile;
  }
  
  public File[] getExternalCacheDirs() {
    synchronized (this.mSync) {
      return ensureExternalDirsExistOrFilter(Environment.buildExternalStorageAppCacheDirs(getPackageName()), false);
    } 
  }
  
  public File getExternalFilesDir(String paramString) {
    File[] arrayOfFile = getExternalFilesDirs(paramString);
    if (arrayOfFile != null && arrayOfFile.length > 0) {
      File file = arrayOfFile[0];
    } else {
      arrayOfFile = null;
    } 
    return (File)arrayOfFile;
  }
  
  public File[] getExternalFilesDirs(String paramString) {
    synchronized (this.mSync) {
      File[] arrayOfFile1 = Environment.buildExternalStorageAppFilesDirs(getPackageName());
      File[] arrayOfFile2 = arrayOfFile1;
      if (paramString != null)
        arrayOfFile2 = Environment.buildPaths(arrayOfFile1, new String[] { paramString }); 
      return ensureExternalDirsExistOrFilter(arrayOfFile2, true);
    } 
  }
  
  public File[] getExternalMediaDirs() {
    synchronized (this.mSync) {
      return ensureExternalDirsExistOrFilter(Environment.buildExternalStorageAppMediaDirs(getPackageName()), true);
    } 
  }
  
  public File getFileStreamPath(String paramString) {
    return makeFilename(getFilesDir(), paramString);
  }
  
  public File getFilesDir() {
    synchronized (this.mSync) {
      if (this.mFilesDir == null) {
        File file = new File();
        this(getDataDir(), "files");
        this.mFilesDir = file;
      } 
      return ensurePrivateDirExists(this.mFilesDir);
    } 
  }
  
  public IApplicationThread getIApplicationThread() {
    return this.mMainThread.getApplicationThread();
  }
  
  public Executor getMainExecutor() {
    return this.mMainThread.getExecutor();
  }
  
  public Looper getMainLooper() {
    return this.mMainThread.getLooper();
  }
  
  public Handler getMainThreadHandler() {
    return this.mMainThread.getHandler();
  }
  
  public File getNoBackupFilesDir() {
    synchronized (this.mSync) {
      if (this.mNoBackupFilesDir == null) {
        File file = new File();
        this(getDataDir(), "no_backup");
        this.mNoBackupFilesDir = file;
      } 
      return ensurePrivateDirExists(this.mNoBackupFilesDir);
    } 
  }
  
  public File getObbDir() {
    File[] arrayOfFile = getObbDirs();
    if (arrayOfFile != null && arrayOfFile.length > 0) {
      File file = arrayOfFile[0];
    } else {
      arrayOfFile = null;
    } 
    return (File)arrayOfFile;
  }
  
  public File[] getObbDirs() {
    synchronized (this.mSync) {
      return ensureExternalDirsExistOrFilter(Environment.buildExternalStorageAppObbDirs(getPackageName()), true);
    } 
  }
  
  public String getOpPackageName() {
    String str = this.mOpPackageName;
    if (str == null)
      str = getBasePackageName(); 
    return str;
  }
  
  final Context getOuterContext() {
    return this.mOuterContext;
  }
  
  public String getPackageCodePath() {
    LoadedApk loadedApk = this.mPackageInfo;
    if (loadedApk != null)
      return loadedApk.getAppDir(); 
    throw new RuntimeException("Not supported in system context");
  }
  
  public PackageManager getPackageManager() {
    PackageManager packageManager = this.mPackageManager;
    if (packageManager != null)
      return packageManager; 
    IPackageManager iPackageManager = ActivityThread.getPackageManager();
    IPermissionManager iPermissionManager = ActivityThread.getPermissionManager();
    if (iPackageManager != null && iPermissionManager != null) {
      ApplicationPackageManager applicationPackageManager = new ApplicationPackageManager(this, iPackageManager, iPermissionManager);
      this.mPackageManager = applicationPackageManager;
      return applicationPackageManager;
    } 
    return null;
  }
  
  public String getPackageName() {
    LoadedApk loadedApk = this.mPackageInfo;
    return (loadedApk != null) ? loadedApk.getPackageName() : "android";
  }
  
  public String getPackageResourcePath() {
    LoadedApk loadedApk = this.mPackageInfo;
    if (loadedApk != null)
      return loadedApk.getResDir(); 
    throw new RuntimeException("Not supported in system context");
  }
  
  public File getPreloadsFileCache() {
    return Environment.getDataPreloadsFileCacheDirectory(getPackageName());
  }
  
  final Context getReceiverRestrictedContext() {
    Context context = this.mReceiverRestrictedContext;
    if (context != null)
      return context; 
    ReceiverRestrictedContext receiverRestrictedContext = new ReceiverRestrictedContext(getOuterContext());
    this.mReceiverRestrictedContext = (Context)receiverRestrictedContext;
    return (Context)receiverRestrictedContext;
  }
  
  public Resources getResources() {
    return this.mResources;
  }
  
  public IServiceConnection getServiceDispatcher(ServiceConnection paramServiceConnection, Handler paramHandler, int paramInt) {
    return this.mPackageInfo.getServiceDispatcher(paramServiceConnection, getOuterContext(), paramHandler, paramInt);
  }
  
  public SharedPreferences getSharedPreferences(File paramFile, int paramInt) {
    // Byte code:
    //   0: ldc android/app/ContextImpl
    //   2: monitorenter
    //   3: aload_0
    //   4: invokespecial getSharedPreferencesCacheLocked : ()Landroid/util/ArrayMap;
    //   7: astore_3
    //   8: aload_3
    //   9: aload_1
    //   10: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   13: checkcast android/app/SharedPreferencesImpl
    //   16: astore #4
    //   18: aload #4
    //   20: ifnonnull -> 108
    //   23: aload_0
    //   24: iload_2
    //   25: invokespecial checkMode : (I)V
    //   28: aload_0
    //   29: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   32: getfield targetSdkVersion : I
    //   35: bipush #26
    //   37: if_icmplt -> 82
    //   40: aload_0
    //   41: invokevirtual isCredentialProtectedStorage : ()Z
    //   44: ifeq -> 82
    //   47: aload_0
    //   48: ldc_w android/os/UserManager
    //   51: invokevirtual getSystemService : (Ljava/lang/Class;)Ljava/lang/Object;
    //   54: checkcast android/os/UserManager
    //   57: invokestatic myUserId : ()I
    //   60: invokevirtual isUserUnlockingOrUnlocked : (I)Z
    //   63: ifeq -> 69
    //   66: goto -> 82
    //   69: new java/lang/IllegalStateException
    //   72: astore_1
    //   73: aload_1
    //   74: ldc_w 'SharedPreferences in credential encrypted storage are not available until after user is unlocked'
    //   77: invokespecial <init> : (Ljava/lang/String;)V
    //   80: aload_1
    //   81: athrow
    //   82: new android/app/SharedPreferencesImpl
    //   85: astore #4
    //   87: aload #4
    //   89: aload_1
    //   90: iload_2
    //   91: invokespecial <init> : (Ljava/io/File;I)V
    //   94: aload_3
    //   95: aload_1
    //   96: aload #4
    //   98: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   101: pop
    //   102: ldc android/app/ContextImpl
    //   104: monitorexit
    //   105: aload #4
    //   107: areturn
    //   108: ldc android/app/ContextImpl
    //   110: monitorexit
    //   111: iload_2
    //   112: iconst_4
    //   113: iand
    //   114: ifne -> 129
    //   117: aload_0
    //   118: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   121: getfield targetSdkVersion : I
    //   124: bipush #11
    //   126: if_icmpge -> 134
    //   129: aload #4
    //   131: invokevirtual startReloadIfChangedUnexpectedly : ()V
    //   134: aload #4
    //   136: areturn
    //   137: astore_1
    //   138: ldc android/app/ContextImpl
    //   140: monitorexit
    //   141: aload_1
    //   142: athrow
    // Exception table:
    //   from	to	target	type
    //   3	18	137	finally
    //   23	66	137	finally
    //   69	82	137	finally
    //   82	105	137	finally
    //   108	111	137	finally
    //   138	141	137	finally
  }
  
  public SharedPreferences getSharedPreferences(String paramString, int paramInt) {
    // Byte code:
    //   0: aload_1
    //   1: astore_3
    //   2: aload_0
    //   3: getfield mPackageInfo : Landroid/app/LoadedApk;
    //   6: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   9: getfield targetSdkVersion : I
    //   12: bipush #19
    //   14: if_icmpge -> 27
    //   17: aload_1
    //   18: astore_3
    //   19: aload_1
    //   20: ifnonnull -> 27
    //   23: ldc_w 'null'
    //   26: astore_3
    //   27: ldc android/app/ContextImpl
    //   29: monitorenter
    //   30: aload_0
    //   31: getfield mSharedPrefsPaths : Landroid/util/ArrayMap;
    //   34: ifnonnull -> 50
    //   37: new android/util/ArrayMap
    //   40: astore_1
    //   41: aload_1
    //   42: invokespecial <init> : ()V
    //   45: aload_0
    //   46: aload_1
    //   47: putfield mSharedPrefsPaths : Landroid/util/ArrayMap;
    //   50: aload_0
    //   51: getfield mSharedPrefsPaths : Landroid/util/ArrayMap;
    //   54: aload_3
    //   55: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   58: checkcast java/io/File
    //   61: astore #4
    //   63: aload #4
    //   65: astore_1
    //   66: aload #4
    //   68: ifnonnull -> 87
    //   71: aload_0
    //   72: aload_3
    //   73: invokevirtual getSharedPreferencesPath : (Ljava/lang/String;)Ljava/io/File;
    //   76: astore_1
    //   77: aload_0
    //   78: getfield mSharedPrefsPaths : Landroid/util/ArrayMap;
    //   81: aload_3
    //   82: aload_1
    //   83: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   86: pop
    //   87: ldc android/app/ContextImpl
    //   89: monitorexit
    //   90: aload_0
    //   91: aload_1
    //   92: iload_2
    //   93: invokevirtual getSharedPreferences : (Ljava/io/File;I)Landroid/content/SharedPreferences;
    //   96: areturn
    //   97: astore_1
    //   98: ldc android/app/ContextImpl
    //   100: monitorexit
    //   101: aload_1
    //   102: athrow
    // Exception table:
    //   from	to	target	type
    //   30	50	97	finally
    //   50	63	97	finally
    //   71	87	97	finally
    //   87	90	97	finally
    //   98	101	97	finally
  }
  
  public File getSharedPreferencesPath(String paramString) {
    File file = getPreferencesDir();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(".xml");
    return makeFilename(file, stringBuilder.toString());
  }
  
  public Object getSystemService(String paramString) {
    if (StrictMode.vmIncorrectContextUseEnabled() && isUiComponent(paramString) && !isSelfOrOuterUiContext()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Tried to access visual service ");
      stringBuilder.append(SystemServiceRegistry.getSystemServiceClassName(paramString));
      stringBuilder.append(" from a non-visual Context:");
      stringBuilder.append(getOuterContext());
      String str = stringBuilder.toString();
      IllegalAccessException illegalAccessException = new IllegalAccessException(str);
      StrictMode.onIncorrectContextUsed("Visual services, such as WindowManager, WallpaperService or LayoutInflater should be accessed from Activity or other visual Context. Use an Activity or a Context created with Context#createWindowContext(int, Bundle), which are adjusted to the configuration and visual bounds of an area on screen.", illegalAccessException);
      stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(" ");
      stringBuilder.append("Visual services, such as WindowManager, WallpaperService or LayoutInflater should be accessed from Activity or other visual Context. Use an Activity or a Context created with Context#createWindowContext(int, Bundle), which are adjusted to the configuration and visual bounds of an area on screen.");
      Log.e("ContextImpl", stringBuilder.toString(), illegalAccessException);
    } 
    return SystemServiceRegistry.getSystemService(this, paramString);
  }
  
  public String getSystemServiceName(Class<?> paramClass) {
    return SystemServiceRegistry.getSystemServiceName(paramClass);
  }
  
  public Resources.Theme getTheme() {
    synchronized (this.mSync) {
      if (this.mTheme != null)
        return this.mTheme; 
      this.mThemeResource = Resources.selectDefaultTheme(this.mThemeResource, (getOuterContext().getApplicationInfo()).targetSdkVersion);
      initializeTheme();
      return this.mTheme;
    } 
  }
  
  public int getThemeResId() {
    synchronized (this.mSync) {
      return this.mThemeResource;
    } 
  }
  
  public UserHandle getUser() {
    return this.mUser;
  }
  
  public int getUserId() {
    return this.mUser.getIdentifier();
  }
  
  @Deprecated
  public Drawable getWallpaper() {
    return getWallpaperManager().getDrawable();
  }
  
  @Deprecated
  public int getWallpaperDesiredMinimumHeight() {
    return getWallpaperManager().getDesiredMinimumHeight();
  }
  
  @Deprecated
  public int getWallpaperDesiredMinimumWidth() {
    return getWallpaperManager().getDesiredMinimumWidth();
  }
  
  public void grantUriPermission(String paramString, Uri paramUri, int paramInt) {
    try {
      ActivityManager.getService().grantUriPermission(this.mMainThread.getApplicationThread(), paramString, ContentProvider.getUriWithoutUserId(paramUri), paramInt, resolveUserId(paramUri));
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  void installSystemApplicationInfo(ApplicationInfo paramApplicationInfo, ClassLoader paramClassLoader) {
    this.mPackageInfo.installSystemApplicationInfo(paramApplicationInfo, paramClassLoader);
  }
  
  public boolean isCredentialProtectedStorage() {
    boolean bool;
    if ((this.mFlags & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isDeviceProtectedStorage() {
    boolean bool;
    if ((this.mFlags & 0x8) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isRestricted() {
    boolean bool;
    if ((this.mFlags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isUiContext() {
    return (this.mIsSystemOrSystemUiContext || this.mIsUiContext);
  }
  
  public boolean moveDatabaseFrom(Context paramContext, String paramString) {
    // Byte code:
    //   0: ldc android/app/ContextImpl
    //   2: monitorenter
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual getDatabasePath : (Ljava/lang/String;)Ljava/io/File;
    //   8: astore_1
    //   9: aload_0
    //   10: aload_2
    //   11: invokevirtual getDatabasePath : (Ljava/lang/String;)Ljava/io/File;
    //   14: astore_2
    //   15: aload_1
    //   16: invokevirtual getParentFile : ()Ljava/io/File;
    //   19: aload_2
    //   20: invokevirtual getParentFile : ()Ljava/io/File;
    //   23: aload_1
    //   24: invokevirtual getName : ()Ljava/lang/String;
    //   27: invokestatic moveFiles : (Ljava/io/File;Ljava/io/File;Ljava/lang/String;)I
    //   30: iconst_m1
    //   31: if_icmpeq -> 39
    //   34: iconst_1
    //   35: istore_3
    //   36: goto -> 41
    //   39: iconst_0
    //   40: istore_3
    //   41: ldc android/app/ContextImpl
    //   43: monitorexit
    //   44: iload_3
    //   45: ireturn
    //   46: astore_1
    //   47: ldc android/app/ContextImpl
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    // Exception table:
    //   from	to	target	type
    //   3	34	46	finally
    //   41	44	46	finally
    //   47	50	46	finally
  }
  
  public boolean moveSharedPreferencesFrom(Context paramContext, String paramString) {
    // Byte code:
    //   0: ldc android/app/ContextImpl
    //   2: monitorenter
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual getSharedPreferencesPath : (Ljava/lang/String;)Ljava/io/File;
    //   8: astore_1
    //   9: aload_0
    //   10: aload_2
    //   11: invokevirtual getSharedPreferencesPath : (Ljava/lang/String;)Ljava/io/File;
    //   14: astore_3
    //   15: aload_1
    //   16: invokevirtual getParentFile : ()Ljava/io/File;
    //   19: aload_3
    //   20: invokevirtual getParentFile : ()Ljava/io/File;
    //   23: aload_1
    //   24: invokevirtual getName : ()Ljava/lang/String;
    //   27: invokestatic moveFiles : (Ljava/io/File;Ljava/io/File;Ljava/lang/String;)I
    //   30: istore #4
    //   32: iload #4
    //   34: ifle -> 54
    //   37: aload_0
    //   38: invokespecial getSharedPreferencesCacheLocked : ()Landroid/util/ArrayMap;
    //   41: astore_2
    //   42: aload_2
    //   43: aload_1
    //   44: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   47: pop
    //   48: aload_2
    //   49: aload_3
    //   50: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   53: pop
    //   54: iload #4
    //   56: iconst_m1
    //   57: if_icmpeq -> 66
    //   60: iconst_1
    //   61: istore #5
    //   63: goto -> 69
    //   66: iconst_0
    //   67: istore #5
    //   69: ldc android/app/ContextImpl
    //   71: monitorexit
    //   72: iload #5
    //   74: ireturn
    //   75: astore_1
    //   76: ldc android/app/ContextImpl
    //   78: monitorexit
    //   79: aload_1
    //   80: athrow
    // Exception table:
    //   from	to	target	type
    //   3	32	75	finally
    //   37	54	75	finally
    //   69	72	75	finally
    //   76	79	75	finally
  }
  
  public FileInputStream openFileInput(String paramString) throws FileNotFoundException {
    return new FileInputStream(makeFilename(getFilesDir(), paramString));
  }
  
  public FileOutputStream openFileOutput(String paramString, int paramInt) throws FileNotFoundException {
    boolean bool;
    checkMode(paramInt);
    if ((0x8000 & paramInt) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    File file = makeFilename(getFilesDir(), paramString);
    try {
      FileOutputStream fileOutputStream = new FileOutputStream();
      this(file, bool);
      setFilePermissionsFromMode(file.getPath(), paramInt, 0);
      return fileOutputStream;
    } catch (FileNotFoundException fileNotFoundException) {
      File file1 = file.getParentFile();
      file1.mkdir();
      FileUtils.setPermissions(file1.getPath(), 505, -1, -1);
      FileOutputStream fileOutputStream = new FileOutputStream(file, bool);
      setFilePermissionsFromMode(file.getPath(), paramInt, 0);
      return fileOutputStream;
    } 
  }
  
  public SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory) {
    return openOrCreateDatabase(paramString, paramInt, paramCursorFactory, null);
  }
  
  public SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory, DatabaseErrorHandler paramDatabaseErrorHandler) {
    checkMode(paramInt);
    File file = getDatabasePath(paramString);
    int i = 268435456;
    if ((paramInt & 0x8) != 0)
      i = 0x10000000 | 0x20000000; 
    int j = i;
    if ((paramInt & 0x10) != 0)
      j = i | 0x10; 
    SQLiteDatabase sQLiteDatabase = SQLiteDatabase.openDatabase(file.getPath(), paramCursorFactory, j, paramDatabaseErrorHandler);
    setFilePermissionsFromMode(file.getPath(), paramInt, 0);
    return sQLiteDatabase;
  }
  
  @Deprecated
  public Drawable peekWallpaper() {
    return getWallpaperManager().peekDrawable();
  }
  
  final void performFinalCleanup(String paramString1, String paramString2) {
    this.mPackageInfo.removeContextRegistrations(getOuterContext(), paramString1, paramString2);
  }
  
  public Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter) {
    return registerReceiver(paramBroadcastReceiver, paramIntentFilter, null, null);
  }
  
  public Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, int paramInt) {
    return registerReceiver(paramBroadcastReceiver, paramIntentFilter, null, null, paramInt);
  }
  
  public Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, String paramString, Handler paramHandler) {
    return registerReceiverInternal(paramBroadcastReceiver, getUserId(), paramIntentFilter, paramString, paramHandler, getOuterContext(), 0);
  }
  
  public Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, String paramString, Handler paramHandler, int paramInt) {
    return registerReceiverInternal(paramBroadcastReceiver, getUserId(), paramIntentFilter, paramString, paramHandler, getOuterContext(), paramInt);
  }
  
  public Intent registerReceiverAsUser(BroadcastReceiver paramBroadcastReceiver, UserHandle paramUserHandle, IntentFilter paramIntentFilter, String paramString, Handler paramHandler) {
    return registerReceiverInternal(paramBroadcastReceiver, paramUserHandle.getIdentifier(), paramIntentFilter, paramString, paramHandler, getOuterContext(), 0);
  }
  
  public Intent registerReceiverForAllUsers(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, String paramString, Handler paramHandler) {
    return registerReceiverAsUser(paramBroadcastReceiver, UserHandle.ALL, paramIntentFilter, paramString, paramHandler);
  }
  
  public void reloadSharedPreferences() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: ldc android/app/ContextImpl
    //   10: monitorenter
    //   11: aload_0
    //   12: invokespecial getSharedPreferencesCacheLocked : ()Landroid/util/ArrayMap;
    //   15: astore_2
    //   16: iconst_0
    //   17: istore_3
    //   18: iload_3
    //   19: aload_2
    //   20: invokevirtual size : ()I
    //   23: if_icmpge -> 54
    //   26: aload_2
    //   27: iload_3
    //   28: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   31: checkcast android/app/SharedPreferencesImpl
    //   34: astore #4
    //   36: aload #4
    //   38: ifnull -> 48
    //   41: aload_1
    //   42: aload #4
    //   44: invokevirtual add : (Ljava/lang/Object;)Z
    //   47: pop
    //   48: iinc #3, 1
    //   51: goto -> 18
    //   54: ldc android/app/ContextImpl
    //   56: monitorexit
    //   57: iconst_0
    //   58: istore_3
    //   59: iload_3
    //   60: aload_1
    //   61: invokevirtual size : ()I
    //   64: if_icmpge -> 84
    //   67: aload_1
    //   68: iload_3
    //   69: invokevirtual get : (I)Ljava/lang/Object;
    //   72: checkcast android/app/SharedPreferencesImpl
    //   75: invokevirtual startReloadIfChangedUnexpectedly : ()V
    //   78: iinc #3, 1
    //   81: goto -> 59
    //   84: return
    //   85: astore_2
    //   86: ldc android/app/ContextImpl
    //   88: monitorexit
    //   89: aload_2
    //   90: athrow
    // Exception table:
    //   from	to	target	type
    //   11	16	85	finally
    //   18	36	85	finally
    //   41	48	85	finally
    //   54	57	85	finally
    //   86	89	85	finally
  }
  
  @Deprecated
  public void removeStickyBroadcast(Intent paramIntent) {
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    Intent intent = paramIntent;
    if (str != null) {
      intent = new Intent(paramIntent);
      intent.setDataAndType(intent.getData(), str);
    } 
    try {
      intent.prepareToLeaveProcess(this);
      ActivityManager.getService().unbroadcastIntent(this.mMainThread.getApplicationThread(), intent, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public void removeStickyBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    Intent intent = paramIntent;
    if (str != null) {
      intent = new Intent(paramIntent);
      intent.setDataAndType(intent.getData(), str);
    } 
    try {
      intent.prepareToLeaveProcess(this);
      ActivityManager.getService().unbroadcastIntent(this.mMainThread.getApplicationThread(), intent, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void revokeUriPermission(Uri paramUri, int paramInt) {
    try {
      ActivityManager.getService().revokeUriPermission(this.mMainThread.getApplicationThread(), null, ContentProvider.getUriWithoutUserId(paramUri), paramInt, resolveUserId(paramUri));
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void revokeUriPermission(String paramString, Uri paramUri, int paramInt) {
    try {
      ActivityManager.getService().revokeUriPermission(this.mMainThread.getApplicationThread(), paramString, ContentProvider.getUriWithoutUserId(paramUri), paramInt, resolveUserId(paramUri));
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  final void scheduleFinalCleanup(String paramString1, String paramString2) {
    this.mMainThread.scheduleContextCleanup(this, paramString1, paramString2);
  }
  
  public void sendBroadcast(Intent paramIntent) {
    warnIfCallingFromSystemProcess();
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, null, -1, null, false, false, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendBroadcast(Intent paramIntent, String paramString) {
    String[] arrayOfString;
    warnIfCallingFromSystemProcess();
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    if (paramString == null) {
      paramString = null;
    } else {
      arrayOfString = new String[] { paramString };
    } 
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, arrayOfString, -1, null, false, false, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendBroadcast(Intent paramIntent, String paramString, int paramInt) {
    String[] arrayOfString;
    warnIfCallingFromSystemProcess();
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    if (paramString == null) {
      paramString = null;
    } else {
      arrayOfString = new String[] { paramString };
    } 
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, arrayOfString, paramInt, null, false, false, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendBroadcast(Intent paramIntent, String paramString, Bundle paramBundle) {
    String[] arrayOfString;
    warnIfCallingFromSystemProcess();
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    if (paramString == null) {
      paramString = null;
    } else {
      arrayOfString = new String[] { paramString };
    } 
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, arrayOfString, -1, paramBundle, false, false, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, null, -1, null, false, false, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString) {
    sendBroadcastAsUser(paramIntent, paramUserHandle, paramString, -1);
  }
  
  public void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString, int paramInt) {
    String[] arrayOfString;
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    if (paramString == null) {
      paramString = null;
    } else {
      arrayOfString = new String[] { paramString };
    } 
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, arrayOfString, paramInt, null, false, false, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString, Bundle paramBundle) {
    String[] arrayOfString;
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    if (paramString == null) {
      paramString = null;
    } else {
      arrayOfString = new String[] { paramString };
    } 
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, arrayOfString, -1, paramBundle, false, false, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendBroadcastAsUserMultiplePermissions(Intent paramIntent, UserHandle paramUserHandle, String[] paramArrayOfString) {
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, paramArrayOfString, -1, null, false, false, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendBroadcastMultiplePermissions(Intent paramIntent, String[] paramArrayOfString) {
    warnIfCallingFromSystemProcess();
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, paramArrayOfString, -1, null, false, false, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, int paramInt, String paramString1, String paramString2, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, String paramString3, Bundle paramBundle1, Bundle paramBundle2) {
    int i = -1;
    if (!TextUtils.isEmpty(paramString2))
      i = AppOpsManager.strOpToOp(paramString2); 
    sendOrderedBroadcastAsUser(paramIntent, getUser(), paramString1, i, paramBundle2, paramBroadcastReceiver, paramHandler, paramInt, paramString3, paramBundle1);
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, String paramString) {
    String[] arrayOfString;
    warnIfCallingFromSystemProcess();
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    if (paramString == null) {
      paramString = null;
    } else {
      arrayOfString = new String[] { paramString };
    } 
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, arrayOfString, -1, null, true, false, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, String paramString1, int paramInt1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt2, String paramString2, Bundle paramBundle) {
    sendOrderedBroadcast(paramIntent, paramString1, paramInt1, paramBroadcastReceiver, paramHandler, paramInt2, paramString2, paramBundle, (Bundle)null);
  }
  
  void sendOrderedBroadcast(Intent paramIntent, String paramString1, int paramInt1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt2, String paramString2, Bundle paramBundle1, Bundle paramBundle2) {
    String[] arrayOfString;
    warnIfCallingFromSystemProcess();
    if (paramBroadcastReceiver != null) {
      IIntentReceiver iIntentReceiver;
      if (this.mPackageInfo != null) {
        if (paramHandler == null)
          paramHandler = this.mMainThread.getHandler(); 
        iIntentReceiver = this.mPackageInfo.getReceiverDispatcher(paramBroadcastReceiver, getOuterContext(), paramHandler, this.mMainThread.getInstrumentation(), false);
      } else {
        if (paramHandler == null)
          paramHandler = this.mMainThread.getHandler(); 
        iIntentReceiver = (new LoadedApk.ReceiverDispatcher((BroadcastReceiver)iIntentReceiver, getOuterContext(), paramHandler, null, false)).getIIntentReceiver();
      } 
    } else {
      paramBroadcastReceiver = null;
    } 
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    if (paramString1 == null) {
      paramString1 = null;
    } else {
      arrayOfString = new String[] { paramString1 };
    } 
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, (IIntentReceiver)paramBroadcastReceiver, paramInt2, paramString2, paramBundle1, arrayOfString, paramInt1, paramBundle2, true, false, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, String paramString1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString2, Bundle paramBundle) {
    sendOrderedBroadcast(paramIntent, paramString1, -1, paramBroadcastReceiver, paramHandler, paramInt, paramString2, paramBundle, (Bundle)null);
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, String paramString1, Bundle paramBundle1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString2, Bundle paramBundle2) {
    sendOrderedBroadcast(paramIntent, paramString1, -1, paramBroadcastReceiver, paramHandler, paramInt, paramString2, paramBundle2, paramBundle1);
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, String paramString1, String paramString2, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString3, Bundle paramBundle) {
    int i = -1;
    if (!TextUtils.isEmpty(paramString2))
      i = AppOpsManager.strOpToOp(paramString2); 
    sendOrderedBroadcastAsUser(paramIntent, getUser(), paramString1, i, paramBroadcastReceiver, paramHandler, paramInt, paramString3, paramBundle);
  }
  
  public void sendOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString1, int paramInt1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt2, String paramString2, Bundle paramBundle) {
    sendOrderedBroadcastAsUser(paramIntent, paramUserHandle, paramString1, paramInt1, null, paramBroadcastReceiver, paramHandler, paramInt2, paramString2, paramBundle);
  }
  
  public void sendOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString1, int paramInt1, Bundle paramBundle1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt2, String paramString2, Bundle paramBundle2) {
    String[] arrayOfString;
    if (paramBroadcastReceiver != null) {
      IIntentReceiver iIntentReceiver;
      if (this.mPackageInfo != null) {
        if (paramHandler == null)
          paramHandler = this.mMainThread.getHandler(); 
        iIntentReceiver = this.mPackageInfo.getReceiverDispatcher(paramBroadcastReceiver, getOuterContext(), paramHandler, this.mMainThread.getInstrumentation(), false);
      } else {
        if (paramHandler == null)
          paramHandler = this.mMainThread.getHandler(); 
        iIntentReceiver = (new LoadedApk.ReceiverDispatcher((BroadcastReceiver)iIntentReceiver, getOuterContext(), paramHandler, null, false)).getIIntentReceiver();
      } 
    } else {
      paramBroadcastReceiver = null;
    } 
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    if (paramString1 == null) {
      paramString1 = null;
    } else {
      arrayOfString = new String[] { paramString1 };
    } 
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, (IIntentReceiver)paramBroadcastReceiver, paramInt2, paramString2, paramBundle2, arrayOfString, paramInt1, paramBundle1, true, false, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString2, Bundle paramBundle) {
    sendOrderedBroadcastAsUser(paramIntent, paramUserHandle, paramString1, -1, null, paramBroadcastReceiver, paramHandler, paramInt, paramString2, paramBundle);
  }
  
  @Deprecated
  public void sendStickyBroadcast(Intent paramIntent) {
    warnIfCallingFromSystemProcess();
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, null, -1, null, false, true, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public void sendStickyBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, null, -1, null, false, true, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public void sendStickyBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, Bundle paramBundle) {
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, null, -1, null, null, null, -1, paramBundle, false, true, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public void sendStickyOrderedBroadcast(Intent paramIntent, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString, Bundle paramBundle) {
    warnIfCallingFromSystemProcess();
    if (paramBroadcastReceiver != null) {
      IIntentReceiver iIntentReceiver;
      if (this.mPackageInfo != null) {
        if (paramHandler == null)
          paramHandler = this.mMainThread.getHandler(); 
        iIntentReceiver = this.mPackageInfo.getReceiverDispatcher(paramBroadcastReceiver, getOuterContext(), paramHandler, this.mMainThread.getInstrumentation(), false);
      } else {
        if (paramHandler == null)
          paramHandler = this.mMainThread.getHandler(); 
        iIntentReceiver = (new LoadedApk.ReceiverDispatcher((BroadcastReceiver)iIntentReceiver, getOuterContext(), paramHandler, null, false)).getIIntentReceiver();
      } 
    } else {
      paramBroadcastReceiver = null;
    } 
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, (IIntentReceiver)paramBroadcastReceiver, paramInt, paramString, paramBundle, null, -1, null, true, true, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public void sendStickyOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString, Bundle paramBundle) {
    if (paramBroadcastReceiver != null) {
      IIntentReceiver iIntentReceiver;
      if (this.mPackageInfo != null) {
        if (paramHandler == null)
          paramHandler = this.mMainThread.getHandler(); 
        iIntentReceiver = this.mPackageInfo.getReceiverDispatcher(paramBroadcastReceiver, getOuterContext(), paramHandler, this.mMainThread.getInstrumentation(), false);
      } else {
        if (paramHandler == null)
          paramHandler = this.mMainThread.getHandler(); 
        iIntentReceiver = (new LoadedApk.ReceiverDispatcher((BroadcastReceiver)iIntentReceiver, getOuterContext(), paramHandler, null, false)).getIIntentReceiver();
      } 
    } else {
      paramBroadcastReceiver = null;
    } 
    String str = paramIntent.resolveTypeIfNeeded(getContentResolver());
    try {
      paramIntent.prepareToLeaveProcess(this);
      ActivityManager.getService().broadcastIntentWithFeature(this.mMainThread.getApplicationThread(), getAttributionTag(), paramIntent, str, (IIntentReceiver)paramBroadcastReceiver, paramInt, paramString, paramBundle, null, -1, null, true, true, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setAutofillClient(AutofillManager.AutofillClient paramAutofillClient) {
    this.mAutofillClient = paramAutofillClient;
  }
  
  public void setAutofillOptions(AutofillOptions paramAutofillOptions) {
    this.mAutofillOptions = paramAutofillOptions;
  }
  
  public void setContentCaptureOptions(ContentCaptureOptions paramContentCaptureOptions) {
    this.mContentCaptureOptions = paramContentCaptureOptions;
  }
  
  final void setOuterContext(Context paramContext) {
    this.mOuterContext = paramContext;
  }
  
  void setResources(Resources paramResources) {
    if (paramResources instanceof CompatResources)
      ((CompatResources)paramResources).setContext(this); 
    this.mResources = paramResources;
  }
  
  public void setTheme(int paramInt) {
    synchronized (this.mSync) {
      if (this.mThemeResource != paramInt) {
        this.mThemeResource = paramInt;
        initializeTheme();
      } 
      return;
    } 
  }
  
  @Deprecated
  public void setWallpaper(Bitmap paramBitmap) throws IOException {
    getWallpaperManager().setBitmap(paramBitmap);
  }
  
  @Deprecated
  public void setWallpaper(InputStream paramInputStream) throws IOException {
    getWallpaperManager().setStream(paramInputStream);
  }
  
  public void startActivities(Intent[] paramArrayOfIntent) {
    warnIfCallingFromSystemProcess();
    startActivities(paramArrayOfIntent, null);
  }
  
  public void startActivities(Intent[] paramArrayOfIntent, Bundle paramBundle) {
    warnIfCallingFromSystemProcess();
    if ((paramArrayOfIntent[0].getFlags() & 0x10000000) != 0) {
      this.mMainThread.getInstrumentation().execStartActivities(getOuterContext(), (IBinder)this.mMainThread.getApplicationThread(), null, (Activity)null, paramArrayOfIntent, paramBundle);
      return;
    } 
    throw new AndroidRuntimeException("Calling startActivities() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag on first Intent. Is this really what you want?");
  }
  
  public int startActivitiesAsUser(Intent[] paramArrayOfIntent, Bundle paramBundle, UserHandle paramUserHandle) {
    if ((paramArrayOfIntent[0].getFlags() & 0x10000000) != 0)
      return this.mMainThread.getInstrumentation().execStartActivitiesAsUser(getOuterContext(), (IBinder)this.mMainThread.getApplicationThread(), null, (Activity)null, paramArrayOfIntent, paramBundle, paramUserHandle.getIdentifier()); 
    throw new AndroidRuntimeException("Calling startActivities() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag on first Intent. Is this really what you want?");
  }
  
  public void startActivity(Intent paramIntent) {
    warnIfCallingFromSystemProcess();
    startActivity(paramIntent, null);
  }
  
  public void startActivity(Intent paramIntent, Bundle paramBundle) {
    warnIfCallingFromSystemProcess();
    int i = (getApplicationInfo()).targetSdkVersion;
    if ((paramIntent.getFlags() & 0x10000000) != 0 || (i >= 24 && i < 28) || (paramBundle != null && ActivityOptions.fromBundle(paramBundle).getLaunchTaskId() != -1)) {
      this.mMainThread.getInstrumentation().execStartActivity(getOuterContext(), (IBinder)this.mMainThread.getApplicationThread(), (IBinder)null, (Activity)null, paramIntent, -1, paramBundle);
      return;
    } 
    throw new AndroidRuntimeException("Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?");
  }
  
  public void startActivityAsUser(Intent paramIntent, Bundle paramBundle, UserHandle paramUserHandle) {
    try {
      IActivityTaskManager iActivityTaskManager = ActivityTaskManager.getService();
      try {
        ActivityThread.ApplicationThread applicationThread = this.mMainThread.getApplicationThread();
        String str1 = getBasePackageName();
        String str2 = getAttributionTag();
        ContentResolver contentResolver = getContentResolver();
        try {
          iActivityTaskManager.startActivityAsUser(applicationThread, str1, str2, paramIntent, paramIntent.resolveTypeIfNeeded(contentResolver), null, null, 0, 268435456, null, paramBundle, paramUserHandle.getIdentifier());
          return;
        } catch (RemoteException null) {}
      } catch (RemoteException null) {}
    } catch (RemoteException remoteException) {}
    throw remoteException.rethrowFromSystemServer();
  }
  
  public void startActivityAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    startActivityAsUser(paramIntent, null, paramUserHandle);
  }
  
  public ComponentName startForegroundService(Intent paramIntent) {
    warnIfCallingFromSystemProcess();
    return startServiceCommon(paramIntent, true, this.mUser);
  }
  
  public ComponentName startForegroundServiceAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    return startServiceCommon(paramIntent, true, paramUserHandle);
  }
  
  public boolean startInstrumentation(ComponentName paramComponentName, String paramString, Bundle paramBundle) {
    if (paramBundle != null)
      try {
        paramBundle.setAllowFds(false);
        return ActivityManager.getService().startInstrumentation(paramComponentName, paramString, 0, paramBundle, null, null, getUserId(), null);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return ActivityManager.getService().startInstrumentation((ComponentName)remoteException, paramString, 0, paramBundle, null, null, getUserId(), null);
  }
  
  public void startIntentSender(IntentSender paramIntentSender, Intent paramIntent, int paramInt1, int paramInt2, int paramInt3) throws IntentSender.SendIntentException {
    startIntentSender(paramIntentSender, paramIntent, paramInt1, paramInt2, paramInt3, null);
  }
  
  public void startIntentSender(IntentSender paramIntentSender, Intent paramIntent, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle) throws IntentSender.SendIntentException {
    // Byte code:
    //   0: aconst_null
    //   1: astore #7
    //   3: aload_2
    //   4: ifnull -> 28
    //   7: aload_2
    //   8: aload_0
    //   9: invokevirtual migrateExtraStreamToClipData : (Landroid/content/Context;)Z
    //   12: pop
    //   13: aload_2
    //   14: aload_0
    //   15: invokevirtual prepareToLeaveProcess : (Landroid/content/Context;)V
    //   18: aload_2
    //   19: aload_0
    //   20: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   23: invokevirtual resolveTypeIfNeeded : (Landroid/content/ContentResolver;)Ljava/lang/String;
    //   26: astore #7
    //   28: invokestatic getService : ()Landroid/app/IActivityTaskManager;
    //   31: astore #8
    //   33: aload_0
    //   34: getfield mMainThread : Landroid/app/ActivityThread;
    //   37: invokevirtual getApplicationThread : ()Landroid/app/ActivityThread$ApplicationThread;
    //   40: astore #9
    //   42: aload_1
    //   43: ifnull -> 55
    //   46: aload_1
    //   47: invokevirtual getTarget : ()Landroid/content/IIntentSender;
    //   50: astore #10
    //   52: goto -> 58
    //   55: aconst_null
    //   56: astore #10
    //   58: aload_1
    //   59: ifnull -> 70
    //   62: aload_1
    //   63: invokevirtual getWhitelistToken : ()Landroid/os/IBinder;
    //   66: astore_1
    //   67: goto -> 72
    //   70: aconst_null
    //   71: astore_1
    //   72: aload #8
    //   74: aload #9
    //   76: aload #10
    //   78: aload_1
    //   79: aload_2
    //   80: aload #7
    //   82: aconst_null
    //   83: aconst_null
    //   84: iconst_0
    //   85: iload_3
    //   86: iload #4
    //   88: aload #6
    //   90: invokeinterface startActivityIntentSender : (Landroid/app/IApplicationThread;Landroid/content/IIntentSender;Landroid/os/IBinder;Landroid/content/Intent;Ljava/lang/String;Landroid/os/IBinder;Ljava/lang/String;IIILandroid/os/Bundle;)I
    //   95: istore_3
    //   96: iload_3
    //   97: bipush #-96
    //   99: if_icmpeq -> 108
    //   102: iload_3
    //   103: aconst_null
    //   104: invokestatic checkStartActivityResult : (ILjava/lang/Object;)V
    //   107: return
    //   108: new android/content/IntentSender$SendIntentException
    //   111: astore_1
    //   112: aload_1
    //   113: invokespecial <init> : ()V
    //   116: aload_1
    //   117: athrow
    //   118: astore_1
    //   119: aload_1
    //   120: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   123: athrow
    // Exception table:
    //   from	to	target	type
    //   7	28	118	android/os/RemoteException
    //   28	42	118	android/os/RemoteException
    //   46	52	118	android/os/RemoteException
    //   62	67	118	android/os/RemoteException
    //   72	96	118	android/os/RemoteException
    //   102	107	118	android/os/RemoteException
    //   108	118	118	android/os/RemoteException
  }
  
  public ComponentName startService(Intent paramIntent) {
    warnIfCallingFromSystemProcess();
    return startServiceCommon(paramIntent, false, this.mUser);
  }
  
  public ComponentName startServiceAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    return startServiceCommon(paramIntent, false, paramUserHandle);
  }
  
  public boolean stopService(Intent paramIntent) {
    warnIfCallingFromSystemProcess();
    return stopServiceCommon(paramIntent, this.mUser);
  }
  
  public boolean stopServiceAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    return stopServiceCommon(paramIntent, paramUserHandle);
  }
  
  public void unbindService(ServiceConnection paramServiceConnection) {
    if (paramServiceConnection != null) {
      LoadedApk loadedApk = this.mPackageInfo;
      if (loadedApk != null) {
        IServiceConnection iServiceConnection = loadedApk.forgetServiceDispatcher(getOuterContext(), paramServiceConnection);
        try {
          ActivityManager.getService().unbindService(iServiceConnection);
          return;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      } 
      throw new RuntimeException("Not supported in system context");
    } 
    throw new IllegalArgumentException("connection is null");
  }
  
  public void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver) {
    LoadedApk loadedApk = this.mPackageInfo;
    if (loadedApk != null) {
      IIntentReceiver iIntentReceiver = loadedApk.forgetReceiverDispatcher(getOuterContext(), paramBroadcastReceiver);
      try {
        ActivityManager.getService().unregisterReceiver(iIntentReceiver);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
    throw new RuntimeException("Not supported in system context");
  }
  
  public void updateDisplay(int paramInt) {
    this.mDisplay = this.mResourcesManager.getAdjustedDisplay(paramInt, this.mResources);
    this.mIsAssociatedWithDisplay = true;
  }
  
  public void updateServiceGroup(ServiceConnection paramServiceConnection, int paramInt1, int paramInt2) {
    if (paramServiceConnection != null) {
      LoadedApk loadedApk = this.mPackageInfo;
      if (loadedApk != null) {
        IServiceConnection iServiceConnection = loadedApk.lookupServiceDispatcher(paramServiceConnection, getOuterContext());
        if (iServiceConnection != null)
          try {
            ActivityManager.getService().updateServiceGroup(iServiceConnection, paramInt1, paramInt2);
            return;
          } catch (RemoteException remoteException) {
            throw remoteException.rethrowFromSystemServer();
          }  
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ServiceConnection not currently bound: ");
        stringBuilder.append(remoteException);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      throw new RuntimeException("Not supported in system context");
    } 
    throw new IllegalArgumentException("connection is null");
  }
  
  private static final class ApplicationContentResolver extends ContentResolver {
    private final ActivityThread mMainThread;
    
    public ApplicationContentResolver(Context param1Context, ActivityThread param1ActivityThread) {
      super(param1Context);
      Objects.requireNonNull(param1ActivityThread);
      this.mMainThread = param1ActivityThread;
    }
    
    protected IContentProvider acquireExistingProvider(Context param1Context, String param1String) {
      return this.mMainThread.acquireExistingProvider(param1Context, ContentProvider.getAuthorityWithoutUserId(param1String), resolveUserIdFromAuthority(param1String), true);
    }
    
    protected IContentProvider acquireProvider(Context param1Context, String param1String) {
      return this.mMainThread.acquireProvider(param1Context, ContentProvider.getAuthorityWithoutUserId(param1String), resolveUserIdFromAuthority(param1String), true);
    }
    
    protected IContentProvider acquireUnstableProvider(Context param1Context, String param1String) {
      return this.mMainThread.acquireProvider(param1Context, ContentProvider.getAuthorityWithoutUserId(param1String), resolveUserIdFromAuthority(param1String), false);
    }
    
    public void appNotRespondingViaProvider(IContentProvider param1IContentProvider) {
      this.mMainThread.appNotRespondingViaProvider(param1IContentProvider.asBinder());
    }
    
    public boolean releaseProvider(IContentProvider param1IContentProvider) {
      return this.mMainThread.releaseProvider(param1IContentProvider, true);
    }
    
    public boolean releaseUnstableProvider(IContentProvider param1IContentProvider) {
      return this.mMainThread.releaseProvider(param1IContentProvider, false);
    }
    
    protected int resolveUserIdFromAuthority(String param1String) {
      return ContentProvider.getUserIdFromAuthority(param1String, getUserId());
    }
    
    public void unstableProviderDied(IContentProvider param1IContentProvider) {
      this.mMainThread.handleUnstableProviderDied(param1IContentProvider.asBinder(), true);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface ServiceInitializationState {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ContextImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
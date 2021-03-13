package android.app;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ChangedPackages;
import android.content.pm.ComponentInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.IDexModuleRegisterCallback;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageManager;
import android.content.pm.IPackageMoveObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.InstallSourceInfo;
import android.content.pm.InstantAppInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.IntentFilterVerificationInfo;
import android.content.pm.KeySet;
import android.content.pm.ModuleInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ParceledListSlice;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.SharedLibraryInfo;
import android.content.pm.SuspendDialogInfo;
import android.content.pm.VerifierDeviceIdentity;
import android.content.pm.VersionedPackage;
import android.content.pm.dex.ArtManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.permission.IOnPermissionsChangeListener;
import android.permission.IPermissionManager;
import android.permission.PermissionManager;
import android.provider.Settings;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.LauncherIcons;
import android.util.Log;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.UserIcons;
import dalvik.system.VMRuntime;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import libcore.util.EmptyArray;

public class ApplicationPackageManager extends PackageManager {
  public static final String APP_PERMISSION_BUTTON_ALLOW_ALWAYS = "app_permission_button_allow_always";
  
  private static final boolean DEBUG_ICONS = false;
  
  public static final boolean DEBUG_TRACE_GRANTS = false;
  
  public static final boolean DEBUG_TRACE_PERMISSION_UPDATES = false;
  
  private static final int DEFAULT_EPHEMERAL_COOKIE_MAX_SIZE_BYTES = 16384;
  
  public static final String PERMISSION_CONTROLLER_RESOURCE_PACKAGE = "com.android.permissioncontroller";
  
  private static final String TAG = "ApplicationPackageManager";
  
  private static final PropertyInvalidatedCache<HasSystemFeatureQuery, Boolean> mHasSystemFeatureCache = new PropertyInvalidatedCache<HasSystemFeatureQuery, Boolean>(256, "cache_key.has_system_feature") {
      protected Boolean recompute(ApplicationPackageManager.HasSystemFeatureQuery param1HasSystemFeatureQuery) {
        try {
          ActivityThread.currentActivityThread();
          boolean bool = ActivityThread.getPackageManager().hasSystemFeature(param1HasSystemFeatureQuery.name, param1HasSystemFeatureQuery.version);
          return Boolean.valueOf(bool);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      }
    };
  
  private static final int sDefaultFlags = 1024;
  
  private static ArrayMap<ResourceName, WeakReference<Drawable.ConstantState>> sIconCache;
  
  private static ArrayMap<ResourceName, WeakReference<CharSequence>> sStringCache;
  
  private static final Object sSync = new Object();
  
  private ArtManager mArtManager;
  
  volatile int mCachedSafeMode = -1;
  
  private final ContextImpl mContext;
  
  private final ArrayList<MoveCallbackDelegate> mDelegates = new ArrayList<>();
  
  private PackageInstaller mInstaller;
  
  private final Object mLock = new Object();
  
  private final IPackageManager mPM;
  
  private final Map<PackageManager.OnPermissionsChangedListener, IOnPermissionsChangeListener> mPermissionListeners = (Map<PackageManager.OnPermissionsChangedListener, IOnPermissionsChangeListener>)new ArrayMap();
  
  private final IPermissionManager mPermissionManager;
  
  private String mPermissionsControllerPackageName;
  
  private UserManager mUserManager;
  
  private volatile boolean mUserUnlocked = false;
  
  static {
    sIconCache = new ArrayMap();
    sStringCache = new ArrayMap();
  }
  
  protected ApplicationPackageManager(ContextImpl paramContextImpl, IPackageManager paramIPackageManager) {
    this(paramContextImpl, paramIPackageManager, ActivityThread.getPermissionManager());
  }
  
  protected ApplicationPackageManager(ContextImpl paramContextImpl, IPackageManager paramIPackageManager, IPermissionManager paramIPermissionManager) {
    this.mContext = paramContextImpl;
    this.mPM = paramIPackageManager;
    this.mPermissionManager = paramIPermissionManager;
  }
  
  static void configurationChanged() {
    synchronized (sSync) {
      sIconCache.clear();
      sStringCache.clear();
      return;
    } 
  }
  
  private Drawable getBadgedDrawable(Drawable paramDrawable1, Drawable paramDrawable2, Rect paramRect, boolean paramBoolean) {
    StringBuilder stringBuilder;
    boolean bool;
    Bitmap bitmap;
    int i = paramDrawable1.getIntrinsicWidth();
    int j = paramDrawable1.getIntrinsicHeight();
    if (paramBoolean && paramDrawable1 instanceof BitmapDrawable && ((BitmapDrawable)paramDrawable1).getBitmap().isMutable()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      bitmap = ((BitmapDrawable)paramDrawable1).getBitmap();
    } else {
      bitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    } 
    Canvas canvas = new Canvas(bitmap);
    if (!bool) {
      paramDrawable1.setBounds(0, 0, i, j);
      paramDrawable1.draw(canvas);
    } 
    if (paramRect != null) {
      if (paramRect.left >= 0 && paramRect.top >= 0 && paramRect.width() <= i && paramRect.height() <= j) {
        paramDrawable2.setBounds(0, 0, paramRect.width(), paramRect.height());
        canvas.save();
        canvas.translate(paramRect.left, paramRect.top);
        paramDrawable2.draw(canvas);
        canvas.restore();
      } else {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Badge location ");
        stringBuilder.append(paramRect);
        stringBuilder.append(" not in badged drawable bounds ");
        stringBuilder.append(new Rect(0, 0, i, j));
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
    } else {
      paramDrawable2.setBounds(0, 0, i, j);
      paramDrawable2.draw(canvas);
    } 
    if (!bool) {
      BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), bitmap);
      if (stringBuilder instanceof BitmapDrawable)
        bitmapDrawable.setTargetDensity(((BitmapDrawable)stringBuilder).getBitmap().getDensity()); 
      return (Drawable)bitmapDrawable;
    } 
    return (Drawable)stringBuilder;
  }
  
  private Drawable getCachedIcon(ResourceName paramResourceName) {
    synchronized (sSync) {
      WeakReference<Drawable.ConstantState> weakReference = (WeakReference)sIconCache.get(paramResourceName);
      if (weakReference != null) {
        Drawable drawable;
        Drawable.ConstantState constantState = weakReference.get();
        if (constantState != null) {
          drawable = constantState.newDrawable();
          return drawable;
        } 
        sIconCache.remove(drawable);
      } 
      return null;
    } 
  }
  
  private CharSequence getCachedString(ResourceName paramResourceName) {
    synchronized (sSync) {
      WeakReference<CharSequence> weakReference = (WeakReference)sStringCache.get(paramResourceName);
      if (weakReference != null) {
        CharSequence charSequence = weakReference.get();
        if (charSequence != null)
          return charSequence; 
        sStringCache.remove(paramResourceName);
      } 
      return null;
    } 
  }
  
  private Drawable getDrawableForDensity(int paramInt1, int paramInt2) {
    int i = paramInt2;
    if (paramInt2 <= 0)
      i = (this.mContext.getResources().getDisplayMetrics()).densityDpi; 
    return this.mContext.getResources().getDrawableForDensity(paramInt1, i);
  }
  
  private Intent getLaunchIntentForPackageAndCategory(String paramString1, String paramString2) {
    Intent intent2 = new Intent("android.intent.action.MAIN");
    intent2.addCategory(paramString2);
    intent2.setPackage(paramString1);
    List<ResolveInfo> list = queryIntentActivities(intent2, 0);
    if (list == null || list.size() <= 0)
      return null; 
    Intent intent1 = new Intent(intent2);
    intent1.setFlags(268435456);
    intent1.setClassName(((ResolveInfo)list.get(0)).activityInfo.packageName, ((ResolveInfo)list.get(0)).activityInfo.name);
    return intent1;
  }
  
  private Drawable getProfileIconForDensity(UserHandle paramUserHandle, int paramInt1, int paramInt2) {
    return hasUserBadge(paramUserHandle.getIdentifier()) ? getDrawableForDensity(paramInt1, paramInt2) : null;
  }
  
  private int getUserBadgeColor(UserHandle paramUserHandle, boolean paramBoolean) {
    return (paramBoolean && this.mContext.getResources().getConfiguration().isNightModeActive()) ? getUserManager().getUserBadgeDarkColor(paramUserHandle.getIdentifier()) : getUserManager().getUserBadgeColor(paramUserHandle.getIdentifier());
  }
  
  static void handlePackageBroadcast(int paramInt, String[] paramArrayOfString, boolean paramBoolean) {
    boolean bool = false;
    if (paramInt == 1)
      bool = true; 
    if (paramArrayOfString != null && paramArrayOfString.length > 0) {
      paramInt = 0;
      int i = paramArrayOfString.length;
      byte b = 0;
      while (b < i) {
        String str = paramArrayOfString[b];
        synchronized (sSync) {
          int j;
          for (j = sIconCache.size() - 1; j >= 0; j--) {
            if (((ResourceName)sIconCache.keyAt(j)).packageName.equals(str)) {
              sIconCache.removeAt(j);
              paramInt = 1;
            } 
          } 
          for (j = sStringCache.size() - 1; j >= 0; j--) {
            if (((ResourceName)sStringCache.keyAt(j)).packageName.equals(str)) {
              sStringCache.removeAt(j);
              paramInt = 1;
            } 
          } 
          b++;
        } 
      } 
      if (paramInt != 0 || paramBoolean)
        if (bool) {
          Runtime.getRuntime().gc();
        } else {
          ActivityThread.currentActivityThread().scheduleGcIdler();
        }  
    } 
  }
  
  private boolean hasUserBadge(int paramInt) {
    return getUserManager().hasBadge(paramInt);
  }
  
  private int installExistingPackageAsUser(String paramString, int paramInt1, int paramInt2) throws PackageManager.NameNotFoundException {
    try {
      paramInt1 = this.mPM.installExistingPackageAsUser(paramString, paramInt2, 4194304, paramInt1, null);
      if (paramInt1 != -3)
        return paramInt1; 
      PackageManager.NameNotFoundException nameNotFoundException = new PackageManager.NameNotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Package ");
      stringBuilder.append(paramString);
      stringBuilder.append(" doesn't exist");
      this(stringBuilder.toString());
      throw nameNotFoundException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static void invalidateHasSystemFeatureCache() {
    mHasSystemFeatureCache.invalidateCache();
  }
  
  private boolean isPackageCandidateVolume(ContextImpl paramContextImpl, ApplicationInfo paramApplicationInfo, VolumeInfo paramVolumeInfo, IPackageManager paramIPackageManager) {
    boolean bool1 = isForceAllowOnExternal(paramContextImpl);
    boolean bool2 = "private".equals(paramVolumeInfo.getId());
    boolean bool3 = true;
    boolean bool4 = true;
    if (bool2) {
      bool3 = bool4;
      if (!paramApplicationInfo.isSystemApp())
        if (isAllow3rdPartyOnInternal(paramContextImpl)) {
          bool3 = bool4;
        } else {
          bool3 = false;
        }  
      return bool3;
    } 
    if (paramApplicationInfo.isSystemApp())
      return false; 
    if (!bool1 && (paramApplicationInfo.installLocation == 1 || paramApplicationInfo.installLocation == -1))
      return false; 
    if (!paramVolumeInfo.isMountedWritable())
      return false; 
    if (paramVolumeInfo.isPrimaryPhysical())
      return paramApplicationInfo.isInternal(); 
    try {
      bool4 = paramIPackageManager.isPackageDeviceAdminOnAnyUser(paramApplicationInfo.packageName);
      if (bool4)
        return false; 
      if (paramVolumeInfo.getType() != 1)
        bool3 = false; 
      return bool3;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private static boolean isPrimaryStorageCandidateVolume(VolumeInfo paramVolumeInfo) {
    boolean bool = "private".equals(paramVolumeInfo.getId());
    boolean bool1 = true;
    if (bool)
      return true; 
    if (!paramVolumeInfo.isMountedWritable())
      return false; 
    if (paramVolumeInfo.getType() != 1)
      bool1 = false; 
    return bool1;
  }
  
  private static ApplicationInfo maybeAdjustApplicationInfo(ApplicationInfo paramApplicationInfo) {
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
        ApplicationInfo applicationInfo = new ApplicationInfo(paramApplicationInfo);
        applicationInfo.nativeLibraryDir = paramApplicationInfo.secondaryNativeLibraryDir;
        return applicationInfo;
      } 
    } 
    return paramApplicationInfo;
  }
  
  private void onImplicitDirectBoot(int paramInt) {
    if (StrictMode.vmImplicitDirectBootEnabled())
      if (paramInt == UserHandle.myUserId()) {
        if (this.mUserUnlocked)
          return; 
        if (((UserManager)this.mContext.getSystemService(UserManager.class)).isUserUnlockingOrUnlocked(paramInt)) {
          this.mUserUnlocked = true;
        } else {
          StrictMode.onImplicitDirectBoot();
        } 
      } else if (!((UserManager)this.mContext.getSystemService(UserManager.class)).isUserUnlockingOrUnlocked(paramInt)) {
        StrictMode.onImplicitDirectBoot();
      }  
  }
  
  private void putCachedIcon(ResourceName paramResourceName, Drawable paramDrawable) {
    synchronized (sSync) {
      ArrayMap<ResourceName, WeakReference<Drawable.ConstantState>> arrayMap = sIconCache;
      WeakReference weakReference = new WeakReference();
      this((T)paramDrawable.getConstantState());
      arrayMap.put(paramResourceName, weakReference);
      return;
    } 
  }
  
  private void putCachedString(ResourceName paramResourceName, CharSequence paramCharSequence) {
    synchronized (sSync) {
      ArrayMap<ResourceName, WeakReference<CharSequence>> arrayMap = sStringCache;
      WeakReference weakReference = new WeakReference();
      this((T)paramCharSequence);
      arrayMap.put(paramResourceName, weakReference);
      return;
    } 
  }
  
  public static boolean shouldTraceGrant(String paramString1, String paramString2, int paramInt) {
    return false;
  }
  
  private int updateFlagsForApplication(int paramInt1, int paramInt2) {
    return updateFlagsForPackage(paramInt1, paramInt2);
  }
  
  private int updateFlagsForComponent(int paramInt1, int paramInt2, Intent paramIntent) {
    int i = paramInt1;
    if (paramIntent != null) {
      i = paramInt1;
      if ((paramIntent.getFlags() & 0x100) != 0)
        i = paramInt1 | 0x10000000; 
    } 
    if ((0x100C0000 & i) == 0)
      onImplicitDirectBoot(paramInt2); 
    return i;
  }
  
  private int updateFlagsForPackage(int paramInt1, int paramInt2) {
    if ((paramInt1 & 0xF) != 0 && (0x100C0000 & paramInt1) == 0)
      onImplicitDirectBoot(paramInt2); 
    return paramInt1;
  }
  
  public void addCrossProfileIntentFilter(IntentFilter paramIntentFilter, int paramInt1, int paramInt2, int paramInt3) {
    try {
      this.mPM.addCrossProfileIntentFilter(paramIntentFilter, this.mContext.getOpPackageName(), paramInt1, paramInt2, paramInt3);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void addOnPermissionsChangeListener(PackageManager.OnPermissionsChangedListener paramOnPermissionsChangedListener) {
    synchronized (this.mPermissionListeners) {
      if (this.mPermissionListeners.get(paramOnPermissionsChangedListener) != null)
        return; 
      OnPermissionsChangeListenerDelegate onPermissionsChangeListenerDelegate = new OnPermissionsChangeListenerDelegate();
      this(this, paramOnPermissionsChangedListener, Looper.getMainLooper());
      try {
        this.mPermissionManager.addOnPermissionsChangeListener((IOnPermissionsChangeListener)onPermissionsChangeListenerDelegate);
        this.mPermissionListeners.put(paramOnPermissionsChangedListener, onPermissionsChangeListenerDelegate);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  public void addPackageToPreferred(String paramString) {
    Log.w("ApplicationPackageManager", "addPackageToPreferred() is a no-op");
  }
  
  public boolean addPermission(PermissionInfo paramPermissionInfo) {
    try {
      return this.mPermissionManager.addPermission(paramPermissionInfo, false);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo) {
    try {
      return this.mPermissionManager.addPermission(paramPermissionInfo, true);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName) {
    try {
      this.mPM.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void addPreferredActivityAsUser(IntentFilter paramIntentFilter, int paramInt1, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName, int paramInt2) {
    try {
      this.mPM.addPreferredActivity(paramIntentFilter, paramInt1, paramArrayOfComponentName, paramComponentName, paramInt2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean addWhitelistedRestrictedPermission(String paramString1, String paramString2, int paramInt) {
    try {
      int i = getUserId();
      return this.mPermissionManager.addWhitelistedRestrictedPermission(paramString1, paramString2, paramInt, i);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean arePermissionsIndividuallyControlled() {
    return this.mContext.getResources().getBoolean(17891500);
  }
  
  public boolean canRequestPackageInstalls() {
    try {
      return this.mPM.canRequestPackageInstalls(this.mContext.getPackageName(), getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString) {
    try {
      return this.mPM.canonicalToCurrentPackageNames(paramArrayOfString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int checkPermission(String paramString1, String paramString2) {
    return PermissionManager.checkPackageNamePermission(paramString1, paramString2, getUserId());
  }
  
  public int checkSignatures(int paramInt1, int paramInt2) {
    try {
      return this.mPM.checkUidSignatures(paramInt1, paramInt2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int checkSignatures(String paramString1, String paramString2) {
    try {
      return this.mPM.checkSignatures(paramString1, paramString2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void clearApplicationUserData(String paramString, IPackageDataObserver paramIPackageDataObserver) {
    try {
      this.mPM.clearApplicationUserData(paramString, paramIPackageDataObserver, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void clearCrossProfileIntentFilters(int paramInt) {
    try {
      this.mPM.clearCrossProfileIntentFilters(paramInt, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void clearInstantAppCookie() {
    updateInstantAppCookie((byte[])null);
  }
  
  public void clearPackagePreferredActivities(String paramString) {
    try {
      this.mPM.clearPackagePreferredActivities(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String[] currentToCanonicalPackageNames(String[] paramArrayOfString) {
    try {
      return this.mPM.currentToCanonicalPackageNames(paramArrayOfString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void deleteApplicationCacheFiles(String paramString, IPackageDataObserver paramIPackageDataObserver) {
    try {
      this.mPM.deleteApplicationCacheFiles(paramString, paramIPackageDataObserver);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void deleteApplicationCacheFilesAsUser(String paramString, int paramInt, IPackageDataObserver paramIPackageDataObserver) {
    try {
      this.mPM.deleteApplicationCacheFilesAsUser(paramString, paramInt, paramIPackageDataObserver);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void deletePackage(String paramString, IPackageDeleteObserver paramIPackageDeleteObserver, int paramInt) {
    deletePackageAsUser(paramString, paramIPackageDeleteObserver, paramInt, getUserId());
  }
  
  public void deletePackageAsUser(String paramString, IPackageDeleteObserver paramIPackageDeleteObserver, int paramInt1, int paramInt2) {
    try {
      this.mPM.deletePackageAsUser(paramString, -1, paramIPackageDeleteObserver, paramInt2, paramInt1);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void disableHasSystemFeatureCache() {
    mHasSystemFeatureCache.disableLocal();
  }
  
  public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong) {
    try {
      this.mPM.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void flushPackageRestrictionsAsUser(int paramInt) {
    try {
      this.mPM.flushPackageRestrictionsAsUser(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void freeStorage(String paramString, long paramLong, IntentSender paramIntentSender) {
    try {
      this.mPM.freeStorage(paramString, paramLong, 0, paramIntentSender);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void freeStorageAndNotify(String paramString, long paramLong, IPackageDataObserver paramIPackageDataObserver) {
    try {
      this.mPM.freeStorageAndNotify(paramString, paramLong, 0, paramIPackageDataObserver);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Drawable getActivityBanner(ComponentName paramComponentName) throws PackageManager.NameNotFoundException {
    return getActivityInfo(paramComponentName, 1024).loadBanner(this);
  }
  
  public Drawable getActivityBanner(Intent paramIntent) throws PackageManager.NameNotFoundException {
    if (paramIntent.getComponent() != null)
      return getActivityBanner(paramIntent.getComponent()); 
    ResolveInfo resolveInfo = resolveActivity(paramIntent, 65536);
    if (resolveInfo != null)
      return resolveInfo.activityInfo.loadBanner(this); 
    throw new PackageManager.NameNotFoundException(paramIntent.toUri(0));
  }
  
  public Drawable getActivityIcon(ComponentName paramComponentName) throws PackageManager.NameNotFoundException {
    return getActivityInfo(paramComponentName, 1024).loadIcon(this);
  }
  
  public Drawable getActivityIcon(Intent paramIntent) throws PackageManager.NameNotFoundException {
    if (paramIntent.getComponent() != null)
      return getActivityIcon(paramIntent.getComponent()); 
    ResolveInfo resolveInfo = resolveActivity(paramIntent, 65536);
    if (resolveInfo != null)
      return resolveInfo.activityInfo.loadIcon(this); 
    throw new PackageManager.NameNotFoundException(paramIntent.toUri(0));
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt) throws PackageManager.NameNotFoundException {
    int i = getUserId();
    try {
      ActivityInfo activityInfo = this.mPM.getActivityInfo(paramComponentName, updateFlagsForComponent(paramInt, i, (Intent)null), i);
      if (activityInfo != null)
        return activityInfo; 
      throw new PackageManager.NameNotFoundException(paramComponentName.toString());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Drawable getActivityLogo(ComponentName paramComponentName) throws PackageManager.NameNotFoundException {
    return getActivityInfo(paramComponentName, 1024).loadLogo(this);
  }
  
  public Drawable getActivityLogo(Intent paramIntent) throws PackageManager.NameNotFoundException {
    if (paramIntent.getComponent() != null)
      return getActivityLogo(paramIntent.getComponent()); 
    ResolveInfo resolveInfo = resolveActivity(paramIntent, 65536);
    if (resolveInfo != null)
      return resolveInfo.activityInfo.loadLogo(this); 
    throw new PackageManager.NameNotFoundException(paramIntent.toUri(0));
  }
  
  public List<IntentFilter> getAllIntentFilters(String paramString) {
    try {
      ParceledListSlice parceledListSlice = this.mPM.getAllIntentFilters(paramString);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt) {
    try {
      ParceledListSlice parceledListSlice = this.mPermissionManager.getAllPermissionGroups(paramInt);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getAppPredictionServicePackageName() {
    try {
      return this.mPM.getAppPredictionServicePackageName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo) {
    return paramApplicationInfo.loadBanner(this);
  }
  
  public Drawable getApplicationBanner(String paramString) throws PackageManager.NameNotFoundException {
    return getApplicationBanner(getApplicationInfo(paramString, 1024));
  }
  
  public int getApplicationEnabledSetting(String paramString) {
    try {
      return this.mPM.getApplicationEnabledSetting(paramString, getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean getApplicationHiddenSettingAsUser(String paramString, UserHandle paramUserHandle) {
    try {
      return this.mPM.getApplicationHiddenSettingAsUser(paramString, paramUserHandle.getIdentifier());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo) {
    return paramApplicationInfo.loadIcon(this);
  }
  
  public Drawable getApplicationIcon(String paramString) throws PackageManager.NameNotFoundException {
    return getApplicationIcon(getApplicationInfo(paramString, 1024));
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return getApplicationInfoAsUser(paramString, paramInt, getUserId());
  }
  
  public ApplicationInfo getApplicationInfoAsUser(String paramString, int paramInt1, int paramInt2) throws PackageManager.NameNotFoundException {
    ApplicationInfo applicationInfo = getApplicationInfoAsUserCached(paramString, updateFlagsForApplication(paramInt1, paramInt2), paramInt2);
    if (applicationInfo != null)
      return maybeAdjustApplicationInfo(applicationInfo); 
    throw new PackageManager.NameNotFoundException(paramString);
  }
  
  public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo) {
    return paramApplicationInfo.loadLabel(this);
  }
  
  public Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo) {
    return paramApplicationInfo.loadLogo(this);
  }
  
  public Drawable getApplicationLogo(String paramString) throws PackageManager.NameNotFoundException {
    return getApplicationLogo(getApplicationInfo(paramString, 1024));
  }
  
  public ArtManager getArtManager() {
    synchronized (this.mLock) {
      ArtManager artManager = this.mArtManager;
      if (artManager == null)
        try {
          artManager = new ArtManager();
          this(this.mContext, this.mPM.getArtManager());
          this.mArtManager = artManager;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      artManager = this.mArtManager;
      return artManager;
    } 
  }
  
  public String getAttentionServicePackageName() {
    try {
      return this.mPM.getAttentionServicePackageName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public CharSequence getBackgroundPermissionOptionLabel() {
    try {
      String str = getPermissionControllerPackageName();
      Context context = this.mContext.createPackageContext(str, 0);
      int i = context.getResources().getIdentifier("app_permission_button_allow_always", "string", "com.android.permissioncontroller");
      if (i != 0)
        return context.getText(i); 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.e("ApplicationPackageManager", "Permission controller not found.", (Throwable)nameNotFoundException);
    } 
    return "";
  }
  
  public Intent getCarLaunchIntentForPackage(String paramString) {
    return getLaunchIntentForPackageAndCategory(paramString, "android.intent.category.CAR_LAUNCHER");
  }
  
  public ChangedPackages getChangedPackages(int paramInt) {
    try {
      return this.mPM.getChangedPackages(paramInt, getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getComponentEnabledSetting(ComponentName paramComponentName) {
    try {
      return this.mPM.getComponentEnabledSetting(paramComponentName, getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getContentCaptureServicePackageName() {
    try {
      return this.mPM.getContentCaptureServicePackageName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public List<SharedLibraryInfo> getDeclaredSharedLibraries(String paramString, int paramInt) {
    try {
      List<?> list;
      ParceledListSlice parceledListSlice = this.mPM.getDeclaredSharedLibraries(paramString, paramInt, this.mContext.getUserId());
      if (parceledListSlice != null) {
        list = parceledListSlice.getList();
      } else {
        list = Collections.emptyList();
      } 
      return (List)list;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Drawable getDefaultActivityIcon() {
    return this.mContext.getDrawable(17301651);
  }
  
  public String getDefaultBrowserPackageNameAsUser(int paramInt) {
    try {
      return this.mPermissionManager.getDefaultBrowser(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getDefaultTextClassifierPackageName() {
    try {
      return this.mPM.getDefaultTextClassifierPackageName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo) {
    ResourceName resourceName = new ResourceName(paramString, paramInt);
    Drawable drawable = getCachedIcon(resourceName);
    if (drawable != null)
      return drawable; 
    ApplicationInfo applicationInfo = paramApplicationInfo;
    if (paramApplicationInfo == null)
      try {
        applicationInfo = getApplicationInfo(paramString, 1024);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        return null;
      }  
    if (paramInt != 0)
      try {
        Drawable drawable1 = getResourcesForApplication(applicationInfo).getDrawable(paramInt, null);
        if (drawable1 != null)
          putCachedIcon(resourceName, drawable1); 
        return drawable1;
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failure retrieving resources for ");
        stringBuilder.append(applicationInfo.packageName);
        Log.w("PackageManager", stringBuilder.toString());
      } catch (android.content.res.Resources.NotFoundException notFoundException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failure retrieving resources for ");
        stringBuilder.append(applicationInfo.packageName);
        stringBuilder.append(": ");
        stringBuilder.append(notFoundException.getMessage());
        Log.w("PackageManager", stringBuilder.toString());
      } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failure retrieving icon 0x");
        stringBuilder.append(Integer.toHexString(paramInt));
        stringBuilder.append(" in package ");
        stringBuilder.append((String)notFoundException);
        Log.w("PackageManager", stringBuilder.toString(), exception);
      }  
    return null;
  }
  
  public CharSequence getHarmfulAppWarning(String paramString) {
    try {
      return this.mPM.getHarmfulAppWarning(paramString, getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public ComponentName getHomeActivities(List<ResolveInfo> paramList) {
    try {
      return this.mPM.getHomeActivities(paramList);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getIncidentReportApproverPackageName() {
    try {
      return this.mPM.getIncidentReportApproverPackageName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public int getInstallReason(String paramString, UserHandle paramUserHandle) {
    try {
      return this.mPM.getInstallReason(paramString, paramUserHandle.getIdentifier());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public InstallSourceInfo getInstallSourceInfo(String paramString) throws PackageManager.NameNotFoundException {
    try {
      InstallSourceInfo installSourceInfo = this.mPM.getInstallSourceInfo(paramString);
      if (installSourceInfo != null)
        return installSourceInfo; 
      throw new PackageManager.NameNotFoundException(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt) {
    return getInstalledApplicationsAsUser(paramInt, getUserId());
  }
  
  public List<ApplicationInfo> getInstalledApplicationsAsUser(int paramInt1, int paramInt2) {
    try {
      ParceledListSlice parceledListSlice = this.mPM.getInstalledApplications(updateFlagsForApplication(paramInt1, paramInt2), paramInt2);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ModuleInfo> getInstalledModules(int paramInt) {
    try {
      return this.mPM.getInstalledModules(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<PackageInfo> getInstalledPackages(int paramInt) {
    return getInstalledPackagesAsUser(paramInt, getUserId());
  }
  
  public List<PackageInfo> getInstalledPackagesAsUser(int paramInt1, int paramInt2) {
    try {
      ParceledListSlice parceledListSlice = this.mPM.getInstalledPackages(updateFlagsForPackage(paramInt1, paramInt2), paramInt2);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getInstallerPackageName(String paramString) {
    try {
      return this.mPM.getInstallerPackageName(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getInstantAppAndroidId(String paramString, UserHandle paramUserHandle) {
    try {
      return this.mPM.getInstantAppAndroidId(paramString, paramUserHandle.getIdentifier());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public byte[] getInstantAppCookie() {
    try {
      null = this.mPM.getInstantAppCookie(this.mContext.getPackageName(), getUserId());
      return (null != null) ? null : EmptyArray.BYTE;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getInstantAppCookieMaxBytes() {
    return Settings.Global.getInt(this.mContext.getContentResolver(), "ephemeral_cookie_max_size_bytes", 16384);
  }
  
  public int getInstantAppCookieMaxSize() {
    return getInstantAppCookieMaxBytes();
  }
  
  public Drawable getInstantAppIcon(String paramString) {
    try {
      Bitmap bitmap = this.mPM.getInstantAppIcon(paramString, getUserId());
      return (Drawable)((bitmap != null) ? new BitmapDrawable(null, bitmap) : null);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ComponentName getInstantAppInstallerComponent() {
    try {
      return this.mPM.getInstantAppInstallerComponent();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public ComponentName getInstantAppResolverSettingsComponent() {
    try {
      return this.mPM.getInstantAppResolverSettingsComponent();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public List<InstantAppInfo> getInstantApps() {
    try {
      ParceledListSlice parceledListSlice = this.mPM.getInstantApps(getUserId());
      return (parceledListSlice != null) ? parceledListSlice.getList() : Collections.emptyList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt) throws PackageManager.NameNotFoundException {
    try {
      InstrumentationInfo instrumentationInfo = this.mPM.getInstrumentationInfo(paramComponentName, paramInt);
      if (instrumentationInfo != null)
        return instrumentationInfo; 
      throw new PackageManager.NameNotFoundException(paramComponentName.toString());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<IntentFilterVerificationInfo> getIntentFilterVerifications(String paramString) {
    try {
      ParceledListSlice parceledListSlice = this.mPM.getIntentFilterVerifications(paramString);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getIntentVerificationStatusAsUser(String paramString, int paramInt) {
    try {
      return this.mPM.getIntentVerificationStatus(paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public KeySet getKeySetByAlias(String paramString1, String paramString2) {
    Objects.requireNonNull(paramString1);
    Objects.requireNonNull(paramString2);
    try {
      return this.mPM.getKeySetByAlias(paramString1, paramString2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Intent getLaunchIntentForPackage(String paramString) {
    // Byte code:
    //   0: new android/content/Intent
    //   3: dup
    //   4: ldc_w 'android.intent.action.MAIN'
    //   7: invokespecial <init> : (Ljava/lang/String;)V
    //   10: astore_2
    //   11: aload_2
    //   12: ldc_w 'android.intent.category.INFO'
    //   15: invokevirtual addCategory : (Ljava/lang/String;)Landroid/content/Intent;
    //   18: pop
    //   19: aload_2
    //   20: aload_1
    //   21: invokevirtual setPackage : (Ljava/lang/String;)Landroid/content/Intent;
    //   24: pop
    //   25: aload_0
    //   26: aload_2
    //   27: iconst_0
    //   28: invokevirtual queryIntentActivities : (Landroid/content/Intent;I)Ljava/util/List;
    //   31: astore_3
    //   32: aload_3
    //   33: ifnull -> 48
    //   36: aload_3
    //   37: astore #4
    //   39: aload_3
    //   40: invokeinterface size : ()I
    //   45: ifgt -> 77
    //   48: aload_2
    //   49: ldc_w 'android.intent.category.INFO'
    //   52: invokevirtual removeCategory : (Ljava/lang/String;)V
    //   55: aload_2
    //   56: ldc_w 'android.intent.category.LAUNCHER'
    //   59: invokevirtual addCategory : (Ljava/lang/String;)Landroid/content/Intent;
    //   62: pop
    //   63: aload_2
    //   64: aload_1
    //   65: invokevirtual setPackage : (Ljava/lang/String;)Landroid/content/Intent;
    //   68: pop
    //   69: aload_0
    //   70: aload_2
    //   71: iconst_0
    //   72: invokevirtual queryIntentActivities : (Landroid/content/Intent;I)Ljava/util/List;
    //   75: astore #4
    //   77: aload #4
    //   79: ifnull -> 153
    //   82: aload #4
    //   84: invokeinterface size : ()I
    //   89: ifgt -> 95
    //   92: goto -> 153
    //   95: new android/content/Intent
    //   98: dup
    //   99: aload_2
    //   100: invokespecial <init> : (Landroid/content/Intent;)V
    //   103: astore_1
    //   104: aload_1
    //   105: ldc_w 268435456
    //   108: invokevirtual setFlags : (I)Landroid/content/Intent;
    //   111: pop
    //   112: aload_1
    //   113: aload #4
    //   115: iconst_0
    //   116: invokeinterface get : (I)Ljava/lang/Object;
    //   121: checkcast android/content/pm/ResolveInfo
    //   124: getfield activityInfo : Landroid/content/pm/ActivityInfo;
    //   127: getfield packageName : Ljava/lang/String;
    //   130: aload #4
    //   132: iconst_0
    //   133: invokeinterface get : (I)Ljava/lang/Object;
    //   138: checkcast android/content/pm/ResolveInfo
    //   141: getfield activityInfo : Landroid/content/pm/ActivityInfo;
    //   144: getfield name : Ljava/lang/String;
    //   147: invokevirtual setClassName : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   150: pop
    //   151: aload_1
    //   152: areturn
    //   153: aconst_null
    //   154: areturn
  }
  
  public Intent getLeanbackLaunchIntentForPackage(String paramString) {
    return getLaunchIntentForPackageAndCategory(paramString, "android.intent.category.LEANBACK_LAUNCHER");
  }
  
  public Set<String> getMimeGroup(String paramString) {
    try {
      return (Set<String>)new ArraySet(this.mPM.getMimeGroup(this.mContext.getPackageName(), paramString));
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public ModuleInfo getModuleInfo(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    try {
      ModuleInfo moduleInfo = this.mPM.getModuleInfo(paramString, paramInt);
      if (moduleInfo != null)
        return moduleInfo; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("No module info for package: ");
      stringBuilder.append(paramString);
      throw new PackageManager.NameNotFoundException(stringBuilder.toString());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getMoveStatus(int paramInt) {
    try {
      return this.mPM.getMoveStatus(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getNameForUid(int paramInt) {
    try {
      return this.mPM.getNameForUid(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String[] getNamesForUids(int[] paramArrayOfint) {
    try {
      return this.mPM.getNamesForUids(paramArrayOfint);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<VolumeInfo> getPackageCandidateVolumes(ApplicationInfo paramApplicationInfo) {
    return getPackageCandidateVolumes(paramApplicationInfo, (StorageManager)this.mContext.getSystemService(StorageManager.class), this.mPM);
  }
  
  protected List<VolumeInfo> getPackageCandidateVolumes(ApplicationInfo paramApplicationInfo, StorageManager paramStorageManager, IPackageManager paramIPackageManager) {
    VolumeInfo volumeInfo = getPackageCurrentVolume(paramApplicationInfo, paramStorageManager);
    List list = paramStorageManager.getVolumes();
    ArrayList<VolumeInfo> arrayList = new ArrayList();
    for (VolumeInfo volumeInfo1 : list) {
      if (Objects.equals(volumeInfo1, volumeInfo) || isPackageCandidateVolume(this.mContext, paramApplicationInfo, volumeInfo1, paramIPackageManager))
        arrayList.add(volumeInfo1); 
    } 
    return arrayList;
  }
  
  public VolumeInfo getPackageCurrentVolume(ApplicationInfo paramApplicationInfo) {
    return getPackageCurrentVolume(paramApplicationInfo, (StorageManager)this.mContext.getSystemService(StorageManager.class));
  }
  
  protected VolumeInfo getPackageCurrentVolume(ApplicationInfo paramApplicationInfo, StorageManager paramStorageManager) {
    return paramApplicationInfo.isInternal() ? paramStorageManager.findVolumeById("private") : paramStorageManager.findVolumeByUuid(paramApplicationInfo.volumeUuid);
  }
  
  public int[] getPackageGids(String paramString) throws PackageManager.NameNotFoundException {
    return getPackageGids(paramString, 0);
  }
  
  public int[] getPackageGids(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    int i = getUserId();
    try {
      int[] arrayOfInt = this.mPM.getPackageGids(paramString, updateFlagsForPackage(paramInt, i), i);
      if (arrayOfInt != null)
        return arrayOfInt; 
      throw new PackageManager.NameNotFoundException(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public PackageInfo getPackageInfo(VersionedPackage paramVersionedPackage, int paramInt) throws PackageManager.NameNotFoundException {
    int i = getUserId();
    try {
      PackageInfo packageInfo = this.mPM.getPackageInfoVersioned(paramVersionedPackage, updateFlagsForPackage(paramInt, i), i);
      if (packageInfo != null)
        return packageInfo; 
      throw new PackageManager.NameNotFoundException(paramVersionedPackage.toString());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return getPackageInfoAsUser(paramString, paramInt, getUserId());
  }
  
  public PackageInfo getPackageInfoAsUser(String paramString, int paramInt1, int paramInt2) throws PackageManager.NameNotFoundException {
    PackageInfo packageInfo = getPackageInfoAsUserCached(paramString, updateFlagsForPackage(paramInt1, paramInt2), paramInt2);
    if (packageInfo != null)
      return packageInfo; 
    throw new PackageManager.NameNotFoundException(paramString);
  }
  
  public PackageInstaller getPackageInstaller() {
    synchronized (this.mLock) {
      PackageInstaller packageInstaller = this.mInstaller;
      if (packageInstaller == null)
        try {
          packageInstaller = new PackageInstaller();
          this(this.mPM.getPackageInstaller(), this.mContext.getPackageName(), getUserId());
          this.mInstaller = packageInstaller;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      packageInstaller = this.mInstaller;
      return packageInstaller;
    } 
  }
  
  public void getPackageSizeInfoAsUser(String paramString, int paramInt, IPackageStatsObserver paramIPackageStatsObserver) {
    if ((this.mContext.getApplicationInfo()).targetSdkVersion < 26) {
      if (paramIPackageStatsObserver != null) {
        Log.d("ApplicationPackageManager", "Shame on you for calling the hidden API getPackageSizeInfoAsUser(). Shame!");
        try {
          paramIPackageStatsObserver.onGetStatsCompleted(null, false);
        } catch (RemoteException remoteException) {}
      } 
      return;
    } 
    throw new UnsupportedOperationException("Shame on you for calling the hidden API getPackageSizeInfoAsUser(). Shame!");
  }
  
  public int getPackageUid(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return getPackageUidAsUser(paramString, paramInt, getUserId());
  }
  
  public int getPackageUidAsUser(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return getPackageUidAsUser(paramString, 0, paramInt);
  }
  
  public int getPackageUidAsUser(String paramString, int paramInt1, int paramInt2) throws PackageManager.NameNotFoundException {
    try {
      paramInt1 = this.mPM.getPackageUid(paramString, updateFlagsForPackage(paramInt1, paramInt2), paramInt2);
      if (paramInt1 >= 0)
        return paramInt1; 
      throw new PackageManager.NameNotFoundException(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String[] getPackagesForUid(int paramInt) {
    try {
      return this.mPM.getPackagesForUid(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt) {
    int i = getUserId();
    try {
      ParceledListSlice parceledListSlice = this.mPM.getPackagesHoldingPermissions(paramArrayOfString, updateFlagsForPackage(paramInt, i), i);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getPermissionControllerPackageName() {
    synchronized (this.mLock) {
      String str = this.mPermissionsControllerPackageName;
      if (str == null)
        try {
          this.mPermissionsControllerPackageName = this.mPM.getPermissionControllerPackageName();
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      str = this.mPermissionsControllerPackageName;
      return str;
    } 
  }
  
  public int getPermissionFlags(String paramString1, String paramString2, UserHandle paramUserHandle) {
    try {
      return this.mPermissionManager.getPermissionFlags(paramString1, paramString2, paramUserHandle.getIdentifier());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    try {
      PermissionGroupInfo permissionGroupInfo = this.mPermissionManager.getPermissionGroupInfo(paramString, paramInt);
      if (permissionGroupInfo != null)
        return permissionGroupInfo; 
      throw new PackageManager.NameNotFoundException(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    try {
      String str = this.mContext.getOpPackageName();
      PermissionInfo permissionInfo = this.mPermissionManager.getPermissionInfo(paramString, str, paramInt);
      if (permissionInfo != null)
        return permissionInfo; 
      throw new PackageManager.NameNotFoundException(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString) {
    try {
      return this.mPM.getPreferredActivities(paramList, paramList1, paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<PackageInfo> getPreferredPackages(int paramInt) {
    Log.w("ApplicationPackageManager", "getPreferredPackages() is a no-op");
    return Collections.emptyList();
  }
  
  public List<VolumeInfo> getPrimaryStorageCandidateVolumes() {
    StorageManager storageManager = (StorageManager)this.mContext.getSystemService(StorageManager.class);
    VolumeInfo volumeInfo = getPrimaryStorageCurrentVolume();
    List list = storageManager.getVolumes();
    ArrayList<VolumeInfo> arrayList = new ArrayList();
    if (Objects.equals("primary_physical", storageManager.getPrimaryStorageUuid()) && volumeInfo != null) {
      arrayList.add(volumeInfo);
    } else {
      for (VolumeInfo volumeInfo1 : list) {
        if (Objects.equals(volumeInfo1, volumeInfo) || isPrimaryStorageCandidateVolume(volumeInfo1))
          arrayList.add(volumeInfo1); 
      } 
    } 
    return arrayList;
  }
  
  public VolumeInfo getPrimaryStorageCurrentVolume() {
    StorageManager storageManager = (StorageManager)this.mContext.getSystemService(StorageManager.class);
    return storageManager.findVolumeByQualifiedUuid(storageManager.getPrimaryStorageUuid());
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt) throws PackageManager.NameNotFoundException {
    int i = getUserId();
    try {
      ProviderInfo providerInfo = this.mPM.getProviderInfo(paramComponentName, updateFlagsForComponent(paramInt, i, (Intent)null), i);
      if (providerInfo != null)
        return providerInfo; 
      throw new PackageManager.NameNotFoundException(paramComponentName.toString());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt) throws PackageManager.NameNotFoundException {
    int i = getUserId();
    try {
      ActivityInfo activityInfo = this.mPM.getReceiverInfo(paramComponentName, updateFlagsForComponent(paramInt, i, (Intent)null), i);
      if (activityInfo != null)
        return activityInfo; 
      throw new PackageManager.NameNotFoundException(paramComponentName.toString());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Resources getResourcesForActivity(ComponentName paramComponentName) throws PackageManager.NameNotFoundException {
    return getResourcesForApplication((getActivityInfo(paramComponentName, 1024)).applicationInfo);
  }
  
  public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo) throws PackageManager.NameNotFoundException {
    boolean bool;
    String str;
    String[] arrayOfString;
    if (paramApplicationInfo.packageName.equals("system"))
      return this.mContext.mMainThread.getSystemUiContext().getResources(); 
    if (paramApplicationInfo.uid == Process.myUid()) {
      bool = true;
    } else {
      bool = false;
    } 
    ActivityThread activityThread = this.mContext.mMainThread;
    if (bool) {
      str = paramApplicationInfo.sourceDir;
    } else {
      str = paramApplicationInfo.publicSourceDir;
    } 
    if (bool) {
      arrayOfString = paramApplicationInfo.splitSourceDirs;
    } else {
      arrayOfString = paramApplicationInfo.splitPublicSourceDirs;
    } 
    Resources resources = activityThread.getTopLevelResources(str, arrayOfString, paramApplicationInfo.resourceDirs, paramApplicationInfo.sharedLibraryFiles, 0, this.mContext.mPackageInfo);
    if (resources != null)
      return resources; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unable to open ");
    stringBuilder.append(paramApplicationInfo.publicSourceDir);
    throw new PackageManager.NameNotFoundException(stringBuilder.toString());
  }
  
  public Resources getResourcesForApplication(String paramString) throws PackageManager.NameNotFoundException {
    return getResourcesForApplication(getApplicationInfo(paramString, 1024));
  }
  
  public Resources getResourcesForApplicationAsUser(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    if (paramInt >= 0) {
      if ("system".equals(paramString))
        return this.mContext.mMainThread.getSystemUiContext().getResources(); 
      try {
        ApplicationInfo applicationInfo = this.mPM.getApplicationInfo(paramString, 1024, paramInt);
        if (applicationInfo != null)
          return getResourcesForApplication(applicationInfo); 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Package ");
        stringBuilder1.append(paramString);
        stringBuilder1.append(" doesn't exist");
        throw new PackageManager.NameNotFoundException(stringBuilder1.toString());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Call does not support special user #");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt) throws PackageManager.NameNotFoundException {
    int i = getUserId();
    try {
      ServiceInfo serviceInfo = this.mPM.getServiceInfo(paramComponentName, updateFlagsForComponent(paramInt, i, (Intent)null), i);
      if (serviceInfo != null)
        return serviceInfo; 
      throw new PackageManager.NameNotFoundException(paramComponentName.toString());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getServicesSystemSharedLibraryPackageName() {
    try {
      return this.mPM.getServicesSystemSharedLibraryPackageName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getSetupWizardPackageName() {
    try {
      return this.mPM.getSetupWizardPackageName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public List<SharedLibraryInfo> getSharedLibraries(int paramInt) {
    return getSharedLibrariesAsUser(paramInt, getUserId());
  }
  
  public List<SharedLibraryInfo> getSharedLibrariesAsUser(int paramInt1, int paramInt2) {
    try {
      ParceledListSlice parceledListSlice = this.mPM.getSharedLibraries(this.mContext.getOpPackageName(), paramInt1, paramInt2);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getSharedSystemSharedLibraryPackageName() {
    try {
      return this.mPM.getSharedSystemSharedLibraryPackageName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public KeySet getSigningKeySet(String paramString) {
    Objects.requireNonNull(paramString);
    try {
      return this.mPM.getSigningKeySet(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Bundle getSuspendedPackageAppExtras() {
    try {
      return this.mPM.getSuspendedPackageAppExtras(this.mContext.getOpPackageName(), getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean getSyntheticAppDetailsActivityEnabled(String paramString) {
    try {
      ComponentName componentName = new ComponentName();
      this(paramString, APP_DETAILS_ACTIVITY_CLASS_NAME);
      int i = this.mPM.getComponentEnabledSetting(componentName, getUserId());
      boolean bool1 = true;
      boolean bool2 = bool1;
      if (i != 1)
        if (i == 0) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
      return bool2;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public FeatureInfo[] getSystemAvailableFeatures() {
    try {
      ParceledListSlice parceledListSlice = this.mPM.getSystemAvailableFeatures();
      if (parceledListSlice == null)
        return new FeatureInfo[0]; 
      List<FeatureInfo> list = parceledListSlice.getList();
      FeatureInfo[] arrayOfFeatureInfo = new FeatureInfo[list.size()];
      for (byte b = 0; b < arrayOfFeatureInfo.length; b++)
        arrayOfFeatureInfo[b] = list.get(b); 
      return arrayOfFeatureInfo;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getSystemCaptionsServicePackageName() {
    try {
      return this.mPM.getSystemCaptionsServicePackageName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public String[] getSystemSharedLibraryNames() {
    try {
      return this.mPM.getSystemSharedLibraryNames();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getSystemTextClassifierPackageName() {
    try {
      return this.mPM.getSystemTextClassifierPackageName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo) {
    StringBuilder stringBuilder;
    ResourceName resourceName = new ResourceName(paramString, paramInt);
    CharSequence charSequence = getCachedString(resourceName);
    if (charSequence != null)
      return charSequence; 
    ApplicationInfo applicationInfo = paramApplicationInfo;
    if (paramApplicationInfo == null)
      try {
        applicationInfo = getApplicationInfo(paramString, 1024);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        return null;
      }  
    try {
      CharSequence charSequence1 = getResourcesForApplication(applicationInfo).getText(paramInt);
      putCachedString(resourceName, charSequence1);
      return charSequence1;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Failure retrieving resources for ");
      stringBuilder.append(applicationInfo.packageName);
      Log.w("PackageManager", stringBuilder.toString());
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Failure retrieving text 0x");
      stringBuilder1.append(Integer.toHexString(paramInt));
      stringBuilder1.append(" in package ");
      stringBuilder1.append((String)stringBuilder);
      Log.w("PackageManager", stringBuilder1.toString(), runtimeException);
    } 
    return null;
  }
  
  public int getUidForSharedUser(String paramString) throws PackageManager.NameNotFoundException {
    try {
      int i = this.mPM.getUidForSharedUser(paramString);
      if (i != -1)
        return i; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("No shared userid for user:");
      stringBuilder.append(paramString);
      throw new PackageManager.NameNotFoundException(stringBuilder.toString());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String[] getUnsuspendablePackages(String[] paramArrayOfString) {
    try {
      return this.mPM.getUnsuspendablePackagesForUser(paramArrayOfString, this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Drawable getUserBadgeForDensity(UserHandle paramUserHandle, int paramInt) {
    Drawable drawable1 = getProfileIconForDensity(paramUserHandle, 17302383, paramInt);
    if (drawable1 == null)
      return null; 
    Drawable drawable2 = getDrawableForDensity(getUserManager().getUserBadgeResId(paramUserHandle.getIdentifier()), paramInt);
    drawable2.setTint(getUserBadgeColor(paramUserHandle, false));
    return (Drawable)new LayerDrawable(new Drawable[] { drawable1, drawable2 });
  }
  
  public Drawable getUserBadgeForDensityNoBackground(UserHandle paramUserHandle, int paramInt) {
    if (!hasUserBadge(paramUserHandle.getIdentifier()))
      return null; 
    Drawable drawable = getDrawableForDensity(getUserManager().getUserBadgeNoBackgroundResId(paramUserHandle.getIdentifier()), paramInt);
    if (drawable != null)
      drawable.setTint(getUserBadgeColor(paramUserHandle, true)); 
    return drawable;
  }
  
  public Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt) {
    Drawable drawable = getUserBadgeForDensity(paramUserHandle, paramInt);
    return (drawable == null) ? paramDrawable : getBadgedDrawable(paramDrawable, drawable, paramRect, true);
  }
  
  public Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle) {
    return !hasUserBadge(paramUserHandle.getIdentifier()) ? paramDrawable : getBadgedDrawable(paramDrawable, (new LauncherIcons(this.mContext)).getBadgeDrawable(getUserManager().getUserIconBadgeResId(paramUserHandle.getIdentifier()), getUserBadgeColor(paramUserHandle, false)), (Rect)null, true);
  }
  
  public CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle) {
    return getUserManager().getBadgedLabelForUser(paramCharSequence, paramUserHandle);
  }
  
  public int getUserId() {
    return this.mContext.getUserId();
  }
  
  UserManager getUserManager() {
    synchronized (this.mLock) {
      if (this.mUserManager == null)
        this.mUserManager = UserManager.get(this.mContext); 
      return this.mUserManager;
    } 
  }
  
  public VerifierDeviceIdentity getVerifierDeviceIdentity() {
    try {
      return this.mPM.getVerifierDeviceIdentity();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getWellbeingPackageName() {
    try {
      return this.mPM.getWellbeingPackageName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public Set<String> getWhitelistedRestrictedPermissions(String paramString, int paramInt) {
    try {
      int i = getUserId();
      List list = this.mPermissionManager.getWhitelistedRestrictedPermissions(paramString, paramInt, i);
      return (Set<String>)((list != null) ? new ArraySet(list) : Collections.emptySet());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo) {
    StringBuilder stringBuilder;
    ApplicationInfo applicationInfo = paramApplicationInfo;
    if (paramApplicationInfo == null)
      try {
        applicationInfo = getApplicationInfo(paramString, 1024);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        return null;
      }  
    try {
      return getResourcesForApplication(applicationInfo).getXml(paramInt);
    } catch (RuntimeException runtimeException) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Failure retrieving xml 0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      stringBuilder.append(" in package ");
      stringBuilder.append((String)nameNotFoundException);
      Log.w("PackageManager", stringBuilder.toString(), runtimeException);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException1) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Failure retrieving resources for ");
      stringBuilder1.append(((ApplicationInfo)stringBuilder).packageName);
      Log.w("PackageManager", stringBuilder1.toString());
    } 
    return null;
  }
  
  public void grantRuntimePermission(String paramString1, String paramString2, UserHandle paramUserHandle) {
    try {
      this.mPM.grantRuntimePermission(paramString1, paramString2, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean hasSigningCertificate(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    try {
      return this.mPM.hasUidSigningCertificate(paramInt1, paramArrayOfbyte, paramInt2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean hasSigningCertificate(String paramString, byte[] paramArrayOfbyte, int paramInt) {
    try {
      return this.mPM.hasSigningCertificate(paramString, paramArrayOfbyte, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean hasSystemFeature(String paramString) {
    return hasSystemFeature(paramString, 0);
  }
  
  public boolean hasSystemFeature(String paramString, int paramInt) {
    return ((Boolean)mHasSystemFeatureCache.query(new HasSystemFeatureQuery(paramString, paramInt))).booleanValue();
  }
  
  public int installExistingPackage(String paramString) throws PackageManager.NameNotFoundException {
    return installExistingPackage(paramString, 0);
  }
  
  public int installExistingPackage(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return installExistingPackageAsUser(paramString, paramInt, getUserId());
  }
  
  public int installExistingPackageAsUser(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return installExistingPackageAsUser(paramString, 0, paramInt);
  }
  
  protected boolean isAllow3rdPartyOnInternal(Context paramContext) {
    return paramContext.getResources().getBoolean(17891341);
  }
  
  public boolean isAutoRevokeWhitelisted() {
    try {
      return this.mPM.isAutoRevokeWhitelisted(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public boolean isAutoRevokeWhitelisted(String paramString) {
    try {
      int i = getUserId();
      return this.mPermissionManager.isAutoRevokeWhitelisted(paramString, i);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isDeviceUpgrading() {
    try {
      return this.mPM.isDeviceUpgrading();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  protected boolean isForceAllowOnExternal(Context paramContext) {
    ContentResolver contentResolver = paramContext.getContentResolver();
    boolean bool = false;
    if (Settings.Global.getInt(contentResolver, "force_allow_on_external", 0) != 0)
      bool = true; 
    return bool;
  }
  
  public boolean isInstantApp() {
    return isInstantApp(this.mContext.getPackageName());
  }
  
  public boolean isInstantApp(String paramString) {
    try {
      return this.mPM.isInstantApp(paramString, getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isPackageAvailable(String paramString) {
    try {
      return this.mPM.isPackageAvailable(paramString, getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isPackageStateProtected(String paramString, int paramInt) {
    try {
      return this.mPM.isPackageStateProtected(paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public boolean isPackageSuspended() {
    return isPackageSuspendedForUser(this.mContext.getOpPackageName(), getUserId());
  }
  
  public boolean isPackageSuspended(String paramString) throws PackageManager.NameNotFoundException {
    try {
      return isPackageSuspendedForUser(paramString, getUserId());
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new PackageManager.NameNotFoundException(paramString);
    } 
  }
  
  public boolean isPackageSuspendedForUser(String paramString, int paramInt) {
    try {
      return this.mPM.isPackageSuspendedForUser(paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isPermissionRevokedByPolicy(String paramString1, String paramString2) {
    try {
      return this.mPermissionManager.isPermissionRevokedByPolicy(paramString1, paramString2, getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isSafeMode() {
    try {
      int i = this.mCachedSafeMode;
      boolean bool = true;
      if (i < 0) {
        if (this.mPM.isSafeMode()) {
          i = 1;
        } else {
          i = 0;
        } 
        this.mCachedSafeMode = i;
      } 
      i = this.mCachedSafeMode;
      if (i == 0)
        bool = false; 
      return bool;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isSignedBy(String paramString, KeySet paramKeySet) {
    Objects.requireNonNull(paramString);
    Objects.requireNonNull(paramKeySet);
    try {
      return this.mPM.isPackageSignedByKeySet(paramString, paramKeySet);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isSignedByExactly(String paramString, KeySet paramKeySet) {
    Objects.requireNonNull(paramString);
    Objects.requireNonNull(paramKeySet);
    try {
      return this.mPM.isPackageSignedByKeySetExactly(paramString, paramKeySet);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isUpgrade() {
    return isDeviceUpgrading();
  }
  
  public boolean isWirelessConsentModeEnabled() {
    return this.mContext.getResources().getBoolean(17891598);
  }
  
  public Drawable loadItemIcon(PackageItemInfo paramPackageItemInfo, ApplicationInfo paramApplicationInfo) {
    Drawable drawable = loadUnbadgedItemIcon(paramPackageItemInfo, paramApplicationInfo);
    return (paramPackageItemInfo.showUserIcon != -10000) ? drawable : getUserBadgedIcon(drawable, new UserHandle(getUserId()));
  }
  
  public Drawable loadUnbadgedItemIcon(PackageItemInfo paramPackageItemInfo, ApplicationInfo paramApplicationInfo) {
    if (paramPackageItemInfo.showUserIcon != -10000) {
      int i = paramPackageItemInfo.showUserIcon;
      return UserIcons.getDefaultUserIcon(this.mContext.getResources(), i, false);
    } 
    Drawable drawable2 = null;
    if (paramPackageItemInfo.packageName != null)
      drawable2 = getDrawable(paramPackageItemInfo.packageName, paramPackageItemInfo.icon, paramApplicationInfo); 
    Drawable drawable3 = drawable2;
    if (drawable2 == null) {
      drawable3 = drawable2;
      if (paramPackageItemInfo != paramApplicationInfo) {
        drawable3 = drawable2;
        if (paramApplicationInfo != null)
          drawable3 = loadUnbadgedItemIcon((PackageItemInfo)paramApplicationInfo, paramApplicationInfo); 
      } 
    } 
    Drawable drawable1 = drawable3;
    if (drawable3 == null)
      drawable1 = paramPackageItemInfo.loadDefaultIcon(this); 
    return drawable1;
  }
  
  public int movePackage(String paramString, VolumeInfo paramVolumeInfo) {
    try {
      String str;
      if ("private".equals(paramVolumeInfo.id)) {
        str = StorageManager.UUID_PRIVATE_INTERNAL;
      } else if (str.isPrimaryPhysical()) {
        str = "primary_physical";
      } else {
        str = ((VolumeInfo)str).fsUuid;
        Objects.requireNonNull(str);
        str = str;
      } 
      return this.mPM.movePackage(paramString, str);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int movePrimaryStorage(VolumeInfo paramVolumeInfo) {
    try {
      String str;
      if ("private".equals(paramVolumeInfo.id)) {
        str = StorageManager.UUID_PRIVATE_INTERNAL;
      } else if (str.isPrimaryPhysical()) {
        str = "primary_physical";
      } else {
        str = ((VolumeInfo)str).fsUuid;
        Objects.requireNonNull(str);
        str = str;
      } 
      return this.mPM.movePrimaryStorage(str);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt) {
    return queryBroadcastReceiversAsUser(paramIntent, paramInt, getUserId());
  }
  
  public List<ResolveInfo> queryBroadcastReceiversAsUser(Intent paramIntent, int paramInt1, int paramInt2) {
    try {
      ParceledListSlice parceledListSlice = this.mPM.queryIntentReceivers(paramIntent, paramIntent.resolveTypeIfNeeded(this.mContext.getContentResolver()), updateFlagsForComponent(paramInt1, paramInt2, paramIntent), paramInt2);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2) {
    return queryContentProviders(paramString, paramInt1, paramInt2, (String)null);
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString1, int paramInt1, int paramInt2, String paramString2) {
    try {
      List<?> list;
      ParceledListSlice parceledListSlice = this.mPM.queryContentProviders(paramString1, paramInt1, updateFlagsForComponent(paramInt2, UserHandle.getUserId(paramInt1), (Intent)null), paramString2);
      if (parceledListSlice != null) {
        list = parceledListSlice.getList();
      } else {
        list = Collections.emptyList();
      } 
      return (List)list;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt) {
    try {
      ParceledListSlice parceledListSlice = this.mPM.queryInstrumentation(paramString, paramInt);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt) {
    return queryIntentActivitiesAsUser(paramIntent, paramInt, getUserId());
  }
  
  public List<ResolveInfo> queryIntentActivitiesAsUser(Intent paramIntent, int paramInt1, int paramInt2) {
    try {
      ParceledListSlice parceledListSlice = this.mPM.queryIntentActivities(paramIntent, paramIntent.resolveTypeIfNeeded(this.mContext.getContentResolver()), updateFlagsForComponent(paramInt1, paramInt2, paramIntent), paramInt2);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt) {
    int i = getUserId();
    ContentResolver contentResolver = this.mContext.getContentResolver();
    String[] arrayOfString = null;
    if (paramArrayOfIntent != null) {
      int j = paramArrayOfIntent.length;
      byte b = 0;
      while (b < j) {
        Intent intent = paramArrayOfIntent[b];
        String[] arrayOfString1 = arrayOfString;
        if (intent != null) {
          String str = intent.resolveTypeIfNeeded(contentResolver);
          arrayOfString1 = arrayOfString;
          if (str != null) {
            arrayOfString1 = arrayOfString;
            if (arrayOfString == null)
              arrayOfString1 = new String[j]; 
            arrayOfString1[b] = str;
          } 
        } 
        b++;
        arrayOfString = arrayOfString1;
      } 
    } else {
      arrayOfString = null;
    } 
    try {
      ParceledListSlice parceledListSlice = this.mPM.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, arrayOfString, paramIntent, paramIntent.resolveTypeIfNeeded(contentResolver), updateFlagsForComponent(paramInt, i, paramIntent), i);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt) {
    return queryIntentContentProvidersAsUser(paramIntent, paramInt, getUserId());
  }
  
  public List<ResolveInfo> queryIntentContentProvidersAsUser(Intent paramIntent, int paramInt1, int paramInt2) {
    try {
      ParceledListSlice parceledListSlice = this.mPM.queryIntentContentProviders(paramIntent, paramIntent.resolveTypeIfNeeded(this.mContext.getContentResolver()), updateFlagsForComponent(paramInt1, paramInt2, paramIntent), paramInt2);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt) {
    return queryIntentServicesAsUser(paramIntent, paramInt, getUserId());
  }
  
  public List<ResolveInfo> queryIntentServicesAsUser(Intent paramIntent, int paramInt1, int paramInt2) {
    try {
      ParceledListSlice parceledListSlice = this.mPM.queryIntentServices(paramIntent, paramIntent.resolveTypeIfNeeded(this.mContext.getContentResolver()), updateFlagsForComponent(paramInt1, paramInt2, paramIntent), paramInt2);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    try {
      ParceledListSlice parceledListSlice = this.mPermissionManager.queryPermissionsByGroup(paramString, paramInt);
      if (parceledListSlice != null) {
        List<PermissionInfo> list = parceledListSlice.getList();
        if (list != null)
          return list; 
      } 
      throw new PackageManager.NameNotFoundException(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void registerDexModule(String paramString, PackageManager.DexModuleRegisterCallback paramDexModuleRegisterCallback) {
    boolean bool = false;
    try {
      DexModuleRegisterCallbackDelegate dexModuleRegisterCallbackDelegate;
      StructStat structStat = Os.stat(paramString);
      int i = OsConstants.S_IROTH;
      int j = structStat.st_mode;
      if ((i & j) != 0)
        bool = true; 
      structStat = null;
      if (paramDexModuleRegisterCallback != null)
        dexModuleRegisterCallbackDelegate = new DexModuleRegisterCallbackDelegate(paramDexModuleRegisterCallback); 
      try {
        this.mPM.registerDexModule(this.mContext.getPackageName(), paramString, bool, (IDexModuleRegisterCallback)dexModuleRegisterCallbackDelegate);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } 
    } catch (ErrnoException errnoException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not get stat the module file: ");
      stringBuilder.append(errnoException.getMessage());
      paramDexModuleRegisterCallback.onDexModuleRegistered((String)remoteException, false, stringBuilder.toString());
      return;
    } 
  }
  
  public void registerMoveCallback(PackageManager.MoveCallback paramMoveCallback, Handler paramHandler) {
    synchronized (this.mDelegates) {
      MoveCallbackDelegate moveCallbackDelegate = new MoveCallbackDelegate();
      this(paramMoveCallback, paramHandler.getLooper());
      try {
        this.mPM.registerMoveCallback((IPackageMoveObserver)moveCallbackDelegate);
        this.mDelegates.add(moveCallbackDelegate);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  public void removeOnPermissionsChangeListener(PackageManager.OnPermissionsChangedListener paramOnPermissionsChangedListener) {
    synchronized (this.mPermissionListeners) {
      IOnPermissionsChangeListener iOnPermissionsChangeListener = this.mPermissionListeners.get(paramOnPermissionsChangedListener);
      if (iOnPermissionsChangeListener != null)
        try {
          this.mPermissionManager.removeOnPermissionsChangeListener(iOnPermissionsChangeListener);
          this.mPermissionListeners.remove(paramOnPermissionsChangedListener);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return;
    } 
  }
  
  public void removePackageFromPreferred(String paramString) {
    Log.w("ApplicationPackageManager", "removePackageFromPreferred() is a no-op");
  }
  
  public void removePermission(String paramString) {
    try {
      this.mPermissionManager.removePermission(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean removeWhitelistedRestrictedPermission(String paramString1, String paramString2, int paramInt) {
    try {
      int i = getUserId();
      return this.mPermissionManager.removeWhitelistedRestrictedPermission(paramString1, paramString2, paramInt, i);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void replacePreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName) {
    try {
      this.mPM.replacePreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void replacePreferredActivityAsUser(IntentFilter paramIntentFilter, int paramInt1, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName, int paramInt2) {
    try {
      this.mPM.replacePreferredActivity(paramIntentFilter, paramInt1, paramArrayOfComponentName, paramComponentName, paramInt2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ResolveInfo resolveActivity(Intent paramIntent, int paramInt) {
    return resolveActivityAsUser(paramIntent, paramInt, getUserId());
  }
  
  public ResolveInfo resolveActivityAsUser(Intent paramIntent, int paramInt1, int paramInt2) {
    try {
      return this.mPM.resolveIntent(paramIntent, paramIntent.resolveTypeIfNeeded(this.mContext.getContentResolver()), updateFlagsForComponent(paramInt1, paramInt2, paramIntent), paramInt2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt) {
    return resolveContentProviderAsUser(paramString, paramInt, getUserId());
  }
  
  public ProviderInfo resolveContentProviderAsUser(String paramString, int paramInt1, int paramInt2) {
    try {
      return this.mPM.resolveContentProvider(paramString, updateFlagsForComponent(paramInt1, paramInt2, (Intent)null), paramInt2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ResolveInfo resolveService(Intent paramIntent, int paramInt) {
    return resolveServiceAsUser(paramIntent, paramInt, getUserId());
  }
  
  public ResolveInfo resolveServiceAsUser(Intent paramIntent, int paramInt1, int paramInt2) {
    try {
      return this.mPM.resolveService(paramIntent, paramIntent.resolveTypeIfNeeded(this.mContext.getContentResolver()), updateFlagsForComponent(paramInt1, paramInt2, paramIntent), paramInt2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void revokeRuntimePermission(String paramString1, String paramString2, UserHandle paramUserHandle) {
    revokeRuntimePermission(paramString1, paramString2, paramUserHandle, (String)null);
  }
  
  public void revokeRuntimePermission(String paramString1, String paramString2, UserHandle paramUserHandle, String paramString3) {
    try {
      this.mPermissionManager.revokeRuntimePermission(paramString1, paramString2, paramUserHandle.getIdentifier(), paramString3);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void sendDeviceCustomizationReadyBroadcast() {
    try {
      this.mPM.sendDeviceCustomizationReadyBroadcast();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public void setApplicationCategoryHint(String paramString, int paramInt) {
    try {
      this.mPM.setApplicationCategoryHint(paramString, paramInt, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2) {
    try {
      this.mPM.setApplicationEnabledSetting(paramString, paramInt1, paramInt2, getUserId(), this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean setApplicationHiddenSettingAsUser(String paramString, boolean paramBoolean, UserHandle paramUserHandle) {
    try {
      return this.mPM.setApplicationHiddenSettingAsUser(paramString, paramBoolean, paramUserHandle.getIdentifier());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean setAutoRevokeWhitelisted(String paramString, boolean paramBoolean) {
    try {
      int i = getUserId();
      return this.mPermissionManager.setAutoRevokeWhitelisted(paramString, paramBoolean, i);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2) {
    try {
      this.mPM.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean setDefaultBrowserPackageNameAsUser(String paramString, int paramInt) {
    try {
      return this.mPermissionManager.setDefaultBrowser(paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String[] setDistractingPackageRestrictions(String[] paramArrayOfString, int paramInt) {
    try {
      return this.mPM.setDistractingPackageRestrictionsAsUser(paramArrayOfString, paramInt, this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setHarmfulAppWarning(String paramString, CharSequence paramCharSequence) {
    try {
      this.mPM.setHarmfulAppWarning(paramString, paramCharSequence, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public void setInstallerPackageName(String paramString1, String paramString2) {
    try {
      this.mPM.setInstallerPackageName(paramString1, paramString2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean setInstantAppCookie(byte[] paramArrayOfbyte) {
    try {
      return this.mPM.setInstantAppCookie(this.mContext.getPackageName(), paramArrayOfbyte, getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setMimeGroup(String paramString, Set<String> paramSet) {
    try {
      IPackageManager iPackageManager = this.mPM;
      String str = this.mContext.getPackageName();
      ArrayList arrayList = new ArrayList();
      this((Collection)paramSet);
      iPackageManager.setMimeGroup(str, paramString, arrayList);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public String[] setPackagesSuspended(String[] paramArrayOfString, boolean paramBoolean, PersistableBundle paramPersistableBundle1, PersistableBundle paramPersistableBundle2, SuspendDialogInfo paramSuspendDialogInfo) {
    try {
      return this.mPM.setPackagesSuspendedAsUser(paramArrayOfString, paramBoolean, paramPersistableBundle1, paramPersistableBundle2, paramSuspendDialogInfo, this.mContext.getOpPackageName(), getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String[] setPackagesSuspended(String[] paramArrayOfString, boolean paramBoolean, PersistableBundle paramPersistableBundle1, PersistableBundle paramPersistableBundle2, String paramString) {
    if (!TextUtils.isEmpty(paramString)) {
      SuspendDialogInfo suspendDialogInfo = (new SuspendDialogInfo.Builder()).setMessage(paramString).build();
    } else {
      paramString = null;
    } 
    return setPackagesSuspended(paramArrayOfString, paramBoolean, paramPersistableBundle1, paramPersistableBundle2, (SuspendDialogInfo)paramString);
  }
  
  public void setSyntheticAppDetailsActivityEnabled(String paramString, boolean paramBoolean) {
    try {
      byte b;
      ComponentName componentName = new ComponentName();
      this(paramString, APP_DETAILS_ACTIVITY_CLASS_NAME);
      IPackageManager iPackageManager = this.mPM;
      if (paramBoolean) {
        b = 0;
      } else {
        b = 2;
      } 
      iPackageManager.setComponentEnabledSetting(componentName, b, 1, getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setSystemAppState(String paramString, int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt == 3)
            try {
              this.mPM.setSystemAppInstallState(paramString, false, getUserId());
              return;
            } catch (RemoteException remoteException) {
              throw remoteException.rethrowFromSystemServer();
            }  
        } else {
          this.mPM.setSystemAppInstallState((String)remoteException, true, getUserId());
        } 
      } else {
        this.mPM.setSystemAppHiddenUntilInstalled((String)remoteException, false);
      } 
    } else {
      this.mPM.setSystemAppHiddenUntilInstalled((String)remoteException, true);
    } 
  }
  
  public void setUpdateAvailable(String paramString, boolean paramBoolean) {
    try {
      this.mPM.setUpdateAvailable(paramString, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean shouldShowRequestPermissionRationale(String paramString) {
    try {
      String str = this.mContext.getPackageName();
      return this.mPermissionManager.shouldShowRequestPermissionRationale(paramString, str, getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void unregisterMoveCallback(PackageManager.MoveCallback paramMoveCallback) {
    synchronized (this.mDelegates) {
      Iterator<MoveCallbackDelegate> iterator = this.mDelegates.iterator();
      while (iterator.hasNext()) {
        MoveCallbackDelegate moveCallbackDelegate = iterator.next();
        PackageManager.MoveCallback moveCallback = moveCallbackDelegate.mCallback;
        if (moveCallback == paramMoveCallback)
          try {
            this.mPM.unregisterMoveCallback((IPackageMoveObserver)moveCallbackDelegate);
            iterator.remove();
          } catch (RemoteException remoteException) {
            throw remoteException.rethrowFromSystemServer();
          }  
      } 
      return;
    } 
  }
  
  public void updateInstantAppCookie(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null || paramArrayOfbyte.length <= getInstantAppCookieMaxBytes())
      try {
        this.mPM.setInstantAppCookie(this.mContext.getPackageName(), paramArrayOfbyte, getUserId());
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("instant cookie longer than ");
    stringBuilder.append(getInstantAppCookieMaxBytes());
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean updateIntentVerificationStatusAsUser(String paramString, int paramInt1, int paramInt2) {
    try {
      return this.mPM.updateIntentVerificationStatus(paramString, paramInt1, paramInt2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void updatePermissionFlags(String paramString1, String paramString2, int paramInt1, int paramInt2, UserHandle paramUserHandle) {
    try {
      boolean bool;
      if ((this.mContext.getApplicationInfo()).targetSdkVersion >= 29) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mPermissionManager.updatePermissionFlags(paramString1, paramString2, paramInt1, paramInt2, bool, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void verifyIntentFilter(int paramInt1, int paramInt2, List<String> paramList) {
    try {
      this.mPM.verifyIntentFilter(paramInt1, paramInt2, paramList);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void verifyPendingInstall(int paramInt1, int paramInt2) {
    try {
      this.mPM.verifyPendingInstall(paramInt1, paramInt2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private static class DexModuleRegisterCallbackDelegate extends IDexModuleRegisterCallback.Stub implements Handler.Callback {
    private static final int MSG_DEX_MODULE_REGISTERED = 1;
    
    private final PackageManager.DexModuleRegisterCallback callback;
    
    private final Handler mHandler;
    
    DexModuleRegisterCallbackDelegate(PackageManager.DexModuleRegisterCallback param1DexModuleRegisterCallback) {
      this.callback = param1DexModuleRegisterCallback;
      this.mHandler = new Handler(Looper.getMainLooper(), this);
    }
    
    public boolean handleMessage(Message param1Message) {
      if (param1Message.what != 1)
        return false; 
      ApplicationPackageManager.DexModuleRegisterResult dexModuleRegisterResult = (ApplicationPackageManager.DexModuleRegisterResult)param1Message.obj;
      this.callback.onDexModuleRegistered(dexModuleRegisterResult.dexModulePath, dexModuleRegisterResult.success, dexModuleRegisterResult.message);
      return true;
    }
    
    public void onDexModuleRegistered(String param1String1, boolean param1Boolean, String param1String2) throws RemoteException {
      this.mHandler.obtainMessage(1, new ApplicationPackageManager.DexModuleRegisterResult(param1String1, param1Boolean, param1String2)).sendToTarget();
    }
  }
  
  private static class DexModuleRegisterResult {
    final String dexModulePath;
    
    final String message;
    
    final boolean success;
    
    private DexModuleRegisterResult(String param1String1, boolean param1Boolean, String param1String2) {
      this.dexModulePath = param1String1;
      this.success = param1Boolean;
      this.message = param1String2;
    }
  }
  
  private static final class HasSystemFeatureQuery {
    public final String name;
    
    public final int version;
    
    public HasSystemFeatureQuery(String param1String, int param1Int) {
      this.name = param1String;
      this.version = param1Int;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = param1Object instanceof HasSystemFeatureQuery;
      boolean bool1 = false;
      if (bool) {
        param1Object = param1Object;
        bool = bool1;
        if (Objects.equals(this.name, ((HasSystemFeatureQuery)param1Object).name)) {
          bool = bool1;
          if (this.version == ((HasSystemFeatureQuery)param1Object).version)
            bool = true; 
        } 
        return bool;
      } 
      return false;
    }
    
    public int hashCode() {
      return Objects.hashCode(this.name) * 13 + this.version;
    }
    
    public String toString() {
      return String.format("HasSystemFeatureQuery(name=\"%s\", version=%d)", new Object[] { this.name, Integer.valueOf(this.version) });
    }
  }
  
  private static class MoveCallbackDelegate extends IPackageMoveObserver.Stub implements Handler.Callback {
    private static final int MSG_CREATED = 1;
    
    private static final int MSG_STATUS_CHANGED = 2;
    
    final PackageManager.MoveCallback mCallback;
    
    final Handler mHandler;
    
    public MoveCallbackDelegate(PackageManager.MoveCallback param1MoveCallback, Looper param1Looper) {
      this.mCallback = param1MoveCallback;
      this.mHandler = new Handler(param1Looper, this);
    }
    
    public boolean handleMessage(Message param1Message) {
      int i = param1Message.what;
      if (i != 1) {
        if (i != 2)
          return false; 
        someArgs = (SomeArgs)param1Message.obj;
        this.mCallback.onStatusChanged(someArgs.argi1, someArgs.argi2, ((Long)someArgs.arg3).longValue());
        someArgs.recycle();
        return true;
      } 
      SomeArgs someArgs = (SomeArgs)((Message)someArgs).obj;
      this.mCallback.onCreated(someArgs.argi1, (Bundle)someArgs.arg2);
      someArgs.recycle();
      return true;
    }
    
    public void onCreated(int param1Int, Bundle param1Bundle) {
      SomeArgs someArgs = SomeArgs.obtain();
      someArgs.argi1 = param1Int;
      someArgs.arg2 = param1Bundle;
      this.mHandler.obtainMessage(1, someArgs).sendToTarget();
    }
    
    public void onStatusChanged(int param1Int1, int param1Int2, long param1Long) {
      SomeArgs someArgs = SomeArgs.obtain();
      someArgs.argi1 = param1Int1;
      someArgs.argi2 = param1Int2;
      someArgs.arg3 = Long.valueOf(param1Long);
      this.mHandler.obtainMessage(2, someArgs).sendToTarget();
    }
  }
  
  public class OnPermissionsChangeListenerDelegate extends IOnPermissionsChangeListener.Stub implements Handler.Callback {
    private static final int MSG_PERMISSIONS_CHANGED = 1;
    
    private final Handler mHandler;
    
    private final PackageManager.OnPermissionsChangedListener mListener;
    
    public OnPermissionsChangeListenerDelegate(PackageManager.OnPermissionsChangedListener param1OnPermissionsChangedListener, Looper param1Looper) {
      this.mListener = param1OnPermissionsChangedListener;
      this.mHandler = new Handler(param1Looper, this);
    }
    
    public boolean handleMessage(Message param1Message) {
      if (param1Message.what != 1)
        return false; 
      int i = param1Message.arg1;
      this.mListener.onPermissionsChanged(i);
      return true;
    }
    
    public void onPermissionsChanged(int param1Int) {
      this.mHandler.obtainMessage(1, param1Int, 0).sendToTarget();
    }
  }
  
  private static final class ResourceName {
    final int iconId;
    
    final String packageName;
    
    ResourceName(ApplicationInfo param1ApplicationInfo, int param1Int) {
      this(param1ApplicationInfo.packageName, param1Int);
    }
    
    ResourceName(ComponentInfo param1ComponentInfo, int param1Int) {
      this(param1ComponentInfo.applicationInfo.packageName, param1Int);
    }
    
    ResourceName(ResolveInfo param1ResolveInfo, int param1Int) {
      this(param1ResolveInfo.activityInfo.applicationInfo.packageName, param1Int);
    }
    
    ResourceName(String param1String, int param1Int) {
      this.packageName = param1String;
      this.iconId = param1Int;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if (this.iconId != ((ResourceName)param1Object).iconId)
        return false; 
      String str = this.packageName;
      if ((str != null) ? !str.equals(((ResourceName)param1Object).packageName) : (((ResourceName)param1Object).packageName != null))
        bool = false; 
      return bool;
    }
    
    public int hashCode() {
      return this.packageName.hashCode() * 31 + this.iconId;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{ResourceName ");
      stringBuilder.append(this.packageName);
      stringBuilder.append(" / ");
      stringBuilder.append(this.iconId);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationPackageManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
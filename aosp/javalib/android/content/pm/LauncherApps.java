package android.content.pm;

import android.annotation.SystemApi;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.LocusId;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import android.util.Pair;
import com.android.internal.util.function.QuadConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

public class LauncherApps {
  public static final String ACTION_CONFIRM_PIN_APPWIDGET = "android.content.pm.action.CONFIRM_PIN_APPWIDGET";
  
  public static final String ACTION_CONFIRM_PIN_SHORTCUT = "android.content.pm.action.CONFIRM_PIN_SHORTCUT";
  
  static final boolean DEBUG = false;
  
  public static final String EXTRA_PIN_ITEM_REQUEST = "android.content.pm.extra.PIN_ITEM_REQUEST";
  
  public static final int FLAG_CACHE_BUBBLE_SHORTCUTS = 1;
  
  public static final int FLAG_CACHE_NOTIFICATION_SHORTCUTS = 0;
  
  static final String TAG = "LauncherApps";
  
  private IOnAppsChangedListener.Stub mAppsChangedListener = new IOnAppsChangedListener.Stub() {
      public void onPackageAdded(UserHandle param1UserHandle, String param1String) throws RemoteException {
        synchronized (LauncherApps.this) {
          Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.this.mCallbacks.iterator();
          while (iterator.hasNext())
            ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackageAdded(param1String, param1UserHandle); 
          return;
        } 
      }
      
      public void onPackageChanged(UserHandle param1UserHandle, String param1String) throws RemoteException {
        synchronized (LauncherApps.this) {
          Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.this.mCallbacks.iterator();
          while (iterator.hasNext())
            ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackageChanged(param1String, param1UserHandle); 
          return;
        } 
      }
      
      public void onPackageRemoved(UserHandle param1UserHandle, String param1String) throws RemoteException {
        synchronized (LauncherApps.this) {
          Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.this.mCallbacks.iterator();
          while (iterator.hasNext())
            ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackageRemoved(param1String, param1UserHandle); 
          return;
        } 
      }
      
      public void onPackagesAvailable(UserHandle param1UserHandle, String[] param1ArrayOfString, boolean param1Boolean) throws RemoteException {
        synchronized (LauncherApps.this) {
          Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.this.mCallbacks.iterator();
          while (iterator.hasNext())
            ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackagesAvailable(param1ArrayOfString, param1UserHandle, param1Boolean); 
          return;
        } 
      }
      
      public void onPackagesSuspended(UserHandle param1UserHandle, String[] param1ArrayOfString, Bundle param1Bundle) throws RemoteException {
        synchronized (LauncherApps.this) {
          Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.this.mCallbacks.iterator();
          while (iterator.hasNext())
            ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackagesSuspended(param1ArrayOfString, param1Bundle, param1UserHandle); 
          return;
        } 
      }
      
      public void onPackagesUnavailable(UserHandle param1UserHandle, String[] param1ArrayOfString, boolean param1Boolean) throws RemoteException {
        synchronized (LauncherApps.this) {
          Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.this.mCallbacks.iterator();
          while (iterator.hasNext())
            ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackagesUnavailable(param1ArrayOfString, param1UserHandle, param1Boolean); 
          return;
        } 
      }
      
      public void onPackagesUnsuspended(UserHandle param1UserHandle, String[] param1ArrayOfString) throws RemoteException {
        synchronized (LauncherApps.this) {
          Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.this.mCallbacks.iterator();
          while (iterator.hasNext())
            ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackagesUnsuspended(param1ArrayOfString, param1UserHandle); 
          return;
        } 
      }
      
      public void onShortcutChanged(UserHandle param1UserHandle, String param1String, ParceledListSlice param1ParceledListSlice) {
        List<ShortcutInfo> list = param1ParceledListSlice.getList();
        synchronized (LauncherApps.this) {
          Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.this.mCallbacks.iterator();
          while (iterator.hasNext())
            ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnShortcutChanged(param1String, param1UserHandle, list); 
          return;
        } 
      }
    };
  
  private final List<CallbackMessageHandler> mCallbacks = new ArrayList<>();
  
  private final Context mContext;
  
  private final List<PackageInstaller.SessionCallbackDelegate> mDelegates = new ArrayList<>();
  
  private final PackageManager mPm;
  
  private final ILauncherApps mService;
  
  private final Map<ShortcutChangeCallback, Pair<Executor, IShortcutChangeCallback>> mShortcutChangeCallbacks = new HashMap<>();
  
  private final UserManager mUserManager;
  
  public LauncherApps(Context paramContext) {
    this(paramContext, ILauncherApps.Stub.asInterface(ServiceManager.getService("launcherapps")));
  }
  
  public LauncherApps(Context paramContext, ILauncherApps paramILauncherApps) {
    this.mContext = paramContext;
    this.mService = paramILauncherApps;
    this.mPm = paramContext.getPackageManager();
    this.mUserManager = (UserManager)paramContext.getSystemService(UserManager.class);
  }
  
  private void addCallbackLocked(Callback paramCallback, Handler paramHandler) {
    removeCallbackLocked(paramCallback);
    Handler handler = paramHandler;
    if (paramHandler == null)
      handler = new Handler(); 
    CallbackMessageHandler callbackMessageHandler = new CallbackMessageHandler(handler.getLooper(), paramCallback);
    this.mCallbacks.add(callbackMessageHandler);
  }
  
  private List<LauncherActivityInfo> convertToActivityList(ParceledListSlice<ResolveInfo> paramParceledListSlice, UserHandle paramUserHandle) {
    if (paramParceledListSlice == null)
      return Collections.EMPTY_LIST; 
    ArrayList<LauncherActivityInfo> arrayList = new ArrayList();
    for (ResolveInfo resolveInfo : paramParceledListSlice.getList())
      arrayList.add(new LauncherActivityInfo(this.mContext, resolveInfo.activityInfo, paramUserHandle)); 
    return arrayList;
  }
  
  private int findCallbackLocked(Callback paramCallback) {
    if (paramCallback != null) {
      int i = this.mCallbacks.size();
      for (byte b = 0; b < i; b++) {
        if ((this.mCallbacks.get(b)).mCallback == paramCallback)
          return b; 
      } 
      return -1;
    } 
    throw new IllegalArgumentException("Callback cannot be null");
  }
  
  private ParcelFileDescriptor getShortcutIconFd(String paramString1, String paramString2, int paramInt) {
    try {
      return this.mService.getShortcutIconFd(this.mContext.getPackageName(), paramString1, paramString2, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private String getShortcutIconUri(String paramString1, String paramString2, int paramInt) {
    try {
      return this.mService.getShortcutIconUri(this.mContext.getPackageName(), paramString1, paramString2, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private ParcelFileDescriptor getUriShortcutIconFd(String paramString1, String paramString2, int paramInt) {
    paramString1 = getShortcutIconUri(paramString1, paramString2, paramInt);
    if (paramString1 == null)
      return null; 
    try {
      return this.mContext.getContentResolver().openFileDescriptor(Uri.parse(paramString1), "r");
    } catch (FileNotFoundException fileNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Icon file not found: ");
      stringBuilder.append(paramString1);
      Log.e("LauncherApps", stringBuilder.toString());
      return null;
    } 
  }
  
  private Drawable loadDrawableFromFileDescriptor(ParcelFileDescriptor paramParcelFileDescriptor, boolean paramBoolean) {
    if (paramParcelFileDescriptor == null)
      return null; 
    try {
      Bitmap bitmap = BitmapFactory.decodeFileDescriptor(paramParcelFileDescriptor.getFileDescriptor());
      if (bitmap != null) {
        AdaptiveIconDrawable adaptiveIconDrawable;
        BitmapDrawable bitmapDrawable = new BitmapDrawable();
        this(this.mContext.getResources(), bitmap);
        if (paramBoolean) {
          adaptiveIconDrawable = new AdaptiveIconDrawable(null, (Drawable)bitmapDrawable);
          return (Drawable)adaptiveIconDrawable;
        } 
        return (Drawable)adaptiveIconDrawable;
      } 
      return null;
    } finally {
      try {
        iOException.close();
      } catch (IOException iOException1) {}
    } 
  }
  
  private Drawable loadDrawableResourceFromPackage(String paramString, int paramInt1, UserHandle paramUserHandle, int paramInt2) {
    if (paramInt1 == 0)
      return null; 
    try {
      ApplicationInfo applicationInfo = getApplicationInfo(paramString, 0, paramUserHandle);
      return this.mContext.getPackageManager().getResourcesForApplication(applicationInfo).getDrawableForDensity(paramInt1, paramInt2);
    } catch (NameNotFoundException|android.content.res.Resources.NotFoundException nameNotFoundException) {
      return null;
    } 
  }
  
  private void logErrorForInvalidProfileAccess(UserHandle paramUserHandle) {
    if (UserHandle.myUserId() != paramUserHandle.getIdentifier() && this.mUserManager.isManagedProfile() && this.mContext.checkSelfPermission("android.permission.INTERACT_ACROSS_USERS_FULL") != 0)
      Log.w("LauncherApps", "Accessing other profiles/users from managed profile is no longer allowed."); 
  }
  
  private List<ShortcutInfo> maybeUpdateDisabledMessage(List<ShortcutInfo> paramList) {
    if (paramList == null)
      return null; 
    for (int i = paramList.size() - 1; i >= 0; i--) {
      ShortcutInfo shortcutInfo = paramList.get(i);
      String str = ShortcutInfo.getDisabledReasonForRestoreIssue(this.mContext, shortcutInfo.getDisabledReason());
      if (str != null)
        shortcutInfo.setDisabledMessage(str); 
    } 
    return paramList;
  }
  
  private void removeCallbackLocked(Callback paramCallback) {
    int i = findCallbackLocked(paramCallback);
    if (i >= 0)
      this.mCallbacks.remove(i); 
  }
  
  private void startShortcut(String paramString1, String paramString2, Rect paramRect, Bundle paramBundle, int paramInt) {
    try {
      if (this.mService.startShortcut(this.mContext.getPackageName(), paramString1, null, paramString2, paramRect, paramBundle, paramInt))
        return; 
      ActivityNotFoundException activityNotFoundException = new ActivityNotFoundException();
      this("Shortcut could not be started");
      throw activityNotFoundException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void cacheShortcuts(String paramString, List<String> paramList, UserHandle paramUserHandle, int paramInt) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      this.mService.cacheShortcuts(this.mContext.getPackageName(), paramString, paramList, paramUserHandle, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<LauncherActivityInfo> getActivityList(String paramString, UserHandle paramUserHandle) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      return convertToActivityList(this.mService.getLauncherActivities(this.mContext.getPackageName(), paramString, paramUserHandle), paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<PackageInstaller.SessionInfo> getAllPackageInstallerSessions() {
    try {
      return this.mService.getAllSessions(this.mContext.getPackageName()).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public AppUsageLimit getAppUsageLimit(String paramString, UserHandle paramUserHandle) {
    try {
      return this.mService.getAppUsageLimit(this.mContext.getPackageName(), paramString, paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt, UserHandle paramUserHandle) throws PackageManager.NameNotFoundException {
    Objects.requireNonNull(paramString, "packageName");
    Objects.requireNonNull(paramUserHandle, "user");
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      ApplicationInfo applicationInfo = this.mService.getApplicationInfo(this.mContext.getPackageName(), paramString, paramInt, paramUserHandle);
      if (applicationInfo != null)
        return applicationInfo; 
      PackageManager.NameNotFoundException nameNotFoundException = new PackageManager.NameNotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Package ");
      stringBuilder.append(paramString);
      stringBuilder.append(" not found for user ");
      stringBuilder.append(paramUserHandle.getIdentifier());
      this(stringBuilder.toString());
      throw nameNotFoundException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public PinItemRequest getPinItemRequest(Intent paramIntent) {
    return (PinItemRequest)paramIntent.getParcelableExtra("android.content.pm.extra.PIN_ITEM_REQUEST");
  }
  
  public List<UserHandle> getProfiles() {
    if (this.mUserManager.isManagedProfile()) {
      ArrayList<UserHandle> arrayList = new ArrayList(1);
      arrayList.add(Process.myUserHandle());
      return arrayList;
    } 
    return this.mUserManager.getUserProfiles();
  }
  
  public Drawable getShortcutBadgedIconDrawable(ShortcutInfo paramShortcutInfo, int paramInt) {
    Drawable drawable1;
    Drawable drawable2 = getShortcutIconDrawable(paramShortcutInfo, paramInt);
    if (drawable2 == null) {
      paramShortcutInfo = null;
    } else {
      drawable1 = this.mContext.getPackageManager().getUserBadgedIcon(drawable2, paramShortcutInfo.getUserHandle());
    } 
    return drawable1;
  }
  
  public IntentSender getShortcutConfigActivityIntent(LauncherActivityInfo paramLauncherActivityInfo) {
    try {
      return this.mService.getShortcutConfigActivityIntent(this.mContext.getPackageName(), paramLauncherActivityInfo.getComponentName(), paramLauncherActivityInfo.getUser());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<LauncherActivityInfo> getShortcutConfigActivityList(String paramString, UserHandle paramUserHandle) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      return convertToActivityList(this.mService.getShortcutConfigActivities(this.mContext.getPackageName(), paramString, paramUserHandle), paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Icon getShortcutIcon(ShortcutInfo paramShortcutInfo) {
    if (paramShortcutInfo.hasIconFile()) {
      ParcelFileDescriptor parcelFileDescriptor = getShortcutIconFd(paramShortcutInfo);
      if (parcelFileDescriptor == null)
        return null; 
      try {
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
        if (bitmap != null) {
          if (paramShortcutInfo.hasAdaptiveBitmap())
            return Icon.createWithAdaptiveBitmap(bitmap); 
          return Icon.createWithBitmap(bitmap);
        } 
        return null;
      } finally {
        try {
          iOException.close();
        } catch (IOException iOException1) {}
      } 
    } 
    if (paramShortcutInfo.hasIconUri()) {
      String str = getShortcutIconUri(paramShortcutInfo.getPackage(), paramShortcutInfo.getId(), paramShortcutInfo.getUserId());
      return (str == null) ? null : (paramShortcutInfo.hasAdaptiveBitmap() ? Icon.createWithAdaptiveBitmapContentUri(str) : Icon.createWithContentUri(str));
    } 
    return paramShortcutInfo.hasIconResource() ? Icon.createWithResource(paramShortcutInfo.getPackage(), paramShortcutInfo.getIconResourceId()) : paramShortcutInfo.getIcon();
  }
  
  public Drawable getShortcutIconDrawable(ShortcutInfo paramShortcutInfo, int paramInt) {
    if (paramShortcutInfo.hasIconFile())
      return loadDrawableFromFileDescriptor(getShortcutIconFd(paramShortcutInfo), paramShortcutInfo.hasAdaptiveBitmap()); 
    if (paramShortcutInfo.hasIconUri())
      return loadDrawableFromFileDescriptor(getUriShortcutIconFd(paramShortcutInfo), paramShortcutInfo.hasAdaptiveBitmap()); 
    if (paramShortcutInfo.hasIconResource())
      return loadDrawableResourceFromPackage(paramShortcutInfo.getPackage(), paramShortcutInfo.getIconResourceId(), paramShortcutInfo.getUserHandle(), paramInt); 
    if (paramShortcutInfo.getIcon() != null) {
      Icon icon = paramShortcutInfo.getIcon();
      int i = icon.getType();
      if (i != 1)
        if (i != 2) {
          if (i != 5)
            return null; 
        } else {
          return loadDrawableResourceFromPackage(paramShortcutInfo.getPackage(), icon.getResId(), paramShortcutInfo.getUserHandle(), paramInt);
        }  
      return icon.loadDrawable(this.mContext);
    } 
    return null;
  }
  
  public ParcelFileDescriptor getShortcutIconFd(ShortcutInfo paramShortcutInfo) {
    return getShortcutIconFd(paramShortcutInfo.getPackage(), paramShortcutInfo.getId(), paramShortcutInfo.getUserId());
  }
  
  public ParcelFileDescriptor getShortcutIconFd(String paramString1, String paramString2, UserHandle paramUserHandle) {
    return getShortcutIconFd(paramString1, paramString2, paramUserHandle.getIdentifier());
  }
  
  @Deprecated
  public int getShortcutIconResId(ShortcutInfo paramShortcutInfo) {
    return paramShortcutInfo.getIconResourceId();
  }
  
  @Deprecated
  public int getShortcutIconResId(String paramString1, String paramString2, UserHandle paramUserHandle) {
    ShortcutQuery shortcutQuery = new ShortcutQuery();
    shortcutQuery.setPackage(paramString1);
    int i = 0;
    shortcutQuery.setShortcutIds(Arrays.asList(new String[] { paramString2 }));
    shortcutQuery.setQueryFlags(27);
    List<ShortcutInfo> list = getShortcuts(shortcutQuery, paramUserHandle);
    if (list.size() > 0)
      i = ((ShortcutInfo)list.get(0)).getIconResourceId(); 
    return i;
  }
  
  @Deprecated
  public List<ShortcutInfo> getShortcutInfo(String paramString, List<String> paramList, UserHandle paramUserHandle) {
    ShortcutQuery shortcutQuery = new ShortcutQuery();
    shortcutQuery.setPackage(paramString);
    shortcutQuery.setShortcutIds(paramList);
    shortcutQuery.setQueryFlags(27);
    return getShortcuts(shortcutQuery, paramUserHandle);
  }
  
  public List<ShortcutInfo> getShortcuts(ShortcutQuery paramShortcutQuery, UserHandle paramUserHandle) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      ILauncherApps iLauncherApps = this.mService;
      String str = this.mContext.getPackageName();
      ShortcutQueryWrapper shortcutQueryWrapper = new ShortcutQueryWrapper();
      this(paramShortcutQuery);
      return maybeUpdateDisabledMessage(iLauncherApps.getShortcuts(str, shortcutQueryWrapper, paramUserHandle).getList());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Bundle getSuspendedPackageLauncherExtras(String paramString, UserHandle paramUserHandle) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      return this.mService.getSuspendedPackageLauncherExtras(paramString, paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ParcelFileDescriptor getUriShortcutIconFd(ShortcutInfo paramShortcutInfo) {
    return getUriShortcutIconFd(paramShortcutInfo.getPackage(), paramShortcutInfo.getId(), paramShortcutInfo.getUserId());
  }
  
  public boolean hasShortcutHostPermission() {
    try {
      return this.mService.hasShortcutHostPermission(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isActivityEnabled(ComponentName paramComponentName, UserHandle paramUserHandle) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      return this.mService.isActivityEnabled(this.mContext.getPackageName(), paramComponentName, paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isPackageEnabled(String paramString, UserHandle paramUserHandle) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      return this.mService.isPackageEnabled(this.mContext.getPackageName(), paramString, paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void pinShortcuts(String paramString, List<String> paramList, UserHandle paramUserHandle) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      this.mService.pinShortcuts(this.mContext.getPackageName(), paramString, paramList, paramUserHandle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void registerCallback(Callback paramCallback) {
    registerCallback(paramCallback, null);
  }
  
  public void registerCallback(Callback paramCallback, Handler paramHandler) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull -> 72
    //   6: aload_0
    //   7: aload_1
    //   8: invokespecial findCallbackLocked : (Landroid/content/pm/LauncherApps$Callback;)I
    //   11: ifge -> 72
    //   14: aload_0
    //   15: getfield mCallbacks : Ljava/util/List;
    //   18: invokeinterface size : ()I
    //   23: ifne -> 31
    //   26: iconst_1
    //   27: istore_3
    //   28: goto -> 33
    //   31: iconst_0
    //   32: istore_3
    //   33: aload_0
    //   34: aload_1
    //   35: aload_2
    //   36: invokespecial addCallbackLocked : (Landroid/content/pm/LauncherApps$Callback;Landroid/os/Handler;)V
    //   39: iload_3
    //   40: ifeq -> 72
    //   43: aload_0
    //   44: getfield mService : Landroid/content/pm/ILauncherApps;
    //   47: aload_0
    //   48: getfield mContext : Landroid/content/Context;
    //   51: invokevirtual getPackageName : ()Ljava/lang/String;
    //   54: aload_0
    //   55: getfield mAppsChangedListener : Landroid/content/pm/IOnAppsChangedListener$Stub;
    //   58: invokeinterface addOnAppsChangedListener : (Ljava/lang/String;Landroid/content/pm/IOnAppsChangedListener;)V
    //   63: goto -> 72
    //   66: astore_1
    //   67: aload_1
    //   68: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   71: athrow
    //   72: aload_0
    //   73: monitorexit
    //   74: return
    //   75: astore_1
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_1
    //   79: athrow
    // Exception table:
    //   from	to	target	type
    //   6	26	75	finally
    //   33	39	75	finally
    //   43	63	66	android/os/RemoteException
    //   43	63	75	finally
    //   67	72	75	finally
    //   72	74	75	finally
    //   76	78	75	finally
  }
  
  public void registerPackageInstallerSessionCallback(Executor paramExecutor, PackageInstaller.SessionCallback paramSessionCallback) {
    if (paramExecutor != null)
      synchronized (this.mDelegates) {
        PackageInstaller.SessionCallbackDelegate sessionCallbackDelegate = new PackageInstaller.SessionCallbackDelegate();
        this(paramSessionCallback, paramExecutor);
        try {
          this.mService.registerPackageInstallerCallback(this.mContext.getPackageName(), sessionCallbackDelegate);
          this.mDelegates.add(sessionCallbackDelegate);
          return;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      }  
    throw new NullPointerException("Executor must not be null");
  }
  
  public void registerShortcutChangeCallback(ShortcutChangeCallback paramShortcutChangeCallback, ShortcutQuery paramShortcutQuery, Executor paramExecutor) {
    Objects.requireNonNull(paramShortcutChangeCallback, "Callback cannot be null");
    Objects.requireNonNull(paramShortcutQuery, "Query cannot be null");
    Objects.requireNonNull(paramExecutor, "Executor cannot be null");
    synchronized (this.mShortcutChangeCallbacks) {
      ShortcutChangeCallbackProxy shortcutChangeCallbackProxy = new ShortcutChangeCallbackProxy();
      this(paramExecutor, paramShortcutChangeCallback);
      Map<ShortcutChangeCallback, Pair<Executor, IShortcutChangeCallback>> map = this.mShortcutChangeCallbacks;
      Pair<Executor, IShortcutChangeCallback> pair = new Pair();
      this(paramExecutor, shortcutChangeCallbackProxy);
      map.put(paramShortcutChangeCallback, pair);
      try {
        ILauncherApps iLauncherApps = this.mService;
        String str = this.mContext.getPackageName();
        ShortcutQueryWrapper shortcutQueryWrapper = new ShortcutQueryWrapper();
        this(paramShortcutQuery);
        iLauncherApps.registerShortcutChangeCallback(str, shortcutQueryWrapper, shortcutChangeCallbackProxy);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  public LauncherActivityInfo resolveActivity(Intent paramIntent, UserHandle paramUserHandle) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      ActivityInfo activityInfo = this.mService.resolveActivity(this.mContext.getPackageName(), paramIntent.getComponent(), paramUserHandle);
      return (activityInfo != null) ? new LauncherActivityInfo(this.mContext, activityInfo, paramUserHandle) : null;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean shouldHideFromSuggestions(String paramString, UserHandle paramUserHandle) {
    Objects.requireNonNull(paramString, "packageName");
    Objects.requireNonNull(paramUserHandle, "user");
    try {
      return this.mService.shouldHideFromSuggestions(paramString, paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void startAppDetailsActivity(ComponentName paramComponentName, UserHandle paramUserHandle, Rect paramRect, Bundle paramBundle) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      this.mService.showAppDetailsAsUser(this.mContext.getIApplicationThread(), this.mContext.getPackageName(), this.mContext.getAttributionTag(), paramComponentName, paramRect, paramBundle, paramUserHandle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void startMainActivity(ComponentName paramComponentName, UserHandle paramUserHandle, Rect paramRect, Bundle paramBundle) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      this.mService.startActivityAsUser(this.mContext.getIApplicationThread(), this.mContext.getPackageName(), this.mContext.getAttributionTag(), paramComponentName, paramRect, paramBundle, paramUserHandle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void startPackageInstallerSessionDetailsActivity(PackageInstaller.SessionInfo paramSessionInfo, Rect paramRect, Bundle paramBundle) {
    try {
      this.mService.startSessionDetailsActivityAsUser(this.mContext.getIApplicationThread(), this.mContext.getPackageName(), this.mContext.getAttributionTag(), paramSessionInfo, paramRect, paramBundle, paramSessionInfo.getUser());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void startShortcut(ShortcutInfo paramShortcutInfo, Rect paramRect, Bundle paramBundle) {
    startShortcut(paramShortcutInfo.getPackage(), paramShortcutInfo.getId(), paramRect, paramBundle, paramShortcutInfo.getUserId());
  }
  
  public void startShortcut(String paramString1, String paramString2, Rect paramRect, Bundle paramBundle, UserHandle paramUserHandle) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    startShortcut(paramString1, paramString2, paramRect, paramBundle, paramUserHandle.getIdentifier());
  }
  
  public void uncacheShortcuts(String paramString, List<String> paramList, UserHandle paramUserHandle, int paramInt) {
    logErrorForInvalidProfileAccess(paramUserHandle);
    try {
      this.mService.uncacheShortcuts(this.mContext.getPackageName(), paramString, paramList, paramUserHandle, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void unregisterCallback(Callback paramCallback) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokespecial removeCallbackLocked : (Landroid/content/pm/LauncherApps$Callback;)V
    //   7: aload_0
    //   8: getfield mCallbacks : Ljava/util/List;
    //   11: invokeinterface size : ()I
    //   16: istore_2
    //   17: iload_2
    //   18: ifne -> 43
    //   21: aload_0
    //   22: getfield mService : Landroid/content/pm/ILauncherApps;
    //   25: aload_0
    //   26: getfield mAppsChangedListener : Landroid/content/pm/IOnAppsChangedListener$Stub;
    //   29: invokeinterface removeOnAppsChangedListener : (Landroid/content/pm/IOnAppsChangedListener;)V
    //   34: goto -> 43
    //   37: astore_1
    //   38: aload_1
    //   39: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   42: athrow
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	46	finally
    //   21	34	37	android/os/RemoteException
    //   21	34	46	finally
    //   38	43	46	finally
    //   43	45	46	finally
    //   47	49	46	finally
  }
  
  public void unregisterPackageInstallerSessionCallback(PackageInstaller.SessionCallback paramSessionCallback) {
    synchronized (this.mDelegates) {
      Iterator<PackageInstaller.SessionCallbackDelegate> iterator = this.mDelegates.iterator();
      while (iterator.hasNext()) {
        PackageInstaller.SessionCallbackDelegate sessionCallbackDelegate = iterator.next();
        if (sessionCallbackDelegate.mCallback == paramSessionCallback) {
          this.mPm.getPackageInstaller().unregisterSessionCallback(sessionCallbackDelegate.mCallback);
          iterator.remove();
        } 
      } 
      return;
    } 
  }
  
  public void unregisterShortcutChangeCallback(ShortcutChangeCallback paramShortcutChangeCallback) {
    Objects.requireNonNull(paramShortcutChangeCallback, "Callback cannot be null");
    synchronized (this.mShortcutChangeCallbacks) {
      if (this.mShortcutChangeCallbacks.containsKey(paramShortcutChangeCallback)) {
        IShortcutChangeCallback iShortcutChangeCallback = (IShortcutChangeCallback)((Pair)this.mShortcutChangeCallbacks.remove(paramShortcutChangeCallback)).second;
        try {
          this.mService.unregisterShortcutChangeCallback(this.mContext.getPackageName(), iShortcutChangeCallback);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      } 
      return;
    } 
  }
  
  @SystemApi
  public static final class AppUsageLimit implements Parcelable {
    public static final Parcelable.Creator<AppUsageLimit> CREATOR = new Parcelable.Creator<AppUsageLimit>() {
        public LauncherApps.AppUsageLimit createFromParcel(Parcel param2Parcel) {
          return new LauncherApps.AppUsageLimit(param2Parcel);
        }
        
        public LauncherApps.AppUsageLimit[] newArray(int param2Int) {
          return new LauncherApps.AppUsageLimit[param2Int];
        }
      };
    
    private final long mTotalUsageLimit;
    
    private final long mUsageRemaining;
    
    public AppUsageLimit(long param1Long1, long param1Long2) {
      this.mTotalUsageLimit = param1Long1;
      this.mUsageRemaining = param1Long2;
    }
    
    private AppUsageLimit(Parcel param1Parcel) {
      this.mTotalUsageLimit = param1Parcel.readLong();
      this.mUsageRemaining = param1Parcel.readLong();
    }
    
    public int describeContents() {
      return 0;
    }
    
    public long getTotalUsageLimit() {
      return this.mTotalUsageLimit;
    }
    
    public long getUsageRemaining() {
      return this.mUsageRemaining;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeLong(this.mTotalUsageLimit);
      param1Parcel.writeLong(this.mUsageRemaining);
    }
  }
  
  class null implements Parcelable.Creator<AppUsageLimit> {
    public LauncherApps.AppUsageLimit createFromParcel(Parcel param1Parcel) {
      return new LauncherApps.AppUsageLimit(param1Parcel);
    }
    
    public LauncherApps.AppUsageLimit[] newArray(int param1Int) {
      return new LauncherApps.AppUsageLimit[param1Int];
    }
  }
  
  public static abstract class Callback {
    public abstract void onPackageAdded(String param1String, UserHandle param1UserHandle);
    
    public abstract void onPackageChanged(String param1String, UserHandle param1UserHandle);
    
    public abstract void onPackageRemoved(String param1String, UserHandle param1UserHandle);
    
    public abstract void onPackagesAvailable(String[] param1ArrayOfString, UserHandle param1UserHandle, boolean param1Boolean);
    
    public void onPackagesSuspended(String[] param1ArrayOfString, UserHandle param1UserHandle) {}
    
    @Deprecated
    public void onPackagesSuspended(String[] param1ArrayOfString, UserHandle param1UserHandle, Bundle param1Bundle) {
      onPackagesSuspended(param1ArrayOfString, param1UserHandle);
    }
    
    public abstract void onPackagesUnavailable(String[] param1ArrayOfString, UserHandle param1UserHandle, boolean param1Boolean);
    
    public void onPackagesUnsuspended(String[] param1ArrayOfString, UserHandle param1UserHandle) {}
    
    public void onShortcutsChanged(String param1String, List<ShortcutInfo> param1List, UserHandle param1UserHandle) {}
  }
  
  private static class CallbackMessageHandler extends Handler {
    private static final int MSG_ADDED = 1;
    
    private static final int MSG_AVAILABLE = 4;
    
    private static final int MSG_CHANGED = 3;
    
    private static final int MSG_REMOVED = 2;
    
    private static final int MSG_SHORTCUT_CHANGED = 8;
    
    private static final int MSG_SUSPENDED = 6;
    
    private static final int MSG_UNAVAILABLE = 5;
    
    private static final int MSG_UNSUSPENDED = 7;
    
    private LauncherApps.Callback mCallback;
    
    public CallbackMessageHandler(Looper param1Looper, LauncherApps.Callback param1Callback) {
      super(param1Looper, null, true);
      this.mCallback = param1Callback;
    }
    
    public void handleMessage(Message param1Message) {
      if (this.mCallback == null || !(param1Message.obj instanceof CallbackInfo))
        return; 
      CallbackInfo callbackInfo = (CallbackInfo)param1Message.obj;
      switch (param1Message.what) {
        default:
          return;
        case 8:
          this.mCallback.onShortcutsChanged(callbackInfo.packageName, callbackInfo.shortcuts, callbackInfo.user);
        case 7:
          this.mCallback.onPackagesUnsuspended(callbackInfo.packageNames, callbackInfo.user);
        case 6:
          this.mCallback.onPackagesSuspended(callbackInfo.packageNames, callbackInfo.user, callbackInfo.launcherExtras);
        case 5:
          this.mCallback.onPackagesUnavailable(callbackInfo.packageNames, callbackInfo.user, callbackInfo.replacing);
        case 4:
          this.mCallback.onPackagesAvailable(callbackInfo.packageNames, callbackInfo.user, callbackInfo.replacing);
        case 3:
          this.mCallback.onPackageChanged(callbackInfo.packageName, callbackInfo.user);
        case 2:
          this.mCallback.onPackageRemoved(callbackInfo.packageName, callbackInfo.user);
        case 1:
          break;
      } 
      this.mCallback.onPackageAdded(callbackInfo.packageName, callbackInfo.user);
    }
    
    public void postOnPackageAdded(String param1String, UserHandle param1UserHandle) {
      CallbackInfo callbackInfo = new CallbackInfo();
      callbackInfo.packageName = param1String;
      callbackInfo.user = param1UserHandle;
      obtainMessage(1, callbackInfo).sendToTarget();
    }
    
    public void postOnPackageChanged(String param1String, UserHandle param1UserHandle) {
      CallbackInfo callbackInfo = new CallbackInfo();
      callbackInfo.packageName = param1String;
      callbackInfo.user = param1UserHandle;
      obtainMessage(3, callbackInfo).sendToTarget();
    }
    
    public void postOnPackageRemoved(String param1String, UserHandle param1UserHandle) {
      CallbackInfo callbackInfo = new CallbackInfo();
      callbackInfo.packageName = param1String;
      callbackInfo.user = param1UserHandle;
      obtainMessage(2, callbackInfo).sendToTarget();
    }
    
    public void postOnPackagesAvailable(String[] param1ArrayOfString, UserHandle param1UserHandle, boolean param1Boolean) {
      CallbackInfo callbackInfo = new CallbackInfo();
      callbackInfo.packageNames = param1ArrayOfString;
      callbackInfo.replacing = param1Boolean;
      callbackInfo.user = param1UserHandle;
      obtainMessage(4, callbackInfo).sendToTarget();
    }
    
    public void postOnPackagesSuspended(String[] param1ArrayOfString, Bundle param1Bundle, UserHandle param1UserHandle) {
      CallbackInfo callbackInfo = new CallbackInfo();
      callbackInfo.packageNames = param1ArrayOfString;
      callbackInfo.user = param1UserHandle;
      callbackInfo.launcherExtras = param1Bundle;
      obtainMessage(6, callbackInfo).sendToTarget();
    }
    
    public void postOnPackagesUnavailable(String[] param1ArrayOfString, UserHandle param1UserHandle, boolean param1Boolean) {
      CallbackInfo callbackInfo = new CallbackInfo();
      callbackInfo.packageNames = param1ArrayOfString;
      callbackInfo.replacing = param1Boolean;
      callbackInfo.user = param1UserHandle;
      obtainMessage(5, callbackInfo).sendToTarget();
    }
    
    public void postOnPackagesUnsuspended(String[] param1ArrayOfString, UserHandle param1UserHandle) {
      CallbackInfo callbackInfo = new CallbackInfo();
      callbackInfo.packageNames = param1ArrayOfString;
      callbackInfo.user = param1UserHandle;
      obtainMessage(7, callbackInfo).sendToTarget();
    }
    
    public void postOnShortcutChanged(String param1String, UserHandle param1UserHandle, List<ShortcutInfo> param1List) {
      CallbackInfo callbackInfo = new CallbackInfo();
      callbackInfo.packageName = param1String;
      callbackInfo.user = param1UserHandle;
      callbackInfo.shortcuts = param1List;
      obtainMessage(8, callbackInfo).sendToTarget();
    }
    
    private static class CallbackInfo {
      Bundle launcherExtras;
      
      String packageName;
      
      String[] packageNames;
      
      boolean replacing;
      
      List<ShortcutInfo> shortcuts;
      
      UserHandle user;
      
      private CallbackInfo() {}
    }
  }
  
  private static class CallbackInfo {
    Bundle launcherExtras;
    
    String packageName;
    
    String[] packageNames;
    
    boolean replacing;
    
    List<ShortcutInfo> shortcuts;
    
    UserHandle user;
    
    private CallbackInfo() {}
  }
  
  public static final class PinItemRequest implements Parcelable {
    public static final Parcelable.Creator<PinItemRequest> CREATOR = new Parcelable.Creator<PinItemRequest>() {
        public LauncherApps.PinItemRequest createFromParcel(Parcel param2Parcel) {
          return new LauncherApps.PinItemRequest(param2Parcel);
        }
        
        public LauncherApps.PinItemRequest[] newArray(int param2Int) {
          return new LauncherApps.PinItemRequest[param2Int];
        }
      };
    
    public static final int REQUEST_TYPE_APPWIDGET = 2;
    
    public static final int REQUEST_TYPE_SHORTCUT = 1;
    
    private final IPinItemRequest mInner;
    
    private final int mRequestType;
    
    public PinItemRequest(IPinItemRequest param1IPinItemRequest, int param1Int) {
      this.mInner = param1IPinItemRequest;
      this.mRequestType = param1Int;
    }
    
    private PinItemRequest(Parcel param1Parcel) {
      getClass().getClassLoader();
      this.mRequestType = param1Parcel.readInt();
      this.mInner = IPinItemRequest.Stub.asInterface(param1Parcel.readStrongBinder());
    }
    
    public boolean accept() {
      return accept(null);
    }
    
    public boolean accept(Bundle param1Bundle) {
      try {
        return this.mInner.accept(param1Bundle);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public AppWidgetProviderInfo getAppWidgetProviderInfo(Context param1Context) {
      try {
        AppWidgetProviderInfo appWidgetProviderInfo = this.mInner.getAppWidgetProviderInfo();
        if (appWidgetProviderInfo == null)
          return null; 
        appWidgetProviderInfo.updateDimensions(param1Context.getResources().getDisplayMetrics());
        return appWidgetProviderInfo;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } 
    }
    
    public Bundle getExtras() {
      try {
        return this.mInner.getExtras();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } 
    }
    
    public int getRequestType() {
      return this.mRequestType;
    }
    
    public ShortcutInfo getShortcutInfo() {
      try {
        return this.mInner.getShortcutInfo();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } 
    }
    
    public boolean isValid() {
      try {
        return this.mInner.isValid();
      } catch (RemoteException remoteException) {
        return false;
      } 
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mRequestType);
      param1Parcel.writeStrongBinder(this.mInner.asBinder());
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface RequestType {}
  }
  
  class null implements Parcelable.Creator<PinItemRequest> {
    public LauncherApps.PinItemRequest createFromParcel(Parcel param1Parcel) {
      return new LauncherApps.PinItemRequest(param1Parcel);
    }
    
    public LauncherApps.PinItemRequest[] newArray(int param1Int) {
      return new LauncherApps.PinItemRequest[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface RequestType {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ShortcutCacheFlags {}
  
  public static interface ShortcutChangeCallback {
    default void onShortcutsAddedOrUpdated(String param1String, List<ShortcutInfo> param1List, UserHandle param1UserHandle) {}
    
    default void onShortcutsRemoved(String param1String, List<ShortcutInfo> param1List, UserHandle param1UserHandle) {}
  }
  
  private static class ShortcutChangeCallbackProxy extends IShortcutChangeCallback.Stub {
    private final WeakReference<Pair<Executor, LauncherApps.ShortcutChangeCallback>> mRemoteReferences;
    
    ShortcutChangeCallbackProxy(Executor param1Executor, LauncherApps.ShortcutChangeCallback param1ShortcutChangeCallback) {
      this.mRemoteReferences = new WeakReference<>(new Pair(param1Executor, param1ShortcutChangeCallback));
    }
    
    public void onShortcutsAddedOrUpdated(String param1String, List<ShortcutInfo> param1List, UserHandle param1UserHandle) {
      Pair pair = this.mRemoteReferences.get();
      if (pair == null)
        return; 
      Executor executor = (Executor)pair.first;
      LauncherApps.ShortcutChangeCallback shortcutChangeCallback = (LauncherApps.ShortcutChangeCallback)pair.second;
      executor.execute((Runnable)PooledLambda.obtainRunnable((QuadConsumer)_$$Lambda$ixVkOBzuX9ZQHchageObICICvzs.INSTANCE, shortcutChangeCallback, param1String, param1List, param1UserHandle).recycleOnUse());
    }
    
    public void onShortcutsRemoved(String param1String, List<ShortcutInfo> param1List, UserHandle param1UserHandle) {
      Pair pair = this.mRemoteReferences.get();
      if (pair == null)
        return; 
      Executor executor = (Executor)pair.first;
      LauncherApps.ShortcutChangeCallback shortcutChangeCallback = (LauncherApps.ShortcutChangeCallback)pair.second;
      executor.execute((Runnable)PooledLambda.obtainRunnable((QuadConsumer)_$$Lambda$us1_9lD180TeidUshXKquRrDKoU.INSTANCE, shortcutChangeCallback, param1String, param1List, param1UserHandle).recycleOnUse());
    }
  }
  
  public static class ShortcutQuery {
    @Deprecated
    public static final int FLAG_GET_ALL_KINDS = 27;
    
    @Deprecated
    public static final int FLAG_GET_DYNAMIC = 1;
    
    public static final int FLAG_GET_KEY_FIELDS_ONLY = 4;
    
    @Deprecated
    public static final int FLAG_GET_MANIFEST = 8;
    
    @Deprecated
    public static final int FLAG_GET_PINNED = 2;
    
    public static final int FLAG_MATCH_ALL_KINDS = 27;
    
    public static final int FLAG_MATCH_ALL_KINDS_WITH_ALL_PINNED = 1051;
    
    public static final int FLAG_MATCH_CACHED = 16;
    
    public static final int FLAG_MATCH_DYNAMIC = 1;
    
    public static final int FLAG_MATCH_MANIFEST = 8;
    
    public static final int FLAG_MATCH_PINNED = 2;
    
    public static final int FLAG_MATCH_PINNED_BY_ANY_LAUNCHER = 1024;
    
    ComponentName mActivity;
    
    long mChangedSince;
    
    List<LocusId> mLocusIds;
    
    String mPackage;
    
    int mQueryFlags;
    
    List<String> mShortcutIds;
    
    public ShortcutQuery setActivity(ComponentName param1ComponentName) {
      this.mActivity = param1ComponentName;
      return this;
    }
    
    public ShortcutQuery setChangedSince(long param1Long) {
      this.mChangedSince = param1Long;
      return this;
    }
    
    public ShortcutQuery setLocusIds(List<LocusId> param1List) {
      this.mLocusIds = param1List;
      return this;
    }
    
    public ShortcutQuery setPackage(String param1String) {
      this.mPackage = param1String;
      return this;
    }
    
    public ShortcutQuery setQueryFlags(int param1Int) {
      this.mQueryFlags = param1Int;
      return this;
    }
    
    public ShortcutQuery setShortcutIds(List<String> param1List) {
      this.mShortcutIds = param1List;
      return this;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface QueryFlags {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface QueryFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
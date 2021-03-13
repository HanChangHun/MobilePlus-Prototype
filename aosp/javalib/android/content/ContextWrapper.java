package android.content;

import android.annotation.SystemApi;
import android.app.IApplicationThread;
import android.app.IServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.view.DisplayAdjustments;
import android.view.autofill.AutofillManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

public class ContextWrapper extends Context {
  String MY_TAG = "201521037";
  
  Context mBase;
  
  public ContextWrapper(Context paramContext) {
    this.mBase = paramContext;
  }
  
  protected void attachBaseContext(Context paramContext) {
    if (this.mBase == null) {
      this.mBase = paramContext;
      return;
    } 
    throw new IllegalStateException("Base context already set");
  }
  
  public boolean bindIsolatedService(Intent paramIntent, int paramInt, String paramString, Executor paramExecutor, ServiceConnection paramServiceConnection) {
    return this.mBase.bindIsolatedService(paramIntent, paramInt, paramString, paramExecutor, paramServiceConnection);
  }
  
  public boolean bindService(Intent paramIntent, int paramInt, Executor paramExecutor, ServiceConnection paramServiceConnection) {
    return this.mBase.bindService(paramIntent, paramInt, paramExecutor, paramServiceConnection);
  }
  
  public boolean bindService(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt) {
    boolean bool;
    int i = Binder.getCallingUid();
    if (i == 10135 || i == 10136) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      String str = this.MY_TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("UID: ");
      stringBuilder.append(i);
      stringBuilder.append(" ContextWrapper: bindService");
      Log.d(str, stringBuilder.toString());
    } 
    return this.mBase.bindService(paramIntent, paramServiceConnection, paramInt);
  }
  
  public boolean bindServiceAsUser(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt, Handler paramHandler, UserHandle paramUserHandle) {
    return this.mBase.bindServiceAsUser(paramIntent, paramServiceConnection, paramInt, paramHandler, paramUserHandle);
  }
  
  public boolean bindServiceAsUser(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt, UserHandle paramUserHandle) {
    return this.mBase.bindServiceAsUser(paramIntent, paramServiceConnection, paramInt, paramUserHandle);
  }
  
  public boolean canLoadUnsafeResources() {
    return this.mBase.canLoadUnsafeResources();
  }
  
  public boolean canStartActivityForResult() {
    return this.mBase.canStartActivityForResult();
  }
  
  public int checkCallingOrSelfPermission(String paramString) {
    return this.mBase.checkCallingOrSelfPermission(paramString);
  }
  
  public int checkCallingOrSelfUriPermission(Uri paramUri, int paramInt) {
    return this.mBase.checkCallingOrSelfUriPermission(paramUri, paramInt);
  }
  
  public int checkCallingPermission(String paramString) {
    return this.mBase.checkCallingPermission(paramString);
  }
  
  public int checkCallingUriPermission(Uri paramUri, int paramInt) {
    return this.mBase.checkCallingUriPermission(paramUri, paramInt);
  }
  
  public int checkPermission(String paramString, int paramInt1, int paramInt2) {
    return this.mBase.checkPermission(paramString, paramInt1, paramInt2);
  }
  
  public int checkPermission(String paramString, int paramInt1, int paramInt2, IBinder paramIBinder) {
    return this.mBase.checkPermission(paramString, paramInt1, paramInt2, paramIBinder);
  }
  
  public int checkSelfPermission(String paramString) {
    return this.mBase.checkSelfPermission(paramString);
  }
  
  public int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3) {
    return this.mBase.checkUriPermission(paramUri, paramInt1, paramInt2, paramInt3);
  }
  
  public int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3, IBinder paramIBinder) {
    return this.mBase.checkUriPermission(paramUri, paramInt1, paramInt2, paramInt3, paramIBinder);
  }
  
  public int checkUriPermission(Uri paramUri, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3) {
    return this.mBase.checkUriPermission(paramUri, paramString1, paramString2, paramInt1, paramInt2, paramInt3);
  }
  
  @Deprecated
  public void clearWallpaper() throws IOException {
    this.mBase.clearWallpaper();
  }
  
  public Context createApplicationContext(ApplicationInfo paramApplicationInfo, int paramInt) throws PackageManager.NameNotFoundException {
    return this.mBase.createApplicationContext(paramApplicationInfo, paramInt);
  }
  
  public Context createAttributionContext(String paramString) {
    return this.mBase.createAttributionContext(paramString);
  }
  
  public Context createConfigurationContext(Configuration paramConfiguration) {
    return this.mBase.createConfigurationContext(paramConfiguration);
  }
  
  public Context createContextAsUser(UserHandle paramUserHandle, int paramInt) {
    return this.mBase.createContextAsUser(paramUserHandle, paramInt);
  }
  
  public Context createContextForSplit(String paramString) throws PackageManager.NameNotFoundException {
    return this.mBase.createContextForSplit(paramString);
  }
  
  @SystemApi
  public Context createCredentialProtectedStorageContext() {
    return this.mBase.createCredentialProtectedStorageContext();
  }
  
  public Context createDeviceProtectedStorageContext() {
    return this.mBase.createDeviceProtectedStorageContext();
  }
  
  public Context createDisplayContext(Display paramDisplay) {
    return this.mBase.createDisplayContext(paramDisplay);
  }
  
  public Context createPackageContext(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return this.mBase.createPackageContext(paramString, paramInt);
  }
  
  public Context createPackageContextAsUser(String paramString, int paramInt, UserHandle paramUserHandle) throws PackageManager.NameNotFoundException {
    return this.mBase.createPackageContextAsUser(paramString, paramInt, paramUserHandle);
  }
  
  public Context createWindowContext(int paramInt, Bundle paramBundle) {
    return this.mBase.createWindowContext(paramInt, paramBundle);
  }
  
  public String[] databaseList() {
    return this.mBase.databaseList();
  }
  
  public boolean deleteDatabase(String paramString) {
    return this.mBase.deleteDatabase(paramString);
  }
  
  public boolean deleteFile(String paramString) {
    return this.mBase.deleteFile(paramString);
  }
  
  public boolean deleteSharedPreferences(String paramString) {
    return this.mBase.deleteSharedPreferences(paramString);
  }
  
  public void enforceCallingOrSelfPermission(String paramString1, String paramString2) {
    this.mBase.enforceCallingOrSelfPermission(paramString1, paramString2);
  }
  
  public void enforceCallingOrSelfUriPermission(Uri paramUri, int paramInt, String paramString) {
    this.mBase.enforceCallingOrSelfUriPermission(paramUri, paramInt, paramString);
  }
  
  public void enforceCallingPermission(String paramString1, String paramString2) {
    this.mBase.enforceCallingPermission(paramString1, paramString2);
  }
  
  public void enforceCallingUriPermission(Uri paramUri, int paramInt, String paramString) {
    this.mBase.enforceCallingUriPermission(paramUri, paramInt, paramString);
  }
  
  public void enforcePermission(String paramString1, int paramInt1, int paramInt2, String paramString2) {
    this.mBase.enforcePermission(paramString1, paramInt1, paramInt2, paramString2);
  }
  
  public void enforceUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3, String paramString) {
    this.mBase.enforceUriPermission(paramUri, paramInt1, paramInt2, paramInt3, paramString);
  }
  
  public void enforceUriPermission(Uri paramUri, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, String paramString3) {
    this.mBase.enforceUriPermission(paramUri, paramString1, paramString2, paramInt1, paramInt2, paramInt3, paramString3);
  }
  
  public String[] fileList() {
    return this.mBase.fileList();
  }
  
  public IBinder getActivityToken() {
    return this.mBase.getActivityToken();
  }
  
  public Context getApplicationContext() {
    return this.mBase.getApplicationContext();
  }
  
  public ApplicationInfo getApplicationInfo() {
    return this.mBase.getApplicationInfo();
  }
  
  public AssetManager getAssets() {
    return this.mBase.getAssets();
  }
  
  public String getAttributionTag() {
    return this.mBase.getAttributionTag();
  }
  
  public AutofillManager.AutofillClient getAutofillClient() {
    return this.mBase.getAutofillClient();
  }
  
  public AutofillOptions getAutofillOptions() {
    AutofillOptions autofillOptions;
    Context context = this.mBase;
    if (context == null) {
      context = null;
    } else {
      autofillOptions = context.getAutofillOptions();
    } 
    return autofillOptions;
  }
  
  public Context getBaseContext() {
    return this.mBase;
  }
  
  public String getBasePackageName() {
    return this.mBase.getBasePackageName();
  }
  
  public File getCacheDir() {
    return this.mBase.getCacheDir();
  }
  
  public ClassLoader getClassLoader() {
    return this.mBase.getClassLoader();
  }
  
  public File getCodeCacheDir() {
    return this.mBase.getCodeCacheDir();
  }
  
  public ContentCaptureOptions getContentCaptureOptions() {
    ContentCaptureOptions contentCaptureOptions;
    Context context = this.mBase;
    if (context == null) {
      context = null;
    } else {
      contentCaptureOptions = context.getContentCaptureOptions();
    } 
    return contentCaptureOptions;
  }
  
  public ContentResolver getContentResolver() {
    return this.mBase.getContentResolver();
  }
  
  public File getCrateDir(String paramString) {
    return this.mBase.getCrateDir(paramString);
  }
  
  public File getDataDir() {
    return this.mBase.getDataDir();
  }
  
  public File getDatabasePath(String paramString) {
    return this.mBase.getDatabasePath(paramString);
  }
  
  public File getDir(String paramString, int paramInt) {
    return this.mBase.getDir(paramString, paramInt);
  }
  
  public Display getDisplay() {
    return this.mBase.getDisplay();
  }
  
  public DisplayAdjustments getDisplayAdjustments(int paramInt) {
    return this.mBase.getDisplayAdjustments(paramInt);
  }
  
  public int getDisplayId() {
    return this.mBase.getDisplayId();
  }
  
  public Display getDisplayNoVerify() {
    return this.mBase.getDisplayNoVerify();
  }
  
  public File getExternalCacheDir() {
    return this.mBase.getExternalCacheDir();
  }
  
  public File[] getExternalCacheDirs() {
    return this.mBase.getExternalCacheDirs();
  }
  
  public File getExternalFilesDir(String paramString) {
    return this.mBase.getExternalFilesDir(paramString);
  }
  
  public File[] getExternalFilesDirs(String paramString) {
    return this.mBase.getExternalFilesDirs(paramString);
  }
  
  public File[] getExternalMediaDirs() {
    return this.mBase.getExternalMediaDirs();
  }
  
  public File getFileStreamPath(String paramString) {
    return this.mBase.getFileStreamPath(paramString);
  }
  
  public File getFilesDir() {
    return this.mBase.getFilesDir();
  }
  
  public IApplicationThread getIApplicationThread() {
    return this.mBase.getIApplicationThread();
  }
  
  public Executor getMainExecutor() {
    return this.mBase.getMainExecutor();
  }
  
  public Looper getMainLooper() {
    return this.mBase.getMainLooper();
  }
  
  public Handler getMainThreadHandler() {
    return this.mBase.getMainThreadHandler();
  }
  
  public int getNextAutofillId() {
    return this.mBase.getNextAutofillId();
  }
  
  public File getNoBackupFilesDir() {
    return this.mBase.getNoBackupFilesDir();
  }
  
  public File getObbDir() {
    return this.mBase.getObbDir();
  }
  
  public File[] getObbDirs() {
    return this.mBase.getObbDirs();
  }
  
  public String getOpPackageName() {
    return this.mBase.getOpPackageName();
  }
  
  public String getPackageCodePath() {
    return this.mBase.getPackageCodePath();
  }
  
  public PackageManager getPackageManager() {
    return this.mBase.getPackageManager();
  }
  
  public String getPackageName() {
    return this.mBase.getPackageName();
  }
  
  public String getPackageResourcePath() {
    return this.mBase.getPackageResourcePath();
  }
  
  public File getPreloadsFileCache() {
    return this.mBase.getPreloadsFileCache();
  }
  
  public Resources getResources() {
    return this.mBase.getResources();
  }
  
  public IServiceConnection getServiceDispatcher(ServiceConnection paramServiceConnection, Handler paramHandler, int paramInt) {
    return this.mBase.getServiceDispatcher(paramServiceConnection, paramHandler, paramInt);
  }
  
  public SharedPreferences getSharedPreferences(File paramFile, int paramInt) {
    return this.mBase.getSharedPreferences(paramFile, paramInt);
  }
  
  public SharedPreferences getSharedPreferences(String paramString, int paramInt) {
    return this.mBase.getSharedPreferences(paramString, paramInt);
  }
  
  public File getSharedPreferencesPath(String paramString) {
    return this.mBase.getSharedPreferencesPath(paramString);
  }
  
  public Object getSystemService(String paramString) {
    return this.mBase.getSystemService(paramString);
  }
  
  public String getSystemServiceName(Class<?> paramClass) {
    return this.mBase.getSystemServiceName(paramClass);
  }
  
  public Resources.Theme getTheme() {
    return this.mBase.getTheme();
  }
  
  public int getThemeResId() {
    return this.mBase.getThemeResId();
  }
  
  public UserHandle getUser() {
    return this.mBase.getUser();
  }
  
  public int getUserId() {
    return this.mBase.getUserId();
  }
  
  @Deprecated
  public Drawable getWallpaper() {
    return this.mBase.getWallpaper();
  }
  
  @Deprecated
  public int getWallpaperDesiredMinimumHeight() {
    return this.mBase.getWallpaperDesiredMinimumHeight();
  }
  
  @Deprecated
  public int getWallpaperDesiredMinimumWidth() {
    return this.mBase.getWallpaperDesiredMinimumWidth();
  }
  
  public void grantUriPermission(String paramString, Uri paramUri, int paramInt) {
    this.mBase.grantUriPermission(paramString, paramUri, paramInt);
  }
  
  @SystemApi
  public boolean isCredentialProtectedStorage() {
    return this.mBase.isCredentialProtectedStorage();
  }
  
  public boolean isDeviceProtectedStorage() {
    return this.mBase.isDeviceProtectedStorage();
  }
  
  public boolean isRestricted() {
    return this.mBase.isRestricted();
  }
  
  public boolean isUiContext() {
    Context context = this.mBase;
    return (context == null) ? false : context.isUiContext();
  }
  
  public boolean moveDatabaseFrom(Context paramContext, String paramString) {
    return this.mBase.moveDatabaseFrom(paramContext, paramString);
  }
  
  public boolean moveSharedPreferencesFrom(Context paramContext, String paramString) {
    return this.mBase.moveSharedPreferencesFrom(paramContext, paramString);
  }
  
  public FileInputStream openFileInput(String paramString) throws FileNotFoundException {
    return this.mBase.openFileInput(paramString);
  }
  
  public FileOutputStream openFileOutput(String paramString, int paramInt) throws FileNotFoundException {
    return this.mBase.openFileOutput(paramString, paramInt);
  }
  
  public SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory) {
    return this.mBase.openOrCreateDatabase(paramString, paramInt, paramCursorFactory);
  }
  
  public SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory, DatabaseErrorHandler paramDatabaseErrorHandler) {
    return this.mBase.openOrCreateDatabase(paramString, paramInt, paramCursorFactory, paramDatabaseErrorHandler);
  }
  
  @Deprecated
  public Drawable peekWallpaper() {
    return this.mBase.peekWallpaper();
  }
  
  public Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter) {
    return this.mBase.registerReceiver(paramBroadcastReceiver, paramIntentFilter);
  }
  
  public Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, int paramInt) {
    return this.mBase.registerReceiver(paramBroadcastReceiver, paramIntentFilter, paramInt);
  }
  
  public Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, String paramString, Handler paramHandler) {
    return this.mBase.registerReceiver(paramBroadcastReceiver, paramIntentFilter, paramString, paramHandler);
  }
  
  public Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, String paramString, Handler paramHandler, int paramInt) {
    return this.mBase.registerReceiver(paramBroadcastReceiver, paramIntentFilter, paramString, paramHandler, paramInt);
  }
  
  public Intent registerReceiverAsUser(BroadcastReceiver paramBroadcastReceiver, UserHandle paramUserHandle, IntentFilter paramIntentFilter, String paramString, Handler paramHandler) {
    return this.mBase.registerReceiverAsUser(paramBroadcastReceiver, paramUserHandle, paramIntentFilter, paramString, paramHandler);
  }
  
  public Intent registerReceiverForAllUsers(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, String paramString, Handler paramHandler) {
    return this.mBase.registerReceiverForAllUsers(paramBroadcastReceiver, paramIntentFilter, paramString, paramHandler);
  }
  
  public void reloadSharedPreferences() {
    this.mBase.reloadSharedPreferences();
  }
  
  @Deprecated
  public void removeStickyBroadcast(Intent paramIntent) {
    this.mBase.removeStickyBroadcast(paramIntent);
  }
  
  @Deprecated
  public void removeStickyBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    this.mBase.removeStickyBroadcastAsUser(paramIntent, paramUserHandle);
  }
  
  public void revokeUriPermission(Uri paramUri, int paramInt) {
    this.mBase.revokeUriPermission(paramUri, paramInt);
  }
  
  public void revokeUriPermission(String paramString, Uri paramUri, int paramInt) {
    this.mBase.revokeUriPermission(paramString, paramUri, paramInt);
  }
  
  public void sendBroadcast(Intent paramIntent) {
    this.mBase.sendBroadcast(paramIntent);
  }
  
  public void sendBroadcast(Intent paramIntent, String paramString) {
    this.mBase.sendBroadcast(paramIntent, paramString);
  }
  
  public void sendBroadcast(Intent paramIntent, String paramString, int paramInt) {
    this.mBase.sendBroadcast(paramIntent, paramString, paramInt);
  }
  
  @SystemApi
  public void sendBroadcast(Intent paramIntent, String paramString, Bundle paramBundle) {
    this.mBase.sendBroadcast(paramIntent, paramString, paramBundle);
  }
  
  public void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    this.mBase.sendBroadcastAsUser(paramIntent, paramUserHandle);
  }
  
  public void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString) {
    this.mBase.sendBroadcastAsUser(paramIntent, paramUserHandle, paramString);
  }
  
  public void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString, int paramInt) {
    this.mBase.sendBroadcastAsUser(paramIntent, paramUserHandle, paramString, paramInt);
  }
  
  public void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString, Bundle paramBundle) {
    this.mBase.sendBroadcastAsUser(paramIntent, paramUserHandle, paramString, paramBundle);
  }
  
  public void sendBroadcastAsUserMultiplePermissions(Intent paramIntent, UserHandle paramUserHandle, String[] paramArrayOfString) {
    this.mBase.sendBroadcastAsUserMultiplePermissions(paramIntent, paramUserHandle, paramArrayOfString);
  }
  
  public void sendBroadcastMultiplePermissions(Intent paramIntent, String[] paramArrayOfString) {
    this.mBase.sendBroadcastMultiplePermissions(paramIntent, paramArrayOfString);
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, int paramInt, String paramString1, String paramString2, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, String paramString3, Bundle paramBundle1, Bundle paramBundle2) {
    this.mBase.sendOrderedBroadcast(paramIntent, paramInt, paramString1, paramString2, paramBroadcastReceiver, paramHandler, paramString3, paramBundle1, paramBundle2);
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, String paramString) {
    this.mBase.sendOrderedBroadcast(paramIntent, paramString);
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, String paramString1, int paramInt1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt2, String paramString2, Bundle paramBundle) {
    this.mBase.sendOrderedBroadcast(paramIntent, paramString1, paramInt1, paramBroadcastReceiver, paramHandler, paramInt2, paramString2, paramBundle);
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, String paramString1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString2, Bundle paramBundle) {
    this.mBase.sendOrderedBroadcast(paramIntent, paramString1, paramBroadcastReceiver, paramHandler, paramInt, paramString2, paramBundle);
  }
  
  @SystemApi
  public void sendOrderedBroadcast(Intent paramIntent, String paramString1, Bundle paramBundle1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString2, Bundle paramBundle2) {
    this.mBase.sendOrderedBroadcast(paramIntent, paramString1, paramBundle1, paramBroadcastReceiver, paramHandler, paramInt, paramString2, paramBundle2);
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, String paramString1, String paramString2, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString3, Bundle paramBundle) {
    this.mBase.sendOrderedBroadcast(paramIntent, paramString1, paramString2, paramBroadcastReceiver, paramHandler, paramInt, paramString3, paramBundle);
  }
  
  public void sendOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString1, int paramInt1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt2, String paramString2, Bundle paramBundle) {
    this.mBase.sendOrderedBroadcastAsUser(paramIntent, paramUserHandle, paramString1, paramInt1, paramBroadcastReceiver, paramHandler, paramInt2, paramString2, paramBundle);
  }
  
  public void sendOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString1, int paramInt1, Bundle paramBundle1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt2, String paramString2, Bundle paramBundle2) {
    this.mBase.sendOrderedBroadcastAsUser(paramIntent, paramUserHandle, paramString1, paramInt1, paramBundle1, paramBroadcastReceiver, paramHandler, paramInt2, paramString2, paramBundle2);
  }
  
  public void sendOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString2, Bundle paramBundle) {
    this.mBase.sendOrderedBroadcastAsUser(paramIntent, paramUserHandle, paramString1, paramBroadcastReceiver, paramHandler, paramInt, paramString2, paramBundle);
  }
  
  @Deprecated
  public void sendStickyBroadcast(Intent paramIntent) {
    this.mBase.sendStickyBroadcast(paramIntent);
  }
  
  @Deprecated
  public void sendStickyBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    this.mBase.sendStickyBroadcastAsUser(paramIntent, paramUserHandle);
  }
  
  @Deprecated
  public void sendStickyBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, Bundle paramBundle) {
    this.mBase.sendStickyBroadcastAsUser(paramIntent, paramUserHandle, paramBundle);
  }
  
  @Deprecated
  public void sendStickyOrderedBroadcast(Intent paramIntent, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString, Bundle paramBundle) {
    this.mBase.sendStickyOrderedBroadcast(paramIntent, paramBroadcastReceiver, paramHandler, paramInt, paramString, paramBundle);
  }
  
  @Deprecated
  public void sendStickyOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString, Bundle paramBundle) {
    this.mBase.sendStickyOrderedBroadcastAsUser(paramIntent, paramUserHandle, paramBroadcastReceiver, paramHandler, paramInt, paramString, paramBundle);
  }
  
  public void setAutofillClient(AutofillManager.AutofillClient paramAutofillClient) {
    this.mBase.setAutofillClient(paramAutofillClient);
  }
  
  public void setAutofillOptions(AutofillOptions paramAutofillOptions) {
    Context context = this.mBase;
    if (context != null)
      context.setAutofillOptions(paramAutofillOptions); 
  }
  
  public void setContentCaptureOptions(ContentCaptureOptions paramContentCaptureOptions) {
    Context context = this.mBase;
    if (context != null)
      context.setContentCaptureOptions(paramContentCaptureOptions); 
  }
  
  public void setTheme(int paramInt) {
    this.mBase.setTheme(paramInt);
  }
  
  @Deprecated
  public void setWallpaper(Bitmap paramBitmap) throws IOException {
    this.mBase.setWallpaper(paramBitmap);
  }
  
  @Deprecated
  public void setWallpaper(InputStream paramInputStream) throws IOException {
    this.mBase.setWallpaper(paramInputStream);
  }
  
  public void startActivities(Intent[] paramArrayOfIntent) {
    this.mBase.startActivities(paramArrayOfIntent);
  }
  
  public void startActivities(Intent[] paramArrayOfIntent, Bundle paramBundle) {
    this.mBase.startActivities(paramArrayOfIntent, paramBundle);
  }
  
  public int startActivitiesAsUser(Intent[] paramArrayOfIntent, Bundle paramBundle, UserHandle paramUserHandle) {
    return this.mBase.startActivitiesAsUser(paramArrayOfIntent, paramBundle, paramUserHandle);
  }
  
  public void startActivity(Intent paramIntent) {
    this.mBase.startActivity(paramIntent);
  }
  
  public void startActivity(Intent paramIntent, Bundle paramBundle) {
    this.mBase.startActivity(paramIntent, paramBundle);
  }
  
  public void startActivityAsUser(Intent paramIntent, Bundle paramBundle, UserHandle paramUserHandle) {
    this.mBase.startActivityAsUser(paramIntent, paramBundle, paramUserHandle);
  }
  
  public void startActivityAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    this.mBase.startActivityAsUser(paramIntent, paramUserHandle);
  }
  
  public void startActivityForResult(String paramString, Intent paramIntent, int paramInt, Bundle paramBundle) {
    this.mBase.startActivityForResult(paramString, paramIntent, paramInt, paramBundle);
  }
  
  public ComponentName startForegroundService(Intent paramIntent) {
    return this.mBase.startForegroundService(paramIntent);
  }
  
  public ComponentName startForegroundServiceAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    return this.mBase.startForegroundServiceAsUser(paramIntent, paramUserHandle);
  }
  
  public boolean startInstrumentation(ComponentName paramComponentName, String paramString, Bundle paramBundle) {
    return this.mBase.startInstrumentation(paramComponentName, paramString, paramBundle);
  }
  
  public void startIntentSender(IntentSender paramIntentSender, Intent paramIntent, int paramInt1, int paramInt2, int paramInt3) throws IntentSender.SendIntentException {
    this.mBase.startIntentSender(paramIntentSender, paramIntent, paramInt1, paramInt2, paramInt3);
  }
  
  public void startIntentSender(IntentSender paramIntentSender, Intent paramIntent, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle) throws IntentSender.SendIntentException {
    this.mBase.startIntentSender(paramIntentSender, paramIntent, paramInt1, paramInt2, paramInt3, paramBundle);
  }
  
  public ComponentName startService(Intent paramIntent) {
    return this.mBase.startService(paramIntent);
  }
  
  public ComponentName startServiceAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    return this.mBase.startServiceAsUser(paramIntent, paramUserHandle);
  }
  
  public boolean stopService(Intent paramIntent) {
    return this.mBase.stopService(paramIntent);
  }
  
  public boolean stopServiceAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    return this.mBase.stopServiceAsUser(paramIntent, paramUserHandle);
  }
  
  public void unbindService(ServiceConnection paramServiceConnection) {
    this.mBase.unbindService(paramServiceConnection);
  }
  
  public void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver) {
    this.mBase.unregisterReceiver(paramBroadcastReceiver);
  }
  
  public void updateDisplay(int paramInt) {
    this.mBase.updateDisplay(paramInt);
  }
  
  public void updateServiceGroup(ServiceConnection paramServiceConnection, int paramInt1, int paramInt2) {
    this.mBase.updateServiceGroup(paramServiceConnection, paramInt1, paramInt2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContextWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content;

import android.annotation.SystemApi;
import android.app.IApplicationThread;
import android.app.IServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.DisplayAdjustments;
import android.view.ViewDebug.ExportedProperty;
import android.view.autofill.AutofillManager;
import android.view.contentcapture.ContentCaptureManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

public abstract class Context {
  public static final String ACCESSIBILITY_SERVICE = "accessibility";
  
  public static final String ACCOUNT_SERVICE = "account";
  
  public static final String ACTIVITY_SERVICE = "activity";
  
  public static final String ACTIVITY_TASK_SERVICE = "activity_task";
  
  public static final String ADB_SERVICE = "adb";
  
  public static final String ALARM_SERVICE = "alarm";
  
  public static final String APPWIDGET_SERVICE = "appwidget";
  
  public static final String APP_BINDING_SERVICE = "app_binding";
  
  @SystemApi
  public static final String APP_INTEGRITY_SERVICE = "app_integrity";
  
  public static final String APP_OPS_SERVICE = "appops";
  
  @SystemApi
  public static final String APP_PREDICTION_SERVICE = "app_prediction";
  
  public static final String ATTENTION_SERVICE = "attention";
  
  public static final String AUDIO_SERVICE = "audio";
  
  public static final String AUTH_SERVICE = "auth";
  
  public static final String AUTOFILL_MANAGER_SERVICE = "autofill";
  
  @SystemApi
  public static final String BACKUP_SERVICE = "backup";
  
  public static final String BATTERY_SERVICE = "batterymanager";
  
  @SystemApi
  public static final String BATTERY_STATS_SERVICE = "batterystats";
  
  public static final int BIND_ABOVE_CLIENT = 8;
  
  public static final int BIND_ADJUST_WITH_ACTIVITY = 128;
  
  public static final int BIND_ALLOW_BACKGROUND_ACTIVITY_STARTS = 1048576;
  
  public static final int BIND_ALLOW_INSTANT = 4194304;
  
  public static final int BIND_ALLOW_OOM_MANAGEMENT = 16;
  
  public static final int BIND_ALLOW_WHITELIST_MANAGEMENT = 16777216;
  
  public static final int BIND_AUTO_CREATE = 1;
  
  public static final int BIND_DEBUG_UNBIND = 2;
  
  public static final int BIND_EXTERNAL_SERVICE = -2147483648;
  
  public static final int BIND_FOREGROUND_SERVICE = 67108864;
  
  public static final int BIND_FOREGROUND_SERVICE_WHILE_AWAKE = 33554432;
  
  public static final int BIND_IMPORTANT = 64;
  
  public static final int BIND_IMPORTANT_BACKGROUND = 8388608;
  
  public static final int BIND_INCLUDE_CAPABILITIES = 4096;
  
  public static final int BIND_NOT_FOREGROUND = 4;
  
  public static final int BIND_NOT_PERCEPTIBLE = 256;
  
  public static final int BIND_NOT_VISIBLE = 1073741824;
  
  public static final int BIND_REDUCTION_FLAGS = 1073742128;
  
  public static final int BIND_RESTRICT_ASSOCIATIONS = 2097152;
  
  public static final int BIND_SCHEDULE_LIKE_TOP_APP = 524288;
  
  public static final int BIND_SHOWING_UI = 536870912;
  
  public static final int BIND_TREAT_LIKE_ACTIVITY = 134217728;
  
  public static final int BIND_VISIBLE = 268435456;
  
  public static final int BIND_WAIVE_PRIORITY = 32;
  
  public static final String BIOMETRIC_SERVICE = "biometric";
  
  public static final String BLOB_STORE_SERVICE = "blob_store";
  
  public static final String BLUETOOTH_SERVICE = "bluetooth";
  
  @SystemApi
  public static final String BUGREPORT_SERVICE = "bugreport";
  
  public static final String CAMERA_SERVICE = "camera";
  
  public static final String CAPTIONING_SERVICE = "captioning";
  
  public static final String CARRIER_CONFIG_SERVICE = "carrier_config";
  
  public static final String CLIPBOARD_SERVICE = "clipboard";
  
  public static final String COLOR_DISPLAY_SERVICE = "color_display";
  
  public static final String COMPANION_DEVICE_SERVICE = "companiondevice";
  
  public static final String CONNECTIVITY_DIAGNOSTICS_SERVICE = "connectivity_diagnostics";
  
  public static final String CONNECTIVITY_SERVICE = "connectivity";
  
  public static final String CONSUMER_IR_SERVICE = "consumer_ir";
  
  public static final String CONTENT_CAPTURE_MANAGER_SERVICE = "content_capture";
  
  @SystemApi
  public static final String CONTENT_SUGGESTIONS_SERVICE = "content_suggestions";
  
  @SystemApi
  public static final String CONTEXTHUB_SERVICE = "contexthub";
  
  public static final int CONTEXT_CREDENTIAL_PROTECTED_STORAGE = 16;
  
  public static final int CONTEXT_DEVICE_PROTECTED_STORAGE = 8;
  
  public static final int CONTEXT_IGNORE_SECURITY = 2;
  
  public static final int CONTEXT_INCLUDE_CODE = 1;
  
  public static final int CONTEXT_REGISTER_PACKAGE = 1073741824;
  
  public static final int CONTEXT_RESTRICTED = 4;
  
  public static final String COUNTRY_DETECTOR = "country_detector";
  
  public static final String CROSS_PROFILE_APPS_SERVICE = "crossprofileapps";
  
  public static final String DATA_LOADER_MANAGER_SERVICE = "dataloader_manager";
  
  public static final String DEVICE_IDENTIFIERS_SERVICE = "device_identifiers";
  
  public static final String DEVICE_IDLE_CONTROLLER = "deviceidle";
  
  public static final String DEVICE_POLICY_SERVICE = "device_policy";
  
  public static final String DISPLAY_SERVICE = "display";
  
  public static final String DOWNLOAD_SERVICE = "download";
  
  public static final String DREAM_SERVICE = "dream";
  
  public static final String DROPBOX_SERVICE = "dropbox";
  
  public static final String DYNAMIC_SYSTEM_SERVICE = "dynamic_system";
  
  @SystemApi
  public static final String ETHERNET_SERVICE = "ethernet";
  
  @SystemApi
  public static final String EUICC_CARD_SERVICE = "euicc_card";
  
  public static final String EUICC_SERVICE = "euicc";
  
  public static final String FACE_SERVICE = "face";
  
  public static final String FILE_INTEGRITY_SERVICE = "file_integrity";
  
  public static final String FINGERPRINT_SERVICE = "fingerprint";
  
  public static final String GATEKEEPER_SERVICE = "android.service.gatekeeper.IGateKeeperService";
  
  public static final String HARDWARE_PROPERTIES_SERVICE = "hardware_properties";
  
  @SystemApi
  public static final String HDMI_CONTROL_SERVICE = "hdmi_control";
  
  public static final String IDMAP_SERVICE = "idmap";
  
  public static final String INCIDENT_COMPANION_SERVICE = "incidentcompanion";
  
  public static final String INCIDENT_SERVICE = "incident";
  
  public static final String INCREMENTAL_SERVICE = "incremental";
  
  public static final String INPUT_METHOD_SERVICE = "input_method";
  
  public static final String INPUT_SERVICE = "input";
  
  public static final String IPSEC_SERVICE = "ipsec";
  
  public static final String IRIS_SERVICE = "iris";
  
  public static final String JOB_SCHEDULER_SERVICE = "jobscheduler";
  
  public static final String KEYGUARD_SERVICE = "keyguard";
  
  public static final String LAUNCHER_APPS_SERVICE = "launcherapps";
  
  public static final String LAYOUT_INFLATER_SERVICE = "layout_inflater";
  
  public static final String LIGHTS_SERVICE = "lights";
  
  public static final String LOCATION_SERVICE = "location";
  
  public static final String LOWPAN_SERVICE = "lowpan";
  
  public static final String MEDIA_PROJECTION_SERVICE = "media_projection";
  
  public static final String MEDIA_ROUTER_SERVICE = "media_router";
  
  public static final String MEDIA_SESSION_SERVICE = "media_session";
  
  public static final String MIDI_SERVICE = "midi";
  
  public static final String MMS_SERVICE = "mms";
  
  public static final int MODE_APPEND = 32768;
  
  public static final int MODE_ENABLE_WRITE_AHEAD_LOGGING = 8;
  
  @Deprecated
  public static final int MODE_MULTI_PROCESS = 4;
  
  public static final int MODE_NO_LOCALIZED_COLLATORS = 16;
  
  public static final int MODE_PRIVATE = 0;
  
  @Deprecated
  public static final int MODE_WORLD_READABLE = 1;
  
  @Deprecated
  public static final int MODE_WORLD_WRITEABLE = 2;
  
  @SystemApi
  public static final String NETD_SERVICE = "netd";
  
  public static final String NETWORKMANAGEMENT_SERVICE = "network_management";
  
  public static final String NETWORK_POLICY_SERVICE = "netpolicy";
  
  @SystemApi
  public static final String NETWORK_SCORE_SERVICE = "network_score";
  
  public static final String NETWORK_STACK_SERVICE = "network_stack";
  
  public static final String NETWORK_STATS_SERVICE = "netstats";
  
  public static final String NETWORK_WATCHLIST_SERVICE = "network_watchlist";
  
  public static final String NFC_SERVICE = "nfc";
  
  public static final String NOTIFICATION_SERVICE = "notification";
  
  public static final String NSD_SERVICE = "servicediscovery";
  
  @SystemApi
  public static final String OEM_LOCK_SERVICE = "oem_lock";
  
  public static final String OVERLAY_SERVICE = "overlay";
  
  public static final String PERMISSION_CONTROLLER_SERVICE = "permission_controller";
  
  @SystemApi
  public static final String PERMISSION_SERVICE = "permission";
  
  @SystemApi
  public static final String PERSISTENT_DATA_BLOCK_SERVICE = "persistent_data_block";
  
  public static final String PLATFORM_COMPAT_NATIVE_SERVICE = "platform_compat_native";
  
  public static final String PLATFORM_COMPAT_SERVICE = "platform_compat";
  
  public static final String POWER_SERVICE = "power";
  
  public static final String POWER_WHITELIST_MANAGER = "power_whitelist";
  
  public static final String PRINT_SERVICE = "print";
  
  public static final String RADIO_SERVICE = "broadcastradio";
  
  public static final int RECEIVER_VISIBLE_TO_INSTANT_APPS = 1;
  
  public static final String RECOVERY_SERVICE = "recovery";
  
  public static final String RESTRICTIONS_SERVICE = "restrictions";
  
  public static final String ROLE_CONTROLLER_SERVICE = "role_controller";
  
  public static final String ROLE_SERVICE = "role";
  
  @SystemApi
  public static final String ROLLBACK_SERVICE = "rollback";
  
  public static final String SEARCH_SERVICE = "search";
  
  @SystemApi
  public static final String SECURE_ELEMENT_SERVICE = "secure_element";
  
  public static final String SENSOR_PRIVACY_SERVICE = "sensor_privacy";
  
  public static final String SENSOR_SERVICE = "sensor";
  
  public static final String SERIAL_SERVICE = "serial";
  
  public static final String SHORTCUT_SERVICE = "shortcut";
  
  public static final String SIP_SERVICE = "sip";
  
  public static final String SLICE_SERVICE = "slice";
  
  public static final String SOUND_TRIGGER_MIDDLEWARE_SERVICE = "soundtrigger_middleware";
  
  public static final String SOUND_TRIGGER_SERVICE = "soundtrigger";
  
  public static final String STATS_COMPANION_SERVICE = "statscompanion";
  
  @SystemApi
  public static final String STATS_MANAGER = "stats";
  
  public static final String STATS_MANAGER_SERVICE = "statsmanager";
  
  @SystemApi
  public static final String STATUS_BAR_SERVICE = "statusbar";
  
  public static final String STORAGE_SERVICE = "storage";
  
  public static final String STORAGE_STATS_SERVICE = "storagestats";
  
  @SystemApi
  public static final String SYSTEM_CONFIG_SERVICE = "system_config";
  
  public static final String SYSTEM_HEALTH_SERVICE = "systemhealth";
  
  @SystemApi
  public static final String SYSTEM_UPDATE_SERVICE = "system_update";
  
  public static final String TELECOM_SERVICE = "telecom";
  
  public static final String TELEPHONY_IMS_SERVICE = "telephony_ims";
  
  public static final String TELEPHONY_RCS_MESSAGE_SERVICE = "ircsmessage";
  
  public static final String TELEPHONY_REGISTRY_SERVICE = "telephony_registry";
  
  public static final String TELEPHONY_SERVICE = "phone";
  
  public static final String TELEPHONY_SUBSCRIPTION_SERVICE = "telephony_subscription_service";
  
  public static final String TEST_NETWORK_SERVICE = "test_network";
  
  @SystemApi
  public static final String TETHERING_SERVICE = "tethering";
  
  public static final String TEXT_CLASSIFICATION_SERVICE = "textclassification";
  
  public static final String TEXT_SERVICES_MANAGER_SERVICE = "textservices";
  
  public static final String THERMAL_SERVICE = "thermalservice";
  
  public static final String TIME_DETECTOR_SERVICE = "time_detector";
  
  public static final String TIME_ZONE_DETECTOR_SERVICE = "time_zone_detector";
  
  public static final String TIME_ZONE_RULES_MANAGER_SERVICE = "timezone";
  
  public static final String TRUST_SERVICE = "trust";
  
  public static final String TV_INPUT_SERVICE = "tv_input";
  
  public static final String TV_TUNER_RESOURCE_MGR_SERVICE = "tv_tuner_resource_mgr";
  
  public static final String UI_MODE_SERVICE = "uimode";
  
  public static final String UPDATE_LOCK_SERVICE = "updatelock";
  
  public static final String URI_GRANTS_SERVICE = "uri_grants";
  
  public static final String USAGE_STATS_SERVICE = "usagestats";
  
  public static final String USB_SERVICE = "usb";
  
  public static final String USER_SERVICE = "user";
  
  public static final String VIBRATOR_SERVICE = "vibrator";
  
  public static final String VOICE_INTERACTION_MANAGER_SERVICE = "voiceinteraction";
  
  public static final String VPN_MANAGEMENT_SERVICE = "vpn_management";
  
  @SystemApi
  public static final String VR_SERVICE = "vrmanager";
  
  public static final String WALLPAPER_SERVICE = "wallpaper";
  
  public static final String WIFI_AWARE_SERVICE = "wifiaware";
  
  @SystemApi
  public static final String WIFI_NL80211_SERVICE = "wifinl80211";
  
  public static final String WIFI_P2P_SERVICE = "wifip2p";
  
  public static final String WIFI_RTT_RANGING_SERVICE = "wifirtt";
  
  @SystemApi
  @Deprecated
  public static final String WIFI_RTT_SERVICE = "rttmanager";
  
  @SystemApi
  public static final String WIFI_SCANNING_SERVICE = "wifiscanner";
  
  public static final String WIFI_SERVICE = "wifi";
  
  public static final String WINDOW_SERVICE = "window";
  
  private static int sLastAutofillId = -1;
  
  public void assertRuntimeOverlayThemable() {
    if (getResources() != Resources.getSystem())
      return; 
    throw new IllegalArgumentException("Non-UI context used to display UI; get a UI context from ActivityThread#getSystemUiContext()");
  }
  
  public boolean bindIsolatedService(Intent paramIntent, int paramInt, String paramString, Executor paramExecutor, ServiceConnection paramServiceConnection) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public boolean bindService(Intent paramIntent, int paramInt, Executor paramExecutor, ServiceConnection paramServiceConnection) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract boolean bindService(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt);
  
  public boolean bindServiceAsUser(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt, Handler paramHandler, UserHandle paramUserHandle) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public boolean bindServiceAsUser(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt, UserHandle paramUserHandle) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract boolean canLoadUnsafeResources();
  
  public boolean canStartActivityForResult() {
    return false;
  }
  
  public abstract int checkCallingOrSelfPermission(String paramString);
  
  public abstract int checkCallingOrSelfUriPermission(Uri paramUri, int paramInt);
  
  public abstract int checkCallingPermission(String paramString);
  
  public abstract int checkCallingUriPermission(Uri paramUri, int paramInt);
  
  public abstract int checkPermission(String paramString, int paramInt1, int paramInt2);
  
  public abstract int checkPermission(String paramString, int paramInt1, int paramInt2, IBinder paramIBinder);
  
  public abstract int checkSelfPermission(String paramString);
  
  public abstract int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3, IBinder paramIBinder);
  
  public abstract int checkUriPermission(Uri paramUri, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3);
  
  @Deprecated
  public abstract void clearWallpaper() throws IOException;
  
  public abstract Context createApplicationContext(ApplicationInfo paramApplicationInfo, int paramInt) throws PackageManager.NameNotFoundException;
  
  public Context createAttributionContext(String paramString) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract Context createConfigurationContext(Configuration paramConfiguration);
  
  @SystemApi
  public Context createContextAsUser(UserHandle paramUserHandle, int paramInt) {
    if (!Build.IS_ENG)
      return this; 
    throw new IllegalStateException("createContextAsUser not overridden!");
  }
  
  public abstract Context createContextForSplit(String paramString) throws PackageManager.NameNotFoundException;
  
  @SystemApi
  public abstract Context createCredentialProtectedStorageContext();
  
  public abstract Context createDeviceProtectedStorageContext();
  
  public abstract Context createDisplayContext(Display paramDisplay);
  
  @Deprecated
  public Context createFeatureContext(String paramString) {
    return createAttributionContext(paramString);
  }
  
  public abstract Context createPackageContext(String paramString, int paramInt) throws PackageManager.NameNotFoundException;
  
  @SystemApi
  public Context createPackageContextAsUser(String paramString, int paramInt, UserHandle paramUserHandle) throws PackageManager.NameNotFoundException {
    if (!Build.IS_ENG)
      return this; 
    throw new IllegalStateException("createPackageContextAsUser not overridden!");
  }
  
  public Context createWindowContext(int paramInt, Bundle paramBundle) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract String[] databaseList();
  
  public abstract boolean deleteDatabase(String paramString);
  
  public abstract boolean deleteFile(String paramString);
  
  public abstract boolean deleteSharedPreferences(String paramString);
  
  public abstract void enforceCallingOrSelfPermission(String paramString1, String paramString2);
  
  public abstract void enforceCallingOrSelfUriPermission(Uri paramUri, int paramInt, String paramString);
  
  public abstract void enforceCallingPermission(String paramString1, String paramString2);
  
  public abstract void enforceCallingUriPermission(Uri paramUri, int paramInt, String paramString);
  
  public abstract void enforcePermission(String paramString1, int paramInt1, int paramInt2, String paramString2);
  
  public abstract void enforceUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3, String paramString);
  
  public abstract void enforceUriPermission(Uri paramUri, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, String paramString3);
  
  public abstract String[] fileList();
  
  public IBinder getActivityToken() {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract Context getApplicationContext();
  
  public abstract ApplicationInfo getApplicationInfo();
  
  public abstract AssetManager getAssets();
  
  public String getAttributionTag() {
    return null;
  }
  
  public AutofillManager.AutofillClient getAutofillClient() {
    return null;
  }
  
  public AutofillOptions getAutofillOptions() {
    return null;
  }
  
  public abstract String getBasePackageName();
  
  public abstract File getCacheDir();
  
  public abstract ClassLoader getClassLoader();
  
  public abstract File getCodeCacheDir();
  
  public final int getColor(int paramInt) {
    return getResources().getColor(paramInt, getTheme());
  }
  
  public final ColorStateList getColorStateList(int paramInt) {
    return getResources().getColorStateList(paramInt, getTheme());
  }
  
  public ContentCaptureManager.ContentCaptureClient getContentCaptureClient() {
    return null;
  }
  
  public ContentCaptureOptions getContentCaptureOptions() {
    return null;
  }
  
  public abstract ContentResolver getContentResolver();
  
  public File getCrateDir(String paramString) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract File getDataDir();
  
  public abstract File getDatabasePath(String paramString);
  
  public abstract File getDir(String paramString, int paramInt);
  
  public Display getDisplay() {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract DisplayAdjustments getDisplayAdjustments(int paramInt);
  
  public abstract int getDisplayId();
  
  public Display getDisplayNoVerify() {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public final Drawable getDrawable(int paramInt) {
    return getResources().getDrawable(paramInt, getTheme());
  }
  
  public abstract File getExternalCacheDir();
  
  public abstract File[] getExternalCacheDirs();
  
  public abstract File getExternalFilesDir(String paramString);
  
  public abstract File[] getExternalFilesDirs(String paramString);
  
  @Deprecated
  public abstract File[] getExternalMediaDirs();
  
  @Deprecated
  public String getFeatureId() {
    return getAttributionTag();
  }
  
  public abstract File getFileStreamPath(String paramString);
  
  public abstract File getFilesDir();
  
  public IApplicationThread getIApplicationThread() {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public Executor getMainExecutor() {
    return (Executor)new HandlerExecutor(new Handler(getMainLooper()));
  }
  
  public abstract Looper getMainLooper();
  
  public Handler getMainThreadHandler() {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public int getNextAutofillId() {
    if (sLastAutofillId == 1073741822)
      sLastAutofillId = -1; 
    int i = sLastAutofillId + 1;
    sLastAutofillId = i;
    return i;
  }
  
  public abstract File getNoBackupFilesDir();
  
  public abstract File getObbDir();
  
  public abstract File[] getObbDirs();
  
  public String getOpPackageName() {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract String getPackageCodePath();
  
  public abstract PackageManager getPackageManager();
  
  public abstract String getPackageName();
  
  public abstract String getPackageResourcePath();
  
  @SystemApi
  public abstract File getPreloadsFileCache();
  
  public abstract Resources getResources();
  
  public IServiceConnection getServiceDispatcher(ServiceConnection paramServiceConnection, Handler paramHandler, int paramInt) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract SharedPreferences getSharedPreferences(File paramFile, int paramInt);
  
  public abstract SharedPreferences getSharedPreferences(String paramString, int paramInt);
  
  public abstract File getSharedPreferencesPath(String paramString);
  
  @Deprecated
  public File getSharedPrefsFile(String paramString) {
    return getSharedPreferencesPath(paramString);
  }
  
  public final String getString(int paramInt) {
    return getResources().getString(paramInt);
  }
  
  public final String getString(int paramInt, Object... paramVarArgs) {
    return getResources().getString(paramInt, paramVarArgs);
  }
  
  public final <T> T getSystemService(Class<T> paramClass) {
    String str = getSystemServiceName(paramClass);
    if (str != null) {
      Object object = getSystemService(str);
    } else {
      str = null;
    } 
    return (T)str;
  }
  
  public abstract Object getSystemService(String paramString);
  
  public abstract String getSystemServiceName(Class<?> paramClass);
  
  public final CharSequence getText(int paramInt) {
    return getResources().getText(paramInt);
  }
  
  @ExportedProperty(deepExport = true)
  public abstract Resources.Theme getTheme();
  
  public int getThemeResId() {
    return 0;
  }
  
  public UserHandle getUser() {
    return Process.myUserHandle();
  }
  
  public int getUserId() {
    return UserHandle.myUserId();
  }
  
  @Deprecated
  public abstract Drawable getWallpaper();
  
  @Deprecated
  public abstract int getWallpaperDesiredMinimumHeight();
  
  @Deprecated
  public abstract int getWallpaperDesiredMinimumWidth();
  
  public abstract void grantUriPermission(String paramString, Uri paramUri, int paramInt);
  
  public final boolean isAutofillCompatibilityEnabled() {
    boolean bool;
    AutofillOptions autofillOptions = getAutofillOptions();
    if (autofillOptions != null && autofillOptions.compatModeEnabled) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @SystemApi
  public abstract boolean isCredentialProtectedStorage();
  
  public abstract boolean isDeviceProtectedStorage();
  
  public boolean isRestricted() {
    return false;
  }
  
  public boolean isUiContext() {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract boolean moveDatabaseFrom(Context paramContext, String paramString);
  
  public abstract boolean moveSharedPreferencesFrom(Context paramContext, String paramString);
  
  public final TypedArray obtainStyledAttributes(int paramInt, int[] paramArrayOfint) throws Resources.NotFoundException {
    return getTheme().obtainStyledAttributes(paramInt, paramArrayOfint);
  }
  
  public final TypedArray obtainStyledAttributes(AttributeSet paramAttributeSet, int[] paramArrayOfint) {
    return getTheme().obtainStyledAttributes(paramAttributeSet, paramArrayOfint, 0, 0);
  }
  
  public final TypedArray obtainStyledAttributes(AttributeSet paramAttributeSet, int[] paramArrayOfint, int paramInt1, int paramInt2) {
    return getTheme().obtainStyledAttributes(paramAttributeSet, paramArrayOfint, paramInt1, paramInt2);
  }
  
  public final TypedArray obtainStyledAttributes(int[] paramArrayOfint) {
    return getTheme().obtainStyledAttributes(paramArrayOfint);
  }
  
  public abstract FileInputStream openFileInput(String paramString) throws FileNotFoundException;
  
  public abstract FileOutputStream openFileOutput(String paramString, int paramInt) throws FileNotFoundException;
  
  public abstract SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory);
  
  public abstract SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory, DatabaseErrorHandler paramDatabaseErrorHandler);
  
  @Deprecated
  public abstract Drawable peekWallpaper();
  
  public void registerComponentCallbacks(ComponentCallbacks paramComponentCallbacks) {
    getApplicationContext().registerComponentCallbacks(paramComponentCallbacks);
  }
  
  public abstract Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter);
  
  public abstract Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, int paramInt);
  
  public abstract Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, String paramString, Handler paramHandler);
  
  public abstract Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, String paramString, Handler paramHandler, int paramInt);
  
  public abstract Intent registerReceiverAsUser(BroadcastReceiver paramBroadcastReceiver, UserHandle paramUserHandle, IntentFilter paramIntentFilter, String paramString, Handler paramHandler);
  
  @SystemApi
  public Intent registerReceiverForAllUsers(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, String paramString, Handler paramHandler) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract void reloadSharedPreferences();
  
  @Deprecated
  public abstract void removeStickyBroadcast(Intent paramIntent);
  
  @Deprecated
  public abstract void removeStickyBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle);
  
  public abstract void revokeUriPermission(Uri paramUri, int paramInt);
  
  public abstract void revokeUriPermission(String paramString, Uri paramUri, int paramInt);
  
  public abstract void sendBroadcast(Intent paramIntent);
  
  public abstract void sendBroadcast(Intent paramIntent, String paramString);
  
  public abstract void sendBroadcast(Intent paramIntent, String paramString, int paramInt);
  
  @SystemApi
  public abstract void sendBroadcast(Intent paramIntent, String paramString, Bundle paramBundle);
  
  public abstract void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle);
  
  public abstract void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString);
  
  public abstract void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString, int paramInt);
  
  @SystemApi
  public abstract void sendBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString, Bundle paramBundle);
  
  public abstract void sendBroadcastAsUserMultiplePermissions(Intent paramIntent, UserHandle paramUserHandle, String[] paramArrayOfString);
  
  public void sendBroadcastMultiplePermissions(Intent paramIntent, String[] paramArrayOfString) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public void sendBroadcastWithMultiplePermissions(Intent paramIntent, String[] paramArrayOfString) {
    sendBroadcastMultiplePermissions(paramIntent, paramArrayOfString);
  }
  
  public void sendOrderedBroadcast(Intent paramIntent, int paramInt, String paramString1, String paramString2, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, String paramString3, Bundle paramBundle1, Bundle paramBundle2) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract void sendOrderedBroadcast(Intent paramIntent, String paramString);
  
  public abstract void sendOrderedBroadcast(Intent paramIntent, String paramString1, int paramInt1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt2, String paramString2, Bundle paramBundle);
  
  public abstract void sendOrderedBroadcast(Intent paramIntent, String paramString1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString2, Bundle paramBundle);
  
  @SystemApi
  public abstract void sendOrderedBroadcast(Intent paramIntent, String paramString1, Bundle paramBundle1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString2, Bundle paramBundle2);
  
  public void sendOrderedBroadcast(Intent paramIntent, String paramString1, String paramString2, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString3, Bundle paramBundle) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract void sendOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString1, int paramInt1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt2, String paramString2, Bundle paramBundle);
  
  public abstract void sendOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString1, int paramInt1, Bundle paramBundle1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt2, String paramString2, Bundle paramBundle2);
  
  public abstract void sendOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, String paramString1, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString2, Bundle paramBundle);
  
  @Deprecated
  public abstract void sendStickyBroadcast(Intent paramIntent);
  
  @Deprecated
  public abstract void sendStickyBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle);
  
  @Deprecated
  public abstract void sendStickyBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, Bundle paramBundle);
  
  @Deprecated
  public abstract void sendStickyOrderedBroadcast(Intent paramIntent, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString, Bundle paramBundle);
  
  @Deprecated
  public abstract void sendStickyOrderedBroadcastAsUser(Intent paramIntent, UserHandle paramUserHandle, BroadcastReceiver paramBroadcastReceiver, Handler paramHandler, int paramInt, String paramString, Bundle paramBundle);
  
  public void setAutofillClient(AutofillManager.AutofillClient paramAutofillClient) {}
  
  public void setAutofillOptions(AutofillOptions paramAutofillOptions) {}
  
  public void setContentCaptureOptions(ContentCaptureOptions paramContentCaptureOptions) {}
  
  public abstract void setTheme(int paramInt);
  
  @Deprecated
  public abstract void setWallpaper(Bitmap paramBitmap) throws IOException;
  
  @Deprecated
  public abstract void setWallpaper(InputStream paramInputStream) throws IOException;
  
  public abstract void startActivities(Intent[] paramArrayOfIntent);
  
  public abstract void startActivities(Intent[] paramArrayOfIntent, Bundle paramBundle);
  
  public int startActivitiesAsUser(Intent[] paramArrayOfIntent, Bundle paramBundle, UserHandle paramUserHandle) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract void startActivity(Intent paramIntent);
  
  public abstract void startActivity(Intent paramIntent, Bundle paramBundle);
  
  public void startActivityAsUser(Intent paramIntent, Bundle paramBundle, UserHandle paramUserHandle) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  @SystemApi
  public void startActivityAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public void startActivityForResult(String paramString, Intent paramIntent, int paramInt, Bundle paramBundle) {
    throw new RuntimeException("This method is only implemented for Activity-based Contexts. Check canStartActivityForResult() before calling.");
  }
  
  public abstract ComponentName startForegroundService(Intent paramIntent);
  
  public abstract ComponentName startForegroundServiceAsUser(Intent paramIntent, UserHandle paramUserHandle);
  
  public abstract boolean startInstrumentation(ComponentName paramComponentName, String paramString, Bundle paramBundle);
  
  public abstract void startIntentSender(IntentSender paramIntentSender, Intent paramIntent, int paramInt1, int paramInt2, int paramInt3) throws IntentSender.SendIntentException;
  
  public abstract void startIntentSender(IntentSender paramIntentSender, Intent paramIntent, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle) throws IntentSender.SendIntentException;
  
  public abstract ComponentName startService(Intent paramIntent);
  
  public abstract ComponentName startServiceAsUser(Intent paramIntent, UserHandle paramUserHandle);
  
  public abstract boolean stopService(Intent paramIntent);
  
  public abstract boolean stopServiceAsUser(Intent paramIntent, UserHandle paramUserHandle);
  
  public abstract void unbindService(ServiceConnection paramServiceConnection);
  
  public void unregisterComponentCallbacks(ComponentCallbacks paramComponentCallbacks) {
    getApplicationContext().unregisterComponentCallbacks(paramComponentCallbacks);
  }
  
  public abstract void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver);
  
  public abstract void updateDisplay(int paramInt);
  
  public void updateServiceGroup(ServiceConnection paramServiceConnection, int paramInt1, int paramInt2) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface BindServiceFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface CreatePackageOptions {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface DatabaseMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface FileMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PreferencesMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface RegisterReceiverFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ServiceName {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/Context.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
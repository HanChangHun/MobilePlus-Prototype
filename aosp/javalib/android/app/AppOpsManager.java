package android.app;

import android.annotation.IntRange;
import android.annotation.NonNull;
import android.annotation.SystemApi;
import android.compat.Compatibility;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ParceledListSlice;
import android.database.DatabaseUtils;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.LongSparseArray;
import android.util.LongSparseLongArray;
import android.util.Pools;
import android.util.SparseArray;
import com.android.internal.app.IAppOpsActiveCallback;
import com.android.internal.app.IAppOpsAsyncNotedCallback;
import com.android.internal.app.IAppOpsCallback;
import com.android.internal.app.IAppOpsNotedCallback;
import com.android.internal.app.IAppOpsService;
import com.android.internal.app.IAppOpsStartedCallback;
import com.android.internal.app.MessageSamplingConfig;
import com.android.internal.os.RuntimeInit;
import com.android.internal.os.ZygoteInit;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Parcelling;
import com.android.internal.util.Preconditions;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AppOpsManager {
  public static final long CALL_BACK_ON_CHANGED_LISTENER_WITH_SWITCHED_OP_CHANGE = 148180766L;
  
  public static final int CALL_BACK_ON_SWITCHED_OP = 2;
  
  private static final int COLLECT_ASYNC = 3;
  
  private static final int COLLECT_SELF = 1;
  
  private static final int COLLECT_SYNC = 2;
  
  private static final String DEBUG_LOGGING_ENABLE_PROP = "appops.logging_enabled";
  
  private static final String DEBUG_LOGGING_OPS_PROP = "appops.logging_ops";
  
  private static final String DEBUG_LOGGING_PACKAGES_PROP = "appops.logging_packages";
  
  private static final String DEBUG_LOGGING_TAG = "AppOpsManager";
  
  private static final int DONT_COLLECT = 0;
  
  public static final int FILTER_BY_ATTRIBUTION_TAG = 4;
  
  public static final int FILTER_BY_OP_NAMES = 8;
  
  public static final int FILTER_BY_PACKAGE_NAME = 2;
  
  public static final int FILTER_BY_UID = 1;
  
  private static final int FLAGS_MASK = -1;
  
  public static final int HISTORICAL_MODE_DISABLED = 0;
  
  public static final int HISTORICAL_MODE_ENABLED_ACTIVE = 1;
  
  public static final int HISTORICAL_MODE_ENABLED_PASSIVE = 2;
  
  public static final String KEY_BG_STATE_SETTLE_TIME = "bg_state_settle_time";
  
  public static final String KEY_FG_SERVICE_STATE_SETTLE_TIME = "fg_service_state_settle_time";
  
  public static final String KEY_HISTORICAL_OPS = "historical_ops";
  
  public static final String KEY_TOP_STATE_SETTLE_TIME = "top_state_settle_time";
  
  public static final int MAX_PRIORITY_UID_STATE = 100;
  
  private static final int MAX_UNFORWARDED_OPS = 10;
  
  public static final int MIN_PRIORITY_UID_STATE = 700;
  
  public static final int MODE_ALLOWED = 0;
  
  public static final int MODE_DEFAULT = 3;
  
  public static final int MODE_ERRORED = 2;
  
  public static final int MODE_FOREGROUND = 4;
  
  public static final int MODE_IGNORED = 1;
  
  public static final String[] MODE_NAMES;
  
  public static final boolean NOTE_OP_COLLECTION_ENABLED = false;
  
  @SystemApi
  public static final String OPSTR_ACCEPT_HANDOVER = "android:accept_handover";
  
  @SystemApi
  public static final String OPSTR_ACCESS_ACCESSIBILITY = "android:access_accessibility";
  
  public static final String OPSTR_ACCESS_MEDIA_LOCATION = "android:access_media_location";
  
  @SystemApi
  public static final String OPSTR_ACCESS_NOTIFICATIONS = "android:access_notifications";
  
  public static final String OPSTR_ACTIVATE_PLATFORM_VPN = "android:activate_platform_vpn";
  
  @SystemApi
  public static final String OPSTR_ACTIVATE_VPN = "android:activate_vpn";
  
  public static final String OPSTR_ACTIVITY_RECOGNITION = "android:activity_recognition";
  
  public static final String OPSTR_ADD_VOICEMAIL = "android:add_voicemail";
  
  public static final String OPSTR_ANSWER_PHONE_CALLS = "android:answer_phone_calls";
  
  @SystemApi
  public static final String OPSTR_ASSIST_SCREENSHOT = "android:assist_screenshot";
  
  @SystemApi
  public static final String OPSTR_ASSIST_STRUCTURE = "android:assist_structure";
  
  @SystemApi
  public static final String OPSTR_AUDIO_ACCESSIBILITY_VOLUME = "android:audio_accessibility_volume";
  
  @SystemApi
  public static final String OPSTR_AUDIO_ALARM_VOLUME = "android:audio_alarm_volume";
  
  @SystemApi
  public static final String OPSTR_AUDIO_BLUETOOTH_VOLUME = "android:audio_bluetooth_volume";
  
  @SystemApi
  public static final String OPSTR_AUDIO_MASTER_VOLUME = "android:audio_master_volume";
  
  @SystemApi
  public static final String OPSTR_AUDIO_MEDIA_VOLUME = "android:audio_media_volume";
  
  @SystemApi
  public static final String OPSTR_AUDIO_NOTIFICATION_VOLUME = "android:audio_notification_volume";
  
  @SystemApi
  public static final String OPSTR_AUDIO_RING_VOLUME = "android:audio_ring_volume";
  
  @SystemApi
  public static final String OPSTR_AUDIO_VOICE_VOLUME = "android:audio_voice_volume";
  
  @SystemApi
  public static final String OPSTR_AUTO_REVOKE_MANAGED_BY_INSTALLER = "android:auto_revoke_managed_by_installer";
  
  @SystemApi
  public static final String OPSTR_AUTO_REVOKE_PERMISSIONS_IF_UNUSED = "android:auto_revoke_permissions_if_unused";
  
  @SystemApi
  public static final String OPSTR_BIND_ACCESSIBILITY_SERVICE = "android:bind_accessibility_service";
  
  public static final String OPSTR_BLUETOOTH_SCAN = "android:bluetooth_scan";
  
  public static final String OPSTR_BODY_SENSORS = "android:body_sensors";
  
  public static final String OPSTR_CALL_PHONE = "android:call_phone";
  
  public static final String OPSTR_CAMERA = "android:camera";
  
  @SystemApi
  public static final String OPSTR_CHANGE_WIFI_STATE = "android:change_wifi_state";
  
  public static final String OPSTR_COARSE_LOCATION = "android:coarse_location";
  
  public static final String OPSTR_FINE_LOCATION = "android:fine_location";
  
  @SystemApi
  public static final String OPSTR_GET_ACCOUNTS = "android:get_accounts";
  
  public static final String OPSTR_GET_USAGE_STATS = "android:get_usage_stats";
  
  @SystemApi
  public static final String OPSTR_GPS = "android:gps";
  
  @SystemApi
  public static final String OPSTR_INSTANT_APP_START_FOREGROUND = "android:instant_app_start_foreground";
  
  @SystemApi
  public static final String OPSTR_INTERACT_ACROSS_PROFILES = "android:interact_across_profiles";
  
  @SystemApi
  public static final String OPSTR_LEGACY_STORAGE = "android:legacy_storage";
  
  @SystemApi
  public static final String OPSTR_LOADER_USAGE_STATS = "android:loader_usage_stats";
  
  @SystemApi
  public static final String OPSTR_MANAGE_EXTERNAL_STORAGE = "android:manage_external_storage";
  
  @SystemApi
  public static final String OPSTR_MANAGE_IPSEC_TUNNELS = "android:manage_ipsec_tunnels";
  
  public static final String OPSTR_MOCK_LOCATION = "android:mock_location";
  
  public static final String OPSTR_MONITOR_HIGH_POWER_LOCATION = "android:monitor_location_high_power";
  
  public static final String OPSTR_MONITOR_LOCATION = "android:monitor_location";
  
  @SystemApi
  public static final String OPSTR_MUTE_MICROPHONE = "android:mute_microphone";
  
  @SystemApi
  public static final String OPSTR_NEIGHBORING_CELLS = "android:neighboring_cells";
  
  public static final String OPSTR_NO_ISOLATED_STORAGE = "android:no_isolated_storage";
  
  public static final String OPSTR_PHONE_CALL_CAMERA = "android:phone_call_camera";
  
  public static final String OPSTR_PHONE_CALL_MICROPHONE = "android:phone_call_microphone";
  
  public static final String OPSTR_PICTURE_IN_PICTURE = "android:picture_in_picture";
  
  @SystemApi
  public static final String OPSTR_PLAY_AUDIO = "android:play_audio";
  
  @SystemApi
  public static final String OPSTR_POST_NOTIFICATION = "android:post_notification";
  
  public static final String OPSTR_PROCESS_OUTGOING_CALLS = "android:process_outgoing_calls";
  
  @SystemApi
  public static final String OPSTR_PROJECT_MEDIA = "android:project_media";
  
  public static final String OPSTR_QUERY_ALL_PACKAGES = "android:query_all_packages";
  
  public static final String OPSTR_READ_CALENDAR = "android:read_calendar";
  
  public static final String OPSTR_READ_CALL_LOG = "android:read_call_log";
  
  public static final String OPSTR_READ_CELL_BROADCASTS = "android:read_cell_broadcasts";
  
  @SystemApi
  public static final String OPSTR_READ_CLIPBOARD = "android:read_clipboard";
  
  public static final String OPSTR_READ_CONTACTS = "android:read_contacts";
  
  public static final String OPSTR_READ_DEVICE_IDENTIFIERS = "android:read_device_identifiers";
  
  public static final String OPSTR_READ_EXTERNAL_STORAGE = "android:read_external_storage";
  
  @SystemApi
  public static final String OPSTR_READ_ICC_SMS = "android:read_icc_sms";
  
  @SystemApi
  public static final String OPSTR_READ_MEDIA_AUDIO = "android:read_media_audio";
  
  @SystemApi
  public static final String OPSTR_READ_MEDIA_IMAGES = "android:read_media_images";
  
  @SystemApi
  public static final String OPSTR_READ_MEDIA_VIDEO = "android:read_media_video";
  
  public static final String OPSTR_READ_PHONE_NUMBERS = "android:read_phone_numbers";
  
  public static final String OPSTR_READ_PHONE_STATE = "android:read_phone_state";
  
  public static final String OPSTR_READ_SMS = "android:read_sms";
  
  @SystemApi
  public static final String OPSTR_RECEIVE_EMERGENCY_BROADCAST = "android:receive_emergency_broadcast";
  
  public static final String OPSTR_RECEIVE_MMS = "android:receive_mms";
  
  public static final String OPSTR_RECEIVE_SMS = "android:receive_sms";
  
  public static final String OPSTR_RECEIVE_WAP_PUSH = "android:receive_wap_push";
  
  public static final String OPSTR_RECORD_AUDIO = "android:record_audio";
  
  public static final String OPSTR_RECORD_AUDIO_HOTWORD = "android:record_audio_hotword";
  
  @SystemApi
  public static final String OPSTR_REQUEST_DELETE_PACKAGES = "android:request_delete_packages";
  
  @SystemApi
  public static final String OPSTR_REQUEST_INSTALL_PACKAGES = "android:request_install_packages";
  
  @SystemApi
  public static final String OPSTR_RUN_ANY_IN_BACKGROUND = "android:run_any_in_background";
  
  @SystemApi
  public static final String OPSTR_RUN_IN_BACKGROUND = "android:run_in_background";
  
  public static final String OPSTR_SEND_SMS = "android:send_sms";
  
  public static final String OPSTR_SMS_FINANCIAL_TRANSACTIONS = "android:sms_financial_transactions";
  
  @SystemApi
  public static final String OPSTR_START_FOREGROUND = "android:start_foreground";
  
  public static final String OPSTR_SYSTEM_ALERT_WINDOW = "android:system_alert_window";
  
  @SystemApi
  public static final String OPSTR_TAKE_AUDIO_FOCUS = "android:take_audio_focus";
  
  @SystemApi
  public static final String OPSTR_TAKE_MEDIA_BUTTONS = "android:take_media_buttons";
  
  @SystemApi
  public static final String OPSTR_TOAST_WINDOW = "android:toast_window";
  
  @SystemApi
  public static final String OPSTR_TURN_SCREEN_ON = "android:turn_screen_on";
  
  public static final String OPSTR_USE_BIOMETRIC = "android:use_biometric";
  
  public static final String OPSTR_USE_FINGERPRINT = "android:use_fingerprint";
  
  public static final String OPSTR_USE_SIP = "android:use_sip";
  
  @SystemApi
  public static final String OPSTR_VIBRATE = "android:vibrate";
  
  @SystemApi
  public static final String OPSTR_WAKE_LOCK = "android:wake_lock";
  
  @SystemApi
  public static final String OPSTR_WIFI_SCAN = "android:wifi_scan";
  
  public static final String OPSTR_WRITE_CALENDAR = "android:write_calendar";
  
  public static final String OPSTR_WRITE_CALL_LOG = "android:write_call_log";
  
  @SystemApi
  public static final String OPSTR_WRITE_CLIPBOARD = "android:write_clipboard";
  
  public static final String OPSTR_WRITE_CONTACTS = "android:write_contacts";
  
  public static final String OPSTR_WRITE_EXTERNAL_STORAGE = "android:write_external_storage";
  
  @SystemApi
  public static final String OPSTR_WRITE_ICC_SMS = "android:write_icc_sms";
  
  @SystemApi
  public static final String OPSTR_WRITE_MEDIA_AUDIO = "android:write_media_audio";
  
  @SystemApi
  public static final String OPSTR_WRITE_MEDIA_IMAGES = "android:write_media_images";
  
  @SystemApi
  public static final String OPSTR_WRITE_MEDIA_VIDEO = "android:write_media_video";
  
  public static final String OPSTR_WRITE_SETTINGS = "android:write_settings";
  
  @SystemApi
  public static final String OPSTR_WRITE_SMS = "android:write_sms";
  
  @SystemApi
  public static final String OPSTR_WRITE_WALLPAPER = "android:write_wallpaper";
  
  public static final int OP_ACCEPT_HANDOVER = 74;
  
  public static final int OP_ACCESS_ACCESSIBILITY = 88;
  
  public static final int OP_ACCESS_MEDIA_LOCATION = 90;
  
  public static final int OP_ACCESS_NOTIFICATIONS = 25;
  
  public static final int OP_ACTIVATE_PLATFORM_VPN = 94;
  
  public static final int OP_ACTIVATE_VPN = 47;
  
  public static final int OP_ACTIVITY_RECOGNITION = 79;
  
  public static final int OP_ADD_VOICEMAIL = 52;
  
  public static final int OP_ANSWER_PHONE_CALLS = 69;
  
  public static final int OP_ASSIST_SCREENSHOT = 50;
  
  public static final int OP_ASSIST_STRUCTURE = 49;
  
  public static final int OP_AUDIO_ACCESSIBILITY_VOLUME = 64;
  
  public static final int OP_AUDIO_ALARM_VOLUME = 37;
  
  public static final int OP_AUDIO_BLUETOOTH_VOLUME = 39;
  
  public static final int OP_AUDIO_MASTER_VOLUME = 33;
  
  public static final int OP_AUDIO_MEDIA_VOLUME = 36;
  
  public static final int OP_AUDIO_NOTIFICATION_VOLUME = 38;
  
  public static final int OP_AUDIO_RING_VOLUME = 35;
  
  public static final int OP_AUDIO_VOICE_VOLUME = 34;
  
  public static final int OP_AUTO_REVOKE_MANAGED_BY_INSTALLER = 98;
  
  public static final int OP_AUTO_REVOKE_PERMISSIONS_IF_UNUSED = 97;
  
  public static final int OP_BIND_ACCESSIBILITY_SERVICE = 73;
  
  public static final int OP_BLUETOOTH_SCAN = 77;
  
  public static final int OP_BODY_SENSORS = 56;
  
  public static final int OP_CALL_PHONE = 13;
  
  public static final int OP_CAMERA = 26;
  
  public static final int OP_CHANGE_WIFI_STATE = 71;
  
  public static final int OP_COARSE_LOCATION = 0;
  
  private static final int OP_DEPRECATED_1 = 96;
  
  public static final int OP_FINE_LOCATION = 1;
  
  @SystemApi
  public static final int OP_FLAGS_ALL = 31;
  
  @SystemApi
  public static final int OP_FLAGS_ALL_TRUSTED = 13;
  
  @SystemApi
  public static final int OP_FLAG_SELF = 1;
  
  @SystemApi
  public static final int OP_FLAG_TRUSTED_PROXIED = 8;
  
  @SystemApi
  public static final int OP_FLAG_TRUSTED_PROXY = 2;
  
  @SystemApi
  public static final int OP_FLAG_UNTRUSTED_PROXIED = 16;
  
  @SystemApi
  public static final int OP_FLAG_UNTRUSTED_PROXY = 4;
  
  public static final int OP_GET_ACCOUNTS = 62;
  
  public static final int OP_GET_USAGE_STATS = 43;
  
  public static final int OP_GPS = 2;
  
  public static final int OP_INSTANT_APP_START_FOREGROUND = 68;
  
  public static final int OP_INTERACT_ACROSS_PROFILES = 93;
  
  public static final int OP_LEGACY_STORAGE = 87;
  
  public static final int OP_LOADER_USAGE_STATS = 95;
  
  public static final int OP_MANAGE_EXTERNAL_STORAGE = 92;
  
  public static final int OP_MANAGE_IPSEC_TUNNELS = 75;
  
  public static final int OP_MOCK_LOCATION = 58;
  
  public static final int OP_MONITOR_HIGH_POWER_LOCATION = 42;
  
  public static final int OP_MONITOR_LOCATION = 41;
  
  public static final int OP_MUTE_MICROPHONE = 44;
  
  public static final int OP_NEIGHBORING_CELLS = 12;
  
  public static final int OP_NONE = -1;
  
  public static final int OP_NO_ISOLATED_STORAGE = 99;
  
  public static final int OP_PHONE_CALL_CAMERA = 101;
  
  public static final int OP_PHONE_CALL_MICROPHONE = 100;
  
  public static final int OP_PICTURE_IN_PICTURE = 67;
  
  public static final int OP_PLAY_AUDIO = 28;
  
  public static final int OP_POST_NOTIFICATION = 11;
  
  public static final int OP_PROCESS_OUTGOING_CALLS = 54;
  
  public static final int OP_PROJECT_MEDIA = 46;
  
  public static final int OP_QUERY_ALL_PACKAGES = 91;
  
  public static final int OP_READ_CALENDAR = 8;
  
  public static final int OP_READ_CALL_LOG = 6;
  
  public static final int OP_READ_CELL_BROADCASTS = 57;
  
  public static final int OP_READ_CLIPBOARD = 29;
  
  public static final int OP_READ_CONTACTS = 4;
  
  public static final int OP_READ_DEVICE_IDENTIFIERS = 89;
  
  public static final int OP_READ_EXTERNAL_STORAGE = 59;
  
  public static final int OP_READ_ICC_SMS = 21;
  
  public static final int OP_READ_MEDIA_AUDIO = 81;
  
  public static final int OP_READ_MEDIA_IMAGES = 85;
  
  public static final int OP_READ_MEDIA_VIDEO = 83;
  
  public static final int OP_READ_PHONE_NUMBERS = 65;
  
  public static final int OP_READ_PHONE_STATE = 51;
  
  public static final int OP_READ_SMS = 14;
  
  public static final int OP_RECEIVE_EMERGECY_SMS = 17;
  
  public static final int OP_RECEIVE_MMS = 18;
  
  public static final int OP_RECEIVE_SMS = 16;
  
  public static final int OP_RECEIVE_WAP_PUSH = 19;
  
  public static final int OP_RECORD_AUDIO = 27;
  
  public static final int OP_RECORD_AUDIO_HOTWORD = 102;
  
  public static final int OP_REQUEST_DELETE_PACKAGES = 72;
  
  public static final int OP_REQUEST_INSTALL_PACKAGES = 66;
  
  public static final int OP_RUN_ANY_IN_BACKGROUND = 70;
  
  public static final int OP_RUN_IN_BACKGROUND = 63;
  
  public static final int OP_SEND_SMS = 20;
  
  public static final int OP_SMS_FINANCIAL_TRANSACTIONS = 80;
  
  public static final int OP_START_FOREGROUND = 76;
  
  public static final int OP_SYSTEM_ALERT_WINDOW = 24;
  
  public static final int OP_TAKE_AUDIO_FOCUS = 32;
  
  public static final int OP_TAKE_MEDIA_BUTTONS = 31;
  
  public static final int OP_TOAST_WINDOW = 45;
  
  public static final int OP_TURN_SCREEN_ON = 61;
  
  public static final int OP_USE_BIOMETRIC = 78;
  
  public static final int OP_USE_FINGERPRINT = 55;
  
  public static final int OP_USE_SIP = 53;
  
  public static final int OP_VIBRATE = 3;
  
  public static final int OP_WAKE_LOCK = 40;
  
  public static final int OP_WIFI_SCAN = 10;
  
  public static final int OP_WRITE_CALENDAR = 9;
  
  public static final int OP_WRITE_CALL_LOG = 7;
  
  public static final int OP_WRITE_CLIPBOARD = 30;
  
  public static final int OP_WRITE_CONTACTS = 5;
  
  public static final int OP_WRITE_EXTERNAL_STORAGE = 60;
  
  public static final int OP_WRITE_ICC_SMS = 22;
  
  public static final int OP_WRITE_MEDIA_AUDIO = 82;
  
  public static final int OP_WRITE_MEDIA_IMAGES = 86;
  
  public static final int OP_WRITE_MEDIA_VIDEO = 84;
  
  public static final int OP_WRITE_SETTINGS = 23;
  
  public static final int OP_WRITE_SMS = 15;
  
  public static final int OP_WRITE_WALLPAPER = 48;
  
  private static final int[] RUNTIME_AND_APPOP_PERMISSIONS_OPS;
  
  public static final int SAMPLING_STRATEGY_BOOT_TIME_SAMPLING = 3;
  
  public static final int SAMPLING_STRATEGY_DEFAULT = 0;
  
  public static final int SAMPLING_STRATEGY_RARELY_USED = 2;
  
  public static final int SAMPLING_STRATEGY_UNIFORM = 1;
  
  private static final byte SHOULD_COLLECT_NOTE_OP = 2;
  
  private static final byte SHOULD_COLLECT_NOTE_OP_NOT_INITIALIZED = 0;
  
  private static final byte SHOULD_NOT_COLLECT_NOTE_OP = 1;
  
  public static final int[] UID_STATES;
  
  @SystemApi
  public static final int UID_STATE_BACKGROUND = 600;
  
  @SystemApi
  public static final int UID_STATE_CACHED = 700;
  
  @SystemApi
  public static final int UID_STATE_FOREGROUND = 500;
  
  @SystemApi
  public static final int UID_STATE_FOREGROUND_SERVICE = 400;
  
  @SystemApi
  @Deprecated
  public static final int UID_STATE_FOREGROUND_SERVICE_LOCATION = 300;
  
  public static final int UID_STATE_MAX_LAST_NON_RESTRICTED = 500;
  
  private static final int UID_STATE_OFFSET = 31;
  
  @SystemApi
  public static final int UID_STATE_PERSISTENT = 100;
  
  @SystemApi
  public static final int UID_STATE_TOP = 200;
  
  public static final int WATCH_FOREGROUND_CHANGES = 1;
  
  public static final int _NUM_OP = 103;
  
  private static final ThreadLocal<ArrayMap<String, long[]>> sAppOpsNotedInThisBinderTransaction;
  
  private static final byte[] sAppOpsToNote;
  
  private static final ThreadLocal<Integer> sBinderThreadCallingUid;
  
  static IBinder sClientId;
  
  private static MessageSamplingConfig sConfig;
  
  private static final Object sLock = new Object();
  
  private static OnOpNotedCallback sMessageCollector;
  
  private static OnOpNotedCallback sOnOpNotedCallback;
  
  private static RestrictionBypass[] sOpAllowSystemRestrictionBypass;
  
  private static int[] sOpDefaultMode;
  
  private static boolean[] sOpDisableReset;
  
  private static String[] sOpNames;
  
  private static String[] sOpPerms;
  
  private static String[] sOpRestrictions;
  
  private static HashMap<String, Integer> sOpStrToOp;
  
  private static String[] sOpToString;
  
  private static int[] sOpToSwitch;
  
  private static HashMap<String, Integer> sPermToOp;
  
  static IAppOpsService sService;
  
  private static ArrayList<AsyncNotedAppOp> sUnforwardedOps = new ArrayList<>();
  
  private final ArrayMap<OnOpActiveChangedListener, IAppOpsActiveCallback> mActiveWatchers = new ArrayMap();
  
  final Context mContext;
  
  private final ArrayMap<OnOpChangedListener, IAppOpsCallback> mModeWatchers = new ArrayMap();
  
  private final ArrayMap<OnOpNotedListener, IAppOpsNotedCallback> mNotedWatchers = new ArrayMap();
  
  final IAppOpsService mService;
  
  private final ArrayMap<OnOpStartedListener, IAppOpsStartedCallback> mStartedWatchers = new ArrayMap();
  
  static {
    sMessageCollector = new OnOpNotedCallback() {
        private void reportStackTraceIfNeeded(SyncNotedAppOp param1SyncNotedAppOp) {
          if (!AppOpsManager.isCollectingStackTraces())
            return; 
          MessageSamplingConfig messageSamplingConfig = AppOpsManager.sConfig;
          if (AppOpsManager.leftCircularDistance(AppOpsManager.strOpToOp(param1SyncNotedAppOp.getOp()), messageSamplingConfig.getSampledOpCode(), 103) <= messageSamplingConfig.getAcceptableLeftDistance() || messageSamplingConfig.getExpirationTimeSinceBootMillis() < SystemClock.elapsedRealtime()) {
            String str = AppOpsManager.getFormattedStackTrace();
            try {
              String str1 = ActivityThread.currentOpPackageName();
              IAppOpsService iAppOpsService = AppOpsManager.getService();
              if (str1 == null)
                str1 = ""; 
              AppOpsManager.access$102(iAppOpsService.reportRuntimeAppOpAccessMessageAndGetConfig(str1, param1SyncNotedAppOp, str));
            } catch (RemoteException remoteException) {
              remoteException.rethrowFromSystemServer();
            } 
          } 
        }
        
        public void onAsyncNoted(AsyncNotedAppOp param1AsyncNotedAppOp) {}
        
        public void onNoted(SyncNotedAppOp param1SyncNotedAppOp) {
          reportStackTraceIfNeeded(param1SyncNotedAppOp);
        }
        
        public void onSelfNoted(SyncNotedAppOp param1SyncNotedAppOp) {
          reportStackTraceIfNeeded(param1SyncNotedAppOp);
        }
      };
    MODE_NAMES = new String[] { "allow", "ignore", "deny", "default", "foreground" };
    UID_STATES = new int[] { 100, 200, 300, 400, 500, 600, 700 };
    RUNTIME_AND_APPOP_PERMISSIONS_OPS = new int[] { 
        4, 5, 62, 8, 9, 20, 16, 14, 19, 18, 
        57, 59, 60, 90, 0, 1, 51, 65, 13, 6, 
        7, 52, 53, 54, 69, 74, 27, 26, 56, 79, 
        81, 82, 83, 84, 85, 86, 25, 24, 23, 66, 
        76, 80, 75, 68, 92, 93, 95 };
    sOpToSwitch = new int[] { 
        0, 0, 0, 3, 4, 5, 6, 7, 8, 9, 
        0, 11, 0, 13, 14, 15, 16, 16, 18, 19, 
        20, 14, 15, 23, 24, 25, 26, 27, 28, 29, 
        30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 
        40, 0, 0, 43, 44, 45, 46, 47, 48, 49, 
        50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 
        60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 
        70, 71, 72, 73, 74, 75, 76, 0, 78, 79, 
        80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 
        90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 
        100, 101, 102 };
    sOpToString = new String[] { 
        "android:coarse_location", "android:fine_location", "android:gps", "android:vibrate", "android:read_contacts", "android:write_contacts", "android:read_call_log", "android:write_call_log", "android:read_calendar", "android:write_calendar", 
        "android:wifi_scan", "android:post_notification", "android:neighboring_cells", "android:call_phone", "android:read_sms", "android:write_sms", "android:receive_sms", "android:receive_emergency_broadcast", "android:receive_mms", "android:receive_wap_push", 
        "android:send_sms", "android:read_icc_sms", "android:write_icc_sms", "android:write_settings", "android:system_alert_window", "android:access_notifications", "android:camera", "android:record_audio", "android:play_audio", "android:read_clipboard", 
        "android:write_clipboard", "android:take_media_buttons", "android:take_audio_focus", "android:audio_master_volume", "android:audio_voice_volume", "android:audio_ring_volume", "android:audio_media_volume", "android:audio_alarm_volume", "android:audio_notification_volume", "android:audio_bluetooth_volume", 
        "android:wake_lock", "android:monitor_location", "android:monitor_location_high_power", "android:get_usage_stats", "android:mute_microphone", "android:toast_window", "android:project_media", "android:activate_vpn", "android:write_wallpaper", "android:assist_structure", 
        "android:assist_screenshot", "android:read_phone_state", "android:add_voicemail", "android:use_sip", "android:process_outgoing_calls", "android:use_fingerprint", "android:body_sensors", "android:read_cell_broadcasts", "android:mock_location", "android:read_external_storage", 
        "android:write_external_storage", "android:turn_screen_on", "android:get_accounts", "android:run_in_background", "android:audio_accessibility_volume", "android:read_phone_numbers", "android:request_install_packages", "android:picture_in_picture", "android:instant_app_start_foreground", "android:answer_phone_calls", 
        "android:run_any_in_background", "android:change_wifi_state", "android:request_delete_packages", "android:bind_accessibility_service", "android:accept_handover", "android:manage_ipsec_tunnels", "android:start_foreground", "android:bluetooth_scan", "android:use_biometric", "android:activity_recognition", 
        "android:sms_financial_transactions", "android:read_media_audio", "android:write_media_audio", "android:read_media_video", "android:write_media_video", "android:read_media_images", "android:write_media_images", "android:legacy_storage", "android:access_accessibility", "android:read_device_identifiers", 
        "android:access_media_location", "android:query_all_packages", "android:manage_external_storage", "android:interact_across_profiles", "android:activate_platform_vpn", "android:loader_usage_stats", "", "android:auto_revoke_permissions_if_unused", "android:auto_revoke_managed_by_installer", "android:no_isolated_storage", 
        "android:phone_call_microphone", "android:phone_call_camera", "android:record_audio_hotword" };
    sOpNames = new String[] { 
        "COARSE_LOCATION", "FINE_LOCATION", "GPS", "VIBRATE", "READ_CONTACTS", "WRITE_CONTACTS", "READ_CALL_LOG", "WRITE_CALL_LOG", "READ_CALENDAR", "WRITE_CALENDAR", 
        "WIFI_SCAN", "POST_NOTIFICATION", "NEIGHBORING_CELLS", "CALL_PHONE", "READ_SMS", "WRITE_SMS", "RECEIVE_SMS", "RECEIVE_EMERGECY_SMS", "RECEIVE_MMS", "RECEIVE_WAP_PUSH", 
        "SEND_SMS", "READ_ICC_SMS", "WRITE_ICC_SMS", "WRITE_SETTINGS", "SYSTEM_ALERT_WINDOW", "ACCESS_NOTIFICATIONS", "CAMERA", "RECORD_AUDIO", "PLAY_AUDIO", "READ_CLIPBOARD", 
        "WRITE_CLIPBOARD", "TAKE_MEDIA_BUTTONS", "TAKE_AUDIO_FOCUS", "AUDIO_MASTER_VOLUME", "AUDIO_VOICE_VOLUME", "AUDIO_RING_VOLUME", "AUDIO_MEDIA_VOLUME", "AUDIO_ALARM_VOLUME", "AUDIO_NOTIFICATION_VOLUME", "AUDIO_BLUETOOTH_VOLUME", 
        "WAKE_LOCK", "MONITOR_LOCATION", "MONITOR_HIGH_POWER_LOCATION", "GET_USAGE_STATS", "MUTE_MICROPHONE", "TOAST_WINDOW", "PROJECT_MEDIA", "ACTIVATE_VPN", "WRITE_WALLPAPER", "ASSIST_STRUCTURE", 
        "ASSIST_SCREENSHOT", "READ_PHONE_STATE", "ADD_VOICEMAIL", "USE_SIP", "PROCESS_OUTGOING_CALLS", "USE_FINGERPRINT", "BODY_SENSORS", "READ_CELL_BROADCASTS", "MOCK_LOCATION", "READ_EXTERNAL_STORAGE", 
        "WRITE_EXTERNAL_STORAGE", "TURN_ON_SCREEN", "GET_ACCOUNTS", "RUN_IN_BACKGROUND", "AUDIO_ACCESSIBILITY_VOLUME", "READ_PHONE_NUMBERS", "REQUEST_INSTALL_PACKAGES", "PICTURE_IN_PICTURE", "INSTANT_APP_START_FOREGROUND", "ANSWER_PHONE_CALLS", 
        "RUN_ANY_IN_BACKGROUND", "CHANGE_WIFI_STATE", "REQUEST_DELETE_PACKAGES", "BIND_ACCESSIBILITY_SERVICE", "ACCEPT_HANDOVER", "MANAGE_IPSEC_TUNNELS", "START_FOREGROUND", "BLUETOOTH_SCAN", "USE_BIOMETRIC", "ACTIVITY_RECOGNITION", 
        "SMS_FINANCIAL_TRANSACTIONS", "READ_MEDIA_AUDIO", "WRITE_MEDIA_AUDIO", "READ_MEDIA_VIDEO", "WRITE_MEDIA_VIDEO", "READ_MEDIA_IMAGES", "WRITE_MEDIA_IMAGES", "LEGACY_STORAGE", "ACCESS_ACCESSIBILITY", "READ_DEVICE_IDENTIFIERS", 
        "ACCESS_MEDIA_LOCATION", "QUERY_ALL_PACKAGES", "MANAGE_EXTERNAL_STORAGE", "INTERACT_ACROSS_PROFILES", "ACTIVATE_PLATFORM_VPN", "LOADER_USAGE_STATS", "deprecated", "AUTO_REVOKE_PERMISSIONS_IF_UNUSED", "AUTO_REVOKE_MANAGED_BY_INSTALLER", "NO_ISOLATED_STORAGE", 
        "PHONE_CALL_MICROPHONE", "PHONE_CALL_CAMERA", "RECORD_AUDIO_HOTWORD" };
    sOpPerms = new String[] { 
        "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", null, "android.permission.VIBRATE", "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR", 
        "android.permission.ACCESS_WIFI_STATE", null, null, "android.permission.CALL_PHONE", "android.permission.READ_SMS", null, "android.permission.RECEIVE_SMS", "android.permission.RECEIVE_EMERGENCY_BROADCAST", "android.permission.RECEIVE_MMS", "android.permission.RECEIVE_WAP_PUSH", 
        "android.permission.SEND_SMS", "android.permission.READ_SMS", null, "android.permission.WRITE_SETTINGS", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.ACCESS_NOTIFICATIONS", "android.permission.CAMERA", "android.permission.RECORD_AUDIO", null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        "android.permission.WAKE_LOCK", null, null, "android.permission.PACKAGE_USAGE_STATS", null, null, null, null, null, null, 
        null, "android.permission.READ_PHONE_STATE", "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.USE_FINGERPRINT", "android.permission.BODY_SENSORS", "android.permission.READ_CELL_BROADCASTS", null, "android.permission.READ_EXTERNAL_STORAGE", 
        "android.permission.WRITE_EXTERNAL_STORAGE", null, "android.permission.GET_ACCOUNTS", null, null, "android.permission.READ_PHONE_NUMBERS", "android.permission.REQUEST_INSTALL_PACKAGES", null, "android.permission.INSTANT_APP_FOREGROUND_SERVICE", "android.permission.ANSWER_PHONE_CALLS", 
        null, "android.permission.CHANGE_WIFI_STATE", "android.permission.REQUEST_DELETE_PACKAGES", "android.permission.BIND_ACCESSIBILITY_SERVICE", "android.permission.ACCEPT_HANDOVER", "android.permission.MANAGE_IPSEC_TUNNELS", "android.permission.FOREGROUND_SERVICE", null, "android.permission.USE_BIOMETRIC", "android.permission.ACTIVITY_RECOGNITION", 
        "android.permission.SMS_FINANCIAL_TRANSACTIONS", null, null, null, null, null, null, null, null, null, 
        "android.permission.ACCESS_MEDIA_LOCATION", null, "android.permission.MANAGE_EXTERNAL_STORAGE", "android.permission.INTERACT_ACROSS_PROFILES", null, "android.permission.LOADER_USAGE_STATS", null, null, null, null, 
        null, null, null };
    sOpRestrictions = new String[] { 
        "no_share_location", "no_share_location", "no_share_location", null, null, null, "no_outgoing_calls", "no_outgoing_calls", null, null, 
        "no_share_location", null, null, null, "no_sms", "no_sms", "no_sms", null, "no_sms", null, 
        "no_sms", "no_sms", "no_sms", null, "no_create_windows", null, "no_camera", "no_record_audio", null, null, 
        null, null, null, "no_adjust_volume", "no_adjust_volume", "no_adjust_volume", "no_adjust_volume", "no_adjust_volume", "no_adjust_volume", "no_adjust_volume", 
        null, "no_share_location", "no_share_location", null, "no_unmute_microphone", "no_create_windows", null, null, "no_wallpaper", null, 
        null, null, null, null, null, null, null, null, null, null, 
        null, null, null, null, "no_adjust_volume", null, null, null, null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        "no_sms", null, null, null, null, null, null, null, null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        null, null, null };
    sOpAllowSystemRestrictionBypass = new RestrictionBypass[] { 
        new RestrictionBypass(true, false), new RestrictionBypass(true, false), null, null, null, null, null, null, null, null, 
        new RestrictionBypass(true, false), null, null, null, null, null, null, null, null, null, 
        null, null, null, null, new RestrictionBypass(true, false), null, null, new RestrictionBypass(false, true), null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        null, null, null, null, null, new RestrictionBypass(true, false), null, null, null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        null, null, null, null, null, null, null, new RestrictionBypass(true, false), null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        null, null, null };
    sOpDefaultMode = new int[] { 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 
        0, 0, 0, 3, getSystemAlertWindowDefault(), 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 3, 0, 0, 1, 1, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 
        0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 
        0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 
        3, 0, 2, 0, 2, 0, 2, 3, 0, 2, 
        0, 3, 3, 3, 1, 3, 1, 3, 0, 2, 
        0, 0, 0 };
    sOpDisableReset = new boolean[] { 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, true, true, true, false, false, true, 
        true, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, true, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, true, false, false, 
        false, false, false, false, false, false, false, false, false, true, 
        false, false, false };
    sOpStrToOp = new HashMap<>();
    sPermToOp = new HashMap<>();
    sBinderThreadCallingUid = new ThreadLocal<>();
    sAppOpsNotedInThisBinderTransaction = new ThreadLocal<>();
    sAppOpsToNote = new byte[103];
    if (sOpToSwitch.length == 103) {
      if (sOpToString.length == 103) {
        if (sOpNames.length == 103) {
          if (sOpPerms.length == 103) {
            if (sOpDefaultMode.length == 103) {
              if (sOpDisableReset.length == 103) {
                if (sOpRestrictions.length == 103) {
                  if (sOpAllowSystemRestrictionBypass.length == 103) {
                    for (null = 0; null < 103; null++) {
                      String[] arrayOfString = sOpToString;
                      if (arrayOfString[null] != null)
                        sOpStrToOp.put(arrayOfString[null], Integer.valueOf(null)); 
                    } 
                    for (int i : RUNTIME_AND_APPOP_PERMISSIONS_OPS) {
                      String[] arrayOfString = sOpPerms;
                      if (arrayOfString[i] != null)
                        sPermToOp.put(arrayOfString[i], Integer.valueOf(i)); 
                    } 
                    sConfig = new MessageSamplingConfig(-1, 0, 0L);
                    return;
                  } 
                  StringBuilder stringBuilder7 = new StringBuilder();
                  stringBuilder7.append("sOpAllowSYstemRestrictionsBypass length ");
                  stringBuilder7.append(sOpRestrictions.length);
                  stringBuilder7.append(" should be ");
                  stringBuilder7.append(103);
                  throw new IllegalStateException(stringBuilder7.toString());
                } 
                StringBuilder stringBuilder6 = new StringBuilder();
                stringBuilder6.append("sOpRestrictions length ");
                stringBuilder6.append(sOpRestrictions.length);
                stringBuilder6.append(" should be ");
                stringBuilder6.append(103);
                throw new IllegalStateException(stringBuilder6.toString());
              } 
              StringBuilder stringBuilder5 = new StringBuilder();
              stringBuilder5.append("sOpDisableReset length ");
              stringBuilder5.append(sOpDisableReset.length);
              stringBuilder5.append(" should be ");
              stringBuilder5.append(103);
              throw new IllegalStateException(stringBuilder5.toString());
            } 
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("sOpDefaultMode length ");
            stringBuilder4.append(sOpDefaultMode.length);
            stringBuilder4.append(" should be ");
            stringBuilder4.append(103);
            throw new IllegalStateException(stringBuilder4.toString());
          } 
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append("sOpPerms length ");
          stringBuilder3.append(sOpPerms.length);
          stringBuilder3.append(" should be ");
          stringBuilder3.append(103);
          throw new IllegalStateException(stringBuilder3.toString());
        } 
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("sOpNames length ");
        stringBuilder2.append(sOpNames.length);
        stringBuilder2.append(" should be ");
        stringBuilder2.append(103);
        throw new IllegalStateException(stringBuilder2.toString());
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("sOpToString length ");
      stringBuilder1.append(sOpToString.length);
      stringBuilder1.append(" should be ");
      stringBuilder1.append(103);
      throw new IllegalStateException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("sOpToSwitch length ");
    stringBuilder.append(sOpToSwitch.length);
    stringBuilder.append(" should be ");
    stringBuilder.append(103);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  AppOpsManager(Context paramContext, IAppOpsService paramIAppOpsService) {
    this.mContext = paramContext;
    this.mService = paramIAppOpsService;
  }
  
  private String buildSecurityExceptionMsg(int paramInt1, int paramInt2, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(" from uid ");
    stringBuilder.append(paramInt2);
    stringBuilder.append(" not allowed to perform ");
    stringBuilder.append(sOpNames[paramInt1]);
    return stringBuilder.toString();
  }
  
  private static LongSparseArray<Object> collectKeys(LongSparseLongArray paramLongSparseLongArray, LongSparseArray<Object> paramLongSparseArray) {
    LongSparseArray<Object> longSparseArray = paramLongSparseArray;
    if (paramLongSparseLongArray != null) {
      LongSparseArray<Object> longSparseArray1 = paramLongSparseArray;
      if (paramLongSparseArray == null)
        longSparseArray1 = new LongSparseArray(); 
      int i = paramLongSparseLongArray.size();
      byte b = 0;
      while (true) {
        longSparseArray = longSparseArray1;
        if (b < i) {
          longSparseArray1.put(paramLongSparseLongArray.keyAt(b), null);
          b++;
          continue;
        } 
        break;
      } 
    } 
    return longSparseArray;
  }
  
  private void collectNoteOpCallsForValidation(int paramInt) {}
  
  private void collectNotedOpForSelf(int paramInt, String paramString) {
    synchronized (sLock) {
      if (sOnOpNotedCallback != null) {
        OnOpNotedCallback onOpNotedCallback = sOnOpNotedCallback;
        SyncNotedAppOp syncNotedAppOp = new SyncNotedAppOp();
        this(paramInt, paramString);
        onOpNotedCallback.onSelfNoted(syncNotedAppOp);
      } 
      sMessageCollector.onSelfNoted(new SyncNotedAppOp(paramInt, paramString));
      return;
    } 
  }
  
  private void collectNotedOpSync(int paramInt, String paramString) {
    ArrayMap<String, long[]> arrayMap1 = sAppOpsNotedInThisBinderTransaction.get();
    ArrayMap<String, long[]> arrayMap2 = arrayMap1;
    if (arrayMap1 == null) {
      arrayMap2 = new ArrayMap(1);
      sAppOpsNotedInThisBinderTransaction.set(arrayMap2);
    } 
    long[] arrayOfLong2 = (long[])arrayMap2.get(paramString);
    long[] arrayOfLong1 = arrayOfLong2;
    if (arrayOfLong2 == null) {
      arrayOfLong1 = new long[2];
      arrayMap2.put(paramString, arrayOfLong1);
    } 
    if (paramInt < 64) {
      arrayOfLong1[0] = 1L << paramInt | arrayOfLong1[0];
    } else {
      arrayOfLong1[1] = 1L << paramInt - 64 | arrayOfLong1[1];
    } 
  }
  
  private static boolean equalsLongSparseLongArray(LongSparseLongArray paramLongSparseLongArray1, LongSparseLongArray paramLongSparseLongArray2) {
    if (paramLongSparseLongArray1 == paramLongSparseLongArray2)
      return true; 
    if (paramLongSparseLongArray1 == null || paramLongSparseLongArray2 == null)
      return false; 
    if (paramLongSparseLongArray1.size() != paramLongSparseLongArray2.size())
      return false; 
    int i = paramLongSparseLongArray1.size();
    for (byte b = 0; b < i; b++) {
      if (paramLongSparseLongArray1.keyAt(b) != paramLongSparseLongArray2.keyAt(b) || paramLongSparseLongArray1.valueAt(b) != paramLongSparseLongArray2.valueAt(b))
        return false; 
    } 
    return true;
  }
  
  public static int extractFlagsFromKey(long paramLong) {
    return (int)(0xFFFFFFFFFFFFFFFFL & paramLong);
  }
  
  public static int extractUidStateFromKey(long paramLong) {
    return (int)(paramLong >> 31L);
  }
  
  public static void finishNotedAppOpsCollection() {
    sBinderThreadCallingUid.remove();
    sAppOpsNotedInThisBinderTransaction.remove();
  }
  
  public static String flagsToString(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    while (paramInt != 0) {
      int i = 1 << Integer.numberOfTrailingZeros(paramInt);
      paramInt &= i;
      if (stringBuilder.length() > 0)
        stringBuilder.append('|'); 
      stringBuilder.append(getFlagName(i));
    } 
    return stringBuilder.toString();
  }
  
  public static IBinder getClientId() {
    // Byte code:
    //   0: ldc android/app/AppOpsManager
    //   2: monitorenter
    //   3: getstatic android/app/AppOpsManager.sClientId : Landroid/os/IBinder;
    //   6: ifnonnull -> 21
    //   9: new android/os/Binder
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic android/app/AppOpsManager.sClientId : Landroid/os/IBinder;
    //   21: getstatic android/app/AppOpsManager.sClientId : Landroid/os/IBinder;
    //   24: astore_0
    //   25: ldc android/app/AppOpsManager
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: astore_0
    //   31: ldc android/app/AppOpsManager
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	30	finally
    //   21	28	30	finally
    //   31	34	30	finally
  }
  
  public static final String getFlagName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 4) ? ((paramInt != 8) ? ((paramInt != 16) ? "unknown" : "upd") : "tpd") : "up") : "tp") : "s";
  }
  
  private static String getFormattedStackTrace() {
    StackTraceElement[] arrayOfStackTraceElement = (new Exception()).getStackTrace();
    int i = 0;
    int j;
    for (j = 0; j < arrayOfStackTraceElement.length && (arrayOfStackTraceElement[j].getClassName().startsWith(AppOpsManager.class.getName()) || arrayOfStackTraceElement[j].getClassName().startsWith(Parcel.class.getName()) || arrayOfStackTraceElement[j].getClassName().contains("$Stub$Proxy") || arrayOfStackTraceElement[j].getClassName().startsWith(DatabaseUtils.class.getName()) || arrayOfStackTraceElement[j].getClassName().startsWith("android.content.ContentProviderProxy") || arrayOfStackTraceElement[j].getClassName().startsWith(ContentResolver.class.getName())); j++)
      i = j; 
    int k = arrayOfStackTraceElement.length - 1;
    for (j = arrayOfStackTraceElement.length - 1; j >= 0 && (arrayOfStackTraceElement[j].getClassName().startsWith(HandlerThread.class.getName()) || arrayOfStackTraceElement[j].getClassName().startsWith(Handler.class.getName()) || arrayOfStackTraceElement[j].getClassName().startsWith(Looper.class.getName()) || arrayOfStackTraceElement[j].getClassName().startsWith(Binder.class.getName()) || arrayOfStackTraceElement[j].getClassName().startsWith(RuntimeInit.class.getName()) || arrayOfStackTraceElement[j].getClassName().startsWith(ZygoteInit.class.getName()) || arrayOfStackTraceElement[j].getClassName().startsWith(ActivityThread.class.getName()) || arrayOfStackTraceElement[j].getClassName().startsWith(Method.class.getName()) || arrayOfStackTraceElement[j].getClassName().startsWith("com.android.server.SystemServer")); j--)
      k = j; 
    StringBuilder stringBuilder = new StringBuilder();
    for (j = i; j <= k; j++) {
      if (j != i)
        stringBuilder.append('\n'); 
      if (stringBuilder.length() + arrayOfStackTraceElement[j].toString().length() > 600)
        break; 
      stringBuilder.append(arrayOfStackTraceElement[j]);
    } 
    return stringBuilder.toString();
  }
  
  private static NoteOpEvent getLastEvent(LongSparseArray<NoteOpEvent> paramLongSparseArray, int paramInt1, int paramInt2, int paramInt3) {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aconst_null
    //   7: astore #4
    //   9: iload_3
    //   10: ifeq -> 145
    //   13: iconst_1
    //   14: iload_3
    //   15: invokestatic numberOfTrailingZeros : (I)I
    //   18: ishl
    //   19: istore #5
    //   21: iload_3
    //   22: iload #5
    //   24: iand
    //   25: istore #6
    //   27: getstatic android/app/AppOpsManager.UID_STATES : [I
    //   30: astore #7
    //   32: aload #7
    //   34: arraylength
    //   35: istore #8
    //   37: iconst_0
    //   38: istore_3
    //   39: iload_3
    //   40: iload #8
    //   42: if_icmpge -> 139
    //   45: aload #7
    //   47: iload_3
    //   48: iaload
    //   49: istore #9
    //   51: iload #9
    //   53: iload_1
    //   54: if_icmplt -> 125
    //   57: iload #9
    //   59: iload_2
    //   60: if_icmple -> 70
    //   63: aload #4
    //   65: astore #10
    //   67: goto -> 129
    //   70: aload_0
    //   71: iload #9
    //   73: iload #5
    //   75: invokestatic makeKey : (II)J
    //   78: invokevirtual get : (J)Ljava/lang/Object;
    //   81: checkcast android/app/AppOpsManager$NoteOpEvent
    //   84: astore #11
    //   86: aload #4
    //   88: ifnull -> 118
    //   91: aload #4
    //   93: astore #10
    //   95: aload #11
    //   97: ifnull -> 129
    //   100: aload #4
    //   102: astore #10
    //   104: aload #11
    //   106: invokevirtual getNoteTime : ()J
    //   109: aload #4
    //   111: invokevirtual getNoteTime : ()J
    //   114: lcmp
    //   115: ifle -> 129
    //   118: aload #11
    //   120: astore #10
    //   122: goto -> 129
    //   125: aload #4
    //   127: astore #10
    //   129: iinc #3, 1
    //   132: aload #10
    //   134: astore #4
    //   136: goto -> 39
    //   139: iload #6
    //   141: istore_3
    //   142: goto -> 9
    //   145: aload #4
    //   147: areturn
  }
  
  private int getNotedOpCollectionMode(int paramInt1, String paramString, int paramInt2) {
    null = paramString;
    if (paramString == null)
      null = "android"; 
    if (sAppOpsToNote[paramInt2] == 0)
      try {
        boolean bool = this.mService.shouldCollectNotes(paramInt2);
        if (bool) {
          sAppOpsToNote[paramInt2] = (byte)2;
        } else {
          sAppOpsToNote[paramInt2] = (byte)1;
        } 
      } catch (RemoteException remoteException) {
        return 0;
      }  
    if (sAppOpsToNote[paramInt2] != 2)
      return 0; 
    synchronized (sLock) {
      if (paramInt1 == Process.myUid() && null.equals(ActivityThread.currentOpPackageName()))
        return 1; 
      null = sBinderThreadCallingUid.get();
      return (null != null && null.intValue() == paramInt1) ? 2 : 3;
    } 
  }
  
  public static int getNumOps() {
    return 103;
  }
  
  @SystemApi
  public static String[] getOpStrs() {
    String[] arrayOfString = sOpToString;
    return Arrays.<String>copyOf(arrayOfString, arrayOfString.length);
  }
  
  private static IAppOpsService getService() {
    synchronized (sLock) {
      if (sService == null)
        sService = IAppOpsService.Stub.asInterface(ServiceManager.getService("appops")); 
      return sService;
    } 
  }
  
  private static int getSystemAlertWindowDefault() {
    Application application = ActivityThread.currentApplication();
    if (application == null)
      return 3; 
    PackageManager packageManager = application.getPackageManager();
    return (ActivityManager.isLowRamDeviceStatic() && !packageManager.hasSystemFeature("android.software.leanback", 0)) ? 1 : 3;
  }
  
  @Deprecated
  public static IBinder getToken(IAppOpsService paramIAppOpsService) {
    return getClientId();
  }
  
  public static String getUidStateName(int paramInt) {
    return (paramInt != 100) ? ((paramInt != 200) ? ((paramInt != 300) ? ((paramInt != 400) ? ((paramInt != 500) ? ((paramInt != 600) ? ((paramInt != 700) ? "unknown" : "cch") : "bg") : "fg") : "fgsvc") : "fgsvcl") : "top") : "pers";
  }
  
  public static String historicalModeToString(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? "UNKNOWN" : "HISTORICAL_MODE_ENABLED_PASSIVE") : "HISTORICAL_MODE_ENABLED_ACTIVE") : "HISTORICAL_MODE_DISABLED";
  }
  
  private static boolean isCollectingStackTraces() {
    return !(sConfig.getSampledOpCode() == -1 && sConfig.getExpirationTimeSinceBootMillis() >= SystemClock.elapsedRealtime());
  }
  
  public static boolean isListeningForOpNoted() {
    return (sOnOpNotedCallback != null || isCollectingStackTraces());
  }
  
  public static String keyToString(long paramLong) {
    int i = extractUidStateFromKey(paramLong);
    int j = extractFlagsFromKey(paramLong);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    stringBuilder.append(getUidStateName(i));
    stringBuilder.append("-");
    stringBuilder.append(flagsToString(j));
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public static int leftCircularDistance(int paramInt1, int paramInt2, int paramInt3) {
    return (paramInt2 + paramInt3 - paramInt1) % paramInt3;
  }
  
  public static long makeKey(int paramInt1, int paramInt2) {
    return paramInt1 << 31L | paramInt2;
  }
  
  public static String modeToName(int paramInt) {
    if (paramInt >= 0) {
      String[] arrayOfString = MODE_NAMES;
      if (paramInt < arrayOfString.length)
        return arrayOfString[paramInt]; 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("mode=");
    stringBuilder.append(paramInt);
    return stringBuilder.toString();
  }
  
  public static RestrictionBypass opAllowSystemBypassRestriction(int paramInt) {
    return sOpAllowSystemRestrictionBypass[paramInt];
  }
  
  public static boolean opAllowsReset(int paramInt) {
    return sOpDisableReset[paramInt] ^ true;
  }
  
  public static int opToDefaultMode(int paramInt) {
    return sOpDefaultMode[paramInt];
  }
  
  @SystemApi
  public static int opToDefaultMode(String paramString) {
    return opToDefaultMode(strOpToOp(paramString));
  }
  
  public static String opToName(int paramInt) {
    String str;
    if (paramInt == -1)
      return "NONE"; 
    String[] arrayOfString = sOpNames;
    if (paramInt < arrayOfString.length) {
      str = arrayOfString[paramInt];
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown(");
      stringBuilder.append(paramInt);
      stringBuilder.append(")");
      str = stringBuilder.toString();
    } 
    return str;
  }
  
  public static String opToPermission(int paramInt) {
    return sOpPerms[paramInt];
  }
  
  @SystemApi
  public static String opToPermission(String paramString) {
    return opToPermission(strOpToOp(paramString));
  }
  
  public static String opToPublicName(int paramInt) {
    return sOpToString[paramInt];
  }
  
  public static String opToRestriction(int paramInt) {
    return sOpRestrictions[paramInt];
  }
  
  public static int opToSwitch(int paramInt) {
    return sOpToSwitch[paramInt];
  }
  
  public static int parseHistoricalMode(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual hashCode : ()I
    //   4: istore_1
    //   5: iload_1
    //   6: ldc_w 155185419
    //   9: if_icmpeq -> 37
    //   12: iload_1
    //   13: ldc_w 885538210
    //   16: if_icmpeq -> 22
    //   19: goto -> 52
    //   22: aload_0
    //   23: ldc_w 'HISTORICAL_MODE_ENABLED_PASSIVE'
    //   26: invokevirtual equals : (Ljava/lang/Object;)Z
    //   29: ifeq -> 19
    //   32: iconst_1
    //   33: istore_1
    //   34: goto -> 54
    //   37: aload_0
    //   38: ldc_w 'HISTORICAL_MODE_ENABLED_ACTIVE'
    //   41: invokevirtual equals : (Ljava/lang/Object;)Z
    //   44: ifeq -> 19
    //   47: iconst_0
    //   48: istore_1
    //   49: goto -> 54
    //   52: iconst_m1
    //   53: istore_1
    //   54: iload_1
    //   55: ifeq -> 67
    //   58: iload_1
    //   59: iconst_1
    //   60: if_icmpeq -> 65
    //   63: iconst_0
    //   64: ireturn
    //   65: iconst_2
    //   66: ireturn
    //   67: iconst_1
    //   68: ireturn
  }
  
  public static PausedNotedAppOpsCollection pauseNotedAppOpsCollection() {
    Integer integer = sBinderThreadCallingUid.get();
    if (integer != null) {
      ArrayMap<String, long[]> arrayMap = sAppOpsNotedInThisBinderTransaction.get();
      sBinderThreadCallingUid.remove();
      sAppOpsNotedInThisBinderTransaction.remove();
      return new PausedNotedAppOpsCollection(integer.intValue(), arrayMap);
    } 
    return null;
  }
  
  public static String permissionToOp(String paramString) {
    Integer integer = sPermToOp.get(paramString);
    return (integer == null) ? null : sOpToString[integer.intValue()];
  }
  
  public static int permissionToOpCode(String paramString) {
    byte b;
    Integer integer = sPermToOp.get(paramString);
    if (integer != null) {
      b = integer.intValue();
    } else {
      b = -1;
    } 
    return b;
  }
  
  public static void prefixParcelWithAppOpsIfNeeded(Parcel paramParcel) {
    ArrayMap arrayMap = sAppOpsNotedInThisBinderTransaction.get();
    if (arrayMap == null)
      return; 
    paramParcel.writeInt(-127);
    int i = arrayMap.size();
    paramParcel.writeInt(i);
    for (byte b = 0; b < i; b++) {
      paramParcel.writeString((String)arrayMap.keyAt(b));
      paramParcel.writeLong(((long[])arrayMap.valueAt(b))[0]);
      paramParcel.writeLong(((long[])arrayMap.valueAt(b))[1]);
    } 
  }
  
  public static void readAndLogNotedAppops(Parcel paramParcel) {
    int i = paramParcel.readInt();
    for (byte b = 0;; b++) {
      if (b < i) {
        String str = paramParcel.readString();
        long[] arrayOfLong = new long[2];
        arrayOfLong[0] = paramParcel.readLong();
        arrayOfLong[1] = paramParcel.readLong();
        if (arrayOfLong[0] != 0L || arrayOfLong[1] != 0L) {
          BitSet bitSet = BitSet.valueOf(arrayOfLong);
          synchronized (sLock) {
            int j;
            for (j = bitSet.nextSetBit(0); j != -1; j = bitSet.nextSetBit(j + 1)) {
              if (sOnOpNotedCallback != null) {
                OnOpNotedCallback onOpNotedCallback = sOnOpNotedCallback;
                SyncNotedAppOp syncNotedAppOp = new SyncNotedAppOp();
                this(j, str);
                onOpNotedCallback.onNoted(syncNotedAppOp);
              } else {
                String str1 = getFormattedStackTrace();
                ArrayList<AsyncNotedAppOp> arrayList = sUnforwardedOps;
                AsyncNotedAppOp asyncNotedAppOp = new AsyncNotedAppOp();
                this(j, Process.myUid(), str, str1, System.currentTimeMillis());
                arrayList.add(asyncNotedAppOp);
                if (sUnforwardedOps.size() > 10)
                  sUnforwardedOps.remove(0); 
              } 
            } 
            for (j = bitSet.nextSetBit(0); j != -1; j = bitSet.nextSetBit(j + 1))
              sMessageCollector.onNoted(new SyncNotedAppOp(j, str)); 
            b++;
          } 
          continue;
        } 
      } else {
        break;
      } 
    } 
  }
  
  private static LongSparseLongArray readLongSparseLongArrayFromParcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    if (i < 0)
      return null; 
    LongSparseLongArray longSparseLongArray = new LongSparseLongArray(i);
    for (byte b = 0; b < i; b++)
      longSparseLongArray.append(paramParcel.readLong(), paramParcel.readLong()); 
    return longSparseLongArray;
  }
  
  public static int resolveFirstUnrestrictedUidState(int paramInt) {
    return 500;
  }
  
  public static int resolveLastRestrictedUidState(int paramInt) {
    return 600;
  }
  
  public static void resumeNotedAppOpsCollection(PausedNotedAppOpsCollection paramPausedNotedAppOpsCollection) {
    if (paramPausedNotedAppOpsCollection != null) {
      sBinderThreadCallingUid.set(Integer.valueOf(paramPausedNotedAppOpsCollection.mUid));
      if (paramPausedNotedAppOpsCollection.mCollectedNotedAppOps != null)
        sAppOpsNotedInThisBinderTransaction.set(paramPausedNotedAppOpsCollection.mCollectedNotedAppOps); 
    } 
  }
  
  public static void startNotedAppOpsCollection(int paramInt) {
    sBinderThreadCallingUid.set(Integer.valueOf(paramInt));
  }
  
  public static int strDebugOpToOp(String paramString) {
    byte b = 0;
    while (true) {
      String[] arrayOfString = sOpNames;
      if (b < arrayOfString.length) {
        if (arrayOfString[b].equals(paramString))
          return b; 
        b++;
        continue;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown operation string: ");
      stringBuilder.append(paramString);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
  }
  
  public static int strOpToOp(String paramString) {
    Integer integer = sOpStrToOp.get(paramString);
    if (integer != null)
      return integer.intValue(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown operation string: ");
    stringBuilder.append(paramString);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static long sumForFlagsInStates(LongSparseLongArray paramLongSparseLongArray, int paramInt1, int paramInt2, int paramInt3) {
    if (paramLongSparseLongArray == null)
      return 0L; 
    long l = 0L;
    while (paramInt3 != 0) {
      int i = 1 << Integer.numberOfTrailingZeros(paramInt3);
      int j = paramInt3 & i;
      int[] arrayOfInt = UID_STATES;
      int k = arrayOfInt.length;
      paramInt3 = 0;
      while (paramInt3 < k) {
        int m = arrayOfInt[paramInt3];
        long l1 = l;
        if (m >= paramInt1)
          if (m > paramInt2) {
            l1 = l;
          } else {
            l1 = l + paramLongSparseLongArray.get(makeKey(m, i));
          }  
        paramInt3++;
        l = l1;
      } 
      paramInt3 = j;
    } 
    return l;
  }
  
  public static String toReceiverId(PendingIntent paramPendingIntent) {
    return paramPendingIntent.getTag("");
  }
  
  public static String toReceiverId(Object paramObject) {
    if (paramObject instanceof PendingIntent)
      return toReceiverId((PendingIntent)paramObject); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramObject.getClass().getName());
    stringBuilder.append("@");
    stringBuilder.append(System.identityHashCode(paramObject));
    return stringBuilder.toString();
  }
  
  public static String uidStateToString(int paramInt) {
    return (paramInt != 100) ? ((paramInt != 200) ? ((paramInt != 300) ? ((paramInt != 400) ? ((paramInt != 500) ? ((paramInt != 600) ? ((paramInt != 700) ? "UNKNOWN" : "UID_STATE_CACHED") : "UID_STATE_BACKGROUND") : "UID_STATE_FOREGROUND") : "UID_STATE_FOREGROUND_SERVICE") : "UID_STATE_FOREGROUND_SERVICE_LOCATION") : "UID_STATE_TOP") : "UID_STATE_PERSISTENT";
  }
  
  private static void writeLongSparseLongArrayToParcel(LongSparseLongArray paramLongSparseLongArray, Parcel paramParcel) {
    if (paramLongSparseLongArray != null) {
      int i = paramLongSparseLongArray.size();
      paramParcel.writeInt(i);
      for (byte b = 0; b < i; b++) {
        paramParcel.writeLong(paramLongSparseLongArray.keyAt(b));
        paramParcel.writeLong(paramLongSparseLongArray.valueAt(b));
      } 
    } else {
      paramParcel.writeInt(-1);
    } 
  }
  
  public void addHistoricalOps(HistoricalOps paramHistoricalOps) {
    try {
      this.mService.addHistoricalOps(paramHistoricalOps);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int checkAudioOp(int paramInt1, int paramInt2, int paramInt3, String paramString) {
    try {
      paramInt2 = this.mService.checkAudioOperation(paramInt1, paramInt2, paramInt3, paramString);
      if (paramInt2 != 2)
        return paramInt2; 
      SecurityException securityException = new SecurityException();
      this(buildSecurityExceptionMsg(paramInt1, paramInt3, paramString));
      throw securityException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int checkAudioOpNoThrow(int paramInt1, int paramInt2, int paramInt3, String paramString) {
    try {
      return this.mService.checkAudioOperation(paramInt1, paramInt2, paramInt3, paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int checkOp(int paramInt1, int paramInt2, String paramString) {
    try {
      int i = this.mService.checkOperation(paramInt1, paramInt2, paramString);
      if (i != 2)
        return i; 
      SecurityException securityException = new SecurityException();
      this(buildSecurityExceptionMsg(paramInt1, paramInt2, paramString));
      throw securityException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public int checkOp(String paramString1, int paramInt, String paramString2) {
    return checkOp(strOpToOp(paramString1), paramInt, paramString2);
  }
  
  public int checkOpNoThrow(int paramInt1, int paramInt2, String paramString) {
    try {
      paramInt1 = this.mService.checkOperation(paramInt1, paramInt2, paramString);
      if (paramInt1 == 4)
        paramInt1 = 0; 
      return paramInt1;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public int checkOpNoThrow(String paramString1, int paramInt, String paramString2) {
    return checkOpNoThrow(strOpToOp(paramString1), paramInt, paramString2);
  }
  
  @Deprecated
  public void checkPackage(int paramInt, String paramString) {
    try {
      if (this.mService.checkPackage(paramInt, paramString) == 0)
        return; 
      SecurityException securityException = new SecurityException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Package ");
      stringBuilder.append(paramString);
      stringBuilder.append(" does not belong to ");
      stringBuilder.append(paramInt);
      this(stringBuilder.toString());
      throw securityException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void clearHistory() {
    try {
      this.mService.clearHistory();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public RuntimeAppOpAccessMessage collectRuntimeAppOpAccessMessage() {
    try {
      return this.mService.collectRuntimeAppOpAccessMessage();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public void finishOp(int paramInt) {
    finishOp(paramInt, Process.myUid(), this.mContext.getOpPackageName(), (String)null);
  }
  
  public void finishOp(int paramInt1, int paramInt2, String paramString) {
    finishOp(paramInt1, paramInt2, paramString, (String)null);
  }
  
  public void finishOp(int paramInt1, int paramInt2, String paramString1, String paramString2) {
    try {
      this.mService.finishOperation(getClientId(), paramInt1, paramInt2, paramString1, paramString2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void finishOp(String paramString1, int paramInt, String paramString2) {
    finishOp(strOpToOp(paramString1), paramInt, paramString2, (String)null);
  }
  
  public void finishOp(String paramString1, int paramInt, String paramString2, String paramString3) {
    finishOp(strOpToOp(paramString1), paramInt, paramString2, paramString3);
  }
  
  @SystemApi
  public void getHistoricalOps(HistoricalOpsRequest paramHistoricalOpsRequest, Executor paramExecutor, Consumer<HistoricalOps> paramConsumer) {
    Objects.requireNonNull(paramExecutor, "executor cannot be null");
    Objects.requireNonNull(paramConsumer, "callback cannot be null");
    try {
      IAppOpsService iAppOpsService = this.mService;
      int i = paramHistoricalOpsRequest.mUid;
      String str1 = paramHistoricalOpsRequest.mPackageName;
      String str2 = paramHistoricalOpsRequest.mAttributionTag;
      List list = paramHistoricalOpsRequest.mOpNames;
      int j = paramHistoricalOpsRequest.mFilter;
      long l1 = paramHistoricalOpsRequest.mBeginTimeMillis;
      long l2 = paramHistoricalOpsRequest.mEndTimeMillis;
      int k = paramHistoricalOpsRequest.mFlags;
      RemoteCallback remoteCallback = new RemoteCallback();
      _$$Lambda$AppOpsManager$4Zbi7CSLEt0nvOmfJBVYtJkauTQ _$$Lambda$AppOpsManager$4Zbi7CSLEt0nvOmfJBVYtJkauTQ = new _$$Lambda$AppOpsManager$4Zbi7CSLEt0nvOmfJBVYtJkauTQ();
      this(paramExecutor, paramConsumer);
      this(_$$Lambda$AppOpsManager$4Zbi7CSLEt0nvOmfJBVYtJkauTQ);
      iAppOpsService.getHistoricalOps(i, str1, str2, list, j, l1, l2, k, remoteCallback);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void getHistoricalOpsFromDiskRaw(HistoricalOpsRequest paramHistoricalOpsRequest, Executor paramExecutor, Consumer<HistoricalOps> paramConsumer) {
    Objects.requireNonNull(paramExecutor, "executor cannot be null");
    Objects.requireNonNull(paramConsumer, "callback cannot be null");
    try {
      IAppOpsService iAppOpsService = this.mService;
      int i = paramHistoricalOpsRequest.mUid;
      String str1 = paramHistoricalOpsRequest.mPackageName;
      String str2 = paramHistoricalOpsRequest.mAttributionTag;
      List list = paramHistoricalOpsRequest.mOpNames;
      int j = paramHistoricalOpsRequest.mFilter;
      long l1 = paramHistoricalOpsRequest.mBeginTimeMillis;
      long l2 = paramHistoricalOpsRequest.mEndTimeMillis;
      int k = paramHistoricalOpsRequest.mFlags;
      RemoteCallback remoteCallback = new RemoteCallback();
      _$$Lambda$AppOpsManager$5k42P8tID8pwpGFZvo7VQyru20E _$$Lambda$AppOpsManager$5k42P8tID8pwpGFZvo7VQyru20E = new _$$Lambda$AppOpsManager$5k42P8tID8pwpGFZvo7VQyru20E();
      this(paramExecutor, paramConsumer);
      this(_$$Lambda$AppOpsManager$5k42P8tID8pwpGFZvo7VQyru20E);
      iAppOpsService.getHistoricalOpsFromDiskRaw(i, str1, str2, list, j, l1, l2, k, remoteCallback);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  @Deprecated
  public List<PackageOps> getOpsForPackage(int paramInt, String paramString, int[] paramArrayOfint) {
    try {
      return this.mService.getOpsForPackage(paramInt, paramString, paramArrayOfint);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public List<PackageOps> getOpsForPackage(int paramInt, String paramString, String... paramVarArgs) {
    int[] arrayOfInt = null;
    if (paramVarArgs != null) {
      int[] arrayOfInt1 = new int[paramVarArgs.length];
      byte b = 0;
      while (true) {
        arrayOfInt = arrayOfInt1;
        if (b < paramVarArgs.length) {
          arrayOfInt1[b] = strOpToOp(paramVarArgs[b]);
          b++;
          continue;
        } 
        break;
      } 
    } 
    try {
      List<?> list = this.mService.getOpsForPackage(paramInt, paramString, arrayOfInt);
      return (List)((list == null) ? Collections.emptyList() : list);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<PackageOps> getPackagesForOps(int[] paramArrayOfint) {
    try {
      return this.mService.getPackagesForOps(paramArrayOfint);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public List<PackageOps> getPackagesForOps(String[] paramArrayOfString) {
    int i = paramArrayOfString.length;
    int[] arrayOfInt = new int[i];
    for (byte b = 0; b < i; b++)
      arrayOfInt[b] = ((Integer)sOpStrToOp.get(paramArrayOfString[b])).intValue(); 
    List<PackageOps> list = getPackagesForOps(arrayOfInt);
    if (list == null)
      list = Collections.emptyList(); 
    return list;
  }
  
  public boolean isOpActive(String paramString1, int paramInt, String paramString2) {
    return isOperationActive(strOpToOp(paramString1), paramInt, paramString2);
  }
  
  public boolean isOperationActive(int paramInt1, int paramInt2, String paramString) {
    try {
      return this.mService.isOperationActive(paramInt1, paramInt2, paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public int noteOp(int paramInt) {
    return noteOp(paramInt, Process.myUid(), this.mContext.getOpPackageName(), (String)null, (String)null);
  }
  
  @Deprecated
  public int noteOp(int paramInt1, int paramInt2, String paramString) {
    return noteOp(paramInt1, paramInt2, paramString, (String)null, (String)null);
  }
  
  public int noteOp(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3) {
    int i = noteOpNoThrow(paramInt1, paramInt2, paramString1, paramString2, paramString3);
    if (i != 2)
      return i; 
    throw new SecurityException(buildSecurityExceptionMsg(paramInt1, paramInt2, paramString1));
  }
  
  @Deprecated
  public int noteOp(String paramString1, int paramInt, String paramString2) {
    return noteOp(paramString1, paramInt, paramString2, (String)null, (String)null);
  }
  
  public int noteOp(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4) {
    return noteOp(strOpToOp(paramString1), paramInt, paramString2, paramString3, paramString4);
  }
  
  @Deprecated
  public int noteOpNoThrow(int paramInt1, int paramInt2, String paramString) {
    return noteOpNoThrow(paramInt1, paramInt2, paramString, (String)null, (String)null);
  }
  
  public int noteOpNoThrow(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3) {
    try {
      collectNoteOpCallsForValidation(paramInt1);
      try {
        boolean bool;
        int i = getNotedOpCollectionMode(paramInt2, paramString1, paramInt1);
        if (Process.myUid() == 1000) {
          bool = true;
        } else {
          bool = false;
        } 
        if (i == 3 && paramString3 == null) {
          paramString3 = getFormattedStackTrace();
          bool = true;
        } 
        try {
          boolean bool1;
          IAppOpsService iAppOpsService = this.mService;
          if (i == 3) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          paramInt2 = iAppOpsService.noteOperation(paramInt1, paramInt2, paramString1, paramString2, bool1, paramString3, bool);
          if (paramInt2 == 0)
            if (i == 1) {
              collectNotedOpForSelf(paramInt1, paramString2);
            } else if (i == 2) {
              collectNotedOpSync(paramInt1, paramString2);
            }  
          return paramInt2;
        } catch (RemoteException null) {}
      } catch (RemoteException null) {}
    } catch (RemoteException remoteException) {}
    throw remoteException.rethrowFromSystemServer();
  }
  
  @Deprecated
  public int noteOpNoThrow(String paramString1, int paramInt, String paramString2) {
    return noteOpNoThrow(paramString1, paramInt, paramString2, (String)null, (String)null);
  }
  
  public int noteOpNoThrow(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4) {
    return noteOpNoThrow(strOpToOp(paramString1), paramInt, paramString2, paramString3, paramString4);
  }
  
  @Deprecated
  public int noteProxyOp(int paramInt, String paramString) {
    return noteProxyOp(paramInt, paramString, Binder.getCallingUid(), (String)null, (String)null);
  }
  
  public int noteProxyOp(int paramInt1, String paramString1, int paramInt2, String paramString2, String paramString3) {
    int i = noteProxyOpNoThrow(paramInt1, paramString1, paramInt2, paramString2, paramString3);
    if (i != 2)
      return i; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Proxy package ");
    stringBuilder.append(this.mContext.getOpPackageName());
    stringBuilder.append(" from uid ");
    stringBuilder.append(Process.myUid());
    stringBuilder.append(" or calling package ");
    stringBuilder.append(paramString1);
    stringBuilder.append(" from uid ");
    stringBuilder.append(paramInt2);
    stringBuilder.append(" not allowed to perform ");
    stringBuilder.append(sOpNames[paramInt1]);
    throw new SecurityException(stringBuilder.toString());
  }
  
  @Deprecated
  public int noteProxyOp(String paramString1, String paramString2) {
    return noteProxyOp(paramString1, paramString2, Binder.getCallingUid(), (String)null, (String)null);
  }
  
  public int noteProxyOp(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4) {
    return noteProxyOp(strOpToOp(paramString1), paramString2, paramInt, paramString3, paramString4);
  }
  
  public int noteProxyOpNoThrow(int paramInt1, String paramString1, int paramInt2, String paramString2, String paramString3) {
    int i = Process.myUid();
    try {
      boolean bool;
      collectNoteOpCallsForValidation(paramInt1);
      int j = getNotedOpCollectionMode(paramInt2, paramString1, paramInt1);
      if (i == 1000) {
        bool = true;
      } else {
        bool = false;
      } 
      if (j == 3 && paramString3 == null)
        try {
          paramString3 = getFormattedStackTrace();
          bool = true;
        } catch (RemoteException null) {} 
      try {
        boolean bool1;
        IAppOpsService iAppOpsService = this.mService;
        String str1 = this.mContext.getOpPackageName();
        String str2 = this.mContext.getAttributionTag();
        if (j == 3) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        try {
          paramInt2 = iAppOpsService.noteProxyOperation(paramInt1, paramInt2, (String)remoteException, paramString2, i, str1, str2, bool1, paramString3, bool);
          if (paramInt2 == 0)
            if (j == 1) {
              try {
                collectNotedOpForSelf(paramInt1, paramString2);
              } catch (RemoteException remoteException1) {}
            } else if (j == 2) {
              Context context = this.mContext;
              try {
                if (context.checkPermission("android.permission.UPDATE_APP_OPS_STATS", -1, i) == 0)
                  collectNotedOpSync(paramInt1, paramString2); 
              } catch (RemoteException remoteException1) {}
            }  
          return paramInt2;
        } catch (RemoteException remoteException1) {}
      } catch (RemoteException remoteException1) {}
    } catch (RemoteException remoteException) {}
    throw remoteException.rethrowFromSystemServer();
  }
  
  @Deprecated
  public int noteProxyOpNoThrow(String paramString1, String paramString2) {
    return noteProxyOpNoThrow(paramString1, paramString2, Binder.getCallingUid(), (String)null, (String)null);
  }
  
  @Deprecated
  public int noteProxyOpNoThrow(String paramString1, String paramString2, int paramInt) {
    return noteProxyOpNoThrow(paramString1, paramString2, paramInt, (String)null, (String)null);
  }
  
  public int noteProxyOpNoThrow(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4) {
    return noteProxyOpNoThrow(strOpToOp(paramString1), paramString2, paramInt, paramString3, paramString4);
  }
  
  public void offsetHistory(long paramLong) {
    try {
      this.mService.offsetHistory(paramLong);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void rebootHistory(long paramLong) {
    try {
      this.mService.rebootHistory(paramLong);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void reloadNonHistoricalState() {
    try {
      this.mService.reloadNonHistoricalState();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void resetAllModes() {
    try {
      this.mService.resetAllModes(this.mContext.getUserId(), null);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void resetHistoryParameters() {
    try {
      this.mService.resetHistoryParameters();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setHistoryParameters(int paramInt1, long paramLong, int paramInt2) {
    try {
      this.mService.setHistoryParameters(paramInt1, paramLong, paramInt2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setMode(int paramInt1, int paramInt2, String paramString, int paramInt3) {
    try {
      this.mService.setMode(paramInt1, paramInt2, paramString, paramInt3);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void setMode(String paramString1, int paramInt1, String paramString2, int paramInt2) {
    try {
      this.mService.setMode(strOpToOp(paramString1), paramInt1, paramString2, paramInt2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  @Deprecated
  public void setNotedAppOpsCollector(AppOpsCollector paramAppOpsCollector) {
    // Byte code:
    //   0: getstatic android/app/AppOpsManager.sLock : Ljava/lang/Object;
    //   3: astore_2
    //   4: aload_2
    //   5: monitorenter
    //   6: aload_1
    //   7: ifnull -> 42
    //   10: invokestatic isListeningForOpNoted : ()Z
    //   13: ifeq -> 22
    //   16: aload_0
    //   17: aconst_null
    //   18: aconst_null
    //   19: invokevirtual setOnOpNotedCallback : (Ljava/util/concurrent/Executor;Landroid/app/AppOpsManager$OnOpNotedCallback;)V
    //   22: new android/os/HandlerExecutor
    //   25: astore_3
    //   26: aload_3
    //   27: invokestatic getMain : ()Landroid/os/Handler;
    //   30: invokespecial <init> : (Landroid/os/Handler;)V
    //   33: aload_0
    //   34: aload_3
    //   35: aload_1
    //   36: invokevirtual setOnOpNotedCallback : (Ljava/util/concurrent/Executor;Landroid/app/AppOpsManager$OnOpNotedCallback;)V
    //   39: goto -> 54
    //   42: getstatic android/app/AppOpsManager.sOnOpNotedCallback : Landroid/app/AppOpsManager$OnOpNotedCallback;
    //   45: ifnull -> 54
    //   48: aload_0
    //   49: aconst_null
    //   50: aconst_null
    //   51: invokevirtual setOnOpNotedCallback : (Ljava/util/concurrent/Executor;Landroid/app/AppOpsManager$OnOpNotedCallback;)V
    //   54: aload_2
    //   55: monitorexit
    //   56: return
    //   57: astore_1
    //   58: aload_2
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Exception table:
    //   from	to	target	type
    //   10	22	57	finally
    //   22	39	57	finally
    //   42	54	57	finally
    //   54	56	57	finally
    //   58	60	57	finally
  }
  
  public void setOnOpNotedCallback(Executor paramExecutor, OnOpNotedCallback paramOnOpNotedCallback) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore #4
    //   5: aload_2
    //   6: ifnonnull -> 15
    //   9: iconst_1
    //   10: istore #5
    //   12: goto -> 18
    //   15: iconst_0
    //   16: istore #5
    //   18: aload_1
    //   19: ifnonnull -> 28
    //   22: iconst_1
    //   23: istore #6
    //   25: goto -> 31
    //   28: iconst_0
    //   29: istore #6
    //   31: iload #5
    //   33: iload #6
    //   35: if_icmpne -> 44
    //   38: iconst_1
    //   39: istore #7
    //   41: goto -> 47
    //   44: iconst_0
    //   45: istore #7
    //   47: iload #7
    //   49: invokestatic checkState : (Z)V
    //   52: getstatic android/app/AppOpsManager.sLock : Ljava/lang/Object;
    //   55: astore #8
    //   57: aload #8
    //   59: monitorenter
    //   60: aload_2
    //   61: ifnonnull -> 123
    //   64: iload #4
    //   66: istore #7
    //   68: getstatic android/app/AppOpsManager.sOnOpNotedCallback : Landroid/app/AppOpsManager$OnOpNotedCallback;
    //   71: ifnull -> 77
    //   74: iconst_1
    //   75: istore #7
    //   77: iload #7
    //   79: ldc_w 'No callback is currently registered'
    //   82: invokestatic checkState : (ZLjava/lang/String;)V
    //   85: aload_0
    //   86: getfield mService : Lcom/android/internal/app/IAppOpsService;
    //   89: aload_0
    //   90: getfield mContext : Landroid/content/Context;
    //   93: invokevirtual getPackageName : ()Ljava/lang/String;
    //   96: getstatic android/app/AppOpsManager.sOnOpNotedCallback : Landroid/app/AppOpsManager$OnOpNotedCallback;
    //   99: invokestatic access$6400 : (Landroid/app/AppOpsManager$OnOpNotedCallback;)Lcom/android/internal/app/IAppOpsAsyncNotedCallback;
    //   102: invokeinterface stopWatchingAsyncNoted : (Ljava/lang/String;Lcom/android/internal/app/IAppOpsAsyncNotedCallback;)V
    //   107: goto -> 116
    //   110: astore_1
    //   111: aload_1
    //   112: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   115: pop
    //   116: aconst_null
    //   117: putstatic android/app/AppOpsManager.sOnOpNotedCallback : Landroid/app/AppOpsManager$OnOpNotedCallback;
    //   120: goto -> 357
    //   123: iload_3
    //   124: istore #7
    //   126: getstatic android/app/AppOpsManager.sOnOpNotedCallback : Landroid/app/AppOpsManager$OnOpNotedCallback;
    //   129: ifnonnull -> 135
    //   132: iconst_1
    //   133: istore #7
    //   135: iload #7
    //   137: ldc_w 'Another callback is already registered'
    //   140: invokestatic checkState : (ZLjava/lang/String;)V
    //   143: aload_2
    //   144: aload_1
    //   145: invokestatic access$6502 : (Landroid/app/AppOpsManager$OnOpNotedCallback;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/Executor;
    //   148: pop
    //   149: aload_2
    //   150: putstatic android/app/AppOpsManager.sOnOpNotedCallback : Landroid/app/AppOpsManager$OnOpNotedCallback;
    //   153: aconst_null
    //   154: astore_1
    //   155: aload_0
    //   156: getfield mService : Lcom/android/internal/app/IAppOpsService;
    //   159: aload_0
    //   160: getfield mContext : Landroid/content/Context;
    //   163: invokevirtual getPackageName : ()Ljava/lang/String;
    //   166: getstatic android/app/AppOpsManager.sOnOpNotedCallback : Landroid/app/AppOpsManager$OnOpNotedCallback;
    //   169: invokestatic access$6400 : (Landroid/app/AppOpsManager$OnOpNotedCallback;)Lcom/android/internal/app/IAppOpsAsyncNotedCallback;
    //   172: invokeinterface startWatchingAsyncNoted : (Ljava/lang/String;Lcom/android/internal/app/IAppOpsAsyncNotedCallback;)V
    //   177: aload_0
    //   178: getfield mService : Lcom/android/internal/app/IAppOpsService;
    //   181: aload_0
    //   182: getfield mContext : Landroid/content/Context;
    //   185: invokevirtual getPackageName : ()Ljava/lang/String;
    //   188: invokeinterface extractAsyncOps : (Ljava/lang/String;)Ljava/util/List;
    //   193: astore_2
    //   194: aload_2
    //   195: astore_1
    //   196: goto -> 205
    //   199: astore_2
    //   200: aload_2
    //   201: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   204: pop
    //   205: aload_1
    //   206: ifnull -> 279
    //   209: aload_1
    //   210: invokeinterface size : ()I
    //   215: istore #6
    //   217: iconst_0
    //   218: istore #5
    //   220: iload #5
    //   222: iload #6
    //   224: if_icmpge -> 279
    //   227: aload_1
    //   228: iload #5
    //   230: invokeinterface get : (I)Ljava/lang/Object;
    //   235: checkcast android/app/AsyncNotedAppOp
    //   238: astore_2
    //   239: getstatic android/app/AppOpsManager.sOnOpNotedCallback : Landroid/app/AppOpsManager$OnOpNotedCallback;
    //   242: ifnull -> 273
    //   245: getstatic android/app/AppOpsManager.sOnOpNotedCallback : Landroid/app/AppOpsManager$OnOpNotedCallback;
    //   248: invokevirtual getAsyncNotedExecutor : ()Ljava/util/concurrent/Executor;
    //   251: astore #9
    //   253: new android/app/_$$Lambda$AppOpsManager$PD824BJSYMsBzJsejbPpl76LS7s
    //   256: astore #10
    //   258: aload #10
    //   260: aload_2
    //   261: invokespecial <init> : (Landroid/app/AsyncNotedAppOp;)V
    //   264: aload #9
    //   266: aload #10
    //   268: invokeinterface execute : (Ljava/lang/Runnable;)V
    //   273: iinc #5, 1
    //   276: goto -> 220
    //   279: aload_0
    //   280: monitorenter
    //   281: getstatic android/app/AppOpsManager.sUnforwardedOps : Ljava/util/ArrayList;
    //   284: invokevirtual size : ()I
    //   287: istore #6
    //   289: iconst_0
    //   290: istore #5
    //   292: iload #5
    //   294: iload #6
    //   296: if_icmpge -> 349
    //   299: getstatic android/app/AppOpsManager.sUnforwardedOps : Ljava/util/ArrayList;
    //   302: iload #5
    //   304: invokevirtual get : (I)Ljava/lang/Object;
    //   307: checkcast android/app/AsyncNotedAppOp
    //   310: astore_1
    //   311: getstatic android/app/AppOpsManager.sOnOpNotedCallback : Landroid/app/AppOpsManager$OnOpNotedCallback;
    //   314: ifnull -> 343
    //   317: getstatic android/app/AppOpsManager.sOnOpNotedCallback : Landroid/app/AppOpsManager$OnOpNotedCallback;
    //   320: invokevirtual getAsyncNotedExecutor : ()Ljava/util/concurrent/Executor;
    //   323: astore_2
    //   324: new android/app/_$$Lambda$AppOpsManager$znmOchbf1jfrWSIXDpZNTtYuDQY
    //   327: astore #10
    //   329: aload #10
    //   331: aload_1
    //   332: invokespecial <init> : (Landroid/app/AsyncNotedAppOp;)V
    //   335: aload_2
    //   336: aload #10
    //   338: invokeinterface execute : (Ljava/lang/Runnable;)V
    //   343: iinc #5, 1
    //   346: goto -> 292
    //   349: getstatic android/app/AppOpsManager.sUnforwardedOps : Ljava/util/ArrayList;
    //   352: invokevirtual clear : ()V
    //   355: aload_0
    //   356: monitorexit
    //   357: aload #8
    //   359: monitorexit
    //   360: return
    //   361: astore_1
    //   362: aload_0
    //   363: monitorexit
    //   364: aload_1
    //   365: athrow
    //   366: astore_1
    //   367: aload #8
    //   369: monitorexit
    //   370: aload_1
    //   371: athrow
    // Exception table:
    //   from	to	target	type
    //   68	74	366	finally
    //   77	85	366	finally
    //   85	107	110	android/os/RemoteException
    //   85	107	366	finally
    //   111	116	366	finally
    //   116	120	366	finally
    //   126	132	366	finally
    //   135	153	366	finally
    //   155	194	199	android/os/RemoteException
    //   155	194	366	finally
    //   200	205	366	finally
    //   209	217	366	finally
    //   227	273	366	finally
    //   279	281	366	finally
    //   281	289	361	finally
    //   299	343	361	finally
    //   349	357	361	finally
    //   357	360	366	finally
    //   362	364	361	finally
    //   364	366	366	finally
    //   367	370	366	finally
  }
  
  public void setRestriction(int paramInt1, int paramInt2, int paramInt3, String[] paramArrayOfString) {
    try {
      int i = Binder.getCallingUid();
      this.mService.setAudioRestriction(paramInt1, paramInt2, i, paramInt3, paramArrayOfString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setUidMode(int paramInt1, int paramInt2, int paramInt3) {
    try {
      this.mService.setUidMode(paramInt1, paramInt2, paramInt3);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void setUidMode(String paramString, int paramInt1, int paramInt2) {
    try {
      this.mService.setUidMode(strOpToOp(paramString), paramInt1, paramInt2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setUserRestriction(int paramInt, boolean paramBoolean, IBinder paramIBinder) {
    setUserRestriction(paramInt, paramBoolean, paramIBinder, null);
  }
  
  public void setUserRestriction(int paramInt, boolean paramBoolean, IBinder paramIBinder, String[] paramArrayOfString) {
    setUserRestrictionForUser(paramInt, paramBoolean, paramIBinder, paramArrayOfString, this.mContext.getUserId());
  }
  
  public void setUserRestrictionForUser(int paramInt1, boolean paramBoolean, IBinder paramIBinder, String[] paramArrayOfString, int paramInt2) {
    try {
      this.mService.setUserRestriction(paramInt1, paramBoolean, paramIBinder, paramInt2, paramArrayOfString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public int startOp(int paramInt) {
    return startOp(paramInt, Process.myUid(), this.mContext.getOpPackageName(), false, null, null);
  }
  
  @Deprecated
  public int startOp(int paramInt1, int paramInt2, String paramString) {
    return startOp(paramInt1, paramInt2, paramString, false, null, null);
  }
  
  @Deprecated
  public int startOp(int paramInt1, int paramInt2, String paramString, boolean paramBoolean) {
    return startOp(paramInt1, paramInt2, paramString, paramBoolean, null, null);
  }
  
  public int startOp(int paramInt1, int paramInt2, String paramString1, boolean paramBoolean, String paramString2, String paramString3) {
    int i = startOpNoThrow(paramInt1, paramInt2, paramString1, paramBoolean, paramString2, paramString3);
    if (i != 2)
      return i; 
    throw new SecurityException(buildSecurityExceptionMsg(paramInt1, paramInt2, paramString1));
  }
  
  @Deprecated
  public int startOp(String paramString1, int paramInt, String paramString2) {
    return startOp(paramString1, paramInt, paramString2, null, null);
  }
  
  public int startOp(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4) {
    return startOp(strOpToOp(paramString1), paramInt, paramString2, false, paramString3, paramString4);
  }
  
  @Deprecated
  public int startOpNoThrow(int paramInt1, int paramInt2, String paramString) {
    return startOpNoThrow(paramInt1, paramInt2, paramString, false, null, null);
  }
  
  @Deprecated
  public int startOpNoThrow(int paramInt1, int paramInt2, String paramString, boolean paramBoolean) {
    return startOpNoThrow(paramInt1, paramInt2, paramString, paramBoolean, null, null);
  }
  
  public int startOpNoThrow(int paramInt1, int paramInt2, String paramString1, boolean paramBoolean, String paramString2, String paramString3) {
    try {
      collectNoteOpCallsForValidation(paramInt1);
      try {
        boolean bool;
        int i = getNotedOpCollectionMode(paramInt2, paramString1, paramInt1);
        if (Process.myUid() == 1000) {
          bool = true;
        } else {
          bool = false;
        } 
        if (i == 3 && paramString3 == null) {
          paramString3 = getFormattedStackTrace();
          bool = true;
        } 
        try {
          boolean bool1;
          IAppOpsService iAppOpsService = this.mService;
          IBinder iBinder = getClientId();
          if (i == 3) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          paramInt2 = iAppOpsService.startOperation(iBinder, paramInt1, paramInt2, paramString1, paramString2, paramBoolean, bool1, paramString3, bool);
          if (paramInt2 == 0)
            if (i == 1) {
              collectNotedOpForSelf(paramInt1, paramString2);
            } else if (i == 2) {
              collectNotedOpSync(paramInt1, paramString2);
            }  
          return paramInt2;
        } catch (RemoteException null) {}
      } catch (RemoteException null) {}
    } catch (RemoteException remoteException) {}
    throw remoteException.rethrowFromSystemServer();
  }
  
  @Deprecated
  public int startOpNoThrow(String paramString1, int paramInt, String paramString2) {
    return startOpNoThrow(paramString1, paramInt, paramString2, null, null);
  }
  
  public int startOpNoThrow(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4) {
    return startOpNoThrow(strOpToOp(paramString1), paramInt, paramString2, false, paramString3, paramString4);
  }
  
  @Deprecated
  public void startWatchingActive(int[] paramArrayOfint, OnOpActiveChangedListener paramOnOpActiveChangedListener) {
    String[] arrayOfString = new String[paramArrayOfint.length];
    for (byte b = 0; b < paramArrayOfint.length; b++)
      arrayOfString[b] = opToPublicName(paramArrayOfint[b]); 
    startWatchingActive(arrayOfString, this.mContext.getMainExecutor(), paramOnOpActiveChangedListener);
  }
  
  public void startWatchingActive(String[] paramArrayOfString, Executor paramExecutor, OnOpActiveChangedListener paramOnOpActiveChangedListener) {
    Objects.requireNonNull(paramArrayOfString);
    Objects.requireNonNull(paramExecutor);
    Objects.requireNonNull(paramOnOpActiveChangedListener);
    synchronized (this.mActiveWatchers) {
      if ((IAppOpsActiveCallback)this.mActiveWatchers.get(paramOnOpActiveChangedListener) != null)
        return; 
      IAppOpsActiveCallback.Stub stub = new IAppOpsActiveCallback.Stub() {
          public void opActiveChanged(int param1Int1, int param1Int2, String param1String, boolean param1Boolean) {
            executor.execute(new _$$Lambda$AppOpsManager$3$aT8CbzI8Vm3cKKLkTbEyDBWuFQI(callback, param1Int1, param1Int2, param1String, param1Boolean));
          }
        };
      super(this, paramExecutor, paramOnOpActiveChangedListener);
      this.mActiveWatchers.put(paramOnOpActiveChangedListener, stub);
      int[] arrayOfInt = new int[paramArrayOfString.length];
      for (byte b = 0; b < paramArrayOfString.length; b++)
        arrayOfInt[b] = strOpToOp(paramArrayOfString[b]); 
      try {
        this.mService.startWatchingActive(arrayOfInt, (IAppOpsActiveCallback)stub);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  public void startWatchingMode(int paramInt1, String paramString, int paramInt2, OnOpChangedListener paramOnOpChangedListener) {
    synchronized (this.mModeWatchers) {
      IAppOpsCallback.Stub stub;
      IAppOpsCallback iAppOpsCallback1 = (IAppOpsCallback)this.mModeWatchers.get(paramOnOpChangedListener);
      IAppOpsCallback iAppOpsCallback2 = iAppOpsCallback1;
      if (iAppOpsCallback1 == null) {
        stub = new IAppOpsCallback.Stub() {
            public void opChanged(int param1Int1, int param1Int2, String param1String) {
              AppOpsManager.OnOpChangedListener onOpChangedListener = callback;
              if (onOpChangedListener instanceof AppOpsManager.OnOpChangedInternalListener)
                ((AppOpsManager.OnOpChangedInternalListener)onOpChangedListener).onOpChanged(param1Int1, param1String); 
              if (AppOpsManager.sOpToString[param1Int1] != null)
                callback.onOpChanged(AppOpsManager.sOpToString[param1Int1], param1String); 
            }
          };
        super(this, paramOnOpChangedListener);
        this.mModeWatchers.put(paramOnOpChangedListener, stub);
      } 
      boolean bool = Compatibility.isChangeEnabled(148180766L);
      int i = paramInt2;
      if (!bool)
        i = paramInt2 | 0x2; 
      try {
        this.mService.startWatchingModeWithFlags(paramInt1, paramString, i, (IAppOpsCallback)stub);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  public void startWatchingMode(int paramInt, String paramString, OnOpChangedListener paramOnOpChangedListener) {
    startWatchingMode(paramInt, paramString, 0, paramOnOpChangedListener);
  }
  
  public void startWatchingMode(String paramString1, String paramString2, int paramInt, OnOpChangedListener paramOnOpChangedListener) {
    startWatchingMode(strOpToOp(paramString1), paramString2, paramInt, paramOnOpChangedListener);
  }
  
  public void startWatchingMode(String paramString1, String paramString2, OnOpChangedListener paramOnOpChangedListener) {
    startWatchingMode(strOpToOp(paramString1), paramString2, paramOnOpChangedListener);
  }
  
  public void startWatchingNoted(int[] paramArrayOfint, OnOpNotedListener paramOnOpNotedListener) {
    synchronized (this.mNotedWatchers) {
      if ((IAppOpsNotedCallback)this.mNotedWatchers.get(paramOnOpNotedListener) != null)
        return; 
      IAppOpsNotedCallback.Stub stub = new IAppOpsNotedCallback.Stub() {
          public void opNoted(int param1Int1, int param1Int2, String param1String, int param1Int3) {
            callback.onOpNoted(param1Int1, param1Int2, param1String, param1Int3);
          }
        };
      super(this, paramOnOpNotedListener);
      this.mNotedWatchers.put(paramOnOpNotedListener, stub);
      try {
        this.mService.startWatchingNoted(paramArrayOfint, (IAppOpsNotedCallback)stub);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  public void startWatchingStarted(int[] paramArrayOfint, OnOpStartedListener paramOnOpStartedListener) {
    synchronized (this.mStartedWatchers) {
      if (this.mStartedWatchers.containsKey(paramOnOpStartedListener))
        return; 
      IAppOpsStartedCallback.Stub stub = new IAppOpsStartedCallback.Stub() {
          public void opStarted(int param1Int1, int param1Int2, String param1String, int param1Int3) {
            callback.onOpStarted(param1Int1, param1Int2, param1String, param1Int3);
          }
        };
      super(this, paramOnOpStartedListener);
      this.mStartedWatchers.put(paramOnOpStartedListener, stub);
      try {
        this.mService.startWatchingStarted(paramArrayOfint, (IAppOpsStartedCallback)stub);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  public void stopWatchingActive(OnOpActiveChangedListener paramOnOpActiveChangedListener) {
    synchronized (this.mActiveWatchers) {
      IAppOpsActiveCallback iAppOpsActiveCallback = (IAppOpsActiveCallback)this.mActiveWatchers.remove(paramOnOpActiveChangedListener);
      if (iAppOpsActiveCallback != null)
        try {
          this.mService.stopWatchingActive(iAppOpsActiveCallback);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return;
    } 
  }
  
  public void stopWatchingMode(OnOpChangedListener paramOnOpChangedListener) {
    synchronized (this.mModeWatchers) {
      IAppOpsCallback iAppOpsCallback = (IAppOpsCallback)this.mModeWatchers.remove(paramOnOpChangedListener);
      if (iAppOpsCallback != null)
        try {
          this.mService.stopWatchingMode(iAppOpsCallback);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return;
    } 
  }
  
  public void stopWatchingNoted(OnOpNotedListener paramOnOpNotedListener) {
    synchronized (this.mNotedWatchers) {
      IAppOpsNotedCallback iAppOpsNotedCallback = (IAppOpsNotedCallback)this.mNotedWatchers.remove(paramOnOpNotedListener);
      if (iAppOpsNotedCallback != null)
        try {
          this.mService.stopWatchingNoted(iAppOpsNotedCallback);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return;
    } 
  }
  
  public void stopWatchingStarted(OnOpStartedListener paramOnOpStartedListener) {
    synchronized (this.mStartedWatchers) {
      IAppOpsStartedCallback iAppOpsStartedCallback = (IAppOpsStartedCallback)this.mStartedWatchers.remove(paramOnOpStartedListener);
      if (iAppOpsStartedCallback != null)
        try {
          this.mService.stopWatchingStarted(iAppOpsStartedCallback);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return;
    } 
  }
  
  public int unsafeCheckOp(String paramString1, int paramInt, String paramString2) {
    return checkOp(strOpToOp(paramString1), paramInt, paramString2);
  }
  
  public int unsafeCheckOpNoThrow(String paramString1, int paramInt, String paramString2) {
    return checkOpNoThrow(strOpToOp(paramString1), paramInt, paramString2);
  }
  
  public int unsafeCheckOpRaw(String paramString1, int paramInt, String paramString2) {
    return unsafeCheckOpRawNoThrow(paramString1, paramInt, paramString2);
  }
  
  public int unsafeCheckOpRawNoThrow(int paramInt1, int paramInt2, String paramString) {
    try {
      return this.mService.checkOperationRaw(paramInt1, paramInt2, paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int unsafeCheckOpRawNoThrow(String paramString1, int paramInt, String paramString2) {
    return unsafeCheckOpRawNoThrow(strOpToOp(paramString1), paramInt, paramString2);
  }
  
  @SystemApi
  @Deprecated
  public static abstract class AppOpsCollector extends OnOpNotedCallback {
    public Executor getAsyncNotedExecutor() {
      return (Executor)new HandlerExecutor(Handler.getMain());
    }
  }
  
  @SystemApi
  public static final class AttributedHistoricalOps implements Parcelable {
    public static final Parcelable.Creator<AttributedHistoricalOps> CREATOR = new Parcelable.Creator<AttributedHistoricalOps>() {
        public AppOpsManager.AttributedHistoricalOps createFromParcel(Parcel param2Parcel) {
          return new AppOpsManager.AttributedHistoricalOps(param2Parcel);
        }
        
        public AppOpsManager.AttributedHistoricalOps[] newArray(int param2Int) {
          return new AppOpsManager.AttributedHistoricalOps[param2Int];
        }
      };
    
    private ArrayMap<String, AppOpsManager.HistoricalOp> mHistoricalOps;
    
    private final String mTag;
    
    private AttributedHistoricalOps(AttributedHistoricalOps param1AttributedHistoricalOps) {
      this.mTag = param1AttributedHistoricalOps.mTag;
      int i = param1AttributedHistoricalOps.getOpCount();
      for (byte b = 0; b < i; b++) {
        AppOpsManager.HistoricalOp historicalOp = new AppOpsManager.HistoricalOp(param1AttributedHistoricalOps.getOpAt(b));
        if (this.mHistoricalOps == null)
          this.mHistoricalOps = new ArrayMap(i); 
        this.mHistoricalOps.put(historicalOp.getOpName(), historicalOp);
      } 
    }
    
    AttributedHistoricalOps(Parcel param1Parcel) {
      String str;
      byte b = param1Parcel.readByte();
      if ((b & 0x1) == 0) {
        str = null;
      } else {
        str = param1Parcel.readString();
      } 
      ArrayMap<String, AppOpsManager.HistoricalOp> arrayMap = null;
      if ((b & 0x2) != 0) {
        arrayMap = new ArrayMap();
        param1Parcel.readMap((Map)arrayMap, AppOpsManager.HistoricalOp.class.getClassLoader());
      } 
      this.mTag = str;
      this.mHistoricalOps = arrayMap;
    }
    
    public AttributedHistoricalOps(String param1String) {
      this.mTag = param1String;
    }
    
    public AttributedHistoricalOps(String param1String, ArrayMap<String, AppOpsManager.HistoricalOp> param1ArrayMap) {
      this.mTag = param1String;
      this.mHistoricalOps = param1ArrayMap;
    }
    
    private void accept(AppOpsManager.HistoricalOpsVisitor param1HistoricalOpsVisitor) {
      param1HistoricalOpsVisitor.visitHistoricalAttributionOps(this);
      int i = getOpCount();
      for (byte b = 0; b < i; b++)
        getOpAt(b).accept(param1HistoricalOpsVisitor); 
    }
    
    private void filter(String[] param1ArrayOfString, int param1Int, double param1Double) {
      for (int i = getOpCount() - 1; i >= 0; i--) {
        AppOpsManager.HistoricalOp historicalOp = (AppOpsManager.HistoricalOp)this.mHistoricalOps.valueAt(i);
        if ((param1Int & 0x8) != 0 && !ArrayUtils.contains((Object[])param1ArrayOfString, historicalOp.getOpName())) {
          this.mHistoricalOps.removeAt(i);
        } else {
          historicalOp.filter(param1Double);
        } 
      } 
    }
    
    private AppOpsManager.HistoricalOp getOrCreateHistoricalOp(int param1Int) {
      if (this.mHistoricalOps == null)
        this.mHistoricalOps = new ArrayMap(); 
      String str = AppOpsManager.sOpToString[param1Int];
      AppOpsManager.HistoricalOp historicalOp1 = (AppOpsManager.HistoricalOp)this.mHistoricalOps.get(str);
      AppOpsManager.HistoricalOp historicalOp2 = historicalOp1;
      if (historicalOp1 == null) {
        historicalOp2 = new AppOpsManager.HistoricalOp(param1Int);
        this.mHistoricalOps.put(str, historicalOp2);
      } 
      return historicalOp2;
    }
    
    private void increaseAccessCount(int param1Int1, int param1Int2, int param1Int3, long param1Long) {
      getOrCreateHistoricalOp(param1Int1).increaseAccessCount(param1Int2, param1Int3, param1Long);
    }
    
    private void increaseAccessDuration(int param1Int1, int param1Int2, int param1Int3, long param1Long) {
      getOrCreateHistoricalOp(param1Int1).increaseAccessDuration(param1Int2, param1Int3, param1Long);
    }
    
    private void increaseRejectCount(int param1Int1, int param1Int2, int param1Int3, long param1Long) {
      getOrCreateHistoricalOp(param1Int1).increaseRejectCount(param1Int2, param1Int3, param1Long);
    }
    
    private boolean isEmpty() {
      for (int i = getOpCount() - 1; i >= 0; i--) {
        if (!((AppOpsManager.HistoricalOp)this.mHistoricalOps.valueAt(i)).isEmpty())
          return false; 
      } 
      return true;
    }
    
    private void merge(AttributedHistoricalOps param1AttributedHistoricalOps) {
      int i = param1AttributedHistoricalOps.getOpCount();
      for (byte b = 0; b < i; b++) {
        AppOpsManager.HistoricalOp historicalOp1 = param1AttributedHistoricalOps.getOpAt(b);
        AppOpsManager.HistoricalOp historicalOp2 = getOp(historicalOp1.getOpName());
        if (historicalOp2 != null) {
          historicalOp2.merge(historicalOp1);
        } else {
          if (this.mHistoricalOps == null)
            this.mHistoricalOps = new ArrayMap(); 
          this.mHistoricalOps.put(historicalOp1.getOpName(), historicalOp1);
        } 
      } 
    }
    
    private AttributedHistoricalOps splice(double param1Double) {
      AttributedHistoricalOps attributedHistoricalOps = null;
      int i = getOpCount();
      byte b = 0;
      while (b < i) {
        AppOpsManager.HistoricalOp historicalOp = getOpAt(b).splice(param1Double);
        AttributedHistoricalOps attributedHistoricalOps1 = attributedHistoricalOps;
        if (historicalOp != null) {
          attributedHistoricalOps1 = attributedHistoricalOps;
          if (attributedHistoricalOps == null)
            attributedHistoricalOps1 = new AttributedHistoricalOps(this.mTag, null); 
          if (attributedHistoricalOps1.mHistoricalOps == null)
            attributedHistoricalOps1.mHistoricalOps = new ArrayMap(); 
          attributedHistoricalOps1.mHistoricalOps.put(historicalOp.getOpName(), historicalOp);
        } 
        b++;
        attributedHistoricalOps = attributedHistoricalOps1;
      } 
      return attributedHistoricalOps;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if (!Objects.equals(this.mTag, ((AttributedHistoricalOps)param1Object).mTag) || !Objects.equals(this.mHistoricalOps, ((AttributedHistoricalOps)param1Object).mHistoricalOps))
        bool = false; 
      return bool;
    }
    
    public AppOpsManager.HistoricalOp getOp(String param1String) {
      ArrayMap<String, AppOpsManager.HistoricalOp> arrayMap = this.mHistoricalOps;
      return (arrayMap == null) ? null : (AppOpsManager.HistoricalOp)arrayMap.get(param1String);
    }
    
    public AppOpsManager.HistoricalOp getOpAt(int param1Int) {
      ArrayMap<String, AppOpsManager.HistoricalOp> arrayMap = this.mHistoricalOps;
      if (arrayMap != null)
        return (AppOpsManager.HistoricalOp)arrayMap.valueAt(param1Int); 
      throw new IndexOutOfBoundsException();
    }
    
    public int getOpCount() {
      ArrayMap<String, AppOpsManager.HistoricalOp> arrayMap = this.mHistoricalOps;
      return (arrayMap == null) ? 0 : arrayMap.size();
    }
    
    public String getTag() {
      return this.mTag;
    }
    
    public int hashCode() {
      return (1 * 31 + Objects.hashCode(this.mTag)) * 31 + Objects.hashCode(this.mHistoricalOps);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Int = 0;
      if (this.mTag != null)
        param1Int = (byte)(false | true); 
      int i = param1Int;
      if (this.mHistoricalOps != null) {
        param1Int = (byte)(param1Int | 0x2);
        i = param1Int;
      } 
      param1Parcel.writeByte(i);
      String str = this.mTag;
      if (str != null)
        param1Parcel.writeString(str); 
      ArrayMap<String, AppOpsManager.HistoricalOp> arrayMap = this.mHistoricalOps;
      if (arrayMap != null)
        param1Parcel.writeMap((Map)arrayMap); 
    }
  }
  
  class null implements Parcelable.Creator<AttributedHistoricalOps> {
    public AppOpsManager.AttributedHistoricalOps createFromParcel(Parcel param1Parcel) {
      return new AppOpsManager.AttributedHistoricalOps(param1Parcel);
    }
    
    public AppOpsManager.AttributedHistoricalOps[] newArray(int param1Int) {
      return new AppOpsManager.AttributedHistoricalOps[param1Int];
    }
  }
  
  @SystemApi
  public static final class AttributedOpEntry implements Parcelable {
    public static final Parcelable.Creator<AttributedOpEntry> CREATOR;
    
    static Parcelling<LongSparseArray<AppOpsManager.NoteOpEvent>> sParcellingForAccessEvents;
    
    static Parcelling<LongSparseArray<AppOpsManager.NoteOpEvent>> sParcellingForRejectEvents;
    
    private final LongSparseArray<AppOpsManager.NoteOpEvent> mAccessEvents;
    
    private final int mOp;
    
    private final LongSparseArray<AppOpsManager.NoteOpEvent> mRejectEvents;
    
    private final boolean mRunning;
    
    static {
      Parcelling<LongSparseArray<AppOpsManager.NoteOpEvent>> parcelling = Parcelling.Cache.get(LongSparseArrayParceling.class);
      sParcellingForAccessEvents = parcelling;
      if (parcelling == null)
        sParcellingForAccessEvents = Parcelling.Cache.put(new LongSparseArrayParceling()); 
      parcelling = Parcelling.Cache.get(LongSparseArrayParceling.class);
      sParcellingForRejectEvents = parcelling;
      if (parcelling == null)
        sParcellingForRejectEvents = Parcelling.Cache.put(new LongSparseArrayParceling()); 
      CREATOR = new Parcelable.Creator<AttributedOpEntry>() {
          public AppOpsManager.AttributedOpEntry createFromParcel(Parcel param2Parcel) {
            return new AppOpsManager.AttributedOpEntry(param2Parcel);
          }
          
          public AppOpsManager.AttributedOpEntry[] newArray(int param2Int) {
            return new AppOpsManager.AttributedOpEntry[param2Int];
          }
        };
    }
    
    public AttributedOpEntry(int param1Int, boolean param1Boolean, LongSparseArray<AppOpsManager.NoteOpEvent> param1LongSparseArray1, LongSparseArray<AppOpsManager.NoteOpEvent> param1LongSparseArray2) {
      this.mOp = param1Int;
      AnnotationValidations.validate(IntRange.class, null, param1Int, "from", 0L, "to", 102L);
      this.mRunning = param1Boolean;
      this.mAccessEvents = param1LongSparseArray1;
      this.mRejectEvents = param1LongSparseArray2;
    }
    
    AttributedOpEntry(Parcel param1Parcel) {
      boolean bool;
      if ((param1Parcel.readByte() & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      int i = param1Parcel.readInt();
      LongSparseArray<AppOpsManager.NoteOpEvent> longSparseArray2 = (LongSparseArray)sParcellingForAccessEvents.unparcel(param1Parcel);
      LongSparseArray<AppOpsManager.NoteOpEvent> longSparseArray1 = (LongSparseArray)sParcellingForRejectEvents.unparcel(param1Parcel);
      this.mOp = i;
      AnnotationValidations.validate(IntRange.class, null, i, "from", 0L, "to", 102L);
      this.mRunning = bool;
      this.mAccessEvents = longSparseArray2;
      this.mRejectEvents = longSparseArray1;
    }
    
    private AppOpsManager.NoteOpEvent getLastAccessEvent(int param1Int1, int param1Int2, int param1Int3) {
      return AppOpsManager.getLastEvent(this.mAccessEvents, param1Int1, param1Int2, param1Int3);
    }
    
    private AppOpsManager.NoteOpEvent getLastRejectEvent(int param1Int1, int param1Int2, int param1Int3) {
      return AppOpsManager.getLastEvent(this.mRejectEvents, param1Int1, param1Int2, param1Int3);
    }
    
    public ArraySet<Long> collectKeys() {
      ArraySet<Long> arraySet = new ArraySet();
      LongSparseArray<AppOpsManager.NoteOpEvent> longSparseArray = this.mAccessEvents;
      if (longSparseArray != null) {
        int i = longSparseArray.size();
        for (byte b = 0; b < i; b++)
          arraySet.add(Long.valueOf(this.mAccessEvents.keyAt(b))); 
      } 
      longSparseArray = this.mRejectEvents;
      if (longSparseArray != null) {
        int i = longSparseArray.size();
        for (byte b = 0; b < i; b++)
          arraySet.add(Long.valueOf(this.mRejectEvents.keyAt(b))); 
      } 
      return arraySet;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public long getLastAccessBackgroundTime(int param1Int) {
      return getLastAccessTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, param1Int);
    }
    
    public long getLastAccessForegroundTime(int param1Int) {
      return getLastAccessTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), param1Int);
    }
    
    public long getLastAccessTime(int param1Int) {
      return getLastAccessTime(100, 700, param1Int);
    }
    
    public long getLastAccessTime(int param1Int1, int param1Int2, int param1Int3) {
      AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(param1Int1, param1Int2, param1Int3);
      return (noteOpEvent == null) ? -1L : noteOpEvent.getNoteTime();
    }
    
    public long getLastBackgroundDuration(int param1Int) {
      return getLastDuration(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, param1Int);
    }
    
    public AppOpsManager.OpEventProxyInfo getLastBackgroundProxyInfo(int param1Int) {
      return getLastProxyInfo(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, param1Int);
    }
    
    public long getLastDuration(int param1Int) {
      return getLastDuration(100, 700, param1Int);
    }
    
    public long getLastDuration(int param1Int1, int param1Int2, int param1Int3) {
      AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(param1Int1, param1Int2, param1Int3);
      return (noteOpEvent == null) ? -1L : noteOpEvent.getDuration();
    }
    
    public long getLastForegroundDuration(int param1Int) {
      return getLastDuration(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), param1Int);
    }
    
    public AppOpsManager.OpEventProxyInfo getLastForegroundProxyInfo(int param1Int) {
      return getLastProxyInfo(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), param1Int);
    }
    
    public AppOpsManager.OpEventProxyInfo getLastProxyInfo(int param1Int) {
      return getLastProxyInfo(100, 700, param1Int);
    }
    
    public AppOpsManager.OpEventProxyInfo getLastProxyInfo(int param1Int1, int param1Int2, int param1Int3) {
      AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(param1Int1, param1Int2, param1Int3);
      return (noteOpEvent == null) ? null : noteOpEvent.getProxy();
    }
    
    public long getLastRejectBackgroundTime(int param1Int) {
      return getLastRejectTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, param1Int);
    }
    
    public long getLastRejectForegroundTime(int param1Int) {
      return getLastRejectTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), param1Int);
    }
    
    public long getLastRejectTime(int param1Int) {
      return getLastRejectTime(100, 700, param1Int);
    }
    
    public long getLastRejectTime(int param1Int1, int param1Int2, int param1Int3) {
      AppOpsManager.NoteOpEvent noteOpEvent = getLastRejectEvent(param1Int1, param1Int2, param1Int3);
      return (noteOpEvent == null) ? -1L : noteOpEvent.getNoteTime();
    }
    
    public boolean isRunning() {
      return this.mRunning;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      byte b1 = 0;
      if (this.mRunning)
        b1 = (byte)(0x0 | 0x2); 
      byte b2 = b1;
      if (this.mAccessEvents != null)
        b2 = (byte)(b1 | 0x4); 
      byte b3 = b2;
      if (this.mRejectEvents != null) {
        b1 = (byte)(b2 | 0x8);
        b3 = b1;
      } 
      param1Parcel.writeByte(b3);
      param1Parcel.writeInt(this.mOp);
      sParcellingForAccessEvents.parcel(this.mAccessEvents, param1Parcel, param1Int);
      sParcellingForRejectEvents.parcel(this.mRejectEvents, param1Parcel, param1Int);
    }
    
    private static class LongSparseArrayParceling implements Parcelling<LongSparseArray<AppOpsManager.NoteOpEvent>> {
      private LongSparseArrayParceling() {}
      
      public void parcel(LongSparseArray<AppOpsManager.NoteOpEvent> param2LongSparseArray, Parcel param2Parcel, int param2Int) {
        if (param2LongSparseArray == null) {
          param2Parcel.writeInt(-1);
          return;
        } 
        int i = param2LongSparseArray.size();
        param2Parcel.writeInt(i);
        for (byte b = 0; b < i; b++) {
          param2Parcel.writeLong(param2LongSparseArray.keyAt(b));
          param2Parcel.writeParcelable((Parcelable)param2LongSparseArray.valueAt(b), param2Int);
        } 
      }
      
      public LongSparseArray<AppOpsManager.NoteOpEvent> unparcel(Parcel param2Parcel) {
        int i = param2Parcel.readInt();
        if (i == -1)
          return null; 
        LongSparseArray<AppOpsManager.NoteOpEvent> longSparseArray = new LongSparseArray(i);
        for (byte b = 0; b < i; b++)
          longSparseArray.put(param2Parcel.readLong(), param2Parcel.readParcelable(null)); 
        return longSparseArray;
      }
    }
  }
  
  class null implements Parcelable.Creator<AttributedOpEntry> {
    public AppOpsManager.AttributedOpEntry createFromParcel(Parcel param1Parcel) {
      return new AppOpsManager.AttributedOpEntry(param1Parcel);
    }
    
    public AppOpsManager.AttributedOpEntry[] newArray(int param1Int) {
      return new AppOpsManager.AttributedOpEntry[param1Int];
    }
  }
  
  private static class LongSparseArrayParceling implements Parcelling<LongSparseArray<NoteOpEvent>> {
    private LongSparseArrayParceling() {}
    
    public void parcel(LongSparseArray<AppOpsManager.NoteOpEvent> param1LongSparseArray, Parcel param1Parcel, int param1Int) {
      if (param1LongSparseArray == null) {
        param1Parcel.writeInt(-1);
        return;
      } 
      int i = param1LongSparseArray.size();
      param1Parcel.writeInt(i);
      for (byte b = 0; b < i; b++) {
        param1Parcel.writeLong(param1LongSparseArray.keyAt(b));
        param1Parcel.writeParcelable((Parcelable)param1LongSparseArray.valueAt(b), param1Int);
      } 
    }
    
    public LongSparseArray<AppOpsManager.NoteOpEvent> unparcel(Parcel param1Parcel) {
      int i = param1Parcel.readInt();
      if (i == -1)
        return null; 
      LongSparseArray<AppOpsManager.NoteOpEvent> longSparseArray = new LongSparseArray(i);
      for (byte b = 0; b < i; b++)
        longSparseArray.put(param1Parcel.readLong(), param1Parcel.readParcelable(null)); 
      return longSparseArray;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
  public static @interface DataBucketKey {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface HistoricalMode {}
  
  @SystemApi
  public static final class HistoricalOp implements Parcelable {
    public static final Parcelable.Creator<HistoricalOp> CREATOR = new Parcelable.Creator<HistoricalOp>() {
        public AppOpsManager.HistoricalOp createFromParcel(Parcel param2Parcel) {
          return new AppOpsManager.HistoricalOp(param2Parcel);
        }
        
        public AppOpsManager.HistoricalOp[] newArray(int param2Int) {
          return new AppOpsManager.HistoricalOp[param2Int];
        }
      };
    
    private LongSparseLongArray mAccessCount;
    
    private LongSparseLongArray mAccessDuration;
    
    private final int mOp;
    
    private LongSparseLongArray mRejectCount;
    
    public HistoricalOp(int param1Int) {
      this.mOp = param1Int;
    }
    
    private HistoricalOp(HistoricalOp param1HistoricalOp) {
      this.mOp = param1HistoricalOp.mOp;
      LongSparseLongArray longSparseLongArray2 = param1HistoricalOp.mAccessCount;
      if (longSparseLongArray2 != null)
        this.mAccessCount = longSparseLongArray2.clone(); 
      longSparseLongArray2 = param1HistoricalOp.mRejectCount;
      if (longSparseLongArray2 != null)
        this.mRejectCount = longSparseLongArray2.clone(); 
      LongSparseLongArray longSparseLongArray1 = param1HistoricalOp.mAccessDuration;
      if (longSparseLongArray1 != null)
        this.mAccessDuration = longSparseLongArray1.clone(); 
    }
    
    private HistoricalOp(Parcel param1Parcel) {
      this.mOp = param1Parcel.readInt();
      this.mAccessCount = AppOpsManager.readLongSparseLongArrayFromParcel(param1Parcel);
      this.mRejectCount = AppOpsManager.readLongSparseLongArrayFromParcel(param1Parcel);
      this.mAccessDuration = AppOpsManager.readLongSparseLongArrayFromParcel(param1Parcel);
    }
    
    private void accept(AppOpsManager.HistoricalOpsVisitor param1HistoricalOpsVisitor) {
      param1HistoricalOpsVisitor.visitHistoricalOp(this);
    }
    
    private void filter(double param1Double) {
      scale(this.mAccessCount, param1Double);
      scale(this.mRejectCount, param1Double);
      scale(this.mAccessDuration, param1Double);
    }
    
    private LongSparseLongArray getOrCreateAccessCount() {
      if (this.mAccessCount == null)
        this.mAccessCount = new LongSparseLongArray(); 
      return this.mAccessCount;
    }
    
    private LongSparseLongArray getOrCreateAccessDuration() {
      if (this.mAccessDuration == null)
        this.mAccessDuration = new LongSparseLongArray(); 
      return this.mAccessDuration;
    }
    
    private LongSparseLongArray getOrCreateRejectCount() {
      if (this.mRejectCount == null)
        this.mRejectCount = new LongSparseLongArray(); 
      return this.mRejectCount;
    }
    
    private boolean hasData(LongSparseLongArray param1LongSparseLongArray) {
      boolean bool;
      if (param1LongSparseLongArray != null && param1LongSparseLongArray.size() > 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private void increaseAccessCount(int param1Int1, int param1Int2, long param1Long) {
      increaseCount(getOrCreateAccessCount(), param1Int1, param1Int2, param1Long);
    }
    
    private void increaseAccessDuration(int param1Int1, int param1Int2, long param1Long) {
      increaseCount(getOrCreateAccessDuration(), param1Int1, param1Int2, param1Long);
    }
    
    private void increaseCount(LongSparseLongArray param1LongSparseLongArray, int param1Int1, int param1Int2, long param1Long) {
      while (param1Int2 != 0) {
        int i = 1 << Integer.numberOfTrailingZeros(param1Int2);
        param1Int2 &= i;
        long l = AppOpsManager.makeKey(param1Int1, i);
        param1LongSparseLongArray.put(l, param1LongSparseLongArray.get(l) + param1Long);
      } 
    }
    
    private void increaseRejectCount(int param1Int1, int param1Int2, long param1Long) {
      increaseCount(getOrCreateRejectCount(), param1Int1, param1Int2, param1Long);
    }
    
    private boolean isEmpty() {
      boolean bool;
      if (!hasData(this.mAccessCount) && !hasData(this.mRejectCount) && !hasData(this.mAccessDuration)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private void merge(HistoricalOp param1HistoricalOp) {
      merge(new _$$Lambda$AppOpsManager$HistoricalOp$HUOLFYs8TiaQIOXcrq6JzjxA6gs(this), param1HistoricalOp.mAccessCount);
      merge(new _$$Lambda$AppOpsManager$HistoricalOp$DkVcBvqB32SMHlxw0sWQPh3GL1A(this), param1HistoricalOp.mRejectCount);
      merge(new _$$Lambda$AppOpsManager$HistoricalOp$Vs6pDL0wjOBTquwNnreWVbPQrn4(this), param1HistoricalOp.mAccessDuration);
    }
    
    private static void merge(Supplier<LongSparseLongArray> param1Supplier, LongSparseLongArray param1LongSparseLongArray) {
      if (param1LongSparseLongArray != null) {
        int i = param1LongSparseLongArray.size();
        for (byte b = 0; b < i; b++) {
          LongSparseLongArray longSparseLongArray = param1Supplier.get();
          long l1 = param1LongSparseLongArray.keyAt(b);
          long l2 = param1LongSparseLongArray.valueAt(b);
          longSparseLongArray.put(l1, longSparseLongArray.get(l1) + l2);
        } 
      } 
    }
    
    private static void scale(LongSparseLongArray param1LongSparseLongArray, double param1Double) {
      if (param1LongSparseLongArray != null) {
        int i = param1LongSparseLongArray.size();
        for (byte b = 0; b < i; b++)
          param1LongSparseLongArray.put(param1LongSparseLongArray.keyAt(b), (long)AppOpsManager.HistoricalOps.round(param1LongSparseLongArray.valueAt(b) * param1Double)); 
      } 
    }
    
    private HistoricalOp splice(double param1Double) {
      HistoricalOp historicalOp = new HistoricalOp(this.mOp);
      LongSparseLongArray longSparseLongArray = this.mAccessCount;
      Objects.requireNonNull(historicalOp);
      splice(longSparseLongArray, new _$$Lambda$AppOpsManager$HistoricalOp$HUOLFYs8TiaQIOXcrq6JzjxA6gs(historicalOp), param1Double);
      longSparseLongArray = this.mRejectCount;
      Objects.requireNonNull(historicalOp);
      splice(longSparseLongArray, new _$$Lambda$AppOpsManager$HistoricalOp$DkVcBvqB32SMHlxw0sWQPh3GL1A(historicalOp), param1Double);
      longSparseLongArray = this.mAccessDuration;
      Objects.requireNonNull(historicalOp);
      splice(longSparseLongArray, new _$$Lambda$AppOpsManager$HistoricalOp$Vs6pDL0wjOBTquwNnreWVbPQrn4(historicalOp), param1Double);
      return historicalOp;
    }
    
    private static void splice(LongSparseLongArray param1LongSparseLongArray, Supplier<LongSparseLongArray> param1Supplier, double param1Double) {
      if (param1LongSparseLongArray != null) {
        int i = param1LongSparseLongArray.size();
        for (byte b = 0; b < i; b++) {
          long l1 = param1LongSparseLongArray.keyAt(b);
          long l2 = param1LongSparseLongArray.valueAt(b);
          long l3 = Math.round(l2 * param1Double);
          if (l3 > 0L) {
            ((LongSparseLongArray)param1Supplier.get()).put(l1, l3);
            param1LongSparseLongArray.put(l1, l2 - l3);
          } 
        } 
      } 
    }
    
    public LongSparseArray<Object> collectKeys() {
      LongSparseArray longSparseArray = AppOpsManager.collectKeys(this.mAccessCount, null);
      longSparseArray = AppOpsManager.collectKeys(this.mRejectCount, longSparseArray);
      return AppOpsManager.collectKeys(this.mAccessDuration, longSparseArray);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      return (this.mOp != ((HistoricalOp)param1Object).mOp) ? false : (!AppOpsManager.equalsLongSparseLongArray(this.mAccessCount, ((HistoricalOp)param1Object).mAccessCount) ? false : (!AppOpsManager.equalsLongSparseLongArray(this.mRejectCount, ((HistoricalOp)param1Object).mRejectCount) ? false : AppOpsManager.equalsLongSparseLongArray(this.mAccessDuration, ((HistoricalOp)param1Object).mAccessDuration)));
    }
    
    public long getAccessCount(int param1Int1, int param1Int2, int param1Int3) {
      return AppOpsManager.sumForFlagsInStates(this.mAccessCount, param1Int1, param1Int2, param1Int3);
    }
    
    public long getAccessDuration(int param1Int1, int param1Int2, int param1Int3) {
      return AppOpsManager.sumForFlagsInStates(this.mAccessDuration, param1Int1, param1Int2, param1Int3);
    }
    
    public long getBackgroundAccessCount(int param1Int) {
      return AppOpsManager.sumForFlagsInStates(this.mAccessCount, AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, param1Int);
    }
    
    public long getBackgroundAccessDuration(int param1Int) {
      return AppOpsManager.sumForFlagsInStates(this.mAccessDuration, AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, param1Int);
    }
    
    public long getBackgroundRejectCount(int param1Int) {
      return AppOpsManager.sumForFlagsInStates(this.mRejectCount, AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, param1Int);
    }
    
    public long getForegroundAccessCount(int param1Int) {
      return AppOpsManager.sumForFlagsInStates(this.mAccessCount, 100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), param1Int);
    }
    
    public long getForegroundAccessDuration(int param1Int) {
      return AppOpsManager.sumForFlagsInStates(this.mAccessDuration, 100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), param1Int);
    }
    
    public long getForegroundRejectCount(int param1Int) {
      return AppOpsManager.sumForFlagsInStates(this.mRejectCount, 100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), param1Int);
    }
    
    public int getOpCode() {
      return this.mOp;
    }
    
    public String getOpName() {
      return AppOpsManager.sOpToString[this.mOp];
    }
    
    public long getRejectCount(int param1Int1, int param1Int2, int param1Int3) {
      return AppOpsManager.sumForFlagsInStates(this.mRejectCount, param1Int1, param1Int2, param1Int3);
    }
    
    public int hashCode() {
      return ((this.mOp * 31 + Objects.hashCode(this.mAccessCount)) * 31 + Objects.hashCode(this.mRejectCount)) * 31 + Objects.hashCode(this.mAccessDuration);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mOp);
      AppOpsManager.writeLongSparseLongArrayToParcel(this.mAccessCount, param1Parcel);
      AppOpsManager.writeLongSparseLongArrayToParcel(this.mRejectCount, param1Parcel);
      AppOpsManager.writeLongSparseLongArrayToParcel(this.mAccessDuration, param1Parcel);
    }
  }
  
  class null implements Parcelable.Creator<HistoricalOp> {
    public AppOpsManager.HistoricalOp createFromParcel(Parcel param1Parcel) {
      return new AppOpsManager.HistoricalOp(param1Parcel);
    }
    
    public AppOpsManager.HistoricalOp[] newArray(int param1Int) {
      return new AppOpsManager.HistoricalOp[param1Int];
    }
  }
  
  @SystemApi
  public static final class HistoricalOps implements Parcelable {
    public static final Parcelable.Creator<HistoricalOps> CREATOR = new Parcelable.Creator<HistoricalOps>() {
        public AppOpsManager.HistoricalOps createFromParcel(Parcel param2Parcel) {
          return new AppOpsManager.HistoricalOps(param2Parcel);
        }
        
        public AppOpsManager.HistoricalOps[] newArray(int param2Int) {
          return new AppOpsManager.HistoricalOps[param2Int];
        }
      };
    
    private long mBeginTimeMillis;
    
    private long mEndTimeMillis;
    
    private SparseArray<AppOpsManager.HistoricalUidOps> mHistoricalUidOps;
    
    public HistoricalOps(long param1Long1, long param1Long2) {
      boolean bool;
      if (param1Long1 <= param1Long2) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool);
      this.mBeginTimeMillis = param1Long1;
      this.mEndTimeMillis = param1Long2;
    }
    
    public HistoricalOps(HistoricalOps param1HistoricalOps) {
      boolean bool;
      long l1 = param1HistoricalOps.mBeginTimeMillis;
      this.mBeginTimeMillis = l1;
      long l2 = param1HistoricalOps.mEndTimeMillis;
      this.mEndTimeMillis = l2;
      if (l1 <= l2) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool);
      if (param1HistoricalOps.mHistoricalUidOps != null) {
        int i = param1HistoricalOps.getUidCount();
        for (byte b = 0; b < i; b++) {
          AppOpsManager.HistoricalUidOps historicalUidOps = new AppOpsManager.HistoricalUidOps(param1HistoricalOps.getUidOpsAt(b));
          if (this.mHistoricalUidOps == null)
            this.mHistoricalUidOps = new SparseArray(i); 
          this.mHistoricalUidOps.put(historicalUidOps.getUid(), historicalUidOps);
        } 
      } 
    }
    
    private HistoricalOps(Parcel param1Parcel) {
      this.mBeginTimeMillis = param1Parcel.readLong();
      this.mEndTimeMillis = param1Parcel.readLong();
      int[] arrayOfInt = param1Parcel.createIntArray();
      if (!ArrayUtils.isEmpty(arrayOfInt)) {
        ParceledListSlice<AppOpsManager.HistoricalUidOps> parceledListSlice = (ParceledListSlice)param1Parcel.readParcelable(HistoricalOps.class.getClassLoader());
        if (parceledListSlice != null) {
          List list = parceledListSlice.getList();
        } else {
          parceledListSlice = null;
        } 
        if (parceledListSlice == null)
          return; 
        for (byte b = 0; b < arrayOfInt.length; b++) {
          if (this.mHistoricalUidOps == null)
            this.mHistoricalUidOps = new SparseArray(); 
          this.mHistoricalUidOps.put(arrayOfInt[b], parceledListSlice.get(b));
        } 
      } 
    }
    
    private AppOpsManager.HistoricalUidOps getOrCreateHistoricalUidOps(int param1Int) {
      if (this.mHistoricalUidOps == null)
        this.mHistoricalUidOps = new SparseArray(); 
      AppOpsManager.HistoricalUidOps historicalUidOps1 = (AppOpsManager.HistoricalUidOps)this.mHistoricalUidOps.get(param1Int);
      AppOpsManager.HistoricalUidOps historicalUidOps2 = historicalUidOps1;
      if (historicalUidOps1 == null) {
        historicalUidOps2 = new AppOpsManager.HistoricalUidOps(param1Int);
        this.mHistoricalUidOps.put(param1Int, historicalUidOps2);
      } 
      return historicalUidOps2;
    }
    
    public static double round(double param1Double) {
      return (new BigDecimal(param1Double)).setScale(0, RoundingMode.HALF_UP).doubleValue();
    }
    
    private HistoricalOps splice(double param1Double, boolean param1Boolean) {
      long l1;
      long l2;
      if (param1Boolean) {
        l1 = this.mBeginTimeMillis;
        l2 = (long)(this.mBeginTimeMillis + getDurationMillis() * param1Double);
        this.mBeginTimeMillis = l2;
      } else {
        l1 = (long)(this.mEndTimeMillis - getDurationMillis() * param1Double);
        l2 = this.mEndTimeMillis;
        this.mEndTimeMillis = l1;
      } 
      HistoricalOps historicalOps = null;
      int i = getUidCount();
      byte b = 0;
      while (b < i) {
        AppOpsManager.HistoricalUidOps historicalUidOps = getUidOpsAt(b).splice(param1Double);
        HistoricalOps historicalOps1 = historicalOps;
        if (historicalUidOps != null) {
          historicalOps1 = historicalOps;
          if (historicalOps == null)
            historicalOps1 = new HistoricalOps(l1, l2); 
          if (historicalOps1.mHistoricalUidOps == null)
            historicalOps1.mHistoricalUidOps = new SparseArray(); 
          historicalOps1.mHistoricalUidOps.put(historicalUidOps.getUid(), historicalUidOps);
        } 
        b++;
        historicalOps = historicalOps1;
      } 
      return historicalOps;
    }
    
    public void accept(AppOpsManager.HistoricalOpsVisitor param1HistoricalOpsVisitor) {
      param1HistoricalOpsVisitor.visitHistoricalOps(this);
      int i = getUidCount();
      for (byte b = 0; b < i; b++)
        getUidOpsAt(b).accept(param1HistoricalOpsVisitor); 
    }
    
    public void clearHistory(int param1Int, String param1String) {
      AppOpsManager.HistoricalUidOps historicalUidOps = getOrCreateHistoricalUidOps(param1Int);
      historicalUidOps.clearHistory(param1String);
      if (historicalUidOps.isEmpty())
        this.mHistoricalUidOps.remove(param1Int); 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object<AppOpsManager.HistoricalUidOps> param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      HistoricalOps historicalOps = (HistoricalOps)param1Object;
      if (this.mBeginTimeMillis != historicalOps.mBeginTimeMillis)
        return false; 
      if (this.mEndTimeMillis != historicalOps.mEndTimeMillis)
        return false; 
      param1Object = (Object<AppOpsManager.HistoricalUidOps>)this.mHistoricalUidOps;
      if (param1Object == null) {
        if (historicalOps.mHistoricalUidOps != null)
          return false; 
      } else if (!param1Object.equals(historicalOps.mHistoricalUidOps)) {
        return false;
      } 
      return true;
    }
    
    public void filter(int param1Int1, String param1String1, String param1String2, String[] param1ArrayOfString, int param1Int2, long param1Long1, long param1Long2) {
      long l = getDurationMillis();
      this.mBeginTimeMillis = Math.max(this.mBeginTimeMillis, param1Long1);
      this.mEndTimeMillis = Math.min(this.mEndTimeMillis, param1Long2);
      double d = Math.min((param1Long2 - param1Long1) / l, 1.0D);
      for (int i = getUidCount() - 1; i >= 0; i--) {
        AppOpsManager.HistoricalUidOps historicalUidOps = (AppOpsManager.HistoricalUidOps)this.mHistoricalUidOps.valueAt(i);
        if ((param1Int2 & 0x1) != 0 && param1Int1 != historicalUidOps.getUid()) {
          this.mHistoricalUidOps.removeAt(i);
        } else {
          historicalUidOps.filter(param1String1, param1String2, param1ArrayOfString, param1Int2, d);
          if (historicalUidOps.getPackageCount() == 0)
            this.mHistoricalUidOps.removeAt(i); 
        } 
      } 
    }
    
    public long getBeginTimeMillis() {
      return this.mBeginTimeMillis;
    }
    
    public long getDurationMillis() {
      return this.mEndTimeMillis - this.mBeginTimeMillis;
    }
    
    public long getEndTimeMillis() {
      return this.mEndTimeMillis;
    }
    
    public int getUidCount() {
      SparseArray<AppOpsManager.HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
      return (sparseArray == null) ? 0 : sparseArray.size();
    }
    
    public AppOpsManager.HistoricalUidOps getUidOps(int param1Int) {
      SparseArray<AppOpsManager.HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
      return (sparseArray == null) ? null : (AppOpsManager.HistoricalUidOps)sparseArray.get(param1Int);
    }
    
    public AppOpsManager.HistoricalUidOps getUidOpsAt(int param1Int) {
      SparseArray<AppOpsManager.HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
      if (sparseArray != null)
        return (AppOpsManager.HistoricalUidOps)sparseArray.valueAt(param1Int); 
      throw new IndexOutOfBoundsException();
    }
    
    public int hashCode() {
      long l = this.mBeginTimeMillis;
      return (int)(l ^ l >>> 32L) * 31 + this.mHistoricalUidOps.hashCode();
    }
    
    public void increaseAccessCount(int param1Int1, int param1Int2, String param1String1, String param1String2, int param1Int3, int param1Int4, long param1Long) {
      getOrCreateHistoricalUidOps(param1Int2).increaseAccessCount(param1Int1, param1String1, param1String2, param1Int3, param1Int4, param1Long);
    }
    
    public void increaseAccessDuration(int param1Int1, int param1Int2, String param1String1, String param1String2, int param1Int3, int param1Int4, long param1Long) {
      getOrCreateHistoricalUidOps(param1Int2).increaseAccessDuration(param1Int1, param1String1, param1String2, param1Int3, param1Int4, param1Long);
    }
    
    public void increaseRejectCount(int param1Int1, int param1Int2, String param1String1, String param1String2, int param1Int3, int param1Int4, long param1Long) {
      getOrCreateHistoricalUidOps(param1Int2).increaseRejectCount(param1Int1, param1String1, param1String2, param1Int3, param1Int4, param1Long);
    }
    
    public boolean isEmpty() {
      if (getBeginTimeMillis() >= getEndTimeMillis())
        return true; 
      for (int i = getUidCount() - 1; i >= 0; i--) {
        if (!((AppOpsManager.HistoricalUidOps)this.mHistoricalUidOps.valueAt(i)).isEmpty())
          return false; 
      } 
      return true;
    }
    
    public void merge(HistoricalOps param1HistoricalOps) {
      this.mBeginTimeMillis = Math.min(this.mBeginTimeMillis, param1HistoricalOps.mBeginTimeMillis);
      this.mEndTimeMillis = Math.max(this.mEndTimeMillis, param1HistoricalOps.mEndTimeMillis);
      int i = param1HistoricalOps.getUidCount();
      for (byte b = 0; b < i; b++) {
        AppOpsManager.HistoricalUidOps historicalUidOps1 = param1HistoricalOps.getUidOpsAt(b);
        AppOpsManager.HistoricalUidOps historicalUidOps2 = getUidOps(historicalUidOps1.getUid());
        if (historicalUidOps2 != null) {
          historicalUidOps2.merge(historicalUidOps1);
        } else {
          if (this.mHistoricalUidOps == null)
            this.mHistoricalUidOps = new SparseArray(); 
          this.mHistoricalUidOps.put(historicalUidOps1.getUid(), historicalUidOps1);
        } 
      } 
    }
    
    public void offsetBeginAndEndTime(long param1Long) {
      this.mBeginTimeMillis += param1Long;
      this.mEndTimeMillis += param1Long;
    }
    
    public void setBeginAndEndTime(long param1Long1, long param1Long2) {
      this.mBeginTimeMillis = param1Long1;
      this.mEndTimeMillis = param1Long2;
    }
    
    public void setBeginTime(long param1Long) {
      this.mBeginTimeMillis = param1Long;
    }
    
    public void setEndTime(long param1Long) {
      this.mEndTimeMillis = param1Long;
    }
    
    public HistoricalOps spliceFromBeginning(double param1Double) {
      return splice(param1Double, true);
    }
    
    public HistoricalOps spliceFromEnd(double param1Double) {
      return splice(param1Double, false);
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(getClass().getSimpleName());
      stringBuilder.append("[from:");
      stringBuilder.append(this.mBeginTimeMillis);
      stringBuilder.append(" to:");
      stringBuilder.append(this.mEndTimeMillis);
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeLong(this.mBeginTimeMillis);
      param1Parcel.writeLong(this.mEndTimeMillis);
      SparseArray<AppOpsManager.HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
      if (sparseArray != null) {
        int i = sparseArray.size();
        param1Parcel.writeInt(i);
        byte b;
        for (b = 0; b < i; b++)
          param1Parcel.writeInt(this.mHistoricalUidOps.keyAt(b)); 
        ArrayList<AppOpsManager.HistoricalUidOps> arrayList = new ArrayList(i);
        for (b = 0; b < i; b++)
          arrayList.add((AppOpsManager.HistoricalUidOps)this.mHistoricalUidOps.valueAt(b)); 
        param1Parcel.writeParcelable((Parcelable)new ParceledListSlice(arrayList), param1Int);
      } else {
        param1Parcel.writeInt(-1);
      } 
    }
  }
  
  class null implements Parcelable.Creator<HistoricalOps> {
    public AppOpsManager.HistoricalOps createFromParcel(Parcel param1Parcel) {
      return new AppOpsManager.HistoricalOps(param1Parcel);
    }
    
    public AppOpsManager.HistoricalOps[] newArray(int param1Int) {
      return new AppOpsManager.HistoricalOps[param1Int];
    }
  }
  
  @SystemApi
  public static final class HistoricalOpsRequest {
    private final String mAttributionTag;
    
    private final long mBeginTimeMillis;
    
    private final long mEndTimeMillis;
    
    private final int mFilter;
    
    private final int mFlags;
    
    private final List<String> mOpNames;
    
    private final String mPackageName;
    
    private final int mUid;
    
    private HistoricalOpsRequest(int param1Int1, String param1String1, String param1String2, List<String> param1List, int param1Int2, long param1Long1, long param1Long2, int param1Int3) {
      this.mUid = param1Int1;
      this.mPackageName = param1String1;
      this.mAttributionTag = param1String2;
      this.mOpNames = param1List;
      this.mFilter = param1Int2;
      this.mBeginTimeMillis = param1Long1;
      this.mEndTimeMillis = param1Long2;
      this.mFlags = param1Int3;
    }
    
    @SystemApi
    public static final class Builder {
      private String mAttributionTag;
      
      private final long mBeginTimeMillis;
      
      private final long mEndTimeMillis;
      
      private int mFilter;
      
      private int mFlags;
      
      private List<String> mOpNames;
      
      private String mPackageName;
      
      private int mUid;
      
      public Builder(long param2Long1, long param2Long2) {
        boolean bool;
        this.mUid = -1;
        this.mFlags = 31;
        if (param2Long1 >= 0L && param2Long1 < param2Long2) {
          bool = true;
        } else {
          bool = false;
        } 
        Preconditions.checkArgument(bool, "beginTimeMillis must be non negative and lesser than endTimeMillis");
        this.mBeginTimeMillis = param2Long1;
        this.mEndTimeMillis = param2Long2;
      }
      
      public AppOpsManager.HistoricalOpsRequest build() {
        return new AppOpsManager.HistoricalOpsRequest(this.mUid, this.mPackageName, this.mAttributionTag, this.mOpNames, this.mFilter, this.mBeginTimeMillis, this.mEndTimeMillis, this.mFlags);
      }
      
      public Builder setAttributionTag(String param2String) {
        this.mAttributionTag = param2String;
        this.mFilter |= 0x4;
        return this;
      }
      
      public Builder setFlags(int param2Int) {
        Preconditions.checkFlagsArgument(param2Int, 31);
        this.mFlags = param2Int;
        return this;
      }
      
      public Builder setOpNames(List<String> param2List) {
        if (param2List != null) {
          int i = param2List.size();
          for (byte b = 0; b < i; b++) {
            boolean bool;
            if (AppOpsManager.strOpToOp(param2List.get(b)) != -1) {
              bool = true;
            } else {
              bool = false;
            } 
            Preconditions.checkArgument(bool);
          } 
        } 
        this.mOpNames = param2List;
        if (param2List == null) {
          this.mFilter &= 0xFFFFFFF7;
        } else {
          this.mFilter |= 0x8;
        } 
        return this;
      }
      
      public Builder setPackageName(String param2String) {
        this.mPackageName = param2String;
        if (param2String == null) {
          this.mFilter &= 0xFFFFFFFD;
        } else {
          this.mFilter |= 0x2;
        } 
        return this;
      }
      
      public Builder setUid(int param2Int) {
        boolean bool;
        if (param2Int == -1 || param2Int >= 0) {
          bool = true;
        } else {
          bool = false;
        } 
        Preconditions.checkArgument(bool, "uid must be -1 or non negative");
        this.mUid = param2Int;
        if (param2Int == -1) {
          this.mFilter &= 0xFFFFFFFE;
        } else {
          this.mFilter = 0x1 | this.mFilter;
        } 
        return this;
      }
    }
  }
  
  @SystemApi
  public static final class Builder {
    private String mAttributionTag;
    
    private final long mBeginTimeMillis;
    
    private final long mEndTimeMillis;
    
    private int mFilter;
    
    private int mFlags;
    
    private List<String> mOpNames;
    
    private String mPackageName;
    
    private int mUid;
    
    public Builder(long param1Long1, long param1Long2) {
      boolean bool;
      this.mUid = -1;
      this.mFlags = 31;
      if (param1Long1 >= 0L && param1Long1 < param1Long2) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, "beginTimeMillis must be non negative and lesser than endTimeMillis");
      this.mBeginTimeMillis = param1Long1;
      this.mEndTimeMillis = param1Long2;
    }
    
    public AppOpsManager.HistoricalOpsRequest build() {
      return new AppOpsManager.HistoricalOpsRequest(this.mUid, this.mPackageName, this.mAttributionTag, this.mOpNames, this.mFilter, this.mBeginTimeMillis, this.mEndTimeMillis, this.mFlags);
    }
    
    public Builder setAttributionTag(String param1String) {
      this.mAttributionTag = param1String;
      this.mFilter |= 0x4;
      return this;
    }
    
    public Builder setFlags(int param1Int) {
      Preconditions.checkFlagsArgument(param1Int, 31);
      this.mFlags = param1Int;
      return this;
    }
    
    public Builder setOpNames(List<String> param1List) {
      if (param1List != null) {
        int i = param1List.size();
        for (byte b = 0; b < i; b++) {
          boolean bool;
          if (AppOpsManager.strOpToOp(param1List.get(b)) != -1) {
            bool = true;
          } else {
            bool = false;
          } 
          Preconditions.checkArgument(bool);
        } 
      } 
      this.mOpNames = param1List;
      if (param1List == null) {
        this.mFilter &= 0xFFFFFFF7;
      } else {
        this.mFilter |= 0x8;
      } 
      return this;
    }
    
    public Builder setPackageName(String param1String) {
      this.mPackageName = param1String;
      if (param1String == null) {
        this.mFilter &= 0xFFFFFFFD;
      } else {
        this.mFilter |= 0x2;
      } 
      return this;
    }
    
    public Builder setUid(int param1Int) {
      boolean bool;
      if (param1Int == -1 || param1Int >= 0) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, "uid must be -1 or non negative");
      this.mUid = param1Int;
      if (param1Int == -1) {
        this.mFilter &= 0xFFFFFFFE;
      } else {
        this.mFilter = 0x1 | this.mFilter;
      } 
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface HistoricalOpsRequestFilter {}
  
  public static interface HistoricalOpsVisitor {
    void visitHistoricalAttributionOps(AppOpsManager.AttributedHistoricalOps param1AttributedHistoricalOps);
    
    void visitHistoricalOp(AppOpsManager.HistoricalOp param1HistoricalOp);
    
    void visitHistoricalOps(AppOpsManager.HistoricalOps param1HistoricalOps);
    
    void visitHistoricalPackageOps(AppOpsManager.HistoricalPackageOps param1HistoricalPackageOps);
    
    void visitHistoricalUidOps(AppOpsManager.HistoricalUidOps param1HistoricalUidOps);
  }
  
  @SystemApi
  public static final class HistoricalPackageOps implements Parcelable {
    public static final Parcelable.Creator<HistoricalPackageOps> CREATOR = new Parcelable.Creator<HistoricalPackageOps>() {
        public AppOpsManager.HistoricalPackageOps createFromParcel(Parcel param2Parcel) {
          return new AppOpsManager.HistoricalPackageOps(param2Parcel);
        }
        
        public AppOpsManager.HistoricalPackageOps[] newArray(int param2Int) {
          return new AppOpsManager.HistoricalPackageOps[param2Int];
        }
      };
    
    private ArrayMap<String, AppOpsManager.AttributedHistoricalOps> mAttributedHistoricalOps;
    
    private final String mPackageName;
    
    private HistoricalPackageOps(HistoricalPackageOps param1HistoricalPackageOps) {
      this.mPackageName = param1HistoricalPackageOps.mPackageName;
      int i = param1HistoricalPackageOps.getAttributedOpsCount();
      for (byte b = 0; b < i; b++) {
        AppOpsManager.AttributedHistoricalOps attributedHistoricalOps = new AppOpsManager.AttributedHistoricalOps(param1HistoricalPackageOps.getAttributedOpsAt(b));
        if (this.mAttributedHistoricalOps == null)
          this.mAttributedHistoricalOps = new ArrayMap(i); 
        this.mAttributedHistoricalOps.put(attributedHistoricalOps.getTag(), attributedHistoricalOps);
      } 
    }
    
    private HistoricalPackageOps(Parcel param1Parcel) {
      this.mPackageName = param1Parcel.readString();
      this.mAttributedHistoricalOps = param1Parcel.createTypedArrayMap(AppOpsManager.AttributedHistoricalOps.CREATOR);
    }
    
    public HistoricalPackageOps(String param1String) {
      this.mPackageName = param1String;
    }
    
    private void accept(AppOpsManager.HistoricalOpsVisitor param1HistoricalOpsVisitor) {
      param1HistoricalOpsVisitor.visitHistoricalPackageOps(this);
      int i = getAttributedOpsCount();
      for (byte b = 0; b < i; b++)
        getAttributedOpsAt(b).accept(param1HistoricalOpsVisitor); 
    }
    
    private void filter(String param1String, String[] param1ArrayOfString, int param1Int, double param1Double) {
      for (int i = getAttributedOpsCount() - 1; i >= 0; i--) {
        AppOpsManager.AttributedHistoricalOps attributedHistoricalOps = getAttributedOpsAt(i);
        if ((param1Int & 0x4) != 0 && !Objects.equals(param1String, attributedHistoricalOps.getTag())) {
          this.mAttributedHistoricalOps.removeAt(i);
        } else {
          attributedHistoricalOps.filter(param1ArrayOfString, param1Int, param1Double);
          if (attributedHistoricalOps.getOpCount() == 0)
            this.mAttributedHistoricalOps.removeAt(i); 
        } 
      } 
    }
    
    private AppOpsManager.AttributedHistoricalOps getOrCreateAttributedHistoricalOps(String param1String) {
      if (this.mAttributedHistoricalOps == null)
        this.mAttributedHistoricalOps = new ArrayMap(); 
      AppOpsManager.AttributedHistoricalOps attributedHistoricalOps1 = (AppOpsManager.AttributedHistoricalOps)this.mAttributedHistoricalOps.get(param1String);
      AppOpsManager.AttributedHistoricalOps attributedHistoricalOps2 = attributedHistoricalOps1;
      if (attributedHistoricalOps1 == null) {
        attributedHistoricalOps2 = new AppOpsManager.AttributedHistoricalOps(param1String);
        this.mAttributedHistoricalOps.put(param1String, attributedHistoricalOps2);
      } 
      return attributedHistoricalOps2;
    }
    
    private void increaseAccessCount(int param1Int1, String param1String, int param1Int2, int param1Int3, long param1Long) {
      getOrCreateAttributedHistoricalOps(param1String).increaseAccessCount(param1Int1, param1Int2, param1Int3, param1Long);
    }
    
    private void increaseAccessDuration(int param1Int1, String param1String, int param1Int2, int param1Int3, long param1Long) {
      getOrCreateAttributedHistoricalOps(param1String).increaseAccessDuration(param1Int1, param1Int2, param1Int3, param1Long);
    }
    
    private void increaseRejectCount(int param1Int1, String param1String, int param1Int2, int param1Int3, long param1Long) {
      getOrCreateAttributedHistoricalOps(param1String).increaseRejectCount(param1Int1, param1Int2, param1Int3, param1Long);
    }
    
    private boolean isEmpty() {
      for (int i = getAttributedOpsCount() - 1; i >= 0; i--) {
        if (!((AppOpsManager.AttributedHistoricalOps)this.mAttributedHistoricalOps.valueAt(i)).isEmpty())
          return false; 
      } 
      return true;
    }
    
    private void merge(HistoricalPackageOps param1HistoricalPackageOps) {
      int i = param1HistoricalPackageOps.getAttributedOpsCount();
      for (byte b = 0; b < i; b++) {
        AppOpsManager.AttributedHistoricalOps attributedHistoricalOps1 = param1HistoricalPackageOps.getAttributedOpsAt(b);
        AppOpsManager.AttributedHistoricalOps attributedHistoricalOps2 = getAttributedOps(attributedHistoricalOps1.getTag());
        if (attributedHistoricalOps2 != null) {
          attributedHistoricalOps2.merge(attributedHistoricalOps1);
        } else {
          if (this.mAttributedHistoricalOps == null)
            this.mAttributedHistoricalOps = new ArrayMap(); 
          this.mAttributedHistoricalOps.put(attributedHistoricalOps1.getTag(), attributedHistoricalOps1);
        } 
      } 
    }
    
    private HistoricalPackageOps splice(double param1Double) {
      HistoricalPackageOps historicalPackageOps = null;
      int i = getAttributedOpsCount();
      byte b = 0;
      while (b < i) {
        AppOpsManager.AttributedHistoricalOps attributedHistoricalOps = getAttributedOpsAt(b).splice(param1Double);
        HistoricalPackageOps historicalPackageOps1 = historicalPackageOps;
        if (attributedHistoricalOps != null) {
          historicalPackageOps1 = historicalPackageOps;
          if (historicalPackageOps == null)
            historicalPackageOps1 = new HistoricalPackageOps(this.mPackageName); 
          if (historicalPackageOps1.mAttributedHistoricalOps == null)
            historicalPackageOps1.mAttributedHistoricalOps = new ArrayMap(); 
          historicalPackageOps1.mAttributedHistoricalOps.put(attributedHistoricalOps.getTag(), attributedHistoricalOps);
        } 
        b++;
        historicalPackageOps = historicalPackageOps1;
      } 
      return historicalPackageOps;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if (!this.mPackageName.equals(((HistoricalPackageOps)param1Object).mPackageName))
        return false; 
      ArrayMap<String, AppOpsManager.AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
      if (arrayMap == null) {
        if (((HistoricalPackageOps)param1Object).mAttributedHistoricalOps != null)
          return false; 
      } else if (!arrayMap.equals(((HistoricalPackageOps)param1Object).mAttributedHistoricalOps)) {
        return false;
      } 
      return true;
    }
    
    public AppOpsManager.AttributedHistoricalOps getAttributedOps(String param1String) {
      ArrayMap<String, AppOpsManager.AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
      return (arrayMap == null) ? null : (AppOpsManager.AttributedHistoricalOps)arrayMap.get(param1String);
    }
    
    public AppOpsManager.AttributedHistoricalOps getAttributedOpsAt(int param1Int) {
      ArrayMap<String, AppOpsManager.AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
      if (arrayMap != null)
        return (AppOpsManager.AttributedHistoricalOps)arrayMap.valueAt(param1Int); 
      throw new IndexOutOfBoundsException();
    }
    
    public int getAttributedOpsCount() {
      ArrayMap<String, AppOpsManager.AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
      return (arrayMap == null) ? 0 : arrayMap.size();
    }
    
    public AppOpsManager.HistoricalOp getOp(String param1String) {
      if (this.mAttributedHistoricalOps == null)
        return null; 
      AppOpsManager.HistoricalOp historicalOp = null;
      int i = getAttributedOpsCount();
      byte b = 0;
      while (b < i) {
        AppOpsManager.HistoricalOp historicalOp1 = getAttributedOpsAt(b).getOp(param1String);
        AppOpsManager.HistoricalOp historicalOp2 = historicalOp;
        if (historicalOp1 != null)
          if (historicalOp == null) {
            historicalOp2 = new AppOpsManager.HistoricalOp(historicalOp1);
          } else {
            historicalOp.merge(historicalOp1);
            historicalOp2 = historicalOp;
          }  
        b++;
        historicalOp = historicalOp2;
      } 
      return historicalOp;
    }
    
    public AppOpsManager.HistoricalOp getOpAt(int param1Int) {
      int i = 0;
      int j = getAttributedOpsCount();
      byte b = 0;
      while (b < 103) {
        int k;
        String str = AppOpsManager.opToPublicName(b);
        byte b1 = 0;
        while (true) {
          k = i;
          if (b1 < j) {
            if (getAttributedOpsAt(b1).getOp(str) != null) {
              if (i == param1Int)
                return getOp(str); 
              k = i + 1;
              break;
            } 
            b1++;
            continue;
          } 
          break;
        } 
        b++;
        i = k;
      } 
      throw new IndexOutOfBoundsException();
    }
    
    public int getOpCount() {
      int i = 0;
      int j = getAttributedOpsCount();
      byte b = 0;
      while (b < 103) {
        int k;
        String str = AppOpsManager.opToPublicName(b);
        byte b1 = 0;
        while (true) {
          k = i;
          if (b1 < j) {
            if (getAttributedOpsAt(b1).getOp(str) != null) {
              k = i + 1;
              break;
            } 
            b1++;
            continue;
          } 
          break;
        } 
        b++;
        i = k;
      } 
      return i;
    }
    
    public String getPackageName() {
      return this.mPackageName;
    }
    
    public int hashCode() {
      byte b;
      String str = this.mPackageName;
      int i = 0;
      if (str != null) {
        b = str.hashCode();
      } else {
        b = 0;
      } 
      ArrayMap<String, AppOpsManager.AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
      if (arrayMap != null)
        i = arrayMap.hashCode(); 
      return b * 31 + i;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeString(this.mPackageName);
      param1Parcel.writeTypedArrayMap(this.mAttributedHistoricalOps, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<HistoricalPackageOps> {
    public AppOpsManager.HistoricalPackageOps createFromParcel(Parcel param1Parcel) {
      return new AppOpsManager.HistoricalPackageOps(param1Parcel);
    }
    
    public AppOpsManager.HistoricalPackageOps[] newArray(int param1Int) {
      return new AppOpsManager.HistoricalPackageOps[param1Int];
    }
  }
  
  @SystemApi
  public static final class HistoricalUidOps implements Parcelable {
    public static final Parcelable.Creator<HistoricalUidOps> CREATOR = new Parcelable.Creator<HistoricalUidOps>() {
        public AppOpsManager.HistoricalUidOps createFromParcel(Parcel param2Parcel) {
          return new AppOpsManager.HistoricalUidOps(param2Parcel);
        }
        
        public AppOpsManager.HistoricalUidOps[] newArray(int param2Int) {
          return new AppOpsManager.HistoricalUidOps[param2Int];
        }
      };
    
    private ArrayMap<String, AppOpsManager.HistoricalPackageOps> mHistoricalPackageOps;
    
    private final int mUid;
    
    public HistoricalUidOps(int param1Int) {
      this.mUid = param1Int;
    }
    
    private HistoricalUidOps(HistoricalUidOps param1HistoricalUidOps) {
      this.mUid = param1HistoricalUidOps.mUid;
      int i = param1HistoricalUidOps.getPackageCount();
      for (byte b = 0; b < i; b++) {
        AppOpsManager.HistoricalPackageOps historicalPackageOps = new AppOpsManager.HistoricalPackageOps(param1HistoricalUidOps.getPackageOpsAt(b));
        if (this.mHistoricalPackageOps == null)
          this.mHistoricalPackageOps = new ArrayMap(i); 
        this.mHistoricalPackageOps.put(historicalPackageOps.getPackageName(), historicalPackageOps);
      } 
    }
    
    private HistoricalUidOps(Parcel param1Parcel) {
      this.mUid = param1Parcel.readInt();
      this.mHistoricalPackageOps = param1Parcel.createTypedArrayMap(AppOpsManager.HistoricalPackageOps.CREATOR);
    }
    
    private void accept(AppOpsManager.HistoricalOpsVisitor param1HistoricalOpsVisitor) {
      param1HistoricalOpsVisitor.visitHistoricalUidOps(this);
      int i = getPackageCount();
      for (byte b = 0; b < i; b++)
        getPackageOpsAt(b).accept(param1HistoricalOpsVisitor); 
    }
    
    private void clearHistory(String param1String) {
      ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
      if (arrayMap != null)
        arrayMap.remove(param1String); 
    }
    
    private void filter(String param1String1, String param1String2, String[] param1ArrayOfString, int param1Int, double param1Double) {
      for (int i = getPackageCount() - 1; i >= 0; i--) {
        AppOpsManager.HistoricalPackageOps historicalPackageOps = getPackageOpsAt(i);
        if ((param1Int & 0x2) != 0 && !param1String1.equals(historicalPackageOps.getPackageName())) {
          this.mHistoricalPackageOps.removeAt(i);
        } else {
          historicalPackageOps.filter(param1String2, param1ArrayOfString, param1Int, param1Double);
          if (historicalPackageOps.getAttributedOpsCount() == 0)
            this.mHistoricalPackageOps.removeAt(i); 
        } 
      } 
    }
    
    private AppOpsManager.HistoricalPackageOps getOrCreateHistoricalPackageOps(String param1String) {
      if (this.mHistoricalPackageOps == null)
        this.mHistoricalPackageOps = new ArrayMap(); 
      AppOpsManager.HistoricalPackageOps historicalPackageOps1 = (AppOpsManager.HistoricalPackageOps)this.mHistoricalPackageOps.get(param1String);
      AppOpsManager.HistoricalPackageOps historicalPackageOps2 = historicalPackageOps1;
      if (historicalPackageOps1 == null) {
        historicalPackageOps2 = new AppOpsManager.HistoricalPackageOps(param1String);
        this.mHistoricalPackageOps.put(param1String, historicalPackageOps2);
      } 
      return historicalPackageOps2;
    }
    
    private void increaseAccessCount(int param1Int1, String param1String1, String param1String2, int param1Int2, int param1Int3, long param1Long) {
      getOrCreateHistoricalPackageOps(param1String1).increaseAccessCount(param1Int1, param1String2, param1Int2, param1Int3, param1Long);
    }
    
    private void increaseAccessDuration(int param1Int1, String param1String1, String param1String2, int param1Int2, int param1Int3, long param1Long) {
      getOrCreateHistoricalPackageOps(param1String1).increaseAccessDuration(param1Int1, param1String2, param1Int2, param1Int3, param1Long);
    }
    
    private void increaseRejectCount(int param1Int1, String param1String1, String param1String2, int param1Int2, int param1Int3, long param1Long) {
      getOrCreateHistoricalPackageOps(param1String1).increaseRejectCount(param1Int1, param1String2, param1Int2, param1Int3, param1Long);
    }
    
    private boolean isEmpty() {
      for (int i = getPackageCount() - 1; i >= 0; i--) {
        if (!((AppOpsManager.HistoricalPackageOps)this.mHistoricalPackageOps.valueAt(i)).isEmpty())
          return false; 
      } 
      return true;
    }
    
    private void merge(HistoricalUidOps param1HistoricalUidOps) {
      int i = param1HistoricalUidOps.getPackageCount();
      for (byte b = 0; b < i; b++) {
        AppOpsManager.HistoricalPackageOps historicalPackageOps1 = param1HistoricalUidOps.getPackageOpsAt(b);
        AppOpsManager.HistoricalPackageOps historicalPackageOps2 = getPackageOps(historicalPackageOps1.getPackageName());
        if (historicalPackageOps2 != null) {
          historicalPackageOps2.merge(historicalPackageOps1);
        } else {
          if (this.mHistoricalPackageOps == null)
            this.mHistoricalPackageOps = new ArrayMap(); 
          this.mHistoricalPackageOps.put(historicalPackageOps1.getPackageName(), historicalPackageOps1);
        } 
      } 
    }
    
    private HistoricalUidOps splice(double param1Double) {
      HistoricalUidOps historicalUidOps = null;
      int i = getPackageCount();
      byte b = 0;
      while (b < i) {
        AppOpsManager.HistoricalPackageOps historicalPackageOps = getPackageOpsAt(b).splice(param1Double);
        HistoricalUidOps historicalUidOps1 = historicalUidOps;
        if (historicalPackageOps != null) {
          historicalUidOps1 = historicalUidOps;
          if (historicalUidOps == null)
            historicalUidOps1 = new HistoricalUidOps(this.mUid); 
          if (historicalUidOps1.mHistoricalPackageOps == null)
            historicalUidOps1.mHistoricalPackageOps = new ArrayMap(); 
          historicalUidOps1.mHistoricalPackageOps.put(historicalPackageOps.getPackageName(), historicalPackageOps);
        } 
        b++;
        historicalUidOps = historicalUidOps1;
      } 
      return historicalUidOps;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if (this.mUid != ((HistoricalUidOps)param1Object).mUid)
        return false; 
      ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
      if (arrayMap == null) {
        if (((HistoricalUidOps)param1Object).mHistoricalPackageOps != null)
          return false; 
      } else if (!arrayMap.equals(((HistoricalUidOps)param1Object).mHistoricalPackageOps)) {
        return false;
      } 
      return true;
    }
    
    public int getPackageCount() {
      ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
      return (arrayMap == null) ? 0 : arrayMap.size();
    }
    
    public AppOpsManager.HistoricalPackageOps getPackageOps(String param1String) {
      ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
      return (arrayMap == null) ? null : (AppOpsManager.HistoricalPackageOps)arrayMap.get(param1String);
    }
    
    public AppOpsManager.HistoricalPackageOps getPackageOpsAt(int param1Int) {
      ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
      if (arrayMap != null)
        return (AppOpsManager.HistoricalPackageOps)arrayMap.valueAt(param1Int); 
      throw new IndexOutOfBoundsException();
    }
    
    public int getUid() {
      return this.mUid;
    }
    
    public int hashCode() {
      byte b;
      int i = this.mUid;
      ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
      if (arrayMap != null) {
        b = arrayMap.hashCode();
      } else {
        b = 0;
      } 
      return i * 31 + b;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mUid);
      param1Parcel.writeTypedArrayMap(this.mHistoricalPackageOps, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<HistoricalUidOps> {
    public AppOpsManager.HistoricalUidOps createFromParcel(Parcel param1Parcel) {
      return new AppOpsManager.HistoricalUidOps(param1Parcel);
    }
    
    public AppOpsManager.HistoricalUidOps[] newArray(int param1Int) {
      return new AppOpsManager.HistoricalUidOps[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Mode {}
  
  public static final class NoteOpEvent implements Parcelable {
    public static final Parcelable.Creator<NoteOpEvent> CREATOR = new Parcelable.Creator<NoteOpEvent>() {
        public AppOpsManager.NoteOpEvent createFromParcel(Parcel param2Parcel) {
          return new AppOpsManager.NoteOpEvent(param2Parcel);
        }
        
        public AppOpsManager.NoteOpEvent[] newArray(int param2Int) {
          return new AppOpsManager.NoteOpEvent[param2Int];
        }
      };
    
    private long mDuration;
    
    private long mNoteTime;
    
    private AppOpsManager.OpEventProxyInfo mProxy;
    
    public NoteOpEvent(long param1Long1, long param1Long2, AppOpsManager.OpEventProxyInfo param1OpEventProxyInfo) {
      this.mNoteTime = param1Long1;
      AnnotationValidations.validate(IntRange.class, null, param1Long1, "from", 0L);
      this.mDuration = param1Long2;
      AnnotationValidations.validate(IntRange.class, null, param1Long2, "from", -1L);
      this.mProxy = param1OpEventProxyInfo;
    }
    
    public NoteOpEvent(NoteOpEvent param1NoteOpEvent) {
      this(l1, l2, (AppOpsManager.OpEventProxyInfo)param1NoteOpEvent);
    }
    
    NoteOpEvent(Parcel param1Parcel) {
      AppOpsManager.OpEventProxyInfo opEventProxyInfo;
      byte b = param1Parcel.readByte();
      long l1 = param1Parcel.readLong();
      long l2 = param1Parcel.readLong();
      if ((b & 0x4) == 0) {
        param1Parcel = null;
      } else {
        opEventProxyInfo = (AppOpsManager.OpEventProxyInfo)param1Parcel.readTypedObject(AppOpsManager.OpEventProxyInfo.CREATOR);
      } 
      this.mNoteTime = l1;
      AnnotationValidations.validate(IntRange.class, null, l1, "from", 0L);
      this.mDuration = l2;
      AnnotationValidations.validate(IntRange.class, null, l2, "from", -1L);
      this.mProxy = opEventProxyInfo;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public long getDuration() {
      return this.mDuration;
    }
    
    public long getNoteTime() {
      return this.mNoteTime;
    }
    
    public AppOpsManager.OpEventProxyInfo getProxy() {
      return this.mProxy;
    }
    
    public void reinit(long param1Long1, long param1Long2, AppOpsManager.OpEventProxyInfo param1OpEventProxyInfo, Pools.Pool<AppOpsManager.OpEventProxyInfo> param1Pool) {
      this.mNoteTime = Preconditions.checkArgumentNonnegative(param1Long1);
      this.mDuration = Preconditions.checkArgumentInRange(param1Long2, -1L, Long.MAX_VALUE, "duration");
      AppOpsManager.OpEventProxyInfo opEventProxyInfo = this.mProxy;
      if (opEventProxyInfo != null)
        param1Pool.release(opEventProxyInfo); 
      this.mProxy = param1OpEventProxyInfo;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      byte b1 = 0;
      byte b2 = b1;
      if (this.mProxy != null) {
        b1 = (byte)(0x0 | 0x4);
        b2 = b1;
      } 
      param1Parcel.writeByte(b2);
      param1Parcel.writeLong(this.mNoteTime);
      param1Parcel.writeLong(this.mDuration);
      AppOpsManager.OpEventProxyInfo opEventProxyInfo = this.mProxy;
      if (opEventProxyInfo != null)
        param1Parcel.writeTypedObject(opEventProxyInfo, param1Int); 
    }
  }
  
  class null implements Parcelable.Creator<NoteOpEvent> {
    public AppOpsManager.NoteOpEvent createFromParcel(Parcel param1Parcel) {
      return new AppOpsManager.NoteOpEvent(param1Parcel);
    }
    
    public AppOpsManager.NoteOpEvent[] newArray(int param1Int) {
      return new AppOpsManager.NoteOpEvent[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface NotedOpCollectionMode {}
  
  public static interface OnOpActiveChangedInternalListener extends OnOpActiveChangedListener {
    default void onOpActiveChanged(int param1Int1, int param1Int2, String param1String, boolean param1Boolean) {}
    
    default void onOpActiveChanged(String param1String1, int param1Int, String param1String2, boolean param1Boolean) {}
  }
  
  public static interface OnOpActiveChangedListener {
    void onOpActiveChanged(String param1String1, int param1Int, String param1String2, boolean param1Boolean);
  }
  
  public static class OnOpChangedInternalListener implements OnOpChangedListener {
    public void onOpChanged(int param1Int, String param1String) {}
    
    public void onOpChanged(String param1String1, String param1String2) {}
  }
  
  public static interface OnOpChangedListener {
    void onOpChanged(String param1String1, String param1String2);
  }
  
  public static abstract class OnOpNotedCallback {
    private final IAppOpsAsyncNotedCallback mAsyncCb = (IAppOpsAsyncNotedCallback)new IAppOpsAsyncNotedCallback.Stub() {
        public void opNoted(AsyncNotedAppOp param2AsyncNotedAppOp) {
          Objects.requireNonNull(param2AsyncNotedAppOp);
          long l = Binder.clearCallingIdentity();
          try {
            Executor executor = AppOpsManager.OnOpNotedCallback.this.getAsyncNotedExecutor();
            _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY = new _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY();
            this(this, param2AsyncNotedAppOp);
            executor.execute(_$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY);
            return;
          } finally {
            Binder.restoreCallingIdentity(l);
          } 
        }
      };
    
    private Executor mAsyncExecutor;
    
    protected Executor getAsyncNotedExecutor() {
      return this.mAsyncExecutor;
    }
    
    public abstract void onAsyncNoted(AsyncNotedAppOp param1AsyncNotedAppOp);
    
    public abstract void onNoted(SyncNotedAppOp param1SyncNotedAppOp);
    
    public abstract void onSelfNoted(SyncNotedAppOp param1SyncNotedAppOp);
  }
  
  class null extends IAppOpsAsyncNotedCallback.Stub {
    public void opNoted(AsyncNotedAppOp param1AsyncNotedAppOp) {
      Objects.requireNonNull(param1AsyncNotedAppOp);
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = this.this$0.getAsyncNotedExecutor();
        _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY = new _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY();
        this(this, param1AsyncNotedAppOp);
        executor.execute(_$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    }
  }
  
  public static interface OnOpNotedListener {
    void onOpNoted(int param1Int1, int param1Int2, String param1String, int param1Int3);
  }
  
  public static interface OnOpStartedListener {
    void onOpStarted(int param1Int1, int param1Int2, String param1String, int param1Int3);
  }
  
  @SystemApi
  public static final class OpEntry implements Parcelable {
    public static final Parcelable.Creator<OpEntry> CREATOR = new Parcelable.Creator<OpEntry>() {
        public AppOpsManager.OpEntry createFromParcel(Parcel param2Parcel) {
          return new AppOpsManager.OpEntry(param2Parcel);
        }
        
        public AppOpsManager.OpEntry[] newArray(int param2Int) {
          return new AppOpsManager.OpEntry[param2Int];
        }
      };
    
    private final Map<String, AppOpsManager.AttributedOpEntry> mAttributedOpEntries;
    
    private final int mMode;
    
    private final int mOp;
    
    public OpEntry(int param1Int1, int param1Int2, Map<String, AppOpsManager.AttributedOpEntry> param1Map) {
      this.mOp = param1Int1;
      AnnotationValidations.validate(IntRange.class, null, param1Int1, "from", 0L, "to", 102L);
      this.mMode = param1Int2;
      AnnotationValidations.validate(AppOpsManager.Mode.class, null, param1Int2);
      this.mAttributedOpEntries = param1Map;
      AnnotationValidations.validate(NonNull.class, null, param1Map);
    }
    
    OpEntry(Parcel param1Parcel) {
      int i = param1Parcel.readInt();
      int j = param1Parcel.readInt();
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
      param1Parcel.readMap(linkedHashMap, AppOpsManager.AttributedOpEntry.class.getClassLoader());
      this.mOp = i;
      AnnotationValidations.validate(IntRange.class, null, i, "from", 0L, "to", 102L);
      this.mMode = j;
      AnnotationValidations.validate(AppOpsManager.Mode.class, null, j);
      this.mAttributedOpEntries = (Map)linkedHashMap;
      AnnotationValidations.validate(NonNull.class, null, linkedHashMap);
    }
    
    private AppOpsManager.NoteOpEvent getLastAccessEvent(int param1Int1, int param1Int2, int param1Int3) {
      // Byte code:
      //   0: aconst_null
      //   1: astore #4
      //   3: aload_0
      //   4: getfield mAttributedOpEntries : Ljava/util/Map;
      //   7: invokeinterface values : ()Ljava/util/Collection;
      //   12: invokeinterface iterator : ()Ljava/util/Iterator;
      //   17: astore #5
      //   19: aload #5
      //   21: invokeinterface hasNext : ()Z
      //   26: ifeq -> 90
      //   29: aload #5
      //   31: invokeinterface next : ()Ljava/lang/Object;
      //   36: checkcast android/app/AppOpsManager$AttributedOpEntry
      //   39: iload_1
      //   40: iload_2
      //   41: iload_3
      //   42: invokestatic access$700 : (Landroid/app/AppOpsManager$AttributedOpEntry;III)Landroid/app/AppOpsManager$NoteOpEvent;
      //   45: astore #6
      //   47: aload #4
      //   49: ifnull -> 79
      //   52: aload #4
      //   54: astore #7
      //   56: aload #6
      //   58: ifnull -> 83
      //   61: aload #4
      //   63: astore #7
      //   65: aload #6
      //   67: invokevirtual getNoteTime : ()J
      //   70: aload #4
      //   72: invokevirtual getNoteTime : ()J
      //   75: lcmp
      //   76: ifle -> 83
      //   79: aload #6
      //   81: astore #7
      //   83: aload #7
      //   85: astore #4
      //   87: goto -> 19
      //   90: aload #4
      //   92: areturn
    }
    
    private AppOpsManager.NoteOpEvent getLastRejectEvent(int param1Int1, int param1Int2, int param1Int3) {
      // Byte code:
      //   0: aconst_null
      //   1: astore #4
      //   3: aload_0
      //   4: getfield mAttributedOpEntries : Ljava/util/Map;
      //   7: invokeinterface values : ()Ljava/util/Collection;
      //   12: invokeinterface iterator : ()Ljava/util/Iterator;
      //   17: astore #5
      //   19: aload #5
      //   21: invokeinterface hasNext : ()Z
      //   26: ifeq -> 90
      //   29: aload #5
      //   31: invokeinterface next : ()Ljava/lang/Object;
      //   36: checkcast android/app/AppOpsManager$AttributedOpEntry
      //   39: iload_1
      //   40: iload_2
      //   41: iload_3
      //   42: invokestatic access$800 : (Landroid/app/AppOpsManager$AttributedOpEntry;III)Landroid/app/AppOpsManager$NoteOpEvent;
      //   45: astore #6
      //   47: aload #4
      //   49: ifnull -> 79
      //   52: aload #4
      //   54: astore #7
      //   56: aload #6
      //   58: ifnull -> 83
      //   61: aload #4
      //   63: astore #7
      //   65: aload #6
      //   67: invokevirtual getNoteTime : ()J
      //   70: aload #4
      //   72: invokevirtual getNoteTime : ()J
      //   75: lcmp
      //   76: ifle -> 83
      //   79: aload #6
      //   81: astore #7
      //   83: aload #7
      //   85: astore #4
      //   87: goto -> 19
      //   90: aload #4
      //   92: areturn
    }
    
    public int describeContents() {
      return 0;
    }
    
    public Map<String, AppOpsManager.AttributedOpEntry> getAttributedOpEntries() {
      return this.mAttributedOpEntries;
    }
    
    @Deprecated
    public long getDuration() {
      return getLastDuration(31);
    }
    
    public long getLastAccessBackgroundTime(int param1Int) {
      return getLastAccessTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, param1Int);
    }
    
    public long getLastAccessForegroundTime(int param1Int) {
      return getLastAccessTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), param1Int);
    }
    
    public long getLastAccessTime(int param1Int) {
      return getLastAccessTime(100, 700, param1Int);
    }
    
    public long getLastAccessTime(int param1Int1, int param1Int2, int param1Int3) {
      AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(param1Int1, param1Int2, param1Int3);
      return (noteOpEvent == null) ? -1L : noteOpEvent.getNoteTime();
    }
    
    public long getLastBackgroundDuration(int param1Int) {
      return getLastDuration(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, param1Int);
    }
    
    public AppOpsManager.OpEventProxyInfo getLastBackgroundProxyInfo(int param1Int) {
      return getLastProxyInfo(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, param1Int);
    }
    
    public long getLastDuration(int param1Int) {
      return getLastDuration(100, 700, param1Int);
    }
    
    public long getLastDuration(int param1Int1, int param1Int2, int param1Int3) {
      AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(param1Int1, param1Int2, param1Int3);
      return (noteOpEvent == null) ? -1L : noteOpEvent.getDuration();
    }
    
    public long getLastForegroundDuration(int param1Int) {
      return getLastDuration(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), param1Int);
    }
    
    public AppOpsManager.OpEventProxyInfo getLastForegroundProxyInfo(int param1Int) {
      return getLastProxyInfo(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), param1Int);
    }
    
    public AppOpsManager.OpEventProxyInfo getLastProxyInfo(int param1Int) {
      return getLastProxyInfo(100, 700, param1Int);
    }
    
    public AppOpsManager.OpEventProxyInfo getLastProxyInfo(int param1Int1, int param1Int2, int param1Int3) {
      AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(param1Int1, param1Int2, param1Int3);
      return (noteOpEvent == null) ? null : noteOpEvent.getProxy();
    }
    
    public long getLastRejectBackgroundTime(int param1Int) {
      return getLastRejectTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, param1Int);
    }
    
    public long getLastRejectForegroundTime(int param1Int) {
      return getLastRejectTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), param1Int);
    }
    
    public long getLastRejectTime(int param1Int) {
      return getLastRejectTime(100, 700, param1Int);
    }
    
    public long getLastRejectTime(int param1Int1, int param1Int2, int param1Int3) {
      AppOpsManager.NoteOpEvent noteOpEvent = getLastRejectEvent(param1Int1, param1Int2, param1Int3);
      return (noteOpEvent == null) ? -1L : noteOpEvent.getNoteTime();
    }
    
    public int getMode() {
      return this.mMode;
    }
    
    public int getOp() {
      return this.mOp;
    }
    
    public String getOpStr() {
      return AppOpsManager.sOpToString[this.mOp];
    }
    
    @Deprecated
    public String getProxyPackageName() {
      AppOpsManager.OpEventProxyInfo opEventProxyInfo = getLastProxyInfo(31);
      return (opEventProxyInfo == null) ? null : opEventProxyInfo.getPackageName();
    }
    
    @Deprecated
    public String getProxyPackageName(int param1Int1, int param1Int2) {
      AppOpsManager.OpEventProxyInfo opEventProxyInfo = getLastProxyInfo(param1Int1, param1Int1, param1Int2);
      return (opEventProxyInfo == null) ? null : opEventProxyInfo.getPackageName();
    }
    
    @Deprecated
    public int getProxyUid() {
      AppOpsManager.OpEventProxyInfo opEventProxyInfo = getLastProxyInfo(31);
      return (opEventProxyInfo == null) ? -1 : opEventProxyInfo.getUid();
    }
    
    @Deprecated
    public int getProxyUid(int param1Int1, int param1Int2) {
      AppOpsManager.OpEventProxyInfo opEventProxyInfo = getLastProxyInfo(param1Int1, param1Int1, param1Int2);
      return (opEventProxyInfo == null) ? -1 : opEventProxyInfo.getUid();
    }
    
    @Deprecated
    public long getRejectTime() {
      return getLastRejectTime(31);
    }
    
    @Deprecated
    public long getTime() {
      return getLastAccessTime(31);
    }
    
    public boolean isRunning() {
      Iterator<AppOpsManager.AttributedOpEntry> iterator = this.mAttributedOpEntries.values().iterator();
      while (iterator.hasNext()) {
        if (((AppOpsManager.AttributedOpEntry)iterator.next()).isRunning())
          return true; 
      } 
      return false;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mOp);
      param1Parcel.writeInt(this.mMode);
      param1Parcel.writeMap(this.mAttributedOpEntries);
    }
  }
  
  class null implements Parcelable.Creator<OpEntry> {
    public AppOpsManager.OpEntry createFromParcel(Parcel param1Parcel) {
      return new AppOpsManager.OpEntry(param1Parcel);
    }
    
    public AppOpsManager.OpEntry[] newArray(int param1Int) {
      return new AppOpsManager.OpEntry[param1Int];
    }
  }
  
  @SystemApi
  public static final class OpEventProxyInfo implements Parcelable {
    public static final Parcelable.Creator<OpEventProxyInfo> CREATOR = new Parcelable.Creator<OpEventProxyInfo>() {
        public AppOpsManager.OpEventProxyInfo createFromParcel(Parcel param2Parcel) {
          return new AppOpsManager.OpEventProxyInfo(param2Parcel);
        }
        
        public AppOpsManager.OpEventProxyInfo[] newArray(int param2Int) {
          return new AppOpsManager.OpEventProxyInfo[param2Int];
        }
      };
    
    private String mAttributionTag;
    
    private String mPackageName;
    
    private int mUid;
    
    public OpEventProxyInfo(int param1Int, String param1String1, String param1String2) {
      this.mUid = param1Int;
      AnnotationValidations.validate(IntRange.class, null, param1Int, "from", 0L);
      this.mPackageName = param1String1;
      this.mAttributionTag = param1String2;
    }
    
    public OpEventProxyInfo(OpEventProxyInfo param1OpEventProxyInfo) {
      this.mUid = param1OpEventProxyInfo.mUid;
      this.mPackageName = param1OpEventProxyInfo.mPackageName;
      this.mAttributionTag = param1OpEventProxyInfo.mAttributionTag;
    }
    
    OpEventProxyInfo(Parcel param1Parcel) {
      String str1;
      String str2;
      byte b = param1Parcel.readByte();
      int i = param1Parcel.readInt();
      Parcel parcel = null;
      if ((b & 0x2) == 0) {
        str2 = null;
      } else {
        str2 = param1Parcel.readString();
      } 
      if ((b & 0x4) == 0) {
        param1Parcel = parcel;
      } else {
        str1 = param1Parcel.readString();
      } 
      this.mUid = i;
      AnnotationValidations.validate(IntRange.class, null, i, "from", 0L);
      this.mPackageName = str2;
      this.mAttributionTag = str1;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public String getAttributionTag() {
      return this.mAttributionTag;
    }
    
    public String getPackageName() {
      return this.mPackageName;
    }
    
    public int getUid() {
      return this.mUid;
    }
    
    public void reinit(int param1Int, String param1String1, String param1String2) {
      this.mUid = Preconditions.checkArgumentNonnegative(param1Int);
      this.mPackageName = param1String1;
      this.mAttributionTag = param1String2;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Int = 0;
      if (this.mPackageName != null)
        param1Int = (byte)(0x0 | 0x2); 
      int i = param1Int;
      if (this.mAttributionTag != null) {
        param1Int = (byte)(param1Int | 0x4);
        i = param1Int;
      } 
      param1Parcel.writeByte(i);
      param1Parcel.writeInt(this.mUid);
      String str = this.mPackageName;
      if (str != null)
        param1Parcel.writeString(str); 
      str = this.mAttributionTag;
      if (str != null)
        param1Parcel.writeString(str); 
    }
  }
  
  class null implements Parcelable.Creator<OpEventProxyInfo> {
    public AppOpsManager.OpEventProxyInfo createFromParcel(Parcel param1Parcel) {
      return new AppOpsManager.OpEventProxyInfo(param1Parcel);
    }
    
    public AppOpsManager.OpEventProxyInfo[] newArray(int param1Int) {
      return new AppOpsManager.OpEventProxyInfo[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface OpFlags {}
  
  @SystemApi
  public static final class PackageOps implements Parcelable {
    public static final Parcelable.Creator<PackageOps> CREATOR = new Parcelable.Creator<PackageOps>() {
        public AppOpsManager.PackageOps createFromParcel(Parcel param2Parcel) {
          return new AppOpsManager.PackageOps(param2Parcel);
        }
        
        public AppOpsManager.PackageOps[] newArray(int param2Int) {
          return new AppOpsManager.PackageOps[param2Int];
        }
      };
    
    private final List<AppOpsManager.OpEntry> mEntries;
    
    private final String mPackageName;
    
    private final int mUid;
    
    PackageOps(Parcel param1Parcel) {
      this.mPackageName = param1Parcel.readString();
      this.mUid = param1Parcel.readInt();
      this.mEntries = new ArrayList<>();
      int i = param1Parcel.readInt();
      for (byte b = 0; b < i; b++)
        this.mEntries.add((AppOpsManager.OpEntry)AppOpsManager.OpEntry.CREATOR.createFromParcel(param1Parcel)); 
    }
    
    public PackageOps(String param1String, int param1Int, List<AppOpsManager.OpEntry> param1List) {
      this.mPackageName = param1String;
      this.mUid = param1Int;
      this.mEntries = param1List;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public List<AppOpsManager.OpEntry> getOps() {
      return this.mEntries;
    }
    
    public String getPackageName() {
      return this.mPackageName;
    }
    
    public int getUid() {
      return this.mUid;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeString(this.mPackageName);
      param1Parcel.writeInt(this.mUid);
      param1Parcel.writeInt(this.mEntries.size());
      for (byte b = 0; b < this.mEntries.size(); b++)
        ((AppOpsManager.OpEntry)this.mEntries.get(b)).writeToParcel(param1Parcel, param1Int); 
    }
  }
  
  class null implements Parcelable.Creator<PackageOps> {
    public AppOpsManager.PackageOps createFromParcel(Parcel param1Parcel) {
      return new AppOpsManager.PackageOps(param1Parcel);
    }
    
    public AppOpsManager.PackageOps[] newArray(int param1Int) {
      return new AppOpsManager.PackageOps[param1Int];
    }
  }
  
  public static class PausedNotedAppOpsCollection {
    final ArrayMap<String, long[]> mCollectedNotedAppOps;
    
    final int mUid;
    
    PausedNotedAppOpsCollection(int param1Int, ArrayMap<String, long[]> param1ArrayMap) {
      this.mUid = param1Int;
      this.mCollectedNotedAppOps = param1ArrayMap;
    }
  }
  
  public static class RestrictionBypass {
    public static RestrictionBypass UNRESTRICTED = new RestrictionBypass(true, true);
    
    public boolean isPrivileged;
    
    public boolean isRecordAudioRestrictionExcept;
    
    public RestrictionBypass(boolean param1Boolean1, boolean param1Boolean2) {
      this.isPrivileged = param1Boolean1;
      this.isRecordAudioRestrictionExcept = param1Boolean2;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SamplingStrategy {}
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface ShouldCollectNoteOp {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface UidState {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
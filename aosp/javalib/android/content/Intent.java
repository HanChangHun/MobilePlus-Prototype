package android.content;

import android.annotation.SystemApi;
import android.app.AppGlobals;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.os.ShellCommand;
import android.os.UserHandle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import com.android.internal.R;
import com.android.internal.util.XmlUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class Intent implements Parcelable, Cloneable {
  public static final String ACTION_ADVANCED_SETTINGS_CHANGED = "android.intent.action.ADVANCED_SETTINGS";
  
  public static final String ACTION_AIRPLANE_MODE_CHANGED = "android.intent.action.AIRPLANE_MODE";
  
  public static final String ACTION_ALARM_CHANGED = "android.intent.action.ALARM_CHANGED";
  
  public static final String ACTION_ALL_APPS = "android.intent.action.ALL_APPS";
  
  public static final String ACTION_ANSWER = "android.intent.action.ANSWER";
  
  public static final String ACTION_APPLICATION_PREFERENCES = "android.intent.action.APPLICATION_PREFERENCES";
  
  public static final String ACTION_APPLICATION_RESTRICTIONS_CHANGED = "android.intent.action.APPLICATION_RESTRICTIONS_CHANGED";
  
  public static final String ACTION_APP_ERROR = "android.intent.action.APP_ERROR";
  
  public static final String ACTION_ASSIST = "android.intent.action.ASSIST";
  
  public static final String ACTION_ATTACH_DATA = "android.intent.action.ATTACH_DATA";
  
  public static final String ACTION_AUTO_REVOKE_PERMISSIONS = "android.intent.action.AUTO_REVOKE_PERMISSIONS";
  
  public static final String ACTION_BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";
  
  @SystemApi
  public static final String ACTION_BATTERY_LEVEL_CHANGED = "android.intent.action.BATTERY_LEVEL_CHANGED";
  
  public static final String ACTION_BATTERY_LOW = "android.intent.action.BATTERY_LOW";
  
  public static final String ACTION_BATTERY_OKAY = "android.intent.action.BATTERY_OKAY";
  
  public static final String ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";
  
  public static final String ACTION_BUG_REPORT = "android.intent.action.BUG_REPORT";
  
  public static final String ACTION_CALL = "android.intent.action.CALL";
  
  public static final String ACTION_CALL_BUTTON = "android.intent.action.CALL_BUTTON";
  
  @SystemApi
  public static final String ACTION_CALL_EMERGENCY = "android.intent.action.CALL_EMERGENCY";
  
  @SystemApi
  public static final String ACTION_CALL_PRIVILEGED = "android.intent.action.CALL_PRIVILEGED";
  
  public static final String ACTION_CAMERA_BUTTON = "android.intent.action.CAMERA_BUTTON";
  
  public static final String ACTION_CANCEL_ENABLE_ROLLBACK = "android.intent.action.CANCEL_ENABLE_ROLLBACK";
  
  public static final String ACTION_CARRIER_SETUP = "android.intent.action.CARRIER_SETUP";
  
  public static final String ACTION_CHOOSER = "android.intent.action.CHOOSER";
  
  public static final String ACTION_CLEAR_DNS_CACHE = "android.intent.action.CLEAR_DNS_CACHE";
  
  public static final String ACTION_CLOSE_SYSTEM_DIALOGS = "android.intent.action.CLOSE_SYSTEM_DIALOGS";
  
  public static final String ACTION_CONFIGURATION_CHANGED = "android.intent.action.CONFIGURATION_CHANGED";
  
  public static final String ACTION_CREATE_DOCUMENT = "android.intent.action.CREATE_DOCUMENT";
  
  public static final String ACTION_CREATE_REMINDER = "android.intent.action.CREATE_REMINDER";
  
  public static final String ACTION_CREATE_SHORTCUT = "android.intent.action.CREATE_SHORTCUT";
  
  public static final String ACTION_DATE_CHANGED = "android.intent.action.DATE_CHANGED";
  
  public static final String ACTION_DEFAULT = "android.intent.action.VIEW";
  
  public static final String ACTION_DEFINE = "android.intent.action.DEFINE";
  
  public static final String ACTION_DELETE = "android.intent.action.DELETE";
  
  @SystemApi
  public static final String ACTION_DEVICE_CUSTOMIZATION_READY = "android.intent.action.DEVICE_CUSTOMIZATION_READY";
  
  @SystemApi
  @Deprecated
  public static final String ACTION_DEVICE_INITIALIZATION_WIZARD = "android.intent.action.DEVICE_INITIALIZATION_WIZARD";
  
  public static final String ACTION_DEVICE_LOCKED_CHANGED = "android.intent.action.DEVICE_LOCKED_CHANGED";
  
  @Deprecated
  public static final String ACTION_DEVICE_STORAGE_FULL = "android.intent.action.DEVICE_STORAGE_FULL";
  
  @Deprecated
  public static final String ACTION_DEVICE_STORAGE_LOW = "android.intent.action.DEVICE_STORAGE_LOW";
  
  @Deprecated
  public static final String ACTION_DEVICE_STORAGE_NOT_FULL = "android.intent.action.DEVICE_STORAGE_NOT_FULL";
  
  @Deprecated
  public static final String ACTION_DEVICE_STORAGE_OK = "android.intent.action.DEVICE_STORAGE_OK";
  
  public static final String ACTION_DIAL = "android.intent.action.DIAL";
  
  @SystemApi
  public static final String ACTION_DIAL_EMERGENCY = "android.intent.action.DIAL_EMERGENCY";
  
  public static final String ACTION_DISMISS_KEYBOARD_SHORTCUTS = "com.android.intent.action.DISMISS_KEYBOARD_SHORTCUTS";
  
  public static final String ACTION_DISTRACTING_PACKAGES_CHANGED = "android.intent.action.DISTRACTING_PACKAGES_CHANGED";
  
  public static final String ACTION_DOCK_ACTIVE = "android.intent.action.DOCK_ACTIVE";
  
  public static final String ACTION_DOCK_EVENT = "android.intent.action.DOCK_EVENT";
  
  public static final String ACTION_DOCK_IDLE = "android.intent.action.DOCK_IDLE";
  
  public static final String ACTION_DREAMING_STARTED = "android.intent.action.DREAMING_STARTED";
  
  public static final String ACTION_DREAMING_STOPPED = "android.intent.action.DREAMING_STOPPED";
  
  public static final String ACTION_DYNAMIC_SENSOR_CHANGED = "android.intent.action.DYNAMIC_SENSOR_CHANGED";
  
  public static final String ACTION_EDIT = "android.intent.action.EDIT";
  
  public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";
  
  public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";
  
  @SystemApi
  public static final String ACTION_FACTORY_RESET = "android.intent.action.FACTORY_RESET";
  
  public static final String ACTION_FACTORY_TEST = "android.intent.action.FACTORY_TEST";
  
  public static final String ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT";
  
  public static final String ACTION_GET_RESTRICTION_ENTRIES = "android.intent.action.GET_RESTRICTION_ENTRIES";
  
  @SystemApi
  public static final String ACTION_GLOBAL_BUTTON = "android.intent.action.GLOBAL_BUTTON";
  
  public static final String ACTION_GTALK_SERVICE_CONNECTED = "android.intent.action.GTALK_CONNECTED";
  
  public static final String ACTION_GTALK_SERVICE_DISCONNECTED = "android.intent.action.GTALK_DISCONNECTED";
  
  public static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";
  
  public static final String ACTION_IDLE_MAINTENANCE_END = "android.intent.action.ACTION_IDLE_MAINTENANCE_END";
  
  public static final String ACTION_IDLE_MAINTENANCE_START = "android.intent.action.ACTION_IDLE_MAINTENANCE_START";
  
  @SystemApi
  public static final String ACTION_INCIDENT_REPORT_READY = "android.intent.action.INCIDENT_REPORT_READY";
  
  public static final String ACTION_INPUT_METHOD_CHANGED = "android.intent.action.INPUT_METHOD_CHANGED";
  
  public static final String ACTION_INSERT = "android.intent.action.INSERT";
  
  public static final String ACTION_INSERT_OR_EDIT = "android.intent.action.INSERT_OR_EDIT";
  
  public static final String ACTION_INSTALL_FAILURE = "android.intent.action.INSTALL_FAILURE";
  
  @SystemApi
  public static final String ACTION_INSTALL_INSTANT_APP_PACKAGE = "android.intent.action.INSTALL_INSTANT_APP_PACKAGE";
  
  @Deprecated
  public static final String ACTION_INSTALL_PACKAGE = "android.intent.action.INSTALL_PACKAGE";
  
  @SystemApi
  public static final String ACTION_INSTANT_APP_RESOLVER_SETTINGS = "android.intent.action.INSTANT_APP_RESOLVER_SETTINGS";
  
  @SystemApi
  public static final String ACTION_INTENT_FILTER_NEEDS_VERIFICATION = "android.intent.action.INTENT_FILTER_NEEDS_VERIFICATION";
  
  @SystemApi
  public static final String ACTION_LOAD_DATA = "android.intent.action.LOAD_DATA";
  
  public static final String ACTION_LOCALE_CHANGED = "android.intent.action.LOCALE_CHANGED";
  
  public static final String ACTION_LOCKED_BOOT_COMPLETED = "android.intent.action.LOCKED_BOOT_COMPLETED";
  
  public static final String ACTION_MAIN = "android.intent.action.MAIN";
  
  public static final String ACTION_MANAGED_PROFILE_ADDED = "android.intent.action.MANAGED_PROFILE_ADDED";
  
  public static final String ACTION_MANAGED_PROFILE_AVAILABLE = "android.intent.action.MANAGED_PROFILE_AVAILABLE";
  
  public static final String ACTION_MANAGED_PROFILE_REMOVED = "android.intent.action.MANAGED_PROFILE_REMOVED";
  
  public static final String ACTION_MANAGED_PROFILE_UNAVAILABLE = "android.intent.action.MANAGED_PROFILE_UNAVAILABLE";
  
  public static final String ACTION_MANAGED_PROFILE_UNLOCKED = "android.intent.action.MANAGED_PROFILE_UNLOCKED";
  
  @SystemApi
  public static final String ACTION_MANAGE_APP_PERMISSION = "android.intent.action.MANAGE_APP_PERMISSION";
  
  @SystemApi
  public static final String ACTION_MANAGE_APP_PERMISSIONS = "android.intent.action.MANAGE_APP_PERMISSIONS";
  
  @SystemApi
  public static final String ACTION_MANAGE_DEFAULT_APP = "android.intent.action.MANAGE_DEFAULT_APP";
  
  public static final String ACTION_MANAGE_NETWORK_USAGE = "android.intent.action.MANAGE_NETWORK_USAGE";
  
  public static final String ACTION_MANAGE_PACKAGE_STORAGE = "android.intent.action.MANAGE_PACKAGE_STORAGE";
  
  @SystemApi
  public static final String ACTION_MANAGE_PERMISSIONS = "android.intent.action.MANAGE_PERMISSIONS";
  
  @SystemApi
  public static final String ACTION_MANAGE_PERMISSION_APPS = "android.intent.action.MANAGE_PERMISSION_APPS";
  
  @SystemApi
  public static final String ACTION_MANAGE_SPECIAL_APP_ACCESSES = "android.intent.action.MANAGE_SPECIAL_APP_ACCESSES";
  
  @SystemApi
  @Deprecated
  public static final String ACTION_MASTER_CLEAR = "android.intent.action.MASTER_CLEAR";
  
  @SystemApi
  public static final String ACTION_MASTER_CLEAR_NOTIFICATION = "android.intent.action.MASTER_CLEAR_NOTIFICATION";
  
  public static final String ACTION_MEDIA_BAD_REMOVAL = "android.intent.action.MEDIA_BAD_REMOVAL";
  
  public static final String ACTION_MEDIA_BUTTON = "android.intent.action.MEDIA_BUTTON";
  
  public static final String ACTION_MEDIA_CHECKING = "android.intent.action.MEDIA_CHECKING";
  
  public static final String ACTION_MEDIA_EJECT = "android.intent.action.MEDIA_EJECT";
  
  public static final String ACTION_MEDIA_MOUNTED = "android.intent.action.MEDIA_MOUNTED";
  
  public static final String ACTION_MEDIA_NOFS = "android.intent.action.MEDIA_NOFS";
  
  public static final String ACTION_MEDIA_REMOVED = "android.intent.action.MEDIA_REMOVED";
  
  public static final String ACTION_MEDIA_RESOURCE_GRANTED = "android.intent.action.MEDIA_RESOURCE_GRANTED";
  
  public static final String ACTION_MEDIA_SCANNER_FINISHED = "android.intent.action.MEDIA_SCANNER_FINISHED";
  
  @Deprecated
  public static final String ACTION_MEDIA_SCANNER_SCAN_FILE = "android.intent.action.MEDIA_SCANNER_SCAN_FILE";
  
  public static final String ACTION_MEDIA_SCANNER_STARTED = "android.intent.action.MEDIA_SCANNER_STARTED";
  
  public static final String ACTION_MEDIA_SHARED = "android.intent.action.MEDIA_SHARED";
  
  public static final String ACTION_MEDIA_UNMOUNTABLE = "android.intent.action.MEDIA_UNMOUNTABLE";
  
  public static final String ACTION_MEDIA_UNMOUNTED = "android.intent.action.MEDIA_UNMOUNTED";
  
  public static final String ACTION_MEDIA_UNSHARED = "android.intent.action.MEDIA_UNSHARED";
  
  public static final String ACTION_MY_PACKAGE_REPLACED = "android.intent.action.MY_PACKAGE_REPLACED";
  
  public static final String ACTION_MY_PACKAGE_SUSPENDED = "android.intent.action.MY_PACKAGE_SUSPENDED";
  
  public static final String ACTION_MY_PACKAGE_UNSUSPENDED = "android.intent.action.MY_PACKAGE_UNSUSPENDED";
  
  @Deprecated
  public static final String ACTION_NEW_OUTGOING_CALL = "android.intent.action.NEW_OUTGOING_CALL";
  
  public static final String ACTION_OPEN_DOCUMENT = "android.intent.action.OPEN_DOCUMENT";
  
  public static final String ACTION_OPEN_DOCUMENT_TREE = "android.intent.action.OPEN_DOCUMENT_TREE";
  
  public static final String ACTION_OVERLAY_CHANGED = "android.intent.action.OVERLAY_CHANGED";
  
  public static final String ACTION_PACKAGES_SUSPENDED = "android.intent.action.PACKAGES_SUSPENDED";
  
  public static final String ACTION_PACKAGES_UNSUSPENDED = "android.intent.action.PACKAGES_UNSUSPENDED";
  
  public static final String ACTION_PACKAGE_ADDED = "android.intent.action.PACKAGE_ADDED";
  
  public static final String ACTION_PACKAGE_CHANGED = "android.intent.action.PACKAGE_CHANGED";
  
  public static final String ACTION_PACKAGE_DATA_CLEARED = "android.intent.action.PACKAGE_DATA_CLEARED";
  
  public static final String ACTION_PACKAGE_ENABLE_ROLLBACK = "android.intent.action.PACKAGE_ENABLE_ROLLBACK";
  
  public static final String ACTION_PACKAGE_FIRST_LAUNCH = "android.intent.action.PACKAGE_FIRST_LAUNCH";
  
  public static final String ACTION_PACKAGE_FULLY_REMOVED = "android.intent.action.PACKAGE_FULLY_REMOVED";
  
  @Deprecated
  public static final String ACTION_PACKAGE_INSTALL = "android.intent.action.PACKAGE_INSTALL";
  
  @SystemApi
  public static final String ACTION_PACKAGE_NEEDS_INTEGRITY_VERIFICATION = "android.intent.action.PACKAGE_NEEDS_INTEGRITY_VERIFICATION";
  
  public static final String ACTION_PACKAGE_NEEDS_VERIFICATION = "android.intent.action.PACKAGE_NEEDS_VERIFICATION";
  
  public static final String ACTION_PACKAGE_REMOVED = "android.intent.action.PACKAGE_REMOVED";
  
  public static final String ACTION_PACKAGE_REPLACED = "android.intent.action.PACKAGE_REPLACED";
  
  public static final String ACTION_PACKAGE_RESTARTED = "android.intent.action.PACKAGE_RESTARTED";
  
  @SystemApi
  public static final String ACTION_PACKAGE_UNSUSPENDED_MANUALLY = "android.intent.action.PACKAGE_UNSUSPENDED_MANUALLY";
  
  public static final String ACTION_PACKAGE_VERIFIED = "android.intent.action.PACKAGE_VERIFIED";
  
  public static final String ACTION_PASTE = "android.intent.action.PASTE";
  
  @SystemApi
  public static final String ACTION_PENDING_INCIDENT_REPORTS_CHANGED = "android.intent.action.PENDING_INCIDENT_REPORTS_CHANGED";
  
  public static final String ACTION_PICK = "android.intent.action.PICK";
  
  public static final String ACTION_PICK_ACTIVITY = "android.intent.action.PICK_ACTIVITY";
  
  public static final String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";
  
  public static final String ACTION_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED";
  
  public static final String ACTION_POWER_USAGE_SUMMARY = "android.intent.action.POWER_USAGE_SUMMARY";
  
  public static final String ACTION_PREFERRED_ACTIVITY_CHANGED = "android.intent.action.ACTION_PREFERRED_ACTIVITY_CHANGED";
  
  @SystemApi
  public static final String ACTION_PRE_BOOT_COMPLETED = "android.intent.action.PRE_BOOT_COMPLETED";
  
  public static final String ACTION_PROCESS_TEXT = "android.intent.action.PROCESS_TEXT";
  
  public static final String ACTION_PROVIDER_CHANGED = "android.intent.action.PROVIDER_CHANGED";
  
  @SystemApi
  public static final String ACTION_QUERY_PACKAGE_RESTART = "android.intent.action.QUERY_PACKAGE_RESTART";
  
  public static final String ACTION_QUICK_CLOCK = "android.intent.action.QUICK_CLOCK";
  
  public static final String ACTION_QUICK_VIEW = "android.intent.action.QUICK_VIEW";
  
  public static final String ACTION_REBOOT = "android.intent.action.REBOOT";
  
  public static final String ACTION_REMOTE_INTENT = "com.google.android.c2dm.intent.RECEIVE";
  
  public static final String ACTION_REQUEST_SHUTDOWN = "com.android.internal.intent.action.REQUEST_SHUTDOWN";
  
  @SystemApi
  public static final String ACTION_RESOLVE_INSTANT_APP_PACKAGE = "android.intent.action.RESOLVE_INSTANT_APP_PACKAGE";
  
  @SystemApi
  public static final String ACTION_REVIEW_ACCESSIBILITY_SERVICES = "android.intent.action.REVIEW_ACCESSIBILITY_SERVICES";
  
  @SystemApi
  public static final String ACTION_REVIEW_ONGOING_PERMISSION_USAGE = "android.intent.action.REVIEW_ONGOING_PERMISSION_USAGE";
  
  @SystemApi
  public static final String ACTION_REVIEW_PERMISSIONS = "android.intent.action.REVIEW_PERMISSIONS";
  
  @SystemApi
  public static final String ACTION_REVIEW_PERMISSION_USAGE = "android.intent.action.REVIEW_PERMISSION_USAGE";
  
  @SystemApi
  public static final String ACTION_ROLLBACK_COMMITTED = "android.intent.action.ROLLBACK_COMMITTED";
  
  public static final String ACTION_RUN = "android.intent.action.RUN";
  
  public static final String ACTION_SCREEN_OFF = "android.intent.action.SCREEN_OFF";
  
  public static final String ACTION_SCREEN_ON = "android.intent.action.SCREEN_ON";
  
  public static final String ACTION_SEARCH = "android.intent.action.SEARCH";
  
  public static final String ACTION_SEARCH_LONG_PRESS = "android.intent.action.SEARCH_LONG_PRESS";
  
  public static final String ACTION_SEND = "android.intent.action.SEND";
  
  public static final String ACTION_SENDTO = "android.intent.action.SENDTO";
  
  public static final String ACTION_SEND_MULTIPLE = "android.intent.action.SEND_MULTIPLE";
  
  @SystemApi
  @Deprecated
  public static final String ACTION_SERVICE_STATE = "android.intent.action.SERVICE_STATE";
  
  public static final String ACTION_SETTING_RESTORED = "android.os.action.SETTING_RESTORED";
  
  public static final String ACTION_SET_WALLPAPER = "android.intent.action.SET_WALLPAPER";
  
  public static final String ACTION_SHOW_APP_INFO = "android.intent.action.SHOW_APP_INFO";
  
  public static final String ACTION_SHOW_BRIGHTNESS_DIALOG = "com.android.intent.action.SHOW_BRIGHTNESS_DIALOG";
  
  public static final String ACTION_SHOW_KEYBOARD_SHORTCUTS = "com.android.intent.action.SHOW_KEYBOARD_SHORTCUTS";
  
  @SystemApi
  public static final String ACTION_SHOW_SUSPENDED_APP_DETAILS = "android.intent.action.SHOW_SUSPENDED_APP_DETAILS";
  
  public static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
  
  @SystemApi
  @Deprecated
  public static final String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";
  
  @SystemApi
  public static final String ACTION_SPLIT_CONFIGURATION_CHANGED = "android.intent.action.SPLIT_CONFIGURATION_CHANGED";
  
  public static final String ACTION_SYNC = "android.intent.action.SYNC";
  
  public static final String ACTION_SYSTEM_TUTORIAL = "android.intent.action.SYSTEM_TUTORIAL";
  
  public static final String ACTION_THERMAL_EVENT = "android.intent.action.THERMAL_EVENT";
  
  public static final String ACTION_TIMEZONE_CHANGED = "android.intent.action.TIMEZONE_CHANGED";
  
  public static final String ACTION_TIME_CHANGED = "android.intent.action.TIME_SET";
  
  public static final String ACTION_TIME_TICK = "android.intent.action.TIME_TICK";
  
  public static final String ACTION_TRANSLATE = "android.intent.action.TRANSLATE";
  
  public static final String ACTION_UID_REMOVED = "android.intent.action.UID_REMOVED";
  
  @Deprecated
  public static final String ACTION_UMS_CONNECTED = "android.intent.action.UMS_CONNECTED";
  
  @Deprecated
  public static final String ACTION_UMS_DISCONNECTED = "android.intent.action.UMS_DISCONNECTED";
  
  @Deprecated
  public static final String ACTION_UNINSTALL_PACKAGE = "android.intent.action.UNINSTALL_PACKAGE";
  
  @SystemApi
  public static final String ACTION_UPGRADE_SETUP = "android.intent.action.UPGRADE_SETUP";
  
  @SystemApi
  public static final String ACTION_USER_ADDED = "android.intent.action.USER_ADDED";
  
  public static final String ACTION_USER_BACKGROUND = "android.intent.action.USER_BACKGROUND";
  
  public static final String ACTION_USER_FOREGROUND = "android.intent.action.USER_FOREGROUND";
  
  public static final String ACTION_USER_INFO_CHANGED = "android.intent.action.USER_INFO_CHANGED";
  
  public static final String ACTION_USER_INITIALIZE = "android.intent.action.USER_INITIALIZE";
  
  public static final String ACTION_USER_PRESENT = "android.intent.action.USER_PRESENT";
  
  @SystemApi
  public static final String ACTION_USER_REMOVED = "android.intent.action.USER_REMOVED";
  
  public static final String ACTION_USER_STARTED = "android.intent.action.USER_STARTED";
  
  public static final String ACTION_USER_STARTING = "android.intent.action.USER_STARTING";
  
  public static final String ACTION_USER_STOPPED = "android.intent.action.USER_STOPPED";
  
  public static final String ACTION_USER_STOPPING = "android.intent.action.USER_STOPPING";
  
  public static final String ACTION_USER_SWITCHED = "android.intent.action.USER_SWITCHED";
  
  public static final String ACTION_USER_UNLOCKED = "android.intent.action.USER_UNLOCKED";
  
  public static final String ACTION_VIEW = "android.intent.action.VIEW";
  
  public static final String ACTION_VIEW_LOCUS = "android.intent.action.VIEW_LOCUS";
  
  public static final String ACTION_VIEW_PERMISSION_USAGE = "android.intent.action.VIEW_PERMISSION_USAGE";
  
  @SystemApi
  public static final String ACTION_VOICE_ASSIST = "android.intent.action.VOICE_ASSIST";
  
  public static final String ACTION_VOICE_COMMAND = "android.intent.action.VOICE_COMMAND";
  
  @Deprecated
  public static final String ACTION_WALLPAPER_CHANGED = "android.intent.action.WALLPAPER_CHANGED";
  
  public static final String ACTION_WEB_SEARCH = "android.intent.action.WEB_SEARCH";
  
  private static final String ATTR_ACTION = "action";
  
  private static final String ATTR_CATEGORY = "category";
  
  private static final String ATTR_COMPONENT = "component";
  
  private static final String ATTR_DATA = "data";
  
  private static final String ATTR_FLAGS = "flags";
  
  private static final String ATTR_IDENTIFIER = "ident";
  
  private static final String ATTR_TYPE = "type";
  
  public static final String CATEGORY_ACCESSIBILITY_SHORTCUT_TARGET = "android.intent.category.ACCESSIBILITY_SHORTCUT_TARGET";
  
  public static final String CATEGORY_ALTERNATIVE = "android.intent.category.ALTERNATIVE";
  
  public static final String CATEGORY_APP_BROWSER = "android.intent.category.APP_BROWSER";
  
  public static final String CATEGORY_APP_CALCULATOR = "android.intent.category.APP_CALCULATOR";
  
  public static final String CATEGORY_APP_CALENDAR = "android.intent.category.APP_CALENDAR";
  
  public static final String CATEGORY_APP_CONTACTS = "android.intent.category.APP_CONTACTS";
  
  public static final String CATEGORY_APP_EMAIL = "android.intent.category.APP_EMAIL";
  
  public static final String CATEGORY_APP_FILES = "android.intent.category.APP_FILES";
  
  public static final String CATEGORY_APP_GALLERY = "android.intent.category.APP_GALLERY";
  
  public static final String CATEGORY_APP_MAPS = "android.intent.category.APP_MAPS";
  
  public static final String CATEGORY_APP_MARKET = "android.intent.category.APP_MARKET";
  
  public static final String CATEGORY_APP_MESSAGING = "android.intent.category.APP_MESSAGING";
  
  public static final String CATEGORY_APP_MUSIC = "android.intent.category.APP_MUSIC";
  
  public static final String CATEGORY_BROWSABLE = "android.intent.category.BROWSABLE";
  
  public static final String CATEGORY_CAR_DOCK = "android.intent.category.CAR_DOCK";
  
  public static final String CATEGORY_CAR_LAUNCHER = "android.intent.category.CAR_LAUNCHER";
  
  public static final String CATEGORY_CAR_MODE = "android.intent.category.CAR_MODE";
  
  public static final String CATEGORY_DEFAULT = "android.intent.category.DEFAULT";
  
  public static final String CATEGORY_DESK_DOCK = "android.intent.category.DESK_DOCK";
  
  public static final String CATEGORY_DEVELOPMENT_PREFERENCE = "android.intent.category.DEVELOPMENT_PREFERENCE";
  
  public static final String CATEGORY_EMBED = "android.intent.category.EMBED";
  
  public static final String CATEGORY_FRAMEWORK_INSTRUMENTATION_TEST = "android.intent.category.FRAMEWORK_INSTRUMENTATION_TEST";
  
  public static final String CATEGORY_HE_DESK_DOCK = "android.intent.category.HE_DESK_DOCK";
  
  public static final String CATEGORY_HOME = "android.intent.category.HOME";
  
  public static final String CATEGORY_HOME_MAIN = "android.intent.category.HOME_MAIN";
  
  public static final String CATEGORY_INFO = "android.intent.category.INFO";
  
  public static final String CATEGORY_LAUNCHER = "android.intent.category.LAUNCHER";
  
  public static final String CATEGORY_LAUNCHER_APP = "android.intent.category.LAUNCHER_APP";
  
  public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
  
  @SystemApi
  public static final String CATEGORY_LEANBACK_SETTINGS = "android.intent.category.LEANBACK_SETTINGS";
  
  public static final String CATEGORY_LE_DESK_DOCK = "android.intent.category.LE_DESK_DOCK";
  
  public static final String CATEGORY_MONKEY = "android.intent.category.MONKEY";
  
  public static final String CATEGORY_OPENABLE = "android.intent.category.OPENABLE";
  
  public static final String CATEGORY_PREFERENCE = "android.intent.category.PREFERENCE";
  
  public static final String CATEGORY_SAMPLE_CODE = "android.intent.category.SAMPLE_CODE";
  
  public static final String CATEGORY_SECONDARY_HOME = "android.intent.category.SECONDARY_HOME";
  
  public static final String CATEGORY_SELECTED_ALTERNATIVE = "android.intent.category.SELECTED_ALTERNATIVE";
  
  public static final String CATEGORY_SETUP_WIZARD = "android.intent.category.SETUP_WIZARD";
  
  public static final String CATEGORY_TAB = "android.intent.category.TAB";
  
  public static final String CATEGORY_TEST = "android.intent.category.TEST";
  
  public static final String CATEGORY_TYPED_OPENABLE = "android.intent.category.TYPED_OPENABLE";
  
  public static final String CATEGORY_UNIT_TEST = "android.intent.category.UNIT_TEST";
  
  public static final String CATEGORY_VOICE = "android.intent.category.VOICE";
  
  public static final String CATEGORY_VR_HOME = "android.intent.category.VR_HOME";
  
  private static final int COPY_MODE_ALL = 0;
  
  private static final int COPY_MODE_FILTER = 1;
  
  private static final int COPY_MODE_HISTORY = 2;
  
  public static final Parcelable.Creator<Intent> CREATOR = new Parcelable.Creator<Intent>() {
      public Intent createFromParcel(Parcel param1Parcel) {
        return new Intent(param1Parcel);
      }
      
      public Intent[] newArray(int param1Int) {
        return new Intent[param1Int];
      }
    };
  
  public static final String EXTRA_ALARM_COUNT = "android.intent.extra.ALARM_COUNT";
  
  public static final String EXTRA_ALLOW_MULTIPLE = "android.intent.extra.ALLOW_MULTIPLE";
  
  @Deprecated
  public static final String EXTRA_ALLOW_REPLACE = "android.intent.extra.ALLOW_REPLACE";
  
  public static final String EXTRA_ALTERNATE_INTENTS = "android.intent.extra.ALTERNATE_INTENTS";
  
  public static final String EXTRA_ASSIST_CONTEXT = "android.intent.extra.ASSIST_CONTEXT";
  
  public static final String EXTRA_ASSIST_INPUT_DEVICE_ID = "android.intent.extra.ASSIST_INPUT_DEVICE_ID";
  
  public static final String EXTRA_ASSIST_INPUT_HINT_KEYBOARD = "android.intent.extra.ASSIST_INPUT_HINT_KEYBOARD";
  
  public static final String EXTRA_ASSIST_PACKAGE = "android.intent.extra.ASSIST_PACKAGE";
  
  public static final String EXTRA_ASSIST_UID = "android.intent.extra.ASSIST_UID";
  
  public static final String EXTRA_AUTO_LAUNCH_SINGLE_CHOICE = "android.intent.extra.AUTO_LAUNCH_SINGLE_CHOICE";
  
  public static final String EXTRA_BCC = "android.intent.extra.BCC";
  
  public static final String EXTRA_BUG_REPORT = "android.intent.extra.BUG_REPORT";
  
  @SystemApi
  public static final String EXTRA_CALLING_PACKAGE = "android.intent.extra.CALLING_PACKAGE";
  
  public static final String EXTRA_CC = "android.intent.extra.CC";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_CDMA_DEFAULT_ROAMING_INDICATOR = "cdmaDefaultRoamingIndicator";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_CDMA_ROAMING_INDICATOR = "cdmaRoamingIndicator";
  
  @Deprecated
  public static final String EXTRA_CHANGED_COMPONENT_NAME = "android.intent.extra.changed_component_name";
  
  public static final String EXTRA_CHANGED_COMPONENT_NAME_LIST = "android.intent.extra.changed_component_name_list";
  
  public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";
  
  public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";
  
  public static final String EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER = "android.intent.extra.CHOOSER_REFINEMENT_INTENT_SENDER";
  
  public static final String EXTRA_CHOOSER_TARGETS = "android.intent.extra.CHOOSER_TARGETS";
  
  public static final String EXTRA_CHOSEN_COMPONENT = "android.intent.extra.CHOSEN_COMPONENT";
  
  public static final String EXTRA_CHOSEN_COMPONENT_INTENT_SENDER = "android.intent.extra.CHOSEN_COMPONENT_INTENT_SENDER";
  
  public static final String EXTRA_CLIENT_INTENT = "android.intent.extra.client_intent";
  
  public static final String EXTRA_CLIENT_LABEL = "android.intent.extra.client_label";
  
  public static final String EXTRA_COMPONENT_NAME = "android.intent.extra.COMPONENT_NAME";
  
  public static final String EXTRA_CONTENT_ANNOTATIONS = "android.intent.extra.CONTENT_ANNOTATIONS";
  
  public static final String EXTRA_CONTENT_QUERY = "android.intent.extra.CONTENT_QUERY";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_CSS_INDICATOR = "cssIndicator";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_DATA_OPERATOR_ALPHA_LONG = "data-operator-alpha-long";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_DATA_OPERATOR_ALPHA_SHORT = "data-operator-alpha-short";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_DATA_OPERATOR_NUMERIC = "data-operator-numeric";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_DATA_RADIO_TECH = "dataRadioTechnology";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_DATA_REG_STATE = "dataRegState";
  
  public static final String EXTRA_DATA_REMOVED = "android.intent.extra.DATA_REMOVED";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_DATA_ROAMING_TYPE = "dataRoamingType";
  
  public static final String EXTRA_DISTRACTION_RESTRICTIONS = "android.intent.extra.distraction_restrictions";
  
  public static final String EXTRA_DOCK_STATE = "android.intent.extra.DOCK_STATE";
  
  public static final int EXTRA_DOCK_STATE_CAR = 2;
  
  public static final int EXTRA_DOCK_STATE_DESK = 1;
  
  public static final int EXTRA_DOCK_STATE_HE_DESK = 4;
  
  public static final int EXTRA_DOCK_STATE_LE_DESK = 3;
  
  public static final int EXTRA_DOCK_STATE_UNDOCKED = 0;
  
  public static final String EXTRA_DONT_KILL_APP = "android.intent.extra.DONT_KILL_APP";
  
  public static final String EXTRA_DURATION_MILLIS = "android.intent.extra.DURATION_MILLIS";
  
  public static final String EXTRA_EMAIL = "android.intent.extra.EMAIL";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_EMERGENCY_ONLY = "emergencyOnly";
  
  public static final String EXTRA_EXCLUDE_COMPONENTS = "android.intent.extra.EXCLUDE_COMPONENTS";
  
  @SystemApi
  public static final String EXTRA_FORCE_FACTORY_RESET = "android.intent.extra.FORCE_FACTORY_RESET";
  
  @Deprecated
  public static final String EXTRA_FORCE_MASTER_CLEAR = "android.intent.extra.FORCE_MASTER_CLEAR";
  
  public static final String EXTRA_FROM_STORAGE = "android.intent.extra.FROM_STORAGE";
  
  public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
  
  public static final String EXTRA_INDEX = "android.intent.extra.INDEX";
  
  public static final String EXTRA_INITIAL_INTENTS = "android.intent.extra.INITIAL_INTENTS";
  
  public static final String EXTRA_INSTALLER_PACKAGE_NAME = "android.intent.extra.INSTALLER_PACKAGE_NAME";
  
  public static final String EXTRA_INSTALL_RESULT = "android.intent.extra.INSTALL_RESULT";
  
  @SystemApi
  public static final String EXTRA_INSTANT_APP_ACTION = "android.intent.extra.INSTANT_APP_ACTION";
  
  @SystemApi
  public static final String EXTRA_INSTANT_APP_BUNDLES = "android.intent.extra.INSTANT_APP_BUNDLES";
  
  @SystemApi
  public static final String EXTRA_INSTANT_APP_EXTRAS = "android.intent.extra.INSTANT_APP_EXTRAS";
  
  @SystemApi
  public static final String EXTRA_INSTANT_APP_FAILURE = "android.intent.extra.INSTANT_APP_FAILURE";
  
  @SystemApi
  public static final String EXTRA_INSTANT_APP_HOSTNAME = "android.intent.extra.INSTANT_APP_HOSTNAME";
  
  @SystemApi
  public static final String EXTRA_INSTANT_APP_SUCCESS = "android.intent.extra.INSTANT_APP_SUCCESS";
  
  @SystemApi
  public static final String EXTRA_INSTANT_APP_TOKEN = "android.intent.extra.INSTANT_APP_TOKEN";
  
  public static final String EXTRA_INTENT = "android.intent.extra.INTENT";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_IS_DATA_ROAMING_FROM_REGISTRATION = "isDataRoamingFromRegistration";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_IS_USING_CARRIER_AGGREGATION = "isUsingCarrierAggregation";
  
  public static final String EXTRA_KEY_CONFIRM = "android.intent.extra.KEY_CONFIRM";
  
  public static final String EXTRA_KEY_EVENT = "android.intent.extra.KEY_EVENT";
  
  public static final String EXTRA_LOCAL_ONLY = "android.intent.extra.LOCAL_ONLY";
  
  public static final String EXTRA_LOCUS_ID = "android.intent.extra.LOCUS_ID";
  
  @SystemApi
  public static final String EXTRA_LONG_VERSION_CODE = "android.intent.extra.LONG_VERSION_CODE";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_LTE_EARFCN_RSRP_BOOST = "LteEarfcnRsrpBoost";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_MANUAL = "manual";
  
  public static final String EXTRA_MEDIA_RESOURCE_TYPE = "android.intent.extra.MEDIA_RESOURCE_TYPE";
  
  public static final int EXTRA_MEDIA_RESOURCE_TYPE_AUDIO_CODEC = 1;
  
  public static final int EXTRA_MEDIA_RESOURCE_TYPE_VIDEO_CODEC = 0;
  
  public static final String EXTRA_MIME_TYPES = "android.intent.extra.MIME_TYPES";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_NETWORK_ID = "networkId";
  
  public static final String EXTRA_NOT_UNKNOWN_SOURCE = "android.intent.extra.NOT_UNKNOWN_SOURCE";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_OPERATOR_ALPHA_LONG = "operator-alpha-long";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_OPERATOR_ALPHA_SHORT = "operator-alpha-short";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_OPERATOR_NUMERIC = "operator-numeric";
  
  @SystemApi
  public static final String EXTRA_ORIGINATING_UID = "android.intent.extra.ORIGINATING_UID";
  
  public static final String EXTRA_ORIGINATING_URI = "android.intent.extra.ORIGINATING_URI";
  
  @SystemApi
  public static final String EXTRA_PACKAGES = "android.intent.extra.PACKAGES";
  
  public static final String EXTRA_PACKAGE_NAME = "android.intent.extra.PACKAGE_NAME";
  
  @SystemApi
  public static final String EXTRA_PERMISSION_GROUP_NAME = "android.intent.extra.PERMISSION_GROUP_NAME";
  
  @SystemApi
  public static final String EXTRA_PERMISSION_NAME = "android.intent.extra.PERMISSION_NAME";
  
  public static final String EXTRA_PHONE_NUMBER = "android.intent.extra.PHONE_NUMBER";
  
  public static final String EXTRA_PROCESS_TEXT = "android.intent.extra.PROCESS_TEXT";
  
  public static final String EXTRA_PROCESS_TEXT_READONLY = "android.intent.extra.PROCESS_TEXT_READONLY";
  
  @Deprecated
  public static final String EXTRA_QUICK_VIEW_ADVANCED = "android.intent.extra.QUICK_VIEW_ADVANCED";
  
  public static final String EXTRA_QUICK_VIEW_FEATURES = "android.intent.extra.QUICK_VIEW_FEATURES";
  
  public static final String EXTRA_QUIET_MODE = "android.intent.extra.QUIET_MODE";
  
  @SystemApi
  public static final String EXTRA_REASON = "android.intent.extra.REASON";
  
  public static final String EXTRA_REBROADCAST_ON_UNLOCK = "rebroadcastOnUnlock";
  
  public static final String EXTRA_REFERRER = "android.intent.extra.REFERRER";
  
  public static final String EXTRA_REFERRER_NAME = "android.intent.extra.REFERRER_NAME";
  
  @SystemApi
  public static final String EXTRA_REMOTE_CALLBACK = "android.intent.extra.REMOTE_CALLBACK";
  
  public static final String EXTRA_REMOTE_INTENT_TOKEN = "android.intent.extra.remote_intent_token";
  
  public static final String EXTRA_REMOVED_FOR_ALL_USERS = "android.intent.extra.REMOVED_FOR_ALL_USERS";
  
  public static final String EXTRA_REPLACEMENT_EXTRAS = "android.intent.extra.REPLACEMENT_EXTRAS";
  
  public static final String EXTRA_REPLACING = "android.intent.extra.REPLACING";
  
  public static final String EXTRA_RESTRICTIONS_BUNDLE = "android.intent.extra.restrictions_bundle";
  
  public static final String EXTRA_RESTRICTIONS_INTENT = "android.intent.extra.restrictions_intent";
  
  public static final String EXTRA_RESTRICTIONS_LIST = "android.intent.extra.restrictions_list";
  
  @SystemApi
  public static final String EXTRA_RESULT_NEEDED = "android.intent.extra.RESULT_NEEDED";
  
  public static final String EXTRA_RESULT_RECEIVER = "android.intent.extra.RESULT_RECEIVER";
  
  public static final String EXTRA_RETURN_RESULT = "android.intent.extra.RETURN_RESULT";
  
  @SystemApi
  public static final String EXTRA_ROLE_NAME = "android.intent.extra.ROLE_NAME";
  
  public static final String EXTRA_SETTING_NAME = "setting_name";
  
  public static final String EXTRA_SETTING_NEW_VALUE = "new_value";
  
  public static final String EXTRA_SETTING_PREVIOUS_VALUE = "previous_value";
  
  public static final String EXTRA_SETTING_RESTORED_FROM_SDK_INT = "restored_from_sdk_int";
  
  @Deprecated
  public static final String EXTRA_SHORTCUT_ICON = "android.intent.extra.shortcut.ICON";
  
  @Deprecated
  public static final String EXTRA_SHORTCUT_ICON_RESOURCE = "android.intent.extra.shortcut.ICON_RESOURCE";
  
  public static final String EXTRA_SHORTCUT_ID = "android.intent.extra.shortcut.ID";
  
  @Deprecated
  public static final String EXTRA_SHORTCUT_INTENT = "android.intent.extra.shortcut.INTENT";
  
  @Deprecated
  public static final String EXTRA_SHORTCUT_NAME = "android.intent.extra.shortcut.NAME";
  
  public static final String EXTRA_SHUTDOWN_USERSPACE_ONLY = "android.intent.extra.SHUTDOWN_USERSPACE_ONLY";
  
  public static final String EXTRA_SIM_ACTIVATION_RESPONSE = "android.intent.extra.SIM_ACTIVATION_RESPONSE";
  
  public static final String EXTRA_SIM_LOCKED_REASON = "reason";
  
  public static final String EXTRA_SIM_STATE = "ss";
  
  public static final String EXTRA_SPLIT_NAME = "android.intent.extra.SPLIT_NAME";
  
  public static final String EXTRA_STREAM = "android.intent.extra.STREAM";
  
  public static final String EXTRA_SUBJECT = "android.intent.extra.SUBJECT";
  
  public static final String EXTRA_SUSPENDED_PACKAGE_EXTRAS = "android.intent.extra.SUSPENDED_PACKAGE_EXTRAS";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_SYSTEM_ID = "systemId";
  
  public static final String EXTRA_TASK_ID = "android.intent.extra.TASK_ID";
  
  public static final String EXTRA_TEMPLATE = "android.intent.extra.TEMPLATE";
  
  public static final String EXTRA_TEXT = "android.intent.extra.TEXT";
  
  public static final String EXTRA_THERMAL_STATE = "android.intent.extra.THERMAL_STATE";
  
  public static final int EXTRA_THERMAL_STATE_EXCEEDED = 2;
  
  public static final int EXTRA_THERMAL_STATE_NORMAL = 0;
  
  public static final int EXTRA_THERMAL_STATE_WARNING = 1;
  
  public static final String EXTRA_TIME = "android.intent.extra.TIME";
  
  public static final String EXTRA_TIMEZONE = "time-zone";
  
  public static final String EXTRA_TIME_PREF_24_HOUR_FORMAT = "android.intent.extra.TIME_PREF_24_HOUR_FORMAT";
  
  public static final int EXTRA_TIME_PREF_VALUE_USE_12_HOUR = 0;
  
  public static final int EXTRA_TIME_PREF_VALUE_USE_24_HOUR = 1;
  
  public static final int EXTRA_TIME_PREF_VALUE_USE_LOCALE_DEFAULT = 2;
  
  public static final String EXTRA_TITLE = "android.intent.extra.TITLE";
  
  public static final String EXTRA_UID = "android.intent.extra.UID";
  
  public static final String EXTRA_UNINSTALL_ALL_USERS = "android.intent.extra.UNINSTALL_ALL_USERS";
  
  @SystemApi
  public static final String EXTRA_UNKNOWN_INSTANT_APP = "android.intent.extra.UNKNOWN_INSTANT_APP";
  
  public static final String EXTRA_USER = "android.intent.extra.USER";
  
  public static final String EXTRA_USER_HANDLE = "android.intent.extra.user_handle";
  
  public static final String EXTRA_USER_ID = "android.intent.extra.USER_ID";
  
  public static final String EXTRA_USER_REQUESTED_SHUTDOWN = "android.intent.extra.USER_REQUESTED_SHUTDOWN";
  
  @SystemApi
  public static final String EXTRA_VERIFICATION_BUNDLE = "android.intent.extra.VERIFICATION_BUNDLE";
  
  @Deprecated
  public static final String EXTRA_VERSION_CODE = "android.intent.extra.VERSION_CODE";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_VOICE_RADIO_TECH = "radioTechnology";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_VOICE_REG_STATE = "voiceRegState";
  
  @SystemApi
  @Deprecated
  public static final String EXTRA_VOICE_ROAMING_TYPE = "voiceRoamingType";
  
  public static final String EXTRA_WIPE_ESIMS = "com.android.internal.intent.extra.WIPE_ESIMS";
  
  public static final String EXTRA_WIPE_EXTERNAL_STORAGE = "android.intent.extra.WIPE_EXTERNAL_STORAGE";
  
  public static final int FILL_IN_ACTION = 1;
  
  public static final int FILL_IN_CATEGORIES = 4;
  
  public static final int FILL_IN_CLIP_DATA = 128;
  
  public static final int FILL_IN_COMPONENT = 8;
  
  public static final int FILL_IN_DATA = 2;
  
  public static final int FILL_IN_IDENTIFIER = 256;
  
  public static final int FILL_IN_PACKAGE = 16;
  
  public static final int FILL_IN_SELECTOR = 64;
  
  public static final int FILL_IN_SOURCE_BOUNDS = 32;
  
  public static final int FLAG_ACTIVITY_BROUGHT_TO_FRONT = 4194304;
  
  public static final int FLAG_ACTIVITY_CLEAR_TASK = 32768;
  
  public static final int FLAG_ACTIVITY_CLEAR_TOP = 67108864;
  
  @Deprecated
  public static final int FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET = 524288;
  
  public static final int FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS = 8388608;
  
  public static final int FLAG_ACTIVITY_FORWARD_RESULT = 33554432;
  
  public static final int FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY = 1048576;
  
  public static final int FLAG_ACTIVITY_LAUNCH_ADJACENT = 4096;
  
  public static final int FLAG_ACTIVITY_MATCH_EXTERNAL = 2048;
  
  public static final int FLAG_ACTIVITY_MULTIPLE_TASK = 134217728;
  
  public static final int FLAG_ACTIVITY_NEW_DOCUMENT = 524288;
  
  public static final int FLAG_ACTIVITY_NEW_TASK = 268435456;
  
  public static final int FLAG_ACTIVITY_NO_ANIMATION = 65536;
  
  public static final int FLAG_ACTIVITY_NO_HISTORY = 1073741824;
  
  public static final int FLAG_ACTIVITY_NO_USER_ACTION = 262144;
  
  public static final int FLAG_ACTIVITY_PREVIOUS_IS_TOP = 16777216;
  
  public static final int FLAG_ACTIVITY_REORDER_TO_FRONT = 131072;
  
  public static final int FLAG_ACTIVITY_REQUIRE_DEFAULT = 512;
  
  public static final int FLAG_ACTIVITY_REQUIRE_NON_BROWSER = 1024;
  
  public static final int FLAG_ACTIVITY_RESET_TASK_IF_NEEDED = 2097152;
  
  public static final int FLAG_ACTIVITY_RETAIN_IN_RECENTS = 8192;
  
  public static final int FLAG_ACTIVITY_SINGLE_TOP = 536870912;
  
  public static final int FLAG_ACTIVITY_TASK_ON_HOME = 16384;
  
  public static final int FLAG_DEBUG_LOG_RESOLUTION = 8;
  
  @Deprecated
  public static final int FLAG_DEBUG_TRIAGED_MISSING = 256;
  
  public static final int FLAG_DIRECT_BOOT_AUTO = 256;
  
  public static final int FLAG_EXCLUDE_STOPPED_PACKAGES = 16;
  
  public static final int FLAG_FROM_BACKGROUND = 4;
  
  public static final int FLAG_GRANT_PERSISTABLE_URI_PERMISSION = 64;
  
  public static final int FLAG_GRANT_PREFIX_URI_PERMISSION = 128;
  
  public static final int FLAG_GRANT_READ_URI_PERMISSION = 1;
  
  public static final int FLAG_GRANT_WRITE_URI_PERMISSION = 2;
  
  public static final int FLAG_IGNORE_EPHEMERAL = 512;
  
  public static final int FLAG_INCLUDE_STOPPED_PACKAGES = 32;
  
  public static final int FLAG_RECEIVER_BOOT_UPGRADE = 33554432;
  
  public static final int FLAG_RECEIVER_EXCLUDE_BACKGROUND = 8388608;
  
  public static final int FLAG_RECEIVER_FOREGROUND = 268435456;
  
  public static final int FLAG_RECEIVER_FROM_SHELL = 4194304;
  
  public static final int FLAG_RECEIVER_INCLUDE_BACKGROUND = 16777216;
  
  public static final int FLAG_RECEIVER_NO_ABORT = 134217728;
  
  public static final int FLAG_RECEIVER_OFFLOAD = -2147483648;
  
  public static final int FLAG_RECEIVER_REGISTERED_ONLY = 1073741824;
  
  @SystemApi
  public static final int FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT = 67108864;
  
  public static final int FLAG_RECEIVER_REPLACE_PENDING = 536870912;
  
  public static final int FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS = 2097152;
  
  public static final int IMMUTABLE_FLAGS = 195;
  
  public static final String METADATA_DOCK_HOME = "android.dock_home";
  
  @SystemApi
  public static final String METADATA_SETUP_VERSION = "android.SETUP_VERSION";
  
  public static final String SIM_ABSENT_ON_PERM_DISABLED = "PERM_DISABLED";
  
  public static final String SIM_LOCKED_NETWORK = "NETWORK";
  
  public static final String SIM_LOCKED_ON_PIN = "PIN";
  
  public static final String SIM_LOCKED_ON_PUK = "PUK";
  
  public static final String SIM_STATE_ABSENT = "ABSENT";
  
  public static final String SIM_STATE_CARD_IO_ERROR = "CARD_IO_ERROR";
  
  public static final String SIM_STATE_CARD_RESTRICTED = "CARD_RESTRICTED";
  
  public static final String SIM_STATE_IMSI = "IMSI";
  
  public static final String SIM_STATE_LOADED = "LOADED";
  
  public static final String SIM_STATE_LOCKED = "LOCKED";
  
  public static final String SIM_STATE_NOT_READY = "NOT_READY";
  
  public static final String SIM_STATE_PRESENT = "PRESENT";
  
  public static final String SIM_STATE_READY = "READY";
  
  public static final String SIM_STATE_UNKNOWN = "UNKNOWN";
  
  private static final String TAG = "Intent";
  
  private static final String TAG_CATEGORIES = "categories";
  
  private static final String TAG_EXTRA = "extra";
  
  public static final int URI_ALLOW_UNSAFE = 4;
  
  public static final int URI_ANDROID_APP_SCHEME = 2;
  
  public static final int URI_INTENT_SCHEME = 1;
  
  private String mAction;
  
  private ArraySet<String> mCategories;
  
  private ClipData mClipData;
  
  private ComponentName mComponent;
  
  private int mContentUserHint = -2;
  
  private Uri mData;
  
  private Bundle mExtras;
  
  private int mFlags;
  
  private String mIdentifier;
  
  private String mLaunchToken;
  
  private String mPackage;
  
  private Intent mSelector;
  
  private Rect mSourceBounds;
  
  private String mType;
  
  public Intent() {}
  
  public Intent(Context paramContext, Class<?> paramClass) {
    this.mComponent = new ComponentName(paramContext, paramClass);
  }
  
  public Intent(Intent paramIntent) {
    this(paramIntent, 0);
  }
  
  private Intent(Intent paramIntent, int paramInt) {
    this.mAction = paramIntent.mAction;
    this.mData = paramIntent.mData;
    this.mType = paramIntent.mType;
    this.mIdentifier = paramIntent.mIdentifier;
    this.mPackage = paramIntent.mPackage;
    this.mComponent = paramIntent.mComponent;
    if (paramIntent.mCategories != null)
      this.mCategories = new ArraySet(paramIntent.mCategories); 
    if (paramInt != 1) {
      this.mFlags = paramIntent.mFlags;
      this.mContentUserHint = paramIntent.mContentUserHint;
      this.mLaunchToken = paramIntent.mLaunchToken;
      if (paramIntent.mSourceBounds != null)
        this.mSourceBounds = new Rect(paramIntent.mSourceBounds); 
      if (paramIntent.mSelector != null)
        this.mSelector = new Intent(paramIntent.mSelector); 
      if (paramInt != 2) {
        if (paramIntent.mExtras != null)
          this.mExtras = new Bundle(paramIntent.mExtras); 
        if (paramIntent.mClipData != null)
          this.mClipData = new ClipData(paramIntent.mClipData); 
      } else {
        Bundle bundle = paramIntent.mExtras;
        if (bundle != null && !bundle.isDefinitelyEmpty())
          this.mExtras = Bundle.STRIPPED; 
      } 
    } 
  }
  
  protected Intent(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public Intent(String paramString) {
    setAction(paramString);
  }
  
  public Intent(String paramString, Uri paramUri) {
    setAction(paramString);
    this.mData = paramUri;
  }
  
  public Intent(String paramString, Uri paramUri, Context paramContext, Class<?> paramClass) {
    setAction(paramString);
    this.mData = paramUri;
    this.mComponent = new ComponentName(paramContext, paramClass);
  }
  
  public static Intent createChooser(Intent paramIntent, CharSequence paramCharSequence) {
    return createChooser(paramIntent, paramCharSequence, null);
  }
  
  public static Intent createChooser(Intent paramIntent, CharSequence paramCharSequence, IntentSender paramIntentSender) {
    Intent intent = new Intent("android.intent.action.CHOOSER");
    intent.putExtra("android.intent.extra.INTENT", paramIntent);
    if (paramCharSequence != null)
      intent.putExtra("android.intent.extra.TITLE", paramCharSequence); 
    if (paramIntentSender != null)
      intent.putExtra("android.intent.extra.CHOSEN_COMPONENT_INTENT_SENDER", paramIntentSender); 
    int i = paramIntent.getFlags() & 0xC3;
    if (i != 0) {
      ClipData clipData2 = paramIntent.getClipData();
      ClipData clipData1 = clipData2;
      if (clipData2 == null) {
        clipData1 = clipData2;
        if (paramIntent.getData() != null) {
          String[] arrayOfString;
          ClipData.Item item = new ClipData.Item(paramIntent.getData());
          if (paramIntent.getType() != null) {
            String[] arrayOfString1 = new String[1];
            arrayOfString1[0] = paramIntent.getType();
            arrayOfString = arrayOfString1;
          } else {
            arrayOfString = new String[0];
          } 
          clipData1 = new ClipData(null, arrayOfString, item);
        } 
      } 
      if (clipData1 != null) {
        intent.setClipData(clipData1);
        intent.addFlags(i);
      } 
    } 
    return intent;
  }
  
  public static String dockStateToString(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? Integer.toString(paramInt) : "EXTRA_DOCK_STATE_HE_DESK") : "EXTRA_DOCK_STATE_LE_DESK") : "EXTRA_DOCK_STATE_CAR") : "EXTRA_DOCK_STATE_DESK") : "EXTRA_DOCK_STATE_UNDOCKED";
  }
  
  private void dumpDebugWithoutFieldId(ProtoOutputStream paramProtoOutputStream, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    String str2 = this.mAction;
    if (str2 != null)
      paramProtoOutputStream.write(1138166333441L, str2); 
    ArraySet<String> arraySet = this.mCategories;
    if (arraySet != null) {
      Iterator<String> iterator = arraySet.iterator();
      while (iterator.hasNext())
        paramProtoOutputStream.write(2237677961218L, iterator.next()); 
    } 
    Uri uri = this.mData;
    if (uri != null) {
      String str;
      if (paramBoolean1) {
        str = uri.toSafeString();
      } else {
        str = str.toString();
      } 
      paramProtoOutputStream.write(1138166333443L, str);
    } 
    String str1 = this.mType;
    if (str1 != null)
      paramProtoOutputStream.write(1138166333444L, str1); 
    str1 = this.mIdentifier;
    if (str1 != null)
      paramProtoOutputStream.write(1138166333453L, str1); 
    if (this.mFlags != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(this.mFlags));
      paramProtoOutputStream.write(1138166333445L, stringBuilder.toString());
    } 
    str1 = this.mPackage;
    if (str1 != null)
      paramProtoOutputStream.write(1138166333446L, str1); 
    if (paramBoolean2) {
      ComponentName componentName = this.mComponent;
      if (componentName != null)
        componentName.dumpDebug(paramProtoOutputStream, 1146756268039L); 
    } 
    Rect rect = this.mSourceBounds;
    if (rect != null)
      paramProtoOutputStream.write(1138166333448L, rect.toShortString()); 
    if (this.mClipData != null) {
      StringBuilder stringBuilder = new StringBuilder();
      if (paramBoolean4) {
        this.mClipData.toShortString(stringBuilder);
      } else {
        this.mClipData.toShortStringShortItems(stringBuilder, false);
      } 
      paramProtoOutputStream.write(1138166333449L, stringBuilder.toString());
    } 
    if (paramBoolean3) {
      Bundle bundle = this.mExtras;
      if (bundle != null)
        paramProtoOutputStream.write(1138166333450L, bundle.toShortString()); 
    } 
    int i = this.mContentUserHint;
    if (i != 0)
      paramProtoOutputStream.write(1120986464267L, i); 
    Intent intent = this.mSelector;
    if (intent != null)
      paramProtoOutputStream.write(1138166333452L, intent.toShortString(paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4)); 
  }
  
  @Deprecated
  public static Intent getIntent(String paramString) throws URISyntaxException {
    return parseUri(paramString, 0);
  }
  
  public static Intent getIntentOld(String paramString) throws URISyntaxException {
    return getIntentOld(paramString, 0);
  }
  
  private static Intent getIntentOld(String paramString, int paramInt) throws URISyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: bipush #35
    //   3: invokevirtual lastIndexOf : (I)I
    //   6: istore_2
    //   7: iload_2
    //   8: iflt -> 969
    //   11: aconst_null
    //   12: astore_3
    //   13: iconst_0
    //   14: istore #4
    //   16: iload_2
    //   17: iconst_1
    //   18: iadd
    //   19: istore #5
    //   21: iload #5
    //   23: istore #6
    //   25: aload_0
    //   26: iload #5
    //   28: ldc_w 'action('
    //   31: iconst_0
    //   32: bipush #7
    //   34: invokevirtual regionMatches : (ILjava/lang/String;II)Z
    //   37: ifeq -> 75
    //   40: iconst_1
    //   41: istore #4
    //   43: iload #5
    //   45: bipush #7
    //   47: iadd
    //   48: istore #6
    //   50: aload_0
    //   51: bipush #41
    //   53: iload #6
    //   55: invokevirtual indexOf : (II)I
    //   58: istore #5
    //   60: aload_0
    //   61: iload #6
    //   63: iload #5
    //   65: invokevirtual substring : (II)Ljava/lang/String;
    //   68: astore_3
    //   69: iload #5
    //   71: iconst_1
    //   72: iadd
    //   73: istore #6
    //   75: new android/content/Intent
    //   78: dup
    //   79: aload_3
    //   80: invokespecial <init> : (Ljava/lang/String;)V
    //   83: astore_3
    //   84: iload #6
    //   86: istore #5
    //   88: aload_0
    //   89: iload #6
    //   91: ldc_w 'categories('
    //   94: iconst_0
    //   95: bipush #11
    //   97: invokevirtual regionMatches : (ILjava/lang/String;II)Z
    //   100: ifeq -> 191
    //   103: iconst_1
    //   104: istore #4
    //   106: iinc #6, 11
    //   109: aload_0
    //   110: bipush #41
    //   112: iload #6
    //   114: invokevirtual indexOf : (II)I
    //   117: istore #7
    //   119: iload #6
    //   121: iload #7
    //   123: if_icmpge -> 185
    //   126: aload_0
    //   127: bipush #33
    //   129: iload #6
    //   131: invokevirtual indexOf : (II)I
    //   134: istore #8
    //   136: iload #8
    //   138: iflt -> 152
    //   141: iload #8
    //   143: istore #5
    //   145: iload #8
    //   147: iload #7
    //   149: if_icmple -> 156
    //   152: iload #7
    //   154: istore #5
    //   156: iload #6
    //   158: iload #5
    //   160: if_icmpge -> 176
    //   163: aload_3
    //   164: aload_0
    //   165: iload #6
    //   167: iload #5
    //   169: invokevirtual substring : (II)Ljava/lang/String;
    //   172: invokevirtual addCategory : (Ljava/lang/String;)Landroid/content/Intent;
    //   175: pop
    //   176: iload #5
    //   178: iconst_1
    //   179: iadd
    //   180: istore #6
    //   182: goto -> 119
    //   185: iload #7
    //   187: iconst_1
    //   188: iadd
    //   189: istore #5
    //   191: iload #5
    //   193: istore #6
    //   195: aload_0
    //   196: iload #5
    //   198: ldc_w 'type('
    //   201: iconst_0
    //   202: iconst_5
    //   203: invokevirtual regionMatches : (ILjava/lang/String;II)Z
    //   206: ifeq -> 240
    //   209: iconst_1
    //   210: istore #4
    //   212: iinc #5, 5
    //   215: aload_0
    //   216: bipush #41
    //   218: iload #5
    //   220: invokevirtual indexOf : (II)I
    //   223: istore #6
    //   225: aload_3
    //   226: aload_0
    //   227: iload #5
    //   229: iload #6
    //   231: invokevirtual substring : (II)Ljava/lang/String;
    //   234: putfield mType : Ljava/lang/String;
    //   237: iinc #6, 1
    //   240: iload #6
    //   242: istore #5
    //   244: aload_0
    //   245: iload #6
    //   247: ldc_w 'launchFlags('
    //   250: iconst_0
    //   251: bipush #12
    //   253: invokevirtual regionMatches : (ILjava/lang/String;II)Z
    //   256: ifeq -> 323
    //   259: iconst_1
    //   260: istore #4
    //   262: iload #6
    //   264: bipush #12
    //   266: iadd
    //   267: istore #5
    //   269: aload_0
    //   270: bipush #41
    //   272: iload #5
    //   274: invokevirtual indexOf : (II)I
    //   277: istore #6
    //   279: aload_0
    //   280: iload #5
    //   282: iload #6
    //   284: invokevirtual substring : (II)Ljava/lang/String;
    //   287: invokestatic decode : (Ljava/lang/String;)Ljava/lang/Integer;
    //   290: invokevirtual intValue : ()I
    //   293: istore #5
    //   295: aload_3
    //   296: iload #5
    //   298: putfield mFlags : I
    //   301: iload_1
    //   302: iconst_4
    //   303: iand
    //   304: ifne -> 317
    //   307: aload_3
    //   308: iload #5
    //   310: sipush #-196
    //   313: iand
    //   314: putfield mFlags : I
    //   317: iload #6
    //   319: iconst_1
    //   320: iadd
    //   321: istore #5
    //   323: iload #5
    //   325: istore_1
    //   326: aload_0
    //   327: iload #5
    //   329: ldc_w 'component('
    //   332: iconst_0
    //   333: bipush #10
    //   335: invokevirtual regionMatches : (ILjava/lang/String;II)Z
    //   338: ifeq -> 413
    //   341: iconst_1
    //   342: istore #4
    //   344: iload #5
    //   346: bipush #10
    //   348: iadd
    //   349: istore_1
    //   350: aload_0
    //   351: bipush #41
    //   353: iload_1
    //   354: invokevirtual indexOf : (II)I
    //   357: istore #5
    //   359: aload_0
    //   360: bipush #33
    //   362: iload_1
    //   363: invokevirtual indexOf : (II)I
    //   366: istore #6
    //   368: iload #6
    //   370: iflt -> 408
    //   373: iload #6
    //   375: iload #5
    //   377: if_icmpge -> 408
    //   380: aload_3
    //   381: new android/content/ComponentName
    //   384: dup
    //   385: aload_0
    //   386: iload_1
    //   387: iload #6
    //   389: invokevirtual substring : (II)Ljava/lang/String;
    //   392: aload_0
    //   393: iload #6
    //   395: iconst_1
    //   396: iadd
    //   397: iload #5
    //   399: invokevirtual substring : (II)Ljava/lang/String;
    //   402: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   405: putfield mComponent : Landroid/content/ComponentName;
    //   408: iload #5
    //   410: iconst_1
    //   411: iadd
    //   412: istore_1
    //   413: aload_0
    //   414: iload_1
    //   415: ldc_w 'extras('
    //   418: iconst_0
    //   419: bipush #7
    //   421: invokevirtual regionMatches : (ILjava/lang/String;II)Z
    //   424: ifeq -> 922
    //   427: iconst_1
    //   428: istore #6
    //   430: iinc #1, 7
    //   433: aload_0
    //   434: bipush #41
    //   436: iload_1
    //   437: invokevirtual indexOf : (II)I
    //   440: istore #5
    //   442: iload #5
    //   444: iconst_m1
    //   445: if_icmpeq -> 909
    //   448: iload #6
    //   450: istore #4
    //   452: iload_1
    //   453: iload #5
    //   455: if_icmpge -> 922
    //   458: aload_0
    //   459: bipush #61
    //   461: iload_1
    //   462: invokevirtual indexOf : (II)I
    //   465: istore #4
    //   467: iload #4
    //   469: iload_1
    //   470: iconst_1
    //   471: iadd
    //   472: if_icmple -> 896
    //   475: iload_1
    //   476: iload #5
    //   478: if_icmpge -> 896
    //   481: aload_0
    //   482: iload_1
    //   483: invokevirtual charAt : (I)C
    //   486: istore #7
    //   488: aload_0
    //   489: iload_1
    //   490: iconst_1
    //   491: iadd
    //   492: iload #4
    //   494: invokevirtual substring : (II)Ljava/lang/String;
    //   497: astore #9
    //   499: iload #4
    //   501: iconst_1
    //   502: iadd
    //   503: istore #8
    //   505: aload_0
    //   506: bipush #33
    //   508: iload #8
    //   510: invokevirtual indexOf : (II)I
    //   513: istore #4
    //   515: iload #4
    //   517: iconst_m1
    //   518: if_icmpeq -> 531
    //   521: iload #4
    //   523: istore_1
    //   524: iload #4
    //   526: iload #5
    //   528: if_icmplt -> 534
    //   531: iload #5
    //   533: istore_1
    //   534: iload #8
    //   536: iload_1
    //   537: if_icmpge -> 882
    //   540: aload_0
    //   541: iload #8
    //   543: iload_1
    //   544: invokevirtual substring : (II)Ljava/lang/String;
    //   547: astore #10
    //   549: aload_3
    //   550: getfield mExtras : Landroid/os/Bundle;
    //   553: ifnonnull -> 567
    //   556: aload_3
    //   557: new android/os/Bundle
    //   560: dup
    //   561: invokespecial <init> : ()V
    //   564: putfield mExtras : Landroid/os/Bundle;
    //   567: iload #7
    //   569: bipush #66
    //   571: if_icmpeq -> 807
    //   574: iload #7
    //   576: bipush #83
    //   578: if_icmpeq -> 790
    //   581: iload #7
    //   583: bipush #102
    //   585: if_icmpeq -> 773
    //   588: iload #7
    //   590: bipush #105
    //   592: if_icmpeq -> 756
    //   595: iload #7
    //   597: bipush #108
    //   599: if_icmpeq -> 739
    //   602: iload #7
    //   604: bipush #115
    //   606: if_icmpeq -> 722
    //   609: iload #7
    //   611: tableswitch default -> 636, 98 -> 705, 99 -> 684, 100 -> 659
    //   636: new java/net/URISyntaxException
    //   639: astore_3
    //   640: aload_3
    //   641: aload_0
    //   642: ldc_w 'EXTRA has unknown type'
    //   645: iload_1
    //   646: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;I)V
    //   649: aload_3
    //   650: athrow
    //   651: astore_3
    //   652: goto -> 869
    //   655: astore_3
    //   656: goto -> 869
    //   659: aload_3
    //   660: getfield mExtras : Landroid/os/Bundle;
    //   663: astore #11
    //   665: aload #11
    //   667: aload #9
    //   669: aload #10
    //   671: invokestatic parseDouble : (Ljava/lang/String;)D
    //   674: invokevirtual putDouble : (Ljava/lang/String;D)V
    //   677: goto -> 821
    //   680: astore_3
    //   681: goto -> 869
    //   684: aload_3
    //   685: getfield mExtras : Landroid/os/Bundle;
    //   688: aload #9
    //   690: aload #10
    //   692: invokestatic decode : (Ljava/lang/String;)Ljava/lang/String;
    //   695: iconst_0
    //   696: invokevirtual charAt : (I)C
    //   699: invokevirtual putChar : (Ljava/lang/String;C)V
    //   702: goto -> 821
    //   705: aload_3
    //   706: getfield mExtras : Landroid/os/Bundle;
    //   709: aload #9
    //   711: aload #10
    //   713: invokestatic parseByte : (Ljava/lang/String;)B
    //   716: invokevirtual putByte : (Ljava/lang/String;B)V
    //   719: goto -> 821
    //   722: aload_3
    //   723: getfield mExtras : Landroid/os/Bundle;
    //   726: aload #9
    //   728: aload #10
    //   730: invokestatic parseShort : (Ljava/lang/String;)S
    //   733: invokevirtual putShort : (Ljava/lang/String;S)V
    //   736: goto -> 821
    //   739: aload_3
    //   740: getfield mExtras : Landroid/os/Bundle;
    //   743: aload #9
    //   745: aload #10
    //   747: invokestatic parseLong : (Ljava/lang/String;)J
    //   750: invokevirtual putLong : (Ljava/lang/String;J)V
    //   753: goto -> 821
    //   756: aload_3
    //   757: getfield mExtras : Landroid/os/Bundle;
    //   760: aload #9
    //   762: aload #10
    //   764: invokestatic parseInt : (Ljava/lang/String;)I
    //   767: invokevirtual putInt : (Ljava/lang/String;I)V
    //   770: goto -> 821
    //   773: aload_3
    //   774: getfield mExtras : Landroid/os/Bundle;
    //   777: aload #9
    //   779: aload #10
    //   781: invokestatic parseFloat : (Ljava/lang/String;)F
    //   784: invokevirtual putFloat : (Ljava/lang/String;F)V
    //   787: goto -> 821
    //   790: aload_3
    //   791: getfield mExtras : Landroid/os/Bundle;
    //   794: aload #9
    //   796: aload #10
    //   798: invokestatic decode : (Ljava/lang/String;)Ljava/lang/String;
    //   801: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   804: goto -> 821
    //   807: aload_3
    //   808: getfield mExtras : Landroid/os/Bundle;
    //   811: aload #9
    //   813: aload #10
    //   815: invokestatic parseBoolean : (Ljava/lang/String;)Z
    //   818: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   821: aload_0
    //   822: iload_1
    //   823: invokevirtual charAt : (I)C
    //   826: istore #4
    //   828: iload #4
    //   830: bipush #41
    //   832: if_icmpne -> 842
    //   835: iload #6
    //   837: istore #4
    //   839: goto -> 922
    //   842: iload #4
    //   844: bipush #33
    //   846: if_icmpne -> 855
    //   849: iinc #1, 1
    //   852: goto -> 448
    //   855: new java/net/URISyntaxException
    //   858: dup
    //   859: aload_0
    //   860: ldc_w 'EXTRA missing '!''
    //   863: iload_1
    //   864: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;I)V
    //   867: athrow
    //   868: astore_3
    //   869: new java/net/URISyntaxException
    //   872: dup
    //   873: aload_0
    //   874: ldc_w 'EXTRA value can't be parsed'
    //   877: iload_1
    //   878: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;I)V
    //   881: athrow
    //   882: new java/net/URISyntaxException
    //   885: dup
    //   886: aload_0
    //   887: ldc_w 'EXTRA missing '!''
    //   890: iload #8
    //   892: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;I)V
    //   895: athrow
    //   896: new java/net/URISyntaxException
    //   899: dup
    //   900: aload_0
    //   901: ldc_w 'EXTRA missing '=''
    //   904: iload_1
    //   905: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;I)V
    //   908: athrow
    //   909: new java/net/URISyntaxException
    //   912: dup
    //   913: aload_0
    //   914: ldc_w 'EXTRA missing trailing ')''
    //   917: iload_1
    //   918: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;I)V
    //   921: athrow
    //   922: iload #4
    //   924: ifeq -> 943
    //   927: aload_3
    //   928: aload_0
    //   929: iconst_0
    //   930: iload_2
    //   931: invokevirtual substring : (II)Ljava/lang/String;
    //   934: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
    //   937: putfield mData : Landroid/net/Uri;
    //   940: goto -> 951
    //   943: aload_3
    //   944: aload_0
    //   945: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
    //   948: putfield mData : Landroid/net/Uri;
    //   951: aload_3
    //   952: getfield mAction : Ljava/lang/String;
    //   955: ifnonnull -> 964
    //   958: aload_3
    //   959: ldc 'android.intent.action.VIEW'
    //   961: putfield mAction : Ljava/lang/String;
    //   964: aload_3
    //   965: astore_0
    //   966: goto -> 983
    //   969: new android/content/Intent
    //   972: dup
    //   973: ldc 'android.intent.action.VIEW'
    //   975: aload_0
    //   976: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
    //   979: invokespecial <init> : (Ljava/lang/String;Landroid/net/Uri;)V
    //   982: astore_0
    //   983: aload_0
    //   984: areturn
    // Exception table:
    //   from	to	target	type
    //   636	640	655	java/lang/NumberFormatException
    //   640	651	651	java/lang/NumberFormatException
    //   659	665	680	java/lang/NumberFormatException
    //   665	677	868	java/lang/NumberFormatException
    //   684	702	868	java/lang/NumberFormatException
    //   705	719	868	java/lang/NumberFormatException
    //   722	736	868	java/lang/NumberFormatException
    //   739	753	868	java/lang/NumberFormatException
    //   756	770	868	java/lang/NumberFormatException
    //   773	787	868	java/lang/NumberFormatException
    //   790	804	868	java/lang/NumberFormatException
    //   807	821	868	java/lang/NumberFormatException
  }
  
  private boolean hasPackageEquivalentComponent() {
    ComponentName componentName = this.mComponent;
    if (componentName != null) {
      String str = this.mPackage;
      if (str == null || str.equals(componentName.getPackageName()))
        return true; 
    } 
    return false;
  }
  
  public static boolean isAccessUriMode(int paramInt) {
    boolean bool;
    if ((paramInt & 0x3) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isImageCaptureIntent() {
    return ("android.media.action.IMAGE_CAPTURE".equals(this.mAction) || "android.media.action.IMAGE_CAPTURE_SECURE".equals(this.mAction) || "android.media.action.VIDEO_CAPTURE".equals(this.mAction));
  }
  
  private static ClipData.Item makeClipItem(ArrayList<Uri> paramArrayList, ArrayList<CharSequence> paramArrayList1, ArrayList<String> paramArrayList2, int paramInt) {
    if (paramArrayList != null) {
      Uri uri = paramArrayList.get(paramInt);
    } else {
      paramArrayList = null;
    } 
    if (paramArrayList1 != null) {
      CharSequence charSequence = paramArrayList1.get(paramInt);
    } else {
      paramArrayList1 = null;
    } 
    if (paramArrayList2 != null) {
      String str = paramArrayList2.get(paramInt);
    } else {
      paramArrayList2 = null;
    } 
    return new ClipData.Item((CharSequence)paramArrayList1, (String)paramArrayList2, null, (Uri)paramArrayList);
  }
  
  public static Intent makeMainActivity(ComponentName paramComponentName) {
    Intent intent = new Intent("android.intent.action.MAIN");
    intent.setComponent(paramComponentName);
    intent.addCategory("android.intent.category.LAUNCHER");
    return intent;
  }
  
  public static Intent makeMainSelectorActivity(String paramString1, String paramString2) {
    Intent intent1 = new Intent("android.intent.action.MAIN");
    intent1.addCategory("android.intent.category.LAUNCHER");
    Intent intent2 = new Intent();
    intent2.setAction(paramString1);
    intent2.addCategory(paramString2);
    intent1.setSelector(intent2);
    return intent1;
  }
  
  public static Intent makeRestartActivityTask(ComponentName paramComponentName) {
    Intent intent = makeMainActivity(paramComponentName);
    intent.addFlags(268468224);
    return intent;
  }
  
  private Uri maybeConvertFileToContentUri(Context paramContext, Uri paramUri) {
    Uri uri = paramUri;
    if ("file".equals(paramUri.getScheme())) {
      uri = paramUri;
      if ((paramContext.getApplicationInfo()).targetSdkVersion < 30) {
        File file = new File(paramUri.getPath());
        try {
          if (!file.exists())
            file.createNewFile(); 
          ContentResolver contentResolver = paramContext.getContentResolver();
          File file1 = new File();
          this(paramUri.getPath());
          Uri uri1 = MediaStore.scanFile(contentResolver, file1);
          if (uri1 != null)
            return uri1; 
        } catch (IOException iOException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Ignoring failure to create file ");
          stringBuilder.append(file);
          Log.e("Intent", stringBuilder.toString(), iOException);
          uri = paramUri;
        } 
      } 
    } 
    return uri;
  }
  
  public static String normalizeMimeType(String paramString) {
    if (paramString == null)
      return null; 
    String str = paramString.trim().toLowerCase(Locale.ROOT);
    int i = str.indexOf(';');
    paramString = str;
    if (i != -1)
      paramString = str.substring(0, i); 
    return paramString;
  }
  
  public static Intent parseCommandArgs(ShellCommand paramShellCommand, CommandOptionHandler paramCommandOptionHandler) throws URISyntaxException {
    Intent intent1 = new Intent();
    String str = null;
    Uri uri = null;
    int i = 0;
    Intent intent2 = intent1;
    while (true) {
      Intent intent3;
      String str2 = paramShellCommand.getNextOption();
      int j = 47;
      if (str2 != null) {
        String[] arrayOfString2;
        String str4;
        String[] arrayOfString1;
        String str3;
        ComponentName componentName2;
        String str6;
        ArrayList<Float> arrayList;
        float[] arrayOfFloat;
        String[] arrayOfString3;
        long[] arrayOfLong;
        String str5;
        ComponentName componentName1;
        boolean bool;
        ArrayList<String> arrayList3;
        String[] arrayOfString4;
        ArrayList<Long> arrayList2;
        String str8;
        ArrayList<Integer> arrayList1;
        int[] arrayOfInt;
        String str7;
        switch (str2.hashCode()) {
          default:
            j = -1;
            break;
          case 1816558127:
            if (str2.equals("--grant-write-uri-permission")) {
              j = 26;
              break;
            } 
          case 1765369476:
            if (str2.equals("--activity-multiple-task")) {
              j = 37;
              break;
            } 
          case 1742380566:
            if (str2.equals("--grant-read-uri-permission")) {
              j = 25;
              break;
            } 
          case 1652786753:
            if (str2.equals("--receiver-foreground")) {
              j = 50;
              break;
            } 
          case 1453225122:
            if (str2.equals("--receiver-no-abort")) {
              j = 51;
              break;
            } 
          case 1398403374:
            if (str2.equals("--activity-launched-from-history")) {
              j = 36;
              break;
            } 
          case 1353919836:
            if (str2.equals("--activity-clear-when-task-reset")) {
              j = 34;
              break;
            } 
          case 1332992761:
            if (str2.equals("--esal")) {
              j = 20;
              break;
            } 
          case 1332986034:
            if (str2.equals("--elal")) {
              j = 15;
              break;
            } 
          case 1332983151:
            if (str2.equals("--eial")) {
              j = 12;
              break;
            } 
          case 1332980268:
            if (str2.equals("--efal")) {
              j = 18;
              break;
            } 
          case 1207327103:
            if (str2.equals("--selector")) {
              j = 53;
              break;
            } 
          case 1110195121:
            if (str2.equals("--activity-match-external"))
              break; 
          case 775126336:
            if (str2.equals("--receiver-replace-pending")) {
              j = 49;
              break;
            } 
          case 749648146:
            if (str2.equals("--include-stopped-packages")) {
              j = 30;
              break;
            } 
          case 580418080:
            if (str2.equals("--exclude-stopped-packages")) {
              j = 29;
              break;
            } 
          case 527014976:
            if (str2.equals("--grant-persistable-uri-permission")) {
              j = 27;
              break;
            } 
          case 438531630:
            if (str2.equals("--activity-single-top")) {
              j = 44;
              break;
            } 
          case 436286937:
            if (str2.equals("--receiver-registered-only")) {
              j = 48;
              break;
            } 
          case 429439306:
            if (str2.equals("--activity-no-user-action")) {
              j = 40;
              break;
            } 
          case 236677687:
            if (str2.equals("--activity-clear-top")) {
              j = 33;
              break;
            } 
          case 190913209:
            if (str2.equals("--activity-reset-task-if-needed")) {
              j = 43;
              break;
            } 
          case 88747734:
            if (str2.equals("--activity-no-animation")) {
              j = 38;
              break;
            } 
          case 69120454:
            if (str2.equals("--activity-exclude-from-recents")) {
              j = 35;
              break;
            } 
          case 42999776:
            if (str2.equals("--esn")) {
              j = 7;
              break;
            } 
          case 42999763:
            if (str2.equals("--esa")) {
              j = 19;
              break;
            } 
          case 42999546:
            if (str2.equals("--ela")) {
              j = 14;
              break;
            } 
          case 42999453:
            if (str2.equals("--eia")) {
              j = 11;
              break;
            } 
          case 42999360:
            if (str2.equals("--efa")) {
              j = 17;
              break;
            } 
          case 42999280:
            if (str2.equals("--ecn")) {
              j = 10;
              break;
            } 
          case 1387093:
            if (str2.equals("--ez")) {
              j = 21;
              break;
            } 
          case 1387088:
            if (str2.equals("--eu")) {
              j = 9;
              break;
            } 
          case 1387086:
            if (str2.equals("--es")) {
              j = 6;
              break;
            } 
          case 1387079:
            if (str2.equals("--el")) {
              j = 13;
              break;
            } 
          case 1387076:
            if (str2.equals("--ei")) {
              j = 8;
              break;
            } 
          case 1387073:
            if (str2.equals("--ef")) {
              j = 16;
              break;
            } 
          case 1511:
            if (str2.equals("-t")) {
              j = 2;
              break;
            } 
          case 1507:
            if (str2.equals("-p")) {
              j = 23;
              break;
            } 
          case 1505:
            if (str2.equals("-n")) {
              j = 22;
              break;
            } 
          case 1500:
            if (str2.equals("-i")) {
              j = 3;
              break;
            } 
          case 1497:
            if (str2.equals("-f")) {
              j = 24;
              break;
            } 
          case 1496:
            if (str2.equals("-e")) {
              j = 5;
              break;
            } 
          case 1495:
            if (str2.equals("-d")) {
              j = 1;
              break;
            } 
          case 1494:
            if (str2.equals("-c")) {
              j = 4;
              break;
            } 
          case 1492:
            if (str2.equals("-a")) {
              j = 0;
              break;
            } 
          case -780160399:
            if (str2.equals("--receiver-include-background")) {
              j = 52;
              break;
            } 
          case -792169302:
            if (str2.equals("--activity-previous-is-top")) {
              j = 41;
              break;
            } 
          case -833172539:
            if (str2.equals("--activity-brought-to-front")) {
              j = 32;
              break;
            } 
          case -848214457:
            if (str2.equals("--activity-reorder-to-front")) {
              j = 42;
              break;
            } 
          case -1069446353:
            if (str2.equals("--debug-log-resolution")) {
              j = 31;
              break;
            } 
          case -1252939549:
            if (str2.equals("--activity-clear-task")) {
              j = 45;
              break;
            } 
          case -1630559130:
            if (str2.equals("--activity-no-history")) {
              j = 39;
              break;
            } 
          case -2118172637:
            if (str2.equals("--activity-task-on-home")) {
              j = 46;
              break;
            } 
          case -2147394086:
            if (str2.equals("--grant-prefix-uri-permission")) {
              j = 28;
              break;
            } 
        } 
        switch (j) {
          default:
            if (paramCommandOptionHandler != null) {
              if (paramCommandOptionHandler.handleOption(str2, paramShellCommand)) {
                j = i;
                break;
              } 
              continue;
            } 
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown option: ");
            stringBuilder.append(str2);
            throw new IllegalArgumentException(stringBuilder.toString());
          case 53:
            intent2.setDataAndType(uri, str);
            intent2 = new Intent();
            j = i;
            break;
          case 52:
            intent2.addFlags(16777216);
            j = i;
            break;
          case 51:
            intent2.addFlags(134217728);
            j = i;
            break;
          case 50:
            intent2.addFlags(268435456);
            j = i;
            break;
          case 49:
            intent2.addFlags(536870912);
            j = i;
            break;
          case 48:
            intent2.addFlags(1073741824);
            j = i;
            break;
          case 47:
            intent2.addFlags(2048);
            j = i;
            break;
          case 46:
            intent2.addFlags(16384);
            j = i;
            break;
          case 45:
            intent2.addFlags(32768);
            j = i;
            break;
          case 44:
            intent2.addFlags(536870912);
            j = i;
            break;
          case 43:
            intent2.addFlags(2097152);
            j = i;
            break;
          case 42:
            intent2.addFlags(131072);
            j = i;
            break;
          case 41:
            intent2.addFlags(16777216);
            j = i;
            break;
          case 40:
            intent2.addFlags(262144);
            j = i;
            break;
          case 39:
            intent2.addFlags(1073741824);
            j = i;
            break;
          case 38:
            intent2.addFlags(65536);
            j = i;
            break;
          case 37:
            intent2.addFlags(134217728);
            j = i;
            break;
          case 36:
            intent2.addFlags(1048576);
            j = i;
            break;
          case 35:
            intent2.addFlags(8388608);
            j = i;
            break;
          case 34:
            intent2.addFlags(524288);
            j = i;
            break;
          case 33:
            intent2.addFlags(67108864);
            j = i;
            break;
          case 32:
            intent2.addFlags(4194304);
            j = i;
            break;
          case 31:
            intent2.addFlags(8);
            j = i;
            break;
          case 30:
            intent2.addFlags(32);
            j = i;
            break;
          case 29:
            intent2.addFlags(16);
            j = i;
            break;
          case 28:
            intent2.addFlags(128);
            j = i;
            break;
          case 27:
            intent2.addFlags(64);
            j = i;
            break;
          case 26:
            intent2.addFlags(2);
            j = i;
            break;
          case 25:
            intent2.addFlags(1);
            j = i;
            break;
          case 24:
            intent2.setFlags(Integer.decode(stringBuilder.getNextArgRequired()).intValue());
            j = i;
            break;
          case 23:
            intent2.setPackage(stringBuilder.getNextArgRequired());
            j = i;
            if (intent2 == intent1)
              j = 1; 
            break;
          case 22:
            str2 = stringBuilder.getNextArgRequired();
            componentName2 = ComponentName.unflattenFromString(str2);
            if (componentName2 != null) {
              intent2.setComponent(componentName2);
              j = i;
              if (intent2 == intent1)
                j = 1; 
              break;
            } 
            stringBuilder = new StringBuilder();
            stringBuilder.append("Bad component name: ");
            stringBuilder.append(str2);
            throw new IllegalArgumentException(stringBuilder.toString());
          case 21:
            str6 = stringBuilder.getNextArgRequired();
            str2 = stringBuilder.getNextArgRequired().toLowerCase();
            if ("true".equals(str2) || "t".equals(str2)) {
              bool = true;
            } else if ("false".equals(str2) || "f".equals(str2)) {
              bool = false;
            } else {
              try {
                j = Integer.decode(str2).intValue();
                if (j != 0) {
                  bool = true;
                } else {
                  bool = false;
                } 
              } catch (NumberFormatException numberFormatException) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Invalid boolean value: ");
                stringBuilder.append(str2);
                throw new IllegalArgumentException(stringBuilder.toString());
              } 
            } 
            intent2.putExtra(str6, bool);
            j = i;
            break;
          case 20:
            str6 = stringBuilder.getNextArgRequired();
            arrayOfString2 = stringBuilder.getNextArgRequired().split("(?<!\\\\),");
            arrayList3 = new ArrayList(arrayOfString2.length);
            for (j = 0; j < arrayOfString2.length; j++)
              arrayList3.add(arrayOfString2[j]); 
            intent2.putExtra(str6, arrayList3);
            j = 1;
            break;
          case 19:
            intent2.putExtra(stringBuilder.getNextArgRequired(), stringBuilder.getNextArgRequired().split("(?<!\\\\),"));
            j = 1;
            break;
          case 18:
            str4 = stringBuilder.getNextArgRequired();
            arrayOfString4 = stringBuilder.getNextArgRequired().split(",");
            arrayList = new ArrayList(arrayOfString4.length);
            for (j = 0; j < arrayOfString4.length; j++)
              arrayList.add(Float.valueOf(arrayOfString4[j])); 
            intent2.putExtra(str4, arrayList);
            j = 1;
            break;
          case 17:
            str4 = stringBuilder.getNextArgRequired();
            arrayOfString4 = stringBuilder.getNextArgRequired().split(",");
            arrayOfFloat = new float[arrayOfString4.length];
            for (j = 0; j < arrayOfString4.length; j++)
              arrayOfFloat[j] = Float.valueOf(arrayOfString4[j]).floatValue(); 
            intent2.putExtra(str4, arrayOfFloat);
            j = 1;
            break;
          case 16:
            intent2.putExtra(stringBuilder.getNextArgRequired(), Float.valueOf(stringBuilder.getNextArgRequired()));
            j = 1;
            break;
          case 15:
            str4 = stringBuilder.getNextArgRequired();
            arrayOfString3 = stringBuilder.getNextArgRequired().split(",");
            arrayList2 = new ArrayList(arrayOfString3.length);
            for (j = 0; j < arrayOfString3.length; j++)
              arrayList2.add(Long.valueOf(arrayOfString3[j])); 
            intent2.putExtra(str4, arrayList2);
            j = 1;
            break;
          case 14:
            str8 = stringBuilder.getNextArgRequired();
            arrayOfString1 = stringBuilder.getNextArgRequired().split(",");
            arrayOfLong = new long[arrayOfString1.length];
            for (j = 0; j < arrayOfString1.length; j++)
              arrayOfLong[j] = Long.valueOf(arrayOfString1[j]).longValue(); 
            intent2.putExtra(str8, arrayOfLong);
            j = 1;
            break;
          case 13:
            intent2.putExtra(stringBuilder.getNextArgRequired(), Long.valueOf(stringBuilder.getNextArgRequired()));
            j = i;
            break;
          case 12:
            str5 = stringBuilder.getNextArgRequired();
            arrayOfString1 = stringBuilder.getNextArgRequired().split(",");
            arrayList1 = new ArrayList(arrayOfString1.length);
            for (j = 0; j < arrayOfString1.length; j++)
              arrayList1.add(Integer.decode(arrayOfString1[j])); 
            intent2.putExtra(str5, arrayList1);
            j = i;
            break;
          case 11:
            str5 = stringBuilder.getNextArgRequired();
            arrayOfString1 = stringBuilder.getNextArgRequired().split(",");
            arrayOfInt = new int[arrayOfString1.length];
            for (j = 0; j < arrayOfString1.length; j++)
              arrayOfInt[j] = Integer.decode(arrayOfString1[j]).intValue(); 
            intent2.putExtra(str5, arrayOfInt);
            j = i;
            break;
          case 10:
            str7 = stringBuilder.getNextArgRequired();
            str3 = stringBuilder.getNextArgRequired();
            componentName1 = ComponentName.unflattenFromString(str3);
            if (componentName1 != null) {
              intent2.putExtra(str7, componentName1);
              j = i;
              break;
            } 
            stringBuilder = new StringBuilder();
            stringBuilder.append("Bad component name: ");
            stringBuilder.append(str3);
            throw new IllegalArgumentException(stringBuilder.toString());
          case 9:
            intent2.putExtra(stringBuilder.getNextArgRequired(), (Parcelable)Uri.parse(stringBuilder.getNextArgRequired()));
            j = i;
            break;
          case 8:
            intent2.putExtra(stringBuilder.getNextArgRequired(), Integer.decode(stringBuilder.getNextArgRequired()));
            j = i;
            break;
          case 7:
            intent2.putExtra(stringBuilder.getNextArgRequired(), (String)null);
            j = i;
            break;
          case 5:
          case 6:
            intent2.putExtra(stringBuilder.getNextArgRequired(), stringBuilder.getNextArgRequired());
            j = i;
            break;
          case 4:
            intent2.addCategory(stringBuilder.getNextArgRequired());
            if (intent2 == intent1) {
              j = 1;
              break;
            } 
            j = i;
            break;
          case 3:
            intent2.setIdentifier(stringBuilder.getNextArgRequired());
            if (intent2 == intent1) {
              j = 1;
              break;
            } 
            j = i;
            break;
          case 2:
            str = stringBuilder.getNextArgRequired();
            if (intent2 == intent1) {
              j = 1;
              break;
            } 
            j = i;
            break;
          case 1:
            uri = Uri.parse(stringBuilder.getNextArgRequired());
            if (intent2 == intent1) {
              j = 1;
              break;
            } 
            j = i;
            break;
          case 0:
            intent2.setAction(stringBuilder.getNextArgRequired());
            if (intent2 == intent1) {
              j = 1;
              break;
            } 
            j = i;
            break;
        } 
        i = j;
        continue;
      } 
      intent2.setDataAndType(uri, str);
      if (intent2 != intent1) {
        j = 1;
      } else {
        j = 0;
      } 
      Intent intent4 = intent2;
      if (j != 0) {
        intent1.setSelector(intent2);
        intent4 = intent1;
      } 
      String str1 = stringBuilder.getNextArg();
      StringBuilder stringBuilder = null;
      if (str1 == null) {
        if (j != 0) {
          intent3 = new Intent("android.intent.action.MAIN");
          intent3.addCategory("android.intent.category.LAUNCHER");
        } 
      } else if (str1.indexOf(':') >= 0) {
        intent3 = parseUri(str1, 7);
      } else if (str1.indexOf('/') >= 0) {
        intent3 = new Intent("android.intent.action.MAIN");
        intent3.addCategory("android.intent.category.LAUNCHER");
        intent3.setComponent(ComponentName.unflattenFromString(str1));
      } else {
        intent3 = new Intent("android.intent.action.MAIN");
        intent3.addCategory("android.intent.category.LAUNCHER");
        intent3.setPackage(str1);
      } 
      if (intent3 != null) {
        Bundle bundle1;
        Bundle bundle3 = intent4.getExtras();
        Bundle bundle2 = (Bundle)null;
        intent4.replaceExtras(bundle2);
        Bundle bundle4 = intent3.getExtras();
        intent3.replaceExtras(bundle2);
        if (intent4.getAction() != null && intent3.getCategories() != null) {
          Iterator<?> iterator = (new HashSet(intent3.getCategories())).iterator();
          while (iterator.hasNext())
            intent3.removeCategory((String)iterator.next()); 
        } 
        intent4.fillIn(intent3, 72);
        if (bundle3 == null) {
          bundle1 = bundle4;
        } else {
          bundle1 = bundle3;
          if (bundle4 != null) {
            bundle4.putAll(bundle3);
            bundle1 = bundle4;
          } 
        } 
        intent4.replaceExtras(bundle1);
        i = 1;
      } 
      if (i != 0)
        return intent4; 
      throw new IllegalArgumentException("No intent supplied");
    } 
  }
  
  public static Intent parseIntent(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws XmlPullParserException, IOException {
    Intent intent = new Intent();
    TypedArray typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.Intent);
    intent.setAction(typedArray.getString(2));
    String str1 = typedArray.getString(3);
    String str2 = typedArray.getString(1);
    if (str1 != null) {
      Uri uri = Uri.parse(str1);
    } else {
      str1 = null;
    } 
    intent.setDataAndType((Uri)str1, str2);
    intent.setIdentifier(typedArray.getString(5));
    str1 = typedArray.getString(0);
    str2 = typedArray.getString(4);
    if (str1 != null && str2 != null)
      intent.setComponent(new ComponentName(str1, str2)); 
    typedArray.recycle();
    int i = paramXmlPullParser.getDepth();
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1 && (j != 3 || paramXmlPullParser.getDepth() > i)) {
        if (j == 3 || j == 4)
          continue; 
        str1 = paramXmlPullParser.getName();
        if (str1.equals("categories")) {
          typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.IntentCategory);
          str1 = typedArray.getString(0);
          typedArray.recycle();
          if (str1 != null)
            intent.addCategory(str1); 
          XmlUtils.skipCurrentTag(paramXmlPullParser);
          continue;
        } 
        if (str1.equals("extra")) {
          if (intent.mExtras == null)
            intent.mExtras = new Bundle(); 
          paramResources.parseBundleExtra("extra", paramAttributeSet, intent.mExtras);
          XmlUtils.skipCurrentTag(paramXmlPullParser);
          continue;
        } 
        XmlUtils.skipCurrentTag(paramXmlPullParser);
        continue;
      } 
      break;
    } 
    return intent;
  }
  
  public static Intent parseUri(String paramString, int paramInt) throws URISyntaxException {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: iload_2
    //   3: istore_3
    //   4: aload_0
    //   5: ldc_w 'android-app:'
    //   8: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   11: istore #4
    //   13: iload_1
    //   14: iconst_3
    //   15: iand
    //   16: ifeq -> 94
    //   19: iload_2
    //   20: istore_3
    //   21: aload_0
    //   22: ldc_w 'intent:'
    //   25: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   28: ifne -> 94
    //   31: iload #4
    //   33: ifne -> 94
    //   36: iload_2
    //   37: istore_3
    //   38: new android/content/Intent
    //   41: astore #5
    //   43: iload_2
    //   44: istore_3
    //   45: aload #5
    //   47: ldc 'android.intent.action.VIEW'
    //   49: invokespecial <init> : (Ljava/lang/String;)V
    //   52: iload_2
    //   53: istore_3
    //   54: aload #5
    //   56: aload_0
    //   57: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
    //   60: invokevirtual setData : (Landroid/net/Uri;)Landroid/content/Intent;
    //   63: pop
    //   64: aload #5
    //   66: areturn
    //   67: astore #6
    //   69: iload_2
    //   70: istore_3
    //   71: new java/net/URISyntaxException
    //   74: astore #5
    //   76: iload_2
    //   77: istore_3
    //   78: aload #5
    //   80: aload_0
    //   81: aload #6
    //   83: invokevirtual getMessage : ()Ljava/lang/String;
    //   86: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   89: iload_2
    //   90: istore_3
    //   91: aload #5
    //   93: athrow
    //   94: iload_2
    //   95: istore_3
    //   96: aload_0
    //   97: ldc_w '#'
    //   100: invokevirtual lastIndexOf : (Ljava/lang/String;)I
    //   103: istore #7
    //   105: iload #7
    //   107: iconst_m1
    //   108: if_icmpne -> 136
    //   111: iload #7
    //   113: istore_2
    //   114: iload #4
    //   116: ifne -> 170
    //   119: iload #7
    //   121: istore_3
    //   122: new android/content/Intent
    //   125: dup
    //   126: ldc 'android.intent.action.VIEW'
    //   128: aload_0
    //   129: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
    //   132: invokespecial <init> : (Ljava/lang/String;Landroid/net/Uri;)V
    //   135: areturn
    //   136: iload #7
    //   138: istore_2
    //   139: iload #7
    //   141: istore_3
    //   142: aload_0
    //   143: ldc_w '#Intent;'
    //   146: iload #7
    //   148: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   151: ifne -> 170
    //   154: iload #4
    //   156: ifne -> 168
    //   159: iload #7
    //   161: istore_3
    //   162: aload_0
    //   163: iload_1
    //   164: invokestatic getIntentOld : (Ljava/lang/String;I)Landroid/content/Intent;
    //   167: areturn
    //   168: iconst_m1
    //   169: istore_2
    //   170: iload_2
    //   171: istore_3
    //   172: new android/content/Intent
    //   175: astore #8
    //   177: iload_2
    //   178: istore_3
    //   179: aload #8
    //   181: ldc 'android.intent.action.VIEW'
    //   183: invokespecial <init> : (Ljava/lang/String;)V
    //   186: iconst_0
    //   187: istore #7
    //   189: iconst_0
    //   190: istore #9
    //   192: aconst_null
    //   193: astore #10
    //   195: iload_2
    //   196: iflt -> 219
    //   199: iload_2
    //   200: istore_3
    //   201: aload_0
    //   202: iconst_0
    //   203: iload_2
    //   204: invokevirtual substring : (II)Ljava/lang/String;
    //   207: astore #6
    //   209: iinc #2, 8
    //   212: aload #8
    //   214: astore #5
    //   216: goto -> 226
    //   219: aload_0
    //   220: astore #6
    //   222: aload #8
    //   224: astore #5
    //   226: ldc_w ''
    //   229: astore #11
    //   231: iload_2
    //   232: iflt -> 1032
    //   235: iload_2
    //   236: istore_3
    //   237: aload_0
    //   238: ldc_w 'end'
    //   241: iload_2
    //   242: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   245: ifne -> 1032
    //   248: iload_2
    //   249: istore_3
    //   250: aload_0
    //   251: bipush #61
    //   253: iload_2
    //   254: invokevirtual indexOf : (II)I
    //   257: istore #12
    //   259: iload #12
    //   261: istore #13
    //   263: iload #12
    //   265: ifge -> 273
    //   268: iload_2
    //   269: iconst_1
    //   270: isub
    //   271: istore #13
    //   273: iload_2
    //   274: istore_3
    //   275: aload_0
    //   276: bipush #59
    //   278: iload_2
    //   279: invokevirtual indexOf : (II)I
    //   282: istore #12
    //   284: iload #13
    //   286: iload #12
    //   288: if_icmpge -> 308
    //   291: iload_2
    //   292: istore_3
    //   293: aload_0
    //   294: iload #13
    //   296: iconst_1
    //   297: iadd
    //   298: iload #12
    //   300: invokevirtual substring : (II)Ljava/lang/String;
    //   303: invokestatic decode : (Ljava/lang/String;)Ljava/lang/String;
    //   306: astore #11
    //   308: iload_2
    //   309: istore_3
    //   310: aload_0
    //   311: ldc_w 'action='
    //   314: iload_2
    //   315: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   318: ifeq -> 345
    //   321: iload_2
    //   322: istore_3
    //   323: aload #5
    //   325: aload #11
    //   327: invokevirtual setAction : (Ljava/lang/String;)Landroid/content/Intent;
    //   330: pop
    //   331: iload #9
    //   333: ifne -> 342
    //   336: iconst_1
    //   337: istore #7
    //   339: goto -> 1000
    //   342: goto -> 1000
    //   345: iload_2
    //   346: istore_3
    //   347: aload_0
    //   348: ldc_w 'category='
    //   351: iload_2
    //   352: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   355: ifeq -> 371
    //   358: iload_2
    //   359: istore_3
    //   360: aload #5
    //   362: aload #11
    //   364: invokevirtual addCategory : (Ljava/lang/String;)Landroid/content/Intent;
    //   367: pop
    //   368: goto -> 1000
    //   371: iload_2
    //   372: istore_3
    //   373: aload_0
    //   374: ldc_w 'type='
    //   377: iload_2
    //   378: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   381: ifeq -> 396
    //   384: iload_2
    //   385: istore_3
    //   386: aload #5
    //   388: aload #11
    //   390: putfield mType : Ljava/lang/String;
    //   393: goto -> 1000
    //   396: iload_2
    //   397: istore_3
    //   398: aload_0
    //   399: ldc_w 'identifier='
    //   402: iload_2
    //   403: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   406: ifeq -> 421
    //   409: iload_2
    //   410: istore_3
    //   411: aload #5
    //   413: aload #11
    //   415: putfield mIdentifier : Ljava/lang/String;
    //   418: goto -> 1000
    //   421: iload_2
    //   422: istore_3
    //   423: aload_0
    //   424: ldc_w 'launchFlags='
    //   427: iload_2
    //   428: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   431: ifeq -> 480
    //   434: iload_2
    //   435: istore_3
    //   436: aload #11
    //   438: invokestatic decode : (Ljava/lang/String;)Ljava/lang/Integer;
    //   441: invokevirtual intValue : ()I
    //   444: istore #13
    //   446: iload_2
    //   447: istore_3
    //   448: aload #5
    //   450: iload #13
    //   452: putfield mFlags : I
    //   455: iload_1
    //   456: iconst_4
    //   457: iand
    //   458: ifne -> 477
    //   461: iload_2
    //   462: istore_3
    //   463: aload #5
    //   465: iload #13
    //   467: sipush #-196
    //   470: iand
    //   471: putfield mFlags : I
    //   474: goto -> 1000
    //   477: goto -> 1000
    //   480: iload_2
    //   481: istore_3
    //   482: aload_0
    //   483: ldc_w 'package='
    //   486: iload_2
    //   487: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   490: ifeq -> 505
    //   493: iload_2
    //   494: istore_3
    //   495: aload #5
    //   497: aload #11
    //   499: putfield mPackage : Ljava/lang/String;
    //   502: goto -> 1000
    //   505: iload_2
    //   506: istore_3
    //   507: aload_0
    //   508: ldc_w 'component='
    //   511: iload_2
    //   512: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   515: ifeq -> 533
    //   518: iload_2
    //   519: istore_3
    //   520: aload #5
    //   522: aload #11
    //   524: invokestatic unflattenFromString : (Ljava/lang/String;)Landroid/content/ComponentName;
    //   527: putfield mComponent : Landroid/content/ComponentName;
    //   530: goto -> 1000
    //   533: iload_2
    //   534: istore_3
    //   535: aload_0
    //   536: ldc_w 'scheme='
    //   539: iload_2
    //   540: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   543: ifeq -> 611
    //   546: iload #9
    //   548: ifeq -> 604
    //   551: iload_2
    //   552: istore_3
    //   553: new java/lang/StringBuilder
    //   556: astore #14
    //   558: iload_2
    //   559: istore_3
    //   560: aload #14
    //   562: invokespecial <init> : ()V
    //   565: iload_2
    //   566: istore_3
    //   567: aload #14
    //   569: aload #11
    //   571: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   574: pop
    //   575: iload_2
    //   576: istore_3
    //   577: aload #14
    //   579: ldc_w ':'
    //   582: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   585: pop
    //   586: iload_2
    //   587: istore_3
    //   588: aload #5
    //   590: aload #14
    //   592: invokevirtual toString : ()Ljava/lang/String;
    //   595: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
    //   598: putfield mData : Landroid/net/Uri;
    //   601: goto -> 1000
    //   604: aload #11
    //   606: astore #10
    //   608: goto -> 1000
    //   611: iload_2
    //   612: istore_3
    //   613: aload_0
    //   614: ldc_w 'sourceBounds='
    //   617: iload_2
    //   618: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   621: ifeq -> 639
    //   624: iload_2
    //   625: istore_3
    //   626: aload #5
    //   628: aload #11
    //   630: invokestatic unflattenFromString : (Ljava/lang/String;)Landroid/graphics/Rect;
    //   633: putfield mSourceBounds : Landroid/graphics/Rect;
    //   636: goto -> 1000
    //   639: iload #12
    //   641: iload_2
    //   642: iconst_3
    //   643: iadd
    //   644: if_icmpne -> 677
    //   647: iload_2
    //   648: istore_3
    //   649: aload_0
    //   650: ldc_w 'SEL'
    //   653: iload_2
    //   654: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   657: ifeq -> 677
    //   660: iload_2
    //   661: istore_3
    //   662: new android/content/Intent
    //   665: dup
    //   666: invokespecial <init> : ()V
    //   669: astore #5
    //   671: iconst_1
    //   672: istore #9
    //   674: goto -> 1000
    //   677: iload_2
    //   678: istore_3
    //   679: aload_0
    //   680: iload_2
    //   681: iconst_2
    //   682: iadd
    //   683: iload #13
    //   685: invokevirtual substring : (II)Ljava/lang/String;
    //   688: invokestatic decode : (Ljava/lang/String;)Ljava/lang/String;
    //   691: astore #14
    //   693: iload_2
    //   694: istore_3
    //   695: aload #5
    //   697: getfield mExtras : Landroid/os/Bundle;
    //   700: ifnonnull -> 726
    //   703: iload_2
    //   704: istore_3
    //   705: new android/os/Bundle
    //   708: astore #15
    //   710: iload_2
    //   711: istore_3
    //   712: aload #15
    //   714: invokespecial <init> : ()V
    //   717: iload_2
    //   718: istore_3
    //   719: aload #5
    //   721: aload #15
    //   723: putfield mExtras : Landroid/os/Bundle;
    //   726: iload_2
    //   727: istore_3
    //   728: aload #5
    //   730: getfield mExtras : Landroid/os/Bundle;
    //   733: astore #15
    //   735: iload_2
    //   736: istore_3
    //   737: aload_0
    //   738: ldc_w 'S.'
    //   741: iload_2
    //   742: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   745: ifeq -> 762
    //   748: iload_2
    //   749: istore_3
    //   750: aload #15
    //   752: aload #14
    //   754: aload #11
    //   756: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   759: goto -> 1000
    //   762: iload_2
    //   763: istore_3
    //   764: aload_0
    //   765: ldc_w 'B.'
    //   768: iload_2
    //   769: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   772: ifeq -> 792
    //   775: iload_2
    //   776: istore_3
    //   777: aload #15
    //   779: aload #14
    //   781: aload #11
    //   783: invokestatic parseBoolean : (Ljava/lang/String;)Z
    //   786: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   789: goto -> 1000
    //   792: iload_2
    //   793: istore_3
    //   794: aload_0
    //   795: ldc_w 'b.'
    //   798: iload_2
    //   799: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   802: ifeq -> 822
    //   805: iload_2
    //   806: istore_3
    //   807: aload #15
    //   809: aload #14
    //   811: aload #11
    //   813: invokestatic parseByte : (Ljava/lang/String;)B
    //   816: invokevirtual putByte : (Ljava/lang/String;B)V
    //   819: goto -> 1000
    //   822: iload_2
    //   823: istore_3
    //   824: aload_0
    //   825: ldc_w 'c.'
    //   828: iload_2
    //   829: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   832: ifeq -> 853
    //   835: iload_2
    //   836: istore_3
    //   837: aload #15
    //   839: aload #14
    //   841: aload #11
    //   843: iconst_0
    //   844: invokevirtual charAt : (I)C
    //   847: invokevirtual putChar : (Ljava/lang/String;C)V
    //   850: goto -> 1000
    //   853: iload_2
    //   854: istore_3
    //   855: aload_0
    //   856: ldc_w 'd.'
    //   859: iload_2
    //   860: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   863: ifeq -> 883
    //   866: iload_2
    //   867: istore_3
    //   868: aload #15
    //   870: aload #14
    //   872: aload #11
    //   874: invokestatic parseDouble : (Ljava/lang/String;)D
    //   877: invokevirtual putDouble : (Ljava/lang/String;D)V
    //   880: goto -> 1000
    //   883: iload_2
    //   884: istore_3
    //   885: aload_0
    //   886: ldc_w 'f.'
    //   889: iload_2
    //   890: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   893: ifeq -> 913
    //   896: iload_2
    //   897: istore_3
    //   898: aload #15
    //   900: aload #14
    //   902: aload #11
    //   904: invokestatic parseFloat : (Ljava/lang/String;)F
    //   907: invokevirtual putFloat : (Ljava/lang/String;F)V
    //   910: goto -> 1000
    //   913: iload_2
    //   914: istore_3
    //   915: aload_0
    //   916: ldc_w 'i.'
    //   919: iload_2
    //   920: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   923: ifeq -> 943
    //   926: iload_2
    //   927: istore_3
    //   928: aload #15
    //   930: aload #14
    //   932: aload #11
    //   934: invokestatic parseInt : (Ljava/lang/String;)I
    //   937: invokevirtual putInt : (Ljava/lang/String;I)V
    //   940: goto -> 1000
    //   943: iload_2
    //   944: istore_3
    //   945: aload_0
    //   946: ldc_w 'l.'
    //   949: iload_2
    //   950: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   953: ifeq -> 973
    //   956: iload_2
    //   957: istore_3
    //   958: aload #15
    //   960: aload #14
    //   962: aload #11
    //   964: invokestatic parseLong : (Ljava/lang/String;)J
    //   967: invokevirtual putLong : (Ljava/lang/String;J)V
    //   970: goto -> 1000
    //   973: iload_2
    //   974: istore_3
    //   975: aload_0
    //   976: ldc_w 's.'
    //   979: iload_2
    //   980: invokevirtual startsWith : (Ljava/lang/String;I)Z
    //   983: ifeq -> 1008
    //   986: iload_2
    //   987: istore_3
    //   988: aload #15
    //   990: aload #14
    //   992: aload #11
    //   994: invokestatic parseShort : (Ljava/lang/String;)S
    //   997: invokevirtual putShort : (Ljava/lang/String;S)V
    //   1000: iload #12
    //   1002: iconst_1
    //   1003: iadd
    //   1004: istore_2
    //   1005: goto -> 226
    //   1008: iload_2
    //   1009: istore_3
    //   1010: new java/net/URISyntaxException
    //   1013: astore #5
    //   1015: iload_2
    //   1016: istore_3
    //   1017: aload #5
    //   1019: aload_0
    //   1020: ldc_w 'unknown EXTRA type'
    //   1023: iload_2
    //   1024: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;I)V
    //   1027: iload_2
    //   1028: istore_3
    //   1029: aload #5
    //   1031: athrow
    //   1032: iload #9
    //   1034: ifeq -> 1066
    //   1037: iload_2
    //   1038: istore_3
    //   1039: aload #8
    //   1041: getfield mPackage : Ljava/lang/String;
    //   1044: ifnonnull -> 1059
    //   1047: iload_2
    //   1048: istore_3
    //   1049: aload #8
    //   1051: aload #5
    //   1053: invokevirtual setSelector : (Landroid/content/Intent;)V
    //   1056: goto -> 1059
    //   1059: aload #8
    //   1061: astore #11
    //   1063: goto -> 1070
    //   1066: aload #5
    //   1068: astore #11
    //   1070: aload #6
    //   1072: ifnull -> 1649
    //   1075: iload_2
    //   1076: istore_3
    //   1077: aload #6
    //   1079: ldc_w 'intent:'
    //   1082: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   1085: ifeq -> 1163
    //   1088: iload_2
    //   1089: istore_3
    //   1090: aload #6
    //   1092: bipush #7
    //   1094: invokevirtual substring : (I)Ljava/lang/String;
    //   1097: astore #5
    //   1099: aload #10
    //   1101: ifnull -> 1160
    //   1104: iload_2
    //   1105: istore_3
    //   1106: new java/lang/StringBuilder
    //   1109: astore #6
    //   1111: iload_2
    //   1112: istore_3
    //   1113: aload #6
    //   1115: invokespecial <init> : ()V
    //   1118: iload_2
    //   1119: istore_3
    //   1120: aload #6
    //   1122: aload #10
    //   1124: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1127: pop
    //   1128: iload_2
    //   1129: istore_3
    //   1130: aload #6
    //   1132: bipush #58
    //   1134: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1137: pop
    //   1138: iload_2
    //   1139: istore_3
    //   1140: aload #6
    //   1142: aload #5
    //   1144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1147: pop
    //   1148: iload_2
    //   1149: istore_3
    //   1150: aload #6
    //   1152: invokevirtual toString : ()Ljava/lang/String;
    //   1155: astore #5
    //   1157: goto -> 1595
    //   1160: goto -> 1595
    //   1163: aload #6
    //   1165: astore #5
    //   1167: iload_2
    //   1168: istore_3
    //   1169: aload #6
    //   1171: ldc_w 'android-app:'
    //   1174: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   1177: ifeq -> 1595
    //   1180: iload_2
    //   1181: istore_3
    //   1182: aload #6
    //   1184: bipush #12
    //   1186: invokevirtual charAt : (I)C
    //   1189: bipush #47
    //   1191: if_icmpne -> 1590
    //   1194: iload_2
    //   1195: istore_3
    //   1196: aload #6
    //   1198: bipush #13
    //   1200: invokevirtual charAt : (I)C
    //   1203: bipush #47
    //   1205: if_icmpne -> 1590
    //   1208: iload_2
    //   1209: istore_3
    //   1210: aload #6
    //   1212: bipush #47
    //   1214: bipush #14
    //   1216: invokevirtual indexOf : (II)I
    //   1219: istore #9
    //   1221: iload #9
    //   1223: ifge -> 1264
    //   1226: iload_2
    //   1227: istore_3
    //   1228: aload #11
    //   1230: aload #6
    //   1232: bipush #14
    //   1234: invokevirtual substring : (I)Ljava/lang/String;
    //   1237: putfield mPackage : Ljava/lang/String;
    //   1240: iload #7
    //   1242: ifne -> 1256
    //   1245: iload_2
    //   1246: istore_3
    //   1247: aload #11
    //   1249: ldc_w 'android.intent.action.MAIN'
    //   1252: invokevirtual setAction : (Ljava/lang/String;)Landroid/content/Intent;
    //   1255: pop
    //   1256: ldc_w ''
    //   1259: astore #5
    //   1261: goto -> 1587
    //   1264: aconst_null
    //   1265: astore #8
    //   1267: iload_2
    //   1268: istore_3
    //   1269: aload #11
    //   1271: aload #6
    //   1273: bipush #14
    //   1275: iload #9
    //   1277: invokevirtual substring : (II)Ljava/lang/String;
    //   1280: putfield mPackage : Ljava/lang/String;
    //   1283: iload #9
    //   1285: istore_1
    //   1286: aload #8
    //   1288: astore #5
    //   1290: iload_2
    //   1291: istore_3
    //   1292: iload #9
    //   1294: iconst_1
    //   1295: iadd
    //   1296: aload #6
    //   1298: invokevirtual length : ()I
    //   1301: if_icmpge -> 1438
    //   1304: iload_2
    //   1305: istore_3
    //   1306: aload #6
    //   1308: bipush #47
    //   1310: iload #9
    //   1312: iconst_1
    //   1313: iadd
    //   1314: invokevirtual indexOf : (II)I
    //   1317: istore_1
    //   1318: iload_1
    //   1319: iflt -> 1418
    //   1322: iload_2
    //   1323: istore_3
    //   1324: aload #6
    //   1326: iload #9
    //   1328: iconst_1
    //   1329: iadd
    //   1330: iload_1
    //   1331: invokevirtual substring : (II)Ljava/lang/String;
    //   1334: astore #14
    //   1336: iload_1
    //   1337: istore #9
    //   1339: iload #9
    //   1341: istore_1
    //   1342: aload #14
    //   1344: astore #10
    //   1346: aload #8
    //   1348: astore #5
    //   1350: iload_2
    //   1351: istore_3
    //   1352: iload #9
    //   1354: aload #6
    //   1356: invokevirtual length : ()I
    //   1359: if_icmpge -> 1438
    //   1362: iload_2
    //   1363: istore_3
    //   1364: aload #6
    //   1366: bipush #47
    //   1368: iload #9
    //   1370: iconst_1
    //   1371: iadd
    //   1372: invokevirtual indexOf : (II)I
    //   1375: istore #13
    //   1377: iload #9
    //   1379: istore_1
    //   1380: aload #14
    //   1382: astore #10
    //   1384: aload #8
    //   1386: astore #5
    //   1388: iload #13
    //   1390: iflt -> 1438
    //   1393: iload_2
    //   1394: istore_3
    //   1395: aload #6
    //   1397: iload #9
    //   1399: iconst_1
    //   1400: iadd
    //   1401: iload #13
    //   1403: invokevirtual substring : (II)Ljava/lang/String;
    //   1406: astore #5
    //   1408: iload #13
    //   1410: istore_1
    //   1411: aload #14
    //   1413: astore #10
    //   1415: goto -> 1438
    //   1418: iload_2
    //   1419: istore_3
    //   1420: aload #6
    //   1422: iload #9
    //   1424: iconst_1
    //   1425: iadd
    //   1426: invokevirtual substring : (I)Ljava/lang/String;
    //   1429: astore #10
    //   1431: aload #8
    //   1433: astore #5
    //   1435: iload #9
    //   1437: istore_1
    //   1438: aload #10
    //   1440: ifnonnull -> 1467
    //   1443: iload #7
    //   1445: ifne -> 1459
    //   1448: iload_2
    //   1449: istore_3
    //   1450: aload #11
    //   1452: ldc_w 'android.intent.action.MAIN'
    //   1455: invokevirtual setAction : (Ljava/lang/String;)Landroid/content/Intent;
    //   1458: pop
    //   1459: ldc_w ''
    //   1462: astore #5
    //   1464: goto -> 1587
    //   1467: aload #5
    //   1469: ifnonnull -> 1519
    //   1472: iload_2
    //   1473: istore_3
    //   1474: new java/lang/StringBuilder
    //   1477: astore #5
    //   1479: iload_2
    //   1480: istore_3
    //   1481: aload #5
    //   1483: invokespecial <init> : ()V
    //   1486: iload_2
    //   1487: istore_3
    //   1488: aload #5
    //   1490: aload #10
    //   1492: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1495: pop
    //   1496: iload_2
    //   1497: istore_3
    //   1498: aload #5
    //   1500: ldc_w ':'
    //   1503: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1506: pop
    //   1507: iload_2
    //   1508: istore_3
    //   1509: aload #5
    //   1511: invokevirtual toString : ()Ljava/lang/String;
    //   1514: astore #5
    //   1516: goto -> 1587
    //   1519: iload_2
    //   1520: istore_3
    //   1521: new java/lang/StringBuilder
    //   1524: astore #8
    //   1526: iload_2
    //   1527: istore_3
    //   1528: aload #8
    //   1530: invokespecial <init> : ()V
    //   1533: iload_2
    //   1534: istore_3
    //   1535: aload #8
    //   1537: aload #10
    //   1539: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1542: pop
    //   1543: iload_2
    //   1544: istore_3
    //   1545: aload #8
    //   1547: ldc_w '://'
    //   1550: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1553: pop
    //   1554: iload_2
    //   1555: istore_3
    //   1556: aload #8
    //   1558: aload #5
    //   1560: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1563: pop
    //   1564: iload_2
    //   1565: istore_3
    //   1566: aload #8
    //   1568: aload #6
    //   1570: iload_1
    //   1571: invokevirtual substring : (I)Ljava/lang/String;
    //   1574: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1577: pop
    //   1578: iload_2
    //   1579: istore_3
    //   1580: aload #8
    //   1582: invokevirtual toString : ()Ljava/lang/String;
    //   1585: astore #5
    //   1587: goto -> 1595
    //   1590: ldc_w ''
    //   1593: astore #5
    //   1595: iload_2
    //   1596: istore_3
    //   1597: aload #5
    //   1599: invokevirtual length : ()I
    //   1602: istore_1
    //   1603: iload_1
    //   1604: ifle -> 1649
    //   1607: iload_2
    //   1608: istore_3
    //   1609: aload #11
    //   1611: aload #5
    //   1613: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
    //   1616: putfield mData : Landroid/net/Uri;
    //   1619: goto -> 1649
    //   1622: astore #6
    //   1624: iload_2
    //   1625: istore_3
    //   1626: new java/net/URISyntaxException
    //   1629: astore #5
    //   1631: iload_2
    //   1632: istore_3
    //   1633: aload #5
    //   1635: aload_0
    //   1636: aload #6
    //   1638: invokevirtual getMessage : ()Ljava/lang/String;
    //   1641: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   1644: iload_2
    //   1645: istore_3
    //   1646: aload #5
    //   1648: athrow
    //   1649: aload #11
    //   1651: areturn
    //   1652: astore #5
    //   1654: new java/net/URISyntaxException
    //   1657: dup
    //   1658: aload_0
    //   1659: ldc_w 'illegal Intent URI format'
    //   1662: iload_3
    //   1663: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;I)V
    //   1666: athrow
    // Exception table:
    //   from	to	target	type
    //   4	13	1652	java/lang/IndexOutOfBoundsException
    //   21	31	1652	java/lang/IndexOutOfBoundsException
    //   38	43	1652	java/lang/IndexOutOfBoundsException
    //   45	52	1652	java/lang/IndexOutOfBoundsException
    //   54	64	67	java/lang/IllegalArgumentException
    //   54	64	1652	java/lang/IndexOutOfBoundsException
    //   71	76	1652	java/lang/IndexOutOfBoundsException
    //   78	89	1652	java/lang/IndexOutOfBoundsException
    //   91	94	1652	java/lang/IndexOutOfBoundsException
    //   96	105	1652	java/lang/IndexOutOfBoundsException
    //   122	136	1652	java/lang/IndexOutOfBoundsException
    //   142	154	1652	java/lang/IndexOutOfBoundsException
    //   162	168	1652	java/lang/IndexOutOfBoundsException
    //   172	177	1652	java/lang/IndexOutOfBoundsException
    //   179	186	1652	java/lang/IndexOutOfBoundsException
    //   201	209	1652	java/lang/IndexOutOfBoundsException
    //   237	248	1652	java/lang/IndexOutOfBoundsException
    //   250	259	1652	java/lang/IndexOutOfBoundsException
    //   275	284	1652	java/lang/IndexOutOfBoundsException
    //   293	308	1652	java/lang/IndexOutOfBoundsException
    //   310	321	1652	java/lang/IndexOutOfBoundsException
    //   323	331	1652	java/lang/IndexOutOfBoundsException
    //   347	358	1652	java/lang/IndexOutOfBoundsException
    //   360	368	1652	java/lang/IndexOutOfBoundsException
    //   373	384	1652	java/lang/IndexOutOfBoundsException
    //   386	393	1652	java/lang/IndexOutOfBoundsException
    //   398	409	1652	java/lang/IndexOutOfBoundsException
    //   411	418	1652	java/lang/IndexOutOfBoundsException
    //   423	434	1652	java/lang/IndexOutOfBoundsException
    //   436	446	1652	java/lang/IndexOutOfBoundsException
    //   448	455	1652	java/lang/IndexOutOfBoundsException
    //   463	474	1652	java/lang/IndexOutOfBoundsException
    //   482	493	1652	java/lang/IndexOutOfBoundsException
    //   495	502	1652	java/lang/IndexOutOfBoundsException
    //   507	518	1652	java/lang/IndexOutOfBoundsException
    //   520	530	1652	java/lang/IndexOutOfBoundsException
    //   535	546	1652	java/lang/IndexOutOfBoundsException
    //   553	558	1652	java/lang/IndexOutOfBoundsException
    //   560	565	1652	java/lang/IndexOutOfBoundsException
    //   567	575	1652	java/lang/IndexOutOfBoundsException
    //   577	586	1652	java/lang/IndexOutOfBoundsException
    //   588	601	1652	java/lang/IndexOutOfBoundsException
    //   613	624	1652	java/lang/IndexOutOfBoundsException
    //   626	636	1652	java/lang/IndexOutOfBoundsException
    //   649	660	1652	java/lang/IndexOutOfBoundsException
    //   662	671	1652	java/lang/IndexOutOfBoundsException
    //   679	693	1652	java/lang/IndexOutOfBoundsException
    //   695	703	1652	java/lang/IndexOutOfBoundsException
    //   705	710	1652	java/lang/IndexOutOfBoundsException
    //   712	717	1652	java/lang/IndexOutOfBoundsException
    //   719	726	1652	java/lang/IndexOutOfBoundsException
    //   728	735	1652	java/lang/IndexOutOfBoundsException
    //   737	748	1652	java/lang/IndexOutOfBoundsException
    //   750	759	1652	java/lang/IndexOutOfBoundsException
    //   764	775	1652	java/lang/IndexOutOfBoundsException
    //   777	789	1652	java/lang/IndexOutOfBoundsException
    //   794	805	1652	java/lang/IndexOutOfBoundsException
    //   807	819	1652	java/lang/IndexOutOfBoundsException
    //   824	835	1652	java/lang/IndexOutOfBoundsException
    //   837	850	1652	java/lang/IndexOutOfBoundsException
    //   855	866	1652	java/lang/IndexOutOfBoundsException
    //   868	880	1652	java/lang/IndexOutOfBoundsException
    //   885	896	1652	java/lang/IndexOutOfBoundsException
    //   898	910	1652	java/lang/IndexOutOfBoundsException
    //   915	926	1652	java/lang/IndexOutOfBoundsException
    //   928	940	1652	java/lang/IndexOutOfBoundsException
    //   945	956	1652	java/lang/IndexOutOfBoundsException
    //   958	970	1652	java/lang/IndexOutOfBoundsException
    //   975	986	1652	java/lang/IndexOutOfBoundsException
    //   988	1000	1652	java/lang/IndexOutOfBoundsException
    //   1010	1015	1652	java/lang/IndexOutOfBoundsException
    //   1017	1027	1652	java/lang/IndexOutOfBoundsException
    //   1029	1032	1652	java/lang/IndexOutOfBoundsException
    //   1039	1047	1652	java/lang/IndexOutOfBoundsException
    //   1049	1056	1652	java/lang/IndexOutOfBoundsException
    //   1077	1088	1652	java/lang/IndexOutOfBoundsException
    //   1090	1099	1652	java/lang/IndexOutOfBoundsException
    //   1106	1111	1652	java/lang/IndexOutOfBoundsException
    //   1113	1118	1652	java/lang/IndexOutOfBoundsException
    //   1120	1128	1652	java/lang/IndexOutOfBoundsException
    //   1130	1138	1652	java/lang/IndexOutOfBoundsException
    //   1140	1148	1652	java/lang/IndexOutOfBoundsException
    //   1150	1157	1652	java/lang/IndexOutOfBoundsException
    //   1169	1180	1652	java/lang/IndexOutOfBoundsException
    //   1182	1194	1652	java/lang/IndexOutOfBoundsException
    //   1196	1208	1652	java/lang/IndexOutOfBoundsException
    //   1210	1221	1652	java/lang/IndexOutOfBoundsException
    //   1228	1240	1652	java/lang/IndexOutOfBoundsException
    //   1247	1256	1652	java/lang/IndexOutOfBoundsException
    //   1269	1283	1652	java/lang/IndexOutOfBoundsException
    //   1292	1304	1652	java/lang/IndexOutOfBoundsException
    //   1306	1318	1652	java/lang/IndexOutOfBoundsException
    //   1324	1336	1652	java/lang/IndexOutOfBoundsException
    //   1352	1362	1652	java/lang/IndexOutOfBoundsException
    //   1364	1377	1652	java/lang/IndexOutOfBoundsException
    //   1395	1408	1652	java/lang/IndexOutOfBoundsException
    //   1420	1431	1652	java/lang/IndexOutOfBoundsException
    //   1450	1459	1652	java/lang/IndexOutOfBoundsException
    //   1474	1479	1652	java/lang/IndexOutOfBoundsException
    //   1481	1486	1652	java/lang/IndexOutOfBoundsException
    //   1488	1496	1652	java/lang/IndexOutOfBoundsException
    //   1498	1507	1652	java/lang/IndexOutOfBoundsException
    //   1509	1516	1652	java/lang/IndexOutOfBoundsException
    //   1521	1526	1652	java/lang/IndexOutOfBoundsException
    //   1528	1533	1652	java/lang/IndexOutOfBoundsException
    //   1535	1543	1652	java/lang/IndexOutOfBoundsException
    //   1545	1554	1652	java/lang/IndexOutOfBoundsException
    //   1556	1564	1652	java/lang/IndexOutOfBoundsException
    //   1566	1578	1652	java/lang/IndexOutOfBoundsException
    //   1580	1587	1652	java/lang/IndexOutOfBoundsException
    //   1597	1603	1652	java/lang/IndexOutOfBoundsException
    //   1609	1619	1622	java/lang/IllegalArgumentException
    //   1609	1619	1652	java/lang/IndexOutOfBoundsException
    //   1626	1631	1652	java/lang/IndexOutOfBoundsException
    //   1633	1644	1652	java/lang/IndexOutOfBoundsException
    //   1646	1649	1652	java/lang/IndexOutOfBoundsException
  }
  
  public static void printIntentArgsHelp(PrintWriter paramPrintWriter, String paramString) {
    String[] arrayOfString = new String[48];
    arrayOfString[0] = "<INTENT> specifications include these flags and arguments:";
    arrayOfString[1] = "    [-a <ACTION>] [-d <DATA_URI>] [-t <MIME_TYPE>] [-i <IDENTIFIER>]";
    arrayOfString[2] = "    [-c <CATEGORY> [-c <CATEGORY>] ...]";
    arrayOfString[3] = "    [-n <COMPONENT_NAME>]";
    arrayOfString[4] = "    [-e|--es <EXTRA_KEY> <EXTRA_STRING_VALUE> ...]";
    arrayOfString[5] = "    [--esn <EXTRA_KEY> ...]";
    arrayOfString[6] = "    [--ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE> ...]";
    arrayOfString[7] = "    [--ei <EXTRA_KEY> <EXTRA_INT_VALUE> ...]";
    arrayOfString[8] = "    [--el <EXTRA_KEY> <EXTRA_LONG_VALUE> ...]";
    arrayOfString[9] = "    [--ef <EXTRA_KEY> <EXTRA_FLOAT_VALUE> ...]";
    arrayOfString[10] = "    [--eu <EXTRA_KEY> <EXTRA_URI_VALUE> ...]";
    arrayOfString[11] = "    [--ecn <EXTRA_KEY> <EXTRA_COMPONENT_NAME_VALUE>]";
    arrayOfString[12] = "    [--eia <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]";
    arrayOfString[13] = "        (mutiple extras passed as Integer[])";
    arrayOfString[14] = "    [--eial <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]";
    arrayOfString[15] = "        (mutiple extras passed as List<Integer>)";
    arrayOfString[16] = "    [--ela <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]";
    arrayOfString[17] = "        (mutiple extras passed as Long[])";
    arrayOfString[18] = "    [--elal <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]";
    arrayOfString[19] = "        (mutiple extras passed as List<Long>)";
    arrayOfString[20] = "    [--efa <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]";
    arrayOfString[21] = "        (mutiple extras passed as Float[])";
    arrayOfString[22] = "    [--efal <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]";
    arrayOfString[23] = "        (mutiple extras passed as List<Float>)";
    arrayOfString[24] = "    [--esa <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]";
    arrayOfString[25] = "        (mutiple extras passed as String[]; to embed a comma into a string,";
    arrayOfString[26] = "         escape it using \"\\,\")";
    arrayOfString[27] = "    [--esal <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]";
    arrayOfString[28] = "        (mutiple extras passed as List<String>; to embed a comma into a string,";
    arrayOfString[29] = "         escape it using \"\\,\")";
    arrayOfString[30] = "    [-f <FLAG>]";
    arrayOfString[31] = "    [--grant-read-uri-permission] [--grant-write-uri-permission]";
    arrayOfString[32] = "    [--grant-persistable-uri-permission] [--grant-prefix-uri-permission]";
    arrayOfString[33] = "    [--debug-log-resolution] [--exclude-stopped-packages]";
    arrayOfString[34] = "    [--include-stopped-packages]";
    arrayOfString[35] = "    [--activity-brought-to-front] [--activity-clear-top]";
    arrayOfString[36] = "    [--activity-clear-when-task-reset] [--activity-exclude-from-recents]";
    arrayOfString[37] = "    [--activity-launched-from-history] [--activity-multiple-task]";
    arrayOfString[38] = "    [--activity-no-animation] [--activity-no-history]";
    arrayOfString[39] = "    [--activity-no-user-action] [--activity-previous-is-top]";
    arrayOfString[40] = "    [--activity-reorder-to-front] [--activity-reset-task-if-needed]";
    arrayOfString[41] = "    [--activity-single-top] [--activity-clear-task]";
    arrayOfString[42] = "    [--activity-task-on-home] [--activity-match-external]";
    arrayOfString[43] = "    [--receiver-registered-only] [--receiver-replace-pending]";
    arrayOfString[44] = "    [--receiver-foreground] [--receiver-no-abort]";
    arrayOfString[45] = "    [--receiver-include-background]";
    arrayOfString[46] = "    [--selector]";
    arrayOfString[47] = "    [<URI> | <PACKAGE> | <COMPONENT>]";
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str = arrayOfString[b];
      paramPrintWriter.print(paramString);
      paramPrintWriter.println(str);
    } 
  }
  
  public static Intent restoreFromXml(XmlPullParser paramXmlPullParser) throws IOException, XmlPullParserException {
    Intent intent = new Intent();
    int i = paramXmlPullParser.getDepth();
    int j;
    for (j = paramXmlPullParser.getAttributeCount() - 1; j >= 0; j--) {
      String str1 = paramXmlPullParser.getAttributeName(j);
      String str2 = paramXmlPullParser.getAttributeValue(j);
      if ("action".equals(str1)) {
        intent.setAction(str2);
      } else if ("data".equals(str1)) {
        intent.setData(Uri.parse(str2));
      } else if ("type".equals(str1)) {
        intent.setType(str2);
      } else if ("ident".equals(str1)) {
        intent.setIdentifier(str2);
      } else if ("component".equals(str1)) {
        intent.setComponent(ComponentName.unflattenFromString(str2));
      } else if ("flags".equals(str1)) {
        intent.setFlags(Integer.parseInt(str2, 16));
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("restoreFromXml: unknown attribute=");
        stringBuilder.append(str1);
        Log.e("Intent", stringBuilder.toString());
      } 
    } 
    while (true) {
      j = paramXmlPullParser.next();
      if (j != 1 && (j != 3 || paramXmlPullParser.getDepth() < i)) {
        if (j == 2) {
          String str = paramXmlPullParser.getName();
          if ("categories".equals(str)) {
            for (j = paramXmlPullParser.getAttributeCount() - 1; j >= 0; j--)
              intent.addCategory(paramXmlPullParser.getAttributeValue(j)); 
            continue;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("restoreFromXml: unknown name=");
          stringBuilder.append(str);
          Log.w("Intent", stringBuilder.toString());
          XmlUtils.skipCurrentTag(paramXmlPullParser);
        } 
        continue;
      } 
      break;
    } 
    return intent;
  }
  
  private void toUriFragment(StringBuilder paramStringBuilder, String paramString1, String paramString2, String paramString3, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder(128);
    toUriInner(stringBuilder, paramString1, paramString2, paramString3, paramInt);
    if (this.mSelector != null) {
      stringBuilder.append("SEL;");
      Intent intent = this.mSelector;
      Uri uri = intent.mData;
      if (uri != null) {
        String str = uri.getScheme();
      } else {
        uri = null;
      } 
      intent.toUriInner(stringBuilder, (String)uri, null, null, paramInt);
    } 
    if (stringBuilder.length() > 0) {
      paramStringBuilder.append("#Intent;");
      paramStringBuilder.append(stringBuilder);
      paramStringBuilder.append("end");
    } 
  }
  
  private void toUriInner(StringBuilder paramStringBuilder, String paramString1, String paramString2, String paramString3, int paramInt) {
    if (paramString1 != null) {
      paramStringBuilder.append("scheme=");
      paramStringBuilder.append(paramString1);
      paramStringBuilder.append(';');
    } 
    paramString1 = this.mAction;
    if (paramString1 != null && !paramString1.equals(paramString2)) {
      paramStringBuilder.append("action=");
      paramStringBuilder.append(Uri.encode(this.mAction));
      paramStringBuilder.append(';');
    } 
    if (this.mCategories != null)
      for (paramInt = 0; paramInt < this.mCategories.size(); paramInt++) {
        paramStringBuilder.append("category=");
        paramStringBuilder.append(Uri.encode((String)this.mCategories.valueAt(paramInt)));
        paramStringBuilder.append(';');
      }  
    if (this.mType != null) {
      paramStringBuilder.append("type=");
      paramStringBuilder.append(Uri.encode(this.mType, "/"));
      paramStringBuilder.append(';');
    } 
    if (this.mIdentifier != null) {
      paramStringBuilder.append("identifier=");
      paramStringBuilder.append(Uri.encode(this.mIdentifier, "/"));
      paramStringBuilder.append(';');
    } 
    if (this.mFlags != 0) {
      paramStringBuilder.append("launchFlags=0x");
      paramStringBuilder.append(Integer.toHexString(this.mFlags));
      paramStringBuilder.append(';');
    } 
    paramString1 = this.mPackage;
    if (paramString1 != null && !paramString1.equals(paramString3)) {
      paramStringBuilder.append("package=");
      paramStringBuilder.append(Uri.encode(this.mPackage));
      paramStringBuilder.append(';');
    } 
    if (this.mComponent != null) {
      paramStringBuilder.append("component=");
      paramStringBuilder.append(Uri.encode(this.mComponent.flattenToShortString(), "/"));
      paramStringBuilder.append(';');
    } 
    if (this.mSourceBounds != null) {
      paramStringBuilder.append("sourceBounds=");
      paramStringBuilder.append(Uri.encode(this.mSourceBounds.flattenToString()));
      paramStringBuilder.append(';');
    } 
    Bundle bundle = this.mExtras;
    if (bundle != null)
      for (String str : bundle.keySet()) {
        int i;
        Object object = this.mExtras.get(str);
        if (object instanceof String) {
          paramInt = 83;
          i = paramInt;
        } else if (object instanceof Boolean) {
          paramInt = 66;
          i = paramInt;
        } else if (object instanceof Byte) {
          paramInt = 98;
          i = paramInt;
        } else if (object instanceof Character) {
          paramInt = 99;
          i = paramInt;
        } else if (object instanceof Double) {
          paramInt = 100;
          i = paramInt;
        } else if (object instanceof Float) {
          paramInt = 102;
          i = paramInt;
        } else if (object instanceof Integer) {
          paramInt = 105;
          i = paramInt;
        } else if (object instanceof Long) {
          paramInt = 108;
          i = paramInt;
        } else if (object instanceof Short) {
          paramInt = 115;
          i = paramInt;
        } else {
          paramInt = 0;
          i = paramInt;
        } 
        if (i != 0) {
          paramStringBuilder.append(i);
          paramStringBuilder.append('.');
          paramStringBuilder.append(Uri.encode(str));
          paramStringBuilder.append('=');
          paramStringBuilder.append(Uri.encode(object.toString()));
          paramStringBuilder.append(';');
        } 
      }  
  }
  
  public Intent addCategory(String paramString) {
    if (this.mCategories == null)
      this.mCategories = new ArraySet(); 
    this.mCategories.add(paramString.intern());
    return this;
  }
  
  public Intent addFlags(int paramInt) {
    this.mFlags |= paramInt;
    return this;
  }
  
  public boolean canStripForHistory() {
    boolean bool;
    Bundle bundle = this.mExtras;
    if ((bundle != null && bundle.isParcelled()) || this.mClipData != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Object clone() {
    return new Intent(this);
  }
  
  public Intent cloneFilter() {
    return new Intent(this, 1);
  }
  
  public int describeContents() {
    boolean bool;
    Bundle bundle = this.mExtras;
    if (bundle != null) {
      bool = bundle.describeContents();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream) {
    dumpDebugWithoutFieldId(paramProtoOutputStream, true, true, true, false);
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    dumpDebug(paramProtoOutputStream, paramLong, true, true, true, false);
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    paramLong = paramProtoOutputStream.start(paramLong);
    dumpDebugWithoutFieldId(paramProtoOutputStream, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
    paramProtoOutputStream.end(paramLong);
  }
  
  public int fillIn(Intent paramIntent, int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore #4
    //   5: iload_3
    //   6: istore #5
    //   8: aload_1
    //   9: getfield mAction : Ljava/lang/String;
    //   12: ifnull -> 44
    //   15: aload_0
    //   16: getfield mAction : Ljava/lang/String;
    //   19: ifnull -> 31
    //   22: iload_3
    //   23: istore #5
    //   25: iload_2
    //   26: iconst_1
    //   27: iand
    //   28: ifeq -> 44
    //   31: aload_0
    //   32: aload_1
    //   33: getfield mAction : Ljava/lang/String;
    //   36: putfield mAction : Ljava/lang/String;
    //   39: iconst_0
    //   40: iconst_1
    //   41: ior
    //   42: istore #5
    //   44: aload_1
    //   45: getfield mData : Landroid/net/Uri;
    //   48: ifnonnull -> 65
    //   51: iload #5
    //   53: istore #6
    //   55: iload #4
    //   57: istore_3
    //   58: aload_1
    //   59: getfield mType : Ljava/lang/String;
    //   62: ifnull -> 116
    //   65: aload_0
    //   66: getfield mData : Landroid/net/Uri;
    //   69: ifnonnull -> 79
    //   72: aload_0
    //   73: getfield mType : Ljava/lang/String;
    //   76: ifnull -> 92
    //   79: iload #5
    //   81: istore #6
    //   83: iload #4
    //   85: istore_3
    //   86: iload_2
    //   87: iconst_2
    //   88: iand
    //   89: ifeq -> 116
    //   92: aload_0
    //   93: aload_1
    //   94: getfield mData : Landroid/net/Uri;
    //   97: putfield mData : Landroid/net/Uri;
    //   100: aload_0
    //   101: aload_1
    //   102: getfield mType : Ljava/lang/String;
    //   105: putfield mType : Ljava/lang/String;
    //   108: iload #5
    //   110: iconst_2
    //   111: ior
    //   112: istore #6
    //   114: iconst_1
    //   115: istore_3
    //   116: iload #6
    //   118: istore #5
    //   120: aload_1
    //   121: getfield mIdentifier : Ljava/lang/String;
    //   124: ifnull -> 162
    //   127: aload_0
    //   128: getfield mIdentifier : Ljava/lang/String;
    //   131: ifnull -> 146
    //   134: iload #6
    //   136: istore #5
    //   138: iload_2
    //   139: sipush #256
    //   142: iand
    //   143: ifeq -> 162
    //   146: aload_0
    //   147: aload_1
    //   148: getfield mIdentifier : Ljava/lang/String;
    //   151: putfield mIdentifier : Ljava/lang/String;
    //   154: iload #6
    //   156: sipush #256
    //   159: ior
    //   160: istore #5
    //   162: iload #5
    //   164: istore #6
    //   166: aload_1
    //   167: getfield mCategories : Landroid/util/ArraySet;
    //   170: ifnull -> 218
    //   173: aload_0
    //   174: getfield mCategories : Landroid/util/ArraySet;
    //   177: ifnull -> 190
    //   180: iload #5
    //   182: istore #6
    //   184: iload_2
    //   185: iconst_4
    //   186: iand
    //   187: ifeq -> 218
    //   190: aload_1
    //   191: getfield mCategories : Landroid/util/ArraySet;
    //   194: ifnull -> 212
    //   197: aload_0
    //   198: new android/util/ArraySet
    //   201: dup
    //   202: aload_1
    //   203: getfield mCategories : Landroid/util/ArraySet;
    //   206: invokespecial <init> : (Landroid/util/ArraySet;)V
    //   209: putfield mCategories : Landroid/util/ArraySet;
    //   212: iload #5
    //   214: iconst_4
    //   215: ior
    //   216: istore #6
    //   218: iload #6
    //   220: istore #5
    //   222: aload_1
    //   223: getfield mPackage : Ljava/lang/String;
    //   226: ifnull -> 273
    //   229: aload_0
    //   230: getfield mPackage : Ljava/lang/String;
    //   233: ifnull -> 247
    //   236: iload #6
    //   238: istore #5
    //   240: iload_2
    //   241: bipush #16
    //   243: iand
    //   244: ifeq -> 273
    //   247: iload #6
    //   249: istore #5
    //   251: aload_0
    //   252: getfield mSelector : Landroid/content/Intent;
    //   255: ifnonnull -> 273
    //   258: aload_0
    //   259: aload_1
    //   260: getfield mPackage : Ljava/lang/String;
    //   263: putfield mPackage : Ljava/lang/String;
    //   266: iload #6
    //   268: bipush #16
    //   270: ior
    //   271: istore #5
    //   273: iload #5
    //   275: istore #4
    //   277: aload_1
    //   278: getfield mSelector : Landroid/content/Intent;
    //   281: ifnull -> 333
    //   284: iload #5
    //   286: istore #4
    //   288: iload_2
    //   289: bipush #64
    //   291: iand
    //   292: ifeq -> 333
    //   295: iload #5
    //   297: istore #4
    //   299: aload_0
    //   300: getfield mPackage : Ljava/lang/String;
    //   303: ifnonnull -> 333
    //   306: aload_0
    //   307: new android/content/Intent
    //   310: dup
    //   311: aload_1
    //   312: getfield mSelector : Landroid/content/Intent;
    //   315: invokespecial <init> : (Landroid/content/Intent;)V
    //   318: putfield mSelector : Landroid/content/Intent;
    //   321: aload_0
    //   322: aconst_null
    //   323: putfield mPackage : Ljava/lang/String;
    //   326: iload #5
    //   328: bipush #64
    //   330: ior
    //   331: istore #4
    //   333: iload #4
    //   335: istore #5
    //   337: iload_3
    //   338: istore #6
    //   340: aload_1
    //   341: getfield mClipData : Landroid/content/ClipData;
    //   344: ifnull -> 388
    //   347: aload_0
    //   348: getfield mClipData : Landroid/content/ClipData;
    //   351: ifnull -> 369
    //   354: iload #4
    //   356: istore #5
    //   358: iload_3
    //   359: istore #6
    //   361: iload_2
    //   362: sipush #128
    //   365: iand
    //   366: ifeq -> 388
    //   369: aload_0
    //   370: aload_1
    //   371: getfield mClipData : Landroid/content/ClipData;
    //   374: putfield mClipData : Landroid/content/ClipData;
    //   377: iload #4
    //   379: sipush #128
    //   382: ior
    //   383: istore #5
    //   385: iconst_1
    //   386: istore #6
    //   388: aload_1
    //   389: getfield mComponent : Landroid/content/ComponentName;
    //   392: astore #7
    //   394: iload #5
    //   396: istore_3
    //   397: aload #7
    //   399: ifnull -> 424
    //   402: iload #5
    //   404: istore_3
    //   405: iload_2
    //   406: bipush #8
    //   408: iand
    //   409: ifeq -> 424
    //   412: aload_0
    //   413: aload #7
    //   415: putfield mComponent : Landroid/content/ComponentName;
    //   418: iload #5
    //   420: bipush #8
    //   422: ior
    //   423: istore_3
    //   424: aload_0
    //   425: aload_0
    //   426: getfield mFlags : I
    //   429: aload_1
    //   430: getfield mFlags : I
    //   433: ior
    //   434: putfield mFlags : I
    //   437: iload_3
    //   438: istore #5
    //   440: aload_1
    //   441: getfield mSourceBounds : Landroid/graphics/Rect;
    //   444: ifnull -> 485
    //   447: aload_0
    //   448: getfield mSourceBounds : Landroid/graphics/Rect;
    //   451: ifnull -> 464
    //   454: iload_3
    //   455: istore #5
    //   457: iload_2
    //   458: bipush #32
    //   460: iand
    //   461: ifeq -> 485
    //   464: aload_0
    //   465: new android/graphics/Rect
    //   468: dup
    //   469: aload_1
    //   470: getfield mSourceBounds : Landroid/graphics/Rect;
    //   473: invokespecial <init> : (Landroid/graphics/Rect;)V
    //   476: putfield mSourceBounds : Landroid/graphics/Rect;
    //   479: iload_3
    //   480: bipush #32
    //   482: ior
    //   483: istore #5
    //   485: aload_0
    //   486: getfield mExtras : Landroid/os/Bundle;
    //   489: ifnonnull -> 522
    //   492: iload #6
    //   494: istore_2
    //   495: aload_1
    //   496: getfield mExtras : Landroid/os/Bundle;
    //   499: ifnull -> 583
    //   502: aload_0
    //   503: new android/os/Bundle
    //   506: dup
    //   507: aload_1
    //   508: getfield mExtras : Landroid/os/Bundle;
    //   511: invokespecial <init> : (Landroid/os/Bundle;)V
    //   514: putfield mExtras : Landroid/os/Bundle;
    //   517: iconst_1
    //   518: istore_2
    //   519: goto -> 583
    //   522: iload #6
    //   524: istore_2
    //   525: aload_1
    //   526: getfield mExtras : Landroid/os/Bundle;
    //   529: ifnull -> 583
    //   532: new android/os/Bundle
    //   535: astore #7
    //   537: aload #7
    //   539: aload_1
    //   540: getfield mExtras : Landroid/os/Bundle;
    //   543: invokespecial <init> : (Landroid/os/Bundle;)V
    //   546: aload #7
    //   548: aload_0
    //   549: getfield mExtras : Landroid/os/Bundle;
    //   552: invokevirtual putAll : (Landroid/os/Bundle;)V
    //   555: aload_0
    //   556: aload #7
    //   558: putfield mExtras : Landroid/os/Bundle;
    //   561: iconst_1
    //   562: istore_2
    //   563: goto -> 583
    //   566: astore #7
    //   568: ldc_w 'Intent'
    //   571: ldc_w 'Failure filling in extras'
    //   574: aload #7
    //   576: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   579: pop
    //   580: iload #6
    //   582: istore_2
    //   583: iload_2
    //   584: ifeq -> 612
    //   587: aload_0
    //   588: getfield mContentUserHint : I
    //   591: bipush #-2
    //   593: if_icmpne -> 612
    //   596: aload_1
    //   597: getfield mContentUserHint : I
    //   600: istore_2
    //   601: iload_2
    //   602: bipush #-2
    //   604: if_icmpeq -> 612
    //   607: aload_0
    //   608: iload_2
    //   609: putfield mContentUserHint : I
    //   612: iload #5
    //   614: ireturn
    // Exception table:
    //   from	to	target	type
    //   532	561	566	java/lang/RuntimeException
  }
  
  public boolean filterEquals(Intent paramIntent) {
    return (paramIntent == null) ? false : (!Objects.equals(this.mAction, paramIntent.mAction) ? false : (!Objects.equals(this.mData, paramIntent.mData) ? false : (!Objects.equals(this.mType, paramIntent.mType) ? false : (!Objects.equals(this.mIdentifier, paramIntent.mIdentifier) ? false : (((!hasPackageEquivalentComponent() || !paramIntent.hasPackageEquivalentComponent()) && !Objects.equals(this.mPackage, paramIntent.mPackage)) ? false : (!Objects.equals(this.mComponent, paramIntent.mComponent) ? false : (!!Objects.equals(this.mCategories, paramIntent.mCategories))))))));
  }
  
  public int filterHashCode() {
    int i = 0;
    String str2 = this.mAction;
    if (str2 != null)
      i = 0 + str2.hashCode(); 
    Uri uri = this.mData;
    int j = i;
    if (uri != null)
      j = i + uri.hashCode(); 
    String str1 = this.mType;
    i = j;
    if (str1 != null)
      i = j + str1.hashCode(); 
    str1 = this.mIdentifier;
    j = i;
    if (str1 != null)
      j = i + str1.hashCode(); 
    str1 = this.mPackage;
    i = j;
    if (str1 != null)
      i = j + str1.hashCode(); 
    ComponentName componentName = this.mComponent;
    j = i;
    if (componentName != null)
      j = i + componentName.hashCode(); 
    ArraySet<String> arraySet = this.mCategories;
    i = j;
    if (arraySet != null)
      i = j + arraySet.hashCode(); 
    return i;
  }
  
  public void fixUris(int paramInt) {
    Uri uri1;
    Uri uri2 = getData();
    if (uri2 != null)
      this.mData = ContentProvider.maybeAddUserId(uri2, paramInt); 
    ClipData clipData = this.mClipData;
    if (clipData != null)
      clipData.fixUris(paramInt); 
    String str = getAction();
    if ("android.intent.action.SEND".equals(str)) {
      uri1 = getParcelableExtra("android.intent.extra.STREAM");
      if (uri1 != null)
        putExtra("android.intent.extra.STREAM", (Parcelable)ContentProvider.maybeAddUserId(uri1, paramInt)); 
    } else if ("android.intent.action.SEND_MULTIPLE".equals(uri1)) {
      ArrayList<Parcelable> arrayList = getParcelableArrayListExtra("android.intent.extra.STREAM");
      if (arrayList != null) {
        ArrayList<Uri> arrayList1 = new ArrayList();
        for (byte b = 0; b < arrayList.size(); b++)
          arrayList1.add(ContentProvider.maybeAddUserId((Uri)arrayList.get(b), paramInt)); 
        putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)arrayList1);
      } 
    } else if (isImageCaptureIntent()) {
      uri1 = getParcelableExtra("output");
      if (uri1 != null)
        putExtra("output", (Parcelable)ContentProvider.maybeAddUserId(uri1, paramInt)); 
    } 
  }
  
  public String getAction() {
    return this.mAction;
  }
  
  public boolean[] getBooleanArrayExtra(String paramString) {
    boolean[] arrayOfBoolean;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayOfBoolean = bundle.getBooleanArray(paramString);
    } 
    return arrayOfBoolean;
  }
  
  public boolean getBooleanExtra(String paramString, boolean paramBoolean) {
    Bundle bundle = this.mExtras;
    if (bundle != null)
      paramBoolean = bundle.getBoolean(paramString, paramBoolean); 
    return paramBoolean;
  }
  
  public Bundle getBundleExtra(String paramString) {
    Bundle bundle1;
    Bundle bundle2 = this.mExtras;
    if (bundle2 == null) {
      paramString = null;
    } else {
      bundle1 = bundle2.getBundle(paramString);
    } 
    return bundle1;
  }
  
  public byte[] getByteArrayExtra(String paramString) {
    byte[] arrayOfByte;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayOfByte = bundle.getByteArray(paramString);
    } 
    return arrayOfByte;
  }
  
  public byte getByteExtra(String paramString, byte paramByte) {
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      byte b = paramByte;
      paramByte = b;
    } else {
      byte b = bundle.getByte(paramString, paramByte).byteValue();
      paramByte = b;
    } 
    return paramByte;
  }
  
  public Set<String> getCategories() {
    return (Set<String>)this.mCategories;
  }
  
  public char[] getCharArrayExtra(String paramString) {
    char[] arrayOfChar;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayOfChar = bundle.getCharArray(paramString);
    } 
    return arrayOfChar;
  }
  
  public char getCharExtra(String paramString, char paramChar) {
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      char c = paramChar;
      paramChar = c;
    } else {
      char c = bundle.getChar(paramString, paramChar);
      paramChar = c;
    } 
    return paramChar;
  }
  
  public CharSequence[] getCharSequenceArrayExtra(String paramString) {
    CharSequence[] arrayOfCharSequence;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayOfCharSequence = bundle.getCharSequenceArray(paramString);
    } 
    return arrayOfCharSequence;
  }
  
  public ArrayList<CharSequence> getCharSequenceArrayListExtra(String paramString) {
    ArrayList<CharSequence> arrayList;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayList = bundle.getCharSequenceArrayList(paramString);
    } 
    return arrayList;
  }
  
  public CharSequence getCharSequenceExtra(String paramString) {
    CharSequence charSequence;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      charSequence = bundle.getCharSequence(paramString);
    } 
    return charSequence;
  }
  
  public ClipData getClipData() {
    return this.mClipData;
  }
  
  public ComponentName getComponent() {
    return this.mComponent;
  }
  
  public int getContentUserHint() {
    return this.mContentUserHint;
  }
  
  public Uri getData() {
    return this.mData;
  }
  
  public String getDataString() {
    Uri uri = this.mData;
    if (uri != null) {
      String str = uri.toString();
    } else {
      uri = null;
    } 
    return (String)uri;
  }
  
  public double[] getDoubleArrayExtra(String paramString) {
    double[] arrayOfDouble;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayOfDouble = bundle.getDoubleArray(paramString);
    } 
    return arrayOfDouble;
  }
  
  public double getDoubleExtra(String paramString, double paramDouble) {
    Bundle bundle = this.mExtras;
    if (bundle != null)
      paramDouble = bundle.getDouble(paramString, paramDouble); 
    return paramDouble;
  }
  
  @Deprecated
  public Object getExtra(String paramString) {
    return getExtra(paramString, null);
  }
  
  @Deprecated
  public Object getExtra(String paramString, Object paramObject) {
    Bundle bundle = this.mExtras;
    Object object = paramObject;
    if (bundle != null) {
      Object object1 = bundle.get(paramString);
      object = paramObject;
      if (object1 != null)
        object = object1; 
    } 
    return object;
  }
  
  public Bundle getExtras() {
    Bundle bundle;
    if (this.mExtras != null) {
      bundle = new Bundle(this.mExtras);
    } else {
      bundle = null;
    } 
    return bundle;
  }
  
  public int getFlags() {
    return this.mFlags;
  }
  
  public float[] getFloatArrayExtra(String paramString) {
    float[] arrayOfFloat;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayOfFloat = bundle.getFloatArray(paramString);
    } 
    return arrayOfFloat;
  }
  
  public float getFloatExtra(String paramString, float paramFloat) {
    Bundle bundle = this.mExtras;
    if (bundle != null)
      paramFloat = bundle.getFloat(paramString, paramFloat); 
    return paramFloat;
  }
  
  @Deprecated
  public IBinder getIBinderExtra(String paramString) {
    IBinder iBinder;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      iBinder = bundle.getIBinder(paramString);
    } 
    return iBinder;
  }
  
  public String getIdentifier() {
    return this.mIdentifier;
  }
  
  public int[] getIntArrayExtra(String paramString) {
    int[] arrayOfInt;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayOfInt = bundle.getIntArray(paramString);
    } 
    return arrayOfInt;
  }
  
  public int getIntExtra(String paramString, int paramInt) {
    Bundle bundle = this.mExtras;
    if (bundle != null)
      paramInt = bundle.getInt(paramString, paramInt); 
    return paramInt;
  }
  
  public ArrayList<Integer> getIntegerArrayListExtra(String paramString) {
    ArrayList<Integer> arrayList;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayList = bundle.getIntegerArrayList(paramString);
    } 
    return arrayList;
  }
  
  public String getLaunchToken() {
    return this.mLaunchToken;
  }
  
  public long[] getLongArrayExtra(String paramString) {
    long[] arrayOfLong;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayOfLong = bundle.getLongArray(paramString);
    } 
    return arrayOfLong;
  }
  
  public long getLongExtra(String paramString, long paramLong) {
    Bundle bundle = this.mExtras;
    if (bundle != null)
      paramLong = bundle.getLong(paramString, paramLong); 
    return paramLong;
  }
  
  public String getPackage() {
    return this.mPackage;
  }
  
  public Parcelable[] getParcelableArrayExtra(String paramString) {
    Parcelable[] arrayOfParcelable;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayOfParcelable = bundle.getParcelableArray(paramString);
    } 
    return arrayOfParcelable;
  }
  
  public <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(String paramString) {
    ArrayList<T> arrayList;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayList = bundle.getParcelableArrayList(paramString);
    } 
    return arrayList;
  }
  
  public <T extends Parcelable> T getParcelableExtra(String paramString) {
    Parcelable parcelable;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      parcelable = bundle.getParcelable(paramString);
    } 
    return (T)parcelable;
  }
  
  public String getScheme() {
    Uri uri = this.mData;
    if (uri != null) {
      String str = uri.getScheme();
    } else {
      uri = null;
    } 
    return (String)uri;
  }
  
  public Intent getSelector() {
    return this.mSelector;
  }
  
  public Serializable getSerializableExtra(String paramString) {
    Serializable serializable;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      serializable = bundle.getSerializable(paramString);
    } 
    return serializable;
  }
  
  public short[] getShortArrayExtra(String paramString) {
    short[] arrayOfShort;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayOfShort = bundle.getShortArray(paramString);
    } 
    return arrayOfShort;
  }
  
  public short getShortExtra(String paramString, short paramShort) {
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      short s = paramShort;
      paramShort = s;
    } else {
      short s = bundle.getShort(paramString, paramShort);
      paramShort = s;
    } 
    return paramShort;
  }
  
  public Rect getSourceBounds() {
    return this.mSourceBounds;
  }
  
  public String[] getStringArrayExtra(String paramString) {
    String[] arrayOfString;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayOfString = bundle.getStringArray(paramString);
    } 
    return arrayOfString;
  }
  
  public ArrayList<String> getStringArrayListExtra(String paramString) {
    ArrayList<String> arrayList;
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      arrayList = bundle.getStringArrayList(paramString);
    } 
    return arrayList;
  }
  
  public String getStringExtra(String paramString) {
    Bundle bundle = this.mExtras;
    if (bundle == null) {
      paramString = null;
    } else {
      paramString = bundle.getString(paramString);
    } 
    return paramString;
  }
  
  public String getType() {
    return this.mType;
  }
  
  public boolean hasCategory(String paramString) {
    boolean bool;
    ArraySet<String> arraySet = this.mCategories;
    if (arraySet != null && arraySet.contains(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasExtra(String paramString) {
    boolean bool;
    Bundle bundle = this.mExtras;
    if (bundle != null && bundle.containsKey(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasFileDescriptors() {
    boolean bool;
    Bundle bundle = this.mExtras;
    if (bundle != null && bundle.hasFileDescriptors()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasWebURI() {
    Uri uri = getData();
    boolean bool = false;
    if (uri == null)
      return false; 
    String str = getScheme();
    if (TextUtils.isEmpty(str))
      return false; 
    if (str.equals("http") || str.equals("https"))
      bool = true; 
    return bool;
  }
  
  public boolean isDocument() {
    boolean bool;
    if ((this.mFlags & 0x80000) == 524288) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isExcludingStopped() {
    boolean bool;
    if ((this.mFlags & 0x30) == 16) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isImplicitImageCaptureIntent() {
    boolean bool;
    if (this.mPackage == null && this.mComponent == null && isImageCaptureIntent()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isWebIntent() {
    boolean bool;
    if ("android.intent.action.VIEW".equals(this.mAction) && hasWebURI()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Intent maybeStripForHistory() {
    return !canStripForHistory() ? this : new Intent(this, 2);
  }
  
  public boolean migrateExtraStreamToClipData() {
    return migrateExtraStreamToClipData((Context)AppGlobals.getInitialApplication());
  }
  
  public boolean migrateExtraStreamToClipData(Context paramContext) {
    Intent intent;
    CharSequence charSequence;
    Bundle bundle = this.mExtras;
    if (bundle != null && bundle.isParcelled())
      return false; 
    if (getClipData() != null)
      return false; 
    String str = getAction();
    if ("android.intent.action.CHOOSER".equals(str)) {
      boolean bool1 = false;
      boolean bool2 = false;
      try {
        intent = getParcelableExtra("android.intent.extra.INTENT");
        if (intent != null) {
          bool2 = intent.migrateExtraStreamToClipData(paramContext);
          int i = false | bool2;
        } 
      } catch (ClassCastException classCastException) {
        bool2 = bool1;
      } 
      bool1 = bool2;
      try {
        Parcelable[] arrayOfParcelable = getParcelableArrayExtra("android.intent.extra.INITIAL_INTENTS");
        boolean bool = bool2;
        if (arrayOfParcelable != null) {
          byte b = 0;
          while (true) {
            bool = bool2;
            bool1 = bool2;
            if (b < arrayOfParcelable.length) {
              bool1 = bool2;
              intent = (Intent)arrayOfParcelable[b];
              bool1 = bool2;
              if (intent != null) {
                bool1 = bool2;
                bool = intent.migrateExtraStreamToClipData(paramContext);
                bool1 = bool2 | bool;
              } 
              b++;
              bool2 = bool1;
              continue;
            } 
            break;
          } 
        } 
        bool1 = bool;
      } catch (ClassCastException classCastException) {}
      return bool1;
    } 
    if ("android.intent.action.SEND".equals(intent)) {
      try {
        Uri uri = getParcelableExtra("android.intent.extra.STREAM");
        charSequence = getCharSequenceExtra("android.intent.extra.TEXT");
        String str1 = getStringExtra("android.intent.extra.HTML_TEXT");
        if (uri != null || charSequence != null || str1 != null) {
          ClipData clipData = new ClipData();
          String str2 = getType();
          ClipData.Item item = new ClipData.Item();
          this(charSequence, str1, null, uri);
          this(null, new String[] { str2 }, item);
          setClipData(clipData);
          addFlags(1);
          return true;
        } 
      } catch (ClassCastException classCastException) {}
    } else if ("android.intent.action.SEND_MULTIPLE".equals(charSequence)) {
      try {
        ArrayList<Parcelable> arrayList1 = getParcelableArrayListExtra("android.intent.extra.STREAM");
        ArrayList<CharSequence> arrayList2 = getCharSequenceArrayListExtra("android.intent.extra.TEXT");
        ArrayList<String> arrayList = getStringArrayListExtra("android.intent.extra.HTML_TEXT");
        int j = -1;
        if (arrayList1 != null)
          j = arrayList1.size(); 
        int i = j;
        if (arrayList2 != null) {
          if (j >= 0 && j != arrayList2.size())
            return false; 
          i = arrayList2.size();
        } 
        j = i;
        if (arrayList != null) {
          if (i >= 0 && i != arrayList.size())
            return false; 
          j = arrayList.size();
        } 
        if (j > 0) {
          ClipData clipData = new ClipData();
          String str1 = getType();
          ClipData.Item item = makeClipItem((ArrayList)arrayList1, arrayList2, arrayList, 0);
          this(null, new String[] { str1 }, item);
          for (i = 1; i < j; i++)
            clipData.addItem(makeClipItem((ArrayList)arrayList1, arrayList2, arrayList, i)); 
          setClipData(clipData);
          addFlags(1);
          return true;
        } 
      } catch (ClassCastException classCastException) {}
    } else if (isImageCaptureIntent()) {
      try {
        Uri uri = getParcelableExtra("output");
        if (uri != null) {
          Uri uri1 = maybeConvertFileToContentUri((Context)classCastException, uri);
          putExtra("output", (Parcelable)uri1);
          setClipData(ClipData.newRawUri("", uri1));
          addFlags(3);
          return true;
        } 
      } catch (ClassCastException classCastException1) {
        return false;
      } 
    } 
    return false;
  }
  
  public void prepareToEnterProcess() {
    setDefusable(true);
    Intent intent = this.mSelector;
    if (intent != null)
      intent.prepareToEnterProcess(); 
    ClipData clipData = this.mClipData;
    if (clipData != null)
      clipData.prepareToEnterProcess(); 
    if (this.mContentUserHint != -2 && UserHandle.getAppId(Process.myUid()) != 1000) {
      fixUris(this.mContentUserHint);
      this.mContentUserHint = -2;
    } 
  }
  
  public void prepareToLeaveProcess(Context paramContext) {
    boolean bool;
    ComponentName componentName = this.mComponent;
    if (componentName == null || !Objects.equals(componentName.getPackageName(), paramContext.getPackageName())) {
      bool = true;
    } else {
      bool = false;
    } 
    prepareToLeaveProcess(bool);
  }
  
  public void prepareToLeaveProcess(boolean paramBoolean) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: iconst_0
    //   4: invokevirtual setAllowFds : (Z)V
    //   7: aload_0
    //   8: getfield mSelector : Landroid/content/Intent;
    //   11: astore_3
    //   12: aload_3
    //   13: ifnull -> 21
    //   16: aload_3
    //   17: iload_1
    //   18: invokevirtual prepareToLeaveProcess : (Z)V
    //   21: aload_0
    //   22: getfield mClipData : Landroid/content/ClipData;
    //   25: astore_3
    //   26: aload_3
    //   27: ifnull -> 39
    //   30: aload_3
    //   31: iload_1
    //   32: aload_0
    //   33: invokevirtual getFlags : ()I
    //   36: invokevirtual prepareToLeaveProcess : (ZI)V
    //   39: aload_0
    //   40: getfield mExtras : Landroid/os/Bundle;
    //   43: astore_3
    //   44: aload_3
    //   45: ifnull -> 81
    //   48: aload_3
    //   49: invokevirtual isParcelled : ()Z
    //   52: ifne -> 81
    //   55: aload_0
    //   56: getfield mExtras : Landroid/os/Bundle;
    //   59: ldc_w 'android.intent.extra.INTENT'
    //   62: invokevirtual get : (Ljava/lang/String;)Ljava/lang/Object;
    //   65: astore_3
    //   66: aload_3
    //   67: instanceof android/content/Intent
    //   70: ifeq -> 81
    //   73: aload_3
    //   74: checkcast android/content/Intent
    //   77: iload_1
    //   78: invokevirtual prepareToLeaveProcess : (Z)V
    //   81: aload_0
    //   82: getfield mAction : Ljava/lang/String;
    //   85: ifnull -> 642
    //   88: aload_0
    //   89: getfield mData : Landroid/net/Uri;
    //   92: ifnull -> 642
    //   95: invokestatic vmFileUriExposureEnabled : ()Z
    //   98: ifeq -> 642
    //   101: iload_1
    //   102: ifeq -> 642
    //   105: aload_0
    //   106: getfield mAction : Ljava/lang/String;
    //   109: astore_3
    //   110: aload_3
    //   111: invokevirtual hashCode : ()I
    //   114: lookupswitch default -> 260, -2015721043 -> 529, -1823790459 -> 513, -1665311200 -> 497, -1514214344 -> 481, -1142424621 -> 464, -963871873 -> 448, -625887599 -> 431, 257177710 -> 415, 410719838 -> 398, 582421979 -> 381, 852070077 -> 364, 1412829408 -> 347, 1431947322 -> 330, 1599438242 -> 313, 1920444806 -> 296, 1964681210 -> 280, 2045140818 -> 263
    //   260: goto -> 546
    //   263: aload_3
    //   264: ldc_w 'android.intent.action.MEDIA_BAD_REMOVAL'
    //   267: invokevirtual equals : (Ljava/lang/Object;)Z
    //   270: ifeq -> 260
    //   273: bipush #7
    //   275: istore #4
    //   277: goto -> 549
    //   280: aload_3
    //   281: ldc_w 'android.intent.action.MEDIA_CHECKING'
    //   284: invokevirtual equals : (Ljava/lang/Object;)Z
    //   287: ifeq -> 260
    //   290: iconst_2
    //   291: istore #4
    //   293: goto -> 549
    //   296: aload_3
    //   297: ldc_w 'android.intent.action.PACKAGE_VERIFIED'
    //   300: invokevirtual equals : (Ljava/lang/Object;)Z
    //   303: ifeq -> 260
    //   306: bipush #15
    //   308: istore #4
    //   310: goto -> 549
    //   313: aload_3
    //   314: ldc_w 'android.intent.action.PACKAGE_ENABLE_ROLLBACK'
    //   317: invokevirtual equals : (Ljava/lang/Object;)Z
    //   320: ifeq -> 260
    //   323: bipush #16
    //   325: istore #4
    //   327: goto -> 549
    //   330: aload_3
    //   331: ldc_w 'android.intent.action.MEDIA_UNMOUNTABLE'
    //   334: invokevirtual equals : (Ljava/lang/Object;)Z
    //   337: ifeq -> 260
    //   340: bipush #8
    //   342: istore #4
    //   344: goto -> 549
    //   347: aload_3
    //   348: ldc_w 'android.intent.action.MEDIA_SCANNER_STARTED'
    //   351: invokevirtual equals : (Ljava/lang/Object;)Z
    //   354: ifeq -> 260
    //   357: bipush #10
    //   359: istore #4
    //   361: goto -> 549
    //   364: aload_3
    //   365: ldc_w 'android.intent.action.MEDIA_SCANNER_SCAN_FILE'
    //   368: invokevirtual equals : (Ljava/lang/Object;)Z
    //   371: ifeq -> 260
    //   374: bipush #12
    //   376: istore #4
    //   378: goto -> 549
    //   381: aload_3
    //   382: ldc_w 'android.intent.action.PACKAGE_NEEDS_VERIFICATION'
    //   385: invokevirtual equals : (Ljava/lang/Object;)Z
    //   388: ifeq -> 260
    //   391: bipush #13
    //   393: istore #4
    //   395: goto -> 549
    //   398: aload_3
    //   399: ldc_w 'android.intent.action.MEDIA_UNSHARED'
    //   402: invokevirtual equals : (Ljava/lang/Object;)Z
    //   405: ifeq -> 260
    //   408: bipush #6
    //   410: istore #4
    //   412: goto -> 549
    //   415: aload_3
    //   416: ldc_w 'android.intent.action.MEDIA_NOFS'
    //   419: invokevirtual equals : (Ljava/lang/Object;)Z
    //   422: ifeq -> 260
    //   425: iconst_3
    //   426: istore #4
    //   428: goto -> 549
    //   431: aload_3
    //   432: ldc_w 'android.intent.action.MEDIA_EJECT'
    //   435: invokevirtual equals : (Ljava/lang/Object;)Z
    //   438: ifeq -> 260
    //   441: bipush #9
    //   443: istore #4
    //   445: goto -> 549
    //   448: aload_3
    //   449: ldc_w 'android.intent.action.MEDIA_UNMOUNTED'
    //   452: invokevirtual equals : (Ljava/lang/Object;)Z
    //   455: ifeq -> 260
    //   458: iconst_1
    //   459: istore #4
    //   461: goto -> 549
    //   464: aload_3
    //   465: ldc_w 'android.intent.action.MEDIA_SCANNER_FINISHED'
    //   468: invokevirtual equals : (Ljava/lang/Object;)Z
    //   471: ifeq -> 260
    //   474: bipush #11
    //   476: istore #4
    //   478: goto -> 549
    //   481: aload_3
    //   482: ldc_w 'android.intent.action.MEDIA_MOUNTED'
    //   485: invokevirtual equals : (Ljava/lang/Object;)Z
    //   488: ifeq -> 260
    //   491: iconst_4
    //   492: istore #4
    //   494: goto -> 549
    //   497: aload_3
    //   498: ldc_w 'android.intent.action.MEDIA_REMOVED'
    //   501: invokevirtual equals : (Ljava/lang/Object;)Z
    //   504: ifeq -> 260
    //   507: iconst_0
    //   508: istore #4
    //   510: goto -> 549
    //   513: aload_3
    //   514: ldc_w 'android.intent.action.MEDIA_SHARED'
    //   517: invokevirtual equals : (Ljava/lang/Object;)Z
    //   520: ifeq -> 260
    //   523: iconst_5
    //   524: istore #4
    //   526: goto -> 549
    //   529: aload_3
    //   530: ldc_w 'android.intent.action.PACKAGE_NEEDS_INTEGRITY_VERIFICATION'
    //   533: invokevirtual equals : (Ljava/lang/Object;)Z
    //   536: ifeq -> 260
    //   539: bipush #14
    //   541: istore #4
    //   543: goto -> 549
    //   546: iconst_m1
    //   547: istore #4
    //   549: iload #4
    //   551: tableswitch default -> 632, 0 -> 642, 1 -> 642, 2 -> 642, 3 -> 642, 4 -> 642, 5 -> 642, 6 -> 642, 7 -> 642, 8 -> 642, 9 -> 642, 10 -> 642, 11 -> 642, 12 -> 642, 13 -> 642, 14 -> 642, 15 -> 642, 16 -> 642
    //   632: aload_0
    //   633: getfield mData : Landroid/net/Uri;
    //   636: ldc_w 'Intent.getData()'
    //   639: invokevirtual checkFileUriExposed : (Ljava/lang/String;)V
    //   642: aload_0
    //   643: getfield mAction : Ljava/lang/String;
    //   646: ifnull -> 756
    //   649: aload_0
    //   650: getfield mData : Landroid/net/Uri;
    //   653: ifnull -> 756
    //   656: invokestatic vmContentUriWithoutPermissionEnabled : ()Z
    //   659: ifeq -> 756
    //   662: iload_1
    //   663: ifeq -> 756
    //   666: aload_0
    //   667: getfield mAction : Ljava/lang/String;
    //   670: astore_3
    //   671: aload_3
    //   672: invokevirtual hashCode : ()I
    //   675: istore #4
    //   677: iload #4
    //   679: ldc_w -577088908
    //   682: if_icmpeq -> 712
    //   685: iload #4
    //   687: ldc_w 1662413067
    //   690: if_icmpeq -> 696
    //   693: goto -> 728
    //   696: aload_3
    //   697: ldc_w 'android.intent.action.PROVIDER_CHANGED'
    //   700: invokevirtual equals : (Ljava/lang/Object;)Z
    //   703: ifeq -> 693
    //   706: iload_2
    //   707: istore #4
    //   709: goto -> 731
    //   712: aload_3
    //   713: ldc_w 'android.provider.action.QUICK_CONTACT'
    //   716: invokevirtual equals : (Ljava/lang/Object;)Z
    //   719: ifeq -> 693
    //   722: iconst_1
    //   723: istore #4
    //   725: goto -> 731
    //   728: iconst_m1
    //   729: istore #4
    //   731: iload #4
    //   733: ifeq -> 756
    //   736: iload #4
    //   738: iconst_1
    //   739: if_icmpeq -> 756
    //   742: aload_0
    //   743: getfield mData : Landroid/net/Uri;
    //   746: ldc_w 'Intent.getData()'
    //   749: aload_0
    //   750: invokevirtual getFlags : ()I
    //   753: invokevirtual checkContentUriWithoutPermission : (Ljava/lang/String;I)V
    //   756: ldc_w 'android.intent.action.MEDIA_SCANNER_SCAN_FILE'
    //   759: aload_0
    //   760: getfield mAction : Ljava/lang/String;
    //   763: invokevirtual equals : (Ljava/lang/Object;)Z
    //   766: ifeq -> 910
    //   769: aload_0
    //   770: getfield mData : Landroid/net/Uri;
    //   773: astore_3
    //   774: aload_3
    //   775: ifnull -> 910
    //   778: ldc_w 'file'
    //   781: aload_3
    //   782: invokevirtual getScheme : ()Ljava/lang/String;
    //   785: invokevirtual equals : (Ljava/lang/Object;)Z
    //   788: ifeq -> 910
    //   791: iload_1
    //   792: ifeq -> 910
    //   795: invokestatic getInitialApplication : ()Landroid/app/Application;
    //   798: ldc_w android/os/storage/StorageManager
    //   801: invokevirtual getSystemService : (Ljava/lang/Class;)Ljava/lang/Object;
    //   804: checkcast android/os/storage/StorageManager
    //   807: astore #5
    //   809: new java/io/File
    //   812: dup
    //   813: aload_0
    //   814: getfield mData : Landroid/net/Uri;
    //   817: invokevirtual getPath : ()Ljava/lang/String;
    //   820: invokespecial <init> : (Ljava/lang/String;)V
    //   823: astore_3
    //   824: aload #5
    //   826: aload_3
    //   827: invokestatic myPid : ()I
    //   830: invokestatic myUid : ()I
    //   833: invokevirtual translateAppToSystem : (Ljava/io/File;II)Ljava/io/File;
    //   836: astore #6
    //   838: aload_3
    //   839: aload #6
    //   841: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   844: ifne -> 910
    //   847: new java/lang/StringBuilder
    //   850: dup
    //   851: invokespecial <init> : ()V
    //   854: astore #5
    //   856: aload #5
    //   858: ldc_w 'Translated '
    //   861: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   864: pop
    //   865: aload #5
    //   867: aload_3
    //   868: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   871: pop
    //   872: aload #5
    //   874: ldc_w ' to '
    //   877: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   880: pop
    //   881: aload #5
    //   883: aload #6
    //   885: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   888: pop
    //   889: ldc_w 'Intent'
    //   892: aload #5
    //   894: invokevirtual toString : ()Ljava/lang/String;
    //   897: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   900: pop
    //   901: aload_0
    //   902: aload #6
    //   904: invokestatic fromFile : (Ljava/io/File;)Landroid/net/Uri;
    //   907: putfield mData : Landroid/net/Uri;
    //   910: return
  }
  
  public void prepareToLeaveUser(int paramInt) {
    if (this.mContentUserHint == -2)
      this.mContentUserHint = paramInt; 
  }
  
  public Intent putCharSequenceArrayListExtra(String paramString, ArrayList<CharSequence> paramArrayList) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putCharSequenceArrayList(paramString, paramArrayList);
    return this;
  }
  
  public Intent putExtra(String paramString, byte paramByte) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putByte(paramString, paramByte);
    return this;
  }
  
  public Intent putExtra(String paramString, char paramChar) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putChar(paramString, paramChar);
    return this;
  }
  
  public Intent putExtra(String paramString, double paramDouble) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putDouble(paramString, paramDouble);
    return this;
  }
  
  public Intent putExtra(String paramString, float paramFloat) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putFloat(paramString, paramFloat);
    return this;
  }
  
  public Intent putExtra(String paramString, int paramInt) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putInt(paramString, paramInt);
    return this;
  }
  
  public Intent putExtra(String paramString, long paramLong) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putLong(paramString, paramLong);
    return this;
  }
  
  public Intent putExtra(String paramString, Bundle paramBundle) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putBundle(paramString, paramBundle);
    return this;
  }
  
  @Deprecated
  public Intent putExtra(String paramString, IBinder paramIBinder) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putIBinder(paramString, paramIBinder);
    return this;
  }
  
  public Intent putExtra(String paramString, Parcelable paramParcelable) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putParcelable(paramString, paramParcelable);
    return this;
  }
  
  public Intent putExtra(String paramString, Serializable paramSerializable) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putSerializable(paramString, paramSerializable);
    return this;
  }
  
  public Intent putExtra(String paramString, CharSequence paramCharSequence) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putCharSequence(paramString, paramCharSequence);
    return this;
  }
  
  public Intent putExtra(String paramString1, String paramString2) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putString(paramString1, paramString2);
    return this;
  }
  
  public Intent putExtra(String paramString, short paramShort) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putShort(paramString, paramShort);
    return this;
  }
  
  public Intent putExtra(String paramString, boolean paramBoolean) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putBoolean(paramString, paramBoolean);
    return this;
  }
  
  public Intent putExtra(String paramString, byte[] paramArrayOfbyte) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putByteArray(paramString, paramArrayOfbyte);
    return this;
  }
  
  public Intent putExtra(String paramString, char[] paramArrayOfchar) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putCharArray(paramString, paramArrayOfchar);
    return this;
  }
  
  public Intent putExtra(String paramString, double[] paramArrayOfdouble) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putDoubleArray(paramString, paramArrayOfdouble);
    return this;
  }
  
  public Intent putExtra(String paramString, float[] paramArrayOffloat) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putFloatArray(paramString, paramArrayOffloat);
    return this;
  }
  
  public Intent putExtra(String paramString, int[] paramArrayOfint) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putIntArray(paramString, paramArrayOfint);
    return this;
  }
  
  public Intent putExtra(String paramString, long[] paramArrayOflong) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putLongArray(paramString, paramArrayOflong);
    return this;
  }
  
  public Intent putExtra(String paramString, Parcelable[] paramArrayOfParcelable) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putParcelableArray(paramString, paramArrayOfParcelable);
    return this;
  }
  
  public Intent putExtra(String paramString, CharSequence[] paramArrayOfCharSequence) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putCharSequenceArray(paramString, paramArrayOfCharSequence);
    return this;
  }
  
  public Intent putExtra(String paramString, String[] paramArrayOfString) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putStringArray(paramString, paramArrayOfString);
    return this;
  }
  
  public Intent putExtra(String paramString, short[] paramArrayOfshort) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putShortArray(paramString, paramArrayOfshort);
    return this;
  }
  
  public Intent putExtra(String paramString, boolean[] paramArrayOfboolean) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putBooleanArray(paramString, paramArrayOfboolean);
    return this;
  }
  
  public Intent putExtras(Intent paramIntent) {
    Bundle bundle = paramIntent.mExtras;
    if (bundle != null) {
      Bundle bundle1 = this.mExtras;
      if (bundle1 == null) {
        this.mExtras = new Bundle(paramIntent.mExtras);
      } else {
        bundle1.putAll(bundle);
      } 
    } 
    return this;
  }
  
  public Intent putExtras(Bundle paramBundle) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putAll(paramBundle);
    return this;
  }
  
  public Intent putIntegerArrayListExtra(String paramString, ArrayList<Integer> paramArrayList) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putIntegerArrayList(paramString, paramArrayList);
    return this;
  }
  
  public Intent putParcelableArrayListExtra(String paramString, ArrayList<? extends Parcelable> paramArrayList) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putParcelableArrayList(paramString, paramArrayList);
    return this;
  }
  
  public Intent putStringArrayListExtra(String paramString, ArrayList<String> paramArrayList) {
    if (this.mExtras == null)
      this.mExtras = new Bundle(); 
    this.mExtras.putStringArrayList(paramString, paramArrayList);
    return this;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    setAction(paramParcel.readString8());
    this.mData = (Uri)Uri.CREATOR.createFromParcel(paramParcel);
    this.mType = paramParcel.readString8();
    this.mIdentifier = paramParcel.readString8();
    this.mFlags = paramParcel.readInt();
    this.mPackage = paramParcel.readString8();
    this.mComponent = ComponentName.readFromParcel(paramParcel);
    if (paramParcel.readInt() != 0)
      this.mSourceBounds = (Rect)Rect.CREATOR.createFromParcel(paramParcel); 
    int i = paramParcel.readInt();
    if (i > 0) {
      this.mCategories = new ArraySet();
      for (byte b = 0; b < i; b++)
        this.mCategories.add(paramParcel.readString8().intern()); 
    } else {
      this.mCategories = null;
    } 
    if (paramParcel.readInt() != 0)
      this.mSelector = new Intent(paramParcel); 
    if (paramParcel.readInt() != 0)
      this.mClipData = new ClipData(paramParcel); 
    this.mContentUserHint = paramParcel.readInt();
    this.mExtras = paramParcel.readBundle();
  }
  
  public void removeCategory(String paramString) {
    ArraySet<String> arraySet = this.mCategories;
    if (arraySet != null) {
      arraySet.remove(paramString);
      if (this.mCategories.size() == 0)
        this.mCategories = null; 
    } 
  }
  
  public void removeExtra(String paramString) {
    Bundle bundle = this.mExtras;
    if (bundle != null) {
      bundle.remove(paramString);
      if (this.mExtras.size() == 0)
        this.mExtras = null; 
    } 
  }
  
  public void removeFlags(int paramInt) {
    this.mFlags &= paramInt;
  }
  
  public void removeUnsafeExtras() {
    Bundle bundle = this.mExtras;
    if (bundle != null)
      this.mExtras = bundle.filterValues(); 
  }
  
  public Intent replaceExtras(Intent paramIntent) {
    if (paramIntent.mExtras != null) {
      Bundle bundle = new Bundle(paramIntent.mExtras);
    } else {
      paramIntent = null;
    } 
    this.mExtras = (Bundle)paramIntent;
    return this;
  }
  
  public Intent replaceExtras(Bundle paramBundle) {
    if (paramBundle != null) {
      paramBundle = new Bundle(paramBundle);
    } else {
      paramBundle = null;
    } 
    this.mExtras = paramBundle;
    return this;
  }
  
  public ComponentName resolveActivity(PackageManager paramPackageManager) {
    ComponentName componentName = this.mComponent;
    if (componentName != null)
      return componentName; 
    ResolveInfo resolveInfo = paramPackageManager.resolveActivity(this, 65536);
    return (resolveInfo != null) ? new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name) : null;
  }
  
  public ActivityInfo resolveActivityInfo(PackageManager paramPackageManager, int paramInt) {
    ActivityInfo activityInfo;
    PackageManager.NameNotFoundException nameNotFoundException2 = null;
    PackageManager.NameNotFoundException nameNotFoundException3 = null;
    ComponentName componentName = this.mComponent;
    if (componentName != null) {
      try {
        activityInfo = paramPackageManager.getActivityInfo(componentName, paramInt);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException1) {
        nameNotFoundException1 = nameNotFoundException3;
      } 
    } else {
      ResolveInfo resolveInfo = nameNotFoundException1.resolveActivity(this, 0x10000 | paramInt);
      nameNotFoundException1 = nameNotFoundException2;
      if (resolveInfo != null)
        activityInfo = resolveInfo.activityInfo; 
    } 
    return activityInfo;
  }
  
  public ComponentName resolveSystemService(PackageManager paramPackageManager, int paramInt) {
    ComponentName componentName1;
    ComponentName componentName2 = this.mComponent;
    if (componentName2 != null)
      return componentName2; 
    List<ResolveInfo> list = paramPackageManager.queryIntentServices(this, paramInt);
    if (list == null)
      return null; 
    paramPackageManager = null;
    for (paramInt = 0; paramInt < list.size(); paramInt++) {
      ResolveInfo resolveInfo = list.get(paramInt);
      if ((resolveInfo.serviceInfo.applicationInfo.flags & 0x1) != 0) {
        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.applicationInfo.packageName, resolveInfo.serviceInfo.name);
        if (paramPackageManager == null) {
          componentName1 = componentName;
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Multiple system services handle ");
          stringBuilder.append(this);
          stringBuilder.append(": ");
          stringBuilder.append(componentName1);
          stringBuilder.append(", ");
          stringBuilder.append(componentName);
          throw new IllegalStateException(stringBuilder.toString());
        } 
      } 
    } 
    return componentName1;
  }
  
  public String resolveType(ContentResolver paramContentResolver) {
    String str = this.mType;
    if (str != null)
      return str; 
    Uri uri = this.mData;
    return (uri != null && "content".equals(uri.getScheme())) ? paramContentResolver.getType(this.mData) : null;
  }
  
  public String resolveType(Context paramContext) {
    return resolveType(paramContext.getContentResolver());
  }
  
  public String resolveTypeIfNeeded(ContentResolver paramContentResolver) {
    return (this.mComponent != null) ? this.mType : resolveType(paramContentResolver);
  }
  
  public void saveToXml(XmlSerializer paramXmlSerializer) throws IOException {
    String str2 = this.mAction;
    if (str2 != null)
      paramXmlSerializer.attribute(null, "action", str2); 
    Uri uri = this.mData;
    if (uri != null)
      paramXmlSerializer.attribute(null, "data", uri.toString()); 
    String str1 = this.mType;
    if (str1 != null)
      paramXmlSerializer.attribute(null, "type", str1); 
    str1 = this.mIdentifier;
    if (str1 != null)
      paramXmlSerializer.attribute(null, "ident", str1); 
    ComponentName componentName = this.mComponent;
    if (componentName != null)
      paramXmlSerializer.attribute(null, "component", componentName.flattenToShortString()); 
    paramXmlSerializer.attribute(null, "flags", Integer.toHexString(getFlags()));
    if (this.mCategories != null) {
      paramXmlSerializer.startTag(null, "categories");
      for (int i = this.mCategories.size() - 1; i >= 0; i--)
        paramXmlSerializer.attribute(null, "category", (String)this.mCategories.valueAt(i)); 
      paramXmlSerializer.endTag(null, "categories");
    } 
  }
  
  public Intent setAction(String paramString) {
    if (paramString != null) {
      paramString = paramString.intern();
    } else {
      paramString = null;
    } 
    this.mAction = paramString;
    return this;
  }
  
  public void setAllowFds(boolean paramBoolean) {
    Bundle bundle = this.mExtras;
    if (bundle != null)
      bundle.setAllowFds(paramBoolean); 
  }
  
  public Intent setClass(Context paramContext, Class<?> paramClass) {
    this.mComponent = new ComponentName(paramContext, paramClass);
    return this;
  }
  
  public Intent setClassName(Context paramContext, String paramString) {
    this.mComponent = new ComponentName(paramContext, paramString);
    return this;
  }
  
  public Intent setClassName(String paramString1, String paramString2) {
    this.mComponent = new ComponentName(paramString1, paramString2);
    return this;
  }
  
  public void setClipData(ClipData paramClipData) {
    this.mClipData = paramClipData;
  }
  
  public Intent setComponent(ComponentName paramComponentName) {
    this.mComponent = paramComponentName;
    return this;
  }
  
  public Intent setData(Uri paramUri) {
    this.mData = paramUri;
    this.mType = null;
    return this;
  }
  
  public Intent setDataAndNormalize(Uri paramUri) {
    return setData(paramUri.normalizeScheme());
  }
  
  public Intent setDataAndType(Uri paramUri, String paramString) {
    this.mData = paramUri;
    this.mType = paramString;
    return this;
  }
  
  public Intent setDataAndTypeAndNormalize(Uri paramUri, String paramString) {
    return setDataAndType(paramUri.normalizeScheme(), normalizeMimeType(paramString));
  }
  
  public void setDefusable(boolean paramBoolean) {
    Bundle bundle = this.mExtras;
    if (bundle != null)
      bundle.setDefusable(paramBoolean); 
  }
  
  public void setExtrasClassLoader(ClassLoader paramClassLoader) {
    Bundle bundle = this.mExtras;
    if (bundle != null)
      bundle.setClassLoader(paramClassLoader); 
  }
  
  public Intent setFlags(int paramInt) {
    this.mFlags = paramInt;
    return this;
  }
  
  public Intent setIdentifier(String paramString) {
    this.mIdentifier = paramString;
    return this;
  }
  
  public void setLaunchToken(String paramString) {
    this.mLaunchToken = paramString;
  }
  
  public Intent setPackage(String paramString) {
    if (paramString == null || this.mSelector == null) {
      this.mPackage = paramString;
      return this;
    } 
    throw new IllegalArgumentException("Can't set package name when selector is already set");
  }
  
  public void setSelector(Intent paramIntent) {
    if (paramIntent != this) {
      if (paramIntent == null || this.mPackage == null) {
        this.mSelector = paramIntent;
        return;
      } 
      throw new IllegalArgumentException("Can't set selector when package name is already set");
    } 
    throw new IllegalArgumentException("Intent being set as a selector of itself");
  }
  
  public void setSourceBounds(Rect paramRect) {
    if (paramRect != null) {
      this.mSourceBounds = new Rect(paramRect);
    } else {
      this.mSourceBounds = null;
    } 
  }
  
  public Intent setType(String paramString) {
    this.mData = null;
    this.mType = paramString;
    return this;
  }
  
  public Intent setTypeAndNormalize(String paramString) {
    return setType(normalizeMimeType(paramString));
  }
  
  public String toInsecureString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("Intent { ");
    toShortString(stringBuilder, false, true, true, false);
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
  
  public String toInsecureStringWithClip() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("Intent { ");
    toShortString(stringBuilder, false, true, true, true);
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
  
  public String toShortString(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    StringBuilder stringBuilder = new StringBuilder(128);
    toShortString(stringBuilder, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
    return stringBuilder.toString();
  }
  
  public void toShortString(StringBuilder paramStringBuilder, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    byte b1 = 1;
    if (this.mAction != null) {
      paramStringBuilder.append("act=");
      paramStringBuilder.append(this.mAction);
      b1 = 0;
    } 
    byte b2 = b1;
    if (this.mCategories != null) {
      if (!b1)
        paramStringBuilder.append(' '); 
      b1 = 0;
      paramStringBuilder.append("cat=[");
      for (b2 = 0; b2 < this.mCategories.size(); b2++) {
        if (b2 > 0)
          paramStringBuilder.append(','); 
        paramStringBuilder.append((String)this.mCategories.valueAt(b2));
      } 
      paramStringBuilder.append("]");
      b2 = b1;
    } 
    byte b3 = b2;
    if (this.mData != null) {
      if (b2 == 0)
        paramStringBuilder.append(' '); 
      b3 = 0;
      paramStringBuilder.append("dat=");
      if (paramBoolean1) {
        paramStringBuilder.append(this.mData.toSafeString());
      } else {
        paramStringBuilder.append(this.mData);
      } 
    } 
    b1 = b3;
    if (this.mType != null) {
      if (b3 == 0)
        paramStringBuilder.append(' '); 
      b1 = 0;
      paramStringBuilder.append("typ=");
      paramStringBuilder.append(this.mType);
    } 
    b2 = b1;
    if (this.mIdentifier != null) {
      if (b1 == 0)
        paramStringBuilder.append(' '); 
      b2 = 0;
      paramStringBuilder.append("id=");
      paramStringBuilder.append(this.mIdentifier);
    } 
    b1 = b2;
    if (this.mFlags != 0) {
      if (b2 == 0)
        paramStringBuilder.append(' '); 
      b1 = 0;
      paramStringBuilder.append("flg=0x");
      paramStringBuilder.append(Integer.toHexString(this.mFlags));
    } 
    b2 = b1;
    if (this.mPackage != null) {
      if (b1 == 0)
        paramStringBuilder.append(' '); 
      b2 = 0;
      paramStringBuilder.append("pkg=");
      paramStringBuilder.append(this.mPackage);
    } 
    b3 = b2;
    if (paramBoolean2) {
      b3 = b2;
      if (this.mComponent != null) {
        if (b2 == 0)
          paramStringBuilder.append(' '); 
        b3 = 0;
        paramStringBuilder.append("cmp=");
        paramStringBuilder.append(this.mComponent.flattenToShortString());
      } 
    } 
    b1 = b3;
    if (this.mSourceBounds != null) {
      if (b3 == 0)
        paramStringBuilder.append(' '); 
      b1 = 0;
      paramStringBuilder.append("bnds=");
      paramStringBuilder.append(this.mSourceBounds.toShortString());
    } 
    b2 = b1;
    if (this.mClipData != null) {
      if (b1 == 0)
        paramStringBuilder.append(' '); 
      paramStringBuilder.append("clip={");
      if (paramBoolean4) {
        this.mClipData.toShortString(paramStringBuilder);
      } else {
        boolean bool;
        if (this.mClipData.getDescription() != null) {
          bool = this.mClipData.getDescription().toShortStringTypesOnly(paramStringBuilder) ^ true;
        } else {
          bool = true;
        } 
        this.mClipData.toShortStringShortItems(paramStringBuilder, bool);
      } 
      b2 = 0;
      paramStringBuilder.append('}');
    } 
    b1 = b2;
    if (paramBoolean3) {
      b1 = b2;
      if (this.mExtras != null) {
        if (b2 == 0)
          paramStringBuilder.append(' '); 
        b1 = 0;
        paramStringBuilder.append("(has extras)");
      } 
    } 
    if (this.mContentUserHint != -2) {
      if (b1 == 0)
        paramStringBuilder.append(' '); 
      paramStringBuilder.append("u=");
      paramStringBuilder.append(this.mContentUserHint);
    } 
    if (this.mSelector != null) {
      paramStringBuilder.append(" sel=");
      this.mSelector.toShortString(paramStringBuilder, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
      paramStringBuilder.append("}");
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("Intent { ");
    toShortString(stringBuilder, true, true, true, false);
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
  
  @Deprecated
  public String toURI() {
    return toUri(0);
  }
  
  public String toUri(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder(128);
    if ((paramInt & 0x2) != 0) {
      if (this.mPackage != null) {
        stringBuilder.append("android-app://");
        stringBuilder.append(this.mPackage);
        String str = null;
        Uri uri1 = this.mData;
        if (uri1 != null) {
          String str4 = uri1.getScheme();
          str = str4;
          if (str4 != null) {
            stringBuilder.append('/');
            stringBuilder.append(str4);
            String str5 = this.mData.getEncodedAuthority();
            str = str4;
            if (str5 != null) {
              stringBuilder.append('/');
              stringBuilder.append(str5);
              str = this.mData.getEncodedPath();
              if (str != null)
                stringBuilder.append(str); 
              str = this.mData.getEncodedQuery();
              if (str != null) {
                stringBuilder.append('?');
                stringBuilder.append(str);
              } 
              str5 = this.mData.getEncodedFragment();
              str = str4;
              if (str5 != null) {
                stringBuilder.append('#');
                stringBuilder.append(str5);
                str = str4;
              } 
            } 
          } 
        } 
        if (str == null) {
          str = "android.intent.action.MAIN";
        } else {
          str = "android.intent.action.VIEW";
        } 
        toUriFragment(stringBuilder, null, str, this.mPackage, paramInt);
        return stringBuilder.toString();
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Intent must include an explicit package name to build an android-app: ");
      stringBuilder1.append(this);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    String str1 = null;
    String str2 = null;
    String str3 = null;
    Uri uri = this.mData;
    if (uri != null) {
      String str = uri.toString();
      str1 = str3;
      str2 = str;
      if ((paramInt & 0x1) != 0) {
        int i = str.length();
        byte b = 0;
        while (true) {
          str1 = str3;
          str2 = str;
          if (b < i) {
            char c = str.charAt(b);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '.' || c == '-' || c == '+') {
              b++;
              continue;
            } 
            str1 = str3;
            str2 = str;
            if (c == ':') {
              str1 = str3;
              str2 = str;
              if (b > 0) {
                str1 = str.substring(0, b);
                stringBuilder.append("intent:");
                str2 = str.substring(b + 1);
              } 
            } 
          } 
          break;
        } 
      } 
      stringBuilder.append(str2);
    } else if ((paramInt & 0x1) != 0) {
      stringBuilder.append("intent:");
      str1 = str2;
    } 
    toUriFragment(stringBuilder, str1, "android.intent.action.VIEW", null, paramInt);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString8(this.mAction);
    Uri.writeToParcel(paramParcel, this.mData);
    paramParcel.writeString8(this.mType);
    paramParcel.writeString8(this.mIdentifier);
    paramParcel.writeInt(this.mFlags);
    paramParcel.writeString8(this.mPackage);
    ComponentName.writeToParcel(this.mComponent, paramParcel);
    if (this.mSourceBounds != null) {
      paramParcel.writeInt(1);
      this.mSourceBounds.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    ArraySet<String> arraySet = this.mCategories;
    if (arraySet != null) {
      int i = arraySet.size();
      paramParcel.writeInt(i);
      for (byte b = 0; b < i; b++)
        paramParcel.writeString8((String)this.mCategories.valueAt(b)); 
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mSelector != null) {
      paramParcel.writeInt(1);
      this.mSelector.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mClipData != null) {
      paramParcel.writeInt(1);
      this.mClipData.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.mContentUserHint);
    paramParcel.writeBundle(this.mExtras);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AccessUriMode {}
  
  public static interface CommandOptionHandler {
    boolean handleOption(String param1String, ShellCommand param1ShellCommand);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface CopyMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface FillInFlags {}
  
  public static final class FilterComparison {
    private final int mHashCode;
    
    private final Intent mIntent;
    
    public FilterComparison(Intent param1Intent) {
      this.mIntent = param1Intent;
      this.mHashCode = param1Intent.filterHashCode();
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object instanceof FilterComparison) {
        param1Object = ((FilterComparison)param1Object).mIntent;
        return this.mIntent.filterEquals((Intent)param1Object);
      } 
      return false;
    }
    
    public Intent getIntent() {
      return this.mIntent;
    }
    
    public int hashCode() {
      return this.mHashCode;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Flags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface GrantUriMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface MutableFlags {}
  
  public static class ShortcutIconResource implements Parcelable {
    public static final Parcelable.Creator<ShortcutIconResource> CREATOR = new Parcelable.Creator<ShortcutIconResource>() {
        public Intent.ShortcutIconResource createFromParcel(Parcel param2Parcel) {
          Intent.ShortcutIconResource shortcutIconResource = new Intent.ShortcutIconResource();
          shortcutIconResource.packageName = param2Parcel.readString8();
          shortcutIconResource.resourceName = param2Parcel.readString8();
          return shortcutIconResource;
        }
        
        public Intent.ShortcutIconResource[] newArray(int param2Int) {
          return new Intent.ShortcutIconResource[param2Int];
        }
      };
    
    public String packageName;
    
    public String resourceName;
    
    public static ShortcutIconResource fromContext(Context param1Context, int param1Int) {
      ShortcutIconResource shortcutIconResource = new ShortcutIconResource();
      shortcutIconResource.packageName = param1Context.getPackageName();
      shortcutIconResource.resourceName = param1Context.getResources().getResourceName(param1Int);
      return shortcutIconResource;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public String toString() {
      return this.resourceName;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeString8(this.packageName);
      param1Parcel.writeString8(this.resourceName);
    }
  }
  
  class null implements Parcelable.Creator<ShortcutIconResource> {
    public Intent.ShortcutIconResource createFromParcel(Parcel param1Parcel) {
      Intent.ShortcutIconResource shortcutIconResource = new Intent.ShortcutIconResource();
      shortcutIconResource.packageName = param1Parcel.readString8();
      shortcutIconResource.resourceName = param1Parcel.readString8();
      return shortcutIconResource;
    }
    
    public Intent.ShortcutIconResource[] newArray(int param1Int) {
      return new Intent.ShortcutIconResource[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface UriFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/Intent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
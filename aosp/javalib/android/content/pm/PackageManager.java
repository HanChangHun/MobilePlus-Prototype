package android.content.pm;

import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.app.AppDetailsActivity;
import android.app.PackageDeleteObserver;
import android.app.PropertyInvalidatedCache;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.dex.ArtManager;
import android.content.pm.parsing.PackageInfoWithoutStateUtils;
import android.content.pm.parsing.ParsingPackageRead;
import android.content.pm.parsing.ParsingPackageUtils;
import android.content.pm.parsing.result.ParseResult;
import android.content.pm.parsing.result.ParseTypeImpl;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.storage.VolumeInfo;
import android.util.AndroidException;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import dalvik.system.VMRuntime;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class PackageManager {
  @SystemApi
  public static final String ACTION_REQUEST_PERMISSIONS = "android.content.pm.action.REQUEST_PERMISSIONS";
  
  public static final boolean APPLY_DEFAULT_TO_DEVICE_PROTECTED_STORAGE = true;
  
  public static final String APP_DETAILS_ACTIVITY_CLASS_NAME = AppDetailsActivity.class.getName();
  
  public static final boolean APP_ENUMERATION_ENABLED_BY_DEFAULT = true;
  
  public static final int CERT_INPUT_RAW_X509 = 0;
  
  public static final int CERT_INPUT_SHA256 = 1;
  
  public static final int COMPONENT_ENABLED_STATE_DEFAULT = 0;
  
  public static final int COMPONENT_ENABLED_STATE_DISABLED = 2;
  
  public static final int COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED = 4;
  
  public static final int COMPONENT_ENABLED_STATE_DISABLED_USER = 3;
  
  public static final int COMPONENT_ENABLED_STATE_ENABLED = 1;
  
  public static final int DELETE_ALL_USERS = 2;
  
  public static final int DELETE_CHATTY = -2147483648;
  
  public static final int DELETE_DONT_KILL_APP = 8;
  
  public static final int DELETE_FAILED_ABORTED = -5;
  
  public static final int DELETE_FAILED_DEVICE_POLICY_MANAGER = -2;
  
  public static final int DELETE_FAILED_INTERNAL_ERROR = -1;
  
  public static final int DELETE_FAILED_OWNER_BLOCKED = -4;
  
  public static final int DELETE_FAILED_USED_SHARED_LIBRARY = -6;
  
  public static final int DELETE_FAILED_USER_RESTRICTED = -3;
  
  public static final int DELETE_KEEP_DATA = 1;
  
  public static final int DELETE_SUCCEEDED = 1;
  
  public static final int DELETE_SYSTEM_APP = 4;
  
  public static final int DONT_KILL_APP = 1;
  
  public static final String EXTRA_FAILURE_EXISTING_PACKAGE = "android.content.pm.extra.FAILURE_EXISTING_PACKAGE";
  
  public static final String EXTRA_FAILURE_EXISTING_PERMISSION = "android.content.pm.extra.FAILURE_EXISTING_PERMISSION";
  
  public static final String EXTRA_INTENT_FILTER_VERIFICATION_HOSTS = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_HOSTS";
  
  public static final String EXTRA_INTENT_FILTER_VERIFICATION_ID = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_ID";
  
  public static final String EXTRA_INTENT_FILTER_VERIFICATION_PACKAGE_NAME = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_PACKAGE_NAME";
  
  public static final String EXTRA_INTENT_FILTER_VERIFICATION_URI_SCHEME = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_URI_SCHEME";
  
  public static final String EXTRA_MOVE_ID = "android.content.pm.extra.MOVE_ID";
  
  @SystemApi
  public static final String EXTRA_REQUEST_PERMISSIONS_NAMES = "android.content.pm.extra.REQUEST_PERMISSIONS_NAMES";
  
  @SystemApi
  public static final String EXTRA_REQUEST_PERMISSIONS_RESULTS = "android.content.pm.extra.REQUEST_PERMISSIONS_RESULTS";
  
  public static final String EXTRA_VERIFICATION_ID = "android.content.pm.extra.VERIFICATION_ID";
  
  public static final String EXTRA_VERIFICATION_INSTALLER_PACKAGE = "android.content.pm.extra.VERIFICATION_INSTALLER_PACKAGE";
  
  public static final String EXTRA_VERIFICATION_INSTALLER_UID = "android.content.pm.extra.VERIFICATION_INSTALLER_UID";
  
  public static final String EXTRA_VERIFICATION_INSTALL_FLAGS = "android.content.pm.extra.VERIFICATION_INSTALL_FLAGS";
  
  public static final String EXTRA_VERIFICATION_LONG_VERSION_CODE = "android.content.pm.extra.VERIFICATION_LONG_VERSION_CODE";
  
  public static final String EXTRA_VERIFICATION_PACKAGE_NAME = "android.content.pm.extra.VERIFICATION_PACKAGE_NAME";
  
  public static final String EXTRA_VERIFICATION_RESULT = "android.content.pm.extra.VERIFICATION_RESULT";
  
  public static final String EXTRA_VERIFICATION_ROOT_HASH = "android.content.pm.extra.EXTRA_VERIFICATION_ROOT_HASH";
  
  public static final String EXTRA_VERIFICATION_URI = "android.content.pm.extra.VERIFICATION_URI";
  
  @Deprecated
  public static final String EXTRA_VERIFICATION_VERSION_CODE = "android.content.pm.extra.VERIFICATION_VERSION_CODE";
  
  public static final String FEATURE_ACTIVITIES_ON_SECONDARY_DISPLAYS = "android.software.activities_on_secondary_displays";
  
  public static final String FEATURE_ADOPTABLE_STORAGE = "android.software.adoptable_storage";
  
  public static final String FEATURE_APP_ENUMERATION = "android.software.app_enumeration";
  
  public static final String FEATURE_APP_WIDGETS = "android.software.app_widgets";
  
  public static final String FEATURE_ASSIST_GESTURE = "android.hardware.sensor.assist";
  
  public static final String FEATURE_AUDIO_LOW_LATENCY = "android.hardware.audio.low_latency";
  
  public static final String FEATURE_AUDIO_OUTPUT = "android.hardware.audio.output";
  
  public static final String FEATURE_AUDIO_PRO = "android.hardware.audio.pro";
  
  public static final String FEATURE_AUTOFILL = "android.software.autofill";
  
  public static final String FEATURE_AUTOMOTIVE = "android.hardware.type.automotive";
  
  public static final String FEATURE_BACKUP = "android.software.backup";
  
  public static final String FEATURE_BLUETOOTH = "android.hardware.bluetooth";
  
  public static final String FEATURE_BLUETOOTH_LE = "android.hardware.bluetooth_le";
  
  @SystemApi
  public static final String FEATURE_BROADCAST_RADIO = "android.hardware.broadcastradio";
  
  public static final String FEATURE_CAMERA = "android.hardware.camera";
  
  public static final String FEATURE_CAMERA_ANY = "android.hardware.camera.any";
  
  public static final String FEATURE_CAMERA_AR = "android.hardware.camera.ar";
  
  public static final String FEATURE_CAMERA_AUTOFOCUS = "android.hardware.camera.autofocus";
  
  public static final String FEATURE_CAMERA_CAPABILITY_MANUAL_POST_PROCESSING = "android.hardware.camera.capability.manual_post_processing";
  
  public static final String FEATURE_CAMERA_CAPABILITY_MANUAL_SENSOR = "android.hardware.camera.capability.manual_sensor";
  
  public static final String FEATURE_CAMERA_CAPABILITY_RAW = "android.hardware.camera.capability.raw";
  
  public static final String FEATURE_CAMERA_CONCURRENT = "android.hardware.camera.concurrent";
  
  public static final String FEATURE_CAMERA_EXTERNAL = "android.hardware.camera.external";
  
  public static final String FEATURE_CAMERA_FLASH = "android.hardware.camera.flash";
  
  public static final String FEATURE_CAMERA_FRONT = "android.hardware.camera.front";
  
  public static final String FEATURE_CAMERA_LEVEL_FULL = "android.hardware.camera.level.full";
  
  public static final String FEATURE_CANT_SAVE_STATE = "android.software.cant_save_state";
  
  public static final String FEATURE_COMPANION_DEVICE_SETUP = "android.software.companion_device_setup";
  
  public static final String FEATURE_CONNECTION_SERVICE = "android.software.connectionservice";
  
  public static final String FEATURE_CONSUMER_IR = "android.hardware.consumerir";
  
  @SystemApi
  public static final String FEATURE_CONTEXT_HUB = "android.hardware.context_hub";
  
  public static final String FEATURE_CONTROLS = "android.software.controls";
  
  public static final String FEATURE_CTS = "android.software.cts";
  
  public static final String FEATURE_DEVICE_ADMIN = "android.software.device_admin";
  
  public static final String FEATURE_DEVICE_ID_ATTESTATION = "android.software.device_id_attestation";
  
  public static final String FEATURE_DEVICE_UNIQUE_ATTESTATION = "android.hardware.device_unique_attestation";
  
  public static final String FEATURE_EMBEDDED = "android.hardware.type.embedded";
  
  public static final String FEATURE_ETHERNET = "android.hardware.ethernet";
  
  public static final String FEATURE_FACE = "android.hardware.biometrics.face";
  
  public static final String FEATURE_FAKETOUCH = "android.hardware.faketouch";
  
  public static final String FEATURE_FAKETOUCH_MULTITOUCH_DISTINCT = "android.hardware.faketouch.multitouch.distinct";
  
  public static final String FEATURE_FAKETOUCH_MULTITOUCH_JAZZHAND = "android.hardware.faketouch.multitouch.jazzhand";
  
  public static final String FEATURE_FILE_BASED_ENCRYPTION = "android.software.file_based_encryption";
  
  public static final String FEATURE_FINGERPRINT = "android.hardware.fingerprint";
  
  public static final String FEATURE_FREEFORM_WINDOW_MANAGEMENT = "android.software.freeform_window_management";
  
  public static final String FEATURE_GAMEPAD = "android.hardware.gamepad";
  
  public static final String FEATURE_HDMI_CEC = "android.hardware.hdmi.cec";
  
  public static final String FEATURE_HIFI_SENSORS = "android.hardware.sensor.hifi_sensors";
  
  public static final String FEATURE_HOME_SCREEN = "android.software.home_screen";
  
  @SystemApi
  public static final String FEATURE_INCREMENTAL_DELIVERY = "android.software.incremental_delivery";
  
  public static final String FEATURE_INPUT_METHODS = "android.software.input_methods";
  
  public static final String FEATURE_IPSEC_TUNNELS = "android.software.ipsec_tunnels";
  
  public static final String FEATURE_IRIS = "android.hardware.biometrics.iris";
  
  public static final String FEATURE_LEANBACK = "android.software.leanback";
  
  public static final String FEATURE_LEANBACK_ONLY = "android.software.leanback_only";
  
  public static final String FEATURE_LIVE_TV = "android.software.live_tv";
  
  public static final String FEATURE_LIVE_WALLPAPER = "android.software.live_wallpaper";
  
  public static final String FEATURE_LOCATION = "android.hardware.location";
  
  public static final String FEATURE_LOCATION_GPS = "android.hardware.location.gps";
  
  public static final String FEATURE_LOCATION_NETWORK = "android.hardware.location.network";
  
  public static final String FEATURE_LOWPAN = "android.hardware.lowpan";
  
  public static final String FEATURE_MANAGED_PROFILES = "android.software.managed_users";
  
  public static final String FEATURE_MANAGED_USERS = "android.software.managed_users";
  
  public static final String FEATURE_MICROPHONE = "android.hardware.microphone";
  
  public static final String FEATURE_MIDI = "android.software.midi";
  
  public static final String FEATURE_NFC = "android.hardware.nfc";
  
  public static final String FEATURE_NFC_ANY = "android.hardware.nfc.any";
  
  public static final String FEATURE_NFC_BEAM = "android.sofware.nfc.beam";
  
  @Deprecated
  public static final String FEATURE_NFC_HCE = "android.hardware.nfc.hce";
  
  public static final String FEATURE_NFC_HOST_CARD_EMULATION = "android.hardware.nfc.hce";
  
  public static final String FEATURE_NFC_HOST_CARD_EMULATION_NFCF = "android.hardware.nfc.hcef";
  
  public static final String FEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE = "android.hardware.nfc.ese";
  
  public static final String FEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC = "android.hardware.nfc.uicc";
  
  public static final String FEATURE_OPENGLES_EXTENSION_PACK = "android.hardware.opengles.aep";
  
  public static final String FEATURE_PC = "android.hardware.type.pc";
  
  public static final String FEATURE_PICTURE_IN_PICTURE = "android.software.picture_in_picture";
  
  public static final String FEATURE_PRINTING = "android.software.print";
  
  public static final String FEATURE_RAM_LOW = "android.hardware.ram.low";
  
  public static final String FEATURE_RAM_NORMAL = "android.hardware.ram.normal";
  
  @SystemApi
  public static final String FEATURE_REBOOT_ESCROW = "android.hardware.reboot_escrow";
  
  public static final String FEATURE_SCREEN_LANDSCAPE = "android.hardware.screen.landscape";
  
  public static final String FEATURE_SCREEN_PORTRAIT = "android.hardware.screen.portrait";
  
  public static final String FEATURE_SECURELY_REMOVES_USERS = "android.software.securely_removes_users";
  
  public static final String FEATURE_SECURE_LOCK_SCREEN = "android.software.secure_lock_screen";
  
  public static final String FEATURE_SENSOR_ACCELEROMETER = "android.hardware.sensor.accelerometer";
  
  public static final String FEATURE_SENSOR_AMBIENT_TEMPERATURE = "android.hardware.sensor.ambient_temperature";
  
  public static final String FEATURE_SENSOR_BAROMETER = "android.hardware.sensor.barometer";
  
  public static final String FEATURE_SENSOR_COMPASS = "android.hardware.sensor.compass";
  
  public static final String FEATURE_SENSOR_GYROSCOPE = "android.hardware.sensor.gyroscope";
  
  public static final String FEATURE_SENSOR_HEART_RATE = "android.hardware.sensor.heartrate";
  
  public static final String FEATURE_SENSOR_HEART_RATE_ECG = "android.hardware.sensor.heartrate.ecg";
  
  public static final String FEATURE_SENSOR_HINGE_ANGLE = "android.hardware.sensor.hinge_angle";
  
  public static final String FEATURE_SENSOR_LIGHT = "android.hardware.sensor.light";
  
  public static final String FEATURE_SENSOR_PROXIMITY = "android.hardware.sensor.proximity";
  
  public static final String FEATURE_SENSOR_RELATIVE_HUMIDITY = "android.hardware.sensor.relative_humidity";
  
  public static final String FEATURE_SENSOR_STEP_COUNTER = "android.hardware.sensor.stepcounter";
  
  public static final String FEATURE_SENSOR_STEP_DETECTOR = "android.hardware.sensor.stepdetector";
  
  public static final String FEATURE_SE_OMAPI_ESE = "android.hardware.se.omapi.ese";
  
  public static final String FEATURE_SE_OMAPI_SD = "android.hardware.se.omapi.sd";
  
  public static final String FEATURE_SE_OMAPI_UICC = "android.hardware.se.omapi.uicc";
  
  public static final String FEATURE_SIP = "android.software.sip";
  
  public static final String FEATURE_SIP_VOIP = "android.software.sip.voip";
  
  public static final String FEATURE_SLICES_DISABLED = "android.software.slices_disabled";
  
  public static final String FEATURE_STRONGBOX_KEYSTORE = "android.hardware.strongbox_keystore";
  
  public static final String FEATURE_TELEPHONY = "android.hardware.telephony";
  
  @SystemApi
  public static final String FEATURE_TELEPHONY_CARRIERLOCK = "android.hardware.telephony.carrierlock";
  
  public static final String FEATURE_TELEPHONY_CDMA = "android.hardware.telephony.cdma";
  
  public static final String FEATURE_TELEPHONY_EUICC = "android.hardware.telephony.euicc";
  
  public static final String FEATURE_TELEPHONY_GSM = "android.hardware.telephony.gsm";
  
  public static final String FEATURE_TELEPHONY_IMS = "android.hardware.telephony.ims";
  
  public static final String FEATURE_TELEPHONY_MBMS = "android.hardware.telephony.mbms";
  
  @Deprecated
  public static final String FEATURE_TELEVISION = "android.hardware.type.television";
  
  public static final String FEATURE_TOUCHSCREEN = "android.hardware.touchscreen";
  
  public static final String FEATURE_TOUCHSCREEN_MULTITOUCH = "android.hardware.touchscreen.multitouch";
  
  public static final String FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT = "android.hardware.touchscreen.multitouch.distinct";
  
  public static final String FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND = "android.hardware.touchscreen.multitouch.jazzhand";
  
  public static final String FEATURE_TUNER = "android.hardware.tv.tuner";
  
  public static final String FEATURE_USB_ACCESSORY = "android.hardware.usb.accessory";
  
  public static final String FEATURE_USB_HOST = "android.hardware.usb.host";
  
  public static final String FEATURE_VERIFIED_BOOT = "android.software.verified_boot";
  
  public static final String FEATURE_VOICE_RECOGNIZERS = "android.software.voice_recognizers";
  
  public static final String FEATURE_VR_HEADTRACKING = "android.hardware.vr.headtracking";
  
  @Deprecated
  public static final String FEATURE_VR_MODE = "android.software.vr.mode";
  
  public static final String FEATURE_VR_MODE_HIGH_PERFORMANCE = "android.hardware.vr.high_performance";
  
  public static final String FEATURE_VULKAN_DEQP_LEVEL = "android.software.vulkan.deqp.level";
  
  public static final String FEATURE_VULKAN_HARDWARE_COMPUTE = "android.hardware.vulkan.compute";
  
  public static final String FEATURE_VULKAN_HARDWARE_LEVEL = "android.hardware.vulkan.level";
  
  public static final String FEATURE_VULKAN_HARDWARE_VERSION = "android.hardware.vulkan.version";
  
  public static final String FEATURE_WATCH = "android.hardware.type.watch";
  
  public static final String FEATURE_WEBVIEW = "android.software.webview";
  
  public static final String FEATURE_WIFI = "android.hardware.wifi";
  
  public static final String FEATURE_WIFI_AWARE = "android.hardware.wifi.aware";
  
  public static final String FEATURE_WIFI_DIRECT = "android.hardware.wifi.direct";
  
  public static final String FEATURE_WIFI_PASSPOINT = "android.hardware.wifi.passpoint";
  
  public static final String FEATURE_WIFI_RTT = "android.hardware.wifi.rtt";
  
  public static final long FILTER_APPLICATION_QUERY = 135549675L;
  
  @SystemApi
  public static final int FLAGS_PERMISSION_RESERVED_PERMISSION_CONTROLLER = -268435456;
  
  public static final int FLAGS_PERMISSION_RESTRICTION_ANY_EXEMPT = 14336;
  
  @SystemApi
  public static final int FLAG_PERMISSION_APPLY_RESTRICTION = 16384;
  
  @SystemApi
  public static final int FLAG_PERMISSION_AUTO_REVOKED = 131072;
  
  @SystemApi
  public static final int FLAG_PERMISSION_GRANTED_BY_DEFAULT = 32;
  
  @SystemApi
  public static final int FLAG_PERMISSION_GRANTED_BY_ROLE = 32768;
  
  @SystemApi
  public static final int FLAG_PERMISSION_ONE_TIME = 65536;
  
  @SystemApi
  public static final int FLAG_PERMISSION_POLICY_FIXED = 4;
  
  @SystemApi
  public static final int FLAG_PERMISSION_RESTRICTION_INSTALLER_EXEMPT = 2048;
  
  @SystemApi
  public static final int FLAG_PERMISSION_RESTRICTION_SYSTEM_EXEMPT = 4096;
  
  @SystemApi
  public static final int FLAG_PERMISSION_RESTRICTION_UPGRADE_EXEMPT = 8192;
  
  @SystemApi
  public static final int FLAG_PERMISSION_REVIEW_REQUIRED = 64;
  
  @SystemApi
  public static final int FLAG_PERMISSION_REVOKED_COMPAT = 8;
  
  @SystemApi
  @Deprecated
  public static final int FLAG_PERMISSION_REVOKE_ON_UPGRADE = 8;
  
  public static final int FLAG_PERMISSION_REVOKE_WHEN_REQUESTED = 128;
  
  @SystemApi
  public static final int FLAG_PERMISSION_SYSTEM_FIXED = 16;
  
  @SystemApi
  public static final int FLAG_PERMISSION_USER_FIXED = 2;
  
  @SystemApi
  public static final int FLAG_PERMISSION_USER_SENSITIVE_WHEN_DENIED = 512;
  
  @SystemApi
  public static final int FLAG_PERMISSION_USER_SENSITIVE_WHEN_GRANTED = 256;
  
  @SystemApi
  public static final int FLAG_PERMISSION_USER_SET = 1;
  
  public static final int FLAG_PERMISSION_WHITELIST_INSTALLER = 2;
  
  public static final int FLAG_PERMISSION_WHITELIST_SYSTEM = 1;
  
  public static final int FLAG_PERMISSION_WHITELIST_UPGRADE = 4;
  
  public static final int GET_ACTIVITIES = 1;
  
  public static final int GET_CONFIGURATIONS = 16384;
  
  @Deprecated
  public static final int GET_DISABLED_COMPONENTS = 512;
  
  @Deprecated
  public static final int GET_DISABLED_UNTIL_USED_COMPONENTS = 32768;
  
  public static final int GET_GIDS = 256;
  
  public static final int GET_INSTRUMENTATION = 16;
  
  public static final int GET_INTENT_FILTERS = 32;
  
  public static final int GET_META_DATA = 128;
  
  public static final int GET_PERMISSIONS = 4096;
  
  public static final int GET_PROVIDERS = 8;
  
  public static final int GET_RECEIVERS = 2;
  
  public static final int GET_RESOLVED_FILTER = 64;
  
  public static final int GET_SERVICES = 4;
  
  public static final int GET_SHARED_LIBRARY_FILES = 1024;
  
  @Deprecated
  public static final int GET_SIGNATURES = 64;
  
  public static final int GET_SIGNING_CERTIFICATES = 134217728;
  
  @Deprecated
  public static final int GET_UNINSTALLED_PACKAGES = 8192;
  
  public static final int GET_URI_PERMISSION_PATTERNS = 2048;
  
  public static final int INSTALL_ALLOCATE_AGGRESSIVE = 32768;
  
  public static final int INSTALL_ALLOW_DOWNGRADE = 1048576;
  
  public static final int INSTALL_ALLOW_TEST = 4;
  
  public static final int INSTALL_ALL_USERS = 64;
  
  public static final int INSTALL_ALL_WHITELIST_RESTRICTED_PERMISSIONS = 4194304;
  
  public static final int INSTALL_APEX = 131072;
  
  public static final int INSTALL_DISABLE_VERIFICATION = 524288;
  
  public static final int INSTALL_DONT_KILL_APP = 4096;
  
  public static final int INSTALL_DRY_RUN = 8388608;
  
  public static final int INSTALL_ENABLE_ROLLBACK = 262144;
  
  public static final int INSTALL_FAILED_ABORTED = -115;
  
  @SystemApi
  public static final int INSTALL_FAILED_ALREADY_EXISTS = -1;
  
  public static final int INSTALL_FAILED_BAD_DEX_METADATA = -117;
  
  public static final int INSTALL_FAILED_BAD_SIGNATURE = -118;
  
  @SystemApi
  public static final int INSTALL_FAILED_CONFLICTING_PROVIDER = -13;
  
  @SystemApi
  public static final int INSTALL_FAILED_CONTAINER_ERROR = -18;
  
  @SystemApi
  public static final int INSTALL_FAILED_CPU_ABI_INCOMPATIBLE = -16;
  
  @SystemApi
  public static final int INSTALL_FAILED_DEXOPT = -11;
  
  @SystemApi
  public static final int INSTALL_FAILED_DUPLICATE_PACKAGE = -5;
  
  public static final int INSTALL_FAILED_DUPLICATE_PERMISSION = -112;
  
  public static final int INSTALL_FAILED_INSTANT_APP_INVALID = -116;
  
  @SystemApi
  public static final int INSTALL_FAILED_INSUFFICIENT_STORAGE = -4;
  
  @SystemApi
  public static final int INSTALL_FAILED_INTERNAL_ERROR = -110;
  
  @SystemApi
  public static final int INSTALL_FAILED_INVALID_APK = -2;
  
  @SystemApi
  public static final int INSTALL_FAILED_INVALID_INSTALL_LOCATION = -19;
  
  @SystemApi
  public static final int INSTALL_FAILED_INVALID_URI = -3;
  
  @SystemApi
  public static final int INSTALL_FAILED_MEDIA_UNAVAILABLE = -20;
  
  @SystemApi
  public static final int INSTALL_FAILED_MISSING_FEATURE = -17;
  
  @SystemApi
  public static final int INSTALL_FAILED_MISSING_SHARED_LIBRARY = -9;
  
  public static final int INSTALL_FAILED_MISSING_SPLIT = -28;
  
  public static final int INSTALL_FAILED_MULTIPACKAGE_INCONSISTENCY = -120;
  
  @SystemApi
  public static final int INSTALL_FAILED_NEWER_SDK = -14;
  
  public static final int INSTALL_FAILED_NO_MATCHING_ABIS = -113;
  
  @SystemApi
  public static final int INSTALL_FAILED_NO_SHARED_USER = -6;
  
  @SystemApi
  public static final int INSTALL_FAILED_OLDER_SDK = -12;
  
  public static final int INSTALL_FAILED_OTHER_STAGED_SESSION_IN_PROGRESS = -119;
  
  @SystemApi
  public static final int INSTALL_FAILED_PACKAGE_CHANGED = -23;
  
  @SystemApi
  public static final int INSTALL_FAILED_PERMISSION_MODEL_DOWNGRADE = -26;
  
  public static final int INSTALL_FAILED_PROCESS_NOT_DEFINED = -122;
  
  @SystemApi
  public static final int INSTALL_FAILED_REPLACE_COULDNT_DELETE = -10;
  
  @SystemApi
  public static final int INSTALL_FAILED_SANDBOX_VERSION_DOWNGRADE = -27;
  
  @SystemApi
  public static final int INSTALL_FAILED_SHARED_USER_INCOMPATIBLE = -8;
  
  @SystemApi
  public static final int INSTALL_FAILED_TEST_ONLY = -15;
  
  public static final int INSTALL_FAILED_UID_CHANGED = -24;
  
  @SystemApi
  public static final int INSTALL_FAILED_UPDATE_INCOMPATIBLE = -7;
  
  public static final int INSTALL_FAILED_USER_RESTRICTED = -111;
  
  @SystemApi
  public static final int INSTALL_FAILED_VERIFICATION_FAILURE = -22;
  
  @SystemApi
  public static final int INSTALL_FAILED_VERIFICATION_TIMEOUT = -21;
  
  public static final int INSTALL_FAILED_VERSION_DOWNGRADE = -25;
  
  public static final int INSTALL_FAILED_WRONG_INSTALLED_VERSION = -121;
  
  public static final int INSTALL_FORCE_PERMISSION_PROMPT = 1024;
  
  public static final int INSTALL_FORCE_VOLUME_UUID = 512;
  
  public static final int INSTALL_FROM_ADB = 32;
  
  public static final int INSTALL_FULL_APP = 16384;
  
  public static final int INSTALL_GRANT_RUNTIME_PERMISSIONS = 256;
  
  public static final int INSTALL_INSTANT_APP = 2048;
  
  public static final int INSTALL_INTERNAL = 16;
  
  @SystemApi
  public static final int INSTALL_PARSE_FAILED_BAD_MANIFEST = -101;
  
  @SystemApi
  public static final int INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME = -106;
  
  @SystemApi
  public static final int INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID = -107;
  
  @SystemApi
  public static final int INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING = -105;
  
  @SystemApi
  public static final int INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES = -104;
  
  @SystemApi
  public static final int INSTALL_PARSE_FAILED_MANIFEST_EMPTY = -109;
  
  @SystemApi
  public static final int INSTALL_PARSE_FAILED_MANIFEST_MALFORMED = -108;
  
  @SystemApi
  public static final int INSTALL_PARSE_FAILED_NOT_APK = -100;
  
  @SystemApi
  public static final int INSTALL_PARSE_FAILED_NO_CERTIFICATES = -103;
  
  public static final int INSTALL_PARSE_FAILED_ONLY_COREAPP_ALLOWED = -123;
  
  public static final int INSTALL_PARSE_FAILED_RESOURCES_ARSC_COMPRESSED = -124;
  
  public static final int INSTALL_PARSE_FAILED_SKIPPED = -125;
  
  @SystemApi
  public static final int INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION = -102;
  
  public static final int INSTALL_REASON_DEVICE_RESTORE = 2;
  
  public static final int INSTALL_REASON_DEVICE_SETUP = 3;
  
  public static final int INSTALL_REASON_POLICY = 1;
  
  public static final int INSTALL_REASON_ROLLBACK = 5;
  
  public static final int INSTALL_REASON_UNKNOWN = 0;
  
  public static final int INSTALL_REASON_USER = 4;
  
  public static final int INSTALL_REPLACE_EXISTING = 2;
  
  public static final int INSTALL_REQUEST_DOWNGRADE = 128;
  
  public static final int INSTALL_STAGED = 2097152;
  
  @SystemApi
  public static final int INSTALL_SUCCEEDED = 1;
  
  public static final int INSTALL_UNKNOWN = 0;
  
  public static final int INSTALL_VIRTUAL_PRELOAD = 65536;
  
  @SystemApi
  public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_ALWAYS = 2;
  
  @SystemApi
  public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_ALWAYS_ASK = 4;
  
  @SystemApi
  public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_ASK = 1;
  
  @SystemApi
  public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_NEVER = 3;
  
  @SystemApi
  public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_UNDEFINED = 0;
  
  @SystemApi
  public static final int INTENT_FILTER_VERIFICATION_FAILURE = -1;
  
  @SystemApi
  public static final int INTENT_FILTER_VERIFICATION_SUCCESS = 1;
  
  @SystemApi
  @Deprecated
  public static final int MASK_PERMISSION_FLAGS = 255;
  
  public static final int MASK_PERMISSION_FLAGS_ALL = 261119;
  
  public static final int MATCH_ALL = 131072;
  
  @SystemApi
  public static final int MATCH_ANY_USER = 4194304;
  
  public static final int MATCH_APEX = 1073741824;
  
  @Deprecated
  public static final int MATCH_DEBUG_TRIAGED_MISSING = 268435456;
  
  public static final int MATCH_DEFAULT_ONLY = 65536;
  
  public static final int MATCH_DIRECT_BOOT_AUTO = 268435456;
  
  public static final int MATCH_DIRECT_BOOT_AWARE = 524288;
  
  public static final int MATCH_DIRECT_BOOT_UNAWARE = 262144;
  
  public static final int MATCH_DISABLED_COMPONENTS = 512;
  
  public static final int MATCH_DISABLED_UNTIL_USED_COMPONENTS = 32768;
  
  public static final int MATCH_EXPLICITLY_VISIBLE_ONLY = 33554432;
  
  @SystemApi
  public static final int MATCH_FACTORY_ONLY = 2097152;
  
  public static final int MATCH_HIDDEN_UNTIL_INSTALLED_COMPONENTS = 536870912;
  
  @SystemApi
  public static final int MATCH_INSTANT = 8388608;
  
  public static final int MATCH_KNOWN_PACKAGES = 4202496;
  
  public static final int MATCH_STATIC_SHARED_LIBRARIES = 67108864;
  
  public static final int MATCH_SYSTEM_ONLY = 1048576;
  
  public static final int MATCH_UNINSTALLED_PACKAGES = 8192;
  
  public static final int MATCH_VISIBLE_TO_INSTANT_APP_ONLY = 16777216;
  
  public static final long MAXIMUM_VERIFICATION_TIMEOUT = 3600000L;
  
  @SystemApi
  public static final int MODULE_APEX_NAME = 1;
  
  @Deprecated
  public static final int MOVE_EXTERNAL_MEDIA = 2;
  
  public static final int MOVE_FAILED_3RD_PARTY_NOT_ALLOWED_ON_INTERNAL = -9;
  
  public static final int MOVE_FAILED_DEVICE_ADMIN = -8;
  
  public static final int MOVE_FAILED_DOESNT_EXIST = -2;
  
  public static final int MOVE_FAILED_INSUFFICIENT_STORAGE = -1;
  
  public static final int MOVE_FAILED_INTERNAL_ERROR = -6;
  
  public static final int MOVE_FAILED_INVALID_LOCATION = -5;
  
  public static final int MOVE_FAILED_LOCKED_USER = -10;
  
  public static final int MOVE_FAILED_OPERATION_PENDING = -7;
  
  public static final int MOVE_FAILED_SYSTEM_PACKAGE = -3;
  
  @Deprecated
  public static final int MOVE_INTERNAL = 1;
  
  public static final int MOVE_SUCCEEDED = -100;
  
  public static final int NOTIFY_PACKAGE_USE_ACTIVITY = 0;
  
  public static final int NOTIFY_PACKAGE_USE_BACKUP = 5;
  
  public static final int NOTIFY_PACKAGE_USE_BROADCAST_RECEIVER = 3;
  
  public static final int NOTIFY_PACKAGE_USE_CONTENT_PROVIDER = 4;
  
  public static final int NOTIFY_PACKAGE_USE_CROSS_PACKAGE = 6;
  
  public static final int NOTIFY_PACKAGE_USE_FOREGROUND_SERVICE = 2;
  
  public static final int NOTIFY_PACKAGE_USE_INSTRUMENTATION = 7;
  
  public static final int NOTIFY_PACKAGE_USE_REASONS_COUNT = 8;
  
  public static final int NOTIFY_PACKAGE_USE_SERVICE = 1;
  
  public static final int NO_NATIVE_LIBRARIES = -114;
  
  public static final int ONLY_IF_NO_MATCH_FOUND = 4;
  
  public static final int PERMISSION_DENIED = -1;
  
  public static final int PERMISSION_GRANTED = 0;
  
  @SystemApi
  public static final int RESTRICTION_HIDE_FROM_SUGGESTIONS = 1;
  
  @SystemApi
  public static final int RESTRICTION_HIDE_NOTIFICATIONS = 2;
  
  @SystemApi
  public static final int RESTRICTION_NONE = 0;
  
  public static final int SIGNATURE_FIRST_NOT_SIGNED = -1;
  
  public static final int SIGNATURE_MATCH = 0;
  
  public static final int SIGNATURE_NEITHER_SIGNED = 1;
  
  public static final int SIGNATURE_NO_MATCH = -3;
  
  public static final int SIGNATURE_SECOND_NOT_SIGNED = -2;
  
  public static final int SIGNATURE_UNKNOWN_PACKAGE = -4;
  
  public static final int SKIP_CURRENT_PROFILE = 2;
  
  public static final int SYNCHRONOUS = 2;
  
  public static final int SYSTEM_APP_STATE_HIDDEN_UNTIL_INSTALLED_HIDDEN = 0;
  
  public static final int SYSTEM_APP_STATE_HIDDEN_UNTIL_INSTALLED_VISIBLE = 1;
  
  public static final int SYSTEM_APP_STATE_INSTALLED = 2;
  
  public static final int SYSTEM_APP_STATE_UNINSTALLED = 3;
  
  public static final String SYSTEM_SHARED_LIBRARY_SERVICES = "android.ext.services";
  
  public static final String SYSTEM_SHARED_LIBRARY_SHARED = "android.ext.shared";
  
  private static final String TAG = "PackageManager";
  
  public static final int UNINSTALL_REASON_UNKNOWN = 0;
  
  public static final int UNINSTALL_REASON_USER_TYPE = 1;
  
  public static final int VERIFICATION_ALLOW = 1;
  
  public static final int VERIFICATION_ALLOW_WITHOUT_SUFFICIENT = 2;
  
  public static final int VERIFICATION_REJECT = -1;
  
  public static final int VERSION_CODE_HIGHEST = -1;
  
  private static final PropertyInvalidatedCache<ApplicationInfoQuery, ApplicationInfo> sApplicationInfoCache = new PropertyInvalidatedCache<ApplicationInfoQuery, ApplicationInfo>(16, "cache_key.package_info") {
      protected ApplicationInfo maybeCheckConsistency(PackageManager.ApplicationInfoQuery param1ApplicationInfoQuery, ApplicationInfo param1ApplicationInfo) {
        return param1ApplicationInfo;
      }
      
      protected ApplicationInfo recompute(PackageManager.ApplicationInfoQuery param1ApplicationInfoQuery) {
        return PackageManager.getApplicationInfoAsUserUncached(param1ApplicationInfoQuery.packageName, param1ApplicationInfoQuery.flags, param1ApplicationInfoQuery.userId);
      }
    };
  
  private static final PropertyInvalidatedCache.AutoCorker sCacheAutoCorker = new PropertyInvalidatedCache.AutoCorker("cache_key.package_info");
  
  private static final PropertyInvalidatedCache<PackageInfoQuery, PackageInfo> sPackageInfoCache = new PropertyInvalidatedCache<PackageInfoQuery, PackageInfo>(32, "cache_key.package_info") {
      protected PackageInfo maybeCheckConsistency(PackageManager.PackageInfoQuery param1PackageInfoQuery, PackageInfo param1PackageInfo) {
        return param1PackageInfo;
      }
      
      protected PackageInfo recompute(PackageManager.PackageInfoQuery param1PackageInfoQuery) {
        return PackageManager.getPackageInfoAsUserUncached(param1PackageInfoQuery.packageName, param1PackageInfoQuery.flags, param1PackageInfoQuery.userId);
      }
    };
  
  public static void corkPackageInfoCache() {
    PropertyInvalidatedCache.corkInvalidations("cache_key.package_info");
  }
  
  public static int deleteStatusToPublicStatus(int paramInt) {
    return (paramInt != -6) ? ((paramInt != -5) ? ((paramInt != -4) ? ((paramInt != -3) ? ((paramInt != -2) ? ((paramInt != 1) ? 1 : 0) : 2) : 2) : 2) : 3) : 5;
  }
  
  public static String deleteStatusToString(int paramInt) {
    switch (paramInt) {
      default:
        return Integer.toString(paramInt);
      case 1:
        return "DELETE_SUCCEEDED";
      case -1:
        return "DELETE_FAILED_INTERNAL_ERROR";
      case -2:
        return "DELETE_FAILED_DEVICE_POLICY_MANAGER";
      case -3:
        return "DELETE_FAILED_USER_RESTRICTED";
      case -4:
        return "DELETE_FAILED_OWNER_BLOCKED";
      case -5:
        return "DELETE_FAILED_ABORTED";
      case -6:
        break;
    } 
    return "DELETE_FAILED_USED_SHARED_LIBRARY";
  }
  
  public static String deleteStatusToString(int paramInt, String paramString) {
    String str = deleteStatusToString(paramInt);
    if (paramString != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(": ");
      stringBuilder.append(paramString);
      return stringBuilder.toString();
    } 
    return str;
  }
  
  public static void disableApplicationInfoCache() {
    sApplicationInfoCache.disableLocal();
  }
  
  public static void disablePackageInfoCache() {
    sPackageInfoCache.disableLocal();
  }
  
  public static ApplicationInfo getApplicationInfoAsUserCached(String paramString, int paramInt1, int paramInt2) {
    return (ApplicationInfo)sApplicationInfoCache.query(new ApplicationInfoQuery(paramString, paramInt1, paramInt2));
  }
  
  private static ApplicationInfo getApplicationInfoAsUserUncached(String paramString, int paramInt1, int paramInt2) {
    try {
      return ActivityThread.getPackageManager().getApplicationInfo(paramString, paramInt1, paramInt2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static PackageInfo getPackageInfoAsUserCached(String paramString, int paramInt1, int paramInt2) {
    return (PackageInfo)sPackageInfoCache.query(new PackageInfoQuery(paramString, paramInt1, paramInt2));
  }
  
  private static PackageInfo getPackageInfoAsUserUncached(String paramString, int paramInt1, int paramInt2) {
    try {
      return ActivityThread.getPackageManager().getPackageInfo(paramString, paramInt1, paramInt2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static int installStatusToPublicStatus(int paramInt) {
    if (paramInt != -118) {
      if (paramInt != -117) {
        if (paramInt != -115) {
          if (paramInt != -28) {
            if (paramInt != 1) {
              switch (paramInt) {
                default:
                  switch (paramInt) {
                    default:
                      switch (paramInt) {
                        default:
                          return 1;
                        case -1:
                          return 5;
                        case -2:
                          return 4;
                        case -3:
                          return 4;
                        case -4:
                          return 6;
                        case -5:
                          return 5;
                        case -6:
                          return 5;
                        case -7:
                          return 5;
                        case -8:
                          return 5;
                        case -9:
                          return 7;
                        case -10:
                          return 5;
                        case -11:
                          return 4;
                        case -12:
                          return 7;
                        case -13:
                          return 5;
                        case -14:
                          return 7;
                        case -15:
                          return 4;
                        case -16:
                          return 7;
                        case -17:
                          return 7;
                        case -18:
                          return 6;
                        case -19:
                          return 6;
                        case -20:
                          return 6;
                        case -21:
                          return 3;
                        case -22:
                          return 3;
                        case -23:
                          return 4;
                        case -24:
                          return 4;
                        case -25:
                          return 4;
                        case -26:
                          break;
                      } 
                      return 4;
                    case -100:
                      return 4;
                    case -101:
                      return 4;
                    case -102:
                      return 4;
                    case -103:
                      return 4;
                    case -104:
                      return 4;
                    case -105:
                      return 4;
                    case -106:
                      return 4;
                    case -107:
                      return 4;
                    case -108:
                      return 4;
                    case -109:
                      break;
                  } 
                  return 4;
                case -111:
                  return 7;
                case -112:
                  return 5;
                case -113:
                  break;
              } 
              return 7;
            } 
            return 0;
          } 
          return 7;
        } 
        return 3;
      } 
      return 4;
    } 
    return 4;
  }
  
  public static String installStatusToString(int paramInt) {
    if (paramInt != -122) {
      if (paramInt != -121) {
        if (paramInt != -118) {
          if (paramInt != -117) {
            if (paramInt != -115) {
              if (paramInt != -28) {
                if (paramInt != 1) {
                  switch (paramInt) {
                    default:
                      switch (paramInt) {
                        default:
                          return Integer.toString(paramInt);
                        case -1:
                          return "INSTALL_FAILED_ALREADY_EXISTS";
                        case -2:
                          return "INSTALL_FAILED_INVALID_APK";
                        case -3:
                          return "INSTALL_FAILED_INVALID_URI";
                        case -4:
                          return "INSTALL_FAILED_INSUFFICIENT_STORAGE";
                        case -5:
                          return "INSTALL_FAILED_DUPLICATE_PACKAGE";
                        case -6:
                          return "INSTALL_FAILED_NO_SHARED_USER";
                        case -7:
                          return "INSTALL_FAILED_UPDATE_INCOMPATIBLE";
                        case -8:
                          return "INSTALL_FAILED_SHARED_USER_INCOMPATIBLE";
                        case -9:
                          return "INSTALL_FAILED_MISSING_SHARED_LIBRARY";
                        case -10:
                          return "INSTALL_FAILED_REPLACE_COULDNT_DELETE";
                        case -11:
                          return "INSTALL_FAILED_DEXOPT";
                        case -12:
                          return "INSTALL_FAILED_OLDER_SDK";
                        case -13:
                          return "INSTALL_FAILED_CONFLICTING_PROVIDER";
                        case -14:
                          return "INSTALL_FAILED_NEWER_SDK";
                        case -15:
                          return "INSTALL_FAILED_TEST_ONLY";
                        case -16:
                          return "INSTALL_FAILED_CPU_ABI_INCOMPATIBLE";
                        case -17:
                          return "INSTALL_FAILED_MISSING_FEATURE";
                        case -18:
                          return "INSTALL_FAILED_CONTAINER_ERROR";
                        case -19:
                          return "INSTALL_FAILED_INVALID_INSTALL_LOCATION";
                        case -20:
                          return "INSTALL_FAILED_MEDIA_UNAVAILABLE";
                        case -21:
                          return "INSTALL_FAILED_VERIFICATION_TIMEOUT";
                        case -22:
                          return "INSTALL_FAILED_VERIFICATION_FAILURE";
                        case -23:
                          return "INSTALL_FAILED_PACKAGE_CHANGED";
                        case -24:
                          return "INSTALL_FAILED_UID_CHANGED";
                        case -25:
                          break;
                      } 
                      return "INSTALL_FAILED_VERSION_DOWNGRADE";
                    case -100:
                      return "INSTALL_PARSE_FAILED_NOT_APK";
                    case -101:
                      return "INSTALL_PARSE_FAILED_BAD_MANIFEST";
                    case -102:
                      return "INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION";
                    case -103:
                      return "INSTALL_PARSE_FAILED_NO_CERTIFICATES";
                    case -104:
                      return "INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES";
                    case -105:
                      return "INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING";
                    case -106:
                      return "INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME";
                    case -107:
                      return "INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID";
                    case -108:
                      return "INSTALL_PARSE_FAILED_MANIFEST_MALFORMED";
                    case -109:
                      return "INSTALL_PARSE_FAILED_MANIFEST_EMPTY";
                    case -110:
                      return "INSTALL_FAILED_INTERNAL_ERROR";
                    case -111:
                      return "INSTALL_FAILED_USER_RESTRICTED";
                    case -112:
                      return "INSTALL_FAILED_DUPLICATE_PERMISSION";
                    case -113:
                      break;
                  } 
                  return "INSTALL_FAILED_NO_MATCHING_ABIS";
                } 
                return "INSTALL_SUCCEEDED";
              } 
              return "INSTALL_FAILED_MISSING_SPLIT";
            } 
            return "INSTALL_FAILED_ABORTED";
          } 
          return "INSTALL_FAILED_BAD_DEX_METADATA";
        } 
        return "INSTALL_FAILED_BAD_SIGNATURE";
      } 
      return "INSTALL_FAILED_WRONG_INSTALLED_VERSION";
    } 
    return "INSTALL_FAILED_PROCESS_NOT_DEFINED";
  }
  
  public static String installStatusToString(int paramInt, String paramString) {
    String str = installStatusToString(paramInt);
    if (paramString != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(": ");
      stringBuilder.append(paramString);
      return stringBuilder.toString();
    } 
    return str;
  }
  
  public static void invalidatePackageInfoCache() {
    sCacheAutoCorker.autoCork();
  }
  
  public static boolean isMoveStatusFinished(int paramInt) {
    return (paramInt < 0 || paramInt > 100);
  }
  
  public static String permissionFlagToString(int paramInt) {
    if (paramInt != 1) {
      if (paramInt != 2) {
        switch (paramInt) {
          default:
            return Integer.toString(paramInt);
          case 131072:
            return "AUTO_REVOKED";
          case 65536:
            return "ONE_TIME";
          case 32768:
            return "GRANTED_BY_ROLE";
          case 16384:
            return "APPLY_RESTRICTION";
          case 8192:
            return "RESTRICTION_UPGRADE_EXEMPT";
          case 4096:
            return "RESTRICTION_SYSTEM_EXEMPT";
          case 2048:
            return "RESTRICTION_INSTALLER_EXEMPT";
          case 512:
            return "USER_SENSITIVE_WHEN_DENIED";
          case 256:
            return "USER_SENSITIVE_WHEN_GRANTED";
          case 128:
            return "REVOKE_WHEN_REQUESTED";
          case 64:
            return "REVIEW_REQUIRED";
          case 32:
            return "GRANTED_BY_DEFAULT";
          case 16:
            return "SYSTEM_FIXED";
          case 8:
            return "REVOKED_COMPAT";
          case 4:
            break;
        } 
        return "POLICY_FIXED";
      } 
      return "USER_FIXED";
    } 
    return "USER_SET";
  }
  
  public static void uncorkPackageInfoCache() {
    PropertyInvalidatedCache.uncorkInvalidations("cache_key.package_info");
  }
  
  public abstract void addCrossProfileIntentFilter(IntentFilter paramIntentFilter, int paramInt1, int paramInt2, int paramInt3);
  
  @SystemApi
  public abstract void addOnPermissionsChangeListener(OnPermissionsChangedListener paramOnPermissionsChangedListener);
  
  @Deprecated
  public abstract void addPackageToPreferred(String paramString);
  
  public abstract boolean addPermission(PermissionInfo paramPermissionInfo);
  
  public abstract boolean addPermissionAsync(PermissionInfo paramPermissionInfo);
  
  @Deprecated
  public abstract void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName);
  
  @Deprecated
  public void addPreferredActivityAsUser(IntentFilter paramIntentFilter, int paramInt1, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName, int paramInt2) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public boolean addWhitelistedRestrictedPermission(String paramString1, String paramString2, int paramInt) {
    return false;
  }
  
  @SystemApi
  public abstract boolean arePermissionsIndividuallyControlled();
  
  public Intent buildRequestPermissionsIntent(String[] paramArrayOfString) {
    if (!ArrayUtils.isEmpty((Object[])paramArrayOfString)) {
      Intent intent = new Intent("android.content.pm.action.REQUEST_PERMISSIONS");
      intent.putExtra("android.content.pm.extra.REQUEST_PERMISSIONS_NAMES", paramArrayOfString);
      intent.setPackage(getPermissionControllerPackageName());
      return intent;
    } 
    throw new IllegalArgumentException("permission cannot be null or empty");
  }
  
  public abstract boolean canRequestPackageInstalls();
  
  public abstract String[] canonicalToCurrentPackageNames(String[] paramArrayOfString);
  
  public abstract int checkPermission(String paramString1, String paramString2);
  
  public abstract int checkSignatures(int paramInt1, int paramInt2);
  
  public abstract int checkSignatures(String paramString1, String paramString2);
  
  public abstract void clearApplicationUserData(String paramString, IPackageDataObserver paramIPackageDataObserver);
  
  public abstract void clearCrossProfileIntentFilters(int paramInt);
  
  public abstract void clearInstantAppCookie();
  
  @Deprecated
  public abstract void clearPackagePreferredActivities(String paramString);
  
  public abstract String[] currentToCanonicalPackageNames(String[] paramArrayOfString);
  
  public abstract void deleteApplicationCacheFiles(String paramString, IPackageDataObserver paramIPackageDataObserver);
  
  public abstract void deleteApplicationCacheFilesAsUser(String paramString, int paramInt, IPackageDataObserver paramIPackageDataObserver);
  
  public abstract void deletePackage(String paramString, IPackageDeleteObserver paramIPackageDeleteObserver, int paramInt);
  
  public abstract void deletePackageAsUser(String paramString, IPackageDeleteObserver paramIPackageDeleteObserver, int paramInt1, int paramInt2);
  
  public abstract void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong);
  
  public abstract void flushPackageRestrictionsAsUser(int paramInt);
  
  public void freeStorage(long paramLong, IntentSender paramIntentSender) {
    freeStorage(null, paramLong, paramIntentSender);
  }
  
  public abstract void freeStorage(String paramString, long paramLong, IntentSender paramIntentSender);
  
  public void freeStorageAndNotify(long paramLong, IPackageDataObserver paramIPackageDataObserver) {
    freeStorageAndNotify(null, paramLong, paramIPackageDataObserver);
  }
  
  public abstract void freeStorageAndNotify(String paramString, long paramLong, IPackageDataObserver paramIPackageDataObserver);
  
  public abstract Drawable getActivityBanner(ComponentName paramComponentName) throws NameNotFoundException;
  
  public abstract Drawable getActivityBanner(Intent paramIntent) throws NameNotFoundException;
  
  public abstract Drawable getActivityIcon(ComponentName paramComponentName) throws NameNotFoundException;
  
  public abstract Drawable getActivityIcon(Intent paramIntent) throws NameNotFoundException;
  
  public abstract ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt) throws NameNotFoundException;
  
  public abstract Drawable getActivityLogo(ComponentName paramComponentName) throws NameNotFoundException;
  
  public abstract Drawable getActivityLogo(Intent paramIntent) throws NameNotFoundException;
  
  @SystemApi
  public abstract List<IntentFilter> getAllIntentFilters(String paramString);
  
  public abstract List<PermissionGroupInfo> getAllPermissionGroups(int paramInt);
  
  public String getAppPredictionServicePackageName() {
    throw new UnsupportedOperationException("getAppPredictionServicePackageName not implemented in subclass");
  }
  
  public abstract Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo);
  
  public abstract Drawable getApplicationBanner(String paramString) throws NameNotFoundException;
  
  public abstract int getApplicationEnabledSetting(String paramString);
  
  public abstract boolean getApplicationHiddenSettingAsUser(String paramString, UserHandle paramUserHandle);
  
  public abstract Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo);
  
  public abstract Drawable getApplicationIcon(String paramString) throws NameNotFoundException;
  
  public abstract ApplicationInfo getApplicationInfo(String paramString, int paramInt) throws NameNotFoundException;
  
  public abstract ApplicationInfo getApplicationInfoAsUser(String paramString, int paramInt1, int paramInt2) throws NameNotFoundException;
  
  @SystemApi
  public ApplicationInfo getApplicationInfoAsUser(String paramString, int paramInt, UserHandle paramUserHandle) throws NameNotFoundException {
    return getApplicationInfoAsUser(paramString, paramInt, paramUserHandle.getIdentifier());
  }
  
  public abstract CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo);
  
  public abstract Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo);
  
  public abstract Drawable getApplicationLogo(String paramString) throws NameNotFoundException;
  
  @SystemApi
  public ArtManager getArtManager() {
    throw new UnsupportedOperationException("getArtManager not implemented in subclass");
  }
  
  public String getAttentionServicePackageName() {
    throw new UnsupportedOperationException("getAttentionServicePackageName not implemented in subclass");
  }
  
  public CharSequence getBackgroundPermissionOptionLabel() {
    return "";
  }
  
  public abstract Intent getCarLaunchIntentForPackage(String paramString);
  
  public abstract ChangedPackages getChangedPackages(int paramInt);
  
  public abstract int getComponentEnabledSetting(ComponentName paramComponentName);
  
  public String getContentCaptureServicePackageName() {
    throw new UnsupportedOperationException("getContentCaptureServicePackageName not implemented in subclass");
  }
  
  @SystemApi
  public List<SharedLibraryInfo> getDeclaredSharedLibraries(String paramString, int paramInt) {
    throw new UnsupportedOperationException("getDeclaredSharedLibraries() not implemented in subclass");
  }
  
  public abstract Drawable getDefaultActivityIcon();
  
  @SystemApi
  public abstract String getDefaultBrowserPackageNameAsUser(int paramInt);
  
  public String getDefaultTextClassifierPackageName() {
    throw new UnsupportedOperationException("getDefaultTextClassifierPackageName not implemented in subclass");
  }
  
  public abstract Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo);
  
  @SystemApi
  public CharSequence getHarmfulAppWarning(String paramString) {
    throw new UnsupportedOperationException("getHarmfulAppWarning not implemented in subclass");
  }
  
  public abstract ComponentName getHomeActivities(List<ResolveInfo> paramList);
  
  @SystemApi
  public String getIncidentReportApproverPackageName() {
    throw new UnsupportedOperationException("getIncidentReportApproverPackageName not implemented in subclass");
  }
  
  public abstract int getInstallReason(String paramString, UserHandle paramUserHandle);
  
  public InstallSourceInfo getInstallSourceInfo(String paramString) throws NameNotFoundException {
    throw new UnsupportedOperationException("getInstallSourceInfo not implemented");
  }
  
  public abstract List<ApplicationInfo> getInstalledApplications(int paramInt);
  
  public abstract List<ApplicationInfo> getInstalledApplicationsAsUser(int paramInt1, int paramInt2);
  
  public List<ModuleInfo> getInstalledModules(int paramInt) {
    throw new UnsupportedOperationException("getInstalledModules not implemented in subclass");
  }
  
  public abstract List<PackageInfo> getInstalledPackages(int paramInt);
  
  @SystemApi
  public abstract List<PackageInfo> getInstalledPackagesAsUser(int paramInt1, int paramInt2);
  
  @Deprecated
  public abstract String getInstallerPackageName(String paramString);
  
  public abstract String getInstantAppAndroidId(String paramString, UserHandle paramUserHandle);
  
  public abstract byte[] getInstantAppCookie();
  
  public abstract int getInstantAppCookieMaxBytes();
  
  public abstract int getInstantAppCookieMaxSize();
  
  @SystemApi
  public abstract Drawable getInstantAppIcon(String paramString);
  
  @SystemApi
  public abstract ComponentName getInstantAppInstallerComponent();
  
  @SystemApi
  public abstract ComponentName getInstantAppResolverSettingsComponent();
  
  @SystemApi
  public abstract List<InstantAppInfo> getInstantApps();
  
  public abstract InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt) throws NameNotFoundException;
  
  @SystemApi
  public abstract List<IntentFilterVerificationInfo> getIntentFilterVerifications(String paramString);
  
  @SystemApi
  public abstract int getIntentVerificationStatusAsUser(String paramString, int paramInt);
  
  public abstract KeySet getKeySetByAlias(String paramString1, String paramString2);
  
  public abstract Intent getLaunchIntentForPackage(String paramString);
  
  public abstract Intent getLeanbackLaunchIntentForPackage(String paramString);
  
  public Set<String> getMimeGroup(String paramString) {
    throw new UnsupportedOperationException("getMimeGroup not implemented in subclass");
  }
  
  public ModuleInfo getModuleInfo(String paramString, int paramInt) throws NameNotFoundException {
    throw new UnsupportedOperationException("getModuleInfo not implemented in subclass");
  }
  
  public abstract int getMoveStatus(int paramInt);
  
  public abstract String getNameForUid(int paramInt);
  
  public abstract String[] getNamesForUids(int[] paramArrayOfint);
  
  public PackageInfo getPackageArchiveInfo(String paramString, int paramInt) {
    boolean bool;
    int i = paramInt;
    if ((paramInt & 0xC0000) == 0)
      i = paramInt | 0xC0000; 
    if ((i & 0x40) != 0 || (0x8000000 & i) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    ParseResult parseResult = ParsingPackageUtils.parseDefault(ParseTypeImpl.forParsingWithoutPlatformCompat().reset(), new File(paramString), 0, bool);
    return parseResult.isError() ? null : PackageInfoWithoutStateUtils.generate((ParsingPackageRead)parseResult.getResult(), null, i, 0L, 0L, null, new PackageUserState(), UserHandle.getCallingUserId());
  }
  
  public abstract List<VolumeInfo> getPackageCandidateVolumes(ApplicationInfo paramApplicationInfo);
  
  public abstract VolumeInfo getPackageCurrentVolume(ApplicationInfo paramApplicationInfo);
  
  public abstract int[] getPackageGids(String paramString) throws NameNotFoundException;
  
  public abstract int[] getPackageGids(String paramString, int paramInt) throws NameNotFoundException;
  
  public abstract PackageInfo getPackageInfo(VersionedPackage paramVersionedPackage, int paramInt) throws NameNotFoundException;
  
  public abstract PackageInfo getPackageInfo(String paramString, int paramInt) throws NameNotFoundException;
  
  public abstract PackageInfo getPackageInfoAsUser(String paramString, int paramInt1, int paramInt2) throws NameNotFoundException;
  
  public abstract PackageInstaller getPackageInstaller();
  
  @Deprecated
  public void getPackageSizeInfo(String paramString, IPackageStatsObserver paramIPackageStatsObserver) {
    getPackageSizeInfoAsUser(paramString, getUserId(), paramIPackageStatsObserver);
  }
  
  @Deprecated
  public abstract void getPackageSizeInfoAsUser(String paramString, int paramInt, IPackageStatsObserver paramIPackageStatsObserver);
  
  public abstract int getPackageUid(String paramString, int paramInt) throws NameNotFoundException;
  
  public abstract int getPackageUidAsUser(String paramString, int paramInt) throws NameNotFoundException;
  
  public abstract int getPackageUidAsUser(String paramString, int paramInt1, int paramInt2) throws NameNotFoundException;
  
  public abstract String[] getPackagesForUid(int paramInt);
  
  public abstract List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt);
  
  public abstract String getPermissionControllerPackageName();
  
  @SystemApi
  public abstract int getPermissionFlags(String paramString1, String paramString2, UserHandle paramUserHandle);
  
  public abstract PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt) throws NameNotFoundException;
  
  public abstract PermissionInfo getPermissionInfo(String paramString, int paramInt) throws NameNotFoundException;
  
  @Deprecated
  public abstract int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString);
  
  @Deprecated
  public abstract List<PackageInfo> getPreferredPackages(int paramInt);
  
  public abstract List<VolumeInfo> getPrimaryStorageCandidateVolumes();
  
  public abstract VolumeInfo getPrimaryStorageCurrentVolume();
  
  public abstract ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt) throws NameNotFoundException;
  
  public abstract ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt) throws NameNotFoundException;
  
  public abstract Resources getResourcesForActivity(ComponentName paramComponentName) throws NameNotFoundException;
  
  public abstract Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo) throws NameNotFoundException;
  
  public abstract Resources getResourcesForApplication(String paramString) throws NameNotFoundException;
  
  public abstract Resources getResourcesForApplicationAsUser(String paramString, int paramInt) throws NameNotFoundException;
  
  public abstract ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt) throws NameNotFoundException;
  
  public abstract String getServicesSystemSharedLibraryPackageName();
  
  public String getSetupWizardPackageName() {
    throw new UnsupportedOperationException("getSetupWizardPackageName not implemented in subclass");
  }
  
  public abstract List<SharedLibraryInfo> getSharedLibraries(int paramInt);
  
  public abstract List<SharedLibraryInfo> getSharedLibrariesAsUser(int paramInt1, int paramInt2);
  
  public abstract String getSharedSystemSharedLibraryPackageName();
  
  public abstract KeySet getSigningKeySet(String paramString);
  
  public Bundle getSuspendedPackageAppExtras() {
    throw new UnsupportedOperationException("getSuspendedPackageAppExtras not implemented");
  }
  
  public boolean getSyntheticAppDetailsActivityEnabled(String paramString) {
    throw new UnsupportedOperationException("getSyntheticAppDetailsActivityEnabled not implemented");
  }
  
  public abstract FeatureInfo[] getSystemAvailableFeatures();
  
  public String getSystemCaptionsServicePackageName() {
    throw new UnsupportedOperationException("getSystemCaptionsServicePackageName not implemented in subclass");
  }
  
  public abstract String[] getSystemSharedLibraryNames();
  
  public String getSystemTextClassifierPackageName() {
    throw new UnsupportedOperationException("getSystemTextClassifierPackageName not implemented in subclass");
  }
  
  public abstract CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo);
  
  public abstract int getUidForSharedUser(String paramString) throws NameNotFoundException;
  
  @SystemApi
  public String[] getUnsuspendablePackages(String[] paramArrayOfString) {
    throw new UnsupportedOperationException("getUnsuspendablePackages not implemented");
  }
  
  public abstract Drawable getUserBadgeForDensity(UserHandle paramUserHandle, int paramInt);
  
  public abstract Drawable getUserBadgeForDensityNoBackground(UserHandle paramUserHandle, int paramInt);
  
  public abstract Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt);
  
  public abstract Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle);
  
  public abstract CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle);
  
  public int getUserId() {
    return UserHandle.myUserId();
  }
  
  public abstract VerifierDeviceIdentity getVerifierDeviceIdentity();
  
  public String getWellbeingPackageName() {
    throw new UnsupportedOperationException("getWellbeingPackageName not implemented in subclass");
  }
  
  public Set<String> getWhitelistedRestrictedPermissions(String paramString, int paramInt) {
    return Collections.emptySet();
  }
  
  public abstract XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo);
  
  public void grantImplicitAccess(int paramInt, String paramString) {
    try {
      ActivityThread.getPackageManager().grantImplicitAccess(paramInt, paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public abstract void grantRuntimePermission(String paramString1, String paramString2, UserHandle paramUserHandle);
  
  public boolean hasSigningCertificate(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    throw new UnsupportedOperationException("hasSigningCertificate not implemented in subclass");
  }
  
  public boolean hasSigningCertificate(String paramString, byte[] paramArrayOfbyte, int paramInt) {
    throw new UnsupportedOperationException("hasSigningCertificate not implemented in subclass");
  }
  
  public abstract boolean hasSystemFeature(String paramString);
  
  public abstract boolean hasSystemFeature(String paramString, int paramInt);
  
  @SystemApi
  @Deprecated
  public abstract int installExistingPackage(String paramString) throws NameNotFoundException;
  
  @SystemApi
  @Deprecated
  public abstract int installExistingPackage(String paramString, int paramInt) throws NameNotFoundException;
  
  @Deprecated
  public abstract int installExistingPackageAsUser(String paramString, int paramInt) throws NameNotFoundException;
  
  public boolean isAutoRevokeWhitelisted() {
    throw new UnsupportedOperationException("isAutoRevokeWhitelisted not implemented in subclass");
  }
  
  public boolean isAutoRevokeWhitelisted(String paramString) {
    return false;
  }
  
  public boolean isDefaultApplicationIcon(Drawable paramDrawable) {
    boolean bool2;
    boolean bool = paramDrawable instanceof AdaptiveIconDrawable;
    boolean bool1 = false;
    if (bool) {
      bool2 = ((AdaptiveIconDrawable)paramDrawable).getSourceDrawableResId();
    } else {
      bool2 = false;
    } 
    if (bool2 == 17301651 || bool2 == 17303652)
      bool1 = true; 
    return bool1;
  }
  
  public boolean isDeviceUpgrading() {
    return false;
  }
  
  public abstract boolean isInstantApp();
  
  public abstract boolean isInstantApp(String paramString);
  
  public abstract boolean isPackageAvailable(String paramString);
  
  public boolean isPackageStateProtected(String paramString, int paramInt) {
    throw new UnsupportedOperationException("isPackageStateProtected not implemented in subclass");
  }
  
  public boolean isPackageSuspended() {
    throw new UnsupportedOperationException("isPackageSuspended not implemented");
  }
  
  public boolean isPackageSuspended(String paramString) throws NameNotFoundException {
    throw new UnsupportedOperationException("isPackageSuspended not implemented");
  }
  
  public abstract boolean isPackageSuspendedForUser(String paramString, int paramInt);
  
  public abstract boolean isPermissionRevokedByPolicy(String paramString1, String paramString2);
  
  public abstract boolean isSafeMode();
  
  public abstract boolean isSignedBy(String paramString, KeySet paramKeySet);
  
  public abstract boolean isSignedByExactly(String paramString, KeySet paramKeySet);
  
  public abstract boolean isUpgrade();
  
  public abstract boolean isWirelessConsentModeEnabled();
  
  public abstract Drawable loadItemIcon(PackageItemInfo paramPackageItemInfo, ApplicationInfo paramApplicationInfo);
  
  public abstract Drawable loadUnbadgedItemIcon(PackageItemInfo paramPackageItemInfo, ApplicationInfo paramApplicationInfo);
  
  public abstract int movePackage(String paramString, VolumeInfo paramVolumeInfo);
  
  public abstract int movePrimaryStorage(VolumeInfo paramVolumeInfo);
  
  public abstract List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt);
  
  @Deprecated
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt1, int paramInt2) {
    if (VMRuntime.getRuntime().getTargetSdkVersion() < 26) {
      Log.d("PackageManager", "Shame on you for calling the hidden API queryBroadcastReceivers(). Shame!");
      return queryBroadcastReceiversAsUser(paramIntent, paramInt1, paramInt2);
    } 
    throw new UnsupportedOperationException("Shame on you for calling the hidden API queryBroadcastReceivers(). Shame!");
  }
  
  public abstract List<ResolveInfo> queryBroadcastReceiversAsUser(Intent paramIntent, int paramInt1, int paramInt2);
  
  @SystemApi
  public List<ResolveInfo> queryBroadcastReceiversAsUser(Intent paramIntent, int paramInt, UserHandle paramUserHandle) {
    return queryBroadcastReceiversAsUser(paramIntent, paramInt, paramUserHandle.getIdentifier());
  }
  
  public abstract List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2);
  
  public List<ProviderInfo> queryContentProviders(String paramString1, int paramInt1, int paramInt2, String paramString2) {
    return queryContentProviders(paramString1, paramInt1, paramInt2);
  }
  
  public abstract List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt);
  
  public abstract List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt);
  
  public abstract List<ResolveInfo> queryIntentActivitiesAsUser(Intent paramIntent, int paramInt1, int paramInt2);
  
  @SystemApi
  public List<ResolveInfo> queryIntentActivitiesAsUser(Intent paramIntent, int paramInt, UserHandle paramUserHandle) {
    return queryIntentActivitiesAsUser(paramIntent, paramInt, paramUserHandle.getIdentifier());
  }
  
  public abstract List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt);
  
  public abstract List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt);
  
  public abstract List<ResolveInfo> queryIntentContentProvidersAsUser(Intent paramIntent, int paramInt1, int paramInt2);
  
  @SystemApi
  public List<ResolveInfo> queryIntentContentProvidersAsUser(Intent paramIntent, int paramInt, UserHandle paramUserHandle) {
    return queryIntentContentProvidersAsUser(paramIntent, paramInt, paramUserHandle.getIdentifier());
  }
  
  public abstract List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt);
  
  public abstract List<ResolveInfo> queryIntentServicesAsUser(Intent paramIntent, int paramInt1, int paramInt2);
  
  @SystemApi
  public List<ResolveInfo> queryIntentServicesAsUser(Intent paramIntent, int paramInt, UserHandle paramUserHandle) {
    return queryIntentServicesAsUser(paramIntent, paramInt, paramUserHandle.getIdentifier());
  }
  
  public abstract List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt) throws NameNotFoundException;
  
  @SystemApi
  public abstract void registerDexModule(String paramString, DexModuleRegisterCallback paramDexModuleRegisterCallback);
  
  public abstract void registerMoveCallback(MoveCallback paramMoveCallback, Handler paramHandler);
  
  @SystemApi
  public abstract void removeOnPermissionsChangeListener(OnPermissionsChangedListener paramOnPermissionsChangedListener);
  
  @Deprecated
  public abstract void removePackageFromPreferred(String paramString);
  
  public abstract void removePermission(String paramString);
  
  public boolean removeWhitelistedRestrictedPermission(String paramString1, String paramString2, int paramInt) {
    return false;
  }
  
  @SystemApi
  public void replacePreferredActivity(IntentFilter paramIntentFilter, int paramInt, List<ComponentName> paramList, ComponentName paramComponentName) {
    replacePreferredActivity(paramIntentFilter, paramInt, paramList.<ComponentName>toArray(new ComponentName[0]), paramComponentName);
  }
  
  @Deprecated
  public abstract void replacePreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName);
  
  @Deprecated
  public void replacePreferredActivityAsUser(IntentFilter paramIntentFilter, int paramInt1, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName, int paramInt2) {
    throw new RuntimeException("Not implemented. Must override in a subclass.");
  }
  
  public abstract ResolveInfo resolveActivity(Intent paramIntent, int paramInt);
  
  public abstract ResolveInfo resolveActivityAsUser(Intent paramIntent, int paramInt1, int paramInt2);
  
  public abstract ProviderInfo resolveContentProvider(String paramString, int paramInt);
  
  public abstract ProviderInfo resolveContentProviderAsUser(String paramString, int paramInt1, int paramInt2);
  
  public abstract ResolveInfo resolveService(Intent paramIntent, int paramInt);
  
  public abstract ResolveInfo resolveServiceAsUser(Intent paramIntent, int paramInt1, int paramInt2);
  
  @SystemApi
  public abstract void revokeRuntimePermission(String paramString1, String paramString2, UserHandle paramUserHandle);
  
  @SystemApi
  public void revokeRuntimePermission(String paramString1, String paramString2, UserHandle paramUserHandle, String paramString3) {
    revokeRuntimePermission(paramString1, paramString2, paramUserHandle);
  }
  
  @SystemApi
  public void sendDeviceCustomizationReadyBroadcast() {
    throw new UnsupportedOperationException("sendDeviceCustomizationReadyBroadcast not implemented in subclass");
  }
  
  public abstract void setApplicationCategoryHint(String paramString, int paramInt);
  
  public abstract void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2);
  
  public abstract boolean setApplicationHiddenSettingAsUser(String paramString, boolean paramBoolean, UserHandle paramUserHandle);
  
  public boolean setAutoRevokeWhitelisted(String paramString, boolean paramBoolean) {
    return false;
  }
  
  public abstract void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2);
  
  @SystemApi
  public abstract boolean setDefaultBrowserPackageNameAsUser(String paramString, int paramInt);
  
  @SystemApi
  public String[] setDistractingPackageRestrictions(String[] paramArrayOfString, int paramInt) {
    throw new UnsupportedOperationException("setDistractingPackageRestrictions not implemented");
  }
  
  @SystemApi
  public void setHarmfulAppWarning(String paramString, CharSequence paramCharSequence) {
    throw new UnsupportedOperationException("setHarmfulAppWarning not implemented in subclass");
  }
  
  public abstract void setInstallerPackageName(String paramString1, String paramString2);
  
  public abstract boolean setInstantAppCookie(byte[] paramArrayOfbyte);
  
  public void setMimeGroup(String paramString, Set<String> paramSet) {
    throw new UnsupportedOperationException("setMimeGroup not implemented in subclass");
  }
  
  @SystemApi
  public String[] setPackagesSuspended(String[] paramArrayOfString, boolean paramBoolean, PersistableBundle paramPersistableBundle1, PersistableBundle paramPersistableBundle2, SuspendDialogInfo paramSuspendDialogInfo) {
    throw new UnsupportedOperationException("setPackagesSuspended not implemented");
  }
  
  @SystemApi
  @Deprecated
  public String[] setPackagesSuspended(String[] paramArrayOfString, boolean paramBoolean, PersistableBundle paramPersistableBundle1, PersistableBundle paramPersistableBundle2, String paramString) {
    throw new UnsupportedOperationException("setPackagesSuspended not implemented");
  }
  
  @SystemApi
  public void setSyntheticAppDetailsActivityEnabled(String paramString, boolean paramBoolean) {
    throw new UnsupportedOperationException("setSyntheticAppDetailsActivityEnabled not implemented");
  }
  
  public void setSystemAppState(String paramString, int paramInt) {
    throw new RuntimeException("Not implemented. Must override in a subclass");
  }
  
  @SystemApi
  public abstract void setUpdateAvailable(String paramString, boolean paramBoolean);
  
  public abstract boolean shouldShowRequestPermissionRationale(String paramString);
  
  public abstract void unregisterMoveCallback(MoveCallback paramMoveCallback);
  
  public abstract void updateInstantAppCookie(byte[] paramArrayOfbyte);
  
  @SystemApi
  public abstract boolean updateIntentVerificationStatusAsUser(String paramString, int paramInt1, int paramInt2);
  
  @SystemApi
  public abstract void updatePermissionFlags(String paramString1, String paramString2, int paramInt1, int paramInt2, UserHandle paramUserHandle);
  
  @SystemApi
  public abstract void verifyIntentFilter(int paramInt1, int paramInt2, List<String> paramList);
  
  public abstract void verifyPendingInstall(int paramInt1, int paramInt2);
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ApplicationInfoFlags {}
  
  private static final class ApplicationInfoQuery {
    final int flags;
    
    final String packageName;
    
    final int userId;
    
    ApplicationInfoQuery(String param1String, int param1Int1, int param1Int2) {
      this.packageName = param1String;
      this.flags = param1Int1;
      this.userId = param1Int2;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = false;
      if (param1Object == null)
        return false; 
      try {
        param1Object = param1Object;
        boolean bool1 = bool;
        if (Objects.equals(this.packageName, ((ApplicationInfoQuery)param1Object).packageName)) {
          bool1 = bool;
          if (this.flags == ((ApplicationInfoQuery)param1Object).flags) {
            bool1 = bool;
            if (this.userId == ((ApplicationInfoQuery)param1Object).userId)
              bool1 = true; 
          } 
        } 
        return bool1;
      } catch (ClassCastException classCastException) {
        return false;
      } 
    }
    
    public int hashCode() {
      return (Objects.hashCode(this.packageName) * 13 + Objects.hashCode(Integer.valueOf(this.flags))) * 13 + Objects.hashCode(Integer.valueOf(this.userId));
    }
    
    public String toString() {
      return String.format("ApplicationInfoQuery(packageName=\"%s\", flags=%s, userId=%s)", new Object[] { this.packageName, Integer.valueOf(this.flags), Integer.valueOf(this.userId) });
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface CertificateInputType {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ComponentInfoFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface DeleteFlags {}
  
  @SystemApi
  public static abstract class DexModuleRegisterCallback {
    public abstract void onDexModuleRegistered(String param1String1, boolean param1Boolean, String param1String2);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface DistractionRestriction {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface EnabledFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface EnabledState {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface InstallFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface InstallReason {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface InstalledModulesFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface InstrumentationInfoFlags {}
  
  public static class LegacyPackageDeleteObserver extends PackageDeleteObserver {
    private final IPackageDeleteObserver mLegacy;
    
    public LegacyPackageDeleteObserver(IPackageDeleteObserver param1IPackageDeleteObserver) {
      this.mLegacy = param1IPackageDeleteObserver;
    }
    
    public void onPackageDeleted(String param1String1, int param1Int, String param1String2) {
      IPackageDeleteObserver iPackageDeleteObserver = this.mLegacy;
      if (iPackageDeleteObserver == null)
        return; 
      try {
        iPackageDeleteObserver.packageDeleted(param1String1, param1Int);
      } catch (RemoteException remoteException) {}
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ModuleInfoFlags {}
  
  public static abstract class MoveCallback {
    public void onCreated(int param1Int, Bundle param1Bundle) {}
    
    public abstract void onStatusChanged(int param1Int1, int param1Int2, long param1Long);
  }
  
  public static class NameNotFoundException extends AndroidException {
    public NameNotFoundException() {}
    
    public NameNotFoundException(String param1String) {
      super(param1String);
    }
  }
  
  @SystemApi
  public static interface OnPermissionsChangedListener {
    void onPermissionsChanged(int param1Int);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PackageInfoFlags {}
  
  private static final class PackageInfoQuery {
    final int flags;
    
    final String packageName;
    
    final int userId;
    
    PackageInfoQuery(String param1String, int param1Int1, int param1Int2) {
      this.packageName = param1String;
      this.flags = param1Int1;
      this.userId = param1Int2;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = false;
      if (param1Object == null)
        return false; 
      try {
        param1Object = param1Object;
        boolean bool1 = bool;
        if (Objects.equals(this.packageName, ((PackageInfoQuery)param1Object).packageName)) {
          bool1 = bool;
          if (this.flags == ((PackageInfoQuery)param1Object).flags) {
            bool1 = bool;
            if (this.userId == ((PackageInfoQuery)param1Object).userId)
              bool1 = true; 
          } 
        } 
        return bool1;
      } catch (ClassCastException classCastException) {
        return false;
      } 
    }
    
    public int hashCode() {
      return (Objects.hashCode(this.packageName) * 13 + Objects.hashCode(Integer.valueOf(this.flags))) * 13 + Objects.hashCode(Integer.valueOf(this.userId));
    }
    
    public String toString() {
      return String.format("PackageInfoQuery(packageName=\"%s\", flags=%s, userId=%s)", new Object[] { this.packageName, Integer.valueOf(this.flags), Integer.valueOf(this.userId) });
    }
  }
  
  @SystemApi
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PermissionFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PermissionGroupInfoFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PermissionInfoFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PermissionResult {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PermissionWhitelistFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ResolveInfoFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface RollbackDataPolicy {
    public static final int RESTORE = 0;
    
    public static final int RETAIN = 2;
    
    public static final int WIPE = 1;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SignatureResult {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SystemAppState {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface UninstallReason {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
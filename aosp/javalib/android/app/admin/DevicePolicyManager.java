package android.app.admin;

import android.annotation.SystemApi;
import android.app.IServiceConnection;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageManager;
import android.content.pm.ParceledListSlice;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.net.NetworkUtils;
import android.net.PrivateDnsConnectivityChecker;
import android.net.Proxy;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.os.UserHandle;
import android.os.UserManager;
import android.security.AttestedKeyPair;
import android.security.Credentials;
import android.security.KeyChain;
import android.security.KeyChainException;
import android.security.keymaster.KeymasterCertificateChain;
import android.security.keystore.AttestationUtils;
import android.security.keystore.KeyAttestationException;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.ParcelableKeyGenParameterSpec;
import android.security.keystore.StrongBoxUnavailableException;
import android.telephony.data.ApnSetting;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.os.BackgroundThread;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.pooled.PooledLambda;
import com.android.org.conscrypt.TrustedCertificateStore;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;

public class DevicePolicyManager {
  @SystemApi
  public static final String ACCOUNT_FEATURE_DEVICE_OR_PROFILE_OWNER_ALLOWED = "android.account.DEVICE_OR_PROFILE_OWNER_ALLOWED";
  
  @SystemApi
  public static final String ACCOUNT_FEATURE_DEVICE_OR_PROFILE_OWNER_DISALLOWED = "android.account.DEVICE_OR_PROFILE_OWNER_DISALLOWED";
  
  public static final String ACTION_ADD_DEVICE_ADMIN = "android.app.action.ADD_DEVICE_ADMIN";
  
  public static final String ACTION_ADMIN_POLICY_COMPLIANCE = "android.app.action.ADMIN_POLICY_COMPLIANCE";
  
  public static final String ACTION_APPLICATION_DELEGATION_SCOPES_CHANGED = "android.app.action.APPLICATION_DELEGATION_SCOPES_CHANGED";
  
  @SystemApi
  public static final String ACTION_BIND_SECONDARY_LOCKSCREEN_SERVICE = "android.app.action.BIND_SECONDARY_LOCKSCREEN_SERVICE";
  
  public static final String ACTION_BUGREPORT_SHARING_ACCEPTED = "com.android.server.action.REMOTE_BUGREPORT_SHARING_ACCEPTED";
  
  public static final String ACTION_BUGREPORT_SHARING_DECLINED = "com.android.server.action.REMOTE_BUGREPORT_SHARING_DECLINED";
  
  public static final String ACTION_CHECK_POLICY_COMPLIANCE = "android.app.action.CHECK_POLICY_COMPLIANCE";
  
  public static final String ACTION_DATA_SHARING_RESTRICTION_APPLIED = "android.app.action.DATA_SHARING_RESTRICTION_APPLIED";
  
  public static final String ACTION_DATA_SHARING_RESTRICTION_CHANGED = "android.app.action.DATA_SHARING_RESTRICTION_CHANGED";
  
  public static final String ACTION_DEVICE_ADMIN_SERVICE = "android.app.action.DEVICE_ADMIN_SERVICE";
  
  public static final String ACTION_DEVICE_OWNER_CHANGED = "android.app.action.DEVICE_OWNER_CHANGED";
  
  public static final String ACTION_DEVICE_POLICY_MANAGER_STATE_CHANGED = "android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED";
  
  public static final String ACTION_GET_PROVISIONING_MODE = "android.app.action.GET_PROVISIONING_MODE";
  
  public static final String ACTION_MANAGED_PROFILE_PROVISIONED = "android.app.action.MANAGED_PROFILE_PROVISIONED";
  
  public static final String ACTION_MANAGED_USER_CREATED = "android.app.action.MANAGED_USER_CREATED";
  
  public static final String ACTION_PROFILE_OWNER_CHANGED = "android.app.action.PROFILE_OWNER_CHANGED";
  
  public static final String ACTION_PROVISIONING_SUCCESSFUL = "android.app.action.PROVISIONING_SUCCESSFUL";
  
  @SystemApi
  public static final String ACTION_PROVISION_FINALIZATION = "android.app.action.PROVISION_FINALIZATION";
  
  @SystemApi
  public static final String ACTION_PROVISION_FINANCED_DEVICE = "android.app.action.PROVISION_FINANCED_DEVICE";
  
  public static final String ACTION_PROVISION_MANAGED_DEVICE = "android.app.action.PROVISION_MANAGED_DEVICE";
  
  @SystemApi
  public static final String ACTION_PROVISION_MANAGED_DEVICE_FROM_TRUSTED_SOURCE = "android.app.action.PROVISION_MANAGED_DEVICE_FROM_TRUSTED_SOURCE";
  
  public static final String ACTION_PROVISION_MANAGED_PROFILE = "android.app.action.PROVISION_MANAGED_PROFILE";
  
  public static final String ACTION_PROVISION_MANAGED_SHAREABLE_DEVICE = "android.app.action.PROVISION_MANAGED_SHAREABLE_DEVICE";
  
  public static final String ACTION_PROVISION_MANAGED_USER = "android.app.action.PROVISION_MANAGED_USER";
  
  public static final String ACTION_REMOTE_BUGREPORT_DISPATCH = "android.intent.action.REMOTE_BUGREPORT_DISPATCH";
  
  @SystemApi
  public static final String ACTION_RESET_PROTECTION_POLICY_CHANGED = "android.app.action.RESET_PROTECTION_POLICY_CHANGED";
  
  public static final String ACTION_SET_NEW_PARENT_PROFILE_PASSWORD = "android.app.action.SET_NEW_PARENT_PROFILE_PASSWORD";
  
  public static final String ACTION_SET_NEW_PASSWORD = "android.app.action.SET_NEW_PASSWORD";
  
  @SystemApi
  public static final String ACTION_SET_PROFILE_OWNER = "android.app.action.SET_PROFILE_OWNER";
  
  public static final String ACTION_SHOW_DEVICE_MONITORING_DIALOG = "android.app.action.SHOW_DEVICE_MONITORING_DIALOG";
  
  public static final String ACTION_START_ENCRYPTION = "android.app.action.START_ENCRYPTION";
  
  @SystemApi
  public static final String ACTION_STATE_USER_SETUP_COMPLETE = "android.app.action.STATE_USER_SETUP_COMPLETE";
  
  public static final String ACTION_SYSTEM_UPDATE_POLICY_CHANGED = "android.app.action.SYSTEM_UPDATE_POLICY_CHANGED";
  
  public static final int CODE_ACCOUNTS_NOT_EMPTY = 6;
  
  public static final int CODE_CANNOT_ADD_MANAGED_PROFILE = 11;
  
  public static final int CODE_DEVICE_ADMIN_NOT_SUPPORTED = 13;
  
  public static final int CODE_HAS_DEVICE_OWNER = 1;
  
  public static final int CODE_HAS_PAIRED = 8;
  
  public static final int CODE_MANAGED_USERS_NOT_SUPPORTED = 9;
  
  public static final int CODE_NONSYSTEM_USER_EXISTS = 5;
  
  public static final int CODE_NOT_SYSTEM_USER = 7;
  
  public static final int CODE_NOT_SYSTEM_USER_SPLIT = 12;
  
  public static final int CODE_OK = 0;
  
  public static final int CODE_SPLIT_SYSTEM_USER_DEVICE_SYSTEM_USER = 14;
  
  public static final int CODE_SYSTEM_USER = 10;
  
  public static final int CODE_USER_HAS_PROFILE_OWNER = 2;
  
  public static final int CODE_USER_NOT_RUNNING = 3;
  
  public static final int CODE_USER_SETUP_COMPLETED = 4;
  
  public static final long DEFAULT_STRONG_AUTH_TIMEOUT_MS = 259200000L;
  
  public static final String DELEGATION_APP_RESTRICTIONS = "delegation-app-restrictions";
  
  public static final String DELEGATION_BLOCK_UNINSTALL = "delegation-block-uninstall";
  
  public static final String DELEGATION_CERT_INSTALL = "delegation-cert-install";
  
  public static final String DELEGATION_CERT_SELECTION = "delegation-cert-selection";
  
  public static final String DELEGATION_ENABLE_SYSTEM_APP = "delegation-enable-system-app";
  
  public static final String DELEGATION_INSTALL_EXISTING_PACKAGE = "delegation-install-existing-package";
  
  public static final String DELEGATION_KEEP_UNINSTALLED_PACKAGES = "delegation-keep-uninstalled-packages";
  
  public static final String DELEGATION_NETWORK_LOGGING = "delegation-network-logging";
  
  public static final String DELEGATION_PACKAGE_ACCESS = "delegation-package-access";
  
  public static final String DELEGATION_PERMISSION_GRANT = "delegation-permission-grant";
  
  public static final int ENCRYPTION_STATUS_ACTIVATING = 2;
  
  public static final int ENCRYPTION_STATUS_ACTIVE = 3;
  
  public static final int ENCRYPTION_STATUS_ACTIVE_DEFAULT_KEY = 4;
  
  public static final int ENCRYPTION_STATUS_ACTIVE_PER_USER = 5;
  
  public static final int ENCRYPTION_STATUS_INACTIVE = 1;
  
  public static final int ENCRYPTION_STATUS_UNSUPPORTED = 0;
  
  public static final int ERROR_VPN_PACKAGE_NOT_FOUND = 1;
  
  public static final String EXTRA_ADD_EXPLANATION = "android.app.extra.ADD_EXPLANATION";
  
  public static final String EXTRA_BUGREPORT_NOTIFICATION_TYPE = "android.app.extra.bugreport_notification_type";
  
  public static final String EXTRA_DELEGATION_SCOPES = "android.app.extra.DELEGATION_SCOPES";
  
  public static final String EXTRA_DEVICE_ADMIN = "android.app.extra.DEVICE_ADMIN";
  
  public static final String EXTRA_PASSWORD_COMPLEXITY = "android.app.extra.PASSWORD_COMPLEXITY";
  
  @SystemApi
  public static final String EXTRA_PROFILE_OWNER_NAME = "android.app.extra.PROFILE_OWNER_NAME";
  
  public static final String EXTRA_PROVISIONING_ACCOUNT_TO_MIGRATE = "android.app.extra.PROVISIONING_ACCOUNT_TO_MIGRATE";
  
  public static final String EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE = "android.app.extra.PROVISIONING_ADMIN_EXTRAS_BUNDLE";
  
  public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME = "android.app.extra.PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME";
  
  public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_MINIMUM_VERSION_CODE = "android.app.extra.PROVISIONING_DEVICE_ADMIN_MINIMUM_VERSION_CODE";
  
  public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_CHECKSUM = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_CHECKSUM";
  
  public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_COOKIE_HEADER = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_COOKIE_HEADER";
  
  public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION";
  
  @SystemApi
  public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_ICON_URI = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_ICON_URI";
  
  @SystemApi
  public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_LABEL = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_LABEL";
  
  @Deprecated
  public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME";
  
  public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM = "android.app.extra.PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM";
  
  public static final String EXTRA_PROVISIONING_DISCLAIMERS = "android.app.extra.PROVISIONING_DISCLAIMERS";
  
  public static final String EXTRA_PROVISIONING_DISCLAIMER_CONTENT = "android.app.extra.PROVISIONING_DISCLAIMER_CONTENT";
  
  public static final String EXTRA_PROVISIONING_DISCLAIMER_HEADER = "android.app.extra.PROVISIONING_DISCLAIMER_HEADER";
  
  @Deprecated
  public static final String EXTRA_PROVISIONING_EMAIL_ADDRESS = "android.app.extra.PROVISIONING_EMAIL_ADDRESS";
  
  public static final String EXTRA_PROVISIONING_IMEI = "android.app.extra.PROVISIONING_IMEI";
  
  public static final String EXTRA_PROVISIONING_KEEP_ACCOUNT_ON_MIGRATION = "android.app.extra.PROVISIONING_KEEP_ACCOUNT_ON_MIGRATION";
  
  public static final String EXTRA_PROVISIONING_LEAVE_ALL_SYSTEM_APPS_ENABLED = "android.app.extra.PROVISIONING_LEAVE_ALL_SYSTEM_APPS_ENABLED";
  
  public static final String EXTRA_PROVISIONING_LOCALE = "android.app.extra.PROVISIONING_LOCALE";
  
  public static final String EXTRA_PROVISIONING_LOCAL_TIME = "android.app.extra.PROVISIONING_LOCAL_TIME";
  
  public static final String EXTRA_PROVISIONING_LOGO_URI = "android.app.extra.PROVISIONING_LOGO_URI";
  
  public static final String EXTRA_PROVISIONING_MAIN_COLOR = "android.app.extra.PROVISIONING_MAIN_COLOR";
  
  public static final String EXTRA_PROVISIONING_MODE = "android.app.extra.PROVISIONING_MODE";
  
  @SystemApi
  public static final String EXTRA_PROVISIONING_ORGANIZATION_NAME = "android.app.extra.PROVISIONING_ORGANIZATION_NAME";
  
  public static final String EXTRA_PROVISIONING_SERIAL_NUMBER = "android.app.extra.PROVISIONING_SERIAL_NUMBER";
  
  public static final String EXTRA_PROVISIONING_SKIP_EDUCATION_SCREENS = "android.app.extra.PROVISIONING_SKIP_EDUCATION_SCREENS";
  
  public static final String EXTRA_PROVISIONING_SKIP_ENCRYPTION = "android.app.extra.PROVISIONING_SKIP_ENCRYPTION";
  
  public static final String EXTRA_PROVISIONING_SKIP_USER_CONSENT = "android.app.extra.PROVISIONING_SKIP_USER_CONSENT";
  
  public static final String EXTRA_PROVISIONING_SKIP_USER_SETUP = "android.app.extra.PROVISIONING_SKIP_USER_SETUP";
  
  @SystemApi
  public static final String EXTRA_PROVISIONING_SUPPORT_URL = "android.app.extra.PROVISIONING_SUPPORT_URL";
  
  public static final String EXTRA_PROVISIONING_TIME_ZONE = "android.app.extra.PROVISIONING_TIME_ZONE";
  
  @SystemApi
  public static final String EXTRA_PROVISIONING_TRIGGER = "android.app.extra.PROVISIONING_TRIGGER";
  
  public static final String EXTRA_PROVISIONING_USE_MOBILE_DATA = "android.app.extra.PROVISIONING_USE_MOBILE_DATA";
  
  public static final String EXTRA_PROVISIONING_WIFI_ANONYMOUS_IDENTITY = "android.app.extra.PROVISIONING_WIFI_ANONYMOUS_IDENTITY";
  
  public static final String EXTRA_PROVISIONING_WIFI_CA_CERTIFICATE = "android.app.extra.PROVISIONING_WIFI_CA_CERTIFICATE";
  
  public static final String EXTRA_PROVISIONING_WIFI_DOMAIN = "android.app.extra.PROVISIONING_WIFI_DOMAIN";
  
  public static final String EXTRA_PROVISIONING_WIFI_EAP_METHOD = "android.app.extra.PROVISIONING_WIFI_EAP_METHOD";
  
  public static final String EXTRA_PROVISIONING_WIFI_HIDDEN = "android.app.extra.PROVISIONING_WIFI_HIDDEN";
  
  public static final String EXTRA_PROVISIONING_WIFI_IDENTITY = "android.app.extra.PROVISIONING_WIFI_IDENTITY";
  
  public static final String EXTRA_PROVISIONING_WIFI_PAC_URL = "android.app.extra.PROVISIONING_WIFI_PAC_URL";
  
  public static final String EXTRA_PROVISIONING_WIFI_PASSWORD = "android.app.extra.PROVISIONING_WIFI_PASSWORD";
  
  public static final String EXTRA_PROVISIONING_WIFI_PHASE2_AUTH = "android.app.extra.PROVISIONING_WIFI_PHASE2_AUTH";
  
  public static final String EXTRA_PROVISIONING_WIFI_PROXY_BYPASS = "android.app.extra.PROVISIONING_WIFI_PROXY_BYPASS";
  
  public static final String EXTRA_PROVISIONING_WIFI_PROXY_HOST = "android.app.extra.PROVISIONING_WIFI_PROXY_HOST";
  
  public static final String EXTRA_PROVISIONING_WIFI_PROXY_PORT = "android.app.extra.PROVISIONING_WIFI_PROXY_PORT";
  
  public static final String EXTRA_PROVISIONING_WIFI_SECURITY_TYPE = "android.app.extra.PROVISIONING_WIFI_SECURITY_TYPE";
  
  public static final String EXTRA_PROVISIONING_WIFI_SSID = "android.app.extra.PROVISIONING_WIFI_SSID";
  
  public static final String EXTRA_PROVISIONING_WIFI_USER_CERTIFICATE = "android.app.extra.PROVISIONING_WIFI_USER_CERTIFICATE";
  
  public static final String EXTRA_REMOTE_BUGREPORT_HASH = "android.intent.extra.REMOTE_BUGREPORT_HASH";
  
  @SystemApi
  public static final String EXTRA_RESTRICTION = "android.app.extra.RESTRICTION";
  
  public static final int FLAG_EVICT_CREDENTIAL_ENCRYPTION_KEY = 1;
  
  public static final int FLAG_MANAGED_CAN_ACCESS_PARENT = 2;
  
  public static final int FLAG_PARENT_CAN_ACCESS_MANAGED = 1;
  
  public static final int ID_TYPE_BASE_INFO = 1;
  
  public static final int ID_TYPE_IMEI = 4;
  
  public static final int ID_TYPE_INDIVIDUAL_ATTESTATION = 16;
  
  public static final int ID_TYPE_MEID = 8;
  
  public static final int ID_TYPE_SERIAL = 2;
  
  public static final int INSTALLKEY_REQUEST_CREDENTIALS_ACCESS = 1;
  
  public static final int INSTALLKEY_SET_USER_SELECTABLE = 2;
  
  public static final int KEYGUARD_DISABLE_BIOMETRICS = 416;
  
  public static final int KEYGUARD_DISABLE_FACE = 128;
  
  public static final int KEYGUARD_DISABLE_FEATURES_ALL = 2147483647;
  
  public static final int KEYGUARD_DISABLE_FEATURES_NONE = 0;
  
  public static final int KEYGUARD_DISABLE_FINGERPRINT = 32;
  
  public static final int KEYGUARD_DISABLE_IRIS = 256;
  
  public static final int KEYGUARD_DISABLE_REMOTE_INPUT = 64;
  
  public static final int KEYGUARD_DISABLE_SECURE_CAMERA = 2;
  
  public static final int KEYGUARD_DISABLE_SECURE_NOTIFICATIONS = 4;
  
  public static final int KEYGUARD_DISABLE_TRUST_AGENTS = 16;
  
  public static final int KEYGUARD_DISABLE_UNREDACTED_NOTIFICATIONS = 8;
  
  public static final int KEYGUARD_DISABLE_WIDGETS_ALL = 1;
  
  public static final int KEY_GEN_STRONGBOX_UNAVAILABLE = 1;
  
  public static final int LEAVE_ALL_SYSTEM_APPS_ENABLED = 16;
  
  public static final int LOCK_TASK_FEATURE_BLOCK_ACTIVITY_START_IN_TASK = 64;
  
  public static final int LOCK_TASK_FEATURE_GLOBAL_ACTIONS = 16;
  
  public static final int LOCK_TASK_FEATURE_HOME = 4;
  
  public static final int LOCK_TASK_FEATURE_KEYGUARD = 32;
  
  public static final int LOCK_TASK_FEATURE_NONE = 0;
  
  public static final int LOCK_TASK_FEATURE_NOTIFICATIONS = 2;
  
  public static final int LOCK_TASK_FEATURE_OVERVIEW = 8;
  
  public static final int LOCK_TASK_FEATURE_SYSTEM_INFO = 1;
  
  public static final int MAKE_USER_DEMO = 4;
  
  public static final int MAKE_USER_EPHEMERAL = 2;
  
  public static final int MAX_PASSWORD_LENGTH = 16;
  
  public static final String MIME_TYPE_PROVISIONING_NFC = "application/com.android.managedprovisioning";
  
  public static final int NON_ORG_OWNED_PROFILE_KEYGUARD_FEATURES_AFFECT_OWNER = 432;
  
  public static final int NOTIFICATION_BUGREPORT_ACCEPTED_NOT_FINISHED = 2;
  
  public static final int NOTIFICATION_BUGREPORT_FINISHED_NOT_ACCEPTED = 3;
  
  public static final int NOTIFICATION_BUGREPORT_STARTED = 1;
  
  public static final int ORG_OWNED_PROFILE_KEYGUARD_FEATURES_PARENT_ONLY = 6;
  
  public static final int PASSWORD_COMPLEXITY_HIGH = 327680;
  
  public static final int PASSWORD_COMPLEXITY_LOW = 65536;
  
  public static final int PASSWORD_COMPLEXITY_MEDIUM = 196608;
  
  public static final int PASSWORD_COMPLEXITY_NONE = 0;
  
  public static final int PASSWORD_QUALITY_ALPHABETIC = 262144;
  
  public static final int PASSWORD_QUALITY_ALPHANUMERIC = 327680;
  
  public static final int PASSWORD_QUALITY_BIOMETRIC_WEAK = 32768;
  
  public static final int PASSWORD_QUALITY_COMPLEX = 393216;
  
  public static final int PASSWORD_QUALITY_MANAGED = 524288;
  
  public static final int PASSWORD_QUALITY_NUMERIC = 131072;
  
  public static final int PASSWORD_QUALITY_NUMERIC_COMPLEX = 196608;
  
  public static final int PASSWORD_QUALITY_SOMETHING = 65536;
  
  public static final int PASSWORD_QUALITY_UNSPECIFIED = 0;
  
  public static final int PERMISSION_GRANT_STATE_DEFAULT = 0;
  
  public static final int PERMISSION_GRANT_STATE_DENIED = 2;
  
  public static final int PERMISSION_GRANT_STATE_GRANTED = 1;
  
  public static final int PERMISSION_POLICY_AUTO_DENY = 2;
  
  public static final int PERMISSION_POLICY_AUTO_GRANT = 1;
  
  public static final int PERMISSION_POLICY_PROMPT = 0;
  
  public static final int PERSONAL_APPS_NOT_SUSPENDED = 0;
  
  public static final int PERSONAL_APPS_SUSPENDED_EXPLICITLY = 1;
  
  public static final int PERSONAL_APPS_SUSPENDED_PROFILE_TIMEOUT = 2;
  
  public static final String POLICY_DISABLE_CAMERA = "policy_disable_camera";
  
  public static final String POLICY_DISABLE_SCREEN_CAPTURE = "policy_disable_screen_capture";
  
  public static final String POLICY_SUSPEND_PACKAGES = "policy_suspend_packages";
  
  public static final int PRIVATE_DNS_MODE_OFF = 1;
  
  public static final int PRIVATE_DNS_MODE_OPPORTUNISTIC = 2;
  
  public static final int PRIVATE_DNS_MODE_PROVIDER_HOSTNAME = 3;
  
  public static final int PRIVATE_DNS_MODE_UNKNOWN = 0;
  
  public static final int PRIVATE_DNS_SET_ERROR_FAILURE_SETTING = 2;
  
  public static final int PRIVATE_DNS_SET_ERROR_HOST_NOT_SERVING = 1;
  
  public static final int PRIVATE_DNS_SET_NO_ERROR = 0;
  
  public static final int PROFILE_KEYGUARD_FEATURES_AFFECT_OWNER = 438;
  
  public static final int PROVISIONING_MODE_FULLY_MANAGED_DEVICE = 1;
  
  public static final int PROVISIONING_MODE_MANAGED_PROFILE = 2;
  
  @SystemApi
  public static final int PROVISIONING_TRIGGER_CLOUD_ENROLLMENT = 1;
  
  @SystemApi
  public static final int PROVISIONING_TRIGGER_PERSISTENT_DEVICE_OWNER = 3;
  
  @SystemApi
  public static final int PROVISIONING_TRIGGER_QR_CODE = 2;
  
  @SystemApi
  public static final int PROVISIONING_TRIGGER_UNSPECIFIED = 0;
  
  public static final int RESET_PASSWORD_DO_NOT_ASK_CREDENTIALS_ON_BOOT = 2;
  
  public static final int RESET_PASSWORD_REQUIRE_ENTRY = 1;
  
  public static final int SKIP_SETUP_WIZARD = 1;
  
  @SystemApi
  public static final int STATE_USER_PROFILE_COMPLETE = 4;
  
  @SystemApi
  public static final int STATE_USER_SETUP_COMPLETE = 2;
  
  @SystemApi
  public static final int STATE_USER_SETUP_FINALIZED = 3;
  
  @SystemApi
  public static final int STATE_USER_SETUP_INCOMPLETE = 1;
  
  @SystemApi
  public static final int STATE_USER_UNMANAGED = 0;
  
  private static String TAG = "DevicePolicyManager";
  
  public static final int WIPE_EUICC = 4;
  
  public static final int WIPE_EXTERNAL_STORAGE = 1;
  
  public static final int WIPE_RESET_PROTECTION_DATA = 2;
  
  public static final int WIPE_SILENTLY = 8;
  
  private final Context mContext;
  
  private final boolean mParentInstance;
  
  private final IDevicePolicyManager mService;
  
  public DevicePolicyManager(Context paramContext, IDevicePolicyManager paramIDevicePolicyManager) {
    this(paramContext, paramIDevicePolicyManager, false);
  }
  
  protected DevicePolicyManager(Context paramContext, IDevicePolicyManager paramIDevicePolicyManager, boolean paramBoolean) {
    this.mContext = paramContext;
    this.mService = paramIDevicePolicyManager;
    this.mParentInstance = paramBoolean;
  }
  
  private void executeCallback(int paramInt, String paramString, Executor paramExecutor, InstallSystemUpdateCallback paramInstallSystemUpdateCallback) {
    paramExecutor.execute(new _$$Lambda$DevicePolicyManager$aBAov4sAc4DWENs1_hCXh31NAg0(paramInstallSystemUpdateCallback, paramInt, paramString));
  }
  
  private static String getCaCertAlias(byte[] paramArrayOfbyte) throws CertificateException {
    X509Certificate x509Certificate = (X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramArrayOfbyte));
    return (new TrustedCertificateStore()).getCertificateAlias(x509Certificate);
  }
  
  private ComponentName getDeviceOwnerComponentInner(boolean paramBoolean) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getDeviceOwnerComponent(paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  private boolean isDeviceOwnerAppOnAnyUserInner(String paramString, boolean paramBoolean) {
    if (paramString == null)
      return false; 
    ComponentName componentName = getDeviceOwnerComponentInner(paramBoolean);
    return (componentName == null) ? false : paramString.equals(componentName.getPackageName());
  }
  
  private void throwIfParentInstance(String paramString) {
    if (!this.mParentInstance)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(" cannot be called on the parent instance");
    throw new SecurityException(stringBuilder.toString());
  }
  
  private void wipeDataInternal(int paramInt, String paramString) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.wipeDataWithReason(paramInt, paramString, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void addCrossProfileIntentFilter(ComponentName paramComponentName, IntentFilter paramIntentFilter, int paramInt) {
    throwIfParentInstance("addCrossProfileIntentFilter");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.addCrossProfileIntentFilter(paramComponentName, paramIntentFilter, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean addCrossProfileWidgetProvider(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("addCrossProfileWidgetProvider");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.addCrossProfileWidgetProvider(paramComponentName, paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public int addOverrideApn(ComponentName paramComponentName, ApnSetting paramApnSetting) {
    throwIfParentInstance("addOverrideApn");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.addOverrideApn(paramComponentName, paramApnSetting);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return -1;
  }
  
  public void addPersistentPreferredActivity(ComponentName paramComponentName1, IntentFilter paramIntentFilter, ComponentName paramComponentName2) {
    throwIfParentInstance("addPersistentPreferredActivity");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.addPersistentPreferredActivity(paramComponentName1, paramIntentFilter, paramComponentName2);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void addUserRestriction(ComponentName paramComponentName, String paramString) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setUserRestriction(paramComponentName, paramString, true, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean approveCaCert(String paramString, int paramInt, boolean paramBoolean) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.approveCaCert(paramString, paramInt, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean bindDeviceAdminServiceAsUser(ComponentName paramComponentName, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt, UserHandle paramUserHandle) {
    throwIfParentInstance("bindDeviceAdminServiceAsUser");
    try {
      IServiceConnection iServiceConnection = this.mContext.getServiceDispatcher(paramServiceConnection, this.mContext.getMainThreadHandler(), paramInt);
      paramIntent.prepareToLeaveProcess(this.mContext);
      return this.mService.bindDeviceAdminServiceAsUser(paramComponentName, this.mContext.getIApplicationThread(), this.mContext.getActivityToken(), paramIntent, iServiceConnection, paramInt, paramUserHandle.getIdentifier());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean canProfileOwnerResetPasswordWhenLocked(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.canProfileOwnerResetPasswordWhenLocked(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public int checkProvisioningPreCondition(String paramString1, String paramString2) {
    try {
      return this.mService.checkProvisioningPreCondition(paramString1, paramString2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void clearApplicationUserData(ComponentName paramComponentName, String paramString, Executor paramExecutor, OnClearApplicationUserDataListener paramOnClearApplicationUserDataListener) {
    throwIfParentInstance("clearAppData");
    Objects.requireNonNull(paramExecutor);
    Objects.requireNonNull(paramOnClearApplicationUserDataListener);
    try {
      IDevicePolicyManager iDevicePolicyManager = this.mService;
      IPackageDataObserver.Stub stub = new IPackageDataObserver.Stub() {
          public void onRemoveCompleted(String param1String, boolean param1Boolean) {
            executor.execute(new _$$Lambda$DevicePolicyManager$1$k6Rmp3Fg9FFATYRU5Z7rHDXGemA(listener, param1String, param1Boolean));
          }
        };
      super(this, paramExecutor, paramOnClearApplicationUserDataListener);
      iDevicePolicyManager.clearApplicationUserData(paramComponentName, paramString, (IPackageDataObserver)stub);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void clearCrossProfileIntentFilters(ComponentName paramComponentName) {
    throwIfParentInstance("clearCrossProfileIntentFilters");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.clearCrossProfileIntentFilters(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @Deprecated
  public void clearDeviceOwnerApp(String paramString) {
    throwIfParentInstance("clearDeviceOwnerApp");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.clearDeviceOwner(paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void clearPackagePersistentPreferredActivities(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("clearPackagePersistentPreferredActivities");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.clearPackagePersistentPreferredActivities(paramComponentName, paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @Deprecated
  public void clearProfileOwner(ComponentName paramComponentName) {
    throwIfParentInstance("clearProfileOwner");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.clearProfileOwner(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean clearResetPasswordToken(ComponentName paramComponentName) {
    throwIfParentInstance("clearResetPasswordToken");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.clearResetPasswordToken(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public void clearSystemUpdatePolicyFreezePeriodRecord() {
    throwIfParentInstance("clearSystemUpdatePolicyFreezePeriodRecord");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager == null)
      return; 
    try {
      iDevicePolicyManager.clearSystemUpdatePolicyFreezePeriodRecord();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void clearUserRestriction(ComponentName paramComponentName, String paramString) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setUserRestriction(paramComponentName, paramString, false, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public Intent createAdminSupportIntent(String paramString) {
    throwIfParentInstance("createAdminSupportIntent");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.createAdminSupportIntent(paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public UserHandle createAndManageUser(ComponentName paramComponentName1, String paramString, ComponentName paramComponentName2, PersistableBundle paramPersistableBundle, int paramInt) {
    throwIfParentInstance("createAndManageUser");
    try {
      return this.mService.createAndManageUser(paramComponentName1, paramString, paramComponentName2, paramPersistableBundle, paramInt);
    } catch (ServiceSpecificException serviceSpecificException) {
      throw new UserManager.UserOperationException(serviceSpecificException.getMessage(), serviceSpecificException.errorCode);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int enableSystemApp(ComponentName paramComponentName, Intent paramIntent) {
    throwIfParentInstance("enableSystemApp");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.enableSystemAppWithIntent(paramComponentName, this.mContext.getPackageName(), paramIntent);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public void enableSystemApp(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("enableSystemApp");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.enableSystemApp(paramComponentName, this.mContext.getPackageName(), paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public long forceNetworkLogs() {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager == null)
      return -1L; 
    try {
      return iDevicePolicyManager.forceNetworkLogs();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void forceRemoveActiveAdmin(ComponentName paramComponentName, int paramInt) {
    try {
      this.mService.forceRemoveActiveAdmin(paramComponentName, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public long forceSecurityLogs() {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager == null)
      return 0L; 
    try {
      return iDevicePolicyManager.forceSecurityLogs();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void forceUpdateUserSetupComplete() {
    try {
      this.mService.forceUpdateUserSetupComplete();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public AttestedKeyPair generateKeyPair(ComponentName paramComponentName, String paramString, KeyGenParameterSpec paramKeyGenParameterSpec, int paramInt) {
    throwIfParentInstance("generateKeyPair");
    try {
      ParcelableKeyGenParameterSpec parcelableKeyGenParameterSpec = new ParcelableKeyGenParameterSpec();
      this(paramKeyGenParameterSpec);
      KeymasterCertificateChain keymasterCertificateChain = new KeymasterCertificateChain();
      this();
      if (!this.mService.generateKeyPair(paramComponentName, this.mContext.getPackageName(), paramString, parcelableKeyGenParameterSpec, paramInt, keymasterCertificateChain)) {
        Log.e(TAG, "Error generating key via DevicePolicyManagerService.");
        return null;
      } 
      String str = paramKeyGenParameterSpec.getKeystoreAlias();
      KeyPair keyPair = KeyChain.getKeyPair(this.mContext, str);
      paramString = null;
      try {
        X509Certificate[] arrayOfX509Certificate;
        if (AttestationUtils.isChainValid(keymasterCertificateChain))
          arrayOfX509Certificate = AttestationUtils.parseCertificateChain(keymasterCertificateChain); 
        return new AttestedKeyPair(keyPair, (Certificate[])arrayOfX509Certificate);
      } catch (KeyAttestationException keyAttestationException) {
        String str1 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Error parsing attestation chain for alias ");
        stringBuilder.append(str);
        Log.e(str1, stringBuilder.toString(), (Throwable)keyAttestationException);
        this.mService.removeKeyPair(paramComponentName, this.mContext.getPackageName(), str);
        return null;
      } 
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } catch (KeyChainException keyChainException) {
      Log.w(TAG, "Failed to generate key", (Throwable)keyChainException);
      return null;
    } catch (InterruptedException interruptedException) {
      Log.w(TAG, "Interrupted while generating key", interruptedException);
      Thread.currentThread().interrupt();
      return null;
    } catch (ServiceSpecificException serviceSpecificException) {
      Log.w(TAG, String.format("Key Generation failure: %d", new Object[] { Integer.valueOf(serviceSpecificException.errorCode) }));
      if (serviceSpecificException.errorCode != 1)
        throw new RuntimeException(String.format("Unknown error while generating key: %d", new Object[] { Integer.valueOf(serviceSpecificException.errorCode) })); 
      throw new StrongBoxUnavailableException("No StrongBox for key generation.");
    } 
  }
  
  public String[] getAccountTypesWithManagementDisabled() {
    return getAccountTypesWithManagementDisabledAsUser(myUserId(), this.mParentInstance);
  }
  
  public String[] getAccountTypesWithManagementDisabledAsUser(int paramInt) {
    return getAccountTypesWithManagementDisabledAsUser(paramInt, false);
  }
  
  public String[] getAccountTypesWithManagementDisabledAsUser(int paramInt, boolean paramBoolean) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getAccountTypesWithManagementDisabledAsUser(paramInt, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public List<ComponentName> getActiveAdmins() {
    throwIfParentInstance("getActiveAdmins");
    return getActiveAdminsAsUser(myUserId());
  }
  
  public List<ComponentName> getActiveAdminsAsUser(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getActiveAdmins(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public Set<String> getAffiliationIds(ComponentName paramComponentName) {
    throwIfParentInstance("getAffiliationIds");
    try {
      return (Set<String>)new ArraySet(this.mService.getAffiliationIds(paramComponentName));
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Set<String> getAllCrossProfilePackages() {
    throwIfParentInstance("getAllCrossProfilePackages");
    if (this.mService != null)
      try {
        return (Set<String>)new ArraySet(this.mService.getAllCrossProfilePackages());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return Collections.emptySet();
  }
  
  public Set<String> getAlwaysOnVpnLockdownWhitelist(ComponentName paramComponentName) {
    throwIfParentInstance("getAlwaysOnVpnLockdownWhitelist");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    List list = null;
    if (iDevicePolicyManager != null)
      try {
        HashSet<String> hashSet;
        List<String> list1 = iDevicePolicyManager.getAlwaysOnVpnLockdownWhitelist(paramComponentName);
        if (list1 == null) {
          list1 = list;
        } else {
          hashSet = new HashSet<>(list1);
        } 
        return hashSet;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public String getAlwaysOnVpnPackage() {
    throwIfParentInstance("getAlwaysOnVpnPackage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getAlwaysOnVpnPackageForUser(myUserId());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public String getAlwaysOnVpnPackage(ComponentName paramComponentName) {
    throwIfParentInstance("getAlwaysOnVpnPackage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getAlwaysOnVpnPackage(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public Bundle getApplicationRestrictions(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("getApplicationRestrictions");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getApplicationRestrictions(paramComponentName, this.mContext.getPackageName(), paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  @Deprecated
  public String getApplicationRestrictionsManagingPackage(ComponentName paramComponentName) {
    throwIfParentInstance("getApplicationRestrictionsManagingPackage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getApplicationRestrictionsManagingPackage(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public boolean getAutoTimeEnabled(ComponentName paramComponentName) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getAutoTimeEnabled(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  @Deprecated
  public boolean getAutoTimeRequired() {
    throwIfParentInstance("getAutoTimeRequired");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getAutoTimeRequired();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean getAutoTimeZoneEnabled(ComponentName paramComponentName) {
    throwIfParentInstance("getAutoTimeZone");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getAutoTimeZoneEnabled(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public List<UserHandle> getBindDeviceAdminTargetUsers(ComponentName paramComponentName) {
    throwIfParentInstance("getBindDeviceAdminTargetUsers");
    try {
      return this.mService.getBindDeviceAdminTargetUsers(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean getBluetoothContactSharingDisabled(ComponentName paramComponentName) {
    throwIfParentInstance("getBluetoothContactSharingDisabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getBluetoothContactSharingDisabled(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return true;
  }
  
  @SystemApi
  public boolean getBluetoothContactSharingDisabled(UserHandle paramUserHandle) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getBluetoothContactSharingDisabledForUser(paramUserHandle.getIdentifier());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return true;
  }
  
  public boolean getCameraDisabled(ComponentName paramComponentName) {
    return getCameraDisabled(paramComponentName, myUserId());
  }
  
  public boolean getCameraDisabled(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getCameraDisabled(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  @Deprecated
  public String getCertInstallerPackage(ComponentName paramComponentName) throws SecurityException {
    throwIfParentInstance("getCertInstallerPackage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getCertInstallerPackage(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public Set<String> getCrossProfileCalendarPackages() {
    throwIfParentInstance("getCrossProfileCalendarPackages");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        ArraySet arraySet;
        List<String> list = iDevicePolicyManager.getCrossProfileCalendarPackagesForUser(myUserId());
        if (list == null) {
          list = null;
        } else {
          arraySet = new ArraySet(list);
        } 
        return (Set<String>)arraySet;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return Collections.emptySet();
  }
  
  public Set<String> getCrossProfileCalendarPackages(ComponentName paramComponentName) {
    throwIfParentInstance("getCrossProfileCalendarPackages");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        ArraySet arraySet;
        List<String> list = iDevicePolicyManager.getCrossProfileCalendarPackages(paramComponentName);
        if (list == null) {
          list = null;
        } else {
          arraySet = new ArraySet(list);
        } 
        return (Set<String>)arraySet;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return Collections.emptySet();
  }
  
  public boolean getCrossProfileCallerIdDisabled(ComponentName paramComponentName) {
    throwIfParentInstance("getCrossProfileCallerIdDisabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getCrossProfileCallerIdDisabled(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean getCrossProfileCallerIdDisabled(UserHandle paramUserHandle) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getCrossProfileCallerIdDisabledForUser(paramUserHandle.getIdentifier());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean getCrossProfileContactsSearchDisabled(ComponentName paramComponentName) {
    throwIfParentInstance("getCrossProfileContactsSearchDisabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getCrossProfileContactsSearchDisabled(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean getCrossProfileContactsSearchDisabled(UserHandle paramUserHandle) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getCrossProfileContactsSearchDisabledForUser(paramUserHandle.getIdentifier());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public Set<String> getCrossProfilePackages(ComponentName paramComponentName) {
    throwIfParentInstance("getCrossProfilePackages");
    if (this.mService != null)
      try {
        return (Set<String>)new ArraySet(this.mService.getCrossProfilePackages(paramComponentName));
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return Collections.emptySet();
  }
  
  public List<String> getCrossProfileWidgetProviders(ComponentName paramComponentName) {
    throwIfParentInstance("getCrossProfileWidgetProviders");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        List<String> list = iDevicePolicyManager.getCrossProfileWidgetProviders(paramComponentName);
        if (list != null)
          return list; 
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return Collections.emptyList();
  }
  
  public int getCurrentFailedPasswordAttempts() {
    return getCurrentFailedPasswordAttempts(myUserId());
  }
  
  public int getCurrentFailedPasswordAttempts(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getCurrentFailedPasswordAttempts(paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return -1;
  }
  
  public Set<String> getDefaultCrossProfilePackages() {
    throwIfParentInstance("getDefaultCrossProfilePackages");
    if (this.mService != null)
      try {
        return (Set<String>)new ArraySet(this.mService.getDefaultCrossProfilePackages());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return Collections.emptySet();
  }
  
  public List<String> getDelegatePackages(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("getDelegatePackages");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getDelegatePackages(paramComponentName, paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public List<String> getDelegatedScopes(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("getDelegatedScopes");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getDelegatedScopes(paramComponentName, paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  @SystemApi
  public String getDeviceOwner() {
    throwIfParentInstance("getDeviceOwner");
    ComponentName componentName = getDeviceOwnerComponentOnCallingUser();
    if (componentName != null) {
      String str = componentName.getPackageName();
    } else {
      componentName = null;
    } 
    return (String)componentName;
  }
  
  @SystemApi
  public ComponentName getDeviceOwnerComponentOnAnyUser() {
    return getDeviceOwnerComponentInner(false);
  }
  
  public ComponentName getDeviceOwnerComponentOnCallingUser() {
    return getDeviceOwnerComponentInner(true);
  }
  
  public CharSequence getDeviceOwnerLockScreenInfo() {
    throwIfParentInstance("getDeviceOwnerLockScreenInfo");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getDeviceOwnerLockScreenInfo();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  @SystemApi
  public String getDeviceOwnerNameOnAnyUser() {
    throwIfParentInstance("getDeviceOwnerNameOnAnyUser");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getDeviceOwnerName();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  @SystemApi
  public CharSequence getDeviceOwnerOrganizationName() {
    try {
      return this.mService.getDeviceOwnerOrganizationName();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public UserHandle getDeviceOwnerUser() {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        int i = iDevicePolicyManager.getDeviceOwnerUserId();
        if (i != -10000)
          return UserHandle.of(i); 
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public int getDeviceOwnerUserId() {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getDeviceOwnerUserId();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return -10000;
  }
  
  public Set<String> getDisallowedSystemApps(ComponentName paramComponentName, int paramInt, String paramString) {
    try {
      return (Set<String>)new ArraySet(this.mService.getDisallowedSystemApps(paramComponentName, paramInt, paramString));
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean getDoNotAskCredentialsOnBoot() {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getDoNotAskCredentialsOnBoot();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public CharSequence getEndUserSessionMessage(ComponentName paramComponentName) {
    throwIfParentInstance("getEndUserSessionMessage");
    try {
      return this.mService.getEndUserSessionMessage(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public FactoryResetProtectionPolicy getFactoryResetProtectionPolicy(ComponentName paramComponentName) {
    throwIfParentInstance("getFactoryResetProtectionPolicy");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getFactoryResetProtectionPolicy(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public boolean getForceEphemeralUsers(ComponentName paramComponentName) {
    throwIfParentInstance("getForceEphemeralUsers");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getForceEphemeralUsers(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public String getGlobalPrivateDnsHost(ComponentName paramComponentName) {
    throwIfParentInstance("setGlobalPrivateDns");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager == null)
      return null; 
    try {
      return iDevicePolicyManager.getGlobalPrivateDnsHost(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getGlobalPrivateDnsMode(ComponentName paramComponentName) {
    throwIfParentInstance("setGlobalPrivateDns");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager == null)
      return 0; 
    try {
      return iDevicePolicyManager.getGlobalPrivateDnsMode(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ComponentName getGlobalProxyAdmin() {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getGlobalProxyAdmin(myUserId());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public boolean getGuestUserDisabled(ComponentName paramComponentName) {
    return false;
  }
  
  public List<byte[]> getInstalledCaCerts(ComponentName paramComponentName) {
    ArrayList<byte[]> arrayList = new ArrayList();
    throwIfParentInstance("getInstalledCaCerts");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.enforceCanManageCaCerts(paramComponentName, this.mContext.getPackageName());
        TrustedCertificateStore trustedCertificateStore = new TrustedCertificateStore();
        this();
        for (String str : trustedCertificateStore.userAliases()) {
          try {
            arrayList.add(trustedCertificateStore.getCertificate(str).getEncoded());
          } catch (CertificateException certificateException) {
            String str1 = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("Could not encode certificate: ");
            stringBuilder.append(str);
            Log.w(str1, stringBuilder.toString(), certificateException);
          } 
        } 
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return (List<byte[]>)arrayList;
  }
  
  public List<String> getKeepUninstalledPackages(ComponentName paramComponentName) {
    throwIfParentInstance("getKeepUninstalledPackages");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getKeepUninstalledPackages(paramComponentName, this.mContext.getPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public int getKeyguardDisabledFeatures(ComponentName paramComponentName) {
    return getKeyguardDisabledFeatures(paramComponentName, myUserId());
  }
  
  public int getKeyguardDisabledFeatures(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getKeyguardDisabledFeatures(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public long getLastBugReportRequestTime() {
    try {
      return this.mService.getLastBugReportRequestTime();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public long getLastNetworkLogRetrievalTime() {
    try {
      return this.mService.getLastNetworkLogRetrievalTime();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public long getLastSecurityLogRetrievalTime() {
    try {
      return this.mService.getLastSecurityLogRetrievalTime();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getLockTaskFeatures(ComponentName paramComponentName) {
    throwIfParentInstance("getLockTaskFeatures");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getLockTaskFeatures(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public String[] getLockTaskPackages(ComponentName paramComponentName) {
    throwIfParentInstance("getLockTaskPackages");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getLockTaskPackages(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return new String[0];
  }
  
  public CharSequence getLongSupportMessage(ComponentName paramComponentName) {
    throwIfParentInstance("getLongSupportMessage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getLongSupportMessage(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public CharSequence getLongSupportMessageForUser(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getLongSupportMessageForUser(paramComponentName, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public long getManagedProfileMaximumTimeOff(ComponentName paramComponentName) {
    throwIfParentInstance("getManagedProfileMaximumTimeOff");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getManagedProfileMaximumTimeOff(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0L;
  }
  
  public int getMaximumFailedPasswordsForWipe(ComponentName paramComponentName) {
    return getMaximumFailedPasswordsForWipe(paramComponentName, myUserId());
  }
  
  public int getMaximumFailedPasswordsForWipe(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getMaximumFailedPasswordsForWipe(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public long getMaximumTimeToLock(ComponentName paramComponentName) {
    return getMaximumTimeToLock(paramComponentName, myUserId());
  }
  
  public long getMaximumTimeToLock(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getMaximumTimeToLock(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0L;
  }
  
  public List<String> getMeteredDataDisabledPackages(ComponentName paramComponentName) {
    throwIfParentInstance("getMeteredDataDisabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getMeteredDataDisabledPackages(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return new ArrayList<>();
  }
  
  public int getOrganizationColor(ComponentName paramComponentName) {
    throwIfParentInstance("getOrganizationColor");
    try {
      return this.mService.getOrganizationColor(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getOrganizationColorForUser(int paramInt) {
    try {
      return this.mService.getOrganizationColorForUser(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public CharSequence getOrganizationName(ComponentName paramComponentName) {
    throwIfParentInstance("getOrganizationName");
    try {
      return this.mService.getOrganizationName(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public CharSequence getOrganizationNameForUser(int paramInt) {
    try {
      return this.mService.getOrganizationNameForUser(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ApnSetting> getOverrideApns(ComponentName paramComponentName) {
    throwIfParentInstance("getOverrideApns");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getOverrideApns(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return Collections.emptyList();
  }
  
  public List<String> getOwnerInstalledCaCerts(UserHandle paramUserHandle) {
    try {
      return this.mService.getOwnerInstalledCaCerts(paramUserHandle).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public DevicePolicyManager getParentProfileInstance(ComponentName paramComponentName) {
    throwIfParentInstance("getParentProfileInstance");
    try {
      if (this.mService.isManagedProfile(paramComponentName))
        return new DevicePolicyManager(this.mContext, this.mService, true); 
      SecurityException securityException = new SecurityException();
      this("The current user does not have a parent profile.");
      throw securityException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public DevicePolicyManager getParentProfileInstance(UserInfo paramUserInfo) {
    this.mContext.checkSelfPermission("android.permission.MANAGE_PROFILE_AND_DEVICE_OWNERS");
    if (paramUserInfo.isManagedProfile())
      return new DevicePolicyManager(this.mContext, this.mService, true); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("The user ");
    stringBuilder.append(paramUserInfo.id);
    stringBuilder.append(" does not have a parent profile.");
    throw new SecurityException(stringBuilder.toString());
  }
  
  public int getPasswordComplexity() {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager == null)
      return 0; 
    try {
      return iDevicePolicyManager.getPasswordComplexity(this.mParentInstance);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public long getPasswordExpiration(ComponentName paramComponentName) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordExpiration(paramComponentName, myUserId(), this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0L;
  }
  
  public long getPasswordExpirationTimeout(ComponentName paramComponentName) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordExpirationTimeout(paramComponentName, myUserId(), this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0L;
  }
  
  public int getPasswordHistoryLength(ComponentName paramComponentName) {
    return getPasswordHistoryLength(paramComponentName, myUserId());
  }
  
  public int getPasswordHistoryLength(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordHistoryLength(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public int getPasswordMaximumLength(int paramInt) {
    return !this.mContext.getPackageManager().hasSystemFeature("android.software.secure_lock_screen") ? 0 : 16;
  }
  
  public int getPasswordMinimumLength(ComponentName paramComponentName) {
    return getPasswordMinimumLength(paramComponentName, myUserId());
  }
  
  public int getPasswordMinimumLength(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordMinimumLength(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public int getPasswordMinimumLetters(ComponentName paramComponentName) {
    return getPasswordMinimumLetters(paramComponentName, myUserId());
  }
  
  public int getPasswordMinimumLetters(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordMinimumLetters(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public int getPasswordMinimumLowerCase(ComponentName paramComponentName) {
    return getPasswordMinimumLowerCase(paramComponentName, myUserId());
  }
  
  public int getPasswordMinimumLowerCase(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordMinimumLowerCase(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public PasswordMetrics getPasswordMinimumMetrics(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordMinimumMetrics(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public int getPasswordMinimumNonLetter(ComponentName paramComponentName) {
    return getPasswordMinimumNonLetter(paramComponentName, myUserId());
  }
  
  public int getPasswordMinimumNonLetter(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordMinimumNonLetter(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public int getPasswordMinimumNumeric(ComponentName paramComponentName) {
    return getPasswordMinimumNumeric(paramComponentName, myUserId());
  }
  
  public int getPasswordMinimumNumeric(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordMinimumNumeric(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public int getPasswordMinimumSymbols(ComponentName paramComponentName) {
    return getPasswordMinimumSymbols(paramComponentName, myUserId());
  }
  
  public int getPasswordMinimumSymbols(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordMinimumSymbols(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public int getPasswordMinimumUpperCase(ComponentName paramComponentName) {
    return getPasswordMinimumUpperCase(paramComponentName, myUserId());
  }
  
  public int getPasswordMinimumUpperCase(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordMinimumUpperCase(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public int getPasswordQuality(ComponentName paramComponentName) {
    return getPasswordQuality(paramComponentName, myUserId());
  }
  
  public int getPasswordQuality(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPasswordQuality(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public SystemUpdateInfo getPendingSystemUpdate(ComponentName paramComponentName) {
    throwIfParentInstance("getPendingSystemUpdate");
    try {
      return this.mService.getPendingSystemUpdate(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getPermissionGrantState(ComponentName paramComponentName, String paramString1, String paramString2) {
    throwIfParentInstance("getPermissionGrantState");
    try {
      return this.mService.getPermissionGrantState(paramComponentName, this.mContext.getPackageName(), paramString1, paramString2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getPermissionPolicy(ComponentName paramComponentName) {
    throwIfParentInstance("getPermissionPolicy");
    try {
      return this.mService.getPermissionPolicy(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public List<String> getPermittedAccessibilityServices(int paramInt) {
    throwIfParentInstance("getPermittedAccessibilityServices");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPermittedAccessibilityServicesForUser(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public List<String> getPermittedAccessibilityServices(ComponentName paramComponentName) {
    throwIfParentInstance("getPermittedAccessibilityServices");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPermittedAccessibilityServices(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public List<String> getPermittedCrossProfileNotificationListeners(ComponentName paramComponentName) {
    throwIfParentInstance("getPermittedCrossProfileNotificationListeners");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPermittedCrossProfileNotificationListeners(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public List<String> getPermittedInputMethods(ComponentName paramComponentName) {
    throwIfParentInstance("getPermittedInputMethods");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPermittedInputMethods(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  @SystemApi
  public List<String> getPermittedInputMethodsForCurrentUser() {
    throwIfParentInstance("getPermittedInputMethodsForCurrentUser");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPermittedInputMethodsForCurrentUser();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public int getPersonalAppsSuspendedReasons(ComponentName paramComponentName) {
    throwIfParentInstance("getPersonalAppsSuspendedReasons");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getPersonalAppsSuspendedReasons(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  @SystemApi
  public ComponentName getProfileOwner() throws IllegalArgumentException {
    throwIfParentInstance("getProfileOwner");
    return getProfileOwnerAsUser(this.mContext.getUserId());
  }
  
  public ComponentName getProfileOwnerAsUser(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getProfileOwnerAsUser(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public ComponentName getProfileOwnerAsUser(UserHandle paramUserHandle) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getProfileOwnerAsUser(paramUserHandle.getIdentifier());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public String getProfileOwnerName() throws IllegalArgumentException {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getProfileOwnerName(this.mContext.getUserId());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  @SystemApi
  public String getProfileOwnerNameAsUser(int paramInt) throws IllegalArgumentException {
    throwIfParentInstance("getProfileOwnerNameAsUser");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getProfileOwnerName(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public ComponentName getProfileOwnerOrDeviceOwnerSupervisionComponent(UserHandle paramUserHandle) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getProfileOwnerOrDeviceOwnerSupervisionComponent(paramUserHandle);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public int getProfileWithMinimumFailedPasswordsForWipe(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getProfileWithMinimumFailedPasswordsForWipe(paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return -10000;
  }
  
  public void getRemoveWarning(ComponentName paramComponentName, RemoteCallback paramRemoteCallback) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.getRemoveWarning(paramComponentName, paramRemoteCallback, myUserId());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public long getRequiredStrongAuthTimeout(ComponentName paramComponentName) {
    return getRequiredStrongAuthTimeout(paramComponentName, myUserId());
  }
  
  public long getRequiredStrongAuthTimeout(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getRequiredStrongAuthTimeout(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 259200000L;
  }
  
  public boolean getScreenCaptureDisabled(ComponentName paramComponentName) {
    return getScreenCaptureDisabled(paramComponentName, myUserId());
  }
  
  public boolean getScreenCaptureDisabled(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getScreenCaptureDisabled(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public List<UserHandle> getSecondaryUsers(ComponentName paramComponentName) {
    throwIfParentInstance("getSecondaryUsers");
    try {
      return this.mService.getSecondaryUsers(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public CharSequence getShortSupportMessage(ComponentName paramComponentName) {
    throwIfParentInstance("getShortSupportMessage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getShortSupportMessage(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public CharSequence getShortSupportMessageForUser(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getShortSupportMessageForUser(paramComponentName, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public CharSequence getStartUserSessionMessage(ComponentName paramComponentName) {
    throwIfParentInstance("getStartUserSessionMessage");
    try {
      return this.mService.getStartUserSessionMessage(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public boolean getStorageEncryption(ComponentName paramComponentName) {
    throwIfParentInstance("getStorageEncryption");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getStorageEncryption(paramComponentName, myUserId());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public int getStorageEncryptionStatus() {
    throwIfParentInstance("getStorageEncryptionStatus");
    return getStorageEncryptionStatus(myUserId());
  }
  
  public int getStorageEncryptionStatus(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getStorageEncryptionStatus(this.mContext.getPackageName(), paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public SystemUpdatePolicy getSystemUpdatePolicy() {
    throwIfParentInstance("getSystemUpdatePolicy");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getSystemUpdatePolicy();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public PersistableBundle getTransferOwnershipBundle() {
    throwIfParentInstance("getTransferOwnershipBundle");
    try {
      return this.mService.getTransferOwnershipBundle();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<PersistableBundle> getTrustAgentConfiguration(ComponentName paramComponentName1, ComponentName paramComponentName2) {
    return getTrustAgentConfiguration(paramComponentName1, paramComponentName2, myUserId());
  }
  
  public List<PersistableBundle> getTrustAgentConfiguration(ComponentName paramComponentName1, ComponentName paramComponentName2, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getTrustAgentConfiguration(paramComponentName1, paramComponentName2, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return new ArrayList<>();
  }
  
  public List<String> getUserControlDisabledPackages(ComponentName paramComponentName) {
    throwIfParentInstance("getUserControlDisabledPackages");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getUserControlDisabledPackages(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return Collections.emptyList();
  }
  
  @SystemApi
  public int getUserProvisioningState() {
    throwIfParentInstance("getUserProvisioningState");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.getUserProvisioningState();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public Bundle getUserRestrictions(ComponentName paramComponentName) {
    Bundle bundle1;
    Bundle bundle2 = null;
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        bundle2 = iDevicePolicyManager.getUserRestrictions(paramComponentName, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    if (bundle2 == null) {
      bundle1 = new Bundle();
    } else {
      bundle1 = bundle2;
    } 
    return bundle1;
  }
  
  public String getWifiMacAddress(ComponentName paramComponentName) {
    throwIfParentInstance("getWifiMacAddress");
    try {
      return this.mService.getWifiMacAddress(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean grantKeyPairToApp(ComponentName paramComponentName, String paramString1, String paramString2) {
    throwIfParentInstance("grantKeyPairToApp");
    try {
      return this.mService.setKeyGrantForApp(paramComponentName, this.mContext.getPackageName(), paramString1, paramString2, true);
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
      return false;
    } 
  }
  
  public boolean hasCaCertInstalled(ComponentName paramComponentName, byte[] paramArrayOfbyte) {
    throwIfParentInstance("hasCaCertInstalled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    boolean bool = false;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.enforceCanManageCaCerts(paramComponentName, this.mContext.getPackageName());
        String str = getCaCertAlias(paramArrayOfbyte);
        if (str != null)
          bool = true; 
        return bool;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } catch (CertificateException certificateException) {
        Log.w(TAG, "Could not parse certificate", certificateException);
      }  
    return false;
  }
  
  public boolean hasDeviceIdentifierAccess(String paramString, int paramInt1, int paramInt2) {
    throwIfParentInstance("hasDeviceIdentifierAccess");
    if (paramString == null)
      return false; 
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.checkDeviceIdentifierAccess(paramString, paramInt1, paramInt2);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean hasGrantedPolicy(ComponentName paramComponentName, int paramInt) {
    throwIfParentInstance("hasGrantedPolicy");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.hasGrantedPolicy(paramComponentName, paramInt, myUserId());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean hasLockdownAdminConfiguredNetworks(ComponentName paramComponentName) {
    throwIfParentInstance("hasLockdownAdminConfiguredNetworks");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.hasLockdownAdminConfiguredNetworks(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean hasUserSetupCompleted() {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.hasUserSetupCompleted();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return true;
  }
  
  public boolean installCaCert(ComponentName paramComponentName, byte[] paramArrayOfbyte) {
    throwIfParentInstance("installCaCert");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.installCaCert(paramComponentName, this.mContext.getPackageName(), paramArrayOfbyte);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean installExistingPackage(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("installExistingPackage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.installExistingPackage(paramComponentName, this.mContext.getPackageName(), paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean installKeyPair(ComponentName paramComponentName, PrivateKey paramPrivateKey, Certificate paramCertificate, String paramString) {
    return installKeyPair(paramComponentName, paramPrivateKey, new Certificate[] { paramCertificate }, paramString, false);
  }
  
  public boolean installKeyPair(ComponentName paramComponentName, PrivateKey paramPrivateKey, Certificate[] paramArrayOfCertificate, String paramString, int paramInt) {
    boolean bool;
    boolean bool1;
    throwIfParentInstance("installKeyPair");
    if ((paramInt & 0x1) == 1) {
      bool = true;
    } else {
      bool = false;
    } 
    if ((paramInt & 0x2) == 2) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    try {
      byte[] arrayOfByte1 = Credentials.convertToPem(new Certificate[] { paramArrayOfCertificate[0] });
      byte[] arrayOfByte2 = null;
      if (paramArrayOfCertificate.length > 1)
        arrayOfByte2 = Credentials.convertToPem(Arrays.<Certificate>copyOfRange(paramArrayOfCertificate, 1, paramArrayOfCertificate.length)); 
      KeyFactory keyFactory = KeyFactory.getInstance(paramPrivateKey.getAlgorithm());
      try {
        byte[] arrayOfByte = ((PKCS8EncodedKeySpec)keyFactory.<PKCS8EncodedKeySpec>getKeySpec(paramPrivateKey, PKCS8EncodedKeySpec.class)).getEncoded();
        return this.mService.installKeyPair(paramComponentName, this.mContext.getPackageName(), arrayOfByte, arrayOfByte1, arrayOfByte2, paramString, bool, bool1);
      } catch (RemoteException remoteException) {
      
      } catch (NoSuchAlgorithmException|java.security.spec.InvalidKeySpecException noSuchAlgorithmException) {
      
      } catch (CertificateException|IOException null) {}
    } catch (RemoteException remoteException) {
    
    } catch (NoSuchAlgorithmException|java.security.spec.InvalidKeySpecException noSuchAlgorithmException) {
      Log.w(TAG, "Failed to obtain private key material", noSuchAlgorithmException);
      return false;
    } catch (CertificateException|IOException certificateException) {
      Log.w(TAG, "Could not pem-encode certificate", certificateException);
      return false;
    } 
    throw certificateException.rethrowFromSystemServer();
  }
  
  public boolean installKeyPair(ComponentName paramComponentName, PrivateKey paramPrivateKey, Certificate[] paramArrayOfCertificate, String paramString, boolean paramBoolean) {
    int i = 2;
    if (paramBoolean)
      i = 0x2 | 0x1; 
    return installKeyPair(paramComponentName, paramPrivateKey, paramArrayOfCertificate, paramString, i);
  }
  
  public void installSystemUpdate(ComponentName paramComponentName, Uri paramUri, Executor paramExecutor, InstallSystemUpdateCallback paramInstallSystemUpdateCallback) {
    throwIfParentInstance("installUpdate");
    if (this.mService == null)
      return; 
    try {
      ParcelFileDescriptor parcelFileDescriptor = this.mContext.getContentResolver().openFileDescriptor(paramUri, "r");
      try {
        IDevicePolicyManager iDevicePolicyManager = this.mService;
        StartInstallingUpdateCallback.Stub stub = new StartInstallingUpdateCallback.Stub() {
            public void onStartInstallingUpdateError(int param1Int, String param1String) {
              DevicePolicyManager.this.executeCallback(param1Int, param1String, executor, callback);
            }
          };
        super(this, paramExecutor, paramInstallSystemUpdateCallback);
        iDevicePolicyManager.installUpdateFromFile(paramComponentName, parcelFileDescriptor, stub);
      } finally {
        if (parcelFileDescriptor != null)
          try {
            parcelFileDescriptor.close();
          } finally {
            parcelFileDescriptor = null;
          }  
      } 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } catch (FileNotFoundException fileNotFoundException) {
      Log.w(TAG, fileNotFoundException);
      executeCallback(4, Log.getStackTraceString(fileNotFoundException), paramExecutor, paramInstallSystemUpdateCallback);
      return;
    } catch (IOException iOException) {
      Log.w(TAG, iOException);
      executeCallback(1, Log.getStackTraceString(iOException), paramExecutor, paramInstallSystemUpdateCallback);
      return;
    } 
  }
  
  public boolean isAccessibilityServicePermittedByAdmin(ComponentName paramComponentName, String paramString, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isAccessibilityServicePermittedByAdmin(paramComponentName, paramString, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isActivePasswordSufficient() {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isActivePasswordSufficient(myUserId(), this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isAdminActive(ComponentName paramComponentName) {
    throwIfParentInstance("isAdminActive");
    return isAdminActiveAsUser(paramComponentName, myUserId());
  }
  
  public boolean isAdminActiveAsUser(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isAdminActive(paramComponentName, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isAffiliatedUser() {
    throwIfParentInstance("isAffiliatedUser");
    try {
      return this.mService.isAffiliatedUser();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isAlwaysOnVpnLockdownEnabled() {
    throwIfParentInstance("isAlwaysOnVpnLockdownEnabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isAlwaysOnVpnLockdownEnabledForUser(myUserId());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isAlwaysOnVpnLockdownEnabled(ComponentName paramComponentName) {
    throwIfParentInstance("isAlwaysOnVpnLockdownEnabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isAlwaysOnVpnLockdownEnabled(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isApplicationHidden(ComponentName paramComponentName, String paramString) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isApplicationHidden(paramComponentName, this.mContext.getPackageName(), paramString, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isBackupServiceEnabled(ComponentName paramComponentName) {
    throwIfParentInstance("isBackupServiceEnabled");
    try {
      return this.mService.isBackupServiceEnabled(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isCaCertApproved(String paramString, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isCaCertApproved(paramString, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  @Deprecated
  public boolean isCallerApplicationRestrictionsManagingPackage() {
    throwIfParentInstance("isCallerApplicationRestrictionsManagingPackage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isCallerApplicationRestrictionsManagingPackage(this.mContext.getPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isCommonCriteriaModeEnabled(ComponentName paramComponentName) {
    throwIfParentInstance("isCommonCriteriaModeEnabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isCommonCriteriaModeEnabled(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isCurrentInputMethodSetByOwner() {
    try {
      return this.mService.isCurrentInputMethodSetByOwner();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isDeviceIdAttestationSupported() {
    return this.mContext.getPackageManager().hasSystemFeature("android.software.device_id_attestation");
  }
  
  @SystemApi
  public boolean isDeviceManaged() {
    try {
      return this.mService.hasDeviceOwner();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isDeviceOwnerApp(String paramString) {
    throwIfParentInstance("isDeviceOwnerApp");
    return isDeviceOwnerAppOnCallingUser(paramString);
  }
  
  public boolean isDeviceOwnerAppOnAnyUser(String paramString) {
    return isDeviceOwnerAppOnAnyUserInner(paramString, false);
  }
  
  public boolean isDeviceOwnerAppOnCallingUser(String paramString) {
    return isDeviceOwnerAppOnAnyUserInner(paramString, true);
  }
  
  @SystemApi
  public boolean isDeviceProvisioned() {
    try {
      return this.mService.isDeviceProvisioned();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public boolean isDeviceProvisioningConfigApplied() {
    try {
      return this.mService.isDeviceProvisioningConfigApplied();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isEphemeralUser(ComponentName paramComponentName) {
    throwIfParentInstance("isEphemeralUser");
    try {
      return this.mService.isEphemeralUser(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isFactoryResetProtectionPolicySupported() {
    try {
      return this.mService.isFactoryResetProtectionPolicySupported();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isInputMethodPermittedByAdmin(ComponentName paramComponentName, String paramString, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isInputMethodPermittedByAdmin(paramComponentName, paramString, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isLockTaskPermitted(String paramString) {
    throwIfParentInstance("isLockTaskPermitted");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isLockTaskPermitted(paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isLogoutEnabled() {
    throwIfParentInstance("isLogoutEnabled");
    try {
      return this.mService.isLogoutEnabled();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public boolean isManagedKiosk() {
    throwIfParentInstance("isManagedKiosk");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isManagedKiosk();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isManagedProfile(ComponentName paramComponentName) {
    throwIfParentInstance("isManagedProfile");
    try {
      return this.mService.isManagedProfile(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isMasterVolumeMuted(ComponentName paramComponentName) {
    throwIfParentInstance("isMasterVolumeMuted");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isMasterVolumeMuted(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isMeteredDataDisabledPackageForUser(ComponentName paramComponentName, String paramString, int paramInt) {
    throwIfParentInstance("getMeteredDataDisabledForUser");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isMeteredDataDisabledPackageForUser(paramComponentName, paramString, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isNetworkLoggingEnabled(ComponentName paramComponentName) {
    throwIfParentInstance("isNetworkLoggingEnabled");
    try {
      return this.mService.isNetworkLoggingEnabled(paramComponentName, this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isNotificationListenerServicePermitted(String paramString, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isNotificationListenerServicePermitted(paramString, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return true;
  }
  
  public boolean isOrganizationOwnedDeviceWithManagedProfile() {
    throwIfParentInstance("isOrganizationOwnedDeviceWithManagedProfile");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isOverrideApnEnabled(ComponentName paramComponentName) {
    throwIfParentInstance("isOverrideApnEnabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isOverrideApnEnabled(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isPackageAllowedToAccessCalendar(String paramString) {
    throwIfParentInstance("isPackageAllowedToAccessCalendar");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isPackageAllowedToAccessCalendarForUser(paramString, myUserId());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isPackageSuspended(ComponentName paramComponentName, String paramString) throws PackageManager.NameNotFoundException {
    throwIfParentInstance("isPackageSuspended");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isPackageSuspended(paramComponentName, this.mContext.getPackageName(), paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } catch (IllegalArgumentException illegalArgumentException) {
        throw new PackageManager.NameNotFoundException(paramString);
      }  
    return false;
  }
  
  public boolean isPasswordSufficientAfterProfileUnification(int paramInt1, int paramInt2) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isPasswordSufficientAfterProfileUnification(paramInt1, paramInt2);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isProfileActivePasswordSufficientForParent(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isProfileActivePasswordSufficientForParent(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isProfileOwnerApp(String paramString) {
    throwIfParentInstance("isProfileOwnerApp");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    boolean bool = false;
    if (iDevicePolicyManager != null)
      try {
        ComponentName componentName = iDevicePolicyManager.getProfileOwner(myUserId());
        if (componentName != null) {
          boolean bool1 = componentName.getPackageName().equals(paramString);
          if (bool1)
            bool = true; 
        } 
        return bool;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isProvisioningAllowed(String paramString) {
    throwIfParentInstance("isProvisioningAllowed");
    try {
      return this.mService.isProvisioningAllowed(paramString, this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isRemovingAdmin(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isRemovingAdmin(paramComponentName, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isResetPasswordTokenActive(ComponentName paramComponentName) {
    throwIfParentInstance("isResetPasswordTokenActive");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isResetPasswordTokenActive(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  @SystemApi
  public boolean isSecondaryLockscreenEnabled(UserHandle paramUserHandle) {
    throwIfParentInstance("isSecondaryLockscreenEnabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isSecondaryLockscreenEnabled(paramUserHandle);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isSecurityLoggingEnabled(ComponentName paramComponentName) {
    throwIfParentInstance("isSecurityLoggingEnabled");
    try {
      return this.mService.isSecurityLoggingEnabled(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isSeparateProfileChallengeAllowed(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isSeparateProfileChallengeAllowed(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isSystemOnlyUser(ComponentName paramComponentName) {
    try {
      return this.mService.isSystemOnlyUser(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public boolean isUnattendedManagedKiosk() {
    throwIfParentInstance("isUnattendedManagedKiosk");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isUnattendedManagedKiosk();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isUninstallBlocked(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("isUninstallBlocked");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isUninstallBlocked(paramComponentName, paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isUninstallInQueue(String paramString) {
    try {
      return this.mService.isUninstallInQueue(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isUniqueDeviceAttestationSupported() {
    return this.mContext.getPackageManager().hasSystemFeature("android.hardware.device_unique_attestation");
  }
  
  public boolean isUsingUnifiedPassword(ComponentName paramComponentName) {
    throwIfParentInstance("isUsingUnifiedPassword");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.isUsingUnifiedPassword(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return true;
  }
  
  public void lockNow() {
    lockNow(0);
  }
  
  public void lockNow(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.lockNow(paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public int logoutUser(ComponentName paramComponentName) {
    throwIfParentInstance("logoutUser");
    try {
      return this.mService.logoutUser(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void markProfileOwnerOnOrganizationOwnedDevice(ComponentName paramComponentName) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager == null)
      return; 
    try {
      iDevicePolicyManager.markProfileOwnerOnOrganizationOwnedDevice(paramComponentName, myUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  protected int myUserId() {
    return this.mContext.getUserId();
  }
  
  @SystemApi
  public void notifyPendingSystemUpdate(long paramLong) {
    throwIfParentInstance("notifyPendingSystemUpdate");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.notifyPendingSystemUpdate(SystemUpdateInfo.of(paramLong));
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @SystemApi
  public void notifyPendingSystemUpdate(long paramLong, boolean paramBoolean) {
    throwIfParentInstance("notifyPendingSystemUpdate");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.notifyPendingSystemUpdate(SystemUpdateInfo.of(paramLong, paramBoolean));
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @SystemApi
  public boolean packageHasActiveAdmins(String paramString) {
    return packageHasActiveAdmins(paramString, myUserId());
  }
  
  public boolean packageHasActiveAdmins(String paramString, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.packageHasActiveAdmins(paramString, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public void reboot(ComponentName paramComponentName) {
    throwIfParentInstance("reboot");
    try {
      this.mService.reboot(paramComponentName);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeActiveAdmin(ComponentName paramComponentName) {
    throwIfParentInstance("removeActiveAdmin");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.removeActiveAdmin(paramComponentName, myUserId());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean removeCrossProfileWidgetProvider(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("removeCrossProfileWidgetProvider");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.removeCrossProfileWidgetProvider(paramComponentName, paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean removeKeyPair(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("removeKeyPair");
    try {
      return this.mService.removeKeyPair(paramComponentName, this.mContext.getPackageName(), paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean removeOverrideApn(ComponentName paramComponentName, int paramInt) {
    throwIfParentInstance("removeOverrideApn");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.removeOverrideApn(paramComponentName, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean removeUser(ComponentName paramComponentName, UserHandle paramUserHandle) {
    throwIfParentInstance("removeUser");
    try {
      return this.mService.removeUser(paramComponentName, paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void reportFailedBiometricAttempt(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.reportFailedBiometricAttempt(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void reportFailedPasswordAttempt(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.reportFailedPasswordAttempt(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void reportKeyguardDismissed(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.reportKeyguardDismissed(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void reportKeyguardSecured(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.reportKeyguardSecured(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void reportPasswordChanged(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.reportPasswordChanged(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void reportSuccessfulBiometricAttempt(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.reportSuccessfulBiometricAttempt(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void reportSuccessfulPasswordAttempt(int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.reportSuccessfulPasswordAttempt(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean requestBugreport(ComponentName paramComponentName) {
    throwIfParentInstance("requestBugreport");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.requestBugreport(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  @Deprecated
  public boolean resetPassword(String paramString, int paramInt) {
    throwIfParentInstance("resetPassword");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.resetPassword(paramString, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean resetPasswordWithToken(ComponentName paramComponentName, String paramString, byte[] paramArrayOfbyte, int paramInt) {
    throwIfParentInstance("resetPassword");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.resetPasswordWithToken(paramComponentName, paramString, paramArrayOfbyte, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public List<NetworkEvent> retrieveNetworkLogs(ComponentName paramComponentName, long paramLong) {
    throwIfParentInstance("retrieveNetworkLogs");
    try {
      return this.mService.retrieveNetworkLogs(paramComponentName, this.mContext.getPackageName(), paramLong);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<SecurityLog.SecurityEvent> retrievePreRebootSecurityLogs(ComponentName paramComponentName) {
    throwIfParentInstance("retrievePreRebootSecurityLogs");
    try {
      ParceledListSlice parceledListSlice = this.mService.retrievePreRebootSecurityLogs(paramComponentName);
      return (parceledListSlice != null) ? parceledListSlice.getList() : null;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<SecurityLog.SecurityEvent> retrieveSecurityLogs(ComponentName paramComponentName) {
    throwIfParentInstance("retrieveSecurityLogs");
    try {
      ParceledListSlice parceledListSlice = this.mService.retrieveSecurityLogs(paramComponentName);
      return (parceledListSlice != null) ? parceledListSlice.getList() : null;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean revokeKeyPairFromApp(ComponentName paramComponentName, String paramString1, String paramString2) {
    throwIfParentInstance("revokeKeyPairFromApp");
    try {
      return this.mService.setKeyGrantForApp(paramComponentName, this.mContext.getPackageName(), paramString1, paramString2, false);
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
      return false;
    } 
  }
  
  public void setAccountManagementDisabled(ComponentName paramComponentName, String paramString, boolean paramBoolean) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setAccountManagementDisabled(paramComponentName, paramString, paramBoolean, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setActiveAdmin(ComponentName paramComponentName, boolean paramBoolean) {
    setActiveAdmin(paramComponentName, paramBoolean, myUserId());
  }
  
  public void setActiveAdmin(ComponentName paramComponentName, boolean paramBoolean, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setActiveAdmin(paramComponentName, paramBoolean, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @SystemApi
  @Deprecated
  public boolean setActiveProfileOwner(ComponentName paramComponentName, @Deprecated String paramString) throws IllegalArgumentException {
    throwIfParentInstance("setActiveProfileOwner");
    if (this.mService != null)
      try {
        int i = myUserId();
        this.mService.setActiveAdmin(paramComponentName, false, i);
        return this.mService.setProfileOwner(paramComponentName, paramString, i);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public void setAffiliationIds(ComponentName paramComponentName, Set<String> paramSet) {
    throwIfParentInstance("setAffiliationIds");
    if (paramSet != null)
      try {
        IDevicePolicyManager iDevicePolicyManager = this.mService;
        ArrayList<String> arrayList = new ArrayList();
        this((Collection)paramSet);
        iDevicePolicyManager.setAffiliationIds(paramComponentName, arrayList);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("ids must not be null");
  }
  
  public void setAlwaysOnVpnPackage(ComponentName paramComponentName, String paramString, boolean paramBoolean) throws PackageManager.NameNotFoundException {
    setAlwaysOnVpnPackage(paramComponentName, paramString, paramBoolean, Collections.emptySet());
  }
  
  public void setAlwaysOnVpnPackage(ComponentName paramComponentName, String paramString, boolean paramBoolean, Set<String> paramSet) throws PackageManager.NameNotFoundException {
    ArrayList<String> arrayList;
    throwIfParentInstance("setAlwaysOnVpnPackage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null) {
      if (paramSet == null) {
        paramSet = null;
      } else {
        try {
          arrayList = new ArrayList<>(paramSet);
          iDevicePolicyManager.setAlwaysOnVpnPackage(paramComponentName, paramString, paramBoolean, arrayList);
        } catch (ServiceSpecificException serviceSpecificException) {
          if (serviceSpecificException.errorCode != 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown error setting always-on VPN: ");
            stringBuilder.append(serviceSpecificException.errorCode);
            throw new RuntimeException(stringBuilder.toString(), serviceSpecificException);
          } 
          throw new PackageManager.NameNotFoundException(serviceSpecificException.getMessage());
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
        return;
      } 
    } else {
      return;
    } 
    iDevicePolicyManager.setAlwaysOnVpnPackage((ComponentName)remoteException, (String)serviceSpecificException, paramBoolean, arrayList);
  }
  
  public boolean setApplicationHidden(ComponentName paramComponentName, String paramString, boolean paramBoolean) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.setApplicationHidden(paramComponentName, this.mContext.getPackageName(), paramString, paramBoolean, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public void setApplicationRestrictions(ComponentName paramComponentName, String paramString, Bundle paramBundle) {
    throwIfParentInstance("setApplicationRestrictions");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setApplicationRestrictions(paramComponentName, this.mContext.getPackageName(), paramString, paramBundle);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @Deprecated
  public void setApplicationRestrictionsManagingPackage(ComponentName paramComponentName, String paramString) throws PackageManager.NameNotFoundException {
    throwIfParentInstance("setApplicationRestrictionsManagingPackage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        if (!iDevicePolicyManager.setApplicationRestrictionsManagingPackage(paramComponentName, paramString)) {
          PackageManager.NameNotFoundException nameNotFoundException = new PackageManager.NameNotFoundException();
          this(paramString);
          throw nameNotFoundException;
        } 
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setAutoTimeEnabled(ComponentName paramComponentName, boolean paramBoolean) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setAutoTimeEnabled(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @Deprecated
  public void setAutoTimeRequired(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setAutoTimeRequired");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setAutoTimeRequired(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setAutoTimeZoneEnabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setAutoTimeZone");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setAutoTimeZoneEnabled(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setBackupServiceEnabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setBackupServiceEnabled");
    try {
      this.mService.setBackupServiceEnabled(paramComponentName, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setBluetoothContactSharingDisabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setBluetoothContactSharingDisabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setBluetoothContactSharingDisabled(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setCameraDisabled(ComponentName paramComponentName, boolean paramBoolean) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setCameraDisabled(paramComponentName, paramBoolean, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @Deprecated
  public void setCertInstallerPackage(ComponentName paramComponentName, String paramString) throws SecurityException {
    throwIfParentInstance("setCertInstallerPackage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setCertInstallerPackage(paramComponentName, paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setCommonCriteriaModeEnabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setCommonCriteriaModeEnabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setCommonCriteriaModeEnabled(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setConfiguredNetworksLockdownState(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setConfiguredNetworksLockdownState");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setConfiguredNetworksLockdownState(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setCrossProfileCalendarPackages(ComponentName paramComponentName, Set<String> paramSet) {
    ArrayList<String> arrayList;
    throwIfParentInstance("setCrossProfileCalendarPackages");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null) {
      if (paramSet == null) {
        paramSet = null;
      } else {
        try {
          arrayList = new ArrayList<>(paramSet);
          iDevicePolicyManager.setCrossProfileCalendarPackages(paramComponentName, arrayList);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
        return;
      } 
    } else {
      return;
    } 
    iDevicePolicyManager.setCrossProfileCalendarPackages((ComponentName)remoteException, arrayList);
  }
  
  public void setCrossProfileCallerIdDisabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setCrossProfileCallerIdDisabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setCrossProfileCallerIdDisabled(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setCrossProfileContactsSearchDisabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setCrossProfileContactsSearchDisabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setCrossProfileContactsSearchDisabled(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setCrossProfilePackages(ComponentName paramComponentName, Set<String> paramSet) {
    throwIfParentInstance("setCrossProfilePackages");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        ArrayList<String> arrayList = new ArrayList();
        this((Collection)paramSet);
        iDevicePolicyManager.setCrossProfilePackages(paramComponentName, arrayList);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setDefaultSmsApplication(ComponentName paramComponentName, String paramString) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setDefaultSmsApplication(paramComponentName, paramString, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setDelegatedScopes(ComponentName paramComponentName, String paramString, List<String> paramList) {
    throwIfParentInstance("setDelegatedScopes");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setDelegatedScopes(paramComponentName, paramString, paramList);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean setDeviceOwner(ComponentName paramComponentName) {
    return setDeviceOwner(paramComponentName, (String)null);
  }
  
  public boolean setDeviceOwner(ComponentName paramComponentName, int paramInt) {
    return setDeviceOwner(paramComponentName, null, paramInt);
  }
  
  public boolean setDeviceOwner(ComponentName paramComponentName, String paramString) {
    return setDeviceOwner(paramComponentName, paramString, 0);
  }
  
  public boolean setDeviceOwner(ComponentName paramComponentName, String paramString, int paramInt) throws IllegalArgumentException, IllegalStateException {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.setDeviceOwner(paramComponentName, paramString, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public void setDeviceOwnerLockScreenInfo(ComponentName paramComponentName, CharSequence paramCharSequence) {
    throwIfParentInstance("setDeviceOwnerLockScreenInfo");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setDeviceOwnerLockScreenInfo(paramComponentName, paramCharSequence);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @SystemApi
  public void setDeviceProvisioningConfigApplied() {
    try {
      this.mService.setDeviceProvisioningConfigApplied();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setEndUserSessionMessage(ComponentName paramComponentName, CharSequence paramCharSequence) {
    throwIfParentInstance("setEndUserSessionMessage");
    try {
      this.mService.setEndUserSessionMessage(paramComponentName, paramCharSequence);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setFactoryResetProtectionPolicy(ComponentName paramComponentName, FactoryResetProtectionPolicy paramFactoryResetProtectionPolicy) {
    throwIfParentInstance("setFactoryResetProtectionPolicy");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setFactoryResetProtectionPolicy(paramComponentName, paramFactoryResetProtectionPolicy);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setForceEphemeralUsers(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setForceEphemeralUsers");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setForceEphemeralUsers(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public int setGlobalPrivateDnsModeOpportunistic(ComponentName paramComponentName) {
    throwIfParentInstance("setGlobalPrivateDnsModeOpportunistic");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager == null)
      return 2; 
    try {
      return iDevicePolicyManager.setGlobalPrivateDns(paramComponentName, 2, null);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int setGlobalPrivateDnsModeSpecifiedHost(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("setGlobalPrivateDnsModeSpecifiedHost");
    Objects.requireNonNull(paramString, "dns resolver is null");
    if (this.mService == null)
      return 2; 
    if (NetworkUtils.isWeaklyValidatedHostname(paramString) && !PrivateDnsConnectivityChecker.canConnectToPrivateDnsServer(paramString))
      return 1; 
    try {
      return this.mService.setGlobalPrivateDns(paramComponentName, 3, paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ComponentName setGlobalProxy(ComponentName paramComponentName, Proxy paramProxy, List<String> paramList) {
    throwIfParentInstance("setGlobalProxy");
    if (paramProxy != null) {
      if (this.mService != null)
        try {
          IllegalArgumentException illegalArgumentException;
          String str1;
          String str2;
          if (paramProxy.equals(Proxy.NO_PROXY)) {
            paramList = null;
            paramProxy = null;
          } else {
            if (paramProxy.type().equals(Proxy.Type.HTTP)) {
              InetSocketAddress inetSocketAddress = (InetSocketAddress)paramProxy.address();
              String str3 = inetSocketAddress.getHostName();
              int i = inetSocketAddress.getPort();
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append(str3);
              stringBuilder.append(":");
              stringBuilder.append(Integer.toString(i));
              String str4 = stringBuilder.toString();
              if (paramList == null) {
                str1 = "";
              } else {
                stringBuilder = new StringBuilder();
                this();
                boolean bool = true;
                for (String null : paramList) {
                  if (!bool) {
                    stringBuilder.append(",");
                  } else {
                    bool = false;
                  } 
                  stringBuilder.append(str2.trim());
                } 
                str1 = stringBuilder.toString();
              } 
              if (Proxy.validate(str3, Integer.toString(i), str1) == 0) {
                str2 = str4;
                return this.mService.setGlobalProxy(paramComponentName, str2, str1);
              } 
              IllegalArgumentException illegalArgumentException1 = new IllegalArgumentException();
              this();
              throw illegalArgumentException1;
            } 
            illegalArgumentException = new IllegalArgumentException();
            this();
            throw illegalArgumentException;
          } 
          return this.mService.setGlobalProxy((ComponentName)illegalArgumentException, str2, str1);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return null;
    } 
    throw null;
  }
  
  public void setGlobalSetting(ComponentName paramComponentName, String paramString1, String paramString2) {
    throwIfParentInstance("setGlobalSetting");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setGlobalSetting(paramComponentName, paramString1, paramString2);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setKeepUninstalledPackages(ComponentName paramComponentName, List<String> paramList) {
    throwIfParentInstance("setKeepUninstalledPackages");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setKeepUninstalledPackages(paramComponentName, this.mContext.getPackageName(), paramList);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean setKeyPairCertificate(ComponentName paramComponentName, String paramString, List<Certificate> paramList, boolean paramBoolean) {
    throwIfParentInstance("setKeyPairCertificate");
    try {
      byte[] arrayOfByte1 = Credentials.convertToPem(new Certificate[] { paramList.get(0) });
      byte[] arrayOfByte2 = null;
      if (paramList.size() > 1)
        arrayOfByte2 = Credentials.convertToPem((Certificate[])paramList.subList(1, paramList.size()).toArray((Object[])new Certificate[0])); 
      return this.mService.setKeyPairCertificate(paramComponentName, this.mContext.getPackageName(), paramString, arrayOfByte1, arrayOfByte2, paramBoolean);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } catch (CertificateException|IOException certificateException) {
      Log.w(TAG, "Could not pem-encode certificate", certificateException);
      return false;
    } 
  }
  
  public boolean setKeyguardDisabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setKeyguardDisabled");
    try {
      return this.mService.setKeyguardDisabled(paramComponentName, paramBoolean);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setKeyguardDisabledFeatures(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setKeyguardDisabledFeatures(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setLocationEnabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setLocationEnabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setLocationEnabled(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setLockTaskFeatures(ComponentName paramComponentName, int paramInt) {
    throwIfParentInstance("setLockTaskFeatures");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setLockTaskFeatures(paramComponentName, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setLockTaskPackages(ComponentName paramComponentName, String[] paramArrayOfString) throws SecurityException {
    throwIfParentInstance("setLockTaskPackages");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setLockTaskPackages(paramComponentName, paramArrayOfString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setLogoutEnabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setLogoutEnabled");
    try {
      this.mService.setLogoutEnabled(paramComponentName, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setLongSupportMessage(ComponentName paramComponentName, CharSequence paramCharSequence) {
    throwIfParentInstance("setLongSupportMessage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setLongSupportMessage(paramComponentName, paramCharSequence);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setManagedProfileMaximumTimeOff(ComponentName paramComponentName, long paramLong) {
    throwIfParentInstance("setManagedProfileMaximumTimeOff");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setManagedProfileMaximumTimeOff(paramComponentName, paramLong);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setMasterVolumeMuted(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setMasterVolumeMuted");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setMasterVolumeMuted(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setMaximumFailedPasswordsForWipe(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setMaximumFailedPasswordsForWipe(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setMaximumTimeToLock(ComponentName paramComponentName, long paramLong) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setMaximumTimeToLock(paramComponentName, paramLong, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public List<String> setMeteredDataDisabledPackages(ComponentName paramComponentName, List<String> paramList) {
    throwIfParentInstance("setMeteredDataDisabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.setMeteredDataDisabledPackages(paramComponentName, paramList);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return paramList;
  }
  
  public void setNetworkLoggingEnabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setNetworkLoggingEnabled");
    try {
      this.mService.setNetworkLoggingEnabled(paramComponentName, this.mContext.getPackageName(), paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setOrganizationColor(ComponentName paramComponentName, int paramInt) {
    throwIfParentInstance("setOrganizationColor");
    try {
      this.mService.setOrganizationColor(paramComponentName, paramInt | 0xFF000000);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setOrganizationColorForUser(int paramInt1, int paramInt2) {
    try {
      this.mService.setOrganizationColorForUser(paramInt1 | 0xFF000000, paramInt2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setOrganizationName(ComponentName paramComponentName, CharSequence paramCharSequence) {
    throwIfParentInstance("setOrganizationName");
    try {
      this.mService.setOrganizationName(paramComponentName, paramCharSequence);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setOverrideApnsEnabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setOverrideApnEnabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setOverrideApnsEnabled(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public String[] setPackagesSuspended(ComponentName paramComponentName, String[] paramArrayOfString, boolean paramBoolean) {
    throwIfParentInstance("setPackagesSuspended");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.setPackagesSuspended(paramComponentName, this.mContext.getPackageName(), paramArrayOfString, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return paramArrayOfString;
  }
  
  public void setPasswordExpirationTimeout(ComponentName paramComponentName, long paramLong) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setPasswordExpirationTimeout(paramComponentName, paramLong, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setPasswordHistoryLength(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setPasswordHistoryLength(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setPasswordMinimumLength(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setPasswordMinimumLength(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setPasswordMinimumLetters(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setPasswordMinimumLetters(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setPasswordMinimumLowerCase(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setPasswordMinimumLowerCase(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setPasswordMinimumNonLetter(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setPasswordMinimumNonLetter(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setPasswordMinimumNumeric(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setPasswordMinimumNumeric(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setPasswordMinimumSymbols(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setPasswordMinimumSymbols(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setPasswordMinimumUpperCase(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setPasswordMinimumUpperCase(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setPasswordQuality(ComponentName paramComponentName, int paramInt) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setPasswordQuality(paramComponentName, paramInt, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean setPermissionGrantState(ComponentName paramComponentName, String paramString1, String paramString2, int paramInt) {
    throwIfParentInstance("setPermissionGrantState");
    try {
      CompletableFuture<Boolean> completableFuture = new CompletableFuture();
      this();
      IDevicePolicyManager iDevicePolicyManager = this.mService;
      String str = this.mContext.getPackageName();
      RemoteCallback remoteCallback = new RemoteCallback();
      _$$Lambda$DevicePolicyManager$w2TynM9H41ejac4JVpNbnemNVWk _$$Lambda$DevicePolicyManager$w2TynM9H41ejac4JVpNbnemNVWk = new _$$Lambda$DevicePolicyManager$w2TynM9H41ejac4JVpNbnemNVWk();
      this(completableFuture);
      this(_$$Lambda$DevicePolicyManager$w2TynM9H41ejac4JVpNbnemNVWk);
      iDevicePolicyManager.setPermissionGrantState(paramComponentName, str, paramString1, paramString2, paramInt, remoteCallback);
      BackgroundThread.getHandler().sendMessageDelayed(PooledLambda.obtainMessage((BiConsumer)_$$Lambda$pWaRScwKTZTgGW4Wa_v5R_pKBDU.INSTANCE, completableFuture, Boolean.valueOf(false)), 20000L);
      return ((Boolean)completableFuture.get()).booleanValue();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } catch (InterruptedException|java.util.concurrent.ExecutionException interruptedException) {
      throw new RuntimeException(interruptedException);
    } 
  }
  
  public void setPermissionPolicy(ComponentName paramComponentName, int paramInt) {
    throwIfParentInstance("setPermissionPolicy");
    try {
      this.mService.setPermissionPolicy(paramComponentName, this.mContext.getPackageName(), paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean setPermittedAccessibilityServices(ComponentName paramComponentName, List<String> paramList) {
    throwIfParentInstance("setPermittedAccessibilityServices");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.setPermittedAccessibilityServices(paramComponentName, paramList);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean setPermittedCrossProfileNotificationListeners(ComponentName paramComponentName, List<String> paramList) {
    throwIfParentInstance("setPermittedCrossProfileNotificationListeners");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.setPermittedCrossProfileNotificationListeners(paramComponentName, paramList);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean setPermittedInputMethods(ComponentName paramComponentName, List<String> paramList) {
    throwIfParentInstance("setPermittedInputMethods");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.setPermittedInputMethods(paramComponentName, paramList);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public void setPersonalAppsSuspended(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setPersonalAppsSuspended");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setPersonalAppsSuspended(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setProfileEnabled(ComponentName paramComponentName) {
    throwIfParentInstance("setProfileEnabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setProfileEnabled(paramComponentName);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setProfileName(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("setProfileName");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setProfileName(paramComponentName, paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean setProfileOwner(ComponentName paramComponentName, @Deprecated String paramString, int paramInt) throws IllegalArgumentException {
    if (this.mService != null) {
      String str = paramString;
      if (paramString == null)
        str = ""; 
      try {
        return this.mService.setProfileOwner(paramComponentName, str, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
    return false;
  }
  
  @SystemApi
  @Deprecated
  public void setProfileOwnerCanAccessDeviceIds(ComponentName paramComponentName) {
    if ((this.mContext.getApplicationInfo()).targetSdkVersion <= 29) {
      markProfileOwnerOnOrganizationOwnedDevice(paramComponentName);
      return;
    } 
    throw new UnsupportedOperationException("This method is deprecated. use markProfileOwnerOnOrganizationOwnedDevice instead.");
  }
  
  public void setRecommendedGlobalProxy(ComponentName paramComponentName, ProxyInfo paramProxyInfo) {
    throwIfParentInstance("setRecommendedGlobalProxy");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setRecommendedGlobalProxy(paramComponentName, paramProxyInfo);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setRequiredStrongAuthTimeout(ComponentName paramComponentName, long paramLong) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setRequiredStrongAuthTimeout(paramComponentName, paramLong, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean setResetPasswordToken(ComponentName paramComponentName, byte[] paramArrayOfbyte) {
    throwIfParentInstance("setResetPasswordToken");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.setResetPasswordToken(paramComponentName, paramArrayOfbyte);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public void setRestrictionsProvider(ComponentName paramComponentName1, ComponentName paramComponentName2) {
    throwIfParentInstance("setRestrictionsProvider");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setRestrictionsProvider(paramComponentName1, paramComponentName2);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setScreenCaptureDisabled(ComponentName paramComponentName, boolean paramBoolean) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setScreenCaptureDisabled(paramComponentName, paramBoolean, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @SystemApi
  public void setSecondaryLockscreenEnabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setSecondaryLockscreenEnabled");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setSecondaryLockscreenEnabled(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setSecureSetting(ComponentName paramComponentName, String paramString1, String paramString2) {
    throwIfParentInstance("setSecureSetting");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setSecureSetting(paramComponentName, paramString1, paramString2);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setSecurityLoggingEnabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setSecurityLoggingEnabled");
    try {
      this.mService.setSecurityLoggingEnabled(paramComponentName, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setShortSupportMessage(ComponentName paramComponentName, CharSequence paramCharSequence) {
    throwIfParentInstance("setShortSupportMessage");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setShortSupportMessage(paramComponentName, paramCharSequence);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setStartUserSessionMessage(ComponentName paramComponentName, CharSequence paramCharSequence) {
    throwIfParentInstance("setStartUserSessionMessage");
    try {
      this.mService.setStartUserSessionMessage(paramComponentName, paramCharSequence);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean setStatusBarDisabled(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setStatusBarDisabled");
    try {
      return this.mService.setStatusBarDisabled(paramComponentName, paramBoolean);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public int setStorageEncryption(ComponentName paramComponentName, boolean paramBoolean) {
    throwIfParentInstance("setStorageEncryption");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.setStorageEncryption(paramComponentName, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  public void setSystemSetting(ComponentName paramComponentName, String paramString1, String paramString2) {
    throwIfParentInstance("setSystemSetting");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setSystemSetting(paramComponentName, paramString1, paramString2);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setSystemUpdatePolicy(ComponentName paramComponentName, SystemUpdatePolicy paramSystemUpdatePolicy) {
    throwIfParentInstance("setSystemUpdatePolicy");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setSystemUpdatePolicy(paramComponentName, paramSystemUpdatePolicy);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean setTime(ComponentName paramComponentName, long paramLong) {
    throwIfParentInstance("setTime");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.setTime(paramComponentName, paramLong);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean setTimeZone(ComponentName paramComponentName, String paramString) {
    throwIfParentInstance("setTimeZone");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.setTimeZone(paramComponentName, paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public void setTrustAgentConfiguration(ComponentName paramComponentName1, ComponentName paramComponentName2, PersistableBundle paramPersistableBundle) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setTrustAgentConfiguration(paramComponentName1, paramComponentName2, paramPersistableBundle, this.mParentInstance);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setUninstallBlocked(ComponentName paramComponentName, String paramString, boolean paramBoolean) {
    throwIfParentInstance("setUninstallBlocked");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setUninstallBlocked(paramComponentName, this.mContext.getPackageName(), paramString, paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setUserControlDisabledPackages(ComponentName paramComponentName, List<String> paramList) {
    throwIfParentInstance("setUserControlDisabledPackages");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setUserControlDisabledPackages(paramComponentName, paramList);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setUserIcon(ComponentName paramComponentName, Bitmap paramBitmap) {
    throwIfParentInstance("setUserIcon");
    try {
      this.mService.setUserIcon(paramComponentName, paramBitmap);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setUserProvisioningState(int paramInt1, int paramInt2) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.setUserProvisioningState(paramInt1, paramInt2);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void startManagedQuickContact(String paramString, long paramLong, Intent paramIntent) {
    startManagedQuickContact(paramString, paramLong, false, 0L, paramIntent);
  }
  
  public void startManagedQuickContact(String paramString, long paramLong1, boolean paramBoolean, long paramLong2, Intent paramIntent) {
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        iDevicePolicyManager.startManagedQuickContact(paramString, paramLong1, paramBoolean, paramLong2, paramIntent);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public int startUserInBackground(ComponentName paramComponentName, UserHandle paramUserHandle) {
    throwIfParentInstance("startUserInBackground");
    try {
      return this.mService.startUserInBackground(paramComponentName, paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean startViewCalendarEventInManagedProfile(long paramLong1, long paramLong2, long paramLong3, boolean paramBoolean, int paramInt) {
    throwIfParentInstance("startViewCalendarEventInManagedProfile");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.startViewCalendarEventInManagedProfile(this.mContext.getPackageName(), paramLong1, paramLong2, paramLong3, paramBoolean, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public int stopUser(ComponentName paramComponentName, UserHandle paramUserHandle) {
    throwIfParentInstance("stopUser");
    try {
      return this.mService.stopUser(paramComponentName, paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean switchUser(ComponentName paramComponentName, UserHandle paramUserHandle) {
    throwIfParentInstance("switchUser");
    try {
      return this.mService.switchUser(paramComponentName, paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void transferOwnership(ComponentName paramComponentName1, ComponentName paramComponentName2, PersistableBundle paramPersistableBundle) {
    throwIfParentInstance("transferOwnership");
    try {
      this.mService.transferOwnership(paramComponentName1, paramComponentName2, paramPersistableBundle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void uninstallAllUserCaCerts(ComponentName paramComponentName) {
    throwIfParentInstance("uninstallAllUserCaCerts");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        String str = this.mContext.getPackageName();
        TrustedCertificateStore trustedCertificateStore = new TrustedCertificateStore();
        this();
        iDevicePolicyManager.uninstallCaCerts(paramComponentName, str, (String[])trustedCertificateStore.userAliases().toArray((Object[])new String[0]));
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void uninstallCaCert(ComponentName paramComponentName, byte[] paramArrayOfbyte) {
    throwIfParentInstance("uninstallCaCert");
    if (this.mService != null)
      try {
        String str = getCaCertAlias(paramArrayOfbyte);
        this.mService.uninstallCaCerts(paramComponentName, this.mContext.getPackageName(), new String[] { str });
      } catch (CertificateException certificateException) {
        Log.w(TAG, "Unable to parse certificate", certificateException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void uninstallPackageWithActiveAdmins(String paramString) {
    try {
      this.mService.uninstallPackageWithActiveAdmins(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean updateOverrideApn(ComponentName paramComponentName, int paramInt, ApnSetting paramApnSetting) {
    throwIfParentInstance("updateOverrideApn");
    IDevicePolicyManager iDevicePolicyManager = this.mService;
    if (iDevicePolicyManager != null)
      try {
        return iDevicePolicyManager.updateOverrideApn(paramComponentName, paramInt, paramApnSetting);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public void wipeData(int paramInt) {
    wipeDataInternal(paramInt, "");
  }
  
  public void wipeData(int paramInt, CharSequence paramCharSequence) {
    boolean bool;
    Objects.requireNonNull(paramCharSequence, "reason string is null");
    Preconditions.checkStringNotEmpty(paramCharSequence, "reason string is empty");
    if ((paramInt & 0x8) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "WIPE_SILENTLY cannot be set");
    wipeDataInternal(paramInt, paramCharSequence.toString());
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AttestationIdType {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface CreateAndManageUserFlags {}
  
  public static abstract class InstallSystemUpdateCallback {
    public static final int UPDATE_ERROR_BATTERY_LOW = 5;
    
    public static final int UPDATE_ERROR_FILE_NOT_FOUND = 4;
    
    public static final int UPDATE_ERROR_INCORRECT_OS_VERSION = 2;
    
    public static final int UPDATE_ERROR_UNKNOWN = 1;
    
    public static final int UPDATE_ERROR_UPDATE_FILE_INVALID = 3;
    
    public void onInstallUpdateError(int param1Int, String param1String) {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface InstallUpdateCallbackErrorConstants {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface LockNowFlag {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface LockTaskFeature {}
  
  public static interface OnClearApplicationUserDataListener {
    void onApplicationUserDataCleared(String param1String, boolean param1Boolean);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PasswordComplexity {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PermissionGrantState {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PersonalAppsSuspensionReason {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PrivateDnsMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PrivateDnsModeErrorCodes {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ProvisioningPreCondition {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SystemSettingsWhitelist {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface UserProvisioningState {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DevicePolicyManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
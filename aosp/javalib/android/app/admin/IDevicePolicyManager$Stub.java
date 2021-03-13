package android.app.admin;

import android.app.IApplicationThread;
import android.app.IServiceConnection;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.IPackageDataObserver;
import android.content.pm.ParceledListSlice;
import android.content.pm.StringParceledListSlice;
import android.graphics.Bitmap;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.UserHandle;
import android.security.keymaster.KeymasterCertificateChain;
import android.security.keystore.ParcelableKeyGenParameterSpec;
import android.telephony.data.ApnSetting;
import android.text.TextUtils;
import java.util.List;

public abstract class Stub extends Binder implements IDevicePolicyManager {
  private static final String DESCRIPTOR = "android.app.admin.IDevicePolicyManager";
  
  static final int TRANSACTION_addCrossProfileIntentFilter = 124;
  
  static final int TRANSACTION_addCrossProfileWidgetProvider = 185;
  
  static final int TRANSACTION_addOverrideApn = 279;
  
  static final int TRANSACTION_addPersistentPreferredActivity = 112;
  
  static final int TRANSACTION_approveCaCert = 94;
  
  static final int TRANSACTION_bindDeviceAdminServiceAsUser = 255;
  
  static final int TRANSACTION_canProfileOwnerResetPasswordWhenLocked = 311;
  
  static final int TRANSACTION_checkDeviceIdentifierAccess = 86;
  
  static final int TRANSACTION_checkProvisioningPreCondition = 211;
  
  static final int TRANSACTION_choosePrivateKeyAlias = 100;
  
  static final int TRANSACTION_clearApplicationUserData = 267;
  
  static final int TRANSACTION_clearCrossProfileIntentFilters = 125;
  
  static final int TRANSACTION_clearDeviceOwner = 74;
  
  static final int TRANSACTION_clearPackagePersistentPreferredActivities = 113;
  
  static final int TRANSACTION_clearProfileOwner = 83;
  
  static final int TRANSACTION_clearResetPasswordToken = 262;
  
  static final int TRANSACTION_clearSystemUpdatePolicyFreezePeriodRecord = 200;
  
  static final int TRANSACTION_createAdminSupportIntent = 137;
  
  static final int TRANSACTION_createAndManageUser = 140;
  
  static final int TRANSACTION_enableSystemApp = 147;
  
  static final int TRANSACTION_enableSystemAppWithIntent = 148;
  
  static final int TRANSACTION_enforceCanManageCaCerts = 93;
  
  static final int TRANSACTION_forceNetworkLogs = 242;
  
  static final int TRANSACTION_forceRemoveActiveAdmin = 61;
  
  static final int TRANSACTION_forceSecurityLogs = 243;
  
  static final int TRANSACTION_forceUpdateUserSetupComplete = 249;
  
  static final int TRANSACTION_generateKeyPair = 98;
  
  static final int TRANSACTION_getAccountTypesWithManagementDisabled = 151;
  
  static final int TRANSACTION_getAccountTypesWithManagementDisabledAsUser = 152;
  
  static final int TRANSACTION_getActiveAdmins = 57;
  
  static final int TRANSACTION_getAffiliationIds = 236;
  
  static final int TRANSACTION_getAllCrossProfilePackages = 297;
  
  static final int TRANSACTION_getAlwaysOnVpnLockdownWhitelist = 111;
  
  static final int TRANSACTION_getAlwaysOnVpnPackage = 107;
  
  static final int TRANSACTION_getAlwaysOnVpnPackageForUser = 108;
  
  static final int TRANSACTION_getApplicationRestrictions = 116;
  
  static final int TRANSACTION_getApplicationRestrictionsManagingPackage = 118;
  
  static final int TRANSACTION_getAutoTimeEnabled = 191;
  
  static final int TRANSACTION_getAutoTimeRequired = 189;
  
  static final int TRANSACTION_getAutoTimeZoneEnabled = 193;
  
  static final int TRANSACTION_getBindDeviceAdminTargetUsers = 256;
  
  static final int TRANSACTION_getBluetoothContactSharingDisabled = 181;
  
  static final int TRANSACTION_getBluetoothContactSharingDisabledForUser = 182;
  
  static final int TRANSACTION_getCameraDisabled = 50;
  
  static final int TRANSACTION_getCertInstallerPackage = 105;
  
  static final int TRANSACTION_getCrossProfileCalendarPackages = 292;
  
  static final int TRANSACTION_getCrossProfileCalendarPackagesForUser = 294;
  
  static final int TRANSACTION_getCrossProfileCallerIdDisabled = 174;
  
  static final int TRANSACTION_getCrossProfileCallerIdDisabledForUser = 175;
  
  static final int TRANSACTION_getCrossProfileContactsSearchDisabled = 177;
  
  static final int TRANSACTION_getCrossProfileContactsSearchDisabledForUser = 178;
  
  static final int TRANSACTION_getCrossProfilePackages = 296;
  
  static final int TRANSACTION_getCrossProfileWidgetProviders = 187;
  
  static final int TRANSACTION_getCurrentFailedPasswordAttempts = 28;
  
  static final int TRANSACTION_getDefaultCrossProfilePackages = 298;
  
  static final int TRANSACTION_getDelegatePackages = 103;
  
  static final int TRANSACTION_getDelegatedScopes = 102;
  
  static final int TRANSACTION_getDeviceOwnerComponent = 71;
  
  static final int TRANSACTION_getDeviceOwnerLockScreenInfo = 88;
  
  static final int TRANSACTION_getDeviceOwnerName = 73;
  
  static final int TRANSACTION_getDeviceOwnerOrganizationName = 231;
  
  static final int TRANSACTION_getDeviceOwnerUserId = 75;
  
  static final int TRANSACTION_getDisallowedSystemApps = 270;
  
  static final int TRANSACTION_getDoNotAskCredentialsOnBoot = 203;
  
  static final int TRANSACTION_getEndUserSessionMessage = 276;
  
  static final int TRANSACTION_getFactoryResetProtectionPolicy = 40;
  
  static final int TRANSACTION_getForceEphemeralUsers = 195;
  
  static final int TRANSACTION_getGlobalPrivateDnsHost = 288;
  
  static final int TRANSACTION_getGlobalPrivateDnsMode = 287;
  
  static final int TRANSACTION_getGlobalProxyAdmin = 43;
  
  static final int TRANSACTION_getKeepUninstalledPackages = 213;
  
  static final int TRANSACTION_getKeyguardDisabledFeatures = 54;
  
  static final int TRANSACTION_getLastBugReportRequestTime = 259;
  
  static final int TRANSACTION_getLastNetworkLogRetrievalTime = 260;
  
  static final int TRANSACTION_getLastSecurityLogRetrievalTime = 258;
  
  static final int TRANSACTION_getLockTaskFeatures = 159;
  
  static final int TRANSACTION_getLockTaskPackages = 156;
  
  static final int TRANSACTION_getLongSupportMessage = 221;
  
  static final int TRANSACTION_getLongSupportMessageForUser = 223;
  
  static final int TRANSACTION_getManagedProfileMaximumTimeOff = 309;
  
  static final int TRANSACTION_getMaximumFailedPasswordsForWipe = 31;
  
  static final int TRANSACTION_getMaximumTimeToLock = 34;
  
  static final int TRANSACTION_getMeteredDataDisabledPackages = 278;
  
  static final int TRANSACTION_getOrganizationColor = 227;
  
  static final int TRANSACTION_getOrganizationColorForUser = 228;
  
  static final int TRANSACTION_getOrganizationName = 230;
  
  static final int TRANSACTION_getOrganizationNameForUser = 232;
  
  static final int TRANSACTION_getOverrideApns = 282;
  
  static final int TRANSACTION_getOwnerInstalledCaCerts = 266;
  
  static final int TRANSACTION_getPasswordComplexity = 26;
  
  static final int TRANSACTION_getPasswordExpiration = 22;
  
  static final int TRANSACTION_getPasswordExpirationTimeout = 21;
  
  static final int TRANSACTION_getPasswordHistoryLength = 19;
  
  static final int TRANSACTION_getPasswordMinimumLength = 4;
  
  static final int TRANSACTION_getPasswordMinimumLetters = 10;
  
  static final int TRANSACTION_getPasswordMinimumLowerCase = 8;
  
  static final int TRANSACTION_getPasswordMinimumMetrics = 17;
  
  static final int TRANSACTION_getPasswordMinimumNonLetter = 16;
  
  static final int TRANSACTION_getPasswordMinimumNumeric = 12;
  
  static final int TRANSACTION_getPasswordMinimumSymbols = 14;
  
  static final int TRANSACTION_getPasswordMinimumUpperCase = 6;
  
  static final int TRANSACTION_getPasswordQuality = 2;
  
  static final int TRANSACTION_getPendingSystemUpdate = 205;
  
  static final int TRANSACTION_getPermissionGrantState = 209;
  
  static final int TRANSACTION_getPermissionPolicy = 207;
  
  static final int TRANSACTION_getPermittedAccessibilityServices = 127;
  
  static final int TRANSACTION_getPermittedAccessibilityServicesForUser = 128;
  
  static final int TRANSACTION_getPermittedCrossProfileNotificationListeners = 135;
  
  static final int TRANSACTION_getPermittedInputMethods = 131;
  
  static final int TRANSACTION_getPermittedInputMethodsForCurrentUser = 132;
  
  static final int TRANSACTION_getPersonalAppsSuspendedReasons = 307;
  
  static final int TRANSACTION_getProfileOwner = 78;
  
  static final int TRANSACTION_getProfileOwnerAsUser = 77;
  
  static final int TRANSACTION_getProfileOwnerName = 80;
  
  static final int TRANSACTION_getProfileOwnerOrDeviceOwnerSupervisionComponent = 79;
  
  static final int TRANSACTION_getProfileWithMinimumFailedPasswordsForWipe = 29;
  
  static final int TRANSACTION_getRemoveWarning = 59;
  
  static final int TRANSACTION_getRequiredStrongAuthTimeout = 36;
  
  static final int TRANSACTION_getRestrictionsProvider = 121;
  
  static final int TRANSACTION_getScreenCaptureDisabled = 52;
  
  static final int TRANSACTION_getSecondaryUsers = 146;
  
  static final int TRANSACTION_getShortSupportMessage = 219;
  
  static final int TRANSACTION_getShortSupportMessageForUser = 222;
  
  static final int TRANSACTION_getStartUserSessionMessage = 275;
  
  static final int TRANSACTION_getStorageEncryption = 46;
  
  static final int TRANSACTION_getStorageEncryptionStatus = 47;
  
  static final int TRANSACTION_getSystemUpdatePolicy = 199;
  
  static final int TRANSACTION_getTransferOwnershipBundle = 272;
  
  static final int TRANSACTION_getTrustAgentConfiguration = 184;
  
  static final int TRANSACTION_getUserControlDisabledPackages = 304;
  
  static final int TRANSACTION_getUserProvisioningState = 233;
  
  static final int TRANSACTION_getUserRestrictions = 123;
  
  static final int TRANSACTION_getWifiMacAddress = 216;
  
  static final int TRANSACTION_hasDeviceOwner = 72;
  
  static final int TRANSACTION_hasGrantedPolicy = 62;
  
  static final int TRANSACTION_hasLockdownAdminConfiguredNetworks = 164;
  
  static final int TRANSACTION_hasUserSetupCompleted = 84;
  
  static final int TRANSACTION_installCaCert = 91;
  
  static final int TRANSACTION_installExistingPackage = 149;
  
  static final int TRANSACTION_installKeyPair = 96;
  
  static final int TRANSACTION_installUpdateFromFile = 290;
  
  static final int TRANSACTION_isAccessibilityServicePermittedByAdmin = 129;
  
  static final int TRANSACTION_isActivePasswordSufficient = 23;
  
  static final int TRANSACTION_isAdminActive = 56;
  
  static final int TRANSACTION_isAffiliatedUser = 237;
  
  static final int TRANSACTION_isAlwaysOnVpnLockdownEnabled = 109;
  
  static final int TRANSACTION_isAlwaysOnVpnLockdownEnabledForUser = 110;
  
  static final int TRANSACTION_isApplicationHidden = 139;
  
  static final int TRANSACTION_isBackupServiceEnabled = 251;
  
  static final int TRANSACTION_isCaCertApproved = 95;
  
  static final int TRANSACTION_isCallerApplicationRestrictionsManagingPackage = 119;
  
  static final int TRANSACTION_isCommonCriteriaModeEnabled = 306;
  
  static final int TRANSACTION_isCurrentInputMethodSetByOwner = 265;
  
  static final int TRANSACTION_isDeviceProvisioned = 246;
  
  static final int TRANSACTION_isDeviceProvisioningConfigApplied = 247;
  
  static final int TRANSACTION_isEphemeralUser = 257;
  
  static final int TRANSACTION_isFactoryResetProtectionPolicySupported = 41;
  
  static final int TRANSACTION_isInputMethodPermittedByAdmin = 133;
  
  static final int TRANSACTION_isLockTaskPermitted = 157;
  
  static final int TRANSACTION_isLogoutEnabled = 269;
  
  static final int TRANSACTION_isManagedKiosk = 299;
  
  static final int TRANSACTION_isManagedProfile = 214;
  
  static final int TRANSACTION_isMasterVolumeMuted = 169;
  
  static final int TRANSACTION_isMeteredDataDisabledPackageForUser = 285;
  
  static final int TRANSACTION_isNetworkLoggingEnabled = 253;
  
  static final int TRANSACTION_isNotificationListenerServicePermitted = 136;
  
  static final int TRANSACTION_isOrganizationOwnedDeviceWithManagedProfile = 85;
  
  static final int TRANSACTION_isOverrideApnEnabled = 284;
  
  static final int TRANSACTION_isPackageAllowedToAccessCalendarForUser = 293;
  
  static final int TRANSACTION_isPackageSuspended = 90;
  
  static final int TRANSACTION_isPasswordSufficientAfterProfileUnification = 25;
  
  static final int TRANSACTION_isProfileActivePasswordSufficientForParent = 24;
  
  static final int TRANSACTION_isProvisioningAllowed = 210;
  
  static final int TRANSACTION_isRemovingAdmin = 196;
  
  static final int TRANSACTION_isResetPasswordTokenActive = 263;
  
  static final int TRANSACTION_isSecondaryLockscreenEnabled = 154;
  
  static final int TRANSACTION_isSecurityLoggingEnabled = 239;
  
  static final int TRANSACTION_isSeparateProfileChallengeAllowed = 224;
  
  static final int TRANSACTION_isSystemOnlyUser = 215;
  
  static final int TRANSACTION_isUnattendedManagedKiosk = 300;
  
  static final int TRANSACTION_isUninstallBlocked = 172;
  
  static final int TRANSACTION_isUninstallInQueue = 244;
  
  static final int TRANSACTION_isUsingUnifiedPassword = 27;
  
  static final int TRANSACTION_lockNow = 37;
  
  static final int TRANSACTION_logoutUser = 145;
  
  static final int TRANSACTION_markProfileOwnerOnOrganizationOwnedDevice = 289;
  
  static final int TRANSACTION_notifyLockTaskModeChanged = 170;
  
  static final int TRANSACTION_notifyPendingSystemUpdate = 204;
  
  static final int TRANSACTION_packageHasActiveAdmins = 58;
  
  static final int TRANSACTION_reboot = 217;
  
  static final int TRANSACTION_removeActiveAdmin = 60;
  
  static final int TRANSACTION_removeCrossProfileWidgetProvider = 186;
  
  static final int TRANSACTION_removeKeyPair = 97;
  
  static final int TRANSACTION_removeOverrideApn = 281;
  
  static final int TRANSACTION_removeUser = 141;
  
  static final int TRANSACTION_reportFailedBiometricAttempt = 66;
  
  static final int TRANSACTION_reportFailedPasswordAttempt = 64;
  
  static final int TRANSACTION_reportKeyguardDismissed = 68;
  
  static final int TRANSACTION_reportKeyguardSecured = 69;
  
  static final int TRANSACTION_reportPasswordChanged = 63;
  
  static final int TRANSACTION_reportSuccessfulBiometricAttempt = 67;
  
  static final int TRANSACTION_reportSuccessfulPasswordAttempt = 65;
  
  static final int TRANSACTION_requestBugreport = 48;
  
  static final int TRANSACTION_resetPassword = 32;
  
  static final int TRANSACTION_resetPasswordWithToken = 264;
  
  static final int TRANSACTION_retrieveNetworkLogs = 254;
  
  static final int TRANSACTION_retrievePreRebootSecurityLogs = 241;
  
  static final int TRANSACTION_retrieveSecurityLogs = 240;
  
  static final int TRANSACTION_setAccountManagementDisabled = 150;
  
  static final int TRANSACTION_setActiveAdmin = 55;
  
  static final int TRANSACTION_setAffiliationIds = 235;
  
  static final int TRANSACTION_setAlwaysOnVpnPackage = 106;
  
  static final int TRANSACTION_setApplicationHidden = 138;
  
  static final int TRANSACTION_setApplicationRestrictions = 115;
  
  static final int TRANSACTION_setApplicationRestrictionsManagingPackage = 117;
  
  static final int TRANSACTION_setAutoTimeEnabled = 190;
  
  static final int TRANSACTION_setAutoTimeRequired = 188;
  
  static final int TRANSACTION_setAutoTimeZoneEnabled = 192;
  
  static final int TRANSACTION_setBackupServiceEnabled = 250;
  
  static final int TRANSACTION_setBluetoothContactSharingDisabled = 180;
  
  static final int TRANSACTION_setCameraDisabled = 49;
  
  static final int TRANSACTION_setCertInstallerPackage = 104;
  
  static final int TRANSACTION_setCommonCriteriaModeEnabled = 305;
  
  static final int TRANSACTION_setConfiguredNetworksLockdownState = 163;
  
  static final int TRANSACTION_setCrossProfileCalendarPackages = 291;
  
  static final int TRANSACTION_setCrossProfileCallerIdDisabled = 173;
  
  static final int TRANSACTION_setCrossProfileContactsSearchDisabled = 176;
  
  static final int TRANSACTION_setCrossProfilePackages = 295;
  
  static final int TRANSACTION_setDefaultSmsApplication = 114;
  
  static final int TRANSACTION_setDelegatedScopes = 101;
  
  static final int TRANSACTION_setDeviceOwner = 70;
  
  static final int TRANSACTION_setDeviceOwnerLockScreenInfo = 87;
  
  static final int TRANSACTION_setDeviceProvisioningConfigApplied = 248;
  
  static final int TRANSACTION_setEndUserSessionMessage = 274;
  
  static final int TRANSACTION_setFactoryResetProtectionPolicy = 39;
  
  static final int TRANSACTION_setForceEphemeralUsers = 194;
  
  static final int TRANSACTION_setGlobalPrivateDns = 286;
  
  static final int TRANSACTION_setGlobalProxy = 42;
  
  static final int TRANSACTION_setGlobalSetting = 160;
  
  static final int TRANSACTION_setKeepUninstalledPackages = 212;
  
  static final int TRANSACTION_setKeyGrantForApp = 302;
  
  static final int TRANSACTION_setKeyPairCertificate = 99;
  
  static final int TRANSACTION_setKeyguardDisabled = 201;
  
  static final int TRANSACTION_setKeyguardDisabledFeatures = 53;
  
  static final int TRANSACTION_setLocationEnabled = 165;
  
  static final int TRANSACTION_setLockTaskFeatures = 158;
  
  static final int TRANSACTION_setLockTaskPackages = 155;
  
  static final int TRANSACTION_setLogoutEnabled = 268;
  
  static final int TRANSACTION_setLongSupportMessage = 220;
  
  static final int TRANSACTION_setManagedProfileMaximumTimeOff = 310;
  
  static final int TRANSACTION_setMasterVolumeMuted = 168;
  
  static final int TRANSACTION_setMaximumFailedPasswordsForWipe = 30;
  
  static final int TRANSACTION_setMaximumTimeToLock = 33;
  
  static final int TRANSACTION_setMeteredDataDisabledPackages = 277;
  
  static final int TRANSACTION_setNetworkLoggingEnabled = 252;
  
  static final int TRANSACTION_setOrganizationColor = 225;
  
  static final int TRANSACTION_setOrganizationColorForUser = 226;
  
  static final int TRANSACTION_setOrganizationName = 229;
  
  static final int TRANSACTION_setOverrideApnsEnabled = 283;
  
  static final int TRANSACTION_setPackagesSuspended = 89;
  
  static final int TRANSACTION_setPasswordExpirationTimeout = 20;
  
  static final int TRANSACTION_setPasswordHistoryLength = 18;
  
  static final int TRANSACTION_setPasswordMinimumLength = 3;
  
  static final int TRANSACTION_setPasswordMinimumLetters = 9;
  
  static final int TRANSACTION_setPasswordMinimumLowerCase = 7;
  
  static final int TRANSACTION_setPasswordMinimumNonLetter = 15;
  
  static final int TRANSACTION_setPasswordMinimumNumeric = 11;
  
  static final int TRANSACTION_setPasswordMinimumSymbols = 13;
  
  static final int TRANSACTION_setPasswordMinimumUpperCase = 5;
  
  static final int TRANSACTION_setPasswordQuality = 1;
  
  static final int TRANSACTION_setPermissionGrantState = 208;
  
  static final int TRANSACTION_setPermissionPolicy = 206;
  
  static final int TRANSACTION_setPermittedAccessibilityServices = 126;
  
  static final int TRANSACTION_setPermittedCrossProfileNotificationListeners = 134;
  
  static final int TRANSACTION_setPermittedInputMethods = 130;
  
  static final int TRANSACTION_setPersonalAppsSuspended = 308;
  
  static final int TRANSACTION_setProfileEnabled = 81;
  
  static final int TRANSACTION_setProfileName = 82;
  
  static final int TRANSACTION_setProfileOwner = 76;
  
  static final int TRANSACTION_setRecommendedGlobalProxy = 44;
  
  static final int TRANSACTION_setRequiredStrongAuthTimeout = 35;
  
  static final int TRANSACTION_setResetPasswordToken = 261;
  
  static final int TRANSACTION_setRestrictionsProvider = 120;
  
  static final int TRANSACTION_setScreenCaptureDisabled = 51;
  
  static final int TRANSACTION_setSecondaryLockscreenEnabled = 153;
  
  static final int TRANSACTION_setSecureSetting = 162;
  
  static final int TRANSACTION_setSecurityLoggingEnabled = 238;
  
  static final int TRANSACTION_setShortSupportMessage = 218;
  
  static final int TRANSACTION_setStartUserSessionMessage = 273;
  
  static final int TRANSACTION_setStatusBarDisabled = 202;
  
  static final int TRANSACTION_setStorageEncryption = 45;
  
  static final int TRANSACTION_setSystemSetting = 161;
  
  static final int TRANSACTION_setSystemUpdatePolicy = 198;
  
  static final int TRANSACTION_setTime = 166;
  
  static final int TRANSACTION_setTimeZone = 167;
  
  static final int TRANSACTION_setTrustAgentConfiguration = 183;
  
  static final int TRANSACTION_setUninstallBlocked = 171;
  
  static final int TRANSACTION_setUserControlDisabledPackages = 303;
  
  static final int TRANSACTION_setUserIcon = 197;
  
  static final int TRANSACTION_setUserProvisioningState = 234;
  
  static final int TRANSACTION_setUserRestriction = 122;
  
  static final int TRANSACTION_startManagedQuickContact = 179;
  
  static final int TRANSACTION_startUserInBackground = 143;
  
  static final int TRANSACTION_startViewCalendarEventInManagedProfile = 301;
  
  static final int TRANSACTION_stopUser = 144;
  
  static final int TRANSACTION_switchUser = 142;
  
  static final int TRANSACTION_transferOwnership = 271;
  
  static final int TRANSACTION_uninstallCaCerts = 92;
  
  static final int TRANSACTION_uninstallPackageWithActiveAdmins = 245;
  
  static final int TRANSACTION_updateOverrideApn = 280;
  
  static final int TRANSACTION_wipeDataWithReason = 38;
  
  public Stub() {
    attachInterface(this, "android.app.admin.IDevicePolicyManager");
  }
  
  public static IDevicePolicyManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.admin.IDevicePolicyManager");
    return (iInterface != null && iInterface instanceof IDevicePolicyManager) ? (IDevicePolicyManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IDevicePolicyManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 311:
        return "canProfileOwnerResetPasswordWhenLocked";
      case 310:
        return "setManagedProfileMaximumTimeOff";
      case 309:
        return "getManagedProfileMaximumTimeOff";
      case 308:
        return "setPersonalAppsSuspended";
      case 307:
        return "getPersonalAppsSuspendedReasons";
      case 306:
        return "isCommonCriteriaModeEnabled";
      case 305:
        return "setCommonCriteriaModeEnabled";
      case 304:
        return "getUserControlDisabledPackages";
      case 303:
        return "setUserControlDisabledPackages";
      case 302:
        return "setKeyGrantForApp";
      case 301:
        return "startViewCalendarEventInManagedProfile";
      case 300:
        return "isUnattendedManagedKiosk";
      case 299:
        return "isManagedKiosk";
      case 298:
        return "getDefaultCrossProfilePackages";
      case 297:
        return "getAllCrossProfilePackages";
      case 296:
        return "getCrossProfilePackages";
      case 295:
        return "setCrossProfilePackages";
      case 294:
        return "getCrossProfileCalendarPackagesForUser";
      case 293:
        return "isPackageAllowedToAccessCalendarForUser";
      case 292:
        return "getCrossProfileCalendarPackages";
      case 291:
        return "setCrossProfileCalendarPackages";
      case 290:
        return "installUpdateFromFile";
      case 289:
        return "markProfileOwnerOnOrganizationOwnedDevice";
      case 288:
        return "getGlobalPrivateDnsHost";
      case 287:
        return "getGlobalPrivateDnsMode";
      case 286:
        return "setGlobalPrivateDns";
      case 285:
        return "isMeteredDataDisabledPackageForUser";
      case 284:
        return "isOverrideApnEnabled";
      case 283:
        return "setOverrideApnsEnabled";
      case 282:
        return "getOverrideApns";
      case 281:
        return "removeOverrideApn";
      case 280:
        return "updateOverrideApn";
      case 279:
        return "addOverrideApn";
      case 278:
        return "getMeteredDataDisabledPackages";
      case 277:
        return "setMeteredDataDisabledPackages";
      case 276:
        return "getEndUserSessionMessage";
      case 275:
        return "getStartUserSessionMessage";
      case 274:
        return "setEndUserSessionMessage";
      case 273:
        return "setStartUserSessionMessage";
      case 272:
        return "getTransferOwnershipBundle";
      case 271:
        return "transferOwnership";
      case 270:
        return "getDisallowedSystemApps";
      case 269:
        return "isLogoutEnabled";
      case 268:
        return "setLogoutEnabled";
      case 267:
        return "clearApplicationUserData";
      case 266:
        return "getOwnerInstalledCaCerts";
      case 265:
        return "isCurrentInputMethodSetByOwner";
      case 264:
        return "resetPasswordWithToken";
      case 263:
        return "isResetPasswordTokenActive";
      case 262:
        return "clearResetPasswordToken";
      case 261:
        return "setResetPasswordToken";
      case 260:
        return "getLastNetworkLogRetrievalTime";
      case 259:
        return "getLastBugReportRequestTime";
      case 258:
        return "getLastSecurityLogRetrievalTime";
      case 257:
        return "isEphemeralUser";
      case 256:
        return "getBindDeviceAdminTargetUsers";
      case 255:
        return "bindDeviceAdminServiceAsUser";
      case 254:
        return "retrieveNetworkLogs";
      case 253:
        return "isNetworkLoggingEnabled";
      case 252:
        return "setNetworkLoggingEnabled";
      case 251:
        return "isBackupServiceEnabled";
      case 250:
        return "setBackupServiceEnabled";
      case 249:
        return "forceUpdateUserSetupComplete";
      case 248:
        return "setDeviceProvisioningConfigApplied";
      case 247:
        return "isDeviceProvisioningConfigApplied";
      case 246:
        return "isDeviceProvisioned";
      case 245:
        return "uninstallPackageWithActiveAdmins";
      case 244:
        return "isUninstallInQueue";
      case 243:
        return "forceSecurityLogs";
      case 242:
        return "forceNetworkLogs";
      case 241:
        return "retrievePreRebootSecurityLogs";
      case 240:
        return "retrieveSecurityLogs";
      case 239:
        return "isSecurityLoggingEnabled";
      case 238:
        return "setSecurityLoggingEnabled";
      case 237:
        return "isAffiliatedUser";
      case 236:
        return "getAffiliationIds";
      case 235:
        return "setAffiliationIds";
      case 234:
        return "setUserProvisioningState";
      case 233:
        return "getUserProvisioningState";
      case 232:
        return "getOrganizationNameForUser";
      case 231:
        return "getDeviceOwnerOrganizationName";
      case 230:
        return "getOrganizationName";
      case 229:
        return "setOrganizationName";
      case 228:
        return "getOrganizationColorForUser";
      case 227:
        return "getOrganizationColor";
      case 226:
        return "setOrganizationColorForUser";
      case 225:
        return "setOrganizationColor";
      case 224:
        return "isSeparateProfileChallengeAllowed";
      case 223:
        return "getLongSupportMessageForUser";
      case 222:
        return "getShortSupportMessageForUser";
      case 221:
        return "getLongSupportMessage";
      case 220:
        return "setLongSupportMessage";
      case 219:
        return "getShortSupportMessage";
      case 218:
        return "setShortSupportMessage";
      case 217:
        return "reboot";
      case 216:
        return "getWifiMacAddress";
      case 215:
        return "isSystemOnlyUser";
      case 214:
        return "isManagedProfile";
      case 213:
        return "getKeepUninstalledPackages";
      case 212:
        return "setKeepUninstalledPackages";
      case 211:
        return "checkProvisioningPreCondition";
      case 210:
        return "isProvisioningAllowed";
      case 209:
        return "getPermissionGrantState";
      case 208:
        return "setPermissionGrantState";
      case 207:
        return "getPermissionPolicy";
      case 206:
        return "setPermissionPolicy";
      case 205:
        return "getPendingSystemUpdate";
      case 204:
        return "notifyPendingSystemUpdate";
      case 203:
        return "getDoNotAskCredentialsOnBoot";
      case 202:
        return "setStatusBarDisabled";
      case 201:
        return "setKeyguardDisabled";
      case 200:
        return "clearSystemUpdatePolicyFreezePeriodRecord";
      case 199:
        return "getSystemUpdatePolicy";
      case 198:
        return "setSystemUpdatePolicy";
      case 197:
        return "setUserIcon";
      case 196:
        return "isRemovingAdmin";
      case 195:
        return "getForceEphemeralUsers";
      case 194:
        return "setForceEphemeralUsers";
      case 193:
        return "getAutoTimeZoneEnabled";
      case 192:
        return "setAutoTimeZoneEnabled";
      case 191:
        return "getAutoTimeEnabled";
      case 190:
        return "setAutoTimeEnabled";
      case 189:
        return "getAutoTimeRequired";
      case 188:
        return "setAutoTimeRequired";
      case 187:
        return "getCrossProfileWidgetProviders";
      case 186:
        return "removeCrossProfileWidgetProvider";
      case 185:
        return "addCrossProfileWidgetProvider";
      case 184:
        return "getTrustAgentConfiguration";
      case 183:
        return "setTrustAgentConfiguration";
      case 182:
        return "getBluetoothContactSharingDisabledForUser";
      case 181:
        return "getBluetoothContactSharingDisabled";
      case 180:
        return "setBluetoothContactSharingDisabled";
      case 179:
        return "startManagedQuickContact";
      case 178:
        return "getCrossProfileContactsSearchDisabledForUser";
      case 177:
        return "getCrossProfileContactsSearchDisabled";
      case 176:
        return "setCrossProfileContactsSearchDisabled";
      case 175:
        return "getCrossProfileCallerIdDisabledForUser";
      case 174:
        return "getCrossProfileCallerIdDisabled";
      case 173:
        return "setCrossProfileCallerIdDisabled";
      case 172:
        return "isUninstallBlocked";
      case 171:
        return "setUninstallBlocked";
      case 170:
        return "notifyLockTaskModeChanged";
      case 169:
        return "isMasterVolumeMuted";
      case 168:
        return "setMasterVolumeMuted";
      case 167:
        return "setTimeZone";
      case 166:
        return "setTime";
      case 165:
        return "setLocationEnabled";
      case 164:
        return "hasLockdownAdminConfiguredNetworks";
      case 163:
        return "setConfiguredNetworksLockdownState";
      case 162:
        return "setSecureSetting";
      case 161:
        return "setSystemSetting";
      case 160:
        return "setGlobalSetting";
      case 159:
        return "getLockTaskFeatures";
      case 158:
        return "setLockTaskFeatures";
      case 157:
        return "isLockTaskPermitted";
      case 156:
        return "getLockTaskPackages";
      case 155:
        return "setLockTaskPackages";
      case 154:
        return "isSecondaryLockscreenEnabled";
      case 153:
        return "setSecondaryLockscreenEnabled";
      case 152:
        return "getAccountTypesWithManagementDisabledAsUser";
      case 151:
        return "getAccountTypesWithManagementDisabled";
      case 150:
        return "setAccountManagementDisabled";
      case 149:
        return "installExistingPackage";
      case 148:
        return "enableSystemAppWithIntent";
      case 147:
        return "enableSystemApp";
      case 146:
        return "getSecondaryUsers";
      case 145:
        return "logoutUser";
      case 144:
        return "stopUser";
      case 143:
        return "startUserInBackground";
      case 142:
        return "switchUser";
      case 141:
        return "removeUser";
      case 140:
        return "createAndManageUser";
      case 139:
        return "isApplicationHidden";
      case 138:
        return "setApplicationHidden";
      case 137:
        return "createAdminSupportIntent";
      case 136:
        return "isNotificationListenerServicePermitted";
      case 135:
        return "getPermittedCrossProfileNotificationListeners";
      case 134:
        return "setPermittedCrossProfileNotificationListeners";
      case 133:
        return "isInputMethodPermittedByAdmin";
      case 132:
        return "getPermittedInputMethodsForCurrentUser";
      case 131:
        return "getPermittedInputMethods";
      case 130:
        return "setPermittedInputMethods";
      case 129:
        return "isAccessibilityServicePermittedByAdmin";
      case 128:
        return "getPermittedAccessibilityServicesForUser";
      case 127:
        return "getPermittedAccessibilityServices";
      case 126:
        return "setPermittedAccessibilityServices";
      case 125:
        return "clearCrossProfileIntentFilters";
      case 124:
        return "addCrossProfileIntentFilter";
      case 123:
        return "getUserRestrictions";
      case 122:
        return "setUserRestriction";
      case 121:
        return "getRestrictionsProvider";
      case 120:
        return "setRestrictionsProvider";
      case 119:
        return "isCallerApplicationRestrictionsManagingPackage";
      case 118:
        return "getApplicationRestrictionsManagingPackage";
      case 117:
        return "setApplicationRestrictionsManagingPackage";
      case 116:
        return "getApplicationRestrictions";
      case 115:
        return "setApplicationRestrictions";
      case 114:
        return "setDefaultSmsApplication";
      case 113:
        return "clearPackagePersistentPreferredActivities";
      case 112:
        return "addPersistentPreferredActivity";
      case 111:
        return "getAlwaysOnVpnLockdownWhitelist";
      case 110:
        return "isAlwaysOnVpnLockdownEnabledForUser";
      case 109:
        return "isAlwaysOnVpnLockdownEnabled";
      case 108:
        return "getAlwaysOnVpnPackageForUser";
      case 107:
        return "getAlwaysOnVpnPackage";
      case 106:
        return "setAlwaysOnVpnPackage";
      case 105:
        return "getCertInstallerPackage";
      case 104:
        return "setCertInstallerPackage";
      case 103:
        return "getDelegatePackages";
      case 102:
        return "getDelegatedScopes";
      case 101:
        return "setDelegatedScopes";
      case 100:
        return "choosePrivateKeyAlias";
      case 99:
        return "setKeyPairCertificate";
      case 98:
        return "generateKeyPair";
      case 97:
        return "removeKeyPair";
      case 96:
        return "installKeyPair";
      case 95:
        return "isCaCertApproved";
      case 94:
        return "approveCaCert";
      case 93:
        return "enforceCanManageCaCerts";
      case 92:
        return "uninstallCaCerts";
      case 91:
        return "installCaCert";
      case 90:
        return "isPackageSuspended";
      case 89:
        return "setPackagesSuspended";
      case 88:
        return "getDeviceOwnerLockScreenInfo";
      case 87:
        return "setDeviceOwnerLockScreenInfo";
      case 86:
        return "checkDeviceIdentifierAccess";
      case 85:
        return "isOrganizationOwnedDeviceWithManagedProfile";
      case 84:
        return "hasUserSetupCompleted";
      case 83:
        return "clearProfileOwner";
      case 82:
        return "setProfileName";
      case 81:
        return "setProfileEnabled";
      case 80:
        return "getProfileOwnerName";
      case 79:
        return "getProfileOwnerOrDeviceOwnerSupervisionComponent";
      case 78:
        return "getProfileOwner";
      case 77:
        return "getProfileOwnerAsUser";
      case 76:
        return "setProfileOwner";
      case 75:
        return "getDeviceOwnerUserId";
      case 74:
        return "clearDeviceOwner";
      case 73:
        return "getDeviceOwnerName";
      case 72:
        return "hasDeviceOwner";
      case 71:
        return "getDeviceOwnerComponent";
      case 70:
        return "setDeviceOwner";
      case 69:
        return "reportKeyguardSecured";
      case 68:
        return "reportKeyguardDismissed";
      case 67:
        return "reportSuccessfulBiometricAttempt";
      case 66:
        return "reportFailedBiometricAttempt";
      case 65:
        return "reportSuccessfulPasswordAttempt";
      case 64:
        return "reportFailedPasswordAttempt";
      case 63:
        return "reportPasswordChanged";
      case 62:
        return "hasGrantedPolicy";
      case 61:
        return "forceRemoveActiveAdmin";
      case 60:
        return "removeActiveAdmin";
      case 59:
        return "getRemoveWarning";
      case 58:
        return "packageHasActiveAdmins";
      case 57:
        return "getActiveAdmins";
      case 56:
        return "isAdminActive";
      case 55:
        return "setActiveAdmin";
      case 54:
        return "getKeyguardDisabledFeatures";
      case 53:
        return "setKeyguardDisabledFeatures";
      case 52:
        return "getScreenCaptureDisabled";
      case 51:
        return "setScreenCaptureDisabled";
      case 50:
        return "getCameraDisabled";
      case 49:
        return "setCameraDisabled";
      case 48:
        return "requestBugreport";
      case 47:
        return "getStorageEncryptionStatus";
      case 46:
        return "getStorageEncryption";
      case 45:
        return "setStorageEncryption";
      case 44:
        return "setRecommendedGlobalProxy";
      case 43:
        return "getGlobalProxyAdmin";
      case 42:
        return "setGlobalProxy";
      case 41:
        return "isFactoryResetProtectionPolicySupported";
      case 40:
        return "getFactoryResetProtectionPolicy";
      case 39:
        return "setFactoryResetProtectionPolicy";
      case 38:
        return "wipeDataWithReason";
      case 37:
        return "lockNow";
      case 36:
        return "getRequiredStrongAuthTimeout";
      case 35:
        return "setRequiredStrongAuthTimeout";
      case 34:
        return "getMaximumTimeToLock";
      case 33:
        return "setMaximumTimeToLock";
      case 32:
        return "resetPassword";
      case 31:
        return "getMaximumFailedPasswordsForWipe";
      case 30:
        return "setMaximumFailedPasswordsForWipe";
      case 29:
        return "getProfileWithMinimumFailedPasswordsForWipe";
      case 28:
        return "getCurrentFailedPasswordAttempts";
      case 27:
        return "isUsingUnifiedPassword";
      case 26:
        return "getPasswordComplexity";
      case 25:
        return "isPasswordSufficientAfterProfileUnification";
      case 24:
        return "isProfileActivePasswordSufficientForParent";
      case 23:
        return "isActivePasswordSufficient";
      case 22:
        return "getPasswordExpiration";
      case 21:
        return "getPasswordExpirationTimeout";
      case 20:
        return "setPasswordExpirationTimeout";
      case 19:
        return "getPasswordHistoryLength";
      case 18:
        return "setPasswordHistoryLength";
      case 17:
        return "getPasswordMinimumMetrics";
      case 16:
        return "getPasswordMinimumNonLetter";
      case 15:
        return "setPasswordMinimumNonLetter";
      case 14:
        return "getPasswordMinimumSymbols";
      case 13:
        return "setPasswordMinimumSymbols";
      case 12:
        return "getPasswordMinimumNumeric";
      case 11:
        return "setPasswordMinimumNumeric";
      case 10:
        return "getPasswordMinimumLetters";
      case 9:
        return "setPasswordMinimumLetters";
      case 8:
        return "getPasswordMinimumLowerCase";
      case 7:
        return "setPasswordMinimumLowerCase";
      case 6:
        return "getPasswordMinimumUpperCase";
      case 5:
        return "setPasswordMinimumUpperCase";
      case 4:
        return "getPasswordMinimumLength";
      case 3:
        return "setPasswordMinimumLength";
      case 2:
        return "getPasswordQuality";
      case 1:
        break;
    } 
    return "setPasswordQuality";
  }
  
  private boolean onTransact$bindDeviceAdminServiceAsUser$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    Intent intent;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    IApplicationThread iApplicationThread = IApplicationThread.Stub.asInterface(paramParcel1.readStrongBinder());
    IBinder iBinder = paramParcel1.readStrongBinder();
    if (paramParcel1.readInt() != 0) {
      intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
    } else {
      intent = null;
    } 
    boolean bool = bindDeviceAdminServiceAsUser(componentName, iApplicationThread, iBinder, intent, IServiceConnection.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readInt());
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool);
    return true;
  }
  
  private boolean onTransact$choosePrivateKeyAlias$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    Uri uri;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    int i = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      uri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
    } else {
      uri = null;
    } 
    choosePrivateKeyAlias(i, uri, paramParcel1.readString(), paramParcel1.readStrongBinder());
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$clearApplicationUserData$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    clearApplicationUserData(componentName, paramParcel1.readString(), IPackageDataObserver.Stub.asInterface(paramParcel1.readStrongBinder()));
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$createAndManageUser$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName1;
    ComponentName componentName2;
    PersistableBundle persistableBundle;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName1 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName1 = null;
    } 
    String str = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      componentName2 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName2 = null;
    } 
    if (paramParcel1.readInt() != 0) {
      persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      persistableBundle = null;
    } 
    UserHandle userHandle = createAndManageUser(componentName1, str, componentName2, persistableBundle, paramParcel1.readInt());
    paramParcel2.writeNoException();
    if (userHandle != null) {
      paramParcel2.writeInt(1);
      userHandle.writeToParcel(paramParcel2, 1);
    } else {
      paramParcel2.writeInt(0);
    } 
    return true;
  }
  
  private boolean onTransact$generateKeyPair$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    ParcelableKeyGenParameterSpec parcelableKeyGenParameterSpec;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str1 = paramParcel1.readString();
    String str2 = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      parcelableKeyGenParameterSpec = (ParcelableKeyGenParameterSpec)ParcelableKeyGenParameterSpec.CREATOR.createFromParcel(paramParcel1);
    } else {
      parcelableKeyGenParameterSpec = null;
    } 
    int i = paramParcel1.readInt();
    KeymasterCertificateChain keymasterCertificateChain = new KeymasterCertificateChain();
    boolean bool = generateKeyPair(componentName, str1, str2, parcelableKeyGenParameterSpec, i, keymasterCertificateChain);
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool);
    paramParcel2.writeInt(1);
    keymasterCertificateChain.writeToParcel(paramParcel2, 1);
    return true;
  }
  
  private boolean onTransact$getDisallowedSystemApps$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    List<String> list = getDisallowedSystemApps(componentName, paramParcel1.readInt(), paramParcel1.readString());
    paramParcel2.writeNoException();
    paramParcel2.writeStringList(list);
    return true;
  }
  
  private boolean onTransact$getPermissionGrantState$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    int i = getPermissionGrantState(componentName, paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
    paramParcel2.writeNoException();
    paramParcel2.writeInt(i);
    return true;
  }
  
  private boolean onTransact$getTrustAgentConfiguration$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName1;
    ComponentName componentName2;
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName1 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName1 = null;
    } 
    if (paramParcel1.readInt() != 0) {
      componentName2 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName2 = null;
    } 
    int i = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    List<PersistableBundle> list = getTrustAgentConfiguration(componentName1, componentName2, i, bool);
    paramParcel2.writeNoException();
    paramParcel2.writeTypedList(list);
    return true;
  }
  
  private boolean onTransact$installKeyPair$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    boolean bool1;
    boolean bool2;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str1 = paramParcel1.readString();
    byte[] arrayOfByte1 = paramParcel1.createByteArray();
    byte[] arrayOfByte2 = paramParcel1.createByteArray();
    byte[] arrayOfByte3 = paramParcel1.createByteArray();
    String str2 = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramParcel1.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    boolean bool = installKeyPair(componentName, str1, arrayOfByte1, arrayOfByte2, arrayOfByte3, str2, bool1, bool2);
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool);
    return true;
  }
  
  private boolean onTransact$installUpdateFromFile$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    ParcelFileDescriptor parcelFileDescriptor;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    if (paramParcel1.readInt() != 0) {
      parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
    } else {
      parcelFileDescriptor = null;
    } 
    installUpdateFromFile(componentName, parcelFileDescriptor, StartInstallingUpdateCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$isApplicationHidden$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str1 = paramParcel1.readString();
    String str2 = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    boolean bool1 = isApplicationHidden(componentName, str1, str2, bool);
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool1);
    return true;
  }
  
  private boolean onTransact$isMeteredDataDisabledPackageForUser$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    boolean bool = isMeteredDataDisabledPackageForUser(componentName, paramParcel1.readString(), paramParcel1.readInt());
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool);
    return true;
  }
  
  private boolean onTransact$notifyLockTaskModeChanged$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    notifyLockTaskModeChanged(bool, paramParcel1.readString(), paramParcel1.readInt());
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$resetPasswordWithToken$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    boolean bool = resetPasswordWithToken(componentName, paramParcel1.readString(), paramParcel1.createByteArray(), paramParcel1.readInt());
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool);
    return true;
  }
  
  private boolean onTransact$retrieveNetworkLogs$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    List<NetworkEvent> list = retrieveNetworkLogs(componentName, paramParcel1.readString(), paramParcel1.readLong());
    paramParcel2.writeNoException();
    paramParcel2.writeTypedList(list);
    return true;
  }
  
  private boolean onTransact$setAccountManagementDisabled$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    boolean bool2;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str = paramParcel1.readString();
    int i = paramParcel1.readInt();
    boolean bool1 = false;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (paramParcel1.readInt() != 0)
      bool1 = true; 
    setAccountManagementDisabled(componentName, str, bool2, bool1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$setAlwaysOnVpnPackage$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    boolean bool1 = setAlwaysOnVpnPackage(componentName, str, bool, paramParcel1.createStringArrayList());
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool1);
    return true;
  }
  
  private boolean onTransact$setApplicationHidden$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    boolean bool1;
    boolean bool2;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str1 = paramParcel1.readString();
    String str2 = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramParcel1.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    boolean bool = setApplicationHidden(componentName, str1, str2, bool1, bool2);
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool);
    return true;
  }
  
  private boolean onTransact$setApplicationRestrictions$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str1 = paramParcel1.readString();
    String str2 = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    setApplicationRestrictions(componentName, str1, str2, (Bundle)paramParcel1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$setGlobalPrivateDns$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    int i = setGlobalPrivateDns(componentName, paramParcel1.readInt(), paramParcel1.readString());
    paramParcel2.writeNoException();
    paramParcel2.writeInt(i);
    return true;
  }
  
  private boolean onTransact$setKeepUninstalledPackages$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    setKeepUninstalledPackages(componentName, paramParcel1.readString(), paramParcel1.createStringArrayList());
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$setKeyGrantForApp$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str1 = paramParcel1.readString();
    String str2 = paramParcel1.readString();
    String str3 = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    boolean bool1 = setKeyGrantForApp(componentName, str1, str2, str3, bool);
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool1);
    return true;
  }
  
  private boolean onTransact$setKeyPairCertificate$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str1 = paramParcel1.readString();
    String str2 = paramParcel1.readString();
    byte[] arrayOfByte1 = paramParcel1.createByteArray();
    byte[] arrayOfByte2 = paramParcel1.createByteArray();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    boolean bool1 = setKeyPairCertificate(componentName, str1, str2, arrayOfByte1, arrayOfByte2, bool);
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool1);
    return true;
  }
  
  private boolean onTransact$setNetworkLoggingEnabled$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    setNetworkLoggingEnabled(componentName, str, bool);
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$setPackagesSuspended$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str = paramParcel1.readString();
    String[] arrayOfString2 = paramParcel1.createStringArray();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    String[] arrayOfString1 = setPackagesSuspended(componentName, str, arrayOfString2, bool);
    paramParcel2.writeNoException();
    paramParcel2.writeStringArray(arrayOfString1);
    return true;
  }
  
  private boolean onTransact$setPermissionGrantState$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str1 = paramParcel1.readString();
    String str2 = paramParcel1.readString();
    String str3 = paramParcel1.readString();
    int i = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    setPermissionGrantState(componentName, str1, str2, str3, i, (RemoteCallback)paramParcel1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$setPermissionPolicy$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    setPermissionPolicy(componentName, paramParcel1.readString(), paramParcel1.readInt());
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$setSecureSetting$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    setSecureSetting(componentName, paramParcel1.readString(), paramParcel1.readString());
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$setSystemSetting$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    setSystemSetting(componentName, paramParcel1.readString(), paramParcel1.readString());
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$setTrustAgentConfiguration$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName1;
    ComponentName componentName2;
    PersistableBundle persistableBundle;
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName1 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName1 = null;
    } 
    if (paramParcel1.readInt() != 0) {
      componentName2 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName2 = null;
    } 
    if (paramParcel1.readInt() != 0) {
      persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      persistableBundle = null;
    } 
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    setTrustAgentConfiguration(componentName1, componentName2, persistableBundle, bool);
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$setUninstallBlocked$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str1 = paramParcel1.readString();
    String str2 = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    setUninstallBlocked(componentName, str1, str2, bool);
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$setUserRestriction$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    boolean bool2;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    String str = paramParcel1.readString();
    int i = paramParcel1.readInt();
    boolean bool1 = false;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (paramParcel1.readInt() != 0)
      bool1 = true; 
    setUserRestriction(componentName, str, bool2, bool1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$startManagedQuickContact$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    String str = paramParcel1.readString();
    long l1 = paramParcel1.readLong();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    long l2 = paramParcel1.readLong();
    if (paramParcel1.readInt() != 0) {
      Intent intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    startManagedQuickContact(str, l1, bool, l2, (Intent)paramParcel1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$startViewCalendarEventInManagedProfile$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    boolean bool;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    String str = paramParcel1.readString();
    long l1 = paramParcel1.readLong();
    long l2 = paramParcel1.readLong();
    long l3 = paramParcel1.readLong();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    boolean bool1 = startViewCalendarEventInManagedProfile(str, l1, l2, l3, bool, paramParcel1.readInt());
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool1);
    return true;
  }
  
  private boolean onTransact$transferOwnership$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName1;
    ComponentName componentName2;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName1 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName1 = null;
    } 
    if (paramParcel1.readInt() != 0) {
      componentName2 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName2 = null;
    } 
    if (paramParcel1.readInt() != 0) {
      PersistableBundle persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    transferOwnership(componentName1, componentName2, (PersistableBundle)paramParcel1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private boolean onTransact$updateOverrideApn$(Parcel paramParcel1, Parcel paramParcel2) throws RemoteException {
    ComponentName componentName;
    paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    int i = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      ApnSetting apnSetting = (ApnSetting)ApnSetting.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    boolean bool = updateOverrideApn(componentName, i, (ApnSetting)paramParcel1);
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool);
    return true;
  }
  
  public static boolean setDefaultImpl(IDevicePolicyManager paramIDevicePolicyManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIDevicePolicyManager != null) {
        Proxy.sDefaultImpl = paramIDevicePolicyManager;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1598968902) {
      boolean bool20;
      int i15;
      boolean bool19;
      int i14;
      boolean bool18;
      int i13;
      boolean bool17;
      int i12;
      boolean bool16;
      int i11;
      boolean bool15;
      int i10;
      boolean bool14;
      int i9;
      boolean bool13;
      int i8;
      boolean bool12;
      int i7;
      boolean bool11;
      int i6;
      boolean bool10;
      int i5;
      boolean bool9;
      int i4;
      boolean bool8;
      int i3;
      boolean bool7;
      int i2;
      boolean bool6;
      int i1;
      boolean bool5;
      int n;
      boolean bool4;
      int m;
      boolean bool3;
      int k;
      boolean bool2;
      int j;
      boolean bool1;
      List<String> list10;
      String str4;
      List<ApnSetting> list9;
      CharSequence charSequence3;
      PersistableBundle persistableBundle;
      StringParceledListSlice stringParceledListSlice;
      List<UserHandle> list8;
      ParceledListSlice parceledListSlice;
      List<String> list7;
      CharSequence charSequence2;
      List<String> list6;
      SystemUpdateInfo systemUpdateInfo;
      SystemUpdatePolicy systemUpdatePolicy;
      List<String> list5;
      String[] arrayOfString;
      List<UserHandle> list4;
      Intent intent;
      List<String> list3;
      Bundle bundle2;
      ComponentName componentName4;
      String str3;
      Bundle bundle1;
      List<String> list2;
      String str2;
      List<String> list1;
      CharSequence charSequence1;
      ComponentName componentName3;
      String str1;
      ComponentName componentName2;
      List<ComponentName> list;
      ComponentName componentName1;
      FactoryResetProtectionPolicy factoryResetProtectionPolicy;
      PasswordMetrics passwordMetrics;
      ComponentName componentName5;
      String str5;
      long l;
      String str6;
      boolean bool21 = false;
      boolean bool22 = false;
      boolean bool23 = false;
      boolean bool24 = false;
      boolean bool25 = false;
      boolean bool26 = false;
      boolean bool27 = false;
      boolean bool28 = false;
      boolean bool29 = false;
      boolean bool30 = false;
      boolean bool31 = false;
      boolean bool32 = false;
      boolean bool33 = false;
      boolean bool34 = false;
      boolean bool35 = false;
      boolean bool36 = false;
      boolean bool37 = false;
      boolean bool38 = false;
      boolean bool39 = false;
      boolean bool40 = false;
      boolean bool41 = false;
      boolean bool42 = false;
      boolean bool43 = false;
      boolean bool44 = false;
      boolean bool45 = false;
      boolean bool46 = false;
      boolean bool47 = false;
      boolean bool48 = false;
      boolean bool49 = false;
      boolean bool50 = false;
      boolean bool51 = false;
      boolean bool52 = false;
      boolean bool53 = false;
      boolean bool54 = false;
      boolean bool55 = false;
      boolean bool56 = false;
      boolean bool57 = false;
      boolean bool58 = false;
      boolean bool59 = false;
      boolean bool60 = false;
      boolean bool61 = false;
      boolean bool62 = false;
      boolean bool63 = false;
      boolean bool64 = false;
      boolean bool65 = false;
      boolean bool66 = false;
      boolean bool67 = false;
      boolean bool68 = false;
      boolean bool69 = false;
      boolean bool70 = false;
      boolean bool71 = false;
      boolean bool72 = false;
      boolean bool73 = false;
      boolean bool74 = false;
      boolean bool75 = false;
      boolean bool76 = false;
      boolean bool77 = false;
      boolean bool78 = false;
      boolean bool79 = false;
      boolean bool80 = false;
      boolean bool81 = false;
      boolean bool82 = false;
      boolean bool83 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 311:
          paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool20 = canProfileOwnerResetPasswordWhenLocked(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool20);
          return true;
        case 310:
          paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (paramParcel1.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          } else {
            componentName5 = null;
          } 
          setManagedProfileMaximumTimeOff(componentName5, paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        case 309:
          paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (paramParcel1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          l = getManagedProfileMaximumTimeOff((ComponentName)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 308:
          paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (paramParcel1.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          } else {
            componentName5 = null;
          } 
          if (paramParcel1.readInt() != 0)
            bool83 = true; 
          setPersonalAppsSuspended(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 307:
          paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (paramParcel1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          i15 = getPersonalAppsSuspendedReasons((ComponentName)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i15);
          return true;
        case 306:
          paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (paramParcel1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          bool19 = isCommonCriteriaModeEnabled((ComponentName)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool19);
          return true;
        case 305:
          paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (paramParcel1.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          } else {
            componentName5 = null;
          } 
          bool83 = bool21;
          if (paramParcel1.readInt() != 0)
            bool83 = true; 
          setCommonCriteriaModeEnabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 304:
          paramParcel1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (paramParcel1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          list10 = getUserControlDisabledPackages((ComponentName)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list10);
          return true;
        case 303:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list10.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list10);
          } else {
            componentName5 = null;
          } 
          setUserControlDisabledPackages(componentName5, list10.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 302:
          return onTransact$setKeyGrantForApp$((Parcel)list10, paramParcel2);
        case 301:
          return onTransact$startViewCalendarEventInManagedProfile$((Parcel)list10, paramParcel2);
        case 300:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool19 = isUnattendedManagedKiosk();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool19);
          return true;
        case 299:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool19 = isManagedKiosk();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool19);
          return true;
        case 298:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          list10 = getDefaultCrossProfilePackages();
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list10);
          return true;
        case 297:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          list10 = getAllCrossProfilePackages();
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list10);
          return true;
        case 296:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list10.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list10);
          } else {
            list10 = null;
          } 
          list10 = getCrossProfilePackages((ComponentName)list10);
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list10);
          return true;
        case 295:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list10.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list10);
          } else {
            componentName5 = null;
          } 
          setCrossProfilePackages(componentName5, list10.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 294:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          list10 = getCrossProfileCalendarPackagesForUser(list10.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list10);
          return true;
        case 293:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool19 = isPackageAllowedToAccessCalendarForUser(list10.readString(), list10.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool19);
          return true;
        case 292:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list10.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list10);
          } else {
            list10 = null;
          } 
          list10 = getCrossProfileCalendarPackages((ComponentName)list10);
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list10);
          return true;
        case 291:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list10.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list10);
          } else {
            componentName5 = null;
          } 
          setCrossProfileCalendarPackages(componentName5, list10.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 290:
          return onTransact$installUpdateFromFile$((Parcel)list10, paramParcel2);
        case 289:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list10.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list10);
          } else {
            componentName5 = null;
          } 
          markProfileOwnerOnOrganizationOwnedDevice(componentName5, list10.readInt());
          paramParcel2.writeNoException();
          return true;
        case 288:
          list10.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list10.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list10);
          } else {
            list10 = null;
          } 
          str4 = getGlobalPrivateDnsHost((ComponentName)list10);
          paramParcel2.writeNoException();
          paramParcel2.writeString(str4);
          return true;
        case 287:
          str4.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (str4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)str4);
          } else {
            str4 = null;
          } 
          i14 = getGlobalPrivateDnsMode((ComponentName)str4);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i14);
          return true;
        case 286:
          return onTransact$setGlobalPrivateDns$((Parcel)str4, paramParcel2);
        case 285:
          return onTransact$isMeteredDataDisabledPackageForUser$((Parcel)str4, paramParcel2);
        case 284:
          str4.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (str4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)str4);
          } else {
            str4 = null;
          } 
          bool18 = isOverrideApnEnabled((ComponentName)str4);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool18);
          return true;
        case 283:
          str4.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (str4.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)str4);
          } else {
            componentName5 = null;
          } 
          bool83 = bool22;
          if (str4.readInt() != 0)
            bool83 = true; 
          setOverrideApnsEnabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 282:
          str4.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (str4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)str4);
          } else {
            str4 = null;
          } 
          list9 = getOverrideApns((ComponentName)str4);
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list9);
          return true;
        case 281:
          list9.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list9.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list9);
          } else {
            componentName5 = null;
          } 
          bool18 = removeOverrideApn(componentName5, list9.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool18);
          return true;
        case 280:
          return onTransact$updateOverrideApn$((Parcel)list9, paramParcel2);
        case 279:
          list9.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list9.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list9);
          } else {
            componentName5 = null;
          } 
          if (list9.readInt() != 0) {
            ApnSetting apnSetting = (ApnSetting)ApnSetting.CREATOR.createFromParcel((Parcel)list9);
          } else {
            list9 = null;
          } 
          i13 = addOverrideApn(componentName5, (ApnSetting)list9);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i13);
          return true;
        case 278:
          list9.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list9.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list9);
          } else {
            list9 = null;
          } 
          list9 = (List)getMeteredDataDisabledPackages((ComponentName)list9);
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list9);
          return true;
        case 277:
          list9.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list9.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list9);
          } else {
            componentName5 = null;
          } 
          list9 = (List)setMeteredDataDisabledPackages(componentName5, list9.createStringArrayList());
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list9);
          return true;
        case 276:
          list9.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list9.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list9);
          } else {
            list9 = null;
          } 
          charSequence3 = getEndUserSessionMessage((ComponentName)list9);
          paramParcel2.writeNoException();
          if (charSequence3 != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence3, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 275:
          charSequence3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence3.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence3);
          } else {
            charSequence3 = null;
          } 
          charSequence3 = getStartUserSessionMessage((ComponentName)charSequence3);
          paramParcel2.writeNoException();
          if (charSequence3 != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence3, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 274:
          charSequence3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence3.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence3);
          } else {
            componentName5 = null;
          } 
          if (charSequence3.readInt() != 0) {
            charSequence3 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)charSequence3);
          } else {
            charSequence3 = null;
          } 
          setEndUserSessionMessage(componentName5, charSequence3);
          paramParcel2.writeNoException();
          return true;
        case 273:
          charSequence3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence3.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence3);
          } else {
            componentName5 = null;
          } 
          if (charSequence3.readInt() != 0) {
            charSequence3 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)charSequence3);
          } else {
            charSequence3 = null;
          } 
          setStartUserSessionMessage(componentName5, charSequence3);
          paramParcel2.writeNoException();
          return true;
        case 272:
          charSequence3.enforceInterface("android.app.admin.IDevicePolicyManager");
          persistableBundle = getTransferOwnershipBundle();
          paramParcel2.writeNoException();
          if (persistableBundle != null) {
            paramParcel2.writeInt(1);
            persistableBundle.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 271:
          return onTransact$transferOwnership$((Parcel)persistableBundle, paramParcel2);
        case 270:
          return onTransact$getDisallowedSystemApps$((Parcel)persistableBundle, paramParcel2);
        case 269:
          persistableBundle.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool17 = isLogoutEnabled();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 268:
          persistableBundle.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (persistableBundle.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)persistableBundle);
          } else {
            componentName5 = null;
          } 
          bool83 = bool23;
          if (persistableBundle.readInt() != 0)
            bool83 = true; 
          setLogoutEnabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 267:
          return onTransact$clearApplicationUserData$((Parcel)persistableBundle, paramParcel2);
        case 266:
          persistableBundle.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (persistableBundle.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)persistableBundle);
          } else {
            persistableBundle = null;
          } 
          stringParceledListSlice = getOwnerInstalledCaCerts((UserHandle)persistableBundle);
          paramParcel2.writeNoException();
          if (stringParceledListSlice != null) {
            paramParcel2.writeInt(1);
            stringParceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 265:
          stringParceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool17 = isCurrentInputMethodSetByOwner();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 264:
          return onTransact$resetPasswordWithToken$((Parcel)stringParceledListSlice, paramParcel2);
        case 263:
          stringParceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (stringParceledListSlice.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)stringParceledListSlice);
          } else {
            stringParceledListSlice = null;
          } 
          bool17 = isResetPasswordTokenActive((ComponentName)stringParceledListSlice);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 262:
          stringParceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (stringParceledListSlice.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)stringParceledListSlice);
          } else {
            stringParceledListSlice = null;
          } 
          bool17 = clearResetPasswordToken((ComponentName)stringParceledListSlice);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 261:
          stringParceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (stringParceledListSlice.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)stringParceledListSlice);
          } else {
            componentName5 = null;
          } 
          bool17 = setResetPasswordToken(componentName5, stringParceledListSlice.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 260:
          stringParceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          l = getLastNetworkLogRetrievalTime();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 259:
          stringParceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          l = getLastBugReportRequestTime();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 258:
          stringParceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          l = getLastSecurityLogRetrievalTime();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 257:
          stringParceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (stringParceledListSlice.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)stringParceledListSlice);
          } else {
            stringParceledListSlice = null;
          } 
          bool17 = isEphemeralUser((ComponentName)stringParceledListSlice);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 256:
          stringParceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (stringParceledListSlice.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)stringParceledListSlice);
          } else {
            stringParceledListSlice = null;
          } 
          list8 = getBindDeviceAdminTargetUsers((ComponentName)stringParceledListSlice);
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list8);
          return true;
        case 255:
          return onTransact$bindDeviceAdminServiceAsUser$((Parcel)list8, paramParcel2);
        case 254:
          return onTransact$retrieveNetworkLogs$((Parcel)list8, paramParcel2);
        case 253:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list8.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list8);
          } else {
            componentName5 = null;
          } 
          bool17 = isNetworkLoggingEnabled(componentName5, list8.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 252:
          return onTransact$setNetworkLoggingEnabled$((Parcel)list8, paramParcel2);
        case 251:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list8.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list8);
          } else {
            list8 = null;
          } 
          bool17 = isBackupServiceEnabled((ComponentName)list8);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 250:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list8.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list8);
          } else {
            componentName5 = null;
          } 
          bool83 = bool24;
          if (list8.readInt() != 0)
            bool83 = true; 
          setBackupServiceEnabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 249:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          forceUpdateUserSetupComplete();
          paramParcel2.writeNoException();
          return true;
        case 248:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          setDeviceProvisioningConfigApplied();
          paramParcel2.writeNoException();
          return true;
        case 247:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool17 = isDeviceProvisioningConfigApplied();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 246:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool17 = isDeviceProvisioned();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 245:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          uninstallPackageWithActiveAdmins(list8.readString());
          paramParcel2.writeNoException();
          return true;
        case 244:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool17 = isUninstallInQueue(list8.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 243:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          l = forceSecurityLogs();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 242:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          l = forceNetworkLogs();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 241:
          list8.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list8.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list8);
          } else {
            list8 = null;
          } 
          parceledListSlice = retrievePreRebootSecurityLogs((ComponentName)list8);
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 240:
          parceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (parceledListSlice.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parceledListSlice);
          } else {
            parceledListSlice = null;
          } 
          parceledListSlice = retrieveSecurityLogs((ComponentName)parceledListSlice);
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 239:
          parceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (parceledListSlice.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parceledListSlice);
          } else {
            parceledListSlice = null;
          } 
          bool17 = isSecurityLoggingEnabled((ComponentName)parceledListSlice);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 238:
          parceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (parceledListSlice.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parceledListSlice);
          } else {
            componentName5 = null;
          } 
          bool83 = bool25;
          if (parceledListSlice.readInt() != 0)
            bool83 = true; 
          setSecurityLoggingEnabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 237:
          parceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool17 = isAffiliatedUser();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool17);
          return true;
        case 236:
          parceledListSlice.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (parceledListSlice.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parceledListSlice);
          } else {
            parceledListSlice = null;
          } 
          list7 = getAffiliationIds((ComponentName)parceledListSlice);
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list7);
          return true;
        case 235:
          list7.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list7.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list7);
          } else {
            componentName5 = null;
          } 
          setAffiliationIds(componentName5, list7.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 234:
          list7.enforceInterface("android.app.admin.IDevicePolicyManager");
          setUserProvisioningState(list7.readInt(), list7.readInt());
          paramParcel2.writeNoException();
          return true;
        case 233:
          list7.enforceInterface("android.app.admin.IDevicePolicyManager");
          i12 = getUserProvisioningState();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i12);
          return true;
        case 232:
          list7.enforceInterface("android.app.admin.IDevicePolicyManager");
          charSequence2 = getOrganizationNameForUser(list7.readInt());
          paramParcel2.writeNoException();
          if (charSequence2 != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence2, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 231:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          charSequence2 = getDeviceOwnerOrganizationName();
          paramParcel2.writeNoException();
          if (charSequence2 != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence2, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 230:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            charSequence2 = null;
          } 
          charSequence2 = getOrganizationName((ComponentName)charSequence2);
          paramParcel2.writeNoException();
          if (charSequence2 != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence2, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 229:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            componentName5 = null;
          } 
          if (charSequence2.readInt() != 0) {
            charSequence2 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            charSequence2 = null;
          } 
          setOrganizationName(componentName5, charSequence2);
          paramParcel2.writeNoException();
          return true;
        case 228:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          i12 = getOrganizationColorForUser(charSequence2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i12);
          return true;
        case 227:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            charSequence2 = null;
          } 
          i12 = getOrganizationColor((ComponentName)charSequence2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i12);
          return true;
        case 226:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          setOrganizationColorForUser(charSequence2.readInt(), charSequence2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 225:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            componentName5 = null;
          } 
          setOrganizationColor(componentName5, charSequence2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 224:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool16 = isSeparateProfileChallengeAllowed(charSequence2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool16);
          return true;
        case 223:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            componentName5 = null;
          } 
          charSequence2 = getLongSupportMessageForUser(componentName5, charSequence2.readInt());
          paramParcel2.writeNoException();
          if (charSequence2 != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence2, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 222:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            componentName5 = null;
          } 
          charSequence2 = getShortSupportMessageForUser(componentName5, charSequence2.readInt());
          paramParcel2.writeNoException();
          if (charSequence2 != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence2, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 221:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            charSequence2 = null;
          } 
          charSequence2 = getLongSupportMessage((ComponentName)charSequence2);
          paramParcel2.writeNoException();
          if (charSequence2 != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence2, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 220:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            componentName5 = null;
          } 
          if (charSequence2.readInt() != 0) {
            charSequence2 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            charSequence2 = null;
          } 
          setLongSupportMessage(componentName5, charSequence2);
          paramParcel2.writeNoException();
          return true;
        case 219:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            charSequence2 = null;
          } 
          charSequence2 = getShortSupportMessage((ComponentName)charSequence2);
          paramParcel2.writeNoException();
          if (charSequence2 != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence2, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 218:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            componentName5 = null;
          } 
          if (charSequence2.readInt() != 0) {
            charSequence2 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            charSequence2 = null;
          } 
          setShortSupportMessage(componentName5, charSequence2);
          paramParcel2.writeNoException();
          return true;
        case 217:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            charSequence2 = null;
          } 
          reboot((ComponentName)charSequence2);
          paramParcel2.writeNoException();
          return true;
        case 216:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            charSequence2 = null;
          } 
          charSequence2 = getWifiMacAddress((ComponentName)charSequence2);
          paramParcel2.writeNoException();
          paramParcel2.writeString((String)charSequence2);
          return true;
        case 215:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            charSequence2 = null;
          } 
          bool16 = isSystemOnlyUser((ComponentName)charSequence2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool16);
          return true;
        case 214:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            charSequence2 = null;
          } 
          bool16 = isManagedProfile((ComponentName)charSequence2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool16);
          return true;
        case 213:
          charSequence2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence2.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence2);
          } else {
            componentName5 = null;
          } 
          list6 = getKeepUninstalledPackages(componentName5, charSequence2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list6);
          return true;
        case 212:
          return onTransact$setKeepUninstalledPackages$((Parcel)list6, paramParcel2);
        case 211:
          list6.enforceInterface("android.app.admin.IDevicePolicyManager");
          i11 = checkProvisioningPreCondition(list6.readString(), list6.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i11);
          return true;
        case 210:
          list6.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool15 = isProvisioningAllowed(list6.readString(), list6.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool15);
          return true;
        case 209:
          return onTransact$getPermissionGrantState$((Parcel)list6, paramParcel2);
        case 208:
          return onTransact$setPermissionGrantState$((Parcel)list6, paramParcel2);
        case 207:
          list6.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list6.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list6);
          } else {
            list6 = null;
          } 
          i10 = getPermissionPolicy((ComponentName)list6);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i10);
          return true;
        case 206:
          return onTransact$setPermissionPolicy$((Parcel)list6, paramParcel2);
        case 205:
          list6.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list6.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list6);
          } else {
            list6 = null;
          } 
          systemUpdateInfo = getPendingSystemUpdate((ComponentName)list6);
          paramParcel2.writeNoException();
          if (systemUpdateInfo != null) {
            paramParcel2.writeInt(1);
            systemUpdateInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 204:
          systemUpdateInfo.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdateInfo.readInt() != 0) {
            systemUpdateInfo = (SystemUpdateInfo)SystemUpdateInfo.CREATOR.createFromParcel((Parcel)systemUpdateInfo);
          } else {
            systemUpdateInfo = null;
          } 
          notifyPendingSystemUpdate(systemUpdateInfo);
          paramParcel2.writeNoException();
          return true;
        case 203:
          systemUpdateInfo.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool14 = getDoNotAskCredentialsOnBoot();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 202:
          systemUpdateInfo.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdateInfo.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdateInfo);
          } else {
            componentName5 = null;
          } 
          bool83 = bool26;
          if (systemUpdateInfo.readInt() != 0)
            bool83 = true; 
          bool14 = setStatusBarDisabled(componentName5, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 201:
          systemUpdateInfo.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdateInfo.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdateInfo);
          } else {
            componentName5 = null;
          } 
          bool83 = bool27;
          if (systemUpdateInfo.readInt() != 0)
            bool83 = true; 
          bool14 = setKeyguardDisabled(componentName5, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 200:
          systemUpdateInfo.enforceInterface("android.app.admin.IDevicePolicyManager");
          clearSystemUpdatePolicyFreezePeriodRecord();
          paramParcel2.writeNoException();
          return true;
        case 199:
          systemUpdateInfo.enforceInterface("android.app.admin.IDevicePolicyManager");
          systemUpdatePolicy = getSystemUpdatePolicy();
          paramParcel2.writeNoException();
          if (systemUpdatePolicy != null) {
            paramParcel2.writeInt(1);
            systemUpdatePolicy.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 198:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdatePolicy.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            componentName5 = null;
          } 
          if (systemUpdatePolicy.readInt() != 0) {
            systemUpdatePolicy = (SystemUpdatePolicy)SystemUpdatePolicy.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            systemUpdatePolicy = null;
          } 
          setSystemUpdatePolicy(componentName5, systemUpdatePolicy);
          paramParcel2.writeNoException();
          return true;
        case 197:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdatePolicy.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            componentName5 = null;
          } 
          if (systemUpdatePolicy.readInt() != 0) {
            Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            systemUpdatePolicy = null;
          } 
          setUserIcon(componentName5, (Bitmap)systemUpdatePolicy);
          paramParcel2.writeNoException();
          return true;
        case 196:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdatePolicy.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            componentName5 = null;
          } 
          bool14 = isRemovingAdmin(componentName5, systemUpdatePolicy.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 195:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdatePolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            systemUpdatePolicy = null;
          } 
          bool14 = getForceEphemeralUsers((ComponentName)systemUpdatePolicy);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 194:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdatePolicy.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            componentName5 = null;
          } 
          bool83 = bool28;
          if (systemUpdatePolicy.readInt() != 0)
            bool83 = true; 
          setForceEphemeralUsers(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 193:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdatePolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            systemUpdatePolicy = null;
          } 
          bool14 = getAutoTimeZoneEnabled((ComponentName)systemUpdatePolicy);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 192:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdatePolicy.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            componentName5 = null;
          } 
          bool83 = bool29;
          if (systemUpdatePolicy.readInt() != 0)
            bool83 = true; 
          setAutoTimeZoneEnabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 191:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdatePolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            systemUpdatePolicy = null;
          } 
          bool14 = getAutoTimeEnabled((ComponentName)systemUpdatePolicy);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 190:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdatePolicy.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            componentName5 = null;
          } 
          bool83 = bool30;
          if (systemUpdatePolicy.readInt() != 0)
            bool83 = true; 
          setAutoTimeEnabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 189:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool14 = getAutoTimeRequired();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 188:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdatePolicy.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            componentName5 = null;
          } 
          bool83 = bool31;
          if (systemUpdatePolicy.readInt() != 0)
            bool83 = true; 
          setAutoTimeRequired(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 187:
          systemUpdatePolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (systemUpdatePolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)systemUpdatePolicy);
          } else {
            systemUpdatePolicy = null;
          } 
          list5 = getCrossProfileWidgetProviders((ComponentName)systemUpdatePolicy);
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list5);
          return true;
        case 186:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          bool14 = removeCrossProfileWidgetProvider(componentName5, list5.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 185:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          bool14 = addCrossProfileWidgetProvider(componentName5, list5.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 184:
          return onTransact$getTrustAgentConfiguration$((Parcel)list5, paramParcel2);
        case 183:
          return onTransact$setTrustAgentConfiguration$((Parcel)list5, paramParcel2);
        case 182:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool14 = getBluetoothContactSharingDisabledForUser(list5.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 181:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            list5 = null;
          } 
          bool14 = getBluetoothContactSharingDisabled((ComponentName)list5);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 180:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          bool83 = bool32;
          if (list5.readInt() != 0)
            bool83 = true; 
          setBluetoothContactSharingDisabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 179:
          return onTransact$startManagedQuickContact$((Parcel)list5, paramParcel2);
        case 178:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool14 = getCrossProfileContactsSearchDisabledForUser(list5.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 177:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            list5 = null;
          } 
          bool14 = getCrossProfileContactsSearchDisabled((ComponentName)list5);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 176:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          bool83 = bool33;
          if (list5.readInt() != 0)
            bool83 = true; 
          setCrossProfileContactsSearchDisabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 175:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool14 = getCrossProfileCallerIdDisabledForUser(list5.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 174:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            list5 = null;
          } 
          bool14 = getCrossProfileCallerIdDisabled((ComponentName)list5);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 173:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          bool83 = bool34;
          if (list5.readInt() != 0)
            bool83 = true; 
          setCrossProfileCallerIdDisabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 172:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          bool14 = isUninstallBlocked(componentName5, list5.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 171:
          return onTransact$setUninstallBlocked$((Parcel)list5, paramParcel2);
        case 170:
          return onTransact$notifyLockTaskModeChanged$((Parcel)list5, paramParcel2);
        case 169:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            list5 = null;
          } 
          bool14 = isMasterVolumeMuted((ComponentName)list5);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 168:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          bool83 = bool35;
          if (list5.readInt() != 0)
            bool83 = true; 
          setMasterVolumeMuted(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 167:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          bool14 = setTimeZone(componentName5, list5.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 166:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          bool14 = setTime(componentName5, list5.readLong());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 165:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          bool83 = bool36;
          if (list5.readInt() != 0)
            bool83 = true; 
          setLocationEnabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 164:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            list5 = null;
          } 
          bool14 = hasLockdownAdminConfiguredNetworks((ComponentName)list5);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 163:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          bool83 = bool37;
          if (list5.readInt() != 0)
            bool83 = true; 
          setConfiguredNetworksLockdownState(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 162:
          return onTransact$setSecureSetting$((Parcel)list5, paramParcel2);
        case 161:
          return onTransact$setSystemSetting$((Parcel)list5, paramParcel2);
        case 160:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          setGlobalSetting(componentName5, list5.readString(), list5.readString());
          paramParcel2.writeNoException();
          return true;
        case 159:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            list5 = null;
          } 
          i9 = getLockTaskFeatures((ComponentName)list5);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i9);
          return true;
        case 158:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            componentName5 = null;
          } 
          setLockTaskFeatures(componentName5, list5.readInt());
          paramParcel2.writeNoException();
          return true;
        case 157:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool13 = isLockTaskPermitted(list5.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool13);
          return true;
        case 156:
          list5.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list5);
          } else {
            list5 = null;
          } 
          arrayOfString = getLockTaskPackages((ComponentName)list5);
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString);
          return true;
        case 155:
          arrayOfString.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (arrayOfString.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString);
          } else {
            componentName5 = null;
          } 
          setLockTaskPackages(componentName5, arrayOfString.createStringArray());
          paramParcel2.writeNoException();
          return true;
        case 154:
          arrayOfString.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (arrayOfString.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)arrayOfString);
          } else {
            arrayOfString = null;
          } 
          bool13 = isSecondaryLockscreenEnabled((UserHandle)arrayOfString);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool13);
          return true;
        case 153:
          arrayOfString.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (arrayOfString.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString);
          } else {
            componentName5 = null;
          } 
          bool83 = bool38;
          if (arrayOfString.readInt() != 0)
            bool83 = true; 
          setSecondaryLockscreenEnabled(componentName5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 152:
          arrayOfString.enforceInterface("android.app.admin.IDevicePolicyManager");
          i8 = arrayOfString.readInt();
          bool83 = bool39;
          if (arrayOfString.readInt() != 0)
            bool83 = true; 
          arrayOfString = getAccountTypesWithManagementDisabledAsUser(i8, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString);
          return true;
        case 151:
          arrayOfString.enforceInterface("android.app.admin.IDevicePolicyManager");
          arrayOfString = getAccountTypesWithManagementDisabled();
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString);
          return true;
        case 150:
          return onTransact$setAccountManagementDisabled$((Parcel)arrayOfString, paramParcel2);
        case 149:
          arrayOfString.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (arrayOfString.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString);
          } else {
            componentName5 = null;
          } 
          bool12 = installExistingPackage(componentName5, arrayOfString.readString(), arrayOfString.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool12);
          return true;
        case 148:
          arrayOfString.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (arrayOfString.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString);
          } else {
            componentName5 = null;
          } 
          str6 = arrayOfString.readString();
          if (arrayOfString.readInt() != 0) {
            Intent intent1 = (Intent)Intent.CREATOR.createFromParcel((Parcel)arrayOfString);
          } else {
            arrayOfString = null;
          } 
          i7 = enableSystemAppWithIntent(componentName5, str6, (Intent)arrayOfString);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i7);
          return true;
        case 147:
          arrayOfString.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (arrayOfString.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString);
          } else {
            componentName5 = null;
          } 
          enableSystemApp(componentName5, arrayOfString.readString(), arrayOfString.readString());
          paramParcel2.writeNoException();
          return true;
        case 146:
          arrayOfString.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (arrayOfString.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString);
          } else {
            arrayOfString = null;
          } 
          list4 = getSecondaryUsers((ComponentName)arrayOfString);
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list4);
          return true;
        case 145:
          list4.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list4);
          } else {
            list4 = null;
          } 
          i7 = logoutUser((ComponentName)list4);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i7);
          return true;
        case 144:
          list4.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list4.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list4);
          } else {
            componentName5 = null;
          } 
          if (list4.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)list4);
          } else {
            list4 = null;
          } 
          i7 = stopUser(componentName5, (UserHandle)list4);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i7);
          return true;
        case 143:
          list4.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list4.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list4);
          } else {
            componentName5 = null;
          } 
          if (list4.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)list4);
          } else {
            list4 = null;
          } 
          i7 = startUserInBackground(componentName5, (UserHandle)list4);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i7);
          return true;
        case 142:
          list4.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list4.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list4);
          } else {
            componentName5 = null;
          } 
          if (list4.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)list4);
          } else {
            list4 = null;
          } 
          bool11 = switchUser(componentName5, (UserHandle)list4);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 141:
          list4.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list4.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list4);
          } else {
            componentName5 = null;
          } 
          if (list4.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)list4);
          } else {
            list4 = null;
          } 
          bool11 = removeUser(componentName5, (UserHandle)list4);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 140:
          return onTransact$createAndManageUser$((Parcel)list4, paramParcel2);
        case 139:
          return onTransact$isApplicationHidden$((Parcel)list4, paramParcel2);
        case 138:
          return onTransact$setApplicationHidden$((Parcel)list4, paramParcel2);
        case 137:
          list4.enforceInterface("android.app.admin.IDevicePolicyManager");
          intent = createAdminSupportIntent(list4.readString());
          paramParcel2.writeNoException();
          if (intent != null) {
            paramParcel2.writeInt(1);
            intent.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 136:
          intent.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool11 = isNotificationListenerServicePermitted(intent.readString(), intent.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 135:
          intent.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (intent.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)intent);
          } else {
            intent = null;
          } 
          list3 = getPermittedCrossProfileNotificationListeners((ComponentName)intent);
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list3);
          return true;
        case 134:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list3.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list3);
          } else {
            componentName5 = null;
          } 
          bool11 = setPermittedCrossProfileNotificationListeners(componentName5, list3.createStringArrayList());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 133:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list3.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list3);
          } else {
            componentName5 = null;
          } 
          bool11 = isInputMethodPermittedByAdmin(componentName5, list3.readString(), list3.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 132:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          list3 = getPermittedInputMethodsForCurrentUser();
          paramParcel2.writeNoException();
          paramParcel2.writeList(list3);
          return true;
        case 131:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list3.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list3);
          } else {
            list3 = null;
          } 
          list3 = getPermittedInputMethods((ComponentName)list3);
          paramParcel2.writeNoException();
          paramParcel2.writeList(list3);
          return true;
        case 130:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list3.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list3);
          } else {
            componentName5 = null;
          } 
          bool11 = setPermittedInputMethods(componentName5, list3.readArrayList(getClass().getClassLoader()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 129:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list3.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list3);
          } else {
            componentName5 = null;
          } 
          bool11 = isAccessibilityServicePermittedByAdmin(componentName5, list3.readString(), list3.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 128:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          list3 = getPermittedAccessibilityServicesForUser(list3.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeList(list3);
          return true;
        case 127:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list3.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list3);
          } else {
            list3 = null;
          } 
          list3 = getPermittedAccessibilityServices((ComponentName)list3);
          paramParcel2.writeNoException();
          paramParcel2.writeList(list3);
          return true;
        case 126:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list3.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list3);
          } else {
            componentName5 = null;
          } 
          bool11 = setPermittedAccessibilityServices(componentName5, list3.readArrayList(getClass().getClassLoader()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 125:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list3.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list3);
          } else {
            list3 = null;
          } 
          clearCrossProfileIntentFilters((ComponentName)list3);
          paramParcel2.writeNoException();
          return true;
        case 124:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list3.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list3);
          } else {
            componentName5 = null;
          } 
          if (list3.readInt() != 0) {
            IntentFilter intentFilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel((Parcel)list3);
          } else {
            str6 = null;
          } 
          addCrossProfileIntentFilter(componentName5, (IntentFilter)str6, list3.readInt());
          paramParcel2.writeNoException();
          return true;
        case 123:
          list3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list3.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list3);
          } else {
            componentName5 = null;
          } 
          if (list3.readInt() != 0) {
            bool83 = true;
          } else {
            bool83 = false;
          } 
          bundle2 = getUserRestrictions(componentName5, bool83);
          paramParcel2.writeNoException();
          if (bundle2 != null) {
            paramParcel2.writeInt(1);
            bundle2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 122:
          return onTransact$setUserRestriction$((Parcel)bundle2, paramParcel2);
        case 121:
          bundle2.enforceInterface("android.app.admin.IDevicePolicyManager");
          componentName4 = getRestrictionsProvider(bundle2.readInt());
          paramParcel2.writeNoException();
          if (componentName4 != null) {
            paramParcel2.writeInt(1);
            componentName4.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 120:
          componentName4.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (componentName4.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName4);
          } else {
            componentName5 = null;
          } 
          if (componentName4.readInt() != 0) {
            componentName4 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName4);
          } else {
            componentName4 = null;
          } 
          setRestrictionsProvider(componentName5, componentName4);
          paramParcel2.writeNoException();
          return true;
        case 119:
          componentName4.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool11 = isCallerApplicationRestrictionsManagingPackage(componentName4.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 118:
          componentName4.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (componentName4.readInt() != 0) {
            componentName4 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName4);
          } else {
            componentName4 = null;
          } 
          str3 = getApplicationRestrictionsManagingPackage(componentName4);
          paramParcel2.writeNoException();
          paramParcel2.writeString(str3);
          return true;
        case 117:
          str3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (str3.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)str3);
          } else {
            componentName5 = null;
          } 
          bool11 = setApplicationRestrictionsManagingPackage(componentName5, str3.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 116:
          str3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (str3.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)str3);
          } else {
            componentName5 = null;
          } 
          bundle1 = getApplicationRestrictions(componentName5, str3.readString(), str3.readString());
          paramParcel2.writeNoException();
          if (bundle1 != null) {
            paramParcel2.writeInt(1);
            bundle1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 115:
          return onTransact$setApplicationRestrictions$((Parcel)bundle1, paramParcel2);
        case 114:
          bundle1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (bundle1.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)bundle1);
          } else {
            componentName5 = null;
          } 
          str6 = bundle1.readString();
          bool83 = bool40;
          if (bundle1.readInt() != 0)
            bool83 = true; 
          setDefaultSmsApplication(componentName5, str6, bool83);
          paramParcel2.writeNoException();
          return true;
        case 113:
          bundle1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (bundle1.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)bundle1);
          } else {
            componentName5 = null;
          } 
          clearPackagePersistentPreferredActivities(componentName5, bundle1.readString());
          paramParcel2.writeNoException();
          return true;
        case 112:
          bundle1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (bundle1.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)bundle1);
          } else {
            componentName5 = null;
          } 
          if (bundle1.readInt() != 0) {
            IntentFilter intentFilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel((Parcel)bundle1);
          } else {
            str6 = null;
          } 
          if (bundle1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)bundle1);
          } else {
            bundle1 = null;
          } 
          addPersistentPreferredActivity(componentName5, (IntentFilter)str6, (ComponentName)bundle1);
          paramParcel2.writeNoException();
          return true;
        case 111:
          bundle1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (bundle1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)bundle1);
          } else {
            bundle1 = null;
          } 
          list2 = getAlwaysOnVpnLockdownWhitelist((ComponentName)bundle1);
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list2);
          return true;
        case 110:
          list2.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool11 = isAlwaysOnVpnLockdownEnabledForUser(list2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 109:
          list2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list2);
          } else {
            list2 = null;
          } 
          bool11 = isAlwaysOnVpnLockdownEnabled((ComponentName)list2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 108:
          list2.enforceInterface("android.app.admin.IDevicePolicyManager");
          str2 = getAlwaysOnVpnPackageForUser(list2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 107:
          str2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (str2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str2 = null;
          } 
          str2 = getAlwaysOnVpnPackage((ComponentName)str2);
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 106:
          return onTransact$setAlwaysOnVpnPackage$((Parcel)str2, paramParcel2);
        case 105:
          str2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (str2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str2 = null;
          } 
          str2 = getCertInstallerPackage((ComponentName)str2);
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 104:
          str2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (str2.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)str2);
          } else {
            componentName5 = null;
          } 
          setCertInstallerPackage(componentName5, str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 103:
          str2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (str2.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)str2);
          } else {
            componentName5 = null;
          } 
          list1 = getDelegatePackages(componentName5, str2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list1);
          return true;
        case 102:
          list1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list1.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            componentName5 = null;
          } 
          list1 = getDelegatedScopes(componentName5, list1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list1);
          return true;
        case 101:
          list1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list1.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            componentName5 = null;
          } 
          setDelegatedScopes(componentName5, list1.readString(), list1.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 100:
          return onTransact$choosePrivateKeyAlias$((Parcel)list1, paramParcel2);
        case 99:
          return onTransact$setKeyPairCertificate$((Parcel)list1, paramParcel2);
        case 98:
          return onTransact$generateKeyPair$((Parcel)list1, paramParcel2);
        case 97:
          list1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list1.readInt() != 0) {
            componentName5 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            componentName5 = null;
          } 
          bool11 = removeKeyPair(componentName5, list1.readString(), list1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 96:
          return onTransact$installKeyPair$((Parcel)list1, paramParcel2);
        case 95:
          list1.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool11 = isCaCertApproved(list1.readString(), list1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 94:
          list1.enforceInterface("android.app.admin.IDevicePolicyManager");
          str5 = list1.readString();
          i6 = list1.readInt();
          bool83 = bool41;
          if (list1.readInt() != 0)
            bool83 = true; 
          bool10 = approveCaCert(str5, i6, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 93:
          list1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            str5 = null;
          } 
          enforceCanManageCaCerts((ComponentName)str5, list1.readString());
          paramParcel2.writeNoException();
          return true;
        case 92:
          list1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            str5 = null;
          } 
          uninstallCaCerts((ComponentName)str5, list1.readString(), list1.createStringArray());
          paramParcel2.writeNoException();
          return true;
        case 91:
          list1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            str5 = null;
          } 
          bool10 = installCaCert((ComponentName)str5, list1.readString(), list1.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 90:
          list1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            str5 = null;
          } 
          bool10 = isPackageSuspended((ComponentName)str5, list1.readString(), list1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 89:
          return onTransact$setPackagesSuspended$((Parcel)list1, paramParcel2);
        case 88:
          list1.enforceInterface("android.app.admin.IDevicePolicyManager");
          charSequence1 = getDeviceOwnerLockScreenInfo();
          paramParcel2.writeNoException();
          if (charSequence1 != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence1, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 87:
          charSequence1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence1);
          } else {
            str5 = null;
          } 
          if (charSequence1.readInt() != 0) {
            charSequence1 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)charSequence1);
          } else {
            charSequence1 = null;
          } 
          setDeviceOwnerLockScreenInfo((ComponentName)str5, charSequence1);
          paramParcel2.writeNoException();
          return true;
        case 86:
          charSequence1.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool10 = checkDeviceIdentifierAccess(charSequence1.readString(), charSequence1.readInt(), charSequence1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 85:
          charSequence1.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool10 = isOrganizationOwnedDeviceWithManagedProfile();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 84:
          charSequence1.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool10 = hasUserSetupCompleted();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 83:
          charSequence1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence1);
          } else {
            charSequence1 = null;
          } 
          clearProfileOwner((ComponentName)charSequence1);
          paramParcel2.writeNoException();
          return true;
        case 82:
          charSequence1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence1);
          } else {
            str5 = null;
          } 
          setProfileName((ComponentName)str5, charSequence1.readString());
          paramParcel2.writeNoException();
          return true;
        case 81:
          charSequence1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)charSequence1);
          } else {
            charSequence1 = null;
          } 
          setProfileEnabled((ComponentName)charSequence1);
          paramParcel2.writeNoException();
          return true;
        case 80:
          charSequence1.enforceInterface("android.app.admin.IDevicePolicyManager");
          charSequence1 = getProfileOwnerName(charSequence1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeString((String)charSequence1);
          return true;
        case 79:
          charSequence1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (charSequence1.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)charSequence1);
          } else {
            charSequence1 = null;
          } 
          componentName3 = getProfileOwnerOrDeviceOwnerSupervisionComponent((UserHandle)charSequence1);
          paramParcel2.writeNoException();
          if (componentName3 != null) {
            paramParcel2.writeInt(1);
            componentName3.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 78:
          componentName3.enforceInterface("android.app.admin.IDevicePolicyManager");
          componentName3 = getProfileOwner(componentName3.readInt());
          paramParcel2.writeNoException();
          if (componentName3 != null) {
            paramParcel2.writeInt(1);
            componentName3.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 77:
          componentName3.enforceInterface("android.app.admin.IDevicePolicyManager");
          componentName3 = getProfileOwnerAsUser(componentName3.readInt());
          paramParcel2.writeNoException();
          if (componentName3 != null) {
            paramParcel2.writeInt(1);
            componentName3.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 76:
          componentName3.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (componentName3.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName3);
          } else {
            str5 = null;
          } 
          bool10 = setProfileOwner((ComponentName)str5, componentName3.readString(), componentName3.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 75:
          componentName3.enforceInterface("android.app.admin.IDevicePolicyManager");
          i5 = getDeviceOwnerUserId();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i5);
          return true;
        case 74:
          componentName3.enforceInterface("android.app.admin.IDevicePolicyManager");
          clearDeviceOwner(componentName3.readString());
          paramParcel2.writeNoException();
          return true;
        case 73:
          componentName3.enforceInterface("android.app.admin.IDevicePolicyManager");
          str1 = getDeviceOwnerName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 72:
          str1.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool9 = hasDeviceOwner();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 71:
          str1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (str1.readInt() != 0) {
            bool83 = true;
          } else {
            bool83 = false;
          } 
          componentName2 = getDeviceOwnerComponent(bool83);
          paramParcel2.writeNoException();
          if (componentName2 != null) {
            paramParcel2.writeInt(1);
            componentName2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 70:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (componentName2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName2);
          } else {
            str5 = null;
          } 
          bool9 = setDeviceOwner((ComponentName)str5, componentName2.readString(), componentName2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 69:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          reportKeyguardSecured(componentName2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 68:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          reportKeyguardDismissed(componentName2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 67:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          reportSuccessfulBiometricAttempt(componentName2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 66:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          reportFailedBiometricAttempt(componentName2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 65:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          reportSuccessfulPasswordAttempt(componentName2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 64:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          reportFailedPasswordAttempt(componentName2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 63:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          reportPasswordChanged(componentName2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 62:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (componentName2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName2);
          } else {
            str5 = null;
          } 
          bool9 = hasGrantedPolicy((ComponentName)str5, componentName2.readInt(), componentName2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 61:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (componentName2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName2);
          } else {
            str5 = null;
          } 
          forceRemoveActiveAdmin((ComponentName)str5, componentName2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 60:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (componentName2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName2);
          } else {
            str5 = null;
          } 
          removeActiveAdmin((ComponentName)str5, componentName2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 59:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (componentName2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName2);
          } else {
            str5 = null;
          } 
          if (componentName2.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel((Parcel)componentName2);
          } else {
            str6 = null;
          } 
          getRemoveWarning((ComponentName)str5, (RemoteCallback)str6, componentName2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 58:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool9 = packageHasActiveAdmins(componentName2.readString(), componentName2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 57:
          componentName2.enforceInterface("android.app.admin.IDevicePolicyManager");
          list = getActiveAdmins(componentName2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 56:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str5 = null;
          } 
          bool9 = isAdminActive((ComponentName)str5, list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 55:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str5 = null;
          } 
          bool83 = bool42;
          if (list.readInt() != 0)
            bool83 = true; 
          setActiveAdmin((ComponentName)str5, bool83, list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 54:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str5 = null;
          } 
          i4 = list.readInt();
          bool83 = bool43;
          if (list.readInt() != 0)
            bool83 = true; 
          i4 = getKeyguardDisabledFeatures((ComponentName)str5, i4, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i4);
          return true;
        case 53:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str5 = null;
          } 
          i4 = list.readInt();
          bool83 = bool44;
          if (list.readInt() != 0)
            bool83 = true; 
          setKeyguardDisabledFeatures((ComponentName)str5, i4, bool83);
          paramParcel2.writeNoException();
          return true;
        case 52:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str5 = null;
          } 
          i4 = list.readInt();
          bool83 = bool45;
          if (list.readInt() != 0)
            bool83 = true; 
          bool8 = getScreenCaptureDisabled((ComponentName)str5, i4, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 51:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str5 = null;
          } 
          if (list.readInt() != 0) {
            bool83 = true;
          } else {
            bool83 = false;
          } 
          if (list.readInt() != 0)
            bool46 = true; 
          setScreenCaptureDisabled((ComponentName)str5, bool83, bool46);
          paramParcel2.writeNoException();
          return true;
        case 50:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str5 = null;
          } 
          i3 = list.readInt();
          bool83 = bool47;
          if (list.readInt() != 0)
            bool83 = true; 
          bool7 = getCameraDisabled((ComponentName)str5, i3, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
          return true;
        case 49:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str5 = null;
          } 
          if (list.readInt() != 0) {
            bool83 = true;
          } else {
            bool83 = false;
          } 
          bool46 = bool48;
          if (list.readInt() != 0)
            bool46 = true; 
          setCameraDisabled((ComponentName)str5, bool83, bool46);
          paramParcel2.writeNoException();
          return true;
        case 48:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          bool7 = requestBugreport((ComponentName)list);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
          return true;
        case 47:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          i2 = getStorageEncryptionStatus(list.readString(), list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i2);
          return true;
        case 46:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str5 = null;
          } 
          bool6 = getStorageEncryption((ComponentName)str5, list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool6);
          return true;
        case 45:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str5 = null;
          } 
          bool83 = bool49;
          if (list.readInt() != 0)
            bool83 = true; 
          i1 = setStorageEncryption((ComponentName)str5, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          return true;
        case 44:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str5 = null;
          } 
          if (list.readInt() != 0) {
            ProxyInfo proxyInfo = (ProxyInfo)ProxyInfo.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          setRecommendedGlobalProxy((ComponentName)str5, (ProxyInfo)list);
          paramParcel2.writeNoException();
          return true;
        case 43:
          list.enforceInterface("android.app.admin.IDevicePolicyManager");
          componentName1 = getGlobalProxyAdmin(list.readInt());
          paramParcel2.writeNoException();
          if (componentName1 != null) {
            paramParcel2.writeInt(1);
            componentName1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 42:
          componentName1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (componentName1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName1);
          } else {
            str5 = null;
          } 
          componentName1 = setGlobalProxy((ComponentName)str5, componentName1.readString(), componentName1.readString());
          paramParcel2.writeNoException();
          if (componentName1 != null) {
            paramParcel2.writeInt(1);
            componentName1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 41:
          componentName1.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool5 = isFactoryResetProtectionPolicySupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool5);
          return true;
        case 40:
          componentName1.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (componentName1.readInt() != 0) {
            componentName1 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)componentName1);
          } else {
            componentName1 = null;
          } 
          factoryResetProtectionPolicy = getFactoryResetProtectionPolicy(componentName1);
          paramParcel2.writeNoException();
          if (factoryResetProtectionPolicy != null) {
            paramParcel2.writeInt(1);
            factoryResetProtectionPolicy.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 39:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          if (factoryResetProtectionPolicy.readInt() != 0) {
            factoryResetProtectionPolicy = (FactoryResetProtectionPolicy)FactoryResetProtectionPolicy.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            factoryResetProtectionPolicy = null;
          } 
          setFactoryResetProtectionPolicy((ComponentName)str5, factoryResetProtectionPolicy);
          paramParcel2.writeNoException();
          return true;
        case 38:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          n = factoryResetProtectionPolicy.readInt();
          str5 = factoryResetProtectionPolicy.readString();
          bool83 = bool50;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          wipeDataWithReason(n, str5, bool83);
          paramParcel2.writeNoException();
          return true;
        case 37:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          n = factoryResetProtectionPolicy.readInt();
          bool83 = bool51;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          lockNow(n, bool83);
          paramParcel2.writeNoException();
          return true;
        case 36:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          n = factoryResetProtectionPolicy.readInt();
          bool83 = bool52;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          l = getRequiredStrongAuthTimeout((ComponentName)str5, n, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 35:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          l = factoryResetProtectionPolicy.readLong();
          bool83 = bool53;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          setRequiredStrongAuthTimeout((ComponentName)str5, l, bool83);
          paramParcel2.writeNoException();
          return true;
        case 34:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          n = factoryResetProtectionPolicy.readInt();
          bool83 = bool54;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          l = getMaximumTimeToLock((ComponentName)str5, n, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 33:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          l = factoryResetProtectionPolicy.readLong();
          bool83 = bool55;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          setMaximumTimeToLock((ComponentName)str5, l, bool83);
          paramParcel2.writeNoException();
          return true;
        case 32:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool4 = resetPassword(factoryResetProtectionPolicy.readString(), factoryResetProtectionPolicy.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool4);
          return true;
        case 31:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          m = factoryResetProtectionPolicy.readInt();
          bool83 = bool56;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          m = getMaximumFailedPasswordsForWipe((ComponentName)str5, m, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(m);
          return true;
        case 30:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          m = factoryResetProtectionPolicy.readInt();
          bool83 = bool57;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          setMaximumFailedPasswordsForWipe((ComponentName)str5, m, bool83);
          paramParcel2.writeNoException();
          return true;
        case 29:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          m = factoryResetProtectionPolicy.readInt();
          bool83 = bool58;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          m = getProfileWithMinimumFailedPasswordsForWipe(m, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(m);
          return true;
        case 28:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          m = factoryResetProtectionPolicy.readInt();
          bool83 = bool59;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          m = getCurrentFailedPasswordAttempts(m, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(m);
          return true;
        case 27:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            factoryResetProtectionPolicy = null;
          } 
          bool3 = isUsingUnifiedPassword((ComponentName)factoryResetProtectionPolicy);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 26:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool83 = bool60;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          k = getPasswordComplexity(bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(k);
          return true;
        case 25:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool2 = isPasswordSufficientAfterProfileUnification(factoryResetProtectionPolicy.readInt(), factoryResetProtectionPolicy.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 24:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          bool2 = isProfileActivePasswordSufficientForParent(factoryResetProtectionPolicy.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 23:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          j = factoryResetProtectionPolicy.readInt();
          bool83 = bool61;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          bool1 = isActivePasswordSufficient(j, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 22:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          i = factoryResetProtectionPolicy.readInt();
          bool83 = bool62;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          l = getPasswordExpiration((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 21:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          i = factoryResetProtectionPolicy.readInt();
          bool83 = bool63;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          l = getPasswordExpirationTimeout((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 20:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          l = factoryResetProtectionPolicy.readLong();
          bool83 = bool64;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          setPasswordExpirationTimeout((ComponentName)str5, l, bool83);
          paramParcel2.writeNoException();
          return true;
        case 19:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          i = factoryResetProtectionPolicy.readInt();
          bool83 = bool65;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          i = getPasswordHistoryLength((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 18:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (factoryResetProtectionPolicy.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)factoryResetProtectionPolicy);
          } else {
            str5 = null;
          } 
          i = factoryResetProtectionPolicy.readInt();
          bool83 = bool66;
          if (factoryResetProtectionPolicy.readInt() != 0)
            bool83 = true; 
          setPasswordHistoryLength((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          return true;
        case 17:
          factoryResetProtectionPolicy.enforceInterface("android.app.admin.IDevicePolicyManager");
          passwordMetrics = getPasswordMinimumMetrics(factoryResetProtectionPolicy.readInt());
          paramParcel2.writeNoException();
          if (passwordMetrics != null) {
            paramParcel2.writeInt(1);
            passwordMetrics.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 16:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool67;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          i = getPasswordMinimumNonLetter((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 15:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool68;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          setPasswordMinimumNonLetter((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          return true;
        case 14:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool69;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          i = getPasswordMinimumSymbols((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 13:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool70;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          setPasswordMinimumSymbols((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          return true;
        case 12:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool71;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          i = getPasswordMinimumNumeric((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 11:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool72;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          setPasswordMinimumNumeric((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          return true;
        case 10:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool73;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          i = getPasswordMinimumLetters((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 9:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool74;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          setPasswordMinimumLetters((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          return true;
        case 8:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool75;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          i = getPasswordMinimumLowerCase((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 7:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool76;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          setPasswordMinimumLowerCase((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          return true;
        case 6:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool77;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          i = getPasswordMinimumUpperCase((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 5:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool78;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          setPasswordMinimumUpperCase((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          return true;
        case 4:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool79;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          i = getPasswordMinimumLength((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 3:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool80;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          setPasswordMinimumLength((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          return true;
        case 2:
          passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
          if (passwordMetrics.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
          } else {
            str5 = null;
          } 
          i = passwordMetrics.readInt();
          bool83 = bool81;
          if (passwordMetrics.readInt() != 0)
            bool83 = true; 
          i = getPasswordQuality((ComponentName)str5, i, bool83);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 1:
          break;
      } 
      passwordMetrics.enforceInterface("android.app.admin.IDevicePolicyManager");
      if (passwordMetrics.readInt() != 0) {
        ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)passwordMetrics);
      } else {
        str5 = null;
      } 
      int i = passwordMetrics.readInt();
      bool83 = bool82;
      if (passwordMetrics.readInt() != 0)
        bool83 = true; 
      setPasswordQuality((ComponentName)str5, i, bool83);
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.app.admin.IDevicePolicyManager");
    return true;
  }
  
  private static class Proxy implements IDevicePolicyManager {
    public static IDevicePolicyManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void addCrossProfileIntentFilter(ComponentName param2ComponentName, IntentFilter param2IntentFilter, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IntentFilter != null) {
          parcel1.writeInt(1);
          param2IntentFilter.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(124, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().addCrossProfileIntentFilter(param2ComponentName, param2IntentFilter, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean addCrossProfileWidgetProvider(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(185, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().addCrossProfileWidgetProvider(param2ComponentName, param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int addOverrideApn(ComponentName param2ComponentName, ApnSetting param2ApnSetting) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ApnSetting != null) {
          parcel1.writeInt(1);
          param2ApnSetting.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(279, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().addOverrideApn(param2ComponentName, param2ApnSetting); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addPersistentPreferredActivity(ComponentName param2ComponentName1, IntentFilter param2IntentFilter, ComponentName param2ComponentName2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName1 != null) {
          parcel1.writeInt(1);
          param2ComponentName1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IntentFilter != null) {
          parcel1.writeInt(1);
          param2IntentFilter.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ComponentName2 != null) {
          parcel1.writeInt(1);
          param2ComponentName2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(112, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().addPersistentPreferredActivity(param2ComponentName1, param2IntentFilter, param2ComponentName2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean approveCaCert(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        boolean bool1 = true;
        if (param2Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(94, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IDevicePolicyManager.Stub.getDefaultImpl().approveCaCert(param2String, param2Int, param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0) {
          param2Boolean = bool1;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean bindDeviceAdminServiceAsUser(ComponentName param2ComponentName, IApplicationThread param2IApplicationThread, IBinder param2IBinder, Intent param2Intent, IServiceConnection param2IServiceConnection, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder2;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        IBinder iBinder1 = null;
        if (param2IApplicationThread != null) {
          iBinder2 = param2IApplicationThread.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        try {
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          iBinder2 = iBinder1;
          if (param2IServiceConnection != null)
            iBinder2 = param2IServiceConnection.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeInt(param2Int2);
              if (!this.mRemote.transact(255, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                bool = IDevicePolicyManager.Stub.getDefaultImpl().bindDeviceAdminServiceAsUser(param2ComponentName, param2IApplicationThread, param2IBinder, param2Intent, param2IServiceConnection, param2Int1, param2Int2);
                parcel2.recycle();
                parcel1.recycle();
                return bool;
              } 
              parcel2.readException();
              param2Int1 = parcel2.readInt();
              if (param2Int1 == 0)
                bool = false; 
              parcel2.recycle();
              parcel1.recycle();
              return bool;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2ComponentName;
    }
    
    public boolean canProfileOwnerResetPasswordWhenLocked(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(311, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().canProfileOwnerResetPasswordWhenLocked(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean checkDeviceIdentifierAccess(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(86, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().checkDeviceIdentifierAccess(param2String, param2Int1, param2Int2);
          return bool;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        if (param2Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int checkProvisioningPreCondition(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(211, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().checkProvisioningPreCondition(param2String1, param2String2); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void choosePrivateKeyAlias(int param2Int, Uri param2Uri, String param2String, IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (param2Uri != null) {
          parcel1.writeInt(1);
          param2Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(100, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().choosePrivateKeyAlias(param2Int, param2Uri, param2String, param2IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearApplicationUserData(ComponentName param2ComponentName, String param2String, IPackageDataObserver param2IPackageDataObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2IPackageDataObserver != null) {
          iBinder = param2IPackageDataObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(267, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().clearApplicationUserData(param2ComponentName, param2String, param2IPackageDataObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearCrossProfileIntentFilters(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(125, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().clearCrossProfileIntentFilters(param2ComponentName);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearDeviceOwner(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().clearDeviceOwner(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearPackagePersistentPreferredActivities(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(113, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().clearPackagePersistentPreferredActivities(param2ComponentName, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearProfileOwner(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(83, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().clearProfileOwner(param2ComponentName);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean clearResetPasswordToken(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(262, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().clearResetPasswordToken(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearSystemUpdatePolicyFreezePeriodRecord() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(200, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().clearSystemUpdatePolicyFreezePeriodRecord();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Intent createAdminSupportIntent(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(137, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().createAdminSupportIntent(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (Intent)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public UserHandle createAndManageUser(ComponentName param2ComponentName1, String param2String, ComponentName param2ComponentName2, PersistableBundle param2PersistableBundle, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName1 != null) {
          parcel1.writeInt(1);
          param2ComponentName1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2ComponentName2 != null) {
          parcel1.writeInt(1);
          param2ComponentName2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2PersistableBundle != null) {
          parcel1.writeInt(1);
          param2PersistableBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(140, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().createAndManageUser(param2ComponentName1, param2String, param2ComponentName2, param2PersistableBundle, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName1 = null;
        } 
        return (UserHandle)param2ComponentName1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enableSystemApp(ComponentName param2ComponentName, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(147, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().enableSystemApp(param2ComponentName, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int enableSystemAppWithIntent(ComponentName param2ComponentName, String param2String, Intent param2Intent) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(148, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().enableSystemAppWithIntent(param2ComponentName, param2String, param2Intent); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enforceCanManageCaCerts(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(93, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().enforceCanManageCaCerts(param2ComponentName, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long forceNetworkLogs() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(242, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().forceNetworkLogs(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void forceRemoveActiveAdmin(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(61, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().forceRemoveActiveAdmin(param2ComponentName, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long forceSecurityLogs() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(243, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().forceSecurityLogs(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void forceUpdateUserSetupComplete() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(249, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().forceUpdateUserSetupComplete();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean generateKeyPair(ComponentName param2ComponentName, String param2String1, String param2String2, ParcelableKeyGenParameterSpec param2ParcelableKeyGenParameterSpec, int param2Int, KeymasterCertificateChain param2KeymasterCertificateChain) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeString(param2String2);
            if (param2ParcelableKeyGenParameterSpec != null) {
              parcel1.writeInt(1);
              param2ParcelableKeyGenParameterSpec.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeInt(param2Int);
              try {
                if (!this.mRemote.transact(98, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                  bool = IDevicePolicyManager.Stub.getDefaultImpl().generateKeyPair(param2ComponentName, param2String1, param2String2, param2ParcelableKeyGenParameterSpec, param2Int, param2KeymasterCertificateChain);
                  parcel2.recycle();
                  parcel1.recycle();
                  return bool;
                } 
                parcel2.readException();
                if (parcel2.readInt() == 0)
                  bool = false; 
                param2Int = parcel2.readInt();
                if (param2Int != 0)
                  try {
                    param2KeymasterCertificateChain.readFromParcel(parcel2);
                  } finally {} 
                parcel2.recycle();
                parcel1.recycle();
                return bool;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2ComponentName;
    }
    
    public String[] getAccountTypesWithManagementDisabled() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(151, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getAccountTypesWithManagementDisabled(); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] getAccountTypesWithManagementDisabledAsUser(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(152, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getAccountTypesWithManagementDisabledAsUser(param2Int, param2Boolean); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ComponentName> getActiveAdmins(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getActiveAdmins(param2Int); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ComponentName.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getAffiliationIds(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(236, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getAffiliationIds(param2ComponentName); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getAllCrossProfilePackages() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(297, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getAllCrossProfilePackages(); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getAlwaysOnVpnLockdownWhitelist(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(111, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getAlwaysOnVpnLockdownWhitelist(param2ComponentName); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getAlwaysOnVpnPackage(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(107, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getAlwaysOnVpnPackage(param2ComponentName); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getAlwaysOnVpnPackageForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(108, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getAlwaysOnVpnPackageForUser(param2Int); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bundle getApplicationRestrictions(ComponentName param2ComponentName, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(116, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getApplicationRestrictions(param2ComponentName, param2String1, param2String2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (Bundle)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getApplicationRestrictionsManagingPackage(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(118, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getApplicationRestrictionsManagingPackage(param2ComponentName); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getAutoTimeEnabled(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(191, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getAutoTimeEnabled(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getAutoTimeRequired() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(189, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getAutoTimeRequired();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getAutoTimeZoneEnabled(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(193, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getAutoTimeZoneEnabled(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<UserHandle> getBindDeviceAdminTargetUsers(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(256, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getBindDeviceAdminTargetUsers(param2ComponentName); 
        parcel2.readException();
        return parcel2.createTypedArrayList(UserHandle.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getBluetoothContactSharingDisabled(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(181, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getBluetoothContactSharingDisabled(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getBluetoothContactSharingDisabledForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(182, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getBluetoothContactSharingDisabledForUser(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getCameraDisabled(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool1 = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(50, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IDevicePolicyManager.Stub.getDefaultImpl().getCameraDisabled(param2ComponentName, param2Int, param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0) {
          param2Boolean = bool1;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getCertInstallerPackage(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(105, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getCertInstallerPackage(param2ComponentName); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getCrossProfileCalendarPackages(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(292, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileCalendarPackages(param2ComponentName); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getCrossProfileCalendarPackagesForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(294, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileCalendarPackagesForUser(param2Int); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getCrossProfileCallerIdDisabled(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(174, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileCallerIdDisabled(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getCrossProfileCallerIdDisabledForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(175, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileCallerIdDisabledForUser(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getCrossProfileContactsSearchDisabled(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(177, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileContactsSearchDisabled(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getCrossProfileContactsSearchDisabledForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(178, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileContactsSearchDisabledForUser(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getCrossProfilePackages(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(296, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfilePackages(param2ComponentName); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getCrossProfileWidgetProviders(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(187, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileWidgetProviders(param2ComponentName); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getCurrentFailedPasswordAttempts(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getCurrentFailedPasswordAttempts(param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getDefaultCrossProfilePackages() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(298, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getDefaultCrossProfilePackages(); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getDelegatePackages(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(103, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getDelegatePackages(param2ComponentName, param2String); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getDelegatedScopes(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(102, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getDelegatedScopes(param2ComponentName, param2String); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getDeviceOwnerComponent(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(71, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          componentName = IDevicePolicyManager.Stub.getDefaultImpl().getDeviceOwnerComponent(param2Boolean);
          return componentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName = null;
        } 
        return componentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CharSequence getDeviceOwnerLockScreenInfo() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        CharSequence charSequence;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(88, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          charSequence = IDevicePolicyManager.Stub.getDefaultImpl().getDeviceOwnerLockScreenInfo();
          return charSequence;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          charSequence = null;
        } 
        return charSequence;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getDeviceOwnerName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getDeviceOwnerName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CharSequence getDeviceOwnerOrganizationName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        CharSequence charSequence;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(231, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          charSequence = IDevicePolicyManager.Stub.getDefaultImpl().getDeviceOwnerOrganizationName();
          return charSequence;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          charSequence = null;
        } 
        return charSequence;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getDeviceOwnerUserId() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getDeviceOwnerUserId(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getDisallowedSystemApps(ComponentName param2ComponentName, int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(270, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getDisallowedSystemApps(param2ComponentName, param2Int, param2String); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getDoNotAskCredentialsOnBoot() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(203, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getDoNotAskCredentialsOnBoot();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CharSequence getEndUserSessionMessage(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(276, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getEndUserSessionMessage(param2ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (CharSequence)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public FactoryResetProtectionPolicy getFactoryResetProtectionPolicy(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getFactoryResetProtectionPolicy(param2ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          FactoryResetProtectionPolicy factoryResetProtectionPolicy = (FactoryResetProtectionPolicy)FactoryResetProtectionPolicy.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (FactoryResetProtectionPolicy)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getForceEphemeralUsers(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(195, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getForceEphemeralUsers(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getGlobalPrivateDnsHost(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(288, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getGlobalPrivateDnsHost(param2ComponentName); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getGlobalPrivateDnsMode(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(287, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getGlobalPrivateDnsMode(param2ComponentName); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getGlobalProxyAdmin(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          componentName = IDevicePolicyManager.Stub.getDefaultImpl().getGlobalProxyAdmin(param2Int);
          return componentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName = null;
        } 
        return componentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.admin.IDevicePolicyManager";
    }
    
    public List<String> getKeepUninstalledPackages(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(213, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getKeepUninstalledPackages(param2ComponentName, param2String); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getKeyguardDisabledFeatures(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getKeyguardDisabledFeatures(param2ComponentName, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getLastBugReportRequestTime() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(259, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getLastBugReportRequestTime(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getLastNetworkLogRetrievalTime() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(260, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getLastNetworkLogRetrievalTime(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getLastSecurityLogRetrievalTime() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(258, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getLastSecurityLogRetrievalTime(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getLockTaskFeatures(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(159, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getLockTaskFeatures(param2ComponentName); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] getLockTaskPackages(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(156, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getLockTaskPackages(param2ComponentName); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CharSequence getLongSupportMessage(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(221, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getLongSupportMessage(param2ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (CharSequence)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CharSequence getLongSupportMessageForUser(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(223, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getLongSupportMessageForUser(param2ComponentName, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (CharSequence)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getManagedProfileMaximumTimeOff(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(309, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getManagedProfileMaximumTimeOff(param2ComponentName); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getMaximumFailedPasswordsForWipe(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getMaximumFailedPasswordsForWipe(param2ComponentName, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getMaximumTimeToLock(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getMaximumTimeToLock(param2ComponentName, param2Int, param2Boolean); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getMeteredDataDisabledPackages(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(278, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getMeteredDataDisabledPackages(param2ComponentName); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getOrganizationColor(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(227, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getOrganizationColor(param2ComponentName); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getOrganizationColorForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(228, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getOrganizationColorForUser(param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CharSequence getOrganizationName(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(230, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getOrganizationName(param2ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (CharSequence)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CharSequence getOrganizationNameForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        CharSequence charSequence;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(232, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          charSequence = IDevicePolicyManager.Stub.getDefaultImpl().getOrganizationNameForUser(param2Int);
          return charSequence;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          charSequence = null;
        } 
        return charSequence;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ApnSetting> getOverrideApns(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(282, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getOverrideApns(param2ComponentName); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ApnSetting.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public StringParceledListSlice getOwnerInstalledCaCerts(UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(266, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getOwnerInstalledCaCerts(param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          StringParceledListSlice stringParceledListSlice = (StringParceledListSlice)StringParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2UserHandle = null;
        } 
        return (StringParceledListSlice)param2UserHandle;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPasswordComplexity(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          i = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordComplexity(param2Boolean);
          return i;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        return i;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getPasswordExpiration(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getPasswordExpiration(param2ComponentName, param2Int, param2Boolean); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getPasswordExpirationTimeout(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getPasswordExpirationTimeout(param2ComponentName, param2Int, param2Boolean); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPasswordHistoryLength(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordHistoryLength(param2ComponentName, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPasswordMinimumLength(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumLength(param2ComponentName, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPasswordMinimumLetters(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumLetters(param2ComponentName, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPasswordMinimumLowerCase(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumLowerCase(param2ComponentName, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public PasswordMetrics getPasswordMinimumMetrics(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        PasswordMetrics passwordMetrics;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          passwordMetrics = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumMetrics(param2Int);
          return passwordMetrics;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          passwordMetrics = (PasswordMetrics)PasswordMetrics.CREATOR.createFromParcel(parcel2);
        } else {
          passwordMetrics = null;
        } 
        return passwordMetrics;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPasswordMinimumNonLetter(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumNonLetter(param2ComponentName, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPasswordMinimumNumeric(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumNumeric(param2ComponentName, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPasswordMinimumSymbols(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumSymbols(param2ComponentName, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPasswordMinimumUpperCase(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumUpperCase(param2ComponentName, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPasswordQuality(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordQuality(param2ComponentName, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public SystemUpdateInfo getPendingSystemUpdate(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(205, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getPendingSystemUpdate(param2ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          SystemUpdateInfo systemUpdateInfo = (SystemUpdateInfo)SystemUpdateInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (SystemUpdateInfo)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPermissionGrantState(ComponentName param2ComponentName, String param2String1, String param2String2, String param2String3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeString(param2String3);
        if (!this.mRemote.transact(209, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getPermissionGrantState(param2ComponentName, param2String1, param2String2, param2String3); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPermissionPolicy(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(207, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getPermissionPolicy(param2ComponentName); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List getPermittedAccessibilityServices(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(127, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getPermittedAccessibilityServices(param2ComponentName); 
        parcel2.readException();
        return parcel2.readArrayList(getClass().getClassLoader());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List getPermittedAccessibilityServicesForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(128, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getPermittedAccessibilityServicesForUser(param2Int); 
        parcel2.readException();
        return parcel2.readArrayList(getClass().getClassLoader());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getPermittedCrossProfileNotificationListeners(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(135, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getPermittedCrossProfileNotificationListeners(param2ComponentName); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List getPermittedInputMethods(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(131, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getPermittedInputMethods(param2ComponentName); 
        parcel2.readException();
        return parcel2.readArrayList(getClass().getClassLoader());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List getPermittedInputMethodsForCurrentUser() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(132, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getPermittedInputMethodsForCurrentUser(); 
        parcel2.readException();
        return parcel2.readArrayList(getClass().getClassLoader());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPersonalAppsSuspendedReasons(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(307, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getPersonalAppsSuspendedReasons(param2ComponentName); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getProfileOwner(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(78, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          componentName = IDevicePolicyManager.Stub.getDefaultImpl().getProfileOwner(param2Int);
          return componentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName = null;
        } 
        return componentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getProfileOwnerAsUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(77, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          componentName = IDevicePolicyManager.Stub.getDefaultImpl().getProfileOwnerAsUser(param2Int);
          return componentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName = null;
        } 
        return componentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getProfileOwnerName(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(80, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getProfileOwnerName(param2Int); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getProfileOwnerOrDeviceOwnerSupervisionComponent(UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(79, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getProfileOwnerOrDeviceOwnerSupervisionComponent(param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          param2UserHandle = null;
        } 
        return (ComponentName)param2UserHandle;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getProfileWithMinimumFailedPasswordsForWipe(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getProfileWithMinimumFailedPasswordsForWipe(param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void getRemoveWarning(ComponentName param2ComponentName, RemoteCallback param2RemoteCallback, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2RemoteCallback != null) {
          parcel1.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().getRemoveWarning(param2ComponentName, param2RemoteCallback, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getRequiredStrongAuthTimeout(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getRequiredStrongAuthTimeout(param2ComponentName, param2Int, param2Boolean); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getRestrictionsProvider(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(121, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          componentName = IDevicePolicyManager.Stub.getDefaultImpl().getRestrictionsProvider(param2Int);
          return componentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName = null;
        } 
        return componentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getScreenCaptureDisabled(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool1 = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IDevicePolicyManager.Stub.getDefaultImpl().getScreenCaptureDisabled(param2ComponentName, param2Int, param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0) {
          param2Boolean = bool1;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<UserHandle> getSecondaryUsers(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(146, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getSecondaryUsers(param2ComponentName); 
        parcel2.readException();
        return parcel2.createTypedArrayList(UserHandle.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CharSequence getShortSupportMessage(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(219, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getShortSupportMessage(param2ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (CharSequence)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CharSequence getShortSupportMessageForUser(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(222, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getShortSupportMessageForUser(param2ComponentName, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (CharSequence)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CharSequence getStartUserSessionMessage(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(275, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getStartUserSessionMessage(param2ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (CharSequence)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getStorageEncryption(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().getStorageEncryption(param2ComponentName, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getStorageEncryptionStatus(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().getStorageEncryptionStatus(param2String, param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public SystemUpdatePolicy getSystemUpdatePolicy() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        SystemUpdatePolicy systemUpdatePolicy;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(199, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          systemUpdatePolicy = IDevicePolicyManager.Stub.getDefaultImpl().getSystemUpdatePolicy();
          return systemUpdatePolicy;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          systemUpdatePolicy = (SystemUpdatePolicy)SystemUpdatePolicy.CREATOR.createFromParcel(parcel2);
        } else {
          systemUpdatePolicy = null;
        } 
        return systemUpdatePolicy;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public PersistableBundle getTransferOwnershipBundle() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        PersistableBundle persistableBundle;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(272, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          persistableBundle = IDevicePolicyManager.Stub.getDefaultImpl().getTransferOwnershipBundle();
          return persistableBundle;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(parcel2);
        } else {
          persistableBundle = null;
        } 
        return persistableBundle;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<PersistableBundle> getTrustAgentConfiguration(ComponentName param2ComponentName1, ComponentName param2ComponentName2, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName1 != null) {
          parcel1.writeInt(1);
          param2ComponentName1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ComponentName2 != null) {
          parcel1.writeInt(1);
          param2ComponentName2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(184, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getTrustAgentConfiguration(param2ComponentName1, param2ComponentName2, param2Int, param2Boolean); 
        parcel2.readException();
        return parcel2.createTypedArrayList(PersistableBundle.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getUserControlDisabledPackages(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(304, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getUserControlDisabledPackages(param2ComponentName); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getUserProvisioningState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(233, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getUserProvisioningState(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bundle getUserRestrictions(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(123, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getUserRestrictions(param2ComponentName, param2Boolean); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (Bundle)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getWifiMacAddress(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(216, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().getWifiMacAddress(param2ComponentName); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasDeviceOwner() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(72, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().hasDeviceOwner();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasGrantedPolicy(ComponentName param2ComponentName, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(62, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().hasGrantedPolicy(param2ComponentName, param2Int1, param2Int2);
          return bool;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        if (param2Int1 == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasLockdownAdminConfiguredNetworks(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(164, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().hasLockdownAdminConfiguredNetworks(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasUserSetupCompleted() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(84, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().hasUserSetupCompleted();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean installCaCert(ComponentName param2ComponentName, String param2String, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(91, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().installCaCert(param2ComponentName, param2String, param2ArrayOfbyte);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean installExistingPackage(ComponentName param2ComponentName, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(149, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().installExistingPackage(param2ComponentName, param2String1, param2String2);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean installKeyPair(ComponentName param2ComponentName, String param2String1, byte[] param2ArrayOfbyte1, byte[] param2ArrayOfbyte2, byte[] param2ArrayOfbyte3, String param2String2, boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeByteArray(param2ArrayOfbyte1);
            try {
              parcel1.writeByteArray(param2ArrayOfbyte2);
              parcel1.writeByteArray(param2ArrayOfbyte3);
              parcel1.writeString(param2String2);
              if (param2Boolean1) {
                i = 1;
              } else {
                i = 0;
              } 
              parcel1.writeInt(i);
              if (param2Boolean2) {
                i = 1;
              } else {
                i = 0;
              } 
              parcel1.writeInt(i);
              if (!this.mRemote.transact(96, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                param2Boolean1 = IDevicePolicyManager.Stub.getDefaultImpl().installKeyPair(param2ComponentName, param2String1, param2ArrayOfbyte1, param2ArrayOfbyte2, param2ArrayOfbyte3, param2String2, param2Boolean1, param2Boolean2);
                parcel2.recycle();
                parcel1.recycle();
                return param2Boolean1;
              } 
              parcel2.readException();
              int i = parcel2.readInt();
              if (i != 0) {
                param2Boolean1 = bool;
              } else {
                param2Boolean1 = false;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return param2Boolean1;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2ComponentName;
    }
    
    public void installUpdateFromFile(ComponentName param2ComponentName, ParcelFileDescriptor param2ParcelFileDescriptor, StartInstallingUpdateCallback param2StartInstallingUpdateCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ParcelFileDescriptor != null) {
          parcel1.writeInt(1);
          param2ParcelFileDescriptor.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2StartInstallingUpdateCallback != null) {
          iBinder = param2StartInstallingUpdateCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(290, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().installUpdateFromFile(param2ComponentName, param2ParcelFileDescriptor, param2StartInstallingUpdateCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAccessibilityServicePermittedByAdmin(ComponentName param2ComponentName, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(129, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isAccessibilityServicePermittedByAdmin(param2ComponentName, param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isActivePasswordSufficient(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        boolean bool1 = true;
        if (param2Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IDevicePolicyManager.Stub.getDefaultImpl().isActivePasswordSufficient(param2Int, param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0) {
          param2Boolean = bool1;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAdminActive(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isAdminActive(param2ComponentName, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAffiliatedUser() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(237, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isAffiliatedUser();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAlwaysOnVpnLockdownEnabled(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(109, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isAlwaysOnVpnLockdownEnabled(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAlwaysOnVpnLockdownEnabledForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(110, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isAlwaysOnVpnLockdownEnabledForUser(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isApplicationHidden(ComponentName param2ComponentName, String param2String1, String param2String2, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(139, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IDevicePolicyManager.Stub.getDefaultImpl().isApplicationHidden(param2ComponentName, param2String1, param2String2, param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isBackupServiceEnabled(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(251, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isBackupServiceEnabled(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isCaCertApproved(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(95, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isCaCertApproved(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isCallerApplicationRestrictionsManagingPackage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(119, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isCallerApplicationRestrictionsManagingPackage(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isCommonCriteriaModeEnabled(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(306, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isCommonCriteriaModeEnabled(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isCurrentInputMethodSetByOwner() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(265, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isCurrentInputMethodSetByOwner();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isDeviceProvisioned() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(246, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isDeviceProvisioned();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isDeviceProvisioningConfigApplied() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(247, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isDeviceProvisioningConfigApplied();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isEphemeralUser(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(257, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isEphemeralUser(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isFactoryResetProtectionPolicySupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(41, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isFactoryResetProtectionPolicySupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isInputMethodPermittedByAdmin(ComponentName param2ComponentName, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(133, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isInputMethodPermittedByAdmin(param2ComponentName, param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isLockTaskPermitted(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(157, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isLockTaskPermitted(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isLogoutEnabled() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(269, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isLogoutEnabled();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isManagedKiosk() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(299, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isManagedKiosk();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isManagedProfile(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(214, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isManagedProfile(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isMasterVolumeMuted(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(169, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isMasterVolumeMuted(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isMeteredDataDisabledPackageForUser(ComponentName param2ComponentName, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(285, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isMeteredDataDisabledPackageForUser(param2ComponentName, param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isNetworkLoggingEnabled(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(253, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isNetworkLoggingEnabled(param2ComponentName, param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isNotificationListenerServicePermitted(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(136, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isNotificationListenerServicePermitted(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isOrganizationOwnedDeviceWithManagedProfile() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(85, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isOrganizationOwnedDeviceWithManagedProfile();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isOverrideApnEnabled(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(284, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isOverrideApnEnabled(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isPackageAllowedToAccessCalendarForUser(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(293, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isPackageAllowedToAccessCalendarForUser(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isPackageSuspended(ComponentName param2ComponentName, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(90, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isPackageSuspended(param2ComponentName, param2String1, param2String2);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isPasswordSufficientAfterProfileUnification(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(25, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isPasswordSufficientAfterProfileUnification(param2Int1, param2Int2);
          return bool;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        if (param2Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isProfileActivePasswordSufficientForParent(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(24, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isProfileActivePasswordSufficientForParent(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isProvisioningAllowed(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(210, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isProvisioningAllowed(param2String1, param2String2);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isRemovingAdmin(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(196, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isRemovingAdmin(param2ComponentName, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isResetPasswordTokenActive(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(263, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isResetPasswordTokenActive(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isSecondaryLockscreenEnabled(UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(154, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isSecondaryLockscreenEnabled(param2UserHandle);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isSecurityLoggingEnabled(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(239, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isSecurityLoggingEnabled(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isSeparateProfileChallengeAllowed(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(224, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isSeparateProfileChallengeAllowed(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isSystemOnlyUser(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(215, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isSystemOnlyUser(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isUnattendedManagedKiosk() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(300, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isUnattendedManagedKiosk();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isUninstallBlocked(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(172, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isUninstallBlocked(param2ComponentName, param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isUninstallInQueue(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(244, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isUninstallInQueue(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isUsingUnifiedPassword(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().isUsingUnifiedPassword(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void lockNow(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().lockNow(param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int logoutUser(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(145, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().logoutUser(param2ComponentName); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void markProfileOwnerOnOrganizationOwnedDevice(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(289, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().markProfileOwnerOnOrganizationOwnedDevice(param2ComponentName, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyLockTaskModeChanged(boolean param2Boolean, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(170, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().notifyLockTaskModeChanged(param2Boolean, param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyPendingSystemUpdate(SystemUpdateInfo param2SystemUpdateInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2SystemUpdateInfo != null) {
          parcel1.writeInt(1);
          param2SystemUpdateInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(204, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().notifyPendingSystemUpdate(param2SystemUpdateInfo);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean packageHasActiveAdmins(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(58, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().packageHasActiveAdmins(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reboot(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(217, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().reboot(param2ComponentName);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeActiveAdmin(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().removeActiveAdmin(param2ComponentName, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeCrossProfileWidgetProvider(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(186, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().removeCrossProfileWidgetProvider(param2ComponentName, param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeKeyPair(ComponentName param2ComponentName, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(97, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().removeKeyPair(param2ComponentName, param2String1, param2String2);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeOverrideApn(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(281, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().removeOverrideApn(param2ComponentName, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeUser(ComponentName param2ComponentName, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(141, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().removeUser(param2ComponentName, param2UserHandle);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportFailedBiometricAttempt(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(66, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().reportFailedBiometricAttempt(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportFailedPasswordAttempt(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(64, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().reportFailedPasswordAttempt(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportKeyguardDismissed(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().reportKeyguardDismissed(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportKeyguardSecured(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().reportKeyguardSecured(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportPasswordChanged(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(63, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().reportPasswordChanged(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportSuccessfulBiometricAttempt(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().reportSuccessfulBiometricAttempt(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportSuccessfulPasswordAttempt(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().reportSuccessfulPasswordAttempt(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean requestBugreport(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().requestBugreport(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean resetPassword(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(32, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().resetPassword(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean resetPasswordWithToken(ComponentName param2ComponentName, String param2String, byte[] param2ArrayOfbyte, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeByteArray(param2ArrayOfbyte);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(264, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().resetPasswordWithToken(param2ComponentName, param2String, param2ArrayOfbyte, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<NetworkEvent> retrieveNetworkLogs(ComponentName param2ComponentName, String param2String, long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(254, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().retrieveNetworkLogs(param2ComponentName, param2String, param2Long); 
        parcel2.readException();
        return parcel2.createTypedArrayList(NetworkEvent.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice retrievePreRebootSecurityLogs(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(241, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().retrievePreRebootSecurityLogs(param2ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (ParceledListSlice)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice retrieveSecurityLogs(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(240, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().retrieveSecurityLogs(param2ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (ParceledListSlice)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAccountManagementDisabled(ComponentName param2ComponentName, String param2String, boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool1 = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(150, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setAccountManagementDisabled(param2ComponentName, param2String, param2Boolean1, param2Boolean2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setActiveAdmin(ComponentName param2ComponentName, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setActiveAdmin(param2ComponentName, param2Boolean, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAffiliationIds(ComponentName param2ComponentName, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(235, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setAffiliationIds(param2ComponentName, param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setAlwaysOnVpnPackage(ComponentName param2ComponentName, String param2String, boolean param2Boolean, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(106, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IDevicePolicyManager.Stub.getDefaultImpl().setAlwaysOnVpnPackage(param2ComponentName, param2String, param2Boolean, param2List);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setApplicationHidden(ComponentName param2ComponentName, String param2String1, String param2String2, boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(param2String1);
          try {
            int i;
            parcel1.writeString(param2String2);
            if (param2Boolean1) {
              i = 1;
            } else {
              i = 0;
            } 
            parcel1.writeInt(i);
            if (param2Boolean2) {
              i = 1;
            } else {
              i = 0;
            } 
            parcel1.writeInt(i);
            try {
              if (!this.mRemote.transact(138, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                param2Boolean1 = IDevicePolicyManager.Stub.getDefaultImpl().setApplicationHidden(param2ComponentName, param2String1, param2String2, param2Boolean1, param2Boolean2);
                parcel2.recycle();
                parcel1.recycle();
                return param2Boolean1;
              } 
              parcel2.readException();
              i = parcel2.readInt();
              if (i != 0) {
                param2Boolean1 = bool;
              } else {
                param2Boolean1 = false;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return param2Boolean1;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2ComponentName;
    }
    
    public void setApplicationRestrictions(ComponentName param2ComponentName, String param2String1, String param2String2, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(115, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setApplicationRestrictions(param2ComponentName, param2String1, param2String2, param2Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setApplicationRestrictionsManagingPackage(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(117, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().setApplicationRestrictionsManagingPackage(param2ComponentName, param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAutoTimeEnabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(190, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setAutoTimeEnabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAutoTimeRequired(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(188, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setAutoTimeRequired(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAutoTimeZoneEnabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(192, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setAutoTimeZoneEnabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setBackupServiceEnabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(250, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setBackupServiceEnabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setBluetoothContactSharingDisabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(180, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setBluetoothContactSharingDisabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCameraDisabled(ComponentName param2ComponentName, boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool1 = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setCameraDisabled(param2ComponentName, param2Boolean1, param2Boolean2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCertInstallerPackage(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(104, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setCertInstallerPackage(param2ComponentName, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCommonCriteriaModeEnabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(305, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setCommonCriteriaModeEnabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setConfiguredNetworksLockdownState(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(163, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setConfiguredNetworksLockdownState(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCrossProfileCalendarPackages(ComponentName param2ComponentName, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(291, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setCrossProfileCalendarPackages(param2ComponentName, param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCrossProfileCallerIdDisabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(173, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setCrossProfileCallerIdDisabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCrossProfileContactsSearchDisabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(176, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setCrossProfileContactsSearchDisabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCrossProfilePackages(ComponentName param2ComponentName, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(295, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setCrossProfilePackages(param2ComponentName, param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDefaultSmsApplication(ComponentName param2ComponentName, String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(114, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setDefaultSmsApplication(param2ComponentName, param2String, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDelegatedScopes(ComponentName param2ComponentName, String param2String, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(101, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setDelegatedScopes(param2ComponentName, param2String, param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setDeviceOwner(ComponentName param2ComponentName, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().setDeviceOwner(param2ComponentName, param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDeviceOwnerLockScreenInfo(ComponentName param2ComponentName, CharSequence param2CharSequence) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2CharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(87, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setDeviceOwnerLockScreenInfo(param2ComponentName, param2CharSequence);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDeviceProvisioningConfigApplied() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (!this.mRemote.transact(248, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setDeviceProvisioningConfigApplied();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setEndUserSessionMessage(ComponentName param2ComponentName, CharSequence param2CharSequence) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2CharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(274, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setEndUserSessionMessage(param2ComponentName, param2CharSequence);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setFactoryResetProtectionPolicy(ComponentName param2ComponentName, FactoryResetProtectionPolicy param2FactoryResetProtectionPolicy) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2FactoryResetProtectionPolicy != null) {
          parcel1.writeInt(1);
          param2FactoryResetProtectionPolicy.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setFactoryResetProtectionPolicy(param2ComponentName, param2FactoryResetProtectionPolicy);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setForceEphemeralUsers(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(194, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setForceEphemeralUsers(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int setGlobalPrivateDns(ComponentName param2ComponentName, int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(286, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Int = IDevicePolicyManager.Stub.getDefaultImpl().setGlobalPrivateDns(param2ComponentName, param2Int, param2String);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName setGlobalProxy(ComponentName param2ComponentName, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2ComponentName = IDevicePolicyManager.Stub.getDefaultImpl().setGlobalProxy(param2ComponentName, param2String1, param2String2);
          return param2ComponentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          param2ComponentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setGlobalSetting(ComponentName param2ComponentName, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(160, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setGlobalSetting(param2ComponentName, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setKeepUninstalledPackages(ComponentName param2ComponentName, String param2String, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(212, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setKeepUninstalledPackages(param2ComponentName, param2String, param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setKeyGrantForApp(ComponentName param2ComponentName, String param2String1, String param2String2, String param2String3, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeString(param2String2);
            try {
              int i;
              parcel1.writeString(param2String3);
              if (param2Boolean) {
                i = 1;
              } else {
                i = 0;
              } 
              parcel1.writeInt(i);
              try {
                if (!this.mRemote.transact(302, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                  param2Boolean = IDevicePolicyManager.Stub.getDefaultImpl().setKeyGrantForApp(param2ComponentName, param2String1, param2String2, param2String3, param2Boolean);
                  parcel2.recycle();
                  parcel1.recycle();
                  return param2Boolean;
                } 
                parcel2.readException();
                i = parcel2.readInt();
                if (i != 0) {
                  param2Boolean = bool;
                } else {
                  param2Boolean = false;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return param2Boolean;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2ComponentName;
    }
    
    public boolean setKeyPairCertificate(ComponentName param2ComponentName, String param2String1, String param2String2, byte[] param2ArrayOfbyte1, byte[] param2ArrayOfbyte2, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeString(param2String2);
            try {
              parcel1.writeByteArray(param2ArrayOfbyte1);
              try {
                int i;
                parcel1.writeByteArray(param2ArrayOfbyte2);
                if (param2Boolean) {
                  i = 1;
                } else {
                  i = 0;
                } 
                parcel1.writeInt(i);
                try {
                  if (!this.mRemote.transact(99, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                    param2Boolean = IDevicePolicyManager.Stub.getDefaultImpl().setKeyPairCertificate(param2ComponentName, param2String1, param2String2, param2ArrayOfbyte1, param2ArrayOfbyte2, param2Boolean);
                    parcel2.recycle();
                    parcel1.recycle();
                    return param2Boolean;
                  } 
                  parcel2.readException();
                  i = parcel2.readInt();
                  if (i != 0) {
                    param2Boolean = bool;
                  } else {
                    param2Boolean = false;
                  } 
                  parcel2.recycle();
                  parcel1.recycle();
                  return param2Boolean;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2ComponentName;
    }
    
    public boolean setKeyguardDisabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(201, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IDevicePolicyManager.Stub.getDefaultImpl().setKeyguardDisabled(param2ComponentName, param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setKeyguardDisabledFeatures(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setKeyguardDisabledFeatures(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setLocationEnabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(165, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setLocationEnabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setLockTaskFeatures(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(158, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setLockTaskFeatures(param2ComponentName, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setLockTaskPackages(ComponentName param2ComponentName, String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(155, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setLockTaskPackages(param2ComponentName, param2ArrayOfString);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setLogoutEnabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(268, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setLogoutEnabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setLongSupportMessage(ComponentName param2ComponentName, CharSequence param2CharSequence) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2CharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(220, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setLongSupportMessage(param2ComponentName, param2CharSequence);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setManagedProfileMaximumTimeOff(ComponentName param2ComponentName, long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(310, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setManagedProfileMaximumTimeOff(param2ComponentName, param2Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setMasterVolumeMuted(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(168, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setMasterVolumeMuted(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setMaximumFailedPasswordsForWipe(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setMaximumFailedPasswordsForWipe(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setMaximumTimeToLock(ComponentName param2ComponentName, long param2Long, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeLong(param2Long);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setMaximumTimeToLock(param2ComponentName, param2Long, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> setMeteredDataDisabledPackages(ComponentName param2ComponentName, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(277, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().setMeteredDataDisabledPackages(param2ComponentName, param2List); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNetworkLoggingEnabled(ComponentName param2ComponentName, String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(252, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setNetworkLoggingEnabled(param2ComponentName, param2String, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setOrganizationColor(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(225, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setOrganizationColor(param2ComponentName, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setOrganizationColorForUser(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(226, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setOrganizationColorForUser(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setOrganizationName(ComponentName param2ComponentName, CharSequence param2CharSequence) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2CharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(229, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setOrganizationName(param2ComponentName, param2CharSequence);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setOverrideApnsEnabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(283, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setOverrideApnsEnabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] setPackagesSuspended(ComponentName param2ComponentName, String param2String, String[] param2ArrayOfString, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeStringArray(param2ArrayOfString);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(89, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().setPackagesSuspended(param2ComponentName, param2String, param2ArrayOfString, param2Boolean); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPasswordExpirationTimeout(ComponentName param2ComponentName, long param2Long, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeLong(param2Long);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPasswordExpirationTimeout(param2ComponentName, param2Long, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPasswordHistoryLength(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPasswordHistoryLength(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPasswordMinimumLength(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumLength(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPasswordMinimumLetters(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumLetters(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPasswordMinimumLowerCase(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumLowerCase(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPasswordMinimumNonLetter(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumNonLetter(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPasswordMinimumNumeric(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumNumeric(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPasswordMinimumSymbols(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumSymbols(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPasswordMinimumUpperCase(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumUpperCase(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPasswordQuality(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPasswordQuality(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPermissionGrantState(ComponentName param2ComponentName, String param2String1, String param2String2, String param2String3, int param2Int, RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeString(param2String2);
            try {
              parcel1.writeString(param2String3);
              try {
                parcel1.writeInt(param2Int);
                if (param2RemoteCallback != null) {
                  parcel1.writeInt(1);
                  param2RemoteCallback.writeToParcel(parcel1, 0);
                } else {
                  parcel1.writeInt(0);
                } 
                if (!this.mRemote.transact(208, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                  IDevicePolicyManager.Stub.getDefaultImpl().setPermissionGrantState(param2ComponentName, param2String1, param2String2, param2String3, param2Int, param2RemoteCallback);
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } 
                parcel2.readException();
                parcel2.recycle();
                parcel1.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2ComponentName;
    }
    
    public void setPermissionPolicy(ComponentName param2ComponentName, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(206, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPermissionPolicy(param2ComponentName, param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPermittedAccessibilityServices(ComponentName param2ComponentName, List param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeList(param2List);
        if (!this.mRemote.transact(126, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().setPermittedAccessibilityServices(param2ComponentName, param2List);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPermittedCrossProfileNotificationListeners(ComponentName param2ComponentName, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(134, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().setPermittedCrossProfileNotificationListeners(param2ComponentName, param2List);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPermittedInputMethods(ComponentName param2ComponentName, List param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeList(param2List);
        if (!this.mRemote.transact(130, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().setPermittedInputMethods(param2ComponentName, param2List);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPersonalAppsSuspended(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(308, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setPersonalAppsSuspended(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setProfileEnabled(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(81, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setProfileEnabled(param2ComponentName);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setProfileName(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(82, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setProfileName(param2ComponentName, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setProfileOwner(ComponentName param2ComponentName, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().setProfileOwner(param2ComponentName, param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setRecommendedGlobalProxy(ComponentName param2ComponentName, ProxyInfo param2ProxyInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ProxyInfo != null) {
          parcel1.writeInt(1);
          param2ProxyInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setRecommendedGlobalProxy(param2ComponentName, param2ProxyInfo);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setRequiredStrongAuthTimeout(ComponentName param2ComponentName, long param2Long, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeLong(param2Long);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setRequiredStrongAuthTimeout(param2ComponentName, param2Long, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setResetPasswordToken(ComponentName param2ComponentName, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(261, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().setResetPasswordToken(param2ComponentName, param2ArrayOfbyte);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setRestrictionsProvider(ComponentName param2ComponentName1, ComponentName param2ComponentName2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName1 != null) {
          parcel1.writeInt(1);
          param2ComponentName1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ComponentName2 != null) {
          parcel1.writeInt(1);
          param2ComponentName2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(120, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setRestrictionsProvider(param2ComponentName1, param2ComponentName2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setScreenCaptureDisabled(ComponentName param2ComponentName, boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool1 = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setScreenCaptureDisabled(param2ComponentName, param2Boolean1, param2Boolean2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSecondaryLockscreenEnabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(153, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setSecondaryLockscreenEnabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSecureSetting(ComponentName param2ComponentName, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(162, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setSecureSetting(param2ComponentName, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSecurityLoggingEnabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(238, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setSecurityLoggingEnabled(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setShortSupportMessage(ComponentName param2ComponentName, CharSequence param2CharSequence) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2CharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(218, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setShortSupportMessage(param2ComponentName, param2CharSequence);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setStartUserSessionMessage(ComponentName param2ComponentName, CharSequence param2CharSequence) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2CharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(273, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setStartUserSessionMessage(param2ComponentName, param2CharSequence);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setStatusBarDisabled(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(202, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IDevicePolicyManager.Stub.getDefaultImpl().setStatusBarDisabled(param2ComponentName, param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int setStorageEncryption(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        int i = 1;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          i = 0; 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          i = IDevicePolicyManager.Stub.getDefaultImpl().setStorageEncryption(param2ComponentName, param2Boolean);
          return i;
        } 
        parcel2.readException();
        i = parcel2.readInt();
        return i;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSystemSetting(ComponentName param2ComponentName, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(161, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setSystemSetting(param2ComponentName, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSystemUpdatePolicy(ComponentName param2ComponentName, SystemUpdatePolicy param2SystemUpdatePolicy) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2SystemUpdatePolicy != null) {
          parcel1.writeInt(1);
          param2SystemUpdatePolicy.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(198, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setSystemUpdatePolicy(param2ComponentName, param2SystemUpdatePolicy);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setTime(ComponentName param2ComponentName, long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(166, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().setTime(param2ComponentName, param2Long);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setTimeZone(ComponentName param2ComponentName, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(167, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().setTimeZone(param2ComponentName, param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setTrustAgentConfiguration(ComponentName param2ComponentName1, ComponentName param2ComponentName2, PersistableBundle param2PersistableBundle, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName1 != null) {
          parcel1.writeInt(1);
          param2ComponentName1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ComponentName2 != null) {
          parcel1.writeInt(1);
          param2ComponentName2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2PersistableBundle != null) {
          parcel1.writeInt(1);
          param2PersistableBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(183, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setTrustAgentConfiguration(param2ComponentName1, param2ComponentName2, param2PersistableBundle, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setUninstallBlocked(ComponentName param2ComponentName, String param2String1, String param2String2, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(171, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setUninstallBlocked(param2ComponentName, param2String1, param2String2, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setUserControlDisabledPackages(ComponentName param2ComponentName, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(303, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setUserControlDisabledPackages(param2ComponentName, param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setUserIcon(ComponentName param2ComponentName, Bitmap param2Bitmap) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Bitmap != null) {
          parcel1.writeInt(1);
          param2Bitmap.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(197, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setUserIcon(param2ComponentName, param2Bitmap);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setUserProvisioningState(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(234, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setUserProvisioningState(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setUserRestriction(ComponentName param2ComponentName, String param2String, boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool1 = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(122, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().setUserRestriction(param2ComponentName, param2String, param2Boolean1, param2Boolean2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startManagedQuickContact(String param2String, long param2Long1, boolean param2Boolean, long param2Long2, Intent param2Intent) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        try {
          parcel1.writeString(param2String);
          try {
            boolean bool;
            parcel1.writeLong(param2Long1);
            if (param2Boolean) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel1.writeInt(bool);
            parcel1.writeLong(param2Long2);
            if (param2Intent != null) {
              parcel1.writeInt(1);
              param2Intent.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              if (!this.mRemote.transact(179, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                IDevicePolicyManager.Stub.getDefaultImpl().startManagedQuickContact(param2String, param2Long1, param2Boolean, param2Long2, param2Intent);
                parcel2.recycle();
                parcel1.recycle();
                return;
              } 
              parcel2.readException();
              parcel2.recycle();
              parcel1.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public int startUserInBackground(ComponentName param2ComponentName, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(143, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().startUserInBackground(param2ComponentName, param2UserHandle); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean startViewCalendarEventInManagedProfile(String param2String, long param2Long1, long param2Long2, long param2Long3, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        try {
          parcel1.writeString(param2String);
          try {
            boolean bool2;
            parcel1.writeLong(param2Long1);
            parcel1.writeLong(param2Long2);
            parcel1.writeLong(param2Long3);
            boolean bool1 = true;
            if (param2Boolean) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            parcel1.writeInt(bool2);
            parcel1.writeInt(param2Int);
            if (!this.mRemote.transact(301, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
              param2Boolean = IDevicePolicyManager.Stub.getDefaultImpl().startViewCalendarEventInManagedProfile(param2String, param2Long1, param2Long2, param2Long3, param2Boolean, param2Int);
              parcel2.recycle();
              parcel1.recycle();
              return param2Boolean;
            } 
            parcel2.readException();
            param2Int = parcel2.readInt();
            if (param2Int != 0) {
              param2Boolean = bool1;
            } else {
              param2Boolean = false;
            } 
            parcel2.recycle();
            parcel1.recycle();
            return param2Boolean;
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public int stopUser(ComponentName param2ComponentName, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(144, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
          return IDevicePolicyManager.Stub.getDefaultImpl().stopUser(param2ComponentName, param2UserHandle); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean switchUser(ComponentName param2ComponentName, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(142, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().switchUser(param2ComponentName, param2UserHandle);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void transferOwnership(ComponentName param2ComponentName1, ComponentName param2ComponentName2, PersistableBundle param2PersistableBundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName1 != null) {
          parcel1.writeInt(1);
          param2ComponentName1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ComponentName2 != null) {
          parcel1.writeInt(1);
          param2ComponentName2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2PersistableBundle != null) {
          parcel1.writeInt(1);
          param2PersistableBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(271, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().transferOwnership(param2ComponentName1, param2ComponentName2, param2PersistableBundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void uninstallCaCerts(ComponentName param2ComponentName, String param2String, String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(92, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().uninstallCaCerts(param2ComponentName, param2String, param2ArrayOfString);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void uninstallPackageWithActiveAdmins(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(245, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().uninstallPackageWithActiveAdmins(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean updateOverrideApn(ComponentName param2ComponentName, int param2Int, ApnSetting param2ApnSetting) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (param2ApnSetting != null) {
          parcel1.writeInt(1);
          param2ApnSetting.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(280, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          bool = IDevicePolicyManager.Stub.getDefaultImpl().updateOverrideApn(param2ComponentName, param2Int, param2ApnSetting);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void wipeDataWithReason(int param2Int, String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
          IDevicePolicyManager.Stub.getDefaultImpl().wipeDataWithReason(param2Int, param2String, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IDevicePolicyManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
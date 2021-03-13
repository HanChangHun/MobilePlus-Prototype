package android.content.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.dex.IArtManager;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public abstract class Stub extends Binder implements IPackageManager {
  private static final String DESCRIPTOR = "android.content.pm.IPackageManager";
  
  static final int TRANSACTION_activitySupportsIntent = 11;
  
  static final int TRANSACTION_addCrossProfileIntentFilter = 61;
  
  static final int TRANSACTION_addPermission = 186;
  
  static final int TRANSACTION_addPermissionAsync = 187;
  
  static final int TRANSACTION_addPersistentPreferredActivity = 59;
  
  static final int TRANSACTION_addPreferredActivity = 55;
  
  static final int TRANSACTION_canForwardTo = 28;
  
  static final int TRANSACTION_canRequestPackageInstalls = 157;
  
  static final int TRANSACTION_canonicalToCurrentPackageNames = 8;
  
  static final int TRANSACTION_checkPackageStartable = 1;
  
  static final int TRANSACTION_checkPermission = 189;
  
  static final int TRANSACTION_checkSignatures = 16;
  
  static final int TRANSACTION_checkUidPermission = 191;
  
  static final int TRANSACTION_checkUidSignatures = 17;
  
  static final int TRANSACTION_clearApplicationProfileData = 90;
  
  static final int TRANSACTION_clearApplicationUserData = 89;
  
  static final int TRANSACTION_clearCrossProfileIntentFilters = 62;
  
  static final int TRANSACTION_clearPackagePersistentPreferredActivities = 60;
  
  static final int TRANSACTION_clearPackagePreferredActivities = 57;
  
  static final int TRANSACTION_compileLayouts = 106;
  
  static final int TRANSACTION_currentToCanonicalPackageNames = 7;
  
  static final int TRANSACTION_deleteApplicationCacheFiles = 87;
  
  static final int TRANSACTION_deleteApplicationCacheFilesAsUser = 88;
  
  static final int TRANSACTION_deleteExistingPackageAsUser = 49;
  
  static final int TRANSACTION_deletePackageAsUser = 47;
  
  static final int TRANSACTION_deletePackageVersioned = 48;
  
  static final int TRANSACTION_deletePreloadsFileCache = 158;
  
  static final int TRANSACTION_dumpProfiles = 107;
  
  static final int TRANSACTION_enterSafeMode = 95;
  
  static final int TRANSACTION_extendVerificationTimeout = 120;
  
  static final int TRANSACTION_findPersistentPreferredActivity = 27;
  
  static final int TRANSACTION_finishPackageInstall = 44;
  
  static final int TRANSACTION_flushPackageRestrictionsAsUser = 83;
  
  static final int TRANSACTION_forceDexOpt = 108;
  
  static final int TRANSACTION_freeStorage = 86;
  
  static final int TRANSACTION_freeStorageAndNotify = 85;
  
  static final int TRANSACTION_getActivityInfo = 10;
  
  static final int TRANSACTION_getAllIntentFilters = 125;
  
  static final int TRANSACTION_getAllPackages = 18;
  
  static final int TRANSACTION_getAppOpPermissionPackages = 184;
  
  static final int TRANSACTION_getAppPredictionServicePackageName = 172;
  
  static final int TRANSACTION_getApplicationEnabledSetting = 81;
  
  static final int TRANSACTION_getApplicationHiddenSettingAsUser = 132;
  
  static final int TRANSACTION_getApplicationInfo = 9;
  
  static final int TRANSACTION_getArtManager = 163;
  
  static final int TRANSACTION_getAttentionServicePackageName = 170;
  
  static final int TRANSACTION_getBlockUninstallForUser = 137;
  
  static final int TRANSACTION_getChangedPackages = 152;
  
  static final int TRANSACTION_getComponentEnabledSetting = 79;
  
  static final int TRANSACTION_getContentCaptureServicePackageName = 176;
  
  static final int TRANSACTION_getDeclaredSharedLibraries = 156;
  
  static final int TRANSACTION_getDefaultAppsBackup = 70;
  
  static final int TRANSACTION_getDefaultTextClassifierPackageName = 168;
  
  static final int TRANSACTION_getFlagsForUid = 23;
  
  static final int TRANSACTION_getHarmfulAppWarning = 165;
  
  static final int TRANSACTION_getHomeActivities = 74;
  
  static final int TRANSACTION_getIncidentReportApproverPackageName = 175;
  
  static final int TRANSACTION_getInstallLocation = 117;
  
  static final int TRANSACTION_getInstallReason = 154;
  
  static final int TRANSACTION_getInstallSourceInfo = 51;
  
  static final int TRANSACTION_getInstalledApplications = 37;
  
  static final int TRANSACTION_getInstalledModules = 179;
  
  static final int TRANSACTION_getInstalledPackages = 35;
  
  static final int TRANSACTION_getInstallerPackageName = 50;
  
  static final int TRANSACTION_getInstantAppAndroidId = 162;
  
  static final int TRANSACTION_getInstantAppCookie = 144;
  
  static final int TRANSACTION_getInstantAppIcon = 146;
  
  static final int TRANSACTION_getInstantAppInstallerComponent = 161;
  
  static final int TRANSACTION_getInstantAppResolverComponent = 159;
  
  static final int TRANSACTION_getInstantAppResolverSettingsComponent = 160;
  
  static final int TRANSACTION_getInstantApps = 143;
  
  static final int TRANSACTION_getInstrumentationInfo = 42;
  
  static final int TRANSACTION_getIntentFilterVerificationBackup = 72;
  
  static final int TRANSACTION_getIntentFilterVerifications = 124;
  
  static final int TRANSACTION_getIntentVerificationStatus = 122;
  
  static final int TRANSACTION_getKeySetByAlias = 138;
  
  static final int TRANSACTION_getLastChosenActivity = 53;
  
  static final int TRANSACTION_getMimeGroup = 193;
  
  static final int TRANSACTION_getModuleInfo = 180;
  
  static final int TRANSACTION_getMoveStatus = 111;
  
  static final int TRANSACTION_getNameForUid = 20;
  
  static final int TRANSACTION_getNamesForUids = 21;
  
  static final int TRANSACTION_getPackageGids = 6;
  
  static final int TRANSACTION_getPackageInfo = 3;
  
  static final int TRANSACTION_getPackageInfoVersioned = 4;
  
  static final int TRANSACTION_getPackageInstaller = 135;
  
  static final int TRANSACTION_getPackageSizeInfo = 91;
  
  static final int TRANSACTION_getPackageUid = 5;
  
  static final int TRANSACTION_getPackagesForUid = 19;
  
  static final int TRANSACTION_getPackagesHoldingPermissions = 36;
  
  static final int TRANSACTION_getPermissionControllerPackageName = 142;
  
  static final int TRANSACTION_getPermissionGroupInfo = 185;
  
  static final int TRANSACTION_getPersistentApplications = 38;
  
  static final int TRANSACTION_getPreferredActivities = 58;
  
  static final int TRANSACTION_getPreferredActivityBackup = 68;
  
  static final int TRANSACTION_getPrivateFlagsForUid = 24;
  
  static final int TRANSACTION_getProviderInfo = 14;
  
  static final int TRANSACTION_getReceiverInfo = 12;
  
  static final int TRANSACTION_getRuntimePermissionsVersion = 181;
  
  static final int TRANSACTION_getServiceInfo = 13;
  
  static final int TRANSACTION_getServicesSystemSharedLibraryPackageName = 150;
  
  static final int TRANSACTION_getSetupWizardPackageName = 174;
  
  static final int TRANSACTION_getSharedLibraries = 155;
  
  static final int TRANSACTION_getSharedSystemSharedLibraryPackageName = 151;
  
  static final int TRANSACTION_getSigningKeySet = 139;
  
  static final int TRANSACTION_getSuspendedPackageAppExtras = 67;
  
  static final int TRANSACTION_getSystemAvailableFeatures = 93;
  
  static final int TRANSACTION_getSystemCaptionsServicePackageName = 173;
  
  static final int TRANSACTION_getSystemSharedLibraryNames = 92;
  
  static final int TRANSACTION_getSystemTextClassifierPackageName = 169;
  
  static final int TRANSACTION_getUidForSharedUser = 22;
  
  static final int TRANSACTION_getUnsuspendablePackagesForUser = 65;
  
  static final int TRANSACTION_getVerifierDeviceIdentity = 126;
  
  static final int TRANSACTION_getWellbeingPackageName = 171;
  
  static final int TRANSACTION_grantImplicitAccess = 195;
  
  static final int TRANSACTION_grantRuntimePermission = 190;
  
  static final int TRANSACTION_hasSigningCertificate = 166;
  
  static final int TRANSACTION_hasSystemFeature = 94;
  
  static final int TRANSACTION_hasSystemUidErrors = 98;
  
  static final int TRANSACTION_hasUidSigningCertificate = 167;
  
  static final int TRANSACTION_installExistingPackageAsUser = 118;
  
  static final int TRANSACTION_isAutoRevokeWhitelisted = 194;
  
  static final int TRANSACTION_isDeviceUpgrading = 129;
  
  static final int TRANSACTION_isFirstBoot = 127;
  
  static final int TRANSACTION_isInstantApp = 147;
  
  static final int TRANSACTION_isOnlyCoreApps = 128;
  
  static final int TRANSACTION_isPackageAvailable = 2;
  
  static final int TRANSACTION_isPackageDeviceAdminOnAnyUser = 153;
  
  static final int TRANSACTION_isPackageSignedByKeySet = 140;
  
  static final int TRANSACTION_isPackageSignedByKeySetExactly = 141;
  
  static final int TRANSACTION_isPackageStateProtected = 177;
  
  static final int TRANSACTION_isPackageSuspendedForUser = 66;
  
  static final int TRANSACTION_isProtectedBroadcast = 15;
  
  static final int TRANSACTION_isSafeMode = 96;
  
  static final int TRANSACTION_isStorageLow = 130;
  
  static final int TRANSACTION_isUidPrivileged = 25;
  
  static final int TRANSACTION_logAppProcessStartIfNeeded = 82;
  
  static final int TRANSACTION_movePackage = 114;
  
  static final int TRANSACTION_movePrimaryStorage = 115;
  
  static final int TRANSACTION_notifyDexLoad = 102;
  
  static final int TRANSACTION_notifyPackageUse = 101;
  
  static final int TRANSACTION_notifyPackagesReplacedReceived = 183;
  
  static final int TRANSACTION_overrideLabelAndIcon = 76;
  
  static final int TRANSACTION_performDexOptMode = 104;
  
  static final int TRANSACTION_performDexOptSecondary = 105;
  
  static final int TRANSACTION_performFstrimIfNeeded = 99;
  
  static final int TRANSACTION_queryContentProviders = 41;
  
  static final int TRANSACTION_queryInstrumentation = 43;
  
  static final int TRANSACTION_queryIntentActivities = 29;
  
  static final int TRANSACTION_queryIntentActivityOptions = 30;
  
  static final int TRANSACTION_queryIntentContentProviders = 34;
  
  static final int TRANSACTION_queryIntentReceivers = 31;
  
  static final int TRANSACTION_queryIntentServices = 33;
  
  static final int TRANSACTION_querySyncProviders = 40;
  
  static final int TRANSACTION_reconcileSecondaryDexFiles = 110;
  
  static final int TRANSACTION_registerDexModule = 103;
  
  static final int TRANSACTION_registerMoveCallback = 112;
  
  static final int TRANSACTION_removePermission = 188;
  
  static final int TRANSACTION_replacePreferredActivity = 56;
  
  static final int TRANSACTION_resetApplicationPreferences = 52;
  
  static final int TRANSACTION_resolveContentProvider = 39;
  
  static final int TRANSACTION_resolveIntent = 26;
  
  static final int TRANSACTION_resolveService = 32;
  
  static final int TRANSACTION_restoreDefaultApps = 71;
  
  static final int TRANSACTION_restoreIntentFilterVerification = 73;
  
  static final int TRANSACTION_restoreLabelAndIcon = 77;
  
  static final int TRANSACTION_restorePreferredActivities = 69;
  
  static final int TRANSACTION_runBackgroundDexoptJob = 109;
  
  static final int TRANSACTION_sendDeviceCustomizationReadyBroadcast = 178;
  
  static final int TRANSACTION_setApplicationCategoryHint = 46;
  
  static final int TRANSACTION_setApplicationEnabledSetting = 80;
  
  static final int TRANSACTION_setApplicationHiddenSettingAsUser = 131;
  
  static final int TRANSACTION_setBlockUninstallForUser = 136;
  
  static final int TRANSACTION_setComponentEnabledSetting = 78;
  
  static final int TRANSACTION_setDistractingPackageRestrictionsAsUser = 63;
  
  static final int TRANSACTION_setHarmfulAppWarning = 164;
  
  static final int TRANSACTION_setHomeActivity = 75;
  
  static final int TRANSACTION_setInstallLocation = 116;
  
  static final int TRANSACTION_setInstallerPackageName = 45;
  
  static final int TRANSACTION_setInstantAppCookie = 145;
  
  static final int TRANSACTION_setLastChosenActivity = 54;
  
  static final int TRANSACTION_setMimeGroup = 192;
  
  static final int TRANSACTION_setPackageStoppedState = 84;
  
  static final int TRANSACTION_setPackagesSuspendedAsUser = 64;
  
  static final int TRANSACTION_setRequiredForSystemUser = 148;
  
  static final int TRANSACTION_setRuntimePermissionsVersion = 182;
  
  static final int TRANSACTION_setSystemAppHiddenUntilInstalled = 133;
  
  static final int TRANSACTION_setSystemAppInstallState = 134;
  
  static final int TRANSACTION_setUpdateAvailable = 149;
  
  static final int TRANSACTION_systemReady = 97;
  
  static final int TRANSACTION_unregisterMoveCallback = 113;
  
  static final int TRANSACTION_updateIntentVerificationStatus = 123;
  
  static final int TRANSACTION_updatePackagesIfNeeded = 100;
  
  static final int TRANSACTION_verifyIntentFilter = 121;
  
  static final int TRANSACTION_verifyPendingInstall = 119;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageManager");
  }
  
  public static IPackageManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageManager");
    return (iInterface != null && iInterface instanceof IPackageManager) ? (IPackageManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 195:
        return "grantImplicitAccess";
      case 194:
        return "isAutoRevokeWhitelisted";
      case 193:
        return "getMimeGroup";
      case 192:
        return "setMimeGroup";
      case 191:
        return "checkUidPermission";
      case 190:
        return "grantRuntimePermission";
      case 189:
        return "checkPermission";
      case 188:
        return "removePermission";
      case 187:
        return "addPermissionAsync";
      case 186:
        return "addPermission";
      case 185:
        return "getPermissionGroupInfo";
      case 184:
        return "getAppOpPermissionPackages";
      case 183:
        return "notifyPackagesReplacedReceived";
      case 182:
        return "setRuntimePermissionsVersion";
      case 181:
        return "getRuntimePermissionsVersion";
      case 180:
        return "getModuleInfo";
      case 179:
        return "getInstalledModules";
      case 178:
        return "sendDeviceCustomizationReadyBroadcast";
      case 177:
        return "isPackageStateProtected";
      case 176:
        return "getContentCaptureServicePackageName";
      case 175:
        return "getIncidentReportApproverPackageName";
      case 174:
        return "getSetupWizardPackageName";
      case 173:
        return "getSystemCaptionsServicePackageName";
      case 172:
        return "getAppPredictionServicePackageName";
      case 171:
        return "getWellbeingPackageName";
      case 170:
        return "getAttentionServicePackageName";
      case 169:
        return "getSystemTextClassifierPackageName";
      case 168:
        return "getDefaultTextClassifierPackageName";
      case 167:
        return "hasUidSigningCertificate";
      case 166:
        return "hasSigningCertificate";
      case 165:
        return "getHarmfulAppWarning";
      case 164:
        return "setHarmfulAppWarning";
      case 163:
        return "getArtManager";
      case 162:
        return "getInstantAppAndroidId";
      case 161:
        return "getInstantAppInstallerComponent";
      case 160:
        return "getInstantAppResolverSettingsComponent";
      case 159:
        return "getInstantAppResolverComponent";
      case 158:
        return "deletePreloadsFileCache";
      case 157:
        return "canRequestPackageInstalls";
      case 156:
        return "getDeclaredSharedLibraries";
      case 155:
        return "getSharedLibraries";
      case 154:
        return "getInstallReason";
      case 153:
        return "isPackageDeviceAdminOnAnyUser";
      case 152:
        return "getChangedPackages";
      case 151:
        return "getSharedSystemSharedLibraryPackageName";
      case 150:
        return "getServicesSystemSharedLibraryPackageName";
      case 149:
        return "setUpdateAvailable";
      case 148:
        return "setRequiredForSystemUser";
      case 147:
        return "isInstantApp";
      case 146:
        return "getInstantAppIcon";
      case 145:
        return "setInstantAppCookie";
      case 144:
        return "getInstantAppCookie";
      case 143:
        return "getInstantApps";
      case 142:
        return "getPermissionControllerPackageName";
      case 141:
        return "isPackageSignedByKeySetExactly";
      case 140:
        return "isPackageSignedByKeySet";
      case 139:
        return "getSigningKeySet";
      case 138:
        return "getKeySetByAlias";
      case 137:
        return "getBlockUninstallForUser";
      case 136:
        return "setBlockUninstallForUser";
      case 135:
        return "getPackageInstaller";
      case 134:
        return "setSystemAppInstallState";
      case 133:
        return "setSystemAppHiddenUntilInstalled";
      case 132:
        return "getApplicationHiddenSettingAsUser";
      case 131:
        return "setApplicationHiddenSettingAsUser";
      case 130:
        return "isStorageLow";
      case 129:
        return "isDeviceUpgrading";
      case 128:
        return "isOnlyCoreApps";
      case 127:
        return "isFirstBoot";
      case 126:
        return "getVerifierDeviceIdentity";
      case 125:
        return "getAllIntentFilters";
      case 124:
        return "getIntentFilterVerifications";
      case 123:
        return "updateIntentVerificationStatus";
      case 122:
        return "getIntentVerificationStatus";
      case 121:
        return "verifyIntentFilter";
      case 120:
        return "extendVerificationTimeout";
      case 119:
        return "verifyPendingInstall";
      case 118:
        return "installExistingPackageAsUser";
      case 117:
        return "getInstallLocation";
      case 116:
        return "setInstallLocation";
      case 115:
        return "movePrimaryStorage";
      case 114:
        return "movePackage";
      case 113:
        return "unregisterMoveCallback";
      case 112:
        return "registerMoveCallback";
      case 111:
        return "getMoveStatus";
      case 110:
        return "reconcileSecondaryDexFiles";
      case 109:
        return "runBackgroundDexoptJob";
      case 108:
        return "forceDexOpt";
      case 107:
        return "dumpProfiles";
      case 106:
        return "compileLayouts";
      case 105:
        return "performDexOptSecondary";
      case 104:
        return "performDexOptMode";
      case 103:
        return "registerDexModule";
      case 102:
        return "notifyDexLoad";
      case 101:
        return "notifyPackageUse";
      case 100:
        return "updatePackagesIfNeeded";
      case 99:
        return "performFstrimIfNeeded";
      case 98:
        return "hasSystemUidErrors";
      case 97:
        return "systemReady";
      case 96:
        return "isSafeMode";
      case 95:
        return "enterSafeMode";
      case 94:
        return "hasSystemFeature";
      case 93:
        return "getSystemAvailableFeatures";
      case 92:
        return "getSystemSharedLibraryNames";
      case 91:
        return "getPackageSizeInfo";
      case 90:
        return "clearApplicationProfileData";
      case 89:
        return "clearApplicationUserData";
      case 88:
        return "deleteApplicationCacheFilesAsUser";
      case 87:
        return "deleteApplicationCacheFiles";
      case 86:
        return "freeStorage";
      case 85:
        return "freeStorageAndNotify";
      case 84:
        return "setPackageStoppedState";
      case 83:
        return "flushPackageRestrictionsAsUser";
      case 82:
        return "logAppProcessStartIfNeeded";
      case 81:
        return "getApplicationEnabledSetting";
      case 80:
        return "setApplicationEnabledSetting";
      case 79:
        return "getComponentEnabledSetting";
      case 78:
        return "setComponentEnabledSetting";
      case 77:
        return "restoreLabelAndIcon";
      case 76:
        return "overrideLabelAndIcon";
      case 75:
        return "setHomeActivity";
      case 74:
        return "getHomeActivities";
      case 73:
        return "restoreIntentFilterVerification";
      case 72:
        return "getIntentFilterVerificationBackup";
      case 71:
        return "restoreDefaultApps";
      case 70:
        return "getDefaultAppsBackup";
      case 69:
        return "restorePreferredActivities";
      case 68:
        return "getPreferredActivityBackup";
      case 67:
        return "getSuspendedPackageAppExtras";
      case 66:
        return "isPackageSuspendedForUser";
      case 65:
        return "getUnsuspendablePackagesForUser";
      case 64:
        return "setPackagesSuspendedAsUser";
      case 63:
        return "setDistractingPackageRestrictionsAsUser";
      case 62:
        return "clearCrossProfileIntentFilters";
      case 61:
        return "addCrossProfileIntentFilter";
      case 60:
        return "clearPackagePersistentPreferredActivities";
      case 59:
        return "addPersistentPreferredActivity";
      case 58:
        return "getPreferredActivities";
      case 57:
        return "clearPackagePreferredActivities";
      case 56:
        return "replacePreferredActivity";
      case 55:
        return "addPreferredActivity";
      case 54:
        return "setLastChosenActivity";
      case 53:
        return "getLastChosenActivity";
      case 52:
        return "resetApplicationPreferences";
      case 51:
        return "getInstallSourceInfo";
      case 50:
        return "getInstallerPackageName";
      case 49:
        return "deleteExistingPackageAsUser";
      case 48:
        return "deletePackageVersioned";
      case 47:
        return "deletePackageAsUser";
      case 46:
        return "setApplicationCategoryHint";
      case 45:
        return "setInstallerPackageName";
      case 44:
        return "finishPackageInstall";
      case 43:
        return "queryInstrumentation";
      case 42:
        return "getInstrumentationInfo";
      case 41:
        return "queryContentProviders";
      case 40:
        return "querySyncProviders";
      case 39:
        return "resolveContentProvider";
      case 38:
        return "getPersistentApplications";
      case 37:
        return "getInstalledApplications";
      case 36:
        return "getPackagesHoldingPermissions";
      case 35:
        return "getInstalledPackages";
      case 34:
        return "queryIntentContentProviders";
      case 33:
        return "queryIntentServices";
      case 32:
        return "resolveService";
      case 31:
        return "queryIntentReceivers";
      case 30:
        return "queryIntentActivityOptions";
      case 29:
        return "queryIntentActivities";
      case 28:
        return "canForwardTo";
      case 27:
        return "findPersistentPreferredActivity";
      case 26:
        return "resolveIntent";
      case 25:
        return "isUidPrivileged";
      case 24:
        return "getPrivateFlagsForUid";
      case 23:
        return "getFlagsForUid";
      case 22:
        return "getUidForSharedUser";
      case 21:
        return "getNamesForUids";
      case 20:
        return "getNameForUid";
      case 19:
        return "getPackagesForUid";
      case 18:
        return "getAllPackages";
      case 17:
        return "checkUidSignatures";
      case 16:
        return "checkSignatures";
      case 15:
        return "isProtectedBroadcast";
      case 14:
        return "getProviderInfo";
      case 13:
        return "getServiceInfo";
      case 12:
        return "getReceiverInfo";
      case 11:
        return "activitySupportsIntent";
      case 10:
        return "getActivityInfo";
      case 9:
        return "getApplicationInfo";
      case 8:
        return "canonicalToCurrentPackageNames";
      case 7:
        return "currentToCanonicalPackageNames";
      case 6:
        return "getPackageGids";
      case 5:
        return "getPackageUid";
      case 4:
        return "getPackageInfoVersioned";
      case 3:
        return "getPackageInfo";
      case 2:
        return "isPackageAvailable";
      case 1:
        break;
    } 
    return "checkPackageStartable";
  }
  
  public static boolean setDefaultImpl(IPackageManager paramIPackageManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageManager != null) {
        Proxy.sDefaultImpl = paramIPackageManager;
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
    HashMap<Object, Object> hashMap;
    if (paramInt1 != 1598968902) {
      boolean bool11;
      int i5;
      boolean bool10;
      int i4;
      boolean bool9;
      int i3;
      boolean bool8;
      int i2;
      boolean bool7;
      int i1;
      boolean bool6;
      int n;
      boolean bool5;
      int m;
      boolean bool4;
      int k;
      boolean bool3;
      int j;
      boolean bool2;
      int i;
      boolean bool1;
      List<String> list2;
      PermissionGroupInfo permissionGroupInfo;
      String[] arrayOfString6;
      ModuleInfo moduleInfo;
      List<ModuleInfo> list;
      String str6;
      CharSequence charSequence1;
      IBinder iBinder2;
      String str5;
      ComponentName componentName1;
      ParceledListSlice parceledListSlice7;
      ChangedPackages changedPackages;
      String str4;
      Bitmap bitmap;
      byte[] arrayOfByte2;
      ParceledListSlice parceledListSlice6;
      String str3;
      KeySet keySet;
      IArtManager iArtManager1;
      IBinder iBinder1;
      VerifierDeviceIdentity verifierDeviceIdentity;
      ParceledListSlice parceledListSlice5;
      String[] arrayOfString5;
      ArrayList<ResolveInfo> arrayList1;
      byte[] arrayOfByte1;
      Bundle bundle;
      String[] arrayOfString4;
      ResolveInfo resolveInfo3;
      InstallSourceInfo installSourceInfo;
      String str2;
      ParceledListSlice parceledListSlice4;
      InstrumentationInfo instrumentationInfo;
      ParceledListSlice parceledListSlice3;
      ArrayList<ProviderInfo> arrayList;
      ProviderInfo providerInfo2;
      ParceledListSlice parceledListSlice2;
      ResolveInfo resolveInfo2;
      ParceledListSlice parceledListSlice1;
      ResolveInfo resolveInfo1;
      String[] arrayOfString3;
      String str1;
      String[] arrayOfString2;
      List<String> list1;
      ProviderInfo providerInfo1;
      ServiceInfo serviceInfo;
      ActivityInfo activityInfo;
      ApplicationInfo applicationInfo;
      String[] arrayOfString1;
      int[] arrayOfInt;
      PackageInfo packageInfo;
      String str7;
      IArtManager iArtManager2;
      String str8;
      ArrayList<ComponentName> arrayList2;
      ComponentName[] arrayOfComponentName;
      String str10;
      Intent[] arrayOfIntent;
      IPackageInstaller iPackageInstaller;
      String str12;
      ComponentName componentName2;
      ArrayList<IntentFilter> arrayList3;
      long l;
      String[] arrayOfString7;
      String str9 = null;
      String str11 = null;
      CharSequence charSequence2 = null;
      boolean bool12 = false;
      boolean bool13 = false;
      boolean bool14 = false;
      boolean bool15 = false;
      boolean bool16 = false;
      boolean bool17 = false;
      boolean bool18 = false;
      boolean bool19 = false;
      boolean bool20 = false;
      boolean bool21 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 195:
          paramParcel1.enforceInterface("android.content.pm.IPackageManager");
          grantImplicitAccess(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 194:
          paramParcel1.enforceInterface("android.content.pm.IPackageManager");
          bool11 = isAutoRevokeWhitelisted(paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 193:
          paramParcel1.enforceInterface("android.content.pm.IPackageManager");
          list2 = getMimeGroup(paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list2);
          return true;
        case 192:
          list2.enforceInterface("android.content.pm.IPackageManager");
          setMimeGroup(list2.readString(), list2.readString(), list2.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 191:
          list2.enforceInterface("android.content.pm.IPackageManager");
          i5 = checkUidPermission(list2.readString(), list2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i5);
          return true;
        case 190:
          list2.enforceInterface("android.content.pm.IPackageManager");
          grantRuntimePermission(list2.readString(), list2.readString(), list2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 189:
          list2.enforceInterface("android.content.pm.IPackageManager");
          i5 = checkPermission(list2.readString(), list2.readString(), list2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i5);
          return true;
        case 188:
          list2.enforceInterface("android.content.pm.IPackageManager");
          removePermission(list2.readString());
          paramParcel2.writeNoException();
          return true;
        case 187:
          list2.enforceInterface("android.content.pm.IPackageManager");
          if (list2.readInt() != 0) {
            PermissionInfo permissionInfo = (PermissionInfo)PermissionInfo.CREATOR.createFromParcel((Parcel)list2);
          } else {
            list2 = null;
          } 
          bool10 = addPermissionAsync((PermissionInfo)list2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 186:
          list2.enforceInterface("android.content.pm.IPackageManager");
          if (list2.readInt() != 0) {
            PermissionInfo permissionInfo = (PermissionInfo)PermissionInfo.CREATOR.createFromParcel((Parcel)list2);
          } else {
            list2 = null;
          } 
          bool10 = addPermission((PermissionInfo)list2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 185:
          list2.enforceInterface("android.content.pm.IPackageManager");
          permissionGroupInfo = getPermissionGroupInfo(list2.readString(), list2.readInt());
          paramParcel2.writeNoException();
          if (permissionGroupInfo != null) {
            paramParcel2.writeInt(1);
            permissionGroupInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 184:
          permissionGroupInfo.enforceInterface("android.content.pm.IPackageManager");
          arrayOfString6 = getAppOpPermissionPackages(permissionGroupInfo.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString6);
          return true;
        case 183:
          arrayOfString6.enforceInterface("android.content.pm.IPackageManager");
          notifyPackagesReplacedReceived(arrayOfString6.createStringArray());
          paramParcel2.writeNoException();
          return true;
        case 182:
          arrayOfString6.enforceInterface("android.content.pm.IPackageManager");
          setRuntimePermissionsVersion(arrayOfString6.readInt(), arrayOfString6.readInt());
          paramParcel2.writeNoException();
          return true;
        case 181:
          arrayOfString6.enforceInterface("android.content.pm.IPackageManager");
          i4 = getRuntimePermissionsVersion(arrayOfString6.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i4);
          return true;
        case 180:
          arrayOfString6.enforceInterface("android.content.pm.IPackageManager");
          moduleInfo = getModuleInfo(arrayOfString6.readString(), arrayOfString6.readInt());
          paramParcel2.writeNoException();
          if (moduleInfo != null) {
            paramParcel2.writeInt(1);
            moduleInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 179:
          moduleInfo.enforceInterface("android.content.pm.IPackageManager");
          list = getInstalledModules(moduleInfo.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 178:
          list.enforceInterface("android.content.pm.IPackageManager");
          sendDeviceCustomizationReadyBroadcast();
          paramParcel2.writeNoException();
          return true;
        case 177:
          list.enforceInterface("android.content.pm.IPackageManager");
          bool9 = isPackageStateProtected(list.readString(), list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 176:
          list.enforceInterface("android.content.pm.IPackageManager");
          str6 = getContentCaptureServicePackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str6);
          return true;
        case 175:
          str6.enforceInterface("android.content.pm.IPackageManager");
          str6 = getIncidentReportApproverPackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str6);
          return true;
        case 174:
          str6.enforceInterface("android.content.pm.IPackageManager");
          str6 = getSetupWizardPackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str6);
          return true;
        case 173:
          str6.enforceInterface("android.content.pm.IPackageManager");
          str6 = getSystemCaptionsServicePackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str6);
          return true;
        case 172:
          str6.enforceInterface("android.content.pm.IPackageManager");
          str6 = getAppPredictionServicePackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str6);
          return true;
        case 171:
          str6.enforceInterface("android.content.pm.IPackageManager");
          str6 = getWellbeingPackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str6);
          return true;
        case 170:
          str6.enforceInterface("android.content.pm.IPackageManager");
          str6 = getAttentionServicePackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str6);
          return true;
        case 169:
          str6.enforceInterface("android.content.pm.IPackageManager");
          str6 = getSystemTextClassifierPackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str6);
          return true;
        case 168:
          str6.enforceInterface("android.content.pm.IPackageManager");
          str6 = getDefaultTextClassifierPackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str6);
          return true;
        case 167:
          str6.enforceInterface("android.content.pm.IPackageManager");
          bool9 = hasUidSigningCertificate(str6.readInt(), str6.createByteArray(), str6.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 166:
          str6.enforceInterface("android.content.pm.IPackageManager");
          bool9 = hasSigningCertificate(str6.readString(), str6.createByteArray(), str6.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 165:
          str6.enforceInterface("android.content.pm.IPackageManager");
          charSequence1 = getHarmfulAppWarning(str6.readString(), str6.readInt());
          paramParcel2.writeNoException();
          if (charSequence1 != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence1, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 164:
          charSequence1.enforceInterface("android.content.pm.IPackageManager");
          str9 = charSequence1.readString();
          if (charSequence1.readInt() != 0) {
            charSequence2 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)charSequence1);
          } else {
            charSequence2 = null;
          } 
          setHarmfulAppWarning(str9, charSequence2, charSequence1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 163:
          charSequence1.enforceInterface("android.content.pm.IPackageManager");
          iArtManager2 = getArtManager();
          paramParcel2.writeNoException();
          charSequence1 = charSequence2;
          if (iArtManager2 != null)
            iBinder2 = iArtManager2.asBinder(); 
          paramParcel2.writeStrongBinder(iBinder2);
          return true;
        case 162:
          iBinder2.enforceInterface("android.content.pm.IPackageManager");
          str5 = getInstantAppAndroidId(iBinder2.readString(), iBinder2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str5);
          return true;
        case 161:
          str5.enforceInterface("android.content.pm.IPackageManager");
          componentName1 = getInstantAppInstallerComponent();
          paramParcel2.writeNoException();
          if (componentName1 != null) {
            paramParcel2.writeInt(1);
            componentName1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 160:
          componentName1.enforceInterface("android.content.pm.IPackageManager");
          componentName1 = getInstantAppResolverSettingsComponent();
          paramParcel2.writeNoException();
          if (componentName1 != null) {
            paramParcel2.writeInt(1);
            componentName1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 159:
          componentName1.enforceInterface("android.content.pm.IPackageManager");
          componentName1 = getInstantAppResolverComponent();
          paramParcel2.writeNoException();
          if (componentName1 != null) {
            paramParcel2.writeInt(1);
            componentName1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 158:
          componentName1.enforceInterface("android.content.pm.IPackageManager");
          deletePreloadsFileCache();
          paramParcel2.writeNoException();
          return true;
        case 157:
          componentName1.enforceInterface("android.content.pm.IPackageManager");
          bool9 = canRequestPackageInstalls(componentName1.readString(), componentName1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 156:
          componentName1.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice7 = getDeclaredSharedLibraries(componentName1.readString(), componentName1.readInt(), componentName1.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice7 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice7.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 155:
          parceledListSlice7.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice7 = getSharedLibraries(parceledListSlice7.readString(), parceledListSlice7.readInt(), parceledListSlice7.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice7 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice7.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 154:
          parceledListSlice7.enforceInterface("android.content.pm.IPackageManager");
          i3 = getInstallReason(parceledListSlice7.readString(), parceledListSlice7.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i3);
          return true;
        case 153:
          parceledListSlice7.enforceInterface("android.content.pm.IPackageManager");
          bool8 = isPackageDeviceAdminOnAnyUser(parceledListSlice7.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 152:
          parceledListSlice7.enforceInterface("android.content.pm.IPackageManager");
          changedPackages = getChangedPackages(parceledListSlice7.readInt(), parceledListSlice7.readInt());
          paramParcel2.writeNoException();
          if (changedPackages != null) {
            paramParcel2.writeInt(1);
            changedPackages.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 151:
          changedPackages.enforceInterface("android.content.pm.IPackageManager");
          str4 = getSharedSystemSharedLibraryPackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str4);
          return true;
        case 150:
          str4.enforceInterface("android.content.pm.IPackageManager");
          str4 = getServicesSystemSharedLibraryPackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str4);
          return true;
        case 149:
          str4.enforceInterface("android.content.pm.IPackageManager");
          charSequence2 = str4.readString();
          if (str4.readInt() != 0)
            bool21 = true; 
          setUpdateAvailable((String)charSequence2, bool21);
          paramParcel2.writeNoException();
          return true;
        case 148:
          str4.enforceInterface("android.content.pm.IPackageManager");
          charSequence2 = str4.readString();
          bool21 = bool12;
          if (str4.readInt() != 0)
            bool21 = true; 
          bool8 = setRequiredForSystemUser((String)charSequence2, bool21);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 147:
          str4.enforceInterface("android.content.pm.IPackageManager");
          bool8 = isInstantApp(str4.readString(), str4.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 146:
          str4.enforceInterface("android.content.pm.IPackageManager");
          bitmap = getInstantAppIcon(str4.readString(), str4.readInt());
          paramParcel2.writeNoException();
          if (bitmap != null) {
            paramParcel2.writeInt(1);
            bitmap.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 145:
          bitmap.enforceInterface("android.content.pm.IPackageManager");
          bool8 = setInstantAppCookie(bitmap.readString(), bitmap.createByteArray(), bitmap.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 144:
          bitmap.enforceInterface("android.content.pm.IPackageManager");
          arrayOfByte2 = getInstantAppCookie(bitmap.readString(), bitmap.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeByteArray(arrayOfByte2);
          return true;
        case 143:
          arrayOfByte2.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice6 = getInstantApps(arrayOfByte2.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice6 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice6.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 142:
          parceledListSlice6.enforceInterface("android.content.pm.IPackageManager");
          str3 = getPermissionControllerPackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str3);
          return true;
        case 141:
          str3.enforceInterface("android.content.pm.IPackageManager");
          charSequence2 = str3.readString();
          if (str3.readInt() != 0) {
            KeySet keySet1 = (KeySet)KeySet.CREATOR.createFromParcel((Parcel)str3);
          } else {
            str3 = null;
          } 
          bool8 = isPackageSignedByKeySetExactly((String)charSequence2, (KeySet)str3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 140:
          str3.enforceInterface("android.content.pm.IPackageManager");
          charSequence2 = str3.readString();
          if (str3.readInt() != 0) {
            KeySet keySet1 = (KeySet)KeySet.CREATOR.createFromParcel((Parcel)str3);
          } else {
            str3 = null;
          } 
          bool8 = isPackageSignedByKeySet((String)charSequence2, (KeySet)str3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 139:
          str3.enforceInterface("android.content.pm.IPackageManager");
          keySet = getSigningKeySet(str3.readString());
          paramParcel2.writeNoException();
          if (keySet != null) {
            paramParcel2.writeInt(1);
            keySet.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 138:
          keySet.enforceInterface("android.content.pm.IPackageManager");
          keySet = getKeySetByAlias(keySet.readString(), keySet.readString());
          paramParcel2.writeNoException();
          if (keySet != null) {
            paramParcel2.writeInt(1);
            keySet.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 137:
          keySet.enforceInterface("android.content.pm.IPackageManager");
          bool8 = getBlockUninstallForUser(keySet.readString(), keySet.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 136:
          keySet.enforceInterface("android.content.pm.IPackageManager");
          charSequence2 = keySet.readString();
          bool21 = bool13;
          if (keySet.readInt() != 0)
            bool21 = true; 
          bool8 = setBlockUninstallForUser((String)charSequence2, bool21, keySet.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 135:
          keySet.enforceInterface("android.content.pm.IPackageManager");
          iPackageInstaller = getPackageInstaller();
          paramParcel2.writeNoException();
          iArtManager1 = iArtManager2;
          if (iPackageInstaller != null)
            iBinder1 = iPackageInstaller.asBinder(); 
          paramParcel2.writeStrongBinder(iBinder1);
          return true;
        case 134:
          iBinder1.enforceInterface("android.content.pm.IPackageManager");
          str12 = iBinder1.readString();
          bool21 = bool14;
          if (iBinder1.readInt() != 0)
            bool21 = true; 
          bool8 = setSystemAppInstallState(str12, bool21, iBinder1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 133:
          iBinder1.enforceInterface("android.content.pm.IPackageManager");
          str12 = iBinder1.readString();
          bool21 = bool15;
          if (iBinder1.readInt() != 0)
            bool21 = true; 
          setSystemAppHiddenUntilInstalled(str12, bool21);
          paramParcel2.writeNoException();
          return true;
        case 132:
          iBinder1.enforceInterface("android.content.pm.IPackageManager");
          bool8 = getApplicationHiddenSettingAsUser(iBinder1.readString(), iBinder1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 131:
          iBinder1.enforceInterface("android.content.pm.IPackageManager");
          str12 = iBinder1.readString();
          bool21 = bool16;
          if (iBinder1.readInt() != 0)
            bool21 = true; 
          bool8 = setApplicationHiddenSettingAsUser(str12, bool21, iBinder1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 130:
          iBinder1.enforceInterface("android.content.pm.IPackageManager");
          bool8 = isStorageLow();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 129:
          iBinder1.enforceInterface("android.content.pm.IPackageManager");
          bool8 = isDeviceUpgrading();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 128:
          iBinder1.enforceInterface("android.content.pm.IPackageManager");
          bool8 = isOnlyCoreApps();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 127:
          iBinder1.enforceInterface("android.content.pm.IPackageManager");
          bool8 = isFirstBoot();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 126:
          iBinder1.enforceInterface("android.content.pm.IPackageManager");
          verifierDeviceIdentity = getVerifierDeviceIdentity();
          paramParcel2.writeNoException();
          if (verifierDeviceIdentity != null) {
            paramParcel2.writeInt(1);
            verifierDeviceIdentity.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 125:
          verifierDeviceIdentity.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice5 = getAllIntentFilters(verifierDeviceIdentity.readString());
          paramParcel2.writeNoException();
          if (parceledListSlice5 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice5.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 124:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice5 = getIntentFilterVerifications(parceledListSlice5.readString());
          paramParcel2.writeNoException();
          if (parceledListSlice5 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice5.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 123:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          bool8 = updateIntentVerificationStatus(parceledListSlice5.readString(), parceledListSlice5.readInt(), parceledListSlice5.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 122:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          i2 = getIntentVerificationStatus(parceledListSlice5.readString(), parceledListSlice5.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i2);
          return true;
        case 121:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          verifyIntentFilter(parceledListSlice5.readInt(), parceledListSlice5.readInt(), parceledListSlice5.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 120:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          extendVerificationTimeout(parceledListSlice5.readInt(), parceledListSlice5.readInt(), parceledListSlice5.readLong());
          paramParcel2.writeNoException();
          return true;
        case 119:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          verifyPendingInstall(parceledListSlice5.readInt(), parceledListSlice5.readInt());
          paramParcel2.writeNoException();
          return true;
        case 118:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          i2 = installExistingPackageAsUser(parceledListSlice5.readString(), parceledListSlice5.readInt(), parceledListSlice5.readInt(), parceledListSlice5.readInt(), parceledListSlice5.createStringArrayList());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i2);
          return true;
        case 117:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          i2 = getInstallLocation();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i2);
          return true;
        case 116:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          bool7 = setInstallLocation(parceledListSlice5.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
          return true;
        case 115:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          i1 = movePrimaryStorage(parceledListSlice5.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          return true;
        case 114:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          i1 = movePackage(parceledListSlice5.readString(), parceledListSlice5.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          return true;
        case 113:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          unregisterMoveCallback(IPackageMoveObserver.Stub.asInterface(parceledListSlice5.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 112:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          registerMoveCallback(IPackageMoveObserver.Stub.asInterface(parceledListSlice5.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 111:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          i1 = getMoveStatus(parceledListSlice5.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          return true;
        case 110:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          reconcileSecondaryDexFiles(parceledListSlice5.readString());
          paramParcel2.writeNoException();
          return true;
        case 109:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          bool6 = runBackgroundDexoptJob(parceledListSlice5.createStringArrayList());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool6);
          return true;
        case 108:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          forceDexOpt(parceledListSlice5.readString());
          paramParcel2.writeNoException();
          return true;
        case 107:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          dumpProfiles(parceledListSlice5.readString());
          paramParcel2.writeNoException();
          return true;
        case 106:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          bool6 = compileLayouts(parceledListSlice5.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool6);
          return true;
        case 105:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          str8 = parceledListSlice5.readString();
          str12 = parceledListSlice5.readString();
          bool21 = bool17;
          if (parceledListSlice5.readInt() != 0)
            bool21 = true; 
          bool6 = performDexOptSecondary(str8, str12, bool21);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool6);
          return true;
        case 104:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          str12 = parceledListSlice5.readString();
          if (parceledListSlice5.readInt() != 0) {
            bool21 = true;
          } else {
            bool21 = false;
          } 
          str8 = parceledListSlice5.readString();
          if (parceledListSlice5.readInt() != 0) {
            bool12 = true;
          } else {
            bool12 = false;
          } 
          if (parceledListSlice5.readInt() != 0) {
            bool13 = true;
          } else {
            bool13 = false;
          } 
          bool6 = performDexOptMode(str12, bool21, str8, bool12, bool13, parceledListSlice5.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool6);
          return true;
        case 103:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          str7 = parceledListSlice5.readString();
          str12 = parceledListSlice5.readString();
          bool21 = bool18;
          if (parceledListSlice5.readInt() != 0)
            bool21 = true; 
          registerDexModule(str7, str12, bool21, IDexModuleRegisterCallback.Stub.asInterface(parceledListSlice5.readStrongBinder()));
          return true;
        case 102:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          str12 = parceledListSlice5.readString();
          n = parceledListSlice5.readInt();
          if (n < 0) {
            str7 = str11;
          } else {
            hashMap = new HashMap<>();
          } 
          IntStream.range(0, n).forEach(new _$$Lambda$IPackageManager$Stub$ZVp6oEh_Gn_bn8lM7TgSgpaGriw((Parcel)parceledListSlice5, hashMap));
          notifyDexLoad(str12, (Map)hashMap, parceledListSlice5.readString());
          return true;
        case 101:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          notifyPackageUse(parceledListSlice5.readString(), parceledListSlice5.readInt());
          return true;
        case 100:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          updatePackagesIfNeeded();
          hashMap.writeNoException();
          return true;
        case 99:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          performFstrimIfNeeded();
          hashMap.writeNoException();
          return true;
        case 98:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          bool5 = hasSystemUidErrors();
          hashMap.writeNoException();
          hashMap.writeInt(bool5);
          return true;
        case 97:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          systemReady();
          hashMap.writeNoException();
          return true;
        case 96:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          bool5 = isSafeMode();
          hashMap.writeNoException();
          hashMap.writeInt(bool5);
          return true;
        case 95:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          enterSafeMode();
          hashMap.writeNoException();
          return true;
        case 94:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          bool5 = hasSystemFeature(parceledListSlice5.readString(), parceledListSlice5.readInt());
          hashMap.writeNoException();
          hashMap.writeInt(bool5);
          return true;
        case 93:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice5 = getSystemAvailableFeatures();
          hashMap.writeNoException();
          if (parceledListSlice5 != null) {
            hashMap.writeInt(1);
            parceledListSlice5.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 92:
          parceledListSlice5.enforceInterface("android.content.pm.IPackageManager");
          arrayOfString5 = getSystemSharedLibraryNames();
          hashMap.writeNoException();
          hashMap.writeStringArray(arrayOfString5);
          return true;
        case 91:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          getPackageSizeInfo(arrayOfString5.readString(), arrayOfString5.readInt(), IPackageStatsObserver.Stub.asInterface(arrayOfString5.readStrongBinder()));
          hashMap.writeNoException();
          return true;
        case 90:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          clearApplicationProfileData(arrayOfString5.readString());
          hashMap.writeNoException();
          return true;
        case 89:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          clearApplicationUserData(arrayOfString5.readString(), IPackageDataObserver.Stub.asInterface(arrayOfString5.readStrongBinder()), arrayOfString5.readInt());
          hashMap.writeNoException();
          return true;
        case 88:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          deleteApplicationCacheFilesAsUser(arrayOfString5.readString(), arrayOfString5.readInt(), IPackageDataObserver.Stub.asInterface(arrayOfString5.readStrongBinder()));
          hashMap.writeNoException();
          return true;
        case 87:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          deleteApplicationCacheFiles(arrayOfString5.readString(), IPackageDataObserver.Stub.asInterface(arrayOfString5.readStrongBinder()));
          hashMap.writeNoException();
          return true;
        case 86:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          str12 = arrayOfString5.readString();
          l = arrayOfString5.readLong();
          m = arrayOfString5.readInt();
          if (arrayOfString5.readInt() != 0) {
            IntentSender intentSender = (IntentSender)IntentSender.CREATOR.createFromParcel((Parcel)arrayOfString5);
          } else {
            arrayOfString5 = null;
          } 
          freeStorage(str12, l, m, (IntentSender)arrayOfString5);
          hashMap.writeNoException();
          return true;
        case 85:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          freeStorageAndNotify(arrayOfString5.readString(), arrayOfString5.readLong(), arrayOfString5.readInt(), IPackageDataObserver.Stub.asInterface(arrayOfString5.readStrongBinder()));
          hashMap.writeNoException();
          return true;
        case 84:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          str12 = arrayOfString5.readString();
          bool21 = bool19;
          if (arrayOfString5.readInt() != 0)
            bool21 = true; 
          setPackageStoppedState(str12, bool21, arrayOfString5.readInt());
          hashMap.writeNoException();
          return true;
        case 83:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          flushPackageRestrictionsAsUser(arrayOfString5.readInt());
          hashMap.writeNoException();
          return true;
        case 82:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          logAppProcessStartIfNeeded(arrayOfString5.readString(), arrayOfString5.readInt(), arrayOfString5.readString(), arrayOfString5.readString(), arrayOfString5.readInt());
          hashMap.writeNoException();
          return true;
        case 81:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          m = getApplicationEnabledSetting(arrayOfString5.readString(), arrayOfString5.readInt());
          hashMap.writeNoException();
          hashMap.writeInt(m);
          return true;
        case 80:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          setApplicationEnabledSetting(arrayOfString5.readString(), arrayOfString5.readInt(), arrayOfString5.readInt(), arrayOfString5.readInt(), arrayOfString5.readString());
          hashMap.writeNoException();
          return true;
        case 79:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfString5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString5);
          } else {
            str12 = null;
          } 
          m = getComponentEnabledSetting((ComponentName)str12, arrayOfString5.readInt());
          hashMap.writeNoException();
          hashMap.writeInt(m);
          return true;
        case 78:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfString5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString5);
          } else {
            str12 = null;
          } 
          setComponentEnabledSetting((ComponentName)str12, arrayOfString5.readInt(), arrayOfString5.readInt(), arrayOfString5.readInt());
          hashMap.writeNoException();
          return true;
        case 77:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfString5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString5);
          } else {
            str12 = null;
          } 
          restoreLabelAndIcon((ComponentName)str12, arrayOfString5.readInt());
          hashMap.writeNoException();
          return true;
        case 76:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfString5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString5);
          } else {
            str12 = null;
          } 
          overrideLabelAndIcon((ComponentName)str12, arrayOfString5.readString(), arrayOfString5.readInt(), arrayOfString5.readInt());
          hashMap.writeNoException();
          return true;
        case 75:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfString5.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString5);
          } else {
            str12 = null;
          } 
          setHomeActivity((ComponentName)str12, arrayOfString5.readInt());
          hashMap.writeNoException();
          return true;
        case 74:
          arrayOfString5.enforceInterface("android.content.pm.IPackageManager");
          arrayList1 = new ArrayList();
          componentName2 = getHomeActivities(arrayList1);
          hashMap.writeNoException();
          if (componentName2 != null) {
            hashMap.writeInt(1);
            componentName2.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          hashMap.writeTypedList(arrayList1);
          return true;
        case 73:
          arrayList1.enforceInterface("android.content.pm.IPackageManager");
          restoreIntentFilterVerification(arrayList1.createByteArray(), arrayList1.readInt());
          hashMap.writeNoException();
          return true;
        case 72:
          arrayList1.enforceInterface("android.content.pm.IPackageManager");
          arrayOfByte1 = getIntentFilterVerificationBackup(arrayList1.readInt());
          hashMap.writeNoException();
          hashMap.writeByteArray(arrayOfByte1);
          return true;
        case 71:
          arrayOfByte1.enforceInterface("android.content.pm.IPackageManager");
          restoreDefaultApps(arrayOfByte1.createByteArray(), arrayOfByte1.readInt());
          hashMap.writeNoException();
          return true;
        case 70:
          arrayOfByte1.enforceInterface("android.content.pm.IPackageManager");
          arrayOfByte1 = getDefaultAppsBackup(arrayOfByte1.readInt());
          hashMap.writeNoException();
          hashMap.writeByteArray(arrayOfByte1);
          return true;
        case 69:
          arrayOfByte1.enforceInterface("android.content.pm.IPackageManager");
          restorePreferredActivities(arrayOfByte1.createByteArray(), arrayOfByte1.readInt());
          hashMap.writeNoException();
          return true;
        case 68:
          arrayOfByte1.enforceInterface("android.content.pm.IPackageManager");
          arrayOfByte1 = getPreferredActivityBackup(arrayOfByte1.readInt());
          hashMap.writeNoException();
          hashMap.writeByteArray(arrayOfByte1);
          return true;
        case 67:
          arrayOfByte1.enforceInterface("android.content.pm.IPackageManager");
          bundle = getSuspendedPackageAppExtras(arrayOfByte1.readString(), arrayOfByte1.readInt());
          hashMap.writeNoException();
          if (bundle != null) {
            hashMap.writeInt(1);
            bundle.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 66:
          bundle.enforceInterface("android.content.pm.IPackageManager");
          bool4 = isPackageSuspendedForUser(bundle.readString(), bundle.readInt());
          hashMap.writeNoException();
          hashMap.writeInt(bool4);
          return true;
        case 65:
          bundle.enforceInterface("android.content.pm.IPackageManager");
          arrayOfString4 = getUnsuspendablePackagesForUser(bundle.createStringArray(), bundle.readInt());
          hashMap.writeNoException();
          hashMap.writeStringArray(arrayOfString4);
          return true;
        case 64:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          arrayOfString7 = arrayOfString4.createStringArray();
          if (arrayOfString4.readInt() != 0) {
            bool21 = true;
          } else {
            bool21 = false;
          } 
          if (arrayOfString4.readInt() != 0) {
            PersistableBundle persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            componentName2 = null;
          } 
          if (arrayOfString4.readInt() != 0) {
            PersistableBundle persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            str8 = null;
          } 
          if (arrayOfString4.readInt() != 0) {
            SuspendDialogInfo suspendDialogInfo = (SuspendDialogInfo)SuspendDialogInfo.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            str11 = null;
          } 
          arrayOfString4 = setPackagesSuspendedAsUser(arrayOfString7, bool21, (PersistableBundle)componentName2, (PersistableBundle)str8, (SuspendDialogInfo)str11, arrayOfString4.readString(), arrayOfString4.readInt());
          hashMap.writeNoException();
          hashMap.writeStringArray(arrayOfString4);
          return true;
        case 63:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          arrayOfString4 = setDistractingPackageRestrictionsAsUser(arrayOfString4.createStringArray(), arrayOfString4.readInt(), arrayOfString4.readInt());
          hashMap.writeNoException();
          hashMap.writeStringArray(arrayOfString4);
          return true;
        case 62:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          clearCrossProfileIntentFilters(arrayOfString4.readInt(), arrayOfString4.readString());
          hashMap.writeNoException();
          return true;
        case 61:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfString4.readInt() != 0) {
            IntentFilter intentFilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            componentName2 = null;
          } 
          addCrossProfileIntentFilter((IntentFilter)componentName2, arrayOfString4.readString(), arrayOfString4.readInt(), arrayOfString4.readInt(), arrayOfString4.readInt());
          hashMap.writeNoException();
          return true;
        case 60:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          clearPackagePersistentPreferredActivities(arrayOfString4.readString(), arrayOfString4.readInt());
          hashMap.writeNoException();
          return true;
        case 59:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfString4.readInt() != 0) {
            IntentFilter intentFilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            componentName2 = null;
          } 
          if (arrayOfString4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            str8 = null;
          } 
          addPersistentPreferredActivity((IntentFilter)componentName2, (ComponentName)str8, arrayOfString4.readInt());
          hashMap.writeNoException();
          return true;
        case 58:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          arrayList3 = new ArrayList();
          arrayList2 = new ArrayList();
          k = getPreferredActivities(arrayList3, arrayList2, arrayOfString4.readString());
          hashMap.writeNoException();
          hashMap.writeInt(k);
          hashMap.writeTypedList(arrayList3);
          hashMap.writeTypedList(arrayList2);
          return true;
        case 57:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          clearPackagePreferredActivities(arrayOfString4.readString());
          hashMap.writeNoException();
          return true;
        case 56:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfString4.readInt() != 0) {
            IntentFilter intentFilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            arrayList3 = null;
          } 
          k = arrayOfString4.readInt();
          arrayOfComponentName = (ComponentName[])arrayOfString4.createTypedArray(ComponentName.CREATOR);
          if (arrayOfString4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            arrayList2 = null;
          } 
          replacePreferredActivity((IntentFilter)arrayList3, k, arrayOfComponentName, (ComponentName)arrayList2, arrayOfString4.readInt());
          hashMap.writeNoException();
          return true;
        case 55:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfString4.readInt() != 0) {
            IntentFilter intentFilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            arrayList3 = null;
          } 
          k = arrayOfString4.readInt();
          arrayOfComponentName = (ComponentName[])arrayOfString4.createTypedArray(ComponentName.CREATOR);
          if (arrayOfString4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            arrayList2 = null;
          } 
          addPreferredActivity((IntentFilter)arrayList3, k, arrayOfComponentName, (ComponentName)arrayList2, arrayOfString4.readInt());
          hashMap.writeNoException();
          return true;
        case 54:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfString4.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            arrayList3 = null;
          } 
          str10 = arrayOfString4.readString();
          paramInt2 = arrayOfString4.readInt();
          if (arrayOfString4.readInt() != 0) {
            IntentFilter intentFilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            arrayList2 = null;
          } 
          k = arrayOfString4.readInt();
          if (arrayOfString4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            arrayOfString4 = null;
          } 
          setLastChosenActivity((Intent)arrayList3, str10, paramInt2, (IntentFilter)arrayList2, k, (ComponentName)arrayOfString4);
          hashMap.writeNoException();
          return true;
        case 53:
          arrayOfString4.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfString4.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)arrayOfString4);
          } else {
            arrayList3 = null;
          } 
          resolveInfo3 = getLastChosenActivity((Intent)arrayList3, arrayOfString4.readString(), arrayOfString4.readInt());
          hashMap.writeNoException();
          if (resolveInfo3 != null) {
            hashMap.writeInt(1);
            resolveInfo3.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 52:
          resolveInfo3.enforceInterface("android.content.pm.IPackageManager");
          resetApplicationPreferences(resolveInfo3.readInt());
          hashMap.writeNoException();
          return true;
        case 51:
          resolveInfo3.enforceInterface("android.content.pm.IPackageManager");
          installSourceInfo = getInstallSourceInfo(resolveInfo3.readString());
          hashMap.writeNoException();
          if (installSourceInfo != null) {
            hashMap.writeInt(1);
            installSourceInfo.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 50:
          installSourceInfo.enforceInterface("android.content.pm.IPackageManager");
          str2 = getInstallerPackageName(installSourceInfo.readString());
          hashMap.writeNoException();
          hashMap.writeString(str2);
          return true;
        case 49:
          str2.enforceInterface("android.content.pm.IPackageManager");
          if (str2.readInt() != 0) {
            VersionedPackage versionedPackage = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel((Parcel)str2);
          } else {
            arrayList3 = null;
          } 
          deleteExistingPackageAsUser((VersionedPackage)arrayList3, IPackageDeleteObserver2.Stub.asInterface(str2.readStrongBinder()), str2.readInt());
          hashMap.writeNoException();
          return true;
        case 48:
          str2.enforceInterface("android.content.pm.IPackageManager");
          if (str2.readInt() != 0) {
            VersionedPackage versionedPackage = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel((Parcel)str2);
          } else {
            arrayList3 = null;
          } 
          deletePackageVersioned((VersionedPackage)arrayList3, IPackageDeleteObserver2.Stub.asInterface(str2.readStrongBinder()), str2.readInt(), str2.readInt());
          hashMap.writeNoException();
          return true;
        case 47:
          str2.enforceInterface("android.content.pm.IPackageManager");
          deletePackageAsUser(str2.readString(), str2.readInt(), IPackageDeleteObserver.Stub.asInterface(str2.readStrongBinder()), str2.readInt(), str2.readInt());
          hashMap.writeNoException();
          return true;
        case 46:
          str2.enforceInterface("android.content.pm.IPackageManager");
          setApplicationCategoryHint(str2.readString(), str2.readInt(), str2.readString());
          hashMap.writeNoException();
          return true;
        case 45:
          str2.enforceInterface("android.content.pm.IPackageManager");
          setInstallerPackageName(str2.readString(), str2.readString());
          hashMap.writeNoException();
          return true;
        case 44:
          str2.enforceInterface("android.content.pm.IPackageManager");
          k = str2.readInt();
          bool21 = bool20;
          if (str2.readInt() != 0)
            bool21 = true; 
          finishPackageInstall(k, bool21);
          hashMap.writeNoException();
          return true;
        case 43:
          str2.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice4 = queryInstrumentation(str2.readString(), str2.readInt());
          hashMap.writeNoException();
          if (parceledListSlice4 != null) {
            hashMap.writeInt(1);
            parceledListSlice4.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 42:
          parceledListSlice4.enforceInterface("android.content.pm.IPackageManager");
          if (parceledListSlice4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parceledListSlice4);
          } else {
            arrayList3 = null;
          } 
          instrumentationInfo = getInstrumentationInfo((ComponentName)arrayList3, parceledListSlice4.readInt());
          hashMap.writeNoException();
          if (instrumentationInfo != null) {
            hashMap.writeInt(1);
            instrumentationInfo.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 41:
          instrumentationInfo.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice3 = queryContentProviders(instrumentationInfo.readString(), instrumentationInfo.readInt(), instrumentationInfo.readInt(), instrumentationInfo.readString());
          hashMap.writeNoException();
          if (parceledListSlice3 != null) {
            hashMap.writeInt(1);
            parceledListSlice3.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 40:
          parceledListSlice3.enforceInterface("android.content.pm.IPackageManager");
          arrayList3 = parceledListSlice3.createStringArrayList();
          arrayList = parceledListSlice3.createTypedArrayList(ProviderInfo.CREATOR);
          querySyncProviders((List)arrayList3, arrayList);
          hashMap.writeNoException();
          hashMap.writeStringList(arrayList3);
          hashMap.writeTypedList(arrayList);
          return true;
        case 39:
          arrayList.enforceInterface("android.content.pm.IPackageManager");
          providerInfo2 = resolveContentProvider(arrayList.readString(), arrayList.readInt(), arrayList.readInt());
          hashMap.writeNoException();
          if (providerInfo2 != null) {
            hashMap.writeInt(1);
            providerInfo2.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 38:
          providerInfo2.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice2 = getPersistentApplications(providerInfo2.readInt());
          hashMap.writeNoException();
          if (parceledListSlice2 != null) {
            hashMap.writeInt(1);
            parceledListSlice2.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 37:
          parceledListSlice2.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice2 = getInstalledApplications(parceledListSlice2.readInt(), parceledListSlice2.readInt());
          hashMap.writeNoException();
          if (parceledListSlice2 != null) {
            hashMap.writeInt(1);
            parceledListSlice2.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 36:
          parceledListSlice2.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice2 = getPackagesHoldingPermissions(parceledListSlice2.createStringArray(), parceledListSlice2.readInt(), parceledListSlice2.readInt());
          hashMap.writeNoException();
          if (parceledListSlice2 != null) {
            hashMap.writeInt(1);
            parceledListSlice2.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 35:
          parceledListSlice2.enforceInterface("android.content.pm.IPackageManager");
          parceledListSlice2 = getInstalledPackages(parceledListSlice2.readInt(), parceledListSlice2.readInt());
          hashMap.writeNoException();
          if (parceledListSlice2 != null) {
            hashMap.writeInt(1);
            parceledListSlice2.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 34:
          parceledListSlice2.enforceInterface("android.content.pm.IPackageManager");
          if (parceledListSlice2.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)parceledListSlice2);
          } else {
            arrayList3 = null;
          } 
          parceledListSlice2 = queryIntentContentProviders((Intent)arrayList3, parceledListSlice2.readString(), parceledListSlice2.readInt(), parceledListSlice2.readInt());
          hashMap.writeNoException();
          if (parceledListSlice2 != null) {
            hashMap.writeInt(1);
            parceledListSlice2.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 33:
          parceledListSlice2.enforceInterface("android.content.pm.IPackageManager");
          if (parceledListSlice2.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)parceledListSlice2);
          } else {
            arrayList3 = null;
          } 
          parceledListSlice2 = queryIntentServices((Intent)arrayList3, parceledListSlice2.readString(), parceledListSlice2.readInt(), parceledListSlice2.readInt());
          hashMap.writeNoException();
          if (parceledListSlice2 != null) {
            hashMap.writeInt(1);
            parceledListSlice2.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 32:
          parceledListSlice2.enforceInterface("android.content.pm.IPackageManager");
          if (parceledListSlice2.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)parceledListSlice2);
          } else {
            arrayList3 = null;
          } 
          resolveInfo2 = resolveService((Intent)arrayList3, parceledListSlice2.readString(), parceledListSlice2.readInt(), parceledListSlice2.readInt());
          hashMap.writeNoException();
          if (resolveInfo2 != null) {
            hashMap.writeInt(1);
            resolveInfo2.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 31:
          resolveInfo2.enforceInterface("android.content.pm.IPackageManager");
          if (resolveInfo2.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)resolveInfo2);
          } else {
            arrayList3 = null;
          } 
          parceledListSlice1 = queryIntentReceivers((Intent)arrayList3, resolveInfo2.readString(), resolveInfo2.readInt(), resolveInfo2.readInt());
          hashMap.writeNoException();
          if (parceledListSlice1 != null) {
            hashMap.writeInt(1);
            parceledListSlice1.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 30:
          parceledListSlice1.enforceInterface("android.content.pm.IPackageManager");
          if (parceledListSlice1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parceledListSlice1);
          } else {
            arrayList3 = null;
          } 
          arrayOfIntent = (Intent[])parceledListSlice1.createTypedArray(Intent.CREATOR);
          arrayOfString7 = parceledListSlice1.createStringArray();
          if (parceledListSlice1.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)parceledListSlice1);
          } else {
            arrayList2 = null;
          } 
          parceledListSlice1 = queryIntentActivityOptions((ComponentName)arrayList3, arrayOfIntent, arrayOfString7, (Intent)arrayList2, parceledListSlice1.readString(), parceledListSlice1.readInt(), parceledListSlice1.readInt());
          hashMap.writeNoException();
          if (parceledListSlice1 != null) {
            hashMap.writeInt(1);
            parceledListSlice1.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 29:
          parceledListSlice1.enforceInterface("android.content.pm.IPackageManager");
          if (parceledListSlice1.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)parceledListSlice1);
          } else {
            arrayList3 = null;
          } 
          parceledListSlice1 = queryIntentActivities((Intent)arrayList3, parceledListSlice1.readString(), parceledListSlice1.readInt(), parceledListSlice1.readInt());
          hashMap.writeNoException();
          if (parceledListSlice1 != null) {
            hashMap.writeInt(1);
            parceledListSlice1.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 28:
          parceledListSlice1.enforceInterface("android.content.pm.IPackageManager");
          if (parceledListSlice1.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)parceledListSlice1);
          } else {
            arrayList3 = null;
          } 
          bool3 = canForwardTo((Intent)arrayList3, parceledListSlice1.readString(), parceledListSlice1.readInt(), parceledListSlice1.readInt());
          hashMap.writeNoException();
          hashMap.writeInt(bool3);
          return true;
        case 27:
          parceledListSlice1.enforceInterface("android.content.pm.IPackageManager");
          if (parceledListSlice1.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)parceledListSlice1);
          } else {
            arrayList3 = null;
          } 
          resolveInfo1 = findPersistentPreferredActivity((Intent)arrayList3, parceledListSlice1.readInt());
          hashMap.writeNoException();
          if (resolveInfo1 != null) {
            hashMap.writeInt(1);
            resolveInfo1.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 26:
          resolveInfo1.enforceInterface("android.content.pm.IPackageManager");
          if (resolveInfo1.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)resolveInfo1);
          } else {
            arrayList3 = null;
          } 
          resolveInfo1 = resolveIntent((Intent)arrayList3, resolveInfo1.readString(), resolveInfo1.readInt(), resolveInfo1.readInt());
          hashMap.writeNoException();
          if (resolveInfo1 != null) {
            hashMap.writeInt(1);
            resolveInfo1.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 25:
          resolveInfo1.enforceInterface("android.content.pm.IPackageManager");
          bool3 = isUidPrivileged(resolveInfo1.readInt());
          hashMap.writeNoException();
          hashMap.writeInt(bool3);
          return true;
        case 24:
          resolveInfo1.enforceInterface("android.content.pm.IPackageManager");
          j = getPrivateFlagsForUid(resolveInfo1.readInt());
          hashMap.writeNoException();
          hashMap.writeInt(j);
          return true;
        case 23:
          resolveInfo1.enforceInterface("android.content.pm.IPackageManager");
          j = getFlagsForUid(resolveInfo1.readInt());
          hashMap.writeNoException();
          hashMap.writeInt(j);
          return true;
        case 22:
          resolveInfo1.enforceInterface("android.content.pm.IPackageManager");
          j = getUidForSharedUser(resolveInfo1.readString());
          hashMap.writeNoException();
          hashMap.writeInt(j);
          return true;
        case 21:
          resolveInfo1.enforceInterface("android.content.pm.IPackageManager");
          arrayOfString3 = getNamesForUids(resolveInfo1.createIntArray());
          hashMap.writeNoException();
          hashMap.writeStringArray(arrayOfString3);
          return true;
        case 20:
          arrayOfString3.enforceInterface("android.content.pm.IPackageManager");
          str1 = getNameForUid(arrayOfString3.readInt());
          hashMap.writeNoException();
          hashMap.writeString(str1);
          return true;
        case 19:
          str1.enforceInterface("android.content.pm.IPackageManager");
          arrayOfString2 = getPackagesForUid(str1.readInt());
          hashMap.writeNoException();
          hashMap.writeStringArray(arrayOfString2);
          return true;
        case 18:
          arrayOfString2.enforceInterface("android.content.pm.IPackageManager");
          list1 = getAllPackages();
          hashMap.writeNoException();
          hashMap.writeStringList(list1);
          return true;
        case 17:
          list1.enforceInterface("android.content.pm.IPackageManager");
          j = checkUidSignatures(list1.readInt(), list1.readInt());
          hashMap.writeNoException();
          hashMap.writeInt(j);
          return true;
        case 16:
          list1.enforceInterface("android.content.pm.IPackageManager");
          j = checkSignatures(list1.readString(), list1.readString());
          hashMap.writeNoException();
          hashMap.writeInt(j);
          return true;
        case 15:
          list1.enforceInterface("android.content.pm.IPackageManager");
          bool2 = isProtectedBroadcast(list1.readString());
          hashMap.writeNoException();
          hashMap.writeInt(bool2);
          return true;
        case 14:
          list1.enforceInterface("android.content.pm.IPackageManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            arrayList3 = null;
          } 
          providerInfo1 = getProviderInfo((ComponentName)arrayList3, list1.readInt(), list1.readInt());
          hashMap.writeNoException();
          if (providerInfo1 != null) {
            hashMap.writeInt(1);
            providerInfo1.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 13:
          providerInfo1.enforceInterface("android.content.pm.IPackageManager");
          if (providerInfo1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)providerInfo1);
          } else {
            arrayList3 = null;
          } 
          serviceInfo = getServiceInfo((ComponentName)arrayList3, providerInfo1.readInt(), providerInfo1.readInt());
          hashMap.writeNoException();
          if (serviceInfo != null) {
            hashMap.writeInt(1);
            serviceInfo.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 12:
          serviceInfo.enforceInterface("android.content.pm.IPackageManager");
          if (serviceInfo.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)serviceInfo);
          } else {
            arrayList3 = null;
          } 
          activityInfo = getReceiverInfo((ComponentName)arrayList3, serviceInfo.readInt(), serviceInfo.readInt());
          hashMap.writeNoException();
          if (activityInfo != null) {
            hashMap.writeInt(1);
            activityInfo.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 11:
          activityInfo.enforceInterface("android.content.pm.IPackageManager");
          if (activityInfo.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)activityInfo);
          } else {
            arrayList3 = null;
          } 
          if (activityInfo.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)activityInfo);
          } else {
            arrayList2 = null;
          } 
          bool2 = activitySupportsIntent((ComponentName)arrayList3, (Intent)arrayList2, activityInfo.readString());
          hashMap.writeNoException();
          hashMap.writeInt(bool2);
          return true;
        case 10:
          activityInfo.enforceInterface("android.content.pm.IPackageManager");
          if (activityInfo.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)activityInfo);
          } else {
            arrayList3 = null;
          } 
          activityInfo = getActivityInfo((ComponentName)arrayList3, activityInfo.readInt(), activityInfo.readInt());
          hashMap.writeNoException();
          if (activityInfo != null) {
            hashMap.writeInt(1);
            activityInfo.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 9:
          activityInfo.enforceInterface("android.content.pm.IPackageManager");
          applicationInfo = getApplicationInfo(activityInfo.readString(), activityInfo.readInt(), activityInfo.readInt());
          hashMap.writeNoException();
          if (applicationInfo != null) {
            hashMap.writeInt(1);
            applicationInfo.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 8:
          applicationInfo.enforceInterface("android.content.pm.IPackageManager");
          arrayOfString1 = canonicalToCurrentPackageNames(applicationInfo.createStringArray());
          hashMap.writeNoException();
          hashMap.writeStringArray(arrayOfString1);
          return true;
        case 7:
          arrayOfString1.enforceInterface("android.content.pm.IPackageManager");
          arrayOfString1 = currentToCanonicalPackageNames(arrayOfString1.createStringArray());
          hashMap.writeNoException();
          hashMap.writeStringArray(arrayOfString1);
          return true;
        case 6:
          arrayOfString1.enforceInterface("android.content.pm.IPackageManager");
          arrayOfInt = getPackageGids(arrayOfString1.readString(), arrayOfString1.readInt(), arrayOfString1.readInt());
          hashMap.writeNoException();
          hashMap.writeIntArray(arrayOfInt);
          return true;
        case 5:
          arrayOfInt.enforceInterface("android.content.pm.IPackageManager");
          i = getPackageUid(arrayOfInt.readString(), arrayOfInt.readInt(), arrayOfInt.readInt());
          hashMap.writeNoException();
          hashMap.writeInt(i);
          return true;
        case 4:
          arrayOfInt.enforceInterface("android.content.pm.IPackageManager");
          if (arrayOfInt.readInt() != 0) {
            VersionedPackage versionedPackage = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel((Parcel)arrayOfInt);
          } else {
            arrayList3 = null;
          } 
          packageInfo = getPackageInfoVersioned((VersionedPackage)arrayList3, arrayOfInt.readInt(), arrayOfInt.readInt());
          hashMap.writeNoException();
          if (packageInfo != null) {
            hashMap.writeInt(1);
            packageInfo.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 3:
          packageInfo.enforceInterface("android.content.pm.IPackageManager");
          packageInfo = getPackageInfo(packageInfo.readString(), packageInfo.readInt(), packageInfo.readInt());
          hashMap.writeNoException();
          if (packageInfo != null) {
            hashMap.writeInt(1);
            packageInfo.writeToParcel((Parcel)hashMap, 1);
          } else {
            hashMap.writeInt(0);
          } 
          return true;
        case 2:
          packageInfo.enforceInterface("android.content.pm.IPackageManager");
          bool1 = isPackageAvailable(packageInfo.readString(), packageInfo.readInt());
          hashMap.writeNoException();
          hashMap.writeInt(bool1);
          return true;
        case 1:
          break;
      } 
      packageInfo.enforceInterface("android.content.pm.IPackageManager");
      checkPackageStartable(packageInfo.readString(), packageInfo.readInt());
      hashMap.writeNoException();
      return true;
    } 
    hashMap.writeString("android.content.pm.IPackageManager");
    return true;
  }
  
  private static class Proxy implements IPackageManager {
    public static IPackageManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public boolean activitySupportsIntent(ComponentName param2ComponentName, Intent param2Intent, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().activitySupportsIntent(param2ComponentName, param2Intent, param2String);
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
    
    public void addCrossProfileIntentFilter(IntentFilter param2IntentFilter, String param2String, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2IntentFilter != null) {
          parcel1.writeInt(1);
          param2IntentFilter.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (!this.mRemote.transact(61, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().addCrossProfileIntentFilter(param2IntentFilter, param2String, param2Int1, param2Int2, param2Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean addPermission(PermissionInfo param2PermissionInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        boolean bool = true;
        if (param2PermissionInfo != null) {
          parcel1.writeInt(1);
          param2PermissionInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(186, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().addPermission(param2PermissionInfo);
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
    
    public boolean addPermissionAsync(PermissionInfo param2PermissionInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        boolean bool = true;
        if (param2PermissionInfo != null) {
          parcel1.writeInt(1);
          param2PermissionInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(187, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().addPermissionAsync(param2PermissionInfo);
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
    
    public void addPersistentPreferredActivity(IntentFilter param2IntentFilter, ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2IntentFilter != null) {
          parcel1.writeInt(1);
          param2IntentFilter.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().addPersistentPreferredActivity(param2IntentFilter, param2ComponentName, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addPreferredActivity(IntentFilter param2IntentFilter, int param2Int1, ComponentName[] param2ArrayOfComponentName, ComponentName param2ComponentName, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2IntentFilter != null) {
          parcel1.writeInt(1);
          param2IntentFilter.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeTypedArray((Parcelable[])param2ArrayOfComponentName, 0);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().addPreferredActivity(param2IntentFilter, param2Int1, param2ArrayOfComponentName, param2ComponentName, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean canForwardTo(Intent param2Intent, String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        boolean bool = true;
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().canForwardTo(param2Intent, param2String, param2Int1, param2Int2);
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
    
    public boolean canRequestPackageInstalls(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(157, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().canRequestPackageInstalls(param2String, param2Int);
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
    
    public String[] canonicalToCurrentPackageNames(String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2ArrayOfString = IPackageManager.Stub.getDefaultImpl().canonicalToCurrentPackageNames(param2ArrayOfString);
          return param2ArrayOfString;
        } 
        parcel2.readException();
        param2ArrayOfString = parcel2.createStringArray();
        return param2ArrayOfString;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void checkPackageStartable(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().checkPackageStartable(param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int checkPermission(String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(189, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int = IPackageManager.Stub.getDefaultImpl().checkPermission(param2String1, param2String2, param2Int);
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
    
    public int checkSignatures(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().checkSignatures(param2String1, param2String2); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int checkUidPermission(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(191, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int = IPackageManager.Stub.getDefaultImpl().checkUidPermission(param2String, param2Int);
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
    
    public int checkUidSignatures(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int1 = IPackageManager.Stub.getDefaultImpl().checkUidSignatures(param2Int1, param2Int2);
          return param2Int1;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        return param2Int1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearApplicationProfileData(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(90, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().clearApplicationProfileData(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearApplicationUserData(String param2String, IPackageDataObserver param2IPackageDataObserver, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (param2IPackageDataObserver != null) {
          iBinder = param2IPackageDataObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(89, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().clearApplicationUserData(param2String, param2IPackageDataObserver, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearCrossProfileIntentFilters(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(62, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().clearCrossProfileIntentFilters(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearPackagePersistentPreferredActivities(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().clearPackagePersistentPreferredActivities(param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearPackagePreferredActivities(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().clearPackagePreferredActivities(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean compileLayouts(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(106, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().compileLayouts(param2String);
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
    
    public String[] currentToCanonicalPackageNames(String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2ArrayOfString = IPackageManager.Stub.getDefaultImpl().currentToCanonicalPackageNames(param2ArrayOfString);
          return param2ArrayOfString;
        } 
        parcel2.readException();
        param2ArrayOfString = parcel2.createStringArray();
        return param2ArrayOfString;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteApplicationCacheFiles(String param2String, IPackageDataObserver param2IPackageDataObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (param2IPackageDataObserver != null) {
          iBinder = param2IPackageDataObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(87, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().deleteApplicationCacheFiles(param2String, param2IPackageDataObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteApplicationCacheFilesAsUser(String param2String, int param2Int, IPackageDataObserver param2IPackageDataObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2IPackageDataObserver != null) {
          iBinder = param2IPackageDataObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(88, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().deleteApplicationCacheFilesAsUser(param2String, param2Int, param2IPackageDataObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteExistingPackageAsUser(VersionedPackage param2VersionedPackage, IPackageDeleteObserver2 param2IPackageDeleteObserver2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2VersionedPackage != null) {
          parcel1.writeInt(1);
          param2VersionedPackage.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IPackageDeleteObserver2 != null) {
          iBinder = param2IPackageDeleteObserver2.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().deleteExistingPackageAsUser(param2VersionedPackage, param2IPackageDeleteObserver2, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deletePackageAsUser(String param2String, int param2Int1, IPackageDeleteObserver param2IPackageDeleteObserver, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        if (param2IPackageDeleteObserver != null) {
          iBinder = param2IPackageDeleteObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().deletePackageAsUser(param2String, param2Int1, param2IPackageDeleteObserver, param2Int2, param2Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deletePackageVersioned(VersionedPackage param2VersionedPackage, IPackageDeleteObserver2 param2IPackageDeleteObserver2, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2VersionedPackage != null) {
          parcel1.writeInt(1);
          param2VersionedPackage.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IPackageDeleteObserver2 != null) {
          iBinder = param2IPackageDeleteObserver2.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().deletePackageVersioned(param2VersionedPackage, param2IPackageDeleteObserver2, param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deletePreloadsFileCache() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(158, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().deletePreloadsFileCache();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void dumpProfiles(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(107, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().dumpProfiles(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enterSafeMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(95, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().enterSafeMode();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void extendVerificationTimeout(int param2Int1, int param2Int2, long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(120, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().extendVerificationTimeout(param2Int1, param2Int2, param2Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ResolveInfo findPersistentPreferredActivity(Intent param2Intent, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().findPersistentPreferredActivity(param2Intent, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ResolveInfo resolveInfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2Intent = null;
        } 
        return (ResolveInfo)param2Intent;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void finishPackageInstall(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().finishPackageInstall(param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void flushPackageRestrictionsAsUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(83, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().flushPackageRestrictionsAsUser(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void forceDexOpt(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(108, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().forceDexOpt(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void freeStorage(String param2String, long param2Long, int param2Int, IntentSender param2IntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeLong(param2Long);
        parcel1.writeInt(param2Int);
        if (param2IntentSender != null) {
          parcel1.writeInt(1);
          param2IntentSender.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(86, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().freeStorage(param2String, param2Long, param2Int, param2IntentSender);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void freeStorageAndNotify(String param2String, long param2Long, int param2Int, IPackageDataObserver param2IPackageDataObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeLong(param2Long);
        parcel1.writeInt(param2Int);
        if (param2IPackageDataObserver != null) {
          iBinder = param2IPackageDataObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(85, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().freeStorageAndNotify(param2String, param2Long, param2Int, param2IPackageDataObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ActivityInfo getActivityInfo(ComponentName param2ComponentName, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getActivityInfo(param2ComponentName, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ActivityInfo activityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (ActivityInfo)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getAllIntentFilters(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(125, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getAllIntentFilters(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getAllPackages() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getAllPackages(); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] getAppOpPermissionPackages(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(184, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getAppOpPermissionPackages(param2String); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getAppPredictionServicePackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(172, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getAppPredictionServicePackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getApplicationEnabledSetting(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(81, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int = IPackageManager.Stub.getDefaultImpl().getApplicationEnabledSetting(param2String, param2Int);
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
    
    public boolean getApplicationHiddenSettingAsUser(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(132, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().getApplicationHiddenSettingAsUser(param2String, param2Int);
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
    
    public ApplicationInfo getApplicationInfo(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getApplicationInfo(param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ApplicationInfo)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IArtManager getArtManager() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(163, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getArtManager(); 
        parcel2.readException();
        return IArtManager.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getAttentionServicePackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(170, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getAttentionServicePackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getBlockUninstallForUser(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(137, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().getBlockUninstallForUser(param2String, param2Int);
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
    
    public ChangedPackages getChangedPackages(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ChangedPackages changedPackages;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(152, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          changedPackages = IPackageManager.Stub.getDefaultImpl().getChangedPackages(param2Int1, param2Int2);
          return changedPackages;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          changedPackages = (ChangedPackages)ChangedPackages.CREATOR.createFromParcel(parcel2);
        } else {
          changedPackages = null;
        } 
        return changedPackages;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getComponentEnabledSetting(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(79, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int = IPackageManager.Stub.getDefaultImpl().getComponentEnabledSetting(param2ComponentName, param2Int);
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
    
    public String getContentCaptureServicePackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(176, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getContentCaptureServicePackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getDeclaredSharedLibraries(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(156, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getDeclaredSharedLibraries(param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public byte[] getDefaultAppsBackup(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getDefaultAppsBackup(param2Int); 
        parcel2.readException();
        return parcel2.createByteArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getDefaultTextClassifierPackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(168, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getDefaultTextClassifierPackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getFlagsForUid(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int = IPackageManager.Stub.getDefaultImpl().getFlagsForUid(param2Int);
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
    
    public CharSequence getHarmfulAppWarning(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(165, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getHarmfulAppWarning(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getHomeActivities(List<ResolveInfo> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName1;
        ComponentName componentName2;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          componentName1 = IPackageManager.Stub.getDefaultImpl().getHomeActivities(param2List);
          return componentName1;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName2 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName2 = null;
        } 
        parcel2.readTypedList((List)componentName1, ResolveInfo.CREATOR);
        return componentName2;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getIncidentReportApproverPackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(175, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getIncidentReportApproverPackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getInstallLocation() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(117, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getInstallLocation(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getInstallReason(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(154, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int = IPackageManager.Stub.getDefaultImpl().getInstallReason(param2String, param2Int);
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
    
    public InstallSourceInfo getInstallSourceInfo(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getInstallSourceInfo(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          InstallSourceInfo installSourceInfo = (InstallSourceInfo)InstallSourceInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (InstallSourceInfo)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getInstalledApplications(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          parceledListSlice = IPackageManager.Stub.getDefaultImpl().getInstalledApplications(param2Int1, param2Int2);
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ModuleInfo> getInstalledModules(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(179, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getInstalledModules(param2Int); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ModuleInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getInstalledPackages(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          parceledListSlice = IPackageManager.Stub.getDefaultImpl().getInstalledPackages(param2Int1, param2Int2);
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInstallerPackageName(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(50, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2String = IPackageManager.Stub.getDefaultImpl().getInstallerPackageName(param2String);
          return param2String;
        } 
        parcel2.readException();
        param2String = parcel2.readString();
        return param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInstantAppAndroidId(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(162, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2String = IPackageManager.Stub.getDefaultImpl().getInstantAppAndroidId(param2String, param2Int);
          return param2String;
        } 
        parcel2.readException();
        param2String = parcel2.readString();
        return param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public byte[] getInstantAppCookie(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(144, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getInstantAppCookie(param2String, param2Int); 
        parcel2.readException();
        return parcel2.createByteArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bitmap getInstantAppIcon(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(146, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getInstantAppIcon(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (Bitmap)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getInstantAppInstallerComponent() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(161, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          componentName = IPackageManager.Stub.getDefaultImpl().getInstantAppInstallerComponent();
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
    
    public ComponentName getInstantAppResolverComponent() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(159, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          componentName = IPackageManager.Stub.getDefaultImpl().getInstantAppResolverComponent();
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
    
    public ComponentName getInstantAppResolverSettingsComponent() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(160, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          componentName = IPackageManager.Stub.getDefaultImpl().getInstantAppResolverSettingsComponent();
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
    
    public ParceledListSlice getInstantApps(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(143, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          parceledListSlice = IPackageManager.Stub.getDefaultImpl().getInstantApps(param2Int);
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public InstrumentationInfo getInstrumentationInfo(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getInstrumentationInfo(param2ComponentName, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          InstrumentationInfo instrumentationInfo = (InstrumentationInfo)InstrumentationInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (InstrumentationInfo)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public byte[] getIntentFilterVerificationBackup(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(72, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getIntentFilterVerificationBackup(param2Int); 
        parcel2.readException();
        return parcel2.createByteArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getIntentFilterVerifications(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(124, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getIntentFilterVerifications(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getIntentVerificationStatus(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(122, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int = IPackageManager.Stub.getDefaultImpl().getIntentVerificationStatus(param2String, param2Int);
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
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageManager";
    }
    
    public KeySet getKeySetByAlias(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(138, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getKeySetByAlias(param2String1, param2String2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          KeySet keySet = (KeySet)KeySet.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (KeySet)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ResolveInfo getLastChosenActivity(Intent param2Intent, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getLastChosenActivity(param2Intent, param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ResolveInfo resolveInfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2Intent = null;
        } 
        return (ResolveInfo)param2Intent;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getMimeGroup(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(193, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getMimeGroup(param2String1, param2String2); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ModuleInfo getModuleInfo(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(180, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getModuleInfo(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ModuleInfo moduleInfo = (ModuleInfo)ModuleInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ModuleInfo)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getMoveStatus(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(111, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int = IPackageManager.Stub.getDefaultImpl().getMoveStatus(param2Int);
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
    
    public String getNameForUid(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getNameForUid(param2Int); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] getNamesForUids(int[] param2ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getNamesForUids(param2ArrayOfint); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int[] getPackageGids(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getPackageGids(param2String, param2Int1, param2Int2); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public PackageInfo getPackageInfo(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getPackageInfo(param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          PackageInfo packageInfo = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (PackageInfo)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public PackageInfo getPackageInfoVersioned(VersionedPackage param2VersionedPackage, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2VersionedPackage != null) {
          parcel1.writeInt(1);
          param2VersionedPackage.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getPackageInfoVersioned(param2VersionedPackage, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          PackageInfo packageInfo = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2VersionedPackage = null;
        } 
        return (PackageInfo)param2VersionedPackage;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IPackageInstaller getPackageInstaller() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(135, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getPackageInstaller(); 
        parcel2.readException();
        return IPackageInstaller.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void getPackageSizeInfo(String param2String, int param2Int, IPackageStatsObserver param2IPackageStatsObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2IPackageStatsObserver != null) {
          iBinder = param2IPackageStatsObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(91, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().getPackageSizeInfo(param2String, param2Int, param2IPackageStatsObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPackageUid(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int1 = IPackageManager.Stub.getDefaultImpl().getPackageUid(param2String, param2Int1, param2Int2);
          return param2Int1;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        return param2Int1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] getPackagesForUid(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getPackagesForUid(param2Int); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getPackagesHoldingPermissions(String[] param2ArrayOfString, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeStringArray(param2ArrayOfString);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getPackagesHoldingPermissions(param2ArrayOfString, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2ArrayOfString = null;
        } 
        return (ParceledListSlice)param2ArrayOfString;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getPermissionControllerPackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(142, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getPermissionControllerPackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public PermissionGroupInfo getPermissionGroupInfo(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(185, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getPermissionGroupInfo(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          PermissionGroupInfo permissionGroupInfo = (PermissionGroupInfo)PermissionGroupInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (PermissionGroupInfo)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getPersistentApplications(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          parceledListSlice = IPackageManager.Stub.getDefaultImpl().getPersistentApplications(param2Int);
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPreferredActivities(List<IntentFilter> param2List, List<ComponentName> param2List1, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(58, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getPreferredActivities(param2List, param2List1, param2String); 
        parcel2.readException();
        int i = parcel2.readInt();
        parcel2.readTypedList(param2List, IntentFilter.CREATOR);
        parcel2.readTypedList(param2List1, ComponentName.CREATOR);
        return i;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public byte[] getPreferredActivityBackup(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getPreferredActivityBackup(param2Int); 
        parcel2.readException();
        return parcel2.createByteArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPrivateFlagsForUid(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int = IPackageManager.Stub.getDefaultImpl().getPrivateFlagsForUid(param2Int);
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
    
    public ProviderInfo getProviderInfo(ComponentName param2ComponentName, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getProviderInfo(param2ComponentName, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ProviderInfo providerInfo = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (ProviderInfo)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ActivityInfo getReceiverInfo(ComponentName param2ComponentName, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getReceiverInfo(param2ComponentName, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ActivityInfo activityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (ActivityInfo)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getRuntimePermissionsVersion(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(181, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int = IPackageManager.Stub.getDefaultImpl().getRuntimePermissionsVersion(param2Int);
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
    
    public ServiceInfo getServiceInfo(ComponentName param2ComponentName, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getServiceInfo(param2ComponentName, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ServiceInfo serviceInfo = (ServiceInfo)ServiceInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2ComponentName = null;
        } 
        return (ServiceInfo)param2ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getServicesSystemSharedLibraryPackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(150, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getServicesSystemSharedLibraryPackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getSetupWizardPackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(174, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getSetupWizardPackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getSharedLibraries(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(155, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getSharedLibraries(param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getSharedSystemSharedLibraryPackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(151, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getSharedSystemSharedLibraryPackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public KeySet getSigningKeySet(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(139, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getSigningKeySet(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          KeySet keySet = (KeySet)KeySet.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (KeySet)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bundle getSuspendedPackageAppExtras(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getSuspendedPackageAppExtras(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (Bundle)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getSystemAvailableFeatures() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(93, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          parceledListSlice = IPackageManager.Stub.getDefaultImpl().getSystemAvailableFeatures();
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getSystemCaptionsServicePackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(173, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getSystemCaptionsServicePackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] getSystemSharedLibraryNames() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(92, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getSystemSharedLibraryNames(); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getSystemTextClassifierPackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(169, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getSystemTextClassifierPackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getUidForSharedUser(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getUidForSharedUser(param2String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] getUnsuspendablePackagesForUser(String[] param2ArrayOfString, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeStringArray(param2ArrayOfString);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2ArrayOfString = IPackageManager.Stub.getDefaultImpl().getUnsuspendablePackagesForUser(param2ArrayOfString, param2Int);
          return param2ArrayOfString;
        } 
        parcel2.readException();
        param2ArrayOfString = parcel2.createStringArray();
        return param2ArrayOfString;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public VerifierDeviceIdentity getVerifierDeviceIdentity() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        VerifierDeviceIdentity verifierDeviceIdentity;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(126, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          verifierDeviceIdentity = IPackageManager.Stub.getDefaultImpl().getVerifierDeviceIdentity();
          return verifierDeviceIdentity;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          verifierDeviceIdentity = (VerifierDeviceIdentity)VerifierDeviceIdentity.CREATOR.createFromParcel(parcel2);
        } else {
          verifierDeviceIdentity = null;
        } 
        return verifierDeviceIdentity;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getWellbeingPackageName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(171, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().getWellbeingPackageName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void grantImplicitAccess(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(195, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().grantImplicitAccess(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void grantRuntimePermission(String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(190, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().grantRuntimePermission(param2String1, param2String2, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasSigningCertificate(String param2String, byte[] param2ArrayOfbyte, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeByteArray(param2ArrayOfbyte);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(166, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().hasSigningCertificate(param2String, param2ArrayOfbyte, param2Int);
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
    
    public boolean hasSystemFeature(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(94, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().hasSystemFeature(param2String, param2Int);
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
    
    public boolean hasSystemUidErrors() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(98, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().hasSystemUidErrors();
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
    
    public boolean hasUidSigningCertificate(int param2Int1, byte[] param2ArrayOfbyte, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeByteArray(param2ArrayOfbyte);
        parcel1.writeInt(param2Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(167, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().hasUidSigningCertificate(param2Int1, param2ArrayOfbyte, param2Int2);
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
    
    public int installExistingPackageAsUser(String param2String, int param2Int1, int param2Int2, int param2Int3, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(118, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Int1 = IPackageManager.Stub.getDefaultImpl().installExistingPackageAsUser(param2String, param2Int1, param2Int2, param2Int3, param2List);
          return param2Int1;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        return param2Int1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAutoRevokeWhitelisted(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(194, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isAutoRevokeWhitelisted(param2String);
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
    
    public boolean isDeviceUpgrading() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(129, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isDeviceUpgrading();
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
    
    public boolean isFirstBoot() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(127, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isFirstBoot();
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
    
    public boolean isInstantApp(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(147, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isInstantApp(param2String, param2Int);
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
    
    public boolean isOnlyCoreApps() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(128, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isOnlyCoreApps();
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
    
    public boolean isPackageAvailable(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isPackageAvailable(param2String, param2Int);
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
    
    public boolean isPackageDeviceAdminOnAnyUser(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(153, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isPackageDeviceAdminOnAnyUser(param2String);
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
    
    public boolean isPackageSignedByKeySet(String param2String, KeySet param2KeySet) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2KeySet != null) {
          parcel1.writeInt(1);
          param2KeySet.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(140, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isPackageSignedByKeySet(param2String, param2KeySet);
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
    
    public boolean isPackageSignedByKeySetExactly(String param2String, KeySet param2KeySet) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2KeySet != null) {
          parcel1.writeInt(1);
          param2KeySet.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(141, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isPackageSignedByKeySetExactly(param2String, param2KeySet);
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
    
    public boolean isPackageStateProtected(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(177, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isPackageStateProtected(param2String, param2Int);
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
    
    public boolean isPackageSuspendedForUser(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(66, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isPackageSuspendedForUser(param2String, param2Int);
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
    
    public boolean isProtectedBroadcast(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(15, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isProtectedBroadcast(param2String);
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
    
    public boolean isSafeMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(96, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isSafeMode();
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
    
    public boolean isStorageLow() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(130, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isStorageLow();
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
    
    public boolean isUidPrivileged(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(25, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().isUidPrivileged(param2Int);
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
    
    public void logAppProcessStartIfNeeded(String param2String1, int param2Int1, String param2String2, String param2String3, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String2);
        parcel1.writeString(param2String3);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(82, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().logAppProcessStartIfNeeded(param2String1, param2Int1, param2String2, param2String3, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int movePackage(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(114, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().movePackage(param2String1, param2String2); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int movePrimaryStorage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(115, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().movePrimaryStorage(param2String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyDexLoad(String param2String1, Map<String, String> param2Map, String param2String2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel.writeString(param2String1);
        if (param2Map == null) {
          parcel.writeInt(-1);
        } else {
          parcel.writeInt(param2Map.size());
          _$$Lambda$IPackageManager$Stub$Proxy$X2I1qlX4SiKMZSjDTNzS_nTibbo _$$Lambda$IPackageManager$Stub$Proxy$X2I1qlX4SiKMZSjDTNzS_nTibbo = new _$$Lambda$IPackageManager$Stub$Proxy$X2I1qlX4SiKMZSjDTNzS_nTibbo();
          this(parcel);
          param2Map.forEach(_$$Lambda$IPackageManager$Stub$Proxy$X2I1qlX4SiKMZSjDTNzS_nTibbo);
        } 
        parcel.writeString(param2String2);
        if (!this.mRemote.transact(102, parcel, null, 1) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().notifyDexLoad(param2String1, param2Map, param2String2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void notifyPackageUse(String param2String, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(101, parcel, null, 1) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().notifyPackageUse(param2String, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void notifyPackagesReplacedReceived(String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(183, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().notifyPackagesReplacedReceived(param2ArrayOfString);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void overrideLabelAndIcon(ComponentName param2ComponentName, String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().overrideLabelAndIcon(param2ComponentName, param2String, param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean performDexOptMode(String param2String1, boolean param2Boolean1, String param2String2, boolean param2Boolean2, boolean param2Boolean3, String param2String3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        try {
          int i;
          parcel1.writeString(param2String1);
          boolean bool = true;
          if (param2Boolean1) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          try {
            parcel1.writeString(param2String2);
            if (param2Boolean2) {
              i = 1;
            } else {
              i = 0;
            } 
            parcel1.writeInt(i);
            if (param2Boolean3) {
              i = 1;
            } else {
              i = 0;
            } 
            parcel1.writeInt(i);
            try {
              parcel1.writeString(param2String3);
              try {
                if (!this.mRemote.transact(104, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
                  param2Boolean1 = IPackageManager.Stub.getDefaultImpl().performDexOptMode(param2String1, param2Boolean1, param2String2, param2Boolean2, param2Boolean3, param2String3);
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
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String1;
    }
    
    public boolean performDexOptSecondary(String param2String1, String param2String2, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        boolean bool = true;
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(105, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IPackageManager.Stub.getDefaultImpl().performDexOptSecondary(param2String1, param2String2, param2Boolean);
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
    
    public void performFstrimIfNeeded() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(99, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().performFstrimIfNeeded();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice queryContentProviders(String param2String1, int param2Int1, int param2Int2, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().queryContentProviders(param2String1, param2Int1, param2Int2, param2String2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (ParceledListSlice)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice queryInstrumentation(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().queryInstrumentation(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice queryIntentActivities(Intent param2Intent, String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().queryIntentActivities(param2Intent, param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2Intent = null;
        } 
        return (ParceledListSlice)param2Intent;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice queryIntentActivityOptions(ComponentName param2ComponentName, Intent[] param2ArrayOfIntent, String[] param2ArrayOfString, Intent param2Intent, String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeTypedArray((Parcelable[])param2ArrayOfIntent, 0);
          try {
            parcel1.writeStringArray(param2ArrayOfString);
            if (param2Intent != null) {
              parcel1.writeInt(1);
              param2Intent.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeString(param2String);
              parcel1.writeInt(param2Int1);
              parcel1.writeInt(param2Int2);
              if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
                ParceledListSlice parceledListSlice = IPackageManager.Stub.getDefaultImpl().queryIntentActivityOptions(param2ComponentName, param2ArrayOfIntent, param2ArrayOfString, param2Intent, param2String, param2Int1, param2Int2);
                parcel2.recycle();
                parcel1.recycle();
                return parceledListSlice;
              } 
              parcel2.readException();
              if (parcel2.readInt() != 0) {
                ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
              } else {
                param2ComponentName = null;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return (ParceledListSlice)param2ComponentName;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2ComponentName;
    }
    
    public ParceledListSlice queryIntentContentProviders(Intent param2Intent, String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().queryIntentContentProviders(param2Intent, param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2Intent = null;
        } 
        return (ParceledListSlice)param2Intent;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice queryIntentReceivers(Intent param2Intent, String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().queryIntentReceivers(param2Intent, param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2Intent = null;
        } 
        return (ParceledListSlice)param2Intent;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice queryIntentServices(Intent param2Intent, String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().queryIntentServices(param2Intent, param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2Intent = null;
        } 
        return (ParceledListSlice)param2Intent;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void querySyncProviders(List<String> param2List, List<ProviderInfo> param2List1) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeStringList(param2List);
        parcel1.writeTypedList(param2List1);
        if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().querySyncProviders(param2List, param2List1);
          return;
        } 
        parcel2.readException();
        parcel2.readStringList(param2List);
        parcel2.readTypedList(param2List1, ProviderInfo.CREATOR);
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reconcileSecondaryDexFiles(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(110, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().reconcileSecondaryDexFiles(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerDexModule(String param2String1, String param2String2, boolean param2Boolean, IDexModuleRegisterCallback param2IDexModuleRegisterCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel.writeString(param2String1);
        parcel.writeString(param2String2);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param2IDexModuleRegisterCallback != null) {
          iBinder = param2IDexModuleRegisterCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(103, parcel, null, 1) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().registerDexModule(param2String1, param2String2, param2Boolean, param2IDexModuleRegisterCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void registerMoveCallback(IPackageMoveObserver param2IPackageMoveObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2IPackageMoveObserver != null) {
          iBinder = param2IPackageMoveObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(112, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().registerMoveCallback(param2IPackageMoveObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removePermission(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(188, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().removePermission(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void replacePreferredActivity(IntentFilter param2IntentFilter, int param2Int1, ComponentName[] param2ArrayOfComponentName, ComponentName param2ComponentName, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2IntentFilter != null) {
          parcel1.writeInt(1);
          param2IntentFilter.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeTypedArray((Parcelable[])param2ArrayOfComponentName, 0);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().replacePreferredActivity(param2IntentFilter, param2Int1, param2ArrayOfComponentName, param2ComponentName, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resetApplicationPreferences(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().resetApplicationPreferences(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ProviderInfo resolveContentProvider(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().resolveContentProvider(param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ProviderInfo providerInfo = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ProviderInfo)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ResolveInfo resolveIntent(Intent param2Intent, String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().resolveIntent(param2Intent, param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ResolveInfo resolveInfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2Intent = null;
        } 
        return (ResolveInfo)param2Intent;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ResolveInfo resolveService(Intent param2Intent, String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
          return IPackageManager.Stub.getDefaultImpl().resolveService(param2Intent, param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ResolveInfo resolveInfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2Intent = null;
        } 
        return (ResolveInfo)param2Intent;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void restoreDefaultApps(byte[] param2ArrayOfbyte, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeByteArray(param2ArrayOfbyte);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(71, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().restoreDefaultApps(param2ArrayOfbyte, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void restoreIntentFilterVerification(byte[] param2ArrayOfbyte, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeByteArray(param2ArrayOfbyte);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().restoreIntentFilterVerification(param2ArrayOfbyte, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void restoreLabelAndIcon(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(77, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().restoreLabelAndIcon(param2ComponentName, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void restorePreferredActivities(byte[] param2ArrayOfbyte, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeByteArray(param2ArrayOfbyte);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().restorePreferredActivities(param2ArrayOfbyte, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean runBackgroundDexoptJob(List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeStringList(param2List);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(109, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().runBackgroundDexoptJob(param2List);
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
    
    public void sendDeviceCustomizationReadyBroadcast() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(178, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().sendDeviceCustomizationReadyBroadcast();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setApplicationCategoryHint(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().setApplicationCategoryHint(param2String1, param2Int, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setApplicationEnabledSetting(String param2String1, int param2Int1, int param2Int2, int param2Int3, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(80, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().setApplicationEnabledSetting(param2String1, param2Int1, param2Int2, param2Int3, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setApplicationHiddenSettingAsUser(String param2String, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        boolean bool1 = true;
        if (param2Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(131, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IPackageManager.Stub.getDefaultImpl().setApplicationHiddenSettingAsUser(param2String, param2Boolean, param2Int);
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
    
    public boolean setBlockUninstallForUser(String param2String, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        boolean bool1 = true;
        if (param2Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(136, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IPackageManager.Stub.getDefaultImpl().setBlockUninstallForUser(param2String, param2Boolean, param2Int);
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
    
    public void setComponentEnabledSetting(ComponentName param2ComponentName, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (!this.mRemote.transact(78, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().setComponentEnabledSetting(param2ComponentName, param2Int1, param2Int2, param2Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] setDistractingPackageRestrictionsAsUser(String[] param2ArrayOfString, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeStringArray(param2ArrayOfString);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(63, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2ArrayOfString = IPackageManager.Stub.getDefaultImpl().setDistractingPackageRestrictionsAsUser(param2ArrayOfString, param2Int1, param2Int2);
          return param2ArrayOfString;
        } 
        parcel2.readException();
        param2ArrayOfString = parcel2.createStringArray();
        return param2ArrayOfString;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setHarmfulAppWarning(String param2String, CharSequence param2CharSequence, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (param2CharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(164, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().setHarmfulAppWarning(param2String, param2CharSequence, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setHomeActivity(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().setHomeActivity(param2ComponentName, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setInstallLocation(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(116, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().setInstallLocation(param2Int);
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
    
    public void setInstallerPackageName(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().setInstallerPackageName(param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setInstantAppCookie(String param2String, byte[] param2ArrayOfbyte, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeByteArray(param2ArrayOfbyte);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(145, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().setInstantAppCookie(param2String, param2ArrayOfbyte, param2Int);
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
    
    public void setLastChosenActivity(Intent param2Intent, String param2String, int param2Int1, IntentFilter param2IntentFilter, int param2Int2, ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(param2String);
          try {
            parcel1.writeInt(param2Int1);
            if (param2IntentFilter != null) {
              parcel1.writeInt(1);
              param2IntentFilter.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeInt(param2Int2);
              if (param2ComponentName != null) {
                parcel1.writeInt(1);
                param2ComponentName.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
                IPackageManager.Stub.getDefaultImpl().setLastChosenActivity(param2Intent, param2String, param2Int1, param2IntentFilter, param2Int2, param2ComponentName);
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
      throw param2Intent;
    }
    
    public void setMimeGroup(String param2String1, String param2String2, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(192, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().setMimeGroup(param2String1, param2String2, param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPackageStoppedState(String param2String, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(84, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().setPackageStoppedState(param2String, param2Boolean, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] setPackagesSuspendedAsUser(String[] param2ArrayOfString, boolean param2Boolean, PersistableBundle param2PersistableBundle1, PersistableBundle param2PersistableBundle2, SuspendDialogInfo param2SuspendDialogInfo, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        try {
          boolean bool;
          parcel1.writeStringArray(param2ArrayOfString);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (param2PersistableBundle1 != null) {
            parcel1.writeInt(1);
            param2PersistableBundle1.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2PersistableBundle2 != null) {
            parcel1.writeInt(1);
            param2PersistableBundle2.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2SuspendDialogInfo != null) {
            parcel1.writeInt(1);
            param2SuspendDialogInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeString(param2String);
            parcel1.writeInt(param2Int);
            if (!this.mRemote.transact(64, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
              param2ArrayOfString = IPackageManager.Stub.getDefaultImpl().setPackagesSuspendedAsUser(param2ArrayOfString, param2Boolean, param2PersistableBundle1, param2PersistableBundle2, param2SuspendDialogInfo, param2String, param2Int);
              parcel2.recycle();
              parcel1.recycle();
              return param2ArrayOfString;
            } 
            parcel2.readException();
            param2ArrayOfString = parcel2.createStringArray();
            parcel2.recycle();
            parcel1.recycle();
            return param2ArrayOfString;
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2ArrayOfString;
    }
    
    public boolean setRequiredForSystemUser(String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(148, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IPackageManager.Stub.getDefaultImpl().setRequiredForSystemUser(param2String, param2Boolean);
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
    
    public void setRuntimePermissionsVersion(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(182, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().setRuntimePermissionsVersion(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSystemAppHiddenUntilInstalled(String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(133, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().setSystemAppHiddenUntilInstalled(param2String, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setSystemAppInstallState(String param2String, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        boolean bool1 = true;
        if (param2Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(134, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IPackageManager.Stub.getDefaultImpl().setSystemAppInstallState(param2String, param2Boolean, param2Int);
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
    
    public void setUpdateAvailable(String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(149, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().setUpdateAvailable(param2String, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void systemReady() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(97, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().systemReady();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterMoveCallback(IPackageMoveObserver param2IPackageMoveObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (param2IPackageMoveObserver != null) {
          iBinder = param2IPackageMoveObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(113, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().unregisterMoveCallback(param2IPackageMoveObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean updateIntentVerificationStatus(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(123, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          bool = IPackageManager.Stub.getDefaultImpl().updateIntentVerificationStatus(param2String, param2Int1, param2Int2);
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
    
    public void updatePackagesIfNeeded() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        if (!this.mRemote.transact(100, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().updatePackagesIfNeeded();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void verifyIntentFilter(int param2Int1, int param2Int2, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(121, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().verifyIntentFilter(param2Int1, param2Int2, param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void verifyPendingInstall(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(119, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
          IPackageManager.Stub.getDefaultImpl().verifyPendingInstall(param2Int1, param2Int2);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
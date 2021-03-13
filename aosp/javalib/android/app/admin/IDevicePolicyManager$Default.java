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
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.UserHandle;
import android.security.keymaster.KeymasterCertificateChain;
import android.security.keystore.ParcelableKeyGenParameterSpec;
import android.telephony.data.ApnSetting;
import java.util.List;

public class Default implements IDevicePolicyManager {
  public void addCrossProfileIntentFilter(ComponentName paramComponentName, IntentFilter paramIntentFilter, int paramInt) throws RemoteException {}
  
  public boolean addCrossProfileWidgetProvider(ComponentName paramComponentName, String paramString) throws RemoteException {
    return false;
  }
  
  public int addOverrideApn(ComponentName paramComponentName, ApnSetting paramApnSetting) throws RemoteException {
    return 0;
  }
  
  public void addPersistentPreferredActivity(ComponentName paramComponentName1, IntentFilter paramIntentFilter, ComponentName paramComponentName2) throws RemoteException {}
  
  public boolean approveCaCert(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public boolean bindDeviceAdminServiceAsUser(ComponentName paramComponentName, IApplicationThread paramIApplicationThread, IBinder paramIBinder, Intent paramIntent, IServiceConnection paramIServiceConnection, int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean canProfileOwnerResetPasswordWhenLocked(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean checkDeviceIdentifierAccess(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public int checkProvisioningPreCondition(String paramString1, String paramString2) throws RemoteException {
    return 0;
  }
  
  public void choosePrivateKeyAlias(int paramInt, Uri paramUri, String paramString, IBinder paramIBinder) throws RemoteException {}
  
  public void clearApplicationUserData(ComponentName paramComponentName, String paramString, IPackageDataObserver paramIPackageDataObserver) throws RemoteException {}
  
  public void clearCrossProfileIntentFilters(ComponentName paramComponentName) throws RemoteException {}
  
  public void clearDeviceOwner(String paramString) throws RemoteException {}
  
  public void clearPackagePersistentPreferredActivities(ComponentName paramComponentName, String paramString) throws RemoteException {}
  
  public void clearProfileOwner(ComponentName paramComponentName) throws RemoteException {}
  
  public boolean clearResetPasswordToken(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public void clearSystemUpdatePolicyFreezePeriodRecord() throws RemoteException {}
  
  public Intent createAdminSupportIntent(String paramString) throws RemoteException {
    return null;
  }
  
  public UserHandle createAndManageUser(ComponentName paramComponentName1, String paramString, ComponentName paramComponentName2, PersistableBundle paramPersistableBundle, int paramInt) throws RemoteException {
    return null;
  }
  
  public void enableSystemApp(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {}
  
  public int enableSystemAppWithIntent(ComponentName paramComponentName, String paramString, Intent paramIntent) throws RemoteException {
    return 0;
  }
  
  public void enforceCanManageCaCerts(ComponentName paramComponentName, String paramString) throws RemoteException {}
  
  public long forceNetworkLogs() throws RemoteException {
    return 0L;
  }
  
  public void forceRemoveActiveAdmin(ComponentName paramComponentName, int paramInt) throws RemoteException {}
  
  public long forceSecurityLogs() throws RemoteException {
    return 0L;
  }
  
  public void forceUpdateUserSetupComplete() throws RemoteException {}
  
  public boolean generateKeyPair(ComponentName paramComponentName, String paramString1, String paramString2, ParcelableKeyGenParameterSpec paramParcelableKeyGenParameterSpec, int paramInt, KeymasterCertificateChain paramKeymasterCertificateChain) throws RemoteException {
    return false;
  }
  
  public String[] getAccountTypesWithManagementDisabled() throws RemoteException {
    return null;
  }
  
  public String[] getAccountTypesWithManagementDisabledAsUser(int paramInt, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public List<ComponentName> getActiveAdmins(int paramInt) throws RemoteException {
    return null;
  }
  
  public List<String> getAffiliationIds(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public List<String> getAllCrossProfilePackages() throws RemoteException {
    return null;
  }
  
  public List<String> getAlwaysOnVpnLockdownWhitelist(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public String getAlwaysOnVpnPackage(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public String getAlwaysOnVpnPackageForUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public Bundle getApplicationRestrictions(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    return null;
  }
  
  public String getApplicationRestrictionsManagingPackage(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public boolean getAutoTimeEnabled(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean getAutoTimeRequired() throws RemoteException {
    return false;
  }
  
  public boolean getAutoTimeZoneEnabled(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public List<UserHandle> getBindDeviceAdminTargetUsers(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public boolean getBluetoothContactSharingDisabled(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean getBluetoothContactSharingDisabledForUser(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean getCameraDisabled(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public String getCertInstallerPackage(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public List<String> getCrossProfileCalendarPackages(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public List<String> getCrossProfileCalendarPackagesForUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public boolean getCrossProfileCallerIdDisabled(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean getCrossProfileCallerIdDisabledForUser(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean getCrossProfileContactsSearchDisabled(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean getCrossProfileContactsSearchDisabledForUser(int paramInt) throws RemoteException {
    return false;
  }
  
  public List<String> getCrossProfilePackages(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public List<String> getCrossProfileWidgetProviders(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public int getCurrentFailedPasswordAttempts(int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public List<String> getDefaultCrossProfilePackages() throws RemoteException {
    return null;
  }
  
  public List<String> getDelegatePackages(ComponentName paramComponentName, String paramString) throws RemoteException {
    return null;
  }
  
  public List<String> getDelegatedScopes(ComponentName paramComponentName, String paramString) throws RemoteException {
    return null;
  }
  
  public ComponentName getDeviceOwnerComponent(boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public CharSequence getDeviceOwnerLockScreenInfo() throws RemoteException {
    return null;
  }
  
  public String getDeviceOwnerName() throws RemoteException {
    return null;
  }
  
  public CharSequence getDeviceOwnerOrganizationName() throws RemoteException {
    return null;
  }
  
  public int getDeviceOwnerUserId() throws RemoteException {
    return 0;
  }
  
  public List<String> getDisallowedSystemApps(ComponentName paramComponentName, int paramInt, String paramString) throws RemoteException {
    return null;
  }
  
  public boolean getDoNotAskCredentialsOnBoot() throws RemoteException {
    return false;
  }
  
  public CharSequence getEndUserSessionMessage(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public FactoryResetProtectionPolicy getFactoryResetProtectionPolicy(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public boolean getForceEphemeralUsers(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public String getGlobalPrivateDnsHost(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public int getGlobalPrivateDnsMode(ComponentName paramComponentName) throws RemoteException {
    return 0;
  }
  
  public ComponentName getGlobalProxyAdmin(int paramInt) throws RemoteException {
    return null;
  }
  
  public List<String> getKeepUninstalledPackages(ComponentName paramComponentName, String paramString) throws RemoteException {
    return null;
  }
  
  public int getKeyguardDisabledFeatures(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public long getLastBugReportRequestTime() throws RemoteException {
    return 0L;
  }
  
  public long getLastNetworkLogRetrievalTime() throws RemoteException {
    return 0L;
  }
  
  public long getLastSecurityLogRetrievalTime() throws RemoteException {
    return 0L;
  }
  
  public int getLockTaskFeatures(ComponentName paramComponentName) throws RemoteException {
    return 0;
  }
  
  public String[] getLockTaskPackages(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public CharSequence getLongSupportMessage(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public CharSequence getLongSupportMessageForUser(ComponentName paramComponentName, int paramInt) throws RemoteException {
    return null;
  }
  
  public long getManagedProfileMaximumTimeOff(ComponentName paramComponentName) throws RemoteException {
    return 0L;
  }
  
  public int getMaximumFailedPasswordsForWipe(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public long getMaximumTimeToLock(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0L;
  }
  
  public List<String> getMeteredDataDisabledPackages(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public int getOrganizationColor(ComponentName paramComponentName) throws RemoteException {
    return 0;
  }
  
  public int getOrganizationColorForUser(int paramInt) throws RemoteException {
    return 0;
  }
  
  public CharSequence getOrganizationName(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public CharSequence getOrganizationNameForUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public List<ApnSetting> getOverrideApns(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public StringParceledListSlice getOwnerInstalledCaCerts(UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public int getPasswordComplexity(boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public long getPasswordExpiration(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0L;
  }
  
  public long getPasswordExpirationTimeout(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0L;
  }
  
  public int getPasswordHistoryLength(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public int getPasswordMinimumLength(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public int getPasswordMinimumLetters(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public int getPasswordMinimumLowerCase(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public PasswordMetrics getPasswordMinimumMetrics(int paramInt) throws RemoteException {
    return null;
  }
  
  public int getPasswordMinimumNonLetter(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public int getPasswordMinimumNumeric(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public int getPasswordMinimumSymbols(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public int getPasswordMinimumUpperCase(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public int getPasswordQuality(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public SystemUpdateInfo getPendingSystemUpdate(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public int getPermissionGrantState(ComponentName paramComponentName, String paramString1, String paramString2, String paramString3) throws RemoteException {
    return 0;
  }
  
  public int getPermissionPolicy(ComponentName paramComponentName) throws RemoteException {
    return 0;
  }
  
  public List getPermittedAccessibilityServices(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public List getPermittedAccessibilityServicesForUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public List<String> getPermittedCrossProfileNotificationListeners(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public List getPermittedInputMethods(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public List getPermittedInputMethodsForCurrentUser() throws RemoteException {
    return null;
  }
  
  public int getPersonalAppsSuspendedReasons(ComponentName paramComponentName) throws RemoteException {
    return 0;
  }
  
  public ComponentName getProfileOwner(int paramInt) throws RemoteException {
    return null;
  }
  
  public ComponentName getProfileOwnerAsUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public String getProfileOwnerName(int paramInt) throws RemoteException {
    return null;
  }
  
  public ComponentName getProfileOwnerOrDeviceOwnerSupervisionComponent(UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public int getProfileWithMinimumFailedPasswordsForWipe(int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public void getRemoveWarning(ComponentName paramComponentName, RemoteCallback paramRemoteCallback, int paramInt) throws RemoteException {}
  
  public long getRequiredStrongAuthTimeout(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0L;
  }
  
  public ComponentName getRestrictionsProvider(int paramInt) throws RemoteException {
    return null;
  }
  
  public boolean getScreenCaptureDisabled(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public List<UserHandle> getSecondaryUsers(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public CharSequence getShortSupportMessage(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public CharSequence getShortSupportMessageForUser(ComponentName paramComponentName, int paramInt) throws RemoteException {
    return null;
  }
  
  public CharSequence getStartUserSessionMessage(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public boolean getStorageEncryption(ComponentName paramComponentName, int paramInt) throws RemoteException {
    return false;
  }
  
  public int getStorageEncryptionStatus(String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public SystemUpdatePolicy getSystemUpdatePolicy() throws RemoteException {
    return null;
  }
  
  public PersistableBundle getTransferOwnershipBundle() throws RemoteException {
    return null;
  }
  
  public List<PersistableBundle> getTrustAgentConfiguration(ComponentName paramComponentName1, ComponentName paramComponentName2, int paramInt, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public List<String> getUserControlDisabledPackages(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public int getUserProvisioningState() throws RemoteException {
    return 0;
  }
  
  public Bundle getUserRestrictions(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public String getWifiMacAddress(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public boolean hasDeviceOwner() throws RemoteException {
    return false;
  }
  
  public boolean hasGrantedPolicy(ComponentName paramComponentName, int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean hasLockdownAdminConfiguredNetworks(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean hasUserSetupCompleted() throws RemoteException {
    return false;
  }
  
  public boolean installCaCert(ComponentName paramComponentName, String paramString, byte[] paramArrayOfbyte) throws RemoteException {
    return false;
  }
  
  public boolean installExistingPackage(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public boolean installKeyPair(ComponentName paramComponentName, String paramString1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, String paramString2, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    return false;
  }
  
  public void installUpdateFromFile(ComponentName paramComponentName, ParcelFileDescriptor paramParcelFileDescriptor, StartInstallingUpdateCallback paramStartInstallingUpdateCallback) throws RemoteException {}
  
  public boolean isAccessibilityServicePermittedByAdmin(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isActivePasswordSufficient(int paramInt, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean isAdminActive(ComponentName paramComponentName, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isAffiliatedUser() throws RemoteException {
    return false;
  }
  
  public boolean isAlwaysOnVpnLockdownEnabled(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isAlwaysOnVpnLockdownEnabledForUser(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isApplicationHidden(ComponentName paramComponentName, String paramString1, String paramString2, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean isBackupServiceEnabled(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isCaCertApproved(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isCallerApplicationRestrictionsManagingPackage(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isCommonCriteriaModeEnabled(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isCurrentInputMethodSetByOwner() throws RemoteException {
    return false;
  }
  
  public boolean isDeviceProvisioned() throws RemoteException {
    return false;
  }
  
  public boolean isDeviceProvisioningConfigApplied() throws RemoteException {
    return false;
  }
  
  public boolean isEphemeralUser(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isFactoryResetProtectionPolicySupported() throws RemoteException {
    return false;
  }
  
  public boolean isInputMethodPermittedByAdmin(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isLockTaskPermitted(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isLogoutEnabled() throws RemoteException {
    return false;
  }
  
  public boolean isManagedKiosk() throws RemoteException {
    return false;
  }
  
  public boolean isManagedProfile(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isMasterVolumeMuted(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isMeteredDataDisabledPackageForUser(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isNetworkLoggingEnabled(ComponentName paramComponentName, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isNotificationListenerServicePermitted(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isOrganizationOwnedDeviceWithManagedProfile() throws RemoteException {
    return false;
  }
  
  public boolean isOverrideApnEnabled(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isPackageAllowedToAccessCalendarForUser(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isPackageSuspended(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public boolean isPasswordSufficientAfterProfileUnification(int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean isProfileActivePasswordSufficientForParent(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isProvisioningAllowed(String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public boolean isRemovingAdmin(ComponentName paramComponentName, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isResetPasswordTokenActive(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isSecondaryLockscreenEnabled(UserHandle paramUserHandle) throws RemoteException {
    return false;
  }
  
  public boolean isSecurityLoggingEnabled(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isSeparateProfileChallengeAllowed(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isSystemOnlyUser(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isUnattendedManagedKiosk() throws RemoteException {
    return false;
  }
  
  public boolean isUninstallBlocked(ComponentName paramComponentName, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isUninstallInQueue(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isUsingUnifiedPassword(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public void lockNow(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public int logoutUser(ComponentName paramComponentName) throws RemoteException {
    return 0;
  }
  
  public void markProfileOwnerOnOrganizationOwnedDevice(ComponentName paramComponentName, int paramInt) throws RemoteException {}
  
  public void notifyLockTaskModeChanged(boolean paramBoolean, String paramString, int paramInt) throws RemoteException {}
  
  public void notifyPendingSystemUpdate(SystemUpdateInfo paramSystemUpdateInfo) throws RemoteException {}
  
  public boolean packageHasActiveAdmins(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public void reboot(ComponentName paramComponentName) throws RemoteException {}
  
  public void removeActiveAdmin(ComponentName paramComponentName, int paramInt) throws RemoteException {}
  
  public boolean removeCrossProfileWidgetProvider(ComponentName paramComponentName, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean removeKeyPair(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public boolean removeOverrideApn(ComponentName paramComponentName, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean removeUser(ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    return false;
  }
  
  public void reportFailedBiometricAttempt(int paramInt) throws RemoteException {}
  
  public void reportFailedPasswordAttempt(int paramInt) throws RemoteException {}
  
  public void reportKeyguardDismissed(int paramInt) throws RemoteException {}
  
  public void reportKeyguardSecured(int paramInt) throws RemoteException {}
  
  public void reportPasswordChanged(int paramInt) throws RemoteException {}
  
  public void reportSuccessfulBiometricAttempt(int paramInt) throws RemoteException {}
  
  public void reportSuccessfulPasswordAttempt(int paramInt) throws RemoteException {}
  
  public boolean requestBugreport(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean resetPassword(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean resetPasswordWithToken(ComponentName paramComponentName, String paramString, byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    return false;
  }
  
  public List<NetworkEvent> retrieveNetworkLogs(ComponentName paramComponentName, String paramString, long paramLong) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice retrievePreRebootSecurityLogs(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice retrieveSecurityLogs(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public void setAccountManagementDisabled(ComponentName paramComponentName, String paramString, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {}
  
  public void setActiveAdmin(ComponentName paramComponentName, boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public void setAffiliationIds(ComponentName paramComponentName, List<String> paramList) throws RemoteException {}
  
  public boolean setAlwaysOnVpnPackage(ComponentName paramComponentName, String paramString, boolean paramBoolean, List<String> paramList) throws RemoteException {
    return false;
  }
  
  public boolean setApplicationHidden(ComponentName paramComponentName, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    return false;
  }
  
  public void setApplicationRestrictions(ComponentName paramComponentName, String paramString1, String paramString2, Bundle paramBundle) throws RemoteException {}
  
  public boolean setApplicationRestrictionsManagingPackage(ComponentName paramComponentName, String paramString) throws RemoteException {
    return false;
  }
  
  public void setAutoTimeEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setAutoTimeRequired(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setAutoTimeZoneEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setBackupServiceEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setBluetoothContactSharingDisabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setCameraDisabled(ComponentName paramComponentName, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {}
  
  public void setCertInstallerPackage(ComponentName paramComponentName, String paramString) throws RemoteException {}
  
  public void setCommonCriteriaModeEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setConfiguredNetworksLockdownState(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setCrossProfileCalendarPackages(ComponentName paramComponentName, List<String> paramList) throws RemoteException {}
  
  public void setCrossProfileCallerIdDisabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setCrossProfileContactsSearchDisabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setCrossProfilePackages(ComponentName paramComponentName, List<String> paramList) throws RemoteException {}
  
  public void setDefaultSmsApplication(ComponentName paramComponentName, String paramString, boolean paramBoolean) throws RemoteException {}
  
  public void setDelegatedScopes(ComponentName paramComponentName, String paramString, List<String> paramList) throws RemoteException {}
  
  public boolean setDeviceOwner(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public void setDeviceOwnerLockScreenInfo(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {}
  
  public void setDeviceProvisioningConfigApplied() throws RemoteException {}
  
  public void setEndUserSessionMessage(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {}
  
  public void setFactoryResetProtectionPolicy(ComponentName paramComponentName, FactoryResetProtectionPolicy paramFactoryResetProtectionPolicy) throws RemoteException {}
  
  public void setForceEphemeralUsers(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public int setGlobalPrivateDns(ComponentName paramComponentName, int paramInt, String paramString) throws RemoteException {
    return 0;
  }
  
  public ComponentName setGlobalProxy(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    return null;
  }
  
  public void setGlobalSetting(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {}
  
  public void setKeepUninstalledPackages(ComponentName paramComponentName, String paramString, List<String> paramList) throws RemoteException {}
  
  public boolean setKeyGrantForApp(ComponentName paramComponentName, String paramString1, String paramString2, String paramString3, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean setKeyPairCertificate(ComponentName paramComponentName, String paramString1, String paramString2, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean setKeyguardDisabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public void setKeyguardDisabledFeatures(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setLocationEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setLockTaskFeatures(ComponentName paramComponentName, int paramInt) throws RemoteException {}
  
  public void setLockTaskPackages(ComponentName paramComponentName, String[] paramArrayOfString) throws RemoteException {}
  
  public void setLogoutEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setLongSupportMessage(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {}
  
  public void setManagedProfileMaximumTimeOff(ComponentName paramComponentName, long paramLong) throws RemoteException {}
  
  public void setMasterVolumeMuted(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setMaximumFailedPasswordsForWipe(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setMaximumTimeToLock(ComponentName paramComponentName, long paramLong, boolean paramBoolean) throws RemoteException {}
  
  public List<String> setMeteredDataDisabledPackages(ComponentName paramComponentName, List<String> paramList) throws RemoteException {
    return null;
  }
  
  public void setNetworkLoggingEnabled(ComponentName paramComponentName, String paramString, boolean paramBoolean) throws RemoteException {}
  
  public void setOrganizationColor(ComponentName paramComponentName, int paramInt) throws RemoteException {}
  
  public void setOrganizationColorForUser(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void setOrganizationName(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {}
  
  public void setOverrideApnsEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public String[] setPackagesSuspended(ComponentName paramComponentName, String paramString, String[] paramArrayOfString, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public void setPasswordExpirationTimeout(ComponentName paramComponentName, long paramLong, boolean paramBoolean) throws RemoteException {}
  
  public void setPasswordHistoryLength(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setPasswordMinimumLength(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setPasswordMinimumLetters(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setPasswordMinimumLowerCase(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setPasswordMinimumNonLetter(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setPasswordMinimumNumeric(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setPasswordMinimumSymbols(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setPasswordMinimumUpperCase(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setPasswordQuality(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setPermissionGrantState(ComponentName paramComponentName, String paramString1, String paramString2, String paramString3, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public void setPermissionPolicy(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {}
  
  public boolean setPermittedAccessibilityServices(ComponentName paramComponentName, List paramList) throws RemoteException {
    return false;
  }
  
  public boolean setPermittedCrossProfileNotificationListeners(ComponentName paramComponentName, List<String> paramList) throws RemoteException {
    return false;
  }
  
  public boolean setPermittedInputMethods(ComponentName paramComponentName, List paramList) throws RemoteException {
    return false;
  }
  
  public void setPersonalAppsSuspended(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setProfileEnabled(ComponentName paramComponentName) throws RemoteException {}
  
  public void setProfileName(ComponentName paramComponentName, String paramString) throws RemoteException {}
  
  public boolean setProfileOwner(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public void setRecommendedGlobalProxy(ComponentName paramComponentName, ProxyInfo paramProxyInfo) throws RemoteException {}
  
  public void setRequiredStrongAuthTimeout(ComponentName paramComponentName, long paramLong, boolean paramBoolean) throws RemoteException {}
  
  public boolean setResetPasswordToken(ComponentName paramComponentName, byte[] paramArrayOfbyte) throws RemoteException {
    return false;
  }
  
  public void setRestrictionsProvider(ComponentName paramComponentName1, ComponentName paramComponentName2) throws RemoteException {}
  
  public void setScreenCaptureDisabled(ComponentName paramComponentName, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {}
  
  public void setSecondaryLockscreenEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setSecureSetting(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {}
  
  public void setSecurityLoggingEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setShortSupportMessage(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {}
  
  public void setStartUserSessionMessage(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {}
  
  public boolean setStatusBarDisabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public int setStorageEncryption(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public void setSystemSetting(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {}
  
  public void setSystemUpdatePolicy(ComponentName paramComponentName, SystemUpdatePolicy paramSystemUpdatePolicy) throws RemoteException {}
  
  public boolean setTime(ComponentName paramComponentName, long paramLong) throws RemoteException {
    return false;
  }
  
  public boolean setTimeZone(ComponentName paramComponentName, String paramString) throws RemoteException {
    return false;
  }
  
  public void setTrustAgentConfiguration(ComponentName paramComponentName1, ComponentName paramComponentName2, PersistableBundle paramPersistableBundle, boolean paramBoolean) throws RemoteException {}
  
  public void setUninstallBlocked(ComponentName paramComponentName, String paramString1, String paramString2, boolean paramBoolean) throws RemoteException {}
  
  public void setUserControlDisabledPackages(ComponentName paramComponentName, List<String> paramList) throws RemoteException {}
  
  public void setUserIcon(ComponentName paramComponentName, Bitmap paramBitmap) throws RemoteException {}
  
  public void setUserProvisioningState(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void setUserRestriction(ComponentName paramComponentName, String paramString, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {}
  
  public void startManagedQuickContact(String paramString, long paramLong1, boolean paramBoolean, long paramLong2, Intent paramIntent) throws RemoteException {}
  
  public int startUserInBackground(ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    return 0;
  }
  
  public boolean startViewCalendarEventInManagedProfile(String paramString, long paramLong1, long paramLong2, long paramLong3, boolean paramBoolean, int paramInt) throws RemoteException {
    return false;
  }
  
  public int stopUser(ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    return 0;
  }
  
  public boolean switchUser(ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    return false;
  }
  
  public void transferOwnership(ComponentName paramComponentName1, ComponentName paramComponentName2, PersistableBundle paramPersistableBundle) throws RemoteException {}
  
  public void uninstallCaCerts(ComponentName paramComponentName, String paramString, String[] paramArrayOfString) throws RemoteException {}
  
  public void uninstallPackageWithActiveAdmins(String paramString) throws RemoteException {}
  
  public boolean updateOverrideApn(ComponentName paramComponentName, int paramInt, ApnSetting paramApnSetting) throws RemoteException {
    return false;
  }
  
  public void wipeDataWithReason(int paramInt, String paramString, boolean paramBoolean) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IDevicePolicyManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
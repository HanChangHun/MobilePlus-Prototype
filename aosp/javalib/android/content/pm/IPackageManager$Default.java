package android.content.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.dex.IArtManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

public class Default implements IPackageManager {
  public boolean activitySupportsIntent(ComponentName paramComponentName, Intent paramIntent, String paramString) throws RemoteException {
    return false;
  }
  
  public void addCrossProfileIntentFilter(IntentFilter paramIntentFilter, String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public boolean addPermission(PermissionInfo paramPermissionInfo) throws RemoteException {
    return false;
  }
  
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo) throws RemoteException {
    return false;
  }
  
  public void addPersistentPreferredActivity(IntentFilter paramIntentFilter, ComponentName paramComponentName, int paramInt) throws RemoteException {}
  
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt1, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName, int paramInt2) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public boolean canForwardTo(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean canRequestPackageInstalls(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString) throws RemoteException {
    return null;
  }
  
  public void checkPackageStartable(String paramString, int paramInt) throws RemoteException {}
  
  public int checkPermission(String paramString1, String paramString2, int paramInt) throws RemoteException {
    return 0;
  }
  
  public int checkSignatures(String paramString1, String paramString2) throws RemoteException {
    return 0;
  }
  
  public int checkUidPermission(String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public int checkUidSignatures(int paramInt1, int paramInt2) throws RemoteException {
    return 0;
  }
  
  public void clearApplicationProfileData(String paramString) throws RemoteException {}
  
  public void clearApplicationUserData(String paramString, IPackageDataObserver paramIPackageDataObserver, int paramInt) throws RemoteException {}
  
  public void clearCrossProfileIntentFilters(int paramInt, String paramString) throws RemoteException {}
  
  public void clearPackagePersistentPreferredActivities(String paramString, int paramInt) throws RemoteException {}
  
  public void clearPackagePreferredActivities(String paramString) throws RemoteException {}
  
  public boolean compileLayouts(String paramString) throws RemoteException {
    return false;
  }
  
  public String[] currentToCanonicalPackageNames(String[] paramArrayOfString) throws RemoteException {
    return null;
  }
  
  public void deleteApplicationCacheFiles(String paramString, IPackageDataObserver paramIPackageDataObserver) throws RemoteException {}
  
  public void deleteApplicationCacheFilesAsUser(String paramString, int paramInt, IPackageDataObserver paramIPackageDataObserver) throws RemoteException {}
  
  public void deleteExistingPackageAsUser(VersionedPackage paramVersionedPackage, IPackageDeleteObserver2 paramIPackageDeleteObserver2, int paramInt) throws RemoteException {}
  
  public void deletePackageAsUser(String paramString, int paramInt1, IPackageDeleteObserver paramIPackageDeleteObserver, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void deletePackageVersioned(VersionedPackage paramVersionedPackage, IPackageDeleteObserver2 paramIPackageDeleteObserver2, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void deletePreloadsFileCache() throws RemoteException {}
  
  public void dumpProfiles(String paramString) throws RemoteException {}
  
  public void enterSafeMode() throws RemoteException {}
  
  public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong) throws RemoteException {}
  
  public ResolveInfo findPersistentPreferredActivity(Intent paramIntent, int paramInt) throws RemoteException {
    return null;
  }
  
  public void finishPackageInstall(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void flushPackageRestrictionsAsUser(int paramInt) throws RemoteException {}
  
  public void forceDexOpt(String paramString) throws RemoteException {}
  
  public void freeStorage(String paramString, long paramLong, int paramInt, IntentSender paramIntentSender) throws RemoteException {}
  
  public void freeStorageAndNotify(String paramString, long paramLong, int paramInt, IPackageDataObserver paramIPackageDataObserver) throws RemoteException {}
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getAllIntentFilters(String paramString) throws RemoteException {
    return null;
  }
  
  public List<String> getAllPackages() throws RemoteException {
    return null;
  }
  
  public String[] getAppOpPermissionPackages(String paramString) throws RemoteException {
    return null;
  }
  
  public String getAppPredictionServicePackageName() throws RemoteException {
    return null;
  }
  
  public int getApplicationEnabledSetting(String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public boolean getApplicationHiddenSettingAsUser(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public IArtManager getArtManager() throws RemoteException {
    return null;
  }
  
  public String getAttentionServicePackageName() throws RemoteException {
    return null;
  }
  
  public boolean getBlockUninstallForUser(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public ChangedPackages getChangedPackages(int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public int getComponentEnabledSetting(ComponentName paramComponentName, int paramInt) throws RemoteException {
    return 0;
  }
  
  public String getContentCaptureServicePackageName() throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getDeclaredSharedLibraries(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public byte[] getDefaultAppsBackup(int paramInt) throws RemoteException {
    return null;
  }
  
  public String getDefaultTextClassifierPackageName() throws RemoteException {
    return null;
  }
  
  public int getFlagsForUid(int paramInt) throws RemoteException {
    return 0;
  }
  
  public CharSequence getHarmfulAppWarning(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public ComponentName getHomeActivities(List<ResolveInfo> paramList) throws RemoteException {
    return null;
  }
  
  public String getIncidentReportApproverPackageName() throws RemoteException {
    return null;
  }
  
  public int getInstallLocation() throws RemoteException {
    return 0;
  }
  
  public int getInstallReason(String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public InstallSourceInfo getInstallSourceInfo(String paramString) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getInstalledApplications(int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public List<ModuleInfo> getInstalledModules(int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getInstalledPackages(int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public String getInstallerPackageName(String paramString) throws RemoteException {
    return null;
  }
  
  public String getInstantAppAndroidId(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public byte[] getInstantAppCookie(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public Bitmap getInstantAppIcon(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public ComponentName getInstantAppInstallerComponent() throws RemoteException {
    return null;
  }
  
  public ComponentName getInstantAppResolverComponent() throws RemoteException {
    return null;
  }
  
  public ComponentName getInstantAppResolverSettingsComponent() throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getInstantApps(int paramInt) throws RemoteException {
    return null;
  }
  
  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt) throws RemoteException {
    return null;
  }
  
  public byte[] getIntentFilterVerificationBackup(int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getIntentFilterVerifications(String paramString) throws RemoteException {
    return null;
  }
  
  public int getIntentVerificationStatus(String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public KeySet getKeySetByAlias(String paramString1, String paramString2) throws RemoteException {
    return null;
  }
  
  public ResolveInfo getLastChosenActivity(Intent paramIntent, String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public List<String> getMimeGroup(String paramString1, String paramString2) throws RemoteException {
    return null;
  }
  
  public ModuleInfo getModuleInfo(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public int getMoveStatus(int paramInt) throws RemoteException {
    return 0;
  }
  
  public String getNameForUid(int paramInt) throws RemoteException {
    return null;
  }
  
  public String[] getNamesForUids(int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public int[] getPackageGids(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public PackageInfo getPackageInfoVersioned(VersionedPackage paramVersionedPackage, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public IPackageInstaller getPackageInstaller() throws RemoteException {
    return null;
  }
  
  public void getPackageSizeInfo(String paramString, int paramInt, IPackageStatsObserver paramIPackageStatsObserver) throws RemoteException {}
  
  public int getPackageUid(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return 0;
  }
  
  public String[] getPackagesForUid(int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public String getPermissionControllerPackageName() throws RemoteException {
    return null;
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getPersistentApplications(int paramInt) throws RemoteException {
    return null;
  }
  
  public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString) throws RemoteException {
    return 0;
  }
  
  public byte[] getPreferredActivityBackup(int paramInt) throws RemoteException {
    return null;
  }
  
  public int getPrivateFlagsForUid(int paramInt) throws RemoteException {
    return 0;
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public int getRuntimePermissionsVersion(int paramInt) throws RemoteException {
    return 0;
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public String getServicesSystemSharedLibraryPackageName() throws RemoteException {
    return null;
  }
  
  public String getSetupWizardPackageName() throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getSharedLibraries(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public String getSharedSystemSharedLibraryPackageName() throws RemoteException {
    return null;
  }
  
  public KeySet getSigningKeySet(String paramString) throws RemoteException {
    return null;
  }
  
  public Bundle getSuspendedPackageAppExtras(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getSystemAvailableFeatures() throws RemoteException {
    return null;
  }
  
  public String getSystemCaptionsServicePackageName() throws RemoteException {
    return null;
  }
  
  public String[] getSystemSharedLibraryNames() throws RemoteException {
    return null;
  }
  
  public String getSystemTextClassifierPackageName() throws RemoteException {
    return null;
  }
  
  public int getUidForSharedUser(String paramString) throws RemoteException {
    return 0;
  }
  
  public String[] getUnsuspendablePackagesForUser(String[] paramArrayOfString, int paramInt) throws RemoteException {
    return null;
  }
  
  public VerifierDeviceIdentity getVerifierDeviceIdentity() throws RemoteException {
    return null;
  }
  
  public String getWellbeingPackageName() throws RemoteException {
    return null;
  }
  
  public void grantImplicitAccess(int paramInt, String paramString) throws RemoteException {}
  
  public void grantRuntimePermission(String paramString1, String paramString2, int paramInt) throws RemoteException {}
  
  public boolean hasSigningCertificate(String paramString, byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean hasSystemFeature(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean hasSystemUidErrors() throws RemoteException {
    return false;
  }
  
  public boolean hasUidSigningCertificate(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) throws RemoteException {
    return false;
  }
  
  public int installExistingPackageAsUser(String paramString, int paramInt1, int paramInt2, int paramInt3, List<String> paramList) throws RemoteException {
    return 0;
  }
  
  public boolean isAutoRevokeWhitelisted(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isDeviceUpgrading() throws RemoteException {
    return false;
  }
  
  public boolean isFirstBoot() throws RemoteException {
    return false;
  }
  
  public boolean isInstantApp(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isOnlyCoreApps() throws RemoteException {
    return false;
  }
  
  public boolean isPackageAvailable(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isPackageDeviceAdminOnAnyUser(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isPackageSignedByKeySet(String paramString, KeySet paramKeySet) throws RemoteException {
    return false;
  }
  
  public boolean isPackageSignedByKeySetExactly(String paramString, KeySet paramKeySet) throws RemoteException {
    return false;
  }
  
  public boolean isPackageStateProtected(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isPackageSuspendedForUser(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isProtectedBroadcast(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isSafeMode() throws RemoteException {
    return false;
  }
  
  public boolean isStorageLow() throws RemoteException {
    return false;
  }
  
  public boolean isUidPrivileged(int paramInt) throws RemoteException {
    return false;
  }
  
  public void logAppProcessStartIfNeeded(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2) throws RemoteException {}
  
  public int movePackage(String paramString1, String paramString2) throws RemoteException {
    return 0;
  }
  
  public int movePrimaryStorage(String paramString) throws RemoteException {
    return 0;
  }
  
  public void notifyDexLoad(String paramString1, Map<String, String> paramMap, String paramString2) throws RemoteException {}
  
  public void notifyPackageUse(String paramString, int paramInt) throws RemoteException {}
  
  public void notifyPackagesReplacedReceived(String[] paramArrayOfString) throws RemoteException {}
  
  public void overrideLabelAndIcon(ComponentName paramComponentName, String paramString, int paramInt1, int paramInt2) throws RemoteException {}
  
  public boolean performDexOptMode(String paramString1, boolean paramBoolean1, String paramString2, boolean paramBoolean2, boolean paramBoolean3, String paramString3) throws RemoteException {
    return false;
  }
  
  public boolean performDexOptSecondary(String paramString1, String paramString2, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public void performFstrimIfNeeded() throws RemoteException {}
  
  public ParceledListSlice queryContentProviders(String paramString1, int paramInt1, int paramInt2, String paramString2) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice queryInstrumentation(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice queryIntentActivities(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, String[] paramArrayOfString, Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice queryIntentContentProviders(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice queryIntentReceivers(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice queryIntentServices(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public void querySyncProviders(List<String> paramList, List<ProviderInfo> paramList1) throws RemoteException {}
  
  public void reconcileSecondaryDexFiles(String paramString) throws RemoteException {}
  
  public void registerDexModule(String paramString1, String paramString2, boolean paramBoolean, IDexModuleRegisterCallback paramIDexModuleRegisterCallback) throws RemoteException {}
  
  public void registerMoveCallback(IPackageMoveObserver paramIPackageMoveObserver) throws RemoteException {}
  
  public void removePermission(String paramString) throws RemoteException {}
  
  public void replacePreferredActivity(IntentFilter paramIntentFilter, int paramInt1, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName, int paramInt2) throws RemoteException {}
  
  public void resetApplicationPreferences(int paramInt) throws RemoteException {}
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public void restoreDefaultApps(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {}
  
  public void restoreIntentFilterVerification(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {}
  
  public void restoreLabelAndIcon(ComponentName paramComponentName, int paramInt) throws RemoteException {}
  
  public void restorePreferredActivities(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {}
  
  public boolean runBackgroundDexoptJob(List<String> paramList) throws RemoteException {
    return false;
  }
  
  public void sendDeviceCustomizationReadyBroadcast() throws RemoteException {}
  
  public void setApplicationCategoryHint(String paramString1, int paramInt, String paramString2) throws RemoteException {}
  
  public void setApplicationEnabledSetting(String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2) throws RemoteException {}
  
  public boolean setApplicationHiddenSettingAsUser(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setBlockUninstallForUser(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    return false;
  }
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public String[] setDistractingPackageRestrictionsAsUser(String[] paramArrayOfString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public void setHarmfulAppWarning(String paramString, CharSequence paramCharSequence, int paramInt) throws RemoteException {}
  
  public void setHomeActivity(ComponentName paramComponentName, int paramInt) throws RemoteException {}
  
  public boolean setInstallLocation(int paramInt) throws RemoteException {
    return false;
  }
  
  public void setInstallerPackageName(String paramString1, String paramString2) throws RemoteException {}
  
  public boolean setInstantAppCookie(String paramString, byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    return false;
  }
  
  public void setLastChosenActivity(Intent paramIntent, String paramString, int paramInt1, IntentFilter paramIntentFilter, int paramInt2, ComponentName paramComponentName) throws RemoteException {}
  
  public void setMimeGroup(String paramString1, String paramString2, List<String> paramList) throws RemoteException {}
  
  public void setPackageStoppedState(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public String[] setPackagesSuspendedAsUser(String[] paramArrayOfString, boolean paramBoolean, PersistableBundle paramPersistableBundle1, PersistableBundle paramPersistableBundle2, SuspendDialogInfo paramSuspendDialogInfo, String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public boolean setRequiredForSystemUser(String paramString, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public void setRuntimePermissionsVersion(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void setSystemAppHiddenUntilInstalled(String paramString, boolean paramBoolean) throws RemoteException {}
  
  public boolean setSystemAppInstallState(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    return false;
  }
  
  public void setUpdateAvailable(String paramString, boolean paramBoolean) throws RemoteException {}
  
  public void systemReady() throws RemoteException {}
  
  public void unregisterMoveCallback(IPackageMoveObserver paramIPackageMoveObserver) throws RemoteException {}
  
  public boolean updateIntentVerificationStatus(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public void updatePackagesIfNeeded() throws RemoteException {}
  
  public void verifyIntentFilter(int paramInt1, int paramInt2, List<String> paramList) throws RemoteException {}
  
  public void verifyPendingInstall(int paramInt1, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
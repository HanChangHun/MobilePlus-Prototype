package android.content.pm.parsing;

import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureGroupInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageParser;
import android.content.pm.parsing.component.ParsedActivity;
import android.content.pm.parsing.component.ParsedAttribution;
import android.content.pm.parsing.component.ParsedInstrumentation;
import android.content.pm.parsing.component.ParsedIntentInfo;
import android.content.pm.parsing.component.ParsedPermission;
import android.content.pm.parsing.component.ParsedPermissionGroup;
import android.content.pm.parsing.component.ParsedProcess;
import android.content.pm.parsing.component.ParsedProvider;
import android.content.pm.parsing.component.ParsedService;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.security.PublicKey;
import java.util.Map;
import java.util.Set;

public interface ParsingPackage extends ParsingPackageRead {
  ParsingPackage addActivity(ParsedActivity paramParsedActivity);
  
  ParsingPackage addAdoptPermission(String paramString);
  
  ParsingPackage addAttribution(ParsedAttribution paramParsedAttribution);
  
  ParsingPackage addConfigPreference(ConfigurationInfo paramConfigurationInfo);
  
  ParsingPackage addFeatureGroup(FeatureGroupInfo paramFeatureGroupInfo);
  
  ParsingPackage addImplicitPermission(String paramString);
  
  ParsingPackage addInstrumentation(ParsedInstrumentation paramParsedInstrumentation);
  
  ParsingPackage addKeySet(String paramString, PublicKey paramPublicKey);
  
  ParsingPackage addLibraryName(String paramString);
  
  ParsingPackage addOriginalPackage(String paramString);
  
  ParsingPackage addOverlayable(String paramString1, String paramString2);
  
  ParsingPackage addPermission(ParsedPermission paramParsedPermission);
  
  ParsingPackage addPermissionGroup(ParsedPermissionGroup paramParsedPermissionGroup);
  
  ParsingPackage addPreferredActivityFilter(String paramString, ParsedIntentInfo paramParsedIntentInfo);
  
  ParsingPackage addProtectedBroadcast(String paramString);
  
  ParsingPackage addProvider(ParsedProvider paramParsedProvider);
  
  ParsingPackage addQueriesIntent(Intent paramIntent);
  
  ParsingPackage addQueriesPackage(String paramString);
  
  ParsingPackage addQueriesProvider(String paramString);
  
  ParsingPackage addReceiver(ParsedActivity paramParsedActivity);
  
  ParsingPackage addReqFeature(FeatureInfo paramFeatureInfo);
  
  ParsingPackage addRequestedPermission(String paramString);
  
  ParsingPackage addService(ParsedService paramParsedService);
  
  ParsingPackage addUsesLibrary(String paramString);
  
  ParsingPackage addUsesOptionalLibrary(String paramString);
  
  ParsingPackage addUsesStaticLibrary(String paramString);
  
  ParsingPackage addUsesStaticLibraryCertDigests(String[] paramArrayOfString);
  
  ParsingPackage addUsesStaticLibraryVersion(long paramLong);
  
  ParsingPackage asSplit(String[] paramArrayOfString1, String[] paramArrayOfString2, int[] paramArrayOfint, SparseArray<int[]> paramSparseArray);
  
  @Deprecated
  Object hideAsParsed();
  
  ParsingPackage removeUsesOptionalLibrary(String paramString);
  
  ParsingPackage setAllowAudioPlaybackCapture(boolean paramBoolean);
  
  ParsingPackage setAllowBackup(boolean paramBoolean);
  
  ParsingPackage setAllowClearUserData(boolean paramBoolean);
  
  ParsingPackage setAllowClearUserDataOnFailedRestore(boolean paramBoolean);
  
  ParsingPackage setAllowNativeHeapPointerTagging(boolean paramBoolean);
  
  ParsingPackage setAllowTaskReparenting(boolean paramBoolean);
  
  ParsingPackage setAnyDensity(int paramInt);
  
  ParsingPackage setAppComponentFactory(String paramString);
  
  ParsingPackage setAutoRevokePermissions(int paramInt);
  
  ParsingPackage setBackupAgentName(String paramString);
  
  ParsingPackage setBackupInForeground(boolean paramBoolean);
  
  ParsingPackage setBanner(int paramInt);
  
  ParsingPackage setBaseHardwareAccelerated(boolean paramBoolean);
  
  ParsingPackage setBaseRevisionCode(int paramInt);
  
  ParsingPackage setCantSaveState(boolean paramBoolean);
  
  ParsingPackage setCategory(int paramInt);
  
  ParsingPackage setClassLoaderName(String paramString);
  
  ParsingPackage setClassName(String paramString);
  
  ParsingPackage setCompatibleWidthLimitDp(int paramInt);
  
  ParsingPackage setCompileSdkVersion(int paramInt);
  
  ParsingPackage setCompileSdkVersionCodename(String paramString);
  
  ParsingPackage setCrossProfile(boolean paramBoolean);
  
  ParsingPackage setDebuggable(boolean paramBoolean);
  
  ParsingPackage setDefaultToDeviceProtectedStorage(boolean paramBoolean);
  
  ParsingPackage setDescriptionRes(int paramInt);
  
  ParsingPackage setDirectBootAware(boolean paramBoolean);
  
  ParsingPackage setEnabled(boolean paramBoolean);
  
  ParsingPackage setExternalStorage(boolean paramBoolean);
  
  ParsingPackage setExtractNativeLibs(boolean paramBoolean);
  
  ParsingPackage setForceQueryable(boolean paramBoolean);
  
  ParsingPackage setFullBackupContent(int paramInt);
  
  ParsingPackage setFullBackupOnly(boolean paramBoolean);
  
  ParsingPackage setGame(boolean paramBoolean);
  
  ParsingPackage setGwpAsanMode(int paramInt);
  
  ParsingPackage setHasCode(boolean paramBoolean);
  
  ParsingPackage setHasDomainUrls(boolean paramBoolean);
  
  ParsingPackage setHasFragileUserData(boolean paramBoolean);
  
  ParsingPackage setIconRes(int paramInt);
  
  ParsingPackage setInstallLocation(int paramInt);
  
  ParsingPackage setIsolatedSplitLoading(boolean paramBoolean);
  
  ParsingPackage setKillAfterRestore(boolean paramBoolean);
  
  ParsingPackage setLabelRes(int paramInt);
  
  ParsingPackage setLargeHeap(boolean paramBoolean);
  
  ParsingPackage setLargestWidthLimitDp(int paramInt);
  
  ParsingPackage setLogo(int paramInt);
  
  ParsingPackage setManageSpaceActivityName(String paramString);
  
  ParsingPackage setMaxAspectRatio(float paramFloat);
  
  ParsingPackage setMetaData(Bundle paramBundle);
  
  ParsingPackage setMinAspectRatio(float paramFloat);
  
  ParsingPackage setMinExtensionVersions(SparseIntArray paramSparseIntArray);
  
  ParsingPackage setMinSdkVersion(int paramInt);
  
  ParsingPackage setMultiArch(boolean paramBoolean);
  
  ParsingPackage setNetworkSecurityConfigRes(int paramInt);
  
  ParsingPackage setNonLocalizedLabel(CharSequence paramCharSequence);
  
  ParsingPackage setOverlay(boolean paramBoolean);
  
  ParsingPackage setOverlayCategory(String paramString);
  
  ParsingPackage setOverlayIsStatic(boolean paramBoolean);
  
  ParsingPackage setOverlayPriority(int paramInt);
  
  ParsingPackage setOverlayTarget(String paramString);
  
  ParsingPackage setOverlayTargetName(String paramString);
  
  ParsingPackage setPartiallyDirectBootAware(boolean paramBoolean);
  
  ParsingPackage setPermission(String paramString);
  
  ParsingPackage setPersistent(boolean paramBoolean);
  
  ParsingPackage setPreserveLegacyExternalStorage(boolean paramBoolean);
  
  ParsingPackage setProcessName(String paramString);
  
  ParsingPackage setProcesses(Map<String, ParsedProcess> paramMap);
  
  ParsingPackage setProfileableByShell(boolean paramBoolean);
  
  ParsingPackage setRealPackage(String paramString);
  
  ParsingPackage setRequestLegacyExternalStorage(boolean paramBoolean);
  
  ParsingPackage setRequiredAccountType(String paramString);
  
  ParsingPackage setRequiredForAllUsers(boolean paramBoolean);
  
  ParsingPackage setRequiresSmallestWidthDp(int paramInt);
  
  ParsingPackage setResizeable(int paramInt);
  
  ParsingPackage setResizeableActivity(Boolean paramBoolean);
  
  ParsingPackage setResizeableActivityViaSdkVersion(boolean paramBoolean);
  
  ParsingPackage setRestoreAnyVersion(boolean paramBoolean);
  
  ParsingPackage setRestrictUpdateHash(byte[] paramArrayOfbyte);
  
  ParsingPackage setRestrictedAccountType(String paramString);
  
  ParsingPackage setRoundIconRes(int paramInt);
  
  ParsingPackage setSharedUserId(String paramString);
  
  ParsingPackage setSharedUserLabel(int paramInt);
  
  ParsingPackage setSigningDetails(PackageParser.SigningDetails paramSigningDetails);
  
  ParsingPackage setSplitClassLoaderName(int paramInt, String paramString);
  
  ParsingPackage setSplitHasCode(int paramInt, boolean paramBoolean);
  
  ParsingPackage setStaticSharedLibName(String paramString);
  
  ParsingPackage setStaticSharedLibVersion(long paramLong);
  
  ParsingPackage setStaticSharedLibrary(boolean paramBoolean);
  
  ParsingPackage setSupportsExtraLargeScreens(int paramInt);
  
  ParsingPackage setSupportsLargeScreens(int paramInt);
  
  ParsingPackage setSupportsNormalScreens(int paramInt);
  
  ParsingPackage setSupportsRtl(boolean paramBoolean);
  
  ParsingPackage setSupportsSmallScreens(int paramInt);
  
  ParsingPackage setTargetSandboxVersion(int paramInt);
  
  ParsingPackage setTargetSdkVersion(int paramInt);
  
  ParsingPackage setTaskAffinity(String paramString);
  
  ParsingPackage setTestOnly(boolean paramBoolean);
  
  ParsingPackage setTheme(int paramInt);
  
  ParsingPackage setUiOptions(int paramInt);
  
  ParsingPackage setUpgradeKeySets(Set<String> paramSet);
  
  ParsingPackage setUse32BitAbi(boolean paramBoolean);
  
  ParsingPackage setUseEmbeddedDex(boolean paramBoolean);
  
  ParsingPackage setUsesCleartextTraffic(boolean paramBoolean);
  
  ParsingPackage setUsesNonSdkApi(boolean paramBoolean);
  
  ParsingPackage setVersionName(String paramString);
  
  ParsingPackage setVisibleToInstantApps(boolean paramBoolean);
  
  ParsingPackage setVmSafeMode(boolean paramBoolean);
  
  ParsingPackage setVolumeUuid(String paramString);
  
  ParsingPackage setZygotePreloadName(String paramString);
  
  ParsingPackage sortActivities();
  
  ParsingPackage sortReceivers();
  
  ParsingPackage sortServices();
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/ParsingPackage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
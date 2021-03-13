package android.content.pm.parsing;

import android.apex.ApexInfo;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.FallbackCategoryProvider;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.PackageUserState;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.SELinuxUtil;
import android.content.pm.ServiceInfo;
import android.content.pm.SigningInfo;
import android.content.pm.parsing.component.ComponentParseUtils;
import android.content.pm.parsing.component.ParsedActivity;
import android.content.pm.parsing.component.ParsedComponent;
import android.content.pm.parsing.component.ParsedInstrumentation;
import android.content.pm.parsing.component.ParsedMainComponent;
import android.content.pm.parsing.component.ParsedPermission;
import android.content.pm.parsing.component.ParsedPermissionGroup;
import android.content.pm.parsing.component.ParsedProvider;
import android.content.pm.parsing.component.ParsedService;
import android.os.Environment;
import android.os.UserHandle;
import com.android.internal.util.ArrayUtils;
import java.io.File;
import java.util.Collections;
import java.util.Set;
import libcore.util.EmptyArray;

public class PackageInfoWithoutStateUtils {
  public static int appInfoFlags(ParsingPackageRead paramParsingPackageRead) {
    return flag(paramParsingPackageRead.isExternalStorage(), 262144) | flag(paramParsingPackageRead.isBaseHardwareAccelerated(), 536870912) | flag(paramParsingPackageRead.isAllowBackup(), 32768) | flag(paramParsingPackageRead.isKillAfterRestore(), 65536) | flag(paramParsingPackageRead.isRestoreAnyVersion(), 131072) | flag(paramParsingPackageRead.isFullBackupOnly(), 67108864) | flag(paramParsingPackageRead.isPersistent(), 8) | flag(paramParsingPackageRead.isDebuggable(), 2) | flag(paramParsingPackageRead.isVmSafeMode(), 16384) | flag(paramParsingPackageRead.isHasCode(), 4) | flag(paramParsingPackageRead.isAllowTaskReparenting(), 32) | flag(paramParsingPackageRead.isAllowClearUserData(), 64) | flag(paramParsingPackageRead.isLargeHeap(), 1048576) | flag(paramParsingPackageRead.isUsesCleartextTraffic(), 134217728) | flag(paramParsingPackageRead.isSupportsRtl(), 4194304) | flag(paramParsingPackageRead.isTestOnly(), 256) | flag(paramParsingPackageRead.isMultiArch(), -2147483648) | flag(paramParsingPackageRead.isExtractNativeLibs(), 268435456) | flag(paramParsingPackageRead.isGame(), 33554432) | flag(paramParsingPackageRead.isSupportsSmallScreens(), 512) | flag(paramParsingPackageRead.isSupportsNormalScreens(), 1024) | flag(paramParsingPackageRead.isSupportsLargeScreens(), 2048) | flag(paramParsingPackageRead.isSupportsExtraLargeScreens(), 524288) | flag(paramParsingPackageRead.isResizeable(), 4096) | flag(paramParsingPackageRead.isAnyDensity(), 8192);
  }
  
  public static int appInfoPrivateFlags(ParsingPackageRead paramParsingPackageRead) {
    int i = flag(paramParsingPackageRead.isStaticSharedLibrary(), 16384) | flag(paramParsingPackageRead.isOverlay(), 268435456) | flag(paramParsingPackageRead.isIsolatedSplitLoading(), 32768) | flag(paramParsingPackageRead.isHasDomainUrls(), 16) | flag(paramParsingPackageRead.isProfileableByShell(), 8388608) | flag(paramParsingPackageRead.isBackupInForeground(), 8192) | flag(paramParsingPackageRead.isUseEmbeddedDex(), 33554432) | flag(paramParsingPackageRead.isDefaultToDeviceProtectedStorage(), 32) | flag(paramParsingPackageRead.isDirectBootAware(), 64) | flag(paramParsingPackageRead.isPartiallyDirectBootAware(), 256) | flag(paramParsingPackageRead.isAllowClearUserDataOnFailedRestore(), 67108864) | flag(paramParsingPackageRead.isAllowAudioPlaybackCapture(), 134217728) | flag(paramParsingPackageRead.isRequestLegacyExternalStorage(), 536870912) | flag(paramParsingPackageRead.isUsesNonSdkApi(), 4194304) | flag(paramParsingPackageRead.isHasFragileUserData(), 16777216) | flag(paramParsingPackageRead.isCantSaveState(), 2) | flag(paramParsingPackageRead.isResizeableActivityViaSdkVersion(), 4096) | flag(paramParsingPackageRead.isAllowNativeHeapPointerTagging(), -2147483648);
    Boolean bool = paramParsingPackageRead.getResizeableActivity();
    int j = i;
    if (bool != null)
      if (bool.booleanValue()) {
        j = i | 0x400;
      } else {
        j = i | 0x800;
      }  
    return j;
  }
  
  private static void assignSharedFieldsForComponentInfo(ComponentInfo paramComponentInfo, ParsedMainComponent paramParsedMainComponent) {
    assignSharedFieldsForPackageItemInfo((PackageItemInfo)paramComponentInfo, (ParsedComponent)paramParsedMainComponent);
    paramComponentInfo.descriptionRes = paramParsedMainComponent.getDescriptionRes();
    paramComponentInfo.directBootAware = paramParsedMainComponent.isDirectBootAware();
    paramComponentInfo.enabled = paramParsedMainComponent.isEnabled();
    paramComponentInfo.splitName = paramParsedMainComponent.getSplitName();
  }
  
  private static void assignSharedFieldsForPackageItemInfo(PackageItemInfo paramPackageItemInfo, ParsedComponent paramParsedComponent) {
    paramPackageItemInfo.nonLocalizedLabel = ComponentParseUtils.getNonLocalizedLabel(paramParsedComponent);
    paramPackageItemInfo.icon = ComponentParseUtils.getIcon(paramParsedComponent);
    paramPackageItemInfo.banner = paramParsedComponent.getBanner();
    paramPackageItemInfo.labelRes = paramParsedComponent.getLabelRes();
    paramPackageItemInfo.logo = paramParsedComponent.getLogo();
    paramPackageItemInfo.name = paramParsedComponent.getName();
    paramPackageItemInfo.packageName = paramParsedComponent.getPackageName();
  }
  
  private static boolean checkUseInstalled(ParsingPackageRead paramParsingPackageRead, PackageUserState paramPackageUserState, int paramInt) {
    return paramPackageUserState.isAvailable(paramInt);
  }
  
  private static int flag(boolean paramBoolean, int paramInt) {
    return paramBoolean ? paramInt : 0;
  }
  
  public static PackageInfo generate(ParsingPackageRead paramParsingPackageRead, ApexInfo paramApexInfo, int paramInt) {
    return generateWithComponents(paramParsingPackageRead, EmptyArray.INT, paramInt, 0L, 0L, Collections.emptySet(), new PackageUserState(), UserHandle.getCallingUserId(), paramApexInfo);
  }
  
  public static PackageInfo generate(ParsingPackageRead paramParsingPackageRead, int[] paramArrayOfint, int paramInt1, long paramLong1, long paramLong2, Set<String> paramSet, PackageUserState paramPackageUserState, int paramInt2) {
    return generateWithComponents(paramParsingPackageRead, paramArrayOfint, paramInt1, paramLong1, paramLong2, paramSet, paramPackageUserState, paramInt2, null);
  }
  
  public static ActivityInfo generateActivityInfo(ParsingPackageRead paramParsingPackageRead, ParsedActivity paramParsedActivity, int paramInt1, PackageUserState paramPackageUserState, int paramInt2) {
    return generateActivityInfo(paramParsingPackageRead, paramParsedActivity, paramInt1, paramPackageUserState, null, paramInt2);
  }
  
  public static ActivityInfo generateActivityInfo(ParsingPackageRead paramParsingPackageRead, ParsedActivity paramParsedActivity, int paramInt1, PackageUserState paramPackageUserState, ApplicationInfo paramApplicationInfo, int paramInt2) {
    if (paramParsedActivity == null)
      return null; 
    if (!checkUseInstalled(paramParsingPackageRead, paramPackageUserState, paramInt1))
      return null; 
    ApplicationInfo applicationInfo = paramApplicationInfo;
    if (paramApplicationInfo == null)
      applicationInfo = generateApplicationInfo(paramParsingPackageRead, paramInt1, paramPackageUserState, paramInt2); 
    return (applicationInfo == null) ? null : generateActivityInfoUnchecked(paramParsedActivity, applicationInfo);
  }
  
  public static ActivityInfo generateActivityInfoUnchecked(ParsedActivity paramParsedActivity, ApplicationInfo paramApplicationInfo) {
    ActivityInfo activityInfo = new ActivityInfo();
    assignSharedFieldsForComponentInfo((ComponentInfo)activityInfo, (ParsedMainComponent)paramParsedActivity);
    activityInfo.targetActivity = paramParsedActivity.getTargetActivity();
    activityInfo.processName = paramParsedActivity.getProcessName();
    activityInfo.exported = paramParsedActivity.isExported();
    activityInfo.theme = paramParsedActivity.getTheme();
    activityInfo.uiOptions = paramParsedActivity.getUiOptions();
    activityInfo.parentActivityName = paramParsedActivity.getParentActivityName();
    activityInfo.permission = paramParsedActivity.getPermission();
    activityInfo.taskAffinity = paramParsedActivity.getTaskAffinity();
    activityInfo.flags = paramParsedActivity.getFlags();
    activityInfo.privateFlags = paramParsedActivity.getPrivateFlags();
    activityInfo.launchMode = paramParsedActivity.getLaunchMode();
    activityInfo.documentLaunchMode = paramParsedActivity.getDocumentLaunchMode();
    activityInfo.maxRecents = paramParsedActivity.getMaxRecents();
    activityInfo.configChanges = paramParsedActivity.getConfigChanges();
    activityInfo.softInputMode = paramParsedActivity.getSoftInputMode();
    activityInfo.persistableMode = paramParsedActivity.getPersistableMode();
    activityInfo.lockTaskLaunchMode = paramParsedActivity.getLockTaskLaunchMode();
    activityInfo.screenOrientation = paramParsedActivity.getScreenOrientation();
    activityInfo.resizeMode = paramParsedActivity.getResizeMode();
    Float float_ = paramParsedActivity.getMaxAspectRatio();
    float f1 = 0.0F;
    if (float_ != null) {
      f2 = float_.floatValue();
    } else {
      f2 = 0.0F;
    } 
    activityInfo.maxAspectRatio = f2;
    float_ = paramParsedActivity.getMinAspectRatio();
    float f2 = f1;
    if (float_ != null)
      f2 = float_.floatValue(); 
    activityInfo.minAspectRatio = f2;
    activityInfo.supportsSizeChanges = paramParsedActivity.getSupportsSizeChanges();
    activityInfo.requestedVrComponent = paramParsedActivity.getRequestedVrComponent();
    activityInfo.rotationAnimation = paramParsedActivity.getRotationAnimation();
    activityInfo.colorMode = paramParsedActivity.getColorMode();
    activityInfo.windowLayout = paramParsedActivity.getWindowLayout();
    activityInfo.metaData = paramParsedActivity.getMetaData();
    activityInfo.applicationInfo = paramApplicationInfo;
    return activityInfo;
  }
  
  public static ApplicationInfo generateApplicationInfo(ParsingPackageRead paramParsingPackageRead, int paramInt1, PackageUserState paramPackageUserState, int paramInt2) {
    return (paramParsingPackageRead == null) ? null : (!checkUseInstalled(paramParsingPackageRead, paramPackageUserState, paramInt1) ? null : generateApplicationInfoUnchecked(paramParsingPackageRead, paramInt1, paramPackageUserState, paramInt2));
  }
  
  public static ApplicationInfo generateApplicationInfoUnchecked(ParsingPackageRead paramParsingPackageRead, int paramInt1, PackageUserState paramPackageUserState, int paramInt2) {
    ApplicationInfo applicationInfo = paramParsingPackageRead.toAppInfoWithoutState();
    applicationInfo.initForUser(paramInt2);
    if ((paramInt1 & 0x80) == 0)
      applicationInfo.metaData = null; 
    if ((paramInt1 & 0x400) == 0) {
      applicationInfo.sharedLibraryFiles = null;
      applicationInfo.sharedLibraryInfos = null;
    } 
    if (!PackageParser.sCompatibilityModeEnabled)
      applicationInfo.disableCompatibilityMode(); 
    applicationInfo.flags |= flag(paramPackageUserState.stopped, 2097152) | flag(paramPackageUserState.installed, 8388608) | flag(paramPackageUserState.suspended, 1073741824);
    int i = applicationInfo.privateFlags;
    int j = flag(paramPackageUserState.instantApp, 128);
    paramInt2 = flag(paramPackageUserState.virtualPreload, 65536);
    boolean bool = paramPackageUserState.hidden;
    boolean bool1 = true;
    applicationInfo.privateFlags = i | j | paramInt2 | flag(bool, 1);
    if (paramPackageUserState.enabled == 1) {
      applicationInfo.enabled = true;
    } else if (paramPackageUserState.enabled == 4) {
      if ((0x8000 & paramInt1) == 0)
        bool1 = false; 
      applicationInfo.enabled = bool1;
    } else if (paramPackageUserState.enabled == 2 || paramPackageUserState.enabled == 3) {
      applicationInfo.enabled = false;
    } 
    applicationInfo.enabledSetting = paramPackageUserState.enabled;
    if (applicationInfo.category == -1)
      applicationInfo.category = paramPackageUserState.categoryHint; 
    if (applicationInfo.category == -1)
      applicationInfo.category = FallbackCategoryProvider.getFallbackCategory(applicationInfo.packageName); 
    applicationInfo.seInfoUser = SELinuxUtil.assignSeinfoUser(paramPackageUserState);
    applicationInfo.resourceDirs = paramPackageUserState.getAllOverlayPaths();
    return applicationInfo;
  }
  
  public static InstrumentationInfo generateInstrumentationInfo(ParsedInstrumentation paramParsedInstrumentation, ParsingPackageRead paramParsingPackageRead, int paramInt1, int paramInt2) {
    if (paramParsedInstrumentation == null)
      return null; 
    InstrumentationInfo instrumentationInfo = new InstrumentationInfo();
    assignSharedFieldsForPackageItemInfo((PackageItemInfo)instrumentationInfo, (ParsedComponent)paramParsedInstrumentation);
    instrumentationInfo.targetPackage = paramParsedInstrumentation.getTargetPackage();
    instrumentationInfo.targetProcesses = paramParsedInstrumentation.getTargetProcesses();
    instrumentationInfo.handleProfiling = paramParsedInstrumentation.isHandleProfiling();
    instrumentationInfo.functionalTest = paramParsedInstrumentation.isFunctionalTest();
    instrumentationInfo.sourceDir = paramParsingPackageRead.getBaseCodePath();
    instrumentationInfo.publicSourceDir = paramParsingPackageRead.getBaseCodePath();
    instrumentationInfo.splitNames = paramParsingPackageRead.getSplitNames();
    instrumentationInfo.splitSourceDirs = paramParsingPackageRead.getSplitCodePaths();
    instrumentationInfo.splitPublicSourceDirs = paramParsingPackageRead.getSplitCodePaths();
    instrumentationInfo.splitDependencies = paramParsingPackageRead.getSplitDependencies();
    instrumentationInfo.dataDir = getDataDir(paramParsingPackageRead, paramInt2).getAbsolutePath();
    instrumentationInfo.deviceProtectedDataDir = getDeviceProtectedDataDir(paramParsingPackageRead, paramInt2).getAbsolutePath();
    instrumentationInfo.credentialProtectedDataDir = getCredentialProtectedDataDir(paramParsingPackageRead, paramInt2).getAbsolutePath();
    if ((paramInt1 & 0x80) == 0)
      return instrumentationInfo; 
    instrumentationInfo.metaData = paramParsedInstrumentation.getMetaData();
    return instrumentationInfo;
  }
  
  public static PermissionGroupInfo generatePermissionGroupInfo(ParsedPermissionGroup paramParsedPermissionGroup, int paramInt) {
    if (paramParsedPermissionGroup == null)
      return null; 
    PermissionGroupInfo permissionGroupInfo = new PermissionGroupInfo(paramParsedPermissionGroup.getRequestDetailResourceId(), paramParsedPermissionGroup.getBackgroundRequestResourceId(), paramParsedPermissionGroup.getBackgroundRequestDetailResourceId());
    assignSharedFieldsForPackageItemInfo((PackageItemInfo)permissionGroupInfo, (ParsedComponent)paramParsedPermissionGroup);
    permissionGroupInfo.descriptionRes = paramParsedPermissionGroup.getDescriptionRes();
    permissionGroupInfo.priority = paramParsedPermissionGroup.getPriority();
    permissionGroupInfo.requestRes = paramParsedPermissionGroup.getRequestRes();
    permissionGroupInfo.flags = paramParsedPermissionGroup.getFlags();
    if ((paramInt & 0x80) == 0)
      return permissionGroupInfo; 
    permissionGroupInfo.metaData = paramParsedPermissionGroup.getMetaData();
    return permissionGroupInfo;
  }
  
  public static PermissionInfo generatePermissionInfo(ParsedPermission paramParsedPermission, int paramInt) {
    if (paramParsedPermission == null)
      return null; 
    PermissionInfo permissionInfo = new PermissionInfo(paramParsedPermission.getBackgroundPermission());
    assignSharedFieldsForPackageItemInfo((PackageItemInfo)permissionInfo, (ParsedComponent)paramParsedPermission);
    permissionInfo.group = paramParsedPermission.getGroup();
    permissionInfo.requestRes = paramParsedPermission.getRequestRes();
    permissionInfo.protectionLevel = paramParsedPermission.getProtectionLevel();
    permissionInfo.descriptionRes = paramParsedPermission.getDescriptionRes();
    permissionInfo.flags = paramParsedPermission.getFlags();
    if ((paramInt & 0x80) == 0)
      return permissionInfo; 
    permissionInfo.metaData = paramParsedPermission.getMetaData();
    return permissionInfo;
  }
  
  public static ProviderInfo generateProviderInfo(ParsingPackageRead paramParsingPackageRead, ParsedProvider paramParsedProvider, int paramInt1, PackageUserState paramPackageUserState, int paramInt2) {
    return generateProviderInfo(paramParsingPackageRead, paramParsedProvider, paramInt1, paramPackageUserState, null, paramInt2);
  }
  
  public static ProviderInfo generateProviderInfo(ParsingPackageRead paramParsingPackageRead, ParsedProvider paramParsedProvider, int paramInt1, PackageUserState paramPackageUserState, ApplicationInfo paramApplicationInfo, int paramInt2) {
    if (paramParsedProvider == null)
      return null; 
    if (!checkUseInstalled(paramParsingPackageRead, paramPackageUserState, paramInt1))
      return null; 
    ApplicationInfo applicationInfo = paramApplicationInfo;
    if (paramApplicationInfo == null)
      applicationInfo = generateApplicationInfo(paramParsingPackageRead, paramInt1, paramPackageUserState, paramInt2); 
    return (applicationInfo == null) ? null : generateProviderInfoUnchecked(paramParsedProvider, paramInt1, applicationInfo);
  }
  
  public static ProviderInfo generateProviderInfoUnchecked(ParsedProvider paramParsedProvider, int paramInt, ApplicationInfo paramApplicationInfo) {
    ProviderInfo providerInfo = new ProviderInfo();
    assignSharedFieldsForComponentInfo((ComponentInfo)providerInfo, (ParsedMainComponent)paramParsedProvider);
    providerInfo.exported = paramParsedProvider.isExported();
    providerInfo.flags = paramParsedProvider.getFlags();
    providerInfo.processName = paramParsedProvider.getProcessName();
    providerInfo.authority = paramParsedProvider.getAuthority();
    providerInfo.isSyncable = paramParsedProvider.isSyncable();
    providerInfo.readPermission = paramParsedProvider.getReadPermission();
    providerInfo.writePermission = paramParsedProvider.getWritePermission();
    providerInfo.grantUriPermissions = paramParsedProvider.isGrantUriPermissions();
    providerInfo.forceUriPermissions = paramParsedProvider.isForceUriPermissions();
    providerInfo.multiprocess = paramParsedProvider.isMultiProcess();
    providerInfo.initOrder = paramParsedProvider.getInitOrder();
    providerInfo.uriPermissionPatterns = paramParsedProvider.getUriPermissionPatterns();
    providerInfo.pathPermissions = paramParsedProvider.getPathPermissions();
    providerInfo.metaData = paramParsedProvider.getMetaData();
    if ((paramInt & 0x800) == 0)
      providerInfo.uriPermissionPatterns = null; 
    providerInfo.applicationInfo = paramApplicationInfo;
    return providerInfo;
  }
  
  public static ServiceInfo generateServiceInfo(ParsingPackageRead paramParsingPackageRead, ParsedService paramParsedService, int paramInt1, PackageUserState paramPackageUserState, int paramInt2) {
    return generateServiceInfo(paramParsingPackageRead, paramParsedService, paramInt1, paramPackageUserState, null, paramInt2);
  }
  
  public static ServiceInfo generateServiceInfo(ParsingPackageRead paramParsingPackageRead, ParsedService paramParsedService, int paramInt1, PackageUserState paramPackageUserState, ApplicationInfo paramApplicationInfo, int paramInt2) {
    if (paramParsedService == null)
      return null; 
    if (!checkUseInstalled(paramParsingPackageRead, paramPackageUserState, paramInt1))
      return null; 
    ApplicationInfo applicationInfo = paramApplicationInfo;
    if (paramApplicationInfo == null)
      applicationInfo = generateApplicationInfo(paramParsingPackageRead, paramInt1, paramPackageUserState, paramInt2); 
    return (applicationInfo == null) ? null : generateServiceInfoUnchecked(paramParsedService, applicationInfo);
  }
  
  public static ServiceInfo generateServiceInfoUnchecked(ParsedService paramParsedService, ApplicationInfo paramApplicationInfo) {
    ServiceInfo serviceInfo = new ServiceInfo();
    assignSharedFieldsForComponentInfo((ComponentInfo)serviceInfo, (ParsedMainComponent)paramParsedService);
    serviceInfo.exported = paramParsedService.isExported();
    serviceInfo.flags = paramParsedService.getFlags();
    serviceInfo.metaData = paramParsedService.getMetaData();
    serviceInfo.permission = paramParsedService.getPermission();
    serviceInfo.processName = paramParsedService.getProcessName();
    serviceInfo.mForegroundServiceType = paramParsedService.getForegroundServiceType();
    serviceInfo.applicationInfo = paramApplicationInfo;
    return serviceInfo;
  }
  
  private static PackageInfo generateWithComponents(ParsingPackageRead paramParsingPackageRead, int[] paramArrayOfint, int paramInt1, long paramLong1, long paramLong2, Set<String> paramSet, PackageUserState paramPackageUserState, int paramInt2, ApexInfo paramApexInfo) {
    ApplicationInfo applicationInfo = generateApplicationInfo(paramParsingPackageRead, paramInt1, paramPackageUserState, paramInt2);
    if (applicationInfo == null)
      return null; 
    PackageInfo packageInfo = generateWithoutComponents(paramParsingPackageRead, paramArrayOfint, paramInt1, paramLong1, paramLong2, paramSet, paramPackageUserState, paramInt2, paramApexInfo, applicationInfo);
    if (packageInfo == null)
      return null; 
    if ((paramInt1 & 0x1) != 0) {
      int i = paramParsingPackageRead.getActivities().size();
      if (i > 0) {
        ActivityInfo[] arrayOfActivityInfo = new ActivityInfo[i];
        byte b1 = 0;
        for (byte b2 = 0; b2 < i; b2++) {
          ParsedActivity parsedActivity = paramParsingPackageRead.getActivities().get(b2);
          if (ComponentParseUtils.isMatch(paramPackageUserState, false, paramParsingPackageRead.isEnabled(), (ParsedMainComponent)parsedActivity, paramInt1) && !PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(parsedActivity.getName())) {
            arrayOfActivityInfo[b1] = generateActivityInfo(paramParsingPackageRead, parsedActivity, paramInt1, paramPackageUserState, applicationInfo, paramInt2);
            b1++;
          } 
        } 
        packageInfo.activities = (ActivityInfo[])ArrayUtils.trimToSize((Object[])arrayOfActivityInfo, b1);
      } 
    } 
    if ((paramInt1 & 0x2) != 0) {
      int i = paramParsingPackageRead.getReceivers().size();
      if (i > 0) {
        ActivityInfo[] arrayOfActivityInfo = new ActivityInfo[i];
        byte b1 = 0;
        for (byte b2 = 0; b2 < i; b2++) {
          ParsedActivity parsedActivity = paramParsingPackageRead.getReceivers().get(b2);
          if (ComponentParseUtils.isMatch(paramPackageUserState, false, paramParsingPackageRead.isEnabled(), (ParsedMainComponent)parsedActivity, paramInt1)) {
            arrayOfActivityInfo[b1] = generateActivityInfo(paramParsingPackageRead, parsedActivity, paramInt1, paramPackageUserState, applicationInfo, paramInt2);
            b1++;
          } 
        } 
        packageInfo.receivers = (ActivityInfo[])ArrayUtils.trimToSize((Object[])arrayOfActivityInfo, b1);
      } 
    } 
    if ((paramInt1 & 0x4) != 0) {
      int i = paramParsingPackageRead.getServices().size();
      if (i > 0) {
        ServiceInfo[] arrayOfServiceInfo = new ServiceInfo[i];
        byte b1 = 0;
        for (byte b2 = 0; b2 < i; b2++) {
          ParsedService parsedService = paramParsingPackageRead.getServices().get(b2);
          if (ComponentParseUtils.isMatch(paramPackageUserState, false, paramParsingPackageRead.isEnabled(), (ParsedMainComponent)parsedService, paramInt1)) {
            arrayOfServiceInfo[b1] = generateServiceInfo(paramParsingPackageRead, parsedService, paramInt1, paramPackageUserState, applicationInfo, paramInt2);
            b1++;
          } 
        } 
        packageInfo.services = (ServiceInfo[])ArrayUtils.trimToSize((Object[])arrayOfServiceInfo, b1);
      } 
    } 
    if ((paramInt1 & 0x8) != 0) {
      int i = paramParsingPackageRead.getProviders().size();
      if (i > 0) {
        ProviderInfo[] arrayOfProviderInfo = new ProviderInfo[i];
        byte b1 = 0;
        for (byte b2 = 0; b2 < i; b2++) {
          ParsedProvider parsedProvider = paramParsingPackageRead.getProviders().get(b2);
          if (ComponentParseUtils.isMatch(paramPackageUserState, false, paramParsingPackageRead.isEnabled(), (ParsedMainComponent)parsedProvider, paramInt1)) {
            arrayOfProviderInfo[b1] = generateProviderInfo(paramParsingPackageRead, parsedProvider, paramInt1, paramPackageUserState, applicationInfo, paramInt2);
            b1++;
          } 
        } 
        packageInfo.providers = (ProviderInfo[])ArrayUtils.trimToSize((Object[])arrayOfProviderInfo, b1);
      } 
    } 
    if ((paramInt1 & 0x10) != 0) {
      int i = paramParsingPackageRead.getInstrumentations().size();
      if (i > 0) {
        packageInfo.instrumentation = new InstrumentationInfo[i];
        for (byte b = 0; b < i; b++)
          packageInfo.instrumentation[b] = generateInstrumentationInfo(paramParsingPackageRead.getInstrumentations().get(b), paramParsingPackageRead, paramInt1, paramInt2); 
      } 
    } 
    return packageInfo;
  }
  
  public static PackageInfo generateWithoutComponents(ParsingPackageRead paramParsingPackageRead, int[] paramArrayOfint, int paramInt1, long paramLong1, long paramLong2, Set<String> paramSet, PackageUserState paramPackageUserState, int paramInt2, ApexInfo paramApexInfo, ApplicationInfo paramApplicationInfo) {
    return !checkUseInstalled(paramParsingPackageRead, paramPackageUserState, paramInt1) ? null : generateWithoutComponentsUnchecked(paramParsingPackageRead, paramArrayOfint, paramInt1, paramLong1, paramLong2, paramSet, paramPackageUserState, paramInt2, paramApexInfo, paramApplicationInfo);
  }
  
  public static PackageInfo generateWithoutComponentsUnchecked(ParsingPackageRead paramParsingPackageRead, int[] paramArrayOfint, int paramInt1, long paramLong1, long paramLong2, Set<String> paramSet, PackageUserState paramPackageUserState, int paramInt2, ApexInfo paramApexInfo, ApplicationInfo paramApplicationInfo) {
    PackageInfo packageInfo = new PackageInfo();
    packageInfo.packageName = paramParsingPackageRead.getPackageName();
    packageInfo.splitNames = paramParsingPackageRead.getSplitNames();
    packageInfo.versionCode = paramParsingPackageRead.getVersionCode();
    packageInfo.versionCodeMajor = paramParsingPackageRead.getVersionCodeMajor();
    packageInfo.baseRevisionCode = paramParsingPackageRead.getBaseRevisionCode();
    packageInfo.splitRevisionCodes = paramParsingPackageRead.getSplitRevisionCodes();
    packageInfo.versionName = paramParsingPackageRead.getVersionName();
    packageInfo.sharedUserId = paramParsingPackageRead.getSharedUserId();
    packageInfo.sharedUserLabel = paramParsingPackageRead.getSharedUserLabel();
    packageInfo.applicationInfo = paramApplicationInfo;
    packageInfo.installLocation = paramParsingPackageRead.getInstallLocation();
    if ((packageInfo.applicationInfo.flags & 0x1) != 0 || (packageInfo.applicationInfo.flags & 0x80) != 0)
      packageInfo.requiredForAllUsers = paramParsingPackageRead.isRequiredForAllUsers(); 
    packageInfo.restrictedAccountType = paramParsingPackageRead.getRestrictedAccountType();
    packageInfo.requiredAccountType = paramParsingPackageRead.getRequiredAccountType();
    packageInfo.overlayTarget = paramParsingPackageRead.getOverlayTarget();
    packageInfo.targetOverlayableName = paramParsingPackageRead.getOverlayTargetName();
    packageInfo.overlayCategory = paramParsingPackageRead.getOverlayCategory();
    packageInfo.overlayPriority = paramParsingPackageRead.getOverlayPriority();
    packageInfo.mOverlayIsStatic = paramParsingPackageRead.isOverlayIsStatic();
    packageInfo.compileSdkVersion = paramParsingPackageRead.getCompileSdkVersion();
    packageInfo.compileSdkVersionCodename = paramParsingPackageRead.getCompileSdkVersionCodeName();
    packageInfo.firstInstallTime = paramLong1;
    packageInfo.lastUpdateTime = paramLong2;
    if ((paramInt1 & 0x100) != 0)
      packageInfo.gids = paramArrayOfint; 
    if ((paramInt1 & 0x4000) != 0) {
      paramInt2 = paramParsingPackageRead.getConfigPreferences().size();
      if (paramInt2 > 0) {
        packageInfo.configPreferences = new android.content.pm.ConfigurationInfo[paramInt2];
        paramParsingPackageRead.getConfigPreferences().toArray(packageInfo.configPreferences);
      } 
      paramInt2 = paramParsingPackageRead.getReqFeatures().size();
      if (paramInt2 > 0) {
        packageInfo.reqFeatures = new android.content.pm.FeatureInfo[paramInt2];
        paramParsingPackageRead.getReqFeatures().toArray(packageInfo.reqFeatures);
      } 
      paramInt2 = paramParsingPackageRead.getFeatureGroups().size();
      if (paramInt2 > 0) {
        packageInfo.featureGroups = new android.content.pm.FeatureGroupInfo[paramInt2];
        paramParsingPackageRead.getFeatureGroups().toArray(packageInfo.featureGroups);
      } 
    } 
    if ((paramInt1 & 0x1000) != 0) {
      int i = ArrayUtils.size(paramParsingPackageRead.getPermissions());
      if (i > 0) {
        packageInfo.permissions = new PermissionInfo[i];
        for (paramInt2 = 0; paramInt2 < i; paramInt2++)
          packageInfo.permissions[paramInt2] = generatePermissionInfo(paramParsingPackageRead.getPermissions().get(paramInt2), paramInt1); 
      } 
      i = paramParsingPackageRead.getRequestedPermissions().size();
      if (i > 0) {
        packageInfo.requestedPermissions = new String[i];
        packageInfo.requestedPermissionsFlags = new int[i];
        for (paramInt2 = 0; paramInt2 < i; paramInt2++) {
          String str = paramParsingPackageRead.getRequestedPermissions().get(paramInt2);
          packageInfo.requestedPermissions[paramInt2] = str;
          paramArrayOfint = packageInfo.requestedPermissionsFlags;
          paramArrayOfint[paramInt2] = paramArrayOfint[paramInt2] | 0x1;
          if (paramSet != null && paramSet.contains(str)) {
            paramArrayOfint = packageInfo.requestedPermissionsFlags;
            paramArrayOfint[paramInt2] = paramArrayOfint[paramInt2] | 0x2;
          } 
        } 
      } 
    } 
    if (paramApexInfo != null) {
      File file = new File(paramApexInfo.modulePath);
      packageInfo.applicationInfo.sourceDir = file.getPath();
      packageInfo.applicationInfo.publicSourceDir = file.getPath();
      if (paramApexInfo.isFactory) {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        applicationInfo.flags |= 0x1;
      } else {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        applicationInfo.flags &= 0xFFFFFFFE;
      } 
      if (paramApexInfo.isActive) {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        applicationInfo.flags |= 0x800000;
      } else {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        applicationInfo.flags &= 0xFF7FFFFF;
      } 
      packageInfo.isApex = true;
    } 
    PackageParser.SigningDetails signingDetails = paramParsingPackageRead.getSigningDetails();
    if ((paramInt1 & 0x40) != 0)
      if (signingDetails.hasPastSigningCertificates()) {
        packageInfo.signatures = new android.content.pm.Signature[1];
        packageInfo.signatures[0] = signingDetails.pastSigningCertificates[0];
      } else if (signingDetails.hasSignatures()) {
        paramInt2 = signingDetails.signatures.length;
        packageInfo.signatures = new android.content.pm.Signature[paramInt2];
        System.arraycopy(signingDetails.signatures, 0, packageInfo.signatures, 0, paramInt2);
      }  
    if ((0x8000000 & paramInt1) != 0)
      if (signingDetails != PackageParser.SigningDetails.UNKNOWN) {
        packageInfo.signingInfo = new SigningInfo(signingDetails);
      } else {
        packageInfo.signingInfo = null;
      }  
    return packageInfo;
  }
  
  public static File getCredentialProtectedDataDir(ParsingPackageRead paramParsingPackageRead, int paramInt) {
    return Environment.getDataUserCePackageDirectory(paramParsingPackageRead.getVolumeUuid(), paramInt, paramParsingPackageRead.getPackageName());
  }
  
  public static File getDataDir(ParsingPackageRead paramParsingPackageRead, int paramInt) {
    return "android".equals(paramParsingPackageRead.getPackageName()) ? Environment.getDataSystemDirectory() : (paramParsingPackageRead.isDefaultToDeviceProtectedStorage() ? getDeviceProtectedDataDir(paramParsingPackageRead, paramInt) : getCredentialProtectedDataDir(paramParsingPackageRead, paramInt));
  }
  
  public static File getDeviceProtectedDataDir(ParsingPackageRead paramParsingPackageRead, int paramInt) {
    return Environment.getDataUserDePackageDirectory(paramParsingPackageRead.getVolumeUuid(), paramInt, paramParsingPackageRead.getPackageName());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/PackageInfoWithoutStateUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
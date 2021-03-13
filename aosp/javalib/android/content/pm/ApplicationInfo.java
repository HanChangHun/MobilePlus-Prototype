package android.content.pm;

import android.annotation.SystemApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.util.Printer;
import android.util.SparseArray;
import android.util.proto.ProtoOutputStream;
import com.android.internal.util.ArrayUtils;
import com.android.server.SystemConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ApplicationInfo extends PackageItemInfo implements Parcelable {
  public static final int AUTO_REVOKE_ALLOWED = 0;
  
  public static final int AUTO_REVOKE_DISALLOWED = 2;
  
  public static final int AUTO_REVOKE_DISCOURAGED = 1;
  
  public static final int CATEGORY_AUDIO = 1;
  
  public static final int CATEGORY_GAME = 0;
  
  public static final int CATEGORY_IMAGE = 3;
  
  public static final int CATEGORY_MAPS = 6;
  
  public static final int CATEGORY_NEWS = 5;
  
  public static final int CATEGORY_PRODUCTIVITY = 7;
  
  public static final int CATEGORY_SOCIAL = 4;
  
  public static final int CATEGORY_UNDEFINED = -1;
  
  public static final int CATEGORY_VIDEO = 2;
  
  public static final Parcelable.Creator<ApplicationInfo> CREATOR = new Parcelable.Creator<ApplicationInfo>() {
      public ApplicationInfo createFromParcel(Parcel param1Parcel) {
        return (ApplicationInfo)param1Parcel.readSquashed((Parcel.SquashReadHelper)_$$Lambda$ApplicationInfo$1$FDtFc_prTtONpy6YSScuAiML69E.INSTANCE);
      }
      
      public ApplicationInfo[] newArray(int param1Int) {
        return new ApplicationInfo[param1Int];
      }
    };
  
  public static final int FLAG_ALLOW_BACKUP = 32768;
  
  public static final int FLAG_ALLOW_CLEAR_USER_DATA = 64;
  
  public static final int FLAG_ALLOW_TASK_REPARENTING = 32;
  
  public static final int FLAG_DEBUGGABLE = 2;
  
  public static final int FLAG_EXTERNAL_STORAGE = 262144;
  
  public static final int FLAG_EXTRACT_NATIVE_LIBS = 268435456;
  
  public static final int FLAG_FACTORY_TEST = 16;
  
  public static final int FLAG_FULL_BACKUP_ONLY = 67108864;
  
  public static final int FLAG_HARDWARE_ACCELERATED = 536870912;
  
  public static final int FLAG_HAS_CODE = 4;
  
  public static final int FLAG_INSTALLED = 8388608;
  
  public static final int FLAG_IS_DATA_ONLY = 16777216;
  
  @Deprecated
  public static final int FLAG_IS_GAME = 33554432;
  
  public static final int FLAG_KILL_AFTER_RESTORE = 65536;
  
  public static final int FLAG_LARGE_HEAP = 1048576;
  
  public static final int FLAG_MULTIARCH = -2147483648;
  
  public static final int FLAG_PERSISTENT = 8;
  
  public static final int FLAG_RESIZEABLE_FOR_SCREENS = 4096;
  
  public static final int FLAG_RESTORE_ANY_VERSION = 131072;
  
  public static final int FLAG_STOPPED = 2097152;
  
  public static final int FLAG_SUPPORTS_LARGE_SCREENS = 2048;
  
  public static final int FLAG_SUPPORTS_NORMAL_SCREENS = 1024;
  
  public static final int FLAG_SUPPORTS_RTL = 4194304;
  
  @Deprecated
  public static final int FLAG_SUPPORTS_SCREEN_DENSITIES = 8192;
  
  public static final int FLAG_SUPPORTS_SMALL_SCREENS = 512;
  
  public static final int FLAG_SUPPORTS_XLARGE_SCREENS = 524288;
  
  public static final int FLAG_SUSPENDED = 1073741824;
  
  public static final int FLAG_SYSTEM = 1;
  
  public static final int FLAG_TEST_ONLY = 256;
  
  public static final int FLAG_UPDATED_SYSTEM_APP = 128;
  
  public static final int FLAG_USES_CLEARTEXT_TRAFFIC = 134217728;
  
  public static final int FLAG_VM_SAFE_MODE = 16384;
  
  public static final int GWP_ASAN_ALWAYS = 1;
  
  public static final int GWP_ASAN_DEFAULT = -1;
  
  public static final int GWP_ASAN_NEVER = 0;
  
  public static final int HIDDEN_API_ENFORCEMENT_DEFAULT = -1;
  
  public static final int HIDDEN_API_ENFORCEMENT_DISABLED = 0;
  
  public static final int HIDDEN_API_ENFORCEMENT_ENABLED = 2;
  
  public static final int HIDDEN_API_ENFORCEMENT_JUST_WARN = 1;
  
  private static final int HIDDEN_API_ENFORCEMENT_MAX = 2;
  
  private static final int HIDDEN_API_ENFORCEMENT_MIN = -1;
  
  public static final String METADATA_PRELOADED_FONTS = "preloaded_fonts";
  
  public static final int PRIVATE_FLAG_ACTIVITIES_RESIZE_MODE_RESIZEABLE = 1024;
  
  public static final int PRIVATE_FLAG_ACTIVITIES_RESIZE_MODE_RESIZEABLE_VIA_SDK_VERSION = 4096;
  
  public static final int PRIVATE_FLAG_ACTIVITIES_RESIZE_MODE_UNRESIZEABLE = 2048;
  
  public static final int PRIVATE_FLAG_ALLOW_AUDIO_PLAYBACK_CAPTURE = 134217728;
  
  public static final int PRIVATE_FLAG_ALLOW_CLEAR_USER_DATA_ON_FAILED_RESTORE = 67108864;
  
  public static final int PRIVATE_FLAG_ALLOW_NATIVE_HEAP_POINTER_TAGGING = -2147483648;
  
  public static final int PRIVATE_FLAG_BACKUP_IN_FOREGROUND = 8192;
  
  public static final int PRIVATE_FLAG_CANT_SAVE_STATE = 2;
  
  public static final int PRIVATE_FLAG_DEFAULT_TO_DEVICE_PROTECTED_STORAGE = 32;
  
  public static final int PRIVATE_FLAG_DIRECT_BOOT_AWARE = 64;
  
  public static final int PRIVATE_FLAG_HAS_DOMAIN_URLS = 16;
  
  public static final int PRIVATE_FLAG_HAS_FRAGILE_USER_DATA = 16777216;
  
  public static final int PRIVATE_FLAG_HIDDEN = 1;
  
  public static final int PRIVATE_FLAG_INSTANT = 128;
  
  public static final int PRIVATE_FLAG_ISOLATED_SPLIT_LOADING = 32768;
  
  public static final int PRIVATE_FLAG_IS_RESOURCE_OVERLAY = 268435456;
  
  public static final int PRIVATE_FLAG_ODM = 1073741824;
  
  public static final int PRIVATE_FLAG_OEM = 131072;
  
  public static final int PRIVATE_FLAG_PARTIALLY_DIRECT_BOOT_AWARE = 256;
  
  public static final int PRIVATE_FLAG_PRIVILEGED = 8;
  
  public static final int PRIVATE_FLAG_PRODUCT = 524288;
  
  public static final int PRIVATE_FLAG_PROFILEABLE_BY_SHELL = 8388608;
  
  public static final int PRIVATE_FLAG_REQUEST_LEGACY_EXTERNAL_STORAGE = 536870912;
  
  public static final int PRIVATE_FLAG_REQUIRED_FOR_SYSTEM_USER = 512;
  
  public static final int PRIVATE_FLAG_SIGNED_WITH_PLATFORM_KEY = 1048576;
  
  public static final int PRIVATE_FLAG_STATIC_SHARED_LIBRARY = 16384;
  
  public static final int PRIVATE_FLAG_SYSTEM_EXT = 2097152;
  
  public static final int PRIVATE_FLAG_USES_NON_SDK_API = 4194304;
  
  public static final int PRIVATE_FLAG_USE_EMBEDDED_DEX = 33554432;
  
  public static final int PRIVATE_FLAG_VENDOR = 262144;
  
  public static final int PRIVATE_FLAG_VIRTUAL_PRELOAD = 65536;
  
  public String appComponentFactory;
  
  public String backupAgentName;
  
  public int category;
  
  public String classLoaderName;
  
  public String className;
  
  public int compatibleWidthLimitDp;
  
  public int compileSdkVersion;
  
  public String compileSdkVersionCodename;
  
  @SystemApi
  public String credentialProtectedDataDir;
  
  public boolean crossProfile;
  
  public String dataDir;
  
  public int descriptionRes;
  
  public String deviceProtectedDataDir;
  
  public boolean enabled;
  
  public int enabledSetting;
  
  public int flags;
  
  public int fullBackupContent;
  
  private int gwpAsanMode;
  
  public boolean hiddenUntilInstalled;
  
  public int iconRes;
  
  public int installLocation;
  
  public int largestWidthLimitDp;
  
  public long longVersionCode;
  
  private int mHiddenApiPolicy;
  
  public String manageSpaceActivityName;
  
  public float maxAspectRatio;
  
  public float minAspectRatio;
  
  public int minSdkVersion;
  
  public String nativeLibraryDir;
  
  public String nativeLibraryRootDir;
  
  public boolean nativeLibraryRootRequiresIsa;
  
  public int networkSecurityConfigRes;
  
  public String permission;
  
  public String primaryCpuAbi;
  
  public int privateFlags;
  
  public String processName;
  
  public String publicSourceDir;
  
  public int requiresSmallestWidthDp;
  
  public String[] resourceDirs;
  
  public int roundIconRes;
  
  public String scanPublicSourceDir;
  
  public String scanSourceDir;
  
  public String seInfo;
  
  public String seInfoUser;
  
  public String secondaryCpuAbi;
  
  public String secondaryNativeLibraryDir;
  
  public String[] sharedLibraryFiles;
  
  public List<SharedLibraryInfo> sharedLibraryInfos;
  
  public String sourceDir;
  
  public String[] splitClassLoaderNames;
  
  public SparseArray<int[]> splitDependencies;
  
  public String[] splitNames;
  
  public String[] splitPublicSourceDirs;
  
  public String[] splitSourceDirs;
  
  public UUID storageUuid;
  
  @SystemApi
  public int targetSandboxVersion;
  
  public int targetSdkVersion;
  
  public String taskAffinity;
  
  public int theme;
  
  public int uiOptions;
  
  public int uid;
  
  @Deprecated
  public int versionCode;
  
  public String volumeUuid;
  
  public String zygotePreloadName;
  
  public ApplicationInfo() {
    this.fullBackupContent = 0;
    this.uiOptions = 0;
    this.flags = 0;
    this.requiresSmallestWidthDp = 0;
    this.compatibleWidthLimitDp = 0;
    this.largestWidthLimitDp = 0;
    this.enabled = true;
    this.enabledSetting = 0;
    this.installLocation = -1;
    this.category = -1;
    this.mHiddenApiPolicy = -1;
  }
  
  public ApplicationInfo(ApplicationInfo paramApplicationInfo) {
    super(paramApplicationInfo);
    this.fullBackupContent = 0;
    this.uiOptions = 0;
    this.flags = 0;
    this.requiresSmallestWidthDp = 0;
    this.compatibleWidthLimitDp = 0;
    this.largestWidthLimitDp = 0;
    this.enabled = true;
    this.enabledSetting = 0;
    this.installLocation = -1;
    this.category = -1;
    this.mHiddenApiPolicy = -1;
    this.taskAffinity = paramApplicationInfo.taskAffinity;
    this.permission = paramApplicationInfo.permission;
    this.processName = paramApplicationInfo.processName;
    this.className = paramApplicationInfo.className;
    this.theme = paramApplicationInfo.theme;
    this.flags = paramApplicationInfo.flags;
    this.privateFlags = paramApplicationInfo.privateFlags;
    this.requiresSmallestWidthDp = paramApplicationInfo.requiresSmallestWidthDp;
    this.compatibleWidthLimitDp = paramApplicationInfo.compatibleWidthLimitDp;
    this.largestWidthLimitDp = paramApplicationInfo.largestWidthLimitDp;
    this.volumeUuid = paramApplicationInfo.volumeUuid;
    this.storageUuid = paramApplicationInfo.storageUuid;
    this.scanSourceDir = paramApplicationInfo.scanSourceDir;
    this.scanPublicSourceDir = paramApplicationInfo.scanPublicSourceDir;
    this.sourceDir = paramApplicationInfo.sourceDir;
    this.publicSourceDir = paramApplicationInfo.publicSourceDir;
    this.splitNames = paramApplicationInfo.splitNames;
    this.splitSourceDirs = paramApplicationInfo.splitSourceDirs;
    this.splitPublicSourceDirs = paramApplicationInfo.splitPublicSourceDirs;
    this.splitDependencies = paramApplicationInfo.splitDependencies;
    this.nativeLibraryDir = paramApplicationInfo.nativeLibraryDir;
    this.secondaryNativeLibraryDir = paramApplicationInfo.secondaryNativeLibraryDir;
    this.nativeLibraryRootDir = paramApplicationInfo.nativeLibraryRootDir;
    this.nativeLibraryRootRequiresIsa = paramApplicationInfo.nativeLibraryRootRequiresIsa;
    this.primaryCpuAbi = paramApplicationInfo.primaryCpuAbi;
    this.secondaryCpuAbi = paramApplicationInfo.secondaryCpuAbi;
    this.resourceDirs = paramApplicationInfo.resourceDirs;
    this.seInfo = paramApplicationInfo.seInfo;
    this.seInfoUser = paramApplicationInfo.seInfoUser;
    this.sharedLibraryFiles = paramApplicationInfo.sharedLibraryFiles;
    this.sharedLibraryInfos = paramApplicationInfo.sharedLibraryInfos;
    this.dataDir = paramApplicationInfo.dataDir;
    this.deviceProtectedDataDir = paramApplicationInfo.deviceProtectedDataDir;
    this.credentialProtectedDataDir = paramApplicationInfo.credentialProtectedDataDir;
    this.uid = paramApplicationInfo.uid;
    this.minSdkVersion = paramApplicationInfo.minSdkVersion;
    this.targetSdkVersion = paramApplicationInfo.targetSdkVersion;
    setVersionCode(paramApplicationInfo.longVersionCode);
    this.enabled = paramApplicationInfo.enabled;
    this.enabledSetting = paramApplicationInfo.enabledSetting;
    this.installLocation = paramApplicationInfo.installLocation;
    this.manageSpaceActivityName = paramApplicationInfo.manageSpaceActivityName;
    this.descriptionRes = paramApplicationInfo.descriptionRes;
    this.uiOptions = paramApplicationInfo.uiOptions;
    this.backupAgentName = paramApplicationInfo.backupAgentName;
    this.fullBackupContent = paramApplicationInfo.fullBackupContent;
    this.crossProfile = paramApplicationInfo.crossProfile;
    this.networkSecurityConfigRes = paramApplicationInfo.networkSecurityConfigRes;
    this.category = paramApplicationInfo.category;
    this.targetSandboxVersion = paramApplicationInfo.targetSandboxVersion;
    this.classLoaderName = paramApplicationInfo.classLoaderName;
    this.splitClassLoaderNames = paramApplicationInfo.splitClassLoaderNames;
    this.appComponentFactory = paramApplicationInfo.appComponentFactory;
    this.iconRes = paramApplicationInfo.iconRes;
    this.roundIconRes = paramApplicationInfo.roundIconRes;
    this.compileSdkVersion = paramApplicationInfo.compileSdkVersion;
    this.compileSdkVersionCodename = paramApplicationInfo.compileSdkVersionCodename;
    this.mHiddenApiPolicy = paramApplicationInfo.mHiddenApiPolicy;
    this.hiddenUntilInstalled = paramApplicationInfo.hiddenUntilInstalled;
    this.zygotePreloadName = paramApplicationInfo.zygotePreloadName;
    this.gwpAsanMode = paramApplicationInfo.gwpAsanMode;
  }
  
  private ApplicationInfo(Parcel paramParcel) {
    super(paramParcel);
    boolean bool1 = false;
    this.fullBackupContent = 0;
    this.uiOptions = 0;
    this.flags = 0;
    this.requiresSmallestWidthDp = 0;
    this.compatibleWidthLimitDp = 0;
    this.largestWidthLimitDp = 0;
    this.enabled = true;
    this.enabledSetting = 0;
    this.installLocation = -1;
    this.category = -1;
    this.mHiddenApiPolicy = -1;
    this.taskAffinity = paramParcel.readString8();
    this.permission = paramParcel.readString8();
    this.processName = paramParcel.readString8();
    this.className = paramParcel.readString8();
    this.theme = paramParcel.readInt();
    this.flags = paramParcel.readInt();
    this.privateFlags = paramParcel.readInt();
    this.requiresSmallestWidthDp = paramParcel.readInt();
    this.compatibleWidthLimitDp = paramParcel.readInt();
    this.largestWidthLimitDp = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      UUID uUID = new UUID(paramParcel.readLong(), paramParcel.readLong());
      this.storageUuid = uUID;
      this.volumeUuid = StorageManager.convert(uUID);
    } 
    this.scanSourceDir = paramParcel.readString8();
    this.scanPublicSourceDir = paramParcel.readString8();
    this.sourceDir = paramParcel.readString8();
    this.publicSourceDir = paramParcel.readString8();
    this.splitNames = paramParcel.createString8Array();
    this.splitSourceDirs = paramParcel.createString8Array();
    this.splitPublicSourceDirs = paramParcel.createString8Array();
    this.splitDependencies = paramParcel.readSparseArray(null);
    this.nativeLibraryDir = paramParcel.readString8();
    this.secondaryNativeLibraryDir = paramParcel.readString8();
    this.nativeLibraryRootDir = paramParcel.readString8();
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.nativeLibraryRootRequiresIsa = bool2;
    this.primaryCpuAbi = paramParcel.readString8();
    this.secondaryCpuAbi = paramParcel.readString8();
    this.resourceDirs = paramParcel.createString8Array();
    this.seInfo = paramParcel.readString8();
    this.seInfoUser = paramParcel.readString8();
    this.sharedLibraryFiles = paramParcel.createString8Array();
    this.sharedLibraryInfos = paramParcel.createTypedArrayList(SharedLibraryInfo.CREATOR);
    this.dataDir = paramParcel.readString8();
    this.deviceProtectedDataDir = paramParcel.readString8();
    this.credentialProtectedDataDir = paramParcel.readString8();
    this.uid = paramParcel.readInt();
    this.minSdkVersion = paramParcel.readInt();
    this.targetSdkVersion = paramParcel.readInt();
    setVersionCode(paramParcel.readLong());
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.enabled = bool2;
    this.enabledSetting = paramParcel.readInt();
    this.installLocation = paramParcel.readInt();
    this.manageSpaceActivityName = paramParcel.readString8();
    this.backupAgentName = paramParcel.readString8();
    this.descriptionRes = paramParcel.readInt();
    this.uiOptions = paramParcel.readInt();
    this.fullBackupContent = paramParcel.readInt();
    this.crossProfile = paramParcel.readBoolean();
    this.networkSecurityConfigRes = paramParcel.readInt();
    this.category = paramParcel.readInt();
    this.targetSandboxVersion = paramParcel.readInt();
    this.classLoaderName = paramParcel.readString8();
    this.splitClassLoaderNames = paramParcel.createString8Array();
    this.compileSdkVersion = paramParcel.readInt();
    this.compileSdkVersionCodename = paramParcel.readString8();
    this.appComponentFactory = paramParcel.readString8();
    this.iconRes = paramParcel.readInt();
    this.roundIconRes = paramParcel.readInt();
    this.mHiddenApiPolicy = paramParcel.readInt();
    boolean bool2 = bool1;
    if (paramParcel.readInt() != 0)
      bool2 = true; 
    this.hiddenUntilInstalled = bool2;
    this.zygotePreloadName = paramParcel.readString8();
    this.gwpAsanMode = paramParcel.readInt();
  }
  
  public static CharSequence getCategoryTitle(Context paramContext, int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 7:
        return paramContext.getText(17039650);
      case 6:
        return paramContext.getText(17039648);
      case 5:
        return paramContext.getText(17039649);
      case 4:
        return paramContext.getText(17039651);
      case 3:
        return paramContext.getText(17039647);
      case 2:
        return paramContext.getText(17039652);
      case 1:
        return paramContext.getText(17039645);
      case 0:
        break;
    } 
    return paramContext.getText(17039646);
  }
  
  private boolean isAllowedToUseHiddenApis() {
    boolean bool = isSignedWithPlatformKey();
    boolean bool1 = true;
    if (bool)
      return true; 
    if (isSystemApp() || isUpdatedSystemApp()) {
      bool = bool1;
      if (!usesNonSdkApi())
        if (isPackageWhitelistedForHiddenApis()) {
          bool = bool1;
        } else {
          bool = false;
        }  
      return bool;
    } 
    return false;
  }
  
  private boolean isPackageUnavailable(PackageManager paramPackageManager) {
    boolean bool = true;
    try {
      PackageInfo packageInfo = paramPackageManager.getPackageInfo(this.packageName, 0);
      if (packageInfo != null)
        bool = false; 
      return bool;
    } catch (NameNotFoundException nameNotFoundException) {
      return true;
    } 
  }
  
  private boolean isPackageWhitelistedForHiddenApis() {
    return SystemConfig.getInstance().getHiddenApiWhitelistedApps().contains(this.packageName);
  }
  
  public static boolean isValidHiddenApiEnforcementPolicy(int paramInt) {
    boolean bool;
    if (paramInt >= -1 && paramInt <= 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean allowsNativeHeapPointerTagging() {
    boolean bool;
    if ((this.privateFlags & Integer.MIN_VALUE) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void disableCompatibilityMode() {
    this.flags |= 0x83E00;
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    dump(paramPrinter, paramString, 3);
  }
  
  public void dump(Printer paramPrinter, String paramString, int paramInt) {
    dumpFront(paramPrinter, paramString);
    if ((paramInt & 0x1) != 0 && this.className != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("className=");
      stringBuilder1.append(this.className);
      paramPrinter.println(stringBuilder1.toString());
    } 
    if (this.permission != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("permission=");
      stringBuilder1.append(this.permission);
      paramPrinter.println(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("processName=");
    stringBuilder.append(this.processName);
    paramPrinter.println(stringBuilder.toString());
    if ((paramInt & 0x1) != 0) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("taskAffinity=");
      stringBuilder.append(this.taskAffinity);
      paramPrinter.println(stringBuilder.toString());
    } 
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("uid=");
    stringBuilder.append(this.uid);
    stringBuilder.append(" flags=0x");
    stringBuilder.append(Integer.toHexString(this.flags));
    stringBuilder.append(" privateFlags=0x");
    stringBuilder.append(Integer.toHexString(this.privateFlags));
    stringBuilder.append(" theme=0x");
    stringBuilder.append(Integer.toHexString(this.theme));
    paramPrinter.println(stringBuilder.toString());
    if ((paramInt & 0x1) != 0) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("requiresSmallestWidthDp=");
      stringBuilder.append(this.requiresSmallestWidthDp);
      stringBuilder.append(" compatibleWidthLimitDp=");
      stringBuilder.append(this.compatibleWidthLimitDp);
      stringBuilder.append(" largestWidthLimitDp=");
      stringBuilder.append(this.largestWidthLimitDp);
      paramPrinter.println(stringBuilder.toString());
    } 
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("sourceDir=");
    stringBuilder.append(this.sourceDir);
    paramPrinter.println(stringBuilder.toString());
    if (!Objects.equals(this.sourceDir, this.publicSourceDir)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("publicSourceDir=");
      stringBuilder.append(this.publicSourceDir);
      paramPrinter.println(stringBuilder.toString());
    } 
    if (!ArrayUtils.isEmpty((Object[])this.splitSourceDirs)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("splitSourceDirs=");
      stringBuilder.append(Arrays.toString((Object[])this.splitSourceDirs));
      paramPrinter.println(stringBuilder.toString());
    } 
    if (!ArrayUtils.isEmpty((Object[])this.splitPublicSourceDirs) && !Arrays.equals((Object[])this.splitSourceDirs, (Object[])this.splitPublicSourceDirs)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("splitPublicSourceDirs=");
      stringBuilder.append(Arrays.toString((Object[])this.splitPublicSourceDirs));
      paramPrinter.println(stringBuilder.toString());
    } 
    if (this.resourceDirs != null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("resourceDirs=");
      stringBuilder.append(Arrays.toString((Object[])this.resourceDirs));
      paramPrinter.println(stringBuilder.toString());
    } 
    if ((paramInt & 0x1) != 0 && this.seInfo != null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("seinfo=");
      stringBuilder.append(this.seInfo);
      paramPrinter.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("seinfoUser=");
      stringBuilder.append(this.seInfoUser);
      paramPrinter.println(stringBuilder.toString());
    } 
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("dataDir=");
    stringBuilder.append(this.dataDir);
    paramPrinter.println(stringBuilder.toString());
    if ((paramInt & 0x1) != 0) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("deviceProtectedDataDir=");
      stringBuilder.append(this.deviceProtectedDataDir);
      paramPrinter.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("credentialProtectedDataDir=");
      stringBuilder.append(this.credentialProtectedDataDir);
      paramPrinter.println(stringBuilder.toString());
      if (this.sharedLibraryFiles != null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append("sharedLibraryFiles=");
        stringBuilder.append(Arrays.toString((Object[])this.sharedLibraryFiles));
        paramPrinter.println(stringBuilder.toString());
      } 
    } 
    if (this.classLoaderName != null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("classLoaderName=");
      stringBuilder.append(this.classLoaderName);
      paramPrinter.println(stringBuilder.toString());
    } 
    if (!ArrayUtils.isEmpty((Object[])this.splitClassLoaderNames)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("splitClassLoaderNames=");
      stringBuilder.append(Arrays.toString((Object[])this.splitClassLoaderNames));
      paramPrinter.println(stringBuilder.toString());
    } 
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("enabled=");
    stringBuilder.append(this.enabled);
    stringBuilder.append(" minSdkVersion=");
    stringBuilder.append(this.minSdkVersion);
    stringBuilder.append(" targetSdkVersion=");
    stringBuilder.append(this.targetSdkVersion);
    stringBuilder.append(" versionCode=");
    stringBuilder.append(this.longVersionCode);
    stringBuilder.append(" targetSandboxVersion=");
    stringBuilder.append(this.targetSandboxVersion);
    paramPrinter.println(stringBuilder.toString());
    if ((paramInt & 0x1) != 0) {
      String str2;
      String str1;
      if (this.manageSpaceActivityName != null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append("manageSpaceActivityName=");
        stringBuilder.append(this.manageSpaceActivityName);
        paramPrinter.println(stringBuilder.toString());
      } 
      if (this.descriptionRes != 0) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append("description=0x");
        stringBuilder.append(Integer.toHexString(this.descriptionRes));
        paramPrinter.println(stringBuilder.toString());
      } 
      if (this.uiOptions != 0) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append("uiOptions=0x");
        stringBuilder.append(Integer.toHexString(this.uiOptions));
        paramPrinter.println(stringBuilder.toString());
      } 
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(paramString);
      stringBuilder2.append("supportsRtl=");
      boolean bool = hasRtlSupport();
      String str3 = "true";
      if (bool) {
        str2 = "true";
      } else {
        str2 = "false";
      } 
      stringBuilder2.append(str2);
      paramPrinter.println(stringBuilder2.toString());
      if (this.fullBackupContent > 0) {
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(paramString);
        stringBuilder3.append("fullBackupContent=@xml/");
        stringBuilder3.append(this.fullBackupContent);
        paramPrinter.println(stringBuilder3.toString());
      } else {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(paramString);
        stringBuilder2.append("fullBackupContent=");
        if (this.fullBackupContent < 0) {
          str2 = "false";
        } else {
          str2 = "true";
        } 
        stringBuilder2.append(str2);
        paramPrinter.println(stringBuilder2.toString());
      } 
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(paramString);
      stringBuilder2.append("crossProfile=");
      if (this.crossProfile) {
        str2 = "true";
      } else {
        str2 = "false";
      } 
      stringBuilder2.append(str2);
      paramPrinter.println(stringBuilder2.toString());
      if (this.networkSecurityConfigRes != 0) {
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(paramString);
        stringBuilder3.append("networkSecurityConfigRes=0x");
        stringBuilder3.append(Integer.toHexString(this.networkSecurityConfigRes));
        paramPrinter.println(stringBuilder3.toString());
      } 
      if (this.category != -1) {
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(paramString);
        stringBuilder3.append("category=");
        stringBuilder3.append(this.category);
        paramPrinter.println(stringBuilder3.toString());
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("HiddenApiEnforcementPolicy=");
      stringBuilder1.append(getHiddenApiEnforcementPolicy());
      paramPrinter.println(stringBuilder1.toString());
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("usesNonSdkApi=");
      stringBuilder1.append(usesNonSdkApi());
      paramPrinter.println(stringBuilder1.toString());
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(paramString);
      stringBuilder2.append("allowsPlaybackCapture=");
      if (isAudioPlaybackCaptureAllowed()) {
        str1 = str3;
      } else {
        str1 = "false";
      } 
      stringBuilder2.append(str1);
      paramPrinter.println(stringBuilder2.toString());
      if (this.gwpAsanMode != -1) {
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(paramString);
        stringBuilder3.append("gwpAsanMode=");
        stringBuilder3.append(this.gwpAsanMode);
        paramPrinter.println(stringBuilder3.toString());
      } 
    } 
    dumpBack(paramPrinter, paramString);
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong, int paramInt) {
    paramLong = paramProtoOutputStream.start(paramLong);
    super.dumpDebug(paramProtoOutputStream, 1146756268033L, paramInt);
    paramProtoOutputStream.write(1138166333442L, this.permission);
    paramProtoOutputStream.write(1138166333443L, this.processName);
    paramProtoOutputStream.write(1120986464260L, this.uid);
    paramProtoOutputStream.write(1120986464261L, this.flags);
    paramProtoOutputStream.write(1120986464262L, this.privateFlags);
    paramProtoOutputStream.write(1120986464263L, this.theme);
    paramProtoOutputStream.write(1138166333448L, this.sourceDir);
    if (!Objects.equals(this.sourceDir, this.publicSourceDir))
      paramProtoOutputStream.write(1138166333449L, this.publicSourceDir); 
    boolean bool = ArrayUtils.isEmpty((Object[])this.splitSourceDirs);
    boolean bool1 = false;
    if (!bool) {
      String[] arrayOfString1 = this.splitSourceDirs;
      int i = arrayOfString1.length;
      for (byte b = 0; b < i; b++)
        paramProtoOutputStream.write(2237677961226L, arrayOfString1[b]); 
    } 
    if (!ArrayUtils.isEmpty((Object[])this.splitPublicSourceDirs) && !Arrays.equals((Object[])this.splitSourceDirs, (Object[])this.splitPublicSourceDirs)) {
      String[] arrayOfString1 = this.splitPublicSourceDirs;
      int i = arrayOfString1.length;
      for (byte b = 0; b < i; b++)
        paramProtoOutputStream.write(2237677961227L, arrayOfString1[b]); 
    } 
    String[] arrayOfString = this.resourceDirs;
    if (arrayOfString != null) {
      int i = arrayOfString.length;
      for (byte b = 0; b < i; b++)
        paramProtoOutputStream.write(2237677961228L, arrayOfString[b]); 
    } 
    paramProtoOutputStream.write(1138166333453L, this.dataDir);
    paramProtoOutputStream.write(1138166333454L, this.classLoaderName);
    if (!ArrayUtils.isEmpty((Object[])this.splitClassLoaderNames)) {
      arrayOfString = this.splitClassLoaderNames;
      int i = arrayOfString.length;
      for (byte b = 0; b < i; b++)
        paramProtoOutputStream.write(2237677961231L, arrayOfString[b]); 
    } 
    long l = paramProtoOutputStream.start(1146756268048L);
    paramProtoOutputStream.write(1133871366145L, this.enabled);
    paramProtoOutputStream.write(1120986464258L, this.minSdkVersion);
    paramProtoOutputStream.write(1120986464259L, this.targetSdkVersion);
    paramProtoOutputStream.write(1120986464260L, this.longVersionCode);
    paramProtoOutputStream.write(1120986464261L, this.targetSandboxVersion);
    paramProtoOutputStream.end(l);
    if ((paramInt & 0x1) != 0) {
      l = paramProtoOutputStream.start(1146756268049L);
      String str2 = this.className;
      if (str2 != null)
        paramProtoOutputStream.write(1138166333441L, str2); 
      paramProtoOutputStream.write(1138166333442L, this.taskAffinity);
      paramProtoOutputStream.write(1120986464259L, this.requiresSmallestWidthDp);
      paramProtoOutputStream.write(1120986464260L, this.compatibleWidthLimitDp);
      paramProtoOutputStream.write(1120986464261L, this.largestWidthLimitDp);
      str2 = this.seInfo;
      if (str2 != null) {
        paramProtoOutputStream.write(1138166333446L, str2);
        paramProtoOutputStream.write(1138166333447L, this.seInfoUser);
      } 
      paramProtoOutputStream.write(1138166333448L, this.deviceProtectedDataDir);
      paramProtoOutputStream.write(1138166333449L, this.credentialProtectedDataDir);
      String[] arrayOfString1 = this.sharedLibraryFiles;
      if (arrayOfString1 != null) {
        int i = arrayOfString1.length;
        for (paramInt = 0; paramInt < i; paramInt++)
          paramProtoOutputStream.write(2237677961226L, arrayOfString1[paramInt]); 
      } 
      String str1 = this.manageSpaceActivityName;
      if (str1 != null)
        paramProtoOutputStream.write(1138166333451L, str1); 
      paramInt = this.descriptionRes;
      if (paramInt != 0)
        paramProtoOutputStream.write(1120986464268L, paramInt); 
      paramInt = this.uiOptions;
      if (paramInt != 0)
        paramProtoOutputStream.write(1120986464269L, paramInt); 
      paramProtoOutputStream.write(1133871366158L, hasRtlSupport());
      paramInt = this.fullBackupContent;
      if (paramInt > 0) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@xml/");
        stringBuilder.append(this.fullBackupContent);
        paramProtoOutputStream.write(1138166333455L, stringBuilder.toString());
      } else {
        if (paramInt == 0)
          bool1 = true; 
        paramProtoOutputStream.write(1133871366160L, bool1);
      } 
      paramInt = this.networkSecurityConfigRes;
      if (paramInt != 0)
        paramProtoOutputStream.write(1120986464273L, paramInt); 
      paramInt = this.category;
      if (paramInt != -1)
        paramProtoOutputStream.write(1120986464274L, paramInt); 
      paramInt = this.gwpAsanMode;
      if (paramInt != -1)
        paramProtoOutputStream.write(1120986464275L, paramInt); 
      paramProtoOutputStream.end(l);
    } 
    paramProtoOutputStream.end(paramLong);
  }
  
  public String[] getAllApkPaths() {
    String[][] arrayOfString = new String[3][];
    arrayOfString[0] = this.splitSourceDirs;
    arrayOfString[1] = this.sharedLibraryFiles;
    arrayOfString[2] = this.resourceDirs;
    ArrayList<String> arrayList = new ArrayList(10);
    String str = this.sourceDir;
    if (str != null)
      arrayList.add(str); 
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String[] arrayOfString1 = arrayOfString[b];
      if (arrayOfString1 != null) {
        int j = arrayOfString1.length;
        for (byte b1 = 0; b1 < j; b1++)
          arrayList.add(arrayOfString1[b1]); 
      } 
    } 
    return arrayList.<String>toArray(new String[arrayList.size()]);
  }
  
  protected ApplicationInfo getApplicationInfo() {
    return this;
  }
  
  public String getBaseCodePath() {
    return this.sourceDir;
  }
  
  public String getBaseResourcePath() {
    return this.publicSourceDir;
  }
  
  public String getCodePath() {
    return this.scanSourceDir;
  }
  
  public int getGwpAsanMode() {
    return this.gwpAsanMode;
  }
  
  public int getHiddenApiEnforcementPolicy() {
    if (isAllowedToUseHiddenApis())
      return 0; 
    int i = this.mHiddenApiPolicy;
    return (i != -1) ? i : 2;
  }
  
  public String getResourcePath() {
    return this.scanPublicSourceDir;
  }
  
  public String[] getSplitCodePaths() {
    return this.splitSourceDirs;
  }
  
  public String[] getSplitResourcePaths() {
    return this.splitPublicSourceDirs;
  }
  
  public boolean hasCode() {
    boolean bool;
    if ((this.flags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasFragileUserData() {
    boolean bool;
    if ((this.privateFlags & 0x1000000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasRequestedLegacyExternalStorage() {
    boolean bool;
    if ((this.privateFlags & 0x20000000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasRtlSupport() {
    boolean bool;
    if ((this.flags & 0x400000) == 4194304) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void initForUser(int paramInt) {
    this.uid = UserHandle.getUid(paramInt, UserHandle.getAppId(this.uid));
    if ("android".equals(this.packageName)) {
      this.dataDir = Environment.getDataSystemDirectory().getAbsolutePath();
      return;
    } 
    this.deviceProtectedDataDir = Environment.getDataUserDePackageDirectory(this.volumeUuid, paramInt, this.packageName).getAbsolutePath();
    String str = Environment.getDataUserCePackageDirectory(this.volumeUuid, paramInt, this.packageName).getAbsolutePath();
    this.credentialProtectedDataDir = str;
    if ((this.privateFlags & 0x20) != 0) {
      this.dataDir = this.deviceProtectedDataDir;
    } else {
      this.dataDir = str;
    } 
  }
  
  public boolean isAudioPlaybackCaptureAllowed() {
    boolean bool;
    if ((this.privateFlags & 0x8000000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isDefaultToDeviceProtectedStorage() {
    boolean bool;
    if ((this.privateFlags & 0x20) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isDirectBootAware() {
    boolean bool;
    if ((this.privateFlags & 0x40) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isEmbeddedDexUsed() {
    boolean bool;
    if ((this.privateFlags & 0x2000000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @SystemApi
  public boolean isEncryptionAware() {
    return (isDirectBootAware() || isPartiallyDirectBootAware());
  }
  
  public boolean isExternal() {
    boolean bool;
    if ((this.flags & 0x40000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @SystemApi
  public boolean isInstantApp() {
    boolean bool;
    if ((this.privateFlags & 0x80) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isInternal() {
    boolean bool;
    if ((this.flags & 0x40000) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isOdm() {
    boolean bool;
    if ((this.privateFlags & 0x40000000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isOem() {
    boolean bool;
    if ((this.privateFlags & 0x20000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isPartiallyDirectBootAware() {
    boolean bool;
    if ((this.privateFlags & 0x100) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isPrivilegedApp() {
    boolean bool;
    if ((this.privateFlags & 0x8) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isProduct() {
    boolean bool;
    if ((this.privateFlags & 0x80000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isProfileableByShell() {
    boolean bool;
    if ((this.privateFlags & 0x800000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isRequiredForSystemUser() {
    boolean bool;
    if ((this.privateFlags & 0x200) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isResourceOverlay() {
    boolean bool;
    if ((this.privateFlags & 0x10000000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isSignedWithPlatformKey() {
    boolean bool;
    if ((this.privateFlags & 0x100000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStaticSharedLibrary() {
    boolean bool;
    if ((this.privateFlags & 0x4000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isSystemApp() {
    int i = this.flags;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public boolean isSystemExt() {
    boolean bool;
    if ((this.privateFlags & 0x200000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isUpdatedSystemApp() {
    boolean bool;
    if ((this.flags & 0x80) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isVendor() {
    boolean bool;
    if ((this.privateFlags & 0x40000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isVirtualPreload() {
    boolean bool;
    if ((this.privateFlags & 0x10000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Drawable loadDefaultIcon(PackageManager paramPackageManager) {
    return ((this.flags & 0x40000) != 0 && isPackageUnavailable(paramPackageManager)) ? Resources.getSystem().getDrawable(17303652) : paramPackageManager.getDefaultActivityIcon();
  }
  
  public CharSequence loadDescription(PackageManager paramPackageManager) {
    if (this.descriptionRes != 0) {
      CharSequence charSequence = paramPackageManager.getText(this.packageName, this.descriptionRes, this);
      if (charSequence != null)
        return charSequence; 
    } 
    return null;
  }
  
  public void maybeUpdateHiddenApiEnforcementPolicy(int paramInt) {
    if (isPackageWhitelistedForHiddenApis())
      return; 
    setHiddenApiEnforcementPolicy(paramInt);
  }
  
  public boolean requestsIsolatedSplitLoading() {
    boolean bool;
    if ((this.privateFlags & 0x8000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setBaseCodePath(String paramString) {
    this.sourceDir = paramString;
  }
  
  public void setBaseResourcePath(String paramString) {
    this.publicSourceDir = paramString;
  }
  
  public void setCodePath(String paramString) {
    this.scanSourceDir = paramString;
  }
  
  public void setGwpAsanMode(int paramInt) {
    this.gwpAsanMode = paramInt;
  }
  
  public void setHiddenApiEnforcementPolicy(int paramInt) {
    if (isValidHiddenApiEnforcementPolicy(paramInt)) {
      this.mHiddenApiPolicy = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid API enforcement policy: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setResourcePath(String paramString) {
    this.scanPublicSourceDir = paramString;
  }
  
  public void setSplitCodePaths(String[] paramArrayOfString) {
    this.splitSourceDirs = paramArrayOfString;
  }
  
  public void setSplitResourcePaths(String[] paramArrayOfString) {
    this.splitPublicSourceDirs = paramArrayOfString;
  }
  
  public void setVersionCode(long paramLong) {
    this.longVersionCode = paramLong;
    this.versionCode = (int)paramLong;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ApplicationInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.packageName);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public boolean usesCompatibilityMode() {
    return (this.targetSdkVersion < 4 || (this.flags & 0x83E00) == 0);
  }
  
  public boolean usesNonSdkApi() {
    boolean bool;
    if ((this.privateFlags & 0x400000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (paramParcel.maybeWriteSquashed(this))
      return; 
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString8(this.taskAffinity);
    paramParcel.writeString8(this.permission);
    paramParcel.writeString8(this.processName);
    paramParcel.writeString8(this.className);
    paramParcel.writeInt(this.theme);
    paramParcel.writeInt(this.flags);
    paramParcel.writeInt(this.privateFlags);
    paramParcel.writeInt(this.requiresSmallestWidthDp);
    paramParcel.writeInt(this.compatibleWidthLimitDp);
    paramParcel.writeInt(this.largestWidthLimitDp);
    if (this.storageUuid != null) {
      paramParcel.writeInt(1);
      paramParcel.writeLong(this.storageUuid.getMostSignificantBits());
      paramParcel.writeLong(this.storageUuid.getLeastSignificantBits());
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeString8(this.scanSourceDir);
    paramParcel.writeString8(this.scanPublicSourceDir);
    paramParcel.writeString8(this.sourceDir);
    paramParcel.writeString8(this.publicSourceDir);
    paramParcel.writeString8Array(this.splitNames);
    paramParcel.writeString8Array(this.splitSourceDirs);
    paramParcel.writeString8Array(this.splitPublicSourceDirs);
    paramParcel.writeSparseArray(this.splitDependencies);
    paramParcel.writeString8(this.nativeLibraryDir);
    paramParcel.writeString8(this.secondaryNativeLibraryDir);
    paramParcel.writeString8(this.nativeLibraryRootDir);
    paramParcel.writeInt(this.nativeLibraryRootRequiresIsa);
    paramParcel.writeString8(this.primaryCpuAbi);
    paramParcel.writeString8(this.secondaryCpuAbi);
    paramParcel.writeString8Array(this.resourceDirs);
    paramParcel.writeString8(this.seInfo);
    paramParcel.writeString8(this.seInfoUser);
    paramParcel.writeString8Array(this.sharedLibraryFiles);
    paramParcel.writeTypedList(this.sharedLibraryInfos);
    paramParcel.writeString8(this.dataDir);
    paramParcel.writeString8(this.deviceProtectedDataDir);
    paramParcel.writeString8(this.credentialProtectedDataDir);
    paramParcel.writeInt(this.uid);
    paramParcel.writeInt(this.minSdkVersion);
    paramParcel.writeInt(this.targetSdkVersion);
    paramParcel.writeLong(this.longVersionCode);
    paramParcel.writeInt(this.enabled);
    paramParcel.writeInt(this.enabledSetting);
    paramParcel.writeInt(this.installLocation);
    paramParcel.writeString8(this.manageSpaceActivityName);
    paramParcel.writeString8(this.backupAgentName);
    paramParcel.writeInt(this.descriptionRes);
    paramParcel.writeInt(this.uiOptions);
    paramParcel.writeInt(this.fullBackupContent);
    paramParcel.writeBoolean(this.crossProfile);
    paramParcel.writeInt(this.networkSecurityConfigRes);
    paramParcel.writeInt(this.category);
    paramParcel.writeInt(this.targetSandboxVersion);
    paramParcel.writeString8(this.classLoaderName);
    paramParcel.writeString8Array(this.splitClassLoaderNames);
    paramParcel.writeInt(this.compileSdkVersion);
    paramParcel.writeString8(this.compileSdkVersionCodename);
    paramParcel.writeString8(this.appComponentFactory);
    paramParcel.writeInt(this.iconRes);
    paramParcel.writeInt(this.roundIconRes);
    paramParcel.writeInt(this.mHiddenApiPolicy);
    paramParcel.writeInt(this.hiddenUntilInstalled);
    paramParcel.writeString8(this.zygotePreloadName);
    paramParcel.writeInt(this.gwpAsanMode);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ApplicationInfoPrivateFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Category {}
  
  public static class DisplayNameComparator implements Comparator<ApplicationInfo> {
    private PackageManager mPM;
    
    private final Collator sCollator = Collator.getInstance();
    
    public DisplayNameComparator(PackageManager param1PackageManager) {
      this.mPM = param1PackageManager;
    }
    
    public final int compare(ApplicationInfo param1ApplicationInfo1, ApplicationInfo param1ApplicationInfo2) {
      CharSequence charSequence2 = this.mPM.getApplicationLabel(param1ApplicationInfo1);
      CharSequence charSequence3 = charSequence2;
      if (charSequence2 == null)
        charSequence3 = param1ApplicationInfo1.packageName; 
      charSequence2 = this.mPM.getApplicationLabel(param1ApplicationInfo2);
      CharSequence charSequence1 = charSequence2;
      if (charSequence2 == null)
        charSequence1 = param1ApplicationInfo2.packageName; 
      return this.sCollator.compare(charSequence3.toString(), charSequence1.toString());
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface GwpAsanMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface HiddenApiEnforcementPolicy {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ApplicationInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
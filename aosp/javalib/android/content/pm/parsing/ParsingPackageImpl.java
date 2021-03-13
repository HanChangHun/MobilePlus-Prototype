package android.content.pm.parsing;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureGroupInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageParser;
import android.content.pm.parsing.component.ParsedActivity;
import android.content.pm.parsing.component.ParsedAttribution;
import android.content.pm.parsing.component.ParsedComponent;
import android.content.pm.parsing.component.ParsedInstrumentation;
import android.content.pm.parsing.component.ParsedIntentInfo;
import android.content.pm.parsing.component.ParsedMainComponent;
import android.content.pm.parsing.component.ParsedPermission;
import android.content.pm.parsing.component.ParsedPermissionGroup;
import android.content.pm.parsing.component.ParsedProcess;
import android.content.pm.parsing.component.ParsedProvider;
import android.content.pm.parsing.component.ParsedService;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.CollectionUtils;
import com.android.internal.util.Parcelling;
import java.security.PublicKey;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParsingPackageImpl implements ParsingPackage, Parcelable {
  public static final Parcelable.Creator<ParsingPackageImpl> CREATOR;
  
  private static final Comparator<ParsedMainComponent> ORDER_COMPARATOR;
  
  private static final String TAG = "PackageImpl";
  
  public static Parcelling.BuiltIn.ForBoolean sForBoolean = (Parcelling.BuiltIn.ForBoolean)Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForBoolean.class);
  
  protected static ParsedIntentInfo.StringPairListParceler sForIntentInfoPairs;
  
  public static Parcelling.BuiltIn.ForInternedString sForInternedString = (Parcelling.BuiltIn.ForInternedString)Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForInternedString.class);
  
  public static Parcelling.BuiltIn.ForInternedStringArray sForInternedStringArray = (Parcelling.BuiltIn.ForInternedStringArray)Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForInternedStringArray.class);
  
  public static Parcelling.BuiltIn.ForInternedStringList sForInternedStringList = (Parcelling.BuiltIn.ForInternedStringList)Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForInternedStringList.class);
  
  public static Parcelling.BuiltIn.ForInternedStringValueMap sForInternedStringValueMap = (Parcelling.BuiltIn.ForInternedStringValueMap)Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForInternedStringValueMap.class);
  
  public static Parcelling.BuiltIn.ForStringSet sForStringSet = (Parcelling.BuiltIn.ForStringSet)Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForStringSet.class);
  
  protected List<ParsedActivity> activities = Collections.emptyList();
  
  protected List<String> adoptPermissions = Collections.emptyList();
  
  private boolean allowAudioPlaybackCapture;
  
  private boolean allowBackup;
  
  private boolean allowClearUserData;
  
  private boolean allowClearUserDataOnFailedRestore;
  
  private boolean allowNativeHeapPointerTagging;
  
  private boolean allowTaskReparenting;
  
  private Boolean anyDensity;
  
  private String appComponentFactory;
  
  private List<ParsedAttribution> attributions = Collections.emptyList();
  
  private int autoRevokePermissions;
  
  private String backupAgentName;
  
  private boolean backupInForeground;
  
  private int banner;
  
  protected String baseCodePath;
  
  private boolean baseHardwareAccelerated;
  
  private int baseRevisionCode;
  
  private boolean cantSaveState;
  
  private int category;
  
  private String classLoaderName;
  
  private String className;
  
  protected String codePath;
  
  private int compatibleWidthLimitDp;
  
  private int compileSdkVersion;
  
  private String compileSdkVersionCodeName;
  
  private List<ConfigurationInfo> configPreferences = Collections.emptyList();
  
  private boolean crossProfile;
  
  private boolean debuggable;
  
  private boolean defaultToDeviceProtectedStorage;
  
  private int descriptionRes;
  
  private boolean directBootAware;
  
  private boolean enabled = true;
  
  private boolean externalStorage;
  
  private boolean extractNativeLibs;
  
  private List<FeatureGroupInfo> featureGroups = Collections.emptyList();
  
  private boolean forceQueryable;
  
  private int fullBackupContent;
  
  private boolean fullBackupOnly;
  
  private boolean game;
  
  protected int gwpAsanMode;
  
  private boolean hasCode;
  
  private boolean hasDomainUrls;
  
  private boolean hasFragileUserData;
  
  private int iconRes;
  
  private List<String> implicitPermissions = Collections.emptyList();
  
  private int installLocation = -1;
  
  protected List<ParsedInstrumentation> instrumentations = Collections.emptyList();
  
  private boolean isolatedSplitLoading;
  
  private Map<String, ArraySet<PublicKey>> keySetMapping = Collections.emptyMap();
  
  private boolean killAfterRestore;
  
  private int labelRes;
  
  private boolean largeHeap;
  
  private int largestWidthLimitDp;
  
  private List<String> libraryNames = Collections.emptyList();
  
  private int logo;
  
  private String manageSpaceActivityName;
  
  private float maxAspectRatio;
  
  private Bundle metaData;
  
  private ArraySet<String> mimeGroups;
  
  private float minAspectRatio;
  
  private SparseIntArray minExtensionVersions;
  
  private int minSdkVersion;
  
  private boolean multiArch;
  
  private int networkSecurityConfigRes;
  
  private CharSequence nonLocalizedLabel;
  
  protected List<String> originalPackages = Collections.emptyList();
  
  private boolean overlay;
  
  private String overlayCategory;
  
  private boolean overlayIsStatic;
  
  private int overlayPriority;
  
  private String overlayTarget;
  
  private String overlayTargetName;
  
  private Map<String, String> overlayables = Collections.emptyMap();
  
  protected String packageName;
  
  private boolean partiallyDirectBootAware;
  
  private String permission;
  
  protected List<ParsedPermissionGroup> permissionGroups = Collections.emptyList();
  
  protected List<ParsedPermission> permissions = Collections.emptyList();
  
  private boolean persistent;
  
  private List<Pair<String, ParsedIntentInfo>> preferredActivityFilters = Collections.emptyList();
  
  private boolean preserveLegacyExternalStorage;
  
  private String processName;
  
  private Map<String, ParsedProcess> processes = Collections.emptyMap();
  
  private boolean profileableByShell;
  
  protected List<String> protectedBroadcasts = Collections.emptyList();
  
  protected List<ParsedProvider> providers = Collections.emptyList();
  
  private List<Intent> queriesIntents = Collections.emptyList();
  
  private List<String> queriesPackages = Collections.emptyList();
  
  private Set<String> queriesProviders = Collections.emptySet();
  
  private String realPackage;
  
  protected List<ParsedActivity> receivers = Collections.emptyList();
  
  private List<FeatureInfo> reqFeatures = Collections.emptyList();
  
  private boolean requestLegacyExternalStorage;
  
  private List<String> requestedPermissions = Collections.emptyList();
  
  private String requiredAccountType;
  
  private boolean requiredForAllUsers;
  
  private int requiresSmallestWidthDp;
  
  private Boolean resizeable;
  
  private Boolean resizeableActivity;
  
  private boolean resizeableActivityViaSdkVersion;
  
  private boolean restoreAnyVersion;
  
  private byte[] restrictUpdateHash;
  
  private String restrictedAccountType;
  
  private int roundIconRes;
  
  protected List<ParsedService> services = Collections.emptyList();
  
  private String sharedUserId;
  
  private int sharedUserLabel;
  
  private PackageParser.SigningDetails signingDetails;
  
  private String[] splitClassLoaderNames;
  
  protected String[] splitCodePaths;
  
  private SparseArray<int[]> splitDependencies;
  
  private int[] splitFlags;
  
  private String[] splitNames;
  
  private int[] splitRevisionCodes;
  
  private String staticSharedLibName;
  
  private long staticSharedLibVersion;
  
  private boolean staticSharedLibrary;
  
  private Boolean supportsExtraLargeScreens;
  
  private Boolean supportsLargeScreens;
  
  private Boolean supportsNormalScreens;
  
  private boolean supportsRtl;
  
  private Boolean supportsSmallScreens;
  
  private int targetSandboxVersion;
  
  private int targetSdkVersion;
  
  private String taskAffinity;
  
  private boolean testOnly;
  
  private int theme;
  
  private int uiOptions;
  
  private Set<String> upgradeKeySets = Collections.emptySet();
  
  private boolean use32BitAbi;
  
  private boolean useEmbeddedDex;
  
  private boolean usesCleartextTraffic;
  
  protected List<String> usesLibraries = Collections.emptyList();
  
  private boolean usesNonSdkApi;
  
  protected List<String> usesOptionalLibraries = Collections.emptyList();
  
  private List<String> usesStaticLibraries = Collections.emptyList();
  
  private String[][] usesStaticLibrariesCertDigests;
  
  private long[] usesStaticLibrariesVersions;
  
  protected int versionCode;
  
  protected int versionCodeMajor;
  
  private String versionName;
  
  private boolean visibleToInstantApps;
  
  private boolean vmSafeMode;
  
  protected String volumeUuid;
  
  private String zygotePreloadName;
  
  static {
    sForIntentInfoPairs = (ParsedIntentInfo.StringPairListParceler)Parcelling.Cache.getOrCreate(ParsedIntentInfo.StringPairListParceler.class);
    ORDER_COMPARATOR = (Comparator<ParsedMainComponent>)_$$Lambda$ParsingPackageImpl$RBt_Tka1DogaqXftDzlcrLHE70c.INSTANCE;
    CREATOR = new Parcelable.Creator<ParsingPackageImpl>() {
        public ParsingPackageImpl createFromParcel(Parcel param1Parcel) {
          return new ParsingPackageImpl(param1Parcel);
        }
        
        public ParsingPackageImpl[] newArray(int param1Int) {
          return new ParsingPackageImpl[param1Int];
        }
      };
  }
  
  public ParsingPackageImpl(Parcel paramParcel) {
    ClassLoader classLoader = Object.class.getClassLoader();
    this.supportsSmallScreens = sForBoolean.unparcel(paramParcel);
    this.supportsNormalScreens = sForBoolean.unparcel(paramParcel);
    this.supportsLargeScreens = sForBoolean.unparcel(paramParcel);
    this.supportsExtraLargeScreens = sForBoolean.unparcel(paramParcel);
    this.resizeable = sForBoolean.unparcel(paramParcel);
    this.anyDensity = sForBoolean.unparcel(paramParcel);
    this.versionCode = paramParcel.readInt();
    this.versionCodeMajor = paramParcel.readInt();
    this.baseRevisionCode = paramParcel.readInt();
    this.versionName = sForInternedString.unparcel(paramParcel);
    this.compileSdkVersion = paramParcel.readInt();
    this.compileSdkVersionCodeName = paramParcel.readString();
    this.packageName = sForInternedString.unparcel(paramParcel);
    this.realPackage = paramParcel.readString();
    this.baseCodePath = paramParcel.readString();
    this.requiredForAllUsers = paramParcel.readBoolean();
    this.restrictedAccountType = paramParcel.readString();
    this.requiredAccountType = paramParcel.readString();
    this.overlayTarget = sForInternedString.unparcel(paramParcel);
    this.overlayTargetName = paramParcel.readString();
    this.overlayCategory = paramParcel.readString();
    this.overlayPriority = paramParcel.readInt();
    this.overlayIsStatic = paramParcel.readBoolean();
    this.overlayables = sForInternedStringValueMap.unparcel(paramParcel);
    this.staticSharedLibName = sForInternedString.unparcel(paramParcel);
    this.staticSharedLibVersion = paramParcel.readLong();
    this.libraryNames = sForInternedStringList.unparcel(paramParcel);
    this.usesLibraries = sForInternedStringList.unparcel(paramParcel);
    this.usesOptionalLibraries = sForInternedStringList.unparcel(paramParcel);
    this.usesStaticLibraries = sForInternedStringList.unparcel(paramParcel);
    this.usesStaticLibrariesVersions = paramParcel.createLongArray();
    int i = paramParcel.readInt();
    if (i >= 0) {
      this.usesStaticLibrariesCertDigests = new String[i][];
      for (byte b = 0; b < i; b++)
        this.usesStaticLibrariesCertDigests[b] = sForInternedStringArray.unparcel(paramParcel); 
    } 
    this.sharedUserId = sForInternedString.unparcel(paramParcel);
    this.sharedUserLabel = paramParcel.readInt();
    this.configPreferences = paramParcel.createTypedArrayList(ConfigurationInfo.CREATOR);
    this.reqFeatures = paramParcel.createTypedArrayList(FeatureInfo.CREATOR);
    this.featureGroups = paramParcel.createTypedArrayList(FeatureGroupInfo.CREATOR);
    this.restrictUpdateHash = paramParcel.createByteArray();
    this.originalPackages = paramParcel.createStringArrayList();
    this.adoptPermissions = sForInternedStringList.unparcel(paramParcel);
    this.requestedPermissions = sForInternedStringList.unparcel(paramParcel);
    this.implicitPermissions = sForInternedStringList.unparcel(paramParcel);
    this.upgradeKeySets = sForStringSet.unparcel(paramParcel);
    this.keySetMapping = paramParcel.readHashMap(classLoader);
    this.protectedBroadcasts = sForInternedStringList.unparcel(paramParcel);
    this.activities = paramParcel.createTypedArrayList(ParsedActivity.CREATOR);
    this.receivers = paramParcel.createTypedArrayList(ParsedActivity.CREATOR);
    this.services = paramParcel.createTypedArrayList(ParsedService.CREATOR);
    this.providers = paramParcel.createTypedArrayList(ParsedProvider.CREATOR);
    this.attributions = paramParcel.createTypedArrayList(ParsedAttribution.CREATOR);
    this.permissions = paramParcel.createTypedArrayList(ParsedPermission.CREATOR);
    this.permissionGroups = paramParcel.createTypedArrayList(ParsedPermissionGroup.CREATOR);
    this.instrumentations = paramParcel.createTypedArrayList(ParsedInstrumentation.CREATOR);
    this.preferredActivityFilters = sForIntentInfoPairs.unparcel(paramParcel);
    this.processes = paramParcel.readHashMap(classLoader);
    this.metaData = paramParcel.readBundle(classLoader);
    this.volumeUuid = sForInternedString.unparcel(paramParcel);
    this.signingDetails = (PackageParser.SigningDetails)paramParcel.readParcelable(classLoader);
    this.codePath = paramParcel.readString();
    this.use32BitAbi = paramParcel.readBoolean();
    this.visibleToInstantApps = paramParcel.readBoolean();
    this.forceQueryable = paramParcel.readBoolean();
    this.queriesIntents = paramParcel.createTypedArrayList(Intent.CREATOR);
    this.queriesPackages = sForInternedStringList.unparcel(paramParcel);
    this.appComponentFactory = paramParcel.readString();
    this.backupAgentName = paramParcel.readString();
    this.banner = paramParcel.readInt();
    this.category = paramParcel.readInt();
    this.classLoaderName = paramParcel.readString();
    this.className = paramParcel.readString();
    this.compatibleWidthLimitDp = paramParcel.readInt();
    this.descriptionRes = paramParcel.readInt();
    this.enabled = paramParcel.readBoolean();
    this.crossProfile = paramParcel.readBoolean();
    this.fullBackupContent = paramParcel.readInt();
    this.iconRes = paramParcel.readInt();
    this.installLocation = paramParcel.readInt();
    this.labelRes = paramParcel.readInt();
    this.largestWidthLimitDp = paramParcel.readInt();
    this.logo = paramParcel.readInt();
    this.manageSpaceActivityName = paramParcel.readString();
    this.maxAspectRatio = paramParcel.readFloat();
    this.minAspectRatio = paramParcel.readFloat();
    this.minSdkVersion = paramParcel.readInt();
    this.networkSecurityConfigRes = paramParcel.readInt();
    this.nonLocalizedLabel = paramParcel.readCharSequence();
    this.permission = paramParcel.readString();
    this.processName = paramParcel.readString();
    this.requiresSmallestWidthDp = paramParcel.readInt();
    this.roundIconRes = paramParcel.readInt();
    this.targetSandboxVersion = paramParcel.readInt();
    this.targetSdkVersion = paramParcel.readInt();
    this.taskAffinity = paramParcel.readString();
    this.theme = paramParcel.readInt();
    this.uiOptions = paramParcel.readInt();
    this.zygotePreloadName = paramParcel.readString();
    this.splitClassLoaderNames = paramParcel.createStringArray();
    this.splitCodePaths = paramParcel.createStringArray();
    this.splitDependencies = paramParcel.readSparseArray(classLoader);
    this.splitFlags = paramParcel.createIntArray();
    this.splitNames = paramParcel.createStringArray();
    this.splitRevisionCodes = paramParcel.createIntArray();
    this.externalStorage = paramParcel.readBoolean();
    this.baseHardwareAccelerated = paramParcel.readBoolean();
    this.allowBackup = paramParcel.readBoolean();
    this.killAfterRestore = paramParcel.readBoolean();
    this.restoreAnyVersion = paramParcel.readBoolean();
    this.fullBackupOnly = paramParcel.readBoolean();
    this.persistent = paramParcel.readBoolean();
    this.debuggable = paramParcel.readBoolean();
    this.vmSafeMode = paramParcel.readBoolean();
    this.hasCode = paramParcel.readBoolean();
    this.allowTaskReparenting = paramParcel.readBoolean();
    this.allowClearUserData = paramParcel.readBoolean();
    this.largeHeap = paramParcel.readBoolean();
    this.usesCleartextTraffic = paramParcel.readBoolean();
    this.supportsRtl = paramParcel.readBoolean();
    this.testOnly = paramParcel.readBoolean();
    this.multiArch = paramParcel.readBoolean();
    this.extractNativeLibs = paramParcel.readBoolean();
    this.game = paramParcel.readBoolean();
    this.resizeableActivity = sForBoolean.unparcel(paramParcel);
    this.staticSharedLibrary = paramParcel.readBoolean();
    this.overlay = paramParcel.readBoolean();
    this.isolatedSplitLoading = paramParcel.readBoolean();
    this.hasDomainUrls = paramParcel.readBoolean();
    this.profileableByShell = paramParcel.readBoolean();
    this.backupInForeground = paramParcel.readBoolean();
    this.useEmbeddedDex = paramParcel.readBoolean();
    this.defaultToDeviceProtectedStorage = paramParcel.readBoolean();
    this.directBootAware = paramParcel.readBoolean();
    this.partiallyDirectBootAware = paramParcel.readBoolean();
    this.resizeableActivityViaSdkVersion = paramParcel.readBoolean();
    this.allowClearUserDataOnFailedRestore = paramParcel.readBoolean();
    this.allowAudioPlaybackCapture = paramParcel.readBoolean();
    this.requestLegacyExternalStorage = paramParcel.readBoolean();
    this.usesNonSdkApi = paramParcel.readBoolean();
    this.hasFragileUserData = paramParcel.readBoolean();
    this.cantSaveState = paramParcel.readBoolean();
    this.allowNativeHeapPointerTagging = paramParcel.readBoolean();
    this.autoRevokePermissions = paramParcel.readInt();
    this.preserveLegacyExternalStorage = paramParcel.readBoolean();
    this.mimeGroups = paramParcel.readArraySet(classLoader);
    this.gwpAsanMode = paramParcel.readInt();
    this.minExtensionVersions = paramParcel.readSparseIntArray();
  }
  
  public ParsingPackageImpl(String paramString1, String paramString2, String paramString3, TypedArray paramTypedArray) {
    this.packageName = TextUtils.safeIntern(paramString1);
    this.baseCodePath = paramString2;
    this.codePath = paramString3;
    if (paramTypedArray != null) {
      this.versionCode = paramTypedArray.getInteger(1, 0);
      this.versionCodeMajor = paramTypedArray.getInteger(11, 0);
      setBaseRevisionCode(paramTypedArray.getInteger(5, 0));
      setVersionName(paramTypedArray.getNonConfigurationString(2, 0));
      setCompileSdkVersion(paramTypedArray.getInteger(9, 0));
      setCompileSdkVersionCodename(paramTypedArray.getNonConfigurationString(10, 0));
      setIsolatedSplitLoading(paramTypedArray.getBoolean(6, false));
    } 
  }
  
  private void addMimeGroupsFromComponent(ParsedComponent paramParsedComponent) {
    for (int i = paramParsedComponent.getIntents().size() - 1; i >= 0; i--) {
      IntentFilter intentFilter = paramParsedComponent.getIntents().get(i);
      for (int j = intentFilter.countMimeGroups() - 1; j >= 0; j--)
        this.mimeGroups = ArrayUtils.add(this.mimeGroups, intentFilter.getMimeGroup(j)); 
    } 
  }
  
  public ParsingPackageImpl addActivity(ParsedActivity paramParsedActivity) {
    this.activities = CollectionUtils.add(this.activities, paramParsedActivity);
    addMimeGroupsFromComponent((ParsedComponent)paramParsedActivity);
    return this;
  }
  
  public ParsingPackageImpl addAdoptPermission(String paramString) {
    this.adoptPermissions = CollectionUtils.add(this.adoptPermissions, TextUtils.safeIntern(paramString));
    return this;
  }
  
  public ParsingPackageImpl addAttribution(ParsedAttribution paramParsedAttribution) {
    this.attributions = CollectionUtils.add(this.attributions, paramParsedAttribution);
    return this;
  }
  
  public ParsingPackageImpl addConfigPreference(ConfigurationInfo paramConfigurationInfo) {
    this.configPreferences = CollectionUtils.add(this.configPreferences, paramConfigurationInfo);
    return this;
  }
  
  public ParsingPackageImpl addFeatureGroup(FeatureGroupInfo paramFeatureGroupInfo) {
    this.featureGroups = CollectionUtils.add(this.featureGroups, paramFeatureGroupInfo);
    return this;
  }
  
  public ParsingPackageImpl addImplicitPermission(String paramString) {
    this.implicitPermissions = CollectionUtils.add(this.implicitPermissions, TextUtils.safeIntern(paramString));
    return this;
  }
  
  public ParsingPackageImpl addInstrumentation(ParsedInstrumentation paramParsedInstrumentation) {
    this.instrumentations = CollectionUtils.add(this.instrumentations, paramParsedInstrumentation);
    return this;
  }
  
  public ParsingPackageImpl addKeySet(String paramString, PublicKey paramPublicKey) {
    ArraySet arraySet1 = this.keySetMapping.get(paramString);
    ArraySet arraySet2 = arraySet1;
    if (arraySet1 == null)
      arraySet2 = new ArraySet(); 
    arraySet2.add(paramPublicKey);
    this.keySetMapping = CollectionUtils.add(this.keySetMapping, paramString, arraySet2);
    return this;
  }
  
  public ParsingPackageImpl addLibraryName(String paramString) {
    this.libraryNames = CollectionUtils.add(this.libraryNames, TextUtils.safeIntern(paramString));
    return this;
  }
  
  public ParsingPackageImpl addOriginalPackage(String paramString) {
    this.originalPackages = CollectionUtils.add(this.originalPackages, paramString);
    return this;
  }
  
  public ParsingPackage addOverlayable(String paramString1, String paramString2) {
    this.overlayables = CollectionUtils.add(this.overlayables, paramString1, TextUtils.safeIntern(paramString2));
    return this;
  }
  
  public ParsingPackageImpl addPermission(ParsedPermission paramParsedPermission) {
    this.permissions = CollectionUtils.add(this.permissions, paramParsedPermission);
    return this;
  }
  
  public ParsingPackageImpl addPermissionGroup(ParsedPermissionGroup paramParsedPermissionGroup) {
    this.permissionGroups = CollectionUtils.add(this.permissionGroups, paramParsedPermissionGroup);
    return this;
  }
  
  public ParsingPackageImpl addPreferredActivityFilter(String paramString, ParsedIntentInfo paramParsedIntentInfo) {
    this.preferredActivityFilters = CollectionUtils.add(this.preferredActivityFilters, Pair.create(paramString, paramParsedIntentInfo));
    return this;
  }
  
  public ParsingPackageImpl addProtectedBroadcast(String paramString) {
    if (!this.protectedBroadcasts.contains(paramString))
      this.protectedBroadcasts = CollectionUtils.add(this.protectedBroadcasts, TextUtils.safeIntern(paramString)); 
    return this;
  }
  
  public ParsingPackageImpl addProvider(ParsedProvider paramParsedProvider) {
    this.providers = CollectionUtils.add(this.providers, paramParsedProvider);
    addMimeGroupsFromComponent((ParsedComponent)paramParsedProvider);
    return this;
  }
  
  public ParsingPackageImpl addQueriesIntent(Intent paramIntent) {
    this.queriesIntents = CollectionUtils.add(this.queriesIntents, paramIntent);
    return this;
  }
  
  public ParsingPackageImpl addQueriesPackage(String paramString) {
    this.queriesPackages = CollectionUtils.add(this.queriesPackages, TextUtils.safeIntern(paramString));
    return this;
  }
  
  public ParsingPackageImpl addQueriesProvider(String paramString) {
    this.queriesProviders = CollectionUtils.add(this.queriesProviders, paramString);
    return this;
  }
  
  public ParsingPackageImpl addReceiver(ParsedActivity paramParsedActivity) {
    this.receivers = CollectionUtils.add(this.receivers, paramParsedActivity);
    addMimeGroupsFromComponent((ParsedComponent)paramParsedActivity);
    return this;
  }
  
  public ParsingPackageImpl addReqFeature(FeatureInfo paramFeatureInfo) {
    this.reqFeatures = CollectionUtils.add(this.reqFeatures, paramFeatureInfo);
    return this;
  }
  
  public ParsingPackageImpl addRequestedPermission(String paramString) {
    this.requestedPermissions = CollectionUtils.add(this.requestedPermissions, TextUtils.safeIntern(paramString));
    return this;
  }
  
  public ParsingPackageImpl addService(ParsedService paramParsedService) {
    this.services = CollectionUtils.add(this.services, paramParsedService);
    addMimeGroupsFromComponent((ParsedComponent)paramParsedService);
    return this;
  }
  
  public ParsingPackageImpl addUsesLibrary(String paramString) {
    this.usesLibraries = CollectionUtils.add(this.usesLibraries, TextUtils.safeIntern(paramString));
    return this;
  }
  
  public ParsingPackageImpl addUsesOptionalLibrary(String paramString) {
    this.usesOptionalLibraries = CollectionUtils.add(this.usesOptionalLibraries, TextUtils.safeIntern(paramString));
    return this;
  }
  
  public ParsingPackageImpl addUsesStaticLibrary(String paramString) {
    this.usesStaticLibraries = CollectionUtils.add(this.usesStaticLibraries, TextUtils.safeIntern(paramString));
    return this;
  }
  
  public ParsingPackageImpl addUsesStaticLibraryCertDigests(String[] paramArrayOfString) {
    this.usesStaticLibrariesCertDigests = (String[][])ArrayUtils.appendElement(String[].class, (Object[])this.usesStaticLibrariesCertDigests, paramArrayOfString, true);
    return this;
  }
  
  public ParsingPackageImpl addUsesStaticLibraryVersion(long paramLong) {
    this.usesStaticLibrariesVersions = ArrayUtils.appendLong(this.usesStaticLibrariesVersions, paramLong, true);
    return this;
  }
  
  public ParsingPackageImpl asSplit(String[] paramArrayOfString1, String[] paramArrayOfString2, int[] paramArrayOfint, SparseArray<int[]> paramSparseArray) {
    this.splitNames = paramArrayOfString1;
    this.splitCodePaths = paramArrayOfString2;
    this.splitRevisionCodes = paramArrayOfint;
    this.splitDependencies = paramSparseArray;
    int i = paramArrayOfString1.length;
    this.splitFlags = new int[i];
    this.splitClassLoaderNames = new String[i];
    return this;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public List<ParsedActivity> getActivities() {
    return this.activities;
  }
  
  public List<String> getAdoptPermissions() {
    return this.adoptPermissions;
  }
  
  public String getAppComponentFactory() {
    return this.appComponentFactory;
  }
  
  public List<ParsedAttribution> getAttributions() {
    return this.attributions;
  }
  
  public int getAutoRevokePermissions() {
    return this.autoRevokePermissions;
  }
  
  public String getBackupAgentName() {
    return this.backupAgentName;
  }
  
  public int getBanner() {
    return this.banner;
  }
  
  public String getBaseCodePath() {
    return this.baseCodePath;
  }
  
  public int getBaseRevisionCode() {
    return this.baseRevisionCode;
  }
  
  public int getCategory() {
    return this.category;
  }
  
  public String getClassLoaderName() {
    return this.classLoaderName;
  }
  
  public String getClassName() {
    return this.className;
  }
  
  public String getCodePath() {
    return this.codePath;
  }
  
  public int getCompatibleWidthLimitDp() {
    return this.compatibleWidthLimitDp;
  }
  
  public int getCompileSdkVersion() {
    return this.compileSdkVersion;
  }
  
  public String getCompileSdkVersionCodeName() {
    return this.compileSdkVersionCodeName;
  }
  
  public List<ConfigurationInfo> getConfigPreferences() {
    return this.configPreferences;
  }
  
  public int getDescriptionRes() {
    return this.descriptionRes;
  }
  
  public List<FeatureGroupInfo> getFeatureGroups() {
    return this.featureGroups;
  }
  
  public int getFullBackupContent() {
    return this.fullBackupContent;
  }
  
  public int getGwpAsanMode() {
    return this.gwpAsanMode;
  }
  
  public int getIconRes() {
    return this.iconRes;
  }
  
  public List<String> getImplicitPermissions() {
    return this.implicitPermissions;
  }
  
  public int getInstallLocation() {
    return this.installLocation;
  }
  
  public List<ParsedInstrumentation> getInstrumentations() {
    return this.instrumentations;
  }
  
  public Map<String, ArraySet<PublicKey>> getKeySetMapping() {
    return this.keySetMapping;
  }
  
  public int getLabelRes() {
    return this.labelRes;
  }
  
  public int getLargestWidthLimitDp() {
    return this.largestWidthLimitDp;
  }
  
  public List<String> getLibraryNames() {
    return this.libraryNames;
  }
  
  public int getLogo() {
    return this.logo;
  }
  
  public String getManageSpaceActivityName() {
    return this.manageSpaceActivityName;
  }
  
  public float getMaxAspectRatio() {
    return this.maxAspectRatio;
  }
  
  public Bundle getMetaData() {
    return this.metaData;
  }
  
  public Set<String> getMimeGroups() {
    return (Set<String>)this.mimeGroups;
  }
  
  public float getMinAspectRatio() {
    return this.minAspectRatio;
  }
  
  public SparseIntArray getMinExtensionVersions() {
    return this.minExtensionVersions;
  }
  
  public int getMinSdkVersion() {
    return this.minSdkVersion;
  }
  
  public int getNetworkSecurityConfigRes() {
    return this.networkSecurityConfigRes;
  }
  
  public CharSequence getNonLocalizedLabel() {
    return this.nonLocalizedLabel;
  }
  
  public List<String> getOriginalPackages() {
    return this.originalPackages;
  }
  
  public String getOverlayCategory() {
    return this.overlayCategory;
  }
  
  public int getOverlayPriority() {
    return this.overlayPriority;
  }
  
  public String getOverlayTarget() {
    return this.overlayTarget;
  }
  
  public String getOverlayTargetName() {
    return this.overlayTargetName;
  }
  
  public Map<String, String> getOverlayables() {
    return this.overlayables;
  }
  
  public String getPackageName() {
    return this.packageName;
  }
  
  public String getPermission() {
    return this.permission;
  }
  
  public List<ParsedPermissionGroup> getPermissionGroups() {
    return this.permissionGroups;
  }
  
  public List<ParsedPermission> getPermissions() {
    return this.permissions;
  }
  
  public List<Pair<String, ParsedIntentInfo>> getPreferredActivityFilters() {
    return this.preferredActivityFilters;
  }
  
  public String getProcessName() {
    String str = this.processName;
    if (str == null)
      str = this.packageName; 
    return str;
  }
  
  public Map<String, ParsedProcess> getProcesses() {
    return this.processes;
  }
  
  public List<String> getProtectedBroadcasts() {
    return this.protectedBroadcasts;
  }
  
  public List<ParsedProvider> getProviders() {
    return this.providers;
  }
  
  public List<Intent> getQueriesIntents() {
    return this.queriesIntents;
  }
  
  public List<String> getQueriesPackages() {
    return this.queriesPackages;
  }
  
  public Set<String> getQueriesProviders() {
    return this.queriesProviders;
  }
  
  public String getRealPackage() {
    return this.realPackage;
  }
  
  public List<ParsedActivity> getReceivers() {
    return this.receivers;
  }
  
  public List<FeatureInfo> getReqFeatures() {
    return this.reqFeatures;
  }
  
  public List<String> getRequestedPermissions() {
    return this.requestedPermissions;
  }
  
  public String getRequiredAccountType() {
    return this.requiredAccountType;
  }
  
  public int getRequiresSmallestWidthDp() {
    return this.requiresSmallestWidthDp;
  }
  
  public Boolean getResizeableActivity() {
    return this.resizeableActivity;
  }
  
  public byte[] getRestrictUpdateHash() {
    return this.restrictUpdateHash;
  }
  
  public String getRestrictedAccountType() {
    return this.restrictedAccountType;
  }
  
  public int getRoundIconRes() {
    return this.roundIconRes;
  }
  
  public List<ParsedService> getServices() {
    return this.services;
  }
  
  public String getSharedUserId() {
    return this.sharedUserId;
  }
  
  public int getSharedUserLabel() {
    return this.sharedUserLabel;
  }
  
  public PackageParser.SigningDetails getSigningDetails() {
    return this.signingDetails;
  }
  
  public String[] getSplitClassLoaderNames() {
    return this.splitClassLoaderNames;
  }
  
  public String[] getSplitCodePaths() {
    return this.splitCodePaths;
  }
  
  public SparseArray<int[]> getSplitDependencies() {
    return this.splitDependencies;
  }
  
  public int[] getSplitFlags() {
    return this.splitFlags;
  }
  
  public String[] getSplitNames() {
    return this.splitNames;
  }
  
  public int[] getSplitRevisionCodes() {
    return this.splitRevisionCodes;
  }
  
  public String getStaticSharedLibName() {
    return this.staticSharedLibName;
  }
  
  public long getStaticSharedLibVersion() {
    return this.staticSharedLibVersion;
  }
  
  public int getTargetSandboxVersion() {
    return this.targetSandboxVersion;
  }
  
  public int getTargetSdkVersion() {
    return this.targetSdkVersion;
  }
  
  public String getTaskAffinity() {
    return this.taskAffinity;
  }
  
  public int getTheme() {
    return this.theme;
  }
  
  public int getUiOptions() {
    return this.uiOptions;
  }
  
  public Set<String> getUpgradeKeySets() {
    return this.upgradeKeySets;
  }
  
  public List<String> getUsesLibraries() {
    return this.usesLibraries;
  }
  
  public List<String> getUsesOptionalLibraries() {
    return this.usesOptionalLibraries;
  }
  
  public List<String> getUsesStaticLibraries() {
    return this.usesStaticLibraries;
  }
  
  public String[][] getUsesStaticLibrariesCertDigests() {
    return this.usesStaticLibrariesCertDigests;
  }
  
  public long[] getUsesStaticLibrariesVersions() {
    return this.usesStaticLibrariesVersions;
  }
  
  public int getVersionCode() {
    return this.versionCode;
  }
  
  public int getVersionCodeMajor() {
    return this.versionCodeMajor;
  }
  
  public String getVersionName() {
    return this.versionName;
  }
  
  public String getVolumeUuid() {
    return this.volumeUuid;
  }
  
  public String getZygotePreloadName() {
    return this.zygotePreloadName;
  }
  
  public boolean hasPreserveLegacyExternalStorage() {
    return this.preserveLegacyExternalStorage;
  }
  
  public Object hideAsParsed() {
    throw new UnsupportedOperationException();
  }
  
  public boolean isAllowAudioPlaybackCapture() {
    return this.allowAudioPlaybackCapture;
  }
  
  public boolean isAllowBackup() {
    return this.allowBackup;
  }
  
  public boolean isAllowClearUserData() {
    return this.allowClearUserData;
  }
  
  public boolean isAllowClearUserDataOnFailedRestore() {
    return this.allowClearUserDataOnFailedRestore;
  }
  
  public boolean isAllowNativeHeapPointerTagging() {
    return this.allowNativeHeapPointerTagging;
  }
  
  public boolean isAllowTaskReparenting() {
    return this.allowTaskReparenting;
  }
  
  public boolean isAnyDensity() {
    Boolean bool = this.anyDensity;
    if (bool == null) {
      boolean bool1;
      if (this.targetSdkVersion >= 4) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      return bool1;
    } 
    return bool.booleanValue();
  }
  
  public boolean isBackupInForeground() {
    return this.backupInForeground;
  }
  
  public boolean isBaseHardwareAccelerated() {
    return this.baseHardwareAccelerated;
  }
  
  public boolean isCantSaveState() {
    return this.cantSaveState;
  }
  
  public boolean isCrossProfile() {
    return this.crossProfile;
  }
  
  public boolean isDebuggable() {
    return this.debuggable;
  }
  
  public boolean isDefaultToDeviceProtectedStorage() {
    return this.defaultToDeviceProtectedStorage;
  }
  
  public boolean isDirectBootAware() {
    return this.directBootAware;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public boolean isExternalStorage() {
    return this.externalStorage;
  }
  
  public boolean isExtractNativeLibs() {
    return this.extractNativeLibs;
  }
  
  public boolean isForceQueryable() {
    return this.forceQueryable;
  }
  
  public boolean isFullBackupOnly() {
    return this.fullBackupOnly;
  }
  
  public boolean isGame() {
    return this.game;
  }
  
  public boolean isHasCode() {
    return this.hasCode;
  }
  
  public boolean isHasDomainUrls() {
    return this.hasDomainUrls;
  }
  
  public boolean isHasFragileUserData() {
    return this.hasFragileUserData;
  }
  
  public boolean isIsolatedSplitLoading() {
    return this.isolatedSplitLoading;
  }
  
  public boolean isKillAfterRestore() {
    return this.killAfterRestore;
  }
  
  public boolean isLargeHeap() {
    return this.largeHeap;
  }
  
  public boolean isMultiArch() {
    return this.multiArch;
  }
  
  public boolean isOverlay() {
    return this.overlay;
  }
  
  public boolean isOverlayIsStatic() {
    return this.overlayIsStatic;
  }
  
  public boolean isPartiallyDirectBootAware() {
    return this.partiallyDirectBootAware;
  }
  
  public boolean isPersistent() {
    return this.persistent;
  }
  
  public boolean isProfileableByShell() {
    return this.profileableByShell;
  }
  
  public boolean isRequestLegacyExternalStorage() {
    return this.requestLegacyExternalStorage;
  }
  
  public boolean isRequiredForAllUsers() {
    return this.requiredForAllUsers;
  }
  
  public boolean isResizeable() {
    Boolean bool = this.resizeable;
    if (bool == null) {
      boolean bool1;
      if (this.targetSdkVersion >= 4) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      return bool1;
    } 
    return bool.booleanValue();
  }
  
  public boolean isResizeableActivityViaSdkVersion() {
    return this.resizeableActivityViaSdkVersion;
  }
  
  public boolean isRestoreAnyVersion() {
    return this.restoreAnyVersion;
  }
  
  public boolean isStaticSharedLibrary() {
    return this.staticSharedLibrary;
  }
  
  public boolean isSupportsExtraLargeScreens() {
    Boolean bool = this.supportsExtraLargeScreens;
    if (bool == null) {
      boolean bool1;
      if (this.targetSdkVersion >= 9) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      return bool1;
    } 
    return bool.booleanValue();
  }
  
  public boolean isSupportsLargeScreens() {
    Boolean bool = this.supportsLargeScreens;
    if (bool == null) {
      boolean bool1;
      if (this.targetSdkVersion >= 4) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      return bool1;
    } 
    return bool.booleanValue();
  }
  
  public boolean isSupportsNormalScreens() {
    Boolean bool = this.supportsNormalScreens;
    return (bool == null || bool.booleanValue());
  }
  
  public boolean isSupportsRtl() {
    return this.supportsRtl;
  }
  
  public boolean isSupportsSmallScreens() {
    Boolean bool = this.supportsSmallScreens;
    if (bool == null) {
      boolean bool1;
      if (this.targetSdkVersion >= 4) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      return bool1;
    } 
    return bool.booleanValue();
  }
  
  public boolean isTestOnly() {
    return this.testOnly;
  }
  
  public boolean isUse32BitAbi() {
    return this.use32BitAbi;
  }
  
  public boolean isUseEmbeddedDex() {
    return this.useEmbeddedDex;
  }
  
  public boolean isUsesCleartextTraffic() {
    return this.usesCleartextTraffic;
  }
  
  public boolean isUsesNonSdkApi() {
    return this.usesNonSdkApi;
  }
  
  public boolean isVisibleToInstantApps() {
    return this.visibleToInstantApps;
  }
  
  public boolean isVmSafeMode() {
    return this.vmSafeMode;
  }
  
  public ParsingPackageImpl removeUsesOptionalLibrary(String paramString) {
    this.usesOptionalLibraries = CollectionUtils.remove(this.usesOptionalLibraries, paramString);
    return this;
  }
  
  public ParsingPackageImpl setAllowAudioPlaybackCapture(boolean paramBoolean) {
    this.allowAudioPlaybackCapture = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setAllowBackup(boolean paramBoolean) {
    this.allowBackup = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setAllowClearUserData(boolean paramBoolean) {
    this.allowClearUserData = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setAllowClearUserDataOnFailedRestore(boolean paramBoolean) {
    this.allowClearUserDataOnFailedRestore = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setAllowNativeHeapPointerTagging(boolean paramBoolean) {
    this.allowNativeHeapPointerTagging = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setAllowTaskReparenting(boolean paramBoolean) {
    this.allowTaskReparenting = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setAnyDensity(int paramInt) {
    boolean bool = true;
    if (paramInt == 1)
      return this; 
    if (paramInt >= 0)
      bool = false; 
    this.anyDensity = Boolean.valueOf(bool);
    return this;
  }
  
  public ParsingPackageImpl setAppComponentFactory(String paramString) {
    this.appComponentFactory = paramString;
    return this;
  }
  
  public ParsingPackageImpl setAutoRevokePermissions(int paramInt) {
    this.autoRevokePermissions = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setBackupAgentName(String paramString) {
    this.backupAgentName = paramString;
    return this;
  }
  
  public ParsingPackageImpl setBackupInForeground(boolean paramBoolean) {
    this.backupInForeground = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setBanner(int paramInt) {
    this.banner = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setBaseHardwareAccelerated(boolean paramBoolean) {
    this.baseHardwareAccelerated = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setBaseRevisionCode(int paramInt) {
    this.baseRevisionCode = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setCantSaveState(boolean paramBoolean) {
    this.cantSaveState = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setCategory(int paramInt) {
    this.category = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setClassLoaderName(String paramString) {
    this.classLoaderName = paramString;
    return this;
  }
  
  public ParsingPackageImpl setClassName(String paramString) {
    this.className = paramString;
    return this;
  }
  
  public ParsingPackageImpl setCompatibleWidthLimitDp(int paramInt) {
    this.compatibleWidthLimitDp = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setCompileSdkVersion(int paramInt) {
    this.compileSdkVersion = paramInt;
    return this;
  }
  
  public ParsingPackage setCompileSdkVersionCodename(String paramString) {
    this.compileSdkVersionCodeName = paramString;
    return this;
  }
  
  public ParsingPackageImpl setCrossProfile(boolean paramBoolean) {
    this.crossProfile = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setDebuggable(boolean paramBoolean) {
    this.debuggable = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setDefaultToDeviceProtectedStorage(boolean paramBoolean) {
    this.defaultToDeviceProtectedStorage = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setDescriptionRes(int paramInt) {
    this.descriptionRes = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setDirectBootAware(boolean paramBoolean) {
    this.directBootAware = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setEnabled(boolean paramBoolean) {
    this.enabled = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setExternalStorage(boolean paramBoolean) {
    this.externalStorage = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setExtractNativeLibs(boolean paramBoolean) {
    this.extractNativeLibs = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setForceQueryable(boolean paramBoolean) {
    this.forceQueryable = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setFullBackupContent(int paramInt) {
    this.fullBackupContent = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setFullBackupOnly(boolean paramBoolean) {
    this.fullBackupOnly = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setGame(boolean paramBoolean) {
    this.game = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setGwpAsanMode(int paramInt) {
    this.gwpAsanMode = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setHasCode(boolean paramBoolean) {
    this.hasCode = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setHasDomainUrls(boolean paramBoolean) {
    this.hasDomainUrls = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setHasFragileUserData(boolean paramBoolean) {
    this.hasFragileUserData = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setIconRes(int paramInt) {
    this.iconRes = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setInstallLocation(int paramInt) {
    this.installLocation = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setIsolatedSplitLoading(boolean paramBoolean) {
    this.isolatedSplitLoading = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setKillAfterRestore(boolean paramBoolean) {
    this.killAfterRestore = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setLabelRes(int paramInt) {
    this.labelRes = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setLargeHeap(boolean paramBoolean) {
    this.largeHeap = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setLargestWidthLimitDp(int paramInt) {
    this.largestWidthLimitDp = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setLogo(int paramInt) {
    this.logo = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setManageSpaceActivityName(String paramString) {
    this.manageSpaceActivityName = paramString;
    return this;
  }
  
  public ParsingPackageImpl setMaxAspectRatio(float paramFloat) {
    this.maxAspectRatio = paramFloat;
    return this;
  }
  
  public ParsingPackageImpl setMetaData(Bundle paramBundle) {
    this.metaData = paramBundle;
    return this;
  }
  
  public ParsingPackageImpl setMinAspectRatio(float paramFloat) {
    this.minAspectRatio = paramFloat;
    return this;
  }
  
  public ParsingPackageImpl setMinExtensionVersions(SparseIntArray paramSparseIntArray) {
    this.minExtensionVersions = paramSparseIntArray;
    return this;
  }
  
  public ParsingPackageImpl setMinSdkVersion(int paramInt) {
    this.minSdkVersion = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setMultiArch(boolean paramBoolean) {
    this.multiArch = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setNetworkSecurityConfigRes(int paramInt) {
    this.networkSecurityConfigRes = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setNonLocalizedLabel(CharSequence paramCharSequence) {
    this.nonLocalizedLabel = paramCharSequence;
    return this;
  }
  
  public ParsingPackageImpl setOverlay(boolean paramBoolean) {
    this.overlay = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setOverlayCategory(String paramString) {
    this.overlayCategory = paramString;
    return this;
  }
  
  public ParsingPackageImpl setOverlayIsStatic(boolean paramBoolean) {
    this.overlayIsStatic = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setOverlayPriority(int paramInt) {
    this.overlayPriority = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setOverlayTarget(String paramString) {
    this.overlayTarget = TextUtils.safeIntern(paramString);
    return this;
  }
  
  public ParsingPackageImpl setOverlayTargetName(String paramString) {
    this.overlayTargetName = paramString;
    return this;
  }
  
  public ParsingPackageImpl setPartiallyDirectBootAware(boolean paramBoolean) {
    this.partiallyDirectBootAware = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setPermission(String paramString) {
    this.permission = paramString;
    return this;
  }
  
  public ParsingPackageImpl setPersistent(boolean paramBoolean) {
    this.persistent = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setPreserveLegacyExternalStorage(boolean paramBoolean) {
    this.preserveLegacyExternalStorage = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setProcessName(String paramString) {
    this.processName = paramString;
    return this;
  }
  
  public ParsingPackageImpl setProcesses(Map<String, ParsedProcess> paramMap) {
    this.processes = paramMap;
    return this;
  }
  
  public ParsingPackageImpl setProfileableByShell(boolean paramBoolean) {
    this.profileableByShell = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setRealPackage(String paramString) {
    this.realPackage = paramString;
    return this;
  }
  
  public ParsingPackageImpl setRequestLegacyExternalStorage(boolean paramBoolean) {
    this.requestLegacyExternalStorage = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setRequiredAccountType(String paramString) {
    this.requiredAccountType = TextUtils.nullIfEmpty(paramString);
    return this;
  }
  
  public ParsingPackageImpl setRequiredForAllUsers(boolean paramBoolean) {
    this.requiredForAllUsers = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setRequiresSmallestWidthDp(int paramInt) {
    this.requiresSmallestWidthDp = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setResizeable(int paramInt) {
    boolean bool = true;
    if (paramInt == 1)
      return this; 
    if (paramInt >= 0)
      bool = false; 
    this.resizeable = Boolean.valueOf(bool);
    return this;
  }
  
  public ParsingPackageImpl setResizeableActivity(Boolean paramBoolean) {
    this.resizeableActivity = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setResizeableActivityViaSdkVersion(boolean paramBoolean) {
    this.resizeableActivityViaSdkVersion = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setRestoreAnyVersion(boolean paramBoolean) {
    this.restoreAnyVersion = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setRestrictUpdateHash(byte... paramVarArgs) {
    this.restrictUpdateHash = paramVarArgs;
    return this;
  }
  
  public ParsingPackageImpl setRestrictedAccountType(String paramString) {
    this.restrictedAccountType = paramString;
    return this;
  }
  
  public ParsingPackageImpl setRoundIconRes(int paramInt) {
    this.roundIconRes = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setSharedUserId(String paramString) {
    this.sharedUserId = TextUtils.safeIntern(paramString);
    return this;
  }
  
  public ParsingPackageImpl setSharedUserLabel(int paramInt) {
    this.sharedUserLabel = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setSigningDetails(PackageParser.SigningDetails paramSigningDetails) {
    this.signingDetails = paramSigningDetails;
    return this;
  }
  
  public ParsingPackageImpl setSplitClassLoaderName(int paramInt, String paramString) {
    this.splitClassLoaderNames[paramInt] = paramString;
    return this;
  }
  
  public ParsingPackageImpl setSplitHasCode(int paramInt, boolean paramBoolean) {
    int i;
    int[] arrayOfInt = this.splitFlags;
    if (paramBoolean) {
      i = arrayOfInt[paramInt] | 0x4;
    } else {
      i = arrayOfInt[paramInt] & 0xFFFFFFFB;
    } 
    arrayOfInt[paramInt] = i;
    return this;
  }
  
  public ParsingPackageImpl setStaticSharedLibName(String paramString) {
    this.staticSharedLibName = TextUtils.safeIntern(paramString);
    return this;
  }
  
  public ParsingPackageImpl setStaticSharedLibVersion(long paramLong) {
    this.staticSharedLibVersion = paramLong;
    return this;
  }
  
  public ParsingPackageImpl setStaticSharedLibrary(boolean paramBoolean) {
    this.staticSharedLibrary = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setSupportsExtraLargeScreens(int paramInt) {
    boolean bool = true;
    if (paramInt == 1)
      return this; 
    if (paramInt >= 0)
      bool = false; 
    this.supportsExtraLargeScreens = Boolean.valueOf(bool);
    return this;
  }
  
  public ParsingPackageImpl setSupportsLargeScreens(int paramInt) {
    boolean bool = true;
    if (paramInt == 1)
      return this; 
    if (paramInt >= 0)
      bool = false; 
    this.supportsLargeScreens = Boolean.valueOf(bool);
    return this;
  }
  
  public ParsingPackageImpl setSupportsNormalScreens(int paramInt) {
    boolean bool = true;
    if (paramInt == 1)
      return this; 
    if (paramInt >= 0)
      bool = false; 
    this.supportsNormalScreens = Boolean.valueOf(bool);
    return this;
  }
  
  public ParsingPackageImpl setSupportsRtl(boolean paramBoolean) {
    this.supportsRtl = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setSupportsSmallScreens(int paramInt) {
    boolean bool = true;
    if (paramInt == 1)
      return this; 
    if (paramInt >= 0)
      bool = false; 
    this.supportsSmallScreens = Boolean.valueOf(bool);
    return this;
  }
  
  public ParsingPackageImpl setTargetSandboxVersion(int paramInt) {
    this.targetSandboxVersion = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setTargetSdkVersion(int paramInt) {
    this.targetSdkVersion = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setTaskAffinity(String paramString) {
    this.taskAffinity = paramString;
    return this;
  }
  
  public ParsingPackageImpl setTestOnly(boolean paramBoolean) {
    this.testOnly = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setTheme(int paramInt) {
    this.theme = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setUiOptions(int paramInt) {
    this.uiOptions = paramInt;
    return this;
  }
  
  public ParsingPackageImpl setUpgradeKeySets(Set<String> paramSet) {
    this.upgradeKeySets = paramSet;
    return this;
  }
  
  public ParsingPackageImpl setUse32BitAbi(boolean paramBoolean) {
    this.use32BitAbi = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setUseEmbeddedDex(boolean paramBoolean) {
    this.useEmbeddedDex = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setUsesCleartextTraffic(boolean paramBoolean) {
    this.usesCleartextTraffic = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setUsesNonSdkApi(boolean paramBoolean) {
    this.usesNonSdkApi = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setVersionName(String paramString) {
    this.versionName = paramString;
    return this;
  }
  
  public ParsingPackageImpl setVisibleToInstantApps(boolean paramBoolean) {
    this.visibleToInstantApps = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setVmSafeMode(boolean paramBoolean) {
    this.vmSafeMode = paramBoolean;
    return this;
  }
  
  public ParsingPackageImpl setVolumeUuid(String paramString) {
    this.volumeUuid = TextUtils.safeIntern(paramString);
    return this;
  }
  
  public ParsingPackageImpl setZygotePreloadName(String paramString) {
    this.zygotePreloadName = paramString;
    return this;
  }
  
  public ParsingPackageImpl sortActivities() {
    Collections.sort(this.activities, ORDER_COMPARATOR);
    return this;
  }
  
  public ParsingPackageImpl sortReceivers() {
    Collections.sort(this.receivers, ORDER_COMPARATOR);
    return this;
  }
  
  public ParsingPackageImpl sortServices() {
    Collections.sort(this.services, ORDER_COMPARATOR);
    return this;
  }
  
  @Deprecated
  public ApplicationInfo toAppInfoWithoutState() {
    ApplicationInfo applicationInfo = toAppInfoWithoutStateWithoutFlags();
    applicationInfo.flags = PackageInfoWithoutStateUtils.appInfoFlags(this);
    applicationInfo.privateFlags = PackageInfoWithoutStateUtils.appInfoPrivateFlags(this);
    return applicationInfo;
  }
  
  public ApplicationInfo toAppInfoWithoutStateWithoutFlags() {
    // Byte code:
    //   0: new android/content/pm/ApplicationInfo
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: aload_1
    //   9: aload_0
    //   10: getfield appComponentFactory : Ljava/lang/String;
    //   13: putfield appComponentFactory : Ljava/lang/String;
    //   16: aload_1
    //   17: aload_0
    //   18: getfield backupAgentName : Ljava/lang/String;
    //   21: putfield backupAgentName : Ljava/lang/String;
    //   24: aload_1
    //   25: aload_0
    //   26: getfield banner : I
    //   29: putfield banner : I
    //   32: aload_1
    //   33: aload_0
    //   34: getfield category : I
    //   37: putfield category : I
    //   40: aload_1
    //   41: aload_0
    //   42: getfield classLoaderName : Ljava/lang/String;
    //   45: putfield classLoaderName : Ljava/lang/String;
    //   48: aload_1
    //   49: aload_0
    //   50: getfield className : Ljava/lang/String;
    //   53: putfield className : Ljava/lang/String;
    //   56: aload_1
    //   57: aload_0
    //   58: getfield compatibleWidthLimitDp : I
    //   61: putfield compatibleWidthLimitDp : I
    //   64: aload_1
    //   65: aload_0
    //   66: getfield compileSdkVersion : I
    //   69: putfield compileSdkVersion : I
    //   72: aload_1
    //   73: aload_0
    //   74: getfield compileSdkVersionCodeName : Ljava/lang/String;
    //   77: putfield compileSdkVersionCodename : Ljava/lang/String;
    //   80: aload_1
    //   81: aload_0
    //   82: getfield descriptionRes : I
    //   85: putfield descriptionRes : I
    //   88: aload_1
    //   89: aload_0
    //   90: getfield enabled : Z
    //   93: putfield enabled : Z
    //   96: aload_1
    //   97: aload_0
    //   98: getfield fullBackupContent : I
    //   101: putfield fullBackupContent : I
    //   104: getstatic android/content/pm/PackageParser.sUseRoundIcon : Z
    //   107: ifeq -> 122
    //   110: aload_0
    //   111: getfield roundIconRes : I
    //   114: istore_2
    //   115: iload_2
    //   116: ifeq -> 122
    //   119: goto -> 127
    //   122: aload_0
    //   123: getfield iconRes : I
    //   126: istore_2
    //   127: aload_1
    //   128: iload_2
    //   129: putfield icon : I
    //   132: aload_1
    //   133: aload_0
    //   134: getfield iconRes : I
    //   137: putfield iconRes : I
    //   140: aload_1
    //   141: aload_0
    //   142: getfield roundIconRes : I
    //   145: putfield roundIconRes : I
    //   148: aload_1
    //   149: aload_0
    //   150: getfield installLocation : I
    //   153: putfield installLocation : I
    //   156: aload_1
    //   157: aload_0
    //   158: getfield labelRes : I
    //   161: putfield labelRes : I
    //   164: aload_1
    //   165: aload_0
    //   166: getfield largestWidthLimitDp : I
    //   169: putfield largestWidthLimitDp : I
    //   172: aload_1
    //   173: aload_0
    //   174: getfield logo : I
    //   177: putfield logo : I
    //   180: aload_1
    //   181: aload_0
    //   182: getfield manageSpaceActivityName : Ljava/lang/String;
    //   185: putfield manageSpaceActivityName : Ljava/lang/String;
    //   188: aload_1
    //   189: aload_0
    //   190: getfield maxAspectRatio : F
    //   193: putfield maxAspectRatio : F
    //   196: aload_1
    //   197: aload_0
    //   198: getfield metaData : Landroid/os/Bundle;
    //   201: putfield metaData : Landroid/os/Bundle;
    //   204: aload_1
    //   205: aload_0
    //   206: getfield minAspectRatio : F
    //   209: putfield minAspectRatio : F
    //   212: aload_1
    //   213: aload_0
    //   214: getfield minSdkVersion : I
    //   217: putfield minSdkVersion : I
    //   220: aload_1
    //   221: aload_0
    //   222: getfield className : Ljava/lang/String;
    //   225: putfield name : Ljava/lang/String;
    //   228: aload_1
    //   229: getfield name : Ljava/lang/String;
    //   232: ifnull -> 246
    //   235: aload_1
    //   236: aload_1
    //   237: getfield name : Ljava/lang/String;
    //   240: invokevirtual trim : ()Ljava/lang/String;
    //   243: putfield name : Ljava/lang/String;
    //   246: aload_1
    //   247: aload_0
    //   248: getfield networkSecurityConfigRes : I
    //   251: putfield networkSecurityConfigRes : I
    //   254: aload_1
    //   255: aload_0
    //   256: getfield nonLocalizedLabel : Ljava/lang/CharSequence;
    //   259: putfield nonLocalizedLabel : Ljava/lang/CharSequence;
    //   262: aload_1
    //   263: getfield nonLocalizedLabel : Ljava/lang/CharSequence;
    //   266: ifnull -> 285
    //   269: aload_1
    //   270: aload_1
    //   271: getfield nonLocalizedLabel : Ljava/lang/CharSequence;
    //   274: invokeinterface toString : ()Ljava/lang/String;
    //   279: invokevirtual trim : ()Ljava/lang/String;
    //   282: putfield nonLocalizedLabel : Ljava/lang/CharSequence;
    //   285: aload_1
    //   286: aload_0
    //   287: getfield packageName : Ljava/lang/String;
    //   290: putfield packageName : Ljava/lang/String;
    //   293: aload_1
    //   294: aload_0
    //   295: getfield permission : Ljava/lang/String;
    //   298: putfield permission : Ljava/lang/String;
    //   301: aload_1
    //   302: aload_0
    //   303: invokevirtual getProcessName : ()Ljava/lang/String;
    //   306: putfield processName : Ljava/lang/String;
    //   309: aload_1
    //   310: aload_0
    //   311: getfield requiresSmallestWidthDp : I
    //   314: putfield requiresSmallestWidthDp : I
    //   317: aload_1
    //   318: aload_0
    //   319: getfield splitClassLoaderNames : [Ljava/lang/String;
    //   322: putfield splitClassLoaderNames : [Ljava/lang/String;
    //   325: aload_1
    //   326: aload_0
    //   327: getfield splitDependencies : Landroid/util/SparseArray;
    //   330: putfield splitDependencies : Landroid/util/SparseArray;
    //   333: aload_1
    //   334: aload_0
    //   335: getfield splitNames : [Ljava/lang/String;
    //   338: putfield splitNames : [Ljava/lang/String;
    //   341: aload_1
    //   342: aload_0
    //   343: getfield volumeUuid : Ljava/lang/String;
    //   346: invokestatic convert : (Ljava/lang/String;)Ljava/util/UUID;
    //   349: putfield storageUuid : Ljava/util/UUID;
    //   352: aload_1
    //   353: aload_0
    //   354: getfield targetSandboxVersion : I
    //   357: putfield targetSandboxVersion : I
    //   360: aload_1
    //   361: aload_0
    //   362: getfield targetSdkVersion : I
    //   365: putfield targetSdkVersion : I
    //   368: aload_1
    //   369: aload_0
    //   370: getfield taskAffinity : Ljava/lang/String;
    //   373: putfield taskAffinity : Ljava/lang/String;
    //   376: aload_1
    //   377: aload_0
    //   378: getfield theme : I
    //   381: putfield theme : I
    //   384: aload_1
    //   385: aload_0
    //   386: getfield uiOptions : I
    //   389: putfield uiOptions : I
    //   392: aload_1
    //   393: aload_0
    //   394: getfield volumeUuid : Ljava/lang/String;
    //   397: putfield volumeUuid : Ljava/lang/String;
    //   400: aload_1
    //   401: aload_0
    //   402: getfield zygotePreloadName : Ljava/lang/String;
    //   405: putfield zygotePreloadName : Ljava/lang/String;
    //   408: aload_1
    //   409: aload_0
    //   410: invokevirtual isCrossProfile : ()Z
    //   413: putfield crossProfile : Z
    //   416: aload_1
    //   417: aload_0
    //   418: getfield gwpAsanMode : I
    //   421: invokevirtual setGwpAsanMode : (I)V
    //   424: aload_1
    //   425: aload_0
    //   426: getfield baseCodePath : Ljava/lang/String;
    //   429: invokevirtual setBaseCodePath : (Ljava/lang/String;)V
    //   432: aload_1
    //   433: aload_0
    //   434: getfield baseCodePath : Ljava/lang/String;
    //   437: invokevirtual setBaseResourcePath : (Ljava/lang/String;)V
    //   440: aload_1
    //   441: aload_0
    //   442: getfield codePath : Ljava/lang/String;
    //   445: invokevirtual setCodePath : (Ljava/lang/String;)V
    //   448: aload_1
    //   449: aload_0
    //   450: getfield codePath : Ljava/lang/String;
    //   453: invokevirtual setResourcePath : (Ljava/lang/String;)V
    //   456: aload_1
    //   457: aload_0
    //   458: getfield splitCodePaths : [Ljava/lang/String;
    //   461: invokevirtual setSplitCodePaths : ([Ljava/lang/String;)V
    //   464: aload_1
    //   465: aload_0
    //   466: getfield splitCodePaths : [Ljava/lang/String;
    //   469: invokevirtual setSplitResourcePaths : ([Ljava/lang/String;)V
    //   472: aload_1
    //   473: aload_0
    //   474: getfield versionCodeMajor : I
    //   477: aload_0
    //   478: getfield versionCode : I
    //   481: invokestatic composeLongVersionCode : (II)J
    //   484: invokevirtual setVersionCode : (J)V
    //   487: aload_1
    //   488: areturn
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Package{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.packageName);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    sForBoolean.parcel(this.supportsSmallScreens, paramParcel, paramInt);
    sForBoolean.parcel(this.supportsNormalScreens, paramParcel, paramInt);
    sForBoolean.parcel(this.supportsLargeScreens, paramParcel, paramInt);
    sForBoolean.parcel(this.supportsExtraLargeScreens, paramParcel, paramInt);
    sForBoolean.parcel(this.resizeable, paramParcel, paramInt);
    sForBoolean.parcel(this.anyDensity, paramParcel, paramInt);
    paramParcel.writeInt(this.versionCode);
    paramParcel.writeInt(this.versionCodeMajor);
    paramParcel.writeInt(this.baseRevisionCode);
    sForInternedString.parcel(this.versionName, paramParcel, paramInt);
    paramParcel.writeInt(this.compileSdkVersion);
    paramParcel.writeString(this.compileSdkVersionCodeName);
    sForInternedString.parcel(this.packageName, paramParcel, paramInt);
    paramParcel.writeString(this.realPackage);
    paramParcel.writeString(this.baseCodePath);
    paramParcel.writeBoolean(this.requiredForAllUsers);
    paramParcel.writeString(this.restrictedAccountType);
    paramParcel.writeString(this.requiredAccountType);
    sForInternedString.parcel(this.overlayTarget, paramParcel, paramInt);
    paramParcel.writeString(this.overlayTargetName);
    paramParcel.writeString(this.overlayCategory);
    paramParcel.writeInt(this.overlayPriority);
    paramParcel.writeBoolean(this.overlayIsStatic);
    sForInternedStringValueMap.parcel(this.overlayables, paramParcel, paramInt);
    sForInternedString.parcel(this.staticSharedLibName, paramParcel, paramInt);
    paramParcel.writeLong(this.staticSharedLibVersion);
    sForInternedStringList.parcel(this.libraryNames, paramParcel, paramInt);
    sForInternedStringList.parcel(this.usesLibraries, paramParcel, paramInt);
    sForInternedStringList.parcel(this.usesOptionalLibraries, paramParcel, paramInt);
    sForInternedStringList.parcel(this.usesStaticLibraries, paramParcel, paramInt);
    paramParcel.writeLongArray(this.usesStaticLibrariesVersions);
    String[][] arrayOfString = this.usesStaticLibrariesCertDigests;
    if (arrayOfString == null) {
      paramParcel.writeInt(-1);
    } else {
      paramParcel.writeInt(arrayOfString.length);
      byte b = 0;
      while (true) {
        arrayOfString = this.usesStaticLibrariesCertDigests;
        if (b < arrayOfString.length) {
          paramParcel.writeStringArray(arrayOfString[b]);
          b++;
          continue;
        } 
        break;
      } 
    } 
    sForInternedString.parcel(this.sharedUserId, paramParcel, paramInt);
    paramParcel.writeInt(this.sharedUserLabel);
    paramParcel.writeTypedList(this.configPreferences);
    paramParcel.writeTypedList(this.reqFeatures);
    paramParcel.writeTypedList(this.featureGroups);
    paramParcel.writeByteArray(this.restrictUpdateHash);
    paramParcel.writeStringList(this.originalPackages);
    sForInternedStringList.parcel(this.adoptPermissions, paramParcel, paramInt);
    sForInternedStringList.parcel(this.requestedPermissions, paramParcel, paramInt);
    sForInternedStringList.parcel(this.implicitPermissions, paramParcel, paramInt);
    sForStringSet.parcel(this.upgradeKeySets, paramParcel, paramInt);
    paramParcel.writeMap(this.keySetMapping);
    sForInternedStringList.parcel(this.protectedBroadcasts, paramParcel, paramInt);
    paramParcel.writeTypedList(this.activities);
    paramParcel.writeTypedList(this.receivers);
    paramParcel.writeTypedList(this.services);
    paramParcel.writeTypedList(this.providers);
    paramParcel.writeTypedList(this.attributions);
    paramParcel.writeTypedList(this.permissions);
    paramParcel.writeTypedList(this.permissionGroups);
    paramParcel.writeTypedList(this.instrumentations);
    sForIntentInfoPairs.parcel(this.preferredActivityFilters, paramParcel, paramInt);
    paramParcel.writeMap(this.processes);
    paramParcel.writeBundle(this.metaData);
    sForInternedString.parcel(this.volumeUuid, paramParcel, paramInt);
    paramParcel.writeParcelable((Parcelable)this.signingDetails, paramInt);
    paramParcel.writeString(this.codePath);
    paramParcel.writeBoolean(this.use32BitAbi);
    paramParcel.writeBoolean(this.visibleToInstantApps);
    paramParcel.writeBoolean(this.forceQueryable);
    paramParcel.writeParcelableList(this.queriesIntents, paramInt);
    sForInternedStringList.parcel(this.queriesPackages, paramParcel, paramInt);
    paramParcel.writeString(this.appComponentFactory);
    paramParcel.writeString(this.backupAgentName);
    paramParcel.writeInt(this.banner);
    paramParcel.writeInt(this.category);
    paramParcel.writeString(this.classLoaderName);
    paramParcel.writeString(this.className);
    paramParcel.writeInt(this.compatibleWidthLimitDp);
    paramParcel.writeInt(this.descriptionRes);
    paramParcel.writeBoolean(this.enabled);
    paramParcel.writeBoolean(this.crossProfile);
    paramParcel.writeInt(this.fullBackupContent);
    paramParcel.writeInt(this.iconRes);
    paramParcel.writeInt(this.installLocation);
    paramParcel.writeInt(this.labelRes);
    paramParcel.writeInt(this.largestWidthLimitDp);
    paramParcel.writeInt(this.logo);
    paramParcel.writeString(this.manageSpaceActivityName);
    paramParcel.writeFloat(this.maxAspectRatio);
    paramParcel.writeFloat(this.minAspectRatio);
    paramParcel.writeInt(this.minSdkVersion);
    paramParcel.writeInt(this.networkSecurityConfigRes);
    paramParcel.writeCharSequence(this.nonLocalizedLabel);
    paramParcel.writeString(this.permission);
    paramParcel.writeString(this.processName);
    paramParcel.writeInt(this.requiresSmallestWidthDp);
    paramParcel.writeInt(this.roundIconRes);
    paramParcel.writeInt(this.targetSandboxVersion);
    paramParcel.writeInt(this.targetSdkVersion);
    paramParcel.writeString(this.taskAffinity);
    paramParcel.writeInt(this.theme);
    paramParcel.writeInt(this.uiOptions);
    paramParcel.writeString(this.zygotePreloadName);
    paramParcel.writeStringArray(this.splitClassLoaderNames);
    paramParcel.writeStringArray(this.splitCodePaths);
    paramParcel.writeSparseArray(this.splitDependencies);
    paramParcel.writeIntArray(this.splitFlags);
    paramParcel.writeStringArray(this.splitNames);
    paramParcel.writeIntArray(this.splitRevisionCodes);
    paramParcel.writeBoolean(this.externalStorage);
    paramParcel.writeBoolean(this.baseHardwareAccelerated);
    paramParcel.writeBoolean(this.allowBackup);
    paramParcel.writeBoolean(this.killAfterRestore);
    paramParcel.writeBoolean(this.restoreAnyVersion);
    paramParcel.writeBoolean(this.fullBackupOnly);
    paramParcel.writeBoolean(this.persistent);
    paramParcel.writeBoolean(this.debuggable);
    paramParcel.writeBoolean(this.vmSafeMode);
    paramParcel.writeBoolean(this.hasCode);
    paramParcel.writeBoolean(this.allowTaskReparenting);
    paramParcel.writeBoolean(this.allowClearUserData);
    paramParcel.writeBoolean(this.largeHeap);
    paramParcel.writeBoolean(this.usesCleartextTraffic);
    paramParcel.writeBoolean(this.supportsRtl);
    paramParcel.writeBoolean(this.testOnly);
    paramParcel.writeBoolean(this.multiArch);
    paramParcel.writeBoolean(this.extractNativeLibs);
    paramParcel.writeBoolean(this.game);
    sForBoolean.parcel(this.resizeableActivity, paramParcel, paramInt);
    paramParcel.writeBoolean(this.staticSharedLibrary);
    paramParcel.writeBoolean(this.overlay);
    paramParcel.writeBoolean(this.isolatedSplitLoading);
    paramParcel.writeBoolean(this.hasDomainUrls);
    paramParcel.writeBoolean(this.profileableByShell);
    paramParcel.writeBoolean(this.backupInForeground);
    paramParcel.writeBoolean(this.useEmbeddedDex);
    paramParcel.writeBoolean(this.defaultToDeviceProtectedStorage);
    paramParcel.writeBoolean(this.directBootAware);
    paramParcel.writeBoolean(this.partiallyDirectBootAware);
    paramParcel.writeBoolean(this.resizeableActivityViaSdkVersion);
    paramParcel.writeBoolean(this.allowClearUserDataOnFailedRestore);
    paramParcel.writeBoolean(this.allowAudioPlaybackCapture);
    paramParcel.writeBoolean(this.requestLegacyExternalStorage);
    paramParcel.writeBoolean(this.usesNonSdkApi);
    paramParcel.writeBoolean(this.hasFragileUserData);
    paramParcel.writeBoolean(this.cantSaveState);
    paramParcel.writeBoolean(this.allowNativeHeapPointerTagging);
    paramParcel.writeInt(this.autoRevokePermissions);
    paramParcel.writeBoolean(this.preserveLegacyExternalStorage);
    paramParcel.writeArraySet(this.mimeGroups);
    paramParcel.writeInt(this.gwpAsanMode);
    paramParcel.writeSparseIntArray(this.minExtensionVersions);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/ParsingPackageImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
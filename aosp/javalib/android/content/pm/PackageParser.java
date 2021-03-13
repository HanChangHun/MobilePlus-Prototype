package android.content.pm;

import android.apex.ApexInfo;
import android.app.ActivityTaskManager;
import android.app.ActivityThread;
import android.app.ResourcesManager;
import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.pm.permission.SplitPermissionInfoParcelable;
import android.content.pm.split.DefaultSplitAssetLoader;
import android.content.pm.split.SplitAssetDependencyLoader;
import android.content.pm.split.SplitDependencyLoader;
import android.content.res.ApkAssets;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PatternMatcher;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.PackageUtils;
import android.util.Pair;
import android.util.Slog;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.apk.ApkSignatureVerifier;
import com.android.internal.R;
import com.android.internal.os.ClassLoaderFactory;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.XmlUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import libcore.io.IoUtils;
import libcore.util.EmptyArray;
import libcore.util.HexEncoding;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PackageParser {
  static {
    if (Build.IS_DEBUGGABLE && SystemProperties.getBoolean("persist.sys.child_packages_enabled", false)) {
      bool = true;
    } else {
      bool = false;
    } 
    MULTI_PACKAGE_APK_ENABLED = bool;
    ArraySet<String> arraySet = new ArraySet();
    CHILD_PACKAGE_TAGS = (Set<String>)arraySet;
    arraySet.add("application");
    CHILD_PACKAGE_TAGS.add("compatible-screens");
    CHILD_PACKAGE_TAGS.add("eat-comment");
    CHILD_PACKAGE_TAGS.add("feature-group");
    CHILD_PACKAGE_TAGS.add("instrumentation");
    CHILD_PACKAGE_TAGS.add("supports-screens");
    CHILD_PACKAGE_TAGS.add("supports-input");
    CHILD_PACKAGE_TAGS.add("uses-configuration");
    CHILD_PACKAGE_TAGS.add("uses-feature");
    CHILD_PACKAGE_TAGS.add("uses-gl-texture");
    CHILD_PACKAGE_TAGS.add("uses-permission");
    CHILD_PACKAGE_TAGS.add("uses-permission-sdk-23");
    CHILD_PACKAGE_TAGS.add("uses-permission-sdk-m");
    CHILD_PACKAGE_TAGS.add("uses-sdk");
    arraySet = new ArraySet();
    SAFE_BROADCASTS = (Set<String>)arraySet;
    arraySet.add("android.intent.action.BOOT_COMPLETED");
    NEW_PERMISSIONS = new NewPermissionInfo[] { new NewPermissionInfo("android.permission.WRITE_EXTERNAL_STORAGE", 4, 0), new NewPermissionInfo("android.permission.READ_PHONE_STATE", 4, 0) };
    SDK_VERSION = Build.VERSION.SDK_INT;
    SDK_CODENAMES = Build.VERSION.ACTIVE_CODENAMES;
    sCompatibilityModeEnabled = true;
    sUseRoundIcon = false;
    sSplitNameComparator = new SplitNameComparator();
  }
  
  public PackageParser() {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    this.mMetrics = displayMetrics;
    displayMetrics.setToDefaults();
  }
  
  private void adjustPackageToBeUnresizeableAndUnpipable(Package paramPackage) {
    for (Activity activity : paramPackage.activities) {
      activity.info.resizeMode = 0;
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags &= 0xFFBFFFFF;
    } 
  }
  
  public static String buildClassName(String paramString, CharSequence paramCharSequence, String[] paramArrayOfString) {
    if (paramCharSequence == null || paramCharSequence.length() <= 0) {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("Empty class name in package ");
      paramCharSequence.append(paramString);
      paramArrayOfString[0] = paramCharSequence.toString();
      return null;
    } 
    paramCharSequence = paramCharSequence.toString();
    if (paramCharSequence.charAt(0) == '.') {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append((String)paramCharSequence);
      return stringBuilder.toString();
    } 
    if (paramCharSequence.indexOf('.') < 0) {
      StringBuilder stringBuilder = new StringBuilder(paramString);
      stringBuilder.append('.');
      stringBuilder.append((String)paramCharSequence);
      return stringBuilder.toString();
    } 
    return (String)paramCharSequence;
  }
  
  private static String buildCompoundName(String paramString1, CharSequence paramCharSequence, String paramString2, String[] paramArrayOfString) {
    StringBuilder stringBuilder;
    paramCharSequence = paramCharSequence.toString();
    char c = paramCharSequence.charAt(0);
    if (paramString1 != null && c == ':') {
      if (paramCharSequence.length() < 2) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Bad ");
        stringBuilder1.append(paramString2);
        stringBuilder1.append(" name ");
        stringBuilder1.append((String)paramCharSequence);
        stringBuilder1.append(" in package ");
        stringBuilder1.append(paramString1);
        stringBuilder1.append(": must be at least two characters");
        paramArrayOfString[0] = stringBuilder1.toString();
        return null;
      } 
      String str1 = validateName(paramCharSequence.substring(1), false, false);
      if (str1 != null) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Invalid ");
        stringBuilder1.append(paramString2);
        stringBuilder1.append(" name ");
        stringBuilder1.append((String)paramCharSequence);
        stringBuilder1.append(" in package ");
        stringBuilder1.append(paramString1);
        stringBuilder1.append(": ");
        stringBuilder1.append(str1);
        paramArrayOfString[0] = stringBuilder1.toString();
        return null;
      } 
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString1);
      stringBuilder.append((String)paramCharSequence);
      return stringBuilder.toString();
    } 
    String str = validateName((String)paramCharSequence, true, false);
    if (str != null && !"system".equals(paramCharSequence)) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Invalid ");
      stringBuilder1.append((String)stringBuilder);
      stringBuilder1.append(" name ");
      stringBuilder1.append((String)paramCharSequence);
      stringBuilder1.append(" in package ");
      stringBuilder1.append(paramString1);
      stringBuilder1.append(": ");
      stringBuilder1.append(str);
      paramArrayOfString[0] = stringBuilder1.toString();
      return null;
    } 
    return (String)paramCharSequence;
  }
  
  public static String buildProcessName(String paramString1, String paramString2, CharSequence paramCharSequence, int paramInt, String[] paramArrayOfString1, String[] paramArrayOfString2) {
    if ((paramInt & 0x2) != 0 && !"system".equals(paramCharSequence)) {
      if (paramString2 != null)
        paramString1 = paramString2; 
      return paramString1;
    } 
    if (paramArrayOfString1 != null)
      for (paramInt = paramArrayOfString1.length - 1; paramInt >= 0; paramInt--) {
        String str = paramArrayOfString1[paramInt];
        if (str.equals(paramString1) || str.equals(paramString2) || str.equals(paramCharSequence))
          return paramString1; 
      }  
    return (paramCharSequence == null || paramCharSequence.length() <= 0) ? paramString2 : TextUtils.safeIntern(buildCompoundName(paramString1, paramCharSequence, "process", paramArrayOfString2));
  }
  
  public static String buildTaskAffinityName(String paramString1, String paramString2, CharSequence paramCharSequence, String[] paramArrayOfString) {
    return (paramCharSequence == null) ? paramString2 : ((paramCharSequence.length() <= 0) ? null : buildCompoundName(paramString1, paramCharSequence, "taskAffinity", paramArrayOfString));
  }
  
  public static boolean checkRequiredSystemProperties(String paramString1, String paramString2) {
    StringBuilder stringBuilder;
    if (TextUtils.isEmpty(paramString1) || TextUtils.isEmpty(paramString2)) {
      if (!TextUtils.isEmpty(paramString1) || !TextUtils.isEmpty(paramString2)) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Disabling overlay - incomplete property :'");
        stringBuilder.append(paramString1);
        stringBuilder.append("=");
        stringBuilder.append(paramString2);
        stringBuilder.append("' - require both requiredSystemPropertyName AND requiredSystemPropertyValue to be specified.");
        Slog.w("PackageParser", stringBuilder.toString());
        return false;
      } 
      return true;
    } 
    String[] arrayOfString1 = paramString1.split(",");
    String[] arrayOfString2 = paramString2.split(",");
    if (arrayOfString1.length != arrayOfString2.length) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Disabling overlay - property :'");
      stringBuilder.append(paramString1);
      stringBuilder.append("=");
      stringBuilder.append(paramString2);
      stringBuilder.append("' - require both requiredSystemPropertyName AND requiredSystemPropertyValue lists to have the same size.");
      Slog.w("PackageParser", stringBuilder.toString());
      return false;
    } 
    for (byte b = 0; b < arrayOfString1.length; b++) {
      if (!TextUtils.equals(SystemProperties.get(arrayOfString1[b]), stringBuilder[b]))
        return false; 
    } 
    return true;
  }
  
  private static boolean checkUseInstalledOrHidden(int paramInt, PackageUserState paramPackageUserState, ApplicationInfo paramApplicationInfo) {
    boolean bool = false;
    if ((paramInt & 0x20000000) == 0 && !paramPackageUserState.installed && paramApplicationInfo != null && paramApplicationInfo.hiddenUntilInstalled)
      return false; 
    if (paramPackageUserState.isAvailable(paramInt) || (paramApplicationInfo != null && paramApplicationInfo.isSystemApp() && ((0x402000 & paramInt) != 0 || (0x20000000 & paramInt) != 0)))
      bool = true; 
    return bool;
  }
  
  private static void collectCertificates(Package paramPackage, File paramFile, boolean paramBoolean) throws PackageParserException {
    SigningDetails signingDetails;
    String str = paramFile.getAbsolutePath();
    int i = ApkSignatureVerifier.getMinimumSignatureSchemeVersionForTargetSdk(paramPackage.applicationInfo.targetSdkVersion);
    if (paramPackage.applicationInfo.isStaticSharedLibrary())
      i = 2; 
    if (paramBoolean) {
      signingDetails = ApkSignatureVerifier.unsafeGetCertsWithoutVerification(str, 1);
    } else {
      signingDetails = ApkSignatureVerifier.verify(str, i);
    } 
    if (paramPackage.mSigningDetails == SigningDetails.UNKNOWN) {
      paramPackage.mSigningDetails = signingDetails;
    } else if (!Signature.areExactMatch(paramPackage.mSigningDetails.signatures, signingDetails.signatures)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(" has mismatched certificates");
      throw new PackageParserException(-104, stringBuilder.toString());
    } 
  }
  
  public static void collectCertificates(Package paramPackage, boolean paramBoolean) throws PackageParserException {
    byte b1;
    collectCertificatesInternal(paramPackage, paramBoolean);
    if (paramPackage.childPackages != null) {
      b1 = paramPackage.childPackages.size();
    } else {
      b1 = 0;
    } 
    for (byte b2 = 0; b2 < b1; b2++)
      ((Package)paramPackage.childPackages.get(b2)).mSigningDetails = paramPackage.mSigningDetails; 
  }
  
  private static void collectCertificatesInternal(Package paramPackage, boolean paramBoolean) throws PackageParserException {
    paramPackage.mSigningDetails = SigningDetails.UNKNOWN;
    Trace.traceBegin(262144L, "collectCertificates");
    try {
      File file = new File();
      this(paramPackage.baseCodePath);
      collectCertificates(paramPackage, file, paramBoolean);
      if (!ArrayUtils.isEmpty((Object[])paramPackage.splitCodePaths))
        for (byte b = 0; b < paramPackage.splitCodePaths.length; b++) {
          file = new File();
          this(paramPackage.splitCodePaths[b]);
          collectCertificates(paramPackage, file, paramBoolean);
        }  
      return;
    } finally {
      Trace.traceEnd(262144L);
    } 
  }
  
  public static int computeMinSdkVersion(int paramInt1, String paramString, int paramInt2, String[] paramArrayOfString1, String[] paramArrayOfString2) {
    StringBuilder stringBuilder;
    if (paramString == null) {
      if (paramInt1 <= paramInt2)
        return paramInt1; 
      stringBuilder = new StringBuilder();
      stringBuilder.append("Requires newer sdk version #");
      stringBuilder.append(paramInt1);
      stringBuilder.append(" (current version is #");
      stringBuilder.append(paramInt2);
      stringBuilder.append(")");
      paramArrayOfString2[0] = stringBuilder.toString();
      return -1;
    } 
    if (matchTargetCode(paramArrayOfString1, (String)stringBuilder))
      return 10000; 
    if (paramArrayOfString1.length > 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Requires development platform ");
      stringBuilder1.append((String)stringBuilder);
      stringBuilder1.append(" (current platform is any of ");
      stringBuilder1.append(Arrays.toString((Object[])paramArrayOfString1));
      stringBuilder1.append(")");
      paramArrayOfString2[0] = stringBuilder1.toString();
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Requires development platform ");
      stringBuilder1.append((String)stringBuilder);
      stringBuilder1.append(" but this is a release platform.");
      paramArrayOfString2[0] = stringBuilder1.toString();
    } 
    return -1;
  }
  
  public static int computeTargetSdkVersion(int paramInt, String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2) {
    if (paramString == null)
      return paramInt; 
    if (matchTargetCode(paramArrayOfString1, paramString))
      return 10000; 
    if (paramArrayOfString1.length > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requires development platform ");
      stringBuilder.append(paramString);
      stringBuilder.append(" (current platform is any of ");
      stringBuilder.append(Arrays.toString((Object[])paramArrayOfString1));
      stringBuilder.append(")");
      paramArrayOfString2[0] = stringBuilder.toString();
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requires development platform ");
      stringBuilder.append(paramString);
      stringBuilder.append(" but this is a release platform.");
      paramArrayOfString2[0] = stringBuilder.toString();
    } 
    return -1;
  }
  
  private static boolean copyNeeded(int paramInt1, Package paramPackage, PackageUserState paramPackageUserState, Bundle paramBundle, int paramInt2) {
    boolean bool;
    if (paramInt2 != 0)
      return true; 
    if (paramPackageUserState.enabled != 0) {
      if (paramPackageUserState.enabled == 1) {
        bool = true;
      } else {
        bool = false;
      } 
      if (paramPackage.applicationInfo.enabled != bool)
        return true; 
    } 
    if ((paramPackage.applicationInfo.flags & 0x40000000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return (paramPackageUserState.suspended != bool) ? true : ((!paramPackageUserState.installed || paramPackageUserState.hidden) ? true : (paramPackageUserState.stopped ? true : ((paramPackageUserState.instantApp != paramPackage.applicationInfo.isInstantApp()) ? true : (((paramInt1 & 0x80) != 0 && (paramBundle != null || paramPackage.mAppMetaData != null)) ? true : (((paramInt1 & 0x400) != 0 && paramPackage.usesLibraryFiles != null) ? true : (((paramInt1 & 0x400) != 0 && paramPackage.usesLibraryInfos != null) ? true : ((paramPackage.staticSharedLibName != null))))))));
  }
  
  public static final ActivityInfo generateActivityInfo(ActivityInfo paramActivityInfo, int paramInt1, PackageUserState paramPackageUserState, int paramInt2) {
    if (paramActivityInfo == null)
      return null; 
    if (!checkUseInstalledOrHidden(paramInt1, paramPackageUserState, paramActivityInfo.applicationInfo))
      return null; 
    paramActivityInfo = new ActivityInfo(paramActivityInfo);
    paramActivityInfo.applicationInfo = generateApplicationInfo(paramActivityInfo.applicationInfo, paramInt1, paramPackageUserState, paramInt2);
    return paramActivityInfo;
  }
  
  public static final ActivityInfo generateActivityInfo(Activity paramActivity, int paramInt1, PackageUserState paramPackageUserState, int paramInt2) {
    if (paramActivity == null)
      return null; 
    if (!checkUseInstalledOrHidden(paramInt1, paramPackageUserState, paramActivity.owner.applicationInfo))
      return null; 
    if (!copyNeeded(paramInt1, paramActivity.owner, paramPackageUserState, paramActivity.metaData, paramInt2)) {
      updateApplicationInfo(paramActivity.info.applicationInfo, paramInt1, paramPackageUserState);
      return paramActivity.info;
    } 
    ActivityInfo activityInfo = new ActivityInfo(paramActivity.info);
    activityInfo.metaData = paramActivity.metaData;
    activityInfo.applicationInfo = generateApplicationInfo(paramActivity.owner, paramInt1, paramPackageUserState, paramInt2);
    return activityInfo;
  }
  
  private Activity generateAppDetailsHiddenActivity(Package paramPackage, int paramInt, String[] paramArrayOfString, boolean paramBoolean) {
    Activity activity = new Activity(paramPackage, PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME, new ActivityInfo());
    activity.owner = paramPackage;
    activity.setPackageName(paramPackage.packageName);
    activity.info.theme = 16973909;
    activity.info.exported = true;
    activity.info.name = PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME;
    activity.info.processName = paramPackage.applicationInfo.processName;
    activity.info.uiOptions = activity.info.applicationInfo.uiOptions;
    activity.info.taskAffinity = buildTaskAffinityName(paramPackage.packageName, paramPackage.packageName, ":app_details", paramArrayOfString);
    activity.info.enabled = true;
    activity.info.launchMode = 0;
    activity.info.documentLaunchMode = 0;
    activity.info.maxRecents = ActivityTaskManager.getDefaultAppRecentsLimitStatic();
    activity.info.configChanges = getActivityConfigChanges(0, 0);
    activity.info.softInputMode = 0;
    activity.info.persistableMode = 1;
    activity.info.screenOrientation = -1;
    activity.info.resizeMode = 4;
    activity.info.lockTaskLaunchMode = 0;
    activity.info.directBootAware = false;
    activity.info.rotationAnimation = -1;
    activity.info.colorMode = 0;
    if (paramBoolean) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x200;
    } 
    return activity;
  }
  
  public static ApplicationInfo generateApplicationInfo(ApplicationInfo paramApplicationInfo, int paramInt1, PackageUserState paramPackageUserState, int paramInt2) {
    if (paramApplicationInfo == null)
      return null; 
    if (!checkUseInstalledOrHidden(paramInt1, paramPackageUserState, paramApplicationInfo))
      return null; 
    paramApplicationInfo = new ApplicationInfo(paramApplicationInfo);
    paramApplicationInfo.initForUser(paramInt2);
    if (paramPackageUserState.stopped) {
      paramApplicationInfo.flags |= 0x200000;
    } else {
      paramApplicationInfo.flags &= 0xFFDFFFFF;
    } 
    updateApplicationInfo(paramApplicationInfo, paramInt1, paramPackageUserState);
    return paramApplicationInfo;
  }
  
  public static ApplicationInfo generateApplicationInfo(Package paramPackage, int paramInt, PackageUserState paramPackageUserState) {
    return generateApplicationInfo(paramPackage, paramInt, paramPackageUserState, UserHandle.getCallingUserId());
  }
  
  public static ApplicationInfo generateApplicationInfo(Package paramPackage, int paramInt1, PackageUserState paramPackageUserState, int paramInt2) {
    if (paramPackage == null)
      return null; 
    if (!checkUseInstalledOrHidden(paramInt1, paramPackageUserState, paramPackage.applicationInfo) || !paramPackage.isMatch(paramInt1))
      return null; 
    if (!copyNeeded(paramInt1, paramPackage, paramPackageUserState, null, paramInt2) && ((0x8000 & paramInt1) == 0 || paramPackageUserState.enabled != 4)) {
      updateApplicationInfo(paramPackage.applicationInfo, paramInt1, paramPackageUserState);
      return paramPackage.applicationInfo;
    } 
    ApplicationInfo applicationInfo = new ApplicationInfo(paramPackage.applicationInfo);
    applicationInfo.initForUser(paramInt2);
    if ((paramInt1 & 0x80) != 0)
      applicationInfo.metaData = paramPackage.mAppMetaData; 
    if ((paramInt1 & 0x400) != 0) {
      applicationInfo.sharedLibraryFiles = paramPackage.usesLibraryFiles;
      applicationInfo.sharedLibraryInfos = paramPackage.usesLibraryInfos;
    } 
    if (paramPackageUserState.stopped) {
      applicationInfo.flags |= 0x200000;
    } else {
      applicationInfo.flags &= 0xFFDFFFFF;
    } 
    updateApplicationInfo(applicationInfo, paramInt1, paramPackageUserState);
    return applicationInfo;
  }
  
  public static final InstrumentationInfo generateInstrumentationInfo(Instrumentation paramInstrumentation, int paramInt) {
    if (paramInstrumentation == null)
      return null; 
    if ((paramInt & 0x80) == 0)
      return paramInstrumentation.info; 
    InstrumentationInfo instrumentationInfo = new InstrumentationInfo(paramInstrumentation.info);
    instrumentationInfo.metaData = paramInstrumentation.metaData;
    return instrumentationInfo;
  }
  
  public static PackageInfo generatePackageInfo(Package paramPackage, ApexInfo paramApexInfo, int paramInt) {
    return generatePackageInfo(paramPackage, paramApexInfo, EmptyArray.INT, paramInt, 0L, 0L, Collections.emptySet(), new PackageUserState(), UserHandle.getCallingUserId());
  }
  
  private static PackageInfo generatePackageInfo(Package paramPackage, ApexInfo paramApexInfo, int[] paramArrayOfint, int paramInt1, long paramLong1, long paramLong2, Set<String> paramSet, PackageUserState paramPackageUserState, int paramInt2) {
    if (!checkUseInstalledOrHidden(paramInt1, paramPackageUserState, paramPackage.applicationInfo) || !paramPackage.isMatch(paramInt1))
      return null; 
    PackageInfo packageInfo = new PackageInfo();
    packageInfo.packageName = paramPackage.packageName;
    packageInfo.splitNames = paramPackage.splitNames;
    packageInfo.versionCode = paramPackage.mVersionCode;
    packageInfo.versionCodeMajor = paramPackage.mVersionCodeMajor;
    packageInfo.baseRevisionCode = paramPackage.baseRevisionCode;
    packageInfo.splitRevisionCodes = paramPackage.splitRevisionCodes;
    packageInfo.versionName = paramPackage.mVersionName;
    packageInfo.sharedUserId = paramPackage.mSharedUserId;
    packageInfo.sharedUserLabel = paramPackage.mSharedUserLabel;
    packageInfo.applicationInfo = generateApplicationInfo(paramPackage, paramInt1, paramPackageUserState, paramInt2);
    packageInfo.installLocation = paramPackage.installLocation;
    packageInfo.isStub = paramPackage.isStub;
    packageInfo.coreApp = paramPackage.coreApp;
    if ((packageInfo.applicationInfo.flags & 0x1) != 0 || (packageInfo.applicationInfo.flags & 0x80) != 0)
      packageInfo.requiredForAllUsers = paramPackage.mRequiredForAllUsers; 
    packageInfo.restrictedAccountType = paramPackage.mRestrictedAccountType;
    packageInfo.requiredAccountType = paramPackage.mRequiredAccountType;
    packageInfo.overlayTarget = paramPackage.mOverlayTarget;
    packageInfo.targetOverlayableName = paramPackage.mOverlayTargetName;
    packageInfo.overlayCategory = paramPackage.mOverlayCategory;
    packageInfo.overlayPriority = paramPackage.mOverlayPriority;
    packageInfo.mOverlayIsStatic = paramPackage.mOverlayIsStatic;
    packageInfo.compileSdkVersion = paramPackage.mCompileSdkVersion;
    packageInfo.compileSdkVersionCodename = paramPackage.mCompileSdkVersionCodename;
    packageInfo.firstInstallTime = paramLong1;
    packageInfo.lastUpdateTime = paramLong2;
    if ((paramInt1 & 0x100) != 0)
      packageInfo.gids = paramArrayOfint; 
    if ((paramInt1 & 0x4000) != 0) {
      int i;
      if (paramPackage.configPreferences != null) {
        i = paramPackage.configPreferences.size();
      } else {
        i = 0;
      } 
      if (i) {
        packageInfo.configPreferences = new ConfigurationInfo[i];
        paramPackage.configPreferences.toArray(packageInfo.configPreferences);
      } 
      if (paramPackage.reqFeatures != null) {
        i = paramPackage.reqFeatures.size();
      } else {
        i = 0;
      } 
      if (i > 0) {
        packageInfo.reqFeatures = new FeatureInfo[i];
        paramPackage.reqFeatures.toArray(packageInfo.reqFeatures);
      } 
      if (paramPackage.featureGroups != null) {
        i = paramPackage.featureGroups.size();
      } else {
        i = 0;
      } 
      if (i > 0) {
        packageInfo.featureGroups = new FeatureGroupInfo[i];
        paramPackage.featureGroups.toArray(packageInfo.featureGroups);
      } 
    } 
    if ((paramInt1 & 0x1) != 0) {
      int i = paramPackage.activities.size();
      if (i > 0) {
        int j = 0;
        ActivityInfo[] arrayOfActivityInfo = new ActivityInfo[i];
        byte b = 0;
        while (b < i) {
          Activity activity = paramPackage.activities.get(b);
          int k = j;
          if (paramPackageUserState.isMatch(activity.info, paramInt1))
            if (PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(activity.className)) {
              k = j;
            } else {
              arrayOfActivityInfo[j] = generateActivityInfo(activity, paramInt1, paramPackageUserState, paramInt2);
              k = j + 1;
            }  
          b++;
          j = k;
        } 
        packageInfo.activities = (ActivityInfo[])ArrayUtils.trimToSize((Object[])arrayOfActivityInfo, j);
      } 
    } 
    if ((paramInt1 & 0x2) != 0) {
      int i = paramPackage.receivers.size();
      if (i > 0) {
        int j = 0;
        ActivityInfo[] arrayOfActivityInfo = new ActivityInfo[i];
        byte b = 0;
        while (b < i) {
          Activity activity = paramPackage.receivers.get(b);
          int k = j;
          if (paramPackageUserState.isMatch(activity.info, paramInt1)) {
            arrayOfActivityInfo[j] = generateActivityInfo(activity, paramInt1, paramPackageUserState, paramInt2);
            k = j + 1;
          } 
          b++;
          j = k;
        } 
        packageInfo.receivers = (ActivityInfo[])ArrayUtils.trimToSize((Object[])arrayOfActivityInfo, j);
      } 
    } 
    if ((paramInt1 & 0x4) != 0) {
      int i = paramPackage.services.size();
      if (i > 0) {
        int j = 0;
        ServiceInfo[] arrayOfServiceInfo = new ServiceInfo[i];
        byte b = 0;
        while (b < i) {
          Service service = paramPackage.services.get(b);
          int k = j;
          if (paramPackageUserState.isMatch(service.info, paramInt1)) {
            arrayOfServiceInfo[j] = generateServiceInfo(service, paramInt1, paramPackageUserState, paramInt2);
            k = j + 1;
          } 
          b++;
          j = k;
        } 
        packageInfo.services = (ServiceInfo[])ArrayUtils.trimToSize((Object[])arrayOfServiceInfo, j);
      } 
    } 
    if ((paramInt1 & 0x8) != 0) {
      int i = paramPackage.providers.size();
      if (i > 0) {
        int j = 0;
        ProviderInfo[] arrayOfProviderInfo = new ProviderInfo[i];
        byte b = 0;
        while (b < i) {
          Provider provider = paramPackage.providers.get(b);
          int k = j;
          if (paramPackageUserState.isMatch(provider.info, paramInt1)) {
            arrayOfProviderInfo[j] = generateProviderInfo(provider, paramInt1, paramPackageUserState, paramInt2);
            k = j + 1;
          } 
          b++;
          j = k;
        } 
        packageInfo.providers = (ProviderInfo[])ArrayUtils.trimToSize((Object[])arrayOfProviderInfo, j);
      } 
    } 
    if ((paramInt1 & 0x10) != 0) {
      int i = paramPackage.instrumentation.size();
      if (i > 0) {
        packageInfo.instrumentation = new InstrumentationInfo[i];
        for (paramInt2 = 0; paramInt2 < i; paramInt2++)
          packageInfo.instrumentation[paramInt2] = generateInstrumentationInfo(paramPackage.instrumentation.get(paramInt2), paramInt1); 
      } 
    } 
    if ((paramInt1 & 0x1000) != 0) {
      int i = paramPackage.permissions.size();
      if (i > 0) {
        packageInfo.permissions = new PermissionInfo[i];
        for (paramInt2 = 0; paramInt2 < i; paramInt2++)
          packageInfo.permissions[paramInt2] = generatePermissionInfo(paramPackage.permissions.get(paramInt2), paramInt1); 
      } 
      i = paramPackage.requestedPermissions.size();
      if (i > 0) {
        packageInfo.requestedPermissions = new String[i];
        packageInfo.requestedPermissionsFlags = new int[i];
        for (paramInt2 = 0; paramInt2 < i; paramInt2++) {
          String str = paramPackage.requestedPermissions.get(paramInt2);
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
    if ((paramInt1 & 0x40) != 0)
      if (paramPackage.mSigningDetails.hasPastSigningCertificates()) {
        packageInfo.signatures = new Signature[1];
        packageInfo.signatures[0] = paramPackage.mSigningDetails.pastSigningCertificates[0];
      } else if (paramPackage.mSigningDetails.hasSignatures()) {
        paramInt2 = paramPackage.mSigningDetails.signatures.length;
        packageInfo.signatures = new Signature[paramInt2];
        System.arraycopy(paramPackage.mSigningDetails.signatures, 0, packageInfo.signatures, 0, paramInt2);
      }  
    if ((0x8000000 & paramInt1) != 0)
      if (paramPackage.mSigningDetails != SigningDetails.UNKNOWN) {
        packageInfo.signingInfo = new SigningInfo(paramPackage.mSigningDetails);
      } else {
        packageInfo.signingInfo = null;
      }  
    return packageInfo;
  }
  
  public static PackageInfo generatePackageInfo(Package paramPackage, int[] paramArrayOfint, int paramInt, long paramLong1, long paramLong2, Set<String> paramSet, PackageUserState paramPackageUserState) {
    return generatePackageInfo(paramPackage, paramArrayOfint, paramInt, paramLong1, paramLong2, paramSet, paramPackageUserState, UserHandle.getCallingUserId());
  }
  
  public static PackageInfo generatePackageInfo(Package paramPackage, int[] paramArrayOfint, int paramInt1, long paramLong1, long paramLong2, Set<String> paramSet, PackageUserState paramPackageUserState, int paramInt2) {
    return generatePackageInfo(paramPackage, null, paramArrayOfint, paramInt1, paramLong1, paramLong2, paramSet, paramPackageUserState, paramInt2);
  }
  
  public static final PermissionGroupInfo generatePermissionGroupInfo(PermissionGroup paramPermissionGroup, int paramInt) {
    if (paramPermissionGroup == null)
      return null; 
    if ((paramInt & 0x80) == 0)
      return paramPermissionGroup.info; 
    PermissionGroupInfo permissionGroupInfo = new PermissionGroupInfo(paramPermissionGroup.info);
    permissionGroupInfo.metaData = paramPermissionGroup.metaData;
    return permissionGroupInfo;
  }
  
  public static final PermissionInfo generatePermissionInfo(Permission paramPermission, int paramInt) {
    if (paramPermission == null)
      return null; 
    if ((paramInt & 0x80) == 0)
      return paramPermission.info; 
    PermissionInfo permissionInfo = new PermissionInfo(paramPermission.info);
    permissionInfo.metaData = paramPermission.metaData;
    return permissionInfo;
  }
  
  public static final ProviderInfo generateProviderInfo(Provider paramProvider, int paramInt1, PackageUserState paramPackageUserState, int paramInt2) {
    if (paramProvider == null)
      return null; 
    if (!checkUseInstalledOrHidden(paramInt1, paramPackageUserState, paramProvider.owner.applicationInfo))
      return null; 
    if (!copyNeeded(paramInt1, paramProvider.owner, paramPackageUserState, paramProvider.metaData, paramInt2) && ((paramInt1 & 0x800) != 0 || paramProvider.info.uriPermissionPatterns == null)) {
      updateApplicationInfo(paramProvider.info.applicationInfo, paramInt1, paramPackageUserState);
      return paramProvider.info;
    } 
    ProviderInfo providerInfo = new ProviderInfo(paramProvider.info);
    providerInfo.metaData = paramProvider.metaData;
    if ((paramInt1 & 0x800) == 0)
      providerInfo.uriPermissionPatterns = null; 
    providerInfo.applicationInfo = generateApplicationInfo(paramProvider.owner, paramInt1, paramPackageUserState, paramInt2);
    return providerInfo;
  }
  
  public static final ServiceInfo generateServiceInfo(Service paramService, int paramInt1, PackageUserState paramPackageUserState, int paramInt2) {
    if (paramService == null)
      return null; 
    if (!checkUseInstalledOrHidden(paramInt1, paramPackageUserState, paramService.owner.applicationInfo))
      return null; 
    if (!copyNeeded(paramInt1, paramService.owner, paramPackageUserState, paramService.metaData, paramInt2)) {
      updateApplicationInfo(paramService.info.applicationInfo, paramInt1, paramPackageUserState);
      return paramService.info;
    } 
    ServiceInfo serviceInfo = new ServiceInfo(paramService.info);
    serviceInfo.metaData = paramService.metaData;
    serviceInfo.applicationInfo = generateApplicationInfo(paramService.owner, paramInt1, paramPackageUserState, paramInt2);
    return serviceInfo;
  }
  
  public static int getActivityConfigChanges(int paramInt1, int paramInt2) {
    return paramInt2 & 0x3 | paramInt1;
  }
  
  private static boolean hasDomainURLs(Package paramPackage) {
    if (paramPackage == null || paramPackage.activities == null)
      return false; 
    ArrayList<Activity> arrayList = paramPackage.activities;
    int i = arrayList.size();
    for (byte b = 0; b < i; b++) {
      ArrayList<ActivityIntentInfo> arrayList1 = ((Activity)arrayList.get(b)).intents;
      if (arrayList1 != null) {
        int j = arrayList1.size();
        for (byte b1 = 0; b1 < j; b1++) {
          ActivityIntentInfo activityIntentInfo = arrayList1.get(b1);
          if (activityIntentInfo.hasAction("android.intent.action.VIEW") && activityIntentInfo.hasAction("android.intent.action.VIEW") && (activityIntentInfo.hasDataScheme("http") || activityIntentInfo.hasDataScheme("https")))
            return true; 
        } 
      } 
    } 
    return false;
  }
  
  public static final boolean isApkFile(File paramFile) {
    return isApkPath(paramFile.getName());
  }
  
  public static boolean isApkPath(String paramString) {
    return paramString.endsWith(".apk");
  }
  
  public static boolean isAvailable(PackageUserState paramPackageUserState) {
    return checkUseInstalledOrHidden(0, paramPackageUserState, null);
  }
  
  private boolean isImplicitlyExposedIntent(IntentInfo paramIntentInfo) {
    return (paramIntentInfo.hasCategory("android.intent.category.BROWSABLE") || paramIntentInfo.hasAction("android.intent.action.SEND") || paramIntentInfo.hasAction("android.intent.action.SENDTO") || paramIntentInfo.hasAction("android.intent.action.SEND_MULTIPLE"));
  }
  
  private static boolean matchTargetCode(String[] paramArrayOfString, String paramString) {
    int i = paramString.indexOf('.');
    if (i != -1)
      paramString = paramString.substring(0, i); 
    return ArrayUtils.contains((Object[])paramArrayOfString, paramString);
  }
  
  private static AssetManager newConfiguredAssetManager() {
    AssetManager assetManager = new AssetManager();
    assetManager.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Build.VERSION.RESOURCES_SDK_INT);
    return assetManager;
  }
  
  private Activity parseActivity(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, String[] paramArrayOfString, CachedComponentArgs paramCachedComponentArgs, boolean paramBoolean1, boolean paramBoolean2) throws XmlPullParserException, IOException {
    boolean bool1;
    Package package_ = paramPackage;
    XmlResourceParser xmlResourceParser2 = paramXmlResourceParser;
    String[] arrayOfString = paramArrayOfString;
    TypedArray typedArray2 = paramResources.obtainAttributes((AttributeSet)xmlResourceParser2, R.styleable.AndroidManifestActivity);
    if (paramCachedComponentArgs.mActivityArgs == null)
      paramCachedComponentArgs.mActivityArgs = new ParseComponentArgs(paramPackage, paramArrayOfString, 3, 1, 2, 44, 23, 30, this.mSeparateProcesses, 7, 17, 5); 
    ParseComponentArgs parseComponentArgs = paramCachedComponentArgs.mActivityArgs;
    if (paramBoolean1) {
      str1 = "<receiver>";
    } else {
      str1 = "<activity>";
    } 
    parseComponentArgs.tag = str1;
    paramCachedComponentArgs.mActivityArgs.sa = typedArray2;
    paramCachedComponentArgs.mActivityArgs.flags = paramInt;
    Activity activity = new Activity(paramCachedComponentArgs.mActivityArgs, new ActivityInfo());
    if (arrayOfString[0] != null) {
      typedArray2.recycle();
      return null;
    } 
    boolean bool = typedArray2.hasValue(6);
    if (bool)
      activity.info.exported = typedArray2.getBoolean(6, false); 
    activity.info.theme = typedArray2.getResourceId(0, 0);
    activity.info.uiOptions = typedArray2.getInt(26, activity.info.applicationInfo.uiOptions);
    String str1 = typedArray2.getNonConfigurationString(27, 1024);
    if (str1 != null) {
      String str = buildClassName(activity.info.packageName, str1, arrayOfString);
      if (arrayOfString[0] == null) {
        activity.info.parentActivityName = str;
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Activity ");
        stringBuilder.append(activity.info.name);
        stringBuilder.append(" specified invalid parentActivityName ");
        stringBuilder.append(str1);
        Log.e("PackageParser", stringBuilder.toString());
        arrayOfString[0] = null;
      } 
    } 
    str1 = typedArray2.getNonConfigurationString(4, 0);
    if (str1 == null) {
      activity.info.permission = package_.applicationInfo.permission;
    } else {
      ActivityInfo activityInfo = activity.info;
      if (str1.length() > 0) {
        str1 = str1.toString().intern();
      } else {
        str1 = null;
      } 
      activityInfo.permission = str1;
    } 
    String str2 = typedArray2.getNonConfigurationString(8, 1024);
    activity.info.taskAffinity = buildTaskAffinityName(package_.applicationInfo.packageName, package_.applicationInfo.taskAffinity, str2, arrayOfString);
    activity.info.splitName = typedArray2.getNonConfigurationString(48, 0);
    activity.info.flags = 0;
    if (typedArray2.getBoolean(9, false)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x1;
    } 
    if (typedArray2.getBoolean(10, false)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x2;
    } 
    if (typedArray2.getBoolean(11, false)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x4;
    } 
    if (typedArray2.getBoolean(21, false)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x80;
    } 
    if (typedArray2.getBoolean(18, false)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x8;
    } 
    if (typedArray2.getBoolean(12, false)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x10;
    } 
    if (typedArray2.getBoolean(13, false)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x20;
    } 
    if ((package_.applicationInfo.flags & 0x20) != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (typedArray2.getBoolean(19, bool1)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x40;
    } 
    if (typedArray2.getBoolean(22, false)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x100;
    } 
    if (typedArray2.getBoolean(29, false) || typedArray2.getBoolean(39, false)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x400;
    } 
    if (typedArray2.getBoolean(24, false)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x800;
    } 
    if (typedArray2.getBoolean(58, false)) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x20000000;
    } 
    if (!paramBoolean1) {
      if (typedArray2.getBoolean(25, paramBoolean2)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.flags |= 0x200;
      } 
      activity.info.launchMode = typedArray2.getInt(14, 0);
      activity.info.documentLaunchMode = typedArray2.getInt(33, 0);
      activity.info.maxRecents = typedArray2.getInt(34, ActivityTaskManager.getDefaultAppRecentsLimitStatic());
      activity.info.configChanges = getActivityConfigChanges(typedArray2.getInt(16, 0), typedArray2.getInt(47, 0));
      activity.info.softInputMode = typedArray2.getInt(20, 0);
      activity.info.persistableMode = typedArray2.getInteger(32, 0);
      if (typedArray2.getBoolean(31, false)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.flags |= Integer.MIN_VALUE;
      } 
      if (typedArray2.getBoolean(35, false)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.flags |= 0x2000;
      } 
      if (typedArray2.getBoolean(36, false)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.flags |= 0x1000;
      } 
      if (typedArray2.getBoolean(37, false)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.flags |= 0x4000;
      } 
      activity.info.screenOrientation = typedArray2.getInt(15, -1);
      setActivityResizeMode(activity.info, typedArray2, package_);
      if (typedArray2.getBoolean(41, false)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.flags |= 0x400000;
      } 
      if (typedArray2.getBoolean(57, false)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.flags |= 0x40000;
      } 
      if (typedArray2.hasValue(50) && typedArray2.getType(50) == 4)
        activity.setMaxAspectRatio(typedArray2.getFloat(50, 0.0F)); 
      if (typedArray2.hasValue(53) && typedArray2.getType(53) == 4)
        activity.setMinAspectRatio(typedArray2.getFloat(53, 0.0F)); 
      activity.info.lockTaskLaunchMode = typedArray2.getInt(38, 0);
      activity.info.directBootAware = typedArray2.getBoolean(42, false);
      activity.info.requestedVrComponent = typedArray2.getString(43);
      activity.info.rotationAnimation = typedArray2.getInt(46, -1);
      activity.info.colorMode = typedArray2.getInt(49, 0);
      if (typedArray2.getBoolean(56, false)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.flags |= 0x2000000;
      } 
      if (typedArray2.getBoolean(51, false)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.flags |= 0x800000;
      } 
      if (typedArray2.getBoolean(52, false)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.flags |= 0x1000000;
      } 
      if (typedArray2.getBoolean(54, false)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.privateFlags |= 0x1;
      } 
    } else {
      activity.info.launchMode = 0;
      activity.info.configChanges = 0;
      if (typedArray2.getBoolean(28, false)) {
        ActivityInfo activityInfo = activity.info;
        activityInfo.flags |= 0x40000000;
      } 
      activity.info.directBootAware = typedArray2.getBoolean(42, false);
    } 
    if (activity.info.directBootAware) {
      ApplicationInfo applicationInfo = package_.applicationInfo;
      applicationInfo.privateFlags |= 0x100;
    } 
    paramBoolean2 = typedArray2.getBoolean(45, false);
    if (paramBoolean2) {
      ActivityInfo activityInfo = activity.info;
      activityInfo.flags |= 0x100000;
      package_.visibleToInstantApps = true;
    } 
    typedArray2.recycle();
    if (paramBoolean1 && (package_.applicationInfo.privateFlags & 0x2) != 0 && activity.info.processName == package_.packageName)
      arrayOfString[0] = "Heavy-weight applications can not have receivers in main process"; 
    if (arrayOfString[0] != null)
      return null; 
    paramInt = paramXmlResourceParser.getDepth();
    XmlResourceParser xmlResourceParser1 = xmlResourceParser2;
    TypedArray typedArray1 = typedArray2;
    while (true) {
      int i = paramXmlResourceParser.next();
      if (i != 1 && (i != 3 || paramXmlResourceParser.getDepth() > paramInt)) {
        if (i == 3 || i == 4)
          continue; 
        if (paramXmlResourceParser.getName().equals("intent-filter")) {
          ActivityIntentInfo activityIntentInfo = new ActivityIntentInfo(activity);
          if (!parseIntent(paramResources, paramXmlResourceParser, true, true, activityIntentInfo, paramArrayOfString))
            return null; 
          if (activityIntentInfo.countActions() == 0) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("No actions in intent filter at ");
            stringBuilder1.append(this.mArchiveSourcePath);
            stringBuilder1.append(" ");
            stringBuilder1.append(paramXmlResourceParser.getPositionDescription());
            Slog.w("PackageParser", stringBuilder1.toString());
          } else {
            activity.order = Math.max(activityIntentInfo.getOrder(), activity.order);
            activity.intents.add(activityIntentInfo);
          } 
          if (paramBoolean2) {
            i = 1;
          } else if (!paramBoolean1 && isImplicitlyExposedIntent(activityIntentInfo)) {
            i = 2;
          } else {
            i = 0;
          } 
          activityIntentInfo.setVisibilityToInstantApp(i);
          if (activityIntentInfo.isVisibleToInstantApp()) {
            ActivityInfo activityInfo = activity.info;
            activityInfo.flags |= 0x100000;
          } 
          if (activityIntentInfo.isImplicitlyVisibleToInstantApp()) {
            ActivityInfo activityInfo = activity.info;
            activityInfo.flags |= 0x200000;
          } 
          XmlResourceParser xmlResourceParser3 = paramXmlResourceParser;
          continue;
        } 
        if (!paramBoolean1 && paramXmlResourceParser.getName().equals("preferred")) {
          ActivityIntentInfo activityIntentInfo = new ActivityIntentInfo(activity);
          if (!parseIntent(paramResources, paramXmlResourceParser, false, false, activityIntentInfo, paramArrayOfString))
            return null; 
          if (activityIntentInfo.countActions() == 0) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("No actions in preferred at ");
            stringBuilder1.append(this.mArchiveSourcePath);
            stringBuilder1.append(" ");
            stringBuilder1.append(paramXmlResourceParser.getPositionDescription());
            Slog.w("PackageParser", stringBuilder1.toString());
          } else {
            if (package_.preferredActivityFilters == null)
              package_.preferredActivityFilters = new ArrayList<>(); 
            package_.preferredActivityFilters.add(activityIntentInfo);
          } 
          if (paramBoolean2) {
            i = 1;
          } else if (!paramBoolean1 && isImplicitlyExposedIntent(activityIntentInfo)) {
            i = 2;
          } else {
            i = 0;
          } 
          activityIntentInfo.setVisibilityToInstantApp(i);
          if (activityIntentInfo.isVisibleToInstantApp()) {
            ActivityInfo activityInfo = activity.info;
            activityInfo.flags |= 0x100000;
          } 
          if (activityIntentInfo.isImplicitlyVisibleToInstantApp()) {
            ActivityInfo activityInfo = activity.info;
            activityInfo.flags |= 0x200000;
          } 
          XmlResourceParser xmlResourceParser3 = paramXmlResourceParser;
          continue;
        } 
        if (paramXmlResourceParser.getName().equals("meta-data")) {
          Bundle bundle = parseMetaData(paramResources, paramXmlResourceParser, activity.metaData, arrayOfString);
          activity.metaData = bundle;
          if (bundle == null)
            return null; 
          XmlResourceParser xmlResourceParser3 = paramXmlResourceParser;
          continue;
        } 
        if (!paramBoolean1 && paramXmlResourceParser.getName().equals("layout")) {
          parseLayout(paramResources, (AttributeSet)paramXmlResourceParser, activity);
          xmlResourceParser1 = paramXmlResourceParser;
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Problem in package ");
        stringBuilder.append(this.mArchiveSourcePath);
        stringBuilder.append(":");
        Slog.w("PackageParser", stringBuilder.toString());
        if (paramBoolean1) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown element under <receiver>: ");
          stringBuilder.append(paramXmlResourceParser.getName());
          stringBuilder.append(" at ");
          stringBuilder.append(this.mArchiveSourcePath);
          stringBuilder.append(" ");
          stringBuilder.append(paramXmlResourceParser.getPositionDescription());
          Slog.w("PackageParser", stringBuilder.toString());
        } else {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown element under <activity>: ");
          stringBuilder.append(paramXmlResourceParser.getName());
          stringBuilder.append(" at ");
          stringBuilder.append(this.mArchiveSourcePath);
          stringBuilder.append(" ");
          stringBuilder.append(paramXmlResourceParser.getPositionDescription());
          Slog.w("PackageParser", stringBuilder.toString());
        } 
        XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
        XmlResourceParser xmlResourceParser = paramXmlResourceParser;
        continue;
      } 
      break;
    } 
    resolveWindowLayout(activity);
    if (!bool) {
      ActivityInfo activityInfo = activity.info;
      if (activity.intents.size() > 0) {
        paramBoolean1 = true;
      } else {
        paramBoolean1 = false;
      } 
      activityInfo.exported = paramBoolean1;
    } 
    return activity;
  }
  
  private Activity parseActivityAlias(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, String[] paramArrayOfString, CachedComponentArgs paramCachedComponentArgs) throws XmlPullParserException, IOException {
    Activity activity1;
    boolean bool1;
    String[] arrayOfString = paramArrayOfString;
    TypedArray typedArray2 = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestActivityAlias);
    String str3 = typedArray2.getNonConfigurationString(7, 1024);
    if (str3 == null) {
      arrayOfString[0] = "<activity-alias> does not specify android:targetActivity";
      typedArray2.recycle();
      return null;
    } 
    String str4 = buildClassName(paramPackage.applicationInfo.packageName, str3, arrayOfString);
    if (str4 == null) {
      typedArray2.recycle();
      return null;
    } 
    if (paramCachedComponentArgs.mActivityAliasArgs == null) {
      paramCachedComponentArgs.mActivityAliasArgs = new ParseComponentArgs(paramPackage, paramArrayOfString, 2, 0, 1, 11, 8, 10, this.mSeparateProcesses, 0, 6, 4);
      paramCachedComponentArgs.mActivityAliasArgs.tag = "<activity-alias>";
    } 
    paramCachedComponentArgs.mActivityAliasArgs.sa = typedArray2;
    paramCachedComponentArgs.mActivityAliasArgs.flags = paramInt;
    String str5 = null;
    int i = paramPackage.activities.size();
    paramInt = 0;
    while (true) {
      str3 = str5;
      if (paramInt < i) {
        activity1 = paramPackage.activities.get(paramInt);
        if (str4.equals(activity1.info.name))
          break; 
        paramInt++;
        continue;
      } 
      break;
    } 
    if (activity1 == null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("<activity-alias> target activity ");
      stringBuilder.append(str4);
      stringBuilder.append(" not found in manifest");
      arrayOfString[0] = stringBuilder.toString();
      typedArray2.recycle();
      return null;
    } 
    ActivityInfo activityInfo = new ActivityInfo();
    activityInfo.targetActivity = str4;
    activityInfo.configChanges = activity1.info.configChanges;
    activityInfo.flags = activity1.info.flags;
    activityInfo.privateFlags = activity1.info.privateFlags;
    activityInfo.icon = activity1.info.icon;
    activityInfo.logo = activity1.info.logo;
    activityInfo.banner = activity1.info.banner;
    activityInfo.labelRes = activity1.info.labelRes;
    activityInfo.nonLocalizedLabel = activity1.info.nonLocalizedLabel;
    activityInfo.launchMode = activity1.info.launchMode;
    activityInfo.lockTaskLaunchMode = activity1.info.lockTaskLaunchMode;
    activityInfo.processName = activity1.info.processName;
    if (activityInfo.descriptionRes == 0)
      activityInfo.descriptionRes = activity1.info.descriptionRes; 
    activityInfo.screenOrientation = activity1.info.screenOrientation;
    activityInfo.taskAffinity = activity1.info.taskAffinity;
    activityInfo.theme = activity1.info.theme;
    activityInfo.softInputMode = activity1.info.softInputMode;
    activityInfo.uiOptions = activity1.info.uiOptions;
    activityInfo.parentActivityName = activity1.info.parentActivityName;
    activityInfo.maxRecents = activity1.info.maxRecents;
    activityInfo.windowLayout = activity1.info.windowLayout;
    activityInfo.resizeMode = activity1.info.resizeMode;
    activityInfo.maxAspectRatio = activity1.info.maxAspectRatio;
    activityInfo.minAspectRatio = activity1.info.minAspectRatio;
    activityInfo.supportsSizeChanges = activity1.info.supportsSizeChanges;
    activityInfo.requestedVrComponent = activity1.info.requestedVrComponent;
    activityInfo.directBootAware = activity1.info.directBootAware;
    Activity activity2 = new Activity(paramCachedComponentArgs.mActivityAliasArgs, activityInfo);
    if (arrayOfString[0] != null) {
      typedArray2.recycle();
      return null;
    } 
    boolean bool = typedArray2.hasValue(5);
    if (bool)
      activity2.info.exported = typedArray2.getBoolean(5, false); 
    String str1 = typedArray2.getNonConfigurationString(3, 0);
    if (str1 != null) {
      ActivityInfo activityInfo1 = activity2.info;
      if (str1.length() > 0) {
        str1 = str1.toString().intern();
      } else {
        str1 = null;
      } 
      activityInfo1.permission = str1;
    } 
    str1 = typedArray2.getNonConfigurationString(9, 1024);
    String str2 = "PackageParser";
    if (str1 != null) {
      str4 = buildClassName(activity2.info.packageName, str1, arrayOfString);
      if (arrayOfString[0] == null) {
        activity2.info.parentActivityName = str4;
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Activity alias ");
        stringBuilder.append(activity2.info.name);
        stringBuilder.append(" specified invalid parentActivityName ");
        stringBuilder.append(str1);
        Log.e("PackageParser", stringBuilder.toString());
        arrayOfString[0] = null;
      } 
    } 
    if ((activity2.info.flags & 0x100000) != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    typedArray2.recycle();
    if (arrayOfString[0] != null)
      return null; 
    paramInt = paramXmlResourceParser.getDepth();
    TypedArray typedArray1 = typedArray2;
    while (true) {
      i = paramXmlResourceParser.next();
      if (i != 1) {
        if (i != 3 || paramXmlResourceParser.getDepth() > paramInt) {
          if (i == 3 || i == 4)
            continue; 
          if (paramXmlResourceParser.getName().equals("intent-filter")) {
            ActivityIntentInfo activityIntentInfo = new ActivityIntentInfo(activity2);
            if (!parseIntent(paramResources, paramXmlResourceParser, true, true, activityIntentInfo, paramArrayOfString))
              return null; 
            if (activityIntentInfo.countActions() == 0) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("No actions in intent filter at ");
              stringBuilder1.append(this.mArchiveSourcePath);
              stringBuilder1.append(" ");
              stringBuilder1.append(paramXmlResourceParser.getPositionDescription());
              Slog.w(str2, stringBuilder1.toString());
            } else {
              activity2.order = Math.max(activityIntentInfo.getOrder(), activity2.order);
              activity2.intents.add(activityIntentInfo);
            } 
            if (bool1) {
              i = 1;
            } else if (isImplicitlyExposedIntent(activityIntentInfo)) {
              i = 2;
            } else {
              i = 0;
            } 
            activityIntentInfo.setVisibilityToInstantApp(i);
            if (activityIntentInfo.isVisibleToInstantApp()) {
              ActivityInfo activityInfo1 = activity2.info;
              activityInfo1.flags |= 0x100000;
            } 
            if (activityIntentInfo.isImplicitlyVisibleToInstantApp()) {
              ActivityInfo activityInfo1 = activity2.info;
              activityInfo1.flags |= 0x200000;
            } 
            continue;
          } 
          if (paramXmlResourceParser.getName().equals("meta-data")) {
            Bundle bundle = parseMetaData(paramResources, paramXmlResourceParser, activity2.metaData, arrayOfString);
            activity2.metaData = bundle;
            if (bundle == null)
              return null; 
            continue;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown element under <activity-alias>: ");
          stringBuilder.append(paramXmlResourceParser.getName());
          stringBuilder.append(" at ");
          stringBuilder.append(this.mArchiveSourcePath);
          stringBuilder.append(" ");
          stringBuilder.append(paramXmlResourceParser.getPositionDescription());
          Slog.w(str2, stringBuilder.toString());
          XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
          continue;
        } 
        boolean bool3 = true;
        break;
      } 
      boolean bool2 = true;
      break;
    } 
    if (!bool) {
      boolean bool2;
      ActivityInfo activityInfo1 = activity2.info;
      if (activity2.intents.size() <= 0)
        bool2 = false; 
      activityInfo1.exported = bool2;
    } 
    return activity2;
  }
  
  private String[] parseAdditionalCertificates(Resources paramResources, XmlResourceParser paramXmlResourceParser, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    String[] arrayOfString = EmptyArray.STRING;
    int i = paramXmlResourceParser.getDepth();
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        if (j == 3 || j == 4)
          continue; 
        if (paramXmlResourceParser.getName().equals("additional-certificate")) {
          TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestAdditionalCertificate);
          String str = typedArray.getNonResourceString(0);
          typedArray.recycle();
          if (TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Bad additional-certificate declaration with empty certDigest:");
            stringBuilder.append(str);
            paramArrayOfString[0] = stringBuilder.toString();
            this.mParseError = -108;
            XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
            typedArray.recycle();
            return null;
          } 
          arrayOfString = (String[])ArrayUtils.appendElement(String.class, (Object[])arrayOfString, str.replace(":", "").toLowerCase());
          continue;
        } 
        XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
        continue;
      } 
      break;
    } 
    return arrayOfString;
  }
  
  private boolean parseAllMetaData(Resources paramResources, XmlResourceParser paramXmlResourceParser, String paramString, Component<?> paramComponent, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    int i = paramXmlResourceParser.getDepth();
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        if (j == 3 || j == 4)
          continue; 
        if (paramXmlResourceParser.getName().equals("meta-data")) {
          Bundle bundle = parseMetaData(paramResources, paramXmlResourceParser, paramComponent.metaData, paramArrayOfString);
          paramComponent.metaData = bundle;
          if (bundle == null)
            return false; 
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown element under ");
        stringBuilder.append(paramString);
        stringBuilder.append(": ");
        stringBuilder.append(paramXmlResourceParser.getName());
        stringBuilder.append(" at ");
        stringBuilder.append(this.mArchiveSourcePath);
        stringBuilder.append(" ");
        stringBuilder.append(paramXmlResourceParser.getPositionDescription());
        Slog.w("PackageParser", stringBuilder.toString());
        XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
        continue;
      } 
      break;
    } 
    return true;
  }
  
  public static ApkLite parseApkLite(File paramFile, int paramInt) throws PackageParserException {
    return parseApkLiteInner(paramFile, null, null, paramInt);
  }
  
  public static ApkLite parseApkLite(FileDescriptor paramFileDescriptor, String paramString, int paramInt) throws PackageParserException {
    return parseApkLiteInner(null, paramFileDescriptor, paramString, paramInt);
  }
  
  private static ApkLite parseApkLite(String paramString, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, SigningDetails paramSigningDetails) throws IOException, XmlPullParserException, PackageParserException {
    Pair<String, String> pair = parsePackageSplitNames(paramXmlPullParser, paramAttributeSet);
    byte b = -1;
    boolean bool1 = false;
    boolean bool2 = false;
    int i = 1;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    boolean bool6 = false;
    boolean bool7 = false;
    boolean bool8 = false;
    String str1 = null;
    byte b1 = 0;
    int j = 0;
    while (j < paramAttributeSet.getAttributeCount()) {
      byte b2;
      boolean bool16;
      boolean bool17;
      boolean bool18;
      boolean bool19;
      boolean bool20;
      boolean bool21;
      String str7;
      String str6 = paramAttributeSet.getAttributeName(j);
      if (str6.equals("installLocation")) {
        b2 = paramAttributeSet.getAttributeIntValue(j, -1);
        bool16 = bool1;
        bool17 = bool2;
        bool18 = bool3;
        bool19 = bool4;
        bool20 = bool6;
        bool21 = bool7;
        str7 = str1;
      } else if (str6.equals("versionCode")) {
        bool16 = paramAttributeSet.getAttributeIntValue(j, 0);
        b2 = b;
        bool17 = bool2;
        bool18 = bool3;
        bool19 = bool4;
        bool20 = bool6;
        bool21 = bool7;
        str7 = str1;
      } else if (str6.equals("versionCodeMajor")) {
        bool17 = paramAttributeSet.getAttributeIntValue(j, 0);
        b2 = b;
        bool16 = bool1;
        bool18 = bool3;
        bool19 = bool4;
        bool20 = bool6;
        bool21 = bool7;
        str7 = str1;
      } else if (str6.equals("revisionCode")) {
        bool18 = paramAttributeSet.getAttributeIntValue(j, 0);
        b2 = b;
        bool16 = bool1;
        bool17 = bool2;
        bool19 = bool4;
        bool20 = bool6;
        bool21 = bool7;
        str7 = str1;
      } else if (str6.equals("coreApp")) {
        bool19 = paramAttributeSet.getAttributeBooleanValue(j, false);
        b2 = b;
        bool16 = bool1;
        bool17 = bool2;
        bool18 = bool3;
        bool20 = bool6;
        bool21 = bool7;
        str7 = str1;
      } else if (str6.equals("isolatedSplits")) {
        bool20 = paramAttributeSet.getAttributeBooleanValue(j, false);
        b2 = b;
        bool16 = bool1;
        bool17 = bool2;
        bool18 = bool3;
        bool19 = bool4;
        bool21 = bool7;
        str7 = str1;
      } else if (str6.equals("configForSplit")) {
        str7 = paramAttributeSet.getAttributeValue(j);
        b2 = b;
        bool16 = bool1;
        bool17 = bool2;
        bool18 = bool3;
        bool19 = bool4;
        bool20 = bool6;
        bool21 = bool7;
      } else if (str6.equals("isFeatureSplit")) {
        bool21 = paramAttributeSet.getAttributeBooleanValue(j, false);
        b2 = b;
        bool16 = bool1;
        bool17 = bool2;
        bool18 = bool3;
        bool19 = bool4;
        bool20 = bool6;
        str7 = str1;
      } else {
        b2 = b;
        bool16 = bool1;
        bool17 = bool2;
        bool18 = bool3;
        bool19 = bool4;
        bool20 = bool6;
        bool21 = bool7;
        str7 = str1;
        if (str6.equals("isSplitRequired")) {
          bool8 = paramAttributeSet.getAttributeBooleanValue(j, false);
          str7 = str1;
          bool21 = bool7;
          bool20 = bool6;
          bool19 = bool4;
          bool18 = bool3;
          bool17 = bool2;
          bool16 = bool1;
          b2 = b;
        } 
      } 
      j++;
      b = b2;
      bool1 = bool16;
      bool2 = bool17;
      bool3 = bool18;
      bool4 = bool19;
      bool6 = bool20;
      bool7 = bool21;
      str1 = str7;
    } 
    j = paramXmlPullParser.getDepth() + 1;
    ArrayList<VerifierInfo> arrayList = new ArrayList();
    String str3 = null;
    String str4 = null;
    String str5 = null;
    boolean bool11 = false;
    int k = 0;
    boolean bool12 = false;
    boolean bool13 = false;
    boolean bool9 = false;
    String str2 = null;
    boolean bool10 = true;
    boolean bool14 = false;
    boolean bool15 = bool5;
    int m = b1;
    bool5 = bool13;
    while (true) {
      int n = paramXmlPullParser.next();
      if (n != 1 && (n != 3 || paramXmlPullParser.getDepth() >= j)) {
        if (n == 3 || n == 4 || paramXmlPullParser.getDepth() != j)
          continue; 
        String str = paramXmlPullParser.getName();
        n = j;
        if ("package-verifier".equals(str)) {
          VerifierInfo verifierInfo = parseVerifier(paramAttributeSet);
          if (verifierInfo != null)
            arrayList.add(verifierInfo); 
          continue;
        } 
        if ("application".equals(paramXmlPullParser.getName())) {
          j = 0;
          for (bool13 = bool9; j < paramAttributeSet.getAttributeCount(); bool13 = bool9) {
            str = paramAttributeSet.getAttributeName(j);
            bool9 = bool13;
            if ("debuggable".equals(str)) {
              boolean bool = paramAttributeSet.getAttributeBooleanValue(j, false);
              bool15 = bool;
              bool9 = bool13;
              if (bool) {
                bool9 = true;
                bool15 = bool;
              } 
            } 
            if ("multiArch".equals(str))
              bool14 = paramAttributeSet.getAttributeBooleanValue(j, false); 
            if ("use32bitAbi".equals(str))
              bool12 = paramAttributeSet.getAttributeBooleanValue(j, false); 
            if ("extractNativeLibs".equals(str))
              bool10 = paramAttributeSet.getAttributeBooleanValue(j, true); 
            if ("useEmbeddedDex".equals(str))
              bool11 = paramAttributeSet.getAttributeBooleanValue(j, false); 
            j++;
          } 
          j = n;
          bool9 = bool13;
          continue;
        } 
        if ("overlay".equals(paramXmlPullParser.getName())) {
          j = 0;
          str = str3;
          while (j < paramAttributeSet.getAttributeCount()) {
            String str7;
            String str8;
            String str6 = paramAttributeSet.getAttributeName(j);
            if ("requiredSystemPropertyName".equals(str6)) {
              str7 = paramAttributeSet.getAttributeValue(j);
              bool13 = bool5;
              str3 = str;
              str8 = str5;
            } else if ("requiredSystemPropertyValue".equals(str6)) {
              str8 = paramAttributeSet.getAttributeValue(j);
              bool13 = bool5;
              str3 = str;
              str7 = str4;
            } else if ("targetPackage".equals(str6)) {
              str3 = paramAttributeSet.getAttributeValue(j);
              bool13 = bool5;
              str7 = str4;
              str8 = str5;
            } else if ("isStatic".equals(str6)) {
              bool13 = paramAttributeSet.getAttributeBooleanValue(j, false);
              str3 = str;
              str7 = str4;
              str8 = str5;
            } else {
              bool13 = bool5;
              str3 = str;
              str7 = str4;
              str8 = str5;
              if ("priority".equals(str6)) {
                k = paramAttributeSet.getAttributeIntValue(j, 0);
                str8 = str5;
                str7 = str4;
                str3 = str;
                bool13 = bool5;
              } 
            } 
            j++;
            bool5 = bool13;
            str = str3;
            str4 = str7;
            str5 = str8;
          } 
          j = n;
          str3 = str;
          continue;
        } 
        if ("uses-split".equals(paramXmlPullParser.getName())) {
          if (str2 != null) {
            Slog.w("PackageParser", "Only one <uses-split> permitted. Ignoring others.");
            continue;
          } 
          str2 = paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
          if (str2 != null) {
            j = n;
            continue;
          } 
          throw new PackageParserException(-108, "<uses-split> tag requires 'android:name' attribute");
        } 
        if ("uses-sdk".equals(paramXmlPullParser.getName())) {
          for (j = 0; j < paramAttributeSet.getAttributeCount(); j++) {
            str = paramAttributeSet.getAttributeName(j);
            if ("targetSdkVersion".equals(str))
              m = paramAttributeSet.getAttributeIntValue(j, 0); 
            if ("minSdkVersion".equals(str))
              i = paramAttributeSet.getAttributeIntValue(j, 1); 
          } 
          j = n;
          continue;
        } 
        if ("profileable".equals(paramXmlPullParser.getName())) {
          j = 0;
          while (j < paramAttributeSet.getAttributeCount()) {
            bool13 = bool9;
            if ("shell".equals(paramAttributeSet.getAttributeName(j)))
              bool13 = paramAttributeSet.getAttributeBooleanValue(j, bool9); 
            j++;
            bool9 = bool13;
          } 
          j = n;
        } 
        continue;
      } 
      break;
    } 
    if (!checkRequiredSystemProperties(str4, str5)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Skipping target and overlay pair ");
      stringBuilder.append(str3);
      stringBuilder.append(" and ");
      stringBuilder.append(paramString);
      stringBuilder.append(": overlay ignored due to required system property: ");
      stringBuilder.append(str4);
      stringBuilder.append(" with value: ");
      stringBuilder.append(str5);
      Slog.i("PackageParser", stringBuilder.toString());
      str3 = null;
      bool5 = false;
      k = 0;
    } 
    return new ApkLite(paramString, (String)pair.first, (String)pair.second, bool7, str1, str2, bool8, bool1, bool2, bool3, b, arrayList, paramSigningDetails, bool4, bool15, bool9, bool14, bool12, bool11, bool10, bool6, str3, bool5, k, i, m);
  }
  
  private static ApkLite parseApkLiteInner(File paramFile, FileDescriptor paramFileDescriptor, String paramString, int paramInt) throws PackageParserException {
    if (paramFileDescriptor != null) {
      str = paramString;
    } else {
      str = paramFile.getAbsolutePath();
    } 
    Object object1 = null;
    Object object2 = null;
    ApkAssets apkAssets1 = null;
    ApkAssets apkAssets2 = null;
    boolean bool = false;
    if (paramFileDescriptor != null) {
      Object object = object2;
      ApkAssets apkAssets = apkAssets2;
      object4 = object1;
      apkAssets4 = apkAssets1;
      try {
        ApkAssets apkAssets5 = ApkAssets.loadFromFd(paramFileDescriptor, paramString, 0, null);
        object = object2;
        apkAssets = apkAssets5;
        object4 = object1;
        apkAssets4 = apkAssets5;
      } catch (IOException iOException) {
        object = object2;
        apkAssets = apkAssets2;
        object4 = object1;
        apkAssets4 = apkAssets1;
        PackageParserException packageParserException1 = new PackageParserException();
        object = object2;
        apkAssets = apkAssets2;
        object4 = object1;
        apkAssets4 = apkAssets1;
        StringBuilder stringBuilder = new StringBuilder();
        object = object2;
        apkAssets = apkAssets2;
        object4 = object1;
        apkAssets4 = apkAssets1;
        this();
        object = object2;
        apkAssets = apkAssets2;
        object4 = object1;
        apkAssets4 = apkAssets1;
        stringBuilder.append("Failed to parse ");
        object = object2;
        apkAssets = apkAssets2;
        object4 = object1;
        apkAssets4 = apkAssets1;
        stringBuilder.append(str);
        object = object2;
        apkAssets = apkAssets2;
        object4 = object1;
        apkAssets4 = apkAssets1;
        this(-100, stringBuilder.toString());
        object = object2;
        apkAssets = apkAssets2;
        object4 = object1;
        apkAssets4 = apkAssets1;
        throw packageParserException1;
      } catch (XmlPullParserException|RuntimeException xmlPullParserException) {
      
      } finally {}
    } else {
      Object object = object2;
      ApkAssets apkAssets6 = apkAssets2;
      object4 = object1;
      apkAssets4 = apkAssets1;
      ApkAssets apkAssets5 = ApkAssets.loadFromPath(str);
      object = object2;
      apkAssets6 = apkAssets5;
      object4 = object1;
      apkAssets4 = apkAssets5;
    } 
    Object object3 = object4;
    ApkAssets apkAssets3 = apkAssets4;
    StringBuilder stringBuilder1 = new StringBuilder();
    object3 = object4;
    apkAssets3 = apkAssets4;
    this();
    String str;
    Object object4;
    ApkAssets apkAssets4;
    object3 = object4;
    apkAssets3 = apkAssets4;
    stringBuilder1.append("Failed to parse ");
    object3 = object4;
    apkAssets3 = apkAssets4;
    stringBuilder1.append(str);
    object3 = object4;
    apkAssets3 = apkAssets4;
    Slog.w("PackageParser", stringBuilder1.toString(), (Throwable)paramFile);
    object3 = object4;
    apkAssets3 = apkAssets4;
    PackageParserException packageParserException = new PackageParserException();
    object3 = object4;
    apkAssets3 = apkAssets4;
    StringBuilder stringBuilder2 = new StringBuilder();
    object3 = object4;
    apkAssets3 = apkAssets4;
    this();
    object3 = object4;
    apkAssets3 = apkAssets4;
    stringBuilder2.append("Failed to parse ");
    object3 = object4;
    apkAssets3 = apkAssets4;
    stringBuilder2.append(str);
    object3 = object4;
    apkAssets3 = apkAssets4;
    this(-102, stringBuilder2.toString(), (Throwable)paramFile);
    object3 = object4;
    apkAssets3 = apkAssets4;
    throw packageParserException;
  }
  
  private Package parseBaseApk(File paramFile, AssetManager paramAssetManager, int paramInt) throws PackageParserException {
    String str2;
    String str1 = paramFile.getAbsolutePath();
    if (str1.startsWith("/mnt/expand/")) {
      int i = str1.indexOf('/', "/mnt/expand/".length());
      str2 = str1.substring("/mnt/expand/".length(), i);
    } else {
      str2 = null;
    } 
    this.mParseError = 1;
    this.mArchiveSourcePath = paramFile.getAbsolutePath();
    Resources resources = null;
    File file1 = null;
    File file2 = null;
    paramFile = file2;
    try {
      Exception exception;
      int i = paramAssetManager.findCookieForPath(str1);
      if (i != 0) {
        paramFile = file2;
        XmlResourceParser xmlResourceParser = paramAssetManager.openXmlResourceParser(i, "AndroidManifest.xml");
        try {
          resources = new Resources();
          this(paramAssetManager, this.mMetrics, null);
          String[] arrayOfString = new String[1];
          Package package_ = parseBaseApk(str1, resources, xmlResourceParser, paramInt, arrayOfString);
          if (package_ != null) {
            package_.setVolumeUuid(str2);
            package_.setApplicationVolumeUuid(str2);
            package_.setBaseCodePath(str1);
            package_.setSigningDetails(SigningDetails.UNKNOWN);
            return package_;
          } 
          PackageParserException packageParserException = new PackageParserException();
          paramInt = this.mParseError;
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(str1);
          stringBuilder.append(" (at ");
          stringBuilder.append(xmlResourceParser.getPositionDescription());
          stringBuilder.append("): ");
          stringBuilder.append(arrayOfString[0]);
          this(paramInt, stringBuilder.toString());
          throw packageParserException;
        } catch (PackageParserException packageParserException) {
        
        } catch (Exception exception1) {
          XmlResourceParser xmlResourceParser1 = xmlResourceParser;
        } finally {
          Exception exception1;
          paramAssetManager = null;
        } 
      } else {
        Exception exception1 = exception;
        PackageParserException packageParserException = new PackageParserException();
        exception1 = exception;
        StringBuilder stringBuilder = new StringBuilder();
        exception1 = exception;
        this();
        exception1 = exception;
        stringBuilder.append("Failed adding asset path: ");
        exception1 = exception;
        stringBuilder.append(str1);
        exception1 = exception;
        this(-101, stringBuilder.toString());
        exception1 = exception;
        throw packageParserException;
      } 
    } catch (PackageParserException packageParserException) {
      paramFile = file1;
    } catch (Exception exception) {
      Resources resources2 = resources;
      Resources resources1 = resources2;
      PackageParserException packageParserException = new PackageParserException();
      resources1 = resources2;
      StringBuilder stringBuilder = new StringBuilder();
      resources1 = resources2;
      this();
      resources1 = resources2;
      stringBuilder.append("Failed to read manifest from ");
      resources1 = resources2;
      stringBuilder.append(str1);
      resources1 = resources2;
      this(-102, stringBuilder.toString(), exception);
      resources1 = resources2;
      throw packageParserException;
    } finally {}
    throw paramAssetManager;
  }
  
  private Package parseBaseApk(String paramString, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    try {
      StringBuilder stringBuilder;
      Pair<String, String> pair = parsePackageSplitNames((XmlPullParser)paramXmlResourceParser, (AttributeSet)paramXmlResourceParser);
      paramString = (String)pair.first;
      String str = (String)pair.second;
      if (!TextUtils.isEmpty(str)) {
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Expected base APK, but found split ");
        stringBuilder.append(str);
        paramArrayOfString[0] = stringBuilder.toString();
        this.mParseError = -106;
        return null;
      } 
      Package package_ = new Package((String)stringBuilder);
      TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifest);
      package_.mVersionCode = typedArray.getInteger(1, 0);
      package_.mVersionCodeMajor = typedArray.getInteger(11, 0);
      package_.applicationInfo.setVersionCode(package_.getLongVersionCode());
      package_.baseRevisionCode = typedArray.getInteger(5, 0);
      package_.mVersionName = typedArray.getNonConfigurationString(2, 0);
      if (package_.mVersionName != null)
        package_.mVersionName = package_.mVersionName.intern(); 
      package_.coreApp = paramXmlResourceParser.getAttributeBooleanValue(null, "coreApp", false);
      if (typedArray.getBoolean(6, false)) {
        ApplicationInfo applicationInfo = package_.applicationInfo;
        applicationInfo.privateFlags |= 0x8000;
      } 
      package_.mCompileSdkVersion = typedArray.getInteger(9, 0);
      package_.applicationInfo.compileSdkVersion = package_.mCompileSdkVersion;
      package_.mCompileSdkVersionCodename = typedArray.getNonConfigurationString(10, 0);
      if (package_.mCompileSdkVersionCodename != null)
        package_.mCompileSdkVersionCodename = package_.mCompileSdkVersionCodename.intern(); 
      package_.applicationInfo.compileSdkVersionCodename = package_.mCompileSdkVersionCodename;
      typedArray.recycle();
      return parseBaseApkCommon(package_, null, paramResources, paramXmlResourceParser, paramInt, paramArrayOfString);
    } catch (PackageParserException packageParserException) {
      this.mParseError = -106;
      return null;
    } 
  }
  
  private boolean parseBaseApkChild(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    String str1;
    StringBuilder stringBuilder;
    String str2 = paramXmlResourceParser.getAttributeValue(null, "package");
    if (validateName(str2, true, false) != null) {
      this.mParseError = -106;
      return false;
    } 
    if (str2.equals(paramPackage.packageName)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Child package name cannot be equal to parent package name: ");
      stringBuilder.append(paramPackage.packageName);
      str1 = stringBuilder.toString();
      Slog.w("PackageParser", str1);
      paramArrayOfString[0] = str1;
      this.mParseError = -108;
      return false;
    } 
    if (str1.hasChildPackage(str2)) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Duplicate child package:");
      stringBuilder1.append(str2);
      str1 = stringBuilder1.toString();
      Slog.w("PackageParser", str1);
      paramArrayOfString[0] = str1;
      this.mParseError = -108;
      return false;
    } 
    Package package_2 = new Package(str2);
    package_2.mVersionCode = ((Package)str1).mVersionCode;
    package_2.baseRevisionCode = ((Package)str1).baseRevisionCode;
    package_2.mVersionName = ((Package)str1).mVersionName;
    package_2.applicationInfo.targetSdkVersion = ((Package)str1).applicationInfo.targetSdkVersion;
    package_2.applicationInfo.minSdkVersion = ((Package)str1).applicationInfo.minSdkVersion;
    Package package_1 = parseBaseApkCommon(package_2, CHILD_PACKAGE_TAGS, (Resources)stringBuilder, paramXmlResourceParser, paramInt, paramArrayOfString);
    if (package_1 == null)
      return false; 
    if (((Package)str1).childPackages == null)
      ((Package)str1).childPackages = new ArrayList<>(); 
    ((Package)str1).childPackages.add(package_1);
    package_1.parentPackage = (Package)str1;
    return true;
  }
  
  private Package parseBaseApkCommon(Package paramPackage, Set<String> paramSet, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    StringBuilder stringBuilder1;
    StringBuilder stringBuilder2;
    this.mParseInstrumentationArgs = null;
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifest);
    String str = typedArray.getNonConfigurationString(0, 0);
    int i = 1;
    if (str != null && str.length() > 0) {
      String str1 = validateName(str, true, true);
      if (str1 != null && !"android".equals(paramPackage.packageName)) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("<manifest> specifies bad sharedUserId name \"");
        stringBuilder1.append(str);
        stringBuilder1.append("\": ");
        stringBuilder1.append(str1);
        paramArrayOfString[0] = stringBuilder1.toString();
        this.mParseError = -107;
        return null;
      } 
      ((Package)stringBuilder1).mSharedUserId = str.intern();
      ((Package)stringBuilder1).mSharedUserLabel = typedArray.getResourceId(3, 0);
    } 
    ((Package)stringBuilder1).installLocation = typedArray.getInteger(4, -1);
    ((Package)stringBuilder1).applicationInfo.installLocation = ((Package)stringBuilder1).installLocation;
    int j = typedArray.getInteger(7, 1);
    ((Package)stringBuilder1).applicationInfo.targetSandboxVersion = j;
    if ((paramInt & 0x8) != 0) {
      ApplicationInfo applicationInfo = ((Package)stringBuilder1).applicationInfo;
      applicationInfo.flags |= 0x40000;
    } 
    int k = 1;
    int m = paramXmlResourceParser.getDepth();
    int n = 1;
    int i1 = 1;
    int i2 = 1;
    int i3 = 0;
    int i4 = 1;
    int i5 = 1;
    while (true) {
      Set<String> set = paramSet;
      int i6 = paramXmlResourceParser.next();
      if (i6 != i && (i6 != 3 || paramXmlResourceParser.getDepth() > m)) {
        if (i6 != 3 && i6 != 4) {
          String str1 = paramXmlResourceParser.getName();
          if (set != null && !set.contains(str1)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Skipping unsupported element under <manifest>: ");
            stringBuilder.append(str1);
            stringBuilder.append(" at ");
            stringBuilder.append(this.mArchiveSourcePath);
            stringBuilder.append(" ");
            stringBuilder.append(paramXmlResourceParser.getPositionDescription());
            Slog.w("PackageParser", stringBuilder.toString());
            XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
          } else if (str1.equals("application")) {
            if (i3) {
              Slog.w("PackageParser", "<manifest> has more than one <application>");
              XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
            } else {
              if (!parseBaseApplication((Package)stringBuilder1, paramResources, paramXmlResourceParser, paramInt, paramArrayOfString))
                return null; 
              i6 = k;
              i = i5;
              int i7 = i4;
              i4 = i2;
              int i8 = 1;
              i2 = i7;
              i7 = i8;
              i8 = n;
              n = 1;
              int i9 = i4;
              i4 = i2;
              i5 = i;
              k = i6;
              i = n;
              i3 = i7;
              n = i8;
              i2 = i9;
            } 
          } else {
            int i8;
            i6 = k;
            i = i3;
            int i7 = i5;
            if (str1.equals("overlay")) {
              TypedArray typedArray1 = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestResourceOverlay);
              ((Package)stringBuilder1).mOverlayTarget = typedArray1.getString(1);
              ((Package)stringBuilder1).mOverlayTargetName = typedArray1.getString(3);
              ((Package)stringBuilder1).mOverlayCategory = typedArray1.getString(2);
              ((Package)stringBuilder1).mOverlayPriority = typedArray1.getInt(0, 0);
              ((Package)stringBuilder1).mOverlayIsStatic = typedArray1.getBoolean(4, false);
              String str2 = typedArray1.getString(5);
              str1 = typedArray1.getString(6);
              typedArray1.recycle();
              if (((Package)stringBuilder1).mOverlayTarget == null) {
                paramArrayOfString[0] = "<overlay> does not specify a target package";
                this.mParseError = -108;
                return null;
              } 
              if (((Package)stringBuilder1).mOverlayPriority < 0 || ((Package)stringBuilder1).mOverlayPriority > 9999) {
                paramArrayOfString[0] = "<overlay> priority must be between 0 and 9999";
                this.mParseError = -108;
                return null;
              } 
              if (!checkRequiredSystemProperties(str2, str1)) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Skipping target and overlay pair ");
                stringBuilder2.append(((Package)stringBuilder1).mOverlayTarget);
                stringBuilder2.append(" and ");
                stringBuilder2.append(((Package)stringBuilder1).baseCodePath);
                stringBuilder2.append(": overlay ignored due to required system property: ");
                stringBuilder2.append(str2);
                stringBuilder2.append(" with value: ");
                stringBuilder2.append(str1);
                Slog.i("PackageParser", stringBuilder2.toString());
                this.mParseError = -125;
                return null;
              } 
              ApplicationInfo applicationInfo = ((Package)stringBuilder1).applicationInfo;
              applicationInfo.privateFlags |= 0x10000000;
              XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
              i5 = i4;
              i8 = i7;
              i7 = i;
              i4 = i2;
              i2 = i5;
              i = i8;
              i8 = n;
            } else {
              if (str1.equals("key-sets")) {
                if (!parseKeySets((Package)stringBuilder1, paramResources, paramXmlResourceParser, paramArrayOfString))
                  return null; 
              } else if (str1.equals("permission-group")) {
                if (!parsePermissionGroup((Package)stringBuilder1, paramInt, paramResources, paramXmlResourceParser, paramArrayOfString))
                  return null; 
              } else {
                int i10 = i4;
                if (str1.equals("permission")) {
                  if (!parsePermission((Package)stringBuilder1, paramResources, paramXmlResourceParser, paramArrayOfString))
                    return null; 
                } else if (str1.equals("permission-tree")) {
                  if (!parsePermissionTree((Package)stringBuilder1, paramResources, paramXmlResourceParser, paramArrayOfString))
                    return null; 
                } else if (str1.equals("uses-permission")) {
                  if (!parseUsesPermission((Package)stringBuilder1, paramResources, paramXmlResourceParser))
                    return null; 
                } else if (str1.equals("uses-permission-sdk-m") || str1.equals("uses-permission-sdk-23")) {
                  if (!parseUsesPermission((Package)stringBuilder1, paramResources, paramXmlResourceParser))
                    return null; 
                } else {
                  ConfigurationInfo configurationInfo;
                  if (str1.equals("uses-configuration")) {
                    configurationInfo = new ConfigurationInfo();
                    TypedArray typedArray1 = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestUsesConfiguration);
                    configurationInfo.reqTouchScreen = typedArray1.getInt(0, 0);
                    configurationInfo.reqKeyboardType = typedArray1.getInt(1, 0);
                    if (typedArray1.getBoolean(2, false))
                      configurationInfo.reqInputFeatures |= 0x1; 
                    configurationInfo.reqNavigation = typedArray1.getInt(3, 0);
                    if (typedArray1.getBoolean(4, false))
                      configurationInfo.reqInputFeatures |= 0x2; 
                    typedArray1.recycle();
                    ((Package)stringBuilder1).configPreferences = ArrayUtils.add(((Package)stringBuilder1).configPreferences, configurationInfo);
                    XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                    i5 = i10;
                    i4 = i;
                    i10 = i2;
                    i2 = i5;
                    i = i7;
                    i7 = i4;
                    i4 = i10;
                    i10 = n;
                  } else {
                    FeatureInfo featureInfo;
                    String str2 = "uses-feature";
                    if (configurationInfo.equals("uses-feature")) {
                      featureInfo = parseUsesFeature(paramResources, (AttributeSet)paramXmlResourceParser);
                      ((Package)stringBuilder1).reqFeatures = ArrayUtils.add(((Package)stringBuilder1).reqFeatures, featureInfo);
                      if (featureInfo.name == null) {
                        configurationInfo = new ConfigurationInfo();
                        configurationInfo.reqGlEsVersion = featureInfo.reqGlEsVersion;
                        ((Package)stringBuilder1).configPreferences = ArrayUtils.add(((Package)stringBuilder1).configPreferences, configurationInfo);
                      } 
                      XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                    } else {
                      ArrayList arrayList;
                      if (configurationInfo.equals("feature-group")) {
                        FeatureGroupInfo featureGroupInfo = new FeatureGroupInfo();
                        configurationInfo = null;
                        i4 = paramXmlResourceParser.getDepth();
                        while (true) {
                          i5 = paramXmlResourceParser.next();
                          if (i5 != 1 && (i5 != 3 || paramXmlResourceParser.getDepth() > i4)) {
                            if (i5 == 3 || i5 == 4)
                              continue; 
                            String str3 = paramXmlResourceParser.getName();
                            if (str3.equals(featureInfo)) {
                              FeatureInfo featureInfo1 = parseUsesFeature(paramResources, (AttributeSet)paramXmlResourceParser);
                              featureInfo1.flags |= 0x1;
                              arrayList = ArrayUtils.add((ArrayList)configurationInfo, featureInfo1);
                            } else {
                              StringBuilder stringBuilder = new StringBuilder();
                              stringBuilder.append("Unknown element under <feature-group>: ");
                              stringBuilder.append(str3);
                              stringBuilder.append(" at ");
                              stringBuilder.append(this.mArchiveSourcePath);
                              stringBuilder.append(" ");
                              stringBuilder.append(paramXmlResourceParser.getPositionDescription());
                              Slog.w("PackageParser", stringBuilder.toString());
                            } 
                            XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                            continue;
                          } 
                          break;
                        } 
                        if (arrayList != null) {
                          featureGroupInfo.features = new FeatureInfo[arrayList.size()];
                          featureGroupInfo.features = (FeatureInfo[])arrayList.toArray((Object[])featureGroupInfo.features);
                        } 
                        ((Package)stringBuilder1).featureGroups = ArrayUtils.add(((Package)stringBuilder1).featureGroups, featureGroupInfo);
                        i5 = i;
                        i4 = i2;
                        i2 = i10;
                        i = i7;
                        i7 = i5;
                        i10 = n;
                      } else {
                        FeatureInfo featureInfo1;
                        if (arrayList.equals("uses-sdk")) {
                          if (SDK_VERSION > 0) {
                            FeatureInfo featureInfo2;
                            TypedArray typedArray1 = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestUsesSdk);
                            i5 = 1;
                            FeatureInfo featureInfo3 = null;
                            k = 0;
                            arrayList = null;
                            TypedValue typedValue2 = typedArray1.peekValue(0);
                            i4 = i5;
                            featureInfo = featureInfo3;
                            if (typedValue2 != null)
                              if (typedValue2.type == 3 && typedValue2.string != null) {
                                String str3 = typedValue2.string.toString();
                                i4 = i5;
                              } else {
                                i4 = typedValue2.data;
                                featureInfo = featureInfo3;
                              }  
                            TypedValue typedValue1 = typedArray1.peekValue(1);
                            if (typedValue1 != null) {
                              String str3;
                              if (typedValue1.type == 3 && typedValue1.string != null) {
                                String str5 = typedValue1.string.toString();
                                featureInfo2 = featureInfo;
                                i5 = k;
                                String str4 = str5;
                                if (featureInfo == null) {
                                  str3 = str5;
                                  i5 = k;
                                  str4 = str5;
                                } 
                              } else {
                                i5 = ((TypedValue)str3).data;
                                featureInfo2 = featureInfo;
                              } 
                            } else {
                              i5 = i4;
                              featureInfo1 = featureInfo;
                              featureInfo2 = featureInfo;
                            } 
                            typedArray1.recycle();
                            i4 = computeMinSdkVersion(i4, (String)featureInfo2, SDK_VERSION, SDK_CODENAMES, paramArrayOfString);
                            if (i4 < 0) {
                              this.mParseError = -12;
                              return null;
                            } 
                            i5 = computeTargetSdkVersion(i5, (String)featureInfo1, SDK_CODENAMES, paramArrayOfString);
                            if (i5 < 0) {
                              this.mParseError = -12;
                              return null;
                            } 
                            ((Package)stringBuilder1).applicationInfo.minSdkVersion = i4;
                            ((Package)stringBuilder1).applicationInfo.targetSdkVersion = i5;
                          } 
                          XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                          i4 = i;
                          i5 = i2;
                          i2 = i10;
                          i = i7;
                          i7 = i4;
                          i4 = i5;
                          i10 = n;
                        } else if (featureInfo1.equals("supports-screens")) {
                          TypedArray typedArray1 = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestSupportsScreens);
                          ((Package)stringBuilder1).applicationInfo.requiresSmallestWidthDp = typedArray1.getInteger(6, 0);
                          ((Package)stringBuilder1).applicationInfo.compatibleWidthLimitDp = typedArray1.getInteger(7, 0);
                          ((Package)stringBuilder1).applicationInfo.largestWidthLimitDp = typedArray1.getInteger(8, 0);
                          i6 = typedArray1.getInteger(1, i6);
                          i4 = typedArray1.getInteger(2, i7);
                          i5 = typedArray1.getInteger(3, i10);
                          i10 = typedArray1.getInteger(5, n);
                          i1 = typedArray1.getInteger(4, i1);
                          n = typedArray1.getInteger(0, i2);
                          typedArray1.recycle();
                          XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                          i7 = i;
                          i2 = i5;
                          i = i4;
                          i4 = n;
                        } else {
                          TypedArray typedArray1;
                          int i13 = n;
                          int i14 = i1;
                          int i15 = i2;
                          if (featureInfo1.equals("protected-broadcast")) {
                            typedArray1 = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestProtectedBroadcast);
                            String str3 = typedArray1.getNonResourceString(0);
                            typedArray1.recycle();
                            if (str3 != null) {
                              if (((Package)stringBuilder1).protectedBroadcasts == null)
                                ((Package)stringBuilder1).protectedBroadcasts = new ArrayList<>(); 
                              if (!((Package)stringBuilder1).protectedBroadcasts.contains(str3))
                                ((Package)stringBuilder1).protectedBroadcasts.add(str3.intern()); 
                            } 
                            XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                            i2 = i10;
                            i4 = i7;
                            i7 = i;
                            i1 = i14;
                            i10 = i13;
                            i = i4;
                            i4 = i15;
                          } else {
                            if (typedArray1.equals("instrumentation")) {
                              if (parseInstrumentation((Package)stringBuilder1, paramResources, paramXmlResourceParser, paramArrayOfString) == null)
                                return null; 
                            } else {
                              String str3;
                              if (typedArray1.equals("original-package")) {
                                TypedArray typedArray2 = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestOriginalPackage);
                                str3 = typedArray2.getNonConfigurationString(0, 0);
                                if (!((Package)stringBuilder1).packageName.equals(str3)) {
                                  if (((Package)stringBuilder1).mOriginalPackages == null) {
                                    ((Package)stringBuilder1).mOriginalPackages = new ArrayList<>();
                                    ((Package)stringBuilder1).mRealPackage = ((Package)stringBuilder1).packageName;
                                  } 
                                  ((Package)stringBuilder1).mOriginalPackages.add(str3);
                                } 
                                typedArray2.recycle();
                                XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                                i2 = i10;
                                i10 = i;
                                i4 = i15;
                                i1 = i14;
                                i = i7;
                                i7 = i10;
                                i10 = i13;
                              } else if (str3.equals("adopt-permissions")) {
                                TypedArray typedArray2 = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestOriginalPackage);
                                str3 = typedArray2.getNonConfigurationString(0, 0);
                                typedArray2.recycle();
                                if (str3 != null) {
                                  if (((Package)stringBuilder1).mAdoptPermissions == null)
                                    ((Package)stringBuilder1).mAdoptPermissions = new ArrayList<>(); 
                                  ((Package)stringBuilder1).mAdoptPermissions.add(str3);
                                } 
                                XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                                i2 = i10;
                                i4 = i;
                                i1 = i14;
                                i10 = i13;
                                i = i7;
                                i7 = i4;
                                i4 = i15;
                              } else {
                                if (str3.equals("uses-gl-texture")) {
                                  XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                                } else if (str3.equals("compatible-screens")) {
                                  XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                                } else if (str3.equals("supports-input")) {
                                  XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                                } else if (str3.equals("eat-comment")) {
                                  XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                                } else if (str3.equals("package")) {
                                  if (!MULTI_PACKAGE_APK_ENABLED) {
                                    XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                                  } else {
                                    if (!parseBaseApkChild((Package)stringBuilder1, paramResources, paramXmlResourceParser, paramInt, paramArrayOfString))
                                      return null; 
                                    i7 = i2;
                                    i2 = i4;
                                    i10 = n;
                                    i4 = i7;
                                    i7 = i;
                                    i = i5;
                                  } 
                                } else {
                                  if (str3.equals("restrict-update")) {
                                    if ((paramInt & 0x10) != 0) {
                                      TypedArray typedArray2 = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestRestrictUpdate);
                                      String str4 = typedArray2.getNonConfigurationString(0, 0);
                                      typedArray2.recycle();
                                      ((Package)stringBuilder1).restrictUpdateHash = null;
                                      if (str4 != null) {
                                        i4 = str4.length();
                                        byte[] arrayOfByte = new byte[i4 / 2];
                                        for (i2 = 0; i2 < i4; i2 += 2)
                                          arrayOfByte[i2 / 2] = (byte)(byte)((Character.digit(str4.charAt(i2), 16) << 4) + Character.digit(str4.charAt(i2 + 1), 16)); 
                                        ((Package)stringBuilder1).restrictUpdateHash = arrayOfByte;
                                      } 
                                    } 
                                    XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                                    i1 = i;
                                    i4 = i15;
                                    i2 = i10;
                                    i = i7;
                                    i10 = i13;
                                    i7 = i1;
                                    i1 = i14;
                                  } else {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("Unknown element under <manifest>: ");
                                    stringBuilder.append(paramXmlResourceParser.getName());
                                    stringBuilder.append(" at ");
                                    stringBuilder.append(this.mArchiveSourcePath);
                                    stringBuilder.append(" ");
                                    stringBuilder.append(paramXmlResourceParser.getPositionDescription());
                                    Slog.w("PackageParser", stringBuilder.toString());
                                    XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                                    i = 1;
                                  } 
                                  n = 1;
                                  i13 = i4;
                                  i4 = i2;
                                  i5 = i;
                                  k = i6;
                                  i = n;
                                  i3 = i7;
                                  n = i10;
                                  i2 = i13;
                                } 
                                i = 1;
                              } 
                              n = 1;
                              i13 = i4;
                              i4 = i2;
                              i5 = i;
                              k = i6;
                              i = n;
                              i3 = i7;
                              n = i10;
                              i2 = i13;
                            } 
                            i7 = i2;
                            i2 = i4;
                            i10 = n;
                            i4 = i7;
                            i7 = i;
                            i = i5;
                          } 
                        } 
                      } 
                      n = 1;
                      int i12 = i4;
                      i4 = i2;
                      i5 = i;
                      k = i6;
                      i = n;
                      i3 = i7;
                      n = i10;
                      i2 = i12;
                    } 
                    i7 = i2;
                    i2 = i4;
                    i10 = n;
                    i4 = i7;
                    i7 = i;
                    i = i5;
                  } 
                  n = 1;
                  int i11 = i4;
                  i4 = i2;
                  i5 = i;
                  k = i6;
                  i = n;
                  i3 = i7;
                  n = i10;
                  i2 = i11;
                } 
              } 
              i7 = i2;
              i2 = i4;
              i8 = n;
              i4 = i7;
              i7 = i;
              i = i5;
            } 
            n = 1;
            int i9 = i4;
            i4 = i2;
            i5 = i;
            k = i6;
            i = n;
            i3 = i7;
            n = i8;
            i2 = i9;
          } 
        } 
      } else {
        break;
      } 
      i = 1;
    } 
    i = i2;
    if (i3 == 0 && ((Package)stringBuilder1).instrumentation.size() == 0) {
      paramArrayOfString[0] = "<manifest> does not contain an <application> or <instrumentation>";
      this.mParseError = -109;
    } 
    i2 = NEW_PERMISSIONS.length;
    paramSet = null;
    paramInt = 0;
    while (paramInt < i2) {
      StringBuilder stringBuilder;
      NewPermissionInfo newPermissionInfo = NEW_PERMISSIONS[paramInt];
      if (((Package)stringBuilder1).applicationInfo.targetSdkVersion >= newPermissionInfo.sdkVersion)
        break; 
      Set<String> set = paramSet;
      if (!((Package)stringBuilder1).requestedPermissions.contains(newPermissionInfo.name)) {
        StringBuilder stringBuilder3;
        if (paramSet == null) {
          stringBuilder3 = new StringBuilder(128);
          stringBuilder3.append(((Package)stringBuilder1).packageName);
          stringBuilder3.append(": compat added ");
        } else {
          stringBuilder3.append(' ');
        } 
        stringBuilder3.append(newPermissionInfo.name);
        ((Package)stringBuilder1).requestedPermissions.add(newPermissionInfo.name);
        ((Package)stringBuilder1).implicitPermissions.add(newPermissionInfo.name);
        stringBuilder = stringBuilder3;
      } 
      paramInt++;
      stringBuilder2 = stringBuilder;
    } 
    if (stringBuilder2 != null)
      Slog.i("PackageParser", stringBuilder2.toString()); 
    try {
      List<SplitPermissionInfoParcelable> list = ActivityThread.getPermissionManager().getSplitPermissions();
      int i6 = list.size();
      for (paramInt = 0; paramInt < i6; paramInt++) {
        SplitPermissionInfoParcelable splitPermissionInfoParcelable = list.get(paramInt);
        if (((Package)stringBuilder1).applicationInfo.targetSdkVersion < splitPermissionInfoParcelable.getTargetSdk() && ((Package)stringBuilder1).requestedPermissions.contains(splitPermissionInfoParcelable.getSplitPermission())) {
          List<String> list1 = splitPermissionInfoParcelable.getNewPermissions();
          for (i2 = 0; i2 < list1.size(); i2++) {
            String str1 = list1.get(i2);
            if (!((Package)stringBuilder1).requestedPermissions.contains(str1)) {
              ((Package)stringBuilder1).requestedPermissions.add(str1);
              ((Package)stringBuilder1).implicitPermissions.add(str1);
            } 
          } 
        } 
      } 
      if (k < 0 || (k > 0 && ((Package)stringBuilder1).applicationInfo.targetSdkVersion >= 4)) {
        ApplicationInfo applicationInfo = ((Package)stringBuilder1).applicationInfo;
        applicationInfo.flags |= 0x200;
      } 
      if (i5 != 0) {
        ApplicationInfo applicationInfo = ((Package)stringBuilder1).applicationInfo;
        applicationInfo.flags |= 0x400;
      } 
      if (i4 < 0 || (i4 > 0 && ((Package)stringBuilder1).applicationInfo.targetSdkVersion >= 4)) {
        ApplicationInfo applicationInfo = ((Package)stringBuilder1).applicationInfo;
        applicationInfo.flags |= 0x800;
      } 
      if (n < 0 || (n > 0 && ((Package)stringBuilder1).applicationInfo.targetSdkVersion >= 9)) {
        ApplicationInfo applicationInfo = ((Package)stringBuilder1).applicationInfo;
        applicationInfo.flags |= 0x80000;
      } 
      if (i1 < 0 || (i1 > 0 && ((Package)stringBuilder1).applicationInfo.targetSdkVersion >= 4)) {
        ApplicationInfo applicationInfo = ((Package)stringBuilder1).applicationInfo;
        applicationInfo.flags |= 0x1000;
      } 
      if (i < 0 || (i > 0 && ((Package)stringBuilder1).applicationInfo.targetSdkVersion >= 4)) {
        ApplicationInfo applicationInfo = ((Package)stringBuilder1).applicationInfo;
        applicationInfo.flags |= 0x2000;
      } 
      if (((Package)stringBuilder1).applicationInfo.usesCompatibilityMode())
        adjustPackageToBeUnresizeableAndUnpipable((Package)stringBuilder1); 
      return (Package)stringBuilder1;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private boolean parseBaseApplication(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    StringBuilder stringBuilder;
    XmlResourceParser xmlResourceParser2;
    boolean bool;
    String str3;
    PackageParser packageParser = this;
    Package package_ = paramPackage;
    XmlResourceParser xmlResourceParser1 = paramXmlResourceParser;
    ApplicationInfo applicationInfo = package_.applicationInfo;
    String str1 = package_.applicationInfo.packageName;
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)xmlResourceParser1, R.styleable.AndroidManifestApplication);
    applicationInfo.iconRes = typedArray.getResourceId(2, 0);
    applicationInfo.roundIconRes = typedArray.getResourceId(42, 0);
    String[] arrayOfString = paramArrayOfString;
    if (!parsePackageItemInfo(paramPackage, applicationInfo, paramArrayOfString, "<application>", typedArray, false, 3, 1, 2, 42, 22, 30)) {
      typedArray.recycle();
      packageParser.mParseError = -108;
      return false;
    } 
    if (applicationInfo.name != null)
      applicationInfo.className = applicationInfo.name; 
    String str2 = typedArray.getNonConfigurationString(4, 1024);
    if (str2 != null)
      applicationInfo.manageSpaceActivityName = buildClassName(str1, str2, arrayOfString); 
    if (typedArray.getBoolean(17, true)) {
      applicationInfo.flags |= 0x8000;
      str2 = typedArray.getNonConfigurationString(16, 1024);
      if (str2 != null) {
        applicationInfo.backupAgentName = buildClassName(str1, str2, arrayOfString);
        if (typedArray.getBoolean(18, true))
          applicationInfo.flags |= 0x10000; 
        if (typedArray.getBoolean(21, false))
          applicationInfo.flags |= 0x20000; 
        if (typedArray.getBoolean(32, false))
          applicationInfo.flags |= 0x4000000; 
        if (typedArray.getBoolean(40, false))
          applicationInfo.privateFlags |= 0x2000; 
      } 
      TypedValue typedValue = typedArray.peekValue(35);
      if (typedValue != null) {
        int n = typedValue.resourceId;
        applicationInfo.fullBackupContent = n;
        if (n == 0) {
          if (typedValue.data == 0) {
            n = -1;
          } else {
            n = 0;
          } 
          applicationInfo.fullBackupContent = n;
        } 
      } 
    } 
    applicationInfo.theme = typedArray.getResourceId(0, 0);
    applicationInfo.descriptionRes = typedArray.getResourceId(13, 0);
    if (typedArray.getBoolean(8, false)) {
      str2 = typedArray.getNonResourceString(45);
      if (str2 == null || packageParser.mCallback.hasFeature(str2))
        applicationInfo.flags |= 0x8; 
    } 
    if (typedArray.getBoolean(27, false))
      package_.mRequiredForAllUsers = true; 
    str2 = typedArray.getString(28);
    if (str2 != null && str2.length() > 0)
      package_.mRestrictedAccountType = str2; 
    str2 = typedArray.getString(29);
    if (str2 != null && str2.length() > 0)
      package_.mRequiredAccountType = str2; 
    if (typedArray.getBoolean(10, false)) {
      applicationInfo.flags |= 0x2;
      applicationInfo.privateFlags |= 0x800000;
    } 
    if (typedArray.getBoolean(20, false))
      applicationInfo.flags |= 0x4000; 
    if (package_.applicationInfo.targetSdkVersion >= 14) {
      bool = true;
    } else {
      bool = false;
    } 
    package_.baseHardwareAccelerated = typedArray.getBoolean(23, bool);
    if (package_.baseHardwareAccelerated)
      applicationInfo.flags |= 0x20000000; 
    if (typedArray.getBoolean(7, true))
      applicationInfo.flags |= 0x4; 
    if (typedArray.getBoolean(14, false))
      applicationInfo.flags |= 0x20; 
    if (typedArray.getBoolean(5, true))
      applicationInfo.flags |= 0x40; 
    if (package_.parentPackage == null && typedArray.getBoolean(15, false))
      applicationInfo.flags |= 0x100; 
    if (typedArray.getBoolean(24, false))
      applicationInfo.flags |= 0x100000; 
    if (package_.applicationInfo.targetSdkVersion < 28) {
      bool = true;
    } else {
      bool = false;
    } 
    if (typedArray.getBoolean(36, bool))
      applicationInfo.flags |= 0x8000000; 
    if (typedArray.getBoolean(26, false))
      applicationInfo.flags |= 0x400000; 
    if (typedArray.getBoolean(33, false))
      applicationInfo.flags |= Integer.MIN_VALUE; 
    if (typedArray.getBoolean(34, true))
      applicationInfo.flags |= 0x10000000; 
    if (typedArray.getBoolean(53, false))
      applicationInfo.privateFlags |= 0x2000000; 
    if (typedArray.getBoolean(38, false))
      applicationInfo.privateFlags |= 0x20; 
    if (typedArray.getBoolean(39, false))
      applicationInfo.privateFlags |= 0x40; 
    if (typedArray.hasValueOrEmpty(37)) {
      if (typedArray.getBoolean(37, true)) {
        applicationInfo.privateFlags |= 0x400;
      } else {
        applicationInfo.privateFlags |= 0x800;
      } 
    } else if (package_.applicationInfo.targetSdkVersion >= 24) {
      applicationInfo.privateFlags |= 0x1000;
    } 
    if (typedArray.getBoolean(54, true))
      applicationInfo.privateFlags |= 0x4000000; 
    if (package_.applicationInfo.targetSdkVersion >= 29) {
      bool = true;
    } else {
      bool = false;
    } 
    if (typedArray.getBoolean(55, bool))
      applicationInfo.privateFlags |= 0x8000000; 
    if (package_.applicationInfo.targetSdkVersion < 29) {
      bool = true;
    } else {
      bool = false;
    } 
    if (typedArray.getBoolean(56, bool))
      applicationInfo.privateFlags |= 0x20000000; 
    if (typedArray.getBoolean(59, true))
      applicationInfo.privateFlags |= Integer.MIN_VALUE; 
    applicationInfo.maxAspectRatio = typedArray.getFloat(44, 0.0F);
    applicationInfo.minAspectRatio = typedArray.getFloat(51, 0.0F);
    applicationInfo.networkSecurityConfigRes = typedArray.getResourceId(41, 0);
    applicationInfo.category = typedArray.getInt(43, -1);
    str2 = typedArray.getNonConfigurationString(6, 0);
    if (str2 != null && str2.length() > 0) {
      str2 = str2.intern();
    } else {
      str2 = null;
    } 
    applicationInfo.permission = str2;
    if (package_.applicationInfo.targetSdkVersion >= 8) {
      str3 = typedArray.getNonConfigurationString(12, 1024);
    } else {
      str3 = typedArray.getNonResourceString(12);
    } 
    applicationInfo.taskAffinity = buildTaskAffinityName(applicationInfo.packageName, applicationInfo.packageName, str3, arrayOfString);
    String str4 = typedArray.getNonResourceString(48);
    if (str4 != null)
      applicationInfo.appComponentFactory = buildClassName(applicationInfo.packageName, str4, arrayOfString); 
    if (typedArray.getBoolean(49, false))
      applicationInfo.privateFlags |= 0x400000; 
    if (typedArray.getBoolean(50, false))
      applicationInfo.privateFlags |= 0x1000000; 
    if (arrayOfString[0] == null) {
      if (package_.applicationInfo.targetSdkVersion >= 8) {
        str2 = typedArray.getNonConfigurationString(11, 1024);
      } else {
        str2 = typedArray.getNonResourceString(11);
      } 
      applicationInfo.processName = buildProcessName(applicationInfo.packageName, null, str2, paramInt, packageParser.mSeparateProcesses, paramArrayOfString);
      applicationInfo.enabled = typedArray.getBoolean(9, true);
      if (typedArray.getBoolean(31, false))
        applicationInfo.flags |= 0x2000000; 
      if (typedArray.getBoolean(47, false)) {
        applicationInfo.privateFlags |= 0x2;
        if (applicationInfo.processName != null && !applicationInfo.processName.equals(applicationInfo.packageName))
          arrayOfString[0] = "cantSaveState applications can not use custom processes"; 
      } 
    } 
    applicationInfo.uiOptions = typedArray.getInt(25, 0);
    applicationInfo.classLoaderName = typedArray.getString(46);
    if (applicationInfo.classLoaderName != null && !ClassLoaderFactory.isValidClassLoaderName(applicationInfo.classLoaderName)) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Invalid class loader name: ");
      stringBuilder1.append(applicationInfo.classLoaderName);
      arrayOfString[0] = stringBuilder1.toString();
    } 
    applicationInfo.zygotePreloadName = typedArray.getString(52);
    typedArray.recycle();
    if (arrayOfString[0] != null) {
      packageParser.mParseError = -108;
      return false;
    } 
    int i = paramXmlResourceParser.getDepth();
    CachedComponentArgs cachedComponentArgs = new CachedComponentArgs();
    int j = 0;
    int k = 0;
    int m = 0;
    str2 = str4;
    while (true) {
      int n = paramXmlResourceParser.next();
      if (n != 1 && (n != 3 || paramXmlResourceParser.getDepth() > i)) {
        String[] arrayOfString3;
        Package package_1;
        if (n == 3 || n == 4)
          continue; 
        String str = paramXmlResourceParser.getName();
        if (str.equals("activity")) {
          Activity activity = parseActivity(paramPackage, paramResources, paramXmlResourceParser, paramInt, paramArrayOfString, cachedComponentArgs, false, package_.baseHardwareAccelerated);
          if (activity == null) {
            packageParser.mParseError = -108;
            return false;
          } 
          if (activity.order != 0) {
            n = 1;
          } else {
            n = 0;
          } 
          package_.activities.add(activity);
          j |= n;
          XmlResourceParser xmlResourceParser4 = xmlResourceParser1;
          String[] arrayOfString4 = arrayOfString;
          XmlResourceParser xmlResourceParser3 = xmlResourceParser4;
          package_1 = package_;
        } else if (package_1.equals("receiver")) {
          Activity activity = parseActivity(paramPackage, paramResources, paramXmlResourceParser, paramInt, paramArrayOfString, cachedComponentArgs, true, false);
          if (activity == null) {
            packageParser.mParseError = -108;
            return false;
          } 
          if (activity.order != 0) {
            n = 1;
          } else {
            n = 0;
          } 
          package_1 = paramPackage;
          package_1.receivers.add(activity);
          XmlResourceParser xmlResourceParser = paramXmlResourceParser;
          String[] arrayOfString4 = paramArrayOfString;
          k |= n;
        } else {
          ApplicationInfo applicationInfo1 = applicationInfo;
          PackageParser packageParser2 = packageParser;
          if (package_1.equals("service")) {
            Service service = parseService(paramPackage, paramResources, paramXmlResourceParser, paramInt, paramArrayOfString, cachedComponentArgs);
            if (service == null) {
              packageParser2.mParseError = -108;
              return false;
            } 
            if (service.order != 0) {
              n = 1;
            } else {
              n = 0;
            } 
            package_.services.add(service);
            XmlResourceParser xmlResourceParser = paramXmlResourceParser;
            String[] arrayOfString4 = paramArrayOfString;
            m |= n;
            package_1 = package_;
          } else if (package_1.equals("provider")) {
            Provider provider = parseProvider(paramPackage, paramResources, paramXmlResourceParser, paramInt, paramArrayOfString, cachedComponentArgs);
            if (provider == null) {
              packageParser2.mParseError = -108;
              return false;
            } 
            package_.providers.add(provider);
            XmlResourceParser xmlResourceParser = paramXmlResourceParser;
            String[] arrayOfString4 = paramArrayOfString;
            package_1 = package_;
          } else if (package_1.equals("activity-alias")) {
            Activity activity = parseActivityAlias(paramPackage, paramResources, paramXmlResourceParser, paramInt, paramArrayOfString, cachedComponentArgs);
            if (activity == null) {
              packageParser2.mParseError = -108;
              return false;
            } 
            if (activity.order != 0) {
              n = 1;
            } else {
              n = 0;
            } 
            package_.activities.add(activity);
            XmlResourceParser xmlResourceParser = paramXmlResourceParser;
            String[] arrayOfString4 = paramArrayOfString;
            j |= n;
            package_1 = package_;
          } else {
            XmlResourceParser xmlResourceParser;
            if (paramXmlResourceParser.getName().equals("meta-data")) {
              Bundle bundle = package_.mAppMetaData;
              xmlResourceParser = paramXmlResourceParser;
              String[] arrayOfString4 = paramArrayOfString;
              bundle = packageParser2.parseMetaData(paramResources, xmlResourceParser, bundle, arrayOfString4);
              package_.mAppMetaData = bundle;
              if (bundle == null) {
                packageParser2.mParseError = -108;
                return false;
              } 
              package_1 = package_;
            } else {
              XmlResourceParser xmlResourceParser3 = paramXmlResourceParser;
              String[] arrayOfString4 = paramArrayOfString;
              if (package_1.equals("static-library")) {
                TypedArray typedArray1 = paramResources.obtainAttributes((AttributeSet)xmlResourceParser3, R.styleable.AndroidManifestStaticLibrary);
                String str5 = typedArray1.getNonResourceString(0);
                int i1 = typedArray1.getInt(1, -1);
                n = typedArray1.getInt(2, 0);
                typedArray1.recycle();
                if (str5 == null || i1 < 0) {
                  stringBuilder = new StringBuilder();
                  stringBuilder.append("Bad static-library declaration name: ");
                  stringBuilder.append(str5);
                  stringBuilder.append(" version: ");
                  stringBuilder.append(i1);
                  arrayOfString4[0] = stringBuilder.toString();
                  packageParser2.mParseError = -108;
                  XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                  return false;
                } 
                if (package_.mSharedUserId != null) {
                  arrayOfString4[0] = "sharedUserId not allowed in static shared library";
                  packageParser2.mParseError = -107;
                  XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                  return false;
                } 
                if (package_.staticSharedLibName != null) {
                  stringBuilder = new StringBuilder();
                  stringBuilder.append("Multiple static-shared libs for package ");
                  stringBuilder.append(str1);
                  arrayOfString4[0] = stringBuilder.toString();
                  packageParser2.mParseError = -108;
                  XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                  return false;
                } 
                package_.staticSharedLibName = str5.intern();
                if (i1 >= 0) {
                  package_.staticSharedLibVersion = PackageInfo.composeLongVersionCode(n, i1);
                } else {
                  package_.staticSharedLibVersion = i1;
                } 
                ((ApplicationInfo)xmlResourceParser).privateFlags |= 0x4000;
                XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                xmlResourceParser = xmlResourceParser3;
                String[] arrayOfString5 = arrayOfString4;
                package_1 = package_;
              } else if (package_1.equals("library")) {
                TypedArray typedArray1 = paramResources.obtainAttributes((AttributeSet)xmlResourceParser3, R.styleable.AndroidManifestLibrary);
                String str5 = typedArray1.getNonResourceString(0);
                typedArray1.recycle();
                if (str5 != null) {
                  String str6 = str5.intern();
                  if (!ArrayUtils.contains(package_.libraryNames, str6))
                    package_.libraryNames = ArrayUtils.add(package_.libraryNames, str6); 
                } 
                XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                XmlResourceParser xmlResourceParser4 = xmlResourceParser3;
                String[] arrayOfString5 = arrayOfString4;
                package_1 = package_;
              } else if (package_1.equals("uses-static-library")) {
                xmlResourceParser = xmlResourceParser3;
                String[] arrayOfString5 = arrayOfString4;
                package_1 = package_;
                if (!packageParser2.parseUsesStaticLibrary(package_, paramResources, xmlResourceParser3, arrayOfString4))
                  return false; 
              } else if (package_1.equals("uses-library")) {
                TypedArray typedArray1 = paramResources.obtainAttributes((AttributeSet)xmlResourceParser3, R.styleable.AndroidManifestUsesLibrary);
                String str5 = typedArray1.getNonResourceString(0);
                bool = typedArray1.getBoolean(1, true);
                typedArray1.recycle();
                if (str5 != null) {
                  str5 = str5.intern();
                  if (bool) {
                    package_.usesLibraries = ArrayUtils.add(package_.usesLibraries, str5);
                  } else {
                    package_.usesOptionalLibraries = ArrayUtils.add(package_.usesOptionalLibraries, str5);
                  } 
                } 
                XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                XmlResourceParser xmlResourceParser4 = xmlResourceParser3;
                String[] arrayOfString5 = arrayOfString4;
                package_1 = package_;
              } else if (package_1.equals("uses-package")) {
                XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                xmlResourceParser = xmlResourceParser3;
                String[] arrayOfString5 = arrayOfString4;
                package_1 = package_;
              } else if (package_1.equals("profileable")) {
                if (paramResources.obtainAttributes((AttributeSet)xmlResourceParser3, R.styleable.AndroidManifestProfileable).getBoolean(0, false))
                  ((ApplicationInfo)xmlResourceParser).privateFlags |= 0x800000; 
                XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                package_1 = package_;
                String[] arrayOfString5 = arrayOfString4;
                xmlResourceParser = xmlResourceParser3;
              } else {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Unknown element under <application>: ");
                stringBuilder1.append((String)package_1);
                stringBuilder1.append(" at ");
                stringBuilder1.append(packageParser2.mArchiveSourcePath);
                stringBuilder1.append(" ");
                stringBuilder1.append(paramXmlResourceParser.getPositionDescription());
                Slog.w("PackageParser", stringBuilder1.toString());
                XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
                xmlResourceParser1 = xmlResourceParser3;
                arrayOfString3 = arrayOfString4;
                continue;
              } 
            } 
          } 
        } 
        PackageParser packageParser1 = packageParser;
        String[] arrayOfString1 = arrayOfString3;
        xmlResourceParser2 = xmlResourceParser1;
        String[] arrayOfString2 = arrayOfString1;
        package_ = package_1;
        packageParser = packageParser1;
        continue;
      } 
      break;
    } 
    if (TextUtils.isEmpty(package_.staticSharedLibName)) {
      Activity activity = packageParser.generateAppDetailsHiddenActivity(package_, paramInt, (String[])xmlResourceParser2, package_.baseHardwareAccelerated);
      package_.activities.add(activity);
    } 
    if (j != 0)
      Collections.sort(package_.activities, (Comparator<? super Activity>)_$$Lambda$PackageParser$0aobsT7Zf7WVZCqMZ5z2clAuQf4.INSTANCE); 
    if (k != 0)
      Collections.sort(package_.receivers, (Comparator<? super Activity>)_$$Lambda$PackageParser$0DZRgzfgaIMpCOhJqjw6PUiU5vw.INSTANCE); 
    if (m != 0)
      Collections.sort(package_.services, (Comparator<? super Service>)_$$Lambda$PackageParser$M_9fHqS_eEp1oYkuKJhRHOGUxf8.INSTANCE); 
    setMaxAspectRatio((Package)stringBuilder);
    setMinAspectRatio((Package)stringBuilder);
    setSupportsSizeChanges((Package)stringBuilder);
    if (hasDomainURLs((Package)stringBuilder)) {
      ApplicationInfo applicationInfo1 = package_.applicationInfo;
      applicationInfo1.privateFlags |= 0x10;
    } else {
      ApplicationInfo applicationInfo1 = package_.applicationInfo;
      applicationInfo1.privateFlags &= 0xFFFFFFEF;
    } 
    return true;
  }
  
  private Package parseClusterPackage(File paramFile, int paramInt) throws PackageParserException {
    DefaultSplitAssetLoader defaultSplitAssetLoader;
    PackageLite packageLite = parseClusterPackageLite(paramFile, 0);
    if (!this.mOnlyCoreApps || packageLite.coreApp) {
      null = null;
      if (packageLite.isolatedSplits && !ArrayUtils.isEmpty((Object[])packageLite.splitNames)) {
        try {
          null = SplitAssetDependencyLoader.createDependenciesFromPackage(packageLite);
          SplitAssetDependencyLoader splitAssetDependencyLoader = new SplitAssetDependencyLoader(packageLite, null, paramInt);
        } catch (android.content.pm.split.SplitDependencyLoader.IllegalDependencyException illegalDependencyException) {
          throw new PackageParserException(-101, illegalDependencyException.getMessage());
        } 
      } else {
        defaultSplitAssetLoader = new DefaultSplitAssetLoader(packageLite, paramInt);
      } 
      try {
        AssetManager assetManager = defaultSplitAssetLoader.getBaseAssetManager();
        File file = new File();
        this(packageLite.baseCodePath);
        Package package_ = parseBaseApk(file, assetManager, paramInt);
        if (package_ != null) {
          if (!ArrayUtils.isEmpty((Object[])packageLite.splitNames)) {
            int i = packageLite.splitNames.length;
            package_.splitNames = packageLite.splitNames;
            package_.splitCodePaths = packageLite.splitCodePaths;
            package_.splitRevisionCodes = packageLite.splitRevisionCodes;
            package_.splitFlags = new int[i];
            package_.splitPrivateFlags = new int[i];
            package_.applicationInfo.splitNames = package_.splitNames;
            package_.applicationInfo.splitDependencies = null;
            package_.applicationInfo.splitClassLoaderNames = new String[i];
            for (byte b = 0; b < i; b++)
              parseSplitApk(package_, b, defaultSplitAssetLoader.getSplitAssetManager(b), paramInt); 
          } 
          package_.setCodePath(packageLite.codePath);
          package_.setUse32bitAbi(packageLite.use32bitAbi);
          return package_;
        } 
        PackageParserException packageParserException = new PackageParserException();
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("Failed to parse base APK: ");
        stringBuilder1.append(file);
        this(-100, stringBuilder1.toString());
        throw packageParserException;
      } finally {
        IoUtils.closeQuietly((AutoCloseable)defaultSplitAssetLoader);
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Not a coreApp: ");
    stringBuilder.append(defaultSplitAssetLoader);
    throw new PackageParserException(-108, stringBuilder.toString());
  }
  
  static PackageLite parseClusterPackageLite(File paramFile, int paramInt) throws PackageParserException {
    File[] arrayOfFile = paramFile.listFiles();
    if (!ArrayUtils.isEmpty((Object[])arrayOfFile)) {
      StringBuilder stringBuilder1;
      int i = arrayOfFile.length;
      byte b = 0;
      if (i == 1 && arrayOfFile[0].isDirectory())
        return parseClusterPackageLite(arrayOfFile[0], paramInt); 
      ApkLite apkLite1 = null;
      i = 0;
      Trace.traceBegin(262144L, "parseApkLite");
      ArrayMap arrayMap = new ArrayMap();
      int j = arrayOfFile.length;
      while (b < j) {
        String str2;
        File file = arrayOfFile[b];
        ApkLite apkLite = apkLite1;
        int k = i;
        if (isApkFile(file)) {
          String str;
          apkLite = parseApkLite(file, paramInt);
          if (apkLite1 == null) {
            str = apkLite.packageName;
            i = apkLite.versionCode;
          } else if (str.equals(apkLite.packageName)) {
            if (i != apkLite.versionCode) {
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append("Inconsistent version ");
              stringBuilder1.append(apkLite.versionCode);
              stringBuilder1.append(" in ");
              stringBuilder1.append(file);
              stringBuilder1.append("; expected ");
              stringBuilder1.append(i);
              throw new PackageParserException(-101, stringBuilder1.toString());
            } 
          } else {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Inconsistent package ");
            stringBuilder1.append(apkLite.packageName);
            stringBuilder1.append(" in ");
            stringBuilder1.append(file);
            stringBuilder1.append("; expected ");
            stringBuilder1.append(str);
            throw new PackageParserException(-101, stringBuilder1.toString());
          } 
          if (arrayMap.put(apkLite.splitName, apkLite) == null) {
            str2 = str;
            k = i;
          } else {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Split name ");
            stringBuilder1.append(((ApkLite)str2).splitName);
            stringBuilder1.append(" defined more than once; most recent was ");
            stringBuilder1.append(file);
            throw new PackageParserException(-101, stringBuilder1.toString());
          } 
        } 
        b++;
        String str1 = str2;
        i = k;
      } 
      Trace.traceEnd(262144L);
      ApkLite apkLite2 = (ApkLite)arrayMap.remove(null);
      if (apkLite2 != null) {
        String[] arrayOfString1;
        String[] arrayOfString3;
        int[] arrayOfInt;
        i = arrayMap.size();
        apkLite1 = null;
        boolean[] arrayOfBoolean = null;
        String[] arrayOfString2 = null;
        if (i > 0) {
          arrayOfString1 = new String[i];
          arrayOfBoolean = new boolean[i];
          arrayOfString2 = new String[i];
          arrayOfString3 = new String[i];
          String[] arrayOfString = new String[i];
          arrayOfInt = new int[i];
          arrayOfString1 = (String[])arrayMap.keySet().toArray((Object[])arrayOfString1);
          Arrays.sort(arrayOfString1, sSplitNameComparator);
          for (paramInt = 0; paramInt < i; paramInt++) {
            ApkLite apkLite = (ApkLite)arrayMap.get(arrayOfString1[paramInt]);
            arrayOfString2[paramInt] = apkLite.usesSplitName;
            arrayOfBoolean[paramInt] = apkLite.isFeatureSplit;
            arrayOfString3[paramInt] = apkLite.configForSplit;
            arrayOfString[paramInt] = apkLite.codePath;
            arrayOfInt[paramInt] = apkLite.revisionCode;
          } 
        } else {
          arrayOfString3 = null;
          arrayOfFile = null;
          arrayOfInt = null;
        } 
        return new PackageLite(stringBuilder1.getAbsolutePath(), apkLite2, arrayOfString1, arrayOfBoolean, arrayOfString2, arrayOfString3, (String[])arrayOfFile, arrayOfInt);
      } 
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Missing base APK in ");
      stringBuilder2.append(stringBuilder1);
      throw new PackageParserException(-101, stringBuilder2.toString());
    } 
    throw new PackageParserException(-100, "No packages found in split");
  }
  
  private Instrumentation parseInstrumentation(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestInstrumentation);
    if (this.mParseInstrumentationArgs == null) {
      ParsePackageItemArgs parsePackageItemArgs = new ParsePackageItemArgs(paramPackage, paramArrayOfString, 2, 0, 1, 8, 6, 7);
      this.mParseInstrumentationArgs = parsePackageItemArgs;
      parsePackageItemArgs.tag = "<instrumentation>";
    } 
    this.mParseInstrumentationArgs.sa = typedArray;
    Instrumentation instrumentation = new Instrumentation(this.mParseInstrumentationArgs, new InstrumentationInfo());
    if (paramArrayOfString[0] != null) {
      typedArray.recycle();
      this.mParseError = -108;
      return null;
    } 
    String str = typedArray.getNonResourceString(3);
    InstrumentationInfo instrumentationInfo = instrumentation.info;
    if (str != null) {
      str = str.intern();
    } else {
      str = null;
    } 
    instrumentationInfo.targetPackage = str;
    str = typedArray.getNonResourceString(9);
    instrumentationInfo = instrumentation.info;
    if (str != null) {
      str = str.intern();
    } else {
      str = null;
    } 
    instrumentationInfo.targetProcesses = str;
    instrumentation.info.handleProfiling = typedArray.getBoolean(4, false);
    instrumentation.info.functionalTest = typedArray.getBoolean(5, false);
    typedArray.recycle();
    if (instrumentation.info.targetPackage == null) {
      paramArrayOfString[0] = "<instrumentation> does not specify targetPackage";
      this.mParseError = -108;
      return null;
    } 
    if (!parseAllMetaData(paramResources, paramXmlResourceParser, "<instrumentation>", instrumentation, paramArrayOfString)) {
      this.mParseError = -108;
      return null;
    } 
    paramPackage.instrumentation.add(instrumentation);
    return instrumentation;
  }
  
  private boolean parseIntent(Resources paramResources, XmlResourceParser paramXmlResourceParser, boolean paramBoolean1, boolean paramBoolean2, IntentInfo paramIntentInfo, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestIntentFilter);
    paramIntentInfo.setPriority(typedArray.getInt(2, 0));
    int i = 3;
    paramIntentInfo.setOrder(typedArray.getInt(3, 0));
    TypedValue typedValue = typedArray.peekValue(0);
    if (typedValue != null) {
      j = typedValue.resourceId;
      paramIntentInfo.labelRes = j;
      if (j == 0)
        paramIntentInfo.nonLocalizedLabel = typedValue.coerceToString(); 
    } 
    if (sUseRoundIcon) {
      j = typedArray.getResourceId(7, 0);
    } else {
      j = 0;
    } 
    if (j) {
      paramIntentInfo.icon = j;
    } else {
      paramIntentInfo.icon = typedArray.getResourceId(1, 0);
    } 
    paramIntentInfo.logo = typedArray.getResourceId(4, 0);
    paramIntentInfo.banner = typedArray.getResourceId(5, 0);
    if (paramBoolean2)
      paramIntentInfo.setAutoVerify(typedArray.getBoolean(6, false)); 
    typedArray.recycle();
    int k = paramXmlResourceParser.getDepth();
    int j = i;
    while (true) {
      i = paramXmlResourceParser.next();
      if (i != 1 && (i != j || paramXmlResourceParser.getDepth() > k)) {
        if (i == j || i == 4)
          continue; 
        String str = paramXmlResourceParser.getName();
        if (str.equals("action")) {
          str = paramXmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
          if (str == null || str == "") {
            paramArrayOfString[0] = "No value supplied for <android:name>";
            return false;
          } 
          XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
          paramIntentInfo.addAction(str);
        } else if (str.equals("category")) {
          str = paramXmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
          if (str == null || str == "") {
            paramArrayOfString[0] = "No value supplied for <android:name>";
            return false;
          } 
          XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
          paramIntentInfo.addCategory(str);
        } else if (str.equals("data")) {
          TypedArray typedArray1 = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestData);
          String str1 = typedArray1.getNonConfigurationString(0, 0);
          if (str1 != null)
            try {
              paramIntentInfo.addDataType(str1);
            } catch (android.content.IntentFilter.MalformedMimeTypeException malformedMimeTypeException) {
              paramArrayOfString[0] = malformedMimeTypeException.toString();
              typedArray1.recycle();
              return false;
            }  
          str1 = typedArray1.getNonConfigurationString(1, 0);
          if (str1 != null)
            paramIntentInfo.addDataScheme(str1); 
          str1 = typedArray1.getNonConfigurationString(7, 0);
          if (str1 != null)
            paramIntentInfo.addDataSchemeSpecificPart(str1, 0); 
          str1 = typedArray1.getNonConfigurationString(8, 0);
          if (str1 != null)
            paramIntentInfo.addDataSchemeSpecificPart(str1, 1); 
          str1 = typedArray1.getNonConfigurationString(9, 0);
          if (str1 != null) {
            if (!paramBoolean1) {
              paramArrayOfString[0] = "sspPattern not allowed here; ssp must be literal";
              return false;
            } 
            paramIntentInfo.addDataSchemeSpecificPart(str1, 2);
          } 
          str1 = typedArray1.getNonConfigurationString(2, 0);
          String str2 = typedArray1.getNonConfigurationString(3, 0);
          if (str1 != null)
            paramIntentInfo.addDataAuthority(str1, str2); 
          str1 = typedArray1.getNonConfigurationString(4, 0);
          if (str1 != null)
            paramIntentInfo.addDataPath(str1, 0); 
          str1 = typedArray1.getNonConfigurationString(5, 0);
          if (str1 != null)
            paramIntentInfo.addDataPath(str1, 1); 
          str1 = typedArray1.getNonConfigurationString(6, 0);
          if (str1 != null) {
            if (!paramBoolean1) {
              paramArrayOfString[0] = "pathPattern not allowed here; path must be literal";
              return false;
            } 
            paramIntentInfo.addDataPath(str1, 2);
          } 
          str1 = typedArray1.getNonConfigurationString(11, 0);
          if (str1 != null) {
            if (!paramBoolean1) {
              paramArrayOfString[0] = "pathAdvancedPattern not allowed here; path must be literal";
              return false;
            } 
            paramIntentInfo.addDataPath(str1, 3);
          } 
          typedArray1.recycle();
          XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown element under <intent-filter>: ");
          stringBuilder.append(paramXmlResourceParser.getName());
          stringBuilder.append(" at ");
          stringBuilder.append(this.mArchiveSourcePath);
          stringBuilder.append(" ");
          stringBuilder.append(paramXmlResourceParser.getPositionDescription());
          Slog.w("PackageParser", stringBuilder.toString());
          XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
        } 
        j = 3;
        continue;
      } 
      break;
    } 
    paramIntentInfo.hasDefault = paramIntentInfo.hasCategory("android.intent.category.DEFAULT");
    return true;
  }
  
  private boolean parseKeySets(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    StringBuilder stringBuilder1;
    int i = paramXmlResourceParser.getDepth();
    int j = -1;
    String str = null;
    ArrayMap arrayMap1 = new ArrayMap();
    ArraySet<String> arraySet = new ArraySet();
    ArrayMap arrayMap2 = new ArrayMap();
    ArraySet arraySet1 = new ArraySet();
    while (true) {
      int k = paramXmlResourceParser.next();
      if (k != 1 && (k != 3 || paramXmlResourceParser.getDepth() > i)) {
        TypedArray typedArray;
        if (k == 3) {
          if (paramXmlResourceParser.getDepth() == j) {
            str = null;
            j = -1;
          } 
          continue;
        } 
        String str1 = paramXmlResourceParser.getName();
        if (str1.equals("key-set")) {
          if (str != null) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Improperly nested 'key-set' tag at ");
            stringBuilder1.append(paramXmlResourceParser.getPositionDescription());
            paramArrayOfString[0] = stringBuilder1.toString();
            this.mParseError = -108;
            return false;
          } 
          typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestKeySet);
          str = typedArray.getNonResourceString(0);
          arrayMap2.put(str, new ArraySet());
          j = paramXmlResourceParser.getDepth();
          typedArray.recycle();
          continue;
        } 
        if (typedArray.equals("public-key")) {
          StringBuilder stringBuilder3;
          if (str == null) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Improperly nested 'key-set' tag at ");
            stringBuilder1.append(paramXmlResourceParser.getPositionDescription());
            paramArrayOfString[0] = stringBuilder1.toString();
            this.mParseError = -108;
            return false;
          } 
          typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestPublicKey);
          String str2 = typedArray.getNonResourceString(0);
          String str3 = typedArray.getNonResourceString(1);
          if (str3 == null && arrayMap1.get(str2) == null) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("'public-key' ");
            stringBuilder1.append(str2);
            stringBuilder1.append(" must define a public-key value on first use at ");
            stringBuilder1.append(paramXmlResourceParser.getPositionDescription());
            paramArrayOfString[0] = stringBuilder1.toString();
            this.mParseError = -108;
            typedArray.recycle();
            return false;
          } 
          if (str3 != null) {
            PublicKey publicKey = parsePublicKey(str3);
            if (publicKey == null) {
              stringBuilder3 = new StringBuilder();
              stringBuilder3.append("No recognized valid key in 'public-key' tag at ");
              stringBuilder3.append(paramXmlResourceParser.getPositionDescription());
              stringBuilder3.append(" key-set ");
              stringBuilder3.append(str);
              stringBuilder3.append(" will not be added to the package's defined key-sets.");
              Slog.w("PackageParser", stringBuilder3.toString());
              typedArray.recycle();
              arraySet1.add(str);
              XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
              continue;
            } 
            if (arrayMap1.get(stringBuilder3) == null || ((PublicKey)arrayMap1.get(stringBuilder3)).equals(publicKey)) {
              arrayMap1.put(stringBuilder3, publicKey);
            } else {
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append("Value of 'public-key' ");
              stringBuilder1.append((String)stringBuilder3);
              stringBuilder1.append(" conflicts with previously defined value at ");
              stringBuilder1.append(paramXmlResourceParser.getPositionDescription());
              paramArrayOfString[0] = stringBuilder1.toString();
              this.mParseError = -108;
              typedArray.recycle();
              return false;
            } 
          } 
          ((ArraySet)arrayMap2.get(str)).add(stringBuilder3);
          typedArray.recycle();
          XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
          continue;
        } 
        if (typedArray.equals("upgrade-key-set")) {
          typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestUpgradeKeySet);
          arraySet.add(typedArray.getNonResourceString(0));
          typedArray.recycle();
          XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown element under <key-sets>: ");
        stringBuilder.append(paramXmlResourceParser.getName());
        stringBuilder.append(" at ");
        stringBuilder.append(this.mArchiveSourcePath);
        stringBuilder.append(" ");
        stringBuilder.append(paramXmlResourceParser.getPositionDescription());
        Slog.w("PackageParser", stringBuilder.toString());
        XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
        continue;
      } 
      break;
    } 
    if (arrayMap1.keySet().removeAll(arrayMap2.keySet())) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Package");
      stringBuilder.append(((Package)stringBuilder1).packageName);
      stringBuilder.append(" AndroidManifext.xml 'key-set' and 'public-key' names must be distinct.");
      paramArrayOfString[0] = stringBuilder.toString();
      this.mParseError = -108;
      return false;
    } 
    ((Package)stringBuilder1).mKeySetMapping = new ArrayMap();
    for (Map.Entry entry : arrayMap2.entrySet()) {
      StringBuilder stringBuilder;
      String str1 = (String)entry.getKey();
      if (((ArraySet)entry.getValue()).size() == 0) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Package");
        stringBuilder.append(((Package)stringBuilder1).packageName);
        stringBuilder.append(" AndroidManifext.xml 'key-set' ");
        stringBuilder.append(str1);
        stringBuilder.append(" has no valid associated 'public-key'. Not including in package's defined key-sets.");
        Slog.w("PackageParser", stringBuilder.toString());
        continue;
      } 
      if (arraySet1.contains(str1)) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Package");
        stringBuilder.append(((Package)stringBuilder1).packageName);
        stringBuilder.append(" AndroidManifext.xml 'key-set' ");
        stringBuilder.append(str1);
        stringBuilder.append(" contained improper 'public-key' tags. Not including in package's defined key-sets.");
        Slog.w("PackageParser", stringBuilder.toString());
        continue;
      } 
      ((Package)stringBuilder1).mKeySetMapping.put(str1, new ArraySet());
      for (String str2 : stringBuilder.getValue())
        ((ArraySet)((Package)stringBuilder1).mKeySetMapping.get(str1)).add(arrayMap1.get(str2)); 
    } 
    if (((Package)stringBuilder1).mKeySetMapping.keySet().containsAll((Collection<?>)arraySet)) {
      ((Package)stringBuilder1).mUpgradeKeySets = arraySet;
      return true;
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Package");
    stringBuilder2.append(((Package)stringBuilder1).packageName);
    stringBuilder2.append(" AndroidManifext.xml does not define all 'upgrade-key-set's .");
    paramArrayOfString[0] = stringBuilder2.toString();
    this.mParseError = -108;
    return false;
  }
  
  private void parseLayout(Resources paramResources, AttributeSet paramAttributeSet, Activity paramActivity) {
    float f3;
    TypedArray typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.AndroidManifestLayout);
    int i = -1;
    float f1 = -1.0F;
    int j = -1;
    float f2 = -1.0F;
    int k = typedArray.getType(3);
    if (k == 6) {
      f3 = typedArray.getFraction(3, 1, 1, -1.0F);
    } else {
      f3 = f1;
      if (k == 5) {
        i = typedArray.getDimensionPixelSize(3, -1);
        f3 = f1;
      } 
    } 
    k = typedArray.getType(4);
    if (k == 6) {
      f1 = typedArray.getFraction(4, 1, 1, -1.0F);
    } else {
      f1 = f2;
      if (k == 5) {
        j = typedArray.getDimensionPixelSize(4, -1);
        f1 = f2;
      } 
    } 
    k = typedArray.getInt(0, 17);
    int m = typedArray.getDimensionPixelSize(1, -1);
    int n = typedArray.getDimensionPixelSize(2, -1);
    typedArray.recycle();
    paramActivity.info.windowLayout = new ActivityInfo.WindowLayout(i, f3, j, f1, k, m, n);
  }
  
  private Bundle parseMetaData(Resources paramResources, XmlResourceParser paramXmlResourceParser, Bundle paramBundle, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestMetaData);
    Bundle bundle = paramBundle;
    if (paramBundle == null)
      bundle = new Bundle(); 
    boolean bool = false;
    String str = typedArray.getNonConfigurationString(0, 0);
    paramBundle = null;
    if (str == null) {
      paramArrayOfString[0] = "<meta-data> requires an android:name attribute";
      typedArray.recycle();
      return null;
    } 
    str = str.intern();
    TypedValue typedValue = typedArray.peekValue(2);
    if (typedValue != null && typedValue.resourceId != 0) {
      bundle.putInt(str, typedValue.resourceId);
    } else {
      CharSequence charSequence;
      typedValue = typedArray.peekValue(1);
      if (typedValue != null) {
        if (typedValue.type == 3) {
          String str1;
          charSequence = typedValue.coerceToString();
          if (charSequence != null)
            str1 = charSequence.toString(); 
          bundle.putString(str, str1);
        } else if (typedValue.type == 18) {
          if (typedValue.data != 0)
            bool = true; 
          bundle.putBoolean(str, bool);
        } else if (typedValue.type >= 16 && typedValue.type <= 31) {
          bundle.putInt(str, typedValue.data);
        } else if (typedValue.type == 4) {
          bundle.putFloat(str, typedValue.getFloat());
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("<meta-data> only supports string, integer, float, color, boolean, and resource reference types: ");
          stringBuilder.append(paramXmlResourceParser.getName());
          stringBuilder.append(" at ");
          stringBuilder.append(this.mArchiveSourcePath);
          stringBuilder.append(" ");
          stringBuilder.append(paramXmlResourceParser.getPositionDescription());
          Slog.w("PackageParser", stringBuilder.toString());
        } 
      } else {
        charSequence[0] = "<meta-data> requires an android:value or android:resource attribute";
        bundle = null;
      } 
    } 
    typedArray.recycle();
    XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
    return bundle;
  }
  
  private static PackageLite parseMonolithicPackageLite(File paramFile, int paramInt) throws PackageParserException {
    Trace.traceBegin(262144L, "parseApkLite");
    ApkLite apkLite = parseApkLite(paramFile, paramInt);
    String str = paramFile.getAbsolutePath();
    Trace.traceEnd(262144L);
    return new PackageLite(str, apkLite, null, null, null, null, null, null);
  }
  
  private static boolean parsePackageItemInfo(Package paramPackage, PackageItemInfo paramPackageItemInfo, String[] paramArrayOfString, String paramString, TypedArray paramTypedArray, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    StringBuilder stringBuilder;
    if (paramTypedArray == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" does not contain any attributes");
      paramArrayOfString[0] = stringBuilder.toString();
      return false;
    } 
    String str = paramTypedArray.getNonConfigurationString(paramInt1, 0);
    if (str == null) {
      if (paramBoolean) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(" does not specify android:name");
        paramArrayOfString[0] = stringBuilder.toString();
        return false;
      } 
    } else {
      str = buildClassName(((Package)stringBuilder).applicationInfo.packageName, str, paramArrayOfString);
      if (PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(str)) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(" invalid android:name");
        paramArrayOfString[0] = stringBuilder.toString();
        return false;
      } 
      paramPackageItemInfo.name = str;
      if (str == null)
        return false; 
    } 
    if (sUseRoundIcon) {
      paramInt1 = paramTypedArray.getResourceId(paramInt4, 0);
    } else {
      paramInt1 = 0;
    } 
    if (paramInt1 != 0) {
      paramPackageItemInfo.icon = paramInt1;
      paramPackageItemInfo.nonLocalizedLabel = null;
    } else {
      paramInt1 = paramTypedArray.getResourceId(paramInt3, 0);
      if (paramInt1 != 0) {
        paramPackageItemInfo.icon = paramInt1;
        paramPackageItemInfo.nonLocalizedLabel = null;
      } 
    } 
    paramInt1 = paramTypedArray.getResourceId(paramInt5, 0);
    if (paramInt1 != 0)
      paramPackageItemInfo.logo = paramInt1; 
    paramInt1 = paramTypedArray.getResourceId(paramInt6, 0);
    if (paramInt1 != 0)
      paramPackageItemInfo.banner = paramInt1; 
    TypedValue typedValue = paramTypedArray.peekValue(paramInt2);
    if (typedValue != null) {
      paramInt1 = typedValue.resourceId;
      paramPackageItemInfo.labelRes = paramInt1;
      if (paramInt1 == 0)
        paramPackageItemInfo.nonLocalizedLabel = typedValue.coerceToString(); 
    } 
    paramPackageItemInfo.packageName = ((Package)stringBuilder).packageName;
    return true;
  }
  
  public static PackageLite parsePackageLite(File paramFile, int paramInt) throws PackageParserException {
    return paramFile.isDirectory() ? parseClusterPackageLite(paramFile, paramInt) : parseMonolithicPackageLite(paramFile, paramInt);
  }
  
  @Deprecated
  public static Pair<String, String> parsePackageSplitNames(XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws IOException, XmlPullParserException, PackageParserException {
    int i;
    while (true) {
      i = paramXmlPullParser.next();
      if (i != 2 && i != 1)
        continue; 
      break;
    } 
    if (i == 2) {
      if (paramXmlPullParser.getName().equals("manifest")) {
        StringBuilder stringBuilder;
        String str3 = paramAttributeSet.getAttributeValue(null, "package");
        if (!"android".equals(str3)) {
          String str = validateName(str3, true, true);
          if (str != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid manifest package: ");
            stringBuilder.append(str);
            throw new PackageParserException(-106, stringBuilder.toString());
          } 
        } 
        String str2 = stringBuilder.getAttributeValue(null, "split");
        String str1 = str2;
        if (str2 != null)
          if (str2.length() == 0) {
            str1 = null;
          } else {
            str1 = validateName(str2, false, false);
            if (str1 == null) {
              str1 = str2;
            } else {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("Invalid manifest split: ");
              stringBuilder1.append(str1);
              throw new PackageParserException(-106, stringBuilder1.toString());
            } 
          }  
        str2 = str3.intern();
        if (str1 != null)
          str1 = str1.intern(); 
        return Pair.create(str2, str1);
      } 
      throw new PackageParserException(-108, "No <manifest> tag");
    } 
    throw new PackageParserException(-108, "No start tag found");
  }
  
  private boolean parsePermission(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestPermission);
    if (typedArray.hasValue(10)) {
      StringBuilder stringBuilder1;
      StringBuilder stringBuilder2;
      if ("android".equals(paramPackage.packageName)) {
        String str = typedArray.getNonResourceString(10);
      } else {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(paramPackage.packageName);
        stringBuilder2.append(" defines a background permission. Only the 'android' package can do that.");
        Slog.w("PackageParser", stringBuilder2.toString());
        stringBuilder2 = null;
      } 
      Permission permission = new Permission(paramPackage, (String)stringBuilder2);
      if (!parsePackageItemInfo(paramPackage, permission.info, paramArrayOfString, "<permission>", typedArray, true, 2, 0, 1, 9, 6, 8)) {
        typedArray.recycle();
        this.mParseError = -108;
        return false;
      } 
      permission.info.group = typedArray.getNonResourceString(4);
      if (permission.info.group != null)
        permission.info.group = permission.info.group.intern(); 
      permission.info.descriptionRes = typedArray.getResourceId(5, 0);
      permission.info.requestRes = typedArray.getResourceId(11, 0);
      permission.info.protectionLevel = typedArray.getInt(3, 0);
      permission.info.flags = typedArray.getInt(7, 0);
      if (!permission.info.isRuntime() || !"android".equals(permission.info.packageName)) {
        PermissionInfo permissionInfo = permission.info;
        permissionInfo.flags &= 0xFFFFFFFB;
        permissionInfo = permission.info;
        permissionInfo.flags &= 0xFFFFFFF7;
      } else if ((permission.info.flags & 0x4) != 0 && (permission.info.flags & 0x8) != 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Permission cannot be both soft and hard restricted: ");
        stringBuilder1.append(permission.info.name);
        throw new IllegalStateException(stringBuilder1.toString());
      } 
      typedArray.recycle();
      if (permission.info.protectionLevel == -1) {
        paramArrayOfString[0] = "<permission> does not specify protectionLevel";
        this.mParseError = -108;
        return false;
      } 
      permission.info.protectionLevel = PermissionInfo.fixProtectionLevel(permission.info.protectionLevel);
      if (permission.info.getProtectionFlags() != 0 && (permission.info.protectionLevel & 0x1000) == 0 && (permission.info.protectionLevel & 0x2000) == 0 && (permission.info.protectionLevel & 0xF) != 2) {
        paramArrayOfString[0] = "<permission>  protectionLevel specifies a non-instant flag but is not based on signature type";
        this.mParseError = -108;
        return false;
      } 
      if (!parseAllMetaData(paramResources, paramXmlResourceParser, "<permission>", permission, paramArrayOfString)) {
        this.mParseError = -108;
        return false;
      } 
      ((Package)stringBuilder1).permissions.add(permission);
      return true;
    } 
    Object object = null;
  }
  
  private boolean parsePermissionGroup(Package paramPackage, int paramInt, Resources paramResources, XmlResourceParser paramXmlResourceParser, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestPermissionGroup);
    PermissionGroup permissionGroup = new PermissionGroup(paramPackage, typedArray.getResourceId(12, 0), typedArray.getResourceId(9, 0), typedArray.getResourceId(10, 0));
    if (!parsePackageItemInfo(paramPackage, permissionGroup.info, paramArrayOfString, "<permission-group>", typedArray, true, 2, 0, 1, 8, 5, 7)) {
      typedArray.recycle();
      this.mParseError = -108;
      return false;
    } 
    permissionGroup.info.descriptionRes = typedArray.getResourceId(4, 0);
    permissionGroup.info.requestRes = typedArray.getResourceId(11, 0);
    permissionGroup.info.flags = typedArray.getInt(6, 0);
    permissionGroup.info.priority = typedArray.getInt(3, 0);
    typedArray.recycle();
    if (!parseAllMetaData(paramResources, paramXmlResourceParser, "<permission-group>", permissionGroup, paramArrayOfString)) {
      this.mParseError = -108;
      return false;
    } 
    paramPackage.permissionGroups.add(permissionGroup);
    return true;
  }
  
  private boolean parsePermissionTree(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    StringBuilder stringBuilder;
    Permission permission = new Permission(paramPackage, (String)null);
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestPermissionTree);
    if (!parsePackageItemInfo(paramPackage, permission.info, paramArrayOfString, "<permission-tree>", typedArray, true, 2, 0, 1, 5, 3, 4)) {
      typedArray.recycle();
      this.mParseError = -108;
      return false;
    } 
    typedArray.recycle();
    int i = permission.info.name.indexOf('.');
    if (i > 0)
      i = permission.info.name.indexOf('.', i + 1); 
    if (i < 0) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("<permission-tree> name has less than three segments: ");
      stringBuilder.append(permission.info.name);
      paramArrayOfString[0] = stringBuilder.toString();
      this.mParseError = -108;
      return false;
    } 
    permission.info.descriptionRes = 0;
    permission.info.requestRes = 0;
    permission.info.protectionLevel = 0;
    permission.tree = true;
    if (!parseAllMetaData(paramResources, paramXmlResourceParser, "<permission-tree>", permission, paramArrayOfString)) {
      this.mParseError = -108;
      return false;
    } 
    ((Package)stringBuilder).permissions.add(permission);
    return true;
  }
  
  private Provider parseProvider(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, String[] paramArrayOfString, CachedComponentArgs paramCachedComponentArgs) throws XmlPullParserException, IOException {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestProvider);
    if (paramCachedComponentArgs.mProviderArgs == null) {
      paramCachedComponentArgs.mProviderArgs = new ParseComponentArgs(paramPackage, paramArrayOfString, 2, 0, 1, 19, 15, 17, this.mSeparateProcesses, 8, 14, 6);
      paramCachedComponentArgs.mProviderArgs.tag = "<provider>";
    } 
    paramCachedComponentArgs.mProviderArgs.sa = typedArray;
    paramCachedComponentArgs.mProviderArgs.flags = paramInt;
    Provider provider = new Provider(paramCachedComponentArgs.mProviderArgs, new ProviderInfo());
    if (paramArrayOfString[0] != null) {
      typedArray.recycle();
      return null;
    } 
    if (paramPackage.applicationInfo.targetSdkVersion < 17) {
      bool = true;
    } else {
      bool = false;
    } 
    provider.info.exported = typedArray.getBoolean(7, bool);
    String str2 = typedArray.getNonConfigurationString(10, 0);
    provider.info.isSyncable = typedArray.getBoolean(11, false);
    String str1 = typedArray.getNonConfigurationString(3, 0);
    String str3 = typedArray.getNonConfigurationString(4, 0);
    String str4 = str3;
    if (str3 == null)
      str4 = str1; 
    if (str4 == null) {
      provider.info.readPermission = paramPackage.applicationInfo.permission;
    } else {
      ProviderInfo providerInfo = provider.info;
      if (str4.length() > 0) {
        str4 = str4.toString().intern();
      } else {
        str4 = null;
      } 
      providerInfo.readPermission = str4;
    } 
    str4 = typedArray.getNonConfigurationString(5, 0);
    if (str4 != null)
      str1 = str4; 
    if (str1 == null) {
      provider.info.writePermission = paramPackage.applicationInfo.permission;
    } else {
      ProviderInfo providerInfo = provider.info;
      if (str1.length() > 0) {
        str1 = str1.toString().intern();
      } else {
        str1 = null;
      } 
      providerInfo.writePermission = str1;
    } 
    provider.info.grantUriPermissions = typedArray.getBoolean(13, false);
    provider.info.forceUriPermissions = typedArray.getBoolean(22, false);
    provider.info.multiprocess = typedArray.getBoolean(9, false);
    provider.info.initOrder = typedArray.getInt(12, 0);
    provider.info.splitName = typedArray.getNonConfigurationString(21, 0);
    provider.info.flags = 0;
    if (typedArray.getBoolean(16, false)) {
      ProviderInfo providerInfo = provider.info;
      providerInfo.flags |= 0x40000000;
    } 
    provider.info.directBootAware = typedArray.getBoolean(18, false);
    if (provider.info.directBootAware) {
      ApplicationInfo applicationInfo = paramPackage.applicationInfo;
      applicationInfo.privateFlags |= 0x100;
    } 
    boolean bool = typedArray.getBoolean(20, false);
    if (bool) {
      ProviderInfo providerInfo = provider.info;
      providerInfo.flags |= 0x100000;
      paramPackage.visibleToInstantApps = true;
    } 
    typedArray.recycle();
    if ((paramPackage.applicationInfo.privateFlags & 0x2) != 0 && provider.info.processName == paramPackage.packageName) {
      paramArrayOfString[0] = "Heavy-weight applications can not have providers in main process";
      return null;
    } 
    if (str2 == null) {
      paramArrayOfString[0] = "<provider> does not include authorities attribute";
      return null;
    } 
    if (str2.length() <= 0) {
      paramArrayOfString[0] = "<provider> has empty authorities attribute";
      return null;
    } 
    provider.info.authority = str2.intern();
    return !parseProviderTags(paramResources, paramXmlResourceParser, bool, provider, paramArrayOfString) ? null : provider;
  }
  
  private boolean parseProviderTags(Resources paramResources, XmlResourceParser paramXmlResourceParser, boolean paramBoolean, Provider paramProvider, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    int i = paramXmlResourceParser.getDepth();
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        if (j == 3 || j == 4)
          continue; 
        if (paramXmlResourceParser.getName().equals("intent-filter")) {
          ProviderIntentInfo providerIntentInfo = new ProviderIntentInfo(paramProvider);
          if (!parseIntent(paramResources, paramXmlResourceParser, true, false, providerIntentInfo, paramArrayOfString))
            return false; 
          if (paramBoolean) {
            providerIntentInfo.setVisibilityToInstantApp(1);
            ProviderInfo providerInfo = paramProvider.info;
            providerInfo.flags |= 0x100000;
          } 
          paramProvider.order = Math.max(providerIntentInfo.getOrder(), paramProvider.order);
          paramProvider.intents.add(providerIntentInfo);
          continue;
        } 
        if (paramXmlResourceParser.getName().equals("meta-data")) {
          Bundle bundle = parseMetaData(paramResources, paramXmlResourceParser, paramProvider.metaData, paramArrayOfString);
          paramProvider.metaData = bundle;
          if (bundle == null)
            return false; 
          continue;
        } 
        if (paramXmlResourceParser.getName().equals("grant-uri-permission")) {
          TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestGrantUriPermission);
          PatternMatcher patternMatcher = null;
          String str = typedArray.getNonConfigurationString(0, 0);
          if (str != null)
            patternMatcher = new PatternMatcher(str, 0); 
          str = typedArray.getNonConfigurationString(1, 0);
          if (str != null)
            patternMatcher = new PatternMatcher(str, 1); 
          str = typedArray.getNonConfigurationString(2, 0);
          if (str != null)
            patternMatcher = new PatternMatcher(str, 2); 
          typedArray.recycle();
          if (patternMatcher != null) {
            if (paramProvider.info.uriPermissionPatterns == null) {
              paramProvider.info.uriPermissionPatterns = new PatternMatcher[1];
              paramProvider.info.uriPermissionPatterns[0] = patternMatcher;
            } else {
              j = paramProvider.info.uriPermissionPatterns.length;
              PatternMatcher[] arrayOfPatternMatcher = new PatternMatcher[j + 1];
              System.arraycopy(paramProvider.info.uriPermissionPatterns, 0, arrayOfPatternMatcher, 0, j);
              arrayOfPatternMatcher[j] = patternMatcher;
              paramProvider.info.uriPermissionPatterns = arrayOfPatternMatcher;
            } 
            paramProvider.info.grantUriPermissions = true;
            XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
            continue;
          } 
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Unknown element under <path-permission>: ");
          stringBuilder1.append(paramXmlResourceParser.getName());
          stringBuilder1.append(" at ");
          stringBuilder1.append(this.mArchiveSourcePath);
          stringBuilder1.append(" ");
          stringBuilder1.append(paramXmlResourceParser.getPositionDescription());
          Slog.w("PackageParser", stringBuilder1.toString());
          XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
          continue;
        } 
        if (paramXmlResourceParser.getName().equals("path-permission")) {
          PathPermission pathPermission;
          TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestPathPermission);
          String str3 = typedArray.getNonConfigurationString(0, 0);
          String str1 = typedArray.getNonConfigurationString(1, 0);
          if (str1 == null)
            str1 = str3; 
          String str4 = typedArray.getNonConfigurationString(2, 0);
          String str2 = str4;
          if (str4 == null)
            str2 = str3; 
          j = 0;
          if (str1 != null) {
            str1 = str1.intern();
            j = 1;
          } 
          if (str2 != null) {
            str3 = str2.intern();
            j = 1;
          } else {
            str3 = str2;
          } 
          if (j == 0) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("No readPermission or writePermssion for <path-permission>: ");
            stringBuilder2.append(paramXmlResourceParser.getName());
            stringBuilder2.append(" at ");
            stringBuilder2.append(this.mArchiveSourcePath);
            stringBuilder2.append(" ");
            stringBuilder2.append(paramXmlResourceParser.getPositionDescription());
            Slog.w("PackageParser", stringBuilder2.toString());
            XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
            continue;
          } 
          str2 = typedArray.getNonConfigurationString(3, 0);
          if (str2 != null) {
            pathPermission = new PathPermission(str2, 0, str1, str3);
          } else {
            str2 = null;
          } 
          str4 = typedArray.getNonConfigurationString(4, 0);
          if (str4 != null)
            pathPermission = new PathPermission(str4, 1, str1, str3); 
          str4 = typedArray.getNonConfigurationString(5, 0);
          if (str4 != null)
            pathPermission = new PathPermission(str4, 2, str1, str3); 
          str4 = typedArray.getNonConfigurationString(6, 0);
          if (str4 != null)
            pathPermission = new PathPermission(str4, 3, str1, str3); 
          typedArray.recycle();
          if (pathPermission != null) {
            if (paramProvider.info.pathPermissions == null) {
              paramProvider.info.pathPermissions = new PathPermission[1];
              paramProvider.info.pathPermissions[0] = pathPermission;
            } else {
              j = paramProvider.info.pathPermissions.length;
              PathPermission[] arrayOfPathPermission = new PathPermission[j + 1];
              System.arraycopy(paramProvider.info.pathPermissions, 0, arrayOfPathPermission, 0, j);
              arrayOfPathPermission[j] = pathPermission;
              paramProvider.info.pathPermissions = arrayOfPathPermission;
            } 
            XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
            continue;
          } 
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("No path, pathPrefix, or pathPattern for <path-permission>: ");
          stringBuilder1.append(paramXmlResourceParser.getName());
          stringBuilder1.append(" at ");
          stringBuilder1.append(this.mArchiveSourcePath);
          stringBuilder1.append(" ");
          stringBuilder1.append(paramXmlResourceParser.getPositionDescription());
          Slog.w("PackageParser", stringBuilder1.toString());
          XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown element under <provider>: ");
        stringBuilder.append(paramXmlResourceParser.getName());
        stringBuilder.append(" at ");
        stringBuilder.append(this.mArchiveSourcePath);
        stringBuilder.append(" ");
        stringBuilder.append(paramXmlResourceParser.getPositionDescription());
        Slog.w("PackageParser", stringBuilder.toString());
        XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
        continue;
      } 
      break;
    } 
    return true;
  }
  
  public static final PublicKey parsePublicKey(String paramString) {
    if (paramString == null) {
      Slog.w("PackageParser", "Could not parse null public key");
      return null;
    } 
    try {
      X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(paramString, 0));
      try {
        return KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        Slog.wtf("PackageParser", "Could not parse public key: RSA KeyFactory not included in build");
      } catch (InvalidKeySpecException invalidKeySpecException) {}
      try {
        return KeyFactory.getInstance("EC").generatePublic(x509EncodedKeySpec);
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        Slog.wtf("PackageParser", "Could not parse public key: EC KeyFactory not included in build");
      } catch (InvalidKeySpecException invalidKeySpecException) {}
      try {
        return KeyFactory.getInstance("DSA").generatePublic(x509EncodedKeySpec);
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        Slog.wtf("PackageParser", "Could not parse public key: DSA KeyFactory not included in build");
      } catch (InvalidKeySpecException invalidKeySpecException) {}
      return null;
    } catch (IllegalArgumentException illegalArgumentException) {
      Slog.w("PackageParser", "Could not parse verifier public key; invalid Base64");
      return null;
    } 
  }
  
  private Service parseService(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, String[] paramArrayOfString, CachedComponentArgs paramCachedComponentArgs) throws XmlPullParserException, IOException {
    XmlResourceParser xmlResourceParser2 = paramXmlResourceParser;
    String[] arrayOfString = paramArrayOfString;
    TypedArray typedArray2 = paramResources.obtainAttributes((AttributeSet)xmlResourceParser2, R.styleable.AndroidManifestService);
    if (paramCachedComponentArgs.mServiceArgs == null) {
      paramCachedComponentArgs.mServiceArgs = new ParseComponentArgs(paramPackage, paramArrayOfString, 2, 0, 1, 15, 8, 12, this.mSeparateProcesses, 6, 7, 4);
      paramCachedComponentArgs.mServiceArgs.tag = "<service>";
    } 
    paramCachedComponentArgs.mServiceArgs.sa = typedArray2;
    paramCachedComponentArgs.mServiceArgs.flags = paramInt;
    Service service = new Service(paramCachedComponentArgs.mServiceArgs, new ServiceInfo());
    if (arrayOfString[0] != null) {
      typedArray2.recycle();
      return null;
    } 
    boolean bool1 = typedArray2.hasValue(5);
    if (bool1)
      service.info.exported = typedArray2.getBoolean(5, false); 
    String str = typedArray2.getNonConfigurationString(3, 0);
    if (str == null) {
      service.info.permission = paramPackage.applicationInfo.permission;
    } else {
      ServiceInfo serviceInfo = service.info;
      if (str.length() > 0) {
        str = str.toString().intern();
      } else {
        str = null;
      } 
      serviceInfo.permission = str;
    } 
    service.info.splitName = typedArray2.getNonConfigurationString(17, 0);
    service.info.mForegroundServiceType = typedArray2.getInt(19, 0);
    service.info.flags = 0;
    boolean bool2 = typedArray2.getBoolean(9, false);
    paramInt = 1;
    if (bool2) {
      ServiceInfo serviceInfo = service.info;
      serviceInfo.flags |= 0x1;
    } 
    if (typedArray2.getBoolean(10, false)) {
      ServiceInfo serviceInfo = service.info;
      serviceInfo.flags |= 0x2;
    } 
    if (typedArray2.getBoolean(14, false)) {
      ServiceInfo serviceInfo = service.info;
      serviceInfo.flags |= 0x4;
    } 
    if (typedArray2.getBoolean(18, false)) {
      ServiceInfo serviceInfo = service.info;
      serviceInfo.flags |= 0x8;
    } 
    if (typedArray2.getBoolean(11, false)) {
      ServiceInfo serviceInfo = service.info;
      serviceInfo.flags |= 0x40000000;
    } 
    service.info.directBootAware = typedArray2.getBoolean(13, false);
    if (service.info.directBootAware) {
      ApplicationInfo applicationInfo = paramPackage.applicationInfo;
      applicationInfo.privateFlags |= 0x100;
    } 
    bool2 = typedArray2.getBoolean(16, false);
    if (bool2) {
      ServiceInfo serviceInfo = service.info;
      serviceInfo.flags |= 0x100000;
      paramPackage.visibleToInstantApps = true;
    } 
    typedArray2.recycle();
    if ((paramPackage.applicationInfo.privateFlags & 0x2) != 0 && service.info.processName == paramPackage.packageName) {
      arrayOfString[0] = "Heavy-weight applications can not have services in main process";
      return null;
    } 
    int i = paramXmlResourceParser.getDepth();
    XmlResourceParser xmlResourceParser1 = xmlResourceParser2;
    TypedArray typedArray1 = typedArray2;
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != paramInt) {
        if (j != 3 || paramXmlResourceParser.getDepth() > i) {
          if (j != 3) {
            if (j == 4) {
              paramInt = 1;
              continue;
            } 
            if (paramXmlResourceParser.getName().equals("intent-filter")) {
              ServiceIntentInfo serviceIntentInfo = new ServiceIntentInfo(service);
              if (!parseIntent(paramResources, paramXmlResourceParser, true, false, serviceIntentInfo, paramArrayOfString))
                return null; 
              if (bool2) {
                serviceIntentInfo.setVisibilityToInstantApp(1);
                ServiceInfo serviceInfo = service.info;
                serviceInfo.flags |= 0x100000;
              } 
              service.order = Math.max(serviceIntentInfo.getOrder(), service.order);
              service.intents.add(serviceIntentInfo);
              paramInt = 1;
              continue;
            } 
            if (paramXmlResourceParser.getName().equals("meta-data")) {
              Bundle bundle = parseMetaData(paramResources, xmlResourceParser1, service.metaData, arrayOfString);
              service.metaData = bundle;
              if (bundle == null)
                return null; 
              paramInt = 1;
              continue;
            } 
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown element under <service>: ");
            stringBuilder.append(paramXmlResourceParser.getName());
            stringBuilder.append(" at ");
            stringBuilder.append(this.mArchiveSourcePath);
            stringBuilder.append(" ");
            stringBuilder.append(paramXmlResourceParser.getPositionDescription());
            Slog.w("PackageParser", stringBuilder.toString());
            XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
            paramInt = 1;
            continue;
          } 
          paramInt = 1;
          continue;
        } 
        paramInt = 1;
      } 
      break;
    } 
    if (!bool1) {
      ServiceInfo serviceInfo = service.info;
      if (service.intents.size() <= 0)
        paramInt = 0; 
      serviceInfo.exported = paramInt;
    } 
    return service;
  }
  
  private Package parseSplitApk(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt1, int paramInt2, String[] paramArrayOfString) throws XmlPullParserException, IOException, PackageParserException {
    parsePackageSplitNames((XmlPullParser)paramXmlResourceParser, (AttributeSet)paramXmlResourceParser);
    this.mParseInstrumentationArgs = null;
    boolean bool = false;
    int i = paramXmlResourceParser.getDepth();
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        if (j == 3 || j == 4)
          continue; 
        if (paramXmlResourceParser.getName().equals("application")) {
          if (bool) {
            Slog.w("PackageParser", "<manifest> has more than one <application>");
            XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
            continue;
          } 
          bool = true;
          if (!parseSplitApplication(paramPackage, paramResources, paramXmlResourceParser, paramInt1, paramInt2, paramArrayOfString))
            return null; 
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown element under <manifest>: ");
        stringBuilder.append(paramXmlResourceParser.getName());
        stringBuilder.append(" at ");
        stringBuilder.append(this.mArchiveSourcePath);
        stringBuilder.append(" ");
        stringBuilder.append(paramXmlResourceParser.getPositionDescription());
        Slog.w("PackageParser", stringBuilder.toString());
        XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
        continue;
      } 
      break;
    } 
    if (!bool) {
      paramArrayOfString[0] = "<manifest> does not contain an <application>";
      this.mParseError = -109;
    } 
    return paramPackage;
  }
  
  private void parseSplitApk(Package paramPackage, int paramInt1, AssetManager paramAssetManager, int paramInt2) throws PackageParserException {
    StringBuilder stringBuilder;
    String str = paramPackage.splitCodePaths[paramInt1];
    this.mParseError = 1;
    this.mArchiveSourcePath = str;
    Package package_2 = null;
    Resources resources = null;
    Package package_3 = null;
    try {
      int i = paramAssetManager.findCookieForPath(str);
    } catch (PackageParserException null) {
    
    } catch (Exception exception) {
      paramPackage = package_3;
      Package package_ = paramPackage;
    } finally {
      StringBuilder stringBuilder1;
      paramPackage = null;
    } 
    Package package_1 = paramPackage;
    throw exception;
  }
  
  private boolean parseSplitApplication(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt1, int paramInt2, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    // Byte code:
    //   0: aload_0
    //   1: astore #7
    //   3: aload_1
    //   4: astore #8
    //   6: aload_2
    //   7: astore #9
    //   9: aload_3
    //   10: astore #10
    //   12: aload #6
    //   14: astore #11
    //   16: aload #9
    //   18: aload #10
    //   20: getstatic com/android/internal/R$styleable.AndroidManifestApplication : [I
    //   23: invokevirtual obtainAttributes : (Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
    //   26: astore #12
    //   28: aload #12
    //   30: bipush #7
    //   32: iconst_1
    //   33: invokevirtual getBoolean : (IZ)Z
    //   36: ifeq -> 58
    //   39: aload #8
    //   41: getfield splitFlags : [I
    //   44: astore #13
    //   46: aload #13
    //   48: iload #5
    //   50: aload #13
    //   52: iload #5
    //   54: iaload
    //   55: iconst_4
    //   56: ior
    //   57: iastore
    //   58: aload #12
    //   60: bipush #46
    //   62: invokevirtual getString : (I)Ljava/lang/String;
    //   65: astore #12
    //   67: bipush #-108
    //   69: istore #14
    //   71: iconst_0
    //   72: istore #15
    //   74: aload #12
    //   76: ifnull -> 130
    //   79: aload #12
    //   81: invokestatic isValidClassLoaderName : (Ljava/lang/String;)Z
    //   84: ifeq -> 90
    //   87: goto -> 130
    //   90: new java/lang/StringBuilder
    //   93: dup
    //   94: invokespecial <init> : ()V
    //   97: astore_1
    //   98: aload_1
    //   99: ldc_w 'Invalid class loader name: '
    //   102: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: pop
    //   106: aload_1
    //   107: aload #12
    //   109: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: aload #11
    //   115: iconst_0
    //   116: aload_1
    //   117: invokevirtual toString : ()Ljava/lang/String;
    //   120: aastore
    //   121: aload #7
    //   123: bipush #-108
    //   125: putfield mParseError : I
    //   128: iconst_0
    //   129: ireturn
    //   130: aload #8
    //   132: getfield applicationInfo : Landroid/content/pm/ApplicationInfo;
    //   135: getfield splitClassLoaderNames : [Ljava/lang/String;
    //   138: iload #5
    //   140: aload #12
    //   142: aastore
    //   143: aload_3
    //   144: invokeinterface getDepth : ()I
    //   149: istore #16
    //   151: aload_3
    //   152: invokeinterface next : ()I
    //   157: istore #17
    //   159: iload #17
    //   161: iconst_1
    //   162: if_icmpeq -> 991
    //   165: iload #17
    //   167: iconst_3
    //   168: if_icmpne -> 188
    //   171: aload_3
    //   172: invokeinterface getDepth : ()I
    //   177: iload #16
    //   179: if_icmple -> 185
    //   182: goto -> 188
    //   185: goto -> 991
    //   188: iload #17
    //   190: iconst_3
    //   191: if_icmpeq -> 988
    //   194: iload #17
    //   196: iconst_4
    //   197: if_icmpne -> 203
    //   200: goto -> 988
    //   203: new android/content/pm/PackageParser$CachedComponentArgs
    //   206: dup
    //   207: aconst_null
    //   208: invokespecial <init> : (Landroid/content/pm/PackageParser$1;)V
    //   211: astore #18
    //   213: aload_3
    //   214: invokeinterface getName : ()Ljava/lang/String;
    //   219: astore #19
    //   221: aload #19
    //   223: ldc_w 'activity'
    //   226: invokevirtual equals : (Ljava/lang/Object;)Z
    //   229: ifeq -> 291
    //   232: aload_0
    //   233: aload_1
    //   234: aload_2
    //   235: aload_3
    //   236: iload #4
    //   238: aload #6
    //   240: aload #18
    //   242: iconst_0
    //   243: aload #8
    //   245: getfield baseHardwareAccelerated : Z
    //   248: invokespecial parseActivity : (Landroid/content/pm/PackageParser$Package;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;I[Ljava/lang/String;Landroid/content/pm/PackageParser$CachedComponentArgs;ZZ)Landroid/content/pm/PackageParser$Activity;
    //   251: astore #9
    //   253: aload #9
    //   255: ifnonnull -> 267
    //   258: aload #7
    //   260: iload #14
    //   262: putfield mParseError : I
    //   265: iconst_0
    //   266: ireturn
    //   267: aload #8
    //   269: getfield activities : Ljava/util/ArrayList;
    //   272: aload #9
    //   274: invokevirtual add : (Ljava/lang/Object;)Z
    //   277: pop
    //   278: aload #9
    //   280: getfield info : Landroid/content/pm/ActivityInfo;
    //   283: astore #9
    //   285: iconst_0
    //   286: istore #15
    //   288: goto -> 871
    //   291: aload #19
    //   293: ldc_w 'receiver'
    //   296: invokevirtual equals : (Ljava/lang/Object;)Z
    //   299: ifeq -> 365
    //   302: aload_0
    //   303: aload_1
    //   304: aload_2
    //   305: aload_3
    //   306: iload #4
    //   308: aload #6
    //   310: aload #18
    //   312: iconst_1
    //   313: iconst_0
    //   314: invokespecial parseActivity : (Landroid/content/pm/PackageParser$Package;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;I[Ljava/lang/String;Landroid/content/pm/PackageParser$CachedComponentArgs;ZZ)Landroid/content/pm/PackageParser$Activity;
    //   317: astore #11
    //   319: aload #11
    //   321: ifnonnull -> 333
    //   324: aload #7
    //   326: bipush #-108
    //   328: putfield mParseError : I
    //   331: iconst_0
    //   332: ireturn
    //   333: bipush #-108
    //   335: istore #14
    //   337: iconst_0
    //   338: istore #15
    //   340: aload #8
    //   342: getfield receivers : Ljava/util/ArrayList;
    //   345: aload #11
    //   347: invokevirtual add : (Ljava/lang/Object;)Z
    //   350: pop
    //   351: aload #11
    //   353: getfield info : Landroid/content/pm/ActivityInfo;
    //   356: astore #9
    //   358: aload #6
    //   360: astore #11
    //   362: goto -> 871
    //   365: aload_2
    //   366: astore #9
    //   368: aload #10
    //   370: astore #20
    //   372: aload #7
    //   374: astore #21
    //   376: iload #15
    //   378: istore #17
    //   380: aload #8
    //   382: astore #13
    //   384: iload #14
    //   386: istore #22
    //   388: aload #19
    //   390: ldc_w 'service'
    //   393: invokevirtual equals : (Ljava/lang/Object;)Z
    //   396: ifeq -> 462
    //   399: aload_0
    //   400: aload_1
    //   401: aload_2
    //   402: aload_3
    //   403: iload #4
    //   405: aload #6
    //   407: aload #18
    //   409: invokespecial parseService : (Landroid/content/pm/PackageParser$Package;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;I[Ljava/lang/String;Landroid/content/pm/PackageParser$CachedComponentArgs;)Landroid/content/pm/PackageParser$Service;
    //   412: astore #11
    //   414: aload #11
    //   416: ifnonnull -> 429
    //   419: aload #21
    //   421: iload #22
    //   423: putfield mParseError : I
    //   426: iload #17
    //   428: ireturn
    //   429: aload #13
    //   431: getfield services : Ljava/util/ArrayList;
    //   434: aload #11
    //   436: invokevirtual add : (Ljava/lang/Object;)Z
    //   439: pop
    //   440: aload #11
    //   442: getfield info : Landroid/content/pm/ServiceInfo;
    //   445: astore #9
    //   447: aload #6
    //   449: astore #11
    //   451: iload #22
    //   453: istore #14
    //   455: iload #17
    //   457: istore #15
    //   459: goto -> 871
    //   462: aload #19
    //   464: ldc_w 'provider'
    //   467: invokevirtual equals : (Ljava/lang/Object;)Z
    //   470: ifeq -> 536
    //   473: aload_0
    //   474: aload_1
    //   475: aload_2
    //   476: aload_3
    //   477: iload #4
    //   479: aload #6
    //   481: aload #18
    //   483: invokespecial parseProvider : (Landroid/content/pm/PackageParser$Package;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;I[Ljava/lang/String;Landroid/content/pm/PackageParser$CachedComponentArgs;)Landroid/content/pm/PackageParser$Provider;
    //   486: astore #11
    //   488: aload #11
    //   490: ifnonnull -> 503
    //   493: aload #21
    //   495: iload #22
    //   497: putfield mParseError : I
    //   500: iload #17
    //   502: ireturn
    //   503: aload #13
    //   505: getfield providers : Ljava/util/ArrayList;
    //   508: aload #11
    //   510: invokevirtual add : (Ljava/lang/Object;)Z
    //   513: pop
    //   514: aload #11
    //   516: getfield info : Landroid/content/pm/ProviderInfo;
    //   519: astore #9
    //   521: aload #6
    //   523: astore #11
    //   525: iload #22
    //   527: istore #14
    //   529: iload #17
    //   531: istore #15
    //   533: goto -> 871
    //   536: aload #19
    //   538: ldc_w 'activity-alias'
    //   541: invokevirtual equals : (Ljava/lang/Object;)Z
    //   544: ifeq -> 610
    //   547: aload_0
    //   548: aload_1
    //   549: aload_2
    //   550: aload_3
    //   551: iload #4
    //   553: aload #6
    //   555: aload #18
    //   557: invokespecial parseActivityAlias : (Landroid/content/pm/PackageParser$Package;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;I[Ljava/lang/String;Landroid/content/pm/PackageParser$CachedComponentArgs;)Landroid/content/pm/PackageParser$Activity;
    //   560: astore #11
    //   562: aload #11
    //   564: ifnonnull -> 577
    //   567: aload #21
    //   569: iload #22
    //   571: putfield mParseError : I
    //   574: iload #17
    //   576: ireturn
    //   577: aload #13
    //   579: getfield activities : Ljava/util/ArrayList;
    //   582: aload #11
    //   584: invokevirtual add : (Ljava/lang/Object;)Z
    //   587: pop
    //   588: aload #11
    //   590: getfield info : Landroid/content/pm/ActivityInfo;
    //   593: astore #9
    //   595: aload #6
    //   597: astore #11
    //   599: iload #22
    //   601: istore #14
    //   603: iload #17
    //   605: istore #15
    //   607: goto -> 871
    //   610: aload_3
    //   611: invokeinterface getName : ()Ljava/lang/String;
    //   616: ldc_w 'meta-data'
    //   619: invokevirtual equals : (Ljava/lang/Object;)Z
    //   622: ifeq -> 665
    //   625: aload #21
    //   627: aload #9
    //   629: aload #20
    //   631: aload #13
    //   633: getfield mAppMetaData : Landroid/os/Bundle;
    //   636: aload #6
    //   638: invokespecial parseMetaData : (Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;Landroid/os/Bundle;[Ljava/lang/String;)Landroid/os/Bundle;
    //   641: astore #11
    //   643: aload #13
    //   645: aload #11
    //   647: putfield mAppMetaData : Landroid/os/Bundle;
    //   650: aload #11
    //   652: ifnonnull -> 856
    //   655: aload #21
    //   657: iload #22
    //   659: putfield mParseError : I
    //   662: iload #17
    //   664: ireturn
    //   665: aload #6
    //   667: astore #11
    //   669: aload #19
    //   671: ldc_w 'uses-static-library'
    //   674: invokevirtual equals : (Ljava/lang/Object;)Z
    //   677: ifeq -> 699
    //   680: aload #21
    //   682: aload #13
    //   684: aload #9
    //   686: aload #20
    //   688: aload #11
    //   690: invokespecial parseUsesStaticLibrary : (Landroid/content/pm/PackageParser$Package;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;[Ljava/lang/String;)Z
    //   693: ifne -> 856
    //   696: iload #17
    //   698: ireturn
    //   699: aload #19
    //   701: ldc_w 'uses-library'
    //   704: invokevirtual equals : (Ljava/lang/Object;)Z
    //   707: ifeq -> 841
    //   710: aload #9
    //   712: aload #20
    //   714: getstatic com/android/internal/R$styleable.AndroidManifestUsesLibrary : [I
    //   717: invokevirtual obtainAttributes : (Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
    //   720: astore #9
    //   722: aload #9
    //   724: iload #17
    //   726: invokevirtual getNonResourceString : (I)Ljava/lang/String;
    //   729: astore #21
    //   731: aload #9
    //   733: iconst_1
    //   734: iconst_1
    //   735: invokevirtual getBoolean : (IZ)Z
    //   738: istore #23
    //   740: aload #9
    //   742: invokevirtual recycle : ()V
    //   745: aload #21
    //   747: ifnull -> 823
    //   750: aload #21
    //   752: invokevirtual intern : ()Ljava/lang/String;
    //   755: astore #9
    //   757: iload #23
    //   759: ifeq -> 795
    //   762: aload #13
    //   764: aload #13
    //   766: getfield usesLibraries : Ljava/util/ArrayList;
    //   769: aload #9
    //   771: invokestatic add : (Ljava/util/ArrayList;Ljava/lang/Object;)Ljava/util/ArrayList;
    //   774: putfield usesLibraries : Ljava/util/ArrayList;
    //   777: aload #13
    //   779: aload #13
    //   781: getfield usesOptionalLibraries : Ljava/util/ArrayList;
    //   784: aload #9
    //   786: invokestatic remove : (Ljava/util/ArrayList;Ljava/lang/Object;)Ljava/util/ArrayList;
    //   789: putfield usesOptionalLibraries : Ljava/util/ArrayList;
    //   792: goto -> 823
    //   795: aload #13
    //   797: getfield usesLibraries : Ljava/util/ArrayList;
    //   800: aload #9
    //   802: invokestatic contains : (Ljava/util/Collection;Ljava/lang/Object;)Z
    //   805: ifne -> 823
    //   808: aload #13
    //   810: aload #13
    //   812: getfield usesOptionalLibraries : Ljava/util/ArrayList;
    //   815: aload #9
    //   817: invokestatic add : (Ljava/util/ArrayList;Ljava/lang/Object;)Ljava/util/ArrayList;
    //   820: putfield usesOptionalLibraries : Ljava/util/ArrayList;
    //   823: aload_3
    //   824: invokestatic skipCurrentTag : (Lorg/xmlpull/v1/XmlPullParser;)V
    //   827: aconst_null
    //   828: astore #9
    //   830: iload #22
    //   832: istore #14
    //   834: iload #17
    //   836: istore #15
    //   838: goto -> 871
    //   841: aload #19
    //   843: ldc_w 'uses-package'
    //   846: invokevirtual equals : (Ljava/lang/Object;)Z
    //   849: ifeq -> 903
    //   852: aload_3
    //   853: invokestatic skipCurrentTag : (Lorg/xmlpull/v1/XmlPullParser;)V
    //   856: aload #6
    //   858: astore #11
    //   860: aconst_null
    //   861: astore #9
    //   863: iload #17
    //   865: istore #15
    //   867: iload #22
    //   869: istore #14
    //   871: aload #9
    //   873: ifnull -> 897
    //   876: aload #9
    //   878: getfield splitName : Ljava/lang/String;
    //   881: ifnonnull -> 897
    //   884: aload #9
    //   886: aload #8
    //   888: getfield splitNames : [Ljava/lang/String;
    //   891: iload #5
    //   893: aaload
    //   894: putfield splitName : Ljava/lang/String;
    //   897: aload_2
    //   898: astore #9
    //   900: goto -> 151
    //   903: new java/lang/StringBuilder
    //   906: dup
    //   907: invokespecial <init> : ()V
    //   910: astore #13
    //   912: aload #13
    //   914: ldc_w 'Unknown element under <application>: '
    //   917: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   920: pop
    //   921: aload #13
    //   923: aload #19
    //   925: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   928: pop
    //   929: aload #13
    //   931: ldc_w ' at '
    //   934: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   937: pop
    //   938: aload #13
    //   940: aload #21
    //   942: getfield mArchiveSourcePath : Ljava/lang/String;
    //   945: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   948: pop
    //   949: aload #13
    //   951: ldc_w ' '
    //   954: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   957: pop
    //   958: aload #13
    //   960: aload_3
    //   961: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   966: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   969: pop
    //   970: ldc 'PackageParser'
    //   972: aload #13
    //   974: invokevirtual toString : ()Ljava/lang/String;
    //   977: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   980: pop
    //   981: aload_3
    //   982: invokestatic skipCurrentTag : (Lorg/xmlpull/v1/XmlPullParser;)V
    //   985: goto -> 988
    //   988: goto -> 151
    //   991: iconst_1
    //   992: ireturn
  }
  
  private FeatureInfo parseUsesFeature(Resources paramResources, AttributeSet paramAttributeSet) {
    FeatureInfo featureInfo = new FeatureInfo();
    TypedArray typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.AndroidManifestUsesFeature);
    featureInfo.name = typedArray.getNonResourceString(0);
    featureInfo.version = typedArray.getInt(3, 0);
    if (featureInfo.name == null)
      featureInfo.reqGlEsVersion = typedArray.getInt(1, 0); 
    if (typedArray.getBoolean(2, true))
      featureInfo.flags |= 0x1; 
    typedArray.recycle();
    return featureInfo;
  }
  
  private boolean parseUsesPermission(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws XmlPullParserException, IOException {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestUsesPermission);
    String str1 = typedArray.getNonResourceString(0);
    byte b = 0;
    TypedValue typedValue = typedArray.peekValue(1);
    int i = b;
    if (typedValue != null) {
      i = b;
      if (typedValue.type >= 16) {
        i = b;
        if (typedValue.type <= 31)
          i = typedValue.data; 
      } 
    } 
    String str3 = typedArray.getNonConfigurationString(2, 0);
    String str2 = typedArray.getNonConfigurationString(3, 0);
    typedArray.recycle();
    XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
    if (str1 == null)
      return true; 
    if (i != 0 && i < Build.VERSION.RESOURCES_SDK_INT)
      return true; 
    if (str3 != null) {
      Callback callback = this.mCallback;
      if (callback != null && !callback.hasFeature(str3))
        return true; 
    } 
    if (str2 != null) {
      Callback callback = this.mCallback;
      if (callback != null && callback.hasFeature(str2))
        return true; 
    } 
    if (paramPackage.requestedPermissions.indexOf(str1) == -1) {
      paramPackage.requestedPermissions.add(str1.intern());
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Ignoring duplicate uses-permissions/uses-permissions-sdk-m: ");
      stringBuilder.append(str1);
      stringBuilder.append(" in package: ");
      stringBuilder.append(paramPackage.packageName);
      stringBuilder.append(" at: ");
      stringBuilder.append(paramXmlResourceParser.getPositionDescription());
      Slog.w("PackageParser", stringBuilder.toString());
    } 
    return true;
  }
  
  private boolean parseUsesStaticLibrary(Package paramPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, String[] paramArrayOfString) throws XmlPullParserException, IOException {
    StringBuilder stringBuilder;
    String[] arrayOfString1;
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestUsesStaticLibrary);
    String str2 = typedArray.getNonResourceString(0);
    int i = typedArray.getInt(1, -1);
    String str3 = typedArray.getNonResourceString(2);
    typedArray.recycle();
    if (str2 == null || i < 0 || str3 == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Bad uses-static-library declaration name: ");
      stringBuilder.append(str2);
      stringBuilder.append(" version: ");
      stringBuilder.append(i);
      stringBuilder.append(" certDigest");
      stringBuilder.append(str3);
      paramArrayOfString[0] = stringBuilder.toString();
      this.mParseError = -108;
      XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
      return false;
    } 
    if (((Package)stringBuilder).usesStaticLibraries != null && ((Package)stringBuilder).usesStaticLibraries.contains(str2)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Depending on multiple versions of static library ");
      stringBuilder.append(str2);
      paramArrayOfString[0] = stringBuilder.toString();
      this.mParseError = -108;
      XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
      return false;
    } 
    str2 = str2.intern();
    String str1 = str3.replace(":", "").toLowerCase();
    String[] arrayOfString3 = EmptyArray.STRING;
    if (((Package)stringBuilder).applicationInfo.targetSdkVersion >= 27) {
      arrayOfString2 = parseAdditionalCertificates(paramResources, paramXmlResourceParser, paramArrayOfString);
      arrayOfString1 = arrayOfString2;
      if (arrayOfString2 == null)
        return false; 
    } else {
      XmlUtils.skipCurrentTag((XmlPullParser)arrayOfString2);
      arrayOfString1 = arrayOfString3;
    } 
    String[] arrayOfString2 = new String[arrayOfString1.length + 1];
    arrayOfString2[0] = str1;
    System.arraycopy(arrayOfString1, 0, arrayOfString2, 1, arrayOfString1.length);
    ((Package)stringBuilder).usesStaticLibraries = ArrayUtils.add(((Package)stringBuilder).usesStaticLibraries, str2);
    ((Package)stringBuilder).usesStaticLibrariesVersions = ArrayUtils.appendLong(((Package)stringBuilder).usesStaticLibrariesVersions, i, true);
    ((Package)stringBuilder).usesStaticLibrariesCertDigests = (String[][])ArrayUtils.appendElement(String[].class, (Object[])((Package)stringBuilder).usesStaticLibrariesCertDigests, arrayOfString2, true);
    return true;
  }
  
  private static VerifierInfo parseVerifier(AttributeSet paramAttributeSet) {
    StringBuilder stringBuilder;
    String str1 = null;
    String str2 = null;
    int i = paramAttributeSet.getAttributeCount();
    for (byte b = 0; b < i; b++) {
      int j = paramAttributeSet.getAttributeNameResource(b);
      if (j != 16842755) {
        if (j == 16843686)
          str2 = paramAttributeSet.getAttributeValue(b); 
      } else {
        str1 = paramAttributeSet.getAttributeValue(b);
      } 
    } 
    if (str1 == null || str1.length() == 0) {
      Slog.i("PackageParser", "verifier package name was null; skipping");
      return null;
    } 
    PublicKey publicKey = parsePublicKey(str2);
    if (publicKey == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to parse verifier public key for ");
      stringBuilder.append(str1);
      Slog.i("PackageParser", stringBuilder.toString());
      return null;
    } 
    return new VerifierInfo(str1, (PublicKey)stringBuilder);
  }
  
  public static void readConfigUseRoundIcon(Resources paramResources) {
    if (paramResources != null) {
      sUseRoundIcon = paramResources.getBoolean(17891572);
      return;
    } 
    try {
      ApplicationInfo applicationInfo = ActivityThread.getPackageManager().getApplicationInfo("android", 0, UserHandle.myUserId());
      Resources resources = Resources.getSystem();
      sUseRoundIcon = ResourcesManager.getInstance().getResources(null, null, null, applicationInfo.resourceDirs, applicationInfo.sharedLibraryFiles, 0, null, resources.getCompatibilityInfo(), resources.getClassLoader(), null).getBoolean(17891572);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private void resolveWindowLayout(Activity paramActivity) {
    if (paramActivity.metaData == null || !paramActivity.metaData.containsKey("android.activity_window_layout_affinity"))
      return; 
    ActivityInfo activityInfo = paramActivity.info;
    if (activityInfo.windowLayout != null && activityInfo.windowLayout.windowLayoutAffinity != null)
      return; 
    String str = paramActivity.metaData.getString("android.activity_window_layout_affinity");
    if (activityInfo.windowLayout == null)
      activityInfo.windowLayout = new ActivityInfo.WindowLayout(-1, -1.0F, -1, -1.0F, 0, -1, -1); 
    activityInfo.windowLayout.windowLayoutAffinity = str;
  }
  
  private void setActivityResizeMode(ActivityInfo paramActivityInfo, TypedArray paramTypedArray, Package paramPackage) {
    int i = paramPackage.applicationInfo.privateFlags;
    boolean bool = true;
    if ((i & 0xC00) != 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if (paramTypedArray.hasValue(40) || i != 0) {
      if ((paramPackage.applicationInfo.privateFlags & 0x400) == 0)
        bool = false; 
      if (paramTypedArray.getBoolean(40, bool)) {
        paramActivityInfo.resizeMode = 2;
      } else {
        paramActivityInfo.resizeMode = 0;
      } 
      return;
    } 
    if ((paramPackage.applicationInfo.privateFlags & 0x1000) != 0) {
      paramActivityInfo.resizeMode = 1;
      return;
    } 
    if (paramActivityInfo.isFixedOrientationPortrait()) {
      paramActivityInfo.resizeMode = 6;
    } else if (paramActivityInfo.isFixedOrientationLandscape()) {
      paramActivityInfo.resizeMode = 5;
    } else if (paramActivityInfo.isFixedOrientation()) {
      paramActivityInfo.resizeMode = 7;
    } else {
      paramActivityInfo.resizeMode = 4;
    } 
  }
  
  public static void setCompatibilityModeEnabled(boolean paramBoolean) {
    sCompatibilityModeEnabled = paramBoolean;
  }
  
  private void setMaxAspectRatio(Package paramPackage) {
    float f1;
    float f2;
    if (paramPackage.applicationInfo.targetSdkVersion < 26) {
      f1 = 1.86F;
    } else {
      f1 = 0.0F;
    } 
    if (paramPackage.applicationInfo.maxAspectRatio != 0.0F) {
      f2 = paramPackage.applicationInfo.maxAspectRatio;
    } else {
      f2 = f1;
      if (paramPackage.mAppMetaData != null) {
        f2 = f1;
        if (paramPackage.mAppMetaData.containsKey("android.max_aspect"))
          f2 = paramPackage.mAppMetaData.getFloat("android.max_aspect", f1); 
      } 
    } 
    for (Activity activity : paramPackage.activities) {
      if (activity.hasMaxAspectRatio())
        continue; 
      if (activity.metaData != null) {
        f1 = activity.metaData.getFloat("android.max_aspect", f2);
      } else {
        f1 = f2;
      } 
      activity.setMaxAspectRatio(f1);
    } 
  }
  
  private void setMinAspectRatio(Package paramPackage) {
    float f1 = paramPackage.applicationInfo.minAspectRatio;
    float f2 = 0.0F;
    if (f1 != 0.0F) {
      f2 = paramPackage.applicationInfo.minAspectRatio;
    } else if (paramPackage.applicationInfo.targetSdkVersion < 29) {
      Callback callback = this.mCallback;
      if (callback != null && callback.hasFeature("android.hardware.type.watch")) {
        f2 = 1.0F;
      } else {
        f2 = 1.333F;
      } 
    } 
    for (Activity activity : paramPackage.activities) {
      if (activity.hasMinAspectRatio())
        continue; 
      activity.setMinAspectRatio(f2);
    } 
  }
  
  private void setSupportsSizeChanges(Package paramPackage) {
    boolean bool;
    if (paramPackage.mAppMetaData != null && paramPackage.mAppMetaData.getBoolean("android.supports_size_changes", false)) {
      bool = true;
    } else {
      bool = false;
    } 
    for (Activity activity : paramPackage.activities) {
      if (bool || (activity.metaData != null && activity.metaData.getBoolean("android.supports_size_changes", false)))
        activity.info.supportsSizeChanges = true; 
    } 
  }
  
  public static ArraySet<PublicKey> toSigningKeys(Signature[] paramArrayOfSignature) throws CertificateException {
    ArraySet<PublicKey> arraySet = new ArraySet(paramArrayOfSignature.length);
    for (byte b = 0; b < paramArrayOfSignature.length; b++)
      arraySet.add(paramArrayOfSignature[b].getPublicKey()); 
    return arraySet;
  }
  
  private static void updateApplicationInfo(ApplicationInfo paramApplicationInfo, int paramInt, PackageUserState paramPackageUserState) {
    if (!sCompatibilityModeEnabled)
      paramApplicationInfo.disableCompatibilityMode(); 
    if (paramPackageUserState.installed) {
      paramApplicationInfo.flags |= 0x800000;
    } else {
      paramApplicationInfo.flags &= 0xFF7FFFFF;
    } 
    if (paramPackageUserState.suspended) {
      paramApplicationInfo.flags |= 0x40000000;
    } else {
      paramApplicationInfo.flags &= 0xBFFFFFFF;
    } 
    if (paramPackageUserState.instantApp) {
      paramApplicationInfo.privateFlags |= 0x80;
    } else {
      paramApplicationInfo.privateFlags &= 0xFFFFFF7F;
    } 
    if (paramPackageUserState.virtualPreload) {
      paramApplicationInfo.privateFlags |= 0x10000;
    } else {
      paramApplicationInfo.privateFlags &= 0xFFFEFFFF;
    } 
    boolean bool = paramPackageUserState.hidden;
    boolean bool1 = true;
    if (bool) {
      paramApplicationInfo.privateFlags |= 0x1;
    } else {
      paramApplicationInfo.privateFlags &= 0xFFFFFFFE;
    } 
    if (paramPackageUserState.enabled == 1) {
      paramApplicationInfo.enabled = true;
    } else if (paramPackageUserState.enabled == 4) {
      if ((0x8000 & paramInt) == 0)
        bool1 = false; 
      paramApplicationInfo.enabled = bool1;
    } else if (paramPackageUserState.enabled == 2 || paramPackageUserState.enabled == 3) {
      paramApplicationInfo.enabled = false;
    } 
    paramApplicationInfo.enabledSetting = paramPackageUserState.enabled;
    if (paramApplicationInfo.category == -1)
      paramApplicationInfo.category = paramPackageUserState.categoryHint; 
    if (paramApplicationInfo.category == -1)
      paramApplicationInfo.category = FallbackCategoryProvider.getFallbackCategory(paramApplicationInfo.packageName); 
    paramApplicationInfo.seInfoUser = SELinuxUtil.assignSeinfoUser(paramPackageUserState);
    paramApplicationInfo.resourceDirs = paramPackageUserState.getAllOverlayPaths();
    if (sUseRoundIcon && paramApplicationInfo.roundIconRes != 0) {
      paramInt = paramApplicationInfo.roundIconRes;
    } else {
      paramInt = paramApplicationInfo.iconRes;
    } 
    paramApplicationInfo.icon = paramInt;
  }
  
  public static String validateName(String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual length : ()I
    //   4: istore_3
    //   5: iconst_0
    //   6: istore #4
    //   8: iconst_1
    //   9: istore #5
    //   11: iconst_0
    //   12: istore #6
    //   14: iload #6
    //   16: iload_3
    //   17: if_icmpge -> 174
    //   20: aload_0
    //   21: iload #6
    //   23: invokevirtual charAt : (I)C
    //   26: istore #7
    //   28: iload #7
    //   30: bipush #97
    //   32: if_icmplt -> 42
    //   35: iload #7
    //   37: bipush #122
    //   39: if_icmple -> 56
    //   42: iload #7
    //   44: bipush #65
    //   46: if_icmplt -> 66
    //   49: iload #7
    //   51: bipush #90
    //   53: if_icmpgt -> 66
    //   56: iconst_0
    //   57: istore #8
    //   59: iload #4
    //   61: istore #9
    //   63: goto -> 124
    //   66: iload #5
    //   68: ifne -> 111
    //   71: iload #7
    //   73: bipush #48
    //   75: if_icmplt -> 93
    //   78: iload #4
    //   80: istore #9
    //   82: iload #5
    //   84: istore #8
    //   86: iload #7
    //   88: bipush #57
    //   90: if_icmple -> 124
    //   93: iload #7
    //   95: bipush #95
    //   97: if_icmpne -> 111
    //   100: iload #4
    //   102: istore #9
    //   104: iload #5
    //   106: istore #8
    //   108: goto -> 124
    //   111: iload #7
    //   113: bipush #46
    //   115: if_icmpne -> 138
    //   118: iconst_1
    //   119: istore #9
    //   121: iconst_1
    //   122: istore #8
    //   124: iinc #6, 1
    //   127: iload #9
    //   129: istore #4
    //   131: iload #8
    //   133: istore #5
    //   135: goto -> 14
    //   138: new java/lang/StringBuilder
    //   141: dup
    //   142: invokespecial <init> : ()V
    //   145: astore_0
    //   146: aload_0
    //   147: ldc_w 'bad character ''
    //   150: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: pop
    //   154: aload_0
    //   155: iload #7
    //   157: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   160: pop
    //   161: aload_0
    //   162: ldc_w '''
    //   165: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: pop
    //   169: aload_0
    //   170: invokevirtual toString : ()Ljava/lang/String;
    //   173: areturn
    //   174: iload_2
    //   175: ifeq -> 189
    //   178: aload_0
    //   179: invokestatic isValidExtFilename : (Ljava/lang/String;)Z
    //   182: ifne -> 189
    //   185: ldc_w 'Invalid filename'
    //   188: areturn
    //   189: iload #4
    //   191: ifne -> 208
    //   194: iload_1
    //   195: ifne -> 201
    //   198: goto -> 208
    //   201: ldc_w 'must have at least one '.' separator'
    //   204: astore_0
    //   205: goto -> 210
    //   208: aconst_null
    //   209: astore_0
    //   210: aload_0
    //   211: areturn
  }
  
  public Package parseMonolithicPackage(File paramFile, int paramInt) throws PackageParserException {
    PackageLite packageLite = parseMonolithicPackageLite(paramFile, paramInt);
    if (!this.mOnlyCoreApps || packageLite.coreApp) {
      DefaultSplitAssetLoader defaultSplitAssetLoader = new DefaultSplitAssetLoader(packageLite, paramInt);
      try {
        Package package_ = parseBaseApk(paramFile, defaultSplitAssetLoader.getBaseAssetManager(), paramInt);
        package_.setCodePath(paramFile.getCanonicalPath());
        package_.setUse32bitAbi(packageLite.use32bitAbi);
        IoUtils.closeQuietly((AutoCloseable)defaultSplitAssetLoader);
        return package_;
      } catch (IOException iOException) {
        PackageParserException packageParserException = new PackageParserException();
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("Failed to get path: ");
        stringBuilder1.append(paramFile);
        this(-102, stringBuilder1.toString(), iOException);
        throw packageParserException;
      } finally {}
      IoUtils.closeQuietly((AutoCloseable)defaultSplitAssetLoader);
      throw paramFile;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Not a coreApp: ");
    stringBuilder.append(paramFile);
    throw new PackageParserException(-108, stringBuilder.toString());
  }
  
  public Package parsePackage(File paramFile, int paramInt) throws PackageParserException {
    return parsePackage(paramFile, paramInt, false);
  }
  
  public Package parsePackage(File paramFile, int paramInt, boolean paramBoolean) throws PackageParserException {
    return paramFile.isDirectory() ? parseClusterPackage(paramFile, paramInt) : parseMonolithicPackage(paramFile, paramInt);
  }
  
  public void setCacheDir(File paramFile) {
    this.mCacheDir = paramFile;
  }
  
  public void setCallback(Callback paramCallback) {
    this.mCallback = paramCallback;
  }
  
  public void setDisplayMetrics(DisplayMetrics paramDisplayMetrics) {
    this.mMetrics = paramDisplayMetrics;
  }
  
  public void setOnlyCoreApps(boolean paramBoolean) {
    this.mOnlyCoreApps = paramBoolean;
  }
  
  public void setSeparateProcesses(String[] paramArrayOfString) {
    this.mSeparateProcesses = paramArrayOfString;
  }
  
  static {
    boolean bool;
  }
  
  public static final String ANDROID_MANIFEST_FILENAME = "AndroidManifest.xml";
  
  public static final String ANDROID_RESOURCES = "http://schemas.android.com/apk/res/android";
  
  public static final String APEX_FILE_EXTENSION = ".apex";
  
  public static final String APK_FILE_EXTENSION = ".apk";
  
  public static final Set<String> CHILD_PACKAGE_TAGS;
  
  public static final boolean DEBUG_BACKUP = false;
  
  public static final boolean DEBUG_JAR = false;
  
  public static final boolean DEBUG_PARSER = false;
  
  private static final int DEFAULT_MIN_SDK_VERSION = 1;
  
  public static final float DEFAULT_PRE_O_MAX_ASPECT_RATIO = 1.86F;
  
  public static final float DEFAULT_PRE_Q_MIN_ASPECT_RATIO = 1.333F;
  
  public static final float DEFAULT_PRE_Q_MIN_ASPECT_RATIO_WATCH = 1.0F;
  
  private static final int DEFAULT_TARGET_SDK_VERSION = 0;
  
  public static final boolean LOG_PARSE_TIMINGS = Build.IS_DEBUGGABLE;
  
  public static final int LOG_PARSE_TIMINGS_THRESHOLD_MS = 100;
  
  public static final boolean LOG_UNSAFE_BROADCASTS = false;
  
  public static final String METADATA_ACTIVITY_WINDOW_LAYOUT_AFFINITY = "android.activity_window_layout_affinity";
  
  public static final String METADATA_MAX_ASPECT_RATIO = "android.max_aspect";
  
  public static final String METADATA_SUPPORTS_SIZE_CHANGES = "android.supports_size_changes";
  
  public static final String MNT_EXPAND = "/mnt/expand/";
  
  public static final boolean MULTI_PACKAGE_APK_ENABLED;
  
  public static final NewPermissionInfo[] NEW_PERMISSIONS;
  
  public static final int PARSE_CHATTY = -2147483648;
  
  public static final int PARSE_COLLECT_CERTIFICATES = 32;
  
  public static final int PARSE_DEFAULT_INSTALL_LOCATION = -1;
  
  public static final int PARSE_DEFAULT_TARGET_SANDBOX = 1;
  
  public static final int PARSE_ENFORCE_CODE = 64;
  
  public static final int PARSE_EXTERNAL_STORAGE = 8;
  
  public static final int PARSE_IGNORE_PROCESSES = 2;
  
  public static final int PARSE_IS_SYSTEM_DIR = 16;
  
  public static final int PARSE_MUST_BE_APK = 1;
  
  private static final String PROPERTY_CHILD_PACKAGES_ENABLED = "persist.sys.child_packages_enabled";
  
  private static final int RECREATE_ON_CONFIG_CHANGES_MASK = 3;
  
  public static final boolean RIGID_PARSER = false;
  
  public static final Set<String> SAFE_BROADCASTS;
  
  public static final String[] SDK_CODENAMES;
  
  public static final int SDK_VERSION;
  
  private static final String TAG = "PackageParser";
  
  public static final String TAG_ADOPT_PERMISSIONS = "adopt-permissions";
  
  public static final String TAG_APPLICATION = "application";
  
  public static final String TAG_ATTRIBUTION = "attribution";
  
  public static final String TAG_COMPATIBLE_SCREENS = "compatible-screens";
  
  public static final String TAG_EAT_COMMENT = "eat-comment";
  
  public static final String TAG_FEATURE_GROUP = "feature-group";
  
  public static final String TAG_INSTRUMENTATION = "instrumentation";
  
  public static final String TAG_KEY_SETS = "key-sets";
  
  public static final String TAG_MANIFEST = "manifest";
  
  public static final String TAG_ORIGINAL_PACKAGE = "original-package";
  
  public static final String TAG_OVERLAY = "overlay";
  
  public static final String TAG_PACKAGE = "package";
  
  public static final String TAG_PACKAGE_VERIFIER = "package-verifier";
  
  public static final String TAG_PERMISSION = "permission";
  
  public static final String TAG_PERMISSION_GROUP = "permission-group";
  
  public static final String TAG_PERMISSION_TREE = "permission-tree";
  
  public static final String TAG_PROFILEABLE = "profileable";
  
  public static final String TAG_PROTECTED_BROADCAST = "protected-broadcast";
  
  public static final String TAG_QUERIES = "queries";
  
  public static final String TAG_RESTRICT_UPDATE = "restrict-update";
  
  public static final String TAG_SUPPORTS_INPUT = "supports-input";
  
  public static final String TAG_SUPPORT_SCREENS = "supports-screens";
  
  public static final String TAG_USES_CONFIGURATION = "uses-configuration";
  
  public static final String TAG_USES_FEATURE = "uses-feature";
  
  public static final String TAG_USES_GL_TEXTURE = "uses-gl-texture";
  
  public static final String TAG_USES_PERMISSION = "uses-permission";
  
  public static final String TAG_USES_PERMISSION_SDK_23 = "uses-permission-sdk-23";
  
  public static final String TAG_USES_PERMISSION_SDK_M = "uses-permission-sdk-m";
  
  public static final String TAG_USES_SDK = "uses-sdk";
  
  public static final String TAG_USES_SPLIT = "uses-split";
  
  public static boolean sCompatibilityModeEnabled;
  
  public static final Comparator<String> sSplitNameComparator;
  
  public static boolean sUseRoundIcon;
  
  @Deprecated
  public String mArchiveSourcePath;
  
  private File mCacheDir;
  
  public Callback mCallback;
  
  private DisplayMetrics mMetrics;
  
  private boolean mOnlyCoreApps;
  
  public int mParseError = 1;
  
  private ParsePackageItemArgs mParseInstrumentationArgs;
  
  public String[] mSeparateProcesses;
  
  public static final class Activity extends Component<ActivityIntentInfo> implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Activity>() {
        public PackageParser.Activity createFromParcel(Parcel param2Parcel) {
          return new PackageParser.Activity(param2Parcel);
        }
        
        public PackageParser.Activity[] newArray(int param2Int) {
          return new PackageParser.Activity[param2Int];
        }
      };
    
    public final ActivityInfo info;
    
    private boolean mHasMaxAspectRatio;
    
    private boolean mHasMinAspectRatio;
    
    Activity(PackageParser.Package param1Package, String param1String, ActivityInfo param1ActivityInfo) {
      super(param1Package, new ArrayList<>(0), param1String);
      this.info = param1ActivityInfo;
      param1ActivityInfo.applicationInfo = param1Package.applicationInfo;
    }
    
    public Activity(PackageParser.ParseComponentArgs param1ParseComponentArgs, ActivityInfo param1ActivityInfo) {
      super(param1ParseComponentArgs, param1ActivityInfo);
      this.info = param1ActivityInfo;
      param1ActivityInfo.applicationInfo = param1ParseComponentArgs.owner.applicationInfo;
    }
    
    private Activity(Parcel param1Parcel) {
      super(param1Parcel);
      this.info = (ActivityInfo)param1Parcel.readParcelable(Object.class.getClassLoader());
      this.mHasMaxAspectRatio = param1Parcel.readBoolean();
      this.mHasMinAspectRatio = param1Parcel.readBoolean();
      for (PackageParser.ActivityIntentInfo activityIntentInfo : this.intents) {
        activityIntentInfo.activity = this;
        this.order = Math.max(activityIntentInfo.getOrder(), this.order);
      } 
      if (this.info.permission != null) {
        ActivityInfo activityInfo = this.info;
        activityInfo.permission = activityInfo.permission.intern();
      } 
    }
    
    private boolean hasMaxAspectRatio() {
      return this.mHasMaxAspectRatio;
    }
    
    private boolean hasMinAspectRatio() {
      return this.mHasMinAspectRatio;
    }
    
    private void setMaxAspectRatio(float param1Float) {
      if (this.info.resizeMode == 2 || this.info.resizeMode == 1)
        return; 
      if (param1Float < 1.0F && param1Float != 0.0F)
        return; 
      this.info.maxAspectRatio = param1Float;
      this.mHasMaxAspectRatio = true;
    }
    
    private void setMinAspectRatio(float param1Float) {
      if (this.info.resizeMode == 2 || this.info.resizeMode == 1)
        return; 
      if (param1Float < 1.0F && param1Float != 0.0F)
        return; 
      this.info.minAspectRatio = param1Float;
      this.mHasMinAspectRatio = true;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void setPackageName(String param1String) {
      super.setPackageName(param1String);
      this.info.packageName = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(128);
      stringBuilder.append("Activity{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(' ');
      appendComponentShortName(stringBuilder);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeParcelable(this.info, param1Int | 0x2);
      param1Parcel.writeBoolean(this.mHasMaxAspectRatio);
      param1Parcel.writeBoolean(this.mHasMinAspectRatio);
    }
  }
  
  class null implements Parcelable.Creator<Activity> {
    public PackageParser.Activity createFromParcel(Parcel param1Parcel) {
      return new PackageParser.Activity(param1Parcel);
    }
    
    public PackageParser.Activity[] newArray(int param1Int) {
      return new PackageParser.Activity[param1Int];
    }
  }
  
  public static final class ActivityIntentInfo extends IntentInfo {
    public PackageParser.Activity activity;
    
    public ActivityIntentInfo(PackageParser.Activity param1Activity) {
      this.activity = param1Activity;
    }
    
    public ActivityIntentInfo(Parcel param1Parcel) {
      super(param1Parcel);
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(128);
      stringBuilder.append("ActivityIntentInfo{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(' ');
      this.activity.appendComponentShortName(stringBuilder);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
  
  public static class ApkLite {
    public final String codePath;
    
    public final String configForSplit;
    
    public final boolean coreApp;
    
    public final boolean debuggable;
    
    public final boolean extractNativeLibs;
    
    public final int installLocation;
    
    public boolean isFeatureSplit;
    
    public final boolean isSplitRequired;
    
    public final boolean isolatedSplits;
    
    public final int minSdkVersion;
    
    public final boolean multiArch;
    
    public final boolean overlayIsStatic;
    
    public final int overlayPriority;
    
    public final String packageName;
    
    public final boolean profilableByShell;
    
    public final int revisionCode;
    
    public final PackageParser.SigningDetails signingDetails;
    
    public final String splitName;
    
    public final String targetPackageName;
    
    public final int targetSdkVersion;
    
    public final boolean use32bitAbi;
    
    public final boolean useEmbeddedDex;
    
    public final String usesSplitName;
    
    public final VerifierInfo[] verifiers;
    
    public final int versionCode;
    
    public final int versionCodeMajor;
    
    public ApkLite(String param1String1, String param1String2, String param1String3, boolean param1Boolean1, String param1String4, String param1String5, boolean param1Boolean2, int param1Int1, int param1Int2, int param1Int3, int param1Int4, List<VerifierInfo> param1List, PackageParser.SigningDetails param1SigningDetails, boolean param1Boolean3, boolean param1Boolean4, boolean param1Boolean5, boolean param1Boolean6, boolean param1Boolean7, boolean param1Boolean8, boolean param1Boolean9, boolean param1Boolean10, String param1String6, boolean param1Boolean11, int param1Int5, int param1Int6, int param1Int7) {
      this.codePath = param1String1;
      this.packageName = param1String2;
      this.splitName = param1String3;
      this.isFeatureSplit = param1Boolean1;
      this.configForSplit = param1String4;
      this.usesSplitName = param1String5;
      this.versionCode = param1Int1;
      this.versionCodeMajor = param1Int2;
      this.revisionCode = param1Int3;
      this.installLocation = param1Int4;
      this.signingDetails = param1SigningDetails;
      this.verifiers = param1List.<VerifierInfo>toArray(new VerifierInfo[param1List.size()]);
      this.coreApp = param1Boolean3;
      this.debuggable = param1Boolean4;
      this.profilableByShell = param1Boolean5;
      this.multiArch = param1Boolean6;
      this.use32bitAbi = param1Boolean7;
      this.useEmbeddedDex = param1Boolean8;
      this.extractNativeLibs = param1Boolean9;
      this.isolatedSplits = param1Boolean10;
      this.isSplitRequired = param1Boolean2;
      this.targetPackageName = param1String6;
      this.overlayIsStatic = param1Boolean11;
      this.overlayPriority = param1Int5;
      this.minSdkVersion = param1Int6;
      this.targetSdkVersion = param1Int7;
    }
    
    public long getLongVersionCode() {
      return PackageInfo.composeLongVersionCode(this.versionCodeMajor, this.versionCode);
    }
  }
  
  private static class CachedComponentArgs {
    PackageParser.ParseComponentArgs mActivityAliasArgs;
    
    PackageParser.ParseComponentArgs mActivityArgs;
    
    PackageParser.ParseComponentArgs mProviderArgs;
    
    PackageParser.ParseComponentArgs mServiceArgs;
    
    private CachedComponentArgs() {}
  }
  
  public static interface Callback {
    boolean hasFeature(String param1String);
  }
  
  public static final class CallbackImpl implements Callback {
    private final PackageManager mPm;
    
    public CallbackImpl(PackageManager param1PackageManager) {
      this.mPm = param1PackageManager;
    }
    
    public boolean hasFeature(String param1String) {
      return this.mPm.hasSystemFeature(param1String);
    }
  }
  
  public static abstract class Component<II extends IntentInfo> {
    public final String className;
    
    ComponentName componentName;
    
    String componentShortName;
    
    public final ArrayList<II> intents;
    
    public Bundle metaData;
    
    public int order;
    
    public PackageParser.Package owner;
    
    public Component(Component<II> param1Component) {
      this.owner = param1Component.owner;
      this.intents = param1Component.intents;
      this.className = param1Component.className;
      this.componentName = param1Component.componentName;
      this.componentShortName = param1Component.componentShortName;
    }
    
    public Component(PackageParser.Package param1Package) {
      this.owner = param1Package;
      this.intents = null;
      this.className = null;
    }
    
    public Component(PackageParser.Package param1Package, ArrayList<II> param1ArrayList, String param1String) {
      this.owner = param1Package;
      this.intents = param1ArrayList;
      this.className = param1String;
    }
    
    public Component(PackageParser.ParseComponentArgs param1ParseComponentArgs, ComponentInfo param1ComponentInfo) {
      this(param1ParseComponentArgs, param1ComponentInfo);
      if (param1ParseComponentArgs.outError[0] != null)
        return; 
      if (param1ParseComponentArgs.processRes != 0) {
        String str;
        if (this.owner.applicationInfo.targetSdkVersion >= 8) {
          str = param1ParseComponentArgs.sa.getNonConfigurationString(param1ParseComponentArgs.processRes, 1024);
        } else {
          str = param1ParseComponentArgs.sa.getNonResourceString(param1ParseComponentArgs.processRes);
        } 
        param1ComponentInfo.processName = PackageParser.buildProcessName(this.owner.applicationInfo.packageName, this.owner.applicationInfo.processName, str, param1ParseComponentArgs.flags, param1ParseComponentArgs.sepProcesses, param1ParseComponentArgs.outError);
      } 
      if (param1ParseComponentArgs.descriptionRes != 0)
        param1ComponentInfo.descriptionRes = param1ParseComponentArgs.sa.getResourceId(param1ParseComponentArgs.descriptionRes, 0); 
      param1ComponentInfo.enabled = param1ParseComponentArgs.sa.getBoolean(param1ParseComponentArgs.enabledRes, true);
    }
    
    public Component(PackageParser.ParsePackageItemArgs param1ParsePackageItemArgs, PackageItemInfo param1PackageItemInfo) {
      this.owner = param1ParsePackageItemArgs.owner;
      this.intents = new ArrayList<>(0);
      if (PackageParser.parsePackageItemInfo(param1ParsePackageItemArgs.owner, param1PackageItemInfo, param1ParsePackageItemArgs.outError, param1ParsePackageItemArgs.tag, param1ParsePackageItemArgs.sa, true, param1ParsePackageItemArgs.nameRes, param1ParsePackageItemArgs.labelRes, param1ParsePackageItemArgs.iconRes, param1ParsePackageItemArgs.roundIconRes, param1ParsePackageItemArgs.logoRes, param1ParsePackageItemArgs.bannerRes)) {
        this.className = param1PackageItemInfo.name;
      } else {
        this.className = null;
      } 
    }
    
    protected Component(Parcel param1Parcel) {
      this.className = param1Parcel.readString();
      this.metaData = param1Parcel.readBundle();
      this.intents = createIntentsList(param1Parcel);
      this.owner = null;
    }
    
    private static <T extends PackageParser.IntentInfo> ArrayList<T> createIntentsList(Parcel param1Parcel) {
      int i = param1Parcel.readInt();
      if (i == -1)
        return null; 
      if (i == 0)
        return new ArrayList<>(0); 
      String str = param1Parcel.readString();
      try {
        Constructor<?> constructor = Class.forName(str).getConstructor(new Class[] { Parcel.class });
        ArrayList<PackageParser.IntentInfo> arrayList = new ArrayList();
        this(i);
        for (byte b = 0; b < i; b++) {
          arrayList.add((PackageParser.IntentInfo)constructor.newInstance(new Object[] { param1Parcel }));
        } 
        return (ArrayList)arrayList;
      } catch (ReflectiveOperationException reflectiveOperationException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to construct intent list for: ");
        stringBuilder.append(str);
        throw new AssertionError(stringBuilder.toString());
      } 
    }
    
    private static void writeIntentsList(ArrayList<? extends PackageParser.IntentInfo> param1ArrayList, Parcel param1Parcel, int param1Int) {
      if (param1ArrayList == null) {
        param1Parcel.writeInt(-1);
        return;
      } 
      int i = param1ArrayList.size();
      param1Parcel.writeInt(i);
      if (i > 0) {
        param1Parcel.writeString(((PackageParser.IntentInfo)param1ArrayList.get(0)).getClass().getName());
        for (byte b = 0; b < i; b++)
          ((PackageParser.IntentInfo)param1ArrayList.get(b)).writeIntentInfoToParcel(param1Parcel, param1Int); 
      } 
    }
    
    public void appendComponentShortName(StringBuilder param1StringBuilder) {
      ComponentName.appendShortString(param1StringBuilder, this.owner.applicationInfo.packageName, this.className);
    }
    
    public ComponentName getComponentName() {
      ComponentName componentName = this.componentName;
      if (componentName != null)
        return componentName; 
      if (this.className != null)
        this.componentName = new ComponentName(this.owner.applicationInfo.packageName, this.className); 
      return this.componentName;
    }
    
    public void printComponentShortName(PrintWriter param1PrintWriter) {
      ComponentName.printShortString(param1PrintWriter, this.owner.applicationInfo.packageName, this.className);
    }
    
    public void setPackageName(String param1String) {
      this.componentName = null;
      this.componentShortName = null;
    }
    
    protected void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeString(this.className);
      param1Parcel.writeBundle(this.metaData);
      writeIntentsList(this.intents, param1Parcel, param1Int);
    }
  }
  
  public static final class Instrumentation extends Component<IntentInfo> implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Instrumentation>() {
        public PackageParser.Instrumentation createFromParcel(Parcel param2Parcel) {
          return new PackageParser.Instrumentation(param2Parcel);
        }
        
        public PackageParser.Instrumentation[] newArray(int param2Int) {
          return new PackageParser.Instrumentation[param2Int];
        }
      };
    
    public final InstrumentationInfo info;
    
    public Instrumentation(PackageParser.ParsePackageItemArgs param1ParsePackageItemArgs, InstrumentationInfo param1InstrumentationInfo) {
      super(param1ParsePackageItemArgs, param1InstrumentationInfo);
      this.info = param1InstrumentationInfo;
    }
    
    private Instrumentation(Parcel param1Parcel) {
      super(param1Parcel);
      InstrumentationInfo instrumentationInfo = (InstrumentationInfo)param1Parcel.readParcelable(Object.class.getClassLoader());
      this.info = instrumentationInfo;
      if (instrumentationInfo.targetPackage != null) {
        instrumentationInfo = this.info;
        instrumentationInfo.targetPackage = instrumentationInfo.targetPackage.intern();
      } 
      if (this.info.targetProcesses != null) {
        instrumentationInfo = this.info;
        instrumentationInfo.targetProcesses = instrumentationInfo.targetProcesses.intern();
      } 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void setPackageName(String param1String) {
      super.setPackageName(param1String);
      this.info.packageName = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(128);
      stringBuilder.append("Instrumentation{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(' ');
      appendComponentShortName(stringBuilder);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeParcelable(this.info, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<Instrumentation> {
    public PackageParser.Instrumentation createFromParcel(Parcel param1Parcel) {
      return new PackageParser.Instrumentation(param1Parcel);
    }
    
    public PackageParser.Instrumentation[] newArray(int param1Int) {
      return new PackageParser.Instrumentation[param1Int];
    }
  }
  
  public static abstract class IntentInfo extends IntentFilter {
    public int banner;
    
    public boolean hasDefault;
    
    public int icon;
    
    public int labelRes;
    
    public int logo;
    
    public CharSequence nonLocalizedLabel;
    
    public int preferred;
    
    protected IntentInfo() {}
    
    protected IntentInfo(Parcel param1Parcel) {
      super(param1Parcel);
      int i = param1Parcel.readInt();
      boolean bool = true;
      if (i != 1)
        bool = false; 
      this.hasDefault = bool;
      this.labelRes = param1Parcel.readInt();
      this.nonLocalizedLabel = param1Parcel.readCharSequence();
      this.icon = param1Parcel.readInt();
      this.logo = param1Parcel.readInt();
      this.banner = param1Parcel.readInt();
      this.preferred = param1Parcel.readInt();
    }
    
    public void writeIntentInfoToParcel(Parcel param1Parcel, int param1Int) {
      writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.hasDefault);
      param1Parcel.writeInt(this.labelRes);
      param1Parcel.writeCharSequence(this.nonLocalizedLabel);
      param1Parcel.writeInt(this.icon);
      param1Parcel.writeInt(this.logo);
      param1Parcel.writeInt(this.banner);
      param1Parcel.writeInt(this.preferred);
    }
  }
  
  public static class NewPermissionInfo {
    public final int fileVersion;
    
    public final String name;
    
    public final int sdkVersion;
    
    public NewPermissionInfo(String param1String, int param1Int1, int param1Int2) {
      this.name = param1String;
      this.sdkVersion = param1Int1;
      this.fileVersion = param1Int2;
    }
  }
  
  public static final class Package implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Package>() {
        public PackageParser.Package createFromParcel(Parcel param2Parcel) {
          return new PackageParser.Package(param2Parcel);
        }
        
        public PackageParser.Package[] newArray(int param2Int) {
          return new PackageParser.Package[param2Int];
        }
      };
    
    public final ArrayList<PackageParser.Activity> activities;
    
    public ApplicationInfo applicationInfo = new ApplicationInfo();
    
    public String baseCodePath;
    
    public boolean baseHardwareAccelerated;
    
    public int baseRevisionCode;
    
    public ArrayList<Package> childPackages;
    
    public String codePath;
    
    public ArrayList<ConfigurationInfo> configPreferences;
    
    public boolean coreApp;
    
    public String cpuAbiOverride;
    
    public ArrayList<FeatureGroupInfo> featureGroups;
    
    public final ArrayList<String> implicitPermissions;
    
    public int installLocation;
    
    public final ArrayList<PackageParser.Instrumentation> instrumentation;
    
    public boolean isStub;
    
    public ArrayList<String> libraryNames;
    
    public ArrayList<String> mAdoptPermissions;
    
    public Bundle mAppMetaData;
    
    public int mCompileSdkVersion;
    
    public String mCompileSdkVersionCodename;
    
    public Object mExtras;
    
    public ArrayMap<String, ArraySet<PublicKey>> mKeySetMapping;
    
    public long[] mLastPackageUsageTimeInMills;
    
    public ArrayList<String> mOriginalPackages;
    
    public String mOverlayCategory;
    
    public boolean mOverlayIsStatic;
    
    public int mOverlayPriority;
    
    public String mOverlayTarget;
    
    public String mOverlayTargetName;
    
    public int mPreferredOrder;
    
    public String mRealPackage;
    
    public String mRequiredAccountType;
    
    public boolean mRequiredForAllUsers;
    
    public String mRestrictedAccountType;
    
    public String mSharedUserId;
    
    public int mSharedUserLabel;
    
    public PackageParser.SigningDetails mSigningDetails;
    
    public ArraySet<String> mUpgradeKeySets;
    
    public int mVersionCode;
    
    public int mVersionCodeMajor;
    
    public String mVersionName;
    
    public String manifestPackageName;
    
    public String packageName;
    
    public Package parentPackage;
    
    public final ArrayList<PackageParser.PermissionGroup> permissionGroups;
    
    public final ArrayList<PackageParser.Permission> permissions;
    
    public ArrayList<PackageParser.ActivityIntentInfo> preferredActivityFilters;
    
    public ArrayList<String> protectedBroadcasts;
    
    public final ArrayList<PackageParser.Provider> providers;
    
    public final ArrayList<PackageParser.Activity> receivers;
    
    public ArrayList<FeatureInfo> reqFeatures;
    
    public final ArrayList<String> requestedPermissions;
    
    public byte[] restrictUpdateHash;
    
    public final ArrayList<PackageParser.Service> services;
    
    public String[] splitCodePaths;
    
    public int[] splitFlags;
    
    public String[] splitNames;
    
    public int[] splitPrivateFlags;
    
    public int[] splitRevisionCodes;
    
    public String staticSharedLibName;
    
    public long staticSharedLibVersion;
    
    public boolean use32bitAbi;
    
    public ArrayList<String> usesLibraries;
    
    public String[] usesLibraryFiles;
    
    public ArrayList<SharedLibraryInfo> usesLibraryInfos;
    
    public ArrayList<String> usesOptionalLibraries;
    
    public ArrayList<String> usesStaticLibraries;
    
    public String[][] usesStaticLibrariesCertDigests;
    
    public long[] usesStaticLibrariesVersions;
    
    public boolean visibleToInstantApps;
    
    public String volumeUuid;
    
    public Package(Parcel param1Parcel) {
      boolean bool1 = false;
      this.permissions = new ArrayList<>(0);
      this.permissionGroups = new ArrayList<>(0);
      this.activities = new ArrayList<>(0);
      this.receivers = new ArrayList<>(0);
      this.providers = new ArrayList<>(0);
      this.services = new ArrayList<>(0);
      this.instrumentation = new ArrayList<>(0);
      this.requestedPermissions = new ArrayList<>();
      this.implicitPermissions = new ArrayList<>();
      this.staticSharedLibName = null;
      this.staticSharedLibVersion = 0L;
      this.libraryNames = null;
      this.usesLibraries = null;
      this.usesStaticLibraries = null;
      this.usesStaticLibrariesVersions = null;
      this.usesStaticLibrariesCertDigests = null;
      this.usesOptionalLibraries = null;
      this.usesLibraryFiles = null;
      this.usesLibraryInfos = null;
      this.preferredActivityFilters = null;
      this.mOriginalPackages = null;
      this.mRealPackage = null;
      this.mAdoptPermissions = null;
      this.mAppMetaData = null;
      this.mSigningDetails = PackageParser.SigningDetails.UNKNOWN;
      this.mPreferredOrder = 0;
      this.mLastPackageUsageTimeInMills = new long[8];
      this.configPreferences = null;
      this.reqFeatures = null;
      this.featureGroups = null;
      ClassLoader classLoader = Object.class.getClassLoader();
      this.packageName = param1Parcel.readString().intern();
      this.manifestPackageName = param1Parcel.readString();
      this.splitNames = param1Parcel.readStringArray();
      this.volumeUuid = param1Parcel.readString();
      this.codePath = param1Parcel.readString();
      this.baseCodePath = param1Parcel.readString();
      this.splitCodePaths = param1Parcel.readStringArray();
      this.baseRevisionCode = param1Parcel.readInt();
      this.splitRevisionCodes = param1Parcel.createIntArray();
      this.splitFlags = param1Parcel.createIntArray();
      this.splitPrivateFlags = param1Parcel.createIntArray();
      if (param1Parcel.readInt() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.baseHardwareAccelerated = bool2;
      ApplicationInfo applicationInfo = (ApplicationInfo)param1Parcel.readParcelable(classLoader);
      this.applicationInfo = applicationInfo;
      if (applicationInfo.permission != null) {
        applicationInfo = this.applicationInfo;
        applicationInfo.permission = applicationInfo.permission.intern();
      } 
      param1Parcel.readParcelableList(this.permissions, classLoader);
      fixupOwner((List)this.permissions);
      param1Parcel.readParcelableList(this.permissionGroups, classLoader);
      fixupOwner((List)this.permissionGroups);
      param1Parcel.readParcelableList(this.activities, classLoader);
      fixupOwner((List)this.activities);
      param1Parcel.readParcelableList(this.receivers, classLoader);
      fixupOwner((List)this.receivers);
      param1Parcel.readParcelableList(this.providers, classLoader);
      fixupOwner((List)this.providers);
      param1Parcel.readParcelableList(this.services, classLoader);
      fixupOwner((List)this.services);
      param1Parcel.readParcelableList(this.instrumentation, classLoader);
      fixupOwner((List)this.instrumentation);
      param1Parcel.readStringList(this.requestedPermissions);
      internStringArrayList(this.requestedPermissions);
      param1Parcel.readStringList(this.implicitPermissions);
      internStringArrayList(this.implicitPermissions);
      ArrayList<String> arrayList2 = param1Parcel.createStringArrayList();
      this.protectedBroadcasts = arrayList2;
      internStringArrayList(arrayList2);
      this.parentPackage = (Package)param1Parcel.readParcelable(classLoader);
      arrayList2 = new ArrayList<>();
      this.childPackages = (ArrayList)arrayList2;
      param1Parcel.readParcelableList(arrayList2, classLoader);
      if (this.childPackages.size() == 0)
        this.childPackages = null; 
      String str2 = param1Parcel.readString();
      this.staticSharedLibName = str2;
      if (str2 != null)
        this.staticSharedLibName = str2.intern(); 
      this.staticSharedLibVersion = param1Parcel.readLong();
      ArrayList<String> arrayList1 = param1Parcel.createStringArrayList();
      this.libraryNames = arrayList1;
      internStringArrayList(arrayList1);
      arrayList1 = param1Parcel.createStringArrayList();
      this.usesLibraries = arrayList1;
      internStringArrayList(arrayList1);
      arrayList1 = param1Parcel.createStringArrayList();
      this.usesOptionalLibraries = arrayList1;
      internStringArrayList(arrayList1);
      this.usesLibraryFiles = param1Parcel.readStringArray();
      this.usesLibraryInfos = param1Parcel.createTypedArrayList(SharedLibraryInfo.CREATOR);
      int i = param1Parcel.readInt();
      if (i > 0) {
        arrayList1 = new ArrayList<>(i);
        this.usesStaticLibraries = arrayList1;
        param1Parcel.readStringList(arrayList1);
        internStringArrayList(this.usesStaticLibraries);
        long[] arrayOfLong = new long[i];
        this.usesStaticLibrariesVersions = arrayOfLong;
        param1Parcel.readLongArray(arrayOfLong);
        this.usesStaticLibrariesCertDigests = new String[i][];
        for (byte b = 0; b < i; b++)
          this.usesStaticLibrariesCertDigests[b] = param1Parcel.createStringArray(); 
      } 
      arrayList1 = new ArrayList<>();
      this.preferredActivityFilters = (ArrayList)arrayList1;
      param1Parcel.readParcelableList(arrayList1, classLoader);
      if (this.preferredActivityFilters.size() == 0)
        this.preferredActivityFilters = null; 
      this.mOriginalPackages = param1Parcel.createStringArrayList();
      this.mRealPackage = param1Parcel.readString();
      this.mAdoptPermissions = param1Parcel.createStringArrayList();
      this.mAppMetaData = param1Parcel.readBundle();
      this.mVersionCode = param1Parcel.readInt();
      this.mVersionCodeMajor = param1Parcel.readInt();
      String str1 = param1Parcel.readString();
      this.mVersionName = str1;
      if (str1 != null)
        this.mVersionName = str1.intern(); 
      str1 = param1Parcel.readString();
      this.mSharedUserId = str1;
      if (str1 != null)
        this.mSharedUserId = str1.intern(); 
      this.mSharedUserLabel = param1Parcel.readInt();
      this.mSigningDetails = (PackageParser.SigningDetails)param1Parcel.readParcelable(classLoader);
      this.mPreferredOrder = param1Parcel.readInt();
      ArrayList<ConfigurationInfo> arrayList = new ArrayList();
      this.configPreferences = arrayList;
      param1Parcel.readParcelableList(arrayList, classLoader);
      if (this.configPreferences.size() == 0)
        this.configPreferences = null; 
      arrayList = new ArrayList<>();
      this.reqFeatures = (ArrayList)arrayList;
      param1Parcel.readParcelableList(arrayList, classLoader);
      if (this.reqFeatures.size() == 0)
        this.reqFeatures = null; 
      arrayList = new ArrayList<>();
      this.featureGroups = (ArrayList)arrayList;
      param1Parcel.readParcelableList(arrayList, classLoader);
      if (this.featureGroups.size() == 0)
        this.featureGroups = null; 
      this.installLocation = param1Parcel.readInt();
      if (param1Parcel.readInt() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.coreApp = bool2;
      if (param1Parcel.readInt() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mRequiredForAllUsers = bool2;
      this.mRestrictedAccountType = param1Parcel.readString();
      this.mRequiredAccountType = param1Parcel.readString();
      this.mOverlayTarget = param1Parcel.readString();
      this.mOverlayTargetName = param1Parcel.readString();
      this.mOverlayCategory = param1Parcel.readString();
      this.mOverlayPriority = param1Parcel.readInt();
      if (param1Parcel.readInt() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mOverlayIsStatic = bool2;
      this.mCompileSdkVersion = param1Parcel.readInt();
      this.mCompileSdkVersionCodename = param1Parcel.readString();
      this.mUpgradeKeySets = param1Parcel.readArraySet(classLoader);
      this.mKeySetMapping = readKeySetMapping(param1Parcel);
      this.cpuAbiOverride = param1Parcel.readString();
      if (param1Parcel.readInt() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.use32bitAbi = bool2;
      this.restrictUpdateHash = param1Parcel.createByteArray();
      boolean bool2 = bool1;
      if (param1Parcel.readInt() == 1)
        bool2 = true; 
      this.visibleToInstantApps = bool2;
    }
    
    public Package(String param1String) {
      this.permissions = new ArrayList<>(0);
      this.permissionGroups = new ArrayList<>(0);
      this.activities = new ArrayList<>(0);
      this.receivers = new ArrayList<>(0);
      this.providers = new ArrayList<>(0);
      this.services = new ArrayList<>(0);
      this.instrumentation = new ArrayList<>(0);
      this.requestedPermissions = new ArrayList<>();
      this.implicitPermissions = new ArrayList<>();
      this.staticSharedLibName = null;
      this.staticSharedLibVersion = 0L;
      this.libraryNames = null;
      this.usesLibraries = null;
      this.usesStaticLibraries = null;
      this.usesStaticLibrariesVersions = null;
      this.usesStaticLibrariesCertDigests = null;
      this.usesOptionalLibraries = null;
      this.usesLibraryFiles = null;
      this.usesLibraryInfos = null;
      this.preferredActivityFilters = null;
      this.mOriginalPackages = null;
      this.mRealPackage = null;
      this.mAdoptPermissions = null;
      this.mAppMetaData = null;
      this.mSigningDetails = PackageParser.SigningDetails.UNKNOWN;
      this.mPreferredOrder = 0;
      this.mLastPackageUsageTimeInMills = new long[8];
      this.configPreferences = null;
      this.reqFeatures = null;
      this.featureGroups = null;
      this.packageName = param1String;
      this.manifestPackageName = param1String;
      this.applicationInfo.packageName = param1String;
      this.applicationInfo.uid = -1;
    }
    
    private static void internStringArrayList(List<String> param1List) {
      if (param1List != null) {
        int i = param1List.size();
        for (byte b = 0; b < i; b++)
          param1List.set(b, ((String)param1List.get(b)).intern()); 
      } 
    }
    
    private static ArrayMap<String, ArraySet<PublicKey>> readKeySetMapping(Parcel param1Parcel) {
      int i = param1Parcel.readInt();
      if (i == -1)
        return null; 
      ArrayMap<String, ArraySet<PublicKey>> arrayMap = new ArrayMap();
      for (byte b = 0; b < i; b++) {
        String str = param1Parcel.readString();
        int j = param1Parcel.readInt();
        if (j == -1) {
          arrayMap.put(str, null);
        } else {
          ArraySet arraySet = new ArraySet(j);
          for (byte b1 = 0; b1 < j; b1++)
            arraySet.add(param1Parcel.readSerializable()); 
          arrayMap.put(str, arraySet);
        } 
      } 
      return arrayMap;
    }
    
    private static void writeKeySetMapping(Parcel param1Parcel, ArrayMap<String, ArraySet<PublicKey>> param1ArrayMap) {
      if (param1ArrayMap == null) {
        param1Parcel.writeInt(-1);
        return;
      } 
      int i = param1ArrayMap.size();
      param1Parcel.writeInt(i);
      for (byte b = 0; b < i; b++) {
        param1Parcel.writeString((String)param1ArrayMap.keyAt(b));
        ArraySet arraySet = (ArraySet)param1ArrayMap.valueAt(b);
        if (arraySet == null) {
          param1Parcel.writeInt(-1);
        } else {
          int j = arraySet.size();
          param1Parcel.writeInt(j);
          for (byte b1 = 0; b1 < j; b1++)
            param1Parcel.writeSerializable((Serializable)arraySet.valueAt(b1)); 
        } 
      } 
    }
    
    public boolean canHaveOatDir() {
      return true;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void fixupOwner(List<? extends PackageParser.Component<?>> param1List) {
      if (param1List != null)
        for (PackageParser.Component<?> component : param1List) {
          component.owner = this;
          if (component instanceof PackageParser.Activity) {
            ((PackageParser.Activity)component).info.applicationInfo = this.applicationInfo;
            continue;
          } 
          if (component instanceof PackageParser.Service) {
            ((PackageParser.Service)component).info.applicationInfo = this.applicationInfo;
            continue;
          } 
          if (component instanceof PackageParser.Provider)
            ((PackageParser.Provider)component).info.applicationInfo = this.applicationInfo; 
        }  
    }
    
    public List<String> getAllCodePaths() {
      ArrayList<String> arrayList = new ArrayList();
      arrayList.add(this.baseCodePath);
      if (!ArrayUtils.isEmpty((Object[])this.splitCodePaths))
        Collections.addAll(arrayList, this.splitCodePaths); 
      return arrayList;
    }
    
    public List<String> getAllCodePathsExcludingResourceOnly() {
      ArrayList<String> arrayList = new ArrayList();
      if ((this.applicationInfo.flags & 0x4) != 0)
        arrayList.add(this.baseCodePath); 
      if (!ArrayUtils.isEmpty((Object[])this.splitCodePaths)) {
        byte b = 0;
        while (true) {
          String[] arrayOfString = this.splitCodePaths;
          if (b < arrayOfString.length) {
            if ((this.splitFlags[b] & 0x4) != 0)
              arrayList.add(arrayOfString[b]); 
            b++;
            continue;
          } 
          break;
        } 
      } 
      return arrayList;
    }
    
    public List<String> getChildPackageNames() {
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList == null)
        return null; 
      int i = arrayList.size();
      arrayList = new ArrayList<>(i);
      for (byte b = 0; b < i; b++)
        arrayList.add(((Package)this.childPackages.get(b)).packageName); 
      return (List)arrayList;
    }
    
    public long getLatestForegroundPackageUseTimeInMills() {
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 2;
      long l = 0L;
      int i = arrayOfInt.length;
      for (byte b = 0; b < i; b++) {
        int j = arrayOfInt[b];
        l = Math.max(l, this.mLastPackageUsageTimeInMills[j]);
      } 
      return l;
    }
    
    public long getLatestPackageUseTimeInMills() {
      long l = 0L;
      long[] arrayOfLong = this.mLastPackageUsageTimeInMills;
      int i = arrayOfLong.length;
      for (byte b = 0; b < i; b++)
        l = Math.max(l, arrayOfLong[b]); 
      return l;
    }
    
    public long getLongVersionCode() {
      return PackageInfo.composeLongVersionCode(this.mVersionCodeMajor, this.mVersionCode);
    }
    
    public boolean hasChildPackage(String param1String) {
      byte b1;
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        b1 = arrayList.size();
      } else {
        b1 = 0;
      } 
      for (byte b2 = 0; b2 < b1; b2++) {
        if (((Package)this.childPackages.get(b2)).packageName.equals(param1String))
          return true; 
      } 
      return false;
    }
    
    public boolean hasComponentClassName(String param1String) {
      int i;
      for (i = this.activities.size() - 1; i >= 0; i--) {
        if (param1String.equals(((PackageParser.Activity)this.activities.get(i)).className))
          return true; 
      } 
      for (i = this.receivers.size() - 1; i >= 0; i--) {
        if (param1String.equals(((PackageParser.Activity)this.receivers.get(i)).className))
          return true; 
      } 
      for (i = this.providers.size() - 1; i >= 0; i--) {
        if (param1String.equals(((PackageParser.Provider)this.providers.get(i)).className))
          return true; 
      } 
      for (i = this.services.size() - 1; i >= 0; i--) {
        if (param1String.equals(((PackageParser.Service)this.services.get(i)).className))
          return true; 
      } 
      for (i = this.instrumentation.size() - 1; i >= 0; i--) {
        if (param1String.equals(((PackageParser.Instrumentation)this.instrumentation.get(i)).className))
          return true; 
      } 
      return false;
    }
    
    public boolean isExternal() {
      return this.applicationInfo.isExternal();
    }
    
    public boolean isForwardLocked() {
      return false;
    }
    
    public boolean isLibrary() {
      return (this.staticSharedLibName != null || !ArrayUtils.isEmpty(this.libraryNames));
    }
    
    public boolean isMatch(int param1Int) {
      return ((0x100000 & param1Int) != 0) ? isSystem() : true;
    }
    
    public boolean isOdm() {
      return this.applicationInfo.isOdm();
    }
    
    public boolean isOem() {
      return this.applicationInfo.isOem();
    }
    
    public boolean isPrivileged() {
      return this.applicationInfo.isPrivilegedApp();
    }
    
    public boolean isProduct() {
      return this.applicationInfo.isProduct();
    }
    
    public boolean isSystem() {
      return this.applicationInfo.isSystemApp();
    }
    
    public boolean isSystemExt() {
      return this.applicationInfo.isSystemExt();
    }
    
    public boolean isUpdatedSystemApp() {
      return this.applicationInfo.isUpdatedSystemApp();
    }
    
    public boolean isVendor() {
      return this.applicationInfo.isVendor();
    }
    
    public void setApplicationInfoBaseCodePath(String param1String) {
      this.applicationInfo.setBaseCodePath(param1String);
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((Package)this.childPackages.get(b)).applicationInfo.setBaseCodePath(param1String); 
      } 
    }
    
    @Deprecated
    public void setApplicationInfoBaseResourcePath(String param1String) {
      this.applicationInfo.setBaseResourcePath(param1String);
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((Package)this.childPackages.get(b)).applicationInfo.setBaseResourcePath(param1String); 
      } 
    }
    
    public void setApplicationInfoCodePath(String param1String) {
      this.applicationInfo.setCodePath(param1String);
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((Package)this.childPackages.get(b)).applicationInfo.setCodePath(param1String); 
      } 
    }
    
    public void setApplicationInfoFlags(int param1Int1, int param1Int2) {
      ApplicationInfo applicationInfo = this.applicationInfo;
      applicationInfo.flags = applicationInfo.flags & param1Int1 | param1Int1 & param1Int2;
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((Package)this.childPackages.get(b)).applicationInfo.flags = this.applicationInfo.flags & param1Int1 | param1Int1 & param1Int2; 
      } 
    }
    
    @Deprecated
    public void setApplicationInfoResourcePath(String param1String) {
      this.applicationInfo.setResourcePath(param1String);
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((Package)this.childPackages.get(b)).applicationInfo.setResourcePath(param1String); 
      } 
    }
    
    public void setApplicationInfoSplitCodePaths(String[] param1ArrayOfString) {
      this.applicationInfo.setSplitCodePaths(param1ArrayOfString);
    }
    
    @Deprecated
    public void setApplicationInfoSplitResourcePaths(String[] param1ArrayOfString) {
      this.applicationInfo.setSplitResourcePaths(param1ArrayOfString);
    }
    
    public void setApplicationVolumeUuid(String param1String) {
      UUID uUID = StorageManager.convert(param1String);
      this.applicationInfo.volumeUuid = param1String;
      this.applicationInfo.storageUuid = uUID;
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++) {
          ((Package)this.childPackages.get(b)).applicationInfo.volumeUuid = param1String;
          ((Package)this.childPackages.get(b)).applicationInfo.storageUuid = uUID;
        } 
      } 
    }
    
    public void setBaseCodePath(String param1String) {
      this.baseCodePath = param1String;
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((Package)this.childPackages.get(b)).baseCodePath = param1String; 
      } 
    }
    
    public void setCodePath(String param1String) {
      this.codePath = param1String;
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((Package)this.childPackages.get(b)).codePath = param1String; 
      } 
    }
    
    public void setPackageName(String param1String) {
      this.packageName = param1String;
      this.applicationInfo.packageName = param1String;
      int i;
      for (i = this.permissions.size() - 1; i >= 0; i--)
        ((PackageParser.Permission)this.permissions.get(i)).setPackageName(param1String); 
      for (i = this.permissionGroups.size() - 1; i >= 0; i--)
        ((PackageParser.PermissionGroup)this.permissionGroups.get(i)).setPackageName(param1String); 
      for (i = this.activities.size() - 1; i >= 0; i--)
        ((PackageParser.Activity)this.activities.get(i)).setPackageName(param1String); 
      for (i = this.receivers.size() - 1; i >= 0; i--)
        ((PackageParser.Activity)this.receivers.get(i)).setPackageName(param1String); 
      for (i = this.providers.size() - 1; i >= 0; i--)
        ((PackageParser.Provider)this.providers.get(i)).setPackageName(param1String); 
      for (i = this.services.size() - 1; i >= 0; i--)
        ((PackageParser.Service)this.services.get(i)).setPackageName(param1String); 
      for (i = this.instrumentation.size() - 1; i >= 0; i--)
        ((PackageParser.Instrumentation)this.instrumentation.get(i)).setPackageName(param1String); 
    }
    
    public void setSigningDetails(PackageParser.SigningDetails param1SigningDetails) {
      this.mSigningDetails = param1SigningDetails;
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((Package)this.childPackages.get(b)).mSigningDetails = param1SigningDetails; 
      } 
    }
    
    public void setSplitCodePaths(String[] param1ArrayOfString) {
      this.splitCodePaths = param1ArrayOfString;
    }
    
    public void setUse32bitAbi(boolean param1Boolean) {
      this.use32bitAbi = param1Boolean;
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((Package)this.childPackages.get(b)).use32bitAbi = param1Boolean; 
      } 
    }
    
    public void setVolumeUuid(String param1String) {
      this.volumeUuid = param1String;
      ArrayList<Package> arrayList = this.childPackages;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((Package)this.childPackages.get(b)).volumeUuid = param1String; 
      } 
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
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeString(this.packageName);
      param1Parcel.writeString(this.manifestPackageName);
      param1Parcel.writeStringArray(this.splitNames);
      param1Parcel.writeString(this.volumeUuid);
      param1Parcel.writeString(this.codePath);
      param1Parcel.writeString(this.baseCodePath);
      param1Parcel.writeStringArray(this.splitCodePaths);
      param1Parcel.writeInt(this.baseRevisionCode);
      param1Parcel.writeIntArray(this.splitRevisionCodes);
      param1Parcel.writeIntArray(this.splitFlags);
      param1Parcel.writeIntArray(this.splitPrivateFlags);
      param1Parcel.writeInt(this.baseHardwareAccelerated);
      param1Parcel.writeParcelable(this.applicationInfo, param1Int);
      param1Parcel.writeParcelableList(this.permissions, param1Int);
      param1Parcel.writeParcelableList(this.permissionGroups, param1Int);
      param1Parcel.writeParcelableList(this.activities, param1Int);
      param1Parcel.writeParcelableList(this.receivers, param1Int);
      param1Parcel.writeParcelableList(this.providers, param1Int);
      param1Parcel.writeParcelableList(this.services, param1Int);
      param1Parcel.writeParcelableList(this.instrumentation, param1Int);
      param1Parcel.writeStringList(this.requestedPermissions);
      param1Parcel.writeStringList(this.implicitPermissions);
      param1Parcel.writeStringList(this.protectedBroadcasts);
      param1Parcel.writeParcelable(this.parentPackage, param1Int);
      param1Parcel.writeParcelableList(this.childPackages, param1Int);
      param1Parcel.writeString(this.staticSharedLibName);
      param1Parcel.writeLong(this.staticSharedLibVersion);
      param1Parcel.writeStringList(this.libraryNames);
      param1Parcel.writeStringList(this.usesLibraries);
      param1Parcel.writeStringList(this.usesOptionalLibraries);
      param1Parcel.writeStringArray(this.usesLibraryFiles);
      param1Parcel.writeTypedList(this.usesLibraryInfos);
      if (ArrayUtils.isEmpty(this.usesStaticLibraries)) {
        param1Parcel.writeInt(-1);
      } else {
        param1Parcel.writeInt(this.usesStaticLibraries.size());
        param1Parcel.writeStringList(this.usesStaticLibraries);
        param1Parcel.writeLongArray(this.usesStaticLibrariesVersions);
        String[][] arrayOfString = this.usesStaticLibrariesCertDigests;
        int i = arrayOfString.length;
        for (byte b = 0; b < i; b++)
          param1Parcel.writeStringArray(arrayOfString[b]); 
      } 
      param1Parcel.writeParcelableList(this.preferredActivityFilters, param1Int);
      param1Parcel.writeStringList(this.mOriginalPackages);
      param1Parcel.writeString(this.mRealPackage);
      param1Parcel.writeStringList(this.mAdoptPermissions);
      param1Parcel.writeBundle(this.mAppMetaData);
      param1Parcel.writeInt(this.mVersionCode);
      param1Parcel.writeInt(this.mVersionCodeMajor);
      param1Parcel.writeString(this.mVersionName);
      param1Parcel.writeString(this.mSharedUserId);
      param1Parcel.writeInt(this.mSharedUserLabel);
      param1Parcel.writeParcelable(this.mSigningDetails, param1Int);
      param1Parcel.writeInt(this.mPreferredOrder);
      param1Parcel.writeParcelableList(this.configPreferences, param1Int);
      param1Parcel.writeParcelableList(this.reqFeatures, param1Int);
      param1Parcel.writeParcelableList(this.featureGroups, param1Int);
      param1Parcel.writeInt(this.installLocation);
      param1Parcel.writeInt(this.coreApp);
      param1Parcel.writeInt(this.mRequiredForAllUsers);
      param1Parcel.writeString(this.mRestrictedAccountType);
      param1Parcel.writeString(this.mRequiredAccountType);
      param1Parcel.writeString(this.mOverlayTarget);
      param1Parcel.writeString(this.mOverlayTargetName);
      param1Parcel.writeString(this.mOverlayCategory);
      param1Parcel.writeInt(this.mOverlayPriority);
      param1Parcel.writeInt(this.mOverlayIsStatic);
      param1Parcel.writeInt(this.mCompileSdkVersion);
      param1Parcel.writeString(this.mCompileSdkVersionCodename);
      param1Parcel.writeArraySet(this.mUpgradeKeySets);
      writeKeySetMapping(param1Parcel, this.mKeySetMapping);
      param1Parcel.writeString(this.cpuAbiOverride);
      param1Parcel.writeInt(this.use32bitAbi);
      param1Parcel.writeByteArray(this.restrictUpdateHash);
      param1Parcel.writeInt(this.visibleToInstantApps);
    }
  }
  
  class null implements Parcelable.Creator<Package> {
    public PackageParser.Package createFromParcel(Parcel param1Parcel) {
      return new PackageParser.Package(param1Parcel);
    }
    
    public PackageParser.Package[] newArray(int param1Int) {
      return new PackageParser.Package[param1Int];
    }
  }
  
  public static class PackageLite {
    public final String baseCodePath;
    
    public final int baseRevisionCode;
    
    public final String codePath;
    
    public final String[] configForSplit;
    
    public final boolean coreApp;
    
    public final boolean debuggable;
    
    public final boolean extractNativeLibs;
    
    public final int installLocation;
    
    public final boolean[] isFeatureSplits;
    
    public final boolean isolatedSplits;
    
    public final boolean multiArch;
    
    public final String packageName;
    
    public final String[] splitCodePaths;
    
    public final String[] splitNames;
    
    public final int[] splitRevisionCodes;
    
    public final boolean use32bitAbi;
    
    public final String[] usesSplitNames;
    
    public final VerifierInfo[] verifiers;
    
    public final int versionCode;
    
    public final int versionCodeMajor;
    
    public PackageLite(String param1String, PackageParser.ApkLite param1ApkLite, String[] param1ArrayOfString1, boolean[] param1ArrayOfboolean, String[] param1ArrayOfString2, String[] param1ArrayOfString3, String[] param1ArrayOfString4, int[] param1ArrayOfint) {
      this.packageName = param1ApkLite.packageName;
      this.versionCode = param1ApkLite.versionCode;
      this.versionCodeMajor = param1ApkLite.versionCodeMajor;
      this.installLocation = param1ApkLite.installLocation;
      this.verifiers = param1ApkLite.verifiers;
      this.splitNames = param1ArrayOfString1;
      this.isFeatureSplits = param1ArrayOfboolean;
      this.usesSplitNames = param1ArrayOfString2;
      this.configForSplit = param1ArrayOfString3;
      this.codePath = param1String;
      this.baseCodePath = param1ApkLite.codePath;
      this.splitCodePaths = param1ArrayOfString4;
      this.baseRevisionCode = param1ApkLite.revisionCode;
      this.splitRevisionCodes = param1ArrayOfint;
      this.coreApp = param1ApkLite.coreApp;
      this.debuggable = param1ApkLite.debuggable;
      this.multiArch = param1ApkLite.multiArch;
      this.use32bitAbi = param1ApkLite.use32bitAbi;
      this.extractNativeLibs = param1ApkLite.extractNativeLibs;
      this.isolatedSplits = param1ApkLite.isolatedSplits;
    }
    
    public List<String> getAllCodePaths() {
      ArrayList<String> arrayList = new ArrayList();
      arrayList.add(this.baseCodePath);
      if (!ArrayUtils.isEmpty((Object[])this.splitCodePaths))
        Collections.addAll(arrayList, this.splitCodePaths); 
      return arrayList;
    }
  }
  
  public static class PackageParserException extends Exception {
    public final int error;
    
    public PackageParserException(int param1Int, String param1String) {
      super(param1String);
      this.error = param1Int;
    }
    
    public PackageParserException(int param1Int, String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
      this.error = param1Int;
    }
  }
  
  public static class ParseComponentArgs extends ParsePackageItemArgs {
    final int descriptionRes;
    
    final int enabledRes;
    
    int flags;
    
    final int processRes;
    
    final String[] sepProcesses;
    
    public ParseComponentArgs(PackageParser.Package param1Package, String[] param1ArrayOfString1, int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, String[] param1ArrayOfString2, int param1Int7, int param1Int8, int param1Int9) {
      super(param1Package, param1ArrayOfString1, param1Int1, param1Int2, param1Int3, param1Int4, param1Int5, param1Int6);
      this.sepProcesses = param1ArrayOfString2;
      this.processRes = param1Int7;
      this.descriptionRes = param1Int8;
      this.enabledRes = param1Int9;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ParseFlags {}
  
  static class ParsePackageItemArgs {
    final int bannerRes;
    
    final int iconRes;
    
    final int labelRes;
    
    final int logoRes;
    
    final int nameRes;
    
    final String[] outError;
    
    final PackageParser.Package owner;
    
    final int roundIconRes;
    
    TypedArray sa;
    
    String tag;
    
    ParsePackageItemArgs(PackageParser.Package param1Package, String[] param1ArrayOfString, int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6) {
      this.owner = param1Package;
      this.outError = param1ArrayOfString;
      this.nameRes = param1Int1;
      this.labelRes = param1Int2;
      this.iconRes = param1Int3;
      this.logoRes = param1Int5;
      this.bannerRes = param1Int6;
      this.roundIconRes = param1Int4;
    }
  }
  
  public static final class Permission extends Component<IntentInfo> implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Permission>() {
        public PackageParser.Permission createFromParcel(Parcel param2Parcel) {
          return new PackageParser.Permission(param2Parcel);
        }
        
        public PackageParser.Permission[] newArray(int param2Int) {
          return new PackageParser.Permission[param2Int];
        }
      };
    
    public PackageParser.PermissionGroup group;
    
    public final PermissionInfo info;
    
    public boolean tree;
    
    public Permission(PackageParser.Package param1Package, PermissionInfo param1PermissionInfo) {
      super(param1Package);
      this.info = param1PermissionInfo;
    }
    
    public Permission(PackageParser.Package param1Package, String param1String) {
      super(param1Package);
      this.info = new PermissionInfo(param1String);
    }
    
    private Permission(Parcel param1Parcel) {
      super(param1Parcel);
      ClassLoader classLoader = Object.class.getClassLoader();
      PermissionInfo permissionInfo = (PermissionInfo)param1Parcel.readParcelable(classLoader);
      this.info = permissionInfo;
      if (permissionInfo.group != null) {
        permissionInfo = this.info;
        permissionInfo.group = permissionInfo.group.intern();
      } 
      int i = param1Parcel.readInt();
      boolean bool = true;
      if (i != 1)
        bool = false; 
      this.tree = bool;
      this.group = (PackageParser.PermissionGroup)param1Parcel.readParcelable(classLoader);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean isAppOp() {
      return this.info.isAppOp();
    }
    
    public void setPackageName(String param1String) {
      super.setPackageName(param1String);
      this.info.packageName = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Permission{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" ");
      stringBuilder.append(this.info.name);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeParcelable(this.info, param1Int);
      param1Parcel.writeInt(this.tree);
      param1Parcel.writeParcelable(this.group, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<Permission> {
    public PackageParser.Permission createFromParcel(Parcel param1Parcel) {
      return new PackageParser.Permission(param1Parcel);
    }
    
    public PackageParser.Permission[] newArray(int param1Int) {
      return new PackageParser.Permission[param1Int];
    }
  }
  
  public static final class PermissionGroup extends Component<IntentInfo> implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<PermissionGroup>() {
        public PackageParser.PermissionGroup createFromParcel(Parcel param2Parcel) {
          return new PackageParser.PermissionGroup(param2Parcel);
        }
        
        public PackageParser.PermissionGroup[] newArray(int param2Int) {
          return new PackageParser.PermissionGroup[param2Int];
        }
      };
    
    public final PermissionGroupInfo info;
    
    public PermissionGroup(PackageParser.Package param1Package, int param1Int1, int param1Int2, int param1Int3) {
      super(param1Package);
      this.info = new PermissionGroupInfo(param1Int1, param1Int2, param1Int3);
    }
    
    public PermissionGroup(PackageParser.Package param1Package, PermissionGroupInfo param1PermissionGroupInfo) {
      super(param1Package);
      this.info = param1PermissionGroupInfo;
    }
    
    private PermissionGroup(Parcel param1Parcel) {
      super(param1Parcel);
      this.info = (PermissionGroupInfo)param1Parcel.readParcelable(Object.class.getClassLoader());
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void setPackageName(String param1String) {
      super.setPackageName(param1String);
      this.info.packageName = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("PermissionGroup{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" ");
      stringBuilder.append(this.info.name);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeParcelable(this.info, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<PermissionGroup> {
    public PackageParser.PermissionGroup createFromParcel(Parcel param1Parcel) {
      return new PackageParser.PermissionGroup(param1Parcel);
    }
    
    public PackageParser.PermissionGroup[] newArray(int param1Int) {
      return new PackageParser.PermissionGroup[param1Int];
    }
  }
  
  public static final class Provider extends Component<ProviderIntentInfo> implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Provider>() {
        public PackageParser.Provider createFromParcel(Parcel param2Parcel) {
          return new PackageParser.Provider(param2Parcel);
        }
        
        public PackageParser.Provider[] newArray(int param2Int) {
          return new PackageParser.Provider[param2Int];
        }
      };
    
    public final ProviderInfo info;
    
    public boolean syncable;
    
    public Provider(PackageParser.ParseComponentArgs param1ParseComponentArgs, ProviderInfo param1ProviderInfo) {
      super(param1ParseComponentArgs, param1ProviderInfo);
      this.info = param1ProviderInfo;
      param1ProviderInfo.applicationInfo = param1ParseComponentArgs.owner.applicationInfo;
      this.syncable = false;
    }
    
    public Provider(Provider param1Provider) {
      super(param1Provider);
      this.info = param1Provider.info;
      this.syncable = param1Provider.syncable;
    }
    
    private Provider(Parcel param1Parcel) {
      super(param1Parcel);
      this.info = (ProviderInfo)param1Parcel.readParcelable(Object.class.getClassLoader());
      int i = param1Parcel.readInt();
      boolean bool = true;
      if (i != 1)
        bool = false; 
      this.syncable = bool;
      Iterator<PackageParser.ProviderIntentInfo> iterator = this.intents.iterator();
      while (iterator.hasNext())
        ((PackageParser.ProviderIntentInfo)iterator.next()).provider = this; 
      if (this.info.readPermission != null) {
        ProviderInfo providerInfo = this.info;
        providerInfo.readPermission = providerInfo.readPermission.intern();
      } 
      if (this.info.writePermission != null) {
        ProviderInfo providerInfo = this.info;
        providerInfo.writePermission = providerInfo.writePermission.intern();
      } 
      if (this.info.authority != null) {
        ProviderInfo providerInfo = this.info;
        providerInfo.authority = providerInfo.authority.intern();
      } 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void setPackageName(String param1String) {
      super.setPackageName(param1String);
      this.info.packageName = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(128);
      stringBuilder.append("Provider{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(' ');
      appendComponentShortName(stringBuilder);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeParcelable(this.info, param1Int | 0x2);
      param1Parcel.writeInt(this.syncable);
    }
  }
  
  class null implements Parcelable.Creator<Provider> {
    public PackageParser.Provider createFromParcel(Parcel param1Parcel) {
      return new PackageParser.Provider(param1Parcel);
    }
    
    public PackageParser.Provider[] newArray(int param1Int) {
      return new PackageParser.Provider[param1Int];
    }
  }
  
  public static final class ProviderIntentInfo extends IntentInfo {
    public PackageParser.Provider provider;
    
    public ProviderIntentInfo(PackageParser.Provider param1Provider) {
      this.provider = param1Provider;
    }
    
    public ProviderIntentInfo(Parcel param1Parcel) {
      super(param1Parcel);
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(128);
      stringBuilder.append("ProviderIntentInfo{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(' ');
      this.provider.appendComponentShortName(stringBuilder);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
  
  public static final class Service extends Component<ServiceIntentInfo> implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Service>() {
        public PackageParser.Service createFromParcel(Parcel param2Parcel) {
          return new PackageParser.Service(param2Parcel);
        }
        
        public PackageParser.Service[] newArray(int param2Int) {
          return new PackageParser.Service[param2Int];
        }
      };
    
    public final ServiceInfo info;
    
    public Service(PackageParser.ParseComponentArgs param1ParseComponentArgs, ServiceInfo param1ServiceInfo) {
      super(param1ParseComponentArgs, param1ServiceInfo);
      this.info = param1ServiceInfo;
      param1ServiceInfo.applicationInfo = param1ParseComponentArgs.owner.applicationInfo;
    }
    
    private Service(Parcel param1Parcel) {
      super(param1Parcel);
      this.info = (ServiceInfo)param1Parcel.readParcelable(Object.class.getClassLoader());
      for (PackageParser.ServiceIntentInfo serviceIntentInfo : this.intents) {
        serviceIntentInfo.service = this;
        this.order = Math.max(serviceIntentInfo.getOrder(), this.order);
      } 
      if (this.info.permission != null) {
        ServiceInfo serviceInfo = this.info;
        serviceInfo.permission = serviceInfo.permission.intern();
      } 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void setPackageName(String param1String) {
      super.setPackageName(param1String);
      this.info.packageName = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(128);
      stringBuilder.append("Service{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(' ');
      appendComponentShortName(stringBuilder);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeParcelable(this.info, param1Int | 0x2);
    }
  }
  
  class null implements Parcelable.Creator<Service> {
    public PackageParser.Service createFromParcel(Parcel param1Parcel) {
      return new PackageParser.Service(param1Parcel);
    }
    
    public PackageParser.Service[] newArray(int param1Int) {
      return new PackageParser.Service[param1Int];
    }
  }
  
  public static final class ServiceIntentInfo extends IntentInfo {
    public PackageParser.Service service;
    
    public ServiceIntentInfo(PackageParser.Service param1Service) {
      this.service = param1Service;
    }
    
    public ServiceIntentInfo(Parcel param1Parcel) {
      super(param1Parcel);
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(128);
      stringBuilder.append("ServiceIntentInfo{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(' ');
      this.service.appendComponentShortName(stringBuilder);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
  
  public static final class SigningDetails implements Parcelable {
    public static final Parcelable.Creator<SigningDetails> CREATOR = new Parcelable.Creator<SigningDetails>() {
        public PackageParser.SigningDetails createFromParcel(Parcel param2Parcel) {
          return param2Parcel.readBoolean() ? PackageParser.SigningDetails.UNKNOWN : new PackageParser.SigningDetails(param2Parcel);
        }
        
        public PackageParser.SigningDetails[] newArray(int param2Int) {
          return new PackageParser.SigningDetails[param2Int];
        }
      };
    
    private static final int PAST_CERT_EXISTS = 0;
    
    public static final SigningDetails UNKNOWN = new SigningDetails(null, 0, null, null);
    
    public final Signature[] pastSigningCertificates;
    
    public final ArraySet<PublicKey> publicKeys;
    
    public final int signatureSchemeVersion;
    
    public final Signature[] signatures;
    
    static {
    
    }
    
    public SigningDetails(SigningDetails param1SigningDetails) {
      if (param1SigningDetails != null) {
        Signature[] arrayOfSignature2 = param1SigningDetails.signatures;
        if (arrayOfSignature2 != null) {
          this.signatures = (Signature[])arrayOfSignature2.clone();
        } else {
          this.signatures = null;
        } 
        this.signatureSchemeVersion = param1SigningDetails.signatureSchemeVersion;
        this.publicKeys = new ArraySet(param1SigningDetails.publicKeys);
        Signature[] arrayOfSignature1 = param1SigningDetails.pastSigningCertificates;
        if (arrayOfSignature1 != null) {
          this.pastSigningCertificates = (Signature[])arrayOfSignature1.clone();
        } else {
          this.pastSigningCertificates = null;
        } 
      } else {
        this.signatures = null;
        this.signatureSchemeVersion = 0;
        this.publicKeys = null;
        this.pastSigningCertificates = null;
      } 
    }
    
    protected SigningDetails(Parcel param1Parcel) {
      ClassLoader classLoader = Object.class.getClassLoader();
      this.signatures = (Signature[])param1Parcel.createTypedArray(Signature.CREATOR);
      this.signatureSchemeVersion = param1Parcel.readInt();
      this.publicKeys = param1Parcel.readArraySet(classLoader);
      this.pastSigningCertificates = (Signature[])param1Parcel.createTypedArray(Signature.CREATOR);
    }
    
    public SigningDetails(Signature[] param1ArrayOfSignature, int param1Int) throws CertificateException {
      this(param1ArrayOfSignature, param1Int, null);
    }
    
    public SigningDetails(Signature[] param1ArrayOfSignature1, int param1Int, ArraySet<PublicKey> param1ArraySet, Signature[] param1ArrayOfSignature2) {
      this.signatures = param1ArrayOfSignature1;
      this.signatureSchemeVersion = param1Int;
      this.publicKeys = param1ArraySet;
      this.pastSigningCertificates = param1ArrayOfSignature2;
    }
    
    public SigningDetails(Signature[] param1ArrayOfSignature1, int param1Int, Signature[] param1ArrayOfSignature2) throws CertificateException {
      this(param1ArrayOfSignature1, param1Int, PackageParser.toSigningKeys(param1ArrayOfSignature1), param1ArrayOfSignature2);
    }
    
    private SigningDetails getDescendantOrSelf(SigningDetails param1SigningDetails) {
      SigningDetails signingDetails;
      int m;
      if (hasAncestorOrSelf(param1SigningDetails)) {
        signingDetails = this;
      } else if (param1SigningDetails.hasAncestor(this)) {
        signingDetails = param1SigningDetails;
        param1SigningDetails = this;
      } else {
        return null;
      } 
      int i = signingDetails.pastSigningCertificates.length - 1;
      int j = param1SigningDetails.pastSigningCertificates.length - 1;
      while (i >= 0 && !signingDetails.pastSigningCertificates[i].equals(param1SigningDetails.pastSigningCertificates[j]))
        i--; 
      int k = i;
      if (i < 0)
        return null; 
      while (true) {
        i = k - 1;
        m = j - 1;
        if (i >= 0 && m >= 0) {
          k = i;
          j = m;
          if (!signingDetails.pastSigningCertificates[i].equals(param1SigningDetails.pastSigningCertificates[m]))
            break; 
          continue;
        } 
        break;
      } 
      return (i >= 0 && m >= 0) ? null : signingDetails;
    }
    
    private boolean hasCertificateInternal(Signature param1Signature, int param1Int) {
      SigningDetails signingDetails = UNKNOWN;
      boolean bool1 = false;
      if (this == signingDetails)
        return false; 
      if (hasPastSigningCertificates()) {
        byte b = 0;
        while (true) {
          Signature[] arrayOfSignature1 = this.pastSigningCertificates;
          if (b < arrayOfSignature1.length - 1) {
            if (arrayOfSignature1[b].equals(param1Signature) && (param1Int == 0 || (this.pastSigningCertificates[b].getFlags() & param1Int) == param1Int))
              return true; 
            b++;
            continue;
          } 
          break;
        } 
      } 
      Signature[] arrayOfSignature = this.signatures;
      boolean bool2 = bool1;
      if (arrayOfSignature.length == 1) {
        bool2 = bool1;
        if (arrayOfSignature[0].equals(param1Signature))
          bool2 = true; 
      } 
      return bool2;
    }
    
    private boolean hasSha256CertificateInternal(byte[] param1ArrayOfbyte, int param1Int) {
      if (this == UNKNOWN)
        return false; 
      if (hasPastSigningCertificates()) {
        byte b = 0;
        while (true) {
          Signature[] arrayOfSignature1 = this.pastSigningCertificates;
          if (b < arrayOfSignature1.length - 1) {
            if (Arrays.equals(param1ArrayOfbyte, PackageUtils.computeSha256DigestBytes(arrayOfSignature1[b].toByteArray())) && (param1Int == 0 || (this.pastSigningCertificates[b].getFlags() & param1Int) == param1Int))
              return true; 
            b++;
            continue;
          } 
          break;
        } 
      } 
      Signature[] arrayOfSignature = this.signatures;
      return (arrayOfSignature.length == 1) ? Arrays.equals(param1ArrayOfbyte, PackageUtils.computeSha256DigestBytes(arrayOfSignature[0].toByteArray())) : false;
    }
    
    private SigningDetails mergeLineageWithAncestorOrSelf(SigningDetails param1SigningDetails) {
      int n;
      int i = this.pastSigningCertificates.length - 1;
      int j = param1SigningDetails.pastSigningCertificates.length - 1;
      if (i < 0 || j < 0)
        return this; 
      ArrayList<Signature> arrayList = new ArrayList();
      int k = 0;
      while (i >= 0 && !this.pastSigningCertificates[i].equals(param1SigningDetails.pastSigningCertificates[j])) {
        arrayList.add(new Signature(this.pastSigningCertificates[i]));
        i--;
      } 
      int m = i;
      if (i < 0)
        return this; 
      while (true) {
        Signature[] arrayOfSignature1 = this.pastSigningCertificates;
        n = m - 1;
        Signature signature1 = arrayOfSignature1[m];
        Signature[] arrayOfSignature2 = param1SigningDetails.pastSigningCertificates;
        m = j - 1;
        Signature signature3 = arrayOfSignature2[j];
        Signature signature2 = new Signature(signature1);
        j = signature1.getFlags() & signature3.getFlags();
        i = k;
        if (signature1.getFlags() != j) {
          i = 1;
          signature2.setFlags(j);
        } 
        arrayList.add(signature2);
        if (n < 0 || m < 0 || !this.pastSigningCertificates[n].equals(param1SigningDetails.pastSigningCertificates[m]))
          break; 
        j = m;
        m = n;
        k = i;
      } 
      j = m;
      if (n >= 0) {
        j = m;
        if (m >= 0)
          return this; 
      } 
      while (true) {
        m = n;
        if (j >= 0) {
          arrayList.add(new Signature(param1SigningDetails.pastSigningCertificates[j]));
          j--;
          continue;
        } 
        break;
      } 
      while (m >= 0) {
        arrayList.add(new Signature(this.pastSigningCertificates[m]));
        m--;
      } 
      if (arrayList.size() == this.pastSigningCertificates.length && i == 0)
        return this; 
      Collections.reverse(arrayList);
      try {
        Signature signature = new Signature();
        this(this.signatures[0]);
        i = this.signatureSchemeVersion;
        Signature[] arrayOfSignature = arrayList.<Signature>toArray(new Signature[0]);
        return new SigningDetails(new Signature[] { signature }, i, arrayOfSignature);
      } catch (CertificateException certificateException) {
        Slog.e("PackageParser", "Caught an exception creating the merged lineage: ", certificateException);
        return this;
      } 
    }
    
    public boolean checkCapability(SigningDetails param1SigningDetails, int param1Int) {
      SigningDetails signingDetails = UNKNOWN;
      if (this == signingDetails || param1SigningDetails == signingDetails)
        return false; 
      Signature[] arrayOfSignature = param1SigningDetails.signatures;
      return (arrayOfSignature.length > 1) ? signaturesMatchExactly(param1SigningDetails) : hasCertificate(arrayOfSignature[0], param1Int);
    }
    
    public boolean checkCapability(String param1String, int param1Int) {
      byte[] arrayOfByte;
      if (this == UNKNOWN)
        return false; 
      if (param1String == null) {
        arrayOfByte = null;
      } else {
        arrayOfByte = HexEncoding.decode(param1String, false);
      } 
      if (hasSha256Certificate(arrayOfByte, param1Int))
        return true; 
      String[] arrayOfString = PackageUtils.computeSignaturesSha256Digests(this.signatures);
      return PackageUtils.computeSignaturesSha256Digest(arrayOfString).equals(param1String);
    }
    
    public boolean checkCapabilityRecover(SigningDetails param1SigningDetails, int param1Int) throws CertificateException {
      SigningDetails signingDetails = UNKNOWN;
      if (param1SigningDetails == signingDetails || this == signingDetails)
        return false; 
      if (hasPastSigningCertificates() && param1SigningDetails.signatures.length == 1) {
        byte b = 0;
        while (true) {
          Signature[] arrayOfSignature = this.pastSigningCertificates;
          if (b < arrayOfSignature.length) {
            if (Signature.areEffectiveMatch(param1SigningDetails.signatures[0], arrayOfSignature[b]) && this.pastSigningCertificates[b].getFlags() == param1Int)
              return true; 
            b++;
            continue;
          } 
          return false;
        } 
      } 
      return Signature.areEffectiveMatch(param1SigningDetails.signatures, this.signatures);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof SigningDetails))
        return false; 
      param1Object = param1Object;
      if (this.signatureSchemeVersion != ((SigningDetails)param1Object).signatureSchemeVersion)
        return false; 
      if (!Signature.areExactMatch(this.signatures, ((SigningDetails)param1Object).signatures))
        return false; 
      ArraySet<PublicKey> arraySet = this.publicKeys;
      if (arraySet != null) {
        if (!arraySet.equals(((SigningDetails)param1Object).publicKeys))
          return false; 
      } else if (((SigningDetails)param1Object).publicKeys != null) {
        return false;
      } 
      if (!Arrays.equals((Object[])this.pastSigningCertificates, (Object[])((SigningDetails)param1Object).pastSigningCertificates))
        return false; 
      byte b = 0;
      while (true) {
        Signature[] arrayOfSignature = this.pastSigningCertificates;
        if (b < arrayOfSignature.length) {
          if (arrayOfSignature[b].getFlags() != ((SigningDetails)param1Object).pastSigningCertificates[b].getFlags())
            return false; 
          b++;
          continue;
        } 
        return true;
      } 
    }
    
    public boolean hasAncestor(SigningDetails param1SigningDetails) {
      SigningDetails signingDetails = UNKNOWN;
      if (this == signingDetails || param1SigningDetails == signingDetails)
        return false; 
      if (hasPastSigningCertificates() && param1SigningDetails.signatures.length == 1) {
        byte b = 0;
        while (true) {
          Signature[] arrayOfSignature = this.pastSigningCertificates;
          if (b < arrayOfSignature.length - 1) {
            if (arrayOfSignature[b].equals(param1SigningDetails.signatures[0]))
              return true; 
            b++;
            continue;
          } 
          break;
        } 
      } 
      return false;
    }
    
    public boolean hasAncestorOrSelf(SigningDetails param1SigningDetails) {
      SigningDetails signingDetails = UNKNOWN;
      if (this == signingDetails || param1SigningDetails == signingDetails)
        return false; 
      Signature[] arrayOfSignature = param1SigningDetails.signatures;
      return (arrayOfSignature.length > 1) ? signaturesMatchExactly(param1SigningDetails) : hasCertificate(arrayOfSignature[0]);
    }
    
    public boolean hasCertificate(Signature param1Signature) {
      return hasCertificateInternal(param1Signature, 0);
    }
    
    public boolean hasCertificate(Signature param1Signature, int param1Int) {
      return hasCertificateInternal(param1Signature, param1Int);
    }
    
    public boolean hasCertificate(byte[] param1ArrayOfbyte) {
      return hasCertificate(new Signature(param1ArrayOfbyte));
    }
    
    public boolean hasCommonAncestor(SigningDetails param1SigningDetails) {
      boolean bool;
      if (!hasPastSigningCertificates())
        return param1SigningDetails.hasAncestorOrSelf(this); 
      if (!param1SigningDetails.hasPastSigningCertificates())
        return hasAncestorOrSelf(param1SigningDetails); 
      if (getDescendantOrSelf(param1SigningDetails) != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasPastSigningCertificates() {
      boolean bool;
      Signature[] arrayOfSignature = this.pastSigningCertificates;
      if (arrayOfSignature != null && arrayOfSignature.length > 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasSha256Certificate(byte[] param1ArrayOfbyte) {
      return hasSha256CertificateInternal(param1ArrayOfbyte, 0);
    }
    
    public boolean hasSha256Certificate(byte[] param1ArrayOfbyte, int param1Int) {
      return hasSha256CertificateInternal(param1ArrayOfbyte, param1Int);
    }
    
    public boolean hasSignatures() {
      boolean bool;
      Signature[] arrayOfSignature = this.signatures;
      if (arrayOfSignature != null && arrayOfSignature.length > 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      byte b;
      int i = Arrays.hashCode((Object[])this.signatures);
      int j = this.signatureSchemeVersion;
      ArraySet<PublicKey> arraySet = this.publicKeys;
      if (arraySet != null) {
        b = arraySet.hashCode();
      } else {
        b = 0;
      } 
      return ((i * 31 + j) * 31 + b) * 31 + Arrays.hashCode((Object[])this.pastSigningCertificates);
    }
    
    public SigningDetails mergeLineageWith(SigningDetails param1SigningDetails) {
      if (!hasPastSigningCertificates()) {
        if (!param1SigningDetails.hasPastSigningCertificates() || !param1SigningDetails.hasAncestorOrSelf(this))
          param1SigningDetails = this; 
        return param1SigningDetails;
      } 
      if (!param1SigningDetails.hasPastSigningCertificates())
        return this; 
      SigningDetails signingDetails = getDescendantOrSelf(param1SigningDetails);
      if (signingDetails == null)
        return this; 
      if (signingDetails == this) {
        param1SigningDetails = mergeLineageWithAncestorOrSelf(param1SigningDetails);
      } else {
        param1SigningDetails = param1SigningDetails.mergeLineageWithAncestorOrSelf(this);
      } 
      return param1SigningDetails;
    }
    
    public boolean signaturesMatchExactly(SigningDetails param1SigningDetails) {
      return Signature.areExactMatch(this.signatures, param1SigningDetails.signatures);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      boolean bool;
      if (UNKNOWN == this) {
        bool = true;
      } else {
        bool = false;
      } 
      param1Parcel.writeBoolean(bool);
      if (bool)
        return; 
      param1Parcel.writeTypedArray((Parcelable[])this.signatures, param1Int);
      param1Parcel.writeInt(this.signatureSchemeVersion);
      param1Parcel.writeArraySet(this.publicKeys);
      param1Parcel.writeTypedArray((Parcelable[])this.pastSigningCertificates, param1Int);
    }
    
    public static class Builder {
      private Signature[] mPastSigningCertificates;
      
      private int mSignatureSchemeVersion = 0;
      
      private Signature[] mSignatures;
      
      private void checkInvariants() {
        if (this.mSignatures != null)
          return; 
        throw new IllegalStateException("SigningDetails requires the current signing certificates.");
      }
      
      public PackageParser.SigningDetails build() throws CertificateException {
        checkInvariants();
        return new PackageParser.SigningDetails(this.mSignatures, this.mSignatureSchemeVersion, this.mPastSigningCertificates);
      }
      
      public Builder setPastSigningCertificates(Signature[] param2ArrayOfSignature) {
        this.mPastSigningCertificates = param2ArrayOfSignature;
        return this;
      }
      
      public Builder setSignatureSchemeVersion(int param2Int) {
        this.mSignatureSchemeVersion = param2Int;
        return this;
      }
      
      public Builder setSignatures(Signature[] param2ArrayOfSignature) {
        this.mSignatures = param2ArrayOfSignature;
        return this;
      }
    }
    
    public static @interface CertCapabilities {
      public static final int AUTH = 16;
      
      public static final int INSTALLED_DATA = 1;
      
      public static final int PERMISSION = 4;
      
      public static final int ROLLBACK = 8;
      
      public static final int SHARED_USER_ID = 2;
    }
    
    public static @interface SignatureSchemeVersion {
      public static final int JAR = 1;
      
      public static final int SIGNING_BLOCK_V2 = 2;
      
      public static final int SIGNING_BLOCK_V3 = 3;
      
      public static final int SIGNING_BLOCK_V4 = 4;
      
      public static final int UNKNOWN = 0;
    }
  }
  
  class null implements Parcelable.Creator<SigningDetails> {
    public PackageParser.SigningDetails createFromParcel(Parcel param1Parcel) {
      return param1Parcel.readBoolean() ? PackageParser.SigningDetails.UNKNOWN : new PackageParser.SigningDetails(param1Parcel);
    }
    
    public PackageParser.SigningDetails[] newArray(int param1Int) {
      return new PackageParser.SigningDetails[param1Int];
    }
  }
  
  public static class Builder {
    private Signature[] mPastSigningCertificates;
    
    private int mSignatureSchemeVersion = 0;
    
    private Signature[] mSignatures;
    
    private void checkInvariants() {
      if (this.mSignatures != null)
        return; 
      throw new IllegalStateException("SigningDetails requires the current signing certificates.");
    }
    
    public PackageParser.SigningDetails build() throws CertificateException {
      checkInvariants();
      return new PackageParser.SigningDetails(this.mSignatures, this.mSignatureSchemeVersion, this.mPastSigningCertificates);
    }
    
    public Builder setPastSigningCertificates(Signature[] param1ArrayOfSignature) {
      this.mPastSigningCertificates = param1ArrayOfSignature;
      return this;
    }
    
    public Builder setSignatureSchemeVersion(int param1Int) {
      this.mSignatureSchemeVersion = param1Int;
      return this;
    }
    
    public Builder setSignatures(Signature[] param1ArrayOfSignature) {
      this.mSignatures = param1ArrayOfSignature;
      return this;
    }
  }
  
  public static @interface CertCapabilities {
    public static final int AUTH = 16;
    
    public static final int INSTALLED_DATA = 1;
    
    public static final int PERMISSION = 4;
    
    public static final int ROLLBACK = 8;
    
    public static final int SHARED_USER_ID = 2;
  }
  
  public static @interface SignatureSchemeVersion {
    public static final int JAR = 1;
    
    public static final int SIGNING_BLOCK_V2 = 2;
    
    public static final int SIGNING_BLOCK_V3 = 3;
    
    public static final int SIGNING_BLOCK_V4 = 4;
    
    public static final int UNKNOWN = 0;
  }
  
  private static class SplitNameComparator implements Comparator<String> {
    private SplitNameComparator() {}
    
    public int compare(String param1String1, String param1String2) {
      return (param1String1 == null) ? -1 : ((param1String2 == null) ? 1 : param1String1.compareTo(param1String2));
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
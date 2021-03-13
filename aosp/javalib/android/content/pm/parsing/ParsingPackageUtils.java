package android.content.pm.parsing;

import android.app.ActivityThread;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureGroupInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageParser;
import android.content.pm.Signature;
import android.content.pm.parsing.component.ComponentParseUtils;
import android.content.pm.parsing.component.ParsedActivity;
import android.content.pm.parsing.component.ParsedAttribution;
import android.content.pm.parsing.component.ParsedAttributionUtils;
import android.content.pm.parsing.component.ParsedInstrumentation;
import android.content.pm.parsing.component.ParsedInstrumentationUtils;
import android.content.pm.parsing.component.ParsedIntentInfo;
import android.content.pm.parsing.component.ParsedIntentInfoUtils;
import android.content.pm.parsing.component.ParsedPermission;
import android.content.pm.parsing.component.ParsedPermissionGroup;
import android.content.pm.parsing.component.ParsedPermissionUtils;
import android.content.pm.parsing.component.ParsedProcess;
import android.content.pm.parsing.component.ParsedProcessUtils;
import android.content.pm.parsing.component.ParsedService;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.pm.parsing.result.ParseTypeImpl;
import android.content.pm.permission.SplitPermissionInfoParcelable;
import android.content.pm.split.DefaultSplitAssetLoader;
import android.content.res.ApkAssets;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.Trace;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.Slog;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.util.apk.ApkSignatureVerifier;
import com.android.internal.R;
import com.android.internal.os.ClassLoaderFactory;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.XmlUtils;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import libcore.io.IoUtils;
import libcore.util.EmptyArray;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ParsingPackageUtils {
  public static final String TAG = "PackageParsing";
  
  private Callback mCallback;
  
  private DisplayMetrics mDisplayMetrics;
  
  private boolean mOnlyCoreApps;
  
  private String[] mSeparateProcesses;
  
  public ParsingPackageUtils(boolean paramBoolean, String[] paramArrayOfString, DisplayMetrics paramDisplayMetrics, Callback paramCallback) {
    this.mOnlyCoreApps = paramBoolean;
    this.mSeparateProcesses = paramArrayOfString;
    this.mDisplayMetrics = paramDisplayMetrics;
    this.mCallback = paramCallback;
  }
  
  private static float aFloat(float paramFloat, int paramInt, TypedArray paramTypedArray) {
    return paramTypedArray.getFloat(paramInt, paramFloat);
  }
  
  private static float aFloat(int paramInt, TypedArray paramTypedArray) {
    return paramTypedArray.getFloat(paramInt, 0.0F);
  }
  
  private static void adjustPackageToBeUnresizeableAndUnpipable(ParsingPackage paramParsingPackage) {
    List<ParsedActivity> list = paramParsingPackage.getActivities();
    int i = list.size();
    for (byte b = 0; b < i; b++) {
      ParsedActivity parsedActivity = list.get(b);
      parsedActivity.setResizeMode(0).setFlags(parsedActivity.getFlags() & 0xFFBFFFFF);
    } 
  }
  
  private static int anInt(int paramInt1, int paramInt2, TypedArray paramTypedArray) {
    return paramTypedArray.getInt(paramInt2, paramInt1);
  }
  
  private static int anInt(int paramInt, TypedArray paramTypedArray) {
    return paramTypedArray.getInt(paramInt, 0);
  }
  
  private static int anInteger(int paramInt1, int paramInt2, TypedArray paramTypedArray) {
    return paramTypedArray.getInteger(paramInt2, paramInt1);
  }
  
  private static boolean bool(boolean paramBoolean, int paramInt, TypedArray paramTypedArray) {
    return paramTypedArray.getBoolean(paramInt, paramBoolean);
  }
  
  public static ParseResult<Integer> computeMinSdkVersion(int paramInt1, String paramString, int paramInt2, String[] paramArrayOfString, ParseInput paramParseInput) {
    StringBuilder stringBuilder1;
    if (paramString == null) {
      if (paramInt1 <= paramInt2)
        return paramParseInput.success(Integer.valueOf(paramInt1)); 
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Requires newer sdk version #");
      stringBuilder1.append(paramInt1);
      stringBuilder1.append(" (current version is #");
      stringBuilder1.append(paramInt2);
      stringBuilder1.append(")");
      return paramParseInput.error(-12, stringBuilder1.toString());
    } 
    if (matchTargetCode(paramArrayOfString, (String)stringBuilder1))
      return paramParseInput.success(Integer.valueOf(10000)); 
    if (paramArrayOfString.length > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requires development platform ");
      stringBuilder.append((String)stringBuilder1);
      stringBuilder.append(" (current platform is any of ");
      stringBuilder.append(Arrays.toString((Object[])paramArrayOfString));
      stringBuilder.append(")");
      return paramParseInput.error(-12, stringBuilder.toString());
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Requires development platform ");
    stringBuilder2.append((String)stringBuilder1);
    stringBuilder2.append(" but this is a release platform.");
    return paramParseInput.error(-12, stringBuilder2.toString());
  }
  
  public static ParseResult<Integer> computeTargetSdkVersion(int paramInt, String paramString, String[] paramArrayOfString, ParseInput paramParseInput) {
    if (paramString == null)
      return paramParseInput.success(Integer.valueOf(paramInt)); 
    if (matchTargetCode(paramArrayOfString, paramString))
      return paramParseInput.success(Integer.valueOf(10000)); 
    if (paramArrayOfString.length > 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Requires development platform ");
      stringBuilder1.append(paramString);
      stringBuilder1.append(" (current platform is any of ");
      stringBuilder1.append(Arrays.toString((Object[])paramArrayOfString));
      stringBuilder1.append(")");
      return paramParseInput.error(-12, stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Requires development platform ");
    stringBuilder.append(paramString);
    stringBuilder.append(" but this is a release platform.");
    return paramParseInput.error(-12, stringBuilder.toString());
  }
  
  private static void convertNewPermissions(ParsingPackage paramParsingPackage) {
    int i = PackageParser.NEW_PERMISSIONS.length;
    StringBuilder stringBuilder = null;
    byte b = 0;
    while (b < i) {
      PackageParser.NewPermissionInfo newPermissionInfo = PackageParser.NEW_PERMISSIONS[b];
      if (paramParsingPackage.getTargetSdkVersion() >= newPermissionInfo.sdkVersion)
        break; 
      StringBuilder stringBuilder1 = stringBuilder;
      if (!paramParsingPackage.getRequestedPermissions().contains(newPermissionInfo.name)) {
        if (stringBuilder == null) {
          stringBuilder = new StringBuilder(128);
          stringBuilder.append(paramParsingPackage.getPackageName());
          stringBuilder.append(": compat added ");
        } else {
          stringBuilder.append(' ');
        } 
        stringBuilder.append(newPermissionInfo.name);
        paramParsingPackage.addRequestedPermission(newPermissionInfo.name).addImplicitPermission(newPermissionInfo.name);
        stringBuilder1 = stringBuilder;
      } 
      b++;
      stringBuilder = stringBuilder1;
    } 
    if (stringBuilder != null)
      Slog.i("PackageParsing", stringBuilder.toString()); 
  }
  
  private static void convertSplitPermissions(ParsingPackage paramParsingPackage) {
    try {
      List<SplitPermissionInfoParcelable> list = ActivityThread.getPermissionManager().getSplitPermissions();
      int i = list.size();
      for (byte b = 0; b < i; b++) {
        SplitPermissionInfoParcelable splitPermissionInfoParcelable = list.get(b);
        List<String> list1 = paramParsingPackage.getRequestedPermissions();
        if (paramParsingPackage.getTargetSdkVersion() < splitPermissionInfoParcelable.getTargetSdk() && list1.contains(splitPermissionInfoParcelable.getSplitPermission())) {
          List<String> list2 = splitPermissionInfoParcelable.getNewPermissions();
          for (byte b1 = 0; b1 < list2.size(); b1++) {
            String str = list2.get(b1);
            if (!list1.contains(str))
              paramParsingPackage.addRequestedPermission(str).addImplicitPermission(str); 
          } 
        } 
      } 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private static SparseIntArray exactSizedCopyOfSparseArray(SparseIntArray paramSparseIntArray) {
    if (paramSparseIntArray == null)
      return null; 
    SparseIntArray sparseIntArray = new SparseIntArray(paramSparseIntArray.size());
    for (byte b = 0; b < paramSparseIntArray.size(); b++)
      sparseIntArray.put(paramSparseIntArray.keyAt(b), paramSparseIntArray.valueAt(b)); 
    return sparseIntArray;
  }
  
  private static ParseResult<ParsedActivity> generateAppDetailsHiddenActivity(ParseInput paramParseInput, ParsingPackage paramParsingPackage) {
    String str1 = paramParsingPackage.getPackageName();
    ParseResult parseResult = ComponentParseUtils.buildTaskAffinityName(str1, str1, ":app_details", paramParseInput);
    if (parseResult.isError())
      return paramParseInput.error(parseResult); 
    String str2 = (String)parseResult.getResult();
    return paramParseInput.success(ParsedActivity.makeAppDetailsActivity(str1, paramParsingPackage.getProcessName(), paramParsingPackage.getUiOptions(), str2, paramParsingPackage.isBaseHardwareAccelerated()));
  }
  
  public static PackageParser.SigningDetails getSigningDetails(ParsingPackageRead paramParsingPackageRead, boolean paramBoolean) throws PackageParser.PackageParserException {
    PackageParser.SigningDetails signingDetails = PackageParser.SigningDetails.UNKNOWN;
    ParseInput parseInput = ParseTypeImpl.forDefaultParsing().reset();
    Trace.traceBegin(262144L, "collectCertificates");
    try {
      PackageParser.SigningDetails signingDetails1;
      ParseResult<PackageParser.SigningDetails> parseResult = getSigningDetails(parseInput, paramParsingPackageRead.getBaseCodePath(), paramBoolean, paramParsingPackageRead.isStaticSharedLibrary(), signingDetails, paramParsingPackageRead.getTargetSdkVersion());
      if (!parseResult.isError()) {
        signingDetails1 = (PackageParser.SigningDetails)parseResult.getResult();
        String[] arrayOfString = paramParsingPackageRead.getSplitCodePaths();
        boolean bool = ArrayUtils.isEmpty((Object[])arrayOfString);
        PackageParser.SigningDetails signingDetails2 = signingDetails1;
        if (!bool) {
          byte b = 0;
          try {
            while (b < arrayOfString.length) {
              ParseResult<PackageParser.SigningDetails> parseResult1 = getSigningDetails(parseInput, arrayOfString[b], paramBoolean, paramParsingPackageRead.isStaticSharedLibrary(), signingDetails1, paramParsingPackageRead.getTargetSdkVersion());
              if (!parseResult1.isError()) {
                signingDetails1 = (PackageParser.SigningDetails)parseResult1.getResult();
                b++;
                continue;
              } 
              PackageParser.PackageParserException packageParserException1 = new PackageParser.PackageParserException();
              this(signingDetails1.getErrorCode(), signingDetails1.getErrorMessage(), signingDetails1.getException());
              throw packageParserException1;
            } 
            signingDetails2 = signingDetails1;
          } finally {}
        } 
        return signingDetails2;
      } 
      PackageParser.PackageParserException packageParserException = new PackageParser.PackageParserException();
      this(signingDetails1.getErrorCode(), signingDetails1.getErrorMessage(), signingDetails1.getException());
      throw packageParserException;
    } finally {
      Trace.traceEnd(262144L);
    } 
  }
  
  public static ParseResult<PackageParser.SigningDetails> getSigningDetails(ParseInput paramParseInput, String paramString, boolean paramBoolean1, boolean paramBoolean2, PackageParser.SigningDetails paramSigningDetails, int paramInt) {
    StringBuilder stringBuilder;
    paramInt = ApkSignatureVerifier.getMinimumSignatureSchemeVersionForTargetSdk(paramInt);
    if (paramBoolean2)
      paramInt = 2; 
    if (paramBoolean1)
      try {
        PackageParser.SigningDetails signingDetails1 = ApkSignatureVerifier.unsafeGetCertsWithoutVerification(paramString, 1);
        if (paramSigningDetails == PackageParser.SigningDetails.UNKNOWN)
          return paramParseInput.success(signingDetails1); 
        if (!Signature.areExactMatch(paramSigningDetails.signatures, signingDetails1.signatures)) {
          stringBuilder = new StringBuilder();
          stringBuilder.append(paramString);
          stringBuilder.append(" has mismatched certificates");
          return paramParseInput.error(-104, stringBuilder.toString());
        } 
        return paramParseInput.success(stringBuilder);
      } catch (android.content.pm.PackageParser.PackageParserException packageParserException) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Failed collecting certificates for ");
        stringBuilder.append(paramString);
        return paramParseInput.error(-103, stringBuilder.toString(), (Exception)packageParserException);
      }  
    PackageParser.SigningDetails signingDetails = ApkSignatureVerifier.verify(paramString, paramInt);
    if (stringBuilder == PackageParser.SigningDetails.UNKNOWN)
      return paramParseInput.success(signingDetails); 
    if (!Signature.areExactMatch(((PackageParser.SigningDetails)stringBuilder).signatures, signingDetails.signatures)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" has mismatched certificates");
      return paramParseInput.error(-104, stringBuilder.toString());
    } 
    return paramParseInput.success(stringBuilder);
  }
  
  private static boolean hasDomainURLs(ParsingPackage paramParsingPackage) {
    List<ParsedActivity> list = paramParsingPackage.getActivities();
    int i = list.size();
    for (byte b = 0; b < i; b++) {
      List<ParsedIntentInfo> list1 = ((ParsedActivity)list.get(b)).getIntents();
      int j = list1.size();
      for (byte b1 = 0; b1 < j; b1++) {
        ParsedIntentInfo parsedIntentInfo = list1.get(b1);
        if (parsedIntentInfo.hasAction("android.intent.action.VIEW") && parsedIntentInfo.hasAction("android.intent.action.VIEW") && (parsedIntentInfo.hasDataScheme("http") || parsedIntentInfo.hasDataScheme("https")))
          return true; 
      } 
    } 
    return false;
  }
  
  private static boolean matchTargetCode(String[] paramArrayOfString, String paramString) {
    int i = paramString.indexOf('.');
    if (i != -1)
      paramString = paramString.substring(0, i); 
    return ArrayUtils.contains((Object[])paramArrayOfString, paramString);
  }
  
  private static String nonConfigString(int paramInt1, int paramInt2, TypedArray paramTypedArray) {
    return paramTypedArray.getNonConfigurationString(paramInt2, paramInt1);
  }
  
  private static String nonResString(int paramInt, TypedArray paramTypedArray) {
    return paramTypedArray.getNonResourceString(paramInt);
  }
  
  private static ParseResult<String[]> parseAdditionalCertificates(ParseInput paramParseInput, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws XmlPullParserException, IOException {
    String[] arrayOfString = EmptyArray.STRING;
    int i = paramXmlResourceParser.getDepth();
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        if (j != 2)
          continue; 
        String[] arrayOfString1 = arrayOfString;
        if (paramXmlResourceParser.getName().equals("additional-certificate")) {
          TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestAdditionalCertificate);
          try {
            String str = typedArray.getNonResourceString(0);
            if (TextUtils.isEmpty(str)) {
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("Bad additional-certificate declaration with empty certDigest:");
              stringBuilder.append(str);
              return paramParseInput.error(stringBuilder.toString());
            } 
            arrayOfString1 = (String[])ArrayUtils.appendElement(String.class, (Object[])arrayOfString, str.replace(":", "").toLowerCase());
          } finally {
            typedArray.recycle();
          } 
        } 
        arrayOfString = arrayOfString1;
        continue;
      } 
      break;
    } 
    return paramParseInput.success(arrayOfString);
  }
  
  private static ParseResult<ParsingPackage> parseAdoptPermissions(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestOriginalPackage);
    try {
      String str = nonConfigString(0, 0, typedArray);
      if (str != null)
        paramParsingPackage.addAdoptPermission(str); 
      return paramParseInput.success(paramParsingPackage);
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsingPackage> parseAttribution(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws IOException, XmlPullParserException {
    ParseResult parseResult = ParsedAttributionUtils.parseAttribution(paramResources, paramXmlResourceParser, paramParseInput);
    return parseResult.isError() ? paramParseInput.error(parseResult) : paramParseInput.success(paramParsingPackage.addAttribution((ParsedAttribution)parseResult.getResult()));
  }
  
  private ParseResult<ParsingPackage> parseBaseApk(ParseInput paramParseInput, File paramFile, String paramString, AssetManager paramAssetManager, int paramInt) {
    String str2;
    String str1 = paramFile.getAbsolutePath();
    if (str1.startsWith("/mnt/expand/")) {
      int j = str1.indexOf('/', "/mnt/expand/".length());
      str2 = str1.substring("/mnt/expand/".length(), j);
    } else {
      str2 = null;
    } 
    int i = paramAssetManager.findCookieForPath(str1);
    if (i == 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Failed adding asset path: ");
      stringBuilder1.append(str1);
      return paramParseInput.error(-101, stringBuilder1.toString());
    } 
    try {
      XmlResourceParser xmlResourceParser = paramAssetManager.openXmlResourceParser(i, "AndroidManifest.xml");
      try {
        Resources resources = new Resources();
        try {
          this(paramAssetManager, this.mDisplayMetrics, null);
          ParseResult<ParsingPackage> parseResult = parseBaseApk(paramParseInput, str1, paramString, resources, xmlResourceParser, paramInt);
          if (parseResult.isError()) {
            paramInt = parseResult.getErrorCode();
            StringBuilder stringBuilder1 = new StringBuilder();
            this();
            stringBuilder1.append(str1);
            stringBuilder1.append(" (at ");
            stringBuilder1.append(xmlResourceParser.getPositionDescription());
            stringBuilder1.append("): ");
            stringBuilder1.append(parseResult.getErrorMessage());
            parseResult = paramParseInput.error(paramInt, stringBuilder1.toString());
            if (xmlResourceParser != null) {
              try {
                xmlResourceParser.close();
                return parseResult;
              } catch (Exception null) {}
            } else {
              return (ParseResult<ParsingPackage>)exception;
            } 
          } else {
            ParsingPackage parsingPackage = (ParsingPackage)exception.getResult();
            if (paramAssetManager.containsAllocatedTable()) {
              ParseResult parseResult2 = paramParseInput.deferError("Targeting R+ (version 30 and above) requires the resources.arsc of installed APKs to be stored uncompressed and aligned on a 4-byte boundary", 132742131L);
              if (parseResult2.isError()) {
                parseResult1 = paramParseInput.error(-124, parseResult2.getErrorMessage());
                if (xmlResourceParser != null)
                  xmlResourceParser.close(); 
                return parseResult1;
              } 
            } 
            ApkAssets apkAssets = paramAssetManager.getApkAssets()[0];
            if (apkAssets.definesOverlayable()) {
              SparseArray sparseArray = paramAssetManager.getAssignedPackageIdentifiers();
              int j = sparseArray.size();
              i = 0;
              while (i < j) {
                ApkAssets apkAssets2;
                ParseResult<ParsingPackage> parseResult2;
                ParseResult<ParsingPackage> parseResult4;
                ApkAssets apkAssets3;
                Map map = paramAssetManager.getOverlayableMap((String)sparseArray.get(i));
                if (map != null && !map.isEmpty()) {
                  for (String str : map.keySet())
                    parsingPackage.addOverlayable(str, (String)map.get(str)); 
                  ApkAssets apkAssets4 = apkAssets;
                  parseResult4 = parseResult1;
                  apkAssets2 = apkAssets4;
                } else {
                  ApkAssets apkAssets4 = apkAssets2;
                  parseResult2 = parseResult4;
                  apkAssets3 = apkAssets4;
                } 
                i++;
                ParseResult<ParsingPackage> parseResult5 = parseResult2;
                ApkAssets apkAssets1 = apkAssets3;
                ParseResult<ParsingPackage> parseResult3 = parseResult5;
              } 
            } 
            parsingPackage.setVolumeUuid(str2);
            if ((paramInt & 0x20) != 0) {
              parsingPackage.setSigningDetails(getSigningDetails(parsingPackage, false));
            } else {
              parsingPackage.setSigningDetails(PackageParser.SigningDetails.UNKNOWN);
            } 
            ParseResult<ParsingPackage> parseResult1 = paramParseInput.success(parsingPackage);
            if (xmlResourceParser != null)
              xmlResourceParser.close(); 
            return parseResult1;
          } 
        } finally {}
      } finally {}
      if (xmlResourceParser != null)
        try {
          xmlResourceParser.close();
        } finally {
          paramString = null;
        }  
      throw paramFile;
    } catch (Exception exception) {}
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Failed to read manifest from ");
    stringBuilder.append(str1);
    return paramParseInput.error(-102, stringBuilder.toString(), exception);
  }
  
  private ParseResult<ParsingPackage> parseBaseApk(ParseInput paramParseInput, String paramString1, String paramString2, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt) throws XmlPullParserException, IOException, PackageParser.PackageParserException {
    StringBuilder stringBuilder;
    ParseResult<Pair<String, String>> parseResult = ApkLiteParseUtils.parsePackageSplitNames(paramParseInput, (XmlPullParser)paramXmlResourceParser, (AttributeSet)paramXmlResourceParser);
    if (parseResult.isError())
      return paramParseInput.error(parseResult); 
    Pair pair = (Pair)parseResult.getResult();
    String str2 = (String)pair.first;
    String str1 = (String)pair.second;
    if (!TextUtils.isEmpty(str1)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Expected base APK, but found split ");
      stringBuilder.append(str1);
      return paramParseInput.error(-106, stringBuilder.toString());
    } 
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifest);
    try {
      boolean bool = paramXmlResourceParser.getAttributeBooleanValue(null, "coreApp", false);
      ParsingPackage parsingPackage = this.mCallback.startParsingPackage(str2, (String)stringBuilder, paramString2, typedArray, bool);
      try {
        ParseResult<ParsingPackage> parseResult2 = parseBaseApkTags(paramParseInput, parsingPackage, typedArray, paramResources, paramXmlResourceParser, paramInt);
        bool = parseResult2.isError();
        if (bool) {
          typedArray.recycle();
          return parseResult2;
        } 
        ParseResult<ParsingPackage> parseResult1 = paramParseInput.success(parsingPackage);
        typedArray.recycle();
        return parseResult1;
      } finally {}
    } finally {}
    typedArray.recycle();
    throw paramParseInput;
  }
  
  private ParseResult parseBaseApkTag(String paramString, ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt) throws IOException, XmlPullParserException {
    byte b;
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 1818228622:
        if (paramString.equals("compatible-screens")) {
          b = 20;
          break;
        } 
      case 1792785909:
        if (paramString.equals("uses-feature")) {
          b = 11;
          break;
        } 
      case 1705921021:
        if (paramString.equals("uses-permission-sdk-m")) {
          b = 8;
          break;
        } 
      case 1682371816:
        if (paramString.equals("feature-group")) {
          b = 12;
          break;
        } 
      case 1439495522:
        if (paramString.equals("protected-broadcast")) {
          b = 15;
          break;
        } 
      case 1343942321:
        if (paramString.equals("uses-permission-sdk-23")) {
          b = 9;
          break;
        } 
      case 896788286:
        if (paramString.equals("supports-screens")) {
          b = 14;
          break;
        } 
      case 655087462:
        if (paramString.equals("queries")) {
          b = 24;
          break;
        } 
      case 632228327:
        if (paramString.equals("adopt-permissions")) {
          b = 18;
          break;
        } 
      case 599862896:
        if (paramString.equals("uses-permission")) {
          b = 7;
          break;
        } 
      case 544550766:
        if (paramString.equals("instrumentation")) {
          b = 16;
          break;
        } 
      case 454915839:
        if (paramString.equals("key-sets")) {
          b = 1;
          break;
        } 
      case 349565761:
        if (paramString.equals("supports-input")) {
          b = 21;
          break;
        } 
      case 119109844:
        if (paramString.equals("uses-gl-texture")) {
          b = 19;
          break;
        } 
      case -129269526:
        if (paramString.equals("eat-comment")) {
          b = 22;
          break;
        } 
      case -170723071:
        if (paramString.equals("permission-group")) {
          b = 4;
          break;
        } 
      case -266709319:
        if (paramString.equals("uses-sdk")) {
          b = 13;
          break;
        } 
      case -309882753:
        if (paramString.equals("attribution")) {
          b = 3;
          break;
        } 
      case -517618225:
        if (paramString.equals("permission")) {
          b = 5;
          break;
        } 
      case -979207434:
        if (paramString.equals("feature")) {
          b = 2;
          break;
        } 
      case -998269702:
        if (paramString.equals("restrict-update")) {
          b = 23;
          break;
        } 
      case -1091287984:
        if (paramString.equals("overlay")) {
          b = 0;
          break;
        } 
      case -1108197302:
        if (paramString.equals("original-package")) {
          b = 17;
          break;
        } 
      case -1667688228:
        if (paramString.equals("permission-tree")) {
          b = 6;
          break;
        } 
      case -1773650763:
        if (paramString.equals("uses-configuration")) {
          b = 10;
          break;
        } 
    } 
    switch (b) {
      default:
        return ParsingUtils.unknownTag("<manifest>", paramParsingPackage, paramXmlResourceParser, paramParseInput);
      case 24:
        return parseQueries(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 23:
        return parseRestrictUpdateHash(paramInt, paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 19:
      case 20:
      case 21:
      case 22:
        XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
        return paramParseInput.success(paramParsingPackage);
      case 18:
        return parseAdoptPermissions(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 17:
        return parseOriginalPackage(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 16:
        return parseInstrumentation(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 15:
        return parseProtectedBroadcast(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 14:
        return parseSupportScreens(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 13:
        return parseUsesSdk(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 12:
        return parseFeatureGroup(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 11:
        return parseUsesFeature(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 10:
        return parseUsesConfiguration(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 7:
      case 8:
      case 9:
        return parseUsesPermission(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 6:
        return parsePermissionTree(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 5:
        return parsePermission(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 4:
        return parsePermissionGroup(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 2:
      case 3:
        return parseAttribution(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 1:
        return parseKeySets(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 0:
        break;
    } 
    return parseOverlay(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
  }
  
  private ParseResult<ParsingPackage> parseBaseApkTags(ParseInput paramParseInput, ParsingPackage paramParsingPackage, TypedArray paramTypedArray, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt) throws XmlPullParserException, IOException {
    boolean bool1;
    ParseResult<ParsingPackage> parseResult = parseSharedUser(paramParseInput, paramParsingPackage, paramTypedArray);
    if (parseResult.isError())
      return parseResult; 
    ParsingPackage parsingPackage = paramParsingPackage.setInstallLocation(anInteger(-1, 4, paramTypedArray)).setTargetSandboxVersion(anInteger(1, 7, paramTypedArray));
    if ((paramInt & 0x8) != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    parsingPackage.setExternalStorage(bool1);
    int i = paramXmlResourceParser.getDepth();
    boolean bool2 = false;
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        ParseResult<ParsingPackage> parseResult1;
        if (j != 2)
          continue; 
        String str = paramXmlResourceParser.getName();
        if ("application".equals(str)) {
          if (bool2) {
            Slog.w("PackageParsing", "<manifest> has more than one <application>");
            parseResult1 = paramParseInput.success(null);
          } else {
            bool2 = true;
            parseResult1 = parseBaseApplication(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser, paramInt);
          } 
        } else {
          parseResult1 = parseBaseApkTag((String)parseResult1, paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser, paramInt);
        } 
        if (parseResult1.isError())
          return paramParseInput.error(parseResult1); 
        continue;
      } 
      break;
    } 
    if (!bool2 && ArrayUtils.size(paramParsingPackage.getInstrumentations()) == 0) {
      ParseResult parseResult1 = paramParseInput.deferError("<manifest> does not contain an <application> or <instrumentation>", 150776642L);
      if (parseResult1.isError())
        return paramParseInput.error(parseResult1); 
    } 
    if (!ParsedAttribution.isCombinationValid(paramParsingPackage.getAttributions()))
      return paramParseInput.error(-101, "Combination <feature> tags are not valid"); 
    convertNewPermissions(paramParsingPackage);
    convertSplitPermissions(paramParsingPackage);
    if (paramParsingPackage.getTargetSdkVersion() < 4 || (!paramParsingPackage.isSupportsSmallScreens() && !paramParsingPackage.isSupportsNormalScreens() && !paramParsingPackage.isSupportsLargeScreens() && !paramParsingPackage.isSupportsExtraLargeScreens() && !paramParsingPackage.isResizeable() && !paramParsingPackage.isAnyDensity()))
      adjustPackageToBeUnresizeableAndUnpipable(paramParsingPackage); 
    return paramParseInput.success(paramParsingPackage);
  }
  
  private void parseBaseAppBasicFlags(ParsingPackage paramParsingPackage, TypedArray paramTypedArray) {
    boolean bool2;
    int i = paramParsingPackage.getTargetSdkVersion();
    boolean bool1 = true;
    paramParsingPackage = paramParsingPackage.setAllowBackup(bool(true, 17, paramTypedArray)).setAllowClearUserData(bool(true, 5, paramTypedArray)).setAllowClearUserDataOnFailedRestore(bool(true, 54, paramTypedArray)).setAllowNativeHeapPointerTagging(bool(true, 59, paramTypedArray)).setEnabled(bool(true, 9, paramTypedArray)).setExtractNativeLibs(bool(true, 34, paramTypedArray)).setHasCode(bool(true, 7, paramTypedArray)).setAllowTaskReparenting(bool(false, 14, paramTypedArray)).setCantSaveState(bool(false, 47, paramTypedArray)).setCrossProfile(bool(false, 58, paramTypedArray)).setDebuggable(bool(false, 10, paramTypedArray)).setDefaultToDeviceProtectedStorage(bool(false, 38, paramTypedArray)).setDirectBootAware(bool(false, 39, paramTypedArray)).setForceQueryable(bool(false, 57, paramTypedArray)).setGame(bool(false, 31, paramTypedArray)).setHasFragileUserData(bool(false, 50, paramTypedArray)).setLargeHeap(bool(false, 24, paramTypedArray)).setMultiArch(bool(false, 33, paramTypedArray)).setPreserveLegacyExternalStorage(bool(false, 61, paramTypedArray)).setRequiredForAllUsers(bool(false, 27, paramTypedArray)).setSupportsRtl(bool(false, 26, paramTypedArray)).setTestOnly(bool(false, 15, paramTypedArray)).setUseEmbeddedDex(bool(false, 53, paramTypedArray)).setUsesNonSdkApi(bool(false, 49, paramTypedArray)).setVmSafeMode(bool(false, 20, paramTypedArray)).setAutoRevokePermissions(anInt(60, paramTypedArray));
    if (i >= 29) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    paramParsingPackage = paramParsingPackage.setAllowAudioPlaybackCapture(bool(bool2, 55, paramTypedArray));
    if (i >= 14) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    paramParsingPackage = paramParsingPackage.setBaseHardwareAccelerated(bool(bool2, 23, paramTypedArray));
    if (i < 29) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    paramParsingPackage = paramParsingPackage.setRequestLegacyExternalStorage(bool(bool2, 56, paramTypedArray));
    if (i < 28) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    paramParsingPackage.setUsesCleartextTraffic(bool(bool2, 36, paramTypedArray)).setUiOptions(anInt(25, paramTypedArray)).setCategory(anInt(-1, 43, paramTypedArray)).setMaxAspectRatio(aFloat(44, paramTypedArray)).setMinAspectRatio(aFloat(51, paramTypedArray)).setBanner(resId(30, paramTypedArray)).setDescriptionRes(resId(13, paramTypedArray)).setIconRes(resId(2, paramTypedArray)).setLogo(resId(22, paramTypedArray)).setNetworkSecurityConfigRes(resId(41, paramTypedArray)).setRoundIconRes(resId(42, paramTypedArray)).setTheme(resId(0, paramTypedArray)).setClassLoaderName(string(46, paramTypedArray)).setRequiredAccountType(string(29, paramTypedArray)).setRestrictedAccountType(string(28, paramTypedArray)).setZygotePreloadName(string(52, paramTypedArray)).setPermission(nonConfigString(0, 6, paramTypedArray));
  }
  
  private ParseResult parseBaseAppChildTag(ParseInput paramParseInput, String paramString, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt) throws IOException, XmlPullParserException {
    byte b;
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 1964930885:
        if (paramString.equals("uses-package")) {
          b = 6;
          break;
        } 
      case 178070147:
        if (paramString.equals("profileable")) {
          b = 7;
          break;
        } 
      case 166208699:
        if (paramString.equals("library")) {
          b = 2;
          break;
        } 
      case 8960125:
        if (paramString.equals("uses-static-library")) {
          b = 3;
          break;
        } 
      case -1056667556:
        if (paramString.equals("static-library")) {
          b = 1;
          break;
        } 
      case -1094759587:
        if (paramString.equals("processes")) {
          b = 5;
          break;
        } 
      case -1115949454:
        if (paramString.equals("meta-data")) {
          b = 0;
          break;
        } 
      case -1356765254:
        if (paramString.equals("uses-library")) {
          b = 4;
          break;
        } 
    } 
    switch (b) {
      default:
        return ParsingUtils.unknownTag("<application>", paramParsingPackage, paramXmlResourceParser, paramParseInput);
      case 7:
        return parseProfileable(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 6:
        return paramParseInput.success(null);
      case 5:
        return parseProcesses(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser, this.mSeparateProcesses, paramInt);
      case 4:
        return parseUsesLibrary(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 3:
        return parseUsesStaticLibrary(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser);
      case 2:
        return parseLibrary(paramParsingPackage, paramResources, paramXmlResourceParser, paramParseInput);
      case 1:
        return parseStaticLibrary(paramParsingPackage, paramResources, paramXmlResourceParser, paramParseInput);
      case 0:
        break;
    } 
    ParseResult<Bundle> parseResult = parseMetaData(paramParsingPackage, paramResources, paramXmlResourceParser, paramParsingPackage.getMetaData(), paramParseInput);
    if (parseResult.isSuccess())
      paramParsingPackage.setMetaData((Bundle)parseResult.getResult()); 
    return parseResult;
  }
  
  private ParseResult<ParsingPackage> parseBaseApplication(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt) throws XmlPullParserException, IOException {
    // Byte code:
    //   0: aload_2
    //   1: invokeinterface getPackageName : ()Ljava/lang/String;
    //   6: astore #6
    //   8: aload_2
    //   9: invokeinterface getTargetSdkVersion : ()I
    //   14: istore #7
    //   16: aload_3
    //   17: aload #4
    //   19: getstatic com/android/internal/R$styleable.AndroidManifestApplication : [I
    //   22: invokevirtual obtainAttributes : (Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
    //   25: astore #8
    //   27: aload #8
    //   29: ifnonnull -> 53
    //   32: aload_1
    //   33: ldc_w '<application> does not contain any attributes'
    //   36: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   41: astore_1
    //   42: aload #8
    //   44: invokevirtual recycle : ()V
    //   47: aload_1
    //   48: areturn
    //   49: astore_1
    //   50: goto -> 1848
    //   53: aload #8
    //   55: iconst_3
    //   56: iconst_0
    //   57: invokevirtual getNonConfigurationString : (II)Ljava/lang/String;
    //   60: astore #9
    //   62: aload #9
    //   64: ifnull -> 167
    //   67: aload_2
    //   68: invokeinterface getPackageName : ()Ljava/lang/String;
    //   73: astore #10
    //   75: aload #10
    //   77: aload #9
    //   79: invokestatic buildClassName : (Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   82: astore #9
    //   84: getstatic android/content/pm/PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME : Ljava/lang/String;
    //   87: aload #9
    //   89: invokevirtual equals : (Ljava/lang/Object;)Z
    //   92: ifeq -> 112
    //   95: aload_1
    //   96: ldc_w '<application> invalid android:name'
    //   99: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   104: astore_1
    //   105: aload #8
    //   107: invokevirtual recycle : ()V
    //   110: aload_1
    //   111: areturn
    //   112: aload #9
    //   114: ifnonnull -> 158
    //   117: new java/lang/StringBuilder
    //   120: astore_2
    //   121: aload_2
    //   122: invokespecial <init> : ()V
    //   125: aload_2
    //   126: ldc_w 'Empty class name in package '
    //   129: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: pop
    //   133: aload_2
    //   134: aload #10
    //   136: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: aload_1
    //   141: aload_2
    //   142: invokevirtual toString : ()Ljava/lang/String;
    //   145: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   150: astore_1
    //   151: aload #8
    //   153: invokevirtual recycle : ()V
    //   156: aload_1
    //   157: areturn
    //   158: aload_2
    //   159: aload #9
    //   161: invokeinterface setClassName : (Ljava/lang/String;)Landroid/content/pm/parsing/ParsingPackage;
    //   166: pop
    //   167: aload #8
    //   169: iconst_1
    //   170: invokevirtual peekValue : (I)Landroid/util/TypedValue;
    //   173: astore #10
    //   175: aload #10
    //   177: ifnull -> 212
    //   180: aload_2
    //   181: aload #10
    //   183: getfield resourceId : I
    //   186: invokeinterface setLabelRes : (I)Landroid/content/pm/parsing/ParsingPackage;
    //   191: pop
    //   192: aload #10
    //   194: getfield resourceId : I
    //   197: ifne -> 212
    //   200: aload_2
    //   201: aload #10
    //   203: invokevirtual coerceToString : ()Ljava/lang/CharSequence;
    //   206: invokeinterface setNonLocalizedLabel : (Ljava/lang/CharSequence;)Landroid/content/pm/parsing/ParsingPackage;
    //   211: pop
    //   212: aload_0
    //   213: aload_2
    //   214: aload #8
    //   216: invokespecial parseBaseAppBasicFlags : (Landroid/content/pm/parsing/ParsingPackage;Landroid/content/res/TypedArray;)V
    //   219: sipush #1024
    //   222: iconst_4
    //   223: aload #8
    //   225: invokestatic nonConfigString : (IILandroid/content/res/TypedArray;)Ljava/lang/String;
    //   228: astore #10
    //   230: aload #10
    //   232: ifnull -> 299
    //   235: aload #6
    //   237: aload #10
    //   239: invokestatic buildClassName : (Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   242: astore #10
    //   244: aload #10
    //   246: ifnonnull -> 290
    //   249: new java/lang/StringBuilder
    //   252: astore_2
    //   253: aload_2
    //   254: invokespecial <init> : ()V
    //   257: aload_2
    //   258: ldc_w 'Empty class name in package '
    //   261: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: pop
    //   265: aload_2
    //   266: aload #6
    //   268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: pop
    //   272: aload_1
    //   273: aload_2
    //   274: invokevirtual toString : ()Ljava/lang/String;
    //   277: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   282: astore_1
    //   283: aload #8
    //   285: invokevirtual recycle : ()V
    //   288: aload_1
    //   289: areturn
    //   290: aload_2
    //   291: aload #10
    //   293: invokeinterface setManageSpaceActivityName : (Ljava/lang/String;)Landroid/content/pm/parsing/ParsingPackage;
    //   298: pop
    //   299: aload_2
    //   300: invokeinterface isAllowBackup : ()Z
    //   305: istore #11
    //   307: iload #11
    //   309: ifeq -> 506
    //   312: sipush #1024
    //   315: bipush #16
    //   317: aload #8
    //   319: invokestatic nonConfigString : (IILandroid/content/res/TypedArray;)Ljava/lang/String;
    //   322: astore #10
    //   324: aload #10
    //   326: ifnull -> 448
    //   329: aload #6
    //   331: aload #10
    //   333: invokestatic buildClassName : (Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   336: astore #10
    //   338: aload #10
    //   340: ifnonnull -> 384
    //   343: new java/lang/StringBuilder
    //   346: astore_2
    //   347: aload_2
    //   348: invokespecial <init> : ()V
    //   351: aload_2
    //   352: ldc_w 'Empty class name in package '
    //   355: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   358: pop
    //   359: aload_2
    //   360: aload #6
    //   362: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: pop
    //   366: aload_1
    //   367: aload_2
    //   368: invokevirtual toString : ()Ljava/lang/String;
    //   371: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   376: astore_1
    //   377: aload #8
    //   379: invokevirtual recycle : ()V
    //   382: aload_1
    //   383: areturn
    //   384: aload_2
    //   385: aload #10
    //   387: invokeinterface setBackupAgentName : (Ljava/lang/String;)Landroid/content/pm/parsing/ParsingPackage;
    //   392: iconst_1
    //   393: bipush #18
    //   395: aload #8
    //   397: invokestatic bool : (ZILandroid/content/res/TypedArray;)Z
    //   400: invokeinterface setKillAfterRestore : (Z)Landroid/content/pm/parsing/ParsingPackage;
    //   405: iconst_0
    //   406: bipush #21
    //   408: aload #8
    //   410: invokestatic bool : (ZILandroid/content/res/TypedArray;)Z
    //   413: invokeinterface setRestoreAnyVersion : (Z)Landroid/content/pm/parsing/ParsingPackage;
    //   418: iconst_0
    //   419: bipush #32
    //   421: aload #8
    //   423: invokestatic bool : (ZILandroid/content/res/TypedArray;)Z
    //   426: invokeinterface setFullBackupOnly : (Z)Landroid/content/pm/parsing/ParsingPackage;
    //   431: iconst_0
    //   432: bipush #40
    //   434: aload #8
    //   436: invokestatic bool : (ZILandroid/content/res/TypedArray;)Z
    //   439: invokeinterface setBackupInForeground : (Z)Landroid/content/pm/parsing/ParsingPackage;
    //   444: pop
    //   445: goto -> 448
    //   448: aload #8
    //   450: bipush #35
    //   452: invokevirtual peekValue : (I)Landroid/util/TypedValue;
    //   455: astore #10
    //   457: aload #10
    //   459: ifnull -> 506
    //   462: aload #10
    //   464: getfield resourceId : I
    //   467: istore #12
    //   469: aload #10
    //   471: getfield resourceId : I
    //   474: ifne -> 494
    //   477: aload #10
    //   479: getfield data : I
    //   482: ifne -> 491
    //   485: iconst_m1
    //   486: istore #12
    //   488: goto -> 494
    //   491: iconst_0
    //   492: istore #12
    //   494: aload_2
    //   495: iload #12
    //   497: invokeinterface setFullBackupContent : (I)Landroid/content/pm/parsing/ParsingPackage;
    //   502: pop
    //   503: goto -> 506
    //   506: aload #8
    //   508: bipush #8
    //   510: iconst_0
    //   511: invokevirtual getBoolean : (IZ)Z
    //   514: istore #11
    //   516: iload #11
    //   518: ifeq -> 570
    //   521: aload #8
    //   523: bipush #45
    //   525: invokevirtual getNonResourceString : (I)Ljava/lang/String;
    //   528: astore #10
    //   530: aload #10
    //   532: ifnull -> 558
    //   535: aload_0
    //   536: getfield mCallback : Landroid/content/pm/parsing/ParsingPackageUtils$Callback;
    //   539: aload #10
    //   541: invokeinterface hasFeature : (Ljava/lang/String;)Z
    //   546: ifeq -> 552
    //   549: goto -> 558
    //   552: iconst_0
    //   553: istore #11
    //   555: goto -> 561
    //   558: iconst_1
    //   559: istore #11
    //   561: aload_2
    //   562: iload #11
    //   564: invokeinterface setPersistent : (Z)Landroid/content/pm/parsing/ParsingPackage;
    //   569: pop
    //   570: aload_2
    //   571: invokeinterface isProfileableByShell : ()Z
    //   576: istore #11
    //   578: iload #11
    //   580: ifne -> 605
    //   583: aload_2
    //   584: invokeinterface isDebuggable : ()Z
    //   589: istore #11
    //   591: iload #11
    //   593: ifeq -> 599
    //   596: goto -> 605
    //   599: iconst_0
    //   600: istore #11
    //   602: goto -> 608
    //   605: iconst_1
    //   606: istore #11
    //   608: aload_2
    //   609: iload #11
    //   611: invokeinterface setProfileableByShell : (Z)Landroid/content/pm/parsing/ParsingPackage;
    //   616: pop
    //   617: aload #8
    //   619: bipush #37
    //   621: invokevirtual hasValueOrEmpty : (I)Z
    //   624: istore #11
    //   626: iload #11
    //   628: ifeq -> 652
    //   631: aload_2
    //   632: aload #8
    //   634: bipush #37
    //   636: iconst_1
    //   637: invokevirtual getBoolean : (IZ)Z
    //   640: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   643: invokeinterface setResizeableActivity : (Ljava/lang/Boolean;)Landroid/content/pm/parsing/ParsingPackage;
    //   648: pop
    //   649: goto -> 677
    //   652: iload #7
    //   654: bipush #24
    //   656: if_icmplt -> 665
    //   659: iconst_1
    //   660: istore #11
    //   662: goto -> 668
    //   665: iconst_0
    //   666: istore #11
    //   668: aload_2
    //   669: iload #11
    //   671: invokeinterface setResizeableActivityViaSdkVersion : (Z)Landroid/content/pm/parsing/ParsingPackage;
    //   676: pop
    //   677: iload #7
    //   679: bipush #8
    //   681: if_icmplt -> 699
    //   684: aload #8
    //   686: bipush #12
    //   688: sipush #1024
    //   691: invokevirtual getNonConfigurationString : (II)Ljava/lang/String;
    //   694: astore #10
    //   696: goto -> 708
    //   699: aload #8
    //   701: bipush #12
    //   703: invokevirtual getNonResourceString : (I)Ljava/lang/String;
    //   706: astore #10
    //   708: aload #6
    //   710: aload #6
    //   712: aload #10
    //   714: aload_1
    //   715: invokestatic buildTaskAffinityName : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/CharSequence;Landroid/content/pm/parsing/result/ParseInput;)Landroid/content/pm/parsing/result/ParseResult;
    //   718: astore #10
    //   720: aload #10
    //   722: invokeinterface isError : ()Z
    //   727: istore #11
    //   729: iload #11
    //   731: ifeq -> 750
    //   734: aload_1
    //   735: aload #10
    //   737: invokeinterface error : (Landroid/content/pm/parsing/result/ParseResult;)Landroid/content/pm/parsing/result/ParseResult;
    //   742: astore_1
    //   743: aload #8
    //   745: invokevirtual recycle : ()V
    //   748: aload_1
    //   749: areturn
    //   750: aload_2
    //   751: aload #10
    //   753: invokeinterface getResult : ()Ljava/lang/Object;
    //   758: checkcast java/lang/String
    //   761: invokeinterface setTaskAffinity : (Ljava/lang/String;)Landroid/content/pm/parsing/ParsingPackage;
    //   766: pop
    //   767: aload #8
    //   769: bipush #48
    //   771: invokevirtual getNonResourceString : (I)Ljava/lang/String;
    //   774: astore #10
    //   776: aload #10
    //   778: ifnull -> 845
    //   781: aload #6
    //   783: aload #10
    //   785: invokestatic buildClassName : (Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   788: astore #10
    //   790: aload #10
    //   792: ifnonnull -> 836
    //   795: new java/lang/StringBuilder
    //   798: astore_2
    //   799: aload_2
    //   800: invokespecial <init> : ()V
    //   803: aload_2
    //   804: ldc_w 'Empty class name in package '
    //   807: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: pop
    //   811: aload_2
    //   812: aload #6
    //   814: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   817: pop
    //   818: aload_1
    //   819: aload_2
    //   820: invokevirtual toString : ()Ljava/lang/String;
    //   823: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   828: astore_1
    //   829: aload #8
    //   831: invokevirtual recycle : ()V
    //   834: aload_1
    //   835: areturn
    //   836: aload_2
    //   837: aload #10
    //   839: invokeinterface setAppComponentFactory : (Ljava/lang/String;)Landroid/content/pm/parsing/ParsingPackage;
    //   844: pop
    //   845: iload #7
    //   847: bipush #8
    //   849: if_icmplt -> 867
    //   852: aload #8
    //   854: bipush #11
    //   856: sipush #1024
    //   859: invokevirtual getNonConfigurationString : (II)Ljava/lang/String;
    //   862: astore #10
    //   864: goto -> 876
    //   867: aload #8
    //   869: bipush #11
    //   871: invokevirtual getNonResourceString : (I)Ljava/lang/String;
    //   874: astore #10
    //   876: aload #6
    //   878: aconst_null
    //   879: aload #10
    //   881: iload #5
    //   883: aload_0
    //   884: getfield mSeparateProcesses : [Ljava/lang/String;
    //   887: aload_1
    //   888: invokestatic buildProcessName : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/CharSequence;I[Ljava/lang/String;Landroid/content/pm/parsing/result/ParseInput;)Landroid/content/pm/parsing/result/ParseResult;
    //   891: astore #10
    //   893: aload #10
    //   895: invokeinterface isError : ()Z
    //   900: istore #11
    //   902: iload #11
    //   904: ifeq -> 923
    //   907: aload_1
    //   908: aload #10
    //   910: invokeinterface error : (Landroid/content/pm/parsing/result/ParseResult;)Landroid/content/pm/parsing/result/ParseResult;
    //   915: astore_1
    //   916: aload #8
    //   918: invokevirtual recycle : ()V
    //   921: aload_1
    //   922: areturn
    //   923: aload #10
    //   925: invokeinterface getResult : ()Ljava/lang/Object;
    //   930: checkcast java/lang/String
    //   933: astore #10
    //   935: aload_2
    //   936: aload #10
    //   938: invokeinterface setProcessName : (Ljava/lang/String;)Landroid/content/pm/parsing/ParsingPackage;
    //   943: pop
    //   944: aload_2
    //   945: invokeinterface isCantSaveState : ()Z
    //   950: istore #11
    //   952: iload #11
    //   954: ifeq -> 989
    //   957: aload #10
    //   959: ifnull -> 989
    //   962: aload #10
    //   964: aload #6
    //   966: invokevirtual equals : (Ljava/lang/Object;)Z
    //   969: ifne -> 989
    //   972: aload_1
    //   973: ldc_w 'cantSaveState applications can not use custom processes'
    //   976: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   981: astore_1
    //   982: aload #8
    //   984: invokevirtual recycle : ()V
    //   987: aload_1
    //   988: areturn
    //   989: aload_2
    //   990: invokeinterface getClassLoaderName : ()Ljava/lang/String;
    //   995: astore #10
    //   997: aload #10
    //   999: ifnull -> 1051
    //   1002: aload #10
    //   1004: invokestatic isValidClassLoaderName : (Ljava/lang/String;)Z
    //   1007: ifne -> 1051
    //   1010: new java/lang/StringBuilder
    //   1013: astore_2
    //   1014: aload_2
    //   1015: invokespecial <init> : ()V
    //   1018: aload_2
    //   1019: ldc_w 'Invalid class loader name: '
    //   1022: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1025: pop
    //   1026: aload_2
    //   1027: aload #10
    //   1029: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1032: pop
    //   1033: aload_1
    //   1034: aload_2
    //   1035: invokevirtual toString : ()Ljava/lang/String;
    //   1038: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   1043: astore_1
    //   1044: aload #8
    //   1046: invokevirtual recycle : ()V
    //   1049: aload_1
    //   1050: areturn
    //   1051: iconst_m1
    //   1052: istore #13
    //   1054: aload_2
    //   1055: aload #8
    //   1057: bipush #62
    //   1059: iconst_m1
    //   1060: invokevirtual getInt : (II)I
    //   1063: invokeinterface setGwpAsanMode : (I)Landroid/content/pm/parsing/ParsingPackage;
    //   1068: pop
    //   1069: aload #8
    //   1071: invokevirtual recycle : ()V
    //   1074: iconst_0
    //   1075: istore #7
    //   1077: aload #4
    //   1079: invokeinterface getDepth : ()I
    //   1084: istore #14
    //   1086: iconst_0
    //   1087: istore #15
    //   1089: iconst_0
    //   1090: istore #16
    //   1092: aload #4
    //   1094: invokeinterface next : ()I
    //   1099: istore #12
    //   1101: iload #12
    //   1103: iconst_1
    //   1104: if_icmpeq -> 1727
    //   1107: iload #12
    //   1109: iconst_3
    //   1110: if_icmpne -> 1131
    //   1113: aload #4
    //   1115: invokeinterface getDepth : ()I
    //   1120: iload #14
    //   1122: if_icmple -> 1128
    //   1125: goto -> 1131
    //   1128: goto -> 1727
    //   1131: iload #12
    //   1133: iconst_2
    //   1134: if_icmpeq -> 1140
    //   1137: goto -> 1092
    //   1140: aload #4
    //   1142: invokeinterface getName : ()Ljava/lang/String;
    //   1147: astore #10
    //   1149: iconst_0
    //   1150: istore #17
    //   1152: aload #10
    //   1154: invokevirtual hashCode : ()I
    //   1157: lookupswitch default -> 1208, -1655966961 -> 1279, -987494927 -> 1262, -808719889 -> 1245, 790287890 -> 1228, 1984153269 -> 1211
    //   1208: goto -> 1296
    //   1211: aload #10
    //   1213: ldc_w 'service'
    //   1216: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1219: ifeq -> 1208
    //   1222: iconst_2
    //   1223: istore #12
    //   1225: goto -> 1300
    //   1228: aload #10
    //   1230: ldc_w 'activity-alias'
    //   1233: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1236: ifeq -> 1208
    //   1239: iconst_4
    //   1240: istore #12
    //   1242: goto -> 1300
    //   1245: aload #10
    //   1247: ldc_w 'receiver'
    //   1250: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1253: ifeq -> 1208
    //   1256: iconst_1
    //   1257: istore #12
    //   1259: goto -> 1300
    //   1262: aload #10
    //   1264: ldc_w 'provider'
    //   1267: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1270: ifeq -> 1208
    //   1273: iconst_3
    //   1274: istore #12
    //   1276: goto -> 1300
    //   1279: aload #10
    //   1281: ldc_w 'activity'
    //   1284: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1287: ifeq -> 1208
    //   1290: iconst_0
    //   1291: istore #12
    //   1293: goto -> 1300
    //   1296: iload #13
    //   1298: istore #12
    //   1300: iload #12
    //   1302: ifeq -> 1563
    //   1305: iload #12
    //   1307: iconst_1
    //   1308: if_icmpeq -> 1560
    //   1311: iload #12
    //   1313: iconst_2
    //   1314: if_icmpeq -> 1475
    //   1317: iload #12
    //   1319: iconst_3
    //   1320: if_icmpeq -> 1426
    //   1323: iload #12
    //   1325: iconst_4
    //   1326: if_icmpeq -> 1347
    //   1329: aload_0
    //   1330: aload_1
    //   1331: aload #10
    //   1333: aload_2
    //   1334: aload_3
    //   1335: aload #4
    //   1337: iload #5
    //   1339: invokespecial parseBaseAppChildTag : (Landroid/content/pm/parsing/result/ParseInput;Ljava/lang/String;Landroid/content/pm/parsing/ParsingPackage;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;I)Landroid/content/pm/parsing/result/ParseResult;
    //   1342: astore #10
    //   1344: goto -> 1705
    //   1347: aload_2
    //   1348: aload_3
    //   1349: aload #4
    //   1351: getstatic android/content/pm/PackageParser.sUseRoundIcon : Z
    //   1354: aload_1
    //   1355: invokestatic parseActivityAlias : (Landroid/content/pm/parsing/ParsingPackage;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;ZLandroid/content/pm/parsing/result/ParseInput;)Landroid/content/pm/parsing/result/ParseResult;
    //   1358: astore #10
    //   1360: iload #7
    //   1362: istore #12
    //   1364: aload #10
    //   1366: invokeinterface isSuccess : ()Z
    //   1371: ifeq -> 1419
    //   1374: aload #10
    //   1376: invokeinterface getResult : ()Ljava/lang/Object;
    //   1381: checkcast android/content/pm/parsing/component/ParsedActivity
    //   1384: astore #8
    //   1386: aload #8
    //   1388: invokevirtual getOrder : ()I
    //   1391: ifeq -> 1400
    //   1394: iconst_1
    //   1395: istore #12
    //   1397: goto -> 1403
    //   1400: iconst_0
    //   1401: istore #12
    //   1403: iload #7
    //   1405: iload #12
    //   1407: ior
    //   1408: istore #12
    //   1410: aload_2
    //   1411: aload #8
    //   1413: invokeinterface addActivity : (Landroid/content/pm/parsing/component/ParsedActivity;)Landroid/content/pm/parsing/ParsingPackage;
    //   1418: pop
    //   1419: iload #12
    //   1421: istore #7
    //   1423: goto -> 1705
    //   1426: aload_0
    //   1427: getfield mSeparateProcesses : [Ljava/lang/String;
    //   1430: aload_2
    //   1431: aload_3
    //   1432: aload #4
    //   1434: iload #5
    //   1436: getstatic android/content/pm/PackageParser.sUseRoundIcon : Z
    //   1439: aload_1
    //   1440: invokestatic parseProvider : ([Ljava/lang/String;Landroid/content/pm/parsing/ParsingPackage;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;IZLandroid/content/pm/parsing/result/ParseInput;)Landroid/content/pm/parsing/result/ParseResult;
    //   1443: astore #10
    //   1445: aload #10
    //   1447: invokeinterface isSuccess : ()Z
    //   1452: ifeq -> 1472
    //   1455: aload_2
    //   1456: aload #10
    //   1458: invokeinterface getResult : ()Ljava/lang/Object;
    //   1463: checkcast android/content/pm/parsing/component/ParsedProvider
    //   1466: invokeinterface addProvider : (Landroid/content/pm/parsing/component/ParsedProvider;)Landroid/content/pm/parsing/ParsingPackage;
    //   1471: pop
    //   1472: goto -> 1705
    //   1475: aload_0
    //   1476: getfield mSeparateProcesses : [Ljava/lang/String;
    //   1479: aload_2
    //   1480: aload_3
    //   1481: aload #4
    //   1483: iload #5
    //   1485: getstatic android/content/pm/PackageParser.sUseRoundIcon : Z
    //   1488: aload_1
    //   1489: invokestatic parseService : ([Ljava/lang/String;Landroid/content/pm/parsing/ParsingPackage;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;IZLandroid/content/pm/parsing/result/ParseInput;)Landroid/content/pm/parsing/result/ParseResult;
    //   1492: astore #10
    //   1494: iload #16
    //   1496: istore #12
    //   1498: aload #10
    //   1500: invokeinterface isSuccess : ()Z
    //   1505: ifeq -> 1553
    //   1508: aload #10
    //   1510: invokeinterface getResult : ()Ljava/lang/Object;
    //   1515: checkcast android/content/pm/parsing/component/ParsedService
    //   1518: astore #8
    //   1520: aload #8
    //   1522: invokevirtual getOrder : ()I
    //   1525: ifeq -> 1534
    //   1528: iconst_1
    //   1529: istore #12
    //   1531: goto -> 1537
    //   1534: iconst_0
    //   1535: istore #12
    //   1537: iload #16
    //   1539: iload #12
    //   1541: ior
    //   1542: istore #12
    //   1544: aload_2
    //   1545: aload #8
    //   1547: invokeinterface addService : (Landroid/content/pm/parsing/component/ParsedService;)Landroid/content/pm/parsing/ParsingPackage;
    //   1552: pop
    //   1553: iload #12
    //   1555: istore #16
    //   1557: goto -> 1705
    //   1560: goto -> 1566
    //   1563: iconst_1
    //   1564: istore #17
    //   1566: aload_0
    //   1567: getfield mSeparateProcesses : [Ljava/lang/String;
    //   1570: aload_2
    //   1571: aload_3
    //   1572: aload #4
    //   1574: iload #5
    //   1576: getstatic android/content/pm/PackageParser.sUseRoundIcon : Z
    //   1579: aload_1
    //   1580: invokestatic parseActivityOrReceiver : ([Ljava/lang/String;Landroid/content/pm/parsing/ParsingPackage;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;IZLandroid/content/pm/parsing/result/ParseInput;)Landroid/content/pm/parsing/result/ParseResult;
    //   1583: astore #10
    //   1585: iload #7
    //   1587: istore #12
    //   1589: iload #15
    //   1591: istore #18
    //   1593: aload #10
    //   1595: invokeinterface isSuccess : ()Z
    //   1600: ifeq -> 1697
    //   1603: aload #10
    //   1605: invokeinterface getResult : ()Ljava/lang/Object;
    //   1610: checkcast android/content/pm/parsing/component/ParsedActivity
    //   1613: astore #8
    //   1615: iload #17
    //   1617: ifeq -> 1660
    //   1620: aload #8
    //   1622: invokevirtual getOrder : ()I
    //   1625: ifeq -> 1634
    //   1628: iconst_1
    //   1629: istore #12
    //   1631: goto -> 1637
    //   1634: iconst_0
    //   1635: istore #12
    //   1637: iload #7
    //   1639: iload #12
    //   1641: ior
    //   1642: istore #12
    //   1644: aload_2
    //   1645: aload #8
    //   1647: invokeinterface addActivity : (Landroid/content/pm/parsing/component/ParsedActivity;)Landroid/content/pm/parsing/ParsingPackage;
    //   1652: pop
    //   1653: iload #15
    //   1655: istore #18
    //   1657: goto -> 1697
    //   1660: aload #8
    //   1662: invokevirtual getOrder : ()I
    //   1665: ifeq -> 1674
    //   1668: iconst_1
    //   1669: istore #12
    //   1671: goto -> 1677
    //   1674: iconst_0
    //   1675: istore #12
    //   1677: iload #15
    //   1679: iload #12
    //   1681: ior
    //   1682: istore #18
    //   1684: aload_2
    //   1685: aload #8
    //   1687: invokeinterface addReceiver : (Landroid/content/pm/parsing/component/ParsedActivity;)Landroid/content/pm/parsing/ParsingPackage;
    //   1692: pop
    //   1693: iload #7
    //   1695: istore #12
    //   1697: iload #18
    //   1699: istore #15
    //   1701: iload #12
    //   1703: istore #7
    //   1705: aload #10
    //   1707: invokeinterface isError : ()Z
    //   1712: ifeq -> 1724
    //   1715: aload_1
    //   1716: aload #10
    //   1718: invokeinterface error : (Landroid/content/pm/parsing/result/ParseResult;)Landroid/content/pm/parsing/result/ParseResult;
    //   1723: areturn
    //   1724: goto -> 1092
    //   1727: aload_2
    //   1728: invokeinterface getStaticSharedLibName : ()Ljava/lang/String;
    //   1733: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   1736: ifeq -> 1778
    //   1739: aload_1
    //   1740: aload_2
    //   1741: invokestatic generateAppDetailsHiddenActivity : (Landroid/content/pm/parsing/result/ParseInput;Landroid/content/pm/parsing/ParsingPackage;)Landroid/content/pm/parsing/result/ParseResult;
    //   1744: astore_3
    //   1745: aload_3
    //   1746: invokeinterface isError : ()Z
    //   1751: ifeq -> 1762
    //   1754: aload_1
    //   1755: aload_3
    //   1756: invokeinterface error : (Landroid/content/pm/parsing/result/ParseResult;)Landroid/content/pm/parsing/result/ParseResult;
    //   1761: areturn
    //   1762: aload_2
    //   1763: aload_3
    //   1764: invokeinterface getResult : ()Ljava/lang/Object;
    //   1769: checkcast android/content/pm/parsing/component/ParsedActivity
    //   1772: invokeinterface addActivity : (Landroid/content/pm/parsing/component/ParsedActivity;)Landroid/content/pm/parsing/ParsingPackage;
    //   1777: pop
    //   1778: iload #7
    //   1780: ifeq -> 1790
    //   1783: aload_2
    //   1784: invokeinterface sortActivities : ()Landroid/content/pm/parsing/ParsingPackage;
    //   1789: pop
    //   1790: iload #15
    //   1792: ifeq -> 1802
    //   1795: aload_2
    //   1796: invokeinterface sortReceivers : ()Landroid/content/pm/parsing/ParsingPackage;
    //   1801: pop
    //   1802: iload #16
    //   1804: ifeq -> 1814
    //   1807: aload_2
    //   1808: invokeinterface sortServices : ()Landroid/content/pm/parsing/ParsingPackage;
    //   1813: pop
    //   1814: aload_2
    //   1815: invokestatic setMaxAspectRatio : (Landroid/content/pm/parsing/ParsingPackage;)V
    //   1818: aload_0
    //   1819: aload_2
    //   1820: invokespecial setMinAspectRatio : (Landroid/content/pm/parsing/ParsingPackage;)V
    //   1823: aload_0
    //   1824: aload_2
    //   1825: invokespecial setSupportsSizeChanges : (Landroid/content/pm/parsing/ParsingPackage;)V
    //   1828: aload_2
    //   1829: aload_2
    //   1830: invokestatic hasDomainURLs : (Landroid/content/pm/parsing/ParsingPackage;)Z
    //   1833: invokeinterface setHasDomainUrls : (Z)Landroid/content/pm/parsing/ParsingPackage;
    //   1838: pop
    //   1839: aload_1
    //   1840: aload_2
    //   1841: invokeinterface success : (Ljava/lang/Object;)Landroid/content/pm/parsing/result/ParseResult;
    //   1846: areturn
    //   1847: astore_1
    //   1848: aload #8
    //   1850: invokevirtual recycle : ()V
    //   1853: aload_1
    //   1854: athrow
    // Exception table:
    //   from	to	target	type
    //   32	42	49	finally
    //   53	62	1847	finally
    //   67	105	49	finally
    //   117	151	49	finally
    //   158	167	49	finally
    //   167	175	1847	finally
    //   180	212	49	finally
    //   212	230	1847	finally
    //   235	244	49	finally
    //   249	283	49	finally
    //   290	299	49	finally
    //   299	307	1847	finally
    //   312	324	49	finally
    //   329	338	49	finally
    //   343	377	49	finally
    //   384	445	49	finally
    //   448	457	49	finally
    //   462	469	49	finally
    //   469	485	49	finally
    //   494	503	49	finally
    //   506	516	1847	finally
    //   521	530	49	finally
    //   535	549	49	finally
    //   561	570	49	finally
    //   570	578	1847	finally
    //   583	591	49	finally
    //   608	626	1847	finally
    //   631	649	49	finally
    //   668	677	1847	finally
    //   684	696	49	finally
    //   699	708	1847	finally
    //   708	729	1847	finally
    //   734	743	49	finally
    //   750	776	1847	finally
    //   781	790	49	finally
    //   795	829	49	finally
    //   836	845	49	finally
    //   852	864	49	finally
    //   867	876	1847	finally
    //   876	902	1847	finally
    //   907	916	49	finally
    //   923	952	1847	finally
    //   962	982	49	finally
    //   989	997	1847	finally
    //   1002	1044	49	finally
    //   1054	1069	1847	finally
  }
  
  private ParseResult<ParsingPackage> parseClusterPackage(ParseInput paramParseInput, File paramFile, int paramInt) {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: iconst_0
    //   3: invokestatic parseClusterPackageLite : (Landroid/content/pm/parsing/result/ParseInput;Ljava/io/File;I)Landroid/content/pm/parsing/result/ParseResult;
    //   6: astore #4
    //   8: aload #4
    //   10: invokeinterface isError : ()Z
    //   15: ifeq -> 27
    //   18: aload_1
    //   19: aload #4
    //   21: invokeinterface error : (Landroid/content/pm/parsing/result/ParseResult;)Landroid/content/pm/parsing/result/ParseResult;
    //   26: areturn
    //   27: aload #4
    //   29: invokeinterface getResult : ()Ljava/lang/Object;
    //   34: checkcast android/content/pm/PackageParser$PackageLite
    //   37: astore #4
    //   39: aload_0
    //   40: getfield mOnlyCoreApps : Z
    //   43: ifeq -> 93
    //   46: aload #4
    //   48: getfield coreApp : Z
    //   51: ifne -> 93
    //   54: new java/lang/StringBuilder
    //   57: dup
    //   58: invokespecial <init> : ()V
    //   61: astore #4
    //   63: aload #4
    //   65: ldc_w 'Not a coreApp: '
    //   68: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: pop
    //   72: aload #4
    //   74: aload_2
    //   75: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: aload_1
    //   80: bipush #-123
    //   82: aload #4
    //   84: invokevirtual toString : ()Ljava/lang/String;
    //   87: invokeinterface error : (ILjava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   92: areturn
    //   93: aload #4
    //   95: getfield isolatedSplits : Z
    //   98: ifeq -> 149
    //   101: aload #4
    //   103: getfield splitNames : [Ljava/lang/String;
    //   106: invokestatic isEmpty : ([Ljava/lang/Object;)Z
    //   109: ifne -> 149
    //   112: aload #4
    //   114: invokestatic createDependenciesFromPackage : (Landroid/content/pm/PackageParser$PackageLite;)Landroid/util/SparseArray;
    //   117: astore #5
    //   119: new android/content/pm/split/SplitAssetDependencyLoader
    //   122: dup
    //   123: aload #4
    //   125: aload #5
    //   127: iload_3
    //   128: invokespecial <init> : (Landroid/content/pm/PackageParser$PackageLite;Landroid/util/SparseArray;I)V
    //   131: astore_2
    //   132: goto -> 163
    //   135: astore_2
    //   136: aload_1
    //   137: bipush #-101
    //   139: aload_2
    //   140: invokevirtual getMessage : ()Ljava/lang/String;
    //   143: invokeinterface error : (ILjava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   148: areturn
    //   149: new android/content/pm/split/DefaultSplitAssetLoader
    //   152: dup
    //   153: aload #4
    //   155: iload_3
    //   156: invokespecial <init> : (Landroid/content/pm/PackageParser$PackageLite;I)V
    //   159: astore_2
    //   160: aconst_null
    //   161: astore #5
    //   163: aload #5
    //   165: astore #6
    //   167: aload_2
    //   168: astore #6
    //   170: aload #4
    //   172: astore #7
    //   174: aload #5
    //   176: astore #7
    //   178: aload_2
    //   179: astore #8
    //   181: aload #4
    //   183: astore #7
    //   185: aload_2
    //   186: invokeinterface getBaseAssetManager : ()Landroid/content/res/AssetManager;
    //   191: astore #9
    //   193: aload #5
    //   195: astore #6
    //   197: aload_2
    //   198: astore #6
    //   200: aload #4
    //   202: astore #7
    //   204: aload #5
    //   206: astore #7
    //   208: aload_2
    //   209: astore #8
    //   211: aload #4
    //   213: astore #7
    //   215: new java/io/File
    //   218: astore #10
    //   220: aload #5
    //   222: astore #6
    //   224: aload_2
    //   225: astore #6
    //   227: aload #4
    //   229: astore #7
    //   231: aload #5
    //   233: astore #7
    //   235: aload_2
    //   236: astore #8
    //   238: aload #4
    //   240: astore #7
    //   242: aload #10
    //   244: aload #4
    //   246: getfield baseCodePath : Ljava/lang/String;
    //   249: invokespecial <init> : (Ljava/lang/String;)V
    //   252: aload #5
    //   254: astore #6
    //   256: aload_2
    //   257: astore #6
    //   259: aload #4
    //   261: astore #7
    //   263: aload #5
    //   265: astore #7
    //   267: aload_2
    //   268: astore #8
    //   270: aload #4
    //   272: astore #7
    //   274: aload_0
    //   275: aload_1
    //   276: aload #10
    //   278: aload #4
    //   280: getfield codePath : Ljava/lang/String;
    //   283: aload #9
    //   285: iload_3
    //   286: invokespecial parseBaseApk : (Landroid/content/pm/parsing/result/ParseInput;Ljava/io/File;Ljava/lang/String;Landroid/content/res/AssetManager;I)Landroid/content/pm/parsing/result/ParseResult;
    //   289: astore #10
    //   291: aload #5
    //   293: astore #6
    //   295: aload_2
    //   296: astore #6
    //   298: aload #4
    //   300: astore #7
    //   302: aload #5
    //   304: astore #7
    //   306: aload_2
    //   307: astore #8
    //   309: aload #4
    //   311: astore #7
    //   313: aload #10
    //   315: invokeinterface isError : ()Z
    //   320: istore #11
    //   322: iload #11
    //   324: ifeq -> 360
    //   327: aload_1
    //   328: aload #10
    //   330: invokeinterface error : (Landroid/content/pm/parsing/result/ParseResult;)Landroid/content/pm/parsing/result/ParseResult;
    //   335: astore #5
    //   337: aload_2
    //   338: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   341: aload #5
    //   343: areturn
    //   344: astore_1
    //   345: goto -> 747
    //   348: astore #5
    //   350: aload #4
    //   352: astore #8
    //   354: aload_2
    //   355: astore #4
    //   357: goto -> 678
    //   360: aload #5
    //   362: astore #6
    //   364: aload_2
    //   365: astore #6
    //   367: aload #4
    //   369: astore #7
    //   371: aload #5
    //   373: astore #7
    //   375: aload_2
    //   376: astore #8
    //   378: aload #4
    //   380: astore #7
    //   382: aload #10
    //   384: invokeinterface getResult : ()Ljava/lang/Object;
    //   389: checkcast android/content/pm/parsing/ParsingPackage
    //   392: astore #10
    //   394: aload #5
    //   396: astore #6
    //   398: aload_2
    //   399: astore #6
    //   401: aload #4
    //   403: astore #7
    //   405: aload #5
    //   407: astore #7
    //   409: aload_2
    //   410: astore #8
    //   412: aload #4
    //   414: astore #7
    //   416: aload #4
    //   418: getfield splitNames : [Ljava/lang/String;
    //   421: invokestatic isEmpty : ([Ljava/lang/Object;)Z
    //   424: ifne -> 592
    //   427: aload #5
    //   429: astore #6
    //   431: aload_2
    //   432: astore #6
    //   434: aload #4
    //   436: astore #7
    //   438: aload #5
    //   440: astore #7
    //   442: aload_2
    //   443: astore #8
    //   445: aload #4
    //   447: astore #7
    //   449: aload #10
    //   451: aload #4
    //   453: getfield splitNames : [Ljava/lang/String;
    //   456: aload #4
    //   458: getfield splitCodePaths : [Ljava/lang/String;
    //   461: aload #4
    //   463: getfield splitRevisionCodes : [I
    //   466: aload #5
    //   468: invokeinterface asSplit : ([Ljava/lang/String;[Ljava/lang/String;[ILandroid/util/SparseArray;)Landroid/content/pm/parsing/ParsingPackage;
    //   473: pop
    //   474: aload #5
    //   476: astore #6
    //   478: aload_2
    //   479: astore #6
    //   481: aload #4
    //   483: astore #7
    //   485: aload #5
    //   487: astore #7
    //   489: aload_2
    //   490: astore #8
    //   492: aload #4
    //   494: astore #7
    //   496: aload #4
    //   498: getfield splitNames : [Ljava/lang/String;
    //   501: arraylength
    //   502: istore #12
    //   504: iconst_0
    //   505: istore #13
    //   507: iload #13
    //   509: iload #12
    //   511: if_icmpge -> 582
    //   514: aload #5
    //   516: astore #6
    //   518: aload_2
    //   519: astore #6
    //   521: aload #4
    //   523: astore #7
    //   525: aload #5
    //   527: astore #7
    //   529: aload_2
    //   530: astore #8
    //   532: aload #4
    //   534: astore #7
    //   536: aload_2
    //   537: iload #13
    //   539: invokeinterface getSplitAssetManager : (I)Landroid/content/res/AssetManager;
    //   544: astore #9
    //   546: aload_2
    //   547: astore #6
    //   549: aload #4
    //   551: astore #8
    //   553: aload #6
    //   555: astore #7
    //   557: aload #6
    //   559: astore_2
    //   560: aload_0
    //   561: aload_1
    //   562: aload #10
    //   564: iload #13
    //   566: aload #9
    //   568: iload_3
    //   569: invokespecial parseSplitApk : (Landroid/content/pm/parsing/result/ParseInput;Landroid/content/pm/parsing/ParsingPackage;ILandroid/content/res/AssetManager;I)Landroid/content/pm/parsing/result/ParseResult;
    //   572: pop
    //   573: iinc #13, 1
    //   576: aload #6
    //   578: astore_2
    //   579: goto -> 507
    //   582: aload #4
    //   584: astore #5
    //   586: aload_2
    //   587: astore #4
    //   589: goto -> 599
    //   592: aload #4
    //   594: astore #5
    //   596: aload_2
    //   597: astore #4
    //   599: aload #5
    //   601: astore #8
    //   603: aload #4
    //   605: astore #7
    //   607: aload #4
    //   609: astore_2
    //   610: aload #10
    //   612: aload #5
    //   614: getfield use32bitAbi : Z
    //   617: invokeinterface setUse32BitAbi : (Z)Landroid/content/pm/parsing/ParsingPackage;
    //   622: pop
    //   623: aload #5
    //   625: astore #8
    //   627: aload #4
    //   629: astore #7
    //   631: aload #4
    //   633: astore_2
    //   634: aload_1
    //   635: aload #10
    //   637: invokeinterface success : (Ljava/lang/Object;)Landroid/content/pm/parsing/result/ParseResult;
    //   642: astore #5
    //   644: aload #4
    //   646: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   649: aload #5
    //   651: areturn
    //   652: astore #5
    //   654: aload #7
    //   656: astore #4
    //   658: goto -> 678
    //   661: astore_1
    //   662: aload #6
    //   664: astore_2
    //   665: goto -> 747
    //   668: astore #5
    //   670: aload #8
    //   672: astore #4
    //   674: aload #7
    //   676: astore #8
    //   678: aload #4
    //   680: astore_2
    //   681: new java/lang/StringBuilder
    //   684: astore #6
    //   686: aload #4
    //   688: astore_2
    //   689: aload #6
    //   691: invokespecial <init> : ()V
    //   694: aload #4
    //   696: astore_2
    //   697: aload #6
    //   699: ldc_w 'Failed to load assets: '
    //   702: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   705: pop
    //   706: aload #4
    //   708: astore_2
    //   709: aload #6
    //   711: aload #8
    //   713: getfield baseCodePath : Ljava/lang/String;
    //   716: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   719: pop
    //   720: aload #4
    //   722: astore_2
    //   723: aload_1
    //   724: bipush #-102
    //   726: aload #6
    //   728: invokevirtual toString : ()Ljava/lang/String;
    //   731: aload #5
    //   733: invokeinterface error : (ILjava/lang/String;Ljava/lang/Exception;)Landroid/content/pm/parsing/result/ParseResult;
    //   738: astore_1
    //   739: aload #4
    //   741: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   744: aload_1
    //   745: areturn
    //   746: astore_1
    //   747: aload_2
    //   748: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   751: aload_1
    //   752: athrow
    // Exception table:
    //   from	to	target	type
    //   112	132	135	android/content/pm/split/SplitDependencyLoader$IllegalDependencyException
    //   185	193	668	android/content/pm/PackageParser$PackageParserException
    //   185	193	661	finally
    //   215	220	668	android/content/pm/PackageParser$PackageParserException
    //   215	220	661	finally
    //   242	252	668	android/content/pm/PackageParser$PackageParserException
    //   242	252	661	finally
    //   274	291	668	android/content/pm/PackageParser$PackageParserException
    //   274	291	661	finally
    //   313	322	668	android/content/pm/PackageParser$PackageParserException
    //   313	322	661	finally
    //   327	337	348	android/content/pm/PackageParser$PackageParserException
    //   327	337	344	finally
    //   382	394	668	android/content/pm/PackageParser$PackageParserException
    //   382	394	661	finally
    //   416	427	668	android/content/pm/PackageParser$PackageParserException
    //   416	427	661	finally
    //   449	474	668	android/content/pm/PackageParser$PackageParserException
    //   449	474	661	finally
    //   496	504	668	android/content/pm/PackageParser$PackageParserException
    //   496	504	661	finally
    //   536	546	668	android/content/pm/PackageParser$PackageParserException
    //   536	546	661	finally
    //   560	573	652	android/content/pm/PackageParser$PackageParserException
    //   560	573	746	finally
    //   610	623	652	android/content/pm/PackageParser$PackageParserException
    //   610	623	746	finally
    //   634	644	652	android/content/pm/PackageParser$PackageParserException
    //   634	644	746	finally
    //   681	686	746	finally
    //   689	694	746	finally
    //   697	706	746	finally
    //   709	720	746	finally
    //   723	739	746	finally
  }
  
  public static ParseResult<ParsingPackage> parseDefault(ParseInput paramParseInput, File paramFile, int paramInt, boolean paramBoolean) {
    ParsingPackageUtils parsingPackageUtils = new ParsingPackageUtils(false, null, null, new Callback() {
          public boolean hasFeature(String param1String) {
            return false;
          }
          
          public ParsingPackage startParsingPackage(String param1String1, String param1String2, String param1String3, TypedArray param1TypedArray, boolean param1Boolean) {
            return new ParsingPackageImpl(param1String1, param1String2, param1String3, param1TypedArray);
          }
        });
    try {
      ParseResult<ParsingPackage> parseResult = parsingPackageUtils.parsePackage(paramParseInput, paramFile, paramInt);
      boolean bool = parseResult.isError();
      if (bool)
        return parseResult; 
      try {
        ParsingPackage parsingPackage = (ParsingPackage)parseResult.getResult();
        if (paramBoolean)
          parsingPackage.setSigningDetails(getSigningDetails(parsingPackage, false)); 
        return paramParseInput.success(parsingPackage);
      } catch (android.content.pm.PackageParser.PackageParserException packageParserException) {
        return paramParseInput.error(-102, "Error collecting package certificates", (Exception)packageParserException);
      } 
    } catch (android.content.pm.PackageParser.PackageParserException packageParserException) {
      return paramParseInput.error(-102, "Error parsing package", (Exception)packageParserException);
    } 
  }
  
  public static ParseResult<ParsingPackage> parseDefaultOneTime(File paramFile, int paramInt, boolean paramBoolean) {
    return parseDefault(ParseTypeImpl.forDefaultParsing().reset(), paramFile, paramInt, paramBoolean);
  }
  
  private static ParseResult<SparseIntArray> parseExtensionSdk(ParseInput paramParseInput, Resources paramResources, XmlResourceParser paramXmlResourceParser, SparseIntArray paramSparseIntArray) {
    StringBuilder stringBuilder;
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestExtensionSdk);
    try {
      int i = typedArray.getInt(0, -1);
      int j = typedArray.getInt(1, -1);
      typedArray.recycle();
      if (i < 0)
        return paramParseInput.error(-108, "<extension-sdk> must specify an sdkVersion >= 0"); 
      if (j < 0)
        return paramParseInput.error(-108, "<extension-sdk> must specify minExtensionVersion >= 0"); 
    } finally {
      stringBuilder.recycle();
    } 
  }
  
  private static ParseResult<ParsingPackage> parseFeatureGroup(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws IOException, XmlPullParserException {
    FeatureGroupInfo featureGroupInfo = new FeatureGroupInfo();
    ArrayList arrayList = null;
    int i = paramXmlResourceParser.getDepth();
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        FeatureInfo featureInfo;
        if (j != 2)
          continue; 
        String str = paramXmlResourceParser.getName();
        if (str.equals("uses-feature")) {
          featureInfo = parseFeatureInfo(paramResources, (AttributeSet)paramXmlResourceParser);
          featureInfo.flags = 0x1 | featureInfo.flags;
          arrayList = ArrayUtils.add(arrayList, featureInfo);
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown element under <feature-group>: ");
        stringBuilder.append((String)featureInfo);
        stringBuilder.append(" at ");
        stringBuilder.append(paramParsingPackage.getBaseCodePath());
        stringBuilder.append(" ");
        stringBuilder.append(paramXmlResourceParser.getPositionDescription());
        Slog.w("PackageParsing", stringBuilder.toString());
        continue;
      } 
      break;
    } 
    if (arrayList != null) {
      featureGroupInfo.features = new FeatureInfo[arrayList.size()];
      featureGroupInfo.features = (FeatureInfo[])arrayList.toArray((Object[])featureGroupInfo.features);
    } 
    paramParsingPackage.addFeatureGroup(featureGroupInfo);
    return paramParseInput.success(paramParsingPackage);
  }
  
  private static FeatureInfo parseFeatureInfo(Resources paramResources, AttributeSet paramAttributeSet) {
    FeatureInfo featureInfo = new FeatureInfo();
    TypedArray typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.AndroidManifestUsesFeature);
    try {
      featureInfo.name = typedArray.getNonResourceString(0);
      featureInfo.version = typedArray.getInt(3, 0);
      if (featureInfo.name == null)
        featureInfo.reqGlEsVersion = typedArray.getInt(1, 0); 
      if (typedArray.getBoolean(2, true))
        featureInfo.flags |= 0x1; 
      return featureInfo;
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsingPackage> parseInstrumentation(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws XmlPullParserException, IOException {
    ParseResult parseResult = ParsedInstrumentationUtils.parseInstrumentation(paramParsingPackage, paramResources, paramXmlResourceParser, PackageParser.sUseRoundIcon, paramParseInput);
    return parseResult.isError() ? paramParseInput.error(parseResult) : paramParseInput.success(paramParsingPackage.addInstrumentation((ParsedInstrumentation)parseResult.getResult()));
  }
  
  private static ParseResult<ParsingPackage> parseKeySets(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws XmlPullParserException, IOException {
    int i = paramXmlResourceParser.getDepth();
    ArrayMap arrayMap1 = new ArrayMap();
    ArraySet arraySet1 = new ArraySet();
    ArrayMap arrayMap2 = new ArrayMap();
    ArraySet arraySet2 = new ArraySet();
    String str2 = null;
    byte b = -1;
    while (true) {
      while (true) {
        int j = paramXmlResourceParser.next();
        break;
      } 
      if (SYNTHETIC_LOCAL_VARIABLE_12.isError())
        return paramParseInput.error((ParseResult)SYNTHETIC_LOCAL_VARIABLE_12); 
    } 
    String str1 = paramParsingPackage.getPackageName();
    if (arrayMap1.keySet().removeAll(arrayMap2.keySet())) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Package");
      stringBuilder.append(str1);
      stringBuilder.append(" AndroidManifest.xml 'key-set' and 'public-key' names must be distinct.");
      return paramParseInput.error(stringBuilder.toString());
    } 
    for (Map.Entry entry : arrayMap2.entrySet()) {
      StringBuilder stringBuilder1;
      str2 = (String)entry.getKey();
      if (((ArraySet)entry.getValue()).size() == 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Package");
        stringBuilder1.append(str1);
        stringBuilder1.append(" AndroidManifest.xml 'key-set' ");
        stringBuilder1.append(str2);
        stringBuilder1.append(" has no valid associated 'public-key'. Not including in package's defined key-sets.");
        Slog.w("PackageParsing", stringBuilder1.toString());
        continue;
      } 
      if (arraySet2.contains(str2)) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Package");
        stringBuilder1.append(str1);
        stringBuilder1.append(" AndroidManifest.xml 'key-set' ");
        stringBuilder1.append(str2);
        stringBuilder1.append(" contained improper 'public-key' tags. Not including in package's defined key-sets.");
        Slog.w("PackageParsing", stringBuilder1.toString());
        continue;
      } 
      Iterator<String> iterator = ((ArraySet)stringBuilder1.getValue()).iterator();
      while (iterator.hasNext())
        stringBuilder.addKeySet(str2, (PublicKey)arrayMap1.get(iterator.next())); 
    } 
    if (stringBuilder.getKeySetMapping().keySet().containsAll((Collection<?>)arraySet1)) {
      stringBuilder.setUpgradeKeySets((Set<String>)arraySet1);
      return paramParseInput.success(stringBuilder);
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Package");
    stringBuilder.append(str1);
    stringBuilder.append(" AndroidManifest.xml does not define all 'upgrade-key-set's .");
    return paramParseInput.error(stringBuilder.toString());
  }
  
  private static ParseResult<ParsingPackage> parseLibrary(ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, ParseInput paramParseInput) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestLibrary);
    try {
      String str = typedArray.getNonResourceString(0);
      if (str != null) {
        str = str.intern();
        if (!ArrayUtils.contains(paramParsingPackage.getLibraryNames(), str))
          paramParsingPackage.addLibraryName(str); 
      } 
      return paramParseInput.success(paramParsingPackage);
    } finally {
      typedArray.recycle();
    } 
  }
  
  public static ParseResult<Bundle> parseMetaData(ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, Bundle paramBundle, ParseInput paramParseInput) {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: getstatic com/android/internal/R$styleable.AndroidManifestMetaData : [I
    //   5: invokevirtual obtainAttributes : (Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
    //   8: astore #5
    //   10: aload_3
    //   11: astore_1
    //   12: aload_3
    //   13: ifnonnull -> 24
    //   16: new android/os/Bundle
    //   19: astore_1
    //   20: aload_1
    //   21: invokespecial <init> : ()V
    //   24: iconst_0
    //   25: istore #6
    //   27: iconst_0
    //   28: iconst_0
    //   29: aload #5
    //   31: invokestatic nonConfigString : (IILandroid/content/res/TypedArray;)Ljava/lang/String;
    //   34: invokestatic safeIntern : (Ljava/lang/String;)Ljava/lang/String;
    //   37: astore_3
    //   38: aload_3
    //   39: ifnonnull -> 60
    //   42: aload #4
    //   44: ldc_w '<meta-data> requires an android:name attribute'
    //   47: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   52: astore_0
    //   53: aload #5
    //   55: invokevirtual recycle : ()V
    //   58: aload_0
    //   59: areturn
    //   60: aload #5
    //   62: iconst_2
    //   63: invokevirtual peekValue : (I)Landroid/util/TypedValue;
    //   66: astore #7
    //   68: aload #7
    //   70: ifnull -> 94
    //   73: aload #7
    //   75: getfield resourceId : I
    //   78: ifeq -> 94
    //   81: aload_1
    //   82: aload_3
    //   83: aload #7
    //   85: getfield resourceId : I
    //   88: invokevirtual putInt : (Ljava/lang/String;I)V
    //   91: goto -> 308
    //   94: aload #5
    //   96: iconst_1
    //   97: invokevirtual peekValue : (I)Landroid/util/TypedValue;
    //   100: astore #7
    //   102: aload #7
    //   104: ifnull -> 324
    //   107: aload #7
    //   109: getfield type : I
    //   112: iconst_3
    //   113: if_icmpne -> 147
    //   116: aload #7
    //   118: invokevirtual coerceToString : ()Ljava/lang/CharSequence;
    //   121: astore_0
    //   122: aload_0
    //   123: ifnull -> 136
    //   126: aload_0
    //   127: invokeinterface toString : ()Ljava/lang/String;
    //   132: astore_0
    //   133: goto -> 138
    //   136: aconst_null
    //   137: astore_0
    //   138: aload_1
    //   139: aload_3
    //   140: aload_0
    //   141: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   144: goto -> 308
    //   147: aload #7
    //   149: getfield type : I
    //   152: bipush #18
    //   154: if_icmpne -> 178
    //   157: aload #7
    //   159: getfield data : I
    //   162: ifeq -> 168
    //   165: iconst_1
    //   166: istore #6
    //   168: aload_1
    //   169: aload_3
    //   170: iload #6
    //   172: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   175: goto -> 308
    //   178: aload #7
    //   180: getfield type : I
    //   183: bipush #16
    //   185: if_icmplt -> 211
    //   188: aload #7
    //   190: getfield type : I
    //   193: bipush #31
    //   195: if_icmpgt -> 211
    //   198: aload_1
    //   199: aload_3
    //   200: aload #7
    //   202: getfield data : I
    //   205: invokevirtual putInt : (Ljava/lang/String;I)V
    //   208: goto -> 308
    //   211: aload #7
    //   213: getfield type : I
    //   216: iconst_4
    //   217: if_icmpne -> 233
    //   220: aload_1
    //   221: aload_3
    //   222: aload #7
    //   224: invokevirtual getFloat : ()F
    //   227: invokevirtual putFloat : (Ljava/lang/String;F)V
    //   230: goto -> 308
    //   233: new java/lang/StringBuilder
    //   236: astore_3
    //   237: aload_3
    //   238: invokespecial <init> : ()V
    //   241: aload_3
    //   242: ldc_w '<meta-data> only supports string, integer, float, color, boolean, and resource reference types: '
    //   245: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: pop
    //   249: aload_3
    //   250: aload_2
    //   251: invokeinterface getName : ()Ljava/lang/String;
    //   256: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: pop
    //   260: aload_3
    //   261: ldc_w ' at '
    //   264: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: pop
    //   268: aload_3
    //   269: aload_0
    //   270: invokeinterface getBaseCodePath : ()Ljava/lang/String;
    //   275: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: pop
    //   279: aload_3
    //   280: ldc_w ' '
    //   283: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: pop
    //   287: aload_3
    //   288: aload_2
    //   289: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: pop
    //   298: ldc 'PackageParsing'
    //   300: aload_3
    //   301: invokevirtual toString : ()Ljava/lang/String;
    //   304: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   307: pop
    //   308: aload #4
    //   310: aload_1
    //   311: invokeinterface success : (Ljava/lang/Object;)Landroid/content/pm/parsing/result/ParseResult;
    //   316: astore_0
    //   317: aload #5
    //   319: invokevirtual recycle : ()V
    //   322: aload_0
    //   323: areturn
    //   324: aload #4
    //   326: ldc_w '<meta-data> requires an android:value or android:resource attribute'
    //   329: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   334: astore_0
    //   335: aload #5
    //   337: invokevirtual recycle : ()V
    //   340: aload_0
    //   341: areturn
    //   342: astore_0
    //   343: aload #5
    //   345: invokevirtual recycle : ()V
    //   348: aload_0
    //   349: athrow
    // Exception table:
    //   from	to	target	type
    //   16	24	342	finally
    //   27	38	342	finally
    //   42	53	342	finally
    //   60	68	342	finally
    //   73	91	342	finally
    //   94	102	342	finally
    //   107	122	342	finally
    //   126	133	342	finally
    //   138	144	342	finally
    //   147	157	342	finally
    //   157	165	342	finally
    //   168	175	342	finally
    //   178	208	342	finally
    //   211	230	342	finally
    //   233	308	342	finally
    //   308	317	342	finally
    //   324	335	342	finally
  }
  
  private ParseResult<ParsingPackage> parseMonolithicPackage(ParseInput paramParseInput, File paramFile, int paramInt) throws PackageParser.PackageParserException {
    ParseResult<PackageParser.PackageLite> parseResult = ApkLiteParseUtils.parseMonolithicPackageLite(paramParseInput, paramFile, paramInt);
    if (parseResult.isError())
      return paramParseInput.error(parseResult); 
    PackageParser.PackageLite packageLite = (PackageParser.PackageLite)parseResult.getResult();
    if (this.mOnlyCoreApps && !packageLite.coreApp) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Not a coreApp: ");
      stringBuilder.append(paramFile);
      return paramParseInput.error(-123, stringBuilder.toString());
    } 
    DefaultSplitAssetLoader defaultSplitAssetLoader = new DefaultSplitAssetLoader(packageLite, paramInt);
    try {
      ParseResult<ParsingPackage> parseResult2 = parseBaseApk(paramParseInput, paramFile, paramFile.getCanonicalPath(), defaultSplitAssetLoader.getBaseAssetManager(), paramInt);
      if (parseResult2.isError()) {
        parseResult1 = paramParseInput.error(parseResult2);
        IoUtils.closeQuietly((AutoCloseable)defaultSplitAssetLoader);
        return parseResult1;
      } 
      ParseResult<ParsingPackage> parseResult1 = paramParseInput.success(((ParsingPackage)parseResult2.getResult()).setUse32BitAbi(((PackageParser.PackageLite)parseResult1).use32bitAbi));
      IoUtils.closeQuietly((AutoCloseable)defaultSplitAssetLoader);
      return parseResult1;
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Failed to get path: ");
      stringBuilder.append(paramFile);
      ParseResult<ParsingPackage> parseResult1 = paramParseInput.error(-102, stringBuilder.toString(), iOException);
      IoUtils.closeQuietly((AutoCloseable)defaultSplitAssetLoader);
      return parseResult1;
    } finally {}
    IoUtils.closeQuietly((AutoCloseable)defaultSplitAssetLoader);
    throw paramParseInput;
  }
  
  private static ParseResult<ParsingPackage> parseOriginalPackage(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestOriginalPackage);
    try {
      String str = typedArray.getNonConfigurationString(0, 0);
      if (!paramParsingPackage.getPackageName().equals(str)) {
        if (paramParsingPackage.getOriginalPackages().isEmpty())
          paramParsingPackage.setRealPackage(paramParsingPackage.getPackageName()); 
        paramParsingPackage.addOriginalPackage(str);
      } 
      return paramParseInput.success(paramParsingPackage);
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsingPackage> parseOverlay(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestResourceOverlay);
    try {
      String str1;
      String str3 = typedArray.getString(1);
      int i = anInt(0, 0, typedArray);
      if (str3 == null) {
        parseResult = paramParseInput.error("<overlay> does not specify a target package");
        return parseResult;
      } 
      if (i < 0 || i > 9999) {
        parseResult = parseResult.error("<overlay> priority must be between 0 and 9999");
        return parseResult;
      } 
      String str4 = typedArray.getString(5);
      String str2 = typedArray.getString(6);
      if (!PackageParser.checkRequiredSystemProperties(str4, str2)) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Skipping target and overlay pair ");
        stringBuilder.append(str3);
        stringBuilder.append(" and ");
        stringBuilder.append(paramParsingPackage.getBaseCodePath());
        stringBuilder.append(": overlay ignored due to required system property: ");
        stringBuilder.append(str4);
        stringBuilder.append(" with value: ");
        stringBuilder.append(str2);
        str1 = stringBuilder.toString();
        Slog.i("PackageParsing", str1);
        parseResult = parseResult.skip(str1);
        return parseResult;
      } 
      ParseResult<ParsingPackage> parseResult = parseResult.success(str1.setOverlay(true).setOverlayTarget(str3).setOverlayPriority(i).setOverlayTargetName(typedArray.getString(3)).setOverlayCategory(typedArray.getString(2)).setOverlayIsStatic(bool(false, 4, typedArray)));
      return parseResult;
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsingPackage> parsePermission(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws XmlPullParserException, IOException {
    ParseResult parseResult = ParsedPermissionUtils.parsePermission(paramParsingPackage, paramResources, paramXmlResourceParser, PackageParser.sUseRoundIcon, paramParseInput);
    return parseResult.isError() ? paramParseInput.error(parseResult) : paramParseInput.success(paramParsingPackage.addPermission((ParsedPermission)parseResult.getResult()));
  }
  
  private static ParseResult<ParsingPackage> parsePermissionGroup(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws XmlPullParserException, IOException {
    ParseResult parseResult = ParsedPermissionUtils.parsePermissionGroup(paramParsingPackage, paramResources, paramXmlResourceParser, PackageParser.sUseRoundIcon, paramParseInput);
    return parseResult.isError() ? paramParseInput.error(parseResult) : paramParseInput.success(paramParsingPackage.addPermissionGroup((ParsedPermissionGroup)parseResult.getResult()));
  }
  
  private static ParseResult<ParsingPackage> parsePermissionTree(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws XmlPullParserException, IOException {
    ParseResult parseResult = ParsedPermissionUtils.parsePermissionTree(paramParsingPackage, paramResources, paramXmlResourceParser, PackageParser.sUseRoundIcon, paramParseInput);
    return parseResult.isError() ? paramParseInput.error(parseResult) : paramParseInput.success(paramParsingPackage.addPermission((ParsedPermission)parseResult.getResult()));
  }
  
  private static ParseResult<ParsingPackage> parseProcesses(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, String[] paramArrayOfString, int paramInt) throws IOException, XmlPullParserException {
    ParseResult parseResult = ParsedProcessUtils.parseProcesses(paramArrayOfString, paramParsingPackage, paramResources, paramXmlResourceParser, paramInt, paramParseInput);
    return parseResult.isError() ? paramParseInput.error(parseResult) : paramParseInput.success(paramParsingPackage.setProcesses((Map<String, ParsedProcess>)parseResult.getResult()));
  }
  
  private static ParseResult<ParsingPackage> parseProfileable(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestProfileable);
    try {
      boolean bool = paramParsingPackage.isProfileableByShell();
      boolean bool1 = false;
      if (bool || bool(false, 0, typedArray))
        bool1 = true; 
      return paramParseInput.success(paramParsingPackage.setProfileableByShell(bool1));
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsingPackage> parseProtectedBroadcast(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestProtectedBroadcast);
    try {
      String str = nonResString(0, typedArray);
      if (str != null)
        paramParsingPackage.addProtectedBroadcast(str); 
      return paramParseInput.success(paramParsingPackage);
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsingPackage> parseQueries(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws IOException, XmlPullParserException {
    int i = paramXmlResourceParser.getDepth();
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        if (j != 2)
          continue; 
        if (paramXmlResourceParser.getName().equals("intent")) {
          String str1;
          ParseResult parseResult = ParsedIntentInfoUtils.parseIntentInfo(null, paramParsingPackage, paramResources, paramXmlResourceParser, true, true, paramParseInput);
          if (parseResult.isError())
            return paramParseInput.error(parseResult); 
          ParsedIntentInfo parsedIntentInfo = (ParsedIntentInfo)parseResult.getResult();
          Uri uri = null;
          parseResult = null;
          String str2 = null;
          int k = parsedIntentInfo.countActions();
          int m = parsedIntentInfo.countDataSchemes();
          int n = parsedIntentInfo.countDataTypes();
          int i1 = (parsedIntentInfo.getHosts()).length;
          if (m == 0 && n == 0 && k == 0)
            return paramParseInput.error("intent tags must contain either an action or data."); 
          if (k > 1)
            return paramParseInput.error("intent tag may have at most one action."); 
          if (n > 1)
            return paramParseInput.error("intent tag may have at most one data type."); 
          if (m > 1)
            return paramParseInput.error("intent tag may have at most one data scheme."); 
          if (i1 > 1)
            return paramParseInput.error("intent tag may have at most one data host."); 
          Intent intent = new Intent();
          int i2 = parsedIntentInfo.countCategories();
          for (j = 0; j < i2; j++)
            intent.addCategory(parsedIntentInfo.getCategory(j)); 
          if (i1 == 1)
            str2 = parsedIntentInfo.getHosts()[0]; 
          if (m == 1)
            uri = (new Uri.Builder()).scheme(parsedIntentInfo.getDataScheme(0)).authority(str2).path("/*").build(); 
          if (n == 1) {
            str2 = parsedIntentInfo.getDataType(0);
            str1 = str2;
            if (!str2.contains("/")) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(str2);
              stringBuilder.append("/*");
              str1 = stringBuilder.toString();
            } 
            if (uri == null)
              uri = (new Uri.Builder()).scheme("content").authority("*").path("/*").build(); 
          } 
          intent.setDataAndType(uri, str1);
          if (k == 1)
            intent.setAction(parsedIntentInfo.getAction(0)); 
          paramParsingPackage.addQueriesIntent(intent);
          continue;
        } 
        if (paramXmlResourceParser.getName().equals("package")) {
          String str = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestQueriesPackage).getNonConfigurationString(0, 0);
          if (TextUtils.isEmpty(str))
            return paramParseInput.error("Package name is missing from package tag."); 
          paramParsingPackage.addQueriesPackage(str.intern());
          continue;
        } 
        if (paramXmlResourceParser.getName().equals("provider")) {
          TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestQueriesProvider);
          try {
            String str = typedArray.getNonConfigurationString(0, 0);
            if (TextUtils.isEmpty(str))
              return paramParseInput.error(-108, "Authority missing from provider tag."); 
            StringTokenizer stringTokenizer = new StringTokenizer();
            this(str, ";");
            while (stringTokenizer.hasMoreElements())
              paramParsingPackage.addQueriesProvider(stringTokenizer.nextToken()); 
          } finally {
            typedArray.recycle();
          } 
        } 
        continue;
      } 
      break;
    } 
    return paramParseInput.success(paramParsingPackage);
  }
  
  private static ParseResult<ParsingPackage> parseRestrictUpdateHash(int paramInt, ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) {
    if ((paramInt & 0x10) != 0) {
      TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestRestrictUpdate);
      try {
        String str = typedArray.getNonConfigurationString(0, 0);
        if (str != null) {
          int i = str.length();
          byte[] arrayOfByte = new byte[i / 2];
          for (paramInt = 0; paramInt < i; paramInt += 2)
            arrayOfByte[paramInt / 2] = (byte)(byte)((Character.digit(str.charAt(paramInt), 16) << 4) + Character.digit(str.charAt(paramInt + 1), 16)); 
          paramParsingPackage.setRestrictUpdateHash(arrayOfByte);
        } else {
          paramParsingPackage.setRestrictUpdateHash((byte[])null);
        } 
      } finally {
        typedArray.recycle();
      } 
    } 
    return paramParseInput.success(paramParsingPackage);
  }
  
  private static ParseResult<ParsingPackage> parseSharedUser(ParseInput paramParseInput, ParsingPackage paramParsingPackage, TypedArray paramTypedArray) {
    StringBuilder stringBuilder;
    String str = nonConfigString(0, 0, paramTypedArray);
    if (TextUtils.isEmpty(str))
      return paramParseInput.success(paramParsingPackage); 
    if (!"android".equals(paramParsingPackage.getPackageName())) {
      ParseResult parseResult = validateName(paramParseInput, str, true, true);
      if (parseResult.isError()) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("<manifest> specifies bad sharedUserId name \"");
        stringBuilder.append(str);
        stringBuilder.append("\": ");
        stringBuilder.append(parseResult.getErrorMessage());
        return paramParseInput.error(-107, stringBuilder.toString());
      } 
    } 
    return paramParseInput.success(stringBuilder.setSharedUserId(str.intern()).setSharedUserLabel(resId(3, paramTypedArray)));
  }
  
  private ParseResult<ParsingPackage> parseSplitApk(ParseInput paramParseInput, ParsingPackage paramParsingPackage, int paramInt1, AssetManager paramAssetManager, int paramInt2) {
    StringBuilder stringBuilder;
    String str = paramParsingPackage.getSplitCodePaths()[paramInt1];
    int i = paramAssetManager.findCookieForPath(str);
    if (i == 0) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Failed adding asset path: ");
      stringBuilder.append(str);
      return paramParseInput.error(-101, stringBuilder.toString());
    } 
    try {
      XmlResourceParser xmlResourceParser = paramAssetManager.openXmlResourceParser(i, "AndroidManifest.xml");
      try {
        Resources resources = new Resources();
        this(paramAssetManager, this.mDisplayMetrics, null);
        ParseResult<ParsingPackage> parseResult = parseSplitApk(paramParseInput, (ParsingPackage)stringBuilder, resources, xmlResourceParser, paramInt2, paramInt1);
        if (parseResult.isError()) {
          paramInt1 = parseResult.getErrorCode();
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append(str);
          stringBuilder1.append(" (at ");
          stringBuilder1.append(xmlResourceParser.getPositionDescription());
          stringBuilder1.append("): ");
          stringBuilder1.append(parseResult.getErrorMessage());
          parseResult = paramParseInput.error(paramInt1, stringBuilder1.toString());
          return parseResult;
        } 
        return parseResult;
      } finally {
        if (xmlResourceParser != null)
          try {
            xmlResourceParser.close();
          } finally {
            paramAssetManager = null;
          }  
      } 
    } catch (Exception exception) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to read manifest from ");
      stringBuilder.append(str);
      return paramParseInput.error(-102, stringBuilder.toString(), exception);
    } 
  }
  
  private ParseResult<ParsingPackage> parseSplitApk(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt1, int paramInt2) throws XmlPullParserException, IOException, PackageParser.PackageParserException {
    PackageParser.parsePackageSplitNames((XmlPullParser)paramXmlResourceParser, (AttributeSet)paramXmlResourceParser);
    boolean bool = false;
    int i = paramXmlResourceParser.getDepth();
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1) {
        ParseResult parseResult;
        if (i + 1 < paramXmlResourceParser.getDepth() || j != 2)
          continue; 
        if ("application".equals(paramXmlResourceParser.getName())) {
          if (bool) {
            Slog.w("PackageParsing", "<manifest> has more than one <application>");
            parseResult = paramParseInput.success(null);
          } else {
            bool = true;
            parseResult = parseSplitApplication(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser, paramInt1, paramInt2);
          } 
        } else {
          parseResult = ParsingUtils.unknownTag("<manifest>", paramParsingPackage, paramXmlResourceParser, paramParseInput);
        } 
        if (parseResult.isError())
          return paramParseInput.error(parseResult); 
        continue;
      } 
      if (!bool) {
        ParseResult parseResult = paramParseInput.deferError("<manifest> does not contain an <application>", 150776642L);
        if (parseResult.isError())
          return paramParseInput.error(parseResult); 
      } 
      return paramParseInput.success(paramParsingPackage);
    } 
  }
  
  private ParseResult<ParsingPackage> parseSplitApplication(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt1, int paramInt2) throws XmlPullParserException, IOException {
    ParsedService parsedService;
    ParsingPackage parsingPackage = paramParsingPackage;
    TypedArray typedArray1 = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestApplication);
    TypedArray typedArray2 = typedArray1;
    byte b = 1;
    try {
      StringBuilder stringBuilder;
      parsingPackage.setSplitHasCode(paramInt2, typedArray2.getBoolean(7, true));
      String str = typedArray2.getString(46);
      if (str != null)
        try {
          if (!ClassLoaderFactory.isValidClassLoaderName(str)) {
            stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("Invalid class loader name: ");
            stringBuilder.append(str);
            return paramParseInput.error(stringBuilder.toString());
          } 
        } finally {} 
      parsingPackage.setSplitClassLoaderName(paramInt2, str);
      typedArray2.recycle();
      int i = paramXmlResourceParser.getDepth();
      return paramParseInput.success(stringBuilder);
    } finally {
      parsedService.recycle();
    } 
  }
  
  private ParseResult parseSplitBaseAppChildTags(ParseInput paramParseInput, String paramString, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws IOException, XmlPullParserException {
    byte b;
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 1964930885:
        if (paramString.equals("uses-package")) {
          b = 3;
          break;
        } 
      case 8960125:
        if (paramString.equals("uses-static-library")) {
          b = 1;
          break;
        } 
      case -1115949454:
        if (paramString.equals("meta-data")) {
          b = 0;
          break;
        } 
      case -1356765254:
        if (paramString.equals("uses-library")) {
          b = 2;
          break;
        } 
    } 
    if (b != 0)
      return (b != 1) ? ((b != 2) ? ((b != 3) ? ParsingUtils.unknownTag("<application>", paramParsingPackage, paramXmlResourceParser, paramParseInput) : paramParseInput.success(null)) : parseUsesLibrary(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser)) : parseUsesStaticLibrary(paramParseInput, paramParsingPackage, paramResources, paramXmlResourceParser); 
    ParseResult<Bundle> parseResult = parseMetaData(paramParsingPackage, paramResources, paramXmlResourceParser, paramParsingPackage.getMetaData(), paramParseInput);
    if (parseResult.isSuccess())
      paramParsingPackage.setMetaData((Bundle)parseResult.getResult()); 
    return parseResult;
  }
  
  private static ParseResult<ParsingPackage> parseStaticLibrary(ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, ParseInput paramParseInput) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestStaticLibrary);
    try {
      StringBuilder stringBuilder;
      String str = typedArray.getNonResourceString(0);
      int i = typedArray.getInt(1, -1);
      int j = typedArray.getInt(2, 0);
      if (str == null || i < 0) {
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("Bad static-library declaration name: ");
        stringBuilder1.append(str);
        stringBuilder1.append(" version: ");
        stringBuilder1.append(i);
        parseResult = paramParseInput.error(stringBuilder1.toString());
        return parseResult;
      } 
      if (parseResult.getSharedUserId() != null) {
        parseResult = paramParseInput.error(-107, "sharedUserId not allowed in static shared library");
        return parseResult;
      } 
      if (parseResult.getStaticSharedLibName() != null) {
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Multiple static-shared libs for package ");
        stringBuilder.append(parseResult.getPackageName());
        parseResult = paramParseInput.error(stringBuilder.toString());
        return parseResult;
      } 
      ParseResult<ParsingPackage> parseResult = paramParseInput.success(parseResult.setStaticSharedLibName(stringBuilder.intern()).setStaticSharedLibVersion(PackageInfo.composeLongVersionCode(j, i)).setStaticSharedLibrary(true));
      return parseResult;
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsingPackage> parseSupportScreens(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestSupportsScreens);
    try {
      int i = anInt(0, 6, typedArray);
      int j = anInt(0, 7, typedArray);
      int k = anInt(0, 8, typedArray);
      return paramParseInput.success(paramParsingPackage.setSupportsSmallScreens(anInt(1, 1, typedArray)).setSupportsNormalScreens(anInt(1, 2, typedArray)).setSupportsLargeScreens(anInt(1, 3, typedArray)).setSupportsExtraLargeScreens(anInt(1, 5, typedArray)).setResizeable(anInt(1, 4, typedArray)).setAnyDensity(anInt(1, 0, typedArray)).setRequiresSmallestWidthDp(i).setCompatibleWidthLimitDp(j).setLargestWidthLimitDp(k));
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsingPackage> parseUsesConfiguration(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) {
    ConfigurationInfo configurationInfo = new ConfigurationInfo();
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestUsesConfiguration);
    try {
      configurationInfo.reqTouchScreen = typedArray.getInt(0, 0);
      configurationInfo.reqKeyboardType = typedArray.getInt(1, 0);
      if (typedArray.getBoolean(2, false))
        configurationInfo.reqInputFeatures = 0x1 | configurationInfo.reqInputFeatures; 
      configurationInfo.reqNavigation = typedArray.getInt(3, 0);
      if (typedArray.getBoolean(4, false))
        configurationInfo.reqInputFeatures |= 0x2; 
      paramParsingPackage.addConfigPreference(configurationInfo);
      return paramParseInput.success(paramParsingPackage);
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsingPackage> parseUsesFeature(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) {
    FeatureInfo featureInfo = parseFeatureInfo(paramResources, (AttributeSet)paramXmlResourceParser);
    paramParsingPackage.addReqFeature(featureInfo);
    if (featureInfo.name == null) {
      ConfigurationInfo configurationInfo = new ConfigurationInfo();
      configurationInfo.reqGlEsVersion = featureInfo.reqGlEsVersion;
      paramParsingPackage.addConfigPreference(configurationInfo);
    } 
    return paramParseInput.success(paramParsingPackage);
  }
  
  private static ParseResult<ParsingPackage> parseUsesLibrary(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestUsesLibrary);
    try {
      String str = typedArray.getNonResourceString(0);
      boolean bool = typedArray.getBoolean(1, true);
      if (str != null) {
        str = str.intern();
        if (bool) {
          paramParsingPackage.addUsesLibrary(str).removeUsesOptionalLibrary(str);
        } else if (!ArrayUtils.contains(paramParsingPackage.getUsesLibraries(), str)) {
          paramParsingPackage.addUsesOptionalLibrary(str);
        } 
      } 
      return paramParseInput.success(paramParsingPackage);
    } finally {
      typedArray.recycle();
    } 
  }
  
  private ParseResult<ParsingPackage> parseUsesPermission(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws IOException, XmlPullParserException {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestUsesPermission);
    try {
      String str1 = typedArray.getNonResourceString(0);
      int i = 0;
      TypedValue typedValue = typedArray.peekValue(1);
      int j = i;
      if (typedValue != null) {
        j = i;
        if (typedValue.type >= 16) {
          j = i;
          if (typedValue.type <= 31)
            j = typedValue.data; 
        } 
      } 
      String str2 = typedArray.getNonConfigurationString(2, 0);
      String str3 = typedArray.getNonConfigurationString(3, 0);
      XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
      ParseResult<ParsingPackage> parseResult = paramParseInput.success(paramParsingPackage);
      if (str1 == null)
        return parseResult; 
      if (j != 0) {
        i = Build.VERSION.RESOURCES_SDK_INT;
        if (j < i)
          return parseResult; 
      } 
      if (str2 != null && this.mCallback != null) {
        boolean bool = this.mCallback.hasFeature(str2);
        if (!bool)
          return parseResult; 
      } 
      if (str3 != null && this.mCallback != null) {
        boolean bool = this.mCallback.hasFeature(str3);
        if (bool)
          return parseResult; 
      } 
      if (!paramParsingPackage.getRequestedPermissions().contains(str1)) {
        paramParsingPackage.addRequestedPermission(str1.intern());
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Ignoring duplicate uses-permissions/uses-permissions-sdk-m: ");
        stringBuilder.append(str1);
        stringBuilder.append(" in package: ");
        stringBuilder.append(paramParsingPackage.getPackageName());
        stringBuilder.append(" at: ");
        stringBuilder.append(paramXmlResourceParser.getPositionDescription());
        Slog.w("PackageParsing", stringBuilder.toString());
      } 
      return parseResult;
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsingPackage> parseUsesSdk(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws IOException, XmlPullParserException {
    if (PackageParser.SDK_VERSION > 0) {
      TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestUsesSdk);
      int i = 1;
      String str1 = null;
      int j = 0;
      String str2 = null;
      try {
        ParseResult<ParsingPackage> parseResult;
        String str3;
        SparseIntArray sparseIntArray;
        TypedValue typedValue2 = typedArray.peekValue(0);
        int k = i;
        String str4 = str1;
        if (typedValue2 != null)
          if (typedValue2.type == 3 && typedValue2.string != null) {
            str4 = typedValue2.string.toString();
            k = i;
          } else {
            k = typedValue2.data;
            str4 = str1;
          }  
        TypedValue typedValue1 = typedArray.peekValue(1);
        if (typedValue1 != null) {
          if (typedValue1.type == 3 && typedValue1.string != null) {
            String str = typedValue1.string.toString();
            str3 = str4;
            i = j;
            str2 = str;
            if (str4 == null) {
              str3 = str;
              i = j;
              str2 = str;
            } 
          } else {
            i = ((TypedValue)str3).data;
            str3 = str4;
          } 
        } else {
          i = k;
          str2 = str4;
          str3 = str4;
        } 
        ParseResult<Integer> parseResult1 = computeTargetSdkVersion(i, str2, PackageParser.SDK_CODENAMES, paramParseInput);
        if (parseResult1.isError()) {
          parseResult = paramParseInput.error(parseResult1);
          return parseResult;
        } 
        i = ((Integer)parseResult1.getResult()).intValue();
        parseResult1 = parseResult.enableDeferredError(paramParsingPackage.getPackageName(), i);
        if (parseResult1.isError()) {
          parseResult = parseResult.error(parseResult1);
          return parseResult;
        } 
        parseResult1 = computeMinSdkVersion(k, str3, PackageParser.SDK_VERSION, PackageParser.SDK_CODENAMES, (ParseInput)parseResult);
        if (parseResult1.isError()) {
          parseResult = parseResult.error(parseResult1);
          return parseResult;
        } 
        paramParsingPackage.setMinSdkVersion(((Integer)parseResult1.getResult()).intValue()).setTargetSdkVersion(i);
        i = paramXmlResourceParser.getDepth();
        parseResult1 = null;
        while (true) {
          j = paramXmlResourceParser.next();
          if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
            ParseResult parseResult2;
            if (j == 3 || j == 4)
              continue; 
            if (paramXmlResourceParser.getName().equals("extension-sdk")) {
              if (parseResult1 == null) {
                sparseIntArray = new SparseIntArray();
                this();
              } 
              parseResult2 = parseExtensionSdk((ParseInput)parseResult, paramResources, paramXmlResourceParser, sparseIntArray);
              XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
            } else {
              parseResult2 = ParsingUtils.unknownTag("<uses-sdk>", paramParsingPackage, paramXmlResourceParser, (ParseInput)parseResult);
            } 
            if (parseResult2.isError()) {
              parseResult = parseResult.error(parseResult2);
              return parseResult;
            } 
            continue;
          } 
          break;
        } 
        paramParsingPackage.setMinExtensionVersions(exactSizedCopyOfSparseArray(sparseIntArray));
      } finally {
        typedArray.recycle();
      } 
    } 
    return paramParseInput.success(paramParsingPackage);
  }
  
  private static ParseResult<ParsingPackage> parseUsesStaticLibrary(ParseInput paramParseInput, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser) throws XmlPullParserException, IOException {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestUsesStaticLibrary);
    try {
      StringBuilder stringBuilder;
      String str1 = typedArray.getNonResourceString(0);
      int i = typedArray.getInt(1, -1);
      String str2 = typedArray.getNonResourceString(2);
      if (str1 == null || i < 0 || str2 == null) {
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Bad uses-static-library declaration name: ");
        stringBuilder.append(str1);
        stringBuilder.append(" version: ");
        stringBuilder.append(i);
        stringBuilder.append(" certDigest");
        stringBuilder.append(str2);
        parseResult = paramParseInput.error(stringBuilder.toString());
        return parseResult;
      } 
      if (stringBuilder.getUsesStaticLibraries().contains(str1)) {
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Depending on multiple versions of static library ");
        stringBuilder.append(str1);
        parseResult = parseResult.error(stringBuilder.toString());
        return parseResult;
      } 
      str1 = str1.intern();
      String str3 = str2.replace(":", "").toLowerCase();
      String[] arrayOfString2 = EmptyArray.STRING;
      if (stringBuilder.getTargetSdkVersion() >= 27) {
        ParseResult<String[]> parseResult1 = parseAdditionalCertificates((ParseInput)parseResult, paramResources, paramXmlResourceParser);
        if (parseResult1.isError()) {
          parseResult = parseResult.error(parseResult1);
          return parseResult;
        } 
        arrayOfString2 = (String[])parseResult1.getResult();
      } 
      String[] arrayOfString1 = new String[arrayOfString2.length + 1];
      arrayOfString1[0] = str3;
      System.arraycopy(arrayOfString2, 0, arrayOfString1, 1, arrayOfString2.length);
      ParseResult<ParsingPackage> parseResult = parseResult.success(stringBuilder.addUsesStaticLibrary(str1).addUsesStaticLibraryVersion(i).addUsesStaticLibraryCertDigests(arrayOfString1));
      return parseResult;
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static int resId(int paramInt, TypedArray paramTypedArray) {
    return paramTypedArray.getResourceId(paramInt, 0);
  }
  
  private static void setMaxAspectRatio(ParsingPackage paramParsingPackage) {
    float f1;
    if (paramParsingPackage.getTargetSdkVersion() < 26) {
      f1 = 1.86F;
    } else {
      f1 = 0.0F;
    } 
    float f2 = paramParsingPackage.getMaxAspectRatio();
    if (f2 == 0.0F) {
      Bundle bundle = paramParsingPackage.getMetaData();
      f2 = f1;
      if (bundle != null) {
        f2 = f1;
        if (bundle.containsKey("android.max_aspect"))
          f2 = bundle.getFloat("android.max_aspect", f1); 
      } 
    } 
    List<ParsedActivity> list = paramParsingPackage.getActivities();
    int i = list.size();
    for (byte b = 0; b < i; b++) {
      ParsedActivity parsedActivity = list.get(b);
      if (parsedActivity.getMaxAspectRatio() == null) {
        if (parsedActivity.getMetaData() != null) {
          f1 = parsedActivity.getMetaData().getFloat("android.max_aspect", f2);
        } else {
          f1 = f2;
        } 
        parsedActivity.setMaxAspectRatio(parsedActivity.getResizeMode(), f1);
      } 
    } 
  }
  
  private void setMinAspectRatio(ParsingPackage paramParsingPackage) {
    float f1 = paramParsingPackage.getMinAspectRatio();
    float f2 = 0.0F;
    if (f1 != 0.0F) {
      f2 = f1;
    } else if (paramParsingPackage.getTargetSdkVersion() < 29) {
      Callback callback = this.mCallback;
      if (callback != null && callback.hasFeature("android.hardware.type.watch")) {
        f2 = 1.0F;
      } else {
        f2 = 1.333F;
      } 
    } 
    List<ParsedActivity> list = paramParsingPackage.getActivities();
    int i = list.size();
    for (byte b = 0; b < i; b++) {
      ParsedActivity parsedActivity = list.get(b);
      if (parsedActivity.getMinAspectRatio() == null)
        parsedActivity.setMinAspectRatio(parsedActivity.getResizeMode(), f2); 
    } 
  }
  
  private void setSupportsSizeChanges(ParsingPackage paramParsingPackage) {
    boolean bool;
    Bundle bundle = paramParsingPackage.getMetaData();
    if (bundle != null && bundle.getBoolean("android.supports_size_changes", false)) {
      bool = true;
    } else {
      bool = false;
    } 
    List<ParsedActivity> list = paramParsingPackage.getActivities();
    int i = list.size();
    for (byte b = 0; b < i; b++) {
      ParsedActivity parsedActivity = list.get(b);
      if (bool || (parsedActivity.getMetaData() != null && parsedActivity.getMetaData().getBoolean("android.supports_size_changes", false)))
        parsedActivity.setSupportsSizeChanges(true); 
    } 
  }
  
  private static String string(int paramInt, TypedArray paramTypedArray) {
    return paramTypedArray.getString(paramInt);
  }
  
  private static ParseResult validateName(ParseInput paramParseInput, String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual length : ()I
    //   4: istore #4
    //   6: iconst_0
    //   7: istore #5
    //   9: iconst_1
    //   10: istore #6
    //   12: iconst_0
    //   13: istore #7
    //   15: iload #7
    //   17: iload #4
    //   19: if_icmpge -> 182
    //   22: aload_1
    //   23: iload #7
    //   25: invokevirtual charAt : (I)C
    //   28: istore #8
    //   30: iload #8
    //   32: bipush #97
    //   34: if_icmplt -> 44
    //   37: iload #8
    //   39: bipush #122
    //   41: if_icmple -> 58
    //   44: iload #8
    //   46: bipush #65
    //   48: if_icmplt -> 68
    //   51: iload #8
    //   53: bipush #90
    //   55: if_icmpgt -> 68
    //   58: iconst_0
    //   59: istore #9
    //   61: iload #5
    //   63: istore #10
    //   65: goto -> 126
    //   68: iload #6
    //   70: ifne -> 113
    //   73: iload #8
    //   75: bipush #48
    //   77: if_icmplt -> 95
    //   80: iload #5
    //   82: istore #10
    //   84: iload #6
    //   86: istore #9
    //   88: iload #8
    //   90: bipush #57
    //   92: if_icmple -> 126
    //   95: iload #8
    //   97: bipush #95
    //   99: if_icmpne -> 113
    //   102: iload #5
    //   104: istore #10
    //   106: iload #6
    //   108: istore #9
    //   110: goto -> 126
    //   113: iload #8
    //   115: bipush #46
    //   117: if_icmpne -> 140
    //   120: iconst_1
    //   121: istore #10
    //   123: iconst_1
    //   124: istore #9
    //   126: iinc #7, 1
    //   129: iload #10
    //   131: istore #5
    //   133: iload #9
    //   135: istore #6
    //   137: goto -> 15
    //   140: new java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial <init> : ()V
    //   147: astore_1
    //   148: aload_1
    //   149: ldc_w 'bad character ''
    //   152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: pop
    //   156: aload_1
    //   157: iload #8
    //   159: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: aload_1
    //   164: ldc_w '''
    //   167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: pop
    //   171: aload_0
    //   172: aload_1
    //   173: invokevirtual toString : ()Ljava/lang/String;
    //   176: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   181: areturn
    //   182: iload_3
    //   183: ifeq -> 203
    //   186: aload_1
    //   187: invokestatic isValidExtFilename : (Ljava/lang/String;)Z
    //   190: ifne -> 203
    //   193: aload_0
    //   194: ldc_w 'Invalid filename'
    //   197: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   202: areturn
    //   203: iload #5
    //   205: ifne -> 228
    //   208: iload_2
    //   209: ifne -> 215
    //   212: goto -> 228
    //   215: aload_0
    //   216: ldc_w 'must have at least one '.' separator'
    //   219: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   224: astore_0
    //   225: goto -> 236
    //   228: aload_0
    //   229: aconst_null
    //   230: invokeinterface success : (Ljava/lang/Object;)Landroid/content/pm/parsing/result/ParseResult;
    //   235: astore_0
    //   236: aload_0
    //   237: areturn
  }
  
  public ParseResult<ParsingPackage> parsePackage(ParseInput paramParseInput, File paramFile, int paramInt) throws PackageParser.PackageParserException {
    return paramFile.isDirectory() ? parseClusterPackage(paramParseInput, paramFile, paramInt) : parseMonolithicPackage(paramParseInput, paramFile, paramInt);
  }
  
  public static interface Callback {
    boolean hasFeature(String param1String);
    
    ParsingPackage startParsingPackage(String param1String1, String param1String2, String param1String3, TypedArray param1TypedArray, boolean param1Boolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/ParsingPackageUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
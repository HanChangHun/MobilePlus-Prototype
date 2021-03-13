package android.content.pm.parsing;

import android.content.pm.PackageParser;
import android.content.pm.VerifierInfo;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Slog;
import com.android.internal.util.ArrayUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ApkLiteParseUtils {
  private static final int DEFAULT_MIN_SDK_VERSION = 1;
  
  private static final int DEFAULT_TARGET_SDK_VERSION = 0;
  
  private static final int PARSE_DEFAULT_INSTALL_LOCATION = -1;
  
  private static final String TAG = "PackageParsing";
  
  public static ParseResult<PackageParser.ApkLite> parseApkLite(ParseInput paramParseInput, File paramFile, int paramInt) {
    return parseApkLiteInner(paramParseInput, paramFile, null, null, paramInt);
  }
  
  public static ParseResult<PackageParser.ApkLite> parseApkLite(ParseInput paramParseInput, FileDescriptor paramFileDescriptor, String paramString, int paramInt) {
    return parseApkLiteInner(paramParseInput, null, paramFileDescriptor, paramString, paramInt);
  }
  
  private static ParseResult<PackageParser.ApkLite> parseApkLite(ParseInput paramParseInput, String paramString, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, PackageParser.SigningDetails paramSigningDetails) throws IOException, XmlPullParserException {
    StringBuilder stringBuilder;
    ParseResult<Pair<String, String>> parseResult = parsePackageSplitNames(paramParseInput, paramXmlPullParser, paramAttributeSet);
    if (parseResult.isError())
      return paramParseInput.error(parseResult); 
    Pair pair = (Pair)parseResult.getResult();
    int i = -1;
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    String str1 = null;
    boolean bool5 = false;
    int i1 = 0;
    int i2;
    for (i2 = 0; i2 < paramAttributeSet.getAttributeCount(); i2++) {
      byte b;
      String str = paramAttributeSet.getAttributeName(i2);
      switch (str.hashCode()) {
        default:
          b = -1;
          break;
        case 1566947635:
          if (str.equals("configForSplit")) {
            b = 6;
            break;
          } 
        case 954743298:
          if (str.equals("coreApp")) {
            b = 4;
            break;
          } 
        case 688591589:
          if (str.equals("versionCode")) {
            b = 1;
            break;
          } 
        case 568084431:
          if (str.equals("isSplitRequired")) {
            b = 8;
            break;
          } 
        case 436669454:
          if (str.equals("isFeatureSplit")) {
            b = 7;
            break;
          } 
        case 138459604:
          if (str.equals("versionCodeMajor")) {
            b = 2;
            break;
          } 
        case 89284208:
          if (str.equals("installLocation")) {
            b = 0;
            break;
          } 
        case -403639534:
          if (str.equals("isolatedSplits")) {
            b = 5;
            break;
          } 
        case -1250986904:
          if (str.equals("revisionCode")) {
            b = 3;
            break;
          } 
      } 
      switch (b) {
        case 8:
          bool4 = paramAttributeSet.getAttributeBooleanValue(i2, false);
          break;
        case 7:
          bool3 = paramAttributeSet.getAttributeBooleanValue(i2, false);
          break;
        case 6:
          str1 = paramAttributeSet.getAttributeValue(i2);
          break;
        case 5:
          bool2 = paramAttributeSet.getAttributeBooleanValue(i2, false);
          break;
        case 4:
          bool1 = paramAttributeSet.getAttributeBooleanValue(i2, false);
          break;
        case 3:
          n = paramAttributeSet.getAttributeIntValue(i2, 0);
          break;
        case 2:
          k = paramAttributeSet.getAttributeIntValue(i2, 0);
          break;
        case 1:
          j = paramAttributeSet.getAttributeIntValue(i2, 0);
          break;
        case 0:
          i = paramAttributeSet.getAttributeIntValue(i2, -1);
          break;
      } 
    } 
    int i4 = paramXmlPullParser.getDepth() + 1;
    ArrayList<VerifierInfo> arrayList2 = new ArrayList();
    ArrayList<VerifierInfo> arrayList3 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    boolean bool6 = false;
    boolean bool7 = true;
    boolean bool8 = false;
    boolean bool9 = false;
    boolean bool10 = false;
    boolean bool11 = false;
    i2 = 1;
    int i3 = m;
    m = i4;
    while (true) {
      i4 = paramXmlPullParser.next();
      if (i4 != 1 && (i4 != 3 || paramXmlPullParser.getDepth() >= m)) {
        if (i4 == 3 || i4 == 4 || paramXmlPullParser.getDepth() != m)
          continue; 
        String str = paramXmlPullParser.getName();
        i4 = m;
        if ("package-verifier".equals(str)) {
          VerifierInfo verifierInfo = parseVerifier(paramAttributeSet);
          if (verifierInfo != null)
            arrayList2.add(verifierInfo); 
          continue;
        } 
        ArrayList<VerifierInfo> arrayList = arrayList2;
        if ("application".equals(paramXmlPullParser.getName())) {
          byte b = 0;
          boolean bool = bool11;
          while (b < paramAttributeSet.getAttributeCount()) {
            boolean bool12;
            boolean bool13;
            boolean bool14;
            String str5 = paramAttributeSet.getAttributeName(b);
            switch (str5.hashCode()) {
              default:
                m = -1;
                break;
              case -57729599:
                if (str5.equals("use32bitAbi")) {
                  m = 2;
                  break;
                } 
              case -597534042:
                if (str5.equals("extractNativeLibs")) {
                  m = 3;
                  break;
                } 
              case -1207511761:
                if (str5.equals("multiArch")) {
                  m = 1;
                  break;
                } 
              case -1517066970:
                if (str5.equals("useEmbeddedDex")) {
                  m = 4;
                  break;
                } 
              case -1833406514:
                if (str5.equals("debuggable")) {
                  m = 0;
                  break;
                } 
            } 
            if (m != 0) {
              if (m != 1) {
                if (m != 2) {
                  if (m != 3) {
                    if (m != 4) {
                      bool11 = bool9;
                      bool12 = bool8;
                      bool13 = bool7;
                      bool14 = bool6;
                    } else {
                      bool14 = paramAttributeSet.getAttributeBooleanValue(b, false);
                      bool11 = bool9;
                      bool12 = bool8;
                      bool13 = bool7;
                    } 
                  } else {
                    bool13 = paramAttributeSet.getAttributeBooleanValue(b, true);
                    bool11 = bool9;
                    bool12 = bool8;
                    bool14 = bool6;
                  } 
                } else {
                  bool12 = paramAttributeSet.getAttributeBooleanValue(b, false);
                  bool11 = bool9;
                  bool13 = bool7;
                  bool14 = bool6;
                } 
              } else {
                bool11 = paramAttributeSet.getAttributeBooleanValue(b, false);
                bool12 = bool8;
                bool13 = bool7;
                bool14 = bool6;
              } 
            } else {
              boolean bool15 = paramAttributeSet.getAttributeBooleanValue(b, false);
              bool = bool15;
              bool11 = bool9;
              bool12 = bool8;
              bool13 = bool7;
              bool14 = bool6;
              if (bool15) {
                bool10 = true;
                bool14 = bool6;
                bool13 = bool7;
                bool12 = bool8;
                bool11 = bool9;
                bool = bool15;
              } 
            } 
            b++;
            bool9 = bool11;
            bool8 = bool12;
            bool7 = bool13;
            bool6 = bool14;
          } 
          arrayList2 = arrayList;
          m = i4;
          bool11 = bool;
          continue;
        } 
        if ("overlay".equals(paramXmlPullParser.getName())) {
          m = 0;
          while (m < paramAttributeSet.getAttributeCount()) {
            boolean bool;
            String str6;
            String str7;
            String str5 = paramAttributeSet.getAttributeName(m);
            if ("requiredSystemPropertyName".equals(str5)) {
              str6 = paramAttributeSet.getAttributeValue(m);
              arrayList2 = arrayList3;
              bool = bool5;
              str7 = str3;
            } else if ("requiredSystemPropertyValue".equals(str5)) {
              str7 = paramAttributeSet.getAttributeValue(m);
              arrayList2 = arrayList3;
              bool = bool5;
              str6 = str2;
            } else if ("targetPackage".equals(str5)) {
              String str8 = paramAttributeSet.getAttributeValue(m);
              bool = bool5;
              str6 = str2;
              str7 = str3;
            } else if ("isStatic".equals(str5)) {
              bool = paramAttributeSet.getAttributeBooleanValue(m, false);
              arrayList2 = arrayList3;
              str6 = str2;
              str7 = str3;
            } else {
              arrayList2 = arrayList3;
              bool = bool5;
              str6 = str2;
              str7 = str3;
              if ("priority".equals(str5)) {
                i1 = paramAttributeSet.getAttributeIntValue(m, 0);
                str7 = str3;
                str6 = str2;
                bool = bool5;
                arrayList2 = arrayList3;
              } 
            } 
            m++;
            arrayList3 = arrayList2;
            bool5 = bool;
            str2 = str6;
            str3 = str7;
          } 
          arrayList2 = arrayList;
          m = i4;
          continue;
        } 
        if ("uses-split".equals(paramXmlPullParser.getName())) {
          if (str4 != null) {
            Slog.w("PackageParsing", "Only one <uses-split> permitted. Ignoring others.");
            continue;
          } 
          str4 = paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
          if (str4 == null)
            return paramParseInput.error(-108, "<uses-split> tag requires 'android:name' attribute"); 
          arrayList2 = arrayList;
          m = i4;
          continue;
        } 
        if ("uses-sdk".equals(paramXmlPullParser.getName())) {
          for (m = 0; m < paramAttributeSet.getAttributeCount(); m++) {
            String str5 = paramAttributeSet.getAttributeName(m);
            if ("targetSdkVersion".equals(str5))
              i3 = paramAttributeSet.getAttributeIntValue(m, 0); 
            if ("minSdkVersion".equals(str5))
              i2 = paramAttributeSet.getAttributeIntValue(m, 1); 
          } 
          arrayList2 = arrayList;
          m = i4;
          continue;
        } 
        if ("profileable".equals(paramXmlPullParser.getName())) {
          m = 0;
          while (m < paramAttributeSet.getAttributeCount()) {
            boolean bool = bool10;
            if ("shell".equals(paramAttributeSet.getAttributeName(m)))
              bool = paramAttributeSet.getAttributeBooleanValue(m, bool10); 
            m++;
            bool10 = bool;
          } 
          arrayList2 = arrayList;
          m = i4;
        } 
        continue;
      } 
      break;
    } 
    ArrayList<VerifierInfo> arrayList1 = arrayList3;
    if (!PackageParser.checkRequiredSystemProperties(str2, str3)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Skipping target and overlay pair ");
      stringBuilder.append((String)arrayList3);
      stringBuilder.append(" and ");
      stringBuilder.append(paramString);
      stringBuilder.append(": overlay ignored due to required system property: ");
      stringBuilder.append(str2);
      stringBuilder.append(" with value: ");
      stringBuilder.append(str3);
      Slog.i("PackageParsing", stringBuilder.toString());
      stringBuilder = null;
      bool5 = false;
      i1 = 0;
    } 
    return paramParseInput.success(new PackageParser.ApkLite(paramString, (String)pair.first, (String)pair.second, bool3, str1, str4, bool4, j, k, n, i, arrayList2, paramSigningDetails, bool1, bool11, bool10, bool9, bool8, bool6, bool7, bool2, (String)stringBuilder, bool5, i1, i2, i3));
  }
  
  private static ParseResult<PackageParser.ApkLite> parseApkLiteInner(ParseInput paramParseInput, File paramFile, FileDescriptor paramFileDescriptor, String paramString, int paramInt) {
    // Byte code:
    //   0: aload_2
    //   1: ifnull -> 10
    //   4: aload_3
    //   5: astore #5
    //   7: goto -> 16
    //   10: aload_1
    //   11: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   14: astore #5
    //   16: aconst_null
    //   17: astore #6
    //   19: aconst_null
    //   20: astore #7
    //   22: aconst_null
    //   23: astore #8
    //   25: aconst_null
    //   26: astore #9
    //   28: aconst_null
    //   29: astore #10
    //   31: iconst_0
    //   32: istore #11
    //   34: aload_2
    //   35: ifnull -> 57
    //   38: aload #8
    //   40: astore #12
    //   42: aload #10
    //   44: astore #13
    //   46: aload_2
    //   47: aload_3
    //   48: iconst_0
    //   49: aconst_null
    //   50: invokestatic loadFromFd : (Ljava/io/FileDescriptor;Ljava/lang/String;ILandroid/content/res/loader/AssetsProvider;)Landroid/content/res/ApkAssets;
    //   53: astore_2
    //   54: goto -> 71
    //   57: aload #8
    //   59: astore #12
    //   61: aload #10
    //   63: astore #13
    //   65: aload #5
    //   67: invokestatic loadFromPath : (Ljava/lang/String;)Landroid/content/res/ApkAssets;
    //   70: astore_2
    //   71: aload_2
    //   72: ldc 'AndroidManifest.xml'
    //   74: invokevirtual openXml : (Ljava/lang/String;)Landroid/content/res/XmlResourceParser;
    //   77: astore_3
    //   78: iload #4
    //   80: bipush #32
    //   82: iand
    //   83: ifeq -> 191
    //   86: iload #4
    //   88: bipush #16
    //   90: iand
    //   91: ifeq -> 97
    //   94: iconst_1
    //   95: istore #11
    //   97: ldc2_w 262144
    //   100: ldc_w 'collectCertificates'
    //   103: invokestatic traceBegin : (JLjava/lang/String;)V
    //   106: aload_0
    //   107: aload_1
    //   108: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   111: iload #11
    //   113: iconst_0
    //   114: getstatic android/content/pm/PackageParser$SigningDetails.UNKNOWN : Landroid/content/pm/PackageParser$SigningDetails;
    //   117: iconst_0
    //   118: invokestatic getSigningDetails : (Landroid/content/pm/parsing/result/ParseInput;Ljava/lang/String;ZZLandroid/content/pm/PackageParser$SigningDetails;I)Landroid/content/pm/parsing/result/ParseResult;
    //   121: astore_1
    //   122: aload_1
    //   123: invokeinterface isError : ()Z
    //   128: ifeq -> 163
    //   131: aload_0
    //   132: aload_1
    //   133: invokeinterface error : (Landroid/content/pm/parsing/result/ParseResult;)Landroid/content/pm/parsing/result/ParseResult;
    //   138: astore_1
    //   139: ldc2_w 262144
    //   142: invokestatic traceEnd : (J)V
    //   145: aload_3
    //   146: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   149: aload_2
    //   150: ifnull -> 161
    //   153: aload_2
    //   154: invokevirtual close : ()V
    //   157: goto -> 161
    //   160: astore_0
    //   161: aload_1
    //   162: areturn
    //   163: aload_1
    //   164: invokeinterface getResult : ()Ljava/lang/Object;
    //   169: checkcast android/content/pm/PackageParser$SigningDetails
    //   172: astore_1
    //   173: ldc2_w 262144
    //   176: invokestatic traceEnd : (J)V
    //   179: goto -> 195
    //   182: astore_1
    //   183: ldc2_w 262144
    //   186: invokestatic traceEnd : (J)V
    //   189: aload_1
    //   190: athrow
    //   191: getstatic android/content/pm/PackageParser$SigningDetails.UNKNOWN : Landroid/content/pm/PackageParser$SigningDetails;
    //   194: astore_1
    //   195: aload_0
    //   196: aload #5
    //   198: aload_3
    //   199: aload_3
    //   200: aload_1
    //   201: invokestatic parseApkLite : (Landroid/content/pm/parsing/result/ParseInput;Ljava/lang/String;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/pm/PackageParser$SigningDetails;)Landroid/content/pm/parsing/result/ParseResult;
    //   204: astore_1
    //   205: aload_3
    //   206: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   209: aload_2
    //   210: ifnull -> 221
    //   213: aload_2
    //   214: invokevirtual close : ()V
    //   217: goto -> 221
    //   220: astore_0
    //   221: aload_1
    //   222: areturn
    //   223: astore_0
    //   224: aload_3
    //   225: astore #12
    //   227: aload_2
    //   228: astore #13
    //   230: goto -> 535
    //   233: astore #13
    //   235: aload_2
    //   236: astore_1
    //   237: aload #13
    //   239: astore_2
    //   240: goto -> 376
    //   243: astore_0
    //   244: aload #7
    //   246: astore #12
    //   248: aload_2
    //   249: astore #13
    //   251: goto -> 535
    //   254: astore_3
    //   255: aload_2
    //   256: astore_1
    //   257: aload_3
    //   258: astore_2
    //   259: aload #6
    //   261: astore_3
    //   262: goto -> 376
    //   265: astore_0
    //   266: goto -> 535
    //   269: astore_2
    //   270: aload #6
    //   272: astore_3
    //   273: aload #9
    //   275: astore_1
    //   276: goto -> 376
    //   279: astore_1
    //   280: aload #8
    //   282: astore #12
    //   284: aload #10
    //   286: astore #13
    //   288: new java/lang/StringBuilder
    //   291: astore_2
    //   292: aload #8
    //   294: astore #12
    //   296: aload #10
    //   298: astore #13
    //   300: aload_2
    //   301: invokespecial <init> : ()V
    //   304: aload #8
    //   306: astore #12
    //   308: aload #10
    //   310: astore #13
    //   312: aload_2
    //   313: ldc_w 'Failed to parse '
    //   316: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: pop
    //   320: aload #8
    //   322: astore #12
    //   324: aload #10
    //   326: astore #13
    //   328: aload_2
    //   329: aload #5
    //   331: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: pop
    //   335: aload #8
    //   337: astore #12
    //   339: aload #10
    //   341: astore #13
    //   343: aload_0
    //   344: bipush #-100
    //   346: aload_2
    //   347: invokevirtual toString : ()Ljava/lang/String;
    //   350: aload_1
    //   351: invokeinterface error : (ILjava/lang/String;Ljava/lang/Exception;)Landroid/content/pm/parsing/result/ParseResult;
    //   356: astore_1
    //   357: aconst_null
    //   358: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   361: iconst_0
    //   362: ifeq -> 374
    //   365: new java/lang/NullPointerException
    //   368: dup
    //   369: invokespecial <init> : ()V
    //   372: athrow
    //   373: astore_0
    //   374: aload_1
    //   375: areturn
    //   376: aload_3
    //   377: astore #12
    //   379: aload_1
    //   380: astore #13
    //   382: new java/lang/StringBuilder
    //   385: astore #6
    //   387: aload_3
    //   388: astore #12
    //   390: aload_1
    //   391: astore #13
    //   393: aload #6
    //   395: invokespecial <init> : ()V
    //   398: aload_3
    //   399: astore #12
    //   401: aload_1
    //   402: astore #13
    //   404: aload #6
    //   406: ldc_w 'Failed to parse '
    //   409: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   412: pop
    //   413: aload_3
    //   414: astore #12
    //   416: aload_1
    //   417: astore #13
    //   419: aload #6
    //   421: aload #5
    //   423: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: pop
    //   427: aload_3
    //   428: astore #12
    //   430: aload_1
    //   431: astore #13
    //   433: ldc 'PackageParsing'
    //   435: aload #6
    //   437: invokevirtual toString : ()Ljava/lang/String;
    //   440: aload_2
    //   441: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   444: pop
    //   445: aload_3
    //   446: astore #12
    //   448: aload_1
    //   449: astore #13
    //   451: new java/lang/StringBuilder
    //   454: astore #6
    //   456: aload_3
    //   457: astore #12
    //   459: aload_1
    //   460: astore #13
    //   462: aload #6
    //   464: invokespecial <init> : ()V
    //   467: aload_3
    //   468: astore #12
    //   470: aload_1
    //   471: astore #13
    //   473: aload #6
    //   475: ldc_w 'Failed to parse '
    //   478: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   481: pop
    //   482: aload_3
    //   483: astore #12
    //   485: aload_1
    //   486: astore #13
    //   488: aload #6
    //   490: aload #5
    //   492: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: pop
    //   496: aload_3
    //   497: astore #12
    //   499: aload_1
    //   500: astore #13
    //   502: aload_0
    //   503: bipush #-102
    //   505: aload #6
    //   507: invokevirtual toString : ()Ljava/lang/String;
    //   510: aload_2
    //   511: invokeinterface error : (ILjava/lang/String;Ljava/lang/Exception;)Landroid/content/pm/parsing/result/ParseResult;
    //   516: astore_0
    //   517: aload_3
    //   518: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   521: aload_1
    //   522: ifnull -> 533
    //   525: aload_1
    //   526: invokevirtual close : ()V
    //   529: goto -> 533
    //   532: astore_1
    //   533: aload_0
    //   534: areturn
    //   535: aload #12
    //   537: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   540: aload #13
    //   542: ifnull -> 554
    //   545: aload #13
    //   547: invokevirtual close : ()V
    //   550: goto -> 554
    //   553: astore_1
    //   554: aload_0
    //   555: athrow
    // Exception table:
    //   from	to	target	type
    //   46	54	279	java/io/IOException
    //   46	54	269	org/xmlpull/v1/XmlPullParserException
    //   46	54	269	java/lang/RuntimeException
    //   46	54	265	finally
    //   65	71	279	java/io/IOException
    //   65	71	269	org/xmlpull/v1/XmlPullParserException
    //   65	71	269	java/lang/RuntimeException
    //   65	71	265	finally
    //   71	78	254	org/xmlpull/v1/XmlPullParserException
    //   71	78	254	java/io/IOException
    //   71	78	254	java/lang/RuntimeException
    //   71	78	243	finally
    //   97	106	233	org/xmlpull/v1/XmlPullParserException
    //   97	106	233	java/io/IOException
    //   97	106	233	java/lang/RuntimeException
    //   97	106	223	finally
    //   106	139	182	finally
    //   139	145	233	org/xmlpull/v1/XmlPullParserException
    //   139	145	233	java/io/IOException
    //   139	145	233	java/lang/RuntimeException
    //   139	145	223	finally
    //   153	157	160	finally
    //   163	173	182	finally
    //   173	179	233	org/xmlpull/v1/XmlPullParserException
    //   173	179	233	java/io/IOException
    //   173	179	233	java/lang/RuntimeException
    //   173	179	223	finally
    //   183	189	233	org/xmlpull/v1/XmlPullParserException
    //   183	189	233	java/io/IOException
    //   183	189	233	java/lang/RuntimeException
    //   183	189	223	finally
    //   189	191	233	org/xmlpull/v1/XmlPullParserException
    //   189	191	233	java/io/IOException
    //   189	191	233	java/lang/RuntimeException
    //   189	191	223	finally
    //   191	195	233	org/xmlpull/v1/XmlPullParserException
    //   191	195	233	java/io/IOException
    //   191	195	233	java/lang/RuntimeException
    //   191	195	223	finally
    //   195	205	233	org/xmlpull/v1/XmlPullParserException
    //   195	205	233	java/io/IOException
    //   195	205	233	java/lang/RuntimeException
    //   195	205	223	finally
    //   213	217	220	finally
    //   288	292	269	org/xmlpull/v1/XmlPullParserException
    //   288	292	269	java/io/IOException
    //   288	292	269	java/lang/RuntimeException
    //   288	292	265	finally
    //   300	304	269	org/xmlpull/v1/XmlPullParserException
    //   300	304	269	java/io/IOException
    //   300	304	269	java/lang/RuntimeException
    //   300	304	265	finally
    //   312	320	269	org/xmlpull/v1/XmlPullParserException
    //   312	320	269	java/io/IOException
    //   312	320	269	java/lang/RuntimeException
    //   312	320	265	finally
    //   328	335	269	org/xmlpull/v1/XmlPullParserException
    //   328	335	269	java/io/IOException
    //   328	335	269	java/lang/RuntimeException
    //   328	335	265	finally
    //   343	357	269	org/xmlpull/v1/XmlPullParserException
    //   343	357	269	java/io/IOException
    //   343	357	269	java/lang/RuntimeException
    //   343	357	265	finally
    //   365	373	373	finally
    //   382	387	265	finally
    //   393	398	265	finally
    //   404	413	265	finally
    //   419	427	265	finally
    //   433	445	265	finally
    //   451	456	265	finally
    //   462	467	265	finally
    //   473	482	265	finally
    //   488	496	265	finally
    //   502	517	265	finally
    //   525	529	532	finally
    //   545	550	553	finally
  }
  
  public static ParseResult<PackageParser.PackageLite> parseClusterPackageLite(ParseInput paramParseInput, File paramFile, int paramInt) {
    File[] arrayOfFile = paramFile.listFiles();
    if (ArrayUtils.isEmpty((Object[])arrayOfFile))
      return paramParseInput.error(-100, "No packages found in split"); 
    int i = arrayOfFile.length;
    byte b = 0;
    if (i == 1 && arrayOfFile[0].isDirectory())
      return parseClusterPackageLite(paramParseInput, arrayOfFile[0], paramInt); 
    ParseResult<PackageParser.ApkLite> parseResult = null;
    i = 0;
    ArrayMap arrayMap = new ArrayMap();
    Trace.traceBegin(262144L, "parseApkLite");
    try {
      int j = arrayOfFile.length;
      while (true) {
        StringBuilder stringBuilder;
        if (b < j) {
          File file = arrayOfFile[b];
          ParseResult<PackageParser.ApkLite> parseResult1 = parseResult;
          int k = i;
          try {
            String str2;
            if (PackageParser.isApkFile(file)) {
              ParseResult<PackageParser.PackageLite> parseResult2;
              String str;
              parseResult1 = parseApkLite(paramParseInput, file, paramInt);
              if (parseResult1.isError()) {
                parseResult2 = paramParseInput.error(parseResult1);
                Trace.traceEnd(262144L);
                return parseResult2;
              } 
              PackageParser.ApkLite apkLite = (PackageParser.ApkLite)parseResult1.getResult();
              if (parseResult == null) {
                str = apkLite.packageName;
                k = apkLite.versionCode;
              } else {
                boolean bool = str.equals(apkLite.packageName);
                if (!bool) {
                  stringBuilder = new StringBuilder();
                  this();
                  stringBuilder.append("Inconsistent package ");
                  stringBuilder.append(apkLite.packageName);
                  stringBuilder.append(" in ");
                  stringBuilder.append(file);
                  stringBuilder.append("; expected ");
                  stringBuilder.append(str);
                  parseResult2 = parseResult2.error(-101, stringBuilder.toString());
                  Trace.traceEnd(262144L);
                  return parseResult2;
                } 
                k = i;
                if (i != apkLite.versionCode) {
                  stringBuilder = new StringBuilder();
                  this();
                  stringBuilder.append("Inconsistent version ");
                  stringBuilder.append(apkLite.versionCode);
                  stringBuilder.append(" in ");
                  stringBuilder.append(file);
                  stringBuilder.append("; expected ");
                  stringBuilder.append(i);
                  parseResult2 = parseResult2.error(-101, stringBuilder.toString());
                  Trace.traceEnd(262144L);
                  return parseResult2;
                } 
              } 
              str2 = str;
              if (arrayMap.put(apkLite.splitName, apkLite) != null) {
                stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("Split name ");
                stringBuilder.append(apkLite.splitName);
                stringBuilder.append(" defined more than once; most recent was ");
                stringBuilder.append(file);
                parseResult2 = parseResult2.error(-101, stringBuilder.toString());
                Trace.traceEnd(262144L);
                return parseResult2;
              } 
            } 
            b++;
            String str1 = str2;
            i = k;
            continue;
          } finally {}
        } else {
          String[] arrayOfString1;
          String[] arrayOfString2;
          Trace.traceEnd(262144L);
          PackageParser.ApkLite apkLite = (PackageParser.ApkLite)arrayMap.remove(null);
          if (apkLite == null) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Missing base APK in ");
            stringBuilder1.append(stringBuilder);
            return paramParseInput.error(-101, stringBuilder1.toString());
          } 
          i = arrayMap.size();
          String[] arrayOfString3 = null;
          boolean[] arrayOfBoolean = null;
          if (i > 0) {
            String[] arrayOfString5 = new String[i];
            arrayOfBoolean = new boolean[i];
            arrayOfString2 = new String[i];
            String[] arrayOfString6 = new String[i];
            arrayOfString1 = new String[i];
            int[] arrayOfInt2 = new int[i];
            arrayOfString3 = (String[])arrayMap.keySet().toArray((Object[])arrayOfString5);
            Arrays.sort(arrayOfString3, PackageParser.sSplitNameComparator);
            paramInt = 0;
            File[] arrayOfFile1 = arrayOfFile;
            while (paramInt < i) {
              PackageParser.ApkLite apkLite1 = (PackageParser.ApkLite)arrayMap.get(arrayOfString3[paramInt]);
              arrayOfString2[paramInt] = apkLite1.usesSplitName;
              arrayOfBoolean[paramInt] = apkLite1.isFeatureSplit;
              arrayOfString6[paramInt] = apkLite1.configForSplit;
              arrayOfString1[paramInt] = apkLite1.codePath;
              arrayOfInt2[paramInt] = apkLite1.revisionCode;
              paramInt++;
            } 
            String[] arrayOfString4 = arrayOfString6;
            int[] arrayOfInt1 = arrayOfInt2;
          } else {
            arrayOfString2 = null;
            parseResult = null;
            arrayOfString1 = null;
            arrayOfFile = null;
          } 
          return paramParseInput.success(new PackageParser.PackageLite(stringBuilder.getAbsolutePath(), apkLite, arrayOfString3, arrayOfBoolean, arrayOfString2, (String[])parseResult, arrayOfString1, (int[])arrayOfFile));
        } 
        Trace.traceEnd(262144L);
        throw paramParseInput;
      } 
    } finally {}
    Trace.traceEnd(262144L);
    throw paramParseInput;
  }
  
  public static ParseResult<PackageParser.PackageLite> parseMonolithicPackageLite(ParseInput paramParseInput, File paramFile, int paramInt) {
    Trace.traceBegin(262144L, "parseApkLite");
    try {
      ParseResult<PackageParser.ApkLite> parseResult1 = parseApkLite(paramParseInput, paramFile, paramInt);
      if (parseResult1.isError()) {
        parseResult = paramParseInput.error(parseResult1);
        return parseResult;
      } 
      PackageParser.ApkLite apkLite = (PackageParser.ApkLite)parseResult1.getResult();
      String str = paramFile.getAbsolutePath();
      PackageParser.PackageLite packageLite = new PackageParser.PackageLite();
      this(str, apkLite, null, null, null, null, null, null);
      ParseResult<PackageParser.PackageLite> parseResult = parseResult.success(packageLite);
      return parseResult;
    } finally {
      Trace.traceEnd(262144L);
    } 
  }
  
  public static ParseResult<PackageParser.PackageLite> parsePackageLite(ParseInput paramParseInput, File paramFile, int paramInt) {
    return paramFile.isDirectory() ? parseClusterPackageLite(paramParseInput, paramFile, paramInt) : parseMonolithicPackageLite(paramParseInput, paramFile, paramInt);
  }
  
  public static ParseResult<Pair<String, String>> parsePackageSplitNames(ParseInput paramParseInput, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws IOException, XmlPullParserException {
    StringBuilder stringBuilder1;
    String str1;
    StringBuilder stringBuilder2;
    int i;
    while (true) {
      i = paramXmlPullParser.next();
      if (i != 2 && i != 1)
        continue; 
      break;
    } 
    if (i != 2)
      return paramParseInput.error(-108, "No start tag found"); 
    if (!paramXmlPullParser.getName().equals("manifest"))
      return paramParseInput.error(-108, "No <manifest> tag"); 
    String str4 = paramAttributeSet.getAttributeValue(null, "package");
    if (!"android".equals(str4)) {
      str1 = PackageParser.validateName(str4, true, true);
      if (str1 != null) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Invalid manifest package: ");
        stringBuilder2.append(str1);
        return paramParseInput.error(-106, stringBuilder2.toString());
      } 
    } 
    String str3 = stringBuilder2.getAttributeValue(null, "split");
    String str2 = str3;
    if (str3 != null)
      if (str3.length() == 0) {
        str2 = null;
      } else {
        String str = PackageParser.validateName(str3, false, false);
        str2 = str3;
        if (str != null) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Invalid manifest split: ");
          stringBuilder1.append(str);
          return paramParseInput.error(-106, stringBuilder1.toString());
        } 
      }  
    str3 = str4.intern();
    if (stringBuilder1 != null)
      str1 = stringBuilder1.intern(); 
    return paramParseInput.success(Pair.create(str3, str1));
  }
  
  public static VerifierInfo parseVerifier(AttributeSet paramAttributeSet) {
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
      Slog.i("PackageParsing", "verifier package name was null; skipping");
      return null;
    } 
    PublicKey publicKey = PackageParser.parsePublicKey(str2);
    if (publicKey == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to parse verifier public key for ");
      stringBuilder.append(str1);
      Slog.i("PackageParsing", stringBuilder.toString());
      return null;
    } 
    return new VerifierInfo(str1, (PublicKey)stringBuilder);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/ApkLiteParseUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
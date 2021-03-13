package android.content.pm.parsing.component;

import android.content.pm.ActivityInfo;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public class ParsedActivityUtils {
  private static final String TAG = "PackageParsing";
  
  private static int getActivityResizeMode(ParsingPackage paramParsingPackage, TypedArray paramTypedArray, int paramInt) {
    Boolean bool = paramParsingPackage.getResizeableActivity();
    boolean bool1 = paramTypedArray.hasValue(40);
    boolean bool2 = true;
    if (bool1 || bool != null) {
      if (bool == null || !bool.booleanValue())
        bool2 = false; 
      return paramTypedArray.getBoolean(40, bool2) ? 2 : 0;
    } 
    return paramParsingPackage.isResizeableActivityViaSdkVersion() ? 1 : (ActivityInfo.isFixedOrientationPortrait(paramInt) ? 6 : (ActivityInfo.isFixedOrientationLandscape(paramInt) ? 5 : ((paramInt == 14) ? 7 : 4)));
  }
  
  public static ParseResult<ParsedActivity> parseActivityAlias(ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, boolean paramBoolean, ParseInput paramParseInput) throws XmlPullParserException, IOException {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: getstatic com/android/internal/R$styleable.AndroidManifestActivityAlias : [I
    //   5: invokevirtual obtainAttributes : (Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
    //   8: astore #5
    //   10: aload #5
    //   12: bipush #7
    //   14: sipush #1024
    //   17: invokevirtual getNonConfigurationString : (II)Ljava/lang/String;
    //   20: astore #6
    //   22: aload #6
    //   24: ifnonnull -> 48
    //   27: aload #4
    //   29: ldc '<activity-alias> does not specify android:targetActivity'
    //   31: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   36: astore_0
    //   37: aload #5
    //   39: invokevirtual recycle : ()V
    //   42: aload_0
    //   43: areturn
    //   44: astore_0
    //   45: goto -> 418
    //   48: aload_0
    //   49: invokeinterface getPackageName : ()Ljava/lang/String;
    //   54: astore #7
    //   56: aload #7
    //   58: aload #6
    //   60: invokestatic buildClassName : (Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   63: astore #6
    //   65: aload #6
    //   67: ifnonnull -> 111
    //   70: new java/lang/StringBuilder
    //   73: astore_0
    //   74: aload_0
    //   75: invokespecial <init> : ()V
    //   78: aload_0
    //   79: ldc 'Empty class name in package '
    //   81: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: aload_0
    //   86: aload #7
    //   88: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: pop
    //   92: aload #4
    //   94: aload_0
    //   95: invokevirtual toString : ()Ljava/lang/String;
    //   98: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   103: astore_0
    //   104: aload #5
    //   106: invokevirtual recycle : ()V
    //   109: aload_0
    //   110: areturn
    //   111: aload_0
    //   112: invokeinterface getActivities : ()Ljava/util/List;
    //   117: astore #8
    //   119: aload #8
    //   121: invokestatic size : (Ljava/util/Collection;)I
    //   124: istore #9
    //   126: iconst_0
    //   127: istore #10
    //   129: iload #10
    //   131: iload #9
    //   133: if_icmpge -> 172
    //   136: aload #8
    //   138: iload #10
    //   140: invokeinterface get : (I)Ljava/lang/Object;
    //   145: checkcast android/content/pm/parsing/component/ParsedActivity
    //   148: astore #7
    //   150: aload #6
    //   152: aload #7
    //   154: invokevirtual getName : ()Ljava/lang/String;
    //   157: invokevirtual equals : (Ljava/lang/Object;)Z
    //   160: ifeq -> 166
    //   163: goto -> 175
    //   166: iinc #10, 1
    //   169: goto -> 129
    //   172: aconst_null
    //   173: astore #7
    //   175: aload #7
    //   177: ifnonnull -> 253
    //   180: new java/lang/StringBuilder
    //   183: astore_1
    //   184: aload_1
    //   185: invokespecial <init> : ()V
    //   188: aload_1
    //   189: ldc '<activity-alias> target activity '
    //   191: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: pop
    //   195: aload_1
    //   196: aload #6
    //   198: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: pop
    //   202: aload_1
    //   203: ldc ' not found in manifest with activities = '
    //   205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: pop
    //   209: aload_1
    //   210: aload_0
    //   211: invokeinterface getActivities : ()Ljava/util/List;
    //   216: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   219: pop
    //   220: aload_1
    //   221: ldc ', parsedActivities = '
    //   223: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload_1
    //   228: aload #8
    //   230: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   233: pop
    //   234: aload #4
    //   236: aload_1
    //   237: invokevirtual toString : ()Ljava/lang/String;
    //   240: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   245: astore_0
    //   246: aload #5
    //   248: invokevirtual recycle : ()V
    //   251: aload_0
    //   252: areturn
    //   253: aload #6
    //   255: aload #7
    //   257: invokestatic makeAlias : (Ljava/lang/String;Landroid/content/pm/parsing/component/ParsedActivity;)Landroid/content/pm/parsing/component/ParsedActivity;
    //   260: astore #6
    //   262: new java/lang/StringBuilder
    //   265: astore #7
    //   267: aload #7
    //   269: invokespecial <init> : ()V
    //   272: aload #7
    //   274: ldc '<'
    //   276: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: pop
    //   280: aload #7
    //   282: aload_2
    //   283: invokeinterface getName : ()Ljava/lang/String;
    //   288: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   291: pop
    //   292: aload #7
    //   294: ldc '>'
    //   296: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: pop
    //   300: aload #7
    //   302: invokevirtual toString : ()Ljava/lang/String;
    //   305: astore #8
    //   307: aload #5
    //   309: astore #7
    //   311: aload #6
    //   313: aload #8
    //   315: aconst_null
    //   316: aload_0
    //   317: aload #5
    //   319: iconst_0
    //   320: iload_3
    //   321: aload #4
    //   323: bipush #10
    //   325: bipush #6
    //   327: aconst_null
    //   328: iconst_4
    //   329: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   332: iconst_1
    //   333: iconst_0
    //   334: bipush #8
    //   336: iconst_2
    //   337: aconst_null
    //   338: bipush #11
    //   340: aconst_null
    //   341: invokestatic parseMainComponent : (Landroid/content/pm/parsing/component/ParsedMainComponent;Ljava/lang/String;[Ljava/lang/String;Landroid/content/pm/parsing/ParsingPackage;Landroid/content/res/TypedArray;IZLandroid/content/pm/parsing/result/ParseInput;IILjava/lang/Integer;Ljava/lang/Integer;IIIILjava/lang/Integer;ILjava/lang/Integer;)Landroid/content/pm/parsing/result/ParseResult;
    //   344: astore #11
    //   346: aload #11
    //   348: invokeinterface isError : ()Z
    //   353: istore_3
    //   354: iload_3
    //   355: ifeq -> 366
    //   358: aload #7
    //   360: invokevirtual recycle : ()V
    //   363: aload #11
    //   365: areturn
    //   366: aload #6
    //   368: invokevirtual getFlags : ()I
    //   371: ldc 1048576
    //   373: iand
    //   374: ifeq -> 382
    //   377: iconst_1
    //   378: istore_3
    //   379: goto -> 384
    //   382: iconst_0
    //   383: istore_3
    //   384: aload #6
    //   386: aload_0
    //   387: aload #8
    //   389: aload_2
    //   390: aload_1
    //   391: aload #7
    //   393: iconst_0
    //   394: iconst_1
    //   395: iload_3
    //   396: aload #4
    //   398: bipush #9
    //   400: iconst_3
    //   401: iconst_5
    //   402: invokestatic parseActivityOrAlias : (Landroid/content/pm/parsing/component/ParsedActivity;Landroid/content/pm/parsing/ParsingPackage;Ljava/lang/String;Landroid/content/res/XmlResourceParser;Landroid/content/res/Resources;Landroid/content/res/TypedArray;ZZZLandroid/content/pm/parsing/result/ParseInput;III)Landroid/content/pm/parsing/result/ParseResult;
    //   405: astore_0
    //   406: aload #7
    //   408: invokevirtual recycle : ()V
    //   411: aload_0
    //   412: areturn
    //   413: astore_0
    //   414: goto -> 418
    //   417: astore_0
    //   418: aload #5
    //   420: invokevirtual recycle : ()V
    //   423: aload_0
    //   424: athrow
    // Exception table:
    //   from	to	target	type
    //   10	22	417	finally
    //   27	37	44	finally
    //   48	65	417	finally
    //   70	104	44	finally
    //   111	126	417	finally
    //   136	163	44	finally
    //   180	246	44	finally
    //   253	307	417	finally
    //   311	354	413	finally
    //   366	377	413	finally
    //   384	406	413	finally
  }
  
  private static ParseResult<ParsedActivity> parseActivityOrAlias(ParsedActivity paramParsedActivity, ParsingPackage paramParsingPackage, String paramString, XmlResourceParser paramXmlResourceParser, Resources paramResources, TypedArray paramTypedArray, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, ParseInput paramParseInput, int paramInt1, int paramInt2, int paramInt3) throws IOException, XmlPullParserException {
    String str = paramTypedArray.getNonConfigurationString(paramInt1, 1024);
    if (str != null) {
      StringBuilder stringBuilder;
      String str1 = ParsingUtils.buildClassName(paramParsingPackage.getPackageName(), str);
      if (str1 == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Activity ");
        stringBuilder.append(paramParsedActivity.getName());
        stringBuilder.append(" specified invalid parentActivityName ");
        stringBuilder.append(str);
        Log.e("PackageParsing", stringBuilder.toString());
      } else {
        paramParsedActivity.setParentActivity((String)stringBuilder);
      } 
    } 
    boolean bool = false;
    str = paramTypedArray.getNonConfigurationString(paramInt2, 0);
    if (paramBoolean2) {
      paramParsedActivity.setPermission(str);
    } else {
      String str1;
      if (str != null) {
        str1 = str;
      } else {
        str1 = paramParsingPackage.getPermission();
      } 
      paramParsedActivity.setPermission(str1);
    } 
    boolean bool1 = paramTypedArray.hasValue(paramInt3);
    if (bool1)
      paramParsedActivity.exported = paramTypedArray.getBoolean(paramInt3, false); 
    paramInt1 = paramXmlResourceParser.getDepth();
    while (true) {
      paramInt2 = paramXmlResourceParser.next();
      if (paramInt2 != 1 && (paramInt2 != 3 || paramXmlResourceParser.getDepth() > paramInt1)) {
        ParseResult parseResult1;
        if (paramInt2 != 2)
          continue; 
        if (paramXmlResourceParser.getName().equals("intent-filter")) {
          parseResult1 = parseIntentFilter(paramParsingPackage, paramParsedActivity, paramBoolean1 ^ true, paramBoolean3, paramResources, paramXmlResourceParser, paramParseInput);
          if (parseResult1.isSuccess()) {
            ParsedIntentInfo parsedIntentInfo = (ParsedIntentInfo)parseResult1.getResult();
            if (parsedIntentInfo != null) {
              paramParsedActivity.order = Math.max(parsedIntentInfo.getOrder(), paramParsedActivity.order);
              paramParsedActivity.addIntent(parsedIntentInfo);
            } 
          } 
        } else if (paramXmlResourceParser.getName().equals("meta-data")) {
          parseResult1 = ParsedComponentUtils.addMetaData(paramParsedActivity, paramParsingPackage, paramResources, paramXmlResourceParser, paramParseInput);
        } else if (!paramBoolean1 && !paramBoolean2 && paramXmlResourceParser.getName().equals("preferred")) {
          parseResult1 = parseIntentFilter(paramParsingPackage, paramParsedActivity, true, paramBoolean3, paramResources, paramXmlResourceParser, paramParseInput);
          if (parseResult1.isSuccess()) {
            ParsedIntentInfo parsedIntentInfo = (ParsedIntentInfo)parseResult1.getResult();
            if (parsedIntentInfo != null)
              paramParsingPackage.addPreferredActivityFilter(paramParsedActivity.getClassName(), parsedIntentInfo); 
          } 
        } else if (!paramBoolean1 && !paramBoolean2 && paramXmlResourceParser.getName().equals("layout")) {
          parseResult1 = parseLayout(paramResources, (AttributeSet)paramXmlResourceParser, paramParseInput);
          if (parseResult1.isSuccess())
            paramParsedActivity.windowLayout = (ActivityInfo.WindowLayout)parseResult1.getResult(); 
        } else {
          parseResult1 = ParsingUtils.unknownTag(paramString, paramParsingPackage, paramXmlResourceParser, paramParseInput);
        } 
        if (parseResult1.isError())
          return paramParseInput.error(parseResult1); 
        continue;
      } 
      break;
    } 
    ParseResult<ActivityInfo.WindowLayout> parseResult = resolveWindowLayout(paramParsedActivity, paramParseInput);
    if (parseResult.isError())
      return paramParseInput.error(parseResult); 
    paramParsedActivity.windowLayout = (ActivityInfo.WindowLayout)parseResult.getResult();
    if (!bool1) {
      if (paramParsedActivity.getIntents().size() > 0)
        bool = true; 
      paramParsedActivity.exported = bool;
    } 
    return paramParseInput.success(paramParsedActivity);
  }
  
  public static ParseResult<ParsedActivity> parseActivityOrReceiver(String[] paramArrayOfString, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, boolean paramBoolean, ParseInput paramParseInput) throws XmlPullParserException, IOException {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface getPackageName : ()Ljava/lang/String;
    //   6: astore #7
    //   8: new android/content/pm/parsing/component/ParsedActivity
    //   11: dup
    //   12: invokespecial <init> : ()V
    //   15: astore #8
    //   17: ldc_w 'receiver'
    //   20: aload_3
    //   21: invokeinterface getName : ()Ljava/lang/String;
    //   26: invokevirtual equals : (Ljava/lang/Object;)Z
    //   29: istore #9
    //   31: new java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial <init> : ()V
    //   38: astore #10
    //   40: aload #10
    //   42: ldc '<'
    //   44: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: pop
    //   48: aload #10
    //   50: aload_3
    //   51: invokeinterface getName : ()Ljava/lang/String;
    //   56: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: aload #10
    //   62: ldc '>'
    //   64: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: aload #10
    //   70: invokevirtual toString : ()Ljava/lang/String;
    //   73: astore #11
    //   75: aload_2
    //   76: aload_3
    //   77: getstatic com/android/internal/R$styleable.AndroidManifestActivity : [I
    //   80: invokevirtual obtainAttributes : (Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
    //   83: astore #10
    //   85: aload #8
    //   87: aload #11
    //   89: aload_0
    //   90: aload_1
    //   91: aload #10
    //   93: iload #4
    //   95: iload #5
    //   97: aload #6
    //   99: bipush #30
    //   101: bipush #17
    //   103: bipush #42
    //   105: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   108: iconst_5
    //   109: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   112: iconst_2
    //   113: iconst_1
    //   114: bipush #23
    //   116: iconst_3
    //   117: bipush #7
    //   119: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   122: bipush #44
    //   124: bipush #48
    //   126: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   129: invokestatic parseMainComponent : (Landroid/content/pm/parsing/component/ParsedMainComponent;Ljava/lang/String;[Ljava/lang/String;Landroid/content/pm/parsing/ParsingPackage;Landroid/content/res/TypedArray;IZLandroid/content/pm/parsing/result/ParseInput;IILjava/lang/Integer;Ljava/lang/Integer;IIIILjava/lang/Integer;ILjava/lang/Integer;)Landroid/content/pm/parsing/result/ParseResult;
    //   132: astore_0
    //   133: aload_0
    //   134: invokeinterface isError : ()Z
    //   139: istore #5
    //   141: iload #5
    //   143: ifeq -> 153
    //   146: aload #10
    //   148: invokevirtual recycle : ()V
    //   151: aload_0
    //   152: areturn
    //   153: iload #9
    //   155: ifeq -> 215
    //   158: aload_1
    //   159: invokeinterface isCantSaveState : ()Z
    //   164: ifeq -> 215
    //   167: aload #8
    //   169: invokevirtual getProcessName : ()Ljava/lang/String;
    //   172: astore_0
    //   173: aload_0
    //   174: aload #7
    //   176: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   179: ifeq -> 204
    //   182: aload #6
    //   184: ldc_w 'Heavy-weight applications can not have receivers in main process'
    //   187: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   192: astore_0
    //   193: aload #10
    //   195: invokevirtual recycle : ()V
    //   198: aload_0
    //   199: areturn
    //   200: astore_0
    //   201: goto -> 208
    //   204: goto -> 215
    //   207: astore_0
    //   208: goto -> 1003
    //   211: astore_0
    //   212: goto -> 1003
    //   215: aload #6
    //   217: astore_0
    //   218: aload #10
    //   220: iconst_0
    //   221: iconst_0
    //   222: invokevirtual getResourceId : (II)I
    //   225: istore #4
    //   227: aload #8
    //   229: iload #4
    //   231: putfield theme : I
    //   234: aload #8
    //   236: aload #10
    //   238: bipush #26
    //   240: aload_1
    //   241: invokeinterface getUiOptions : ()I
    //   246: invokevirtual getInt : (II)I
    //   249: putfield uiOptions : I
    //   252: aload #8
    //   254: aload #8
    //   256: getfield flags : I
    //   259: bipush #64
    //   261: bipush #19
    //   263: aload_1
    //   264: invokeinterface isAllowTaskReparenting : ()Z
    //   269: aload #10
    //   271: invokestatic flag : (IIZLandroid/content/res/TypedArray;)I
    //   274: bipush #8
    //   276: bipush #18
    //   278: aload #10
    //   280: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   283: ior
    //   284: iconst_4
    //   285: bipush #11
    //   287: aload #10
    //   289: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   292: ior
    //   293: bipush #32
    //   295: bipush #13
    //   297: aload #10
    //   299: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   302: ior
    //   303: sipush #256
    //   306: bipush #22
    //   308: aload #10
    //   310: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   313: ior
    //   314: iconst_2
    //   315: bipush #10
    //   317: aload #10
    //   319: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   322: ior
    //   323: sipush #2048
    //   326: bipush #24
    //   328: aload #10
    //   330: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   333: ior
    //   334: iconst_1
    //   335: bipush #9
    //   337: aload #10
    //   339: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   342: ior
    //   343: sipush #128
    //   346: bipush #21
    //   348: aload #10
    //   350: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   353: ior
    //   354: sipush #1024
    //   357: bipush #39
    //   359: aload #10
    //   361: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   364: ior
    //   365: sipush #1024
    //   368: bipush #29
    //   370: aload #10
    //   372: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   375: ior
    //   376: bipush #16
    //   378: bipush #12
    //   380: aload #10
    //   382: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   385: ior
    //   386: ldc_w 536870912
    //   389: bipush #58
    //   391: aload #10
    //   393: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   396: ior
    //   397: ior
    //   398: putfield flags : I
    //   401: iload #9
    //   403: ifne -> 808
    //   406: aload #8
    //   408: aload #8
    //   410: getfield flags : I
    //   413: sipush #512
    //   416: bipush #25
    //   418: aload_1
    //   419: invokeinterface isBaseHardwareAccelerated : ()Z
    //   424: aload #10
    //   426: invokestatic flag : (IIZLandroid/content/res/TypedArray;)I
    //   429: ldc_w -2147483648
    //   432: bipush #31
    //   434: aload #10
    //   436: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   439: ior
    //   440: ldc_w 262144
    //   443: bipush #57
    //   445: aload #10
    //   447: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   450: ior
    //   451: sipush #8192
    //   454: bipush #35
    //   456: aload #10
    //   458: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   461: ior
    //   462: sipush #4096
    //   465: bipush #36
    //   467: aload #10
    //   469: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   472: ior
    //   473: sipush #16384
    //   476: bipush #37
    //   478: aload #10
    //   480: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   483: ior
    //   484: ldc_w 8388608
    //   487: bipush #51
    //   489: aload #10
    //   491: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   494: ior
    //   495: ldc_w 4194304
    //   498: bipush #41
    //   500: aload #10
    //   502: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   505: ior
    //   506: ldc_w 16777216
    //   509: bipush #52
    //   511: aload #10
    //   513: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   516: ior
    //   517: ldc_w 33554432
    //   520: bipush #56
    //   522: aload #10
    //   524: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   527: ior
    //   528: ior
    //   529: putfield flags : I
    //   532: aload #8
    //   534: aload #8
    //   536: getfield privateFlags : I
    //   539: iconst_1
    //   540: bipush #54
    //   542: aload #10
    //   544: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   547: ior
    //   548: putfield privateFlags : I
    //   551: aload #8
    //   553: aload #10
    //   555: bipush #49
    //   557: iconst_0
    //   558: invokevirtual getInt : (II)I
    //   561: putfield colorMode : I
    //   564: aload #8
    //   566: aload #10
    //   568: bipush #33
    //   570: iconst_0
    //   571: invokevirtual getInt : (II)I
    //   574: putfield documentLaunchMode : I
    //   577: aload #8
    //   579: aload #10
    //   581: bipush #14
    //   583: iconst_0
    //   584: invokevirtual getInt : (II)I
    //   587: putfield launchMode : I
    //   590: aload #8
    //   592: aload #10
    //   594: bipush #38
    //   596: iconst_0
    //   597: invokevirtual getInt : (II)I
    //   600: putfield lockTaskLaunchMode : I
    //   603: aload #8
    //   605: aload #10
    //   607: bipush #34
    //   609: invokestatic getDefaultAppRecentsLimitStatic : ()I
    //   612: invokevirtual getInt : (II)I
    //   615: putfield maxRecents : I
    //   618: aload #8
    //   620: aload #10
    //   622: bipush #32
    //   624: iconst_0
    //   625: invokevirtual getInteger : (II)I
    //   628: putfield persistableMode : I
    //   631: aload #8
    //   633: aload #10
    //   635: bipush #43
    //   637: invokevirtual getString : (I)Ljava/lang/String;
    //   640: putfield requestedVrComponent : Ljava/lang/String;
    //   643: aload #8
    //   645: aload #10
    //   647: bipush #46
    //   649: iconst_m1
    //   650: invokevirtual getInt : (II)I
    //   653: putfield rotationAnimation : I
    //   656: aload #8
    //   658: aload #10
    //   660: bipush #20
    //   662: iconst_0
    //   663: invokevirtual getInt : (II)I
    //   666: putfield softInputMode : I
    //   669: aload #8
    //   671: aload #10
    //   673: bipush #16
    //   675: iconst_0
    //   676: invokevirtual getInt : (II)I
    //   679: aload #10
    //   681: bipush #47
    //   683: iconst_0
    //   684: invokevirtual getInt : (II)I
    //   687: invokestatic getActivityConfigChanges : (II)I
    //   690: putfield configChanges : I
    //   693: aload #10
    //   695: bipush #15
    //   697: iconst_m1
    //   698: invokevirtual getInt : (II)I
    //   701: istore #4
    //   703: aload_1
    //   704: aload #10
    //   706: iload #4
    //   708: invokestatic getActivityResizeMode : (Landroid/content/pm/parsing/ParsingPackage;Landroid/content/res/TypedArray;I)I
    //   711: istore #12
    //   713: aload #8
    //   715: iload #4
    //   717: putfield screenOrientation : I
    //   720: aload #8
    //   722: iload #12
    //   724: putfield resizeMode : I
    //   727: aload #10
    //   729: bipush #50
    //   731: invokevirtual hasValue : (I)Z
    //   734: ifeq -> 764
    //   737: aload #10
    //   739: bipush #50
    //   741: invokevirtual getType : (I)I
    //   744: iconst_4
    //   745: if_icmpne -> 764
    //   748: aload #8
    //   750: iload #12
    //   752: aload #10
    //   754: bipush #50
    //   756: fconst_0
    //   757: invokevirtual getFloat : (IF)F
    //   760: invokevirtual setMaxAspectRatio : (IF)Landroid/content/pm/parsing/component/ParsedActivity;
    //   763: pop
    //   764: aload #10
    //   766: bipush #53
    //   768: invokevirtual hasValue : (I)Z
    //   771: ifeq -> 801
    //   774: aload #10
    //   776: bipush #53
    //   778: invokevirtual getType : (I)I
    //   781: iconst_4
    //   782: if_icmpne -> 801
    //   785: aload #8
    //   787: iload #12
    //   789: aload #10
    //   791: bipush #53
    //   793: fconst_0
    //   794: invokevirtual getFloat : (IF)F
    //   797: invokevirtual setMinAspectRatio : (IF)Landroid/content/pm/parsing/component/ParsedActivity;
    //   800: pop
    //   801: goto -> 841
    //   804: astore_0
    //   805: goto -> 1003
    //   808: aload #8
    //   810: iconst_0
    //   811: putfield launchMode : I
    //   814: aload #8
    //   816: iconst_0
    //   817: putfield configChanges : I
    //   820: aload #8
    //   822: aload #8
    //   824: getfield flags : I
    //   827: ldc_w 1073741824
    //   830: bipush #28
    //   832: aload #10
    //   834: invokestatic flag : (IILandroid/content/res/TypedArray;)I
    //   837: ior
    //   838: putfield flags : I
    //   841: aload #10
    //   843: bipush #8
    //   845: sipush #1024
    //   848: invokevirtual getNonConfigurationString : (II)Ljava/lang/String;
    //   851: astore #13
    //   853: aload #7
    //   855: aload_1
    //   856: invokeinterface getTaskAffinity : ()Ljava/lang/String;
    //   861: aload #13
    //   863: aload_0
    //   864: invokestatic buildTaskAffinityName : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/CharSequence;Landroid/content/pm/parsing/result/ParseInput;)Landroid/content/pm/parsing/result/ParseResult;
    //   867: astore #7
    //   869: aload #7
    //   871: invokeinterface isError : ()Z
    //   876: istore #5
    //   878: iload #5
    //   880: ifeq -> 903
    //   883: aload_0
    //   884: aload #7
    //   886: invokeinterface error : (Landroid/content/pm/parsing/result/ParseResult;)Landroid/content/pm/parsing/result/ParseResult;
    //   891: astore_0
    //   892: aload #10
    //   894: invokevirtual recycle : ()V
    //   897: aload_0
    //   898: areturn
    //   899: astore_0
    //   900: goto -> 805
    //   903: aload #8
    //   905: aload #7
    //   907: invokeinterface getResult : ()Ljava/lang/Object;
    //   912: checkcast java/lang/String
    //   915: putfield taskAffinity : Ljava/lang/String;
    //   918: aload #10
    //   920: bipush #45
    //   922: iconst_0
    //   923: invokevirtual getBoolean : (IZ)Z
    //   926: istore #5
    //   928: iload #5
    //   930: ifeq -> 954
    //   933: aload #8
    //   935: aload #8
    //   937: getfield flags : I
    //   940: ldc 1048576
    //   942: ior
    //   943: putfield flags : I
    //   946: aload_1
    //   947: iconst_1
    //   948: invokeinterface setVisibleToInstantApps : (Z)Landroid/content/pm/parsing/ParsingPackage;
    //   953: pop
    //   954: aload #8
    //   956: aload_1
    //   957: aload #11
    //   959: aload_3
    //   960: aload_2
    //   961: aload #10
    //   963: iload #9
    //   965: iconst_0
    //   966: iload #5
    //   968: aload #6
    //   970: bipush #27
    //   972: iconst_4
    //   973: bipush #6
    //   975: invokestatic parseActivityOrAlias : (Landroid/content/pm/parsing/component/ParsedActivity;Landroid/content/pm/parsing/ParsingPackage;Ljava/lang/String;Landroid/content/res/XmlResourceParser;Landroid/content/res/Resources;Landroid/content/res/TypedArray;ZZZLandroid/content/pm/parsing/result/ParseInput;III)Landroid/content/pm/parsing/result/ParseResult;
    //   978: astore_0
    //   979: aload #10
    //   981: invokevirtual recycle : ()V
    //   984: aload_0
    //   985: areturn
    //   986: astore_0
    //   987: goto -> 1003
    //   990: astore_0
    //   991: goto -> 1003
    //   994: astore_0
    //   995: goto -> 1003
    //   998: astore_0
    //   999: goto -> 1003
    //   1002: astore_1
    //   1003: aload #10
    //   1005: invokevirtual recycle : ()V
    //   1008: aload_0
    //   1009: athrow
    // Exception table:
    //   from	to	target	type
    //   85	141	998	finally
    //   158	173	211	finally
    //   173	182	207	finally
    //   182	193	200	finally
    //   218	227	994	finally
    //   227	401	990	finally
    //   406	669	804	finally
    //   669	703	804	finally
    //   703	748	899	finally
    //   748	764	899	finally
    //   764	785	899	finally
    //   785	801	899	finally
    //   808	841	990	finally
    //   841	853	990	finally
    //   853	878	990	finally
    //   883	892	899	finally
    //   903	928	990	finally
    //   933	954	899	finally
    //   954	979	986	finally
  }
  
  private static ParseResult<ParsedIntentInfo> parseIntentFilter(ParsingPackage paramParsingPackage, ParsedActivity paramParsedActivity, boolean paramBoolean1, boolean paramBoolean2, Resources paramResources, XmlResourceParser paramXmlResourceParser, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    ParseResult<ParsedIntentInfo> parseResult = ParsedMainComponentUtils.parseIntentFilter(paramParsedActivity, paramParsingPackage, paramResources, paramXmlResourceParser, paramBoolean2, true, true, paramBoolean1, true, paramParseInput);
    if (parseResult.isError())
      return paramParseInput.error(parseResult); 
    ParsedIntentInfo parsedIntentInfo = (ParsedIntentInfo)parseResult.getResult();
    if (parsedIntentInfo != null) {
      if (parsedIntentInfo.isVisibleToInstantApp())
        paramParsedActivity.flags |= 0x100000; 
      if (parsedIntentInfo.isImplicitlyVisibleToInstantApp())
        paramParsedActivity.flags |= 0x200000; 
    } 
    return paramParseInput.success(parsedIntentInfo);
  }
  
  private static ParseResult<ActivityInfo.WindowLayout> parseLayout(Resources paramResources, AttributeSet paramAttributeSet, ParseInput paramParseInput) {
    TypedArray typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.AndroidManifestLayout);
    int i = -1;
    float f1 = -1.0F;
    int j = -1;
    float f2 = -1.0F;
    try {
      float f;
      int k = typedArray.getType(3);
      if (k == 6) {
        f = typedArray.getFraction(3, 1, 1, -1.0F);
      } else {
        f = f1;
        if (k == 5) {
          i = typedArray.getDimensionPixelSize(3, -1);
          f = f1;
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
      int m = typedArray.getInt(0, 17);
      int n = typedArray.getDimensionPixelSize(1, -1);
      k = typedArray.getDimensionPixelSize(2, -1);
      ActivityInfo.WindowLayout windowLayout = new ActivityInfo.WindowLayout();
      this(i, f, j, f1, m, n, k);
      try {
        ParseResult<ActivityInfo.WindowLayout> parseResult = paramParseInput.success(windowLayout);
        typedArray.recycle();
        return parseResult;
      } finally {}
    } finally {}
    typedArray.recycle();
    throw paramResources;
  }
  
  private static ParseResult<ActivityInfo.WindowLayout> resolveWindowLayout(ParsedActivity paramParsedActivity, ParseInput paramParseInput) {
    if (paramParsedActivity.metaData == null || !paramParsedActivity.metaData.containsKey("android.activity_window_layout_affinity"))
      return paramParseInput.success(paramParsedActivity.windowLayout); 
    if (paramParsedActivity.windowLayout != null && paramParsedActivity.windowLayout.windowLayoutAffinity != null)
      return paramParseInput.success(paramParsedActivity.windowLayout); 
    String str = paramParsedActivity.metaData.getString("android.activity_window_layout_affinity");
    ActivityInfo.WindowLayout windowLayout2 = paramParsedActivity.windowLayout;
    ActivityInfo.WindowLayout windowLayout1 = windowLayout2;
    if (windowLayout2 == null)
      windowLayout1 = new ActivityInfo.WindowLayout(-1, -1.0F, -1, -1.0F, 0, -1, -1); 
    windowLayout1.windowLayoutAffinity = str;
    return paramParseInput.success(windowLayout1);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedActivityUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
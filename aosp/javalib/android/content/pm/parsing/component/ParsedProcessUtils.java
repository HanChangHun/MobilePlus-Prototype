package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.ArrayMap;
import android.util.AttributeSet;
import com.android.internal.R;
import com.android.internal.util.CollectionUtils;
import java.io.IOException;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

public class ParsedProcessUtils {
  private static final String TAG = "PackageParsing";
  
  private static ParseResult<Set<String>> parseAllowPermission(Set<String> paramSet, Resources paramResources, XmlResourceParser paramXmlResourceParser, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestAllowPermission);
    try {
      String str = typedArray.getNonConfigurationString(0, 0);
      Set<String> set = paramSet;
      if (str != null) {
        set = paramSet;
        if (str.equals("android.permission.INTERNET"))
          set = CollectionUtils.remove(paramSet, str); 
      } 
      typedArray.recycle();
      return paramParseInput.success(set);
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<Set<String>> parseDenyPermission(Set<String> paramSet, Resources paramResources, XmlResourceParser paramXmlResourceParser, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestDenyPermission);
    try {
      String str = typedArray.getNonConfigurationString(0, 0);
      Set<String> set = paramSet;
      if (str != null) {
        set = paramSet;
        if (str.equals("android.permission.INTERNET"))
          set = CollectionUtils.add(paramSet, str); 
      } 
      typedArray.recycle();
      return paramParseInput.success(set);
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsedProcess> parseProcess(Set<String> paramSet, String[] paramArrayOfString, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    // Byte code:
    //   0: new android/content/pm/parsing/component/ParsedProcess
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #7
    //   9: aload_3
    //   10: aload #4
    //   12: getstatic com/android/internal/R$styleable.AndroidManifestProcess : [I
    //   15: invokevirtual obtainAttributes : (Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
    //   18: astore #8
    //   20: aload_0
    //   21: ifnull -> 42
    //   24: new android/util/ArraySet
    //   27: astore #9
    //   29: aload #9
    //   31: aload_0
    //   32: invokespecial <init> : (Ljava/util/Collection;)V
    //   35: aload #7
    //   37: aload #9
    //   39: putfield deniedPermissions : Ljava/util/Set;
    //   42: aload #7
    //   44: aload #8
    //   46: iconst_0
    //   47: iconst_0
    //   48: invokevirtual getNonConfigurationString : (II)Ljava/lang/String;
    //   51: putfield name : Ljava/lang/String;
    //   54: aload_2
    //   55: invokeinterface getPackageName : ()Ljava/lang/String;
    //   60: aload_2
    //   61: invokeinterface getPackageName : ()Ljava/lang/String;
    //   66: aload #7
    //   68: getfield name : Ljava/lang/String;
    //   71: iload #5
    //   73: aload_1
    //   74: aload #6
    //   76: invokestatic buildProcessName : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/CharSequence;I[Ljava/lang/String;Landroid/content/pm/parsing/result/ParseInput;)Landroid/content/pm/parsing/result/ParseResult;
    //   79: astore_0
    //   80: aload_0
    //   81: invokeinterface isError : ()Z
    //   86: ifeq -> 105
    //   89: aload #6
    //   91: aload_0
    //   92: invokeinterface error : (Landroid/content/pm/parsing/result/ParseResult;)Landroid/content/pm/parsing/result/ParseResult;
    //   97: astore_0
    //   98: aload #8
    //   100: invokevirtual recycle : ()V
    //   103: aload_0
    //   104: areturn
    //   105: aload #7
    //   107: aload_0
    //   108: invokeinterface getResult : ()Ljava/lang/Object;
    //   113: checkcast java/lang/String
    //   116: putfield name : Ljava/lang/String;
    //   119: aload #7
    //   121: getfield name : Ljava/lang/String;
    //   124: ifnull -> 441
    //   127: aload #7
    //   129: getfield name : Ljava/lang/String;
    //   132: invokevirtual length : ()I
    //   135: ifgt -> 141
    //   138: goto -> 441
    //   141: aload #7
    //   143: aload #8
    //   145: iconst_1
    //   146: iconst_m1
    //   147: invokevirtual getInt : (II)I
    //   150: putfield gwpAsanMode : I
    //   153: aload #8
    //   155: invokevirtual recycle : ()V
    //   158: aload #4
    //   160: invokeinterface getDepth : ()I
    //   165: istore #10
    //   167: aload #4
    //   169: invokeinterface next : ()I
    //   174: istore #5
    //   176: iload #5
    //   178: iconst_1
    //   179: if_icmpeq -> 431
    //   182: iload #5
    //   184: iconst_3
    //   185: if_icmpne -> 206
    //   188: aload #4
    //   190: invokeinterface getDepth : ()I
    //   195: iload #10
    //   197: if_icmple -> 203
    //   200: goto -> 206
    //   203: goto -> 431
    //   206: iload #5
    //   208: iconst_3
    //   209: if_icmpeq -> 428
    //   212: iload #5
    //   214: iconst_4
    //   215: if_icmpne -> 221
    //   218: goto -> 167
    //   221: aload #4
    //   223: invokeinterface getName : ()Ljava/lang/String;
    //   228: astore_0
    //   229: aload_0
    //   230: invokevirtual hashCode : ()I
    //   233: istore #5
    //   235: iload #5
    //   237: ldc -1239165229
    //   239: if_icmpeq -> 267
    //   242: iload #5
    //   244: ldc 1658008624
    //   246: if_icmpeq -> 252
    //   249: goto -> 282
    //   252: aload_0
    //   253: ldc 'deny-permission'
    //   255: invokevirtual equals : (Ljava/lang/Object;)Z
    //   258: ifeq -> 249
    //   261: iconst_0
    //   262: istore #5
    //   264: goto -> 285
    //   267: aload_0
    //   268: ldc 'allow-permission'
    //   270: invokevirtual equals : (Ljava/lang/Object;)Z
    //   273: ifeq -> 249
    //   276: iconst_1
    //   277: istore #5
    //   279: goto -> 285
    //   282: iconst_m1
    //   283: istore #5
    //   285: iload #5
    //   287: ifeq -> 360
    //   290: iload #5
    //   292: iconst_1
    //   293: if_icmpeq -> 310
    //   296: ldc '<process>'
    //   298: aload_2
    //   299: aload #4
    //   301: aload #6
    //   303: invokestatic unknownTag : (Ljava/lang/String;Landroid/content/pm/parsing/ParsingPackage;Landroid/content/res/XmlResourceParser;Landroid/content/pm/parsing/result/ParseInput;)Landroid/content/pm/parsing/result/ParseResult;
    //   306: astore_0
    //   307: goto -> 407
    //   310: aload #7
    //   312: getfield deniedPermissions : Ljava/util/Set;
    //   315: aload_3
    //   316: aload #4
    //   318: aload #6
    //   320: invokestatic parseAllowPermission : (Ljava/util/Set;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;Landroid/content/pm/parsing/result/ParseInput;)Landroid/content/pm/parsing/result/ParseResult;
    //   323: astore #8
    //   325: aload #8
    //   327: astore_1
    //   328: aload_1
    //   329: astore_0
    //   330: aload #8
    //   332: invokeinterface isSuccess : ()Z
    //   337: ifeq -> 407
    //   340: aload #7
    //   342: aload #8
    //   344: invokeinterface getResult : ()Ljava/lang/Object;
    //   349: checkcast java/util/Set
    //   352: putfield deniedPermissions : Ljava/util/Set;
    //   355: aload_1
    //   356: astore_0
    //   357: goto -> 407
    //   360: aload #7
    //   362: getfield deniedPermissions : Ljava/util/Set;
    //   365: aload_3
    //   366: aload #4
    //   368: aload #6
    //   370: invokestatic parseDenyPermission : (Ljava/util/Set;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;Landroid/content/pm/parsing/result/ParseInput;)Landroid/content/pm/parsing/result/ParseResult;
    //   373: astore #8
    //   375: aload #8
    //   377: astore_1
    //   378: aload_1
    //   379: astore_0
    //   380: aload #8
    //   382: invokeinterface isSuccess : ()Z
    //   387: ifeq -> 407
    //   390: aload #7
    //   392: aload #8
    //   394: invokeinterface getResult : ()Ljava/lang/Object;
    //   399: checkcast java/util/Set
    //   402: putfield deniedPermissions : Ljava/util/Set;
    //   405: aload_1
    //   406: astore_0
    //   407: aload_0
    //   408: invokeinterface isError : ()Z
    //   413: ifeq -> 425
    //   416: aload #6
    //   418: aload_0
    //   419: invokeinterface error : (Landroid/content/pm/parsing/result/ParseResult;)Landroid/content/pm/parsing/result/ParseResult;
    //   424: areturn
    //   425: goto -> 167
    //   428: goto -> 167
    //   431: aload #6
    //   433: aload #7
    //   435: invokeinterface success : (Ljava/lang/Object;)Landroid/content/pm/parsing/result/ParseResult;
    //   440: areturn
    //   441: aload #6
    //   443: ldc '<process> does not specify android:process'
    //   445: invokeinterface error : (Ljava/lang/String;)Landroid/content/pm/parsing/result/ParseResult;
    //   450: astore_0
    //   451: aload #8
    //   453: invokevirtual recycle : ()V
    //   456: aload_0
    //   457: areturn
    //   458: astore_0
    //   459: goto -> 463
    //   462: astore_0
    //   463: aload #8
    //   465: invokevirtual recycle : ()V
    //   468: aload_0
    //   469: athrow
    // Exception table:
    //   from	to	target	type
    //   24	42	462	finally
    //   42	54	462	finally
    //   54	98	462	finally
    //   105	138	462	finally
    //   141	153	462	finally
    //   441	451	458	finally
  }
  
  public static ParseResult<ArrayMap<String, ParsedProcess>> parseProcesses(String[] paramArrayOfString, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    ArrayMap arrayMap = new ArrayMap();
    int i = paramXmlResourceParser.getDepth();
    Set<String> set = null;
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        ParseResult<Set<String>> parseResult;
        if (j == 3 || j == 4)
          continue; 
        String str = paramXmlResourceParser.getName();
        j = -1;
        int k = str.hashCode();
        if (k != -1239165229) {
          if (k != -309518737) {
            if (k == 1658008624 && str.equals("deny-permission"))
              j = 0; 
          } else if (str.equals("process")) {
            j = 2;
          } 
        } else if (str.equals("allow-permission")) {
          j = 1;
        } 
        if (j != 0) {
          if (j != 1) {
            if (j != 2) {
              parseResult = ParsingUtils.unknownTag("<processes>", paramParsingPackage, paramXmlResourceParser, paramParseInput);
            } else {
              ParseResult<ParsedProcess> parseResult2 = parseProcess(set, paramArrayOfString, paramParsingPackage, paramResources, paramXmlResourceParser, paramInt, paramParseInput);
              ParseResult<ParsedProcess> parseResult1 = parseResult2;
              if (parseResult2.isSuccess()) {
                ParsedProcess parsedProcess = (ParsedProcess)parseResult2.getResult();
                if (arrayMap.put(parsedProcess.name, parsedProcess) != null) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("<process> specified existing name '");
                  stringBuilder.append(parsedProcess.name);
                  stringBuilder.append("'");
                  parseResult = paramParseInput.error(stringBuilder.toString());
                } 
              } 
            } 
          } else {
            parseResult = parseAllowPermission(set, paramResources, paramXmlResourceParser, paramParseInput);
            if (parseResult.isSuccess())
              set = (Set<String>)parseResult.getResult(); 
          } 
        } else {
          parseResult = parseDenyPermission(set, paramResources, paramXmlResourceParser, paramParseInput);
          if (parseResult.isSuccess())
            set = (Set<String>)parseResult.getResult(); 
        } 
        if (parseResult.isError())
          return paramParseInput.error(parseResult); 
        continue;
      } 
      break;
    } 
    return paramParseInput.success(arrayMap);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedProcessUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
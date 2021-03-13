package android.content.pm.parsing.component;

import android.content.pm.PathPermission;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.AttributeSet;
import android.util.Slog;
import com.android.internal.R;
import java.io.IOException;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParserException;

public class ParsedProviderUtils {
  private static final String TAG = "PackageParsing";
  
  private static ParseResult<ParsedProvider> parseGrantUriPermission(ParsedProvider paramParsedProvider, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, ParseInput paramParseInput) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestGrantUriPermission);
    try {
      PatternMatcher[] arrayOfPatternMatcher;
      PatternMatcher patternMatcher;
      String str1 = paramXmlResourceParser.getName();
      paramResources = null;
      String str2 = typedArray.getNonConfigurationString(2, 0);
      if (str2 != null) {
        patternMatcher = new PatternMatcher();
        this(str2, 2);
      } else {
        str2 = typedArray.getNonConfigurationString(1, 0);
        if (str2 != null) {
          patternMatcher = new PatternMatcher(str2, 1);
        } else {
          str2 = typedArray.getNonConfigurationString(0, 0);
          if (str2 != null)
            patternMatcher = new PatternMatcher(str2, 0); 
        } 
      } 
      if (patternMatcher != null) {
        if (paramParsedProvider.uriPermissionPatterns == null) {
          paramParsedProvider.uriPermissionPatterns = new PatternMatcher[1];
          paramParsedProvider.uriPermissionPatterns[0] = patternMatcher;
        } else {
          int i = paramParsedProvider.uriPermissionPatterns.length;
          arrayOfPatternMatcher = new PatternMatcher[i + 1];
          System.arraycopy(paramParsedProvider.uriPermissionPatterns, 0, arrayOfPatternMatcher, 0, i);
          arrayOfPatternMatcher[i] = patternMatcher;
          paramParsedProvider.uriPermissionPatterns = arrayOfPatternMatcher;
        } 
        paramParsedProvider.grantUriPermissions = true;
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Unknown element under <path-permission>: ");
        stringBuilder.append(str1);
        stringBuilder.append(" at ");
        stringBuilder.append(arrayOfPatternMatcher.getBaseCodePath());
        stringBuilder.append(" ");
        stringBuilder.append(paramXmlResourceParser.getPositionDescription());
        Slog.w("PackageParsing", stringBuilder.toString());
      } 
      return paramParseInput.success(paramParsedProvider);
    } finally {
      typedArray.recycle();
    } 
  }
  
  private static ParseResult<ParsedProvider> parsePathPermission(ParsedProvider paramParsedProvider, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, ParseInput paramParseInput) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestPathPermission);
    try {
      PathPermission[] arrayOfPathPermission;
      PathPermission pathPermission;
      String str2 = paramXmlResourceParser.getName();
      String str3 = typedArray.getNonConfigurationString(0, 0);
      String str4 = typedArray.getNonConfigurationString(1, 0);
      String str1 = str4;
      if (str4 == null)
        str1 = str3; 
      String str5 = typedArray.getNonConfigurationString(2, 0);
      str4 = str5;
      if (str5 == null)
        str4 = str3; 
      int i = 0;
      str3 = str1;
      if (str1 != null) {
        str3 = str1.intern();
        i = 1;
      } 
      str5 = str4;
      if (str4 != null) {
        str5 = str4.intern();
        i = 1;
      } 
      if (!i) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("No readPermission or writePermission for <path-permission>: ");
        stringBuilder.append(str2);
        stringBuilder.append(" at ");
        stringBuilder.append(paramParsingPackage.getBaseCodePath());
        stringBuilder.append(" ");
        stringBuilder.append(paramXmlResourceParser.getPositionDescription());
        Slog.w("PackageParsing", stringBuilder.toString());
        parseResult = paramParseInput.success(paramParsedProvider);
        return parseResult;
      } 
      str1 = null;
      str4 = typedArray.getNonConfigurationString(6, 0);
      if (str4 != null) {
        pathPermission = new PathPermission();
        this(str4, 3, str3, str5);
      } else {
        str4 = typedArray.getNonConfigurationString(5, 0);
        if (str4 != null) {
          pathPermission = new PathPermission(str4, 2, str3, str5);
        } else {
          str4 = typedArray.getNonConfigurationString(4, 0);
          if (str4 != null) {
            pathPermission = new PathPermission(str4, 1, str3, str5);
          } else {
            str4 = typedArray.getNonConfigurationString(3, 0);
            if (str4 != null)
              pathPermission = new PathPermission(str4, 0, str3, str5); 
          } 
        } 
      } 
      if (pathPermission != null) {
        if (((ParsedProvider)parseResult).pathPermissions == null) {
          ((ParsedProvider)parseResult).pathPermissions = new PathPermission[1];
          ((ParsedProvider)parseResult).pathPermissions[0] = pathPermission;
        } else {
          i = ((ParsedProvider)parseResult).pathPermissions.length;
          arrayOfPathPermission = new PathPermission[i + 1];
          System.arraycopy(((ParsedProvider)parseResult).pathPermissions, 0, arrayOfPathPermission, 0, i);
          arrayOfPathPermission[i] = pathPermission;
          ((ParsedProvider)parseResult).pathPermissions = arrayOfPathPermission;
        } 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("No path, pathPrefix, or pathPattern for <path-permission>: ");
        stringBuilder.append(str2);
        stringBuilder.append(" at ");
        stringBuilder.append(arrayOfPathPermission.getBaseCodePath());
        stringBuilder.append(" ");
        stringBuilder.append(paramXmlResourceParser.getPositionDescription());
        Slog.w("PackageParsing", stringBuilder.toString());
      } 
      ParseResult<ParsedProvider> parseResult = paramParseInput.success(parseResult);
      return parseResult;
    } finally {
      typedArray.recycle();
    } 
  }
  
  public static ParseResult<ParsedProvider> parseProvider(String[] paramArrayOfString, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, boolean paramBoolean, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    int i = paramParsingPackage.getTargetSdkVersion();
    String str1 = paramParsingPackage.getPackageName();
    ParsedProvider parsedProvider = new ParsedProvider();
    String str2 = paramXmlResourceParser.getName();
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestProvider);
    try {
      ParseResult<ParsedProvider> parseResult = ParsedMainComponentUtils.parseMainComponent(parsedProvider, str2, paramArrayOfString, paramParsingPackage, typedArray, paramInt, paramBoolean, paramParseInput, 17, 14, Integer.valueOf(18), Integer.valueOf(6), 1, 0, 15, 2, Integer.valueOf(8), 19, Integer.valueOf(21));
      paramBoolean = parseResult.isError();
      if (paramBoolean) {
        typedArray.recycle();
        return parseResult;
      } 
      TypedArray typedArray1 = typedArray;
      try {
        String str = typedArray1.getNonConfigurationString(10, 0);
        if (i < 17) {
          paramBoolean = true;
        } else {
          paramBoolean = false;
        } 
        try {
          paramBoolean = typedArray1.getBoolean(7, paramBoolean);
          try {
            parsedProvider.exported = paramBoolean;
            parsedProvider.syncable = typedArray1.getBoolean(11, false);
            String str3 = typedArray1.getNonConfigurationString(3, 0);
            String str4 = typedArray1.getNonConfigurationString(4, 0);
            String str5 = str4;
            if (str4 == null)
              str5 = str3; 
            if (str5 == null) {
              try {
                parsedProvider.setReadPermission(paramParsingPackage.getPermission());
              } finally {}
            } else {
              parsedProvider.setReadPermission(str5);
            } 
            str4 = typedArray1.getNonConfigurationString(5, 0);
            str5 = str4;
            if (str4 == null)
              str5 = str3; 
            if (str5 == null) {
              parsedProvider.setWritePermission(paramParsingPackage.getPermission());
            } else {
              parsedProvider.setWritePermission(str5);
            } 
            parsedProvider.grantUriPermissions = typedArray1.getBoolean(13, false);
            parsedProvider.forceUriPermissions = typedArray1.getBoolean(22, false);
            parsedProvider.multiProcess = typedArray1.getBoolean(9, false);
            parsedProvider.initOrder = typedArray1.getInt(12, 0);
            parsedProvider.flags |= ComponentParseUtils.flag(1073741824, 16, typedArray1);
            paramBoolean = typedArray1.getBoolean(20, false);
            if (paramBoolean) {
              parsedProvider.flags |= 0x100000;
              try {
                paramParsingPackage.setVisibleToInstantApps(true);
              } finally {}
            } 
            typedArray1.recycle();
            if (paramParsingPackage.isCantSaveState() && Objects.equals(parsedProvider.getProcessName(), str1))
              return paramParseInput.error("Heavy-weight applications can not have providers in main process"); 
            ParseInput parseInput = paramParseInput;
            if (str == null)
              return parseInput.error("<provider> does not include authorities attribute"); 
            if (str.length() <= 0)
              return parseInput.error("<provider> has empty authorities attribute"); 
            parsedProvider.setAuthority(str);
            return parseProviderTags(paramParsingPackage, str2, paramResources, paramXmlResourceParser, paramBoolean, parsedProvider, paramParseInput);
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    while (true) {
      typedArray.recycle();
      throw paramArrayOfString;
    } 
  }
  
  private static ParseResult<ParsedProvider> parseProviderTags(ParsingPackage paramParsingPackage, String paramString, Resources paramResources, XmlResourceParser paramXmlResourceParser, boolean paramBoolean, ParsedProvider paramParsedProvider, ParseInput paramParseInput) throws XmlPullParserException, IOException {
    int i = paramXmlResourceParser.getDepth();
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        ParseResult<ParsedIntentInfo> parseResult;
        if (j != 2)
          continue; 
        String str = paramXmlResourceParser.getName();
        j = -1;
        switch (str.hashCode()) {
          case 636171383:
            if (str.equals("path-permission"))
              j = 3; 
            break;
          case -1029793847:
            if (str.equals("intent-filter"))
              j = 0; 
            break;
          case -1115949454:
            if (str.equals("meta-data"))
              j = 1; 
            break;
          case -1814617695:
            if (str.equals("grant-uri-permission"))
              j = 2; 
            break;
        } 
        if (j != 0) {
          if (j != 1) {
            if (j != 2) {
              if (j != 3) {
                parseResult = ParsingUtils.unknownTag(paramString, paramParsingPackage, paramXmlResourceParser, paramParseInput);
              } else {
                ParseResult<ParsedProvider> parseResult1 = parsePathPermission(paramParsedProvider, paramParsingPackage, paramResources, paramXmlResourceParser, paramParseInput);
              } 
            } else {
              ParseResult<ParsedProvider> parseResult1 = parseGrantUriPermission(paramParsedProvider, paramParsingPackage, paramResources, paramXmlResourceParser, paramParseInput);
            } 
          } else {
            ParseResult<Bundle> parseResult1 = ParsedComponentUtils.addMetaData(paramParsedProvider, paramParsingPackage, paramResources, paramXmlResourceParser, paramParseInput);
          } 
        } else {
          parseResult = ParsedMainComponentUtils.parseIntentFilter(paramParsedProvider, paramParsingPackage, paramResources, paramXmlResourceParser, paramBoolean, true, false, false, false, paramParseInput);
          if (parseResult.isSuccess()) {
            ParsedIntentInfo parsedIntentInfo = (ParsedIntentInfo)parseResult.getResult();
            paramParsedProvider.order = Math.max(parsedIntentInfo.getOrder(), paramParsedProvider.order);
            paramParsedProvider.addIntent(parsedIntentInfo);
          } 
        } 
        if (parseResult.isError())
          return paramParseInput.error(parseResult); 
        continue;
      } 
      break;
    } 
    return paramParseInput.success(paramParsedProvider);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedProviderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
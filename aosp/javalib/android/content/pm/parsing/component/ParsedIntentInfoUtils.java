package android.content.pm.parsing.component;

import android.content.IntentFilter;
import android.content.pm.PackageParser;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public class ParsedIntentInfoUtils {
  private static final String TAG = "PackageParsing";
  
  private static ParseResult<ParsedIntentInfo> parseData(ParsedIntentInfo paramParsedIntentInfo, Resources paramResources, XmlResourceParser paramXmlResourceParser, boolean paramBoolean, ParseInput paramParseInput) {
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestData);
    try {
      String str1 = typedArray.getNonConfigurationString(0, 0);
      if (str1 != null)
        try {
          paramParsedIntentInfo.addDataType(str1);
        } catch (android.content.IntentFilter.MalformedMimeTypeException malformedMimeTypeException) {
          parseResult = paramParseInput.error(malformedMimeTypeException.toString());
          return parseResult;
        }  
      str1 = typedArray.getNonConfigurationString(10, 0);
      if (str1 != null)
        parseResult.addMimeGroup(str1); 
      str1 = typedArray.getNonConfigurationString(1, 0);
      if (str1 != null)
        parseResult.addDataScheme(str1); 
      str1 = typedArray.getNonConfigurationString(7, 0);
      if (str1 != null)
        parseResult.addDataSchemeSpecificPart(str1, 0); 
      str1 = typedArray.getNonConfigurationString(8, 0);
      if (str1 != null)
        parseResult.addDataSchemeSpecificPart(str1, 1); 
      str1 = typedArray.getNonConfigurationString(9, 0);
      if (str1 != null) {
        if (!paramBoolean) {
          parseResult = paramParseInput.error("sspPattern not allowed here; ssp must be literal");
          return parseResult;
        } 
        parseResult.addDataSchemeSpecificPart(str1, 2);
      } 
      String str2 = typedArray.getNonConfigurationString(2, 0);
      str1 = typedArray.getNonConfigurationString(3, 0);
      if (str2 != null)
        parseResult.addDataAuthority(str2, str1); 
      str1 = typedArray.getNonConfigurationString(4, 0);
      if (str1 != null)
        parseResult.addDataPath(str1, 0); 
      str1 = typedArray.getNonConfigurationString(5, 0);
      if (str1 != null)
        parseResult.addDataPath(str1, 1); 
      str1 = typedArray.getNonConfigurationString(6, 0);
      if (str1 != null) {
        if (!paramBoolean) {
          parseResult = paramParseInput.error("pathPattern not allowed here; path must be literal");
          return parseResult;
        } 
        parseResult.addDataPath(str1, 2);
      } 
      str1 = typedArray.getNonConfigurationString(11, 0);
      if (str1 != null) {
        if (!paramBoolean) {
          parseResult = paramParseInput.error("pathAdvancedPattern not allowed here; path must be literal");
          return parseResult;
        } 
        parseResult.addDataPath(str1, 3);
      } 
      ParseResult<ParsedIntentInfo> parseResult = paramParseInput.success(null);
      return parseResult;
    } finally {
      typedArray.recycle();
    } 
  }
  
  public static ParseResult<ParsedIntentInfo> parseIntentInfo(String paramString, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, boolean paramBoolean1, boolean paramBoolean2, ParseInput paramParseInput) throws XmlPullParserException, IOException {
    ParseResult parseResult;
    ParsedIntentInfo parsedIntentInfo = new ParsedIntentInfo();
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestIntentFilter);
    try {
      parsedIntentInfo.setPriority(typedArray.getInt(2, 0));
      parsedIntentInfo.setOrder(typedArray.getInt(3, 0));
      TypedValue typedValue = typedArray.peekValue(0);
      if (typedValue != null) {
        parsedIntentInfo.labelRes = typedValue.resourceId;
        if (typedValue.resourceId == 0)
          parsedIntentInfo.nonLocalizedLabel = typedValue.coerceToString(); 
      } 
      if (PackageParser.sUseRoundIcon)
        parsedIntentInfo.icon = typedArray.getResourceId(7, 0); 
      if (parsedIntentInfo.icon == 0)
        parsedIntentInfo.icon = typedArray.getResourceId(1, 0); 
      if (paramBoolean2)
        parsedIntentInfo.setAutoVerify(typedArray.getBoolean(6, false)); 
      typedArray.recycle();
      int i = paramXmlResourceParser.getDepth();
      while (true) {
        int j = paramXmlResourceParser.next();
        if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
          if (j != 2)
            continue; 
          String str = paramXmlResourceParser.getName();
          j = -1;
          int k = str.hashCode();
          if (k != -1422950858) {
            if (k != 3076010) {
              if (k == 50511102 && str.equals("category"))
                j = 1; 
            } else if (str.equals("data")) {
              j = 2;
            } 
          } else if (str.equals("action")) {
            j = 0;
          } 
          if (j != 0) {
            if (j != 1) {
              if (j != 2) {
                parseResult = ParsingUtils.unknownTag("<intent-filter>", paramParsingPackage, paramXmlResourceParser, paramParseInput);
              } else {
                parseResult = parseData(parsedIntentInfo, paramResources, paramXmlResourceParser, paramBoolean1, paramParseInput);
              } 
            } else {
              str = paramXmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
              if (str == null) {
                parseResult = paramParseInput.error("No value supplied for <android:name>");
              } else if (parseResult.isEmpty()) {
                parsedIntentInfo.addCategory((String)parseResult);
                parseResult = paramParseInput.deferError("No value supplied for <android:name>", 151163173L);
              } else {
                parsedIntentInfo.addCategory((String)parseResult);
                parseResult = paramParseInput.success(null);
              } 
            } 
          } else {
            str = paramXmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
            if (str == null) {
              parseResult = paramParseInput.error("No value supplied for <android:name>");
            } else if (parseResult.isEmpty()) {
              parsedIntentInfo.addAction((String)parseResult);
              parseResult = paramParseInput.deferError("No value supplied for <android:name>", 151163173L);
            } else {
              parsedIntentInfo.addAction((String)parseResult);
              parseResult = paramParseInput.success(null);
            } 
          } 
          if (parseResult.isError())
            return paramParseInput.error(parseResult); 
          continue;
        } 
        break;
      } 
      return paramParseInput.success(parsedIntentInfo);
    } finally {
      parseResult.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedIntentInfoUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
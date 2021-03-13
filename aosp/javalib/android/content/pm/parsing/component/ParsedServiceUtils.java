package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.AttributeSet;
import com.android.internal.R;
import java.io.IOException;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParserException;

public class ParsedServiceUtils {
  private static final String TAG = "PackageParsing";
  
  public static ParseResult<ParsedService> parseService(String[] paramArrayOfString, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, int paramInt, boolean paramBoolean, ParseInput paramParseInput) throws XmlPullParserException, IOException {
    ParseResult<ParsedIntentInfo> parseResult;
    String str1 = paramParsingPackage.getPackageName();
    ParsedService parsedService = new ParsedService();
    String str2 = paramXmlResourceParser.getName();
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestService);
    String str3 = str2;
    try {
      ParseResult<ParsedService> parseResult1 = ParsedMainComponentUtils.parseMainComponent(parsedService, str2, paramArrayOfString, paramParsingPackage, typedArray, paramInt, paramBoolean, paramParseInput, 12, 7, Integer.valueOf(13), Integer.valueOf(4), 1, 0, 8, 2, Integer.valueOf(6), 15, Integer.valueOf(17));
      paramBoolean = parseResult1.isError();
      if (paramBoolean) {
        typedArray.recycle();
        return parseResult1;
      } 
      TypedArray typedArray1 = typedArray;
      try {
        paramBoolean = typedArray1.hasValue(5);
        if (paramBoolean)
          try {
            boolean bool = typedArray1.getBoolean(5, false);
            try {
              parsedService.exported = bool;
            } finally {}
          } finally {} 
        try {
          String str = typedArray1.getNonConfigurationString(3, 0);
          if (str == null)
            str = paramParsingPackage.getPermission(); 
          parsedService.setPermission(str);
          parsedService.foregroundServiceType = typedArray1.getInt(19, 0);
          int i = parsedService.flags;
          paramInt = 1;
          int j = ComponentParseUtils.flag(1, 9, typedArray1);
          byte b = 2;
          parsedService.flags = i | j | ComponentParseUtils.flag(2, 10, typedArray1) | ComponentParseUtils.flag(4, 14, typedArray1) | ComponentParseUtils.flag(8, 18, typedArray1) | ComponentParseUtils.flag(1073741824, 11, typedArray1);
          boolean bool = typedArray1.getBoolean(16, false);
          if (bool)
            try {
              parsedService.flags |= 0x100000;
              paramParsingPackage.setVisibleToInstantApps(true);
            } finally {} 
          typedArray1.recycle();
          if (paramParsingPackage.isCantSaveState() && Objects.equals(parsedService.getProcessName(), str1))
            return paramParseInput.error("Heavy-weight applications can not have services in main process"); 
          ParseInput parseInput = paramParseInput;
          j = paramXmlResourceParser.getDepth();
          while (true) {
            ParsingPackage parsingPackage = paramParsingPackage;
            i = paramXmlResourceParser.next();
            if (i != paramInt && (i != 3 || paramXmlResourceParser.getDepth() > j)) {
              if (i != b)
                continue; 
              String str4 = paramXmlResourceParser.getName();
              i = -1;
              int k = str4.hashCode();
              if (k != -1115949454) {
                if (k == -1029793847 && str4.equals("intent-filter"))
                  i = 0; 
              } else if (str4.equals("meta-data")) {
                i = paramInt;
              } 
              if (i != 0) {
                ParseResult<Bundle> parseResult2;
                if (i != paramInt) {
                  parseResult2 = ParsingUtils.unknownTag(str3, parsingPackage, paramXmlResourceParser, parseInput);
                } else {
                  parseResult2 = ParsedComponentUtils.addMetaData(parsedService, (ParsingPackage)parseResult2, paramResources, paramXmlResourceParser, parseInput);
                } 
              } else {
                parseResult = ParsedMainComponentUtils.parseIntentFilter(parsedService, paramParsingPackage, paramResources, paramXmlResourceParser, bool, true, false, false, false, paramParseInput);
                if (parseResult.isSuccess()) {
                  ParsedIntentInfo parsedIntentInfo = (ParsedIntentInfo)parseResult.getResult();
                  parsedService.order = Math.max(parsedIntentInfo.getOrder(), parsedService.order);
                  parsedService.addIntent(parsedIntentInfo);
                } 
              } 
              if (parseResult.isError())
                return parseInput.error(parseResult); 
              continue;
            } 
            break;
          } 
          if (!paramBoolean) {
            if (parsedService.getIntents().size() <= 0)
              paramInt = 0; 
            parsedService.exported = paramInt;
          } 
          return parseInput.success(parsedService);
        } finally {}
      } finally {}
    } finally {}
    while (true) {
      parseResult.recycle();
      throw paramArrayOfString;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedServiceUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Slog;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

class ParsedMainComponentUtils {
  private static final String TAG = "PackageParsing";
  
  static ParseResult<ParsedIntentInfo> parseIntentFilter(ParsedMainComponent paramParsedMainComponent, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    StringBuilder stringBuilder;
    boolean bool;
    ParseResult<ParsedIntentInfo> parseResult = ParsedIntentInfoUtils.parseIntentInfo(paramParsedMainComponent.getName(), paramParsingPackage, paramResources, paramXmlResourceParser, paramBoolean2, paramBoolean3, paramParseInput);
    if (parseResult.isError())
      return paramParseInput.error(parseResult); 
    ParsedIntentInfo parsedIntentInfo = (ParsedIntentInfo)parseResult.getResult();
    if (parsedIntentInfo.countActions() == 0 && paramBoolean5) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("No actions in ");
      stringBuilder.append(paramXmlResourceParser.getName());
      stringBuilder.append(" at ");
      stringBuilder.append(paramParsingPackage.getBaseCodePath());
      stringBuilder.append(" ");
      stringBuilder.append(paramXmlResourceParser.getPositionDescription());
      Slog.w("PackageParsing", stringBuilder.toString());
      return paramParseInput.success(null);
    } 
    if (paramBoolean1) {
      bool = true;
    } else if (paramBoolean4 && ComponentParseUtils.isImplicitlyExposedIntent(parsedIntentInfo)) {
      bool = true;
    } else {
      bool = false;
    } 
    parsedIntentInfo.setVisibilityToInstantApp(bool);
    return paramParseInput.success(stringBuilder.getResult());
  }
  
  static <Component extends ParsedMainComponent> ParseResult<Component> parseMainComponent(Component paramComponent, String paramString, String[] paramArrayOfString, ParsingPackage paramParsingPackage, TypedArray paramTypedArray, int paramInt1, boolean paramBoolean, ParseInput paramParseInput, int paramInt2, int paramInt3, Integer paramInteger1, Integer paramInteger2, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Integer paramInteger3, int paramInt8, Integer paramInteger4) {
    ParseResult<ParsedComponent> parseResult = ParsedComponentUtils.parseComponent((ParsedComponent)paramComponent, paramString, paramParsingPackage, paramTypedArray, paramBoolean, paramParseInput, paramInt2, Integer.valueOf(paramInt3), paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
    if (parseResult.isError())
      return (ParseResult)parseResult; 
    if (paramInteger1 != null) {
      ((ParsedMainComponent)paramComponent).directBootAware = paramTypedArray.getBoolean(paramInteger1.intValue(), false);
      if (paramComponent.isDirectBootAware())
        paramParsingPackage.setPartiallyDirectBootAware(true); 
    } 
    if (paramInteger2 != null)
      ((ParsedMainComponent)paramComponent).enabled = paramTypedArray.getBoolean(paramInteger2.intValue(), true); 
    if (paramInteger3 != null) {
      String str;
      if (paramParsingPackage.getTargetSdkVersion() >= 8) {
        str = paramTypedArray.getNonConfigurationString(paramInteger3.intValue(), 1024);
      } else {
        str = paramTypedArray.getNonResourceString(paramInteger3.intValue());
      } 
      ParseResult<String> parseResult1 = ComponentParseUtils.buildProcessName(paramParsingPackage.getPackageName(), paramParsingPackage.getProcessName(), str, paramInt1, paramArrayOfString, paramParseInput);
      if (parseResult1.isError())
        return paramParseInput.error(parseResult1); 
      paramComponent.setProcessName((String)parseResult1.getResult());
    } 
    if (paramInteger4 != null)
      ((ParsedMainComponent)paramComponent).splitName = paramTypedArray.getNonConfigurationString(paramInteger4.intValue(), 0); 
    return paramParseInput.success(paramComponent);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedMainComponentUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm.parsing.component;

import android.content.pm.PackageManager;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingPackageUtils;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;

class ParsedComponentUtils {
  private static final String TAG = "PackageParsing";
  
  static ParseResult<Bundle> addMetaData(ParsedComponent paramParsedComponent, ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, ParseInput paramParseInput) {
    ParseResult parseResult = ParsingPackageUtils.parseMetaData(paramParsingPackage, paramResources, paramXmlResourceParser, paramParsedComponent.metaData, paramParseInput);
    if (parseResult.isError())
      return paramParseInput.error(parseResult); 
    Bundle bundle = (Bundle)parseResult.getResult();
    paramParsedComponent.metaData = bundle;
    return paramParseInput.success(bundle);
  }
  
  static <Component extends ParsedComponent> ParseResult<Component> parseComponent(Component paramComponent, String paramString, ParsingPackage paramParsingPackage, TypedArray paramTypedArray, boolean paramBoolean, ParseInput paramParseInput, int paramInt1, Integer paramInteger, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    StringBuilder stringBuilder;
    String str2 = paramTypedArray.getNonConfigurationString(paramInt5, 0);
    if (TextUtils.isEmpty(str2)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" does not specify android:name");
      return paramParseInput.error(stringBuilder.toString());
    } 
    String str1 = paramParsingPackage.getPackageName();
    str2 = ParsingUtils.buildClassName(str1, str2);
    if (PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(str2)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" invalid android:name");
      return paramParseInput.error(stringBuilder.toString());
    } 
    stringBuilder.setName(str2);
    stringBuilder.setPackageName(str1);
    if (paramBoolean) {
      paramInt5 = paramTypedArray.getResourceId(paramInt6, 0);
    } else {
      paramInt5 = 0;
    } 
    if (paramInt5 != 0) {
      ((ParsedComponent)stringBuilder).icon = paramInt5;
      ((ParsedComponent)stringBuilder).nonLocalizedLabel = null;
    } else {
      paramInt2 = paramTypedArray.getResourceId(paramInt2, 0);
      if (paramInt2 != 0) {
        ((ParsedComponent)stringBuilder).icon = paramInt2;
        ((ParsedComponent)stringBuilder).nonLocalizedLabel = null;
      } 
    } 
    paramInt2 = paramTypedArray.getResourceId(paramInt4, 0);
    if (paramInt2 != 0)
      ((ParsedComponent)stringBuilder).logo = paramInt2; 
    paramInt1 = paramTypedArray.getResourceId(paramInt1, 0);
    if (paramInt1 != 0)
      ((ParsedComponent)stringBuilder).banner = paramInt1; 
    if (paramInteger != null)
      ((ParsedComponent)stringBuilder).descriptionRes = paramTypedArray.getResourceId(paramInteger.intValue(), 0); 
    TypedValue typedValue = paramTypedArray.peekValue(paramInt3);
    if (typedValue != null) {
      ((ParsedComponent)stringBuilder).labelRes = typedValue.resourceId;
      if (typedValue.resourceId == 0)
        ((ParsedComponent)stringBuilder).nonLocalizedLabel = typedValue.coerceToString(); 
    } 
    return paramParseInput.success(stringBuilder);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedComponentUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
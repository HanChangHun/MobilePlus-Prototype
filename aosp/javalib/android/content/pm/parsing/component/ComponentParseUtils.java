package android.content.pm.parsing.component;

import android.content.pm.PackageParser;
import android.content.pm.PackageUserState;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public class ComponentParseUtils {
  private static final String TAG = "PackageParsing";
  
  public static ParseResult<String> buildCompoundName(String paramString1, CharSequence paramCharSequence, String paramString2, ParseInput paramParseInput) {
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
        return paramParseInput.error(stringBuilder1.toString());
      } 
      String str1 = PackageParser.validateName(paramCharSequence.substring(1), false, false);
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
        return paramParseInput.error(stringBuilder1.toString());
      } 
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString1);
      stringBuilder.append((String)paramCharSequence);
      return paramParseInput.success(stringBuilder.toString());
    } 
    String str = PackageParser.validateName((String)paramCharSequence, true, false);
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
      return paramParseInput.error(stringBuilder1.toString());
    } 
    return paramParseInput.success(paramCharSequence);
  }
  
  public static ParseResult<String> buildProcessName(String paramString1, String paramString2, CharSequence paramCharSequence, int paramInt, String[] paramArrayOfString, ParseInput paramParseInput) {
    if ((paramInt & 0x2) != 0 && !"system".contentEquals(paramCharSequence)) {
      if (paramString2 != null)
        paramString1 = paramString2; 
      return paramParseInput.success(paramString1);
    } 
    if (paramArrayOfString != null)
      for (paramInt = paramArrayOfString.length - 1; paramInt >= 0; paramInt--) {
        String str = paramArrayOfString[paramInt];
        if (str.equals(paramString1) || str.equals(paramString2) || str.contentEquals(paramCharSequence))
          return paramParseInput.success(paramString1); 
      }  
    return (paramCharSequence == null || paramCharSequence.length() <= 0) ? paramParseInput.success(paramString2) : paramParseInput.success(TextUtils.safeIntern((String)buildCompoundName(paramString1, paramCharSequence, "process", paramParseInput).getResult()));
  }
  
  public static ParseResult<String> buildTaskAffinityName(String paramString1, String paramString2, CharSequence paramCharSequence, ParseInput paramParseInput) {
    return (paramCharSequence == null) ? paramParseInput.success(paramString2) : ((paramCharSequence.length() <= 0) ? paramParseInput.success(null) : buildCompoundName(paramString1, paramCharSequence, "taskAffinity", paramParseInput));
  }
  
  public static int flag(int paramInt1, int paramInt2, TypedArray paramTypedArray) {
    int i = 0;
    if (paramTypedArray.getBoolean(paramInt2, false))
      i = paramInt1; 
    return i;
  }
  
  public static int flag(int paramInt1, int paramInt2, boolean paramBoolean, TypedArray paramTypedArray) {
    if (!paramTypedArray.getBoolean(paramInt2, paramBoolean))
      paramInt1 = 0; 
    return paramInt1;
  }
  
  public static int getIcon(ParsedComponent paramParsedComponent) {
    return paramParsedComponent.icon;
  }
  
  public static CharSequence getNonLocalizedLabel(ParsedComponent paramParsedComponent) {
    return paramParsedComponent.nonLocalizedLabel;
  }
  
  public static boolean isEnabled(PackageUserState paramPackageUserState, boolean paramBoolean, ParsedMainComponent paramParsedMainComponent, int paramInt) {
    return paramPackageUserState.isEnabled(paramBoolean, paramParsedMainComponent.isEnabled(), paramParsedMainComponent.getName(), paramInt);
  }
  
  public static boolean isImplicitlyExposedIntent(ParsedIntentInfo paramParsedIntentInfo) {
    return (paramParsedIntentInfo.hasCategory("android.intent.category.BROWSABLE") || paramParsedIntentInfo.hasAction("android.intent.action.SEND") || paramParsedIntentInfo.hasAction("android.intent.action.SENDTO") || paramParsedIntentInfo.hasAction("android.intent.action.SEND_MULTIPLE"));
  }
  
  public static boolean isMatch(PackageUserState paramPackageUserState, boolean paramBoolean1, boolean paramBoolean2, ParsedMainComponent paramParsedMainComponent, int paramInt) {
    return paramPackageUserState.isMatch(paramBoolean1, paramBoolean2, paramParsedMainComponent.isEnabled(), paramParsedMainComponent.isDirectBootAware(), paramParsedMainComponent.getName(), paramInt);
  }
  
  static <Component extends ParsedComponent> ParseResult<Component> parseAllMetaData(ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, String paramString, Component paramComponent, ParseInput paramParseInput) throws XmlPullParserException, IOException {
    int i = paramXmlResourceParser.getDepth();
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        ParseResult parseResult;
        if (j != 2)
          continue; 
        if ("meta-data".equals(paramXmlResourceParser.getName())) {
          parseResult = ParsedComponentUtils.addMetaData((ParsedComponent)paramComponent, paramParsingPackage, paramResources, paramXmlResourceParser, paramParseInput);
        } else {
          parseResult = ParsingUtils.unknownTag(paramString, paramParsingPackage, paramXmlResourceParser, paramParseInput);
        } 
        if (parseResult.isError())
          return paramParseInput.error(parseResult); 
        continue;
      } 
      break;
    } 
    return paramParseInput.success(paramComponent);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ComponentParseUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
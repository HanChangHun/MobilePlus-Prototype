package android.content.pm.parsing.component;

import android.content.pm.PermissionInfo;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Slog;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public class ParsedPermissionUtils {
  private static final String TAG = "PackageParsing";
  
  public static ParseResult<ParsedPermission> parsePermission(ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, boolean paramBoolean, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    String str1 = paramParsingPackage.getPackageName();
    ParsedPermission parsedPermission = new ParsedPermission();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<");
    stringBuilder.append(paramXmlResourceParser.getName());
    stringBuilder.append(">");
    String str2 = stringBuilder.toString();
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestPermission);
    try {
      ParseResult<ParsedPermission> parseResult = ParsedComponentUtils.parseComponent(parsedPermission, str2, paramParsingPackage, typedArray, paramBoolean, paramParseInput, 8, Integer.valueOf(5), 1, 0, 6, 2, 9);
      paramBoolean = parseResult.isError();
      if (paramBoolean) {
        typedArray.recycle();
        return parseResult;
      } 
      TypedArray typedArray1 = typedArray;
      try {
        StringBuilder stringBuilder1;
        IllegalStateException illegalStateException;
        paramBoolean = typedArray1.hasValue(10);
        if (paramBoolean)
          try {
            if ("android".equals(str1)) {
              parsedPermission.backgroundPermission = typedArray1.getNonResourceString(10);
            } else {
              StringBuilder stringBuilder2 = new StringBuilder();
              this();
              stringBuilder2.append(str1);
              stringBuilder2.append(" defines a background permission. Only the 'android' package can do that.");
              Slog.w("PackageParsing", stringBuilder2.toString());
            } 
          } finally {} 
        parsedPermission.setGroup(typedArray1.getNonResourceString(4));
        parsedPermission.requestRes = typedArray1.getResourceId(11, 0);
        parsedPermission.protectionLevel = typedArray1.getInt(3, 0);
        parsedPermission.flags = typedArray1.getInt(7, 0);
        paramBoolean = parsedPermission.isRuntime();
        if (!paramBoolean || !"android".equals(parsedPermission.getPackageName())) {
          parsedPermission.flags &= 0xFFFFFFFB;
          parsedPermission.flags &= 0xFFFFFFF7;
        } else if ((0x4 & parsedPermission.flags) != 0 && (parsedPermission.flags & 0x8) != 0) {
          illegalStateException = new IllegalStateException();
          stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("Permission cannot be both soft and hard restricted: ");
          stringBuilder1.append(parsedPermission.getName());
          this(stringBuilder1.toString());
          throw illegalStateException;
        } 
        typedArray1.recycle();
        if (parsedPermission.protectionLevel == -1)
          return paramParseInput.error("<permission> does not specify protectionLevel"); 
        parsedPermission.protectionLevel = PermissionInfo.fixProtectionLevel(parsedPermission.protectionLevel);
        return (parsedPermission.getProtectionFlags() != 0 && (parsedPermission.protectionLevel & 0x1000) == 0 && (parsedPermission.protectionLevel & 0x2000) == 0 && (parsedPermission.protectionLevel & 0xF) != 2) ? paramParseInput.error("<permission>  protectionLevel specifies a non-instant flag but is not based on signature type") : ComponentParseUtils.parseAllMetaData((ParsingPackage)stringBuilder1, (Resources)illegalStateException, paramXmlResourceParser, str2, parsedPermission, paramParseInput);
      } finally {}
    } finally {}
    while (true) {
      typedArray.recycle();
      throw paramParsingPackage;
    } 
  }
  
  public static ParseResult<ParsedPermissionGroup> parsePermissionGroup(ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, boolean paramBoolean, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    ParsedPermissionGroup parsedPermissionGroup = new ParsedPermissionGroup();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<");
    stringBuilder.append(paramXmlResourceParser.getName());
    stringBuilder.append(">");
    String str = stringBuilder.toString();
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestPermissionGroup);
    try {
      ParseResult<ParsedPermissionGroup> parseResult = ParsedComponentUtils.parseComponent(parsedPermissionGroup, str, paramParsingPackage, typedArray, paramBoolean, paramParseInput, 7, Integer.valueOf(4), 1, 0, 5, 2, 8);
      paramBoolean = parseResult.isError();
      if (paramBoolean) {
        typedArray.recycle();
        return parseResult;
      } 
      TypedArray typedArray1 = typedArray;
      try {
        parsedPermissionGroup.requestDetailResourceId = typedArray1.getResourceId(12, 0);
        parsedPermissionGroup.backgroundRequestResourceId = typedArray1.getResourceId(9, 0);
        parsedPermissionGroup.backgroundRequestDetailResourceId = typedArray1.getResourceId(10, 0);
        parsedPermissionGroup.requestRes = typedArray1.getResourceId(11, 0);
        parsedPermissionGroup.flags = typedArray1.getInt(6, 0);
        parsedPermissionGroup.priority = typedArray1.getInt(3, 0);
        typedArray1.recycle();
        return ComponentParseUtils.parseAllMetaData(paramParsingPackage, paramResources, paramXmlResourceParser, str, parsedPermissionGroup, paramParseInput);
      } finally {}
    } finally {}
    while (true) {
      typedArray.recycle();
      throw paramParsingPackage;
    } 
  }
  
  public static ParseResult<ParsedPermission> parsePermissionTree(ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, boolean paramBoolean, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    ParsedPermission parsedPermission = new ParsedPermission();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<");
    stringBuilder.append(paramXmlResourceParser.getName());
    stringBuilder.append(">");
    String str = stringBuilder.toString();
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestPermissionTree);
    try {
      StringBuilder stringBuilder1;
      ParseResult<ParsedPermission> parseResult = ParsedComponentUtils.parseComponent(parsedPermission, str, paramParsingPackage, typedArray, paramBoolean, paramParseInput, 4, null, 1, 0, 3, 2, 5);
      paramBoolean = parseResult.isError();
      if (paramBoolean)
        return parseResult; 
      typedArray.recycle();
      int i = parsedPermission.getName().indexOf('.');
      if (i > 0)
        i = parsedPermission.getName().indexOf('.', i + 1); 
      if (i < 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("<permission-tree> name has less than three segments: ");
        return paramParseInput.error(stringBuilder1.toString());
      } 
      parsedPermission.protectionLevel = 0;
      return ComponentParseUtils.parseAllMetaData((ParsingPackage)stringBuilder1, paramResources, paramXmlResourceParser, str, parsedPermission, paramParseInput);
    } finally {
      typedArray.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedPermissionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
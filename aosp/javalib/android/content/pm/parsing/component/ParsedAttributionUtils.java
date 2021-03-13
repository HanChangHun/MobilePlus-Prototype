package android.content.pm.parsing.component;

import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import com.android.internal.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

public class ParsedAttributionUtils {
  public static ParseResult<ParsedAttribution> parseAttribution(Resources paramResources, XmlResourceParser paramXmlResourceParser, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    String str = null;
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestAttribution);
    if (typedArray == null)
      return paramParseInput.error("<attribution> could not be parsed"); 
    try {
      ParseResult<ParsedAttribution> parseResult;
      List<?> list;
      String str1 = typedArray.getNonConfigurationString(1, 0);
      String str2 = str1;
      if (str1 == null) {
        str1 = typedArray.getNonConfigurationString(2, 0);
        str2 = str1;
        if (str1 == null) {
          parseResult = paramParseInput.error("<attribution> does not specify android:tag");
          return parseResult;
        } 
      } 
      if (str2.length() > 50) {
        parseResult = paramParseInput.error("android:tag is too long. Max length is 50");
        return parseResult;
      } 
      int i = typedArray.getResourceId(0, 0);
      if (i == 0) {
        parseResult = paramParseInput.error("<attribution> does not specify android:label");
        return parseResult;
      } 
      typedArray.recycle();
      int j = paramXmlResourceParser.getDepth();
      while (true) {
        int k = paramXmlResourceParser.next();
        if (k != 1 && (k != 3 || paramXmlResourceParser.getDepth() > j)) {
          ArrayList<String> arrayList;
          if (k == 3 || k == 4)
            continue; 
          str1 = paramXmlResourceParser.getName();
          if (str1.equals("inherit-from")) {
            typedArray = parseResult.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestAttributionInheritFrom);
            if (typedArray == null)
              return paramParseInput.error("<inherit-from> could not be parsed"); 
            try {
              String str3 = typedArray.getNonConfigurationString(0, 0);
              str1 = str;
              if (str == null) {
                arrayList = new ArrayList();
                this();
              } 
              arrayList.add(str3);
              typedArray.recycle();
            } finally {
              typedArray.recycle();
            } 
            continue;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Bad element under <attribution>: ");
          return paramParseInput.error(stringBuilder.toString());
        } 
        break;
      } 
      return paramParseInput.success(new ParsedAttribution(str2, i, (List)list));
    } finally {
      typedArray.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedAttributionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
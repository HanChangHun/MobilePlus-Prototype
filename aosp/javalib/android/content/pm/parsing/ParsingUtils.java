package android.content.pm.parsing;

import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.XmlResourceParser;
import android.util.Slog;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ParsingUtils {
  public static final String TAG = "PackageParsing";
  
  public static String buildClassName(String paramString, CharSequence paramCharSequence) {
    if (paramCharSequence == null || paramCharSequence.length() <= 0)
      return null; 
    paramCharSequence = paramCharSequence.toString();
    if (paramCharSequence.charAt(0) == '.') {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append((String)paramCharSequence);
      return stringBuilder.toString();
    } 
    if (paramCharSequence.indexOf('.') < 0) {
      StringBuilder stringBuilder = new StringBuilder(paramString);
      stringBuilder.append('.');
      stringBuilder.append((String)paramCharSequence);
      return stringBuilder.toString();
    } 
    return (String)paramCharSequence;
  }
  
  public static ParseResult unknownTag(String paramString, ParsingPackage paramParsingPackage, XmlResourceParser paramXmlResourceParser, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown element under ");
    stringBuilder.append(paramString);
    stringBuilder.append(": ");
    stringBuilder.append(paramXmlResourceParser.getName());
    stringBuilder.append(" at ");
    stringBuilder.append(paramParsingPackage.getBaseCodePath());
    stringBuilder.append(" ");
    stringBuilder.append(paramXmlResourceParser.getPositionDescription());
    Slog.w("PackageParsing", stringBuilder.toString());
    XmlUtils.skipCurrentTag((XmlPullParser)paramXmlResourceParser);
    return paramParseInput.success(null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/ParsingUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
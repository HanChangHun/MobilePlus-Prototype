package android.graphics.fonts;

import android.graphics.FontListParser;
import android.text.FontConfig;
import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class FontCustomizationParser {
  public static Result parse(InputStream paramInputStream, String paramString) throws XmlPullParserException, IOException {
    XmlPullParser xmlPullParser = Xml.newPullParser();
    xmlPullParser.setInput(paramInputStream, null);
    xmlPullParser.nextTag();
    return readFamilies(xmlPullParser, paramString);
  }
  
  private static Result readFamilies(XmlPullParser paramXmlPullParser, String paramString) throws XmlPullParserException, IOException {
    Result result = new Result();
    paramXmlPullParser.require(2, null, "fonts-modification");
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() != 2)
        continue; 
      String str = paramXmlPullParser.getName();
      if (str.equals("family")) {
        readFamily(paramXmlPullParser, paramString, result);
        continue;
      } 
      if (str.equals("alias")) {
        result.mAdditionalAliases.add(FontListParser.readAlias(paramXmlPullParser));
        continue;
      } 
      FontListParser.skip(paramXmlPullParser);
    } 
    validate(result);
    return result;
  }
  
  private static void readFamily(XmlPullParser paramXmlPullParser, String paramString, Result paramResult) throws XmlPullParserException, IOException {
    String str = paramXmlPullParser.getAttributeValue(null, "customizationType");
    if (str != null) {
      if (str.equals("new-named-family")) {
        paramResult.mAdditionalNamedFamilies.add(FontListParser.readFamily(paramXmlPullParser, paramString));
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown customizationType=");
      stringBuilder.append(str);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    throw new IllegalArgumentException("customizationType must be specified");
  }
  
  private static void validate(Result paramResult) {
    HashSet<String> hashSet = new HashSet();
    byte b = 0;
    while (b < paramResult.mAdditionalNamedFamilies.size()) {
      String str = ((FontConfig.Family)paramResult.mAdditionalNamedFamilies.get(b)).getName();
      if (str != null) {
        if (hashSet.add(str)) {
          b++;
          continue;
        } 
        throw new IllegalArgumentException("new-named-family requires unique name attribute");
      } 
      throw new IllegalArgumentException("new-named-family requires name attribute");
    } 
  }
  
  public static class Result {
    ArrayList<FontConfig.Alias> mAdditionalAliases = new ArrayList<>();
    
    ArrayList<FontConfig.Family> mAdditionalNamedFamilies = new ArrayList<>();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/fonts/FontCustomizationParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
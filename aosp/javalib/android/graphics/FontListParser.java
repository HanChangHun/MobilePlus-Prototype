package android.graphics;

import android.graphics.fonts.FontVariationAxis;
import android.text.FontConfig;
import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class FontListParser {
  private static final Pattern FILENAME_WHITESPACE_PATTERN = Pattern.compile("^[ \\n\\r\\t]+|[ \\n\\r\\t]+$");
  
  public static FontConfig parse(InputStream paramInputStream) throws XmlPullParserException, IOException {
    return parse(paramInputStream, "/system/fonts");
  }
  
  public static FontConfig parse(InputStream paramInputStream, String paramString) throws XmlPullParserException, IOException {
    try {
      XmlPullParser xmlPullParser = Xml.newPullParser();
      xmlPullParser.setInput(paramInputStream, null);
      xmlPullParser.nextTag();
      return readFamilies(xmlPullParser, paramString);
    } finally {
      paramInputStream.close();
    } 
  }
  
  public static FontConfig.Alias readAlias(XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    int i;
    String str1 = paramXmlPullParser.getAttributeValue(null, "name");
    String str2 = paramXmlPullParser.getAttributeValue(null, "to");
    String str3 = paramXmlPullParser.getAttributeValue(null, "weight");
    if (str3 == null) {
      i = 400;
    } else {
      i = Integer.parseInt(str3);
    } 
    skip(paramXmlPullParser);
    return new FontConfig.Alias(str1, str2, i);
  }
  
  private static FontVariationAxis readAxis(XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    String str1 = paramXmlPullParser.getAttributeValue(null, "tag");
    String str2 = paramXmlPullParser.getAttributeValue(null, "stylevalue");
    skip(paramXmlPullParser);
    return new FontVariationAxis(str1, Float.parseFloat(str2));
  }
  
  private static FontConfig readFamilies(XmlPullParser paramXmlPullParser, String paramString) throws XmlPullParserException, IOException {
    ArrayList<FontConfig.Family> arrayList = new ArrayList();
    ArrayList<FontConfig.Alias> arrayList1 = new ArrayList();
    paramXmlPullParser.require(2, null, "familyset");
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() != 2)
        continue; 
      String str = paramXmlPullParser.getName();
      if (str.equals("family")) {
        arrayList.add(readFamily(paramXmlPullParser, paramString));
        continue;
      } 
      if (str.equals("alias")) {
        arrayList1.add(readAlias(paramXmlPullParser));
        continue;
      } 
      skip(paramXmlPullParser);
    } 
    return new FontConfig(arrayList.<FontConfig.Family>toArray(new FontConfig.Family[arrayList.size()]), arrayList1.<FontConfig.Alias>toArray(new FontConfig.Alias[arrayList1.size()]));
  }
  
  public static FontConfig.Family readFamily(XmlPullParser paramXmlPullParser, String paramString) throws XmlPullParserException, IOException {
    String str1 = paramXmlPullParser.getAttributeValue(null, "name");
    String str2 = paramXmlPullParser.getAttributeValue("", "lang");
    String str3 = paramXmlPullParser.getAttributeValue(null, "variant");
    ArrayList<FontConfig.Font> arrayList = new ArrayList();
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() != 2)
        continue; 
      if (paramXmlPullParser.getName().equals("font")) {
        arrayList.add(readFont(paramXmlPullParser, paramString));
        continue;
      } 
      skip(paramXmlPullParser);
    } 
    byte b1 = 0;
    byte b2 = b1;
    if (str3 != null)
      if (str3.equals("compact")) {
        b2 = 1;
      } else {
        b2 = b1;
        if (str3.equals("elegant"))
          b2 = 2; 
      }  
    return new FontConfig.Family(str1, arrayList.<FontConfig.Font>toArray(new FontConfig.Font[arrayList.size()]), str2, b2);
  }
  
  private static FontConfig.Font readFont(XmlPullParser paramXmlPullParser, String paramString) throws XmlPullParserException, IOException {
    int i;
    int j;
    String str2 = paramXmlPullParser.getAttributeValue(null, "index");
    if (str2 == null) {
      i = 0;
    } else {
      i = Integer.parseInt(str2);
    } 
    ArrayList<FontVariationAxis> arrayList = new ArrayList();
    String str3 = paramXmlPullParser.getAttributeValue(null, "weight");
    if (str3 == null) {
      j = 400;
    } else {
      j = Integer.parseInt(str3);
    } 
    boolean bool = "italic".equals(paramXmlPullParser.getAttributeValue(null, "style"));
    str3 = paramXmlPullParser.getAttributeValue(null, "fallbackFor");
    StringBuilder stringBuilder = new StringBuilder();
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() == 4)
        stringBuilder.append(paramXmlPullParser.getText()); 
      if (paramXmlPullParser.getEventType() != 2)
        continue; 
      if (paramXmlPullParser.getName().equals("axis")) {
        arrayList.add(readAxis(paramXmlPullParser));
        continue;
      } 
      skip(paramXmlPullParser);
    } 
    String str1 = FILENAME_WHITESPACE_PATTERN.matcher(stringBuilder).replaceAll("");
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(str1);
    return new FontConfig.Font(stringBuilder.toString(), i, arrayList.<FontVariationAxis>toArray(new FontVariationAxis[arrayList.size()]), j, bool, str3);
  }
  
  public static void skip(XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    for (byte b = 1; b; b++) {
      int i = paramXmlPullParser.next();
      if (i != 2) {
        if (i != 3)
          continue; 
        b--;
        continue;
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/FontListParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
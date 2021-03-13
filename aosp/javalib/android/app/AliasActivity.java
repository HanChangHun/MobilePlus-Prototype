package android.app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Xml;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@Deprecated
public class AliasActivity extends Activity {
  public final String ALIAS_META_DATA = "android.app.alias";
  
  private Intent parseAlias(XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    Intent intent;
    AttributeSet attributeSet = Xml.asAttributeSet(paramXmlPullParser);
    String str1 = null;
    while (true) {
      int i = paramXmlPullParser.next();
      if (i != 1 && i != 2)
        continue; 
      break;
    } 
    String str2 = paramXmlPullParser.getName();
    if ("alias".equals(str2)) {
      Intent intent1;
      int i = paramXmlPullParser.getDepth();
      while (true) {
        int j = paramXmlPullParser.next();
        if (j != 1 && (j != 3 || paramXmlPullParser.getDepth() > i)) {
          if (j == 3 || j == 4)
            continue; 
          if ("intent".equals(paramXmlPullParser.getName())) {
            Intent intent2 = Intent.parseIntent(getResources(), paramXmlPullParser, attributeSet);
            str2 = str1;
            if (str1 == null)
              intent = intent2; 
            intent1 = intent;
            continue;
          } 
          XmlUtils.skipCurrentTag(paramXmlPullParser);
          continue;
        } 
        break;
      } 
      return intent1;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Alias meta-data must start with <alias> tag; found");
    stringBuilder.append((String)intent);
    stringBuilder.append(" at ");
    stringBuilder.append(paramXmlPullParser.getPositionDescription());
    throw new RuntimeException(stringBuilder.toString());
  }
  
  protected void onCreate(Bundle paramBundle) {
    XmlPullParserException xmlPullParserException1;
    Exception exception;
    super.onCreate(paramBundle);
    XmlResourceParser xmlResourceParser1 = null;
    XmlResourceParser xmlResourceParser2 = null;
    XmlResourceParser xmlResourceParser3 = null;
    paramBundle = null;
    try {
      XmlResourceParser xmlResourceParser5 = getPackageManager().getActivityInfo(getComponentName(), 128).loadXmlMetaData(getPackageManager(), "android.app.alias");
      if (xmlResourceParser5 != null) {
        XmlResourceParser xmlResourceParser = xmlResourceParser5;
        xmlResourceParser1 = xmlResourceParser5;
        xmlResourceParser2 = xmlResourceParser5;
        xmlResourceParser3 = xmlResourceParser5;
        Intent intent = parseAlias((XmlPullParser)xmlResourceParser5);
        if (intent != null) {
          xmlResourceParser = xmlResourceParser5;
          xmlResourceParser1 = xmlResourceParser5;
          xmlResourceParser2 = xmlResourceParser5;
          xmlResourceParser3 = xmlResourceParser5;
          startActivity(intent);
          xmlResourceParser = xmlResourceParser5;
          xmlResourceParser1 = xmlResourceParser5;
          xmlResourceParser2 = xmlResourceParser5;
          xmlResourceParser3 = xmlResourceParser5;
          finish();
          if (xmlResourceParser5 != null)
            xmlResourceParser5.close(); 
          return;
        } 
        xmlResourceParser = xmlResourceParser5;
        xmlResourceParser1 = xmlResourceParser5;
        xmlResourceParser2 = xmlResourceParser5;
        xmlResourceParser3 = xmlResourceParser5;
        RuntimeException runtimeException1 = new RuntimeException();
        xmlResourceParser = xmlResourceParser5;
        xmlResourceParser1 = xmlResourceParser5;
        xmlResourceParser2 = xmlResourceParser5;
        xmlResourceParser3 = xmlResourceParser5;
        this("No <intent> tag found in alias description");
        xmlResourceParser = xmlResourceParser5;
        xmlResourceParser1 = xmlResourceParser5;
        xmlResourceParser2 = xmlResourceParser5;
        xmlResourceParser3 = xmlResourceParser5;
        throw runtimeException1;
      } 
      XmlResourceParser xmlResourceParser4 = xmlResourceParser5;
      xmlResourceParser1 = xmlResourceParser5;
      xmlResourceParser2 = xmlResourceParser5;
      xmlResourceParser3 = xmlResourceParser5;
      RuntimeException runtimeException = new RuntimeException();
      xmlResourceParser4 = xmlResourceParser5;
      xmlResourceParser1 = xmlResourceParser5;
      xmlResourceParser2 = xmlResourceParser5;
      xmlResourceParser3 = xmlResourceParser5;
      this("Alias requires a meta-data field android.app.alias");
      xmlResourceParser4 = xmlResourceParser5;
      xmlResourceParser1 = xmlResourceParser5;
      xmlResourceParser2 = xmlResourceParser5;
      xmlResourceParser3 = xmlResourceParser5;
      throw runtimeException;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      XmlResourceParser xmlResourceParser = xmlResourceParser3;
      RuntimeException runtimeException = new RuntimeException();
      xmlResourceParser = xmlResourceParser3;
      this("Error parsing alias", (Throwable)nameNotFoundException);
      xmlResourceParser = xmlResourceParser3;
      throw runtimeException;
    } catch (XmlPullParserException xmlPullParserException2) {
      XmlResourceParser xmlResourceParser = xmlResourceParser2;
      exception = new RuntimeException();
      xmlResourceParser = xmlResourceParser2;
      this("Error parsing alias", (Throwable)xmlPullParserException2);
      xmlResourceParser = xmlResourceParser2;
      throw exception;
    } catch (IOException iOException) {
      xmlPullParserException1 = xmlPullParserException2;
      exception = new RuntimeException();
      xmlPullParserException1 = xmlPullParserException2;
      this("Error parsing alias", iOException);
      xmlPullParserException1 = xmlPullParserException2;
      throw exception;
    } finally {}
    if (xmlPullParserException1 != null)
      xmlPullParserException1.close(); 
    throw exception;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AliasActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
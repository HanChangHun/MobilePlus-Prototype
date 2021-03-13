package android.content.res;

import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

public interface XmlResourceParser extends XmlPullParser, AttributeSet, AutoCloseable {
  void close();
  
  String getAttributeNamespace(int paramInt);
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/XmlResourceParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
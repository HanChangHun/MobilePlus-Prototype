package android.content.pm;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public interface XmlSerializerAndParser<T> {
  T createFromXml(XmlPullParser paramXmlPullParser) throws IOException, XmlPullParserException;
  
  void writeAsXml(T paramT, XmlSerializer paramXmlSerializer) throws IOException;
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/XmlSerializerAndParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
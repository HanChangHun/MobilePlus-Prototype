package android.content;

import android.content.pm.XmlSerializerAndParser;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

class MySerializer implements XmlSerializerAndParser<SyncAdapterType> {
  public SyncAdapterType createFromXml(XmlPullParser paramXmlPullParser) throws IOException, XmlPullParserException {
    return SyncAdapterType.newKey(paramXmlPullParser.getAttributeValue(null, "authority"), paramXmlPullParser.getAttributeValue(null, "accountType"));
  }
  
  public void writeAsXml(SyncAdapterType paramSyncAdapterType, XmlSerializer paramXmlSerializer) throws IOException {
    paramXmlSerializer.attribute(null, "authority", paramSyncAdapterType.authority);
    paramXmlSerializer.attribute(null, "accountType", paramSyncAdapterType.accountType);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncAdaptersCache$MySerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
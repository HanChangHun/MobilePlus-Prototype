package android.content;

import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public interface ContentInsertHandler extends ContentHandler {
  void insert(ContentResolver paramContentResolver, InputStream paramInputStream) throws IOException, SAXException;
  
  void insert(ContentResolver paramContentResolver, String paramString) throws SAXException;
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentInsertHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
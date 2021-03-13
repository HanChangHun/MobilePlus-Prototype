package android.content;

import android.net.Uri;
import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class DefaultDataHandler implements ContentInsertHandler {
  private static final String ARG = "arg";
  
  private static final String COL = "col";
  
  private static final String DEL = "del";
  
  private static final String POSTFIX = "postfix";
  
  private static final String ROW = "row";
  
  private static final String SELECT = "select";
  
  private static final String URI_STR = "uri";
  
  private ContentResolver mContentResolver;
  
  private Stack<Uri> mUris = new Stack<>();
  
  private ContentValues mValues;
  
  private Uri insertRow() {
    Uri uri = this.mContentResolver.insert(this.mUris.lastElement(), this.mValues);
    this.mValues = null;
    return uri;
  }
  
  private void parseRow(Attributes paramAttributes) throws SAXException {
    Uri uri;
    String str = paramAttributes.getValue("uri");
    if (str != null) {
      Uri uri1 = Uri.parse(str);
      if (uri1 != null) {
        uri = uri1;
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("attribute ");
        stringBuilder.append(uri.getValue("uri"));
        stringBuilder.append(" parsing failure");
        throw new SAXException(stringBuilder.toString());
      } 
    } else if (this.mUris.size() > 0) {
      String str1 = uri.getValue("postfix");
      if (str1 != null) {
        Uri uri1 = Uri.withAppendedPath(this.mUris.lastElement(), str1);
      } else {
        uri = this.mUris.lastElement();
      } 
    } else {
      throw new SAXException("attribute parsing failure");
    } 
    this.mUris.push(uri);
  }
  
  public void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {}
  
  public void endDocument() throws SAXException {}
  
  public void endElement(String paramString1, String paramString2, String paramString3) throws SAXException {
    if ("row".equals(paramString2))
      if (!this.mUris.empty()) {
        if (this.mValues != null)
          insertRow(); 
        this.mUris.pop();
      } else {
        throw new SAXException("uri mismatch");
      }  
  }
  
  public void endPrefixMapping(String paramString) throws SAXException {}
  
  public void ignorableWhitespace(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {}
  
  public void insert(ContentResolver paramContentResolver, InputStream paramInputStream) throws IOException, SAXException {
    this.mContentResolver = paramContentResolver;
    Xml.parse(paramInputStream, Xml.Encoding.UTF_8, this);
  }
  
  public void insert(ContentResolver paramContentResolver, String paramString) throws SAXException {
    this.mContentResolver = paramContentResolver;
    Xml.parse(paramString, this);
  }
  
  public void processingInstruction(String paramString1, String paramString2) throws SAXException {}
  
  public void setDocumentLocator(Locator paramLocator) {}
  
  public void skippedEntity(String paramString) throws SAXException {}
  
  public void startDocument() throws SAXException {}
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) throws SAXException {
    if ("row".equals(paramString2)) {
      if (this.mValues != null) {
        if (!this.mUris.empty()) {
          Uri uri = insertRow();
          if (uri != null) {
            this.mUris.pop();
            this.mUris.push(uri);
            parseRow(paramAttributes);
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("insert to uri ");
            stringBuilder.append(((Uri)this.mUris.lastElement()).toString());
            stringBuilder.append(" failure");
            throw new SAXException(stringBuilder.toString());
          } 
        } else {
          throw new SAXException("uri is empty");
        } 
      } else if (paramAttributes.getLength() == 0) {
        Stack<Uri> stack = this.mUris;
        stack.push(stack.lastElement());
      } else {
        parseRow(paramAttributes);
      } 
    } else if ("col".equals(paramString2)) {
      int i = paramAttributes.getLength();
      if (i == 2) {
        paramString1 = paramAttributes.getValue(0);
        paramString2 = paramAttributes.getValue(1);
        if (paramString1 != null && paramString1.length() > 0 && paramString2 != null && paramString2.length() > 0) {
          if (this.mValues == null)
            this.mValues = new ContentValues(); 
          this.mValues.put(paramString1, paramString2);
        } else {
          throw new SAXException("illegal attributes value");
        } 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("illegal attributes number ");
        stringBuilder.append(i);
        throw new SAXException(stringBuilder.toString());
      } 
    } else {
      String[] arrayOfString;
      if ("del".equals(paramString2)) {
        Uri uri = Uri.parse(paramAttributes.getValue("uri"));
        if (uri != null) {
          int i = paramAttributes.getLength() - 2;
          if (i > 0) {
            arrayOfString = new String[i];
            for (byte b = 0; b < i; b++)
              arrayOfString[b] = paramAttributes.getValue(b + 2); 
            this.mContentResolver.delete(uri, paramAttributes.getValue(1), arrayOfString);
          } else if (i == 0) {
            this.mContentResolver.delete(uri, paramAttributes.getValue(1), null);
          } else {
            this.mContentResolver.delete(uri, null, null);
          } 
          return;
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("attribute ");
        stringBuilder1.append(paramAttributes.getValue("uri"));
        stringBuilder1.append(" parsing failure");
        throw new SAXException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("unknown element: ");
      stringBuilder.append((String)arrayOfString);
      throw new SAXException(stringBuilder.toString());
    } 
  }
  
  public void startPrefixMapping(String paramString1, String paramString2) throws SAXException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/DefaultDataHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
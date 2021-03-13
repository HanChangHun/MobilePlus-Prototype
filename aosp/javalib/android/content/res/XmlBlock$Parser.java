package android.content.res;

import android.util.TypedValue;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParserException;

final class Parser implements XmlResourceParser {
  private final XmlBlock mBlock;
  
  private boolean mDecNextDepth = false;
  
  private int mDepth = 0;
  
  private int mEventType = 0;
  
  long mParseState;
  
  private boolean mStarted = false;
  
  Parser(long paramLong, XmlBlock paramXmlBlock2) {
    this.mParseState = paramLong;
    this.mBlock = paramXmlBlock2;
    XmlBlock.access$008(paramXmlBlock2);
  }
  
  public void close() {
    synchronized (this.mBlock) {
      if (this.mParseState != 0L) {
        XmlBlock.access$1600(this.mParseState);
        this.mParseState = 0L;
        XmlBlock.access$1700(this.mBlock);
      } 
      return;
    } 
  }
  
  public void defineEntityReplacementText(String paramString1, String paramString2) throws XmlPullParserException {
    throw new XmlPullParserException("defineEntityReplacementText() not supported");
  }
  
  protected void finalize() throws Throwable {
    close();
  }
  
  public boolean getAttributeBooleanValue(int paramInt, boolean paramBoolean) {
    int i = XmlBlock.access$900(this.mParseState, paramInt);
    if (i >= 16 && i <= 31) {
      if (XmlBlock.access$1000(this.mParseState, paramInt) != 0) {
        paramBoolean = true;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } 
    return paramBoolean;
  }
  
  public boolean getAttributeBooleanValue(String paramString1, String paramString2, boolean paramBoolean) {
    int i = XmlBlock.access$1100(this.mParseState, paramString1, paramString2);
    return (i >= 0) ? getAttributeBooleanValue(i, paramBoolean) : paramBoolean;
  }
  
  public int getAttributeCount() {
    byte b;
    if (this.mEventType == 2) {
      b = XmlBlock.access$700(this.mParseState);
    } else {
      b = -1;
    } 
    return b;
  }
  
  public float getAttributeFloatValue(int paramInt, float paramFloat) {
    if (XmlBlock.access$900(this.mParseState, paramInt) == 4)
      return Float.intBitsToFloat(XmlBlock.access$1000(this.mParseState, paramInt)); 
    throw new RuntimeException("not a float!");
  }
  
  public float getAttributeFloatValue(String paramString1, String paramString2, float paramFloat) {
    int i = XmlBlock.access$1100(this.mParseState, paramString1, paramString2);
    return (i >= 0) ? getAttributeFloatValue(i, paramFloat) : paramFloat;
  }
  
  public int getAttributeIntValue(int paramInt1, int paramInt2) {
    int i = XmlBlock.access$900(this.mParseState, paramInt1);
    return (i >= 16 && i <= 31) ? XmlBlock.access$1000(this.mParseState, paramInt1) : paramInt2;
  }
  
  public int getAttributeIntValue(String paramString1, String paramString2, int paramInt) {
    int i = XmlBlock.access$1100(this.mParseState, paramString1, paramString2);
    return (i >= 0) ? getAttributeIntValue(i, paramInt) : paramInt;
  }
  
  public int getAttributeListValue(int paramInt1, String[] paramArrayOfString, int paramInt2) {
    int i = XmlBlock.access$900(this.mParseState, paramInt1);
    paramInt1 = XmlBlock.access$1000(this.mParseState, paramInt1);
    return (i == 3) ? XmlUtils.convertValueToList(XmlBlock.this.mStrings.get(paramInt1), paramArrayOfString, paramInt2) : paramInt1;
  }
  
  public int getAttributeListValue(String paramString1, String paramString2, String[] paramArrayOfString, int paramInt) {
    int i = XmlBlock.access$1100(this.mParseState, paramString1, paramString2);
    return (i >= 0) ? getAttributeListValue(i, paramArrayOfString, paramInt) : paramInt;
  }
  
  public String getAttributeName(int paramInt) {
    int i = XmlBlock.access$600(this.mParseState, paramInt);
    if (i >= 0)
      return XmlBlock.this.mStrings.get(i).toString(); 
    throw new IndexOutOfBoundsException(String.valueOf(paramInt));
  }
  
  public int getAttributeNameResource(int paramInt) {
    return XmlBlock.access$1200(this.mParseState, paramInt);
  }
  
  public String getAttributeNamespace(int paramInt) {
    int i = XmlBlock.access$500(this.mParseState, paramInt);
    if (i >= 0)
      return XmlBlock.this.mStrings.get(i).toString(); 
    if (i == -1)
      return ""; 
    throw new IndexOutOfBoundsException(String.valueOf(paramInt));
  }
  
  public String getAttributePrefix(int paramInt) {
    throw new RuntimeException("getAttributePrefix not supported");
  }
  
  public int getAttributeResourceValue(int paramInt1, int paramInt2) {
    return (XmlBlock.access$900(this.mParseState, paramInt1) == 1) ? XmlBlock.access$1000(this.mParseState, paramInt1) : paramInt2;
  }
  
  public int getAttributeResourceValue(String paramString1, String paramString2, int paramInt) {
    int i = XmlBlock.access$1100(this.mParseState, paramString1, paramString2);
    return (i >= 0) ? getAttributeResourceValue(i, paramInt) : paramInt;
  }
  
  public String getAttributeType(int paramInt) {
    return "CDATA";
  }
  
  public int getAttributeUnsignedIntValue(int paramInt1, int paramInt2) {
    int i = XmlBlock.access$900(this.mParseState, paramInt1);
    return (i >= 16 && i <= 31) ? XmlBlock.access$1000(this.mParseState, paramInt1) : paramInt2;
  }
  
  public int getAttributeUnsignedIntValue(String paramString1, String paramString2, int paramInt) {
    int i = XmlBlock.access$1100(this.mParseState, paramString1, paramString2);
    return (i >= 0) ? getAttributeUnsignedIntValue(i, paramInt) : paramInt;
  }
  
  public String getAttributeValue(int paramInt) {
    int i = XmlBlock.access$800(this.mParseState, paramInt);
    if (i >= 0)
      return XmlBlock.this.mStrings.get(i).toString(); 
    i = XmlBlock.access$900(this.mParseState, paramInt);
    if (i != 0)
      return TypedValue.coerceToString(i, XmlBlock.access$1000(this.mParseState, paramInt)); 
    throw new IndexOutOfBoundsException(String.valueOf(paramInt));
  }
  
  public String getAttributeValue(String paramString1, String paramString2) {
    int i = XmlBlock.access$1100(this.mParseState, paramString1, paramString2);
    return (i >= 0) ? getAttributeValue(i) : null;
  }
  
  public String getClassAttribute() {
    String str;
    int i = XmlBlock.access$1400(this.mParseState);
    if (i >= 0) {
      str = XmlBlock.this.mStrings.get(i).toString();
    } else {
      str = null;
    } 
    return str;
  }
  
  public int getColumnNumber() {
    return -1;
  }
  
  public int getDepth() {
    return this.mDepth;
  }
  
  public int getEventType() throws XmlPullParserException {
    return this.mEventType;
  }
  
  public boolean getFeature(String paramString) {
    return "http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(paramString) ? true : ("http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes".equals(paramString));
  }
  
  public String getIdAttribute() {
    String str;
    int i = XmlBlock.access$1300(this.mParseState);
    if (i >= 0) {
      str = XmlBlock.this.mStrings.get(i).toString();
    } else {
      str = null;
    } 
    return str;
  }
  
  public int getIdAttributeResourceValue(int paramInt) {
    return getAttributeResourceValue(null, "id", paramInt);
  }
  
  public String getInputEncoding() {
    return null;
  }
  
  public int getLineNumber() {
    return XmlBlock.access$300(this.mParseState);
  }
  
  public String getName() {
    String str;
    int i = XmlBlock.nativeGetName(this.mParseState);
    if (i >= 0) {
      str = XmlBlock.this.mStrings.get(i).toString();
    } else {
      str = null;
    } 
    return str;
  }
  
  public String getNamespace() {
    String str;
    int i = XmlBlock.access$400(this.mParseState);
    if (i >= 0) {
      str = XmlBlock.this.mStrings.get(i).toString();
    } else {
      str = "";
    } 
    return str;
  }
  
  public String getNamespace(String paramString) {
    throw new RuntimeException("getNamespace() not supported");
  }
  
  public int getNamespaceCount(int paramInt) throws XmlPullParserException {
    throw new XmlPullParserException("getNamespaceCount() not supported");
  }
  
  public String getNamespacePrefix(int paramInt) throws XmlPullParserException {
    throw new XmlPullParserException("getNamespacePrefix() not supported");
  }
  
  public String getNamespaceUri(int paramInt) throws XmlPullParserException {
    throw new XmlPullParserException("getNamespaceUri() not supported");
  }
  
  final CharSequence getPooledString(int paramInt) {
    return XmlBlock.this.mStrings.get(paramInt);
  }
  
  public String getPositionDescription() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Binary XML file line #");
    stringBuilder.append(getLineNumber());
    return stringBuilder.toString();
  }
  
  public String getPrefix() {
    throw new RuntimeException("getPrefix not supported");
  }
  
  public Object getProperty(String paramString) {
    return null;
  }
  
  public int getSourceResId() {
    return XmlBlock.access$100(this.mParseState);
  }
  
  public int getStyleAttribute() {
    return XmlBlock.access$1500(this.mParseState);
  }
  
  public String getText() {
    String str;
    int i = XmlBlock.access$200(this.mParseState);
    if (i >= 0) {
      str = XmlBlock.this.mStrings.get(i).toString();
    } else {
      str = null;
    } 
    return str;
  }
  
  public char[] getTextCharacters(int[] paramArrayOfint) {
    String str = getText();
    char[] arrayOfChar = null;
    if (str != null) {
      paramArrayOfint[0] = 0;
      paramArrayOfint[1] = str.length();
      arrayOfChar = new char[str.length()];
      str.getChars(0, str.length(), arrayOfChar, 0);
    } 
    return arrayOfChar;
  }
  
  public boolean isAttributeDefault(int paramInt) {
    return false;
  }
  
  public boolean isEmptyElementTag() throws XmlPullParserException {
    return false;
  }
  
  public boolean isWhitespace() throws XmlPullParserException {
    return false;
  }
  
  public int next() throws XmlPullParserException, IOException {
    if (!this.mStarted) {
      this.mStarted = true;
      return 0;
    } 
    long l = this.mParseState;
    if (l == 0L)
      return 1; 
    int i = XmlBlock.nativeNext(l);
    if (this.mDecNextDepth) {
      this.mDepth--;
      this.mDecNextDepth = false;
    } 
    if (i != 2) {
      if (i == 3)
        this.mDecNextDepth = true; 
    } else {
      this.mDepth++;
    } 
    this.mEventType = i;
    if (i == 1)
      close(); 
    return i;
  }
  
  public int nextTag() throws XmlPullParserException, IOException {
    int i = next();
    int j = i;
    if (i == 4) {
      j = i;
      if (isWhitespace())
        j = next(); 
    } 
    if (j == 2 || j == 3)
      return j; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getPositionDescription());
    stringBuilder.append(": expected start or end tag");
    throw new XmlPullParserException(stringBuilder.toString(), this, null);
  }
  
  public String nextText() throws XmlPullParserException, IOException {
    if (getEventType() == 2) {
      int i = next();
      if (i == 4) {
        String str = getText();
        if (next() == 3)
          return str; 
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(getPositionDescription());
        stringBuilder2.append(": event TEXT it must be immediately followed by END_TAG");
        throw new XmlPullParserException(stringBuilder2.toString(), this, null);
      } 
      if (i == 3)
        return ""; 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(getPositionDescription());
      stringBuilder1.append(": parser must be on START_TAG or TEXT to read text");
      throw new XmlPullParserException(stringBuilder1.toString(), this, null);
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getPositionDescription());
    stringBuilder.append(": parser must be on START_TAG to read next text");
    throw new XmlPullParserException(stringBuilder.toString(), this, null);
  }
  
  public int nextToken() throws XmlPullParserException, IOException {
    return next();
  }
  
  public void require(int paramInt, String paramString1, String paramString2) throws XmlPullParserException, IOException {
    if (paramInt == getEventType() && (paramString1 == null || paramString1.equals(getNamespace())) && (paramString2 == null || paramString2.equals(getName())))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("expected ");
    stringBuilder.append(TYPES[paramInt]);
    stringBuilder.append(getPositionDescription());
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XmlPullParserException {
    if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(paramString) && paramBoolean)
      return; 
    if ("http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes".equals(paramString) && paramBoolean)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unsupported feature: ");
    stringBuilder.append(paramString);
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void setInput(InputStream paramInputStream, String paramString) throws XmlPullParserException {
    throw new XmlPullParserException("setInput() not supported");
  }
  
  public void setInput(Reader paramReader) throws XmlPullParserException {
    throw new XmlPullParserException("setInput() not supported");
  }
  
  public void setProperty(String paramString, Object paramObject) throws XmlPullParserException {
    throw new XmlPullParserException("setProperty() not supported");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/XmlBlock$Parser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
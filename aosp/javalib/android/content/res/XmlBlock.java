package android.content.res;

import android.util.TypedValue;
import com.android.internal.util.XmlUtils;
import dalvik.annotation.optimization.FastNative;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParserException;

final class XmlBlock implements AutoCloseable {
  private static final boolean DEBUG = false;
  
  private final AssetManager mAssets;
  
  private final long mNative;
  
  private boolean mOpen = true;
  
  private int mOpenCount = 1;
  
  final StringBlock mStrings;
  
  XmlBlock(AssetManager paramAssetManager, long paramLong) {
    this.mAssets = paramAssetManager;
    this.mNative = paramLong;
    this.mStrings = new StringBlock(nativeGetStringBlock(paramLong), false);
  }
  
  public XmlBlock(byte[] paramArrayOfbyte) {
    this.mAssets = null;
    long l = nativeCreate(paramArrayOfbyte, 0, paramArrayOfbyte.length);
    this.mNative = l;
    this.mStrings = new StringBlock(nativeGetStringBlock(l), false);
  }
  
  public XmlBlock(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.mAssets = null;
    long l = nativeCreate(paramArrayOfbyte, paramInt1, paramInt2);
    this.mNative = l;
    this.mStrings = new StringBlock(nativeGetStringBlock(l), false);
  }
  
  private void decOpenCountLocked() {
    int i = this.mOpenCount - 1;
    this.mOpenCount = i;
    if (i == 0) {
      nativeDestroy(this.mNative);
      AssetManager assetManager = this.mAssets;
      if (assetManager != null)
        assetManager.xmlBlockGone(hashCode()); 
    } 
  }
  
  private static final native long nativeCreate(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  private static final native long nativeCreateParseState(long paramLong, int paramInt);
  
  private static final native void nativeDestroy(long paramLong);
  
  private static final native void nativeDestroyParseState(long paramLong);
  
  @FastNative
  private static final native int nativeGetAttributeCount(long paramLong);
  
  @FastNative
  private static final native int nativeGetAttributeData(long paramLong, int paramInt);
  
  @FastNative
  private static final native int nativeGetAttributeDataType(long paramLong, int paramInt);
  
  @FastNative
  private static final native int nativeGetAttributeIndex(long paramLong, String paramString1, String paramString2);
  
  @FastNative
  private static final native int nativeGetAttributeName(long paramLong, int paramInt);
  
  @FastNative
  private static final native int nativeGetAttributeNamespace(long paramLong, int paramInt);
  
  @FastNative
  private static final native int nativeGetAttributeResource(long paramLong, int paramInt);
  
  @FastNative
  private static final native int nativeGetAttributeStringValue(long paramLong, int paramInt);
  
  @FastNative
  private static final native int nativeGetClassAttribute(long paramLong);
  
  @FastNative
  private static final native int nativeGetIdAttribute(long paramLong);
  
  @FastNative
  private static final native int nativeGetLineNumber(long paramLong);
  
  @FastNative
  static final native int nativeGetName(long paramLong);
  
  @FastNative
  private static final native int nativeGetNamespace(long paramLong);
  
  @FastNative
  private static final native int nativeGetSourceResId(long paramLong);
  
  private static final native long nativeGetStringBlock(long paramLong);
  
  @FastNative
  private static final native int nativeGetStyleAttribute(long paramLong);
  
  @FastNative
  private static final native int nativeGetText(long paramLong);
  
  @FastNative
  static final native int nativeNext(long paramLong);
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mOpen : Z
    //   6: ifeq -> 18
    //   9: aload_0
    //   10: iconst_0
    //   11: putfield mOpen : Z
    //   14: aload_0
    //   15: invokespecial decOpenCountLocked : ()V
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	21	finally
    //   18	20	21	finally
    //   22	24	21	finally
  }
  
  protected void finalize() throws Throwable {
    close();
  }
  
  public XmlResourceParser newParser() {
    return newParser(0);
  }
  
  public XmlResourceParser newParser(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mNative : J
    //   6: lconst_0
    //   7: lcmp
    //   8: ifeq -> 33
    //   11: new android/content/res/XmlBlock$Parser
    //   14: astore_2
    //   15: aload_2
    //   16: aload_0
    //   17: aload_0
    //   18: getfield mNative : J
    //   21: iload_1
    //   22: invokestatic nativeCreateParseState : (JI)J
    //   25: aload_0
    //   26: invokespecial <init> : (Landroid/content/res/XmlBlock;JLandroid/content/res/XmlBlock;)V
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_2
    //   32: areturn
    //   33: aload_0
    //   34: monitorexit
    //   35: aconst_null
    //   36: areturn
    //   37: astore_2
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_2
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   2	31	37	finally
    //   33	35	37	finally
    //   38	40	37	finally
  }
  
  final class Parser implements XmlResourceParser {
    private final XmlBlock mBlock;
    
    private boolean mDecNextDepth = false;
    
    private int mDepth = 0;
    
    private int mEventType = 0;
    
    long mParseState;
    
    private boolean mStarted = false;
    
    Parser(long param1Long, XmlBlock param1XmlBlock1) {
      this.mParseState = param1Long;
      this.mBlock = param1XmlBlock1;
      XmlBlock.access$008(param1XmlBlock1);
    }
    
    public void close() {
      synchronized (this.mBlock) {
        if (this.mParseState != 0L) {
          XmlBlock.nativeDestroyParseState(this.mParseState);
          this.mParseState = 0L;
          this.mBlock.decOpenCountLocked();
        } 
        return;
      } 
    }
    
    public void defineEntityReplacementText(String param1String1, String param1String2) throws XmlPullParserException {
      throw new XmlPullParserException("defineEntityReplacementText() not supported");
    }
    
    protected void finalize() throws Throwable {
      close();
    }
    
    public boolean getAttributeBooleanValue(int param1Int, boolean param1Boolean) {
      int i = XmlBlock.nativeGetAttributeDataType(this.mParseState, param1Int);
      if (i >= 16 && i <= 31) {
        if (XmlBlock.nativeGetAttributeData(this.mParseState, param1Int) != 0) {
          param1Boolean = true;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } 
      return param1Boolean;
    }
    
    public boolean getAttributeBooleanValue(String param1String1, String param1String2, boolean param1Boolean) {
      int i = XmlBlock.nativeGetAttributeIndex(this.mParseState, param1String1, param1String2);
      return (i >= 0) ? getAttributeBooleanValue(i, param1Boolean) : param1Boolean;
    }
    
    public int getAttributeCount() {
      byte b;
      if (this.mEventType == 2) {
        b = XmlBlock.nativeGetAttributeCount(this.mParseState);
      } else {
        b = -1;
      } 
      return b;
    }
    
    public float getAttributeFloatValue(int param1Int, float param1Float) {
      if (XmlBlock.nativeGetAttributeDataType(this.mParseState, param1Int) == 4)
        return Float.intBitsToFloat(XmlBlock.nativeGetAttributeData(this.mParseState, param1Int)); 
      throw new RuntimeException("not a float!");
    }
    
    public float getAttributeFloatValue(String param1String1, String param1String2, float param1Float) {
      int i = XmlBlock.nativeGetAttributeIndex(this.mParseState, param1String1, param1String2);
      return (i >= 0) ? getAttributeFloatValue(i, param1Float) : param1Float;
    }
    
    public int getAttributeIntValue(int param1Int1, int param1Int2) {
      int i = XmlBlock.nativeGetAttributeDataType(this.mParseState, param1Int1);
      return (i >= 16 && i <= 31) ? XmlBlock.nativeGetAttributeData(this.mParseState, param1Int1) : param1Int2;
    }
    
    public int getAttributeIntValue(String param1String1, String param1String2, int param1Int) {
      int i = XmlBlock.nativeGetAttributeIndex(this.mParseState, param1String1, param1String2);
      return (i >= 0) ? getAttributeIntValue(i, param1Int) : param1Int;
    }
    
    public int getAttributeListValue(int param1Int1, String[] param1ArrayOfString, int param1Int2) {
      int i = XmlBlock.nativeGetAttributeDataType(this.mParseState, param1Int1);
      param1Int1 = XmlBlock.nativeGetAttributeData(this.mParseState, param1Int1);
      return (i == 3) ? XmlUtils.convertValueToList(XmlBlock.this.mStrings.get(param1Int1), param1ArrayOfString, param1Int2) : param1Int1;
    }
    
    public int getAttributeListValue(String param1String1, String param1String2, String[] param1ArrayOfString, int param1Int) {
      int i = XmlBlock.nativeGetAttributeIndex(this.mParseState, param1String1, param1String2);
      return (i >= 0) ? getAttributeListValue(i, param1ArrayOfString, param1Int) : param1Int;
    }
    
    public String getAttributeName(int param1Int) {
      int i = XmlBlock.nativeGetAttributeName(this.mParseState, param1Int);
      if (i >= 0)
        return XmlBlock.this.mStrings.get(i).toString(); 
      throw new IndexOutOfBoundsException(String.valueOf(param1Int));
    }
    
    public int getAttributeNameResource(int param1Int) {
      return XmlBlock.nativeGetAttributeResource(this.mParseState, param1Int);
    }
    
    public String getAttributeNamespace(int param1Int) {
      int i = XmlBlock.nativeGetAttributeNamespace(this.mParseState, param1Int);
      if (i >= 0)
        return XmlBlock.this.mStrings.get(i).toString(); 
      if (i == -1)
        return ""; 
      throw new IndexOutOfBoundsException(String.valueOf(param1Int));
    }
    
    public String getAttributePrefix(int param1Int) {
      throw new RuntimeException("getAttributePrefix not supported");
    }
    
    public int getAttributeResourceValue(int param1Int1, int param1Int2) {
      return (XmlBlock.nativeGetAttributeDataType(this.mParseState, param1Int1) == 1) ? XmlBlock.nativeGetAttributeData(this.mParseState, param1Int1) : param1Int2;
    }
    
    public int getAttributeResourceValue(String param1String1, String param1String2, int param1Int) {
      int i = XmlBlock.nativeGetAttributeIndex(this.mParseState, param1String1, param1String2);
      return (i >= 0) ? getAttributeResourceValue(i, param1Int) : param1Int;
    }
    
    public String getAttributeType(int param1Int) {
      return "CDATA";
    }
    
    public int getAttributeUnsignedIntValue(int param1Int1, int param1Int2) {
      int i = XmlBlock.nativeGetAttributeDataType(this.mParseState, param1Int1);
      return (i >= 16 && i <= 31) ? XmlBlock.nativeGetAttributeData(this.mParseState, param1Int1) : param1Int2;
    }
    
    public int getAttributeUnsignedIntValue(String param1String1, String param1String2, int param1Int) {
      int i = XmlBlock.nativeGetAttributeIndex(this.mParseState, param1String1, param1String2);
      return (i >= 0) ? getAttributeUnsignedIntValue(i, param1Int) : param1Int;
    }
    
    public String getAttributeValue(int param1Int) {
      int i = XmlBlock.nativeGetAttributeStringValue(this.mParseState, param1Int);
      if (i >= 0)
        return XmlBlock.this.mStrings.get(i).toString(); 
      i = XmlBlock.nativeGetAttributeDataType(this.mParseState, param1Int);
      if (i != 0)
        return TypedValue.coerceToString(i, XmlBlock.nativeGetAttributeData(this.mParseState, param1Int)); 
      throw new IndexOutOfBoundsException(String.valueOf(param1Int));
    }
    
    public String getAttributeValue(String param1String1, String param1String2) {
      int i = XmlBlock.nativeGetAttributeIndex(this.mParseState, param1String1, param1String2);
      return (i >= 0) ? getAttributeValue(i) : null;
    }
    
    public String getClassAttribute() {
      String str;
      int i = XmlBlock.nativeGetClassAttribute(this.mParseState);
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
    
    public boolean getFeature(String param1String) {
      return "http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(param1String) ? true : ("http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes".equals(param1String));
    }
    
    public String getIdAttribute() {
      String str;
      int i = XmlBlock.nativeGetIdAttribute(this.mParseState);
      if (i >= 0) {
        str = XmlBlock.this.mStrings.get(i).toString();
      } else {
        str = null;
      } 
      return str;
    }
    
    public int getIdAttributeResourceValue(int param1Int) {
      return getAttributeResourceValue(null, "id", param1Int);
    }
    
    public String getInputEncoding() {
      return null;
    }
    
    public int getLineNumber() {
      return XmlBlock.nativeGetLineNumber(this.mParseState);
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
      int i = XmlBlock.nativeGetNamespace(this.mParseState);
      if (i >= 0) {
        str = XmlBlock.this.mStrings.get(i).toString();
      } else {
        str = "";
      } 
      return str;
    }
    
    public String getNamespace(String param1String) {
      throw new RuntimeException("getNamespace() not supported");
    }
    
    public int getNamespaceCount(int param1Int) throws XmlPullParserException {
      throw new XmlPullParserException("getNamespaceCount() not supported");
    }
    
    public String getNamespacePrefix(int param1Int) throws XmlPullParserException {
      throw new XmlPullParserException("getNamespacePrefix() not supported");
    }
    
    public String getNamespaceUri(int param1Int) throws XmlPullParserException {
      throw new XmlPullParserException("getNamespaceUri() not supported");
    }
    
    final CharSequence getPooledString(int param1Int) {
      return XmlBlock.this.mStrings.get(param1Int);
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
    
    public Object getProperty(String param1String) {
      return null;
    }
    
    public int getSourceResId() {
      return XmlBlock.nativeGetSourceResId(this.mParseState);
    }
    
    public int getStyleAttribute() {
      return XmlBlock.nativeGetStyleAttribute(this.mParseState);
    }
    
    public String getText() {
      String str;
      int i = XmlBlock.nativeGetText(this.mParseState);
      if (i >= 0) {
        str = XmlBlock.this.mStrings.get(i).toString();
      } else {
        str = null;
      } 
      return str;
    }
    
    public char[] getTextCharacters(int[] param1ArrayOfint) {
      String str = getText();
      char[] arrayOfChar = null;
      if (str != null) {
        param1ArrayOfint[0] = 0;
        param1ArrayOfint[1] = str.length();
        arrayOfChar = new char[str.length()];
        str.getChars(0, str.length(), arrayOfChar, 0);
      } 
      return arrayOfChar;
    }
    
    public boolean isAttributeDefault(int param1Int) {
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
    
    public void require(int param1Int, String param1String1, String param1String2) throws XmlPullParserException, IOException {
      if (param1Int == getEventType() && (param1String1 == null || param1String1.equals(getNamespace())) && (param1String2 == null || param1String2.equals(getName())))
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("expected ");
      stringBuilder.append(TYPES[param1Int]);
      stringBuilder.append(getPositionDescription());
      throw new XmlPullParserException(stringBuilder.toString());
    }
    
    public void setFeature(String param1String, boolean param1Boolean) throws XmlPullParserException {
      if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(param1String) && param1Boolean)
        return; 
      if ("http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes".equals(param1String) && param1Boolean)
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unsupported feature: ");
      stringBuilder.append(param1String);
      throw new XmlPullParserException(stringBuilder.toString());
    }
    
    public void setInput(InputStream param1InputStream, String param1String) throws XmlPullParserException {
      throw new XmlPullParserException("setInput() not supported");
    }
    
    public void setInput(Reader param1Reader) throws XmlPullParserException {
      throw new XmlPullParserException("setInput() not supported");
    }
    
    public void setProperty(String param1String, Object param1Object) throws XmlPullParserException {
      throw new XmlPullParserException("setProperty() not supported");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/XmlBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
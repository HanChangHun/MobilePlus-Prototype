package android.app.assist;

import android.annotation.SystemApi;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.PooledStringReader;
import android.os.PooledStringWriter;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;

public class ViewNode {
  static final int AUTOFILL_FLAGS_HAS_AUTOFILL_HINTS = 16;
  
  static final int AUTOFILL_FLAGS_HAS_AUTOFILL_OPTIONS = 32;
  
  static final int AUTOFILL_FLAGS_HAS_AUTOFILL_SESSION_ID = 2048;
  
  static final int AUTOFILL_FLAGS_HAS_AUTOFILL_TYPE = 8;
  
  static final int AUTOFILL_FLAGS_HAS_AUTOFILL_VALUE = 4;
  
  static final int AUTOFILL_FLAGS_HAS_AUTOFILL_VIEW_ID = 1;
  
  static final int AUTOFILL_FLAGS_HAS_AUTOFILL_VIRTUAL_VIEW_ID = 2;
  
  static final int AUTOFILL_FLAGS_HAS_HINT_ID_ENTRY = 4096;
  
  static final int AUTOFILL_FLAGS_HAS_HTML_INFO = 64;
  
  static final int AUTOFILL_FLAGS_HAS_MAX_TEXT_EMS = 512;
  
  static final int AUTOFILL_FLAGS_HAS_MAX_TEXT_LENGTH = 1024;
  
  static final int AUTOFILL_FLAGS_HAS_MIN_TEXT_EMS = 256;
  
  static final int AUTOFILL_FLAGS_HAS_TEXT_ID_ENTRY = 128;
  
  static final int FLAGS_ACCESSIBILITY_FOCUSED = 4096;
  
  static final int FLAGS_ACTIVATED = 8192;
  
  static final int FLAGS_ALL_CONTROL = -1048576;
  
  static final int FLAGS_ASSIST_BLOCKED = 128;
  
  static final int FLAGS_CHECKABLE = 256;
  
  static final int FLAGS_CHECKED = 512;
  
  static final int FLAGS_CLICKABLE = 1024;
  
  static final int FLAGS_CONTEXT_CLICKABLE = 16384;
  
  static final int FLAGS_DISABLED = 1;
  
  static final int FLAGS_FOCUSABLE = 16;
  
  static final int FLAGS_FOCUSED = 32;
  
  static final int FLAGS_HAS_ALPHA = 536870912;
  
  static final int FLAGS_HAS_CHILDREN = 1048576;
  
  static final int FLAGS_HAS_COMPLEX_TEXT = 8388608;
  
  static final int FLAGS_HAS_CONTENT_DESCRIPTION = 33554432;
  
  static final int FLAGS_HAS_ELEVATION = 268435456;
  
  static final int FLAGS_HAS_EXTRAS = 4194304;
  
  static final int FLAGS_HAS_ID = 2097152;
  
  static final int FLAGS_HAS_INPUT_TYPE = 262144;
  
  static final int FLAGS_HAS_LARGE_COORDS = 67108864;
  
  static final int FLAGS_HAS_LOCALE_LIST = 65536;
  
  static final int FLAGS_HAS_MATRIX = 1073741824;
  
  static final int FLAGS_HAS_SCROLL = 134217728;
  
  static final int FLAGS_HAS_TEXT = 16777216;
  
  static final int FLAGS_HAS_URL_DOMAIN = 524288;
  
  static final int FLAGS_HAS_URL_SCHEME = 131072;
  
  static final int FLAGS_LONG_CLICKABLE = 2048;
  
  static final int FLAGS_OPAQUE = 32768;
  
  static final int FLAGS_SELECTED = 64;
  
  static final int FLAGS_VISIBILITY_MASK = 12;
  
  public static final int TEXT_COLOR_UNDEFINED = 1;
  
  public static final int TEXT_STYLE_BOLD = 1;
  
  public static final int TEXT_STYLE_ITALIC = 2;
  
  public static final int TEXT_STYLE_STRIKE_THRU = 8;
  
  public static final int TEXT_STYLE_UNDERLINE = 4;
  
  float mAlpha;
  
  int mAutofillFlags;
  
  String[] mAutofillHints;
  
  AutofillId mAutofillId;
  
  CharSequence[] mAutofillOptions;
  
  AssistStructure.AutofillOverlay mAutofillOverlay;
  
  int mAutofillType;
  
  AutofillValue mAutofillValue;
  
  ViewNode[] mChildren;
  
  String mClassName;
  
  CharSequence mContentDescription;
  
  float mElevation;
  
  Bundle mExtras;
  
  int mFlags;
  
  int mHeight;
  
  String mHintIdEntry;
  
  ViewStructure.HtmlInfo mHtmlInfo;
  
  int mId = -1;
  
  String mIdEntry;
  
  String mIdPackage;
  
  String mIdType;
  
  int mImportantForAutofill;
  
  int mInputType;
  
  LocaleList mLocaleList;
  
  Matrix mMatrix;
  
  int mMaxEms;
  
  int mMaxLength;
  
  int mMinEms;
  
  boolean mSanitized;
  
  int mScrollX;
  
  int mScrollY;
  
  AssistStructure.ViewNodeText mText;
  
  String mTextIdEntry;
  
  String mWebDomain;
  
  String mWebScheme;
  
  int mWidth;
  
  int mX;
  
  int mY;
  
  @SystemApi
  public ViewNode() {
    this.mAutofillType = 0;
    this.mMinEms = -1;
    this.mMaxEms = -1;
    this.mMaxLength = -1;
    this.mAlpha = 1.0F;
  }
  
  ViewNode(AssistStructure.ParcelTransferReader paramParcelTransferReader, int paramInt) {
    boolean bool = false;
    this.mAutofillType = 0;
    this.mMinEms = -1;
    this.mMaxEms = -1;
    this.mMaxLength = -1;
    this.mAlpha = 1.0F;
    Parcel parcel = paramParcelTransferReader.readParcel(572662306, paramInt);
    paramParcelTransferReader.mNumReadViews++;
    PooledStringReader pooledStringReader = paramParcelTransferReader.mStringReader;
    this.mClassName = pooledStringReader.readString();
    this.mFlags = parcel.readInt();
    int i = this.mFlags;
    this.mAutofillFlags = parcel.readInt();
    int j = this.mAutofillFlags;
    if ((0x200000 & i) != 0) {
      int k = parcel.readInt();
      this.mId = k;
      if (k != -1) {
        String str = pooledStringReader.readString();
        this.mIdEntry = str;
        if (str != null) {
          this.mIdType = pooledStringReader.readString();
          this.mIdPackage = pooledStringReader.readString();
        } 
      } 
    } 
    if (j != 0) {
      boolean bool1;
      if (parcel.readInt() == 1) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      this.mSanitized = bool1;
      this.mImportantForAutofill = parcel.readInt();
      if ((j & 0x1) != 0) {
        int k = parcel.readInt();
        if ((j & 0x2) != 0) {
          this.mAutofillId = new AutofillId(k, parcel.readInt());
        } else {
          this.mAutofillId = new AutofillId(k);
        } 
        if ((j & 0x800) != 0)
          this.mAutofillId.setSessionId(parcel.readInt()); 
      } 
      if ((j & 0x8) != 0)
        this.mAutofillType = parcel.readInt(); 
      if ((j & 0x10) != 0)
        this.mAutofillHints = parcel.readStringArray(); 
      if ((j & 0x4) != 0)
        this.mAutofillValue = (AutofillValue)parcel.readParcelable(null); 
      if ((j & 0x20) != 0)
        this.mAutofillOptions = parcel.readCharSequenceArray(); 
      if ((j & 0x40) != 0)
        this.mHtmlInfo = (ViewStructure.HtmlInfo)parcel.readParcelable(null); 
      if ((j & 0x100) != 0)
        this.mMinEms = parcel.readInt(); 
      if ((j & 0x200) != 0)
        this.mMaxEms = parcel.readInt(); 
      if ((j & 0x400) != 0)
        this.mMaxLength = parcel.readInt(); 
      if ((j & 0x80) != 0)
        this.mTextIdEntry = pooledStringReader.readString(); 
      if ((j & 0x1000) != 0)
        this.mHintIdEntry = pooledStringReader.readString(); 
    } 
    if ((0x4000000 & i) != 0) {
      this.mX = parcel.readInt();
      this.mY = parcel.readInt();
      this.mWidth = parcel.readInt();
      this.mHeight = parcel.readInt();
    } else {
      j = parcel.readInt();
      this.mX = j & 0x7FFF;
      this.mY = j >> 16 & 0x7FFF;
      j = parcel.readInt();
      this.mWidth = j & 0x7FFF;
      this.mHeight = j >> 16 & 0x7FFF;
    } 
    if ((0x8000000 & i) != 0) {
      this.mScrollX = parcel.readInt();
      this.mScrollY = parcel.readInt();
    } 
    if ((0x40000000 & i) != 0) {
      this.mMatrix = new Matrix();
      parcel.readFloatArray(paramParcelTransferReader.mTmpMatrix);
      this.mMatrix.setValues(paramParcelTransferReader.mTmpMatrix);
    } 
    if ((0x10000000 & i) != 0)
      this.mElevation = parcel.readFloat(); 
    if ((0x20000000 & i) != 0)
      this.mAlpha = parcel.readFloat(); 
    if ((0x2000000 & i) != 0)
      this.mContentDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel); 
    if ((0x1000000 & i) != 0) {
      boolean bool1 = bool;
      if ((0x800000 & i) == 0)
        bool1 = true; 
      this.mText = new AssistStructure.ViewNodeText(parcel, bool1);
    } 
    if ((0x40000 & i) != 0)
      this.mInputType = parcel.readInt(); 
    if ((0x20000 & i) != 0)
      this.mWebScheme = parcel.readString(); 
    if ((0x80000 & i) != 0)
      this.mWebDomain = parcel.readString(); 
    if ((0x10000 & i) != 0)
      this.mLocaleList = (LocaleList)parcel.readParcelable(null); 
    if ((0x400000 & i) != 0)
      this.mExtras = parcel.readBundle(); 
    if ((0x100000 & i) != 0) {
      j = parcel.readInt();
      this.mChildren = new ViewNode[j];
      for (i = 0; i < j; i++)
        this.mChildren[i] = new ViewNode(paramParcelTransferReader, paramInt + 1); 
    } 
  }
  
  public float getAlpha() {
    return this.mAlpha;
  }
  
  public String[] getAutofillHints() {
    return this.mAutofillHints;
  }
  
  public AutofillId getAutofillId() {
    return this.mAutofillId;
  }
  
  public CharSequence[] getAutofillOptions() {
    return this.mAutofillOptions;
  }
  
  public int getAutofillType() {
    return this.mAutofillType;
  }
  
  public AutofillValue getAutofillValue() {
    return this.mAutofillValue;
  }
  
  public ViewNode getChildAt(int paramInt) {
    return this.mChildren[paramInt];
  }
  
  public int getChildCount() {
    boolean bool;
    ViewNode[] arrayOfViewNode = this.mChildren;
    if (arrayOfViewNode != null) {
      bool = arrayOfViewNode.length;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String getClassName() {
    return this.mClassName;
  }
  
  public CharSequence getContentDescription() {
    return this.mContentDescription;
  }
  
  public float getElevation() {
    return this.mElevation;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public int getHeight() {
    return this.mHeight;
  }
  
  public String getHint() {
    AssistStructure.ViewNodeText viewNodeText = this.mText;
    if (viewNodeText != null) {
      String str = viewNodeText.mHint;
    } else {
      viewNodeText = null;
    } 
    return (String)viewNodeText;
  }
  
  public String getHintIdEntry() {
    return this.mHintIdEntry;
  }
  
  public ViewStructure.HtmlInfo getHtmlInfo() {
    return this.mHtmlInfo;
  }
  
  public int getId() {
    return this.mId;
  }
  
  public String getIdEntry() {
    return this.mIdEntry;
  }
  
  public String getIdPackage() {
    return this.mIdPackage;
  }
  
  public String getIdType() {
    return this.mIdType;
  }
  
  public int getImportantForAutofill() {
    return this.mImportantForAutofill;
  }
  
  public int getInputType() {
    return this.mInputType;
  }
  
  public int getLeft() {
    return this.mX;
  }
  
  public LocaleList getLocaleList() {
    return this.mLocaleList;
  }
  
  public int getMaxTextEms() {
    return this.mMaxEms;
  }
  
  public int getMaxTextLength() {
    return this.mMaxLength;
  }
  
  public int getMinTextEms() {
    return this.mMinEms;
  }
  
  public int getScrollX() {
    return this.mScrollX;
  }
  
  public int getScrollY() {
    return this.mScrollY;
  }
  
  public CharSequence getText() {
    AssistStructure.ViewNodeText viewNodeText = this.mText;
    if (viewNodeText != null) {
      CharSequence charSequence = viewNodeText.mText;
    } else {
      viewNodeText = null;
    } 
    return (CharSequence)viewNodeText;
  }
  
  public int getTextBackgroundColor() {
    boolean bool;
    AssistStructure.ViewNodeText viewNodeText = this.mText;
    if (viewNodeText != null) {
      bool = viewNodeText.mTextBackgroundColor;
    } else {
      bool = true;
    } 
    return bool;
  }
  
  public int getTextColor() {
    boolean bool;
    AssistStructure.ViewNodeText viewNodeText = this.mText;
    if (viewNodeText != null) {
      bool = viewNodeText.mTextColor;
    } else {
      bool = true;
    } 
    return bool;
  }
  
  public String getTextIdEntry() {
    return this.mTextIdEntry;
  }
  
  public int[] getTextLineBaselines() {
    AssistStructure.ViewNodeText viewNodeText = this.mText;
    if (viewNodeText != null) {
      int[] arrayOfInt = viewNodeText.mLineBaselines;
    } else {
      viewNodeText = null;
    } 
    return (int[])viewNodeText;
  }
  
  public int[] getTextLineCharOffsets() {
    AssistStructure.ViewNodeText viewNodeText = this.mText;
    if (viewNodeText != null) {
      int[] arrayOfInt = viewNodeText.mLineCharOffsets;
    } else {
      viewNodeText = null;
    } 
    return (int[])viewNodeText;
  }
  
  public int getTextSelectionEnd() {
    byte b;
    AssistStructure.ViewNodeText viewNodeText = this.mText;
    if (viewNodeText != null) {
      b = viewNodeText.mTextSelectionEnd;
    } else {
      b = -1;
    } 
    return b;
  }
  
  public int getTextSelectionStart() {
    byte b;
    AssistStructure.ViewNodeText viewNodeText = this.mText;
    if (viewNodeText != null) {
      b = viewNodeText.mTextSelectionStart;
    } else {
      b = -1;
    } 
    return b;
  }
  
  public float getTextSize() {
    float f;
    AssistStructure.ViewNodeText viewNodeText = this.mText;
    if (viewNodeText != null) {
      f = viewNodeText.mTextSize;
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public int getTextStyle() {
    boolean bool;
    AssistStructure.ViewNodeText viewNodeText = this.mText;
    if (viewNodeText != null) {
      bool = viewNodeText.mTextStyle;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int getTop() {
    return this.mY;
  }
  
  public Matrix getTransformation() {
    return this.mMatrix;
  }
  
  public int getVisibility() {
    return this.mFlags & 0xC;
  }
  
  public String getWebDomain() {
    return this.mWebDomain;
  }
  
  public String getWebScheme() {
    return this.mWebScheme;
  }
  
  public int getWidth() {
    return this.mWidth;
  }
  
  public boolean isAccessibilityFocused() {
    boolean bool;
    if ((this.mFlags & 0x1000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isActivated() {
    boolean bool;
    if ((this.mFlags & 0x2000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isAssistBlocked() {
    boolean bool;
    if ((this.mFlags & 0x80) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isCheckable() {
    boolean bool;
    if ((this.mFlags & 0x100) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isChecked() {
    boolean bool;
    if ((this.mFlags & 0x200) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isClickable() {
    boolean bool;
    if ((this.mFlags & 0x400) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isContextClickable() {
    boolean bool;
    if ((this.mFlags & 0x4000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isEnabled() {
    int i = this.mFlags;
    boolean bool = true;
    if ((i & 0x1) != 0)
      bool = false; 
    return bool;
  }
  
  public boolean isFocusable() {
    boolean bool;
    if ((this.mFlags & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isFocused() {
    boolean bool;
    if ((this.mFlags & 0x20) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isLongClickable() {
    boolean bool;
    if ((this.mFlags & 0x800) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isOpaque() {
    boolean bool;
    if ((this.mFlags & 0x8000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isSanitized() {
    return this.mSanitized;
  }
  
  public boolean isSelected() {
    boolean bool;
    if ((this.mFlags & 0x40) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setAutofillOverlay(AssistStructure.AutofillOverlay paramAutofillOverlay) {
    this.mAutofillOverlay = paramAutofillOverlay;
  }
  
  public void setWebDomain(String paramString) {
    if (paramString == null)
      return; 
    Uri uri = Uri.parse(paramString);
    if (uri == null) {
      Log.w("AssistStructure", "Failed to parse web domain");
      return;
    } 
    String str = uri.getScheme();
    this.mWebScheme = str;
    if (str == null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("http://");
      stringBuilder.append(paramString);
      uri = Uri.parse(stringBuilder.toString());
    } 
    this.mWebDomain = uri.getHost();
  }
  
  public void updateAutofillValue(AutofillValue paramAutofillValue) {
    this.mAutofillValue = paramAutofillValue;
    if (paramAutofillValue.isText()) {
      if (this.mText == null)
        this.mText = new AssistStructure.ViewNodeText(); 
      this.mText.mText = paramAutofillValue.getTextValue();
    } 
  }
  
  int writeSelfToParcel(Parcel paramParcel, PooledStringWriter paramPooledStringWriter, boolean paramBoolean, float[] paramArrayOffloat) {
    // Byte code:
    //   0: iconst_1
    //   1: istore #5
    //   3: aload_0
    //   4: getfield mFlags : I
    //   7: ldc_w 1048575
    //   10: iand
    //   11: istore #6
    //   13: iconst_0
    //   14: istore #7
    //   16: iload #6
    //   18: istore #8
    //   20: aload_0
    //   21: getfield mId : I
    //   24: iconst_m1
    //   25: if_icmpeq -> 35
    //   28: iload #6
    //   30: ldc 2097152
    //   32: ior
    //   33: istore #8
    //   35: aload_0
    //   36: getfield mX : I
    //   39: sipush #-32768
    //   42: iand
    //   43: ifne -> 109
    //   46: aload_0
    //   47: getfield mY : I
    //   50: sipush #-32768
    //   53: iand
    //   54: ifne -> 109
    //   57: aload_0
    //   58: getfield mWidth : I
    //   61: sipush #-32768
    //   64: iand
    //   65: ifeq -> 74
    //   68: iconst_1
    //   69: istore #9
    //   71: goto -> 77
    //   74: iconst_0
    //   75: istore #9
    //   77: aload_0
    //   78: getfield mHeight : I
    //   81: sipush #-32768
    //   84: iand
    //   85: ifeq -> 94
    //   88: iconst_1
    //   89: istore #10
    //   91: goto -> 97
    //   94: iconst_0
    //   95: istore #10
    //   97: iload #8
    //   99: istore #6
    //   101: iload #9
    //   103: iload #10
    //   105: ior
    //   106: ifeq -> 116
    //   109: iload #8
    //   111: ldc 67108864
    //   113: ior
    //   114: istore #6
    //   116: aload_0
    //   117: getfield mScrollX : I
    //   120: ifne -> 134
    //   123: iload #6
    //   125: istore #8
    //   127: aload_0
    //   128: getfield mScrollY : I
    //   131: ifeq -> 141
    //   134: iload #6
    //   136: ldc 134217728
    //   138: ior
    //   139: istore #8
    //   141: iload #8
    //   143: istore #6
    //   145: aload_0
    //   146: getfield mMatrix : Landroid/graphics/Matrix;
    //   149: ifnull -> 159
    //   152: iload #8
    //   154: ldc 1073741824
    //   156: ior
    //   157: istore #6
    //   159: iload #6
    //   161: istore #8
    //   163: aload_0
    //   164: getfield mElevation : F
    //   167: fconst_0
    //   168: fcmpl
    //   169: ifeq -> 179
    //   172: iload #6
    //   174: ldc 268435456
    //   176: ior
    //   177: istore #8
    //   179: iload #8
    //   181: istore #9
    //   183: aload_0
    //   184: getfield mAlpha : F
    //   187: fconst_1
    //   188: fcmpl
    //   189: ifeq -> 199
    //   192: iload #8
    //   194: ldc 536870912
    //   196: ior
    //   197: istore #9
    //   199: iload #9
    //   201: istore #6
    //   203: aload_0
    //   204: getfield mContentDescription : Ljava/lang/CharSequence;
    //   207: ifnull -> 217
    //   210: iload #9
    //   212: ldc 33554432
    //   214: ior
    //   215: istore #6
    //   217: aload_0
    //   218: getfield mText : Landroid/app/assist/AssistStructure$ViewNodeText;
    //   221: astore #11
    //   223: iload #6
    //   225: istore #8
    //   227: aload #11
    //   229: ifnull -> 258
    //   232: iload #6
    //   234: ldc 16777216
    //   236: ior
    //   237: istore #6
    //   239: iload #6
    //   241: istore #8
    //   243: aload #11
    //   245: invokevirtual isSimple : ()Z
    //   248: ifne -> 258
    //   251: iload #6
    //   253: ldc 8388608
    //   255: ior
    //   256: istore #8
    //   258: iload #8
    //   260: istore #9
    //   262: aload_0
    //   263: getfield mInputType : I
    //   266: ifeq -> 276
    //   269: iload #8
    //   271: ldc 262144
    //   273: ior
    //   274: istore #9
    //   276: iload #9
    //   278: istore #6
    //   280: aload_0
    //   281: getfield mWebScheme : Ljava/lang/String;
    //   284: ifnull -> 294
    //   287: iload #9
    //   289: ldc 131072
    //   291: ior
    //   292: istore #6
    //   294: iload #6
    //   296: istore #8
    //   298: aload_0
    //   299: getfield mWebDomain : Ljava/lang/String;
    //   302: ifnull -> 312
    //   305: iload #6
    //   307: ldc 524288
    //   309: ior
    //   310: istore #8
    //   312: iload #8
    //   314: istore #6
    //   316: aload_0
    //   317: getfield mLocaleList : Landroid/os/LocaleList;
    //   320: ifnull -> 330
    //   323: iload #8
    //   325: ldc 65536
    //   327: ior
    //   328: istore #6
    //   330: iload #6
    //   332: istore #8
    //   334: aload_0
    //   335: getfield mExtras : Landroid/os/Bundle;
    //   338: ifnull -> 348
    //   341: iload #6
    //   343: ldc 4194304
    //   345: ior
    //   346: istore #8
    //   348: iload #8
    //   350: istore #6
    //   352: aload_0
    //   353: getfield mChildren : [Landroid/app/assist/AssistStructure$ViewNode;
    //   356: ifnull -> 366
    //   359: iload #8
    //   361: ldc 1048576
    //   363: ior
    //   364: istore #6
    //   366: aload_0
    //   367: getfield mAutofillId : Landroid/view/autofill/AutofillId;
    //   370: astore #11
    //   372: iload #7
    //   374: istore #8
    //   376: aload #11
    //   378: ifnull -> 426
    //   381: iconst_0
    //   382: iconst_1
    //   383: ior
    //   384: istore #8
    //   386: iload #8
    //   388: istore #9
    //   390: aload #11
    //   392: invokevirtual isVirtualInt : ()Z
    //   395: ifeq -> 404
    //   398: iload #8
    //   400: iconst_2
    //   401: ior
    //   402: istore #9
    //   404: iload #9
    //   406: istore #8
    //   408: aload_0
    //   409: getfield mAutofillId : Landroid/view/autofill/AutofillId;
    //   412: invokevirtual hasSession : ()Z
    //   415: ifeq -> 426
    //   418: iload #9
    //   420: sipush #2048
    //   423: ior
    //   424: istore #8
    //   426: iload #8
    //   428: istore #9
    //   430: aload_0
    //   431: getfield mAutofillValue : Landroid/view/autofill/AutofillValue;
    //   434: ifnull -> 443
    //   437: iload #8
    //   439: iconst_4
    //   440: ior
    //   441: istore #9
    //   443: iload #9
    //   445: istore #8
    //   447: aload_0
    //   448: getfield mAutofillType : I
    //   451: ifeq -> 461
    //   454: iload #9
    //   456: bipush #8
    //   458: ior
    //   459: istore #8
    //   461: iload #8
    //   463: istore #9
    //   465: aload_0
    //   466: getfield mAutofillHints : [Ljava/lang/String;
    //   469: ifnull -> 479
    //   472: iload #8
    //   474: bipush #16
    //   476: ior
    //   477: istore #9
    //   479: iload #9
    //   481: istore #10
    //   483: aload_0
    //   484: getfield mAutofillOptions : [Ljava/lang/CharSequence;
    //   487: ifnull -> 497
    //   490: iload #9
    //   492: bipush #32
    //   494: ior
    //   495: istore #10
    //   497: iload #10
    //   499: istore #8
    //   501: aload_0
    //   502: getfield mHtmlInfo : Landroid/view/ViewStructure$HtmlInfo;
    //   505: instanceof android/os/Parcelable
    //   508: ifeq -> 518
    //   511: iload #10
    //   513: bipush #64
    //   515: ior
    //   516: istore #8
    //   518: iload #8
    //   520: istore #9
    //   522: aload_0
    //   523: getfield mMinEms : I
    //   526: iconst_m1
    //   527: if_icmple -> 538
    //   530: iload #8
    //   532: sipush #256
    //   535: ior
    //   536: istore #9
    //   538: iload #9
    //   540: istore #10
    //   542: aload_0
    //   543: getfield mMaxEms : I
    //   546: iconst_m1
    //   547: if_icmple -> 558
    //   550: iload #9
    //   552: sipush #512
    //   555: ior
    //   556: istore #10
    //   558: iload #10
    //   560: istore #8
    //   562: aload_0
    //   563: getfield mMaxLength : I
    //   566: iconst_m1
    //   567: if_icmple -> 578
    //   570: iload #10
    //   572: sipush #1024
    //   575: ior
    //   576: istore #8
    //   578: iload #8
    //   580: istore #9
    //   582: aload_0
    //   583: getfield mTextIdEntry : Ljava/lang/String;
    //   586: ifnull -> 597
    //   589: iload #8
    //   591: sipush #128
    //   594: ior
    //   595: istore #9
    //   597: iload #9
    //   599: istore #10
    //   601: aload_0
    //   602: getfield mHintIdEntry : Ljava/lang/String;
    //   605: ifnull -> 616
    //   608: iload #9
    //   610: sipush #4096
    //   613: ior
    //   614: istore #10
    //   616: aload_2
    //   617: aload_0
    //   618: getfield mClassName : Ljava/lang/String;
    //   621: invokevirtual writeString : (Ljava/lang/String;)V
    //   624: iload #6
    //   626: istore #9
    //   628: iload #9
    //   630: istore #8
    //   632: iload #10
    //   634: ifeq -> 660
    //   637: aload_0
    //   638: getfield mSanitized : Z
    //   641: ifne -> 652
    //   644: iload #9
    //   646: istore #8
    //   648: iload_3
    //   649: ifne -> 660
    //   652: iload #6
    //   654: sipush #-513
    //   657: iand
    //   658: istore #8
    //   660: aload_0
    //   661: getfield mAutofillOverlay : Landroid/app/assist/AssistStructure$AutofillOverlay;
    //   664: astore #11
    //   666: iload #8
    //   668: istore #9
    //   670: aload #11
    //   672: ifnull -> 700
    //   675: aload #11
    //   677: getfield focused : Z
    //   680: ifeq -> 693
    //   683: iload #8
    //   685: bipush #32
    //   687: ior
    //   688: istore #9
    //   690: goto -> 700
    //   693: iload #8
    //   695: bipush #-33
    //   697: iand
    //   698: istore #9
    //   700: aload_1
    //   701: iload #9
    //   703: invokevirtual writeInt : (I)V
    //   706: aload_1
    //   707: iload #10
    //   709: invokevirtual writeInt : (I)V
    //   712: ldc 2097152
    //   714: iload #6
    //   716: iand
    //   717: ifeq -> 767
    //   720: aload_1
    //   721: aload_0
    //   722: getfield mId : I
    //   725: invokevirtual writeInt : (I)V
    //   728: aload_0
    //   729: getfield mId : I
    //   732: iconst_m1
    //   733: if_icmpeq -> 767
    //   736: aload_2
    //   737: aload_0
    //   738: getfield mIdEntry : Ljava/lang/String;
    //   741: invokevirtual writeString : (Ljava/lang/String;)V
    //   744: aload_0
    //   745: getfield mIdEntry : Ljava/lang/String;
    //   748: ifnull -> 767
    //   751: aload_2
    //   752: aload_0
    //   753: getfield mIdType : Ljava/lang/String;
    //   756: invokevirtual writeString : (Ljava/lang/String;)V
    //   759: aload_2
    //   760: aload_0
    //   761: getfield mIdPackage : Ljava/lang/String;
    //   764: invokevirtual writeString : (Ljava/lang/String;)V
    //   767: iload #10
    //   769: ifeq -> 1085
    //   772: aload_1
    //   773: aload_0
    //   774: getfield mSanitized : Z
    //   777: invokevirtual writeInt : (I)V
    //   780: aload_1
    //   781: aload_0
    //   782: getfield mImportantForAutofill : I
    //   785: invokevirtual writeInt : (I)V
    //   788: aload_0
    //   789: getfield mSanitized : Z
    //   792: ifne -> 807
    //   795: iload_3
    //   796: ifne -> 802
    //   799: goto -> 807
    //   802: iconst_0
    //   803: istore_3
    //   804: goto -> 809
    //   807: iconst_1
    //   808: istore_3
    //   809: iload #10
    //   811: iconst_1
    //   812: iand
    //   813: ifeq -> 865
    //   816: aload_1
    //   817: aload_0
    //   818: getfield mAutofillId : Landroid/view/autofill/AutofillId;
    //   821: invokevirtual getViewId : ()I
    //   824: invokevirtual writeInt : (I)V
    //   827: iload #10
    //   829: iconst_2
    //   830: iand
    //   831: ifeq -> 845
    //   834: aload_1
    //   835: aload_0
    //   836: getfield mAutofillId : Landroid/view/autofill/AutofillId;
    //   839: invokevirtual getVirtualChildIntId : ()I
    //   842: invokevirtual writeInt : (I)V
    //   845: iload #10
    //   847: sipush #2048
    //   850: iand
    //   851: ifeq -> 865
    //   854: aload_1
    //   855: aload_0
    //   856: getfield mAutofillId : Landroid/view/autofill/AutofillId;
    //   859: invokevirtual getSessionId : ()I
    //   862: invokevirtual writeInt : (I)V
    //   865: iload #10
    //   867: bipush #8
    //   869: iand
    //   870: ifeq -> 881
    //   873: aload_1
    //   874: aload_0
    //   875: getfield mAutofillType : I
    //   878: invokevirtual writeInt : (I)V
    //   881: iload #10
    //   883: bipush #16
    //   885: iand
    //   886: ifeq -> 897
    //   889: aload_1
    //   890: aload_0
    //   891: getfield mAutofillHints : [Ljava/lang/String;
    //   894: invokevirtual writeStringArray : ([Ljava/lang/String;)V
    //   897: iload #10
    //   899: iconst_4
    //   900: iand
    //   901: ifeq -> 958
    //   904: iload_3
    //   905: ifeq -> 917
    //   908: aload_0
    //   909: getfield mAutofillValue : Landroid/view/autofill/AutofillValue;
    //   912: astore #11
    //   914: goto -> 951
    //   917: aload_0
    //   918: getfield mAutofillOverlay : Landroid/app/assist/AssistStructure$AutofillOverlay;
    //   921: astore #11
    //   923: aload #11
    //   925: ifnull -> 948
    //   928: aload #11
    //   930: getfield value : Landroid/view/autofill/AutofillValue;
    //   933: ifnull -> 948
    //   936: aload_0
    //   937: getfield mAutofillOverlay : Landroid/app/assist/AssistStructure$AutofillOverlay;
    //   940: getfield value : Landroid/view/autofill/AutofillValue;
    //   943: astore #11
    //   945: goto -> 951
    //   948: aconst_null
    //   949: astore #11
    //   951: aload_1
    //   952: aload #11
    //   954: iconst_0
    //   955: invokevirtual writeParcelable : (Landroid/os/Parcelable;I)V
    //   958: iload #10
    //   960: bipush #32
    //   962: iand
    //   963: ifeq -> 974
    //   966: aload_1
    //   967: aload_0
    //   968: getfield mAutofillOptions : [Ljava/lang/CharSequence;
    //   971: invokevirtual writeCharSequenceArray : ([Ljava/lang/CharSequence;)V
    //   974: iload #10
    //   976: bipush #64
    //   978: iand
    //   979: ifeq -> 994
    //   982: aload_1
    //   983: aload_0
    //   984: getfield mHtmlInfo : Landroid/view/ViewStructure$HtmlInfo;
    //   987: checkcast android/os/Parcelable
    //   990: iconst_0
    //   991: invokevirtual writeParcelable : (Landroid/os/Parcelable;I)V
    //   994: iload #10
    //   996: sipush #256
    //   999: iand
    //   1000: ifeq -> 1011
    //   1003: aload_1
    //   1004: aload_0
    //   1005: getfield mMinEms : I
    //   1008: invokevirtual writeInt : (I)V
    //   1011: iload #10
    //   1013: sipush #512
    //   1016: iand
    //   1017: ifeq -> 1028
    //   1020: aload_1
    //   1021: aload_0
    //   1022: getfield mMaxEms : I
    //   1025: invokevirtual writeInt : (I)V
    //   1028: iload #10
    //   1030: sipush #1024
    //   1033: iand
    //   1034: ifeq -> 1045
    //   1037: aload_1
    //   1038: aload_0
    //   1039: getfield mMaxLength : I
    //   1042: invokevirtual writeInt : (I)V
    //   1045: iload #10
    //   1047: sipush #128
    //   1050: iand
    //   1051: ifeq -> 1062
    //   1054: aload_2
    //   1055: aload_0
    //   1056: getfield mTextIdEntry : Ljava/lang/String;
    //   1059: invokevirtual writeString : (Ljava/lang/String;)V
    //   1062: iload_3
    //   1063: istore #5
    //   1065: iload #10
    //   1067: sipush #4096
    //   1070: iand
    //   1071: ifeq -> 1085
    //   1074: aload_2
    //   1075: aload_0
    //   1076: getfield mHintIdEntry : Ljava/lang/String;
    //   1079: invokevirtual writeString : (Ljava/lang/String;)V
    //   1082: iload_3
    //   1083: istore #5
    //   1085: iload #6
    //   1087: ldc 67108864
    //   1089: iand
    //   1090: ifeq -> 1128
    //   1093: aload_1
    //   1094: aload_0
    //   1095: getfield mX : I
    //   1098: invokevirtual writeInt : (I)V
    //   1101: aload_1
    //   1102: aload_0
    //   1103: getfield mY : I
    //   1106: invokevirtual writeInt : (I)V
    //   1109: aload_1
    //   1110: aload_0
    //   1111: getfield mWidth : I
    //   1114: invokevirtual writeInt : (I)V
    //   1117: aload_1
    //   1118: aload_0
    //   1119: getfield mHeight : I
    //   1122: invokevirtual writeInt : (I)V
    //   1125: goto -> 1160
    //   1128: aload_1
    //   1129: aload_0
    //   1130: getfield mY : I
    //   1133: bipush #16
    //   1135: ishl
    //   1136: aload_0
    //   1137: getfield mX : I
    //   1140: ior
    //   1141: invokevirtual writeInt : (I)V
    //   1144: aload_1
    //   1145: aload_0
    //   1146: getfield mHeight : I
    //   1149: bipush #16
    //   1151: ishl
    //   1152: aload_0
    //   1153: getfield mWidth : I
    //   1156: ior
    //   1157: invokevirtual writeInt : (I)V
    //   1160: iload #6
    //   1162: ldc 134217728
    //   1164: iand
    //   1165: ifeq -> 1184
    //   1168: aload_1
    //   1169: aload_0
    //   1170: getfield mScrollX : I
    //   1173: invokevirtual writeInt : (I)V
    //   1176: aload_1
    //   1177: aload_0
    //   1178: getfield mScrollY : I
    //   1181: invokevirtual writeInt : (I)V
    //   1184: iload #6
    //   1186: ldc 1073741824
    //   1188: iand
    //   1189: ifeq -> 1207
    //   1192: aload_0
    //   1193: getfield mMatrix : Landroid/graphics/Matrix;
    //   1196: aload #4
    //   1198: invokevirtual getValues : ([F)V
    //   1201: aload_1
    //   1202: aload #4
    //   1204: invokevirtual writeFloatArray : ([F)V
    //   1207: iload #6
    //   1209: ldc 268435456
    //   1211: iand
    //   1212: ifeq -> 1223
    //   1215: aload_1
    //   1216: aload_0
    //   1217: getfield mElevation : F
    //   1220: invokevirtual writeFloat : (F)V
    //   1223: iload #6
    //   1225: ldc 536870912
    //   1227: iand
    //   1228: ifeq -> 1239
    //   1231: aload_1
    //   1232: aload_0
    //   1233: getfield mAlpha : F
    //   1236: invokevirtual writeFloat : (F)V
    //   1239: iload #6
    //   1241: ldc 33554432
    //   1243: iand
    //   1244: ifeq -> 1256
    //   1247: aload_0
    //   1248: getfield mContentDescription : Ljava/lang/CharSequence;
    //   1251: aload_1
    //   1252: iconst_0
    //   1253: invokestatic writeToParcel : (Ljava/lang/CharSequence;Landroid/os/Parcel;I)V
    //   1256: iload #6
    //   1258: ldc 16777216
    //   1260: iand
    //   1261: ifeq -> 1292
    //   1264: aload_0
    //   1265: getfield mText : Landroid/app/assist/AssistStructure$ViewNodeText;
    //   1268: astore_2
    //   1269: iload #6
    //   1271: ldc 8388608
    //   1273: iand
    //   1274: ifne -> 1282
    //   1277: iconst_1
    //   1278: istore_3
    //   1279: goto -> 1284
    //   1282: iconst_0
    //   1283: istore_3
    //   1284: aload_2
    //   1285: aload_1
    //   1286: iload_3
    //   1287: iload #5
    //   1289: invokevirtual writeToParcel : (Landroid/os/Parcel;ZZ)V
    //   1292: iload #6
    //   1294: ldc 262144
    //   1296: iand
    //   1297: ifeq -> 1308
    //   1300: aload_1
    //   1301: aload_0
    //   1302: getfield mInputType : I
    //   1305: invokevirtual writeInt : (I)V
    //   1308: iload #6
    //   1310: ldc 131072
    //   1312: iand
    //   1313: ifeq -> 1324
    //   1316: aload_1
    //   1317: aload_0
    //   1318: getfield mWebScheme : Ljava/lang/String;
    //   1321: invokevirtual writeString : (Ljava/lang/String;)V
    //   1324: iload #6
    //   1326: ldc 524288
    //   1328: iand
    //   1329: ifeq -> 1340
    //   1332: aload_1
    //   1333: aload_0
    //   1334: getfield mWebDomain : Ljava/lang/String;
    //   1337: invokevirtual writeString : (Ljava/lang/String;)V
    //   1340: iload #6
    //   1342: ldc 65536
    //   1344: iand
    //   1345: ifeq -> 1357
    //   1348: aload_1
    //   1349: aload_0
    //   1350: getfield mLocaleList : Landroid/os/LocaleList;
    //   1353: iconst_0
    //   1354: invokevirtual writeParcelable : (Landroid/os/Parcelable;I)V
    //   1357: iload #6
    //   1359: ldc 4194304
    //   1361: iand
    //   1362: ifeq -> 1373
    //   1365: aload_1
    //   1366: aload_0
    //   1367: getfield mExtras : Landroid/os/Bundle;
    //   1370: invokevirtual writeBundle : (Landroid/os/Bundle;)V
    //   1373: iload #6
    //   1375: ireturn
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure$ViewNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
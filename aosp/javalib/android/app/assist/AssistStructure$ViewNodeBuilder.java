package android.app.assist;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.TextUtils;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import java.util.Objects;

class ViewNodeBuilder extends ViewStructure {
  final AssistStructure mAssist;
  
  final boolean mAsync;
  
  final AssistStructure.ViewNode mNode;
  
  ViewNodeBuilder(AssistStructure paramAssistStructure, AssistStructure.ViewNode paramViewNode, boolean paramBoolean) {
    this.mAssist = paramAssistStructure;
    this.mNode = paramViewNode;
    this.mAsync = paramBoolean;
  }
  
  private final AssistStructure.ViewNodeText getNodeText() {
    if (this.mNode.mText != null)
      return this.mNode.mText; 
    this.mNode.mText = new AssistStructure.ViewNodeText();
    return this.mNode.mText;
  }
  
  public int addChildCount(int paramInt) {
    if (this.mNode.mChildren == null) {
      setChildCount(paramInt);
      return 0;
    } 
    int i = this.mNode.mChildren.length;
    AssistStructure.ViewNode[] arrayOfViewNode = new AssistStructure.ViewNode[i + paramInt];
    System.arraycopy(this.mNode.mChildren, 0, arrayOfViewNode, 0, i);
    this.mNode.mChildren = arrayOfViewNode;
    return i;
  }
  
  public void asyncCommit() {
    synchronized (this.mAssist) {
      if (this.mAsync) {
        if (AssistStructure.access$600(this.mAssist).remove(this)) {
          this.mAssist.notifyAll();
          return;
        } 
        IllegalStateException illegalStateException1 = new IllegalStateException();
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("Child ");
        stringBuilder1.append(this);
        stringBuilder1.append(" already committed");
        this(stringBuilder1.toString());
        throw illegalStateException1;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Child ");
      stringBuilder.append(this);
      stringBuilder.append(" was not created with ViewStructure.asyncNewChild");
      this(stringBuilder.toString());
      throw illegalStateException;
    } 
  }
  
  public ViewStructure asyncNewChild(int paramInt) {
    synchronized (this.mAssist) {
      AssistStructure.ViewNode viewNode = new AssistStructure.ViewNode();
      this();
      this.mNode.mChildren[paramInt] = viewNode;
      ViewNodeBuilder viewNodeBuilder = new ViewNodeBuilder();
      this(this.mAssist, viewNode, true);
      AssistStructure.access$600(this.mAssist).add(viewNodeBuilder);
      return viewNodeBuilder;
    } 
  }
  
  public AutofillId getAutofillId() {
    return this.mNode.mAutofillId;
  }
  
  public int getChildCount() {
    boolean bool;
    if (this.mNode.mChildren != null) {
      bool = this.mNode.mChildren.length;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Bundle getExtras() {
    if (this.mNode.mExtras != null)
      return this.mNode.mExtras; 
    this.mNode.mExtras = new Bundle();
    return this.mNode.mExtras;
  }
  
  public CharSequence getHint() {
    CharSequence charSequence;
    if (this.mNode.mText != null) {
      charSequence = this.mNode.mText.mHint;
    } else {
      charSequence = null;
    } 
    return charSequence;
  }
  
  public Rect getTempRect() {
    return AssistStructure.access$700(this.mAssist);
  }
  
  public CharSequence getText() {
    CharSequence charSequence;
    if (this.mNode.mText != null) {
      charSequence = this.mNode.mText.mText;
    } else {
      charSequence = null;
    } 
    return charSequence;
  }
  
  public int getTextSelectionEnd() {
    byte b;
    if (this.mNode.mText != null) {
      b = this.mNode.mText.mTextSelectionEnd;
    } else {
      b = -1;
    } 
    return b;
  }
  
  public int getTextSelectionStart() {
    byte b;
    if (this.mNode.mText != null) {
      b = this.mNode.mText.mTextSelectionStart;
    } else {
      b = -1;
    } 
    return b;
  }
  
  public boolean hasExtras() {
    boolean bool;
    if (this.mNode.mExtras != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public ViewStructure newChild(int paramInt) {
    AssistStructure.ViewNode viewNode = new AssistStructure.ViewNode();
    this.mNode.mChildren[paramInt] = viewNode;
    return new ViewNodeBuilder(this.mAssist, viewNode, false);
  }
  
  public ViewStructure.HtmlInfo.Builder newHtmlInfoBuilder(String paramString) {
    return new AssistStructure.HtmlInfoNodeBuilder(paramString);
  }
  
  public void setAccessibilityFocused(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFFEFFF | bool;
  }
  
  public void setActivated(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFFDFFF | bool;
  }
  
  public void setAlpha(float paramFloat) {
    this.mNode.mAlpha = paramFloat;
  }
  
  public void setAssistBlocked(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFFFF7F | bool;
  }
  
  public void setAutofillHints(String[] paramArrayOfString) {
    this.mNode.mAutofillHints = paramArrayOfString;
  }
  
  public void setAutofillId(AutofillId paramAutofillId) {
    this.mNode.mAutofillId = paramAutofillId;
  }
  
  public void setAutofillId(AutofillId paramAutofillId, int paramInt) {
    this.mNode.mAutofillId = new AutofillId(paramAutofillId, paramInt);
  }
  
  public void setAutofillOptions(CharSequence[] paramArrayOfCharSequence) {
    this.mNode.mAutofillOptions = paramArrayOfCharSequence;
  }
  
  public void setAutofillType(int paramInt) {
    this.mNode.mAutofillType = paramInt;
  }
  
  public void setAutofillValue(AutofillValue paramAutofillValue) {
    this.mNode.mAutofillValue = paramAutofillValue;
  }
  
  public void setCheckable(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFFFEFF | bool;
  }
  
  public void setChecked(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFFFDFF | bool;
  }
  
  public void setChildCount(int paramInt) {
    this.mNode.mChildren = new AssistStructure.ViewNode[paramInt];
  }
  
  public void setClassName(String paramString) {
    this.mNode.mClassName = paramString;
  }
  
  public void setClickable(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFFFBFF | bool;
  }
  
  public void setContentDescription(CharSequence paramCharSequence) {
    this.mNode.mContentDescription = paramCharSequence;
  }
  
  public void setContextClickable(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFFBFFF | bool;
  }
  
  public void setDataIsSensitive(boolean paramBoolean) {
    this.mNode.mSanitized = paramBoolean ^ true;
  }
  
  public void setDimens(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    this.mNode.mX = paramInt1;
    this.mNode.mY = paramInt2;
    this.mNode.mScrollX = paramInt3;
    this.mNode.mScrollY = paramInt4;
    this.mNode.mWidth = paramInt5;
    this.mNode.mHeight = paramInt6;
  }
  
  public void setElevation(float paramFloat) {
    this.mNode.mElevation = paramFloat;
  }
  
  public void setEnabled(boolean paramBoolean) {
    AssistStructure.ViewNode viewNode = this.mNode;
    viewNode.mFlags = viewNode.mFlags & 0xFFFFFFFE | paramBoolean ^ true;
  }
  
  public void setFocusable(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFFFFEF | bool;
  }
  
  public void setFocused(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFFFFDF | bool;
  }
  
  public void setHint(CharSequence paramCharSequence) {
    AssistStructure.ViewNodeText viewNodeText = getNodeText();
    if (paramCharSequence != null) {
      paramCharSequence = paramCharSequence.toString();
    } else {
      paramCharSequence = null;
    } 
    viewNodeText.mHint = (String)paramCharSequence;
  }
  
  public void setHintIdEntry(String paramString) {
    AssistStructure.ViewNode viewNode = this.mNode;
    Objects.requireNonNull(paramString);
    viewNode.mHintIdEntry = paramString;
  }
  
  public void setHtmlInfo(ViewStructure.HtmlInfo paramHtmlInfo) {
    this.mNode.mHtmlInfo = paramHtmlInfo;
  }
  
  public void setId(int paramInt, String paramString1, String paramString2, String paramString3) {
    this.mNode.mId = paramInt;
    this.mNode.mIdPackage = paramString1;
    this.mNode.mIdType = paramString2;
    this.mNode.mIdEntry = paramString3;
  }
  
  public void setImportantForAutofill(int paramInt) {
    this.mNode.mImportantForAutofill = paramInt;
  }
  
  public void setInputType(int paramInt) {
    this.mNode.mInputType = paramInt;
  }
  
  public void setLocaleList(LocaleList paramLocaleList) {
    this.mNode.mLocaleList = paramLocaleList;
  }
  
  public void setLongClickable(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFFF7FF | bool;
  }
  
  public void setMaxTextEms(int paramInt) {
    this.mNode.mMaxEms = paramInt;
  }
  
  public void setMaxTextLength(int paramInt) {
    this.mNode.mMaxLength = paramInt;
  }
  
  public void setMinTextEms(int paramInt) {
    this.mNode.mMinEms = paramInt;
  }
  
  public void setOpaque(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFF7FFF | bool;
  }
  
  public void setSelected(boolean paramBoolean) {
    boolean bool;
    AssistStructure.ViewNode viewNode = this.mNode;
    int i = viewNode.mFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    viewNode.mFlags = i & 0xFFFFFFBF | bool;
  }
  
  public void setText(CharSequence paramCharSequence) {
    AssistStructure.ViewNodeText viewNodeText = getNodeText();
    viewNodeText.mText = TextUtils.trimNoCopySpans(paramCharSequence);
    viewNodeText.mTextSelectionEnd = -1;
    viewNodeText.mTextSelectionStart = -1;
  }
  
  public void setText(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    AssistStructure.ViewNodeText viewNodeText = getNodeText();
    viewNodeText.mText = TextUtils.trimNoCopySpans(paramCharSequence);
    viewNodeText.mTextSelectionStart = paramInt1;
    viewNodeText.mTextSelectionEnd = paramInt2;
  }
  
  public void setTextIdEntry(String paramString) {
    AssistStructure.ViewNode viewNode = this.mNode;
    Objects.requireNonNull(paramString);
    viewNode.mTextIdEntry = paramString;
  }
  
  public void setTextLines(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    AssistStructure.ViewNodeText viewNodeText = getNodeText();
    viewNodeText.mLineCharOffsets = paramArrayOfint1;
    viewNodeText.mLineBaselines = paramArrayOfint2;
  }
  
  public void setTextStyle(float paramFloat, int paramInt1, int paramInt2, int paramInt3) {
    AssistStructure.ViewNodeText viewNodeText = getNodeText();
    viewNodeText.mTextColor = paramInt1;
    viewNodeText.mTextBackgroundColor = paramInt2;
    viewNodeText.mTextSize = paramFloat;
    viewNodeText.mTextStyle = paramInt3;
  }
  
  public void setTransformation(Matrix paramMatrix) {
    if (paramMatrix == null) {
      this.mNode.mMatrix = null;
    } else {
      this.mNode.mMatrix = new Matrix(paramMatrix);
    } 
  }
  
  public void setVisibility(int paramInt) {
    AssistStructure.ViewNode viewNode = this.mNode;
    viewNode.mFlags = viewNode.mFlags & 0xFFFFFFF3 | paramInt & 0xC;
  }
  
  public void setWebDomain(String paramString) {
    this.mNode.setWebDomain(paramString);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure$ViewNodeBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
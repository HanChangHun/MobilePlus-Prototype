package android.app.assist;

import android.os.Parcel;
import android.text.TextUtils;

final class ViewNodeText {
  String mHint;
  
  int[] mLineBaselines;
  
  int[] mLineCharOffsets;
  
  CharSequence mText;
  
  int mTextBackgroundColor = 1;
  
  int mTextColor = 1;
  
  int mTextSelectionEnd;
  
  int mTextSelectionStart;
  
  float mTextSize;
  
  int mTextStyle;
  
  ViewNodeText() {}
  
  ViewNodeText(Parcel paramParcel, boolean paramBoolean) {
    this.mText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mTextSize = paramParcel.readFloat();
    this.mTextStyle = paramParcel.readInt();
    this.mTextColor = paramParcel.readInt();
    if (!paramBoolean) {
      this.mTextBackgroundColor = paramParcel.readInt();
      this.mTextSelectionStart = paramParcel.readInt();
      this.mTextSelectionEnd = paramParcel.readInt();
      this.mLineCharOffsets = paramParcel.createIntArray();
      this.mLineBaselines = paramParcel.createIntArray();
      this.mHint = paramParcel.readString();
    } 
  }
  
  boolean isSimple() {
    int i = this.mTextBackgroundColor;
    boolean bool = true;
    if (i != 1 || this.mTextSelectionStart != 0 || this.mTextSelectionEnd != 0 || this.mLineCharOffsets != null || this.mLineBaselines != null || this.mHint != null)
      bool = false; 
    return bool;
  }
  
  void writeToParcel(Parcel paramParcel, boolean paramBoolean1, boolean paramBoolean2) {
    CharSequence charSequence;
    if (paramBoolean2) {
      charSequence = this.mText;
    } else {
      charSequence = "";
    } 
    TextUtils.writeToParcel(charSequence, paramParcel, 0);
    paramParcel.writeFloat(this.mTextSize);
    paramParcel.writeInt(this.mTextStyle);
    paramParcel.writeInt(this.mTextColor);
    if (!paramBoolean1) {
      paramParcel.writeInt(this.mTextBackgroundColor);
      paramParcel.writeInt(this.mTextSelectionStart);
      paramParcel.writeInt(this.mTextSelectionEnd);
      paramParcel.writeIntArray(this.mLineCharOffsets);
      paramParcel.writeIntArray(this.mLineBaselines);
      paramParcel.writeString(this.mHint);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure$ViewNodeText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
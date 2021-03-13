package android.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.android.internal.R;

public class ActionKeyInfo implements Parcelable {
  private final int mKeyCode;
  
  private final String mQueryActionMsg;
  
  private final String mSuggestActionMsg;
  
  private final String mSuggestActionMsgColumn;
  
  ActionKeyInfo(Context paramContext, AttributeSet paramAttributeSet) {
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SearchableActionKey);
    this.mKeyCode = typedArray.getInt(0, 0);
    this.mQueryActionMsg = typedArray.getString(1);
    this.mSuggestActionMsg = typedArray.getString(2);
    this.mSuggestActionMsgColumn = typedArray.getString(3);
    typedArray.recycle();
    if (this.mKeyCode != 0) {
      if (this.mQueryActionMsg != null || this.mSuggestActionMsg != null || this.mSuggestActionMsgColumn != null)
        return; 
      throw new IllegalArgumentException("No message information.");
    } 
    throw new IllegalArgumentException("No keycode.");
  }
  
  private ActionKeyInfo(Parcel paramParcel) {
    this.mKeyCode = paramParcel.readInt();
    this.mQueryActionMsg = paramParcel.readString();
    this.mSuggestActionMsg = paramParcel.readString();
    this.mSuggestActionMsgColumn = paramParcel.readString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getKeyCode() {
    return this.mKeyCode;
  }
  
  public String getQueryActionMsg() {
    return this.mQueryActionMsg;
  }
  
  public String getSuggestActionMsg() {
    return this.mSuggestActionMsg;
  }
  
  public String getSuggestActionMsgColumn() {
    return this.mSuggestActionMsgColumn;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mKeyCode);
    paramParcel.writeString(this.mQueryActionMsg);
    paramParcel.writeString(this.mSuggestActionMsg);
    paramParcel.writeString(this.mSuggestActionMsgColumn);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SearchableInfo$ActionKeyInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
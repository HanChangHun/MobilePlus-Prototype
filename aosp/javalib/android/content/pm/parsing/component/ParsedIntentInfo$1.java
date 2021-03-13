package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParsedIntentInfo> {
  public ParsedIntentInfo createFromParcel(Parcel paramParcel) {
    return new ParsedIntentInfo(paramParcel);
  }
  
  public ParsedIntentInfo[] newArray(int paramInt) {
    return new ParsedIntentInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedIntentInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
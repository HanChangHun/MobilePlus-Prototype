package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParsedActivity> {
  public ParsedActivity createFromParcel(Parcel paramParcel) {
    return new ParsedActivity(paramParcel);
  }
  
  public ParsedActivity[] newArray(int paramInt) {
    return new ParsedActivity[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedActivity$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
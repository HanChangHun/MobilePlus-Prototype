package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParsedMainComponent> {
  public ParsedMainComponent createFromParcel(Parcel paramParcel) {
    return new ParsedMainComponent(paramParcel);
  }
  
  public ParsedMainComponent[] newArray(int paramInt) {
    return new ParsedMainComponent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedMainComponent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
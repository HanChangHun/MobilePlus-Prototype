package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParsedPermission> {
  public ParsedPermission createFromParcel(Parcel paramParcel) {
    return new ParsedPermission(paramParcel);
  }
  
  public ParsedPermission[] newArray(int paramInt) {
    return new ParsedPermission[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedPermission$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
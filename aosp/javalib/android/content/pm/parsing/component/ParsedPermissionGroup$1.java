package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParsedPermissionGroup> {
  public ParsedPermissionGroup createFromParcel(Parcel paramParcel) {
    return new ParsedPermissionGroup(paramParcel);
  }
  
  public ParsedPermissionGroup[] newArray(int paramInt) {
    return new ParsedPermissionGroup[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedPermissionGroup$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
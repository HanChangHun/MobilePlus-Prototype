package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParsedService> {
  public ParsedService createFromParcel(Parcel paramParcel) {
    return new ParsedService(paramParcel);
  }
  
  public ParsedService[] newArray(int paramInt) {
    return new ParsedService[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedService$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParsedAttribution> {
  public ParsedAttribution createFromParcel(Parcel paramParcel) {
    return new ParsedAttribution(paramParcel);
  }
  
  public ParsedAttribution[] newArray(int paramInt) {
    return new ParsedAttribution[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedAttribution$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
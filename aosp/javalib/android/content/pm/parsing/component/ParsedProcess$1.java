package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParsedProcess> {
  public ParsedProcess createFromParcel(Parcel paramParcel) {
    return new ParsedProcess(paramParcel);
  }
  
  public ParsedProcess[] newArray(int paramInt) {
    return new ParsedProcess[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedProcess$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
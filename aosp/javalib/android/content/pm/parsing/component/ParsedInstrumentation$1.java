package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParsedInstrumentation> {
  public ParsedInstrumentation createFromParcel(Parcel paramParcel) {
    return new ParsedInstrumentation(paramParcel);
  }
  
  public ParsedInstrumentation[] newArray(int paramInt) {
    return new ParsedInstrumentation[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedInstrumentation$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
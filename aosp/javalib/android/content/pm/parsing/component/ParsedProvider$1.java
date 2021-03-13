package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParsedProvider> {
  public ParsedProvider createFromParcel(Parcel paramParcel) {
    return new ParsedProvider(paramParcel);
  }
  
  public ParsedProvider[] newArray(int paramInt) {
    return new ParsedProvider[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedProvider$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
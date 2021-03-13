package android.content.pm.parsing;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParsingPackageImpl> {
  public ParsingPackageImpl createFromParcel(Parcel paramParcel) {
    return new ParsingPackageImpl(paramParcel);
  }
  
  public ParsingPackageImpl[] newArray(int paramInt) {
    return new ParsingPackageImpl[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/ParsingPackageImpl$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
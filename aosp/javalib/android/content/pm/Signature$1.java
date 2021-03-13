package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Signature> {
  public Signature createFromParcel(Parcel paramParcel) {
    return new Signature(paramParcel, null);
  }
  
  public Signature[] newArray(int paramInt) {
    return new Signature[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/Signature$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
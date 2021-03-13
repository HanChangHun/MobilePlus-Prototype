package android.companion;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Association> {
  public Association createFromParcel(Parcel paramParcel) {
    return new Association(paramParcel);
  }
  
  public Association[] newArray(int paramInt) {
    return new Association[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/Association$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
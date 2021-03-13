package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<LocusId> {
  public LocusId createFromParcel(Parcel paramParcel) {
    return new LocusId(paramParcel.readString());
  }
  
  public LocusId[] newArray(int paramInt) {
    return new LocusId[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/LocusId$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
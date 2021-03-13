package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RestrictionEntry> {
  public RestrictionEntry createFromParcel(Parcel paramParcel) {
    return new RestrictionEntry(paramParcel);
  }
  
  public RestrictionEntry[] newArray(int paramInt) {
    return new RestrictionEntry[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/RestrictionEntry$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
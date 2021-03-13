package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Region> {
  public Region createFromParcel(Parcel paramParcel) {
    long l = Region.access$000(paramParcel);
    if (l != 0L)
      return new Region(l); 
    throw new RuntimeException();
  }
  
  public Region[] newArray(int paramInt) {
    return new Region[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Region$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
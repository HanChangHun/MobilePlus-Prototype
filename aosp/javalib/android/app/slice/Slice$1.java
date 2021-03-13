package android.app.slice;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Slice> {
  public Slice createFromParcel(Parcel paramParcel) {
    return new Slice(paramParcel);
  }
  
  public Slice[] newArray(int paramInt) {
    return new Slice[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/Slice$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
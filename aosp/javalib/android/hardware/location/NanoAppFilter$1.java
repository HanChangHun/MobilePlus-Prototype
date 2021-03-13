package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<NanoAppFilter> {
  public NanoAppFilter createFromParcel(Parcel paramParcel) {
    return new NanoAppFilter(paramParcel, null);
  }
  
  public NanoAppFilter[] newArray(int paramInt) {
    return new NanoAppFilter[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoAppFilter$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
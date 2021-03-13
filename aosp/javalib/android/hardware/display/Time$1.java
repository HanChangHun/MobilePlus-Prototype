package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Time> {
  public Time createFromParcel(Parcel paramParcel) {
    return new Time(paramParcel);
  }
  
  public Time[] newArray(int paramInt) {
    return new Time[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/Time$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
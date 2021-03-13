package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<NanoAppState> {
  public NanoAppState createFromParcel(Parcel paramParcel) {
    return new NanoAppState(paramParcel, null);
  }
  
  public NanoAppState[] newArray(int paramInt) {
    return new NanoAppState[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoAppState$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
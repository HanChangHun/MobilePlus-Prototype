package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<NanoAppBinary> {
  public NanoAppBinary createFromParcel(Parcel paramParcel) {
    return new NanoAppBinary(paramParcel, null);
  }
  
  public NanoAppBinary[] newArray(int paramInt) {
    return new NanoAppBinary[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoAppBinary$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
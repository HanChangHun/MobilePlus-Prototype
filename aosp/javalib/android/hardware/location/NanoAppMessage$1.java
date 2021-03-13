package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<NanoAppMessage> {
  public NanoAppMessage createFromParcel(Parcel paramParcel) {
    return new NanoAppMessage(paramParcel, null);
  }
  
  public NanoAppMessage[] newArray(int paramInt) {
    return new NanoAppMessage[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoAppMessage$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
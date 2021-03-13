package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<NanoAppInstanceInfo> {
  public NanoAppInstanceInfo createFromParcel(Parcel paramParcel) {
    return new NanoAppInstanceInfo(paramParcel, null);
  }
  
  public NanoAppInstanceInfo[] newArray(int paramInt) {
    return new NanoAppInstanceInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoAppInstanceInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
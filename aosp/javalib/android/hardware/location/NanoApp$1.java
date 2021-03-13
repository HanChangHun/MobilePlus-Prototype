package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<NanoApp> {
  public NanoApp createFromParcel(Parcel paramParcel) {
    return new NanoApp(paramParcel, null);
  }
  
  public NanoApp[] newArray(int paramInt) {
    return new NanoApp[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoApp$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<UidTraffic> {
  public UidTraffic createFromParcel(Parcel paramParcel) {
    return new UidTraffic(paramParcel);
  }
  
  public UidTraffic[] newArray(int paramInt) {
    return new UidTraffic[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/UidTraffic$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AdvertiseSettings> {
  public AdvertiseSettings createFromParcel(Parcel paramParcel) {
    return new AdvertiseSettings(paramParcel, null);
  }
  
  public AdvertiseSettings[] newArray(int paramInt) {
    return new AdvertiseSettings[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/AdvertiseSettings$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
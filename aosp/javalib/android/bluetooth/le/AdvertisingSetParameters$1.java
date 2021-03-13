package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AdvertisingSetParameters> {
  public AdvertisingSetParameters createFromParcel(Parcel paramParcel) {
    return new AdvertisingSetParameters(paramParcel, null);
  }
  
  public AdvertisingSetParameters[] newArray(int paramInt) {
    return new AdvertisingSetParameters[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/AdvertisingSetParameters$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.companion;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<WifiDeviceFilter> {
  public WifiDeviceFilter createFromParcel(Parcel paramParcel) {
    return new WifiDeviceFilter(paramParcel);
  }
  
  public WifiDeviceFilter[] newArray(int paramInt) {
    return new WifiDeviceFilter[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/WifiDeviceFilter$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
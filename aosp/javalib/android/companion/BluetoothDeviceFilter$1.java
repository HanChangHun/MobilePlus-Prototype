package android.companion;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothDeviceFilter> {
  public BluetoothDeviceFilter createFromParcel(Parcel paramParcel) {
    return new BluetoothDeviceFilter(paramParcel, null);
  }
  
  public BluetoothDeviceFilter[] newArray(int paramInt) {
    return new BluetoothDeviceFilter[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/BluetoothDeviceFilter$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
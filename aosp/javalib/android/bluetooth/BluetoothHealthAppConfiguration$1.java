package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothHealthAppConfiguration> {
  public BluetoothHealthAppConfiguration createFromParcel(Parcel paramParcel) {
    return new BluetoothHealthAppConfiguration();
  }
  
  public BluetoothHealthAppConfiguration[] newArray(int paramInt) {
    return new BluetoothHealthAppConfiguration[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHealthAppConfiguration$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothAvrcpPlayerSettings> {
  public BluetoothAvrcpPlayerSettings createFromParcel(Parcel paramParcel) {
    return new BluetoothAvrcpPlayerSettings(paramParcel, null);
  }
  
  public BluetoothAvrcpPlayerSettings[] newArray(int paramInt) {
    return new BluetoothAvrcpPlayerSettings[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAvrcpPlayerSettings$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
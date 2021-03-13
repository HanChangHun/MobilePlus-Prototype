package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothHidDeviceAppQosSettings> {
  public BluetoothHidDeviceAppQosSettings createFromParcel(Parcel paramParcel) {
    return new BluetoothHidDeviceAppQosSettings(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt());
  }
  
  public BluetoothHidDeviceAppQosSettings[] newArray(int paramInt) {
    return new BluetoothHidDeviceAppQosSettings[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHidDeviceAppQosSettings$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
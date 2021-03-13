package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothHidDeviceAppSdpSettings> {
  public BluetoothHidDeviceAppSdpSettings createFromParcel(Parcel paramParcel) {
    return new BluetoothHidDeviceAppSdpSettings(paramParcel.readString(), paramParcel.readString(), paramParcel.readString(), paramParcel.readByte(), paramParcel.createByteArray());
  }
  
  public BluetoothHidDeviceAppSdpSettings[] newArray(int paramInt) {
    return new BluetoothHidDeviceAppSdpSettings[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHidDeviceAppSdpSettings$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothCodecStatus> {
  public BluetoothCodecStatus createFromParcel(Parcel paramParcel) {
    return new BluetoothCodecStatus((BluetoothCodecConfig)paramParcel.readTypedObject(BluetoothCodecConfig.CREATOR), (BluetoothCodecConfig[])paramParcel.createTypedArray(BluetoothCodecConfig.CREATOR), (BluetoothCodecConfig[])paramParcel.createTypedArray(BluetoothCodecConfig.CREATOR));
  }
  
  public BluetoothCodecStatus[] newArray(int paramInt) {
    return new BluetoothCodecStatus[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothCodecStatus$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
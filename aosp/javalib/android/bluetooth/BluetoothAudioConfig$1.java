package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothAudioConfig> {
  public BluetoothAudioConfig createFromParcel(Parcel paramParcel) {
    return new BluetoothAudioConfig(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt());
  }
  
  public BluetoothAudioConfig[] newArray(int paramInt) {
    return new BluetoothAudioConfig[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAudioConfig$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
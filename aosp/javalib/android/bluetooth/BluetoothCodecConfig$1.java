package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothCodecConfig> {
  public BluetoothCodecConfig createFromParcel(Parcel paramParcel) {
    return new BluetoothCodecConfig(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readLong(), paramParcel.readLong(), paramParcel.readLong(), paramParcel.readLong());
  }
  
  public BluetoothCodecConfig[] newArray(int paramInt) {
    return new BluetoothCodecConfig[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothCodecConfig$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
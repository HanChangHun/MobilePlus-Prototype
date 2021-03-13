package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothDevice> {
  public BluetoothDevice createFromParcel(Parcel paramParcel) {
    return new BluetoothDevice(paramParcel.readString());
  }
  
  public BluetoothDevice[] newArray(int paramInt) {
    return new BluetoothDevice[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothDevice$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothClass> {
  public BluetoothClass createFromParcel(Parcel paramParcel) {
    return new BluetoothClass(paramParcel.readInt());
  }
  
  public BluetoothClass[] newArray(int paramInt) {
    return new BluetoothClass[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothClass$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
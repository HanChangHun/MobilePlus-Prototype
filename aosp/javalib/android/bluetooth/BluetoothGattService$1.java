package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothGattService> {
  public BluetoothGattService createFromParcel(Parcel paramParcel) {
    return new BluetoothGattService(paramParcel, null);
  }
  
  public BluetoothGattService[] newArray(int paramInt) {
    return new BluetoothGattService[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothGattService$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothGattCharacteristic> {
  public BluetoothGattCharacteristic createFromParcel(Parcel paramParcel) {
    return new BluetoothGattCharacteristic(paramParcel, null);
  }
  
  public BluetoothGattCharacteristic[] newArray(int paramInt) {
    return new BluetoothGattCharacteristic[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothGattCharacteristic$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
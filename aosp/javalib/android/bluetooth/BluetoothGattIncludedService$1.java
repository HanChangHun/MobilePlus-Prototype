package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothGattIncludedService> {
  public BluetoothGattIncludedService createFromParcel(Parcel paramParcel) {
    return new BluetoothGattIncludedService(paramParcel, null);
  }
  
  public BluetoothGattIncludedService[] newArray(int paramInt) {
    return new BluetoothGattIncludedService[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothGattIncludedService$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
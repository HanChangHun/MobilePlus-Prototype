package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothGattDescriptor> {
  public BluetoothGattDescriptor createFromParcel(Parcel paramParcel) {
    return new BluetoothGattDescriptor(paramParcel, null);
  }
  
  public BluetoothGattDescriptor[] newArray(int paramInt) {
    return new BluetoothGattDescriptor[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothGattDescriptor$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
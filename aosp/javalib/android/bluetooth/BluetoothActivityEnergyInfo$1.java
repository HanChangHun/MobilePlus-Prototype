package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothActivityEnergyInfo> {
  public BluetoothActivityEnergyInfo createFromParcel(Parcel paramParcel) {
    return new BluetoothActivityEnergyInfo(paramParcel);
  }
  
  public BluetoothActivityEnergyInfo[] newArray(int paramInt) {
    return new BluetoothActivityEnergyInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothActivityEnergyInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
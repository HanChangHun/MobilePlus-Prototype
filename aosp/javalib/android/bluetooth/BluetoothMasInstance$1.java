package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BluetoothMasInstance> {
  public BluetoothMasInstance createFromParcel(Parcel paramParcel) {
    return new BluetoothMasInstance(paramParcel.readInt(), paramParcel.readString(), paramParcel.readInt(), paramParcel.readInt());
  }
  
  public BluetoothMasInstance[] newArray(int paramInt) {
    return new BluetoothMasInstance[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothMasInstance$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
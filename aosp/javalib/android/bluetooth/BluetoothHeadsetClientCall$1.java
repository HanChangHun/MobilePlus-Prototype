package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.UUID;

class null implements Parcelable.Creator<BluetoothHeadsetClientCall> {
  public BluetoothHeadsetClientCall createFromParcel(Parcel paramParcel) {
    boolean bool1;
    boolean bool2;
    boolean bool3;
    BluetoothDevice bluetoothDevice = (BluetoothDevice)paramParcel.readParcelable(null);
    int i = paramParcel.readInt();
    UUID uUID = UUID.fromString(paramParcel.readString());
    int j = paramParcel.readInt();
    String str = paramParcel.readString();
    if (paramParcel.readInt() == 1) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (paramParcel.readInt() == 1) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    return new BluetoothHeadsetClientCall(bluetoothDevice, i, uUID, j, str, bool1, bool2, bool3);
  }
  
  public BluetoothHeadsetClientCall[] newArray(int paramInt) {
    return new BluetoothHeadsetClientCall[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHeadsetClientCall$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.companion;

import android.bluetooth.le.ScanFilter;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.ByteOrder;

class null implements Parcelable.Creator<BluetoothLeDeviceFilter> {
  public BluetoothLeDeviceFilter createFromParcel(Parcel paramParcel) {
    BluetoothLeDeviceFilter.Builder builder = (new BluetoothLeDeviceFilter.Builder()).setNamePattern(BluetoothDeviceFilterUtils.patternFromString(paramParcel.readString())).setScanFilter((ScanFilter)paramParcel.readParcelable(null));
    byte[] arrayOfByte1 = paramParcel.createByteArray();
    byte[] arrayOfByte2 = paramParcel.createByteArray();
    if (arrayOfByte1 != null)
      builder.setRawDataFilter(arrayOfByte1, arrayOfByte2); 
    String str2 = paramParcel.readString();
    String str1 = paramParcel.readString();
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    int k = paramParcel.readInt();
    int m = paramParcel.readInt();
    boolean bool = paramParcel.readBoolean();
    if (str2 != null)
      if (i >= 0) {
        ByteOrder byteOrder;
        if (bool) {
          byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
          byteOrder = ByteOrder.BIG_ENDIAN;
        } 
        builder.setRenameFromBytes(str2, str1, i, j, byteOrder);
      } else {
        builder.setRenameFromName(str2, str1, k, m);
      }  
    return builder.build();
  }
  
  public BluetoothLeDeviceFilter[] newArray(int paramInt) {
    return new BluetoothLeDeviceFilter[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/BluetoothLeDeviceFilter$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
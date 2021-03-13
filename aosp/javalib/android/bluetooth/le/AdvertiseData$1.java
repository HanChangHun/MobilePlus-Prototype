package android.bluetooth.le;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.Iterator;

class null implements Parcelable.Creator<AdvertiseData> {
  public AdvertiseData createFromParcel(Parcel paramParcel) {
    AdvertiseData.Builder builder = new AdvertiseData.Builder();
    Iterator<ParcelUuid> iterator = paramParcel.createTypedArrayList(ParcelUuid.CREATOR).iterator();
    while (iterator.hasNext())
      builder.addServiceUuid(iterator.next()); 
    int i = paramParcel.readInt();
    byte b;
    for (b = 0; b < i; b++)
      builder.addManufacturerData(paramParcel.readInt(), paramParcel.createByteArray()); 
    i = paramParcel.readInt();
    for (b = 0; b < i; b++)
      builder.addServiceData((ParcelUuid)paramParcel.readTypedObject(ParcelUuid.CREATOR), paramParcel.createByteArray()); 
    b = paramParcel.readByte();
    boolean bool1 = false;
    if (b == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    builder.setIncludeTxPowerLevel(bool2);
    boolean bool2 = bool1;
    if (paramParcel.readByte() == 1)
      bool2 = true; 
    builder.setIncludeDeviceName(bool2);
    return builder.build();
  }
  
  public AdvertiseData[] newArray(int paramInt) {
    return new AdvertiseData[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/AdvertiseData$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
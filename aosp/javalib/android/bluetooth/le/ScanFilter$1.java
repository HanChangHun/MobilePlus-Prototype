package android.bluetooth.le;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;

class null implements Parcelable.Creator<ScanFilter> {
  public ScanFilter createFromParcel(Parcel paramParcel) {
    ScanFilter.Builder builder = new ScanFilter.Builder();
    if (paramParcel.readInt() == 1)
      builder.setDeviceName(paramParcel.readString()); 
    if (paramParcel.readInt() == 1)
      builder.setDeviceAddress(paramParcel.readString()); 
    if (paramParcel.readInt() == 1) {
      ParcelUuid parcelUuid = (ParcelUuid)paramParcel.readParcelable(ParcelUuid.class.getClassLoader());
      builder.setServiceUuid(parcelUuid);
      if (paramParcel.readInt() == 1)
        builder.setServiceUuid(parcelUuid, (ParcelUuid)paramParcel.readParcelable(ParcelUuid.class.getClassLoader())); 
    } 
    if (paramParcel.readInt() == 1) {
      ParcelUuid parcelUuid = (ParcelUuid)paramParcel.readParcelable(ParcelUuid.class.getClassLoader());
      builder.setServiceSolicitationUuid(parcelUuid);
      if (paramParcel.readInt() == 1)
        builder.setServiceSolicitationUuid(parcelUuid, (ParcelUuid)paramParcel.readParcelable(ParcelUuid.class.getClassLoader())); 
    } 
    if (paramParcel.readInt() == 1) {
      ParcelUuid parcelUuid = (ParcelUuid)paramParcel.readParcelable(ParcelUuid.class.getClassLoader());
      if (paramParcel.readInt() == 1) {
        byte[] arrayOfByte = new byte[paramParcel.readInt()];
        paramParcel.readByteArray(arrayOfByte);
        if (paramParcel.readInt() == 0) {
          builder.setServiceData(parcelUuid, arrayOfByte);
        } else {
          byte[] arrayOfByte1 = new byte[paramParcel.readInt()];
          paramParcel.readByteArray(arrayOfByte1);
          builder.setServiceData(parcelUuid, arrayOfByte, arrayOfByte1);
        } 
      } 
    } 
    int i = paramParcel.readInt();
    if (paramParcel.readInt() == 1) {
      byte[] arrayOfByte = new byte[paramParcel.readInt()];
      paramParcel.readByteArray(arrayOfByte);
      if (paramParcel.readInt() == 0) {
        builder.setManufacturerData(i, arrayOfByte);
      } else {
        byte[] arrayOfByte1 = new byte[paramParcel.readInt()];
        paramParcel.readByteArray(arrayOfByte1);
        builder.setManufacturerData(i, arrayOfByte, arrayOfByte1);
      } 
    } 
    return builder.build();
  }
  
  public ScanFilter[] newArray(int paramInt) {
    return new ScanFilter[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/ScanFilter$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
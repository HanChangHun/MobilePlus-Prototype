package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<DeviceProductInfo.ManufactureDate> {
  public DeviceProductInfo.ManufactureDate createFromParcel(Parcel paramParcel) {
    return new DeviceProductInfo.ManufactureDate(paramParcel);
  }
  
  public DeviceProductInfo.ManufactureDate[] newArray(int paramInt) {
    return new DeviceProductInfo.ManufactureDate[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DeviceProductInfo$ManufactureDate$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
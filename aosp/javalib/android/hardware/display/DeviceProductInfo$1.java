package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<DeviceProductInfo> {
  public DeviceProductInfo createFromParcel(Parcel paramParcel) {
    return new DeviceProductInfo(paramParcel, null);
  }
  
  public DeviceProductInfo[] newArray(int paramInt) {
    return new DeviceProductInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DeviceProductInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<DeviceAdminInfo> {
  public DeviceAdminInfo createFromParcel(Parcel paramParcel) {
    return new DeviceAdminInfo(paramParcel);
  }
  
  public DeviceAdminInfo[] newArray(int paramInt) {
    return new DeviceAdminInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DeviceAdminInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
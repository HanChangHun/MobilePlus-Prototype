package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SystemUpdateInfo> {
  public SystemUpdateInfo createFromParcel(Parcel paramParcel) {
    return new SystemUpdateInfo(paramParcel, null);
  }
  
  public SystemUpdateInfo[] newArray(int paramInt) {
    return new SystemUpdateInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/SystemUpdateInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
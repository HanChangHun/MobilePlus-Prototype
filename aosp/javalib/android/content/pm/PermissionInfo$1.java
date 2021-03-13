package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PermissionInfo> {
  public PermissionInfo createFromParcel(Parcel paramParcel) {
    return new PermissionInfo(paramParcel, null);
  }
  
  public PermissionInfo[] newArray(int paramInt) {
    return new PermissionInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PermissionInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PermissionGroupInfo> {
  public PermissionGroupInfo createFromParcel(Parcel paramParcel) {
    return new PermissionGroupInfo(paramParcel, null);
  }
  
  public PermissionGroupInfo[] newArray(int paramInt) {
    return new PermissionGroupInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PermissionGroupInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
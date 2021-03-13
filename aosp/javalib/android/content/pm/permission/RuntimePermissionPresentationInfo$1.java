package android.content.pm.permission;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RuntimePermissionPresentationInfo> {
  public RuntimePermissionPresentationInfo createFromParcel(Parcel paramParcel) {
    return new RuntimePermissionPresentationInfo(paramParcel, null);
  }
  
  public RuntimePermissionPresentationInfo[] newArray(int paramInt) {
    return new RuntimePermissionPresentationInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/permission/RuntimePermissionPresentationInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
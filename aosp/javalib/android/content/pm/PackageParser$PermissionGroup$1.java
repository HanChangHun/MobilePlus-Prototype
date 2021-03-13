package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageParser.PermissionGroup> {
  public PackageParser.PermissionGroup createFromParcel(Parcel paramParcel) {
    return new PackageParser.PermissionGroup(paramParcel, null);
  }
  
  public PackageParser.PermissionGroup[] newArray(int paramInt) {
    return new PackageParser.PermissionGroup[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$PermissionGroup$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
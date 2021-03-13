package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageParser.Permission> {
  public PackageParser.Permission createFromParcel(Parcel paramParcel) {
    return new PackageParser.Permission(paramParcel, null);
  }
  
  public PackageParser.Permission[] newArray(int paramInt) {
    return new PackageParser.Permission[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Permission$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
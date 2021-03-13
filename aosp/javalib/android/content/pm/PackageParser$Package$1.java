package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageParser.Package> {
  public PackageParser.Package createFromParcel(Parcel paramParcel) {
    return new PackageParser.Package(paramParcel);
  }
  
  public PackageParser.Package[] newArray(int paramInt) {
    return new PackageParser.Package[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Package$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
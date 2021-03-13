package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageParser.Service> {
  public PackageParser.Service createFromParcel(Parcel paramParcel) {
    return new PackageParser.Service(paramParcel, null);
  }
  
  public PackageParser.Service[] newArray(int paramInt) {
    return new PackageParser.Service[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Service$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
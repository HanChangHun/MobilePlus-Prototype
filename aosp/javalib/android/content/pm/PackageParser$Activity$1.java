package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageParser.Activity> {
  public PackageParser.Activity createFromParcel(Parcel paramParcel) {
    return new PackageParser.Activity(paramParcel, null);
  }
  
  public PackageParser.Activity[] newArray(int paramInt) {
    return new PackageParser.Activity[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Activity$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
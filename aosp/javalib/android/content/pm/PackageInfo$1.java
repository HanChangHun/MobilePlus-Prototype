package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageInfo> {
  public PackageInfo createFromParcel(Parcel paramParcel) {
    return new PackageInfo(paramParcel, null);
  }
  
  public PackageInfo[] newArray(int paramInt) {
    return new PackageInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
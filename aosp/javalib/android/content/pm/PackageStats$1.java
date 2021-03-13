package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageStats> {
  public PackageStats createFromParcel(Parcel paramParcel) {
    return new PackageStats(paramParcel);
  }
  
  public PackageStats[] newArray(int paramInt) {
    return new PackageStats[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageStats$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
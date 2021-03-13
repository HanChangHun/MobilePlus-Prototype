package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageInfoLite> {
  public PackageInfoLite createFromParcel(Parcel paramParcel) {
    return new PackageInfoLite(paramParcel, null);
  }
  
  public PackageInfoLite[] newArray(int paramInt) {
    return new PackageInfoLite[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInfoLite$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
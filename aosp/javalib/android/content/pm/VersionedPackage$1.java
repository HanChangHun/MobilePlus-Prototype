package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<VersionedPackage> {
  public VersionedPackage createFromParcel(Parcel paramParcel) {
    return new VersionedPackage(paramParcel, null);
  }
  
  public VersionedPackage[] newArray(int paramInt) {
    return new VersionedPackage[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/VersionedPackage$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
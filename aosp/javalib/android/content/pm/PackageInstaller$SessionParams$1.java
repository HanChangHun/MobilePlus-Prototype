package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageInstaller.SessionParams> {
  public PackageInstaller.SessionParams createFromParcel(Parcel paramParcel) {
    return new PackageInstaller.SessionParams(paramParcel);
  }
  
  public PackageInstaller.SessionParams[] newArray(int paramInt) {
    return new PackageInstaller.SessionParams[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInstaller$SessionParams$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageInstaller.SessionInfo> {
  public PackageInstaller.SessionInfo createFromParcel(Parcel paramParcel) {
    return new PackageInstaller.SessionInfo(paramParcel);
  }
  
  public PackageInstaller.SessionInfo[] newArray(int paramInt) {
    return new PackageInstaller.SessionInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInstaller$SessionInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
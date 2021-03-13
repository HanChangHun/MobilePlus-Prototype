package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<InstallSourceInfo> {
  public InstallSourceInfo createFromParcel(Parcel paramParcel) {
    return new InstallSourceInfo(paramParcel, null);
  }
  
  public InstallSourceInfo[] newArray(int paramInt) {
    return new InstallSourceInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstallSourceInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
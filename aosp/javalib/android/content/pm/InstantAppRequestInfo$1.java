package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<InstantAppRequestInfo> {
  public InstantAppRequestInfo createFromParcel(Parcel paramParcel) {
    return new InstantAppRequestInfo(paramParcel);
  }
  
  public InstantAppRequestInfo[] newArray(int paramInt) {
    return new InstantAppRequestInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstantAppRequestInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
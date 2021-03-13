package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<InstantAppInfo> {
  public InstantAppInfo createFromParcel(Parcel paramParcel) {
    return new InstantAppInfo(paramParcel, null);
  }
  
  public InstantAppInfo[] newArray(int paramInt) {
    return new InstantAppInfo[0];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstantAppInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ProfilerInfo> {
  public ProfilerInfo createFromParcel(Parcel paramParcel) {
    return new ProfilerInfo(paramParcel, null);
  }
  
  public ProfilerInfo[] newArray(int paramInt) {
    return new ProfilerInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ProfilerInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
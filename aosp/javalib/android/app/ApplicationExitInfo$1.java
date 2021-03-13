package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ApplicationExitInfo> {
  public ApplicationExitInfo createFromParcel(Parcel paramParcel) {
    return new ApplicationExitInfo(paramParcel, null);
  }
  
  public ApplicationExitInfo[] newArray(int paramInt) {
    return new ApplicationExitInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationExitInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
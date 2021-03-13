package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppStandbyInfo> {
  public AppStandbyInfo createFromParcel(Parcel paramParcel) {
    return new AppStandbyInfo(paramParcel, null);
  }
  
  public AppStandbyInfo[] newArray(int paramInt) {
    return new AppStandbyInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/AppStandbyInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<UserInfo> {
  public UserInfo createFromParcel(Parcel paramParcel) {
    return new UserInfo(paramParcel, null);
  }
  
  public UserInfo[] newArray(int paramInt) {
    return new UserInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/UserInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
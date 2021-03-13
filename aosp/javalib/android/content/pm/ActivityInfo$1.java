package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityInfo> {
  public ActivityInfo createFromParcel(Parcel paramParcel) {
    return new ActivityInfo(paramParcel, null);
  }
  
  public ActivityInfo[] newArray(int paramInt) {
    return new ActivityInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ActivityInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityManager.RecentTaskInfo> {
  public ActivityManager.RecentTaskInfo createFromParcel(Parcel paramParcel) {
    return new ActivityManager.RecentTaskInfo(paramParcel, null);
  }
  
  public ActivityManager.RecentTaskInfo[] newArray(int paramInt) {
    return new ActivityManager.RecentTaskInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$RecentTaskInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
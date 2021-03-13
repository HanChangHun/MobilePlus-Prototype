package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityManager.RunningTaskInfo> {
  public ActivityManager.RunningTaskInfo createFromParcel(Parcel paramParcel) {
    return new ActivityManager.RunningTaskInfo(paramParcel, null);
  }
  
  public ActivityManager.RunningTaskInfo[] newArray(int paramInt) {
    return new ActivityManager.RunningTaskInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$RunningTaskInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
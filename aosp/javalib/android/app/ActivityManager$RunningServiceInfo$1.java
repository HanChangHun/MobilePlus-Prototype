package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityManager.RunningServiceInfo> {
  public ActivityManager.RunningServiceInfo createFromParcel(Parcel paramParcel) {
    return new ActivityManager.RunningServiceInfo(paramParcel, null);
  }
  
  public ActivityManager.RunningServiceInfo[] newArray(int paramInt) {
    return new ActivityManager.RunningServiceInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$RunningServiceInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityManager.RunningAppProcessInfo> {
  public ActivityManager.RunningAppProcessInfo createFromParcel(Parcel paramParcel) {
    return new ActivityManager.RunningAppProcessInfo(paramParcel, null);
  }
  
  public ActivityManager.RunningAppProcessInfo[] newArray(int paramInt) {
    return new ActivityManager.RunningAppProcessInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$RunningAppProcessInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
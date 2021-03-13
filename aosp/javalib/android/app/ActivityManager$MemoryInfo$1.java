package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityManager.MemoryInfo> {
  public ActivityManager.MemoryInfo createFromParcel(Parcel paramParcel) {
    return new ActivityManager.MemoryInfo(paramParcel, null);
  }
  
  public ActivityManager.MemoryInfo[] newArray(int paramInt) {
    return new ActivityManager.MemoryInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$MemoryInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
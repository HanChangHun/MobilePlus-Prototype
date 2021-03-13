package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityManager.StackInfo> {
  public ActivityManager.StackInfo createFromParcel(Parcel paramParcel) {
    return new ActivityManager.StackInfo(paramParcel, null);
  }
  
  public ActivityManager.StackInfo[] newArray(int paramInt) {
    return new ActivityManager.StackInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$StackInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
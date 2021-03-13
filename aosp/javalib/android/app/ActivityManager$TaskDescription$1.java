package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityManager.TaskDescription> {
  public ActivityManager.TaskDescription createFromParcel(Parcel paramParcel) {
    return new ActivityManager.TaskDescription(paramParcel, null);
  }
  
  public ActivityManager.TaskDescription[] newArray(int paramInt) {
    return new ActivityManager.TaskDescription[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$TaskDescription$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
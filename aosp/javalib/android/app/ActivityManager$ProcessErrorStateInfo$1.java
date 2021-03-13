package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityManager.ProcessErrorStateInfo> {
  public ActivityManager.ProcessErrorStateInfo createFromParcel(Parcel paramParcel) {
    return new ActivityManager.ProcessErrorStateInfo(paramParcel, null);
  }
  
  public ActivityManager.ProcessErrorStateInfo[] newArray(int paramInt) {
    return new ActivityManager.ProcessErrorStateInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$ProcessErrorStateInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
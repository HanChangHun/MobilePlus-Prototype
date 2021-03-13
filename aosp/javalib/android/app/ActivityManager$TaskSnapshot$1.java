package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityManager.TaskSnapshot> {
  public ActivityManager.TaskSnapshot createFromParcel(Parcel paramParcel) {
    return new ActivityManager.TaskSnapshot(paramParcel, null);
  }
  
  public ActivityManager.TaskSnapshot[] newArray(int paramInt) {
    return new ActivityManager.TaskSnapshot[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$TaskSnapshot$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
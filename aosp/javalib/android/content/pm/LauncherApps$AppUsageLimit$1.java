package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<LauncherApps.AppUsageLimit> {
  public LauncherApps.AppUsageLimit createFromParcel(Parcel paramParcel) {
    return new LauncherApps.AppUsageLimit(paramParcel, null);
  }
  
  public LauncherApps.AppUsageLimit[] newArray(int paramInt) {
    return new LauncherApps.AppUsageLimit[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps$AppUsageLimit$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
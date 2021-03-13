package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<LauncherApps.PinItemRequest> {
  public LauncherApps.PinItemRequest createFromParcel(Parcel paramParcel) {
    return new LauncherApps.PinItemRequest(paramParcel, null);
  }
  
  public LauncherApps.PinItemRequest[] newArray(int paramInt) {
    return new LauncherApps.PinItemRequest[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps$PinItemRequest$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
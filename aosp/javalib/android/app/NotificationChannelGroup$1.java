package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<NotificationChannelGroup> {
  public NotificationChannelGroup createFromParcel(Parcel paramParcel) {
    return new NotificationChannelGroup(paramParcel);
  }
  
  public NotificationChannelGroup[] newArray(int paramInt) {
    return new NotificationChannelGroup[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NotificationChannelGroup$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<NotificationChannel> {
  public NotificationChannel createFromParcel(Parcel paramParcel) {
    return new NotificationChannel(paramParcel);
  }
  
  public NotificationChannel[] newArray(int paramInt) {
    return new NotificationChannel[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NotificationChannel$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<NotificationHistory> {
  public NotificationHistory createFromParcel(Parcel paramParcel) {
    return new NotificationHistory(paramParcel, null);
  }
  
  public NotificationHistory[] newArray(int paramInt) {
    return new NotificationHistory[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NotificationHistory$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
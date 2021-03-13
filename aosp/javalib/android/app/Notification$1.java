package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Notification> {
  public Notification createFromParcel(Parcel paramParcel) {
    return new Notification(paramParcel);
  }
  
  public Notification[] newArray(int paramInt) {
    return new Notification[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
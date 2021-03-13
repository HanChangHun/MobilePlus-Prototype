package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Notification.Action> {
  public Notification.Action createFromParcel(Parcel paramParcel) {
    return new Notification.Action(paramParcel, null);
  }
  
  public Notification.Action[] newArray(int paramInt) {
    return new Notification.Action[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$Action$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<NotificationManager.Policy> {
  public NotificationManager.Policy createFromParcel(Parcel paramParcel) {
    return new NotificationManager.Policy(paramParcel);
  }
  
  public NotificationManager.Policy[] newArray(int paramInt) {
    return new NotificationManager.Policy[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NotificationManager$Policy$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Notification.BubbleMetadata> {
  public Notification.BubbleMetadata createFromParcel(Parcel paramParcel) {
    return new Notification.BubbleMetadata(paramParcel, null);
  }
  
  public Notification.BubbleMetadata[] newArray(int paramInt) {
    return new Notification.BubbleMetadata[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$BubbleMetadata$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
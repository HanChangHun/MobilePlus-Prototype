package android.appwidget;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PendingHostUpdate> {
  public PendingHostUpdate createFromParcel(Parcel paramParcel) {
    return new PendingHostUpdate(paramParcel, null);
  }
  
  public PendingHostUpdate[] newArray(int paramInt) {
    return new PendingHostUpdate[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/appwidget/PendingHostUpdate$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
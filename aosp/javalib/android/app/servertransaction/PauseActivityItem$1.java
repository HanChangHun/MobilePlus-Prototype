package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PauseActivityItem> {
  public PauseActivityItem createFromParcel(Parcel paramParcel) {
    return new PauseActivityItem(paramParcel, null);
  }
  
  public PauseActivityItem[] newArray(int paramInt) {
    return new PauseActivityItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/PauseActivityItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
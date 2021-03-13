package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<StopActivityItem> {
  public StopActivityItem createFromParcel(Parcel paramParcel) {
    return new StopActivityItem(paramParcel, null);
  }
  
  public StopActivityItem[] newArray(int paramInt) {
    return new StopActivityItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/StopActivityItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
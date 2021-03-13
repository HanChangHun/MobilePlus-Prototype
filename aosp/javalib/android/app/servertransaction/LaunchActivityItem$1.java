package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<LaunchActivityItem> {
  public LaunchActivityItem createFromParcel(Parcel paramParcel) {
    return new LaunchActivityItem(paramParcel, null);
  }
  
  public LaunchActivityItem[] newArray(int paramInt) {
    return new LaunchActivityItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/LaunchActivityItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
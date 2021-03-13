package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<StartActivityItem> {
  public StartActivityItem createFromParcel(Parcel paramParcel) {
    return new StartActivityItem(paramParcel, null);
  }
  
  public StartActivityItem[] newArray(int paramInt) {
    return new StartActivityItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/StartActivityItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
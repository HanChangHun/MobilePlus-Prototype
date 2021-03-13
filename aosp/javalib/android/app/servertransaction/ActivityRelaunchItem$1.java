package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityRelaunchItem> {
  public ActivityRelaunchItem createFromParcel(Parcel paramParcel) {
    return new ActivityRelaunchItem(paramParcel, null);
  }
  
  public ActivityRelaunchItem[] newArray(int paramInt) {
    return new ActivityRelaunchItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ActivityRelaunchItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityConfigurationChangeItem> {
  public ActivityConfigurationChangeItem createFromParcel(Parcel paramParcel) {
    return new ActivityConfigurationChangeItem(paramParcel, null);
  }
  
  public ActivityConfigurationChangeItem[] newArray(int paramInt) {
    return new ActivityConfigurationChangeItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ActivityConfigurationChangeItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
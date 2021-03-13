package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityResultItem> {
  public ActivityResultItem createFromParcel(Parcel paramParcel) {
    return new ActivityResultItem(paramParcel, null);
  }
  
  public ActivityResultItem[] newArray(int paramInt) {
    return new ActivityResultItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ActivityResultItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
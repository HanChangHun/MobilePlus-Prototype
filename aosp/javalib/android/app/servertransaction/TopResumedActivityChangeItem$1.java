package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<TopResumedActivityChangeItem> {
  public TopResumedActivityChangeItem createFromParcel(Parcel paramParcel) {
    return new TopResumedActivityChangeItem(paramParcel, null);
  }
  
  public TopResumedActivityChangeItem[] newArray(int paramInt) {
    return new TopResumedActivityChangeItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/TopResumedActivityChangeItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<MoveToDisplayItem> {
  public MoveToDisplayItem createFromParcel(Parcel paramParcel) {
    return new MoveToDisplayItem(paramParcel, null);
  }
  
  public MoveToDisplayItem[] newArray(int paramInt) {
    return new MoveToDisplayItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/MoveToDisplayItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
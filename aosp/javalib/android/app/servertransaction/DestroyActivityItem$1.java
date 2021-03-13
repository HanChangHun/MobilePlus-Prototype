package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<DestroyActivityItem> {
  public DestroyActivityItem createFromParcel(Parcel paramParcel) {
    return new DestroyActivityItem(paramParcel, null);
  }
  
  public DestroyActivityItem[] newArray(int paramInt) {
    return new DestroyActivityItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/DestroyActivityItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
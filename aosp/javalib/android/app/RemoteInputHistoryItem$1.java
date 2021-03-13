package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RemoteInputHistoryItem> {
  public RemoteInputHistoryItem createFromParcel(Parcel paramParcel) {
    return new RemoteInputHistoryItem(paramParcel);
  }
  
  public RemoteInputHistoryItem[] newArray(int paramInt) {
    return new RemoteInputHistoryItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RemoteInputHistoryItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
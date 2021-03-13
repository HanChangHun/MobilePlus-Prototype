package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SyncResult> {
  public SyncResult createFromParcel(Parcel paramParcel) {
    return new SyncResult(paramParcel, null);
  }
  
  public SyncResult[] newArray(int paramInt) {
    return new SyncResult[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncResult$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
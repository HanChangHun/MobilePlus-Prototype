package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SyncStats> {
  public SyncStats createFromParcel(Parcel paramParcel) {
    return new SyncStats(paramParcel);
  }
  
  public SyncStats[] newArray(int paramInt) {
    return new SyncStats[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncStats$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
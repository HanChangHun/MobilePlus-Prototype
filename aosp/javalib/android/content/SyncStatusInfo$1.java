package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SyncStatusInfo> {
  public SyncStatusInfo createFromParcel(Parcel paramParcel) {
    return new SyncStatusInfo(paramParcel);
  }
  
  public SyncStatusInfo[] newArray(int paramInt) {
    return new SyncStatusInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncStatusInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
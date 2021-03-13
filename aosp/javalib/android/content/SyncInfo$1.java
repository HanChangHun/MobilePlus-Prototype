package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SyncInfo> {
  public SyncInfo createFromParcel(Parcel paramParcel) {
    return new SyncInfo(paramParcel);
  }
  
  public SyncInfo[] newArray(int paramInt) {
    return new SyncInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SyncRequest> {
  public SyncRequest createFromParcel(Parcel paramParcel) {
    return new SyncRequest(paramParcel, null);
  }
  
  public SyncRequest[] newArray(int paramInt) {
    return new SyncRequest[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncRequest$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
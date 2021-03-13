package android.app.backup;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RestoreSet> {
  public RestoreSet createFromParcel(Parcel paramParcel) {
    return new RestoreSet(paramParcel, null);
  }
  
  public RestoreSet[] newArray(int paramInt) {
    return new RestoreSet[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/RestoreSet$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
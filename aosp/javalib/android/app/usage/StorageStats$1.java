package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<StorageStats> {
  public StorageStats createFromParcel(Parcel paramParcel) {
    return new StorageStats(paramParcel);
  }
  
  public StorageStats[] newArray(int paramInt) {
    return new StorageStats[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/StorageStats$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
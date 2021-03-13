package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ExternalStorageStats> {
  public ExternalStorageStats createFromParcel(Parcel paramParcel) {
    return new ExternalStorageStats(paramParcel);
  }
  
  public ExternalStorageStats[] newArray(int paramInt) {
    return new ExternalStorageStats[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/ExternalStorageStats$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
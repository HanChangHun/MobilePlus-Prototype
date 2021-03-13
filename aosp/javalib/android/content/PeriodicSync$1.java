package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PeriodicSync> {
  public PeriodicSync createFromParcel(Parcel paramParcel) {
    return new PeriodicSync(paramParcel, null);
  }
  
  public PeriodicSync[] newArray(int paramInt) {
    return new PeriodicSync[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/PeriodicSync$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
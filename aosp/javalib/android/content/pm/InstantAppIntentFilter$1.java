package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<InstantAppIntentFilter> {
  public InstantAppIntentFilter createFromParcel(Parcel paramParcel) {
    return new InstantAppIntentFilter(paramParcel);
  }
  
  public InstantAppIntentFilter[] newArray(int paramInt) {
    return new InstantAppIntentFilter[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstantAppIntentFilter$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
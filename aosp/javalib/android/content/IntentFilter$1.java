package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<IntentFilter> {
  public IntentFilter createFromParcel(Parcel paramParcel) {
    return new IntentFilter(paramParcel);
  }
  
  public IntentFilter[] newArray(int paramInt) {
    return new IntentFilter[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IntentFilter$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
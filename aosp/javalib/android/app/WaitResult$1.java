package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<WaitResult> {
  public WaitResult createFromParcel(Parcel paramParcel) {
    return new WaitResult(paramParcel, null);
  }
  
  public WaitResult[] newArray(int paramInt) {
    return new WaitResult[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WaitResult$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
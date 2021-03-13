package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BackStackState> {
  public BackStackState createFromParcel(Parcel paramParcel) {
    return new BackStackState(paramParcel);
  }
  
  public BackStackState[] newArray(int paramInt) {
    return new BackStackState[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/BackStackState$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
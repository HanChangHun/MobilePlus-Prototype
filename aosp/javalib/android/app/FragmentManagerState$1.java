package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<FragmentManagerState> {
  public FragmentManagerState createFromParcel(Parcel paramParcel) {
    return new FragmentManagerState(paramParcel);
  }
  
  public FragmentManagerState[] newArray(int paramInt) {
    return new FragmentManagerState[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentManagerState$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<FragmentState> {
  public FragmentState createFromParcel(Parcel paramParcel) {
    return new FragmentState(paramParcel);
  }
  
  public FragmentState[] newArray(int paramInt) {
    return new FragmentState[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentState$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
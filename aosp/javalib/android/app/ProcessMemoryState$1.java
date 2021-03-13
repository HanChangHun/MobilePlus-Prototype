package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ProcessMemoryState> {
  public ProcessMemoryState createFromParcel(Parcel paramParcel) {
    return new ProcessMemoryState(paramParcel, null);
  }
  
  public ProcessMemoryState[] newArray(int paramInt) {
    return new ProcessMemoryState[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ProcessMemoryState$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ProcessInfo> {
  public ProcessInfo createFromParcel(Parcel paramParcel) {
    return new ProcessInfo(paramParcel);
  }
  
  public ProcessInfo[] newArray(int paramInt) {
    return new ProcessInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ProcessInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<InstrumentationInfo> {
  public InstrumentationInfo createFromParcel(Parcel paramParcel) {
    return new InstrumentationInfo(paramParcel, null);
  }
  
  public InstrumentationInfo[] newArray(int paramInt) {
    return new InstrumentationInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstrumentationInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
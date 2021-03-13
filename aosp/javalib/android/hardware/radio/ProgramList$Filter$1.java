package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ProgramList.Filter> {
  public ProgramList.Filter createFromParcel(Parcel paramParcel) {
    return new ProgramList.Filter(paramParcel, null);
  }
  
  public ProgramList.Filter[] newArray(int paramInt) {
    return new ProgramList.Filter[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ProgramList$Filter$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
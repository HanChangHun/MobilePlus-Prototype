package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ProgramSelector> {
  public ProgramSelector createFromParcel(Parcel paramParcel) {
    return new ProgramSelector(paramParcel, null);
  }
  
  public ProgramSelector[] newArray(int paramInt) {
    return new ProgramSelector[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ProgramSelector$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
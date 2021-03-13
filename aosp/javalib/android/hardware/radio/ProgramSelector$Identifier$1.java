package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ProgramSelector.Identifier> {
  public ProgramSelector.Identifier createFromParcel(Parcel paramParcel) {
    return new ProgramSelector.Identifier(paramParcel, null);
  }
  
  public ProgramSelector.Identifier[] newArray(int paramInt) {
    return new ProgramSelector.Identifier[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ProgramSelector$Identifier$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
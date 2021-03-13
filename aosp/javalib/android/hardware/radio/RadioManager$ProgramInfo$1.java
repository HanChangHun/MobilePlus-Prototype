package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RadioManager.ProgramInfo> {
  public RadioManager.ProgramInfo createFromParcel(Parcel paramParcel) {
    return new RadioManager.ProgramInfo(paramParcel, null);
  }
  
  public RadioManager.ProgramInfo[] newArray(int paramInt) {
    return new RadioManager.ProgramInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$ProgramInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
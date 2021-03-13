package android.hardware.input;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<TouchCalibration> {
  public TouchCalibration createFromParcel(Parcel paramParcel) {
    return new TouchCalibration(paramParcel);
  }
  
  public TouchCalibration[] newArray(int paramInt) {
    return new TouchCalibration[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/TouchCalibration$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
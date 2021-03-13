package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BrightnessCorrection> {
  public BrightnessCorrection createFromParcel(Parcel paramParcel) {
    return (paramParcel.readInt() != 1) ? null : BrightnessCorrection.ScaleAndTranslateLog.readFromParcel(paramParcel);
  }
  
  public BrightnessCorrection[] newArray(int paramInt) {
    return new BrightnessCorrection[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/BrightnessCorrection$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
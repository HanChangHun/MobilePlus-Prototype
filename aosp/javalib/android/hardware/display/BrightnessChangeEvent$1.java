package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BrightnessChangeEvent> {
  public BrightnessChangeEvent createFromParcel(Parcel paramParcel) {
    return new BrightnessChangeEvent(paramParcel, null);
  }
  
  public BrightnessChangeEvent[] newArray(int paramInt) {
    return new BrightnessChangeEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/BrightnessChangeEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RadioManager.FmBandConfig> {
  public RadioManager.FmBandConfig createFromParcel(Parcel paramParcel) {
    return new RadioManager.FmBandConfig(paramParcel, null);
  }
  
  public RadioManager.FmBandConfig[] newArray(int paramInt) {
    return new RadioManager.FmBandConfig[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$FmBandConfig$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
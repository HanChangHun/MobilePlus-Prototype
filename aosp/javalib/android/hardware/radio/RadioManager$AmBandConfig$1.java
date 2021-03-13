package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RadioManager.AmBandConfig> {
  public RadioManager.AmBandConfig createFromParcel(Parcel paramParcel) {
    return new RadioManager.AmBandConfig(paramParcel, null);
  }
  
  public RadioManager.AmBandConfig[] newArray(int paramInt) {
    return new RadioManager.AmBandConfig[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$AmBandConfig$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
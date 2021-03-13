package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RadioMetadata.Clock> {
  public RadioMetadata.Clock createFromParcel(Parcel paramParcel) {
    return new RadioMetadata.Clock(paramParcel, null);
  }
  
  public RadioMetadata.Clock[] newArray(int paramInt) {
    return new RadioMetadata.Clock[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioMetadata$Clock$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
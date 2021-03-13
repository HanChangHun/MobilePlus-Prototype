package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RadioMetadata> {
  public RadioMetadata createFromParcel(Parcel paramParcel) {
    return new RadioMetadata(paramParcel, null);
  }
  
  public RadioMetadata[] newArray(int paramInt) {
    return new RadioMetadata[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioMetadata$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
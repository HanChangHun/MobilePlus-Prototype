package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RadioManager.FmBandDescriptor> {
  public RadioManager.FmBandDescriptor createFromParcel(Parcel paramParcel) {
    return new RadioManager.FmBandDescriptor(paramParcel, null);
  }
  
  public RadioManager.FmBandDescriptor[] newArray(int paramInt) {
    return new RadioManager.FmBandDescriptor[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$FmBandDescriptor$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
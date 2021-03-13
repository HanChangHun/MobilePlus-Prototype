package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RadioManager.AmBandDescriptor> {
  public RadioManager.AmBandDescriptor createFromParcel(Parcel paramParcel) {
    return new RadioManager.AmBandDescriptor(paramParcel, null);
  }
  
  public RadioManager.AmBandDescriptor[] newArray(int paramInt) {
    return new RadioManager.AmBandDescriptor[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$AmBandDescriptor$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
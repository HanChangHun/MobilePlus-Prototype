package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RadioManager.BandDescriptor> {
  public RadioManager.BandDescriptor createFromParcel(Parcel paramParcel) {
    StringBuilder stringBuilder;
    int i = RadioManager.BandDescriptor.access$100(paramParcel);
    if (i != 0)
      if (i != 1 && i != 2) {
        if (i != 3) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unsupported band: ");
          stringBuilder.append(i);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
      } else {
        return new RadioManager.FmBandDescriptor((Parcel)stringBuilder, null);
      }  
    return new RadioManager.AmBandDescriptor((Parcel)stringBuilder, null);
  }
  
  public RadioManager.BandDescriptor[] newArray(int paramInt) {
    return new RadioManager.BandDescriptor[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$BandDescriptor$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
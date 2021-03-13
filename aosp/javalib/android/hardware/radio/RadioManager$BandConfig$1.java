package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RadioManager.BandConfig> {
  public RadioManager.BandConfig createFromParcel(Parcel paramParcel) {
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
        return new RadioManager.FmBandConfig((Parcel)stringBuilder, null);
      }  
    return new RadioManager.AmBandConfig((Parcel)stringBuilder, null);
  }
  
  public RadioManager.BandConfig[] newArray(int paramInt) {
    return new RadioManager.BandConfig[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$BandConfig$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RadioManager.ModuleProperties> {
  public RadioManager.ModuleProperties createFromParcel(Parcel paramParcel) {
    return new RadioManager.ModuleProperties(paramParcel, null);
  }
  
  public RadioManager.ModuleProperties[] newArray(int paramInt) {
    return new RadioManager.ModuleProperties[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$ModuleProperties$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
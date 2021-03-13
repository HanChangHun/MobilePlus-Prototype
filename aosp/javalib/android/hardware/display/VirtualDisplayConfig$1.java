package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<VirtualDisplayConfig> {
  public VirtualDisplayConfig createFromParcel(Parcel paramParcel) {
    return new VirtualDisplayConfig(paramParcel);
  }
  
  public VirtualDisplayConfig[] newArray(int paramInt) {
    return new VirtualDisplayConfig[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/VirtualDisplayConfig$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
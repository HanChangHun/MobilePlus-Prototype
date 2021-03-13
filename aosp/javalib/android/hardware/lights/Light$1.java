package android.hardware.lights;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Light> {
  public Light createFromParcel(Parcel paramParcel) {
    return new Light(paramParcel, null);
  }
  
  public Light[] newArray(int paramInt) {
    return new Light[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/Light$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
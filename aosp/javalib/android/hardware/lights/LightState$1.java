package android.hardware.lights;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<LightState> {
  public LightState createFromParcel(Parcel paramParcel) {
    return new LightState(paramParcel, null);
  }
  
  public LightState[] newArray(int paramInt) {
    return new LightState[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/LightState$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
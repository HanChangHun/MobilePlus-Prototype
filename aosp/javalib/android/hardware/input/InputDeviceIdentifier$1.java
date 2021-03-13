package android.hardware.input;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<InputDeviceIdentifier> {
  public InputDeviceIdentifier createFromParcel(Parcel paramParcel) {
    return new InputDeviceIdentifier(paramParcel, null);
  }
  
  public InputDeviceIdentifier[] newArray(int paramInt) {
    return new InputDeviceIdentifier[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/InputDeviceIdentifier$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.hardware.input;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<KeyboardLayout> {
  public KeyboardLayout createFromParcel(Parcel paramParcel) {
    return new KeyboardLayout(paramParcel, null);
  }
  
  public KeyboardLayout[] newArray(int paramInt) {
    return new KeyboardLayout[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/KeyboardLayout$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
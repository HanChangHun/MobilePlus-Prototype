package android.hardware.biometrics;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BiometricSourceType> {
  public BiometricSourceType createFromParcel(Parcel paramParcel) {
    return BiometricSourceType.valueOf(paramParcel.readString());
  }
  
  public BiometricSourceType[] newArray(int paramInt) {
    return new BiometricSourceType[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricSourceType$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
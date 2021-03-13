package android.hardware.biometrics;

import android.os.Parcel;
import android.os.Parcelable;

public enum BiometricSourceType implements Parcelable {
  FACE, FINGERPRINT, IRIS;
  
  public static final Parcelable.Creator<BiometricSourceType> CREATOR;
  
  static {
    FACE = new BiometricSourceType("FACE", 1);
    BiometricSourceType biometricSourceType = new BiometricSourceType("IRIS", 2);
    IRIS = biometricSourceType;
    $VALUES = new BiometricSourceType[] { FINGERPRINT, FACE, biometricSourceType };
    CREATOR = new Parcelable.Creator<BiometricSourceType>() {
        public BiometricSourceType createFromParcel(Parcel param1Parcel) {
          return BiometricSourceType.valueOf(param1Parcel.readString());
        }
        
        public BiometricSourceType[] newArray(int param1Int) {
          return new BiometricSourceType[param1Int];
        }
      };
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(name());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricSourceType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
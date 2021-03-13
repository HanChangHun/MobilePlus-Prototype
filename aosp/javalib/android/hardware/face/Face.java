package android.hardware.face;

import android.hardware.biometrics.BiometricAuthenticator;
import android.os.Parcel;
import android.os.Parcelable;

public final class Face extends BiometricAuthenticator.Identifier {
  public static final Parcelable.Creator<Face> CREATOR = new Parcelable.Creator<Face>() {
      public Face createFromParcel(Parcel param1Parcel) {
        return new Face(param1Parcel);
      }
      
      public Face[] newArray(int param1Int) {
        return new Face[param1Int];
      }
    };
  
  private Face(Parcel paramParcel) {
    super(paramParcel.readString(), paramParcel.readInt(), paramParcel.readLong());
  }
  
  public Face(CharSequence paramCharSequence, int paramInt, long paramLong) {
    super(paramCharSequence, paramInt, paramLong);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(getName().toString());
    paramParcel.writeInt(getBiometricId());
    paramParcel.writeLong(getDeviceId());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/Face.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
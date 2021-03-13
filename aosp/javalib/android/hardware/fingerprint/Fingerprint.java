package android.hardware.fingerprint;

import android.hardware.biometrics.BiometricAuthenticator;
import android.os.Parcel;
import android.os.Parcelable;

public final class Fingerprint extends BiometricAuthenticator.Identifier {
  public static final Parcelable.Creator<Fingerprint> CREATOR = new Parcelable.Creator<Fingerprint>() {
      public Fingerprint createFromParcel(Parcel param1Parcel) {
        return new Fingerprint(param1Parcel);
      }
      
      public Fingerprint[] newArray(int param1Int) {
        return new Fingerprint[param1Int];
      }
    };
  
  private int mGroupId;
  
  private Fingerprint(Parcel paramParcel) {
    super(paramParcel.readString(), paramParcel.readInt(), paramParcel.readLong());
    this.mGroupId = paramParcel.readInt();
  }
  
  public Fingerprint(CharSequence paramCharSequence, int paramInt1, int paramInt2, long paramLong) {
    super(paramCharSequence, paramInt2, paramLong);
    this.mGroupId = paramInt1;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getGroupId() {
    return this.mGroupId;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(getName().toString());
    paramParcel.writeInt(getBiometricId());
    paramParcel.writeLong(getDeviceId());
    paramParcel.writeInt(this.mGroupId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/Fingerprint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
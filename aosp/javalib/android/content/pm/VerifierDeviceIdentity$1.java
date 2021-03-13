package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<VerifierDeviceIdentity> {
  public VerifierDeviceIdentity createFromParcel(Parcel paramParcel) {
    return new VerifierDeviceIdentity(paramParcel, null);
  }
  
  public VerifierDeviceIdentity[] newArray(int paramInt) {
    return new VerifierDeviceIdentity[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/VerifierDeviceIdentity$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.hardware.fingerprint;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Fingerprint> {
  public Fingerprint createFromParcel(Parcel paramParcel) {
    return new Fingerprint(paramParcel, null);
  }
  
  public Fingerprint[] newArray(int paramInt) {
    return new Fingerprint[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/Fingerprint$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
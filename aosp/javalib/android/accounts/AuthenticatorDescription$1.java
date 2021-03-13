package android.accounts;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AuthenticatorDescription> {
  public AuthenticatorDescription createFromParcel(Parcel paramParcel) {
    return new AuthenticatorDescription(paramParcel, null);
  }
  
  public AuthenticatorDescription[] newArray(int paramInt) {
    return new AuthenticatorDescription[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AuthenticatorDescription$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
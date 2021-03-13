package android.accounts;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AccountAuthenticatorResponse> {
  public AccountAuthenticatorResponse createFromParcel(Parcel paramParcel) {
    return new AccountAuthenticatorResponse(paramParcel);
  }
  
  public AccountAuthenticatorResponse[] newArray(int paramInt) {
    return new AccountAuthenticatorResponse[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountAuthenticatorResponse$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
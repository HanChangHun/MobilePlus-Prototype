package android.accounts;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AccountManagerResponse> {
  public AccountManagerResponse createFromParcel(Parcel paramParcel) {
    return new AccountManagerResponse(paramParcel);
  }
  
  public AccountManagerResponse[] newArray(int paramInt) {
    return new AccountManagerResponse[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManagerResponse$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
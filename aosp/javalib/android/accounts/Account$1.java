package android.accounts;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Account> {
  public Account createFromParcel(Parcel paramParcel) {
    return new Account(paramParcel);
  }
  
  public Account[] newArray(int paramInt) {
    return new Account[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/Account$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.accounts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public class AccountManagerResponse implements Parcelable {
  public static final Parcelable.Creator<AccountManagerResponse> CREATOR = new Parcelable.Creator<AccountManagerResponse>() {
      public AccountManagerResponse createFromParcel(Parcel param1Parcel) {
        return new AccountManagerResponse(param1Parcel);
      }
      
      public AccountManagerResponse[] newArray(int param1Int) {
        return new AccountManagerResponse[param1Int];
      }
    };
  
  private IAccountManagerResponse mResponse;
  
  public AccountManagerResponse(IAccountManagerResponse paramIAccountManagerResponse) {
    this.mResponse = paramIAccountManagerResponse;
  }
  
  public AccountManagerResponse(Parcel paramParcel) {
    this.mResponse = IAccountManagerResponse.Stub.asInterface(paramParcel.readStrongBinder());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void onError(int paramInt, String paramString) {
    try {
      this.mResponse.onError(paramInt, paramString);
    } catch (RemoteException remoteException) {}
  }
  
  public void onResult(Bundle paramBundle) {
    try {
      this.mResponse.onResult(paramBundle);
    } catch (RemoteException remoteException) {}
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeStrongBinder(this.mResponse.asBinder());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManagerResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
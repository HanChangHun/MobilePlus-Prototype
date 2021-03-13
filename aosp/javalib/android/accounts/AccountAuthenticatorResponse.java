package android.accounts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;

public class AccountAuthenticatorResponse implements Parcelable {
  public static final Parcelable.Creator<AccountAuthenticatorResponse> CREATOR = new Parcelable.Creator<AccountAuthenticatorResponse>() {
      public AccountAuthenticatorResponse createFromParcel(Parcel param1Parcel) {
        return new AccountAuthenticatorResponse(param1Parcel);
      }
      
      public AccountAuthenticatorResponse[] newArray(int param1Int) {
        return new AccountAuthenticatorResponse[param1Int];
      }
    };
  
  private static final String TAG = "AccountAuthenticator";
  
  private IAccountAuthenticatorResponse mAccountAuthenticatorResponse;
  
  public AccountAuthenticatorResponse(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse) {
    this.mAccountAuthenticatorResponse = paramIAccountAuthenticatorResponse;
  }
  
  public AccountAuthenticatorResponse(Parcel paramParcel) {
    this.mAccountAuthenticatorResponse = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel.readStrongBinder());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void onError(int paramInt, String paramString) {
    if (Log.isLoggable("AccountAuthenticator", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("AccountAuthenticatorResponse.onError: ");
      stringBuilder.append(paramInt);
      stringBuilder.append(", ");
      stringBuilder.append(paramString);
      Log.v("AccountAuthenticator", stringBuilder.toString());
    } 
    try {
      this.mAccountAuthenticatorResponse.onError(paramInt, paramString);
    } catch (RemoteException remoteException) {}
  }
  
  public void onRequestContinued() {
    if (Log.isLoggable("AccountAuthenticator", 2))
      Log.v("AccountAuthenticator", "AccountAuthenticatorResponse.onRequestContinued"); 
    try {
      this.mAccountAuthenticatorResponse.onRequestContinued();
    } catch (RemoteException remoteException) {}
  }
  
  public void onResult(Bundle paramBundle) {
    if (Log.isLoggable("AccountAuthenticator", 2)) {
      paramBundle.keySet();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("AccountAuthenticatorResponse.onResult: ");
      stringBuilder.append(AccountManager.sanitizeResult(paramBundle));
      Log.v("AccountAuthenticator", stringBuilder.toString());
    } 
    try {
      this.mAccountAuthenticatorResponse.onResult(paramBundle);
    } catch (RemoteException remoteException) {}
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeStrongBinder(this.mAccountAuthenticatorResponse.asBinder());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountAuthenticatorResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.accounts;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.RemoteException;

class null extends AccountManager.Future2Task<Account[]> {
  null(Handler paramHandler, AccountManagerCallback<Account> paramAccountManagerCallback) {
    super(paramHandler, paramAccountManagerCallback);
  }
  
  public Account[] bundleToResult(Bundle paramBundle) throws AuthenticatorException {
    if (paramBundle.containsKey("accounts")) {
      Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray("accounts");
      Account[] arrayOfAccount = new Account[arrayOfParcelable.length];
      for (byte b = 0; b < arrayOfParcelable.length; b++)
        arrayOfAccount[b] = (Account)arrayOfParcelable[b]; 
      return arrayOfAccount;
    } 
    throw new AuthenticatorException("no result in response");
  }
  
  public void doWork() throws RemoteException {
    AccountManager.access$000(AccountManager.this).getAccountsByFeatures(this.mResponse, type, features, AccountManager.access$100(AccountManager.this).getOpPackageName());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
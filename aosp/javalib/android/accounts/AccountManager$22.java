package android.accounts;

import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;

class null extends AccountManager.Future2Task<Boolean> {
  null(Handler paramHandler, AccountManagerCallback<Boolean> paramAccountManagerCallback) {
    super(paramHandler, paramAccountManagerCallback);
  }
  
  public Boolean bundleToResult(Bundle paramBundle) throws AuthenticatorException {
    if (paramBundle.containsKey("booleanResult"))
      return Boolean.valueOf(paramBundle.getBoolean("booleanResult")); 
    throw new AuthenticatorException("no result in response");
  }
  
  public void doWork() throws RemoteException {
    AccountManager.access$000(AccountManager.this).isCredentialsUpdateSuggested(this.mResponse, account, statusToken);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$22.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
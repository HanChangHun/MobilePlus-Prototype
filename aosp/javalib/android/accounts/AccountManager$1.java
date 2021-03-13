package android.accounts;

import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;

class null extends AccountManager.Future2Task<String> {
  null(Handler paramHandler, AccountManagerCallback<String> paramAccountManagerCallback) {
    super(paramHandler, paramAccountManagerCallback);
  }
  
  public String bundleToResult(Bundle paramBundle) throws AuthenticatorException {
    if (paramBundle.containsKey("authTokenLabelKey"))
      return paramBundle.getString("authTokenLabelKey"); 
    throw new AuthenticatorException("no result in response");
  }
  
  public void doWork() throws RemoteException {
    AccountManager.access$000(AccountManager.this).getAuthTokenLabel(this.mResponse, accountType, authTokenType);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
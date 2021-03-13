package android.accounts;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;

class null extends AccountManager.AmsTask {
  null(Activity paramActivity, Handler paramHandler, AccountManagerCallback<Bundle> paramAccountManagerCallback) {
    super(paramActivity, paramHandler, paramAccountManagerCallback);
  }
  
  public void doWork() throws RemoteException {
    AccountManager.access$000(AccountManager.this).getAuthToken(this.mResponse, account, authTokenType, false, true, optionsIn);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$7.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
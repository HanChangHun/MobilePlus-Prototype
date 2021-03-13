package android.accounts;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;

class null extends AccountManager.AmsTask {
  null(Activity paramActivity1, Handler paramHandler, AccountManagerCallback<Bundle> paramAccountManagerCallback) {
    super(paramActivity1, paramHandler, paramAccountManagerCallback);
  }
  
  public void doWork() throws RemoteException {
    boolean bool;
    IAccountManager iAccountManager = AccountManager.access$000(AccountManager.this);
    IAccountManagerResponse iAccountManagerResponse = this.mResponse;
    String str = accountType;
    if (activity != null) {
      bool = true;
    } else {
      bool = false;
    } 
    iAccountManager.editProperties(iAccountManagerResponse, str, bool);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$14.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
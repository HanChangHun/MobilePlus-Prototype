package android.accounts;

import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;

class null extends AccountManager.Future2Task<Account> {
  null(Handler paramHandler, AccountManagerCallback<Account> paramAccountManagerCallback) {
    super(paramHandler, paramAccountManagerCallback);
  }
  
  public Account bundleToResult(Bundle paramBundle) throws AuthenticatorException {
    return new Account(paramBundle.getString("authAccount"), paramBundle.getString("accountType"), paramBundle.getString("accountAccessId"));
  }
  
  public void doWork() throws RemoteException {
    AccountManager.access$000(AccountManager.this).renameAccount(this.mResponse, account, newName);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
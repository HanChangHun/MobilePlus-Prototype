package android.accounts;

import android.os.Bundle;
import android.os.RemoteException;
import java.io.IOException;

class null implements AccountManagerCallback<Bundle> {
  public void run(AccountManagerFuture<Bundle> paramAccountManagerFuture) {
    try {
      Bundle bundle = paramAccountManagerFuture.getResult();
      String str1 = bundle.getString("authAccount");
      String str2 = bundle.getString("accountType");
      if (str1 == null) {
        if (AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mActivity != null) {
          AccountManager.GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = AccountManager.GetAuthTokenByTypeAndFeaturesTask.this;
          getAuthTokenByTypeAndFeaturesTask.mFuture = getAuthTokenByTypeAndFeaturesTask.this$0.addAccount(AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAccountType, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mFeatures, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAddAccountOptions, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mActivity, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
        } else {
          Bundle bundle1 = new Bundle();
          bundle1.putString("authAccount", null);
          bundle1.putString("accountType", null);
          bundle1.putString("authtoken", null);
          bundle1.putBinder("accountAccessId", null);
          try {
            AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mResponse.onResult(bundle1);
          } catch (RemoteException remoteException) {}
        } 
      } else {
        AccountManager.GetAuthTokenByTypeAndFeaturesTask.access$1502(AccountManager.GetAuthTokenByTypeAndFeaturesTask.this, 1);
        Account account = new Account((String)remoteException, str2);
        if (AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mActivity == null) {
          AccountManager.GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = AccountManager.GetAuthTokenByTypeAndFeaturesTask.this;
          getAuthTokenByTypeAndFeaturesTask.mFuture = getAuthTokenByTypeAndFeaturesTask.this$0.getAuthToken(account, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, false, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
        } else {
          AccountManager.GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = AccountManager.GetAuthTokenByTypeAndFeaturesTask.this;
          getAuthTokenByTypeAndFeaturesTask.mFuture = getAuthTokenByTypeAndFeaturesTask.this$0.getAuthToken(account, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mLoginOptions, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mActivity, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
        } 
      } 
      return;
    } catch (OperationCanceledException operationCanceledException) {
      AccountManager.GetAuthTokenByTypeAndFeaturesTask.access$1200(AccountManager.GetAuthTokenByTypeAndFeaturesTask.this, operationCanceledException);
      return;
    } catch (IOException iOException) {
      AccountManager.GetAuthTokenByTypeAndFeaturesTask.access$1300(AccountManager.GetAuthTokenByTypeAndFeaturesTask.this, iOException);
      return;
    } catch (AuthenticatorException authenticatorException) {
      AccountManager.GetAuthTokenByTypeAndFeaturesTask.access$1400(AccountManager.GetAuthTokenByTypeAndFeaturesTask.this, authenticatorException);
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$GetAuthTokenByTypeAndFeaturesTask$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.accounts;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import java.io.IOException;

class GetAuthTokenByTypeAndFeaturesTask extends AccountManager.AmsTask implements AccountManagerCallback<Bundle> {
  final String mAccountType;
  
  final Bundle mAddAccountOptions;
  
  final String mAuthTokenType;
  
  final String[] mFeatures;
  
  volatile AccountManagerFuture<Bundle> mFuture = null;
  
  final Bundle mLoginOptions;
  
  final AccountManagerCallback<Bundle> mMyCallback;
  
  private volatile int mNumAccounts = 0;
  
  GetAuthTokenByTypeAndFeaturesTask(String paramString1, String paramString2, String[] paramArrayOfString, Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    super(paramActivity, paramHandler, paramAccountManagerCallback);
    if (paramString1 != null) {
      this.mAccountType = paramString1;
      this.mAuthTokenType = paramString2;
      this.mFeatures = paramArrayOfString;
      this.mAddAccountOptions = paramBundle1;
      this.mLoginOptions = paramBundle2;
      this.mMyCallback = this;
      return;
    } 
    throw new IllegalArgumentException("account type is null");
  }
  
  public void doWork() throws RemoteException {
    AccountManager.access$1600(AccountManager.this, this.mAccountType, this.mFeatures, new AccountManagerCallback<Bundle>() {
          public void run(AccountManagerFuture<Bundle> param2AccountManagerFuture) {
            try {
              Bundle bundle = param2AccountManagerFuture.getResult();
              String str1 = bundle.getString("authAccount");
              String str2 = bundle.getString("accountType");
              if (str1 == null) {
                if (AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mActivity != null) {
                  AccountManager.GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = AccountManager.GetAuthTokenByTypeAndFeaturesTask.this;
                  getAuthTokenByTypeAndFeaturesTask.mFuture = AccountManager.this.addAccount(AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAccountType, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mFeatures, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAddAccountOptions, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mActivity, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
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
                  getAuthTokenByTypeAndFeaturesTask.mFuture = AccountManager.this.getAuthToken(account, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, false, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                } else {
                  AccountManager.GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = AccountManager.GetAuthTokenByTypeAndFeaturesTask.this;
                  getAuthTokenByTypeAndFeaturesTask.mFuture = AccountManager.this.getAuthToken(account, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mLoginOptions, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mActivity, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                } 
              } 
              return;
            } catch (OperationCanceledException operationCanceledException) {
              AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.setException(operationCanceledException);
              return;
            } catch (IOException iOException) {
              AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.setException(iOException);
              return;
            } catch (AuthenticatorException authenticatorException) {
              AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.setException(authenticatorException);
              return;
            } 
          }
        }this.mHandler);
  }
  
  public void run(AccountManagerFuture<Bundle> paramAccountManagerFuture) {
    try {
      String str;
      Bundle bundle = paramAccountManagerFuture.getResult();
      if (this.mNumAccounts == 0) {
        AuthenticatorException authenticatorException;
        String str2 = bundle.getString("authAccount");
        String str1 = bundle.getString("accountType");
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str1)) {
          authenticatorException = new AuthenticatorException();
          this("account not in result");
          setException(authenticatorException);
          return;
        } 
        str = bundle.getString("accountAccessId");
        Account account = new Account();
        this(str2, (String)authenticatorException, str);
        this.mNumAccounts = 1;
        AccountManager.this.getAuthToken(account, this.mAuthTokenType, (Bundle)null, this.mActivity, this.mMyCallback, this.mHandler);
        return;
      } 
      set((Bundle)str);
    } catch (OperationCanceledException operationCanceledException) {
      cancel(true);
    } catch (IOException iOException) {
      setException(iOException);
    } catch (AuthenticatorException authenticatorException) {
      setException(authenticatorException);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$GetAuthTokenByTypeAndFeaturesTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
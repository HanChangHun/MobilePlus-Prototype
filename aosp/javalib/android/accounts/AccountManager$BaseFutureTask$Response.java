package android.accounts;

import android.os.Bundle;

public class Response extends IAccountManagerResponse.Stub {
  public void onError(int paramInt, String paramString) {
    if (paramInt == 4 || paramInt == 100 || paramInt == 101) {
      AccountManager.BaseFutureTask.this.cancel(true);
      return;
    } 
    AccountManager.BaseFutureTask baseFutureTask = AccountManager.BaseFutureTask.this;
    AccountManager.BaseFutureTask.access$1100(baseFutureTask, AccountManager.access$700(baseFutureTask.this$0, paramInt, paramString));
  }
  
  public void onResult(Bundle paramBundle) {
    try {
      paramBundle = AccountManager.BaseFutureTask.this.bundleToResult(paramBundle);
      if (paramBundle == null)
        return; 
      AccountManager.BaseFutureTask.access$1000(AccountManager.BaseFutureTask.this, paramBundle);
      return;
    } catch (ClassCastException classCastException) {
    
    } catch (AuthenticatorException authenticatorException) {}
    onError(5, "no result in response");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$BaseFutureTask$Response.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
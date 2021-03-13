package android.accounts;

import android.app.Activity;
import android.os.Bundle;

@Deprecated
public class AccountAuthenticatorActivity extends Activity {
  private AccountAuthenticatorResponse mAccountAuthenticatorResponse = null;
  
  private Bundle mResultBundle = null;
  
  public void finish() {
    AccountAuthenticatorResponse accountAuthenticatorResponse = this.mAccountAuthenticatorResponse;
    if (accountAuthenticatorResponse != null) {
      Bundle bundle = this.mResultBundle;
      if (bundle != null) {
        accountAuthenticatorResponse.onResult(bundle);
      } else {
        accountAuthenticatorResponse.onError(4, "canceled");
      } 
      this.mAccountAuthenticatorResponse = null;
    } 
    super.finish();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    AccountAuthenticatorResponse accountAuthenticatorResponse = (AccountAuthenticatorResponse)getIntent().getParcelableExtra("accountAuthenticatorResponse");
    this.mAccountAuthenticatorResponse = accountAuthenticatorResponse;
    if (accountAuthenticatorResponse != null)
      accountAuthenticatorResponse.onRequestContinued(); 
  }
  
  public final void setAccountAuthenticatorResult(Bundle paramBundle) {
    this.mResultBundle = paramBundle;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountAuthenticatorActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
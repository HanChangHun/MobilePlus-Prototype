package android.accounts;

import android.text.TextUtils;
import android.widget.TextView;
import java.io.IOException;

class null implements AccountManagerCallback<String> {
  public void run(AccountManagerFuture<String> paramAccountManagerFuture) {
    try {
      String str = paramAccountManagerFuture.getResult();
      if (!TextUtils.isEmpty(str)) {
        GrantCredentialsPermissionActivity grantCredentialsPermissionActivity = GrantCredentialsPermissionActivity.this;
        Runnable runnable = new Runnable() {
            public void run() {
              if (!GrantCredentialsPermissionActivity.this.isFinishing()) {
                authTokenTypeView.setText(authTokenLabel);
                authTokenTypeView.setVisibility(0);
              } 
            }
          };
        super(this, str);
        grantCredentialsPermissionActivity.runOnUiThread(runnable);
      } 
    } catch (OperationCanceledException operationCanceledException) {
    
    } catch (IOException iOException) {
    
    } catch (AuthenticatorException authenticatorException) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/GrantCredentialsPermissionActivity$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
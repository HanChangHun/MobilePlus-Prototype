package android.accounts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CantAddAccountActivity extends Activity {
  public static final String EXTRA_ERROR_CODE = "android.accounts.extra.ERROR_CODE";
  
  public void onCancelButtonClicked(View paramView) {
    onBackPressed();
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(17367096);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/CantAddAccountActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
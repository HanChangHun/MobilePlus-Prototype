package android.accounts;

import android.app.Activity;
import android.app.ActivityTaskManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.IOException;

public class GrantCredentialsPermissionActivity extends Activity implements View.OnClickListener {
  public static final String EXTRAS_ACCOUNT = "account";
  
  public static final String EXTRAS_AUTH_TOKEN_TYPE = "authTokenType";
  
  public static final String EXTRAS_REQUESTING_UID = "uid";
  
  public static final String EXTRAS_RESPONSE = "response";
  
  private Account mAccount;
  
  private String mAuthTokenType;
  
  private int mCallingUid;
  
  protected LayoutInflater mInflater;
  
  private Bundle mResultBundle = null;
  
  private int mUid;
  
  private String getAccountLabel(Account paramAccount) {
    AuthenticatorDescription[] arrayOfAuthenticatorDescription = AccountManager.get((Context)this).getAuthenticatorTypes();
    byte b = 0;
    int i = arrayOfAuthenticatorDescription.length;
    while (b < i) {
      AuthenticatorDescription authenticatorDescription = arrayOfAuthenticatorDescription[b];
      if (authenticatorDescription.type.equals(paramAccount.type))
        try {
          return createPackageContext(authenticatorDescription.packageName, 0).getString(authenticatorDescription.labelId);
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
          return paramAccount.type;
        } catch (android.content.res.Resources.NotFoundException notFoundException) {
          return paramAccount.type;
        }  
      b++;
    } 
    return paramAccount.type;
  }
  
  private View newPackageView(String paramString) {
    View view = this.mInflater.inflate(17367232, null);
    ((TextView)view.findViewById(16909271)).setText(paramString);
    return view;
  }
  
  public void finish() {
    AccountAuthenticatorResponse accountAuthenticatorResponse = (AccountAuthenticatorResponse)getIntent().getParcelableExtra("response");
    if (accountAuthenticatorResponse != null) {
      Bundle bundle = this.mResultBundle;
      if (bundle != null) {
        accountAuthenticatorResponse.onResult(bundle);
      } else {
        accountAuthenticatorResponse.onError(4, "canceled");
      } 
    } 
    super.finish();
  }
  
  public void onClick(View paramView) {
    int i = paramView.getId();
    if (i != 16908746) {
      if (i == 16908925) {
        AccountManager.get((Context)this).updateAppPermission(this.mAccount, this.mAuthTokenType, this.mUid, false);
        setResult(0);
      } 
    } else {
      AccountManager.get((Context)this).updateAppPermission(this.mAccount, this.mAuthTokenType, this.mUid, true);
      Intent intent = new Intent();
      intent.putExtra("retry", true);
      setResult(-1, intent);
      setAccountAuthenticatorResult(intent.getExtras());
    } 
    finish();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    getWindow().addSystemFlags(524288);
    setContentView(17367169);
    setTitle(17040267);
    this.mInflater = (LayoutInflater)getSystemService("layout_inflater");
    paramBundle = getIntent().getExtras();
    if (paramBundle == null) {
      setResult(0);
      finish();
      return;
    } 
    this.mAccount = (Account)paramBundle.getParcelable("account");
    this.mAuthTokenType = paramBundle.getString("authTokenType");
    this.mUid = paramBundle.getInt("uid");
    PackageManager packageManager = getPackageManager();
    String[] arrayOfString = packageManager.getPackagesForUid(this.mUid);
    if (this.mAccount == null || this.mAuthTokenType == null || arrayOfString == null) {
      setResult(0);
      finish();
      return;
    } 
    try {
      IBinder iBinder = getActivityToken();
      this.mCallingUid = ActivityTaskManager.getService().getLaunchedFromUid(iBinder);
    } catch (RemoteException remoteException) {
      String str = getClass().getSimpleName();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to get caller identity \n");
      stringBuilder.append(remoteException);
      Log.w(str, stringBuilder.toString());
    } 
    if (!UserHandle.isSameApp(this.mCallingUid, 1000) && this.mCallingUid != this.mUid) {
      setResult(0);
      finish();
      return;
    } 
    try {
      String str = getAccountLabel(this.mAccount);
      final TextView authTokenTypeView = (TextView)findViewById(16908769);
      textView.setVisibility(8);
      AccountManagerCallback<String> accountManagerCallback = new AccountManagerCallback<String>() {
          public void run(AccountManagerFuture<String> param1AccountManagerFuture) {
            try {
              String str = param1AccountManagerFuture.getResult();
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
        };
      if (!"com.android.AccountManager.ACCOUNT_ACCESS_TOKEN_TYPE".equals(this.mAuthTokenType))
        AccountManager.get((Context)this).getAuthTokenLabel(this.mAccount.type, this.mAuthTokenType, accountManagerCallback, null); 
      findViewById(16908746).setOnClickListener(this);
      findViewById(16908925).setOnClickListener(this);
      LinearLayout linearLayout = (LinearLayout)findViewById(16909272);
      int i = arrayOfString.length;
      for (byte b = 0; b < i; b++) {
        String str1 = arrayOfString[b];
        try {
          String str2 = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str1, 0)).toString();
          str1 = str2;
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
        linearLayout.addView(newPackageView(str1));
      } 
      ((TextView)findViewById(16908692)).setText(this.mAccount.name);
      ((TextView)findViewById(16908695)).setText(str);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      setResult(0);
      finish();
      return;
    } 
  }
  
  public final void setAccountAuthenticatorResult(Bundle paramBundle) {
    this.mResultBundle = paramBundle;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/GrantCredentialsPermissionActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
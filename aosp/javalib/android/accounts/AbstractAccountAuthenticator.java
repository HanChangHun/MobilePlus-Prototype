package android.accounts;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;

public abstract class AbstractAccountAuthenticator {
  private static final String KEY_ACCOUNT = "android.accounts.AbstractAccountAuthenticator.KEY_ACCOUNT";
  
  private static final String KEY_AUTH_TOKEN_TYPE = "android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE";
  
  public static final String KEY_CUSTOM_TOKEN_EXPIRY = "android.accounts.expiry";
  
  private static final String KEY_OPTIONS = "android.accounts.AbstractAccountAuthenticator.KEY_OPTIONS";
  
  private static final String KEY_REQUIRED_FEATURES = "android.accounts.AbstractAccountAuthenticator.KEY_REQUIRED_FEATURES";
  
  private static final String TAG = "AccountAuthenticator";
  
  private final Context mContext;
  
  private Transport mTransport = new Transport();
  
  public AbstractAccountAuthenticator(Context paramContext) {
    this.mContext = paramContext;
  }
  
  private void checkBinderPermission() {
    int i = Binder.getCallingUid();
    if (this.mContext.checkCallingOrSelfPermission("android.permission.ACCOUNT_MANAGER") == 0)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("caller uid ");
    stringBuilder.append(i);
    stringBuilder.append(" lacks ");
    stringBuilder.append("android.permission.ACCOUNT_MANAGER");
    throw new SecurityException(stringBuilder.toString());
  }
  
  private void handleException(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString1, String paramString2, Exception paramException) throws RemoteException {
    if (paramException instanceof NetworkErrorException) {
      if (Log.isLoggable("AccountAuthenticator", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString1);
        stringBuilder.append("(");
        stringBuilder.append(paramString2);
        stringBuilder.append(")");
        Log.v("AccountAuthenticator", stringBuilder.toString(), paramException);
      } 
      paramIAccountAuthenticatorResponse.onError(3, paramException.getMessage());
    } else {
      StringBuilder stringBuilder;
      if (paramException instanceof UnsupportedOperationException) {
        if (Log.isLoggable("AccountAuthenticator", 2)) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(paramString1);
          stringBuilder1.append("(");
          stringBuilder1.append(paramString2);
          stringBuilder1.append(")");
          Log.v("AccountAuthenticator", stringBuilder1.toString(), paramException);
        } 
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString1);
        stringBuilder.append(" not supported");
        paramIAccountAuthenticatorResponse.onError(6, stringBuilder.toString());
      } else if (paramException instanceof IllegalArgumentException) {
        if (Log.isLoggable("AccountAuthenticator", 2)) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(paramString1);
          stringBuilder1.append("(");
          stringBuilder1.append((String)stringBuilder);
          stringBuilder1.append(")");
          Log.v("AccountAuthenticator", stringBuilder1.toString(), paramException);
        } 
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString1);
        stringBuilder.append(" not supported");
        paramIAccountAuthenticatorResponse.onError(7, stringBuilder.toString());
      } else {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString1);
        stringBuilder1.append("(");
        stringBuilder1.append((String)stringBuilder);
        stringBuilder1.append(")");
        Log.w("AccountAuthenticator", stringBuilder1.toString(), paramException);
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString1);
        stringBuilder.append(" failed");
        paramIAccountAuthenticatorResponse.onError(1, stringBuilder.toString());
      } 
    } 
  }
  
  public abstract Bundle addAccount(AccountAuthenticatorResponse paramAccountAuthenticatorResponse, String paramString1, String paramString2, String[] paramArrayOfString, Bundle paramBundle) throws NetworkErrorException;
  
  public Bundle addAccountFromCredentials(final AccountAuthenticatorResponse response, Account paramAccount, Bundle paramBundle) throws NetworkErrorException {
    (new Thread(new Runnable() {
          public void run() {
            Bundle bundle = new Bundle();
            bundle.putBoolean("booleanResult", false);
            response.onResult(bundle);
          }
        })).start();
    return null;
  }
  
  public abstract Bundle confirmCredentials(AccountAuthenticatorResponse paramAccountAuthenticatorResponse, Account paramAccount, Bundle paramBundle) throws NetworkErrorException;
  
  public abstract Bundle editProperties(AccountAuthenticatorResponse paramAccountAuthenticatorResponse, String paramString);
  
  public Bundle finishSession(AccountAuthenticatorResponse paramAccountAuthenticatorResponse, String paramString, Bundle paramBundle) throws NetworkErrorException {
    Bundle bundle1;
    Bundle bundle2;
    if (TextUtils.isEmpty(paramString)) {
      Log.e("AccountAuthenticator", "Account type cannot be empty.");
      bundle1 = new Bundle();
      bundle1.putInt("errorCode", 7);
      bundle1.putString("errorMessage", "accountType cannot be empty.");
      return bundle1;
    } 
    if (paramBundle == null) {
      Log.e("AccountAuthenticator", "Session bundle cannot be null.");
      bundle1 = new Bundle();
      bundle1.putInt("errorCode", 7);
      bundle1.putString("errorMessage", "sessionBundle cannot be null.");
      return bundle1;
    } 
    if (!paramBundle.containsKey("android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE")) {
      bundle2 = new Bundle();
      bundle2.putInt("errorCode", 6);
      bundle2.putString("errorMessage", "Authenticator must override finishSession if startAddAccountSession or startUpdateCredentialsSession is overridden.");
      bundle1.onResult(bundle2);
      return bundle2;
    } 
    String str = paramBundle.getString("android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE");
    Bundle bundle3 = paramBundle.getBundle("android.accounts.AbstractAccountAuthenticator.KEY_OPTIONS");
    String[] arrayOfString = paramBundle.getStringArray("android.accounts.AbstractAccountAuthenticator.KEY_REQUIRED_FEATURES");
    Account account = (Account)paramBundle.getParcelable("android.accounts.AbstractAccountAuthenticator.KEY_ACCOUNT");
    boolean bool = paramBundle.containsKey("android.accounts.AbstractAccountAuthenticator.KEY_ACCOUNT");
    paramBundle = new Bundle(paramBundle);
    paramBundle.remove("android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE");
    paramBundle.remove("android.accounts.AbstractAccountAuthenticator.KEY_REQUIRED_FEATURES");
    paramBundle.remove("android.accounts.AbstractAccountAuthenticator.KEY_OPTIONS");
    paramBundle.remove("android.accounts.AbstractAccountAuthenticator.KEY_ACCOUNT");
    if (bundle3 != null) {
      bundle3.putAll(paramBundle);
      paramBundle = bundle3;
    } 
    return bool ? updateCredentials((AccountAuthenticatorResponse)bundle1, account, str, bundle3) : addAccount((AccountAuthenticatorResponse)bundle1, (String)bundle2, str, arrayOfString, paramBundle);
  }
  
  public Bundle getAccountCredentialsForCloning(final AccountAuthenticatorResponse response, Account paramAccount) throws NetworkErrorException {
    (new Thread(new Runnable() {
          public void run() {
            Bundle bundle = new Bundle();
            bundle.putBoolean("booleanResult", false);
            response.onResult(bundle);
          }
        })).start();
    return null;
  }
  
  public Bundle getAccountRemovalAllowed(AccountAuthenticatorResponse paramAccountAuthenticatorResponse, Account paramAccount) throws NetworkErrorException {
    Bundle bundle = new Bundle();
    bundle.putBoolean("booleanResult", true);
    return bundle;
  }
  
  public abstract Bundle getAuthToken(AccountAuthenticatorResponse paramAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws NetworkErrorException;
  
  public abstract String getAuthTokenLabel(String paramString);
  
  public final IBinder getIBinder() {
    return this.mTransport.asBinder();
  }
  
  public abstract Bundle hasFeatures(AccountAuthenticatorResponse paramAccountAuthenticatorResponse, Account paramAccount, String[] paramArrayOfString) throws NetworkErrorException;
  
  public Bundle isCredentialsUpdateSuggested(AccountAuthenticatorResponse paramAccountAuthenticatorResponse, Account paramAccount, String paramString) throws NetworkErrorException {
    Bundle bundle = new Bundle();
    bundle.putBoolean("booleanResult", false);
    return bundle;
  }
  
  public Bundle startAddAccountSession(final AccountAuthenticatorResponse response, String paramString1, final String authTokenType, final String[] requiredFeatures, final Bundle options) throws NetworkErrorException {
    (new Thread(new Runnable() {
          public void run() {
            Bundle bundle1 = new Bundle();
            bundle1.putString("android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE", authTokenType);
            bundle1.putStringArray("android.accounts.AbstractAccountAuthenticator.KEY_REQUIRED_FEATURES", requiredFeatures);
            bundle1.putBundle("android.accounts.AbstractAccountAuthenticator.KEY_OPTIONS", options);
            Bundle bundle2 = new Bundle();
            bundle2.putBundle("accountSessionBundle", bundle1);
            response.onResult(bundle2);
          }
        })).start();
    return null;
  }
  
  public Bundle startUpdateCredentialsSession(final AccountAuthenticatorResponse response, final Account account, final String authTokenType, final Bundle options) throws NetworkErrorException {
    (new Thread(new Runnable() {
          public void run() {
            Bundle bundle1 = new Bundle();
            bundle1.putString("android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE", authTokenType);
            bundle1.putParcelable("android.accounts.AbstractAccountAuthenticator.KEY_ACCOUNT", account);
            bundle1.putBundle("android.accounts.AbstractAccountAuthenticator.KEY_OPTIONS", options);
            Bundle bundle2 = new Bundle();
            bundle2.putBundle("accountSessionBundle", bundle1);
            response.onResult(bundle2);
          }
        })).start();
    return null;
  }
  
  public abstract Bundle updateCredentials(AccountAuthenticatorResponse paramAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws NetworkErrorException;
  
  private class Transport extends IAccountAuthenticator.Stub {
    private Transport() {}
    
    public void addAccount(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String1, String param1String2, String[] param1ArrayOfString, Bundle param1Bundle) throws RemoteException {
      if (Log.isLoggable("AccountAuthenticator", 2)) {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("addAccount: accountType ");
        stringBuilder.append(param1String1);
        stringBuilder.append(", authTokenType ");
        stringBuilder.append(param1String2);
        stringBuilder.append(", features ");
        if (param1ArrayOfString == null) {
          str = "[]";
        } else {
          str = Arrays.toString((Object[])param1ArrayOfString);
        } 
        stringBuilder.append(str);
        Log.v("AccountAuthenticator", stringBuilder.toString());
      } 
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        Bundle bundle = abstractAccountAuthenticator.addAccount(accountAuthenticatorResponse, param1String1, param1String2, param1ArrayOfString, param1Bundle);
        if (Log.isLoggable("AccountAuthenticator", 2)) {
          if (bundle != null)
            bundle.keySet(); 
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("addAccount: result ");
          stringBuilder.append(AccountManager.sanitizeResult(bundle));
          Log.v("AccountAuthenticator", stringBuilder.toString());
        } 
        if (bundle != null) {
          param1IAccountAuthenticatorResponse.onResult(bundle);
        } else {
          param1IAccountAuthenticatorResponse.onError(5, "null bundle returned");
        } 
      } catch (Exception exception) {
        AbstractAccountAuthenticator.this.handleException(param1IAccountAuthenticatorResponse, "addAccount", param1String1, exception);
      } 
    }
    
    public void addAccountFromCredentials(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, Bundle param1Bundle) throws RemoteException {
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        param1Bundle = abstractAccountAuthenticator.addAccountFromCredentials(accountAuthenticatorResponse, param1Account, param1Bundle);
        if (param1Bundle != null)
          param1IAccountAuthenticatorResponse.onResult(param1Bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator.this.handleException(param1IAccountAuthenticatorResponse, "addAccountFromCredentials", param1Account.toString(), exception);
      } 
    }
    
    public void confirmCredentials(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, Bundle param1Bundle) throws RemoteException {
      if (Log.isLoggable("AccountAuthenticator", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("confirmCredentials: ");
        stringBuilder.append(param1Account);
        Log.v("AccountAuthenticator", stringBuilder.toString());
      } 
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        Bundle bundle = abstractAccountAuthenticator.confirmCredentials(accountAuthenticatorResponse, param1Account, param1Bundle);
        if (Log.isLoggable("AccountAuthenticator", 2)) {
          if (bundle != null)
            bundle.keySet(); 
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("confirmCredentials: result ");
          stringBuilder.append(AccountManager.sanitizeResult(bundle));
          Log.v("AccountAuthenticator", stringBuilder.toString());
        } 
        if (bundle != null)
          param1IAccountAuthenticatorResponse.onResult(bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator.this.handleException(param1IAccountAuthenticatorResponse, "confirmCredentials", param1Account.toString(), exception);
      } 
    }
    
    public void editProperties(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String) throws RemoteException {
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        Bundle bundle = abstractAccountAuthenticator.editProperties(accountAuthenticatorResponse, param1String);
        if (bundle != null)
          param1IAccountAuthenticatorResponse.onResult(bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator.this.handleException(param1IAccountAuthenticatorResponse, "editProperties", param1String, exception);
      } 
    }
    
    public void finishSession(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String, Bundle param1Bundle) throws RemoteException {
      if (Log.isLoggable("AccountAuthenticator", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("finishSession: accountType ");
        stringBuilder.append(param1String);
        Log.v("AccountAuthenticator", stringBuilder.toString());
      } 
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        Bundle bundle = abstractAccountAuthenticator.finishSession(accountAuthenticatorResponse, param1String, param1Bundle);
        if (bundle != null)
          bundle.keySet(); 
        if (Log.isLoggable("AccountAuthenticator", 2)) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("finishSession: result ");
          stringBuilder.append(AccountManager.sanitizeResult(bundle));
          Log.v("AccountAuthenticator", stringBuilder.toString());
        } 
        if (bundle != null)
          param1IAccountAuthenticatorResponse.onResult(bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator.this.handleException(param1IAccountAuthenticatorResponse, "finishSession", param1String, exception);
      } 
    }
    
    public void getAccountCredentialsForCloning(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account) throws RemoteException {
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        Bundle bundle = abstractAccountAuthenticator.getAccountCredentialsForCloning(accountAuthenticatorResponse, param1Account);
        if (bundle != null)
          param1IAccountAuthenticatorResponse.onResult(bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator.this.handleException(param1IAccountAuthenticatorResponse, "getAccountCredentialsForCloning", param1Account.toString(), exception);
      } 
    }
    
    public void getAccountRemovalAllowed(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account) throws RemoteException {
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        Bundle bundle = abstractAccountAuthenticator.getAccountRemovalAllowed(accountAuthenticatorResponse, param1Account);
        if (bundle != null)
          param1IAccountAuthenticatorResponse.onResult(bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator.this.handleException(param1IAccountAuthenticatorResponse, "getAccountRemovalAllowed", param1Account.toString(), exception);
      } 
    }
    
    public void getAuthToken(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String, Bundle param1Bundle) throws RemoteException {
      if (Log.isLoggable("AccountAuthenticator", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getAuthToken: ");
        stringBuilder.append(param1Account);
        stringBuilder.append(", authTokenType ");
        stringBuilder.append(param1String);
        Log.v("AccountAuthenticator", stringBuilder.toString());
      } 
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        Bundle bundle = abstractAccountAuthenticator.getAuthToken(accountAuthenticatorResponse, param1Account, param1String, param1Bundle);
        if (Log.isLoggable("AccountAuthenticator", 2)) {
          if (bundle != null)
            bundle.keySet(); 
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("getAuthToken: result ");
          stringBuilder.append(AccountManager.sanitizeResult(bundle));
          Log.v("AccountAuthenticator", stringBuilder.toString());
        } 
        if (bundle != null)
          param1IAccountAuthenticatorResponse.onResult(bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(param1Account.toString());
        stringBuilder.append(",");
        stringBuilder.append(param1String);
        abstractAccountAuthenticator.handleException(param1IAccountAuthenticatorResponse, "getAuthToken", stringBuilder.toString(), exception);
      } 
    }
    
    public void getAuthTokenLabel(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String) throws RemoteException {
      if (Log.isLoggable("AccountAuthenticator", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getAuthTokenLabel: authTokenType ");
        stringBuilder.append(param1String);
        Log.v("AccountAuthenticator", stringBuilder.toString());
      } 
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        Bundle bundle = new Bundle();
        this();
        bundle.putString("authTokenLabelKey", AbstractAccountAuthenticator.this.getAuthTokenLabel(param1String));
        if (Log.isLoggable("AccountAuthenticator", 2)) {
          bundle.keySet();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("getAuthTokenLabel: result ");
          stringBuilder.append(AccountManager.sanitizeResult(bundle));
          Log.v("AccountAuthenticator", stringBuilder.toString());
        } 
        param1IAccountAuthenticatorResponse.onResult(bundle);
      } catch (Exception exception) {
        AbstractAccountAuthenticator.this.handleException(param1IAccountAuthenticatorResponse, "getAuthTokenLabel", param1String, exception);
      } 
    }
    
    public void hasFeatures(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String[] param1ArrayOfString) throws RemoteException {
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        Bundle bundle = abstractAccountAuthenticator.hasFeatures(accountAuthenticatorResponse, param1Account, param1ArrayOfString);
        if (bundle != null)
          param1IAccountAuthenticatorResponse.onResult(bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator.this.handleException(param1IAccountAuthenticatorResponse, "hasFeatures", param1Account.toString(), exception);
      } 
    }
    
    public void isCredentialsUpdateSuggested(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String) throws RemoteException {
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        Bundle bundle = abstractAccountAuthenticator.isCredentialsUpdateSuggested(accountAuthenticatorResponse, param1Account, param1String);
        if (bundle != null)
          param1IAccountAuthenticatorResponse.onResult(bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator.this.handleException(param1IAccountAuthenticatorResponse, "isCredentialsUpdateSuggested", param1Account.toString(), exception);
      } 
    }
    
    public void startAddAccountSession(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String1, String param1String2, String[] param1ArrayOfString, Bundle param1Bundle) throws RemoteException {
      if (Log.isLoggable("AccountAuthenticator", 2)) {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("startAddAccountSession: accountType ");
        stringBuilder.append(param1String1);
        stringBuilder.append(", authTokenType ");
        stringBuilder.append(param1String2);
        stringBuilder.append(", features ");
        if (param1ArrayOfString == null) {
          str = "[]";
        } else {
          str = Arrays.toString((Object[])param1ArrayOfString);
        } 
        stringBuilder.append(str);
        Log.v("AccountAuthenticator", stringBuilder.toString());
      } 
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        Bundle bundle = abstractAccountAuthenticator.startAddAccountSession(accountAuthenticatorResponse, param1String1, param1String2, param1ArrayOfString, param1Bundle);
        if (Log.isLoggable("AccountAuthenticator", 2)) {
          if (bundle != null)
            bundle.keySet(); 
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("startAddAccountSession: result ");
          stringBuilder.append(AccountManager.sanitizeResult(bundle));
          Log.v("AccountAuthenticator", stringBuilder.toString());
        } 
        if (bundle != null)
          param1IAccountAuthenticatorResponse.onResult(bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator.this.handleException(param1IAccountAuthenticatorResponse, "startAddAccountSession", param1String1, exception);
      } 
    }
    
    public void startUpdateCredentialsSession(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String, Bundle param1Bundle) throws RemoteException {
      if (Log.isLoggable("AccountAuthenticator", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("startUpdateCredentialsSession: ");
        stringBuilder.append(param1Account);
        stringBuilder.append(", authTokenType ");
        stringBuilder.append(param1String);
        Log.v("AccountAuthenticator", stringBuilder.toString());
      } 
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        param1Bundle = abstractAccountAuthenticator.startUpdateCredentialsSession(accountAuthenticatorResponse, param1Account, param1String, param1Bundle);
        if (Log.isLoggable("AccountAuthenticator", 2)) {
          if (param1Bundle != null)
            param1Bundle.keySet(); 
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("startUpdateCredentialsSession: result ");
          stringBuilder.append(AccountManager.sanitizeResult(param1Bundle));
          Log.v("AccountAuthenticator", stringBuilder.toString());
        } 
        if (param1Bundle != null)
          param1IAccountAuthenticatorResponse.onResult(param1Bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(param1Account.toString());
        stringBuilder.append(",");
        stringBuilder.append(param1String);
        abstractAccountAuthenticator.handleException(param1IAccountAuthenticatorResponse, "startUpdateCredentialsSession", stringBuilder.toString(), exception);
      } 
    }
    
    public void updateCredentials(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String, Bundle param1Bundle) throws RemoteException {
      if (Log.isLoggable("AccountAuthenticator", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("updateCredentials: ");
        stringBuilder.append(param1Account);
        stringBuilder.append(", authTokenType ");
        stringBuilder.append(param1String);
        Log.v("AccountAuthenticator", stringBuilder.toString());
      } 
      AbstractAccountAuthenticator.this.checkBinderPermission();
      try {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
        this(param1IAccountAuthenticatorResponse);
        Bundle bundle = abstractAccountAuthenticator.updateCredentials(accountAuthenticatorResponse, param1Account, param1String, param1Bundle);
        if (Log.isLoggable("AccountAuthenticator", 2)) {
          if (bundle != null)
            bundle.keySet(); 
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("updateCredentials: result ");
          stringBuilder.append(AccountManager.sanitizeResult(bundle));
          Log.v("AccountAuthenticator", stringBuilder.toString());
        } 
        if (bundle != null)
          param1IAccountAuthenticatorResponse.onResult(bundle); 
      } catch (Exception exception) {
        AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(param1Account.toString());
        stringBuilder.append(",");
        stringBuilder.append(param1String);
        abstractAccountAuthenticator.handleException(param1IAccountAuthenticatorResponse, "updateCredentials", stringBuilder.toString(), exception);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AbstractAccountAuthenticator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
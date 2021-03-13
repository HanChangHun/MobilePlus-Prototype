package android.accounts;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import java.util.Arrays;

class Transport extends IAccountAuthenticator.Stub {
  private Transport() {}
  
  public void addAccount(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString1, String paramString2, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException {
    if (Log.isLoggable("AccountAuthenticator", 2)) {
      String str;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("addAccount: accountType ");
      stringBuilder.append(paramString1);
      stringBuilder.append(", authTokenType ");
      stringBuilder.append(paramString2);
      stringBuilder.append(", features ");
      if (paramArrayOfString == null) {
        str = "[]";
      } else {
        str = Arrays.toString((Object[])paramArrayOfString);
      } 
      stringBuilder.append(str);
      Log.v("AccountAuthenticator", stringBuilder.toString());
    } 
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      Bundle bundle = abstractAccountAuthenticator.addAccount(accountAuthenticatorResponse, paramString1, paramString2, paramArrayOfString, paramBundle);
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
        paramIAccountAuthenticatorResponse.onResult(bundle);
      } else {
        paramIAccountAuthenticatorResponse.onError(5, "null bundle returned");
      } 
    } catch (Exception exception) {
      AbstractAccountAuthenticator.access$100(AbstractAccountAuthenticator.this, paramIAccountAuthenticatorResponse, "addAccount", paramString1, exception);
    } 
  }
  
  public void addAccountFromCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, Bundle paramBundle) throws RemoteException {
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      paramBundle = abstractAccountAuthenticator.addAccountFromCredentials(accountAuthenticatorResponse, paramAccount, paramBundle);
      if (paramBundle != null)
        paramIAccountAuthenticatorResponse.onResult(paramBundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator.access$100(AbstractAccountAuthenticator.this, paramIAccountAuthenticatorResponse, "addAccountFromCredentials", paramAccount.toString(), exception);
    } 
  }
  
  public void confirmCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, Bundle paramBundle) throws RemoteException {
    if (Log.isLoggable("AccountAuthenticator", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("confirmCredentials: ");
      stringBuilder.append(paramAccount);
      Log.v("AccountAuthenticator", stringBuilder.toString());
    } 
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      Bundle bundle = abstractAccountAuthenticator.confirmCredentials(accountAuthenticatorResponse, paramAccount, paramBundle);
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
        paramIAccountAuthenticatorResponse.onResult(bundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator.access$100(AbstractAccountAuthenticator.this, paramIAccountAuthenticatorResponse, "confirmCredentials", paramAccount.toString(), exception);
    } 
  }
  
  public void editProperties(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString) throws RemoteException {
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      Bundle bundle = abstractAccountAuthenticator.editProperties(accountAuthenticatorResponse, paramString);
      if (bundle != null)
        paramIAccountAuthenticatorResponse.onResult(bundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator.access$100(AbstractAccountAuthenticator.this, paramIAccountAuthenticatorResponse, "editProperties", paramString, exception);
    } 
  }
  
  public void finishSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString, Bundle paramBundle) throws RemoteException {
    if (Log.isLoggable("AccountAuthenticator", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("finishSession: accountType ");
      stringBuilder.append(paramString);
      Log.v("AccountAuthenticator", stringBuilder.toString());
    } 
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      Bundle bundle = abstractAccountAuthenticator.finishSession(accountAuthenticatorResponse, paramString, paramBundle);
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
        paramIAccountAuthenticatorResponse.onResult(bundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator.access$100(AbstractAccountAuthenticator.this, paramIAccountAuthenticatorResponse, "finishSession", paramString, exception);
    } 
  }
  
  public void getAccountCredentialsForCloning(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount) throws RemoteException {
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      Bundle bundle = abstractAccountAuthenticator.getAccountCredentialsForCloning(accountAuthenticatorResponse, paramAccount);
      if (bundle != null)
        paramIAccountAuthenticatorResponse.onResult(bundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator.access$100(AbstractAccountAuthenticator.this, paramIAccountAuthenticatorResponse, "getAccountCredentialsForCloning", paramAccount.toString(), exception);
    } 
  }
  
  public void getAccountRemovalAllowed(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount) throws RemoteException {
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      Bundle bundle = abstractAccountAuthenticator.getAccountRemovalAllowed(accountAuthenticatorResponse, paramAccount);
      if (bundle != null)
        paramIAccountAuthenticatorResponse.onResult(bundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator.access$100(AbstractAccountAuthenticator.this, paramIAccountAuthenticatorResponse, "getAccountRemovalAllowed", paramAccount.toString(), exception);
    } 
  }
  
  public void getAuthToken(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {
    if (Log.isLoggable("AccountAuthenticator", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getAuthToken: ");
      stringBuilder.append(paramAccount);
      stringBuilder.append(", authTokenType ");
      stringBuilder.append(paramString);
      Log.v("AccountAuthenticator", stringBuilder.toString());
    } 
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      Bundle bundle = abstractAccountAuthenticator.getAuthToken(accountAuthenticatorResponse, paramAccount, paramString, paramBundle);
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
        paramIAccountAuthenticatorResponse.onResult(bundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramAccount.toString());
      stringBuilder.append(",");
      stringBuilder.append(paramString);
      AbstractAccountAuthenticator.access$100(abstractAccountAuthenticator, paramIAccountAuthenticatorResponse, "getAuthToken", stringBuilder.toString(), exception);
    } 
  }
  
  public void getAuthTokenLabel(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString) throws RemoteException {
    if (Log.isLoggable("AccountAuthenticator", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getAuthTokenLabel: authTokenType ");
      stringBuilder.append(paramString);
      Log.v("AccountAuthenticator", stringBuilder.toString());
    } 
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      Bundle bundle = new Bundle();
      this();
      bundle.putString("authTokenLabelKey", AbstractAccountAuthenticator.this.getAuthTokenLabel(paramString));
      if (Log.isLoggable("AccountAuthenticator", 2)) {
        bundle.keySet();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("getAuthTokenLabel: result ");
        stringBuilder.append(AccountManager.sanitizeResult(bundle));
        Log.v("AccountAuthenticator", stringBuilder.toString());
      } 
      paramIAccountAuthenticatorResponse.onResult(bundle);
    } catch (Exception exception) {
      AbstractAccountAuthenticator.access$100(AbstractAccountAuthenticator.this, paramIAccountAuthenticatorResponse, "getAuthTokenLabel", paramString, exception);
    } 
  }
  
  public void hasFeatures(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String[] paramArrayOfString) throws RemoteException {
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      Bundle bundle = abstractAccountAuthenticator.hasFeatures(accountAuthenticatorResponse, paramAccount, paramArrayOfString);
      if (bundle != null)
        paramIAccountAuthenticatorResponse.onResult(bundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator.access$100(AbstractAccountAuthenticator.this, paramIAccountAuthenticatorResponse, "hasFeatures", paramAccount.toString(), exception);
    } 
  }
  
  public void isCredentialsUpdateSuggested(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString) throws RemoteException {
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      Bundle bundle = abstractAccountAuthenticator.isCredentialsUpdateSuggested(accountAuthenticatorResponse, paramAccount, paramString);
      if (bundle != null)
        paramIAccountAuthenticatorResponse.onResult(bundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator.access$100(AbstractAccountAuthenticator.this, paramIAccountAuthenticatorResponse, "isCredentialsUpdateSuggested", paramAccount.toString(), exception);
    } 
  }
  
  public void startAddAccountSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString1, String paramString2, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException {
    if (Log.isLoggable("AccountAuthenticator", 2)) {
      String str;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("startAddAccountSession: accountType ");
      stringBuilder.append(paramString1);
      stringBuilder.append(", authTokenType ");
      stringBuilder.append(paramString2);
      stringBuilder.append(", features ");
      if (paramArrayOfString == null) {
        str = "[]";
      } else {
        str = Arrays.toString((Object[])paramArrayOfString);
      } 
      stringBuilder.append(str);
      Log.v("AccountAuthenticator", stringBuilder.toString());
    } 
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      Bundle bundle = abstractAccountAuthenticator.startAddAccountSession(accountAuthenticatorResponse, paramString1, paramString2, paramArrayOfString, paramBundle);
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
        paramIAccountAuthenticatorResponse.onResult(bundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator.access$100(AbstractAccountAuthenticator.this, paramIAccountAuthenticatorResponse, "startAddAccountSession", paramString1, exception);
    } 
  }
  
  public void startUpdateCredentialsSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {
    if (Log.isLoggable("AccountAuthenticator", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("startUpdateCredentialsSession: ");
      stringBuilder.append(paramAccount);
      stringBuilder.append(", authTokenType ");
      stringBuilder.append(paramString);
      Log.v("AccountAuthenticator", stringBuilder.toString());
    } 
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      paramBundle = abstractAccountAuthenticator.startUpdateCredentialsSession(accountAuthenticatorResponse, paramAccount, paramString, paramBundle);
      if (Log.isLoggable("AccountAuthenticator", 2)) {
        if (paramBundle != null)
          paramBundle.keySet(); 
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("startUpdateCredentialsSession: result ");
        stringBuilder.append(AccountManager.sanitizeResult(paramBundle));
        Log.v("AccountAuthenticator", stringBuilder.toString());
      } 
      if (paramBundle != null)
        paramIAccountAuthenticatorResponse.onResult(paramBundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramAccount.toString());
      stringBuilder.append(",");
      stringBuilder.append(paramString);
      AbstractAccountAuthenticator.access$100(abstractAccountAuthenticator, paramIAccountAuthenticatorResponse, "startUpdateCredentialsSession", stringBuilder.toString(), exception);
    } 
  }
  
  public void updateCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {
    if (Log.isLoggable("AccountAuthenticator", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("updateCredentials: ");
      stringBuilder.append(paramAccount);
      stringBuilder.append(", authTokenType ");
      stringBuilder.append(paramString);
      Log.v("AccountAuthenticator", stringBuilder.toString());
    } 
    AbstractAccountAuthenticator.access$000(AbstractAccountAuthenticator.this);
    try {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      AccountAuthenticatorResponse accountAuthenticatorResponse = new AccountAuthenticatorResponse();
      this(paramIAccountAuthenticatorResponse);
      Bundle bundle = abstractAccountAuthenticator.updateCredentials(accountAuthenticatorResponse, paramAccount, paramString, paramBundle);
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
        paramIAccountAuthenticatorResponse.onResult(bundle); 
    } catch (Exception exception) {
      AbstractAccountAuthenticator abstractAccountAuthenticator = AbstractAccountAuthenticator.this;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramAccount.toString());
      stringBuilder.append(",");
      stringBuilder.append(paramString);
      AbstractAccountAuthenticator.access$100(abstractAccountAuthenticator, paramIAccountAuthenticatorResponse, "updateCredentials", stringBuilder.toString(), exception);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AbstractAccountAuthenticator$Transport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
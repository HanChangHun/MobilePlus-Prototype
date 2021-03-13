package android.accounts;

import android.content.IntentSender;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.Map;

public class Default implements IAccountManager {
  public boolean accountAuthenticated(Account paramAccount) throws RemoteException {
    return false;
  }
  
  public void addAccount(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean, Bundle paramBundle) throws RemoteException {}
  
  public void addAccountAsUser(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean, Bundle paramBundle, int paramInt) throws RemoteException {}
  
  public boolean addAccountExplicitly(Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {
    return false;
  }
  
  public boolean addAccountExplicitlyWithVisibility(Account paramAccount, String paramString, Bundle paramBundle, Map paramMap) throws RemoteException {
    return false;
  }
  
  public void addSharedAccountsFromParentUser(int paramInt1, int paramInt2, String paramString) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void clearPassword(Account paramAccount) throws RemoteException {}
  
  public void confirmCredentialsAsUser(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, Bundle paramBundle, boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public void copyAccountToUser(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, int paramInt1, int paramInt2) throws RemoteException {}
  
  public IntentSender createRequestAccountAccessIntentSenderAsUser(Account paramAccount, String paramString, UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public void editProperties(IAccountManagerResponse paramIAccountManagerResponse, String paramString, boolean paramBoolean) throws RemoteException {}
  
  public void finishSessionAsUser(IAccountManagerResponse paramIAccountManagerResponse, Bundle paramBundle1, boolean paramBoolean, Bundle paramBundle2, int paramInt) throws RemoteException {}
  
  public void getAccountByTypeAndFeatures(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String[] paramArrayOfString, String paramString2) throws RemoteException {}
  
  public int getAccountVisibility(Account paramAccount, String paramString) throws RemoteException {
    return 0;
  }
  
  public Map getAccountsAndVisibilityForPackage(String paramString1, String paramString2) throws RemoteException {
    return null;
  }
  
  public Account[] getAccountsAsUser(String paramString1, int paramInt, String paramString2) throws RemoteException {
    return null;
  }
  
  public void getAccountsByFeatures(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String[] paramArrayOfString, String paramString2) throws RemoteException {}
  
  public Account[] getAccountsByTypeForPackage(String paramString1, String paramString2, String paramString3) throws RemoteException {
    return null;
  }
  
  public Account[] getAccountsForPackage(String paramString1, int paramInt, String paramString2) throws RemoteException {
    return null;
  }
  
  public void getAuthToken(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String paramString, boolean paramBoolean1, boolean paramBoolean2, Bundle paramBundle) throws RemoteException {}
  
  public void getAuthTokenLabel(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String paramString2) throws RemoteException {}
  
  public AuthenticatorDescription[] getAuthenticatorTypes(int paramInt) throws RemoteException {
    return null;
  }
  
  public Map getPackagesAndVisibilityForAccount(Account paramAccount) throws RemoteException {
    return null;
  }
  
  public String getPassword(Account paramAccount) throws RemoteException {
    return null;
  }
  
  public String getPreviousName(Account paramAccount) throws RemoteException {
    return null;
  }
  
  public String getUserData(Account paramAccount, String paramString) throws RemoteException {
    return null;
  }
  
  public boolean hasAccountAccess(Account paramAccount, String paramString, UserHandle paramUserHandle) throws RemoteException {
    return false;
  }
  
  public void hasFeatures(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String[] paramArrayOfString, String paramString) throws RemoteException {}
  
  public void invalidateAuthToken(String paramString1, String paramString2) throws RemoteException {}
  
  public void isCredentialsUpdateSuggested(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String paramString) throws RemoteException {}
  
  public void onAccountAccessed(String paramString) throws RemoteException {}
  
  public String peekAuthToken(Account paramAccount, String paramString) throws RemoteException {
    return null;
  }
  
  public void registerAccountListener(String[] paramArrayOfString, String paramString) throws RemoteException {}
  
  public void removeAccountAsUser(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public boolean removeAccountExplicitly(Account paramAccount) throws RemoteException {
    return false;
  }
  
  public void renameAccount(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String paramString) throws RemoteException {}
  
  public boolean setAccountVisibility(Account paramAccount, String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public void setAuthToken(Account paramAccount, String paramString1, String paramString2) throws RemoteException {}
  
  public void setPassword(Account paramAccount, String paramString) throws RemoteException {}
  
  public void setUserData(Account paramAccount, String paramString1, String paramString2) throws RemoteException {}
  
  public boolean someUserHasAccount(Account paramAccount) throws RemoteException {
    return false;
  }
  
  public void startAddAccountSession(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean, Bundle paramBundle) throws RemoteException {}
  
  public void startUpdateCredentialsSession(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String paramString, boolean paramBoolean, Bundle paramBundle) throws RemoteException {}
  
  public void unregisterAccountListener(String[] paramArrayOfString, String paramString) throws RemoteException {}
  
  public void updateAppPermission(Account paramAccount, String paramString, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void updateCredentials(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String paramString, boolean paramBoolean, Bundle paramBundle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
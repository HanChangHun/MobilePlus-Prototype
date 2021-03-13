package android.accounts;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IAccountAuthenticator {
  public void addAccount(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString1, String paramString2, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException {}
  
  public void addAccountFromCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, Bundle paramBundle) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void confirmCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, Bundle paramBundle) throws RemoteException {}
  
  public void editProperties(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString) throws RemoteException {}
  
  public void finishSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString, Bundle paramBundle) throws RemoteException {}
  
  public void getAccountCredentialsForCloning(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount) throws RemoteException {}
  
  public void getAccountRemovalAllowed(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount) throws RemoteException {}
  
  public void getAuthToken(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {}
  
  public void getAuthTokenLabel(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString) throws RemoteException {}
  
  public void hasFeatures(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String[] paramArrayOfString) throws RemoteException {}
  
  public void isCredentialsUpdateSuggested(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString) throws RemoteException {}
  
  public void startAddAccountSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString1, String paramString2, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException {}
  
  public void startUpdateCredentialsSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {}
  
  public void updateCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountAuthenticator$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
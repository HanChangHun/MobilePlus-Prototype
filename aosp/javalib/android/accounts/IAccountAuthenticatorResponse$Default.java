package android.accounts;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IAccountAuthenticatorResponse {
  public IBinder asBinder() {
    return null;
  }
  
  public void onError(int paramInt, String paramString) throws RemoteException {}
  
  public void onRequestContinued() throws RemoteException {}
  
  public void onResult(Bundle paramBundle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountAuthenticatorResponse$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
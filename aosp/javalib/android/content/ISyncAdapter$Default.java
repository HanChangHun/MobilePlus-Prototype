package android.content;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ISyncAdapter {
  public IBinder asBinder() {
    return null;
  }
  
  public void cancelSync(ISyncContext paramISyncContext) throws RemoteException {}
  
  public void onUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback paramISyncAdapterUnsyncableAccountCallback) throws RemoteException {}
  
  public void startSync(ISyncContext paramISyncContext, String paramString, Account paramAccount, Bundle paramBundle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncAdapter$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
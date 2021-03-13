package android.accounts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import java.util.Map;

class null extends BroadcastReceiver {
  public void onReceive(Context paramContext, Intent paramIntent) {
    null = AccountManager.this.getAccounts();
    synchronized (AccountManager.access$200(AccountManager.this)) {
      for (Map.Entry entry : AccountManager.access$200(AccountManager.this).entrySet())
        AccountManager.access$1700(AccountManager.this, (Handler)entry.getValue(), (OnAccountsUpdateListener)entry.getKey(), null); 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$18.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
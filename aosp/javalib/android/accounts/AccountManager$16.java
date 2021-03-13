package android.accounts;

import android.database.SQLException;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

class null implements Runnable {
  public void run() {
    HashMap hashMap = AccountManager.access$200(AccountManager.this);
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/HashMap}, name=null} */
    try {
      if (AccountManager.access$200(AccountManager.this).containsKey(listener)) {
        Set set = (Set)AccountManager.access$300(AccountManager.this).get(listener);
        if (set != null) {
          ArrayList<Account> arrayList = new ArrayList();
          this();
          for (Account account : accountsCopy) {
            if (set.contains(account.type))
              arrayList.add(account); 
          } 
          listener.onAccountsUpdated(arrayList.<Account>toArray(new Account[arrayList.size()]));
        } else {
          listener.onAccountsUpdated(accountsCopy);
        } 
      } 
    } catch (SQLException sQLException) {
      Log.e("AccountManager", "Can't update accounts", (Throwable)sQLException);
    } finally {
      Exception exception;
    } 
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/HashMap}, name=null} */
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$16.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
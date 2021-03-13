package android.accounts;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;

class Response extends IAccountManagerResponse.Stub {
  private Response() {}
  
  public void onError(int paramInt, String paramString) {
    if (paramInt == 4 || paramInt == 100 || paramInt == 101) {
      AccountManager.AmsTask.this.cancel(true);
      return;
    } 
    AccountManager.AmsTask amsTask = AccountManager.AmsTask.this;
    AccountManager.AmsTask.access$800(amsTask, AccountManager.access$700(amsTask.this$0, paramInt, paramString));
  }
  
  public void onResult(Bundle paramBundle) {
    if (paramBundle == null) {
      onError(5, "null bundle returned");
      return;
    } 
    Intent intent = (Intent)paramBundle.getParcelable("intent");
    if (intent != null && AccountManager.AmsTask.this.mActivity != null) {
      AccountManager.AmsTask.this.mActivity.startActivity(intent);
    } else if (paramBundle.getBoolean("retry")) {
      try {
        AccountManager.AmsTask.this.doWork();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } else {
      AccountManager.AmsTask.this.set((Bundle)remoteException);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$AmsTask$Response.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
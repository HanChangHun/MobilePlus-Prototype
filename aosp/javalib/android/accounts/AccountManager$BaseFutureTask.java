package android.accounts;

import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

abstract class BaseFutureTask<T> extends FutureTask<T> {
  final Handler mHandler;
  
  public final IAccountManagerResponse mResponse;
  
  public BaseFutureTask(Handler paramHandler) {
    super(new Callable<T>(paramAccountManager) {
          public T call() throws Exception {
            throw new IllegalStateException("this should never be called");
          }
        });
    this.mHandler = paramHandler;
    this.mResponse = new Response();
  }
  
  public abstract T bundleToResult(Bundle paramBundle) throws AuthenticatorException;
  
  public abstract void doWork() throws RemoteException;
  
  protected void postRunnableToHandler(Runnable paramRunnable) {
    Handler handler1 = this.mHandler;
    Handler handler2 = handler1;
    if (handler1 == null)
      handler2 = AccountManager.access$900(AccountManager.this); 
    handler2.post(paramRunnable);
  }
  
  protected void startTask() {
    try {
      doWork();
    } catch (RemoteException remoteException) {
      setException((Throwable)remoteException);
    } 
  }
  
  protected class Response extends IAccountManagerResponse.Stub {
    public void onError(int param2Int, String param2String) {
      if (param2Int == 4 || param2Int == 100 || param2Int == 101) {
        AccountManager.BaseFutureTask.this.cancel(true);
        return;
      } 
      AccountManager.BaseFutureTask baseFutureTask = AccountManager.BaseFutureTask.this;
      baseFutureTask.setException(AccountManager.access$700(baseFutureTask.this$0, param2Int, param2String));
    }
    
    public void onResult(Bundle param2Bundle) {
      try {
        param2Bundle = AccountManager.BaseFutureTask.this.bundleToResult(param2Bundle);
        if (param2Bundle == null)
          return; 
        AccountManager.BaseFutureTask.this.set((T)param2Bundle);
        return;
      } catch (ClassCastException classCastException) {
      
      } catch (AuthenticatorException authenticatorException) {}
      onError(5, "no result in response");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$BaseFutureTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
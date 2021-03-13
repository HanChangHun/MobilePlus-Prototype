package android.accounts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

abstract class AmsTask extends FutureTask<Bundle> implements AccountManagerFuture<Bundle> {
  final Activity mActivity;
  
  final AccountManagerCallback<Bundle> mCallback;
  
  final Handler mHandler;
  
  final IAccountManagerResponse mResponse;
  
  public AmsTask(Activity paramActivity, Handler paramHandler, AccountManagerCallback<Bundle> paramAccountManagerCallback) {
    super(new Callable<Bundle>(paramAccountManager) {
          public Bundle call() throws Exception {
            throw new IllegalStateException("this should never be called");
          }
        });
    this.mHandler = paramHandler;
    this.mCallback = paramAccountManagerCallback;
    this.mActivity = paramActivity;
    this.mResponse = new Response();
  }
  
  private Bundle internalGetResult(Long paramLong, TimeUnit paramTimeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
    Bundle bundle;
    AuthenticatorException authenticatorException;
    if (!isDone())
      AccountManager.access$500(AccountManager.this); 
    if (paramLong == null) {
      try {
        bundle = get();
        cancel(true);
        return bundle;
      } catch (CancellationException cancellationException) {
        OperationCanceledException operationCanceledException = new OperationCanceledException();
        this();
        throw operationCanceledException;
      } catch (TimeoutException timeoutException) {
        cancel(true);
        throw new OperationCanceledException();
      } catch (InterruptedException interruptedException) {
        cancel(true);
        throw new OperationCanceledException();
      } catch (ExecutionException executionException) {
        Throwable throwable = executionException.getCause();
        if (!(throwable instanceof IOException)) {
          if (!(throwable instanceof UnsupportedOperationException)) {
            if (!(throwable instanceof AuthenticatorException)) {
              if (!(throwable instanceof RuntimeException)) {
                if (throwable instanceof Error)
                  throw (Error)throwable; 
                IllegalStateException illegalStateException = new IllegalStateException();
                this(throwable);
                throw illegalStateException;
              } 
              throw (RuntimeException)throwable;
            } 
            throw (AuthenticatorException)throwable;
          } 
          authenticatorException = new AuthenticatorException();
          this(throwable);
          throw authenticatorException;
        } 
        throw (IOException)throwable;
      } finally {}
    } else {
      bundle = get(paramLong.longValue(), (TimeUnit)authenticatorException);
      cancel(true);
      return bundle;
    } 
    cancel(true);
    throw bundle;
  }
  
  public abstract void doWork() throws RemoteException;
  
  protected void done() {
    AccountManagerCallback<Bundle> accountManagerCallback = this.mCallback;
    if (accountManagerCallback != null)
      AccountManager.access$600(AccountManager.this, this.mHandler, accountManagerCallback, this); 
  }
  
  public Bundle getResult() throws OperationCanceledException, IOException, AuthenticatorException {
    return internalGetResult((Long)null, (TimeUnit)null);
  }
  
  public Bundle getResult(long paramLong, TimeUnit paramTimeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
    return internalGetResult(Long.valueOf(paramLong), paramTimeUnit);
  }
  
  protected void set(Bundle paramBundle) {
    if (paramBundle == null)
      Log.e("AccountManager", "the bundle must not be null", new Exception()); 
    super.set(paramBundle);
  }
  
  public final AccountManagerFuture<Bundle> start() {
    try {
      doWork();
    } catch (RemoteException remoteException) {
      setException((Throwable)remoteException);
    } 
    return this;
  }
  
  private class Response extends IAccountManagerResponse.Stub {
    private Response() {}
    
    public void onError(int param2Int, String param2String) {
      if (param2Int == 4 || param2Int == 100 || param2Int == 101) {
        AccountManager.AmsTask.this.cancel(true);
        return;
      } 
      AccountManager.AmsTask amsTask = AccountManager.AmsTask.this;
      amsTask.setException(AccountManager.access$700(amsTask.this$0, param2Int, param2String));
    }
    
    public void onResult(Bundle param2Bundle) {
      if (param2Bundle == null) {
        onError(5, "null bundle returned");
        return;
      } 
      Intent intent = (Intent)param2Bundle.getParcelable("intent");
      if (intent != null && AccountManager.AmsTask.this.mActivity != null) {
        AccountManager.AmsTask.this.mActivity.startActivity(intent);
      } else if (param2Bundle.getBoolean("retry")) {
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
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$AmsTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.accounts;

import android.os.Handler;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

abstract class Future2Task<T> extends AccountManager.BaseFutureTask<T> implements AccountManagerFuture<T> {
  final AccountManagerCallback<T> mCallback;
  
  public Future2Task(Handler paramHandler, AccountManagerCallback<T> paramAccountManagerCallback) {
    super(paramHandler);
    this.mCallback = paramAccountManagerCallback;
  }
  
  private T internalGetResult(Long paramLong, TimeUnit paramTimeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
    AuthenticatorException authenticatorException;
    if (!isDone())
      AccountManager.access$500(AccountManager.this); 
    if (paramLong == null) {
      try {
        paramLong = (Long)get();
        return (T)paramLong;
      } catch (InterruptedException interruptedException) {
      
      } catch (TimeoutException timeoutException) {
      
      } catch (CancellationException cancellationException) {
      
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
      } finally {
        cancel(true);
      } 
    } else {
      paramLong = (Long)get(paramLong.longValue(), (TimeUnit)authenticatorException);
      cancel(true);
      return (T)paramLong;
    } 
    cancel(true);
    throw new OperationCanceledException();
  }
  
  protected void done() {
    if (this.mCallback != null)
      postRunnableToHandler(new Runnable() {
            public void run() {
              AccountManager.Future2Task.this.mCallback.run(AccountManager.Future2Task.this);
            }
          }); 
  }
  
  public T getResult() throws OperationCanceledException, IOException, AuthenticatorException {
    return internalGetResult((Long)null, (TimeUnit)null);
  }
  
  public T getResult(long paramLong, TimeUnit paramTimeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
    return internalGetResult(Long.valueOf(paramLong), paramTimeUnit);
  }
  
  public Future2Task<T> start() {
    startTask();
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$Future2Task.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.accounts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public interface AccountManagerFuture<V> {
  boolean cancel(boolean paramBoolean);
  
  V getResult() throws OperationCanceledException, IOException, AuthenticatorException;
  
  V getResult(long paramLong, TimeUnit paramTimeUnit) throws OperationCanceledException, IOException, AuthenticatorException;
  
  boolean isCancelled();
  
  boolean isDone();
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManagerFuture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
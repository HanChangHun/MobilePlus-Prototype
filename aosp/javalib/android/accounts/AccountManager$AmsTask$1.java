package android.accounts;

import android.os.Bundle;
import java.util.concurrent.Callable;

class null implements Callable<Bundle> {
  public Bundle call() throws Exception {
    throw new IllegalStateException("this should never be called");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager$AmsTask$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
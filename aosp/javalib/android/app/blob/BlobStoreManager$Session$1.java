package android.app.blob;

import com.android.internal.util.function.pooled.PooledLambda;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class null extends IBlobCommitCallback.Stub {
  public void onResult(int paramInt) {
    executor.execute((Runnable)PooledLambda.obtainRunnable((BiConsumer)_$$Lambda$2oD6p7V9cUAK7HNu017eF9iL6ZI.INSTANCE, resultCallback, Integer.valueOf(paramInt)));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/BlobStoreManager$Session$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.annotation.SystemApi;
import android.os.Handler;
import android.os.HandlerExecutor;
import java.util.concurrent.Executor;

@SystemApi
@Deprecated
public abstract class AppOpsCollector extends AppOpsManager.OnOpNotedCallback {
  public Executor getAsyncNotedExecutor() {
    return (Executor)new HandlerExecutor(Handler.getMain());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$AppOpsCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
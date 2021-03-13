package android.app;

import android.os.Binder;
import com.android.internal.app.IAppOpsAsyncNotedCallback;
import java.util.Objects;
import java.util.concurrent.Executor;

class null extends IAppOpsAsyncNotedCallback.Stub {
  public void opNoted(AsyncNotedAppOp paramAsyncNotedAppOp) {
    Objects.requireNonNull(paramAsyncNotedAppOp);
    long l = Binder.clearCallingIdentity();
    try {
      Executor executor = AppOpsManager.OnOpNotedCallback.this.getAsyncNotedExecutor();
      _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY = new _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY();
      this(this, paramAsyncNotedAppOp);
      executor.execute(_$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$OnOpNotedCallback$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
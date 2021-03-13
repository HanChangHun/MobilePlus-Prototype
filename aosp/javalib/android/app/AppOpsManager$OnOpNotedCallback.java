package android.app;

import android.os.Binder;
import com.android.internal.app.IAppOpsAsyncNotedCallback;
import java.util.Objects;
import java.util.concurrent.Executor;

public abstract class OnOpNotedCallback {
  private final IAppOpsAsyncNotedCallback mAsyncCb = (IAppOpsAsyncNotedCallback)new IAppOpsAsyncNotedCallback.Stub() {
      public void opNoted(AsyncNotedAppOp param2AsyncNotedAppOp) {
        Objects.requireNonNull(param2AsyncNotedAppOp);
        long l = Binder.clearCallingIdentity();
        try {
          Executor executor = AppOpsManager.OnOpNotedCallback.this.getAsyncNotedExecutor();
          _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY = new _$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY();
          this(this, param2AsyncNotedAppOp);
          executor.execute(_$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp_GotIWwSMrFjDEmAcFNvnPRkY);
          return;
        } finally {
          Binder.restoreCallingIdentity(l);
        } 
      }
    };
  
  private Executor mAsyncExecutor;
  
  protected Executor getAsyncNotedExecutor() {
    return this.mAsyncExecutor;
  }
  
  public abstract void onAsyncNoted(AsyncNotedAppOp paramAsyncNotedAppOp);
  
  public abstract void onNoted(SyncNotedAppOp paramSyncNotedAppOp);
  
  public abstract void onSelfNoted(SyncNotedAppOp paramSyncNotedAppOp);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$OnOpNotedCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
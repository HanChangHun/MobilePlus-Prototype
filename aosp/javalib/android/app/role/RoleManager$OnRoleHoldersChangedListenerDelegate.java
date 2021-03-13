package android.app.role;

import android.os.Binder;
import android.os.UserHandle;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.concurrent.Executor;

class OnRoleHoldersChangedListenerDelegate extends IOnRoleHoldersChangedListener.Stub {
  private final Executor mExecutor;
  
  private final OnRoleHoldersChangedListener mListener;
  
  OnRoleHoldersChangedListenerDelegate(Executor paramExecutor, OnRoleHoldersChangedListener paramOnRoleHoldersChangedListener) {
    this.mExecutor = paramExecutor;
    this.mListener = paramOnRoleHoldersChangedListener;
  }
  
  public void onRoleHoldersChanged(String paramString, int paramInt) {
    long l = Binder.clearCallingIdentity();
    try {
      this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((TriConsumer)_$$Lambda$o94o2jK_ei_IVw_3oY_QJ49zpAA.INSTANCE, this.mListener, paramString, UserHandle.of(paramInt)));
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/RoleManager$OnRoleHoldersChangedListenerDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
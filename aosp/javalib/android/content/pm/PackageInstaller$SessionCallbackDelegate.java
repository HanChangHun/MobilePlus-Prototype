package android.content.pm;

import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;

class SessionCallbackDelegate extends IPackageInstallerCallback.Stub {
  private static final int MSG_SESSION_ACTIVE_CHANGED = 3;
  
  private static final int MSG_SESSION_BADGING_CHANGED = 2;
  
  private static final int MSG_SESSION_CREATED = 1;
  
  private static final int MSG_SESSION_FINISHED = 5;
  
  private static final int MSG_SESSION_PROGRESS_CHANGED = 4;
  
  final PackageInstaller.SessionCallback mCallback;
  
  final Executor mExecutor;
  
  SessionCallbackDelegate(PackageInstaller.SessionCallback paramSessionCallback, Executor paramExecutor) {
    this.mCallback = paramSessionCallback;
    this.mExecutor = paramExecutor;
  }
  
  public void onSessionActiveChanged(int paramInt, boolean paramBoolean) {
    this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((TriConsumer)_$$Lambda$T1UQAuePWRRmVQ1KzTyMAktZUPM.INSTANCE, this.mCallback, Integer.valueOf(paramInt), Boolean.valueOf(paramBoolean)).recycleOnUse());
  }
  
  public void onSessionBadgingChanged(int paramInt) {
    this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((BiConsumer)_$$Lambda$B12dZLpdwpXn89QSesmkaZjD72Q.INSTANCE, this.mCallback, Integer.valueOf(paramInt)).recycleOnUse());
  }
  
  public void onSessionCreated(int paramInt) {
    this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((BiConsumer)_$$Lambda$ciir_QAmv6RwJro4I58t77dPnxU.INSTANCE, this.mCallback, Integer.valueOf(paramInt)).recycleOnUse());
  }
  
  public void onSessionFinished(int paramInt, boolean paramBoolean) {
    this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((TriConsumer)_$$Lambda$zO9HBUVgPeroyDQPLJE_MNMvSqc.INSTANCE, this.mCallback, Integer.valueOf(paramInt), Boolean.valueOf(paramBoolean)).recycleOnUse());
  }
  
  public void onSessionProgressChanged(int paramInt, float paramFloat) {
    this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((TriConsumer)_$$Lambda$n3uXeb1v_YRmq_BWTfosEqUUr9g.INSTANCE, this.mCallback, Integer.valueOf(paramInt), Float.valueOf(paramFloat)).recycleOnUse());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInstaller$SessionCallbackDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
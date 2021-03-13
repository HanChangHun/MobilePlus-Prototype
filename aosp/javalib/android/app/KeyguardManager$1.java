package android.app;

import android.os.Handler;
import android.os.RemoteException;
import com.android.internal.policy.IKeyguardDismissCallback;
import java.util.Objects;

class null extends IKeyguardDismissCallback.Stub {
  public void onDismissCancelled() throws RemoteException {
    if (callback != null && !activity.isDestroyed()) {
      Handler handler = activity.mHandler;
      KeyguardManager.KeyguardDismissCallback keyguardDismissCallback = callback;
      Objects.requireNonNull(keyguardDismissCallback);
      handler.post(new _$$Lambda$KlsE01yvVI54Xvdo0TIjyhUKWHQ(keyguardDismissCallback));
    } 
  }
  
  public void onDismissError() throws RemoteException {
    if (callback != null && !activity.isDestroyed()) {
      Handler handler = activity.mHandler;
      KeyguardManager.KeyguardDismissCallback keyguardDismissCallback = callback;
      Objects.requireNonNull(keyguardDismissCallback);
      handler.post(new _$$Lambda$rztNj2LGZZegxvT34NFbOqZrZHM(keyguardDismissCallback));
    } 
  }
  
  public void onDismissSucceeded() throws RemoteException {
    if (callback != null && !activity.isDestroyed()) {
      Handler handler = activity.mHandler;
      KeyguardManager.KeyguardDismissCallback keyguardDismissCallback = callback;
      Objects.requireNonNull(keyguardDismissCallback);
      handler.post(new _$$Lambda$YTMEV7TmbMrzjIag59qAffcsEUw(keyguardDismissCallback));
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/KeyguardManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
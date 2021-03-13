package android.app;

import android.os.Handler;
import java.lang.ref.WeakReference;
import java.util.Objects;

final class RequestFinishCallback extends IRequestFinishCallback.Stub {
  private final WeakReference<Activity> mActivityRef;
  
  RequestFinishCallback(WeakReference<Activity> paramWeakReference) {
    this.mActivityRef = paramWeakReference;
  }
  
  public void requestFinish() {
    Activity activity = this.mActivityRef.get();
    if (activity != null) {
      Handler handler = activity.mHandler;
      Objects.requireNonNull(activity);
      handler.post(new _$$Lambda$thfU5Zh_cKOR8p7IfITtlg111Go(activity));
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Activity$RequestFinishCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.CancellationSignal;
import android.os.ICancellationSignal;
import java.lang.ref.WeakReference;

final class SafeCancellationTransport extends ICancellationSignal.Stub {
  private final WeakReference<ActivityThread> mWeakActivityThread;
  
  SafeCancellationTransport(ActivityThread paramActivityThread, CancellationSignal paramCancellationSignal) {
    this.mWeakActivityThread = new WeakReference<>(paramActivityThread);
  }
  
  public void cancel() {
    ActivityThread activityThread = this.mWeakActivityThread.get();
    if (activityThread != null) {
      CancellationSignal cancellationSignal = ActivityThread.access$1200(activityThread, this);
      if (cancellationSignal != null)
        cancellationSignal.cancel(); 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$SafeCancellationTransport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
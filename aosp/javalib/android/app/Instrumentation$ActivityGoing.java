package android.app;

import android.os.MessageQueue;

final class ActivityGoing implements MessageQueue.IdleHandler {
  private final Instrumentation.ActivityWaiter mWaiter;
  
  public ActivityGoing(Instrumentation.ActivityWaiter paramActivityWaiter) {
    this.mWaiter = paramActivityWaiter;
  }
  
  public final boolean queueIdle() {
    synchronized (Instrumentation.access$200(Instrumentation.this)) {
      Instrumentation.access$300(Instrumentation.this).remove(this.mWaiter);
      Instrumentation.access$200(Instrumentation.this).notifyAll();
      return false;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Instrumentation$ActivityGoing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
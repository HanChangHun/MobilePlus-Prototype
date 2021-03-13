package android.app;

import android.os.MessageQueue;

final class GcIdler implements MessageQueue.IdleHandler {
  public final boolean queueIdle() {
    ActivityThread.this.doGcIfNeeded();
    ActivityThread.access$3500(ActivityThread.this);
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$GcIdler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
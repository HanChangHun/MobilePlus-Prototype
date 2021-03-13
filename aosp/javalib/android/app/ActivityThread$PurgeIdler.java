package android.app;

import android.os.MessageQueue;

final class PurgeIdler implements MessageQueue.IdleHandler {
  public boolean queueIdle() {
    ActivityThread.access$3500(ActivityThread.this);
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$PurgeIdler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
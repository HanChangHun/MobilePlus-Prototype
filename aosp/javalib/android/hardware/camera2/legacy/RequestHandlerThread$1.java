package android.hardware.camera2.legacy;

import android.os.MessageQueue;

class null implements MessageQueue.IdleHandler {
  public boolean queueIdle() {
    RequestHandlerThread.access$000(RequestHandlerThread.this).open();
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestHandlerThread$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
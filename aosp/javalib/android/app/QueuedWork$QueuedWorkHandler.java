package android.app;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class QueuedWorkHandler extends Handler {
  static final int MSG_RUN = 1;
  
  QueuedWorkHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    if (paramMessage.what == 1)
      QueuedWork.access$000(); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/QueuedWork$QueuedWorkHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
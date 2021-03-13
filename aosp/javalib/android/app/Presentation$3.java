package android.app;

import android.os.Handler;
import android.os.Message;

class null extends Handler {
  public void handleMessage(Message paramMessage) {
    if (paramMessage.what == 1)
      Presentation.this.cancel(); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Presentation$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
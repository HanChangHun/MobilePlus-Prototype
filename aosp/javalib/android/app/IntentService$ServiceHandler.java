package android.app;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class ServiceHandler extends Handler {
  public ServiceHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    IntentService.this.onHandleIntent((Intent)paramMessage.obj);
    IntentService.this.stopSelf(paramMessage.arg1);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IntentService$ServiceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
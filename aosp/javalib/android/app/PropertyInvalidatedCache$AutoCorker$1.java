package android.app;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class null extends Handler {
  null(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    PropertyInvalidatedCache.AutoCorker.access$000(PropertyInvalidatedCache.AutoCorker.this, paramMessage);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PropertyInvalidatedCache$AutoCorker$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
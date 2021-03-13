package android.graphics;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class null extends Handler {
  null(Looper paramLooper, Handler.Callback paramCallback, boolean paramBoolean) {
    super(paramLooper, paramCallback, paramBoolean);
  }
  
  public void handleMessage(Message paramMessage) {
    listener.onFrameAvailable(SurfaceTexture.this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/SurfaceTexture$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
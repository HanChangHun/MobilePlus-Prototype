package android.hardware.display;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class DisplayListenerDelegate extends Handler {
  public final DisplayManager.DisplayListener mListener;
  
  DisplayListenerDelegate(DisplayManager.DisplayListener paramDisplayListener, Looper paramLooper) {
    super(paramLooper, null, true);
    this.mListener = paramDisplayListener;
  }
  
  public void clearEvents() {
    removeCallbacksAndMessages(null);
  }
  
  public void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 1) {
      if (i != 2) {
        if (i == 3)
          this.mListener.onDisplayRemoved(paramMessage.arg1); 
      } else {
        this.mListener.onDisplayChanged(paramMessage.arg1);
      } 
    } else {
      this.mListener.onDisplayAdded(paramMessage.arg1);
    } 
  }
  
  public void sendDisplayEvent(int paramInt1, int paramInt2) {
    sendMessage(obtainMessage(paramInt2, paramInt1, 0));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayManagerGlobal$DisplayListenerDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
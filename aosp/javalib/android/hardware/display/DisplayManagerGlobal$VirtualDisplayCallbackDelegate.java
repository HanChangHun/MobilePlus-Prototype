package android.hardware.display;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class VirtualDisplayCallbackDelegate extends Handler {
  public static final int MSG_DISPLAY_PAUSED = 0;
  
  public static final int MSG_DISPLAY_RESUMED = 1;
  
  public static final int MSG_DISPLAY_STOPPED = 2;
  
  private final VirtualDisplay.Callback mCallback;
  
  public VirtualDisplayCallbackDelegate(VirtualDisplay.Callback paramCallback, Handler paramHandler) {
    super(looper, null, true);
    Looper looper;
    this.mCallback = paramCallback;
  }
  
  public void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 0) {
      if (i != 1) {
        if (i == 2)
          this.mCallback.onStopped(); 
      } else {
        this.mCallback.onResumed();
      } 
    } else {
      this.mCallback.onPaused();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayManagerGlobal$VirtualDisplayCallbackDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
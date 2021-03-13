package android.hardware.input;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class InputDeviceListenerDelegate extends Handler {
  public final InputManager.InputDeviceListener mListener;
  
  public InputDeviceListenerDelegate(InputManager.InputDeviceListener paramInputDeviceListener, Handler paramHandler) {
    super(looper);
    Looper looper;
    this.mListener = paramInputDeviceListener;
  }
  
  public void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 1) {
      if (i != 2) {
        if (i == 3)
          this.mListener.onInputDeviceChanged(paramMessage.arg1); 
      } else {
        this.mListener.onInputDeviceRemoved(paramMessage.arg1);
      } 
    } else {
      this.mListener.onInputDeviceAdded(paramMessage.arg1);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/InputManager$InputDeviceListenerDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
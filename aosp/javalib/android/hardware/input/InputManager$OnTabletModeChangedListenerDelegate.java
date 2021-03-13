package android.hardware.input;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.internal.os.SomeArgs;

final class OnTabletModeChangedListenerDelegate extends Handler {
  private static final int MSG_TABLET_MODE_CHANGED = 0;
  
  public final InputManager.OnTabletModeChangedListener mListener;
  
  public OnTabletModeChangedListenerDelegate(InputManager.OnTabletModeChangedListener paramOnTabletModeChangedListener, Handler paramHandler) {
    super(looper);
    Looper looper;
    this.mListener = paramOnTabletModeChangedListener;
  }
  
  public void handleMessage(Message paramMessage) {
    if (paramMessage.what == 0) {
      SomeArgs someArgs = (SomeArgs)paramMessage.obj;
      long l1 = someArgs.argi1;
      long l2 = someArgs.argi2;
      boolean bool = ((Boolean)someArgs.arg1).booleanValue();
      this.mListener.onTabletModeChanged(l1 & 0xFFFFFFFFL | l2 << 32L, bool);
    } 
  }
  
  public void sendTabletModeChanged(long paramLong, boolean paramBoolean) {
    SomeArgs someArgs = SomeArgs.obtain();
    someArgs.argi1 = (int)(0xFFFFFFFFFFFFFFFFL & paramLong);
    someArgs.argi2 = (int)(paramLong >> 32L);
    someArgs.arg1 = Boolean.valueOf(paramBoolean);
    obtainMessage(0, someArgs).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/InputManager$OnTabletModeChangedListenerDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
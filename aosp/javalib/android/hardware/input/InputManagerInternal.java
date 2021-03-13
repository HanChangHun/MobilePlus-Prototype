package android.hardware.input;

import android.hardware.display.DisplayViewport;
import android.os.IBinder;
import android.view.InputEvent;
import java.util.List;

public abstract class InputManagerInternal {
  public abstract boolean injectInputEvent(InputEvent paramInputEvent, int paramInt);
  
  public abstract void setDisplayViewports(List<DisplayViewport> paramList);
  
  public abstract void setInteractive(boolean paramBoolean);
  
  public abstract void setPulseGestureEnabled(boolean paramBoolean);
  
  public abstract void toggleCapsLock(int paramInt);
  
  public abstract boolean transferTouchFocus(IBinder paramIBinder1, IBinder paramIBinder2);
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/InputManagerInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
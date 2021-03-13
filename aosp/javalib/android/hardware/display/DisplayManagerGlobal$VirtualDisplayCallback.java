package android.hardware.display;

import android.os.Handler;

final class VirtualDisplayCallback extends IVirtualDisplayCallback.Stub {
  private DisplayManagerGlobal.VirtualDisplayCallbackDelegate mDelegate;
  
  public VirtualDisplayCallback(VirtualDisplay.Callback paramCallback, Handler paramHandler) {
    if (paramCallback != null)
      this.mDelegate = new DisplayManagerGlobal.VirtualDisplayCallbackDelegate(paramCallback, paramHandler); 
  }
  
  public void onPaused() {
    DisplayManagerGlobal.VirtualDisplayCallbackDelegate virtualDisplayCallbackDelegate = this.mDelegate;
    if (virtualDisplayCallbackDelegate != null)
      virtualDisplayCallbackDelegate.sendEmptyMessage(0); 
  }
  
  public void onResumed() {
    DisplayManagerGlobal.VirtualDisplayCallbackDelegate virtualDisplayCallbackDelegate = this.mDelegate;
    if (virtualDisplayCallbackDelegate != null)
      virtualDisplayCallbackDelegate.sendEmptyMessage(1); 
  }
  
  public void onStopped() {
    DisplayManagerGlobal.VirtualDisplayCallbackDelegate virtualDisplayCallbackDelegate = this.mDelegate;
    if (virtualDisplayCallbackDelegate != null)
      virtualDisplayCallbackDelegate.sendEmptyMessage(2); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayManagerGlobal$VirtualDisplayCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
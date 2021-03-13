package android.app;

import android.accessibilityservice.AccessibilityGestureEvent;
import android.accessibilityservice.AccessibilityService;
import android.graphics.Region;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.function.BiConsumer;

class null implements AccessibilityService.Callbacks {
  public void init(int paramInt, IBinder paramIBinder) {
    synchronized (UiAutomation.access$000(this$0)) {
      UiAutomation.access$102(this$0, paramInt);
      UiAutomation.access$000(this$0).notifyAll();
      if (Build.IS_DEBUGGABLE) {
        String str = UiAutomation.access$200();
        null = new StringBuilder();
        null.append("Init ");
        null.append(this$0);
        Log.v(str, null.toString());
      } 
      return;
    } 
  }
  
  public void onAccessibilityButtonAvailabilityChanged(boolean paramBoolean) {}
  
  public void onAccessibilityButtonClicked(int paramInt) {}
  
  public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    synchronized (UiAutomation.access$000(this$0)) {
      UiAutomation.access$302(this$0, paramAccessibilityEvent.getEventTime());
      if (UiAutomation.access$400(this$0))
        UiAutomation.access$500(this$0).add(AccessibilityEvent.obtain(paramAccessibilityEvent)); 
      UiAutomation.access$000(this$0).notifyAll();
      UiAutomation.OnAccessibilityEventListener onAccessibilityEventListener = UiAutomation.access$600(this$0);
      if (onAccessibilityEventListener != null)
        UiAutomation.access$700(this$0).sendMessage(PooledLambda.obtainMessage((BiConsumer)_$$Lambda$GnVtsLTLDH5bZdtLeTd6cfwpgcs.INSTANCE, onAccessibilityEventListener, AccessibilityEvent.obtain(paramAccessibilityEvent))); 
      return;
    } 
  }
  
  public void onFingerprintCapturingGesturesChanged(boolean paramBoolean) {}
  
  public void onFingerprintGesture(int paramInt) {}
  
  public boolean onGesture(AccessibilityGestureEvent paramAccessibilityGestureEvent) {
    return false;
  }
  
  public void onInterrupt() {}
  
  public boolean onKeyEvent(KeyEvent paramKeyEvent) {
    return false;
  }
  
  public void onMagnificationChanged(int paramInt, Region paramRegion, float paramFloat1, float paramFloat2, float paramFloat3) {}
  
  public void onPerformGestureResult(int paramInt, boolean paramBoolean) {}
  
  public void onServiceConnected() {}
  
  public void onSoftKeyboardShowModeChanged(int paramInt) {}
  
  public void onSystemActionsChanged() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/UiAutomation$IAccessibilityServiceClientImpl$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
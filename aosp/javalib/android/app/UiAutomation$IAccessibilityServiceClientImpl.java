package android.app;

import android.accessibilityservice.AccessibilityGestureEvent;
import android.accessibilityservice.AccessibilityService;
import android.graphics.Region;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.function.BiConsumer;

class IAccessibilityServiceClientImpl extends AccessibilityService.IAccessibilityServiceClientWrapper {
  public IAccessibilityServiceClientImpl(Looper paramLooper) {
    super(null, paramLooper, new AccessibilityService.Callbacks(paramUiAutomation) {
          public void init(int param2Int, IBinder param2IBinder) {
            synchronized (UiAutomation.access$000(UiAutomation.this)) {
              UiAutomation.access$102(UiAutomation.this, param2Int);
              UiAutomation.access$000(UiAutomation.this).notifyAll();
              if (Build.IS_DEBUGGABLE) {
                String str = UiAutomation.access$200();
                null = new StringBuilder();
                null.append("Init ");
                null.append(UiAutomation.this);
                Log.v(str, null.toString());
              } 
              return;
            } 
          }
          
          public void onAccessibilityButtonAvailabilityChanged(boolean param2Boolean) {}
          
          public void onAccessibilityButtonClicked(int param2Int) {}
          
          public void onAccessibilityEvent(AccessibilityEvent param2AccessibilityEvent) {
            synchronized (UiAutomation.access$000(UiAutomation.this)) {
              UiAutomation.access$302(UiAutomation.this, param2AccessibilityEvent.getEventTime());
              if (UiAutomation.access$400(UiAutomation.this))
                UiAutomation.access$500(UiAutomation.this).add(AccessibilityEvent.obtain(param2AccessibilityEvent)); 
              UiAutomation.access$000(UiAutomation.this).notifyAll();
              UiAutomation.OnAccessibilityEventListener onAccessibilityEventListener = UiAutomation.access$600(UiAutomation.this);
              if (onAccessibilityEventListener != null)
                UiAutomation.access$700(UiAutomation.this).sendMessage(PooledLambda.obtainMessage((BiConsumer)_$$Lambda$GnVtsLTLDH5bZdtLeTd6cfwpgcs.INSTANCE, onAccessibilityEventListener, AccessibilityEvent.obtain(param2AccessibilityEvent))); 
              return;
            } 
          }
          
          public void onFingerprintCapturingGesturesChanged(boolean param2Boolean) {}
          
          public void onFingerprintGesture(int param2Int) {}
          
          public boolean onGesture(AccessibilityGestureEvent param2AccessibilityGestureEvent) {
            return false;
          }
          
          public void onInterrupt() {}
          
          public boolean onKeyEvent(KeyEvent param2KeyEvent) {
            return false;
          }
          
          public void onMagnificationChanged(int param2Int, Region param2Region, float param2Float1, float param2Float2, float param2Float3) {}
          
          public void onPerformGestureResult(int param2Int, boolean param2Boolean) {}
          
          public void onServiceConnected() {}
          
          public void onSoftKeyboardShowModeChanged(int param2Int) {}
          
          public void onSystemActionsChanged() {}
        });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/UiAutomation$IAccessibilityServiceClientImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
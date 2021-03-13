package android.accessibilityservice;

import android.graphics.Region;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public interface Callbacks {
  void init(int paramInt, IBinder paramIBinder);
  
  void onAccessibilityButtonAvailabilityChanged(boolean paramBoolean);
  
  void onAccessibilityButtonClicked(int paramInt);
  
  void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent);
  
  void onFingerprintCapturingGesturesChanged(boolean paramBoolean);
  
  void onFingerprintGesture(int paramInt);
  
  boolean onGesture(AccessibilityGestureEvent paramAccessibilityGestureEvent);
  
  void onInterrupt();
  
  boolean onKeyEvent(KeyEvent paramKeyEvent);
  
  void onMagnificationChanged(int paramInt, Region paramRegion, float paramFloat1, float paramFloat2, float paramFloat3);
  
  void onPerformGestureResult(int paramInt, boolean paramBoolean);
  
  void onServiceConnected();
  
  void onSoftKeyboardShowModeChanged(int paramInt);
  
  void onSystemActionsChanged();
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityService$Callbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
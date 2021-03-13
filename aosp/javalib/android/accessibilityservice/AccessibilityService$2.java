package android.accessibilityservice;

import android.graphics.Region;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.WindowManagerImpl;
import android.view.accessibility.AccessibilityEvent;

class null implements AccessibilityService.Callbacks {
  public void init(int paramInt, IBinder paramIBinder) {
    AccessibilityService.access$002(AccessibilityService.this, paramInt);
    AccessibilityService.access$202(AccessibilityService.this, paramIBinder);
    ((WindowManagerImpl)AccessibilityService.this.getSystemService("window")).setDefaultToken(paramIBinder);
  }
  
  public void onAccessibilityButtonAvailabilityChanged(boolean paramBoolean) {
    AccessibilityService.access$800(AccessibilityService.this, paramBoolean);
  }
  
  public void onAccessibilityButtonClicked(int paramInt) {
    AccessibilityService.access$700(AccessibilityService.this, paramInt);
  }
  
  public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    AccessibilityService.this.onAccessibilityEvent(paramAccessibilityEvent);
  }
  
  public void onFingerprintCapturingGesturesChanged(boolean paramBoolean) {
    AccessibilityService.access$500(AccessibilityService.this, paramBoolean);
  }
  
  public void onFingerprintGesture(int paramInt) {
    AccessibilityService.access$600(AccessibilityService.this, paramInt);
  }
  
  public boolean onGesture(AccessibilityGestureEvent paramAccessibilityGestureEvent) {
    return AccessibilityService.this.onGesture(paramAccessibilityGestureEvent);
  }
  
  public void onInterrupt() {
    AccessibilityService.this.onInterrupt();
  }
  
  public boolean onKeyEvent(KeyEvent paramKeyEvent) {
    return AccessibilityService.this.onKeyEvent(paramKeyEvent);
  }
  
  public void onMagnificationChanged(int paramInt, Region paramRegion, float paramFloat1, float paramFloat2, float paramFloat3) {
    AccessibilityService.access$300(AccessibilityService.this, paramInt, paramRegion, paramFloat1, paramFloat2, paramFloat3);
  }
  
  public void onPerformGestureResult(int paramInt, boolean paramBoolean) {
    AccessibilityService.this.onPerformGestureResult(paramInt, paramBoolean);
  }
  
  public void onServiceConnected() {
    AccessibilityService.access$100(AccessibilityService.this);
  }
  
  public void onSoftKeyboardShowModeChanged(int paramInt) {
    AccessibilityService.access$400(AccessibilityService.this, paramInt);
  }
  
  public void onSystemActionsChanged() {
    AccessibilityService.this.onSystemActionsChanged();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityService$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.accessibilityservice;

import android.os.Handler;

class GestureResultCallbackInfo {
  AccessibilityService.GestureResultCallback callback;
  
  GestureDescription gestureDescription;
  
  Handler handler;
  
  GestureResultCallbackInfo(GestureDescription paramGestureDescription, AccessibilityService.GestureResultCallback paramGestureResultCallback, Handler paramHandler) {
    this.gestureDescription = paramGestureDescription;
    this.callback = paramGestureResultCallback;
    this.handler = paramHandler;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityService$GestureResultCallbackInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
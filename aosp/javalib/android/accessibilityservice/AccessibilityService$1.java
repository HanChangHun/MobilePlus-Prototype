package android.accessibilityservice;

class null implements Runnable {
  public void run() {
    if (completedSuccessfully) {
      finalCallbackInfo.callback.onCompleted(finalCallbackInfo.gestureDescription);
    } else {
      finalCallbackInfo.callback.onCancelled(finalCallbackInfo.gestureDescription);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityService$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.accessibilityservice;

import android.graphics.Region;

class null implements Runnable {
  public void run() {
    listener.onMagnificationChanged(AccessibilityService.MagnificationController.this, region, scale, centerX, centerY);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityService$MagnificationController$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
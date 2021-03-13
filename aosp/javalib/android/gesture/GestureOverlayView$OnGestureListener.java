package android.gesture;

import android.view.MotionEvent;

public interface OnGestureListener {
  void onGesture(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent);
  
  void onGestureCancelled(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent);
  
  void onGestureEnded(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent);
  
  void onGestureStarted(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent);
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/GestureOverlayView$OnGestureListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
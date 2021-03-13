package android.gesture;

import android.view.animation.AnimationUtils;

class FadeOutRunnable implements Runnable {
  boolean fireActionPerformed;
  
  boolean resetMultipleStrokes;
  
  private FadeOutRunnable() {}
  
  public void run() {
    if (GestureOverlayView.access$100(GestureOverlayView.this)) {
      long l = AnimationUtils.currentAnimationTimeMillis() - GestureOverlayView.access$200(GestureOverlayView.this);
      if (l > GestureOverlayView.access$300(GestureOverlayView.this)) {
        if (this.fireActionPerformed)
          GestureOverlayView.access$400(GestureOverlayView.this); 
        GestureOverlayView.access$502(GestureOverlayView.this, false);
        GestureOverlayView.access$102(GestureOverlayView.this, false);
        GestureOverlayView.access$602(GestureOverlayView.this, false);
        GestureOverlayView.access$700(GestureOverlayView.this).rewind();
        GestureOverlayView.access$802(GestureOverlayView.this, (Gesture)null);
        GestureOverlayView.access$900(GestureOverlayView.this, 255);
      } else {
        GestureOverlayView.access$602(GestureOverlayView.this, true);
        float f = Math.max(0.0F, Math.min(1.0F, (float)l / (float)GestureOverlayView.access$300(GestureOverlayView.this)));
        GestureOverlayView gestureOverlayView = GestureOverlayView.this;
        GestureOverlayView.access$1002(gestureOverlayView, 1.0F - GestureOverlayView.access$1100(gestureOverlayView).getInterpolation(f));
        gestureOverlayView = GestureOverlayView.this;
        GestureOverlayView.access$900(gestureOverlayView, (int)(GestureOverlayView.access$1000(gestureOverlayView) * 255.0F));
        GestureOverlayView.this.postDelayed(this, 16L);
      } 
    } else if (this.resetMultipleStrokes) {
      GestureOverlayView.access$1202(GestureOverlayView.this, true);
    } else {
      GestureOverlayView.access$400(GestureOverlayView.this);
      GestureOverlayView.access$602(GestureOverlayView.this, false);
      GestureOverlayView.access$700(GestureOverlayView.this).rewind();
      GestureOverlayView.access$802(GestureOverlayView.this, (Gesture)null);
      GestureOverlayView.access$502(GestureOverlayView.this, false);
      GestureOverlayView.access$900(GestureOverlayView.this, 255);
    } 
    GestureOverlayView.this.invalidate();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/GestureOverlayView$FadeOutRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
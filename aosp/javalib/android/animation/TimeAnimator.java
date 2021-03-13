package android.animation;

import android.view.animation.AnimationUtils;

public class TimeAnimator extends ValueAnimator {
  private TimeListener mListener;
  
  private long mPreviousTime = -1L;
  
  boolean animateBasedOnTime(long paramLong) {
    if (this.mListener != null) {
      long l1 = this.mStartTime;
      long l2 = this.mPreviousTime;
      if (l2 < 0L) {
        l2 = 0L;
      } else {
        l2 = paramLong - l2;
      } 
      this.mPreviousTime = paramLong;
      this.mListener.onTimeUpdate(this, paramLong - l1, l2);
    } 
    return false;
  }
  
  void animateValue(float paramFloat) {}
  
  void initAnimation() {}
  
  public void setCurrentPlayTime(long paramLong) {
    long l = AnimationUtils.currentAnimationTimeMillis();
    this.mStartTime = Math.max(this.mStartTime, l - paramLong);
    this.mStartTimeCommitted = true;
    animateBasedOnTime(l);
  }
  
  public void setTimeListener(TimeListener paramTimeListener) {
    this.mListener = paramTimeListener;
  }
  
  public void start() {
    this.mPreviousTime = -1L;
    super.start();
  }
  
  public static interface TimeListener {
    void onTimeUpdate(TimeAnimator param1TimeAnimator, long param1Long1, long param1Long2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/TimeAnimator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
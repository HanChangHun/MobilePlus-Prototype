package android.graphics.drawable;

import android.animation.ObjectAnimator;

class AnimationDrawableTransition extends AnimatedStateListDrawable.Transition {
  private final ObjectAnimator mAnim;
  
  private final boolean mHasReversibleFlag;
  
  public AnimationDrawableTransition(AnimationDrawable paramAnimationDrawable, boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool;
    int i = paramAnimationDrawable.getNumberOfFrames();
    if (paramBoolean1) {
      bool = i - 1;
    } else {
      bool = false;
    } 
    if (paramBoolean1) {
      i = 0;
    } else {
      i--;
    } 
    AnimatedStateListDrawable.FrameInterpolator frameInterpolator = new AnimatedStateListDrawable.FrameInterpolator(paramAnimationDrawable, paramBoolean1);
    ObjectAnimator objectAnimator = ObjectAnimator.ofInt(paramAnimationDrawable, "currentIndex", new int[] { bool, i });
    objectAnimator.setAutoCancel(true);
    objectAnimator.setDuration(frameInterpolator.getTotalDuration());
    objectAnimator.setInterpolator(frameInterpolator);
    this.mHasReversibleFlag = paramBoolean2;
    this.mAnim = objectAnimator;
  }
  
  public boolean canReverse() {
    return this.mHasReversibleFlag;
  }
  
  public void reverse() {
    this.mAnim.reverse();
  }
  
  public void start() {
    this.mAnim.start();
  }
  
  public void stop() {
    this.mAnim.cancel();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedStateListDrawable$AnimationDrawableTransition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
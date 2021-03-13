package android.graphics.drawable;

import android.util.Log;

class AnimatedVectorDrawableTransition extends AnimatedStateListDrawable.Transition {
  private final AnimatedVectorDrawable mAvd;
  
  private final boolean mHasReversibleFlag;
  
  private final boolean mReversed;
  
  public AnimatedVectorDrawableTransition(AnimatedVectorDrawable paramAnimatedVectorDrawable, boolean paramBoolean1, boolean paramBoolean2) {
    this.mAvd = paramAnimatedVectorDrawable;
    this.mReversed = paramBoolean1;
    this.mHasReversibleFlag = paramBoolean2;
  }
  
  public boolean canReverse() {
    boolean bool;
    if (this.mAvd.canReverse() && this.mHasReversibleFlag) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void reverse() {
    if (canReverse()) {
      this.mAvd.reverse();
    } else {
      Log.w(AnimatedStateListDrawable.access$100(), "Can't reverse, either the reversible is set to false, or the AnimatedVectorDrawable can't reverse");
    } 
  }
  
  public void start() {
    if (this.mReversed) {
      reverse();
    } else {
      this.mAvd.start();
    } 
  }
  
  public void stop() {
    this.mAvd.stop();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedStateListDrawable$AnimatedVectorDrawableTransition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
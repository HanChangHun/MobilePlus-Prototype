package android.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.Canvas;

interface VectorDrawableAnimator {
  boolean canReverse();
  
  void end();
  
  void init(AnimatorSet paramAnimatorSet);
  
  boolean isInfinite();
  
  boolean isRunning();
  
  boolean isStarted();
  
  void onDraw(Canvas paramCanvas);
  
  void pause();
  
  void removeListener(Animator.AnimatorListener paramAnimatorListener);
  
  void reset();
  
  void resume();
  
  void reverse();
  
  void setListener(Animator.AnimatorListener paramAnimatorListener);
  
  void start();
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedVectorDrawable$VectorDrawableAnimator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.animation;

public interface AnimatorListener {
  void onAnimationCancel(Animator paramAnimator);
  
  void onAnimationEnd(Animator paramAnimator);
  
  default void onAnimationEnd(Animator paramAnimator, boolean paramBoolean) {
    onAnimationEnd(paramAnimator);
  }
  
  void onAnimationRepeat(Animator paramAnimator);
  
  void onAnimationStart(Animator paramAnimator);
  
  default void onAnimationStart(Animator paramAnimator, boolean paramBoolean) {
    onAnimationStart(paramAnimator);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/Animator$AnimatorListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
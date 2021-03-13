package android.animation;

class null extends AnimatorListenerAdapter {
  public void onAnimationEnd(Animator paramAnimator) {
    paramAnimator.setTarget(null);
    if (StateListAnimator.access$000(StateListAnimator.this) == paramAnimator)
      StateListAnimator.access$002(StateListAnimator.this, null); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/StateListAnimator$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
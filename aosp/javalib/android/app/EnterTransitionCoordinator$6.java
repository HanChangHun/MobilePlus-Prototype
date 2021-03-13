package android.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class null extends AnimatorListenerAdapter {
  public void onAnimationEnd(Animator paramAnimator) {
    EnterTransitionCoordinator.access$400(EnterTransitionCoordinator.this);
    EnterTransitionCoordinator.this.backgroundAnimatorComplete();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/EnterTransitionCoordinator$6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
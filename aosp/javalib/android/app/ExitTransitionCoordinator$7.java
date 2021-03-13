package android.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;

class null extends AnimatorListenerAdapter {
  public void onAnimationEnd(Animator paramAnimator) {
    ExitTransitionCoordinator.access$802(ExitTransitionCoordinator.this, (ObjectAnimator)null);
    if (!ExitTransitionCoordinator.access$500(ExitTransitionCoordinator.this)) {
      ExitTransitionCoordinator.access$902(ExitTransitionCoordinator.this, true);
      ExitTransitionCoordinator.this.notifyComplete();
    } 
    ExitTransitionCoordinator.this.backgroundAnimatorComplete();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ExitTransitionCoordinator$7.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
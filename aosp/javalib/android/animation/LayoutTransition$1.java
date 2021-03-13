package android.animation;

import android.view.View;

class null extends AnimatorListenerAdapter {
  public void onAnimationEnd(Animator paramAnimator) {
    LayoutTransition.access$000(LayoutTransition.this).remove(child);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/LayoutTransition$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
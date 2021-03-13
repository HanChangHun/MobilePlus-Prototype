package android.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

class null extends AnimatorListenerAdapter {
  public void onAnimationEnd(Animator paramAnimator) {
    ViewGroup viewGroup = container;
    if (viewGroup != null)
      viewGroup.endViewTransition(animatingView); 
    paramAnimator.removeListener((Animator.AnimatorListener)this);
    animatingView.setVisibility(8);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentManagerImpl$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
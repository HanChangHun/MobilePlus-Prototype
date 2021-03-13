package android.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

class null extends AnimatorListenerAdapter {
  public void onAnimationEnd(Animator paramAnimator) {
    container.endViewTransition(view);
    paramAnimator = f.getAnimatingAway();
    f.setAnimatingAway(null);
    if (container.indexOfChild(view) == -1 && paramAnimator != null) {
      FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
      Fragment fragment = fragment;
      fragmentManagerImpl.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentManagerImpl$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.transition.Transition;
import android.transition.TransitionListenerAdapter;

class null extends TransitionListenerAdapter {
  public void onTransitionEnd(Transition paramTransition) {
    paramTransition.removeListener((Transition.TransitionListener)this);
    if (ExitTransitionCoordinator.this.isViewsTransitionComplete())
      ExitTransitionCoordinator.access$100(ExitTransitionCoordinator.this); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ExitTransitionCoordinator$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
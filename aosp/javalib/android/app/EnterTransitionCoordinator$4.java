package android.app;

import android.transition.Transition;
import android.transition.TransitionListenerAdapter;

class null extends TransitionListenerAdapter {
  public void onTransitionEnd(Transition paramTransition) {
    paramTransition.removeListener((Transition.TransitionListener)this);
    EnterTransitionCoordinator.this.sharedElementTransitionComplete();
  }
  
  public void onTransitionStart(Transition paramTransition) {
    EnterTransitionCoordinator.access$200(EnterTransitionCoordinator.this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/EnterTransitionCoordinator$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
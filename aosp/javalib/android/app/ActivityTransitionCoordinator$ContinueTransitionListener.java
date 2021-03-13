package android.app;

import android.transition.Transition;
import android.transition.TransitionListenerAdapter;

public class ContinueTransitionListener extends TransitionListenerAdapter {
  public void onTransitionEnd(Transition paramTransition) {
    paramTransition.removeListener((Transition.TransitionListener)this);
  }
  
  public void onTransitionStart(Transition paramTransition) {
    ActivityTransitionCoordinator.access$102(ActivityTransitionCoordinator.this, false);
    Runnable runnable = ActivityTransitionCoordinator.access$200(ActivityTransitionCoordinator.this);
    ActivityTransitionCoordinator.access$202(ActivityTransitionCoordinator.this, null);
    if (runnable != null)
      ActivityTransitionCoordinator.this.startTransition(runnable); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityTransitionCoordinator$ContinueTransitionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
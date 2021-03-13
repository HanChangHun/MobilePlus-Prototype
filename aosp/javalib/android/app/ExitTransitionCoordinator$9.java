package android.app;

import android.transition.Transition;

class null extends ActivityTransitionCoordinator.ContinueTransitionListener {
  public void onTransitionEnd(Transition paramTransition) {
    ExitTransitionCoordinator.this.sharedElementTransitionComplete();
    if (ExitTransitionCoordinator.access$1000(ExitTransitionCoordinator.this)) {
      ExitTransitionCoordinator exitTransitionCoordinator = ExitTransitionCoordinator.this;
      exitTransitionCoordinator.showViews(exitTransitionCoordinator.mSharedElements, true);
    } 
    super.onTransitionEnd(paramTransition);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ExitTransitionCoordinator$9.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
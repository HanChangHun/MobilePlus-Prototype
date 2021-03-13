package android.app;

import android.transition.Transition;
import android.view.ViewGroup;

class null implements Runnable {
  public void run() {
    ViewGroup viewGroup = EnterTransitionCoordinator.this.getDecor();
    if (viewGroup != null) {
      Transition transition = EnterTransitionCoordinator.access$500(EnterTransitionCoordinator.this, viewGroup, true, false);
      EnterTransitionCoordinator.access$600(EnterTransitionCoordinator.this, transition);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/EnterTransitionCoordinator$9.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
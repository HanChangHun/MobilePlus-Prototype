package android.app;

import android.transition.Transition;
import android.view.View;
import java.util.ArrayList;

class null extends ActivityTransitionCoordinator.ContinueTransitionListener {
  public void onTransitionEnd(Transition paramTransition) {
    EnterTransitionCoordinator.access$302(EnterTransitionCoordinator.this, (Transition)null);
    paramTransition.removeListener((Transition.TransitionListener)this);
    EnterTransitionCoordinator.this.viewsTransitionComplete();
    super.onTransitionEnd(paramTransition);
  }
  
  public void onTransitionStart(Transition paramTransition) {
    EnterTransitionCoordinator.access$302(EnterTransitionCoordinator.this, paramTransition);
    ArrayList<View> arrayList = transitioningViews;
    if (arrayList != null)
      EnterTransitionCoordinator.this.showViews(arrayList, false); 
    super.onTransitionStart(paramTransition);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/EnterTransitionCoordinator$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
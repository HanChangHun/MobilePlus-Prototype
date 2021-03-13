package android.app;

import android.transition.Transition;
import android.view.View;
import java.util.ArrayList;

class null extends ActivityTransitionCoordinator.ContinueTransitionListener {
  public void onTransitionEnd(Transition paramTransition) {
    ExitTransitionCoordinator.this.viewsTransitionComplete();
    if (ExitTransitionCoordinator.access$1000(ExitTransitionCoordinator.this)) {
      ArrayList<View> arrayList = transitioningViews;
      if (arrayList != null) {
        ExitTransitionCoordinator.this.showViews(arrayList, true);
        ExitTransitionCoordinator.this.setTransitioningViewsVisiblity(0, true);
      } 
    } 
    if (ExitTransitionCoordinator.access$1100(ExitTransitionCoordinator.this) != null)
      ExitTransitionCoordinator.access$100(ExitTransitionCoordinator.this); 
    super.onTransitionEnd(paramTransition);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ExitTransitionCoordinator$8.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
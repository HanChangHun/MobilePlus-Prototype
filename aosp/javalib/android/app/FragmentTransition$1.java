package android.app;

import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.view.View;
import java.util.ArrayList;

class null extends TransitionListenerAdapter {
  public void onTransitionEnd(Transition paramTransition) {
    paramTransition.removeListener((Transition.TransitionListener)this);
    fragmentView.setVisibility(8);
    FragmentTransition.access$000(exitingViews, 0);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentTransition$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
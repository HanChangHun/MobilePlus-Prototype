package android.app;

import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionSet;
import java.util.ArrayList;

class null extends TransitionListenerAdapter {
  public void onTransitionEnd(Transition paramTransition) {
    paramTransition.removeListener((Transition.TransitionListener)this);
  }
  
  public void onTransitionStart(Transition paramTransition) {
    paramTransition = enterTransition;
    if (paramTransition != null)
      FragmentTransition.replaceTargets(paramTransition, enteringViews, null); 
    paramTransition = exitTransition;
    if (paramTransition != null)
      FragmentTransition.replaceTargets(paramTransition, exitingViews, null); 
    TransitionSet transitionSet = sharedElementTransition;
    if (transitionSet != null)
      FragmentTransition.replaceTargets((Transition)transitionSet, sharedElementsIn, null); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentTransition$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
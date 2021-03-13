package android.animation;

import android.view.View;
import android.view.ViewGroup;

class null extends AnimatorListenerAdapter {
  public void onAnimationCancel(Animator paramAnimator) {
    child.removeOnLayoutChangeListener(listener);
    LayoutTransition.access$1500(LayoutTransition.this).remove(child);
  }
  
  public void onAnimationEnd(Animator paramAnimator) {
    LayoutTransition.access$1400(LayoutTransition.this).remove(child);
    if (LayoutTransition.access$1600(LayoutTransition.this))
      for (LayoutTransition.TransitionListener transitionListener : LayoutTransition.access$1700(LayoutTransition.this).clone()) {
        LayoutTransition layoutTransition = LayoutTransition.this;
        ViewGroup viewGroup = parent;
        View view = child;
        int i = changeReason;
        if (i == 2) {
          i = 0;
        } else if (i == 3) {
          i = 1;
        } else {
          i = 4;
        } 
        transitionListener.endTransition(layoutTransition, viewGroup, view, i);
      }  
  }
  
  public void onAnimationStart(Animator paramAnimator) {
    if (LayoutTransition.access$1600(LayoutTransition.this))
      for (LayoutTransition.TransitionListener transitionListener : LayoutTransition.access$1700(LayoutTransition.this).clone()) {
        LayoutTransition layoutTransition = LayoutTransition.this;
        ViewGroup viewGroup = parent;
        View view = child;
        int i = changeReason;
        if (i == 2) {
          i = 0;
        } else if (i == 3) {
          i = 1;
        } else {
          i = 4;
        } 
        transitionListener.startTransition(layoutTransition, viewGroup, view, i);
      }  
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/LayoutTransition$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
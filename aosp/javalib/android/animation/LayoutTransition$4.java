package android.animation;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

class null extends AnimatorListenerAdapter {
  public void onAnimationEnd(Animator paramAnimator) {
    LayoutTransition.access$1800(LayoutTransition.this).remove(child);
    if (LayoutTransition.access$1600(LayoutTransition.this)) {
      Iterator<LayoutTransition.TransitionListener> iterator = ((ArrayList)LayoutTransition.access$1700(LayoutTransition.this).clone()).iterator();
      while (iterator.hasNext())
        ((LayoutTransition.TransitionListener)iterator.next()).endTransition(LayoutTransition.this, parent, child, 2); 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/LayoutTransition$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
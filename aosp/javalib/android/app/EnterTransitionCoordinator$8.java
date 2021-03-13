package android.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import java.util.ArrayList;

class null extends AnimatorListenerAdapter {
  public void onAnimationEnd(Animator paramAnimator) {
    ViewGroupOverlay viewGroupOverlay = decorView.getOverlay();
    int i = rejectedSnapshots.size();
    for (byte b = 0; b < i; b++)
      viewGroupOverlay.remove(rejectedSnapshots.get(b)); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/EnterTransitionCoordinator$8.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.view.View;
import android.view.ViewTreeObserver;

class null implements ViewTreeObserver.OnPreDrawListener {
  public boolean onPreDraw() {
    if (EnterTransitionCoordinator.access$000(EnterTransitionCoordinator.this))
      if (viewTreeObserver.isAlive()) {
        viewTreeObserver.removeOnPreDrawListener(this);
      } else {
        decorView.getViewTreeObserver().removeOnPreDrawListener(this);
      }  
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/EnterTransitionCoordinator$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.view.OneShotPreDrawListener;

class null implements SharedElementCallback.OnSharedElementsReadyListener {
  public void onSharedElementsReady() {
    ViewGroup viewGroup = EnterTransitionCoordinator.this.getDecor();
    if (viewGroup != null) {
      OneShotPreDrawListener.add((View)viewGroup, false, new _$$Lambda$EnterTransitionCoordinator$3$I_t9rJUkrW7bwRLQtTrE8DgvPZs(this, sharedElementState));
      viewGroup.invalidate();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/EnterTransitionCoordinator$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
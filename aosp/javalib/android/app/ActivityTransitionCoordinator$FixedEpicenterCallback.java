package android.app;

import android.graphics.Rect;
import android.transition.Transition;

class FixedEpicenterCallback extends Transition.EpicenterCallback {
  private Rect mEpicenter;
  
  private FixedEpicenterCallback() {}
  
  public Rect onGetEpicenter(Transition paramTransition) {
    return this.mEpicenter;
  }
  
  public void setEpicenter(Rect paramRect) {
    this.mEpicenter = paramRect;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityTransitionCoordinator$FixedEpicenterCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
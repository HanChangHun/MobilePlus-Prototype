package android.app;

import android.graphics.Rect;
import android.transition.Transition;

class null extends Transition.EpicenterCallback {
  public Rect onGetEpicenter(Transition paramTransition) {
    return inEpicenter.isEmpty() ? null : inEpicenter;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentTransition$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
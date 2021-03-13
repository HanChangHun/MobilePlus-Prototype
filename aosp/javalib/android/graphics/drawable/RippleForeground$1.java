package android.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class null extends AnimatorListenerAdapter {
  public void onAnimationEnd(Animator paramAnimator) {
    RippleForeground.access$002(RippleForeground.this, true);
    RippleForeground.access$100(RippleForeground.this);
    RippleForeground.access$200(RippleForeground.this);
    if (RippleForeground.access$300(RippleForeground.this).isEmpty())
      RippleForeground.access$400(RippleForeground.this); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/RippleForeground$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
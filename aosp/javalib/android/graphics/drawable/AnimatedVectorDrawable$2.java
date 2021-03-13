package android.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import java.util.ArrayList;

class null extends AnimatorListenerAdapter {
  public void onAnimationEnd(Animator paramAnimator) {
    ArrayList<Animatable2.AnimationCallback> arrayList = new ArrayList(AnimatedVectorDrawable.access$700(AnimatedVectorDrawable.this));
    int i = arrayList.size();
    for (byte b = 0; b < i; b++)
      ((Animatable2.AnimationCallback)arrayList.get(b)).onAnimationEnd(AnimatedVectorDrawable.this); 
  }
  
  public void onAnimationStart(Animator paramAnimator) {
    ArrayList<Animatable2.AnimationCallback> arrayList = new ArrayList(AnimatedVectorDrawable.access$700(AnimatedVectorDrawable.this));
    int i = arrayList.size();
    for (byte b = 0; b < i; b++)
      ((Animatable2.AnimationCallback)arrayList.get(b)).onAnimationStart(AnimatedVectorDrawable.this); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedVectorDrawable$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
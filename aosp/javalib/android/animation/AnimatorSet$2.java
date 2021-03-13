package android.animation;

import android.util.AndroidRuntimeException;

class null extends AnimatorListenerAdapter {
  public void onAnimationEnd(Animator paramAnimator) {
    if (AnimatorSet.access$100(anim).get(paramAnimator) != null) {
      ((AnimatorSet.Node)AnimatorSet.access$100(anim).get(paramAnimator)).mEnded = true;
      return;
    } 
    throw new AndroidRuntimeException("Error: animation ended is not in the node map");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimatorSet$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
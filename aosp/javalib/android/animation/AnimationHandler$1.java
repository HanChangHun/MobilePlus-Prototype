package android.animation;

import android.view.Choreographer;

class null implements Choreographer.FrameCallback {
  public void doFrame(long paramLong) {
    AnimationHandler animationHandler = AnimationHandler.this;
    AnimationHandler.access$100(animationHandler, AnimationHandler.access$000(animationHandler).getFrameTime());
    if (AnimationHandler.access$200(AnimationHandler.this).size() > 0)
      AnimationHandler.access$000(AnimationHandler.this).postFrameCallback(this); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimationHandler$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
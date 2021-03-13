package android.animation;

import android.view.Choreographer;

public interface AnimationFrameCallbackProvider {
  long getFrameDelay();
  
  long getFrameTime();
  
  void postCommitCallback(Runnable paramRunnable);
  
  void postFrameCallback(Choreographer.FrameCallback paramFrameCallback);
  
  void setFrameDelay(long paramLong);
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimationHandler$AnimationFrameCallbackProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
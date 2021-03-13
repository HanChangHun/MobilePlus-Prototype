package android.animation;

import android.view.Choreographer;

class MyFrameCallbackProvider implements AnimationHandler.AnimationFrameCallbackProvider {
  final Choreographer mChoreographer = Choreographer.getInstance();
  
  private MyFrameCallbackProvider() {}
  
  public long getFrameDelay() {
    return Choreographer.getFrameDelay();
  }
  
  public long getFrameTime() {
    return this.mChoreographer.getFrameTime();
  }
  
  public void postCommitCallback(Runnable paramRunnable) {
    this.mChoreographer.postCallback(4, paramRunnable, null);
  }
  
  public void postFrameCallback(Choreographer.FrameCallback paramFrameCallback) {
    this.mChoreographer.postFrameCallback(paramFrameCallback);
  }
  
  public void setFrameDelay(long paramLong) {
    Choreographer.setFrameDelay(paramLong);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimationHandler$MyFrameCallbackProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
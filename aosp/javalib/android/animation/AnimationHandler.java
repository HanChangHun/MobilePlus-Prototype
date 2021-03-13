package android.animation;

import android.os.SystemClock;
import android.util.ArrayMap;
import android.view.Choreographer;
import java.util.ArrayList;

public class AnimationHandler {
  public static final ThreadLocal<AnimationHandler> sAnimatorHandler = new ThreadLocal<>();
  
  private final ArrayList<AnimationFrameCallback> mAnimationCallbacks = new ArrayList<>();
  
  private final ArrayList<AnimationFrameCallback> mCommitCallbacks = new ArrayList<>();
  
  private final ArrayMap<AnimationFrameCallback, Long> mDelayedCallbackStartTime = new ArrayMap();
  
  private final Choreographer.FrameCallback mFrameCallback = new Choreographer.FrameCallback() {
      public void doFrame(long param1Long) {
        AnimationHandler animationHandler = AnimationHandler.this;
        animationHandler.doAnimationFrame(animationHandler.getProvider().getFrameTime());
        if (AnimationHandler.this.mAnimationCallbacks.size() > 0)
          AnimationHandler.this.getProvider().postFrameCallback(this); 
      }
    };
  
  private boolean mListDirty = false;
  
  private AnimationFrameCallbackProvider mProvider;
  
  private void cleanUpList() {
    if (this.mListDirty) {
      for (int i = this.mAnimationCallbacks.size() - 1; i >= 0; i--) {
        if (this.mAnimationCallbacks.get(i) == null)
          this.mAnimationCallbacks.remove(i); 
      } 
      this.mListDirty = false;
    } 
  }
  
  private void commitAnimationFrame(AnimationFrameCallback paramAnimationFrameCallback, long paramLong) {
    if (!this.mDelayedCallbackStartTime.containsKey(paramAnimationFrameCallback) && this.mCommitCallbacks.contains(paramAnimationFrameCallback)) {
      paramAnimationFrameCallback.commitAnimationFrame(paramLong);
      this.mCommitCallbacks.remove(paramAnimationFrameCallback);
    } 
  }
  
  private void doAnimationFrame(long paramLong) {
    long l = SystemClock.uptimeMillis();
    int i = this.mAnimationCallbacks.size();
    for (byte b = 0; b < i; b++) {
      final AnimationFrameCallback callback = this.mAnimationCallbacks.get(b);
      if (animationFrameCallback != null && isCallbackDue(animationFrameCallback, l)) {
        animationFrameCallback.doAnimationFrame(paramLong);
        if (this.mCommitCallbacks.contains(animationFrameCallback))
          getProvider().postCommitCallback(new Runnable() {
                public void run() {
                  AnimationHandler animationHandler = AnimationHandler.this;
                  animationHandler.commitAnimationFrame(callback, animationHandler.getProvider().getFrameTime());
                }
              }); 
      } 
    } 
    cleanUpList();
  }
  
  public static int getAnimationCount() {
    AnimationHandler animationHandler = sAnimatorHandler.get();
    return (animationHandler == null) ? 0 : animationHandler.getCallbackSize();
  }
  
  private int getCallbackSize() {
    int i = 0;
    int j = this.mAnimationCallbacks.size() - 1;
    while (j >= 0) {
      int k = i;
      if (this.mAnimationCallbacks.get(j) != null)
        k = i + 1; 
      j--;
      i = k;
    } 
    return i;
  }
  
  public static long getFrameDelay() {
    return getInstance().getProvider().getFrameDelay();
  }
  
  public static AnimationHandler getInstance() {
    if (sAnimatorHandler.get() == null)
      sAnimatorHandler.set(new AnimationHandler()); 
    return sAnimatorHandler.get();
  }
  
  private AnimationFrameCallbackProvider getProvider() {
    if (this.mProvider == null)
      this.mProvider = new MyFrameCallbackProvider(); 
    return this.mProvider;
  }
  
  private boolean isCallbackDue(AnimationFrameCallback paramAnimationFrameCallback, long paramLong) {
    Long long_ = (Long)this.mDelayedCallbackStartTime.get(paramAnimationFrameCallback);
    if (long_ == null)
      return true; 
    if (long_.longValue() < paramLong) {
      this.mDelayedCallbackStartTime.remove(paramAnimationFrameCallback);
      return true;
    } 
    return false;
  }
  
  public static void setFrameDelay(long paramLong) {
    getInstance().getProvider().setFrameDelay(paramLong);
  }
  
  public void addAnimationFrameCallback(AnimationFrameCallback paramAnimationFrameCallback, long paramLong) {
    if (this.mAnimationCallbacks.size() == 0)
      getProvider().postFrameCallback(this.mFrameCallback); 
    if (!this.mAnimationCallbacks.contains(paramAnimationFrameCallback))
      this.mAnimationCallbacks.add(paramAnimationFrameCallback); 
    if (paramLong > 0L)
      this.mDelayedCallbackStartTime.put(paramAnimationFrameCallback, Long.valueOf(SystemClock.uptimeMillis() + paramLong)); 
  }
  
  public void addOneShotCommitCallback(AnimationFrameCallback paramAnimationFrameCallback) {
    if (!this.mCommitCallbacks.contains(paramAnimationFrameCallback))
      this.mCommitCallbacks.add(paramAnimationFrameCallback); 
  }
  
  void autoCancelBasedOn(ObjectAnimator paramObjectAnimator) {
    for (int i = this.mAnimationCallbacks.size() - 1; i >= 0; i--) {
      AnimationFrameCallback animationFrameCallback = this.mAnimationCallbacks.get(i);
      if (animationFrameCallback != null && paramObjectAnimator.shouldAutoCancel(animationFrameCallback))
        ((Animator)this.mAnimationCallbacks.get(i)).cancel(); 
    } 
  }
  
  public void removeCallback(AnimationFrameCallback paramAnimationFrameCallback) {
    this.mCommitCallbacks.remove(paramAnimationFrameCallback);
    this.mDelayedCallbackStartTime.remove(paramAnimationFrameCallback);
    int i = this.mAnimationCallbacks.indexOf(paramAnimationFrameCallback);
    if (i >= 0) {
      this.mAnimationCallbacks.set(i, null);
      this.mListDirty = true;
    } 
  }
  
  public void setProvider(AnimationFrameCallbackProvider paramAnimationFrameCallbackProvider) {
    if (paramAnimationFrameCallbackProvider == null) {
      this.mProvider = new MyFrameCallbackProvider();
    } else {
      this.mProvider = paramAnimationFrameCallbackProvider;
    } 
  }
  
  static interface AnimationFrameCallback {
    void commitAnimationFrame(long param1Long);
    
    boolean doAnimationFrame(long param1Long);
  }
  
  public static interface AnimationFrameCallbackProvider {
    long getFrameDelay();
    
    long getFrameTime();
    
    void postCommitCallback(Runnable param1Runnable);
    
    void postFrameCallback(Choreographer.FrameCallback param1FrameCallback);
    
    void setFrameDelay(long param1Long);
  }
  
  private class MyFrameCallbackProvider implements AnimationFrameCallbackProvider {
    final Choreographer mChoreographer = Choreographer.getInstance();
    
    private MyFrameCallbackProvider() {}
    
    public long getFrameDelay() {
      return Choreographer.getFrameDelay();
    }
    
    public long getFrameTime() {
      return this.mChoreographer.getFrameTime();
    }
    
    public void postCommitCallback(Runnable param1Runnable) {
      this.mChoreographer.postCallback(4, param1Runnable, null);
    }
    
    public void postFrameCallback(Choreographer.FrameCallback param1FrameCallback) {
      this.mChoreographer.postFrameCallback(param1FrameCallback);
    }
    
    public void setFrameDelay(long param1Long) {
      Choreographer.setFrameDelay(param1Long);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimationHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
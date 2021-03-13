package android.animation;

import android.content.res.ConstantState;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Animator implements Cloneable {
  public static final long DURATION_INFINITE = -1L;
  
  int mChangingConfigurations = 0;
  
  private AnimatorConstantState mConstantState;
  
  ArrayList<AnimatorListener> mListeners = null;
  
  ArrayList<AnimatorPauseListener> mPauseListeners = null;
  
  boolean mPaused = false;
  
  public void addListener(AnimatorListener paramAnimatorListener) {
    if (this.mListeners == null)
      this.mListeners = new ArrayList<>(); 
    this.mListeners.add(paramAnimatorListener);
  }
  
  public void addPauseListener(AnimatorPauseListener paramAnimatorPauseListener) {
    if (this.mPauseListeners == null)
      this.mPauseListeners = new ArrayList<>(); 
    this.mPauseListeners.add(paramAnimatorPauseListener);
  }
  
  void animateBasedOnPlayTime(long paramLong1, long paramLong2, boolean paramBoolean) {}
  
  public void appendChangingConfigurations(int paramInt) {
    this.mChangingConfigurations |= paramInt;
  }
  
  public boolean canReverse() {
    return false;
  }
  
  public void cancel() {}
  
  public Animator clone() {
    try {
      Animator animator = (Animator)super.clone();
      if (this.mListeners != null) {
        ArrayList<AnimatorListener> arrayList = new ArrayList();
        this((Collection)this.mListeners);
        animator.mListeners = arrayList;
      } 
      if (this.mPauseListeners != null) {
        ArrayList<AnimatorPauseListener> arrayList = new ArrayList();
        this((Collection)this.mPauseListeners);
        animator.mPauseListeners = arrayList;
      } 
      return animator;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new AssertionError();
    } 
  }
  
  public ConstantState<Animator> createConstantState() {
    return new AnimatorConstantState(this);
  }
  
  public void end() {}
  
  public int getChangingConfigurations() {
    return this.mChangingConfigurations;
  }
  
  public abstract long getDuration();
  
  public TimeInterpolator getInterpolator() {
    return null;
  }
  
  public ArrayList<AnimatorListener> getListeners() {
    return this.mListeners;
  }
  
  public abstract long getStartDelay();
  
  public long getTotalDuration() {
    long l = getDuration();
    return (l == -1L) ? -1L : (getStartDelay() + l);
  }
  
  boolean isInitialized() {
    return true;
  }
  
  public boolean isPaused() {
    return this.mPaused;
  }
  
  public abstract boolean isRunning();
  
  public boolean isStarted() {
    return isRunning();
  }
  
  public void pause() {
    if (isStarted() && !this.mPaused) {
      this.mPaused = true;
      ArrayList<AnimatorPauseListener> arrayList = this.mPauseListeners;
      if (arrayList != null) {
        arrayList = (ArrayList<AnimatorPauseListener>)arrayList.clone();
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((AnimatorPauseListener)arrayList.get(b)).onAnimationPause(this); 
      } 
    } 
  }
  
  boolean pulseAnimationFrame(long paramLong) {
    return false;
  }
  
  public void removeAllListeners() {
    ArrayList<AnimatorListener> arrayList1 = this.mListeners;
    if (arrayList1 != null) {
      arrayList1.clear();
      this.mListeners = null;
    } 
    ArrayList<AnimatorPauseListener> arrayList = this.mPauseListeners;
    if (arrayList != null) {
      arrayList.clear();
      this.mPauseListeners = null;
    } 
  }
  
  public void removeListener(AnimatorListener paramAnimatorListener) {
    ArrayList<AnimatorListener> arrayList = this.mListeners;
    if (arrayList == null)
      return; 
    arrayList.remove(paramAnimatorListener);
    if (this.mListeners.size() == 0)
      this.mListeners = null; 
  }
  
  public void removePauseListener(AnimatorPauseListener paramAnimatorPauseListener) {
    ArrayList<AnimatorPauseListener> arrayList = this.mPauseListeners;
    if (arrayList == null)
      return; 
    arrayList.remove(paramAnimatorPauseListener);
    if (this.mPauseListeners.size() == 0)
      this.mPauseListeners = null; 
  }
  
  public void resume() {
    if (this.mPaused) {
      this.mPaused = false;
      ArrayList<AnimatorPauseListener> arrayList = this.mPauseListeners;
      if (arrayList != null) {
        arrayList = (ArrayList<AnimatorPauseListener>)arrayList.clone();
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((AnimatorPauseListener)arrayList.get(b)).onAnimationResume(this); 
      } 
    } 
  }
  
  public void reverse() {
    throw new IllegalStateException("Reverse is not supported");
  }
  
  public void setAllowRunningAsynchronously(boolean paramBoolean) {}
  
  public void setChangingConfigurations(int paramInt) {
    this.mChangingConfigurations = paramInt;
  }
  
  public abstract Animator setDuration(long paramLong);
  
  public abstract void setInterpolator(TimeInterpolator paramTimeInterpolator);
  
  public abstract void setStartDelay(long paramLong);
  
  public void setTarget(Object paramObject) {}
  
  public void setupEndValues() {}
  
  public void setupStartValues() {}
  
  void skipToEndValue(boolean paramBoolean) {}
  
  public void start() {}
  
  void startWithoutPulsing(boolean paramBoolean) {
    if (paramBoolean) {
      reverse();
    } else {
      start();
    } 
  }
  
  private static class AnimatorConstantState extends ConstantState<Animator> {
    final Animator mAnimator;
    
    int mChangingConf;
    
    public AnimatorConstantState(Animator param1Animator) {
      this.mAnimator = param1Animator;
      Animator.access$002(param1Animator, this);
      this.mChangingConf = this.mAnimator.getChangingConfigurations();
    }
    
    public int getChangingConfigurations() {
      return this.mChangingConf;
    }
    
    public Animator newInstance() {
      Animator animator = this.mAnimator.clone();
      Animator.access$002(animator, this);
      return animator;
    }
  }
  
  public static interface AnimatorListener {
    void onAnimationCancel(Animator param1Animator);
    
    void onAnimationEnd(Animator param1Animator);
    
    default void onAnimationEnd(Animator param1Animator, boolean param1Boolean) {
      onAnimationEnd(param1Animator);
    }
    
    void onAnimationRepeat(Animator param1Animator);
    
    void onAnimationStart(Animator param1Animator);
    
    default void onAnimationStart(Animator param1Animator, boolean param1Boolean) {
      onAnimationStart(param1Animator);
    }
  }
  
  public static interface AnimatorPauseListener {
    void onAnimationPause(Animator param1Animator);
    
    void onAnimationResume(Animator param1Animator);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
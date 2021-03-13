package android.animation;

import android.os.Looper;
import android.os.Trace;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ValueAnimator extends Animator implements AnimationHandler.AnimationFrameCallback {
  private static final boolean DEBUG = false;
  
  public static final int INFINITE = -1;
  
  public static final int RESTART = 1;
  
  public static final int REVERSE = 2;
  
  private static final String TAG = "ValueAnimator";
  
  private static final TimeInterpolator sDefaultInterpolator;
  
  private static float sDurationScale = 1.0F;
  
  private boolean mAnimationEndRequested = false;
  
  private AnimationHandler mAnimationHandler;
  
  private float mCurrentFraction = 0.0F;
  
  private long mDuration = 300L;
  
  private float mDurationScale = -1.0F;
  
  private long mFirstFrameTime = -1L;
  
  boolean mInitialized = false;
  
  private TimeInterpolator mInterpolator = sDefaultInterpolator;
  
  private long mLastFrameTime = -1L;
  
  private float mOverallFraction = 0.0F;
  
  private long mPauseTime;
  
  private int mRepeatCount = 0;
  
  private int mRepeatMode = 1;
  
  private boolean mResumed = false;
  
  private boolean mReversing;
  
  private boolean mRunning = false;
  
  float mSeekFraction = -1.0F;
  
  private boolean mSelfPulse = true;
  
  private long mStartDelay = 0L;
  
  private boolean mStartListenersCalled = false;
  
  long mStartTime = -1L;
  
  boolean mStartTimeCommitted;
  
  private boolean mStarted = false;
  
  private boolean mSuppressSelfPulseRequested = false;
  
  ArrayList<AnimatorUpdateListener> mUpdateListeners = null;
  
  PropertyValuesHolder[] mValues;
  
  HashMap<String, PropertyValuesHolder> mValuesMap;
  
  static {
    sDefaultInterpolator = (TimeInterpolator)new AccelerateDecelerateInterpolator();
  }
  
  private void addAnimationCallback(long paramLong) {
    if (!this.mSelfPulse)
      return; 
    getAnimationHandler().addAnimationFrameCallback(this, paramLong);
  }
  
  private void addOneShotCommitCallback() {
    if (!this.mSelfPulse)
      return; 
    getAnimationHandler().addOneShotCommitCallback(this);
  }
  
  public static boolean areAnimatorsEnabled() {
    boolean bool;
    if (sDurationScale != 0.0F) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private float clampFraction(float paramFloat) {
    float f;
    if (paramFloat < 0.0F) {
      f = 0.0F;
    } else {
      int i = this.mRepeatCount;
      f = paramFloat;
      if (i != -1)
        f = Math.min(paramFloat, (i + 1)); 
    } 
    return f;
  }
  
  private void endAnimation() {
    if (this.mAnimationEndRequested)
      return; 
    removeAnimationCallback();
    byte b = 1;
    this.mAnimationEndRequested = true;
    this.mPaused = false;
    if ((!this.mStarted && !this.mRunning) || this.mListeners == null)
      b = 0; 
    if (b && !this.mRunning)
      notifyStartListeners(); 
    this.mRunning = false;
    this.mStarted = false;
    this.mStartListenersCalled = false;
    this.mLastFrameTime = -1L;
    this.mFirstFrameTime = -1L;
    this.mStartTime = -1L;
    if (b && this.mListeners != null) {
      ArrayList<Animator.AnimatorListener> arrayList = (ArrayList)this.mListeners.clone();
      int i = arrayList.size();
      for (b = 0; b < i; b++)
        ((Animator.AnimatorListener)arrayList.get(b)).onAnimationEnd(this, this.mReversing); 
    } 
    this.mReversing = false;
    if (Trace.isTagEnabled(8L))
      Trace.asyncTraceEnd(8L, getNameForTrace(), System.identityHashCode(this)); 
  }
  
  public static int getCurrentAnimationsCount() {
    return AnimationHandler.getAnimationCount();
  }
  
  private int getCurrentIteration(float paramFloat) {
    paramFloat = clampFraction(paramFloat);
    double d1 = Math.floor(paramFloat);
    double d2 = d1;
    if (paramFloat == d1) {
      d2 = d1;
      if (paramFloat > 0.0F)
        d2 = d1 - 1.0D; 
    } 
    return (int)d2;
  }
  
  private float getCurrentIterationFraction(float paramFloat, boolean paramBoolean) {
    paramFloat = clampFraction(paramFloat);
    int i = getCurrentIteration(paramFloat);
    paramFloat -= i;
    if (shouldPlayBackward(i, paramBoolean))
      paramFloat = 1.0F - paramFloat; 
    return paramFloat;
  }
  
  public static float getDurationScale() {
    return sDurationScale;
  }
  
  public static long getFrameDelay() {
    AnimationHandler.getInstance();
    return AnimationHandler.getFrameDelay();
  }
  
  private long getScaledDuration() {
    return (long)((float)this.mDuration * resolveDurationScale());
  }
  
  private boolean isPulsingInternal() {
    boolean bool;
    if (this.mLastFrameTime >= 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void notifyStartListeners() {
    if (this.mListeners != null && !this.mStartListenersCalled) {
      ArrayList<Animator.AnimatorListener> arrayList = (ArrayList)this.mListeners.clone();
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Animator.AnimatorListener)arrayList.get(b)).onAnimationStart(this, this.mReversing); 
    } 
    this.mStartListenersCalled = true;
  }
  
  public static ValueAnimator ofArgb(int... paramVarArgs) {
    ValueAnimator valueAnimator = new ValueAnimator();
    valueAnimator.setIntValues(paramVarArgs);
    valueAnimator.setEvaluator(ArgbEvaluator.getInstance());
    return valueAnimator;
  }
  
  public static ValueAnimator ofFloat(float... paramVarArgs) {
    ValueAnimator valueAnimator = new ValueAnimator();
    valueAnimator.setFloatValues(paramVarArgs);
    return valueAnimator;
  }
  
  public static ValueAnimator ofInt(int... paramVarArgs) {
    ValueAnimator valueAnimator = new ValueAnimator();
    valueAnimator.setIntValues(paramVarArgs);
    return valueAnimator;
  }
  
  public static ValueAnimator ofObject(TypeEvaluator paramTypeEvaluator, Object... paramVarArgs) {
    ValueAnimator valueAnimator = new ValueAnimator();
    valueAnimator.setObjectValues(paramVarArgs);
    valueAnimator.setEvaluator(paramTypeEvaluator);
    return valueAnimator;
  }
  
  public static ValueAnimator ofPropertyValuesHolder(PropertyValuesHolder... paramVarArgs) {
    ValueAnimator valueAnimator = new ValueAnimator();
    valueAnimator.setValues(paramVarArgs);
    return valueAnimator;
  }
  
  private void removeAnimationCallback() {
    if (!this.mSelfPulse)
      return; 
    getAnimationHandler().removeCallback(this);
  }
  
  private float resolveDurationScale() {
    float f = this.mDurationScale;
    if (f < 0.0F)
      f = sDurationScale; 
    return f;
  }
  
  public static void setDurationScale(float paramFloat) {
    sDurationScale = paramFloat;
  }
  
  public static void setFrameDelay(long paramLong) {
    AnimationHandler.getInstance();
    AnimationHandler.setFrameDelay(paramLong);
  }
  
  private boolean shouldPlayBackward(int paramInt, boolean paramBoolean) {
    if (paramInt > 0 && this.mRepeatMode == 2) {
      int i = this.mRepeatCount;
      if (paramInt < i + 1 || i == -1) {
        boolean bool1 = false;
        boolean bool2 = false;
        if (paramBoolean) {
          paramBoolean = bool2;
          if (paramInt % 2 == 0)
            paramBoolean = true; 
          return paramBoolean;
        } 
        paramBoolean = bool1;
        if (paramInt % 2 != 0)
          paramBoolean = true; 
        return paramBoolean;
      } 
    } 
    return paramBoolean;
  }
  
  private void start(boolean paramBoolean) {
    if (Looper.myLooper() != null) {
      this.mReversing = paramBoolean;
      this.mSelfPulse = this.mSuppressSelfPulseRequested ^ true;
      if (paramBoolean) {
        float f = this.mSeekFraction;
        if (f != -1.0F && f != 0.0F) {
          int i = this.mRepeatCount;
          if (i == -1) {
            this.mSeekFraction = 1.0F - (float)(f - Math.floor(f));
          } else {
            this.mSeekFraction = (i + 1) - f;
          } 
        } 
      } 
      this.mStarted = true;
      this.mPaused = false;
      this.mRunning = false;
      this.mAnimationEndRequested = false;
      this.mLastFrameTime = -1L;
      this.mFirstFrameTime = -1L;
      this.mStartTime = -1L;
      addAnimationCallback(0L);
      if (this.mStartDelay == 0L || this.mSeekFraction >= 0.0F || this.mReversing) {
        startAnimation();
        float f = this.mSeekFraction;
        if (f == -1.0F) {
          setCurrentPlayTime(0L);
        } else {
          setCurrentFraction(f);
        } 
      } 
      return;
    } 
    throw new AndroidRuntimeException("Animators may only be run on Looper threads");
  }
  
  private void startAnimation() {
    if (Trace.isTagEnabled(8L))
      Trace.asyncTraceBegin(8L, getNameForTrace(), System.identityHashCode(this)); 
    this.mAnimationEndRequested = false;
    initAnimation();
    this.mRunning = true;
    float f = this.mSeekFraction;
    if (f >= 0.0F) {
      this.mOverallFraction = f;
    } else {
      this.mOverallFraction = 0.0F;
    } 
    if (this.mListeners != null)
      notifyStartListeners(); 
  }
  
  public void addUpdateListener(AnimatorUpdateListener paramAnimatorUpdateListener) {
    if (this.mUpdateListeners == null)
      this.mUpdateListeners = new ArrayList<>(); 
    this.mUpdateListeners.add(paramAnimatorUpdateListener);
  }
  
  void animateBasedOnPlayTime(long paramLong1, long paramLong2, boolean paramBoolean) {
    if (paramLong1 >= 0L && paramLong2 >= 0L) {
      initAnimation();
      int i = this.mRepeatCount;
      if (i > 0) {
        long l = this.mDuration;
        int k = (int)(paramLong1 / l);
        int m = (int)(paramLong2 / l);
        if (Math.min(k, i) != Math.min(m, this.mRepeatCount) && this.mListeners != null) {
          i = this.mListeners.size();
          for (k = 0; k < i; k++)
            ((Animator.AnimatorListener)this.mListeners.get(k)).onAnimationRepeat(this); 
        } 
      } 
      int j = this.mRepeatCount;
      if (j != -1 && paramLong1 >= (j + 1) * this.mDuration) {
        skipToEndValue(paramBoolean);
      } else {
        animateValue(getCurrentIterationFraction((float)paramLong1 / (float)this.mDuration, paramBoolean));
      } 
      return;
    } 
    throw new UnsupportedOperationException("Error: Play time should never be negative.");
  }
  
  boolean animateBasedOnTime(long paramLong) {
    boolean bool1 = false;
    boolean bool2 = false;
    if (this.mRunning) {
      long l = getScaledDuration();
      if (l > 0L) {
        f1 = (float)(paramLong - this.mStartTime) / (float)l;
      } else {
        f1 = 1.0F;
      } 
      float f2 = this.mOverallFraction;
      int i = (int)f1;
      int j = (int)f2;
      boolean bool = false;
      if (i > j) {
        j = 1;
      } else {
        j = 0;
      } 
      int k = this.mRepeatCount;
      i = bool;
      if (f1 >= (k + 1)) {
        i = bool;
        if (k != -1)
          i = 1; 
      } 
      if (l == 0L) {
        bool1 = true;
      } else if (j != 0 && i == 0) {
        bool1 = bool2;
        if (this.mListeners != null) {
          i = this.mListeners.size();
          for (j = 0; j < i; j++)
            ((Animator.AnimatorListener)this.mListeners.get(j)).onAnimationRepeat(this); 
          bool1 = bool2;
        } 
      } else {
        bool1 = bool2;
        if (i != 0)
          bool1 = true; 
      } 
      float f1 = clampFraction(f1);
      this.mOverallFraction = f1;
      animateValue(getCurrentIterationFraction(f1, this.mReversing));
    } 
    return bool1;
  }
  
  void animateValue(float paramFloat) {
    paramFloat = this.mInterpolator.getInterpolation(paramFloat);
    this.mCurrentFraction = paramFloat;
    int i = this.mValues.length;
    byte b;
    for (b = 0; b < i; b++)
      this.mValues[b].calculateValue(paramFloat); 
    ArrayList<AnimatorUpdateListener> arrayList = this.mUpdateListeners;
    if (arrayList != null) {
      i = arrayList.size();
      for (b = 0; b < i; b++)
        ((AnimatorUpdateListener)this.mUpdateListeners.get(b)).onAnimationUpdate(this); 
    } 
  }
  
  public boolean canReverse() {
    return true;
  }
  
  public void cancel() {
    if (Looper.myLooper() != null) {
      if (this.mAnimationEndRequested)
        return; 
      if ((this.mStarted || this.mRunning) && this.mListeners != null) {
        if (!this.mRunning)
          notifyStartListeners(); 
        Iterator<Animator.AnimatorListener> iterator = ((ArrayList)this.mListeners.clone()).iterator();
        while (iterator.hasNext())
          ((Animator.AnimatorListener)iterator.next()).onAnimationCancel(this); 
      } 
      endAnimation();
      return;
    } 
    throw new AndroidRuntimeException("Animators may only be run on Looper threads");
  }
  
  public ValueAnimator clone() {
    ValueAnimator valueAnimator = (ValueAnimator)super.clone();
    if (this.mUpdateListeners != null)
      valueAnimator.mUpdateListeners = new ArrayList<>(this.mUpdateListeners); 
    valueAnimator.mSeekFraction = -1.0F;
    valueAnimator.mReversing = false;
    valueAnimator.mInitialized = false;
    valueAnimator.mStarted = false;
    valueAnimator.mRunning = false;
    valueAnimator.mPaused = false;
    valueAnimator.mResumed = false;
    valueAnimator.mStartListenersCalled = false;
    valueAnimator.mStartTime = -1L;
    valueAnimator.mStartTimeCommitted = false;
    valueAnimator.mAnimationEndRequested = false;
    valueAnimator.mPauseTime = -1L;
    valueAnimator.mLastFrameTime = -1L;
    valueAnimator.mFirstFrameTime = -1L;
    valueAnimator.mOverallFraction = 0.0F;
    valueAnimator.mCurrentFraction = 0.0F;
    valueAnimator.mSelfPulse = true;
    valueAnimator.mSuppressSelfPulseRequested = false;
    PropertyValuesHolder[] arrayOfPropertyValuesHolder = this.mValues;
    if (arrayOfPropertyValuesHolder != null) {
      int i = arrayOfPropertyValuesHolder.length;
      valueAnimator.mValues = new PropertyValuesHolder[i];
      valueAnimator.mValuesMap = new HashMap<>(i);
      for (byte b = 0; b < i; b++) {
        PropertyValuesHolder propertyValuesHolder = arrayOfPropertyValuesHolder[b].clone();
        valueAnimator.mValues[b] = propertyValuesHolder;
        valueAnimator.mValuesMap.put(propertyValuesHolder.getPropertyName(), propertyValuesHolder);
      } 
    } 
    return valueAnimator;
  }
  
  public void commitAnimationFrame(long paramLong) {
    if (!this.mStartTimeCommitted) {
      this.mStartTimeCommitted = true;
      paramLong -= this.mLastFrameTime;
      if (paramLong > 0L)
        this.mStartTime += paramLong; 
    } 
  }
  
  public final boolean doAnimationFrame(long paramLong) {
    if (this.mStartTime < 0L) {
      long l;
      if (this.mReversing) {
        l = paramLong;
      } else {
        l = (long)((float)this.mStartDelay * resolveDurationScale()) + paramLong;
      } 
      this.mStartTime = l;
    } 
    if (this.mPaused) {
      this.mPauseTime = paramLong;
      removeAnimationCallback();
      return false;
    } 
    if (this.mResumed) {
      this.mResumed = false;
      long l = this.mPauseTime;
      if (l > 0L)
        this.mStartTime += paramLong - l; 
    } 
    if (!this.mRunning) {
      if (this.mStartTime > paramLong && this.mSeekFraction == -1.0F)
        return false; 
      this.mRunning = true;
      startAnimation();
    } 
    if (this.mLastFrameTime < 0L) {
      if (this.mSeekFraction >= 0.0F) {
        this.mStartTime = paramLong - (long)((float)getScaledDuration() * this.mSeekFraction);
        this.mSeekFraction = -1.0F;
      } 
      this.mStartTimeCommitted = false;
    } 
    this.mLastFrameTime = paramLong;
    boolean bool = animateBasedOnTime(Math.max(paramLong, this.mStartTime));
    if (bool)
      endAnimation(); 
    return bool;
  }
  
  public void end() {
    if (Looper.myLooper() != null) {
      float f;
      if (!this.mRunning) {
        startAnimation();
        this.mStarted = true;
      } else if (!this.mInitialized) {
        initAnimation();
      } 
      if (shouldPlayBackward(this.mRepeatCount, this.mReversing)) {
        f = 0.0F;
      } else {
        f = 1.0F;
      } 
      animateValue(f);
      endAnimation();
      return;
    } 
    throw new AndroidRuntimeException("Animators may only be run on Looper threads");
  }
  
  public float getAnimatedFraction() {
    return this.mCurrentFraction;
  }
  
  public Object getAnimatedValue() {
    PropertyValuesHolder[] arrayOfPropertyValuesHolder = this.mValues;
    return (arrayOfPropertyValuesHolder != null && arrayOfPropertyValuesHolder.length > 0) ? arrayOfPropertyValuesHolder[0].getAnimatedValue() : null;
  }
  
  public Object getAnimatedValue(String paramString) {
    PropertyValuesHolder propertyValuesHolder = this.mValuesMap.get(paramString);
    return (propertyValuesHolder != null) ? propertyValuesHolder.getAnimatedValue() : null;
  }
  
  public AnimationHandler getAnimationHandler() {
    AnimationHandler animationHandler = this.mAnimationHandler;
    if (animationHandler == null)
      animationHandler = AnimationHandler.getInstance(); 
    return animationHandler;
  }
  
  public long getCurrentPlayTime() {
    if (!this.mInitialized || (!this.mStarted && this.mSeekFraction < 0.0F))
      return 0L; 
    float f1 = this.mSeekFraction;
    if (f1 >= 0.0F)
      return (long)((float)this.mDuration * f1); 
    float f2 = resolveDurationScale();
    f1 = f2;
    if (f2 == 0.0F)
      f1 = 1.0F; 
    return (long)((float)(AnimationUtils.currentAnimationTimeMillis() - this.mStartTime) / f1);
  }
  
  public long getDuration() {
    return this.mDuration;
  }
  
  public TimeInterpolator getInterpolator() {
    return this.mInterpolator;
  }
  
  String getNameForTrace() {
    return "animator";
  }
  
  public int getRepeatCount() {
    return this.mRepeatCount;
  }
  
  public int getRepeatMode() {
    return this.mRepeatMode;
  }
  
  public long getStartDelay() {
    return this.mStartDelay;
  }
  
  public long getTotalDuration() {
    int i = this.mRepeatCount;
    return (i == -1) ? -1L : (this.mStartDelay + this.mDuration * (i + 1));
  }
  
  public PropertyValuesHolder[] getValues() {
    return this.mValues;
  }
  
  void initAnimation() {
    if (!this.mInitialized) {
      int i = this.mValues.length;
      for (byte b = 0; b < i; b++)
        this.mValues[b].init(); 
      this.mInitialized = true;
    } 
  }
  
  boolean isInitialized() {
    return this.mInitialized;
  }
  
  public boolean isRunning() {
    return this.mRunning;
  }
  
  public boolean isStarted() {
    return this.mStarted;
  }
  
  public void overrideDurationScale(float paramFloat) {
    this.mDurationScale = paramFloat;
  }
  
  public void pause() {
    boolean bool = this.mPaused;
    super.pause();
    if (!bool && this.mPaused) {
      this.mPauseTime = -1L;
      this.mResumed = false;
    } 
  }
  
  boolean pulseAnimationFrame(long paramLong) {
    return this.mSelfPulse ? false : doAnimationFrame(paramLong);
  }
  
  public void removeAllUpdateListeners() {
    ArrayList<AnimatorUpdateListener> arrayList = this.mUpdateListeners;
    if (arrayList == null)
      return; 
    arrayList.clear();
    this.mUpdateListeners = null;
  }
  
  public void removeUpdateListener(AnimatorUpdateListener paramAnimatorUpdateListener) {
    ArrayList<AnimatorUpdateListener> arrayList = this.mUpdateListeners;
    if (arrayList == null)
      return; 
    arrayList.remove(paramAnimatorUpdateListener);
    if (this.mUpdateListeners.size() == 0)
      this.mUpdateListeners = null; 
  }
  
  public void resume() {
    if (Looper.myLooper() != null) {
      if (this.mPaused && !this.mResumed) {
        this.mResumed = true;
        if (this.mPauseTime > 0L)
          addAnimationCallback(0L); 
      } 
      super.resume();
      return;
    } 
    throw new AndroidRuntimeException("Animators may only be resumed from the same thread that the animator was started on");
  }
  
  public void reverse() {
    if (isPulsingInternal()) {
      long l1 = AnimationUtils.currentAnimationTimeMillis();
      long l2 = this.mStartTime;
      this.mStartTime = l1 - getScaledDuration() - l1 - l2;
      this.mStartTimeCommitted = true;
      this.mReversing ^= 0x1;
    } else if (this.mStarted) {
      this.mReversing ^= 0x1;
      end();
    } else {
      start(true);
    } 
  }
  
  public void setAllowRunningAsynchronously(boolean paramBoolean) {}
  
  public void setAnimationHandler(AnimationHandler paramAnimationHandler) {
    this.mAnimationHandler = paramAnimationHandler;
  }
  
  public void setCurrentFraction(float paramFloat) {
    initAnimation();
    paramFloat = clampFraction(paramFloat);
    this.mStartTimeCommitted = true;
    if (isPulsingInternal()) {
      long l = (long)((float)getScaledDuration() * paramFloat);
      this.mStartTime = AnimationUtils.currentAnimationTimeMillis() - l;
    } else {
      this.mSeekFraction = paramFloat;
    } 
    this.mOverallFraction = paramFloat;
    animateValue(getCurrentIterationFraction(paramFloat, this.mReversing));
  }
  
  public void setCurrentPlayTime(long paramLong) {
    float f;
    long l = this.mDuration;
    if (l > 0L) {
      f = (float)paramLong / (float)l;
    } else {
      f = 1.0F;
    } 
    setCurrentFraction(f);
  }
  
  public ValueAnimator setDuration(long paramLong) {
    if (paramLong >= 0L) {
      this.mDuration = paramLong;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Animators cannot have negative duration: ");
    stringBuilder.append(paramLong);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setEvaluator(TypeEvaluator paramTypeEvaluator) {
    if (paramTypeEvaluator != null) {
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = this.mValues;
      if (arrayOfPropertyValuesHolder != null && arrayOfPropertyValuesHolder.length > 0)
        arrayOfPropertyValuesHolder[0].setEvaluator(paramTypeEvaluator); 
    } 
  }
  
  public void setFloatValues(float... paramVarArgs) {
    if (paramVarArgs == null || paramVarArgs.length == 0)
      return; 
    PropertyValuesHolder[] arrayOfPropertyValuesHolder = this.mValues;
    if (arrayOfPropertyValuesHolder == null || arrayOfPropertyValuesHolder.length == 0) {
      setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat("", paramVarArgs) });
    } else {
      arrayOfPropertyValuesHolder[0].setFloatValues(paramVarArgs);
    } 
    this.mInitialized = false;
  }
  
  public void setIntValues(int... paramVarArgs) {
    if (paramVarArgs == null || paramVarArgs.length == 0)
      return; 
    PropertyValuesHolder[] arrayOfPropertyValuesHolder = this.mValues;
    if (arrayOfPropertyValuesHolder == null || arrayOfPropertyValuesHolder.length == 0) {
      setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofInt("", paramVarArgs) });
    } else {
      arrayOfPropertyValuesHolder[0].setIntValues(paramVarArgs);
    } 
    this.mInitialized = false;
  }
  
  public void setInterpolator(TimeInterpolator paramTimeInterpolator) {
    if (paramTimeInterpolator != null) {
      this.mInterpolator = paramTimeInterpolator;
    } else {
      this.mInterpolator = (TimeInterpolator)new LinearInterpolator();
    } 
  }
  
  public void setObjectValues(Object... paramVarArgs) {
    if (paramVarArgs == null || paramVarArgs.length == 0)
      return; 
    PropertyValuesHolder[] arrayOfPropertyValuesHolder = this.mValues;
    if (arrayOfPropertyValuesHolder == null || arrayOfPropertyValuesHolder.length == 0) {
      setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofObject("", (TypeEvaluator)null, paramVarArgs) });
    } else {
      arrayOfPropertyValuesHolder[0].setObjectValues(paramVarArgs);
    } 
    this.mInitialized = false;
  }
  
  public void setRepeatCount(int paramInt) {
    this.mRepeatCount = paramInt;
  }
  
  public void setRepeatMode(int paramInt) {
    this.mRepeatMode = paramInt;
  }
  
  public void setStartDelay(long paramLong) {
    long l = paramLong;
    if (paramLong < 0L) {
      Log.w("ValueAnimator", "Start delay should always be non-negative");
      l = 0L;
    } 
    this.mStartDelay = l;
  }
  
  public void setValues(PropertyValuesHolder... paramVarArgs) {
    int i = paramVarArgs.length;
    this.mValues = paramVarArgs;
    this.mValuesMap = new HashMap<>(i);
    for (byte b = 0; b < i; b++) {
      PropertyValuesHolder propertyValuesHolder = paramVarArgs[b];
      this.mValuesMap.put(propertyValuesHolder.getPropertyName(), propertyValuesHolder);
    } 
    this.mInitialized = false;
  }
  
  void skipToEndValue(boolean paramBoolean) {
    float f1;
    initAnimation();
    if (paramBoolean) {
      f1 = 0.0F;
    } else {
      f1 = 1.0F;
    } 
    float f2 = f1;
    if (this.mRepeatCount % 2 == 1) {
      f2 = f1;
      if (this.mRepeatMode == 2)
        f2 = 0.0F; 
    } 
    animateValue(f2);
  }
  
  public void start() {
    start(false);
  }
  
  void startWithoutPulsing(boolean paramBoolean) {
    this.mSuppressSelfPulseRequested = true;
    if (paramBoolean) {
      reverse();
    } else {
      start();
    } 
    this.mSuppressSelfPulseRequested = false;
  }
  
  public String toString() {
    StringBuilder stringBuilder2;
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("ValueAnimator@");
    stringBuilder1.append(Integer.toHexString(hashCode()));
    String str1 = stringBuilder1.toString();
    String str2 = str1;
    if (this.mValues != null) {
      byte b = 0;
      while (true) {
        str2 = str1;
        if (b < this.mValues.length) {
          stringBuilder2 = new StringBuilder();
          stringBuilder2.append(str1);
          stringBuilder2.append("\n    ");
          stringBuilder2.append(this.mValues[b].toString());
          str1 = stringBuilder2.toString();
          b++;
          continue;
        } 
        break;
      } 
    } 
    return (String)stringBuilder2;
  }
  
  public static interface AnimatorUpdateListener {
    void onAnimationUpdate(ValueAnimator param1ValueAnimator);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface RepeatMode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/ValueAnimator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
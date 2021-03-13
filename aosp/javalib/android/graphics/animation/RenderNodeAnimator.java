package android.graphics.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.CanvasProperty;
import android.graphics.Paint;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import com.android.internal.util.VirtualRefBasePtr;
import java.util.ArrayList;
import java.util.Objects;

public class RenderNodeAnimator extends Animator {
  public static final int ALPHA = 11;
  
  public static final int LAST_VALUE = 11;
  
  public static final int PAINT_ALPHA = 1;
  
  public static final int PAINT_STROKE_WIDTH = 0;
  
  public static final int ROTATION = 5;
  
  public static final int ROTATION_X = 6;
  
  public static final int ROTATION_Y = 7;
  
  public static final int SCALE_X = 3;
  
  public static final int SCALE_Y = 4;
  
  private static final int STATE_DELAYED = 1;
  
  private static final int STATE_FINISHED = 3;
  
  private static final int STATE_PREPARE = 0;
  
  private static final int STATE_RUNNING = 2;
  
  public static final int TRANSLATION_X = 0;
  
  public static final int TRANSLATION_Y = 1;
  
  public static final int TRANSLATION_Z = 2;
  
  public static final int X = 8;
  
  public static final int Y = 9;
  
  public static final int Z = 10;
  
  private static ThreadLocal<DelayedAnimationHelper> sAnimationHelper = new ThreadLocal<>();
  
  private float mFinalValue;
  
  private Handler mHandler;
  
  private TimeInterpolator mInterpolator;
  
  private VirtualRefBasePtr mNativePtr;
  
  private int mRenderProperty = -1;
  
  private long mStartDelay = 0L;
  
  private long mStartTime;
  
  private int mState = 0;
  
  private RenderNode mTarget;
  
  private final boolean mUiThreadHandlesDelay;
  
  private long mUnscaledDuration = 300L;
  
  private long mUnscaledStartDelay = 0L;
  
  private ViewListener mViewListener;
  
  public RenderNodeAnimator(int paramInt, float paramFloat) {
    this.mRenderProperty = paramInt;
    this.mFinalValue = paramFloat;
    this.mUiThreadHandlesDelay = true;
    init(nCreateAnimator(paramInt, paramFloat));
  }
  
  public RenderNodeAnimator(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) {
    init(nCreateRevealAnimator(paramInt1, paramInt2, paramFloat1, paramFloat2));
    this.mUiThreadHandlesDelay = true;
  }
  
  public RenderNodeAnimator(CanvasProperty<Float> paramCanvasProperty, float paramFloat) {
    init(nCreateCanvasPropertyFloatAnimator(paramCanvasProperty.getNativeContainer(), paramFloat));
    this.mUiThreadHandlesDelay = false;
  }
  
  public RenderNodeAnimator(CanvasProperty<Paint> paramCanvasProperty, int paramInt, float paramFloat) {
    init(nCreateCanvasPropertyPaintAnimator(paramCanvasProperty.getNativeContainer(), paramInt, paramFloat));
    this.mUiThreadHandlesDelay = false;
  }
  
  private void applyInterpolator() {
    long l;
    TimeInterpolator timeInterpolator = this.mInterpolator;
    if (timeInterpolator == null || this.mNativePtr == null)
      return; 
    if (isNativeInterpolator(timeInterpolator)) {
      l = ((NativeInterpolator)this.mInterpolator).createNativeInterpolator();
    } else {
      l = nGetDuration(this.mNativePtr.get());
      l = FallbackLUTInterpolator.createNativeInterpolator(this.mInterpolator, l);
    } 
    nSetInterpolator(this.mNativePtr.get(), l);
  }
  
  private static void callOnFinished(RenderNodeAnimator paramRenderNodeAnimator) {
    Handler handler = paramRenderNodeAnimator.mHandler;
    if (handler != null) {
      Objects.requireNonNull(paramRenderNodeAnimator);
      handler.post(new _$$Lambda$awqPSgriNRe12PWP0zkpAtPsfV4(paramRenderNodeAnimator));
    } else {
      handler = new Handler(Looper.getMainLooper(), null, true);
      Objects.requireNonNull(paramRenderNodeAnimator);
      handler.post(new _$$Lambda$awqPSgriNRe12PWP0zkpAtPsfV4(paramRenderNodeAnimator));
    } 
  }
  
  private void checkMutable() {
    if (this.mState == 0) {
      if (this.mNativePtr != null)
        return; 
      throw new IllegalStateException("Animator's target has been destroyed (trying to modify an animation after activity destroy?)");
    } 
    throw new IllegalStateException("Animator has already started, cannot change it now!");
  }
  
  private ArrayList<Animator.AnimatorListener> cloneListeners() {
    ArrayList<Animator.AnimatorListener> arrayList1 = getListeners();
    ArrayList<Animator.AnimatorListener> arrayList2 = arrayList1;
    if (arrayList1 != null)
      arrayList2 = (ArrayList)arrayList1.clone(); 
    return arrayList2;
  }
  
  private void doStart() {
    if (this.mRenderProperty == 11) {
      ViewListener viewListener1 = this.mViewListener;
      if (viewListener1 != null)
        viewListener1.onAlphaAnimationStart(this.mFinalValue); 
    } 
    moveToRunningState();
    ViewListener viewListener = this.mViewListener;
    if (viewListener != null)
      viewListener.invalidateParent(false); 
  }
  
  private static DelayedAnimationHelper getHelper() {
    DelayedAnimationHelper delayedAnimationHelper1 = sAnimationHelper.get();
    DelayedAnimationHelper delayedAnimationHelper2 = delayedAnimationHelper1;
    if (delayedAnimationHelper1 == null) {
      delayedAnimationHelper2 = new DelayedAnimationHelper();
      sAnimationHelper.set(delayedAnimationHelper2);
    } 
    return delayedAnimationHelper2;
  }
  
  private void init(long paramLong) {
    this.mNativePtr = new VirtualRefBasePtr(paramLong);
  }
  
  static boolean isNativeInterpolator(TimeInterpolator paramTimeInterpolator) {
    return paramTimeInterpolator.getClass().isAnnotationPresent((Class)HasNativeInterpolator.class);
  }
  
  private void moveToRunningState() {
    this.mState = 2;
    VirtualRefBasePtr virtualRefBasePtr = this.mNativePtr;
    if (virtualRefBasePtr != null)
      nStart(virtualRefBasePtr.get()); 
    notifyStartListeners();
  }
  
  private static native long nCreateAnimator(int paramInt, float paramFloat);
  
  private static native long nCreateCanvasPropertyFloatAnimator(long paramLong, float paramFloat);
  
  private static native long nCreateCanvasPropertyPaintAnimator(long paramLong, int paramInt, float paramFloat);
  
  private static native long nCreateRevealAnimator(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2);
  
  private static native void nEnd(long paramLong);
  
  private static native long nGetDuration(long paramLong);
  
  private static native void nSetAllowRunningAsync(long paramLong, boolean paramBoolean);
  
  private static native void nSetDuration(long paramLong1, long paramLong2);
  
  private static native void nSetInterpolator(long paramLong1, long paramLong2);
  
  private static native void nSetListener(long paramLong, RenderNodeAnimator paramRenderNodeAnimator);
  
  private static native void nSetStartDelay(long paramLong1, long paramLong2);
  
  private static native void nSetStartValue(long paramLong, float paramFloat);
  
  private static native void nStart(long paramLong);
  
  private void notifyStartListeners() {
    int i;
    ArrayList<Animator.AnimatorListener> arrayList = cloneListeners();
    if (arrayList == null) {
      i = 0;
    } else {
      i = arrayList.size();
    } 
    for (byte b = 0; b < i; b++)
      ((Animator.AnimatorListener)arrayList.get(b)).onAnimationStart(this); 
  }
  
  private boolean processDelayed(long paramLong) {
    long l = this.mStartTime;
    if (l == 0L) {
      this.mStartTime = paramLong;
    } else if (paramLong - l >= this.mStartDelay) {
      doStart();
      return true;
    } 
    return false;
  }
  
  private void releaseNativePtr() {
    VirtualRefBasePtr virtualRefBasePtr = this.mNativePtr;
    if (virtualRefBasePtr != null) {
      virtualRefBasePtr.release();
      this.mNativePtr = null;
    } 
  }
  
  public void cancel() {
    int i = this.mState;
    if (i != 0 && i != 3) {
      if (i == 1) {
        getHelper().removeDelayedAnimation(this);
        moveToRunningState();
      } 
      ArrayList<Animator.AnimatorListener> arrayList = cloneListeners();
      if (arrayList == null) {
        i = 0;
      } else {
        i = arrayList.size();
      } 
      for (byte b = 0; b < i; b++)
        ((Animator.AnimatorListener)arrayList.get(b)).onAnimationCancel(this); 
      end();
    } 
  }
  
  public Animator clone() {
    throw new IllegalStateException("Cannot clone this animator");
  }
  
  public void end() {
    int i = this.mState;
    if (i != 3) {
      if (i < 2) {
        getHelper().removeDelayedAnimation(this);
        doStart();
      } 
      VirtualRefBasePtr virtualRefBasePtr = this.mNativePtr;
      if (virtualRefBasePtr != null) {
        nEnd(virtualRefBasePtr.get());
        ViewListener viewListener = this.mViewListener;
        if (viewListener != null)
          viewListener.invalidateParent(false); 
      } else {
        onFinished();
      } 
    } 
  }
  
  public long getDuration() {
    return this.mUnscaledDuration;
  }
  
  public TimeInterpolator getInterpolator() {
    return this.mInterpolator;
  }
  
  public long getNativeAnimator() {
    return this.mNativePtr.get();
  }
  
  public long getStartDelay() {
    return this.mUnscaledStartDelay;
  }
  
  public long getTotalDuration() {
    return this.mUnscaledDuration + this.mUnscaledStartDelay;
  }
  
  public boolean isRunning() {
    int i = this.mState;
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (i != 1)
      if (i == 2) {
        bool2 = bool1;
      } else {
        bool2 = false;
      }  
    return bool2;
  }
  
  public boolean isStarted() {
    boolean bool;
    if (this.mState != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  protected void onFinished() {
    int i = this.mState;
    if (i == 0) {
      releaseNativePtr();
      return;
    } 
    if (i == 1) {
      getHelper().removeDelayedAnimation(this);
      notifyStartListeners();
    } 
    this.mState = 3;
    ArrayList<Animator.AnimatorListener> arrayList = cloneListeners();
    if (arrayList == null) {
      i = 0;
    } else {
      i = arrayList.size();
    } 
    for (byte b = 0; b < i; b++)
      ((Animator.AnimatorListener)arrayList.get(b)).onAnimationEnd(this); 
    releaseNativePtr();
  }
  
  public void pause() {
    throw new UnsupportedOperationException();
  }
  
  public void resume() {
    throw new UnsupportedOperationException();
  }
  
  public void setAllowRunningAsynchronously(boolean paramBoolean) {
    checkMutable();
    nSetAllowRunningAsync(this.mNativePtr.get(), paramBoolean);
  }
  
  public RenderNodeAnimator setDuration(long paramLong) {
    checkMutable();
    if (paramLong >= 0L) {
      this.mUnscaledDuration = paramLong;
      nSetDuration(this.mNativePtr.get(), (long)((float)paramLong * ValueAnimator.getDurationScale()));
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("duration must be positive; ");
    stringBuilder.append(paramLong);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setInterpolator(TimeInterpolator paramTimeInterpolator) {
    checkMutable();
    this.mInterpolator = paramTimeInterpolator;
  }
  
  public void setStartDelay(long paramLong) {
    checkMutable();
    if (paramLong >= 0L) {
      this.mUnscaledStartDelay = paramLong;
      this.mStartDelay = (long)(ValueAnimator.getDurationScale() * (float)paramLong);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("startDelay must be positive; ");
    stringBuilder.append(paramLong);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setStartValue(float paramFloat) {
    checkMutable();
    nSetStartValue(this.mNativePtr.get(), paramFloat);
  }
  
  public final void setTarget(RecordingCanvas paramRecordingCanvas) {
    setTarget(paramRecordingCanvas.mNode);
  }
  
  protected void setTarget(RenderNode paramRenderNode) {
    checkMutable();
    if (this.mTarget == null) {
      nSetListener(this.mNativePtr.get(), this);
      this.mTarget = paramRenderNode;
      paramRenderNode.addAnimator(this);
      return;
    } 
    throw new IllegalStateException("Target already set!");
  }
  
  public void setViewListener(ViewListener paramViewListener) {
    this.mViewListener = paramViewListener;
  }
  
  public void start() {
    if (this.mTarget != null) {
      if (this.mState == 0) {
        this.mState = 1;
        if (this.mHandler == null)
          this.mHandler = new Handler(true); 
        applyInterpolator();
        if (this.mNativePtr == null) {
          cancel();
        } else {
          if (this.mStartDelay <= 0L || !this.mUiThreadHandlesDelay) {
            nSetStartDelay(this.mNativePtr.get(), this.mStartDelay);
            doStart();
            return;
          } 
          getHelper().addDelayedAnimation(this);
        } 
        return;
      } 
      throw new IllegalStateException("Already started!");
    } 
    throw new IllegalStateException("Missing target!");
  }
  
  private static class DelayedAnimationHelper implements Runnable {
    private boolean mCallbackScheduled;
    
    private final Choreographer mChoreographer = Choreographer.getInstance();
    
    private ArrayList<RenderNodeAnimator> mDelayedAnims = new ArrayList<>();
    
    private void scheduleCallback() {
      if (!this.mCallbackScheduled) {
        this.mCallbackScheduled = true;
        this.mChoreographer.postCallback(1, this, null);
      } 
    }
    
    public void addDelayedAnimation(RenderNodeAnimator param1RenderNodeAnimator) {
      this.mDelayedAnims.add(param1RenderNodeAnimator);
      scheduleCallback();
    }
    
    public void removeDelayedAnimation(RenderNodeAnimator param1RenderNodeAnimator) {
      this.mDelayedAnims.remove(param1RenderNodeAnimator);
    }
    
    public void run() {
      long l = this.mChoreographer.getFrameTime();
      this.mCallbackScheduled = false;
      int i = 0;
      byte b = 0;
      while (b < this.mDelayedAnims.size()) {
        RenderNodeAnimator renderNodeAnimator = this.mDelayedAnims.get(b);
        int j = i;
        if (!renderNodeAnimator.processDelayed(l)) {
          if (i != b)
            this.mDelayedAnims.set(i, renderNodeAnimator); 
          j = i + 1;
        } 
        b++;
        i = j;
      } 
      while (this.mDelayedAnims.size() > i) {
        ArrayList<RenderNodeAnimator> arrayList = this.mDelayedAnims;
        arrayList.remove(arrayList.size() - 1);
      } 
      if (this.mDelayedAnims.size() > 0)
        scheduleCallback(); 
    }
  }
  
  public static interface ViewListener {
    void invalidateParent(boolean param1Boolean);
    
    void onAlphaAnimationStart(float param1Float);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/animation/RenderNodeAnimator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.graphics.animation.NativeInterpolatorFactory;
import android.os.Handler;
import android.util.IntArray;
import android.util.Log;
import android.util.LongArray;
import android.util.PathParser;
import android.view.Choreographer;
import android.view.NativeVectorDrawableAnimator;
import com.android.internal.util.VirtualRefBasePtr;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class VectorDrawableAnimatorRT implements AnimatedVectorDrawable.VectorDrawableAnimator, NativeVectorDrawableAnimator {
  private static final int END_ANIMATION = 4;
  
  private static final int MAX_SAMPLE_POINTS = 300;
  
  private static final int RESET_ANIMATION = 3;
  
  private static final int REVERSE_ANIMATION = 2;
  
  private static final int START_ANIMATION = 1;
  
  private boolean mContainsSequentialAnimators = false;
  
  private final AnimatedVectorDrawable mDrawable;
  
  private Handler mHandler;
  
  private boolean mInitialized = false;
  
  private boolean mIsInfinite = false;
  
  private boolean mIsReversible = false;
  
  private int mLastListenerId = 0;
  
  private WeakReference<RenderNode> mLastSeenTarget = null;
  
  private Animator.AnimatorListener mListener = null;
  
  private final IntArray mPendingAnimationActions = new IntArray();
  
  private long mSetPtr = 0L;
  
  private final VirtualRefBasePtr mSetRefBasePtr;
  
  private final LongArray mStartDelays = new LongArray();
  
  private boolean mStarted = false;
  
  private PropertyValuesHolder.PropertyValues mTmpValues = new PropertyValuesHolder.PropertyValues();
  
  VectorDrawableAnimatorRT(AnimatedVectorDrawable paramAnimatedVectorDrawable) {
    this.mDrawable = paramAnimatedVectorDrawable;
    long l = AnimatedVectorDrawable.access$800();
    this.mSetPtr = l;
    this.mSetRefBasePtr = new VirtualRefBasePtr(l);
  }
  
  private void addPendingAction(int paramInt) {
    invalidateOwningView();
    this.mPendingAnimationActions.add(paramInt);
  }
  
  private static void callOnFinished(VectorDrawableAnimatorRT paramVectorDrawableAnimatorRT, int paramInt) {
    paramVectorDrawableAnimatorRT.mHandler.post(new _$$Lambda$AnimatedVectorDrawable$VectorDrawableAnimatorRT$PzjgSeyQweoFjbEZJP80UteZqm8(paramVectorDrawableAnimatorRT, paramInt));
  }
  
  private static float[] createFloatDataPoints(PropertyValuesHolder.PropertyValues.DataSource paramDataSource, long paramLong) {
    int i = getFrameCount(paramLong);
    float[] arrayOfFloat = new float[i];
    float f = (i - 1);
    for (byte b = 0; b < i; b++)
      arrayOfFloat[b] = ((Float)paramDataSource.getValueAtFraction(b / f)).floatValue(); 
    return arrayOfFloat;
  }
  
  private static int[] createIntDataPoints(PropertyValuesHolder.PropertyValues.DataSource paramDataSource, long paramLong) {
    int i = getFrameCount(paramLong);
    int[] arrayOfInt = new int[i];
    float f = (i - 1);
    for (byte b = 0; b < i; b++)
      arrayOfInt[b] = ((Integer)paramDataSource.getValueAtFraction(b / f)).intValue(); 
    return arrayOfInt;
  }
  
  private void createNativeChildAnimator(long paramLong1, long paramLong2, ObjectAnimator paramObjectAnimator) {
    long l1 = paramObjectAnimator.getDuration();
    int i = paramObjectAnimator.getRepeatCount();
    long l2 = paramObjectAnimator.getStartDelay();
    TimeInterpolator timeInterpolator = paramObjectAnimator.getInterpolator();
    long l3 = NativeInterpolatorFactory.createNativeInterpolator(timeInterpolator, l1);
    paramLong2 = (long)((float)(paramLong2 + l2) * ValueAnimator.getDurationScale());
    l1 = (long)((float)l1 * ValueAnimator.getDurationScale());
    this.mStartDelays.add(paramLong2);
    AnimatedVectorDrawable.access$1800(this.mSetPtr, paramLong1, l3, paramLong2, l1, i, paramObjectAnimator.getRepeatMode());
  }
  
  private void createRTAnimator(ObjectAnimator paramObjectAnimator, long paramLong) {
    PropertyValuesHolder[] arrayOfPropertyValuesHolder = paramObjectAnimator.getValues();
    Object object = paramObjectAnimator.getTarget();
    if (object instanceof VectorDrawable.VGroup) {
      createRTAnimatorForGroup(arrayOfPropertyValuesHolder, paramObjectAnimator, (VectorDrawable.VGroup)object, paramLong);
    } else if (object instanceof VectorDrawable.VPath) {
      for (byte b = 0; b < arrayOfPropertyValuesHolder.length; b++) {
        arrayOfPropertyValuesHolder[b].getPropertyValues(this.mTmpValues);
        if (this.mTmpValues.endValue instanceof PathParser.PathData && this.mTmpValues.propertyName.equals("pathData")) {
          createRTAnimatorForPath(paramObjectAnimator, (VectorDrawable.VPath)object, paramLong);
        } else if (object instanceof VectorDrawable.VFullPath) {
          createRTAnimatorForFullPath(paramObjectAnimator, (VectorDrawable.VFullPath)object, paramLong);
        } else if (!AnimatedVectorDrawable.AnimatedVectorDrawableState.access$100(AnimatedVectorDrawable.access$900(this.mDrawable))) {
          throw new IllegalArgumentException("ClipPath only supports PathData property");
        } 
      } 
    } else if (object instanceof VectorDrawable.VectorDrawableState) {
      createRTAnimatorForRootGroup(arrayOfPropertyValuesHolder, paramObjectAnimator, (VectorDrawable.VectorDrawableState)object, paramLong);
    } 
  }
  
  private void createRTAnimatorForFullPath(ObjectAnimator paramObjectAnimator, VectorDrawable.VFullPath paramVFullPath, long paramLong) {
    StringBuilder stringBuilder;
    int i = paramVFullPath.getPropertyIndex(this.mTmpValues.propertyName);
    long l = paramVFullPath.getNativePtr();
    if (this.mTmpValues.type == Float.class || this.mTmpValues.type == float.class) {
      if (i < 0) {
        if (AnimatedVectorDrawable.AnimatedVectorDrawableState.access$100(AnimatedVectorDrawable.access$900(this.mDrawable)))
          return; 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Property: ");
        stringBuilder.append(this.mTmpValues.propertyName);
        stringBuilder.append(" is not supported for FullPath");
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      long l1 = AnimatedVectorDrawable.access$1400(l, i, ((Float)this.mTmpValues.startValue).floatValue(), ((Float)this.mTmpValues.endValue).floatValue());
      l = l1;
      if (this.mTmpValues.dataSource != null) {
        float[] arrayOfFloat = createFloatDataPoints(this.mTmpValues.dataSource, stringBuilder.getDuration());
        AnimatedVectorDrawable.access$1200(l1, arrayOfFloat, arrayOfFloat.length);
        l = l1;
      } 
    } else if (this.mTmpValues.type == Integer.class || this.mTmpValues.type == int.class) {
      long l1 = AnimatedVectorDrawable.access$1500(l, i, ((Integer)this.mTmpValues.startValue).intValue(), ((Integer)this.mTmpValues.endValue).intValue());
      l = l1;
      if (this.mTmpValues.dataSource != null) {
        int[] arrayOfInt = createIntDataPoints(this.mTmpValues.dataSource, stringBuilder.getDuration());
        AnimatedVectorDrawable.access$1600(l1, arrayOfInt, arrayOfInt.length);
        l = l1;
      } 
    } else {
      if (AnimatedVectorDrawable.AnimatedVectorDrawableState.access$100(AnimatedVectorDrawable.access$900(this.mDrawable)))
        return; 
      stringBuilder = new StringBuilder();
      stringBuilder.append("Unsupported type: ");
      stringBuilder.append(this.mTmpValues.type);
      stringBuilder.append(". Only float, int or PathData value is supported for Paths.");
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
    createNativeChildAnimator(l, paramLong, (ObjectAnimator)stringBuilder);
  }
  
  private void createRTAnimatorForGroup(PropertyValuesHolder[] paramArrayOfPropertyValuesHolder, ObjectAnimator paramObjectAnimator, VectorDrawable.VGroup paramVGroup, long paramLong) {
    long l = paramVGroup.getNativePtr();
    for (byte b = 0; b < paramArrayOfPropertyValuesHolder.length; b++) {
      paramArrayOfPropertyValuesHolder[b].getPropertyValues(this.mTmpValues);
      int i = VectorDrawable.VGroup.getPropertyIndex(this.mTmpValues.propertyName);
      if ((this.mTmpValues.type == Float.class || this.mTmpValues.type == float.class) && i >= 0) {
        long l1 = AnimatedVectorDrawable.access$1100(l, i, ((Float)this.mTmpValues.startValue).floatValue(), ((Float)this.mTmpValues.endValue).floatValue());
        if (this.mTmpValues.dataSource != null) {
          float[] arrayOfFloat = createFloatDataPoints(this.mTmpValues.dataSource, paramObjectAnimator.getDuration());
          AnimatedVectorDrawable.access$1200(l1, arrayOfFloat, arrayOfFloat.length);
        } 
        createNativeChildAnimator(l1, paramLong, paramObjectAnimator);
      } 
    } 
  }
  
  private void createRTAnimatorForPath(ObjectAnimator paramObjectAnimator, VectorDrawable.VPath paramVPath, long paramLong) {
    createNativeChildAnimator(AnimatedVectorDrawable.access$1300(paramVPath.getNativePtr(), ((PathParser.PathData)this.mTmpValues.startValue).getNativePtr(), ((PathParser.PathData)this.mTmpValues.endValue).getNativePtr()), paramLong, paramObjectAnimator);
  }
  
  private void createRTAnimatorForRootGroup(PropertyValuesHolder[] paramArrayOfPropertyValuesHolder, ObjectAnimator paramObjectAnimator, VectorDrawable.VectorDrawableState paramVectorDrawableState, long paramLong) {
    long l = paramVectorDrawableState.getNativeRenderer();
    if (!paramObjectAnimator.getPropertyName().equals("alpha")) {
      if (AnimatedVectorDrawable.AnimatedVectorDrawableState.access$100(AnimatedVectorDrawable.access$900(this.mDrawable)))
        return; 
      throw new UnsupportedOperationException("Only alpha is supported for root group");
    } 
    byte b = 0;
    while (true) {
      if (b < paramArrayOfPropertyValuesHolder.length) {
        paramArrayOfPropertyValuesHolder[b].getPropertyValues(this.mTmpValues);
        if (this.mTmpValues.propertyName.equals("alpha")) {
          Float float_1 = (Float)this.mTmpValues.startValue;
          Float float_2 = (Float)this.mTmpValues.endValue;
          break;
        } 
        b++;
        continue;
      } 
      paramArrayOfPropertyValuesHolder = null;
      paramVectorDrawableState = null;
      break;
    } 
    if (paramArrayOfPropertyValuesHolder == null && paramVectorDrawableState == null) {
      if (AnimatedVectorDrawable.AnimatedVectorDrawableState.access$100(AnimatedVectorDrawable.access$900(this.mDrawable)))
        return; 
      throw new UnsupportedOperationException("No alpha values are specified");
    } 
    l = AnimatedVectorDrawable.access$1700(l, paramArrayOfPropertyValuesHolder.floatValue(), paramVectorDrawableState.floatValue());
    if (this.mTmpValues.dataSource != null) {
      float[] arrayOfFloat = createFloatDataPoints(this.mTmpValues.dataSource, paramObjectAnimator.getDuration());
      AnimatedVectorDrawable.access$1200(l, arrayOfFloat, arrayOfFloat.length);
    } 
    createNativeChildAnimator(l, paramLong, paramObjectAnimator);
  }
  
  private void endAnimation() {
    AnimatedVectorDrawable.access$2000(this.mSetPtr);
    invalidateOwningView();
  }
  
  private static int getFrameCount(long paramLong) {
    int i = (int)(Choreographer.getInstance().getFrameIntervalNanos() / 1000000L);
    int j = Math.max(2, (int)Math.ceil(paramLong / i));
    i = j;
    if (j > 300) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Duration for the animation is too long :");
      stringBuilder.append(paramLong);
      stringBuilder.append(", the animation will subsample the keyframe or path data.");
      Log.w("AnimatedVectorDrawable", stringBuilder.toString());
      i = 300;
    } 
    return i;
  }
  
  private void handlePendingAction(int paramInt) {
    if (paramInt == 1) {
      startAnimation();
    } else if (paramInt == 2) {
      reverseAnimation();
    } else if (paramInt == 3) {
      resetAnimation();
    } else {
      if (paramInt == 4) {
        endAnimation();
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Animation action ");
      stringBuilder.append(paramInt);
      stringBuilder.append("is not supported");
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
  }
  
  private void invalidateOwningView() {
    this.mDrawable.invalidateSelf();
  }
  
  private void onAnimationEnd(int paramInt) {
    if (paramInt != this.mLastListenerId)
      return; 
    this.mStarted = false;
    invalidateOwningView();
    Animator.AnimatorListener animatorListener = this.mListener;
    if (animatorListener != null)
      animatorListener.onAnimationEnd(null); 
  }
  
  private void parseAnimatorSet(AnimatorSet paramAnimatorSet, long paramLong) {
    ArrayList<Animator> arrayList = paramAnimatorSet.getChildAnimations();
    boolean bool = paramAnimatorSet.shouldPlayTogether();
    byte b = 0;
    long l;
    for (l = paramLong; b < arrayList.size(); l = paramLong) {
      Animator animator = arrayList.get(b);
      if (animator instanceof AnimatorSet) {
        parseAnimatorSet((AnimatorSet)animator, l);
      } else if (animator instanceof ObjectAnimator) {
        createRTAnimator((ObjectAnimator)animator, l);
      } 
      paramLong = l;
      if (!bool) {
        paramLong = l + animator.getTotalDuration();
        this.mContainsSequentialAnimators = true;
      } 
      b++;
    } 
  }
  
  private void resetAnimation() {
    AnimatedVectorDrawable.access$2100(this.mSetPtr);
    invalidateOwningView();
  }
  
  private void reverseAnimation() {
    this.mStarted = true;
    long l = this.mSetPtr;
    int i = this.mLastListenerId + 1;
    this.mLastListenerId = i;
    AnimatedVectorDrawable.access$2200(l, this, i);
    invalidateOwningView();
    Animator.AnimatorListener animatorListener = this.mListener;
    if (animatorListener != null)
      animatorListener.onAnimationStart(null); 
  }
  
  private void startAnimation() {
    this.mStarted = true;
    if (this.mHandler == null)
      this.mHandler = new Handler(); 
    long l = this.mSetPtr;
    int i = this.mLastListenerId + 1;
    this.mLastListenerId = i;
    AnimatedVectorDrawable.access$1900(l, this, i);
    invalidateOwningView();
    Animator.AnimatorListener animatorListener = this.mListener;
    if (animatorListener != null)
      animatorListener.onAnimationStart(null); 
  }
  
  private void transferPendingActions(AnimatedVectorDrawable.VectorDrawableAnimator paramVectorDrawableAnimator) {
    for (byte b = 0; b < this.mPendingAnimationActions.size(); b++) {
      int i = this.mPendingAnimationActions.get(b);
      if (i == 1) {
        paramVectorDrawableAnimator.start();
      } else if (i == 4) {
        paramVectorDrawableAnimator.end();
      } else if (i == 2) {
        paramVectorDrawableAnimator.reverse();
      } else if (i == 3) {
        paramVectorDrawableAnimator.reset();
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Animation action ");
        stringBuilder.append(i);
        stringBuilder.append("is not supported");
        throw new UnsupportedOperationException(stringBuilder.toString());
      } 
    } 
    this.mPendingAnimationActions.clear();
  }
  
  private boolean useLastSeenTarget() {
    WeakReference<RenderNode> weakReference = this.mLastSeenTarget;
    return (weakReference != null) ? useTarget(weakReference.get()) : false;
  }
  
  private boolean useTarget(RenderNode paramRenderNode) {
    if (paramRenderNode != null && paramRenderNode.isAttached()) {
      paramRenderNode.registerVectorDrawableAnimator(this);
      return true;
    } 
    return false;
  }
  
  public boolean canReverse() {
    return this.mIsReversible;
  }
  
  public void end() {
    if (!this.mInitialized)
      return; 
    if (useLastSeenTarget()) {
      endAnimation();
    } else {
      addPendingAction(4);
    } 
  }
  
  public long getAnimatorNativePtr() {
    return this.mSetPtr;
  }
  
  public void init(AnimatorSet paramAnimatorSet) {
    if (!this.mInitialized) {
      boolean bool;
      parseAnimatorSet(paramAnimatorSet, 0L);
      long l = (AnimatedVectorDrawable.access$900(this.mDrawable)).mVectorDrawable.getNativeTree();
      AnimatedVectorDrawable.access$1000(this.mSetPtr, l);
      this.mInitialized = true;
      if (paramAnimatorSet.getTotalDuration() == -1L) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mIsInfinite = bool;
      this.mIsReversible = true;
      if (this.mContainsSequentialAnimators) {
        this.mIsReversible = false;
      } else {
        for (byte b = 0; b < this.mStartDelays.size(); b++) {
          if (this.mStartDelays.get(b) > 0L) {
            this.mIsReversible = false;
            return;
          } 
        } 
      } 
      return;
    } 
    throw new UnsupportedOperationException("VectorDrawableAnimator cannot be re-initialized");
  }
  
  public boolean isInfinite() {
    return this.mIsInfinite;
  }
  
  public boolean isRunning() {
    return !this.mInitialized ? false : this.mStarted;
  }
  
  public boolean isStarted() {
    return this.mStarted;
  }
  
  public void onDraw(Canvas paramCanvas) {
    if (paramCanvas.isHardwareAccelerated())
      recordLastSeenTarget((RecordingCanvas)paramCanvas); 
  }
  
  public void pause() {}
  
  protected void recordLastSeenTarget(RecordingCanvas paramRecordingCanvas) {
    RenderNode renderNode = paramRecordingCanvas.mNode;
    this.mLastSeenTarget = new WeakReference<>(renderNode);
    if ((this.mInitialized || this.mPendingAnimationActions.size() > 0) && useTarget(renderNode)) {
      for (byte b = 0; b < this.mPendingAnimationActions.size(); b++)
        handlePendingAction(this.mPendingAnimationActions.get(b)); 
      this.mPendingAnimationActions.clear();
    } 
  }
  
  public void removeListener(Animator.AnimatorListener paramAnimatorListener) {
    this.mListener = null;
  }
  
  public void reset() {
    if (!this.mInitialized)
      return; 
    if (useLastSeenTarget()) {
      resetAnimation();
    } else {
      addPendingAction(3);
    } 
  }
  
  public void resume() {}
  
  public void reverse() {
    if (!this.mIsReversible || !this.mInitialized)
      return; 
    if (useLastSeenTarget()) {
      reverseAnimation();
    } else {
      addPendingAction(2);
    } 
  }
  
  public void setListener(Animator.AnimatorListener paramAnimatorListener) {
    this.mListener = paramAnimatorListener;
  }
  
  public void start() {
    if (!this.mInitialized)
      return; 
    if (useLastSeenTarget()) {
      startAnimation();
    } else {
      addPendingAction(1);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedVectorDrawable$VectorDrawableAnimatorRT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
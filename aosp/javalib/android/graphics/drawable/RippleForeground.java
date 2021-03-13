package android.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.CanvasProperty;
import android.graphics.Paint;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.animation.RenderNodeAnimator;
import android.util.FloatProperty;
import android.util.MathUtils;
import android.util.Property;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import java.util.ArrayList;

class RippleForeground extends RippleComponent {
  private static final TimeInterpolator DECELERATE_INTERPOLATOR;
  
  private static final TimeInterpolator LINEAR_INTERPOLATOR = (TimeInterpolator)new LinearInterpolator();
  
  private static final FloatProperty<RippleForeground> OPACITY;
  
  private static final int OPACITY_ENTER_DURATION = 75;
  
  private static final int OPACITY_EXIT_DURATION = 150;
  
  private static final int OPACITY_HOLD_DURATION = 225;
  
  private static final int RIPPLE_ENTER_DURATION = 225;
  
  private static final int RIPPLE_ORIGIN_DURATION = 225;
  
  private static final FloatProperty<RippleForeground> TWEEN_ORIGIN;
  
  private static final FloatProperty<RippleForeground> TWEEN_RADIUS;
  
  private final AnimatorListenerAdapter mAnimationListener = new AnimatorListenerAdapter() {
      public void onAnimationEnd(Animator param1Animator) {
        RippleForeground.access$002(RippleForeground.this, true);
        RippleForeground.this.pruneHwFinished();
        RippleForeground.this.pruneSwFinished();
        if (RippleForeground.this.mRunningHwAnimators.isEmpty())
          RippleForeground.this.clearHwProps(); 
      }
    };
  
  private float mClampedStartingX;
  
  private float mClampedStartingY;
  
  private long mEnterStartedAtMillis;
  
  private final boolean mForceSoftware;
  
  private boolean mHasFinishedExit;
  
  private float mOpacity = 0.0F;
  
  private ArrayList<RenderNodeAnimator> mPendingHwAnimators = new ArrayList<>();
  
  private CanvasProperty<Paint> mPropPaint;
  
  private CanvasProperty<Float> mPropRadius;
  
  private CanvasProperty<Float> mPropX;
  
  private CanvasProperty<Float> mPropY;
  
  private ArrayList<RenderNodeAnimator> mRunningHwAnimators = new ArrayList<>();
  
  private ArrayList<Animator> mRunningSwAnimators = new ArrayList<>();
  
  private float mStartRadius = 0.0F;
  
  private float mStartingX;
  
  private float mStartingY;
  
  private float mTargetX = 0.0F;
  
  private float mTargetY = 0.0F;
  
  private float mTweenRadius = 0.0F;
  
  private float mTweenX = 0.0F;
  
  private float mTweenY = 0.0F;
  
  private boolean mUsingProperties;
  
  static {
    DECELERATE_INTERPOLATOR = (TimeInterpolator)new PathInterpolator(0.4F, 0.0F, 0.2F, 1.0F);
    TWEEN_RADIUS = new FloatProperty<RippleForeground>("tweenRadius") {
        public Float get(RippleForeground param1RippleForeground) {
          return Float.valueOf(param1RippleForeground.mTweenRadius);
        }
        
        public void setValue(RippleForeground param1RippleForeground, float param1Float) {
          RippleForeground.access$502(param1RippleForeground, param1Float);
          param1RippleForeground.onAnimationPropertyChanged();
        }
      };
    TWEEN_ORIGIN = new FloatProperty<RippleForeground>("tweenOrigin") {
        public Float get(RippleForeground param1RippleForeground) {
          return Float.valueOf(param1RippleForeground.mTweenX);
        }
        
        public void setValue(RippleForeground param1RippleForeground, float param1Float) {
          RippleForeground.access$702(param1RippleForeground, param1Float);
          RippleForeground.access$802(param1RippleForeground, param1Float);
          param1RippleForeground.onAnimationPropertyChanged();
        }
      };
    OPACITY = new FloatProperty<RippleForeground>("opacity") {
        public Float get(RippleForeground param1RippleForeground) {
          return Float.valueOf(param1RippleForeground.mOpacity);
        }
        
        public void setValue(RippleForeground param1RippleForeground, float param1Float) {
          RippleForeground.access$902(param1RippleForeground, param1Float);
          param1RippleForeground.onAnimationPropertyChanged();
        }
      };
  }
  
  public RippleForeground(RippleDrawable paramRippleDrawable, Rect paramRect, float paramFloat1, float paramFloat2, boolean paramBoolean) {
    super(paramRippleDrawable, paramRect);
    this.mForceSoftware = paramBoolean;
    this.mStartingX = paramFloat1;
    this.mStartingY = paramFloat2;
    this.mStartRadius = Math.max(paramRect.width(), paramRect.height()) * 0.3F;
    clampStartingPosition();
  }
  
  private void clampStartingPosition() {
    float f1 = this.mBounds.exactCenterX();
    float f2 = this.mBounds.exactCenterY();
    float f3 = this.mStartingX - f1;
    float f4 = this.mStartingY - f2;
    float f5 = this.mTargetRadius - this.mStartRadius;
    if (f3 * f3 + f4 * f4 > f5 * f5) {
      double d = Math.atan2(f4, f3);
      this.mClampedStartingX = (float)(Math.cos(d) * f5) + f1;
      this.mClampedStartingY = (float)(Math.sin(d) * f5) + f2;
    } else {
      this.mClampedStartingX = this.mStartingX;
      this.mClampedStartingY = this.mStartingY;
    } 
  }
  
  private void clearHwProps() {
    this.mPropPaint = null;
    this.mPropRadius = null;
    this.mPropX = null;
    this.mPropY = null;
    this.mUsingProperties = false;
  }
  
  private long computeFadeOutDelay() {
    long l = AnimationUtils.currentAnimationTimeMillis() - this.mEnterStartedAtMillis;
    return (l > 0L && l < 225L) ? (225L - l) : 0L;
  }
  
  private void drawHardware(RecordingCanvas paramRecordingCanvas, Paint paramPaint) {
    startPending(paramRecordingCanvas);
    pruneHwFinished();
    CanvasProperty<Paint> canvasProperty = this.mPropPaint;
    if (canvasProperty != null) {
      this.mUsingProperties = true;
      paramRecordingCanvas.drawCircle(this.mPropX, this.mPropY, this.mPropRadius, canvasProperty);
    } else {
      this.mUsingProperties = false;
      drawSoftware((Canvas)paramRecordingCanvas, paramPaint);
    } 
  }
  
  private void drawSoftware(Canvas paramCanvas, Paint paramPaint) {
    int i = paramPaint.getAlpha();
    int j = (int)(i * this.mOpacity + 0.5F);
    float f = getCurrentRadius();
    if (j > 0 && f > 0.0F) {
      float f1 = getCurrentX();
      float f2 = getCurrentY();
      paramPaint.setAlpha(j);
      paramCanvas.drawCircle(f1, f2, f, paramPaint);
      paramPaint.setAlpha(i);
    } 
  }
  
  private float getCurrentRadius() {
    return MathUtils.lerp(this.mStartRadius, this.mTargetRadius, this.mTweenRadius);
  }
  
  private float getCurrentX() {
    return MathUtils.lerp(this.mClampedStartingX - this.mBounds.exactCenterX(), this.mTargetX, this.mTweenX);
  }
  
  private float getCurrentY() {
    return MathUtils.lerp(this.mClampedStartingY - this.mBounds.exactCenterY(), this.mTargetY, this.mTweenY);
  }
  
  private void onAnimationPropertyChanged() {
    if (!this.mUsingProperties)
      invalidateSelf(); 
  }
  
  private void pruneHwFinished() {
    if (!this.mRunningHwAnimators.isEmpty())
      for (int i = this.mRunningHwAnimators.size() - 1; i >= 0; i--) {
        if (!((RenderNodeAnimator)this.mRunningHwAnimators.get(i)).isRunning())
          this.mRunningHwAnimators.remove(i); 
      }  
  }
  
  private void pruneSwFinished() {
    if (!this.mRunningSwAnimators.isEmpty())
      for (int i = this.mRunningSwAnimators.size() - 1; i >= 0; i--) {
        if (!((Animator)this.mRunningSwAnimators.get(i)).isRunning())
          this.mRunningSwAnimators.remove(i); 
      }  
  }
  
  private void startHardwareEnter() {
    if (this.mForceSoftware)
      return; 
    this.mPropX = CanvasProperty.createFloat(getCurrentX());
    this.mPropY = CanvasProperty.createFloat(getCurrentY());
    this.mPropRadius = CanvasProperty.createFloat(getCurrentRadius());
    Paint paint = this.mOwner.getRipplePaint();
    this.mPropPaint = CanvasProperty.createPaint(paint);
    RenderNodeAnimator renderNodeAnimator2 = new RenderNodeAnimator(this.mPropRadius, this.mTargetRadius);
    renderNodeAnimator2.setDuration(225L);
    renderNodeAnimator2.setInterpolator(DECELERATE_INTERPOLATOR);
    this.mPendingHwAnimators.add(renderNodeAnimator2);
    renderNodeAnimator2 = new RenderNodeAnimator(this.mPropX, this.mTargetX);
    renderNodeAnimator2.setDuration(225L);
    renderNodeAnimator2.setInterpolator(DECELERATE_INTERPOLATOR);
    this.mPendingHwAnimators.add(renderNodeAnimator2);
    renderNodeAnimator2 = new RenderNodeAnimator(this.mPropY, this.mTargetY);
    renderNodeAnimator2.setDuration(225L);
    renderNodeAnimator2.setInterpolator(DECELERATE_INTERPOLATOR);
    this.mPendingHwAnimators.add(renderNodeAnimator2);
    RenderNodeAnimator renderNodeAnimator1 = new RenderNodeAnimator(this.mPropPaint, 1, paint.getAlpha());
    renderNodeAnimator1.setDuration(75L);
    renderNodeAnimator1.setInterpolator(LINEAR_INTERPOLATOR);
    renderNodeAnimator1.setStartValue(0.0F);
    this.mPendingHwAnimators.add(renderNodeAnimator1);
    invalidateSelf();
  }
  
  private void startHardwareExit() {
    if (this.mForceSoftware || this.mPropPaint == null)
      return; 
    RenderNodeAnimator renderNodeAnimator = new RenderNodeAnimator(this.mPropPaint, 1, 0.0F);
    renderNodeAnimator.setDuration(150L);
    renderNodeAnimator.setInterpolator(LINEAR_INTERPOLATOR);
    renderNodeAnimator.addListener((Animator.AnimatorListener)this.mAnimationListener);
    renderNodeAnimator.setStartDelay(computeFadeOutDelay());
    renderNodeAnimator.setStartValue(this.mOwner.getRipplePaint().getAlpha());
    this.mPendingHwAnimators.add(renderNodeAnimator);
    invalidateSelf();
  }
  
  private void startPending(RecordingCanvas paramRecordingCanvas) {
    if (!this.mPendingHwAnimators.isEmpty()) {
      for (byte b = 0; b < this.mPendingHwAnimators.size(); b++) {
        RenderNodeAnimator renderNodeAnimator = this.mPendingHwAnimators.get(b);
        renderNodeAnimator.setTarget(paramRecordingCanvas);
        renderNodeAnimator.start();
        this.mRunningHwAnimators.add(renderNodeAnimator);
      } 
      this.mPendingHwAnimators.clear();
    } 
  }
  
  private void startSoftwareEnter() {
    for (byte b = 0; b < this.mRunningSwAnimators.size(); b++)
      ((Animator)this.mRunningSwAnimators.get(b)).cancel(); 
    this.mRunningSwAnimators.clear();
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, (Property)TWEEN_RADIUS, new float[] { 1.0F });
    objectAnimator.setDuration(225L);
    objectAnimator.setInterpolator(DECELERATE_INTERPOLATOR);
    objectAnimator.start();
    this.mRunningSwAnimators.add(objectAnimator);
    objectAnimator = ObjectAnimator.ofFloat(this, (Property)TWEEN_ORIGIN, new float[] { 1.0F });
    objectAnimator.setDuration(225L);
    objectAnimator.setInterpolator(DECELERATE_INTERPOLATOR);
    objectAnimator.start();
    this.mRunningSwAnimators.add(objectAnimator);
    objectAnimator = ObjectAnimator.ofFloat(this, (Property)OPACITY, new float[] { 1.0F });
    objectAnimator.setDuration(75L);
    objectAnimator.setInterpolator(LINEAR_INTERPOLATOR);
    objectAnimator.start();
    this.mRunningSwAnimators.add(objectAnimator);
  }
  
  private void startSoftwareExit() {
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, (Property)OPACITY, new float[] { 0.0F });
    objectAnimator.setDuration(150L);
    objectAnimator.setInterpolator(LINEAR_INTERPOLATOR);
    objectAnimator.addListener((Animator.AnimatorListener)this.mAnimationListener);
    objectAnimator.setStartDelay(computeFadeOutDelay());
    objectAnimator.start();
    this.mRunningSwAnimators.add(objectAnimator);
  }
  
  private void switchToUiThreadAnimation() {
    for (byte b = 0; b < this.mRunningHwAnimators.size(); b++) {
      Animator animator = (Animator)this.mRunningHwAnimators.get(b);
      animator.removeListener((Animator.AnimatorListener)this.mAnimationListener);
      animator.end();
    } 
    this.mRunningHwAnimators.clear();
    clearHwProps();
    invalidateSelf();
  }
  
  public void draw(Canvas paramCanvas, Paint paramPaint) {
    boolean bool;
    if (!this.mForceSoftware && paramCanvas instanceof RecordingCanvas) {
      bool = true;
    } else {
      bool = false;
    } 
    pruneSwFinished();
    if (bool) {
      drawHardware((RecordingCanvas)paramCanvas, paramPaint);
    } else {
      drawSoftware(paramCanvas, paramPaint);
    } 
  }
  
  public void end() {
    byte b;
    for (b = 0; b < this.mRunningSwAnimators.size(); b++)
      ((Animator)this.mRunningSwAnimators.get(b)).end(); 
    this.mRunningSwAnimators.clear();
    for (b = 0; b < this.mRunningHwAnimators.size(); b++)
      ((RenderNodeAnimator)this.mRunningHwAnimators.get(b)).end(); 
    this.mRunningHwAnimators.clear();
  }
  
  public final void enter() {
    this.mEnterStartedAtMillis = AnimationUtils.currentAnimationTimeMillis();
    startSoftwareEnter();
    startHardwareEnter();
  }
  
  public final void exit() {
    startSoftwareExit();
    startHardwareExit();
  }
  
  public void getBounds(Rect paramRect) {
    int i = (int)this.mTargetX;
    int j = (int)this.mTargetY;
    int k = (int)this.mTargetRadius + 1;
    paramRect.set(i - k, j - k, i + k, j + k);
  }
  
  public boolean hasFinishedExit() {
    return this.mHasFinishedExit;
  }
  
  public void move(float paramFloat1, float paramFloat2) {
    this.mStartingX = paramFloat1;
    this.mStartingY = paramFloat2;
    clampStartingPosition();
  }
  
  protected void onTargetRadiusChanged(float paramFloat) {
    clampStartingPosition();
    switchToUiThreadAnimation();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/RippleForeground.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.FloatProperty;
import android.util.Property;
import android.view.animation.LinearInterpolator;

class RippleBackground extends RippleComponent {
  private static final TimeInterpolator LINEAR_INTERPOLATOR = (TimeInterpolator)new LinearInterpolator();
  
  private static final BackgroundProperty OPACITY = new BackgroundProperty("opacity") {
      public Float get(RippleBackground param1RippleBackground) {
        return Float.valueOf(param1RippleBackground.mOpacity);
      }
      
      public void setValue(RippleBackground param1RippleBackground, float param1Float) {
        RippleBackground.access$002(param1RippleBackground, param1Float);
        param1RippleBackground.invalidateSelf();
      }
    };
  
  private static final int OPACITY_DURATION = 80;
  
  private ObjectAnimator mAnimator;
  
  private boolean mFocused = false;
  
  private boolean mHovered = false;
  
  private boolean mIsBounded;
  
  private float mOpacity = 0.0F;
  
  public RippleBackground(RippleDrawable paramRippleDrawable, Rect paramRect, boolean paramBoolean) {
    super(paramRippleDrawable, paramRect);
    this.mIsBounded = paramBoolean;
  }
  
  private void onStateChanged() {
    float f;
    if (this.mFocused) {
      f = 0.6F;
    } else if (this.mHovered) {
      f = 0.2F;
    } else {
      f = 0.0F;
    } 
    ObjectAnimator objectAnimator = this.mAnimator;
    if (objectAnimator != null) {
      objectAnimator.cancel();
      this.mAnimator = null;
    } 
    objectAnimator = ObjectAnimator.ofFloat(this, (Property)OPACITY, new float[] { f });
    this.mAnimator = objectAnimator;
    objectAnimator.setDuration(80L);
    this.mAnimator.setInterpolator(LINEAR_INTERPOLATOR);
    this.mAnimator.start();
  }
  
  public void draw(Canvas paramCanvas, Paint paramPaint) {
    int i = paramPaint.getAlpha();
    int j = Math.min((int)(i * this.mOpacity + 0.5F), 255);
    if (j > 0) {
      paramPaint.setAlpha(j);
      paramCanvas.drawCircle(0.0F, 0.0F, this.mTargetRadius, paramPaint);
      paramPaint.setAlpha(i);
    } 
  }
  
  public boolean isVisible() {
    boolean bool;
    if (this.mOpacity > 0.0F) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void jumpToFinal() {
    ObjectAnimator objectAnimator = this.mAnimator;
    if (objectAnimator != null) {
      objectAnimator.end();
      this.mAnimator = null;
    } 
  }
  
  public void setState(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    boolean bool1 = this.mFocused;
    boolean bool = true;
    boolean bool2 = paramBoolean1;
    if (!bool1) {
      if (paramBoolean1 && !paramBoolean3) {
        paramBoolean1 = true;
      } else {
        paramBoolean1 = false;
      } 
      bool2 = paramBoolean1;
    } 
    paramBoolean1 = paramBoolean2;
    if (!this.mHovered)
      if (paramBoolean2 && !paramBoolean3) {
        paramBoolean1 = bool;
      } else {
        paramBoolean1 = false;
      }  
    if (this.mHovered != paramBoolean1 || this.mFocused != bool2) {
      this.mHovered = paramBoolean1;
      this.mFocused = bool2;
      onStateChanged();
    } 
  }
  
  private static abstract class BackgroundProperty extends FloatProperty<RippleBackground> {
    public BackgroundProperty(String param1String) {
      super(param1String);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/RippleBackground.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
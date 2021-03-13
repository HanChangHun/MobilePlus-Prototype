package android.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.SystemClock;

public class TransitionDrawable extends LayerDrawable implements Drawable.Callback {
  private static final int TRANSITION_NONE = 2;
  
  private static final int TRANSITION_RUNNING = 1;
  
  private static final int TRANSITION_STARTING = 0;
  
  private int mAlpha = 0;
  
  private boolean mCrossFade;
  
  private int mDuration;
  
  private int mFrom;
  
  private int mOriginalDuration;
  
  private boolean mReverse;
  
  private long mStartTimeMillis;
  
  private int mTo;
  
  private int mTransitionState = 2;
  
  TransitionDrawable() {
    this(new TransitionState(null, null, null), (Resources)null);
  }
  
  private TransitionDrawable(TransitionState paramTransitionState, Resources paramResources) {
    super(paramTransitionState, paramResources);
  }
  
  private TransitionDrawable(TransitionState paramTransitionState, Drawable[] paramArrayOfDrawable) {
    super(paramArrayOfDrawable, paramTransitionState);
  }
  
  public TransitionDrawable(Drawable[] paramArrayOfDrawable) {
    this(new TransitionState(null, null, null), paramArrayOfDrawable);
  }
  
  LayerDrawable.LayerState createConstantState(LayerDrawable.LayerState paramLayerState, Resources paramResources) {
    return new TransitionState((TransitionState)paramLayerState, this, paramResources);
  }
  
  public void draw(Canvas paramCanvas) {
    boolean bool = true;
    int i = this.mTransitionState;
    if (i != 0) {
      if (i == 1 && this.mStartTimeMillis >= 0L) {
        float f = (float)(SystemClock.uptimeMillis() - this.mStartTimeMillis) / this.mDuration;
        if (f >= 1.0F) {
          bool = true;
        } else {
          bool = false;
        } 
        f = Math.min(f, 1.0F);
        i = this.mFrom;
        this.mAlpha = (int)(i + (this.mTo - i) * f);
      } 
    } else {
      this.mStartTimeMillis = SystemClock.uptimeMillis();
      bool = false;
      this.mTransitionState = 1;
    } 
    i = this.mAlpha;
    boolean bool1 = this.mCrossFade;
    LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    if (bool) {
      if (!bool1 || i == 0)
        (arrayOfChildDrawable[0]).mDrawable.draw(paramCanvas); 
      if (i == 255)
        (arrayOfChildDrawable[1]).mDrawable.draw(paramCanvas); 
      return;
    } 
    Drawable drawable = (arrayOfChildDrawable[0]).mDrawable;
    if (bool1)
      drawable.setAlpha(255 - i); 
    drawable.draw(paramCanvas);
    if (bool1)
      drawable.setAlpha(255); 
    if (i > 0) {
      Drawable drawable1 = (arrayOfChildDrawable[1]).mDrawable;
      drawable1.setAlpha(i);
      drawable1.draw(paramCanvas);
      drawable1.setAlpha(255);
    } 
    if (!bool)
      invalidateSelf(); 
  }
  
  public boolean isCrossFadeEnabled() {
    return this.mCrossFade;
  }
  
  public void resetTransition() {
    this.mAlpha = 0;
    this.mTransitionState = 2;
    invalidateSelf();
  }
  
  public void reverseTransition(int paramInt) {
    long l1 = SystemClock.uptimeMillis();
    long l2 = this.mStartTimeMillis;
    long l3 = this.mDuration;
    char c = 'ÿ';
    if (l1 - l2 > l3) {
      if (this.mTo == 0) {
        this.mFrom = 0;
        this.mTo = 255;
        this.mAlpha = 0;
        this.mReverse = false;
      } else {
        this.mFrom = 255;
        this.mTo = 0;
        this.mAlpha = 255;
        this.mReverse = true;
      } 
      this.mOriginalDuration = paramInt;
      this.mDuration = paramInt;
      this.mTransitionState = 0;
      invalidateSelf();
      return;
    } 
    int i = this.mReverse ^ true;
    this.mReverse = i;
    this.mFrom = this.mAlpha;
    paramInt = c;
    if (i != 0)
      paramInt = 0; 
    this.mTo = paramInt;
    if (this.mReverse) {
      l1 -= this.mStartTimeMillis;
    } else {
      l1 = this.mOriginalDuration - l1 - this.mStartTimeMillis;
    } 
    this.mDuration = (int)l1;
    this.mTransitionState = 0;
  }
  
  public void setCrossFadeEnabled(boolean paramBoolean) {
    this.mCrossFade = paramBoolean;
  }
  
  public void showSecondLayer() {
    this.mAlpha = 255;
    this.mReverse = false;
    this.mTransitionState = 2;
    invalidateSelf();
  }
  
  public void startTransition(int paramInt) {
    this.mFrom = 0;
    this.mTo = 255;
    this.mAlpha = 0;
    this.mOriginalDuration = paramInt;
    this.mDuration = paramInt;
    this.mReverse = false;
    this.mTransitionState = 0;
    invalidateSelf();
  }
  
  static class TransitionState extends LayerDrawable.LayerState {
    TransitionState(TransitionState param1TransitionState, TransitionDrawable param1TransitionDrawable, Resources param1Resources) {
      super(param1TransitionState, param1TransitionDrawable, param1Resources);
    }
    
    public int getChangingConfigurations() {
      return this.mChangingConfigurations;
    }
    
    public Drawable newDrawable() {
      return new TransitionDrawable(this, (Resources)null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new TransitionDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/TransitionDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
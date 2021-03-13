package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedRotateDrawable extends DrawableWrapper implements Animatable {
  private float mCurrentDegrees;
  
  private float mIncrement;
  
  private final Runnable mNextFrame = new Runnable() {
      public void run() {
        AnimatedRotateDrawable animatedRotateDrawable = AnimatedRotateDrawable.this;
        AnimatedRotateDrawable.access$216(animatedRotateDrawable, animatedRotateDrawable.mIncrement);
        if (AnimatedRotateDrawable.this.mCurrentDegrees > 360.0F - AnimatedRotateDrawable.this.mIncrement)
          AnimatedRotateDrawable.access$202(AnimatedRotateDrawable.this, 0.0F); 
        AnimatedRotateDrawable.this.invalidateSelf();
        AnimatedRotateDrawable.this.nextFrame();
      }
    };
  
  private boolean mRunning;
  
  private AnimatedRotateState mState;
  
  public AnimatedRotateDrawable() {
    this(new AnimatedRotateState(null, null), (Resources)null);
  }
  
  private AnimatedRotateDrawable(AnimatedRotateState paramAnimatedRotateState, Resources paramResources) {
    super(paramAnimatedRotateState, paramResources);
    this.mState = paramAnimatedRotateState;
    updateLocalState();
  }
  
  private void nextFrame() {
    unscheduleSelf(this.mNextFrame);
    scheduleSelf(this.mNextFrame, SystemClock.uptimeMillis() + this.mState.mFrameDuration);
  }
  
  private void updateLocalState() {
    this.mIncrement = 360.0F / this.mState.mFramesCount;
    Drawable drawable = getDrawable();
    if (drawable != null) {
      drawable.setFilterBitmap(true);
      if (drawable instanceof BitmapDrawable)
        ((BitmapDrawable)drawable).setAntiAlias(true); 
    } 
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    AnimatedRotateState animatedRotateState = this.mState;
    if (animatedRotateState == null)
      return; 
    animatedRotateState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    AnimatedRotateState.access$002(animatedRotateState, paramTypedArray.extractThemeAttrs());
    boolean bool = paramTypedArray.hasValue(2);
    boolean bool1 = true;
    if (bool) {
      float f;
      TypedValue typedValue = paramTypedArray.peekValue(2);
      if (typedValue.type == 6) {
        bool = true;
      } else {
        bool = false;
      } 
      animatedRotateState.mPivotXRel = bool;
      if (animatedRotateState.mPivotXRel) {
        f = typedValue.getFraction(1.0F, 1.0F);
      } else {
        f = typedValue.getFloat();
      } 
      animatedRotateState.mPivotX = f;
    } 
    if (paramTypedArray.hasValue(3)) {
      float f;
      TypedValue typedValue = paramTypedArray.peekValue(3);
      if (typedValue.type == 6) {
        bool = bool1;
      } else {
        bool = false;
      } 
      animatedRotateState.mPivotYRel = bool;
      if (animatedRotateState.mPivotYRel) {
        f = typedValue.getFraction(1.0F, 1.0F);
      } else {
        f = typedValue.getFloat();
      } 
      animatedRotateState.mPivotY = f;
    } 
    setFramesCount(paramTypedArray.getInt(5, animatedRotateState.mFramesCount));
    setFramesDuration(paramTypedArray.getInt(4, animatedRotateState.mFrameDuration));
  }
  
  private void verifyRequiredAttributes(TypedArray paramTypedArray) throws XmlPullParserException {
    if (getDrawable() != null || (this.mState.mThemeAttrs != null && this.mState.mThemeAttrs[1] != 0))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramTypedArray.getPositionDescription());
    stringBuilder.append(": <animated-rotate> tag requires a 'drawable' attribute or child tag defining a drawable");
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    AnimatedRotateState animatedRotateState = this.mState;
    if (animatedRotateState == null)
      return; 
    if (animatedRotateState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(animatedRotateState.mThemeAttrs, R.styleable.AnimatedRotateDrawable);
      try {
        updateStateFromTypedArray(typedArray);
        verifyRequiredAttributes(typedArray);
        typedArray.recycle();
      } catch (XmlPullParserException xmlPullParserException) {
        rethrowAsRuntimeException((Exception)xmlPullParserException);
        typedArray.recycle();
      } finally {}
    } 
    updateLocalState();
  }
  
  public void draw(Canvas paramCanvas) {
    float f1;
    float f2;
    Drawable drawable = getDrawable();
    Rect rect = drawable.getBounds();
    int i = rect.right;
    int j = rect.left;
    int k = rect.bottom;
    int m = rect.top;
    AnimatedRotateState animatedRotateState = this.mState;
    if (animatedRotateState.mPivotXRel) {
      f1 = (i - j) * animatedRotateState.mPivotX;
    } else {
      f1 = animatedRotateState.mPivotX;
    } 
    if (animatedRotateState.mPivotYRel) {
      f2 = (k - m) * animatedRotateState.mPivotY;
    } else {
      f2 = animatedRotateState.mPivotY;
    } 
    j = paramCanvas.save();
    paramCanvas.rotate(this.mCurrentDegrees, rect.left + f1, rect.top + f2);
    drawable.draw(paramCanvas);
    paramCanvas.restoreToCount(j);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedRotateDrawable);
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    updateStateFromTypedArray(typedArray);
    verifyRequiredAttributes(typedArray);
    typedArray.recycle();
    updateLocalState();
  }
  
  public boolean isRunning() {
    return this.mRunning;
  }
  
  DrawableWrapper.DrawableWrapperState mutateConstantState() {
    AnimatedRotateState animatedRotateState = new AnimatedRotateState(this.mState, null);
    this.mState = animatedRotateState;
    return animatedRotateState;
  }
  
  public void setFramesCount(int paramInt) {
    this.mState.mFramesCount = paramInt;
    this.mIncrement = 360.0F / this.mState.mFramesCount;
  }
  
  public void setFramesDuration(int paramInt) {
    this.mState.mFrameDuration = paramInt;
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    if (paramBoolean1) {
      if (bool || paramBoolean2) {
        this.mCurrentDegrees = 0.0F;
        nextFrame();
      } 
    } else {
      unscheduleSelf(this.mNextFrame);
    } 
    return bool;
  }
  
  public void start() {
    if (!this.mRunning) {
      this.mRunning = true;
      nextFrame();
    } 
  }
  
  public void stop() {
    this.mRunning = false;
    unscheduleSelf(this.mNextFrame);
  }
  
  static final class AnimatedRotateState extends DrawableWrapper.DrawableWrapperState {
    int mFrameDuration = 150;
    
    int mFramesCount = 12;
    
    float mPivotX = 0.0F;
    
    boolean mPivotXRel = false;
    
    float mPivotY = 0.0F;
    
    boolean mPivotYRel = false;
    
    private int[] mThemeAttrs;
    
    public AnimatedRotateState(AnimatedRotateState param1AnimatedRotateState, Resources param1Resources) {
      super(param1AnimatedRotateState, param1Resources);
      if (param1AnimatedRotateState != null) {
        this.mPivotXRel = param1AnimatedRotateState.mPivotXRel;
        this.mPivotX = param1AnimatedRotateState.mPivotX;
        this.mPivotYRel = param1AnimatedRotateState.mPivotYRel;
        this.mPivotY = param1AnimatedRotateState.mPivotY;
        this.mFramesCount = param1AnimatedRotateState.mFramesCount;
        this.mFrameDuration = param1AnimatedRotateState.mFrameDuration;
      } 
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new AnimatedRotateDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedRotateDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
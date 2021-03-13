package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.Outline;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class InsetDrawable extends DrawableWrapper {
  private InsetState mState;
  
  private final Rect mTmpInsetRect = new Rect();
  
  private final Rect mTmpRect = new Rect();
  
  InsetDrawable() {
    this(new InsetState(null, null), (Resources)null);
  }
  
  public InsetDrawable(Drawable paramDrawable, float paramFloat) {
    this(paramDrawable, paramFloat, paramFloat, paramFloat, paramFloat);
  }
  
  public InsetDrawable(Drawable paramDrawable, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    this(new InsetState(null, null), (Resources)null);
    this.mState.mInsetLeft = new InsetValue(paramFloat1, 0);
    this.mState.mInsetTop = new InsetValue(paramFloat2, 0);
    this.mState.mInsetRight = new InsetValue(paramFloat3, 0);
    this.mState.mInsetBottom = new InsetValue(paramFloat4, 0);
    setDrawable(paramDrawable);
  }
  
  public InsetDrawable(Drawable paramDrawable, int paramInt) {
    this(paramDrawable, paramInt, paramInt, paramInt, paramInt);
  }
  
  public InsetDrawable(Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this(new InsetState(null, null), (Resources)null);
    this.mState.mInsetLeft = new InsetValue(0.0F, paramInt1);
    this.mState.mInsetTop = new InsetValue(0.0F, paramInt2);
    this.mState.mInsetRight = new InsetValue(0.0F, paramInt3);
    this.mState.mInsetBottom = new InsetValue(0.0F, paramInt4);
    setDrawable(paramDrawable);
  }
  
  private InsetDrawable(InsetState paramInsetState, Resources paramResources) {
    super(paramInsetState, paramResources);
    this.mState = paramInsetState;
  }
  
  private InsetValue getInset(TypedArray paramTypedArray, int paramInt, InsetValue paramInsetValue) {
    if (paramTypedArray.hasValue(paramInt)) {
      TypedValue typedValue = paramTypedArray.peekValue(paramInt);
      if (typedValue.type == 6) {
        float f = typedValue.getFraction(1.0F, 1.0F);
        if (f < 1.0F)
          return new InsetValue(f, 0); 
        throw new IllegalStateException("Fraction cannot be larger than 1");
      } 
      paramInt = paramTypedArray.getDimensionPixelOffset(paramInt, 0);
      if (paramInt != 0)
        return new InsetValue(0.0F, paramInt); 
    } 
    return paramInsetValue;
  }
  
  private void getInsets(Rect paramRect) {
    Rect rect = getBounds();
    paramRect.left = this.mState.mInsetLeft.getDimension(rect.width());
    paramRect.right = this.mState.mInsetRight.getDimension(rect.width());
    paramRect.top = this.mState.mInsetTop.getDimension(rect.height());
    paramRect.bottom = this.mState.mInsetBottom.getDimension(rect.height());
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    InsetState insetState = this.mState;
    if (insetState == null)
      return; 
    insetState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    InsetState.access$002(insetState, paramTypedArray.extractThemeAttrs());
    if (paramTypedArray.hasValue(6)) {
      InsetValue insetValue = getInset(paramTypedArray, 6, new InsetValue());
      insetState.mInsetLeft = insetValue;
      insetState.mInsetTop = insetValue;
      insetState.mInsetRight = insetValue;
      insetState.mInsetBottom = insetValue;
    } 
    insetState.mInsetLeft = getInset(paramTypedArray, 2, insetState.mInsetLeft);
    insetState.mInsetTop = getInset(paramTypedArray, 4, insetState.mInsetTop);
    insetState.mInsetRight = getInset(paramTypedArray, 3, insetState.mInsetRight);
    insetState.mInsetBottom = getInset(paramTypedArray, 5, insetState.mInsetBottom);
  }
  
  private void verifyRequiredAttributes(TypedArray paramTypedArray) throws XmlPullParserException {
    if (getDrawable() != null || (this.mState.mThemeAttrs != null && this.mState.mThemeAttrs[1] != 0))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramTypedArray.getPositionDescription());
    stringBuilder.append(": <inset> tag requires a 'drawable' attribute or child tag defining a drawable");
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    InsetState insetState = this.mState;
    if (insetState == null)
      return; 
    if (insetState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(insetState.mThemeAttrs, R.styleable.InsetDrawable);
      try {
        updateStateFromTypedArray(typedArray);
        verifyRequiredAttributes(typedArray);
        typedArray.recycle();
      } catch (XmlPullParserException xmlPullParserException) {
        rethrowAsRuntimeException((Exception)xmlPullParserException);
        typedArray.recycle();
      } finally {}
    } 
  }
  
  public int getIntrinsicHeight() {
    int i = getDrawable().getIntrinsicHeight();
    float f = this.mState.mInsetTop.mFraction + this.mState.mInsetBottom.mFraction;
    return (i < 0 || f >= 1.0F) ? -1 : ((int)(i / (1.0F - f)) + this.mState.mInsetTop.mDimension + this.mState.mInsetBottom.mDimension);
  }
  
  public int getIntrinsicWidth() {
    int i = getDrawable().getIntrinsicWidth();
    float f = this.mState.mInsetLeft.mFraction + this.mState.mInsetRight.mFraction;
    return (i < 0 || f >= 1.0F) ? -1 : ((int)(i / (1.0F - f)) + this.mState.mInsetLeft.mDimension + this.mState.mInsetRight.mDimension);
  }
  
  public int getOpacity() {
    InsetState insetState = this.mState;
    int i = getDrawable().getOpacity();
    getInsets(this.mTmpInsetRect);
    return (i == -1 && (this.mTmpInsetRect.left > 0 || this.mTmpInsetRect.top > 0 || this.mTmpInsetRect.right > 0 || this.mTmpInsetRect.bottom > 0)) ? -3 : i;
  }
  
  public Insets getOpticalInsets() {
    Insets insets = super.getOpticalInsets();
    getInsets(this.mTmpInsetRect);
    return Insets.of(insets.left + this.mTmpInsetRect.left, insets.top + this.mTmpInsetRect.top, insets.right + this.mTmpInsetRect.right, insets.bottom + this.mTmpInsetRect.bottom);
  }
  
  public void getOutline(Outline paramOutline) {
    getDrawable().getOutline(paramOutline);
  }
  
  public boolean getPadding(Rect paramRect) {
    null = super.getPadding(paramRect);
    getInsets(this.mTmpInsetRect);
    paramRect.left += this.mTmpInsetRect.left;
    paramRect.right += this.mTmpInsetRect.right;
    paramRect.top += this.mTmpInsetRect.top;
    paramRect.bottom += this.mTmpInsetRect.bottom;
    return (null || (this.mTmpInsetRect.left | this.mTmpInsetRect.right | this.mTmpInsetRect.top | this.mTmpInsetRect.bottom) != 0);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.InsetDrawable);
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    updateStateFromTypedArray(typedArray);
    verifyRequiredAttributes(typedArray);
    typedArray.recycle();
  }
  
  DrawableWrapper.DrawableWrapperState mutateConstantState() {
    InsetState insetState = new InsetState(this.mState, null);
    this.mState = insetState;
    return insetState;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    Rect rect = this.mTmpRect;
    rect.set(paramRect);
    rect.left += this.mState.mInsetLeft.getDimension(paramRect.width());
    rect.top += this.mState.mInsetTop.getDimension(paramRect.height());
    rect.right -= this.mState.mInsetRight.getDimension(paramRect.width());
    rect.bottom -= this.mState.mInsetBottom.getDimension(paramRect.height());
    super.onBoundsChange(rect);
  }
  
  static final class InsetState extends DrawableWrapper.DrawableWrapperState {
    InsetDrawable.InsetValue mInsetBottom;
    
    InsetDrawable.InsetValue mInsetLeft;
    
    InsetDrawable.InsetValue mInsetRight;
    
    InsetDrawable.InsetValue mInsetTop;
    
    private int[] mThemeAttrs;
    
    InsetState(InsetState param1InsetState, Resources param1Resources) {
      super(param1InsetState, param1Resources);
      if (param1InsetState != null) {
        this.mInsetLeft = param1InsetState.mInsetLeft.clone();
        this.mInsetTop = param1InsetState.mInsetTop.clone();
        this.mInsetRight = param1InsetState.mInsetRight.clone();
        this.mInsetBottom = param1InsetState.mInsetBottom.clone();
        if (param1InsetState.mDensity != this.mDensity)
          applyDensityScaling(param1InsetState.mDensity, this.mDensity); 
      } else {
        this.mInsetLeft = new InsetDrawable.InsetValue();
        this.mInsetTop = new InsetDrawable.InsetValue();
        this.mInsetRight = new InsetDrawable.InsetValue();
        this.mInsetBottom = new InsetDrawable.InsetValue();
      } 
    }
    
    private void applyDensityScaling(int param1Int1, int param1Int2) {
      this.mInsetLeft.scaleFromDensity(param1Int1, param1Int2);
      this.mInsetTop.scaleFromDensity(param1Int1, param1Int2);
      this.mInsetRight.scaleFromDensity(param1Int1, param1Int2);
      this.mInsetBottom.scaleFromDensity(param1Int1, param1Int2);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      InsetState insetState;
      if (param1Resources != null) {
        int i = (param1Resources.getDisplayMetrics()).densityDpi;
        if (i == 0)
          i = 160; 
        if (i != this.mDensity) {
          insetState = new InsetState(this, param1Resources);
        } else {
          insetState = this;
        } 
      } else {
        insetState = this;
      } 
      return new InsetDrawable(insetState, param1Resources);
    }
    
    void onDensityChanged(int param1Int1, int param1Int2) {
      super.onDensityChanged(param1Int1, param1Int2);
      applyDensityScaling(param1Int1, param1Int2);
    }
  }
  
  static final class InsetValue implements Cloneable {
    int mDimension;
    
    final float mFraction;
    
    public InsetValue() {
      this(0.0F, 0);
    }
    
    public InsetValue(float param1Float, int param1Int) {
      this.mFraction = param1Float;
      this.mDimension = param1Int;
    }
    
    public InsetValue clone() {
      return new InsetValue(this.mFraction, this.mDimension);
    }
    
    int getDimension(int param1Int) {
      return (int)(param1Int * this.mFraction) + this.mDimension;
    }
    
    void scaleFromDensity(int param1Int1, int param1Int2) {
      int i = this.mDimension;
      if (i != 0)
        this.mDimension = Bitmap.scaleFromDensity(i, param1Int1, param1Int2); 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/InsetDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
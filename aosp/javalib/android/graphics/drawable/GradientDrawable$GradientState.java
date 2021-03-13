package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.BlendMode;
import android.graphics.Insets;
import android.graphics.Rect;

final class GradientState extends Drawable.ConstantState {
  public int mAngle = 0;
  
  int[] mAttrCorners;
  
  int[] mAttrGradient;
  
  int[] mAttrPadding;
  
  int[] mAttrSize;
  
  int[] mAttrSolid;
  
  int[] mAttrStroke;
  
  BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
  
  float mCenterX = 0.5F;
  
  float mCenterY = 0.5F;
  
  public int mChangingConfigurations;
  
  int mDensity = 160;
  
  public boolean mDither = false;
  
  public int mGradient = 0;
  
  public int[] mGradientColors;
  
  float mGradientRadius = 0.5F;
  
  int mGradientRadiusType = 0;
  
  public int mHeight = -1;
  
  public int mInnerRadius = -1;
  
  public float mInnerRadiusRatio = 3.0F;
  
  boolean mOpaqueOverBounds;
  
  boolean mOpaqueOverShape;
  
  public Insets mOpticalInsets = Insets.NONE;
  
  public GradientDrawable.Orientation mOrientation;
  
  public Rect mPadding = null;
  
  public float[] mPositions;
  
  public float mRadius = 0.0F;
  
  public float[] mRadiusArray = null;
  
  public int mShape = 0;
  
  public ColorStateList mSolidColors;
  
  public ColorStateList mStrokeColors;
  
  public float mStrokeDashGap = 0.0F;
  
  public float mStrokeDashWidth = 0.0F;
  
  public int mStrokeWidth = -1;
  
  public int[] mTempColors;
  
  public float[] mTempPositions;
  
  int[] mThemeAttrs;
  
  public int mThickness = -1;
  
  public float mThicknessRatio = 9.0F;
  
  ColorStateList mTint = null;
  
  boolean mUseLevel = false;
  
  boolean mUseLevelForShape = true;
  
  public int mWidth = -1;
  
  public GradientState(GradientState paramGradientState, Resources paramResources) {
    this.mChangingConfigurations = paramGradientState.mChangingConfigurations;
    this.mShape = paramGradientState.mShape;
    this.mGradient = paramGradientState.mGradient;
    this.mAngle = paramGradientState.mAngle;
    this.mOrientation = paramGradientState.mOrientation;
    this.mSolidColors = paramGradientState.mSolidColors;
    int[] arrayOfInt = paramGradientState.mGradientColors;
    if (arrayOfInt != null)
      this.mGradientColors = (int[])arrayOfInt.clone(); 
    float[] arrayOfFloat = paramGradientState.mPositions;
    if (arrayOfFloat != null)
      this.mPositions = (float[])arrayOfFloat.clone(); 
    this.mStrokeColors = paramGradientState.mStrokeColors;
    this.mStrokeWidth = paramGradientState.mStrokeWidth;
    this.mStrokeDashWidth = paramGradientState.mStrokeDashWidth;
    this.mStrokeDashGap = paramGradientState.mStrokeDashGap;
    this.mRadius = paramGradientState.mRadius;
    arrayOfFloat = paramGradientState.mRadiusArray;
    if (arrayOfFloat != null)
      this.mRadiusArray = (float[])arrayOfFloat.clone(); 
    if (paramGradientState.mPadding != null)
      this.mPadding = new Rect(paramGradientState.mPadding); 
    this.mWidth = paramGradientState.mWidth;
    this.mHeight = paramGradientState.mHeight;
    this.mInnerRadiusRatio = paramGradientState.mInnerRadiusRatio;
    this.mThicknessRatio = paramGradientState.mThicknessRatio;
    this.mInnerRadius = paramGradientState.mInnerRadius;
    this.mThickness = paramGradientState.mThickness;
    this.mDither = paramGradientState.mDither;
    this.mOpticalInsets = paramGradientState.mOpticalInsets;
    this.mCenterX = paramGradientState.mCenterX;
    this.mCenterY = paramGradientState.mCenterY;
    this.mGradientRadius = paramGradientState.mGradientRadius;
    this.mGradientRadiusType = paramGradientState.mGradientRadiusType;
    this.mUseLevel = paramGradientState.mUseLevel;
    this.mUseLevelForShape = paramGradientState.mUseLevelForShape;
    this.mOpaqueOverBounds = paramGradientState.mOpaqueOverBounds;
    this.mOpaqueOverShape = paramGradientState.mOpaqueOverShape;
    this.mTint = paramGradientState.mTint;
    this.mBlendMode = paramGradientState.mBlendMode;
    this.mThemeAttrs = paramGradientState.mThemeAttrs;
    this.mAttrSize = paramGradientState.mAttrSize;
    this.mAttrGradient = paramGradientState.mAttrGradient;
    this.mAttrSolid = paramGradientState.mAttrSolid;
    this.mAttrStroke = paramGradientState.mAttrStroke;
    this.mAttrCorners = paramGradientState.mAttrCorners;
    this.mAttrPadding = paramGradientState.mAttrPadding;
    int i = Drawable.resolveDensity(paramResources, paramGradientState.mDensity);
    this.mDensity = i;
    int j = paramGradientState.mDensity;
    if (j != i)
      applyDensityScaling(j, i); 
  }
  
  public GradientState(GradientDrawable.Orientation paramOrientation, int[] paramArrayOfint) {
    this.mOrientation = paramOrientation;
    setGradientColors(paramArrayOfint);
  }
  
  private void applyDensityScaling(int paramInt1, int paramInt2) {
    int i = this.mInnerRadius;
    if (i > 0)
      this.mInnerRadius = Drawable.scaleFromDensity(i, paramInt1, paramInt2, true); 
    i = this.mThickness;
    if (i > 0)
      this.mThickness = Drawable.scaleFromDensity(i, paramInt1, paramInt2, true); 
    if (this.mOpticalInsets != Insets.NONE)
      this.mOpticalInsets = Insets.of(Drawable.scaleFromDensity(this.mOpticalInsets.left, paramInt1, paramInt2, true), Drawable.scaleFromDensity(this.mOpticalInsets.top, paramInt1, paramInt2, true), Drawable.scaleFromDensity(this.mOpticalInsets.right, paramInt1, paramInt2, true), Drawable.scaleFromDensity(this.mOpticalInsets.bottom, paramInt1, paramInt2, true)); 
    Rect rect = this.mPadding;
    if (rect != null) {
      rect.left = Drawable.scaleFromDensity(rect.left, paramInt1, paramInt2, false);
      rect = this.mPadding;
      rect.top = Drawable.scaleFromDensity(rect.top, paramInt1, paramInt2, false);
      rect = this.mPadding;
      rect.right = Drawable.scaleFromDensity(rect.right, paramInt1, paramInt2, false);
      rect = this.mPadding;
      rect.bottom = Drawable.scaleFromDensity(rect.bottom, paramInt1, paramInt2, false);
    } 
    float f = this.mRadius;
    if (f > 0.0F)
      this.mRadius = Drawable.scaleFromDensity(f, paramInt1, paramInt2); 
    float[] arrayOfFloat = this.mRadiusArray;
    if (arrayOfFloat != null) {
      arrayOfFloat[0] = Drawable.scaleFromDensity((int)arrayOfFloat[0], paramInt1, paramInt2, true);
      arrayOfFloat = this.mRadiusArray;
      arrayOfFloat[1] = Drawable.scaleFromDensity((int)arrayOfFloat[1], paramInt1, paramInt2, true);
      arrayOfFloat = this.mRadiusArray;
      arrayOfFloat[2] = Drawable.scaleFromDensity((int)arrayOfFloat[2], paramInt1, paramInt2, true);
      arrayOfFloat = this.mRadiusArray;
      arrayOfFloat[3] = Drawable.scaleFromDensity((int)arrayOfFloat[3], paramInt1, paramInt2, true);
    } 
    i = this.mStrokeWidth;
    if (i > 0)
      this.mStrokeWidth = Drawable.scaleFromDensity(i, paramInt1, paramInt2, true); 
    if (this.mStrokeDashWidth > 0.0F)
      this.mStrokeDashWidth = Drawable.scaleFromDensity(this.mStrokeDashGap, paramInt1, paramInt2); 
    f = this.mStrokeDashGap;
    if (f > 0.0F)
      this.mStrokeDashGap = Drawable.scaleFromDensity(f, paramInt1, paramInt2); 
    if (this.mGradientRadiusType == 0)
      this.mGradientRadius = Drawable.scaleFromDensity(this.mGradientRadius, paramInt1, paramInt2); 
    i = this.mWidth;
    if (i > 0)
      this.mWidth = Drawable.scaleFromDensity(i, paramInt1, paramInt2, true); 
    i = this.mHeight;
    if (i > 0)
      this.mHeight = Drawable.scaleFromDensity(i, paramInt1, paramInt2, true); 
  }
  
  private void computeOpacity() {
    boolean bool1 = false;
    this.mOpaqueOverBounds = false;
    this.mOpaqueOverShape = false;
    if (this.mGradientColors != null) {
      byte b = 0;
      while (true) {
        int[] arrayOfInt = this.mGradientColors;
        if (b < arrayOfInt.length) {
          if (!GradientDrawable.isOpaque(arrayOfInt[b]))
            return; 
          b++;
          continue;
        } 
        break;
      } 
    } 
    if (this.mGradientColors == null && this.mSolidColors == null)
      return; 
    this.mOpaqueOverShape = true;
    boolean bool2 = bool1;
    if (this.mShape == 0) {
      bool2 = bool1;
      if (this.mRadius <= 0.0F) {
        bool2 = bool1;
        if (this.mRadiusArray == null)
          bool2 = true; 
      } 
    } 
    this.mOpaqueOverBounds = bool2;
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs == null && this.mAttrSize == null && this.mAttrGradient == null && this.mAttrSolid == null && this.mAttrStroke == null && this.mAttrCorners == null && this.mAttrPadding == null) {
      ColorStateList colorStateList = this.mTint;
      if (colorStateList == null || !colorStateList.canApplyTheme()) {
        colorStateList = this.mStrokeColors;
        if (colorStateList == null || !colorStateList.canApplyTheme()) {
          colorStateList = this.mSolidColors;
          return ((colorStateList != null && colorStateList.canApplyTheme()) || super.canApplyTheme());
        } 
      } 
    } 
    return true;
  }
  
  public int getChangingConfigurations() {
    byte b;
    boolean bool;
    int i = this.mChangingConfigurations;
    ColorStateList colorStateList = this.mStrokeColors;
    int j = 0;
    if (colorStateList != null) {
      b = colorStateList.getChangingConfigurations();
    } else {
      b = 0;
    } 
    colorStateList = this.mSolidColors;
    if (colorStateList != null) {
      bool = colorStateList.getChangingConfigurations();
    } else {
      bool = false;
    } 
    colorStateList = this.mTint;
    if (colorStateList != null)
      j = colorStateList.getChangingConfigurations(); 
    return i | b | bool | j;
  }
  
  public GradientDrawable.Orientation getOrientation() {
    return this.mOrientation;
  }
  
  public boolean hasCenterColor() {
    boolean bool;
    int[] arrayOfInt = this.mGradientColors;
    if (arrayOfInt != null && arrayOfInt.length == 3) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Drawable newDrawable() {
    return new GradientDrawable(this, null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    GradientState gradientState;
    if (Drawable.resolveDensity(paramResources, this.mDensity) != this.mDensity) {
      gradientState = new GradientState(this, paramResources);
    } else {
      gradientState = this;
    } 
    return new GradientDrawable(gradientState, paramResources, null);
  }
  
  public void setCornerRadii(float[] paramArrayOffloat) {
    this.mRadiusArray = paramArrayOffloat;
    if (paramArrayOffloat == null)
      this.mRadius = 0.0F; 
    computeOpacity();
  }
  
  public void setCornerRadius(float paramFloat) {
    float f = paramFloat;
    if (paramFloat < 0.0F)
      f = 0.0F; 
    this.mRadius = f;
    this.mRadiusArray = null;
    computeOpacity();
  }
  
  public final void setDensity(int paramInt) {
    if (this.mDensity != paramInt) {
      int i = this.mDensity;
      this.mDensity = paramInt;
      applyDensityScaling(i, paramInt);
    } 
  }
  
  public void setGradientCenter(float paramFloat1, float paramFloat2) {
    this.mCenterX = paramFloat1;
    this.mCenterY = paramFloat2;
  }
  
  public void setGradientColors(int[] paramArrayOfint) {
    this.mGradientColors = paramArrayOfint;
    this.mSolidColors = null;
    computeOpacity();
  }
  
  public void setGradientRadius(float paramFloat, int paramInt) {
    this.mGradientRadius = paramFloat;
    this.mGradientRadiusType = paramInt;
  }
  
  public void setGradientType(int paramInt) {
    this.mGradient = paramInt;
  }
  
  public void setShape(int paramInt) {
    this.mShape = paramInt;
    computeOpacity();
  }
  
  public void setSize(int paramInt1, int paramInt2) {
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
  }
  
  public void setSolidColors(ColorStateList paramColorStateList) {
    this.mGradientColors = null;
    this.mSolidColors = paramColorStateList;
    computeOpacity();
  }
  
  public void setStroke(int paramInt, ColorStateList paramColorStateList, float paramFloat1, float paramFloat2) {
    this.mStrokeWidth = paramInt;
    this.mStrokeColors = paramColorStateList;
    this.mStrokeDashWidth = paramFloat1;
    this.mStrokeDashGap = paramFloat2;
    computeOpacity();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/GradientDrawable$GradientState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
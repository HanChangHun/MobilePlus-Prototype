package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.BlendMode;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;

final class ShapeState extends Drawable.ConstantState {
  int mAlpha = 255;
  
  BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
  
  int mChangingConfigurations;
  
  int mIntrinsicHeight;
  
  int mIntrinsicWidth;
  
  Rect mPadding;
  
  final Paint mPaint;
  
  ShapeDrawable.ShaderFactory mShaderFactory;
  
  Shape mShape;
  
  int[] mThemeAttrs;
  
  ColorStateList mTint;
  
  ShapeState() {
    this.mPaint = new Paint(1);
  }
  
  ShapeState(ShapeState paramShapeState) {
    this.mChangingConfigurations = paramShapeState.mChangingConfigurations;
    this.mPaint = new Paint(paramShapeState.mPaint);
    this.mThemeAttrs = paramShapeState.mThemeAttrs;
    Shape shape = paramShapeState.mShape;
    if (shape != null)
      try {
        this.mShape = shape.clone();
      } catch (CloneNotSupportedException cloneNotSupportedException) {
        this.mShape = paramShapeState.mShape;
      }  
    this.mTint = paramShapeState.mTint;
    this.mBlendMode = paramShapeState.mBlendMode;
    if (paramShapeState.mPadding != null)
      this.mPadding = new Rect(paramShapeState.mPadding); 
    this.mIntrinsicWidth = paramShapeState.mIntrinsicWidth;
    this.mIntrinsicHeight = paramShapeState.mIntrinsicHeight;
    this.mAlpha = paramShapeState.mAlpha;
    this.mShaderFactory = paramShapeState.mShaderFactory;
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs == null) {
      ColorStateList colorStateList = this.mTint;
      return (colorStateList != null && colorStateList.canApplyTheme());
    } 
    return true;
  }
  
  public int getChangingConfigurations() {
    byte b;
    int i = this.mChangingConfigurations;
    ColorStateList colorStateList = this.mTint;
    if (colorStateList != null) {
      b = colorStateList.getChangingConfigurations();
    } else {
      b = 0;
    } 
    return i | b;
  }
  
  public Drawable newDrawable() {
    return new ShapeDrawable(new ShapeState(this), null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new ShapeDrawable(new ShapeState(this), paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/ShapeDrawable$ShapeState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.BlendMode;

final class ColorStateListDrawableState extends Drawable.ConstantState {
  int mAlpha = -1;
  
  BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
  
  int mChangingConfigurations = 0;
  
  ColorStateList mColor = null;
  
  ColorStateList mTint = null;
  
  ColorStateListDrawableState() {}
  
  ColorStateListDrawableState(ColorStateListDrawableState paramColorStateListDrawableState) {
    this.mColor = paramColorStateListDrawableState.mColor;
    this.mTint = paramColorStateListDrawableState.mTint;
    this.mAlpha = paramColorStateListDrawableState.mAlpha;
    this.mBlendMode = paramColorStateListDrawableState.mBlendMode;
    this.mChangingConfigurations = paramColorStateListDrawableState.mChangingConfigurations;
  }
  
  public boolean canApplyTheme() {
    ColorStateList colorStateList = this.mColor;
    if (colorStateList == null || !colorStateList.canApplyTheme()) {
      colorStateList = this.mTint;
      return (colorStateList != null && colorStateList.canApplyTheme());
    } 
    return true;
  }
  
  public int getChangingConfigurations() {
    byte b;
    int i = this.mChangingConfigurations;
    ColorStateList colorStateList = this.mColor;
    int j = 0;
    if (colorStateList != null) {
      b = colorStateList.getChangingConfigurations();
    } else {
      b = 0;
    } 
    colorStateList = this.mTint;
    if (colorStateList != null)
      j = colorStateList.getChangingConfigurations(); 
    return i | b | j;
  }
  
  public boolean hasFocusStateSpecified() {
    ColorStateList colorStateList = this.mColor;
    if (colorStateList == null || !colorStateList.hasFocusStateSpecified()) {
      colorStateList = this.mTint;
      return (colorStateList != null && colorStateList.hasFocusStateSpecified());
    } 
    return true;
  }
  
  public boolean isStateful() {
    ColorStateList colorStateList = this.mColor;
    if (colorStateList == null || !colorStateList.isStateful()) {
      colorStateList = this.mTint;
      return (colorStateList != null && colorStateList.isStateful());
    } 
    return true;
  }
  
  public Drawable newDrawable() {
    return new ColorStateListDrawable(this, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/ColorStateListDrawable$ColorStateListDrawableState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
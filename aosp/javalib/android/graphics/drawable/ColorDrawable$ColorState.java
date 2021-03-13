package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.BlendMode;
import android.view.ViewDebug.ExportedProperty;

final class ColorState extends Drawable.ConstantState {
  int mBaseColor;
  
  BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
  
  int mChangingConfigurations;
  
  int[] mThemeAttrs;
  
  ColorStateList mTint = null;
  
  @ExportedProperty
  int mUseColor;
  
  ColorState() {}
  
  ColorState(ColorState paramColorState) {
    this.mThemeAttrs = paramColorState.mThemeAttrs;
    this.mBaseColor = paramColorState.mBaseColor;
    this.mUseColor = paramColorState.mUseColor;
    this.mChangingConfigurations = paramColorState.mChangingConfigurations;
    this.mTint = paramColorState.mTint;
    this.mBlendMode = paramColorState.mBlendMode;
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
    return new ColorDrawable(this, null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new ColorDrawable(this, paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/ColorDrawable$ColorState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
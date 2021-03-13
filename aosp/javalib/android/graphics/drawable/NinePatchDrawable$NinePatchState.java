package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.BlendMode;
import android.graphics.Insets;
import android.graphics.NinePatch;
import android.graphics.Rect;

final class NinePatchState extends Drawable.ConstantState {
  boolean mAutoMirrored = false;
  
  float mBaseAlpha = 1.0F;
  
  BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
  
  int mChangingConfigurations;
  
  boolean mDither = false;
  
  NinePatch mNinePatch = null;
  
  Insets mOpticalInsets = Insets.NONE;
  
  Rect mPadding = null;
  
  int[] mThemeAttrs;
  
  ColorStateList mTint = null;
  
  NinePatchState() {}
  
  NinePatchState(NinePatch paramNinePatch, Rect paramRect) {
    this(paramNinePatch, paramRect, null, false, false);
  }
  
  NinePatchState(NinePatch paramNinePatch, Rect paramRect1, Rect paramRect2) {
    this(paramNinePatch, paramRect1, paramRect2, false, false);
  }
  
  NinePatchState(NinePatch paramNinePatch, Rect paramRect1, Rect paramRect2, boolean paramBoolean1, boolean paramBoolean2) {
    this.mNinePatch = paramNinePatch;
    this.mPadding = paramRect1;
    this.mOpticalInsets = Insets.of(paramRect2);
    this.mDither = paramBoolean1;
    this.mAutoMirrored = paramBoolean2;
  }
  
  NinePatchState(NinePatchState paramNinePatchState) {
    this.mChangingConfigurations = paramNinePatchState.mChangingConfigurations;
    this.mNinePatch = paramNinePatchState.mNinePatch;
    this.mTint = paramNinePatchState.mTint;
    this.mBlendMode = paramNinePatchState.mBlendMode;
    this.mPadding = paramNinePatchState.mPadding;
    this.mOpticalInsets = paramNinePatchState.mOpticalInsets;
    this.mBaseAlpha = paramNinePatchState.mBaseAlpha;
    this.mDither = paramNinePatchState.mDither;
    this.mAutoMirrored = paramNinePatchState.mAutoMirrored;
    this.mThemeAttrs = paramNinePatchState.mThemeAttrs;
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs == null) {
      ColorStateList colorStateList = this.mTint;
      return ((colorStateList != null && colorStateList.canApplyTheme()) || super.canApplyTheme());
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
    return new NinePatchDrawable(this, null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new NinePatchDrawable(this, paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/NinePatchDrawable$NinePatchState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
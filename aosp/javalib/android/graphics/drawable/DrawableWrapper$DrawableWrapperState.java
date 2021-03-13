package android.graphics.drawable;

import android.content.res.Resources;

abstract class DrawableWrapperState extends Drawable.ConstantState {
  int mChangingConfigurations;
  
  int mDensity;
  
  Drawable.ConstantState mDrawableState;
  
  int mSrcDensityOverride;
  
  private int[] mThemeAttrs;
  
  DrawableWrapperState(DrawableWrapperState paramDrawableWrapperState, Resources paramResources) {
    char c2;
    char c1 = ' ';
    this.mDensity = 160;
    this.mSrcDensityOverride = 0;
    if (paramDrawableWrapperState != null) {
      this.mThemeAttrs = paramDrawableWrapperState.mThemeAttrs;
      this.mChangingConfigurations = paramDrawableWrapperState.mChangingConfigurations;
      this.mDrawableState = paramDrawableWrapperState.mDrawableState;
      this.mSrcDensityOverride = paramDrawableWrapperState.mSrcDensityOverride;
    } 
    if (paramResources != null) {
      c2 = (paramResources.getDisplayMetrics()).densityDpi;
    } else if (paramDrawableWrapperState != null) {
      c2 = paramDrawableWrapperState.mDensity;
    } else {
      c2 = Character.MIN_VALUE;
    } 
    if (!c2)
      c2 = c1; 
    this.mDensity = c2;
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs == null) {
      Drawable.ConstantState constantState = this.mDrawableState;
      return ((constantState != null && constantState.canApplyTheme()) || super.canApplyTheme());
    } 
    return true;
  }
  
  public boolean canConstantState() {
    boolean bool;
    if (this.mDrawableState != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int getChangingConfigurations() {
    byte b;
    int i = this.mChangingConfigurations;
    Drawable.ConstantState constantState = this.mDrawableState;
    if (constantState != null) {
      b = constantState.getChangingConfigurations();
    } else {
      b = 0;
    } 
    return i | b;
  }
  
  public Drawable newDrawable() {
    return newDrawable(null);
  }
  
  public abstract Drawable newDrawable(Resources paramResources);
  
  void onDensityChanged(int paramInt1, int paramInt2) {}
  
  public final void setDensity(int paramInt) {
    if (this.mDensity != paramInt) {
      int i = this.mDensity;
      this.mDensity = paramInt;
      onDensityChanged(i, paramInt);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/DrawableWrapper$DrawableWrapperState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
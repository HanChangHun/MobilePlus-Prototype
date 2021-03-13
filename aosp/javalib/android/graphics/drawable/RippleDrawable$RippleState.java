package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;

class RippleState extends LayerDrawable.LayerState {
  ColorStateList mColor = ColorStateList.valueOf(-65281);
  
  int mMaxRadius = -1;
  
  int[] mTouchThemeAttrs;
  
  public RippleState(LayerDrawable.LayerState paramLayerState, RippleDrawable paramRippleDrawable, Resources paramResources) {
    super(paramLayerState, paramRippleDrawable, paramResources);
    if (paramLayerState != null && paramLayerState instanceof RippleState) {
      RippleState rippleState = (RippleState)paramLayerState;
      this.mTouchThemeAttrs = rippleState.mTouchThemeAttrs;
      this.mColor = rippleState.mColor;
      this.mMaxRadius = rippleState.mMaxRadius;
      if (rippleState.mDensity != this.mDensity)
        applyDensityScaling(paramLayerState.mDensity, this.mDensity); 
    } 
  }
  
  private void applyDensityScaling(int paramInt1, int paramInt2) {
    int i = this.mMaxRadius;
    if (i != -1)
      this.mMaxRadius = Drawable.scaleFromDensity(i, paramInt1, paramInt2, true); 
  }
  
  public boolean canApplyTheme() {
    if (this.mTouchThemeAttrs == null) {
      ColorStateList colorStateList = this.mColor;
      return ((colorStateList != null && colorStateList.canApplyTheme()) || super.canApplyTheme());
    } 
    return true;
  }
  
  public int getChangingConfigurations() {
    byte b;
    int i = super.getChangingConfigurations();
    ColorStateList colorStateList = this.mColor;
    if (colorStateList != null) {
      b = colorStateList.getChangingConfigurations();
    } else {
      b = 0;
    } 
    return i | b;
  }
  
  public Drawable newDrawable() {
    return new RippleDrawable(this, null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new RippleDrawable(this, paramResources, null);
  }
  
  protected void onDensityChanged(int paramInt1, int paramInt2) {
    super.onDensityChanged(paramInt1, paramInt2);
    applyDensityScaling(paramInt1, paramInt2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/RippleDrawable$RippleState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */